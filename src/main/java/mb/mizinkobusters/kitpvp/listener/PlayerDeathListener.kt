package mb.mizinkobusters.kitpvp.listener

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import mb.mizinkobusters.kitpvp.utils.PlayerSalvationUtils
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class PlayerDeathListener : Listener {
    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        val player = event.entity
        if (!KitPvPUtils.isInWorld(player)) {
            return
        }
        event.deathMessage = null
        PlayerSalvationUtils.salvage(player)
        player.spigot().respawn()
    }
}