/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.StringContext;
/*     */ import scala.collection.Seq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001e<Q!\001\002\t\002\035\t\001\"Q2u_J\024VM\032\006\003\007\021\tQ!Y2u_JT\021!B\001\005C.\\\027m\001\001\021\005!IQ\"\001\002\007\013)\021\001\022A\006\003\021\005\033Go\034:SK\032\0342!\003\007\023!\ti\001#D\001\017\025\005y\021!B:dC2\f\027BA\t\017\005\031\te.\037*fMB\021QbE\005\003)9\021AbU3sS\006d\027N_1cY\026DQAF\005\005\002]\ta\001P5oSRtD#A\004\t\017eI!\031!C\0035\005Aan\\*f]\022,'/F\001\034!\tAAD\002\004\013\005\005\005QD\\\n\00591q\"\003E\002 Imi\021\001\t\006\003C\t\nA\001\\1oO*\t1%\001\003kCZ\f\027BA\023!\005)\031u.\0349be\006\024G.\032\005\006-q!\ta\n\013\0027!)\021\006\bD\001U\005!\001/\031;i+\005Y\003C\001\005-\023\ti#AA\005BGR|'\017U1uQ\")q\006\bC\003a\005I1m\\7qCJ,Gk\034\013\003cQ\002\"!\004\032\n\005Mr!aA%oi\")QG\fa\0017\005)q\016\0365fe\")q\007\bC\003q\005!A/\0327m)\rID(\021\t\003\033iJ!a\017\b\003\tUs\027\016\036\005\006{Y\002\rAP\001\004[N<\007CA\007@\023\t\001eBA\002B]fDQA\021\034A\002m\taa]3oI\026\024\b\"\002#\035\t\003)\025a\0024pe^\f'\017\032\013\003\r2#\"!O$\t\013!\033\0059A%\002\017\r|g\016^3yiB\021\001BS\005\003\027\n\021A\"Q2u_J\034uN\034;fqRDQ!T\"A\002y\nq!\\3tg\006<W\rC\003P9\031\005\001+\001\007jgR+'/\\5oCR,G-F\001R!\ti!+\003\002T\035\t9!i\\8mK\006t\007\006\002(V1j\003\"!\004,\n\005]s!A\0033faJ,7-\031;fI\006\n\021,\001\034Vg\026\0043m\0348uKb$hf^1uG\"D\023m\031;pe&\002\023M\0343!e\026\034W-\033<fAQ+'/\\5oCR,G\rK1di>\024\030&I\001\\\003\r\021dF\r\005\006;r!)EX\001\tQ\006\034\bnQ8eKR\t\021\007C\003a9\021\025\023-\001\004fcV\fGn\035\013\003#\nDQaY0A\002y\nA\001\0365bi\")Q\r\bC!M\006AAo\\*ue&tw\rF\001h!\tA7N\004\002\016S&\021!ND\001\007!J,G-\0324\n\0051l'AB*ue&twM\003\002k\035A\021\001b\\\005\003a\n\021\001#\0238uKJt\027\r\\!di>\024(+\0324\t\rIL\001\025!\004\034\003%qwnU3oI\026\024\b\005C\004u\023\005\005I\021B;\002\027I,\027\r\032*fg>dg/\032\013\002mB\021qd^\005\003q\002\022aa\0242kK\016$\b")
/*     */ public abstract class ActorRef implements Comparable<ActorRef>, Serializable {
/*     */   public final int compareTo(ActorRef other) {
/* 112 */     int x = path().compareTo(other.path());
/* 113 */     return (x == 0) ? ((path().uid() < other.path().uid()) ? -1 : ((path().uid() == other.path().uid()) ? 0 : 1)) : 
/* 114 */       x;
/*     */   }
/*     */   
/*     */   public final void tell(Object msg, ActorRef sender) {
/* 123 */     ((ScalaActorRef)this).$bang(msg, sender);
/*     */   }
/*     */   
/*     */   public void forward(Object message, ActorContext context) {
/* 130 */     tell(message, context.sender());
/*     */   }
/*     */   
/*     */   public final int hashCode() {
/* 140 */     return (path().uid() == 0) ? path().hashCode() : 
/* 141 */       path().uid();
/*     */   }
/*     */   
/*     */   public final boolean equals(Object that) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: astore_2
/*     */     //   2: aload_2
/*     */     //   3: instanceof akka/actor/ActorRef
/*     */     //   6: ifeq -> 76
/*     */     //   9: aload_2
/*     */     //   10: checkcast akka/actor/ActorRef
/*     */     //   13: astore_3
/*     */     //   14: aload_0
/*     */     //   15: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   18: invokeinterface uid : ()I
/*     */     //   23: aload_3
/*     */     //   24: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   27: invokeinterface uid : ()I
/*     */     //   32: if_icmpne -> 70
/*     */     //   35: aload_0
/*     */     //   36: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   39: aload_3
/*     */     //   40: invokevirtual path : ()Lakka/actor/ActorPath;
/*     */     //   43: astore #5
/*     */     //   45: dup
/*     */     //   46: ifnonnull -> 58
/*     */     //   49: pop
/*     */     //   50: aload #5
/*     */     //   52: ifnull -> 66
/*     */     //   55: goto -> 70
/*     */     //   58: aload #5
/*     */     //   60: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   63: ifeq -> 70
/*     */     //   66: iconst_1
/*     */     //   67: goto -> 71
/*     */     //   70: iconst_0
/*     */     //   71: istore #4
/*     */     //   73: goto -> 79
/*     */     //   76: iconst_0
/*     */     //   77: istore #4
/*     */     //   79: iload #4
/*     */     //   81: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #147	-> 0
/*     */     //   #148	-> 2
/*     */     //   #149	-> 76
/*     */     //   #147	-> 79
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	82	0	this	Lakka/actor/ActorRef;
/*     */     //   0	82	1	that	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 153 */     (new String[2])[0] = "Actor[";
/* 153 */     (new String[2])[1] = "]";
/* 154 */     (new String[3])[0] = "Actor[";
/* 154 */     (new String[3])[1] = "#";
/* 154 */     (new String[3])[2] = "]";
/* 154 */     return (path().uid() == 0) ? (new StringContext((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { path() })) : (new StringContext((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[3]))).s((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { path(), BoxesRunTime.boxToInteger(path().uid()) }));
/*     */   }
/*     */   
/*     */   public static ActorRef noSender() {
/*     */     return ActorRef$.MODULE$.noSender();
/*     */   }
/*     */   
/*     */   public abstract ActorPath path();
/*     */   
/*     */   public abstract boolean isTerminated();
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */