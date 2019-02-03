package com.mgg;

import com.mgg.dto.InputData;
import com.mgg.dto.SolutionData;
import com.mgg.input.ConsoleInputProcessor;
import com.mgg.input.InputProcessor;
import com.mgg.process.DataProcessor;

public class MainController {

	public static void main(String[] args) {

		InputProcessor inputProcessor = new ConsoleInputProcessor();

		InputData inputData = inputProcessor.getInput();

		DataProcessor dataProcessor = new DataProcessor(inputData);

		SolutionData solutionData = dataProcessor.findSolution();

		solutionData.getAnswers().forEach(System.out::println);
	}
}
