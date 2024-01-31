package dk.shadow.minthygge.commands.ingamecommands.hygge;

import dk.shadow.minthygge.commands.ICommand;
import dk.shadow.minthygge.commands.ISubCommand;
import dk.shadow.minthygge.commands.ingamecommands.hygge.subs.InventoryDropSub;
import dk.shadow.minthygge.commands.ingamecommands.hygge.subs.PushSub;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class HyggeCommand extends ICommand {
    public HyggeCommand(JavaPlugin plugin, String command) {

        super(plugin, command);

        setDefaultCommand(new DefaultCommand());
        addSubCommands(
            new InventoryDropSub(),
            new PushSub()
        );
    }

    @Override
    protected String getCommandName() {
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            if (args.length == 0 && getDefaultCommand() != null) {
                execute(sender, getDefaultCommand(), args);
            } else if (args.length > 0) {
                ISubCommand subCommand = findSubCommand(args[0]);
                if (subCommand != null) {
                    execute(sender, subCommand, args);
                    return true;
                }

                return true;
            }
            return false;
        } catch (Exception e) {
            sender.sendMessage("§cDer skete en fejl under udførelsen af kommandoen: " + command.getName() + " " +
                    String.join(" ", args) + " §8( "+e+" )");
            e.printStackTrace();
            return false;
        }
    }
}
