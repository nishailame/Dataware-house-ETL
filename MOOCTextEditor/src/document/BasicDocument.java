//package document;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///** 
// * A naive implementation of the Document abstract class. 
// * @author UC San Diego Intermediate Programming MOOC team
// */
//public class BasicDocument extends Document 
//{
//	/** Create a new BasicDocument object
//	 *
//	 * 
//	 * @param text The full text of the Document.
//	 */
//	ArrayList<String> tokens = new ArrayList<String>();
//	public BasicDocument(String text)
//	{
//		super(text);
//	}
//	
//	
//	/**
//	 * Get the number of words in the document.
//	 * "Words" are defined as contiguous strings of alphabetic characters
//	 * i.e. any upper or lower case characters a-z or A-Z
//	 * 
//	 * @return The number of words in the document.
//	 */
//	@Override
//	public int getNumWords()
//	{
//		//TODO: Implement this method.  See the Module 1 support videos 
//	    // if you need help.
//		int num = 0;
//		String pattern = "[a-zA-Z]+";
//		Pattern tokSplitter = Pattern.compile(pattern);
//		Matcher m = tokSplitter.matcher(getText());
//		
//		while (m.find()) {
//			tokens.add(m.group());
//			//System.out.println(m.group());
//			num++;
//			}
//		 return num;
//	}
//	
//	/**
//	 * Get the number of sentences in the document.
//	 * Sentences are defined as contiguous strings of characters ending in an 
//	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
//	 * characters in the document, even if they don't end with a punctuation mark.
//	 * 
//	 * @return The number of sentences in the document.
//	 */
//	@Override
//	public int getNumSentences()
//	{
//	    //TODO: Implement this method.  See the Module 1 support videos 
//        // if you need help.
//		int num = 0;
//		
//		String pattern = "[^!.?]+";
//		Pattern tokSplitter = Pattern.compile(pattern);
//		
//		Matcher m = tokSplitter.matcher(getText());
//		String lastToken = "";
//		while (m.find()) {
//			num++;
//			lastToken = m.group();
//		//System.out.println(lastToken);
//		}
//		/*if (!getText().endsWith("!")&&!getText().endsWith("?")&&!getText().endsWith(".") && !getText().endsWith(" ")&& getText()!="" )
//		{
//			num++;
//		}*/
//		
//		 return num;
//	}
//	
//	
//	/**
//	 * Get the number of sentences in the document.
//	 * Words are defined as above.  Syllables are defined as:
//	 * a contiguous sequence of vowels, except for an "e" at the 
//	 * end of a word if the word has another set of contiguous vowels, 
//	 * makes up one syllable.   y is considered a vowel.
//	 * @return The number of syllables in the document.
//	 */
//	@Override
//	public int getNumSyllables()
//	{
//	    //TODO: Implement this method.  See the Module 1 support videos 
//        // if you need help.
//		tokens.clear();
//		int num = 0;
//		int count=0;
//		String pattern = "[a-zA-Z]+";
//		Pattern tokSplitter = Pattern.compile(pattern);
//		Matcher m = tokSplitter.matcher(getText());
//		//String lastToken = "";
//		while (m.find()) {
//			tokens.add(m.group());
//			//System.out.println(m.group());
//		}
//		String pattern1 = "[aeiuoyAUIOYE]+";
//		Pattern tokSplitter1 = Pattern.compile(pattern1);
//		
//		for (String word : tokens){
//			//System.out.println(word);
//			Matcher m1 = tokSplitter1.matcher(word);
//			String lastToken1 = "";
//			while (m1.find()) {
//			num++;
//			lastToken1 = m1.group();
//			//System.out.println(lastToken1);
//			}
//			if(num > 1 && (word.endsWith("e")||word.endsWith("E")) && lastToken1.equalsIgnoreCase("e")) {
//			num--;
//			}
//			count=count+num;
//			num=0;
//			//System.out.println(num);
//		}
//		return count;
//	}
//	
//	
//	/* The main method for testing this class. 
//	 * You are encouraged to add your own tests.  */
//	public static void main(String[] args)
//	{
//		
//		testCase(new BasicDocument("This is a test.  How many???  "
//		        + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
//				16, 13, 5);
//		testCase(new BasicDocument(""), 0, 0, 0);
//		testCase(new BasicDocument("sentence, with, lots, of, commas.!  "
//		        + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
//		testCase(new BasicDocument("These are some sentences. These are some sentences. These are some sentences."), 6, 3, 2);		
//	}
//	
//}

package document;

import java.util.List;

/** 
 * A naive implementation of the Document abstract class. 
  * @author Nisha Ilame
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class BasicDocument extends Document 
{
	/** Create a new BasicDocument object
	 * 
	 * @param text The full text of the Document.
	 */
	public BasicDocument(String text)
	{
		super(text);
	}
	
	
	/**
	 * Get the number of words in the document.
	 * "Words" are defined as contiguous strings of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords()
	{
		List<String> tokens = getTokens("[a-zA-Z]+");
		return tokens.size();
	}
	
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences()
	{
		// The pattern below will break for floating point numbers, 
		// abbreviations, and other edge cases
		List<String> tokens = getTokens("[^?.!]+");  
		return tokens.size();
	}
	
	/**
	 * Get the number of sentences in the document.
	 * Words are defined as above.  Syllables are defined as:
	 * a contiguous sequence of vowels, except for an "e" at the 
	 * end of a word if the word has another set of contiguous vowels, 
	 * makes up one syllable.   y is considered a vowel.
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables()
	{
		// We provide for you two solutions: One that uses multiple 
		// regexs to calculate the number of syllables and the other
		// that finds words using a loop.  The regex solution is commented 
		// out here at the top.

		/* Our solution using regex's.  Uncoment here to run it*/
		/*
		List<String> tokens = getTokens("[aeiouyAEIOUY]+");
		List<String> loneEs = getTokens("[^aeiouyAEIOUY]+[eE]\\b");
		List<String> singleEs = getTokens("\\b[^aeiouyAEIOUY]*[eE]\\b");
		
		
		return tokens.size() - (loneEs.size() - singleEs.size());
		*/
		
		/* Our solution that does NOT use regexs to find syllables */
		List<String> tokens = getTokens("[a-zA-Z]+");
		int totalSyllables = 0;
		for (String word : tokens)
		{
			totalSyllables += countSyllables(word);
		}
		return totalSyllables;
	
	}
	
	
	/* The main method for testing this class. 
	 * You are encouraged to add your own tests.  */
	public static void main(String[] args)
	{
		testCase(new BasicDocument("Sentence"), 2, 1, 1);
		testCase(new BasicDocument("This is a test.  How many???  Senteeeeeeeeeences are here... there should be 5!  Right?"),
				16, 13, 5);
		testCase(new BasicDocument(""), 0, 0, 0);
		testCase(new BasicDocument("sentence, with, lots, of, commas.!  (And some poaren)).  The output is: 7.5."), 15, 11, 4);
		testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
		testCase(new BasicDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		         32, 15, 1);
		testCase(new BasicDocument("Segue."), 2, 1, 1);
		
		String s = "%one%%two%%%three%%%%";
		String[] arr = s.split("one,two,three");
		for ( String s1 : arr) {
			System.out.println("Str: " + s1 + ".");
		}	
	}
	
}


