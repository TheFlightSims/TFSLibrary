/*     */ package scala.reflect;
/*     */ 
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.mutable.ArrayBuilder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.mutable.WrappedArray;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.runtime.Null$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\rmx!B\001\003\021\0039\021aD'b]&4Wm\035;GC\016$xN]=\013\005\r!\021a\002:fM2,7\r\036\006\002\013\005)1oY1mC\016\001\001C\001\005\n\033\005\021a!\002\006\003\021\003Y!aD'b]&4Wm\035;GC\016$xN]=\024\005%a\001CA\007\017\033\005!\021BA\b\005\005\031\te.\037*fM\")\021#\003C\001%\0051A(\0338jiz\"\022a\002\005\006)%!\t!F\001\017m\006dW/Z'b]&4Wm\035;t+\0051\002cA\f E9\021\001$\b\b\0033qi\021A\007\006\0037\031\ta\001\020:p_Rt\024\"A\003\n\005y!\021a\0029bG.\fw-Z\005\003A\005\022A\001T5ti*\021a\004\002\031\003G!\0022\001\003\023'\023\t)#A\001\bB]f4\026\r\\'b]&4Wm\035;\021\005\035BC\002\001\003\nSM\t\t\021!A\003\002)\0221a\030\0234#\tYc\006\005\002\016Y%\021Q\006\002\002\b\035>$\b.\0338h!\tiq&\003\0021\t\t\031\021I\\=\t\017IJ!\031!C\001g\005!!)\037;f+\005!\004c\001\005%kA\021QBN\005\003o\021\021AAQ=uK\"1\021(\003Q\001\nQ\nQAQ=uK\002BqaO\005C\002\023\005A(A\003TQ>\024H/F\001>!\rAAE\020\t\003\033}J!\001\021\003\003\013MCwN\035;\t\r\tK\001\025!\003>\003\031\031\006n\034:uA!9A)\003b\001\n\003)\025\001B\"iCJ,\022A\022\t\004\021\021:\005CA\007I\023\tIEA\001\003DQ\006\024\bBB&\nA\003%a)A\003DQ\006\024\b\005C\004N\023\t\007I\021\001(\002\007%sG/F\001P!\rAA\005\025\t\003\033EK!A\025\003\003\007%sG\017\003\004U\023\001\006IaT\001\005\023:$\b\005C\004W\023\t\007I\021A,\002\t1{gnZ\013\0021B\031\001\002J-\021\0055Q\026BA.\005\005\021auN\\4\t\ruK\001\025!\003Y\003\025auN\\4!\021\035y\026B1A\005\002\001\fQA\0227pCR,\022!\031\t\004\021\021\022\007CA\007d\023\t!GAA\003GY>\fG\017\003\004g\023\001\006I!Y\001\007\r2|\027\r\036\021\t\017!L!\031!C\001S\0061Ai\\;cY\026,\022A\033\t\004\021\021Z\007CA\007m\023\tiGA\001\004E_V\024G.\032\005\007_&\001\013\021\0026\002\017\021{WO\0317fA!9\021/\003b\001\n\003\021\030a\002\"p_2,\027M\\\013\002gB\031\001\002\n;\021\0055)\030B\001<\005\005\035\021un\0347fC:Da\001_\005!\002\023\031\030\001\003\"p_2,\027M\034\021\t\017iL!\031!C\001w\006!QK\\5u+\005a\bc\001\005%{B\021QB`\005\003\022\021A!\0268ji\"9\0211A\005!\002\023a\030!B+oSR\004\003\"CA\004\023\t\007I\021BA\005\003)y%M[3diRK\006+R\013\003\003\027\001b!!\004\002\030\005mQBAA\b\025\021\t\t\"a\005\002\t1\fgn\032\006\003\003+\tAA[1wC&!\021\021DA\b\005\025\031E.Y:t!\021\ti!!\b\n\t\005}\021q\002\002\007\037\nTWm\031;\t\021\005\r\022\002)A\005\003\027\t1b\0242kK\016$H+\027)FA!I\021qE\005C\002\023%\021\021F\001\f\035>$\b.\0338h)f\003V)\006\002\002,A1\021QBA\f\003[\001B!a\f\00265\021\021\021\007\006\004\003g!\021a\002:v]RLW.Z\005\005\003o\t\tD\001\005O_RD\027N\\4%\021!\tY$\003Q\001\n\005-\022\001\004(pi\"Lgn\032+Z!\026\003\003\"CA \023\t\007I\021BA!\003!qU\017\0347U3B+UCAA\"!\031\ti!a\006\002FA!\021qFA$\023\021\tI%!\r\003\0139+H\016\034\023\t\021\0055\023\002)A\005\003\007\n\021BT;mYRK\006+\022\021\t\023\005E\023B1A\005\002\005M\023aA!osV\021\021Q\013\t\005\021\005]c&C\002\002Z\t\021\001\"T1oS\032,7\017\036\005\t\003;J\001\025!\003\002V\005!\021I\\=!\021%\t\t'\003b\001\n\003\t\031'\001\004PE*,7\r^\013\003\003K\002R\001CA,\0037A\001\"!\033\nA\003%\021QM\001\b\037\nTWm\031;!\021%\ti'\003b\001\n\003\ty'\001\004B]f\024VMZ\013\003\003c\002B\001CA,\031!A\021QO\005!\002\023\t\t(A\004B]f\024VM\032\021\t\023\005e\024B1A\005\002\005m\024AB!osZ\013G.\006\002\002~A)\001\"a\026\002\000A\031Q\"!!\n\007\005\rEA\001\004B]f4\026\r\034\005\t\003\017K\001\025!\003\002~\0059\021I\\=WC2\004\003\"CAF\023\t\007I\021AAG\003\021qU\017\0347\026\005\005=\005#\002\005\002X\005E\005cA\007\002\024&\031\021Q\023\003\003\t9+H\016\034\005\t\0033K\001\025!\003\002\020\006)a*\0367mA!I\021QT\005C\002\023\005\021qT\001\b\035>$\b.\0338h+\t\t\t\013\005\003\t\003/Z\003\002CAS\023\001\006I!!)\002\0219{G\017[5oO\0022a!!+\n\t\005-&!F*j]\036dW\r^8o)f\004X-T1oS\032,7\017^\013\005\003[\013\031lE\003\002(2\ty\013E\003\t\003/\n\t\fE\002(\003g#\001\"!.\002(\n\007\021q\027\002\002)F\0211\006\004\005\013\003w\0139K!A!\002\023a\021!\002<bYV,\007bB\t\002(\022\005\021q\030\013\005\003\003\f)\r\005\004\002D\006\035\026\021W\007\002\023!9\0211XA_\001\004a\001bCAe\003OC)\031!C\001\003\027\fAB];oi&lWm\0217bgN,\"!!41\t\005=\0271\033\t\007\003\033\t9\"!5\021\007\035\n\031\016\002\007\002V\006]\027\021!A\001\006\003\t9L\001\002@a!Y\021\021\\AT\021\003\005\013\025BAg\0035\021XO\034;j[\026\034E.Y:tA!Y\021Q\\AT\021\013\007I\021IAp\003!!xn\025;sS:<WCAAq!\021\ti!a9\n\t\005\025\030q\002\002\007'R\024\030N\\4\t\027\005%\030q\025E\001B\003&\021\021]\001\ni>\034FO]5oO\002Bq!!<\n\t\003\ty/\001\006tS:<G.\032+za\026,B!!=\002xR!\0211_A}!\025A\021qKA{!\r9\023q\037\003\t\003k\013YO1\001\0028\"9\0211XAv\001\004a\001bBA\023\021\005\021q`\001\nG2\f7o\035+za\026,BA!\001\003\bQ!!1\001B\005!\025A\021q\013B\003!\r9#q\001\003\b\003k\013YP1\001+\021!\021Y!a?A\002\t5\021!B2mCjT\b\007\002B\b\005;\001bA!\005\003\030\tmabA\007\003\024%\031!Q\003\003\002\rA\023X\rZ3g\023\021\tIB!\007\013\007\tUA\001E\002(\005;!1Ba\b\003\n\005\005\t\021!B\001U\t\031q\fJ\035\t\017\005u\030\002\"\001\003$U!!Q\005B\026)!\0219C!\f\0032\t}\002#\002\005\002X\t%\002cA\024\003,\0219\021Q\027B\021\005\004Q\003\002\003B\006\005C\001\rAa\f\021\r\tE!q\003B\025\021!\021\031D!\tA\002\tU\022\001B1sOF\002DAa\016\003<A)\001\"a\026\003:A\031qEa\017\005\027\tu\"\021GA\001\002\003\025\tA\013\002\005?\022\n\004\007\003\005\003B\t\005\002\031\001B\"\003\021\t'oZ:\021\0135\021)E!\023\n\007\t\035CA\001\006=e\026\004X-\031;fIz\002DAa\023\003PA)\001\"a\026\003NA\031qEa\024\005\027\tE#1KA\001\002\003\025\tA\013\002\005?\022\n\024\007\003\005\003B\t\005\002\031\001B\"\021\035\ti0\003C\001\005/*BA!\027\003`QA!1\fB1\005_\022Y\bE\003\t\003/\022i\006E\002(\005?\"q!!.\003V\t\007!\006\003\005\003d\tU\003\031\001B3\003\031\001(/\0324jqB\"!q\rB6!\025A\021q\013B5!\r9#1\016\003\f\005[\022\t'!A\001\002\013\005!F\001\003`IE\022\004\002\003B\006\005+\002\rA!\0351\t\tM$q\017\t\007\005#\0219B!\036\021\007\035\0229\bB\006\003z\t=\024\021!A\001\006\003Q#\001B0%cMB\001B!\021\003V\001\007!Q\020\t\006\033\t\025#q\020\031\005\005\003\023)\tE\003\t\003/\022\031\tE\002(\005\013#1Ba\"\003|\005\005\t\021!B\001U\t!q\fJ\0315\r\035\021Y)CA\005\005\033\023q\002\0255b]R|W.T1oS\032,7\017^\013\005\005\037\033Ib\005\003\003\n\nE\005CBAb\005'\0339B\002\004\003\026&!!q\023\002\022\0072\f7o\035+za\026l\025M\\5gKN$X\003\002BM\005?\033RAa%\r\0057\003R\001CA,\005;\0032a\nBP\t\035\t)La%C\002)B1Ba\031\003\024\n\005\t\025!\003\003$B)QB!*\003*&\031!q\025\003\003\r=\003H/[8oa\021\021YKa,\021\013!\t9F!,\021\007\035\022y\013B\006\0032\n\005\026\021!A\001\006\003Q#\001B0%cYB1\"!3\003\024\n\025\r\021\"\001\0036V\021!q\027\031\005\005s\023i\f\005\004\003\022\t]!1\030\t\004O\tuFa\003B`\005\003\f\t\021!A\003\002)\022Aa\030\0232o!Y\021\021\034BJ\005\003\005\013\021\002Bba\021\021)M!3\021\r\tE!q\003Bd!\r9#\021\032\003\f\005\023\t-!A\001\002\013\005!\006C\006\003N\nM%Q1A\005B\t=\027!\004;za\026\f%oZ;nK:$8/\006\002\003RB!qc\bBja\021\021)N!7\021\013!\t9Fa6\021\007\035\022I\016B\006\003\\\nu\027\021!A\001\006\003Q#\001B0%caB1Ba8\003\024\n\005\t\025!\003\003b\006qA/\0379f\003J<W/\\3oiN\004\003\003B\f \005G\004DA!:\003jB)\001\"a\026\003hB\031qE!;\005\027\tm'Q\\A\001\002\003\025\tA\013\005\b#\tME\021\001Bw)!\021yO!=\003~\016\035\001CBAb\005'\023i\n\003\005\003d\t-\b\031\001Bz!\025i!Q\025B{a\021\0219Pa?\021\013!\t9F!?\021\007\035\022Y\020B\006\0032\nE\030\021!A\001\006\003Q\003\002CAe\005W\004\rAa@1\t\r\0051Q\001\t\007\005#\0219ba\001\021\007\035\032)\001B\006\003@\nu\030\021!A\001\006\003Q\003\002\003Bg\005W\004\ra!\003\021\t]y21\002\031\005\007\033\031\t\002E\003\t\003/\032y\001E\002(\007#!1Ba7\004\b\005\005\t\021!B\001U!A\021Q\034BJ\t\003\032)\002\006\002\002bB\031qe!\007\005\017\005U&\021\022b\001U!i1Q\004BE\005\003\005\013\021BB\020\005g\013Qb\030:v]RLW.Z\"mCN\034\b\007BB\021\007K\001bA!\005\003\030\r\r\002cA\024\004&\021Y1qEB\016\003\003\005\tQ!\001+\005\021yF%M\033\t\027\005u'\021\022BC\002\023\00531F\013\003\007[\001BA!\005\0040%!\021Q\035B\r\021-\tIO!#\003\002\003\006Ia!\f\t\017E\021I\t\"\001\0046Q11qGB\035\007\007\002b!a1\003\n\016]\001\002CB\017\007g\001\raa\0171\t\ru2\021\t\t\007\005#\0219ba\020\021\007\035\032\t\005B\006\004(\re\022\021!A\001\006\003Q\003\002CAo\007g\001\ra!\f\t\021\r\035#\021\022C!\007\023\na!Z9vC2\034Hc\001;\004L!91QJB#\001\004q\023\001\002;iCRD!b!\025\003\n\n\007I\021IB*\003!A\027m\0355D_\022,W#\001)\t\021\r]#\021\022Q\001\nA\013\021\002[1tQ\016{G-\032\021\t\017\rm\023\002\"\001\004^\005I\021M\035:bsRK\b/Z\013\005\007?\032Y\007\006\003\004b\r5\004#\002\005\002X\r\r\004#B\007\004f\r%\024bAB4\t\t)\021I\035:bsB\031qea\033\005\017\005U6\021\fb\001U!A1qNB-\001\004\031\t(A\002be\036\004Daa\035\004xA)\001\"a\026\004vA\031qea\036\005\027\re4QNA\001\002\003\025\tA\013\002\005?\022\n\024\bC\004\004~%!\taa \002\031\005\0247\017\036:bGR$\026\020]3\026\t\r\0055q\021\013\013\007\007\033Ii!&\004\032\016\035\006#\002\005\002X\r\025\005cA\024\004\b\0229\021QWB>\005\004Q\003\002\003B2\007w\002\raa#1\t\r55\021\023\t\006\021\005]3q\022\t\004O\rEEaCBJ\007\023\013\t\021!A\003\002)\022Aa\030\0233a!A1qSB>\001\004\031i#\001\003oC6,\007\002CBN\007w\002\ra!(\002\025U\004\b/\032:C_VtG\r\r\003\004 \016\r\006C\002B\t\005/\031\t\013E\002(\007G#1b!*\004\032\006\005\t\021!B\001U\t!q\f\n\0322\021!\021\tea\037A\002\r%\006#B\007\003F\r-\006\007BBW\007c\003R\001CA,\007_\0032aJBY\t-\031\031la*\002\002\003\005)\021\001\026\003\t}##G\r\005\b\007oKA\021AB]\00319\030\016\0343dCJ$G+\0379f+\021\031Yl!1\025\r\ru61YBi!\025A\021qKB`!\r93\021\031\003\b\003k\033)L1\001+\021!\031)m!.A\002\r\035\027A\0037po\026\024(i\\;oIB\"1\021ZBg!\025A\021qKBf!\r93Q\032\003\f\007\037\034\031-!A\001\002\013\005!F\001\003`II\032\004\002CBN\007k\003\raa51\t\rU7\021\034\t\006\021\005]3q\033\t\004O\reGaCBn\007#\f\t\021!A\003\002)\022Aa\030\0233i!91q\\\005\005\002\r\005\030\001E5oi\026\0248/Z2uS>tG+\0379f+\021\031\031o!;\025\t\r\02581\036\t\006\021\005]3q\035\t\004O\r%HaBA[\007;\024\rA\013\005\t\007[\034i\0161\001\004p\0069\001/\031:f]R\034\b#B\007\003F\rE\b\007BBz\007o\004R\001CA,\007k\0042aJB|\t-\031Ipa;\002\002\003\005)\021\001\026\003\t}##'\016")
/*     */ public final class ManifestFactory {
/*     */   public static <T> Manifest<T> intersectionType(Seq<Manifest<?>> paramSeq) {
/*     */     return ManifestFactory$.MODULE$.intersectionType(paramSeq);
/*     */   }
/*     */   
/*     */   public static <T> Manifest<T> wildcardType(Manifest<?> paramManifest1, Manifest<?> paramManifest2) {
/*     */     return ManifestFactory$.MODULE$.wildcardType(paramManifest1, paramManifest2);
/*     */   }
/*     */   
/*     */   public static <T> Manifest<T> abstractType(Manifest<?> paramManifest, String paramString, Class<?> paramClass, Seq<Manifest<?>> paramSeq) {
/*     */     return ManifestFactory$.MODULE$.abstractType(paramManifest, paramString, paramClass, paramSeq);
/*     */   }
/*     */   
/*     */   public static <T> Manifest<Object> arrayType(Manifest<?> paramManifest) {
/*     */     return ManifestFactory$.MODULE$.arrayType(paramManifest);
/*     */   }
/*     */   
/*     */   public static <T> Manifest<T> classType(Manifest<?> paramManifest, Class<?> paramClass, Seq<Manifest<?>> paramSeq) {
/*     */     return ManifestFactory$.MODULE$.classType(paramManifest, paramClass, paramSeq);
/*     */   }
/*     */   
/*     */   public static <T> Manifest<T> classType(Class<T> paramClass, Manifest<?> paramManifest, Seq<Manifest<?>> paramSeq) {
/*     */     return ManifestFactory$.MODULE$.classType(paramClass, paramManifest, paramSeq);
/*     */   }
/*     */   
/*     */   public static <T> Manifest<T> classType(Class<?> paramClass) {
/*     */     return ManifestFactory$.MODULE$.classType(paramClass);
/*     */   }
/*     */   
/*     */   public static <T> Manifest<T> singleType(Object paramObject) {
/*     */     return ManifestFactory$.MODULE$.singleType(paramObject);
/*     */   }
/*     */   
/*     */   public static Manifest<Nothing$> Nothing() {
/*     */     return ManifestFactory$.MODULE$.Nothing();
/*     */   }
/*     */   
/*     */   public static Manifest<Null$> Null() {
/*     */     return ManifestFactory$.MODULE$.Null();
/*     */   }
/*     */   
/*     */   public static Manifest<Object> AnyVal() {
/*     */     return ManifestFactory$.MODULE$.AnyVal();
/*     */   }
/*     */   
/*     */   public static Manifest<Object> AnyRef() {
/*     */     return ManifestFactory$.MODULE$.AnyRef();
/*     */   }
/*     */   
/*     */   public static Manifest<Object> Object() {
/*     */     return ManifestFactory$.MODULE$.Object();
/*     */   }
/*     */   
/*     */   public static Manifest<Object> Any() {
/*     */     return ManifestFactory$.MODULE$.Any();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<BoxedUnit> Unit() {
/*     */     return ManifestFactory$.MODULE$.Unit();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<Object> Boolean() {
/*     */     return ManifestFactory$.MODULE$.Boolean();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<Object> Double() {
/*     */     return ManifestFactory$.MODULE$.Double();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<Object> Float() {
/*     */     return ManifestFactory$.MODULE$.Float();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<Object> Long() {
/*     */     return ManifestFactory$.MODULE$.Long();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<Object> Int() {
/*     */     return ManifestFactory$.MODULE$.Int();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<Object> Char() {
/*     */     return ManifestFactory$.MODULE$.Char();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<Object> Short() {
/*     */     return ManifestFactory$.MODULE$.Short();
/*     */   }
/*     */   
/*     */   public static AnyValManifest<Object> Byte() {
/*     */     return ManifestFactory$.MODULE$.Byte();
/*     */   }
/*     */   
/*     */   public static List<AnyValManifest<?>> valueManifests() {
/*     */     return ManifestFactory$.MODULE$.valueManifests();
/*     */   }
/*     */   
/*     */   public static class $anon$6 extends AnyValManifest<Object> {
/*     */     public $anon$6() {
/*  88 */       super("Byte");
/*     */     }
/*     */     
/*     */     public Class<Byte> runtimeClass() {
/*  89 */       return (Class)byte.class;
/*     */     }
/*     */     
/*     */     public byte[] newArray(int len) {
/*  90 */       return new byte[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<Object> newWrappedArray(int len) {
/*  91 */       return (WrappedArray<Object>)new WrappedArray.ofByte(new byte[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<Object> newArrayBuilder() {
/*  92 */       return (ArrayBuilder<Object>)new ArrayBuilder.ofByte();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  93 */       return package$.MODULE$.Manifest().Byte();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$7 extends AnyValManifest<Object> {
/*     */     public $anon$7() {
/*  96 */       super("Short");
/*     */     }
/*     */     
/*     */     public Class<Short> runtimeClass() {
/*  97 */       return (Class)short.class;
/*     */     }
/*     */     
/*     */     public short[] newArray(int len) {
/*  98 */       return new short[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<Object> newWrappedArray(int len) {
/*  99 */       return (WrappedArray<Object>)new WrappedArray.ofShort(new short[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<Object> newArrayBuilder() {
/* 100 */       return (ArrayBuilder<Object>)new ArrayBuilder.ofShort();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 101 */       return package$.MODULE$.Manifest().Short();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$8 extends AnyValManifest<Object> {
/*     */     public $anon$8() {
/* 104 */       super("Char");
/*     */     }
/*     */     
/*     */     public Class<Character> runtimeClass() {
/* 105 */       return (Class)char.class;
/*     */     }
/*     */     
/*     */     public char[] newArray(int len) {
/* 106 */       return new char[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<Object> newWrappedArray(int len) {
/* 107 */       return (WrappedArray<Object>)new WrappedArray.ofChar(new char[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<Object> newArrayBuilder() {
/* 108 */       return (ArrayBuilder<Object>)new ArrayBuilder.ofChar();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 109 */       return package$.MODULE$.Manifest().Char();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$9 extends AnyValManifest<Object> {
/*     */     public $anon$9() {
/* 112 */       super("Int");
/*     */     }
/*     */     
/*     */     public Class<Integer> runtimeClass() {
/* 113 */       return (Class)int.class;
/*     */     }
/*     */     
/*     */     public int[] newArray(int len) {
/* 114 */       return new int[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<Object> newWrappedArray(int len) {
/* 115 */       return (WrappedArray<Object>)new WrappedArray.ofInt(new int[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<Object> newArrayBuilder() {
/* 116 */       return (ArrayBuilder<Object>)new ArrayBuilder.ofInt();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 117 */       return package$.MODULE$.Manifest().Int();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$10 extends AnyValManifest<Object> {
/*     */     public $anon$10() {
/* 120 */       super("Long");
/*     */     }
/*     */     
/*     */     public Class<Long> runtimeClass() {
/* 121 */       return (Class)long.class;
/*     */     }
/*     */     
/*     */     public long[] newArray(int len) {
/* 122 */       return new long[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<Object> newWrappedArray(int len) {
/* 123 */       return (WrappedArray<Object>)new WrappedArray.ofLong(new long[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<Object> newArrayBuilder() {
/* 124 */       return (ArrayBuilder<Object>)new ArrayBuilder.ofLong();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 125 */       return package$.MODULE$.Manifest().Long();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$11 extends AnyValManifest<Object> {
/*     */     public $anon$11() {
/* 128 */       super("Float");
/*     */     }
/*     */     
/*     */     public Class<Float> runtimeClass() {
/* 129 */       return (Class)float.class;
/*     */     }
/*     */     
/*     */     public float[] newArray(int len) {
/* 130 */       return new float[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<Object> newWrappedArray(int len) {
/* 131 */       return (WrappedArray<Object>)new WrappedArray.ofFloat(new float[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<Object> newArrayBuilder() {
/* 132 */       return (ArrayBuilder<Object>)new ArrayBuilder.ofFloat();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 133 */       return package$.MODULE$.Manifest().Float();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$12 extends AnyValManifest<Object> {
/*     */     public $anon$12() {
/* 136 */       super("Double");
/*     */     }
/*     */     
/*     */     public Class<Double> runtimeClass() {
/* 137 */       return (Class)double.class;
/*     */     }
/*     */     
/*     */     public double[] newArray(int len) {
/* 138 */       return new double[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<Object> newWrappedArray(int len) {
/* 139 */       return (WrappedArray<Object>)new WrappedArray.ofDouble(new double[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<Object> newArrayBuilder() {
/* 140 */       return (ArrayBuilder<Object>)new ArrayBuilder.ofDouble();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 141 */       return package$.MODULE$.Manifest().Double();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$13 extends AnyValManifest<Object> {
/*     */     public $anon$13() {
/* 144 */       super("Boolean");
/*     */     }
/*     */     
/*     */     public Class<Boolean> runtimeClass() {
/* 145 */       return (Class)boolean.class;
/*     */     }
/*     */     
/*     */     public boolean[] newArray(int len) {
/* 146 */       return new boolean[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<Object> newWrappedArray(int len) {
/* 147 */       return (WrappedArray<Object>)new WrappedArray.ofBoolean(new boolean[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<Object> newArrayBuilder() {
/* 148 */       return (ArrayBuilder<Object>)new ArrayBuilder.ofBoolean();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 149 */       return package$.MODULE$.Manifest().Boolean();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$14 extends AnyValManifest<BoxedUnit> {
/*     */     public $anon$14() {
/* 152 */       super("Unit");
/*     */     }
/*     */     
/*     */     public Class<Void> runtimeClass() {
/* 153 */       return (Class)void.class;
/*     */     }
/*     */     
/*     */     public BoxedUnit[] newArray(int len) {
/* 154 */       return new BoxedUnit[len];
/*     */     }
/*     */     
/*     */     public WrappedArray<BoxedUnit> newWrappedArray(int len) {
/* 155 */       return (WrappedArray<BoxedUnit>)new WrappedArray.ofUnit(new BoxedUnit[len]);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<BoxedUnit> newArrayBuilder() {
/* 156 */       return (ArrayBuilder<BoxedUnit>)new ArrayBuilder.ofUnit();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 157 */       return package$.MODULE$.Manifest().Unit();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$1 extends PhantomManifest<Object> {
/*     */     public $anon$1() {
/* 164 */       super(ManifestFactory$.MODULE$.scala$reflect$ManifestFactory$$ObjectTYPE(), "Any");
/*     */     }
/*     */     
/*     */     public Object[] newArray(int len) {
/* 165 */       return new Object[len];
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 166 */       return (that == this);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 167 */       return package$.MODULE$.Manifest().Any();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$2 extends PhantomManifest<Object> {
/*     */     public $anon$2() {
/* 170 */       super(ManifestFactory$.MODULE$.scala$reflect$ManifestFactory$$ObjectTYPE(), "Object");
/*     */     }
/*     */     
/*     */     public Object[] newArray(int len) {
/* 171 */       return new Object[len];
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 172 */       return (that == this || that == ManifestFactory$.MODULE$.Any());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 173 */       return package$.MODULE$.Manifest().Object();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$3 extends PhantomManifest<Object> {
/*     */     public $anon$3() {
/* 178 */       super(ManifestFactory$.MODULE$.scala$reflect$ManifestFactory$$ObjectTYPE(), "AnyVal");
/*     */     }
/*     */     
/*     */     public Object[] newArray(int len) {
/* 179 */       return new Object[len];
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 180 */       return (that == this || that == ManifestFactory$.MODULE$.Any());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 181 */       return package$.MODULE$.Manifest().AnyVal();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$4 extends PhantomManifest<Null$> {
/*     */     public $anon$4() {
/* 184 */       super(ManifestFactory$.MODULE$.scala$reflect$ManifestFactory$$NullTYPE(), "Null");
/*     */     }
/*     */     
/*     */     public Object[] newArray(int len) {
/* 185 */       return new Object[len];
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 187 */       return (that != null && that != ManifestFactory$.MODULE$.Nothing() && !that.$less$colon$less(ManifestFactory$.MODULE$.AnyVal()));
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 188 */       return package$.MODULE$.Manifest().Null();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class $anon$5 extends PhantomManifest<Nothing$> {
/*     */     public $anon$5() {
/* 191 */       super(ManifestFactory$.MODULE$.scala$reflect$ManifestFactory$$NothingTYPE(), "Nothing");
/*     */     }
/*     */     
/*     */     public Object[] newArray(int len) {
/* 192 */       return new Object[len];
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 193 */       return (that != null);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 194 */       return package$.MODULE$.Manifest().Nothing();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SingletonTypeManifest<T> implements Manifest<T> {
/*     */     private final Object value;
/*     */     
/*     */     private Class<?> runtimeClass;
/*     */     
/*     */     private String toString;
/*     */     
/*     */     private volatile byte bitmap$0;
/*     */     
/*     */     public List<Manifest<?>> typeArguments() {
/* 197 */       return Manifest$class.typeArguments(this);
/*     */     }
/*     */     
/*     */     public Manifest<T[]> arrayManifest() {
/* 197 */       return Manifest$class.arrayManifest(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object that) {
/* 197 */       return Manifest$class.canEqual(this, that);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/* 197 */       return Manifest$class.equals(this, that);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 197 */       return Manifest$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public ClassTag<T[]> wrap() {
/* 197 */       return ClassTag$class.wrap(this);
/*     */     }
/*     */     
/*     */     public T[] newArray(int len) {
/* 197 */       return (T[])ClassTag$class.newArray(this, len);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(Object x) {
/* 197 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(byte x) {
/* 197 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(short x) {
/* 197 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(char x) {
/* 197 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(int x) {
/* 197 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(long x) {
/* 197 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(float x) {
/* 197 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(double x) {
/* 197 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(boolean x) {
/* 197 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(BoxedUnit x) {
/* 197 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Class<?> erasure() {
/* 197 */       return ClassManifestDeprecatedApis$class.erasure(this);
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 197 */       return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
/*     */     }
/*     */     
/*     */     public boolean $greater$colon$greater(ClassTag that) {
/* 197 */       return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
/*     */     }
/*     */     
/*     */     public <T> Class<Object> arrayClass(Class tp) {
/* 197 */       return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
/*     */     }
/*     */     
/*     */     public T[][] newArray2(int len) {
/* 197 */       return (T[][])ClassManifestDeprecatedApis$class.newArray2(this, len);
/*     */     }
/*     */     
/*     */     public T[][][] newArray3(int len) {
/* 197 */       return (T[][][])ClassManifestDeprecatedApis$class.newArray3(this, len);
/*     */     }
/*     */     
/*     */     public T[][][][] newArray4(int len) {
/* 197 */       return (T[][][][])ClassManifestDeprecatedApis$class.newArray4(this, len);
/*     */     }
/*     */     
/*     */     public T[][][][][] newArray5(int len) {
/* 197 */       return (T[][][][][])ClassManifestDeprecatedApis$class.newArray5(this, len);
/*     */     }
/*     */     
/*     */     public WrappedArray<T> newWrappedArray(int len) {
/* 197 */       return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<T> newArrayBuilder() {
/* 197 */       return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
/*     */     }
/*     */     
/*     */     public String argString() {
/* 197 */       return ClassManifestDeprecatedApis$class.argString(this);
/*     */     }
/*     */     
/*     */     public SingletonTypeManifest(Object value) {
/* 197 */       ClassManifestDeprecatedApis$class.$init$(this);
/* 197 */       ClassTag$class.$init$(this);
/* 197 */       Manifest$class.$init$(this);
/*     */     }
/*     */     
/*     */     private Class runtimeClass$lzycompute() {
/* 198 */       synchronized (this) {
/* 198 */         if ((byte)(this.bitmap$0 & 0x1) == 0) {
/* 198 */           this.runtimeClass = this.value.getClass();
/* 198 */           this.bitmap$0 = (byte)(this.bitmap$0 | 0x1);
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/reflect/ManifestFactory}.Lscala/reflect/ManifestFactory$SingletonTypeManifest;}} */
/* 198 */         return this.runtimeClass;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Class<?> runtimeClass() {
/* 198 */       return ((byte)(this.bitmap$0 & 0x1) == 0) ? runtimeClass$lzycompute() : this.runtimeClass;
/*     */     }
/*     */     
/*     */     private String toString$lzycompute() {
/* 199 */       synchronized (this) {
/* 199 */         if ((byte)(this.bitmap$0 & 0x2) == 0) {
/* 199 */           this.toString = (new StringBuilder()).append(this.value.toString()).append(".type").toString();
/* 199 */           this.bitmap$0 = (byte)(this.bitmap$0 | 0x2);
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/reflect/ManifestFactory}.Lscala/reflect/ManifestFactory$SingletonTypeManifest;}} */
/* 199 */         return this.toString;
/*     */       } 
/*     */     }
/*     */     
/*     */     public String toString() {
/* 199 */       return ((byte)(this.bitmap$0 & 0x2) == 0) ? toString$lzycompute() : this.toString;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class PhantomManifest<T> extends ClassTypeManifest<T> {
/*     */     private final String toString;
/*     */     
/*     */     private final int hashCode;
/*     */     
/*     */     public String toString() {
/* 228 */       return this.toString;
/*     */     }
/*     */     
/*     */     public PhantomManifest(Class<?> _runtimeClass, String toString) {
/* 228 */       super((Option<Manifest<?>>)None$.MODULE$, _runtimeClass, (List<Manifest<?>>)Nil$.MODULE$);
/* 230 */       this.hashCode = System.identityHashCode(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/*     */       return (this == that);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 230 */       return this.hashCode;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ClassTypeManifest<T> implements Manifest<T> {
/*     */     private final Option<Manifest<?>> prefix;
/*     */     
/*     */     private final Class<?> runtimeClass;
/*     */     
/*     */     private final List<Manifest<?>> typeArguments;
/*     */     
/*     */     public Manifest<Object> arrayManifest() {
/* 235 */       return Manifest$class.arrayManifest(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object that) {
/* 235 */       return Manifest$class.canEqual(this, that);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/* 235 */       return Manifest$class.equals(this, that);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 235 */       return Manifest$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public ClassTag<Object> wrap() {
/* 235 */       return ClassTag$class.wrap(this);
/*     */     }
/*     */     
/*     */     public Object newArray(int len) {
/* 235 */       return ClassTag$class.newArray(this, len);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(Object x) {
/* 235 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(byte x) {
/* 235 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(short x) {
/* 235 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(char x) {
/* 235 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(int x) {
/* 235 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(long x) {
/* 235 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(float x) {
/* 235 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(double x) {
/* 235 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(boolean x) {
/* 235 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(BoxedUnit x) {
/* 235 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Class<?> erasure() {
/* 235 */       return ClassManifestDeprecatedApis$class.erasure(this);
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 235 */       return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
/*     */     }
/*     */     
/*     */     public boolean $greater$colon$greater(ClassTag that) {
/* 235 */       return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
/*     */     }
/*     */     
/*     */     public <T> Class<Object> arrayClass(Class tp) {
/* 235 */       return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
/*     */     }
/*     */     
/*     */     public Object[] newArray2(int len) {
/* 235 */       return ClassManifestDeprecatedApis$class.newArray2(this, len);
/*     */     }
/*     */     
/*     */     public Object[][] newArray3(int len) {
/* 235 */       return ClassManifestDeprecatedApis$class.newArray3(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][] newArray4(int len) {
/* 235 */       return ClassManifestDeprecatedApis$class.newArray4(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][][] newArray5(int len) {
/* 235 */       return ClassManifestDeprecatedApis$class.newArray5(this, len);
/*     */     }
/*     */     
/*     */     public WrappedArray<T> newWrappedArray(int len) {
/* 235 */       return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<T> newArrayBuilder() {
/* 235 */       return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
/*     */     }
/*     */     
/*     */     public String argString() {
/* 235 */       return ClassManifestDeprecatedApis$class.argString(this);
/*     */     }
/*     */     
/*     */     public ClassTypeManifest(Option<Manifest<?>> prefix, Class<?> runtimeClass, List<Manifest<?>> typeArguments) {
/* 235 */       ClassManifestDeprecatedApis$class.$init$(this);
/* 235 */       ClassTag$class.$init$(this);
/* 235 */       Manifest$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Class<?> runtimeClass() {
/* 236 */       return this.runtimeClass;
/*     */     }
/*     */     
/*     */     public List<Manifest<?>> typeArguments() {
/* 237 */       return this.typeArguments;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 239 */       if (this.prefix.isEmpty()) {
/*     */       
/*     */       } else {
/*     */       
/*     */       } 
/* 240 */       return (new StringBuilder()).append((new StringBuilder()).append(((ClassTag)this.prefix.get()).toString()).append("#").toString()).append(erasure().isArray() ? "Array" : erasure().getName())
/* 241 */         .append(argString()).toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ManifestFactory$$anon$15 implements Manifest<T> {
/*     */     private final List<Manifest<?>> typeArguments;
/*     */     
/*     */     private final Manifest prefix$1;
/*     */     
/*     */     private final String name$1;
/*     */     
/*     */     private final Class upperBound$1;
/*     */     
/*     */     public Manifest<Object> arrayManifest() {
/* 251 */       return Manifest$class.arrayManifest(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object that) {
/* 251 */       return Manifest$class.canEqual(this, that);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/* 251 */       return Manifest$class.equals(this, that);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 251 */       return Manifest$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public ClassTag<Object> wrap() {
/* 251 */       return ClassTag$class.wrap(this);
/*     */     }
/*     */     
/*     */     public Object newArray(int len) {
/* 251 */       return ClassTag$class.newArray(this, len);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(Object x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(byte x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(short x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(char x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(int x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(long x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(float x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(double x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(boolean x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(BoxedUnit x) {
/* 251 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Class<?> erasure() {
/* 251 */       return ClassManifestDeprecatedApis$class.erasure(this);
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 251 */       return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
/*     */     }
/*     */     
/*     */     public boolean $greater$colon$greater(ClassTag that) {
/* 251 */       return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
/*     */     }
/*     */     
/*     */     public <T> Class<Object> arrayClass(Class tp) {
/* 251 */       return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
/*     */     }
/*     */     
/*     */     public Object[] newArray2(int len) {
/* 251 */       return ClassManifestDeprecatedApis$class.newArray2(this, len);
/*     */     }
/*     */     
/*     */     public Object[][] newArray3(int len) {
/* 251 */       return ClassManifestDeprecatedApis$class.newArray3(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][] newArray4(int len) {
/* 251 */       return ClassManifestDeprecatedApis$class.newArray4(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][][] newArray5(int len) {
/* 251 */       return ClassManifestDeprecatedApis$class.newArray5(this, len);
/*     */     }
/*     */     
/*     */     public WrappedArray<T> newWrappedArray(int len) {
/* 251 */       return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<T> newArrayBuilder() {
/* 251 */       return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
/*     */     }
/*     */     
/*     */     public String argString() {
/* 251 */       return ClassManifestDeprecatedApis$class.argString(this);
/*     */     }
/*     */     
/*     */     public ManifestFactory$$anon$15(Manifest prefix$1, String name$1, Class upperBound$1, Seq args$1) {
/* 251 */       ClassManifestDeprecatedApis$class.$init$(this);
/* 251 */       ClassTag$class.$init$(this);
/* 251 */       Manifest$class.$init$(this);
/* 253 */       this.typeArguments = args$1.toList();
/*     */     }
/*     */     
/*     */     public Class<?> runtimeClass() {
/*     */       return this.upperBound$1;
/*     */     }
/*     */     
/*     */     public List<Manifest<?>> typeArguments() {
/* 253 */       return this.typeArguments;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 254 */       return (new StringBuilder()).append(this.prefix$1.toString()).append("#").append(this.name$1).append(argString()).toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ManifestFactory$$anon$16 implements Manifest<T> {
/*     */     private final Manifest lowerBound$1;
/*     */     
/*     */     private final Manifest upperBound$2;
/*     */     
/*     */     public List<Manifest<?>> typeArguments() {
/* 260 */       return Manifest$class.typeArguments(this);
/*     */     }
/*     */     
/*     */     public Manifest<Object> arrayManifest() {
/* 260 */       return Manifest$class.arrayManifest(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object that) {
/* 260 */       return Manifest$class.canEqual(this, that);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/* 260 */       return Manifest$class.equals(this, that);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 260 */       return Manifest$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public ClassTag<Object> wrap() {
/* 260 */       return ClassTag$class.wrap(this);
/*     */     }
/*     */     
/*     */     public Object newArray(int len) {
/* 260 */       return ClassTag$class.newArray(this, len);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(Object x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(byte x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(short x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(char x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(int x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(long x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(float x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(double x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(boolean x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(BoxedUnit x) {
/* 260 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Class<?> erasure() {
/* 260 */       return ClassManifestDeprecatedApis$class.erasure(this);
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 260 */       return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
/*     */     }
/*     */     
/*     */     public boolean $greater$colon$greater(ClassTag that) {
/* 260 */       return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
/*     */     }
/*     */     
/*     */     public <T> Class<Object> arrayClass(Class tp) {
/* 260 */       return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
/*     */     }
/*     */     
/*     */     public Object[] newArray2(int len) {
/* 260 */       return ClassManifestDeprecatedApis$class.newArray2(this, len);
/*     */     }
/*     */     
/*     */     public Object[][] newArray3(int len) {
/* 260 */       return ClassManifestDeprecatedApis$class.newArray3(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][] newArray4(int len) {
/* 260 */       return ClassManifestDeprecatedApis$class.newArray4(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][][] newArray5(int len) {
/* 260 */       return ClassManifestDeprecatedApis$class.newArray5(this, len);
/*     */     }
/*     */     
/*     */     public WrappedArray<T> newWrappedArray(int len) {
/* 260 */       return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<T> newArrayBuilder() {
/* 260 */       return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
/*     */     }
/*     */     
/*     */     public String argString() {
/* 260 */       return ClassManifestDeprecatedApis$class.argString(this);
/*     */     }
/*     */     
/*     */     public ManifestFactory$$anon$16(Manifest lowerBound$1, Manifest upperBound$2) {
/* 260 */       ClassManifestDeprecatedApis$class.$init$(this);
/* 260 */       ClassTag$class.$init$(this);
/* 260 */       Manifest$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Class<?> runtimeClass() {
/* 261 */       return this.upperBound$2.erasure();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 264 */       return (new StringBuilder()).append("_").append((this.lowerBound$1 == ManifestFactory$.MODULE$.Nothing()) ? "" : (new StringBuilder()).append(" >: ").append(this.lowerBound$1).toString())
/* 265 */         .append((this.upperBound$2 == ManifestFactory$.MODULE$.Nothing()) ? "" : (new StringBuilder()).append(" <: ").append(this.upperBound$2).toString()).toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ManifestFactory$$anon$17 implements Manifest<T> {
/*     */     private final Seq parents$1;
/*     */     
/*     */     public List<Manifest<?>> typeArguments() {
/* 270 */       return Manifest$class.typeArguments(this);
/*     */     }
/*     */     
/*     */     public Manifest<Object> arrayManifest() {
/* 270 */       return Manifest$class.arrayManifest(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object that) {
/* 270 */       return Manifest$class.canEqual(this, that);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/* 270 */       return Manifest$class.equals(this, that);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 270 */       return Manifest$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public ClassTag<Object> wrap() {
/* 270 */       return ClassTag$class.wrap(this);
/*     */     }
/*     */     
/*     */     public Object newArray(int len) {
/* 270 */       return ClassTag$class.newArray(this, len);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(Object x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(byte x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(short x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(char x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(int x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(long x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(float x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(double x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(boolean x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Option<T> unapply(BoxedUnit x) {
/* 270 */       return ClassTag$class.unapply(this, x);
/*     */     }
/*     */     
/*     */     public Class<?> erasure() {
/* 270 */       return ClassManifestDeprecatedApis$class.erasure(this);
/*     */     }
/*     */     
/*     */     public boolean $less$colon$less(ClassTag that) {
/* 270 */       return ClassManifestDeprecatedApis$class.$less$colon$less(this, that);
/*     */     }
/*     */     
/*     */     public boolean $greater$colon$greater(ClassTag that) {
/* 270 */       return ClassManifestDeprecatedApis$class.$greater$colon$greater(this, that);
/*     */     }
/*     */     
/*     */     public <T> Class<Object> arrayClass(Class tp) {
/* 270 */       return ClassManifestDeprecatedApis$class.arrayClass(this, tp);
/*     */     }
/*     */     
/*     */     public Object[] newArray2(int len) {
/* 270 */       return ClassManifestDeprecatedApis$class.newArray2(this, len);
/*     */     }
/*     */     
/*     */     public Object[][] newArray3(int len) {
/* 270 */       return ClassManifestDeprecatedApis$class.newArray3(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][] newArray4(int len) {
/* 270 */       return ClassManifestDeprecatedApis$class.newArray4(this, len);
/*     */     }
/*     */     
/*     */     public Object[][][][] newArray5(int len) {
/* 270 */       return ClassManifestDeprecatedApis$class.newArray5(this, len);
/*     */     }
/*     */     
/*     */     public WrappedArray<T> newWrappedArray(int len) {
/* 270 */       return ClassManifestDeprecatedApis$class.newWrappedArray(this, len);
/*     */     }
/*     */     
/*     */     public ArrayBuilder<T> newArrayBuilder() {
/* 270 */       return ClassManifestDeprecatedApis$class.newArrayBuilder(this);
/*     */     }
/*     */     
/*     */     public String argString() {
/* 270 */       return ClassManifestDeprecatedApis$class.argString(this);
/*     */     }
/*     */     
/*     */     public ManifestFactory$$anon$17(Seq parents$1) {
/* 270 */       ClassManifestDeprecatedApis$class.$init$(this);
/* 270 */       ClassTag$class.$init$(this);
/* 270 */       Manifest$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Class<?> runtimeClass() {
/* 271 */       return ((ClassManifestDeprecatedApis)this.parents$1.head()).erasure();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 272 */       return this.parents$1.mkString(" with ");
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\ManifestFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */