/*     */ package org.geotools.resources;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractList;
/*     */ import org.geotools.util.CheckedCollection;
/*     */ 
/*     */ public class UnmodifiableArrayList<E> extends AbstractList<E> implements CheckedCollection<E>, Serializable {
/*     */   private static final long serialVersionUID = -3605810209653785967L;
/*     */   
/*     */   private final E[] array;
/*     */   
/*     */   protected UnmodifiableArrayList(E[] array) {
/*  74 */     this.array = array;
/*     */   }
/*     */   
/*     */   public static <E> UnmodifiableArrayList<E> wrap(E[] array) {
/*  89 */     return new UnmodifiableArrayList<E>(array);
/*     */   }
/*     */   
/*     */   public Class<E> getElementType() {
/*  99 */     return (Class)this.array.getClass().getComponentType();
/*     */   }
/*     */   
/*     */   public int size() {
/* 106 */     return this.array.length;
/*     */   }
/*     */   
/*     */   public E get(int index) {
/* 113 */     return this.array[index];
/*     */   }
/*     */   
/*     */   public int indexOf(Object object) {
/* 126 */     if (object == null) {
/* 127 */       for (int i = 0; i < this.array.length; i++) {
/* 128 */         if (this.array[i] == null)
/* 129 */           return i; 
/*     */       } 
/*     */     } else {
/* 133 */       for (int i = 0; i < this.array.length; i++) {
/* 134 */         if (object.equals(this.array[i]))
/* 135 */           return i; 
/*     */       } 
/*     */     } 
/* 139 */     return -1;
/*     */   }
/*     */   
/*     */   public int lastIndexOf(Object object) {
/* 152 */     int i = this.array.length;
/* 153 */     if (object == null) {
/*     */       do {
/*     */       
/* 154 */       } while (--i >= 0 && 
/* 155 */         this.array[i] != null);
/*     */     } else {
/*     */       do {
/*     */       
/* 160 */       } while (--i >= 0 && 
/* 161 */         !object.equals(this.array[i]));
/*     */     } 
/* 166 */     return i;
/*     */   }
/*     */   
/*     */   public boolean contains(Object object) {
/* 178 */     int i = this.array.length;
/* 179 */     if (object == null) {
/* 180 */       while (--i >= 0) {
/* 181 */         if (this.array[i] == null)
/* 182 */           return true; 
/*     */       } 
/*     */     } else {
/* 186 */       while (--i >= 0) {
/* 187 */         if (object.equals(this.array[i]))
/* 188 */           return true; 
/*     */       } 
/*     */     } 
/* 192 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\UnmodifiableArrayList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */