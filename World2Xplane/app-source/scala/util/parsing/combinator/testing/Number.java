/*     */ package scala.util.parsing.combinator.testing;
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
/*     */ @ScalaSignature(bytes = "\006\001\005}a\001B\001\003\0016\021aAT;nE\026\024(BA\002\005\003\035!Xm\035;j]\036T!!\002\004\002\025\r|WNY5oCR|'O\003\002\b\021\0059\001/\031:tS:<'BA\005\013\003\021)H/\0337\013\003-\tQa]2bY\006\034\001a\005\003\001\035I)\002CA\b\021\033\005Q\021BA\t\013\005\031\te.\037*fMB\021qbE\005\003))\021q\001\025:pIV\034G\017\005\002\020-%\021qC\003\002\r'\026\024\030.\0317ju\006\024G.\032\005\t3\001\021)\032!C\0015\005\ta.F\001\034!\tyA$\003\002\036\025\t\031\021J\034;\t\021}\001!\021#Q\001\nm\t!A\034\021\t\013\005\002A\021\001\022\002\rqJg.\033;?)\t\031S\005\005\002%\0015\t!\001C\003\032A\001\0071\004C\004(\001\005\005I\021\001\025\002\t\r|\007/\037\013\003G%Bq!\007\024\021\002\003\0071\004C\004,\001E\005I\021\001\027\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\tQF\013\002\034]-\nq\006\005\0021k5\t\021G\003\0023g\005IQO\\2iK\016\\W\r\032\006\003i)\t!\"\0318o_R\fG/[8o\023\t1\024GA\tv]\016DWmY6fIZ\013'/[1oG\026Dq\001\017\001\002\002\023\005\023(A\007qe>$Wo\031;Qe\0264\027\016_\013\002uA\0211\bQ\007\002y)\021QHP\001\005Y\006twMC\001@\003\021Q\027M^1\n\005\005c$AB*ue&tw\rC\004D\001\005\005I\021\001\016\002\031A\024x\016Z;di\006\023\030\016^=\t\017\025\003\021\021!C\001\r\006q\001O]8ek\016$X\t\\3nK:$HCA$K!\ty\001*\003\002J\025\t\031\021I\\=\t\017-#\025\021!a\0017\005\031\001\020J\031\t\0175\003\021\021!C!\035\006y\001O]8ek\016$\030\n^3sCR|'/F\001P!\r\0016kR\007\002#*\021!KC\001\013G>dG.Z2uS>t\027B\001+R\005!IE/\032:bi>\024\bb\002,\001\003\003%\taV\001\tG\006tW)];bYR\021\001l\027\t\003\037eK!A\027\006\003\017\t{w\016\\3b]\"91*VA\001\002\0049\005bB/\001\003\003%\tEX\001\tQ\006\034\bnQ8eKR\t1\004C\004a\001\005\005I\021I1\002\021Q|7\013\036:j]\036$\022A\017\005\bG\002\t\t\021\"\021e\003\031)\027/^1mgR\021\001,\032\005\b\027\n\f\t\0211\001HQ\021\001qM\0337\021\005=A\027BA5\013\005)!W\r\035:fG\006$X\rZ\021\002W\006QB\013[5tA\rd\027m]:!o&dG\016\t2fAI,Wn\034<fI\006\nQ.\001\0043]E\002d\006M\004\b_\n\t\t\021#\001q\003\031qU/\0342feB\021A%\035\004\b\003\t\t\t\021#\001s'\r\t8/\006\t\005i^\\2%D\001v\025\t1(\"A\004sk:$\030.\\3\n\005a,(!E!cgR\024\030m\031;Gk:\034G/[8oc!)\021%\035C\001uR\t\001\017C\004ac\006\005IQI1\t\017u\f\030\021!CA}\006)\021\r\0359msR\0211e \005\0063q\004\ra\007\005\n\003\007\t\030\021!CA\003\013\tq!\0368baBd\027\020\006\003\002\b\0055\001\003B\b\002\nmI1!a\003\013\005\031y\005\017^5p]\"I\021qBA\001\003\003\005\raI\001\004q\022\002\004\"CA\nc\006\005I\021BA\013\003-\021X-\0313SKN|GN^3\025\005\005]\001cA\036\002\032%\031\0211\004\037\003\r=\023'.Z2uQ\021\txM\0337")
/*     */ public class Number implements Product, Serializable {
/*     */   private final int n;
/*     */   
/*     */   public static <A> Function1<Object, A> andThen(Function1<Number, A> paramFunction1) {
/*     */     return Number$.MODULE$.andThen(paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> Function1<A, Number> compose(Function1<A, Object> paramFunction1) {
/*     */     return Number$.MODULE$.compose(paramFunction1);
/*     */   }
/*     */   
/*     */   public int n() {
/*  11 */     return this.n;
/*     */   }
/*     */   
/*     */   public Number copy(int n) {
/*  11 */     return new Number(n);
/*     */   }
/*     */   
/*     */   public int copy$default$1() {
/*  11 */     return n();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*  11 */     return "Number";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*  11 */     return 1;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*  11 */     switch (x$1) {
/*     */       default:
/*  11 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 0:
/*     */         break;
/*     */     } 
/*  11 */     return BoxesRunTime.boxToInteger(n());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*  11 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*  11 */     return x$1 instanceof Number;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  11 */     return Statics.finalizeHash(Statics.mix(-889275714, n()), 1);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  11 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*  11 */     if (this != x$1) {
/*     */       boolean bool;
/*  11 */       if (x$1 instanceof Number) {
/* 236 */         bool = true;
/*     */       } else {
/* 236 */         bool = false;
/*     */       } 
/*     */       if (bool) {
/*     */         Number number = (Number)x$1;
/*     */         if ((n() == number.n() && number.canEqual(this)));
/*     */       } 
/*     */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Number(int n) {
/*     */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\testing\Number.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */