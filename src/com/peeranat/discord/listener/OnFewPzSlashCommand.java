package com.peeranat.discord.listener;

import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;

import com.peeranat.discord.FewPzDiscord;

public class OnFewPzSlashCommand implements SlashCommandCreateListener {
	
	private FewPzDiscord discord;
	
	public OnFewPzSlashCommand(FewPzDiscord discord) {
		this.discord = discord;
	}

	@Override
	public void onSlashCommandCreate(SlashCommandCreateEvent event) {
		if (discord != null && discord.isEnable()) {
			discord.getCommand().doExecute(event);
		}
	}

}
