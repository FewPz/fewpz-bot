package com.peeranat.discord.listener;

import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.interaction.MessageComponentCreateEvent;
import org.javacord.api.interaction.MessageComponentInteraction;
import org.javacord.api.interaction.SelectMenuInteraction;
import org.javacord.api.interaction.callback.InteractionImmediateResponseBuilder;
import org.javacord.api.listener.interaction.MessageComponentCreateListener;

import com.peeranat.discord.FewPzDiscord;

public class OnFewPzClickSelectRoleEvent implements MessageComponentCreateListener {

	private FewPzDiscord discord;
	public OnFewPzClickSelectRoleEvent(FewPzDiscord discord) {
		// TODO Auto-generated constructor stub
		this.discord = discord;
	}
	
	@Override
	public void onComponentCreate(MessageComponentCreateEvent event) {
		// TODO Auto-generated method stub
		User user = event.getInteraction().getUser();
		MessageComponentInteraction msg = event.getMessageComponentInteraction();
		SelectMenuInteraction select = event.getMessageComponentInteraction().asSelectMenuInteraction().get();
		String customId = msg.getCustomId();
		switch (customId) {
			case "FEW_ROLE_SELECT":
				String value = select.getChosenOptions().get(0).getValue();
				if (user.getRoles(discord.getServer()).size() == 1) {
					Role role = discord.getServer().getRoleById(discord.getCredential().asLong(value)).get();
					discord.getServer().addRoleToUser(user, role);
					InteractionImmediateResponseBuilder content = event.getInteraction().createImmediateResponder().setContent("Waiting a role");
					content.respond().thenAccept(ex -> {
						ex.delete();
					});
				} else {
					InteractionImmediateResponseBuilder content = event.getInteraction().createImmediateResponder().setContent("Waiting a role");
					content.respond().thenAccept(ex -> {
						ex.delete();
					});
				}
				break;
		}
	}

}
