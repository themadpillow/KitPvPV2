package mb.mizinkobusters.kitpvp.kit

import kotlin.math.abs
import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Arrow
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.inventory.ItemStack

class Sniper : Listener {
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
        if (KitPvPUtils.getKit(killer) != "Sniper") {
            return
        }
        killer!!.inventory.addItem(ItemStack(Material.GOLDEN_APPLE))
        killer.health = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
    }

    @EventHandler
    fun onDamage(event: EntityDamageByEntityEvent) {
        if (event.entity.type != EntityType.PLAYER) {
            return
        }
        val damagee = event.entity as Player
        if (!KitPvPUtils.isInWorld(damagee)) {
            return
        }
        if (event.damager.type != EntityType.ARROW) {
            return
        }
        val arrow = event.damager as Arrow
        val shooter = arrow.shooter as Player?
        if (KitPvPUtils.getKit(shooter) != "Sniper") {
            return
        }
        val distance = shooter!!.location.distance(damagee.location)
        val damage = event.damage
        if (abs(distance) < 10) {
            if (damage <= 2) {
                event.damage = 0.0
                shooter.sendMessage("§7Skill Trigger: §aSniper(-2)")
            } else {
                event.damage = damage - 2
                shooter.sendMessage("§7Skill Trigger: §aSniper(-2)")
            }
        } else if (10 <= abs(distance) && abs(distance) < 15) {
            shooter.sendMessage("§7Skill Trigger: §aSniper(+0)")
        } else if (15 <= abs(distance) && abs(distance) < 30) {
            event.damage = damage + 1
            shooter.sendMessage("§7Skill Trigger: §aSniper(+1)")
        } else if (30 <= abs(distance)) {
            event.damage = damage + 2
            shooter.sendMessage("§7Skill Trigger: §aSniper(+2)")
        }
    }
}