/*     */ package org.openstreetmap.osmosis.core.store;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.zip.GZIPOutputStream;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ import org.openstreetmap.osmosis.core.lifecycle.Completable;
/*     */ import org.openstreetmap.osmosis.core.lifecycle.ReleasableIterator;
/*     */ import org.openstreetmap.osmosis.core.util.MultiMemberGZIPInputStream;
/*     */ 
/*     */ public class SegmentedObjectStore<T extends Storeable> implements Completable {
/*  39 */   private static final Logger LOG = Logger.getLogger(SegmentedObjectStore.class.getName());
/*     */   
/*     */   private ObjectSerializationFactory serializationFactory;
/*     */   
/*     */   private StorageStage stage;
/*     */   
/*     */   private String storageFilePrefix;
/*     */   
/*     */   private File file;
/*     */   
/*     */   private FileOutputStream fileOutStream;
/*     */   
/*     */   private DataOutputStream dataOutStream;
/*     */   
/*     */   private ByteArrayOutputStream arrayOutStream;
/*     */   
/*     */   private StoreClassRegister storeClassRegister;
/*     */   
/*     */   private ObjectWriter objectWriter;
/*     */   
/*     */   private boolean chunkActive;
/*     */   
/*     */   private boolean useCompression;
/*     */   
/*     */   private long fileSize;
/*     */   
/*     */   public SegmentedObjectStore(ObjectSerializationFactory serializationFactory, String storageFilePrefix, boolean useCompression) {
/*  67 */     this.serializationFactory = serializationFactory;
/*  68 */     this.storageFilePrefix = storageFilePrefix;
/*  69 */     this.useCompression = useCompression;
/*  71 */     this.storeClassRegister = new DynamicStoreClassRegister();
/*  73 */     this.stage = StorageStage.NotStarted;
/*  74 */     this.fileSize = 0L;
/*  76 */     this.chunkActive = false;
/*     */   }
/*     */   
/*     */   public void add(T data) {
/*  88 */     if (this.stage.compareTo(StorageStage.Add) > 0)
/*  89 */       throw new OsmosisRuntimeException("Cannot add to storage in stage " + this.stage + "."); 
/*  93 */     if (this.stage.compareTo(StorageStage.Add) < 0)
/*     */       try {
/*  95 */         this.file = File.createTempFile(this.storageFilePrefix, null);
/*  97 */         this.fileOutStream = new FileOutputStream(this.file);
/*  99 */         this.stage = StorageStage.Add;
/* 101 */       } catch (IOException e) {
/* 102 */         throw new OsmosisRuntimeException("Unable to open temporary file " + this.file + " for writing.", e);
/*     */       }  
/* 107 */     if (!this.chunkActive)
/*     */       try {
/* 109 */         this.arrayOutStream = new ByteArrayOutputStream();
/* 111 */         if (this.useCompression) {
/* 112 */           this.dataOutStream = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(this.arrayOutStream), 65536));
/*     */         } else {
/* 116 */           this.dataOutStream = new DataOutputStream(new BufferedOutputStream(this.arrayOutStream, 65536));
/*     */         } 
/* 119 */         this.objectWriter = this.serializationFactory.createObjectWriter(new DataOutputStoreWriter(this.dataOutStream), this.storeClassRegister);
/* 122 */         this.chunkActive = true;
/* 124 */       } catch (IOException e) {
/* 125 */         throw new OsmosisRuntimeException("Unable to create object stream.", e);
/*     */       }  
/* 130 */     this.objectWriter.writeObject((Storeable)data);
/* 133 */     this.fileSize += this.arrayOutStream.size();
/*     */     try {
/* 137 */       this.arrayOutStream.writeTo(this.fileOutStream);
/* 138 */       this.arrayOutStream.reset();
/* 140 */     } catch (IOException e) {
/* 141 */       throw new OsmosisRuntimeException("Unable to write object to file.", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public long closeChunk() {
/* 156 */     if (this.stage.compareTo(StorageStage.Add) != 0)
/* 157 */       throw new OsmosisRuntimeException("Cannot create interval in stage " + this.stage + "."); 
/* 161 */     if (this.chunkActive)
/*     */       try {
/* 163 */         this.dataOutStream.close();
/* 164 */         this.fileSize += this.arrayOutStream.size();
/* 166 */         this.arrayOutStream.writeTo(this.fileOutStream);
/* 167 */         this.arrayOutStream.reset();
/* 170 */         this.arrayOutStream = null;
/* 171 */         this.dataOutStream = null;
/* 173 */         this.chunkActive = false;
/* 175 */       } catch (IOException e) {
/* 176 */         throw new OsmosisRuntimeException("Unable to create a new interval.", e);
/*     */       }  
/* 180 */     return this.fileSize;
/*     */   }
/*     */   
/*     */   private boolean initializeIteratingStage() {
/* 193 */     if (this.stage.compareTo(StorageStage.Released) >= 0)
/* 194 */       throw new OsmosisRuntimeException("Cannot iterate over storage in stage " + this.stage + "."); 
/* 198 */     if (this.stage.compareTo(StorageStage.NotStarted) <= 0)
/* 199 */       return false; 
/* 203 */     if (this.stage.compareTo(StorageStage.Add) == 0) {
/* 204 */       closeChunk();
/*     */       try {
/* 207 */         this.fileOutStream.close();
/* 209 */       } catch (IOException e) {
/* 210 */         throw new OsmosisRuntimeException("Unable to close output stream.", e);
/*     */       } finally {
/* 212 */         this.fileOutStream = null;
/*     */       } 
/* 215 */       this.stage = StorageStage.Reading;
/*     */     } 
/* 219 */     return true;
/*     */   }
/*     */   
/*     */   public ReleasableIterator<T> iterate() {
/* 230 */     return iterate(0L, -1L);
/*     */   }
/*     */   
/*     */   public ReleasableIterator<T> iterate(long streamOffset, long maxObjectCount) {
/* 246 */     FileInputStream fileStream = null;
/*     */     try {
/*     */       DataInputStream dataInStream;
/* 252 */       if (!initializeIteratingStage())
/* 253 */         return new EmptyIterator(); 
/*     */       try {
/* 258 */         fileStream = new FileInputStream(this.file);
/* 259 */       } catch (IOException e) {
/* 260 */         throw new OsmosisRuntimeException("Unable to open file for reading.", e);
/*     */       } 
/* 264 */       if (streamOffset > 0L)
/*     */         try {
/* 266 */           fileStream.skip(streamOffset);
/* 267 */         } catch (IOException e) {
/* 268 */           throw new OsmosisRuntimeException("Unable to skip to specified location in file.", e);
/*     */         }  
/*     */       try {
/* 274 */         if (this.useCompression) {
/* 275 */           dataInStream = new DataInputStream(new BufferedInputStream((InputStream)new MultiMemberGZIPInputStream(fileStream), 65536));
/*     */         } else {
/* 279 */           dataInStream = new DataInputStream(new BufferedInputStream(fileStream, 65536));
/*     */         } 
/* 282 */       } catch (IOException e) {
/* 283 */         throw new OsmosisRuntimeException("Unable to open object stream.", e);
/*     */       } 
/* 288 */       fileStream = null;
/* 290 */       ObjectReader objectReader = this.serializationFactory.createObjectReader(new DataInputStoreReader(dataInStream), this.storeClassRegister);
/* 293 */       if (maxObjectCount >= 0L)
/* 294 */         return (ReleasableIterator)new SubObjectStreamIterator<Storeable>(dataInStream, objectReader, maxObjectCount); 
/* 296 */       return new ObjectStreamIterator(dataInStream, objectReader);
/*     */     } finally {
/* 300 */       if (fileStream != null)
/*     */         try {
/* 302 */           fileStream.close();
/* 303 */         } catch (IOException e) {
/* 305 */           LOG.log(Level.WARNING, "Unable to close result set.", e);
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public void complete() {}
/*     */   
/*     */   public void release() {
/* 325 */     if (this.fileOutStream != null) {
/*     */       try {
/* 327 */         this.fileOutStream.close();
/* 328 */       } catch (Exception e) {
/* 330 */         LOG.log(Level.WARNING, "Unable to file output stream.", e);
/*     */       } 
/* 332 */       this.fileOutStream = null;
/*     */     } 
/* 335 */     if (this.file != null) {
/* 336 */       if (!this.file.delete())
/* 337 */         LOG.warning("Unable to delete file " + this.file); 
/* 339 */       this.file = null;
/*     */     } 
/* 342 */     this.stage = StorageStage.Released;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\SegmentedObjectStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */