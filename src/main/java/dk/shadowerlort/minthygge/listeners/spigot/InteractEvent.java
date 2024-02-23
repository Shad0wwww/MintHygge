package dk.shadowerlort.minthygge.listeners.spigot;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import dk.shadowerlort.minthygge.MintHygge;
import dk.shadowerlort.minthygge.utils.WorldGuard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

import static dk.shadowerlort.minthygge.utils.WorldGuard.getWorldGuard;

public class InteractEvent implements Listener {

    // PICK UP PLAYER

    @EventHandler (priority = EventPriority.HIGHEST)
    public void SignInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (event.getClickedBlock().getType() != Material.WALL_SIGN) return;

        Sign sign = (Sign) event.getClickedBlock().getState();

        if (sign.getLine(0).equals("§4§lSOLGT!")) {
            if (ChatColor.stripColor(sign.getLine(1)).equals(event.getPlayer().getName())) {
                String cellRegion = ChatColor.stripColor(sign.getLine(2)).toLowerCase();

                WorldGuardPlugin worldGuard = getWorldGuard();
                if (worldGuard == null) return;
                if (worldGuard.getRegionManager(event.getPlayer().getWorld()).getRegion(cellRegion) == null) return;

                ProtectedRegion region = worldGuard.getRegionManager(event.getPlayer().getWorld()).getRegion(cellRegion);
                assert region != null;

                if (region.getMembers().contains(event.getPlayer().getName())) {
                    return;
                }

                event.getPlayer().sendMessage("§aDu burde gerne kunne åbne din celle igen. Hvis ikke, kontakt en staff.");
                event.getPlayer().sendMessage("§aHusk også at fjerne og tilføje dine medlemmer igen, hvis de skal have adgang.");
                region.getMembers().addPlayer(event.getPlayer().getName());
                event.setCancelled(true);
            }
        }
    }

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

    private final Map<UUID, List<Block>> eggBridges = new HashMap<>();

    @EventHandler (priority = EventPriority.HIGH)
    public void onEggThrow(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp()) {
            return;
        }

        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) return;
        if (event.getItem() == null || event.getItem().getType() != Material.EGG) return;

        if (event.getItem().getType().equals(Material.EGG)) {
            if (event.getItem().getItemMeta() == null) return;

            if (event.getItem().getItemMeta().getDisplayName().equals("§d§lBridge Egg")) {
                event.setCancelled(true);
                Item Item = player.getWorld().dropItem(player.getLocation(), new ItemStack(Material.EGG, 1));
                Item.setVelocity(player.getLocation().getDirection());

                createBridgeTrail(Item, player);
            }
        }
    }

    private void createBridgeTrail(Item item, Player player) {
        UUID eggUUID = item.getUniqueId();
        List<Block> bridgeBlocks = new ArrayList<>();

        new BukkitRunnable() {

            int count = 0;
            final double DISTANCE = 2.0;
            final int WIDTH = 2;
            final double OFFSET_FACTOR = 1.5;

            @Override
            public void run() {
                if (count > 100) {
                    item.remove();
                    removeBridgeTrail(eggUUID);
                    cancel();
                }

                if (item.isValid() && !item.isOnGround() && !item.isDead()) {
                    if (count > 4) {
                        Location spawnLocation = item.getLocation().clone();
                        Vector direction = item.getVelocity().normalize().multiply(-1); // Get direction vector opposite to item's velocity
                        Vector offset = direction.clone().multiply(OFFSET_FACTOR); // Offset to spawn blocks behind the egg

                        for (int i = 0; i < WIDTH; i++) {
                            Location currentLocation = spawnLocation.clone().add(direction.clone().multiply(DISTANCE * i)).add(offset);
                            if (currentLocation.getBlock().getType() != Material.AIR) {
                                continue;
                            }

                            if (currentLocation.distance(player.getLocation()) < 2) {
                                Block blockInfrontOfPlayer = player.getWorld().getBlockAt(player.getLocation().add(player.getLocation().getDirection().multiply(1)));
                                if (blockInfrontOfPlayer.getType() != Material.AIR) {
                                    continue;
                                }
                                blockInfrontOfPlayer.setType(Material.WOOL);
                                bridgeBlocks.add(blockInfrontOfPlayer);
                                continue;
                            }

                            currentLocation.getWorld().getBlockAt(currentLocation).setType(Material.WOOL);
                            currentLocation.getWorld().getBlockAt(currentLocation).setData((byte) new Random().nextInt(16));
                            bridgeBlocks.add(currentLocation.getWorld().getBlockAt(currentLocation));
                        }
                    }
                    count++;
                } else {
                    item.remove();
                    removeBridgeTrail(eggUUID);
                    cancel();
                }
            }
        }.runTaskTimer(MintHygge.getInstance(), 0, 1);

        eggBridges.put(eggUUID, bridgeBlocks);
    }

    private void removeBridgeTrail(UUID eggUUID) {
        List<Block> bridgeBlocks = eggBridges.get(eggUUID);
        new BukkitRunnable() {
            int index = 0;

            @Override
            public void run() {
                if (index < bridgeBlocks.size()) {
                    bridgeBlocks.get(index).setType(Material.AIR);
                    index++;
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(MintHygge.getInstance(), 60, 1);

        eggBridges.remove(eggUUID);
    }
}
