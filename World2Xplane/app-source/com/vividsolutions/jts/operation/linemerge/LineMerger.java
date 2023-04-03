/*     */ package com.vividsolutions.jts.operation.linemerge;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import com.vividsolutions.jts.geom.GeometryComponentFilter;
/*     */ import com.vividsolutions.jts.geom.GeometryFactory;
/*     */ import com.vividsolutions.jts.geom.LineString;
/*     */ import com.vividsolutions.jts.planargraph.GraphComponent;
/*     */ import com.vividsolutions.jts.planargraph.Node;
/*     */ import com.vividsolutions.jts.util.Assert;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class LineMerger {
/*  72 */   private LineMergeGraph graph = new LineMergeGraph();
/*     */   
/*  73 */   private Collection mergedLineStrings = null;
/*     */   
/*  74 */   private GeometryFactory factory = null;
/*     */   
/*     */   private Collection edgeStrings;
/*     */   
/*     */   public void add(Geometry geometry) {
/*  93 */     geometry.apply(new GeometryComponentFilter() {
/*     */           public void filter(Geometry component) {
/*  95 */             if (component instanceof LineString)
/*  96 */               LineMerger.this.add((LineString)component); 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void add(Collection geometries) {
/* 110 */     this.mergedLineStrings = null;
/* 111 */     for (Iterator<Geometry> i = geometries.iterator(); i.hasNext(); ) {
/* 112 */       Geometry geometry = i.next();
/* 113 */       add(geometry);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void add(LineString lineString) {
/* 117 */     if (this.factory == null)
/* 118 */       this.factory = lineString.getFactory(); 
/* 120 */     this.graph.addEdge(lineString);
/*     */   }
/*     */   
/*     */   public LineMerger() {
/* 123 */     this.edgeStrings = null;
/*     */   }
/*     */   
/*     */   private void merge() {
/* 127 */     if (this.mergedLineStrings != null)
/*     */       return; 
/* 130 */     GraphComponent.setMarked(this.graph.nodeIterator(), false);
/* 131 */     GraphComponent.setMarked(this.graph.edgeIterator(), false);
/* 133 */     this.edgeStrings = new ArrayList();
/* 134 */     buildEdgeStringsForObviousStartNodes();
/* 135 */     buildEdgeStringsForIsolatedLoops();
/* 136 */     this.mergedLineStrings = new ArrayList();
/* 137 */     for (Iterator<EdgeString> i = this.edgeStrings.iterator(); i.hasNext(); ) {
/* 138 */       EdgeString edgeString = i.next();
/* 139 */       this.mergedLineStrings.add(edgeString.toLineString());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void buildEdgeStringsForObviousStartNodes() {
/* 144 */     buildEdgeStringsForNonDegree2Nodes();
/*     */   }
/*     */   
/*     */   private void buildEdgeStringsForIsolatedLoops() {
/* 148 */     buildEdgeStringsForUnprocessedNodes();
/*     */   }
/*     */   
/*     */   private void buildEdgeStringsForUnprocessedNodes() {
/* 152 */     for (Iterator<Node> i = this.graph.getNodes().iterator(); i.hasNext(); ) {
/* 153 */       Node node = i.next();
/* 154 */       if (!node.isMarked()) {
/* 155 */         Assert.isTrue((node.getDegree() == 2));
/* 156 */         buildEdgeStringsStartingAt(node);
/* 157 */         node.setMarked(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void buildEdgeStringsForNonDegree2Nodes() {
/* 162 */     for (Iterator<Node> i = this.graph.getNodes().iterator(); i.hasNext(); ) {
/* 163 */       Node node = i.next();
/* 164 */       if (node.getDegree() != 2) {
/* 165 */         buildEdgeStringsStartingAt(node);
/* 166 */         node.setMarked(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void buildEdgeStringsStartingAt(Node node) {
/* 171 */     for (Iterator<LineMergeDirectedEdge> i = node.getOutEdges().iterator(); i.hasNext(); ) {
/* 172 */       LineMergeDirectedEdge directedEdge = i.next();
/* 173 */       if (directedEdge.getEdge().isMarked())
/*     */         continue; 
/* 174 */       this.edgeStrings.add(buildEdgeStringStartingWith(directedEdge));
/*     */     } 
/*     */   }
/*     */   
/*     */   private EdgeString buildEdgeStringStartingWith(LineMergeDirectedEdge start) {
/* 179 */     EdgeString edgeString = new EdgeString(this.factory);
/* 180 */     LineMergeDirectedEdge current = start;
/*     */     do {
/* 182 */       edgeString.add(current);
/* 183 */       current.getEdge().setMarked(true);
/* 184 */       current = current.getNext();
/* 185 */     } while (current != null && current != start);
/* 186 */     return edgeString;
/*     */   }
/*     */   
/*     */   public Collection getMergedLineStrings() {
/* 195 */     merge();
/* 196 */     return this.mergedLineStrings;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\linemerge\LineMerger.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */