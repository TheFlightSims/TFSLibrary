/*     */ package org.geotools.renderer;
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
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public class ScreenMap {
/*  57 */   double[] point = new double[2];
/*     */   
/*     */   int[] pixels;
/*     */   
/*     */   int width;
/*     */   
/*     */   int height;
/*     */   
/*     */   private int minx;
/*     */   
/*     */   private int miny;
/*     */   
/*     */   MathTransform mt;
/*     */   
/*     */   double spanX;
/*     */   
/*     */   double spanY;
/*     */   
/*     */   public ScreenMap(int x, int y, int width, int height, MathTransform mt) {
/*  76 */     this.width = width;
/*  77 */     this.height = height;
/*  78 */     this.minx = x;
/*  79 */     this.miny = y;
/*  81 */     int arraySize = width * height / 32 + 1;
/*  82 */     this.pixels = new int[arraySize];
/*  83 */     this.mt = mt;
/*     */   }
/*     */   
/*     */   public ScreenMap(ScreenMap original, int expandBy) {
/*  87 */     this(original.minx - expandBy, original.miny - expandBy, original.width + expandBy * 2, original.height + expandBy * 2);
/*     */   }
/*     */   
/*     */   public ScreenMap(int x, int y, int width, int height) {
/*  91 */     this(x, y, width, height, null);
/*     */   }
/*     */   
/*     */   public void setTransform(MathTransform mt) {
/*  95 */     this.mt = mt;
/*     */   }
/*     */   
/*     */   public boolean checkAndSet(Envelope envelope) throws TransformException {
/*  99 */     if (!canSimplify(envelope))
/* 100 */       return false; 
/* 103 */     this.point[0] = (envelope.getMinX() + envelope.getMaxX()) / 2.0D;
/* 104 */     this.point[1] = (envelope.getMinY() + envelope.getMaxY()) / 2.0D;
/* 105 */     this.mt.transform(this.point, 0, this.point, 0, 1);
/* 106 */     int r = (int)this.point[0];
/* 107 */     int c = (int)this.point[1];
/* 108 */     return checkAndSet(r, c);
/*     */   }
/*     */   
/*     */   public boolean canSimplify(Envelope envelope) {
/* 112 */     return (envelope.getWidth() < this.spanX && envelope.getHeight() < this.spanY);
/*     */   }
/*     */   
/*     */   public void setSpans(double spanX, double spanY) {
/* 116 */     this.spanX = spanX;
/* 117 */     this.spanY = spanY;
/*     */   }
/*     */   
/*     */   public boolean checkAndSet(int x, int y) {
/* 128 */     if (x - this.minx < 0 || x - this.minx > this.width - 1 || y - this.miny < 0 || y - this.miny > this.height - 1)
/* 129 */       return false; 
/* 130 */     int bit = bit(x - this.minx, y - this.miny);
/* 131 */     int index = bit / 32;
/* 132 */     int offset = bit % 32;
/* 133 */     int mask = 1 << offset;
/*     */     try {
/* 136 */       if ((this.pixels[index] & mask) != 0)
/* 137 */         return true; 
/* 139 */       this.pixels[index] = this.pixels[index] | mask;
/* 140 */       return false;
/* 142 */     } catch (Exception e) {
/* 143 */       return true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean get(int x, int y) {
/* 151 */     if (x - this.minx < 0 || x - this.minx > this.width - 1 || y - this.miny < 0 || y - this.miny > this.height - 1)
/* 152 */       return true; 
/* 153 */     int bit = bit(x - this.minx, y - this.miny);
/* 154 */     int index = bit / 32;
/* 155 */     int offset = bit % 32;
/* 156 */     int mask = 1 << offset;
/*     */     try {
/* 159 */       return ((this.pixels[index] & mask) != 0);
/* 160 */     } catch (Exception e) {
/* 162 */       return true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private int bit(int x, int y) {
/* 167 */     return this.width * y + x;
/*     */   }
/*     */   
/*     */   public Geometry getSimplifiedShape(double minx, double miny, double maxx, double maxy, GeometryFactory geometryFactory, Class<?> geometryType) {
/* 183 */     CoordinateSequenceFactory csf = geometryFactory.getCoordinateSequenceFactory();
/* 184 */     double midx = (minx + maxx) / 2.0D;
/* 185 */     double midy = (miny + maxy) / 2.0D;
/* 186 */     double x0 = midx - this.spanX / 2.0D;
/* 187 */     double x1 = midx + this.spanX / 2.0D;
/* 188 */     double y0 = midy - this.spanY / 2.0D;
/* 189 */     double y1 = midy + this.spanY / 2.0D;
/* 190 */     if (Point.class.isAssignableFrom(geometryType) || MultiPoint.class.isAssignableFrom(geometryType)) {
/* 192 */       CoordinateSequence coordinateSequence = csf.create(1, 2);
/* 193 */       coordinateSequence.setOrdinate(0, 0, midx);
/* 194 */       coordinateSequence.setOrdinate(0, 1, midy);
/* 195 */       if (Point.class.isAssignableFrom(geometryType))
/* 197 */         return (Geometry)geometryFactory.createPoint(coordinateSequence); 
/* 199 */       return (Geometry)geometryFactory.createMultiPoint(new Point[] { geometryFactory.createPoint(coordinateSequence) });
/*     */     } 
/* 202 */     if (LineString.class.isAssignableFrom(geometryType) || MultiLineString.class.isAssignableFrom(geometryType)) {
/* 204 */       CoordinateSequence coordinateSequence = csf.create(2, 2);
/* 205 */       coordinateSequence.setOrdinate(0, 0, x0);
/* 206 */       coordinateSequence.setOrdinate(0, 1, y0);
/* 207 */       coordinateSequence.setOrdinate(1, 0, x1);
/* 208 */       coordinateSequence.setOrdinate(1, 1, y1);
/* 209 */       if (MultiLineString.class.isAssignableFrom(geometryType))
/* 210 */         return (Geometry)geometryFactory.createMultiLineString(new LineString[] { geometryFactory.createLineString(coordinateSequence) }); 
/* 213 */       return (Geometry)geometryFactory.createLineString(coordinateSequence);
/*     */     } 
/* 216 */     CoordinateSequence cs = csf.create(5, 2);
/* 217 */     cs.setOrdinate(0, 0, x0);
/* 218 */     cs.setOrdinate(0, 1, y0);
/* 219 */     cs.setOrdinate(1, 0, x0);
/* 220 */     cs.setOrdinate(1, 1, y1);
/* 221 */     cs.setOrdinate(2, 0, x1);
/* 222 */     cs.setOrdinate(2, 1, y1);
/* 223 */     cs.setOrdinate(3, 0, x1);
/* 224 */     cs.setOrdinate(3, 1, y0);
/* 225 */     cs.setOrdinate(4, 0, x0);
/* 226 */     cs.setOrdinate(4, 1, y0);
/* 227 */     LinearRing ring = geometryFactory.createLinearRing(cs);
/* 228 */     if (MultiPolygon.class.isAssignableFrom(geometryType))
/* 229 */       return (Geometry)geometryFactory.createMultiPolygon(new Polygon[] { geometryFactory.createPolygon(ring, null) }); 
/* 232 */     return (Geometry)geometryFactory.createPolygon(ring, null);
/*     */   }
/*     */   
/*     */   public void set(int x, int y, boolean value) {
/* 241 */     if (x - this.minx < 0 || x - this.minx > this.width - 1 || y - this.miny < 0 || y - this.miny > this.height - 1)
/*     */       return; 
/* 243 */     int bit = bit(x - this.minx, y - this.miny);
/* 244 */     int index = bit / 32;
/* 245 */     int offset = bit % 32;
/* 246 */     int mask = 1;
/* 247 */     mask <<= offset;
/* 249 */     if (value) {
/* 250 */       this.pixels[index] = this.pixels[index] | mask;
/*     */     } else {
/* 252 */       int tmp = this.pixels[index];
/* 253 */       tmp ^= 0xFFFFFFFF;
/* 254 */       tmp |= mask;
/* 255 */       tmp ^= 0xFFFFFFFF;
/* 256 */       this.pixels[index] = tmp;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\renderer\ScreenMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */