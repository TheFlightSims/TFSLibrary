/*    */ package org.poly2tri.geometry.primitives;
/*    */ 
/*    */ public abstract class Edge<A extends Point> {
/*    */   protected A p;
/*    */   
/*    */   protected A q;
/*    */   
/*    */   public A getP() {
/* 32 */     return this.p;
/*    */   }
/*    */   
/*    */   public A getQ() {
/* 37 */     return this.q;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\geometry\primitives\Edge.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */