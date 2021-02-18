package mb.mizinkobusters.kitpvp.listener;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class VoidWalkingListener implements Listener {

    private String prefix = "§f[§dKitPvP§f] ";

    @EventHandler
    public void onWalk(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (KitPvPUtils.isInWorld(player)) {
            return;
        }
        if (!KitPvPUtils.hasKit(player)) {
            return;
        }

        Block from = event.getFrom().getBlock();
        Block to = event.getTo().getBlock();
        if (from.getLocation().distance(to.getLocation()) < 1) {
            return;
        }

        int x = event.getTo().getBlockX();
        int y = event.getTo().getBlockY() - 1;
        int z = event.getTo().getBlockZ();
        Block block = Bukkit.getWorld(player.getWorld().getName()).getBlockAt(new Location(player.getWorld(), x, y, z));
        if (!block.getType().equals(Material.STAINED_GLASS)) {
            return;
        }

        if (player.getLastDamageCause() != null
                && player.getLastDamageCause().getEntity() != null) {
            player.damage(1, player.getLastDamageCause().getEntity());
        } else {
            player.damage(1);
        }

        player.sendTitle("", "§c戦場へ戻るまでダメージを受け続けます", 0, 20, 0);
    }
}
