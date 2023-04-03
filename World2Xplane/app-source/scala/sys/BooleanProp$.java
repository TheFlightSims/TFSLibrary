/*    */ package scala.sys;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class BooleanProp$ {
/*    */   public static final BooleanProp$ MODULE$;
/*    */   
/*    */   private BooleanProp$() {
/* 33 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public <T> BooleanProp valueIsTrue(String key) {
/* 62 */     return new BooleanProp.BooleanPropImpl(key, (Function1<String, Object>)new BooleanProp$$anonfun$valueIsTrue$1());
/*    */   }
/*    */   
/*    */   public static class BooleanProp$$anonfun$valueIsTrue$1 extends AbstractFunction1<String, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(String x$1) {
/* 62 */       if (x$1.toLowerCase() == null) {
/* 62 */         x$1.toLowerCase();
/* 62 */         if ("true" != null);
/* 62 */       } else if (x$1.toLowerCase().equals("true")) {
/*    */       
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public <T> BooleanProp keyExists(String key) {
/* 70 */     return new BooleanProp.BooleanPropImpl(key, (Function1<String, Object>)new BooleanProp$$anonfun$keyExists$1());
/*    */   }
/*    */   
/*    */   public static class BooleanProp$$anonfun$keyExists$1 extends AbstractFunction1<String, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(String x$2) {
/* 70 */       return true;
/*    */     }
/*    */   }
/*    */   
/*    */   public BooleanProp constant(String key, boolean isOn) {
/* 74 */     return new BooleanProp.ConstantImpl(key, isOn);
/*    */   }
/*    */   
/*    */   public boolean booleanPropAsBoolean(BooleanProp b) {
/* 76 */     return b.value();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\sys\BooleanProp$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */