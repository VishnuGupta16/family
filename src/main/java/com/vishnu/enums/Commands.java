package com.vishnu.enums;

import com.vishnu.util.Constants;

public enum Commands {
	
	GET_RELATIONSHIP(Constants.Commands.GET_RELATIONSHIP),
	ADD_CHILD(Constants.Commands.ADD_CHILD),
	ADD_SPOUSE(Constants.Commands.ADD_SPOUSE);
	
	private final String value;

	private Commands(final String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}
	
	public static Commands getEnum(String value) {
		for(Commands command: Commands.values()) {
			if(command.value().equalsIgnoreCase(value))
				return command;
		}
			throw new RuntimeException(Constants.Message.NONE);
	}
}
