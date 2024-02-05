package dk.shadowerlort.minthygge.commands.ingamecommands.hygge.subs;

import dk.shadowerlort.minthygge.commands.ISubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AnvilSub extends ISubCommand {
    public AnvilSub() {
        super("anvil");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args, String paramString) {

        if (args.length == 0) {
            sender.sendMessage("§cDu skal skrive <player> <number of anvils>");
            return;
        }

        Player player = (Player) sender;
        Player target = player.getServer().getPlayer(args[0]);

        int amount = args.length > 1 ? Integer.parseInt(args[1]) : 1;


        if (target == null) {
            sender.sendMessage("§cSpilleren er ikke online");
            return;
        }

        for (int i = 0; i < amount; i++) {
            target.playSound(target.getLocation(), "block.anvil.use", 3.0F, 1);
        }




    }
}
