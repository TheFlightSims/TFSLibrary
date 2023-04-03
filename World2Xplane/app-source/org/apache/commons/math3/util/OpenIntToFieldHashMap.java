/*     */ package org.apache.commons.math3.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.math3.Field;
/*     */ import org.apache.commons.math3.FieldElement;
/*     */ 
/*     */ public class OpenIntToFieldHashMap<T extends FieldElement<T>> implements Serializable {
/*     */   protected static final byte FREE = 0;
/*     */   
/*     */   protected static final byte FULL = 1;
/*     */   
/*     */   protected static final byte REMOVED = 2;
/*     */   
/*     */   private static final long serialVersionUID = -9179080286849120720L;
/*     */   
/*     */   private static final float LOAD_FACTOR = 0.5F;
/*     */   
/*     */   private static final int DEFAULT_EXPECTED_SIZE = 16;
/*     */   
/*     */   private static final int RESIZE_MULTIPLIER = 2;
/*     */   
/*     */   private static final int PERTURB_SHIFT = 5;
/*     */   
/*     */   private final Field<T> field;
/*     */   
/*     */   private int[] keys;
/*     */   
/*     */   private T[] values;
/*     */   
/*     */   private byte[] states;
/*     */   
/*     */   private final T missingEntries;
/*     */   
/*     */   private int size;
/*     */   
/*     */   private int mask;
/*     */   
/*     */   private transient int count;
/*     */   
/*     */   public OpenIntToFieldHashMap(Field<T> field) {
/* 100 */     this(field, 16, (T)field.getZero());
/*     */   }
/*     */   
/*     */   public OpenIntToFieldHashMap(Field<T> field, T missingEntries) {
/* 109 */     this(field, 16, missingEntries);
/*     */   }
/*     */   
/*     */   public OpenIntToFieldHashMap(Field<T> field, int expectedSize) {
/* 118 */     this(field, expectedSize, (T)field.getZero());
/*     */   }
/*     */   
/*     */   public OpenIntToFieldHashMap(Field<T> field, int expectedSize, T missingEntries) {
/* 129 */     this.field = field;
/* 130 */     int capacity = computeCapacity(expectedSize);
/* 131 */     this.keys = new int[capacity];
/* 132 */     this.values = buildArray(capacity);
/* 133 */     this.states = new byte[capacity];
/* 134 */     this.missingEntries = missingEntries;
/* 135 */     this.mask = capacity - 1;
/*     */   }
/*     */   
/*     */   public OpenIntToFieldHashMap(OpenIntToFieldHashMap<T> source) {
/* 143 */     this.field = source.field;
/* 144 */     int length = source.keys.length;
/* 145 */     this.keys = new int[length];
/* 146 */     System.arraycopy(source.keys, 0, this.keys, 0, length);
/* 147 */     this.values = buildArray(length);
/* 148 */     System.arraycopy(source.values, 0, this.values, 0, length);
/* 149 */     this.states = new byte[length];
/* 150 */     System.arraycopy(source.states, 0, this.states, 0, length);
/* 151 */     this.missingEntries = source.missingEntries;
/* 152 */     this.size = source.size;
/* 153 */     this.mask = source.mask;
/* 154 */     this.count = source.count;
/*     */   }
/*     */   
/*     */   private static int computeCapacity(int expectedSize) {
/* 163 */     if (expectedSize == 0)
/* 164 */       return 1; 
/* 166 */     int capacity = (int)FastMath.ceil((expectedSize / 0.5F));
/* 167 */     int powerOfTwo = Integer.highestOneBit(capacity);
/* 168 */     if (powerOfTwo == capacity)
/* 169 */       return capacity; 
/* 171 */     return nextPowerOfTwo(capacity);
/*     */   }
/*     */   
/*     */   private static int nextPowerOfTwo(int i) {
/* 180 */     return Integer.highestOneBit(i) << 1;
/*     */   }
/*     */   
/*     */   public T get(int key) {
/* 190 */     int hash = hashOf(key);
/* 191 */     int index = hash & this.mask;
/* 192 */     if (containsKey(key, index))
/* 193 */       return this.values[index]; 
/* 196 */     if (this.states[index] == 0)
/* 197 */       return this.missingEntries; 
/* 200 */     int j = index;
/*     */     int perturb;
/* 201 */     for (perturb = perturb(hash); this.states[index] != 0; perturb >>= 5) {
/* 202 */       j = probe(perturb, j);
/* 203 */       index = j & this.mask;
/* 204 */       if (containsKey(key, index))
/* 205 */         return this.values[index]; 
/*     */     } 
/* 209 */     return this.missingEntries;
/*     */   }
/*     */   
/*     */   public boolean containsKey(int key) {
/* 220 */     int hash = hashOf(key);
/* 221 */     int index = hash & this.mask;
/* 222 */     if (containsKey(key, index))
/* 223 */       return true; 
/* 226 */     if (this.states[index] == 0)
/* 227 */       return false; 
/* 230 */     int j = index;
/*     */     int perturb;
/* 231 */     for (perturb = perturb(hash); this.states[index] != 0; perturb >>= 5) {
/* 232 */       j = probe(perturb, j);
/* 233 */       index = j & this.mask;
/* 234 */       if (containsKey(key, index))
/* 235 */         return true; 
/*     */     } 
/* 239 */     return false;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/* 251 */     return new Iterator();
/*     */   }
/*     */   
/*     */   private static int perturb(int hash) {
/* 260 */     return hash & Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   private int findInsertionIndex(int key) {
/* 269 */     return findInsertionIndex(this.keys, this.states, key, this.mask);
/*     */   }
/*     */   
/*     */   private static int findInsertionIndex(int[] keys, byte[] states, int key, int mask) {
/* 282 */     int hash = hashOf(key);
/* 283 */     int index = hash & mask;
/* 284 */     if (states[index] == 0)
/* 285 */       return index; 
/* 286 */     if (states[index] == 1 && keys[index] == key)
/* 287 */       return changeIndexSign(index); 
/* 290 */     int perturb = perturb(hash);
/* 291 */     int j = index;
/* 292 */     if (states[index] == 1)
/*     */       do {
/* 294 */         j = probe(perturb, j);
/* 295 */         index = j & mask;
/* 296 */         perturb >>= 5;
/* 298 */       } while (states[index] == 1 && keys[index] != key); 
/* 304 */     if (states[index] == 0)
/* 305 */       return index; 
/* 306 */     if (states[index] == 1)
/* 309 */       return changeIndexSign(index); 
/* 312 */     int firstRemoved = index;
/*     */     while (true) {
/* 314 */       j = probe(perturb, j);
/* 315 */       index = j & mask;
/* 317 */       if (states[index] == 0)
/* 318 */         return firstRemoved; 
/* 319 */       if (states[index] == 1 && keys[index] == key)
/* 320 */         return changeIndexSign(index); 
/* 323 */       perturb >>= 5;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int probe(int perturb, int j) {
/* 336 */     return (j << 2) + j + perturb + 1;
/*     */   }
/*     */   
/*     */   private static int changeIndexSign(int index) {
/* 345 */     return -index - 1;
/*     */   }
/*     */   
/*     */   public int size() {
/* 353 */     return this.size;
/*     */   }
/*     */   
/*     */   public T remove(int key) {
/* 364 */     int hash = hashOf(key);
/* 365 */     int index = hash & this.mask;
/* 366 */     if (containsKey(key, index))
/* 367 */       return doRemove(index); 
/* 370 */     if (this.states[index] == 0)
/* 371 */       return this.missingEntries; 
/* 374 */     int j = index;
/*     */     int perturb;
/* 375 */     for (perturb = perturb(hash); this.states[index] != 0; perturb >>= 5) {
/* 376 */       j = probe(perturb, j);
/* 377 */       index = j & this.mask;
/* 378 */       if (containsKey(key, index))
/* 379 */         return doRemove(index); 
/*     */     } 
/* 383 */     return this.missingEntries;
/*     */   }
/*     */   
/*     */   private boolean containsKey(int key, int index) {
/* 395 */     return ((key != 0 || this.states[index] == 1) && this.keys[index] == key);
/*     */   }
/*     */   
/*     */   private T doRemove(int index) {
/* 404 */     this.keys[index] = 0;
/* 405 */     this.states[index] = 2;
/* 406 */     T previous = this.values[index];
/* 407 */     this.values[index] = this.missingEntries;
/* 408 */     this.size--;
/* 409 */     this.count++;
/* 410 */     return previous;
/*     */   }
/*     */   
/*     */   public T put(int key, T value) {
/* 420 */     int index = findInsertionIndex(key);
/* 421 */     T previous = this.missingEntries;
/* 422 */     boolean newMapping = true;
/* 423 */     if (index < 0) {
/* 424 */       index = changeIndexSign(index);
/* 425 */       previous = this.values[index];
/* 426 */       newMapping = false;
/*     */     } 
/* 428 */     this.keys[index] = key;
/* 429 */     this.states[index] = 1;
/* 430 */     this.values[index] = value;
/* 431 */     if (newMapping) {
/* 432 */       this.size++;
/* 433 */       if (shouldGrowTable())
/* 434 */         growTable(); 
/* 436 */       this.count++;
/*     */     } 
/* 438 */     return previous;
/*     */   }
/*     */   
/*     */   private void growTable() {
/* 447 */     int oldLength = this.states.length;
/* 448 */     int[] oldKeys = this.keys;
/* 449 */     T[] oldValues = this.values;
/* 450 */     byte[] oldStates = this.states;
/* 452 */     int newLength = 2 * oldLength;
/* 453 */     int[] newKeys = new int[newLength];
/* 454 */     T[] newValues = buildArray(newLength);
/* 455 */     byte[] newStates = new byte[newLength];
/* 456 */     int newMask = newLength - 1;
/* 457 */     for (int i = 0; i < oldLength; i++) {
/* 458 */       if (oldStates[i] == 1) {
/* 459 */         int key = oldKeys[i];
/* 460 */         int index = findInsertionIndex(newKeys, newStates, key, newMask);
/* 461 */         newKeys[index] = key;
/* 462 */         newValues[index] = oldValues[i];
/* 463 */         newStates[index] = 1;
/*     */       } 
/*     */     } 
/* 467 */     this.mask = newMask;
/* 468 */     this.keys = newKeys;
/* 469 */     this.values = newValues;
/* 470 */     this.states = newStates;
/*     */   }
/*     */   
/*     */   private boolean shouldGrowTable() {
/* 479 */     return (this.size > (this.mask + 1) * 0.5F);
/*     */   }
/*     */   
/*     */   private static int hashOf(int key) {
/* 488 */     int h = key ^ key >>> 20 ^ key >>> 12;
/* 489 */     return h ^ h >>> 7 ^ h >>> 4;
/*     */   }
/*     */   
/*     */   public class Iterator {
/* 511 */     private final int referenceCount = OpenIntToFieldHashMap.this.count;
/*     */     
/*     */     private int current;
/*     */     
/* 514 */     private int next = -1;
/*     */     
/*     */     private Iterator() {
/*     */       try {
/* 516 */         advance();
/* 517 */       } catch (NoSuchElementException nsee) {}
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 528 */       return (this.next >= 0);
/*     */     }
/*     */     
/*     */     public int key() throws ConcurrentModificationException, NoSuchElementException {
/* 539 */       if (this.referenceCount != OpenIntToFieldHashMap.this.count)
/* 540 */         throw new ConcurrentModificationException(); 
/* 542 */       if (this.current < 0)
/* 543 */         throw new NoSuchElementException(); 
/* 545 */       return OpenIntToFieldHashMap.this.keys[this.current];
/*     */     }
/*     */     
/*     */     public T value() throws ConcurrentModificationException, NoSuchElementException {
/* 556 */       if (this.referenceCount != OpenIntToFieldHashMap.this.count)
/* 557 */         throw new ConcurrentModificationException(); 
/* 559 */       if (this.current < 0)
/* 560 */         throw new NoSuchElementException(); 
/* 562 */       return (T)OpenIntToFieldHashMap.this.values[this.current];
/*     */     }
/*     */     
/*     */     public void advance() throws ConcurrentModificationException, NoSuchElementException {
/* 573 */       if (this.referenceCount != OpenIntToFieldHashMap.this.count)
/* 574 */         throw new ConcurrentModificationException(); 
/* 578 */       this.current = this.next;
/*     */       while (true) {
/*     */         try {
/* 582 */           if (OpenIntToFieldHashMap.this.states[++this.next] != 1)
/*     */             continue; 
/*     */           break;
/* 585 */         } catch (ArrayIndexOutOfBoundsException e) {
/* 586 */           this.next = -2;
/* 587 */           if (this.current < 0)
/* 588 */             throw new NoSuchElementException(); 
/*     */         } 
/*     */         return;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 605 */     stream.defaultReadObject();
/* 606 */     this.count = 0;
/*     */   }
/*     */   
/*     */   private T[] buildArray(int length) {
/* 615 */     return (T[])Array.newInstance(this.field.getRuntimeClass(), length);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math\\util\OpenIntToFieldHashMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */