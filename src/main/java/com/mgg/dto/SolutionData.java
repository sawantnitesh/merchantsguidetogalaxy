package com.mgg.dto;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mgg.common.Constants.ROMAN_NUMBERS;

public class SolutionData {

	private Map<String, ROMAN_NUMBERS> galacticNumbers;

	private Set<String> metals;

	private Map<String, Float> metalSingleUnitCredits;

	private List<String> answers;

	public Map<String, ROMAN_NUMBERS> getGalacticNumbers() {
		return galacticNumbers;
	}

	public void setGalacticNumbers(Map<String, ROMAN_NUMBERS> galacticNumbers) {
		this.galacticNumbers = galacticNumbers;
	}

	public Set<String> getMetals() {
		return metals;
	}

	public void setMetals(Set<String> metals) {
		this.metals = metals;
	}

	public Map<String, Float> getMetalSingleUnitCredits() {
		return metalSingleUnitCredits;
	}

	public void setMetalSingleUnitCredits(Map<String, Float> metalSingleUnitCredits) {
		this.metalSingleUnitCredits = metalSingleUnitCredits;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

}
