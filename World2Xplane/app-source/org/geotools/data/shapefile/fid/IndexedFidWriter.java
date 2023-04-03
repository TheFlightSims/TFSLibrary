/*     */ package org.geotools.data.shapefile.fid;
/*     */ 
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import org.geotools.data.shapefile.files.FileWriter;
/*     */ import org.geotools.data.shapefile.files.ShpFileType;
/*     */ import org.geotools.data.shapefile.files.ShpFiles;
/*     */ import org.geotools.data.shapefile.files.StorageFile;
/*     */ import org.geotools.data.shapefile.files.StreamLogging;
/*     */ import org.geotools.resources.NIOUtilities;
/*     */ 
/*     */ public class IndexedFidWriter implements FileWriter {
/*     */   public static final int HEADER_SIZE = 13;
/*     */   
/*     */   public static final int RECORD_SIZE = 12;
/*     */   
/*     */   private FileChannel channel;
/*     */   
/*     */   private ByteBuffer writeBuffer;
/*     */   
/*     */   private IndexedFidReader reader;
/*     */   
/*     */   long fidIndex;
/*     */   
/*     */   private int recordIndex;
/*     */   
/*     */   private boolean closed;
/*     */   
/*     */   private long current;
/*     */   
/*     */   private long position;
/*     */   
/*     */   private int removes;
/*     */   
/*  55 */   StreamLogging streamLogger = new StreamLogging("IndexedFidReader");
/*     */   
/*     */   private StorageFile storageFile;
/*     */   
/*     */   public IndexedFidWriter(ShpFiles shpFiles) throws IOException {
/*  66 */     this.storageFile = shpFiles.getStorageFile(ShpFileType.FIX);
/*  67 */     init(shpFiles, this.storageFile);
/*     */   }
/*     */   
/*     */   public IndexedFidWriter(ShpFiles shpFiles, StorageFile storageFile) throws IOException {
/*  82 */     init(shpFiles, storageFile);
/*     */   }
/*     */   
/*     */   private void init(ShpFiles shpFiles, StorageFile storageFile) throws IOException {
/*  86 */     if (!shpFiles.isLocal())
/*  87 */       throw new IllegalArgumentException("Currently only local files are supported for writing"); 
/*     */     try {
/*  93 */       this.reader = new IndexedFidReader(shpFiles);
/*  94 */     } catch (FileNotFoundException e) {
/*  95 */       this.reader = new IndexedFidReader(shpFiles, storageFile.getWriteChannel());
/*     */     } 
/*  98 */     this.channel = storageFile.getWriteChannel();
/*  99 */     this.streamLogger.open();
/* 100 */     allocateBuffers();
/* 101 */     this.removes = this.reader.getRemoves();
/* 102 */     this.writeBuffer.position(13);
/* 103 */     this.closed = false;
/* 104 */     this.position = 0L;
/* 105 */     this.current = -1L;
/* 106 */     this.recordIndex = 0;
/* 107 */     this.fidIndex = 0L;
/*     */   }
/*     */   
/*     */   private void allocateBuffers() {
/* 117 */     this.writeBuffer = NIOUtilities.allocate(12301);
/*     */   }
/*     */   
/*     */   private void drain() throws IOException {
/* 126 */     this.writeBuffer.flip();
/* 128 */     int written = 0;
/* 130 */     while (this.writeBuffer.remaining() > 0)
/* 131 */       written += this.channel.write(this.writeBuffer, this.position); 
/* 133 */     this.position += written;
/* 135 */     this.writeBuffer.flip().limit(this.writeBuffer.capacity());
/*     */   }
/*     */   
/*     */   private void writeHeader() throws IOException {
/* 139 */     ByteBuffer buffer = NIOUtilities.allocate(13);
/*     */     try {
/* 142 */       buffer.put((byte)1);
/* 144 */       buffer.putLong(this.recordIndex);
/* 145 */       buffer.putInt(this.removes);
/* 146 */       buffer.flip();
/* 147 */       this.channel.write(buffer, 0L);
/*     */     } finally {
/* 149 */       NIOUtilities.clean(buffer, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 154 */     return this.reader.hasNext();
/*     */   }
/*     */   
/*     */   public long next() throws IOException {
/* 159 */     if (this.current != -1L)
/* 160 */       write(); 
/* 162 */     if (this.reader.hasNext()) {
/* 163 */       this.reader.next();
/* 164 */       this.fidIndex = this.reader.getCurrentFIDIndex();
/*     */     } else {
/* 166 */       this.fidIndex++;
/*     */     } 
/* 169 */     this.current = this.fidIndex;
/* 171 */     return this.fidIndex;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 175 */     if (this.closed)
/*     */       return; 
/*     */     try {
/* 181 */       finishLastWrite();
/*     */     } finally {
/*     */       try {
/* 184 */         this.reader.close();
/*     */       } finally {
/* 186 */         closeWriterChannels();
/*     */       } 
/* 188 */       if (this.storageFile != null)
/* 189 */         this.storageFile.replaceOriginal(); 
/*     */     } 
/* 193 */     this.closed = true;
/*     */   }
/*     */   
/*     */   private void closeWriterChannels() throws IOException {
/* 197 */     if (this.channel.isOpen())
/* 198 */       this.channel.close(); 
/* 199 */     this.streamLogger.close();
/* 200 */     if (this.writeBuffer != null) {
/* 201 */       NIOUtilities.clean(this.writeBuffer, false);
/* 202 */       this.writeBuffer = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void finishLastWrite() throws IOException {
/* 208 */     while (hasNext())
/* 209 */       next(); 
/* 212 */     if (this.current != -1L)
/* 213 */       write(); 
/* 216 */     drain();
/* 217 */     writeHeader();
/*     */   }
/*     */   
/*     */   public void remove() throws IOException {
/* 233 */     if (this.current == -1L)
/* 234 */       throw new IOException("Current fid index is null, next must be called before remove"); 
/* 235 */     if (hasNext()) {
/* 236 */       this.removes++;
/* 237 */       this.current = -1L;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void write() throws IOException {
/* 252 */     if (this.current == -1L)
/* 253 */       throw new IOException("Current fid index is null, next must be called before write()"); 
/* 255 */     if (this.writeBuffer == null)
/* 256 */       allocateBuffers(); 
/* 259 */     if (this.writeBuffer.remaining() < 12)
/* 260 */       drain(); 
/* 263 */     this.writeBuffer.putLong(this.current);
/* 264 */     this.writeBuffer.putInt(this.recordIndex);
/* 266 */     this.recordIndex++;
/* 267 */     this.current = -1L;
/*     */   }
/*     */   
/*     */   public boolean isClosed() {
/* 271 */     return this.closed;
/*     */   }
/*     */   
/*     */   public String id() {
/* 275 */     return getClass().getName();
/*     */   }
/*     */   
/* 278 */   public static final IndexedFidWriter EMPTY_WRITER = new IndexedFidWriter() {
/*     */       public void close() throws IOException {}
/*     */       
/*     */       public boolean hasNext() throws IOException {
/* 284 */         return false;
/*     */       }
/*     */       
/*     */       public boolean isClosed() {
/* 288 */         return false;
/*     */       }
/*     */       
/*     */       public void write() throws IOException {}
/*     */       
/*     */       public long next() throws IOException {
/* 295 */         return 0L;
/*     */       }
/*     */       
/*     */       public void remove() throws IOException {}
/*     */     };
/*     */   
/*     */   private IndexedFidWriter() {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\fid\IndexedFidWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */