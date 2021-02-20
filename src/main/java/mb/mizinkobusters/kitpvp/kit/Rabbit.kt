package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object Rabbit : BaseKit {
    override val lore = listOf(
        "§f頭: 鉄",
        "§f胴: チェーン",
        "§f腰: 鉄",
        "§f脚: 革",
        "§f武器: 鉄の剣",
        "§f補助: 金のリンゴ×1",
        "§f特殊: 跳躍力上昇II",
        "§f特殊: 移動速度上昇II"
    )
    override val displayItemStack = ItemStack(Material.RABBIT_FOOT)

    override val helmet = ItemStack(Material.IRON_HELMET)
    override val chestplate = ItemStack(Material.CHAINMAIL_CHESTPLATE)
    override val leggings = ItemStack(Material.IRON_LEGGINGS)
    override val boots = ItemStack(Material.LEATHER_BOOTS)
    override val weapons = listOf(
        ItemStack(Material.IRON_SWORD),
        ItemStack(Material.GOLDEN_APPLE)
    )
    override val effects = listOf(
        PotionEffect(PotionEffectType.JUMP, 1000000, 1, false, false),
        PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false)
    )
}