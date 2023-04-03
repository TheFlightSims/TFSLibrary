/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.immutable.NumericRange;
/*    */ import scala.collection.immutable.NumericRange$;
/*    */ import scala.collection.immutable.Range;
/*    */ import scala.math.Fractional;
/*    */ import scala.math.Integral;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\00114q!\001\002\021\002\007\005qAA\bGe\006\034G/[8oC2\004&o\034=z\025\t\031A!A\004sk:$\030.\\3\013\003\025\tQa]2bY\006\034\001!\006\002\t'M!\001!C\007\032!\tQ1\"D\001\005\023\taAAA\002B]f\0042AD\b\022\033\005\021\021B\001\t\003\005A\0316-\0317b\035Vl'-\032:Qe>D\030\020\005\002\023'1\001A!\002\013\001\005\004)\"!\001+\022\005YI\001C\001\006\030\023\tABAA\004O_RD\027N\\4\021\0079Q\022#\003\002\034\005\tY!+\0318hK\022\004&o\034=z\021\025i\002\001\"\001\037\003\031!\023N\\5uIQ\tq\004\005\002\013A%\021\021\005\002\002\005+:LG\017C\003$\001\031MA%A\002ok6,\022!\n\t\004M9\nbBA\024-\035\tA3&D\001*\025\tQc!\001\004=e>|GOP\005\002\013%\021Q\006B\001\ba\006\0347.Y4f\023\ty\003G\001\006Ge\006\034G/[8oC2T!!\f\003\t\013I\002a1C\032\002\027%tG/Z4sC2tU/\\\013\002iA\031a%N\t\n\005Y\002$\001C%oi\026<'/\0317\006\ta\002\001!\017\002\022%\026\034X\017\034;XSRDw.\036;Ti\026\004\b\003\002\036>#\021s!AJ\036\n\005q\002\024!\002*b]\036,\027B\001 @\005\035\001\026M\035;jC2T!\001\020!\013\005\005\023\025!C5n[V$\030M\0317f\025\t\031E!\001\006d_2dWm\031;j_:\0042!\022$\022\033\005\001\025BA$A\0051qU/\\3sS\016\024\026M\\4f\021\025I\005\001\"\001K\003\035I7o\0265pY\026$\022a\023\t\003\0251K!!\024\003\003\017\t{w\016\\3b]\")q\n\001C\001!\006)QO\034;jYR\021\021k\025\t\003%^j\021\001\001\005\006):\003\r!E\001\004K:$\007\"B(\001\t\0031FcA,_?B\031\001lW\t\017\005\025K\026B\001.A\0031qU/\\3sS\016\024\026M\\4f\023\taVLA\005Fq\016dWo]5wK*\021!\f\021\005\006)V\003\r!\005\005\006AV\003\r!E\001\005gR,\007\017C\003c\001\021\0051-\001\002u_R\021\021\013\032\005\006)\006\004\r!\005\005\006E\002!\tA\032\013\004O*\\\007c\001-i#%\021\021.\030\002\n\023:\034G.^:jm\026DQ\001V3A\002EAQ\001Y3A\002E\001")
/*    */ public interface FractionalProxy<T> extends ScalaNumberProxy<T>, RangedProxy<T> {
/*    */   Fractional<T> num();
/*    */   
/*    */   Integral<T> integralNum();
/*    */   
/*    */   boolean isWhole();
/*    */   
/*    */   Range.Partial<T, NumericRange<T>> until(T paramT);
/*    */   
/*    */   NumericRange.Exclusive<T> until(T paramT1, T paramT2);
/*    */   
/*    */   Range.Partial<T, NumericRange<T>> to(T paramT);
/*    */   
/*    */   NumericRange.Inclusive<T> to(T paramT1, T paramT2);
/*    */   
/*    */   public class FractionalProxy$$anonfun$until$1 extends AbstractFunction1<T, NumericRange.Exclusive<T>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Object end$1;
/*    */     
/*    */     public final NumericRange.Exclusive<T> apply(Object x$1) {
/* 62 */       return NumericRange$.MODULE$.apply(this.$outer.self(), this.end$1, x$1, this.$outer.integralNum());
/*    */     }
/*    */     
/*    */     public FractionalProxy$$anonfun$until$1(FractionalProxy $outer, Object end$1) {}
/*    */   }
/*    */   
/*    */   public class FractionalProxy$$anonfun$to$1 extends AbstractFunction1<T, NumericRange.Inclusive<T>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Object end$2;
/*    */     
/*    */     public final NumericRange.Inclusive<T> apply(Object x$2) {
/* 64 */       return NumericRange$.MODULE$.inclusive(this.$outer.self(), this.end$2, x$2, this.$outer.integralNum());
/*    */     }
/*    */     
/*    */     public FractionalProxy$$anonfun$to$1(FractionalProxy $outer, Object end$2) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\FractionalProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */