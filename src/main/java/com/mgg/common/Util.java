package com.mgg.common;

import java.util.Map;

import com.mgg.common.Constants.ROMAN_NUMBERS;

public class Util {

	public static int convertRomanToArabic(String romanNumber) {

		int arabicValue = 0;

		char[] r = romanNumber.toCharArray();

		int i = 0;
		for (i = 0; i < r.length - 1; i++) {

			ROMAN_NUMBERS current = ROMAN_NUMBERS.valueOf(String.valueOf(r[i]));
			ROMAN_NUMBERS next = ROMAN_NUMBERS.valueOf(String.valueOf(r[i + 1]));

			int currentValue = current.value();
			int nextValue = next.value();

			if (currentValue < nextValue) {

				arabicValue = arabicValue + (nextValue - currentValue);
				i = i + 1;

			} else {

				arabicValue = arabicValue + currentValue;
			}
		}

		if (i == r.length - 1) {

			ROMAN_NUMBERS last = ROMAN_NUMBERS.valueOf(String.valueOf(r[r.length - 1]));

			arabicValue = arabicValue + last.value();
		}

		return arabicValue;
	}

	public static String convertGalacticToRomanNumber(String galacticNumber,
			Map<String, ROMAN_NUMBERS> galacticNumbers) {

		String romanNumber = "";

		String[] g = galacticNumber.split("\\s+");

		for (int i = 0; i < g.length; i++) {

			romanNumber = romanNumber + galacticNumbers.get(g[i]);
		}

		return romanNumber;
	}
}
