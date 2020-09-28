package com.vishnu.enums;

import com.vishnu.util.Constants;

public enum Gender {

	Male("Male"), Female("Female");

	private final String value;

	private Gender(final String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	public static Gender getEnum(String value) {
		for(Gender gender: Gender.values()) {
			if(gender.value().equalsIgnoreCase(value))
				return gender;
		}
		throw new RuntimeException(Constants.Message.NONE);
	}

}
