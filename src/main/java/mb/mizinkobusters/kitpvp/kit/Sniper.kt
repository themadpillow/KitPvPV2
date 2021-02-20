package mb.mizinkobusters.kitpvp.kit

import kotlin.math.abs
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Arrow
import org.bukkit.entity.Damageable
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack

object Sniper : BaseKit {
    override val kitTypeInfo = "§dTechnical"
    override val isBillingKit = true
    override val lore = listOf(
        "§f頭: チェーン",
        "§f胴: チェーン",
        "§f腰: チェーン",
        "§f脚: チェーン",
        "§f武器: 弓[射撃ダメージ増加I, 無限I]",
        "§f補助: 矢×1",
        "§f補助: 金のリンゴ×1",
        "§f特殊: 矢を当てた相手との距離に応じて与ダメージが変動する",
        "§f特殊: 10m未満: 与ダメージ-2",
        "§f特殊: 10m以上15m未満: 与ダメージ+0",
        "§f特殊: 15m以上30m未満: 与ダメージ+1",
        "§f特殊: 30m以上: 与ダメージ+2"
    )
    override val displayItemStack = ItemStack(Material.LONG_GRASS).apply {
        (itemMeta as Damageable).damage(1.0)
    }

    override val helmet = ItemStack(Material.CHAINMAIL_HELMET)
    override val chestplate = ItemStack(Material.CHAINMAIL_CHESTPLATE)
    override val leggings = ItemStack(Material.CHAINMAIL_LEGGINGS)
    override val boots = ItemStack(Material.CHAINMAIL_BOOTS)
    override val weapons = listOf(
        ItemStack(Material.BOW).apply {
            addEnchantment(Enchantment.ARROW_DAMAGE, 1)
            addEnchantment(Enchantment.ARROW_INFINITE, 1)
        },
        ItemStack(Material.ARROW),
        ItemStack(Material.GOLDEN_APPLE)
    )

    override fun onDamageByEntity(event: EntityDamageByEntityEvent) {
        super.onDamageByEntity(event)

        val arrow = event.damager
        if (arrow !is Arrow) return

        val shooter = arrow.shooter
        if (shooter !is Player) return

        val distance = shooter.location.distance(event.entity.location)
        val damage = event.damage
        if (abs(distance) < 10) {
            if (damage <= 2) {
                event.damage = 0.0
                shooter.sendMessage("§7Skill Trigger: §aSniper(-2)")
            } else {
                event.damage = damage - 2
                shooter.sendMessage("§7Skill Trigger: §aSniper(-2)")
            }
        } else if (10 <= abs(distance) && abs(distance) < 15) {
            shooter.sendMessage("§7Skill Trigger: §aSniper(+0)")
        } else if (15 <= abs(distance) && abs(distance) < 30) {
            event.damage = damage + 1
            shooter.sendMessage("§7Skill Trigger: §aSniper(+1)")
        } else if (30 <= abs(distance)) {
            event.damage = damage + 2
            shooter.sendMessage("§7Skill Trigger: §aSniper(+2)")
        }
    }
}