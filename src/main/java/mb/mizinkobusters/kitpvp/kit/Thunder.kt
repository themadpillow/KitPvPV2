package mb.mizinkobusters.kitpvp.kit

import java.util.Random
import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.inventory.ItemStack

class Thunder : Listener {
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
        if (KitPvPUtils.getKit(killer) != "Thunder") {
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
        if (event.damager.type != EntityType.PLAYER) {
            return
        }
        val damager = event.damager as Player
        if (KitPvPUtils.getKit(damager) != "Thunder") {
            return
        }
        val r = Random()
        val i = r.nextInt(6)
        if (i == 0) {
            damagee.world.strikeLightningEffect(damagee.location)
            damagee.damage(event.damage * 1.2, damager)
            damager.sendMessage("§7Skill Trigger: §eThunder")
        }
    }
}