package dk.shadowerlort.minthygge.commands;

import dk.shadowerlort.minthygge.commands.ingamecommands.hygge.HyggeCommand;
import dk.shadowerlort.minthygge.commands.ingamecommands.hygge.TabCompleteListener;
import dk.shadowerlort.minthygge.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager {
    public static void initialise(JavaPlugin instance) {
        HyggeCommand hyggeCommand = new HyggeCommand(instance, "hygge");

        try {
            Bukkit.getPluginCommand("hygge").setExecutor(hyggeCommand);
            Bukkit.getPluginCommand("hygge").setTabCompleter(new TabCompleteListener(hyggeCommand));

            Bukkit.getConsoleSender().sendMessage(ColorUtils.getColored("   &a - Successful enabled commands"));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ColorUtils.getColored("   &c - Could not enable commands"));
        }


    }
}
