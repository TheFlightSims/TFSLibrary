/*    */ package scala.sys;
/*    */ 
/*    */ public final class Prop$ {
/*    */   public static final Prop$ MODULE$;
/*    */   
/*    */   private Prop$() {
/* 72 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> Prop<T> apply(String key, Prop.Creator<T> evidence$1) {
/* 88 */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 88 */     return evidence$1.apply(key);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\Prop$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */