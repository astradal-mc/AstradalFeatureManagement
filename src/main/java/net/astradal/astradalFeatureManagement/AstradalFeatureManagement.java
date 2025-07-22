package net.astradal.astradalFeatureManagement;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.astradal.astradalFeatureManagement.listeners.IronGolemSpawnListener;
import net.astradal.astradalFeatureManagement.listeners.ShulkerDuplicationListener;
import net.astradal.astradalFeatureManagement.listeners.VillagerInteractListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class AstradalFeatureManagement extends JavaPlugin {

    @Override
    public void onEnable() {
        saveResource("config.yml", false);

        //hook listeners
        getServer().getPluginManager().registerEvents(new VillagerInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new ShulkerDuplicationListener(this), this);
        getServer().getPluginManager().registerEvents(new IronGolemSpawnListener(this), this);

    }
}
