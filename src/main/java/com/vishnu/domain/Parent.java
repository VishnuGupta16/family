package com.vishnu.domain;

import java.io.Serializable;

public class Parent implements Serializable {

	private static final long serialVersionUID = 1095785879447651548L;

	private final Member mother;

	private final Member father;

	public Parent(final Member mother, final Member father) {
		this.mother = mother;
		this.father = father;
	}

	public Member mother() {
		return mother;
	}

	public Member father() {
		return father;
	}
	
	public void addChildren(Member child) {
		if(this.mother != null)
			this.mother.childrens().add(child);
		
		if(this.father != null)
			this.father.childrens().add(child);
	}

}
