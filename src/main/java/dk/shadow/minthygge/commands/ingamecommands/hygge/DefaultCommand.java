package dk.shadow.minthygge.commands.ingamecommands.hygge;

import dk.shadow.minthygge.commands.ISubCommand;
import dk.shadow.minthygge.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DefaultCommand extends ISubCommand {

    public DefaultCommand() {
        super("default");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args, String paramString) {
        if(!(sender instanceof Player)) return;
        Player player = (Player) sender;
        player.sendMessage(ColorUtils.getColored(
                "&8&l[ &3&lMINTHYGGE &8&l]",
                "&7&l- &f/hygge inventorydrop &7<true/false>",
                "&7&l- &f/hygge push &7<player>"
        ));

    }
}