package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object Fighter : BaseKit {
    override val lore = listOf(
        "§f頭: 鉄",
        "§f胴: 鉄",
        "§f腰: チェーン",
        "§f脚: 鉄",
        "§f武器: 鉄の剣",
        "§f補助: 釣り竿×1",
        "§f補助: 金のリンゴ×1"
    )
    override val displayItemStack = ItemStack(Material.FISHING_ROD)

    override val helmet = ItemStack(Material.IRON_HELMET)
    override val chestplate = ItemStack(Material.IRON_CHESTPLATE)
    override val leggings = ItemStack(Material.CHAINMAIL_LEGGINGS)
    override val boots = ItemStack(Material.IRON_BOOTS)
    override val weapons = listOf(
        ItemStack(Material.IRON_SWORD),
        ItemStack(Material.FISHING_ROD),
        ItemStack(Material.GOLDEN_APPLE)
    )
}