/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\035c\001B\001\003\001\036\021!\002R3bI2+G\017^3s\025\t\031A!A\003bGR|'OC\001\006\003\021\t7n[1\004\001M!\001\001\003\b\022!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fMB\021\021bD\005\003!)\021q\001\025:pIV\034G\017\005\002\n%%\0211C\003\002\r'\026\024\030.\0317ju\006\024G.\032\005\t+\001\021)\032!C\001-\0059Q.Z:tC\036,W#A\f\021\005%A\022BA\r\013\005\r\te.\037\005\t7\001\021\t\022)A\005/\005AQ.Z:tC\036,\007\005\003\005\036\001\tU\r\021\"\001\037\003\031\031XM\0343feV\tq\004\005\002!C5\t!!\003\002#\005\tA\021i\031;peJ+g\r\003\005%\001\tE\t\025!\003 \003\035\031XM\0343fe\002B\001B\n\001\003\026\004%\tAH\001\ne\026\034\027\016]5f]RD\001\002\013\001\003\022\003\006IaH\001\013e\026\034\027\016]5f]R\004\003\"\002\026\001\t\003Y\023A\002\037j]&$h\b\006\003-[9z\003C\001\021\001\021\025)\022\0061\001\030\021\025i\022\0061\001 \021\0251\023\0061\001 \021\035\t\004!!A\005\002I\nAaY8qsR!Af\r\0336\021\035)\002\007%AA\002]Aq!\b\031\021\002\003\007q\004C\004'aA\005\t\031A\020\t\017]\002\021\023!C\001q\005q1m\0349zI\021,g-Y;mi\022\nT#A\035+\005]Q4&A\036\021\005q\nU\"A\037\013\005yz\024!C;oG\",7m[3e\025\t\001%\"\001\006b]:|G/\031;j_:L!AQ\037\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\004E\001E\005I\021A#\002\035\r|\007/\037\023eK\032\fW\017\034;%eU\taI\013\002 u!9\001\nAI\001\n\003)\025AD2paf$C-\0324bk2$He\r\005\b\025\002\t\t\021\"\021L\0035\001(o\0343vGR\004&/\0324jqV\tA\n\005\002N%6\taJ\003\002P!\006!A.\0318h\025\005\t\026\001\0026bm\006L!a\025(\003\rM#(/\0338h\021\035)\006!!A\005\002Y\013A\002\035:pIV\034G/\021:jif,\022a\026\t\003\023aK!!\027\006\003\007%sG\017C\004\\\001\005\005I\021\001/\002\035A\024x\016Z;di\026cW-\\3oiR\021q#\030\005\b=j\013\t\0211\001X\003\rAH%\r\005\bA\002\t\t\021\"\021b\003=\001(o\0343vGRLE/\032:bi>\024X#\0012\021\007\r4w#D\001e\025\t)'\"\001\006d_2dWm\031;j_:L!a\0323\003\021%#XM]1u_JDq!\033\001\002\002\023\005!.\001\005dC:,\025/^1m)\tYg\016\005\002\nY&\021QN\003\002\b\005>|G.Z1o\021\035q\006.!AA\002]Aq\001\035\001\002\002\023\005\023/\001\005iCND7i\0343f)\0059\006bB:\001\003\003%\t\005^\001\ti>\034FO]5oOR\tA\nC\004w\001\005\005I\021I<\002\r\025\fX/\0317t)\tY\007\020C\004_k\006\005\t\031A\f)\007\001QX\020\005\002\nw&\021AP\003\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\022!A\004\t\n\t\t\021#\001\002\002\005QA)Z1e\031\026$H/\032:\021\007\001\n\031A\002\005\002\005\005\005\t\022AA\003'\025\t\031!a\002\022!!\tI!a\004\030?}aSBAA\006\025\r\tiAC\001\beVtG/[7f\023\021\t\t\"a\003\003#\005\0237\017\036:bGR4UO\\2uS>t7\007C\004+\003\007!\t!!\006\025\005\005\005\001\002C:\002\004\005\005IQ\t;\t\025\005m\0211AA\001\n\003\013i\"A\003baBd\027\020F\004-\003?\t\t#a\t\t\rU\tI\0021\001\030\021\031i\022\021\004a\001?!1a%!\007A\002}A!\"a\n\002\004\005\005I\021QA\025\003\035)h.\0319qYf$B!a\013\0028A)\021\"!\f\0022%\031\021q\006\006\003\r=\003H/[8o!\031I\0211G\f ?%\031\021Q\007\006\003\rQ+\b\017\\34\021%\tI$!\n\002\002\003\007A&A\002yIAB!\"!\020\002\004\005\005I\021BA \003-\021X-\0313SKN|GN^3\025\005\005\005\003cA'\002D%\031\021Q\t(\003\r=\023'.Z2u\001")
/*     */ public class DeadLetter implements Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Object message;
/*     */   
/*     */   private final ActorRef sender;
/*     */   
/*     */   private final ActorRef recipient;
/*     */   
/*     */   public static Function1<Tuple3<Object, ActorRef, ActorRef>, DeadLetter> tupled() {
/*     */     return DeadLetter$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<Object, Function1<ActorRef, Function1<ActorRef, DeadLetter>>> curried() {
/*     */     return DeadLetter$.MODULE$.curried();
/*     */   }
/*     */   
/*     */   public Object message() {
/* 454 */     return this.message;
/*     */   }
/*     */   
/*     */   public ActorRef sender() {
/* 454 */     return this.sender;
/*     */   }
/*     */   
/*     */   public ActorRef recipient() {
/* 454 */     return this.recipient;
/*     */   }
/*     */   
/*     */   public DeadLetter copy(Object message, ActorRef sender, ActorRef recipient) {
/* 454 */     return new DeadLetter(message, sender, recipient);
/*     */   }
/*     */   
/*     */   public Object copy$default$1() {
/* 454 */     return message();
/*     */   }
/*     */   
/*     */   public ActorRef copy$default$2() {
/* 454 */     return sender();
/*     */   }
/*     */   
/*     */   public ActorRef copy$default$3() {
/* 454 */     return recipient();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 454 */     return "DeadLetter";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 454 */     return 3;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 454 */     int i = x$1;
/* 454 */     switch (i) {
/*     */       default:
/* 454 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 454 */     return message();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 454 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 454 */     return x$1 instanceof DeadLetter;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 454 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 454 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 127
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/actor/DeadLetter
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 131
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/DeadLetter
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual message : ()Ljava/lang/Object;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual message : ()Ljava/lang/Object;
/*     */     //   40: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */     //   43: ifeq -> 123
/*     */     //   46: aload_0
/*     */     //   47: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */     //   50: aload #4
/*     */     //   52: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */     //   55: astore #5
/*     */     //   57: dup
/*     */     //   58: ifnonnull -> 70
/*     */     //   61: pop
/*     */     //   62: aload #5
/*     */     //   64: ifnull -> 78
/*     */     //   67: goto -> 123
/*     */     //   70: aload #5
/*     */     //   72: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   75: ifeq -> 123
/*     */     //   78: aload_0
/*     */     //   79: invokevirtual recipient : ()Lakka/actor/ActorRef;
/*     */     //   82: aload #4
/*     */     //   84: invokevirtual recipient : ()Lakka/actor/ActorRef;
/*     */     //   87: astore #6
/*     */     //   89: dup
/*     */     //   90: ifnonnull -> 102
/*     */     //   93: pop
/*     */     //   94: aload #6
/*     */     //   96: ifnull -> 110
/*     */     //   99: goto -> 123
/*     */     //   102: aload #6
/*     */     //   104: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   107: ifeq -> 123
/*     */     //   110: aload #4
/*     */     //   112: aload_0
/*     */     //   113: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   116: ifeq -> 123
/*     */     //   119: iconst_1
/*     */     //   120: goto -> 124
/*     */     //   123: iconst_0
/*     */     //   124: ifeq -> 131
/*     */     //   127: iconst_1
/*     */     //   128: goto -> 132
/*     */     //   131: iconst_0
/*     */     //   132: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #454	-> 0
/*     */     //   #63	-> 14
/*     */     //   #454	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	133	0	this	Lakka/actor/DeadLetter;
/*     */     //   0	133	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public DeadLetter(Object message, ActorRef sender, ActorRef recipient) {
/* 454 */     Product.class.$init$(this);
/* 455 */     Predef$.MODULE$.require((sender != null), (Function0)new DeadLetter$$anonfun$1(this));
/* 456 */     Predef$.MODULE$.require((recipient != null), (Function0)new DeadLetter$$anonfun$2(this));
/*     */   }
/*     */   
/*     */   public class DeadLetter$$anonfun$1 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/*     */       return "DeadLetter sender may not be null";
/*     */     }
/*     */     
/*     */     public DeadLetter$$anonfun$1(DeadLetter $outer) {}
/*     */   }
/*     */   
/*     */   public class DeadLetter$$anonfun$2 extends AbstractFunction0<String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply() {
/* 456 */       return "DeadLetter recipient may not be null";
/*     */     }
/*     */     
/*     */     public DeadLetter$$anonfun$2(DeadLetter $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\DeadLetter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */