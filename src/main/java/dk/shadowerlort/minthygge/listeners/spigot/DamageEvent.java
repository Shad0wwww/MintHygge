package dk.shadowerlort.minthygge.listeners.spigot;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.lang.reflect.Field;
import java.util.Arrays;

public class DamageEvent implements Listener {

    @EventHandler (priority = org.bukkit.event.EventPriority.HIGHEST)
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            if (player.hasMetadata("noKB")) {

                if (player.getHealth() - event.getFinalDamage() <= 0) {
                    return;
                }

                event.setCancelled(true);
                player.damage(3);
            }
        }
    }
}

