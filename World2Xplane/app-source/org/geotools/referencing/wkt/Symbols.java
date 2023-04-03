/*     */ package org.geotools.referencing.wkt;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public class Symbols {
/*  38 */   public static final Symbols DEFAULT = new Symbols(Locale.US);
/*     */   
/*  44 */   public static final Symbols SQUARE_BRACKETS = DEFAULT;
/*     */   
/*  50 */   public static final Symbols CURLY_BRACKETS = new Symbols();
/*     */   
/*     */   final Locale locale;
/*     */   
/*     */   static {
/*  52 */     CURLY_BRACKETS.open = '(';
/*  53 */     CURLY_BRACKETS.close = ')';
/*     */   }
/*     */   
/*  73 */   char open = '[';
/*     */   
/*  79 */   char close = ']';
/*     */   
/*  85 */   final char openArray = '{';
/*     */   
/*  91 */   final char closeArray = '}';
/*     */   
/*  97 */   final char quote = '"';
/*     */   
/* 103 */   char separator = ',';
/*     */   
/* 109 */   final char space = ' ';
/*     */   
/* 116 */   final char[] openingBrackets = new char[] { '[', '(' };
/*     */   
/* 121 */   final char[] closingBrackets = new char[] { ']', ')' };
/*     */   
/*     */   final NumberFormat numberFormat;
/*     */   
/*     */   private Symbols() {
/* 136 */     this.locale = Locale.US;
/* 137 */     this.numberFormat = DEFAULT.numberFormat;
/*     */   }
/*     */   
/*     */   public Symbols(Locale locale) {
/* 144 */     this.locale = locale;
/* 145 */     this.numberFormat = NumberFormat.getNumberInstance(locale);
/* 146 */     this.numberFormat.setGroupingUsed(false);
/* 147 */     this.numberFormat.setMinimumFractionDigits(1);
/* 148 */     this.numberFormat.setMaximumFractionDigits(20);
/* 158 */     if (this.numberFormat instanceof DecimalFormat) {
/* 159 */       char decimalSeparator = ((DecimalFormat)this.numberFormat).getDecimalFormatSymbols().getDecimalSeparator();
/* 161 */       if (decimalSeparator == ',')
/* 162 */         this.separator = ';'; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean containsAxis(CharSequence wkt) {
/* 174 */     return (indexOf(wkt, "AXIS", 0) >= 0);
/*     */   }
/*     */   
/*     */   private int indexOf(CharSequence wkt, String element, int index) {
/* 187 */     assert element.equals(element.trim().toUpperCase(this.locale)) : element;
/* 188 */     assert element.indexOf('"') < 0 : element;
/* 189 */     boolean isQuoting = false;
/* 190 */     int elementLength = element.length();
/* 191 */     int length = wkt.length();
/* 192 */     if (index < length) {
/* 193 */       char c = wkt.charAt(index);
/*     */       label56: while (true) {
/* 196 */         if (c == '"')
/* 197 */           isQuoting = !isQuoting; 
/* 199 */         if (isQuoting || !Character.isJavaIdentifierStart(c)) {
/* 200 */           if (++index == length)
/*     */             break; 
/* 203 */           c = wkt.charAt(index);
/*     */           continue;
/*     */         } 
/* 207 */         for (int j = 0; j < elementLength; j++) {
/* 208 */           c = Character.toUpperCase(c);
/* 209 */           if (c != element.charAt(j)) {
/* 211 */             while (Character.isJavaIdentifierPart(c)) {
/* 212 */               if (++index == length)
/*     */                 // Byte code: goto -> 300 
/* 215 */               c = wkt.charAt(index);
/*     */             } 
/*     */             continue label56;
/*     */           } 
/* 219 */           if (++index == length)
/*     */             // Byte code: goto -> 300 
/* 222 */           c = wkt.charAt(index);
/*     */         } 
/* 225 */         while (Character.isWhitespace(c)) {
/* 226 */           if (++index == length)
/*     */             // Byte code: goto -> 300 
/* 229 */           c = wkt.charAt(index);
/*     */         } 
/* 231 */         for (int i = 0; i < this.openingBrackets.length; i++) {
/* 232 */           if (c == this.openingBrackets[i])
/* 233 */             return index; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 238 */     return -1;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\wkt\Symbols.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */