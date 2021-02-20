package mb.mizinkobusters.kitpvp

import mb.mizinkobusters.kitpvp.listener.FallToSpongeListener
import mb.mizinkobusters.kitpvp.listener.InventoryListener
import mb.mizinkobusters.kitpvp.listener.KitListener
import mb.mizinkobusters.kitpvp.listener.PlayerListener
import mb.mizinkobusters.kitpvp.listener.VoidWalkingListener
import mb.mizinkobusters.kitpvp.other.ArrowsRemover
import mb.mizinkobusters.kitpvp.other.FieldSender
import mb.mizinkobusters.kitpvp.other.MatchResultAnnounce
import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin(), Listener {
    companion object {
        lateinit var instance: JavaPlugin
        const val PREFIX = "§f[§dKitPvP§f] "
    }

    init {
        instance = this
    }

    override fun onEnable() {
        Bukkit.getPluginManager().run {
            registerEvents(KitListener, this@Main)
            registerEvents(PlayerListener, this@Main)
            registerEvents(VoidWalkingListener, this@Main)
            registerEvents(KitListener, this@Main)
            registerEvents(ArrowsRemover, this@Main)
            registerEvents(FieldSender, this@Main)
            registerEvents(MatchResultAnnounce, this@Main)
            registerEvents(KitPvPUtils, this@Main)
            registerEvents(InventoryListener, this@Main)
            registerEvents(FallToSpongeListener, this@Main)
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