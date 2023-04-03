/*     */ package akka.event;
/*     */ 
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import scala.Option;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ 
/*     */ public final class LogSource$ {
/*     */   public static final LogSource$ MODULE$;
/*     */   
/*     */   private final LogSource<String> fromString;
/*     */   
/*     */   private final LogSource<Actor> fromActor;
/*     */   
/*     */   private final LogSource<ActorRef> fromActorRef;
/*     */   
/*     */   private final LogSource<Class<?>> fromClass;
/*     */   
/*     */   private LogSource$() {
/* 265 */     MODULE$ = this;
/* 266 */     this.fromString = new LogSource.$anon$4();
/* 272 */     this.fromActor = new LogSource.$anon$5();
/* 277 */     this.fromActorRef = new LogSource.$anon$6();
/* 288 */     this.fromClass = new LogSource.$anon$7();
/*     */   }
/*     */   
/*     */   public LogSource<String> fromString() {
/*     */     return this.fromString;
/*     */   }
/*     */   
/*     */   public static class $anon$4 implements LogSource<String> {
/*     */     public $anon$4() {
/*     */       LogSource$class.$init$(this);
/*     */     }
/*     */     
/*     */     public String genString(String s) {
/*     */       return s;
/*     */     }
/*     */     
/*     */     public String genString(String s, ActorSystem system) {
/*     */       return (new StringBuilder()).append(s).append("(").append(system).append(")").toString();
/*     */     }
/*     */     
/*     */     public Class<DummyClassForStringSources> getClazz(String s) {
/*     */       return DummyClassForStringSources.class;
/*     */     }
/*     */   }
/*     */   
/*     */   public LogSource<Actor> fromActor() {
/*     */     return this.fromActor;
/*     */   }
/*     */   
/*     */   public static class $anon$5 implements LogSource<Actor> {
/*     */     public Class<?> getClazz(Object t) {
/*     */       return LogSource$class.getClazz(this, t);
/*     */     }
/*     */     
/*     */     public $anon$5() {
/*     */       LogSource$class.$init$(this);
/*     */     }
/*     */     
/*     */     public String genString(Actor a) {
/*     */       return LogSource$.MODULE$.fromActorRef().genString(a.self());
/*     */     }
/*     */     
/*     */     public String genString(Actor a, ActorSystem system) {
/*     */       return LogSource$.MODULE$.fromActorRef().genString(a.self(), system);
/*     */     }
/*     */   }
/*     */   
/*     */   public LogSource<ActorRef> fromActorRef() {
/*     */     return this.fromActorRef;
/*     */   }
/*     */   
/*     */   public static class $anon$6 implements LogSource<ActorRef> {
/*     */     public Class<?> getClazz(Object t) {
/*     */       return LogSource$class.getClazz(this, t);
/*     */     }
/*     */     
/*     */     public $anon$6() {
/*     */       LogSource$class.$init$(this);
/*     */     }
/*     */     
/*     */     public String genString(ActorRef a) {
/*     */       return a.path().toString();
/*     */     }
/*     */     
/*     */     public String genString(ActorRef a, ActorSystem system) {
/*     */       try {
/*     */       
/*     */       } finally {
/*     */         String str;
/*     */         Exception exception1 = null, exception2 = exception1;
/*     */         Option option = scala.util.control.NonFatal$.MODULE$.unapply(exception2);
/*     */         if (option.isEmpty())
/*     */           throw exception1; 
/*     */       } 
/*     */       return str;
/*     */     }
/*     */   }
/*     */   
/*     */   public LogSource<Class<?>> fromClass() {
/* 288 */     return this.fromClass;
/*     */   }
/*     */   
/*     */   public static class $anon$7 implements LogSource<Class<?>> {
/*     */     public $anon$7() {
/* 288 */       LogSource$class.$init$(this);
/*     */     }
/*     */     
/*     */     public String genString(Class<?> c) {
/* 289 */       return Logging$.MODULE$.simpleName(c);
/*     */     }
/*     */     
/*     */     public String genString(Class<?> c, ActorSystem system) {
/* 290 */       return (new StringBuilder()).append(genString(c)).append("(").append(system).append(")").toString();
/*     */     }
/*     */     
/*     */     public Class<?> getClazz(Class<?> c) {
/* 291 */       return c;
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> LogSource<Class<T>> fromAnyClass() {
/* 293 */     return (LogSource)fromClass();
/*     */   }
/*     */   
/*     */   public <T> Tuple2<String, Class<?>> apply(Object o, LogSource evidence$1) {
/* 300 */     LogSource<Object> ls = (LogSource)scala.Predef$.MODULE$.implicitly(evidence$1);
/* 301 */     return new Tuple2(ls.genString(o), ls.getClazz(o));
/*     */   }
/*     */   
/*     */   public <T> Tuple2<String, Class<?>> apply(Object o, ActorSystem system, LogSource evidence$2) {
/* 310 */     LogSource<Object> ls = (LogSource)scala.Predef$.MODULE$.implicitly(evidence$2);
/* 311 */     return new Tuple2(ls.genString(o, system), ls.getClazz(o));
/*     */   }
/*     */   
/*     */   public Tuple2<String, Class<?>> fromAnyRef(Object o) {
/*     */     Tuple2<String, Class<?>> tuple2;
/* 319 */     Object object = o;
/* 320 */     if (object instanceof Class) {
/* 320 */       Class<?> clazz = (Class)object;
/* 320 */       tuple2 = apply(clazz, fromAnyClass());
/* 321 */     } else if (object instanceof Actor) {
/* 321 */       Actor actor = (Actor)object;
/* 321 */       tuple2 = apply(actor, fromActor());
/* 322 */     } else if (object instanceof ActorRef) {
/* 322 */       ActorRef actorRef = (ActorRef)object;
/* 322 */       tuple2 = apply(actorRef, fromActorRef());
/* 323 */     } else if (object instanceof String) {
/* 323 */       String str = (String)object;
/* 323 */       tuple2 = apply(str, fromString());
/*     */     } else {
/* 324 */       tuple2 = new Tuple2(Logging$.MODULE$.simpleName(object), object.getClass());
/*     */     } 
/*     */     return tuple2;
/*     */   }
/*     */   
/*     */   public Tuple2<String, Class<?>> fromAnyRef(Object o, ActorSystem system) {
/*     */     Tuple2<String, Class<?>> tuple2;
/* 333 */     Object object = o;
/* 334 */     if (object instanceof Class) {
/* 334 */       Class<?> clazz = (Class)object;
/* 334 */       tuple2 = apply(clazz, fromAnyClass());
/* 335 */     } else if (object instanceof Actor) {
/* 335 */       Actor actor = (Actor)object;
/* 335 */       tuple2 = apply(actor, fromActor());
/* 336 */     } else if (object instanceof ActorRef) {
/* 336 */       ActorRef actorRef = (ActorRef)object;
/* 336 */       tuple2 = apply(actorRef, fromActorRef());
/* 337 */     } else if (object instanceof String) {
/* 337 */       String str = (String)object;
/* 337 */       tuple2 = apply(str, fromString());
/*     */     } else {
/* 338 */       tuple2 = new Tuple2((new StringBuilder()).append(Logging$.MODULE$.simpleName(object)).append("(").append(system).append(")").toString(), object.getClass());
/*     */     } 
/*     */     return tuple2;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\event\LogSource$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */