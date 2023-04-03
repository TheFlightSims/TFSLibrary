/*    */ package com.typesafe.config.impl;
/*    */ 
/*    */ import com.typesafe.config.ConfigException;
/*    */ import com.typesafe.config.ConfigOrigin;
/*    */ 
/*    */ class Token {
/*    */   private final TokenType tokenType;
/*    */   
/*    */   private final String debugString;
/*    */   
/*    */   private final ConfigOrigin origin;
/*    */   
/*    */   Token(TokenType tokenType, ConfigOrigin origin) {
/* 15 */     this(tokenType, origin, null);
/*    */   }
/*    */   
/*    */   Token(TokenType tokenType, ConfigOrigin origin, String debugString) {
/* 19 */     this.tokenType = tokenType;
/* 20 */     this.origin = origin;
/* 21 */     this.debugString = debugString;
/*    */   }
/*    */   
/*    */   static Token newWithoutOrigin(TokenType tokenType, String debugString) {
/* 26 */     return new Token(tokenType, null, debugString);
/*    */   }
/*    */   
/*    */   final TokenType tokenType() {
/* 30 */     return this.tokenType;
/*    */   }
/*    */   
/*    */   final ConfigOrigin origin() {
/* 38 */     if (this.origin == null)
/* 39 */       throw new ConfigException.BugOrBroken("tried to get origin from token that doesn't have one: " + this); 
/* 41 */     return this.origin;
/*    */   }
/*    */   
/*    */   final int lineNumber() {
/* 45 */     if (this.origin != null)
/* 46 */       return this.origin.lineNumber(); 
/* 48 */     return -1;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 53 */     if (this.debugString != null)
/* 54 */       return this.debugString; 
/* 56 */     return this.tokenType.name();
/*    */   }
/*    */   
/*    */   protected boolean canEqual(Object other) {
/* 60 */     return other instanceof Token;
/*    */   }
/*    */   
/*    */   public boolean equals(Object other) {
/* 65 */     if (other instanceof Token)
/* 67 */       return (canEqual(other) && this.tokenType == ((Token)other).tokenType); 
/* 70 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 77 */     return this.tokenType.hashCode();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\Token.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */