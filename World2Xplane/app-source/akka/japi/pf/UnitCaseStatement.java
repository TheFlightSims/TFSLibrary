/*    */ package akka.japi.pf;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0253Q!\001\002\001\005!\021\021#\0268ji\016\0137/Z*uCR,W.\0328u\025\t\031A!\001\002qM*\021QAB\001\005U\006\004\030NC\001\b\003\021\t7n[1\026\007%)\"gE\002\001\025A\001\"a\003\b\016\0031Q\021!D\001\006g\016\fG.Y\005\003\0371\021a!\0218z%\0264\007\003B\006\022'}I!A\005\007\003\037A\013'\017^5bY\032+hn\031;j_:\004\"\001F\013\r\001\021)a\003\001b\0011\t\tai\001\001\022\005ea\002CA\006\033\023\tYBBA\004O_RD\027N\\4\021\005-i\022B\001\020\r\005\r\te.\037\t\003\027\001J!!\t\007\003\tUs\027\016\036\005\tG\001\021\t\021)A\005I\005I\001O]3eS\016\fG/\032\t\003K%r!AJ\024\016\003\tI!\001\013\002\002\005\031K\025B\001\026,\005%\001&/\0323jG\006$XM\003\002)\005!AQ\006\001B\001B\003%a&A\003baBd\027\020E\002&_EJ!\001M\026\003\023Us\027\016^!qa2L\bC\001\0133\t\025\031\004A1\001\031\005\005\001\006\"B\033\001\t\0031\024A\002\037j]&$h\bF\0028qe\002BA\n\001\024c!)1\005\016a\001I!)Q\006\016a\001]!)1\b\001C!y\005Y\021n\035#fM&tW\rZ!u)\ti\004\t\005\002\f}%\021q\b\004\002\b\005>|G.Z1o\021\025\t%\b1\001\024\003\005y\007\"B\027\001\t\003\032ECA\020E\021\025\t%\t1\001\024\001")
/*    */ public class UnitCaseStatement<F, P> implements PartialFunction<F, BoxedUnit> {
/*    */   private final FI.Predicate predicate;
/*    */   
/*    */   private final FI.UnitApply<P> apply;
/*    */   
/*    */   public <A1 extends F, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 21 */     return PartialFunction.class.orElse(this, that);
/*    */   }
/*    */   
/*    */   public <C> PartialFunction<F, C> andThen(Function1 k) {
/* 21 */     return PartialFunction.class.andThen(this, k);
/*    */   }
/*    */   
/*    */   public Function1<F, Option<BoxedUnit>> lift() {
/* 21 */     return PartialFunction.class.lift(this);
/*    */   }
/*    */   
/*    */   public <A1 extends F, B1> B1 applyOrElse(Object x, Function1 default) {
/* 21 */     return (B1)PartialFunction.class.applyOrElse(this, x, default);
/*    */   }
/*    */   
/*    */   public <U> Function1<F, Object> runWith(Function1 action) {
/* 21 */     return PartialFunction.class.runWith(this, action);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZD$sp(double v1) {
/* 21 */     return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDD$sp(double v1) {
/* 21 */     return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFD$sp(double v1) {
/* 21 */     return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcID$sp(double v1) {
/* 21 */     return Function1.class.apply$mcID$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJD$sp(double v1) {
/* 21 */     return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVD$sp(double v1) {
/* 21 */     Function1.class.apply$mcVD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZF$sp(float v1) {
/* 21 */     return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDF$sp(float v1) {
/* 21 */     return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFF$sp(float v1) {
/* 21 */     return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcIF$sp(float v1) {
/* 21 */     return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJF$sp(float v1) {
/* 21 */     return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVF$sp(float v1) {
/* 21 */     Function1.class.apply$mcVF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZI$sp(int v1) {
/* 21 */     return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDI$sp(int v1) {
/* 21 */     return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFI$sp(int v1) {
/* 21 */     return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcII$sp(int v1) {
/* 21 */     return Function1.class.apply$mcII$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJI$sp(int v1) {
/* 21 */     return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVI$sp(int v1) {
/* 21 */     Function1.class.apply$mcVI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZJ$sp(long v1) {
/* 21 */     return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDJ$sp(long v1) {
/* 21 */     return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFJ$sp(long v1) {
/* 21 */     return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcIJ$sp(long v1) {
/* 21 */     return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJJ$sp(long v1) {
/* 21 */     return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVJ$sp(long v1) {
/* 21 */     Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose(Function1 g) {
/* 21 */     return Function1.class.compose((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcZD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcDD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcFD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcID$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcJD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcVD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcZF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcDF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcFF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcIF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcJF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcVF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcZI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcDI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcFI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcII$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcJI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcVI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 21 */     return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcID$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcII$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 21 */     return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 21 */     return Function1.class.toString((Function1)this);
/*    */   }
/*    */   
/*    */   public UnitCaseStatement(FI.Predicate predicate, FI.UnitApply<P> apply) {
/* 21 */     Function1.class.$init$((Function1)this);
/* 21 */     PartialFunction.class.$init$(this);
/*    */   }
/*    */   
/*    */   public boolean isDefinedAt(Object o) {
/* 24 */     return this.predicate.defined(o);
/*    */   }
/*    */   
/*    */   public void apply(Object o) {
/* 26 */     this.apply.apply((P)o);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\japi\pf\UnitCaseStatement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */