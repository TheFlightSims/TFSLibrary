/*     */ package org.geotools.data.shapefile.shp;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.MultiLineString;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.DoubleBuffer;
/*     */ 
/*     */ public class MultiLineHandler implements ShapeHandler {
/*     */   final ShapeType shapeType;
/*     */   
/*     */   GeometryFactory geometryFactory;
/*     */   
/*     */   double[] xy;
/*     */   
/*     */   double[] z;
/*     */   
/*     */   public MultiLineHandler(GeometryFactory gf) {
/*  52 */     this.shapeType = ShapeType.ARC;
/*  53 */     this.geometryFactory = gf;
/*     */   }
/*     */   
/*     */   public MultiLineHandler(ShapeType type, GeometryFactory gf) throws ShapefileException {
/*  66 */     if (type != ShapeType.ARC && type != ShapeType.ARCM && type != ShapeType.ARCZ)
/*  68 */       throw new ShapefileException("MultiLineHandler constructor - expected type to be 3,13 or 23"); 
/*  72 */     this.shapeType = type;
/*  73 */     this.geometryFactory = gf;
/*     */   }
/*     */   
/*     */   public ShapeType getShapeType() {
/*  81 */     return this.shapeType;
/*     */   }
/*     */   
/*     */   public int getLength(Object geometry) {
/*     */     int length;
/*  86 */     MultiLineString multi = (MultiLineString)geometry;
/*  92 */     int numlines = multi.getNumGeometries();
/*  93 */     int numpoints = multi.getNumPoints();
/*  95 */     if (this.shapeType == ShapeType.ARC) {
/*  96 */       length = 44 + 4 * numlines + numpoints * 16;
/*  97 */     } else if (this.shapeType == ShapeType.ARCM) {
/*  98 */       length = 44 + 4 * numlines + numpoints * 16 + 8 + 8 + 8 * numpoints;
/* 100 */     } else if (this.shapeType == ShapeType.ARCZ) {
/* 101 */       length = 44 + 4 * numlines + numpoints * 16 + 8 + 8 + 8 * numpoints + 8 + 8 + 8 * numpoints;
/*     */     } else {
/* 104 */       throw new IllegalStateException("Expected ShapeType of Arc, got " + this.shapeType);
/*     */     } 
/* 108 */     return length;
/*     */   }
/*     */   
/*     */   private Object createNull() {
/* 112 */     return this.geometryFactory.createMultiLineString((LineString[])null);
/*     */   }
/*     */   
/*     */   public Object read(ByteBuffer buffer, ShapeType type, boolean flatGeometry) {
/* 116 */     if (type == ShapeType.NULL)
/* 117 */       return createNull(); 
/* 119 */     int dimensions = (this.shapeType == ShapeType.ARCZ && !flatGeometry) ? 3 : 2;
/* 121 */     buffer.position(buffer.position() + 32);
/* 123 */     int numParts = buffer.getInt();
/* 124 */     int numPoints = buffer.getInt();
/* 126 */     int[] partOffsets = new int[numParts];
/* 129 */     for (int i = 0; i < numParts; i++)
/* 130 */       partOffsets[i] = buffer.getInt(); 
/* 134 */     CoordinateSequence[] lines = new CoordinateSequence[numParts];
/* 135 */     int start = 0;
/* 136 */     int length = 0;
/* 137 */     boolean clonePoint = false;
/* 138 */     DoubleBuffer doubleBuffer = buffer.asDoubleBuffer();
/*     */     int part;
/* 139 */     for (part = 0; part < numParts; part++) {
/*     */       int finish;
/* 140 */       start = partOffsets[part];
/* 142 */       if (part == numParts - 1) {
/* 143 */         finish = numPoints;
/*     */       } else {
/* 145 */         finish = partOffsets[part + 1];
/*     */       } 
/* 148 */       length = finish - start;
/* 149 */       int xyLength = length;
/* 150 */       if (length == 1) {
/* 151 */         length = 2;
/* 152 */         clonePoint = true;
/*     */       } else {
/* 154 */         clonePoint = false;
/*     */       } 
/* 157 */       CoordinateSequence cs = this.geometryFactory.getCoordinateSequenceFactory().create(length, dimensions);
/* 158 */       double[] xy = new double[xyLength * 2];
/* 159 */       doubleBuffer.get(xy);
/* 160 */       for (int k = 0; k < xyLength; k++) {
/* 161 */         cs.setOrdinate(k, 0, xy[k * 2]);
/* 162 */         cs.setOrdinate(k, 1, xy[k * 2 + 1]);
/*     */       } 
/* 165 */       if (clonePoint) {
/* 166 */         cs.setOrdinate(1, 0, cs.getOrdinate(0, 0));
/* 167 */         cs.setOrdinate(1, 1, cs.getOrdinate(0, 1));
/*     */       } 
/* 170 */       lines[part] = cs;
/*     */     } 
/* 175 */     if (dimensions == 3) {
/* 178 */       doubleBuffer.position(doubleBuffer.position() + 2);
/* 179 */       for (part = 0; part < numParts; part++) {
/*     */         int finish;
/* 180 */         start = partOffsets[part];
/* 182 */         if (part == numParts - 1) {
/* 183 */           finish = numPoints;
/*     */         } else {
/* 185 */           finish = partOffsets[part + 1];
/*     */         } 
/* 188 */         length = finish - start;
/* 189 */         if (length == 1) {
/* 190 */           length = 2;
/* 191 */           clonePoint = true;
/*     */         } else {
/* 193 */           clonePoint = false;
/*     */         } 
/* 196 */         double[] z = new double[length];
/* 197 */         doubleBuffer.get(z);
/* 198 */         for (int k = 0; k < length; k++)
/* 199 */           lines[part].setOrdinate(k, 2, z[k]); 
/*     */       } 
/*     */     } 
/* 206 */     LineString[] lineStrings = new LineString[numParts];
/* 207 */     for (int j = 0; j < numParts; j++)
/* 208 */       lineStrings[j] = this.geometryFactory.createLineString(lines[j]); 
/* 211 */     return this.geometryFactory.createMultiLineString(lineStrings);
/*     */   }
/*     */   
/*     */   public void write(ByteBuffer buffer, Object geometry) {
/* 215 */     MultiLineString multi = (MultiLineString)geometry;
/* 217 */     Envelope box = multi.getEnvelopeInternal();
/* 218 */     buffer.putDouble(box.getMinX());
/* 219 */     buffer.putDouble(box.getMinY());
/* 220 */     buffer.putDouble(box.getMaxX());
/* 221 */     buffer.putDouble(box.getMaxY());
/* 223 */     int numParts = multi.getNumGeometries();
/* 224 */     CoordinateSequence[] lines = new CoordinateSequence[numParts];
/* 225 */     double[] zExtreame = { Double.NaN, Double.NaN };
/* 226 */     int npoints = multi.getNumPoints();
/* 228 */     buffer.putInt(numParts);
/* 229 */     buffer.putInt(npoints);
/* 232 */     int idx = 0;
/* 233 */     for (int i = 0; i < numParts; i++) {
/* 234 */       lines[i] = ((LineString)multi.getGeometryN(i)).getCoordinateSequence();
/* 235 */       buffer.putInt(idx);
/* 236 */       idx += lines[i].size();
/*     */     } 
/*     */     int lineN;
/* 240 */     for (lineN = 0; lineN < lines.length; lineN++) {
/* 241 */       CoordinateSequence coords = lines[lineN];
/* 242 */       if (this.shapeType == ShapeType.ARCZ)
/* 243 */         JTSUtilities.zMinMax(coords, zExtreame); 
/* 245 */       int ncoords = coords.size();
/* 247 */       for (int t = 0; t < ncoords; t++) {
/* 248 */         buffer.putDouble(coords.getX(t));
/* 249 */         buffer.putDouble(coords.getY(t));
/*     */       } 
/*     */     } 
/* 253 */     if (this.shapeType == ShapeType.ARCZ) {
/* 254 */       if (Double.isNaN(zExtreame[0])) {
/* 255 */         buffer.putDouble(0.0D);
/* 256 */         buffer.putDouble(0.0D);
/*     */       } else {
/* 258 */         buffer.putDouble(zExtreame[0]);
/* 259 */         buffer.putDouble(zExtreame[1]);
/*     */       } 
/* 262 */       for (lineN = 0; lineN < lines.length; lineN++) {
/* 263 */         CoordinateSequence coords = lines[lineN];
/* 264 */         int ncoords = coords.size();
/* 266 */         for (int j = 0; j < ncoords; j++) {
/* 267 */           double z = coords.getOrdinate(j, 2);
/* 268 */           if (Double.isNaN(z)) {
/* 269 */             buffer.putDouble(0.0D);
/*     */           } else {
/* 271 */             buffer.putDouble(z);
/*     */           } 
/*     */         } 
/*     */       } 
/* 276 */       buffer.putDouble(-1.0E41D);
/* 277 */       buffer.putDouble(-1.0E41D);
/* 279 */       for (int t = 0; t < npoints; t++)
/* 280 */         buffer.putDouble(-1.0E41D); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\shp\MultiLineHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */