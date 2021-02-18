package mb.mizinkobusters.kitpvp.kit;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class Sniper implements Listener {

    @EventHandler
    public void onKill(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (player.getKiller() == null) {
            return;
        }

        Player killer = player.getKiller();
        if (!KitPvPUtils.isInWorld(killer)) {
            return;
        }
        if (!KitPvPUtils.getKit(killer).equals("Sniper")) {
            return;
        }
        killer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
        killer.setHealth(killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!event.getEntity().getType().equals(EntityType.PLAYER)) {
            return;
        }

        Player damagee = (Player) event.getEntity();
        if (!KitPvPUtils.isInWorld(damagee)) {
            return;
        }
        if (!event.getDamager().getType().equals(EntityType.ARROW)) {
            return;
        }

        Arrow arrow = (Arrow) event.getDamager();
        Player shooter = (Player) arrow.getShooter();

        if (!KitPvPUtils.getKit(shooter).equals("Sniper")) {
            return;
        }

        double distance = shooter.getLocation().distance(damagee.getLocation());
        double damage = event.getDamage();
        if (Math.abs(distance) < 10) {
            if (damage <= 2) {
                event.setDamage(0);
                shooter.sendMessage("§7Skill Trigger: §aSniper(-2)");
            } else {
                event.setDamage(damage - 2);
                shooter.sendMessage("§7Skill Trigger: §aSniper(-2)");
            }
        } else if (10 <= Math.abs(distance) && Math.abs(distance) < 15) {
            shooter.sendMessage("§7Skill Trigger: §aSniper(+0)");
        } else if (15 <= Math.abs(distance) && Math.abs(distance) < 30) {
            event.setDamage(damage + 1);
            shooter.sendMessage("§7Skill Trigger: §aSniper(+1)");
        } else if (30 <= Math.abs(distance)) {
            event.setDamage(damage + 2);
            shooter.sendMessage("§7Skill Trigger: §aSniper(+2)");
        }
    }
}
