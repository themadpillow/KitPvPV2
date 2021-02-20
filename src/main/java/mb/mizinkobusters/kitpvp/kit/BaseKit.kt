package mb.mizinkobusters.kitpvp.kit

import mb.mizinkobusters.kitpvp.utils.PlayerSalvationUtils
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect

interface BaseKit {
    val kitName: String get() = this::class.java.simpleName
    val kitTypeInfo: String get() = "§dNormal"
    val lore: List<String>
    val displayItemStack: ItemStack
    val isBillingKit get() = false

    val helmet: ItemStack get() = ItemStack(Material.AIR)
    val chestplate: ItemStack get() = ItemStack(Material.AIR)
    val leggings: ItemStack get() = ItemStack(Material.AIR)
    val boots: ItemStack get() = ItemStack(Material.AIR)
    val weapons: List<ItemStack>
    val effects: List<PotionEffect> get() = listOf()

    val isHealOnKill: Boolean get() = true

    fun onEatGapple(event: PlayerItemConsumeEvent) {
    }

    fun onKill(event: PlayerDeathEvent) {
        if (isHealOnKill) {
            PlayerSalvationUtils.heal(event.entity.killer)
        }
    }

    fun onDamage(event: EntityDamageEvent) {
    }

    fun onDamageByEntity(event: EntityDamageByEntityEvent) {
    }

    fun onFish(event: PlayerFishEvent) {
    }

    fun onPassive(player: Player) {
    }
}