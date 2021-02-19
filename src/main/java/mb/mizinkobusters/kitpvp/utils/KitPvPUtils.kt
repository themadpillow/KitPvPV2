package mb.mizinkobusters.kitpvp.utils

import java.util.HashMap
import java.util.UUID
import mb.mizinkobusters.kitpvp.kit.BaseKit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

object KitPvPUtils : Listener {
    private val kitMap = HashMap<UUID, BaseKit>()
    private val streak = HashMap<UUID, Int>()

    fun getKit(player: Player): BaseKit? {
        return kitMap[player.uniqueId]
    }

    fun hasKit(player: Player): Boolean {
        return kitMap.containsKey(player.uniqueId)
    }

    fun resetKit(player: Player) {
        kitMap.remove(player.uniqueId)
    }

    fun setKit(player: Player, kit: BaseKit?) {
        if (kit == null) {
            kitMap.remove(player.uniqueId)
        } else {
            kitMap[player.uniqueId] = kit
        }
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