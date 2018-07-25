package com.careerit.web.utils;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ElementLocator {
	
	private String elementName;
    private String elementValue;
    private String elementType;
    private String errorMessage;
    public static final String XPATH = "XPATH";
    public static final String CSS = "CSS";
    public static final String ID = "ID";
    public static final String CLASS = "CLASS";
    public static final String NAME = "NAME";
    public static final String LINKTEXT = "LINKTEXT";
    public static final String PARTIALLINKTEXT = "PARTIALLINKTEXT";
    public static final String TAGNAME = "TAGNAME";

	    public ElementLocator(final String elementName, final String elementValue, final String elementType) {
	        super();
	        this.errorMessage = "";
	        this.elementName = elementName;
	        this.elementValue = elementValue;
	        this.elementType = elementType;
	    }		    

	    public String getelementName() {
	        return this.elementName;
	    }   

	    public String getelementValue() {
	        return this.elementValue;
	    }    

	    public String getelementType() {
	        return this.elementType;
	    }		    

	    public String getErrorMessage() {
	        return this.errorMessage;
	    }		    

	    public ElementLocator format(final Object... substitutions) {
	        String elementValue = this.elementValue;
	        final Pattern typePattern = Pattern.compile("[=,]''[^\\]]*''");
	        final Matcher typeMatcher = typePattern.matcher(elementValue);
	        if (typeMatcher.find()) {
	            elementValue = MessageFormat.format(elementValue, substitutions);
	        }
	        else {
	            Pattern pattern = Pattern.compile("([{][0-9]+[}])");
	            Matcher matcher = pattern.matcher(elementValue);
	            int count = 0;
	            while (matcher.find()) {
	                ++count;
	            }
	            for (int i = 0; i < count; ++i) {
	                pattern = Pattern.compile("([{]" + i + "[}])");
	                matcher = pattern.matcher(elementValue);
	                elementValue = matcher.replaceAll(substitutions[i].toString());
	            }
	        }
	        return this.fixXpath(elementValue);
	    }
	    private ElementLocator fixXpath(String elementValue) {
	    	Pattern replacePattern = Pattern.compile("[=,]'[^']*(['][\\w\\s!@#$%^&*-;:.\342\u201E\242/]*)+'");
	        final Matcher replaceMatcher = replacePattern.matcher(elementValue);
	        while (replaceMatcher.find()) {
	            String matchValue = replaceMatcher.group();
	            matchValue = matchValue.replace("='", "=\"");
	            matchValue = matchValue.replace(",'", ",\"");
	            matchValue = matchValue.substring(0, matchValue.length() - 1) + "\"";
	            elementValue = elementValue.replace(replaceMatcher.group(), matchValue);
	        }
	       // elementValue=elementValue.replace("\"", "'");
	        return new ElementLocator(this.elementName, elementValue, this.elementType);
	    }		    

	    public ElementLocator replace(final String findString, final String replaceString) {
	        return new ElementLocator(this.elementName, this.elementValue.replaceFirst(findString, replaceString), this.elementType);
	    }

}
