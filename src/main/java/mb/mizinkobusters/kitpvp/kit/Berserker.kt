package mb.mizinkobusters.kitpvp.kit

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import org.bukkit.attribute.Attribute
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Berserker : Listener {
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
        if (KitPvPUtils.getKit(killer) != "Berserker") {
            return
        }
        if (player.hasPotionEffect(PotionEffectType.WEAKNESS)) {
            player.removePotionEffect(PotionEffectType.WEAKNESS)
        }
        killer!!.sendMessage("§cこのKitではキル時に金のリンゴを獲得できません")
        killer.health = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
        killer.addPotionEffect(PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 0, false, false))
        killer.addPotionEffect(PotionEffect(PotionEffectType.ABSORPTION, 600, 0, false, false))
        killer.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 600, 0, false, false))
        killer.sendMessage("§7Skill Trigger: §4Berserker")
    }
}