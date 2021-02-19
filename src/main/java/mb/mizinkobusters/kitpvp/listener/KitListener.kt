package mb.mizinkobusters.kitpvp.listener

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerItemConsumeEvent

object KitListener : Listener {
    @EventHandler
    fun onEatGapple(event: PlayerItemConsumeEvent) {
        val player = event.player
        val item = event.item
        if (item.type != Material.GOLDEN_APPLE) return
        if (!KitPvPUtils.isInWorld(player)) return

        val killerKit = KitPvPUtils.getKit(player) ?: return
        killerKit.onEatGapple(event)
    }

    @EventHandler
    fun onKill(event: PlayerDeathEvent) {
        val killer = event.entity.killer ?: return
        if (!KitPvPUtils.isInWorld(killer)) return

        val killerKit = KitPvPUtils.getKit(killer) ?: return
        killerKit.onKill(event)
    }

    @EventHandler
    fun onDamage(event: EntityDamageEvent) {
        val player = event.entity
        if (player !is Player) return
        if (!KitPvPUtils.isInWorld(player)) return

        val playerKit = KitPvPUtils.getKit(player) ?: return
        playerKit.onDamage(event)
    }

    @EventHandler
    fun onDamageByEntity(event: EntityDamageByEntityEvent) {
        val damager = event.damager
        if (damager !is Player) return
        if (!KitPvPUtils.isInWorld(damager)) return

        val damagerKit = KitPvPUtils.getKit(damager) ?: return
        damagerKit.onDamageByEntity(event)
    }

    @EventHandler
    fun onFish(event: PlayerFishEvent) {
        val player = event.player
        if (!KitPvPUtils.isInWorld(player)) return

        val killerKit = KitPvPUtils.getKit(player) ?: return
        killerKit.onFish(event)
    }
}