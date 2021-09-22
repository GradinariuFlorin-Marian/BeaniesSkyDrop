package ro.ang3l.sky;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.*;

public class Main extends JavaPlugin {
    static List<Location> crates = new ArrayList<>();
    static List<Location> used = new ArrayList<>();
    static Map<Integer, RewardObj> storm = new HashMap<>();
    static Map<Integer, RewardObj> superStorm = new HashMap<>();
    static List<UUID> editor = new ArrayList<>();
    static String crateType = "";

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
        for (String crate : getConfig().getStringList("Crates")) {
            String[] splitter = crate.split(":");
            Location loc = new Location(Bukkit.getWorld(splitter[0]), Integer.parseInt(splitter[1]),
                    Integer.parseInt(splitter[2]), Integer.parseInt(splitter[3]));
            crates.add(loc);
        }
        int size = 0;
        for (String str : getConfig().getConfigurationSection("Storm").getKeys(false)) {
            RewardObj reward = new RewardObj();
            if (getConfig().getString("Storm." + str + ".Type").equalsIgnoreCase("Item")) {
                ItemStack itm = new ItemStack(Material.valueOf(getConfig().getString("Storm." + str + ".ItemType")), getConfig().getInt("Storm." + str + ".Amount"));
                ItemMeta met = itm.getItemMeta();
                if (getConfig().contains("Storm." + str + ".Name")) {
                    met.setDisplayName(Utils.color(getConfig().getString("Storm." + str + ".Name")));
                }
                if (getConfig().contains("Storm." + str + ".Lore")) {
                    List<String> lor = new ArrayList<>();
                    for (String s : getConfig().getStringList("Storm." + str + ".Lore")) {
                        lor.add(Utils.color(s));
                    }
                    met.setLore(lor);
                }
                if (getConfig().contains("Storm." + str + ".Enchant")) {
                    for (String s : getConfig().getStringList("Storm." + str + ".Enchant")) {
                        String[] splitter = s.split(":");
                        if (Utils.getEnchant(splitter[0]) != null) {
                            if (Utils.verifyNumber(splitter[1])) {
                                met.addEnchant(Utils.getEnchant(splitter[0]), Integer.parseInt(splitter[1]), true);
                            } else {
                                System.out.println(s + " value of enchant is not a number!");
                            }
                        } else {
                            System.out.println(s + " is not a enchant!");
                        }
                    }
                }
                itm.setItemMeta(met);
                reward.setType("ITEM");
                reward.setItem(itm);
            } else {
                reward.setType("COMMAND");
                reward.setCommands(getConfig().getStringList("Storm." + str + ".Command"));
            }
            storm.put(size, reward);
            size++;
        }
        size = 0;
        for (String str : getConfig().getConfigurationSection("SuperStorm").getKeys(false)) {
            RewardObj reward = new RewardObj();
            if (getConfig().getString("SuperStorm." + str + ".Type").equalsIgnoreCase("Item")) {
                ItemStack itm = new ItemStack(Material.valueOf(getConfig().getString("SuperStorm." + str + ".ItemType")), getConfig().getInt("SuperStorm." + str + ".Amount"));
                ItemMeta met = itm.getItemMeta();
                if (getConfig().contains("SuperStorm." + str + ".Name")) {
                    met.setDisplayName(Utils.color(getConfig().getString("SuperStorm." + str + ".Name")));
                }
                if (getConfig().contains("SuperStorm." + str + ".Lore")) {
                    List<String> lor = new ArrayList<>();
                    for (String s : getConfig().getStringList("SuperStorm." + str + ".Lore")) {
                        lor.add(Utils.color(s));
                    }
                    met.setLore(lor);
                }
                if (getConfig().contains("SuperStorm." + str + ".Enchant")) {
                    for (String s : getConfig().getStringList("SuperStorm." + str + ".Enchant")) {
                        String[] splitter = s.split(":");
                        if (Utils.getEnchant(splitter[0]) != null) {
                            if (Utils.verifyNumber(splitter[1])) {
                                met.addEnchant(Utils.getEnchant(splitter[0]), Integer.parseInt(splitter[1]), true);
                            } else {
                                System.out.println(s + " value of enchant is not a number!");
                            }
                        } else {
                            System.out.println(s + " is not a enchant!");
                        }
                    }
                }
                itm.setItemMeta(met);
                reward.setType("ITEM");
                reward.setItem(itm);
            } else {
                reward.setType("COMMAND");
                reward.setCommands(getConfig().getStringList("SuperStorm." + str + ".Command"));
            }
            superStorm.put(size, reward);
            size++;
        }
    }
}
