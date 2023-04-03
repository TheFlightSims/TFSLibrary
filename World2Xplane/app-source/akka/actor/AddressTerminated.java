/*     */ package akka.actor;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\rb!B\001\003\001\0221!!E!eIJ,7o\035+fe6Lg.\031;fI*\0211\001B\001\006C\016$xN\035\006\002\013\005!\021m[6b'\031\001q!D\t\025/A\021\001bC\007\002\023)\t!\"A\003tG\006d\027-\003\002\r\023\t1\021I\\=SK\032\004\"AD\b\016\003\tI!\001\005\002\003'\005+Ho\034*fG\026Lg/\0323NKN\034\030mZ3\021\0059\021\022BA\n\003\005=\001vn]:jE2L\b*\031:nMVd\007C\001\005\026\023\t1\022BA\004Qe>$Wo\031;\021\005!A\022BA\r\n\0051\031VM]5bY&T\030M\0317f\021!Y\002A!f\001\n\003i\022aB1eIJ,7o]\002\001+\005q\002C\001\b \023\t\001#AA\004BI\022\024Xm]:\t\021\t\002!\021#Q\001\ny\t\001\"\0313ee\026\0348\017\t\005\006I\001!\t!J\001\007y%t\027\016\036 \025\005\031:\003C\001\b\001\021\025Y2\0051\001\037\021\035I\003!!A\005\002)\nAaY8qsR\021ae\013\005\b7!\002\n\0211\001\037\021\035i\003!%A\005\0029\nabY8qs\022\"WMZ1vYR$\023'F\0010U\tq\002gK\0012!\t\021t'D\0014\025\t!T'A\005v]\016DWmY6fI*\021a'C\001\013C:tw\016^1uS>t\027B\001\0354\005E)hn\0315fG.,GMV1sS\006t7-\032\005\bu\001\t\t\021\"\021<\0035\001(o\0343vGR\004&/\0324jqV\tA\b\005\002>\0056\taH\003\002@\001\006!A.\0318h\025\005\t\025\001\0026bm\006L!a\021 \003\rM#(/\0338h\021\035)\005!!A\005\002\031\013A\002\035:pIV\034G/\021:jif,\022a\022\t\003\021!K!!S\005\003\007%sG\017C\004L\001\005\005I\021\001'\002\035A\024x\016Z;di\026cW-\\3oiR\021Q\n\025\t\003\0219K!aT\005\003\007\005s\027\020C\004R\025\006\005\t\031A$\002\007a$\023\007C\004T\001\005\005I\021\t+\002\037A\024x\016Z;di&#XM]1u_J,\022!\026\t\004-fkU\"A,\013\005aK\021AC2pY2,7\r^5p]&\021!l\026\002\t\023R,'/\031;pe\"9A\fAA\001\n\003i\026\001C2b]\026\013X/\0317\025\005y\013\007C\001\005`\023\t\001\027BA\004C_>dW-\0318\t\017E[\026\021!a\001\033\"91\rAA\001\n\003\"\027\001\0035bg\"\034u\016Z3\025\003\035CqA\032\001\002\002\023\005s-\001\005u_N#(/\0338h)\005a\004bB5\001\003\003%\tE[\001\007KF,\030\r\\:\025\005y[\007bB)i\003\003\005\r!\024\025\004\0015\004\bC\001\005o\023\ty\027B\001\tTKJL\027\r\034,feNLwN\\+J\tz\t\021a\002\005s\005\005\005\t\022\001\003t\003E\tE\r\032:fgN$VM]7j]\006$X\r\032\t\003\035Q4\001\"\001\002\002\002#\005A!^\n\004iZ<\002\003B<{=\031j\021\001\037\006\003s&\tqA];oi&lW-\003\002|q\n\t\022IY:ue\006\034GOR;oGRLwN\\\031\t\013\021\"H\021A?\025\003MDqA\032;\002\002\023\025s\rC\005\002\002Q\f\t\021\"!\002\004\005)\021\r\0359msR\031a%!\002\t\013my\b\031\001\020\t\023\005%A/!A\005\002\006-\021aB;oCB\004H.\037\013\005\003\033\t\031\002\005\003\t\003\037q\022bAA\t\023\t1q\n\035;j_:D\021\"!\006\002\b\005\005\t\031\001\024\002\007a$\003\007C\005\002\032Q\f\t\021\"\003\002\034\005Y!/Z1e%\026\034x\016\034<f)\t\ti\002E\002>\003?I1!!\t?\005\031y%M[3di\002")
/*     */ public class AddressTerminated implements AutoReceivedMessage, PossiblyHarmful, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Address address;
/*     */   
/*     */   public static <A> Function1<Address, A> andThen(Function1<AddressTerminated, A> paramFunction1) {
/*     */     return AddressTerminated$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, AddressTerminated> compose(Function1<A, Address> paramFunction1) {
/*     */     return AddressTerminated$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public Address address() {
/* 110 */     return this.address;
/*     */   }
/*     */   
/*     */   public AddressTerminated copy(Address address) {
/* 110 */     return new AddressTerminated(address);
/*     */   }
/*     */   
/*     */   public Address copy$default$1() {
/* 110 */     return address();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 110 */     return "AddressTerminated";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 110 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 110 */     int i = x$1;
/* 110 */     switch (i) {
/*     */       default:
/* 110 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 110 */     return address();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 110 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 110 */     return x$1 instanceof AddressTerminated;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 110 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 110 */     return ScalaRunTime$.MODULE$._toString(this);
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
/*     */     //   8: instanceof akka/actor/AddressTerminated
/*     */     //   11: ifeq -> 19
/*     */     //   14: iconst_1
/*     */     //   15: istore_3
/*     */     //   16: goto -> 21
/*     */     //   19: iconst_0
/*     */     //   20: istore_3
/*     */     //   21: iload_3
/*     */     //   22: ifeq -> 84
/*     */     //   25: aload_1
/*     */     //   26: checkcast akka/actor/AddressTerminated
/*     */     //   29: astore #4
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual address : ()Lakka/actor/Address;
/*     */     //   35: aload #4
/*     */     //   37: invokevirtual address : ()Lakka/actor/Address;
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
/*     */     //   #110	-> 0
/*     */     //   #63	-> 14
/*     */     //   #110	-> 21
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	86	0	this	Lakka/actor/AddressTerminated;
/*     */     //   0	86	1	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public AddressTerminated(Address address) {
/* 110 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\AddressTerminated.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */