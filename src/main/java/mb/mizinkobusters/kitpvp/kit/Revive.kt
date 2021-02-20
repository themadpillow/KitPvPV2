package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionData
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.potion.PotionType

object Revive : BaseKit {
    override val kitTypeInfo = "§5Catastrophy"
    override val isBillingKit = true
    override val lore = listOf(
        "§f頭: 鉄",
        "§f胴: 鉄",
        "§f腰: ダイヤ",
        "§f脚: ダイヤ",
        "§f武器: 鉄の剣",
        "§f補助: なし",
        "§f特殊: キルをすると再生能力IIが60秒間付与される",
        "§c特殊: キル時に金のリンゴを獲得できない",
        "§c特殊: キル時にHPが全快しない"
    )
    override val displayItemStack = ItemStack(Material.SPLASH_POTION).apply {
        itemMeta = (itemMeta as PotionMeta).apply {
            basePotionData = PotionData(PotionType.REGEN)
        }
    }
    override val helmet = ItemStack(Material.IRON_HELMET)
    override val chestplate = ItemStack(Material.IRON_CHESTPLATE)
    override val leggings = ItemStack(Material.DIAMOND_LEGGINGS)
    override val boots = ItemStack(Material.DIAMOND_BOOTS)
    override val weapons = listOf(
        ItemStack(Material.IRON_SWORD)
    )
    override val isHealOnKill = false

    override fun onKill(event: PlayerDeathEvent) {
        super.onKill(event)

        val player = event.entity
        val killer = player.killer
        killer!!.sendMessage("§cこのKitではキル時に金のリンゴを獲得できません")
        killer.sendMessage("§cこのKitではキル時にHPが全快しません")
        killer.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 1200, 1))
        killer.sendMessage("§7Skill Trigger: §6Revive")
        killer.health = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
    }
}