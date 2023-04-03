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
/*     */ @ScalaSignature(bytes = "\006\001\005]a\001B\001\003\001\036\021\021B\021:pC\022\034\027m\035;\013\005\r!\021a\002:pkRLgn\032\006\002\013\005!\021m[6b\007\001\031R\001\001\005\017%U\001\"!\003\007\016\003)Q\021aC\001\006g\016\fG.Y\005\003\033)\021a!\0218z%\0264\007CA\b\021\033\005\021\021BA\t\003\0059\021v.\036;fe\026sg/\0327pa\026\004\"!C\n\n\005QQ!a\002)s_\022,8\r\036\t\003\023YI!a\006\006\003\031M+'/[1mSj\f'\r\\3\t\021e\001!Q3A\005\002i\tq!\\3tg\006<W-F\001\034!\tIA$\003\002\036\025\t\031\021I\\=\t\021}\001!\021#Q\001\nm\t\001\"\\3tg\006<W\r\t\005\006C\001!\tAI\001\007y%t\027\016\036 \025\005\r\"\003CA\b\001\021\025I\002\0051\001\034\021\0351\003!!A\005\002\035\nAaY8qsR\0211\005\013\005\b3\025\002\n\0211\001\034\021\035Q\003!%A\005\002-\nabY8qs\022\"WMZ1vYR$\023'F\001-U\tYRfK\001/!\tyC'D\0011\025\t\t$'A\005v]\016DWmY6fI*\0211GC\001\013C:tw\016^1uS>t\027BA\0331\005E)hn\0315fG.,GMV1sS\006t7-\032\005\bo\001\t\t\021\"\0219\0035\001(o\0343vGR\004&/\0324jqV\t\021\b\005\002;5\t1H\003\002={\005!A.\0318h\025\005q\024\001\0026bm\006L!\001Q\036\003\rM#(/\0338h\021\035\021\005!!A\005\002\r\013A\002\035:pIV\034G/\021:jif,\022\001\022\t\003\023\025K!A\022\006\003\007%sG\017C\004I\001\005\005I\021A%\002\035A\024x\016Z;di\026cW-\\3oiR\0211D\023\005\b\027\036\013\t\0211\001E\003\rAH%\r\005\b\033\002\t\t\021\"\021O\003=\001(o\0343vGRLE/\032:bi>\024X#A(\021\007A\0336$D\001R\025\t\021&\"\001\006d_2dWm\031;j_:L!\001V)\003\021%#XM]1u_JDqA\026\001\002\002\023\005q+\001\005dC:,\025/^1m)\tA6\f\005\002\n3&\021!L\003\002\b\005>|G.Z1o\021\035YU+!AA\002mAq!\030\001\002\002\023\005c,\001\005iCND7i\0343f)\005!\005b\0021\001\003\003%\t%Y\001\ti>\034FO]5oOR\t\021\bC\004d\001\005\005I\021\t3\002\r\025\fX/\0317t)\tAV\rC\004LE\006\005\t\031A\016)\007\0019'\016\005\002\nQ&\021\021N\003\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\022!A\004\bY\n\t\t\021#\001n\003%\021%o\\1eG\006\034H\017\005\002\020]\0329\021AAA\001\022\003y7c\0018q+A!\021\017^\016$\033\005\021(BA:\013\003\035\021XO\034;j[\026L!!\036:\003#\005\0237\017\036:bGR4UO\\2uS>t\027\007C\003\"]\022\005q\017F\001n\021\035\001g.!A\005F\005DqA\0378\002\002\023\00550A\003baBd\027\020\006\002$y\")\021$\037a\0017!9aP\\A\001\n\003{\030aB;oCB\004H.\037\013\005\003\003\t9\001\005\003\n\003\007Y\022bAA\003\025\t1q\n\035;j_:D\001\"!\003~\003\003\005\raI\001\004q\022\002\004\"CA\007]\006\005I\021BA\b\003-\021X-\0313SKN|GN^3\025\005\005E\001c\001\036\002\024%\031\021QC\036\003\r=\023'.Z2u\001")
/*     */ public class Broadcast implements RouterEnvelope, Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final Object message;
/*     */   
/*     */   public static <A> Function1<Object, A> andThen(Function1<Broadcast, A> paramFunction1) {
/*     */     return Broadcast$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, Broadcast> compose(Function1<A, Object> paramFunction1) {
/*     */     return Broadcast$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public Object message() {
/* 178 */     return this.message;
/*     */   }
/*     */   
/*     */   public Broadcast copy(Object message) {
/* 178 */     return new Broadcast(message);
/*     */   }
/*     */   
/*     */   public Object copy$default$1() {
/* 178 */     return message();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 178 */     return "Broadcast";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 178 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 178 */     int i = x$1;
/* 178 */     switch (i) {
/*     */       default:
/* 178 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 178 */     return message();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 178 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 178 */     return x$1 instanceof Broadcast;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 178 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 178 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/* 178 */     if (this != x$1) {
/*     */       boolean bool;
/* 178 */       Object object = x$1;
/* 178 */       if (object instanceof Broadcast) {
/*     */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/* 178 */       if (bool) {
/* 178 */         Broadcast broadcast = (Broadcast)x$1;
/* 178 */         if ((BoxesRunTime.equals(message(), broadcast.message()) && broadcast.canEqual(this)));
/*     */       } 
/* 178 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Broadcast(Object message) {
/* 178 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\Broadcast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */