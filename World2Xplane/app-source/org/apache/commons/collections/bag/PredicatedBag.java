/*     */ package org.apache.commons.collections.bag;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.Bag;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ import org.apache.commons.collections.collection.PredicatedCollection;
/*     */ 
/*     */ public class PredicatedBag extends PredicatedCollection implements Bag {
/*     */   private static final long serialVersionUID = -2575833140344736876L;
/*     */   
/*     */   public static Bag decorate(Bag bag, Predicate predicate) {
/*  63 */     return new PredicatedBag(bag, predicate);
/*     */   }
/*     */   
/*     */   protected PredicatedBag(Bag bag, Predicate predicate) {
/*  79 */     super((Collection)bag, predicate);
/*     */   }
/*     */   
/*     */   protected Bag getBag() {
/*  88 */     return (Bag)getCollection();
/*     */   }
/*     */   
/*     */   public boolean add(Object object, int count) {
/*  93 */     validate(object);
/*  94 */     return getBag().add(object, count);
/*     */   }
/*     */   
/*     */   public boolean remove(Object object, int count) {
/*  98 */     return getBag().remove(object, count);
/*     */   }
/*     */   
/*     */   public Set uniqueSet() {
/* 102 */     return getBag().uniqueSet();
/*     */   }
/*     */   
/*     */   public int getCount(Object object) {
/* 106 */     return getBag().getCount(object);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bag\PredicatedBag.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */