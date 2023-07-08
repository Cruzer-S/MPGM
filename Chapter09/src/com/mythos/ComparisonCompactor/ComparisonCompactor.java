package com.mythos;

public class ComparisonCompactor
{
	private static final String ELLIPSIS = "...";
	private static final String DELTA_END = "]";
	private static final String DELTA_START = "[";

	private int contextLength;
	private String expected;
	private String actual;
	private int prefixLength;
	private int suffixLength;

	public ComparisonCompactor(int contextLength, String expected, String actual)
	{
		this.contextLength = contextLength;
		this.expected = expected;
		this.actual = actual;
	}

	public String formatCompactedComparison(String message)
	{
		String compactExpected = expected;
		String compactActual = actual;

		if (shouldBeCompacted()) {
			findCommonPrefixAndSuffix();
			compactExpected = compact(expected);
			compactActual = compact(actual);
		}

		return format(message, compactExpected, compactActual);
	}

	private String format(String message, String expected, String actual)
	{
		if (message == null)	message = "";
		else 				 	message = message.concat(" ");

		if (expected == null)	expected = "null";
		if (actual == null)		actual = "null";

		return String.format("%sexpected:<%s> but was:<%s>", message, expected, actual);
	}

	private boolean shouldBeCompacted()
	{
		return ! (expected == null || actual == null || expected.equals(actual) );
	}

	private void findCommonPrefixAndSuffix()
	{
		findCommonPrefix();

		for (suffixLength = 0; !suffixOverlapsPrefix(); suffixLength++)
			if (charFromEnd(expected, suffixLength) != charFromEnd(actual, suffixLength))
				break;
	}

	private char charFromEnd(String s, int i)
	{
		return s.charAt(s.length() - i - 1);
	}

	private boolean suffixOverlapsPrefix()
	{
		return actual.length() - suffixLength <= prefixLength || expected.length() - suffixLength <= prefixLength;
	}

	private void findCommonPrefix()
	{
		prefixLength = 0;

		for (int end = Math.min(expected.length(), actual.length());
		     prefixLength < end;
		     prefixLength++)
		{
			if (expected.charAt(prefixLength) != actual.charAt(prefixLength))
				break;
		}
	}

	private String compact(String s)
	{
		return new StringBuilder()
			.append(startingEllipsis())
			.append(startingContext())
			.append(DELTA_START)
			.append(delta(s))
			.append(DELTA_END)
			.append(endingContext())
			.append(endingEllipsis())
			.toString()
		;
	}

	private String startingEllipsis()
	{
		return prefixLength > contextLength ? ELLIPSIS : "";
	}

	private String startingContext()
	{
		int contextStart = Math.max(0, prefixLength - contextLength);
		int contextEnd = prefixLength;

		return expected.substring(contextStart, contextEnd);
	}

	private String delta(String s)
	{
		int deltaStart = prefixLength;
		int deltaEnd = s.length() - suffixLength;

		return s.substring(deltaStart, deltaEnd);
	}

	private String endingContext()
	{
		int contextStart = expected.length() - suffixLength;
		int contextEnd = Math.min(contextStart + contextLength, expected.length());

		return expected.substring(contextStart, contextEnd);
	}

	private String endingEllipsis()
	{
		return (suffixLength > contextLength) ? ELLIPSIS : "";
	}
}
