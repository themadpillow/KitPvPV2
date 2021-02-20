package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object Blizzard : BaseKit {
    override val kitTypeInfo = "§dTarget"
    override val isBillingKit = true
    override val lore = listOf(
        "§f頭: ダイヤ",
        "§f胴: チェーン",
        "§f腰: チェーン",
        "§f脚: ダイヤ",
        "§f武器: ダイヤの剣",
        "§f補助: 金のリンゴ×1",
        "§f特殊: 攻撃時に1/4の確率で相手に移動速度低下IIIの効果を3秒間付与する"
    )

    override val displayItemStack = ItemStack(Material.ICE)

    override val helmet = ItemStack(Material.DIAMOND_HELMET)
    override val chestplate = ItemStack(Material.CHAINMAIL_CHESTPLATE)
    override val leggings = ItemStack(Material.CHAINMAIL_LEGGINGS)
    override val boots = ItemStack(Material.DIAMOND_BOOTS)

    override val weapons = listOf(
        ItemStack(Material.IRON_SWORD),
        ItemStack(Material.GOLDEN_APPLE)
    )

    override fun onDamageByEntity(event: EntityDamageByEntityEvent) {
        super.onDamageByEntity(event)

        val entity = event.entity
        if (entity !is Player) return
        val damager = event.damager
        if (damager !is Player) return

        if ((0..3).random() == 0) {
            entity.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 60, 0, false, false))
            entity.playSound(damager.location, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1f, 0f)
            damager.playSound(damager.location, Sound.BLOCK_GLASS_BREAK, 1f, 0f)
            damager.sendMessage("§7Skill Trigger: §3Blizzard")
        }
    }
}