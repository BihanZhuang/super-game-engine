package util;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegexParser {
    
    public static final String NO_MATCH = "NO MATCH";
    
    private List<Entry<String, Pattern>> symbolTable;

	public RegexParser() {
		symbolTable = new ArrayList<>();
	}

	public void setPattern(String pattern) {
		symbolTable.clear();
        ResourceBundle resources = ResourceBundle.getBundle(Constants.RESOURCE_PACKAGE + pattern);
        Enumeration<String> iter = resources.getKeys();
        while (iter.hasMoreElements()) {
            String key = iter.nextElement();
            String regex = resources.getString(key);
            symbolTable.add(new SimpleEntry<>(key, 
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }
	
	public String getSymbol(String text) {
        for (Entry<String, Pattern> e : symbolTable) {
            if (e.getValue().matcher(text).matches() || e.getKey().toLowerCase().equals(text.toLowerCase())) {
                return e.getKey();
            }
        }
        return NO_MATCH;
    }
	
	public String getRegex(String symbol) {
	    for (Entry<String, Pattern> e : symbolTable) {
            if (e.getKey().equals(symbol)) {
                return e.getValue().toString();
            }
        }
        return NO_MATCH;
	}
}
