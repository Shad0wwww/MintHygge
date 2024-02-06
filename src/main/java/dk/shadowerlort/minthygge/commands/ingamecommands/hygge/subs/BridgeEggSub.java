package dk.shadowerlort.minthygge.commands.ingamecommands.hygge.subs;

import dk.shadowerlort.minthygge.commands.ISubCommand;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BridgeEggSub extends ISubCommand {
    public BridgeEggSub() {
        super("bridgeEgg");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args, String paramString) {

        Player player = (Player) sender;

        ItemStack bridgeEgg = new ItemStack(Material.EGG, 1);
        ItemMeta bridgeEggMeta = bridgeEgg.getItemMeta();
        bridgeEggMeta.setDisplayName("§d§lBridge Egg");
        bridgeEgg.setItemMeta(bridgeEggMeta);

        if (player.getInventory().firstEmpty() == -1) {
            player.sendMessage("§cDit inventory er fuld!");
            return;
        }

        player.getInventory().addItem(bridgeEgg);

    }
}
