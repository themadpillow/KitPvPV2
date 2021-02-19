package mb.mizinkobusters.kitpvp

import mb.mizinkobusters.kitpvp.gui.KitMenu
import mb.mizinkobusters.kitpvp.gui.KitPurchaseMenu
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
import mb.mizinkobusters.kitpvp.listener.PlayerDeathListener
import mb.mizinkobusters.kitpvp.listener.PlayerRespawnListener
import mb.mizinkobusters.kitpvp.listener.VoidWalkingListener
import mb.mizinkobusters.kitpvp.other.ArrowsRemover
import mb.mizinkobusters.kitpvp.other.FieldSender
import mb.mizinkobusters.kitpvp.other.MatchResultAnnounce
import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), Listener {
    override fun onEnable() {
        Bukkit.getPluginManager().run {
            registerEvents(KitMenu(), this@Main)
            registerEvents(KitMenu(), this@Main)
            registerEvents(KitPurchaseMenu(), this@Main)
            registerEvents(PlayerDeathListener(), this@Main)
            registerEvents(PlayerRespawnListener(this@Main), this@Main)
            registerEvents(VoidWalkingListener(), this@Main)
            registerEvents(Archer(), this@Main)
            registerEvents(Astronaut(), this@Main)
            registerEvents(Berserker(), this@Main)
            registerEvents(Blizzard(), this@Main)
            registerEvents(Boxer(), this@Main)
            registerEvents(Comet(), this@Main)
            registerEvents(Counter(), this@Main)
            registerEvents(Fighter(), this@Main)
            registerEvents(Fisherman(), this@Main)
            registerEvents(Flame(), this@Main)
            registerEvents(HealthBoost(), this@Main)
            registerEvents(PotionHandler(), this@Main)
            registerEvents(Rabbit(), this@Main)
            registerEvents(Revive(), this@Main)
            registerEvents(Sniper(), this@Main)
            registerEvents(Standard(), this@Main)
            registerEvents(Tank(), this@Main)
            registerEvents(Thunder(), this@Main)
            registerEvents(ArrowsRemover(), this@Main)
            registerEvents(FieldSender(), this@Main)
            registerEvents(MatchResultAnnounce(), this@Main)
            registerEvents(KitPvPUtils, this@Main)
        }
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