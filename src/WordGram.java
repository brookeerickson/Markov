
public class WordGram implements Comparable<WordGram>{
	
	private int myHash;
	private String[] myWords;
	
	public WordGram(String[] words, int index, int size) {
		myWords = new String[size];
		System.arraycopy(words, index, myWords, 0, size);
		myHash = 17;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		for (int k=0;k<myWords.length;k++) {
			hash += myWords[k].hashCode();
			hash += myHash*k;
			myHash = hash;
		}
		return myHash;
	}
	
	@Override
	public String toString() {
		String result = "";
		for (String each:myWords) {
			result += each + " ";}
		return result.trim();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null || ! (other instanceof WordGram)) return false;
		WordGram wg = (WordGram)other;	
		if (myWords.length != wg.myWords.length) return false;
		for (int k=0;k<myWords.length;k++) {
			if (!myWords[k].equals(wg.myWords[k])) return false;
		}
		return true;	
	}
	
	@Override
	public int compareTo(WordGram wg) {
		if ((myWords.length)-(wg.myWords.length) <0) return -1;
		if ((myWords.length)-(wg.myWords.length)>0) return 1;
		else {
		for (int k=0; k<myWords.length; k++) {
			int answer = myWords[k].compareTo(wg.myWords[k]);
			if (answer != 0) {
				return answer;}
		}}
		return 0;
	}
	
	public int length() {
		int count = 0;
		for (String each:myWords) {
			count++;}
		return count;
	}
	
	public WordGram shiftAdd(String last) {
		String[] words = new String[myWords.length];
		for (int k=0; k<myWords.length-1; k++) {
			words[k] = myWords[k+1];}
		words[myWords.length-1] = last;
		return new WordGram(words,0,words.length);
	}
}
