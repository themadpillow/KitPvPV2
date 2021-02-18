package mb.mizinkobusters.kitpvp.kit;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Revive implements Listener {

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
        if (!KitPvPUtils.getKit(killer).equals("Revive")) {
            return;
        }
        killer.sendMessage("§cこのKitではキル時に金のリンゴを獲得できません");
        killer.sendMessage("§cこのKitではキル時にHPが全快しません");
        killer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1200, 1));
        killer.sendMessage("§7Skill Trigger: §6Revive");
        killer.setHealth(killer.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    }
}
