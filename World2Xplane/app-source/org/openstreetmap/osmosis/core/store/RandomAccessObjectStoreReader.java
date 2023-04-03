/*     */ package org.openstreetmap.osmosis.core.store;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ import org.openstreetmap.osmosis.core.lifecycle.Releasable;
/*     */ 
/*     */ public class RandomAccessObjectStoreReader<T> implements Releasable {
/*  25 */   private static final Logger LOG = Logger.getLogger(RandomAccessObjectStoreReader.class.getName());
/*     */   
/*     */   private BufferedRandomAccessFileInputStream randomFile;
/*     */   
/*     */   private ObjectReader objectReader;
/*     */   
/*     */   public RandomAccessObjectStoreReader(BufferedRandomAccessFileInputStream randomFile, ObjectReader objectReader) {
/*  40 */     this.randomFile = randomFile;
/*  41 */     this.objectReader = objectReader;
/*     */   }
/*     */   
/*     */   private void seek(long offset) {
/*     */     try {
/*  53 */       this.randomFile.seek(offset);
/*  54 */     } catch (IOException e) {
/*  55 */       throw new OsmosisRuntimeException("Unable to seek to position " + offset + " in the storage file.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public T get(long offset) {
/*  69 */     seek(offset);
/*  71 */     return (T)this.objectReader.readObject();
/*     */   }
/*     */   
/*     */   public long length() {
/*     */     try {
/*  82 */       return this.randomFile.length();
/*  83 */     } catch (IOException e) {
/*  84 */       throw new OsmosisRuntimeException("Unable to obtain the length of the storage file.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public long position() {
/*     */     try {
/*  96 */       return this.randomFile.position();
/*  97 */     } catch (IOException e) {
/*  98 */       throw new OsmosisRuntimeException("Unable to obtain the current position in the storage file.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Iterator<T> iterate(long offset) {
/* 112 */     seek(offset);
/* 114 */     return new ObjectDataInputIterator<T>(this.objectReader);
/*     */   }
/*     */   
/*     */   public Iterator<T> iterate() {
/* 125 */     return iterate(0L);
/*     */   }
/*     */   
/*     */   public void release() {
/* 134 */     if (this.randomFile != null) {
/*     */       try {
/* 136 */         this.randomFile.close();
/* 137 */       } catch (IOException e) {
/* 139 */         LOG.log(Level.WARNING, "Unable to close random access file.", e);
/*     */       } 
/* 141 */       this.randomFile = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\RandomAccessObjectStoreReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */