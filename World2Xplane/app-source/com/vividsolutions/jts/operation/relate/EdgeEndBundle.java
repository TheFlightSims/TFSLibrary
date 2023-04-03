/*     */ package com.vividsolutions.jts.operation.relate;
/*     */ 
/*     */ import com.vividsolutions.jts.algorithm.BoundaryNodeRule;
/*     */ import com.vividsolutions.jts.geom.IntersectionMatrix;
/*     */ import com.vividsolutions.jts.geomgraph.Edge;
/*     */ import com.vividsolutions.jts.geomgraph.EdgeEnd;
/*     */ import com.vividsolutions.jts.geomgraph.GeometryGraph;
/*     */ import com.vividsolutions.jts.geomgraph.Label;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ public class EdgeEndBundle extends EdgeEnd {
/*  55 */   private List edgeEnds = new ArrayList();
/*     */   
/*     */   public EdgeEndBundle(BoundaryNodeRule boundaryNodeRule, EdgeEnd e) {
/*  59 */     super(e.getEdge(), e.getCoordinate(), e.getDirectedCoordinate(), new Label(e.getLabel()));
/*  60 */     insert(e);
/*     */   }
/*     */   
/*     */   public EdgeEndBundle(EdgeEnd e) {
/*  71 */     this(null, e);
/*     */   }
/*     */   
/*     */   public Label getLabel() {
/*  74 */     return this.label;
/*     */   }
/*     */   
/*     */   public Iterator iterator() {
/*  75 */     return this.edgeEnds.iterator();
/*     */   }
/*     */   
/*     */   public List getEdgeEnds() {
/*  76 */     return this.edgeEnds;
/*     */   }
/*     */   
/*     */   public void insert(EdgeEnd e) {
/*  82 */     this.edgeEnds.add(e);
/*     */   }
/*     */   
/*     */   public void computeLabel(BoundaryNodeRule boundaryNodeRule) {
/*  93 */     boolean isArea = false;
/*  94 */     for (Iterator<EdgeEnd> it = iterator(); it.hasNext(); ) {
/*  95 */       EdgeEnd e = it.next();
/*  96 */       if (e.getLabel().isArea())
/*  96 */         isArea = true; 
/*     */     } 
/*  98 */     if (isArea) {
/*  99 */       this.label = new Label(-1, -1, -1);
/*     */     } else {
/* 101 */       this.label = new Label(-1);
/*     */     } 
/* 104 */     for (int i = 0; i < 2; i++) {
/* 105 */       computeLabelOn(i, boundaryNodeRule);
/* 106 */       if (isArea)
/* 107 */         computeLabelSides(i); 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void computeLabelOn(int geomIndex, BoundaryNodeRule boundaryNodeRule) {
/* 134 */     int boundaryCount = 0;
/* 135 */     boolean foundInterior = false;
/* 137 */     for (Iterator<EdgeEnd> it = iterator(); it.hasNext(); ) {
/* 138 */       EdgeEnd e = it.next();
/* 139 */       int i = e.getLabel().getLocation(geomIndex);
/* 140 */       if (i == 1)
/* 140 */         boundaryCount++; 
/* 141 */       if (i == 0)
/* 141 */         foundInterior = true; 
/*     */     } 
/* 143 */     int loc = -1;
/* 144 */     if (foundInterior)
/* 144 */       loc = 0; 
/* 145 */     if (boundaryCount > 0)
/* 146 */       loc = GeometryGraph.determineBoundary(boundaryNodeRule, boundaryCount); 
/* 148 */     this.label.setLocation(geomIndex, loc);
/*     */   }
/*     */   
/*     */   private void computeLabelSides(int geomIndex) {
/* 156 */     computeLabelSide(geomIndex, 1);
/* 157 */     computeLabelSide(geomIndex, 2);
/*     */   }
/*     */   
/*     */   private void computeLabelSide(int geomIndex, int side) {
/* 176 */     for (Iterator<EdgeEnd> it = iterator(); it.hasNext(); ) {
/* 177 */       EdgeEnd e = it.next();
/* 178 */       if (e.getLabel().isArea()) {
/* 179 */         int loc = e.getLabel().getLocation(geomIndex, side);
/* 180 */         if (loc == 0) {
/* 181 */           this.label.setLocation(geomIndex, side, 0);
/*     */           return;
/*     */         } 
/* 184 */         if (loc == 2)
/* 185 */           this.label.setLocation(geomIndex, side, 2); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   void updateIM(IntersectionMatrix im) {
/* 195 */     Edge.updateIM(this.label, im);
/*     */   }
/*     */   
/*     */   public void print(PrintStream out) {
/* 199 */     out.println("EdgeEndBundle--> Label: " + this.label);
/* 200 */     for (Iterator<EdgeEnd> it = iterator(); it.hasNext(); ) {
/* 201 */       EdgeEnd ee = it.next();
/* 202 */       ee.print(out);
/* 203 */       out.println();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\relate\EdgeEndBundle.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */