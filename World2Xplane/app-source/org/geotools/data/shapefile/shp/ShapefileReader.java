/*     */ package org.geotools.data.shapefile.shp;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.ByteOrder;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.ReadableByteChannel;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.data.DataSourceException;
/*     */ import org.geotools.data.shapefile.files.FileReader;
/*     */ import org.geotools.data.shapefile.files.ShpFileType;
/*     */ import org.geotools.data.shapefile.files.ShpFiles;
/*     */ import org.geotools.data.shapefile.files.StreamLogging;
/*     */ import org.geotools.renderer.ScreenMap;
/*     */ import org.geotools.resources.NIOUtilities;
/*     */ import org.geotools.util.logging.Logging;
/*     */ 
/*     */ public class ShapefileReader implements FileReader {
/*  69 */   private static final Logger LOGGER = Logging.getLogger(ShapefileReader.class);
/*     */   
/*     */   private static final int UNKNOWN = -2147483648;
/*     */   
/*     */   private ShapeHandler handler;
/*     */   
/*     */   private ShapefileHeader header;
/*     */   
/*     */   private ReadableByteChannel channel;
/*     */   
/*     */   ByteBuffer buffer;
/*     */   
/*     */   public final class Record {
/*     */     int length;
/*     */     
/*  84 */     public int number = 0;
/*     */     
/*     */     int offset;
/*     */     
/*  88 */     int start = 0;
/*     */     
/*     */     public double minX;
/*     */     
/*     */     public double minY;
/*     */     
/*     */     public double maxX;
/*     */     
/*     */     public double maxY;
/*     */     
/*     */     public ShapeType type;
/*     */     
/* 104 */     int end = 0;
/*     */     
/* 106 */     Object shape = null;
/*     */     
/*     */     public Object shape() {
/* 110 */       if (this.shape == null) {
/* 111 */         ShapefileReader.this.buffer.position(this.start);
/* 112 */         ShapefileReader.this.buffer.order(ByteOrder.LITTLE_ENDIAN);
/* 113 */         if (this.type == ShapeType.NULL) {
/* 114 */           this.shape = null;
/*     */         } else {
/* 116 */           this.shape = ShapefileReader.this.handler.read(ShapefileReader.this.buffer, this.type, ShapefileReader.this.flatGeometry);
/*     */         } 
/*     */       } 
/* 119 */       return this.shape;
/*     */     }
/*     */     
/*     */     public int offset() {
/* 123 */       return this.offset;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 128 */       return "Record " + this.number + " length " + this.length + " bounds " + this.minX + "," + this.minY + " " + this.maxX + "," + this.maxY;
/*     */     }
/*     */     
/*     */     public Envelope envelope() {
/* 133 */       return new Envelope(this.minX, this.maxX, this.minY, this.maxY);
/*     */     }
/*     */     
/*     */     public Object getSimplifiedShape() {
/* 137 */       CoordinateSequenceFactory csf = ShapefileReader.this.geometryFactory.getCoordinateSequenceFactory();
/* 138 */       if (this.type.isPointType()) {
/* 139 */         CoordinateSequence cs = csf.create(1, 2);
/* 140 */         cs.setOrdinate(0, 0, (this.minX + this.maxX) / 2.0D);
/* 141 */         cs.setOrdinate(0, 1, (this.minY + this.maxY) / 2.0D);
/* 142 */         return ShapefileReader.this.geometryFactory.createMultiPoint(new Point[] { ShapefileReader.access$200(this.this$0).createPoint(cs) });
/*     */       } 
/* 143 */       if (this.type.isLineType()) {
/* 144 */         CoordinateSequence cs = csf.create(2, 2);
/* 145 */         cs.setOrdinate(0, 0, this.minX);
/* 146 */         cs.setOrdinate(0, 1, this.minY);
/* 147 */         cs.setOrdinate(1, 0, this.maxX);
/* 148 */         cs.setOrdinate(1, 1, this.maxY);
/* 149 */         return ShapefileReader.this.geometryFactory.createMultiLineString(new LineString[] { ShapefileReader.access$200(this.this$0).createLineString(cs) });
/*     */       } 
/* 150 */       if (this.type.isPolygonType()) {
/* 151 */         CoordinateSequence cs = csf.create(5, 2);
/* 152 */         cs.setOrdinate(0, 0, this.minX);
/* 153 */         cs.setOrdinate(0, 1, this.minY);
/* 154 */         cs.setOrdinate(1, 0, this.minX);
/* 155 */         cs.setOrdinate(1, 1, this.maxY);
/* 156 */         cs.setOrdinate(2, 0, this.maxX);
/* 157 */         cs.setOrdinate(2, 1, this.maxY);
/* 158 */         cs.setOrdinate(3, 0, this.maxX);
/* 159 */         cs.setOrdinate(3, 1, this.minY);
/* 160 */         cs.setOrdinate(4, 0, this.minX);
/* 161 */         cs.setOrdinate(4, 1, this.minY);
/* 162 */         LinearRing ring = ShapefileReader.this.geometryFactory.createLinearRing(cs);
/* 163 */         return ShapefileReader.this.geometryFactory.createMultiPolygon(new Polygon[] { ShapefileReader.access$200(this.this$0).createPolygon(ring, null) });
/*     */       } 
/* 165 */       return shape();
/*     */     }
/*     */     
/*     */     public Object getSimplifiedShape(ScreenMap sm) {
/*     */       Class<MultiPolygon> clazz;
/* 170 */       if (this.type.isPointType())
/* 171 */         return shape(); 
/* 174 */       Class<Geometry> geomType = Geometry.class;
/* 175 */       if (this.type.isLineType()) {
/* 176 */         Class<MultiLineString> clazz1 = MultiLineString.class;
/* 177 */       } else if (this.type.isMultiPointType()) {
/* 178 */         Class<MultiPoint> clazz1 = MultiPoint.class;
/* 179 */       } else if (this.type.isPolygonType()) {
/* 180 */         clazz = MultiPolygon.class;
/*     */       } 
/* 182 */       return sm.getSimplifiedShape(this.minX, this.minY, this.maxX, this.maxY, ShapefileReader.this.geometryFactory, clazz);
/*     */     }
/*     */   }
/*     */   
/* 194 */   private ShapeType fileShapeType = ShapeType.UNDEFINED;
/*     */   
/*     */   private ByteBuffer headerTransfer;
/*     */   
/* 198 */   private final Record record = new Record();
/*     */   
/*     */   private final boolean randomAccessEnabled;
/*     */   
/*     */   private boolean useMemoryMappedBuffer;
/*     */   
/* 204 */   private long currentOffset = 0L;
/*     */   
/* 206 */   private int currentShape = 0;
/*     */   
/*     */   private IndexFile shxReader;
/*     */   
/* 210 */   private StreamLogging streamLogger = new StreamLogging("Shapefile Reader");
/*     */   
/*     */   private GeometryFactory geometryFactory;
/*     */   
/*     */   private boolean flatGeometry;
/*     */   
/*     */   public ShapefileReader(ShpFiles shapefileFiles, boolean strict, boolean useMemoryMapped, GeometryFactory gf) throws IOException, ShapefileException {
/* 231 */     this(shapefileFiles, strict, useMemoryMapped, gf, false);
/*     */   }
/*     */   
/*     */   public ShapefileReader(ShpFiles shapefileFiles, boolean strict, boolean useMemoryMapped, GeometryFactory gf, boolean onlyRandomAccess) throws IOException, ShapefileException {
/* 253 */     this.channel = shapefileFiles.getReadChannel(ShpFileType.SHP, this);
/* 254 */     this.useMemoryMappedBuffer = useMemoryMapped;
/* 255 */     this.streamLogger.open();
/* 256 */     this.randomAccessEnabled = this.channel instanceof FileChannel;
/* 257 */     if (!onlyRandomAccess)
/*     */       try {
/* 259 */         this.shxReader = new IndexFile(shapefileFiles, this.useMemoryMappedBuffer);
/* 260 */       } catch (Exception e) {
/* 261 */         LOGGER.log(Level.WARNING, "Could not open the .shx file, continuing assuming the .shp file is not sparse", e);
/* 263 */         this.currentShape = Integer.MIN_VALUE;
/*     */       }  
/* 266 */     init(strict, gf);
/*     */   }
/*     */   
/*     */   public void disableShxUsage() throws IOException {
/* 276 */     if (this.shxReader != null) {
/* 277 */       this.shxReader.close();
/* 278 */       this.shxReader = null;
/*     */     } 
/* 280 */     this.currentShape = Integer.MIN_VALUE;
/*     */   }
/*     */   
/*     */   private ByteBuffer ensureCapacity(ByteBuffer buffer, int size, boolean useMemoryMappedBuffer) {
/* 292 */     if (buffer.isReadOnly() || useMemoryMappedBuffer)
/* 293 */       return buffer; 
/* 296 */     int limit = buffer.limit();
/* 297 */     while (limit < size)
/* 298 */       limit *= 2; 
/* 300 */     if (limit != buffer.limit())
/* 302 */       buffer = NIOUtilities.allocate(limit); 
/* 304 */     return buffer;
/*     */   }
/*     */   
/*     */   public static int fill(ByteBuffer buffer, ReadableByteChannel channel) throws IOException {
/* 310 */     int r = buffer.remaining();
/* 313 */     while (buffer.remaining() > 0 && r != -1)
/* 314 */       r = channel.read(buffer); 
/* 316 */     buffer.limit(buffer.position());
/* 317 */     return r;
/*     */   }
/*     */   
/*     */   private void init(boolean strict, GeometryFactory gf) throws IOException, ShapefileException {
/* 321 */     this.geometryFactory = gf;
/* 323 */     if (this.channel instanceof FileChannel && this.useMemoryMappedBuffer) {
/* 324 */       FileChannel fc = (FileChannel)this.channel;
/* 325 */       this.buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0L, fc.size());
/* 326 */       this.buffer.position(0);
/* 327 */       this.currentOffset = 0L;
/*     */     } else {
/* 330 */       this.useMemoryMappedBuffer = false;
/* 332 */       this.buffer = NIOUtilities.allocate(1024);
/* 333 */       fill(this.buffer, this.channel);
/* 334 */       this.buffer.flip();
/* 335 */       this.currentOffset = 0L;
/*     */     } 
/* 337 */     this.header = new ShapefileHeader();
/* 338 */     this.header.read(this.buffer, strict);
/* 339 */     this.fileShapeType = this.header.getShapeType();
/* 340 */     this.handler = this.fileShapeType.getShapeHandler(gf);
/* 341 */     if (this.handler == null)
/* 342 */       throw new IOException("Unsuported shape type:" + this.fileShapeType); 
/* 345 */     this.headerTransfer = ByteBuffer.allocate(8);
/* 346 */     this.headerTransfer.order(ByteOrder.BIG_ENDIAN);
/* 349 */     this.record.end = toFileOffset(this.buffer.position());
/*     */   }
/*     */   
/*     */   public ShapefileHeader getHeader() {
/* 358 */     return this.header;
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 371 */     if (this.channel == null)
/*     */       return; 
/*     */     try {
/* 374 */       if (this.channel.isOpen()) {
/* 375 */         this.channel.close();
/* 376 */         this.streamLogger.close();
/*     */       } 
/* 378 */       NIOUtilities.clean(this.buffer, this.useMemoryMappedBuffer);
/*     */     } finally {
/* 380 */       if (this.shxReader != null)
/* 381 */         this.shxReader.close(); 
/*     */     } 
/* 383 */     this.shxReader = null;
/* 384 */     this.channel = null;
/* 385 */     this.header = null;
/*     */   }
/*     */   
/*     */   public boolean supportsRandomAccess() {
/* 389 */     return this.randomAccessEnabled;
/*     */   }
/*     */   
/*     */   public boolean hasNext() throws IOException {
/* 402 */     return hasNext(true);
/*     */   }
/*     */   
/*     */   private boolean hasNext(boolean checkRecno) throws IOException {
/* 419 */     if (this.currentShape > Integer.MIN_VALUE && this.currentShape > this.shxReader.getRecordCount() - 1)
/* 420 */       return false; 
/* 423 */     positionBufferForOffset(this.buffer, getNextOffset());
/* 426 */     if (this.buffer.remaining() < 8)
/* 427 */       return false; 
/* 431 */     int position = this.buffer.position();
/* 434 */     boolean hasNext = true;
/* 435 */     if (checkRecno) {
/* 437 */       this.buffer.order(ByteOrder.BIG_ENDIAN);
/* 438 */       int declaredRecNo = this.buffer.getInt();
/* 439 */       hasNext = (declaredRecNo == this.record.number + 1);
/*     */     } 
/* 443 */     this.buffer.position(position);
/* 445 */     return hasNext;
/*     */   }
/*     */   
/*     */   private int getNextOffset() throws IOException {
/* 449 */     if (this.currentShape >= 0)
/* 450 */       return this.shxReader.getOffsetInBytes(this.currentShape); 
/* 452 */     return this.record.end;
/*     */   }
/*     */   
/*     */   public int transferTo(ShapefileWriter writer, int recordNum, double[] bounds) throws IOException {
/* 468 */     this.buffer.position(toBufferOffset(this.record.end));
/* 469 */     this.buffer.order(ByteOrder.BIG_ENDIAN);
/* 471 */     this.buffer.getInt();
/* 472 */     int rl = this.buffer.getInt();
/* 473 */     int mark = this.buffer.position();
/* 474 */     int len = rl * 2;
/* 476 */     this.buffer.order(ByteOrder.LITTLE_ENDIAN);
/* 477 */     ShapeType recordType = ShapeType.forID(this.buffer.getInt());
/* 479 */     if (recordType.isMultiPoint()) {
/* 480 */       for (int i = 0; i < 4; i++)
/* 481 */         bounds[i] = this.buffer.getDouble(); 
/* 483 */     } else if (recordType != ShapeType.NULL) {
/* 484 */       bounds[1] = this.buffer.getDouble();
/* 484 */       bounds[0] = this.buffer.getDouble();
/* 485 */       bounds[3] = this.buffer.getDouble();
/* 485 */       bounds[2] = this.buffer.getDouble();
/*     */     } 
/* 489 */     this.headerTransfer.position(0);
/* 490 */     this.headerTransfer.putInt(recordNum).putInt(rl).position(0);
/* 491 */     writer.shpChannel.write(this.headerTransfer);
/* 492 */     this.headerTransfer.putInt(0, writer.offset).position(0);
/* 493 */     writer.offset += rl + 4;
/* 494 */     writer.shxChannel.write(this.headerTransfer);
/* 497 */     int oldLimit = this.buffer.limit();
/* 498 */     this.buffer.position(mark).limit(mark + len);
/* 499 */     writer.shpChannel.write(this.buffer);
/* 500 */     this.buffer.limit(oldLimit);
/* 502 */     this.record.end = toFileOffset(this.buffer.position());
/* 503 */     this.record.number++;
/* 505 */     return len;
/*     */   }
/*     */   
/*     */   private void positionBufferForOffset(ByteBuffer buffer, int offset) throws IOException {
/* 509 */     if (this.useMemoryMappedBuffer) {
/* 510 */       buffer.position(offset);
/*     */       return;
/*     */     } 
/* 515 */     if (this.currentOffset <= offset && this.currentOffset + buffer.limit() >= (offset + 8)) {
/* 516 */       buffer.position(toBufferOffset(offset));
/*     */     } else {
/* 518 */       if (!this.randomAccessEnabled)
/* 519 */         throw new UnsupportedOperationException("Random Access not enabled"); 
/* 521 */       FileChannel fc = (FileChannel)this.channel;
/* 522 */       fc.position(offset);
/* 523 */       this.currentOffset = offset;
/* 524 */       buffer.position(0);
/* 525 */       buffer.limit(buffer.capacity());
/* 526 */       fill(buffer, fc);
/* 527 */       buffer.flip();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Record nextRecord() throws IOException {
/* 540 */     positionBufferForOffset(this.buffer, getNextOffset());
/* 541 */     if (this.currentShape != Integer.MIN_VALUE)
/* 542 */       this.currentShape++; 
/* 545 */     this.buffer.order(ByteOrder.BIG_ENDIAN);
/* 548 */     int recordNumber = this.buffer.getInt();
/* 552 */     int recordLength = this.buffer.getInt() * 2;
/* 554 */     if (!this.buffer.isReadOnly() && !this.useMemoryMappedBuffer)
/* 557 */       if (this.buffer.capacity() < recordLength + 8) {
/* 558 */         this.currentOffset += this.buffer.position();
/* 559 */         ByteBuffer old = this.buffer;
/* 561 */         this.buffer = ensureCapacity(this.buffer, recordLength + 8, this.useMemoryMappedBuffer);
/* 563 */         this.buffer.put(old);
/* 564 */         NIOUtilities.clean(old, this.useMemoryMappedBuffer);
/* 565 */         fill(this.buffer, this.channel);
/* 566 */         this.buffer.position(0);
/* 571 */       } else if (this.buffer.remaining() < recordLength + 8) {
/* 572 */         this.currentOffset += this.buffer.position();
/* 573 */         this.buffer.compact();
/* 574 */         fill(this.buffer, this.channel);
/* 575 */         this.buffer.position(0);
/*     */       }  
/* 580 */     this.buffer.order(ByteOrder.LITTLE_ENDIAN);
/* 583 */     ShapeType recordType = ShapeType.forID(this.buffer.getInt());
/* 587 */     if (recordType != ShapeType.NULL && recordType != this.fileShapeType)
/* 588 */       throw new IllegalStateException("ShapeType changed illegally from " + this.fileShapeType + " to " + recordType); 
/* 595 */     this.buffer.mark();
/* 596 */     if (recordType.isMultiPoint()) {
/* 597 */       this.record.minX = this.buffer.getDouble();
/* 598 */       this.record.minY = this.buffer.getDouble();
/* 599 */       this.record.maxX = this.buffer.getDouble();
/* 600 */       this.record.maxY = this.buffer.getDouble();
/* 601 */     } else if (recordType != ShapeType.NULL) {
/* 602 */       this.record.minX = this.record.maxX = this.buffer.getDouble();
/* 603 */       this.record.minY = this.record.maxY = this.buffer.getDouble();
/*     */     } 
/* 605 */     this.buffer.reset();
/* 607 */     this.record.offset = this.record.end;
/* 609 */     this.record.length = recordLength;
/* 610 */     this.record.type = recordType;
/* 611 */     this.record.number = recordNumber;
/* 613 */     this.record.end = toFileOffset(this.buffer.position()) + recordLength - 4;
/* 615 */     this.record.start = this.buffer.position();
/* 617 */     this.record.shape = null;
/* 619 */     return this.record;
/*     */   }
/*     */   
/*     */   public void goTo(int offset) throws IOException, UnsupportedOperationException {
/* 637 */     disableShxUsage();
/* 638 */     if (this.randomAccessEnabled) {
/* 639 */       positionBufferForOffset(this.buffer, offset);
/* 641 */       int oldRecordOffset = this.record.end;
/* 642 */       this.record.end = offset;
/*     */       try {
/* 644 */         hasNext(false);
/* 645 */       } catch (IOException ioe) {
/* 646 */         this.record.end = oldRecordOffset;
/* 647 */         throw ioe;
/*     */       } 
/*     */     } else {
/* 650 */       throw new UnsupportedOperationException("Random Access not enabled");
/*     */     } 
/*     */   }
/*     */   
/*     */   public Object shapeAt(int offset) throws IOException, UnsupportedOperationException {
/* 671 */     disableShxUsage();
/* 672 */     if (this.randomAccessEnabled) {
/* 673 */       goTo(offset);
/* 674 */       return nextRecord().shape();
/*     */     } 
/* 676 */     throw new UnsupportedOperationException("Random Access not enabled");
/*     */   }
/*     */   
/*     */   public Record recordAt(int offset) throws IOException, UnsupportedOperationException {
/* 703 */     if (this.randomAccessEnabled) {
/* 704 */       goTo(offset);
/* 705 */       return nextRecord();
/*     */     } 
/* 707 */     throw new UnsupportedOperationException("Random Access not enabled");
/*     */   }
/*     */   
/*     */   private int toBufferOffset(int offset) {
/* 718 */     return (int)(offset - this.currentOffset);
/*     */   }
/*     */   
/*     */   private int toFileOffset(int offset) {
/* 729 */     return (int)(this.currentOffset + offset);
/*     */   }
/*     */   
/*     */   public int getCount(int count) throws DataSourceException {
/*     */     try {
/* 739 */       if (this.channel == null)
/* 740 */         return -1; 
/* 741 */       count = 0;
/* 742 */       long offset = this.currentOffset;
/*     */       try {
/* 744 */         goTo(100);
/* 745 */       } catch (UnsupportedOperationException e) {
/* 746 */         return -1;
/*     */       } 
/* 748 */       while (hasNext()) {
/* 749 */         count++;
/* 750 */         nextRecord();
/*     */       } 
/* 753 */       goTo((int)offset);
/* 755 */     } catch (IOException ioe) {
/* 756 */       count = -1;
/* 758 */       throw new DataSourceException("Problem reading shapefile record", ioe);
/*     */     } 
/* 761 */     return count;
/*     */   }
/*     */   
/*     */   public void setHandler(ShapeHandler handler) {
/* 769 */     this.handler = handler;
/*     */   }
/*     */   
/*     */   public String id() {
/* 773 */     return getClass().getName();
/*     */   }
/*     */   
/*     */   public void setFlatGeometry(boolean flatGeometry) {
/* 777 */     this.flatGeometry = flatGeometry;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\shp\ShapefileReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */