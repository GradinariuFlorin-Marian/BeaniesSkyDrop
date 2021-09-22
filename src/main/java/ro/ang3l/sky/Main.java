package ro.ang3l.sky;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("beaniesskydrops").setExecutor(new Cmds());
        Bukkit.getPluginManager().registerEvents(new Evnts(), this);
    }

    public void loadConfig() {
        File f = new File(getDataFolder(), "config.yml");
        if (!f.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }
}
