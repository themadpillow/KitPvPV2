package mb.mizinkobusters.kitpvp.utils

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
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object KitPvPUtils : Listener {
    var kit = HashMap<UUID, String?>()
    var streak = HashMap<UUID, Int>()
    fun getKit(player: Player?): String? {
        return if (kit.getOrDefault(player!!.uniqueId, "none") != null) {
            kit.getOrDefault(player.uniqueId, "none")
        } else "none"
    }

    fun hasKit(player: Player?): Boolean {
        return getKit(player) != "none"
    }

    fun resetKit(player: Player) {
        kit.remove(player.uniqueId)
    }

    fun setKit(player: Player, name: String?) {
        if (hasKit(player)) {
            resetKit(player)
        }
        kit[player.uniqueId] = name
    }

    fun getStreak(player: Player?): Int {
        return streak.getOrDefault(player!!.uniqueId, 0)
    }

    fun isInWorld(player: Player?): Boolean {
        return player!!.world.name == "kitpvp" || player.world.name == "kitpvp2"
    }

    @EventHandler
    fun setStreak(event: PlayerDeathEvent) {
        val dead = event.entity
        if (!isInWorld(dead)) {
            return
        }
        streak[dead.uniqueId] = 0
        if (dead.killer == null) {
            return
        }
        val killer = dead.killer
        streak[killer!!.uniqueId] = getStreak(killer) + 1
    }

    fun getGapple(player: Player): Int {
        val pi = player.inventory
        var apple = 0
        for (i in 0..35) {
            if (pi.getItem(i) == null) {
                continue
            }
            if (pi.getItem(i)!!.type == Material.AIR) {
                continue
            }
            if (pi.getItem(i)!!.type == Material.GOLDEN_APPLE) {
                apple += pi.getItem(i)!!.amount
            }
        }
        return apple
    }
}