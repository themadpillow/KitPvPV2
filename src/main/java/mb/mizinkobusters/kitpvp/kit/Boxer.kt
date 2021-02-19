package mb.mizinkobusters.kitpvp.kit

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import mb.mizinkobusters.kitpvp.utils.PlayerSalvationUtils
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRespawnEvent

class Boxer : Listener {
    @EventHandler
    fun onKill(event: PlayerRespawnEvent) {
        val player = event.player
        if (player.killer == null) {
            return
        }
        val killer = player.killer
        if (!KitPvPUtils.isInWorld(killer)) {
            return
        }
        if (KitPvPUtils.getKit(killer) != "Boxer") {
            return
        }
        PlayerSalvationUtils.heal(player)
    }
}