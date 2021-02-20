package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object Comet : BaseKit {
    override val lore = listOf(
        "§f頭: 革",
        "§f胴: 革",
        "§f腰: 革",
        "§f脚: ダイヤ",
        "§f武器: ダイヤの剣",
        "§f補助: 金のリンゴ×2",
        "§f特殊: 移動速度上昇III"
    )
    override val displayItemStack = ItemStack(Material.DIAMOND_BOOTS)
    override val helmet = ItemStack(Material.LEATHER_HELMET).apply {
        addEnchantment(Enchantment.DURABILITY, 1)
        itemMeta = (itemMeta as LeatherArmorMeta).apply {
            color = Color.AQUA
        }
    }
    override val chestplate = ItemStack(Material.LEATHER_CHESTPLATE).apply {
        addEnchantment(Enchantment.DURABILITY, 1)
        itemMeta = (itemMeta as LeatherArmorMeta).apply {
            color = Color.AQUA
        }
    }

    override val leggings = ItemStack(Material.LEATHER_LEGGINGS).apply {
        helmet.addEnchantment(Enchantment.DURABILITY, 1)
        itemMeta = (itemMeta as LeatherArmorMeta).apply {
            color = Color.AQUA
        }
    }
    override val boots = ItemStack(Material.DIAMOND_BOOTS).apply {
        addEnchantment(Enchantment.DURABILITY, 1)
    }

    override val weapons = listOf(
        ItemStack(Material.DIAMOND_SWORD),
        ItemStack(Material.GOLDEN_APPLE, 2)
    )
    override val effects = listOf(
        PotionEffect(PotionEffectType.SPEED, 1000000, 2, false, false)
    )
}