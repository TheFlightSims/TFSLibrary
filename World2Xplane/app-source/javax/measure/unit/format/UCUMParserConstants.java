/*    */ package javax.measure.unit.format;
/*    */ 
/*    */ interface UCUMParserConstants {
/*    */   public static final int EOF = 0;
/*    */   
/*    */   public static final int ATOM_CHAR = 1;
/*    */   
/*    */   public static final int ESCAPED_ATOM_CHAR = 2;
/*    */   
/*    */   public static final int TERMINAL_ATOM_CHAR = 3;
/*    */   
/*    */   public static final int LCBRACKET = 4;
/*    */   
/*    */   public static final int RCBRACKET = 5;
/*    */   
/*    */   public static final int LSBRACKET = 6;
/*    */   
/*    */   public static final int RSBRACKET = 7;
/*    */   
/*    */   public static final int ANNOTATION = 8;
/*    */   
/*    */   public static final int FACTOR = 9;
/*    */   
/*    */   public static final int SIGN = 10;
/*    */   
/*    */   public static final int DOT = 11;
/*    */   
/*    */   public static final int SOLIDUS = 12;
/*    */   
/*    */   public static final int ATOM = 13;
/*    */   
/*    */   public static final int DEFAULT = 0;
/*    */   
/* 23 */   public static final String[] tokenImage = new String[] { 
/* 23 */       "<EOF>", "<ATOM_CHAR>", "<ESCAPED_ATOM_CHAR>", "<TERMINAL_ATOM_CHAR>", "\"{\"", "\"}\"", "\"[\"", "\"]\"", "<ANNOTATION>", "<FACTOR>", 
/* 23 */       "<SIGN>", "\".\"", "\"/\"", "<ATOM>", "\"(\"", "\")\"" };
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\format\UCUMParserConstants.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */