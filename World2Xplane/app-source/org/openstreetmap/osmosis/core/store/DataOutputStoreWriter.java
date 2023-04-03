/*     */ package org.openstreetmap.osmosis.core.store;
/*     */ 
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ 
/*     */ public class DataOutputStoreWriter implements StoreWriter {
/*     */   private DataOutput output;
/*     */   
/*     */   public DataOutputStoreWriter(DataOutput output) {
/*  26 */     this.output = output;
/*     */   }
/*     */   
/*     */   public void writeBoolean(boolean value) {
/*     */     try {
/*  35 */       this.output.writeBoolean(value);
/*  36 */     } catch (IOException e) {
/*  37 */       throw new OsmosisRuntimeException("Unable to write boolean " + value + " to the store.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeByte(byte value) {
/*     */     try {
/*  47 */       this.output.writeByte(value);
/*  48 */     } catch (IOException e) {
/*  49 */       throw new OsmosisRuntimeException("Unable to write byte " + value + " to the store.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeCharacter(char value) {
/*     */     try {
/*  60 */       this.output.writeChar(value);
/*  61 */     } catch (IOException e) {
/*  62 */       throw new OsmosisRuntimeException("Unable to write character " + value + " to the store.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeInteger(int value) {
/*     */     try {
/*  72 */       this.output.writeInt(value);
/*  73 */     } catch (IOException e) {
/*  74 */       throw new OsmosisRuntimeException("Unable to write integer " + value + " to the store.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeLong(long value) {
/*     */     try {
/*  84 */       this.output.writeLong(value);
/*  85 */     } catch (IOException e) {
/*  86 */       throw new OsmosisRuntimeException("Unable to write long " + value + " to the store.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeDouble(double value) {
/*     */     try {
/*  96 */       this.output.writeDouble(value);
/*  97 */     } catch (IOException e) {
/*  98 */       throw new OsmosisRuntimeException("Unable to write double " + value + " to the store.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeString(String value) {
/*     */     try {
/* 108 */       this.output.writeUTF(value);
/* 109 */     } catch (IOException e) {
/* 110 */       throw new OsmosisRuntimeException("Unable to write String (" + value + ") to the store.", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\DataOutputStoreWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */