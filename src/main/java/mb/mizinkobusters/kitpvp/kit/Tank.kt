package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object Tank : BaseKit {
    override val kitTypeInfo = "§aGuard"
    override val lore = listOf(
        "§f頭: ダイヤ",
        "§f胴: ダイヤ",
        "§f腰: ダイヤ",
        "§f脚: ダイヤ",
        "§f武器: 木の剣[ダメージ増加I]",
        "§f補助: 金のリンゴ×2",
        "§f特殊: 移動速度低下I"
    )

    override val displayItemStack = ItemStack(Material.ANVIL)
    override val helmet = ItemStack(Material.DIAMOND_HELMET)
    override val chestplate = ItemStack(Material.DIAMOND_CHESTPLATE)
    override val leggings = ItemStack(Material.DIAMOND_LEGGINGS)
    override val boots = ItemStack(Material.DIAMOND_BOOTS)
    override val weapons = listOf(
        ItemStack(Material.WOOD_SWORD).apply {
            addEnchantment(Enchantment.DAMAGE_ALL, 1)
        },
        ItemStack(Material.GOLDEN_APPLE, 2)
    )
    override val effects = listOf(
        PotionEffect(PotionEffectType.SLOW, 1000000, 0, false, false)
    )
}