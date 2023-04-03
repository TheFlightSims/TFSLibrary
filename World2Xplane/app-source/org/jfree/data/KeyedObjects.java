/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class KeyedObjects implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 1321582394193530984L;
/*     */   
/*  67 */   private List data = new ArrayList();
/*     */   
/*     */   public int getItemCount() {
/*  76 */     return this.data.size();
/*     */   }
/*     */   
/*     */   public Object getObject(int item) {
/*  87 */     Object result = null;
/*  88 */     if (item >= 0 && item < this.data.size()) {
/*  89 */       KeyedObject kobj = this.data.get(item);
/*  90 */       if (kobj != null)
/*  91 */         result = kobj.getObject(); 
/*     */     } 
/*  94 */     return result;
/*     */   }
/*     */   
/*     */   public Comparable getKey(int index) {
/* 107 */     Comparable result = null;
/* 108 */     if (index >= 0 && index < this.data.size()) {
/* 109 */       KeyedObject item = this.data.get(index);
/* 110 */       if (item != null)
/* 111 */         result = item.getKey(); 
/*     */     } 
/* 114 */     return result;
/*     */   }
/*     */   
/*     */   public int getIndex(Comparable key) {
/* 125 */     int result = -1;
/* 126 */     int i = 0;
/* 127 */     Iterator iterator = this.data.iterator();
/* 128 */     while (iterator.hasNext()) {
/* 129 */       KeyedObject ko = iterator.next();
/* 130 */       if (ko.getKey().equals(key))
/* 131 */         result = i; 
/* 133 */       i++;
/*     */     } 
/* 135 */     return result;
/*     */   }
/*     */   
/*     */   public List getKeys() {
/* 144 */     List result = new ArrayList();
/* 145 */     Iterator iterator = this.data.iterator();
/* 146 */     while (iterator.hasNext()) {
/* 147 */       KeyedObject ko = iterator.next();
/* 148 */       result.add(ko.getKey());
/*     */     } 
/* 150 */     return result;
/*     */   }
/*     */   
/*     */   public Object getObject(Comparable key) {
/* 162 */     return getObject(getIndex(key));
/*     */   }
/*     */   
/*     */   public void addObject(Comparable key, Object object) {
/* 173 */     setObject(key, object);
/*     */   }
/*     */   
/*     */   public void setObject(Comparable key, Object object) {
/* 185 */     int keyIndex = getIndex(key);
/* 186 */     if (keyIndex >= 0) {
/* 187 */       KeyedObject ko = this.data.get(keyIndex);
/* 188 */       ko.setObject(object);
/*     */     } else {
/* 191 */       KeyedObject ko = new KeyedObject(key, object);
/* 192 */       this.data.add(ko);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void removeValue(int index) {
/* 202 */     this.data.remove(index);
/*     */   }
/*     */   
/*     */   public void removeValue(Comparable key) {
/* 211 */     removeValue(getIndex(key));
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 222 */     KeyedObjects clone = (KeyedObjects)super.clone();
/* 223 */     clone.data = new ArrayList();
/* 224 */     Iterator iterator = this.data.iterator();
/* 225 */     while (iterator.hasNext()) {
/* 226 */       KeyedObject ko = iterator.next();
/* 227 */       clone.data.add(ko.clone());
/*     */     } 
/* 229 */     return clone;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 241 */     if (o == null)
/* 242 */       return false; 
/* 244 */     if (o == this)
/* 245 */       return true; 
/* 248 */     if (!(o instanceof KeyedObjects))
/* 249 */       return false; 
/* 252 */     KeyedObjects kos = (KeyedObjects)o;
/* 253 */     int count = getItemCount();
/* 254 */     if (count != kos.getItemCount())
/* 255 */       return false; 
/* 258 */     for (int i = 0; i < count; i++) {
/* 259 */       Comparable k1 = getKey(i);
/* 260 */       Comparable k2 = kos.getKey(i);
/* 261 */       if (!k1.equals(k2))
/* 262 */         return false; 
/* 264 */       Object o1 = getObject(i);
/* 265 */       Object o2 = kos.getObject(i);
/* 266 */       if (o1 == null) {
/* 267 */         if (o2 != null)
/* 268 */           return false; 
/* 272 */       } else if (!o1.equals(o2)) {
/* 273 */         return false;
/*     */       } 
/*     */     } 
/* 277 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\KeyedObjects.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */