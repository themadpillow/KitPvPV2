package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.inventory.ItemStack

object HealthBoost : BaseKit {
    override val kitTypeInfo = "§5Hard"
    override val isBillingKit = true
    override val lore = listOf(
        "§f頭: 鉄",
        "§f胴: 鉄",
        "§f腰: ダイヤ",
        "§f脚: 鉄",
        "§f武器: 鉄の剣",
        "§f補助: なし",
        "§f特殊: 初期HPが18になり,",
        "§fキルをするとHPが1増える(最大26)",
        "§c特殊: キル時に金のリンゴを獲得できない"
    )
    override val displayItemStack = ItemStack(Material.GOLDEN_APPLE)

    override val helmet = ItemStack(Material.IRON_HELMET)
    override val chestplate = ItemStack(Material.IRON_CHESTPLATE)
    override val leggings = ItemStack(Material.DIAMOND_LEGGINGS)
    override val boots = ItemStack(Material.IRON_BOOTS)
    override val weapons = listOf(
        ItemStack(Material.IRON_SWORD)
    )

    override val isHealOnKill = false

    override fun onKill(event: PlayerDeathEvent) {
        super.onKill(event)

        val killer = event.entity.killer
        killer!!.sendMessage("§cこのKitではキル時に金のリンゴを獲得できません")
        killer.health = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
        if (killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value < 26.0) {
            killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue =
                killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value + 1.0
            killer.sendMessage("§7Skill Trigger: §eHealthBoost")
        }
    }

    override fun onPassive(player: Player) {
        super.onPassive(player)

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = 18.0
    }
}