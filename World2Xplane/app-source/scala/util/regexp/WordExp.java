/*     */ package scala.util.regexp;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005%g!B\001\003\003\003I!aB,pe\022,\005\020\035\006\003\007\021\taA]3hKb\004(BA\003\007\003\021)H/\0337\013\003\035\tQa]2bY\006\034\001a\005\002\001\025A\0211\002D\007\002\005%\021QB\001\002\005\005\006\034X\rC\003\020\001\021\005\001#\001\004=S:LGO\020\013\002#A\0211\002\001\004\006'\001\t\t\001\006\002\006\031\006\024W\r\\\n\003%U\001\"AF\f\016\003\031I!\001\007\004\003\r\005s\027PU3g\021\025y!\003\"\001\033)\005Y\002C\001\017\023\033\005\001A!\002\020\001\005\003y\"\001C0sK\036,\007\020\035+\022\005\001\032\003C\001\f\"\023\t\021cAA\004O_RD\027N\\4\021\005q!\023BA\023\r\005\031\021VmZ#ya\022)q\005\001B\001Q\t9q\f\\1cK2$\026C\001\021\034\r\021Q\003\001Q\026\003\r1+G\017^3s'\021I3\005L\030\021\005Yi\023B\001\030\007\005\035\001&o\0343vGR\004\"A\006\031\n\005E2!\001D*fe&\fG.\033>bE2,\007\002C\032*\005+\007I\021\001\033\002\003\005,\022!\016\t\0039\031B\001bN\025\003\022\003\006I!N\001\003C\002BQaD\025\005\002e\"\"AO\036\021\005qI\003\"B\0329\001\004)\004\002C\037*\021\013\007IQ\001 \002\025%\034h*\0367mC\ndW-F\001@!\t1\002)\003\002B\r\t9!i\\8mK\006t\007\002C\"*\021\003\005\013UB \002\027%\034h*\0367mC\ndW\r\t\005\b\013&\002\r\021\"\001G\003\r\001xn]\013\002\017B\021a\003S\005\003\023\032\0211!\0238u\021\035Y\025\0061A\005\0021\013q\001]8t?\022*\027\017\006\002N!B\021aCT\005\003\037\032\021A!\0268ji\"9\021KSA\001\002\0049\025a\001=%c!11+\013Q!\n\035\013A\001]8tA!9Q+KA\001\n\0031\026\001B2paf$\"AO,\t\017M\"\006\023!a\001k!9\021,KI\001\n\003Q\026AD2paf$C-\0324bk2$H%M\013\0027*\022Q\007X\026\002;B\021alY\007\002?*\021\001-Y\001\nk:\034\007.Z2lK\022T!A\031\004\002\025\005tgn\034;bi&|g.\003\002e?\n\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\017\031L\023\021!C!O\006i\001O]8ek\016$\bK]3gSb,\022\001\033\t\003S:l\021A\033\006\003W2\fA\001\\1oO*\tQ.\001\003kCZ\f\027BA8k\005\031\031FO]5oO\"9\021/KA\001\n\0031\025\001\0049s_\022,8\r^!sSRL\bbB:*\003\003%\t\001^\001\017aJ|G-^2u\0132,W.\0328u)\t)\b\020\005\002\027m&\021qO\002\002\004\003:L\bbB)s\003\003\005\ra\022\005\bu&\n\t\021\"\021|\003=\001(o\0343vGRLE/\032:bi>\024X#\001?\021\tu\f\t!^\007\002}*\021qPB\001\013G>dG.Z2uS>t\027bAA\002}\nA\021\n^3sCR|'\017C\005\002\b%\n\t\021\"\001\002\n\005A1-\0318FcV\fG\016F\002@\003\027A\001\"UA\003\003\003\005\r!\036\005\n\003\037I\023\021!C!\003#\t\001\002[1tQ\016{G-\032\013\002\017\"I\021QC\025\002\002\023\005\023qC\001\ti>\034FO]5oOR\t\001\016C\005\002\034%\n\t\021\"\021\002\036\0051Q-];bYN$2aPA\020\021!\t\026\021DA\001\002\004)x!CA\022\001\005\005\t\022AA\023\003\031aU\r\036;feB\031A$a\n\007\021)\002\021\021!E\001\003S\031R!a\n\002,=\002b!!\f\0024URTBAA\030\025\r\t\tDB\001\beVtG/[7f\023\021\t)$a\f\003#\005\0237\017\036:bGR4UO\\2uS>t\027\007C\004\020\003O!\t!!\017\025\005\005\025\002BCA\013\003O\t\t\021\"\022\002\030!Q\021qHA\024\003\003%\t)!\021\002\013\005\004\b\017\\=\025\007i\n\031\005\003\0044\003{\001\r!\016\005\013\003\017\n9#!A\005\002\006%\023aB;oCB\004H.\037\013\005\003\027\n\t\006\005\003\027\003\033*\024bAA(\r\t1q\n\035;j_:D\021\"a\025\002F\005\005\t\031\001\036\002\007a$\003\007\003\006\002X\005\035\022\021!C\005\0033\n1B]3bIJ+7o\0347wKR\021\0211\f\t\004S\006u\023bAA0U\n1qJ\0316fGR4a!a\031\001\001\006\025$\001C,jY\022\034\027M\0353\024\013\005\0054\005L\030\t\017=\t\t\007\"\001\002jQ\021\0211\016\t\0049\005\005\004\"C\037\002b!\025\r\021\"\002?\021%\031\025\021\rE\001B\0036q\b\003\005F\003C\002\r\021\"\001G\021%Y\025\021\ra\001\n\003\t)\bF\002N\003oB\001\"UA:\003\003\005\ra\022\005\b'\006\005\004\025)\003H\021%)\026\021MA\001\n\003\tI\007\003\005g\003C\n\t\021\"\021h\021!\t\030\021MA\001\n\0031\005\"C:\002b\005\005I\021AAB)\r)\030Q\021\005\t#\006\005\025\021!a\001\017\"A!0!\031\002\002\023\0053\020\003\006\002\b\005\005\024\021!C\001\003\027#2aPAG\021!\t\026\021RA\001\002\004)\bBCA\b\003C\n\t\021\"\021\002\022!Q\021QCA1\003\003%\t%a\006\t\025\005m\021\021MA\001\n\003\n)\nF\002@\003/C\001\"UAJ\003\003\005\r!^\004\n\0037\003\021\021!E\001\003;\013\001bV5mI\016\f'\017\032\t\0049\005}e!CA2\001\005\005\t\022AAQ'\025\ty*a)0!\031\ti#!*\002l%!\021qUA\030\005E\t%m\035;sC\016$h)\0368di&|g\016\r\005\b\037\005}E\021AAV)\t\ti\n\003\006\002\026\005}\025\021!C#\003/A!\"a\020\002 \006\005I\021QA5\021)\t9%a(\002\002\023\005\0251\027\013\004\005U\006BCA*\003c\013\t\0211\001\002l!Q\021qKAP\003\003%I!!\027)\017\001\tY,!1\002FB\031a#!0\n\007\005}fA\001\006eKB\024XmY1uK\022\f#!a1\0025QC\027n\035\021dY\006\0348\017I<jY2\004#-\032\021sK6|g/\0323\"\005\005\035\027A\002\032/cAr\003\007")
/*     */ public abstract class WordExp extends Base {
/*     */   private volatile Letter$ Letter$module;
/*     */   
/*     */   private volatile Wildcard$ Wildcard$module;
/*     */   
/*     */   public abstract class Label {
/*     */     public Label(WordExp $outer) {}
/*     */   }
/*     */   
/*     */   private Letter$ Letter$lzycompute() {
/*  49 */     synchronized (this) {
/*  49 */       if (this.Letter$module == null)
/*  49 */         this.Letter$module = new Letter$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/util/regexp/WordExp}} */
/*  49 */       return this.Letter$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Letter$ Letter() {
/*  49 */     return (this.Letter$module == null) ? Letter$lzycompute() : this.Letter$module;
/*     */   }
/*     */   
/*     */   public class Letter$ extends AbstractFunction1<Label, Letter> implements Serializable {
/*     */     public final String toString() {
/*  49 */       return "Letter";
/*     */     }
/*     */     
/*     */     public WordExp.Letter apply(WordExp.Label a) {
/*  49 */       return new WordExp.Letter(this.$outer, a);
/*     */     }
/*     */     
/*     */     public Option<WordExp.Label> unapply(WordExp.Letter x$0) {
/*  49 */       return (x$0 == null) ? (Option<WordExp.Label>)scala.None$.MODULE$ : (Option<WordExp.Label>)new Some(x$0.a());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  49 */       return this.$outer.Letter();
/*     */     }
/*     */     
/*     */     public Letter$(WordExp $outer) {}
/*     */   }
/*     */   
/*     */   public class Letter extends Base.RegExp implements Product, Serializable {
/*     */     private final WordExp.Label a;
/*     */     
/*     */     private boolean isNullable;
/*     */     
/*     */     private int pos;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public WordExp.Label a() {
/*  49 */       return this.a;
/*     */     }
/*     */     
/*     */     public Letter copy(WordExp.Label a) {
/*  49 */       return new Letter(scala$util$regexp$WordExp$Letter$$$outer(), a);
/*     */     }
/*     */     
/*     */     public WordExp.Label copy$default$1() {
/*  49 */       return a();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  49 */       return "Letter";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  49 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  49 */       switch (x$1) {
/*     */         default:
/*  49 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  49 */       return a();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  49 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  49 */       return x$1 instanceof Letter;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  49 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  49 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 89
/*     */       //   5: aload_1
/*     */       //   6: instanceof scala/util/regexp/WordExp$Letter
/*     */       //   9: ifeq -> 31
/*     */       //   12: aload_1
/*     */       //   13: checkcast scala/util/regexp/WordExp$Letter
/*     */       //   16: invokevirtual scala$util$regexp$WordExp$Letter$$$outer : ()Lscala/util/regexp/WordExp;
/*     */       //   19: aload_0
/*     */       //   20: invokevirtual scala$util$regexp$WordExp$Letter$$$outer : ()Lscala/util/regexp/WordExp;
/*     */       //   23: if_acmpne -> 31
/*     */       //   26: iconst_1
/*     */       //   27: istore_2
/*     */       //   28: goto -> 33
/*     */       //   31: iconst_0
/*     */       //   32: istore_2
/*     */       //   33: iload_2
/*     */       //   34: ifeq -> 93
/*     */       //   37: aload_1
/*     */       //   38: checkcast scala/util/regexp/WordExp$Letter
/*     */       //   41: astore #4
/*     */       //   43: aload_0
/*     */       //   44: invokevirtual a : ()Lscala/util/regexp/WordExp$Label;
/*     */       //   47: aload #4
/*     */       //   49: invokevirtual a : ()Lscala/util/regexp/WordExp$Label;
/*     */       //   52: astore_3
/*     */       //   53: dup
/*     */       //   54: ifnonnull -> 65
/*     */       //   57: pop
/*     */       //   58: aload_3
/*     */       //   59: ifnull -> 72
/*     */       //   62: goto -> 85
/*     */       //   65: aload_3
/*     */       //   66: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   69: ifeq -> 85
/*     */       //   72: aload #4
/*     */       //   74: aload_0
/*     */       //   75: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   78: ifeq -> 85
/*     */       //   81: iconst_1
/*     */       //   82: goto -> 86
/*     */       //   85: iconst_0
/*     */       //   86: ifeq -> 93
/*     */       //   89: iconst_1
/*     */       //   90: goto -> 94
/*     */       //   93: iconst_0
/*     */       //   94: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #49	-> 0
/*     */       //   #236	-> 26
/*     */       //   #49	-> 33
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	95	0	this	Lscala/util/regexp/WordExp$Letter;
/*     */       //   0	95	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public Letter(WordExp $outer, WordExp.Label a) {
/*  49 */       super($outer);
/*  49 */       Product.class.$init$(this);
/*  51 */       this.pos = -1;
/*     */     }
/*     */     
/*     */     private boolean isNullable$lzycompute() {
/*     */       synchronized (this) {
/*     */         if (!this.bitmap$0) {
/*     */           this.isNullable = false;
/*     */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/util/regexp/WordExp}.Lscala/util/regexp/WordExp$Letter;}} */
/*     */         return this.isNullable;
/*     */       } 
/*     */     }
/*     */     
/*     */     public final boolean isNullable() {
/*     */       return this.bitmap$0 ? this.isNullable : isNullable$lzycompute();
/*     */     }
/*     */     
/*     */     public int pos() {
/*  51 */       return this.pos;
/*     */     }
/*     */     
/*     */     public void pos_$eq(int x$1) {
/*  51 */       this.pos = x$1;
/*     */     }
/*     */   }
/*     */   
/*     */   private Wildcard$ Wildcard$lzycompute() {
/*  54 */     synchronized (this) {
/*  54 */       if (this.Wildcard$module == null)
/*  54 */         this.Wildcard$module = new Wildcard$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/util/regexp/WordExp}} */
/*  54 */       return this.Wildcard$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Wildcard$ Wildcard() {
/*  54 */     return (this.Wildcard$module == null) ? Wildcard$lzycompute() : this.Wildcard$module;
/*     */   }
/*     */   
/*     */   public class Wildcard$ extends AbstractFunction0<Wildcard> implements Serializable {
/*     */     public final String toString() {
/*  54 */       return "Wildcard";
/*     */     }
/*     */     
/*     */     public WordExp.Wildcard apply() {
/*  54 */       return new WordExp.Wildcard(this.$outer);
/*     */     }
/*     */     
/*     */     public boolean unapply(WordExp.Wildcard x$0) {
/*  54 */       return !(x$0 == null);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  54 */       return this.$outer.Wildcard();
/*     */     }
/*     */     
/*     */     public Wildcard$(WordExp $outer) {}
/*     */   }
/*     */   
/*     */   public class Wildcard extends Base.RegExp implements Product, Serializable {
/*     */     private boolean isNullable;
/*     */     
/*     */     private int pos;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public Wildcard copy() {
/*  54 */       return new Wildcard(scala$util$regexp$WordExp$Wildcard$$$outer());
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  54 */       return "Wildcard";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  54 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  54 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  54 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  54 */       return x$1 instanceof Wildcard;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  54 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  54 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       boolean bool;
/*  54 */       if (x$1 instanceof Wildcard && ((Wildcard)x$1).scala$util$regexp$WordExp$Wildcard$$$outer() == scala$util$regexp$WordExp$Wildcard$$$outer()) {
/* 236 */         bool = true;
/*     */       } else {
/* 236 */         bool = false;
/*     */       } 
/*     */       return (bool && ((Wildcard)x$1).canEqual(this));
/*     */     }
/*     */     
/*     */     public Wildcard(WordExp $outer) {
/*     */       super($outer);
/*     */       Product.class.$init$(this);
/*     */       this.pos = -1;
/*     */     }
/*     */     
/*     */     private boolean isNullable$lzycompute() {
/*     */       synchronized (this) {
/*     */         if (!this.bitmap$0) {
/*     */           this.isNullable = false;
/*     */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/util/regexp/WordExp}.Lscala/util/regexp/WordExp$Wildcard;}} */
/*     */         return this.isNullable;
/*     */       } 
/*     */     }
/*     */     
/*     */     public final boolean isNullable() {
/*     */       return this.bitmap$0 ? this.isNullable : isNullable$lzycompute();
/*     */     }
/*     */     
/*     */     public int pos() {
/*     */       return this.pos;
/*     */     }
/*     */     
/*     */     public void pos_$eq(int x$1) {
/*     */       this.pos = x$1;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\regexp\WordExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */