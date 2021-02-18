package mb.mizinkobusters.kitpvp.listener;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FallToSpongeListener implements Listener {

    private String prefix = "§f[§dKitPvP§f] ";

    @EventHandler
    public void onFallToSponge(EntityDamageEvent event) {
        Player player = (Player) event.getEntity();
        if (!KitPvPUtils.isInWorld(player)) {
            return;
        }
        if (!event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            return;
        }

        Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
        if (!block.getType().equals(Material.SPONGE)) {
            return;
        }

        if (!KitPvPUtils.hasKit(player)) {
            player.sendMessage(prefix + "§cKitを選択してください");
            player.teleport(new Location(player.getWorld(), 0.5, 11.0, 0.5, 0, 0), PlayerTeleportEvent.TeleportCause.PLUGIN);
        } else {
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0, false, false));
        }

        event.setCancelled(true);
    }
}
