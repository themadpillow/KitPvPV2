package mb.mizinkobusters.kitpvp.kit;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils;
import mb.mizinkobusters.kitpvp.utils.PlayerSalvationUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class Flame implements Listener {

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
        if (!KitPvPUtils.getKit(killer).equals("Flame")) {
            return;
        }
        PlayerSalvationUtils.heal(player);
    }
}
