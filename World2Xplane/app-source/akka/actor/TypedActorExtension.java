/*     */ package akka.actor;
/*     */ 
/*     */ import akka.util.Helpers$;
/*     */ import akka.util.Helpers$ConfigOps$;
/*     */ import akka.util.Timeout;
/*     */ import java.lang.reflect.InvocationHandler;
/*     */ import java.lang.reflect.Proxy;
/*     */ import java.util.concurrent.atomic.AtomicReference;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001a4A!\001\002\001\017\t\031B+\0379fI\006\033Go\034:FqR,gn]5p]*\0211\001B\001\006C\016$xN\035\006\002\013\005!\021m[6b\007\001\031B\001\001\005\017%A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003#QK\b/\0323BGR|'OR1di>\024\030\020\005\002\020'%\021AC\001\002\n\013b$XM\\:j_:D\001B\006\001\003\006\004%\taF\001\007gf\034H/Z7\026\003a\001\"aD\r\n\005i\021!aE#yi\026tG-\0323BGR|'oU=ti\026l\007\002\003\017\001\005\003\005\013\021\002\r\002\017ML8\017^3nA!)a\004\001C\001?\0051A(\0338jiz\"\"\001I\021\021\005=\001\001\"\002\f\036\001\004A\002\"B\022\001\t#!\023\001D1di>\024h)Y2u_JLX#A\023\021\005=1\023BA\024\003\005=\t5\r^8s%\0264g)Y2u_JL\b\"B\025\001\t#Q\023A\003;za\026$\027i\031;peV\t\001\005C\004-\001\t\007IQA\027\002)\021+g-Y;miJ+G/\036:o)&lWm\\;u+\005q\003CA\0303\033\005\001$BA\031\005\003\021)H/\0337\n\005M\002$a\002+j[\026|W\017\036\005\007k\001\001\013Q\002\030\002+\021+g-Y;miJ+G/\036:o)&lWm\\;uA!)q\007\001C\001q\005qq-\032;BGR|'OU3g\r>\024HCA\035=!\ty!(\003\002<\005\tA\021i\031;peJ+g\rC\003>m\001\007\001\"A\003qe>D\030\020C\003@\001\021\005\001)\001\007jgRK\b/\0323BGR|'\017\006\002B\tB\021\021BQ\005\003\007*\021qAQ8pY\026\fg\016C\003F}\001\007\001\"\001\006qe>D\030p\024:O_RDaa\022\001\005\002\021A\025aE2sK\006$X-Q2u_J\024VM\032)s_bLXcA%M1R!!JU.i!\tYE\n\004\001\005\01353%\031\001(\003\003I\013\"a\024\005\021\005%\001\026BA)\013\005\035qu\016\0365j]\036DQa\025$A\002Q\013Q\001\035:paN\0042aD+X\023\t1&A\001\006UsB,G\r\025:paN\004\"a\023-\005\013e3%\031\001.\003\003Q\013\"a\024&\t\013q3\005\031A/\002\021A\024x\016_=WCJ\0042A\0304K\033\005y&B\0011b\003\031\tGo\\7jG*\021!mY\001\013G>t7-\036:sK:$(BA\031e\025\005)\027\001\0026bm\006L!aZ0\003\037\005#x.\\5d%\0264WM]3oG\026Da!\033$\005\002\004Q\027\001C1di>\024(+\0324\021\007%Y\027(\003\002m\025\tAAHY=oC6,g\b\003\004o\001\021\005Aa\\\001\025S:4xnY1uS>t\007*\0318eY\026\024hi\034:\025\005A<\bCA9u\035\ty!/\003\002t\005\005QA+\0379fI\006\033Go\034:\n\005U4(a\007+za\026$\027i\031;pe&sgo\\2bi&|g\016S1oI2,'O\003\002t\005!)\021&\034a\001\021\001")
/*     */ public class TypedActorExtension implements TypedActorFactory, Extension {
/*     */   private final ExtendedActorSystem system;
/*     */   
/*     */   private final Timeout DefaultReturnTimeout;
/*     */   
/*     */   public boolean stop(Object proxy) {
/* 634 */     return TypedActorFactory$class.stop(this, proxy);
/*     */   }
/*     */   
/*     */   public boolean poisonPill(Object proxy) {
/* 634 */     return TypedActorFactory$class.poisonPill(this, proxy);
/*     */   }
/*     */   
/*     */   public <R, T extends R> R typedActorOf(TypedProps props) {
/* 634 */     return (R)TypedActorFactory$class.typedActorOf(this, props);
/*     */   }
/*     */   
/*     */   public <R, T extends R> R typedActorOf(TypedProps props, String name) {
/* 634 */     return (R)TypedActorFactory$class.typedActorOf(this, props, name);
/*     */   }
/*     */   
/*     */   public <R, T extends R> R typedActorOf(TypedProps props, ActorRef actorRef) {
/* 634 */     return (R)TypedActorFactory$class.typedActorOf(this, props, actorRef);
/*     */   }
/*     */   
/*     */   public ExtendedActorSystem system() {
/* 634 */     return this.system;
/*     */   }
/*     */   
/*     */   public TypedActorExtension(ExtendedActorSystem system) {
/* 634 */     TypedActorFactory$class.$init$(this);
/* 645 */     this.DefaultReturnTimeout = new Timeout(Helpers$ConfigOps$.MODULE$.getMillisDuration$extension(Helpers$.MODULE$.ConfigOps(system.settings().config()), "akka.actor.typed.timeout"));
/*     */   }
/*     */   
/*     */   public ActorRefFactory actorFactory() {
/*     */     return system();
/*     */   }
/*     */   
/*     */   public TypedActorExtension typedActor() {
/*     */     return this;
/*     */   }
/*     */   
/*     */   public final Timeout DefaultReturnTimeout() {
/* 645 */     return this.DefaultReturnTimeout;
/*     */   }
/*     */   
/*     */   public ActorRef getActorRefFor(Object proxy) {
/*     */     ActorRef actorRef;
/* 650 */     TypedActor.TypedActorInvocationHandler typedActorInvocationHandler = invocationHandlerFor(proxy);
/* 651 */     if (typedActorInvocationHandler == null) {
/* 651 */       null;
/* 651 */       actorRef = null;
/*     */     } else {
/* 652 */       actorRef = typedActorInvocationHandler.actor();
/*     */     } 
/*     */     return actorRef;
/*     */   }
/*     */   
/*     */   public boolean isTypedActor(Object proxyOrNot) {
/* 658 */     return (invocationHandlerFor(proxyOrNot) != null);
/*     */   }
/*     */   
/*     */   public <R, T extends R> R createActorRefProxy(TypedProps props, AtomicReference<Object> proxyVar, Function0 actorRef) {
/* 666 */     null;
/* 666 */     AtomicReference<ActorRef> actorVar = new AtomicReference(null);
/* 667 */     Object proxy = Proxy.newProxyInstance(
/* 668 */         (ClassLoader)props.loader().orElse((Function0)new TypedActorExtension$$anonfun$4(this, props)).orNull(Predef$.MODULE$.conforms()), 
/* 669 */         (Class[])props.interfaces().toArray(ClassTag$.MODULE$.apply(Class.class)), 
/* 670 */         new TypedActor.TypedActorInvocationHandler(this, actorVar, (Timeout)props.timeout().getOrElse((Function0)new TypedActorExtension$$anonfun$5(this))));
/* 673 */     actorVar.set(actorRef.apply());
/* 676 */     proxyVar.set(proxy);
/* 677 */     actorVar.set(actorRef.apply());
/* 678 */     return (proxyVar == null) ? (R)proxy : (R)proxyVar.get();
/*     */   }
/*     */   
/*     */   public class TypedActorExtension$$anonfun$4 extends AbstractFunction0<Option<ClassLoader>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final TypedProps props$1;
/*     */     
/*     */     public final Option<ClassLoader> apply() {
/*     */       return this.props$1.interfaces().collectFirst((PartialFunction)new TypedActorExtension$$anonfun$4$$anonfun$apply$1(this));
/*     */     }
/*     */     
/*     */     public TypedActorExtension$$anonfun$4(TypedActorExtension $outer, TypedProps props$1) {}
/*     */     
/*     */     public class TypedActorExtension$$anonfun$4$$anonfun$apply$1 extends AbstractPartialFunction<Class<?>, ClassLoader> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final <A1 extends Class<?>, B1> B1 applyOrElse(Class x2, Function1 default) {
/*     */         Class clazz = x2;
/*     */         return (B1)clazz.getClassLoader();
/*     */       }
/*     */       
/*     */       public final boolean isDefinedAt(Class x2) {
/*     */         Class clazz = x2;
/*     */         return true;
/*     */       }
/*     */       
/*     */       public TypedActorExtension$$anonfun$4$$anonfun$apply$1(TypedActorExtension$$anonfun$4 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class TypedActorExtension$$anonfun$5 extends AbstractFunction0<Timeout> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Timeout apply() {
/*     */       return this.$outer.DefaultReturnTimeout();
/*     */     }
/*     */     
/*     */     public TypedActorExtension$$anonfun$5(TypedActorExtension $outer) {}
/*     */   }
/*     */   
/*     */   public TypedActor.TypedActorInvocationHandler invocationHandlerFor(Object typedActor) {
/*     */     TypedActor.TypedActorInvocationHandler typedActorInvocationHandler;
/* 686 */     Object object = typedActor;
/* 687 */     if (object == null) {
/* 687 */       null;
/* 687 */       typedActorInvocationHandler = null;
/*     */     } else {
/*     */       TypedActor.TypedActorInvocationHandler typedActorInvocationHandler1;
/* 688 */       InvocationHandler invocationHandler = Proxy.getInvocationHandler(object);
/* 689 */       if (invocationHandler == null) {
/* 689 */         null;
/* 689 */         typedActorInvocationHandler1 = null;
/* 690 */       } else if (invocationHandler instanceof TypedActor.TypedActorInvocationHandler) {
/* 690 */         TypedActor.TypedActorInvocationHandler typedActorInvocationHandler2 = (TypedActor.TypedActorInvocationHandler)invocationHandler;
/*     */       } else {
/* 691 */         null;
/* 691 */         typedActorInvocationHandler1 = null;
/*     */       } 
/*     */       typedActorInvocationHandler = typedActorInvocationHandler1;
/*     */     } 
/* 694 */     null;
/* 694 */     return (typedActor != null && Proxy.class.isAssignableFrom(typedActor.getClass()) && Proxy.isProxyClass(typedActor.getClass())) ? typedActorInvocationHandler : null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\TypedActorExtension.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */