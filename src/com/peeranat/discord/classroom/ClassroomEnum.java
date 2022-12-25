package com.peeranat.discord.classroom;

import java.util.ArrayList;
import java.util.List;

import org.javacord.api.interaction.SlashCommandOptionChoice;

public enum ClassroomEnum {
	
	OOP(0, "OBJECT-ORIENTED PROGRAMMING", "OOP"),
	DATA(1, "DATA STRUCTURES AND ALGORITHMS", "Data Strusture"),
	BUSINESS(2, "BUSINESS FUNDAMENTALS FOR INFORMATION", "Business"),
	DIGITAL(3, "DIGITAL INTELLIGENCE QUOTIENT", "DIQ"),
	PROBABILITY(4, "PROBABILITY AND STATISTICS", "Prob stat"),
	FE(5, "FOUNDATION ENGLISH 2", "FE2"),
	;
	
	private int id;
	private String name;
	private String shortName;
	
	private ClassroomEnum(int id, String name, String shortName) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.name = name;
		this.shortName = shortName;
	}
	
	public String getShortName() {
		return shortName;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public static List<SlashCommandOptionChoice> getChoice() {
		ArrayList<SlashCommandOptionChoice> choice = new ArrayList<SlashCommandOptionChoice>();
		for (ClassroomEnum classroom : values()) {
			choice.add(SlashCommandOptionChoice.create(classroom.getName(), classroom.name()));
		}
		return choice;
	}

}
