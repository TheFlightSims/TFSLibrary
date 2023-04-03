/*     */ package com.google.common.io;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.nio.charset.Charset;
/*     */ 
/*     */ public abstract class ByteSink implements OutputSupplier<OutputStream> {
/*     */   public CharSink asCharSink(Charset charset) {
/*  59 */     return new AsCharSink(charset);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public final OutputStream getOutput() throws IOException {
/*  84 */     return openStream();
/*     */   }
/*     */   
/*     */   public OutputStream openBufferedStream() throws IOException {
/* 100 */     OutputStream out = openStream();
/* 101 */     return (out instanceof BufferedOutputStream) ? out : new BufferedOutputStream(out);
/*     */   }
/*     */   
/*     */   public void write(byte[] bytes) throws IOException {
/* 112 */     Preconditions.checkNotNull(bytes);
/* 114 */     Closer closer = Closer.create();
/*     */     try {
/* 116 */       OutputStream out = closer.<OutputStream>register(openStream());
/* 117 */       out.write(bytes);
/* 118 */       out.flush();
/* 119 */     } catch (Throwable e) {
/* 120 */       throw closer.rethrow(e);
/*     */     } finally {
/* 122 */       closer.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public long writeFrom(InputStream input) throws IOException {
/* 134 */     Preconditions.checkNotNull(input);
/* 136 */     Closer closer = Closer.create();
/*     */     try {
/* 138 */       OutputStream out = closer.<OutputStream>register(openStream());
/* 139 */       long written = ByteStreams.copy(input, out);
/* 140 */       out.flush();
/* 141 */       return written;
/* 142 */     } catch (Throwable e) {
/* 143 */       throw closer.rethrow(e);
/*     */     } finally {
/* 145 */       closer.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract OutputStream openStream() throws IOException;
/*     */   
/*     */   private final class AsCharSink extends CharSink {
/*     */     private final Charset charset;
/*     */     
/*     */     private AsCharSink(Charset charset) {
/* 158 */       this.charset = (Charset)Preconditions.checkNotNull(charset);
/*     */     }
/*     */     
/*     */     public Writer openStream() throws IOException {
/* 163 */       return new OutputStreamWriter(ByteSink.this.openStream(), this.charset);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 168 */       return ByteSink.this.toString() + ".asCharSink(" + this.charset + ")";
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\io\ByteSink.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */