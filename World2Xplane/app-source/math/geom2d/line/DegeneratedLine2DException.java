/*    */ package math.geom2d.line;
/*    */ 
/*    */ public class DegeneratedLine2DException extends RuntimeException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   protected LinearShape2D line;
/*    */   
/*    */   public DegeneratedLine2DException(String msg, LinearShape2D line) {
/* 34 */     super(msg);
/* 35 */     this.line = line;
/*    */   }
/*    */   
/*    */   public DegeneratedLine2DException(LinearShape2D line) {
/* 43 */     this.line = line;
/*    */   }
/*    */   
/*    */   public LinearShape2D getLine() {
/* 47 */     return this.line;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\line\DegeneratedLine2DException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */