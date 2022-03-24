package com.github.messycraft.namekicker.bungee;

import com.github.messycraft.namekicker.bungee.command.nkreloadbc;
import com.github.messycraft.namekicker.bungee.event.check;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class Main extends Plugin {
    public static Configuration config;
    public static Main instance;

    @Override
    public void onEnable() {
        getLogger().info("[Namekicker] Plugin has been loaded.");
        instance = this;
        saveDefaultConfig();
        reloadConfig();
        getProxy().getPluginManager().registerCommand(this,new nkreloadbc());
        getProxy().getPluginManager().registerListener(this,new check());

        Metrics metrics = new Metrics(this,14718);
    }

    @Override
    public void onDisable() {
        getLogger().info("[Namekicker] Plugin has been unloaded.");
    }

    public void saveDefaultConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File file = new File(getDataFolder(),"config.yml");
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in,file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void reloadConfig() {
        File file = new File(instance.getDataFolder(),"config.yml");
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String replaceColor(String msg) {
        return msg.replaceAll("&","§");
    }
}
