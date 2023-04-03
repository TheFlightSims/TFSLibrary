/*     */ package org.geotools.data.shapefile.shp;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.IntBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.shapefile.files.FileReader;
/*     */ import org.geotools.data.shapefile.files.ShpFileType;
/*     */ import org.geotools.data.shapefile.files.ShpFiles;
/*     */ import org.geotools.data.shapefile.files.StreamLogging;
/*     */ import org.geotools.resources.NIOUtilities;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class IndexFile implements FileReader {
/*  48 */   private static final Logger LOGGER = Logging.getLogger("org.geotools.data.shapefile");
/*     */   
/*     */   private static final int RECS_IN_BUFFER = 2000;
/*     */   
/*     */   private boolean useMemoryMappedBuffer;
/*     */   
/*     */   private FileChannel channel;
/*     */   
/*     */   private int channelOffset;
/*     */   
/*  56 */   private ByteBuffer buf = null;
/*     */   
/*  57 */   private int lastIndex = -1;
/*     */   
/*     */   private int recOffset;
/*     */   
/*     */   private int recLen;
/*     */   
/*  60 */   private ShapefileHeader header = null;
/*     */   
/*     */   private int[] content;
/*     */   
/*  62 */   private StreamLogging streamLogger = new StreamLogging("IndexFile");
/*     */   
/*     */   private volatile boolean closed = false;
/*     */   
/*     */   public IndexFile(ShpFiles shpFiles, boolean useMemoryMappedBuffer) throws IOException {
/*  76 */     this.useMemoryMappedBuffer = useMemoryMappedBuffer;
/*  77 */     this.streamLogger.open();
/*  78 */     ReadableByteChannel byteChannel = shpFiles.getReadChannel(ShpFileType.SHX, this);
/*     */     try {
/*  81 */       if (byteChannel instanceof FileChannel) {
/*  83 */         this.channel = (FileChannel)byteChannel;
/*  84 */         if (useMemoryMappedBuffer) {
/*  85 */           LOGGER.finest("Memory mapping file...");
/*  86 */           this.buf = this.channel.map(FileChannel.MapMode.READ_ONLY, 0L, this.channel.size());
/*  89 */           this.channelOffset = 0;
/*     */         } else {
/*  91 */           LOGGER.finest("Reading from file...");
/*  92 */           this.buf = NIOUtilities.allocate(16000);
/*  93 */           this.channel.read(this.buf);
/*  94 */           this.buf.flip();
/*  95 */           this.channelOffset = 0;
/*     */         } 
/*  98 */         this.header = new ShapefileHeader();
/*  99 */         this.header.read(this.buf, true);
/*     */       } else {
/* 101 */         LOGGER.finest("Loading all shx...");
/* 102 */         readHeader(byteChannel);
/* 103 */         readRecords(byteChannel);
/* 104 */         byteChannel.close();
/*     */       } 
/* 106 */     } catch (Throwable e) {
/* 107 */       if (byteChannel != null)
/* 108 */         byteChannel.close(); 
/* 110 */       throw (IOException)(new IOException(e.getLocalizedMessage())).initCause(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ShapefileHeader getHeader() {
/* 121 */     return this.header;
/*     */   }
/*     */   
/*     */   private void check() {
/* 125 */     if (this.closed)
/* 126 */       throw new IllegalStateException("Index file has been closed"); 
/*     */   }
/*     */   
/*     */   private void readHeader(ReadableByteChannel channel) throws IOException {
/* 132 */     ByteBuffer buffer = NIOUtilities.allocate(100);
/*     */     try {
/* 134 */       while (buffer.remaining() > 0)
/* 135 */         channel.read(buffer); 
/* 137 */       buffer.flip();
/* 138 */       this.header = new ShapefileHeader();
/* 139 */       this.header.read(buffer, true);
/*     */     } finally {
/* 141 */       NIOUtilities.clean(buffer, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readRecords(ReadableByteChannel channel) throws IOException {
/* 146 */     check();
/* 147 */     int remaining = this.header.getFileLength() * 2 - 100;
/* 148 */     ByteBuffer buffer = NIOUtilities.allocate(remaining);
/*     */     try {
/* 150 */       buffer.order(ByteOrder.BIG_ENDIAN);
/* 151 */       while (buffer.remaining() > 0)
/* 152 */         channel.read(buffer); 
/* 154 */       buffer.flip();
/* 155 */       int records = remaining / 4;
/* 156 */       this.content = new int[records];
/* 157 */       IntBuffer ints = buffer.asIntBuffer();
/* 158 */       ints.get(this.content);
/*     */     } finally {
/* 160 */       NIOUtilities.clean(buffer, false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void readRecord(int index) throws IOException {
/* 165 */     check();
/* 166 */     int pos = 100 + index * 8;
/* 167 */     if (!this.useMemoryMappedBuffer)
/* 170 */       if (pos - this.channelOffset < 0 || this.channelOffset + this.buf.limit() <= pos || this.lastIndex == -1) {
/* 173 */         LOGGER.finest("Filling buffer...");
/* 174 */         this.channelOffset = pos;
/* 175 */         this.channel.position(pos);
/* 176 */         this.buf.clear();
/* 177 */         this.channel.read(this.buf);
/* 178 */         this.buf.flip();
/*     */       }  
/* 182 */     this.buf.position(pos - this.channelOffset);
/* 183 */     this.recOffset = this.buf.getInt();
/* 184 */     this.recLen = this.buf.getInt();
/* 185 */     this.lastIndex = index;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 189 */     this.closed = true;
/* 190 */     if (this.channel != null && this.channel.isOpen()) {
/* 191 */       this.channel.close();
/* 192 */       this.streamLogger.close();
/* 194 */       NIOUtilities.clean(this.buf, this.useMemoryMappedBuffer);
/*     */     } 
/* 196 */     this.buf = null;
/* 197 */     this.content = null;
/* 198 */     this.channel = null;
/*     */   }
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 205 */     close();
/* 206 */     super.finalize();
/*     */   }
/*     */   
/*     */   public int getRecordCount() {
/* 215 */     return (this.header.getFileLength() * 2 - 100) / 8;
/*     */   }
/*     */   
/*     */   public int getOffset(int index) throws IOException {
/* 227 */     int ret = -1;
/* 229 */     if (this.channel != null) {
/* 230 */       if (this.lastIndex != index)
/* 231 */         readRecord(index); 
/* 234 */       ret = this.recOffset;
/*     */     } else {
/* 236 */       ret = this.content[2 * index];
/*     */     } 
/* 239 */     return ret;
/*     */   }
/*     */   
/*     */   public int getOffsetInBytes(int index) throws IOException {
/* 251 */     return getOffset(index) * 2;
/*     */   }
/*     */   
/*     */   public int getContentLength(int index) throws IOException {
/* 263 */     int ret = -1;
/* 265 */     if (this.channel != null) {
/* 266 */       if (this.lastIndex != index)
/* 267 */         readRecord(index); 
/* 270 */       ret = this.recLen;
/*     */     } else {
/* 272 */       ret = this.content[2 * index + 1];
/*     */     } 
/* 275 */     return ret;
/*     */   }
/*     */   
/*     */   public String id() {
/* 279 */     return getClass().getName();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\shp\IndexFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */