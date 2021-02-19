package mb.mizinkobusters.kitpvp.kit

import org.bukkit.attribute.Attribute
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object Berserker : BaseKit {
    override val isHealOnKill = false

    override fun onKill(event: PlayerDeathEvent) {
        super.onKill(event)

        val player = event.entity
        val killer = player.killer
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