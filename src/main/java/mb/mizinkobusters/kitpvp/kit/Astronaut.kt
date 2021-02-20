package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Material
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDamageEvent.DamageCause
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object Astronaut : BaseKit {
    override val displayItemStack = ItemStack(Material.GLASS)
    override val lore = listOf(
        "§f頭: ガラス",
        "§f胴: 鉄",
        "§f腰: 鉄",
        "§f脚: 鉄",
        "§f武器: 鉄の剣",
        "§f補助: 金のリンゴ×2",
        "§f特殊: 跳躍力上昇III",
        "§f特殊: 移動速度低下II",
        "§f特殊: 落下ダメージ無効"
    )

    override val helmet = ItemStack(Material.GLASS)
    override val chestplate = ItemStack(Material.IRON_CHESTPLATE)
    override val leggings = ItemStack(Material.IRON_LEGGINGS)
    override val boots = ItemStack(Material.IRON_BOOTS)

    override val weapons = listOf(
        ItemStack(Material.IRON_SWORD),
        ItemStack(Material.GOLDEN_APPLE, 2),
    )

    override val effects: List<PotionEffect> = listOf(
        PotionEffect(PotionEffectType.JUMP, 1000000, 2, false, false),
        PotionEffect(PotionEffectType.SLOW, 1000000, 1, false, false)
    )

    override fun onDamage(event: EntityDamageEvent) {
        super.onDamage(event)

        if (event.cause == DamageCause.FALL) {
            event.isCancelled = true
        }
    } // TODO ダブルジャンプの実装
}