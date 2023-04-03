/*    */ package math.geom2d;
/*    */ 
/*    */ public class ColinearPoints2DException extends RuntimeException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   protected Point2D p1;
/*    */   
/*    */   protected Point2D p2;
/*    */   
/*    */   protected Point2D p3;
/*    */   
/*    */   public ColinearPoints2DException(Point2D p1, Point2D p2, Point2D p3) {
/* 30 */     this.p1 = p1;
/* 31 */     this.p2 = p2;
/* 32 */     this.p3 = p3;
/*    */   }
/*    */   
/*    */   public Point2D getP1() {
/* 36 */     return this.p1;
/*    */   }
/*    */   
/*    */   public Point2D getP2() {
/* 40 */     return this.p2;
/*    */   }
/*    */   
/*    */   public Point2D getP3() {
/* 44 */     return this.p3;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\ColinearPoints2DException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */