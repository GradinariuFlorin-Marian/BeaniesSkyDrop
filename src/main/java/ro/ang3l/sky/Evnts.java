package ro.ang3l.sky;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Evnts implements Listener {
    private Main pl;

    public Evnts(Main plugin) {
        pl = plugin;
    }

    @EventHandler
    public void cmd(PlayerInteractEvent e) {
        if (Main.editor.contains(e.getPlayer().getUniqueId())) {
            if (e.getClickedBlock() != null) {
                if (e.getClickedBlock().getType().equals(Material.CHEST)) {
                    Location loc = e.getClickedBlock().getLocation();
                    if (Main.crates.contains(loc)) {
                        e.setCancelled(true);
                        Main.crates.remove(loc);
                        List<String> str = new ArrayList<>();
                        for (Location locNew : Main.crates) {
                            str.add(locNew.getWorld().getName() + ":" + locNew.getBlockX() + ":" + locNew.getBlockY() + ":" + locNew.getBlockZ());
                        }
                        pl.getConfig().set("Crates", str);
                        pl.saveConfig();
                        e.getPlayer().sendMessage(Utils.color("&eCrate removed!"));
                    } else {
                        e.setCancelled(true);
                        Main.crates.add(loc);
                        List<String> str = new ArrayList<>();
                        for (Location locNew : Main.crates) {
                            str.add(locNew.getWorld().getName() + ":" + locNew.getBlockX() + ":" + locNew.getBlockY() + ":" + locNew.getBlockZ());
                        }
                        pl.getConfig().set("Crates", str);
                        pl.saveConfig();
                        e.getPlayer().sendMessage(Utils.color("&eCrate added!"));
                    }
                    Main.editor.remove(e.getPlayer().getUniqueId());
                }
            }
        } else {
            if (e.getItem() != null) {
                if (e.getItem().isSimilar(Utils.stormGenerator(1))) {
                    e.setCancelled(true);
                    Main.crateType = "storm";
                    Main.used.clear();
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendMessage(Utils.color(e.getPlayer().getName() + " &7&lhas triggered a storm in the Warezone... Sky Chests have been restocked!"));
                    }
                    if (e.getItem().getAmount() > 1) {
                        e.getItem().setAmount(e.getItem().getAmount() - 1);
                    } else {
                        e.getItem().setAmount(0);
                    }
                } else if (e.getItem().isSimilar(Utils.superStormGenerator(1))) {
                    e.setCancelled(true);
                    Main.crateType = "superStorm";
                    Main.used.clear();
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendMessage(Utils.color(e.getPlayer().getName() + " &7&lhas triggered a super storm in the Warezone... Sky Chests have been restocked!"));
                    }
                    if (e.getItem().getAmount() > 1) {
                        e.getItem().setAmount(e.getItem().getAmount() - 1);
                    } else {
                        e.getItem().setAmount(0);
                    }
                }
            }
            if (e.getClickedBlock() != null) {
                if (e.getClickedBlock().getType().equals(Material.CHEST) && Main.crates.contains(e.getClickedBlock().getLocation())) {
                    if (Main.crateType.isEmpty() || Main.used.contains(e.getClickedBlock().getLocation())) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(Utils.color("&eCrate looted!"));
                    } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                        e.setCancelled(false);
                        if (Main.crateType.equalsIgnoreCase("storm")) {
                            Chest chest = (Chest) e.getClickedBlock().getState();
                            chest.getInventory().clear();
                            byte size = (byte) ThreadLocalRandom.current().nextInt(1, 6);
                            for (int i = 1; i <= size; i++) {
                                RewardObj reward = Main.storm.get(ThreadLocalRandom.current().nextInt(0, Main.storm.size()));
                                if (reward.getType().equalsIgnoreCase("ITEM")) {
                                    chest.getInventory().addItem(reward.getItem());
                                } else {
                                    for (String s : reward.getCommands()) {
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("%player%", e.getPlayer().getName()));
                                    }
                                }
                            }
                        } else if (Main.crateType.equalsIgnoreCase("superStorm")) {
                            Chest chest = (Chest) e.getClickedBlock().getState();
                            chest.getInventory().clear();
                            for (int i = 1; i <= 5; i++) {
                                RewardObj reward = Main.superStorm.get(ThreadLocalRandom.current().nextInt(0, Main.superStorm.size()));
                                if (reward.getType().equalsIgnoreCase("ITEM")) {
                                    chest.getInventory().addItem(reward.getItem());
                                } else {
                                    for (String s : reward.getCommands()) {
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), s.replace("%player%", e.getPlayer().getName()));
                                    }
                                }
                            }
                        }
                        Main.used.add(e.getClickedBlock().getLocation());
                    }
                }
            }
        }
    }

    @EventHandler
    public void brk(BlockBreakEvent e) {
        if (Main.crates.contains(e.getBlock().getLocation())) {
            e.setCancelled(true);
        }
    }
}
