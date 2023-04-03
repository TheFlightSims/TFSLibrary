/*    */ package javax.measure.unit.format;
/*    */ 
/*    */ interface UnitParserConstants {
/*    */   public static final int EOF = 0;
/*    */   
/*    */   public static final int DIGIT = 1;
/*    */   
/*    */   public static final int SUPERSCRIPT_DIGIT = 2;
/*    */   
/*    */   public static final int INITIAL_CHAR = 3;
/*    */   
/*    */   public static final int EXTENDED_CHAR = 4;
/*    */   
/*    */   public static final int PLUS = 5;
/*    */   
/*    */   public static final int MINUS = 6;
/*    */   
/*    */   public static final int ASTERISK = 7;
/*    */   
/*    */   public static final int MIDDLE_DOT = 8;
/*    */   
/*    */   public static final int SOLIDUS = 9;
/*    */   
/*    */   public static final int CARET = 10;
/*    */   
/*    */   public static final int COLON = 11;
/*    */   
/*    */   public static final int OPEN_PAREN = 12;
/*    */   
/*    */   public static final int CLOSE_PAREN = 13;
/*    */   
/*    */   public static final int INTEGER = 14;
/*    */   
/*    */   public static final int SUPERSCRIPT_INTEGER = 15;
/*    */   
/*    */   public static final int FLOATING_POINT = 16;
/*    */   
/*    */   public static final int LOG = 17;
/*    */   
/*    */   public static final int NAT_LOG = 18;
/*    */   
/*    */   public static final int E = 19;
/*    */   
/*    */   public static final int UNIT_IDENTIFIER = 20;
/*    */   
/*    */   public static final int DEFAULT = 0;
/*    */   
/* 30 */   public static final String[] tokenImage = new String[] { 
/* 30 */       "<EOF>", "<DIGIT>", "<SUPERSCRIPT_DIGIT>", "<INITIAL_CHAR>", "<EXTENDED_CHAR>", "\"+\"", "\"-\"", "\"*\"", "\"\\u00b7\"", "\"/\"", 
/* 30 */       "\"^\"", "\":\"", "\"(\"", "\")\"", "<INTEGER>", "<SUPERSCRIPT_INTEGER>", "<FLOATING_POINT>", "<LOG>", "<NAT_LOG>", "\"e\"", 
/* 30 */       "<UNIT_IDENTIFIER>" };
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\format\UnitParserConstants.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */