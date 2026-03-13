package net.astradal.astradalFeatureManagement.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;

public class ReloadCommand implements Command<CommandSourceStack> {

    private final AstradalFeatureManagement plugin;

    public ReloadCommand(AstradalFeatureManagement plugin) {
        this.plugin = plugin;
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) {
        // 1. Tell Bukkit to read the config.yml file from the disk again
        plugin.reloadConfig();

        // 2. Tell your cache to pull those new values into fast memory
        plugin.configCache.updateCache();

        context.getSource().getSender().sendMessage("AFM Reloaded");
        return Command.SINGLE_SUCCESS;
    }
}