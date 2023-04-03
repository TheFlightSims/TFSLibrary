/*     */ package javax.media.jai;
/*     */ 
/*     */ import com.sun.media.jai.util.DataBufferUtils;
/*     */ import java.awt.Point;
/*     */ import java.awt.image.ComponentSampleModel;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.DataBufferShort;
/*     */ import java.awt.image.DataBufferUShort;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.Observable;
/*     */ 
/*     */ public class RecyclingTileFactory extends Observable implements TileFactory, TileRecycler {
/*     */   private static final boolean DEBUG = false;
/*     */   
/* 132 */   private HashMap recycledArrays = new HashMap(32);
/*     */   
/* 137 */   private long memoryUsed = 0L;
/*     */   
/*     */   private static long getBufferSizeCSM(ComponentSampleModel csm) {
/* 141 */     int[] bandOffsets = csm.getBandOffsets();
/* 142 */     int maxBandOff = bandOffsets[0];
/* 143 */     for (int i = 1; i < bandOffsets.length; i++)
/* 144 */       maxBandOff = Math.max(maxBandOff, bandOffsets[i]); 
/* 146 */     long size = 0L;
/* 147 */     if (maxBandOff >= 0)
/* 148 */       size += (maxBandOff + 1); 
/* 149 */     int pixelStride = csm.getPixelStride();
/* 150 */     if (pixelStride > 0)
/* 151 */       size += (pixelStride * (csm.getWidth() - 1)); 
/* 152 */     int scanlineStride = csm.getScanlineStride();
/* 153 */     if (scanlineStride > 0)
/* 154 */       size += (scanlineStride * (csm.getHeight() - 1)); 
/* 155 */     return size;
/*     */   }
/*     */   
/*     */   private static long getNumBanksCSM(ComponentSampleModel csm) {
/* 160 */     int[] bankIndices = csm.getBankIndices();
/* 161 */     int maxIndex = bankIndices[0];
/* 162 */     for (int i = 1; i < bankIndices.length; i++) {
/* 163 */       int bankIndex = bankIndices[i];
/* 164 */       if (bankIndex > maxIndex)
/* 165 */         maxIndex = bankIndex; 
/*     */     } 
/* 168 */     return (maxIndex + 1);
/*     */   }
/*     */   
/*     */   private static SoftReference getBankReference(DataBuffer db) {
/* 176 */     Object array = null;
/* 178 */     switch (db.getDataType()) {
/*     */       case 0:
/* 180 */         array = ((DataBufferByte)db).getBankData();
/* 203 */         return new SoftReference(array);
/*     */       case 1:
/*     */         array = ((DataBufferUShort)db).getBankData();
/* 203 */         return new SoftReference(array);
/*     */       case 2:
/*     */         array = ((DataBufferShort)db).getBankData();
/* 203 */         return new SoftReference(array);
/*     */       case 3:
/*     */         array = ((DataBufferInt)db).getBankData();
/* 203 */         return new SoftReference(array);
/*     */       case 4:
/*     */         array = DataBufferUtils.getBankDataFloat(db);
/* 203 */         return new SoftReference(array);
/*     */       case 5:
/*     */         array = DataBufferUtils.getBankDataDouble(db);
/* 203 */         return new SoftReference(array);
/*     */     } 
/*     */     throw new UnsupportedOperationException(JaiI18N.getString("Generic3"));
/*     */   }
/*     */   
/*     */   private static long getDataBankSize(int dataType, int numBanks, int size) {
/* 211 */     int bytesPerElement = 0;
/* 212 */     switch (dataType) {
/*     */       case 0:
/* 214 */         bytesPerElement = 1;
/* 233 */         return (numBanks * size * bytesPerElement);
/*     */       case 1:
/*     */       case 2:
/*     */         bytesPerElement = 2;
/* 233 */         return (numBanks * size * bytesPerElement);
/*     */       case 3:
/*     */       case 4:
/*     */         bytesPerElement = 4;
/* 233 */         return (numBanks * size * bytesPerElement);
/*     */       case 5:
/*     */         bytesPerElement = 8;
/* 233 */         return (numBanks * size * bytesPerElement);
/*     */     } 
/*     */     throw new UnsupportedOperationException(JaiI18N.getString("Generic3"));
/*     */   }
/*     */   
/*     */   public boolean canReclaimMemory() {
/* 245 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isMemoryCache() {
/* 252 */     return true;
/*     */   }
/*     */   
/*     */   public long getMemoryUsed() {
/* 256 */     return this.memoryUsed;
/*     */   }
/*     */   
/*     */   public void flush() {
/* 260 */     synchronized (this.recycledArrays) {
/* 261 */       this.recycledArrays.clear();
/* 262 */       this.memoryUsed = 0L;
/*     */     } 
/*     */   }
/*     */   
/*     */   public WritableRaster createTile(SampleModel sampleModel, Point location) {
/* 269 */     if (sampleModel == null)
/* 270 */       throw new IllegalArgumentException("sampleModel == null!"); 
/* 273 */     if (location == null)
/* 274 */       location = new Point(0, 0); 
/* 277 */     DataBuffer db = null;
/* 279 */     int type = sampleModel.getTransferType();
/* 280 */     long numBanks = 0L;
/* 281 */     long size = 0L;
/* 283 */     if (sampleModel instanceof ComponentSampleModel) {
/* 284 */       ComponentSampleModel csm = (ComponentSampleModel)sampleModel;
/* 285 */       numBanks = getNumBanksCSM(csm);
/* 286 */       size = getBufferSizeCSM(csm);
/* 287 */     } else if (sampleModel instanceof MultiPixelPackedSampleModel) {
/* 288 */       MultiPixelPackedSampleModel mppsm = (MultiPixelPackedSampleModel)sampleModel;
/* 290 */       numBanks = 1L;
/* 291 */       int dataTypeSize = DataBuffer.getDataTypeSize(type);
/* 292 */       size = (mppsm.getScanlineStride() * mppsm.getHeight() + (mppsm.getDataBitOffset() + dataTypeSize - 1) / dataTypeSize);
/* 294 */     } else if (sampleModel instanceof SinglePixelPackedSampleModel) {
/* 295 */       SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)sampleModel;
/* 297 */       numBanks = 1L;
/* 298 */       size = (sppsm.getScanlineStride() * (sppsm.getHeight() - 1) + sppsm.getWidth());
/*     */     } 
/* 302 */     if (size != 0L) {
/* 303 */       Object array = getRecycledArray(type, numBanks, size);
/* 305 */       if (array != null) {
/*     */         byte[][] arrayOfByte;
/*     */         short[][] arrayOfShort;
/*     */         int[][] arrayOfInt;
/*     */         float[][] arrayOfFloat;
/*     */         double[][] bankData;
/*     */         int i;
/* 306 */         switch (type) {
/*     */           case 0:
/* 309 */             arrayOfByte = (byte[][])array;
/* 310 */             for (i = 0; i < numBanks; i++)
/* 311 */               Arrays.fill(arrayOfByte[i], (byte)0); 
/* 313 */             db = new DataBufferByte(arrayOfByte, (int)size);
/*     */             break;
/*     */           case 1:
/* 318 */             arrayOfShort = (short[][])array;
/* 319 */             for (i = 0; i < numBanks; i++)
/* 320 */               Arrays.fill(arrayOfShort[i], (short)0); 
/* 322 */             db = new DataBufferUShort(arrayOfShort, (int)size);
/*     */             break;
/*     */           case 2:
/* 327 */             arrayOfShort = (short[][])array;
/* 328 */             for (i = 0; i < numBanks; i++)
/* 329 */               Arrays.fill(arrayOfShort[i], (short)0); 
/* 331 */             db = new DataBufferShort(arrayOfShort, (int)size);
/*     */             break;
/*     */           case 3:
/* 336 */             arrayOfInt = (int[][])array;
/* 337 */             for (i = 0; i < numBanks; i++)
/* 338 */               Arrays.fill(arrayOfInt[i], 0); 
/* 340 */             db = new DataBufferInt(arrayOfInt, (int)size);
/*     */             break;
/*     */           case 4:
/* 345 */             arrayOfFloat = (float[][])array;
/* 346 */             for (i = 0; i < numBanks; i++)
/* 347 */               Arrays.fill(arrayOfFloat[i], 0.0F); 
/* 349 */             db = DataBufferUtils.createDataBufferFloat(arrayOfFloat, (int)size);
/*     */             break;
/*     */           case 5:
/* 355 */             bankData = (double[][])array;
/* 356 */             for (i = 0; i < numBanks; i++)
/* 357 */               Arrays.fill(bankData[i], 0.0D); 
/* 359 */             db = DataBufferUtils.createDataBufferDouble(bankData, (int)size);
/*     */             break;
/*     */           default:
/* 364 */             throw new IllegalArgumentException(JaiI18N.getString("Generic3"));
/*     */         } 
/*     */       } 
/*     */     } 
/* 383 */     if (db == null)
/* 389 */       db = sampleModel.createDataBuffer(); 
/* 392 */     return Raster.createWritableRaster(sampleModel, db, location);
/*     */   }
/*     */   
/*     */   public void recycleTile(Raster tile) {
/* 401 */     DataBuffer db = tile.getDataBuffer();
/* 403 */     Long key = new Long(db.getDataType() << 56L | db.getNumBanks() << 32L | db.getSize());
/* 415 */     synchronized (this.recycledArrays) {
/* 416 */       Object value = this.recycledArrays.get(key);
/* 417 */       ArrayList arrays = null;
/* 418 */       if (value != null) {
/* 419 */         arrays = (ArrayList)value;
/*     */       } else {
/* 421 */         arrays = new ArrayList();
/*     */       } 
/* 424 */       this.memoryUsed += getDataBankSize(db.getDataType(), db.getNumBanks(), db.getSize());
/* 428 */       arrays.add(getBankReference(db));
/* 430 */       if (value == null)
/* 431 */         this.recycledArrays.put(key, arrays); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private Object getRecycledArray(int arrayType, long numBanks, long arrayLength) {
/* 442 */     Long key = new Long(arrayType << 56L | numBanks << 32L | arrayLength);
/* 452 */     synchronized (this.recycledArrays) {
/* 453 */       Object value = this.recycledArrays.get(key);
/* 455 */       if (value != null) {
/* 456 */         ArrayList arrays = (ArrayList)value;
/* 457 */         for (int idx = arrays.size() - 1; idx >= 0; idx--) {
/* 458 */           SoftReference bankRef = arrays.remove(idx);
/* 459 */           this.memoryUsed -= getDataBankSize(arrayType, (int)numBanks, (int)arrayLength);
/* 462 */           if (idx == 0)
/* 463 */             this.recycledArrays.remove(key); 
/* 466 */           Object array = bankRef.get();
/* 467 */           if (array != null)
/* 468 */             return array; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 478 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\RecyclingTileFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */