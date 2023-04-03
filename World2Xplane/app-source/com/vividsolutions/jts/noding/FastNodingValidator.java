/*     */ package com.vividsolutions.jts.noding;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.LineIntersector;
/*     */ import com.vividsolutions.jts.algorithm.RobustLineIntersector;
/*     */ import com.vividsolutions.jts.geom.Coordinate;
/*     */ import com.vividsolutions.jts.geom.TopologyException;
/*     */ import com.vividsolutions.jts.io.WKTWriter;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ public class FastNodingValidator {
/*  64 */   private LineIntersector li = (LineIntersector)new RobustLineIntersector();
/*     */   
/*     */   private Collection segStrings;
/*     */   
/*     */   private boolean findAllIntersections = false;
/*     */   
/*  68 */   private InteriorIntersectionFinder segInt = null;
/*     */   
/*     */   private boolean isValid = true;
/*     */   
/*     */   public FastNodingValidator(Collection segStrings) {
/*  78 */     this.segStrings = segStrings;
/*     */   }
/*     */   
/*     */   public void setFindAllIntersections(boolean findAllIntersections) {
/*  83 */     this.findAllIntersections = findAllIntersections;
/*     */   }
/*     */   
/*     */   public List getIntersections() {
/*  88 */     return this.segInt.getIntersections();
/*     */   }
/*     */   
/*     */   public boolean isValid() {
/*  99 */     execute();
/* 100 */     return this.isValid;
/*     */   }
/*     */   
/*     */   public String getErrorMessage() {
/* 111 */     if (this.isValid)
/* 111 */       return "no intersections found"; 
/* 113 */     Coordinate[] intSegs = this.segInt.getIntersectionSegments();
/* 114 */     return "found non-noded intersection between " + WKTWriter.toLineString(intSegs[0], intSegs[1]) + " and " + WKTWriter.toLineString(intSegs[2], intSegs[3]);
/*     */   }
/*     */   
/*     */   public void checkValid() {
/* 128 */     execute();
/* 129 */     if (!this.isValid)
/* 130 */       throw new TopologyException(getErrorMessage(), this.segInt.getInteriorIntersection()); 
/*     */   }
/*     */   
/*     */   private void execute() {
/* 135 */     if (this.segInt != null)
/*     */       return; 
/* 137 */     checkInteriorIntersections();
/*     */   }
/*     */   
/*     */   private void checkInteriorIntersections() {
/* 147 */     this.isValid = true;
/* 148 */     this.segInt = new InteriorIntersectionFinder(this.li);
/* 149 */     this.segInt.setFindAllIntersections(this.findAllIntersections);
/* 150 */     MCIndexNoder noder = new MCIndexNoder();
/* 151 */     noder.setSegmentIntersector(this.segInt);
/* 152 */     noder.computeNodes(this.segStrings);
/* 153 */     if (this.segInt.hasIntersection()) {
/* 154 */       this.isValid = false;
/*     */       return;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\FastNodingValidator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */