/*    */ package scala.collection;
/*    */ 
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.Nothing$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001E:Q!\001\002\t\002\035\tq\001]1dW\006<WM\003\002\004\t\005Q1m\0347mK\016$\030n\0348\013\003\025\tQa]2bY\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0051BA\004qC\016\\\027mZ3\024\005%a\001CA\007\017\033\005!\021BA\b\005\005\031\te.\037*fM\")\021#\003C\001%\0051A(\0338jiz\"\022a\002\005\006)%!\t!F\001\tEJ,\027m[(viV!acH\025-)\t9b\006E\003\0317uA3&D\001\032\025\tQ\"!A\004hK:,'/[2\n\005qI\"\001D\"b]\n+\030\016\0343Ge>l\007C\001\020 \031\001!Q\001I\nC\002\005\022AA\022:p[F\021!%\n\t\003\033\rJ!\001\n\003\003\0179{G\017[5oOB\021QBJ\005\003O\021\0211!\0218z!\tq\022\006B\003+'\t\007\021EA\001U!\tqB\006B\003.'\t\007\021E\001\002U_\")qf\005a\002a\005\t!\rE\003\0317\tB3\006")
/*    */ public final class package {
/*    */   public static <From, T, To> CanBuildFrom<From, T, To> breakOut(CanBuildFrom<Nothing$, T, To> paramCanBuildFrom) {
/*    */     return package$.MODULE$.breakOut(paramCanBuildFrom);
/*    */   }
/*    */   
/*    */   public static class package$$anon$1 implements CanBuildFrom<From, T, To> {
/*    */     private final CanBuildFrom b$1;
/*    */     
/*    */     public package$$anon$1(CanBuildFrom b$1) {}
/*    */     
/*    */     public Builder<T, To> apply(Object from) {
/* 87 */       return this.b$1.apply();
/*    */     }
/*    */     
/*    */     public Builder<T, To> apply() {
/* 88 */       return this.b$1.apply();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\package.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */