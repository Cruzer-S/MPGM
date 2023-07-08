package com.mythos;

import junit.framework.TestCase;

public class ComparisonCompactorTest extends TestCase
{
	String failure;

    public void testMessage()
    {
		failure = new ComparisonCompactor(0, "b", "c").formatCompactedComparison("a");
		assertTrue("a expected:<[b]> but was:<[c]>".equals(failure));
    }

	public void testStartSame()
	{
		failure = new ComparisonCompactor(1, "ba", "bc").formatCompactedComparison(null);
		assertEquals("expected:<b[a]> but was:<b[c]>", failure);
	}

	public void testEndSame()
	{
		failure = new ComparisonCompactor(1, "ab", "cb").formatCompactedComparison(null);
		assertEquals("expected:<[a]b> but was:<[c]b>", failure);
	}

	public void testSame()
	{
		failure = new ComparisonCompactor(1, "ab", "ab").formatCompactedComparison(null);
		assertEquals("expected:<ab> but was:<ab>", failure);
	}

	public void testNoContextStartAndEndSame()
	{
		failure = new ComparisonCompactor(0, "abc", "adc").formatCompactedComparison(null);
		assertEquals("expected:<...[b]...> but was:<...[d]...>", failure);
	}

	public void testStartAndEndContext()
	{
		failure = new ComparisonCompactor(1, "abc", "adc").formatCompactedComparison(null);
		assertEquals("expected:<a[b]c> but was:<a[d]c>", failure);
	}

	public void testStartAndEndContextWithEllipses()
	{
		failure = new ComparisonCompactor(1, "abcde", "abfde").formatCompactedComparison(null);
		assertEquals("expected:<...b[c]d...> but was:<...b[f]d...>", failure);
	}

	public void testComparisonErrorStartSameComplete()
	{
		failure = new ComparisonCompactor(2, "ab", "abc").formatCompactedComparison(null);
		assertEquals("expected:<ab[]> but was:<ab[c]>", failure);
	}

	public void testComparisonErrorEndSameComplete()
	{
		failure = new ComparisonCompactor(0, "bc", "abc").formatCompactedComparison(null);
		assertEquals("expected:<[]...> but was:<[a]...>", failure);
	}

	public void testComparisonErrorEndSameCompleteContext()
	{
		failure = new ComparisonCompactor(2, "bc", "abc").formatCompactedComparison(null);
		assertEquals("expected:<[]bc> but was:<[a]bc>", failure);
	}

	public void testComparisonErrorOverlapingMatches()
	{
		failure = new ComparisonCompactor(0, "abc", "abbc").formatCompactedComparison(null);
		assertEquals("expected:<...[]...> but was:<...[b]...>", failure);
	}

	public void testComparisonErrorOverlapingMatchesContext()
	{
		failure = new ComparisonCompactor(2, "abc", "abbc").formatCompactedComparison(null);
		assertEquals("expected:<ab[]c> but was:<ab[b]c>", failure);
	}

	public void testComparisonErrorOverlapingMatches2()
	{
		failure = new ComparisonCompactor(0, "abcdde", "abcde").formatCompactedComparison(null);
		assertEquals("expected:<...[d]...> but was:<...[]...>", failure);
	}

	public void testComparisonErrorOverlapingMatches2Context()
	{
		failure = new ComparisonCompactor(2, "abcdde", "abcde").formatCompactedComparison(null);
		assertEquals("expected:<...cd[d]e> but was:<...cd[]e>", failure);
	}

	public void testComparisonErrorWithActualNull()
	{
		failure = new ComparisonCompactor(0, "a", null).formatCompactedComparison(null);
		assertEquals("expected:<a> but was:<null>", failure);
	}

	public void testComparisonErrorWithExpectedNullContext()
	{
		failure = new ComparisonCompactor(2, null, "a").formatCompactedComparison(null);
		assertEquals("expected:<null> but was:<a>", failure);
	}

	public void testBug609972()
	{
		failure = new ComparisonCompactor(10, "S&P500", "0").formatCompactedComparison(null);
		assertEquals("expected:<[S&P50]0> but was:<[]0>", failure);
	}
}
