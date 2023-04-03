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
/*     */ @ScalaSignature(bytes = "\006\001\005}d!B\001\003\001\032A!A\002$bS2,GM\003\002\004\t\00511/_:ng\036T!!\002\004\002\021\021L7\017]1uG\"T\021aB\001\005C.\\\027mE\004\001\023=\031b#\007\017\021\005)iQ\"A\006\013\0031\tQa]2bY\006L!AD\006\003\r\005s\027PU3g!\t\001\022#D\001\003\023\t\021\"AA\007TsN$X-\\'fgN\fw-\032\t\003!QI!!\006\002\003\037M#\030m\0355XQ\026tg)Y5mK\022\004\"\001E\f\n\005a\021!aG*uCNDw\013[3o/\006LG/\0338h\r>\0248\t[5mIJ,g\016\005\002\0135%\0211d\003\002\b!J|G-^2u!\tQQ$\003\002\037\027\ta1+\032:jC2L'0\0312mK\"A\001\005\001BK\002\023\005!%A\003dQ&dGm\001\001\026\003\r\002\"\001J\024\016\003\025R!A\n\004\002\013\005\034Go\034:\n\005!*#\001C!di>\024(+\0324\t\021)\002!\021#Q\001\n\r\naa\0315jY\022\004\003\002\003\027\001\005+\007I\021A\027\002\013\r\fWo]3\026\0039\002\"aL\034\017\005A*dBA\0315\033\005\021$BA\032\"\003\031a$o\\8u}%\tA\"\003\0027\027\0059\001/Y2lC\036,\027B\001\035:\005%!\006N]8xC\ndWM\003\0027\027!A1\b\001B\tB\003%a&\001\004dCV\034X\r\t\005\t{\001\021)\032!C\001}\005\031Q/\0333\026\003}\002\"A\003!\n\005\005[!aA%oi\"A1\t\001B\tB\003%q(\001\003vS\022\004\003\"B#\001\t\0031\025A\002\037j]&$h\b\006\003H\021&S\005C\001\t\001\021\025\001C\t1\001$\021\025aC\t1\001/\021\025iD\t1\001@\021\035a\005!!A\005\0025\013AaY8qsR!qIT(Q\021\035\0013\n%AA\002\rBq\001L&\021\002\003\007a\006C\004>\027B\005\t\031A \t\017I\003\021\023!C\001'\006q1m\0349zI\021,g-Y;mi\022\nT#\001++\005\r*6&\001,\021\005]cV\"\001-\013\005eS\026!C;oG\",7m[3e\025\tY6\"\001\006b]:|G/\031;j_:L!!\030-\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\004`\001E\005I\021\0011\002\035\r|\007/\037\023eK\032\fW\017\034;%eU\t\021M\013\002/+\"91\rAI\001\n\003!\027AD2paf$C-\0324bk2$HeM\013\002K*\022q(\026\005\bO\002\t\t\021\"\021i\0035\001(o\0343vGR\004&/\0324jqV\t\021\016\005\002k_6\t1N\003\002m[\006!A.\0318h\025\005q\027\001\0026bm\006L!\001]6\003\rM#(/\0338h\021\035\021\b!!A\005\002y\nA\002\035:pIV\034G/\021:jifDq\001\036\001\002\002\023\005Q/\001\bqe>$Wo\031;FY\026lWM\034;\025\005YL\bC\001\006x\023\tA8BA\002B]fDqA_:\002\002\003\007q(A\002yIEBq\001 \001\002\002\023\005S0A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005q\b\003B@\002\006Yl!!!\001\013\007\005\r1\"\001\006d_2dWm\031;j_:LA!a\002\002\002\tA\021\n^3sCR|'\017C\005\002\f\001\t\t\021\"\001\002\016\005A1-\0318FcV\fG\016\006\003\002\020\005U\001c\001\006\002\022%\031\0211C\006\003\017\t{w\016\\3b]\"A!0!\003\002\002\003\007a\017C\005\002\032\001\t\t\021\"\021\002\034\005A\001.Y:i\007>$W\rF\001@\021%\ty\002AA\001\n\003\n\t#\001\005u_N#(/\0338h)\005I\007\"CA\023\001\005\005I\021IA\024\003\031)\027/^1mgR!\021qBA\025\021!Q\0301EA\001\002\0041\b&\002\001\002.\005M\002c\001\006\0020%\031\021\021G\006\003!M+'/[1m-\026\0248/[8o+&#e$A\001\b\025\005]\"!!A\t\002\031\tI$\001\004GC&dW\r\032\t\004!\005mb!C\001\003\003\003E\tABA\037'\025\tY$a\020\035!!\t\t%a\022$]}:UBAA\"\025\r\t)eC\001\beVtG/[7f\023\021\tI%a\021\003#\005\0237\017\036:bGR4UO\\2uS>t7\007C\004F\003w!\t!!\024\025\005\005e\002BCA\020\003w\t\t\021\"\022\002\"!Q\0211KA\036\003\003%\t)!\026\002\013\005\004\b\017\\=\025\017\035\0139&!\027\002\\!1\001%!\025A\002\rBa\001LA)\001\004q\003BB\037\002R\001\007q\b\003\006\002`\005m\022\021!CA\003C\nq!\0368baBd\027\020\006\003\002d\005=\004#\002\006\002f\005%\024bAA4\027\t1q\n\035;j_:\004bACA6G9z\024bAA7\027\t1A+\0369mKNB\021\"!\035\002^\005\005\t\031A$\002\007a$\003\007\003\006\002v\005m\022\021!C\005\003o\n1B]3bIJ+7o\0347wKR\021\021\021\020\t\004U\006m\024bAA?W\n1qJ\0316fGR\004")
/*     */ public class Failed implements SystemMessage, StashWhenFailed, StashWhenWaitingForChildren, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final ActorRef child;
/*     */   
/*     */   private final Throwable cause;
/*     */   
/*     */   private final int uid;
/*     */   
/*     */   private transient SystemMessage next;
/*     */   
/*     */   public static Function1<Tuple3<ActorRef, Throwable, Object>, Failed> tupled() {
/*     */     return Failed$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<ActorRef, Function1<Throwable, Function1<Object, Failed>>> curried() {
/*     */     return Failed$.MODULE$.curried();
/*     */   }
/*     */   
/*     */   public SystemMessage next() {
/* 256 */     return this.next;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void next_$eq(SystemMessage x$1) {
/* 256 */     this.next = x$1;
/*     */   }
/*     */   
/*     */   public void unlink() {
/* 256 */     SystemMessage$class.unlink(this);
/*     */   }
/*     */   
/*     */   public boolean unlinked() {
/* 256 */     return SystemMessage$class.unlinked(this);
/*     */   }
/*     */   
/*     */   public ActorRef child() {
/* 256 */     return this.child;
/*     */   }
/*     */   
/*     */   public Throwable cause() {
/* 256 */     return this.cause;
/*     */   }
/*     */   
/*     */   public int uid() {
/* 256 */     return this.uid;
/*     */   }
/*     */   
/*     */   public Failed copy(ActorRef child, Throwable cause, int uid) {
/* 256 */     return new Failed(child, cause, uid);
/*     */   }
/*     */   
/*     */   public ActorRef copy$default$1() {
/* 256 */     return child();
/*     */   }
/*     */   
/*     */   public Throwable copy$default$2() {
/* 256 */     return cause();
/*     */   }
/*     */   
/*     */   public int copy$default$3() {
/* 256 */     return uid();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 256 */     return "Failed";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 256 */     return 3;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 256 */     int i = x$1;
/* 256 */     switch (i) {
/*     */       default:
/* 256 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 256 */     return child();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 256 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 256 */     return x$1 instanceof Failed;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 256 */     int i = -889275714;
/* 256 */     i = Statics.mix(i, Statics.anyHash(child()));
/* 256 */     i = Statics.mix(i, Statics.anyHash(cause()));
/* 256 */     i = Statics.mix(i, uid());
/* 256 */     return Statics.finalizeHash(i, 3);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 256 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 124
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/dispatch/sysmsg/Failed
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 128
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/dispatch/sysmsg/Failed
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
/*     */     //   52: goto -> 120
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 120
/*     */     //   63: aload_0
/*     */     //   64: invokevirtual cause : ()Ljava/lang/Throwable;
/*     */     //   67: aload #4
/*     */     //   69: invokevirtual cause : ()Ljava/lang/Throwable;
/*     */     //   72: astore #6
/*     */     //   74: dup
/*     */     //   75: ifnonnull -> 87
/*     */     //   78: pop
/*     */     //   79: aload #6
/*     */     //   81: ifnull -> 95
/*     */     //   84: goto -> 120
/*     */     //   87: aload #6
/*     */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   92: ifeq -> 120
/*     */     //   95: aload_0
/*     */     //   96: invokevirtual uid : ()I
/*     */     //   99: aload #4
/*     */     //   101: invokevirtual uid : ()I
/*     */     //   104: if_icmpne -> 120
/*     */     //   107: aload #4
/*     */     //   109: aload_0
/*     */     //   110: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   113: ifeq -> 120
/*     */     //   116: iconst_1
/*     */     //   117: goto -> 121
/*     */     //   120: iconst_0
/*     */     //   121: ifeq -> 128
/*     */     //   124: iconst_1
/*     */     //   125: goto -> 129
/*     */     //   128: iconst_0
/*     */     //   129: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #256	-> 0
/*     */     //   #63	-> 14
/*     */     //   #256	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	130	0	this	Lakka/dispatch/sysmsg/Failed;
/*     */     //   0	130	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Failed(ActorRef child, Throwable cause, int uid) {
/* 256 */     SystemMessage$class.$init$(this);
/* 256 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Failed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */