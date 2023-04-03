/*     */ package com.vividsolutions.jts.noding.snapround;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryComponentFilter;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.geom.PrecisionModel;
/*     */ import com.vividsolutions.jts.geom.util.LinearComponentExtracter;
/*     */ import com.vividsolutions.jts.noding.NodedSegmentString;
/*     */ import com.vividsolutions.jts.noding.Noder;
/*     */ import com.vividsolutions.jts.noding.NodingValidator;
/*     */ import com.vividsolutions.jts.noding.SegmentString;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class GeometryNoder {
/*     */   private GeometryFactory geomFact;
/*     */   
/*     */   private PrecisionModel pm;
/*     */   
/*     */   private boolean isValidityChecked = false;
/*     */   
/*     */   public GeometryNoder(PrecisionModel pm) {
/*  72 */     this.pm = pm;
/*     */   }
/*     */   
/*     */   public void setValidate(boolean isValidityChecked) {
/*  82 */     this.isValidityChecked = isValidityChecked;
/*     */   }
/*     */   
/*     */   public List node(Collection<Geometry> geoms) {
/*  94 */     Geometry geom0 = geoms.iterator().next();
/*  95 */     this.geomFact = geom0.getFactory();
/*  97 */     List segStrings = toSegmentStrings(extractLines(geoms));
/*  99 */     Noder sr = new MCIndexSnapRounder(this.pm);
/* 100 */     sr.computeNodes(segStrings);
/* 101 */     Collection nodedLines = sr.getNodedSubstrings();
/* 104 */     if (this.isValidityChecked) {
/* 105 */       NodingValidator nv = new NodingValidator(nodedLines);
/* 106 */       nv.checkValid();
/*     */     } 
/* 109 */     return toLineStrings(nodedLines);
/*     */   }
/*     */   
/*     */   private List toLineStrings(Collection segStrings) {
/* 114 */     List<LineString> lines = new ArrayList();
/* 115 */     for (Iterator<SegmentString> it = segStrings.iterator(); it.hasNext(); ) {
/* 116 */       SegmentString ss = it.next();
/* 118 */       if (ss.size() < 2)
/*     */         continue; 
/* 120 */       lines.add(this.geomFact.createLineString(ss.getCoordinates()));
/*     */     } 
/* 122 */     return lines;
/*     */   }
/*     */   
/*     */   private List extractLines(Collection geoms) {
/* 127 */     List lines = new ArrayList();
/* 128 */     LinearComponentExtracter lce = new LinearComponentExtracter(lines);
/* 129 */     for (Iterator<Geometry> it = geoms.iterator(); it.hasNext(); ) {
/* 130 */       Geometry geom = it.next();
/* 131 */       geom.apply((GeometryComponentFilter)lce);
/*     */     } 
/* 133 */     return lines;
/*     */   }
/*     */   
/*     */   private List toSegmentStrings(Collection lines) {
/* 138 */     List<NodedSegmentString> segStrings = new ArrayList();
/* 139 */     for (Iterator<LineString> it = lines.iterator(); it.hasNext(); ) {
/* 140 */       LineString line = it.next();
/* 141 */       segStrings.add(new NodedSegmentString(line.getCoordinates(), null));
/*     */     } 
/* 143 */     return segStrings;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\noding\snapround\GeometryNoder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */