/*     */ package akka.actor;
/*     */ 
/*     */ import akka.actor.dungeon.ChildrenContainer;
/*     */ import akka.actor.dungeon.ChildrenContainer$EmptyChildrenContainer$;
/*     */ import akka.dispatch.Envelope;
/*     */ import akka.dispatch.sysmsg.Recreate;
/*     */ import akka.dispatch.sysmsg.Resume;
/*     */ import akka.dispatch.sysmsg.Suspend;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import akka.dispatch.sysmsg.Terminate;
/*     */ import akka.event.Logging;
/*     */ import java.util.LinkedList;
/*     */ import java.util.ListIterator;
/*     */ import java.util.concurrent.locks.ReentrantLock;
/*     */ import scala.Function0;
/*     */ import scala.MatchError;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.util.Try$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005Mf!B\001\003\001\0211!!D+ogR\f'\017^3e\007\026dGN\003\002\004\t\005)\021m\031;pe*\tQ!\001\003bW.\f7c\001\001\b\033A\021\001bC\007\002\023)\t!\"A\003tG\006d\027-\003\002\r\023\t1\021I\\=SK\032\004\"AD\b\016\003\tI!\001\005\002\003\t\r+G\016\034\005\t%\001\021)\031!C\001)\005Q1/_:uK6LU\016\0357\004\001U\tQ\003\005\002\017-%\021qC\001\002\020\003\016$xN]*zgR,W.S7qY\"A\021\004\001B\001B\003%Q#A\006tsN$X-\\%na2\004\003\002C\016\001\005\013\007I\021\001\017\002\tM,GNZ\013\002;A\021aBH\005\003?\t\0211CU3q_&tG/\0312mK\006\033Go\034:SK\032D\001\"\t\001\003\002\003\006I!H\001\006g\026dg\r\t\005\tG\001\021)\031!C\001I\005)\001O]8qgV\tQ\005\005\002\017M%\021qE\001\002\006!J|\007o\035\005\tS\001\021\t\021)A\005K\0051\001O]8qg\002B\001b\013\001\003\006\004%\t\001L\001\013gV\004XM\035<jg>\024X#A\027\021\0059q\023BA\030\003\005AIe\016^3s]\006d\027i\031;peJ+g\r\003\0052\001\t\005\t\025!\003.\003-\031X\017]3sm&\034xN\035\021\t\013M\002A\021\001\033\002\rqJg.\033;?)\025)dg\016\035:!\tq\001\001C\003\023e\001\007Q\003C\003\034e\001\007Q\004C\003$e\001\007Q\005C\003,e\001\007Q\006\003\004<\001\001\006i\001P\001\005Y>\0347\016\005\002>\r6\taH\003\002@\001\006)An\\2lg*\021\021IQ\001\013G>t7-\036:sK:$(BA\"E\003\021)H/\0337\013\003\025\013AA[1wC&\021qI\020\002\016%\026,g\016\036:b]RdunY6\t\r%\003\001\025!\004K\003\025\tX/Z;f!\rYEJT\007\002\005&\021QJ\021\002\013\031&t7.\0323MSN$\bC\001\005P\023\t\001\026BA\002B]fDQA\025\001\005\002M\0131B]3qY\006\034WmV5uQR\021Ak\026\t\003\021UK!AV\005\003\tUs\027\016\036\005\0061F\003\r!D\001\005G\026dG\016C\003[\001\021\0051,\001\004tsN$X-\\\013\0029B\021a\"X\005\003=\n\0211\"Q2u_J\034\026p\035;f[\")\001\r\001C\001C\006)1\017^1siR\t!-D\001\001\021\025!\007\001\"\001f\003\035\031Xo\0359f]\022$\022\001\026\005\006O\002!\t\001[\001\007e\026\034X/\\3\025\005QK\007\"\0026g\001\004Y\027aD2bkN,GMQ=GC&dWO]3\021\0051$hBA7s\035\tq\027/D\001p\025\t\0018#\001\004=e>|GOP\005\002\025%\0211/C\001\ba\006\0347.Y4f\023\t)hOA\005UQJ|w/\0312mK*\0211/\003\005\006q\002!\t!_\001\be\026\034H/\031:u)\t!&\020C\003|o\002\0071.A\003dCV\034X\rC\003~\001\021\005Q-\001\003ti>\004\bBB@\001\t\003\t\t!\001\007jgR+'/\\5oCR,G-\006\002\002\004A\031\001\"!\002\n\007\005\035\021BA\004C_>dW-\0318\t\r\005-\001\001\"\001-\003\031\001\030M]3oi\"9\021q\002\001\005\002\005E\021\001D2iS2$'/\0328SK\032\034XCAA\n!\021\t)\"a\007\016\005\005]!bAA\r\005\0059A-\0368hK>t\027\002BA\017\003/\021\021c\0215jY\022\024XM\\\"p]R\f\027N\\3s\021\035\t\t\003\001C\001\003G\tabZ3u\007\"LG\016\032\"z\035\006lW\r\006\003\002&\005E\002#\002\005\002(\005-\022bAA\025\023\t1q\n\035;j_:\0042ADA\027\023\r\tyC\001\002\022\007\"LG\016\032*fgR\f'\017^*uCR\034\b\002CA\032\003?\001\r!!\016\002\t9\fW.\032\t\005\003o\tiDD\002\t\003sI1!a\017\n\003\031\001&/\0323fM&!\021qHA!\005\031\031FO]5oO*\031\0211H\005\t\017\005\025\003\001\"\021\002H\005qq-\032;TS:<G.Z\"iS2$GcA\027\002J!A\0211GA\"\001\004\t)\004C\004\002N\001!\t!a\024\002\027M,g\016Z'fgN\fw-\032\013\004)\006E\003\002CA*\003\027\002\r!!\026\002\0075\034x\r\005\003\002X\005uSBAA-\025\r\tY\006B\001\tI&\034\b/\031;dQ&!\021qLA-\005!)eN^3m_B,\007bBA2\001\021\005\021QM\001\022g\026tGmU=ti\026lW*Z:tC\036,Gc\001+\002h!A\0211KA1\001\004\tI\007\005\003\002l\005ETBAA7\025\021\ty'!\027\002\rML8/\\:h\023\021\t\031(!\034\003\033MK8\017^3n\033\026\0348/Y4f\021\035\t9\b\001C\001\003\003\tq![:M_\016\fG\016\003\005\002|\001\001KQBA?\003-\031W\r\0347JgJ+\027\rZ=\025\t\005\r\021q\020\005\0071\006e\004\031A\007\t\017\005\r\005\001\"\001\002\002\005Y\001.Y:NKN\034\030mZ3t\021\035\t9\t\001C\001\003\023\013\001C\\;nE\026\024xJZ'fgN\fw-Z:\026\005\005-\005c\001\005\002\016&\031\021qR\005\003\007%sG\017\003\005\002\024\002\001KQBAK\003\031awnY6fIV!\021qSAO)\021\tI*!+\021\t\005m\025Q\024\007\001\t!\ty*!%C\002\005\005&!\001+\022\007\005\rf\nE\002\t\003KK1!a*\n\005\035qu\016\0365j]\036D\021\"a+\002\022\022\005\r!!,\002\t\t|G-\037\t\006\021\005=\026\021T\005\004\003cK!\001\003\037cs:\fW.\032 ")
/*     */ public class UnstartedCell implements Cell {
/*     */   private final ActorSystemImpl systemImpl;
/*     */   
/*     */   private final RepointableActorRef self;
/*     */   
/*     */   private final Props props;
/*     */   
/*     */   private final InternalActorRef supervisor;
/*     */   
/*     */   private final ReentrantLock lock;
/*     */   
/*     */   public final LinkedList<Object> akka$actor$UnstartedCell$$queue;
/*     */   
/*     */   public final void sendMessage(Object message, ActorRef sender) {
/* 174 */     Cell$class.sendMessage(this, message, sender);
/*     */   }
/*     */   
/*     */   public ActorSystemImpl systemImpl() {
/* 174 */     return this.systemImpl;
/*     */   }
/*     */   
/*     */   public UnstartedCell(ActorSystemImpl systemImpl, RepointableActorRef self, Props props, InternalActorRef supervisor) {
/* 174 */     Cell$class.$init$(this);
/* 183 */     this.lock = new ReentrantLock();
/* 186 */     this.akka$actor$UnstartedCell$$queue = new LinkedList();
/*     */   }
/*     */   
/*     */   public RepointableActorRef self() {
/*     */     return this.self;
/*     */   }
/*     */   
/*     */   public Props props() {
/*     */     return this.props;
/*     */   }
/*     */   
/*     */   public InternalActorRef supervisor() {
/*     */     return this.supervisor;
/*     */   }
/*     */   
/*     */   public void replaceWith(Cell cell) {
/* 190 */     locked(
/* 191 */         (Function0<?>)new UnstartedCell$$anonfun$replaceWith$1(this, cell));
/*     */   }
/*     */   
/*     */   public class UnstartedCell$$anonfun$replaceWith$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Cell cell$1;
/*     */     
/*     */     public final void apply() {
/* 191 */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public UnstartedCell$$anonfun$replaceWith$1(UnstartedCell $outer, Cell cell$1) {}
/*     */     
/*     */     public void apply$mcV$sp() {
/*     */       try {
/*     */         while (true) {
/* 192 */           if (this.$outer.akka$actor$UnstartedCell$$queue.isEmpty())
/*     */             return; 
/* 193 */           Object object = this.$outer.akka$actor$UnstartedCell$$queue.poll();
/* 194 */           if (object instanceof SystemMessage) {
/* 194 */             SystemMessage systemMessage = (SystemMessage)object;
/* 194 */             this.cell$1.sendSystemMessage(systemMessage);
/* 194 */             BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */             continue;
/*     */           } 
/* 195 */           if (object instanceof Envelope) {
/* 195 */             Envelope envelope = (Envelope)object;
/* 195 */             this.cell$1.sendMessage(envelope);
/* 195 */             BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */             continue;
/*     */           } 
/*     */           throw new MatchError(object);
/*     */         } 
/*     */       } finally {
/* 199 */         this.$outer.self().swapCell(this.cell$1);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public ActorSystem system() {
/* 203 */     return systemImpl();
/*     */   }
/*     */   
/*     */   public UnstartedCell start() {
/* 204 */     return this;
/*     */   }
/*     */   
/*     */   public void suspend() {
/* 205 */     sendSystemMessage((SystemMessage)new Suspend());
/*     */   }
/*     */   
/*     */   public void resume(Throwable causedByFailure) {
/* 206 */     sendSystemMessage((SystemMessage)new Resume(causedByFailure));
/*     */   }
/*     */   
/*     */   public void restart(Throwable cause) {
/* 207 */     sendSystemMessage((SystemMessage)new Recreate(cause));
/*     */   }
/*     */   
/*     */   public void stop() {
/* 208 */     sendSystemMessage((SystemMessage)new Terminate());
/*     */   }
/*     */   
/*     */   public boolean isTerminated() {
/* 209 */     return BoxesRunTime.unboxToBoolean(locked((Function0<?>)new UnstartedCell$$anonfun$isTerminated$1(this)));
/*     */   }
/*     */   
/*     */   public class UnstartedCell$$anonfun$isTerminated$1 extends AbstractFunction0.mcZ.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply() {
/* 209 */       return apply$mcZ$sp();
/*     */     }
/*     */     
/*     */     public UnstartedCell$$anonfun$isTerminated$1(UnstartedCell $outer) {}
/*     */     
/*     */     public boolean apply$mcZ$sp() {
/* 210 */       Cell cell = this.$outer.self().underlying();
/* 211 */       return this.$outer.akka$actor$UnstartedCell$$cellIsReady(cell) ? cell.isTerminated() : false;
/*     */     }
/*     */   }
/*     */   
/*     */   public InternalActorRef parent() {
/* 213 */     return supervisor();
/*     */   }
/*     */   
/*     */   public ChildrenContainer childrenRefs() {
/* 214 */     return (ChildrenContainer)ChildrenContainer$EmptyChildrenContainer$.MODULE$;
/*     */   }
/*     */   
/*     */   public Option<ChildRestartStats> getChildByName(String name) {
/* 215 */     return (Option<ChildRestartStats>)None$.MODULE$;
/*     */   }
/*     */   
/*     */   public InternalActorRef getSingleChild(String name) {
/* 216 */     return Nobody$.MODULE$;
/*     */   }
/*     */   
/*     */   public void sendMessage(Envelope msg) {
/* 219 */     if (this.lock.tryLock(systemImpl().settings().UnstartedPushTimeout().duration().length(), systemImpl().settings().UnstartedPushTimeout().duration().unit()))
/*     */       try {
/* 221 */         Cell cell = self().underlying();
/* 222 */         if (akka$actor$UnstartedCell$$cellIsReady(cell)) {
/* 223 */           cell.sendMessage(msg);
/* 224 */         } else if (!this.akka$actor$UnstartedCell$$queue.offer(msg)) {
/* 225 */           system().eventStream().publish(new Logging.Warning(self().path().toString(), getClass(), (new StringBuilder()).append("dropping message of type ").append(msg.message().getClass()).append(" due to enqueue failure").toString()));
/* 226 */           system().deadLetters().tell(new DeadLetter(msg.message(), msg.sender(), self()), msg.sender());
/*     */         } 
/*     */         return;
/*     */       } finally {
/* 228 */         this.lock.unlock();
/*     */       }  
/* 230 */     system().eventStream().publish(new Logging.Warning(self().path().toString(), getClass(), (new StringBuilder()).append("dropping message of type").append(msg.message().getClass()).append(" due to lock timeout").toString()));
/* 231 */     system().deadLetters().tell(new DeadLetter(msg.message(), msg.sender(), self()), msg.sender());
/*     */   }
/*     */   
/*     */   public void sendSystemMessage(SystemMessage msg) {
/* 236 */     this.lock.lock();
/*     */     try {
/* 238 */       Cell cell = self().underlying();
/* 239 */       if (akka$actor$UnstartedCell$$cellIsReady(cell)) {
/* 240 */         cell.sendSystemMessage(msg);
/*     */       } else {
/* 243 */         boolean wasEnqueued = (self().lookup() != this && self().underlying() == this && !this.akka$actor$UnstartedCell$$queue.isEmpty()) ? 
/*     */           
/* 251 */           tryEnqueue$1(tryEnqueue$default$1$1(), tryEnqueue$default$2$1(), msg) : 
/* 252 */           this.akka$actor$UnstartedCell$$queue.offer(msg);
/* 254 */         if (!wasEnqueued) {
/* 255 */           system().eventStream().publish(new Logging.Warning(self().path().toString(), getClass(), (new StringBuilder()).append("dropping system message ").append(msg).append(" due to enqueue failure").toString()));
/* 256 */           ScalaActorRef qual$1 = package$.MODULE$.actorRef2Scala(system().deadLetters());
/* 256 */           DeadLetter x$2 = new DeadLetter(msg, self(), self());
/* 256 */           ActorRef x$3 = qual$1.$bang$default$2(x$2);
/* 256 */           qual$1.$bang(x$2, x$3);
/*     */         } 
/*     */       } 
/*     */       return;
/*     */     } finally {
/* 259 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private final ListIterator tryEnqueue$default$1$1() {
/*     */     return this.akka$actor$UnstartedCell$$queue.listIterator();
/*     */   }
/*     */   
/*     */   private final int tryEnqueue$default$2$1() {
/*     */     return -1;
/*     */   }
/*     */   
/*     */   private final boolean tryEnqueue$1(ListIterator i, int insertIntoIndex, SystemMessage msg$1) {
/*     */     for (; i.hasNext(); i = i)
/*     */       insertIntoIndex = (i.next() instanceof SystemMessage) ? i.nextIndex() : insertIntoIndex; 
/*     */     return (insertIntoIndex == -1) ? this.akka$actor$UnstartedCell$$queue.offer(msg$1) : Try$.MODULE$.apply((Function0)new UnstartedCell$$anonfun$tryEnqueue$1$1(this, msg$1, insertIntoIndex)).isSuccess();
/*     */   }
/*     */   
/*     */   public class UnstartedCell$$anonfun$tryEnqueue$1$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final SystemMessage msg$1;
/*     */     
/*     */     private final int insertIntoIndex$1;
/*     */     
/*     */     public final void apply() {
/*     */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/*     */       this.$outer.akka$actor$UnstartedCell$$queue.add(this.insertIntoIndex$1, this.msg$1);
/*     */     }
/*     */     
/*     */     public UnstartedCell$$anonfun$tryEnqueue$1$1(UnstartedCell $outer, SystemMessage msg$1, int insertIntoIndex$1) {}
/*     */   }
/*     */   
/*     */   public boolean isLocal() {
/* 262 */     return true;
/*     */   }
/*     */   
/*     */   public final boolean akka$actor$UnstartedCell$$cellIsReady(Cell cell) {
/* 264 */     return (cell != this && cell != null);
/*     */   }
/*     */   
/*     */   public boolean hasMessages() {
/* 266 */     return BoxesRunTime.unboxToBoolean(locked((Function0<?>)new UnstartedCell$$anonfun$hasMessages$1(this)));
/*     */   }
/*     */   
/*     */   public class UnstartedCell$$anonfun$hasMessages$1 extends AbstractFunction0.mcZ.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply() {
/* 266 */       return apply$mcZ$sp();
/*     */     }
/*     */     
/*     */     public UnstartedCell$$anonfun$hasMessages$1(UnstartedCell $outer) {}
/*     */     
/*     */     public boolean apply$mcZ$sp() {
/* 267 */       Cell cell = this.$outer.self().underlying();
/* 268 */       return this.$outer.akka$actor$UnstartedCell$$cellIsReady(cell) ? cell.hasMessages() : (!this.$outer.akka$actor$UnstartedCell$$queue.isEmpty());
/*     */     }
/*     */   }
/*     */   
/*     */   public int numberOfMessages() {
/* 271 */     return BoxesRunTime.unboxToInt(locked((Function0<?>)new UnstartedCell$$anonfun$numberOfMessages$1(this)));
/*     */   }
/*     */   
/*     */   public class UnstartedCell$$anonfun$numberOfMessages$1 extends AbstractFunction0.mcI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply() {
/* 271 */       return apply$mcI$sp();
/*     */     }
/*     */     
/*     */     public UnstartedCell$$anonfun$numberOfMessages$1(UnstartedCell $outer) {}
/*     */     
/*     */     public int apply$mcI$sp() {
/* 272 */       Cell cell = this.$outer.self().underlying();
/* 273 */       return this.$outer.akka$actor$UnstartedCell$$cellIsReady(cell) ? cell.numberOfMessages() : this.$outer.akka$actor$UnstartedCell$$queue.size();
/*     */     }
/*     */   }
/*     */   
/*     */   private final <T> T locked(Function0 body) {
/* 277 */     this.lock.lock();
/*     */     try {
/* 278 */       return (T)body.apply();
/*     */     } finally {
/* 278 */       this.lock.unlock();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\UnstartedCell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */