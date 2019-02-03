package com.mgg.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.mgg.dto.InputData;

public class ConsoleInputProcessor implements InputProcessor {

	@Override
	public InputData getInput() {

		Scanner scanner = new Scanner(System.in);

		List<String> inputLines = new ArrayList<String>();

		while (scanner.hasNextLine()) {

			String line = scanner.nextLine();
			
			if (line.equals("")) {
				break;
			}
			
			inputLines.add(line);
		}

		scanner.close();

		List<String> facts = inputLines.stream().filter(line -> !line.contains("?")).collect(Collectors.toList());
		List<String> questions = inputLines.stream().filter(line -> line.contains("?")).collect(Collectors.toList());

		return new InputData(facts, questions);
	}

}
