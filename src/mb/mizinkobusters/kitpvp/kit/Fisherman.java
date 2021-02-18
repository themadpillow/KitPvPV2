package mb.mizinkobusters.kitpvp.kit;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils;
import mb.mizinkobusters.kitpvp.utils.PlayerSalvationUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Fisherman implements Listener {

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
        if (!KitPvPUtils.getKit(killer).equals("Fisherman")) {
            return;
        }
        PlayerSalvationUtils.heal(player);
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        Player player = event.getPlayer();
        if (!KitPvPUtils.isInWorld(player)) {
            return;
        }
        if (!KitPvPUtils.getKit(player).equals("Fisherman")) {
            return;
        }

        if (event.getState().equals(PlayerFishEvent.State.CAUGHT_ENTITY)
                && event.getCaught().getType().equals(EntityType.PLAYER)) {
            Player caught = (Player) event.getCaught();
            World world = Bukkit.getWorld(player.getWorld().getName());
            double x = (player.getLocation().getX() + caught.getLocation().getX()) / 2;
            double y = (player.getLocation().getY() + caught.getLocation().getY()) / 2;
            double z = (player.getLocation().getZ() + caught.getLocation().getZ()) / 2;
            caught.teleport(new Location(world, x, y, z, caught.getLocation().getYaw(), caught.getLocation().getPitch()));

            player.playSound(player.getLocation(), Sound.BLOCK_COMPARATOR_CLICK, 1, 0);
            player.sendMessage("§7Skill Trigger: §bFisherman");
        }
    }
}
