package ro.ang3l.sky;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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

    public static ItemStack stormGenerator(int amount) {
        ItemStack itm = new ItemStack(Material.NETHER_STAR, amount);
        ItemMeta met = itm.getItemMeta();
        met.setDisplayName(Utils.color("&e&lSTORM GENERATOR &7(Right Click)"));
        met.setLore(Arrays.asList(Utils.color("&7Triggers a storm in the &cWarzone"), Utils.color("&7that drops supplies into the &eSky Chest.")));
        itm.setItemMeta(met);
        return itm;
    }

    public static ItemStack superStormGenerator(int amount) {
        ItemStack itm = new ItemStack(Material.NETHER_STAR, amount);
        ItemMeta met = itm.getItemMeta();
        met.setDisplayName(Utils.color("&b&lSUPER &e&lSTORM GENERATOR &7(Right Click)"));
        met.setLore(Arrays.asList(Utils.color("&7Triggers a storm in the &cWarzone"), Utils.color("&7that drops supplies into the &eSky Chest"),
                "", Utils.color("&b&lSUPER STORM"), Utils.color("&bSuper Storms restock all chests with &c&lEXTRA"), Utils.color("&bitems, giving you a MUCH better chance to"), Utils.color("&bget amazing loot!"),
                "", Utils.color("&fAwards 5 &bRARE &fSky Chest items.")));
        itm.setItemMeta(met);
        return itm;
    }
    public static Enchantment getEnchant(String name){
        switch(name.toLowerCase()){
            case "unbreaking":
                return Enchantment.DURABILITY;
            case "thorns":
                return Enchantment.THORNS;
            case "sweeping_edge":
                return Enchantment.SWEEPING_EDGE;
            case "soul_speed":
                return Enchantment.SOUL_SPEED;
            case "smite":
                return Enchantment.DAMAGE_UNDEAD;
            case "silk_touch":
                return Enchantment.SILK_TOUCH;
            case "sharpness":
                return Enchantment.DAMAGE_ALL;
            case "riptide":
                return Enchantment.RIPTIDE;
            case "respiration":
                return Enchantment.OXYGEN;
            case "quick_charge":
                return Enchantment.QUICK_CHARGE;
            case "punch":
                return Enchantment.ARROW_KNOCKBACK;
            case "protection":
                return Enchantment.PROTECTION_ENVIRONMENTAL;
            case "projectile_protection":
                return Enchantment.PROTECTION_PROJECTILE;
            case "piercing":
                return Enchantment.PIERCING;
            case "multishot":
                return Enchantment.MULTISHOT;
            case "mending":
                return Enchantment.MENDING;
            case "lure":
                return Enchantment.LURE;
            case "luck_of_the_sea":
                return Enchantment.LUCK;
            case "loyalty":
                return Enchantment.LOYALTY;
            case "looting":
                return Enchantment.LOOT_BONUS_MOBS;
            case "knockback":
                return Enchantment.KNOCKBACK;
            case "infinity":
                return Enchantment.ARROW_INFINITE;
            case "impaling":
                return Enchantment.IMPALING;
            case "frost_walker":
                return Enchantment.FROST_WALKER;
            case "fortune":
                return Enchantment.LOOT_BONUS_BLOCKS;
            case "flame":
                return Enchantment.ARROW_FIRE;
            case "fire_protection":
                return Enchantment.PROTECTION_FIRE;
            case "fire_aspect":
                return Enchantment.FIRE_ASPECT;
            case "feather_falling":
                return Enchantment.PROTECTION_FALL;
            case "efficiency":
                return Enchantment.DIG_SPEED;
            case "depth_strider":
                return Enchantment.DEPTH_STRIDER;
            case "curse_of_vanishing":
                return Enchantment.VANISHING_CURSE;
            case "curse_of_binding":
                return Enchantment.BINDING_CURSE;
            case "channeling":
                return Enchantment.CHANNELING;
            case "blast_protection":
                return Enchantment.PROTECTION_EXPLOSIONS;
            case "bane_of_arthropods":
                return Enchantment.DAMAGE_ARTHROPODS;
            case "aqua_affinity":
                return Enchantment.WATER_WORKER;
            default:
                return null;
        }
    }
}
