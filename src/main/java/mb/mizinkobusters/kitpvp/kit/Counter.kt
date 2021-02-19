package mb.mizinkobusters.kitpvp.kit

import java.util.Random
import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import mb.mizinkobusters.kitpvp.utils.PlayerSalvationUtils
import org.bukkit.Sound
import org.bukkit.entity.Arrow
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerRespawnEvent

class Counter : Listener {
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
        if (KitPvPUtils.getKit(killer) != "Counter") {
            return
        }
        PlayerSalvationUtils.heal(player)
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
        if (KitPvPUtils.getKit(damagee) != "Counter") {
            return
        }
        if (!(event.damager.type == EntityType.PLAYER || event.damager.type == EntityType.ARROW)) {
            return
        }
        var damager: Player? = null
        if (event.damager.type == EntityType.PLAYER) {
            damager = event.damager as Player
        }
        if (event.damager.type == EntityType.ARROW) {
            val arrow = event.damager as Arrow
            damager = arrow.shooter as Player?
        }
        val r = Random()
        val i = r.nextInt(4)
        if (i == 0) {
            damager!!.damage(event.damage / 2, damagee)
            event.damage = event.damage / 2
            damagee.playSound(damagee.location, Sound.BLOCK_ANVIL_HIT, 1f, 0f)
            damagee.sendMessage("§7Skill Trigger: §aCounter")
        }
    }
}