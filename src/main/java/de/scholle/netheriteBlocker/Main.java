package de.scholle.netheriteBlocker;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        // Standard-Konfiguration laden oder erstellen
        saveDefaultConfig();

        // Events registrieren
        getServer().getPluginManager().registerEvents(new NetheriteBlocker(this), this);

        getLogger().info("NetheriteBlocker aktiviert!");
    }

    @Override
    public void onDisable() {
        getLogger().info("NetheriteBlocker deaktiviert!");
    }

    public static Main getInstance() {
        return instance;
    }
}
