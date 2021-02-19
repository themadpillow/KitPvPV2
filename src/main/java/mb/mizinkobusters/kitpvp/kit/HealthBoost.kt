package mb.mizinkobusters.kitpvp.kit

import org.bukkit.attribute.Attribute
import org.bukkit.event.entity.PlayerDeathEvent

object HealthBoost : BaseKit {
    override val isHealOnKill = false

    override fun onKill(event: PlayerDeathEvent) {
        super.onKill(event)

        val killer = event.entity.killer
        killer!!.sendMessage("§cこのKitではキル時に金のリンゴを獲得できません")
        killer.health = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
        if (killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value < 26.0) {
            killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue =
                killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value + 1.0
            killer.sendMessage("§7Skill Trigger: §eHealthBoost")
        }
    }
}