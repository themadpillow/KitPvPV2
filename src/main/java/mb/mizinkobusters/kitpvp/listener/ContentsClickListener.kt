package mb.mizinkobusters.kitpvp.listener

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType

class ContentsClickListener : Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    fun onClick(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        if (!KitPvPUtils.isInWorld(player)) {
            return
        }
        if (!KitPvPUtils.hasKit(player)) {
            event.isCancelled = true
        }
        event.currentItem ?: return
        if (event.slotType == InventoryType.SlotType.ARMOR) {
            event.isCancelled = true
        }
    }
}