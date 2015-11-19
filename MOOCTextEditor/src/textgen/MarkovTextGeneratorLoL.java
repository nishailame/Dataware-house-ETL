package textgen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An implementation of the MTG interface that uses a list of lists.
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 * @author Nisha Ilame
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList;

	// The starting "word"
	private String starter;

	// The random number generator
	private Random rnGenerator;

	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}

	@Override
	public void train(String sourceText) {
		// TODO: Implement this method
		// wordList = new LinkedList<ListNode>();
		starter = "";
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile("[a-zA-Z]+");
		Matcher m = tokSplitter.matcher(sourceText);

		while (m.find()) {
			tokens.add(m.group());
		}
		int count = 0;
		for (int i = 0; i < tokens.size(); i++) {
			// System.out.println(s);
			String s = tokens.get(i);
			Iterator itr = wordList.iterator();

			while (itr.hasNext()) {
				ListNode temp = (ListNode) itr.next();

				if (temp.getWord().equals(starter)) {
					temp.addNextWord(s);
					count++;

				}
			}
			if (count == 0) {
				ListNode node = new ListNode(starter);
				wordList.add(node);
				node.addNextWord(s);
			}

			starter = s;
			count = 0;
			//
			// if (i == tokens.size() - 1) {
			// ListNode last = new ListNode(starter);
			// wordList.add(last);
			// last.addNextWord("");
			// }

		}

		// Iterator itr2 = wordList.iterator();

		// while (itr2.hasNext()) {
		// ListNode temp = (ListNode) itr2.next();
		// System.out.println(temp.toString());
		// }
	}

	/**
	 * Generate the number of words requested.
	 * 
	 * set "starter" to be an empty String "" set "output" to be "" while you
	 * need more words find the "node" corresponding to "starter" in the list if
	 * that node does not exist find the "node" corresponding to the empty
	 * String "" select a random word from the "wordList" for "node" add the
	 * random word to the "output" set "starter" to the random word increment
	 * number of words added to the list
	 */
	@Override
	public String generateText(int numWords) {
		// TODO: Implement this method
		starter = "";
		String output = "";
		String nextWord = null;
		int check = 0;

		while (numWords != 0) {
			Iterator itr = wordList.iterator();

			while (itr.hasNext()) {
				ListNode temp = (ListNode) itr.next();

				if (temp.getWord().equals(starter)) {
					rnGenerator = new Random();
					nextWord = temp.getRandomNextWord(rnGenerator);
					output = output + " " + nextWord;
					check = 1;
					break;
				}
			}
			if (check == 0) {
				Iterator itr2 = wordList.iterator();
				while (itr2.hasNext()) {
					ListNode temp = (ListNode) itr2.next();

					if (temp.getWord().equals("")) {
						rnGenerator = new Random();
						nextWord = temp.getRandomNextWord(rnGenerator);
						output = output + " " + nextWord;
						break;
					}
				}
			}
			starter = nextWord;
			numWords--;
			check = 0;
			if (numWords==1){
				break;
			}
		}

		return output;
	}

	// Can be helpful for debugging
	@Override
	public String toString() {
		String toReturn = "";
		for (ListNode n : wordList) {
			toReturn += n.toString();
		}
		return toReturn;
	}

	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText) {
		// TODO: Implement this method.
		wordList.clear();
		starter = "";
		train(sourceText);

	}
	// TODO: Add any private helper methods you need here.

	/**
	 * This is a minimal set of tests. Note that it can be difficult to test
	 * methods/classes with randomized behavior.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, " + "You say stop, and I say go, go, go, "
				+ "Oh no. You say goodbye and I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "I say high, you say low, "
				+ "You say why, and I say I don't know. " + "Oh no. "
				+ "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "Why, why, why, why, why, why, "
				+ "Do you say goodbye. " + "Oh no. " + "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello. " + "You say yes, I say no, "
				+ "You say stop and I say go, go, go. " + "Oh, oh no. "
				+ "You say goodbye and I say hello, hello, hello. "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello, "
				+ "I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/**
 * Links a word to the next words in the list You should use this class in your
 * implementation.
 */
class ListNode {
	// The word that is linking to the next words
	private String word;

	// The next words that could follow it
	private List<String> nextWords;

	ListNode(String word) {
		this.word = word;
		nextWords = new LinkedList<String>();
	}

	public String getWord() {
		return word;
	}

	public void addNextWord(String nextWord) {
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator) {
		// TODO: Implement this method
		// The random number generator should be passed from
		// the MarkovTextGeneratorLoL class

		int size = nextWords.size();
		// System.out.println("size " + size);
		int rand_num = generator.nextInt(size);
		// System.out.println("rand num " + rand_num);
		return nextWords.get(rand_num);
	}

	public String toString() {
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}

}
