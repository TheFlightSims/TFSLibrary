/*     */ package org.geotools.util;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.SortedSet;
/*     */ 
/*     */ public final class Comparators {
/*  49 */   private static final Comparator<Collection<Comparable>> COLLECTIONS = new Collections();
/*     */   
/*     */   private static final class Collections implements Comparator<Collection<Comparable>>, Serializable {
/*     */     private static final long serialVersionUID = -8926770873102046405L;
/*     */     
/*     */     private Collections() {}
/*     */     
/*     */     public int compare(Collection<Comparable> c1, Collection<Comparable> c2) {
/*  65 */       Iterator<Comparable> i1 = c1.iterator();
/*  66 */       Iterator<Comparable> i2 = c2.iterator();
/*     */       while (true) {
/*  69 */         boolean h1 = i1.hasNext();
/*  70 */         boolean h2 = i2.hasNext();
/*  71 */         if (!h1)
/*  71 */           return h2 ? -1 : 0; 
/*  72 */         if (!h2)
/*  72 */           return 1; 
/*  73 */         Comparable<Comparable> e1 = i1.next();
/*  74 */         Comparable e2 = i2.next();
/*  76 */         int cmp = e1.compareTo(e2);
/*  77 */         int c = cmp;
/*  78 */         if (c != 0)
/*  79 */           return c; 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static <T extends Comparable<T>> Comparator<List<T>> forLists() {
/*  99 */     return (Comparator)COLLECTIONS;
/*     */   }
/*     */   
/*     */   public static <T extends Comparable<T>> Comparator<SortedSet<T>> forSortedSets() {
/* 111 */     return (Comparator)COLLECTIONS;
/*     */   }
/*     */   
/*     */   public static <T extends Comparable<T>> Comparator<Collection<T>> forCollections() {
/* 126 */     return (Comparator)COLLECTIONS;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotool\\util\Comparators.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */