/*     */ package org.apache.commons.collections.list;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections.Factory;
/*     */ 
/*     */ public class LazyList extends AbstractSerializableListDecorator {
/*     */   private static final long serialVersionUID = -1708388017160694542L;
/*     */   
/*     */   protected final Factory factory;
/*     */   
/*     */   public static List decorate(List list, Factory factory) {
/*  80 */     return new LazyList(list, factory);
/*     */   }
/*     */   
/*     */   protected LazyList(List list, Factory factory) {
/*  92 */     super(list);
/*  93 */     if (factory == null)
/*  94 */       throw new IllegalArgumentException("Factory must not be null"); 
/*  96 */     this.factory = factory;
/*     */   }
/*     */   
/*     */   public Object get(int index) {
/* 111 */     int size = getList().size();
/* 112 */     if (index < size) {
/* 114 */       Object object1 = getList().get(index);
/* 115 */       if (object1 == null) {
/* 117 */         object1 = this.factory.create();
/* 118 */         getList().set(index, object1);
/* 119 */         return object1;
/*     */       } 
/* 122 */       return object1;
/*     */     } 
/* 126 */     for (int i = size; i < index; i++)
/* 127 */       getList().add(null); 
/* 130 */     Object object = this.factory.create();
/* 131 */     getList().add(object);
/* 132 */     return object;
/*     */   }
/*     */   
/*     */   public List subList(int fromIndex, int toIndex) {
/* 138 */     List sub = getList().subList(fromIndex, toIndex);
/* 139 */     return new LazyList(sub, this.factory);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\collections\list\LazyList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */