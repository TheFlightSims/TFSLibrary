/*    */ package com.jhlabs.map.proj;
/*    */ 
/*    */ import com.jhlabs.map.MapMath;
/*    */ import java.awt.geom.Point2D;
/*    */ 
/*    */ public class Eckert4Projection extends Projection {
/*    */   private static final double C_x = 0.4222382003157712D;
/*    */   
/*    */   private static final double C_y = 1.3265004281770023D;
/*    */   
/*    */   private static final double RC_y = 0.7538633073600218D;
/*    */   
/*    */   private static final double C_p = 3.5707963267948966D;
/*    */   
/*    */   private static final double RC_p = 0.2800495767557787D;
/*    */   
/*    */   private static final double EPS = 1.0E-7D;
/*    */   
/* 33 */   private final int NITER = 6;
/*    */   
/*    */   public Point2D.Double project(double lplam, double lpphi, Point2D.Double out) {
/* 39 */     double p = 3.5707963267948966D * Math.sin(lpphi);
/* 40 */     double V = lpphi * lpphi;
/* 41 */     lpphi *= 0.895168D + V * (0.0218849D + V * 0.00826809D);
/* 42 */     int i = 6;
/* 43 */     double c = Math.cos(lpphi);
/* 44 */     double s = Math.sin(lpphi);
/* 45 */     lpphi -= V = (lpphi + s * (c + 2.0D) - p) / (1.0D + c * (c + 2.0D) - s * s);
/* 47 */     for (; i > 0 && Math.abs(V) >= 1.0E-7D; i--);
/* 50 */     if (i == 0) {
/* 51 */       out.x = 0.4222382003157712D * lplam;
/* 52 */       out.y = (lpphi < 0.0D) ? -1.3265004281770023D : 1.3265004281770023D;
/*    */     } else {
/* 54 */       out.x = 0.4222382003157712D * lplam * (1.0D + Math.cos(lpphi));
/* 55 */       out.y = 1.3265004281770023D * Math.sin(lpphi);
/*    */     } 
/* 57 */     return out;
/*    */   }
/*    */   
/*    */   public Point2D.Double projectInverse(double xyx, double xyy, Point2D.Double out) {
/* 63 */     out.y = MapMath.asin(xyy / 1.3265004281770023D);
/*    */     double c;
/* 64 */     out.x = xyx / 0.4222382003157712D * (1.0D + (c = Math.cos(out.y)));
/* 65 */     out.y = MapMath.asin((out.y + Math.sin(out.y) * (c + 2.0D)) / 3.5707963267948966D);
/* 66 */     return out;
/*    */   }
/*    */   
/*    */   public boolean hasInverse() {
/* 70 */     return true;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 74 */     return "Eckert IV";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\jhlabs\map\proj\Eckert4Projection.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */