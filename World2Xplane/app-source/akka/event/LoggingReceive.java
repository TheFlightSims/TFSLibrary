/*    */ package akka.event;
/*    */ 
/*    */ import akka.actor.Actor;
/*    */ import akka.actor.ActorCell;
/*    */ import akka.actor.ActorContext;
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.MatchError;
/*    */ import scala.None$;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001A<Q!\001\002\t\002\035\ta\002T8hO&twMU3dK&4XM\003\002\004\t\005)QM^3oi*\tQ!\001\003bW.\f7\001\001\t\003\021%i\021A\001\004\006\025\tA\ta\003\002\017\031><w-\0338h%\026\034W-\033<f'\tIA\002\005\002\016!5\taBC\001\020\003\025\0318-\0317b\023\t\tbB\001\004B]f\024VM\032\005\006'%!\t\001F\001\007y%t\027\016\036 \025\003\035AQAF\005\005\002]\tQ!\0319qYf$\"\001G\030\025\005eI\003C\001\016'\035\tY2E\004\002\035C9\021Q\004I\007\002=)\021qDB\001\007yI|w\016\036 \n\003\025I!A\t\003\002\013\005\034Go\034:\n\005\021*\023!B!di>\024(B\001\022\005\023\t9\003FA\004SK\016,\027N^3\013\005\021*\003\"\002\026\026\001\bY\023aB2p]R,\007\020\036\t\003Y5j\021!J\005\003]\025\022A\"Q2u_J\034uN\034;fqRDQ\001M\013A\002e\t\021A\035\005\006e%!\taM\001\007GJ,\027\r^3\025\007e!T\007C\0031c\001\007\021\004C\003+c\001\0071\006C\0038\023\021\005\001(A\005xSRDG*\0312fYR\021\021(\020\013\003uq\"\"!G\036\t\013)2\0049A\026\t\013A2\004\031A\r\t\013y2\004\031A \002\0131\f'-\0327\021\005\001\033eBA\007B\023\t\021e\"\001\004Qe\026$WMZ\005\003\t\026\023aa\025;sS:<'B\001\"\017\r\021Q!\001A$\024\007\031c\021\004\003\005J\r\n\005\t\025!\003K\003\031\031x.\036:dKB\031Qb\023\007\n\0051s!AB(qi&|g\016\003\0051\r\n\005\t\025!\003\032\021!qdI!A!\002\023y\005cA\007L!A!F\022B\001B\003-1\006C\003\024\r\022\005!\013\006\003T-^CFC\001+V!\tAa\tC\003+#\002\0171\006C\003J#\002\007!\nC\0031#\002\007\021\004C\003?#\002\007q\nC\003\024\r\022\005!\fF\002\\;z#\"\001\026/\t\013)J\0069A\026\t\013%K\006\031\001&\t\013AJ\006\031A\r\t\013\0014E\021A1\002\027%\034H)\0324j]\026$\027\t\036\013\003E\026\004\"!D2\n\005\021t!a\002\"p_2,\027M\034\005\006M~\003\raZ\001\002_B\021Q\002[\005\003S:\0211!\0218z\021\0251b\t\"\001l)\taw\016\005\002\016[&\021aN\004\002\005+:LG\017C\003gU\002\007q\r")
/*    */ public class LoggingReceive implements PartialFunction<Object, BoxedUnit> {
/*    */   private final Option<Object> source;
/*    */   
/*    */   private final PartialFunction<Object, BoxedUnit> r;
/*    */   
/*    */   private final Option<String> label;
/*    */   
/*    */   public final ActorContext akka$event$LoggingReceive$$context;
/*    */   
/*    */   public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 50 */     return PartialFunction.class.orElse(this, that);
/*    */   }
/*    */   
/*    */   public <C> PartialFunction<Object, C> andThen(Function1 k) {
/* 50 */     return PartialFunction.class.andThen(this, k);
/*    */   }
/*    */   
/*    */   public Function1<Object, Option<BoxedUnit>> lift() {
/* 50 */     return PartialFunction.class.lift(this);
/*    */   }
/*    */   
/*    */   public <A1, B1> B1 applyOrElse(Object x, Function1 default) {
/* 50 */     return (B1)PartialFunction.class.applyOrElse(this, x, default);
/*    */   }
/*    */   
/*    */   public <U> Function1<Object, Object> runWith(Function1 action) {
/* 50 */     return PartialFunction.class.runWith(this, action);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZD$sp(double v1) {
/* 50 */     return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDD$sp(double v1) {
/* 50 */     return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFD$sp(double v1) {
/* 50 */     return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcID$sp(double v1) {
/* 50 */     return Function1.class.apply$mcID$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJD$sp(double v1) {
/* 50 */     return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVD$sp(double v1) {
/* 50 */     Function1.class.apply$mcVD$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZF$sp(float v1) {
/* 50 */     return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDF$sp(float v1) {
/* 50 */     return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFF$sp(float v1) {
/* 50 */     return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcIF$sp(float v1) {
/* 50 */     return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJF$sp(float v1) {
/* 50 */     return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVF$sp(float v1) {
/* 50 */     Function1.class.apply$mcVF$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZI$sp(int v1) {
/* 50 */     return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDI$sp(int v1) {
/* 50 */     return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFI$sp(int v1) {
/* 50 */     return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcII$sp(int v1) {
/* 50 */     return Function1.class.apply$mcII$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJI$sp(int v1) {
/* 50 */     return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVI$sp(int v1) {
/* 50 */     Function1.class.apply$mcVI$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public boolean apply$mcZJ$sp(long v1) {
/* 50 */     return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public double apply$mcDJ$sp(long v1) {
/* 50 */     return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public float apply$mcFJ$sp(long v1) {
/* 50 */     return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public int apply$mcIJ$sp(long v1) {
/* 50 */     return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public long apply$mcJJ$sp(long v1) {
/* 50 */     return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public void apply$mcVJ$sp(long v1) {
/* 50 */     Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose(Function1 g) {
/* 50 */     return Function1.class.compose((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcZD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcDD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcFD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcID$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcJD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcVD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcZF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcDF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcFF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcIF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcJF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcVF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcZI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcDI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcFI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcII$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcJI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcVI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 50 */     return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcID$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcII$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 50 */     return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 50 */     return Function1.class.toString((Function1)this);
/*    */   }
/*    */   
/*    */   public LoggingReceive(Option<Object> source, PartialFunction<Object, BoxedUnit> r, Option<String> label, ActorContext context) {
/* 50 */     Function1.class.$init$((Function1)this);
/* 50 */     PartialFunction.class.$init$(this);
/*    */   }
/*    */   
/*    */   public LoggingReceive(Option<Object> source, PartialFunction<Object, BoxedUnit> r, ActorContext context) {
/* 51 */     this(source, r, (Option<String>)None$.MODULE$, context);
/*    */   }
/*    */   
/*    */   public boolean isDefinedAt(Object o) {
/* 53 */     boolean handled = this.r.isDefinedAt(o);
/* 54 */     Tuple2<String, Class<?>> tuple2 = LogSource$.MODULE$.fromAnyRef(this.source.getOrElse((Function0)new $anonfun$1(this)));
/* 54 */     if (tuple2 != null) {
/* 54 */       String str2, str = (String)tuple2._1();
/* 54 */       Class clazz = (Class)tuple2._2();
/* 54 */       Tuple2 tuple22 = new Tuple2(str, clazz), tuple21 = tuple22;
/* 54 */       String str1 = (String)tuple21._1();
/* 54 */       Class<?> clazz1 = (Class)tuple21._2();
/* 56 */       Option<String> option = this.label;
/* 57 */       if (option instanceof Some) {
/* 57 */         Some some = (Some)option;
/* 57 */         String l = (String)some.x();
/* 57 */         str2 = (new StringBuilder()).append(" in state ").append(l).toString();
/*    */       } else {
/* 58 */         str2 = "";
/*    */       } 
/*    */       this.akka$event$LoggingReceive$$context.system().eventStream().publish(new Logging.Debug(str1, clazz1, (new StringBuilder()).append("received ").append(handled ? "handled" : "unhandled").append(" message ").append(o).append(str2).toString()));
/* 60 */       return handled;
/*    */     } 
/*    */     throw new MatchError(tuple2);
/*    */   }
/*    */   
/*    */   public class $anonfun$1 extends AbstractFunction0<Actor> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Actor apply() {
/*    */       return ((ActorCell)this.$outer.akka$event$LoggingReceive$$context).actor();
/*    */     }
/*    */     
/*    */     public $anonfun$1(LoggingReceive $outer) {}
/*    */   }
/*    */   
/*    */   public void apply(Object o) {
/* 62 */     this.r.apply(o);
/*    */   }
/*    */   
/*    */   public static PartialFunction<Object, BoxedUnit> withLabel(String paramString, PartialFunction<Object, BoxedUnit> paramPartialFunction, ActorContext paramActorContext) {
/*    */     return LoggingReceive$.MODULE$.withLabel(paramString, paramPartialFunction, paramActorContext);
/*    */   }
/*    */   
/*    */   public static PartialFunction<Object, BoxedUnit> create(PartialFunction<Object, BoxedUnit> paramPartialFunction, ActorContext paramActorContext) {
/*    */     return LoggingReceive$.MODULE$.create(paramPartialFunction, paramActorContext);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\LoggingReceive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */