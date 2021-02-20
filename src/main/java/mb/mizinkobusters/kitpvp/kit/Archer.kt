package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

object Archer : BaseKit {
    override val lore = listOf(
        "§f頭: 革",
        "§f胴: チェーン",
        "§f腰: チェーン",
        "§f脚: 革",
        "§f武器: 弓[無限I]",
        "§f補助: 矢×1",
        "§f補助: 金のリンゴ×1",
        "§f特殊: なし"
    )
    override val displayItemStack = ItemStack(Material.BOW)

    override val helmet = ItemStack(Material.LEATHER_HELMET)
    override val chestplate = ItemStack(Material.CHAINMAIL_CHESTPLATE)
    override val leggings = ItemStack(Material.CHAINMAIL_LEGGINGS)
    override val boots = ItemStack(Material.LEATHER_BOOTS)

    override val weapons = listOf(
        ItemStack(Material.BOW).apply {
            addEnchantment(Enchantment.ARROW_INFINITE, 1)
        },
        ItemStack(Material.ARROW),
        ItemStack(Material.GOLDEN_APPLE)
    )
}