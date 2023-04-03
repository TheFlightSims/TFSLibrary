/*     */ package akka.dispatch;
/*     */ 
/*     */ import akka.ConfigurationException;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.DeadLetter;
/*     */ import akka.actor.ScalaActorRef;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.dispatch.sysmsg.SystemMessageList$;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.StringContext;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class Mailboxes$ {
/*     */   public static final Mailboxes$ MODULE$;
/*     */   
/*     */   private final String DefaultMailboxId;
/*     */   
/*     */   private final String NoMailboxRequirement;
/*     */   
/*     */   private Mailboxes$() {
/*  28 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final String DefaultMailboxId() {
/*  29 */     return "akka.actor.default-mailbox";
/*     */   }
/*     */   
/*     */   public final String NoMailboxRequirement() {
/*  30 */     return "";
/*     */   }
/*     */   
/*     */   public class $anon$1 extends Mailbox {
/*     */     public $anon$1(Mailboxes $outer) {
/*  41 */       super(new Mailboxes$$anon$1$$anon$2($outer));
/*  51 */       becomeClosed();
/*     */     }
/*     */     
/*     */     public class Mailboxes$$anon$1$$anon$2 implements MessageQueue {
/*     */       public Mailboxes$$anon$1$$anon$2(Mailboxes $outer) {}
/*     */       
/*     */       public void enqueue(ActorRef receiver, Envelope envelope) {
/*     */         Object object = envelope.message();
/*     */         if (object instanceof DeadLetter) {
/*     */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } else {
/*     */           this.$outer.akka$dispatch$Mailboxes$$deadLetters.tell(new DeadLetter(object, envelope.sender(), receiver), envelope.sender());
/*     */           BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */         } 
/*     */       }
/*     */       
/*     */       public scala.runtime.Null$ dequeue() {
/*     */         return null;
/*     */       }
/*     */       
/*     */       public boolean hasMessages() {
/*     */         return false;
/*     */       }
/*     */       
/*     */       public int numberOfMessages() {
/*     */         return 0;
/*     */       }
/*     */       
/*     */       public void cleanUp(ActorRef owner, MessageQueue deadLetters) {}
/*     */     }
/*     */     
/*     */     public void systemEnqueue(ActorRef receiver, SystemMessage handle) {
/*  53 */       ScalaActorRef qual$1 = akka.actor.package$.MODULE$.actorRef2Scala(this.$outer.akka$dispatch$Mailboxes$$deadLetters);
/*  53 */       DeadLetter x$1 = new DeadLetter(handle, receiver, receiver);
/*  53 */       ActorRef x$2 = qual$1.$bang$default$2(x$1);
/*  53 */       qual$1.$bang(x$1, x$2);
/*     */     }
/*     */     
/*     */     public SystemMessage systemDrain(SystemMessage newContents) {
/*  54 */       return SystemMessageList$.MODULE$.ENil();
/*     */     }
/*     */     
/*     */     public boolean hasSystemMessages() {
/*  55 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   public class $anonfun$2 extends AbstractFunction2<Map<Class<?>, String>, Tuple2<String, Object>, Map<Class<?>, String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Map<Class<?>, String> apply(Map x0$1, Tuple2 x1$1) {
/*  63 */       Tuple2 tuple2 = new Tuple2(x0$1, x1$1);
/*  63 */       if (tuple2 != null) {
/*  64 */         Map m = (Map)tuple2._1();
/*  64 */         Tuple2 tuple21 = (Tuple2)tuple2._2();
/*     */         if (tuple21 != null) {
/*  64 */           String k = (String)tuple21._1();
/*  64 */           Object v = tuple21._2();
/*  65 */           return (Map)this.$outer.akka$dispatch$Mailboxes$$dynamicAccess.getClassFor(k, scala.reflect.ClassTag$.MODULE$.Any()).map((Function1)new Mailboxes$$anonfun$2$$anonfun$apply$2(this, m, v))
/*     */             
/*  67 */             .recover((PartialFunction)new $anonfun$apply$1(this, k, v))
/*     */             
/*  71 */             .get();
/*     */         } 
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public $anonfun$2(Mailboxes $outer) {}
/*     */     
/*     */     public class Mailboxes$$anonfun$2$$anonfun$apply$2 extends AbstractFunction1<Class<?>, Map<Class<?>, String>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Map m$1;
/*     */       
/*     */       private final Object v$1;
/*     */       
/*     */       public final Map<Class<?>, String> apply(Class x0$2) {
/*     */         Class clazz = x0$2;
/*     */         return this.m$1.updated(clazz, this.v$1.toString());
/*     */       }
/*     */       
/*     */       public Mailboxes$$anonfun$2$$anonfun$apply$2(Mailboxes.$anonfun$2 $outer, Map m$1, Object v$1) {}
/*     */     }
/*     */     
/*     */     public class $anonfun$apply$1 extends AbstractPartialFunction<Throwable, scala.runtime.Nothing$> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final String k$1;
/*     */       
/*     */       private final Object v$1;
/*     */       
/*     */       public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x1, Function1 default) {
/*     */         Throwable throwable = x1;
/*     */         (new String[2])[0] = "Type [";
/*     */         (new String[2])[1] = "] specified as akka.actor.mailbox.requirement ";
/*     */         (new String[3])[0] = "[";
/*     */         (new String[3])[1] = "] in config can't be loaded due to [";
/*     */         (new String[3])[2] = "]";
/*     */         throw new ConfigurationException((new StringBuilder()).append((new StringContext(scala.Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s(scala.Predef$.MODULE$.genericWrapArray(new Object[] { this.k$1 }, ))).append((new StringContext(scala.Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s(scala.Predef$.MODULE$.genericWrapArray(new Object[] { this.v$1, throwable.getMessage() }, ))).toString(), throwable);
/*     */       }
/*     */       
/*     */       public final boolean isDefinedAt(Throwable x1) {
/*     */         Throwable throwable = x1;
/*     */         return true;
/*     */       }
/*     */       
/*     */       public $anonfun$apply$1(Mailboxes.$anonfun$2 $outer, String k$1, Object v$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Mailboxes$$anonfun$1 extends AbstractPartialFunction<Throwable, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String id$1;
/*     */     
/*     */     private final String x1$2;
/*     */     
/*     */     public final <A1 extends Throwable, B1> B1 applyOrElse(Throwable x2, Function1 default) {
/* 195 */       Throwable throwable = x2;
/* 198 */       (new String[3])[0] = "Cannot instantiate MailboxType [";
/* 198 */       (new String[3])[1] = "], defined in [";
/* 198 */       (new String[3])[2] = "], make sure it has a public";
/* 198 */       throw new IllegalArgumentException((new StringBuilder()).append((new StringContext(scala.Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s(scala.Predef$.MODULE$.genericWrapArray(new Object[] { this.x1$2, this.id$1 }, ))).append(" constructor with [akka.actor.ActorSystem.Settings, com.typesafe.config.Config] parameters").toString(), 
/* 200 */           throwable);
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Throwable x2) {
/*     */       Throwable throwable = x2;
/*     */       return true;
/*     */     }
/*     */     
/*     */     public Mailboxes$$anonfun$1(Mailboxes $outer, String id$1, String x1$2) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\Mailboxes$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */