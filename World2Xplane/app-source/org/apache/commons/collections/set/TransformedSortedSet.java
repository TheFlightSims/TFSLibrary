/*     */ package org.apache.commons.collections.set;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import java.util.SortedSet;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ public class TransformedSortedSet extends TransformedSet implements SortedSet {
/*     */   private static final long serialVersionUID = -1675486811351124386L;
/*     */   
/*     */   public static SortedSet decorate(SortedSet set, Transformer transformer) {
/*  55 */     return new TransformedSortedSet(set, transformer);
/*     */   }
/*     */   
/*     */   protected TransformedSortedSet(SortedSet set, Transformer transformer) {
/*  70 */     super(set, transformer);
/*     */   }
/*     */   
/*     */   protected SortedSet getSortedSet() {
/*  79 */     return (SortedSet)this.collection;
/*     */   }
/*     */   
/*     */   public Object first() {
/*  84 */     return getSortedSet().first();
/*     */   }
/*     */   
/*     */   public Object last() {
/*  88 */     return getSortedSet().last();
/*     */   }
/*     */   
/*     */   public Comparator comparator() {
/*  92 */     return getSortedSet().comparator();
/*     */   }
/*     */   
/*     */   public SortedSet subSet(Object fromElement, Object toElement) {
/*  97 */     SortedSet set = getSortedSet().subSet(fromElement, toElement);
/*  98 */     return new TransformedSortedSet(set, this.transformer);
/*     */   }
/*     */   
/*     */   public SortedSet headSet(Object toElement) {
/* 102 */     SortedSet set = getSortedSet().headSet(toElement);
/* 103 */     return new TransformedSortedSet(set, this.transformer);
/*     */   }
/*     */   
/*     */   public SortedSet tailSet(Object fromElement) {
/* 107 */     SortedSet set = getSortedSet().tailSet(fromElement);
/* 108 */     return new TransformedSortedSet(set, this.transformer);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\set\TransformedSortedSet.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */