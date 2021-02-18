package mb.mizinkobusters.kitpvp.other;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ArmorGiver {

	private Player player;
	private PlayerInventory pi;
	private Material helmet;
	private Material chest;
	private Material leg;
	private Material boots;

	public ArmorGiver(Player player, Material helmet, Material chest, Material leg, Material boots) {
		this.player = player;
		this.pi = player.getInventory();
		this.helmet = helmet;
		this.chest = chest;
		this.leg = leg;
		this.boots = boots;
	}

	public Player getPlayer() {
		return player;
	}

	public Material getHelmet() {
		return helmet;
	}

	public Material getChest() {
		return chest;
	}

	public Material getLeg() {
		return leg;
	}

	public Material getBoots() {
		return boots;
	}

	public void equip() {
		// TODO エンチャントなどの対応
		pi.setHelmet(new ItemStack(helmet));
		pi.setChestplate(new ItemStack(chest));
		pi.setLeggings(new ItemStack(leg));
		pi.setBoots(new ItemStack(boots));
	}
}
