package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

object Boxer : BaseKit {
    override val lore = listOf(
        "§f頭: なし",
        "§f胴: なし",
        "§f腰: 革",
        "§f脚: なし",
        "§f武器: ダイヤの剣[ダメージ増加V]",
        "§f補助: 金のリンゴ×2",
        "§f特殊: なし"
    )

    override val displayItemStack = ItemStack(Material.LEATHER_LEGGINGS)

    override val leggings = ItemStack(Material.LEATHER_LEGGINGS)
    override val weapons = listOf(
        ItemStack(Material.DIAMOND_SWORD).apply {
            addEnchantment(Enchantment.DAMAGE_ALL, 5)
        },
        ItemStack(Material.GOLDEN_APPLE, 2)
    )
}