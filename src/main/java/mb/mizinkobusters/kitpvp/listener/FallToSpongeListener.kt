package mb.mizinkobusters.kitpvp.listener

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDamageEvent.DamageCause
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class FallToSpongeListener : Listener {
    private val prefix = "§f[§dKitPvP§f] "

    @EventHandler
    fun onFallToSponge(event: EntityDamageEvent) {
        val player = event.entity as Player
        if (!KitPvPUtils.isInWorld(player)) {
            return
        }
        if (event.cause != DamageCause.FALL) {
            return
        }
        val block = player.location.block.getRelative(BlockFace.DOWN)
        if (block.type != Material.SPONGE) {
            return
        }
        if (!KitPvPUtils.hasKit(player)) {
            player.sendMessage("$prefix§cKitを選択してください")
            player.teleport(Location(player.world, 0.5, 11.0, 0.5, 0f, 0f), PlayerTeleportEvent.TeleportCause.PLUGIN)
        } else {
            player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 0, false, false))
        }
        event.isCancelled = true
    }
}