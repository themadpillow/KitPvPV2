package mb.mizinkobusters.kitpvp.kit;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Berserker implements Listener {

    @EventHandler
    public void onKill(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (player.getKiller() == null) {
            return;
        }

        Player killer = player.getKiller();
        if (!KitPvPUtils.isInWorld(killer)) {
            return;
        }
        if (!KitPvPUtils.getKit(killer).equals("Berserker")) {
            return;
        }

        if (player.hasPotionEffect(PotionEffectType.WEAKNESS)) {
            player.removePotionEffect(PotionEffectType.WEAKNESS);
        }
        killer.sendMessage("§cこのKitではキル時に金のリンゴを獲得できません");
        killer.setHealth(killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        killer.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 0, false, false));
        killer.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 600, 0, false, false));
        killer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 0, false, false));
        killer.sendMessage("§7Skill Trigger: §4Berserker");
    }
}
