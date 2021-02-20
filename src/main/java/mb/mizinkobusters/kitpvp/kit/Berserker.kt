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

object Berserker : BaseKit {
    override val kitTypeInfo = "§5Absolute"
    override val isBillingKit = true
    override val lore = listOf(
        "§f頭: 鉄",
        "§f胴: 鉄",
        "§f腰: チェーン",
        "§f脚: 鉄",
        "§f武器: 鉄の剣",
        "§f補助: なし",
        "§f特殊: 弱体化I",
        "§f特殊: キルをすると弱体化の効果が消え,",
        "§f攻撃力上昇I, 衝撃吸収I, 移動速度上昇Iの効果が30秒間付与される",
        "§c特殊: キル時に金のリンゴを獲得できない"
    )

    override val displayItemStack = ItemStack(Material.POTION).apply {
        val meta = this.itemMeta as PotionMeta?
        meta!!.basePotionData = PotionData(PotionType.INSTANT_DAMAGE)
        this.itemMeta = meta
    }

    override val helmet = ItemStack(Material.IRON_HELMET)
    override val chestplate = ItemStack(Material.IRON_CHESTPLATE)
    override val leggings = ItemStack(Material.CHAINMAIL_LEGGINGS)
    override val boots = ItemStack(Material.IRON_BOOTS)
    override val weapons = listOf(ItemStack(Material.IRON_SWORD))
    override val effects = listOf(PotionEffect(PotionEffectType.WEAKNESS, 1000000, 0, false, false))

    override val isHealOnKill = false

    override fun onKill(event: PlayerDeathEvent) {
        super.onKill(event)

        val player = event.entity
        val killer = player.killer
        if (player.hasPotionEffect(PotionEffectType.WEAKNESS)) {
            player.removePotionEffect(PotionEffectType.WEAKNESS)
        }
        killer!!.sendMessage("§cこのKitではキル時に金のリンゴを獲得できません")
        killer.health = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
        killer.addPotionEffect(PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 0, false, false))
        killer.addPotionEffect(PotionEffect(PotionEffectType.ABSORPTION, 600, 0, false, false))
        killer.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 600, 0, false, false))
        killer.sendMessage("§7Skill Trigger: §4Berserker")
    }
}