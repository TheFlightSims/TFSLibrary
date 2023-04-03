/*    */ package org.apache.commons.configuration.plist;
/*    */ 
/*    */ interface PropertyListParserConstants {
/*    */   public static final int EOF = 0;
/*    */   
/*    */   public static final int ARRAY_BEGIN = 5;
/*    */   
/*    */   public static final int ARRAY_END = 6;
/*    */   
/*    */   public static final int ARRAY_SEPARATOR = 7;
/*    */   
/*    */   public static final int DICT_BEGIN = 8;
/*    */   
/*    */   public static final int DICT_END = 9;
/*    */   
/*    */   public static final int DICT_SEPARATOR = 10;
/*    */   
/*    */   public static final int EQUAL = 11;
/*    */   
/*    */   public static final int DATA_START = 12;
/*    */   
/*    */   public static final int DATA_END = 13;
/*    */   
/*    */   public static final int DATE_START = 14;
/*    */   
/*    */   public static final int QUOTE = 15;
/*    */   
/*    */   public static final int LETTER = 16;
/*    */   
/*    */   public static final int WHITE = 17;
/*    */   
/*    */   public static final int HEXA = 18;
/*    */   
/*    */   public static final int DATA = 19;
/*    */   
/*    */   public static final int DATE = 20;
/*    */   
/*    */   public static final int STRING = 21;
/*    */   
/*    */   public static final int QUOTED_STRING = 22;
/*    */   
/*    */   public static final int ESCAPED_QUOTE = 23;
/*    */   
/*    */   public static final int DEFAULT = 0;
/*    */   
/* 29 */   public static final String[] tokenImage = new String[] { 
/* 29 */       "<EOF>", "\" \"", "\"\\t\"", "\"\\n\"", "\"\\r\"", "\"(\"", "\")\"", "\",\"", "\"{\"", "\"}\"", 
/* 29 */       "\";\"", "\"=\"", "\"<\"", "\">\"", "\"<*D\"", "\"\\\"\"", "<LETTER>", "<WHITE>", "<HEXA>", "<DATA>", 
/* 29 */       "<DATE>", "<STRING>", "<QUOTED_STRING>", "\"\\\\\\\"\"" };
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\plist\PropertyListParserConstants.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */