package mb.mizinkobusters.kitpvp.utils

import java.util.HashMap
import java.util.UUID
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

object KitPvPUtils : Listener {
    var kit = HashMap<UUID, String?>()
    private var streak = HashMap<UUID, Int>()
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