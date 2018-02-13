import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientMarkov extends MarkovModel{
	
	public Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov(int order) {
		super(order);
		myOrder = order;
		myMap = new HashMap<String,ArrayList<String>>();
	}
	
	public void setTraining(String text) {
		myText = text;
		myMap.clear();
		for (int i=0;i<(myText.length()-myOrder+1);i++) {
			String part = myText.substring(i,i+myOrder);
			if (!myMap.containsKey(part)) {
				ArrayList<String> let = new ArrayList<String>();
				myMap.put(part,let);
				}
			if (i+myOrder >= myText.length()){
				myMap.get(part).add(PSEUDO_EOS);}
			else {
			myMap.get(part).add(myText.substring(i+myOrder,i+myOrder+1));}
			}
		}
	
	public ArrayList<String> getFollows(String key){
		if (!myMap.containsKey(key)) throw new NoSuchElementException("This key does not exist!");
		else {
		return myMap.get(key);}	
	}
}
