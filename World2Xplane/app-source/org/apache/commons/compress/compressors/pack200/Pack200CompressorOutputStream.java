/*     */ package org.apache.commons.compress.compressors.pack200;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Map;
/*     */ import java.util.jar.JarInputStream;
/*     */ import java.util.jar.Pack200;
/*     */ import org.apache.commons.compress.compressors.CompressorOutputStream;
/*     */ 
/*     */ public class Pack200CompressorOutputStream extends CompressorOutputStream {
/*     */   private boolean finished = false;
/*     */   
/*     */   private final OutputStream originalOutput;
/*     */   
/*     */   private final StreamBridge streamBridge;
/*     */   
/*     */   private final Map<String, String> properties;
/*     */   
/*     */   public Pack200CompressorOutputStream(OutputStream out) throws IOException {
/*  48 */     this(out, Pack200Strategy.IN_MEMORY);
/*     */   }
/*     */   
/*     */   public Pack200CompressorOutputStream(OutputStream out, Pack200Strategy mode) throws IOException {
/*  58 */     this(out, mode, null);
/*     */   }
/*     */   
/*     */   public Pack200CompressorOutputStream(OutputStream out, Map<String, String> props) throws IOException {
/*  68 */     this(out, Pack200Strategy.IN_MEMORY, props);
/*     */   }
/*     */   
/*     */   public Pack200CompressorOutputStream(OutputStream out, Pack200Strategy mode, Map<String, String> props) throws IOException {
/*  79 */     this.originalOutput = out;
/*  80 */     this.streamBridge = mode.newStreamBridge();
/*  81 */     this.properties = props;
/*     */   }
/*     */   
/*     */   public void write(int b) throws IOException {
/*  87 */     this.streamBridge.write(b);
/*     */   }
/*     */   
/*     */   public void write(byte[] b) throws IOException {
/*  95 */     this.streamBridge.write(b);
/*     */   }
/*     */   
/*     */   public void write(byte[] b, int from, int length) throws IOException {
/* 103 */     this.streamBridge.write(b, from, length);
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 108 */     finish();
/*     */     try {
/* 110 */       this.streamBridge.stop();
/*     */     } finally {
/* 112 */       this.originalOutput.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void finish() throws IOException {
/* 117 */     if (!this.finished) {
/* 118 */       this.finished = true;
/* 119 */       Pack200.Packer p = Pack200.newPacker();
/* 120 */       if (this.properties != null)
/* 121 */         p.properties().putAll(this.properties); 
/* 123 */       JarInputStream ji = null;
/* 124 */       boolean success = false;
/*     */       try {
/* 126 */         p.pack(ji = new JarInputStream(this.streamBridge.getInput()), this.originalOutput);
/* 128 */         success = true;
/*     */       } finally {
/* 130 */         if (!success && ji != null)
/*     */           try {
/* 132 */             ji.close();
/* 133 */           } catch (IOException ex) {} 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\pack200\Pack200CompressorOutputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */