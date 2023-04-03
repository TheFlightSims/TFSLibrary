/*     */ package ch.qos.logback.core.util;
/*     */ 
/*     */ import ch.qos.logback.core.Context;
/*     */ import ch.qos.logback.core.rolling.RolloverFailure;
/*     */ import ch.qos.logback.core.spi.ContextAwareBase;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ 
/*     */ public class FileUtil extends ContextAwareBase {
/*     */   static final int BUF_SIZE = 32768;
/*     */   
/*     */   public FileUtil(Context context) {
/*  28 */     setContext(context);
/*     */   }
/*     */   
/*     */   public static URL fileToURL(File file) {
/*     */     try {
/*  33 */       return file.toURI().toURL();
/*  34 */     } catch (MalformedURLException e) {
/*  35 */       throw new RuntimeException("Unexpected exception on file [" + file + "]", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isParentDirectoryCreationRequired(File file) {
/*  40 */     File parent = file.getParentFile();
/*  41 */     if (parent != null && !parent.exists())
/*  42 */       return true; 
/*  44 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean createMissingParentDirectories(File file) {
/*  49 */     File parent = file.getParentFile();
/*  50 */     if (parent == null)
/*  51 */       throw new IllegalStateException(file + " should not have a null parent"); 
/*  53 */     if (parent.exists())
/*  54 */       throw new IllegalStateException(file + " should not have existing parent directory"); 
/*  56 */     return parent.mkdirs();
/*     */   }
/*     */   
/*     */   public String resourceAsString(ClassLoader classLoader, String resourceName) {
/*  61 */     URL url = classLoader.getResource(resourceName);
/*  62 */     if (url == null) {
/*  63 */       addError("Failed to find resource [" + resourceName + "]");
/*  64 */       return null;
/*     */     } 
/*  67 */     InputStreamReader isr = null;
/*     */     try {
/*  69 */       URLConnection urlConnection = url.openConnection();
/*  70 */       urlConnection.setUseCaches(false);
/*  71 */       isr = new InputStreamReader(urlConnection.getInputStream());
/*  72 */       char[] buf = new char[128];
/*  73 */       StringBuilder builder = new StringBuilder();
/*  74 */       int count = -1;
/*  75 */       while ((count = isr.read(buf, 0, buf.length)) != -1)
/*  76 */         builder.append(buf, 0, count); 
/*  78 */       return builder.toString();
/*  79 */     } catch (IOException e) {
/*  80 */       addError("Failed to open " + resourceName, e);
/*     */     } finally {
/*  82 */       if (isr != null)
/*     */         try {
/*  84 */           isr.close();
/*  85 */         } catch (IOException e) {} 
/*     */     } 
/*  90 */     return null;
/*     */   }
/*     */   
/*     */   public void copy(String src, String destination) throws RolloverFailure {
/*  96 */     BufferedInputStream bis = null;
/*  97 */     BufferedOutputStream bos = null;
/*     */     try {
/*  99 */       bis = new BufferedInputStream(new FileInputStream(src));
/* 100 */       bos = new BufferedOutputStream(new FileOutputStream(destination));
/* 101 */       byte[] inbuf = new byte[32768];
/*     */       int n;
/* 104 */       while ((n = bis.read(inbuf)) != -1)
/* 105 */         bos.write(inbuf, 0, n); 
/* 108 */       bis.close();
/* 109 */       bis = null;
/* 110 */       bos.close();
/* 111 */       bos = null;
/* 112 */     } catch (IOException ioe) {
/* 113 */       String msg = "Failed to copy [" + src + "] to [" + destination + "]";
/* 114 */       addError(msg, ioe);
/* 115 */       throw new RolloverFailure(msg);
/*     */     } finally {
/* 117 */       if (bis != null)
/*     */         try {
/* 119 */           bis.close();
/* 120 */         } catch (IOException e) {} 
/* 124 */       if (bos != null)
/*     */         try {
/* 126 */           bos.close();
/* 127 */         } catch (IOException e) {} 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\cor\\util\FileUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */