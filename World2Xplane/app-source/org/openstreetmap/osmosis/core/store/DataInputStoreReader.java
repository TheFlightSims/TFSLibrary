/*     */ package org.openstreetmap.osmosis.core.store;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ 
/*     */ public class DataInputStoreReader implements StoreReader {
/*     */   private DataInput input;
/*     */   
/*     */   public DataInputStoreReader(DataInput input) {
/*  27 */     this.input = input;
/*     */   }
/*     */   
/*     */   public boolean readBoolean() {
/*     */     try {
/*  37 */       return this.input.readBoolean();
/*  38 */     } catch (EOFException e) {
/*  39 */       throw new EndOfStoreException("End of stream was reached while attempting to read a boolean from the store.", e);
/*  41 */     } catch (IOException e) {
/*  42 */       throw new OsmosisRuntimeException("Unable to read a boolean from the store.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public byte readByte() {
/*     */     try {
/*  53 */       return this.input.readByte();
/*  54 */     } catch (EOFException e) {
/*  55 */       throw new EndOfStoreException("End of stream was reached while attempting to read a byte from the store.", e);
/*  57 */     } catch (IOException e) {
/*  58 */       throw new OsmosisRuntimeException("Unable to read a byte from the store.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public char readCharacter() {
/*     */     try {
/*  70 */       return this.input.readChar();
/*  71 */     } catch (EOFException e) {
/*  72 */       throw new EndOfStoreException("End of stream was reached while attempting to read a character from the store.", e);
/*  74 */     } catch (IOException e) {
/*  75 */       throw new OsmosisRuntimeException("Unable to read a character from the store.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public int readInteger() {
/*     */     try {
/*  87 */       return this.input.readInt();
/*  88 */     } catch (EOFException e) {
/*  89 */       throw new EndOfStoreException("End of stream was reached while attempting to read an integer from the store.", e);
/*  91 */     } catch (IOException e) {
/*  92 */       throw new OsmosisRuntimeException("Unable to read an integer from the store.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public long readLong() {
/*     */     try {
/* 104 */       return this.input.readLong();
/* 105 */     } catch (EOFException e) {
/* 106 */       throw new EndOfStoreException("End of stream was reached while attempting to read a long from the store.", e);
/* 108 */     } catch (IOException e) {
/* 109 */       throw new OsmosisRuntimeException("Unable to read a long from the store.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public double readDouble() {
/*     */     try {
/* 121 */       return this.input.readDouble();
/* 122 */     } catch (EOFException e) {
/* 123 */       throw new EndOfStoreException("End of stream was reached while attempting to read a double from the store.", e);
/* 125 */     } catch (IOException e) {
/* 126 */       throw new OsmosisRuntimeException("Unable to read a double from the store.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String readString() {
/*     */     try {
/* 138 */       return this.input.readUTF();
/* 139 */     } catch (EOFException e) {
/* 140 */       throw new EndOfStoreException("End of stream was reached while attempting to read a String from the store.", e);
/* 142 */     } catch (IOException e) {
/* 143 */       throw new OsmosisRuntimeException("Unable to read a String from the store.", e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\DataInputStoreReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */