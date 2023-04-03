/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.Predicate;
/*     */ import org.apache.commons.collections.collection.PredicatedCollection;
/*     */ import org.apache.commons.collections.iterators.AbstractListIteratorDecorator;
/*     */ 
/*     */ public class PredicatedList extends PredicatedCollection implements List {
/*     */   private static final long serialVersionUID = -5722039223898659102L;
/*     */   
/*     */   public static List decorate(List list, Predicate predicate) {
/*  64 */     return new PredicatedList(list, predicate);
/*     */   }
/*     */   
/*     */   protected PredicatedList(List list, Predicate predicate) {
/*  80 */     super(list, predicate);
/*     */   }
/*     */   
/*     */   protected List getList() {
/*  89 */     return (List)getCollection();
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/*  94 */     return getList().get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/*  98 */     return getList().indexOf(object);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object object) {
/* 102 */     return getList().lastIndexOf(object);
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 106 */     return getList().remove(index);
/*     */   }
/*     */   
/*     */   public void add(int index, Object object) {
/* 111 */     validate(object);
/* 112 */     getList().add(index, object);
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/* 116 */     for (Iterator it = coll.iterator(); it.hasNext();)
/* 117 */       validate(it.next()); 
/* 119 */     return getList().addAll(index, coll);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/* 123 */     return listIterator(0);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int i) {
/* 127 */     return (ListIterator)new PredicatedListIterator(this, getList().listIterator(i));
/*     */   }
/*     */   
/*     */   public Object set(int index, Object object) {
/* 131 */     validate(object);
/* 132 */     return getList().set(index, object);
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 136 */     List sub = getList().subList(fromIndex, toIndex);
/* 137 */     return new PredicatedList(sub, this.predicate);
/*     */   }
/*     */   
/*     */   protected class PredicatedListIterator extends AbstractListIteratorDecorator {
/*     */     private final PredicatedList this$0;
/*     */     
/*     */     protected PredicatedListIterator(PredicatedList this$0, ListIterator iterator) {
/* 146 */       super(iterator);
/*     */       this.this$0 = this$0;
/*     */     }
/*     */     
/*     */     public void add(Object object) {
/* 150 */       this.this$0.validate(object);
/* 151 */       this.iterator.add(object);
/*     */     }
/*     */     
/*     */     public void set(Object object) {
/* 155 */       this.this$0.validate(object);
/* 156 */       this.iterator.set(object);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\list\PredicatedList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */