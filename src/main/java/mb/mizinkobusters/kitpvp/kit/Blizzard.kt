package mb.mizinkobusters.kitpvp.kit

import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object Blizzard : BaseKit {
    override fun onDamageByEntity(event: EntityDamageByEntityEvent) {
        super.onDamageByEntity(event)

        val entity = event.entity
        if (entity !is Player) return
        val damager = event.damager
        if (damager !is Player) return

        if ((0..3).random() == 0) {
            entity.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 60, 0, false, false))
            entity.playSound(damager.location, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1f, 0f)
            damager.playSound(damager.location, Sound.BLOCK_GLASS_BREAK, 1f, 0f)
            damager.sendMessage("§7Skill Trigger: §3Blizzard")
        }
    }
}