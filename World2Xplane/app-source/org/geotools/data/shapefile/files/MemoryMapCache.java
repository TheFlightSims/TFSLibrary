/*     */ package org.geotools.data.shapefile.files;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.nio.MappedByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.resources.NIOUtilities;
/*     */ import org.geotools.util.SoftValueHashMap;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ class MemoryMapCache {
/*  45 */   static final Logger LOGGER = Logging.getLogger(MemoryMapCache.class);
/*     */   
/*  47 */   SoftValueHashMap<MappingKey, MappedByteBuffer> buffers = new SoftValueHashMap(0, new BufferCleaner());
/*     */   
/*     */   MappedByteBuffer map(FileChannel wrapped, URL url, FileChannel.MapMode mode, long position, long size) throws IOException {
/*  50 */     if (mode != FileChannel.MapMode.READ_ONLY)
/*  51 */       return wrapped.map(mode, position, size); 
/*  54 */     File file = DataUtilities.urlToFile(url).getCanonicalFile();
/*  55 */     MappingKey mk = new MappingKey(file, position, size);
/*  56 */     MappedByteBuffer buffer = (MappedByteBuffer)this.buffers.get(mk);
/*  57 */     if (buffer == null) {
/*  58 */       synchronized (this) {
/*  59 */         buffer = (MappedByteBuffer)this.buffers.get(mk);
/*  60 */         if (buffer == null) {
/*  61 */           buffer = wrapped.map(mode, position, size);
/*  62 */           this.buffers.put(mk, buffer);
/*  63 */           if (LOGGER.isLoggable(Level.FINE))
/*  64 */             LOGGER.log(Level.FINE, "Mapping and caching " + file.getAbsolutePath()); 
/*     */         } 
/*     */       } 
/*  69 */     } else if (LOGGER.isLoggable(Level.FINE)) {
/*  70 */       LOGGER.log(Level.FINE, "Using cached map for " + file.getAbsolutePath());
/*     */     } 
/*  74 */     return (MappedByteBuffer)buffer.duplicate();
/*     */   }
/*     */   
/*     */   void cleanFileCache(URL url) {
/*     */     try {
/*  84 */       File rawFile = DataUtilities.urlToFile(url);
/*  85 */       if (rawFile == null)
/*     */         return; 
/*  89 */       File file = rawFile.getCanonicalFile();
/*  90 */       List<MappingKey> keys = new ArrayList<MappingKey>(this.buffers.keySet());
/*  91 */       for (MappingKey key : keys) {
/*  92 */         if (key.file.equals(file)) {
/*  93 */           MappedByteBuffer buffer = (MappedByteBuffer)this.buffers.remove(key);
/*  94 */           NIOUtilities.clean(buffer, true);
/*  95 */           if (LOGGER.isLoggable(Level.FINE))
/*  96 */             LOGGER.log(Level.FINE, "Removed mapping for " + file.getAbsolutePath()); 
/*     */         } 
/*     */       } 
/* 100 */     } catch (Throwable t) {
/* 101 */       LOGGER.log(Level.WARNING, "An error occurred while trying to clean the memory map cache", t);
/*     */     } 
/*     */   }
/*     */   
/*     */   void clean() {
/* 106 */     List<MappingKey> keys = new ArrayList<MappingKey>(this.buffers.keySet());
/* 107 */     for (MappingKey key : keys) {
/* 108 */       MappedByteBuffer buffer = (MappedByteBuffer)this.buffers.remove(key);
/* 109 */       NIOUtilities.clean(buffer, true);
/* 110 */       if (LOGGER.isLoggable(Level.FINE))
/* 111 */         LOGGER.log(Level.FINE, "Removed mapping for " + key.file.getAbsolutePath()); 
/*     */     } 
/*     */   }
/*     */   
/*     */   static class MappingKey {
/*     */     File file;
/*     */     
/*     */     long position;
/*     */     
/*     */     long size;
/*     */     
/*     */     public MappingKey(File file, long position, long size) {
/* 126 */       this.file = file;
/* 127 */       this.position = position;
/* 128 */       this.size = size;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 133 */       int prime = 31;
/* 134 */       int result = 1;
/* 135 */       result = 31 * result + ((this.file == null) ? 0 : this.file.hashCode());
/* 136 */       result = 31 * result + (int)(this.position ^ this.position >>> 32L);
/* 137 */       result = 31 * result + (int)(this.size ^ this.size >>> 32L);
/* 138 */       return result;
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 143 */       if (this == obj)
/* 144 */         return true; 
/* 145 */       if (obj == null)
/* 146 */         return false; 
/* 147 */       if (getClass() != obj.getClass())
/* 148 */         return false; 
/* 149 */       MappingKey other = (MappingKey)obj;
/* 150 */       if (this.file == null) {
/* 151 */         if (other.file != null)
/* 152 */           return false; 
/* 153 */       } else if (!this.file.equals(other.file)) {
/* 154 */         return false;
/*     */       } 
/* 155 */       if (this.position != other.position)
/* 156 */         return false; 
/* 157 */       if (this.size != other.size)
/* 158 */         return false; 
/* 159 */       return true;
/*     */     }
/*     */   }
/*     */   
/*     */   public class BufferCleaner implements SoftValueHashMap.ValueCleaner {
/*     */     public void clean(Object key, Object object) {
/* 173 */       MappedByteBuffer buffer = (MappedByteBuffer)object;
/* 174 */       NIOUtilities.clean(buffer, true);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\files\MemoryMapCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */