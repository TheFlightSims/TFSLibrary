/*     */ package org.apache.commons.math3.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ public class OpenIntToDoubleHashMap implements Serializable {
/*     */   protected static final byte FREE = 0;
/*     */   
/*     */   protected static final byte FULL = 1;
/*     */   
/*     */   protected static final byte REMOVED = 2;
/*     */   
/*     */   private static final long serialVersionUID = -3646337053166149105L;
/*     */   
/*     */   private static final float LOAD_FACTOR = 0.5F;
/*     */   
/*     */   private static final int DEFAULT_EXPECTED_SIZE = 16;
/*     */   
/*     */   private static final int RESIZE_MULTIPLIER = 2;
/*     */   
/*     */   private static final int PERTURB_SHIFT = 5;
/*     */   
/*     */   private int[] keys;
/*     */   
/*     */   private double[] values;
/*     */   
/*     */   private byte[] states;
/*     */   
/*     */   private final double missingEntries;
/*     */   
/*     */   private int size;
/*     */   
/*     */   private int mask;
/*     */   
/*     */   private transient int count;
/*     */   
/*     */   public OpenIntToDoubleHashMap() {
/*  92 */     this(16, Double.NaN);
/*     */   }
/*     */   
/*     */   public OpenIntToDoubleHashMap(double missingEntries) {
/* 100 */     this(16, missingEntries);
/*     */   }
/*     */   
/*     */   public OpenIntToDoubleHashMap(int expectedSize) {
/* 108 */     this(expectedSize, Double.NaN);
/*     */   }
/*     */   
/*     */   public OpenIntToDoubleHashMap(int expectedSize, double missingEntries) {
/* 118 */     int capacity = computeCapacity(expectedSize);
/* 119 */     this.keys = new int[capacity];
/* 120 */     this.values = new double[capacity];
/* 121 */     this.states = new byte[capacity];
/* 122 */     this.missingEntries = missingEntries;
/* 123 */     this.mask = capacity - 1;
/*     */   }
/*     */   
/*     */   public OpenIntToDoubleHashMap(OpenIntToDoubleHashMap source) {
/* 131 */     int length = source.keys.length;
/* 132 */     this.keys = new int[length];
/* 133 */     System.arraycopy(source.keys, 0, this.keys, 0, length);
/* 134 */     this.values = new double[length];
/* 135 */     System.arraycopy(source.values, 0, this.values, 0, length);
/* 136 */     this.states = new byte[length];
/* 137 */     System.arraycopy(source.states, 0, this.states, 0, length);
/* 138 */     this.missingEntries = source.missingEntries;
/* 139 */     this.size = source.size;
/* 140 */     this.mask = source.mask;
/* 141 */     this.count = source.count;
/*     */   }
/*     */   
/*     */   private static int computeCapacity(int expectedSize) {
/* 150 */     if (expectedSize == 0)
/* 151 */       return 1; 
/* 153 */     int capacity = (int)FastMath.ceil((expectedSize / 0.5F));
/* 154 */     int powerOfTwo = Integer.highestOneBit(capacity);
/* 155 */     if (powerOfTwo == capacity)
/* 156 */       return capacity; 
/* 158 */     return nextPowerOfTwo(capacity);
/*     */   }
/*     */   
/*     */   private static int nextPowerOfTwo(int i) {
/* 167 */     return Integer.highestOneBit(i) << 1;
/*     */   }
/*     */   
/*     */   public double get(int key) {
/* 177 */     int hash = hashOf(key);
/* 178 */     int index = hash & this.mask;
/* 179 */     if (containsKey(key, index))
/* 180 */       return this.values[index]; 
/* 183 */     if (this.states[index] == 0)
/* 184 */       return this.missingEntries; 
/* 187 */     int j = index;
/*     */     int perturb;
/* 188 */     for (perturb = perturb(hash); this.states[index] != 0; perturb >>= 5) {
/* 189 */       j = probe(perturb, j);
/* 190 */       index = j & this.mask;
/* 191 */       if (containsKey(key, index))
/* 192 */         return this.values[index]; 
/*     */     } 
/* 196 */     return this.missingEntries;
/*     */   }
/*     */   
/*     */   public boolean containsKey(int key) {
/* 207 */     int hash = hashOf(key);
/* 208 */     int index = hash & this.mask;
/* 209 */     if (containsKey(key, index))
/* 210 */       return true; 
/* 213 */     if (this.states[index] == 0)
/* 214 */       return false; 
/* 217 */     int j = index;
/*     */     int perturb;
/* 218 */     for (perturb = perturb(hash); this.states[index] != 0; perturb >>= 5) {
/* 219 */       j = probe(perturb, j);
/* 220 */       index = j & this.mask;
/* 221 */       if (containsKey(key, index))
/* 222 */         return true; 
/*     */     } 
/* 226 */     return false;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 238 */     return new Iterator();
/*     */   }
/*     */   
/*     */   private static int perturb(int hash) {
/* 247 */     return hash & Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   private int findInsertionIndex(int key) {
/* 256 */     return findInsertionIndex(this.keys, this.states, key, this.mask);
/*     */   }
/*     */   
/*     */   private static int findInsertionIndex(int[] keys, byte[] states, int key, int mask) {
/* 269 */     int hash = hashOf(key);
/* 270 */     int index = hash & mask;
/* 271 */     if (states[index] == 0)
/* 272 */       return index; 
/* 273 */     if (states[index] == 1 && keys[index] == key)
/* 274 */       return changeIndexSign(index); 
/* 277 */     int perturb = perturb(hash);
/* 278 */     int j = index;
/* 279 */     if (states[index] == 1)
/*     */       do {
/* 281 */         j = probe(perturb, j);
/* 282 */         index = j & mask;
/* 283 */         perturb >>= 5;
/* 285 */       } while (states[index] == 1 && keys[index] != key); 
/* 291 */     if (states[index] == 0)
/* 292 */       return index; 
/* 293 */     if (states[index] == 1)
/* 296 */       return changeIndexSign(index); 
/* 299 */     int firstRemoved = index;
/*     */     while (true) {
/* 301 */       j = probe(perturb, j);
/* 302 */       index = j & mask;
/* 304 */       if (states[index] == 0)
/* 305 */         return firstRemoved; 
/* 306 */       if (states[index] == 1 && keys[index] == key)
/* 307 */         return changeIndexSign(index); 
/* 310 */       perturb >>= 5;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int probe(int perturb, int j) {
/* 323 */     return (j << 2) + j + perturb + 1;
/*     */   }
/*     */   
/*     */   private static int changeIndexSign(int index) {
/* 332 */     return -index - 1;
/*     */   }
/*     */   
/*     */   public int size() {
/* 340 */     return this.size;
/*     */   }
/*     */   
/*     */   public double remove(int key) {
/* 351 */     int hash = hashOf(key);
/* 352 */     int index = hash & this.mask;
/* 353 */     if (containsKey(key, index))
/* 354 */       return doRemove(index); 
/* 357 */     if (this.states[index] == 0)
/* 358 */       return this.missingEntries; 
/* 361 */     int j = index;
/*     */     int perturb;
/* 362 */     for (perturb = perturb(hash); this.states[index] != 0; perturb >>= 5) {
/* 363 */       j = probe(perturb, j);
/* 364 */       index = j & this.mask;
/* 365 */       if (containsKey(key, index))
/* 366 */         return doRemove(index); 
/*     */     } 
/* 370 */     return this.missingEntries;
/*     */   }
/*     */   
/*     */   private boolean containsKey(int key, int index) {
/* 382 */     return ((key != 0 || this.states[index] == 1) && this.keys[index] == key);
/*     */   }
/*     */   
/*     */   private double doRemove(int index) {
/* 391 */     this.keys[index] = 0;
/* 392 */     this.states[index] = 2;
/* 393 */     double previous = this.values[index];
/* 394 */     this.values[index] = this.missingEntries;
/* 395 */     this.size--;
/* 396 */     this.count++;
/* 397 */     return previous;
/*     */   }
/*     */   
/*     */   public double put(int key, double value) {
/* 407 */     int index = findInsertionIndex(key);
/* 408 */     double previous = this.missingEntries;
/* 409 */     boolean newMapping = true;
/* 410 */     if (index < 0) {
/* 411 */       index = changeIndexSign(index);
/* 412 */       previous = this.values[index];
/* 413 */       newMapping = false;
/*     */     } 
/* 415 */     this.keys[index] = key;
/* 416 */     this.states[index] = 1;
/* 417 */     this.values[index] = value;
/* 418 */     if (newMapping) {
/* 419 */       this.size++;
/* 420 */       if (shouldGrowTable())
/* 421 */         growTable(); 
/* 423 */       this.count++;
/*     */     } 
/* 425 */     return previous;
/*     */   }
/*     */   
/*     */   private void growTable() {
/* 434 */     int oldLength = this.states.length;
/* 435 */     int[] oldKeys = this.keys;
/* 436 */     double[] oldValues = this.values;
/* 437 */     byte[] oldStates = this.states;
/* 439 */     int newLength = 2 * oldLength;
/* 440 */     int[] newKeys = new int[newLength];
/* 441 */     double[] newValues = new double[newLength];
/* 442 */     byte[] newStates = new byte[newLength];
/* 443 */     int newMask = newLength - 1;
/* 444 */     for (int i = 0; i < oldLength; i++) {
/* 445 */       if (oldStates[i] == 1) {
/* 446 */         int key = oldKeys[i];
/* 447 */         int index = findInsertionIndex(newKeys, newStates, key, newMask);
/* 448 */         newKeys[index] = key;
/* 449 */         newValues[index] = oldValues[i];
/* 450 */         newStates[index] = 1;
/*     */       } 
/*     */     } 
/* 454 */     this.mask = newMask;
/* 455 */     this.keys = newKeys;
/* 456 */     this.values = newValues;
/* 457 */     this.states = newStates;
/*     */   }
/*     */   
/*     */   private boolean shouldGrowTable() {
/* 466 */     return (this.size > (this.mask + 1) * 0.5F);
/*     */   }
/*     */   
/*     */   private static int hashOf(int key) {
/* 475 */     int h = key ^ key >>> 20 ^ key >>> 12;
/* 476 */     return h ^ h >>> 7 ^ h >>> 4;
/*     */   }
/*     */   
/*     */   public class Iterator {
/* 498 */     private final int referenceCount = OpenIntToDoubleHashMap.this.count;
/*     */     
/*     */     private int current;
/*     */     
/* 501 */     private int next = -1;
/*     */     
/*     */     private Iterator() {
/*     */       try {
/* 503 */         advance();
/* 504 */       } catch (NoSuchElementException nsee) {}
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 515 */       return (this.next >= 0);
/*     */     }
/*     */     
/*     */     public int key() throws ConcurrentModificationException, NoSuchElementException {
/* 526 */       if (this.referenceCount != OpenIntToDoubleHashMap.this.count)
/* 527 */         throw new ConcurrentModificationException(); 
/* 529 */       if (this.current < 0)
/* 530 */         throw new NoSuchElementException(); 
/* 532 */       return OpenIntToDoubleHashMap.this.keys[this.current];
/*     */     }
/*     */     
/*     */     public double value() throws ConcurrentModificationException, NoSuchElementException {
/* 543 */       if (this.referenceCount != OpenIntToDoubleHashMap.this.count)
/* 544 */         throw new ConcurrentModificationException(); 
/* 546 */       if (this.current < 0)
/* 547 */         throw new NoSuchElementException(); 
/* 549 */       return OpenIntToDoubleHashMap.this.values[this.current];
/*     */     }
/*     */     
/*     */     public void advance() throws ConcurrentModificationException, NoSuchElementException {
/* 560 */       if (this.referenceCount != OpenIntToDoubleHashMap.this.count)
/* 561 */         throw new ConcurrentModificationException(); 
/* 565 */       this.current = this.next;
/*     */       while (true) {
/*     */         try {
/* 569 */           if (OpenIntToDoubleHashMap.this.states[++this.next] != 1)
/*     */             continue; 
/*     */           break;
/* 572 */         } catch (ArrayIndexOutOfBoundsException e) {
/* 573 */           this.next = -2;
/* 574 */           if (this.current < 0)
/* 575 */             throw new NoSuchElementException(); 
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 592 */     stream.defaultReadObject();
/* 593 */     this.count = 0;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\OpenIntToDoubleHashMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */