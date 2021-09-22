package ro.ang3l.sky;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cmds implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (command.getName().equalsIgnoreCase("beaniesskydrops")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (args.length < 1) {
                    Utils.help(p);
                } else {
                    if (args[0].equalsIgnoreCase("editor")) {
                        if(p.hasPermission("beaniesskydrops.editor")) {
                            if (!Main.editor.contains(p.getUniqueId())) {
                                Main.editor.add(p.getUniqueId());
                                p.sendMessage(Utils.color("&eEditor enabled!"));
                            } else {
                                Main.editor.remove(p.getUniqueId());
                                p.sendMessage(Utils.color("&eEditor disabled!"));
                            }
                        }else{
                            p.sendMessage(Utils.color("&cYou don't have permission!"));
                        }
                    } else if (args[0].equalsIgnoreCase("give")) {
                        if (p.hasPermission("beaniesskydrops.give")) {
                            if (args.length < 4) {
                                p.sendMessage(Utils.color("&e/beaniesskydrops give <player> skydrop <amount>"));
                                p.sendMessage(Utils.color("&e/beaniesskydrops give <player> superskydrop <amount>"));
                            } else {
                                Player receiver = Bukkit.getPlayer(args[1]);
                                if (receiver != null) {
                                    if (args[2].equalsIgnoreCase("skydrop")) {
                                        if (Utils.verifyNumber(args[3])) {
                                            receiver.getInventory().addItem(Utils.stormGenerator(Integer.parseInt(args[3])));
                                            p.sendMessage(Utils.color("&eYou received a &e&lStorm Generator &ekey!"));
                                        } else {
                                            p.sendMessage(Utils.color("&eFourth argument is not a number!"));
                                        }
                                    } else if (args[2].equalsIgnoreCase("superskydrop")) {
                                        if (Utils.verifyNumber(args[3])) {
                                            receiver.getInventory().addItem(Utils.superStormGenerator(Integer.parseInt(args[3])));
                                            p.sendMessage(Utils.color("&eYou received a &b&lSuper &e&lStorm Generator &ekey!"));
                                        } else {
                                            p.sendMessage(Utils.color("&eFourth argument is not a number!"));
                                        }
                                    } else {
                                        p.sendMessage(Utils.color("&e/beaniesskydrops give <player> skydrop <amount>"));
                                        p.sendMessage(Utils.color("&e/beaniesskydrops give <player> superskydrop <amount>"));
                                    }
                                } else {
                                    p.sendMessage(Utils.color("&ePlayer not found!"));
                                }
                            }
                        }else{
                            p.sendMessage(Utils.color("&cYou don't have permission!"));
                        }
                    }
                }
            }
        }
        return false;
    }
}
