/*     */ package org.java.plugin.util;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileFilter;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URISyntaxException;
/*     */ import java.net.URL;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import java.util.jar.JarFile;
/*     */ import java.util.zip.ZipEntry;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public final class IoUtil {
/*     */   private static final String PACKAGE_NAME = "org.java.plugin.util";
/*     */   
/*     */   public static void copyFile(File src, File dest) throws IOException {
/*  60 */     if (!src.isFile())
/*  61 */       throw new IOException(ResourceManager.getMessage("org.java.plugin.util", "notAFile", src)); 
/*  64 */     if (dest.isDirectory())
/*  65 */       throw new IOException(ResourceManager.getMessage("org.java.plugin.util", "isFolder", dest)); 
/*  68 */     BufferedInputStream in = new BufferedInputStream(new FileInputStream(src));
/*     */     try {
/*  71 */       BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest, false));
/*     */       try {
/*  74 */         copyStream(in, out, 1024);
/*     */       } finally {
/*  76 */         out.close();
/*     */       } 
/*     */     } finally {
/*  79 */       in.close();
/*     */     } 
/*  81 */     dest.setLastModified(src.lastModified());
/*     */   }
/*     */   
/*     */   public static void copyFolder(File src, File dest) throws IOException {
/*  92 */     copyFolder(src, dest, true, false, null);
/*     */   }
/*     */   
/*     */   public static void copyFolder(File src, File dest, boolean recursive) throws IOException {
/* 104 */     copyFolder(src, dest, recursive, false, null);
/*     */   }
/*     */   
/*     */   public static void copyFolder(File src, File dest, boolean recursive, boolean onlyNew) throws IOException {
/* 118 */     copyFolder(src, dest, recursive, onlyNew, null);
/*     */   }
/*     */   
/*     */   public static void copyFolder(File src, File dest, boolean recursive, boolean onlyNew, FileFilter filter) throws IOException {
/* 135 */     if (!src.isDirectory())
/* 136 */       throw new IOException(ResourceManager.getMessage("org.java.plugin.util", "notAFolder", src)); 
/* 140 */     if (dest.isFile())
/* 141 */       throw new IOException(ResourceManager.getMessage("org.java.plugin.util", "isFile", dest)); 
/* 144 */     if (!dest.exists() && !dest.mkdirs())
/* 145 */       throw new IOException(ResourceManager.getMessage("org.java.plugin.util", "cantMakeFolder", dest)); 
/* 149 */     File[] srcFiles = (filter != null) ? src.listFiles(filter) : src.listFiles();
/* 151 */     for (int i = 0; i < srcFiles.length; i++) {
/* 152 */       File file = srcFiles[i];
/* 153 */       if (file.isDirectory()) {
/* 154 */         if (recursive)
/* 155 */           copyFolder(file, new File(dest, file.getName()), recursive, onlyNew, filter); 
/*     */       } else {
/* 160 */         File destFile = new File(dest, file.getName());
/* 161 */         if (!onlyNew || !destFile.isFile() || destFile.lastModified() <= file.lastModified())
/* 165 */           copyFile(file, destFile); 
/*     */       } 
/*     */     } 
/* 167 */     dest.setLastModified(src.lastModified());
/*     */   }
/*     */   
/*     */   public static void copyStream(InputStream in, OutputStream out, int bufferSize) throws IOException {
/* 179 */     byte[] buf = new byte[bufferSize];
/*     */     int len;
/* 181 */     while ((len = in.read(buf)) != -1)
/* 182 */       out.write(buf, 0, len); 
/*     */   }
/*     */   
/*     */   public static boolean emptyFolder(File folder) {
/* 192 */     if (!folder.isDirectory())
/* 193 */       return true; 
/* 195 */     File[] files = folder.listFiles();
/* 196 */     boolean result = true;
/* 197 */     for (File file : files) {
/* 199 */       if (file.isDirectory()) {
/* 200 */         if (emptyFolder(file)) {
/* 201 */           result &= file.delete();
/*     */         } else {
/* 203 */           result = false;
/*     */         } 
/*     */       } else {
/* 206 */         result &= file.delete();
/*     */       } 
/*     */     } 
/* 209 */     return result;
/*     */   }
/*     */   
/*     */   public static boolean compareFiles(File file1, File file2) {
/* 223 */     if (!file1.isFile() || !file2.isFile())
/* 224 */       return false; 
/* 226 */     if (!file1.getName().equals(file2.getName()))
/* 227 */       return false; 
/* 229 */     if (file1.length() != file2.length())
/* 230 */       return false; 
/* 232 */     return compareFileDates(new Date(file1.lastModified()), new Date(file2.lastModified()));
/*     */   }
/*     */   
/*     */   public static boolean compareFileDates(Date date1, Date date2) {
/* 245 */     if (date1 == null || date2 == null)
/* 246 */       return false; 
/* 248 */     Calendar cldr = Calendar.getInstance(Locale.ENGLISH);
/* 249 */     cldr.setTime(date1);
/* 250 */     cldr.set(14, 0);
/* 251 */     long dt1 = cldr.getTimeInMillis();
/* 252 */     cldr.setTime(date2);
/* 253 */     cldr.set(14, 0);
/* 254 */     long dt2 = cldr.getTimeInMillis();
/* 255 */     return (dt1 == dt2);
/*     */   }
/*     */   
/*     */   public static void synchronizeFolders(File src, File dest) throws IOException {
/* 270 */     synchronizeFolders(src, dest, null);
/*     */   }
/*     */   
/*     */   public static void synchronizeFolders(File src, File dest, FileFilter filter) throws IOException {
/* 286 */     if (!src.isDirectory())
/* 287 */       throw new IOException(ResourceManager.getMessage("org.java.plugin.util", "notAFolder", src)); 
/* 291 */     if (dest.isFile())
/* 292 */       throw new IOException(ResourceManager.getMessage("org.java.plugin.util", "isFile", dest)); 
/* 295 */     if (!dest.exists() && !dest.mkdirs())
/* 296 */       throw new IOException(ResourceManager.getMessage("org.java.plugin.util", "cantMakeFolder", dest)); 
/* 300 */     File[] srcFiles = (filter != null) ? src.listFiles(filter) : src.listFiles();
/* 302 */     for (File srcFile : srcFiles) {
/* 303 */       File destFile = new File(dest, srcFile.getName());
/* 304 */       if (srcFile.isDirectory()) {
/* 305 */         if (destFile.isFile() && !destFile.delete())
/* 306 */           throw new IOException(ResourceManager.getMessage("org.java.plugin.util", "cantDeleteFile", destFile)); 
/* 310 */         synchronizeFolders(srcFile, destFile, filter);
/* 313 */       } else if (!compareFiles(srcFile, destFile)) {
/* 316 */         copyFile(srcFile, destFile);
/*     */       } 
/*     */     } 
/* 318 */     File[] destFiles = dest.listFiles();
/* 319 */     for (int i = 0; i < destFiles.length; i++) {
/* 320 */       File destFile = destFiles[i];
/* 321 */       File srcFile = new File(src, destFile.getName());
/* 322 */       if ((filter == null || !filter.accept(destFile) || !srcFile.exists()) && (filter != null || !srcFile.exists())) {
/* 327 */         if (destFile.isDirectory() && !emptyFolder(destFile))
/* 328 */           throw new IOException(ResourceManager.getMessage("org.java.plugin.util", "cantEmptyFolder", destFile)); 
/* 332 */         if (!destFile.delete())
/* 333 */           throw new IOException(ResourceManager.getMessage("org.java.plugin.util", "cantDeleteFile", destFile)); 
/*     */       } 
/*     */     } 
/* 338 */     dest.setLastModified(src.lastModified());
/*     */   }
/*     */   
/*     */   public static boolean isResourceExists(URL url) {
/* 347 */     File file = url2file(url);
/* 348 */     if (file != null)
/* 349 */       return file.canRead(); 
/* 351 */     if ("jar".equalsIgnoreCase(url.getProtocol()))
/* 352 */       return isJarResourceExists(url); 
/* 354 */     return isUrlResourceExists(url);
/*     */   }
/*     */   
/*     */   private static boolean isUrlResourceExists(URL url) {
/*     */     try {
/* 361 */       InputStream is = url.openStream();
/*     */       try {
/* 363 */         is.close();
/* 364 */       } catch (IOException ioe) {}
/* 367 */       return true;
/* 368 */     } catch (IOException ioe) {
/* 369 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean isJarResourceExists(URL url) {
/*     */     try {
/* 375 */       String urlStr = url.toExternalForm();
/* 376 */       int p = urlStr.indexOf("!/");
/* 377 */       if (p == -1)
/* 378 */         return false; 
/* 380 */       URL fileUrl = new URL(urlStr.substring(4, p));
/* 381 */       File file = url2file(fileUrl);
/* 382 */       if (file == null)
/* 383 */         return isUrlResourceExists(url); 
/* 385 */       if (!file.canRead())
/* 386 */         return false; 
/* 388 */       if (p == urlStr.length() - 2)
/* 389 */         return true; 
/* 391 */       JarFile jarFile = new JarFile(file);
/*     */       try {
/* 393 */         return (jarFile.getEntry(urlStr.substring(p + 2)) != null);
/*     */       } finally {
/* 395 */         jarFile.close();
/*     */       } 
/* 397 */     } catch (IOException ioe) {
/* 398 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static InputStream getResourceInputStream(URL url) throws IOException {
/* 421 */     File file = url2file(url);
/* 422 */     if (file != null)
/* 423 */       return new BufferedInputStream(new FileInputStream(file)); 
/* 425 */     if (!"jar".equalsIgnoreCase(url.getProtocol()))
/* 426 */       return url.openStream(); 
/* 428 */     String urlStr = url.toExternalForm();
/* 429 */     if (urlStr.endsWith("!/"))
/* 431 */       throw new FileNotFoundException(url.toExternalForm()); 
/* 433 */     int p = urlStr.indexOf("!/");
/* 434 */     if (p == -1)
/* 435 */       throw new MalformedURLException(url.toExternalForm()); 
/* 437 */     String path = urlStr.substring(p + 2);
/* 438 */     file = url2file(new URL(urlStr.substring(4, p)));
/* 439 */     if (file == null)
/* 440 */       return url.openStream(); 
/* 442 */     JarFile jarFile = new JarFile(file);
/*     */     try {
/* 444 */       ZipEntry entry = jarFile.getEntry(path);
/* 445 */       if (entry == null)
/* 446 */         throw new FileNotFoundException(url.toExternalForm()); 
/* 448 */       InputStream in = jarFile.getInputStream(entry);
/*     */     } finally {
/* 457 */       jarFile.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static File url2file(URL url) {
/* 468 */     String prot = url.getProtocol();
/* 469 */     if ("jar".equalsIgnoreCase(prot)) {
/* 470 */       if (url.getFile().endsWith("!/")) {
/* 471 */         String urlStr = url.toExternalForm();
/*     */         try {
/* 473 */           return url2file(new URL(urlStr.substring(4, urlStr.length() - 2)));
/* 475 */         } catch (MalformedURLException mue) {}
/*     */       } 
/* 479 */       return null;
/*     */     } 
/* 481 */     if (!"file".equalsIgnoreCase(prot))
/* 482 */       return null; 
/*     */     try {
/* 485 */       return new File(url.toURI());
/* 486 */     } catch (URISyntaxException use) {
/* 487 */       LogFactory.getLog(IoUtil.class).warn("failed converting URL to a file " + url, use);
/* 489 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static URL file2url(File file) throws MalformedURLException {
/*     */     try {
/* 502 */       return file.getCanonicalFile().toURI().toURL();
/* 503 */     } catch (MalformedURLException mue) {
/* 504 */       throw mue;
/* 505 */     } catch (IOException ioe) {
/* 506 */       throw new MalformedURLException(ResourceManager.getMessage("org.java.plugin.util", "file2urlFailed", new Object[] { file, ioe }));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugi\\util\IoUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */