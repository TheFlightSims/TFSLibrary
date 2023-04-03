/*     */ package org.apache.commons.collections.collection;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ 
/*     */ public class PredicatedCollection extends AbstractSerializableCollectionDecorator {
/*     */   private static final long serialVersionUID = -5259182142076705162L;
/*     */   
/*     */   protected final Predicate predicate;
/*     */   
/*     */   public static Collection decorate(Collection coll, Predicate predicate) {
/*  64 */     return new PredicatedCollection(coll, predicate);
/*     */   }
/*     */   
/*     */   protected PredicatedCollection(Collection coll, Predicate predicate) {
/*  80 */     super(coll);
/*  81 */     if (predicate == null)
/*  82 */       throw new IllegalArgumentException("Predicate must not be null"); 
/*  84 */     this.predicate = predicate;
/*  85 */     for (Iterator it = coll.iterator(); it.hasNext();)
/*  86 */       validate(it.next()); 
/*     */   }
/*     */   
/*     */   protected void validate(Object object) {
/* 100 */     if (!this.predicate.evaluate(object))
/* 101 */       throw new IllegalArgumentException("Cannot add Object '" + object + "' - Predicate rejected it"); 
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/* 115 */     validate(object);
/* 116 */     return getCollection().add(object);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 129 */     for (Iterator it = coll.iterator(); it.hasNext();)
/* 130 */       validate(it.next()); 
/* 132 */     return getCollection().addAll(coll);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\collection\PredicatedCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */