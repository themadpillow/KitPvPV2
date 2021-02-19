package mb.mizinkobusters.kitpvp.kit

import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object Standard : BaseKit {
    override fun onEatGapple(event: PlayerItemConsumeEvent) {
        super.onEatGapple(event)

        val player = event.player
        if (player.hasPotionEffect(PotionEffectType.SPEED)) {
            player.removePotionEffect(PotionEffectType.SPEED)
        }
        player.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 600, 0))
        player.sendMessage("§7Skill Trigger: §6Standard")
    }
}