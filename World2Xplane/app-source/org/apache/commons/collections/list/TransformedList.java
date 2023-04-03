/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ import org.apache.commons.collections.collection.TransformedCollection;
/*     */ import org.apache.commons.collections.iterators.AbstractListIteratorDecorator;
/*     */ 
/*     */ public class TransformedList extends TransformedCollection implements List {
/*     */   private static final long serialVersionUID = 1077193035000013141L;
/*     */   
/*     */   public static List decorate(List list, Transformer transformer) {
/*  58 */     return new TransformedList(list, transformer);
/*     */   }
/*     */   
/*     */   protected TransformedList(List list, Transformer transformer) {
/*  73 */     super(list, transformer);
/*     */   }
/*     */   
/*     */   protected List getList() {
/*  82 */     return (List)this.collection;
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/*  87 */     return getList().get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/*  91 */     return getList().indexOf(object);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object object) {
/*  95 */     return getList().lastIndexOf(object);
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/*  99 */     return getList().remove(index);
/*     */   }
/*     */   
/*     */   public void add(int index, Object object) {
/* 104 */     object = transform(object);
/* 105 */     getList().add(index, object);
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/* 109 */     coll = transform(coll);
/* 110 */     return getList().addAll(index, coll);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/* 114 */     return listIterator(0);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int i) {
/* 118 */     return (ListIterator)new TransformedListIterator(this, getList().listIterator(i));
/*     */   }
/*     */   
/*     */   public Object set(int index, Object object) {
/* 122 */     object = transform(object);
/* 123 */     return getList().set(index, object);
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 127 */     List sub = getList().subList(fromIndex, toIndex);
/* 128 */     return new TransformedList(sub, this.transformer);
/*     */   }
/*     */   
/*     */   protected class TransformedListIterator extends AbstractListIteratorDecorator {
/*     */     private final TransformedList this$0;
/*     */     
/*     */     protected TransformedListIterator(TransformedList this$0, ListIterator iterator) {
/* 137 */       super(iterator);
/*     */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public void add(Object object) {
/* 141 */       object = this.this$0.transform(object);
/* 142 */       this.iterator.add(object);
/*     */     }
/*     */     
/*     */     public void set(Object object) {
/* 146 */       object = this.this$0.transform(object);
/* 147 */       this.iterator.set(object);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\list\TransformedList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */