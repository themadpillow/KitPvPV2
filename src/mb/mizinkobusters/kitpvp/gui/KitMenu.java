package mb.mizinkobusters.kitpvp.gui;

import amata1219.niflheimr.dsl.InventoryLayout;
import amata1219.niflheimr.dsl.InventoryUI;
import amata1219.niflheimr.dsl.component.format.InventoryLines;
import mb.mizinkobusters.kitpvp.KitPvP;
import mb.mizinkobusters.kitpvp.other.ArmorGiver;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class KitMenu implements InventoryUI, Listener {

    private String prefix = "§f[§dKitPvP§f] ";

    @EventHandler(priority = EventPriority.LOWEST)
    public void optimizeInventory(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        ItemStack item = event.getCurrentItem();
        if (item == null) {
            return;
        }
        if (!item.hasItemMeta()) {
            return;
        }
        if (!item.getItemMeta().hasDisplayName()) {
            return;
        }
        if (item.getItemMeta().getDisplayName().equals("§6§lKitを選択する")) {
            return;
        }

        if (item.getItemMeta().getDisplayName().endsWith("を選択する")) {
            player.getInventory().clear();
            for (ItemStack armor : player.getInventory().getArmorContents()) {
                if (armor != null) {
                    armor.setType(null);
                }
            }
            player.closeInventory();
        }
    }

    @Override
    public InventoryLayout layout(Player viewer) {
        return build(InventoryLines.x6, l -> {
            l.title = "§6§lKitを選択してください";
            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lArcher Kitを選択する";
                    i.lore("§dNormal §7/ §b一般Kit",
                            "",
                            "§f頭: 革",
                            "§f胴: チェーン",
                            "§f腰: チェーン",
                            "§f脚: 革",
                            "§f武器: 弓[無限I]",
                            "§f補助: 矢×1",
                            "§f補助: 金のリンゴ×1",
                            "§f特殊: なし");
                    i.material = Material.BOW;
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.LEATHER_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.LEATHER_BOOTS).equip();

                    ItemStack bow = new ItemStack(Material.BOW);
                    bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                    viewer.getInventory().addItem(bow);
                    viewer.getInventory().addItem(new ItemStack(Material.ARROW));
                    viewer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    KitPvP.setKit(viewer, "Archer");
                    viewer.sendMessage(prefix + "§aArcher Kitを選択しました");
                });
            }, 0);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lAstronaut Kitを選択する";
                    i.lore("§dNormal §7/ §b一般Kit",
                            "",
                            "§f頭: ガラス",
                            "§f胴: 鉄",
                            "§f腰: 鉄",
                            "§f脚: 鉄",
                            "§f武器: 鉄の剣",
                            "§f補助: 金のリンゴ×2",
                            "§f特殊: 跳躍力上昇III",
                            "§f特殊: 移動速度低下II",
                            "§f特殊: 落下ダメージ無効");
                    i.material = Material.GLASS;
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.GLASS, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS).equip();

                    viewer.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                    viewer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));

                    viewer.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 2, false, false));
                    viewer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 1, false, false));

                    KitPvP.setKit(viewer, "Astronaut");
                    viewer.sendMessage(prefix + "§aAstronaut Kitを選択しました");
                });
            }, 1);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lBerserker Kitを選択する";
                    i.lore("§5Absolute §7/ §c購入Kit",
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
                            "§c特殊: キル時に金のリンゴを獲得できない");
                    i.basedItemStack = new ItemStack(Material.POTION);
                    i.raw(item -> {
                        PotionMeta meta = (PotionMeta) item.getItemMeta();
                        meta.setBasePotionData(new PotionData(PotionType.INSTANT_DAMAGE));
                        item.setItemMeta(meta);
                    });
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.IRON_BOOTS).equip();

                    viewer.getInventory().addItem(new ItemStack(Material.IRON_SWORD));

                    viewer.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1000000, 0, false, false));

                    KitPvP.setKit(viewer, "Berserker");
                    viewer.sendMessage(prefix + "§aBerserker Kitを選択しました");
                });
            }, 2);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lBlizzard Kitを選択する";
                    i.lore("§dTarget §7/ §c購入Kit",
                            "",
                            "§f頭: ダイヤ",
                            "§f胴: チェーン",
                            "§f腰: チェーン",
                            "§f脚: ダイヤ",
                            "§f武器: ダイヤの剣",
                            "§f補助: 金のリンゴ×1",
                            "§f特殊: 攻撃時に1/4の確率で相手に移動速度低下IIIの効果を3秒間付与する");
                    i.material = Material.ICE;
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.DIAMOND_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.DIAMOND_BOOTS).equip();

                    viewer.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                    viewer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    KitPvP.setKit(viewer, "Blizzard");
                    viewer.sendMessage(prefix + "§aBlizzard Kitを選択しました");
                });
            }, 3);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lBoxer Kitを選択する";
                    i.lore("§dNormal §7/ §b一般Kit",
                            "",
                            "§f頭: なし",
                            "§f胴: なし",
                            "§f腰: 革",
                            "§f脚: なし",
                            "§f武器: ダイヤの剣[ダメージ増加V]",
                            "§f補助: 金のリンゴ×2",
                            "§f特殊: なし");
                    i.material = Material.LEATHER_LEGGINGS;
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.AIR, Material.AIR, Material.LEATHER_LEGGINGS, Material.AIR).equip();

                    ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
                    viewer.getInventory().addItem(sword);
                    viewer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));

                    KitPvP.setKit(viewer, "Boxer");
                    viewer.sendMessage(prefix + "§aBoxer Kitを選択しました");
                });
            }, 4);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lComet Kitを選択する";
                    i.lore("§dNormal §7/ §b一般Kit",
                            "",
                            "§f頭: 革",
                            "§f胴: 革",
                            "§f腰: 革",
                            "§f脚: ダイヤ",
                            "§f武器: ダイヤの剣",
                            "§f補助: 金のリンゴ×2",
                            "§f特殊: 移動速度上昇III");
                    i.material = Material.DIAMOND_BOOTS;
                });

                s.onClick(e -> {
                    LeatherArmorMeta armormeta;

                    ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
                    helmet.addEnchantment(Enchantment.DURABILITY, 1);
                    armormeta = (LeatherArmorMeta) helmet.getItemMeta();
                    armormeta.setColor(Color.AQUA);
                    helmet.setItemMeta(armormeta);

                    ItemStack chest = new ItemStack(Material.LEATHER_CHESTPLATE);
                    helmet.addEnchantment(Enchantment.DURABILITY, 1);
                    armormeta = (LeatherArmorMeta) helmet.getItemMeta();
                    armormeta.setColor(Color.AQUA);
                    chest.setItemMeta(armormeta);

                    ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
                    helmet.addEnchantment(Enchantment.DURABILITY, 1);
                    armormeta = (LeatherArmorMeta) helmet.getItemMeta();
                    armormeta.setColor(Color.AQUA);
                    leg.setItemMeta(armormeta);

                    ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
                    boots.addEnchantment(Enchantment.DURABILITY, 1);
                    viewer.getInventory().setHelmet(helmet);
                    viewer.getInventory().setChestplate(chest);
                    viewer.getInventory().setLeggings(leg);
                    viewer.getInventory().setBoots(boots);

                    viewer.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
                    viewer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));

                    viewer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 2, false, false));

                    KitPvP.setKit(viewer, "Comet");
                    viewer.sendMessage(prefix + "§aComet Kitを選択しました");
                });
            }, 5);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lCounter Kitを選択する";
                    i.lore("§aGuard §7/ §c購入Kit",
                            "",
                            "§f頭: 鉄",
                            "§f胴: ダイヤ",
                            "§f腰: チェーン",
                            "§f脚: 鉄",
                            "§f武器: 石の剣",
                            "§f補助: 金のリンゴ×1",
                            "§f特殊: 1/5の確率で被ダメージを半減し,",
                            "§f被ダメージを相手に反射する");
                    i.material = Material.DIAMOND_CHESTPLATE;
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.IRON_HELMET, Material.DIAMOND_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.IRON_BOOTS).equip();

                    viewer.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
                    viewer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    KitPvP.setKit(viewer, "Counter");
                    viewer.sendMessage(prefix + "§aCounter Kitを選択しました");
                });
            }, 6);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lFighter Kitを選択する";
                    i.lore("§dNormal §7/ §b一般Kit",
                            "",
                            "§f頭: 鉄",
                            "§f胴: 鉄",
                            "§f腰: チェーン",
                            "§f脚: 鉄",
                            "§f武器: 鉄の剣",
                            "§f補助: 釣り竿×1",
                            "§f補助: 金のリンゴ×1");
                    i.material = Material.FISHING_ROD;
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.IRON_BOOTS).equip();

                    viewer.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                    viewer.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
                    viewer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    KitPvP.setKit(viewer, "Fighter");
                    viewer.sendMessage(prefix + "§aFighter Kitを選択しました");
                });
            }, 7);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lFisherman Kitを選択する";
                    i.lore("§dTechnical §7/ §c購入Kit",
                            "",
                            "§f頭: 革",
                            "§f胴: 鉄",
                            "§f腰: チェーン",
                            "§f脚: 革",
                            "§f武器: 鉄の剣",
                            "§f補助: 釣り竿×1",
                            "§f補助: 金のリンゴ×1",
                            "§f特殊: 釣り竿で相手を引っ掛けると少し引き寄せられる");
                    i.material = Material.RAW_FISH;
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.LEATHER_HELMET, Material.IRON_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.LEATHER_BOOTS).equip();

                    viewer.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                    viewer.getInventory().addItem(new ItemStack(Material.FISHING_ROD));
                    viewer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    KitPvP.setKit(viewer, "Fisherman");
                    viewer.sendMessage(prefix + "§aFisherman Kitを選択しました");
                });
            }, 8);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lFlame Kitを選択する";
                    i.lore("§dNormal §7/ §b一般Kit",
                            "",
                            "§f頭: 鉄",
                            "§f胴: 鉄",
                            "§f腰: チェーン",
                            "§f脚: 鉄",
                            "§f武器: 鉄の剣[火属性I]",
                            "§f武器: 弓[フレイムI]",
                            "§f補助: 矢×10",
                            "§f補助: 金のリンゴ×1",
                            "§f特殊: 火炎耐性III");
                    i.material = Material.BLAZE_POWDER;
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.IRON_BOOTS).equip();

                    ItemStack sword = new ItemStack(Material.IRON_SWORD);
                    sword.addEnchantment(Enchantment.FIRE_ASPECT, 1);
                    ItemStack bow = new ItemStack(Material.BOW);
                    bow.addEnchantment(Enchantment.ARROW_FIRE, 1);
                    viewer.getInventory().addItem(sword);
                    viewer.getInventory().addItem(bow);
                    viewer.getInventory().addItem(new ItemStack(Material.ARROW, 10));
                    viewer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    viewer.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1000000, 2, false, false));

                    KitPvP.setKit(viewer, "Flame");
                    viewer.sendMessage(prefix + "§aFlame Kitを選択しました");
                });
            }, 9);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lHealthBoost Kitを選択する";
                    i.lore("§5Hard §7/ §c購入Kit",
                            "",
                            "§f頭: 鉄",
                            "§f胴: 鉄",
                            "§f腰: ダイヤ",
                            "§f脚: 鉄",
                            "§f武器: 鉄の剣",
                            "§f補助: なし",
                            "§f特殊: 初期HPが18になり,",
                            "§fキルをするとHPが1増える(最大26)",
                            "§c特殊: キル時に金のリンゴを獲得できない");
                    i.material = Material.GOLDEN_APPLE;
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.IRON_BOOTS).equip();

                    viewer.getInventory().addItem(new ItemStack(Material.IRON_SWORD));

                    viewer.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(18.0);

                    KitPvP.setKit(viewer, "HealthBoost");
                    viewer.sendMessage(prefix + "§aHealthBoost Kitを選択しました");
                });
            }, 10);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lPotionHandler Kitを選択する";
                    i.lore("§dManiac §7/ §b一般Kit",
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
                            "§c特殊: キル時に金のリンゴを獲得できない");
                    i.basedItemStack = new ItemStack(Material.SPLASH_POTION);
                    i.raw(item -> {
                        PotionMeta meta = (PotionMeta) item.getItemMeta();
                        meta.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL));
                        item.setItemMeta(meta);
                    });
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS).equip();

                    viewer.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                    viewer.getInventory().addItem(new ItemStack(Material.POTION, 2, (short) 16389));
                    viewer.getInventory().addItem(new ItemStack(Material.POTION, 2, (short) 16396));
                    viewer.getInventory().addItem(new ItemStack(Material.POTION, 2, (short) 16388));

                    KitPvP.setKit(viewer, "PotionHandler");
                    viewer.sendMessage(prefix + "§aPotionHandler Kitを選択しました");
                });
            }, 11);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lRabbit Kitを選択する";
                    i.lore("§dNormal §7/ §c購入Kit",
                            "",
                            "§f頭: 鉄",
                            "§f胴: チェーン",
                            "§f腰: 鉄",
                            "§f脚: 革",
                            "§f武器: 鉄の剣",
                            "§f補助: 金のリンゴ×1",
                            "§f特殊: 跳躍力上昇II",
                            "§f特殊: 移動速度上昇II");
                    i.material = Material.RABBIT_FOOT;
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.IRON_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.IRON_LEGGINGS, Material.LEATHER_BOOTS).equip();

                    viewer.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                    viewer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    viewer.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, 1, false, false));
                    viewer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1, false, false));

                    KitPvP.setKit(viewer, "Rabbit");
                    viewer.sendMessage(prefix + "§aRabbit Kitを選択しました");
                });
            }, 12);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lRevive Kitを選択する";
                    i.lore("§5Catastrophy §7/ §c購入Kit",
                            "",
                            "§f頭: 鉄",
                            "§f胴: 鉄",
                            "§f腰: ダイヤ",
                            "§f脚: ダイヤ",
                            "§f武器: 鉄の剣",
                            "§f補助: なし",
                            "§f特殊: キルをすると再生能力IIが60秒間付与される",
                            "§c特殊: キル時に金のリンゴを獲得できない",
                            "§c特殊: キル時にHPが全快しない");
                    i.basedItemStack = new ItemStack(Material.SPLASH_POTION);
                    i.raw(item -> {
                        PotionMeta meta = (PotionMeta) item.getItemMeta();
                        meta.setBasePotionData(new PotionData(PotionType.REGEN));
                        item.setItemMeta(meta);
                    });
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS).equip();

                    viewer.getInventory().addItem(new ItemStack(Material.IRON_SWORD));

                    KitPvP.setKit(viewer, "Revive");
                    viewer.sendMessage(prefix + "§aRevive Kitを選択しました");
                });
            }, 13);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lSniper Kitを選択する";
                    i.lore("§dTechnical §7/ §c購入Kit",
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
                            "§f特殊: 30m以上: 与ダメージ+2");
                    i.basedItemStack = new ItemStack(Material.LONG_GRASS);
                    i.damage = 1;
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS).equip();

                    ItemStack bow = new ItemStack(Material.BOW);
                    bow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
                    bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
                    viewer.getInventory().addItem(bow);
                    viewer.getInventory().addItem(new ItemStack(Material.ARROW));
                    viewer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    KitPvP.setKit(viewer, "Sniper");
                    viewer.sendMessage(prefix + "§aSniper Kitを選択しました");
                });
            }, 14);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lStandard Kitを選択する";
                    i.lore("§dNormal §7/ §b一般Kit",
                            "",
                            "§f頭: 鉄",
                            "§f胴: ダイヤ",
                            "§f腰: 鉄",
                            "§f脚: 鉄",
                            "§f武器: 鉄の剣",
                            "§f補助: 金のリンゴ×1",
                            "§f特殊: 金のリンゴを食べると30秒間移動速度上昇Iが付与される");
                    i.material = Material.IRON_SWORD;
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.IRON_HELMET, Material.DIAMOND_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS).equip();

                    viewer.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
                    viewer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE));

                    KitPvP.setKit(viewer, "Standard");
                    viewer.sendMessage(prefix + "§aStandard Kitを選択しました");
                });
            }, 15);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lTank Kitを選択する";
                    i.lore("§aGuard §7/ §b一般Kit",
                            "",
                            "§f頭: ダイヤ",
                            "§f胴: ダイヤ",
                            "§f腰: ダイヤ",
                            "§f脚: ダイヤ",
                            "§f武器: 木の剣[ダメージ増加I]",
                            "§f補助: 金のリンゴ×2",
                            "§f特殊: 移動速度低下I");
                    i.material = Material.ANVIL;
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS).equip();

                    ItemStack sword = new ItemStack(Material.WOOD_SWORD);
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);
                    viewer.getInventory().addItem(sword);
                    viewer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));

                    viewer.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 0, false, false));

                    KitPvP.setKit(viewer, "Tank");
                    viewer.sendMessage(prefix + "§aTank Kitを選択しました");
                });
            }, 16);

            l.putSlot(s -> {
                s.icon(i -> {
                    i.displayName = "§f§lThunder Kitを選択する";
                    i.lore("§dTarget §7/ §b一般Kit",
                            "",
                            "§f頭: 鉄",
                            "§f胴: 金",
                            "§f腰: 鉄",
                            "§f脚: 金",
                            "§f武器: 金の剣[ダメージ増加II]",
                            "§f補助: 金のリンゴ×2",
                            "§f特殊: 1/7の確率で与ダメージの1.2倍のダメージを与える雷を相手の頭上に落とす");
                    i.material = Material.DOUBLE_PLANT;
                });

                s.onClick(e -> {
                    new ArmorGiver(viewer, Material.IRON_HELMET, Material.GOLD_CHESTPLATE, Material.IRON_LEGGINGS, Material.GOLD_BOOTS).equip();

                    ItemStack sword = new ItemStack(Material.GOLD_SWORD);
                    sword.addEnchantment(Enchantment.DAMAGE_ALL, 2);
                    viewer.getInventory().addItem(sword);
                    viewer.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 2));

                    KitPvP.setKit(viewer, "Thunder");
                    viewer.sendMessage(prefix + "§aThunder Kitを選択しました");
                });
            }, 17);
        });
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (!event.hasItem()) {
            return;
        }

        ItemStack item = event.getItem();
        if (!item.hasItemMeta()) {
            return;
        }
        if (!item.getItemMeta().hasDisplayName()) {
            return;
        }

        Player player = event.getPlayer();
        if (item.getItemMeta().getDisplayName().equals("§6§lKitを選択する")) {
            openInventory(player);
        }
    }
}
