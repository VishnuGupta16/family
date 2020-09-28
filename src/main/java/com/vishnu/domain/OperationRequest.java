package com.vishnu.domain;

import com.vishnu.enums.Commands;
import com.vishnu.enums.Relationship;

public class OperationRequest {

	private Commands command;

	private String operateOn;

	private String name;

	private String gender;

	private Relationship relationship;

	public OperationRequest(Commands command, String operateOn, String name, String gender) {
		this.command = command;
		this.operateOn = operateOn;
		this.name = name;
		this.gender = gender;
	}

	public OperationRequest(Commands command, String operateOn, Relationship relationship) {
		this.command = command;
		this.operateOn = operateOn;
		this.relationship = relationship;
	}

	public Commands getCommand() {
		return this.command;
	}

	public String getOperateOn() {
		return this.operateOn;
	}

	public String getName() {
		return this.name;
	}

	public String getGender() {
		return this.gender;
	}

	public Relationship getRelationship() {
		return this.relationship;
	}
}
