/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005Uc!B\001\003\001\032A!A\006#fCRDw+\031;dQ:{G/\0334jG\006$\030n\0348\013\005\r!\021AB:zg6\034xM\003\002\006\r\005AA-[:qCR\034\007NC\001\b\003\021\t7n[1\024\013\001Iqb\005\f\021\005)iQ\"A\006\013\0031\tQa]2bY\006L!AD\006\003\r\005s\027PU3g!\t\001\022#D\001\003\023\t\021\"AA\007TsN$X-\\'fgN\fw-\032\t\003\025QI!!F\006\003\017A\023x\016Z;diB\021!bF\005\0031-\021AbU3sS\006d\027N_1cY\026D\001B\007\001\003\026\004%\t\001H\001\006C\016$xN]\002\001+\005i\002C\001\020!\033\005y\"B\001\016\007\023\t\tsD\001\005BGR|'OU3g\021!\031\003A!E!\002\023i\022AB1di>\024\b\005\003\005&\001\tU\r\021\"\001'\003I)\0070[:uK:\034WmQ8oM&\024X.\0323\026\003\035\002\"A\003\025\n\005%Z!a\002\"p_2,\027M\034\005\tW\001\021\t\022)A\005O\005\031R\r_5ti\026t7-Z\"p]\032L'/\\3eA!AQ\006\001BK\002\023\005a%A\tbI\022\024Xm]:UKJl\027N\\1uK\022D\001b\f\001\003\022\003\006IaJ\001\023C\022$'/Z:t)\026\024X.\0338bi\026$\007\005C\0032\001\021\005!'\001\004=S:LGO\020\013\005gQ*d\007\005\002\021\001!)!\004\ra\001;!)Q\005\ra\001O!)Q\006\ra\001O!9\001\bAA\001\n\003I\024\001B2paf$Ba\r\036<y!9!d\016I\001\002\004i\002bB\0238!\003\005\ra\n\005\b[]\002\n\0211\001(\021\035q\004!%A\005\002}\nabY8qs\022\"WMZ1vYR$\023'F\001AU\ti\022iK\001C!\t\031\005*D\001E\025\t)e)A\005v]\016DWmY6fI*\021qiC\001\013C:tw\016^1uS>t\027BA%E\005E)hn\0315fG.,GMV1sS\006t7-\032\005\b\027\002\t\n\021\"\001M\0039\031w\016]=%I\0264\027-\0367uII*\022!\024\026\003O\005Cqa\024\001\022\002\023\005A*\001\bd_BLH\005Z3gCVdG\017J\032\t\017E\003\021\021!C!%\006i\001O]8ek\016$\bK]3gSb,\022a\025\t\003)fk\021!\026\006\003-^\013A\001\\1oO*\t\001,\001\003kCZ\f\027B\001.V\005\031\031FO]5oO\"9A\fAA\001\n\003i\026\001\0049s_\022,8\r^!sSRLX#\0010\021\005)y\026B\0011\f\005\rIe\016\036\005\bE\002\t\t\021\"\001d\0039\001(o\0343vGR,E.Z7f]R$\"\001Z4\021\005))\027B\0014\f\005\r\te.\037\005\bQ\006\f\t\0211\001_\003\rAH%\r\005\bU\002\t\t\021\"\021l\003=\001(o\0343vGRLE/\032:bi>\024X#\0017\021\0075\004H-D\001o\025\ty7\"\001\006d_2dWm\031;j_:L!!\0358\003\021%#XM]1u_JDqa\035\001\002\002\023\005A/\001\005dC:,\025/^1m)\t9S\017C\004ie\006\005\t\031\0013\t\017]\004\021\021!C!q\006A\001.Y:i\007>$W\rF\001_\021\035Q\b!!A\005Bm\f\001\002^8TiJLgn\032\013\002'\"9Q\020AA\001\n\003r\030AB3rk\006d7\017\006\002(\"9\001\016`A\001\002\004!\007&\002\001\002\004\005%\001c\001\006\002\006%\031\021qA\006\003!M+'/[1m-\026\0248/[8o+&#e$A\001\b\025\0055!!!A\t\002\031\ty!\001\fEK\006$\bnV1uG\"tu\016^5gS\016\fG/[8o!\r\001\022\021\003\004\n\003\t\t\t\021#\001\007\003'\031R!!\005\002\026Y\001\002\"a\006\002\036u9seM\007\003\0033Q1!a\007\f\003\035\021XO\034;j[\026LA!a\b\002\032\t\t\022IY:ue\006\034GOR;oGRLwN\\\032\t\017E\n\t\002\"\001\002$Q\021\021q\002\005\tu\006E\021\021!C#w\"Q\021\021FA\t\003\003%\t)a\013\002\013\005\004\b\017\\=\025\017M\ni#a\f\0022!1!$a\nA\002uAa!JA\024\001\0049\003BB\027\002(\001\007q\005\003\006\0026\005E\021\021!CA\003o\tq!\0368baBd\027\020\006\003\002:\005\025\003#\002\006\002<\005}\022bAA\037\027\t1q\n\035;j_:\004bACA!;\035:\023bAA\"\027\t1A+\0369mKNB\021\"a\022\0024\005\005\t\031A\032\002\007a$\003\007\003\006\002L\005E\021\021!C\005\003\033\n1B]3bIJ+7o\0347wKR\021\021q\n\t\004)\006E\023bAA*+\n1qJ\0316fGR\004")
/*     */ public class DeathWatchNotification implements SystemMessage, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final ActorRef actor;
/*     */   
/*     */   private final boolean existenceConfirmed;
/*     */   
/*     */   private final boolean addressTerminated;
/*     */   
/*     */   private transient SystemMessage next;
/*     */   
/*     */   public SystemMessage next() {
/* 261 */     return this.next;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void next_$eq(SystemMessage x$1) {
/* 261 */     this.next = x$1;
/*     */   }
/*     */   
/*     */   public void unlink() {
/* 261 */     SystemMessage$class.unlink(this);
/*     */   }
/*     */   
/*     */   public boolean unlinked() {
/* 261 */     return SystemMessage$class.unlinked(this);
/*     */   }
/*     */   
/*     */   public DeathWatchNotification copy(ActorRef actor, boolean existenceConfirmed, boolean addressTerminated) {
/* 261 */     return new DeathWatchNotification(
/* 262 */         actor, 
/* 263 */         existenceConfirmed, 
/* 264 */         addressTerminated);
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*     */     return "DeathWatchNotification";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*     */     return 3;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*     */     int i = x$1;
/*     */     switch (i) {
/*     */       default:
/*     */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/*     */     return actor();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*     */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*     */     return x$1 instanceof DeathWatchNotification;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*     */     int i = -889275714;
/*     */     i = Statics.mix(i, Statics.anyHash(actor()));
/*     */     i = Statics.mix(i, existenceConfirmed() ? 1231 : 1237);
/*     */     i = Statics.mix(i, addressTerminated() ? 1231 : 1237);
/*     */     return Statics.finalizeHash(i, 3);
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 104
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/dispatch/sysmsg/DeathWatchNotification
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 108
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/dispatch/sysmsg/DeathWatchNotification
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual actor : ()Lakka/actor/ActorRef;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual actor : ()Lakka/actor/ActorRef;
/*     */     //   40: astore #5
/*     */     //   42: dup
/*     */     //   43: ifnonnull -> 55
/*     */     //   46: pop
/*     */     //   47: aload #5
/*     */     //   49: ifnull -> 63
/*     */     //   52: goto -> 100
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 100
/*     */     //   63: aload_0
/*     */     //   64: invokevirtual existenceConfirmed : ()Z
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual existenceConfirmed : ()Z
/*     */     //   72: if_icmpne -> 100
/*     */     //   75: aload_0
/*     */     //   76: invokevirtual addressTerminated : ()Z
/*     */     //   79: aload #4
/*     */     //   81: invokevirtual addressTerminated : ()Z
/*     */     //   84: if_icmpne -> 100
/*     */     //   87: aload #4
/*     */     //   89: aload_0
/*     */     //   90: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   93: ifeq -> 100
/*     */     //   96: iconst_1
/*     */     //   97: goto -> 101
/*     */     //   100: iconst_0
/*     */     //   101: ifeq -> 108
/*     */     //   104: iconst_1
/*     */     //   105: goto -> 109
/*     */     //   108: iconst_0
/*     */     //   109: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #261	-> 0
/*     */     //   #63	-> 14
/*     */     //   #261	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	110	0	this	Lakka/dispatch/sysmsg/DeathWatchNotification;
/*     */     //   0	110	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public ActorRef actor() {
/*     */     return this.actor;
/*     */   }
/*     */   
/*     */   public ActorRef copy$default$1() {
/*     */     return actor();
/*     */   }
/*     */   
/*     */   public DeathWatchNotification(ActorRef actor, boolean existenceConfirmed, boolean addressTerminated) {
/*     */     SystemMessage$class.$init$(this);
/*     */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public boolean existenceConfirmed() {
/*     */     return this.existenceConfirmed;
/*     */   }
/*     */   
/*     */   public boolean copy$default$2() {
/*     */     return existenceConfirmed();
/*     */   }
/*     */   
/*     */   public boolean addressTerminated() {
/* 264 */     return this.addressTerminated;
/*     */   }
/*     */   
/*     */   public boolean copy$default$3() {
/* 264 */     return addressTerminated();
/*     */   }
/*     */   
/*     */   public static Function1<Tuple3<ActorRef, Object, Object>, DeathWatchNotification> tupled() {
/*     */     return DeathWatchNotification$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<ActorRef, Function1<Object, Function1<Object, DeathWatchNotification>>> curried() {
/*     */     return DeathWatchNotification$.MODULE$.curried();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\DeathWatchNotification.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */