/*     */ package scala.xml.parsing;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.BufferedIterator;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.CharRef;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tub\001C\001\003!\003\r\tA\002\005\003%5\013'o[;q!\006\0248/\032:D_6lwN\034\006\003\007\021\tq\001]1sg&twM\003\002\006\r\005\031\0010\0347\013\003\035\tQa]2bY\006\0342\001A\005\016!\tQ1\"D\001\007\023\taaA\001\004B]f\024VM\032\t\003\035=i\021AA\005\003!\t\021!\002V8lK:$Vm\035;t\021\025\021\002\001\"\001\025\003\031!\023N\\5uI\r\001A#A\013\021\005)1\022BA\f\007\005\021)f.\033;\t\013e\001A\021\003\016\002\027Ut'/Z1dQ\006\024G.Z\013\0027A\021!\002H\005\003;\031\021qAT8uQ&tw\rB\003 \001\t\005\001EA\005J]B,H\017V=qKF\0211$\t\t\003\025\tJ!a\t\004\003\007\005s\027\020B\003&\001\t\005\001E\001\007Q_NLG/[8o)f\004X\rB\003(\001\t\005\001EA\006FY\026lWM\034;UsB,G!B\025\001\005\003\001#!\004(b[\026\034\b/Y2f)f\004X\rB\003,\001\t\005\001E\001\bBiR\024\030NY;uKN$\026\020]3\t\0135\002a\021\001\030\002\0315\\\027\t\036;sS\n,H/Z:\025\007=\n$\b\005\0021U5\t\001\001C\0033Y\001\0071'\001\003oC6,\007C\001\0338\035\tQQ'\003\0027\r\0051\001K]3eK\032L!\001O\035\003\rM#(/\0338h\025\t1d\001C\003<Y\001\007A(\001\004qg\016|\007/\032\t\003a!BQA\020\001\007\002}\n1\"\\6Qe>\034\027J\\:ueR!\001)\021#F!\t\001d\005C\003C{\001\0071)\001\005q_NLG/[8o!\t\001D\005C\0033{\001\0071\007C\003G{\001\0071'\001\003uKb$\b\"\002%\001\t#I\025\001\002=UC\036$\"AS'\021\t)Y5gL\005\003\031\032\021a\001V;qY\026\024\004\"B\036H\001\004a\004\"B(\001\t\003\001\026A\003=Qe>\034\027J\\:ueV\t\001\tC\003S\001\021\0051+A\by\003R$(/\0332vi\0264\026\r\\;f)\t\031D\013C\003V#\002\007a+A\003f]\022\034\005\016\005\002\013/&\021\001L\002\002\005\007\"\f'\017C\003S\001\021\005!\fF\0014\021\025a\006\001\"\003^\0035!\030m[3V]RLGn\0215beR\0311G\0307\t\013}[\006\031\0011\002\005%$\bcA1j-:\021!m\032\b\003G\032l\021\001\032\006\003KN\ta\001\020:p_Rt\024\"A\004\n\005!4\021a\0029bG.\fw-Z\005\003U.\024\001\"\023;fe\006$xN\035\006\003Q\032AQ!\\.A\002Y\0131!\0328e\021\025y\007\001\"\001q\003\035AXI\0343UC\036$\"!F9\t\013It\007\031A\032\002\023M$\030M\035;OC6,\007\"\002;\001\t\003)\030!\002=OC6,W#A\032\t\013]\004A\021\002=\002\033\005$HO]0v]\026\0348-\0319f)\rI\030\021\001\t\003u~l\021a\037\006\003yv\fA\001\\1oO*\ta0\001\003kCZ\f\027B\001\035|\021\031\t\031A\036a\001g\005\t1\017C\004\002\b\001!I!!\003\002/9|'/\\1mSj,\027\t\036;sS\n,H/\032,bYV,GcA\032\002\f!9\021QBA\003\001\004\031\024AB1uiZ\fG\016C\004\002\022\001!\t!a\005\002\021a\034\005.\031:SK\032$RaMA\013\003?A\001\"a\006\002\020\001\007\021\021D\001\003G\"\004BACA\016-&\031\021Q\004\004\003\023\031+hn\031;j_:\004\004\002CA\021\003\037\001\r!a\t\002\r9,\007\020^2i!\021Q\0211D\013\t\017\005E\001\001\"\001\002(Q\0311'!\013\t\r}\013)\0031\001a\021\031\t\t\002\001C\001k\"9\021q\006\001\007\002\005E\022!\0037p_.\f\007.Z1e)\t\t\031\004\005\003b\003k1\026bAA\034W\n\001\")\0364gKJ,G-\023;fe\006$xN\035\005\b\003/\001a\021AA\036+\0051\006BBA\021\001\031\005A\003C\004\002B\0011\t\"a\017\002'\rDwL]3ukJt\027N\\4`]\026DHo\0315\t\017\005\025\003A\"\001\002H\005\031Qm\0344\026\005\005%\003c\001\006\002L%\031\021Q\n\004\003\017\t{w\016\\3b]\"I\021\021\013\001A\002\033\005\0211K\001\007i6\004\bo\\:\026\003\rC\021\"a\026\001\001\0045\t!!\027\002\025Ql\007\017]8t?\022*\027\017F\002\026\0037B\021\"!\030\002V\005\005\t\031A\"\002\007a$\023\007C\004\002b\0011\t!a\031\002\031aD\025M\0343mK\026\023(o\034:\025\013U\t)'!\033\t\017\005\035\024q\fa\001-\006!A\017[1u\021\035\tY'a\030A\002M\n1!\\:h\021\035\ty\007\001D\001\003c\n\021C]3q_J$8+\0378uCb,%O]8s)\r)\0221\017\005\b\003k\ni\0071\0014\003\r\031HO\035\005\b\003_\002a\021AA=)\025)\0221PAC\021!\ti(a\036A\002\005}\024a\0019pgB\031!\"!!\n\007\005\reAA\002J]RDq!!\036\002x\001\0071\007C\004\002\n\0021\t!a#\002\035Q\024XO\\2bi\026$WI\035:peR\0311$!$\t\017\005-\024q\021a\001g!9\021\021\023\001\007\002\005M\025AC3se>\024hj\\#oIR\0311$!&\t\017\005]\025q\022a\001g\005\031A/Y4\t\017\005m\005\001\"\005\002\036\006qQM\035:pe\006sGMU3tk2$X\003BAP\003K#b!!)\002*\006-\006\003BAR\003Kc\001\001B\004\002(\006e%\031\001\021\003\003QCq!a\033\002\032\002\0071\007\003\005\002.\006e\005\031AAQ\003\005A\bbBAY\001\021\005\0211W\001\007qR{7.\0328\025\007U\t)\fC\004\002h\005=\006\031\001,\t\017\005E\006\001\"\001\002:R\031Q#a/\t\021\005\035\024q\027a\001\003{\003B!YA`-&\031\021\021Y6\003\007M+\027\017\003\004\002F\002!\t\001F\001\004q\026\013\006BBAe\001\021\005A#A\005y'B\f7-Z(qi\"1\021Q\032\001\005\002Q\ta\001_*qC\016,\007bBAi\001\021\005\0211[\001\ne\026$XO\0358j]\036,B!!6\002\\R!\021q[At)\021\tI.!8\021\t\005\r\0261\034\003\b\003O\013yM1\001!\021!\ty.a4A\002\005\005\030!\0014\021\r)\t\031/!7\026\023\r\t)O\002\002\n\rVt7\r^5p]FB\001\"!,\002P\002\007\021\021\034\005\b\003W\004A\021AAw\003\031\031\030M^5oOV1\021q\036B\005\003k$b!!=\003\004\t5A\003BAz\003s\004B!a)\002v\0229\021q_Au\005\004\001#!\001\"\t\023\005m\030\021\036CA\002\005u\030\001\0022pIf\004RACA\000\003gL1A!\001\007\005!a$-\0378b[\026t\004\002\003B\003\003S\004\rAa\002\002\r\035,G\017^3s!\021\t\031K!\003\005\017\t-\021\021\036b\001A\t\t\021\t\003\005\003\020\005%\b\031\001B\t\003\031\031X\r\036;feB1!\"a9\003\bUAqA!\006\001\t#\0219\"\001\006y)\006\\W-\0268uS2,BA!\007\003\036QA!1\004B\020\005S\021y\003\005\003\002$\nuAaBAT\005'\021\r\001\t\005\t\005C\021\031\0021\001\003$\0059\001.\0318eY\026\024\bc\002\006\003&\r\033$1D\005\004\005O1!!\003$v]\016$\030n\03483\021!\021YCa\005A\002\t5\022A\0039pg&$\030n\0348feB!!\"a\007D\021\035\021\tDa\005A\002M\nQ!\0368uS2DqA!\016\001\t\023\0219$\001\003qK\026\\G\003BA%\005sAqAa\017\0034\001\0071'\001\006m_>\\\027N\\4G_J\004")
/*     */ public interface MarkupParserCommon extends TokenTests {
/*     */   Nothing$ unreachable();
/*     */   
/*     */   Object mkAttributes(String paramString, Object paramObject);
/*     */   
/*     */   Object mkProcInstr(Object paramObject, String paramString1, String paramString2);
/*     */   
/*     */   Tuple2<String, Object> xTag(Object paramObject);
/*     */   
/*     */   Object xProcInstr();
/*     */   
/*     */   String xAttributeValue(char paramChar);
/*     */   
/*     */   String xAttributeValue();
/*     */   
/*     */   void xEndTag(String paramString);
/*     */   
/*     */   String xName();
/*     */   
/*     */   String xCharRef(Function0<Object> paramFunction0, Function0<BoxedUnit> paramFunction01);
/*     */   
/*     */   String xCharRef(Iterator<Object> paramIterator);
/*     */   
/*     */   String xCharRef();
/*     */   
/*     */   BufferedIterator<Object> lookahead();
/*     */   
/*     */   char ch();
/*     */   
/*     */   void nextch();
/*     */   
/*     */   char ch_returning_nextch();
/*     */   
/*     */   boolean eof();
/*     */   
/*     */   Object tmppos();
/*     */   
/*     */   void tmppos_$eq(Object paramObject);
/*     */   
/*     */   void xHandleError(char paramChar, String paramString);
/*     */   
/*     */   void reportSyntaxError(String paramString);
/*     */   
/*     */   void reportSyntaxError(int paramInt, String paramString);
/*     */   
/*     */   Nothing$ truncatedError(String paramString);
/*     */   
/*     */   Nothing$ errorNoEnd(String paramString);
/*     */   
/*     */   <T> T errorAndResult(String paramString, T paramT);
/*     */   
/*     */   void xToken(char paramChar);
/*     */   
/*     */   void xToken(Seq<Object> paramSeq);
/*     */   
/*     */   void xEQ();
/*     */   
/*     */   void xSpaceOpt();
/*     */   
/*     */   void xSpace();
/*     */   
/*     */   <T> T returning(T paramT, Function1<T, BoxedUnit> paramFunction1);
/*     */   
/*     */   <A, B> B saving(A paramA, Function1<A, BoxedUnit> paramFunction1, Function0<B> paramFunction0);
/*     */   
/*     */   <T> T xTakeUntil(Function2<Object, String, T> paramFunction2, Function0<Object> paramFunction0, String paramString);
/*     */   
/*     */   public class MarkupParserCommon$$anonfun$xProcInstr$1 extends AbstractFunction2<Object, String, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String n$1;
/*     */     
/*     */     public final Object apply(Object x$1, String x$2) {
/*  54 */       return this.$outer.mkProcInstr(x$1, this.n$1, x$2);
/*     */     }
/*     */     
/*     */     public MarkupParserCommon$$anonfun$xProcInstr$1(MarkupParserCommon $outer, String n$1) {}
/*     */   }
/*     */   
/*     */   public class MarkupParserCommon$$anonfun$xProcInstr$2 extends AbstractFunction0<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Object apply() {
/*  54 */       return this.$outer.tmppos();
/*     */     }
/*     */     
/*     */     public MarkupParserCommon$$anonfun$xProcInstr$2(MarkupParserCommon $outer) {}
/*     */   }
/*     */   
/*     */   public class MarkupParserCommon$$anonfun$xCharRef$5 extends AbstractFunction1<String, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(String str) {
/* 158 */       this.$outer.reportSyntaxError(str);
/*     */     }
/*     */     
/*     */     public MarkupParserCommon$$anonfun$xCharRef$5(MarkupParserCommon $outer) {}
/*     */   }
/*     */   
/*     */   public class MarkupParserCommon$$anonfun$xCharRef$6 extends AbstractFunction1<String, Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Nothing$ apply(String msg) {
/* 158 */       return this.$outer.truncatedError(msg);
/*     */     }
/*     */     
/*     */     public MarkupParserCommon$$anonfun$xCharRef$6(MarkupParserCommon $outer) {}
/*     */   }
/*     */   
/*     */   public class MarkupParserCommon$$anonfun$xCharRef$1 extends AbstractFunction0.mcC.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final CharRef c$1;
/*     */     
/*     */     public final char apply() {
/* 162 */       return this.c$1.elem;
/*     */     }
/*     */     
/*     */     public char apply$mcC$sp() {
/* 162 */       return this.c$1.elem;
/*     */     }
/*     */     
/*     */     public MarkupParserCommon$$anonfun$xCharRef$1(MarkupParserCommon $outer, CharRef c$1) {}
/*     */   }
/*     */   
/*     */   public class MarkupParserCommon$$anonfun$xCharRef$2 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final CharRef c$1;
/*     */     
/*     */     public final Iterator it$1;
/*     */     
/*     */     public final void apply() {
/* 162 */       this.c$1.elem = BoxesRunTime.unboxToChar(this.it$1.next());
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 162 */       this.c$1.elem = BoxesRunTime.unboxToChar(this.it$1.next());
/*     */     }
/*     */     
/*     */     public MarkupParserCommon$$anonfun$xCharRef$2(MarkupParserCommon $outer, CharRef c$1, Iterator it$1) {}
/*     */   }
/*     */   
/*     */   public class MarkupParserCommon$$anonfun$xCharRef$7 extends AbstractFunction1<String, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(String str) {
/* 162 */       this.$outer.reportSyntaxError(str);
/*     */     }
/*     */     
/*     */     public MarkupParserCommon$$anonfun$xCharRef$7(MarkupParserCommon $outer) {}
/*     */   }
/*     */   
/*     */   public class MarkupParserCommon$$anonfun$xCharRef$8 extends AbstractFunction1<String, Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Nothing$ apply(String msg) {
/* 162 */       return this.$outer.truncatedError(msg);
/*     */     }
/*     */     
/*     */     public MarkupParserCommon$$anonfun$xCharRef$8(MarkupParserCommon $outer) {}
/*     */   }
/*     */   
/*     */   public class MarkupParserCommon$$anonfun$xCharRef$3 extends AbstractFunction0.mcC.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final char apply() {
/* 165 */       return this.$outer.ch();
/*     */     }
/*     */     
/*     */     public char apply$mcC$sp() {
/* 165 */       return this.$outer.ch();
/*     */     }
/*     */     
/*     */     public MarkupParserCommon$$anonfun$xCharRef$3(MarkupParserCommon $outer) {}
/*     */   }
/*     */   
/*     */   public class MarkupParserCommon$$anonfun$xCharRef$4 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply() {
/* 165 */       this.$outer.nextch();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 165 */       this.$outer.nextch();
/*     */     }
/*     */     
/*     */     public MarkupParserCommon$$anonfun$xCharRef$4(MarkupParserCommon $outer) {}
/*     */   }
/*     */   
/*     */   public class MarkupParserCommon$$anonfun$xToken$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(char that) {
/* 201 */       this.$outer.xToken(that);
/*     */     }
/*     */     
/*     */     public MarkupParserCommon$$anonfun$xToken$1(MarkupParserCommon $outer) {}
/*     */   }
/*     */   
/*     */   public class MarkupParserCommon$$anonfun$peek$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(int x$3) {
/* 257 */       this.$outer.nextch();
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int x$3) {
/* 257 */       this.$outer.nextch();
/*     */     }
/*     */     
/*     */     public MarkupParserCommon$$anonfun$peek$1(MarkupParserCommon $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\MarkupParserCommon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */