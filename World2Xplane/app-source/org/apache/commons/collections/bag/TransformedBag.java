/*     */ package org.apache.commons.collections.bag;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections.Bag;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ import org.apache.commons.collections.collection.TransformedCollection;
/*     */ import org.apache.commons.collections.set.TransformedSet;
/*     */ 
/*     */ public class TransformedBag extends TransformedCollection implements Bag {
/*     */   private static final long serialVersionUID = 5421170911299074185L;
/*     */   
/*     */   public static Bag decorate(Bag bag, Transformer transformer) {
/*  59 */     return new TransformedBag(bag, transformer);
/*     */   }
/*     */   
/*     */   protected TransformedBag(Bag bag, Transformer transformer) {
/*  74 */     super((Collection)bag, transformer);
/*     */   }
/*     */   
/*     */   protected Bag getBag() {
/*  83 */     return (Bag)this.collection;
/*     */   }
/*     */   
/*     */   public int getCount(Object object) {
/*  88 */     return getBag().getCount(object);
/*     */   }
/*     */   
/*     */   public boolean remove(Object object, int nCopies) {
/*  92 */     return getBag().remove(object, nCopies);
/*     */   }
/*     */   
/*     */   public boolean add(Object object, int nCopies) {
/*  97 */     object = transform(object);
/*  98 */     return getBag().add(object, nCopies);
/*     */   }
/*     */   
/*     */   public Set uniqueSet() {
/* 102 */     Set set = getBag().uniqueSet();
/* 103 */     return TransformedSet.decorate(set, this.transformer);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\bag\TransformedBag.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */