package mb.mizinkobusters.kitpvp.other

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory

class ArmorGiver(
    val player: Player,
    private val helmet: Material,
    private val chest: Material,
    private val leg: Material,
    private val boots: Material
) {
    private val pi: PlayerInventory = player.inventory
    fun equip() {
        // TODO エンチャントなどの対応
        pi.helmet = ItemStack(helmet)
        pi.chestplate = ItemStack(chest)
        pi.leggings = ItemStack(leg)
        pi.boots = ItemStack(boots)
    }

}