package mb.mizinkobusters.kitpvp.kit

import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent

object Thunder : BaseKit {
    override fun onDamageByEntity(event: EntityDamageByEntityEvent) {
        super.onDamageByEntity(event)

        val entity = event.entity
        if (entity !is Player) return

        val damager = event.damager

        if ((0..6).random() == 0) {
            entity.world.strikeLightningEffect(entity.location)
            entity.damage(event.damage * 1.2, damager)
            entity.sendMessage("§7Skill Trigger: §eThunder")
        }
    }
}