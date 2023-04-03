/*     */ package akka.actor;
/*     */ 
/*     */ import akka.japi.Creator;
/*     */ import akka.util.Timeout;
/*     */ import scala.Function0;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple6;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ 
/*     */ public final class TypedProps$ implements Serializable {
/*     */   public static final TypedProps$ MODULE$;
/*     */   
/*     */   private final String defaultDispatcherId;
/*     */   
/*     */   private final Option<Timeout> defaultTimeout;
/*     */   
/*     */   private final Option<ClassLoader> defaultLoader;
/*     */   
/*     */   private Object readResolve() {
/* 460 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private TypedProps$() {
/* 460 */     MODULE$ = this;
/* 462 */     this.defaultDispatcherId = "akka.actor.default-dispatcher";
/* 463 */     this.defaultTimeout = (Option<Timeout>)scala.None$.MODULE$;
/* 464 */     this.defaultLoader = (Option<ClassLoader>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public String defaultDispatcherId() {
/*     */     return this.defaultDispatcherId;
/*     */   }
/*     */   
/*     */   public Option<Timeout> defaultTimeout() {
/*     */     return this.defaultTimeout;
/*     */   }
/*     */   
/*     */   public Option<ClassLoader> defaultLoader() {
/* 464 */     return this.defaultLoader;
/*     */   }
/*     */   
/*     */   public Seq<Class<?>> extractInterfaces(Class clazz) {
/* 471 */     return clazz.isInterface() ? akka.japi.Util$.MODULE$.immutableSingletonSeq(clazz) : akka.japi.Util$.MODULE$.immutableSeq(clazz.getInterfaces());
/*     */   }
/*     */   
/*     */   public <T> TypedProps<T> apply(Class<T> implementation) {
/* 480 */     return new TypedProps<T>(implementation);
/*     */   }
/*     */   
/*     */   public <T> TypedProps<T> apply(Class<?> interface, Class implementation) {
/* 491 */     return new TypedProps<T>(extractInterfaces(interface), akka.util.Reflect$.MODULE$.instantiator(implementation), $lessinit$greater$default$3(), $lessinit$greater$default$4(), $lessinit$greater$default$5(), $lessinit$greater$default$6());
/*     */   }
/*     */   
/*     */   public <T> TypedProps<T> apply(Class<?> interface, Function0<T> creator) {
/* 502 */     return new TypedProps<T>(extractInterfaces(interface), creator, $lessinit$greater$default$3(), $lessinit$greater$default$4(), $lessinit$greater$default$5(), $lessinit$greater$default$6());
/*     */   }
/*     */   
/*     */   public <T> TypedProps<T> apply(ClassTag evidence$1) {
/* 511 */     return new TypedProps<T>(((ClassTag)scala.Predef$.MODULE$.implicitly(evidence$1)).runtimeClass());
/*     */   }
/*     */   
/*     */   public <T> TypedProps<T> apply(Seq<Class<?>> interfaces, Function0<T> creator) {
/* 517 */     return new TypedProps<T>(interfaces, creator, $lessinit$greater$default$3(), $lessinit$greater$default$4(), $lessinit$greater$default$5(), $lessinit$greater$default$6());
/*     */   }
/*     */   
/*     */   public <T> TypedProps<T> apply(Seq<Class<?>> interfaces, Function0<T> creator, String dispatcher, Deploy deploy, Option<Timeout> timeout, Option<ClassLoader> loader) {
/* 525 */     return new TypedProps<T>(interfaces, creator, dispatcher, deploy, timeout, loader);
/*     */   }
/*     */   
/*     */   public <T> Option<Tuple6<Seq<Class<?>>, Function0<T>, String, Deploy, Option<Timeout>, Option<ClassLoader>>> unapply(TypedProps x$0) {
/* 525 */     return (x$0 == null) ? (Option<Tuple6<Seq<Class<?>>, Function0<T>, String, Deploy, Option<Timeout>, Option<ClassLoader>>>)scala.None$.MODULE$ : (Option<Tuple6<Seq<Class<?>>, Function0<T>, String, Deploy, Option<Timeout>, Option<ClassLoader>>>)new Some(new Tuple6(x$0.interfaces(), x$0.creator(), x$0.dispatcher(), x$0.deploy(), x$0.timeout(), x$0.loader()));
/*     */   }
/*     */   
/*     */   public <T> String apply$default$3() {
/* 528 */     return defaultDispatcherId();
/*     */   }
/*     */   
/*     */   public <T> String $lessinit$greater$default$3() {
/* 528 */     return defaultDispatcherId();
/*     */   }
/*     */   
/*     */   public <T> Deploy apply$default$4() {
/* 529 */     return Props$.MODULE$.defaultDeploy();
/*     */   }
/*     */   
/*     */   public <T> Deploy $lessinit$greater$default$4() {
/* 529 */     return Props$.MODULE$.defaultDeploy();
/*     */   }
/*     */   
/*     */   public <T> Option<Timeout> apply$default$5() {
/* 530 */     return defaultTimeout();
/*     */   }
/*     */   
/*     */   public <T> Option<Timeout> $lessinit$greater$default$5() {
/* 530 */     return defaultTimeout();
/*     */   }
/*     */   
/*     */   public <T> Option<ClassLoader> apply$default$6() {
/* 531 */     return defaultLoader();
/*     */   }
/*     */   
/*     */   public <T> Option<ClassLoader> $lessinit$greater$default$6() {
/* 531 */     return defaultLoader();
/*     */   }
/*     */   
/*     */   public class TypedProps$$anonfun$$init$$1 extends AbstractFunction0<T> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Creator implementation$1;
/*     */     
/*     */     public final T apply() {
/* 551 */       return (T)this.implementation$1.create();
/*     */     }
/*     */     
/*     */     public TypedProps$$anonfun$$init$$1(Creator implementation$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\TypedProps$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */