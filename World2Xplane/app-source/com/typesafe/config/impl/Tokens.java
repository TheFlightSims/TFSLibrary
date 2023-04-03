/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import com.typesafe.config.ConfigValueType;
/*     */ import java.util.List;
/*     */ 
/*     */ final class Tokens {
/*     */   private static class Value extends Token {
/*     */     private final AbstractConfigValue value;
/*     */     
/*     */     Value(AbstractConfigValue value) {
/*  19 */       super(TokenType.VALUE, value.origin());
/*  20 */       this.value = value;
/*     */     }
/*     */     
/*     */     AbstractConfigValue value() {
/*  24 */       return this.value;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  29 */       if (value().resolveStatus() == ResolveStatus.RESOLVED)
/*  30 */         return "'" + value().unwrapped() + "' (" + this.value.valueType().name() + ")"; 
/*  32 */       return "'<unresolved value>' (" + this.value.valueType().name() + ")";
/*     */     }
/*     */     
/*     */     protected boolean canEqual(Object other) {
/*  37 */       return other instanceof Value;
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/*  42 */       return (super.equals(other) && ((Value)other).value.equals(this.value));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  47 */       return 41 * (41 + super.hashCode()) + this.value.hashCode();
/*     */     }
/*     */   }
/*     */   
/*     */   private static class Line extends Token {
/*     */     Line(ConfigOrigin origin) {
/*  53 */       super(TokenType.NEWLINE, origin);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  58 */       return "'\\n'@" + lineNumber();
/*     */     }
/*     */     
/*     */     protected boolean canEqual(Object other) {
/*  63 */       return other instanceof Line;
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/*  68 */       return (super.equals(other) && ((Line)other).lineNumber() == lineNumber());
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  73 */       return 41 * (41 + super.hashCode()) + lineNumber();
/*     */     }
/*     */   }
/*     */   
/*     */   private static class UnquotedText extends Token {
/*     */     private final String value;
/*     */     
/*     */     UnquotedText(ConfigOrigin origin, String s) {
/*  82 */       super(TokenType.UNQUOTED_TEXT, origin);
/*  83 */       this.value = s;
/*     */     }
/*     */     
/*     */     String value() {
/*  87 */       return this.value;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  92 */       return "'" + this.value + "'";
/*     */     }
/*     */     
/*     */     protected boolean canEqual(Object other) {
/*  97 */       return other instanceof UnquotedText;
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/* 102 */       return (super.equals(other) && ((UnquotedText)other).value.equals(this.value));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 108 */       return 41 * (41 + super.hashCode()) + this.value.hashCode();
/*     */     }
/*     */   }
/*     */   
/*     */   private static class Problem extends Token {
/*     */     private final String what;
/*     */     
/*     */     private final String message;
/*     */     
/*     */     private final boolean suggestQuotes;
/*     */     
/*     */     private final Throwable cause;
/*     */     
/*     */     Problem(ConfigOrigin origin, String what, String message, boolean suggestQuotes, Throwable cause) {
/* 120 */       super(TokenType.PROBLEM, origin);
/* 121 */       this.what = what;
/* 122 */       this.message = message;
/* 123 */       this.suggestQuotes = suggestQuotes;
/* 124 */       this.cause = cause;
/*     */     }
/*     */     
/*     */     String what() {
/* 128 */       return this.what;
/*     */     }
/*     */     
/*     */     String message() {
/* 132 */       return this.message;
/*     */     }
/*     */     
/*     */     boolean suggestQuotes() {
/* 136 */       return this.suggestQuotes;
/*     */     }
/*     */     
/*     */     Throwable cause() {
/* 140 */       return this.cause;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 145 */       StringBuilder sb = new StringBuilder();
/* 146 */       sb.append('\'');
/* 147 */       sb.append(this.what);
/* 148 */       sb.append('\'');
/* 149 */       sb.append(" (");
/* 150 */       sb.append(this.message);
/* 151 */       sb.append(")");
/* 152 */       return sb.toString();
/*     */     }
/*     */     
/*     */     protected boolean canEqual(Object other) {
/* 157 */       return other instanceof Problem;
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/* 162 */       return (super.equals(other) && ((Problem)other).what.equals(this.what) && ((Problem)other).message.equals(this.message) && ((Problem)other).suggestQuotes == this.suggestQuotes && ConfigImplUtil.equalsHandlingNull(((Problem)other).cause, this.cause));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 170 */       int h = 41 * (41 + super.hashCode());
/* 171 */       h = 41 * (h + this.what.hashCode());
/* 172 */       h = 41 * (h + this.message.hashCode());
/* 173 */       h = 41 * (h + Boolean.valueOf(this.suggestQuotes).hashCode());
/* 174 */       if (this.cause != null)
/* 175 */         h = 41 * (h + this.cause.hashCode()); 
/* 176 */       return h;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class Comment extends Token {
/*     */     private final String text;
/*     */     
/*     */     Comment(ConfigOrigin origin, String text) {
/* 184 */       super(TokenType.COMMENT, origin);
/* 185 */       this.text = text;
/*     */     }
/*     */     
/*     */     String text() {
/* 189 */       return this.text;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 194 */       StringBuilder sb = new StringBuilder();
/* 195 */       sb.append("'#");
/* 196 */       sb.append(this.text);
/* 197 */       sb.append("' (COMMENT)");
/* 198 */       return sb.toString();
/*     */     }
/*     */     
/*     */     protected boolean canEqual(Object other) {
/* 203 */       return other instanceof Comment;
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/* 208 */       return (super.equals(other) && ((Comment)other).text.equals(this.text));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 213 */       int h = 41 * (41 + super.hashCode());
/* 214 */       h = 41 * (h + this.text.hashCode());
/* 215 */       return h;
/*     */     }
/*     */   }
/*     */   
/*     */   private static class Substitution extends Token {
/*     */     private final boolean optional;
/*     */     
/*     */     private final List<Token> value;
/*     */     
/*     */     Substitution(ConfigOrigin origin, boolean optional, List<Token> expression) {
/* 225 */       super(TokenType.SUBSTITUTION, origin);
/* 226 */       this.optional = optional;
/* 227 */       this.value = expression;
/*     */     }
/*     */     
/*     */     boolean optional() {
/* 231 */       return this.optional;
/*     */     }
/*     */     
/*     */     List<Token> value() {
/* 235 */       return this.value;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 240 */       StringBuilder sb = new StringBuilder();
/* 241 */       for (Token t : this.value)
/* 242 */         sb.append(t.toString()); 
/* 244 */       return "'${" + sb.toString() + "}'";
/*     */     }
/*     */     
/*     */     protected boolean canEqual(Object other) {
/* 249 */       return other instanceof Substitution;
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/* 254 */       return (super.equals(other) && ((Substitution)other).value.equals(this.value));
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 260 */       return 41 * (41 + super.hashCode()) + this.value.hashCode();
/*     */     }
/*     */   }
/*     */   
/*     */   static boolean isValue(Token token) {
/* 265 */     return token instanceof Value;
/*     */   }
/*     */   
/*     */   static AbstractConfigValue getValue(Token token) {
/* 269 */     if (token instanceof Value)
/* 270 */       return ((Value)token).value(); 
/* 272 */     throw new ConfigException.BugOrBroken("tried to get value of non-value token " + token);
/*     */   }
/*     */   
/*     */   static boolean isValueWithType(Token t, ConfigValueType valueType) {
/* 278 */     return (isValue(t) && getValue(t).valueType() == valueType);
/*     */   }
/*     */   
/*     */   static boolean isNewline(Token token) {
/* 282 */     return token instanceof Line;
/*     */   }
/*     */   
/*     */   static boolean isProblem(Token token) {
/* 286 */     return token instanceof Problem;
/*     */   }
/*     */   
/*     */   static String getProblemWhat(Token token) {
/* 290 */     if (token instanceof Problem)
/* 291 */       return ((Problem)token).what(); 
/* 293 */     throw new ConfigException.BugOrBroken("tried to get problem what from " + token);
/*     */   }
/*     */   
/*     */   static String getProblemMessage(Token token) {
/* 298 */     if (token instanceof Problem)
/* 299 */       return ((Problem)token).message(); 
/* 301 */     throw new ConfigException.BugOrBroken("tried to get problem message from " + token);
/*     */   }
/*     */   
/*     */   static boolean getProblemSuggestQuotes(Token token) {
/* 306 */     if (token instanceof Problem)
/* 307 */       return ((Problem)token).suggestQuotes(); 
/* 309 */     throw new ConfigException.BugOrBroken("tried to get problem suggestQuotes from " + token);
/*     */   }
/*     */   
/*     */   static Throwable getProblemCause(Token token) {
/* 315 */     if (token instanceof Problem)
/* 316 */       return ((Problem)token).cause(); 
/* 318 */     throw new ConfigException.BugOrBroken("tried to get problem cause from " + token);
/*     */   }
/*     */   
/*     */   static boolean isComment(Token token) {
/* 323 */     return token instanceof Comment;
/*     */   }
/*     */   
/*     */   static String getCommentText(Token token) {
/* 327 */     if (token instanceof Comment)
/* 328 */       return ((Comment)token).text(); 
/* 330 */     throw new ConfigException.BugOrBroken("tried to get comment text from " + token);
/*     */   }
/*     */   
/*     */   static boolean isUnquotedText(Token token) {
/* 335 */     return token instanceof UnquotedText;
/*     */   }
/*     */   
/*     */   static String getUnquotedText(Token token) {
/* 339 */     if (token instanceof UnquotedText)
/* 340 */       return ((UnquotedText)token).value(); 
/* 342 */     throw new ConfigException.BugOrBroken("tried to get unquoted text from " + token);
/*     */   }
/*     */   
/*     */   static boolean isSubstitution(Token token) {
/* 348 */     return token instanceof Substitution;
/*     */   }
/*     */   
/*     */   static List<Token> getSubstitutionPathExpression(Token token) {
/* 352 */     if (token instanceof Substitution)
/* 353 */       return ((Substitution)token).value(); 
/* 355 */     throw new ConfigException.BugOrBroken("tried to get substitution from " + token);
/*     */   }
/*     */   
/*     */   static boolean getSubstitutionOptional(Token token) {
/* 361 */     if (token instanceof Substitution)
/* 362 */       return ((Substitution)token).optional(); 
/* 364 */     throw new ConfigException.BugOrBroken("tried to get substitution optionality from " + token);
/*     */   }
/*     */   
/* 369 */   static final Token START = Token.newWithoutOrigin(TokenType.START, "start of file");
/*     */   
/* 370 */   static final Token END = Token.newWithoutOrigin(TokenType.END, "end of file");
/*     */   
/* 371 */   static final Token COMMA = Token.newWithoutOrigin(TokenType.COMMA, "','");
/*     */   
/* 372 */   static final Token EQUALS = Token.newWithoutOrigin(TokenType.EQUALS, "'='");
/*     */   
/* 373 */   static final Token COLON = Token.newWithoutOrigin(TokenType.COLON, "':'");
/*     */   
/* 374 */   static final Token OPEN_CURLY = Token.newWithoutOrigin(TokenType.OPEN_CURLY, "'{'");
/*     */   
/* 375 */   static final Token CLOSE_CURLY = Token.newWithoutOrigin(TokenType.CLOSE_CURLY, "'}'");
/*     */   
/* 376 */   static final Token OPEN_SQUARE = Token.newWithoutOrigin(TokenType.OPEN_SQUARE, "'['");
/*     */   
/* 377 */   static final Token CLOSE_SQUARE = Token.newWithoutOrigin(TokenType.CLOSE_SQUARE, "']'");
/*     */   
/* 378 */   static final Token PLUS_EQUALS = Token.newWithoutOrigin(TokenType.PLUS_EQUALS, "'+='");
/*     */   
/*     */   static Token newLine(ConfigOrigin origin) {
/* 381 */     return new Line(origin);
/*     */   }
/*     */   
/*     */   static Token newProblem(ConfigOrigin origin, String what, String message, boolean suggestQuotes, Throwable cause) {
/* 386 */     return new Problem(origin, what, message, suggestQuotes, cause);
/*     */   }
/*     */   
/*     */   static Token newComment(ConfigOrigin origin, String text) {
/* 390 */     return new Comment(origin, text);
/*     */   }
/*     */   
/*     */   static Token newUnquotedText(ConfigOrigin origin, String s) {
/* 394 */     return new UnquotedText(origin, s);
/*     */   }
/*     */   
/*     */   static Token newSubstitution(ConfigOrigin origin, boolean optional, List<Token> expression) {
/* 398 */     return new Substitution(origin, optional, expression);
/*     */   }
/*     */   
/*     */   static Token newValue(AbstractConfigValue value) {
/* 402 */     return new Value(value);
/*     */   }
/*     */   
/*     */   static Token newString(ConfigOrigin origin, String value) {
/* 406 */     return newValue(new ConfigString(origin, value));
/*     */   }
/*     */   
/*     */   static Token newInt(ConfigOrigin origin, int value, String originalText) {
/* 410 */     return newValue(ConfigNumber.newNumber(origin, value, originalText));
/*     */   }
/*     */   
/*     */   static Token newDouble(ConfigOrigin origin, double value, String originalText) {
/* 416 */     return newValue(ConfigNumber.newNumber(origin, value, originalText));
/*     */   }
/*     */   
/*     */   static Token newLong(ConfigOrigin origin, long value, String originalText) {
/* 421 */     return newValue(ConfigNumber.newNumber(origin, value, originalText));
/*     */   }
/*     */   
/*     */   static Token newNull(ConfigOrigin origin) {
/* 426 */     return newValue(new ConfigNull(origin));
/*     */   }
/*     */   
/*     */   static Token newBoolean(ConfigOrigin origin, boolean value) {
/* 430 */     return newValue(new ConfigBoolean(origin, value));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\Tokens.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */