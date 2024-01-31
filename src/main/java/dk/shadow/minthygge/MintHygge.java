package dk.shadow.minthygge;

import dk.shadow.minthygge.commands.CommandManager;
import dk.shadow.minthygge.listeners.Listeners;
import dk.shadow.minthygge.utils.ColorUtils;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class MintHygge extends JavaPlugin {

    private static MintHygge instance;
    private static ConsoleCommandSender log;

    @Override
    public void onEnable() {
        instance = this;
        log = getServer().getConsoleSender();
        long timestampBeforeLoad = System.currentTimeMillis();

        log.sendMessage(ColorUtils.getColored("&8&m---------------------------------&r", "", "  &2Enabling &a"+ instance.getName() + " &fv" + getDescription().getVersion()));
        // Plugin startup logic


        log.sendMessage(ColorUtils.getColored("", "  &2Hooking into Commands"));
        CommandManager.initialise(this);

        log.sendMessage(ColorUtils.getColored("", "  &2Hooking into Listeners"));
        Listeners.initialise(this);

        log.sendMessage(ColorUtils.getColored("", "  &f" + instance.getName() + " has been enabled!", "    &aVersion: &f" +
                        getDescription().getVersion(), "    &aAuthors: &f" +
                        getDescription().getAuthors(), "",
                "  &2Took &a" + ( System.currentTimeMillis() - timestampBeforeLoad) + " millis &2to load!", "", "&8&m---------------------------------&r"));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static ConsoleCommandSender getLog() {
        return log;
    }

    public static MintHygge getInstance() {
        return instance;
    }

}
