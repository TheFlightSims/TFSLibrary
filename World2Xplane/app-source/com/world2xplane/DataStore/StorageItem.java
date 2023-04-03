/*     */ package com.world2xplane.DataStore;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ 
/*     */ public abstract class StorageItem {
/*     */   public void packNumber(ObjectOutput output, Long value) throws IOException {
/*  33 */     if (value.longValue() < 32767L) {
/*  34 */       output.writeByte(0);
/*  35 */       output.writeShort(value.intValue());
/*  36 */     } else if (value.longValue() < 2147483647L) {
/*  37 */       output.writeByte(1);
/*  38 */       output.writeInt(value.intValue());
/*     */     } else {
/*  40 */       output.writeByte(2);
/*  41 */       output.writeLong(value.longValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   public Long unpackNumber(ObjectInput input) throws IOException {
/*     */     Long value;
/*  48 */     byte type = input.readByte();
/*  49 */     if (type == 0) {
/*  50 */       value = Long.valueOf(input.readShort());
/*  51 */     } else if (type == 1) {
/*  52 */       value = Long.valueOf(input.readInt());
/*     */     } else {
/*  54 */       value = Long.valueOf(input.readLong());
/*     */     } 
/*  56 */     return value;
/*     */   }
/*     */   
/*     */   public static void packNumber(DataOutput output, long value) throws IOException {
/*  62 */     if (value < 32767L) {
/*  63 */       output.writeByte(0);
/*  64 */       output.writeShort((int)value);
/*  65 */     } else if (value < 2147483647L) {
/*  66 */       output.writeByte(1);
/*  67 */       output.writeInt((int)value);
/*     */     } else {
/*  69 */       output.writeByte(2);
/*  70 */       output.writeLong(value);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static long unpackNumber(DataInput input) throws IOException {
/*     */     Long value;
/*  77 */     byte type = input.readByte();
/*  78 */     if (type == 0) {
/*  79 */       value = Long.valueOf(input.readShort());
/*  80 */     } else if (type == 1) {
/*  81 */       value = Long.valueOf(input.readInt());
/*     */     } else {
/*  83 */       value = Long.valueOf(input.readLong());
/*     */     } 
/*  85 */     return value.longValue();
/*     */   }
/*     */   
/*     */   public void packInt(ObjectOutput output, Integer value) throws IOException {
/*  91 */     if (value.intValue() < 32767) {
/*  92 */       output.writeByte(0);
/*  93 */       output.writeShort(value.intValue());
/*     */     } else {
/*  95 */       output.writeByte(1);
/*  96 */       output.writeInt(value.intValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   public Integer unpackInt(ObjectInput input) throws IOException {
/*     */     Integer value;
/* 103 */     byte type = input.readByte();
/* 104 */     if (type == 0) {
/* 105 */       value = Integer.valueOf(input.readShort());
/*     */     } else {
/* 107 */       value = Integer.valueOf(input.readInt());
/*     */     } 
/* 109 */     return value;
/*     */   }
/*     */   
/*     */   public static void packInt(DataOutput output, Integer value) throws IOException {
/* 115 */     if (value.intValue() < 32767) {
/* 116 */       output.writeByte(0);
/* 117 */       output.writeShort(value.intValue());
/*     */     } else {
/* 119 */       output.writeByte(1);
/* 120 */       output.writeInt(value.intValue());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Integer unpackInt(DataInput input) throws IOException {
/*     */     Integer value;
/* 127 */     byte type = input.readByte();
/* 128 */     if (type == 0) {
/* 129 */       value = Integer.valueOf(input.readShort());
/*     */     } else {
/* 131 */       value = Integer.valueOf(input.readInt());
/*     */     } 
/* 133 */     return value;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\DataStore\StorageItem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */