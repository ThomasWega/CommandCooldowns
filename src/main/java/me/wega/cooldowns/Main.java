package me.wega.cooldowns;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getCommand("example").setExecutor(new ExampleCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
