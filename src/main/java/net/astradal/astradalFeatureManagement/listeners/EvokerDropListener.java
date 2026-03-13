package net.astradal.astradalFeatureManagement.listeners;

import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EvokerDropListener implements Listener {
    private final AstradalFeatureManagement pluginInstance;

    public EvokerDropListener(AstradalFeatureManagement pluginInstance) {
        this.pluginInstance = pluginInstance;
    }


    @EventHandler
    public void onEvokerDeath(EntityDeathEvent event) {

        // 1. Filter out non-Evokers immediately
        if (event.getEntityType() != EntityType.EVOKER) return;

        // 2. Now check the config, since we know it's an Evoker
        if (pluginInstance.configCache.isEvokerTotemDrop()) return;

        // 3. Remove the totem
        event.getDrops().removeIf(item -> item.getType() == Material.TOTEM_OF_UNDYING);

    }
}
