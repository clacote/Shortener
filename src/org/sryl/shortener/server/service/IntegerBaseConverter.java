package org.sryl.shortener.server.service;

class IntegerBaseConverter {

	/**
	 * All possible chars for representing a number as a String
	 */
	public final static char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z', '@', '#', '$', 'Û', '=', '¤', ',', ';' };

	/**
	 * Maximum radix for integer conversion
	 */
	public static final int MAX_RADIX = DIGITS.length;
	
	/**
	 * Copy of {@link Integer#toString(int, int) enabling <code>radix</code> to be greater than {@link Character#MAX_RADIX}
	 * @param i integer to convert to given radix
	 * @param radix radix to convert given integer to
	 * @return String representation of given integer to given radix
	 */
	static String toString(int i, int radix) {

		char buf[] = new char[DIGITS.length+1];
		boolean negative = (i < 0);
		int charPos = buf.length-1;

		if (!negative) {
			i = -i;
		}

		while (i <= -radix) {
			buf[charPos--] = DIGITS[-(i % radix)];
			i = i / radix;
		}
		buf[charPos] = DIGITS[-i];

		if (negative) {
			buf[--charPos] = '-';
		}

		return new String(buf, charPos, (buf.length - charPos));
	}

}
