/*     */ package org.openstreetmap.osmosis.core.store;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.openstreetmap.osmosis.core.OsmosisRuntimeException;
/*     */ import org.openstreetmap.osmosis.core.lifecycle.Completable;
/*     */ 
/*     */ public class RandomAccessObjectStore<T extends Storeable> implements Completable {
/*  28 */   private static final Logger LOG = Logger.getLogger(RandomAccessObjectStore.class.getName());
/*     */   
/*     */   private ObjectSerializationFactory serializationFactory;
/*     */   
/*     */   private StorageStage stage;
/*     */   
/*     */   private String tempFilePrefix;
/*     */   
/*     */   private File tempFile;
/*     */   
/*     */   private File storageFile;
/*     */   
/*     */   private OffsetTrackingOutputStream offsetTrackingStream;
/*     */   
/*     */   private StoreClassRegister storeClassRegister;
/*     */   
/*     */   private ObjectWriter objectWriter;
/*     */   
/*     */   public RandomAccessObjectStore(ObjectSerializationFactory serializationFactory, String tempFilePrefix) {
/*  49 */     this.serializationFactory = serializationFactory;
/*  50 */     this.tempFilePrefix = tempFilePrefix;
/*  52 */     this.storeClassRegister = new DynamicStoreClassRegister();
/*  54 */     this.stage = StorageStage.NotStarted;
/*     */   }
/*     */   
/*     */   public RandomAccessObjectStore(ObjectSerializationFactory serializationFactory, File storageFile) {
/*  67 */     this.serializationFactory = serializationFactory;
/*  68 */     this.storageFile = storageFile;
/*  70 */     this.storeClassRegister = new DynamicStoreClassRegister();
/*  72 */     this.stage = StorageStage.NotStarted;
/*     */   }
/*     */   
/*     */   private void initializeAddStage() {
/*  81 */     if (this.stage.compareTo(StorageStage.Add) > 0)
/*  82 */       throw new OsmosisRuntimeException("Cannot add to storage in stage " + this.stage + "."); 
/*  86 */     if (this.stage.compareTo(StorageStage.Add) < 0) {
/*  87 */       FileOutputStream fileStream = null;
/*     */       try {
/*  89 */         if (this.storageFile == null) {
/*  90 */           this.tempFile = File.createTempFile(this.tempFilePrefix, null);
/*  91 */           this.storageFile = this.tempFile;
/*     */         } 
/*  94 */         fileStream = new FileOutputStream(this.storageFile);
/*  95 */         this.offsetTrackingStream = new OffsetTrackingOutputStream(new BufferedOutputStream(fileStream, 65536));
/*  98 */         fileStream = null;
/* 100 */         this.objectWriter = this.serializationFactory.createObjectWriter(new DataOutputStoreWriter(new DataOutputStream(this.offsetTrackingStream)), this.storeClassRegister);
/* 105 */         this.stage = StorageStage.Add;
/* 107 */       } catch (IOException e) {
/* 108 */         throw new OsmosisRuntimeException("Unable to create object stream writing to file " + this.storageFile + ".", e);
/*     */       } finally {
/* 111 */         if (fileStream != null)
/*     */           try {
/* 113 */             fileStream.close();
/* 114 */           } catch (IOException e) {
/* 116 */             LOG.log(Level.WARNING, "Unable to close file stream.", e);
/*     */           }  
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public long add(T data) {
/* 134 */     initializeAddStage();
/* 136 */     long objectFileOffset = this.offsetTrackingStream.getByteCount();
/* 139 */     this.objectWriter.writeObject((Storeable)data);
/* 141 */     return objectFileOffset;
/*     */   }
/*     */   
/*     */   private void initializeReadingStage() {
/* 151 */     if (this.stage.equals(StorageStage.Reading))
/*     */       return; 
/* 155 */     if (this.stage.equals(StorageStage.Add))
/* 156 */       throw new OsmosisRuntimeException("Cannot begin reading in " + StorageStage.Add + " stage, must call complete first."); 
/* 162 */     if (this.stage.compareTo(StorageStage.Reading) < 0)
/* 163 */       this.stage = StorageStage.Reading; 
/* 167 */     if (this.stage.compareTo(StorageStage.Reading) > 0)
/* 168 */       throw new OsmosisRuntimeException("Cannot read from storage once we've reached stage " + this.stage + "."); 
/*     */   }
/*     */   
/*     */   public RandomAccessObjectStoreReader<T> createReader() {
/* 181 */     initializeReadingStage();
/*     */     try {
/* 186 */       BufferedRandomAccessFileInputStream randomFileReader = new BufferedRandomAccessFileInputStream(this.storageFile);
/* 188 */       return new RandomAccessObjectStoreReader<T>(randomFileReader, this.serializationFactory.createObjectReader(new DataInputStoreReader(new DataInputStream(randomFileReader)), this.storeClassRegister));
/* 195 */     } catch (FileNotFoundException e) {
/* 196 */       throw new OsmosisRuntimeException("Unable to create object stream reading from file " + this.storageFile + ".", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void complete() {
/* 209 */     if (this.stage.compareTo(StorageStage.Reading) != 0) {
/* 212 */       initializeAddStage();
/*     */       try {
/* 215 */         this.offsetTrackingStream.close();
/* 216 */         this.offsetTrackingStream = null;
/* 218 */       } catch (IOException e) {
/* 219 */         throw new OsmosisRuntimeException("Unable to close the file " + this.storageFile + ".");
/*     */       } 
/* 222 */       this.stage = StorageStage.Reading;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void release() {
/* 231 */     if (this.offsetTrackingStream != null) {
/*     */       try {
/* 233 */         this.offsetTrackingStream.close();
/* 234 */       } catch (Exception e) {
/* 236 */         LOG.log(Level.WARNING, "Unable to close offset tracking output stream.", e);
/*     */       } 
/* 238 */       this.offsetTrackingStream = null;
/*     */     } 
/* 241 */     if (this.tempFile != null) {
/* 242 */       if (!this.tempFile.delete())
/* 244 */         LOG.warning("Unable to delete file " + this.tempFile); 
/* 246 */       this.tempFile = null;
/*     */     } 
/* 249 */     this.stage = StorageStage.Released;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\RandomAccessObjectStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */