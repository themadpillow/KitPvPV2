package mb.mizinkobusters.kitpvp.kit

import java.util.Random
import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import mb.mizinkobusters.kitpvp.utils.PlayerSalvationUtils
import org.bukkit.Sound
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Blizzard : Listener {
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
        if (KitPvPUtils.getKit(killer) != "Blizzard") {
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
        if (event.damager.type != EntityType.PLAYER) {
            return
        }
        val damager = event.damager as Player
        if (KitPvPUtils.getKit(damager) != "Blizzard") {
            return
        }
        val r = Random()
        val i = r.nextInt(3)
        if (i == 0) {
            damagee.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 60, 0, false, false))
            damagee.playSound(damagee.location, Sound.ENTITY_ZOMBIE_ATTACK_DOOR_WOOD, 1f, 0f)
            damager.playSound(damager.location, Sound.BLOCK_GLASS_BREAK, 1f, 0f)
            damager.sendMessage("§7Skill Trigger: §3Blizzard")
        }
    }
}