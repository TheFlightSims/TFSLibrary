/*     */ package org.geotools.data.shapefile.files;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.MappedByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.FileLock;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.nio.channels.WritableByteChannel;
/*     */ 
/*     */ public class FileChannelDecorator extends FileChannel implements ReadableByteChannel {
/*     */   private final FileChannel wrapped;
/*     */   
/*     */   private final ShpFiles shapefileFiles;
/*     */   
/*     */   private final URL url;
/*     */   
/*     */   private final FileReader reader;
/*     */   
/*     */   private final FileWriter writer;
/*     */   
/*     */   private boolean closed;
/*     */   
/*     */   public FileChannelDecorator(FileChannel channel, ShpFiles shapefileFiles, URL url, FileReader requestor) {
/*  52 */     this.wrapped = channel;
/*  53 */     this.shapefileFiles = shapefileFiles;
/*  54 */     this.url = url;
/*  55 */     this.reader = requestor;
/*  56 */     this.writer = null;
/*  57 */     this.closed = false;
/*     */   }
/*     */   
/*     */   public FileChannelDecorator(FileChannel channel, ShpFiles shapefileFiles, URL url, FileWriter requestor) {
/*  62 */     this.wrapped = channel;
/*  63 */     this.shapefileFiles = shapefileFiles;
/*  64 */     this.url = url;
/*  65 */     this.writer = requestor;
/*  66 */     this.reader = null;
/*     */   }
/*     */   
/*     */   public void force(boolean metaData) throws IOException {
/*  70 */     this.wrapped.force(metaData);
/*     */   }
/*     */   
/*     */   public FileLock lock(long position, long size, boolean shared) throws IOException {
/*  75 */     return this.wrapped.lock(position, size, shared);
/*     */   }
/*     */   
/*     */   public MappedByteBuffer map(FileChannel.MapMode mode, long position, long size) throws IOException {
/*  81 */     return this.shapefileFiles.map(this.wrapped, this.url, mode, position, size);
/*     */   }
/*     */   
/*     */   public long position() throws IOException {
/*  85 */     return this.wrapped.position();
/*     */   }
/*     */   
/*     */   public FileChannel position(long newPosition) throws IOException {
/*  89 */     return this.wrapped.position(newPosition);
/*     */   }
/*     */   
/*     */   public int read(ByteBuffer dst, long position) throws IOException {
/*  93 */     return this.wrapped.read(dst, position);
/*     */   }
/*     */   
/*     */   public int read(ByteBuffer dst) throws IOException {
/*  97 */     return this.wrapped.read(dst);
/*     */   }
/*     */   
/*     */   public long read(ByteBuffer[] dsts, int offset, int length) throws IOException {
/* 102 */     return this.wrapped.read(dsts, offset, length);
/*     */   }
/*     */   
/*     */   public long size() throws IOException {
/* 106 */     return this.wrapped.size();
/*     */   }
/*     */   
/*     */   public long transferFrom(ReadableByteChannel src, long position, long count) throws IOException {
/* 111 */     return this.wrapped.transferFrom(src, position, count);
/*     */   }
/*     */   
/*     */   public long transferTo(long position, long count, WritableByteChannel target) throws IOException {
/* 116 */     return this.wrapped.transferTo(position, count, target);
/*     */   }
/*     */   
/*     */   public FileChannel truncate(long size) throws IOException {
/* 120 */     return this.wrapped.truncate(size);
/*     */   }
/*     */   
/*     */   public FileLock tryLock(long position, long size, boolean shared) throws IOException {
/* 125 */     return this.wrapped.tryLock(position, size, shared);
/*     */   }
/*     */   
/*     */   public int write(ByteBuffer src, long position) throws IOException {
/* 129 */     return this.wrapped.write(src, position);
/*     */   }
/*     */   
/*     */   public int write(ByteBuffer src) throws IOException {
/* 133 */     return this.wrapped.write(src);
/*     */   }
/*     */   
/*     */   public long write(ByteBuffer[] srcs, int offset, int length) throws IOException {
/* 138 */     return this.wrapped.write(srcs, offset, length);
/*     */   }
/*     */   
/*     */   protected void implCloseChannel() throws IOException {
/*     */     try {
/* 144 */       this.wrapped.close();
/*     */     } finally {
/* 146 */       if (!this.closed) {
/* 147 */         this.closed = true;
/* 148 */         if (this.reader != null) {
/* 149 */           this.shapefileFiles.unlockRead(this.url, this.reader);
/*     */         } else {
/* 151 */           this.shapefileFiles.unlockWrite(this.url, this.writer);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\files\FileChannelDecorator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */