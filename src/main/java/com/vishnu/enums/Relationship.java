package com.vishnu.enums;

import java.util.stream.Collectors;

import com.vishnu.domain.Member;
import com.vishnu.util.Constants;

public enum Relationship {

	PATERNAL_UNCLE(Constants.Relationship.PATERNAL_UNCLE) {
		@Override
		public String getRelations(Member member) {
			if (member.parent() == null || member.parent().father() == null || member.parent().father().parent() == null
					|| member.parent().father().parent().mother() == null)
				return Constants.Message.NONE;
			return member.parent().father().parent().mother().childrens().stream()
					.filter(sibling -> !sibling.name().equalsIgnoreCase(member.parent().father().name())
							&& sibling.gender().equalsIgnoreCase(Gender.Male.value()))
					.map(sibling -> sibling.name()).collect(Collectors.joining(" "));
		}
	},
	MATERNAL_UNCLE(Constants.Relationship.MATERNAL_UNCLE) {
		@Override
		public String getRelations(Member member) {
			if (member.parent() == null || member.parent().mother() == null || member.parent().mother().parent() == null
					|| member.parent().mother().parent().mother() == null)
				return Constants.Message.NONE;
			return member.parent().mother().parent().mother().childrens().stream()
					.filter(sibling -> sibling.gender().equalsIgnoreCase(Gender.Male.value()))
					.map(sibling -> sibling.name()).collect(Collectors.joining(" "));
		}
	},
	PATERNAL_AUNT(Constants.Relationship.PATERNAL_AUNT) {
		@Override
		public String getRelations(Member member) {
			if (member.parent() == null || member.parent().father() == null || member.parent().father().parent() == null
					|| member.parent().father().parent().mother() == null)
				return Constants.Message.NONE;
			return member.parent().father().parent().mother().childrens().stream()
					.filter(sibling -> sibling.gender().equalsIgnoreCase(Gender.Female.value()))
					.map(sibling -> sibling.name()).collect(Collectors.joining(" "));
		}
	},
	MATERNAL_AUNT(Constants.Relationship.MATERNAL_AUNT) {
		@Override
		public String getRelations(Member member) {
			if (member.parent() == null || member.parent().mother() == null || member.parent().mother().parent() == null
					|| member.parent().mother().parent().mother() == null)
				return Constants.Message.NONE;
			return member.parent().mother().parent().mother().childrens().stream()
					.filter(sibling -> sibling.gender().equalsIgnoreCase(Gender.Female.value())
							&& !sibling.name().equalsIgnoreCase(member.parent().mother().name()))
					.map(sibling -> sibling.name()).collect(Collectors.joining(" "));
		}
	},
	SISTER_IN_LAW(Constants.Relationship.SISTER_IN_LAW) {
		@Override
		public String getRelations(Member member) {
			String spouseSisters = "";
			if (member.spouse() != null && member.spouse().parent() != null
					&& member.spouse().parent().mother() != null) {
				spouseSisters = member.spouse().parent().mother().childrens().stream()
						.filter(sibling -> !sibling.name().equalsIgnoreCase(member.spouse().name())).map(sibling -> {
							if (sibling.gender().equalsIgnoreCase(Gender.Female.value()))
								return sibling.name();
//							if (sibling.gender().equalsIgnoreCase(Gender.Male.value()) && sibling.spouse() != null)
//								return sibling.spouse().name();
							return "";
						}).filter(name -> !name.isEmpty()).collect(Collectors.joining(" "));
			}

			String wivesOfBrother = "";
			if (member.parent() != null && member.parent().mother() != null) {
				wivesOfBrother = member.parent().mother().childrens().stream()
						.filter(sibling -> sibling.gender().equalsIgnoreCase(Gender.Male.value())
								&& !sibling.name().equalsIgnoreCase(member.name()))
						.map(sibling -> sibling.spouse().name()).collect(Collectors.joining(" "));
			}

			return (spouseSisters.trim() + wivesOfBrother.trim()).trim().isEmpty() ? Constants.Message.NONE
					: (spouseSisters.trim() + wivesOfBrother.trim()).trim();
		}
	},
	BROTHER_IN_LAW(Constants.Relationship.BROTHER_IN_LAW) {
		@Override
		public String getRelations(Member member) {
			String spouseBrothers = "";
			if (member.spouse() != null && member.spouse().parent() != null
					&& member.spouse().parent().mother() != null) {
				spouseBrothers = member.spouse().parent().mother().childrens().stream()
						.filter(sibling -> !sibling.name().equalsIgnoreCase(member.name())
								&& !sibling.name().equalsIgnoreCase(member.spouse().name()))
						.map(sibling -> {
							if (sibling.gender().equalsIgnoreCase(Gender.Male.value())) {
								return sibling.name();
							}
//							if (sibling.gender().equalsIgnoreCase(Gender.Female.value()) && sibling.spouse() != null
//									&& !sibling.spouse().name().equalsIgnoreCase(member.name()))
//								return sibling.spouse().name();
							return "";
						}).filter(name -> !name.isEmpty()).collect(Collectors.joining(" "));
			}

			String hubandsOfSisters = "";
			if (member.parent() != null && member.parent().mother() != null) {
				hubandsOfSisters = member.parent().mother().childrens().stream()
						.filter(sibling -> sibling.gender().equalsIgnoreCase(Gender.Female.value())
								&& sibling.spouse() != null && !sibling.name().equalsIgnoreCase(member.name()))
						.map(sibling -> sibling.spouse().name()).collect(Collectors.joining(" "));
			}
			return (spouseBrothers.trim() + hubandsOfSisters.trim()).trim();

		}
	},
	SON(Constants.Relationship.SON) {
		@Override
		public String getRelations(Member member) {
			return member.childrens().stream()
					.filter(sibling -> sibling.gender().equalsIgnoreCase(Gender.Male.value())
							&& !sibling.name().equalsIgnoreCase(member.name()))
					.map(sibling -> sibling.name()).collect(Collectors.joining(" "));
		}
	},
	DAUGHTER(Constants.Relationship.DAUGHTER) {
		@Override
		public String getRelations(Member member) {
			return member.childrens().stream()
					.filter(sibling -> sibling.gender().equalsIgnoreCase(Gender.Female.value()))
					.map(sibling -> sibling.name()).collect(Collectors.joining(" "));
		}
	},
	SIBLINGS(Constants.Relationship.SIBLINGS) {
		@Override
		public String getRelations(Member member) {
			if (member.parent() == null || member.parent().mother() == null)
				return Constants.Message.NONE;
			return member.parent().mother().childrens().stream()
					.filter(sibling -> !sibling.name().equalsIgnoreCase(member.name())
							&& !sibling.name().equalsIgnoreCase(member.name()))
					.map(sibling -> sibling.name()).collect(Collectors.joining(" "));
		}
	};

	private final String value;

	private Relationship(final String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	public abstract String getRelations(Member member);

	public static Relationship getEnum(String value) {
		for (Relationship relationship : Relationship.values()) {
			if (relationship.value().equalsIgnoreCase(value))
				return relationship;
		}
		throw new RuntimeException(Constants.Message.NONE);
	}

}
