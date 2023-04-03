/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005=t!B\001\003\021\0039\021!B!di>\024(BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0051BA\003BGR|'o\005\002\n\031A\021Q\002E\007\002\035)\tq\"A\003tG\006d\027-\003\002\022\035\t1\021I\\=SK\032DQaE\005\005\002Q\ta\001P5oSRtD#A\004\006\tYI\001a\006\002\b%\026\034W-\033<f!\021i\001DG\017\n\005eq!a\004)beRL\027\r\034$v]\016$\030n\0348\021\0055Y\022B\001\017\017\005\r\te.\037\t\003\033yI!a\b\b\003\tUs\027\016^\004\006C%A\tAI\001\016K6\004H/\037\"fQ\0064\030n\034:\021\005\r\"S\"A\005\007\013\025J\001\022\001\024\003\033\025l\007\017^=CK\"\fg/[8s'\r!Cb\n\t\003GUAQa\005\023\005\002%\"\022A\t\005\006W\021\"\t\001L\001\fSN$UMZ5oK\022\fE\017\006\002.aA\021QBL\005\003_9\021qAQ8pY\026\fg\016C\0032U\001\007!$A\001y\021\025\031D\005\"\0015\003\025\t\007\017\0357z)\t)\004\b\005\002\016m%\021qG\004\002\b\035>$\b.\0338h\021\025\t$\0071\001\033Q\r!#(\020\t\003\033mJ!\001\020\b\003!M+'/[1m-\026\0248/[8o+&#e$A\001)\007\001RT\bC\004A\023\t\007IQA!\002\0219|7+\0328eKJ,\022A\021\t\003\021\rK!\001\022\002\003\021\005\033Go\034:SK\032DaAR\005!\002\033\021\025!\0038p'\026tG-\032:!\r\035Q!\001%A\002\002!\033\"a\022\007\t\013);E\021A&\002\r\021Jg.\033;%)\005iR\001\002\fH\0015\003\"AT\013\017\005!\001\001b\002)H\005\004%\031!U\001\bG>tG/\032=u+\005\021\006C\001\005T\023\t!&A\001\007BGR|'oQ8oi\026DH\017\003\004W\017\002\006IAU\001\tG>tG/\032=uA!9\001l\022b\001\n\017\t\025\001B:fY\032DaAW$!\002\033\021\025!B:fY\032\004\003\"\002/H\t\013i\026AB:f]\022,'\017F\001C\021\025yvI\"\001a\003\035\021XmY3jm\026,\022!\024\005\007E\036#\t\002B2\002\033\005\024x.\0368e%\026\034W-\033<f)\riB-\032\005\006?\006\004\r!\024\005\006M\006\004\rAG\001\004[N<\007B\0025H\t#!1*\001\bbe>,h\016\032)sKN#\030M\035;\t\r)<E\021\003\003L\0039\t'o\\;oIB{7\017^*u_BDa\001\\$\005\022\021i\027\001E1s_VtG\r\025:f%\026\034H/\031:u)\rib\016 \005\006_.\004\r\001]\001\007e\026\f7o\0348\021\005ELhB\001:x\035\t\031h/D\001u\025\t)h!\001\004=e>|GOP\005\002\037%\021\001PD\001\ba\006\0347.Y4f\023\tQ8PA\005UQJ|w/\0312mK*\021\001P\004\005\006{.\004\rA`\001\b[\026\0348/Y4f!\riqPG\005\004\003\003q!AB(qi&|g\016\003\005\002\006\035#\t\002BA\004\003E\t'o\\;oIB{7\017\036*fgR\f'\017\036\013\004;\005%\001BB8\002\004\001\007\001\017C\004\002\016\035#\t!a\004\002%M,\b/\032:wSN|'o\025;sCR,w-_\013\003\003#\0012\001CA\n\023\r\t)B\001\002\023'V\004XM\035<jg>\0248\013\036:bi\026<\027\020\003\004\002\032\035#\taS\001\taJ,7\013^1si\"2\021qCA\017\003c\001R!DA\020\003GI1!!\t\017\005\031!\bN]8xgB!\021QEA\024\031\001!q!!\013\001\005\004\tYCA\001U#\r)\024Q\006\t\004\003_IhBA\007xG\t\t\031\004E\002r\003kI1!a\016|\005%)\005pY3qi&|g\016\003\004\002<\035#\taS\001\ta>\034Ho\025;pa\"2\021\021HA \003c\001R!DA\020\003\003\002B!!\n\002D\0219\021\021\006\001C\002\005-\002bBA$\017\022\005\021\021J\001\013aJ,'+Z:uCJ$H#B\017\002L\0055\003BB8\002F\001\007\001\017\003\004~\003\013\002\rA \025\007\003\013\n\t&!\r\021\0135\ty\"a\025\021\t\005\025\022Q\013\003\b\003S\001!\031AA\026\021\035\tIf\022C\001\0037\n1\002]8tiJ+7\017^1siR\031Q$!\030\t\r=\f9\0061\001qQ\031\t9&!\031\0022A)Q\"a\b\002dA!\021QEA3\t\035\tI\003\001b\001\003WAq!!\033H\t\003\tY'A\005v]\"\fg\016\0327fIR\031Q$!\034\t\ru\f9\0071\001\033\001")
/*     */ public interface Actor {
/*     */   void akka$actor$Actor$_setter_$context_$eq(ActorContext paramActorContext);
/*     */   
/*     */   void akka$actor$Actor$_setter_$self_$eq(ActorRef paramActorRef);
/*     */   
/*     */   ActorContext context();
/*     */   
/*     */   ActorRef self();
/*     */   
/*     */   ActorRef sender();
/*     */   
/*     */   PartialFunction<Object, BoxedUnit> receive();
/*     */   
/*     */   void aroundReceive(PartialFunction<Object, BoxedUnit> paramPartialFunction, Object paramObject);
/*     */   
/*     */   void aroundPreStart();
/*     */   
/*     */   void aroundPostStop();
/*     */   
/*     */   void aroundPreRestart(Throwable paramThrowable, Option<Object> paramOption);
/*     */   
/*     */   void aroundPostRestart(Throwable paramThrowable);
/*     */   
/*     */   SupervisorStrategy supervisorStrategy();
/*     */   
/*     */   void preStart() throws Exception;
/*     */   
/*     */   void postStop() throws Exception;
/*     */   
/*     */   void preRestart(Throwable paramThrowable, Option<Object> paramOption) throws Exception;
/*     */   
/*     */   void postRestart(Throwable paramThrowable) throws Exception;
/*     */   
/*     */   void unhandled(Object paramObject);
/*     */   
/*     */   public static class emptyBehavior$ implements PartialFunction<Object, BoxedUnit> {
/*     */     public static final emptyBehavior$ MODULE$;
/*     */     
/*     */     public static final long serialVersionUID = 1L;
/*     */     
/*     */     public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 336 */       return PartialFunction.class.orElse(this, that);
/*     */     }
/*     */     
/*     */     public <C> PartialFunction<Object, C> andThen(Function1 k) {
/* 336 */       return PartialFunction.class.andThen(this, k);
/*     */     }
/*     */     
/*     */     public Function1<Object, Option<BoxedUnit>> lift() {
/* 336 */       return PartialFunction.class.lift(this);
/*     */     }
/*     */     
/*     */     public <A1, B1> B1 applyOrElse(Object x, Function1 default) {
/* 336 */       return (B1)PartialFunction.class.applyOrElse(this, x, default);
/*     */     }
/*     */     
/*     */     public <U> Function1<Object, Object> runWith(Function1 action) {
/* 336 */       return PartialFunction.class.runWith(this, action);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZD$sp(double v1) {
/* 336 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/* 336 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/* 336 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/* 336 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/* 336 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/* 336 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/* 336 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/* 336 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/* 336 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/* 336 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/* 336 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/* 336 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/* 336 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/* 336 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/* 336 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/* 336 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/* 336 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/* 336 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/* 336 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/* 336 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/* 336 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/* 336 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/* 336 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/* 336 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose(Function1 g) {
/* 336 */       return Function1.class.compose((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 336 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 336 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 336 */       return Function1.class.toString((Function1)this);
/*     */     }
/*     */     
/*     */     public emptyBehavior$() {
/* 336 */       MODULE$ = this;
/* 336 */       Function1.class.$init$((Function1)this);
/* 336 */       PartialFunction.class.$init$(this);
/*     */     }
/*     */     
/*     */     public boolean isDefinedAt(Object x) {
/* 337 */       return false;
/*     */     }
/*     */     
/*     */     public scala.runtime.Nothing$ apply(Object x) {
/* 338 */       throw new UnsupportedOperationException("Empty behavior apply()");
/*     */     }
/*     */   }
/*     */   
/*     */   public class Actor$$anonfun$aroundReceive$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Object message) {
/* 465 */       this.$outer.unhandled(message);
/*     */     }
/*     */     
/*     */     public Actor$$anonfun$aroundReceive$1(Actor $outer) {}
/*     */   }
/*     */   
/*     */   public class Actor$$anonfun$preRestart$1 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public Actor$$anonfun$preRestart$1(Actor $outer) {}
/*     */     
/*     */     public final void apply(ActorRef child) {
/* 530 */       this.$outer.context().unwatch(child);
/* 531 */       this.$outer.context().stop(child);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Actor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */