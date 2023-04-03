/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import java.net.URL;
/*     */ import javax.xml.stream.Location;
/*     */ 
/*     */ public final class CharArraySource extends BaseInputSource {
/*     */   int mOffset;
/*     */   
/*     */   final Location mContentStart;
/*     */   
/*     */   CharArraySource(WstxInputSource parent, String fromEntity, char[] chars, int offset, int len, Location loc, URL src) {
/*  25 */     super(parent, fromEntity, loc.getPublicId(), loc.getSystemId(), src);
/*  26 */     this.mBuffer = chars;
/*  27 */     this.mOffset = offset;
/*  28 */     this.mInputLast = offset + len;
/*  29 */     this.mContentStart = loc;
/*     */   }
/*     */   
/*     */   public boolean fromInternalEntity() {
/*  37 */     return true;
/*     */   }
/*     */   
/*     */   protected void doInitInputLocation(WstxInputData reader) {
/*  46 */     reader.mCurrInputProcessed = this.mContentStart.getCharacterOffset();
/*  47 */     reader.mCurrInputRow = this.mContentStart.getLineNumber();
/*  52 */     reader.mCurrInputRowStart = -this.mContentStart.getColumnNumber() + 1;
/*     */   }
/*     */   
/*     */   public int readInto(WstxInputData reader) {
/*  60 */     if (this.mBuffer == null)
/*  61 */       return -1; 
/*  69 */     int len = this.mInputLast - this.mOffset;
/*  70 */     if (len < 1)
/*  71 */       return -1; 
/*  73 */     reader.mInputBuffer = this.mBuffer;
/*  74 */     reader.mInputPtr = this.mOffset;
/*  75 */     reader.mInputEnd = this.mInputLast;
/*  77 */     this.mOffset = this.mInputLast;
/*  78 */     return len;
/*     */   }
/*     */   
/*     */   public boolean readMore(WstxInputData reader, int minAmount) {
/*  87 */     if (reader.mInputPtr >= reader.mInputEnd) {
/*  88 */       int len = this.mInputLast - this.mOffset;
/*  89 */       if (len >= minAmount)
/*  90 */         return (readInto(reader) > 0); 
/*     */     } 
/*  93 */     return false;
/*     */   }
/*     */   
/*     */   public void close() {
/* 101 */     this.mBuffer = null;
/*     */   }
/*     */   
/*     */   public void closeCompletely() {
/* 105 */     close();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\CharArraySource.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */