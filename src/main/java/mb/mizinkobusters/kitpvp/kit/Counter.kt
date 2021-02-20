package mb.mizinkobusters.kitpvp.kit

import java.util.Random
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack

object Counter : BaseKit {
    override val kitTypeInfo = "§aGuard"
    override val isBillingKit = true
    override val lore = listOf(
        "§f頭: 鉄",
        "§f胴: ダイヤ",
        "§f腰: チェーン",
        "§f脚: 鉄",
        "§f武器: 石の剣",
        "§f補助: 金のリンゴ×1",
        "§f特殊: 1/5の確率で被ダメージを半減し,",
        "§f被ダメージを相手に反射する"
    )
    override val displayItemStack = ItemStack(Material.DIAMOND_CHESTPLATE)

    override val helmet = ItemStack(Material.IRON_HELMET)
    override val chestplate = ItemStack(Material.DIAMOND_CHESTPLATE)
    override val leggings = ItemStack(Material.CHAINMAIL_LEGGINGS)
    override val boots = ItemStack(Material.IRON_BOOTS)

    override val weapons = listOf(
        ItemStack(Material.STONE_SWORD),
        ItemStack(Material.GOLDEN_APPLE)
    )

    override fun onDamageByEntity(event: EntityDamageByEntityEvent) {
        super.onDamageByEntity(event)

        val entity = event.entity
        if (entity !is Player) return

        val damager = event.damager
        if (damager !is Arrow) return
        if (damager.shooter !is Player) return

        val r = Random()
        val i = r.nextInt(4)
        if (i == 0) {
            (damager.shooter as Player).damage(event.damage / 2, entity)
            event.damage = event.damage / 2
            entity.playSound(entity.location, Sound.BLOCK_ANVIL_HIT, 1f, 0f)
            entity.sendMessage("§7Skill Trigger: §aCounter")
        }
    }
}