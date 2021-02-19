package mb.mizinkobusters.kitpvp.kit

import kotlin.math.abs
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent

object Sniper : BaseKit {
    override fun onDamageByEntity(event: EntityDamageByEntityEvent) {
        super.onDamageByEntity(event)

        val arrow = event.damager
        if (arrow !is Arrow) return

        val shooter = arrow.shooter
        if (shooter !is Player) return

        val distance = shooter.location.distance(event.entity.location)
        val damage = event.damage
        if (abs(distance) < 10) {
            if (damage <= 2) {
                event.damage = 0.0
                shooter.sendMessage("§7Skill Trigger: §aSniper(-2)")
            } else {
                event.damage = damage - 2
                shooter.sendMessage("§7Skill Trigger: §aSniper(-2)")
            }
        } else if (10 <= abs(distance) && abs(distance) < 15) {
            shooter.sendMessage("§7Skill Trigger: §aSniper(+0)")
        } else if (15 <= abs(distance) && abs(distance) < 30) {
            event.damage = damage + 1
            shooter.sendMessage("§7Skill Trigger: §aSniper(+1)")
        } else if (30 <= abs(distance)) {
            event.damage = damage + 2
            shooter.sendMessage("§7Skill Trigger: §aSniper(+2)")
        }
    }
}