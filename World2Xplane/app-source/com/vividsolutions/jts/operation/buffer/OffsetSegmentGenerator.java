/*     */ package com.vividsolutions.jts.operation.buffer;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.Angle;
/*     */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*     */ import com.vividsolutions.jts.algorithm.HCoordinate;
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.algorithm.NotRepresentableException;
/*     */ import com.vividsolutions.jts.algorithm.RobustLineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.LineSegment;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ 
/*     */ class OffsetSegmentGenerator {
/*     */   private static final double OFFSET_SEGMENT_SEPARATION_FACTOR = 0.001D;
/*     */   
/*     */   private static final double INSIDE_TURN_VERTEX_SNAP_DISTANCE_FACTOR = 0.001D;
/*     */   
/*     */   private static final double CURVE_VERTEX_SNAP_DISTANCE_FACTOR = 1.0E-6D;
/*     */   
/*     */   private static final int MAX_CLOSING_SEG_LEN_FACTOR = 80;
/*     */   
/*  86 */   private double maxCurveSegmentError = 0.0D;
/*     */   
/*     */   private double filletAngleQuantum;
/*     */   
/* 109 */   private int closingSegLengthFactor = 1;
/*     */   
/*     */   private OffsetSegmentString segList;
/*     */   
/* 112 */   private double distance = 0.0D;
/*     */   
/*     */   private PrecisionModel precisionModel;
/*     */   
/*     */   private BufferParameters bufParams;
/*     */   
/*     */   private LineIntersector li;
/*     */   
/*     */   private Coordinate s0;
/*     */   
/*     */   private Coordinate s1;
/*     */   
/*     */   private Coordinate s2;
/*     */   
/* 118 */   private LineSegment seg0 = new LineSegment();
/*     */   
/* 119 */   private LineSegment seg1 = new LineSegment();
/*     */   
/* 120 */   private LineSegment offset0 = new LineSegment();
/*     */   
/* 121 */   private LineSegment offset1 = new LineSegment();
/*     */   
/* 122 */   private int side = 0;
/*     */   
/*     */   private boolean hasNarrowConcaveAngle = false;
/*     */   
/*     */   public OffsetSegmentGenerator(PrecisionModel precisionModel, BufferParameters bufParams, double distance) {
/* 127 */     this.precisionModel = precisionModel;
/* 128 */     this.bufParams = bufParams;
/* 132 */     this.li = (LineIntersector)new RobustLineIntersector();
/* 133 */     this.filletAngleQuantum = 1.5707963267948966D / bufParams.getQuadrantSegments();
/* 140 */     if (bufParams.getQuadrantSegments() >= 8 && bufParams.getJoinStyle() == 1)
/* 142 */       this.closingSegLengthFactor = 80; 
/* 143 */     init(distance);
/*     */   }
/*     */   
/*     */   public boolean hasNarrowConcaveAngle() {
/* 160 */     return this.hasNarrowConcaveAngle;
/*     */   }
/*     */   
/*     */   private void init(double distance) {
/* 165 */     this.distance = distance;
/* 166 */     this.maxCurveSegmentError = distance * (1.0D - Math.cos(this.filletAngleQuantum / 2.0D));
/* 167 */     this.segList = new OffsetSegmentString();
/* 168 */     this.segList.setPrecisionModel(this.precisionModel);
/* 172 */     this.segList.setMinimumVertexDistance(distance * 1.0E-6D);
/*     */   }
/*     */   
/*     */   public void initSideSegments(Coordinate s1, Coordinate s2, int side) {
/* 178 */     this.s1 = s1;
/* 179 */     this.s2 = s2;
/* 180 */     this.side = side;
/* 181 */     this.seg1.setCoordinates(s1, s2);
/* 182 */     computeOffsetSegment(this.seg1, side, this.distance, this.offset1);
/*     */   }
/*     */   
/*     */   public Coordinate[] getCoordinates() {
/* 187 */     Coordinate[] pts = this.segList.getCoordinates();
/* 188 */     return pts;
/*     */   }
/*     */   
/*     */   public void closeRing() {
/* 193 */     this.segList.closeRing();
/*     */   }
/*     */   
/*     */   public void addSegments(Coordinate[] pt, boolean isForward) {
/* 198 */     this.segList.addPts(pt, isForward);
/*     */   }
/*     */   
/*     */   public void addFirstSegment() {
/* 203 */     this.segList.addPt(this.offset1.p0);
/*     */   }
/*     */   
/*     */   public void addLastSegment() {
/* 211 */     this.segList.addPt(this.offset1.p1);
/*     */   }
/*     */   
/*     */   public void addNextSegment(Coordinate p, boolean addStartPoint) {
/* 219 */     this.s0 = this.s1;
/* 220 */     this.s1 = this.s2;
/* 221 */     this.s2 = p;
/* 222 */     this.seg0.setCoordinates(this.s0, this.s1);
/* 223 */     computeOffsetSegment(this.seg0, this.side, this.distance, this.offset0);
/* 224 */     this.seg1.setCoordinates(this.s1, this.s2);
/* 225 */     computeOffsetSegment(this.seg1, this.side, this.distance, this.offset1);
/* 228 */     if (this.s1.equals(this.s2))
/*     */       return; 
/* 230 */     int orientation = CGAlgorithms.computeOrientation(this.s0, this.s1, this.s2);
/* 231 */     boolean outsideTurn = ((orientation == -1 && this.side == 1) || (orientation == 1 && this.side == 2));
/* 235 */     if (orientation == 0) {
/* 236 */       addCollinear(addStartPoint);
/* 238 */     } else if (outsideTurn) {
/* 240 */       addOutsideTurn(orientation, addStartPoint);
/*     */     } else {
/* 243 */       addInsideTurn(orientation, addStartPoint);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addCollinear(boolean addStartPoint) {
/* 253 */     this.li.computeIntersection(this.s0, this.s1, this.s1, this.s2);
/* 254 */     int numInt = this.li.getIntersectionNum();
/* 260 */     if (numInt >= 2)
/* 270 */       if (this.bufParams.getJoinStyle() == 3 || this.bufParams.getJoinStyle() == 2) {
/* 272 */         if (addStartPoint)
/* 272 */           this.segList.addPt(this.offset0.p1); 
/* 273 */         this.segList.addPt(this.offset1.p0);
/*     */       } else {
/* 276 */         addFillet(this.s1, this.offset0.p1, this.offset1.p0, -1, this.distance);
/*     */       }  
/*     */   }
/*     */   
/*     */   private void addOutsideTurn(int orientation, boolean addStartPoint) {
/* 296 */     if (this.offset0.p1.distance(this.offset1.p0) < this.distance * 0.001D) {
/* 297 */       this.segList.addPt(this.offset0.p1);
/*     */       return;
/*     */     } 
/* 301 */     if (this.bufParams.getJoinStyle() == 2) {
/* 302 */       addMitreJoin(this.s1, this.offset0, this.offset1, this.distance);
/* 304 */     } else if (this.bufParams.getJoinStyle() == 3) {
/* 305 */       addBevelJoin(this.offset0, this.offset1);
/*     */     } else {
/* 309 */       if (addStartPoint)
/* 309 */         this.segList.addPt(this.offset0.p1); 
/* 311 */       addFillet(this.s1, this.offset0.p1, this.offset1.p0, orientation, this.distance);
/* 312 */       this.segList.addPt(this.offset1.p0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addInsideTurn(int orientation, boolean addStartPoint) {
/* 326 */     this.li.computeIntersection(this.offset0.p0, this.offset0.p1, this.offset1.p0, this.offset1.p1);
/* 327 */     if (this.li.hasIntersection()) {
/* 328 */       this.segList.addPt(this.li.getIntersection(0));
/*     */     } else {
/* 359 */       this.hasNarrowConcaveAngle = true;
/* 361 */       if (this.offset0.p1.distance(this.offset1.p0) < this.distance * 0.001D) {
/* 363 */         this.segList.addPt(this.offset0.p1);
/*     */       } else {
/* 366 */         this.segList.addPt(this.offset0.p1);
/* 371 */         if (this.closingSegLengthFactor > 0) {
/* 372 */           Coordinate mid0 = new Coordinate((this.closingSegLengthFactor * this.offset0.p1.x + this.s1.x) / (this.closingSegLengthFactor + 1), (this.closingSegLengthFactor * this.offset0.p1.y + this.s1.y) / (this.closingSegLengthFactor + 1));
/* 374 */           this.segList.addPt(mid0);
/* 375 */           Coordinate mid1 = new Coordinate((this.closingSegLengthFactor * this.offset1.p0.x + this.s1.x) / (this.closingSegLengthFactor + 1), (this.closingSegLengthFactor * this.offset1.p0.y + this.s1.y) / (this.closingSegLengthFactor + 1));
/* 377 */           this.segList.addPt(mid1);
/*     */         } else {
/* 385 */           this.segList.addPt(this.s1);
/*     */         } 
/* 390 */         this.segList.addPt(this.offset1.p0);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeOffsetSegment(LineSegment seg, int side, double distance, LineSegment offset) {
/* 407 */     int sideSign = (side == 1) ? 1 : -1;
/* 408 */     double dx = seg.p1.x - seg.p0.x;
/* 409 */     double dy = seg.p1.y - seg.p0.y;
/* 410 */     double len = Math.sqrt(dx * dx + dy * dy);
/* 412 */     double ux = sideSign * distance * dx / len;
/* 413 */     double uy = sideSign * distance * dy / len;
/* 414 */     seg.p0.x -= uy;
/* 415 */     seg.p0.y += ux;
/* 416 */     seg.p1.x -= uy;
/* 417 */     seg.p1.y += ux;
/*     */   }
/*     */   
/*     */   public void addLineEndCap(Coordinate p0, Coordinate p1) {
/*     */     Coordinate squareCapSideOffset, squareCapLOffset, squareCapROffset;
/* 425 */     LineSegment seg = new LineSegment(p0, p1);
/* 427 */     LineSegment offsetL = new LineSegment();
/* 428 */     computeOffsetSegment(seg, 1, this.distance, offsetL);
/* 429 */     LineSegment offsetR = new LineSegment();
/* 430 */     computeOffsetSegment(seg, 2, this.distance, offsetR);
/* 432 */     double dx = p1.x - p0.x;
/* 433 */     double dy = p1.y - p0.y;
/* 434 */     double angle = Math.atan2(dy, dx);
/* 436 */     switch (this.bufParams.getEndCapStyle()) {
/*     */       case 1:
/* 439 */         this.segList.addPt(offsetL.p1);
/* 440 */         addFillet(p1, angle + 1.5707963267948966D, angle - 1.5707963267948966D, -1, this.distance);
/* 441 */         this.segList.addPt(offsetR.p1);
/*     */         break;
/*     */       case 2:
/* 445 */         this.segList.addPt(offsetL.p1);
/* 446 */         this.segList.addPt(offsetR.p1);
/*     */         break;
/*     */       case 3:
/* 450 */         squareCapSideOffset = new Coordinate();
/* 451 */         squareCapSideOffset.x = Math.abs(this.distance) * Math.cos(angle);
/* 452 */         squareCapSideOffset.y = Math.abs(this.distance) * Math.sin(angle);
/* 454 */         squareCapLOffset = new Coordinate(offsetL.p1.x + squareCapSideOffset.x, offsetL.p1.y + squareCapSideOffset.y);
/* 457 */         squareCapROffset = new Coordinate(offsetR.p1.x + squareCapSideOffset.x, offsetR.p1.y + squareCapSideOffset.y);
/* 460 */         this.segList.addPt(squareCapLOffset);
/* 461 */         this.segList.addPt(squareCapROffset);
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addMitreJoin(Coordinate p, LineSegment offset0, LineSegment offset1, double distance) {
/* 479 */     boolean isMitreWithinLimit = true;
/* 480 */     Coordinate intPt = null;
/*     */     try {
/* 488 */       intPt = HCoordinate.intersection(offset0.p0, offset0.p1, offset1.p0, offset1.p1);
/* 491 */       double mitreRatio = (distance <= 0.0D) ? 1.0D : (intPt.distance(p) / Math.abs(distance));
/* 494 */       if (mitreRatio > this.bufParams.getMitreLimit())
/* 495 */         isMitreWithinLimit = false; 
/* 497 */     } catch (NotRepresentableException ex) {
/* 498 */       intPt = new Coordinate(0.0D, 0.0D);
/* 499 */       isMitreWithinLimit = false;
/*     */     } 
/* 502 */     if (isMitreWithinLimit) {
/* 503 */       this.segList.addPt(intPt);
/*     */     } else {
/* 506 */       addLimitedMitreJoin(offset0, offset1, distance, this.bufParams.getMitreLimit());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addLimitedMitreJoin(LineSegment offset0, LineSegment offset1, double distance, double mitreLimit) {
/* 528 */     Coordinate basePt = this.seg0.p1;
/* 530 */     double ang0 = Angle.angle(basePt, this.seg0.p0);
/* 531 */     double ang1 = Angle.angle(basePt, this.seg1.p1);
/* 534 */     double angDiff = Angle.angleBetweenOriented(this.seg0.p0, basePt, this.seg1.p1);
/* 536 */     double angDiffHalf = angDiff / 2.0D;
/* 539 */     double midAng = Angle.normalize(ang0 + angDiffHalf);
/* 541 */     double mitreMidAng = Angle.normalize(midAng + Math.PI);
/* 544 */     double mitreDist = mitreLimit * distance;
/* 547 */     double bevelDelta = mitreDist * Math.abs(Math.sin(angDiffHalf));
/* 548 */     double bevelHalfLen = distance - bevelDelta;
/* 551 */     double bevelMidX = basePt.x + mitreDist * Math.cos(mitreMidAng);
/* 552 */     double bevelMidY = basePt.y + mitreDist * Math.sin(mitreMidAng);
/* 553 */     Coordinate bevelMidPt = new Coordinate(bevelMidX, bevelMidY);
/* 556 */     LineSegment mitreMidLine = new LineSegment(basePt, bevelMidPt);
/* 560 */     Coordinate bevelEndLeft = mitreMidLine.pointAlongOffset(1.0D, bevelHalfLen);
/* 561 */     Coordinate bevelEndRight = mitreMidLine.pointAlongOffset(1.0D, -bevelHalfLen);
/* 563 */     if (this.side == 1) {
/* 564 */       this.segList.addPt(bevelEndLeft);
/* 565 */       this.segList.addPt(bevelEndRight);
/*     */     } else {
/* 568 */       this.segList.addPt(bevelEndRight);
/* 569 */       this.segList.addPt(bevelEndLeft);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addBevelJoin(LineSegment offset0, LineSegment offset1) {
/* 584 */     this.segList.addPt(offset0.p1);
/* 585 */     this.segList.addPt(offset1.p0);
/*     */   }
/*     */   
/*     */   private void addFillet(Coordinate p, Coordinate p0, Coordinate p1, int direction, double radius) {
/* 601 */     double dx0 = p0.x - p.x;
/* 602 */     double dy0 = p0.y - p.y;
/* 603 */     double startAngle = Math.atan2(dy0, dx0);
/* 604 */     double dx1 = p1.x - p.x;
/* 605 */     double dy1 = p1.y - p.y;
/* 606 */     double endAngle = Math.atan2(dy1, dx1);
/* 608 */     if (direction == -1) {
/* 609 */       if (startAngle <= endAngle)
/* 609 */         startAngle += 6.283185307179586D; 
/* 612 */     } else if (startAngle >= endAngle) {
/* 612 */       startAngle -= 6.283185307179586D;
/*     */     } 
/* 614 */     this.segList.addPt(p0);
/* 615 */     addFillet(p, startAngle, endAngle, direction, radius);
/* 616 */     this.segList.addPt(p1);
/*     */   }
/*     */   
/*     */   private void addFillet(Coordinate p, double startAngle, double endAngle, int direction, double radius) {
/* 630 */     int directionFactor = (direction == -1) ? -1 : 1;
/* 632 */     double totalAngle = Math.abs(startAngle - endAngle);
/* 633 */     int nSegs = (int)(totalAngle / this.filletAngleQuantum + 0.5D);
/* 635 */     if (nSegs < 1)
/*     */       return; 
/* 640 */     double initAngle = 0.0D;
/* 641 */     double currAngleInc = totalAngle / nSegs;
/* 643 */     double currAngle = initAngle;
/* 644 */     Coordinate pt = new Coordinate();
/* 645 */     while (currAngle < totalAngle) {
/* 646 */       double angle = startAngle + directionFactor * currAngle;
/* 647 */       p.x += radius * Math.cos(angle);
/* 648 */       p.y += radius * Math.sin(angle);
/* 649 */       this.segList.addPt(pt);
/* 650 */       currAngle += currAngleInc;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void createCircle(Coordinate p) {
/* 661 */     Coordinate pt = new Coordinate(p.x + this.distance, p.y);
/* 662 */     this.segList.addPt(pt);
/* 663 */     addFillet(p, 0.0D, 6.283185307179586D, -1, this.distance);
/* 664 */     this.segList.closeRing();
/*     */   }
/*     */   
/*     */   public void createSquare(Coordinate p) {
/* 672 */     this.segList.addPt(new Coordinate(p.x + this.distance, p.y + this.distance));
/* 673 */     this.segList.addPt(new Coordinate(p.x + this.distance, p.y - this.distance));
/* 674 */     this.segList.addPt(new Coordinate(p.x - this.distance, p.y - this.distance));
/* 675 */     this.segList.addPt(new Coordinate(p.x - this.distance, p.y + this.distance));
/* 676 */     this.segList.closeRing();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\buffer\OffsetSegmentGenerator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */