/*    */ package akka.japi.pf;
/*    */ 
/*    */ public class ReceiveBuilder {
/*    */   public static <P> UnitPFBuilder<Object> match(Class<P> paramClass, FI.UnitApply<P> paramUnitApply) {
/* 46 */     return UnitMatch.match(paramClass, paramUnitApply);
/*    */   }
/*    */   
/*    */   public static <P> UnitPFBuilder<Object> match(Class<P> paramClass, FI.TypedPredicate<P> paramTypedPredicate, FI.UnitApply<P> paramUnitApply) {
/* 60 */     return UnitMatch.match(paramClass, paramTypedPredicate, paramUnitApply);
/*    */   }
/*    */   
/*    */   public static <P> UnitPFBuilder<Object> matchEquals(P paramP, FI.UnitApply<P> paramUnitApply) {
/* 71 */     return UnitMatch.matchEquals(paramP, paramUnitApply);
/*    */   }
/*    */   
/*    */   public static <P> UnitPFBuilder<Object> matchEquals(P paramP, FI.TypedPredicate<P> paramTypedPredicate, FI.UnitApply<P> paramUnitApply) {
/* 85 */     return UnitMatch.matchEquals(paramP, paramTypedPredicate, paramUnitApply);
/*    */   }
/*    */   
/*    */   public static UnitPFBuilder<Object> matchAny(FI.UnitApply<Object> paramUnitApply) {
/* 95 */     return UnitMatch.matchAny(paramUnitApply);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\pf\ReceiveBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */