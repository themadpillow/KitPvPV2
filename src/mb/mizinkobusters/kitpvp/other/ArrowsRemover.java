package mb.mizinkobusters.kitpvp.other;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ArrowsRemover implements Listener {

    @EventHandler
    public void onHitArrow(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if(!projectile.getType().equals(EntityType.ARROW)) {
            return;
        }
        if (!(projectile.getWorld().getName().equals("kitpvp")
                || projectile.getWorld().getName().equals("kitpvp2"))) {
            return;
        }
        projectile.remove();
    }
}
