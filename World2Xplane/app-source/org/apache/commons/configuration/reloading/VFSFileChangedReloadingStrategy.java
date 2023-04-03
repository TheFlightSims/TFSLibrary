/*     */ package org.apache.commons.configuration.reloading;
/*     */ 
/*     */ import org.apache.commons.configuration.ConfigurationRuntimeException;
/*     */ import org.apache.commons.configuration.FileConfiguration;
/*     */ import org.apache.commons.configuration.FileSystem;
/*     */ import org.apache.commons.configuration.FileSystemBased;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.commons.vfs2.FileObject;
/*     */ import org.apache.commons.vfs2.FileSystemException;
/*     */ import org.apache.commons.vfs2.FileSystemManager;
/*     */ import org.apache.commons.vfs2.VFS;
/*     */ 
/*     */ public class VFSFileChangedReloadingStrategy implements ReloadingStrategy {
/*     */   private static final int DEFAULT_REFRESH_DELAY = 5000;
/*     */   
/*     */   protected FileConfiguration configuration;
/*     */   
/*     */   protected long lastModified;
/*     */   
/*     */   protected long lastChecked;
/*     */   
/*  67 */   protected long refreshDelay = 5000L;
/*     */   
/*     */   private boolean reloading;
/*     */   
/*  73 */   private Log log = LogFactory.getLog(getClass());
/*     */   
/*     */   public void setConfiguration(FileConfiguration configuration) {
/*  77 */     this.configuration = configuration;
/*     */   }
/*     */   
/*     */   public void init() {
/*  82 */     if (this.configuration.getURL() == null && this.configuration.getFileName() == null)
/*     */       return; 
/*  86 */     if (this.configuration == null)
/*  88 */       throw new IllegalStateException("No configuration has been set for this strategy"); 
/*  90 */     updateLastModified();
/*     */   }
/*     */   
/*     */   public boolean reloadingRequired() {
/*  95 */     if (!this.reloading) {
/*  97 */       long now = System.currentTimeMillis();
/*  99 */       if (now > this.lastChecked + this.refreshDelay) {
/* 101 */         this.lastChecked = now;
/* 102 */         if (hasChanged())
/* 104 */           this.reloading = true; 
/*     */       } 
/*     */     } 
/* 109 */     return this.reloading;
/*     */   }
/*     */   
/*     */   public void reloadingPerformed() {
/* 114 */     updateLastModified();
/*     */   }
/*     */   
/*     */   public long getRefreshDelay() {
/* 124 */     return this.refreshDelay;
/*     */   }
/*     */   
/*     */   public void setRefreshDelay(long refreshDelay) {
/* 134 */     this.refreshDelay = refreshDelay;
/*     */   }
/*     */   
/*     */   protected void updateLastModified() {
/* 142 */     FileObject file = getFile();
/* 143 */     if (file != null)
/*     */       try {
/* 147 */         this.lastModified = file.getContent().getLastModifiedTime();
/* 149 */       } catch (FileSystemException fse) {
/* 151 */         this.log.error("Unable to get last modified time for" + file.getName().getURI());
/*     */       }  
/* 154 */     this.reloading = false;
/*     */   }
/*     */   
/*     */   protected boolean hasChanged() {
/* 164 */     FileObject file = getFile();
/*     */     try {
/* 167 */       if (file == null || !file.exists())
/* 169 */         return false; 
/* 172 */       return (file.getContent().getLastModifiedTime() > this.lastModified);
/* 174 */     } catch (FileSystemException ex) {
/* 176 */       this.log.error("Unable to get last modified time for" + file.getName().getURI());
/* 177 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected FileObject getFile() {
/*     */     try {
/* 191 */       FileSystemManager fsManager = VFS.getManager();
/* 192 */       FileSystem fs = ((FileSystemBased)this.configuration).getFileSystem();
/* 193 */       String uri = fs.getPath(null, this.configuration.getURL(), this.configuration.getBasePath(), this.configuration.getFileName());
/* 195 */       if (uri == null)
/* 197 */         throw new ConfigurationRuntimeException("Unable to determine file to monitor"); 
/* 199 */       return fsManager.resolveFile(uri);
/* 201 */     } catch (FileSystemException fse) {
/* 203 */       String msg = "Unable to monitor " + this.configuration.getURL().toString();
/* 204 */       this.log.error(msg);
/* 205 */       throw new ConfigurationRuntimeException(msg, fse);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\reloading\VFSFileChangedReloadingStrategy.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */