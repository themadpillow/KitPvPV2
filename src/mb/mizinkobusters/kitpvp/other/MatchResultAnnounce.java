package mb.mizinkobusters.kitpvp.other;

import mb.mizinkobusters.kitpvp.utils.KitPvPUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.math.BigDecimal;

public class MatchResultAnnounce implements Listener {

    private String prefix = "§f[§dKitPvP§f] ";

    @EventHandler(priority = EventPriority.LOW)
    public void onDeath(PlayerRespawnEvent event) {
        Player dead = event.getPlayer();

        if (!KitPvPUtils.isInWorld(dead)) {
            return;
        }

        Plugin pl = Bukkit.getPluginManager().getPlugin("LeafPvPLogger");
        File plfolder = new File(pl.getDataFolder().getPath());

        String uuid = dead.getUniqueId().toString();
        File file = new File(plfolder.getPath() + "/season/beta/" + uuid + ".yml");
        if (!file.exists()) {
            return;
        }

        // dead側リザルト通知
        if (dead.getKiller() != null) {
            double health = dead.getKiller().getHealth();
            BigDecimal d = new BigDecimal(health);

            dead.sendMessage(prefix + "§d---------<< マッチリザルト >>---------");
            dead.sendMessage(prefix + "§7キラー: §e" + dead.getKiller().getName() + " §c(♥ " + String.format("%.1f", d) + ")");
            dead.sendMessage(prefix + "§7使用Kit: §e" + KitPvPUtils.getKit(dead.getKiller()));
            dead.sendMessage(prefix + "§c§lキラーを不正なプレイヤーとして通報する§7(未実装)");
        } else {
            dead.sendMessage(prefix + "§d---------<< マッチリザルト >>---------");
            dead.sendMessage(prefix + "§7死因: §e" + getDamageCauseEasily(dead.getLastDamageCause().getCause()));
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        int rating = config.getInt("KitPvP.Rating");

        dead.sendMessage(prefix + "§d---------<< あなたのスコア >>---------");
        dead.sendMessage(prefix + "§7キルストリーク: §e" + KitPvPUtils.getStreak(dead));
        dead.sendMessage(prefix + "§7所持していた金のリンゴの数: §e" + KitPvPUtils.getGapple(dead));
        dead.sendMessage(prefix + "§7最終レーティング: §e" + rating);
        dead.sendMessage(prefix + "§d---------------------------------------");

        if (dead.getKiller() == null) {
            return;
        }

        // killer側リザルト通知
        // 獲得RPアナウンスと統合
        // dead.getKiller().sendMessage(prefix + "§c" + dead.getName() + " §7を撃破");

        // 全体通知
        // レート通知を廃止
        dead.sendMessage(prefix + "§7{ §b" + dead.getKiller().getName() + " §7} === [ §6§l" + getWeapon(dead.getKiller()) + "§7 ] ===> { §c§l" + dead.getName() + " §7}");
        dead.getKiller().sendMessage(prefix + "§7{ §b§l" + dead.getKiller().getName() + " §7} === [ §6§l" + getWeapon(dead.getKiller()) + "§7 ] ===> { §c" + dead.getName() + " §7}");
        for (Player players : Bukkit.getServer().getOnlinePlayers()) {
            if (players != dead && players != dead.getKiller()) {
                players.sendMessage(prefix + "§7{ §b" + dead.getKiller().getName() + " §7} === [ §6" + getWeapon(dead.getKiller()) + "§7 ] ===> { §c" + dead.getName() + " §7}");
            }
        }
    }

    public String getDamageCauseEasily(EntityDamageEvent.DamageCause cause) {
        if (cause.equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) {
            return "ブロックの爆発に巻き込まれたため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.CONTACT)) {
            return "地面に埋まり窒息したため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.CRAMMING)) {
            return "エンティティに埋もれ窒息したため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.CUSTOM)) {
            return "プラグインによるカスタマイズされた死因";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.DRAGON_BREATH)) {
            return "ドラゴンの吐いたブレスに巻き込まれたため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.DROWNING)) {
            return "溺れ死んだため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            return "プレイヤー以外のエンティティによる攻撃";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) {
            return "エンティティの爆発に巻き込まれたため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK)) {
            return "エンティティの範囲攻撃に巻き込まれたため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.FALL)) {
            return "高所から落下したため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.FALLING_BLOCK)) {
            return "落下してきたブロックに押しつぶされたため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.FIRE)) {
            return "炎に焼かれたため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.FIRE_TICK)) {
            return "炎によるスリップダメージを受け続けたため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.FLY_INTO_WALL)) {
            return "壁に向かって飛んでいったため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.HOT_FLOOR)) {
            return "マグマブロックの上を歩き続けたため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.LAVA)) {
            return "マグマの中を泳ぎ続けたため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.LIGHTNING)) {
            return "雷を受けたため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.MAGIC)) {
            return "魔法攻撃を受けたため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.POISON)) {
            return "毒によるスリップダメージを受け続けたため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.PROJECTILE)) {
            return "飛び道具に被弾したため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.STARVATION)) {
            return "食事を摂らなさすぎたため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.SUFFOCATION)) {
            return "ブロックの中に埋まり窒息したため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.SUICIDE)) {
            return "/killコマンドを実行したため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.THORNS)) {
            return "棘の鎧の効果を受けたため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.VOID)) {
            return "奈落によるダメージを受けたため";
        }
        if (cause.equals(EntityDamageEvent.DamageCause.WITHER)) {
            return "ウィザーの効果によるスリップダメージを受け続けたため";
        }
        return "特定できませんでした";
    }

    public String getWeapon(Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType().equals(Material.WOOD_AXE)) {
            return "木の斧";
        }
        if (item.getType().equals(Material.GOLD_AXE)) {
            return "金の斧";
        }
        if (item.getType().equals(Material.STONE_AXE)) {
            return "石の斧";
        }
        if (item.getType().equals(Material.IRON_AXE)) {
            return "鉄の斧";
        }
        if (item.getType().equals(Material.DIAMOND_AXE)) {
            return "ダイヤの斧";
        }
        if (item.getType().equals(Material.WOOD_SWORD)) {
            return "木の剣";
        }
        if (item.getType().equals(Material.GOLD_SWORD)) {
            return "金の剣";
        }
        if (item.getType().equals(Material.STONE_SWORD)) {
            return "石の剣";
        }
        if (item.getType().equals(Material.IRON_SWORD)) {
            return "鉄の剣";
        }
        if (item.getType().equals(Material.DIAMOND_SWORD)) {
            return "ダイヤの剣";
        }
        if (item.getType().equals(Material.BOW)) {
            return "弓";
        }
        if (item.getType().equals(Material.ARROW)) {
            return "矢";
        }
        if (item.getType().equals(Material.GOLDEN_APPLE)) {
            return "金のリンゴ";
        }
        if (item.getType().equals(Material.POTION)) {
            return "ポーション";
        }
        if (item.getType().equals(Material.SPLASH_POTION)) {
            return "スプラッシュポーション";
        }
        if (item.getType().equals(Material.FISHING_ROD)) {
            return "釣り竿";
        }
        return item.getType().name();
    }
}
