/*     */ package scala.runtime;
/*     */ 
/*     */ public final class RichChar$ {
/*     */   public static final RichChar$ MODULE$;
/*     */   
/*     */   public final int hashCode$extension(char $this) {
/*  13 */     return BoxesRunTime.boxToCharacter($this).hashCode();
/*     */   }
/*     */   
/*     */   public final boolean equals$extension(char $this, Object x$1) {
/*     */     boolean bool;
/*  13 */     if (x$1 instanceof RichChar) {
/* 236 */       bool = true;
/*     */     } else {
/* 236 */       bool = false;
/*     */     } 
/*     */     if (bool) {
/*     */       char c = ((RichChar)x$1).self();
/*     */       if (($this == c));
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   private RichChar$() {
/*     */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final scala.math.Numeric$CharIsIntegral$ num$extension(char $this) {
/*     */     return scala.math.Numeric$CharIsIntegral$.MODULE$;
/*     */   }
/*     */   
/*     */   public final scala.math.Ordering$Char$ ord$extension(char $this) {
/*     */     return scala.math.Ordering$Char$.MODULE$;
/*     */   }
/*     */   
/*     */   public final int asDigit$extension(char $this) {
/*     */     return Character.digit($this, 36);
/*     */   }
/*     */   
/*     */   public final boolean isControl$extension(char $this) {
/*     */     return Character.isISOControl($this);
/*     */   }
/*     */   
/*     */   public final boolean isDigit$extension(char $this) {
/*     */     return Character.isDigit($this);
/*     */   }
/*     */   
/*     */   public final boolean isLetter$extension(char $this) {
/*     */     return Character.isLetter($this);
/*     */   }
/*     */   
/*     */   public final boolean isLetterOrDigit$extension(char $this) {
/*     */     return Character.isLetterOrDigit($this);
/*     */   }
/*     */   
/*     */   public final boolean isWhitespace$extension(char $this) {
/*     */     return Character.isWhitespace($this);
/*     */   }
/*     */   
/*     */   public final boolean isSpaceChar$extension(char $this) {
/*     */     return Character.isSpaceChar($this);
/*     */   }
/*     */   
/*     */   public final boolean isHighSurrogate$extension(char $this) {
/*     */     return Character.isHighSurrogate($this);
/*     */   }
/*     */   
/*     */   public final boolean isLowSurrogate$extension(char $this) {
/*     */     return Character.isLowSurrogate($this);
/*     */   }
/*     */   
/*     */   public final boolean isSurrogate$extension(char $this) {
/*     */     return (isHighSurrogate$extension($this) || isLowSurrogate$extension($this));
/*     */   }
/*     */   
/*     */   public final boolean isUnicodeIdentifierStart$extension(char $this) {
/*     */     return Character.isUnicodeIdentifierStart($this);
/*     */   }
/*     */   
/*     */   public final boolean isUnicodeIdentifierPart$extension(char $this) {
/*     */     return Character.isUnicodeIdentifierPart($this);
/*     */   }
/*     */   
/*     */   public final boolean isIdentifierIgnorable$extension(char $this) {
/*     */     return Character.isIdentifierIgnorable($this);
/*     */   }
/*     */   
/*     */   public final boolean isMirrored$extension(char $this) {
/*     */     return Character.isMirrored($this);
/*     */   }
/*     */   
/*     */   public final boolean isLower$extension(char $this) {
/*     */     return Character.isLowerCase($this);
/*     */   }
/*     */   
/*     */   public final boolean isUpper$extension(char $this) {
/*     */     return Character.isUpperCase($this);
/*     */   }
/*     */   
/*     */   public final boolean isTitleCase$extension(char $this) {
/*     */     return Character.isTitleCase($this);
/*     */   }
/*     */   
/*     */   public final char toLower$extension(char $this) {
/*     */     return Character.toLowerCase($this);
/*     */   }
/*     */   
/*     */   public final char toUpper$extension(char $this) {
/*     */     return Character.toUpperCase($this);
/*     */   }
/*     */   
/*     */   public final char toTitleCase$extension(char $this) {
/*     */     return Character.toTitleCase($this);
/*     */   }
/*     */   
/*     */   public final int getType$extension(char $this) {
/*     */     return Character.getType($this);
/*     */   }
/*     */   
/*     */   public final int getNumericValue$extension(char $this) {
/*     */     return Character.getNumericValue($this);
/*     */   }
/*     */   
/*     */   public final byte getDirectionality$extension(char $this) {
/*     */     return Character.getDirectionality($this);
/*     */   }
/*     */   
/*     */   public final char reverseBytes$extension(char $this) {
/*     */     return Character.reverseBytes($this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\RichChar$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */