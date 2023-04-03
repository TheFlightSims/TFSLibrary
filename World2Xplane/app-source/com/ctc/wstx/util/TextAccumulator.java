/*    */ package com.ctc.wstx.util;
/*    */ 
/*    */ public final class TextAccumulator {
/*  9 */   private String mText = null;
/*    */   
/* 14 */   private StringBuffer mBuilder = null;
/*    */   
/*    */   public boolean hasText() {
/* 19 */     return (this.mBuilder != null || this.mText != null);
/*    */   }
/*    */   
/*    */   public void addText(String text) {
/* 24 */     int len = text.length();
/* 25 */     if (len > 0) {
/* 27 */       if (this.mText != null) {
/* 28 */         this.mBuilder = new StringBuffer(this.mText.length() + len);
/* 29 */         this.mBuilder.append(this.mText);
/* 30 */         this.mText = null;
/*    */       } 
/* 32 */       if (this.mBuilder != null) {
/* 33 */         this.mBuilder.append(text);
/*    */       } else {
/* 35 */         this.mText = text;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public void addText(char[] buf, int start, int end) {
/* 42 */     int len = end - start;
/* 43 */     if (len > 0) {
/* 45 */       if (this.mText != null) {
/* 46 */         this.mBuilder = new StringBuffer(this.mText.length() + len);
/* 47 */         this.mBuilder.append(this.mText);
/* 48 */         this.mText = null;
/* 49 */       } else if (this.mBuilder == null) {
/* 54 */         this.mBuilder = new StringBuffer(len);
/*    */       } 
/* 56 */       this.mBuilder.append(buf, start, end - start);
/*    */     } 
/*    */   }
/*    */   
/*    */   public String getAndClear() {
/*    */     String result;
/* 64 */     if (this.mText != null) {
/* 65 */       result = this.mText;
/* 66 */       this.mText = null;
/* 67 */     } else if (this.mBuilder != null) {
/* 68 */       result = this.mBuilder.toString();
/* 69 */       this.mBuilder = null;
/*    */     } else {
/* 71 */       result = "";
/*    */     } 
/* 73 */     return result;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\TextAccumulator.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */