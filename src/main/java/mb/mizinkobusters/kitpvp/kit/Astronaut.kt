package mb.mizinkobusters.kitpvp.kit

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import mb.mizinkobusters.kitpvp.utils.PlayerSalvationUtils
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDamageEvent.DamageCause
import org.bukkit.event.player.PlayerRespawnEvent

class Astronaut : Listener {
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
        if (KitPvPUtils.getKit(killer) != "Astronaut") {
            return
        }
        PlayerSalvationUtils.heal(player)
    }

    @EventHandler
    fun onDamage(event: EntityDamageEvent) {
        if (event.entity.type != EntityType.PLAYER) {
            return
        }
        val player = event.entity as Player
        if (KitPvPUtils.getKit(player) != "Astronaut") {
            return
        }
        if (event.cause != DamageCause.FALL) {
            return
        }
        event.isCancelled = true
    } // TODO ダブルジャンプの実装
}