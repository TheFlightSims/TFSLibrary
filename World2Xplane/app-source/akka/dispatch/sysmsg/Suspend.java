/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001Y4Q!\001\002A\r!\021qaU;ta\026tGM\003\002\004\t\00511/_:ng\036T!!\002\004\002\021\021L7\017]1uG\"T\021aB\001\005C.\\\027m\005\004\001\023=\031b#\007\t\003\0255i\021a\003\006\002\031\005)1oY1mC&\021ab\003\002\007\003:L(+\0324\021\005A\tR\"\001\002\n\005I\021!!D*zgR,W.T3tg\006<W\r\005\002\021)%\021QC\001\002\034'R\f7\017[,iK:<\026-\033;j]\0364uN]\"iS2$'/\0328\021\005)9\022B\001\r\f\005\035\001&o\0343vGR\004\"A\003\016\n\005mY!\001D*fe&\fG.\033>bE2,\007\"B\017\001\t\003y\022A\002\037j]&$hh\001\001\025\003\001\002\"\001\005\001\t\017\t\002\021\021!C\001?\005!1m\0349z\021\035!\003!!A\005B\025\nQ\002\035:pIV\034G\017\025:fM&DX#\001\024\021\005\035bS\"\001\025\013\005%R\023\001\0027b]\036T\021aK\001\005U\0064\030-\003\002.Q\t11\013\036:j]\036Dqa\f\001\002\002\023\005\001'\001\007qe>$Wo\031;Be&$\0300F\0012!\tQ!'\003\0024\027\t\031\021J\034;\t\017U\002\021\021!C\001m\005q\001O]8ek\016$X\t\\3nK:$HCA\034;!\tQ\001(\003\002:\027\t\031\021I\\=\t\017m\"\024\021!a\001c\005\031\001\020J\031\t\017u\002\021\021!C!}\005y\001O]8ek\016$\030\n^3sCR|'/F\001@!\r\0015iN\007\002\003*\021!iC\001\013G>dG.Z2uS>t\027B\001#B\005!IE/\032:bi>\024\bb\002$\001\003\003%\taR\001\tG\006tW)];bYR\021\001j\023\t\003\025%K!AS\006\003\017\t{w\016\\3b]\"91(RA\001\002\0049\004bB'\001\003\003%\tET\001\tQ\006\034\bnQ8eKR\t\021\007C\004Q\001\005\005I\021I)\002\021Q|7\013\036:j]\036$\022A\n\005\b'\002\t\t\021\"\021U\003\031)\027/^1mgR\021\001*\026\005\bwI\013\t\0211\0018Q\r\001qK\027\t\003\025aK!!W\006\003!M+'/[1m-\026\0248/[8o+&#e$A\001\b\021q\023\021\021!E\001\ru\013qaU;ta\026tG\r\005\002\021=\032A\021AAA\001\022\0031qlE\002_Af\0012!\0313!\033\005\021'BA2\f\003\035\021XO\034;j[\026L!!\0322\003#\005\0237\017\036:bGR4UO\\2uS>t\007\007C\003\036=\022\005q\rF\001^\021\035\001f,!A\005FECqA\0330\002\002\023\005u$A\003baBd\027\020C\004m=\006\005I\021Q7\002\017Ut\027\r\0359msR\021\001J\034\005\b_.\f\t\0211\001!\003\rAH\005\r\005\bcz\013\t\021\"\003s\003-\021X-\0313SKN|GN^3\025\003M\004\"a\n;\n\005UD#AB(cU\026\034G\017")
/*     */ public class Suspend implements SystemMessage, StashWhenWaitingForChildren, Product {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private transient SystemMessage next;
/*     */   
/*     */   public SystemMessage next() {
/* 220 */     return this.next;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void next_$eq(SystemMessage x$1) {
/* 220 */     this.next = x$1;
/*     */   }
/*     */   
/*     */   public void unlink() {
/* 220 */     SystemMessage$class.unlink(this);
/*     */   }
/*     */   
/*     */   public boolean unlinked() {
/* 220 */     return SystemMessage$class.unlinked(this);
/*     */   }
/*     */   
/*     */   public Suspend copy() {
/* 220 */     return new Suspend();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/* 220 */     return "Suspend";
/*     */   }
/*     */   
/*     */   public int productArity() {
/* 220 */     return 0;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/* 220 */     int i = x$1;
/* 220 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/* 220 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/* 220 */     return x$1 instanceof Suspend;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 220 */     return ScalaRunTime$.MODULE$._hashCode(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 220 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*     */     boolean bool;
/* 220 */     Object object = x$1;
/* 220 */     if (object instanceof Suspend) {
/*     */       bool = true;
/*     */     } else {
/*     */       bool = false;
/*     */     } 
/* 220 */     return (bool && ((Suspend)x$1).canEqual(this));
/*     */   }
/*     */   
/*     */   public Suspend() {
/* 220 */     SystemMessage$class.$init$(this);
/* 220 */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\Suspend.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */