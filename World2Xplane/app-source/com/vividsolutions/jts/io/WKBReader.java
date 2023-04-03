/*     */ package com.vividsolutions.jts.io;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequences;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import com.vividsolutions.jts.geom.MultiPolygon;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import java.io.IOException;
/*     */ 
/*     */ public class WKBReader {
/*     */   private static final String INVALID_GEOM_TYPE_MSG = "Invalid geometry type encountered in ";
/*     */   
/*     */   private GeometryFactory factory;
/*     */   
/*     */   private CoordinateSequenceFactory csFactory;
/*     */   
/*     */   private PrecisionModel precisionModel;
/*     */   
/*     */   public static byte[] hexToBytes(String hex) {
/*  69 */     int byteLen = hex.length() / 2;
/*  70 */     byte[] bytes = new byte[byteLen];
/*  72 */     for (int i = 0; i < hex.length() / 2; i++) {
/*  73 */       int i2 = 2 * i;
/*  74 */       if (i2 + 1 > hex.length())
/*  75 */         throw new IllegalArgumentException("Hex string has odd length"); 
/*  77 */       int nib1 = hexToInt(hex.charAt(i2));
/*  78 */       int nib0 = hexToInt(hex.charAt(i2 + 1));
/*  79 */       byte b = (byte)((nib1 << 4) + (byte)nib0);
/*  80 */       bytes[i] = b;
/*     */     } 
/*  82 */     return bytes;
/*     */   }
/*     */   
/*     */   private static int hexToInt(char hex) {
/*  87 */     int nib = Character.digit(hex, 16);
/*  88 */     if (nib < 0)
/*  89 */       throw new IllegalArgumentException("Invalid hex digit: '" + hex + "'"); 
/*  90 */     return nib;
/*     */   }
/*     */   
/* 100 */   private int inputDimension = 2;
/*     */   
/*     */   private boolean hasSRID = false;
/*     */   
/* 102 */   private int SRID = 0;
/*     */   
/*     */   private boolean isStrict = false;
/*     */   
/* 108 */   private ByteOrderDataInStream dis = new ByteOrderDataInStream();
/*     */   
/*     */   private double[] ordValues;
/*     */   
/*     */   public WKBReader() {
/* 112 */     this(new GeometryFactory());
/*     */   }
/*     */   
/*     */   public WKBReader(GeometryFactory geometryFactory) {
/* 116 */     this.factory = geometryFactory;
/* 117 */     this.precisionModel = this.factory.getPrecisionModel();
/* 118 */     this.csFactory = this.factory.getCoordinateSequenceFactory();
/*     */   }
/*     */   
/*     */   public Geometry read(byte[] bytes) throws ParseException {
/*     */     try {
/* 133 */       return read(new ByteArrayInStream(bytes));
/* 135 */     } catch (IOException ex) {
/* 136 */       throw new RuntimeException("Unexpected IOException caught: " + ex.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public Geometry read(InStream is) throws IOException, ParseException {
/* 151 */     this.dis.setInStream(is);
/* 152 */     Geometry g = readGeometry();
/* 153 */     return g;
/*     */   }
/*     */   
/*     */   private Geometry readGeometry() throws IOException, ParseException {
/*     */     Point point;
/*     */     LineString lineString;
/*     */     Polygon polygon;
/*     */     MultiPoint multiPoint;
/*     */     MultiLineString multiLineString;
/*     */     MultiPolygon multiPolygon;
/*     */     GeometryCollection geometryCollection;
/* 161 */     byte byteOrderWKB = this.dis.readByte();
/* 164 */     if (byteOrderWKB == 1) {
/* 166 */       this.dis.setOrder(2);
/* 168 */     } else if (byteOrderWKB == 0) {
/* 170 */       this.dis.setOrder(1);
/* 172 */     } else if (this.isStrict) {
/* 174 */       throw new ParseException("Unknown geometry byte order (not NDR or XDR): " + byteOrderWKB);
/*     */     } 
/* 182 */     int typeInt = this.dis.readInt();
/* 183 */     int geometryType = typeInt & 0xFF;
/* 185 */     boolean hasZ = ((typeInt & Integer.MIN_VALUE) != 0);
/* 186 */     this.inputDimension = hasZ ? 3 : 2;
/* 188 */     this.hasSRID = ((typeInt & 0x20000000) != 0);
/* 190 */     int SRID = 0;
/* 191 */     if (this.hasSRID)
/* 192 */       SRID = this.dis.readInt(); 
/* 196 */     if (this.ordValues == null || this.ordValues.length < this.inputDimension)
/* 197 */       this.ordValues = new double[this.inputDimension]; 
/* 199 */     Geometry geom = null;
/* 200 */     switch (geometryType) {
/*     */       case 1:
/* 202 */         point = readPoint();
/* 225 */         setSRID((Geometry)point, SRID);
/* 226 */         return (Geometry)point;
/*     */       case 2:
/*     */         lineString = readLineString();
/*     */         setSRID((Geometry)lineString, SRID);
/* 226 */         return (Geometry)lineString;
/*     */       case 3:
/*     */         polygon = readPolygon();
/*     */         setSRID((Geometry)polygon, SRID);
/* 226 */         return (Geometry)polygon;
/*     */       case 4:
/*     */         multiPoint = readMultiPoint();
/*     */         setSRID((Geometry)multiPoint, SRID);
/* 226 */         return (Geometry)multiPoint;
/*     */       case 5:
/*     */         multiLineString = readMultiLineString();
/*     */         setSRID((Geometry)multiLineString, SRID);
/* 226 */         return (Geometry)multiLineString;
/*     */       case 6:
/*     */         multiPolygon = readMultiPolygon();
/*     */         setSRID((Geometry)multiPolygon, SRID);
/* 226 */         return (Geometry)multiPolygon;
/*     */       case 7:
/*     */         geometryCollection = readGeometryCollection();
/*     */         setSRID((Geometry)geometryCollection, SRID);
/* 226 */         return (Geometry)geometryCollection;
/*     */     } 
/*     */     throw new ParseException("Unknown WKB type " + geometryType);
/*     */   }
/*     */   
/*     */   private Geometry setSRID(Geometry g, int SRID) {
/* 237 */     if (SRID != 0)
/* 238 */       g.setSRID(SRID); 
/* 239 */     return g;
/*     */   }
/*     */   
/*     */   private Point readPoint() throws IOException {
/* 244 */     CoordinateSequence pts = readCoordinateSequence(1);
/* 245 */     return this.factory.createPoint(pts);
/*     */   }
/*     */   
/*     */   private LineString readLineString() throws IOException {
/* 250 */     int size = this.dis.readInt();
/* 251 */     CoordinateSequence pts = readCoordinateSequenceLineString(size);
/* 252 */     return this.factory.createLineString(pts);
/*     */   }
/*     */   
/*     */   private LinearRing readLinearRing() throws IOException {
/* 257 */     int size = this.dis.readInt();
/* 258 */     CoordinateSequence pts = readCoordinateSequenceRing(size);
/* 259 */     return this.factory.createLinearRing(pts);
/*     */   }
/*     */   
/*     */   private Polygon readPolygon() throws IOException {
/* 264 */     int numRings = this.dis.readInt();
/* 265 */     LinearRing[] holes = null;
/* 266 */     if (numRings > 1)
/* 267 */       holes = new LinearRing[numRings - 1]; 
/* 269 */     LinearRing shell = readLinearRing();
/* 270 */     for (int i = 0; i < numRings - 1; i++)
/* 271 */       holes[i] = readLinearRing(); 
/* 273 */     return this.factory.createPolygon(shell, holes);
/*     */   }
/*     */   
/*     */   private MultiPoint readMultiPoint() throws IOException, ParseException {
/* 278 */     int numGeom = this.dis.readInt();
/* 279 */     Point[] geoms = new Point[numGeom];
/* 280 */     for (int i = 0; i < numGeom; i++) {
/* 281 */       Geometry g = readGeometry();
/* 282 */       if (!(g instanceof Point))
/* 283 */         throw new ParseException("Invalid geometry type encountered in MultiPoint"); 
/* 284 */       geoms[i] = (Point)g;
/*     */     } 
/* 286 */     return this.factory.createMultiPoint(geoms);
/*     */   }
/*     */   
/*     */   private MultiLineString readMultiLineString() throws IOException, ParseException {
/* 291 */     int numGeom = this.dis.readInt();
/* 292 */     LineString[] geoms = new LineString[numGeom];
/* 293 */     for (int i = 0; i < numGeom; i++) {
/* 294 */       Geometry g = readGeometry();
/* 295 */       if (!(g instanceof LineString))
/* 296 */         throw new ParseException("Invalid geometry type encountered in MultiLineString"); 
/* 297 */       geoms[i] = (LineString)g;
/*     */     } 
/* 299 */     return this.factory.createMultiLineString(geoms);
/*     */   }
/*     */   
/*     */   private MultiPolygon readMultiPolygon() throws IOException, ParseException {
/* 304 */     int numGeom = this.dis.readInt();
/* 305 */     Polygon[] geoms = new Polygon[numGeom];
/* 307 */     for (int i = 0; i < numGeom; i++) {
/* 308 */       Geometry g = readGeometry();
/* 309 */       if (!(g instanceof Polygon))
/* 310 */         throw new ParseException("Invalid geometry type encountered in MultiPolygon"); 
/* 311 */       geoms[i] = (Polygon)g;
/*     */     } 
/* 313 */     return this.factory.createMultiPolygon(geoms);
/*     */   }
/*     */   
/*     */   private GeometryCollection readGeometryCollection() throws IOException, ParseException {
/* 318 */     int numGeom = this.dis.readInt();
/* 319 */     Geometry[] geoms = new Geometry[numGeom];
/* 320 */     for (int i = 0; i < numGeom; i++)
/* 321 */       geoms[i] = readGeometry(); 
/* 323 */     return this.factory.createGeometryCollection(geoms);
/*     */   }
/*     */   
/*     */   private CoordinateSequence readCoordinateSequence(int size) throws IOException {
/* 328 */     CoordinateSequence seq = this.csFactory.create(size, this.inputDimension);
/* 329 */     int targetDim = seq.getDimension();
/* 330 */     if (targetDim > this.inputDimension)
/* 331 */       targetDim = this.inputDimension; 
/* 332 */     for (int i = 0; i < size; i++) {
/* 333 */       readCoordinate();
/* 334 */       for (int j = 0; j < targetDim; j++)
/* 335 */         seq.setOrdinate(i, j, this.ordValues[j]); 
/*     */     } 
/* 338 */     return seq;
/*     */   }
/*     */   
/*     */   private CoordinateSequence readCoordinateSequenceLineString(int size) throws IOException {
/* 343 */     CoordinateSequence seq = readCoordinateSequence(size);
/* 344 */     if (this.isStrict)
/* 344 */       return seq; 
/* 345 */     if (seq.size() == 0 || seq.size() >= 2)
/* 345 */       return seq; 
/* 346 */     return CoordinateSequences.extend(this.csFactory, seq, 2);
/*     */   }
/*     */   
/*     */   private CoordinateSequence readCoordinateSequenceRing(int size) throws IOException {
/* 351 */     CoordinateSequence seq = readCoordinateSequence(size);
/* 352 */     if (this.isStrict)
/* 352 */       return seq; 
/* 353 */     if (CoordinateSequences.isRing(seq))
/* 353 */       return seq; 
/* 354 */     return CoordinateSequences.ensureValidRing(this.csFactory, seq);
/*     */   }
/*     */   
/*     */   private void readCoordinate() throws IOException {
/* 364 */     for (int i = 0; i < this.inputDimension; i++) {
/* 365 */       if (i <= 1) {
/* 366 */         this.ordValues[i] = this.precisionModel.makePrecise(this.dis.readDouble());
/*     */       } else {
/* 369 */         this.ordValues[i] = this.dis.readDouble();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\io\WKBReader.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */