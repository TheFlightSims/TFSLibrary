/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ 
/*     */ public class ShapeList extends AbstractObjectList {
/*     */   public Shape getShape(int index) {
/*  75 */     return (Shape)get(index);
/*     */   }
/*     */   
/*     */   public void setShape(int index, Shape shape) {
/*  85 */     set(index, shape);
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/*  96 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 108 */     if (o == null)
/* 109 */       return false; 
/* 112 */     if (o == this)
/* 113 */       return true; 
/* 116 */     if (o instanceof ShapeList)
/* 117 */       return super.equals(o); 
/* 120 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 130 */     return super.hashCode();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 142 */     stream.defaultWriteObject();
/* 143 */     int count = size();
/* 144 */     stream.writeInt(count);
/* 145 */     for (int i = 0; i < count; i++) {
/* 146 */       Shape shape = getShape(i);
/* 147 */       if (shape != null) {
/* 148 */         stream.writeInt(i);
/* 149 */         SerialUtilities.writeShape(shape, stream);
/*     */       } else {
/* 152 */         stream.writeInt(-1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 168 */     stream.defaultReadObject();
/* 169 */     int count = stream.readInt();
/* 170 */     for (int i = 0; i < count; i++) {
/* 171 */       int index = stream.readInt();
/* 172 */       if (index != -1)
/* 173 */         setShape(index, SerialUtilities.readShape(stream)); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\ShapeList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */