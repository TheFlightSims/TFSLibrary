/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import org.apache.commons.collections.collection.SynchronizedCollection;
/*     */ 
/*     */ public class SynchronizedList extends SynchronizedCollection implements List {
/*     */   private static final long serialVersionUID = -1403835447328619437L;
/*     */   
/*     */   public static List decorate(List list) {
/*  50 */     return new SynchronizedList(list);
/*     */   }
/*     */   
/*     */   protected SynchronizedList(List list) {
/*  61 */     super(list);
/*     */   }
/*     */   
/*     */   protected SynchronizedList(List list, Object lock) {
/*  72 */     super(list, lock);
/*     */   }
/*     */   
/*     */   protected List getList() {
/*  81 */     return (List)this.collection;
/*     */   }
/*     */   
/*     */   public void add(int index, Object object) {
/*  86 */     synchronized (this.lock) {
/*  87 */       getList().add(index, object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean addAll(int index, Collection coll) {
/*  92 */     synchronized (this.lock) {
/*  93 */       return getList().addAll(index, coll);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/*  98 */     synchronized (this.lock) {
/*  99 */       return getList().get(index);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/* 104 */     synchronized (this.lock) {
/* 105 */       return getList().indexOf(object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object object) {
/* 110 */     synchronized (this.lock) {
/* 111 */       return getList().lastIndexOf(object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ListIterator listIterator() {
/* 126 */     return getList().listIterator();
/*     */   }
/*     */   
/*     */   public ListIterator listIterator(int index) {
/* 140 */     return getList().listIterator(index);
/*     */   }
/*     */   
/*     */   public Object remove(int index) {
/* 144 */     synchronized (this.lock) {
/* 145 */       return getList().remove(index);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object set(int index, Object object) {
/* 150 */     synchronized (this.lock) {
/* 151 */       return getList().set(index, object);
/*     */     } 
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 156 */     synchronized (this.lock) {
/* 157 */       List list = getList().subList(fromIndex, toIndex);
/* 160 */       return new SynchronizedList(list, this.lock);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\list\SynchronizedList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */