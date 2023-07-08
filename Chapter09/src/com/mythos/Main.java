package com.mythos;

public class Main
{
	public static void main(String[] args)
	{
		ComparisonCompactor compactor = new ComparisonCompactor(3, "hello", "world");

		System.out.println(compactor.formatCompactedComparison(null));
	}
}
