package com.mgg.process;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mgg.common.Constants.ROMAN_NUMBERS;
import com.mgg.common.Util;
import com.mgg.dto.InputData;
import com.mgg.dto.SolutionData;

public class DataProcessor {

	private InputData inputData;

	public DataProcessor(InputData inputData) {

		this.setInputData(inputData);
	}

	public InputData getInputData() {
		return inputData;
	}

	public void setInputData(InputData inputData) {
		this.inputData = inputData;
	}

	public SolutionData findSolution() {

		SolutionData solutionData = parseInputData(inputData);

		findAnswer(inputData, solutionData);

		return solutionData;
	}

	private SolutionData parseInputData(InputData inputData) {

		SolutionData solutionData = new SolutionData();

		setGalacticNumbers(inputData, solutionData);

		setMetalSingleUnitCredits(inputData, solutionData);

		return solutionData;
	}

	private void setGalacticNumbers(InputData inputData, SolutionData solutionData) {

		List<String> facts = inputData.getFacts();

		Map<String, ROMAN_NUMBERS> galacticNumbers = new HashMap<>();

		for (String fact : facts) {

			String[] s = fact.split("(?i)\\sis\\s");

			if (s.length == 2 && !fact.toUpperCase().contains(" CREDITS")) {

				galacticNumbers.put(s[0], ROMAN_NUMBERS.valueOf(s[1].trim()));

			} else {
				break;
			}
		}

		solutionData.setGalacticNumbers(Collections.unmodifiableMap(galacticNumbers));
	}

	private void setMetalSingleUnitCredits(InputData inputData, SolutionData solutionData) {

		List<String> facts = inputData.getFacts();

		Set<String> metals = new HashSet<>();

		Map<String, Float> metalSingleUnitCredits = new HashMap<>();

		for (String fact : facts) {

			if (fact.toUpperCase().contains("CREDITS")) {

				String[] s = fact.split("(?i)((\\sIS\\s)|(\\sCREDITS\\s?))");

				String[] romanNumberWithMetal = s[0].split("\\s+");

				String metal = romanNumberWithMetal[romanNumberWithMetal.length - 1];

				String galacticNumber = s[0].trim().substring(0, s[0].trim().lastIndexOf(" ")).trim();

				String romanNumber = Util.convertGalacticToRomanNumber(galacticNumber,
						solutionData.getGalacticNumbers());

				float credits = Float.parseFloat(s[1].trim());

				int arabicValue = Util.convertRomanToArabic(romanNumber);

				float unitCredit = credits / arabicValue;

				metals.add(metal);

				metalSingleUnitCredits.put(metal, unitCredit);
			}
		}

		solutionData.setMetals(metals);
		solutionData.setMetalSingleUnitCredits(Collections.unmodifiableMap(metalSingleUnitCredits));
	}

	private void findAnswer(InputData inputData, SolutionData solutionData) {

		List<String> questions = inputData.getQuestions();

		solutionData.setAnswers(new ArrayList<String>());

		Set<String> metals = solutionData.getMetals();

		questions.forEach(question -> {

			solutionData.getAnswers().add(findAnswer(question, solutionData));
		});
	}

	private String findAnswer(String question, SolutionData solutionData) {

		Map<String, ROMAN_NUMBERS> galacticNumbers = solutionData.getGalacticNumbers();
		Set<String> galacticNumbersKeys = solutionData.getGalacticNumbers().keySet();
		Set<String> metals = solutionData.getMetals();
		Map<String, Float> metalSingleUnitCredits = solutionData.getMetalSingleUnitCredits();

		boolean isQuestionValid = isQuestionValid(question, galacticNumbersKeys);

		if (isQuestionValid) {

			String questionPart = question.substring(question.indexOf(" is ") + 4, question.indexOf("?")).trim();
			String[] tokens = questionPart.split("\\s+");

			boolean isMetalCreditQuestion = isMetalCreditQuestion(question, metals);

			String galacticNumber = null;
			String metal = null;

			if (isMetalCreditQuestion) {

				metal = tokens[tokens.length - 1];
				galacticNumber = questionPart.substring(0, questionPart.indexOf(metal)).trim();

			} else {

				galacticNumber = questionPart;
			}

			String romanNumber = Util.convertGalacticToRomanNumber(galacticNumber, galacticNumbers);

			float arabicValue = Util.convertRomanToArabic(romanNumber);

			if (isMetalCreditQuestion) {

				arabicValue = arabicValue * metalSingleUnitCredits.get(metal);
			}

			String arabicValueString = new DecimalFormat("#.##").format(arabicValue);

			String answer = questionPart + " is " + arabicValueString;

			if (isMetalCreditQuestion) {

				answer = answer + " Credits";
			}

			return answer;
		}

		return "I have no idea what you are talking about";
	}

	private boolean isQuestionValid(String question, Set<String> galacticNumbersKeys) {

		String[] tokens = question.split("\\s+");

		for (String t : tokens) {

			if (galacticNumbersKeys.contains(t)) {

				return true;
			}
		}

		return false;
	}

	private boolean isMetalCreditQuestion(String question, Set<String> metals) {

		String[] tokens = question.split("\\s+");

		for (String t : tokens) {

			if (metals.contains(t)) {

				return true;
			}
		}

		return false;
	}
}
