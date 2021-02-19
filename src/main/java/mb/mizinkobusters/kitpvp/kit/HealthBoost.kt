package mb.mizinkobusters.kitpvp.kit

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import org.bukkit.attribute.Attribute
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRespawnEvent

class HealthBoost : Listener {
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
        if (KitPvPUtils.getKit(killer) != "HealthBoost") {
            return
        }
        killer!!.sendMessage("§cこのKitではキル時に金のリンゴを獲得できません")
        killer.health = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
        if (killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value < 26.0) {
            killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue =
                killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value + 1.0
            killer.sendMessage("§7Skill Trigger: §eHealthBoost")
        }
    }
}