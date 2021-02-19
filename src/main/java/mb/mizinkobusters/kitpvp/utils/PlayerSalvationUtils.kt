package mb.mizinkobusters.kitpvp.utils

import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector

object PlayerSalvationUtils {
    fun salvage(player: Player) {
        player.velocity = Vector()
        player.fireTicks = 0
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = 20.0
        player.activePotionEffects.stream().map { obj: PotionEffect -> obj.type }
            .forEach { type: PotionEffectType? -> player.removePotionEffect(type!!) }
        KitPvPUtils.resetKit(player)
    }

    fun heal(player: Player) {
        player.inventory.addItem(ItemStack(Material.GOLDEN_APPLE))
        player.health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
    }
}