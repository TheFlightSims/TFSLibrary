/*    */ package akka.dispatch;
/*    */ 
/*    */ import akka.actor.ActorRef;
/*    */ import akka.actor.ActorSystem;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005Ub\001B\001\003\005\036\021\001\"\0228wK2|\007/\032\006\003\007\021\t\001\002Z5ta\006$8\r\033\006\002\013\005!\021m[6b\007\001\031B\001\001\005\017#A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032\004\"!C\b\n\005AQ!a\002)s_\022,8\r\036\t\003\023II!a\005\006\003\031M+'/[1mSj\f'\r\\3\t\021U\001!Q3A\005\002Y\tq!\\3tg\006<W-F\001\030!\tI\001$\003\002\032\025\t\031\021I\\=\t\021m\001!\021#Q\001\n]\t\001\"\\3tg\006<W\r\t\005\t;\001\021)\032!C\001=\00511/\0328eKJ,\022a\b\t\003A\rj\021!\t\006\003E\021\tQ!Y2u_JL!\001J\021\003\021\005\033Go\034:SK\032D\001B\n\001\003\022\003\006IaH\001\bg\026tG-\032:!\021\025A\003\001\"\003*\003\031a\024N\\5u}Q\031!\006L\027\021\005-\002Q\"\001\002\t\013U9\003\031A\f\t\013u9\003\031A\020\t\017=\002\021\021!C\001a\005!1m\0349z)\rQ\023G\r\005\b+9\002\n\0211\001\030\021\035ib\006%AA\002}Aq\001\016\001\022\002\023\005Q'\001\bd_BLH\005Z3gCVdG\017J\031\026\003YR#aF\034,\003a\002\"!\017 \016\003iR!a\017\037\002\023Ut7\r[3dW\026$'BA\037\013\003)\tgN\\8uCRLwN\\\005\003i\022\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021\035\t\005!%A\005\002\t\013abY8qs\022\"WMZ1vYR$#'F\001DU\tyr\007C\004F\001\005\005I\021\t$\002\033A\024x\016Z;diB\023XMZ5y+\0059\005C\001%N\033\005I%B\001&L\003\021a\027M\\4\013\0031\013AA[1wC&\021a*\023\002\007'R\024\030N\\4\t\017A\003\021\021!C\001#\006a\001O]8ek\016$\030I]5usV\t!\013\005\002\n'&\021AK\003\002\004\023:$\bb\002,\001\003\003%\taV\001\017aJ|G-^2u\0132,W.\0328u)\t9\002\fC\004Z+\006\005\t\031\001*\002\007a$\023\007C\004\\\001\005\005I\021\t/\002\037A\024x\016Z;di&#XM]1u_J,\022!\030\t\004=\006<R\"A0\013\005\001T\021AC2pY2,7\r^5p]&\021!m\030\002\t\023R,'/\031;pe\"9A\rAA\001\n\003)\027\001C2b]\026\013X/\0317\025\005\031L\007CA\005h\023\tA'BA\004C_>dW-\0318\t\017e\033\027\021!a\001/!91\016AA\001\n\003b\027\001\0035bg\"\034u\016Z3\025\003ICqA\034\001\002\002\023\005s.\001\005u_N#(/\0338h)\0059\005bB9\001\003\003%\tE]\001\007KF,\030\r\\:\025\005\031\034\bbB-q\003\003\005\raF\004\006k\nA\tA^\001\t\013:4X\r\\8qKB\0211f\036\004\006\003\tA\t\001_\n\004o\"\t\002\"\002\025x\t\003QH#\001<\t\013q<H\021A?\002\013\005\004\b\017\\=\025\013)rx0!\001\t\013UY\b\031A\f\t\013uY\b\031A\020\t\017\005\r1\0201\001\002\006\00511/_:uK6\0042\001IA\004\023\r\tI!\t\002\f\003\016$xN]*zgR,W\016\003\005}o\006\005I\021QA\007)\025Q\023qBA\t\021\031)\0221\002a\001/!1Q$a\003A\002}A\021\"!\006x\003\003%\t)a\006\002\017Ut\027\r\0359msR!\021\021DA\023!\025I\0211DA\020\023\r\tiB\003\002\007\037B$\030n\0348\021\013%\t\tcF\020\n\007\005\r\"B\001\004UkBdWM\r\005\n\003O\t\031\"!AA\002)\n1\001\037\0231\021%\tYc^A\001\n\023\ti#A\006sK\006$'+Z:pYZ,GCAA\030!\rA\025\021G\005\004\003gI%AB(cU\026\034G\017")
/*    */ public final class Envelope implements Product, Serializable {
/*    */   private final Object message;
/*    */   
/*    */   private final ActorRef sender;
/*    */   
/*    */   public static Envelope apply(Object paramObject, ActorRef paramActorRef, ActorSystem paramActorSystem) {
/*    */     return Envelope$.MODULE$.apply(paramObject, paramActorRef, paramActorSystem);
/*    */   }
/*    */   
/*    */   public Object message() {
/* 24 */     return this.message;
/*    */   }
/*    */   
/*    */   public ActorRef sender() {
/* 24 */     return this.sender;
/*    */   }
/*    */   
/*    */   public Envelope copy(Object message, ActorRef sender) {
/* 24 */     return new Envelope(message, sender);
/*    */   }
/*    */   
/*    */   public Object copy$default$1() {
/* 24 */     return message();
/*    */   }
/*    */   
/*    */   public ActorRef copy$default$2() {
/* 24 */     return sender();
/*    */   }
/*    */   
/*    */   public String productPrefix() {
/* 24 */     return "Envelope";
/*    */   }
/*    */   
/*    */   public int productArity() {
/* 24 */     return 2;
/*    */   }
/*    */   
/*    */   public Object productElement(int x$1) {
/* 24 */     int i = x$1;
/* 24 */     switch (i) {
/*    */       default:
/* 24 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */       case 1:
/*    */       
/*    */       case 0:
/*    */         break;
/*    */     } 
/* 24 */     return message();
/*    */   }
/*    */   
/*    */   public Iterator<Object> productIterator() {
/* 24 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */   }
/*    */   
/*    */   public boolean canEqual(Object x$1) {
/* 24 */     return x$1 instanceof Envelope;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 24 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 24 */     return ScalaRunTime$.MODULE$._toString(this);
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: if_acmpeq -> 86
/*    */     //   5: aload_1
/*    */     //   6: astore_2
/*    */     //   7: aload_2
/*    */     //   8: instanceof akka/dispatch/Envelope
/*    */     //   11: ifeq -> 19
/*    */     //   14: iconst_1
/*    */     //   15: istore_3
/*    */     //   16: goto -> 21
/*    */     //   19: iconst_0
/*    */     //   20: istore_3
/*    */     //   21: iload_3
/*    */     //   22: ifeq -> 90
/*    */     //   25: aload_1
/*    */     //   26: checkcast akka/dispatch/Envelope
/*    */     //   29: astore #4
/*    */     //   31: aload_0
/*    */     //   32: invokevirtual message : ()Ljava/lang/Object;
/*    */     //   35: aload #4
/*    */     //   37: invokevirtual message : ()Ljava/lang/Object;
/*    */     //   40: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*    */     //   43: ifeq -> 82
/*    */     //   46: aload_0
/*    */     //   47: invokevirtual sender : ()Lakka/actor/ActorRef;
/*    */     //   50: aload #4
/*    */     //   52: invokevirtual sender : ()Lakka/actor/ActorRef;
/*    */     //   55: astore #5
/*    */     //   57: dup
/*    */     //   58: ifnonnull -> 70
/*    */     //   61: pop
/*    */     //   62: aload #5
/*    */     //   64: ifnull -> 78
/*    */     //   67: goto -> 82
/*    */     //   70: aload #5
/*    */     //   72: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   75: ifeq -> 82
/*    */     //   78: iconst_1
/*    */     //   79: goto -> 83
/*    */     //   82: iconst_0
/*    */     //   83: ifeq -> 90
/*    */     //   86: iconst_1
/*    */     //   87: goto -> 91
/*    */     //   90: iconst_0
/*    */     //   91: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #24	-> 0
/*    */     //   #63	-> 14
/*    */     //   #24	-> 21
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	92	0	this	Lakka/dispatch/Envelope;
/*    */     //   0	92	1	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public Envelope(Object message, ActorRef sender) {
/* 24 */     Product.class.$init$(this);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\Envelope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */