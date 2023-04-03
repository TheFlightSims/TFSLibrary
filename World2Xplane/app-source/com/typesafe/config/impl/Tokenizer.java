/*     */ package com.typesafe.config.impl;
/*     */ 
/*     */ import com.typesafe.config.ConfigException;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import com.typesafe.config.ConfigSyntax;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Queue;
/*     */ 
/*     */ final class Tokenizer {
/*     */   private static class ProblemException extends Exception {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*     */     private final Token problem;
/*     */     
/*     */     ProblemException(Token problem) {
/*  26 */       this.problem = problem;
/*     */     }
/*     */     
/*     */     Token problem() {
/*  30 */       return this.problem;
/*     */     }
/*     */   }
/*     */   
/*     */   private static String asString(int codepoint) {
/*  35 */     if (codepoint == 10)
/*  36 */       return "newline"; 
/*  37 */     if (codepoint == 9)
/*  38 */       return "tab"; 
/*  39 */     if (codepoint == -1)
/*  40 */       return "end of file"; 
/*  41 */     if (Character.isISOControl(codepoint))
/*  42 */       return String.format("control character 0x%x", new Object[] { Integer.valueOf(codepoint) }); 
/*  44 */     return String.format("%c", new Object[] { Integer.valueOf(codepoint) });
/*     */   }
/*     */   
/*     */   static Iterator<Token> tokenize(ConfigOrigin origin, Reader input, ConfigSyntax flavor) {
/*  52 */     return new TokenIterator(origin, input, (flavor != ConfigSyntax.JSON));
/*     */   }
/*     */   
/*     */   private static class TokenIterator implements Iterator<Token> {
/*     */     private final SimpleConfigOrigin origin;
/*     */     
/*     */     private final Reader input;
/*     */     
/*     */     private final LinkedList<Integer> buffer;
/*     */     
/*     */     private int lineNumber;
/*     */     
/*     */     private ConfigOrigin lineOrigin;
/*     */     
/*     */     private final Queue<Token> tokens;
/*     */     
/*     */     private final WhitespaceSaver whitespaceSaver;
/*     */     
/*     */     private final boolean allowComments;
/*     */     
/*     */     static final String firstNumberChars = "0123456789-";
/*     */     
/*     */     static final String numberChars = "0123456789eE+-.";
/*     */     
/*     */     static final String notInUnquotedText = "$\"{}[]:=,+#`^?!@*&\\";
/*     */     
/*     */     private static class WhitespaceSaver {
/*  64 */       private StringBuilder whitespace = new StringBuilder();
/*     */       
/*     */       private boolean lastTokenWasSimpleValue = false;
/*     */       
/*     */       void add(int c) {
/*  69 */         if (this.lastTokenWasSimpleValue)
/*  70 */           this.whitespace.appendCodePoint(c); 
/*     */       }
/*     */       
/*     */       Token check(Token t, ConfigOrigin baseOrigin, int lineNumber) {
/*  74 */         if (Tokenizer.TokenIterator.isSimpleValue(t))
/*  75 */           return nextIsASimpleValue(baseOrigin, lineNumber); 
/*  77 */         nextIsNotASimpleValue();
/*  78 */         return null;
/*     */       }
/*     */       
/*     */       private void nextIsNotASimpleValue() {
/*  86 */         this.lastTokenWasSimpleValue = false;
/*  87 */         this.whitespace.setLength(0);
/*     */       }
/*     */       
/*     */       private Token nextIsASimpleValue(ConfigOrigin baseOrigin, int lineNumber) {
/*  95 */         if (this.lastTokenWasSimpleValue) {
/*  98 */           if (this.whitespace.length() > 0) {
/*  99 */             Token t = Tokens.newUnquotedText(Tokenizer.TokenIterator.lineOrigin(baseOrigin, lineNumber), this.whitespace.toString());
/* 102 */             this.whitespace.setLength(0);
/* 103 */             return t;
/*     */           } 
/* 106 */           return null;
/*     */         } 
/* 109 */         this.lastTokenWasSimpleValue = true;
/* 110 */         this.whitespace.setLength(0);
/* 111 */         return null;
/*     */       }
/*     */     }
/*     */     
/*     */     TokenIterator(ConfigOrigin origin, Reader input, boolean allowComments) {
/* 126 */       this.origin = (SimpleConfigOrigin)origin;
/* 127 */       this.input = input;
/* 128 */       this.allowComments = allowComments;
/* 129 */       this.buffer = new LinkedList<Integer>();
/* 130 */       this.lineNumber = 1;
/* 131 */       this.lineOrigin = this.origin.setLineNumber(this.lineNumber);
/* 132 */       this.tokens = new LinkedList<Token>();
/* 133 */       this.tokens.add(Tokens.START);
/* 134 */       this.whitespaceSaver = new WhitespaceSaver();
/*     */     }
/*     */     
/*     */     private int nextCharRaw() {
/* 143 */       if (this.buffer.isEmpty())
/*     */         try {
/* 145 */           return this.input.read();
/* 146 */         } catch (IOException e) {
/* 147 */           throw new ConfigException.IO(this.origin, "read error: " + e.getMessage(), e);
/*     */         }  
/* 151 */       int c = ((Integer)this.buffer.pop()).intValue();
/* 152 */       return c;
/*     */     }
/*     */     
/*     */     private void putBack(int c) {
/* 157 */       if (this.buffer.size() > 2)
/* 158 */         throw new ConfigException.BugOrBroken("bug: putBack() three times, undesirable look-ahead"); 
/* 161 */       this.buffer.push(Integer.valueOf(c));
/*     */     }
/*     */     
/*     */     static boolean isWhitespace(int c) {
/* 165 */       return ConfigImplUtil.isWhitespace(c);
/*     */     }
/*     */     
/*     */     static boolean isWhitespaceNotNewline(int c) {
/* 169 */       return (c != 10 && ConfigImplUtil.isWhitespace(c));
/*     */     }
/*     */     
/*     */     private boolean startOfComment(int c) {
/* 173 */       if (c == -1)
/* 174 */         return false; 
/* 176 */       if (this.allowComments) {
/* 177 */         if (c == 35)
/* 178 */           return true; 
/* 179 */         if (c == 47) {
/* 180 */           int maybeSecondSlash = nextCharRaw();
/* 182 */           putBack(maybeSecondSlash);
/* 183 */           if (maybeSecondSlash == 47)
/* 184 */             return true; 
/* 186 */           return false;
/*     */         } 
/* 189 */         return false;
/*     */       } 
/* 192 */       return false;
/*     */     }
/*     */     
/*     */     private int nextCharAfterWhitespace(WhitespaceSaver saver) {
/*     */       int c;
/*     */       while (true) {
/* 200 */         c = nextCharRaw();
/* 202 */         if (c == -1)
/* 203 */           return -1; 
/* 205 */         if (isWhitespaceNotNewline(c)) {
/* 206 */           saver.add(c);
/*     */           continue;
/*     */         } 
/*     */         break;
/*     */       } 
/* 209 */       return c;
/*     */     }
/*     */     
/*     */     private Tokenizer.ProblemException problem(String message) {
/* 216 */       return problem("", message, (Throwable)null);
/*     */     }
/*     */     
/*     */     private Tokenizer.ProblemException problem(String what, String message) {
/* 220 */       return problem(what, message, (Throwable)null);
/*     */     }
/*     */     
/*     */     private Tokenizer.ProblemException problem(String what, String message, boolean suggestQuotes) {
/* 224 */       return problem(what, message, suggestQuotes, (Throwable)null);
/*     */     }
/*     */     
/*     */     private Tokenizer.ProblemException problem(String what, String message, Throwable cause) {
/* 228 */       return problem(this.lineOrigin, what, message, cause);
/*     */     }
/*     */     
/*     */     private Tokenizer.ProblemException problem(String what, String message, boolean suggestQuotes, Throwable cause) {
/* 233 */       return problem(this.lineOrigin, what, message, suggestQuotes, cause);
/*     */     }
/*     */     
/*     */     private static Tokenizer.ProblemException problem(ConfigOrigin origin, String what, String message, Throwable cause) {
/* 239 */       return problem(origin, what, message, false, cause);
/*     */     }
/*     */     
/*     */     private static Tokenizer.ProblemException problem(ConfigOrigin origin, String what, String message, boolean suggestQuotes, Throwable cause) {
/* 244 */       if (what == null || message == null)
/* 245 */         throw new ConfigException.BugOrBroken("internal error, creating bad ProblemException"); 
/* 247 */       return new Tokenizer.ProblemException(Tokens.newProblem(origin, what, message, suggestQuotes, cause));
/*     */     }
/*     */     
/*     */     private static Tokenizer.ProblemException problem(ConfigOrigin origin, String message) {
/* 252 */       return problem(origin, "", message, (Throwable)null);
/*     */     }
/*     */     
/*     */     private static ConfigOrigin lineOrigin(ConfigOrigin baseOrigin, int lineNumber) {
/* 257 */       return ((SimpleConfigOrigin)baseOrigin).setLineNumber(lineNumber);
/*     */     }
/*     */     
/*     */     private Token pullComment(int firstChar) {
/* 263 */       if (firstChar == 47) {
/* 264 */         int discard = nextCharRaw();
/* 265 */         if (discard != 47)
/* 266 */           throw new ConfigException.BugOrBroken("called pullComment but // not seen"); 
/*     */       } 
/* 269 */       StringBuilder sb = new StringBuilder();
/*     */       while (true) {
/* 271 */         int c = nextCharRaw();
/* 272 */         if (c == -1 || c == 10) {
/* 273 */           putBack(c);
/* 274 */           return Tokens.newComment(this.lineOrigin, sb.toString());
/*     */         } 
/* 276 */         sb.appendCodePoint(c);
/*     */       } 
/*     */     }
/*     */     
/*     */     private Token pullUnquotedText() {
/* 293 */       ConfigOrigin origin = this.lineOrigin;
/* 294 */       StringBuilder sb = new StringBuilder();
/* 295 */       int c = nextCharRaw();
/* 297 */       while (c != -1) {
/* 299 */         if ("$\"{}[]:=,+#`^?!@*&\\".indexOf(c) >= 0)
/*     */           break; 
/* 301 */         if (isWhitespace(c))
/*     */           break; 
/* 303 */         if (startOfComment(c))
/*     */           break; 
/* 306 */         sb.appendCodePoint(c);
/* 312 */         if (sb.length() == 4) {
/* 313 */           String str = sb.toString();
/* 314 */           if (str.equals("true"))
/* 315 */             return Tokens.newBoolean(origin, true); 
/* 316 */           if (str.equals("null"))
/* 317 */             return Tokens.newNull(origin); 
/* 318 */         } else if (sb.length() == 5) {
/* 319 */           String str = sb.toString();
/* 320 */           if (str.equals("false"))
/* 321 */             return Tokens.newBoolean(origin, false); 
/*     */         } 
/* 324 */         c = nextCharRaw();
/*     */       } 
/* 328 */       putBack(c);
/* 330 */       String s = sb.toString();
/* 331 */       return Tokens.newUnquotedText(origin, s);
/*     */     }
/*     */     
/*     */     private Token pullNumber(int firstChar) throws Tokenizer.ProblemException {
/* 335 */       StringBuilder sb = new StringBuilder();
/* 336 */       sb.appendCodePoint(firstChar);
/* 337 */       boolean containedDecimalOrE = false;
/* 338 */       int c = nextCharRaw();
/* 339 */       while (c != -1 && "0123456789eE+-.".indexOf(c) >= 0) {
/* 340 */         if (c == 46 || c == 101 || c == 69)
/* 341 */           containedDecimalOrE = true; 
/* 342 */         sb.appendCodePoint(c);
/* 343 */         c = nextCharRaw();
/*     */       } 
/* 347 */       putBack(c);
/* 348 */       String s = sb.toString();
/*     */       try {
/* 350 */         if (containedDecimalOrE)
/* 352 */           return Tokens.newDouble(this.lineOrigin, Double.parseDouble(s), s); 
/* 355 */         return Tokens.newLong(this.lineOrigin, Long.parseLong(s), s);
/* 357 */       } catch (NumberFormatException e) {
/* 359 */         for (char u : s.toCharArray()) {
/* 360 */           if ("$\"{}[]:=,+#`^?!@*&\\".indexOf(u) >= 0)
/* 361 */             throw problem(Tokenizer.asString(u), "Reserved character '" + Tokenizer.asString(u) + "' is not allowed outside quotes", true); 
/*     */         } 
/* 366 */         return Tokens.newUnquotedText(this.lineOrigin, s);
/*     */       } 
/*     */     }
/*     */     
/*     */     private void pullEscapeSequence(StringBuilder sb) throws Tokenizer.ProblemException {
/*     */       char[] a;
/*     */       int i;
/*     */       String digits;
/* 371 */       int escaped = nextCharRaw();
/* 372 */       if (escaped == -1)
/* 373 */         throw problem("End of input but backslash in string had nothing after it"); 
/* 375 */       switch (escaped) {
/*     */         case 34:
/* 377 */           sb.append('"');
/*     */           return;
/*     */         case 92:
/* 380 */           sb.append('\\');
/*     */           return;
/*     */         case 47:
/* 383 */           sb.append('/');
/*     */           return;
/*     */         case 98:
/* 386 */           sb.append('\b');
/*     */           return;
/*     */         case 102:
/* 389 */           sb.append('\f');
/*     */           return;
/*     */         case 110:
/* 392 */           sb.append('\n');
/*     */           return;
/*     */         case 114:
/* 395 */           sb.append('\r');
/*     */           return;
/*     */         case 116:
/* 398 */           sb.append('\t');
/*     */           return;
/*     */         case 117:
/* 402 */           a = new char[4];
/* 403 */           for (i = 0; i < 4; i++) {
/* 404 */             int c = nextCharRaw();
/* 405 */             if (c == -1)
/* 406 */               throw problem("End of input but expecting 4 hex digits for \\uXXXX escape"); 
/* 407 */             a[i] = (char)c;
/*     */           } 
/* 409 */           digits = new String(a);
/*     */           try {
/* 411 */             sb.appendCodePoint(Integer.parseInt(digits, 16));
/* 412 */           } catch (NumberFormatException e) {
/* 413 */             throw problem(digits, String.format("Malformed hex digits after \\u escape in string: '%s'", new Object[] { digits }), e);
/*     */           } 
/*     */           return;
/*     */       } 
/* 419 */       throw problem(Tokenizer.asString(escaped), String.format("backslash followed by '%s', this is not a valid escape sequence (quoted strings use JSON escaping, so use double-backslash \\\\ for literal backslash)", new Object[] { Tokenizer.access$200(escaped) }));
/*     */     }
/*     */     
/*     */     private void appendTripleQuotedString(StringBuilder sb) throws Tokenizer.ProblemException {
/* 430 */       int consecutiveQuotes = 0;
/*     */       while (true) {
/* 432 */         int c = nextCharRaw();
/* 434 */         if (c == 34) {
/* 435 */           consecutiveQuotes++;
/*     */         } else {
/* 436 */           if (consecutiveQuotes >= 3) {
/* 439 */             sb.setLength(sb.length() - 3);
/* 440 */             putBack(c);
/*     */             break;
/*     */           } 
/* 443 */           consecutiveQuotes = 0;
/* 444 */           if (c == -1)
/* 445 */             throw problem("End of input but triple-quoted string was still open"); 
/* 446 */           if (c == 10) {
/* 448 */             this.lineNumber++;
/* 449 */             this.lineOrigin = this.origin.setLineNumber(this.lineNumber);
/*     */           } 
/*     */         } 
/* 453 */         sb.appendCodePoint(c);
/*     */       } 
/*     */     }
/*     */     
/*     */     private Token pullQuotedString() throws Tokenizer.ProblemException {
/* 459 */       StringBuilder sb = new StringBuilder();
/* 460 */       int c = 0;
/*     */       do {
/* 462 */         c = nextCharRaw();
/* 463 */         if (c == -1)
/* 464 */           throw problem("End of input but string quote was still open"); 
/* 466 */         if (c == 92) {
/* 467 */           pullEscapeSequence(sb);
/* 468 */         } else if (c != 34) {
/* 470 */           if (Character.isISOControl(c))
/* 471 */             throw problem(Tokenizer.asString(c), "JSON does not allow unescaped " + Tokenizer.asString(c) + " in quoted strings, use a backslash escape"); 
/* 474 */           sb.appendCodePoint(c);
/*     */         } 
/* 476 */       } while (c != 34);
/* 479 */       if (sb.length() == 0) {
/* 480 */         int third = nextCharRaw();
/* 481 */         if (third == 34) {
/* 482 */           appendTripleQuotedString(sb);
/*     */         } else {
/* 484 */           putBack(third);
/*     */         } 
/*     */       } 
/* 488 */       return Tokens.newString(this.lineOrigin, sb.toString());
/*     */     }
/*     */     
/*     */     private Token pullPlusEquals() throws Tokenizer.ProblemException {
/* 493 */       int c = nextCharRaw();
/* 494 */       if (c != 61)
/* 495 */         throw problem(Tokenizer.asString(c), "'+' not followed by =, '" + Tokenizer.asString(c) + "' not allowed after '+'", true); 
/* 498 */       return Tokens.PLUS_EQUALS;
/*     */     }
/*     */     
/*     */     private Token pullSubstitution() throws Tokenizer.ProblemException {
/* 503 */       ConfigOrigin origin = this.lineOrigin;
/* 504 */       int c = nextCharRaw();
/* 505 */       if (c != 123)
/* 506 */         throw problem(Tokenizer.asString(c), "'$' not followed by {, '" + Tokenizer.asString(c) + "' not allowed after '$'", true); 
/* 510 */       boolean optional = false;
/* 511 */       c = nextCharRaw();
/* 512 */       if (c == 63) {
/* 513 */         optional = true;
/*     */       } else {
/* 515 */         putBack(c);
/*     */       } 
/* 518 */       WhitespaceSaver saver = new WhitespaceSaver();
/* 519 */       List<Token> expression = new ArrayList<Token>();
/*     */       while (true) {
/* 523 */         Token t = pullNextToken(saver);
/* 528 */         if (t == Tokens.CLOSE_CURLY)
/*     */           break; 
/* 531 */         if (t == Tokens.END)
/* 532 */           throw problem(origin, "Substitution ${ was not closed with a }"); 
/* 535 */         Token whitespace = saver.check(t, origin, this.lineNumber);
/* 536 */         if (whitespace != null)
/* 537 */           expression.add(whitespace); 
/* 538 */         expression.add(t);
/*     */       } 
/* 542 */       return Tokens.newSubstitution(origin, optional, expression);
/*     */     }
/*     */     
/*     */     private Token pullNextToken(WhitespaceSaver saver) throws Tokenizer.ProblemException {
/*     */       Token t;
/* 546 */       int c = nextCharAfterWhitespace(saver);
/* 547 */       if (c == -1)
/* 548 */         return Tokens.END; 
/* 549 */       if (c == 10) {
/* 551 */         Token line = Tokens.newLine(this.lineOrigin);
/* 552 */         this.lineNumber++;
/* 553 */         this.lineOrigin = this.origin.setLineNumber(this.lineNumber);
/* 554 */         return line;
/*     */       } 
/* 557 */       if (startOfComment(c)) {
/* 558 */         t = pullComment(c);
/*     */       } else {
/* 560 */         switch (c) {
/*     */           case 34:
/* 562 */             t = pullQuotedString();
/*     */             break;
/*     */           case 36:
/* 565 */             t = pullSubstitution();
/*     */             break;
/*     */           case 58:
/* 568 */             t = Tokens.COLON;
/*     */             break;
/*     */           case 44:
/* 571 */             t = Tokens.COMMA;
/*     */             break;
/*     */           case 61:
/* 574 */             t = Tokens.EQUALS;
/*     */             break;
/*     */           case 123:
/* 577 */             t = Tokens.OPEN_CURLY;
/*     */             break;
/*     */           case 125:
/* 580 */             t = Tokens.CLOSE_CURLY;
/*     */             break;
/*     */           case 91:
/* 583 */             t = Tokens.OPEN_SQUARE;
/*     */             break;
/*     */           case 93:
/* 586 */             t = Tokens.CLOSE_SQUARE;
/*     */             break;
/*     */           case 43:
/* 589 */             t = pullPlusEquals();
/*     */             break;
/*     */           default:
/* 592 */             t = null;
/*     */             break;
/*     */         } 
/* 596 */         if (t == null)
/* 597 */           if ("0123456789-".indexOf(c) >= 0) {
/* 598 */             t = pullNumber(c);
/*     */           } else {
/* 599 */             if ("$\"{}[]:=,+#`^?!@*&\\".indexOf(c) >= 0)
/* 600 */               throw problem(Tokenizer.asString(c), "Reserved character '" + Tokenizer.asString(c) + "' is not allowed outside quotes", true); 
/* 603 */             putBack(c);
/* 604 */             t = pullUnquotedText();
/*     */           }  
/*     */       } 
/* 609 */       if (t == null)
/* 610 */         throw new ConfigException.BugOrBroken("bug: failed to generate next token"); 
/* 613 */       return t;
/*     */     }
/*     */     
/*     */     private static boolean isSimpleValue(Token t) {
/* 618 */       if (Tokens.isSubstitution(t) || Tokens.isUnquotedText(t) || Tokens.isValue(t))
/* 620 */         return true; 
/* 622 */       return false;
/*     */     }
/*     */     
/*     */     private void queueNextToken() throws Tokenizer.ProblemException {
/* 627 */       Token t = pullNextToken(this.whitespaceSaver);
/* 628 */       Token whitespace = this.whitespaceSaver.check(t, this.origin, this.lineNumber);
/* 629 */       if (whitespace != null)
/* 630 */         this.tokens.add(whitespace); 
/* 632 */       this.tokens.add(t);
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 637 */       return !this.tokens.isEmpty();
/*     */     }
/*     */     
/*     */     public Token next() {
/* 642 */       Token t = this.tokens.remove();
/* 643 */       if (this.tokens.isEmpty() && t != Tokens.END) {
/*     */         try {
/* 645 */           queueNextToken();
/* 646 */         } catch (ProblemException e) {
/* 647 */           this.tokens.add(e.problem());
/*     */         } 
/* 649 */         if (this.tokens.isEmpty())
/* 650 */           throw new ConfigException.BugOrBroken("bug: tokens queue should not be empty here"); 
/*     */       } 
/* 653 */       return t;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 658 */       throw new UnsupportedOperationException("Does not make sense to remove items from token stream");
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\typesafe\config\impl\Tokenizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */