/*    */ package math.geom2d;
/*    */ 
/*    */ public class UnboundedBox2DException extends RuntimeException {
/*    */   private Box2D box;
/*    */   
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public UnboundedBox2DException(Box2D box) {
/* 24 */     this.box = box;
/*    */   }
/*    */   
/*    */   public Box2D getBox() {
/* 28 */     return this.box;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\UnboundedBox2DException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */