package mb.mizinkobusters.kitpvp.kit;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils;
import mb.mizinkobusters.kitpvp.utils.PlayerSalvationUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.util.Random;

public class Counter implements Listener {

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
        if (!KitPvPUtils.getKit(killer).equals("Counter")) {
            return;
        }
        PlayerSalvationUtils.heal(player);
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
        if (!KitPvPUtils.getKit(damagee).equals("Counter")) {
            return;
        }

        if (!(event.getDamager().getType().equals(EntityType.PLAYER)
                || event.getDamager().getType().equals(EntityType.ARROW))) {
            return;
        }

        Player damager = null;
        if (event.getDamager().getType().equals(EntityType.PLAYER)) {
            damager = (Player) event.getDamager();
        }
        if (event.getDamager().getType().equals(EntityType.ARROW)) {
            Arrow arrow = (Arrow) event.getDamager();
            damager = (Player) arrow.getShooter();
        }

        Random r = new Random();
        int i = r.nextInt(4);
        if (i == 0) {
            damager.damage(event.getDamage() / 2, damagee);
            event.setDamage(event.getDamage() / 2);
            damagee.playSound(damagee.getLocation(), Sound.BLOCK_ANVIL_HIT, 1, 0);
            damagee.sendMessage("§7Skill Trigger: §aCounter");
        }
    }
}
