package ro.ang3l.sky;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

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
                        Main.crates.remove(loc);
                        List<String> str = new ArrayList<>();
                        for (Location locNew : Main.crates) {
                            str.add(locNew.getWorld().getName() + ":" + locNew.getBlockX() + ":" + locNew.getBlockY() + ":" + locNew.getBlockZ());
                        }
                        pl.getConfig().set("Crates", str);
                        pl.saveConfig();
                        e.getPlayer().sendMessage(Utils.color("&eCrate removed!"));
                    } else {
                        Main.crates.add(loc);
                        List<String> str = pl.getConfig().getStringList("Crates");
                        str.add(loc.getWorld().getName() + ":" + loc.getBlockX() + ":" + loc.getBlockY() + ":" + loc.getBlockZ());
                        pl.getConfig().set("Crates", str);
                        pl.saveConfig();
                        e.getPlayer().sendMessage(Utils.color("&eCrate added!"));
                    }
                    Main.editor.remove(e.getPlayer().getUniqueId());
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
