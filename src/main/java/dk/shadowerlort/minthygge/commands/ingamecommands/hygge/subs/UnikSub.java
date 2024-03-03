package dk.shadowerlort.minthygge.commands.ingamecommands.hygge.subs;

import dk.manaxi.unikpay.plugin.API.Internal;
import dk.shadowerlort.minthygge.commands.ISubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnikSub extends ISubCommand {
    public UnikSub() {
        super("Unik");
    }
    @Override
    public void onCommand(CommandSender sender, String[] args, String paramString) {

        if (sender.hasPermission("unikpay.admin") && !sender.isOp()) {
            sender.sendMessage("§cDu har ikke tilladelse til at bruge denne kommando");
            return;
        }

        //loop all players
        for (Player player : Bukkit.getOnlinePlayers()) {
            Internal.sendPayRequest(player, "Test", 50);
        }

        sender.sendMessage("§aUnik betaling sendt");
    }
}