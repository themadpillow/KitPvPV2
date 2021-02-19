package mb.mizinkobusters.kitpvp.gui

import amata1219.niflheimr.dsl.InventoryLayout
import amata1219.niflheimr.dsl.InventoryUI
import amata1219.niflheimr.dsl.component.Icon
import amata1219.niflheimr.dsl.component.format.InventoryLines
import amata1219.niflheimr.dsl.component.slot.Slot
import mb.mizinkobusters.kitpvp.other.ArmorGiver
import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionData
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.potion.PotionType

class KitMenu : InventoryUI, Listener {
    private val prefix = "§f[§dKitPvP§f] "

    @EventHandler(priority = EventPriority.LOWEST)
    fun optimizeInventory(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        val item = event.currentItem ?: return
        if (!item.hasItemMeta()) {
            return
        }
        if (!item.itemMeta!!.hasDisplayName()) {
            return
        }
        if (item.itemMeta!!.displayName == "§6§lKitを選択する") {
            return
        }
        if (item.itemMeta!!.displayName.endsWith("を選択する")) {
            player.inventory.clear()
            for (armor in player.inventory.armorContents) {
                armor?.type = null
            }
            player.closeInventory()
        }
    }

    override fun layout(viewer: Player): InventoryLayout {
        return build(InventoryLines.x6) { l: InventoryLayout ->
            l.title = "§6§lKitを選択してください"
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lArcher Kitを選択する"
                    i.lore(
                        "§dNormal §7/ §b一般Kit",
                        "",
                        "§f頭: 革",
                        "§f胴: チェーン",
                        "§f腰: チェーン",
                        "§f脚: 革",
                        "§f武器: 弓[無限I]",
                        "§f補助: 矢×1",
                        "§f補助: 金のリンゴ×1",
                        "§f特殊: なし"
                    )
                    i.material = Material.BOW
                }
                s.onClick {
                    ArmorGiver(
                        viewer,
                        Material.LEATHER_HELMET,
                        Material.CHAINMAIL_CHESTPLATE,
                        Material.CHAINMAIL_LEGGINGS,
                        Material.LEATHER_BOOTS
                    ).equip()
                    val bow = ItemStack(Material.BOW)
                    bow.addEnchantment(Enchantment.ARROW_INFINITE, 1)
                    viewer.inventory.addItem(bow)
                    viewer.inventory.addItem(ItemStack(Material.ARROW))
                    viewer.inventory.addItem(ItemStack(Material.GOLDEN_APPLE))
                    KitPvPUtils.setKit(viewer, "Archer")
                    viewer.sendMessage("$prefix§aArcher Kitを選択しました")
                }
            }, 0)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lAstronaut Kitを選択する"
                    i.lore(
                        "§dNormal §7/ §b一般Kit",
                        "",
                        "§f頭: ガラス",
                        "§f胴: 鉄",
                        "§f腰: 鉄",
                        "§f脚: 鉄",
                        "§f武器: 鉄の剣",
                        "§f補助: 金のリンゴ×2",
                        "§f特殊: 跳躍力上昇III",
                        "§f特殊: 移動速度低下II",
                        "§f特殊: 落下ダメージ無効"
                    )
                    i.material = Material.GLASS
                }
                s.onClick {
                    ArmorGiver(
                        viewer,
                        Material.GLASS,
                        Material.IRON_CHESTPLATE,
                        Material.IRON_LEGGINGS,
                        Material.IRON_BOOTS
                    ).equip()
                    viewer.inventory.addItem(ItemStack(Material.IRON_SWORD))
                    viewer.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 2))
                    viewer.addPotionEffect(PotionEffect(PotionEffectType.JUMP, 1000000, 2, false, false))
                    viewer.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 1000000, 1, false, false))
                    KitPvPUtils.setKit(viewer, "Astronaut")
                    viewer.sendMessage("$prefix§aAstronaut Kitを選択しました")
                }
            }, 1)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lBerserker Kitを選択する"
                    i.lore(
                        "§5Absolute §7/ §c購入Kit",
                        "",
                        "§f頭: 鉄",
                        "§f胴: 鉄",
                        "§f腰: チェーン",
                        "§f脚: 鉄",
                        "§f武器: 鉄の剣",
                        "§f補助: なし",
                        "§f特殊: 弱体化I",
                        "§f特殊: キルをすると弱体化の効果が消え,",
                        "§f攻撃力上昇I, 衝撃吸収I, 移動速度上昇Iの効果が30秒間付与される",
                        "§c特殊: キル時に金のリンゴを獲得できない"
                    )
                    i.basedItemStack = ItemStack(Material.POTION)
                    i.raw { item: ItemStack ->
                        val meta = item.itemMeta as PotionMeta?
                        meta!!.basePotionData = PotionData(PotionType.INSTANT_DAMAGE)
                        item.itemMeta = meta
                    }
                }
                s.onClick {
                    ArmorGiver(
                        viewer,
                        Material.IRON_HELMET,
                        Material.IRON_CHESTPLATE,
                        Material.CHAINMAIL_LEGGINGS,
                        Material.IRON_BOOTS
                    ).equip()
                    viewer.inventory.addItem(ItemStack(Material.IRON_SWORD))
                    viewer.addPotionEffect(PotionEffect(PotionEffectType.WEAKNESS, 1000000, 0, false, false))
                    KitPvPUtils.setKit(viewer, "Berserker")
                    viewer.sendMessage("$prefix§aBerserker Kitを選択しました")
                }
            }, 2)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lBlizzard Kitを選択する"
                    i.lore(
                        "§dTarget §7/ §c購入Kit",
                        "",
                        "§f頭: ダイヤ",
                        "§f胴: チェーン",
                        "§f腰: チェーン",
                        "§f脚: ダイヤ",
                        "§f武器: ダイヤの剣",
                        "§f補助: 金のリンゴ×1",
                        "§f特殊: 攻撃時に1/4の確率で相手に移動速度低下IIIの効果を3秒間付与する"
                    )
                    i.material = Material.ICE
                }
                s.onClick {
                    ArmorGiver(
                        viewer,
                        Material.DIAMOND_HELMET,
                        Material.CHAINMAIL_CHESTPLATE,
                        Material.CHAINMAIL_LEGGINGS,
                        Material.DIAMOND_BOOTS
                    ).equip()
                    viewer.inventory.addItem(ItemStack(Material.IRON_SWORD))
                    viewer.inventory.addItem(ItemStack(Material.GOLDEN_APPLE))
                    KitPvPUtils.setKit(viewer, "Blizzard")
                    viewer.sendMessage("$prefix§aBlizzard Kitを選択しました")
                }
            }, 3)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lBoxer Kitを選択する"
                    i.lore(
                        "§dNormal §7/ §b一般Kit",
                        "",
                        "§f頭: なし",
                        "§f胴: なし",
                        "§f腰: 革",
                        "§f脚: なし",
                        "§f武器: ダイヤの剣[ダメージ増加V]",
                        "§f補助: 金のリンゴ×2",
                        "§f特殊: なし"
                    )
                    i.material = Material.LEATHER_LEGGINGS
                }
                s.onClick {
                    ArmorGiver(viewer, Material.AIR, Material.AIR, Material.LEATHER_LEGGINGS, Material.AIR).equip()
                    val sword = ItemStack(Material.DIAMOND_SWORD)
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 5)
                    viewer.inventory.addItem(sword)
                    viewer.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 2))
                    KitPvPUtils.setKit(viewer, "Boxer")
                    viewer.sendMessage("$prefix§aBoxer Kitを選択しました")
                }
            }, 4)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lComet Kitを選択する"
                    i.lore(
                        "§dNormal §7/ §b一般Kit",
                        "",
                        "§f頭: 革",
                        "§f胴: 革",
                        "§f腰: 革",
                        "§f脚: ダイヤ",
                        "§f武器: ダイヤの剣",
                        "§f補助: 金のリンゴ×2",
                        "§f特殊: 移動速度上昇III"
                    )
                    i.material = Material.DIAMOND_BOOTS
                }
                s.onClick {
                    var armormeta: LeatherArmorMeta?
                    val helmet = ItemStack(Material.LEATHER_HELMET)
                    helmet.addEnchantment(Enchantment.DURABILITY, 1)
                    armormeta = helmet.itemMeta as LeatherArmorMeta?
                    armormeta!!.color = Color.AQUA
                    helmet.itemMeta = armormeta
                    val chest = ItemStack(Material.LEATHER_CHESTPLATE)
                    helmet.addEnchantment(Enchantment.DURABILITY, 1)
                    armormeta = helmet.itemMeta as LeatherArmorMeta?
                    armormeta!!.color = Color.AQUA
                    chest.itemMeta = armormeta
                    val leg = ItemStack(Material.LEATHER_LEGGINGS)
                    helmet.addEnchantment(Enchantment.DURABILITY, 1)
                    armormeta = helmet.itemMeta as LeatherArmorMeta?
                    armormeta!!.color = Color.AQUA
                    leg.itemMeta = armormeta
                    val boots = ItemStack(Material.DIAMOND_BOOTS)
                    boots.addEnchantment(Enchantment.DURABILITY, 1)
                    viewer.inventory.helmet = helmet
                    viewer.inventory.chestplate = chest
                    viewer.inventory.leggings = leg
                    viewer.inventory.boots = boots
                    viewer.inventory.addItem(ItemStack(Material.DIAMOND_SWORD))
                    viewer.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 2))
                    viewer.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 1000000, 2, false, false))
                    KitPvPUtils.setKit(viewer, "Comet")
                    viewer.sendMessage("$prefix§aComet Kitを選択しました")
                }
            }, 5)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lCounter Kitを選択する"
                    i.lore(
                        "§aGuard §7/ §c購入Kit",
                        "",
                        "§f頭: 鉄",
                        "§f胴: ダイヤ",
                        "§f腰: チェーン",
                        "§f脚: 鉄",
                        "§f武器: 石の剣",
                        "§f補助: 金のリンゴ×1",
                        "§f特殊: 1/5の確率で被ダメージを半減し,",
                        "§f被ダメージを相手に反射する"
                    )
                    i.material = Material.DIAMOND_CHESTPLATE
                }
                s.onClick {
                    ArmorGiver(
                        viewer,
                        Material.IRON_HELMET,
                        Material.DIAMOND_CHESTPLATE,
                        Material.CHAINMAIL_LEGGINGS,
                        Material.IRON_BOOTS
                    ).equip()
                    viewer.inventory.addItem(ItemStack(Material.STONE_SWORD))
                    viewer.inventory.addItem(ItemStack(Material.GOLDEN_APPLE))
                    KitPvPUtils.setKit(viewer, "Counter")
                    viewer.sendMessage("$prefix§aCounter Kitを選択しました")
                }
            }, 6)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lFighter Kitを選択する"
                    i.lore(
                        "§dNormal §7/ §b一般Kit",
                        "",
                        "§f頭: 鉄",
                        "§f胴: 鉄",
                        "§f腰: チェーン",
                        "§f脚: 鉄",
                        "§f武器: 鉄の剣",
                        "§f補助: 釣り竿×1",
                        "§f補助: 金のリンゴ×1"
                    )
                    i.material = Material.FISHING_ROD
                }
                s.onClick {
                    ArmorGiver(
                        viewer,
                        Material.IRON_HELMET,
                        Material.IRON_CHESTPLATE,
                        Material.CHAINMAIL_LEGGINGS,
                        Material.IRON_BOOTS
                    ).equip()
                    viewer.inventory.addItem(ItemStack(Material.IRON_SWORD))
                    viewer.inventory.addItem(ItemStack(Material.FISHING_ROD))
                    viewer.inventory.addItem(ItemStack(Material.GOLDEN_APPLE))
                    KitPvPUtils.setKit(viewer, "Fighter")
                    viewer.sendMessage("$prefix§aFighter Kitを選択しました")
                }
            }, 7)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lFisherman Kitを選択する"
                    i.lore(
                        "§dTechnical §7/ §c購入Kit",
                        "",
                        "§f頭: 革",
                        "§f胴: 鉄",
                        "§f腰: チェーン",
                        "§f脚: 革",
                        "§f武器: 鉄の剣",
                        "§f補助: 釣り竿×1",
                        "§f補助: 金のリンゴ×1",
                        "§f特殊: 釣り竿で相手を引っ掛けると少し引き寄せられる"
                    )
                    i.material = Material.RAW_FISH
                }
                s.onClick {
                    ArmorGiver(
                        viewer,
                        Material.LEATHER_HELMET,
                        Material.IRON_CHESTPLATE,
                        Material.CHAINMAIL_LEGGINGS,
                        Material.LEATHER_BOOTS
                    ).equip()
                    viewer.inventory.addItem(ItemStack(Material.IRON_SWORD))
                    viewer.inventory.addItem(ItemStack(Material.FISHING_ROD))
                    viewer.inventory.addItem(ItemStack(Material.GOLDEN_APPLE))
                    KitPvPUtils.setKit(viewer, "Fisherman")
                    viewer.sendMessage("$prefix§aFisherman Kitを選択しました")
                }
            }, 8)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lFlame Kitを選択する"
                    i.lore(
                        "§dNormal §7/ §b一般Kit",
                        "",
                        "§f頭: 鉄",
                        "§f胴: 鉄",
                        "§f腰: チェーン",
                        "§f脚: 鉄",
                        "§f武器: 鉄の剣[火属性I]",
                        "§f武器: 弓[フレイムI]",
                        "§f補助: 矢×10",
                        "§f補助: 金のリンゴ×1",
                        "§f特殊: 火炎耐性III"
                    )
                    i.material = Material.BLAZE_POWDER
                }
                s.onClick {
                    ArmorGiver(
                        viewer,
                        Material.IRON_HELMET,
                        Material.IRON_CHESTPLATE,
                        Material.CHAINMAIL_LEGGINGS,
                        Material.IRON_BOOTS
                    ).equip()
                    val sword = ItemStack(Material.IRON_SWORD)
                    sword.addEnchantment(Enchantment.FIRE_ASPECT, 1)
                    val bow = ItemStack(Material.BOW)
                    bow.addEnchantment(Enchantment.ARROW_FIRE, 1)
                    viewer.inventory.addItem(sword)
                    viewer.inventory.addItem(bow)
                    viewer.inventory.addItem(ItemStack(Material.ARROW, 10))
                    viewer.inventory.addItem(ItemStack(Material.GOLDEN_APPLE))
                    viewer.addPotionEffect(PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1000000, 2, false, false))
                    KitPvPUtils.setKit(viewer, "Flame")
                    viewer.sendMessage("$prefix§aFlame Kitを選択しました")
                }
            }, 9)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lHealthBoost Kitを選択する"
                    i.lore(
                        "§5Hard §7/ §c購入Kit",
                        "",
                        "§f頭: 鉄",
                        "§f胴: 鉄",
                        "§f腰: ダイヤ",
                        "§f脚: 鉄",
                        "§f武器: 鉄の剣",
                        "§f補助: なし",
                        "§f特殊: 初期HPが18になり,",
                        "§fキルをするとHPが1増える(最大26)",
                        "§c特殊: キル時に金のリンゴを獲得できない"
                    )
                    i.material = Material.GOLDEN_APPLE
                }
                s.onClick {
                    ArmorGiver(
                        viewer,
                        Material.IRON_HELMET,
                        Material.IRON_CHESTPLATE,
                        Material.DIAMOND_LEGGINGS,
                        Material.IRON_BOOTS
                    ).equip()
                    viewer.inventory.addItem(ItemStack(Material.IRON_SWORD))
                    viewer.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = 18.0
                    KitPvPUtils.setKit(viewer, "HealthBoost")
                    viewer.sendMessage("$prefix§aHealthBoost Kitを選択しました")
                }
            }, 10)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lPotionHandler Kitを選択する"
                    i.lore(
                        "§dManiac §7/ §b一般Kit",
                        "",
                        "§f頭: チェーン",
                        "§f胴: チェーン",
                        "§f腰: チェーン",
                        "§f脚: チェーン",
                        "§f武器: 鉄の剣",
                        "§f補助: 治癒のスプラッシュポーション×2",
                        "§f補助: 負傷のスプラッシュポーション×2",
                        "§f補助: 毒のスプラッシュポーション×2",
                        "§f特殊: キル時に治癒のスプラッシュポーションを獲得する",
                        "§c特殊: キル時に金のリンゴを獲得できない"
                    )
                    i.basedItemStack = ItemStack(Material.SPLASH_POTION)
                    i.raw { item: ItemStack ->
                        val meta = item.itemMeta as PotionMeta?
                        meta!!.basePotionData = PotionData(PotionType.INSTANT_HEAL)
                        item.itemMeta = meta
                    }
                }
                s.onClick {
                    ArmorGiver(
                        viewer,
                        Material.CHAINMAIL_HELMET,
                        Material.CHAINMAIL_CHESTPLATE,
                        Material.CHAINMAIL_LEGGINGS,
                        Material.CHAINMAIL_BOOTS
                    ).equip()
                    viewer.inventory.addItem(ItemStack(Material.IRON_SWORD))
                    viewer.inventory.addItem(ItemStack(Material.POTION, 2, 16389.toShort()))
                    viewer.inventory.addItem(ItemStack(Material.POTION, 2, 16396.toShort()))
                    viewer.inventory.addItem(ItemStack(Material.POTION, 2, 16388.toShort()))
                    KitPvPUtils.setKit(viewer, "PotionHandler")
                    viewer.sendMessage("$prefix§aPotionHandler Kitを選択しました")
                }
            }, 11)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lRabbit Kitを選択する"
                    i.lore(
                        "§dNormal §7/ §c購入Kit",
                        "",
                        "§f頭: 鉄",
                        "§f胴: チェーン",
                        "§f腰: 鉄",
                        "§f脚: 革",
                        "§f武器: 鉄の剣",
                        "§f補助: 金のリンゴ×1",
                        "§f特殊: 跳躍力上昇II",
                        "§f特殊: 移動速度上昇II"
                    )
                    i.material = Material.RABBIT_FOOT
                }
                s.onClick {
                    ArmorGiver(
                        viewer,
                        Material.IRON_HELMET,
                        Material.CHAINMAIL_CHESTPLATE,
                        Material.IRON_LEGGINGS,
                        Material.LEATHER_BOOTS
                    ).equip()
                    viewer.inventory.addItem(ItemStack(Material.IRON_SWORD))
                    viewer.inventory.addItem(ItemStack(Material.GOLDEN_APPLE))
                    viewer.addPotionEffect(PotionEffect(PotionEffectType.JUMP, 1000000, 1, false, false))
                    viewer.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false))
                    KitPvPUtils.setKit(viewer, "Rabbit")
                    viewer.sendMessage("$prefix§aRabbit Kitを選択しました")
                }
            }, 12)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lRevive Kitを選択する"
                    i.lore(
                        "§5Catastrophy §7/ §c購入Kit",
                        "",
                        "§f頭: 鉄",
                        "§f胴: 鉄",
                        "§f腰: ダイヤ",
                        "§f脚: ダイヤ",
                        "§f武器: 鉄の剣",
                        "§f補助: なし",
                        "§f特殊: キルをすると再生能力IIが60秒間付与される",
                        "§c特殊: キル時に金のリンゴを獲得できない",
                        "§c特殊: キル時にHPが全快しない"
                    )
                    i.basedItemStack = ItemStack(Material.SPLASH_POTION)
                    i.raw { item: ItemStack ->
                        val meta = item.itemMeta as PotionMeta?
                        meta!!.basePotionData = PotionData(PotionType.REGEN)
                        item.itemMeta = meta
                    }
                }
                s.onClick {
                    ArmorGiver(
                        viewer,
                        Material.IRON_HELMET,
                        Material.IRON_CHESTPLATE,
                        Material.DIAMOND_LEGGINGS,
                        Material.DIAMOND_BOOTS
                    ).equip()
                    viewer.inventory.addItem(ItemStack(Material.IRON_SWORD))
                    KitPvPUtils.setKit(viewer, "Revive")
                    viewer.sendMessage("$prefix§aRevive Kitを選択しました")
                }
            }, 13)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lSniper Kitを選択する"
                    i.lore(
                        "§dTechnical §7/ §c購入Kit",
                        "",
                        "§f頭: チェーン",
                        "§f胴: チェーン",
                        "§f腰: チェーン",
                        "§f脚: チェーン",
                        "§f武器: 弓[射撃ダメージ増加I, 無限I]",
                        "§f補助: 矢×1",
                        "§f補助: 金のリンゴ×1",
                        "§f特殊: 矢を当てた相手との距離に応じて与ダメージが変動する",
                        "§f特殊: 10m未満: 与ダメージ-2",
                        "§f特殊: 10m以上15m未満: 与ダメージ+0",
                        "§f特殊: 15m以上30m未満: 与ダメージ+1",
                        "§f特殊: 30m以上: 与ダメージ+2"
                    )
                    i.basedItemStack = ItemStack(Material.LONG_GRASS)
                    i.damage = 1
                }
                s.onClick {
                    ArmorGiver(
                        viewer,
                        Material.CHAINMAIL_HELMET,
                        Material.CHAINMAIL_CHESTPLATE,
                        Material.CHAINMAIL_LEGGINGS,
                        Material.CHAINMAIL_BOOTS
                    ).equip()
                    val bow = ItemStack(Material.BOW)
                    bow.addEnchantment(Enchantment.ARROW_DAMAGE, 1)
                    bow.addEnchantment(Enchantment.ARROW_INFINITE, 1)
                    viewer.inventory.addItem(bow)
                    viewer.inventory.addItem(ItemStack(Material.ARROW))
                    viewer.inventory.addItem(ItemStack(Material.GOLDEN_APPLE))
                    KitPvPUtils.setKit(viewer, "Sniper")
                    viewer.sendMessage("$prefix§aSniper Kitを選択しました")
                }
            }, 14)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lStandard Kitを選択する"
                    i.lore(
                        "§dNormal §7/ §b一般Kit",
                        "",
                        "§f頭: 鉄",
                        "§f胴: ダイヤ",
                        "§f腰: 鉄",
                        "§f脚: 鉄",
                        "§f武器: 鉄の剣",
                        "§f補助: 金のリンゴ×1",
                        "§f特殊: 金のリンゴを食べると30秒間移動速度上昇Iが付与される"
                    )
                    i.material = Material.IRON_SWORD
                }
                s.onClick {
                    ArmorGiver(
                        viewer,
                        Material.IRON_HELMET,
                        Material.DIAMOND_CHESTPLATE,
                        Material.IRON_LEGGINGS,
                        Material.IRON_BOOTS
                    ).equip()
                    viewer.inventory.addItem(ItemStack(Material.IRON_SWORD))
                    viewer.inventory.addItem(ItemStack(Material.GOLDEN_APPLE))
                    KitPvPUtils.setKit(viewer, "Standard")
                    viewer.sendMessage("$prefix§aStandard Kitを選択しました")
                }
            }, 15)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lTank Kitを選択する"
                    i.lore(
                        "§aGuard §7/ §b一般Kit",
                        "",
                        "§f頭: ダイヤ",
                        "§f胴: ダイヤ",
                        "§f腰: ダイヤ",
                        "§f脚: ダイヤ",
                        "§f武器: 木の剣[ダメージ増加I]",
                        "§f補助: 金のリンゴ×2",
                        "§f特殊: 移動速度低下I"
                    )
                    i.material = Material.ANVIL
                }
                s.onClick {
                    ArmorGiver(
                        viewer,
                        Material.DIAMOND_HELMET,
                        Material.DIAMOND_CHESTPLATE,
                        Material.DIAMOND_LEGGINGS,
                        Material.DIAMOND_BOOTS
                    ).equip()
                    val sword = ItemStack(Material.WOOD_SWORD)
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 1)
                    viewer.inventory.addItem(sword)
                    viewer.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 2))
                    viewer.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 1000000, 0, false, false))
                    KitPvPUtils.setKit(viewer, "Tank")
                    viewer.sendMessage("$prefix§aTank Kitを選択しました")
                }
            }, 16)
            l.putSlot({ s: Slot ->
                s.icon { i: Icon ->
                    i.displayName = "§f§lThunder Kitを選択する"
                    i.lore(
                        "§dTarget §7/ §b一般Kit",
                        "",
                        "§f頭: 鉄",
                        "§f胴: 金",
                        "§f腰: 鉄",
                        "§f脚: 金",
                        "§f武器: 金の剣[ダメージ増加II]",
                        "§f補助: 金のリンゴ×2",
                        "§f特殊: 1/7の確率で与ダメージの1.2倍のダメージを与える雷を相手の頭上に落とす"
                    )
                    i.material = Material.DOUBLE_PLANT
                }
                s.onClick {
                    ArmorGiver(
                        viewer,
                        Material.IRON_HELMET,
                        Material.GOLD_CHESTPLATE,
                        Material.IRON_LEGGINGS,
                        Material.GOLD_BOOTS
                    ).equip()
                    val sword = ItemStack(Material.GOLD_SWORD)
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 2)
                    viewer.inventory.addItem(sword)
                    viewer.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 2))
                    KitPvPUtils.setKit(viewer, "Thunder")
                    viewer.sendMessage("$prefix§aThunder Kitを選択しました")
                }
            }, 17)
        }
    }

    @EventHandler
    fun onClick(event: PlayerInteractEvent) {
        if (!event.hasItem()) {
            return
        }
        val item = event.item
        if (!item!!.hasItemMeta()) {
            return
        }
        if (!item.itemMeta!!.hasDisplayName()) {
            return
        }
        val player = event.player
        if (item.itemMeta!!.displayName == "§6§lKitを選択する") {
            openInventory(player)
        }
    }
}