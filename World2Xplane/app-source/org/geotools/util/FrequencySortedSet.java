/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.SortedSet;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ 
/*     */ public class FrequencySortedSet<E> extends AbstractSet<E> implements SortedSet<E>, Comparator<E>, Serializable {
/*     */   private static final long serialVersionUID = 6034102231354388179L;
/*     */   
/*     */   private final Map<E, Integer> count;
/*     */   
/*     */   private final int order;
/*     */   
/*     */   private transient E[] sorted;
/*     */   
/*     */   private transient int[] frequencies;
/*     */   
/*     */   public FrequencySortedSet() {
/*  91 */     this(false);
/*     */   }
/*     */   
/*     */   public FrequencySortedSet(boolean reversed) {
/* 101 */     this(16, reversed);
/*     */   }
/*     */   
/*     */   public FrequencySortedSet(int initialCapacity, boolean reversed) {
/* 112 */     this.count = new LinkedHashMap<E, Integer>(initialCapacity);
/* 113 */     this.order = reversed ? -1 : 1;
/*     */   }
/*     */   
/*     */   public int size() {
/* 120 */     return this.count.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 128 */     return this.count.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean add(E element, int occurence) throws IllegalArgumentException {
/* 142 */     if (occurence != 0) {
/* 143 */       if (occurence < 0)
/* 144 */         throw new IllegalArgumentException(Errors.format(125, Integer.valueOf(occurence))); 
/* 147 */       this.sorted = null;
/* 148 */       occurence *= this.order;
/* 149 */       Integer n = this.count.put(element, Integer.valueOf(occurence));
/* 150 */       if (n == null)
/* 151 */         return true; 
/* 153 */       this.count.put(element, Integer.valueOf(n.intValue() + occurence));
/*     */     } 
/* 155 */     return false;
/*     */   }
/*     */   
/*     */   public boolean add(E element) {
/* 168 */     return add(element, 1);
/*     */   }
/*     */   
/*     */   public boolean contains(Object element) {
/* 179 */     return this.count.containsKey(element);
/*     */   }
/*     */   
/*     */   public boolean remove(Object element) {
/* 191 */     if (this.count.remove(element) != null) {
/* 192 */       this.sorted = null;
/* 193 */       return true;
/*     */     } 
/* 195 */     return false;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 204 */     this.sorted = null;
/* 205 */     this.count.clear();
/*     */   }
/*     */   
/*     */   public Iterator<E> iterator() {
/* 212 */     ensureSorted();
/* 213 */     return new Iter();
/*     */   }
/*     */   
/*     */   private final class Iter implements Iterator<E> {
/* 236 */     private final E[] elements = FrequencySortedSet.this.sorted;
/*     */     
/*     */     private int index;
/*     */     
/*     */     public boolean hasNext() {
/* 243 */       return (this.index < this.elements.length);
/*     */     }
/*     */     
/*     */     public E next() {
/* 250 */       if (this.index >= this.elements.length)
/* 251 */         throw new NoSuchElementException(); 
/* 253 */       return this.elements[this.index++];
/*     */     }
/*     */     
/*     */     public void remove() {
/* 260 */       if (this.index == 0)
/* 261 */         throw new IllegalStateException(); 
/* 263 */       if (!FrequencySortedSet.this.remove(this.elements[this.index - 1]))
/* 265 */         throw new IllegalStateException(); 
/*     */     }
/*     */   }
/*     */   
/*     */   public SortedSet<E> headSet(E toElement) {
/* 274 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */   
/*     */   public SortedSet<E> tailSet(E fromElement) {
/* 281 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */   
/*     */   public SortedSet<E> subSet(E fromElement, E toElement) {
/* 288 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */   
/*     */   public E first() throws NoSuchElementException {
/* 305 */     ensureSorted();
/* 306 */     if (this.sorted.length != 0)
/* 307 */       return this.sorted[0]; 
/* 309 */     throw new NoSuchElementException();
/*     */   }
/*     */   
/*     */   public E last() throws NoSuchElementException {
/* 327 */     ensureSorted();
/* 328 */     int length = this.sorted.length;
/* 329 */     if (length != 0)
/* 330 */       return this.sorted[length - 1]; 
/* 332 */     throw new NoSuchElementException();
/*     */   }
/*     */   
/*     */   private void ensureSorted() {
/* 344 */     if (this.sorted != null)
/*     */       return; 
/* 347 */     Map.Entry[] arrayOfEntry = (Map.Entry[])this.count.entrySet().toArray((Object[])new Map.Entry[this.count.size()]);
/* 348 */     Arrays.sort(arrayOfEntry, (Comparator)COMPARATOR);
/* 349 */     int length = arrayOfEntry.length;
/* 350 */     this.sorted = (E[])new Object[length];
/* 351 */     if (this.frequencies == null || this.frequencies.length != length)
/* 352 */       this.frequencies = new int[length]; 
/* 354 */     for (int i = 0; i < length; i++) {
/* 355 */       Map.Entry<E, Integer> entry = arrayOfEntry[i];
/* 356 */       this.sorted[i] = entry.getKey();
/* 357 */       this.frequencies[i] = Math.abs(((Integer)entry.getValue()).intValue());
/*     */     } 
/*     */   }
/*     */   
/* 365 */   private static final Comparator<Map.Entry<?, Integer>> COMPARATOR = new Comparator<Map.Entry<?, Integer>>() {
/*     */       public int compare(Map.Entry<?, Integer> o1, Map.Entry<?, Integer> o2) {
/* 367 */         return ((Integer)o1.getValue()).compareTo(o2.getValue());
/*     */       }
/*     */     };
/*     */   
/*     */   public final Comparator<E> comparator() {
/* 379 */     return this;
/*     */   }
/*     */   
/*     */   public final int compare(E o1, E o2) {
/* 394 */     return signedFrequency(o1) - signedFrequency(o2);
/*     */   }
/*     */   
/*     */   private int signedFrequency(E element) {
/* 402 */     Integer n = this.count.get(element);
/* 403 */     return (n != null) ? n.intValue() : 0;
/*     */   }
/*     */   
/*     */   public int frequency(E element) {
/* 413 */     return Math.abs(signedFrequency(element));
/*     */   }
/*     */   
/*     */   public int[] frequencies() {
/* 422 */     ensureSorted();
/* 423 */     return (int[])this.frequencies.clone();
/*     */   }
/*     */   
/*     */   public Object[] toArray() {
/* 431 */     ensureSorted();
/* 432 */     return (Object[])this.sorted.clone();
/*     */   }
/*     */   
/*     */   public <T> T[] toArray(T[] array) {
/* 446 */     ensureSorted();
/* 447 */     if (array.length < this.sorted.length)
/* 448 */       array = (T[])Array.newInstance(array.getClass().getComponentType(), this.sorted.length); 
/* 450 */     System.arraycopy(this.sorted, 0, array, 0, this.sorted.length);
/* 451 */     return array;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\FrequencySortedSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */