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
        if(pluginInstance.getConfig().getBoolean("evoker-totem-drop")) return; // skip if the config is set to enable this.

        if (event.getEntityType() == EntityType.EVOKER) {
            event.getDrops().removeIf(item -> item.getType() == Material.TOTEM_OF_UNDYING);
        }
    }
}
