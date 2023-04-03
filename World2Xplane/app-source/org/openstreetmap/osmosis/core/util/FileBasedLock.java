/*     */ package org.openstreetmap.osmosis.core.util;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.FileLock;
/*     */ import java.util.logging.Logger;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ import org.openstreetmap.osmosis.core.lifecycle.Releasable;
/*     */ 
/*     */ public class FileBasedLock implements Releasable {
/*  23 */   private static final Logger LOG = Logger.getLogger(FileBasedLock.class.getName());
/*     */   
/*     */   private File lockFile;
/*     */   
/*     */   private FileOutputStream outputStream;
/*     */   
/*     */   private FileChannel fileChannel;
/*     */   
/*     */   private FileLock fileLock;
/*     */   
/*     */   private boolean initialized;
/*     */   
/*     */   public FileBasedLock(File lockFile) {
/*  39 */     this.lockFile = lockFile;
/*  41 */     this.initialized = false;
/*     */   }
/*     */   
/*     */   private void initialize() {
/*  49 */     if (!this.initialized) {
/*     */       try {
/*  51 */         this.outputStream = new FileOutputStream(this.lockFile);
/*  52 */       } catch (IOException e) {
/*  53 */         throw new OsmosisRuntimeException("Unable to open lock file " + this.lockFile + ".");
/*     */       } 
/*  56 */       this.fileChannel = this.outputStream.getChannel();
/*  58 */       this.initialized = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void lock() {
/*  68 */     initialize();
/*  70 */     if (this.fileLock != null)
/*  71 */       throw new OsmosisRuntimeException("A lock has already been obtained on file " + this.lockFile + "."); 
/*     */     try {
/*  75 */       this.fileLock = this.fileChannel.tryLock();
/*  77 */       if (this.fileLock == null)
/*  78 */         throw new OsmosisRuntimeException("A exclusive lock already exists on file " + this.lockFile + "."); 
/*  81 */     } catch (IOException e) {
/*  82 */       throw new OsmosisRuntimeException("An error occurred while trying to obtain an exclusive lock on file " + this.lockFile + ".");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void unlock() {
/*  92 */     initialize();
/*     */     try {
/*  95 */       this.fileLock.release();
/*  96 */       this.fileLock = null;
/*  97 */     } catch (IOException e) {
/*  98 */       throw new OsmosisRuntimeException("Unable to release lock on file " + this.lockFile + ".");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void release() {
/* 107 */     if (this.outputStream != null)
/*     */       try {
/* 109 */         this.outputStream.close();
/* 110 */       } catch (Exception e) {
/* 111 */         LOG.warning("Unable to close lock stream on file " + this.lockFile + ".");
/*     */       } finally {
/* 113 */         this.outputStream = null;
/* 114 */         this.fileChannel = null;
/* 115 */         this.fileLock = null;
/* 116 */         this.initialized = false;
/*     */       }  
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\cor\\util\FileBasedLock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */