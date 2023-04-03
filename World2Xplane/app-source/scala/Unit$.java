/*    */ package scala;
/*    */ 
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public final class Unit$ implements AnyValCompanion {
/*    */   public static final Unit$ MODULE$;
/*    */   
/*    */   private Unit$() {
/* 25 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public BoxedUnit box(BoxedUnit x) {
/* 32 */     return BoxedUnit.UNIT;
/*    */   }
/*    */   
/*    */   public void unbox(Object x) {}
/*    */   
/*    */   public String toString() {
/* 46 */     return "object scala.Unit";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\Unit$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */