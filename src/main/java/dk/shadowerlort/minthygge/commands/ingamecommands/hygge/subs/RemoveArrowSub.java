package dk.shadowerlort.minthygge.commands.ingamecommands.hygge.subs;

import dk.shadowerlort.minthygge.commands.ISubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getPlayer;

public class RemoveArrowSub extends ISubCommand {
    public RemoveArrowSub() {
        super("removeArrow");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args, String paramString) {
        Player player = (Player) sender;
        if (args.length == 1) {
            player = player.getServer().getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage("§cSpilleren findes ikke");
                return;
            }
        }

        try {
            ((CraftPlayer)player).getHandle().getDataWatcher().watch(9, (byte) 0);
            sender.sendMessage("§aPile fjernet");
        } catch (Exception e) {
            System.out.println(e);
            sender.sendMessage("§cDer skete en fejl");
        }
    }
}
