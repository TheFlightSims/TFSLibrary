/*    */ package akka.dispatch;
/*    */ 
/*    */ import java.util.concurrent.ArrayBlockingQueue;
/*    */ import java.util.concurrent.BlockingQueue;
/*    */ import java.util.concurrent.ExecutorService;
/*    */ import java.util.concurrent.LinkedBlockingQueue;
/*    */ import java.util.concurrent.RejectedExecutionHandler;
/*    */ import java.util.concurrent.SynchronousQueue;
/*    */ import java.util.concurrent.ThreadFactory;
/*    */ import java.util.concurrent.ThreadPoolExecutor;
/*    */ import scala.Function0;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.concurrent.duration.Duration;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ import scala.runtime.Statics;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\tuu!B\001\003\021\0039\021\001\005+ie\026\fG\rU8pY\016{gNZ5h\025\t\031A!\001\005eSN\004\030\r^2i\025\005)\021\001B1lW\006\034\001\001\005\002\t\0235\t!AB\003\013\005!\0051B\001\tUQJ,\027\r\032)p_2\034uN\0344jON\031\021\002\004\n\021\0055\001R\"\001\b\013\003=\tQa]2bY\006L!!\005\b\003\r\005s\027PU3g!\ti1#\003\002\025\035\ta1+\032:jC2L'0\0312mK\")a#\003C\001/\0051A(\0338jiz\"\022aB\003\0053%\001!D\001\007Rk\026,XMR1di>\024\030\020E\002\0167uI!\001\b\b\003\023\031+hn\031;j_:\004\004c\001\020&O5\tqD\003\002!C\005Q1m\0348dkJ\024XM\034;\013\005\t\032\023\001B;uS2T\021\001J\001\005U\0064\030-\003\002'?\ti!\t\\8dW&tw-U;fk\026\004\"\001K\026\016\003%R!AK\022\002\t1\fgnZ\005\003Y%\022\001BU;o]\006\024G.\032\005\b]%\021\r\021\"\0010\003u!WMZ1vYR\fE\016\\8x\007>\024X\r\0265sK\006$G+[7f_V$X#\001\031\021\0055\t\024B\001\032\017\005\035\021un\0347fC:Da\001N\005!\002\023\001\024A\b3fM\006,H\016^!mY><8i\034:f)\"\024X-\0313US6,w.\036;!\021\0351\024B1A\005\002]\n1\003Z3gCVdGoQ8sKB{w\016\\*ju\026,\022\001\017\t\003\033eJ!A\017\b\003\007%sG\017\003\004=\023\001\006I\001O\001\025I\0264\027-\0367u\007>\024X\rU8pYNK'0\032\021\t\017yJ!\031!C\001o\005\021B-\0324bk2$X*\031=Q_>d7+\033>f\021\031\001\025\002)A\005q\005\031B-\0324bk2$X*\031=Q_>d7+\033>fA!9!)\003b\001\n\003\031\025A\0043fM\006,H\016\036+j[\026|W\017^\013\002\tB\021Q)S\007\002\r*\021q\tS\001\tIV\024\030\r^5p]*\021\001ED\005\003\025\032\023\001\002R;sCRLwN\034\005\007\031&\001\013\021\002#\002\037\021,g-Y;miRKW.Z8vi\002BqAT\005C\002\023\005q*\001\feK\032\fW\017\034;SK*,7\r^5p]B{G.[2z+\005\001\006C\001\020R\023\t\021vD\001\rSK*,7\r^3e\013b,7-\036;j_:D\025M\0343mKJDa\001V\005!\002\023\001\026a\0063fM\006,H\016\036*fU\026\034G/[8o!>d\027nY=!\021\0251\026\002\"\001X\0039\0318-\0317fIB{w\016\\*ju\026$B\001\017-[?\")\021,\026a\001q\005)a\r\\8pe\")1,\026a\0019\006QQ.\0367uSBd\027.\032:\021\0055i\026B\0010\017\005\031!u.\0362mK\")\001-\026a\001q\00591-Z5mS:<\007\"\0022\n\t\003\031\027AE1se\006L(\t\\8dW&tw-U;fk\026$2\001\0324i!\t)\007$D\001\n\021\0259\027\r1\0019\003!\031\027\r]1dSRL\b\"B5b\001\004\001\024\001\0024bSJDQa[\005\005\0021\f\001c]=oG\"\024xN\\8vgF+X-^3\025\005\021l\007\"B5k\001\004\001\004\"B8\n\t\003\001\030a\0057j].,GM\0217pG.LgnZ)vKV,G#\0013\t\013=LA\021\001:\025\005\021\034\b\"B4r\001\004A\004\"B;\n\t\0031\030!\004:fkN\f'\r\\3Rk\026,X\r\006\002eo\")\001\020\036a\001;\005)\021/^3vK\")Q/\003C\001uR\021Am\037\005\006yf\004\r\001Z\001\rcV,W/\032$bGR|'/\037\005\b}&\t\t\021\"!\000\003\025\t\007\017\0357z)9\t\tAa\020\003B\t\r#Q\tB$\005\023\0022\001CA\002\r\025Q!\001QA\003'!\t\031\001DA\004\003\033\021\002c\001\005\002\n%\031\0211\002\002\003=\025CXmY;u_J\034VM\035<jG\0264\025m\031;pef\004&o\034<jI\026\024\bcA\007\002\020%\031\021\021\003\b\003\017A\023x\016Z;di\"Q\021QCA\002\005+\007I\021A\030\002)\005dGn\\<D_J,\007k\\8m)&lWm\\;u\021)\tI\"a\001\003\022\003\006I\001M\001\026C2dwn^\"pe\026\004vn\0347US6,w.\036;!\021)\ti\"a\001\003\026\004%\taN\001\rG>\024X\rU8pYNK'0\032\005\013\003C\t\031A!E!\002\023A\024!D2pe\026\004vn\0347TSj,\007\005\003\006\002&\005\r!Q3A\005\002]\n1\"\\1y!>|GnU5{K\"Q\021\021FA\002\005#\005\013\021\002\035\002\0315\f\007\020U8pYNK'0\032\021\t\025\0055\0221\001BK\002\023\0051)A\007uQJ,\027\r\032+j[\026|W\017\036\005\013\003c\t\031A!E!\002\023!\025A\004;ie\026\fG\rV5nK>,H\017\t\005\013y\006\r!Q3A\005\002\005URCAA\034!\r\tI\004\007\b\003\021\001A1\"!\020\002\004\tE\t\025!\003\0028\005i\021/^3vK\032\0137\r^8ss\002B!\"!\021\002\004\tU\r\021\"\001P\003=\021XM[3di&|g\016U8mS\016L\bBCA#\003\007\021\t\022)A\005!\006\001\"/\0326fGRLwN\034)pY&\034\027\020\t\005\b-\005\rA\021AA%)9\t\t!a\023\002N\005=\023\021KA*\003+B\021\"!\006\002HA\005\t\031\001\031\t\023\005u\021q\tI\001\002\004A\004\"CA\023\003\017\002\n\0211\0019\021%\ti#a\022\021\002\003\007A\tC\005}\003\017\002\n\0211\001\0028!I\021\021IA$!\003\005\r\001\025\004\b\0033\n\031\001AA.\005\001\"\006N]3bIB{w\016\\#yK\016,Ho\034:TKJ4\030nY3GC\016$xN]=\024\013\005]C\"!\030\021\007!\ty&C\002\002b\t\021a#\022=fGV$xN]*feZL7-\032$bGR|'/\037\005\f\003K\n9F!b\001\n\003\t9'A\007uQJ,\027\r\032$bGR|'/_\013\003\003S\0022AHA6\023\r\tig\b\002\016)\"\024X-\0313GC\016$xN]=\t\027\005E\024q\013B\001B\003%\021\021N\001\017i\"\024X-\0313GC\016$xN]=!\021\0351\022q\013C\001\003k\"B!a\036\002|A!\021\021PA,\033\t\t\031\001\003\005\002f\005M\004\031AA5\021!\ty(a\026\005\002\005\005\025!F2sK\006$X-\022=fGV$xN]*feZL7-Z\013\003\003\007\0032AHAC\023\r\t9i\b\002\020\013b,7-\036;peN+'O^5dK\"A\0211RA\002\t\013\ti)\001\017de\026\fG/Z#yK\016,Ho\034:TKJ4\030nY3GC\016$xN]=\025\r\005u\023qRAQ\021!\t\t*!#A\002\005M\025AA5e!\021\t)*a'\017\0075\t9*C\002\002\032:\ta\001\025:fI\0264\027\002BAO\003?\023aa\025;sS:<'bAAM\035!A\021QMAE\001\004\tI\007\003\006\002&\006\r\021\021!C\001\003O\013AaY8qsRq\021\021AAU\003W\013i+a,\0022\006M\006\"CA\013\003G\003\n\0211\0011\021%\ti\"a)\021\002\003\007\001\bC\005\002&\005\r\006\023!a\001q!I\021QFAR!\003\005\r\001\022\005\ny\006\r\006\023!a\001\003oA\021\"!\021\002$B\005\t\031\001)\t\025\005]\0261AI\001\n\003\tI,\001\bd_BLH\005Z3gCVdG\017J\031\026\005\005m&f\001\031\002>.\022\021q\030\t\005\003\003\fY-\004\002\002D*!\021QYAd\003%)hn\0315fG.,GMC\002\002J:\t!\"\0318o_R\fG/[8o\023\021\ti-a1\003#Ut7\r[3dW\026$g+\031:jC:\034W\r\003\006\002R\006\r\021\023!C\001\003'\fabY8qs\022\"WMZ1vYR$#'\006\002\002V*\032\001(!0\t\025\005e\0271AI\001\n\003\t\031.\001\bd_BLH\005Z3gCVdG\017J\032\t\025\005u\0271AI\001\n\003\ty.\001\bd_BLH\005Z3gCVdG\017\n\033\026\005\005\005(f\001#\002>\"Q\021Q]A\002#\003%\t!a:\002\035\r|\007/\037\023eK\032\fW\017\034;%kU\021\021\021\036\026\005\003o\ti\f\003\006\002n\006\r\021\023!C\001\003_\fabY8qs\022\"WMZ1vYR$c'\006\002\002r*\032\001+!0\t\025\005U\0301AA\001\n\003\n90A\007qe>$Wo\031;Qe\0264\027\016_\013\003\003s\0042\001KA~\023\r\ti*\013\005\n\003\f\031!!A\005\002]\nA\002\035:pIV\034G/\021:jifD!Ba\001\002\004\005\005I\021\001B\003\0039\001(o\0343vGR,E.Z7f]R$BAa\002\003\016A\031QB!\003\n\007\t-aBA\002B]fD\021Ba\004\003\002\005\005\t\031\001\035\002\007a$\023\007\003\006\003\024\005\r\021\021!C!\005+\tq\002\035:pIV\034G/\023;fe\006$xN]\013\003\005/\001bA!\007\003 \t\035QB\001B\016\025\r\021iBD\001\013G>dG.Z2uS>t\027\002\002B\021\0057\021\001\"\023;fe\006$xN\035\005\013\005K\t\031!!A\005\002\t\035\022\001C2b]\026\013X/\0317\025\007A\022I\003\003\006\003\020\t\r\022\021!a\001\005\017A!B!\f\002\004\005\005I\021\tB\030\003!A\027m\0355D_\022,G#\001\035\t\025\tM\0221AA\001\n\003\022)$\001\005u_N#(/\0338h)\t\tI\020\003\006\003:\005\r\021\021!C!\005w\ta!Z9vC2\034Hc\001\031\003>!Q!q\002B\034\003\003\005\rAa\002\t\021\005UQ\020%AA\002AB\001\"!\b~!\003\005\r\001\017\005\t\003Ki\b\023!a\001q!A\021QF?\021\002\003\007A\t\003\005}{B\005\t\031AA\034\021!\t\t% I\001\002\004\001\006\"\003B'\023\005\005I\021\021B(\003\035)h.\0319qYf$BA!\025\003^A)QBa\025\003X%\031!Q\013\b\003\r=\003H/[8o!)i!\021\f\0319q\021\0139\004U\005\004\0057r!A\002+va2,g\007\003\006\003`\t-\023\021!a\001\003\003\t1\001\037\0231\021%\021\031'CI\001\n\003\tI,A\bbaBd\027\020\n3fM\006,H\016\036\0232\021%\0219'CI\001\n\003\t\031.A\bbaBd\027\020\n3fM\006,H\016\036\0233\021%\021Y'CI\001\n\003\t\031.A\bbaBd\027\020\n3fM\006,H\016\036\0234\021%\021y'CI\001\n\003\ty.A\bbaBd\027\020\n3fM\006,H\016\036\0235\021%\021\031(CI\001\n\003\t9/A\bbaBd\027\020\n3fM\006,H\016\036\0236\021%\0219(CI\001\n\003\ty/A\bbaBd\027\020\n3fM\006,H\016\036\0237\021%\021Y(CI\001\n\003\tI,A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$H%\r\005\n\005J\021\023!C\001\003'\f1\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022\022\004\"\003BB\023E\005I\021AAj\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%g!I!qQ\005\022\002\023\005\021q\\\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017\n\033\t\023\t-\025\"%A\005\002\005\035\030a\007\023mKN\034\030N\\5uI\035\024X-\031;fe\022\"WMZ1vYR$S\007C\005\003\020&\t\n\021\"\001\002p\006YB\005\\3tg&t\027\016\036\023he\026\fG/\032:%I\0264\027-\0367uIYB\021Ba%\n\003\003%IA!&\002\027I,\027\r\032*fg>dg/\032\013\003\005/\0032\001\013BM\023\r\021Y*\013\002\007\037\nTWm\031;")
/*    */ public class ThreadPoolConfig implements ExecutorServiceFactoryProvider, Product, Serializable {
/*    */   private final boolean allowCorePoolTimeout;
/*    */   
/*    */   private final int corePoolSize;
/*    */   
/*    */   private final int maxPoolSize;
/*    */   
/*    */   private final Duration threadTimeout;
/*    */   
/*    */   private final Function0<BlockingQueue<Runnable>> queueFactory;
/*    */   
/*    */   private final RejectedExecutionHandler rejectionPolicy;
/*    */   
/*    */   public static class ThreadPoolConfig$$anonfun$arrayBlockingQueue$1 extends AbstractFunction0<ArrayBlockingQueue<Runnable>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final int capacity$1;
/*    */     
/*    */     private final boolean fair$1;
/*    */     
/*    */     public final ArrayBlockingQueue<Runnable> apply() {
/* 38 */       return new ArrayBlockingQueue<Runnable>(this.capacity$1, this.fair$1);
/*    */     }
/*    */     
/*    */     public ThreadPoolConfig$$anonfun$arrayBlockingQueue$1(int capacity$1, boolean fair$1) {}
/*    */   }
/*    */   
/*    */   public static class ThreadPoolConfig$$anonfun$synchronousQueue$1 extends AbstractFunction0<SynchronousQueue<Runnable>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final boolean fair$2;
/*    */     
/*    */     public final SynchronousQueue<Runnable> apply() {
/* 40 */       return new SynchronousQueue<Runnable>(this.fair$2);
/*    */     }
/*    */     
/*    */     public ThreadPoolConfig$$anonfun$synchronousQueue$1(boolean fair$2) {}
/*    */   }
/*    */   
/*    */   public static class ThreadPoolConfig$$anonfun$linkedBlockingQueue$1 extends AbstractFunction0<LinkedBlockingQueue<Runnable>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final LinkedBlockingQueue<Runnable> apply() {
/* 42 */       return new LinkedBlockingQueue<Runnable>();
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ThreadPoolConfig$$anonfun$linkedBlockingQueue$2 extends AbstractFunction0<LinkedBlockingQueue<Runnable>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final int capacity$2;
/*    */     
/*    */     public final LinkedBlockingQueue<Runnable> apply() {
/* 44 */       return new LinkedBlockingQueue<Runnable>(this.capacity$2);
/*    */     }
/*    */     
/*    */     public ThreadPoolConfig$$anonfun$linkedBlockingQueue$2(int capacity$2) {}
/*    */   }
/*    */   
/*    */   public static class ThreadPoolConfig$$anonfun$reusableQueue$1 extends AbstractFunction0<BlockingQueue<Runnable>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final BlockingQueue queue$1;
/*    */     
/*    */     public final BlockingQueue<Runnable> apply() {
/* 46 */       return this.queue$1;
/*    */     }
/*    */     
/*    */     public ThreadPoolConfig$$anonfun$reusableQueue$1(BlockingQueue queue$1) {}
/*    */   }
/*    */   
/*    */   public boolean allowCorePoolTimeout() {
/* 68 */     return this.allowCorePoolTimeout;
/*    */   }
/*    */   
/*    */   public ThreadPoolConfig copy(boolean allowCorePoolTimeout, int corePoolSize, int maxPoolSize, Duration threadTimeout, Function0<BlockingQueue<Runnable>> queueFactory, RejectedExecutionHandler rejectionPolicy) {
/* 68 */     return new ThreadPoolConfig(allowCorePoolTimeout, 
/* 69 */         corePoolSize, 
/* 70 */         maxPoolSize, 
/* 71 */         threadTimeout, 
/* 72 */         queueFactory, 
/* 73 */         rejectionPolicy);
/*    */   }
/*    */   
/*    */   public boolean copy$default$1() {
/*    */     return allowCorePoolTimeout();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/*    */     return "ThreadPoolConfig";
/*    */   }
/*    */   
/*    */   public int productArity() {
/*    */     return 6;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/*    */     int i = x$1;
/*    */     switch (i) {
/*    */       default:
/*    */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 5:
/*    */       
/*    */       case 4:
/*    */       
/*    */       case 3:
/*    */       
/*    */       case 2:
/*    */       
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/*    */     return BoxesRunTime.boxToBoolean(allowCorePoolTimeout());
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/*    */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/*    */     return x$1 instanceof ThreadPoolConfig;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/*    */     int i = -889275714;
/*    */     i = Statics.mix(i, allowCorePoolTimeout() ? 1231 : 1237);
/*    */     i = Statics.mix(i, corePoolSize());
/*    */     i = Statics.mix(i, maxPoolSize());
/*    */     i = Statics.mix(i, Statics.anyHash(threadTimeout()));
/*    */     i = Statics.mix(i, Statics.anyHash(queueFactory()));
/*    */     i = Statics.mix(i, Statics.anyHash(rejectionPolicy()));
/*    */     return Statics.finalizeHash(i, 6);
/*    */   }
/*    */   
/*    */   public String toString() {
/*    */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 180
/*    */     //   5: aload_1
/*    */     //   6: astore_2
/*    */     //   7: aload_2
/*    */     //   8: instanceof akka/dispatch/ThreadPoolConfig
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 184
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/dispatch/ThreadPoolConfig
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual allowCorePoolTimeout : ()Z
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual allowCorePoolTimeout : ()Z
/*    */     //   40: if_icmpne -> 176
/*    */     //   43: aload_0
/*    */     //   44: invokevirtual corePoolSize : ()I
/*    */     //   47: aload #4
/*    */     //   49: invokevirtual corePoolSize : ()I
/*    */     //   52: if_icmpne -> 176
/*    */     //   55: aload_0
/*    */     //   56: invokevirtual maxPoolSize : ()I
/*    */     //   59: aload #4
/*    */     //   61: invokevirtual maxPoolSize : ()I
/*    */     //   64: if_icmpne -> 176
/*    */     //   67: aload_0
/*    */     //   68: invokevirtual threadTimeout : ()Lscala/concurrent/duration/Duration;
/*    */     //   71: aload #4
/*    */     //   73: invokevirtual threadTimeout : ()Lscala/concurrent/duration/Duration;
/*    */     //   76: astore #5
/*    */     //   78: dup
/*    */     //   79: ifnonnull -> 91
/*    */     //   82: pop
/*    */     //   83: aload #5
/*    */     //   85: ifnull -> 99
/*    */     //   88: goto -> 176
/*    */     //   91: aload #5
/*    */     //   93: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   96: ifeq -> 176
/*    */     //   99: aload_0
/*    */     //   100: invokevirtual queueFactory : ()Lscala/Function0;
/*    */     //   103: aload #4
/*    */     //   105: invokevirtual queueFactory : ()Lscala/Function0;
/*    */     //   108: astore #6
/*    */     //   110: dup
/*    */     //   111: ifnonnull -> 123
/*    */     //   114: pop
/*    */     //   115: aload #6
/*    */     //   117: ifnull -> 131
/*    */     //   120: goto -> 176
/*    */     //   123: aload #6
/*    */     //   125: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   128: ifeq -> 176
/*    */     //   131: aload_0
/*    */     //   132: invokevirtual rejectionPolicy : ()Ljava/util/concurrent/RejectedExecutionHandler;
/*    */     //   135: aload #4
/*    */     //   137: invokevirtual rejectionPolicy : ()Ljava/util/concurrent/RejectedExecutionHandler;
/*    */     //   140: astore #7
/*    */     //   142: dup
/*    */     //   143: ifnonnull -> 155
/*    */     //   146: pop
/*    */     //   147: aload #7
/*    */     //   149: ifnull -> 163
/*    */     //   152: goto -> 176
/*    */     //   155: aload #7
/*    */     //   157: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   160: ifeq -> 176
/*    */     //   163: aload #4
/*    */     //   165: aload_0
/*    */     //   166: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   169: ifeq -> 176
/*    */     //   172: iconst_1
/*    */     //   173: goto -> 177
/*    */     //   176: iconst_0
/*    */     //   177: ifeq -> 184
/*    */     //   180: iconst_1
/*    */     //   181: goto -> 185
/*    */     //   184: iconst_0
/*    */     //   185: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #68	-> 0
/*    */     //   #63	-> 14
/*    */     //   #68	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	186	0	this	Lakka/dispatch/ThreadPoolConfig;
/*    */     //   0	186	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public ThreadPoolConfig(boolean allowCorePoolTimeout, int corePoolSize, int maxPoolSize, Duration threadTimeout, Function0<BlockingQueue<Runnable>> queueFactory, RejectedExecutionHandler rejectionPolicy) {
/*    */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public int corePoolSize() {
/*    */     return this.corePoolSize;
/*    */   }
/*    */   
/*    */   public int copy$default$2() {
/*    */     return corePoolSize();
/*    */   }
/*    */   
/*    */   public int maxPoolSize() {
/*    */     return this.maxPoolSize;
/*    */   }
/*    */   
/*    */   public int copy$default$3() {
/*    */     return maxPoolSize();
/*    */   }
/*    */   
/*    */   public Duration threadTimeout() {
/*    */     return this.threadTimeout;
/*    */   }
/*    */   
/*    */   public Duration copy$default$4() {
/*    */     return threadTimeout();
/*    */   }
/*    */   
/*    */   public Function0<BlockingQueue<Runnable>> queueFactory() {
/*    */     return this.queueFactory;
/*    */   }
/*    */   
/*    */   public Function0<BlockingQueue<Runnable>> copy$default$5() {
/*    */     return queueFactory();
/*    */   }
/*    */   
/*    */   public RejectedExecutionHandler rejectionPolicy() {
/* 73 */     return this.rejectionPolicy;
/*    */   }
/*    */   
/*    */   public RejectedExecutionHandler copy$default$6() {
/* 73 */     return rejectionPolicy();
/*    */   }
/*    */   
/*    */   public class ThreadPoolExecutorServiceFactory implements ExecutorServiceFactory {
/*    */     private final ThreadFactory threadFactory;
/*    */     
/*    */     public ThreadFactory threadFactory() {
/* 75 */       return this.threadFactory;
/*    */     }
/*    */     
/*    */     public ThreadPoolExecutorServiceFactory(ThreadPoolConfig $outer, ThreadFactory threadFactory) {}
/*    */     
/*    */     public ExecutorService createExecutorService() {
/* 77 */       ThreadPoolExecutor service = new ThreadPoolConfig$ThreadPoolExecutorServiceFactory$$anon$1(this);
/* 87 */       service.allowCoreThreadTimeOut(akka$dispatch$ThreadPoolConfig$ThreadPoolExecutorServiceFactory$$$outer().allowCorePoolTimeout());
/* 88 */       return service;
/*    */     }
/*    */     
/*    */     public class ThreadPoolConfig$ThreadPoolExecutorServiceFactory$$anon$1 extends ThreadPoolExecutor implements LoadMetrics {
/*    */       public ThreadPoolConfig$ThreadPoolExecutorServiceFactory$$anon$1(ThreadPoolConfig.ThreadPoolExecutorServiceFactory $outer) {
/*    */         super($outer.akka$dispatch$ThreadPoolConfig$ThreadPoolExecutorServiceFactory$$$outer().corePoolSize(), $outer.akka$dispatch$ThreadPoolConfig$ThreadPoolExecutorServiceFactory$$$outer().maxPoolSize(), $outer.akka$dispatch$ThreadPoolConfig$ThreadPoolExecutorServiceFactory$$$outer().threadTimeout().length(), $outer.akka$dispatch$ThreadPoolConfig$ThreadPoolExecutorServiceFactory$$$outer().threadTimeout().unit(), (BlockingQueue<Runnable>)$outer.akka$dispatch$ThreadPoolConfig$ThreadPoolExecutorServiceFactory$$$outer().queueFactory().apply(), $outer.threadFactory(), $outer.akka$dispatch$ThreadPoolConfig$ThreadPoolExecutorServiceFactory$$$outer().rejectionPolicy());
/*    */       }
/*    */       
/*    */       public boolean atFullThrottle() {
/*    */         return (getActiveCount() >= getPoolSize());
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public final ExecutorServiceFactory createExecutorServiceFactory(String id, ThreadFactory threadFactory) {
/* 92 */     ThreadFactory threadFactory2, threadFactory1 = threadFactory;
/* 93 */     if (threadFactory1 instanceof MonitorableThreadFactory) {
/* 93 */       MonitorableThreadFactory monitorableThreadFactory = (MonitorableThreadFactory)threadFactory1;
/* 95 */       threadFactory2 = monitorableThreadFactory.withName((new StringBuilder()).append(monitorableThreadFactory.name()).append("-").append(id).toString());
/*    */     } else {
/* 96 */       threadFactory2 = threadFactory1;
/*    */     } 
/*    */     ThreadFactory tf = threadFactory2;
/* 98 */     return new ThreadPoolExecutorServiceFactory(this, tf);
/*    */   }
/*    */   
/*    */   public static RejectedExecutionHandler $lessinit$greater$default$6() {
/*    */     return ThreadPoolConfig$.MODULE$.$lessinit$greater$default$6();
/*    */   }
/*    */   
/*    */   public static Function0<BlockingQueue<Runnable>> $lessinit$greater$default$5() {
/*    */     return ThreadPoolConfig$.MODULE$.$lessinit$greater$default$5();
/*    */   }
/*    */   
/*    */   public static Duration $lessinit$greater$default$4() {
/*    */     return ThreadPoolConfig$.MODULE$.$lessinit$greater$default$4();
/*    */   }
/*    */   
/*    */   public static int $lessinit$greater$default$3() {
/*    */     return ThreadPoolConfig$.MODULE$.$lessinit$greater$default$3();
/*    */   }
/*    */   
/*    */   public static int $lessinit$greater$default$2() {
/*    */     return ThreadPoolConfig$.MODULE$.$lessinit$greater$default$2();
/*    */   }
/*    */   
/*    */   public static boolean $lessinit$greater$default$1() {
/*    */     return ThreadPoolConfig$.MODULE$.$lessinit$greater$default$1();
/*    */   }
/*    */   
/*    */   public static RejectedExecutionHandler apply$default$6() {
/*    */     return ThreadPoolConfig$.MODULE$.apply$default$6();
/*    */   }
/*    */   
/*    */   public static Function0<BlockingQueue<Runnable>> apply$default$5() {
/*    */     return ThreadPoolConfig$.MODULE$.apply$default$5();
/*    */   }
/*    */   
/*    */   public static Duration apply$default$4() {
/*    */     return ThreadPoolConfig$.MODULE$.apply$default$4();
/*    */   }
/*    */   
/*    */   public static int apply$default$3() {
/*    */     return ThreadPoolConfig$.MODULE$.apply$default$3();
/*    */   }
/*    */   
/*    */   public static int apply$default$2() {
/*    */     return ThreadPoolConfig$.MODULE$.apply$default$2();
/*    */   }
/*    */   
/*    */   public static boolean apply$default$1() {
/*    */     return ThreadPoolConfig$.MODULE$.apply$default$1();
/*    */   }
/*    */   
/*    */   public static Function0<BlockingQueue<Runnable>> reusableQueue(Function0<BlockingQueue<Runnable>> paramFunction0) {
/*    */     return ThreadPoolConfig$.MODULE$.reusableQueue(paramFunction0);
/*    */   }
/*    */   
/*    */   public static Function0<BlockingQueue<Runnable>> reusableQueue(BlockingQueue<Runnable> paramBlockingQueue) {
/*    */     return ThreadPoolConfig$.MODULE$.reusableQueue(paramBlockingQueue);
/*    */   }
/*    */   
/*    */   public static Function0<BlockingQueue<Runnable>> linkedBlockingQueue(int paramInt) {
/*    */     return ThreadPoolConfig$.MODULE$.linkedBlockingQueue(paramInt);
/*    */   }
/*    */   
/*    */   public static Function0<BlockingQueue<Runnable>> linkedBlockingQueue() {
/*    */     return ThreadPoolConfig$.MODULE$.linkedBlockingQueue();
/*    */   }
/*    */   
/*    */   public static Function0<BlockingQueue<Runnable>> synchronousQueue(boolean paramBoolean) {
/*    */     return ThreadPoolConfig$.MODULE$.synchronousQueue(paramBoolean);
/*    */   }
/*    */   
/*    */   public static Function0<BlockingQueue<Runnable>> arrayBlockingQueue(int paramInt, boolean paramBoolean) {
/*    */     return ThreadPoolConfig$.MODULE$.arrayBlockingQueue(paramInt, paramBoolean);
/*    */   }
/*    */   
/*    */   public static int scaledPoolSize(int paramInt1, double paramDouble, int paramInt2) {
/*    */     return ThreadPoolConfig$.MODULE$.scaledPoolSize(paramInt1, paramDouble, paramInt2);
/*    */   }
/*    */   
/*    */   public static RejectedExecutionHandler defaultRejectionPolicy() {
/*    */     return ThreadPoolConfig$.MODULE$.defaultRejectionPolicy();
/*    */   }
/*    */   
/*    */   public static Duration defaultTimeout() {
/*    */     return ThreadPoolConfig$.MODULE$.defaultTimeout();
/*    */   }
/*    */   
/*    */   public static int defaultMaxPoolSize() {
/*    */     return ThreadPoolConfig$.MODULE$.defaultMaxPoolSize();
/*    */   }
/*    */   
/*    */   public static int defaultCorePoolSize() {
/*    */     return ThreadPoolConfig$.MODULE$.defaultCorePoolSize();
/*    */   }
/*    */   
/*    */   public static boolean defaultAllowCoreThreadTimeout() {
/*    */     return ThreadPoolConfig$.MODULE$.defaultAllowCoreThreadTimeout();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\ThreadPoolConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */