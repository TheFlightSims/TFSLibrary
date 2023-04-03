/*    */ package com.vividsolutions.jts.operation.linemerge;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import com.vividsolutions.jts.geom.CoordinateArrays;
/*    */ import com.vividsolutions.jts.geom.LineString;
/*    */ import com.vividsolutions.jts.planargraph.DirectedEdge;
/*    */ import com.vividsolutions.jts.planargraph.Edge;
/*    */ import com.vividsolutions.jts.planargraph.Node;
/*    */ import com.vividsolutions.jts.planargraph.PlanarGraph;
/*    */ 
/*    */ public class LineMergeGraph extends PlanarGraph {
/*    */   public void addEdge(LineString lineString) {
/* 62 */     if (lineString.isEmpty())
/*    */       return; 
/* 64 */     Coordinate[] coordinates = CoordinateArrays.removeRepeatedPoints(lineString.getCoordinates());
/* 67 */     if (coordinates.length <= 1)
/*    */       return; 
/* 69 */     Coordinate startCoordinate = coordinates[0];
/* 70 */     Coordinate endCoordinate = coordinates[coordinates.length - 1];
/* 71 */     Node startNode = getNode(startCoordinate);
/* 72 */     Node endNode = getNode(endCoordinate);
/* 73 */     DirectedEdge directedEdge0 = new LineMergeDirectedEdge(startNode, endNode, coordinates[1], true);
/* 75 */     DirectedEdge directedEdge1 = new LineMergeDirectedEdge(endNode, startNode, coordinates[coordinates.length - 2], false);
/* 77 */     Edge edge = new LineMergeEdge(lineString);
/* 78 */     edge.setDirectedEdges(directedEdge0, directedEdge1);
/* 79 */     add(edge);
/*    */   }
/*    */   
/*    */   private Node getNode(Coordinate coordinate) {
/* 83 */     Node node = findNode(coordinate);
/* 84 */     if (node == null) {
/* 85 */       node = new Node(coordinate);
/* 86 */       add(node);
/*    */     } 
/* 89 */     return node;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\operation\linemerge\LineMergeGraph.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */