/*     */ package com.vividsolutions.jts.util;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFilter;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import com.vividsolutions.jts.geom.util.AffineTransformation;
/*     */ 
/*     */ public class GeometricShapeFactory {
/*     */   protected GeometryFactory geomFact;
/*     */   
/*  59 */   protected PrecisionModel precModel = null;
/*     */   
/*  60 */   protected Dimensions dim = new Dimensions();
/*     */   
/*  61 */   protected int nPts = 100;
/*     */   
/*  66 */   protected double rotationAngle = 0.0D;
/*     */   
/*     */   public GeometricShapeFactory() {
/*  74 */     this(new GeometryFactory());
/*     */   }
/*     */   
/*     */   public GeometricShapeFactory(GeometryFactory geomFact) {
/*  85 */     this.geomFact = geomFact;
/*  86 */     this.precModel = geomFact.getPrecisionModel();
/*     */   }
/*     */   
/*     */   public void setEnvelope(Envelope env) {
/*  91 */     this.dim.setEnvelope(env);
/*     */   }
/*     */   
/*     */   public void setBase(Coordinate base) {
/* 101 */     this.dim.setBase(base);
/*     */   }
/*     */   
/*     */   public void setCentre(Coordinate centre) {
/* 108 */     this.dim.setCentre(centre);
/*     */   }
/*     */   
/*     */   public void setNumPoints(int nPts) {
/* 115 */     this.nPts = nPts;
/*     */   }
/*     */   
/*     */   public void setSize(double size) {
/* 122 */     this.dim.setSize(size);
/*     */   }
/*     */   
/*     */   public void setWidth(double width) {
/* 129 */     this.dim.setWidth(width);
/*     */   }
/*     */   
/*     */   public void setHeight(double height) {
/* 136 */     this.dim.setHeight(height);
/*     */   }
/*     */   
/*     */   public void setRotation(double radians) {
/* 146 */     this.rotationAngle = radians;
/*     */   }
/*     */   
/*     */   protected Geometry rotate(Geometry geom) {
/* 151 */     if (this.rotationAngle != 0.0D) {
/* 152 */       AffineTransformation trans = AffineTransformation.rotationInstance(this.rotationAngle, (this.dim.getCentre()).x, (this.dim.getCentre()).y);
/* 154 */       geom.apply((CoordinateSequenceFilter)trans);
/*     */     } 
/* 156 */     return geom;
/*     */   }
/*     */   
/*     */   public Polygon createRectangle() {
/* 168 */     int ipt = 0;
/* 169 */     int nSide = this.nPts / 4;
/* 170 */     if (nSide < 1)
/* 170 */       nSide = 1; 
/* 171 */     double XsegLen = this.dim.getEnvelope().getWidth() / nSide;
/* 172 */     double YsegLen = this.dim.getEnvelope().getHeight() / nSide;
/* 174 */     Coordinate[] pts = new Coordinate[4 * nSide + 1];
/* 175 */     Envelope env = this.dim.getEnvelope();
/*     */     int i;
/* 180 */     for (i = 0; i < nSide; i++) {
/* 181 */       double x = env.getMinX() + i * XsegLen;
/* 182 */       double y = env.getMinY();
/* 183 */       pts[ipt++] = coord(x, y);
/*     */     } 
/* 185 */     for (i = 0; i < nSide; i++) {
/* 186 */       double x = env.getMaxX();
/* 187 */       double y = env.getMinY() + i * YsegLen;
/* 188 */       pts[ipt++] = coord(x, y);
/*     */     } 
/* 190 */     for (i = 0; i < nSide; i++) {
/* 191 */       double x = env.getMaxX() - i * XsegLen;
/* 192 */       double y = env.getMaxY();
/* 193 */       pts[ipt++] = coord(x, y);
/*     */     } 
/* 195 */     for (i = 0; i < nSide; i++) {
/* 196 */       double x = env.getMinX();
/* 197 */       double y = env.getMaxY() - i * YsegLen;
/* 198 */       pts[ipt++] = coord(x, y);
/*     */     } 
/* 200 */     pts[ipt++] = new Coordinate(pts[0]);
/* 202 */     LinearRing ring = this.geomFact.createLinearRing(pts);
/* 203 */     Polygon poly = this.geomFact.createPolygon(ring, null);
/* 204 */     return (Polygon)rotate((Geometry)poly);
/*     */   }
/*     */   
/*     */   public Polygon createCircle() {
/* 215 */     return createEllipse();
/*     */   }
/*     */   
/*     */   public Polygon createEllipse() {
/* 228 */     Envelope env = this.dim.getEnvelope();
/* 229 */     double xRadius = env.getWidth() / 2.0D;
/* 230 */     double yRadius = env.getHeight() / 2.0D;
/* 232 */     double centreX = env.getMinX() + xRadius;
/* 233 */     double centreY = env.getMinY() + yRadius;
/* 235 */     Coordinate[] pts = new Coordinate[this.nPts + 1];
/* 236 */     int iPt = 0;
/* 237 */     for (int i = 0; i < this.nPts; i++) {
/* 238 */       double ang = i * 6.283185307179586D / this.nPts;
/* 239 */       double x = xRadius * Math.cos(ang) + centreX;
/* 240 */       double y = yRadius * Math.sin(ang) + centreY;
/* 241 */       pts[iPt++] = coord(x, y);
/*     */     } 
/* 243 */     pts[iPt] = new Coordinate(pts[0]);
/* 245 */     LinearRing ring = this.geomFact.createLinearRing(pts);
/* 246 */     Polygon poly = this.geomFact.createPolygon(ring, null);
/* 247 */     return (Polygon)rotate((Geometry)poly);
/*     */   }
/*     */   
/*     */   public Polygon createSquircle() {
/* 261 */     return createSupercircle(4.0D);
/*     */   }
/*     */   
/*     */   public Polygon createSupercircle(double power) {
/* 272 */     double recipPow = 1.0D / power;
/* 274 */     double radius = this.dim.getMinSize() / 2.0D;
/* 275 */     Coordinate centre = this.dim.getCentre();
/* 277 */     double r4 = Math.pow(radius, power);
/* 278 */     double y0 = radius;
/* 280 */     double xyInt = Math.pow(r4 / 2.0D, recipPow);
/* 282 */     int nSegsInOct = this.nPts / 8;
/* 283 */     int totPts = nSegsInOct * 8 + 1;
/* 284 */     Coordinate[] pts = new Coordinate[totPts];
/* 285 */     double xInc = xyInt / nSegsInOct;
/* 287 */     for (int i = 0; i <= nSegsInOct; i++) {
/* 288 */       double x = 0.0D;
/* 289 */       double y = y0;
/* 290 */       if (i != 0) {
/* 291 */         x = xInc * i;
/* 292 */         double x4 = Math.pow(x, power);
/* 293 */         y = Math.pow(r4 - x4, recipPow);
/*     */       } 
/* 295 */       pts[i] = coordTrans(x, y, centre);
/* 296 */       pts[2 * nSegsInOct - i] = coordTrans(y, x, centre);
/* 298 */       pts[2 * nSegsInOct + i] = coordTrans(y, -x, centre);
/* 299 */       pts[4 * nSegsInOct - i] = coordTrans(x, -y, centre);
/* 301 */       pts[4 * nSegsInOct + i] = coordTrans(-x, -y, centre);
/* 302 */       pts[6 * nSegsInOct - i] = coordTrans(-y, -x, centre);
/* 304 */       pts[6 * nSegsInOct + i] = coordTrans(-y, x, centre);
/* 305 */       pts[8 * nSegsInOct - i] = coordTrans(-x, y, centre);
/*     */     } 
/* 307 */     pts[pts.length - 1] = new Coordinate(pts[0]);
/* 309 */     LinearRing ring = this.geomFact.createLinearRing(pts);
/* 310 */     Polygon poly = this.geomFact.createPolygon(ring, null);
/* 311 */     return (Polygon)rotate((Geometry)poly);
/*     */   }
/*     */   
/*     */   public LineString createArc(double startAng, double angExtent) {
/* 328 */     Envelope env = this.dim.getEnvelope();
/* 329 */     double xRadius = env.getWidth() / 2.0D;
/* 330 */     double yRadius = env.getHeight() / 2.0D;
/* 332 */     double centreX = env.getMinX() + xRadius;
/* 333 */     double centreY = env.getMinY() + yRadius;
/* 335 */     double angSize = angExtent;
/* 336 */     if (angSize <= 0.0D || angSize > 6.283185307179586D)
/* 337 */       angSize = 6.283185307179586D; 
/* 338 */     double angInc = angSize / (this.nPts - 1);
/* 340 */     Coordinate[] pts = new Coordinate[this.nPts];
/* 341 */     int iPt = 0;
/* 342 */     for (int i = 0; i < this.nPts; i++) {
/* 343 */       double ang = startAng + i * angInc;
/* 344 */       double x = xRadius * Math.cos(ang) + centreX;
/* 345 */       double y = yRadius * Math.sin(ang) + centreY;
/* 346 */       pts[iPt++] = coord(x, y);
/*     */     } 
/* 348 */     LineString line = this.geomFact.createLineString(pts);
/* 349 */     return (LineString)rotate((Geometry)line);
/*     */   }
/*     */   
/*     */   public Polygon createArcPolygon(double startAng, double angExtent) {
/* 362 */     Envelope env = this.dim.getEnvelope();
/* 363 */     double xRadius = env.getWidth() / 2.0D;
/* 364 */     double yRadius = env.getHeight() / 2.0D;
/* 366 */     double centreX = env.getMinX() + xRadius;
/* 367 */     double centreY = env.getMinY() + yRadius;
/* 369 */     double angSize = angExtent;
/* 370 */     if (angSize <= 0.0D || angSize > 6.283185307179586D)
/* 371 */       angSize = 6.283185307179586D; 
/* 372 */     double angInc = angSize / (this.nPts - 1);
/* 376 */     Coordinate[] pts = new Coordinate[this.nPts + 2];
/* 378 */     int iPt = 0;
/* 379 */     pts[iPt++] = coord(centreX, centreY);
/* 380 */     for (int i = 0; i < this.nPts; i++) {
/* 381 */       double ang = startAng + angInc * i;
/* 383 */       double x = xRadius * Math.cos(ang) + centreX;
/* 384 */       double y = yRadius * Math.sin(ang) + centreY;
/* 385 */       pts[iPt++] = coord(x, y);
/*     */     } 
/* 387 */     pts[iPt++] = coord(centreX, centreY);
/* 388 */     LinearRing ring = this.geomFact.createLinearRing(pts);
/* 389 */     Polygon poly = this.geomFact.createPolygon(ring, null);
/* 390 */     return (Polygon)rotate((Geometry)poly);
/*     */   }
/*     */   
/*     */   protected Coordinate coord(double x, double y) {
/* 395 */     Coordinate pt = new Coordinate(x, y);
/* 396 */     this.precModel.makePrecise(pt);
/* 397 */     return pt;
/*     */   }
/*     */   
/*     */   protected Coordinate coordTrans(double x, double y, Coordinate trans) {
/* 402 */     return coord(x + trans.x, y + trans.y);
/*     */   }
/*     */   
/*     */   protected class Dimensions {
/*     */     public Coordinate base;
/*     */     
/*     */     public Coordinate centre;
/*     */     
/*     */     public double width;
/*     */     
/*     */     public double height;
/*     */     
/*     */     public void setBase(Coordinate base) {
/* 412 */       this.base = base;
/*     */     }
/*     */     
/*     */     public Coordinate getBase() {
/* 413 */       return this.base;
/*     */     }
/*     */     
/*     */     public void setCentre(Coordinate centre) {
/* 415 */       this.centre = centre;
/*     */     }
/*     */     
/*     */     public Coordinate getCentre() {
/* 418 */       if (this.centre == null)
/* 419 */         this.centre = new Coordinate(this.base.x + this.width / 2.0D, this.base.y + this.height / 2.0D); 
/* 421 */       return this.centre;
/*     */     }
/*     */     
/*     */     public void setSize(double size) {
/* 426 */       this.height = size;
/* 427 */       this.width = size;
/*     */     }
/*     */     
/*     */     public double getMinSize() {
/* 432 */       return Math.min(this.width, this.height);
/*     */     }
/*     */     
/*     */     public void setWidth(double width) {
/* 434 */       this.width = width;
/*     */     }
/*     */     
/*     */     public double getWidth() {
/* 435 */       return this.width;
/*     */     }
/*     */     
/*     */     public double getHeight() {
/* 436 */       return this.height;
/*     */     }
/*     */     
/*     */     public void setHeight(double height) {
/* 438 */       this.height = height;
/*     */     }
/*     */     
/*     */     public void setEnvelope(Envelope env) {
/* 442 */       this.width = env.getWidth();
/* 443 */       this.height = env.getHeight();
/* 444 */       this.base = new Coordinate(env.getMinX(), env.getMinY());
/* 445 */       this.centre = new Coordinate(env.centre());
/*     */     }
/*     */     
/*     */     public Envelope getEnvelope() {
/* 449 */       if (this.base != null)
/* 450 */         return new Envelope(this.base.x, this.base.x + this.width, this.base.y, this.base.y + this.height); 
/* 452 */       if (this.centre != null)
/* 453 */         return new Envelope(this.centre.x - this.width / 2.0D, this.centre.x + this.width / 2.0D, this.centre.y - this.height / 2.0D, this.centre.y + this.height / 2.0D); 
/* 456 */       return new Envelope(0.0D, this.width, 0.0D, this.height);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jt\\util\GeometricShapeFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */