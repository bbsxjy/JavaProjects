

//Name: Jingyu Xie
//USC loginid:jingyuxi
//CSCI 455 PA4
//Spring 2016

import java.util.ArrayList;

/**
 * This class creaating a prefix object that contains with Arraylist that have
 * the useful prefix and the Prefix is the list of words that can be found in
 * the source file
 * 
 */
public class Prefix {

	private ArrayList<String> curPrefix;

	/**
	 * This constructor have an ArrayListg that use to be a temprary Prefix
	 * container Representation Invariant: size of words is at least 1
	 * 
	 * @param an
	 *            Arraylist that contains list of words to make next prefix
	 */

	public Prefix(ArrayList<String> curPrefix) {
		this.curPrefix = curPrefix;

	}

	/**
	 * This method create an starting prefix by the length of given prefix
	 * 
	 * @return an random prefix in an arraylist
	 */

	// initialize and generate prefix
	public ArrayList<String> prefixGen() {
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < curPrefix.size(); i++) {
			temp.add(curPrefix.get(i));
		}

		return temp;

	}

	/**
	 * This method create a shifted prefix that generating from the last prefix
	 * by move word towards right by 1. Using remove the first string and add
	 * the new string to the end of Arraylist
	 * 
	 * @return a shifted new Prefix
	 */

	public Prefix getShiftedPrefix(String word) {
		ArrayList<String> temp = new ArrayList<String>();
		for(int i =1;i<curPrefix.size();i++){
			temp.add(curPrefix.get(i));
		}
		temp.add(word);
		Prefix shiftedPrefix=new Prefix(temp);
		return shiftedPrefix;
	}

	/**
	 * Add data from Scanner to RandomTextGenerator.
	 * 
	 * @param in
	 *            data to scan. "in" will be at the end of its data after this
	 *            operation.
	 */
	public String toString() {
		String prints = "";
		for (int i = 0; i < curPrefix.size(); i++) {
			prints = prints + " " + curPrefix.get(i);
		}
		return prints;

	}

	/**
	 * This overide the hashcode
	 * 
	 * @return a currentfix hashcode
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return curPrefix.hashCode();
	}

	/**
	 * This overide the equals method that used to compare each prefix since it
	 * can't be compare directly from prefix then I transfer it to Arraylist to
	 * compare
	 * 
	 * @return true for same false for not the same
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return curPrefix.equals(((Prefix) obj).toArrayList());
	}

	/**
	 * This returns a ArrayList of a Prefix
	 * 
	 * @return an Arraylist of a prefix
	 */
	public ArrayList<String> toArrayList() {
		return new ArrayList<String>(curPrefix);
	}

}
