/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Option;
/*    */ import scala.Product;
/*    */ import scala.Serializable;
/*    */ import scala.Some;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.Set;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractPartialFunction;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.util.automata.WordBerrySethi;
/*    */ import scala.util.regexp.Base;
/*    */ import scala.util.regexp.WordExp;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t]q!B\001\003\021\003I\021\001D\"p]R,g\016^'pI\026d'BA\002\005\003\r!G\017\032\006\003\013\031\t1\001_7m\025\0059\021!B:dC2\f7\001\001\t\003\025-i\021A\001\004\006\031\tA\t!\004\002\r\007>tG/\0328u\033>$W\r\\\n\003\0279\001\"a\004\013\016\003AQ!!\005\n\002\rI,w-\032=q\025\t\031b!\001\003vi&d\027BA\013\021\005\0359vN\0353FqBDQaF\006\005\002a\ta\001P5oSRtD#A\005\006\tiY\001a\007\002\b?2\f'-\0327U!\taR$D\001\f\r\021q2\002Q\020\003\021\025cW-\034(b[\026\034B!\b\021$OA\021A$I\005\003EQ\021Q\001T1cK2\004\"\001J\023\016\003\031I!A\n\004\003\017A\023x\016Z;diB\021A\005K\005\003S\031\021AbU3sS\006d\027N_1cY\026D\001bK\017\003\026\004%\t\001L\001\005]\006lW-F\001.!\tq\023G\004\002%_%\021\001GB\001\007!J,G-\0324\n\005I\032$AB*ue&twM\003\0021\r!AQ'\bB\tB\003%Q&A\003oC6,\007\005C\003\030;\021\005q\007\006\002\034q!)1F\016a\001[!)!(\bC!w\005AAo\\*ue&tw\rF\001.\021\035iT$!A\005\002y\nAaY8qsR\0211d\020\005\bWq\002\n\0211\001.\021\035\tU$%A\005\002\t\013abY8qs\022\"WMZ1vYR$\023'F\001DU\tiCiK\001F!\t15*D\001H\025\tA\025*A\005v]\016DWmY6fI*\021!JB\001\013C:tw\016^1uS>t\027B\001'H\005E)hn\0315fG.,GMV1sS\006t7-\032\005\b\035v\t\t\021\"\021P\0035\001(o\0343vGR\004&/\0324jqV\t\001\013\005\002R-6\t!K\003\002T)\006!A.\0318h\025\005)\026\001\0026bm\006L!A\r*\t\017ak\022\021!C\0013\006a\001O]8ek\016$\030I]5usV\t!\f\005\002%7&\021AL\002\002\004\023:$\bb\0020\036\003\003%\taX\001\017aJ|G-^2u\0132,W.\0328u)\t\0017\r\005\002%C&\021!M\002\002\004\003:L\bb\0023^\003\003\005\rAW\001\004q\022\n\004b\0024\036\003\003%\teZ\001\020aJ|G-^2u\023R,'/\031;peV\t\001\016E\002jY\002l\021A\033\006\003W\032\t!bY8mY\026\034G/[8o\023\ti'N\001\005Ji\026\024\030\r^8s\021\035yW$!A\005\002A\f\001bY1o\013F,\030\r\034\013\003cR\004\"\001\n:\n\005M4!a\002\"p_2,\027M\034\005\bI:\f\t\0211\001a\021\0351X$!A\005B]\f\001\002[1tQ\016{G-\032\013\0025\"9\0210HA\001\n\003R\030AB3rk\006d7\017\006\002rw\"9A\r_A\001\002\004\001W\001B?\f\001y\024\001b\030:fO\026D\b\017\026\t\0039}LA!!\001\002\004\t1!+Z4FqBL1!!\002\021\005\021\021\025m]3\b\017\005%1\002#\001\002\f\005QAK]1og2\fGo\034:\021\007q\tiAB\004\002\020-A\t!!\005\003\025Q\023\030M\\:mCR|'o\005\003\002\016\005M\001\003BA\013\0037i!!a\006\013\007\005e!#\001\005bkR|W.\031;b\023\021\ti\"a\006\003\035]{'\017\032\"feJL8+\032;iS\"9q#!\004\005\002\005\005BCAA\006\021%\031\026Q\002b\001\n\003\n)#F\001\035\021!\tI#!\004!\002\023a\022!\0027b]\036\004s!CA\027\027\005\005\t\022AA\030\003!)E.Z7OC6,\007c\001\017\0022\031AadCA\001\022\003\t\031dE\003\0022\005Ur\005\005\004\0028\005uRfG\007\003\003sQ1!a\017\007\003\035\021XO\034;j[\026LA!a\020\002:\t\t\022IY:ue\006\034GOR;oGRLwN\\\031\t\017]\t\t\004\"\001\002DQ\021\021q\006\005\nu\005E\022\021!C#\003\017\"\022\001\025\005\013\003\027\n\t$!A\005\002\0065\023!B1qa2LHcA\016\002P!11&!\023A\0025B!\"a\025\0022\005\005I\021QA+\003\035)h.\0319qYf$B!a\026\002^A!A%!\027.\023\r\tYF\002\002\007\037B$\030n\0348\t\023\005}\023\021KA\001\002\004Y\022a\001=%a!Q\0211MA\031\003\003%I!!\032\002\027I,\027\r\032*fg>dg/\032\013\003\003O\0022!UA5\023\r\tYG\025\002\007\037\nTWm\031;\t\017\005=4\002\"\001\002r\0059\021n]'jq\026$GcA9\002t!A\021QOA7\001\004\t9(\001\002d[B\031!\"!\037\007\r1\021\021\021EA>'\021\tI(! \021\007\021\ny(C\002\002\002\032\021a!\0218z%\0264\007bB\f\002z\021\005\021Q\021\013\003\003oBaAOA=\t\003Z\004\002CAF\003s2\t!!$\002\027\t,\030\016\0343TiJLgn\032\013\005\003\037\0139\013\005\003\002\022\006\005f\002BAJ\003;sA!!&\002\0346\021\021q\023\006\004\0033C\021A\002\037s_>$h(C\001\b\023\r\tyJB\001\ba\006\0347.Y4f\023\021\t\031+!*\003\033M#(/\0338h\005VLG\016Z3s\025\r\tyJ\002\005\t\003S\013I\t1\001\002\020\006\0211OY\025\013\003s\ni+!-\0026\006e&bAAX\005\005\031\021IT-\n\007\005M&AA\bE\r\006\033uN\034;f]Rlu\016Z3m\025\r\t9LA\001\006\0136\003F+\027\006\004\003w\023\021A\002)D\t\006#\026\tC\004\002@.!\t!!1\002\031\r|g\016^1j]N$V\r\037;\025\007E\f\031\r\003\005\002v\005u\006\031AA<\021\035\t9m\003C\001\003\023\fQ\001]1sg\026$B!a\036\002L\"9\021QZAc\001\004i\023!A:\t\017\005E7\002\"\001\002T\006Iq-\032;MC\n,Gn\035\013\005\003+\fY\016\005\003/\003/l\023bAAmg\t\0311+\032;\t\017\005u\027q\032a\001}\006\t!\017C\004\002\f.!\t!!9\025\0075\n\031\017C\004\002^\006}\007\031\001@\t\017\005-5\002\"\003\002hRA\021\021^Ax\003s\fY\020E\002%\003WL1!!<\007\005\021)f.\033;\t\021\005E\030Q\035a\001\003g\f!A]:\021\013\005E\025Q\037@\n\t\005]\030Q\025\002\004'\026\f\b\002CAU\003K\004\r!a$\t\021\005u\030Q\035a\001\003\f1a]3q!\r!#\021A\005\004\005\0071!\001B\"iCJDq!a#\f\t\003\0219\001\006\004\002\020\n%!Q\002\005\t\005\027\021)\0011\001\002x\005\t1\r\003\005\002*\n\025\001\031AAH\021\035\tYi\003C\001\005#!b!a$\003\024\tU\001bBAo\005\037\001\rA \005\t\003S\023y\0011\001\002\020\002")
/*    */ public abstract class ContentModel {
/*    */   public static Set<String> getLabels(Base.RegExp paramRegExp) {
/*    */     return ContentModel$.MODULE$.getLabels(paramRegExp);
/*    */   }
/*    */   
/*    */   public static ContentModel parse(String paramString) {
/*    */     return ContentModel$.MODULE$.parse(paramString);
/*    */   }
/*    */   
/*    */   public static boolean containsText(ContentModel paramContentModel) {
/*    */     return ContentModel$.MODULE$.containsText(paramContentModel);
/*    */   }
/*    */   
/*    */   public static boolean isMixed(ContentModel paramContentModel) {
/*    */     return ContentModel$.MODULE$.isMixed(paramContentModel);
/*    */   }
/*    */   
/*    */   public static scala.util.regexp.WordExp$Wildcard$ Wildcard() {
/*    */     return ContentModel$.MODULE$.Wildcard();
/*    */   }
/*    */   
/*    */   public static scala.util.regexp.WordExp$Letter$ Letter() {
/*    */     return ContentModel$.MODULE$.Letter();
/*    */   }
/*    */   
/*    */   public static scala.util.regexp.Base$Star$ Star() {
/*    */     return ContentModel$.MODULE$.Star();
/*    */   }
/*    */   
/*    */   public static scala.util.regexp.Base$Sequ$ Sequ() {
/*    */     return ContentModel$.MODULE$.Sequ();
/*    */   }
/*    */   
/*    */   public static scala.util.regexp.Base$Alt$ Alt() {
/*    */     return ContentModel$.MODULE$.Alt();
/*    */   }
/*    */   
/*    */   public static class Translator$ extends WordBerrySethi {
/*    */     public static final Translator$ MODULE$;
/*    */     
/*    */     private final ContentModel$ lang;
/*    */     
/*    */     public Translator$() {
/* 23 */       MODULE$ = this;
/* 24 */       this.lang = ContentModel$.MODULE$;
/*    */     }
/*    */     
/*    */     public ContentModel$ lang() {
/* 24 */       return this.lang;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ElemName extends WordExp.Label implements Product, Serializable {
/*    */     private final String name;
/*    */     
/*    */     public String name() {
/* 27 */       return this.name;
/*    */     }
/*    */     
/*    */     public ElemName copy(String name) {
/* 27 */       return new ElemName(name);
/*    */     }
/*    */     
/*    */     public String copy$default$1() {
/* 27 */       return name();
/*    */     }
/*    */     
/*    */     public String productPrefix() {
/* 27 */       return "ElemName";
/*    */     }
/*    */     
/*    */     public int productArity() {
/* 27 */       return 1;
/*    */     }
/*    */     
/*    */     public Object productElement(int x$1) {
/* 27 */       switch (x$1) {
/*    */         default:
/* 27 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*    */         case 0:
/*    */           break;
/*    */       } 
/* 27 */       return name();
/*    */     }
/*    */     
/*    */     public Iterator<Object> productIterator() {
/* 27 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object x$1) {
/* 27 */       return x$1 instanceof ElemName;
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 27 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object x$1) {
/*    */       // Byte code:
/*    */       //   0: aload_0
/*    */       //   1: aload_1
/*    */       //   2: if_acmpeq -> 75
/*    */       //   5: aload_1
/*    */       //   6: instanceof scala/xml/dtd/ContentModel$ElemName
/*    */       //   9: ifeq -> 17
/*    */       //   12: iconst_1
/*    */       //   13: istore_2
/*    */       //   14: goto -> 19
/*    */       //   17: iconst_0
/*    */       //   18: istore_2
/*    */       //   19: iload_2
/*    */       //   20: ifeq -> 79
/*    */       //   23: aload_1
/*    */       //   24: checkcast scala/xml/dtd/ContentModel$ElemName
/*    */       //   27: astore #4
/*    */       //   29: aload_0
/*    */       //   30: invokevirtual name : ()Ljava/lang/String;
/*    */       //   33: aload #4
/*    */       //   35: invokevirtual name : ()Ljava/lang/String;
/*    */       //   38: astore_3
/*    */       //   39: dup
/*    */       //   40: ifnonnull -> 51
/*    */       //   43: pop
/*    */       //   44: aload_3
/*    */       //   45: ifnull -> 58
/*    */       //   48: goto -> 71
/*    */       //   51: aload_3
/*    */       //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */       //   55: ifeq -> 71
/*    */       //   58: aload #4
/*    */       //   60: aload_0
/*    */       //   61: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*    */       //   64: ifeq -> 71
/*    */       //   67: iconst_1
/*    */       //   68: goto -> 72
/*    */       //   71: iconst_0
/*    */       //   72: ifeq -> 79
/*    */       //   75: iconst_1
/*    */       //   76: goto -> 80
/*    */       //   79: iconst_0
/*    */       //   80: ireturn
/*    */       // Line number table:
/*    */       //   Java source line number -> byte code offset
/*    */       //   #27	-> 0
/*    */       //   #236	-> 12
/*    */       //   #27	-> 19
/*    */       // Local variable table:
/*    */       //   start	length	slot	name	descriptor
/*    */       //   0	81	0	this	Lscala/xml/dtd/ContentModel$ElemName;
/*    */       //   0	81	1	x$1	Ljava/lang/Object;
/*    */     }
/*    */     
/*    */     public ElemName(String name) {
/* 27 */       super(ContentModel$.MODULE$);
/* 27 */       Product.class.$init$(this);
/*    */     }
/*    */     
/*    */     public String toString() {
/* 28 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 28 */       return (new StringOps("ElemName(\"%s\")")).format((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { name() }));
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ElemName$ extends AbstractFunction1<String, ElemName> implements Serializable {
/*    */     public static final ElemName$ MODULE$;
/*    */     
/*    */     public final String toString() {
/*    */       return "ElemName";
/*    */     }
/*    */     
/*    */     public ContentModel.ElemName apply(String name) {
/*    */       return new ContentModel.ElemName(name);
/*    */     }
/*    */     
/*    */     public Option<String> unapply(ContentModel.ElemName x$0) {
/*    */       return (x$0 == null) ? (Option<String>)scala.None$.MODULE$ : (Option<String>)new Some(x$0.name());
/*    */     }
/*    */     
/*    */     private Object readResolve() {
/*    */       return MODULE$;
/*    */     }
/*    */     
/*    */     public ElemName$() {
/*    */       MODULE$ = this;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ContentModel$$anonfun$isMixed$1 extends AbstractPartialFunction.mcZL.sp<ContentModel> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final <A1 extends ContentModel, B1> B1 applyOrElse(ContentModel x1, Function1 default) {
/*    */       Object object;
/* 31 */       if (x1 instanceof MIXED) {
/* 31 */         object = BoxesRunTime.boxToBoolean(true);
/*    */       } else {
/* 31 */         object = default.apply(x1);
/*    */       } 
/* 31 */       return (B1)object;
/*    */     }
/*    */     
/*    */     public final boolean isDefinedAt(ContentModel x1) {
/*    */       boolean bool;
/* 31 */       if (x1 instanceof MIXED) {
/* 31 */         bool = true;
/*    */       } else {
/* 31 */         bool = false;
/*    */       } 
/* 31 */       return bool;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ContentModel$$anonfun$scala$xml$dtd$ContentModel$$traverse$1$1 extends AbstractFunction1<Base.RegExp, Set<String>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Set<String> apply(Base.RegExp r) {
/* 39 */       return ContentModel$.MODULE$.scala$xml$dtd$ContentModel$$traverse$1(r);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ContentModel$$anonfun$scala$xml$dtd$ContentModel$$traverse$1$2 extends AbstractFunction1<Base.RegExp, Set<String>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Set<String> apply(Base.RegExp r) {
/* 40 */       return ContentModel$.MODULE$.scala$xml$dtd$ContentModel$$traverse$1(r);
/*    */     }
/*    */   }
/*    */   
/*    */   public static class ContentModel$$anonfun$buildString$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Base.RegExp r$1;
/*    */     
/*    */     public final void apply(StringBuilder x$1) {
/* 46 */       ContentModel$.MODULE$.buildString(this.r$1, x$1);
/*    */     }
/*    */     
/*    */     public ContentModel$$anonfun$buildString$1(Base.RegExp r$1) {}
/*    */   }
/*    */   
/*    */   public static class ContentModel$$anonfun$buildString$2 extends AbstractFunction1<Base.RegExp, StringBuilder> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final StringBuilder sb$1;
/*    */     
/*    */     private final char sep$1;
/*    */     
/*    */     public ContentModel$$anonfun$buildString$2(StringBuilder sb$1, char sep$1) {}
/*    */     
/*    */     public final StringBuilder apply(Base.RegExp z) {
/* 52 */       this.sb$1.append(this.sep$1);
/* 53 */       return ContentModel$.MODULE$.buildString(z, this.sb$1);
/*    */     }
/*    */   }
/*    */   
/*    */   public String toString() {
/* 82 */     return scala.xml.Utility$.MODULE$.sbToString((Function1)new ContentModel$$anonfun$toString$1(this));
/*    */   }
/*    */   
/*    */   public abstract StringBuilder buildString(StringBuilder paramStringBuilder);
/*    */   
/*    */   public class ContentModel$$anonfun$toString$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(StringBuilder sb) {
/* 82 */       this.$outer.buildString(sb);
/*    */     }
/*    */     
/*    */     public ContentModel$$anonfun$toString$1(ContentModel $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\ContentModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */