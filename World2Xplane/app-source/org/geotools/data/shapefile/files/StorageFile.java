/*     */ package org.geotools.data.shapefile.files;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.net.URL;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.util.Arrays;
/*     */ import java.util.SortedSet;
/*     */ import java.util.TreeSet;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public final class StorageFile implements Comparable<StorageFile>, FileWriter {
/*  47 */   static final Logger LOGGER = Logging.getLogger("org.geotools.data.shapefile");
/*     */   
/*     */   private final ShpFiles shpFiles;
/*     */   
/*     */   private final File tempFile;
/*     */   
/*     */   private final ShpFileType type;
/*     */   
/*     */   public StorageFile(ShpFiles shpFiles, File tempFile, ShpFileType type) {
/*  54 */     this.shpFiles = shpFiles;
/*  55 */     this.tempFile = tempFile;
/*  56 */     this.type = type;
/*     */   }
/*     */   
/*     */   public File getFile() {
/*  65 */     return this.tempFile;
/*     */   }
/*     */   
/*     */   public FileChannel getWriteChannel() throws IOException {
/*  69 */     return (new RandomAccessFile(this.tempFile, "rw")).getChannel();
/*     */   }
/*     */   
/*     */   public void replaceOriginal() throws IOException {
/*  80 */     replaceOriginals(new StorageFile[] { this });
/*     */   }
/*     */   
/*     */   public static void replaceOriginals(StorageFile... storageFiles) throws IOException {
/*  93 */     SortedSet<StorageFile> files = new TreeSet<StorageFile>(Arrays.asList(storageFiles));
/*  95 */     ShpFiles currentShpFiles = null;
/*  96 */     URL shpURL = null;
/*  97 */     StorageFile locker = null;
/*     */     try {
/* 101 */       for (StorageFile storageFile : files) {
/* 102 */         if (currentShpFiles != storageFile.shpFiles) {
/* 104 */           unlock(currentShpFiles, shpURL, locker);
/* 105 */           locker = storageFile;
/* 106 */           currentShpFiles = storageFile.shpFiles;
/* 107 */           shpURL = currentShpFiles.acquireWrite(ShpFileType.SHP, storageFile);
/*     */         } 
/* 110 */         File storage = storageFile.getFile();
/* 112 */         URL url = storageFile.getSrcURLForWrite();
/*     */         try {
/* 114 */           File dest = DataUtilities.urlToFile(url);
/* 116 */           if (storage.equals(dest))
/*     */             return; 
/* 119 */           if (dest.exists() && 
/* 120 */             !dest.delete()) {
/* 121 */             LOGGER.severe("Unable to delete the file: " + dest + " when attempting to replace with temporary copy.");
/* 123 */             if (storageFile.shpFiles.numberOfLocks() > 0) {
/* 124 */               LOGGER.severe("The problem is almost certainly caused by the fact that there are still locks being held on the shapefiles.  Probably a reader or writer was left unclosed");
/* 126 */               storageFile.shpFiles.logCurrentLockers(Level.SEVERE);
/*     */             } 
/*     */           } 
/* 132 */           if (storage.exists() && !storage.renameTo(dest)) {
/* 133 */             LOGGER.finer("Unable to rename temporary file to the file: " + dest + " when attempting to replace with temporary copy");
/* 137 */             copyFile(storage, url, dest);
/* 138 */             if (!storage.delete())
/* 139 */               storage.deleteOnExit(); 
/*     */           } 
/*     */         } finally {
/* 143 */           storageFile.unlockWriteURL(url);
/* 145 */           if (storage.exists())
/* 146 */             storage.delete(); 
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 151 */       unlock(currentShpFiles, shpURL, locker);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void copyFile(File storage, URL url, File dest) throws FileNotFoundException, IOException {
/* 158 */     FileChannel in = null;
/* 159 */     FileChannel out = null;
/*     */     try {
/* 161 */       in = (new FileInputStream(storage)).getChannel();
/* 162 */       out = (new FileOutputStream(dest)).getChannel();
/* 165 */       int maxCount = 67076096;
/* 166 */       long size = in.size();
/* 167 */       long position = 0L;
/* 168 */       while (position < size)
/* 169 */         position += in.transferTo(position, maxCount, out); 
/*     */     } finally {
/* 172 */       if (in != null)
/* 173 */         in.close(); 
/* 175 */       if (out != null)
/* 176 */         out.close(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private URL getSrcURLForWrite() {
/* 182 */     return this.shpFiles.acquireWrite(this.type, this);
/*     */   }
/*     */   
/*     */   private void unlockWriteURL(URL url) {
/* 186 */     this.shpFiles.unlockWrite(url, this);
/*     */   }
/*     */   
/*     */   private static void unlock(ShpFiles currentShpFiles, URL shpURL, StorageFile locker) {
/* 191 */     if (currentShpFiles == null)
/*     */       return; 
/* 195 */     currentShpFiles.unlockWrite(shpURL, locker);
/*     */   }
/*     */   
/*     */   public int compareTo(StorageFile o) {
/* 203 */     if (this == o)
/* 204 */       return 0; 
/* 209 */     return getFile().compareTo(o.getFile());
/*     */   }
/*     */   
/*     */   public String toString() {
/* 214 */     return id();
/*     */   }
/*     */   
/*     */   public String id() {
/* 218 */     return getClass().getSimpleName() + ": " + this.tempFile.getName();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\files\StorageFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */