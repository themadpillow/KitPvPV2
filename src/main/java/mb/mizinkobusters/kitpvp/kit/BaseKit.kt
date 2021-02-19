package mb.mizinkobusters.kitpvp.kit

import mb.mizinkobusters.kitpvp.utils.PlayerSalvationUtils
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerItemConsumeEvent

interface BaseKit {
    val isHealOnKill: Boolean
        get() = true

    fun onEatGapple(event: PlayerItemConsumeEvent) {

    }

    fun onKill(event: PlayerDeathEvent) {
        if (isHealOnKill) {
            PlayerSalvationUtils.heal(event.entity.killer)
        }
    }

    fun onDamage(event: EntityDamageEvent) {
    }

    fun onDamageByEntity(event: EntityDamageByEntityEvent) {
    }

    fun onFish(event: PlayerFishEvent) {
    }

}