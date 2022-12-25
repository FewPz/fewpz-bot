package com.peeranat.discord.command;

import java.util.List;

import org.javacord.api.entity.user.User;
import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteractionOption;
import org.javacord.api.interaction.callback.InteractionImmediateResponseBuilder;

public interface CommandExecutor {

	public void onCommand(SlashCommandCreateEvent event, List<SlashCommandInteractionOption> args);
	

	public default InteractionImmediateResponseBuilder replyMessage(SlashCommandCreateEvent event) {
		return event.getSlashCommandInteraction().createImmediateResponder();
	}
	
	public default User getUser(SlashCommandCreateEvent event) {
		return event.getSlashCommandInteraction().getUser();
	}
	
}
