/** ------------------------------------------------------------
 * ForHTML.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Jan 15, 2009 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.util;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/** 
 * This class is used to escape HTML characters in user's input
 */

public class ForHTML {

	public static String forHTML(String aText){
		if (aText == null)
			return "";
	     final StringBuilder result = new StringBuilder();
	     final StringCharacterIterator iterator = new StringCharacterIterator(aText);
	     char character =  iterator.current();
	     while (character != CharacterIterator.DONE ){
	       if (character == '<') {
	         result.append("&lt;");
	       }
	       else if (character == '>') {
	         result.append("&gt;");
	       }
	       else if (character == '&') {
	         result.append("&amp;");
	      }
	       else if (character == '\"') {
	         result.append("&quot;");
	       }
	       else if (character == '\t') {
	         addCharEntity(9, result);
	       }
	       else if (character == '!') {
	         addCharEntity(33, result);
	       }
	       else if (character == '#') {
	         addCharEntity(35, result);
	       }
	       else if (character == '$') {
	         addCharEntity(36, result);
	       }
	       else if (character == '%') {
	         addCharEntity(37, result);
	       }
	       else if (character == '{') {
	         addCharEntity(123, result);
	       }
	       else if (character == '|') {
	         addCharEntity(124, result);
	       }
	       else if (character == '}') {
	         addCharEntity(125, result);
	       }
	       else if (character == '~') {
	         addCharEntity(126, result);
	       }
	       else {
	         //the char is not a special one
	         //add it to the result as is
	         result.append(character);
	       }
	       character = iterator.next();
	     }
	     return result.toString();
	  }
	  
	 private static void addCharEntity(Integer aIdx, StringBuilder aBuilder){
		    String padding = "";
		    if( aIdx <= 9 ){
		       padding = "00";
		    }
		    else if( aIdx <= 99 ){
		      padding = "0";
		    }
		    else {
		      //no prefix
		    }
		    String number = padding + aIdx.toString();
		    aBuilder.append("&#" + number + ";");
		  }
}
