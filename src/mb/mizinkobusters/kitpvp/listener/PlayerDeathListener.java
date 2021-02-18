package mb.mizinkobusters.kitpvp.listener;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils;
import mb.mizinkobusters.kitpvp.utils.PlayerSalvationUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (!KitPvPUtils.isInWorld(player)) {
            return;
        }
        event.setDeathMessage(null);
        PlayerSalvationUtils.salvage(player);
        player.spigot().respawn();
    }
}
