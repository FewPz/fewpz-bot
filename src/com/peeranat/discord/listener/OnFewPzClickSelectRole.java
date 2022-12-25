package com.peeranat.discord.listener;

import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.interaction.MessageComponentCreateEvent;
import org.javacord.api.interaction.MessageComponentInteraction;
import org.javacord.api.interaction.SelectMenuInteraction;
import org.javacord.api.interaction.callback.InteractionImmediateResponseBuilder;
import org.javacord.api.listener.interaction.MessageComponentCreateListener;

import com.peeranat.discord.FewPzDiscord;

public class OnFewPzClickSelectRole implements MessageComponentCreateListener {

	private FewPzDiscord discord;
	public OnFewPzClickSelectRole(FewPzDiscord discord) {
		// TODO Auto-generated constructor stub
		this.discord = discord;
	}
	
	@Override
	public void onComponentCreate(MessageComponentCreateEvent event) {
		// TODO Auto-generated method stub
		User user = event.getInteraction().getUser();
		MessageComponentInteraction msg = event.getMessageComponentInteraction();
		Message message = msg.getMessage();
		SelectMenuInteraction select = null;
		if (event.getMessageComponentInteraction().asSelectMenuInteraction().isPresent()) {
			select = event.getMessageComponentInteraction().asSelectMenuInteraction().get();		
		}
		String customId = msg.getCustomId();
		switch (customId) {
			case "FEW_ROLE_SELECT":
				String value = select.getChosenOptions().get(0).getValue();
				InteractionImmediateResponseBuilder content = event.getInteraction().createImmediateResponder().setContent("Waiting a role");
				if (user.getRoles(discord.getServer()).size() == 1) {
					Role role = discord.getServer().getRoleById(discord.getCredential().asLong(value)).get();
					discord.getServer().addRoleToUser(user, role);
				}
				content.respond().thenAccept(ex -> {
					ex.delete();
				});
				break;
			case "FEW_ANNOUNCEMENT_CLASSROOM":
				TextChannel textChannel = discord.getTextChannel(discord.getCredential().asLong("SPECIAL_CLASSID"));
				MessageBuilder messageBuilder = message.toMessageBuilder();
				messageBuilder.removeAllComponents();
				messageBuilder.send(textChannel);
				break;
		}
	}

}
