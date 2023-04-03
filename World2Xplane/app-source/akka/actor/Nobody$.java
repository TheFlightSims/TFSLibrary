/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import java.io.ObjectStreamException;
/*     */ import scala.Product;
/*     */ import scala.collection.Iterator;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class Nobody$ extends InternalActorRef implements MinimalActorRef, Product {
/*     */   public static final Nobody$ MODULE$;
/*     */   
/*     */   private final RootActorPath path;
/*     */   
/*     */   public InternalActorRef getParent() {
/* 274 */     return MinimalActorRef$class.getParent(this);
/*     */   }
/*     */   
/*     */   public InternalActorRef getChild(Iterator names) {
/* 274 */     return MinimalActorRef$class.getChild(this, names);
/*     */   }
/*     */   
/*     */   public void start() {
/* 274 */     MinimalActorRef$class.start(this);
/*     */   }
/*     */   
/*     */   public void suspend() {
/* 274 */     MinimalActorRef$class.suspend(this);
/*     */   }
/*     */   
/*     */   public void resume(Throwable causedByFailure) {
/* 274 */     MinimalActorRef$class.resume(this, causedByFailure);
/*     */   }
/*     */   
/*     */   public void stop() {
/* 274 */     MinimalActorRef$class.stop(this);
/*     */   }
/*     */   
/*     */   public boolean isTerminated() {
/* 274 */     return MinimalActorRef$class.isTerminated(this);
/*     */   }
/*     */   
/*     */   public void $bang(Object message, ActorRef sender) {
/* 274 */     MinimalActorRef$class.$bang(this, message, sender);
/*     */   }
/*     */   
/*     */   public void sendSystemMessage(SystemMessage message) {
/* 274 */     MinimalActorRef$class.sendSystemMessage(this, message);
/*     */   }
/*     */   
/*     */   public void restart(Throwable cause) {
/* 274 */     MinimalActorRef$class.restart(this, cause);
/*     */   }
/*     */   
/*     */   public Object writeReplace() throws ObjectStreamException {
/* 274 */     return MinimalActorRef$class.writeReplace(this);
/*     */   }
/*     */   
/*     */   public ActorRef $bang$default$2(Object message) {
/* 274 */     return MinimalActorRef$class.$bang$default$2(this, message);
/*     */   }
/*     */   
/*     */   public final boolean isLocal() {
/* 274 */     return LocalRef$class.isLocal(this);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 274 */     return "Nobody";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 274 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 274 */     int i = x$1;
/* 274 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 274 */     return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 274 */     return x$1 instanceof Nobody$;
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 274 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private Nobody$() {
/* 274 */     MODULE$ = this;
/* 274 */     LocalRef$class.$init$(this);
/* 274 */     MinimalActorRef$class.$init$(this);
/* 274 */     Product.class.$init$(this);
/* 275 */     this.path = new RootActorPath(Address$.MODULE$.apply("akka", "all-systems"), "/Nobody");
/*     */   }
/*     */   
/*     */   public RootActorPath path() {
/* 275 */     return this.path;
/*     */   }
/*     */   
/*     */   public scala.runtime.Nothing$ provider() {
/* 276 */     throw new UnsupportedOperationException("Nobody does not provide");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Nobody$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */