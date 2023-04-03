/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class AbstractObjectList implements Cloneable, Serializable {
/*     */   private static final long serialVersionUID = 7789833772597351595L;
/*     */   
/*     */   public static final int DEFAULT_INITIAL_CAPACITY = 8;
/*     */   
/*     */   private transient Object[] objects;
/*     */   
/*  70 */   private int size = 0;
/*     */   
/*  73 */   private int increment = 8;
/*     */   
/*     */   protected AbstractObjectList() {
/*  79 */     this(8);
/*     */   }
/*     */   
/*     */   protected AbstractObjectList(int initialCapacity) {
/*  88 */     this(initialCapacity, initialCapacity);
/*     */   }
/*     */   
/*     */   protected AbstractObjectList(int initialCapacity, int increment) {
/*  99 */     this.objects = new Object[initialCapacity];
/* 100 */     this.increment = increment;
/*     */   }
/*     */   
/*     */   protected Object get(int index) {
/* 112 */     Object result = null;
/* 113 */     if (index >= 0 && index < this.size)
/* 114 */       result = this.objects[index]; 
/* 116 */     return result;
/*     */   }
/*     */   
/*     */   protected void set(int index, Object object) {
/* 126 */     if (index < 0)
/* 127 */       throw new IllegalArgumentException("Requires index >= 0."); 
/* 129 */     if (index >= this.objects.length) {
/* 130 */       Object[] enlarged = new Object[index + this.increment];
/* 131 */       System.arraycopy(this.objects, 0, enlarged, 0, this.objects.length);
/* 132 */       this.objects = enlarged;
/*     */     } 
/* 134 */     this.objects[index] = object;
/* 135 */     this.size = Math.max(this.size, index + 1);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 142 */     Arrays.fill(this.objects, (Object)null);
/* 143 */     this.size = 0;
/*     */   }
/*     */   
/*     */   public int size() {
/* 152 */     return this.size;
/*     */   }
/*     */   
/*     */   protected int indexOf(Object object) {
/* 164 */     for (int index = 0; index < this.size; index++) {
/* 165 */       if (this.objects[index] == object)
/* 166 */         return index; 
/*     */     } 
/* 169 */     return -1;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 181 */     if (obj == null)
/* 182 */       return false; 
/* 185 */     if (obj == this)
/* 186 */       return true; 
/* 189 */     if (!(obj instanceof AbstractObjectList))
/* 190 */       return false; 
/* 193 */     AbstractObjectList other = (AbstractObjectList)obj;
/* 194 */     int listSize = size();
/* 195 */     for (int i = 0; i < listSize; i++) {
/* 196 */       if (!ObjectUtilities.equal(get(i), other.get(i)))
/* 197 */         return false; 
/*     */     } 
/* 200 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 209 */     return super.hashCode();
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 223 */     AbstractObjectList clone = (AbstractObjectList)super.clone();
/* 224 */     if (this.objects != null) {
/* 225 */       clone.objects = new Object[this.objects.length];
/* 226 */       System.arraycopy(this.objects, 0, clone.objects, 0, this.objects.length);
/*     */     } 
/* 230 */     return clone;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 244 */     stream.defaultWriteObject();
/* 245 */     int count = size();
/* 246 */     stream.writeInt(count);
/* 247 */     for (int i = 0; i < count; i++) {
/* 248 */       Object object = get(i);
/* 249 */       if (object != null && object instanceof Serializable) {
/* 250 */         stream.writeInt(i);
/* 251 */         stream.writeObject(object);
/*     */       } else {
/* 254 */         stream.writeInt(-1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 271 */     stream.defaultReadObject();
/* 272 */     this.objects = new Object[this.size];
/* 273 */     int count = stream.readInt();
/* 274 */     for (int i = 0; i < count; i++) {
/* 275 */       int index = stream.readInt();
/* 276 */       if (index != -1)
/* 277 */         set(index, stream.readObject()); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\AbstractObjectList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */