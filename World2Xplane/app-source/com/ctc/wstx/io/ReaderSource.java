/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import com.ctc.wstx.exc.WstxException;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.net.URL;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public class ReaderSource extends BaseInputSource {
/*     */   final ReaderConfig mConfig;
/*     */   
/*     */   Reader mReader;
/*     */   
/*     */   final boolean mDoRealClose;
/*     */   
/*  31 */   int mInputProcessed = 0;
/*     */   
/*  32 */   int mInputRow = 1;
/*     */   
/*  33 */   int mInputRowStart = 0;
/*     */   
/*     */   public ReaderSource(ReaderConfig cfg, WstxInputSource parent, String fromEntity, String pubId, String sysId, URL src, Reader r, boolean realClose) {
/*  39 */     super(parent, fromEntity, pubId, sysId, src);
/*  40 */     this.mConfig = cfg;
/*  41 */     this.mReader = r;
/*  42 */     this.mDoRealClose = realClose;
/*  43 */     int bufSize = cfg.getInputBufferLength();
/*  44 */     this.mBuffer = cfg.allocFullCBuffer(bufSize);
/*     */   }
/*     */   
/*     */   public void setInputOffsets(int proc, int row, int rowStart) {
/*  54 */     this.mInputProcessed = proc;
/*  55 */     this.mInputRow = row;
/*  56 */     this.mInputRowStart = rowStart;
/*     */   }
/*     */   
/*     */   protected void doInitInputLocation(WstxInputData reader) {
/*  65 */     reader.mCurrInputProcessed = this.mInputProcessed;
/*  66 */     reader.mCurrInputRow = this.mInputRow;
/*  67 */     reader.mCurrInputRowStart = this.mInputRowStart;
/*     */   }
/*     */   
/*     */   public boolean fromInternalEntity() {
/*  75 */     return false;
/*     */   }
/*     */   
/*     */   public int readInto(WstxInputData reader) throws IOException, XMLStreamException {
/*  84 */     if (this.mBuffer == null)
/*  85 */       return -1; 
/*  87 */     int count = this.mReader.read(this.mBuffer, 0, this.mBuffer.length);
/*  88 */     if (count < 1) {
/*  92 */       this.mInputLast = 0;
/*  93 */       reader.mInputPtr = 0;
/*  94 */       reader.mInputEnd = 0;
/*  95 */       if (count == 0)
/*  99 */         throw new WstxException("Reader (of type " + this.mReader.getClass().getName() + ") returned 0 characters, even when asked to read up to " + this.mBuffer.length, getLocation()); 
/* 101 */       return -1;
/*     */     } 
/* 103 */     reader.mInputBuffer = this.mBuffer;
/* 104 */     reader.mInputPtr = 0;
/* 105 */     this.mInputLast = count;
/* 106 */     reader.mInputEnd = count;
/* 108 */     return count;
/*     */   }
/*     */   
/*     */   public boolean readMore(WstxInputData reader, int minAmount) throws IOException, XMLStreamException {
/* 117 */     if (this.mBuffer == null)
/* 118 */       return false; 
/* 121 */     int ptr = reader.mInputPtr;
/* 122 */     int currAmount = this.mInputLast - ptr;
/* 130 */     reader.mCurrInputProcessed += ptr;
/* 131 */     reader.mCurrInputRowStart -= ptr;
/* 134 */     if (currAmount > 0) {
/* 135 */       System.arraycopy(this.mBuffer, ptr, this.mBuffer, 0, currAmount);
/* 136 */       minAmount -= currAmount;
/*     */     } 
/* 138 */     reader.mInputBuffer = this.mBuffer;
/* 139 */     reader.mInputPtr = 0;
/* 140 */     this.mInputLast = currAmount;
/* 142 */     while (minAmount > 0) {
/* 143 */       int amount = this.mBuffer.length - currAmount;
/* 144 */       int actual = this.mReader.read(this.mBuffer, currAmount, amount);
/* 145 */       if (actual < 1) {
/* 146 */         if (actual == 0)
/* 147 */           throw new WstxException("Reader (of type " + this.mReader.getClass().getName() + ") returned 0 characters, even when asked to read up to " + amount, getLocation()); 
/* 149 */         reader.mInputEnd = this.mInputLast = currAmount;
/* 150 */         return false;
/*     */       } 
/* 152 */       currAmount += actual;
/* 153 */       minAmount -= actual;
/*     */     } 
/* 155 */     reader.mInputEnd = this.mInputLast = currAmount;
/* 156 */     return true;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 165 */     if (this.mBuffer != null)
/* 166 */       closeAndRecycle(this.mDoRealClose); 
/*     */   }
/*     */   
/*     */   public void closeCompletely() throws IOException {
/* 176 */     if (this.mReader != null)
/* 177 */       closeAndRecycle(true); 
/*     */   }
/*     */   
/*     */   private void closeAndRecycle(boolean fullClose) throws IOException {
/* 184 */     char[] buf = this.mBuffer;
/* 187 */     if (buf != null) {
/* 188 */       this.mBuffer = null;
/* 189 */       this.mConfig.freeFullCBuffer(buf);
/*     */     } 
/* 193 */     if (this.mReader != null) {
/* 194 */       if (this.mReader instanceof BaseReader)
/* 195 */         ((BaseReader)this.mReader).freeBuffers(); 
/* 197 */       if (fullClose) {
/* 198 */         Reader r = this.mReader;
/* 199 */         this.mReader = null;
/* 200 */         r.close();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\ReaderSource.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */