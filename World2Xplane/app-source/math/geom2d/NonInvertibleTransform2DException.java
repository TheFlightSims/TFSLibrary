/*    */ package math.geom2d;
/*    */ 
/*    */ import math.geom2d.transform.Transform2D;
/*    */ 
/*    */ public class NonInvertibleTransform2DException extends RuntimeException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   protected Transform2D transform;
/*    */   
/*    */   public NonInvertibleTransform2DException() {
/* 24 */     this.transform = null;
/*    */   }
/*    */   
/*    */   public NonInvertibleTransform2DException(Transform2D transform) {
/* 28 */     this.transform = transform;
/*    */   }
/*    */   
/*    */   public Transform2D getTransform() {
/* 32 */     return this.transform;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\NonInvertibleTransform2DException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */