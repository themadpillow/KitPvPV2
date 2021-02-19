package mb.mizinkobusters.kitpvp

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
import mb.mizinkobusters.kitpvp.kit.Counter
import mb.mizinkobusters.kitpvp.kit.Fighter
import mb.mizinkobusters.kitpvp.kit.Fisherman
import mb.mizinkobusters.kitpvp.kit.Flame
import mb.mizinkobusters.kitpvp.kit.HealthBoost
import mb.mizinkobusters.kitpvp.kit.PotionHandler
import mb.mizinkobusters.kitpvp.kit.Rabbit
import mb.mizinkobusters.kitpvp.kit.Revive
import mb.mizinkobusters.kitpvp.kit.Sniper
import mb.mizinkobusters.kitpvp.kit.Standard
import mb.mizinkobusters.kitpvp.kit.Tank
import mb.mizinkobusters.kitpvp.kit.Thunder
import mb.mizinkobusters.kitpvp.other.ArrowsRemover
import mb.mizinkobusters.kitpvp.other.FieldSender
import mb.mizinkobusters.kitpvp.other.MatchResultAnnounce
import org.bukkit.event.Listener

class Main : JavaPlugin(), Listener {
    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(KitMenu(), this)
        Bukkit.getPluginManager().registerEvents(KitPurchaseMenu(), this)
        Bukkit.getPluginManager().registerEvents(PlayerDeathListener(), this)
        Bukkit.getPluginManager().registerEvents(PlayerRespawnListener(this), this)
        Bukkit.getPluginManager().registerEvents(VoidWalkingListener(), this)
        Bukkit.getPluginManager().registerEvents(Archer(), this)
        Bukkit.getPluginManager().registerEvents(Astronaut(), this)
        Bukkit.getPluginManager().registerEvents(Berserker(), this)
        Bukkit.getPluginManager().registerEvents(Blizzard(), this)
        Bukkit.getPluginManager().registerEvents(Boxer(), this)
        Bukkit.getPluginManager().registerEvents(Comet(), this)
        Bukkit.getPluginManager().registerEvents(Counter(), this)
        Bukkit.getPluginManager().registerEvents(Fighter(), this)
        Bukkit.getPluginManager().registerEvents(Fisherman(), this)
        Bukkit.getPluginManager().registerEvents(Flame(), this)
        Bukkit.getPluginManager().registerEvents(HealthBoost(), this)
        Bukkit.getPluginManager().registerEvents(PotionHandler(), this)
        Bukkit.getPluginManager().registerEvents(Rabbit(), this)
        Bukkit.getPluginManager().registerEvents(Revive(), this)
        Bukkit.getPluginManager().registerEvents(Sniper(), this)
        Bukkit.getPluginManager().registerEvents(Standard(), this)
        Bukkit.getPluginManager().registerEvents(Tank(), this)
        Bukkit.getPluginManager().registerEvents(Thunder(), this)
        Bukkit.getPluginManager().registerEvents(ArrowsRemover(), this)
        Bukkit.getPluginManager().registerEvents(FieldSender(), this)
        Bukkit.getPluginManager().registerEvents(MatchResultAnnounce(), this)
        Bukkit.getPluginManager().registerEvents(KitPvPUtils(), this)
        val name = description.name
        val ver = description.version
        val author = description.authors
        Bukkit.getLogger().info("$name(v.$ver) by $author")
        Bukkit.getLogger().info("Technical cooperation:")
        Bukkit.getLogger().info(" - amata1219(https://github.com/amata1219)")
        Bukkit.getLogger().info("Now Loading...")
    }

    override fun onDisable() {
        Bukkit.getLogger().info("See You Next Play!")
    }
}