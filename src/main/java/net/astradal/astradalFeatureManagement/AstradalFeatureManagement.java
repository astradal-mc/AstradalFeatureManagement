package net.astradal.astradalFeatureManagement;


import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.astradal.astradalFeatureManagement.commands.AFMCommand;
import net.astradal.astradalFeatureManagement.listeners.ExplosionDamage.EndCrystalDamageListener;
import net.astradal.astradalFeatureManagement.listeners.ExplosionDamage.RespawnAnchorDamageListener;
import net.astradal.astradalFeatureManagement.listeners.IronGolemSpawnListener;
import net.astradal.astradalFeatureManagement.listeners.ShulkerDuplicationListener;
import net.astradal.astradalFeatureManagement.listeners.VillagerInteractListener;

import org.bukkit.plugin.java.JavaPlugin;

public final class AstradalFeatureManagement extends JavaPlugin {

    @Override
    public void onEnable() {
        //load config.yml
        saveDefaultConfig();

        //Register command
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands ->
            commands.registrar().register(AFMCommand.create(this)));

        //Register listeners
        getServer().getPluginManager().registerEvents(new VillagerInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new ShulkerDuplicationListener(this), this);
        getServer().getPluginManager().registerEvents(new IronGolemSpawnListener(this), this);
        getServer().getPluginManager().registerEvents(new EndCrystalDamageListener(this), this);
        getServer().getPluginManager().registerEvents(new RespawnAnchorDamageListener(this), this);
    }
}
