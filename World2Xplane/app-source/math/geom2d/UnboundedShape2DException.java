/*    */ package math.geom2d;
/*    */ 
/*    */ public class UnboundedShape2DException extends RuntimeException {
/*    */   private Shape2D shape;
/*    */   
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public UnboundedShape2DException(Shape2D shape) {
/* 22 */     this.shape = shape;
/*    */   }
/*    */   
/*    */   public Shape2D getShape() {
/* 26 */     return this.shape;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\UnboundedShape2DException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */