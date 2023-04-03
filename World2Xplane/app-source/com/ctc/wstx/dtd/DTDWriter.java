/*     */ package com.ctc.wstx.dtd;
/*     */ 
/*     */ import com.ctc.wstx.exc.WstxIOException;
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ final class DTDWriter {
/*     */   final Writer mWriter;
/*     */   
/*     */   final boolean mIncludeComments;
/*     */   
/*     */   final boolean mIncludeConditionals;
/*     */   
/*     */   final boolean mIncludePEs;
/*     */   
/*  51 */   int mIsFlattening = 0;
/*     */   
/*  57 */   int mFlattenStart = 0;
/*     */   
/*     */   public DTDWriter(Writer out, boolean inclComments, boolean inclCond, boolean inclPEs) {
/*  68 */     this.mWriter = out;
/*  69 */     this.mIncludeComments = inclComments;
/*  70 */     this.mIncludeConditionals = inclCond;
/*  71 */     this.mIncludePEs = inclPEs;
/*  73 */     this.mIsFlattening = 1;
/*     */   }
/*     */   
/*     */   public boolean includeComments() {
/*  83 */     return this.mIncludeComments;
/*     */   }
/*     */   
/*     */   public boolean includeConditionals() {
/*  87 */     return this.mIncludeConditionals;
/*     */   }
/*     */   
/*     */   public boolean includeParamEntities() {
/*  91 */     return this.mIncludePEs;
/*     */   }
/*     */   
/*     */   public void disableOutput() {
/*  96 */     this.mIsFlattening--;
/*     */   }
/*     */   
/*     */   public void enableOutput(int newStart) {
/* 101 */     this.mIsFlattening++;
/* 102 */     this.mFlattenStart = newStart;
/*     */   }
/*     */   
/*     */   public void setFlattenStart(int ptr) {
/* 106 */     this.mFlattenStart = ptr;
/*     */   }
/*     */   
/*     */   public int getFlattenStart() {
/* 110 */     return this.mFlattenStart;
/*     */   }
/*     */   
/*     */   public void flush(char[] buf, int upUntil) throws XMLStreamException {
/* 122 */     if (this.mFlattenStart < upUntil) {
/* 123 */       if (this.mIsFlattening > 0)
/*     */         try {
/* 125 */           this.mWriter.write(buf, this.mFlattenStart, upUntil - this.mFlattenStart);
/* 126 */         } catch (IOException ioe) {
/* 127 */           throw new WstxIOException(ioe);
/*     */         }  
/* 130 */       this.mFlattenStart = upUntil;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void output(String output) throws XMLStreamException {
/* 143 */     if (this.mIsFlattening > 0)
/*     */       try {
/* 145 */         this.mWriter.write(output);
/* 146 */       } catch (IOException ioe) {
/* 147 */         throw new WstxIOException(ioe);
/*     */       }  
/*     */   }
/*     */   
/*     */   public void output(char c) throws XMLStreamException {
/* 155 */     if (this.mIsFlattening > 0)
/*     */       try {
/* 157 */         this.mWriter.write(c);
/* 158 */       } catch (IOException ioe) {
/* 159 */         throw new WstxIOException(ioe);
/*     */       }  
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\dtd\DTDWriter.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */