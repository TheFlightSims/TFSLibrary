/*     */ package com.ctc.wstx.sw;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ 
/*     */ public abstract class XmlWriterWrapper extends Writer {
/*     */   protected final XmlWriter mWriter;
/*     */   
/*  31 */   private char[] mBuffer = null;
/*     */   
/*     */   public static XmlWriterWrapper wrapWriteRaw(XmlWriter xw) {
/*  35 */     return new RawWrapper(xw);
/*     */   }
/*     */   
/*     */   public static XmlWriterWrapper wrapWriteCharacters(XmlWriter xw) {
/*  40 */     return new TextWrapper(xw);
/*     */   }
/*     */   
/*     */   protected XmlWriterWrapper(XmlWriter writer) {
/*  45 */     this.mWriter = writer;
/*     */   }
/*     */   
/*     */   public final void close() throws IOException {
/*  51 */     this.mWriter.close(false);
/*     */   }
/*     */   
/*     */   public final void flush() throws IOException {
/*  57 */     this.mWriter.flush();
/*     */   }
/*     */   
/*     */   public final void write(char[] cbuf) throws IOException {
/*  81 */     write(cbuf, 0, cbuf.length);
/*     */   }
/*     */   
/*     */   public abstract void write(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   public final void write(int c) throws IOException {
/*  90 */     if (this.mBuffer == null)
/*  91 */       this.mBuffer = new char[1]; 
/*  93 */     this.mBuffer[0] = (char)c;
/*  94 */     write(this.mBuffer, 0, 1);
/*     */   }
/*     */   
/*     */   public abstract void write(String paramString) throws IOException;
/*     */   
/*     */   public abstract void write(String paramString, int paramInt1, int paramInt2) throws IOException;
/*     */   
/*     */   private static final class RawWrapper extends XmlWriterWrapper {
/*     */     protected RawWrapper(XmlWriter writer) {
/* 118 */       super(writer);
/*     */     }
/*     */     
/*     */     public void write(char[] cbuf, int off, int len) throws IOException {
/* 124 */       this.mWriter.writeRaw(cbuf, off, len);
/*     */     }
/*     */     
/*     */     public void write(String str, int off, int len) throws IOException {
/* 130 */       this.mWriter.writeRaw(str, off, len);
/*     */     }
/*     */     
/*     */     public final void write(String str) throws IOException {
/* 136 */       this.mWriter.writeRaw(str, 0, str.length());
/*     */     }
/*     */   }
/*     */   
/*     */   private static class TextWrapper extends XmlWriterWrapper {
/*     */     protected TextWrapper(XmlWriter writer) {
/* 150 */       super(writer);
/*     */     }
/*     */     
/*     */     public void write(char[] cbuf, int off, int len) throws IOException {
/* 156 */       this.mWriter.writeCharacters(cbuf, off, len);
/*     */     }
/*     */     
/*     */     public void write(String str) throws IOException {
/* 162 */       this.mWriter.writeCharacters(str);
/*     */     }
/*     */     
/*     */     public void write(String str, int off, int len) throws IOException {
/* 168 */       this.mWriter.writeCharacters(str.substring(off, off + len));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\sw\XmlWriterWrapper.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */