

//Name: Jingyu Xie
//USC loginid:jingyuxi
//CSCI 455 PA4
//Spring 2016
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class contains the text generator functions with the user's given
 * numbers of words limitation and the length of prefix
 */

public class RandomTextGenerator {
	private ArrayList<String> stringByWords;
	private HashMap<Prefix, ArrayList<String>> kOrderMap = new HashMap<Prefix, ArrayList<String>>();
	private int iniPrefixLen;
	private Random random;
	private String readText;
	private boolean isDebug;

	/**
	 * Creates empty RandomTextGenerator constructor Representation Invariant:
	 * prefixLength>=1;
	 * 
	 * @param iniPrefixLen
	 *            the length of user's given of prefix
	 * @param stringByWords
	 *            is the one by one words ArrayList
	 * @param readText
	 *            the whole text of source file
	 * @param isDebug
	 *            a flag to check whether a debug mode
	 */
	public RandomTextGenerator(int iniPrefixLen, ArrayList<String> stringByWords, String readText, boolean isDebug) {
		random = new Random();
		this.isDebug = isDebug;
		this.iniPrefixLen = iniPrefixLen;
		this.stringByWords = stringByWords;
		this.readText = readText;
		kOrderMap = getMap();

	}

	/**
	 * This method creat a hashmap that provides us the main function to save
	 * values and prefix. It is also a container of generatiing next String word
	 * 
	 * @return a fulload data HashMap
	 */
	public HashMap<Prefix, ArrayList<String>> getMap() {

		// if the file is empty then return back empty map
		if (stringByWords.size() == 0) {
			return new HashMap<Prefix, ArrayList<String>>();
		}

		ArrayList<String> temp = new ArrayList<String>();

		// initialize map
		temp = new ArrayList<String>();
		int i = 0;
		// get the starting string by prefix length
		while (i < iniPrefixLen) {
			temp.add(stringByWords.get(i));
			i++;
		}
		// use the first prefix as the starting prefix into the prefix obj
		Prefix iniPrefix = new Prefix(temp);
		temp = new ArrayList<String>();
		// creating a Hash map that contains key-as prefix,value-as the string
		// come after
		while (i < stringByWords.size()) {
			String nextWordInList = stringByWords.get(i);
			// there is a key exsist copy and add to save to the same key
			boolean isContains = kOrderMap.containsKey(iniPrefix);
			if (isContains) {
				temp = kOrderMap.get(iniPrefix);
				// otherwise create a new prefix key and store the only string
				// into the value
			} else {
				temp = new ArrayList<String>();

			}
			temp.add(nextWordInList);
			ArrayList<String> prefixValue = new ArrayList<String>(temp);
			kOrderMap.put(iniPrefix, prefixValue);
			// shifting the string by 1 and get new prefix
			Prefix shiftedPrefix = iniPrefix.getShiftedPrefix(nextWordInList);
			iniPrefix = shiftedPrefix;
			i++;
		}

		return kOrderMap;

	}

	/**
	 * This method create a random prefix by given prefix length using a prefix
	 * object to save prefix data which is a ArrayList
	 * 
	 * @return a random prefix
	 */

	private Prefix getPickedPrefix() {
		// assgin keyset to arraylist
		ArrayList<Prefix> keySet = new ArrayList<Prefix>(kOrderMap.keySet());
		int keyNums = keySet.size();
		int index = random.nextInt(keyNums);
		// using random index to generate prefix
		Prefix pickedPrefix = keySet.get(index);
		if (isDebug == true) {
			System.out.println("DEBUG: chose a new initial prefix:" + pickedPrefix.toString());
		}
		return pickedPrefix;
	}

	
	/**
	 * This method generate the next words that come after each new prefix
	 * and return as a string
	 * @param pickedPrefix this is the prefix that going to use to generate next words
	 * @return a string that is the next words
	 */
	private String wordsGen(Prefix pickedPrefix) {
		ArrayList<String> values = new ArrayList<String>(kOrderMap.get(pickedPrefix));
		if (isDebug == true) {
			String value = "";
			for (int i = 0; i < values.size(); i++) {
				value = value + " " + values.get(i);
			}
			System.out.println("DEBUG: successors: " + value);
		}
		// using random index to generate next words
		int index = random.nextInt(values.size());
		return values.get(index);
	}

	/**
	 * This method create an Arraylist to save the final results that this class
	 * generate and get ready for saving it into a new text file
	 * 
	 * @param numWords
	 *            it is a user given numbers that to limit the totla words of
	 *            the output file
	 * @return an ArrayList that contains the final results
	 */

	public ArrayList<String> outputText(int numWords) {
		// if the text is empty then return empty arraylist
		if (stringByWords.size() == 0) {
			return stringByWords = new ArrayList<String>();
		}

		// get random prefix
		Prefix newPrefix = getPickedPrefix();

		String nextWords = "";
		ArrayList<String> text = new ArrayList<String>();
		int i = 0;
		while (i < numWords) {

			nextWords = wordsGen(newPrefix);
			if (isDebug == true) {
				System.out.println("DEBUG: word generated:" + nextWords);
			}
			// generate arraylist to store new prefix
			ArrayList<String> nextPrefixAL = new ArrayList<String>(newPrefix.toArrayList());
			nextPrefixAL.remove(0);
			nextPrefixAL.add(nextWords);
			// then use the after work arraylist to generate next shifted prefix
			newPrefix = new Prefix(nextPrefixAL);
			if (isDebug == true) {
				System.out.println("DEBUG: prefix:" + newPrefix);
			}
			if (kOrderMap.get(newPrefix) == null) {
				if (isDebug == true) {
					System.out.println("DEBUG: successors: <END OF FILE>");
				}
				// if there is no words after the prefix pick another random
				// prefix
				newPrefix = getPickedPrefix();

			}
			text.add(nextWords);

			i++;

		}

		return text;
	}

}
