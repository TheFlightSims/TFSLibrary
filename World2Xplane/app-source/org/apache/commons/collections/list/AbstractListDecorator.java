/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.collection.AbstractCollectionDecorator;
/*     */ 
/*     */ public abstract class AbstractListDecorator extends AbstractCollectionDecorator implements List {
/*     */   protected AbstractListDecorator() {}
/*     */   
/*     */   protected AbstractListDecorator(List list) {
/*  52 */     super(list);
/*     */   }
/*     */   
/*     */   protected List getList() {
/*  61 */     return (List)getCollection();
/*     */   }
/*     */   
/*     */   public void add(int index, Object object) {
/*  66 */     getList().add(index, object);
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/*  70 */     return getList().addAll(index, coll);
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/*  74 */     return getList().get(index);
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/*  78 */     return getList().indexOf(object);
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object object) {
/*  82 */     return getList().lastIndexOf(object);
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/*  86 */     return getList().listIterator();
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int index) {
/*  90 */     return getList().listIterator(index);
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/*  94 */     return getList().remove(index);
/*     */   }
/*     */   
/*     */   public Object set(int index, Object object) {
/*  98 */     return getList().set(index, object);
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 102 */     return getList().subList(fromIndex, toIndex);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\list\AbstractListDecorator.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */