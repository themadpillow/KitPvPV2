package mb.mizinkobusters.kitpvp.kit

import java.util.Random
import org.bukkit.Sound
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent

object Counter : BaseKit {
    override fun onDamageByEntity(event: EntityDamageByEntityEvent) {
        super.onDamageByEntity(event)

        val entity = event.entity
        if (entity !is Player) return

        val damager = event.damager
        if (damager !is Arrow) return
        if (damager.shooter !is Player) return

        val r = Random()
        val i = r.nextInt(4)
        if (i == 0) {
            (damager.shooter as Player).damage(event.damage / 2, entity)
            event.damage = event.damage / 2
            entity.playSound(entity.location, Sound.BLOCK_ANVIL_HIT, 1f, 0f)
            entity.sendMessage("§7Skill Trigger: §aCounter")
        }
    }
}