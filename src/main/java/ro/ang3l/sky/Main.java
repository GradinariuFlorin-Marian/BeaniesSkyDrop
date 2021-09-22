package ro.ang3l.sky;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main extends JavaPlugin {
    static List<Location> crates = new ArrayList<>();
    static List<UUID> editor = new ArrayList<>();

    @Override
    public void onEnable() {
        loadConfig();
        getCommand("beaniesskydrops").setExecutor(new Cmds());
        Bukkit.getPluginManager().registerEvents(new Evnts(this), this);
    }

    public void loadConfig() {
        File f = new File(getDataFolder(), "config.yml");
        if (!f.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }
}
