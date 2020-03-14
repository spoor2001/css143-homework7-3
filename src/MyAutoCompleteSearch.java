



;

/**
 * Spoorthi Gowda
 * 
 * Homeowork 7 part 3  
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class MyAutoCompleteSearch implements AutoCompleteSearch{
	List<String> wordList = new ArrayList<>();
	private Map<String, List<List<Integer>>> indexes;
	
	public MyAutoCompleteSearch(List<String> wordList) {
		// TODO Auto-generated constructor stub
		this.wordList = wordList;
		createIndex();
		System.out.println(indexes);
	}
	
	
	private void createIndex() {
        indexes = new HashMap<>();
        //int count =0 ;
        for (String word : wordList) {
        	Integer wordId = wordList.indexOf(word); 
        	word = word.toLowerCase();
        	char[] charList = word.toCharArray();
        	for (int i = 0; i < charList.length; i++) {
        		char alphabet = charList[i];
        		List<List<Integer>> charDocList = indexes.get(String.valueOf(alphabet));
				if (charDocList==null) {
					charDocList = new ArrayList<>(wordList.size());
					for (int j = 0; j < wordList.size(); j++) {
						charDocList.add(new ArrayList<>());
					}
					List<Integer> documentPosition = new ArrayList<>();
					documentPosition.add(i);
					charDocList.set(wordId, documentPosition);
					indexes.put(String.valueOf(alphabet), charDocList);
				} else {
					List<Integer> documentPosition = charDocList.get(wordId);
					if (documentPosition.isEmpty()) {
						documentPosition = new ArrayList<>();
						documentPosition.add(i);
						charDocList.set(wordId, documentPosition);
						indexes.put(String.valueOf(alphabet), charDocList);
					} else {
						documentPosition.add(i);
						charDocList.set(wordId, documentPosition);
						indexes.put(String.valueOf(alphabet), charDocList);
					}
				}
			}
        	
		}
    }
	
	@Override
	public List<String> search(String text) {
		// TODO Auto-generated method stub
		List<String> matchingWords = new ArrayList<>();
		text = text.toLowerCase();
		char[] searchPhrase = text.toCharArray();

		List<List<Integer>> firstCharIndexList = indexes.get(String.valueOf(searchPhrase[0]));
		if (firstCharIndexList != null) {
			
			for (int i = 0; i < firstCharIndexList.size(); i++) {
				if (!firstCharIndexList.get(i).isEmpty()) {
					List<List<Integer>> positionList = new ArrayList<>();
					for (int j = 0; j < searchPhrase.length; j++) {
						List<List<Integer>> indexList = indexes.get(String.valueOf(searchPhrase[j]));
						if (indexList == null || indexList.get(i).isEmpty()) {
							break;
						}

						List<Integer> docPosList = new ArrayList<>();
						for (Integer pos : indexList.get(i)) {
							docPosList.add(pos - j);
						}
						positionList.add(docPosList);
					}
					if (positionList.size() == searchPhrase.length && positionList.size()==1) {
						if (positionList.get(0).contains(0)) {
							matchingWords.add(wordList.get(i));
						}
						
					} else if (positionList.size() == searchPhrase.length && intersection(positionList)) {
						matchingWords.add(wordList.get(i));
					}
				}
			}

		}
		
		return matchingWords;
	}
	
	private boolean intersection(List<List<Integer>> inputListArrays)
	{	
		
		//Creating HashSet object for first input array
		
		HashSet<Integer> intersectionSet = new HashSet<>(inputListArrays.get(0));
		
		//Calling retainAll() method of first object by passing 2nd, 3rd, 4th... objects
		
		for (int i = 1; i < inputListArrays.size(); i++) 
		{
			HashSet<Integer> set = new HashSet<>(inputListArrays.get(i));
			
			intersectionSet.retainAll(set);
		}		
		
		return intersectionSet.isEmpty()? false : true;
	}
}