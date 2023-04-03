/*     */ package org.apache.commons.collections.collection;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ public class TransformedCollection extends AbstractSerializableCollectionDecorator {
/*     */   private static final long serialVersionUID = 8692300188161871514L;
/*     */   
/*     */   protected final Transformer transformer;
/*     */   
/*     */   public static Collection decorate(Collection coll, Transformer transformer) {
/*  61 */     return new TransformedCollection(coll, transformer);
/*     */   }
/*     */   
/*     */   protected TransformedCollection(Collection coll, Transformer transformer) {
/*  76 */     super(coll);
/*  77 */     if (transformer == null)
/*  78 */       throw new IllegalArgumentException("Transformer must not be null"); 
/*  80 */     this.transformer = transformer;
/*     */   }
/*     */   
/*     */   protected Object transform(Object object) {
/*  92 */     return this.transformer.transform(object);
/*     */   }
/*     */   
/*     */   protected Collection transform(Collection coll) {
/* 104 */     List list = new ArrayList(coll.size());
/* 105 */     for (Iterator it = coll.iterator(); it.hasNext();)
/* 106 */       list.add(transform(it.next())); 
/* 108 */     return list;
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/* 113 */     object = transform(object);
/* 114 */     return getCollection().add(object);
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 118 */     coll = transform(coll);
/* 119 */     return getCollection().addAll(coll);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\collection\TransformedCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */