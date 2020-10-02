package com.vishnu;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GeektrustTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}

	@After
	public void restoreStreams() {
	    System.setOut(originalOut);
	    System.setErr(originalErr);
	}

	@Test
	public void testMain() throws IOException, URISyntaxException {
		//Given
		final String inputPath = "./src/test/resource/TestInput/input1.txt";
		final String outputPath = "./src/test/resource/TestInput/output1.txt";
		final String expectedOutput = new String(Files.readAllBytes(Paths.get(outputPath)));

		//Expected
		Geektrust.main(new String[] {inputPath});
		assertEquals(expectedOutput.trim(), outContent.toString().trim());
	}

}
