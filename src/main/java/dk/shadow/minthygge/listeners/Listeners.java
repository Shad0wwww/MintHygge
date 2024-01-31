package dk.shadow.minthygge.listeners;

import dk.shadow.minthygge.listeners.spigot.DeathEvent;
import dk.shadow.minthygge.listeners.spigot.InteractEvent;
import dk.shadow.minthygge.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Listeners {
    private static JavaPlugin plugin;

    public static void initialise(JavaPlugin paramPlugin) {
        plugin = paramPlugin;
        register(new InteractEvent(), "PlayerInteractEvent");
        register(new DeathEvent(), "PlayerDeathEvent");

    }

    private static void register(Listener listener, String name) {
        try {
            Bukkit.getPluginManager().registerEvents(listener, plugin);
            Bukkit.getConsoleSender().sendMessage(ColorUtils.getColored("   &a - " + name));
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ColorUtils.getColored("   &c - " + name));
            throw new RuntimeException();
        }
    }
}