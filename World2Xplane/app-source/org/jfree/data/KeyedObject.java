/*     */ package org.jfree.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ public class KeyedObject implements Cloneable, PublicCloneable, Serializable {
/*     */   private static final long serialVersionUID = 2677930479256885863L;
/*     */   
/*     */   private Comparable key;
/*     */   
/*     */   private Object object;
/*     */   
/*     */   public KeyedObject(Comparable key, Object object) {
/*  73 */     this.key = key;
/*  74 */     this.object = object;
/*     */   }
/*     */   
/*     */   public Comparable getKey() {
/*  83 */     return this.key;
/*     */   }
/*     */   
/*     */   public Object getObject() {
/*  92 */     return this.object;
/*     */   }
/*     */   
/*     */   public void setObject(Object object) {
/* 101 */     this.object = object;
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 115 */     KeyedObject clone = (KeyedObject)super.clone();
/* 116 */     if (this.object instanceof PublicCloneable) {
/* 117 */       PublicCloneable pc = (PublicCloneable)this.object;
/* 118 */       clone.object = pc.clone();
/*     */     } 
/* 120 */     return clone;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 132 */     if (obj == this)
/* 133 */       return true; 
/* 136 */     if (!(obj instanceof KeyedObject))
/* 137 */       return false; 
/* 139 */     KeyedObject that = (KeyedObject)obj;
/* 140 */     if (!ObjectUtilities.equal(this.key, that.key))
/* 141 */       return false; 
/* 144 */     if (!ObjectUtilities.equal(this.object, that.object))
/* 145 */       return false; 
/* 148 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\data\KeyedObject.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */