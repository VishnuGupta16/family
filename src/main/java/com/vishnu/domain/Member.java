package com.vishnu.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Member implements Serializable {

	private static final long serialVersionUID = -4126414506501853778L;

	private final String name;

	private final String gender;

	private final Parent parent;

	private final List<Member> childrens;

	private Member spouse;

	public Member(final String name, final String gender, final Parent parent) {
		this.name = name;
		this.gender = gender;
		this.parent = parent;
		this.childrens = new LinkedList<Member>();
	}

	public Member(final String name, final String gender, final Parent parent, final List<Member> childrens) {
		this.name = name;
		this.gender = gender;
		this.parent = parent;
		this.childrens = new LinkedList<>(childrens);
		addChildren();
	}

	public Member(final String name, final String gender, final Parent parent, final Member spouse) {
		this.name = name;
		this.gender = gender;
		this.parent = parent;
		this.spouse = spouse;
		spouse.spouse = this;
		this.childrens = new LinkedList<>(spouse.childrens());
		addChildren();
	}

	private void addChildren() {
		if(this.parent != null && this.childrens != null) {
			this.childrens.stream().forEach(child -> this.parent.addChildren(child));
		}
	}

	public String name() {
		return name;
	}

	public String gender() {
		return gender;
	}

	public Parent parent() {
		return parent;
	}

	public List<Member> childrens() {
		return childrens;
	}

	public Member spouse() {
		return spouse;
	}
}
