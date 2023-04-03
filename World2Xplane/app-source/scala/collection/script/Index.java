/*     */ package scala.collection.script;
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
/*     */ @ScalaSignature(bytes = "\006\001\005\005a\001B\001\003\001&\021Q!\0238eKbT!a\001\003\002\rM\034'/\0339u\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001'\021\001!B\004\n\021\005-aQ\"\001\002\n\0055\021!\001\003'pG\006$\030n\0348\021\005=\001R\"\001\004\n\005E1!a\002)s_\022,8\r\036\t\003\037MI!\001\006\004\003\031M+'/[1mSj\f'\r\\3\t\021Y\001!Q3A\005\002]\t\021A\\\013\0021A\021q\"G\005\0035\031\0211!\0238u\021!a\002A!E!\002\023A\022A\0018!\021\025q\002\001\"\001 \003\031a\024N\\5u}Q\021\001%\t\t\003\027\001AQAF\017A\002aAqa\t\001\002\002\023\005A%\001\003d_BLHC\001\021&\021\0351\"\005%AA\002aAqa\n\001\022\002\023\005\001&\001\bd_BLH\005Z3gCVdG\017J\031\026\003%R#\001\007\026,\003-\002\"\001L\031\016\0035R!AL\030\002\023Ut7\r[3dW\026$'B\001\031\007\003)\tgN\\8uCRLwN\\\005\003e5\022\021#\0368dQ\026\0347.\0323WCJL\027M\\2f\021\035!\004!!A\005BU\nQ\002\035:pIV\034G\017\025:fM&DX#\001\034\021\005]bT\"\001\035\013\005eR\024\001\0027b]\036T\021aO\001\005U\0064\030-\003\002>q\t11\013\036:j]\036Dqa\020\001\002\002\023\005q#\001\007qe>$Wo\031;Be&$\030\020C\004B\001\005\005I\021\001\"\002\035A\024x\016Z;di\026cW-\\3oiR\0211I\022\t\003\037\021K!!\022\004\003\007\005s\027\020C\004H\001\006\005\t\031\001\r\002\007a$\023\007C\004J\001\005\005I\021\t&\002\037A\024x\016Z;di&#XM]1u_J,\022a\023\t\004\0316\033U\"\001\003\n\0059#!\001C%uKJ\fGo\034:\t\017A\003\021\021!C\001#\006A1-\0318FcV\fG\016\006\002S+B\021qbU\005\003)\032\021qAQ8pY\026\fg\016C\004H\037\006\005\t\031A\"\t\017]\003\021\021!C!1\006A\001.Y:i\007>$W\rF\001\031\021\035Q\006!!A\005Bm\013\001\002^8TiJLgn\032\013\002m!9Q\fAA\001\n\003r\026AB3rk\006d7\017\006\002S?\"9q\tXA\001\002\004\031uaB1\003\003\003E\tAY\001\006\023:$W\r\037\t\003\027\r4q!\001\002\002\002#\005AmE\002dKJ\001BAZ5\031A5\tqM\003\002i\r\0059!/\0368uS6,\027B\0016h\005E\t%m\035;sC\016$h)\0368di&|g.\r\005\006=\r$\t\001\034\013\002E\"9!lYA\001\n\013Z\006bB8d\003\003%\t\t]\001\006CB\004H.\037\013\003AEDQA\0068A\002aAqa]2\002\002\023\005E/A\004v]\006\004\b\017\\=\025\005UD\bcA\bw1%\021qO\002\002\007\037B$\030n\0348\t\017e\024\030\021!a\001A\005\031\001\020\n\031\t\017m\034\027\021!C\005y\006Y!/Z1e%\026\034x\016\034<f)\005i\bCA\034\023\ty\bH\001\004PE*,7\r\036")
/*     */ public class Index extends Location implements Product, Serializable {
/*     */   private final int n;
/*     */   
/*     */   public static <A> Function1<Object, A> andThen(Function1<Index, A> paramFunction1) {
/*     */     return Index$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, Index> compose(Function1<A, Object> paramFunction1) {
/*     */     return Index$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public int n() {
/*  24 */     return this.n;
/*     */   }
/*     */   
/*     */   public Index copy(int n) {
/*  24 */     return new Index(n);
/*     */   }
/*     */   
/*     */   public int copy$default$1() {
/*  24 */     return n();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*  24 */     return "Index";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*  24 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*  24 */     switch (x$1) {
/*     */       default:
/*  24 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/*  24 */     return BoxesRunTime.boxToInteger(n());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*  24 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*  24 */     return x$1 instanceof Index;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  24 */     return Statics.finalizeHash(Statics.mix(-889275714, n()), 1);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  24 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*  24 */     if (this != x$1) {
/*     */       boolean bool;
/*  24 */       if (x$1 instanceof Index) {
/* 236 */         bool = true;
/*     */       } else {
/* 236 */         bool = false;
/*     */       } 
/*     */       if (bool) {
/*     */         Index index = (Index)x$1;
/*     */         if ((n() == index.n() && index.canEqual(this)));
/*     */       } 
/*     */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Index(int n) {
/*     */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\script\Index.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */