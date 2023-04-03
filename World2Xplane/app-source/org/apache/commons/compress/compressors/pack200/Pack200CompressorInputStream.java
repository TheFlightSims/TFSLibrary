/*     */ package org.apache.commons.compress.compressors.pack200;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Map;
/*     */ import java.util.jar.JarOutputStream;
/*     */ import java.util.jar.Pack200;
/*     */ import org.apache.commons.compress.compressors.CompressorInputStream;
/*     */ 
/*     */ public class Pack200CompressorInputStream extends CompressorInputStream {
/*     */   private final InputStream originalInput;
/*     */   
/*     */   private final StreamBridge streamBridge;
/*     */   
/*     */   public Pack200CompressorInputStream(InputStream in) throws IOException {
/*  56 */     this(in, Pack200Strategy.IN_MEMORY);
/*     */   }
/*     */   
/*     */   public Pack200CompressorInputStream(InputStream in, Pack200Strategy mode) throws IOException {
/*  69 */     this(in, null, mode, null);
/*     */   }
/*     */   
/*     */   public Pack200CompressorInputStream(InputStream in, Map<String, String> props) throws IOException {
/*  82 */     this(in, Pack200Strategy.IN_MEMORY, props);
/*     */   }
/*     */   
/*     */   public Pack200CompressorInputStream(InputStream in, Pack200Strategy mode, Map<String, String> props) throws IOException {
/*  96 */     this(in, null, mode, props);
/*     */   }
/*     */   
/*     */   public Pack200CompressorInputStream(File f) throws IOException {
/* 104 */     this(f, Pack200Strategy.IN_MEMORY);
/*     */   }
/*     */   
/*     */   public Pack200CompressorInputStream(File f, Pack200Strategy mode) throws IOException {
/* 113 */     this(null, f, mode, null);
/*     */   }
/*     */   
/*     */   public Pack200CompressorInputStream(File f, Map<String, String> props) throws IOException {
/* 123 */     this(f, Pack200Strategy.IN_MEMORY, props);
/*     */   }
/*     */   
/*     */   public Pack200CompressorInputStream(File f, Pack200Strategy mode, Map<String, String> props) throws IOException {
/* 133 */     this(null, f, mode, props);
/*     */   }
/*     */   
/*     */   private Pack200CompressorInputStream(InputStream in, File f, Pack200Strategy mode, Map<String, String> props) throws IOException {
/* 140 */     this.originalInput = in;
/* 141 */     this.streamBridge = mode.newStreamBridge();
/* 142 */     JarOutputStream jarOut = new JarOutputStream(this.streamBridge);
/* 143 */     Pack200.Unpacker u = Pack200.newUnpacker();
/* 144 */     if (props != null)
/* 145 */       u.properties().putAll(props); 
/* 147 */     if (f == null) {
/* 148 */       u.unpack(new FilterInputStream(in) {
/*     */             public void close() {}
/* 148 */           }jarOut);
/*     */     } else {
/* 157 */       u.unpack(f, jarOut);
/*     */     } 
/* 159 */     jarOut.close();
/*     */   }
/*     */   
/*     */   public int read() throws IOException {
/* 165 */     return this.streamBridge.getInput().read();
/*     */   }
/*     */   
/*     */   public int read(byte[] b) throws IOException {
/* 171 */     return this.streamBridge.getInput().read(b);
/*     */   }
/*     */   
/*     */   public int read(byte[] b, int off, int count) throws IOException {
/* 177 */     return this.streamBridge.getInput().read(b, off, count);
/*     */   }
/*     */   
/*     */   public int available() throws IOException {
/* 183 */     return this.streamBridge.getInput().available();
/*     */   }
/*     */   
/*     */   public boolean markSupported() {
/*     */     try {
/* 190 */       return this.streamBridge.getInput().markSupported();
/* 191 */     } catch (IOException ex) {
/* 192 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void mark(int limit) {
/*     */     try {
/* 200 */       this.streamBridge.getInput().mark(limit);
/* 201 */     } catch (IOException ex) {
/* 202 */       throw new RuntimeException(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reset() throws IOException {
/* 209 */     this.streamBridge.getInput().reset();
/*     */   }
/*     */   
/*     */   public long skip(long count) throws IOException {
/* 215 */     return this.streamBridge.getInput().skip(count);
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*     */     try {
/* 221 */       this.streamBridge.stop();
/*     */     } finally {
/* 223 */       if (this.originalInput != null)
/* 224 */         this.originalInput.close(); 
/*     */     } 
/*     */   }
/*     */   
/* 229 */   private static final byte[] CAFE_DOOD = new byte[] { -54, -2, -48, 13 };
/*     */   
/* 232 */   private static final int SIG_LENGTH = CAFE_DOOD.length;
/*     */   
/*     */   public static boolean matches(byte[] signature, int length) {
/* 246 */     if (length < SIG_LENGTH)
/* 247 */       return false; 
/* 250 */     for (int i = 0; i < SIG_LENGTH; i++) {
/* 251 */       if (signature[i] != CAFE_DOOD[i])
/* 252 */         return false; 
/*     */     } 
/* 256 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\compress\compressors\pack200\Pack200CompressorInputStream.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */