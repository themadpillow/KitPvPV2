package mb.mizinkobusters.kitpvp.gui

import amata1219.niflheimr.dsl.InventoryLayout
import amata1219.niflheimr.dsl.InventoryUI
import amata1219.niflheimr.dsl.component.Icon
import amata1219.niflheimr.dsl.component.format.InventoryLines
import amata1219.niflheimr.dsl.component.slot.Slot
import mb.mizinkobusters.kitpvp.Main.Companion.PREFIX
import mb.mizinkobusters.kitpvp.kit.Kit
import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import mb.mizinkobusters.kitpvp.utils.equip
import org.bukkit.entity.Player

object KitMenu : InventoryUI {
    override fun layout(viewer: Player): InventoryLayout {
        return build(InventoryLines.x6) { l: InventoryLayout ->
            l.title = "§6§lKitを選択してください"

            Kit.values().forEachIndexed { index, kit ->
                val kitObject = kit.kitObject

                l.putSlot({ s: Slot ->
                    s.icon { i: Icon ->
                        i.displayName = "§f§l${kitObject.kitName} Kitを選択する"
                        i.lore(
                            if (kitObject.isBillingKit) "${kitObject.kitTypeInfo} §7/ §c購入Kit"
                            else "${kitObject.kitTypeInfo} §7/ §b一般Kit",
                            "",
                            *kitObject.lore.toTypedArray()
                        )
                        i.basedItemStack = kitObject.displayItemStack
                    }
                    s.onClick {
                        viewer.equip(
                            helmet = kitObject.helmet,
                            chestplate = kitObject.chestplate,
                            leggings = kitObject.leggings,
                            boots = kitObject.boots
                        )
                        kitObject.weapons.forEach { viewer.inventory.addItem(it) }
                        kitObject.effects.forEach { viewer.addPotionEffect(it) }
                        kitObject.onPassive(viewer)
                        KitPvPUtils.setKit(viewer, kitObject)
                        viewer.sendMessage("$PREFIX§a${kitObject.kitName} Kitを選択しました")
                    }
                }, index)
            }
        }
    }
}