/*    */ package com.world2xplane.Geom.Geom3D;
/*    */ 
/*    */ public class EdgeDistancePack implements Comparable<EdgeDistancePack> {
/*    */   Edge edge;
/*    */   
/*    */   double distance;
/*    */   
/*    */   public EdgeDistancePack(Edge edge, double distance) {
/* 22 */     this.edge = edge;
/* 23 */     this.distance = distance;
/*    */   }
/*    */   
/*    */   public int compareTo(EdgeDistancePack o) {
/* 28 */     if (o.distance == this.distance)
/* 29 */       return 0; 
/* 30 */     if (o.distance < this.distance)
/* 31 */       return 1; 
/* 33 */     return -1;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\world2xplane\Geom\Geom3D\EdgeDistancePack.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */