/*    */ package scala.util;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ public class MurmurHash$mcD$sp extends MurmurHash<Object> implements Function1.mcVD.sp {
/*    */   private final int seed;
/*    */   
/*    */   public MurmurHash$mcD$sp(int seed) {
/* 31 */     super(seed);
/* 31 */     Function1.mcVD.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public void apply(double t) {
/* 49 */     apply$mcD$sp(t);
/*    */   }
/*    */   
/*    */   public void apply$mcD$sp(double t) {
/* 50 */     scala$util$MurmurHash$$h_$eq(MurmurHash$.MODULE$.extendHash(scala$util$MurmurHash$$h(), ScalaRunTime$.MODULE$.hash(t), scala$util$MurmurHash$$c(), scala$util$MurmurHash$$k()));
/* 51 */     scala$util$MurmurHash$$c_$eq(MurmurHash$.MODULE$.nextMagicA(scala$util$MurmurHash$$c()));
/* 52 */     scala$util$MurmurHash$$k_$eq(MurmurHash$.MODULE$.nextMagicB(scala$util$MurmurHash$$k()));
/* 53 */     scala$util$MurmurHash$$hashed_$eq(false);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\MurmurHash$mcD$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */