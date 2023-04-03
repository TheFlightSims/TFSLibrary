/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Tuple4;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005Ud\001B\001\003\001\036\0211\003\025:f%\026\034H/\031:u\013b\034W\r\035;j_:T!a\001\003\002\013\005\034Go\034:\013\003\025\tA!Y6lC\016\0011\003\002\001\t\031I\001\"!\003\006\016\003\tI!a\003\002\0039\005\033Go\034:J]&$\030.\0317ju\006$\030n\0348Fq\016,\007\017^5p]B\021Q\002E\007\002\035)\tq\"A\003tG\006d\027-\003\002\022\035\t9\001K]8ek\016$\bCA\007\024\023\t!bB\001\007TKJL\027\r\\5{C\ndW\r\003\005\004\001\tU\r\021\"\001\027+\0059\002CA\005\031\023\tI\"A\001\005BGR|'OU3g\021!Y\002A!E!\002\0239\022AB1di>\024\b\005\003\005\036\001\tU\r\021\"\001\037\003\025\031\027-^:f+\005y\002C\001\021)\035\t\tcE\004\002#K5\t1E\003\002%\r\0051AH]8pizJ\021aD\005\003O9\tq\001]1dW\006<W-\003\002*U\tIA\013\033:po\006\024G.\032\006\003O9A\001\002\f\001\003\022\003\006IaH\001\007G\006,8/\032\021\t\0219\002!Q3A\005\002y\tQb\034:jO&t\027\r\\\"bkN,\007\002\003\031\001\005#\005\013\021B\020\002\035=\024\030nZ5oC2\034\025-^:fA!A!\007\001BK\002\023\0051'A\007nKN\034\030mZ3PaRLwN\\\013\002iA\031Q\"N\034\n\005Yr!AB(qi&|g\016\005\002\016q%\021\021H\004\002\004\003:L\b\002C\036\001\005#\005\013\021\002\033\002\0355,7o]1hK>\003H/[8oA!1Q\b\001C\001\ty\na\001P5oSRtD#B A\003\n\033\005CA\005\001\021\025\031A\b1\001\030\021\025iB\b1\001 \021\025qC\b1\001 \021\025\021D\b1\0015\021\035)\005!!A\005\002\031\013AaY8qsR)qh\022%J\025\"91\001\022I\001\002\0049\002bB\017E!\003\005\ra\b\005\b]\021\003\n\0211\001 \021\035\021D\t%AA\002QBq\001\024\001\022\002\023\005Q*\001\bd_BLH\005Z3gCVdG\017J\031\026\0039S#aF(,\003A\003\"!\025,\016\003IS!a\025+\002\023Ut7\r[3dW\026$'BA+\017\003)\tgN\\8uCRLwN\\\005\003/J\023\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021\035I\006!%A\005\002i\013abY8qs\022\"WMZ1vYR$#'F\001\\U\tyr\nC\004^\001E\005I\021\001.\002\035\r|\007/\037\023eK\032\fW\017\034;%g!9q\fAI\001\n\003\001\027AD2paf$C-\0324bk2$H\005N\013\002C*\022Ag\024\005\bG\002\t\t\021\"\021e\0035\001(o\0343vGR\004&/\0324jqV\tQ\r\005\002gW6\tqM\003\002iS\006!A.\0318h\025\005Q\027\001\0026bm\006L!\001\\4\003\rM#(/\0338h\021\035q\007!!A\005\002=\fA\002\035:pIV\034G/\021:jif,\022\001\035\t\003\033EL!A\035\b\003\007%sG\017C\004u\001\005\005I\021A;\002\035A\024x\016Z;di\026cW-\\3oiR\021qG\036\005\boN\f\t\0211\001q\003\rAH%\r\005\bs\002\t\t\021\"\021{\003=\001(o\0343vGRLE/\032:bi>\024X#A>\021\007q|x'D\001~\025\tqh\"\001\006d_2dWm\031;j_:L1!!\001~\005!IE/\032:bi>\024\b\"CA\003\001\005\005I\021AA\004\003!\031\027M\\#rk\006dG\003BA\005\003\037\0012!DA\006\023\r\tiA\004\002\b\005>|G.Z1o\021!9\0301AA\001\002\0049\004\"CA\n\001\005\005I\021IA\013\003!A\027m\0355D_\022,G#\0019\t\023\005e\001!!A\005B\005m\021AB3rk\006d7\017\006\003\002\n\005u\001\002C<\002\030\005\005\t\031A\034)\013\001\t\t#a\n\021\0075\t\031#C\002\002&9\021\001cU3sS\006dg+\032:tS>tW+\023#\037\003\0059\021\"a\013\003\003\003E\t!!\f\002'A\023XMU3ti\006\024H/\022=dKB$\030n\0348\021\007%\tyC\002\005\002\005\005\005\t\022AA\031'\025\ty#a\r\023!%\t)$a\017\030?}!t(\004\002\0028)\031\021\021\b\b\002\017I,h\016^5nK&!\021QHA\034\005E\t%m\035;sC\016$h)\0368di&|g\016\016\005\b{\005=B\021AA!)\t\ti\003\003\006\002F\005=\022\021!C#\003\017\n\001\002^8TiJLgn\032\013\002K\"Q\0211JA\030\003\003%\t)!\024\002\013\005\004\b\017\\=\025\023}\ny%!\025\002T\005U\003BB\002\002J\001\007q\003\003\004\036\003\023\002\ra\b\005\007]\005%\003\031A\020\t\rI\nI\0051\0015\021)\tI&a\f\002\002\023\005\0251L\001\bk:\f\007\017\0357z)\021\ti&!\032\021\t5)\024q\f\t\b\033\005\005tcH\0205\023\r\t\031G\004\002\007)V\004H.\032\033\t\023\005\035\024qKA\001\002\004y\024a\001=%a!Q\0211NA\030\003\003%I!!\034\002\027I,\027\r\032*fg>dg/\032\013\003\003_\0022AZA9\023\r\t\031h\032\002\007\037\nTWm\031;")
/*     */ public class PreRestartException extends ActorInitializationException implements Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final ActorRef actor;
/*     */   
/*     */   private final Throwable cause;
/*     */   
/*     */   private final Throwable originalCause;
/*     */   
/*     */   private final Option<Object> messageOption;
/*     */   
/*     */   public static Function1<Tuple4<ActorRef, Throwable, Throwable, Option<Object>>, PreRestartException> tupled() {
/*     */     return PreRestartException$.MODULE$.tupled();
/*     */   }
/*     */   
/*     */   public static Function1<ActorRef, Function1<Throwable, Function1<Throwable, Function1<Option<Object>, PreRestartException>>>> curried() {
/*     */     return PreRestartException$.MODULE$.curried();
/*     */   }
/*     */   
/*     */   public ActorRef actor() {
/* 181 */     return this.actor;
/*     */   }
/*     */   
/*     */   public Throwable cause() {
/* 181 */     return this.cause;
/*     */   }
/*     */   
/*     */   public Throwable originalCause() {
/* 181 */     return this.originalCause;
/*     */   }
/*     */   
/*     */   public Option<Object> messageOption() {
/* 181 */     return this.messageOption;
/*     */   }
/*     */   
/*     */   public PreRestartException copy(ActorRef actor, Throwable cause, Throwable originalCause, Option<Object> messageOption) {
/* 181 */     return new PreRestartException(actor, cause, originalCause, messageOption);
/*     */   }
/*     */   
/*     */   public ActorRef copy$default$1() {
/* 181 */     return actor();
/*     */   }
/*     */   
/*     */   public Throwable copy$default$2() {
/* 181 */     return cause();
/*     */   }
/*     */   
/*     */   public Throwable copy$default$3() {
/* 181 */     return originalCause();
/*     */   }
/*     */   
/*     */   public Option<Object> copy$default$4() {
/* 181 */     return messageOption();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 181 */     return "PreRestartException";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 181 */     return 4;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 181 */     int i = x$1;
/* 181 */     switch (i) {
/*     */       default:
/* 181 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 3:
/*     */       
/*     */       case 2:
/*     */       
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 181 */     return actor();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 181 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 181 */     return x$1 instanceof PreRestartException;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 181 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 176
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/actor/PreRestartException
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 180
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/PreRestartException
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
/*     */     //   52: goto -> 172
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 172
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
/*     */     //   84: goto -> 172
/*     */     //   87: aload #6
/*     */     //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   92: ifeq -> 172
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
/*     */     //   116: goto -> 172
/*     */     //   119: aload #7
/*     */     //   121: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   124: ifeq -> 172
/*     */     //   127: aload_0
/*     */     //   128: invokevirtual messageOption : ()Lscala/Option;
/*     */     //   131: aload #4
/*     */     //   133: invokevirtual messageOption : ()Lscala/Option;
/*     */     //   136: astore #8
/*     */     //   138: dup
/*     */     //   139: ifnonnull -> 151
/*     */     //   142: pop
/*     */     //   143: aload #8
/*     */     //   145: ifnull -> 159
/*     */     //   148: goto -> 172
/*     */     //   151: aload #8
/*     */     //   153: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   156: ifeq -> 172
/*     */     //   159: aload #4
/*     */     //   161: aload_0
/*     */     //   162: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   165: ifeq -> 172
/*     */     //   168: iconst_1
/*     */     //   169: goto -> 173
/*     */     //   172: iconst_0
/*     */     //   173: ifeq -> 180
/*     */     //   176: iconst_1
/*     */     //   177: goto -> 181
/*     */     //   180: iconst_0
/*     */     //   181: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #181	-> 0
/*     */     //   #63	-> 14
/*     */     //   #181	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	182	0	this	Lakka/actor/PreRestartException;
/*     */     //   0	182	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public PreRestartException(ActorRef actor, Throwable cause, Throwable originalCause, Option messageOption) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: putfield actor : Lakka/actor/ActorRef;
/*     */     //   5: aload_0
/*     */     //   6: aload_2
/*     */     //   7: putfield cause : Ljava/lang/Throwable;
/*     */     //   10: aload_0
/*     */     //   11: aload_3
/*     */     //   12: putfield originalCause : Ljava/lang/Throwable;
/*     */     //   15: aload_0
/*     */     //   16: aload #4
/*     */     //   18: putfield messageOption : Lscala/Option;
/*     */     //   21: aload_0
/*     */     //   22: aload_1
/*     */     //   23: new scala/collection/mutable/StringBuilder
/*     */     //   26: dup
/*     */     //   27: invokespecial <init> : ()V
/*     */     //   30: ldc 'exception in preRestart('
/*     */     //   32: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   35: aload_3
/*     */     //   36: ifnonnull -> 44
/*     */     //   39: ldc 'null'
/*     */     //   41: goto -> 48
/*     */     //   44: aload_3
/*     */     //   45: invokevirtual getClass : ()Ljava/lang/Class;
/*     */     //   48: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   51: ldc ', '
/*     */     //   53: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   56: aload #4
/*     */     //   58: astore #5
/*     */     //   60: aload #5
/*     */     //   62: instanceof scala/Some
/*     */     //   65: ifeq -> 104
/*     */     //   68: aload #5
/*     */     //   70: checkcast scala/Some
/*     */     //   73: astore #6
/*     */     //   75: aload #6
/*     */     //   77: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   80: astore #7
/*     */     //   82: aload #7
/*     */     //   84: instanceof java/lang/Object
/*     */     //   87: ifeq -> 104
/*     */     //   90: aload #7
/*     */     //   92: astore #8
/*     */     //   94: aload #8
/*     */     //   96: invokevirtual getClass : ()Ljava/lang/Class;
/*     */     //   99: astore #9
/*     */     //   101: goto -> 108
/*     */     //   104: ldc 'None'
/*     */     //   106: astore #9
/*     */     //   108: aload #9
/*     */     //   110: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   113: ldc ')'
/*     */     //   115: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   118: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   121: aload_2
/*     */     //   122: invokespecial <init> : (Lakka/actor/ActorRef;Ljava/lang/String;Ljava/lang/Throwable;)V
/*     */     //   125: aload_0
/*     */     //   126: invokestatic $init$ : (Lscala/Product;)V
/*     */     //   129: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #181	-> 0
/*     */     //   #182	-> 22
/*     */     //   #185	-> 23
/*     */     //   #183	-> 30
/*     */     //   #184	-> 35
/*     */     //   #185	-> 56
/*     */     //   #186	-> 113
/*     */     //   #185	-> 118
/*     */     //   #186	-> 121
/*     */     //   #181	-> 122
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	130	0	this	Lakka/actor/PreRestartException;
/*     */     //   0	130	1	actor	Lakka/actor/ActorRef;
/*     */     //   0	130	2	cause	Ljava/lang/Throwable;
/*     */     //   0	130	3	originalCause	Ljava/lang/Throwable;
/*     */     //   0	130	4	messageOption	Lscala/Option;
/*     */     //   82	48	7	m	Ljava/lang/Object;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\PreRestartException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */