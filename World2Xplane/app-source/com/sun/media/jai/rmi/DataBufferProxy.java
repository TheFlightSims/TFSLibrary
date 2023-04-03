/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import com.sun.media.jai.util.DataBufferUtils;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.DataBufferShort;
/*     */ import java.awt.image.DataBufferUShort;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class DataBufferProxy implements Serializable {
/*     */   private transient DataBuffer dataBuffer;
/*     */   
/*     */   public DataBufferProxy(DataBuffer source) {
/*  43 */     this.dataBuffer = source;
/*     */   }
/*     */   
/*     */   public DataBuffer getDataBuffer() {
/*  51 */     return this.dataBuffer;
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  64 */     int dataType = this.dataBuffer.getDataType();
/*  65 */     out.writeInt(dataType);
/*  66 */     out.writeObject(this.dataBuffer.getOffsets());
/*  67 */     out.writeInt(this.dataBuffer.getSize());
/*  68 */     Object dataArray = null;
/*  69 */     switch (dataType) {
/*     */       case 0:
/*  71 */         dataArray = ((DataBufferByte)this.dataBuffer).getBankData();
/*     */         break;
/*     */       case 2:
/*  74 */         dataArray = ((DataBufferShort)this.dataBuffer).getBankData();
/*     */         break;
/*     */       case 1:
/*  77 */         dataArray = ((DataBufferUShort)this.dataBuffer).getBankData();
/*     */         break;
/*     */       case 3:
/*  80 */         dataArray = ((DataBufferInt)this.dataBuffer).getBankData();
/*     */         break;
/*     */       case 4:
/*  83 */         dataArray = DataBufferUtils.getBankDataFloat(this.dataBuffer);
/*     */         break;
/*     */       case 5:
/*  86 */         dataArray = DataBufferUtils.getBankDataDouble(this.dataBuffer);
/*     */         break;
/*     */       default:
/*  89 */         throw new RuntimeException(JaiI18N.getString("DataBufferProxy0"));
/*     */     } 
/*  91 */     out.writeObject(dataArray);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 102 */     int dataType = -1;
/* 103 */     int[] offsets = null;
/* 104 */     int size = -1;
/* 105 */     Object dataArray = null;
/* 106 */     dataType = in.readInt();
/* 107 */     offsets = (int[])in.readObject();
/* 108 */     size = in.readInt();
/* 109 */     dataArray = in.readObject();
/* 112 */     switch (dataType) {
/*     */       case 0:
/* 114 */         this.dataBuffer = new DataBufferByte((byte[][])dataArray, size, offsets);
/*     */         return;
/*     */       case 2:
/* 118 */         this.dataBuffer = new DataBufferShort((short[][])dataArray, size, offsets);
/*     */         return;
/*     */       case 1:
/* 122 */         this.dataBuffer = new DataBufferUShort((short[][])dataArray, size, offsets);
/*     */         return;
/*     */       case 3:
/* 126 */         this.dataBuffer = new DataBufferInt((int[][])dataArray, size, offsets);
/*     */         return;
/*     */       case 4:
/* 130 */         this.dataBuffer = DataBufferUtils.createDataBufferFloat((float[][])dataArray, size, offsets);
/*     */         return;
/*     */       case 5:
/* 134 */         this.dataBuffer = DataBufferUtils.createDataBufferDouble((double[][])dataArray, size, offsets);
/*     */         return;
/*     */     } 
/* 138 */     throw new RuntimeException(JaiI18N.getString("DataBufferProxy0"));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\DataBufferProxy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */