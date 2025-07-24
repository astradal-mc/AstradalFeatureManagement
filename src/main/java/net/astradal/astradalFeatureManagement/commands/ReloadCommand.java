package net.astradal.astradalFeatureManagement.commands;


import com.mojang.brigadier.Command;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.astradal.astradalFeatureManagement.AstradalFeatureManagement;

public class ReloadCommand {

    static LiteralCommandNode<CommandSourceStack> get(AstradalFeatureManagement plugin) {
        return Commands.literal("reload")
            .executes(ctx -> {
                plugin.reloadConfig();
                ctx.getSource().getSender().sendMessage("AFM Reloaded");
                return Command.SINGLE_SUCCESS;
            }).build();
    }
}