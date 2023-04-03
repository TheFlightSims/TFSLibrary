/*    */ package akka.japi.pf;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001U;a!\001\002\t\002\tA\021!D\"bg\026\034F/\031;f[\026tGO\003\002\004\t\005\021\001O\032\006\003\013\031\tAA[1qS*\tq!\001\003bW.\f\007CA\005\013\033\005\021aAB\006\003\021\003\021ABA\007DCN,7\013^1uK6,g\016^\n\003\0255\001\"AD\t\016\003=Q\021\001E\001\006g\016\fG.Y\005\003%=\021a!\0218z%\0264\007\"\002\013\013\t\0031\022A\002\037j]&$hh\001\001\025\003!AQ\001\007\006\005\002e\tQ!Z7qif,2A\007\021+)\005Y\002\003\002\b\035=%J!!H\b\003\037A\013'\017^5bY\032+hn\031;j_:\004\"a\b\021\r\001\021)\021e\006b\001E\t\ta)\005\002$MA\021a\002J\005\003K=\021qAT8uQ&tw\r\005\002\017O%\021\001f\004\002\004\003:L\bCA\020+\t\025YsC1\001#\005\005!f!B\006\003\001\tiS\003\002\0302\007N\0322\001L\0070!\021qA\004\r\032\021\005}\tD!B\021-\005\004\021\003CA\0204\t\025YCF1\001#\021!)DF!A!\002\0231\024!\0039sK\022L7-\031;f!\t9$H\004\002\nq%\021\021HA\001\003\r&K!a\017\037\003\023A\023X\rZ5dCR,'BA\035\003\021!qDF!A!\002\023y\024!B1qa2L\b\003B\034A\005JJ!!\021\037\003\013\005\003\b\017\\=\021\005}\031E!\002#-\005\004\021#!\001)\t\013QaC\021\001$\025\007\035C\025\nE\003\nYA\022%\007C\0036\013\002\007a\007C\003?\013\002\007q\bC\003LY\021\005C*A\006jg\022+g-\0338fI\006#HCA'Q!\tqa*\003\002P\037\t9!i\\8mK\006t\007\"B)K\001\004\001\024!A8\t\013ybC\021I*\025\005I\"\006\"B)S\001\004\001\004")
/*    */ public class CaseStatement<F, P, T> implements PartialFunction<F, T> {
/*    */   private final FI.Predicate predicate;
/*    */   
/*    */   private final FI.Apply<P, T> apply;
/*    */   
/*    */   public <A1 extends F, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 13 */     return PartialFunction.class.orElse(this, that);
/*    */   }
/*    */   
/*    */   public <C> PartialFunction<F, C> andThen(Function1 k) {
/* 13 */     return PartialFunction.class.andThen(this, k);
/*    */   }
/*    */   
/*    */   public Function1<F, Option<T>> lift() {
/* 13 */     return PartialFunction.class.lift(this);
/*    */   }
/*    */   
/*    */   public <A1 extends F, B1> B1 applyOrElse(Object x, Function1 default) {
/* 13 */     return (B1)PartialFunction.class.applyOrElse(this, x, default);
/*    */   }
/*    */   
/*    */   public <U> Function1<F, Object> runWith(Function1 action) {
/* 13 */     return PartialFunction.class.runWith(this, action);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZD$sp(double v1) {
/* 13 */     return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDD$sp(double v1) {
/* 13 */     return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFD$sp(double v1) {
/* 13 */     return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcID$sp(double v1) {
/* 13 */     return Function1.class.apply$mcID$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJD$sp(double v1) {
/* 13 */     return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVD$sp(double v1) {
/* 13 */     Function1.class.apply$mcVD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZF$sp(float v1) {
/* 13 */     return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDF$sp(float v1) {
/* 13 */     return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFF$sp(float v1) {
/* 13 */     return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcIF$sp(float v1) {
/* 13 */     return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJF$sp(float v1) {
/* 13 */     return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVF$sp(float v1) {
/* 13 */     Function1.class.apply$mcVF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZI$sp(int v1) {
/* 13 */     return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDI$sp(int v1) {
/* 13 */     return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFI$sp(int v1) {
/* 13 */     return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcII$sp(int v1) {
/* 13 */     return Function1.class.apply$mcII$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJI$sp(int v1) {
/* 13 */     return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVI$sp(int v1) {
/* 13 */     Function1.class.apply$mcVI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZJ$sp(long v1) {
/* 13 */     return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDJ$sp(long v1) {
/* 13 */     return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFJ$sp(long v1) {
/* 13 */     return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcIJ$sp(long v1) {
/* 13 */     return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJJ$sp(long v1) {
/* 13 */     return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVJ$sp(long v1) {
/* 13 */     Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, T> compose(Function1 g) {
/* 13 */     return Function1.class.compose((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcZD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcDD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcFD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcID$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcJD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcVD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcZF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcDF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcFF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcIF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcJF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcVF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcZI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcDI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcFI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcII$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcJI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcVI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 13 */     return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcID$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcII$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 13 */     return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 13 */     return Function1.class.toString((Function1)this);
/*    */   }
/*    */   
/*    */   public CaseStatement(FI.Predicate predicate, FI.Apply<P, T> apply) {
/* 13 */     Function1.class.$init$((Function1)this);
/* 13 */     PartialFunction.class.$init$(this);
/*    */   }
/*    */   
/*    */   public boolean isDefinedAt(Object o) {
/* 16 */     return this.predicate.defined(o);
/*    */   }
/*    */   
/*    */   public T apply(Object o) {
/* 18 */     return this.apply.apply((P)o);
/*    */   }
/*    */   
/*    */   public static <F, T> PartialFunction<F, T> empty() {
/*    */     return CaseStatement$.MODULE$.empty();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\pf\CaseStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */