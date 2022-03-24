package com.github.messycraft.namekicker.bukkit;

import com.github.messycraft.namekicker.bukkit.command.nkreload;
import com.github.messycraft.namekicker.bukkit.event.check;
import org.bukkit.plugin.java.JavaPlugin;

public final class Namekicker extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Plugin has been loaded.");
        saveDefaultConfig();
        reloadConfig();
        getCommand("nkreload").setExecutor(new nkreload());
        getServer().getPluginManager().registerEvents(new check(),this);

        Metrics metrics = new Metrics(this,14717);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin has been unloaded.");
    }

    public static String replaceColor(String msg) {
        return msg.replaceAll("&","§");
    }
}
