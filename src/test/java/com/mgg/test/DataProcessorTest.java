package com.mgg.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mgg.dto.InputData;
import com.mgg.dto.SolutionData;
import com.mgg.process.DataProcessor;

public class DataProcessorTest {

	@BeforeAll
	static void beforeAll() {

	}

	@AfterAll
	static void afterAll() {

	}

	@BeforeEach
	void beforeEach() {
		System.out.println("**--- Executed before each test method in this class ---**");
	}

	@AfterEach
	void afterEach() {
		System.out.println("**--- Executed before each test method in this class ---**");
	}

	@Test
	void test1() {

		List<String> facts = new ArrayList<>();
		List<String> questions = new ArrayList<>();
		facts.add("glob is I");
		facts.add("prok is V");
		facts.add("pish is X");
		facts.add("tegj is L");
		facts.add("glob glob Silver is 34 Credits");
		facts.add("glob prok Gold is 57800 Credits");
		facts.add("pish pish Iron is 3910 Credits");
		questions.add("how much is pish tegj glob glob ?");
		questions.add("how many Credits is glob prok Silver ?");
		questions.add("how many Credits is glob prok Gold ?");
		questions.add("how many Credits is glob prok Iron ?");
		questions.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");

		DataProcessor dataProcessor = new DataProcessor(new InputData(facts, questions));

		SolutionData solutionData = dataProcessor.findSolution();

		List<String> answers = solutionData.getAnswers();

		assertEquals(answers.get(0), "pish tegj glob glob is 42");
		assertEquals(answers.get(1), "glob prok Silver is 68 Credits");
		assertEquals(answers.get(2), "glob prok Gold is 57800 Credits");
		assertEquals(answers.get(3), "glob prok Iron is 782 Credits");
		assertEquals(answers.get(4), "I have no idea what you are talking about");
	}

	@Test
	void test2() {

		List<String> facts = new ArrayList<>();
		List<String> questions = new ArrayList<>();
		facts.add("glob is I");
		facts.add("prok is V");
		facts.add("pish is X");
		facts.add("tegj is L");
		facts.add("glob glob Silver is 50 Credits");
		facts.add("prok prok Gold is 500000 Credits");
		questions.add("how many Credits is glob glob Silver ?");
		questions.add("how many Credits is glob prok Silver ?");
		questions.add("it is invalid question 1 ?");
		questions.add("how many Credits is glob prok Gold ?");
		questions.add("how many Credits is prok prok Gold ?");
		questions.add("it is invalid question 2 ?");

		DataProcessor dataProcessor = new DataProcessor(new InputData(facts, questions));

		SolutionData solutionData = dataProcessor.findSolution();

		List<String> answers = solutionData.getAnswers();

		assertEquals(answers.get(0), "glob glob Silver is 50 Credits");
		assertEquals(answers.get(1), "glob prok Silver is 100 Credits");
		assertEquals(answers.get(2), "I have no idea what you are talking about");
		assertEquals(answers.get(3), "glob prok Gold is 200000 Credits");
		assertEquals(answers.get(4), "prok prok Gold is 500000 Credits");
		assertEquals(answers.get(5), "I have no idea what you are talking about");
	}
}
