package com.mgg.dto;

import java.util.Collections;
import java.util.List;

public class InputData {

	private List<String> facts;

	private List<String> questions;

	public InputData(List<String> facts, List<String> questions) {

		this.facts = Collections.unmodifiableList(facts);
		this.questions = Collections.unmodifiableList(questions);
	}

	public List<String> getFacts() {
		return facts;
	}

	public List<String> getQuestions() {
		return questions;
	}

}
