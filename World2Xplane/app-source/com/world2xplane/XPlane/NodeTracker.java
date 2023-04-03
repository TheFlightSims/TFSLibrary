/*    */ package com.world2xplane.XPlane;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import com.vividsolutions.jts.geom.GeometryFactory;
/*    */ import com.vividsolutions.jts.geom.Point;
/*    */ import com.vividsolutions.jts.index.strtree.STRtree;
/*    */ import com.world2xplane.Rules.NodeTrackerRule;
/*    */ import java.util.List;
/*    */ import math.geom2d.Point2D;
/*    */ 
/*    */ public class NodeTracker {
/* 37 */   public STRtree nodeTree = new STRtree();
/*    */   
/* 38 */   public GeometryFactory geometryFactory = new GeometryFactory();
/*    */   
/*    */   public List<TrackPoint> query(Envelope envelope) {
/* 41 */     return this.nodeTree.query(envelope);
/*    */   }
/*    */   
/*    */   public static class TrackPoint {
/*    */     public NodeTrackerRule nodeTrackerRule;
/*    */     
/*    */     public Point point;
/*    */     
/*    */     public TrackPoint(Point point, NodeTrackerRule rule) {
/* 50 */       this.point = point;
/* 51 */       this.nodeTrackerRule = rule;
/*    */     }
/*    */   }
/*    */   
/*    */   public void addNode(Point2D point2d, NodeTrackerRule rule) {
/* 56 */     Point point = this.geometryFactory.createPoint(new Coordinate(point2d.x(), point2d.y()));
/* 57 */     this.nodeTree.insert(point.getEnvelopeInternal(), new TrackPoint(point, rule));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\XPlane\NodeTracker.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */