package net.astradal.astradalFeatureManagement.listeners;

import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.Objects;

public class VillagerInteractListener implements Listener {

    //plugin instance constructor injection
    public VillagerInteractListener(AstradalFeatureManagement plugin) {
        this.pluginInstance = plugin;
    }
    private final AstradalFeatureManagement pluginInstance;

    private Component getStringFromConfig(String type) {
        return switch (type) {
            case "Deny" -> LegacyComponentSerializer.legacyAmpersand().deserialize(
                    Objects.requireNonNull(pluginInstance.getConfig().getString("villager-trades.deny-message")));
            case "Bypass" -> LegacyComponentSerializer.legacyAmpersand().deserialize(
                    Objects.requireNonNull(pluginInstance.getConfig().getString("villager-trades.bypass-message"))
            );
            default -> {
                throw new IllegalStateException("Unexpected value: " + type);
            }
        };
    }

    @EventHandler
    public void onPlayerVillagerInteract(PlayerInteractEntityEvent event) {
        //make sure villager trades are not enabled
        if (pluginInstance.getConfig().getBoolean("villager-trades.enabled")) {
            return;
        }

        //make sure the interacted entity is a villager
        if (event.getRightClicked().getType() != EntityType.VILLAGER) {
            return;
        }

        //make sure the villager isn't unemployed
        Villager villager = (Villager) event.getRightClicked();
        if (villager.getProfession() == Villager.Profession.NONE) {
            return;
        }

        //make sure the interacting player doesn't have bypass permissions
        Player player = event.getPlayer();
        if (player.hasPermission("astradalFeatureManagement.villagerTrading.bypass")) {
            //tell them they're bypassing the restrictions
            player.sendMessage(getStringFromConfig("Bypass"));

            //log to console
            pluginInstance.getLogger().info(player.getName() + "is bypassing villager trade restrictions.");
            return;
        }

        //stop the player from interacting with the villager
        event.setCancelled(true);

        //tell them they can't trade with villagers
        player.sendMessage(getStringFromConfig("Deny"));

        //log to console
        pluginInstance.getLogger().info(player.getName() + "has been prevented from trading.");
    }
}
