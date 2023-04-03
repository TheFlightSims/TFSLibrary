/*     */ package org.apache.commons.collections.comparators;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.BitSet;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class ComparatorChain implements Comparator, Serializable {
/*     */   private static final long serialVersionUID = -721644942746081630L;
/*     */   
/*  63 */   protected List comparatorChain = null;
/*     */   
/*  65 */   protected BitSet orderingBits = null;
/*     */   
/*     */   protected boolean isLocked = false;
/*     */   
/*     */   public ComparatorChain() {
/*  77 */     this(new ArrayList(), new BitSet());
/*     */   }
/*     */   
/*     */   public ComparatorChain(Comparator comparator) {
/*  87 */     this(comparator, false);
/*     */   }
/*     */   
/*     */   public ComparatorChain(Comparator comparator, boolean reverse) {
/*  98 */     this.comparatorChain = new ArrayList();
/*  99 */     this.comparatorChain.add(comparator);
/* 100 */     this.orderingBits = new BitSet(1);
/* 101 */     if (reverse == true)
/* 102 */       this.orderingBits.set(0); 
/*     */   }
/*     */   
/*     */   public ComparatorChain(List list) {
/* 115 */     this(list, new BitSet(list.size()));
/*     */   }
/*     */   
/*     */   public ComparatorChain(List list, BitSet bits) {
/* 134 */     this.comparatorChain = list;
/* 135 */     this.orderingBits = bits;
/*     */   }
/*     */   
/*     */   public void addComparator(Comparator comparator) {
/* 146 */     addComparator(comparator, false);
/*     */   }
/*     */   
/*     */   public void addComparator(Comparator comparator, boolean reverse) {
/* 157 */     checkLocked();
/* 159 */     this.comparatorChain.add(comparator);
/* 160 */     if (reverse == true)
/* 161 */       this.orderingBits.set(this.comparatorChain.size() - 1); 
/*     */   }
/*     */   
/*     */   public void setComparator(int index, Comparator comparator) throws IndexOutOfBoundsException {
/* 176 */     setComparator(index, comparator, false);
/*     */   }
/*     */   
/*     */   public void setComparator(int index, Comparator comparator, boolean reverse) {
/* 188 */     checkLocked();
/* 190 */     this.comparatorChain.set(index, comparator);
/* 191 */     if (reverse == true) {
/* 192 */       this.orderingBits.set(index);
/*     */     } else {
/* 194 */       this.orderingBits.clear(index);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setForwardSort(int index) {
/* 206 */     checkLocked();
/* 207 */     this.orderingBits.clear(index);
/*     */   }
/*     */   
/*     */   public void setReverseSort(int index) {
/* 217 */     checkLocked();
/* 218 */     this.orderingBits.set(index);
/*     */   }
/*     */   
/*     */   public int size() {
/* 227 */     return this.comparatorChain.size();
/*     */   }
/*     */   
/*     */   public boolean isLocked() {
/* 239 */     return this.isLocked;
/*     */   }
/*     */   
/*     */   private void checkLocked() {
/* 244 */     if (this.isLocked == true)
/* 245 */       throw new UnsupportedOperationException("Comparator ordering cannot be changed after the first comparison is performed"); 
/*     */   }
/*     */   
/*     */   private void checkChainIntegrity() {
/* 250 */     if (this.comparatorChain.size() == 0)
/* 251 */       throw new UnsupportedOperationException("ComparatorChains must contain at least one Comparator"); 
/*     */   }
/*     */   
/*     */   public int compare(Object o1, Object o2) throws UnsupportedOperationException {
/* 268 */     if (!this.isLocked) {
/* 269 */       checkChainIntegrity();
/* 270 */       this.isLocked = true;
/*     */     } 
/* 274 */     Iterator comparators = this.comparatorChain.iterator();
/* 275 */     for (int comparatorIndex = 0; comparators.hasNext(); comparatorIndex++) {
/* 277 */       Comparator comparator = comparators.next();
/* 278 */       int retval = comparator.compare(o1, o2);
/* 279 */       if (retval != 0) {
/* 281 */         if (this.orderingBits.get(comparatorIndex) == true)
/* 282 */           if (Integer.MIN_VALUE == retval) {
/* 283 */             retval = Integer.MAX_VALUE;
/*     */           } else {
/* 285 */             retval *= -1;
/*     */           }  
/* 289 */         return retval;
/*     */       } 
/*     */     } 
/* 295 */     return 0;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 307 */     int hash = 0;
/* 308 */     if (null != this.comparatorChain)
/* 309 */       hash ^= this.comparatorChain.hashCode(); 
/* 311 */     if (null != this.orderingBits)
/* 312 */       hash ^= this.orderingBits.hashCode(); 
/* 314 */     return hash;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 334 */     if (this == object)
/* 335 */       return true; 
/* 336 */     if (null == object)
/* 337 */       return false; 
/* 338 */     if (object.getClass().equals(getClass())) {
/* 339 */       ComparatorChain chain = (ComparatorChain)object;
/* 340 */       return (((null == this.orderingBits) ? (null == chain.orderingBits) : this.orderingBits.equals(chain.orderingBits)) && ((null == this.comparatorChain) ? (null == chain.comparatorChain) : this.comparatorChain.equals(chain.comparatorChain)));
/*     */     } 
/* 343 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\comparators\ComparatorChain.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */