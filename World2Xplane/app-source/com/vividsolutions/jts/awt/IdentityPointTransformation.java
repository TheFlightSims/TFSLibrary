/*    */ package com.vividsolutions.jts.awt;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Coordinate;
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class IdentityPointTransformation implements PointTransformation {
/*    */   public void transform(Coordinate model, Point2D view) {
/* 49 */     view.setLocation(model.x, model.y);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\vividsolutions\jts\awt\IdentityPointTransformation.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */