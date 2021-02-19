package mb.mizinkobusters.kitpvp.listener

import amata1219.niflheimr.dsl.InventoryUI
import org.bukkit.event.EventPriority
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import amata1219.niflheimr.dsl.InventoryLayout
import amata1219.niflheimr.dsl.component.format.InventoryLines
import amata1219.niflheimr.dsl.component.slot.Slot
import org.bukkit.Material
import amata1219.niflheimr.event.InventoryUIClickEvent
import mb.mizinkobusters.kitpvp.other.ArmorGiver
import org.bukkit.enchantments.Enchantment
import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionData
import org.bukkit.potion.PotionType
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerRespawnEvent
import mb.mizinkobusters.kitpvp.utils.PlayerSalvationUtils
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.entity.EntityType
import org.bukkit.entity.Arrow
import org.bukkit.Sound
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.World
import org.bukkit.Bukkit
import org.bukkit.inventory.PlayerInventory
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.entity.Projectile
import java.io.File
import java.math.BigDecimal
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.event.entity.EntityDamageEvent.DamageCause
import java.util.HashMap
import java.util.UUID
import mb.mizinkobusters.kitpvp.Main
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.block.BlockFace
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.event.player.PlayerToggleSneakEvent
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.scheduler.BukkitRunnable
import mb.mizinkobusters.kitpvp.gui.KitMenu
import mb.mizinkobusters.kitpvp.gui.KitPurchaseMenu
import mb.mizinkobusters.kitpvp.listener.PlayerDeathListener
import mb.mizinkobusters.kitpvp.listener.PlayerRespawnListener
import mb.mizinkobusters.kitpvp.listener.VoidWalkingListener
import mb.mizinkobusters.kitpvp.kit.Archer
import mb.mizinkobusters.kitpvp.kit.Astronaut
import mb.mizinkobusters.kitpvp.kit.Berserker
import mb.mizinkobusters.kitpvp.kit.Blizzard
import mb.mizinkobusters.kitpvp.kit.Boxer
import mb.mizinkobusters.kitpvp.kit.Comet
import mb.mizinkobusters.kitpvp.kit.Fighter
import mb.mizinkobusters.kitpvp.kit.Fisherman
import mb.mizinkobusters.kitpvp.kit.Flame
import mb.mizinkobusters.kitpvp.kit.HealthBoost
import mb.mizinkobusters.kitpvp.kit.PotionHandler
import mb.mizinkobusters.kitpvp.kit.Revive
import mb.mizinkobusters.kitpvp.kit.Sniper
import mb.mizinkobusters.kitpvp.kit.Standard
import mb.mizinkobusters.kitpvp.kit.Tank
import mb.mizinkobusters.kitpvp.kit.Thunder
import mb.mizinkobusters.kitpvp.other.ArrowsRemover
import mb.mizinkobusters.kitpvp.other.FieldSender
import mb.mizinkobusters.kitpvp.other.MatchResultAnnounce
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

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
                            Location(player.world, 0.5, 11.0, 0.5, 0, 0),
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