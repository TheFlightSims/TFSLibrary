/*     */ package akka.routing;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005Ua\001B\001\003\001\036\021a\"\0213kkN$\bk\\8m'&TXM\003\002\004\t\0059!o\\;uS:<'\"A\003\002\t\005\\7.Y\002\001'\025\001\001B\004\n\026!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fMB\021q\002E\007\002\005%\021\021C\001\002\031%>,H/\032:NC:\fw-Z7f]RlUm]:tC\036,\007CA\005\024\023\t!\"BA\004Qe>$Wo\031;\021\005%1\022BA\f\013\0051\031VM]5bY&T\030M\0317f\021!I\002A!f\001\n\003Q\022AB2iC:<W-F\001\034!\tIA$\003\002\036\025\t\031\021J\034;\t\021}\001!\021#Q\001\nm\tqa\0315b]\036,\007\005C\003\"\001\021\005!%\001\004=S:LGO\020\013\003G\021\002\"a\004\001\t\013e\001\003\031A\016\t\017\031\002\021\021!C\001O\005!1m\0349z)\t\031\003\006C\004\032KA\005\t\031A\016\t\017)\002\021\023!C\001W\005q1m\0349zI\021,g-Y;mi\022\nT#\001\027+\005mi3&\001\030\021\005=\"T\"\001\031\013\005E\022\024!C;oG\",7m[3e\025\t\031$\"\001\006b]:|G/\031;j_:L!!\016\031\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\0048\001\005\005I\021\t\035\002\033A\024x\016Z;diB\023XMZ5y+\005I\004C\001\036@\033\005Y$B\001\037>\003\021a\027M\\4\013\003y\nAA[1wC&\021\001i\017\002\007'R\024\030N\\4\t\017\t\003\021\021!C\0015\005a\001O]8ek\016$\030I]5us\"9A\tAA\001\n\003)\025A\0049s_\022,8\r^#mK6,g\016\036\013\003\r&\003\"!C$\n\005!S!aA!os\"9!jQA\001\002\004Y\022a\001=%c!9A\nAA\001\n\003j\025a\0049s_\022,8\r^%uKJ\fGo\034:\026\0039\0032a\024*G\033\005\001&BA)\013\003)\031w\016\0347fGRLwN\\\005\003'B\023\001\"\023;fe\006$xN\035\005\b+\002\t\t\021\"\001W\003!\031\027M\\#rk\006dGCA,[!\tI\001,\003\002Z\025\t9!i\\8mK\006t\007b\002&U\003\003\005\rA\022\005\b9\002\t\t\021\"\021^\003!A\027m\0355D_\022,G#A\016\t\017}\003\021\021!C!A\006AAo\\*ue&tw\rF\001:\021\035\021\007!!A\005B\r\fa!Z9vC2\034HCA,e\021\035Q\025-!AA\002\031C3\001\0014j!\tIq-\003\002i\025\t\0012+\032:jC24VM]:j_:,\026\n\022\020\002\003\03591NAA\001\022\003a\027AD!eUV\034H\017U8pYNK'0\032\t\003\03754q!\001\002\002\002#\005anE\002n_V\001B\001]:\034G5\t\021O\003\002s\025\0059!/\0368uS6,\027B\001;r\005E\t%m\035;sC\016$h)\0368di&|g.\r\005\006C5$\tA\036\013\002Y\"9q,\\A\001\n\013\002\007bB=n\003\003%\tI_\001\006CB\004H.\037\013\003GmDQ!\007=A\002mAq!`7\002\002\023\005e0A\004v]\006\004\b\017\\=\025\007}\f)\001\005\003\n\003\003Y\022bAA\002\025\t1q\n\035;j_:D\001\"a\002}\003\003\005\raI\001\004q\022\002\004\"CA\006[\006\005I\021BA\007\003-\021X-\0313SKN|GN^3\025\005\005=\001c\001\036\002\022%\031\0211C\036\003\r=\023'.Z2u\001")
/*     */ public class AdjustPoolSize implements RouterManagementMesssage, Product, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final int change;
/*     */   
/*     */   public static <A> Function1<Object, A> andThen(Function1<AdjustPoolSize, A> paramFunction1) {
/*     */     return AdjustPoolSize$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, AdjustPoolSize> compose(Function1<A, Object> paramFunction1) {
/*     */     return AdjustPoolSize$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public int change() {
/* 420 */     return this.change;
/*     */   }
/*     */   
/*     */   public AdjustPoolSize copy(int change) {
/* 420 */     return new AdjustPoolSize(change);
/*     */   }
/*     */   
/*     */   public int copy$default$1() {
/* 420 */     return change();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 420 */     return "AdjustPoolSize";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 420 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 420 */     int i = x$1;
/* 420 */     switch (i) {
/*     */       default:
/* 420 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/* 420 */     return BoxesRunTime.boxToInteger(change());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 420 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 420 */     return x$1 instanceof AdjustPoolSize;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 420 */     int i = -889275714;
/* 420 */     i = Statics.mix(i, change());
/* 420 */     return Statics.finalizeHash(i, 1);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 420 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/* 420 */     if (this != x$1) {
/*     */       boolean bool;
/* 420 */       Object object = x$1;
/* 420 */       if (object instanceof AdjustPoolSize) {
/*     */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/* 420 */       if (bool) {
/* 420 */         AdjustPoolSize adjustPoolSize = (AdjustPoolSize)x$1;
/* 420 */         if ((change() == adjustPoolSize.change() && adjustPoolSize.canEqual(this)));
/*     */       } 
/* 420 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public AdjustPoolSize(int change) {
/* 420 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\AdjustPoolSize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */