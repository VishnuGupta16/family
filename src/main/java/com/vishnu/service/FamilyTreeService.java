package com.vishnu.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.vishnu.domain.Member;
import com.vishnu.domain.OperationRequest;
import com.vishnu.domain.Parent;
import com.vishnu.enums.Commands;
import com.vishnu.enums.Gender;
import com.vishnu.util.Constants;

public class FamilyTreeService implements Serializable, Cloneable {

	private static final long serialVersionUID = -9154778735223079023L;

	private static FamilyTreeService INSTANCE;

	private final Map<String, Member> nameMap;

	private FamilyTreeService() {
		if (INSTANCE != null)
			throw new RuntimeException(Constants.Message.INSTANCE_ALREADY_EXISTS);
		this.nameMap = new HashMap<String, Member>();
		final Member queen = new Member("Queen Anga", "Female", null);
		this.nameMap.put(queen.name(), queen);
	}

	public static FamilyTreeService getInstance() {
		if (INSTANCE == null) {
			synchronized (FamilyTreeService.class) {
				if (INSTANCE == null)
					INSTANCE = new FamilyTreeService();
			}
		}
		return INSTANCE;
	}

	public Boolean memberExists(final String name) {
		return nameMap.containsKey(name);
	}

	public Member getMember(final String name) {
		if (!memberExists(name))
			throw new RuntimeException(Constants.Message.PERSON_NOT_FOUND);
		return nameMap.get(name);
	}

	public void addMember(final String name, final Member member) {
		nameMap.put(name, member);
	}

	public String performOperation(OperationRequest operationRequest) {
		try {
			if (operationRequest.getCommand() == Commands.ADD_SPOUSE) {
				Member member = getMember(operationRequest.getOperateOn());
				Member spouse = new Member(operationRequest.getName(), operationRequest.getGender(), null, member);
				addMember(spouse.name(), spouse);
				return Constants.Message.SPOUSE_ADDITION_SUCCEEDED;
			} else if (operationRequest.getCommand() == Commands.ADD_CHILD) {
				Member mother = getMember(operationRequest.getOperateOn());
				if(mother.gender().equalsIgnoreCase(Gender.Male.value()))
					return Constants.Message.CHILD_ADDITION_FAILED;
				Member child = new Member(operationRequest.getName(), operationRequest.getGender(),
						new Parent(mother, mother.spouse()));
				child.parent().addChildren(child);
				addMember(child.name(), child);
				return Constants.Message.CHILD_ADDITION_SUCCEEDED;
			} else if (operationRequest.getCommand() == Commands.GET_RELATIONSHIP) {
				Member member = getMember(operationRequest.getOperateOn());
				String result = operationRequest.getRelationship().getRelations(member);
				return result.trim().isEmpty() ? Constants.Message.NONE : result.trim();
			}
		} catch (Exception e) {
			return e.getMessage();
		}
		return Constants.Message.INVALID_COMMAND;
	}

	@Override
	public FamilyTreeService clone() throws CloneNotSupportedException {
		return INSTANCE;
	}

	public Object readResolve() {
		return INSTANCE;
	}
}
