package dk.shadowerlort.minthygge.commands.ingamecommands.hygge.subs;

import dk.shadowerlort.minthygge.commands.ISubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
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
            double speed = 2;
            pushEntity(player, speed);
            return;
        }
    }

    private void pushEntity(Entity entity, double speed) {
        Vector currentVelocity = entity.getVelocity();
        currentVelocity.setY(-speed);
        entity.setVelocity(currentVelocity);
    }
}
