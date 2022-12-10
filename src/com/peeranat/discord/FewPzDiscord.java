package com.peeranat.discord;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;

import com.peeranat.discord.credential.Credential;
import com.peeranat.discord.listener.OnFewPzClickSelectRoleEvent;

public class FewPzDiscord {
	
	private Credential credential;
	private DiscordApi discordApi;
	private boolean isEnable;
	private String token;
	private long serverId;
	
	public FewPzDiscord() {
		credential = new Credential();
		token = credential.asString("TOKEN");
		serverId = credential.asLong("SERVER_ID");
	}
	
	protected void start() {
		try {
			discordApi = new DiscordApiBuilder().setToken(token).login().join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		isEnable = true;
		
		discordApi.addListener(new OnFewPzClickSelectRoleEvent(this));
	}
	
	protected void stop() {
		discordApi.disconnect();
		discordApi = null;
	}
	
	public DiscordApi getDiscord() {
		return discordApi;
	}
	
	public boolean isEnable() {
		return isEnable;
	}
	
	public Server getServer() {
		return discordApi.getServerById(serverId).get();
	}
	
	public TextChannel getTextChannel(long channelId) {
		TextChannel textChannel = discordApi.getTextChannelById(channelId).get();
		return textChannel;
	}
	
	public Credential getCredential() {
		return credential;
	}
	
}
