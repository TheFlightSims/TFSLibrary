/*    */ package org.geotools.referencing.datum;
/*    */ 
/*    */ import java.util.Map;
/*    */ import javax.measure.quantity.Length;
/*    */ import javax.measure.unit.Unit;
/*    */ 
/*    */ final class Spheroid extends DefaultEllipsoid {
/*    */   private static final long serialVersionUID = 7867565381280669821L;
/*    */   
/*    */   protected Spheroid(Map<String, ?> properties, double radius, boolean ivfDefinitive, Unit<Length> unit) {
/* 52 */     super(properties, check("radius", radius), radius, Double.POSITIVE_INFINITY, ivfDefinitive, unit);
/*    */   }
/*    */   
/*    */   public double orthodromicDistance(double x1, double y1, double x2, double y2) {
/* 77 */     y1 = Math.toRadians(y1);
/* 78 */     y2 = Math.toRadians(y2);
/* 79 */     double dx = Math.toRadians(Math.abs(x2 - x1) % 360.0D);
/* 80 */     double rho = Math.sin(y1) * Math.sin(y2) + Math.cos(y1) * Math.cos(y2) * Math.cos(dx);
/* 81 */     assert Math.abs(rho) < 1.0000001D : rho;
/* 82 */     if (rho > 1.0D)
/* 82 */       rho = 1.0D; 
/* 83 */     if (rho < -1.0D)
/* 83 */       rho = -1.0D; 
/* 84 */     double distance = Math.acos(rho) * getSemiMajorAxis();
/*    */     try {
/*    */       double delta;
/* 93 */       assert (delta = Math.abs(super.orthodromicDistance(x1, Math.toDegrees(y1), x2, Math.toDegrees(y2)) - distance)) < getSemiMajorAxis() / 1.0E9D : delta;
/* 94 */     } catch (ArithmeticException exception) {}
/* 99 */     return distance;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\datum\Spheroid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */