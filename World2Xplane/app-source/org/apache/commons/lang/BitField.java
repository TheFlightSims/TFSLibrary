/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ public class BitField {
/*     */   private final int _mask;
/*     */   
/*     */   private final int _shift_count;
/*     */   
/*     */   public BitField(int mask) {
/*  45 */     this._mask = mask;
/*  46 */     int count = 0;
/*  47 */     int bit_pattern = mask;
/*  49 */     if (bit_pattern != 0)
/*  50 */       while ((bit_pattern & 0x1) == 0) {
/*  51 */         count++;
/*  52 */         bit_pattern >>= 1;
/*     */       }  
/*  55 */     this._shift_count = count;
/*     */   }
/*     */   
/*     */   public int getValue(int holder) {
/*  73 */     return getRawValue(holder) >> this._shift_count;
/*     */   }
/*     */   
/*     */   public short getShortValue(short holder) {
/*  91 */     return (short)getValue(holder);
/*     */   }
/*     */   
/*     */   public int getRawValue(int holder) {
/* 102 */     return holder & this._mask;
/*     */   }
/*     */   
/*     */   public short getShortRawValue(short holder) {
/* 113 */     return (short)getRawValue(holder);
/*     */   }
/*     */   
/*     */   public boolean isSet(int holder) {
/* 130 */     return ((holder & this._mask) != 0);
/*     */   }
/*     */   
/*     */   public boolean isAllSet(int holder) {
/* 146 */     return ((holder & this._mask) == this._mask);
/*     */   }
/*     */   
/*     */   public int setValue(int holder, int value) {
/* 160 */     return holder & (this._mask ^ 0xFFFFFFFF) | value << this._shift_count & this._mask;
/*     */   }
/*     */   
/*     */   public short setShortValue(short holder, short value) {
/* 174 */     return (short)setValue(holder, value);
/*     */   }
/*     */   
/*     */   public int clear(int holder) {
/* 186 */     return holder & (this._mask ^ 0xFFFFFFFF);
/*     */   }
/*     */   
/*     */   public short clearShort(short holder) {
/* 198 */     return (short)clear(holder);
/*     */   }
/*     */   
/*     */   public byte clearByte(byte holder) {
/* 211 */     return (byte)clear(holder);
/*     */   }
/*     */   
/*     */   public int set(int holder) {
/* 223 */     return holder | this._mask;
/*     */   }
/*     */   
/*     */   public short setShort(short holder) {
/* 235 */     return (short)set(holder);
/*     */   }
/*     */   
/*     */   public byte setByte(byte holder) {
/* 248 */     return (byte)set(holder);
/*     */   }
/*     */   
/*     */   public int setBoolean(int holder, boolean flag) {
/* 261 */     return flag ? set(holder) : clear(holder);
/*     */   }
/*     */   
/*     */   public short setShortBoolean(short holder, boolean flag) {
/* 274 */     return flag ? setShort(holder) : clearShort(holder);
/*     */   }
/*     */   
/*     */   public byte setByteBoolean(byte holder, boolean flag) {
/* 287 */     return flag ? setByte(holder) : clearByte(holder);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\BitField.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */