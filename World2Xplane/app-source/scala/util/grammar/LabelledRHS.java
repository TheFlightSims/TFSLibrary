/*     */ package scala.util.grammar;
/*     */ 
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005\005d\001B\001\003\001&\0211\002T1cK2dW\r\032*I'*\0211\001B\001\bOJ\fW.\\1s\025\t)a!\001\003vi&d'\"A\004\002\013M\034\027\r\\1\004\001U\021!bG\n\005\001-y1\003\005\002\r\0335\t!!\003\002\017\005\t9AK]3f%\"\033\006C\001\t\022\033\0051\021B\001\n\007\005\035\001&o\0343vGR\004\"\001\005\013\n\005U1!\001D*fe&\fG.\033>bE2,\007\002C\f\001\005+\007I\021\001\r\002\0131\f'-\0327\026\003e\001\"AG\016\r\001\021)A\004\001b\001;\t\t\021)\005\002\037CA\021\001cH\005\003A\031\021qAT8uQ&tw\r\005\002\021E%\0211E\002\002\004\003:L\b\002C\023\001\005#\005\013\021B\r\002\r1\f'-\0327!\021!9\003A!f\001\n\003A\023a\0015oiV\t\021\006\005\002\021U%\0211F\002\002\004\023:$\b\002C\027\001\005#\005\013\021B\025\002\t!tG\017\t\005\006_\001!\t\001M\001\007y%t\027\016\036 \025\007E\0224\007E\002\r\001eAQa\006\030A\002eAQa\n\030A\002%Bq!\016\001\002\002\023\005a'\001\003d_BLXCA\034;)\rA4\b\020\t\004\031\001I\004C\001\016;\t\025aBG1\001\036\021\0359B\007%AA\002eBqa\n\033\021\002\003\007\021\006C\004?\001E\005I\021A \002\035\r|\007/\037\023eK\032\fW\017\034;%cU\021\001iS\013\002\003*\022\021DQ\026\002\007B\021A)S\007\002\013*\021aiR\001\nk:\034\007.Z2lK\022T!\001\023\004\002\025\005tgn\034;bi&|g.\003\002K\013\n\tRO\\2iK\016\\W\r\032,be&\fgnY3\005\013qi$\031A\017\t\0175\003\021\023!C\001\035\006q1m\0349zI\021,g-Y;mi\022\022TCA(R+\005\001&FA\025C\t\025aBJ1\001\036\021\035\031\006!!A\005BQ\013Q\002\035:pIV\034G\017\025:fM&DX#A+\021\005Y[V\"A,\013\005aK\026\001\0027b]\036T\021AW\001\005U\0064\030-\003\002]/\n11\013\036:j]\036DqA\030\001\002\002\023\005\001&\001\007qe>$Wo\031;Be&$\030\020C\004a\001\005\005I\021A1\002\035A\024x\016Z;di\026cW-\\3oiR\021\021E\031\005\bG~\013\t\0211\001*\003\rAH%\r\005\bK\002\t\t\021\"\021g\003=\001(o\0343vGRLE/\032:bi>\024X#A4\021\007!\\\027%D\001j\025\tQg!\001\006d_2dWm\031;j_:L!\001\\5\003\021%#XM]1u_JDqA\034\001\002\002\023\005q.\001\005dC:,\025/^1m)\t\0018\017\005\002\021c&\021!O\002\002\b\005>|G.Z1o\021\035\031W.!AA\002\005Bq!\036\001\002\002\023\005c/\001\005iCND7i\0343f)\005I\003b\002=\001\003\003%\t%_\001\ti>\034FO]5oOR\tQ\013C\004|\001\005\005I\021\t?\002\r\025\fX/\0317t)\t\001X\020C\004du\006\005\t\031A\021)\r\001y\030QAA\005!\r\001\022\021A\005\004\003\0071!A\0033faJ,7-\031;fI\006\022\021qA\001\033)\"L7\017I2mCN\034\be^5mY\002\022W\r\t:f[>4X\rZ\021\003\003\027\taA\r\0302a9\002t!CA\b\005\005\005\t\022AA\t\003-a\025MY3mY\026$'\013S*\021\0071\t\031B\002\005\002\005\005\005\t\022AA\013'\025\t\031\"a\006\024!\r\001\022\021D\005\004\00371!AB!osJ+g\rC\0040\003'!\t!a\b\025\005\005E\001\002\003=\002\024\005\005IQI=\t\025\005\025\0221CA\001\n\003\0139#A\003baBd\0270\006\003\002*\005=BCBA\026\003c\t\031\004\005\003\r\001\0055\002c\001\016\0020\0211A$a\tC\002uAqaFA\022\001\004\ti\003\003\004(\003G\001\r!\013\005\013\003o\t\031\"!A\005\002\006e\022aB;oCB\004H._\013\005\003w\tY\005\006\003\002>\0055\003#\002\t\002@\005\r\023bAA!\r\t1q\n\035;j_:\004b\001EA#\003\023J\023bAA$\r\t1A+\0369mKJ\0022AGA&\t\031a\022Q\007b\001;!Q\021qJA\033\003\003\005\r!!\025\002\007a$\003\007\005\003\r\001\005%\003BCA+\003'\t\t\021\"\003\002X\005Y!/Z1e%\026\034x\016\034<f)\t\tI\006E\002W\0037J1!!\030X\005\031y%M[3di\":\0211C@\002\006\005%\001")
/*     */ public class LabelledRHS<A> extends TreeRHS implements Product, Serializable {
/*     */   private final A label;
/*     */   
/*     */   private final int hnt;
/*     */   
/*     */   public A label() {
/*  19 */     return this.label;
/*     */   }
/*     */   
/*     */   public int hnt() {
/*  19 */     return this.hnt;
/*     */   }
/*     */   
/*     */   public <A> LabelledRHS<A> copy(Object label, int hnt) {
/*  19 */     return new LabelledRHS((A)label, hnt);
/*     */   }
/*     */   
/*     */   public <A> A copy$default$1() {
/*  19 */     return label();
/*     */   }
/*     */   
/*     */   public <A> int copy$default$2() {
/*  19 */     return hnt();
/*     */   }
/*     */   
/*     */   public String productPrefix() {
/*  19 */     return "LabelledRHS";
/*     */   }
/*     */   
/*     */   public int productArity() {
/*  19 */     return 2;
/*     */   }
/*     */   
/*     */   public Object productElement(int x$1) {
/*  19 */     switch (x$1) {
/*     */       default:
/*  19 */         throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */       case 1:
/*     */       
/*     */       case 0:
/*     */         break;
/*     */     } 
/*  19 */     return label();
/*     */   }
/*     */   
/*     */   public Iterator<Object> productIterator() {
/*  19 */     return ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object x$1) {
/*  19 */     return x$1 instanceof LabelledRHS;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  19 */     return Statics.finalizeHash(Statics.mix(Statics.mix(-889275714, Statics.anyHash(label())), hnt()), 2);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  19 */     return ScalaRunTime$.MODULE$._toString(this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/*  19 */     if (this != x$1) {
/*     */       boolean bool;
/*  19 */       if (x$1 instanceof LabelledRHS) {
/* 236 */         bool = true;
/*     */       } else {
/* 236 */         bool = false;
/*     */       } 
/*     */       if (bool) {
/*     */         LabelledRHS<Object> labelledRHS = (LabelledRHS)x$1;
/*     */         A a2 = (A)labelledRHS.label();
/*     */         A a1;
/*     */         if (((((a1 = label()) == a2) ? true : ((a1 == null) ? false : ((a1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)a1, a2) : ((a1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)a1, a2) : a1.equals(a2))))) && hnt() == labelledRHS.hnt() && labelledRHS.canEqual(this)));
/*     */       } 
/*     */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public LabelledRHS(Object label, int hnt) {
/*     */     Product.class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\grammar\LabelledRHS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */