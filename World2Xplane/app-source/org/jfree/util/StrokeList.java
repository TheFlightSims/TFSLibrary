/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.awt.Stroke;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ 
/*     */ public class StrokeList extends AbstractObjectList {
/*     */   public Stroke getStroke(int index) {
/*  74 */     return (Stroke)get(index);
/*     */   }
/*     */   
/*     */   public void setStroke(int index, Stroke stroke) {
/*  84 */     set(index, stroke);
/*     */   }
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/*  95 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 107 */     if (o == null)
/* 108 */       return false; 
/* 111 */     if (o == this)
/* 112 */       return true; 
/* 115 */     if (o instanceof StrokeList)
/* 116 */       return super.equals(o); 
/* 119 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 129 */     return super.hashCode();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 141 */     stream.defaultWriteObject();
/* 142 */     int count = size();
/* 143 */     stream.writeInt(count);
/* 144 */     for (int i = 0; i < count; i++) {
/* 145 */       Stroke stroke = getStroke(i);
/* 146 */       if (stroke != null) {
/* 147 */         stream.writeInt(i);
/* 148 */         SerialUtilities.writeStroke(stroke, stream);
/*     */       } else {
/* 151 */         stream.writeInt(-1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 167 */     stream.defaultReadObject();
/* 168 */     int count = stream.readInt();
/* 169 */     for (int i = 0; i < count; i++) {
/* 170 */       int index = stream.readInt();
/* 171 */       if (index != -1)
/* 172 */         setStroke(index, SerialUtilities.readStroke(stream)); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\StrokeList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */