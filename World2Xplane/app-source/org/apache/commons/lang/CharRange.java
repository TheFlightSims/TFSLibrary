/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.lang.text.StrBuilder;
/*     */ 
/*     */ public final class CharRange implements Serializable {
/*     */   private static final long serialVersionUID = 8270183163158333422L;
/*     */   
/*     */   private final char start;
/*     */   
/*     */   private final char end;
/*     */   
/*     */   private final boolean negated;
/*     */   
/*     */   private transient String iToString;
/*     */   
/*     */   public static CharRange is(char ch) {
/*  67 */     return new CharRange(ch, ch, false);
/*     */   }
/*     */   
/*     */   public static CharRange isNot(char ch) {
/*  79 */     return new CharRange(ch, ch, true);
/*     */   }
/*     */   
/*     */   public static CharRange isIn(char start, char end) {
/*  92 */     return new CharRange(start, end, false);
/*     */   }
/*     */   
/*     */   public static CharRange isNotIn(char start, char end) {
/* 105 */     return new CharRange(start, end, true);
/*     */   }
/*     */   
/*     */   public CharRange(char ch) {
/* 115 */     this(ch, ch, false);
/*     */   }
/*     */   
/*     */   public CharRange(char ch, boolean negated) {
/* 128 */     this(ch, ch, negated);
/*     */   }
/*     */   
/*     */   public CharRange(char start, char end) {
/* 138 */     this(start, end, false);
/*     */   }
/*     */   
/*     */   public CharRange(char start, char end, boolean negated) {
/* 157 */     if (start > end) {
/* 158 */       char temp = start;
/* 159 */       start = end;
/* 160 */       end = temp;
/*     */     } 
/* 163 */     this.start = start;
/* 164 */     this.end = end;
/* 165 */     this.negated = negated;
/*     */   }
/*     */   
/*     */   public char getStart() {
/* 176 */     return this.start;
/*     */   }
/*     */   
/*     */   public char getEnd() {
/* 185 */     return this.end;
/*     */   }
/*     */   
/*     */   public boolean isNegated() {
/* 197 */     return this.negated;
/*     */   }
/*     */   
/*     */   public boolean contains(char ch) {
/* 209 */     return (((ch >= this.start && ch <= this.end)) != this.negated);
/*     */   }
/*     */   
/*     */   public boolean contains(CharRange range) {
/* 221 */     if (range == null)
/* 222 */       throw new IllegalArgumentException("The Range must not be null"); 
/* 224 */     if (this.negated) {
/* 225 */       if (range.negated)
/* 226 */         return (this.start >= range.start && this.end <= range.end); 
/* 228 */       return (range.end < this.start || range.start > this.end);
/*     */     } 
/* 230 */     if (range.negated)
/* 231 */       return (this.start == '\000' && this.end == Character.MAX_VALUE); 
/* 233 */     return (this.start <= range.start && this.end >= range.end);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 246 */     if (obj == this)
/* 247 */       return true; 
/* 249 */     if (!(obj instanceof CharRange))
/* 250 */       return false; 
/* 252 */     CharRange other = (CharRange)obj;
/* 253 */     return (this.start == other.start && this.end == other.end && this.negated == other.negated);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 262 */     return 83 + this.start + 7 * this.end + (this.negated ? 1 : 0);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 271 */     if (this.iToString == null) {
/* 272 */       StrBuilder buf = new StrBuilder(4);
/* 273 */       if (isNegated())
/* 274 */         buf.append('^'); 
/* 276 */       buf.append(this.start);
/* 277 */       if (this.start != this.end) {
/* 278 */         buf.append('-');
/* 279 */         buf.append(this.end);
/*     */       } 
/* 281 */       this.iToString = buf.toString();
/*     */     } 
/* 283 */     return this.iToString;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 296 */     return new CharacterIterator(this);
/*     */   }
/*     */   
/*     */   private static class CharacterIterator implements Iterator {
/*     */     private char current;
/*     */     
/*     */     private final CharRange range;
/*     */     
/*     */     private boolean hasNext;
/*     */     
/*     */     private CharacterIterator(CharRange r) {
/* 316 */       this.range = r;
/* 317 */       this.hasNext = true;
/* 319 */       if (this.range.negated) {
/* 320 */         if (this.range.start == '\000') {
/* 321 */           if (this.range.end == Character.MAX_VALUE) {
/* 323 */             this.hasNext = false;
/*     */           } else {
/* 325 */             this.current = (char)(this.range.end + 1);
/*     */           } 
/*     */         } else {
/* 328 */           this.current = Character.MIN_VALUE;
/*     */         } 
/*     */       } else {
/* 331 */         this.current = this.range.start;
/*     */       } 
/*     */     }
/*     */     
/*     */     private void prepareNext() {
/* 339 */       if (this.range.negated) {
/* 340 */         if (this.current == Character.MAX_VALUE) {
/* 341 */           this.hasNext = false;
/* 342 */         } else if (this.current + 1 == this.range.start) {
/* 343 */           if (this.range.end == Character.MAX_VALUE) {
/* 344 */             this.hasNext = false;
/*     */           } else {
/* 346 */             this.current = (char)(this.range.end + 1);
/*     */           } 
/*     */         } else {
/* 349 */           this.current = (char)(this.current + 1);
/*     */         } 
/* 351 */       } else if (this.current < this.range.end) {
/* 352 */         this.current = (char)(this.current + 1);
/*     */       } else {
/* 354 */         this.hasNext = false;
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 364 */       return this.hasNext;
/*     */     }
/*     */     
/*     */     public Object next() {
/* 373 */       if (!this.hasNext)
/* 374 */         throw new NoSuchElementException(); 
/* 376 */       char cur = this.current;
/* 377 */       prepareNext();
/* 378 */       return new Character(cur);
/*     */     }
/*     */     
/*     */     public void remove() {
/* 388 */       throw new UnsupportedOperationException();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\CharRange.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */