/*     */ package org.geotools.data.shapefile.shp;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.channels.FileChannel;
/*     */ import org.geotools.data.shapefile.files.StreamLogging;
/*     */ import org.geotools.resources.NIOUtilities;
/*     */ 
/*     */ public class ShapefileWriter {
/*     */   FileChannel shpChannel;
/*     */   
/*     */   FileChannel shxChannel;
/*     */   
/*     */   ByteBuffer shapeBuffer;
/*     */   
/*     */   ByteBuffer indexBuffer;
/*     */   
/*     */   ShapeHandler handler;
/*     */   
/*     */   ShapeType type;
/*     */   
/*     */   int offset;
/*     */   
/*     */   int lp;
/*     */   
/*     */   int cnt;
/*     */   
/*  66 */   private StreamLogging shpLogger = new StreamLogging("SHP Channel in ShapefileWriter");
/*     */   
/*  68 */   private StreamLogging shxLogger = new StreamLogging("SHX Channel in ShapefileWriter");
/*     */   
/*  70 */   private GeometryFactory gf = new GeometryFactory();
/*     */   
/*     */   public ShapefileWriter(FileChannel shpChannel, FileChannel shxChannel) throws IOException {
/*  79 */     this.shpChannel = shpChannel;
/*  80 */     this.shxChannel = shxChannel;
/*  81 */     this.shpLogger.open();
/*  82 */     this.shxLogger.open();
/*     */   }
/*     */   
/*     */   private void allocateBuffers() {
/* 103 */     this.shapeBuffer = NIOUtilities.allocate(16384);
/* 104 */     this.indexBuffer = NIOUtilities.allocate(100);
/*     */   }
/*     */   
/*     */   private void checkShapeBuffer(int size) {
/* 111 */     if (this.shapeBuffer.capacity() < size) {
/* 112 */       if (this.shapeBuffer != null)
/* 113 */         NIOUtilities.clean(this.shapeBuffer, false); 
/* 114 */       this.shapeBuffer = NIOUtilities.allocate(size);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void drain() throws IOException {
/* 122 */     this.shapeBuffer.flip();
/* 123 */     this.indexBuffer.flip();
/* 124 */     while (this.shapeBuffer.remaining() > 0)
/* 125 */       this.shpChannel.write(this.shapeBuffer); 
/* 126 */     while (this.indexBuffer.remaining() > 0)
/* 127 */       this.shxChannel.write(this.indexBuffer); 
/* 128 */     this.shapeBuffer.flip().limit(this.shapeBuffer.capacity());
/* 129 */     this.indexBuffer.flip().limit(this.indexBuffer.capacity());
/*     */   }
/*     */   
/*     */   private void writeHeaders(GeometryCollection geometries, ShapeType type) throws IOException {
/* 144 */     int fileLength = 100;
/* 146 */     for (int i = geometries.getNumGeometries() - 1; i >= 0; i--) {
/* 148 */       int size = this.handler.getLength(geometries.getGeometryN(i)) + 8;
/* 149 */       fileLength += size;
/*     */     } 
/* 153 */     writeHeaders(geometries.getEnvelopeInternal(), type, geometries.getNumGeometries(), fileLength);
/*     */   }
/*     */   
/*     */   public void writeHeaders(Envelope bounds, ShapeType type, int numberOfGeometries, int fileLength) throws IOException {
/*     */     try {
/* 166 */       this.handler = type.getShapeHandler(this.gf);
/* 167 */     } catch (ShapefileException se) {
/* 168 */       throw new RuntimeException("unexpected Exception", se);
/*     */     } 
/* 170 */     if (this.shapeBuffer == null)
/* 171 */       allocateBuffers(); 
/* 172 */     ShapefileHeader header = new ShapefileHeader();
/* 173 */     header.write(this.shapeBuffer, type, numberOfGeometries, fileLength / 2, bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
/* 176 */     header.write(this.indexBuffer, type, numberOfGeometries, 50 + 4 * numberOfGeometries, bounds.getMinX(), bounds.getMinY(), bounds.getMaxX(), bounds.getMaxY());
/* 180 */     this.offset = 50;
/* 181 */     this.type = type;
/* 182 */     this.cnt = 0;
/* 184 */     this.shpChannel.position(0L);
/* 185 */     this.shxChannel.position(0L);
/* 186 */     drain();
/*     */   }
/*     */   
/*     */   public void skipHeaders() throws IOException {
/* 195 */     if (this.shapeBuffer == null)
/* 196 */       allocateBuffers(); 
/* 197 */     this.shpChannel.position(100L);
/* 198 */     this.shxChannel.position(100L);
/*     */   }
/*     */   
/*     */   public void writeGeometry(Geometry g) throws IOException {
/*     */     int length;
/* 206 */     if (this.shapeBuffer == null)
/* 207 */       throw new IOException("Must write headers first"); 
/* 208 */     this.lp = this.shapeBuffer.position();
/* 210 */     if (g == null) {
/* 211 */       length = writeNullGeometry();
/*     */     } else {
/* 213 */       length = writeNonNullGeometry(g);
/*     */     } 
/* 215 */     assert length * 2 == this.shapeBuffer.position() - this.lp - 8;
/* 217 */     this.lp = this.shapeBuffer.position();
/* 220 */     this.indexBuffer.putInt(this.offset);
/* 221 */     this.indexBuffer.putInt(length);
/* 222 */     this.offset += length + 4;
/* 224 */     drain();
/* 225 */     assert this.shapeBuffer.position() == 0;
/*     */   }
/*     */   
/*     */   private int writeNonNullGeometry(Geometry g) {
/* 229 */     int length = this.handler.getLength(g);
/* 232 */     checkShapeBuffer(length + 8);
/* 234 */     length /= 2;
/* 236 */     this.shapeBuffer.order(ByteOrder.BIG_ENDIAN);
/* 237 */     this.shapeBuffer.putInt(++this.cnt);
/* 238 */     this.shapeBuffer.putInt(length);
/* 239 */     this.shapeBuffer.order(ByteOrder.LITTLE_ENDIAN);
/* 240 */     this.shapeBuffer.putInt(this.type.id);
/* 241 */     this.handler.write(this.shapeBuffer, g);
/* 242 */     return length;
/*     */   }
/*     */   
/*     */   protected int writeNullGeometry() throws IOException {
/* 247 */     int length = 4;
/* 248 */     checkShapeBuffer(8 + length);
/* 250 */     length /= 2;
/* 252 */     this.shapeBuffer.order(ByteOrder.BIG_ENDIAN);
/* 253 */     this.shapeBuffer.putInt(++this.cnt);
/* 254 */     this.shapeBuffer.putInt(length);
/* 255 */     this.shapeBuffer.order(ByteOrder.LITTLE_ENDIAN);
/* 256 */     this.shapeBuffer.putInt(ShapeType.NULL.id);
/* 258 */     return length;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/*     */     try {
/* 266 */       if (this.shpChannel != null && this.shpChannel.isOpen()) {
/* 267 */         this.shpChannel.close();
/* 268 */         this.shpLogger.close();
/*     */       } 
/*     */     } finally {
/* 271 */       if (this.shxChannel != null && this.shxChannel.isOpen()) {
/* 272 */         this.shxChannel.close();
/* 273 */         this.shxLogger.close();
/*     */       } 
/*     */     } 
/* 276 */     this.shpChannel = null;
/* 277 */     this.shxChannel = null;
/* 278 */     this.handler = null;
/* 279 */     if (this.indexBuffer != null)
/* 280 */       NIOUtilities.clean(this.indexBuffer, false); 
/* 281 */     if (this.shapeBuffer != null)
/* 282 */       NIOUtilities.clean(this.shapeBuffer, false); 
/* 283 */     this.indexBuffer = null;
/* 284 */     this.shapeBuffer = null;
/*     */   }
/*     */   
/*     */   public void write(GeometryCollection geometries, ShapeType type) throws IOException, ShapefileException {
/* 293 */     this.handler = type.getShapeHandler(this.gf);
/* 295 */     writeHeaders(geometries, type);
/* 297 */     this.lp = this.shapeBuffer.position();
/* 298 */     for (int i = 0, ii = geometries.getNumGeometries(); i < ii; i++) {
/* 299 */       Geometry g = geometries.getGeometryN(i);
/* 301 */       writeGeometry(g);
/*     */     } 
/* 304 */     close();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\shp\ShapefileWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */