/*    */ package org.apache.commons.configuration.plist;
/*    */ 
/*    */ class Token {
/*    */   public int kind;
/*    */   
/*    */   public int beginLine;
/*    */   
/*    */   public int beginColumn;
/*    */   
/*    */   public int endLine;
/*    */   
/*    */   public int endColumn;
/*    */   
/*    */   public String image;
/*    */   
/*    */   public Token next;
/*    */   
/*    */   public Token specialToken;
/*    */   
/*    */   public String toString() {
/* 58 */     return this.image;
/*    */   }
/*    */   
/*    */   public static final Token newToken(int ofKind) {
/* 75 */     switch (ofKind) {
/*    */     
/*    */     } 
/* 77 */     return new Token();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\plist\Token.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */