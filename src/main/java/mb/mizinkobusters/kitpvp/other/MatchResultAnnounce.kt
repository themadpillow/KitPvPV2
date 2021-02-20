package mb.mizinkobusters.kitpvp.other

import java.io.File
import java.math.BigDecimal
import mb.mizinkobusters.kitpvp.Main.Companion.PREFIX
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

object MatchResultAnnounce : Listener {
    @EventHandler(priority = EventPriority.LOW)
    fun onDeath(event: PlayerRespawnEvent) {
        val dead = event.player
        if (!KitPvPUtils.isInWorld(dead)) return
        val pl = Bukkit.getPluginManager().getPlugin("LeafPvPLogger")
        val plfolder = File(pl!!.dataFolder.path)
        val uuid = dead.uniqueId.toString()
        val file = File(plfolder.path + "/season/beta/" + uuid + ".yml")
        if (!file.exists()) return

        // dead側リザルト通知
        if (dead.killer != null) {
            val health = dead.killer!!.health
            val d = BigDecimal(health)
            dead.sendMessage("$PREFIX§d---------<< マッチリザルト >>---------")
            dead.sendMessage(PREFIX + "§7キラー: §e" + dead.killer!!.name + " §c(♥ " + String.format("%.1f", d) + ")")
            dead.sendMessage(PREFIX + "§7使用Kit: §e" + KitPvPUtils.getKit(dead.killer))
            dead.sendMessage("$PREFIX§c§lキラーを不正なプレイヤーとして通報する§7(未実装)")
        } else {
            dead.sendMessage("$PREFIX§d---------<< マッチリザルト >>---------")
            dead.sendMessage(PREFIX + "§7死因: §e" + getDamageCauseEasily(dead.lastDamageCause!!.cause))
        }
        val config: FileConfiguration = YamlConfiguration.loadConfiguration(file)
        val rating = config.getInt("KitPvP.Rating")
        dead.sendMessage("$PREFIX§d---------<< あなたのスコア >>---------")
        dead.sendMessage(PREFIX + "§7キルストリーク: §e" + KitPvPUtils.getStreak(dead))
        dead.sendMessage(PREFIX + "§7所持していた金のリンゴの数: §e" + KitPvPUtils.getGapple(dead))
        dead.sendMessage("$PREFIX§7最終レーティング: §e$rating")
        dead.sendMessage("$PREFIX§d---------------------------------------")
        if (dead.killer == null) {
            return
        }

        // killer側リザルト通知
        // 獲得RPアナウンスと統合
        // dead.getKiller().sendMessage(prefix + "§c" + dead.getName() + " §7を撃破");

        // 全体通知
        // レート通知を廃止
        dead.sendMessage(PREFIX + "§7{ §b" + dead.killer!!.name + " §7} === [ §6§l" + getWeapon(dead.killer) + "§7 ] ===> { §c§l" + dead.name + " §7}")
        dead.killer!!.sendMessage(PREFIX + "§7{ §b§l" + dead.killer!!.name + " §7} === [ §6§l" + getWeapon(dead.killer) + "§7 ] ===> { §c" + dead.name + " §7}")
        for (players in Bukkit.getServer().onlinePlayers) {
            if (players !== dead && players !== dead.killer) {
                players.sendMessage(PREFIX + "§7{ §b" + dead.killer!!.name + " §7} === [ §6" + getWeapon(dead.killer) + "§7 ] ===> { §c" + dead.name + " §7}")
            }
        }
    }

    private fun getDamageCauseEasily(cause: DamageCause): String {
        return when (cause) {
            DamageCause.BLOCK_EXPLOSION -> "ブロックの爆発に巻き込まれたため"
            DamageCause.CONTACT -> "地面に埋まり窒息したため"
            DamageCause.CRAMMING -> "エンティティに埋もれ窒息したため"
            DamageCause.CUSTOM -> "プラグインによるカスタマイズされた死因"
            DamageCause.DRAGON_BREATH -> "ドラゴンの吐いたブレスに巻き込まれたため"
            DamageCause.DROWNING -> "溺れ死んだため"
            DamageCause.ENTITY_ATTACK -> "プレイヤー以外のエンティティによる攻撃"
            DamageCause.ENTITY_EXPLOSION -> "エンティティの爆発に巻き込まれたため"
            DamageCause.ENTITY_SWEEP_ATTACK -> "エンティティの範囲攻撃に巻き込まれたため"
            DamageCause.FALL -> "高所から落下したため"
            DamageCause.FALLING_BLOCK -> "落下してきたブロックに押しつぶされたため"
            DamageCause.FIRE -> "炎に焼かれたため"
            DamageCause.FIRE_TICK -> "炎によるスリップダメージを受け続けたため"
            DamageCause.FLY_INTO_WALL -> "壁に向かって飛んでいったため"
            DamageCause.HOT_FLOOR -> "マグマブロックの上を歩き続けたため"
            DamageCause.LAVA -> "マグマの中を泳ぎ続けたため"
            DamageCause.LIGHTNING -> "雷を受けたため"
            DamageCause.MAGIC -> "魔法攻撃を受けたため"
            DamageCause.POISON -> "毒によるスリップダメージを受け続けたため"
            DamageCause.PROJECTILE -> "飛び道具に被弾したため"
            DamageCause.STARVATION -> "食事を摂らなさすぎたため"
            DamageCause.SUFFOCATION -> "ブロックの中に埋まり窒息したため"
            DamageCause.SUICIDE -> "/killコマンドを実行したため"
            DamageCause.THORNS -> "棘の鎧の効果を受けたため"
            DamageCause.VOID -> "奈落によるダメージを受けたため"
            DamageCause.WITHER -> "ウィザーの効果によるスリップダメージを受け続けたため"
            else -> "特定できませんでした"
        }
    }

    private fun getWeapon(player: Player): String {
        return when (val itemType = player.inventory.itemInMainHand.type) {
            Material.WOOD_AXE -> "木の斧"
            Material.GOLD_AXE -> "金の斧"
            Material.STONE_AXE -> "石の斧"
            Material.IRON_AXE -> "鉄の斧"
            Material.DIAMOND_AXE -> "ダイヤの斧"
            Material.WOOD_SWORD -> "木の剣"
            Material.GOLD_SWORD -> "金の剣"
            Material.STONE_SWORD -> "石の剣"
            Material.IRON_SWORD -> "鉄の剣"
            Material.DIAMOND_SWORD -> "ダイヤの剣"
            Material.BOW -> "弓"
            Material.ARROW -> "矢"
            Material.GOLDEN_APPLE -> "金のリンゴ"
            Material.POTION -> "ポーション"
            Material.SPLASH_POTION -> "スプラッシュポーション"
            Material.FISHING_ROD -> "釣り竿"
            else -> itemType.name
        }
    }
}