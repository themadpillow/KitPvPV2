package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object Flame : BaseKit {
    override val lore = listOf(
        "§f頭: 鉄",
        "§f胴: 鉄",
        "§f腰: チェーン",
        "§f脚: 鉄",
        "§f武器: 鉄の剣[火属性I]",
        "§f武器: 弓[フレイムI]",
        "§f補助: 矢×10",
        "§f補助: 金のリンゴ×1",
        "§f特殊: 火炎耐性III"
    )

    override val displayItemStack = ItemStack(Material.BLAZE_POWDER)
    override val helmet = ItemStack(Material.IRON_HELMET)
    override val chestplate = ItemStack(Material.IRON_CHESTPLATE)
    override val leggings = ItemStack(Material.CHAINMAIL_LEGGINGS)
    override val boots = ItemStack(Material.IRON_BOOTS)
    override val weapons = listOf(
        ItemStack(Material.IRON_SWORD).apply {
            addEnchantment(Enchantment.FIRE_ASPECT, 1)
        },
        ItemStack(Material.BOW).apply {
            addEnchantment(Enchantment.ARROW_FIRE, 1)
        },
        ItemStack(Material.ARROW, 10),
        ItemStack(Material.GOLDEN_APPLE)
    )
    override val effects = listOf(
        PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1000000, 2, false, false)
    )
}