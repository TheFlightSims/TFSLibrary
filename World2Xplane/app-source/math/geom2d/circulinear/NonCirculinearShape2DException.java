/*    */ package math.geom2d.circulinear;
/*    */ 
/*    */ public class NonCirculinearShape2DException extends RuntimeException {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   Object object;
/*    */   
/*    */   public NonCirculinearShape2DException(Object obj) {
/* 26 */     this.object = obj;
/*    */   }
/*    */   
/*    */   public Object getObject() {
/* 30 */     return this.object;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\math\geom2d\circulinear\NonCirculinearShape2DException.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */