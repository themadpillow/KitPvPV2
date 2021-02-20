package mb.mizinkobusters.kitpvp.utils

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


fun Player.equip(
    helmet: ItemStack = ItemStack(Material.AIR),
    chestplate: ItemStack = ItemStack(Material.AIR),
    leggings: ItemStack = ItemStack(Material.AIR),
    boots: ItemStack = ItemStack(Material.AIR)
) {
    // TODO エンチャントなどの対応
    val pi = player.inventory
    pi.helmet = helmet
    pi.chestplate = chestplate
    pi.leggings = leggings
    pi.boots = boots
}

fun Player.equip(
    helmet: Material = Material.AIR,
    chestplate: Material = Material.AIR,
    leggings: Material = Material.AIR,
    boots: Material = Material.AIR
) {
    this.equip(
        helmet = ItemStack(helmet),
        chestplate = ItemStack(chestplate),
        leggings = ItemStack(leggings),
        boots = ItemStack(boots)
    )
}

fun Player.clearEquip() {
    this.equip(Material.AIR, Material.AIR, Material.AIR, Material.AIR)
}