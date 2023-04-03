/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\025b!B\001\003\001\0221!aD*fY\026\034Go\0215jY\022t\025-\\3\013\005\r!\021!B1di>\024(\"A\003\002\t\005\\7.Y\n\006\001\035i\021\003\006\t\003\021-i\021!\003\006\002\025\005)1oY1mC&\021A\"\003\002\007\003:L(+\0324\021\0059yQ\"\001\002\n\005A\021!\001F*fY\026\034G/[8o!\006$\b.\0227f[\026tG\017\005\002\t%%\0211#\003\002\b!J|G-^2u!\tAQ#\003\002\027\023\ta1+\032:jC2L'0\0312mK\"A\001\004\001BK\002\023\005!$\001\003oC6,7\001A\013\0027A\021Ad\b\b\003\021uI!AH\005\002\rA\023X\rZ3g\023\t\001\023E\001\004TiJLgn\032\006\003=%A\001b\t\001\003\022\003\006IaG\001\006]\006lW\r\t\005\006K\001!\tAJ\001\007y%t\027\016\036 \025\005\035B\003C\001\b\001\021\025AB\0051\001\034\021\025Q\003\001\"\021,\003!!xn\025;sS:<G#A\016\t\0175\002\021\021!C\001]\005!1m\0349z)\t9s\006C\004\031YA\005\t\031A\016\t\017E\002\021\023!C\001e\005q1m\0349zI\021,g-Y;mi\022\nT#A\032+\005m!4&A\033\021\005YZT\"A\034\013\005aJ\024!C;oG\",7m[3e\025\tQ\024\"\001\006b]:|G/\031;j_:L!\001P\034\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\004?\001\005\005I\021I \002\033A\024x\016Z;diB\023XMZ5y+\005\001\005CA!G\033\005\021%BA\"E\003\021a\027M\\4\013\003\025\013AA[1wC&\021\001E\021\005\b\021\002\t\t\021\"\001J\0031\001(o\0343vGR\f%/\033;z+\005Q\005C\001\005L\023\ta\025BA\002J]RDqA\024\001\002\002\023\005q*\001\bqe>$Wo\031;FY\026lWM\034;\025\005A\033\006C\001\005R\023\t\021\026BA\002B]fDq\001V'\002\002\003\007!*A\002yIEBqA\026\001\002\002\023\005s+A\bqe>$Wo\031;Ji\026\024\030\r^8s+\005A\006cA-]!6\t!L\003\002\\\023\005Q1m\0347mK\016$\030n\0348\n\005uS&\001C%uKJ\fGo\034:\t\017}\003\021\021!C\001A\006A1-\0318FcV\fG\016\006\002bIB\021\001BY\005\003G&\021qAQ8pY\026\fg\016C\004U=\006\005\t\031\001)\t\017\031\004\021\021!C!O\006A\001.Y:i\007>$W\rF\001K\021\035I\007!!A\005B)\fa!Z9vC2\034HCA1l\021\035!\006.!AA\002AC3\001A7q!\tAa.\003\002p\023\t\0012+\032:jC24VM]:j_:,\026\n\022\020\002\005\035A!OAA\001\022\003!1/A\bTK2,7\r^\"iS2$g*Y7f!\tqAO\002\005\002\005\005\005\t\022\001\003v'\r!h\017\006\t\005oj\\r%D\001y\025\tI\030\"A\004sk:$\030.\\3\n\005mD(!E!cgR\024\030m\031;Gk:\034G/[8oc!)Q\005\036C\001{R\t1\017C\004+i\006\005IQI@\025\003\001C\021\"a\001u\003\003%\t)!\002\002\013\005\004\b\017\\=\025\007\035\n9\001\003\004\031\003\003\001\ra\007\005\n\003\027!\030\021!CA\003\033\tq!\0368baBd\027\020\006\003\002\020\005U\001\003\002\005\002\022mI1!a\005\n\005\031y\005\017^5p]\"I\021qCA\005\003\003\005\raJ\001\004q\022\002\004\"CA\016i\006\005I\021BA\017\003-\021X-\0313SKN|GN^3\025\005\005}\001cA!\002\"%\031\0211\005\"\003\r=\023'.Z2u\001")
/*     */ public class SelectChildName implements SelectionPathElement, Product, Serializable {
/*     */   public static final long serialVersionUID = 2L;
/*     */   
/*     */   private final String name;
/*     */   
/*     */   public String name() {
/* 261 */     return this.name;
/*     */   }
/*     */   
/*     */   public SelectChildName copy(String name) {
/* 261 */     return new SelectChildName(name);
/*     */   }
/*     */   
/*     */   public String copy$default$1() {
/* 261 */     return name();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 261 */     return "SelectChildName";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 261 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 261 */     int i = x$1;
/* 261 */     switch (i) {
/*     */       default:
/* 261 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 261 */     return name();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 261 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 261 */     return x$1 instanceof SelectChildName;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 261 */     return ScalaRunTime$.MODULE$._hashCode(this);
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
/*     */     //   8: instanceof akka/actor/SelectChildName
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/SelectChildName
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual name : ()Ljava/lang/String;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual name : ()Ljava/lang/String;
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
/*     */     //   #261	-> 0
/*     */     //   #63	-> 14
/*     */     //   #261	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/actor/SelectChildName;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public SelectChildName(String name) {
/* 261 */     Product.class.$init$(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 262 */     return name();
/*     */   }
/*     */   
/*     */   public static <A> Function1<String, A> andThen(Function1<SelectChildName, A> paramFunction1) {
/*     */     return SelectChildName$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, SelectChildName> compose(Function1<A, String> paramFunction1) {
/*     */     return SelectChildName$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\SelectChildName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */