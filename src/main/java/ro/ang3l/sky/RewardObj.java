package ro.ang3l.sky;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Getter
@Setter
public class RewardObj {
    private String type;
    private List<String> commands;
    private ItemStack item;
}
