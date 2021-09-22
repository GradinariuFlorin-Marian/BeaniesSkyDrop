package ro.ang3l.sky;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Utils {
    public static void help(Player p) {
        p.sendMessage(Utils.color("&e/beaniesskydrops give <player> skydrop <amount>"));
        p.sendMessage(Utils.color("&e/beaniesskydrops give <player> superskydrop <amount>"));
        p.sendMessage(Utils.color("&e/beaniesskydrops editor"));
    }

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static Boolean verifyNumber(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static ItemStack stormGenerator() {
        ItemStack itm = new ItemStack(Material.NETHER_STAR);
        ItemMeta met = itm.getItemMeta();
        met.setDisplayName(Utils.color("&e&lSTORM GENERATOR &7(Right Click)"));
        met.setLore(Arrays.asList(Utils.color("&7Triggers a storm in the &cWarzone"), Utils.color("&7that drops supplies into the &eSky Chest.")));
        itm.setItemMeta(met);
        return itm;
    }

    public static ItemStack superStormGenerator() {
        ItemStack itm = new ItemStack(Material.NETHER_STAR);
        ItemMeta met = itm.getItemMeta();
        met.setDisplayName(Utils.color("&b&lSUPER &e&lSTORM GENERATOR &7(Right Click)"));
        met.setLore(Arrays.asList(Utils.color("&7Triggers a storm in the &cWarzone"), Utils.color("&7that drops supplies into the &eSky Chest"),
                "", Utils.color("&b&lSUPER STORM"), Utils.color("&bSuper Storms restock all chests with &c&lEXTRA"), Utils.color("&bitems, giving you a MUCH better chance to"), Utils.color("&bget amazing loot!"),
                "", Utils.color("&fAwards 5 &bRARE &fSky Chest items.")));
        itm.setItemMeta(met);
        return itm;
    }
}
