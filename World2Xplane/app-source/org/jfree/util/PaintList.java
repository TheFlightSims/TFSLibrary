/*     */ package org.jfree.util;
/*     */ 
/*     */ import java.awt.Paint;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ 
/*     */ public class PaintList extends AbstractObjectList {
/*     */   public Paint getPaint(int index) {
/*  75 */     return (Paint)get(index);
/*     */   }
/*     */   
/*     */   public void setPaint(int index, Paint paint) {
/*  85 */     set(index, paint);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/*  96 */     if (obj == null)
/*  97 */       return false; 
/*  99 */     if (obj == this)
/* 100 */       return true; 
/* 102 */     if (obj instanceof PaintList) {
/* 103 */       PaintList that = (PaintList)obj;
/* 104 */       int listSize = size();
/* 105 */       for (int i = 0; i < listSize; i++) {
/* 106 */         if (!PaintUtilities.equal(getPaint(i), that.getPaint(i)))
/* 107 */           return false; 
/*     */       } 
/*     */     } 
/* 111 */     return true;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 120 */     return super.hashCode();
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream stream) throws IOException {
/* 132 */     stream.defaultWriteObject();
/* 133 */     int count = size();
/* 134 */     stream.writeInt(count);
/* 135 */     for (int i = 0; i < count; i++) {
/* 136 */       Paint paint = getPaint(i);
/* 137 */       if (paint != null) {
/* 138 */         stream.writeInt(i);
/* 139 */         SerialUtilities.writePaint(paint, stream);
/*     */       } else {
/* 142 */         stream.writeInt(-1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
/* 158 */     stream.defaultReadObject();
/* 159 */     int count = stream.readInt();
/* 160 */     for (int i = 0; i < count; i++) {
/* 161 */       int index = stream.readInt();
/* 162 */       if (index != -1)
/* 163 */         setPaint(index, SerialUtilities.readPaint(stream)); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfre\\util\PaintList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */