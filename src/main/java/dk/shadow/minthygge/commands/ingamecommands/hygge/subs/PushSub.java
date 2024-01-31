package dk.shadow.minthygge.commands.ingamecommands.hygge.subs;

import dk.shadow.minthygge.commands.ISubCommand;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.StringUtil;
import org.bukkit.util.Vector;

public class PushSub extends ISubCommand {

    public PushSub() {
        super("push");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args, String paramString) {
        Player player = (Player) sender;

        if (args.length < 1) {
            sender.sendMessage("§Du skal skrive up/down");
            return;
        }

        if(!args[0].toLowerCase().matches("up|down")) {
            sender.sendMessage("§Du skal skrive up/down");
            return;
        }

        if(args[0].equalsIgnoreCase("down")) {
            Vector vector = new Vector(0, player.getLocation().getDirection().getY(), 0);

            vector.multiply(15);
            player.setVelocity(vector);

            return;
        }
    }
}
