package mb.mizinkobusters.kitpvp.kit

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import mb.mizinkobusters.kitpvp.utils.PlayerSalvationUtils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerRespawnEvent

class Fisherman : Listener {
    @EventHandler
    fun onKill(event: PlayerRespawnEvent) {
        val player = event.player
        if (player.killer == null) {
            return
        }
        val killer = player.killer
        if (!KitPvPUtils.isInWorld(killer)) {
            return
        }
        if (KitPvPUtils.getKit(killer) != "Fisherman") {
            return
        }
        PlayerSalvationUtils.heal(player)
    }

    @EventHandler
    fun onFish(event: PlayerFishEvent) {
        val player = event.player
        if (!KitPvPUtils.isInWorld(player)) {
            return
        }
        if (KitPvPUtils.getKit(player) != "Fisherman") {
            return
        }
        if (event.state == PlayerFishEvent.State.CAUGHT_ENTITY && event.caught!!.type == EntityType.PLAYER) {
            val caught = event.caught as Player?
            val world = Bukkit.getWorld(player.world.name)
            val x = (player.location.x + caught!!.location.x) / 2
            val y = (player.location.y + caught.location.y) / 2
            val z = (player.location.z + caught.location.z) / 2
            caught.teleport(Location(world, x, y, z, caught.location.yaw, caught.location.pitch))
            player.playSound(player.location, Sound.BLOCK_COMPARATOR_CLICK, 1f, 0f)
            player.sendMessage("§7Skill Trigger: §bFisherman")
        }
    }
}