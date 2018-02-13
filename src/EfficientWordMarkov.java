	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.NoSuchElementException;
import java.util.TreeMap;
	
public class EfficientWordMarkov extends WordMarkovModel{
	public Map<WordGram,ArrayList<String>> myMap;
	
	public EfficientWordMarkov(int order) {
		super(order);
		myOrder = order;
		myMap = new HashMap<WordGram, ArrayList<String>>();
	//	myMap = new TreeMap<WordGram, ArrayList<String>>();
	}
		@Override
		public void setTraining(String text) {
			myWords = text.split("\\s+");
			myMap.clear();
			for (int i=0;i<(myWords.length-myOrder+1);i++) {
				WordGram wg = new WordGram(myWords,i,myOrder);
				if (!myMap.containsKey(wg)) {
					ArrayList<String> let = new ArrayList<String>();
					myMap.put(wg,let);
					}
				if (i+myOrder >= myWords.length){
					myMap.get(wg).add(PSEUDO_EOS);}
				else {
				myMap.get(wg).add(myWords[i+myOrder+1]);}
				}
			}
		
		@Override
		public ArrayList<String> getFollows(WordGram key){
			if (!myMap.containsKey(key)) throw new NoSuchElementException("This key does not exist!");
			else {
			return myMap.get(key);}	
		}
}
