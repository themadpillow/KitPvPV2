package mb.mizinkobusters.kitpvp.kit;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class PotionHandler implements Listener {

    @EventHandler
    public void onKill(PlayerRespawnEvent event) {

        Player player = event.getPlayer();

        if (player.getKiller() == null) {
            return;
        }

        Player killer = player.getKiller();

        if (!(killer.getWorld().getName().equals("kitpvp")
                || killer.getWorld().getName().equals("kitpvp2"))) {
            return;
        }

        if (!KitPvPUtils.getKit(killer).equals("PotionHandler")) {
            return;
        }
        killer.sendMessage("§cこのKitではキル時に金のリンゴを獲得できません");
        killer.getInventory().addItem(new ItemStack(Material.POTION, 1, (short) 16388));
        killer.setHealth(killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    }
}
