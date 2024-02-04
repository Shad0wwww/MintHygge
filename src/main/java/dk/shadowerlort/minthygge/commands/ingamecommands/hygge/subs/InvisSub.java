package dk.shadowerlort.minthygge.commands.ingamecommands.hygge.subs;

import dk.shadowerlort.minthygge.MintHygge;
import dk.shadowerlort.minthygge.commands.ISubCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class InvisSub extends ISubCommand {

    HashMap<Player, List<ItemStack>> playerItems = new HashMap<>();

    public InvisSub() {
        super("Invis");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args, String paramString) {
        Player player = (Player) sender;

        if (player.hasMetadata("invis")) {
            player.removeMetadata("invis", MintHygge.getInstance());
            sender.sendMessage("§aDu har nu slået invis fra");

            //remove the invisibility
            player.removePotionEffect(PotionEffectType.INVISIBILITY);

            //return the items
            List<ItemStack> items = playerItems.get(player);
            player.getInventory().setHelmet(items.get(0));
            player.getInventory().setChestplate(items.get(1));
            player.getInventory().setLeggings(items.get(2));
            player.getInventory().setBoots(items.get(3));
            player.getInventory().setItemInHand(items.get(4));

        } else {
            player.setMetadata("invis", new FixedMetadataValue(MintHygge.getInstance(), true));
            sender.sendMessage("§aDu har nu slået invis til");

            player.performCommand("hygge removeArrow");
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, false, false));

            List<ItemStack> items = Arrays.asList(
                    player.getInventory().getHelmet(),
                    player.getInventory().getChestplate(),
                    player.getInventory().getLeggings(),
                    player.getInventory().getBoots(),
                    player.getItemInHand()
            );

            player.getInventory().setHelmet(null);
            player.getInventory().setChestplate(null);
            player.getInventory().setLeggings(null);
            player.getInventory().setBoots(null);
            player.getInventory().setItemInHand(null);

            playerItems.put(player, items);
        }

    }
}
