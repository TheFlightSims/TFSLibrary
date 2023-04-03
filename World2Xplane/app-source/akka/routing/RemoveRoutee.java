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
/*     */ @ScalaSignature(bytes = "\006\001\005ua\001B\001\003\001\036\021ABU3n_Z,'k\\;uK\026T!a\001\003\002\017I|W\017^5oO*\tQ!\001\003bW.\f7\001A\n\006\001!q!#\006\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\021\005=\001R\"\001\002\n\005E\021!\001\007*pkR,'/T1oC\036,W.\0328u\033\026\0348o]1hKB\021\021bE\005\003))\021q\001\025:pIV\034G\017\005\002\n-%\021qC\003\002\r'\026\024\030.\0317ju\006\024G.\032\005\t3\001\021)\032!C\0015\0051!o\\;uK\026,\022a\007\t\003\037qI!!\b\002\003\rI{W\017^3f\021!y\002A!E!\002\023Y\022a\002:pkR,W\r\t\005\006C\001!\tAI\001\007y%t\027\016\036 \025\005\r\"\003CA\b\001\021\025I\002\0051\001\034\021\0351\003!!A\005\002\035\nAaY8qsR\0211\005\013\005\b3\025\002\n\0211\001\034\021\035Q\003!%A\005\002-\nabY8qs\022\"WMZ1vYR$\023'F\001-U\tYRfK\001/!\tyC'D\0011\025\t\t$'A\005v]\016DWmY6fI*\0211GC\001\013C:tw\016^1uS>t\027BA\0331\005E)hn\0315fG.,GMV1sS\006t7-\032\005\bo\001\t\t\021\"\0219\0035\001(o\0343vGR\004&/\0324jqV\t\021\b\005\002;5\t1H\003\002={\005!A.\0318h\025\005q\024\001\0026bm\006L!\001Q\036\003\rM#(/\0338h\021\035\021\005!!A\005\002\r\013A\002\035:pIV\034G/\021:jif,\022\001\022\t\003\023\025K!A\022\006\003\007%sG\017C\004I\001\005\005I\021A%\002\035A\024x\016Z;di\026cW-\\3oiR\021!*\024\t\003\023-K!\001\024\006\003\007\005s\027\020C\004O\017\006\005\t\031\001#\002\007a$\023\007C\004Q\001\005\005I\021I)\002\037A\024x\016Z;di&#XM]1u_J,\022A\025\t\004'ZSU\"\001+\013\005US\021AC2pY2,7\r^5p]&\021q\013\026\002\t\023R,'/\031;pe\"9\021\fAA\001\n\003Q\026\001C2b]\026\013X/\0317\025\005ms\006CA\005]\023\ti&BA\004C_>dW-\0318\t\0179C\026\021!a\001\025\"9\001\rAA\001\n\003\n\027\001\0035bg\"\034u\016Z3\025\003\021Cqa\031\001\002\002\023\005C-\001\005u_N#(/\0338h)\005I\004b\0024\001\003\003%\teZ\001\007KF,\030\r\\:\025\005mC\007b\002(f\003\003\005\rA\023\025\004\001)l\007CA\005l\023\ta'B\001\tTKJL\027\r\034,feNLwN\\+J\tz\t\021aB\004p\005\005\005\t\022\0019\002\031I+Wn\034<f%>,H/Z3\021\005=\thaB\001\003\003\003E\tA]\n\004cN,\002\003\002;x7\rj\021!\036\006\003m*\tqA];oi&lW-\003\002yk\n\t\022IY:ue\006\034GOR;oGRLwN\\\031\t\013\005\nH\021\001>\025\003ADqaY9\002\002\023\025C\rC\004~c\006\005I\021\021@\002\013\005\004\b\017\\=\025\005\rz\b\"B\r}\001\004Y\002\"CA\002c\006\005I\021QA\003\003\035)h.\0319qYf$B!a\002\002\016A!\021\"!\003\034\023\r\tYA\003\002\007\037B$\030n\0348\t\023\005=\021\021AA\001\002\004\031\023a\001=%a!I\0211C9\002\002\023%\021QC\001\fe\026\fGMU3t_24X\r\006\002\002\030A\031!(!\007\n\007\005m1H\001\004PE*,7\r\036")
/*     */ public class RemoveRoutee implements RouterManagementMesssage, Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Routee routee;
/*     */   
/*     */   public static <A> Function1<Routee, A> andThen(Function1<RemoveRoutee, A> paramFunction1) {
/*     */     return RemoveRoutee$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, RemoveRoutee> compose(Function1<A, Routee> paramFunction1) {
/*     */     return RemoveRoutee$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public Routee routee() {
/* 406 */     return this.routee;
/*     */   }
/*     */   
/*     */   public RemoveRoutee copy(Routee routee) {
/* 406 */     return new RemoveRoutee(routee);
/*     */   }
/*     */   
/*     */   public Routee copy$default$1() {
/* 406 */     return routee();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 406 */     return "RemoveRoutee";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 406 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 406 */     int i = x$1;
/* 406 */     switch (i) {
/*     */       default:
/* 406 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 406 */     return routee();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 406 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 406 */     return x$1 instanceof RemoveRoutee;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 406 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 406 */     return ScalaRunTime$.MODULE$._toString(this);
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
/*     */     //   8: instanceof akka/routing/RemoveRoutee
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/routing/RemoveRoutee
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
/*     */     //   #406	-> 0
/*     */     //   #63	-> 14
/*     */     //   #406	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/routing/RemoveRoutee;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public RemoveRoutee(Routee routee) {
/* 406 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RemoveRoutee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */