/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Serializable;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ public final class WrappedString$ {
/*    */   public static final WrappedString$ MODULE$;
/*    */   
/*    */   private WrappedString$() {
/* 55 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public CanBuildFrom<WrappedString, Object, WrappedString> canBuildFrom() {
/* 56 */     return new WrappedString$$anon$1();
/*    */   }
/*    */   
/*    */   public static class WrappedString$$anon$1 implements CanBuildFrom<WrappedString, Object, WrappedString> {
/*    */     public Builder<Object, WrappedString> apply(WrappedString from) {
/* 57 */       return WrappedString$.MODULE$.newBuilder();
/*    */     }
/*    */     
/*    */     public Builder<Object, WrappedString> apply() {
/* 58 */       return WrappedString$.MODULE$.newBuilder();
/*    */     }
/*    */   }
/*    */   
/*    */   public Builder<Object, WrappedString> newBuilder() {
/* 61 */     scala.collection.mutable.StringBuilder$ stringBuilder$ = scala.collection.mutable.StringBuilder$.MODULE$;
/* 61 */     return Builder.class.mapResult((Builder)new StringBuilder(), (Function1)new WrappedString$$anonfun$newBuilder$1());
/*    */   }
/*    */   
/*    */   public static class WrappedString$$anonfun$newBuilder$1 extends AbstractFunction1<String, WrappedString> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final WrappedString apply(String x) {
/* 61 */       return new WrappedString(x);
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\WrappedString$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */