/*    */ package com.vividsolutions.jts.edgegraph;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import com.vividsolutions.jts.geom.GeometryComponentFilter;
/*    */ import com.vividsolutions.jts.geom.LineString;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class EdgeGraphBuilder {
/*    */   public static EdgeGraph build(Collection geoms) {
/* 20 */     EdgeGraphBuilder builder = new EdgeGraphBuilder();
/* 21 */     builder.add(geoms);
/* 22 */     return builder.getGraph();
/*    */   }
/*    */   
/* 25 */   private EdgeGraph graph = new EdgeGraph();
/*    */   
/*    */   public EdgeGraph getGraph() {
/* 34 */     return this.graph;
/*    */   }
/*    */   
/*    */   public void add(Geometry geometry) {
/* 46 */     geometry.apply(new GeometryComponentFilter() {
/*    */           public void filter(Geometry component) {
/* 48 */             if (component instanceof LineString)
/* 49 */               EdgeGraphBuilder.this.add((LineString)component); 
/*    */           }
/*    */         });
/*    */   }
/*    */   
/*    */   public void add(Collection geometries) {
/* 63 */     for (Iterator<Geometry> i = geometries.iterator(); i.hasNext(); ) {
/* 64 */       Geometry geometry = i.next();
/* 65 */       add(geometry);
/*    */     } 
/*    */   }
/*    */   
/*    */   private void add(LineString lineString) {
/* 70 */     CoordinateSequence seq = lineString.getCoordinateSequence();
/* 71 */     for (int i = 1; i < seq.size(); i++)
/* 72 */       this.graph.addEdge(seq.getCoordinate(i - 1), seq.getCoordinate(i)); 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\edgegraph\EdgeGraphBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */