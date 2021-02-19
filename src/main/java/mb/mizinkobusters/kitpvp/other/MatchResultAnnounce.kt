package mb.mizinkobusters.kitpvp.other

import java.io.File
import java.math.BigDecimal
import mb.mizinkobusters.kitpvp.utils.KitPvPUtils
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent.DamageCause
import org.bukkit.event.player.PlayerRespawnEvent

class MatchResultAnnounce : Listener {
    private val prefix = "§f[§dKitPvP§f] "

    @EventHandler(priority = EventPriority.LOW)
    fun onDeath(event: PlayerRespawnEvent) {
        val dead = event.player
        if (!KitPvPUtils.isInWorld(dead)) {
            return
        }
        val pl = Bukkit.getPluginManager().getPlugin("LeafPvPLogger")
        val plfolder = File(pl!!.dataFolder.path)
        val uuid = dead.uniqueId.toString()
        val file = File(plfolder.path + "/season/beta/" + uuid + ".yml")
        if (!file.exists()) {
            return
        }

        // dead側リザルト通知
        if (dead.killer != null) {
            val health = dead.killer!!.health
            val d = BigDecimal(health)
            dead.sendMessage("$prefix§d---------<< マッチリザルト >>---------")
            dead.sendMessage(prefix + "§7キラー: §e" + dead.killer!!.name + " §c(♥ " + String.format("%.1f", d) + ")")
            dead.sendMessage(prefix + "§7使用Kit: §e" + KitPvPUtils.getKit(dead.killer))
            dead.sendMessage("$prefix§c§lキラーを不正なプレイヤーとして通報する§7(未実装)")
        } else {
            dead.sendMessage("$prefix§d---------<< マッチリザルト >>---------")
            dead.sendMessage(prefix + "§7死因: §e" + getDamageCauseEasily(dead.lastDamageCause!!.cause))
        }
        val config: FileConfiguration = YamlConfiguration.loadConfiguration(file)
        val rating = config.getInt("KitPvP.Rating")
        dead.sendMessage("$prefix§d---------<< あなたのスコア >>---------")
        dead.sendMessage(prefix + "§7キルストリーク: §e" + KitPvPUtils.getStreak(dead))
        dead.sendMessage(prefix + "§7所持していた金のリンゴの数: §e" + KitPvPUtils.getGapple(dead))
        dead.sendMessage("$prefix§7最終レーティング: §e$rating")
        dead.sendMessage("$prefix§d---------------------------------------")
        if (dead.killer == null) {
            return
        }

        // killer側リザルト通知
        // 獲得RPアナウンスと統合
        // dead.getKiller().sendMessage(prefix + "§c" + dead.getName() + " §7を撃破");

        // 全体通知
        // レート通知を廃止
        dead.sendMessage(prefix + "§7{ §b" + dead.killer!!.name + " §7} === [ §6§l" + getWeapon(dead.killer) + "§7 ] ===> { §c§l" + dead.name + " §7}")
        dead.killer!!.sendMessage(prefix + "§7{ §b§l" + dead.killer!!.name + " §7} === [ §6§l" + getWeapon(dead.killer) + "§7 ] ===> { §c" + dead.name + " §7}")
        for (players in Bukkit.getServer().onlinePlayers) {
            if (players !== dead && players !== dead.killer) {
                players.sendMessage(prefix + "§7{ §b" + dead.killer!!.name + " §7} === [ §6" + getWeapon(dead.killer) + "§7 ] ===> { §c" + dead.name + " §7}")
            }
        }
    }

    private fun getDamageCauseEasily(cause: DamageCause): String {
        if (cause == DamageCause.BLOCK_EXPLOSION) {
            return "ブロックの爆発に巻き込まれたため"
        }
        if (cause == DamageCause.CONTACT) {
            return "地面に埋まり窒息したため"
        }
        if (cause == DamageCause.CRAMMING) {
            return "エンティティに埋もれ窒息したため"
        }
        if (cause == DamageCause.CUSTOM) {
            return "プラグインによるカスタマイズされた死因"
        }
        if (cause == DamageCause.DRAGON_BREATH) {
            return "ドラゴンの吐いたブレスに巻き込まれたため"
        }
        if (cause == DamageCause.DROWNING) {
            return "溺れ死んだため"
        }
        if (cause == DamageCause.ENTITY_ATTACK) {
            return "プレイヤー以外のエンティティによる攻撃"
        }
        if (cause == DamageCause.ENTITY_EXPLOSION) {
            return "エンティティの爆発に巻き込まれたため"
        }
        if (cause == DamageCause.ENTITY_SWEEP_ATTACK) {
            return "エンティティの範囲攻撃に巻き込まれたため"
        }
        if (cause == DamageCause.FALL) {
            return "高所から落下したため"
        }
        if (cause == DamageCause.FALLING_BLOCK) {
            return "落下してきたブロックに押しつぶされたため"
        }
        if (cause == DamageCause.FIRE) {
            return "炎に焼かれたため"
        }
        if (cause == DamageCause.FIRE_TICK) {
            return "炎によるスリップダメージを受け続けたため"
        }
        if (cause == DamageCause.FLY_INTO_WALL) {
            return "壁に向かって飛んでいったため"
        }
        if (cause == DamageCause.HOT_FLOOR) {
            return "マグマブロックの上を歩き続けたため"
        }
        if (cause == DamageCause.LAVA) {
            return "マグマの中を泳ぎ続けたため"
        }
        if (cause == DamageCause.LIGHTNING) {
            return "雷を受けたため"
        }
        if (cause == DamageCause.MAGIC) {
            return "魔法攻撃を受けたため"
        }
        if (cause == DamageCause.POISON) {
            return "毒によるスリップダメージを受け続けたため"
        }
        if (cause == DamageCause.PROJECTILE) {
            return "飛び道具に被弾したため"
        }
        if (cause == DamageCause.STARVATION) {
            return "食事を摂らなさすぎたため"
        }
        if (cause == DamageCause.SUFFOCATION) {
            return "ブロックの中に埋まり窒息したため"
        }
        if (cause == DamageCause.SUICIDE) {
            return "/killコマンドを実行したため"
        }
        if (cause == DamageCause.THORNS) {
            return "棘の鎧の効果を受けたため"
        }
        if (cause == DamageCause.VOID) {
            return "奈落によるダメージを受けたため"
        }
        return if (cause == DamageCause.WITHER) {
            "ウィザーの効果によるスリップダメージを受け続けたため"
        } else "特定できませんでした"
    }

    private fun getWeapon(player: Player?): String {
        val item = player!!.inventory.itemInMainHand
        if (item.type == Material.WOOD_AXE) {
            return "木の斧"
        }
        if (item.type == Material.GOLD_AXE) {
            return "金の斧"
        }
        if (item.type == Material.STONE_AXE) {
            return "石の斧"
        }
        if (item.type == Material.IRON_AXE) {
            return "鉄の斧"
        }
        if (item.type == Material.DIAMOND_AXE) {
            return "ダイヤの斧"
        }
        if (item.type == Material.WOOD_SWORD) {
            return "木の剣"
        }
        if (item.type == Material.GOLD_SWORD) {
            return "金の剣"
        }
        if (item.type == Material.STONE_SWORD) {
            return "石の剣"
        }
        if (item.type == Material.IRON_SWORD) {
            return "鉄の剣"
        }
        if (item.type == Material.DIAMOND_SWORD) {
            return "ダイヤの剣"
        }
        if (item.type == Material.BOW) {
            return "弓"
        }
        if (item.type == Material.ARROW) {
            return "矢"
        }
        if (item.type == Material.GOLDEN_APPLE) {
            return "金のリンゴ"
        }
        if (item.type == Material.POTION) {
            return "ポーション"
        }
        if (item.type == Material.SPLASH_POTION) {
            return "スプラッシュポーション"
        }
        return if (item.type == Material.FISHING_ROD) {
            "釣り竿"
        } else item.type.name
    }
}