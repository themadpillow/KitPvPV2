package mb.mizinkobusters.kitpvp.other;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FieldSender implements Listener {

    private String prefix = "§f[§dKitPvP§f] ";

    @EventHandler
    public void onStepPlate(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!KitPvPUtils.isInWorld(player)) {
            return;
        }
        if (!event.getAction().equals(Action.PHYSICAL)) {
            return;
        }
        if (!KitPvPUtils.hasKit(player)) {
            player.sendMessage(prefix + "§cKitを選択してください");
            return;
        }

        Location loc = event.getClickedBlock().getLocation();

        // 山岳地帯へTP
        if (loc.getBlockX() == 4
                && loc.getBlockY() == 12
                && loc.getBlockZ() == 4) {
            player.teleport(new Location(player.getWorld(), 29.5, 5.0, 25.5), PlayerTeleportEvent.TeleportCause.PLUGIN);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0, false, false));
        }

        // キノコ地帯へTP
        if (loc.getBlockX() == -4
                && loc.getBlockY() == 12
                && loc.getBlockZ() == 4) {
            player.teleport(new Location(player.getWorld(), -39.5, 11.0, 13.5), PlayerTeleportEvent.TeleportCause.PLUGIN);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0, false, false));
        }

        // 氷原地帯へTP
        if (loc.getBlockX() == -4
                && loc.getBlockY() == 12
                && loc.getBlockZ() == -4) {
            player.teleport(new Location(player.getWorld(), -17.5, 7.0, -41.5), PlayerTeleportEvent.TeleportCause.PLUGIN);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0, false, false));
        }

        // 火山地帯へTP
        if (loc.getBlockX() == 4
                && loc.getBlockY() == 12
                && loc.getBlockZ() == -4) {
            player.teleport(new Location(player.getWorld(), 35.5, 5.0, -8.5), PlayerTeleportEvent.TeleportCause.PLUGIN);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0, false, false));
        }
    }
}
