package mb.mizinkobusters.kitpvp.kit

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.inventory.ItemStack

class PotionHandler : Listener {
    @EventHandler
    fun onKill(event: PlayerRespawnEvent) {
        val player = event.player
        if (player.killer == null) {
            return
        }
        val killer = player.killer
        if (!(killer!!.world.name == "kitpvp" || killer.world.name == "kitpvp2")) {
            return
        }
        if (KitPvPUtils.getKit(killer) != "PotionHandler") {
            return
        }
        killer.sendMessage("§cこのKitではキル時に金のリンゴを獲得できません")
        killer.inventory.addItem(ItemStack(Material.POTION, 1, 16388.toShort()))
        killer.health = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
    }
}