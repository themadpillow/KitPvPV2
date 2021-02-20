package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Material
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object Standard : BaseKit {
    override val lore = listOf(
        "§f頭: 鉄",
        "§f胴: ダイヤ",
        "§f腰: 鉄",
        "§f脚: 鉄",
        "§f武器: 鉄の剣",
        "§f補助: 金のリンゴ×1",
        "§f特殊: 金のリンゴを食べると30秒間移動速度上昇Iが付与される"
    )

    override val displayItemStack = ItemStack(Material.IRON_SWORD)
    override val helmet = ItemStack(Material.IRON_HELMET)
    override val chestplate = ItemStack(Material.DIAMOND_CHESTPLATE)
    override val leggings = ItemStack(Material.IRON_LEGGINGS)
    override val boots = ItemStack(Material.IRON_BOOTS)
    override val weapons = listOf(
        ItemStack(Material.IRON_SWORD),
        ItemStack(Material.GOLDEN_APPLE)
    )

    override fun onEatGapple(event: PlayerItemConsumeEvent) {
        super.onEatGapple(event)

        val player = event.player
        if (player.hasPotionEffect(PotionEffectType.SPEED)) {
            player.removePotionEffect(PotionEffectType.SPEED)
        }
        player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 600, 0))
        player.sendMessage("§7Skill Trigger: §6Standard")
    }
}