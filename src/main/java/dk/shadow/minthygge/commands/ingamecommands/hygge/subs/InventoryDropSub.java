package dk.shadow.minthygge.commands.ingamecommands.hygge.subs;

import dk.shadow.minthygge.MintHygge;
import dk.shadow.minthygge.commands.ISubCommand;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.Objects;

public class InventoryDropSub extends ISubCommand {
    public InventoryDropSub() {
        super("InventoryDrop");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args, String paramString) {

        if (args.length == 0) {
            sender.sendMessage("§Du skal skrive true/false");
            return;
        }

        if(!args[0].toLowerCase().matches("true|false")) {
            sender.sendMessage("§cDu skal skrive true/false");
            return;
        }

        Boolean shouldDrop = Boolean.parseBoolean(args[0]);
        Player player = (Player) sender;

        player.setMetadata("inventoryDrop", new FixedMetadataValue(MintHygge.getInstance(), shouldDrop));
        player.sendMessage(shouldDrop ? "§aDropper ikke dine items længere" : "§aDropper dine items igen");
    }
}
