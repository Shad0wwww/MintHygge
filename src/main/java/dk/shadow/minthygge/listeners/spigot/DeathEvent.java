package dk.shadow.minthygge.listeners.spigot;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    @EventHandler
    public void onDeathEvent(PlayerDeathEvent event) {
        Player player = event.getEntity();

        if (!player.isOp()) return;
        if (!player.hasMetadata("inventoryDrop")) return;

        event.setKeepInventory(Boolean.parseBoolean(player.getMetadata("inventoryDrop").get(0).asString()));
    }
}
