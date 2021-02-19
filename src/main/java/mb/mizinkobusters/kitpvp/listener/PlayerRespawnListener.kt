package mb.mizinkobusters.kitpvp.listener

import mb.mizinkobusters.kitpvp.Main
import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import mb.mizinkobusters.kitpvp.utils.PlayerSalvationUtils
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class PlayerRespawnListener(plugin: Main) : Listener {
    private val plugin: JavaPlugin
    private val prefix = "§f[§dKitPvP§f] "

    @EventHandler
    fun onSneak(event: PlayerToggleSneakEvent) {
        val player = event.player
        if (!KitPvPUtils.isInWorld(player)) {
            return
        }
        if (!KitPvPUtils.hasKit(player)) {
            return
        }
        if (!event.isSneaking
            && player.hasMetadata("respawning")
        ) {
            player.removeMetadata("respawning", plugin)
            player.sendMessage("$prefix§eスニークを終了したためリスポーンをキャンセルしました")
        }
        if (event.isSneaking
            && !player.hasMetadata("respawning")
        ) {
            player.setMetadata("respawning", FixedMetadataValue(plugin, this))
            player.sendMessage("$prefix§aリスポーンを申請しました")
            player.sendMessage("$prefix§e8秒間§7その場から動かないでください...")
            object : BukkitRunnable() {
                var i = 0
                override fun run() {
                    if (7 < i || !player.hasMetadata("respawning")) {
                        cancel()
                        return
                    }
                    i++
                    if (event.isSneaking && player.hasMetadata("respawning")) {
                        val sec = 8 - i
                        player.sendMessage("$prefix§7リスポーンまであと... §c$sec§7秒")
                    }
                    if (i == 8 && player.hasMetadata("respawning")) {
                        player.teleport(
                            Location(player.world, 0.5, 11.0, 0.5, 0f, 0f),
                            PlayerTeleportEvent.TeleportCause.PLUGIN
                        )
                        player.removeMetadata("respawning", plugin)
                        PlayerSalvationUtils.salvage(player)
                        player.sendMessage("$prefix§aリスポーンに成功しました")
                    }
                }
            }.runTaskTimerAsynchronously(plugin, 20, 20)
        }
    }

    @EventHandler
    fun onMove(event: PlayerMoveEvent) {
        val player = event.player
        if (!KitPvPUtils.isInWorld(player)) {
            return
        }
        if (!KitPvPUtils.hasKit(player)) {
            return
        }
        val from = event.from.block
        val to = event.to!!.block
        if (from.location.distance(to.location) < 1) {
            return
        }
        if (!player.hasMetadata("respawning")) {
            return
        }
        player.removeMetadata("respawning", plugin)
        player.sendMessage("$prefix§eその場から動いたためリスポーンをキャンセルしました")
    }

    init {
        this.plugin = plugin
    }
}