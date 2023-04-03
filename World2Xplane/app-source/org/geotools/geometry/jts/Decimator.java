/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.awt.Rectangle;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.geotools.util.logging.Logging;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public final class Decimator {
/*  48 */   private static final Logger LOGGER = Logging.getLogger(Decimator.class);
/*     */   
/*     */   static final double DP_THRESHOLD;
/*     */   
/*     */   private static final double EPS = 1.0E-9D;
/*     */   
/*     */   static {
/*  53 */     int threshold = -1;
/*  54 */     String sthreshold = System.getProperty("org.geotools.decimate.dpThreshold");
/*  55 */     if (sthreshold != null)
/*     */       try {
/*  57 */         threshold = Integer.parseInt(sthreshold);
/*  58 */       } catch (Throwable t) {
/*  59 */         LOGGER.log(Level.WARNING, "Invalid value for org.geotools.decimate.dpThreshold, should be a positive integer but is: " + sthreshold);
/*     */       }  
/*  63 */     DP_THRESHOLD = threshold;
/*     */   }
/*     */   
/*  68 */   private double spanx = -1.0D;
/*     */   
/*  70 */   private double spany = -1.0D;
/*     */   
/*     */   public Decimator(MathTransform screenToWorld, Rectangle paintArea, double pixelDistance) {
/*  82 */     if (screenToWorld != null && pixelDistance > 0.0D) {
/*     */       try {
/*  84 */         double[] spans = computeGeneralizationDistances(screenToWorld, paintArea, pixelDistance);
/*  85 */         this.spanx = spans[0];
/*  86 */         this.spany = spans[1];
/*  87 */       } catch (TransformException e) {
/*  88 */         throw new RuntimeException("Could not perform the generalization spans computation", e);
/*     */       } 
/*     */     } else {
/*  91 */       this.spanx = 1.0D;
/*  92 */       this.spany = 1.0D;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Decimator(MathTransform screenToWorld, Rectangle paintArea) {
/* 122 */     this(screenToWorld, paintArea, 0.8D);
/*     */   }
/*     */   
/*     */   public static double[] computeGeneralizationDistances(MathTransform screenToWorld, Rectangle paintArea, double pixelDistance) throws TransformException {
/*     */     try {
/* 139 */       double[] spans = getGeneralizationSpans(paintArea.x, paintArea.y, screenToWorld);
/* 142 */       for (int i = 0; i < 2; i++) {
/* 143 */         for (int j = 0; j < 2; j++) {
/* 144 */           double[] ns = getGeneralizationSpans(paintArea.x + (paintArea.width * i) / 2.0D, paintArea.y + paintArea.height / 2.0D, screenToWorld);
/* 146 */           if (ns[0] < spans[0])
/* 147 */             spans[0] = ns[0]; 
/* 148 */           if (ns[1] < spans[1])
/* 149 */             spans[1] = ns[1]; 
/*     */         } 
/*     */       } 
/* 152 */       spans[0] = spans[0] * pixelDistance;
/* 153 */       spans[1] = spans[1] * pixelDistance;
/* 154 */       return spans;
/* 155 */     } catch (TransformException e) {
/* 157 */       return new double[] { 0.0D, 0.0D };
/*     */     } 
/*     */   }
/*     */   
/*     */   static double[] getGeneralizationSpans(double x, double y, MathTransform transform) throws TransformException {
/* 170 */     double[] original = { x - 0.5D, y - 0.5D, x + 0.5D, y + 0.5D };
/* 173 */     double[] transformed = new double[4];
/* 174 */     transform.transform(original, 0, transformed, 0, 2);
/* 175 */     double[] spans = new double[2];
/* 176 */     spans[0] = Math.abs(transformed[0] - transformed[2]);
/* 177 */     spans[1] = Math.abs(transformed[1] - transformed[3]);
/* 178 */     return spans;
/*     */   }
/*     */   
/*     */   public Decimator(MathTransform screenToWorld) {
/* 189 */     this(screenToWorld, new Rectangle());
/*     */   }
/*     */   
/*     */   public Decimator(double spanx, double spany) {
/* 193 */     this.spanx = spanx;
/* 194 */     this.spany = spany;
/*     */   }
/*     */   
/*     */   public final void decimateTransformGeneralize(Geometry geometry, MathTransform transform) throws TransformException {
/* 199 */     if (geometry instanceof GeometryCollection) {
/* 200 */       GeometryCollection collection = (GeometryCollection)geometry;
/* 201 */       int length = collection.getNumGeometries();
/* 202 */       for (int i = 0; i < length; i++)
/* 203 */         decimateTransformGeneralize(collection.getGeometryN(i), transform); 
/* 206 */     } else if (geometry instanceof Point) {
/* 207 */       LiteCoordinateSequence seq = (LiteCoordinateSequence)((Point)geometry).getCoordinateSequence();
/* 209 */       decimateTransformGeneralize(seq, transform, false);
/* 210 */     } else if (geometry instanceof Polygon) {
/* 211 */       Polygon polygon = (Polygon)geometry;
/* 212 */       decimateTransformGeneralize((Geometry)polygon.getExteriorRing(), transform);
/* 213 */       int length = polygon.getNumInteriorRing();
/* 214 */       for (int i = 0; i < length; i++)
/* 215 */         decimateTransformGeneralize((Geometry)polygon.getInteriorRingN(i), transform); 
/* 218 */     } else if (geometry instanceof LineString) {
/* 219 */       LineString ls = (LineString)geometry;
/* 220 */       LiteCoordinateSequence seq = (LiteCoordinateSequence)ls.getCoordinateSequence();
/* 221 */       boolean loop = ls instanceof com.vividsolutions.jts.geom.LinearRing;
/* 222 */       if (!loop && seq.size() > 1) {
/* 223 */         double x0 = seq.getOrdinate(0, 0);
/* 224 */         double y0 = seq.getOrdinate(0, 1);
/* 225 */         double x1 = seq.getOrdinate(seq.size() - 1, 0);
/* 226 */         double y1 = seq.getOrdinate(seq.size() - 1, 1);
/* 227 */         loop = (Math.abs(x0 - x1) < 1.0E-9D && Math.abs(y0 - y1) < 1.0E-9D);
/*     */       } 
/* 230 */       decimateTransformGeneralize(seq, transform, loop);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void decimate(Geometry geom) {
/* 238 */     if (this.spanx == -1.0D)
/*     */       return; 
/* 240 */     if (geom instanceof com.vividsolutions.jts.geom.MultiPoint)
/*     */       return; 
/* 245 */     if (geom instanceof GeometryCollection) {
/* 250 */       GeometryCollection collection = (GeometryCollection)geom;
/* 251 */       int numGeometries = collection.getNumGeometries();
/* 252 */       for (int i = 0; i < numGeometries; i++)
/* 253 */         decimate(collection.getGeometryN(i)); 
/* 255 */     } else if (geom instanceof LineString) {
/* 256 */       LineString line = (LineString)geom;
/* 257 */       LiteCoordinateSequence seq = (LiteCoordinateSequence)line.getCoordinateSequence();
/* 259 */       if (decimateOnEnvelope((Geometry)line, seq))
/*     */         return; 
/* 262 */       decimate((Geometry)line, seq);
/* 263 */     } else if (geom instanceof Polygon) {
/* 264 */       Polygon line = (Polygon)geom;
/* 265 */       decimate((Geometry)line.getExteriorRing());
/* 266 */       int numRings = line.getNumInteriorRing();
/* 267 */       for (int i = 0; i < numRings; i++)
/* 268 */         decimate((Geometry)line.getInteriorRingN(i)); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean decimateOnEnvelope(Geometry geom, LiteCoordinateSequence seq) {
/* 278 */     Envelope env = geom.getEnvelopeInternal();
/* 279 */     if (env.getWidth() <= this.spanx && env.getHeight() <= this.spany) {
/* 280 */       if (geom instanceof com.vividsolutions.jts.geom.LinearRing) {
/* 281 */         decimateRingFully(seq);
/* 282 */         return true;
/*     */       } 
/* 284 */       double[] coords = seq.getArray();
/* 285 */       int dim = seq.getDimension();
/* 286 */       double[] newcoords = new double[dim * 2];
/* 287 */       for (int i = 0; i < dim; i++) {
/* 288 */         newcoords[i] = coords[i];
/* 289 */         newcoords[dim + i] = coords[coords.length - dim + i];
/*     */       } 
/* 291 */       seq.setArray(newcoords);
/* 292 */       return true;
/*     */     } 
/* 295 */     return false;
/*     */   }
/*     */   
/*     */   private void decimateRingFully(LiteCoordinateSequence seq) {
/* 303 */     double[] coords = seq.getArray();
/* 304 */     int dim = seq.getDimension();
/* 307 */     if (seq.size() <= 4)
/*     */       return; 
/* 310 */     double[] newcoords = new double[dim * 4];
/* 313 */     for (int i = 0; i < dim; i++) {
/* 314 */       newcoords[i] = coords[i];
/* 315 */       newcoords[dim + i] = coords[dim + i];
/* 316 */       newcoords[dim * 2 + i] = coords[coords.length - dim * 2 + i];
/* 317 */       newcoords[dim * 3 + i] = coords[coords.length - dim + i];
/*     */     } 
/* 319 */     seq.setArray(newcoords);
/*     */   }
/*     */   
/*     */   private final void decimateTransformGeneralize(LiteCoordinateSequence seq, MathTransform transform, boolean ring) throws TransformException {
/* 333 */     int ncoords = seq.size();
/* 334 */     double[] coords = null;
/* 335 */     if (transform != null) {
/* 336 */       coords = seq.getOrdinateArray(transform.getSourceDimensions());
/*     */     } else {
/* 338 */       coords = seq.getXYArray();
/*     */     } 
/* 341 */     if (ncoords < 2) {
/* 342 */       if (ncoords == 1) {
/* 345 */         if (transform != null) {
/* 346 */           transform.transform(coords, 0, coords, 0, 1);
/* 347 */           seq.setArray(coords, 2);
/*     */         } 
/*     */         return;
/*     */       } 
/*     */       return;
/*     */     } 
/* 356 */     if (this.spanx == -1.0D && this.spany == -1.0D) {
/* 358 */       if (transform != null && !transform.isIdentity()) {
/* 359 */         transform.transform(coords, 0, coords, 0, ncoords);
/* 360 */         seq.setArray(coords, 2);
/*     */       } 
/*     */       return;
/*     */     } 
/* 366 */     int actualCoords = spanBasedGeneralize(ncoords, coords);
/* 367 */     if (DP_THRESHOLD > 0.0D && actualCoords > DP_THRESHOLD)
/* 368 */       actualCoords = dpBasedGeneralize(actualCoords, coords, Math.min(this.spanx, this.spany) * Math.min(this.spanx, this.spany)); 
/* 372 */     if (ring && actualCoords <= 3)
/* 373 */       if (coords.length > 6) {
/* 375 */         coords[2] = coords[2];
/* 376 */         coords[3] = coords[3];
/* 377 */         coords[4] = coords[4];
/* 378 */         coords[5] = coords[5];
/* 379 */         actualCoords = 3;
/* 380 */       } else if (coords.length > 4) {
/* 382 */         coords[2] = coords[2];
/* 383 */         coords[3] = coords[3];
/* 384 */         actualCoords = 2;
/*     */       }  
/* 389 */     coords[actualCoords * 2] = coords[(ncoords - 1) * 2];
/* 390 */     coords[actualCoords * 2 + 1] = coords[(ncoords - 1) * 2 + 1];
/* 391 */     actualCoords++;
/* 394 */     if (transform != null && !transform.isIdentity())
/* 397 */       transform.transform(coords, 0, coords, 0, actualCoords); 
/* 401 */     if (actualCoords * 2 < coords.length) {
/* 402 */       double[] seqDouble = new double[2 * actualCoords];
/* 403 */       System.arraycopy(coords, 0, seqDouble, 0, actualCoords * 2);
/* 404 */       seq.setArray(seqDouble, 2);
/*     */     } else {
/* 406 */       seq.setArray(coords, 2);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int spanBasedGeneralize(int ncoords, double[] coords) {
/* 411 */     int actualCoords = 1;
/* 412 */     double lastX = coords[0];
/* 413 */     double lastY = coords[1];
/* 414 */     for (int t = 1; t < ncoords - 1; t++) {
/* 416 */       double x = coords[t * 2];
/* 417 */       double y = coords[t * 2 + 1];
/* 418 */       if (Math.abs(x - lastX) > this.spanx || Math.abs(y - lastY) > this.spany) {
/* 419 */         coords[actualCoords * 2] = x;
/* 420 */         coords[actualCoords * 2 + 1] = y;
/* 421 */         lastX = x;
/* 422 */         lastY = y;
/* 423 */         actualCoords++;
/*     */       } 
/*     */     } 
/* 426 */     return actualCoords;
/*     */   }
/*     */   
/*     */   private int dpBasedGeneralize(int ncoords, double[] coords, double maxDistance) {
/* 430 */     while (coords[0] == coords[(ncoords - 1) * 2] && coords[1] == coords[2 * ncoords - 1] && ncoords > 0)
/* 431 */       ncoords--; 
/* 433 */     if (ncoords == 0)
/* 434 */       return 0; 
/* 437 */     dpSimplifySection(0, ncoords - 1, coords, maxDistance);
/* 438 */     int actualCoords = 1;
/* 439 */     for (int i = 1; i < ncoords - 1; i++) {
/* 440 */       double x = coords[i * 2];
/* 441 */       double y = coords[i * 2 + 1];
/* 442 */       if (!Double.isNaN(x)) {
/* 443 */         coords[actualCoords * 2] = x;
/* 444 */         coords[actualCoords * 2 + 1] = y;
/* 445 */         actualCoords++;
/*     */       } 
/*     */     } 
/* 449 */     return actualCoords;
/*     */   }
/*     */   
/*     */   private void dpSimplifySection(int first, int last, double[] coords, double maxDistanceSquared) {
/* 453 */     if (last - 1 <= first)
/*     */       return; 
/* 457 */     double x0 = coords[first * 2];
/* 458 */     double y0 = coords[first * 2 + 1];
/* 459 */     double x1 = coords[last * 2];
/* 460 */     double y1 = coords[last * 2 + 1];
/* 461 */     double dx = x1 - x0;
/* 462 */     double dy = y1 - y0;
/* 463 */     double ls = dx * dx + dy * dy;
/* 465 */     int idx = -1;
/* 466 */     double dsmax = -1.0D;
/*     */     int i;
/* 467 */     for (i = first + 1; i < last; i++) {
/* 468 */       double ds, x = coords[i * 2];
/* 469 */       double y = coords[i * 2 + 1];
/* 472 */       double r = ((x - x0) * dx + (y - y0) * dy) / ls;
/* 473 */       if (r <= 0.0D) {
/* 474 */         ds = (x - x0) * (x - x0) + (y - y0) * (y - y0);
/* 475 */       } else if (r >= 1.0D) {
/* 476 */         ds = (x - x1) * (x - x1) + (y - y1) * (y - y1);
/*     */       } else {
/* 478 */         double s = ((y0 - y) * dx - (x0 - x) * dy) / ls;
/* 479 */         ds = s * s * ls;
/*     */       } 
/* 482 */       if (idx == -1 || ds > dsmax) {
/* 483 */         idx = i;
/* 484 */         dsmax = ds;
/*     */       } 
/*     */     } 
/* 488 */     if (dsmax <= maxDistanceSquared) {
/* 489 */       for (i = first + 1; i < last; i++) {
/* 490 */         coords[i * 2] = Double.NaN;
/* 491 */         coords[i * 2 + 1] = Double.NaN;
/*     */       } 
/*     */     } else {
/* 494 */       dpSimplifySection(first, idx, coords, maxDistanceSquared);
/* 495 */       dpSimplifySection(idx, last, coords, maxDistanceSquared);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void decimate(Geometry g, LiteCoordinateSequence seq) {
/* 500 */     double[] coords = seq.getXYArray();
/* 501 */     int dim = seq.getDimension();
/* 502 */     int numDoubles = coords.length;
/* 503 */     int readDoubles = 0;
/*     */     int currentDoubles;
/* 505 */     for (currentDoubles = 0; currentDoubles < numDoubles; currentDoubles += dim) {
/* 506 */       if (currentDoubles >= dim && currentDoubles < numDoubles - dim) {
/* 507 */         double prevx = coords[readDoubles - dim];
/* 508 */         double currx = coords[currentDoubles];
/* 509 */         double diffx = Math.abs(prevx - currx);
/* 510 */         double prevy = coords[readDoubles - dim + 1];
/* 511 */         double curry = coords[currentDoubles + 1];
/* 512 */         double diffy = Math.abs(prevy - curry);
/* 513 */         if (diffx > this.spanx || diffy > this.spany)
/* 514 */           readDoubles = copyCoordinate(coords, dim, readDoubles, currentDoubles); 
/*     */       } else {
/* 518 */         readDoubles = copyCoordinate(coords, dim, readDoubles, currentDoubles);
/*     */       } 
/*     */     } 
/* 522 */     if (g instanceof com.vividsolutions.jts.geom.LinearRing && readDoubles < dim * 4) {
/* 523 */       decimateRingFully(seq);
/* 525 */     } else if (readDoubles < numDoubles) {
/* 526 */       double[] newCoords = new double[readDoubles];
/* 527 */       System.arraycopy(coords, 0, newCoords, 0, readDoubles);
/* 528 */       seq.setArray(newCoords);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int copyCoordinate(double[] coords, int dimension, int readDoubles, int currentDoubles) {
/* 541 */     for (int i = 0; i < dimension; i++)
/* 542 */       coords[readDoubles + i] = coords[currentDoubles + i]; 
/* 544 */     readDoubles += dimension;
/* 545 */     return readDoubles;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\Decimator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */