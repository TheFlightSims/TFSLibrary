/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import com.ctc.wstx.api.ReaderConfig;
/*     */ import com.ctc.wstx.util.TextBuffer;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.net.URL;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public final class BranchingReaderSource extends ReaderSource {
/*  25 */   TextBuffer mBranchBuffer = null;
/*     */   
/*  27 */   int mBranchStartOffset = 0;
/*     */   
/*     */   boolean mConvertLFs = false;
/*     */   
/*     */   boolean mGotCR = false;
/*     */   
/*     */   public BranchingReaderSource(ReaderConfig cfg, String pubId, String sysId, URL src, Reader r, boolean realClose) {
/*  44 */     super(cfg, (WstxInputSource)null, (String)null, pubId, sysId, src, r, realClose);
/*     */   }
/*     */   
/*     */   public int readInto(WstxInputData reader) throws IOException, XMLStreamException {
/*  51 */     if (this.mBranchBuffer != null) {
/*  52 */       if (this.mInputLast > this.mBranchStartOffset)
/*  53 */         appendBranched(this.mBranchStartOffset, this.mInputLast); 
/*  55 */       this.mBranchStartOffset = 0;
/*     */     } 
/*  57 */     return super.readInto(reader);
/*     */   }
/*     */   
/*     */   public boolean readMore(WstxInputData reader, int minAmount) throws IOException, XMLStreamException {
/*  64 */     if (this.mBranchBuffer != null) {
/*  65 */       int ptr = reader.mInputPtr;
/*  66 */       int currAmount = this.mInputLast - ptr;
/*  67 */       if (currAmount > 0) {
/*  68 */         if (ptr > this.mBranchStartOffset)
/*  69 */           appendBranched(this.mBranchStartOffset, ptr); 
/*  71 */         this.mBranchStartOffset = 0;
/*     */       } 
/*     */     } 
/*  74 */     return super.readMore(reader, minAmount);
/*     */   }
/*     */   
/*     */   public void startBranch(TextBuffer tb, int startOffset, boolean convertLFs) {
/*  87 */     this.mBranchBuffer = tb;
/*  88 */     this.mBranchStartOffset = startOffset;
/*  89 */     this.mConvertLFs = convertLFs;
/*  90 */     this.mGotCR = false;
/*     */   }
/*     */   
/*     */   public void endBranch(int endOffset) {
/*  98 */     if (this.mBranchBuffer != null) {
/*  99 */       if (endOffset > this.mBranchStartOffset)
/* 100 */         appendBranched(this.mBranchStartOffset, endOffset); 
/* 103 */       this.mBranchBuffer = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void appendBranched(int startOffset, int pastEnd) {
/* 115 */     if (this.mConvertLFs) {
/* 116 */       char[] inBuf = this.mBuffer;
/* 120 */       char[] outBuf = this.mBranchBuffer.getCurrentSegment();
/* 121 */       int outPtr = this.mBranchBuffer.getCurrentSegmentSize();
/* 124 */       if (this.mGotCR && 
/* 125 */         inBuf[startOffset] == '\n')
/* 126 */         startOffset++; 
/* 130 */       while (startOffset < pastEnd) {
/* 131 */         char c = inBuf[startOffset++];
/* 132 */         if (c == '\r') {
/* 133 */           if (startOffset < pastEnd) {
/* 134 */             if (inBuf[startOffset] == '\n')
/* 135 */               startOffset++; 
/*     */           } else {
/* 138 */             this.mGotCR = true;
/*     */           } 
/* 140 */           c = '\n';
/*     */         } 
/* 144 */         outBuf[outPtr++] = c;
/* 147 */         if (outPtr >= outBuf.length) {
/* 148 */           outBuf = this.mBranchBuffer.finishCurrentSegment();
/* 149 */           outPtr = 0;
/*     */         } 
/*     */       } 
/* 153 */       this.mBranchBuffer.setCurrentLength(outPtr);
/*     */     } else {
/* 155 */       this.mBranchBuffer.append(this.mBuffer, startOffset, pastEnd - startOffset);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\BranchingReaderSource.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */