/*     */ package org.apache.commons.math3.stat;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.TreeMap;
/*     */ import org.apache.commons.math3.exception.MathIllegalArgumentException;
/*     */ import org.apache.commons.math3.exception.util.LocalizedFormats;
/*     */ 
/*     */ public class Frequency implements Serializable {
/*     */   private static final long serialVersionUID = -3845586908418844111L;
/*     */   
/*     */   private final TreeMap<Comparable<?>, Long> freqTable;
/*     */   
/*     */   public Frequency() {
/*  61 */     this.freqTable = new TreeMap<Comparable<?>, Long>();
/*     */   }
/*     */   
/*     */   public Frequency(Comparator<?> comparator) {
/*  71 */     this.freqTable = (TreeMap)new TreeMap<Object, Long>(comparator);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  82 */     NumberFormat nf = NumberFormat.getPercentInstance();
/*  83 */     StringBuilder outBuffer = new StringBuilder();
/*  84 */     outBuffer.append("Value \t Freq. \t Pct. \t Cum Pct. \n");
/*  85 */     Iterator<Comparable<?>> iter = this.freqTable.keySet().iterator();
/*  86 */     while (iter.hasNext()) {
/*  87 */       Comparable<?> value = iter.next();
/*  88 */       outBuffer.append(value);
/*  89 */       outBuffer.append('\t');
/*  90 */       outBuffer.append(getCount(value));
/*  91 */       outBuffer.append('\t');
/*  92 */       outBuffer.append(nf.format(getPct(value)));
/*  93 */       outBuffer.append('\t');
/*  94 */       outBuffer.append(nf.format(getCumPct(value)));
/*  95 */       outBuffer.append('\n');
/*     */     } 
/*  97 */     return outBuffer.toString();
/*     */   }
/*     */   
/*     */   public void addValue(Comparable<?> v) {
/* 111 */     Comparable<?> obj = v;
/* 112 */     if (v instanceof Integer)
/* 113 */       obj = Long.valueOf(((Integer)v).longValue()); 
/*     */     try {
/* 116 */       Long count = this.freqTable.get(obj);
/* 117 */       if (count == null) {
/* 118 */         this.freqTable.put(obj, Long.valueOf(1L));
/*     */       } else {
/* 120 */         this.freqTable.put(obj, Long.valueOf(count.longValue() + 1L));
/*     */       } 
/* 122 */     } catch (ClassCastException ex) {
/* 124 */       throw new MathIllegalArgumentException(LocalizedFormats.INSTANCES_NOT_COMPARABLE_TO_EXISTING_VALUES, new Object[] { v.getClass().getName() });
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addValue(int v) {
/* 136 */     addValue(Long.valueOf(v));
/*     */   }
/*     */   
/*     */   public void addValue(long v) {
/* 145 */     addValue(Long.valueOf(v));
/*     */   }
/*     */   
/*     */   public void addValue(char v) {
/* 154 */     addValue(Character.valueOf(v));
/*     */   }
/*     */   
/*     */   public void clear() {
/* 159 */     this.freqTable.clear();
/*     */   }
/*     */   
/*     */   public Iterator<Comparable<?>> valuesIterator() {
/* 172 */     return this.freqTable.keySet().iterator();
/*     */   }
/*     */   
/*     */   public long getSumFreq() {
/* 183 */     long result = 0L;
/* 184 */     Iterator<Long> iterator = this.freqTable.values().iterator();
/* 185 */     while (iterator.hasNext())
/* 186 */       result += ((Long)iterator.next()).longValue(); 
/* 188 */     return result;
/*     */   }
/*     */   
/*     */   public long getCount(Comparable<?> v) {
/* 199 */     if (v instanceof Integer)
/* 200 */       return getCount(((Integer)v).longValue()); 
/* 202 */     long result = 0L;
/*     */     try {
/* 204 */       Long count = this.freqTable.get(v);
/* 205 */       if (count != null)
/* 206 */         result = count.longValue(); 
/* 208 */     } catch (ClassCastException ex) {}
/* 211 */     return result;
/*     */   }
/*     */   
/*     */   public long getCount(int v) {
/* 221 */     return getCount(Long.valueOf(v));
/*     */   }
/*     */   
/*     */   public long getCount(long v) {
/* 231 */     return getCount(Long.valueOf(v));
/*     */   }
/*     */   
/*     */   public long getCount(char v) {
/* 241 */     return getCount(Character.valueOf(v));
/*     */   }
/*     */   
/*     */   public int getUniqueCount() {
/* 251 */     return this.freqTable.keySet().size();
/*     */   }
/*     */   
/*     */   public double getPct(Comparable<?> v) {
/* 264 */     long sumFreq = getSumFreq();
/* 265 */     if (sumFreq == 0L)
/* 266 */       return Double.NaN; 
/* 268 */     return getCount(v) / sumFreq;
/*     */   }
/*     */   
/*     */   public double getPct(int v) {
/* 279 */     return getPct(Long.valueOf(v));
/*     */   }
/*     */   
/*     */   public double getPct(long v) {
/* 290 */     return getPct(Long.valueOf(v));
/*     */   }
/*     */   
/*     */   public double getPct(char v) {
/* 301 */     return getPct(Character.valueOf(v));
/*     */   }
/*     */   
/*     */   public long getCumFreq(Comparable<?> v) {
/* 315 */     if (getSumFreq() == 0L)
/* 316 */       return 0L; 
/* 318 */     if (v instanceof Integer)
/* 319 */       return getCumFreq(((Integer)v).longValue()); 
/* 322 */     Comparator<Comparable<?>> c = (Comparator)this.freqTable.comparator();
/* 323 */     if (c == null)
/* 324 */       c = new NaturalComparator(); 
/* 326 */     long result = 0L;
/*     */     try {
/* 329 */       Long value = this.freqTable.get(v);
/* 330 */       if (value != null)
/* 331 */         result = value.longValue(); 
/* 333 */     } catch (ClassCastException ex) {
/* 334 */       return result;
/*     */     } 
/* 337 */     if (c.compare(v, this.freqTable.firstKey()) < 0)
/* 338 */       return 0L; 
/* 341 */     if (c.compare(v, this.freqTable.lastKey()) >= 0)
/* 342 */       return getSumFreq(); 
/* 345 */     Iterator<Comparable<?>> values = valuesIterator();
/* 346 */     while (values.hasNext()) {
/* 347 */       Comparable<?> nextValue = values.next();
/* 348 */       if (c.compare(v, nextValue) > 0) {
/* 349 */         result += getCount(nextValue);
/*     */         continue;
/*     */       } 
/* 351 */       return result;
/*     */     } 
/* 354 */     return result;
/*     */   }
/*     */   
/*     */   public long getCumFreq(int v) {
/* 366 */     return getCumFreq(Long.valueOf(v));
/*     */   }
/*     */   
/*     */   public long getCumFreq(long v) {
/* 378 */     return getCumFreq(Long.valueOf(v));
/*     */   }
/*     */   
/*     */   public long getCumFreq(char v) {
/* 390 */     return getCumFreq(Character.valueOf(v));
/*     */   }
/*     */   
/*     */   public double getCumPct(Comparable<?> v) {
/* 407 */     long sumFreq = getSumFreq();
/* 408 */     if (sumFreq == 0L)
/* 409 */       return Double.NaN; 
/* 411 */     return getCumFreq(v) / sumFreq;
/*     */   }
/*     */   
/*     */   public double getCumPct(int v) {
/* 424 */     return getCumPct(Long.valueOf(v));
/*     */   }
/*     */   
/*     */   public double getCumPct(long v) {
/* 437 */     return getCumPct(Long.valueOf(v));
/*     */   }
/*     */   
/*     */   public double getCumPct(char v) {
/* 450 */     return getCumPct(Character.valueOf(v));
/*     */   }
/*     */   
/*     */   private static class NaturalComparator<T extends Comparable<T>> implements Comparator<Comparable<T>>, Serializable {
/*     */     private static final long serialVersionUID = -3852193713161395148L;
/*     */     
/*     */     private NaturalComparator() {}
/*     */     
/*     */     public int compare(Comparable<T> o1, Comparable<T> o2) {
/* 477 */       return o1.compareTo((T)o2);
/*     */     }
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 484 */     int prime = 31;
/* 485 */     int result = 1;
/* 486 */     result = 31 * result + ((this.freqTable == null) ? 0 : this.freqTable.hashCode());
/* 488 */     return result;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 494 */     if (this == obj)
/* 495 */       return true; 
/* 497 */     if (!(obj instanceof Frequency))
/* 498 */       return false; 
/* 500 */     Frequency other = (Frequency)obj;
/* 501 */     if (this.freqTable == null) {
/* 502 */       if (other.freqTable != null)
/* 503 */         return false; 
/* 505 */     } else if (!this.freqTable.equals(other.freqTable)) {
/* 506 */       return false;
/*     */     } 
/* 508 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\stat\Frequency.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */