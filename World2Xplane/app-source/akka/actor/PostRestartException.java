/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005mc\001B\001\003\001\036\021A\003U8tiJ+7\017^1si\026C8-\0329uS>t'BA\002\005\003\025\t7\r^8s\025\005)\021\001B1lW\006\034\001a\005\003\001\0211\021\002CA\005\013\033\005\021\021BA\006\003\005q\t5\r^8s\023:LG/[1mSj\fG/[8o\013b\034W\r\035;j_:\004\"!\004\t\016\0039Q\021aD\001\006g\016\fG.Y\005\003#9\021q\001\025:pIV\034G\017\005\002\016'%\021AC\004\002\r'\026\024\030.\0317ju\006\024G.\032\005\t\007\001\021)\032!C\001-U\tq\003\005\002\n1%\021\021D\001\002\t\003\016$xN\035*fM\"A1\004\001B\tB\003%q#\001\004bGR|'\017\t\005\t;\001\021)\032!C\001=\005)1-Y;tKV\tq\004\005\002!Q9\021\021E\n\b\003E\025j\021a\t\006\003I\031\ta\001\020:p_Rt\024\"A\b\n\005\035r\021a\0029bG.\fw-Z\005\003S)\022\021\002\0265s_^\f'\r\\3\013\005\035r\001\002\003\027\001\005#\005\013\021B\020\002\r\r\fWo]3!\021!q\003A!f\001\n\003q\022!D8sS\036Lg.\0317DCV\034X\r\003\0051\001\tE\t\025!\003 \0039y'/[4j]\006d7)Y;tK\002BaA\r\001\005\002\021\031\024A\002\037j]&$h\b\006\0035kY:\004CA\005\001\021\025\031\021\0071\001\030\021\025i\022\0071\001 \021\025q\023\0071\001 \021\035I\004!!A\005\002i\nAaY8qsR!Ag\017\037>\021\035\031\001\b%AA\002]Aq!\b\035\021\002\003\007q\004C\004/qA\005\t\031A\020\t\017}\002\021\023!C\001\001\006q1m\0349zI\021,g-Y;mi\022\nT#A!+\005]\0215&A\"\021\005\021KU\"A#\013\005\031;\025!C;oG\",7m[3e\025\tAe\"\001\006b]:|G/\031;j_:L!AS#\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\004M\001E\005I\021A'\002\035\r|\007/\037\023eK\032\fW\017\034;%eU\taJ\013\002 \005\"9\001\013AI\001\n\003i\025AD2paf$C-\0324bk2$He\r\005\b%\002\t\t\021\"\021T\0035\001(o\0343vGR\004&/\0324jqV\tA\013\005\002V56\taK\003\002X1\006!A.\0318h\025\005I\026\001\0026bm\006L!a\027,\003\rM#(/\0338h\021\035i\006!!A\005\002y\013A\002\035:pIV\034G/\021:jif,\022a\030\t\003\033\001L!!\031\b\003\007%sG\017C\004d\001\005\005I\021\0013\002\035A\024x\016Z;di\026cW-\\3oiR\021Q\r\033\t\003\033\031L!a\032\b\003\007\005s\027\020C\004jE\006\005\t\031A0\002\007a$\023\007C\004l\001\005\005I\021\t7\002\037A\024x\016Z;di&#XM]1u_J,\022!\034\t\004]F,W\"A8\013\005At\021AC2pY2,7\r^5p]&\021!o\034\002\t\023R,'/\031;pe\"9A\017AA\001\n\003)\030\001C2b]\026\013X/\0317\025\005YL\bCA\007x\023\tAhBA\004C_>dW-\0318\t\017%\034\030\021!a\001K\"91\020AA\001\n\003b\030\001\0035bg\"\034u\016Z3\025\003}CqA \001\002\002\023\005s0\001\004fcV\fGn\035\013\004m\006\005\001bB5~\003\003\005\r!\032\025\006\001\005\025\0211\002\t\004\033\005\035\021bAA\005\035\t\0012+\032:jC24VM]:j_:,\026\n\022\020\002\003\035I\021q\002\002\002\002#\005\021\021C\001\025!>\034HOU3ti\006\024H/\022=dKB$\030n\0348\021\007%\t\031B\002\005\002\005\005\005\t\022AA\013'\025\t\031\"a\006\023!!\tI\"a\b\030?}!TBAA\016\025\r\tiBD\001\beVtG/[7f\023\021\t\t#a\007\003#\005\0237\017\036:bGR4UO\\2uS>t7\007C\0043\003'!\t!!\n\025\005\005E\001BCA\025\003'\t\t\021\"\022\002,\005AAo\\*ue&tw\rF\001U\021)\ty#a\005\002\002\023\005\025\021G\001\006CB\004H.\037\013\bi\005M\022QGA\034\021\031\031\021Q\006a\001/!1Q$!\fA\002}AaALA\027\001\004y\002BCA\036\003'\t\t\021\"!\002>\0059QO\\1qa2LH\003BA \003\027\002R!DA!\003\013J1!a\021\017\005\031y\005\017^5p]B1Q\"a\022\030?}I1!!\023\017\005\031!V\017\0357fg!I\021QJA\035\003\003\005\r\001N\001\004q\022\002\004BCA)\003'\t\t\021\"\003\002T\005Y!/Z1e%\026\034x\016\034<f)\t\t)\006E\002V\003/J1!!\027W\005\031y%M[3di\002")
/*     */ public class PostRestartException extends ActorInitializationException implements Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final ActorRef actor;
/*     */   
/*     */   private final Throwable cause;
/*     */   
/*     */   private final Throwable originalCause;
/*     */   
/*     */   public static Function1<Tuple3<ActorRef, Throwable, Throwable>, PostRestartException> tupled() {
/*     */     return PostRestartException$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<ActorRef, Function1<Throwable, Function1<Throwable, PostRestartException>>> curried() {
/*     */     return PostRestartException$.MODULE$.curried();
/*     */   }
/*     */   
/*     */   public ActorRef actor() {
/* 197 */     return this.actor;
/*     */   }
/*     */   
/*     */   public Throwable cause() {
/* 197 */     return this.cause;
/*     */   }
/*     */   
/*     */   public Throwable originalCause() {
/* 197 */     return this.originalCause;
/*     */   }
/*     */   
/*     */   public PostRestartException copy(ActorRef actor, Throwable cause, Throwable originalCause) {
/* 197 */     return new PostRestartException(actor, cause, originalCause);
/*     */   }
/*     */   
/*     */   public ActorRef copy$default$1() {
/* 197 */     return actor();
/*     */   }
/*     */   
/*     */   public Throwable copy$default$2() {
/* 197 */     return cause();
/*     */   }
/*     */   
/*     */   public Throwable copy$default$3() {
/* 197 */     return originalCause();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 197 */     return "PostRestartException";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 197 */     return 3;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 197 */     int i = x$1;
/* 197 */     switch (i) {
/*     */       default:
/* 197 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 197 */     return actor();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 197 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 197 */     return x$1 instanceof PostRestartException;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 197 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 144
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/actor/PostRestartException
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 148
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/PostRestartException
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
/*     */     //   52: goto -> 140
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 140
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
/*     */     //   84: goto -> 140
/*     */     //   87: aload #6
/*     */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   92: ifeq -> 140
/*     */     //   95: aload_0
/*     */     //   96: invokevirtual originalCause : ()Ljava/lang/Throwable;
/*     */     //   99: aload #4
/*     */     //   101: invokevirtual originalCause : ()Ljava/lang/Throwable;
/*     */     //   104: astore #7
/*     */     //   106: dup
/*     */     //   107: ifnonnull -> 119
/*     */     //   110: pop
/*     */     //   111: aload #7
/*     */     //   113: ifnull -> 127
/*     */     //   116: goto -> 140
/*     */     //   119: aload #7
/*     */     //   121: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   124: ifeq -> 140
/*     */     //   127: aload #4
/*     */     //   129: aload_0
/*     */     //   130: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   133: ifeq -> 140
/*     */     //   136: iconst_1
/*     */     //   137: goto -> 141
/*     */     //   140: iconst_0
/*     */     //   141: ifeq -> 148
/*     */     //   144: iconst_1
/*     */     //   145: goto -> 149
/*     */     //   148: iconst_0
/*     */     //   149: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #197	-> 0
/*     */     //   #63	-> 14
/*     */     //   #197	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	150	0	this	Lakka/actor/PostRestartException;
/*     */     //   0	150	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public PostRestartException(ActorRef actor, Throwable cause, Throwable originalCause) {
/* 197 */     super(
/* 198 */         actor, (
/* 199 */         new StringBuilder()).append("exception post restart (").append((originalCause == null) ? "null" : originalCause.getClass()).append(")").toString(), cause);
/*     */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\PostRestartException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */