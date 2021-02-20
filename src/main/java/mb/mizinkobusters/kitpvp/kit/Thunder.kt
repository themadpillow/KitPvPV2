package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack

object Thunder : BaseKit {
    override val kitTypeInfo = "§dTarget"
    override val lore = listOf(
        "§f頭: 鉄",
        "§f胴: 金",
        "§f腰: 鉄",
        "§f脚: 金",
        "§f武器: 金の剣[ダメージ増加II]",
        "§f補助: 金のリンゴ×2",
        "§f特殊: 1/7の確率で与ダメージの1.2倍のダメージを与える雷を相手の頭上に落とす"
    )
    override val displayItemStack = ItemStack(Material.DOUBLE_PLANT)
    override val helmet = ItemStack(Material.IRON_HELMET)
    override val chestplate = ItemStack(Material.GOLD_CHESTPLATE)
    override val leggings = ItemStack(Material.IRON_LEGGINGS)
    override val boots = ItemStack(Material.GOLD_BOOTS)
    override val weapons = listOf(
        ItemStack(Material.GOLD_SWORD).apply {
            addEnchantment(Enchantment.DAMAGE_ALL, 2)
        },
        ItemStack(Material.GOLDEN_APPLE, 2)
    )

    override fun onDamageByEntity(event: EntityDamageByEntityEvent) {
        super.onDamageByEntity(event)

        val entity = event.entity
        if (entity !is Player) return

        val damager = event.damager

        if ((0..6).random() == 0) {
            entity.world.strikeLightningEffect(entity.location)
            entity.damage(event.damage * 1.2, damager)
            entity.sendMessage("§7Skill Trigger: §eThunder")
        }
    }
}