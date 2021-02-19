package mb.mizinkobusters.kitpvp.gui

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class KitPurchaseMenu : Listener {
    @EventHandler
    fun onClick(event: PlayerInteractEvent) {
        if (!event.hasItem()) {
            return
        }
        val item = event.item
        if (!item!!.hasItemMeta()) {
            return
        }
        if (!item.itemMeta!!.hasDisplayName()) {
            return
        }
        val player = event.player
        if (item.itemMeta!!.displayName == "§e§lKitを購入する") {
            player.sendMessage("§eベータ期間中はKitを購入することなく, すべてのKitを選択できます")
        }
    }
}