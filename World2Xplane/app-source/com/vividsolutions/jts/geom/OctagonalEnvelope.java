/*     */ package com.vividsolutions.jts.geom;
/*     */ 
/*     */ public class OctagonalEnvelope {
/*     */   private static double computeA(double x, double y) {
/*  48 */     return x + y;
/*     */   }
/*     */   
/*     */   private static double computeB(double x, double y) {
/*  53 */     return x - y;
/*     */   }
/*     */   
/*  56 */   private static double SQRT2 = Math.sqrt(2.0D);
/*     */   
/*  59 */   private double minX = Double.NaN;
/*     */   
/*     */   private double maxX;
/*     */   
/*     */   private double minY;
/*     */   
/*     */   private double maxY;
/*     */   
/*     */   private double minA;
/*     */   
/*     */   private double maxA;
/*     */   
/*     */   private double minB;
/*     */   
/*     */   private double maxB;
/*     */   
/*     */   public OctagonalEnvelope() {}
/*     */   
/*     */   public OctagonalEnvelope(Coordinate p) {
/*  80 */     expandToInclude(p);
/*     */   }
/*     */   
/*     */   public OctagonalEnvelope(Coordinate p0, Coordinate p1) {
/*  88 */     expandToInclude(p0);
/*  89 */     expandToInclude(p1);
/*     */   }
/*     */   
/*     */   public OctagonalEnvelope(Envelope env) {
/*  97 */     expandToInclude(env);
/*     */   }
/*     */   
/*     */   public OctagonalEnvelope(OctagonalEnvelope oct) {
/* 106 */     expandToInclude(oct);
/*     */   }
/*     */   
/*     */   public OctagonalEnvelope(Geometry geom) {
/* 114 */     expandToInclude(geom);
/*     */   }
/*     */   
/*     */   public double getMinX() {
/* 118 */     return this.minX;
/*     */   }
/*     */   
/*     */   public double getMaxX() {
/* 119 */     return this.maxX;
/*     */   }
/*     */   
/*     */   public double getMinY() {
/* 120 */     return this.minY;
/*     */   }
/*     */   
/*     */   public double getMaxY() {
/* 121 */     return this.maxY;
/*     */   }
/*     */   
/*     */   public double getMinA() {
/* 122 */     return this.minA;
/*     */   }
/*     */   
/*     */   public double getMaxA() {
/* 123 */     return this.maxA;
/*     */   }
/*     */   
/*     */   public double getMinB() {
/* 124 */     return this.minB;
/*     */   }
/*     */   
/*     */   public double getMaxB() {
/* 125 */     return this.maxB;
/*     */   }
/*     */   
/*     */   public boolean isNull() {
/* 127 */     return Double.isNaN(this.minX);
/*     */   }
/*     */   
/*     */   public void setToNull() {
/* 133 */     this.minX = Double.NaN;
/*     */   }
/*     */   
/*     */   public void expandToInclude(Geometry g) {
/* 138 */     g.apply(new BoundingOctagonComponentFilter());
/*     */   }
/*     */   
/*     */   public OctagonalEnvelope expandToInclude(CoordinateSequence seq) {
/* 143 */     for (int i = 0; i < seq.size(); i++) {
/* 144 */       double x = seq.getX(i);
/* 145 */       double y = seq.getY(i);
/* 146 */       expandToInclude(x, y);
/*     */     } 
/* 148 */     return this;
/*     */   }
/*     */   
/*     */   public OctagonalEnvelope expandToInclude(OctagonalEnvelope oct) {
/* 153 */     if (oct.isNull())
/* 153 */       return this; 
/* 155 */     if (isNull()) {
/* 156 */       this.minX = oct.minX;
/* 157 */       this.maxX = oct.maxX;
/* 158 */       this.minY = oct.minY;
/* 159 */       this.maxY = oct.maxY;
/* 160 */       this.minA = oct.minA;
/* 161 */       this.maxA = oct.maxA;
/* 162 */       this.minB = oct.minB;
/* 163 */       this.maxB = oct.maxB;
/* 164 */       return this;
/*     */     } 
/* 166 */     if (oct.minX < this.minX)
/* 166 */       this.minX = oct.minX; 
/* 167 */     if (oct.maxX > this.maxX)
/* 167 */       this.maxX = oct.maxX; 
/* 168 */     if (oct.minY < this.minY)
/* 168 */       this.minY = oct.minY; 
/* 169 */     if (oct.maxY > this.maxY)
/* 169 */       this.maxY = oct.maxY; 
/* 170 */     if (oct.minA < this.minA)
/* 170 */       this.minA = oct.minA; 
/* 171 */     if (oct.maxA > this.maxA)
/* 171 */       this.maxA = oct.maxA; 
/* 172 */     if (oct.minB < this.minB)
/* 172 */       this.minB = oct.minB; 
/* 173 */     if (oct.maxB > this.maxB)
/* 173 */       this.maxB = oct.maxB; 
/* 174 */     return this;
/*     */   }
/*     */   
/*     */   public OctagonalEnvelope expandToInclude(Coordinate p) {
/* 179 */     expandToInclude(p.x, p.y);
/* 180 */     return this;
/*     */   }
/*     */   
/*     */   public OctagonalEnvelope expandToInclude(Envelope env) {
/* 185 */     expandToInclude(env.getMinX(), env.getMinY());
/* 186 */     expandToInclude(env.getMinX(), env.getMaxY());
/* 187 */     expandToInclude(env.getMaxX(), env.getMinY());
/* 188 */     expandToInclude(env.getMaxX(), env.getMaxY());
/* 189 */     return this;
/*     */   }
/*     */   
/*     */   public OctagonalEnvelope expandToInclude(double x, double y) {
/* 194 */     double A = computeA(x, y);
/* 195 */     double B = computeB(x, y);
/* 197 */     if (isNull()) {
/* 198 */       this.minX = x;
/* 199 */       this.maxX = x;
/* 200 */       this.minY = y;
/* 201 */       this.maxY = y;
/* 202 */       this.minA = A;
/* 203 */       this.maxA = A;
/* 204 */       this.minB = B;
/* 205 */       this.maxB = B;
/*     */     } else {
/* 208 */       if (x < this.minX)
/* 208 */         this.minX = x; 
/* 209 */       if (x > this.maxX)
/* 209 */         this.maxX = x; 
/* 210 */       if (y < this.minY)
/* 210 */         this.minY = y; 
/* 211 */       if (y > this.maxY)
/* 211 */         this.maxY = y; 
/* 212 */       if (A < this.minA)
/* 212 */         this.minA = A; 
/* 213 */       if (A > this.maxA)
/* 213 */         this.maxA = A; 
/* 214 */       if (B < this.minB)
/* 214 */         this.minB = B; 
/* 215 */       if (B > this.maxB)
/* 215 */         this.maxB = B; 
/*     */     } 
/* 217 */     return this;
/*     */   }
/*     */   
/*     */   public void expandBy(double distance) {
/* 222 */     if (isNull())
/*     */       return; 
/* 224 */     double diagonalDistance = SQRT2 * distance;
/* 226 */     this.minX -= distance;
/* 227 */     this.maxX += distance;
/* 228 */     this.minY -= distance;
/* 229 */     this.maxY += distance;
/* 230 */     this.minA -= diagonalDistance;
/* 231 */     this.maxA += diagonalDistance;
/* 232 */     this.minB -= diagonalDistance;
/* 233 */     this.maxB += diagonalDistance;
/* 235 */     if (!isValid())
/* 236 */       setToNull(); 
/*     */   }
/*     */   
/*     */   private boolean isValid() {
/* 246 */     if (isNull())
/* 246 */       return true; 
/* 247 */     return (this.minX <= this.maxX && this.minY <= this.maxY && this.minA <= this.maxA && this.minB <= this.maxB);
/*     */   }
/*     */   
/*     */   public boolean intersects(OctagonalEnvelope other) {
/* 255 */     if (isNull() || other.isNull())
/* 255 */       return false; 
/* 257 */     if (this.minX > other.maxX)
/* 257 */       return false; 
/* 258 */     if (this.maxX < other.minX)
/* 258 */       return false; 
/* 259 */     if (this.minY > other.maxY)
/* 259 */       return false; 
/* 260 */     if (this.maxY < other.minY)
/* 260 */       return false; 
/* 261 */     if (this.minA > other.maxA)
/* 261 */       return false; 
/* 262 */     if (this.maxA < other.minA)
/* 262 */       return false; 
/* 263 */     if (this.minB > other.maxB)
/* 263 */       return false; 
/* 264 */     if (this.maxB < other.minB)
/* 264 */       return false; 
/* 265 */     return true;
/*     */   }
/*     */   
/*     */   public boolean intersects(Coordinate p) {
/* 270 */     if (this.minX > p.x)
/* 270 */       return false; 
/* 271 */     if (this.maxX < p.x)
/* 271 */       return false; 
/* 272 */     if (this.minY > p.y)
/* 272 */       return false; 
/* 273 */     if (this.maxY < p.y)
/* 273 */       return false; 
/* 275 */     double A = computeA(p.x, p.y);
/* 276 */     double B = computeB(p.x, p.y);
/* 277 */     if (this.minA > A)
/* 277 */       return false; 
/* 278 */     if (this.maxA < A)
/* 278 */       return false; 
/* 279 */     if (this.minB > B)
/* 279 */       return false; 
/* 280 */     if (this.maxB < B)
/* 280 */       return false; 
/* 281 */     return true;
/*     */   }
/*     */   
/*     */   public boolean contains(OctagonalEnvelope other) {
/* 286 */     if (isNull() || other.isNull())
/* 286 */       return false; 
/* 288 */     return (other.minX >= this.minX && other.maxX <= this.maxX && other.minY >= this.minY && other.maxY <= this.maxY && other.minA >= this.minA && other.maxA <= this.maxA && other.minB >= this.minB && other.maxB <= this.maxB);
/*     */   }
/*     */   
/*     */   public Geometry toGeometry(GeometryFactory geomFactory) {
/* 300 */     if (isNull())
/* 301 */       return geomFactory.createPoint((CoordinateSequence)null); 
/* 304 */     Coordinate px00 = new Coordinate(this.minX, this.minA - this.minX);
/* 305 */     Coordinate px01 = new Coordinate(this.minX, this.minX - this.minB);
/* 307 */     Coordinate px10 = new Coordinate(this.maxX, this.maxX - this.maxB);
/* 308 */     Coordinate px11 = new Coordinate(this.maxX, this.maxA - this.maxX);
/* 310 */     Coordinate py00 = new Coordinate(this.minA - this.minY, this.minY);
/* 311 */     Coordinate py01 = new Coordinate(this.minY + this.maxB, this.minY);
/* 313 */     Coordinate py10 = new Coordinate(this.maxY + this.minB, this.maxY);
/* 314 */     Coordinate py11 = new Coordinate(this.maxA - this.maxY, this.maxY);
/* 316 */     PrecisionModel pm = geomFactory.getPrecisionModel();
/* 317 */     pm.makePrecise(px00);
/* 318 */     pm.makePrecise(px01);
/* 319 */     pm.makePrecise(px10);
/* 320 */     pm.makePrecise(px11);
/* 321 */     pm.makePrecise(py00);
/* 322 */     pm.makePrecise(py01);
/* 323 */     pm.makePrecise(py10);
/* 324 */     pm.makePrecise(py11);
/* 326 */     CoordinateList coordList = new CoordinateList();
/* 327 */     coordList.add(px00, false);
/* 328 */     coordList.add(px01, false);
/* 329 */     coordList.add(py10, false);
/* 330 */     coordList.add(py11, false);
/* 331 */     coordList.add(px11, false);
/* 332 */     coordList.add(px10, false);
/* 333 */     coordList.add(py01, false);
/* 334 */     coordList.add(py00, false);
/* 336 */     if (coordList.size() == 1)
/* 337 */       return geomFactory.createPoint(px00); 
/* 339 */     if (coordList.size() == 2) {
/* 340 */       Coordinate[] arrayOfCoordinate = coordList.toCoordinateArray();
/* 341 */       return geomFactory.createLineString(arrayOfCoordinate);
/*     */     } 
/* 344 */     coordList.add(px00, false);
/* 345 */     Coordinate[] pts = coordList.toCoordinateArray();
/* 346 */     return geomFactory.createPolygon(geomFactory.createLinearRing(pts), null);
/*     */   }
/*     */   
/*     */   private class BoundingOctagonComponentFilter implements GeometryComponentFilter {
/*     */     private BoundingOctagonComponentFilter() {}
/*     */     
/*     */     public void filter(Geometry geom) {
/* 354 */       if (geom instanceof LineString) {
/* 355 */         OctagonalEnvelope.this.expandToInclude(((LineString)geom).getCoordinateSequence());
/* 357 */       } else if (geom instanceof Point) {
/* 358 */         OctagonalEnvelope.this.expandToInclude(((Point)geom).getCoordinateSequence());
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\geom\OctagonalEnvelope.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */