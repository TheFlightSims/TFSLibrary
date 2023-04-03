/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005ua\001B\001\003\001\036\021\021\"\0213e%>,H/Z3\013\005\r!\021a\002:pkRLgn\032\006\002\013\005!\021m[6b\007\001\031R\001\001\005\017%U\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007CA\b\021\033\005\021\021BA\t\003\005a\021v.\036;fe6\013g.Y4f[\026tG/T3tgN\fw-\032\t\003\023MI!\001\006\006\003\017A\023x\016Z;diB\021\021BF\005\003/)\021AbU3sS\006d\027N_1cY\026D\001\"\007\001\003\026\004%\tAG\001\007e>,H/Z3\026\003m\001\"a\004\017\n\005u\021!A\002*pkR,W\r\003\005 \001\tE\t\025!\003\034\003\035\021x.\036;fK\002BQ!\t\001\005\002\t\na\001P5oSRtDCA\022%!\ty\001\001C\003\032A\001\0071\004C\004'\001\005\005I\021A\024\002\t\r|\007/\037\013\003G!Bq!G\023\021\002\003\0071\004C\004+\001E\005I\021A\026\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\tAF\013\002\034[-\na\006\005\0020i5\t\001G\003\0022e\005IQO\\2iK\016\\W\r\032\006\003g)\t!\"\0318o_R\fG/[8o\023\t)\004GA\tv]\016DWmY6fIZ\013'/[1oG\026Dqa\016\001\002\002\023\005\003(A\007qe>$Wo\031;Qe\0264\027\016_\013\002sA\021!hP\007\002w)\021A(P\001\005Y\006twMC\001?\003\021Q\027M^1\n\005\001[$AB*ue&tw\rC\004C\001\005\005I\021A\"\002\031A\024x\016Z;di\006\023\030\016^=\026\003\021\003\"!C#\n\005\031S!aA%oi\"9\001\nAA\001\n\003I\025A\0049s_\022,8\r^#mK6,g\016\036\013\003\0256\003\"!C&\n\0051S!aA!os\"9ajRA\001\002\004!\025a\001=%c!9\001\013AA\001\n\003\n\026a\0049s_\022,8\r^%uKJ\fGo\034:\026\003I\0032a\025,K\033\005!&BA+\013\003)\031w\016\0347fGRLwN\\\005\003/R\023\001\"\023;fe\006$xN\035\005\b3\002\t\t\021\"\001[\003!\031\027M\\#rk\006dGCA._!\tIA,\003\002^\025\t9!i\\8mK\006t\007b\002(Y\003\003\005\rA\023\005\bA\002\t\t\021\"\021b\003!A\027m\0355D_\022,G#\001#\t\017\r\004\021\021!C!I\006AAo\\*ue&tw\rF\001:\021\0351\007!!A\005B\035\fa!Z9vC2\034HCA.i\021\035qU-!AA\002)C3\001\0016n!\tI1.\003\002m\025\t\0012+\032:jC24VM]:j_:,\026\n\022\020\002\003\0359qNAA\001\022\003\001\030!C!eIJ{W\017^3f!\ty\021OB\004\002\005\005\005\t\022\001:\024\007E\034X\003\005\003uon\031S\"A;\013\005YT\021a\002:v]RLW.Z\005\003qV\024\021#\0212tiJ\f7\r\036$v]\016$\030n\03482\021\025\t\023\017\"\001{)\005\001\bbB2r\003\003%)\005\032\005\b{F\f\t\021\"!\003\025\t\007\017\0357z)\t\031s\020C\003\032y\002\0071\004C\005\002\004E\f\t\021\"!\002\006\0059QO\\1qa2LH\003BA\004\003\033\001B!CA\0057%\031\0211\002\006\003\r=\003H/[8o\021%\ty!!\001\002\002\003\0071%A\002yIAB\021\"a\005r\003\003%I!!\006\002\027I,\027\r\032*fg>dg/\032\013\003\003/\0012AOA\r\023\r\tYb\017\002\007\037\nTWm\031;")
/*     */ public class AddRoutee implements RouterManagementMesssage, Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Routee routee;
/*     */   
/*     */   public static <A> Function1<Routee, A> andThen(Function1<AddRoutee, A> paramFunction1) {
/*     */     return AddRoutee$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, AddRoutee> compose(Function1<A, Routee> paramFunction1) {
/*     */     return AddRoutee$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public Routee routee() {
/* 394 */     return this.routee;
/*     */   }
/*     */   
/*     */   public AddRoutee copy(Routee routee) {
/* 394 */     return new AddRoutee(routee);
/*     */   }
/*     */   
/*     */   public Routee copy$default$1() {
/* 394 */     return routee();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 394 */     return "AddRoutee";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 394 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 394 */     int i = x$1;
/* 394 */     switch (i) {
/*     */       default:
/* 394 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 394 */     return routee();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 394 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 394 */     return x$1 instanceof AddRoutee;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 394 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 394 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: if_acmpeq -> 80
/*     */     //   5: aload_1
/*     */     //   6: astore_2
/*     */     //   7: aload_2
/*     */     //   8: instanceof akka/routing/AddRoutee
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/AddRoutee
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual routee : ()Lakka/routing/Routee;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual routee : ()Lakka/routing/Routee;
/*     */     //   40: astore #5
/*     */     //   42: dup
/*     */     //   43: ifnonnull -> 55
/*     */     //   46: pop
/*     */     //   47: aload #5
/*     */     //   49: ifnull -> 63
/*     */     //   52: goto -> 76
/*     */     //   55: aload #5
/*     */     //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   60: ifeq -> 76
/*     */     //   63: aload #4
/*     */     //   65: aload_0
/*     */     //   66: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */     //   69: ifeq -> 76
/*     */     //   72: iconst_1
/*     */     //   73: goto -> 77
/*     */     //   76: iconst_0
/*     */     //   77: ifeq -> 84
/*     */     //   80: iconst_1
/*     */     //   81: goto -> 85
/*     */     //   84: iconst_0
/*     */     //   85: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #394	-> 0
/*     */     //   #63	-> 14
/*     */     //   #394	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/routing/AddRoutee;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public AddRoutee(Routee routee) {
/* 394 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\AddRoutee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */