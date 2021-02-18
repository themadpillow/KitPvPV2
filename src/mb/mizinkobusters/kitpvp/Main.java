package mb.mizinkobusters.kitpvp;

import mb.mizinkobusters.kitpvp.gui.KitMenu;
import mb.mizinkobusters.kitpvp.gui.KitPurchaseMenu;
import mb.mizinkobusters.kitpvp.listener.PlayerDeathListener;
import mb.mizinkobusters.kitpvp.listener.PlayerRespawnListener;
import mb.mizinkobusters.kitpvp.listener.VoidWalkingListener;
import mb.mizinkobusters.kitpvp.other.ArrowsRemover;
import mb.mizinkobusters.kitpvp.other.FieldSender;
import mb.mizinkobusters.kitpvp.utils.KitPvPUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin implements Listener {

    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new KitMenu(), this);
        Bukkit.getPluginManager().registerEvents(new KitPurchaseMenu(), this);

        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerRespawnListener(this), this);
        Bukkit.getPluginManager().registerEvents(new VoidWalkingListener(), this);

        Bukkit.getPluginManager().registerEvents(new ArrowsRemover(), this);
        Bukkit.getPluginManager().registerEvents(new FieldSender(), this);

        Bukkit.getPluginManager().registerEvents(new KitPvPUtils(), this);

        String name = this.getDescription().getName();
        String ver = this.getDescription().getVersion();
        List<String> author = this.getDescription().getAuthors();
        Bukkit.getLogger().info(name + "(v." + ver + ") by " + author);
        Bukkit.getLogger().info("Technical cooperation:");
        Bukkit.getLogger().info(" - amata1219(https://github.com/amata1219)");
        Bukkit.getLogger().info("Now Loading...");
    }

    public void onDisable() {
        Bukkit.getLogger().info("See You Next Play!");
    }
}
