package mb.mizinkobusters.kitpvp.utils;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

public class PlayerSalvationUtils {

    public static void salvage(Player player) {
        player.setVelocity(new Vector());
        player.setFireTicks(0);
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20.0);
        player.getActivePotionEffects().stream().map(PotionEffect::getType).forEach(player::removePotionEffect);

        KitPvPUtils.resetKit(player);
    }

    public static void heal(Player player) {
        player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));
        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
    }
}
