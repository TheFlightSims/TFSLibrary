/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\025c!B\001\003\001\032A!!C*va\026\024h/[:f\025\t\031A!\001\004tsNl7o\032\006\003\013\031\t\001\002Z5ta\006$8\r\033\006\002\017\005!\021m[6b'\025\001\021bD\n\027!\tQQ\"D\001\f\025\005a\021!B:dC2\f\027B\001\b\f\005\031\te.\037*fMB\021\001#E\007\002\005%\021!C\001\002\016'f\034H/Z7NKN\034\030mZ3\021\005)!\022BA\013\f\005\035\001&o\0343vGR\004\"AC\f\n\005aY!\001D*fe&\fG.\033>bE2,\007\002\003\016\001\005+\007I\021\001\017\002\013\rD\027\016\0343\004\001U\tQ\004\005\002\037C5\tqD\003\002!\r\005)\021m\031;pe&\021!e\b\002\t\003\016$xN\035*fM\"AA\005\001B\tB\003%Q$\001\004dQ&dG\r\t\005\tM\001\021)\032!C\001O\005)\021m]=oGV\t\001\006\005\002\013S%\021!f\003\002\b\005>|G.Z1o\021!a\003A!E!\002\023A\023AB1ts:\034\007\005C\003/\001\021\005q&\001\004=S:LGO\020\013\004aE\022\004C\001\t\001\021\025QR\0061\001\036\021\0251S\0061\001)\021\035!\004!!A\005\002U\nAaY8qsR\031\001GN\034\t\017i\031\004\023!a\001;!9ae\rI\001\002\004A\003bB\035\001#\003%\tAO\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\005Y$FA\017=W\005i\004C\001 D\033\005y$B\001!B\003%)hn\0315fG.,GM\003\002C\027\005Q\021M\0348pi\006$\030n\0348\n\005\021{$!E;oG\",7m[3e-\006\024\030.\0318dK\"9a\tAI\001\n\0039\025AD2paf$C-\0324bk2$HEM\013\002\021*\022\001\006\020\005\b\025\002\t\t\021\"\021L\0035\001(o\0343vGR\004&/\0324jqV\tA\n\005\002N%6\taJ\003\002P!\006!A.\0318h\025\005\t\026\001\0026bm\006L!a\025(\003\rM#(/\0338h\021\035)\006!!A\005\002Y\013A\002\035:pIV\034G/\021:jif,\022a\026\t\003\025aK!!W\006\003\007%sG\017C\004\\\001\005\005I\021\001/\002\035A\024x\016Z;di\026cW-\\3oiR\021Q\f\031\t\003\025yK!aX\006\003\007\005s\027\020C\004b5\006\005\t\031A,\002\007a$\023\007C\004d\001\005\005I\021\t3\002\037A\024x\016Z;di&#XM]1u_J,\022!\032\t\004M&lV\"A4\013\005!\\\021AC2pY2,7\r^5p]&\021!n\032\002\t\023R,'/\031;pe\"9A\016AA\001\n\003i\027\001C2b]\026\013X/\0317\025\005!r\007bB1l\003\003\005\r!\030\005\ba\002\t\t\021\"\021r\003!A\027m\0355D_\022,G#A,\t\017M\004\021\021!C!i\006AAo\\*ue&tw\rF\001M\021\0351\b!!A\005B]\fa!Z9vC2\034HC\001\025y\021\035\tW/!AA\002uC3\001\001>~!\tQ10\003\002}\027\t\0012+\032:jC24VM]:j_:,\026\n\022\020\002\003\035IqPAA\001\022\0031\021\021A\001\n'V\004XM\035<jg\026\0042\001EA\002\r%\t!!!A\t\002\031\t)aE\003\002\004\005\035a\003E\004\002\n\005=Q\004\013\031\016\005\005-!bAA\007\027\0059!/\0368uS6,\027\002BA\t\003\027\021\021#\0212tiJ\f7\r\036$v]\016$\030n\03483\021\035q\0231\001C\001\003+!\"!!\001\t\021M\f\031!!A\005FQD!\"a\007\002\004\005\005I\021QA\017\003\025\t\007\017\0357z)\025\001\024qDA\021\021\031Q\022\021\004a\001;!1a%!\007A\002!B!\"!\n\002\004\005\005I\021QA\024\003\035)h.\0319qYf$B!!\013\0026A)!\"a\013\0020%\031\021QF\006\003\r=\003H/[8o!\025Q\021\021G\017)\023\r\t\031d\003\002\007)V\004H.\032\032\t\023\005]\0221EA\001\002\004\001\024a\001=%a!Q\0211HA\002\003\003%I!!\020\002\027I,\027\r\032*fg>dg/\032\013\003\003\0012!TA!\023\r\t\031E\024\002\007\037\nTWm\031;")
/*     */ public class Supervise implements SystemMessage, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final ActorRef child;
/*     */   
/*     */   private final boolean async;
/*     */   
/*     */   private transient SystemMessage next;
/*     */   
/*     */   public static Function1<Tuple2<ActorRef, Object>, Supervise> tupled() {
/*     */     return Supervise$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<ActorRef, Function1<Object, Supervise>> curried() {
/*     */     return Supervise$.MODULE$.curried();
/*     */   }
/*     */   
/*     */   public SystemMessage next() {
/* 235 */     return this.next;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void next_$eq(SystemMessage x$1) {
/* 235 */     this.next = x$1;
/*     */   }
/*     */   
/*     */   public void unlink() {
/* 235 */     SystemMessage$class.unlink(this);
/*     */   }
/*     */   
/*     */   public boolean unlinked() {
/* 235 */     return SystemMessage$class.unlinked(this);
/*     */   }
/*     */   
/*     */   public ActorRef child() {
/* 235 */     return this.child;
/*     */   }
/*     */   
/*     */   public boolean async() {
/* 235 */     return this.async;
/*     */   }
/*     */   
/*     */   public Supervise copy(ActorRef child, boolean async) {
/* 235 */     return new Supervise(child, async);
/*     */   }
/*     */   
/*     */   public ActorRef copy$default$1() {
/* 235 */     return child();
/*     */   }
/*     */   
/*     */   public boolean copy$default$2() {
/* 235 */     return async();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 235 */     return "Supervise";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 235 */     return 2;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 235 */     int i = x$1;
/* 235 */     switch (i) {
/*     */       default:
/* 235 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 235 */     return child();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 235 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 235 */     return x$1 instanceof Supervise;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 235 */     int i = -889275714;
/* 235 */     i = Statics.mix(i, Statics.anyHash(child()));
/* 235 */     i = Statics.mix(i, async() ? 1231 : 1237);
/* 235 */     return Statics.finalizeHash(i, 2);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 235 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 92
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/dispatch/sysmsg/Supervise
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 96
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/dispatch/sysmsg/Supervise
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual child : ()Lakka/actor/ActorRef;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual child : ()Lakka/actor/ActorRef;
/*     */     //   40: astore #5
/*     */     //   42: dup
/*     */     //   43: ifnonnull -> 55
/*     */     //   46: pop
/*     */     //   47: aload #5
/*     */     //   49: ifnull -> 63
/*     */     //   52: goto -> 88
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 88
/*     */     //   63: aload_0
/*     */     //   64: invokevirtual async : ()Z
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual async : ()Z
/*     */     //   72: if_icmpne -> 88
/*     */     //   75: aload #4
/*     */     //   77: aload_0
/*     */     //   78: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   81: ifeq -> 88
/*     */     //   84: iconst_1
/*     */     //   85: goto -> 89
/*     */     //   88: iconst_0
/*     */     //   89: ifeq -> 96
/*     */     //   92: iconst_1
/*     */     //   93: goto -> 97
/*     */     //   96: iconst_0
/*     */     //   97: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #235	-> 0
/*     */     //   #63	-> 14
/*     */     //   #235	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	98	0	this	Lakka/dispatch/sysmsg/Supervise;
/*     */     //   0	98	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Supervise(ActorRef child, boolean async) {
/* 235 */     SystemMessage$class.$init$(this);
/* 235 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Supervise.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */