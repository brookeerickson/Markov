	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.NoSuchElementException;
import java.util.TreeMap;
	
public class EfficientWordMarkov extends WordMarkovModel{
	public Map<WordGram,ArrayList<String>> myMap;
	
	public EfficientWordMarkov(int order) {
		super(order);
		//myOrder = order;
		myMap = new HashMap<WordGram, ArrayList<String>>();
	//	myMap = new TreeMap<WordGram, ArrayList<String>>();
	}
		@Override
		public void setTraining(String text) {
			myWords = text.split("\\s+");
			myMap.clear();
			WordGram wg = new WordGram(myWords,0,myOrder);
			for (int i=0;i<=(myWords.length-myOrder);i++) {
				if (!myMap.containsKey(wg)) {
					ArrayList<String> let = new ArrayList<String>();
					myMap.put(wg,let);
					}
				if (i+myOrder >= myWords.length){
					myMap.get(wg).add(PSEUDO_EOS);}
				else {
				myMap.get(wg).add(myWords[i+myOrder]);
				wg = wg.shiftAdd(myWords[i+myOrder]);}
				}
			}
		
		@Override
		public ArrayList<String> getFollows(WordGram key){
			if (!myMap.containsKey(key)) throw new NoSuchElementException("This key does not exist!");
			else {
			return myMap.get(key);}	
		}
}
