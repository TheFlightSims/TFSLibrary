/*     */ package com.ctc.wstx.util;
/*     */ 
/*     */ public class SymbolTable {
/*     */   protected static final int DEFAULT_TABLE_SIZE = 128;
/*     */   
/*     */   protected static final float DEFAULT_FILL_FACTOR = 0.75F;
/*     */   
/*     */   protected static final String EMPTY_STRING = "";
/*     */   
/*     */   protected boolean mInternStrings;
/*     */   
/*     */   protected String[] mSymbols;
/*     */   
/*     */   protected Bucket[] mBuckets;
/*     */   
/*     */   protected int mSize;
/*     */   
/*     */   protected int mSizeThreshold;
/*     */   
/*     */   protected int mIndexMask;
/*     */   
/*     */   protected int mThisVersion;
/*     */   
/*     */   protected boolean mDirty;
/*     */   
/*     */   public SymbolTable() {
/* 160 */     this(true);
/*     */   }
/*     */   
/*     */   public SymbolTable(boolean internStrings) {
/* 167 */     this(internStrings, 128);
/*     */   }
/*     */   
/*     */   public SymbolTable(boolean internStrings, int initialSize) {
/* 174 */     this(internStrings, initialSize, 0.75F);
/*     */   }
/*     */   
/*     */   public SymbolTable(boolean internStrings, int initialSize, float fillFactor) {
/* 190 */     this.mInternStrings = internStrings;
/* 192 */     this.mThisVersion = 1;
/* 194 */     this.mDirty = true;
/* 197 */     if (initialSize < 1)
/* 198 */       throw new IllegalArgumentException("Can not use negative/zero initial size: " + initialSize); 
/* 204 */     int currSize = 4;
/* 205 */     while (currSize < initialSize)
/* 206 */       currSize += currSize; 
/* 208 */     initialSize = currSize;
/* 211 */     this.mSymbols = new String[initialSize];
/* 212 */     this.mBuckets = new Bucket[initialSize >> 1];
/* 214 */     this.mIndexMask = initialSize - 1;
/* 215 */     this.mSize = 0;
/* 218 */     if (fillFactor < 0.01F)
/* 219 */       throw new IllegalArgumentException("Fill factor can not be lower than 0.01."); 
/* 221 */     if (fillFactor > 10.0F)
/* 222 */       throw new IllegalArgumentException("Fill factor can not be higher than 10.0."); 
/* 224 */     this.mSizeThreshold = (int)((initialSize * fillFactor) + 0.5D);
/*     */   }
/*     */   
/*     */   private SymbolTable(boolean internStrings, String[] symbols, Bucket[] buckets, int size, int sizeThreshold, int indexMask, int version) {
/* 234 */     this.mInternStrings = internStrings;
/* 235 */     this.mSymbols = symbols;
/* 236 */     this.mBuckets = buckets;
/* 237 */     this.mSize = size;
/* 238 */     this.mSizeThreshold = sizeThreshold;
/* 239 */     this.mIndexMask = indexMask;
/* 240 */     this.mThisVersion = version;
/* 243 */     this.mDirty = false;
/*     */   }
/*     */   
/*     */   public SymbolTable makeChild() {
/*     */     boolean internStrings;
/*     */     String[] symbols;
/*     */     Bucket[] buckets;
/*     */     int size;
/*     */     int sizeThreshold;
/*     */     int indexMask;
/*     */     int version;
/* 268 */     synchronized (this) {
/* 269 */       internStrings = this.mInternStrings;
/* 270 */       symbols = this.mSymbols;
/* 271 */       buckets = this.mBuckets;
/* 272 */       size = this.mSize;
/* 273 */       sizeThreshold = this.mSizeThreshold;
/* 274 */       indexMask = this.mIndexMask;
/* 275 */       version = this.mThisVersion + 1;
/*     */     } 
/* 277 */     return new SymbolTable(internStrings, symbols, buckets, size, sizeThreshold, indexMask, version);
/*     */   }
/*     */   
/*     */   public synchronized void mergeChild(SymbolTable child) {
/* 291 */     if (child.size() <= size())
/*     */       return; 
/* 296 */     this.mSymbols = child.mSymbols;
/* 297 */     this.mBuckets = child.mBuckets;
/* 298 */     this.mSize = child.mSize;
/* 299 */     this.mSizeThreshold = child.mSizeThreshold;
/* 300 */     this.mIndexMask = child.mIndexMask;
/* 301 */     this.mThisVersion++;
/* 305 */     this.mDirty = false;
/* 313 */     child.mDirty = false;
/*     */   }
/*     */   
/*     */   public void setInternStrings(boolean state) {
/* 323 */     this.mInternStrings = state;
/*     */   }
/*     */   
/*     */   public int size() {
/* 332 */     return this.mSize;
/*     */   }
/*     */   
/*     */   public int version() {
/* 334 */     return this.mThisVersion;
/*     */   }
/*     */   
/*     */   public boolean isDirty() {
/* 336 */     return this.mDirty;
/*     */   }
/*     */   
/*     */   public boolean isDirectChildOf(SymbolTable t) {
/* 349 */     if (this.mThisVersion == t.mThisVersion + 1)
/* 350 */       return true; 
/* 352 */     return false;
/*     */   }
/*     */   
/*     */   public String findSymbol(char[] buffer, int start, int len, int hash) {
/* 377 */     if (len < 1)
/* 378 */       return ""; 
/* 381 */     hash &= this.mIndexMask;
/* 383 */     String sym = this.mSymbols[hash];
/* 386 */     if (sym != null) {
/* 388 */       if (sym.length() == len) {
/* 389 */         int i = 0;
/* 391 */         while (sym.charAt(i) == buffer[start + i]) {
/* 394 */           if (++i >= len)
/*     */             break; 
/*     */         } 
/* 396 */         if (i == len)
/* 397 */           return sym; 
/*     */       } 
/* 401 */       Bucket b = this.mBuckets[hash >> 1];
/* 402 */       if (b != null) {
/* 403 */         sym = b.find(buffer, start, len);
/* 404 */         if (sym != null)
/* 405 */           return sym; 
/*     */       } 
/*     */     } 
/* 411 */     if (this.mSize >= this.mSizeThreshold) {
/* 412 */       rehash();
/* 416 */       hash = calcHash(buffer, start, len) & this.mIndexMask;
/* 417 */     } else if (!this.mDirty) {
/* 419 */       copyArrays();
/* 420 */       this.mDirty = true;
/*     */     } 
/* 422 */     this.mSize++;
/* 424 */     String newSymbol = new String(buffer, start, len);
/* 425 */     if (this.mInternStrings)
/* 426 */       newSymbol = newSymbol.intern(); 
/* 429 */     if (this.mSymbols[hash] == null) {
/* 430 */       this.mSymbols[hash] = newSymbol;
/*     */     } else {
/* 432 */       int bix = hash >> 1;
/* 433 */       this.mBuckets[bix] = new Bucket(newSymbol, this.mBuckets[bix]);
/*     */     } 
/* 436 */     return newSymbol;
/*     */   }
/*     */   
/*     */   public String findSymbolIfExists(char[] buffer, int start, int len, int hash) {
/* 446 */     if (len < 1)
/* 447 */       return ""; 
/* 449 */     hash &= this.mIndexMask;
/* 451 */     String sym = this.mSymbols[hash];
/* 453 */     if (sym != null) {
/* 455 */       if (sym.length() == len) {
/* 456 */         int i = 0;
/* 458 */         while (sym.charAt(i) == buffer[start + i]) {
/* 461 */           if (++i >= len)
/*     */             break; 
/*     */         } 
/* 463 */         if (i == len)
/* 464 */           return sym; 
/*     */       } 
/* 468 */       Bucket b = this.mBuckets[hash >> 1];
/* 469 */       if (b != null) {
/* 470 */         sym = b.find(buffer, start, len);
/* 471 */         if (sym != null)
/* 472 */           return sym; 
/*     */       } 
/*     */     } 
/* 476 */     return null;
/*     */   }
/*     */   
/*     */   public String findSymbol(String str) {
/* 486 */     int len = str.length();
/* 488 */     if (len < 1)
/* 489 */       return ""; 
/* 492 */     int index = calcHash(str) & this.mIndexMask;
/* 493 */     String sym = this.mSymbols[index];
/* 496 */     if (sym != null) {
/* 498 */       if (sym.length() == len) {
/* 499 */         int i = 0;
/* 500 */         for (; i < len && 
/* 501 */           sym.charAt(i) == str.charAt(i); i++);
/* 506 */         if (i == len)
/* 507 */           return sym; 
/*     */       } 
/* 511 */       Bucket b = this.mBuckets[index >> 1];
/* 512 */       if (b != null) {
/* 513 */         sym = b.find(str);
/* 514 */         if (sym != null)
/* 515 */           return sym; 
/*     */       } 
/*     */     } 
/* 521 */     if (this.mSize >= this.mSizeThreshold) {
/* 522 */       rehash();
/* 526 */       index = calcHash(str) & this.mIndexMask;
/* 527 */     } else if (!this.mDirty) {
/* 529 */       copyArrays();
/* 530 */       this.mDirty = true;
/*     */     } 
/* 532 */     this.mSize++;
/* 534 */     if (this.mInternStrings)
/* 535 */       str = str.intern(); 
/* 538 */     if (this.mSymbols[index] == null) {
/* 539 */       this.mSymbols[index] = str;
/*     */     } else {
/* 541 */       int bix = index >> 1;
/* 542 */       this.mBuckets[bix] = new Bucket(str, this.mBuckets[bix]);
/*     */     } 
/* 545 */     return str;
/*     */   }
/*     */   
/*     */   public static int calcHash(char[] buffer, int start, int len) {
/* 558 */     int hash = buffer[0];
/* 559 */     for (int i = 1; i < len; i++)
/* 560 */       hash = hash * 31 + buffer[i]; 
/* 562 */     return hash;
/*     */   }
/*     */   
/*     */   public static int calcHash(String key) {
/* 566 */     int hash = key.charAt(0);
/* 567 */     for (int i = 1, len = key.length(); i < len; i++)
/* 568 */       hash = hash * 31 + key.charAt(i); 
/* 571 */     return hash;
/*     */   }
/*     */   
/*     */   private void copyArrays() {
/* 585 */     String[] oldSyms = this.mSymbols;
/* 586 */     int size = oldSyms.length;
/* 587 */     this.mSymbols = new String[size];
/* 588 */     System.arraycopy(oldSyms, 0, this.mSymbols, 0, size);
/* 589 */     Bucket[] oldBuckets = this.mBuckets;
/* 590 */     size = oldBuckets.length;
/* 591 */     this.mBuckets = new Bucket[size];
/* 592 */     System.arraycopy(oldBuckets, 0, this.mBuckets, 0, size);
/*     */   }
/*     */   
/*     */   private void rehash() {
/* 604 */     int size = this.mSymbols.length;
/* 605 */     int newSize = size + size;
/* 606 */     String[] oldSyms = this.mSymbols;
/* 607 */     Bucket[] oldBuckets = this.mBuckets;
/* 608 */     this.mSymbols = new String[newSize];
/* 609 */     this.mBuckets = new Bucket[newSize >> 1];
/* 611 */     this.mIndexMask = newSize - 1;
/* 612 */     this.mSizeThreshold += this.mSizeThreshold;
/* 614 */     int count = 0;
/*     */     int i;
/* 619 */     for (i = 0; i < size; i++) {
/* 620 */       String symbol = oldSyms[i];
/* 621 */       if (symbol != null) {
/* 622 */         count++;
/* 623 */         int index = calcHash(symbol) & this.mIndexMask;
/* 624 */         if (this.mSymbols[index] == null) {
/* 625 */           this.mSymbols[index] = symbol;
/*     */         } else {
/* 627 */           int bix = index >> 1;
/* 628 */           this.mBuckets[bix] = new Bucket(symbol, this.mBuckets[bix]);
/*     */         } 
/*     */       } 
/*     */     } 
/* 633 */     size >>= 1;
/* 634 */     for (i = 0; i < size; i++) {
/* 635 */       Bucket b = oldBuckets[i];
/* 636 */       while (b != null) {
/* 637 */         count++;
/* 638 */         String symbol = b.getSymbol();
/* 639 */         int index = calcHash(symbol) & this.mIndexMask;
/* 640 */         if (this.mSymbols[index] == null) {
/* 641 */           this.mSymbols[index] = symbol;
/*     */         } else {
/* 643 */           int bix = index >> 1;
/* 644 */           this.mBuckets[bix] = new Bucket(symbol, this.mBuckets[bix]);
/*     */         } 
/* 646 */         b = b.getNext();
/*     */       } 
/*     */     } 
/* 650 */     if (count != this.mSize)
/* 651 */       throw new IllegalStateException("Internal error on SymbolTable.rehash(): had " + this.mSize + " entries; now have " + count + "."); 
/*     */   }
/*     */   
/*     */   public double calcAvgSeek() {
/* 662 */     int count = 0;
/*     */     int i, len;
/* 664 */     for (i = 0, len = this.mSymbols.length; i < len; i++) {
/* 665 */       if (this.mSymbols[i] != null)
/* 666 */         count++; 
/*     */     } 
/* 670 */     for (i = 0, len = this.mBuckets.length; i < len; i++) {
/* 671 */       Bucket b = this.mBuckets[i];
/* 672 */       int cost = 2;
/* 673 */       while (b != null) {
/* 674 */         count += cost;
/* 675 */         cost++;
/* 676 */         b = b.getNext();
/*     */       } 
/*     */     } 
/* 680 */     return count / this.mSize;
/*     */   }
/*     */   
/*     */   static final class Bucket {
/*     */     private final String mSymbol;
/*     */     
/*     */     private final Bucket mNext;
/*     */     
/*     */     public Bucket(String symbol, Bucket next) {
/* 698 */       this.mSymbol = symbol;
/* 699 */       this.mNext = next;
/*     */     }
/*     */     
/*     */     public String getSymbol() {
/* 702 */       return this.mSymbol;
/*     */     }
/*     */     
/*     */     public Bucket getNext() {
/* 703 */       return this.mNext;
/*     */     }
/*     */     
/*     */     public String find(char[] buf, int start, int len) {
/* 706 */       String sym = this.mSymbol;
/* 707 */       Bucket b = this.mNext;
/*     */       while (true) {
/* 710 */         if (sym.length() == len) {
/* 711 */           int i = 0;
/* 713 */           while (sym.charAt(i) == buf[start + i]) {
/* 716 */             if (++i >= len)
/*     */               break; 
/*     */           } 
/* 717 */           if (i == len)
/* 718 */             return sym; 
/*     */         } 
/* 721 */         if (b == null)
/*     */           break; 
/* 724 */         sym = b.getSymbol();
/* 725 */         b = b.getNext();
/*     */       } 
/* 727 */       return null;
/*     */     }
/*     */     
/*     */     public String find(String str) {
/* 731 */       String sym = this.mSymbol;
/* 732 */       Bucket b = this.mNext;
/*     */       while (true) {
/* 735 */         if (sym.equals(str))
/* 736 */           return sym; 
/* 738 */         if (b == null)
/*     */           break; 
/* 741 */         sym = b.getSymbol();
/* 742 */         b = b.getNext();
/*     */       } 
/* 744 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\SymbolTable.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */