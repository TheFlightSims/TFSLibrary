/*     */ package javax.measure.unit.format;
/*     */ 
/*     */ class TokenMgrError extends Error {
/*     */   static final int LEXICAL_ERROR = 0;
/*     */   
/*     */   static final int STATIC_LEXER_ERROR = 1;
/*     */   
/*     */   static final int INVALID_LEXICAL_STATE = 2;
/*     */   
/*     */   static final int LOOP_DETECTED = 3;
/*     */   
/*     */   int errorCode;
/*     */   
/*     */   protected static final String addEscapes(String str) {
/*  41 */     StringBuffer retval = new StringBuffer();
/*  43 */     for (int i = 0; i < str.length(); i++) {
/*     */       char ch;
/*  44 */       switch (str.charAt(i)) {
/*     */         case '\000':
/*     */           break;
/*     */         case '\b':
/*  49 */           retval.append("\\b");
/*     */           break;
/*     */         case '\t':
/*  52 */           retval.append("\\t");
/*     */           break;
/*     */         case '\n':
/*  55 */           retval.append("\\n");
/*     */           break;
/*     */         case '\f':
/*  58 */           retval.append("\\f");
/*     */           break;
/*     */         case '\r':
/*  61 */           retval.append("\\r");
/*     */           break;
/*     */         case '"':
/*  64 */           retval.append("\\\"");
/*     */           break;
/*     */         case '\'':
/*  67 */           retval.append("\\'");
/*     */           break;
/*     */         case '\\':
/*  70 */           retval.append("\\\\");
/*     */           break;
/*     */         default:
/*  73 */           if ((ch = str.charAt(i)) < ' ' || ch > '~') {
/*  74 */             String s = "0000" + Integer.toString(ch, 16);
/*  75 */             retval.append("\\u" + s.substring(s.length() - 4, s.length()));
/*     */             break;
/*     */           } 
/*  77 */           retval.append(ch);
/*     */           break;
/*     */       } 
/*     */     } 
/*  82 */     return retval.toString();
/*     */   }
/*     */   
/*     */   protected static String LexicalError(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, char curChar) {
/*  98 */     return "Lexical error at line " + errorLine + ", column " + errorColumn + ".  Encountered: " + (EOFSeen ? "<EOF> " : ("\"" + addEscapes(String.valueOf(curChar)) + "\"" + " (" + curChar + "), ")) + "after : \"" + addEscapes(errorAfter) + "\"";
/*     */   }
/*     */   
/*     */   public String getMessage() {
/* 115 */     return super.getMessage();
/*     */   }
/*     */   
/*     */   public TokenMgrError() {}
/*     */   
/*     */   public TokenMgrError(String message, int reason) {
/* 126 */     super(message);
/* 127 */     this.errorCode = reason;
/*     */   }
/*     */   
/*     */   public TokenMgrError(boolean EOFSeen, int lexState, int errorLine, int errorColumn, String errorAfter, char curChar, int reason) {
/* 131 */     this(LexicalError(EOFSeen, lexState, errorLine, errorColumn, errorAfter, curChar), reason);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\format\TokenMgrError.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */