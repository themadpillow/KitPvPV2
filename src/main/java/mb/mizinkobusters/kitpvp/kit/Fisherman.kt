package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.inventory.ItemStack

object Fisherman : BaseKit {
    override val kitTypeInfo = "§dTechnical"
    override val isBillingKit = true
    override val lore = listOf(
        "§f頭: 革",
        "§f胴: 鉄",
        "§f腰: チェーン",
        "§f脚: 革",
        "§f武器: 鉄の剣",
        "§f補助: 釣り竿×1",
        "§f補助: 金のリンゴ×1",
        "§f特殊: 釣り竿で相手を引っ掛けると少し引き寄せられる"
    )

    override val displayItemStack = ItemStack(Material.RAW_FISH)

    override val helmet = ItemStack(Material.LEATHER_HELMET)
    override val chestplate = ItemStack(Material.IRON_CHESTPLATE)
    override val leggings = ItemStack(Material.CHAINMAIL_LEGGINGS)
    override val boots = ItemStack(Material.LEATHER_BOOTS)
    override val weapons = listOf(
        ItemStack(Material.IRON_SWORD),
        ItemStack(Material.FISHING_ROD),
        ItemStack(Material.GOLDEN_APPLE)
    )

    override fun onFish(event: PlayerFishEvent) {
        super.onFish(event)

        val player = event.player
        if (event.state != PlayerFishEvent.State.CAUGHT_ENTITY) return

        val caught = event.caught
        if (caught !is Player) return

        val world = player.world
        val x = (player.location.x + caught.location.x) / 2
        val y = (player.location.y + caught.location.y) / 2
        val z = (player.location.z + caught.location.z) / 2
        caught.teleport(Location(world, x, y, z, caught.location.yaw, caught.location.pitch))
        player.playSound(player.location, Sound.BLOCK_COMPARATOR_CLICK, 1f, 0f)
        player.sendMessage("§7Skill Trigger: §bFisherman")
    }
}