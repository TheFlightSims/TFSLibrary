/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import org.apache.commons.collections.comparators.BooleanComparator;
/*     */ import org.apache.commons.collections.comparators.ComparableComparator;
/*     */ import org.apache.commons.collections.comparators.ComparatorChain;
/*     */ import org.apache.commons.collections.comparators.NullComparator;
/*     */ import org.apache.commons.collections.comparators.ReverseComparator;
/*     */ import org.apache.commons.collections.comparators.TransformingComparator;
/*     */ 
/*     */ public class ComparatorUtils {
/*  57 */   public static final Comparator NATURAL_COMPARATOR = (Comparator)ComparableComparator.getInstance();
/*     */   
/*     */   public static Comparator naturalComparator() {
/*  65 */     return NATURAL_COMPARATOR;
/*     */   }
/*     */   
/*     */   public static Comparator chainedComparator(Comparator comparator1, Comparator comparator2) {
/*  80 */     return chainedComparator(new Comparator[] { comparator1, comparator2 });
/*     */   }
/*     */   
/*     */   public static Comparator chainedComparator(Comparator[] comparators) {
/*  93 */     ComparatorChain chain = new ComparatorChain();
/*  94 */     for (int i = 0; i < comparators.length; i++) {
/*  95 */       if (comparators[i] == null)
/*  96 */         throw new NullPointerException("Comparator cannot be null"); 
/*  98 */       chain.addComparator(comparators[i]);
/*     */     } 
/* 100 */     return (Comparator)chain;
/*     */   }
/*     */   
/*     */   public static Comparator chainedComparator(Collection comparators) {
/* 115 */     return chainedComparator((Comparator[])comparators.toArray((Object[])new Comparator[comparators.size()]));
/*     */   }
/*     */   
/*     */   public static Comparator reversedComparator(Comparator comparator) {
/* 128 */     if (comparator == null)
/* 129 */       comparator = NATURAL_COMPARATOR; 
/* 131 */     return (Comparator)new ReverseComparator(comparator);
/*     */   }
/*     */   
/*     */   public static Comparator booleanComparator(boolean trueFirst) {
/* 147 */     return (Comparator)BooleanComparator.getBooleanComparator(trueFirst);
/*     */   }
/*     */   
/*     */   public static Comparator nullLowComparator(Comparator comparator) {
/* 162 */     if (comparator == null)
/* 163 */       comparator = NATURAL_COMPARATOR; 
/* 165 */     return (Comparator)new NullComparator(comparator, false);
/*     */   }
/*     */   
/*     */   public static Comparator nullHighComparator(Comparator comparator) {
/* 180 */     if (comparator == null)
/* 181 */       comparator = NATURAL_COMPARATOR; 
/* 183 */     return (Comparator)new NullComparator(comparator, true);
/*     */   }
/*     */   
/*     */   public static Comparator transformedComparator(Comparator comparator, Transformer transformer) {
/* 199 */     if (comparator == null)
/* 200 */       comparator = NATURAL_COMPARATOR; 
/* 202 */     return (Comparator)new TransformingComparator(transformer, comparator);
/*     */   }
/*     */   
/*     */   public static Object min(Object o1, Object o2, Comparator comparator) {
/* 216 */     if (comparator == null)
/* 217 */       comparator = NATURAL_COMPARATOR; 
/* 219 */     int c = comparator.compare(o1, o2);
/* 220 */     return (c < 0) ? o1 : o2;
/*     */   }
/*     */   
/*     */   public static Object max(Object o1, Object o2, Comparator comparator) {
/* 234 */     if (comparator == null)
/* 235 */       comparator = NATURAL_COMPARATOR; 
/* 237 */     int c = comparator.compare(o1, o2);
/* 238 */     return (c > 0) ? o1 : o2;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\ComparatorUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */