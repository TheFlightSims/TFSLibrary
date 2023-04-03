/*     */ package com.ctc.wstx.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.net.URLDecoder;
/*     */ 
/*     */ public final class URLUtil {
/*     */   public static URL urlFromSystemId(String sysId) throws IOException {
/*     */     try {
/*  30 */       int ix = sysId.indexOf(':', 0);
/*  34 */       if (ix >= 3 && ix <= 8)
/*  35 */         return new URL(sysId); 
/*  46 */       String absPath = (new File(sysId)).getAbsolutePath();
/*  49 */       char sep = File.separatorChar;
/*  50 */       if (sep != '/')
/*  51 */         absPath = absPath.replace(sep, '/'); 
/*  54 */       if (absPath.length() > 0 && absPath.charAt(0) != '/')
/*  55 */         absPath = "/" + absPath; 
/*  57 */       return new URL("file", "", absPath);
/*  58 */     } catch (MalformedURLException e) {
/*  59 */       throwIOException(e, sysId);
/*  60 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static URI uriFromSystemId(String sysId) throws IOException {
/*     */     try {
/*  71 */       int ix = sysId.indexOf(':', 0);
/*  72 */       if (ix >= 3 && ix <= 8)
/*  73 */         return new URI(sysId); 
/*  75 */       String absPath = (new File(sysId)).getAbsolutePath();
/*  76 */       char sep = File.separatorChar;
/*  77 */       if (sep != '/')
/*  78 */         absPath = absPath.replace(sep, '/'); 
/*  80 */       if (absPath.length() > 0 && absPath.charAt(0) != '/')
/*  81 */         absPath = "/" + absPath; 
/*  83 */       return new URI("file", absPath, null);
/*  84 */     } catch (URISyntaxException e) {
/*  85 */       throwIOException(e, sysId);
/*  86 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static URL urlFromSystemId(String sysId, URL ctxt) throws IOException {
/*  92 */     if (ctxt == null)
/*  93 */       return urlFromSystemId(sysId); 
/*     */     try {
/*  96 */       return new URL(ctxt, sysId);
/*  97 */     } catch (MalformedURLException e) {
/*  98 */       throwIOException(e, sysId);
/*  99 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static URL urlFromCurrentDir() throws MalformedURLException {
/* 114 */     return (new File("a")).getAbsoluteFile().getParentFile().toURL();
/*     */   }
/*     */   
/*     */   public static InputStream inputStreamFromURL(URL url) throws IOException {
/* 127 */     if ("file".equals(url.getProtocol())) {
/* 134 */       String host = url.getHost();
/* 135 */       if (host == null || host.length() == 0) {
/* 139 */         String path = url.getPath();
/* 140 */         if (path.indexOf('%') >= 0)
/* 141 */           path = URLDecoder.decode(path, "UTF-8"); 
/* 143 */         return new FileInputStream(path);
/*     */       } 
/*     */     } 
/* 146 */     return url.openStream();
/*     */   }
/*     */   
/*     */   public static OutputStream outputStreamFromURL(URL url) throws IOException {
/* 159 */     if ("file".equals(url.getProtocol())) {
/* 163 */       String host = url.getHost();
/* 164 */       if (host == null || host.length() == 0)
/* 165 */         return new FileOutputStream(url.getPath()); 
/*     */     } 
/* 168 */     return url.openConnection().getOutputStream();
/*     */   }
/*     */   
/*     */   private static void throwIOException(Exception mex, String sysId) throws IOException {
/* 186 */     IOException ie = new IOException("[resolving systemId '" + sysId + "']: " + mex.toString());
/* 187 */     ExceptionUtil.setInitCause(ie, mex);
/* 188 */     throw ie;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wst\\util\URLUtil.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */