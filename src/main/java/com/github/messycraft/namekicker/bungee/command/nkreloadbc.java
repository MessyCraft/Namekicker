package com.github.messycraft.namekicker.bungee.command;

import com.github.messycraft.namekicker.bungee.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class nkreloadbc extends Command {
    public nkreloadbc() {
        super("nkreloadbc","namekickerbungee.admin","");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Main.reloadConfig();
        sender.sendMessage(new TextComponent(Main.replaceColor(
                "&e[NamekickerBungee] &6Config has been reloaded!")));
    }
}
