package mb.mizinkobusters.kitpvp.other

import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent

class ArrowsRemover : Listener {
    @EventHandler
    fun onHitArrow(event: ProjectileHitEvent) {
        val projectile = event.entity
        if (projectile.type != EntityType.ARROW) {
            return
        }
        if (!(projectile.world.name == "kitpvp" || projectile.world.name == "kitpvp2")) {
            return
        }
        projectile.remove()
    }
}