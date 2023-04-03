/*     */ package org.geotools.referencing.factory.gridshift;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLDecoder;
/*     */ 
/*     */ public class DataUtilities {
/*     */   public static File urlToFile(URL url) {
/*     */     String path3;
/*  43 */     if (!"file".equals(url.getProtocol()))
/*  44 */       return null; 
/*  46 */     String string = url.toExternalForm();
/*  47 */     if (string.contains("+"))
/*  51 */       string = string.replace("+", "%2B"); 
/*     */     try {
/*  54 */       string = URLDecoder.decode(string, "UTF-8");
/*  55 */     } catch (UnsupportedEncodingException e) {
/*  56 */       throw new RuntimeException("Could not decode the URL to UTF-8 format", e);
/*     */     } 
/*  59 */     String simplePrefix = "file:/";
/*  60 */     String standardPrefix = "file://";
/*  61 */     String os = System.getProperty("os.name");
/*  62 */     if (os.toUpperCase().contains("WINDOWS") && string.startsWith(standardPrefix)) {
/*  64 */       path3 = string.substring(standardPrefix.length() - 2);
/*  65 */     } else if (string.startsWith(standardPrefix)) {
/*  66 */       path3 = string.substring(standardPrefix.length());
/*  67 */     } else if (string.startsWith(simplePrefix)) {
/*  68 */       path3 = string.substring(simplePrefix.length() - 1);
/*     */     } else {
/*  70 */       String auth = url.getAuthority();
/*  71 */       String path2 = url.getPath().replace("%20", " ");
/*  72 */       if (auth != null && !auth.equals("")) {
/*  73 */         path3 = "//" + auth + path2;
/*     */       } else {
/*  75 */         path3 = path2;
/*     */       } 
/*     */     } 
/*  78 */     return new File(path3);
/*     */   }
/*     */   
/*     */   public static URL fileToURL(File file) {
/*     */     try {
/*  97 */       URL url = file.toURI().toURL();
/*  98 */       String string = url.toExternalForm();
/*  99 */       if (string.contains("+"))
/* 103 */         string = string.replace("+", "%2B"); 
/* 105 */       if (string.contains(" "))
/* 109 */         string = string.replace(" ", "%20"); 
/* 111 */       return new URL(string);
/* 112 */     } catch (MalformedURLException e) {
/* 113 */       return null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\gridshift\DataUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */