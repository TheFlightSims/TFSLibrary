/*    */ package scala.util.regexp;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t-a!B\001\003\003\003I!\001\002\"bg\026T!a\001\003\002\rI,w-\032=q\025\t)a!\001\003vi&d'\"A\004\002\013M\034\027\r\\1\004\001M\021\001A\003\t\003\0271i\021AB\005\003\033\031\021a!\0218z%\0264\007\"B\b\001\t\003\001\022A\002\037j]&$h\bF\001\022!\t\021\002!D\001\003\t\025!\002A!\001\026\005!y&/Z4fqB$\026C\001\f\032!\tYq#\003\002\031\r\t9aj\034;iS:<\007C\001\016\034\033\005\001a!\002\017\001\003\003i\"A\002*fO\026C\bo\005\002\034\025!)qb\007C\001?Q\t\021\004C\004\"7\t\007i\021\001\022\002\025%\034h*\0367mC\ndW-F\001$!\tYA%\003\002&\r\t9!i\\8mK\006tw!B\024\001\021\003A\023aA!miB\021!$\013\004\006U\001A\ta\013\002\004\0032$8CA\025\013\021\025y\021\006\"\001.)\005A\003\"B\030*\t\003\001\024!B1qa2LHCA\031D!\tQ\"G\002\003+\001\001\0314C\001\032\032\021!)$G!b\001\n\0031\024A\001:t+\0059\004cA\0069u%\021\021H\002\002\013yI,\007/Z1uK\022t\004C\001\016\024\021!a$G!A!\002\0239\024a\001:tA!)qB\rC\005}Q\021\021g\020\005\006ku\002\ra\016\005\bCI\022\r\021\"\002#\021\031\021%\007)A\007G\005Y\021n\035(vY2\f'\r\\3!\021\025)d\0061\0018\021\025)\025\006\"\001G\003))h.\0319qYf\034V-\035\013\003\017B\0032a\003%K\023\tIeA\001\003T_6,\007cA&Ou5\tAJ\003\002N\r\005Q1m\0347mK\016$\030n\0348\n\005=c%aA*fc\")\021\013\022a\001c\005\t\001pB\003T\001!\005A+\001\003TKF,\bC\001\016V\r\0251\006\001#\001X\005\021\031V-];\024\005US\001\"B\bV\t\003IF#\001+\t\013=*F\021A.\025\005ea\006\"B\033[\001\0049\004\"B#V\t\003qFCA$`\021\025\tV\f1\001a!\tQ\022M\002\003W\001\001\0217CA1\032\021!)\024M!b\001\n\0031\004\002\003\037b\005\003\005\013\021B\034\t\013=\tG\021\0024\025\005\001<\007\"B\033f\001\0049\004bB\021b\005\004%)A\t\005\007\005\006\004\013QB\022\007\t-\004\001\t\034\002\005'R\f'o\005\003k35\004\bCA\006o\023\tygAA\004Qe>$Wo\031;\021\005-\t\030B\001:\007\0051\031VM]5bY&T\030M\0317f\021!!(N!f\001\n\003)\030!\001:\026\003iB\001b\0366\003\022\003\006IAO\001\003e\002BQa\0046\005\002e$\"A_>\021\005iQ\007\"\002;y\001\004Q\004\002C\021k\021\013\007IQ\001\022\t\021\tS\007\022!Q!\016\rB\001b 6\002\002\023\005\021\021A\001\005G>\004\030\020F\002{\003\007Aq\001\036@\021\002\003\007!\bC\005\002\b)\f\n\021\"\001\002\n\005q1m\0349zI\021,g-Y;mi\022\nTCAA\006U\rQ\024QB\026\003\003\037\001B!!\005\002\0345\021\0211\003\006\005\003+\t9\"A\005v]\016DWmY6fI*\031\021\021\004\004\002\025\005tgn\034;bi&|g.\003\003\002\036\005M!!E;oG\",7m[3e-\006\024\030.\0318dK\"I\021\021\0056\002\002\023\005\0231E\001\016aJ|G-^2u!J,g-\033=\026\005\005\025\002\003BA\024\003ci!!!\013\013\t\005-\022QF\001\005Y\006twM\003\002\0020\005!!.\031<b\023\021\t\031$!\013\003\rM#(/\0338h\021%\t9D[A\001\n\003\tI$\001\007qe>$Wo\031;Be&$\0300\006\002\002<A\0311\"!\020\n\007\005}bAA\002J]RD\021\"a\021k\003\003%\t!!\022\002\035A\024x\016Z;di\026cW-\\3oiR!\021qIA'!\rY\021\021J\005\004\003\0272!aA!os\"Q\021qJA!\003\003\005\r!a\017\002\007a$\023\007C\005\002T)\f\t\021\"\021\002V\005y\001O]8ek\016$\030\n^3sCR|'/\006\002\002XA)1*!\027\002H%\031\0211\f'\003\021%#XM]1u_JD\021\"a\030k\003\003%\t!!\031\002\021\r\fg.R9vC2$2aIA2\021)\ty%!\030\002\002\003\007\021q\t\005\n\003OR\027\021!C!\003S\n\001\002[1tQ\016{G-\032\013\003\003wA\021\"!\034k\003\003%\t%a\034\002\021Q|7\013\036:j]\036$\"!!\n\t\023\005M$.!A\005B\005U\024AB3rk\006d7\017F\002$\003oB!\"a\024\002r\005\005\t\031AA$\017%\tY\bAA\001\022\003\ti(\001\003Ti\006\024\bc\001\016\002\000\031A1\016AA\001\022\003\t\tiE\003\002\000\005\r\005\017\005\004\002\006\006-%H_\007\003\003\017S1!!#\007\003\035\021XO\034;j[\026LA!!$\002\b\n\t\022IY:ue\006\034GOR;oGRLwN\\\031\t\017=\ty\b\"\001\002\022R\021\021Q\020\005\013\003[\ny(!A\005F\005=\004\"C\030\002\000\005\005I\021QAL)\rQ\030\021\024\005\007i\006U\005\031\001\036\t\025\005u\025qPA\001\n\003\013y*A\004v]\006\004\b\017\\=\025\t\005\005\026q\025\t\005\027\005\r&(C\002\002&\032\021aa\0249uS>t\007\"CAU\0037\013\t\0211\001{\003\rAH\005\r\005\013\003[\013y(!A\005\n\005=\026a\003:fC\022\024Vm]8mm\026$\"!!-\021\t\005\035\0221W\005\005\003k\013IC\001\004PE*,7\r^\004\b\003s\003\001\022QA^\003\r)\005o\035\t\0045\005ufaBA`\001!\005\025\021\031\002\004\013B\0348#BA_35\004\bbB\b\002>\022\005\021Q\031\013\003\003wC\021\"IA_\021\013\007IQ\001\022\t\023\t\013i\f#A!B\033\031\003\002CA7\003{#\t%a\034\t\025\005\005\022QXA\001\n\003\n\031\003\003\006\0028\005u\026\021!C\001\003sA!\"a\021\002>\006\005I\021AAj)\021\t9%!6\t\025\005=\023\021[A\001\002\004\tY\004\003\006\002T\005u\026\021!C!\003+B!\"a\030\002>\006\005I\021AAn)\r\031\023Q\034\005\013\003\037\nI.!AA\002\005\035\003BCA4\003{\013\t\021\"\021\002j!Q\021QVA_\003\003%I!a,\007\r\005\025\b\001AAt\005\021iU\r^1\024\007\005\r\030\004\003\006\002l\006\r(\021!Q\001\ni\n!A]\031\t\017=\t\031\017\"\001\002pR!\021\021_Az!\rQ\0221\035\005\b\003W\fi\0171\001;\021!\t\0231\035b\001\n\013\021\003b\002\"\002d\002\006ia\t\005\007i\006\rH\021A;)\017\001\tiPa\001\003\bA\0311\"a@\n\007\t\005aA\001\006eKB\024XmY1uK\022\f#A!\002\0025QC\027n\035\021dY\006\0348\017I<jY2\004#-\032\021sK6|g/\0323\"\005\t%\021A\002\032/cAr\003\007")
/*    */ public abstract class Base {
/*    */   private volatile Alt$ Alt$module;
/*    */   
/*    */   private volatile Sequ$ Sequ$module;
/*    */   
/*    */   private volatile Star$ Star$module;
/*    */   
/*    */   private volatile Eps$ Eps$module;
/*    */   
/*    */   public abstract class RegExp {
/*    */     public abstract boolean isNullable();
/*    */     
/*    */     public RegExp(Base $outer) {}
/*    */   }
/*    */   
/*    */   private Alt$ Alt$lzycompute() {
/* 27 */     synchronized (this) {
/* 27 */       if (this.Alt$module == null)
/* 27 */         this.Alt$module = new Alt$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/regexp/Base}} */
/* 27 */       return this.Alt$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Alt$ Alt() {
/* 27 */     return (this.Alt$module == null) ? Alt$lzycompute() : this.Alt$module;
/*    */   }
/*    */   
/*    */   public class Alt$ {
/*    */     public Alt$(Base $outer) {}
/*    */     
/*    */     public Base.Alt apply(Seq<Base.RegExp> rs) {
/* 30 */       if (rs.size() < 2)
/* 30 */         throw new SyntaxError("need at least 2 branches in Alt"); 
/* 30 */       return 
/* 31 */         new Base.Alt(this.$outer, rs);
/*    */     }
/*    */     
/*    */     public Some<Seq<Base.RegExp>> unapplySeq(Base.Alt x) {
/* 34 */       return new Some(x.rs());
/*    */     }
/*    */     
/*    */     public class $anonfun$1 extends AbstractFunction1<Base.RegExp, Object> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final boolean apply(Base.RegExp x$1) {
/* 38 */         return x$1.isNullable();
/*    */       }
/*    */       
/*    */       public $anonfun$1(Base.Alt $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Alt extends RegExp {
/*    */     private final Seq<Base.RegExp> rs;
/*    */     
/*    */     private final boolean isNullable;
/*    */     
/*    */     public Seq<Base.RegExp> rs() {
/*    */       return this.rs;
/*    */     }
/*    */     
/*    */     public Alt(Base $outer, Seq<Base.RegExp> rs) {
/*    */       super($outer);
/* 38 */       this.isNullable = rs.exists((Function1)new $anonfun$1(this));
/*    */     }
/*    */     
/*    */     public final boolean isNullable() {
/* 38 */       return this.isNullable;
/*    */     }
/*    */     
/*    */     public class $anonfun$1 extends AbstractFunction1<Base.RegExp, Object> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final boolean apply(Base.RegExp x$1) {
/* 38 */         return x$1.isNullable();
/*    */       }
/*    */       
/*    */       public $anonfun$1(Base.Alt $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   private Sequ$ Sequ$lzycompute() {
/* 41 */     synchronized (this) {
/* 41 */       if (this.Sequ$module == null)
/* 41 */         this.Sequ$module = new Sequ$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/regexp/Base}} */
/* 41 */       return this.Sequ$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Sequ$ Sequ() {
/* 41 */     return (this.Sequ$module == null) ? Sequ$lzycompute() : this.Sequ$module;
/*    */   }
/*    */   
/*    */   public class Sequ$ {
/*    */     public Sequ$(Base $outer) {}
/*    */     
/*    */     public Base.RegExp apply(Seq<Base.RegExp> rs) {
/* 43 */       return rs.isEmpty() ? this.$outer.Eps() : new Base.Sequ(this.$outer, rs);
/*    */     }
/*    */     
/*    */     public Some<Seq<Base.RegExp>> unapplySeq(Base.Sequ x) {
/* 44 */       return new Some(x.rs());
/*    */     }
/*    */     
/*    */     public class $anonfun$2 extends AbstractFunction1<Base.RegExp, Object> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final boolean apply(Base.RegExp x$2) {
/* 48 */         return x$2.isNullable();
/*    */       }
/*    */       
/*    */       public $anonfun$2(Base.Sequ $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   public class Sequ extends RegExp {
/*    */     private final Seq<Base.RegExp> rs;
/*    */     
/*    */     private final boolean isNullable;
/*    */     
/*    */     public Seq<Base.RegExp> rs() {
/*    */       return this.rs;
/*    */     }
/*    */     
/*    */     public Sequ(Base $outer, Seq<Base.RegExp> rs) {
/*    */       super($outer);
/* 48 */       this.isNullable = rs.forall((Function1)new $anonfun$2(this));
/*    */     }
/*    */     
/*    */     public final boolean isNullable() {
/* 48 */       return this.isNullable;
/*    */     }
/*    */     
/*    */     public class $anonfun$2 extends AbstractFunction1<Base.RegExp, Object> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       public final boolean apply(Base.RegExp x$2) {
/* 48 */         return x$2.isNullable();
/*    */       }
/*    */       
/*    */       public $anonfun$2(Base.Sequ $outer) {}
/*    */     }
/*    */   }
/*    */   
/*    */   private Star$ Star$lzycompute() {
/* 51 */     synchronized (this) {
/* 51 */       if (this.Star$module == null)
/* 51 */         this.Star$module = new Star$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/regexp/Base}} */
/* 51 */       return this.Star$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Star$ Star() {
/* 51 */     return (this.Star$module == null) ? Star$lzycompute() : this.Star$module;
/*    */   }
/*    */   
/*    */   public class Star$ extends AbstractFunction1<RegExp, Star> implements Serializable {
/*    */     public final String toString() {
/* 51 */       return "Star";
/*    */     }
/*    */     
/*    */     public Base.Star apply(Base.RegExp r) {
/* 51 */       return new Base.Star(this.$outer, r);
/*    */     }
/*    */     
/*    */     public Option<Base.RegExp> unapply(Base.Star x$0) {
/* 51 */       return (x$0 == null) ? (Option<Base.RegExp>)scala.None$.MODULE$ : (Option<Base.RegExp>)new Some(x$0.r());
/*    */     }
/*    */     
/*    */     private Object readResolve() {
/* 51 */       return this.$outer.Star();
/*    */     }
/*    */     
/*    */     public Star$(Base $outer) {}
/*    */   }
/*    */   
/*    */   public class Star extends RegExp implements Product, Serializable {
/*    */     private final Base.RegExp r;
/*    */     
/*    */     private boolean isNullable;
/*    */     
/*    */     private volatile boolean bitmap$0;
/*    */     
/*    */     public Base.RegExp r() {
/* 51 */       return this.r;
/*    */     }
/*    */     
/*    */     public Star copy(Base.RegExp r) {
/* 51 */       return new Star(scala$util$regexp$Base$Star$$$outer(), r);
/*    */     }
/*    */     
/*    */     public Base.RegExp copy$default$1() {
/* 51 */       return r();
/*    */     }
/*    */     
/*    */     public String productPrefix() {
/* 51 */       return "Star";
/*    */     }
/*    */     
/*    */     public int productArity() {
/* 51 */       return 1;
/*    */     }
/*    */     
/*    */     public Object productElement(int x$1) {
/* 51 */       switch (x$1) {
/*    */         default:
/* 51 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */         case 0:
/*    */           break;
/*    */       } 
/* 51 */       return r();
/*    */     }
/*    */     
/*    */     public Iterator<Object> productIterator() {
/* 51 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object x$1) {
/* 51 */       return x$1 instanceof Star;
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 51 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 51 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object x$1) {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: if_acmpeq -> 89
/*    */       //   5: aload_1
/*    */       //   6: instanceof scala/util/regexp/Base$Star
/*    */       //   9: ifeq -> 31
/*    */       //   12: aload_1
/*    */       //   13: checkcast scala/util/regexp/Base$Star
/*    */       //   16: invokevirtual scala$util$regexp$Base$Star$$$outer : ()Lscala/util/regexp/Base;
/*    */       //   19: aload_0
/*    */       //   20: invokevirtual scala$util$regexp$Base$Star$$$outer : ()Lscala/util/regexp/Base;
/*    */       //   23: if_acmpne -> 31
/*    */       //   26: iconst_1
/*    */       //   27: istore_2
/*    */       //   28: goto -> 33
/*    */       //   31: iconst_0
/*    */       //   32: istore_2
/*    */       //   33: iload_2
/*    */       //   34: ifeq -> 93
/*    */       //   37: aload_1
/*    */       //   38: checkcast scala/util/regexp/Base$Star
/*    */       //   41: astore #4
/*    */       //   43: aload_0
/*    */       //   44: invokevirtual r : ()Lscala/util/regexp/Base$RegExp;
/*    */       //   47: aload #4
/*    */       //   49: invokevirtual r : ()Lscala/util/regexp/Base$RegExp;
/*    */       //   52: astore_3
/*    */       //   53: dup
/*    */       //   54: ifnonnull -> 65
/*    */       //   57: pop
/*    */       //   58: aload_3
/*    */       //   59: ifnull -> 72
/*    */       //   62: goto -> 85
/*    */       //   65: aload_3
/*    */       //   66: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   69: ifeq -> 85
/*    */       //   72: aload #4
/*    */       //   74: aload_0
/*    */       //   75: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */       //   78: ifeq -> 85
/*    */       //   81: iconst_1
/*    */       //   82: goto -> 86
/*    */       //   85: iconst_0
/*    */       //   86: ifeq -> 93
/*    */       //   89: iconst_1
/*    */       //   90: goto -> 94
/*    */       //   93: iconst_0
/*    */       //   94: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #51	-> 0
/*    */       //   #236	-> 26
/*    */       //   #51	-> 33
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	95	0	this	Lscala/util/regexp/Base$Star;
/*    */       //   0	95	1	x$1	Ljava/lang/Object;
/*    */     }
/*    */     
/*    */     public Star(Base $outer, Base.RegExp r) {
/* 51 */       super($outer);
/* 51 */       Product.class.$init$(this);
/*    */     }
/*    */     
/*    */     private boolean isNullable$lzycompute() {
/* 52 */       synchronized (this) {
/* 52 */         if (!this.bitmap$0) {
/* 52 */           this.isNullable = true;
/* 52 */           this.bitmap$0 = true;
/*    */         } 
/*    */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/util/regexp/Base}.Lscala/util/regexp/Base$Star;}} */
/* 52 */         return this.isNullable;
/*    */       } 
/*    */     }
/*    */     
/*    */     public final boolean isNullable() {
/* 52 */       return this.bitmap$0 ? this.isNullable : isNullable$lzycompute();
/*    */     }
/*    */   }
/*    */   
/*    */   private Eps$ Eps$lzycompute() {
/* 56 */     synchronized (this) {
/* 56 */       if (this.Eps$module == null)
/* 56 */         this.Eps$module = new Eps$(this); 
/*    */       /* monitor exit ThisExpression{ObjectType{scala/util/regexp/Base}} */
/* 56 */       return this.Eps$module;
/*    */     } 
/*    */   }
/*    */   
/*    */   public Eps$ Eps() {
/* 56 */     return (this.Eps$module == null) ? Eps$lzycompute() : this.Eps$module;
/*    */   }
/*    */   
/*    */   public class Eps$ extends RegExp implements Product, Serializable {
/*    */     private boolean isNullable;
/*    */     
/*    */     private volatile boolean bitmap$0;
/*    */     
/*    */     public String productPrefix() {
/* 56 */       return "Eps";
/*    */     }
/*    */     
/*    */     public int productArity() {
/* 56 */       return 0;
/*    */     }
/*    */     
/*    */     public Object productElement(int x$1) {
/* 56 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */     }
/*    */     
/*    */     public Iterator<Object> productIterator() {
/* 56 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object x$1) {
/* 56 */       return x$1 instanceof Eps$;
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 56 */       return 69896;
/*    */     }
/*    */     
/*    */     private Object readResolve() {
/* 56 */       return scala$util$regexp$Base$Eps$$$outer().Eps();
/*    */     }
/*    */     
/*    */     public Eps$(Base $outer) {
/* 56 */       super($outer);
/* 56 */       Product.class.$init$(this);
/*    */     }
/*    */     
/*    */     private boolean isNullable$lzycompute() {
/* 57 */       synchronized (this) {
/* 57 */         if (!this.bitmap$0) {
/* 57 */           this.isNullable = true;
/* 57 */           this.bitmap$0 = true;
/*    */         } 
/*    */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/util/regexp/Base}.Lscala/util/regexp/Base$Eps$;}} */
/* 57 */         return this.isNullable;
/*    */       } 
/*    */     }
/*    */     
/*    */     public final boolean isNullable() {
/* 57 */       return this.bitmap$0 ? this.isNullable : isNullable$lzycompute();
/*    */     }
/*    */     
/*    */     public String toString() {
/* 58 */       return "Eps";
/*    */     }
/*    */   }
/*    */   
/*    */   public class Meta extends RegExp {
/*    */     private final Base.RegExp r1;
/*    */     
/*    */     private final boolean isNullable;
/*    */     
/*    */     public Meta(Base $outer, Base.RegExp r1) {
/* 62 */       super($outer);
/* 63 */       this.isNullable = r1.isNullable();
/*    */     }
/*    */     
/*    */     public final boolean isNullable() {
/* 63 */       return this.isNullable;
/*    */     }
/*    */     
/*    */     public Base.RegExp r() {
/* 64 */       return this.r1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\regexp\Base.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */