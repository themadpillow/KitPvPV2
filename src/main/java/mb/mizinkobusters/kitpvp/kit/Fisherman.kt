package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerFishEvent

object Fisherman : BaseKit {
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