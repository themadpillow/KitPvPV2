package mb.mizinkobusters.kitpvp.other

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class FieldSender : Listener {
    private val prefix = "§f[§dKitPvP§f] "

    @EventHandler
    fun onStepPlate(event: PlayerInteractEvent) {
        val player = event.player
        if (!KitPvPUtils.isInWorld(player)) {
            return
        }
        if (event.action != Action.PHYSICAL) {
            return
        }
        if (!KitPvPUtils.hasKit(player)) {
            player.sendMessage("$prefix§cKitを選択してください")
            return
        }
        val loc = event.clickedBlock!!.location

        // 山岳地帯へTP
        if (loc.blockX == 4 && loc.blockY == 12 && loc.blockZ == 4) {
            player.teleport(Location(player.world, 29.5, 5.0, 25.5), PlayerTeleportEvent.TeleportCause.PLUGIN)
            player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0, false, false))
        }

        // キノコ地帯へTP
        if (loc.blockX == -4 && loc.blockY == 12 && loc.blockZ == 4) {
            player.teleport(Location(player.world, -39.5, 11.0, 13.5), PlayerTeleportEvent.TeleportCause.PLUGIN)
            player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0, false, false))
        }

        // 氷原地帯へTP
        if (loc.blockX == -4 && loc.blockY == 12 && loc.blockZ == -4) {
            player.teleport(Location(player.world, -17.5, 7.0, -41.5), PlayerTeleportEvent.TeleportCause.PLUGIN)
            player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0, false, false))
        }

        // 火山地帯へTP
        if (loc.blockX == 4 && loc.blockY == 12 && loc.blockZ == -4) {
            player.teleport(Location(player.world, 35.5, 5.0, -8.5), PlayerTeleportEvent.TeleportCause.PLUGIN)
            player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0, false, false))
        }
    }
}