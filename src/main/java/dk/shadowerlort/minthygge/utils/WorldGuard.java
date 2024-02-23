package dk.shadowerlort.minthygge.utils;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import static org.bukkit.Bukkit.getServer;

public class WorldGuard {
    public static WorldGuardPlugin getWorldGuard() {
        return (WorldGuardPlugin) getServer().getPluginManager().getPlugin("WorldGuard");
    }
}
