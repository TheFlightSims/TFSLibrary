/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ public final class BufferRecycler {
/*  21 */   private char[] mSmallCBuffer = null;
/*     */   
/*  22 */   private char[] mMediumCBuffer = null;
/*     */   
/*  23 */   private char[] mFullCBuffer = null;
/*     */   
/*  25 */   private byte[] mFullBBuffer = null;
/*     */   
/*     */   public char[] getSmallCBuffer(int minSize) {
/*  35 */     char[] result = null;
/*  36 */     if (this.mSmallCBuffer != null && this.mSmallCBuffer.length >= minSize) {
/*  37 */       result = this.mSmallCBuffer;
/*  38 */       this.mSmallCBuffer = null;
/*     */     } 
/*  41 */     return result;
/*     */   }
/*     */   
/*     */   public void returnSmallCBuffer(char[] buffer) {
/*  47 */     this.mSmallCBuffer = buffer;
/*     */   }
/*     */   
/*     */   public char[] getMediumCBuffer(int minSize) {
/*  54 */     char[] result = null;
/*  55 */     if (this.mMediumCBuffer != null && this.mMediumCBuffer.length >= minSize) {
/*  56 */       result = this.mMediumCBuffer;
/*  57 */       this.mMediumCBuffer = null;
/*     */     } 
/*  60 */     return result;
/*     */   }
/*     */   
/*     */   public void returnMediumCBuffer(char[] buffer) {
/*  65 */     this.mMediumCBuffer = buffer;
/*     */   }
/*     */   
/*     */   public char[] getFullCBuffer(int minSize) {
/*  73 */     char[] result = null;
/*  74 */     if (this.mFullCBuffer != null && this.mFullCBuffer.length >= minSize) {
/*  75 */       result = this.mFullCBuffer;
/*  76 */       this.mFullCBuffer = null;
/*     */     } 
/*  79 */     return result;
/*     */   }
/*     */   
/*     */   public void returnFullCBuffer(char[] buffer) {
/*  84 */     this.mFullCBuffer = buffer;
/*     */   }
/*     */   
/*     */   public byte[] getFullBBuffer(int minSize) {
/*  94 */     byte[] result = null;
/*  95 */     if (this.mFullBBuffer != null && this.mFullBBuffer.length >= minSize) {
/*  96 */       result = this.mFullBBuffer;
/*  97 */       this.mFullBBuffer = null;
/*     */     } 
/* 100 */     return result;
/*     */   }
/*     */   
/*     */   public void returnFullBBuffer(byte[] buffer) {
/* 105 */     this.mFullBBuffer = buffer;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\BufferRecycler.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */