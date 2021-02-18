package mb.mizinkobusters.kitpvp.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.PlayerInventory;

import java.util.HashMap;
import java.util.UUID;

public class KitPvPUtils implements Listener {

    public static HashMap<UUID, String> kit = new HashMap<>();
    public static HashMap<UUID, Integer> streak = new HashMap<>();

    public static String getKit(Player player) {
        if (kit.getOrDefault(player.getUniqueId(), "none") != null) {
            return kit.getOrDefault(player.getUniqueId(), "none");
        }
        return "none";
    }

    public static boolean hasKit(Player player) {
        return !getKit(player).equals("none");
    }

    public static void resetKit(Player player) {
        kit.remove(player.getUniqueId());
    }

    public static void setKit(Player player, String name) {
        if (hasKit(player)) {
            resetKit(player);
        }
        kit.put(player.getUniqueId(), name);
    }

    public static int getStreak(Player player) {
        return streak.getOrDefault(player.getUniqueId(), 0);
    }

    public static boolean isInWorld(Player player) {
        return player.getWorld().getName().equals("kitpvp") || player.getWorld().getName().equals("kitpvp2");
    }

    @EventHandler
    public static void setStreak(PlayerDeathEvent event) {
        Player dead = event.getEntity();
        if (!isInWorld(dead)) {
            return;
        }

        streak.put(dead.getUniqueId(), 0);

        if (dead.getKiller() == null) {
            return;
        }
        Player killer = dead.getKiller();
        streak.put(killer.getUniqueId(), getStreak(killer) + 1);
    }

    public static int getGapple(Player player) {
        PlayerInventory pi = player.getInventory();
        int apple = 0;
        for (int i = 0; i <= 35; i++) {
            if (pi.getItem(i) == null) {
                continue;
            }
            if (pi.getItem(i).getType().equals(Material.AIR)) {
                continue;
            }
            if (pi.getItem(i).getType().equals(Material.GOLDEN_APPLE)) {
                apple += pi.getItem(i).getAmount();
            }
        }
        return apple;
    }
}
