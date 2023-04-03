/*     */ package akka.actor.dsl;
/*     */ 
/*     */ import akka.actor.ActorDSL$;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorRefFactory;
/*     */ import akka.actor.Props;
/*     */ import akka.actor.Props$;
/*     */ import akka.actor.TypedCreatorFunctionConsumer;
/*     */ import scala.Function0;
/*     */ import scala.Predef$;
/*     */ import scala.collection.Seq;
/*     */ import scala.reflect.ClassTag;
/*     */ 
/*     */ public abstract class Creators$class {
/*     */   public static void $init$(ActorDSL$ $this) {}
/*     */   
/*     */   private static Props mkProps(ActorDSL$ $this, Class classOfActor, Function0 ctor) {
/* 150 */     return Props$.MODULE$.apply(TypedCreatorFunctionConsumer.class, (Seq)Predef$.MODULE$.genericWrapArray(new Object[] { classOfActor, ctor }));
/*     */   }
/*     */   
/*     */   public static ActorRef actor(ActorDSL$ $this, Function0 ctor, ClassTag evidence$1, ActorRefFactory factory) {
/* 164 */     Class classOfActor = ((ClassTag)Predef$.MODULE$.implicitly(evidence$1)).runtimeClass();
/* 165 */     Props props = mkProps($this, classOfActor, ctor);
/* 166 */     return factory.actorOf(props);
/*     */   }
/*     */   
/*     */   public static ActorRef actor(ActorDSL$ $this, String name, Function0 ctor, ClassTag evidence$2, ActorRefFactory factory) {
/* 183 */     Class classOfActor = ((ClassTag)Predef$.MODULE$.implicitly(evidence$2)).runtimeClass();
/* 184 */     Props props = mkProps($this, classOfActor, ctor);
/* 186 */     return (name == null) ? factory.actorOf(props) : 
/* 187 */       factory.actorOf(props, name);
/*     */   }
/*     */   
/*     */   public static ActorRef actor(ActorDSL$ $this, ActorRefFactory factory, String name, Function0 ctor, ClassTag evidence$3) {
/* 203 */     return $this.actor(name, ctor, (ClassTag)Predef$.MODULE$.implicitly(evidence$3), factory);
/*     */   }
/*     */   
/*     */   public static ActorRef actor(ActorDSL$ $this, ActorRefFactory factory, Function0 ctor, ClassTag evidence$4) {
/* 217 */     null;
/* 217 */     return $this.actor(null, ctor, (ClassTag)Predef$.MODULE$.implicitly(evidence$4), factory);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dsl\Creators$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */