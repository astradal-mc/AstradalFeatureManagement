package net.astradal.astradalFeatureManagement.commands;


import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;

public class ReloadCommand implements Command<CommandSourceStack> {

    private final AstradalFeatureManagement plugin;
    public ReloadCommand(AstradalFeatureManagement plugin) {
        this.plugin = plugin;
    }

    @Override
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        plugin.reloadConfig();
        context.getSource().getSender().sendMessage("AFM Reloaded");
        return Command.SINGLE_SUCCESS;
    }
}