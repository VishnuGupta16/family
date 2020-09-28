package com.vishnu;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import com.vishnu.domain.OperationRequest;
import com.vishnu.enums.Commands;
import com.vishnu.enums.Relationship;
import com.vishnu.service.FamilyTreeService;
import com.vishnu.util.Bootstrap;

public class Geektrust {

	public static void main(String[] args) throws IOException, URISyntaxException {

		FamilyTreeService familyTreeService = FamilyTreeService.getInstance();

		bootstrap(familyTreeService);

		if (args != null && args.length > 0 && args[0] != null && !args[0].isEmpty()) {
			Files.lines(Paths.get(args[0])).forEachOrdered(line -> {
				String[] inputs = line.split(" ");
				OperationRequest request = null;
				if (inputs.length == 4) {
					request = new OperationRequest(Commands.getEnum(inputs[0]), inputs[1], inputs[2], inputs[3]);
				} else if (inputs.length == 3) {
					request = new OperationRequest(Commands.getEnum(inputs[0]), inputs[1],
							Relationship.getEnum(inputs[2]));
				}
				if (request != null)
					System.out.println(familyTreeService.performOperation(request));
			});
		} else
			System.out.println("Please enter input file location");
	}

	private static void bootstrap(FamilyTreeService familyTreeService) throws IOException, URISyntaxException {
		Arrays.asList(Bootstrap.INIT_DATA.split("\n")).stream().forEachOrdered(line -> {
			String[] inputs = line.split(";");
			final OperationRequest request = new OperationRequest(Commands.getEnum(inputs[0]), inputs[1], inputs[2],
					inputs[3]);
			familyTreeService.performOperation(request);
		});
	}

}
