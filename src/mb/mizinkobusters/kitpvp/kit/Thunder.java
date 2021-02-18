package mb.mizinkobusters.kitpvp.kit;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Thunder implements Listener {

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
		if (!KitPvPUtils.getKit(killer).equals("Thunder")) {
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

		if (!(event.getDamager().getType().equals(EntityType.PLAYER))) {
			return;
		}

		Player damager = (Player) event.getDamager();
		if (!KitPvPUtils.getKit(damager).equals("Thunder")) {
			return;
		}

		Random r = new Random();
		int i = r.nextInt(6);
		if (i == 0) {
			damagee.getWorld().strikeLightningEffect(damagee.getLocation());
			damagee.damage(event.getDamage() * 1.2, damager);
			damager.sendMessage("§7Skill Trigger: §eThunder");
		}
	}
}
