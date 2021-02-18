package mb.mizinkobusters.kitpvp.kit;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils;
import mb.mizinkobusters.kitpvp.utils.PlayerSalvationUtils;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Blizzard implements Listener {

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
        if (!KitPvPUtils.getKit(killer).equals("Blizzard")) {
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

        if (!event.getDamager().getType().equals(EntityType.PLAYER)) {
            return;
        }
        Player damager = (Player) event.getDamager();
        if (!KitPvPUtils.getKit(damager).equals("Blizzard")) {
            return;
        }

        Random r = new Random();
        int i = r.nextInt(3);
        if (i == 0) {
            damagee.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60, 0, false, false));
            damagee.playSound(damagee.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1, 0);
            damager.playSound(damager.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 0);
            damager.sendMessage("§7Skill Trigger: §3Blizzard");
        }
    }
}
