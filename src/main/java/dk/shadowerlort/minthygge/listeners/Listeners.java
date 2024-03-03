package dk.shadowerlort.minthygge.listeners;

import dk.shadowerlort.minthygge.listeners.spigot.DamageEvent;
import dk.shadowerlort.minthygge.listeners.spigot.DeathEvent;
import dk.shadowerlort.minthygge.listeners.spigot.InteractEvent;
import dk.shadowerlort.minthygge.listeners.unikpay.PayRequest;
import dk.shadowerlort.minthygge.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Listeners {
    private static JavaPlugin plugin;

    public static void initialise(JavaPlugin paramPlugin) {
        plugin = paramPlugin;
        register(new InteractEvent(), "PlayerInteractEvent");
        register(new DeathEvent(), "PlayerDeathEvent");
        register(new DamageEvent(), "EntityDamageEvent");
        register(new InteractEvent(), "PlayerInteractEvent");
        register(new PayRequest(), "OnPayRequest");
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