/*    */ package scala.util.automata;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Predef$;
/*    */ import scala.Predef$ArrowAssoc$;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.MapLike;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.immutable.BitSet;
/*    */ import scala.collection.immutable.BitSet$;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.collection.immutable.IndexedSeq$;
/*    */ import scala.collection.immutable.Set$;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.Map;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.RichInt$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\005b!B\001\003\003\003I!a\004(p]\022,GoV8sI\006+Ho\\7\013\005\r!\021\001C1vi>l\027\r^1\013\005\0251\021\001B;uS2T\021aB\001\006g\016\fG.Y\002\001+\tQac\005\002\001\027A\021A\"D\007\002\r%\021aB\002\002\007\003:L(+\0324\t\013A\001A\021A\t\002\rqJg.\033;?)\005\021\002cA\n\001)5\t!\001\005\002\026-1\001A!B\f\001\005\004A\"!\001+\022\005eY\001C\001\007\033\023\tYbAA\004O_RD\027N\\4\t\017u\001!\031!D\001=\0059an\035;bi\026\034X#A\020\021\0051\001\023BA\021\007\005\rIe\016\036\005\bG\001\021\rQ\"\001%\003\031a\027MY3mgV\tQ\005E\002']Qq!a\n\027\017\005!ZS\"A\025\013\005)B\021A\002\037s_>$h(C\001\b\023\tic!A\004qC\016\\\027mZ3\n\005=\002$aA*fc*\021QF\002\005\be\001\021\rQ\"\0014\003\0311\027N\\1mgV\tA\007E\002\rk}I!A\016\004\003\013\005\023(/Y=\t\017a\002!\031!D\001s\005)A-\0327uCV\t!\bE\002\rkm\002B\001P!\025\0076\tQH\003\002?\0059Q.\036;bE2,'B\001!\007\003)\031w\016\0347fGRLwN\\\005\003\005v\0221!T1q!\t!u)D\001F\025\t1u(A\005j[6,H/\0312mK&\021\001*\022\002\007\005&$8+\032;\t\017)\003!\031!D\001\027\0069A-\0324bk2$X#\001'\021\0071)4\tC\003O\001\021\025q*A\004jg\032Kg.\0317\025\005A\033\006C\001\007R\023\t\021fAA\004C_>dW-\0318\t\013Qk\005\031A\020\002\013M$\030\r^3\t\013Y\003AQA,\002\021\031Lg.\0317UC\036$\"a\b-\t\013Q+\006\031A\020\t\013i\003AQA.\002\033\r|g\016^1j]N4\025N\\1m)\t\001F\fC\003^3\002\0071)A\001R\021\025y\006\001\"\002a\003\035I7/R7qif,\022\001\025\005\006E\002!\taY\001\005]\026DH\017F\002DI\032DQ!Z1A\002}\t\021!\035\005\006O\006\004\r\001F\001\002C\")!\r\001C\001SR\0311I[6\t\013uC\007\031A\"\t\013\035D\007\031\001\013\t\0135\004A\021\0018\002\0279,\007\020\036#fM\006,H\016\036\013\003\007>DQ!\0307A\002\rCQA\031\001\005\nE$2a\021:t\021\025i\006\0171\001D\021\025!\b\0171\001v\003\0051\007\003\002\007w?\rK!a\036\004\003\023\031+hn\031;j_:\f\004\"B=\001\t\023Q\030a\0034j]\006d7\013^1uKN,\022a\037\t\004\tr|\022BA?F\005)Ie\016Z3yK\022\034V-\035\005\007\002!\t%!\001\002\021Q|7\013\036:j]\036$\"!a\001\021\t\005\025\0211\002\b\004\031\005\035\021bAA\005\r\0051\001K]3eK\032LA!!\004\002\020\t11\013\036:j]\036T1!!\003\007Q\035\001\0211CA\r\003;\0012\001DA\013\023\r\t9B\002\002\013I\026\004(/Z2bi\026$\027EAA\016\003i!\006.[:!G2\f7o\035\021xS2d\007EY3!e\026lwN^3eC\t\ty\"\001\0043]E\002d\006\r")
/*    */ public abstract class NondetWordAutom<T> {
/*    */   public abstract int nstates();
/*    */   
/*    */   public abstract Seq<T> labels();
/*    */   
/*    */   public abstract int[] finals();
/*    */   
/*    */   public abstract Map<T, BitSet>[] delta();
/*    */   
/*    */   public abstract BitSet[] default();
/*    */   
/*    */   public final boolean isFinal(int state) {
/* 29 */     return (finals()[state] > 0);
/*    */   }
/*    */   
/*    */   public final int finalTag(int state) {
/* 32 */     return finals()[state];
/*    */   }
/*    */   
/*    */   public final boolean containsFinal(BitSet Q) {
/* 35 */     return Q.exists((Function1)new NondetWordAutom$$anonfun$containsFinal$1(this));
/*    */   }
/*    */   
/*    */   public class NondetWordAutom$$anonfun$containsFinal$1 extends AbstractFunction1.mcZI.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(int state) {
/* 35 */       return this.$outer.isFinal(state);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZI$sp(int state) {
/* 35 */       return this.$outer.isFinal(state);
/*    */     }
/*    */     
/*    */     public NondetWordAutom$$anonfun$containsFinal$1(NondetWordAutom $outer) {}
/*    */   }
/*    */   
/*    */   public final boolean isEmpty() {
/* 38 */     Predef$ predef$ = Predef$.MODULE$;
/* 38 */     return RichInt$.MODULE$.until$extension0(0, nstates()).forall((Function1)new NondetWordAutom$$anonfun$isEmpty$1(this));
/*    */   }
/*    */   
/*    */   public class NondetWordAutom$$anonfun$isEmpty$1 extends AbstractFunction1.mcZI.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(int x) {
/* 38 */       return apply$mcZI$sp(x);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZI$sp(int x) {
/* 38 */       return !this.$outer.isFinal(x);
/*    */     }
/*    */     
/*    */     public NondetWordAutom$$anonfun$isEmpty$1(NondetWordAutom $outer) {}
/*    */   }
/*    */   
/*    */   public BitSet next(int q, Object a) {
/* 41 */     return (BitSet)delta()[q].getOrElse(a, (Function0)new NondetWordAutom$$anonfun$next$1(this, q));
/*    */   }
/*    */   
/*    */   public class NondetWordAutom$$anonfun$next$1 extends AbstractFunction0<BitSet> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final int q$1;
/*    */     
/*    */     public final BitSet apply() {
/* 41 */       return this.$outer.default()[this.q$1];
/*    */     }
/*    */     
/*    */     public NondetWordAutom$$anonfun$next$1(NondetWordAutom $outer, int q$1) {}
/*    */   }
/*    */   
/*    */   public BitSet next(BitSet Q, Object a) {
/* 44 */     return next(Q, (Function1<Object, BitSet>)new NondetWordAutom$$anonfun$next$2(this, (NondetWordAutom<T>)a));
/*    */   }
/*    */   
/*    */   public class NondetWordAutom$$anonfun$next$2 extends AbstractFunction1<Object, BitSet> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Object a$1;
/*    */     
/*    */     public final BitSet apply(int x$1) {
/* 44 */       return this.$outer.next(x$1, this.a$1);
/*    */     }
/*    */     
/*    */     public NondetWordAutom$$anonfun$next$2(NondetWordAutom $outer, Object a$1) {}
/*    */   }
/*    */   
/*    */   public BitSet nextDefault(BitSet Q) {
/* 45 */     return next(Q, (Function1<Object, BitSet>)Predef$.MODULE$.wrapRefArray((Object[])default()));
/*    */   }
/*    */   
/*    */   private BitSet next(BitSet Q, Function1 f) {
/* 48 */     return (BitSet)((TraversableOnce)Q.map(f, Set$.MODULE$.canBuildFrom())).foldLeft(BitSet$.MODULE$.empty(), (Function2)new NondetWordAutom$$anonfun$next$3(this));
/*    */   }
/*    */   
/*    */   public class NondetWordAutom$$anonfun$next$3 extends AbstractFunction2<BitSet, BitSet, BitSet> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final BitSet apply(BitSet x$2, BitSet x$3) {
/* 48 */       return (BitSet)x$2.$plus$plus((GenTraversableOnce)x$3);
/*    */     }
/*    */     
/*    */     public NondetWordAutom$$anonfun$next$3(NondetWordAutom $outer) {}
/*    */   }
/*    */   
/*    */   private IndexedSeq<Object> finalStates() {
/* 50 */     Predef$ predef$ = Predef$.MODULE$;
/* 50 */     return (IndexedSeq<Object>)RichInt$.MODULE$.until$extension0(0, nstates()).filter((Function1)new NondetWordAutom$$anonfun$finalStates$1(this));
/*    */   }
/*    */   
/*    */   public class NondetWordAutom$$anonfun$finalStates$1 extends AbstractFunction1.mcZI.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final boolean apply(int state) {
/* 50 */       return this.$outer.isFinal(state);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZI$sp(int state) {
/* 50 */       return this.$outer.isFinal(state);
/*    */     }
/*    */     
/*    */     public NondetWordAutom$$anonfun$finalStates$1(NondetWordAutom $outer) {}
/*    */   }
/*    */   
/*    */   public String toString() {
/* 53 */     String finalString = ((MapLike)Predef$.MODULE$.Map().apply((Seq)finalStates().map((Function1)new NondetWordAutom$$anonfun$1(this), IndexedSeq$.MODULE$.canBuildFrom()))).toString();
/* 54 */     Predef$ predef$1 = Predef$.MODULE$;
/* 54 */     String deltaString = ((TraversableOnce)RichInt$.MODULE$.until$extension0(0, nstates())
/* 55 */       .map((Function1)new NondetWordAutom$$anonfun$2(this), IndexedSeq$.MODULE$.canBuildFrom())).mkString();
/* 57 */     Predef$ predef$2 = Predef$.MODULE$;
/* 57 */     return (new StringOps("[NondetWordAutom  nstates=%d  finals=%s  delta=\n%s")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(nstates()), finalString, deltaString }));
/*    */   }
/*    */   
/*    */   public class NondetWordAutom$$anonfun$1 extends AbstractFunction1<Object, Tuple2<Object, Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Tuple2<Object, Object> apply(int j) {
/*    */       Integer integer1 = BoxesRunTime.boxToInteger(j);
/*    */       Predef$ predef$ = Predef$.MODULE$;
/*    */       Integer integer2 = BoxesRunTime.boxToInteger(this.$outer.finals()[j]);
/*    */       Predef$ArrowAssoc$ predef$ArrowAssoc$ = Predef$ArrowAssoc$.MODULE$;
/*    */       return new Tuple2(integer1, integer2);
/*    */     }
/*    */     
/*    */     public NondetWordAutom$$anonfun$1(NondetWordAutom $outer) {}
/*    */   }
/*    */   
/*    */   public class NondetWordAutom$$anonfun$2 extends AbstractFunction1<Object, String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply(int i) {
/*    */       Predef$ predef$ = Predef$.MODULE$;
/*    */       return (new StringOps("   %d->%s\n    _>%s\n")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(i), this.$outer.delta()[i], this.$outer.default()[i] }));
/*    */     }
/*    */     
/*    */     public NondetWordAutom$$anonfun$2(NondetWordAutom $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\automata\NondetWordAutom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */