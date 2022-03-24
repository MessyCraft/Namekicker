package com.github.messycraft.namekicker.bukkit.command;

import com.github.messycraft.namekicker.bukkit.Namekicker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class nkreload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Namekicker.getProvidingPlugin(Namekicker.class).reloadConfig();
        sender.sendMessage(Namekicker.replaceColor(
                "&e[Namekicker] &6Config has been reloaded!"));
        return false;
    }
}
