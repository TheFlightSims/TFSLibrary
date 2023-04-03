/*     */ package akka.actor;
/*     */ 
/*     */ import akka.dispatch.sysmsg.SystemMessage;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001%4q!\001\002\002\002\02111M\001\tJ]R,'O\\1m\003\016$xN\035*fM*\0211\001B\001\006C\016$xN\035\006\002\013\005!\021m[6b'\r\001qa\003\t\003\021%i\021AA\005\003\025\t\021\001\"Q2u_J\024VM\032\t\003\0211I!!\004\002\003\033M\033\027\r\\1BGR|'OU3g\021\025y\001\001\"\001\022\003\031a\024N\\5u}\r\001A#\001\n\021\005!\001\001\"\002\013\001\r\003)\022!B:uCJ$H#\001\f\021\005]QR\"\001\r\013\003e\tQa]2bY\006L!a\007\r\003\tUs\027\016\036\005\006;\0011\tAH\001\007e\026\034X/\\3\025\005Yy\002\"\002\021\035\001\004\t\023aD2bkN,GMQ=GC&dWO]3\021\005\tRcBA\022)\035\t!s%D\001&\025\t1\003#\001\004=e>|GOP\005\0023%\021\021\006G\001\ba\006\0347.Y4f\023\tYCFA\005UQJ|w/\0312mK*\021\021\006\007\005\006]\0011\t!F\001\bgV\034\b/\0328e\021\025\001\004A\"\0012\003\035\021Xm\035;beR$\"A\006\032\t\013Mz\003\031A\021\002\013\r\fWo]3\t\013U\002a\021A\013\002\tM$x\016\035\005\006o\0011\t\001O\001\022g\026tGmU=ti\026lW*Z:tC\036,GC\001\f:\021\025Qd\0071\001<\003\035iWm]:bO\026\004\"\001P!\016\003uR!AP \002\rML8/\\:h\025\t\001E!\001\005eSN\004\030\r^2i\023\t\021UHA\007TsN$X-\\'fgN\fw-\032\005\006\t\0021\t!R\001\taJ|g/\0333feV\ta\t\005\002\t\017&\021\001J\001\002\021\003\016$xN\035*fMB\023xN^5eKJDQA\023\001\007\002-\013\021bZ3u!\006\024XM\034;\026\003IAQ!\024\001\007\0029\013\001bZ3u\007\"LG\016\032\013\003%=CQ\001\025'A\002E\013AA\\1nKB\031!E\025+\n\005Mc#\001C%uKJ\fGo\034:\021\005UCfBA\fW\023\t9\006$\001\004Qe\026$WMZ\005\0033j\023aa\025;sS:<'BA,\031\021\025a\006A\"\001^\003\035I7\017T8dC2,\022A\030\t\003/}K!\001\031\r\003\017\t{w\016\\3b]\")!\r\001D\001;\006a\021n\035+fe6Lg.\031;fIJ\031AM\0054\007\t\025\004\001a\031\002\ryI,g-\0338f[\026tGO\020\t\003\021\035L!\001\033\002\003\033\005\033Go\034:SK\032\0346m\0349f\001")
/*     */ public abstract class InternalActorRef extends ActorRef implements ScalaActorRef {
/*     */   public ActorRef $bang$default$2(Object message) {
/* 215 */     return ScalaActorRef$class.$bang$default$2(this, message);
/*     */   }
/*     */   
/*     */   public abstract void start();
/*     */   
/*     */   public abstract void resume(Throwable paramThrowable);
/*     */   
/*     */   public abstract void suspend();
/*     */   
/*     */   public abstract void restart(Throwable paramThrowable);
/*     */   
/*     */   public abstract void stop();
/*     */   
/*     */   public abstract void sendSystemMessage(SystemMessage paramSystemMessage);
/*     */   
/*     */   public abstract ActorRefProvider provider();
/*     */   
/*     */   public abstract InternalActorRef getParent();
/*     */   
/*     */   public abstract InternalActorRef getChild(Iterator<String> paramIterator);
/*     */   
/*     */   public abstract boolean isLocal();
/*     */   
/*     */   public abstract boolean isTerminated();
/*     */   
/*     */   public InternalActorRef() {
/* 215 */     ScalaActorRef$class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\InternalActorRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */