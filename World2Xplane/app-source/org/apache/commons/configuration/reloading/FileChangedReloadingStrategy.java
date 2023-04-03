/*     */ package org.apache.commons.configuration.reloading;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import org.apache.commons.configuration.ConfigurationUtils;
/*     */ import org.apache.commons.configuration.FileConfiguration;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ public class FileChangedReloadingStrategy implements ReloadingStrategy {
/*     */   private static final String JAR_PROTOCOL = "jar";
/*     */   
/*     */   private static final int DEFAULT_REFRESH_DELAY = 5000;
/*     */   
/*     */   protected FileConfiguration configuration;
/*     */   
/*     */   protected long lastModified;
/*     */   
/*     */   protected long lastChecked;
/*     */   
/*  64 */   protected long refreshDelay = 5000L;
/*     */   
/*     */   private boolean reloading;
/*     */   
/*  70 */   private Log logger = LogFactory.getLog(FileChangedReloadingStrategy.class);
/*     */   
/*     */   public void setConfiguration(FileConfiguration configuration) {
/*  74 */     this.configuration = configuration;
/*     */   }
/*     */   
/*     */   public void init() {
/*  79 */     updateLastModified();
/*     */   }
/*     */   
/*     */   public boolean reloadingRequired() {
/*  84 */     if (!this.reloading) {
/*  86 */       long now = System.currentTimeMillis();
/*  88 */       if (now > this.lastChecked + this.refreshDelay) {
/*  90 */         this.lastChecked = now;
/*  91 */         if (hasChanged()) {
/*  93 */           if (this.logger.isDebugEnabled())
/*  95 */             this.logger.debug("File change detected: " + getName()); 
/*  97 */           this.reloading = true;
/*     */         } 
/*     */       } 
/*     */     } 
/* 102 */     return this.reloading;
/*     */   }
/*     */   
/*     */   public void reloadingPerformed() {
/* 107 */     updateLastModified();
/*     */   }
/*     */   
/*     */   public long getRefreshDelay() {
/* 117 */     return this.refreshDelay;
/*     */   }
/*     */   
/*     */   public void setRefreshDelay(long refreshDelay) {
/* 127 */     this.refreshDelay = refreshDelay;
/*     */   }
/*     */   
/*     */   protected void updateLastModified() {
/* 135 */     File file = getFile();
/* 136 */     if (file != null)
/* 138 */       this.lastModified = file.lastModified(); 
/* 140 */     this.reloading = false;
/*     */   }
/*     */   
/*     */   protected boolean hasChanged() {
/* 150 */     File file = getFile();
/* 151 */     if (file == null || !file.exists()) {
/* 153 */       if (this.logger.isWarnEnabled() && this.lastModified != 0L) {
/* 155 */         this.logger.warn("File was deleted: " + getName(file));
/* 156 */         this.lastModified = 0L;
/*     */       } 
/* 158 */       return false;
/*     */     } 
/* 161 */     return (file.lastModified() > this.lastModified);
/*     */   }
/*     */   
/*     */   protected File getFile() {
/* 172 */     return (this.configuration.getURL() != null) ? fileFromURL(this.configuration.getURL()) : this.configuration.getFile();
/*     */   }
/*     */   
/*     */   private File fileFromURL(URL url) {
/* 185 */     if ("jar".equals(url.getProtocol())) {
/* 187 */       String path = url.getPath();
/*     */       try {
/* 190 */         return ConfigurationUtils.fileFromURL(new URL(path.substring(0, path.indexOf('!'))));
/* 193 */       } catch (MalformedURLException mex) {
/* 195 */         return null;
/*     */       } 
/*     */     } 
/* 200 */     return ConfigurationUtils.fileFromURL(url);
/*     */   }
/*     */   
/*     */   private String getName() {
/* 206 */     return getName(getFile());
/*     */   }
/*     */   
/*     */   private String getName(File file) {
/* 211 */     String name = this.configuration.getURL().toString();
/* 212 */     if (name == null)
/* 214 */       if (file != null) {
/* 216 */         name = file.getAbsolutePath();
/*     */       } else {
/* 220 */         name = "base: " + this.configuration.getBasePath() + "file: " + this.configuration.getFileName();
/*     */       }  
/* 224 */     return name;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\reloading\FileChangedReloadingStrategy.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */