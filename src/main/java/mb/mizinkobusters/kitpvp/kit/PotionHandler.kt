package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.inventory.ItemStack

object PotionHandler : BaseKit {
    override val isHealOnKill = false

    override fun onKill(event: PlayerDeathEvent) {
        super.onKill(event)

        val killer = event.entity.killer

        killer.sendMessage("§cこのKitではキル時に金のリンゴを獲得できません")
        killer.inventory.addItem(ItemStack(Material.POTION, 1, 16388.toShort()))
        killer.health = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
    }
}