package mb.mizinkobusters.kitpvp.listener

import mb.mizinkobusters.kitpvp.gui.KitMenu
import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import mb.mizinkobusters.kitpvp.utils.clearEquip
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerInteractEvent

object InventoryListener : Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    fun optimizeInventory(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        val item = event.currentItem ?: return
        val meta = item.itemMeta ?: return
        if (!meta.hasDisplayName()) return
        if (meta.displayName == "§6§lKitを選択する") return
        if (meta.displayName.endsWith("を選択する")) {
            player.inventory.clear()
            player.clearEquip()
            player.closeInventory()
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onClick(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        if (!KitPvPUtils.isInWorld(player)) return
        if (!KitPvPUtils.hasKit(player)) event.isCancelled = true
        event.currentItem ?: return
        if (event.slotType == InventoryType.SlotType.ARMOR) event.isCancelled = true
    }

    @EventHandler
    fun onClick(event: PlayerInteractEvent) {
        val itemName = event.item?.itemMeta?.displayName ?: return
        val player = event.player
        when (itemName) {
            "§6§lKitを選択する" -> {
                KitMenu.openInventory(player)
            }
            "§e§lKitを購入する" -> {
                player.sendMessage("§eベータ期間中はKitを購入することなく, すべてのKitを選択できます")
            }
        }
    }
}