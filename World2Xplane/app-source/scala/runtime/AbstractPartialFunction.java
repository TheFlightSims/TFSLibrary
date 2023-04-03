/*    */ package scala.runtime;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.PartialFunction$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001=4Q!\001\002\002\002\035\021q#\0212tiJ\f7\r\036)beRL\027\r\034$v]\016$\030n\0348\013\005\r!\021a\002:v]RLW.\032\006\002\013\005)1oY1mC\016\001Qc\001\005\023\025N!\001!C\007c!\tQ1\"D\001\005\023\taAA\001\004B]f\024VM\032\t\005\0259\001\022*\003\002\020\t\tIa)\0368di&|g.\r\t\003#Ia\001\001B\005\024\001\001\006\t\021#b\001)\t\021A+M\t\003+a\001\"A\003\f\n\005]!!a\002(pi\"Lgn\032\t\003\025eI!A\007\003\003\007\005s\027\020K\004\0239}Icf\r\035\021\005)i\022B\001\020\005\005-\031\b/Z2jC2L'0\03232\013\r\002c\005K\024\017\005\0052cB\001\022&\033\005\031#B\001\023\007\003\031a$o\\8u}%\tQ!\003\002(\t\005\031\021J\034;2\t\021\nS%B\031\006G)ZS\006\f\b\003C-J!\001\f\003\002\t1{gnZ\031\005I\005*S!M\003$_A\022\024G\004\002\"a%\021\021\007B\001\006\r2|\027\r^\031\005I\005*S!M\003$iU:dG\004\002\"k%\021a\007B\001\007\t>,(\r\\32\t\021\nS%B\031\006Ge\032U\t\022\n\004u%\001e\001B\036\001\001e\022A\002\020:fM&tW-\\3oizJ!!\020 \002\017\005s\027PU3gA)\021q\bB\001\ba\006\0347.Y4f!\tQ\021)\003\002C\t\ti1\013]3dS\006d\027N_1cY\026L!\001\022 \002\r\005s\027PU3gc\025\031ci\022%@\035\t\ts)\003\002@\tE\"A%I\023\006!\t\t\"\nB\005L\001\001\006\t\021\"b\001)\t\t!\013K\005K95\023v+W.^?F*1ET(R!:\021\021eT\005\003!\022\tA!\0268jiF\"A%I\023\006c\025\0313\013\026,V\035\t\tC+\003\002V\t\0059!i\\8mK\006t\027\007\002\023\"K\025\tTa\t\021'1\036\nD\001J\021&\013E*1e\f\031[cE\"A%I\023\006c\025\031#f\013/-c\021!\023%J\0032\013\r\"TG\030\0342\t\021\nS%B\031\006Ge\032\005\rR\031\006G\031;\025mP\031\005I\005*S\001\005\003\013GBI\025B\0013\005\005=\001\026M\035;jC24UO\\2uS>t\007\"\0024\001\t\0039\027A\002\037j]&$h\bF\001i!\021I\007\001E%\016\003\tAQa\033\001\005\0021\fQ!\0319qYf$\"!S7\t\0139T\007\031\001\t\002\003a\004")
/*    */ public abstract class AbstractPartialFunction<T1, R> implements Function1<T1, R>, PartialFunction<T1, R> {
/*    */   public <A1 extends T1, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 25 */     return PartialFunction.class.orElse(this, that);
/*    */   }
/*    */   
/*    */   public <C> PartialFunction<T1, C> andThen(Function1 k) {
/* 25 */     return PartialFunction.class.andThen(this, k);
/*    */   }
/*    */   
/*    */   public Function1<T1, Option<R>> lift() {
/* 25 */     return PartialFunction.class.lift(this);
/*    */   }
/*    */   
/*    */   public <A1 extends T1, B1> B1 applyOrElse(Object x, Function1 default) {
/* 25 */     return (B1)PartialFunction.class.applyOrElse(this, x, default);
/*    */   }
/*    */   
/*    */   public <U> Function1<T1, Object> runWith(Function1 action) {
/* 25 */     return PartialFunction.class.runWith(this, action);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, R> compose(Function1 g) {
/* 25 */     return Function1.class.compose(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcZD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcDD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcFD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcID$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcJD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcVD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcZF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcDF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcFF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcIF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcJF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcVF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcZI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcDI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcFI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcII$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcJI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcVI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcZJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcDJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcFJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcIJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcJJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 25 */     return Function1.class.compose$mcVJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcZD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcDD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcFD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcID$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcJD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcVD$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcZF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcDF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcFF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcIF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcJF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcVF$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcZI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcDI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcFI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcII$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcJI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcVI$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcZJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcDJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcFJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcIJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcJJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 25 */     return Function1.class.andThen$mcVJ$sp(this, g);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 25 */     return Function1.class.toString(this);
/*    */   }
/*    */   
/*    */   public AbstractPartialFunction() {
/* 25 */     Function1.class.$init$(this);
/* 25 */     PartialFunction.class.$init$(this);
/*    */   }
/*    */   
/*    */   public R apply(Object x) {
/* 33 */     return applyOrElse(x, (Function1<Object, R>)PartialFunction$.MODULE$.empty());
/*    */   }
/*    */   
/*    */   public boolean apply$mcZD$sp(double x) {
/* 33 */     return BoxesRunTime.unboxToBoolean(apply((T1)BoxesRunTime.boxToDouble(x)));
/*    */   }
/*    */   
/*    */   public double apply$mcDD$sp(double x) {
/* 33 */     return BoxesRunTime.unboxToDouble(apply((T1)BoxesRunTime.boxToDouble(x)));
/*    */   }
/*    */   
/*    */   public float apply$mcFD$sp(double x) {
/* 33 */     return BoxesRunTime.unboxToFloat(apply((T1)BoxesRunTime.boxToDouble(x)));
/*    */   }
/*    */   
/*    */   public int apply$mcID$sp(double x) {
/* 33 */     return BoxesRunTime.unboxToInt(apply((T1)BoxesRunTime.boxToDouble(x)));
/*    */   }
/*    */   
/*    */   public long apply$mcJD$sp(double x) {
/* 33 */     return BoxesRunTime.unboxToLong(apply((T1)BoxesRunTime.boxToDouble(x)));
/*    */   }
/*    */   
/*    */   public R apply$mcLD$sp(double x) {
/* 33 */     return apply((T1)BoxesRunTime.boxToDouble(x));
/*    */   }
/*    */   
/*    */   public void apply$mcVD$sp(double x) {
/* 33 */     apply((T1)BoxesRunTime.boxToDouble(x));
/*    */   }
/*    */   
/*    */   public boolean apply$mcZF$sp(float x) {
/* 33 */     return BoxesRunTime.unboxToBoolean(apply((T1)BoxesRunTime.boxToFloat(x)));
/*    */   }
/*    */   
/*    */   public double apply$mcDF$sp(float x) {
/* 33 */     return BoxesRunTime.unboxToDouble(apply((T1)BoxesRunTime.boxToFloat(x)));
/*    */   }
/*    */   
/*    */   public float apply$mcFF$sp(float x) {
/* 33 */     return BoxesRunTime.unboxToFloat(apply((T1)BoxesRunTime.boxToFloat(x)));
/*    */   }
/*    */   
/*    */   public int apply$mcIF$sp(float x) {
/* 33 */     return BoxesRunTime.unboxToInt(apply((T1)BoxesRunTime.boxToFloat(x)));
/*    */   }
/*    */   
/*    */   public long apply$mcJF$sp(float x) {
/* 33 */     return BoxesRunTime.unboxToLong(apply((T1)BoxesRunTime.boxToFloat(x)));
/*    */   }
/*    */   
/*    */   public R apply$mcLF$sp(float x) {
/* 33 */     return apply((T1)BoxesRunTime.boxToFloat(x));
/*    */   }
/*    */   
/*    */   public void apply$mcVF$sp(float x) {
/* 33 */     apply((T1)BoxesRunTime.boxToFloat(x));
/*    */   }
/*    */   
/*    */   public boolean apply$mcZI$sp(int x) {
/* 33 */     return BoxesRunTime.unboxToBoolean(apply((T1)BoxesRunTime.boxToInteger(x)));
/*    */   }
/*    */   
/*    */   public double apply$mcDI$sp(int x) {
/* 33 */     return BoxesRunTime.unboxToDouble(apply((T1)BoxesRunTime.boxToInteger(x)));
/*    */   }
/*    */   
/*    */   public float apply$mcFI$sp(int x) {
/* 33 */     return BoxesRunTime.unboxToFloat(apply((T1)BoxesRunTime.boxToInteger(x)));
/*    */   }
/*    */   
/*    */   public int apply$mcII$sp(int x) {
/* 33 */     return BoxesRunTime.unboxToInt(apply((T1)BoxesRunTime.boxToInteger(x)));
/*    */   }
/*    */   
/*    */   public long apply$mcJI$sp(int x) {
/* 33 */     return BoxesRunTime.unboxToLong(apply((T1)BoxesRunTime.boxToInteger(x)));
/*    */   }
/*    */   
/*    */   public R apply$mcLI$sp(int x) {
/* 33 */     return apply((T1)BoxesRunTime.boxToInteger(x));
/*    */   }
/*    */   
/*    */   public void apply$mcVI$sp(int x) {
/* 33 */     apply((T1)BoxesRunTime.boxToInteger(x));
/*    */   }
/*    */   
/*    */   public boolean apply$mcZJ$sp(long x) {
/* 33 */     return BoxesRunTime.unboxToBoolean(apply((T1)BoxesRunTime.boxToLong(x)));
/*    */   }
/*    */   
/*    */   public double apply$mcDJ$sp(long x) {
/* 33 */     return BoxesRunTime.unboxToDouble(apply((T1)BoxesRunTime.boxToLong(x)));
/*    */   }
/*    */   
/*    */   public float apply$mcFJ$sp(long x) {
/* 33 */     return BoxesRunTime.unboxToFloat(apply((T1)BoxesRunTime.boxToLong(x)));
/*    */   }
/*    */   
/*    */   public int apply$mcIJ$sp(long x) {
/* 33 */     return BoxesRunTime.unboxToInt(apply((T1)BoxesRunTime.boxToLong(x)));
/*    */   }
/*    */   
/*    */   public long apply$mcJJ$sp(long x) {
/* 33 */     return BoxesRunTime.unboxToLong(apply((T1)BoxesRunTime.boxToLong(x)));
/*    */   }
/*    */   
/*    */   public R apply$mcLJ$sp(long x) {
/* 33 */     return apply((T1)BoxesRunTime.boxToLong(x));
/*    */   }
/*    */   
/*    */   public void apply$mcVJ$sp(long x) {
/* 33 */     apply((T1)BoxesRunTime.boxToLong(x));
/*    */   }
/*    */   
/*    */   public boolean apply$mcZL$sp(Object x) {
/* 33 */     return BoxesRunTime.unboxToBoolean(apply((T1)x));
/*    */   }
/*    */   
/*    */   public double apply$mcDL$sp(Object x) {
/* 33 */     return BoxesRunTime.unboxToDouble(apply((T1)x));
/*    */   }
/*    */   
/*    */   public float apply$mcFL$sp(Object x) {
/* 33 */     return BoxesRunTime.unboxToFloat(apply((T1)x));
/*    */   }
/*    */   
/*    */   public int apply$mcIL$sp(Object x) {
/* 33 */     return BoxesRunTime.unboxToInt(apply((T1)x));
/*    */   }
/*    */   
/*    */   public long apply$mcJL$sp(Object x) {
/* 33 */     return BoxesRunTime.unboxToLong(apply((T1)x));
/*    */   }
/*    */   
/*    */   public void apply$mcVL$sp(Object x) {
/* 33 */     apply((T1)x);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\AbstractPartialFunction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */