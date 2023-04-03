/*     */ package org.geotools.geometry.jts;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*     */ import com.vividsolutions.jts.geom.CoordinateSequenceFactory;
/*     */ import com.vividsolutions.jts.geom.Envelope;
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryCollection;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.LinearRing;
/*     */ import com.vividsolutions.jts.geom.Point;
/*     */ import com.vividsolutions.jts.geom.Polygon;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ public class GeometryClipper {
/*  48 */   private static int RIGHT = 2;
/*     */   
/*  50 */   private static int TOP = 8;
/*     */   
/*  52 */   private static int BOTTOM = 4;
/*     */   
/*  54 */   private static int LEFT = 1;
/*     */   
/*     */   final double xmin;
/*     */   
/*     */   final double ymin;
/*     */   
/*     */   final double xmax;
/*     */   
/*     */   final double ymax;
/*     */   
/*     */   final Envelope bounds;
/*     */   
/*     */   public Envelope getBounds() {
/*  67 */     return this.bounds;
/*     */   }
/*     */   
/*     */   public GeometryClipper(Envelope bounds) {
/*  71 */     this.xmin = bounds.getMinX();
/*  72 */     this.ymin = bounds.getMinY();
/*  73 */     this.xmax = bounds.getMaxX();
/*  74 */     this.ymax = bounds.getMaxY();
/*  75 */     this.bounds = bounds;
/*     */   }
/*     */   
/*     */   public Geometry clip(Geometry g, boolean ensureValid) {
/*  90 */     if (g == null)
/*  91 */       return null; 
/*  93 */     Envelope geomEnvelope = g.getEnvelopeInternal();
/*  94 */     if (geomEnvelope.isNull())
/*  95 */       return null; 
/*  97 */     if (this.bounds.contains(geomEnvelope))
/*  98 */       return g; 
/*  99 */     if (!this.bounds.intersects(geomEnvelope))
/* 100 */       return null; 
/* 104 */     if (g instanceof LineString)
/* 105 */       return clipLineString((LineString)g); 
/* 106 */     if (g instanceof Polygon) {
/* 107 */       if (ensureValid) {
/* 108 */         GeometryFactory gf = g.getFactory();
/* 109 */         CoordinateSequenceFactory csf = gf.getCoordinateSequenceFactory();
/* 110 */         return g.intersection((Geometry)gf.createPolygon(buildBoundsString(gf, csf), null));
/*     */       } 
/* 112 */       return clipPolygon((Polygon)g);
/*     */     } 
/* 114 */     if (g instanceof GeometryCollection)
/* 115 */       return clipCollection((GeometryCollection)g, ensureValid); 
/* 118 */     return g;
/*     */   }
/*     */   
/*     */   private int computeOutCode(double x, double y, double xmin, double ymin, double xmax, double ymax) {
/* 134 */     int code = 0;
/* 135 */     if (y > ymax) {
/* 136 */       code |= TOP;
/* 137 */     } else if (y < ymin) {
/* 138 */       code |= BOTTOM;
/*     */     } 
/* 139 */     if (x > xmax) {
/* 140 */       code |= RIGHT;
/* 141 */     } else if (x < xmin) {
/* 142 */       code |= LEFT;
/*     */     } 
/* 143 */     return code;
/*     */   }
/*     */   
/*     */   private double[] clipSegment(double[] segment) {
/* 153 */     double x0 = segment[0];
/* 154 */     double y0 = segment[1];
/* 155 */     double x1 = segment[2];
/* 156 */     double y1 = segment[3];
/* 159 */     int outcode0 = computeOutCode(x0, y0, this.xmin, this.ymin, this.xmax, this.ymax);
/* 160 */     int outcode1 = computeOutCode(x1, y1, this.xmin, this.ymin, this.xmax, this.ymax);
/* 162 */     int step = 0;
/*     */     do {
/*     */       double x, y;
/* 164 */       if ((outcode0 | outcode1) == 0) {
/* 166 */         if (x0 == x1 && y0 == y1)
/* 167 */           return null; 
/* 171 */         segment[0] = x0;
/* 172 */         segment[1] = y0;
/* 173 */         segment[2] = x1;
/* 174 */         segment[3] = y1;
/* 175 */         return segment;
/*     */       } 
/* 176 */       if ((outcode0 & outcode1) > 0)
/* 179 */         return null; 
/* 185 */       int outcodeOut = (outcode0 != 0) ? outcode0 : outcode1;
/* 193 */       if ((outcodeOut & TOP) > 0) {
/* 194 */         x = x0 + (x1 - x0) * (this.ymax - y0) / (y1 - y0);
/* 195 */         y = this.ymax;
/* 196 */       } else if ((outcodeOut & BOTTOM) > 0) {
/* 197 */         x = x0 + (x1 - x0) * (this.ymin - y0) / (y1 - y0);
/* 198 */         y = this.ymin;
/* 199 */       } else if ((outcodeOut & RIGHT) > 0) {
/* 200 */         y = y0 + (y1 - y0) * (this.xmax - x0) / (x1 - x0);
/* 201 */         x = this.xmax;
/*     */       } else {
/* 203 */         y = y0 + (y1 - y0) * (this.xmin - x0) / (x1 - x0);
/* 204 */         x = this.xmin;
/*     */       } 
/* 208 */       if (outcodeOut == outcode0) {
/* 209 */         x0 = x;
/* 210 */         y0 = y;
/* 211 */         outcode0 = computeOutCode(x0, y0, this.xmin, this.ymin, this.xmax, this.ymax);
/*     */       } else {
/* 213 */         x1 = x;
/* 214 */         y1 = y;
/* 215 */         outcode1 = computeOutCode(x1, y1, this.xmin, this.ymin, this.xmax, this.ymax);
/*     */       } 
/* 219 */       ++step;
/* 220 */     } while (step < 5);
/* 224 */     throw new RuntimeException("Algorithm did not converge");
/*     */   }
/*     */   
/*     */   private boolean outside(double x0, double y0, double x1, double y1) {
/* 232 */     int outcode0 = computeOutCode(x0, y0, this.xmin, this.ymin, this.xmax, this.ymax);
/* 233 */     int outcode1 = computeOutCode(x1, y1, this.xmin, this.ymin, this.xmax, this.ymax);
/* 235 */     return ((outcode0 & outcode1) > 0);
/*     */   }
/*     */   
/*     */   private boolean contained(double x, double y) {
/* 245 */     return (x > this.xmin && x < this.xmax && y > this.ymin && y < this.ymax);
/*     */   }
/*     */   
/*     */   private Geometry clipPolygon(Polygon polygon) {
/* 255 */     GeometryFactory gf = polygon.getFactory();
/* 257 */     LinearRing exterior = (LinearRing)polygon.getExteriorRing();
/* 258 */     LinearRing shell = polygonClip(exterior);
/* 259 */     if (shell == null || shell.isEmpty())
/* 260 */       return null; 
/* 263 */     List<LinearRing> holes = new ArrayList<LinearRing>();
/* 264 */     for (int i = 0; i < polygon.getNumInteriorRing(); i++) {
/* 265 */       LinearRing hole = (LinearRing)polygon.getInteriorRingN(i);
/* 266 */       hole = polygonClip(hole);
/* 267 */       if (hole != null && !hole.isEmpty())
/* 268 */         holes.add(hole); 
/*     */     } 
/* 272 */     return (Geometry)gf.createPolygon(shell, holes.<LinearRing>toArray(new LinearRing[holes.size()]));
/*     */   }
/*     */   
/*     */   private LinearRing polygonClip(LinearRing ring) {
/* 285 */     double INFINITY = Double.MAX_VALUE;
/* 287 */     CoordinateSequence cs = ring.getCoordinateSequence();
/* 288 */     Ordinates out = new Ordinates();
/* 302 */     for (int i = 0; i < cs.size() - 1; i++) {
/* 304 */       double xIn, xOut, yIn, yOut, tOutX, tOutY, tOut1, tOut2, x0 = cs.getOrdinate(i, 0);
/* 305 */       double x1 = cs.getOrdinate(i + 1, 0);
/* 306 */       double y0 = cs.getOrdinate(i, 1);
/* 307 */       double y1 = cs.getOrdinate(i + 1, 1);
/* 310 */       double deltaX = x1 - x0;
/* 311 */       double deltaY = y1 - y0;
/* 315 */       if (deltaX > 0.0D || (deltaX == 0.0D && x0 > this.xmax)) {
/* 316 */         xIn = this.xmin;
/* 317 */         xOut = this.xmax;
/*     */       } else {
/* 319 */         xIn = this.xmax;
/* 320 */         xOut = this.xmin;
/*     */       } 
/* 322 */       if (deltaY > 0.0D || (deltaY == 0.0D && y0 > this.ymax)) {
/* 323 */         yIn = this.ymin;
/* 324 */         yOut = this.ymax;
/*     */       } else {
/* 326 */         yIn = this.ymax;
/* 327 */         yOut = this.ymin;
/*     */       } 
/* 331 */       if (deltaX != 0.0D) {
/* 332 */         tOutX = (xOut - x0) / deltaX;
/* 333 */       } else if (x0 <= this.xmax && this.xmin <= x0) {
/* 335 */         tOutX = Double.MAX_VALUE;
/*     */       } else {
/* 338 */         tOutX = -1.7976931348623157E308D;
/*     */       } 
/* 341 */       if (deltaY != 0.0D) {
/* 342 */         tOutY = (yOut - y0) / deltaY;
/* 343 */       } else if (y0 <= this.ymax && this.ymin <= y0) {
/* 345 */         tOutY = Double.MAX_VALUE;
/*     */       } else {
/* 348 */         tOutY = -1.7976931348623157E308D;
/*     */       } 
/* 352 */       if (tOutX < tOutY) {
/* 353 */         tOut1 = tOutX;
/* 354 */         tOut2 = tOutY;
/*     */       } else {
/* 356 */         tOut1 = tOutY;
/* 357 */         tOut2 = tOutX;
/*     */       } 
/* 362 */       if (tOut2 > 0.0D) {
/*     */         double tInX;
/*     */         double tInY;
/*     */         double tIn2;
/* 365 */         if (deltaX != 0.0D) {
/* 366 */           tInX = (xIn - x0) / deltaX;
/*     */         } else {
/* 368 */           tInX = -1.7976931348623157E308D;
/*     */         } 
/* 371 */         if (deltaY != 0.0D) {
/* 372 */           tInY = (yIn - y0) / deltaY;
/*     */         } else {
/* 374 */           tInY = -1.7976931348623157E308D;
/*     */         } 
/* 378 */         if (tInX < tInY) {
/* 379 */           double tIn1 = tInX;
/* 380 */           tIn2 = tInY;
/*     */         } else {
/* 382 */           double tIn1 = tInY;
/* 383 */           tIn2 = tInX;
/*     */         } 
/* 386 */         if (tOut1 < tIn2) {
/* 388 */           if (0.0D < tOut1 && tOut1 <= 1.0D)
/* 390 */             if (tInX < tInY) {
/* 391 */               out.add(xOut, yIn);
/*     */             } else {
/* 393 */               out.add(xIn, yOut);
/*     */             }  
/* 399 */         } else if (0.0D < tOut1 && tIn2 <= 1.0D) {
/* 400 */           if (0.0D <= tIn2)
/* 401 */             if (tInX > tInY) {
/* 402 */               out.add(xIn, y0 + tInX * deltaY);
/*     */             } else {
/* 404 */               out.add(x0 + tInY * deltaX, yIn);
/*     */             }  
/* 408 */           if (1.0D >= tOut1) {
/* 409 */             if (tOutX < tOutY) {
/* 410 */               out.add(xOut, y0 + tOutX * deltaY);
/*     */             } else {
/* 412 */               out.add(x0 + tOutY * deltaX, yOut);
/*     */             } 
/*     */           } else {
/* 415 */             out.add(x1, y1);
/*     */           } 
/*     */         } 
/* 421 */         if (0.0D < tOut2 && tOut2 <= 1.0D)
/* 422 */           out.add(xOut, yOut); 
/*     */       } 
/*     */     } 
/* 428 */     if (out.size() < 3)
/* 429 */       return null; 
/* 432 */     if (out.getOrdinate(0, 0) != out.getOrdinate(out.size() - 1, 0) || out.getOrdinate(0, 1) != out.getOrdinate(out.size() - 1, 1)) {
/* 434 */       out.add(out.getOrdinate(0, 0), out.getOrdinate(0, 1));
/* 435 */     } else if (out.size() == 3) {
/* 436 */       return null;
/*     */     } 
/* 439 */     return ring.getFactory().createLinearRing(out.toCoordinateSequence(ring.getFactory().getCoordinateSequenceFactory()));
/*     */   }
/*     */   
/*     */   LinearRing buildBoundsString(GeometryFactory gf, CoordinateSequenceFactory csf) {
/* 450 */     CoordinateSequence cs = csf.create(5, 2);
/* 451 */     cs.setOrdinate(0, 0, this.xmin);
/* 452 */     cs.setOrdinate(0, 1, this.ymin);
/* 453 */     cs.setOrdinate(1, 0, this.xmin);
/* 454 */     cs.setOrdinate(1, 1, this.ymax);
/* 455 */     cs.setOrdinate(2, 0, this.xmax);
/* 456 */     cs.setOrdinate(2, 1, this.ymax);
/* 457 */     cs.setOrdinate(3, 0, this.xmax);
/* 458 */     cs.setOrdinate(3, 1, this.ymin);
/* 459 */     cs.setOrdinate(4, 0, this.xmin);
/* 460 */     cs.setOrdinate(4, 1, this.ymin);
/* 461 */     return gf.createLinearRing(cs);
/*     */   }
/*     */   
/*     */   private Geometry clipCollection(GeometryCollection gc, boolean ensureValid) {
/* 471 */     if (gc.getNumGeometries() == 1)
/* 472 */       return clip(gc.getGeometryN(0), ensureValid); 
/* 474 */     List<Geometry> result = new ArrayList<Geometry>(gc.getNumGeometries());
/* 475 */     for (int i = 0; i < gc.getNumGeometries(); i++) {
/* 476 */       Geometry clipped = clip(gc.getGeometryN(i), ensureValid);
/* 477 */       if (clipped != null)
/* 478 */         result.add(clipped); 
/*     */     } 
/* 491 */     if (result.size() == 0)
/* 492 */       return null; 
/* 493 */     if (result.size() == 1)
/* 494 */       return result.get(0); 
/* 497 */     flattenCollection(result);
/* 499 */     if (gc instanceof com.vividsolutions.jts.geom.MultiPoint)
/* 500 */       return (Geometry)gc.getFactory().createMultiPoint(result.<Point>toArray(new Point[result.size()])); 
/* 502 */     if (gc instanceof com.vividsolutions.jts.geom.MultiLineString)
/* 503 */       return (Geometry)gc.getFactory().createMultiLineString(result.<LineString>toArray(new LineString[result.size()])); 
/* 505 */     if (gc instanceof com.vividsolutions.jts.geom.MultiPolygon)
/* 506 */       return (Geometry)gc.getFactory().createMultiPolygon(result.<Polygon>toArray(new Polygon[result.size()])); 
/* 509 */     return (Geometry)gc.getFactory().createGeometryCollection(result.<Geometry>toArray(new Geometry[result.size()]));
/*     */   }
/*     */   
/*     */   private void flattenCollection(List<Geometry> result) {
/* 516 */     for (int i = 0; i < result.size(); ) {
/* 517 */       Geometry g = result.get(i);
/* 518 */       if (g instanceof GeometryCollection) {
/* 519 */         GeometryCollection gc = (GeometryCollection)g;
/* 520 */         for (int j = 0; j < gc.getNumGeometries(); j++)
/* 521 */           result.add(gc.getGeometryN(j)); 
/* 523 */         result.remove(i);
/*     */         continue;
/*     */       } 
/* 525 */       i++;
/*     */     } 
/*     */   }
/*     */   
/*     */   Geometry clipLineString(LineString line) {
/* 539 */     List<LineString> clipped = new ArrayList<LineString>();
/* 542 */     GeometryFactory gf = line.getFactory();
/* 543 */     CoordinateSequenceFactory csf = gf.getCoordinateSequenceFactory();
/* 544 */     CoordinateSequence coords = line.getCoordinateSequence();
/* 547 */     Ordinates ordinates = new Ordinates(coords.size());
/* 548 */     double x0 = coords.getX(0);
/* 549 */     double y0 = coords.getY(0);
/* 550 */     boolean prevInside = contained(x0, y0);
/* 551 */     if (prevInside)
/* 552 */       ordinates.add(x0, y0); 
/* 554 */     double[] segment = new double[4];
/* 555 */     int size = coords.size();
/* 557 */     for (int i = 1; i < size; i++) {
/* 558 */       double x1 = coords.getX(i);
/* 559 */       double y1 = coords.getY(i);
/* 561 */       boolean inside = contained(x1, y1);
/* 562 */       if (inside == prevInside) {
/* 563 */         if (inside) {
/* 565 */           ordinates.add(x1, y1);
/* 569 */         } else if (!outside(x0, y0, x1, y1)) {
/* 570 */           segment[0] = x0;
/* 571 */           segment[1] = y0;
/* 572 */           segment[2] = x1;
/* 573 */           segment[3] = y1;
/* 574 */           double[] clippedSegment = clipSegment(segment);
/* 575 */           if (clippedSegment != null) {
/* 576 */             CoordinateSequence cs = csf.create(2, 2);
/* 577 */             cs.setOrdinate(0, 0, clippedSegment[0]);
/* 578 */             cs.setOrdinate(0, 1, clippedSegment[1]);
/* 579 */             cs.setOrdinate(1, 0, clippedSegment[2]);
/* 580 */             cs.setOrdinate(1, 1, clippedSegment[3]);
/* 581 */             clipped.add(gf.createLineString(cs));
/*     */           } 
/*     */         } 
/*     */       } else {
/* 587 */         segment[0] = x0;
/* 588 */         segment[1] = y0;
/* 589 */         segment[2] = x1;
/* 590 */         segment[3] = y1;
/* 591 */         double[] clippedSegment = clipSegment(segment);
/* 592 */         if (clippedSegment != null) {
/* 593 */           if (prevInside) {
/* 594 */             ordinates.add(clippedSegment[2], clippedSegment[3]);
/*     */           } else {
/* 596 */             ordinates.add(clippedSegment[0], clippedSegment[1]);
/* 597 */             ordinates.add(clippedSegment[2], clippedSegment[3]);
/*     */           } 
/* 601 */           if (prevInside) {
/* 606 */             clipped.add(gf.createLineString(ordinates.toCoordinateSequence(csf)));
/* 608 */             ordinates.clear();
/*     */           } 
/*     */         } else {
/* 611 */           prevInside = false;
/*     */         } 
/*     */       } 
/* 614 */       prevInside = inside;
/* 615 */       x0 = x1;
/* 616 */       y0 = y1;
/*     */     } 
/* 619 */     if (ordinates.size() > 1)
/* 620 */       clipped.add(gf.createLineString(ordinates.toCoordinateSequence(csf))); 
/* 623 */     if (line.isClosed() && clipped.size() > 1) {
/* 625 */       CoordinateSequence cs0 = ((LineString)clipped.get(0)).getCoordinateSequence();
/* 626 */       CoordinateSequence cs1 = ((LineString)clipped.get(clipped.size() - 1)).getCoordinateSequence();
/* 627 */       if (cs0.getOrdinate(0, 0) == cs1.getOrdinate(cs1.size() - 1, 0) && cs0.getOrdinate(0, 1) == cs1.getOrdinate(cs1.size() - 1, 1)) {
/* 629 */         CoordinateSequence cs = csf.create(cs0.size() + cs1.size() - 1, 2);
/*     */         int j;
/* 630 */         for (j = 0; j < cs1.size(); j++) {
/* 631 */           cs.setOrdinate(j, 0, cs1.getOrdinate(j, 0));
/* 632 */           cs.setOrdinate(j, 1, cs1.getOrdinate(j, 1));
/*     */         } 
/* 634 */         for (j = 1; j < cs0.size(); j++) {
/* 635 */           cs.setOrdinate(j + cs1.size() - 1, 0, cs0.getOrdinate(j, 0));
/* 636 */           cs.setOrdinate(j + cs1.size() - 1, 1, cs0.getOrdinate(j, 1));
/*     */         } 
/* 638 */         clipped.remove(0);
/* 639 */         clipped.remove(clipped.size() - 1);
/* 640 */         clipped.add(gf.createLineString(cs));
/*     */       } 
/*     */     } 
/* 645 */     if (clipped.size() > 1)
/* 646 */       return (Geometry)gf.createMultiLineString(clipped.<LineString>toArray(new LineString[clipped.size()])); 
/* 648 */     if (clipped.size() == 1)
/* 649 */       return (Geometry)clipped.get(0); 
/* 651 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\GeometryClipper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */