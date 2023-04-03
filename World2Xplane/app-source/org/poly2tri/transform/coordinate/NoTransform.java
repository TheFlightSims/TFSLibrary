/*    */ package org.poly2tri.transform.coordinate;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.poly2tri.geometry.primitives.Point;
/*    */ 
/*    */ public class NoTransform implements CoordinateTransform {
/*    */   public void transform(Point p, Point store) {
/* 33 */     store.set(p.getX(), p.getY(), p.getZ());
/*    */   }
/*    */   
/*    */   public void transform(Point p) {}
/*    */   
/*    */   public void transform(List<? extends Point> list) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\poly2tri\transform\coordinate\NoTransform.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */