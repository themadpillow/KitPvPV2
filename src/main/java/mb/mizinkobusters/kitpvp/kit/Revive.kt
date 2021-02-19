package mb.mizinkobusters.kitpvp.kit

import org.bukkit.attribute.Attribute
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object Revive : BaseKit {
    override val isHealOnKill = false

    override fun onKill(event: PlayerDeathEvent) {
        super.onKill(event)

        val player = event.entity
        val killer = player.killer
        killer!!.sendMessage("§cこのKitではキル時に金のリンゴを獲得できません")
        killer.sendMessage("§cこのKitではキル時にHPが全快しません")
        killer.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION, 1200, 1))
        killer.sendMessage("§7Skill Trigger: §6Revive")
        killer.health = killer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.value
    }
}