/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import java.util.SortedSet;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.commons.collections.set.ListOrderedSet;
/*     */ import org.apache.commons.collections.set.PredicatedSet;
/*     */ import org.apache.commons.collections.set.PredicatedSortedSet;
/*     */ import org.apache.commons.collections.set.SynchronizedSet;
/*     */ import org.apache.commons.collections.set.SynchronizedSortedSet;
/*     */ import org.apache.commons.collections.set.TransformedSet;
/*     */ import org.apache.commons.collections.set.TransformedSortedSet;
/*     */ import org.apache.commons.collections.set.TypedSet;
/*     */ import org.apache.commons.collections.set.TypedSortedSet;
/*     */ import org.apache.commons.collections.set.UnmodifiableSet;
/*     */ import org.apache.commons.collections.set.UnmodifiableSortedSet;
/*     */ 
/*     */ public class SetUtils {
/*  57 */   public static final Set EMPTY_SET = Collections.EMPTY_SET;
/*     */   
/*  62 */   public static final SortedSet EMPTY_SORTED_SET = UnmodifiableSortedSet.decorate(new TreeSet());
/*     */   
/*     */   public static boolean isEqualSet(Collection set1, Collection set2) {
/* 100 */     if (set1 == set2)
/* 101 */       return true; 
/* 103 */     if (set1 == null || set2 == null || set1.size() != set2.size())
/* 104 */       return false; 
/* 107 */     return set1.containsAll(set2);
/*     */   }
/*     */   
/*     */   public static int hashCodeForSet(Collection set) {
/* 123 */     if (set == null)
/* 124 */       return 0; 
/* 126 */     int hashCode = 0;
/* 127 */     Iterator it = set.iterator();
/* 128 */     Object obj = null;
/* 130 */     while (it.hasNext()) {
/* 131 */       obj = it.next();
/* 132 */       if (obj != null)
/* 133 */         hashCode += obj.hashCode(); 
/*     */     } 
/* 136 */     return hashCode;
/*     */   }
/*     */   
/*     */   public static Set synchronizedSet(Set set) {
/* 163 */     return SynchronizedSet.decorate(set);
/*     */   }
/*     */   
/*     */   public static Set unmodifiableSet(Set set) {
/* 176 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public static Set predicatedSet(Set set, Predicate predicate) {
/* 193 */     return PredicatedSet.decorate(set, predicate);
/*     */   }
/*     */   
/*     */   public static Set typedSet(Set set, Class type) {
/* 206 */     return TypedSet.decorate(set, type);
/*     */   }
/*     */   
/*     */   public static Set transformedSet(Set set, Transformer transformer) {
/* 222 */     return TransformedSet.decorate(set, transformer);
/*     */   }
/*     */   
/*     */   public static Set orderedSet(Set set) {
/* 237 */     return (Set)ListOrderedSet.decorate(set);
/*     */   }
/*     */   
/*     */   public static SortedSet synchronizedSortedSet(SortedSet set) {
/* 264 */     return SynchronizedSortedSet.decorate(set);
/*     */   }
/*     */   
/*     */   public static SortedSet unmodifiableSortedSet(SortedSet set) {
/* 277 */     return UnmodifiableSortedSet.decorate(set);
/*     */   }
/*     */   
/*     */   public static SortedSet predicatedSortedSet(SortedSet set, Predicate predicate) {
/* 294 */     return PredicatedSortedSet.decorate(set, predicate);
/*     */   }
/*     */   
/*     */   public static SortedSet typedSortedSet(SortedSet set, Class type) {
/* 307 */     return TypedSortedSet.decorate(set, type);
/*     */   }
/*     */   
/*     */   public static SortedSet transformedSortedSet(SortedSet set, Transformer transformer) {
/* 323 */     return TransformedSortedSet.decorate(set, transformer);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\SetUtils.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */