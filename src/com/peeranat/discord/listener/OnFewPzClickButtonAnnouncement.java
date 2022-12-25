package com.peeranat.discord.listener;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.event.interaction.MessageComponentCreateEvent;
import org.javacord.api.interaction.MessageComponentInteraction;
import org.javacord.api.listener.interaction.MessageComponentCreateListener;

import com.peeranat.discord.FewPzDiscord;

public class OnFewPzClickButtonAnnouncement implements MessageComponentCreateListener {

	private FewPzDiscord discord;
	public OnFewPzClickButtonAnnouncement(FewPzDiscord discord) {
		// TODO Auto-generated constructor stub
		this.discord = discord;
	}
	
	@Override
	public void onComponentCreate(MessageComponentCreateEvent event) {
		// TODO Auto-generated method stub
		TextChannel textChannel = discord.getTextChannel(discord.getCredential().asLong("DEV_ID"));
		MessageComponentInteraction mci = event.getMessageComponentInteraction();
		Message message = mci.getMessage();
		String customId = mci.getCustomId();
		switch (customId) {
			case "FEW_ANNOUNCEMENT_CLASSROOM":
				message.getComponents().clear();
				MessageBuilder messageBuilder = message.toMessageBuilder();
				messageBuilder.send(textChannel);
				break;
		}
	}

}
