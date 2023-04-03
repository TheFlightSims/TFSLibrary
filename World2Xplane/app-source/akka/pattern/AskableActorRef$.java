/*     */ package akka.pattern;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.InternalActorRef;
/*     */ import akka.actor.ScalaActorRef;
/*     */ import akka.util.Timeout;
/*     */ import scala.StringContext;
/*     */ import scala.collection.Seq;
/*     */ import scala.concurrent.Future;
/*     */ 
/*     */ public final class AskableActorRef$ {
/*     */   public static final AskableActorRef$ MODULE$;
/*     */   
/*     */   public final int hashCode$extension(ActorRef $this) {
/* 127 */     return $this.hashCode();
/*     */   }
/*     */   
/*     */   public final boolean equals$extension(ActorRef $this, Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_2
/*     */     //   1: astore_3
/*     */     //   2: aload_3
/*     */     //   3: instanceof akka/pattern/AskableActorRef
/*     */     //   6: ifeq -> 15
/*     */     //   9: iconst_1
/*     */     //   10: istore #4
/*     */     //   12: goto -> 18
/*     */     //   15: iconst_0
/*     */     //   16: istore #4
/*     */     //   18: iload #4
/*     */     //   20: ifeq -> 80
/*     */     //   23: aload_2
/*     */     //   24: ifnonnull -> 33
/*     */     //   27: aconst_null
/*     */     //   28: pop
/*     */     //   29: aconst_null
/*     */     //   30: goto -> 40
/*     */     //   33: aload_2
/*     */     //   34: checkcast akka/pattern/AskableActorRef
/*     */     //   37: invokevirtual actorRef : ()Lakka/actor/ActorRef;
/*     */     //   40: astore #5
/*     */     //   42: aload_1
/*     */     //   43: aload #5
/*     */     //   45: astore #6
/*     */     //   47: dup
/*     */     //   48: ifnonnull -> 60
/*     */     //   51: pop
/*     */     //   52: aload #6
/*     */     //   54: ifnull -> 68
/*     */     //   57: goto -> 72
/*     */     //   60: aload #6
/*     */     //   62: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   65: ifeq -> 72
/*     */     //   68: iconst_1
/*     */     //   69: goto -> 73
/*     */     //   72: iconst_0
/*     */     //   73: ifeq -> 80
/*     */     //   76: iconst_1
/*     */     //   77: goto -> 81
/*     */     //   80: iconst_0
/*     */     //   81: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #127	-> 0
/*     */     //   #63	-> 9
/*     */     //   #127	-> 18
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	82	0	this	Lakka/pattern/AskableActorRef$;
/*     */     //   0	82	1	$this	Lakka/actor/ActorRef;
/*     */     //   0	82	2	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   private AskableActorRef$() {
/* 127 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final Future<Object> ask$extension(ActorRef $this, Object message, Timeout timeout) {
/*     */     Future<Object> future;
/* 130 */     boolean bool = false;
/* 130 */     null;
/* 130 */     InternalActorRef internalActorRef = null;
/* 130 */     ActorRef actorRef = $this;
/* 130 */     if (actorRef instanceof InternalActorRef) {
/* 130 */       bool = true;
/* 130 */       internalActorRef = (InternalActorRef)actorRef;
/* 130 */       if (internalActorRef.isTerminated()) {
/* 131 */         ScalaActorRef qual$1 = akka.actor.package$.MODULE$.actorRef2Scala($this);
/* 131 */         Object x$2 = message;
/* 131 */         ActorRef x$3 = qual$1.$bang$default$2(x$2);
/* 131 */         qual$1.$bang(x$2, x$3);
/* 132 */         (new String[2])[0] = "Recipient[";
/* 132 */         (new String[2])[1] = "] had already been terminated.";
/* 132 */         return scala.concurrent.Future$.MODULE$.failed(new AskTimeoutException((new StringContext((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { $this }))));
/*     */       } 
/*     */     } 
/*     */     if (bool) {
/* 135 */       (new String[2])[0] = "Timeout length must not be negative, question not sent to [";
/* 135 */       (new String[2])[1] = "]";
/* 137 */       PromiseActorRef a = PromiseActorRef$.MODULE$.apply(internalActorRef.provider(), timeout, $this.toString());
/* 137 */       $this
/* 138 */         .tell(message, (ActorRef)a);
/* 139 */       future = (timeout.duration().length() <= 0L) ? scala.concurrent.Future$.MODULE$.failed(new IllegalArgumentException((new StringContext((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { $this })))) : a.result().future();
/*     */     } else {
/* 141 */       (new String[2])[0] = "Unsupported recipient ActorRef type, question not sent to [";
/* 141 */       (new String[2])[1] = "]";
/* 141 */       future = scala.concurrent.Future$.MODULE$.failed(new IllegalArgumentException((new StringContext((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { $this }))));
/*     */     } 
/*     */     return future;
/*     */   }
/*     */   
/*     */   public final Future<Object> $qmark$extension(ActorRef $this, Object message, Timeout timeout) {
/* 144 */     return ask$extension($this, message, timeout);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\pattern\AskableActorRef$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */