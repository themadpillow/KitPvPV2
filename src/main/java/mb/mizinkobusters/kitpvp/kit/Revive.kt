package mb.mizinkobusters.kitpvp.kit

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import org.bukkit.attribute.Attribute
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Revive : Listener {
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
        if (KitPvPUtils.getKit(killer) != "Revive") {
            return
        }
        killer!!.sendMessage("§cこのKitではキル時に金のリンゴを獲得できません")
        killer.sendMessage("§cこのKitではキル時にHPが全快しません")
        killer.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 1200, 1))
        killer.sendMessage("§7Skill Trigger: §6Revive")
        killer.health = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
    }
}