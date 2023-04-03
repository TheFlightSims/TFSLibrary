/*     */ package com.sun.media.jai.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.security.AccessControlException;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import java.util.PropertyResourceBundle;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.Vector;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
/*     */ 
/*     */ public class PropertyUtil {
/*  32 */   private static Hashtable bundles = new Hashtable();
/*     */   
/*  33 */   private static String propertiesDir = "javax/media/jai";
/*     */   
/*     */   public static InputStream getFileFromClasspath(String path) throws IOException, FileNotFoundException {
/*  39 */     String pathFinal = path;
/*  40 */     String sep = File.separator;
/*  41 */     String tmpHome = null;
/*     */     try {
/*  43 */       tmpHome = System.getProperty("java.home");
/*  44 */     } catch (Exception e) {
/*  45 */       tmpHome = null;
/*     */     } 
/*  47 */     String home = tmpHome;
/*  48 */     String urlHeader = (tmpHome == null) ? null : (home + sep + "lib" + sep);
/*  51 */     if (home != null) {
/*  52 */       String libExtPath = urlHeader + "ext" + sep + path;
/*  53 */       File libExtFile = new File(libExtPath);
/*     */       try {
/*  55 */         if (libExtFile.exists()) {
/*  56 */           InputStream inputStream = new FileInputStream(libExtFile);
/*  57 */           if (inputStream != null)
/*  58 */             return inputStream; 
/*     */         } 
/*  61 */       } catch (AccessControlException e) {}
/*     */     } 
/*  72 */     InputStream is = PropertyUtil.class.getResourceAsStream("/" + path);
/*  73 */     if (is != null)
/*  74 */       return is; 
/*  83 */     PrivilegedAction p = new PrivilegedAction(home, urlHeader, sep, pathFinal) {
/*     */         private final String val$home;
/*     */         
/*     */         private final String val$urlHeader;
/*     */         
/*     */         private final String val$sep;
/*     */         
/*     */         private final String val$pathFinal;
/*     */         
/*     */         public Object run() {
/*  85 */           String localHome = null;
/*  86 */           String localUrlHeader = null;
/*  87 */           if (this.val$home != null) {
/*  88 */             localHome = this.val$home;
/*  89 */             localUrlHeader = this.val$urlHeader;
/*     */           } else {
/*  91 */             localHome = System.getProperty("java.home");
/*  92 */             localUrlHeader = localHome + this.val$sep + "lib" + this.val$sep;
/*     */           } 
/*  94 */           String[] filenames = { localUrlHeader + "ext" + this.val$sep + "jai_core.jar", localUrlHeader + "ext" + this.val$sep + "jai_codec.jar", localUrlHeader + "jai_core.jar", localUrlHeader + "jai_codec.jar" };
/* 101 */           for (int i = 0; i < filenames.length; i++) {
/*     */             try {
/* 103 */               InputStream tmpIS = PropertyUtil.getFileFromJar(filenames[i], this.val$pathFinal);
/* 105 */               if (tmpIS != null)
/* 106 */                 return tmpIS; 
/* 108 */             } catch (Exception e) {}
/*     */           } 
/* 112 */           return null;
/*     */         }
/*     */       };
/* 116 */     return AccessController.<InputStream>doPrivileged(p);
/*     */   }
/*     */   
/*     */   private static InputStream getFileFromJar(String jarFilename, String path) throws Exception {
/* 122 */     JarFile f = null;
/*     */     try {
/* 124 */       f = new JarFile(jarFilename);
/* 125 */     } catch (Exception e) {}
/* 127 */     JarEntry ent = f.getJarEntry(path);
/* 128 */     if (ent != null)
/* 129 */       return f.getInputStream(ent); 
/* 131 */     return null;
/*     */   }
/*     */   
/*     */   private static ResourceBundle getBundle(String packageName) {
/* 136 */     ResourceBundle bundle = null;
/* 138 */     InputStream in = null;
/*     */     try {
/* 140 */       in = getFileFromClasspath(propertiesDir + "/" + packageName + ".properties");
/* 142 */       if (in != null) {
/* 143 */         bundle = new PropertyResourceBundle(in);
/* 144 */         bundles.put(packageName, bundle);
/* 145 */         return bundle;
/*     */       } 
/* 147 */     } catch (Exception e) {
/* 148 */       e.printStackTrace();
/*     */     } 
/* 151 */     return null;
/*     */   }
/*     */   
/*     */   public static String getString(String packageName, String key) {
/* 155 */     ResourceBundle b = (ResourceBundle)bundles.get(packageName);
/* 156 */     if (b == null)
/* 157 */       b = getBundle(packageName); 
/* 159 */     return b.getString(key);
/*     */   }
/*     */   
/*     */   public static String[] getPropertyNames(String[] propertyNames, String prefix) {
/* 173 */     if (propertyNames == null)
/* 174 */       return null; 
/* 175 */     if (prefix == null)
/* 176 */       throw new IllegalArgumentException(JaiI18N.getString("PropertyUtil0")); 
/* 179 */     prefix = prefix.toLowerCase();
/* 181 */     Vector names = new Vector();
/* 182 */     for (int i = 0; i < propertyNames.length; i++) {
/* 183 */       if (propertyNames[i].toLowerCase().startsWith(prefix))
/* 184 */         names.addElement(propertyNames[i]); 
/*     */     } 
/* 188 */     if (names.size() == 0)
/* 189 */       return null; 
/* 193 */     String[] prefixNames = new String[names.size()];
/* 194 */     int count = 0;
/* 195 */     for (Iterator it = names.iterator(); it.hasNext();)
/* 196 */       prefixNames[count++] = it.next(); 
/* 199 */     return prefixNames;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\ja\\util\PropertyUtil.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */