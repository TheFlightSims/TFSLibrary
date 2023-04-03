/*     */ package javax.measure.unit.format;
/*     */ 
/*     */ class ParseException extends Exception {
/*     */   protected boolean specialConstructor;
/*     */   
/*     */   public Token currentToken;
/*     */   
/*     */   public int[][] expectedTokenSequences;
/*     */   
/*     */   public String[] tokenImage;
/*     */   
/*     */   protected String eol;
/*     */   
/*     */   public ParseException(Token currentTokenVal, int[][] expectedTokenSequencesVal, String[] tokenImageVal) {
/*  32 */     super("");
/* 140 */     this.eol = System.getProperty("line.separator", "\n");
/*     */     this.specialConstructor = true;
/*     */     this.currentToken = currentTokenVal;
/*     */     this.expectedTokenSequences = expectedTokenSequencesVal;
/*     */     this.tokenImage = tokenImageVal;
/*     */   }
/*     */   
/*     */   public ParseException() {
/* 140 */     this.eol = System.getProperty("line.separator", "\n");
/*     */     this.specialConstructor = false;
/*     */   }
/*     */   
/*     */   public ParseException(String message) {
/*     */     super(message);
/* 140 */     this.eol = System.getProperty("line.separator", "\n");
/*     */     this.specialConstructor = false;
/*     */   }
/*     */   
/*     */   public String getMessage() {
/*     */     if (!this.specialConstructor)
/*     */       return super.getMessage(); 
/*     */     StringBuffer expected = new StringBuffer();
/*     */     int maxSize = 0;
/*     */     for (int i = 0; i < this.expectedTokenSequences.length; i++) {
/*     */       if (maxSize < (this.expectedTokenSequences[i]).length)
/*     */         maxSize = (this.expectedTokenSequences[i]).length; 
/*     */       for (int k = 0; k < (this.expectedTokenSequences[i]).length; k++)
/*     */         expected.append(this.tokenImage[this.expectedTokenSequences[i][k]]).append(" "); 
/*     */       if (this.expectedTokenSequences[i][(this.expectedTokenSequences[i]).length - 1] != 0)
/*     */         expected.append("..."); 
/*     */       expected.append(this.eol).append("    ");
/*     */     } 
/*     */     String retval = "Encountered \"";
/*     */     Token tok = this.currentToken.next;
/*     */     for (int j = 0; j < maxSize; j++) {
/*     */       if (j != 0)
/*     */         retval = retval + " "; 
/*     */       if (tok.kind == 0) {
/*     */         retval = retval + this.tokenImage[0];
/*     */         break;
/*     */       } 
/*     */       retval = retval + add_escapes(tok.image);
/*     */       tok = tok.next;
/*     */     } 
/*     */     retval = retval + "\" at line " + this.currentToken.next.beginLine + ", column " + this.currentToken.next.beginColumn;
/*     */     retval = retval + "." + this.eol;
/*     */     if (this.expectedTokenSequences.length == 1) {
/*     */       retval = retval + "Was expecting:" + this.eol + "    ";
/*     */     } else {
/*     */       retval = retval + "Was expecting one of:" + this.eol + "    ";
/*     */     } 
/*     */     retval = retval + expected.toString();
/*     */     return retval;
/*     */   }
/*     */   
/*     */   protected String add_escapes(String str) {
/* 148 */     StringBuffer retval = new StringBuffer();
/* 150 */     for (int i = 0; i < str.length(); i++) {
/*     */       char ch;
/* 151 */       switch (str.charAt(i)) {
/*     */         case '\000':
/*     */           break;
/*     */         case '\b':
/* 156 */           retval.append("\\b");
/*     */           break;
/*     */         case '\t':
/* 159 */           retval.append("\\t");
/*     */           break;
/*     */         case '\n':
/* 162 */           retval.append("\\n");
/*     */           break;
/*     */         case '\f':
/* 165 */           retval.append("\\f");
/*     */           break;
/*     */         case '\r':
/* 168 */           retval.append("\\r");
/*     */           break;
/*     */         case '"':
/* 171 */           retval.append("\\\"");
/*     */           break;
/*     */         case '\'':
/* 174 */           retval.append("\\'");
/*     */           break;
/*     */         case '\\':
/* 177 */           retval.append("\\\\");
/*     */           break;
/*     */         default:
/* 180 */           if ((ch = str.charAt(i)) < ' ' || ch > '~') {
/* 181 */             String s = "0000" + Integer.toString(ch, 16);
/* 182 */             retval.append("\\u" + s.substring(s.length() - 4, s.length()));
/*     */             break;
/*     */           } 
/* 184 */           retval.append(ch);
/*     */           break;
/*     */       } 
/*     */     } 
/* 189 */     return retval.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\measur\\unit\format\ParseException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */