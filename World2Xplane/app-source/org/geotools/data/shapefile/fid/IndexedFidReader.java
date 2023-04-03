/*     */ package org.geotools.data.shapefile.fid;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.DataUtilities;
/*     */ import org.geotools.data.FIDReader;
/*     */ import org.geotools.data.shapefile.files.FileReader;
/*     */ import org.geotools.data.shapefile.files.ShpFileType;
/*     */ import org.geotools.data.shapefile.files.ShpFiles;
/*     */ import org.geotools.data.shapefile.files.StreamLogging;
/*     */ import org.geotools.data.shapefile.shp.ShapefileReader;
/*     */ import org.geotools.resources.NIOUtilities;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class IndexedFidReader implements FIDReader, FileReader {
/*  47 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.data.shapefile");
/*     */   
/*     */   private ReadableByteChannel readChannel;
/*     */   
/*     */   private ByteBuffer buffer;
/*     */   
/*     */   private long count;
/*     */   
/*     */   private String typeName;
/*     */   
/*     */   private boolean done;
/*     */   
/*     */   private int removes;
/*     */   
/*  55 */   private int currentShxIndex = -1;
/*     */   
/*     */   private long currentId;
/*     */   
/*     */   private StringBuilder fidBuilder;
/*     */   
/*  65 */   private long bufferStart = Long.MIN_VALUE;
/*     */   
/*  66 */   StreamLogging streamLogger = new StreamLogging("IndexedFidReader");
/*     */   
/*     */   public IndexedFidReader(ShpFiles shpFiles) throws IOException {
/*  69 */     init(shpFiles, shpFiles.getReadChannel(ShpFileType.FIX, this));
/*     */   }
/*     */   
/*     */   public IndexedFidReader(ShpFiles shpFiles, ReadableByteChannel in) throws IOException {
/*  73 */     init(shpFiles, in);
/*     */   }
/*     */   
/*     */   private void init(ShpFiles shpFiles, ReadableByteChannel in) throws IOException {
/*  77 */     this.typeName = shpFiles.getTypeName() + ".";
/*  78 */     this.fidBuilder = new StringBuilder(this.typeName);
/*  79 */     this.readChannel = in;
/*  80 */     this.streamLogger.open();
/*  81 */     getHeader(shpFiles);
/*  83 */     this.buffer = NIOUtilities.allocate(12288);
/*  84 */     this.buffer.position(this.buffer.limit());
/*     */   }
/*     */   
/*     */   private void getHeader(ShpFiles shpFiles) throws IOException {
/*  88 */     ByteBuffer buffer = NIOUtilities.allocate(13);
/*     */     try {
/*  90 */       ShapefileReader.fill(buffer, this.readChannel);
/*  92 */       if (buffer.position() == 0) {
/*  93 */         this.done = true;
/*  94 */         this.count = 0L;
/*     */         return;
/*     */       } 
/*  99 */       buffer.position(0);
/* 101 */       byte version = buffer.get();
/* 103 */       if (version != 1)
/* 104 */         throw new IOException("File is not of a compatible version for this reader or file is corrupt."); 
/* 108 */       this.count = buffer.getLong();
/* 109 */       this.removes = buffer.getInt();
/* 110 */       if (this.removes > getCount() / 2L) {
/* 111 */         URL url = shpFiles.acquireRead(ShpFileType.FIX, this);
/*     */         try {
/* 113 */           DataUtilities.urlToFile(url).deleteOnExit();
/*     */         } finally {
/* 115 */           shpFiles.unlockRead(url, this);
/*     */         } 
/*     */       } 
/*     */     } finally {
/* 119 */       NIOUtilities.clean(buffer, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   public long getCount() {
/* 129 */     return this.count;
/*     */   }
/*     */   
/*     */   public int getRemoves() {
/* 140 */     return this.removes;
/*     */   }
/*     */   
/*     */   public long findFid(String fid) throws IOException {
/*     */     try {
/* 159 */       int idx = this.typeName.length();
/* 160 */       long desired = -1L;
/* 161 */       if (fid.startsWith(this.typeName)) {
/*     */         try {
/* 163 */           desired = Long.parseLong(fid.substring(idx), 10);
/* 164 */         } catch (NumberFormatException e) {
/* 165 */           return -1L;
/*     */         } 
/*     */       } else {
/* 168 */         return -1L;
/*     */       } 
/* 171 */       if (desired < 0L)
/* 172 */         return -1L; 
/* 175 */       if (desired < this.count)
/* 176 */         return search(desired, -1L, this.count, desired - 1L); 
/* 178 */       return search(desired, -1L, this.count, this.count - 1L);
/* 180 */     } catch (NumberFormatException e) {
/* 181 */       LOGGER.warning("Fid is not recognized as a fid for this shapefile: " + this.typeName);
/* 184 */       return -1L;
/*     */     } 
/*     */   }
/*     */   
/*     */   long search(long desired, long minRec, long maxRec, long predictedRec) throws IOException {
/* 208 */     if (minRec == maxRec)
/* 209 */       return -1L; 
/* 212 */     goTo(predictedRec);
/* 213 */     hasNext();
/* 214 */     next();
/* 215 */     this.buffer.limit(this.buffer.capacity());
/* 216 */     if (this.currentId == desired)
/* 217 */       return this.currentShxIndex; 
/* 220 */     if (maxRec - minRec < 10L)
/* 221 */       return search(desired, minRec + 1L, maxRec, minRec + 1L); 
/* 223 */     long newOffset = desired - this.currentId;
/* 224 */     long newPrediction = predictedRec + newOffset;
/* 226 */     if (newPrediction <= minRec)
/* 227 */       newPrediction = minRec + (predictedRec - minRec) / 2L; 
/* 230 */     if (newPrediction >= maxRec)
/* 231 */       newPrediction = predictedRec + (maxRec - predictedRec) / 2L; 
/* 234 */     if (newPrediction == predictedRec)
/* 235 */       return -1L; 
/* 238 */     if (newPrediction < predictedRec)
/* 239 */       return search(desired, minRec, predictedRec, newPrediction); 
/* 241 */     return search(desired, predictedRec, maxRec, newPrediction);
/*     */   }
/*     */   
/*     */   public void goTo(long recno) throws IOException {
/* 247 */     assert recno < this.count;
/* 248 */     if (this.readChannel instanceof FileChannel) {
/* 249 */       long newPosition = 13L + recno * 12L;
/* 251 */       if (newPosition >= this.bufferStart + this.buffer.limit() || newPosition < this.bufferStart) {
/* 253 */         FileChannel fc = (FileChannel)this.readChannel;
/* 254 */         fc.position(newPosition);
/* 255 */         this.buffer.limit(this.buffer.capacity());
/* 256 */         this.buffer.position(this.buffer.limit());
/*     */       } else {
/* 258 */         this.buffer.position((int)(newPosition - this.bufferStart));
/*     */       } 
/*     */     } else {
/* 261 */       throw new IOException("Read Channel is not a File Channel so this is not possible.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*     */     try {
/* 268 */       if (this.buffer != null)
/* 269 */         NIOUtilities.clean(this.buffer, false); 
/*     */     } finally {
/* 272 */       this.buffer = null;
/* 273 */       this.readChannel.close();
/* 274 */       this.streamLogger.close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 279 */     if (this.done)
/* 280 */       return false; 
/* 283 */     if (this.buffer.position() == this.buffer.limit()) {
/* 284 */       this.buffer.position(0);
/* 286 */       FileChannel fc = (FileChannel)this.readChannel;
/* 287 */       this.bufferStart = fc.position();
/* 288 */       int read = ShapefileReader.fill(this.buffer, this.readChannel);
/* 290 */       if (read != 0)
/* 291 */         this.buffer.position(0); 
/*     */     } 
/* 295 */     return (this.buffer.remaining() != 0);
/*     */   }
/*     */   
/*     */   public String next() throws IOException {
/* 299 */     if (!hasNext())
/* 300 */       throw new NoSuchElementException("FID index could not be read; the index may be invalid"); 
/* 304 */     this.currentId = this.buffer.getLong();
/* 305 */     this.currentShxIndex = this.buffer.getInt();
/* 307 */     this.fidBuilder.setLength(this.typeName.length());
/* 308 */     this.fidBuilder.append(this.currentId);
/* 309 */     return this.fidBuilder.toString();
/*     */   }
/*     */   
/*     */   public int currentSHXIndex() {
/* 323 */     if (this.currentShxIndex == -1)
/* 324 */       throw new NoSuchElementException("Next must be called before there exists a current element."); 
/* 328 */     return this.currentShxIndex;
/*     */   }
/*     */   
/*     */   public long getCurrentFIDIndex() {
/* 337 */     return this.currentId;
/*     */   }
/*     */   
/*     */   public String id() {
/* 341 */     return getClass().getName();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\fid\IndexedFidReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */