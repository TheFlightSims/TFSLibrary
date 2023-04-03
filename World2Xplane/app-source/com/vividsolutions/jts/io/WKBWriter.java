/*     */ package com.vividsolutions.jts.io;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ 
/*     */ public class WKBWriter {
/*     */   public static String bytesToHex(byte[] bytes) {
/* 180 */     return toHex(bytes);
/*     */   }
/*     */   
/*     */   public static String toHex(byte[] bytes) {
/* 191 */     StringBuffer buf = new StringBuffer();
/* 192 */     for (int i = 0; i < bytes.length; i++) {
/* 193 */       byte b = bytes[i];
/* 194 */       buf.append(toHexDigit(b >> 4 & 0xF));
/* 195 */       buf.append(toHexDigit(b & 0xF));
/*     */     } 
/* 197 */     return buf.toString();
/*     */   }
/*     */   
/*     */   private static char toHexDigit(int n) {
/* 202 */     if (n < 0 || n > 15)
/* 203 */       throw new IllegalArgumentException("Nibble value out of range: " + n); 
/* 204 */     if (n <= 9)
/* 205 */       return (char)(48 + n); 
/* 206 */     return (char)(65 + n - 10);
/*     */   }
/*     */   
/* 209 */   private int outputDimension = 2;
/*     */   
/*     */   private int byteOrder;
/*     */   
/*     */   private boolean includeSRID = false;
/*     */   
/* 212 */   private ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
/*     */   
/* 213 */   private OutStream byteArrayOutStream = new OutputStreamOutStream(this.byteArrayOS);
/*     */   
/* 215 */   private byte[] buf = new byte[8];
/*     */   
/*     */   public WKBWriter() {
/* 222 */     this(2, 1);
/*     */   }
/*     */   
/*     */   public WKBWriter(int outputDimension) {
/* 235 */     this(outputDimension, 1);
/*     */   }
/*     */   
/*     */   public WKBWriter(int outputDimension, boolean includeSRID) {
/* 251 */     this(outputDimension, 1, includeSRID);
/*     */   }
/*     */   
/*     */   public WKBWriter(int outputDimension, int byteOrder) {
/* 265 */     this(outputDimension, byteOrder, false);
/*     */   }
/*     */   
/*     */   public WKBWriter(int outputDimension, int byteOrder, boolean includeSRID) {
/* 281 */     this.outputDimension = outputDimension;
/* 282 */     this.byteOrder = byteOrder;
/* 283 */     this.includeSRID = includeSRID;
/* 285 */     if (outputDimension < 2 || outputDimension > 3)
/* 286 */       throw new IllegalArgumentException("Output dimension must be 2 or 3"); 
/*     */   }
/*     */   
/*     */   public byte[] write(Geometry geom) {
/*     */     try {
/* 298 */       this.byteArrayOS.reset();
/* 299 */       write(geom, this.byteArrayOutStream);
/* 301 */     } catch (IOException ex) {
/* 302 */       throw new RuntimeException("Unexpected IO exception: " + ex.getMessage());
/*     */     } 
/* 304 */     return this.byteArrayOS.toByteArray();
/*     */   }
/*     */   
/*     */   public void write(Geometry geom, OutStream os) throws IOException {
/* 316 */     if (geom instanceof Point) {
/* 317 */       writePoint((Point)geom, os);
/* 319 */     } else if (geom instanceof LineString) {
/* 320 */       writeLineString((LineString)geom, os);
/* 321 */     } else if (geom instanceof Polygon) {
/* 322 */       writePolygon((Polygon)geom, os);
/* 323 */     } else if (geom instanceof com.vividsolutions.jts.geom.MultiPoint) {
/* 324 */       writeGeometryCollection(4, (GeometryCollection)geom, os);
/* 326 */     } else if (geom instanceof com.vividsolutions.jts.geom.MultiLineString) {
/* 327 */       writeGeometryCollection(5, (GeometryCollection)geom, os);
/* 329 */     } else if (geom instanceof com.vividsolutions.jts.geom.MultiPolygon) {
/* 330 */       writeGeometryCollection(6, (GeometryCollection)geom, os);
/* 332 */     } else if (geom instanceof GeometryCollection) {
/* 333 */       writeGeometryCollection(7, (GeometryCollection)geom, os);
/*     */     } else {
/* 336 */       Assert.shouldNeverReachHere("Unknown Geometry type");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void writePoint(Point pt, OutStream os) throws IOException {
/* 342 */     if (pt.getCoordinateSequence().size() == 0)
/* 343 */       throw new IllegalArgumentException("Empty Points cannot be represented in WKB"); 
/* 344 */     writeByteOrder(os);
/* 345 */     writeGeometryType(1, (Geometry)pt, os);
/* 346 */     writeCoordinateSequence(pt.getCoordinateSequence(), false, os);
/*     */   }
/*     */   
/*     */   private void writeLineString(LineString line, OutStream os) throws IOException {
/* 352 */     writeByteOrder(os);
/* 353 */     writeGeometryType(2, (Geometry)line, os);
/* 354 */     writeCoordinateSequence(line.getCoordinateSequence(), true, os);
/*     */   }
/*     */   
/*     */   private void writePolygon(Polygon poly, OutStream os) throws IOException {
/* 359 */     writeByteOrder(os);
/* 360 */     writeGeometryType(3, (Geometry)poly, os);
/* 361 */     writeInt(poly.getNumInteriorRing() + 1, os);
/* 362 */     writeCoordinateSequence(poly.getExteriorRing().getCoordinateSequence(), true, os);
/* 363 */     for (int i = 0; i < poly.getNumInteriorRing(); i++)
/* 364 */       writeCoordinateSequence(poly.getInteriorRingN(i).getCoordinateSequence(), true, os); 
/*     */   }
/*     */   
/*     */   private void writeGeometryCollection(int geometryType, GeometryCollection gc, OutStream os) throws IOException {
/* 372 */     writeByteOrder(os);
/* 373 */     writeGeometryType(geometryType, (Geometry)gc, os);
/* 374 */     writeInt(gc.getNumGeometries(), os);
/* 375 */     for (int i = 0; i < gc.getNumGeometries(); i++)
/* 376 */       write(gc.getGeometryN(i), os); 
/*     */   }
/*     */   
/*     */   private void writeByteOrder(OutStream os) throws IOException {
/* 382 */     if (this.byteOrder == 2) {
/* 383 */       this.buf[0] = 1;
/*     */     } else {
/* 385 */       this.buf[0] = 0;
/*     */     } 
/* 386 */     os.write(this.buf, 1);
/*     */   }
/*     */   
/*     */   private void writeGeometryType(int geometryType, Geometry g, OutStream os) throws IOException {
/* 392 */     int flag3D = (this.outputDimension == 3) ? Integer.MIN_VALUE : 0;
/* 393 */     int typeInt = geometryType | flag3D;
/* 394 */     typeInt |= this.includeSRID ? 536870912 : 0;
/* 395 */     writeInt(typeInt, os);
/* 396 */     if (this.includeSRID)
/* 397 */       writeInt(g.getSRID(), os); 
/*     */   }
/*     */   
/*     */   private void writeInt(int intValue, OutStream os) throws IOException {
/* 403 */     ByteOrderValues.putInt(intValue, this.buf, this.byteOrder);
/* 404 */     os.write(this.buf, 4);
/*     */   }
/*     */   
/*     */   private void writeCoordinateSequence(CoordinateSequence seq, boolean writeSize, OutStream os) throws IOException {
/* 410 */     if (writeSize)
/* 411 */       writeInt(seq.size(), os); 
/* 413 */     for (int i = 0; i < seq.size(); i++)
/* 414 */       writeCoordinate(seq, i, os); 
/*     */   }
/*     */   
/*     */   private void writeCoordinate(CoordinateSequence seq, int index, OutStream os) throws IOException {
/* 421 */     ByteOrderValues.putDouble(seq.getX(index), this.buf, this.byteOrder);
/* 422 */     os.write(this.buf, 8);
/* 423 */     ByteOrderValues.putDouble(seq.getY(index), this.buf, this.byteOrder);
/* 424 */     os.write(this.buf, 8);
/* 427 */     if (this.outputDimension >= 3) {
/* 429 */       double ordVal = Double.NaN;
/* 430 */       if (seq.getDimension() >= 3)
/* 431 */         ordVal = seq.getOrdinate(index, 2); 
/* 432 */       ByteOrderValues.putDouble(ordVal, this.buf, this.byteOrder);
/* 433 */       os.write(this.buf, 8);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\WKBWriter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */