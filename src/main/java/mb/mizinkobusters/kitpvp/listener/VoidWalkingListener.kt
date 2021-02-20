package mb.mizinkobusters.kitpvp.listener

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

object VoidWalkingListener : Listener {
    @EventHandler
    fun onWalk(event: PlayerMoveEvent) {
        val player = event.player
        if (KitPvPUtils.isInWorld(player)) return
        if (!KitPvPUtils.hasKit(player)) return

        val from = event.from.block
        val to = event.to!!.block
        if (from.location.distance(to.location) < 1) return

        val x = event.to!!.blockX
        val y = event.to!!.blockY - 1
        val z = event.to!!.blockZ
        val block = Bukkit.getWorld(player.world.name)!!
            .getBlockAt(Location(player.world, x.toDouble(), y.toDouble(), z.toDouble()))
        if (block.type != Material.STAINED_GLASS) return
        if (player.lastDamageCause != null
            && player.lastDamageCause!!.entity != null
        ) {
            player.damage(1.0, player.lastDamageCause!!.entity)
        } else {
            player.damage(1.0)
        }

        player.sendTitle("", "§c戦場へ戻るまでダメージを受け続けます", 0, 20, 0)
    }
}