package mb.mizinkobusters.kitpvp.kit

import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDamageEvent.DamageCause

object Astronaut : BaseKit {
    override fun onDamage(event: EntityDamageEvent) {
        super.onDamage(event)

        if (event.cause == DamageCause.FALL) {
            event.isCancelled = true
        }
    } // TODO ダブルジャンプの実装
}