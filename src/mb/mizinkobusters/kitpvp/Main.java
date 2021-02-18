package mb.mizinkobusters.kitpvp;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin implements Listener {

    public void onEnable() {
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
