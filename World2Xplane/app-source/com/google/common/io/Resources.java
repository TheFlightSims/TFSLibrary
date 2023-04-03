/*     */ package com.google.common.io;
/*     */ 
/*     */ import com.google.common.annotations.Beta;
/*     */ import com.google.common.base.Objects;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStream;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.List;
/*     */ 
/*     */ @Beta
/*     */ public final class Resources {
/*     */   @Deprecated
/*     */   public static InputSupplier<InputStream> newInputStreamSupplier(URL url) {
/*  62 */     return ByteStreams.asInputSupplier(asByteSource(url));
/*     */   }
/*     */   
/*     */   public static ByteSource asByteSource(URL url) {
/*  71 */     return new UrlByteSource(url);
/*     */   }
/*     */   
/*     */   private static final class UrlByteSource extends ByteSource {
/*     */     private final URL url;
/*     */     
/*     */     private UrlByteSource(URL url) {
/*  82 */       this.url = (URL)Preconditions.checkNotNull(url);
/*     */     }
/*     */     
/*     */     public InputStream openStream() throws IOException {
/*  87 */       return this.url.openStream();
/*     */     }
/*     */     
/*     */     public String toString() {
/*  92 */       return "Resources.asByteSource(" + this.url + ")";
/*     */     }
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static InputSupplier<InputStreamReader> newReaderSupplier(URL url, Charset charset) {
/* 110 */     return CharStreams.asInputSupplier(asCharSource(url, charset));
/*     */   }
/*     */   
/*     */   public static CharSource asCharSource(URL url, Charset charset) {
/* 120 */     return asByteSource(url).asCharSource(charset);
/*     */   }
/*     */   
/*     */   public static byte[] toByteArray(URL url) throws IOException {
/* 131 */     return asByteSource(url).read();
/*     */   }
/*     */   
/*     */   public static String toString(URL url, Charset charset) throws IOException {
/* 145 */     return asCharSource(url, charset).read();
/*     */   }
/*     */   
/*     */   public static <T> T readLines(URL url, Charset charset, LineProcessor<T> callback) throws IOException {
/* 161 */     return CharStreams.readLines(newReaderSupplier(url, charset), callback);
/*     */   }
/*     */   
/*     */   public static List<String> readLines(URL url, Charset charset) throws IOException {
/* 183 */     return readLines(url, charset, new LineProcessor<List<String>>() {
/* 184 */           final List<String> result = Lists.newArrayList();
/*     */           
/*     */           public boolean processLine(String line) {
/* 188 */             this.result.add(line);
/* 189 */             return true;
/*     */           }
/*     */           
/*     */           public List<String> getResult() {
/* 194 */             return this.result;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static void copy(URL from, OutputStream to) throws IOException {
/* 207 */     asByteSource(from).copyTo(to);
/*     */   }
/*     */   
/*     */   public static URL getResource(String resourceName) {
/* 225 */     ClassLoader loader = (ClassLoader)Objects.firstNonNull(Thread.currentThread().getContextClassLoader(), Resources.class.getClassLoader());
/* 228 */     URL url = loader.getResource(resourceName);
/* 229 */     Preconditions.checkArgument((url != null), "resource %s not found.", new Object[] { resourceName });
/* 230 */     return url;
/*     */   }
/*     */   
/*     */   public static URL getResource(Class<?> contextClass, String resourceName) {
/* 240 */     URL url = contextClass.getResource(resourceName);
/* 241 */     Preconditions.checkArgument((url != null), "resource %s relative to %s not found.", new Object[] { resourceName, contextClass.getName() });
/* 243 */     return url;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\google\common\io\Resources.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */