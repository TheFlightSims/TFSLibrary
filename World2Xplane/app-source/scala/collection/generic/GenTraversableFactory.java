/*     */ package scala.collection.generic;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Function3;
/*     */ import scala.Function4;
/*     */ import scala.Function5;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Seq$;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.NumericRange$;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.math.Integral;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Numeric$IntIsIntegral$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractFunction3;
/*     */ import scala.runtime.AbstractFunction4;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t}e!B\001\003\003\003I!!F$f]R\023\030M^3sg\006\024G.\032$bGR|'/\037\006\003\007\021\tqaZ3oKJL7M\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!\006\002\013#M\021\001a\003\t\004\0315yQ\"\001\002\n\0059\021!\001E$f]\026\024\030nY\"p[B\fg.[8o!\t\001\022\003\004\001\005\013I\001!\031A\n\003\005\r\033UC\001\013\"#\t)\022\004\005\002\027/5\ta!\003\002\031\r\t9aj\034;iS:<'c\001\016\035O\031!1\004\001\001\032\0051a$/\0324j]\026lWM\034;?!\rib\004I\007\002\t%\021q\004\002\002\017\017\026tGK]1wKJ\034\030M\0317f!\t\001\022\005B\003##\t\0071EA\001Y#\t)B\005\005\002\027K%\021aE\002\002\004\003:L\b\003\002\007)A=I!!\013\002\0035\035+g.\032:jGR\023\030M^3sg\006\024G.\032+f[Bd\027\r^3\t\013-\002A\021\001\027\002\rqJg.\033;?)\005i\003c\001\007\001\037\031!q\006\001\0031\005-\021V-^:bE2,7I\021$\024\0059\n\004c\001\0324+5\t\001A\002\0035\001\001)$aE$f]\026\024\030nY\"b]\n+\030\016\0343Ge>lWC\001\034D'\r\031tG\017\t\003-aJ!!\017\004\003\r\005s\027PU3g!\025a1(\020\"F\023\ta$A\001\007DC:\024U/\0337e\rJ|W\016\r\002?\001B\031\001#E \021\005A\001E!C!4\003\003\005\tQ!\001$\005\ryF%\r\t\003!\r#Q\001R\032C\002\r\022\021!\021\t\004!E\021\005\"B\0264\t\0039E#\001%\021\007I\032$\tC\003Kg\021\0051*A\003baBd\027\020\006\002M%B!Q\n\025\"F\033\005q%BA(\005\003\035iW\017^1cY\026L!!\025(\003\017\t+\030\016\0343fe\")1+\023a\001)\006!aM]8n!\t\021T+\003\002W\033\t!1i\0347m\021\025Q5\007\"\001Y)\005a\005\"B\026/\t\003QF#A.\021\005Ir\003\"\002&/\t\003jF#\0010\021\t5\003Vc\030\t\004!E)\002\002C1\001\021\013\007I\021\0012\002\027I+Wo]1cY\026\034%IR\013\002c!AA\r\001E\001B\003&\021'\001\007SKV\034\030M\0317f\007\n3\005\005C\003g\001\021\005q-\001\004d_:\034\027\r^\013\003Q.$\"!\0337\021\007A\t\"\016\005\002\021W\022)A)\032b\001G!)Q.\032a\001]\006\031\001p]:\021\007Yy\027/\003\002q\r\tQAH]3qK\006$X\r\032 \021\007u\021(.\003\002t\t\tYAK]1wKJ\034\030M\0317f\021\025)\b\001\"\001w\003\0211\027\016\0347\026\005]\\Hc\001=\002\004Q\021\021\020 \t\004!EQ\bC\001\t|\t\025!EO1\001$\021\031iH\017\"a\001}\006!Q\r\\3n!\r1rP_\005\004\003\0031!\001\003\037cs:\fW.\032 \t\017\005\025A\0171\001\002\b\005\ta\016E\002\027\003\023I1!a\003\007\005\rIe\016\036\005\007k\002!\t!a\004\026\t\005E\0211\004\013\007\003'\t\t#!\n\025\t\005U\021Q\004\t\005!E\t9\002\005\003\021#\005e\001c\001\t\002\034\0211A)!\004C\002\rB\001\"`A\007\t\003\007\021q\004\t\005-}\fI\002\003\005\002$\0055\001\031AA\004\003\tq\027\007\003\005\002(\0055\001\031AA\004\003\tq'\007\003\004v\001\021\005\0211F\013\005\003[\tI\004\006\005\0020\005}\022\021IA\")\021\t\t$a\017\021\tA\t\0221\007\t\005!E\t)\004\005\003\021#\005]\002c\001\t\002:\0211A)!\013C\002\rB\001\"`A\025\t\003\007\021Q\b\t\005-}\f9\004\003\005\002$\005%\002\031AA\004\021!\t9#!\013A\002\005\035\001\002CA#\003S\001\r!a\002\002\0059\034\004BB;\001\t\003\tI%\006\003\002L\005eCCCA'\003?\n\t'a\031\002fQ!\021qJA.!\021\001\022#!\025\021\tA\t\0221\013\t\005!E\t)\006\005\003\021#\005]\003c\001\t\002Z\0211A)a\022C\002\rB\001\"`A$\t\003\007\021Q\f\t\005-}\f9\006\003\005\002$\005\035\003\031AA\004\021!\t9#a\022A\002\005\035\001\002CA#\003\017\002\r!a\002\t\021\005\035\024q\ta\001\003\017\t!A\034\033\t\rU\004A\021AA6+\021\ti'! \025\031\005=\0241QAC\003\017\013I)a#\025\t\005E\024q\020\t\005!E\t\031\b\005\003\021#\005U\004\003\002\t\022\003o\002B\001E\t\002zA!\001#EA>!\r\001\022Q\020\003\007\t\006%$\031A\022\t\021u\fI\007\"a\001\003\003\003BAF@\002|!A\0211EA5\001\004\t9\001\003\005\002(\005%\004\031AA\004\021!\t)%!\033A\002\005\035\001\002CA4\003S\002\r!a\002\t\021\0055\025\021\016a\001\003\017\t!A\\\033\t\017\005E\005\001\"\001\002\024\006AA/\0312vY\006$X-\006\003\002\026\006uE\003BAL\003S#B!!'\002 B!\001#EAN!\r\001\022Q\024\003\007\t\006=%\031A\022\t\021\005\005\026q\022a\001\003G\013\021A\032\t\b-\005\025\026qAAN\023\r\t9K\002\002\n\rVt7\r^5p]FB\001\"!\002\002\020\002\007\021q\001\005\b\003#\003A\021AAW+\021\ty+!/\025\r\005E\0261YAc)\021\t\031,a/\021\tA\t\022Q\027\t\005!E\t9\fE\002\021\003s#a\001RAV\005\004\031\003\002CAQ\003W\003\r!!0\021\023Y\ty,a\002\002\b\005]\026bAAa\r\tIa)\0368di&|gN\r\005\t\003G\tY\0131\001\002\b!A\021qEAV\001\004\t9\001C\004\002\022\002!\t!!3\026\t\005-\027q\033\013\t\003\033\f\t/a9\002fR!\021qZAm!\021\001\022#!5\021\tA\t\0221\033\t\005!E\t)\016E\002\021\003/$a\001RAd\005\004\031\003\002CAQ\003\017\004\r!a7\021\027Y\ti.a\002\002\b\005\035\021Q[\005\004\003?4!!\003$v]\016$\030n\03484\021!\t\031#a2A\002\005\035\001\002CA\024\003\017\004\r!a\002\t\021\005\025\023q\031a\001\003\017Aq!!%\001\t\003\tI/\006\003\002l\006eHCCAw\005\007\021)Aa\002\003\nQ!\021q^A~!\021\001\022#!=\021\tA\t\0221\037\t\005!E\t)\020\005\003\021#\005]\bc\001\t\002z\0221A)a:C\002\rB\001\"!)\002h\002\007\021Q \t\016-\005}\030qAA\004\003\017\t9!a>\n\007\t\005aAA\005Gk:\034G/[8oi!A\0211EAt\001\004\t9\001\003\005\002(\005\035\b\031AA\004\021!\t)%a:A\002\005\035\001\002CA4\003O\004\r!a\002\t\017\005E\005\001\"\001\003\016U!!q\002B\020)1\021\tB!\013\003,\t5\"q\006B\031)\021\021\031B!\t\021\tA\t\"Q\003\t\005!E\0219\002\005\003\021#\te\001\003\002\t\022\0057\001B\001E\t\003\036A\031\001Ca\b\005\r\021\023YA1\001$\021!\t\tKa\003A\002\t\r\002c\004\f\003&\005\035\021qAA\004\003\017\t9A!\b\n\007\t\035bAA\005Gk:\034G/[8ok!A\0211\005B\006\001\004\t9\001\003\005\002(\t-\001\031AA\004\021!\t)Ea\003A\002\005\035\001\002CA4\005\027\001\r!a\002\t\021\0055%1\002a\001\003\017AqA!\016\001\t\003\0219$A\003sC:<W-\006\003\003:\t\005CC\002B\036\005C\022)\007\006\003\003>\t\025\003\003\002\t\022\005\0012\001\005B!\t\035\021\031Ea\rC\002\r\022\021\001\026\005\013\005\017\022\031$!AA\004\t%\023AC3wS\022,gnY3%cA1!1\nB.\005qAA!\024\003X9!!q\nB+\033\t\021\tFC\002\003T!\ta\001\020:p_Rt\024\"A\004\n\007\tec!A\004qC\016\\\027mZ3\n\t\tu#q\f\002\t\023:$Xm\032:bY*\031!\021\f\004\t\021\t\r$1\007a\001\005\tQa\035;beRD\001Ba\032\0034\001\007!qH\001\004K:$\007b\002B\033\001\021\005!1N\013\005\005[\022)\b\006\005\003p\tu$q\020BA)\021\021\tHa\036\021\tA\t\"1\017\t\004!\tUDa\002B\"\005S\022\ra\t\005\013\005s\022I'!AA\004\tm\024AC3wS\022,gnY3%eA1!1\nB.\005gB\001Ba\031\003j\001\007!1\017\005\t\005O\022I\0071\001\003t!A!1\021B5\001\004\021\031(\001\003ti\026\004\bb\002BD\001\021\005!\021R\001\bSR,'/\031;f+\021\021YIa%\025\r\t5%\021\024BN)\021\021yI!&\021\tA\t\"\021\023\t\004!\tMEA\002#\003\006\n\0071\005\003\005\002\"\n\025\005\031\001BL!\0351\022Q\025BI\005#C\001Ba\031\003\006\002\007!\021\023\005\t\005;\023)\t1\001\002\b\005\031A.\0328")
/*     */ public abstract class GenTraversableFactory<CC extends GenTraversable<Object>> extends GenericCompanion<CC> {
/*     */   private GenericCanBuildFrom<Nothing$> ReusableCBF;
/*     */   
/*     */   private volatile boolean bitmap$0;
/*     */   
/*     */   public class ReusableCBF extends GenericCanBuildFrom<Nothing$> {
/*     */     public ReusableCBF(GenTraversableFactory<CC> $outer) {
/*  43 */       super($outer);
/*     */     }
/*     */     
/*     */     public Builder<Nothing$, CC> apply() {
/*  44 */       return scala$collection$generic$GenTraversableFactory$ReusableCBF$$$outer().newBuilder();
/*     */     }
/*     */   }
/*     */   
/*     */   private GenericCanBuildFrom ReusableCBF$lzycompute() {
/*  46 */     synchronized (this) {
/*  46 */       if (!this.bitmap$0) {
/*  46 */         this.ReusableCBF = new ReusableCBF(this);
/*  46 */         this.bitmap$0 = true;
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/generic/GenTraversableFactory}} */
/*  46 */       return this.ReusableCBF;
/*     */     } 
/*     */   }
/*     */   
/*     */   public GenericCanBuildFrom<Nothing$> ReusableCBF() {
/*  46 */     return this.bitmap$0 ? this.ReusableCBF : ReusableCBF$lzycompute();
/*     */   }
/*     */   
/*     */   public class GenericCanBuildFrom<A> implements CanBuildFrom<CC, A, CC> {
/*     */     public GenericCanBuildFrom(GenTraversableFactory $outer) {}
/*     */     
/*     */     public Builder<A, CC> apply(GenTraversable from) {
/*  58 */       return from.genericBuilder();
/*     */     }
/*     */     
/*     */     public Builder<A, CC> apply() {
/*  63 */       return scala$collection$generic$GenTraversableFactory$GenericCanBuildFrom$$$outer().newBuilder();
/*     */     }
/*     */   }
/*     */   
/*     */   public <A> CC concat(Seq xss) {
/*  72 */     Builder b = newBuilder();
/*  74 */     if (xss.forall((Function1)new GenTraversableFactory$$anonfun$concat$1(this)))
/*  75 */       b.sizeHint(BoxesRunTime.unboxToInt(((TraversableOnce)xss.map((Function1)new GenTraversableFactory$$anonfun$concat$2(this), Seq$.MODULE$.canBuildFrom())).sum((Numeric)Numeric$IntIsIntegral$.MODULE$))); 
/*  77 */     xss.seq().foreach((Function1)new GenTraversableFactory$$anonfun$concat$3(this, (GenTraversableFactory<CC>)b));
/*  78 */     return (CC)b.result();
/*     */   }
/*     */   
/*     */   public class GenTraversableFactory$$anonfun$concat$1 extends AbstractFunction1<Traversable<A>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Traversable x$1) {
/*     */       return x$1 instanceof scala.collection.IndexedSeq;
/*     */     }
/*     */     
/*     */     public GenTraversableFactory$$anonfun$concat$1(GenTraversableFactory $outer) {}
/*     */   }
/*     */   
/*     */   public class GenTraversableFactory$$anonfun$concat$2 extends AbstractFunction1<Traversable<A>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(Traversable x$2) {
/*     */       return x$2.size();
/*     */     }
/*     */     
/*     */     public GenTraversableFactory$$anonfun$concat$2(GenTraversableFactory $outer) {}
/*     */   }
/*     */   
/*     */   public class GenTraversableFactory$$anonfun$concat$3 extends AbstractFunction1<Traversable<A>, Builder<A, CC>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$1;
/*     */     
/*     */     public final Builder<A, CC> apply(Traversable xs) {
/*     */       return (Builder<A, CC>)this.b$1.$plus$plus$eq((TraversableOnce)xs);
/*     */     }
/*     */     
/*     */     public GenTraversableFactory$$anonfun$concat$3(GenTraversableFactory $outer, Builder b$1) {}
/*     */   }
/*     */   
/*     */   public <A> CC fill(int n, Function0 elem) {
/*  87 */     Builder b = newBuilder();
/*  88 */     b.sizeHint(n);
/*  89 */     int i = 0;
/*  90 */     while (i < n) {
/*  91 */       b.$plus$eq(elem.apply());
/*  92 */       i++;
/*     */     } 
/*  94 */     return (CC)b.result();
/*     */   }
/*     */   
/*     */   public <A> CC fill(int n1, int n2, Function0 elem) {
/* 104 */     return tabulate(n1, (Function1<Object, ?>)new GenTraversableFactory$$anonfun$fill$1(this, n2, (GenTraversableFactory<CC>)elem));
/*     */   }
/*     */   
/*     */   public class GenTraversableFactory$$anonfun$fill$1 extends AbstractFunction1<Object, CC> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$4;
/*     */     
/*     */     private final Function0 elem$4;
/*     */     
/*     */     public final CC apply(int x$3) {
/* 104 */       return (CC)this.$outer.fill(this.n2$4, this.elem$4);
/*     */     }
/*     */     
/*     */     public GenTraversableFactory$$anonfun$fill$1(GenTraversableFactory $outer, int n2$4, Function0 elem$4) {}
/*     */   }
/*     */   
/*     */   public <A> CC fill(int n1, int n2, int n3, Function0 elem) {
/* 114 */     return tabulate(n1, (Function1<Object, ?>)new GenTraversableFactory$$anonfun$fill$2(this, n2, n3, (GenTraversableFactory<CC>)elem));
/*     */   }
/*     */   
/*     */   public class GenTraversableFactory$$anonfun$fill$2 extends AbstractFunction1<Object, CC> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$3;
/*     */     
/*     */     private final int n3$3;
/*     */     
/*     */     private final Function0 elem$3;
/*     */     
/*     */     public final CC apply(int x$4) {
/* 114 */       return (CC)this.$outer.fill(this.n2$3, this.n3$3, this.elem$3);
/*     */     }
/*     */     
/*     */     public GenTraversableFactory$$anonfun$fill$2(GenTraversableFactory $outer, int n2$3, int n3$3, Function0 elem$3) {}
/*     */   }
/*     */   
/*     */   public <A> CC fill(int n1, int n2, int n3, int n4, Function0 elem) {
/* 125 */     return tabulate(n1, (Function1<Object, ?>)new GenTraversableFactory$$anonfun$fill$3(this, n2, n3, n4, (GenTraversableFactory<CC>)elem));
/*     */   }
/*     */   
/*     */   public class GenTraversableFactory$$anonfun$fill$3 extends AbstractFunction1<Object, CC> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$2;
/*     */     
/*     */     private final int n3$2;
/*     */     
/*     */     private final int n4$2;
/*     */     
/*     */     private final Function0 elem$2;
/*     */     
/*     */     public final CC apply(int x$5) {
/* 125 */       return (CC)this.$outer.fill(this.n2$2, this.n3$2, this.n4$2, this.elem$2);
/*     */     }
/*     */     
/*     */     public GenTraversableFactory$$anonfun$fill$3(GenTraversableFactory $outer, int n2$2, int n3$2, int n4$2, Function0 elem$2) {}
/*     */   }
/*     */   
/*     */   public <A> CC fill(int n1, int n2, int n3, int n4, int n5, Function0 elem) {
/* 137 */     return tabulate(n1, (Function1<Object, ?>)new GenTraversableFactory$$anonfun$fill$4(this, n2, n3, n4, n5, (GenTraversableFactory<CC>)elem));
/*     */   }
/*     */   
/*     */   public class GenTraversableFactory$$anonfun$fill$4 extends AbstractFunction1<Object, CC> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$1;
/*     */     
/*     */     private final int n3$1;
/*     */     
/*     */     private final int n4$1;
/*     */     
/*     */     private final int n5$1;
/*     */     
/*     */     private final Function0 elem$1;
/*     */     
/*     */     public final CC apply(int x$6) {
/* 137 */       return (CC)this.$outer.fill(this.n2$1, this.n3$1, this.n4$1, this.n5$1, this.elem$1);
/*     */     }
/*     */     
/*     */     public GenTraversableFactory$$anonfun$fill$4(GenTraversableFactory $outer, int n2$1, int n3$1, int n4$1, int n5$1, Function0 elem$1) {}
/*     */   }
/*     */   
/*     */   public <A> CC tabulate(int n, Function1 f) {
/* 145 */     Builder b = newBuilder();
/* 146 */     b.sizeHint(n);
/* 147 */     int i = 0;
/* 148 */     while (i < n) {
/* 149 */       b.$plus$eq(f.apply(BoxesRunTime.boxToInteger(i)));
/* 150 */       i++;
/*     */     } 
/* 152 */     return (CC)b.result();
/*     */   }
/*     */   
/*     */   public <A> CC tabulate(int n1, int n2, Function2 f) {
/* 163 */     return tabulate(n1, (Function1<Object, ?>)new GenTraversableFactory$$anonfun$tabulate$1(this, n2, (GenTraversableFactory<CC>)f));
/*     */   }
/*     */   
/*     */   public class GenTraversableFactory$$anonfun$tabulate$1 extends AbstractFunction1<Object, CC> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$8;
/*     */     
/*     */     public final Function2 f$4;
/*     */     
/*     */     public final CC apply(int i1) {
/* 163 */       return (CC)this.$outer.tabulate(this.n2$8, (Function1)new GenTraversableFactory$$anonfun$tabulate$1$$anonfun$apply$1(this, i1));
/*     */     }
/*     */     
/*     */     public GenTraversableFactory$$anonfun$tabulate$1(GenTraversableFactory $outer, int n2$8, Function2 f$4) {}
/*     */     
/*     */     public class GenTraversableFactory$$anonfun$tabulate$1$$anonfun$apply$1 extends AbstractFunction1<Object, A> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final int i1$1;
/*     */       
/*     */       public final A apply(int x$7) {
/* 163 */         return (A)this.$outer.f$4.apply(BoxesRunTime.boxToInteger(this.i1$1), BoxesRunTime.boxToInteger(x$7));
/*     */       }
/*     */       
/*     */       public GenTraversableFactory$$anonfun$tabulate$1$$anonfun$apply$1(GenTraversableFactory$$anonfun$tabulate$1 $outer, int i1$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public <A> CC tabulate(int n1, int n2, int n3, Function3 f) {
/* 174 */     return tabulate(n1, (Function1<Object, ?>)new GenTraversableFactory$$anonfun$tabulate$2(this, n2, n3, (GenTraversableFactory<CC>)f));
/*     */   }
/*     */   
/*     */   public class GenTraversableFactory$$anonfun$tabulate$2 extends AbstractFunction1<Object, CC> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$7;
/*     */     
/*     */     private final int n3$6;
/*     */     
/*     */     public final Function3 f$3;
/*     */     
/*     */     public final CC apply(int i1) {
/* 174 */       return (CC)this.$outer.tabulate(this.n2$7, this.n3$6, (Function2)new GenTraversableFactory$$anonfun$tabulate$2$$anonfun$apply$2(this, i1));
/*     */     }
/*     */     
/*     */     public GenTraversableFactory$$anonfun$tabulate$2(GenTraversableFactory $outer, int n2$7, int n3$6, Function3 f$3) {}
/*     */     
/*     */     public class GenTraversableFactory$$anonfun$tabulate$2$$anonfun$apply$2 extends AbstractFunction2<Object, Object, A> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final int i1$2;
/*     */       
/*     */       public final A apply(int x$8, int x$9) {
/* 174 */         return (A)this.$outer.f$3.apply(BoxesRunTime.boxToInteger(this.i1$2), BoxesRunTime.boxToInteger(x$8), BoxesRunTime.boxToInteger(x$9));
/*     */       }
/*     */       
/*     */       public GenTraversableFactory$$anonfun$tabulate$2$$anonfun$apply$2(GenTraversableFactory$$anonfun$tabulate$2 $outer, int i1$2) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public <A> CC tabulate(int n1, int n2, int n3, int n4, Function4 f) {
/* 186 */     return tabulate(n1, (Function1<Object, ?>)new GenTraversableFactory$$anonfun$tabulate$3(this, n2, n3, n4, (GenTraversableFactory<CC>)f));
/*     */   }
/*     */   
/*     */   public class GenTraversableFactory$$anonfun$tabulate$3 extends AbstractFunction1<Object, CC> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$6;
/*     */     
/*     */     private final int n3$5;
/*     */     
/*     */     private final int n4$4;
/*     */     
/*     */     public final Function4 f$2;
/*     */     
/*     */     public final CC apply(int i1) {
/* 186 */       return (CC)this.$outer.tabulate(this.n2$6, this.n3$5, this.n4$4, (Function3)new GenTraversableFactory$$anonfun$tabulate$3$$anonfun$apply$3(this, i1));
/*     */     }
/*     */     
/*     */     public GenTraversableFactory$$anonfun$tabulate$3(GenTraversableFactory $outer, int n2$6, int n3$5, int n4$4, Function4 f$2) {}
/*     */     
/*     */     public class GenTraversableFactory$$anonfun$tabulate$3$$anonfun$apply$3 extends AbstractFunction3<Object, Object, Object, A> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final int i1$3;
/*     */       
/*     */       public final A apply(int x$10, int x$11, int x$12) {
/* 186 */         return (A)this.$outer.f$2.apply(BoxesRunTime.boxToInteger(this.i1$3), BoxesRunTime.boxToInteger(x$10), BoxesRunTime.boxToInteger(x$11), BoxesRunTime.boxToInteger(x$12));
/*     */       }
/*     */       
/*     */       public GenTraversableFactory$$anonfun$tabulate$3$$anonfun$apply$3(GenTraversableFactory$$anonfun$tabulate$3 $outer, int i1$3) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public <A> CC tabulate(int n1, int n2, int n3, int n4, int n5, Function5 f) {
/* 199 */     return tabulate(n1, (Function1<Object, ?>)new GenTraversableFactory$$anonfun$tabulate$4(this, n2, n3, n4, n5, (GenTraversableFactory<CC>)f));
/*     */   }
/*     */   
/*     */   public class GenTraversableFactory$$anonfun$tabulate$4 extends AbstractFunction1<Object, CC> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final int n2$5;
/*     */     
/*     */     private final int n3$4;
/*     */     
/*     */     private final int n4$3;
/*     */     
/*     */     private final int n5$2;
/*     */     
/*     */     public final Function5 f$1;
/*     */     
/*     */     public final CC apply(int i1) {
/* 199 */       return (CC)this.$outer.tabulate(this.n2$5, this.n3$4, this.n4$3, this.n5$2, (Function4)new GenTraversableFactory$$anonfun$tabulate$4$$anonfun$apply$4(this, i1));
/*     */     }
/*     */     
/*     */     public GenTraversableFactory$$anonfun$tabulate$4(GenTraversableFactory $outer, int n2$5, int n3$4, int n4$3, int n5$2, Function5 f$1) {}
/*     */     
/*     */     public class GenTraversableFactory$$anonfun$tabulate$4$$anonfun$apply$4 extends AbstractFunction4<Object, Object, Object, Object, A> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final int i1$4;
/*     */       
/*     */       public final A apply(int x$13, int x$14, int x$15, int x$16) {
/* 199 */         return (A)this.$outer.f$1.apply(BoxesRunTime.boxToInteger(this.i1$4), BoxesRunTime.boxToInteger(x$13), BoxesRunTime.boxToInteger(x$14), BoxesRunTime.boxToInteger(x$15), BoxesRunTime.boxToInteger(x$16));
/*     */       }
/*     */       
/*     */       public GenTraversableFactory$$anonfun$tabulate$4$$anonfun$apply$4(GenTraversableFactory$$anonfun$tabulate$4 $outer, int i1$4) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> CC range(Object start, Object end, Integral evidence$1) {
/* 207 */     Predef$ predef$ = Predef$.MODULE$;
/* 207 */     return range(start, end, ((Numeric)evidence$1).one(), evidence$1);
/*     */   }
/*     */   
/*     */   public <T> CC range(Object start, Object end, Object step, Integral evidence$2) {
/* 216 */     Predef$ predef$ = Predef$.MODULE$;
/* 216 */     Integral num = evidence$2;
/* 219 */     Object object1 = num.zero();
/* 219 */     if ((step == object1) ? true : ((step == null) ? false : ((step instanceof Number) ? BoxesRunTime.equalsNumObject((Number)step, object1) : ((step instanceof Character) ? BoxesRunTime.equalsCharObject((Character)step, object1) : step.equals(object1)))))
/* 219 */       throw new IllegalArgumentException("zero step"); 
/* 220 */     Builder b = newBuilder();
/* 221 */     b.sizeHint(NumericRange$.MODULE$.count(start, end, step, false, evidence$2));
/* 222 */     Object i = start;
/* 223 */     while (num.mkOrderingOps(step).$less(num.zero()) ? num.mkOrderingOps(end).$less(i) : num.mkOrderingOps(i).$less(end)) {
/* 224 */       b.$plus$eq(i);
/* 225 */       i = num.mkNumericOps(i).$plus(step);
/*     */     } 
/* 227 */     return (CC)b.result();
/*     */   }
/*     */   
/*     */   public <A> CC iterate(Object start, int len, Function1 f) {
/* 238 */     Builder b = newBuilder();
/* 239 */     if (len > 0) {
/* 240 */       b.sizeHint(len);
/* 241 */       Object acc = start;
/* 242 */       int i = 1;
/* 243 */       b.$plus$eq(start);
/* 245 */       while (i < len) {
/* 246 */         acc = f.apply(acc);
/* 247 */         i++;
/* 248 */         b.$plus$eq(acc);
/*     */       } 
/*     */     } 
/* 251 */     return (CC)b.result();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\GenTraversableFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */