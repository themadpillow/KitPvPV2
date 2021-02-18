package mb.mizinkobusters.kitpvp.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class KitPurchaseMenu implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (!event.hasItem()) {
            return;
        }

        ItemStack item = event.getItem();
        if (!item.hasItemMeta()) {
            return;
        }
        if (!item.getItemMeta().hasDisplayName()) {
            return;
        }

        Player player = event.getPlayer();
        if (item.getItemMeta().getDisplayName().equals("§e§lKitを購入する")) {
            player.sendMessage("§eベータ期間中はKitを購入することなく, すべてのKitを選択できます");
        }

    }

}
