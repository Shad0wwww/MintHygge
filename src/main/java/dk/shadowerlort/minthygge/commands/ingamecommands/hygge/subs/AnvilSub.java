package dk.shadowerlort.minthygge.commands.ingamecommands.hygge.subs;

import dk.shadowerlort.minthygge.MintHygge;
import dk.shadowerlort.minthygge.commands.ISubCommand;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AnvilSub extends ISubCommand {
    public AnvilSub() {
        super("anvil");
    }

    @Override
    public void onCommand(CommandSender sender, String[] args, String paramString) {

        if (args.length == 1) {
            sender.sendMessage("§cDu skal skrive <player> <number of anvils>");
            return;
        }

        Player player = (Player) sender;
        Player target = player.getServer().getPlayer(args[0]);

        int amount;
        try {
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.sendMessage("§cUgyldigt antal annuller");
            return;
        }

        if (amount <= 0) {
            sender.sendMessage("§cAntallet af ambolter skal være større end 0");
            return;
        }



        new BukkitRunnable() {
            int count = 0;

            @Override
            public void run() {
                if (count < amount || target.isOnline()) {

                    target.playSound(target.getLocation(), Sound.ANVIL_LAND, 10, 1);
                    count++;
                } else {
                    cancel(); // Stop the BukkitRunnable when all sounds are played
                }
            }
        }.runTaskTimer(MintHygge.getInstance(), 20L, 20L); // 20 ticks = 1 second




    }
}
