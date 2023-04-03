/*     */ package scala.xml;
/*     */ 
/*     */ import scala.Enumeration;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.HashSet;
/*     */ import scala.collection.mutable.Set;
/*     */ import scala.collection.mutable.SetLike;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.CharRef;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\t\005w!B\001\003\021\0039\021aB+uS2LG/\037\006\003\007\021\t1\001_7m\025\005)\021!B:dC2\f7\001\001\t\003\021%i\021A\001\004\006\025\tA\ta\003\002\b+RLG.\033;z'\rIA\002\005\t\003\0339i\021\001B\005\003\037\021\021a!\0218z%\0264\007CA\t\025\033\005\021\"BA\n\003\003\035\001\030M]:j]\036L!!\006\n\003\025Q{7.\0328UKN$8\017C\003\030\023\021\005\001$\001\004=S:LGO\020\013\002\017!9!$\003b\001\n\013Y\022AA*V+\005ar\"A\017\035\003iAaaH\005!\002\033a\022aA*VA!)\021%\003C\002E\005\021\022.\0349mS\016LGo\0252U_N#(/\0338h)\t\0313\006\005\002%S5\tQE\003\002'O\005!A.\0318h\025\005A\023\001\0026bm\006L!AK\023\003\rM#(/\0338h\021\025a\003\0051\001.\003\t\031(\r\005\002/m9\021q\006\016\b\003aMj\021!\r\006\003e\031\ta\001\020:p_Rt\024\"A\003\n\005U\"\021a\0029bG.\fw-Z\005\003oa\022Qb\025;sS:<')^5mI\026\024(BA\033\005\021\031Q\024\002\"\001\003w\005Q1O\031+p'R\024\030N\\4\025\005q\022\005CA\037A\035\tia(\003\002@\t\0051\001K]3eK\032L!AK!\013\005}\"\001\"B\":\001\004!\025!\0014\021\t5)UfR\005\003\r\022\021\021BR;oGRLwN\\\031\021\0055A\025BA%\005\005\021)f.\033;\t\r-KA\021\001\002M\003AI7/\021;p[\006sGMT8u)\026DH\017\006\002N!B\021QBT\005\003\037\022\021qAQ8pY\026\fg\016C\003R\025\002\007!+A\001y!\tA1+\003\002U\005\t!aj\0343f\021\0251\026\002\"\001X\003\021!(/[7\025\005IC\006\"B)V\001\004\021\006\"\002.\n\t\003Y\026A\003;sS6\004&o\0349feR\021Al\030\t\004]u\023\026B\00109\005\r\031V-\035\005\006#f\003\rA\025\005\006C&!\tAY\001\005g>\024H\017\006\002dMB\021\001\002Z\005\003K\n\021\001\"T3uC\022\013G/\031\005\006O\002\004\raY\001\003[\022DQ!Y\005\005\002%$\"A\0256\t\013-D\007\031\001*\002\0039DQ!\\\005\005\0069\fa!Z:dCB,GC\001\037p\021\025\001H\0161\001=\003\021!X\r\037;\b\013IL\001\022A:\002\017\025\0338-\0319fgB\021A/^\007\002\023\031)a/\003E\001o\n9Qi]2ba\026\0348CA;\r\021\0259R\017\"\001z)\005\031\bbB>v\005\004%\t\001`\001\006a\006L'o]\013\002{B1a0a\002$\003\027i\021a \006\005\003\003\t\031!A\005j[6,H/\0312mK*\031\021Q\001\003\002\025\r|G\016\\3di&|g.C\002\002\n}\0241!T1q!\ri\021QB\005\004\003\037!!\001B\"iCJDq!a\005vA\003%Q0\001\004qC&\0248\017\t\005\n\003/)(\031!C\001\0033\ta!Z:d\033\006\004XCAA\016!\031q\030qAA\006y!A\021qD;!\002\023\tY\"A\004fg\016l\025\r\035\021\t\021\005\rRO1A\005\002q\f\001\"\0368fg\016l\025\r\035\005\b\003O)\b\025!\003~\003%)h.Z:d\033\006\004\b\005\003\004n\023\021\025\0211\006\013\006[\0055\022q\006\005\007a\006%\002\031\001\037\t\017\005E\022\021\006a\001[\005\t1\017C\004\0026%!)!a\016\002\021UtWm]2ba\026$R!LA\035\003{Aq!a\017\0024\001\007A(A\002sK\032Dq!!\r\0024\001\007Q\006C\004\002B%!\t!a\021\002#\r|G\016\\3di:\013W.Z:qC\016,7\017\006\003\002F\005E\003#BA$\003\033bTBAA%\025\021\tY%a\001\002\0175,H/\0312mK&!\021qJA%\005\r\031V\r\036\005\b\003'\ny\0041\001]\003\025qw\016Z3t\021\035\t\t%\003C\001\003/\"RaRA-\0037Baa[A+\001\004\021\006\002CA/\003+\002\r!!\022\002\007M,G\017C\004\002b%!\t!a\031\002\013Q|\007,\024'\025\0375\n)'a\032\002r\005M\024qOA>\003Ba!UA0\001\004\021\006BCA5\003?\002\n\0211\001\002l\0051\001o]2pa\026\0042\001CA7\023\r\tyG\001\002\021\035\006lWm\0359bG\026\024\025N\0343j]\036D\001\002LA0!\003\005\r!\f\005\n\003k\ny\006%AA\0025\013Qb\035;sSB\034u.\\7f]R\034\b\"CA=\003?\002\n\0211\001N\0039!WmY8eK\026sG/\033;jKND\021\"! \002`A\005\t\031A'\002%A\024Xm]3sm\026<\006.\033;fgB\f7-\032\005\n\003\003\013y\006%AA\0025\013A\"\\5oS6L'0\032+bOND\003\"a\030\002\006\006-\025q\022\t\004\033\005\035\025bAAE\t\tQA-\0329sK\016\fG/\0323\"\005\0055\025!\022)mK\006\034X\rI;tK\002\0027/\032:jC2L'0\0321!S:\034H/Z1eA\005tG\rI:qK\016Lg-\037\021bA\001l\027N\\5nSj,G+Y4tA\002\002\030M]1nKR,'/\t\002\002\022\0061!GL\0311]ABq!!&\n\t\003\t9*A\005tKJL\027\r\\5{KRyQ&!'\002\034\006u\025qTAQ\003G\013)\013\003\004R\003'\003\rA\025\005\013\003S\n\031\n%AA\002\005-\004\002\003\027\002\024B\005\t\031A\027\t\023\005U\0241\023I\001\002\004i\005\"CA=\003'\003\n\0211\001N\021%\ti(a%\021\002\003\007Q\n\003\006\002\002\006M\005\023!a\001\003O\003B!!+\0020:\031\001\"a+\n\007\0055&!\001\007NS:LW.\033>f\033>$W-\003\003\0022\006M&!\002,bYV,\027bAA[\t\tYQI\\;nKJ\fG/[8o\021\035\tI,\003C\001\003w\013Qb]3rk\026t7-\032+p16cEcD$\002>\006\005\0271YAc\003\017\fI-a3\t\017\005}\026q\027a\0019\006A1\r[5mIJ,g\016\003\006\002j\005]\006\023!a\001\003WB\001\002LA\\!\003\005\r!\f\005\n\003k\n9\f%AA\0025C\021\"!\037\0028B\005\t\031A'\t\023\005u\024q\027I\001\002\004i\005BCAA\003o\003\n\0211\001\002(\"9\021qZ\005\005\006\005E\027A\0029sK\032L\007\020\006\003\002T\006e\007\003B\007\002VrJ1!a6\005\005\031y\005\017^5p]\"9\0211\\Ag\001\004a\024\001\0028b[\026Dq!a8\n\t\003\t\t/\001\005iCND7i\0343f)1\t\031/!;\002n\006E\030Q_A}!\ri\021Q]\005\004\003O$!aA%oi\"9\0211^Ao\001\004a\024a\0019sK\"9\021q^Ao\001\004a\024!\0027bE\026d\007\002CAz\003;\004\r!a9\002\035\005$HO]5c\021\006\034\bnQ8eK\"A\021q_Ao\001\004\t\031/\001\005tGB,\007*Y:i\021\035\ty,!8A\002qCq!!@\n\t\003\ty0\001\007baB,g\016Z)v_R,G\rF\002=\005\003Aq!!\r\002|\002\007A\bC\004\002~&!\tA!\002\025\r\t\035!1\002B\007!\021\t9E!\003\n\007]\nI\005C\004\0022\t\r\001\031\001\037\t\r1\022\031\0011\001.\021\035\021\t\"\003C\001\005'\t1#\0319qK:$Wi]2ba\026$\027+^8uK\022$R!\fB\013\005/Aq!!\r\003\020\001\007A\b\003\004-\005\037\001\r!\f\005\b\0057IA\021\001B\017\003\0359W\r\036(b[\026$R\001\020B\020\005CAq!!\r\003\032\001\007A\b\003\005\003$\te\001\031AAr\003\025Ig\016Z3y\021\035\0219#\003C\001\005S\t1c\0315fG.\fE\017\036:jEV$XMV1mk\026$2\001\020B\026\021\035\021iC!\nA\002q\nQA^1mk\026DqA!\r\n\t\003\021\031$A\nqCJ\034X-\021;ue&\024W\017^3WC2,X\rF\002]\005kAqA!\f\0030\001\007A\bC\004\003:%!\tAa\017\002\031A\f'o]3DQ\006\024(+\0324\025\023q\022iDa\022\003N\tM\003\002\003B \005o\001\rA!\021\002\005\rD\007#B\007\003D\005-\021b\001B#\t\tIa)\0368di&|g\016\r\005\t\005\023\0229\0041\001\003L\0051a.\032=uG\"\004B!\004B\"\017\"A!q\nB\034\001\004\021\t&A\tsKB|'\017^*z]R\f\0070\022:s_J\004B!D#=\017\"A!Q\013B\034\001\004\021\t&\001\013sKB|'\017\036+sk:\034\027\r^3e\013J\024xN\035\005\n\0053J\021\023!C\001\0057\nqc]3rk\026t7-\032+p16cE\005Z3gCVdG\017\n\032\026\005\tu#\006BA6\005?Z#A!\031\021\t\t\r$QN\007\003\005KRAAa\032\003j\005IQO\\2iK\016\\W\r\032\006\004\005W\"\021AC1o]>$\030\r^5p]&!!q\016B3\005E)hn\0315fG.,GMV1sS\006t7-\032\005\n\005gJ\021\023!C\001\005k\nqc]3rk\026t7-\032+p16cE\005Z3gCVdG\017J\032\026\005\t]$fA\027\003`!I!1P\005\022\002\023\005!QP\001\030g\026\fX/\0328dKR{\007,\024'%I\0264\027-\0367uIQ*\"Aa +\0075\023y\006C\005\003\004&\t\n\021\"\001\003~\00592/Z9vK:\034W\rV8Y\0332#C-\0324bk2$H%\016\005\n\005\017K\021\023!C\001\005{\nqc]3rk\026t7-\032+p16cE\005Z3gCVdG\017\n\034\t\023\t-\025\"%A\005\002\t5\025aF:fcV,gnY3U_bkE\n\n3fM\006,H\016\036\0238+\t\021yI\013\003\002(\n}\003\"\003BJ\023E\005I\021\001B.\003M\031XM]5bY&TX\r\n3fM\006,H\016\036\0233\021%\0219*CI\001\n\003\021)(A\ntKJL\027\r\\5{K\022\"WMZ1vYR$3\007C\005\003\034&\t\n\021\"\001\003~\005\0312/\032:jC2L'0\032\023eK\032\fW\017\034;%i!I!qT\005\022\002\023\005!QP\001\024g\026\024\030.\0317ju\026$C-\0324bk2$H%\016\005\n\005GK\021\023!C\001\005{\n1c]3sS\006d\027N_3%I\0264\027-\0367uIYB\021Ba*\n#\003%\tA!$\002'M,'/[1mSj,G\005Z3gCVdG\017J\034\t\023\t-\026\"%A\005\002\tm\023a\004;p16cE\005Z3gCVdG\017\n\032\t\023\t=\026\"%A\005\002\tU\024a\004;p16cE\005Z3gCVdG\017J\032\t\023\tM\026\"%A\005\002\tu\024a\004;p16cE\005Z3gCVdG\017\n\033\t\023\t]\026\"%A\005\002\tu\024a\004;p16cE\005Z3gCVdG\017J\033\t\023\tm\026\"%A\005\002\tu\024a\004;p16cE\005Z3gCVdG\017\n\034\t\023\t}\026\"%A\005\002\tu\024a\004;p16cE\005Z3gCVdG\017J\034")
/*     */ public final class Utility {
/*     */   public static boolean checkPubID(String paramString) {
/*     */     return Utility$.MODULE$.checkPubID(paramString);
/*     */   }
/*     */   
/*     */   public static boolean checkSysID(String paramString) {
/*     */     return Utility$.MODULE$.checkSysID(paramString);
/*     */   }
/*     */   
/*     */   public static boolean isValidIANAEncoding(Seq<Object> paramSeq) {
/*     */     return Utility$.MODULE$.isValidIANAEncoding(paramSeq);
/*     */   }
/*     */   
/*     */   public static boolean isPubIDChar(char paramChar) {
/*     */     return Utility$.MODULE$.isPubIDChar(paramChar);
/*     */   }
/*     */   
/*     */   public static boolean isName(String paramString) {
/*     */     return Utility$.MODULE$.isName(paramString);
/*     */   }
/*     */   
/*     */   public static boolean isNameStart(char paramChar) {
/*     */     return Utility$.MODULE$.isNameStart(paramChar);
/*     */   }
/*     */   
/*     */   public static boolean isNameChar(char paramChar) {
/*     */     return Utility$.MODULE$.isNameChar(paramChar);
/*     */   }
/*     */   
/*     */   public static boolean isAlphaDigit(char paramChar) {
/*     */     return Utility$.MODULE$.isAlphaDigit(paramChar);
/*     */   }
/*     */   
/*     */   public static boolean isAlpha(char paramChar) {
/*     */     return Utility$.MODULE$.isAlpha(paramChar);
/*     */   }
/*     */   
/*     */   public static boolean isSpace(Seq<Object> paramSeq) {
/*     */     return Utility$.MODULE$.isSpace(paramSeq);
/*     */   }
/*     */   
/*     */   public static boolean isSpace(char paramChar) {
/*     */     return Utility$.MODULE$.isSpace(paramChar);
/*     */   }
/*     */   
/*     */   public static boolean toXML$default$7() {
/*     */     return Utility$.MODULE$.toXML$default$7();
/*     */   }
/*     */   
/*     */   public static boolean toXML$default$6() {
/*     */     return Utility$.MODULE$.toXML$default$6();
/*     */   }
/*     */   
/*     */   public static boolean toXML$default$5() {
/*     */     return Utility$.MODULE$.toXML$default$5();
/*     */   }
/*     */   
/*     */   public static boolean toXML$default$4() {
/*     */     return Utility$.MODULE$.toXML$default$4();
/*     */   }
/*     */   
/*     */   public static StringBuilder toXML$default$3() {
/*     */     return Utility$.MODULE$.toXML$default$3();
/*     */   }
/*     */   
/*     */   public static NamespaceBinding toXML$default$2() {
/*     */     return Utility$.MODULE$.toXML$default$2();
/*     */   }
/*     */   
/*     */   public static Enumeration.Value serialize$default$7() {
/*     */     return Utility$.MODULE$.serialize$default$7();
/*     */   }
/*     */   
/*     */   public static boolean serialize$default$6() {
/*     */     return Utility$.MODULE$.serialize$default$6();
/*     */   }
/*     */   
/*     */   public static boolean serialize$default$5() {
/*     */     return Utility$.MODULE$.serialize$default$5();
/*     */   }
/*     */   
/*     */   public static boolean serialize$default$4() {
/*     */     return Utility$.MODULE$.serialize$default$4();
/*     */   }
/*     */   
/*     */   public static StringBuilder serialize$default$3() {
/*     */     return Utility$.MODULE$.serialize$default$3();
/*     */   }
/*     */   
/*     */   public static NamespaceBinding serialize$default$2() {
/*     */     return Utility$.MODULE$.serialize$default$2();
/*     */   }
/*     */   
/*     */   public static Enumeration.Value sequenceToXML$default$7() {
/*     */     return Utility$.MODULE$.sequenceToXML$default$7();
/*     */   }
/*     */   
/*     */   public static boolean sequenceToXML$default$6() {
/*     */     return Utility$.MODULE$.sequenceToXML$default$6();
/*     */   }
/*     */   
/*     */   public static boolean sequenceToXML$default$5() {
/*     */     return Utility$.MODULE$.sequenceToXML$default$5();
/*     */   }
/*     */   
/*     */   public static boolean sequenceToXML$default$4() {
/*     */     return Utility$.MODULE$.sequenceToXML$default$4();
/*     */   }
/*     */   
/*     */   public static StringBuilder sequenceToXML$default$3() {
/*     */     return Utility$.MODULE$.sequenceToXML$default$3();
/*     */   }
/*     */   
/*     */   public static NamespaceBinding sequenceToXML$default$2() {
/*     */     return Utility$.MODULE$.sequenceToXML$default$2();
/*     */   }
/*     */   
/*     */   public static String parseCharRef(Function0<Object> paramFunction0, Function0<BoxedUnit> paramFunction01, Function1<String, BoxedUnit> paramFunction11, Function1<String, BoxedUnit> paramFunction12) {
/*     */     return Utility$.MODULE$.parseCharRef(paramFunction0, paramFunction01, paramFunction11, paramFunction12);
/*     */   }
/*     */   
/*     */   public static Seq<Node> parseAttributeValue(String paramString) {
/*     */     return Utility$.MODULE$.parseAttributeValue(paramString);
/*     */   }
/*     */   
/*     */   public static String checkAttributeValue(String paramString) {
/*     */     return Utility$.MODULE$.checkAttributeValue(paramString);
/*     */   }
/*     */   
/*     */   public static String getName(String paramString, int paramInt) {
/*     */     return Utility$.MODULE$.getName(paramString, paramInt);
/*     */   }
/*     */   
/*     */   public static StringBuilder appendEscapedQuoted(String paramString, StringBuilder paramStringBuilder) {
/*     */     return Utility$.MODULE$.appendEscapedQuoted(paramString, paramStringBuilder);
/*     */   }
/*     */   
/*     */   public static StringBuilder appendQuoted(String paramString, StringBuilder paramStringBuilder) {
/*     */     return Utility$.MODULE$.appendQuoted(paramString, paramStringBuilder);
/*     */   }
/*     */   
/*     */   public static String appendQuoted(String paramString) {
/*     */     return Utility$.MODULE$.appendQuoted(paramString);
/*     */   }
/*     */   
/*     */   public static int hashCode(String paramString1, String paramString2, int paramInt1, int paramInt2, Seq<Node> paramSeq) {
/*     */     return Utility$.MODULE$.hashCode(paramString1, paramString2, paramInt1, paramInt2, paramSeq);
/*     */   }
/*     */   
/*     */   public static Option<String> prefix(String paramString) {
/*     */     return Utility$.MODULE$.prefix(paramString);
/*     */   }
/*     */   
/*     */   public static void sequenceToXML(Seq<Node> paramSeq, NamespaceBinding paramNamespaceBinding, StringBuilder paramStringBuilder, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Enumeration.Value paramValue) {
/*     */     Utility$.MODULE$.sequenceToXML(paramSeq, paramNamespaceBinding, paramStringBuilder, paramBoolean1, paramBoolean2, paramBoolean3, paramValue);
/*     */   }
/*     */   
/*     */   public static StringBuilder serialize(Node paramNode, NamespaceBinding paramNamespaceBinding, StringBuilder paramStringBuilder, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, Enumeration.Value paramValue) {
/*     */     return Utility$.MODULE$.serialize(paramNode, paramNamespaceBinding, paramStringBuilder, paramBoolean1, paramBoolean2, paramBoolean3, paramValue);
/*     */   }
/*     */   
/*     */   public static StringBuilder toXML(Node paramNode, NamespaceBinding paramNamespaceBinding, StringBuilder paramStringBuilder, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
/*     */     return Utility$.MODULE$.toXML(paramNode, paramNamespaceBinding, paramStringBuilder, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
/*     */   }
/*     */   
/*     */   public static void collectNamespaces(Node paramNode, Set<String> paramSet) {
/*     */     Utility$.MODULE$.collectNamespaces(paramNode, paramSet);
/*     */   }
/*     */   
/*     */   public static Set<String> collectNamespaces(Seq<Node> paramSeq) {
/*     */     return Utility$.MODULE$.collectNamespaces(paramSeq);
/*     */   }
/*     */   
/*     */   public static StringBuilder unescape(String paramString, StringBuilder paramStringBuilder) {
/*     */     return Utility$.MODULE$.unescape(paramString, paramStringBuilder);
/*     */   }
/*     */   
/*     */   public static StringBuilder escape(String paramString, StringBuilder paramStringBuilder) {
/*     */     return Utility$.MODULE$.escape(paramString, paramStringBuilder);
/*     */   }
/*     */   
/*     */   public static String escape(String paramString) {
/*     */     return Utility$.MODULE$.escape(paramString);
/*     */   }
/*     */   
/*     */   public static Node sort(Node paramNode) {
/*     */     return Utility$.MODULE$.sort(paramNode);
/*     */   }
/*     */   
/*     */   public static MetaData sort(MetaData paramMetaData) {
/*     */     return Utility$.MODULE$.sort(paramMetaData);
/*     */   }
/*     */   
/*     */   public static Seq<Node> trimProper(Node paramNode) {
/*     */     return Utility$.MODULE$.trimProper(paramNode);
/*     */   }
/*     */   
/*     */   public static Node trim(Node paramNode) {
/*     */     return Utility$.MODULE$.trim(paramNode);
/*     */   }
/*     */   
/*     */   public static String implicitSbToString(StringBuilder paramStringBuilder) {
/*     */     return Utility$.MODULE$.implicitSbToString(paramStringBuilder);
/*     */   }
/*     */   
/*     */   public static char SU() {
/*     */     return Utility$.MODULE$.SU();
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$trim$1 extends AbstractFunction1<Node, Seq<Node>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Seq<Node> apply(Node x) {
/*  47 */       return Utility$.MODULE$.trimProper(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$trimProper$1 extends AbstractFunction1<Node, Seq<Node>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Seq<Node> apply(Node x) {
/*  55 */       return Utility$.MODULE$.trimProper(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$3 extends AbstractFunction1<MetaData, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String key$1;
/*     */     
/*     */     public final boolean apply(MetaData m) {
/*  65 */       String str = m.key();
/*  65 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*  65 */       return (new StringOps(str)).$less(this.key$1);
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$3(String key$1) {}
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$4 extends AbstractFunction1<MetaData, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String key$1;
/*     */     
/*     */     public final boolean apply(MetaData m) {
/*  66 */       String str = m.key();
/*  66 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*  66 */       return (new StringOps(str)).$greater(this.key$1);
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$4(String key$1) {}
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$sort$1 extends AbstractFunction2<MetaData, MetaData, MetaData> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final MetaData apply(MetaData x, MetaData xs) {
/*  67 */       return x.copy(xs);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$sort$2 extends AbstractFunction1<Node, Node> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Node apply(Node n) {
/*  74 */       return Utility$.MODULE$.sort(n);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$escape$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String text$1;
/*     */     
/*     */     public final void apply(StringBuilder x$1) {
/*  81 */       Utility$.MODULE$.escape(this.text$1, x$1);
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$escape$1(String text$1) {}
/*     */   }
/*     */   
/*     */   public static class Escapes$ {
/*     */     public static final Escapes$ MODULE$;
/*     */     
/*     */     private final Map<String, Object> pairs;
/*     */     
/*     */     private final Map<Object, String> escMap;
/*     */     
/*     */     private final Map<String, Object> unescMap;
/*     */     
/*     */     public Escapes$() {
/*  83 */       MODULE$ = this;
/*  87 */       scala.Predef$ predef$1 = scala.Predef$.MODULE$;
/*  87 */       Character character1 = BoxesRunTime.boxToCharacter('<');
/*  87 */       scala.Predef$ArrowAssoc$ predef$ArrowAssoc$1 = scala.Predef$ArrowAssoc$.MODULE$;
/*  87 */       (new Tuple2[4])[0] = new Tuple2("lt", character1);
/*  88 */       scala.Predef$ predef$2 = scala.Predef$.MODULE$;
/*  88 */       Character character2 = BoxesRunTime.boxToCharacter('>');
/*  88 */       scala.Predef$ArrowAssoc$ predef$ArrowAssoc$2 = scala.Predef$ArrowAssoc$.MODULE$;
/*  88 */       (new Tuple2[4])[1] = new Tuple2("gt", character2);
/*  89 */       scala.Predef$ predef$3 = scala.Predef$.MODULE$;
/*  89 */       Character character3 = BoxesRunTime.boxToCharacter('&');
/*  89 */       scala.Predef$ArrowAssoc$ predef$ArrowAssoc$3 = scala.Predef$ArrowAssoc$.MODULE$;
/*  89 */       (new Tuple2[4])[2] = new Tuple2("amp", character3);
/*  90 */       scala.Predef$ predef$4 = scala.Predef$.MODULE$;
/*  90 */       Character character4 = BoxesRunTime.boxToCharacter('"');
/*  90 */       scala.Predef$ArrowAssoc$ predef$ArrowAssoc$4 = scala.Predef$ArrowAssoc$.MODULE$;
/*  90 */       (new Tuple2[4])[3] = new Tuple2("quot", character4);
/*     */       this.pairs = (Map<String, Object>)scala.Predef$.MODULE$.Map().apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[4]));
/*  95 */       this.escMap = (Map<Object, String>)pairs().map((Function1)new anonfun$5(), scala.collection.immutable.Map$.MODULE$.canBuildFrom());
/*  96 */       scala.Predef$ predef$5 = scala.Predef$.MODULE$;
/*  96 */       Character character5 = BoxesRunTime.boxToCharacter('\'');
/*  96 */       scala.Predef$ArrowAssoc$ predef$ArrowAssoc$5 = scala.Predef$ArrowAssoc$.MODULE$;
/*  96 */       (new Tuple2[1])[0] = new Tuple2("apos", character5);
/*  96 */       this.unescMap = pairs().$plus$plus((GenTraversableOnce)scala.Predef$.MODULE$.Map().apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1])));
/*     */     }
/*     */     
/*     */     public Map<String, Object> pairs() {
/*     */       return this.pairs;
/*     */     }
/*     */     
/*     */     public Map<Object, String> escMap() {
/*     */       return this.escMap;
/*     */     }
/*     */     
/*     */     public static class anonfun$5 extends AbstractFunction1<Tuple2<String, Object>, Tuple2<Object, String>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Tuple2<Object, String> apply(Tuple2 x0$1) {
/*     */         if (x0$1 != null) {
/*     */           Character character = BoxesRunTime.boxToCharacter(x0$1._2$mcC$sp());
/*     */           scala.Predef$ predef$1 = scala.Predef$.MODULE$, predef$2 = scala.Predef$.MODULE$;
/*     */           String str = (new StringOps("&%s;")).format((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { x0$1._1() }));
/*     */           scala.Predef$ArrowAssoc$ predef$ArrowAssoc$ = scala.Predef$ArrowAssoc$.MODULE$;
/*     */           return new Tuple2(character, str);
/*     */         } 
/*     */         throw new MatchError(x0$1);
/*     */       }
/*     */     }
/*     */     
/*     */     public Map<String, Object> unescMap() {
/*  96 */       return this.unescMap;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$collectNamespaces$1 extends AbstractFunction2<HashSet<String>, Node, HashSet<String>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final HashSet<String> apply(HashSet<String> set, Node x) {
/* 141 */       Utility$.MODULE$.collectNamespaces(x, (Set<String>)set);
/* 141 */       return set;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$collectNamespaces$2 extends AbstractFunction1<MetaData, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Node n$1;
/*     */     
/*     */     private final Set set$1;
/*     */     
/*     */     public Utility$$anonfun$collectNamespaces$2(Node n$1, Set set$1) {}
/*     */     
/*     */     public final Object apply(MetaData a) {
/*     */       BoxedUnit boxedUnit;
/* 149 */       if (a instanceof PrefixedAttribute) {
/* 151 */         SetLike setLike = this.set$1.$plus$eq(a.getNamespace(this.n$1));
/*     */       } else {
/* 152 */         boxedUnit = BoxedUnit.UNIT;
/*     */       } 
/*     */       return boxedUnit;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$collectNamespaces$3 extends AbstractFunction1<Node, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Set set$1;
/*     */     
/*     */     public Utility$$anonfun$collectNamespaces$3(Set set$1) {}
/*     */     
/*     */     public final void apply(Node i) {
/* 155 */       Utility$.MODULE$.collectNamespaces(i, this.set$1);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$serialize$1 extends AbstractFunction1<Node, StringBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final StringBuilder sb$2;
/*     */     
/*     */     private final Enumeration.Value minimizeTags$2;
/*     */     
/*     */     private final Group x4$1;
/*     */     
/*     */     public final StringBuilder apply(Node c) {
/* 211 */       NamespaceBinding x$9 = this.x4$1.scope();
/* 211 */       StringBuilder x$10 = this.sb$2;
/* 211 */       Enumeration.Value x$11 = this.minimizeTags$2;
/* 211 */       boolean x$12 = Utility$.MODULE$.serialize$default$4(), x$13 = Utility$.MODULE$.serialize$default$5(), x$14 = Utility$.MODULE$.serialize$default$6();
/* 211 */       return Utility$.MODULE$.serialize(c, x$9, x$10, x$12, x$13, x$14, x$11);
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$serialize$1(StringBuilder sb$2, Enumeration.Value minimizeTags$2, Group x4$1) {}
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$sequenceToXML$1 extends AbstractFunction1<Node, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(Node x) {
/* 246 */       return Utility$.MODULE$.isAtomAndNotText(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$sequenceToXML$2 extends AbstractFunction1<Node, StringBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final NamespaceBinding pscope$1;
/*     */     
/*     */     private final StringBuilder sb$1;
/*     */     
/*     */     private final boolean stripComments$1;
/*     */     
/*     */     private final boolean decodeEntities$1;
/*     */     
/*     */     private final boolean preserveWhitespace$1;
/*     */     
/*     */     private final Enumeration.Value minimizeTags$1;
/*     */     
/*     */     public final StringBuilder apply(Node x$3) {
/* 256 */       return Utility$.MODULE$.serialize(x$3, this.pscope$1, this.sb$1, this.stripComments$1, this.decodeEntities$1, this.preserveWhitespace$1, this.minimizeTags$1);
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$sequenceToXML$2(NamespaceBinding pscope$1, StringBuilder sb$1, boolean stripComments$1, boolean decodeEntities$1, boolean preserveWhitespace$1, Enumeration.Value minimizeTags$1) {}
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$appendQuoted$1 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String s$1;
/*     */     
/*     */     public final void apply(StringBuilder x$7) {
/* 273 */       Utility$.MODULE$.appendQuoted(this.s$1, x$7);
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$appendQuoted$1(String s$1) {}
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$appendEscapedQuoted$1 extends AbstractFunction1<Object, StringBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final StringBuilder sb$3;
/*     */     
/*     */     public final StringBuilder apply(char c) {
/* 289 */       switch (c) {
/*     */         default:
/*     */         
/*     */         case '"':
/*     */           break;
/*     */       } 
/* 290 */       this.sb$3.append('\\');
/* 290 */       return this.sb$3.append('"');
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$appendEscapedQuoted$1(StringBuilder sb$3) {}
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$getName$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(char ch) {
/* 300 */       return Utility$.MODULE$.isNameChar(ch);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$1 extends AbstractFunction0.mcC.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final CharRef c$1;
/*     */     
/*     */     public final char apply() {
/* 342 */       return this.c$1.elem;
/*     */     }
/*     */     
/*     */     public char apply$mcC$sp() {
/* 342 */       return this.c$1.elem;
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$1(CharRef c$1) {}
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$2 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Iterator it$1;
/*     */     
/*     */     public final CharRef c$1;
/*     */     
/*     */     public final void apply() {
/* 342 */       this.c$1.elem = BoxesRunTime.unboxToChar(this.it$1.next());
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 342 */       this.c$1.elem = BoxesRunTime.unboxToChar(this.it$1.next());
/*     */     }
/*     */     
/*     */     public Utility$$anonfun$2(Iterator it$1, CharRef c$1) {}
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$6 extends AbstractFunction1<String, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final scala.runtime.Nothing$ apply(String s) {
/* 342 */       throw new RuntimeException(s);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Utility$$anonfun$7 extends AbstractFunction1<String, scala.runtime.Nothing$> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final scala.runtime.Nothing$ apply(String s) {
/* 342 */       throw new RuntimeException(s);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Utility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */