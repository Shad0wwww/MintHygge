package dk.shadowerlort.minthygge.listeners.spigot;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

public class InteractEvent implements Listener {

    // PICK UP PLAYER
    @EventHandler (priority = EventPriority.HIGH)
    public void onPlayerInteractEvent(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (player.isOp()) {
            Entity target = event.getRightClicked();

            if (target instanceof Player || target.getType().equals(EntityType.PLAYER)) {
                if (player.isSneaking()) {
                    player.setPassenger(target);
                }
            }
        }
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Player passenger = (Player) player.getPassenger();
        if (!player.isOp()) {
            return;
        }

        if (player.getPassenger() == null) {
            return;
        }

        if (!event.getAction().equals(Action.LEFT_CLICK_AIR) && !event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            return;
        }

        if (!player.isSneaking()) {
            return;
        }

        event.setCancelled(true);
        passenger.getVehicle().eject();

        Location location = player.getLocation();
        float yaw = location.getYaw();

        if (location.getPitch() > 70 && -70 < location.getPitch()) {
            location.setPitch((float) -3.8);
            location.setYaw(yaw);
            passenger.setVelocity(location.getDirection().multiply(15));
        }

        if (location.getPitch() > 90 && 70 < location.getPitch()) {
            Vector velocity = player.getLocation().getDirection().multiply(15).setY(1);
            passenger.setVelocity(velocity);
        }
        if (location.getPitch() > -90 && -70 < location.getPitch()) {
            Vector velocity = player.getLocation().getDirection().multiply(15).setY(1);
            passenger.setVelocity(velocity);
        }
    }
}
