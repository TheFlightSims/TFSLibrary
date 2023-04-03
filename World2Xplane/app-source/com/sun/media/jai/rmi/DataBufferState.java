/*     */ package com.sun.media.jai.rmi;
/*     */ 
/*     */ import com.sun.media.jai.codecimpl.util.DataBufferDouble;
/*     */ import com.sun.media.jai.codecimpl.util.DataBufferFloat;
/*     */ import com.sun.media.jai.util.DataBufferUtils;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.DataBufferShort;
/*     */ import java.awt.image.DataBufferUShort;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import javax.media.jai.DataBufferDouble;
/*     */ import javax.media.jai.DataBufferFloat;
/*     */ 
/*     */ public class DataBufferState extends SerializableStateImpl {
/*  36 */   private static Class[] J2DDataBufferClasses = null;
/*     */   
/*     */   private transient DataBuffer dataBuffer;
/*     */   
/*     */   static {
/*     */     try {
/*  44 */       Class dbfClass = Class.forName("java.awt.image.DataBufferFloat");
/*  45 */       Class dbdClass = Class.forName("java.awt.image.DataBufferDouble");
/*  46 */       J2DDataBufferClasses = new Class[] { dbfClass, dbdClass };
/*  47 */     } catch (ClassNotFoundException e) {}
/*     */   }
/*     */   
/*     */   public static Class[] getSupportedClasses() {
/*  53 */     Class[] supportedClasses = null;
/*  54 */     if (J2DDataBufferClasses != null) {
/*  56 */       supportedClasses = new Class[] { DataBufferByte.class, DataBufferShort.class, DataBufferUShort.class, DataBufferInt.class, J2DDataBufferClasses[0], J2DDataBufferClasses[1], DataBufferFloat.class, DataBufferDouble.class, DataBufferFloat.class, DataBufferDouble.class };
/*     */     } else {
/*  70 */       supportedClasses = new Class[] { DataBufferByte.class, DataBufferShort.class, DataBufferUShort.class, DataBufferInt.class, DataBufferFloat.class, DataBufferDouble.class, DataBufferFloat.class, DataBufferDouble.class };
/*     */     } 
/*  82 */     return supportedClasses;
/*     */   }
/*     */   
/*     */   public DataBufferState(Class c, Object o, RenderingHints h) {
/*  94 */     super(c, o, h);
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 106 */     DataBuffer dataBuffer = (DataBuffer)this.theObject;
/* 109 */     int dataType = dataBuffer.getDataType();
/* 110 */     out.writeInt(dataType);
/* 111 */     out.writeObject(dataBuffer.getOffsets());
/* 112 */     out.writeInt(dataBuffer.getSize());
/* 113 */     Object dataArray = null;
/* 114 */     switch (dataType) {
/*     */       case 0:
/* 116 */         dataArray = ((DataBufferByte)dataBuffer).getBankData();
/*     */         break;
/*     */       case 2:
/* 119 */         dataArray = ((DataBufferShort)dataBuffer).getBankData();
/*     */         break;
/*     */       case 1:
/* 122 */         dataArray = ((DataBufferUShort)dataBuffer).getBankData();
/*     */         break;
/*     */       case 3:
/* 125 */         dataArray = ((DataBufferInt)dataBuffer).getBankData();
/*     */         break;
/*     */       case 4:
/* 128 */         dataArray = DataBufferUtils.getBankDataFloat(dataBuffer);
/*     */         break;
/*     */       case 5:
/* 131 */         dataArray = DataBufferUtils.getBankDataDouble(dataBuffer);
/*     */         break;
/*     */       default:
/* 134 */         throw new RuntimeException(JaiI18N.getString("DataBufferState0"));
/*     */     } 
/* 136 */     out.writeObject(dataArray);
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 146 */     DataBuffer dataBuffer = null;
/* 149 */     int dataType = -1;
/* 150 */     int[] offsets = null;
/* 151 */     int size = -1;
/* 152 */     Object dataArray = null;
/* 153 */     dataType = in.readInt();
/* 154 */     offsets = (int[])in.readObject();
/* 155 */     size = in.readInt();
/* 156 */     dataArray = in.readObject();
/* 159 */     switch (dataType) {
/*     */       case 0:
/* 161 */         dataBuffer = new DataBufferByte((byte[][])dataArray, size, offsets);
/*     */         break;
/*     */       case 2:
/* 165 */         dataBuffer = new DataBufferShort((short[][])dataArray, size, offsets);
/*     */         break;
/*     */       case 1:
/* 169 */         dataBuffer = new DataBufferUShort((short[][])dataArray, size, offsets);
/*     */         break;
/*     */       case 3:
/* 173 */         dataBuffer = new DataBufferInt((int[][])dataArray, size, offsets);
/*     */         break;
/*     */       case 4:
/* 177 */         dataBuffer = DataBufferUtils.createDataBufferFloat((float[][])dataArray, size, offsets);
/*     */         break;
/*     */       case 5:
/* 181 */         dataBuffer = DataBufferUtils.createDataBufferDouble((double[][])dataArray, size, offsets);
/*     */         break;
/*     */       default:
/* 185 */         throw new RuntimeException(JaiI18N.getString("DataBufferState0"));
/*     */     } 
/* 188 */     this.theObject = dataBuffer;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\rmi\DataBufferState.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */