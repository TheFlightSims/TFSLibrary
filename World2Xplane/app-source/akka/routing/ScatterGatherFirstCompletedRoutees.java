/*    */ package akka.routing;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.ActorSelection;
/*    */ import akka.dispatch.ExecutionContexts$sameThreadExecutionContext$;
/*    */ import akka.pattern.AskableActorRef$;
/*    */ import akka.pattern.AskableActorSelection$;
/*    */ import akka.pattern.PipeToSupport;
/*    */ import akka.pattern.package$;
/*    */ import akka.util.Timeout;
/*    */ import scala.Function1;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.immutable.IndexedSeq;
/*    */ import scala.concurrent.ExecutionContext;
/*    */ import scala.concurrent.Promise;
/*    */ import scala.concurrent.Promise$;
/*    */ import scala.concurrent.duration.FiniteDuration;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005Ed!B\001\003\001\0221!AI*dCR$XM]$bi\",'OR5sgR\034u.\0349mKR,GMU8vi\026,7O\003\002\004\t\0059!o\\;uS:<'\"A\003\002\t\005\\7.Y\n\006\001\035i\021\003\006\t\003\021-i\021!\003\006\002\025\005)1oY1mC&\021A\"\003\002\007\003:L(+\0324\021\0059yQ\"\001\002\n\005A\021!A\002*pkR,W\r\005\002\t%%\0211#\003\002\b!J|G-^2u!\tAQ#\003\002\027\023\ta1+\032:jC2L'0\0312mK\"A\001\004\001BK\002\023\005!$A\004s_V$X-Z:\004\001U\t1\004E\002\035C5i\021!\b\006\003=}\t\021\"[7nkR\f'\r\\3\013\005\001J\021AC2pY2,7\r^5p]&\021!%\b\002\013\023:$W\r_3e'\026\f\b\002\003\023\001\005#\005\013\021B\016\002\021I|W\017^3fg\002B\001B\n\001\003\026\004%\taJ\001\007o&$\b.\0338\026\003!\002\"!\013\030\016\003)R!a\013\027\002\021\021,(/\031;j_:T!!L\005\002\025\r|gnY;se\026tG/\003\0020U\tqa)\0338ji\026$UO]1uS>t\007\002C\031\001\005#\005\013\021\002\025\002\017]LG\017[5oA!)1\007\001C\001i\0051A(\0338jiz\"2!\016\0348!\tq\001\001C\003\031e\001\0071\004C\003'e\001\007\001\006C\003:\001\021\005#(\001\003tK:$GcA\036?\007B\021\001\002P\005\003{%\021A!\0268ji\")q\b\017a\001\001\0069Q.Z:tC\036,\007C\001\005B\023\t\021\025BA\002B]fDQ\001\022\035A\002\025\013aa]3oI\026\024\bC\001$J\033\0059%B\001%\005\003\025\t7\r^8s\023\tQuI\001\005BGR|'OU3g\021\035a\005!!A\005\0025\013AaY8qsR\031QGT(\t\017aY\005\023!a\0017!9ae\023I\001\002\004A\003bB)\001#\003%\tAU\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\005\031&FA\016UW\005)\006C\001,\\\033\0059&B\001-Z\003%)hn\0315fG.,GM\003\002[\023\005Q\021M\0348pi\006$\030n\0348\n\005q;&!E;oG\",7m[3e-\006\024\030.\0318dK\"9a\fAI\001\n\003y\026AD2paf$C-\0324bk2$HEM\013\002A*\022\001\006\026\005\bE\002\t\t\021\"\021d\0035\001(o\0343vGR\004&/\0324jqV\tA\r\005\002fU6\taM\003\002hQ\006!A.\0318h\025\005I\027\001\0026bm\006L!a\0334\003\rM#(/\0338h\021\035i\007!!A\005\0029\fA\002\035:pIV\034G/\021:jif,\022a\034\t\003\021AL!!]\005\003\007%sG\017C\004t\001\005\005I\021\001;\002\035A\024x\016Z;di\026cW-\\3oiR\021\001)\036\005\bmJ\f\t\0211\001p\003\rAH%\r\005\bq\002\t\t\021\"\021z\003=\001(o\0343vGRLE/\032:bi>\024X#\001>\021\007md\b)D\001 \023\tixD\001\005Ji\026\024\030\r^8s\021!y\b!!A\005\002\005\005\021\001C2b]\026\013X/\0317\025\t\005\r\021\021\002\t\004\021\005\025\021bAA\004\023\t9!i\\8mK\006t\007b\002<\003\003\005\r\001\021\005\n\003\033\001\021\021!C!\003\037\t\001\002[1tQ\016{G-\032\013\002_\"I\0211\003\001\002\002\023\005\023QC\001\ti>\034FO]5oOR\tA\rC\005\002\032\001\t\t\021\"\021\002\034\0051Q-];bYN$B!a\001\002\036!Aa/a\006\002\002\003\007\001\tK\003\001\003C\t9\003E\002\t\003GI1!!\n\n\005A\031VM]5bYZ+'o]5p]VKEIH\001\002\017)\tYCAA\001\022\003!\021QF\001#'\016\fG\017^3s\017\006$\b.\032:GSJ\034HoQ8na2,G/\0323S_V$X-Z:\021\0079\tyCB\005\002\005\005\005\t\022\001\003\0022M)\021qFA\032)A9\021QGA\0367!*TBAA\034\025\r\tI$C\001\beVtG/[7f\023\021\ti$a\016\003#\005\0237\017\036:bGR4UO\\2uS>t'\007C\0044\003_!\t!!\021\025\005\0055\002BCA\n\003_\t\t\021\"\022\002\026!Q\021qIA\030\003\003%\t)!\023\002\013\005\004\b\017\\=\025\013U\nY%!\024\t\ra\t)\0051\001\034\021\0311\023Q\ta\001Q!Q\021\021KA\030\003\003%\t)a\025\002\017Ut\027\r\0359msR!\021QKA1!\025A\021qKA.\023\r\tI&\003\002\007\037B$\030n\0348\021\013!\tif\007\025\n\007\005}\023B\001\004UkBdWM\r\005\n\003G\ny%!AA\002U\n1\001\037\0231\021)\t9'a\f\002\002\023%\021\021N\001\fe\026\fGMU3t_24X\r\006\002\002lA\031Q-!\034\n\007\005=dM\001\004PE*,7\r\036")
/*    */ public class ScatterGatherFirstCompletedRoutees implements Routee, Product, Serializable {
/*    */   public static final long serialVersionUID = 1L;
/*    */   
/*    */   private final IndexedSeq<Routee> routees;
/*    */   
/*    */   private final FiniteDuration within;
/*    */   
/*    */   public ScatterGatherFirstCompletedRoutees copy(IndexedSeq<Routee> routees, FiniteDuration within) {
/* 42 */     return new ScatterGatherFirstCompletedRoutees(
/* 43 */         routees, within);
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/*    */     return "ScatterGatherFirstCompletedRoutees";
/*    */   }
/*    */   
/*    */   public int productArity() {
/*    */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/*    */     int i = x$1;
/*    */     switch (i) {
/*    */       default:
/*    */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/*    */     return routees();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/*    */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/*    */     return x$1 instanceof ScatterGatherFirstCompletedRoutees;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/*    */     return ScalaRunTime$.MODULE$._hashCode(this);
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
/*    */     //   2: if_acmpeq -> 112
/*    */     //   5: aload_1
/*    */     //   6: astore_2
/*    */     //   7: aload_2
/*    */     //   8: instanceof akka/routing/ScatterGatherFirstCompletedRoutees
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 116
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/routing/ScatterGatherFirstCompletedRoutees
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual routees : ()Lscala/collection/immutable/IndexedSeq;
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual routees : ()Lscala/collection/immutable/IndexedSeq;
/*    */     //   40: astore #5
/*    */     //   42: dup
/*    */     //   43: ifnonnull -> 55
/*    */     //   46: pop
/*    */     //   47: aload #5
/*    */     //   49: ifnull -> 63
/*    */     //   52: goto -> 108
/*    */     //   55: aload #5
/*    */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   60: ifeq -> 108
/*    */     //   63: aload_0
/*    */     //   64: invokevirtual within : ()Lscala/concurrent/duration/FiniteDuration;
/*    */     //   67: aload #4
/*    */     //   69: invokevirtual within : ()Lscala/concurrent/duration/FiniteDuration;
/*    */     //   72: astore #6
/*    */     //   74: dup
/*    */     //   75: ifnonnull -> 87
/*    */     //   78: pop
/*    */     //   79: aload #6
/*    */     //   81: ifnull -> 95
/*    */     //   84: goto -> 108
/*    */     //   87: aload #6
/*    */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   92: ifeq -> 108
/*    */     //   95: aload #4
/*    */     //   97: aload_0
/*    */     //   98: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */     //   101: ifeq -> 108
/*    */     //   104: iconst_1
/*    */     //   105: goto -> 109
/*    */     //   108: iconst_0
/*    */     //   109: ifeq -> 116
/*    */     //   112: iconst_1
/*    */     //   113: goto -> 117
/*    */     //   116: iconst_0
/*    */     //   117: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #42	-> 0
/*    */     //   #63	-> 14
/*    */     //   #42	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	118	0	this	Lakka/routing/ScatterGatherFirstCompletedRoutees;
/*    */     //   0	118	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public IndexedSeq<Routee> routees() {
/* 43 */     return this.routees;
/*    */   }
/*    */   
/*    */   public FiniteDuration within() {
/* 43 */     return this.within;
/*    */   }
/*    */   
/*    */   public IndexedSeq<Routee> copy$default$1() {
/* 43 */     return routees();
/*    */   }
/*    */   
/*    */   public FiniteDuration copy$default$2() {
/* 43 */     return within();
/*    */   }
/*    */   
/*    */   public ScatterGatherFirstCompletedRoutees(IndexedSeq<Routee> routees, FiniteDuration within) {
/*    */     Product.class.$init$(this);
/*    */   }
/*    */   
/*    */   public void send(Object message, ActorRef sender) {
/* 46 */     ExecutionContexts$sameThreadExecutionContext$ ec = ExecutionContexts$sameThreadExecutionContext$.MODULE$;
/* 47 */     Timeout timeout = new Timeout(within());
/* 48 */     Promise promise = Promise$.MODULE$.apply();
/* 49 */     routees().foreach((Function1)new ScatterGatherFirstCompletedRoutees$$anonfun$send$1(this, message, timeout, promise));
/* 57 */     PipeToSupport.PipeableFuture qual$1 = package$.MODULE$.pipe(promise.future(), (ExecutionContext)ec);
/* 57 */     ActorRef x$1 = sender, x$2 = qual$1.pipeTo$default$2(x$1);
/* 57 */     qual$1.pipeTo(x$1, x$2);
/*    */   }
/*    */   
/*    */   public static Function1<Tuple2<IndexedSeq<Routee>, FiniteDuration>, ScatterGatherFirstCompletedRoutees> tupled() {
/*    */     return ScatterGatherFirstCompletedRoutees$.MODULE$.tupled();
/*    */   }
/*    */   
/*    */   public static Function1<IndexedSeq<Routee>, Function1<FiniteDuration, ScatterGatherFirstCompletedRoutees>> curried() {
/*    */     return ScatterGatherFirstCompletedRoutees$.MODULE$.curried();
/*    */   }
/*    */   
/*    */   public class ScatterGatherFirstCompletedRoutees$$anonfun$send$1 extends AbstractFunction1<Routee, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Object message$1;
/*    */     
/*    */     private final Timeout timeout$1;
/*    */     
/*    */     private final Promise promise$1;
/*    */     
/*    */     public final Object apply(Routee x0$1) {
/*    */       BoxedUnit boxedUnit;
/*    */       Routee routee = x0$1;
/*    */       if (routee instanceof ActorRefRoutee) {
/*    */         ActorRefRoutee actorRefRoutee = (ActorRefRoutee)routee;
/*    */         ActorRef ref = actorRefRoutee.ref();
/*    */         Promise promise = this.promise$1.tryCompleteWith(AskableActorRef$.MODULE$.ask$extension(package$.MODULE$.ask(ref), this.message$1, this.timeout$1));
/*    */       } else if (routee instanceof ActorSelectionRoutee) {
/*    */         ActorSelectionRoutee actorSelectionRoutee = (ActorSelectionRoutee)routee;
/*    */         ActorSelection sel = actorSelectionRoutee.selection();
/*    */         Promise promise = this.promise$1.tryCompleteWith(AskableActorSelection$.MODULE$.ask$extension(package$.MODULE$.ask(sel), this.message$1, this.timeout$1));
/*    */       } else {
/*    */         boxedUnit = BoxedUnit.UNIT;
/*    */       } 
/*    */       return boxedUnit;
/*    */     }
/*    */     
/*    */     public ScatterGatherFirstCompletedRoutees$$anonfun$send$1(ScatterGatherFirstCompletedRoutees $outer, Object message$1, Timeout timeout$1, Promise promise$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\ScatterGatherFirstCompletedRoutees.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */