/*     */ package akka.actor;
/*     */ 
/*     */ import akka.japi.Creator;
/*     */ import akka.routing.RouterConfig;
/*     */ import java.lang.reflect.ParameterizedType;
/*     */ import java.lang.reflect.Type;
/*     */ import java.lang.reflect.TypeVariable;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.StringContext;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Seq;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ 
/*     */ public final class Props$ implements Serializable {
/*     */   public static final Props$ MODULE$;
/*     */   
/*     */   private final Function0<Actor> defaultCreator;
/*     */   
/*     */   private final RouterConfig defaultRoutedProps;
/*     */   
/*     */   private final Deploy defaultDeploy;
/*     */   
/*     */   private final Props empty;
/*     */   
/*     */   private final Props default;
/*     */   
/*     */   private Object readResolve() {
/*  29 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Props$() {
/*  29 */     MODULE$ = this;
/*  34 */     this.defaultCreator = (Function0<Actor>)new Props.$anonfun$2();
/*  39 */     this.defaultRoutedProps = (RouterConfig)akka.routing.NoRouter$.MODULE$;
/*  44 */     this.defaultDeploy = new Deploy(Deploy$.MODULE$.apply$default$1(), Deploy$.MODULE$.apply$default$2(), Deploy$.MODULE$.apply$default$3(), Deploy$.MODULE$.apply$default$4(), Deploy$.MODULE$.apply$default$5(), Deploy$.MODULE$.apply$default$6());
/*  49 */     this.empty = apply(scala.reflect.ClassTag$.MODULE$.apply(Props.EmptyActor.class));
/*  54 */     (new Function0[1])[0] = defaultCreator();
/*  54 */     this.default = new Props(defaultDeploy(), CreatorFunctionConsumer.class, (Seq<Object>)scala.collection.immutable.List$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Function0[1])));
/*     */   }
/*     */   
/*     */   public final Function0<Actor> defaultCreator() {
/*     */     return this.defaultCreator;
/*     */   }
/*     */   
/*     */   public static class $anonfun$2 extends AbstractFunction0<scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final scala.runtime.Nothing$ apply() {
/*     */       throw new UnsupportedOperationException("No actor creator specified!");
/*     */     }
/*     */   }
/*     */   
/*     */   public final RouterConfig defaultRoutedProps() {
/*     */     return this.defaultRoutedProps;
/*     */   }
/*     */   
/*     */   public final Deploy defaultDeploy() {
/*     */     return this.defaultDeploy;
/*     */   }
/*     */   
/*     */   public final Props empty() {
/*     */     return this.empty;
/*     */   }
/*     */   
/*     */   public final Props default() {
/*  54 */     return this.default;
/*     */   }
/*     */   
/*     */   public <T extends Actor> Props apply(ClassTag evidence$1) {
/*  69 */     return new Props(defaultDeploy(), ((ClassTag)scala.Predef$.MODULE$.implicitly(evidence$1)).runtimeClass(), (Seq)scala.collection.immutable.List$.MODULE$.empty());
/*     */   }
/*     */   
/*     */   public <T extends Actor> Props apply(Function0<Actor> creator, ClassTag evidence$2) {
/*  85 */     return mkProps(((ClassTag)scala.Predef$.MODULE$.implicitly(evidence$2)).runtimeClass(), creator);
/*     */   }
/*     */   
/*     */   private Props mkProps(Class classOfActor, Function0 ctor) {
/*  88 */     return apply(TypedCreatorFunctionConsumer.class, (Seq<Object>)scala.Predef$.MODULE$.genericWrapArray(new Object[] { classOfActor, ctor }));
/*     */   }
/*     */   
/*     */   public Props apply(Class<?> clazz, Seq args) {
/*  93 */     return new Props(defaultDeploy(), clazz, (Seq<Object>)args.toList());
/*     */   }
/*     */   
/*     */   public Props create(Class<?> clazz, Object... args) {
/*  99 */     return create(clazz, (Seq<Object>)scala.Predef$.MODULE$.wrapRefArray(args));
/*     */   }
/*     */   
/*     */   public Props create(Class<?> clazz, Seq args) {
/*  99 */     return new Props(defaultDeploy(), clazz, (Seq<Object>)args.toList());
/*     */   }
/*     */   
/*     */   public <T extends Actor> Props create(Creator creator) {
/* 110 */     Class<?> cc = creator.getClass();
/* 111 */     if (cc.getEnclosingClass() != null && (cc.getModifiers() & 0x8) == 0)
/* 112 */       throw new IllegalArgumentException("cannot use non-static local Creator to create actors; make it static (e.g. local to a static method) or top-level"); 
/* 113 */     Class<Actor> ac = Actor.class;
/* 114 */     Class<Creator> coc = Creator.class;
/* 115 */     Type type = akka.util.Reflect$.MODULE$.findMarker(cc, coc);
/* 116 */     if (type instanceof ParameterizedType) {
/*     */       Class clazz2;
/* 116 */       ParameterizedType parameterizedType = (ParameterizedType)type;
/* 117 */       Type type1 = (Type)scala.Predef$.MODULE$.refArrayOps((Object[])parameterizedType.getActualTypeArguments()).head();
/* 118 */       if (type1 instanceof Class) {
/* 118 */         Class clazz = (Class)type1;
/* 119 */       } else if (type1 instanceof TypeVariable) {
/* 119 */         TypeVariable typeVariable = (TypeVariable)type1;
/* 120 */         clazz2 = (Class)scala.Predef$.MODULE$.refArrayOps((Object[])typeVariable.getBounds()).collectFirst((PartialFunction)new Props$$anonfun$1(ac)).getOrElse((Function0)new Props$$anonfun$3(ac));
/*     */       } else {
/* 121 */         (new String[2])[0] = "unsupported type found in Creator argument [";
/* 121 */         (new String[2])[1] = "]";
/* 121 */         throw new IllegalArgumentException((new StringContext(scala.Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(scala.Predef$.MODULE$.genericWrapArray(new Object[] { type1 })));
/*     */       } 
/*     */       Class clazz1 = clazz2;
/*     */       Class actorClass = clazz1;
/* 126 */       Class clazz3 = actorClass;
/* 126 */       Creator creator1 = creator;
/* 126 */       return new Props(defaultDeploy(), CreatorConsumer.class, (Seq)scala.collection.immutable.Nil$.MODULE$.$colon$colon(creator1).$colon$colon(clazz3));
/*     */     } 
/*     */     if (type instanceof Class) {
/*     */       Class clazz = (Class)type;
/*     */       Class<Creator> clazz1 = coc;
/*     */       if (clazz == null) {
/*     */         if (clazz1 != null)
/*     */           throw new MatchError(type); 
/*     */       } else {
/*     */         if (clazz.equals(clazz1)) {
/*     */           (new String[1])[0] = "erased Creator types are unsupported, use Props.create(actorClass, creator) instead";
/*     */           throw new IllegalArgumentException((new StringContext(scala.Predef$.MODULE$.wrapRefArray((Object[])new String[1]))).s(scala.collection.immutable.Nil$.MODULE$));
/*     */         } 
/*     */         throw new MatchError(type);
/*     */       } 
/*     */       (new String[1])[0] = "erased Creator types are unsupported, use Props.create(actorClass, creator) instead";
/*     */       throw new IllegalArgumentException((new StringContext(scala.Predef$.MODULE$.wrapRefArray((Object[])new String[1]))).s(scala.collection.immutable.Nil$.MODULE$));
/*     */     } 
/*     */     throw new MatchError(type);
/*     */   }
/*     */   
/*     */   public static class Props$$anonfun$3 extends AbstractFunction0<Class<Actor>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Class ac$1;
/*     */     
/*     */     public final Class<Actor> apply() {
/*     */       return this.ac$1;
/*     */     }
/*     */     
/*     */     public Props$$anonfun$3(Class ac$1) {}
/*     */   }
/*     */   
/*     */   public static class Props$$anonfun$1 extends AbstractPartialFunction<Type, Class<?>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Class ac$1;
/*     */     
/*     */     public final <A1 extends Type, B1> B1 applyOrElse(Type x1, Function1 default) {
/*     */       Type type = x1;
/*     */       if (type instanceof Class) {
/*     */         Class<?> clazz = (Class)type;
/*     */         if (this.ac$1.isAssignableFrom(clazz)) {
/*     */           Class clazz1 = this.ac$1;
/*     */           if (clazz == null) {
/*     */             if (clazz1 != null)
/*     */               return (B1)clazz; 
/*     */           } else if (!clazz.equals(clazz1)) {
/*     */             return (B1)clazz;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       return (B1)default.apply(x1);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Type x1) {
/*     */       Type type = x1;
/*     */       if (type instanceof Class) {
/*     */         Class<?> clazz = (Class)type;
/*     */         if (this.ac$1.isAssignableFrom(clazz)) {
/*     */           Class clazz1 = this.ac$1;
/*     */           if (clazz == null) {
/*     */             if (clazz1 != null)
/*     */               return true; 
/*     */           } else if (!clazz.equals(clazz1)) {
/*     */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       return false;
/*     */     }
/*     */     
/*     */     public Props$$anonfun$1(Class ac$1) {}
/*     */   }
/*     */   
/*     */   public <T extends Actor> Props create(Class actorClass, Creator creator) {
/* 133 */     Class clazz = actorClass;
/* 133 */     Creator creator1 = creator;
/* 133 */     return new Props(defaultDeploy(), CreatorConsumer.class, (Seq)scala.collection.immutable.Nil$.MODULE$.$colon$colon(creator1).$colon$colon(clazz));
/*     */   }
/*     */   
/*     */   public Props apply(Deploy deploy, Class<?> clazz, Seq<Object> args) {
/* 161 */     return new Props(deploy, clazz, args);
/*     */   }
/*     */   
/*     */   public Option<Tuple3<Deploy, Class<Object>, Seq<Object>>> unapply(Props x$0) {
/* 161 */     return (x$0 == null) ? (Option<Tuple3<Deploy, Class<Object>, Seq<Object>>>)scala.None$.MODULE$ : (Option<Tuple3<Deploy, Class<Object>, Seq<Object>>>)new Some(new Tuple3(x$0.deploy(), x$0.clazz(), x$0.args()));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Props$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */