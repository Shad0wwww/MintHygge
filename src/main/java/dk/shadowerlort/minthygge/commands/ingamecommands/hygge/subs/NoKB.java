package dk.shadowerlort.minthygge.commands.ingamecommands.hygge.subs;

import dk.shadowerlort.minthygge.MintHygge;
import dk.shadowerlort.minthygge.commands.ISubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class NoKB extends ISubCommand {
    public NoKB() {
        super("noKB");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args, String paramString) {
        Player player = (Player) sender;

        //check if player has metadata noKB
        if (player.hasMetadata("noKB")) {
            player.removeMetadata("noKB", MintHygge.getInstance());
            sender.sendMessage("§aDu har nu slået no-knockback fra");
        } else {
            player.setMetadata("noKB", new FixedMetadataValue(MintHygge.getInstance(), true));
            sender.sendMessage("§aDu har nu slået no-knockback til");
        }
    }

}
