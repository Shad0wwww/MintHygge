package dk.shadowerlort.minthygge.commands.ingamecommands.hygge;

import dk.shadowerlort.minthygge.commands.ISubCommand;
import dk.shadowerlort.minthygge.utils.ColorUtils;
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
                "&7&l- &f/hygge push &7<player>",
                "&7&l- &f/hygge removeArrow &7[player]",
                "&7&l- &f/hygge noKB",
                "&7&l- &f/hygge invis",
                "&7&l- &f/hygge anvil <player> <number of anvils>"
        ));

    }
}