package mb.mizinkobusters.kitpvp.other

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
import org.bukkit.Location
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action

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