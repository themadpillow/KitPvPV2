package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionData
import org.bukkit.potion.PotionType

object PotionHandler : BaseKit {
    override val kitTypeInfo = "§dManiac"
    override val lore = listOf(
        "§f頭: チェーン",
        "§f胴: チェーン",
        "§f腰: チェーン",
        "§f脚: チェーン",
        "§f武器: 鉄の剣",
        "§f補助: 治癒のスプラッシュポーション×2",
        "§f補助: 負傷のスプラッシュポーション×2",
        "§f補助: 毒のスプラッシュポーション×2",
        "§f特殊: キル時に治癒のスプラッシュポーションを獲得する",
        "§c特殊: キル時に金のリンゴを獲得できない"
    )

    override val displayItemStack = ItemStack(Material.SPLASH_POTION).apply {
        itemMeta = (itemMeta as PotionMeta).apply {
            basePotionData = PotionData(PotionType.INSTANT_HEAL)
        }
    }
    override val helmet = ItemStack(Material.CHAINMAIL_HELMET)
    override val chestplate = ItemStack(Material.CHAINMAIL_CHESTPLATE)
    override val leggings = ItemStack(Material.CHAINMAIL_LEGGINGS)
    override val boots = ItemStack(Material.CHAINMAIL_BOOTS)
    override val weapons = listOf(
        ItemStack(Material.IRON_SWORD),
        ItemStack(Material.POTION, 2, 16389.toShort()),
        ItemStack(Material.POTION, 2, 16396.toShort()),
        ItemStack(Material.POTION, 2, 16388.toShort())
    )
    override val isHealOnKill = false

    override fun onKill(event: PlayerDeathEvent) {
        super.onKill(event)

        val killer = event.entity.killer

        killer.sendMessage("§cこのKitではキル時に金のリンゴを獲得できません")
        killer.inventory.addItem(ItemStack(Material.POTION, 1, 16388.toShort()))
        killer.health = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
    }
}