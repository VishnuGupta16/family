package com.vishnu.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.vishnu.domain.OperationRequest;
import com.vishnu.enums.Commands;
import com.vishnu.enums.Gender;
import com.vishnu.enums.Relationship;
import com.vishnu.util.Constants;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FamilyTreeServiceTest {

	static FamilyTreeService familyTreeService;

	@BeforeClass
	public static void init() {
		familyTreeService = FamilyTreeService.getInstance();
	}

	@Test
	public void testGetInstance() {
		// Expected
		assertNotNull(familyTreeService);
	}

	@Test
	public void testQueenPresent() {
		// Given
		final String queenName = "Queen Anga";

		// Expected
		assertEquals(queenName, familyTreeService.getMember(queenName).name());
	}

	@Test
	public void testAddSpouse() {
		// Given
		final String queenName = "Queen Anga";
		final String kingName = "King Shan";
		OperationRequest operationRequest = new OperationRequest(Commands.ADD_SPOUSE, queenName, kingName,
				Gender.Male.value());

		// Expected
		assertEquals(Constants.Message.SPOUSE_ADDITION_SUCCEEDED, familyTreeService.performOperation(operationRequest));
	}

	@Test
	public void testGetRelaionshipSonByFather() {
		// Given
		final String kingName = "King Shan";
		final String name = "Chit Ish Vich Aras";
		OperationRequest operationRequest = new OperationRequest(Commands.GET_RELATIONSHIP, kingName, Relationship.SON);

		// Expected
		assertEquals(name, familyTreeService.performOperation(operationRequest));
	}

	@Test
	public void testGetRelaionshipSonByMother() {
		// Given
		final String queenName = "Queen Anga";
		final String name = "Chit Ish Vich Aras";
		OperationRequest operationRequest = new OperationRequest(Commands.GET_RELATIONSHIP, queenName,
				Relationship.SON);

		// Expected
		assertEquals(name, familyTreeService.performOperation(operationRequest));
	}

	@Test
	public void testGetRelaionshipPaternalUncle() {
		// Given
		final String queenName = "Vasa";
		final String name = "Vyas Ketu Sanjay";
		OperationRequest operationRequest = new OperationRequest(Commands.GET_RELATIONSHIP, queenName,
				Relationship.PATERNAL_UNCLE);

		// Expected
		assertEquals(name, familyTreeService.performOperation(operationRequest));
	}

	@Test
	public void testGetRelaionshipMaternalUncle() {
		// Given
		final String queenName = "Vasa";
		final String name = "NONE";
		OperationRequest operationRequest = new OperationRequest(Commands.GET_RELATIONSHIP, queenName,
				Relationship.MATERNAL_UNCLE);

		// Expected
		assertEquals(name, familyTreeService.performOperation(operationRequest));
	}

	@Test
	public void testAddChild() {
		// Given
		final String motherName = "Satya";
		final String name = "Sanjay";
		OperationRequest operationRequest = new OperationRequest(Commands.ADD_CHILD, motherName, name,
				Gender.Male.value());

		// Expected
		assertEquals(Constants.Message.CHILD_ADDITION_SUCCEEDED, familyTreeService.performOperation(operationRequest));
	}

}
