/*     */ package akka.actor;
/*     */ 
/*     */ import akka.event.EventStream;
/*     */ import akka.serialization.JavaSerializer$;
/*     */ import java.io.ObjectStreamException;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\rsAB\001\003\021\003!a!\001\nEK\006$G*\032;uKJ\f5\r^8s%\0264'BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\004\"a\002\005\016\003\t1a!\003\002\t\002\021Q!A\005#fC\022dU\r\036;fe\006\033Go\034:SK\032\0342\001C\006\022!\taq\"D\001\016\025\005q\021!B:dC2\f\027B\001\t\016\005\031\te.\037*fMB\021ABE\005\003'5\021AbU3sS\006d\027N_1cY\026DQ!\006\005\005\002]\ta\001P5oSRt4\001\001\013\002\r\031!\021\004\003\001\033\005q\031VM]5bY&TX\r\032#fC\022dU\r\036;fe\006\033Go\034:SK\032\0342\001G\006\022\021\025)\002\004\"\001\035)\005i\002C\001\020\031\033\005A\001\"\002\021\031\t\023\t\023a\003:fC\022\024Vm]8mm\026$\022a\003\025\004?\r*\004c\001\007%M%\021Q%\004\002\007i\"\024xn^:\021\005\035BC\002\001\003\006S\001\021\rA\013\002\002)F\0211F\f\t\003\0311J!!L\007\003\0179{G\017[5oOB\021qF\r\b\003\031AJ!!M\007\002\017A\f7m[1hK&\0211\007\016\002\n)\"\024xn^1cY\026T!!M\007$\003Y\002\"a\016\037\016\003aR!!\017\036\002\005%|'\"A\036\002\t)\fg/Y\005\003{a\022Qc\0242kK\016$8\013\036:fC6,\005pY3qi&|g\016K\002\031\t\003\"\001\004!\n\005\005k!\001E*fe&\fGNV3sg&|g.V%E=\005\t\001b\002#\t\005\004%\t!R\001\013g\026\024\030.\0317ju\026$W#A\017\t\r\035C\001\025!\003\036\003-\031XM]5bY&TX\r\032\021\t\017\001B\021\021!C\005\023R\t!\n\005\002L\0356\tAJ\003\002Nu\005!A.\0318h\023\tyEJ\001\004PE*,7\r\036\004\006\023\t\001A!U\n\003!J\003\"aB*\n\005Q\023!AE#naRLHj\\2bY\006\033Go\034:SK\032D\021B\026)\003\002\003\006Ia\026.\002\023}\003(o\034<jI\026\024\bCA\004Y\023\tI&A\001\tBGR|'OU3g!J|g/\0333fe&\0211lU\001\taJ|g/\0333fe\"IQ\f\025B\001B\003%a,Y\001\006?B\fG\017\033\t\003\017}K!\001\031\002\003\023\005\033Go\034:QCRD\027B\0012T\003\021\001\030\r\0365\t\023\021\004&\021!Q\001\n\025\\\027\001D0fm\026tGo\025;sK\006l\007C\0014j\033\0059'B\0015\005\003\025)g/\0328u\023\tQwMA\006Fm\026tGo\025;sK\006l\027B\0017T\003-)g/\0328u'R\024X-Y7\t\013U\001F\021\0018\025\t=\004\030O\035\t\003\017ACQAV7A\002]CQ!X7A\002yCQ\001Z7A\002\025DQ\001\036)\005BU\fQ\001\n2b]\036$\"A^@\025\005]T\bC\001\007y\023\tIXB\001\003V]&$\bbB>t!\003\005\035\001`\001\007g\026tG-\032:\021\005\035i\030B\001@\003\005!\t5\r^8s%\0264\007bBA\001g\002\007\0211A\001\b[\026\0348/Y4f!\ra\021QA\005\004\003\017i!aA!os\"9\0211\002)\005R\0055\021!D:qK\016L\027\r\034%b]\022dW\r\006\004\002\020\005U\021\021\004\t\004\031\005E\021bAA\n\033\t9!i\\8mK\006t\007\002CA\f\003\023\001\r!a\001\002\0075\034x\r\003\004|\003\023\001\r\001 \005\007\003;\001F\021K\021\002\031]\024\030\016^3SKBd\027mY3)\013\005m\021\021E\033\021\t1!\0231\005\t\004O\005\025B!B\025\001\005\004Q\003\"CA\025!F\005I\021IA\026\003=!#-\0318hI\021,g-Y;mi\022\022D\003BA\027\003\003R3\001`A\030W\t\t\t\004\005\003\0024\005uRBAA\033\025\021\t9$!\017\002\023Ut7\r[3dW\026$'bAA\036\033\005Q\021M\0348pi\006$\030n\0348\n\t\005}\022Q\007\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007\002CA\001\003O\001\r!a\001")
/*     */ public class DeadLetterActorRef extends EmptyLocalActorRef {
/*     */   public static class SerializedDeadLetterActorRef implements Serializable {
/*     */     public static final long serialVersionUID = 1L;
/*     */     
/*     */     private Object readResolve() throws ObjectStreamException {
/* 463 */       return ((ActorSystem)JavaSerializer$.MODULE$.currentSystem().value()).deadLetters();
/*     */     }
/*     */   }
/*     */   
/*     */   public DeadLetterActorRef(ActorRefProvider _provider, ActorPath _path, EventStream _eventStream) {
/* 522 */     super(
/*     */         
/* 524 */         _provider, _path, _eventStream);
/*     */   }
/*     */   
/*     */   public void $bang(Object message, ActorRef sender) {
/* 526 */     Object object = message;
/* 527 */     if (object == null)
/* 527 */       throw new InvalidMessageException("Message is null"); 
/* 528 */     if (object instanceof Identify) {
/* 528 */       Identify identify = (Identify)object;
/* 528 */       Object messageId = identify.messageId();
/* 528 */       package$.MODULE$.actorRef2Scala(sender).$bang(new ActorIdentity(messageId, (Option<ActorRef>)new Some(this)), sender);
/* 528 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/* 529 */     } else if (object instanceof DeadLetter) {
/* 529 */       DeadLetter deadLetter = (DeadLetter)object;
/* 529 */       eventStream().publish(deadLetter);
/* 529 */       BoxedUnit boxedUnit = specialHandle(deadLetter.message(), deadLetter.sender()) ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/*     */     } else {
/* 531 */       eventStream().publish(new DeadLetter(message, (sender == Actor$.MODULE$.noSender()) ? provider().deadLetters() : sender, this));
/* 531 */       BoxedUnit boxedUnit = specialHandle(message, sender) ? BoxedUnit.UNIT : BoxedUnit.UNIT;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ActorRef $bang$default$2(Object message) {
/*     */     return this;
/*     */   }
/*     */   
/*     */   public boolean specialHandle(Object msg, ActorRef sender) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: astore_3
/*     */     //   2: aload_3
/*     */     //   3: instanceof akka/dispatch/sysmsg/Watch
/*     */     //   6: ifeq -> 101
/*     */     //   9: aload_3
/*     */     //   10: checkcast akka/dispatch/sysmsg/Watch
/*     */     //   13: astore #4
/*     */     //   15: aload #4
/*     */     //   17: invokevirtual watchee : ()Lakka/actor/InternalActorRef;
/*     */     //   20: aload_0
/*     */     //   21: astore #6
/*     */     //   23: dup
/*     */     //   24: ifnonnull -> 36
/*     */     //   27: pop
/*     */     //   28: aload #6
/*     */     //   30: ifnull -> 95
/*     */     //   33: goto -> 44
/*     */     //   36: aload #6
/*     */     //   38: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   41: ifne -> 95
/*     */     //   44: aload #4
/*     */     //   46: invokevirtual watcher : ()Lakka/actor/InternalActorRef;
/*     */     //   49: aload_0
/*     */     //   50: astore #7
/*     */     //   52: dup
/*     */     //   53: ifnonnull -> 65
/*     */     //   56: pop
/*     */     //   57: aload #7
/*     */     //   59: ifnull -> 95
/*     */     //   62: goto -> 73
/*     */     //   65: aload #7
/*     */     //   67: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   70: ifne -> 95
/*     */     //   73: aload #4
/*     */     //   75: invokevirtual watcher : ()Lakka/actor/InternalActorRef;
/*     */     //   78: new akka/dispatch/sysmsg/DeathWatchNotification
/*     */     //   81: dup
/*     */     //   82: aload #4
/*     */     //   84: invokevirtual watchee : ()Lakka/actor/InternalActorRef;
/*     */     //   87: iconst_0
/*     */     //   88: iconst_0
/*     */     //   89: invokespecial <init> : (Lakka/actor/ActorRef;ZZ)V
/*     */     //   92: invokevirtual sendSystemMessage : (Lakka/dispatch/sysmsg/SystemMessage;)V
/*     */     //   95: iconst_1
/*     */     //   96: istore #5
/*     */     //   98: goto -> 109
/*     */     //   101: aload_0
/*     */     //   102: aload_1
/*     */     //   103: aload_2
/*     */     //   104: invokespecial specialHandle : (Ljava/lang/Object;Lakka/actor/ActorRef;)Z
/*     */     //   107: istore #5
/*     */     //   109: iload #5
/*     */     //   111: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #534	-> 0
/*     */     //   #535	-> 2
/*     */     //   #536	-> 15
/*     */     //   #537	-> 73
/*     */     //   #538	-> 78
/*     */     //   #537	-> 92
/*     */     //   #539	-> 95
/*     */     //   #535	-> 96
/*     */     //   #540	-> 101
/*     */     //   #534	-> 109
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	112	0	this	Lakka/actor/DeadLetterActorRef;
/*     */     //   0	112	1	msg	Ljava/lang/Object;
/*     */     //   0	112	2	sender	Lakka/actor/ActorRef;
/*     */   }
/*     */   
/*     */   public Object writeReplace() throws ObjectStreamException {
/* 544 */     return DeadLetterActorRef$.MODULE$.serialized();
/*     */   }
/*     */   
/*     */   public static SerializedDeadLetterActorRef serialized() {
/*     */     return DeadLetterActorRef$.MODULE$.serialized();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\DeadLetterActorRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */