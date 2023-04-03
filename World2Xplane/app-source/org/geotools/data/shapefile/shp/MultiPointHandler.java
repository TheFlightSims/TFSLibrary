/*     */ package org.geotools.data.shapefile.shp;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.MultiPoint;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ 
/*     */ public class MultiPointHandler implements ShapeHandler {
/*     */   final ShapeType shapeType;
/*     */   
/*     */   GeometryFactory geometryFactory;
/*     */   
/*     */   public MultiPointHandler(GeometryFactory gf) {
/*  43 */     this.shapeType = ShapeType.POINT;
/*  44 */     this.geometryFactory = gf;
/*     */   }
/*     */   
/*     */   public MultiPointHandler(ShapeType type, GeometryFactory gf) throws ShapefileException {
/*  48 */     if (type != ShapeType.MULTIPOINT && type != ShapeType.MULTIPOINTM && type != ShapeType.MULTIPOINTZ)
/*  50 */       throw new ShapefileException("Multipointhandler constructor - expected type to be 8, 18, or 28"); 
/*  54 */     this.shapeType = type;
/*  55 */     this.geometryFactory = gf;
/*     */   }
/*     */   
/*     */   public ShapeType getShapeType() {
/*  64 */     return this.shapeType;
/*     */   }
/*     */   
/*     */   public int getLength(Object geometry) {
/*     */     int length;
/*  74 */     MultiPoint mp = (MultiPoint)geometry;
/*  78 */     if (this.shapeType == ShapeType.MULTIPOINT) {
/*  80 */       length = mp.getNumGeometries() * 16 + 40;
/*  81 */     } else if (this.shapeType == ShapeType.MULTIPOINTM) {
/*  83 */       length = mp.getNumGeometries() * 16 + 40 + 16 + 8 * mp.getNumGeometries();
/*  85 */     } else if (this.shapeType == ShapeType.MULTIPOINTZ) {
/*  87 */       length = mp.getNumGeometries() * 16 + 40 + 16 + 8 * mp.getNumGeometries() + 16 + 8 * mp.getNumGeometries();
/*     */     } else {
/*  91 */       throw new IllegalStateException("Expected ShapeType of Arc, got " + this.shapeType);
/*     */     } 
/*  95 */     return length;
/*     */   }
/*     */   
/*     */   private Object createNull() {
/*  99 */     Coordinate[] c = null;
/* 100 */     return this.geometryFactory.createMultiPoint(c);
/*     */   }
/*     */   
/*     */   public Object read(ByteBuffer buffer, ShapeType type, boolean flatGeometry) {
/* 104 */     if (type == ShapeType.NULL)
/* 105 */       return createNull(); 
/* 109 */     buffer.position(buffer.position() + 32);
/* 111 */     int numpoints = buffer.getInt();
/* 112 */     int dimensions = (this.shapeType == ShapeType.MULTIPOINTZ && !flatGeometry) ? 3 : 2;
/* 113 */     CoordinateSequence cs = this.geometryFactory.getCoordinateSequenceFactory().create(numpoints, dimensions);
/* 115 */     DoubleBuffer dbuffer = buffer.asDoubleBuffer();
/* 116 */     double[] ordinates = new double[numpoints * 2];
/* 117 */     dbuffer.get(ordinates);
/*     */     int t;
/* 118 */     for (t = 0; t < numpoints; t++) {
/* 119 */       cs.setOrdinate(t, 0, ordinates[t * 2]);
/* 120 */       cs.setOrdinate(t, 1, ordinates[t * 2 + 1]);
/*     */     } 
/* 123 */     if (dimensions > 2) {
/* 124 */       dbuffer.position(dbuffer.position() + 2);
/* 126 */       dbuffer.get(ordinates, 0, numpoints);
/* 127 */       for (t = 0; t < numpoints; t++)
/* 128 */         cs.setOrdinate(t, 2, ordinates[t]); 
/*     */     } 
/* 132 */     return this.geometryFactory.createMultiPoint(cs);
/*     */   }
/*     */   
/*     */   public void write(ByteBuffer buffer, Object geometry) {
/* 136 */     MultiPoint mp = (MultiPoint)geometry;
/* 138 */     Envelope box = mp.getEnvelopeInternal();
/* 139 */     buffer.putDouble(box.getMinX());
/* 140 */     buffer.putDouble(box.getMinY());
/* 141 */     buffer.putDouble(box.getMaxX());
/* 142 */     buffer.putDouble(box.getMaxY());
/* 144 */     buffer.putInt(mp.getNumGeometries());
/*     */     int t, tt;
/* 146 */     for (t = 0, tt = mp.getNumGeometries(); t < tt; t++) {
/* 147 */       Coordinate c = mp.getGeometryN(t).getCoordinate();
/* 148 */       buffer.putDouble(c.x);
/* 149 */       buffer.putDouble(c.y);
/*     */     } 
/* 152 */     if (this.shapeType == ShapeType.MULTIPOINTZ) {
/* 153 */       double[] zExtreame = JTSUtilities.zMinMax(mp.getCoordinates());
/* 155 */       if (Double.isNaN(zExtreame[0])) {
/* 156 */         buffer.putDouble(0.0D);
/* 157 */         buffer.putDouble(0.0D);
/*     */       } else {
/* 159 */         buffer.putDouble(zExtreame[0]);
/* 160 */         buffer.putDouble(zExtreame[1]);
/*     */       } 
/* 163 */       for (int i = 0; i < mp.getNumGeometries(); i++) {
/* 164 */         Coordinate c = mp.getGeometryN(i).getCoordinate();
/* 165 */         double z = c.z;
/* 167 */         if (Double.isNaN(z)) {
/* 168 */           buffer.putDouble(0.0D);
/*     */         } else {
/* 170 */           buffer.putDouble(z);
/*     */         } 
/*     */       } 
/*     */     } 
/* 175 */     if (this.shapeType == ShapeType.MULTIPOINTM || this.shapeType == ShapeType.MULTIPOINTZ) {
/* 177 */       buffer.putDouble(-1.0E41D);
/* 178 */       buffer.putDouble(-1.0E41D);
/* 180 */       for (t = 0; t < mp.getNumGeometries(); t++)
/* 181 */         buffer.putDouble(-1.0E41D); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\shp\MultiPointHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */