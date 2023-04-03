/*     */ package org.java.plugin.standard;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Locale;
/*     */ import org.java.plugin.PluginManager;
/*     */ import org.java.plugin.util.IoUtil;
/*     */ 
/*     */ public class StandardPluginLocation implements PluginManager.PluginLocation {
/*     */   private final URL context;
/*     */   
/*     */   private final URL manifest;
/*     */   
/*     */   public static PluginManager.PluginLocation create(File file) throws MalformedURLException {
/*  59 */     if (file.isDirectory()) {
/*  60 */       URL uRL = getManifestUrl(file);
/*  61 */       return (uRL == null) ? null : new StandardPluginLocation(IoUtil.file2url(file), uRL);
/*     */     } 
/*  65 */     String fileName = file.getName().toLowerCase(Locale.getDefault());
/*  66 */     if (!fileName.endsWith(".jar") && !fileName.endsWith(".zip"))
/*  68 */       return null; 
/*  70 */     URL manifestUrl = getManifestUrl(file);
/*  71 */     return (manifestUrl == null) ? null : new StandardPluginLocation(new URL("jar:" + IoUtil.file2url(file).toExternalForm() + "!/"), manifestUrl);
/*     */   }
/*     */   
/*     */   private static URL getManifestUrl(File file) throws MalformedURLException {
/*  79 */     if (file.isDirectory()) {
/*  80 */       File result = new File(file, "plugin.xml");
/*  81 */       if (result.isFile())
/*  82 */         return IoUtil.file2url(result); 
/*  84 */       result = new File(file, "plugin-fragment.xml");
/*  85 */       if (result.isFile())
/*  86 */         return IoUtil.file2url(result); 
/*  88 */       result = new File(file, "META-INF" + File.separator + "plugin.xml");
/*  90 */       if (result.isFile())
/*  91 */         return IoUtil.file2url(result); 
/*  93 */       result = new File(file, "META-INF" + File.separator + "plugin-fragment.xml");
/*  95 */       if (result.isFile())
/*  96 */         return IoUtil.file2url(result); 
/*  98 */       return null;
/*     */     } 
/* 100 */     if (!file.isFile())
/* 101 */       return null; 
/* 103 */     URL url = new URL("jar:" + IoUtil.file2url(file).toExternalForm() + "!/plugin.xml");
/* 106 */     if (IoUtil.isResourceExists(url))
/* 107 */       return url; 
/* 109 */     url = new URL("jar:" + IoUtil.file2url(file).toExternalForm() + "!/plugin-fragment.xml");
/* 112 */     if (IoUtil.isResourceExists(url))
/* 113 */       return url; 
/* 115 */     url = new URL("jar:" + IoUtil.file2url(file).toExternalForm() + "!/META-INF/plugin.xml");
/* 118 */     if (IoUtil.isResourceExists(url))
/* 119 */       return url; 
/* 121 */     url = new URL("jar:" + IoUtil.file2url(file).toExternalForm() + "!/META-INF/plugin-fragment.xml");
/* 124 */     if (IoUtil.isResourceExists(url))
/* 125 */       return url; 
/* 127 */     return null;
/*     */   }
/*     */   
/*     */   public StandardPluginLocation(URL aContext, URL aManifest) {
/* 139 */     if (aContext == null)
/* 140 */       throw new NullPointerException("context"); 
/* 142 */     if (aManifest == null)
/* 143 */       throw new NullPointerException("manifest"); 
/* 145 */     this.context = aContext;
/* 146 */     this.manifest = aManifest;
/*     */   }
/*     */   
/*     */   public StandardPluginLocation(File file, String manifestPath) throws MalformedURLException {
/* 159 */     if (file.isDirectory()) {
/* 160 */       this.context = IoUtil.file2url(file);
/*     */     } else {
/* 162 */       this.context = new URL("jar:" + IoUtil.file2url(file).toExternalForm() + "!/");
/*     */     } 
/* 165 */     this.manifest = new URL(this.context, manifestPath.startsWith("/") ? manifestPath.substring(1) : manifestPath);
/*     */   }
/*     */   
/*     */   public URL getManifestLocation() {
/* 173 */     return this.manifest;
/*     */   }
/*     */   
/*     */   public URL getContextLocation() {
/* 180 */     return this.context;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 188 */     return this.context.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\standard\StandardPluginLocation.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */