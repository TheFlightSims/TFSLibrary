/*     */ package scala.io;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.mutable.HashMap;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021\005v!B\001\003\021\0039\021A\003\"zi\026\004\026nY6mK*\0211\001B\001\003S>T\021!B\001\006g\016\fG.Y\002\001!\tA\021\"D\001\003\r\025Q!\001#\001\f\005)\021\025\020^3QS\016\\G.Z\n\003\0231\001\"!\004\b\016\003\021I!a\004\003\003\r\005s\027PU3g\021\025\t\022\002\"\001\023\003\031a\024N\\5u}Q\tqAB\003\025\023\005\005QCA\002T!V+\"AF\017\024\005Ma\001\"B\t\024\t\003AB#A\r\021\007i\0312$D\001\n!\taR\004\004\001\005\013y\031\"\031A\020\003\003Q\013\"\001I\022\021\0055\t\023B\001\022\005\005\035qu\016\0365j]\036\004\"!\004\023\n\005\025\"!aA!os\")qe\005D\001Q\005!\021\r\0359Q)\rI#\r\032\t\0035)2AaK\005\001Y\ta\001+[2lY\026\0248\013^1uKN\021!\006\004\005\t])\022)\031!C\001_\00511\017\036:fC6,\022\001\r\t\004\033E\032\024B\001\032\005\005\025\t%O]1z!\tiA'\003\0026\t\t!!)\037;f\021!9$F!A!\002\023\001\024aB:ue\026\fW\016\t\005\ts)\022)\031!C\001u\005!A-[2u+\005Y\004C\001\016=\r\021i\024\002\001 \003\025AK7m\0337fe\026sgo\005\002=A!\001)R\022H\033\005\t%B\001\"D\003\035iW\017^1cY\026T!\001\022\003\002\025\r|G\016\\3di&|g.\003\002G\003\n9\001*Y:i\033\006\004\bCA\007I\023\tIEAA\002J]RDQ!\005\037\005\002-#\022a\017\005\b\033r\002\r\021\"\003O\003\r\031g\016^\013\002\017\"9\001\013\020a\001\n\023\t\026aB2oi~#S-\035\013\003%V\003\"!D*\n\005Q#!\001B+oSRDqAV(\002\002\003\007q)A\002yIEBa\001\027\037!B\0239\025\001B2oi\002BQA\027\037\005\002m\013qA\\3yi2{7\rF\001H\021!i&F!A!\002\023Y\024!\0023jGR\004\003\"B\t+\t\003yFcA\025aC\")aF\030a\001a!)\021H\030a\001w!)1M\na\0017\005\t\021\rC\003fM\001\007\021&A\003ti\006$X\rC\003h'\031\005\001.\001\003baB,FcA5\002\016A!QB[\016m\023\tYGA\001\004UkBdWM\r\t\003554AA\\\005\001_\nqQK\034)jG.dWM]*uCR,7CA7\r\021!qSN!b\001\n\003y\003\002C\034n\005\003\005\013\021\002\031\t\021ej'Q1A\005\002M,\022\001\036\t\0035U4AA^\005\001o\naQK\034)jG.dWM]#omN\021Q\017\037\t\005\001\026;5\005C\003\022k\022\005!\020F\001u\021\035iU\0171A\005\n9Cq\001U;A\002\023%Q\020\006\002S}\"9a\013`A\001\002\0049\005B\002-vA\003&q\tC\003[k\022\0051\f\003\005^[\n\005\t\025!\003u\021\031\tR\016\"\001\002\bQ)A.!\003\002\f!1a&!\002A\002ABa!OA\003\001\004!\b\"B3g\001\004a\007bBA\t\023\021\005\0211C\001\007a&\0347\016\\3\026\t\005U\021q\004\013\006a\005]\021\021\005\005\t\0033\ty\0011\001\002\034\005\t\001\017\005\003\033'\005u\001c\001\017\002 \0211a$a\004C\002}AqaYA\b\001\004\ti\002C\004\002&%!\t!a\n\002\021Ut\007/[2lY\026,B!!\013\002.Q1\0211FA\030\003g\0012\001HA\027\t\031q\0221\005b\001?!A\021\021DA\022\001\004\t\t\004\005\003\033'\005-\002B\002\030\002$\001\007\001GB\004\0028%\t\t!!\017\003\005A+V\003BA\036\003\013\0322!!\016\r\021\035\t\022Q\007C\001\003!\"!!\021\021\013i\t)$a\021\021\007q\t)\005\002\004\037\003k\021\ra\b\005\bO\005Ub\021AA%)\025\001\0241JA'\021\035\031\027q\ta\001\003\007Ba!ZA$\001\004\001\004bB4\0026\031\005\021\021\013\013\005\003'\n)\006E\003\016U\006\r\003\007\003\004f\003\037\002\r\001\r\005\b\0033JA\021AA.\003\035)\b/[2lY\026,B!!\030\002fQ)\001'a\030\002h!A\021\021DA,\001\004\t\t\007E\003\033\003k\t\031\007E\002\035\003K\"aAHA,\005\004y\002bB2\002X\001\007\0211\r\005\b\003WJA\021AA7\003%)XO\0349jG.dW-\006\003\002p\005MDCBA9\003k\nI\bE\002\035\003g\"aAHA5\005\004y\002\002CA\r\003S\002\r!a\036\021\013i\t)$!\035\t\r9\nI\0071\0011\r\035\ti(CA\001\003\022aAU3g\t\02647cAA>\031!9\021#a\037\005\002\005\rECAAC!\rQ\0221\020\004\007\003\023K\001)a#\003\007I+gm\005\005\002\b\006\025\025QRAJ!\ri\021qR\005\004\003##!a\002)s_\022,8\r\036\t\004\033\005U\025bAAL\t\ta1+\032:jC2L'0\0312mK\"9\021#a\"\005\002\005mECAAO!\rQ\022q\021\005\013\003C\0139)!A\005\002\005m\025\001B2pafD!\"!*\002\b\006\005I\021IAT\0035\001(o\0343vGR\004&/\0324jqV\021\021\021\026\t\005\003W\013),\004\002\002.*!\021qVAY\003\021a\027M\\4\013\005\005M\026\001\0026bm\006LA!a.\002.\n11\013\036:j]\036D\021\"a/\002\b\006\005I\021\001(\002\031A\024x\016Z;di\006\023\030\016^=\t\025\005}\026qQA\001\n\003\t\t-\001\bqe>$Wo\031;FY\026lWM\034;\025\007\r\n\031\r\003\005W\003{\013\t\0211\001H\021)\t9-a\"\002\002\023\005\023\021Z\001\020aJ|G-^2u\023R,'/\031;peV\021\0211\032\t\006\003\033\fymI\007\002\007&\031\021\021[\"\003\021%#XM]1u_JD!\"!6\002\b\006\005I\021AAl\003!\031\027M\\#rk\006dG\003BAm\003?\0042!DAn\023\r\ti\016\002\002\b\005>|G.Z1o\021!1\0261[A\001\002\004\031\003\"CAr\003\017\013\t\021\"\021\\\003!A\027m\0355D_\022,\007BCAt\003\017\013\t\021\"\021\002j\006AAo\\*ue&tw\r\006\002\002*\"Q\021Q^AD\003\003%\t%a<\002\r\025\fX/\0317t)\021\tI.!=\t\021Y\013Y/!AA\002\r:\021\"!>\n\003\003E\t!a>\002\007I+g\rE\002\033\003s4\021\"!#\n\003\003E\t!a?\024\r\005e\030Q`AJ!\031\tyP!\002\002\0366\021!\021\001\006\004\005\007!\021a\002:v]RLW.Z\005\005\005\017\021\tAA\tBEN$(/Y2u\rVt7\r^5p]BBq!EA}\t\003\021Y\001\006\002\002x\"Q\021q]A}\003\003%)%!;\t\025\tE\021\021`A\001\n\003\013Y*A\003baBd\027\020\003\006\003\026\005e\030\021!CA\005/\tq!\0368baBd\027\020\006\003\002Z\ne\001B\003B\016\005'\t\t\0211\001\002\036\006\031\001\020\n\031\t\025\t}\021\021`A\001\n\023\021\t#A\006sK\006$'+Z:pYZ,GC\001B\022!\021\tYK!\n\n\t\t\035\022Q\026\002\007\037\nTWm\031;\007\r\t-\022\002\021B\027\005\r!UMZ\n\t\005S\t))!$\002\024\"9\021C!\013\005\002\tEBC\001B\032!\rQ\"\021\006\005\013\003C\023I#!A\005\002\tE\002BCAS\005S\t\t\021\"\021\002(\"I\0211\030B\025\003\003%\tA\024\005\013\003\023I#!A\005\002\tuBcA\022\003@!AaKa\017\002\002\003\007q\t\003\006\002H\n%\022\021!C!\003\023D!\"!6\003*\005\005I\021\001B#)\021\tINa\022\t\021Y\023\031%!AA\002\rB\021\"a9\003*\005\005I\021I.\t\025\005\035(\021FA\001\n\003\nI\017\003\006\002n\n%\022\021!C!\005\037\"B!!7\003R!AaK!\024\002\002\003\0071eB\005\003V%\t\t\021#\001\003X\005\031A)\0324\021\007i\021IFB\005\003,%\t\t\021#\001\003\\M1!\021\fB/\003'\003b!a@\003\006\tM\002bB\t\003Z\021\005!\021\r\013\003\005/B!\"a:\003Z\005\005IQIAu\021)\021\tB!\027\002\002\023\005%\021\007\005\013\005+\021I&!A\005\002\n%D\003BAm\005WB!Ba\007\003h\005\005\t\031\001B\032\021)\021yB!\027\002\002\023%!\021\005\005\b\005cJA\021\001B:\003\031\021XM\032#fMV\021!Q\017\t\0065\005U\022Q\021\005\t\005sJ!\031!C\001\035\006\031!+\022$\t\017\tu\024\002)A\005\017\006!!+\022$!\021!\021\t)\003b\001\n\003q\025a\001#F\r\"9!QQ\005!\002\0239\025\001\002#F\r\002BqA!#\n\t\003\021Y)\001\003v]\006$XC\001BG!\021Q\022QG$\t\017\tE\025\002\"\001\003\024\006)1\017[1sKV!!Q\023BN)\021\0219Ja(\021\ti\031\"\021\024\t\0049\tmEa\002BO\005\037\023\ra\b\002\002C\"A!\021\025BH\001\004\0219*\001\002qC\"9!QU\005\005\002\t\035\026!B;mS\032$X\003\002BU\005_#BAa+\0034B)!$!\016\003.B\031ADa,\005\017\tE&1\025b\001?\t\tA\017\003\005\0036\n\r\006\031\001BW\003\005A\bb\002B]\023\021\005!1X\001\005Y&4G/\006\003\003>\n\rG\003\002B`\005\013\004BAG\n\003BB\031ADa1\005\017\tE&q\027b\001?!A!Q\027B\\\001\004\021\t\rC\004\003J&!\tAa3\002\013U\034X-];\026\r\t5'1\035Bj)!\021yMa6\003f\n%\b#\002\016\0026\tE\007c\001\017\003T\0229!Q\033Bd\005\004y\"!A;\t\021\te'q\031a\001\0057\f\021A\032\t\b\033\tu'\021\033Bq\023\r\021y\016\002\002\n\rVt7\r^5p]F\0022\001\bBr\t\035\021\tLa2C\002}A\001B!)\003H\002\007!q\035\t\0065\005U\"\021\035\005\t\005W\0249\r1\001\003n\006\t1\016E\004\016\005;\024\tOa4\t\017\tE\030\002\"\001\003t\006!1/Z9v+\031\021)pa\001\003|RA!q\037B\007\013\031I\001\005\003\033'\te\bc\001\017\003|\0229!Q\033Bx\005\004y\002\002\003Bm\005_\004\rAa@\021\0175\021iN!?\004\002A\031Ada\001\005\017\tE&q\036b\001?!A!\021\025Bx\001\004\0319\001\005\003\033'\r\005\001\002\003Bv\005_\004\raa\003\021\0175\021in!\001\003x\"91qB\005\005\002\rE\021!B;qC&\024XCBB\n\0077\031y\002\006\004\004\026\r\r2q\005\t\0065\005U2q\003\t\007\033)\034Ib!\b\021\007q\031Y\002B\004\003\036\0165!\031A\020\021\007q\031y\002B\004\004\"\r5!\031A\020\003\003\tD\001B!)\004\016\001\0071Q\005\t\0065\005U2\021\004\005\t\007S\031i\0011\001\004,\005\021\001O\031\t\0065\005U2Q\004\005\b\007_IA\021AB\031\003\021\001\030-\033:\026\r\rM21HB )\031\031)d!\021\004FA!!dEB\034!\031i!n!\017\004>A\031Ada\017\005\017\tu5Q\006b\001?A\031Ada\020\005\017\r\0052Q\006b\001?!A!\021UB\027\001\004\031\031\005\005\003\033'\re\002\002CB\025\007[\001\raa\022\021\ti\0312Q\b\005\b\007\027JA\021AB'\003\031!(/\0339mKVA1qJB.\007?\032\031\007\006\005\004R\r\03541NB8!\021Q2ca\025\021\0235\031)f!\027\004^\r\005\024bAB,\t\t1A+\0369mKN\0022\001HB.\t\035\021ij!\023C\002}\0012\001HB0\t\035\031\tc!\023C\002}\0012\001HB2\t\035\031)g!\023C\002}\021\021a\031\005\t\005C\033I\0051\001\004jA!!dEB-\021!\031Ic!\023A\002\r5\004\003\002\016\024\007;B\001b!\035\004J\001\00711O\001\003a\016\004BAG\n\004b!91qO\005\005\002\re\024!B;xe\006\004XCBB>\007\027\033\t\t\006\005\004~\r\r5QRBJ!\025Q\022QGB@!\ra2\021\021\003\b\007C\031)H1\001 \021!\031)i!\036A\002\r\035\025!A5\021\0175\021in!#\004\000A\031Ada#\005\017\tu5Q\017b\001?!A1qRB;\001\004\031\t*A\001k!\035i!Q\\B@\007\023C\001B!)\004v\001\0071Q\023\t\0065\005U2\021\022\005\b\0073KA\021ABN\003\0219(/\0319\026\r\ru51VBR)!\031yj!*\004.\016E\006\003\002\016\024\007C\0032\001HBR\t\035\031\tca&C\002}A\001b!\"\004\030\002\0071q\025\t\b\033\tu7\021VBQ!\ra21\026\003\b\005;\0339J1\001 \021!\031yia&A\002\r=\006cB\007\003^\016\0056\021\026\005\t\005C\0339\n1\001\0044B!!dEBU\021\035\0319,\003C\001\007s\013!\"\0319qK:$')\037;f)\025\00141XB_\021\031\0317Q\027a\001a!91qXB[\001\0049\025!\0012\t\017\r\r\027\002\"\001\004F\006Ia.\031;3\005f$Xm\035\013\004a\r\035\007b\002B[\007\003\004\ra\022\005\b\007\027LA\021ABg\003\rq\027\r^\013\003\007\037\0042AG\nH\021\035\031\031.\003C\001\007+\fAAY=uKV\0211q\033\t\0045M\031\004bBBn\023\021\0051Q\\\001\007gR\024\030N\\4\026\005\r}\007\003\002\016\024\007C\004Baa9\004j:\031Qb!:\n\007\r\035H!\001\004Qe\026$WMZ\005\005\003o\033YOC\002\004h\022Aqaa<\n\t\003\031\t0A\005csR,\027M\035:bsV\02111\037\t\0045M\001\004bBB|\023\021\0051\021`\001\005E>|G.\006\002\004|B!!dEAm\021\035\031y0\003C\001\t\003\t!\"\0364jq\026$G*[:u+\021!\031\001b\t\025\t\021\025A1\006\013\005\t\017!9\003E\003\033\003k!I\001\005\004\005\f\021mA\021\005\b\005\t\033!9B\004\003\005\020\021UQB\001C\t\025\r!\031BB\001\007yI|w\016\036 \n\003\025I1\001\"\007\005\003\035\001\030mY6bO\026LA\001\"\b\005 \t!A*[:u\025\r!I\002\002\t\0049\021\rBa\002C\023\007{\024\ra\b\002\002\003\"9A\021FB\001\0049\025!\0018\t\021\t\0056Q a\001\t[\001RAGA\033\tCAq\001\"\r\n\t\003!\031$A\005gSb,G\rT5tiV!AQ\007C )\021!9\004b\021\025\t\021eB\021\t\t\0055M!Y\004\005\004\005\f\021mAQ\b\t\0049\021}Ba\002BO\t_\021\ra\b\005\b\tS!y\0031\001H\021!\021\t\013b\fA\002\021\025\003\003\002\016\024\t{Aq\001\"\023\n\t\003!Y%\001\003mSN$X\003\002C'\t+\"B\001b\024\005XA!!d\005C)!\031!Y\001b\007\005TA\031A\004\"\026\005\017\tuEq\tb\001?!A!\021\025C$\001\004!I\006\005\003\033'\021M\003b\002C/\023\021\005AqL\001\006k2L7\017^\013\005\tC\"I\007\006\003\005d\021-\004#\002\016\0026\021\025\004C\002C\006\t7!9\007E\002\035\tS\"qA!(\005\\\t\007q\004\003\005\003\"\022m\003\031\001C7!\025Q\022Q\007C4\021\035!\t(\003C\001\tg\nA\001Z1uCV!AQ\017C>)\031!9\b\" \005\004B!!d\005C=!\raB1\020\003\b\005;#yG1\001 \021!!y\bb\034A\002\021\005\025a\001;bOB1QB!8\005z\035C\001\002\"\"\005p\001\007AqQ\001\003aN\004b\001b\003\005\034\021%\005#B\007\005\f\022]\024b\001CG\t\tIa)\0368di&|g\016\r\025\b\023\021EEq\023CN!\riA1S\005\004\t+#!A\0033faJ,7-\031;fI\006\022A\021T\001\034)\"L7\017I2mCN\034\be^5mY\002\022W\r\t:f[>4X\r\032\030\"\005\021u\025A\002\032/cAr\003\007K\004\001\t##9\nb'")
/*     */ public final class BytePickle {
/*     */   public static <a> SPU<a> data(Function1<a, Object> paramFunction1, List<Function0<SPU<a>>> paramList) {
/*     */     return BytePickle$.MODULE$.data(paramFunction1, paramList);
/*     */   }
/*     */   
/*     */   public static <a> PU<List<a>> ulist(PU<a> paramPU) {
/*     */     return BytePickle$.MODULE$.ulist(paramPU);
/*     */   }
/*     */   
/*     */   public static <a> SPU<List<a>> list(SPU<a> paramSPU) {
/*     */     return BytePickle$.MODULE$.list(paramSPU);
/*     */   }
/*     */   
/*     */   public static <a> SPU<List<a>> fixedList(SPU<a> paramSPU, int paramInt) {
/*     */     return BytePickle$.MODULE$.fixedList(paramSPU, paramInt);
/*     */   }
/*     */   
/*     */   public static <A> PU<List<A>> ufixedList(PU<A> paramPU, int paramInt) {
/*     */     return BytePickle$.MODULE$.ufixedList(paramPU, paramInt);
/*     */   }
/*     */   
/*     */   public static SPU<Object> bool() {
/*     */     return BytePickle$.MODULE$.bool();
/*     */   }
/*     */   
/*     */   public static SPU<byte[]> bytearray() {
/*     */     return BytePickle$.MODULE$.bytearray();
/*     */   }
/*     */   
/*     */   public static SPU<String> string() {
/*     */     return BytePickle$.MODULE$.string();
/*     */   }
/*     */   
/*     */   public static SPU<Object> byte() {
/*     */     return BytePickle$.MODULE$.byte();
/*     */   }
/*     */   
/*     */   public static SPU<Object> nat() {
/*     */     return BytePickle$.MODULE$.nat();
/*     */   }
/*     */   
/*     */   public static byte[] nat2Bytes(int paramInt) {
/*     */     return BytePickle$.MODULE$.nat2Bytes(paramInt);
/*     */   }
/*     */   
/*     */   public static byte[] appendByte(byte[] paramArrayOfbyte, int paramInt) {
/*     */     return BytePickle$.MODULE$.appendByte(paramArrayOfbyte, paramInt);
/*     */   }
/*     */   
/*     */   public static <a, b> SPU<b> wrap(Function1<a, b> paramFunction1, Function1<b, a> paramFunction11, SPU<a> paramSPU) {
/*     */     return BytePickle$.MODULE$.wrap(paramFunction1, paramFunction11, paramSPU);
/*     */   }
/*     */   
/*     */   public static <a, b> PU<b> uwrap(Function1<a, b> paramFunction1, Function1<b, a> paramFunction11, PU<a> paramPU) {
/*     */     return BytePickle$.MODULE$.uwrap(paramFunction1, paramFunction11, paramPU);
/*     */   }
/*     */   
/*     */   public static <a, b, c> SPU<Tuple3<a, b, c>> triple(SPU<a> paramSPU, SPU<b> paramSPU1, SPU<c> paramSPU2) {
/*     */     return BytePickle$.MODULE$.triple(paramSPU, paramSPU1, paramSPU2);
/*     */   }
/*     */   
/*     */   public static <a, b> SPU<Tuple2<a, b>> pair(SPU<a> paramSPU, SPU<b> paramSPU1) {
/*     */     return BytePickle$.MODULE$.pair(paramSPU, paramSPU1);
/*     */   }
/*     */   
/*     */   public static <a, b> PU<Tuple2<a, b>> upair(PU<a> paramPU, PU<b> paramPU1) {
/*     */     return BytePickle$.MODULE$.upair(paramPU, paramPU1);
/*     */   }
/*     */   
/*     */   public static <t, u> SPU<u> sequ(Function1<u, t> paramFunction1, SPU<t> paramSPU, Function1<t, SPU<u>> paramFunction11) {
/*     */     return BytePickle$.MODULE$.sequ(paramFunction1, paramSPU, paramFunction11);
/*     */   }
/*     */   
/*     */   public static <t, u> PU<u> usequ(Function1<u, t> paramFunction1, PU<t> paramPU, Function1<t, PU<u>> paramFunction11) {
/*     */     return BytePickle$.MODULE$.usequ(paramFunction1, paramPU, paramFunction11);
/*     */   }
/*     */   
/*     */   public static <t> SPU<t> lift(t paramt) {
/*     */     return BytePickle$.MODULE$.lift(paramt);
/*     */   }
/*     */   
/*     */   public static <t> PU<t> ulift(t paramt) {
/*     */     return BytePickle$.MODULE$.ulift(paramt);
/*     */   }
/*     */   
/*     */   public static <a> SPU<a> share(SPU<a> paramSPU) {
/*     */     return BytePickle$.MODULE$.share(paramSPU);
/*     */   }
/*     */   
/*     */   public static PU<Object> unat() {
/*     */     return BytePickle$.MODULE$.unat();
/*     */   }
/*     */   
/*     */   public static int DEF() {
/*     */     return BytePickle$.MODULE$.DEF();
/*     */   }
/*     */   
/*     */   public static int REF() {
/*     */     return BytePickle$.MODULE$.REF();
/*     */   }
/*     */   
/*     */   public static PU<RefDef> refDef() {
/*     */     return BytePickle$.MODULE$.refDef();
/*     */   }
/*     */   
/*     */   public static <T> T uunpickle(PU<T> paramPU, byte[] paramArrayOfbyte) {
/*     */     return BytePickle$.MODULE$.uunpickle(paramPU, paramArrayOfbyte);
/*     */   }
/*     */   
/*     */   public static <T> byte[] upickle(PU<T> paramPU, T paramT) {
/*     */     return BytePickle$.MODULE$.upickle(paramPU, paramT);
/*     */   }
/*     */   
/*     */   public static <T> T unpickle(SPU<T> paramSPU, byte[] paramArrayOfbyte) {
/*     */     return BytePickle$.MODULE$.unpickle(paramSPU, paramArrayOfbyte);
/*     */   }
/*     */   
/*     */   public static <T> byte[] pickle(SPU<T> paramSPU, T paramT) {
/*     */     return BytePickle$.MODULE$.pickle(paramSPU, paramT);
/*     */   }
/*     */   
/*     */   public static abstract class SPU<T> {
/*     */     public abstract BytePickle.PicklerState appP(T param1T, BytePickle.PicklerState param1PicklerState);
/*     */     
/*     */     public abstract Tuple2<T, BytePickle.UnPicklerState> appU(BytePickle.UnPicklerState param1UnPicklerState);
/*     */   }
/*     */   
/*     */   public static abstract class PU<T> {
/*     */     public abstract byte[] appP(T param1T, byte[] param1ArrayOfbyte);
/*     */     
/*     */     public abstract Tuple2<T, byte[]> appU(byte[] param1ArrayOfbyte);
/*     */   }
/*     */   
/*     */   public static class PicklerEnv extends HashMap<Object, Object> {
/*  47 */     private int cnt = 64;
/*     */     
/*     */     private int cnt() {
/*  47 */       return this.cnt;
/*     */     }
/*     */     
/*     */     private void cnt_$eq(int x$1) {
/*  47 */       this.cnt = x$1;
/*     */     }
/*     */     
/*     */     public int nextLoc() {
/*  48 */       cnt_$eq(cnt() + 1);
/*  48 */       return cnt();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class UnPicklerEnv extends HashMap<Object, Object> {
/*  52 */     private int cnt = 64;
/*     */     
/*     */     private int cnt() {
/*  52 */       return this.cnt;
/*     */     }
/*     */     
/*     */     private void cnt_$eq(int x$1) {
/*  52 */       this.cnt = x$1;
/*     */     }
/*     */     
/*     */     public int nextLoc() {
/*  53 */       cnt_$eq(cnt() + 1);
/*  53 */       return cnt();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class PicklerState {
/*     */     private final byte[] stream;
/*     */     
/*     */     private final BytePickle.PicklerEnv dict;
/*     */     
/*     */     public byte[] stream() {
/*  56 */       return this.stream;
/*     */     }
/*     */     
/*     */     public BytePickle.PicklerEnv dict() {
/*  56 */       return this.dict;
/*     */     }
/*     */     
/*     */     public PicklerState(byte[] stream, BytePickle.PicklerEnv dict) {}
/*     */   }
/*     */   
/*     */   public static class UnPicklerState {
/*     */     private final byte[] stream;
/*     */     
/*     */     private final BytePickle.UnPicklerEnv dict;
/*     */     
/*     */     public byte[] stream() {
/*  57 */       return this.stream;
/*     */     }
/*     */     
/*     */     public BytePickle.UnPicklerEnv dict() {
/*  57 */       return this.dict;
/*     */     }
/*     */     
/*     */     public UnPicklerState(byte[] stream, BytePickle.UnPicklerEnv dict) {}
/*     */   }
/*     */   
/*     */   public static class Ref extends RefDef implements Product, Serializable {
/*     */     public Ref copy() {
/*  60 */       return new Ref();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  60 */       return "Ref";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  60 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  60 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  60 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  60 */       return x$1 instanceof Ref;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  60 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  60 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       boolean bool;
/*  60 */       if (x$1 instanceof Ref) {
/* 236 */         bool = true;
/*     */       } else {
/* 236 */         bool = false;
/*     */       } 
/*     */       return (bool && ((Ref)x$1).canEqual(this));
/*     */     }
/*     */     
/*     */     public Ref() {
/*     */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Ref$ extends AbstractFunction0<Ref> implements Serializable {
/*     */     public static final Ref$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*     */       return "Ref";
/*     */     }
/*     */     
/*     */     public BytePickle.Ref apply() {
/*     */       return new BytePickle.Ref();
/*     */     }
/*     */     
/*     */     public boolean unapply(BytePickle.Ref x$0) {
/*     */       return !(x$0 == null);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Ref$() {
/*     */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class RefDef {}
/*     */   
/*     */   public static class Def extends RefDef implements Product, Serializable {
/*     */     public Def copy() {
/*     */       return new Def();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*     */       return "Def";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*     */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*     */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*     */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*     */       return x$1 instanceof Def;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*     */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       boolean bool;
/*     */       if (x$1 instanceof Def) {
/* 236 */         bool = true;
/*     */       } else {
/* 236 */         bool = false;
/*     */       } 
/*     */       return (bool && ((Def)x$1).canEqual(this));
/*     */     }
/*     */     
/*     */     public Def() {
/*     */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Def$ extends AbstractFunction0<Def> implements Serializable {
/*     */     public static final Def$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*     */       return "Def";
/*     */     }
/*     */     
/*     */     public BytePickle.Def apply() {
/*     */       return new BytePickle.Def();
/*     */     }
/*     */     
/*     */     public boolean unapply(BytePickle.Def x$0) {
/*     */       return !(x$0 == null);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Def$() {
/*     */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$1 extends PU<RefDef> {
/*     */     public byte[] appP(BytePickle.RefDef b, byte[] s) {
/*     */       if (b instanceof BytePickle.Ref) {
/*     */         (new byte[2][])[0] = s;
/*     */         (new byte[2][])[1] = (byte[])scala.Array$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapByteArray(new byte[] { 0 }, ), scala.reflect.ClassTag$.MODULE$.Byte());
/*     */         byte[] arrayOfByte = (byte[])scala.Array$.MODULE$.concat((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new byte[2][]), scala.reflect.ClassTag$.MODULE$.Byte());
/*     */       } else {
/*     */         if (b instanceof BytePickle.Def) {
/*     */           (new byte[2][])[0] = s;
/*     */           (new byte[2][])[1] = (byte[])scala.Array$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapByteArray(new byte[] { 1 }, ), scala.reflect.ClassTag$.MODULE$.Byte());
/*     */           return (byte[])scala.Array$.MODULE$.concat((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new byte[2][]), scala.reflect.ClassTag$.MODULE$.Byte());
/*     */         } 
/*     */         throw new MatchError(b);
/*     */       } 
/*     */       return (byte[])SYNTHETIC_LOCAL_VARIABLE_3;
/*     */     }
/*     */     
/*     */     public Tuple2<BytePickle.RefDef, byte[]> appU(byte[] s) {
/*     */       return (s[0] == 0) ? new Tuple2(new BytePickle.Ref(), scala.Predef$.MODULE$.byteArrayOps(s).slice(1, s.length)) : new Tuple2(new BytePickle.Def(), scala.Predef$.MODULE$.byteArrayOps(s).slice(1, s.length));
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$2 extends PU<Object> {
/*     */     public byte[] appP(int n, byte[] s) {
/*     */       (new byte[2][])[0] = s;
/*     */       (new byte[2][])[1] = BytePickle$.MODULE$.nat2Bytes(n);
/*     */       return (byte[])scala.Array$.MODULE$.concat((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new byte[2][]), scala.reflect.ClassTag$.MODULE$.Byte());
/*     */     }
/*     */     
/*     */     public Tuple2<Object, byte[]> appU(byte[] s) {
/*     */       IntRef num = new IntRef(0);
/*     */       return new Tuple2(BoxesRunTime.boxToInteger(readNat$1(s, num)), scala.Predef$.MODULE$.byteArrayOps(s).slice(num.elem, s.length));
/*     */     }
/*     */     
/*     */     private final int readNat$1(byte[] s$1, IntRef num$1) {
/*     */       int x = 0;
/*     */       while (true) {
/*     */         b = s$1[num$1.elem];
/*     */         num$1.elem++;
/*     */         x = (x << 7) + (b & 0x7F);
/*     */         if ((b & 0x80) == 0)
/*     */           return x; 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$3 extends SPU<a> {
/*     */     private final BytePickle.SPU pa$1;
/*     */     
/*     */     public BytePickle$$anon$3(BytePickle.SPU pa$1) {}
/*     */     
/*     */     public BytePickle.PicklerState appP(Object v, BytePickle.PicklerState state) {
/*     */       BytePickle.PicklerEnv pe = state.dict();
/*     */       Option option = pe.get(v);
/*     */       if (scala.None$.MODULE$ == null) {
/*     */         if (option != null)
/*     */           if (option instanceof Some) {
/*     */             Some some = (Some)option;
/*     */             byte[] sPrime = BytePickle$.MODULE$.refDef().appP(new BytePickle.Ref(), state.stream());
/*     */             return new BytePickle.PicklerState(BytePickle$.MODULE$.unat().appP(some.x(), sPrime), pe);
/*     */           }  
/*     */       } else {
/*     */         if (scala.None$.MODULE$.equals(option)) {
/*     */           byte[] sPrime = BytePickle$.MODULE$.refDef().appP(new BytePickle.Def(), state.stream());
/*     */           int l = pe.nextLoc();
/*     */           BytePickle.PicklerState sPrimePrime = this.pa$1.appP(v, new BytePickle.PicklerState(sPrime, pe));
/*     */           pe.update(v, BoxesRunTime.boxToInteger(l));
/*     */           return sPrimePrime;
/*     */         } 
/*     */         if (option instanceof Some) {
/*     */           Some some = (Some)option;
/*     */           byte[] arrayOfByte1 = BytePickle$.MODULE$.refDef().appP(new BytePickle.Ref(), state.stream());
/*     */           return new BytePickle.PicklerState(BytePickle$.MODULE$.unat().appP(some.x(), arrayOfByte1), pe);
/*     */         } 
/*     */       } 
/*     */       byte[] arrayOfByte = BytePickle$.MODULE$.refDef().appP(new BytePickle.Def(), state.stream());
/*     */       int i = pe.nextLoc();
/*     */       BytePickle.PicklerState picklerState = this.pa$1.appP(v, new BytePickle.PicklerState(arrayOfByte, pe));
/*     */       pe.update(v, BoxesRunTime.boxToInteger(i));
/*     */       return picklerState;
/*     */     }
/*     */     
/*     */     public Tuple2<a, BytePickle.UnPicklerState> appU(BytePickle.UnPicklerState state) {
/*     */       BytePickle.UnPicklerEnv upe = state.dict();
/*     */       Tuple2<BytePickle.RefDef, byte[]> res = BytePickle$.MODULE$.refDef().appU(state.stream());
/*     */       BytePickle.RefDef refDef = (BytePickle.RefDef)res._1();
/*     */       if (refDef instanceof BytePickle.Def) {
/*     */         int l = upe.nextLoc();
/*     */         Tuple2<a, BytePickle.UnPicklerState> res2 = this.pa$1.appU(new BytePickle.UnPicklerState((byte[])res._2(), upe));
/*     */         upe.update(BoxesRunTime.boxToInteger(l), res2._1());
/*     */         return res2;
/*     */       } 
/*     */       if (refDef instanceof BytePickle.Ref) {
/*     */         Tuple2<Object, byte[]> res2 = BytePickle$.MODULE$.unat().appU((byte[])res._2());
/*     */         Option option = upe.get(BoxesRunTime.boxToInteger(res2._1$mcI$sp()));
/*     */         if (scala.None$.MODULE$ == null) {
/*     */           if (option != null)
/*     */             if (option instanceof Some) {
/*     */               Some some = (Some)option;
/*     */               return new Tuple2(some.x(), new BytePickle.UnPicklerState((byte[])res2._2(), upe));
/*     */             }  
/*     */         } else {
/*     */           if (scala.None$.MODULE$.equals(option))
/*     */             throw new IllegalArgumentException("invalid unpickler environment"); 
/*     */           if (option instanceof Some) {
/*     */             Some some = (Some)option;
/*     */             return new Tuple2(some.x(), new BytePickle.UnPicklerState((byte[])res2._2(), upe));
/*     */           } 
/*     */         } 
/*     */         throw new IllegalArgumentException("invalid unpickler environment");
/*     */       } 
/*     */       throw new MatchError(refDef);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$4 extends PU<t> {
/*     */     private final Object x$4;
/*     */     
/*     */     public BytePickle$$anon$4(Object x$4) {}
/*     */     
/*     */     public byte[] appP(Object a, byte[] state) {
/*     */       Object object;
/*     */       if (((object = this.x$4) == a) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, a) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, a) : object.equals(a)))))
/*     */         return state; 
/*     */       throw new IllegalArgumentException((new StringBuilder()).append("value to be pickled (").append(a).append(") != ").append(this.x$4).toString());
/*     */     }
/*     */     
/*     */     public Tuple2<t, byte[]> appU(byte[] state) {
/*     */       return new Tuple2(this.x$4, state);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$5 extends SPU<t> {
/*     */     private final Object x$5;
/*     */     
/*     */     public BytePickle$$anon$5(Object x$5) {}
/*     */     
/*     */     public BytePickle.PicklerState appP(Object a, BytePickle.PicklerState state) {
/*     */       Object object;
/*     */       return (((object = this.x$5) == a) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, a) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, a) : object.equals(a))))) ? state : state;
/*     */     }
/*     */     
/*     */     public Tuple2<t, BytePickle.UnPicklerState> appU(BytePickle.UnPicklerState state) {
/*     */       return new Tuple2(this.x$5, state);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$6 extends PU<u> {
/*     */     private final Function1 f$1;
/*     */     
/*     */     private final BytePickle.PU pa$2;
/*     */     
/*     */     private final Function1 k$1;
/*     */     
/*     */     public BytePickle$$anon$6(Function1 f$1, BytePickle.PU pa$2, Function1 k$1) {}
/*     */     
/*     */     public byte[] appP(Object b, byte[] s) {
/*     */       Object a = this.f$1.apply(b);
/*     */       byte[] sPrime = this.pa$2.appP(a, s);
/*     */       BytePickle.PU<Object> pb = (BytePickle.PU)this.k$1.apply(a);
/*     */       byte[] sPrimePrime = pb.appP(b, sPrime);
/*     */       return sPrimePrime;
/*     */     }
/*     */     
/*     */     public Tuple2<u, byte[]> appU(byte[] s) {
/*     */       Tuple2 resPa = this.pa$2.appU(s);
/*     */       Object a = resPa._1();
/*     */       byte[] sPrime = (byte[])resPa._2();
/*     */       BytePickle.PU<u> pb = (BytePickle.PU)this.k$1.apply(a);
/*     */       return pb.appU(sPrime);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$7 extends SPU<u> {
/*     */     private final Function1 f$2;
/*     */     
/*     */     private final BytePickle.SPU pa$3;
/*     */     
/*     */     private final Function1 k$2;
/*     */     
/*     */     public BytePickle$$anon$7(Function1 f$2, BytePickle.SPU pa$3, Function1 k$2) {}
/*     */     
/*     */     public BytePickle.PicklerState appP(Object b, BytePickle.PicklerState s) {
/*     */       Object a = this.f$2.apply(b);
/*     */       BytePickle.PicklerState sPrime = this.pa$3.appP(a, s);
/*     */       BytePickle.SPU<Object> pb = (BytePickle.SPU)this.k$2.apply(a);
/*     */       return pb.appP(b, sPrime);
/*     */     }
/*     */     
/*     */     public Tuple2<u, BytePickle.UnPicklerState> appU(BytePickle.UnPicklerState s) {
/*     */       Tuple2 resPa = this.pa$3.appU(s);
/*     */       Object a = resPa._1();
/*     */       BytePickle.UnPicklerState sPrime = (BytePickle.UnPicklerState)resPa._2();
/*     */       BytePickle.SPU<u> pb = (BytePickle.SPU)this.k$2.apply(a);
/*     */       return pb.appU(sPrime);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$upair$1 extends AbstractFunction1<Tuple2<a, b>, a> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final a apply(Tuple2 p) {
/*     */       return (a)BytePickle$.MODULE$.scala$io$BytePickle$$fst$1(p);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$upair$2 extends AbstractFunction1<a, PU<Tuple2<a, b>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BytePickle.PU pb$1;
/*     */     
/*     */     public final BytePickle.PU<Tuple2<a, b>> apply(Object x) {
/*     */       return BytePickle$.MODULE$.usequ((Function1<Tuple2<a, b>, ?>)new BytePickle$$anonfun$upair$2$$anonfun$apply$1(this), this.pb$1, (Function1<?, BytePickle.PU<Tuple2<a, b>>>)new BytePickle$$anonfun$upair$2$$anonfun$apply$2(this, x));
/*     */     }
/*     */     
/*     */     public BytePickle$$anonfun$upair$2(BytePickle.PU pb$1) {}
/*     */     
/*     */     public class BytePickle$$anonfun$upair$2$$anonfun$apply$1 extends AbstractFunction1<Tuple2<a, b>, b> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final b apply(Tuple2 p) {
/*     */         return (b)BytePickle$.MODULE$.scala$io$BytePickle$$snd$1(p);
/*     */       }
/*     */       
/*     */       public BytePickle$$anonfun$upair$2$$anonfun$apply$1(BytePickle$$anonfun$upair$2 $outer) {}
/*     */     }
/*     */     
/*     */     public class BytePickle$$anonfun$upair$2$$anonfun$apply$2 extends AbstractFunction1<b, BytePickle.PU<Tuple2<a, b>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object x$6;
/*     */       
/*     */       public final BytePickle.PU<Tuple2<a, b>> apply(Object y) {
/*     */         return BytePickle$.MODULE$.ulift(new Tuple2(this.x$6, y));
/*     */       }
/*     */       
/*     */       public BytePickle$$anonfun$upair$2$$anonfun$apply$2(BytePickle$$anonfun$upair$2 $outer, Object x$6) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$pair$1 extends AbstractFunction1<Tuple2<a, b>, a> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final a apply(Tuple2 p) {
/*     */       return (a)BytePickle$.MODULE$.scala$io$BytePickle$$fst$2(p);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$pair$2 extends AbstractFunction1<a, SPU<Tuple2<a, b>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BytePickle.SPU pb$2;
/*     */     
/*     */     public final BytePickle.SPU<Tuple2<a, b>> apply(Object x) {
/*     */       return BytePickle$.MODULE$.sequ((Function1<Tuple2<a, b>, ?>)new BytePickle$$anonfun$pair$2$$anonfun$apply$3(this), this.pb$2, (Function1<?, BytePickle.SPU<Tuple2<a, b>>>)new BytePickle$$anonfun$pair$2$$anonfun$apply$4(this, x));
/*     */     }
/*     */     
/*     */     public BytePickle$$anonfun$pair$2(BytePickle.SPU pb$2) {}
/*     */     
/*     */     public class BytePickle$$anonfun$pair$2$$anonfun$apply$3 extends AbstractFunction1<Tuple2<a, b>, b> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final b apply(Tuple2 p) {
/*     */         return (b)BytePickle$.MODULE$.scala$io$BytePickle$$snd$2(p);
/*     */       }
/*     */       
/*     */       public BytePickle$$anonfun$pair$2$$anonfun$apply$3(BytePickle$$anonfun$pair$2 $outer) {}
/*     */     }
/*     */     
/*     */     public class BytePickle$$anonfun$pair$2$$anonfun$apply$4 extends AbstractFunction1<b, BytePickle.SPU<Tuple2<a, b>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Object x$7;
/*     */       
/*     */       public final BytePickle.SPU<Tuple2<a, b>> apply(Object y) {
/*     */         return BytePickle$.MODULE$.lift(new Tuple2(this.x$7, y));
/*     */       }
/*     */       
/*     */       public BytePickle$$anonfun$pair$2$$anonfun$apply$4(BytePickle$$anonfun$pair$2 $outer, Object x$7) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$triple$1 extends AbstractFunction1<Tuple3<a, b, c>, a> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final a apply(Tuple3 p) {
/*     */       return (a)BytePickle$.MODULE$.scala$io$BytePickle$$fst$3(p);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$triple$2 extends AbstractFunction1<a, SPU<Tuple3<a, b, c>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BytePickle.SPU pb$3;
/*     */     
/*     */     public final BytePickle.SPU pc$1;
/*     */     
/*     */     public final BytePickle.SPU<Tuple3<a, b, c>> apply(Object x) {
/*     */       return BytePickle$.MODULE$.sequ((Function1<Tuple3<a, b, c>, ?>)new BytePickle$$anonfun$triple$2$$anonfun$apply$5(this), this.pb$3, (Function1<?, BytePickle.SPU<Tuple3<a, b, c>>>)new BytePickle$$anonfun$triple$2$$anonfun$apply$6(this, x));
/*     */     }
/*     */     
/*     */     public BytePickle$$anonfun$triple$2(BytePickle.SPU pb$3, BytePickle.SPU pc$1) {}
/*     */     
/*     */     public class BytePickle$$anonfun$triple$2$$anonfun$apply$5 extends AbstractFunction1<Tuple3<a, b, c>, b> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final b apply(Tuple3 p) {
/*     */         return (b)BytePickle$.MODULE$.scala$io$BytePickle$$snd$3(p);
/*     */       }
/*     */       
/*     */       public BytePickle$$anonfun$triple$2$$anonfun$apply$5(BytePickle$$anonfun$triple$2 $outer) {}
/*     */     }
/*     */     
/*     */     public class BytePickle$$anonfun$triple$2$$anonfun$apply$6 extends AbstractFunction1<b, BytePickle.SPU<Tuple3<a, b, c>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Object x$8;
/*     */       
/*     */       public final BytePickle.SPU<Tuple3<a, b, c>> apply(Object y) {
/*     */         return BytePickle$.MODULE$.sequ((Function1<Tuple3<a, b, c>, ?>)new BytePickle$$anonfun$triple$2$$anonfun$apply$6$$anonfun$apply$7(this), this.$outer.pc$1, (Function1<?, BytePickle.SPU<Tuple3<a, b, c>>>)new BytePickle$$anonfun$triple$2$$anonfun$apply$6$$anonfun$apply$8(this, y));
/*     */       }
/*     */       
/*     */       public BytePickle$$anonfun$triple$2$$anonfun$apply$6(BytePickle$$anonfun$triple$2 $outer, Object x$8) {}
/*     */       
/*     */       public class BytePickle$$anonfun$triple$2$$anonfun$apply$6$$anonfun$apply$7 extends AbstractFunction1<Tuple3<a, b, c>, c> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final c apply(Tuple3 p) {
/*     */           return (c)BytePickle$.MODULE$.scala$io$BytePickle$$trd$1(p);
/*     */         }
/*     */         
/*     */         public BytePickle$$anonfun$triple$2$$anonfun$apply$6$$anonfun$apply$7(BytePickle$$anonfun$triple$2$$anonfun$apply$6 $outer) {}
/*     */       }
/*     */       
/*     */       public class BytePickle$$anonfun$triple$2$$anonfun$apply$6$$anonfun$apply$8 extends AbstractFunction1<c, BytePickle.SPU<Tuple3<a, b, c>>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         private final Object y$1;
/*     */         
/*     */         public final BytePickle.SPU<Tuple3<a, b, c>> apply(Object z) {
/*     */           return BytePickle$.MODULE$.lift(new Tuple3(this.$outer.x$8, this.y$1, z));
/*     */         }
/*     */         
/*     */         public BytePickle$$anonfun$triple$2$$anonfun$apply$6$$anonfun$apply$8(BytePickle$$anonfun$triple$2$$anonfun$apply$6 $outer, Object y$1) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$uwrap$1 extends AbstractFunction1<a, PU<b>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 i$1;
/*     */     
/*     */     public final BytePickle.PU<b> apply(Object x) {
/*     */       return BytePickle$.MODULE$.ulift((b)this.i$1.apply(x));
/*     */     }
/*     */     
/*     */     public BytePickle$$anonfun$uwrap$1(Function1 i$1) {}
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$wrap$1 extends AbstractFunction1<a, SPU<b>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 i$2;
/*     */     
/*     */     public final BytePickle.SPU<b> apply(Object x) {
/*     */       return BytePickle$.MODULE$.lift((b)this.i$2.apply(x));
/*     */     }
/*     */     
/*     */     public BytePickle$$anonfun$wrap$1(Function1 i$2) {}
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$8 extends SPU<Object> {
/*     */     public BytePickle.PicklerState appP(int n, BytePickle.PicklerState s) {
/* 247 */       (new byte[2][])[0] = s.stream();
/* 247 */       (new byte[2][])[1] = BytePickle$.MODULE$.nat2Bytes(n);
/* 247 */       return new BytePickle.PicklerState((byte[])scala.Array$.MODULE$.concat((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new byte[2][]), scala.reflect.ClassTag$.MODULE$.Byte()), s.dict());
/*     */     }
/*     */     
/*     */     public Tuple2<Object, BytePickle.UnPicklerState> appU(BytePickle.UnPicklerState s) {
/* 250 */       IntRef num = new IntRef(0);
/* 261 */       return new Tuple2(BoxesRunTime.boxToInteger(readNat$2(s, num)), new BytePickle.UnPicklerState((byte[])scala.Predef$.MODULE$.byteArrayOps(s.stream()).slice(num.elem, (s.stream()).length), s.dict()));
/*     */     }
/*     */     
/*     */     private final int readNat$2(BytePickle.UnPicklerState s$2, IntRef num$2) {
/*     */       int x = 0;
/*     */       while (true) {
/*     */         b = s$2.stream()[num$2.elem];
/*     */         num$2.elem++;
/*     */         x = (x << 7) + (b & 0x7F);
/*     */         if ((b & 0x80) == 0)
/*     */           return x; 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anon$9 extends SPU<Object> {
/*     */     public BytePickle.PicklerState appP(byte b, BytePickle.PicklerState s) {
/* 267 */       (new byte[2][])[0] = s.stream();
/* 267 */       (new byte[1])[0] = b;
/* 267 */       (new byte[2][])[1] = new byte[1];
/* 267 */       return new BytePickle.PicklerState((byte[])scala.Array$.MODULE$.concat((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new byte[2][]), scala.reflect.ClassTag$.MODULE$.Byte()), s.dict());
/*     */     }
/*     */     
/*     */     public Tuple2<Object, BytePickle.UnPicklerState> appU(BytePickle.UnPicklerState s) {
/* 269 */       return new Tuple2(BoxesRunTime.boxToByte(s.stream()[0]), new BytePickle.UnPicklerState((byte[])scala.Predef$.MODULE$.byteArrayOps(s.stream()).slice(1, (s.stream()).length), s.dict()));
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$string$1 extends AbstractFunction1<byte[], String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(byte[] a) {
/* 273 */       return scala.Predef$.MODULE$.charArrayOps(Codec$.MODULE$.fromUTF8(a)).mkString();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$string$2 extends AbstractFunction1<String, byte[]> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final byte[] apply(String s) {
/* 274 */       return Codec$.MODULE$.toUTF8(s);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$bytearray$1 extends AbstractFunction1<List<Object>, byte[]> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final byte[] apply(List l) {
/* 279 */       return (byte[])l.toArray(scala.reflect.ClassTag$.MODULE$.Byte());
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$bytearray$2 extends AbstractFunction1<byte[], List<Object>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<Object> apply(byte[] x$1) {
/* 279 */       return scala.Predef$.MODULE$.byteArrayOps(x$1).toList();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$bool$1 extends AbstractFunction1.mcZI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final boolean apply(int n) {
/* 285 */       return BytePickle$.MODULE$.scala$io$BytePickle$$fromEnum$1(n);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int n) {
/* 285 */       return BytePickle$.MODULE$.scala$io$BytePickle$$fromEnum$1(n);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$bool$2 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(boolean b) {
/* 285 */       return BytePickle$.MODULE$.scala$io$BytePickle$$toEnum$1(b);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$ufixedList$1 extends AbstractFunction1<Tuple2<A, List<A>>, List<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<A> apply(Tuple2 p) {
/* 296 */       return BytePickle$.MODULE$.scala$io$BytePickle$$pairToList$1(p);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$ufixedList$2 extends AbstractFunction1<List<A>, Tuple2<A, List<A>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<A, List<A>> apply(List l) {
/* 296 */       return BytePickle$.MODULE$.scala$io$BytePickle$$listToPair$1(l);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$fixedList$1 extends AbstractFunction1<Tuple2<a, List<a>>, List<a>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final List<a> apply(Tuple2 p) {
/* 307 */       return BytePickle$.MODULE$.scala$io$BytePickle$$pairToList$2(p);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$fixedList$2 extends AbstractFunction1<List<a>, Tuple2<a, List<a>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<a, List<a>> apply(List l) {
/* 307 */       return BytePickle$.MODULE$.scala$io$BytePickle$$listToPair$2(l);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$list$1 extends AbstractFunction1<List<a>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(List l) {
/* 311 */       return l.length();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$list$2 extends AbstractFunction1<Object, SPU<List<a>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BytePickle.SPU pa$4;
/*     */     
/*     */     public final BytePickle.SPU<List<a>> apply(int n) {
/* 311 */       return BytePickle$.MODULE$.fixedList(this.pa$4, n);
/*     */     }
/*     */     
/*     */     public BytePickle$$anonfun$list$2(BytePickle.SPU pa$4) {}
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$ulist$1 extends AbstractFunction1<List<a>, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final int apply(List l) {
/* 314 */       return l.length();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$ulist$2 extends AbstractFunction1<Object, PU<List<a>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BytePickle.PU pa$5;
/*     */     
/*     */     public final BytePickle.PU<List<a>> apply(int n) {
/* 314 */       return BytePickle$.MODULE$.ufixedList(this.pa$5, n);
/*     */     }
/*     */     
/*     */     public BytePickle$$anonfun$ulist$2(BytePickle.PU pa$5) {}
/*     */   }
/*     */   
/*     */   public static class BytePickle$$anonfun$data$1 extends AbstractFunction1<Object, SPU<a>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final List ps$1;
/*     */     
/*     */     public final BytePickle.SPU<a> apply(int x) {
/* 317 */       return (BytePickle.SPU<a>)((Function0)this.ps$1.apply(x)).apply();
/*     */     }
/*     */     
/*     */     public BytePickle$$anonfun$data$1(List ps$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\io\BytePickle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */