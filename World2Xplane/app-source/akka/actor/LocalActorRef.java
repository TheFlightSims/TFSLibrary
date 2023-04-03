/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.MailboxType;
/*     */ import akka.dispatch.MessageDispatcher;
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import java.io.ObjectStreamException;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0055h!B\001\003\001\0211!!\004'pG\006d\027i\031;peJ+gM\003\002\004\t\005)\021m\031;pe*\tQ!\001\003bW.\f7c\001\001\b\027A\021\001\"C\007\002\005%\021!B\001\002\021\003\016$xN\035*fM^KG\017[\"fY2\004\"\001\003\007\n\0055\021!\001\003'pG\006d'+\0324\t\021=\001!\021!Q\001\nE\tqaX:zgR,Wn\001\001\021\005!\021\022BA\n\003\005=\t5\r^8s'f\034H/Z7J[Bd\007\002C\013\001\005\003\005\013\021\002\f\002\r}\003(o\0349t!\tAq#\003\002\031\005\t)\001K]8qg\"A!\004\001B\001B\003%1$A\006`I&\034\b/\031;dQ\026\024\bC\001\017 \033\005i\"B\001\020\005\003!!\027n\0359bi\016D\027B\001\021\036\005EiUm]:bO\026$\025n\0359bi\016DWM\035\005\tE\001\021\t\021)A\005G\005aq,\\1jY\n|\007\020V=qKB\021A\004J\005\003Ku\0211\"T1jY\n|\007\020V=qK\"Aq\005\001B\001B\003%\001&A\006`gV\004XM\035<jg>\024\bC\001\005*\023\tQ#A\001\tJ]R,'O\\1m\003\016$xN\035*fM\"AA\006\001BC\002\023\005S&\001\003qCRDW#\001\030\021\005!y\023B\001\031\003\005%\t5\r^8s!\006$\b\016\003\0053\001\t\005\t\025!\003/\003\025\001\030\r\0365!\021\031!\004\001\"\001\005k\0051A(\0338jiz\"rAN\0349siZD\b\005\002\t\001!)qb\ra\001#!)Qc\ra\001-!)!d\ra\0017!)!e\ra\001G!)qe\ra\001Q!)Af\ra\001]!9a\b\001b\001\n\023y\024!C1di>\0248)\0327m+\005\001\005C\001\005B\023\t\021%AA\005BGR|'oQ3mY\"1A\t\001Q\001\n\001\013!\"Y2u_J\034U\r\0347!\021\0251\005\001\"\005H\0031qWm^!di>\0248)\0327m)\031\001\005J\023'O!\")\021*\022a\001#\00511/_:uK6DQaS#A\002!\n1A]3g\021\025iU\t1\001\027\003\025\001(o\0349t\021\025yU\t1\001\034\003)!\027n\0359bi\016DWM\035\005\006#\026\003\r\001K\001\013gV\004XM\035<jg>\024\b\"B*\001\t#!\026\001D1di>\0248i\0348uKb$X#A+\021\005!1\026BA,\003\0051\t5\r^8s\007>tG/\032=u\021\025I\006\001\"\021[\0031I7\017V3s[&t\027\r^3e+\005Y\006C\001/`\033\005i&\"\0010\002\013M\034\027\r\\1\n\005\001l&a\002\"p_2,\027M\034\025\0051\n,w\r\005\002]G&\021A-\030\002\013I\026\004(/Z2bi\026$\027%\0014\002mU\033X\rI2p]R,\007\020\036\030xCR\034\007\016K1di>\024\030\006I1oI\002\022XmY3jm\026\004C+\032:nS:\fG/\0323)C\016$xN]\025\"\003!\f1A\r\0303\021\025Q\007\001\"\021l\003\025\031H/\031:u)\005a\007C\001/n\023\tqWL\001\003V]&$\b\"\0029\001\t\003Z\027aB:vgB,g\016\032\005\006e\002!\te]\001\007e\026\034X/\\3\025\0051$\b\"B;r\001\0041\030aD2bkN,GMQ=GC&dWO]3\021\005]|hB\001=~\035\tIH0D\001{\025\tY\b#\001\004=e>|GOP\005\002=&\021a0X\001\ba\006\0347.Y4f\023\021\t\t!a\001\003\023QC'o\\<bE2,'B\001@^\021\031\t9\001\001C!W\006!1\017^8q\021\035\tY\001\001C!\003\033\t\021bZ3u!\006\024XM\034;\026\003!Bq!!\005\001\t\003\n\031\"\001\005qe>4\030\016Z3s+\t\t)\002E\002\t\003/I1!!\007\003\005A\t5\r^8s%\0264\007K]8wS\022,'\017C\004\002\036\001!\t!a\b\002\021\rD\027\016\0343sK:,\"!!\t\021\r\005\r\022QFA\031\033\t\t)C\003\003\002(\005%\022!C5n[V$\030M\0317f\025\r\tY#X\001\013G>dG.Z2uS>t\027\002BA\030\003K\021\001\"\023;fe\006\024G.\032\t\004\021\005M\022bAA\033\005\tA\021i\031;peJ+g\rC\004\002:\001!\t!a\017\002\035\035,GoU5oO2,7\t[5mIR\031\001&!\020\t\021\005}\022q\007a\001\003\003\nAA\\1nKB!\0211IA%\035\ra\026QI\005\004\003\017j\026A\002)sK\022,g-\003\003\002L\0055#AB*ue&twMC\002\002HuCq!!\025\001\t\003\n\031&\001\005hKR\034\005.\0337e)\rA\023Q\013\005\t\003/\ny\0051\001\002Z\005)a.Y7fgB)q/a\027\002B%!\021QLA\002\005!IE/\032:bi>\024\bBBA1\001\021\005q(\001\006v]\022,'\017\\=j]\036Dq!!\032\001\t\003\n9'A\ttK:$7+_:uK6lUm]:bO\026$2\001\\A5\021!\tY'a\031A\002\0055\024aB7fgN\fw-\032\t\005\003_\n)(\004\002\002r)\031\0211O\017\002\rML8/\\:h\023\021\t9(!\035\003\033MK8\017^3n\033\026\0348/Y4f\021\035\tY\b\001C!\003{\nQ\001\n2b]\036$B!a \002\006R\031A.!!\t\025\005\r\025\021\020I\001\002\b\t\t$\001\004tK:$WM\035\005\t\003W\nI\b1\001\002\bB\031A,!#\n\007\005-ULA\002B]fDq!a$\001\t\003\n\t*A\004sKN$\030M\035;\025\0071\f\031\nC\004\002\026\0065\005\031\001<\002\013\r\fWo]3\t\017\005e\005\001\"\005\002\034\006aqO]5uKJ+\007\017\\1dKR\021\021Q\024\t\0049\006}\025bAAQ;\n1\021I\\=SK\032Dc!a&\002&\006}\006#\002/\002(\006-\026bAAU;\n1A\017\033:poN\004B!!,\00202\001AaBAY\001\t\007\0211\027\002\002)F!\021QWA^!\ra\026qW\005\004\003sk&a\002(pi\"Lgn\032\t\004\003{{hB\001/~G\t\t\t\r\005\003\002D\0065WBAAc\025\021\t9-!3\002\005%|'BAAf\003\021Q\027M^1\n\t\005=\027Q\031\002\026\037\nTWm\031;TiJ,\027-\\#yG\026\004H/[8o\021%\t\031\016AI\001\n\003\n).A\b%E\006tw\r\n3fM\006,H\016\036\0233)\021\t9.a;+\t\005E\022\021\\\026\003\0037\004B!!8\002h6\021\021q\034\006\005\003C\f\031/A\005v]\016DWmY6fI*\031\021Q]/\002\025\005tgn\034;bi&|g.\003\003\002j\006}'!E;oG\",7m[3e-\006\024\030.\0318dK\"A\0211NAi\001\004\t9\t")
/*     */ public class LocalActorRef extends ActorRefWithCell implements LocalRef {
/*     */   private final ActorPath path;
/*     */   
/*     */   private final ActorCell actorCell;
/*     */   
/*     */   public final boolean isLocal() {
/* 284 */     return LocalRef$class.isLocal(this);
/*     */   }
/*     */   
/*     */   public ActorPath path() {
/* 290 */     return this.path;
/*     */   }
/*     */   
/*     */   public LocalActorRef(ActorSystemImpl _system, Props _props, MessageDispatcher _dispatcher, MailboxType _mailboxType, InternalActorRef _supervisor, ActorPath path) {
/*     */     LocalRef$class.$init$(this);
/* 303 */     this.actorCell = newActorCell(_system, this, _props, _dispatcher, _supervisor);
/* 304 */     actorCell().init(true, _mailboxType);
/*     */   }
/*     */   
/*     */   private ActorCell actorCell() {
/*     */     return this.actorCell;
/*     */   }
/*     */   
/*     */   public ActorCell newActorCell(ActorSystemImpl system, InternalActorRef ref, Props props, MessageDispatcher dispatcher, InternalActorRef supervisor) {
/* 307 */     return new ActorCell(system, ref, props, dispatcher, supervisor);
/*     */   }
/*     */   
/*     */   public ActorContext actorContext() {
/* 309 */     return actorCell();
/*     */   }
/*     */   
/*     */   public boolean isTerminated() {
/* 316 */     return actorCell().isTerminated();
/*     */   }
/*     */   
/*     */   public void start() {
/* 321 */     actorCell().start();
/*     */   }
/*     */   
/*     */   public void suspend() {
/* 330 */     actorCell().suspend();
/*     */   }
/*     */   
/*     */   public void resume(Throwable causedByFailure) {
/* 335 */     actorCell().resume(causedByFailure);
/*     */   }
/*     */   
/*     */   public void stop() {
/* 340 */     actorCell().stop();
/*     */   }
/*     */   
/*     */   public InternalActorRef getParent() {
/* 342 */     return actorCell().parent();
/*     */   }
/*     */   
/*     */   public ActorRefProvider provider() {
/* 344 */     return actorCell().provider();
/*     */   }
/*     */   
/*     */   public Iterable<ActorRef> children() {
/* 346 */     return actorCell().children();
/*     */   }
/*     */   
/*     */   public InternalActorRef getSingleChild(String name) {
/* 353 */     return actorCell().getSingleChild(name);
/*     */   }
/*     */   
/*     */   private final InternalActorRef rec$1(InternalActorRef ref, Iterator name) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: astore #4
/*     */     //   3: aload #4
/*     */     //   5: instanceof akka/actor/LocalActorRef
/*     */     //   8: ifeq -> 165
/*     */     //   11: aload #4
/*     */     //   13: checkcast akka/actor/LocalActorRef
/*     */     //   16: astore #5
/*     */     //   18: aload_2
/*     */     //   19: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   24: checkcast java/lang/String
/*     */     //   27: astore #8
/*     */     //   29: ldc '..'
/*     */     //   31: aload #8
/*     */     //   33: astore #9
/*     */     //   35: dup
/*     */     //   36: ifnonnull -> 48
/*     */     //   39: pop
/*     */     //   40: aload #9
/*     */     //   42: ifnull -> 56
/*     */     //   45: goto -> 66
/*     */     //   48: aload #9
/*     */     //   50: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   53: ifeq -> 66
/*     */     //   56: aload #5
/*     */     //   58: invokevirtual getParent : ()Lakka/actor/InternalActorRef;
/*     */     //   61: astore #10
/*     */     //   63: goto -> 109
/*     */     //   66: ldc ''
/*     */     //   68: aload #8
/*     */     //   70: astore #11
/*     */     //   72: dup
/*     */     //   73: ifnonnull -> 85
/*     */     //   76: pop
/*     */     //   77: aload #11
/*     */     //   79: ifnull -> 93
/*     */     //   82: goto -> 100
/*     */     //   85: aload #11
/*     */     //   87: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   90: ifeq -> 100
/*     */     //   93: aload #5
/*     */     //   95: astore #10
/*     */     //   97: goto -> 109
/*     */     //   100: aload #5
/*     */     //   102: aload #8
/*     */     //   104: invokevirtual getSingleChild : (Ljava/lang/String;)Lakka/actor/InternalActorRef;
/*     */     //   107: astore #10
/*     */     //   109: aload #10
/*     */     //   111: astore #7
/*     */     //   113: aload #7
/*     */     //   115: getstatic akka/actor/Nobody$.MODULE$ : Lakka/actor/Nobody$;
/*     */     //   118: astore #12
/*     */     //   120: dup
/*     */     //   121: ifnonnull -> 133
/*     */     //   124: pop
/*     */     //   125: aload #12
/*     */     //   127: ifnull -> 150
/*     */     //   130: goto -> 141
/*     */     //   133: aload #12
/*     */     //   135: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   138: ifne -> 150
/*     */     //   141: aload_2
/*     */     //   142: invokeinterface isEmpty : ()Z
/*     */     //   147: ifeq -> 157
/*     */     //   150: aload #7
/*     */     //   152: astore #6
/*     */     //   154: goto -> 172
/*     */     //   157: aload #7
/*     */     //   159: aload_2
/*     */     //   160: astore_2
/*     */     //   161: astore_1
/*     */     //   162: goto -> 0
/*     */     //   165: aload_1
/*     */     //   166: aload_2
/*     */     //   167: invokevirtual getChild : (Lscala/collection/Iterator;)Lakka/actor/InternalActorRef;
/*     */     //   170: astore #6
/*     */     //   172: aload #6
/*     */     //   174: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #362	-> 0
/*     */     //   #363	-> 3
/*     */     //   #364	-> 18
/*     */     //   #365	-> 29
/*     */     //   #366	-> 66
/*     */     //   #367	-> 100
/*     */     //   #364	-> 109
/*     */     //   #369	-> 113
/*     */     //   #363	-> 152
/*     */     //   #369	-> 157
/*     */     //   #371	-> 165
/*     */     //   #362	-> 172
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	175	0	this	Lakka/actor/LocalActorRef;
/*     */     //   0	175	1	ref	Lakka/actor/InternalActorRef;
/*     */     //   0	175	2	name	Lscala/collection/Iterator;
/*     */     //   113	62	7	next	Lakka/actor/InternalActorRef;
/*     */   }
/*     */   
/*     */   public InternalActorRef getChild(Iterator names) {
/* 374 */     return names.isEmpty() ? this : 
/* 375 */       rec$1(this, names);
/*     */   }
/*     */   
/*     */   public ActorCell underlying() {
/* 380 */     return actorCell();
/*     */   }
/*     */   
/*     */   public void sendSystemMessage(SystemMessage message) {
/* 382 */     actorCell().sendSystemMessage(message);
/*     */   }
/*     */   
/*     */   public void $bang(Object message, ActorRef sender) {
/* 384 */     actorCell().sendMessage(message, sender);
/*     */   }
/*     */   
/*     */   public ActorRef $bang$default$2(Object message) {
/* 384 */     return Actor$.MODULE$.noSender();
/*     */   }
/*     */   
/*     */   public void restart(Throwable cause) {
/* 386 */     actorCell().restart(cause);
/*     */   }
/*     */   
/*     */   public Object writeReplace() throws ObjectStreamException {
/* 389 */     return SerializedActorRef$.MODULE$.apply(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\LocalActorRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */