package com.mgg.common;

public class Constants {

	public enum ROMAN_NUMBERS {

		I(1),

		V(5),

		X(10),

		L(50),

		C(100),

		D(500),

		M(1000);

		int value;

		private ROMAN_NUMBERS(final int value) {

			this.value = value;
		}

		public int value() {

			return this.value;
		}

	}
}
