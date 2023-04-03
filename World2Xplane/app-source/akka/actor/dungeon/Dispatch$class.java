/*     */ package akka.actor.dungeon;
/*     */ 
/*     */ import akka.actor.ActorCell;
/*     */ import akka.actor.ActorInitializationException$;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.Cell;
/*     */ import akka.actor.DeadLetter;
/*     */ import akka.dispatch.Envelope;
/*     */ import akka.dispatch.Mailbox;
/*     */ import akka.dispatch.MailboxType;
/*     */ import akka.dispatch.sysmsg.Create;
/*     */ import akka.dispatch.sysmsg.Recreate;
/*     */ import akka.dispatch.sysmsg.Resume;
/*     */ import akka.dispatch.sysmsg.Supervise;
/*     */ import akka.dispatch.sysmsg.Suspend;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.dispatch.sysmsg.Terminate;
/*     */ import akka.serialization.Serialization;
/*     */ import akka.serialization.SerializationExtension$;
/*     */ import akka.util.Unsafe;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.StringContext;
/*     */ import scala.collection.Seq;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public abstract class Dispatch$class {
/*     */   public static void $init$(ActorCell $this) {}
/*     */   
/*     */   public static final Mailbox mailbox(ActorCell $this) {
/*  23 */     return (Mailbox)Unsafe.instance.getObjectVolatile($this, AbstractActorCell.mailboxOffset);
/*     */   }
/*     */   
/*     */   public static final Mailbox swapMailbox(ActorCell $this, Mailbox newMailbox) {
/*     */     while (true) {
/*  26 */       Mailbox oldMailbox = $this.mailbox();
/*  27 */       if (Unsafe.instance.compareAndSwapObject($this, AbstractActorCell.mailboxOffset, oldMailbox, newMailbox))
/*  28 */         return oldMailbox; 
/*     */       newMailbox = newMailbox;
/*     */       $this = $this;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final boolean hasMessages(ActorCell $this) {
/*  31 */     return $this.mailbox().hasMessages();
/*     */   }
/*     */   
/*     */   public static final int numberOfMessages(ActorCell $this) {
/*  33 */     return $this.mailbox().numberOfMessages();
/*     */   }
/*     */   
/*     */   public static final boolean isTerminated(ActorCell $this) {
/*  35 */     return $this.mailbox().isClosed();
/*     */   }
/*     */   
/*     */   public static final ActorCell init(ActorCell $this, boolean sendSupervise, MailboxType mailboxType) {
/*     */     Create create1;
/*  47 */     Mailbox mbox = $this.dispatcher().createMailbox((Cell)$this, mailboxType);
/*  56 */     Class actorClass = $this.props().actorClass();
/*  57 */     MailboxType mailboxType1 = mailboxType;
/*  58 */     if (mailboxType1 instanceof akka.dispatch.ProducesMessageQueue && $this.system().mailboxes().hasRequiredType(actorClass)) {
/*  59 */       Class req = $this.system().mailboxes().getRequiredType(actorClass);
/*  62 */       String gotType = (mbox.messageQueue() == null) ? "null" : mbox.messageQueue().getClass().getName();
/*  64 */       (new String[4])[0] = "Actor [";
/*  64 */       (new String[4])[1] = "] requires mailbox type [";
/*  64 */       (new String[4])[2] = "] got [";
/*  64 */       (new String[4])[3] = "]";
/*  64 */       create1 = req.isInstance(mbox.messageQueue()) ? new Create((Option)None$.MODULE$) : new Create((Option)new Some(ActorInitializationException$.MODULE$.apply((ActorRef)$this.self(), (new StringContext((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[4]))).s((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { $this.self(), req, gotType }, )), ActorInitializationException$.MODULE$.apply$default$3())));
/*     */     } else {
/*  66 */       create1 = new Create((Option)None$.MODULE$);
/*     */     } 
/*     */     Create createMessage = create1;
/*  69 */     $this.swapMailbox(mbox);
/*  70 */     $this.mailbox().setActor($this);
/*  73 */     $this.mailbox().systemEnqueue((ActorRef)$this.self(), (SystemMessage)createMessage);
/*  75 */     if (sendSupervise)
/*  77 */       $this.parent().sendSystemMessage((SystemMessage)new Supervise((ActorRef)$this.self(), false)); 
/*  79 */     return $this;
/*     */   }
/*     */   
/*     */   public static ActorCell start(ActorCell $this) {
/*  87 */     $this.dispatcher().attach($this);
/*  88 */     return $this;
/*     */   }
/*     */   
/*     */   private static PartialFunction handleException(ActorCell $this) {
/*  91 */     return (PartialFunction)new Dispatch$$anonfun$handleException$1($this);
/*     */   }
/*     */   
/*     */   public static final void suspend(ActorCell $this) {
/*     */     try {
/* 100 */       $this.dispatcher().systemDispatch($this, (SystemMessage)new Suspend());
/*     */     } finally {
/* 100 */       Exception exception = null;
/* 100 */       PartialFunction catchExpr1 = handleException($this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final void resume(ActorCell $this, Throwable causedByFailure) {
/*     */     try {
/* 103 */       $this.dispatcher().systemDispatch($this, (SystemMessage)new Resume(causedByFailure));
/*     */     } finally {
/* 103 */       Exception exception = null;
/* 103 */       PartialFunction catchExpr2 = handleException($this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final void restart(ActorCell $this, Throwable cause) {
/*     */     try {
/* 106 */       $this.dispatcher().systemDispatch($this, (SystemMessage)new Recreate(cause));
/*     */     } finally {
/* 106 */       Exception exception = null;
/* 106 */       PartialFunction catchExpr3 = handleException($this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final void stop(ActorCell $this) {
/*     */     try {
/* 109 */       $this.dispatcher().systemDispatch($this, (SystemMessage)new Terminate());
/*     */     } finally {
/* 109 */       Exception exception = null;
/* 109 */       PartialFunction catchExpr4 = handleException($this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sendMessage(ActorCell $this, Envelope msg) {
/*     */     try {
/* 114 */       Object object2, object1 = msg.message();
/* 115 */       if (object1 instanceof DeadLetter) {
/* 115 */         DeadLetter deadLetter = (DeadLetter)object1;
/* 115 */         Object wrapped = deadLetter.message();
/*     */       } else {
/* 116 */         object2 = object1;
/*     */       } 
/*     */       Object unwrapped = object2;
/* 119 */       Serialization s = (Serialization)SerializationExtension$.MODULE$.apply((ActorSystem)$this.system());
/* 120 */       $this.system().settings().SerializeAllMessages() ? ((unwrapped instanceof akka.actor.NoSerializationVerificationNeeded) ? BoxedUnit.UNIT : s.deserialize((byte[])s.serialize(unwrapped).get(), unwrapped.getClass()).get()) : BoxedUnit.UNIT;
/* 123 */       $this.dispatcher().dispatch($this, msg);
/*     */     } finally {
/*     */       Exception exception = null;
/* 124 */       PartialFunction catchExpr5 = handleException($this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void sendSystemMessage(ActorCell $this, SystemMessage message) {
/*     */     try {
/* 126 */       $this.dispatcher().systemDispatch($this, message);
/*     */     } finally {
/* 126 */       Exception exception = null;
/* 126 */       PartialFunction catchExpr6 = handleException($this);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dungeon\Dispatch$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */