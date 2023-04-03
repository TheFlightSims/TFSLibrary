/*     */ package org.java.plugin.standard;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Locale;
/*     */ import java.util.zip.ZipEntry;
/*     */ import java.util.zip.ZipFile;
/*     */ import java.util.zip.ZipInputStream;
/*     */ import org.java.plugin.util.IoUtil;
/*     */ 
/*     */ final class ShadingUtil {
/*     */   static String getExtension(String name) {
/* 283 */     if (name == null || name.length() == 0)
/* 284 */       return null; 
/* 286 */     int p = name.lastIndexOf('.');
/* 287 */     if (p != -1 && p > 0 && p < name.length() - 1)
/* 288 */       return name.substring(p + 1); 
/* 290 */     return null;
/*     */   }
/*     */   
/*     */   static void unpack(ZipFile zipFile, File destFolder) throws IOException {
/* 295 */     for (Enumeration<? extends ZipEntry> en = zipFile.entries(); en.hasMoreElements(); ) {
/* 296 */       ZipEntry entry = en.nextElement();
/* 297 */       String name = entry.getName();
/* 298 */       File entryFile = new File(destFolder.getCanonicalPath() + "/" + name);
/* 300 */       if (name.endsWith("/")) {
/* 301 */         if (!entryFile.exists() && !entryFile.mkdirs())
/* 302 */           throw new IOException("can't create folder " + entryFile); 
/*     */       } else {
/* 305 */         File folder = entryFile.getParentFile();
/* 306 */         if (!folder.exists() && !folder.mkdirs())
/* 307 */           throw new IOException("can't create folder " + folder); 
/* 309 */         OutputStream out = new BufferedOutputStream(new FileOutputStream(entryFile, false));
/*     */         try {
/* 312 */           InputStream in = zipFile.getInputStream(entry);
/*     */           try {
/* 314 */             IoUtil.copyStream(in, out, 1024);
/*     */           } finally {
/* 316 */             in.close();
/*     */           } 
/*     */         } finally {
/* 319 */           out.close();
/*     */         } 
/*     */       } 
/* 322 */       entryFile.setLastModified(entry.getTime());
/*     */     } 
/*     */   }
/*     */   
/*     */   static void unpack(InputStream strm, File destFolder) throws IOException {
/* 328 */     ZipInputStream zipStrm = new ZipInputStream(strm);
/* 329 */     ZipEntry entry = zipStrm.getNextEntry();
/* 330 */     while (entry != null) {
/* 331 */       String name = entry.getName();
/* 332 */       File entryFile = new File(destFolder.getCanonicalPath() + "/" + name);
/* 334 */       if (name.endsWith("/")) {
/* 335 */         if (!entryFile.exists() && !entryFile.mkdirs())
/* 336 */           throw new IOException("can't create folder " + entryFile); 
/*     */       } else {
/* 339 */         File folder = entryFile.getParentFile();
/* 340 */         if (!folder.exists() && !folder.mkdirs())
/* 341 */           throw new IOException("can't create folder " + folder); 
/* 343 */         OutputStream out = new BufferedOutputStream(new FileOutputStream(entryFile, false));
/*     */         try {
/* 346 */           IoUtil.copyStream(zipStrm, out, 1024);
/*     */         } finally {
/* 348 */           out.close();
/*     */         } 
/*     */       } 
/* 351 */       entryFile.setLastModified(entry.getTime());
/* 352 */       entry = zipStrm.getNextEntry();
/*     */     } 
/*     */   }
/*     */   
/*     */   static boolean deleteFile(File file) {
/* 357 */     if (file.isDirectory())
/* 358 */       IoUtil.emptyFolder(file); 
/* 360 */     return file.delete();
/*     */   }
/*     */   
/*     */   static Date getLastModified(URL url) throws IOException {
/* 364 */     long result = 0L;
/* 365 */     File sourceFile = null;
/* 366 */     if ("jar".equalsIgnoreCase(url.getProtocol())) {
/* 367 */       String urlStr = url.toExternalForm();
/* 368 */       int p = urlStr.indexOf("!/");
/* 369 */       if (p != -1)
/* 370 */         sourceFile = IoUtil.url2file(new URL(urlStr.substring(4, p))); 
/*     */     } 
/* 373 */     if (sourceFile == null)
/* 374 */       sourceFile = IoUtil.url2file(url); 
/* 376 */     if (sourceFile != null) {
/* 377 */       result = sourceFile.lastModified();
/*     */     } else {
/* 379 */       URLConnection cnn = url.openConnection();
/*     */       try {
/* 381 */         cnn.setUseCaches(false);
/* 382 */         result = cnn.getLastModified();
/*     */       } finally {
/* 384 */         cnn.getInputStream().close();
/*     */       } 
/*     */     } 
/* 387 */     if (result == 0L)
/* 388 */       throw new IOException("can't retrieve modification date for resource " + url); 
/* 393 */     Calendar cldr = Calendar.getInstance(Locale.ENGLISH);
/* 394 */     cldr.setTime(new Date(result));
/* 395 */     cldr.set(14, 0);
/* 396 */     return cldr.getTime();
/*     */   }
/*     */   
/*     */   private static String getRelativePath(File base, File file) throws IOException {
/* 402 */     String basePath, filePath = file.getCanonicalPath();
/* 403 */     if (base.isFile()) {
/* 404 */       File baseParent = base.getParentFile();
/* 405 */       if (baseParent == null)
/* 406 */         return null; 
/* 408 */       basePath = baseParent.getCanonicalPath();
/*     */     } else {
/* 410 */       basePath = base.getCanonicalPath();
/*     */     } 
/* 412 */     if (!basePath.endsWith(File.separator))
/* 413 */       basePath = basePath + File.separator; 
/* 415 */     int p = basePath.indexOf(File.separatorChar);
/* 416 */     String prefix = null;
/* 417 */     while (p != -1) {
/* 418 */       String newPrefix = basePath.substring(0, p + 1);
/* 419 */       if (!filePath.startsWith(newPrefix))
/*     */         break; 
/* 422 */       prefix = newPrefix;
/* 423 */       p = basePath.indexOf(File.separatorChar, p + 1);
/*     */     } 
/* 425 */     if (prefix == null)
/* 426 */       return null; 
/* 428 */     filePath = filePath.substring(prefix.length());
/* 429 */     if (prefix.length() == basePath.length())
/* 430 */       return filePath; 
/* 432 */     int c = 0;
/* 433 */     p = basePath.indexOf(File.separatorChar, prefix.length());
/* 434 */     while (p != -1) {
/* 435 */       c++;
/* 436 */       p = basePath.indexOf(File.separatorChar, p + 1);
/*     */     } 
/* 438 */     for (int i = 0; i < c; i++)
/* 439 */       filePath = ".." + File.separator + filePath; 
/* 441 */     return filePath;
/*     */   }
/*     */   
/*     */   private static String getRelativeUrl(File base, File file) throws IOException {
/* 446 */     String result = getRelativePath(base, file);
/* 447 */     if (result == null)
/* 448 */       return null; 
/* 450 */     result = result.replace('\\', '/');
/* 451 */     if (file.isDirectory() && !result.endsWith("/"))
/* 452 */       result = result + "/"; 
/* 454 */     return result;
/*     */   }
/*     */   
/*     */   static String getRelativeUrl(File base, URL url) throws IOException {
/* 459 */     File file = IoUtil.url2file(url);
/* 460 */     if (file != null) {
/* 461 */       String result = getRelativeUrl(base, file);
/* 462 */       if (result != null)
/* 463 */         return result; 
/*     */     } 
/* 466 */     if ("jar".equalsIgnoreCase(url.getProtocol())) {
/* 467 */       String urlStr = url.toExternalForm();
/* 468 */       int p = urlStr.indexOf("!/");
/* 469 */       if (p != -1)
/* 470 */         return "jar:" + getRelativeUrl(base, new URL(urlStr.substring(4, p))) + urlStr.substring(p); 
/*     */     } 
/* 475 */     return url.toExternalForm();
/*     */   }
/*     */   
/*     */   static URL buildURL(URL base, String url) throws MalformedURLException {
/* 480 */     if (!url.toLowerCase(Locale.ENGLISH).startsWith("jar:"))
/* 481 */       return new URL(base, url); 
/* 483 */     int p = url.indexOf("!/");
/* 484 */     if (p == -1)
/* 485 */       return new URL(base, url); 
/* 487 */     return new URL("jar:" + (new URL(base, url.substring(4, p))).toExternalForm() + url.substring(p));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\standard\ShadingUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */