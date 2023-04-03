/*    */ package com.vividsolutions.jts.planargraph;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.TreeMap;
/*    */ 
/*    */ public class NodeMap {
/* 53 */   private Map nodeMap = new TreeMap<>();
/*    */   
/*    */   public Node add(Node n) {
/* 67 */     this.nodeMap.put(n.getCoordinate(), n);
/* 68 */     return n;
/*    */   }
/*    */   
/*    */   public Node remove(Coordinate pt) {
/* 76 */     return (Node)this.nodeMap.remove(pt);
/*    */   }
/*    */   
/*    */   public Node find(Coordinate coord) {
/* 82 */     return (Node)this.nodeMap.get(coord);
/*    */   }
/*    */   
/*    */   public Iterator iterator() {
/* 90 */     return this.nodeMap.values().iterator();
/*    */   }
/*    */   
/*    */   public Collection values() {
/* 98 */     return this.nodeMap.values();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\planargraph\NodeMap.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */