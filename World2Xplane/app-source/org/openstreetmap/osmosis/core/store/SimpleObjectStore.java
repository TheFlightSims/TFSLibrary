/*     */ package org.openstreetmap.osmosis.core.store;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.BufferedOutputStream;
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
/*     */ public class SimpleObjectStore<T extends Storeable> implements Completable {
/*  32 */   private static final Logger LOG = Logger.getLogger(SimpleObjectStore.class.getName());
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
/*     */   private StoreClassRegister storeClassRegister;
/*     */   
/*     */   private ObjectWriter objectWriter;
/*     */   
/*     */   private boolean useCompression;
/*     */   
/*     */   public SimpleObjectStore(ObjectSerializationFactory serializationFactory, String storageFilePrefix, boolean useCompression) {
/*  57 */     this.serializationFactory = serializationFactory;
/*  58 */     this.storageFilePrefix = storageFilePrefix;
/*  59 */     this.useCompression = useCompression;
/*  61 */     this.storeClassRegister = new DynamicStoreClassRegister();
/*  63 */     this.stage = StorageStage.NotStarted;
/*     */   }
/*     */   
/*     */   public void add(T data) {
/*  75 */     if (this.stage.compareTo(StorageStage.Add) > 0)
/*  76 */       throw new OsmosisRuntimeException("Cannot add to storage in stage " + this.stage + "."); 
/*  80 */     if (this.stage.compareTo(StorageStage.Add) < 0)
/*     */       try {
/*  82 */         this.file = File.createTempFile(this.storageFilePrefix, null);
/*  84 */         this.fileOutStream = new FileOutputStream(this.file);
/*  86 */         if (this.useCompression) {
/*  87 */           this.dataOutStream = new DataOutputStream(new BufferedOutputStream(new GZIPOutputStream(this.fileOutStream), 65536));
/*     */         } else {
/*  90 */           this.dataOutStream = new DataOutputStream(new BufferedOutputStream(this.fileOutStream, 65536));
/*     */         } 
/*  94 */         this.objectWriter = this.serializationFactory.createObjectWriter(new DataOutputStoreWriter(this.dataOutStream), this.storeClassRegister);
/*  97 */         this.stage = StorageStage.Add;
/*  99 */       } catch (IOException e) {
/* 100 */         throw new OsmosisRuntimeException("Unable to create object stream writing to temporary file " + this.file + ".", e);
/*     */       }  
/* 106 */     this.objectWriter.writeObject((Storeable)data);
/*     */   }
/*     */   
/*     */   private boolean initializeIteratingStage() {
/* 119 */     if (this.stage.compareTo(StorageStage.Released) >= 0)
/* 120 */       throw new OsmosisRuntimeException("Cannot iterate over storage in stage " + this.stage + "."); 
/* 124 */     if (this.stage.compareTo(StorageStage.NotStarted) <= 0)
/* 125 */       return false; 
/* 129 */     if (this.stage.compareTo(StorageStage.Add) == 0) {
/*     */       try {
/* 131 */         this.dataOutStream.close();
/* 132 */         this.fileOutStream.close();
/* 134 */       } catch (IOException e) {
/* 135 */         throw new OsmosisRuntimeException("Unable to close output stream.", e);
/*     */       } finally {
/* 137 */         this.dataOutStream = null;
/* 138 */         this.fileOutStream = null;
/*     */       } 
/* 141 */       this.stage = StorageStage.Reading;
/*     */     } 
/* 145 */     return true;
/*     */   }
/*     */   
/*     */   public ReleasableIterator<T> iterate() {
/* 156 */     FileInputStream fileStream = null;
/*     */     try {
/*     */       DataInputStream dataInStream;
/* 161 */       if (!initializeIteratingStage())
/* 162 */         return new EmptyIterator(); 
/*     */       try {
/* 167 */         fileStream = new FileInputStream(this.file);
/* 168 */       } catch (IOException e) {
/* 169 */         throw new OsmosisRuntimeException("Unable to open file for reading.", e);
/*     */       } 
/*     */       try {
/* 174 */         if (this.useCompression) {
/* 175 */           dataInStream = new DataInputStream(new BufferedInputStream((InputStream)new MultiMemberGZIPInputStream(fileStream), 65536));
/*     */         } else {
/* 178 */           dataInStream = new DataInputStream(new BufferedInputStream(fileStream, 65536));
/*     */         } 
/* 182 */       } catch (IOException e) {
/* 183 */         throw new OsmosisRuntimeException("Unable to open object stream.", e);
/*     */       } 
/* 188 */       fileStream = null;
/* 190 */       return new ObjectStreamIterator(dataInStream, this.serializationFactory.createObjectReader(new DataInputStoreReader(dataInStream), this.storeClassRegister));
/*     */     } finally {
/* 196 */       if (fileStream != null)
/*     */         try {
/* 198 */           fileStream.close();
/* 199 */         } catch (IOException e) {
/* 201 */           LOG.log(Level.WARNING, "Unable to close file input stream.", e);
/*     */         }  
/*     */     } 
/*     */   }
/*     */   
/*     */   public void complete() {}
/*     */   
/*     */   public void release() {
/* 221 */     if (this.fileOutStream != null) {
/*     */       try {
/* 223 */         this.fileOutStream.close();
/* 224 */       } catch (IOException e) {
/* 226 */         LOG.log(Level.WARNING, "Unable to close file output stream.", e);
/*     */       } 
/* 228 */       this.fileOutStream = null;
/*     */     } 
/* 231 */     if (this.file != null) {
/* 232 */       if (!this.file.delete())
/* 233 */         LOG.warning("Unable to delete file " + this.file); 
/* 235 */       this.file = null;
/*     */     } 
/* 238 */     this.stage = StorageStage.Released;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\openstreetmap\osmosis\core\store\SimpleObjectStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */