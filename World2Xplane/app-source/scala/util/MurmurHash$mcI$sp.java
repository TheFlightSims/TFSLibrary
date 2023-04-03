/*    */ package scala.util;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public class MurmurHash$mcI$sp extends MurmurHash<Object> implements Function1.mcVI.sp {
/*    */   private final int seed;
/*    */   
/*    */   public MurmurHash$mcI$sp(int seed) {
/* 31 */     super(seed);
/* 31 */     Function1.mcVI.sp.class.$init$(this);
/*    */   }
/*    */   
/*    */   public void apply(int t) {
/* 49 */     apply$mcI$sp(t);
/*    */   }
/*    */   
/*    */   public void apply$mcI$sp(int t) {
/* 50 */     scala$util$MurmurHash$$h_$eq(MurmurHash$.MODULE$.extendHash(scala$util$MurmurHash$$h(), t, scala$util$MurmurHash$$c(), scala$util$MurmurHash$$k()));
/* 51 */     scala$util$MurmurHash$$c_$eq(MurmurHash$.MODULE$.nextMagicA(scala$util$MurmurHash$$c()));
/* 52 */     scala$util$MurmurHash$$k_$eq(MurmurHash$.MODULE$.nextMagicB(scala$util$MurmurHash$$k()));
/* 53 */     scala$util$MurmurHash$$hashed_$eq(false);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\MurmurHash$mcI$sp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */