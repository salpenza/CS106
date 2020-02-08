package spellchecker;

import static sbcc.Core.*;
import java.util.regex.*;
import org.apache.commons.*;

public class BasicSpellChecker implements SpellChecker {

	BasicDictionary dictionary;
	StringBuilder doc;
	int index;
	Pattern p;
	Matcher m;


	public BasicSpellChecker() {
		super();
		dictionary = new BasicDictionary();
		index = 0;
	}


	@Override
	public void importDictionary(String filename) throws Exception {
		// TODO Auto-generated method stub
		dictionary.importFile(filename);
	}


	@Override
	public void loadDictionary(String filename) throws Exception {
		// TODO Auto-generated method stub
		dictionary.load(filename);
	}


	@Override
	public void saveDictionary(String filename) throws Exception {
		// TODO Auto-generated method stub
		dictionary.save(filename);
	}


	@Override
	public void loadDocument(String filename) throws Exception {
		// TODO Auto-generated method stub
		doc = new StringBuilder(readFile(filename));

	}


	@Override
	public void saveDocument(String filename) throws Exception {
		// TODO Auto-generated method stub
		writeFile(filename, doc.toString());
	}


	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return doc.toString();
	}


	@Override
	public String[] spellCheck(boolean continueFromPrevious) {
		// TODO Auto-generated method stub
		p = Pattern.compile("\\b[\\w']+\\b");
		m = p.matcher(doc);
		while (m.find(index)) {
			String word = m.group();
			int start = m.start();
			index = m.end();
			String[] results = dictionary.find(word);
			if (results != null) {
				String[] newResult = { word, Integer.toString(start), results[0], results[1] };
				return newResult;
			}
		}
		return null;
	}


	@Override
	public void addWordToDictionary(String word) {
		// TODO Auto-generated method stub
		dictionary.add(word);
	}


	@Override
	public void replaceText(int startIndex, int endIndex, String replacementText) {
		// TODO Auto-generated method stub
		doc.replace(startIndex, endIndex, replacementText);
	}
}
