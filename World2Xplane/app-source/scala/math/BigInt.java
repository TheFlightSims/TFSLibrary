/*     */ package scala.math;
/*     */ 
/*     */ import java.math.BigInteger;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.immutable.NumericRange;
/*     */ import scala.collection.immutable.Range$BigInt$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ import scala.util.Random;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\ruu!B\001\003\021\0039\021A\002\"jO&sGO\003\002\004\t\005!Q.\031;i\025\005)\021!B:dC2\f7\001\001\t\003\021%i\021A\001\004\006\025\tA\ta\003\002\007\005&<\027J\034;\024\007%a\001\003\005\002\016\0355\tA!\003\002\020\t\t1\021I\\=SK\032\004\"!D\t\n\005I!!\001D*fe&\fG.\033>bE2,\007\"\002\013\n\t\003)\022A\002\037j]&$h\bF\001\b\021\0359\022B1A\005\na\t\021\"\\5o\007\006\034\007.\0323\026\003e\001\"!\004\016\n\005m!!aA%oi\"1Q$\003Q\001\ne\t!\"\\5o\007\006\034\007.\0323!\021\035y\022B1A\005\na\t\021\"\\1y\007\006\034\007.\0323\t\r\005J\001\025!\003\032\003)i\027\r_\"bG\",G\r\t\005\bG%\021\r\021\"\003%\003\025\031\027m\0315f+\005)\003cA\007'Q%\021q\005\002\002\006\003J\024\030-\037\t\003\021%2AA\003\002\001UM!\021f\013\030\021!\tAA&\003\002.\005\tY1kY1mC:+XNY3s!\tAq&\003\0021\005\t92kY1mC:+X.\032:jG\016{gN^3sg&|gn\035\005\te%\022)\031!C\001g\005Q!-[4J]R,w-\032:\026\003Q\002\"!N\035\016\003YR!aA\034\013\003a\nAA[1wC&\021!H\016\002\013\005&<\027J\034;fO\026\024\b\002\003\037*\005\003\005\013\021\002\033\002\027\tLw-\0238uK\036,'\017\t\005\006)%\"\tA\020\013\003Q}BQAM\037A\002QBQ!Q\025\005B\t\013\001\002[1tQ\016{G-\032\013\0023!)A)\013C!\013\0061Q-];bYN$\"AR%\021\00559\025B\001%\005\005\035\021un\0347fC:DQAS\"A\002-\013A\001\0365biB\021Q\002T\005\003\033\022\0211!\0218z\021\025y\025\006\"\021Q\003-I7OV1mS\022\024\025\020^3\026\003\031CQAU\025\005BA\013A\"[:WC2LGm\0255peRDQ\001V\025\005BA\0131\"[:WC2LGm\0215be\")a+\013C!!\006Q\021n\035,bY&$\027J\034;\t\013aKC\021\001)\002\027%\034h+\0317jI2{gn\032\005\0065&\"\t\001U\001\rSN4\026\r\\5e\r2|\027\r\036\005\0069&\"\t\001U\001\016SN4\026\r\\5e\t>,(\r\\3\t\013yKC\021\002)\002#\tLG\017T3oORDwJ^3sM2|w\017C\003aS\021\005\021-A\004jg^Cw\016\\3\025\003\031CQaY\025\005\002\021\f!\"\0368eKJd\0270\0338h)\005!\004\"\002#*\t\0031GC\001$h\021\025QU\r1\001)\021\025I\027\006\"\001k\003\035\031w.\0349be\026$\"!G6\t\013)C\007\031\001\025\t\0135LC\021\0018\002\021\021bWm]:%KF$\"AR8\t\013)c\007\031\001\025\t\013ELC\021\001:\002\027\021:'/Z1uKJ$S-\035\013\003\rNDQA\0239A\002!BQ!^\025\005\002Y\fQ\001\n7fgN$\"AR<\t\013)#\b\031\001\025\t\013eLC\021\001>\002\021\021:'/Z1uKJ$\"AR>\t\013)C\b\031\001\025\t\013uLC\021\001@\002\013\021\002H.^:\025\005!z\b\"\002&}\001\004A\003bBA\002S\021\005\021QA\001\007I5Lg.^:\025\007!\n9\001\003\004K\003\003\001\r\001\013\005\b\003\027IC\021AA\007\003\031!C/[7fgR\031\001&a\004\t\r)\013I\0011\001)\021\035\t\031\"\013C\001\003+\tA\001\n3jmR\031\001&a\006\t\r)\013\t\0021\001)\021\035\tY\"\013C\001\003;\t\001\002\n9fe\016,g\016\036\013\004Q\005}\001B\002&\002\032\001\007\001\006C\004\002$%\"\t!!\n\002\031\021\"\027N\036\023qKJ\034WM\034;\025\t\005\035\022Q\006\t\006\033\005%\002\006K\005\004\003W!!A\002+va2,'\007\003\004K\003C\001\r\001\013\005\b\003cIC\021AA\032\003)!C.Z:tI1,7o\035\013\004Q\005U\002bBA\034\003_\001\r!G\001\002]\"9\0211H\025\005\002\005u\022\001\005\023he\026\fG/\032:%OJ,\027\r^3s)\rA\023q\b\005\b\003o\tI\0041\001\032\021\035\t\031%\013C\001\003\013\nA\001J1naR\031\001&a\022\t\r)\013\t\0051\001)\021\035\tY%\013C\001\003\033\nA\001\n2beR\031\001&a\024\t\r)\013I\0051\001)\021\035\t\031&\013C\001\003+\n1\001J;q)\rA\023q\013\005\007\025\006E\003\031\001\025\t\017\005m\023\006\"\001\002^\005QA%Y7qIQLG\016Z3\025\007!\ny\006\003\004K\0033\002\r\001\013\005\b\003GJC\021AA3\003\r97\r\032\013\004Q\005\035\004B\002&\002b\001\007\001\006C\004\002l%\"\t!!\034\002\0075|G\rF\002)\003_BaASA5\001\004A\003bBA:S\021\005\021QO\001\004[&tGc\001\025\002x!1!*!\035A\002!Bq!a\037*\t\003\ti(A\002nCb$2\001KA@\021\031Q\025\021\020a\001Q!9\0211Q\025\005\002\005\025\025a\0019poR\031\001&a\"\t\017\005%\025\021\021a\0013\005\031Q\r\0379\t\017\0055\025\006\"\001\002\020\0061Qn\0343Q_^$R\001KAI\003'Cq!!#\002\f\002\007\001\006C\004\002\026\006-\005\031\001\025\002\0035Dq!!'*\t\003\tY*\001\006n_\022LeN^3sg\026$2\001KAO\021\035\t)*a&A\002!Bq!!)*\t\003\t\031+\001\007v]\006\024\030p\030\023nS:,8/F\001)\021\035\t9+\013C\001\003G\0131!\0312t\021\031\tY+\013C\0011\00511/[4ok6Dq!a,*\t\003\t\031+\001\004%i&dG-\032\025\t\003[\013\031,!/\002>B\031Q\"!.\n\007\005]FA\001\006eKB\024XmY1uK\022\f#!a/\002QU\033X\r\t@cS\036Le\016\036\021)i\",\007%\0368bef|f\020I7fi\"|G-\013\021j]N$X-\0313\"\005\005}\026A\002\032/cAr\003\007C\004\002D&\"\t!a)\002\031Ut\027M]=`IQLG\016Z3\t\017\005\035\027\006\"\001\002J\0069A/Z:u\005&$Hc\001$\002L\"9\021qGAc\001\004I\002bBAhS\021\005\021\021[\001\007g\026$()\033;\025\007!\n\031\016C\004\0028\0055\007\031A\r\t\017\005]\027\006\"\001\002Z\006A1\r\\3be\nKG\017F\002)\0037Dq!a\016\002V\002\007\021\004C\004\002`&\"\t!!9\002\017\031d\027\016\035\"jiR\031\001&a9\t\017\005]\022Q\034a\0013!1\021q]\025\005\002a\tA\002\\8xKN$8+\032;CSRDa!a;*\t\003A\022!\0032ji2+gn\032;i\021\031\ty/\013C\0011\005A!-\033;D_VtG\017C\004\002t&\"\t!!>\002\037%\034\bK]8cC\ndW\r\025:j[\026$2ARA|\021\035\tI0!=A\002e\t\021bY3si\006Lg\016^=\t\017\005u\030\006\"\021\002\000\006I!-\037;f-\006dW/\032\013\003\005\003\0012!\004B\002\023\r\021)\001\002\002\005\005f$X\rC\004\003\n%\"\tEa\003\002\025MDwN\035;WC2,X\r\006\002\003\016A\031QBa\004\n\007\tEAAA\003TQ>\024H\017C\004\003\026%\"\tAa\006\002\023\rD\027M\035,bYV,WC\001B\r!\ri!1D\005\004\005;!!\001B\"iCJDaA!\t*\t\003\021\025\001C5oiZ\013G.^3\t\017\t\025\022\006\"\001\003(\005IAn\0348h-\006dW/\032\013\003\005S\0012!\004B\026\023\r\021i\003\002\002\005\031>tw\rC\004\0032%\"\tAa\r\002\025\031dw.\031;WC2,X\r\006\002\0036A\031QBa\016\n\007\teBAA\003GY>\fG\017C\004\003>%\"\tAa\020\002\027\021|WO\0317f-\006dW/\032\013\003\005\003\0022!\004B\"\023\r\021)\005\002\002\007\t>,(\r\\3\t\017\t%\023\006\"\001\003L\005)QO\034;jYR1!Q\nB>\005\002bAa\024\003`\t\025d\002\002B)\0057j!Aa\025\013\t\tU#qK\001\nS6lW\017^1cY\026T1A!\027\005\003)\031w\016\0347fGRLwN\\\005\005\005;\022\031&\001\007Ok6,'/[2SC:<W-\003\003\003b\t\r$!C#yG2,8/\033<f\025\021\021iFa\025\021\t\t\035$q\017\b\005\005S\022\031H\004\003\003l\tETB\001B7\025\r\021yGB\001\007yI|w\016\036 \n\003\025I1A!\036\005\003\035\001\030mY6bO\026L1A\003B=\025\r\021)\b\002\005\b\005{\0229\0051\001)\003\r)g\016\032\005\n\005\003\0239\005%AA\002!\nAa\035;fa\"9!QQ\025\005\002\t\035\025A\001;p)\031\021IIa$\003\022B1!q\nBF\005KJAA!$\003d\tI\021J\\2mkNLg/\032\005\b\005{\022\031\t1\001)\021%\021\tIa!\021\002\003\007\001\006C\004\003\026&\"\tEa&\002\021Q|7\013\036:j]\036$\"A!'\021\t\tm%\021\025\b\004\033\tu\025b\001BP\t\0051\001K]3eK\032LAAa)\003&\n11\013\036:j]\036T1Aa(\005\021\035\021)*\013C\001\005S#BA!'\003,\"9!Q\026BT\001\004I\022!\002:bI&D\bb\002BYS\021\005!1W\001\fi>\024\025\020^3BeJ\f\0270\006\002\0036B!QB\nB\001\021%\021I,KI\001\n\003\021Y,A\bv]RLG\016\n3fM\006,H\016\036\0233+\t\021iLK\002)\005[#A!1\021\t\t\r'QZ\007\003\005\013TAAa2\003J\006IQO\\2iK\016\\W\r\032\006\004\005\027$\021AC1o]>$\030\r^5p]&!!q\032Bc\005E)hn\0315fG.,GMV1sS\006t7-\032\005\n\005'L\023\023!C\001\005w\013A\002^8%I\0264\027-\0367uIIBs!\013Bl\005;\fi\fE\002\016\0053L1Aa7\005\005U!W\r\035:fG\006$X\rZ%oQ\026\024\030\016^1oG\026\f#Aa8\002=QC\027n\035\021dY\006\0348\017I<jY2\004#-\032\021nC\022,\007EZ5oC2t\003b\002Br\023\001\006I!J\001\007G\006\034\007.\032\021\t\021\t\035\030B1A\005\nM\n\001\"\\5okN|e.\032\005\b\005WL\001\025!\0035\003%i\027N\\;t\037:,\007\005C\005\003p&\021\r\021\"\001\002$\0069Q*\0338M_:<\007\006\003Bw\003g\023\031Pa>\"\005\tU\030!E+tK\002buN\\4/\033&tg+\0317vK\006\022!\021`\001\006e9Jd\006\r\005\b\005{L\001\025!\003)\003!i\025N\034'p]\036\004\003\"CB\001\023\t\007I\021AAR\003\035i\025\r\037'p]\036D\003Ba@\0024\016\025!q_\021\003\007\017\t\021#V:fA1{gn\032\030NCb4\026\r\\;f\021\035\031Y!\003Q\001\n!\n\001\"T1y\031>tw\r\t\005\b\007\037IA\021AB\t\003\025\t\007\017\0357z)\rA31\003\005\b\007+\031i\0011\001\032\003\005I\007bBB\b\023\021\0051\021\004\013\004Q\rm\001\002CB\017\007/\001\rA!\013\002\0031Dqaa\004\n\t\003\031\t\003F\002)\007GA\001b!\n\004 \001\007!QW\001\002q\"91qB\005\005\002\r%B#\002\025\004,\r5\002bBAV\007O\001\r!\007\005\t\007_\0319\0031\001\0036\006IQ.Y4oSR,H-\032\005\b\007\037IA\021AB\032)\035A3QGB\035\007wAqaa\016\0042\001\007\021$A\005cSRdWM\\4uQ\"9\021\021`B\031\001\004I\002\002CB\037\007c\001\raa\020\002\007ItG\r\005\003\004B\r\035SBAB\"\025\r\031)\005B\001\005kRLG.\003\003\004J\r\r#A\002*b]\022|W\016C\004\004\020%!\ta!\024\025\013!\032yea\025\t\017\rE31\na\0013\0059a.^7cSR\034\b\002CB\037\007\027\002\raa\020\t\017\r=\021\002\"\001\004XQ\031\001f!\027\t\021\r\0252Q\013a\001\0053Cqaa\004\n\t\003\031i\006F\003)\007?\032\t\007\003\005\004&\rm\003\031\001BM\021\035\021ika\027A\002eAqaa\004\n\t\003\031)\007F\002)\007OBqa!\n\004d\001\007A\007C\004\004l%!\ta!\034\002\033A\024xNY1cY\026\004&/[7f)\025A3qNB9\021\035\tYo!\033A\002eA\001b!\020\004j\001\0071q\b\005\b\007kJA1AB<\003)Ig\016\036\032cS\036Le\016\036\013\004Q\re\004bBB\013\007g\002\r!\007\005\b\007{JA1AB@\003-awN\\43E&<\027J\034;\025\007!\032\t\t\003\005\004\036\rm\004\031\001B\025\021\035\031))\003C\002\007\017\013QC[1wC\nKw-\0238uK\036,'O\r2jO&sG\017F\002)\007\023Cqa!\n\004\004\002\007A\007C\005\004\016&\t\t\021\"\003\004\020\006Y!/Z1e%\026\034x\016\034<f)\t\031\t\n\005\003\004\024\016eUBABK\025\r\0319jN\001\005Y\006tw-\003\003\004\034\016U%AB(cU\026\034G\017")
/*     */ public class BigInt extends ScalaNumber implements ScalaNumericConversions, Serializable {
/*     */   private final BigInteger bigInteger;
/*     */   
/*     */   public char toChar() {
/* 118 */     return ScalaNumericAnyConversions$class.toChar(this);
/*     */   }
/*     */   
/*     */   public byte toByte() {
/* 118 */     return ScalaNumericAnyConversions$class.toByte(this);
/*     */   }
/*     */   
/*     */   public short toShort() {
/* 118 */     return ScalaNumericAnyConversions$class.toShort(this);
/*     */   }
/*     */   
/*     */   public int toInt() {
/* 118 */     return ScalaNumericAnyConversions$class.toInt(this);
/*     */   }
/*     */   
/*     */   public long toLong() {
/* 118 */     return ScalaNumericAnyConversions$class.toLong(this);
/*     */   }
/*     */   
/*     */   public float toFloat() {
/* 118 */     return ScalaNumericAnyConversions$class.toFloat(this);
/*     */   }
/*     */   
/*     */   public double toDouble() {
/* 118 */     return ScalaNumericAnyConversions$class.toDouble(this);
/*     */   }
/*     */   
/*     */   public int unifiedPrimitiveHashcode() {
/* 118 */     return ScalaNumericAnyConversions$class.unifiedPrimitiveHashcode(this);
/*     */   }
/*     */   
/*     */   public boolean unifiedPrimitiveEquals(Object x) {
/* 118 */     return ScalaNumericAnyConversions$class.unifiedPrimitiveEquals(this, x);
/*     */   }
/*     */   
/*     */   public BigInteger bigInteger() {
/* 118 */     return this.bigInteger;
/*     */   }
/*     */   
/*     */   public BigInt(BigInteger bigInteger) {
/* 118 */     ScalaNumericAnyConversions$class.$init$(this);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 121 */     return isValidLong() ? unifiedPrimitiveHashcode() : ScalaRunTime$.MODULE$
/* 122 */       .hash(bigInteger());
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: instanceof scala/math/BigInt
/*     */     //   4: ifeq -> 22
/*     */     //   7: aload_1
/*     */     //   8: checkcast scala/math/BigInt
/*     */     //   11: astore_2
/*     */     //   12: aload_0
/*     */     //   13: aload_2
/*     */     //   14: invokevirtual equals : (Lscala/math/BigInt;)Z
/*     */     //   17: istore #11
/*     */     //   19: goto -> 178
/*     */     //   22: aload_1
/*     */     //   23: instanceof scala/math/BigDecimal
/*     */     //   26: ifeq -> 76
/*     */     //   29: aload_1
/*     */     //   30: checkcast scala/math/BigDecimal
/*     */     //   33: astore_3
/*     */     //   34: aload_3
/*     */     //   35: invokevirtual toBigIntExact : ()Lscala/Option;
/*     */     //   38: dup
/*     */     //   39: astore #4
/*     */     //   41: invokevirtual isEmpty : ()Z
/*     */     //   44: ifne -> 70
/*     */     //   47: aload #4
/*     */     //   49: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   52: checkcast scala/math/BigInt
/*     */     //   55: astore #6
/*     */     //   57: aload_0
/*     */     //   58: aload #6
/*     */     //   60: invokevirtual equals : (Lscala/math/BigInt;)Z
/*     */     //   63: ifeq -> 70
/*     */     //   66: iconst_1
/*     */     //   67: goto -> 71
/*     */     //   70: iconst_0
/*     */     //   71: istore #11
/*     */     //   73: goto -> 178
/*     */     //   76: aload_1
/*     */     //   77: instanceof java/lang/Double
/*     */     //   80: ifeq -> 116
/*     */     //   83: aload_1
/*     */     //   84: invokestatic unboxToDouble : (Ljava/lang/Object;)D
/*     */     //   87: dstore #8
/*     */     //   89: aload_0
/*     */     //   90: invokevirtual isValidDouble : ()Z
/*     */     //   93: ifeq -> 110
/*     */     //   96: aload_0
/*     */     //   97: invokevirtual toDouble : ()D
/*     */     //   100: dload #8
/*     */     //   102: dcmpl
/*     */     //   103: ifne -> 110
/*     */     //   106: iconst_1
/*     */     //   107: goto -> 111
/*     */     //   110: iconst_0
/*     */     //   111: istore #11
/*     */     //   113: goto -> 178
/*     */     //   116: aload_1
/*     */     //   117: instanceof java/lang/Float
/*     */     //   120: ifeq -> 156
/*     */     //   123: aload_1
/*     */     //   124: invokestatic unboxToFloat : (Ljava/lang/Object;)F
/*     */     //   127: fstore #10
/*     */     //   129: aload_0
/*     */     //   130: invokevirtual isValidFloat : ()Z
/*     */     //   133: ifeq -> 150
/*     */     //   136: aload_0
/*     */     //   137: invokevirtual toFloat : ()F
/*     */     //   140: fload #10
/*     */     //   142: fcmpl
/*     */     //   143: ifne -> 150
/*     */     //   146: iconst_1
/*     */     //   147: goto -> 151
/*     */     //   150: iconst_0
/*     */     //   151: istore #11
/*     */     //   153: goto -> 178
/*     */     //   156: aload_0
/*     */     //   157: invokevirtual isValidLong : ()Z
/*     */     //   160: ifeq -> 175
/*     */     //   163: aload_0
/*     */     //   164: aload_1
/*     */     //   165: invokevirtual unifiedPrimitiveEquals : (Ljava/lang/Object;)Z
/*     */     //   168: ifeq -> 175
/*     */     //   171: iconst_1
/*     */     //   172: goto -> 176
/*     */     //   175: iconst_0
/*     */     //   176: istore #11
/*     */     //   178: iload #11
/*     */     //   180: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #127	-> 0
/*     */     //   #126	-> 0
/*     */     //   #128	-> 22
/*     */     //   #129	-> 76
/*     */     //   #130	-> 116
/*     */     //   #131	-> 156
/*     */     //   #126	-> 178
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	181	0	this	Lscala/math/BigInt;
/*     */     //   0	181	1	that	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public boolean isValidByte() {
/* 133 */     return ($greater$eq(BigInt$.MODULE$.int2bigInt(-128)) && $less$eq(BigInt$.MODULE$.int2bigInt(127)));
/*     */   }
/*     */   
/*     */   public boolean isValidShort() {
/* 134 */     return ($greater$eq(BigInt$.MODULE$.int2bigInt(-32768)) && $less$eq(BigInt$.MODULE$.int2bigInt(32767)));
/*     */   }
/*     */   
/*     */   public boolean isValidChar() {
/* 135 */     return ($greater$eq(BigInt$.MODULE$.int2bigInt(0)) && $less$eq(BigInt$.MODULE$.int2bigInt(65535)));
/*     */   }
/*     */   
/*     */   public boolean isValidInt() {
/* 136 */     return ($greater$eq(BigInt$.MODULE$.int2bigInt(-2147483648)) && $less$eq(BigInt$.MODULE$.int2bigInt(2147483647)));
/*     */   }
/*     */   
/*     */   public boolean isValidLong() {
/* 137 */     return ($greater$eq(BigInt$.MODULE$.long2bigInt(Long.MIN_VALUE)) && $less$eq(BigInt$.MODULE$.long2bigInt(Long.MAX_VALUE)));
/*     */   }
/*     */   
/*     */   public boolean isValidFloat() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual bitLength : ()I
/*     */     //   4: istore_1
/*     */     //   5: iload_1
/*     */     //   6: bipush #24
/*     */     //   8: if_icmple -> 46
/*     */     //   11: aload_0
/*     */     //   12: invokevirtual lowestSetBit : ()I
/*     */     //   15: istore_2
/*     */     //   16: iload_1
/*     */     //   17: sipush #128
/*     */     //   20: if_icmpgt -> 42
/*     */     //   23: iload_2
/*     */     //   24: iload_1
/*     */     //   25: bipush #24
/*     */     //   27: isub
/*     */     //   28: if_icmplt -> 42
/*     */     //   31: iload_2
/*     */     //   32: sipush #128
/*     */     //   35: if_icmpge -> 42
/*     */     //   38: iconst_1
/*     */     //   39: goto -> 43
/*     */     //   42: iconst_0
/*     */     //   43: ifeq -> 57
/*     */     //   46: aload_0
/*     */     //   47: invokespecial bitLengthOverflow : ()Z
/*     */     //   50: ifne -> 57
/*     */     //   53: iconst_1
/*     */     //   54: goto -> 58
/*     */     //   57: iconst_0
/*     */     //   58: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #141	-> 0
/*     */     //   #142	-> 5
/*     */     //   #144	-> 11
/*     */     //   #145	-> 16
/*     */     //   #146	-> 23
/*     */     //   #147	-> 31
/*     */     //   #146	-> 38
/*     */     //   #149	-> 46
/*     */     //   #140	-> 58
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	59	0	this	Lscala/math/BigInt;
/*     */     //   5	54	1	bitLen	I
/*     */     //   16	43	2	lowest	I
/*     */   }
/*     */   
/*     */   public boolean isValidDouble() {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual bitLength : ()I
/*     */     //   4: istore_1
/*     */     //   5: iload_1
/*     */     //   6: bipush #53
/*     */     //   8: if_icmple -> 46
/*     */     //   11: aload_0
/*     */     //   12: invokevirtual lowestSetBit : ()I
/*     */     //   15: istore_2
/*     */     //   16: iload_1
/*     */     //   17: sipush #1024
/*     */     //   20: if_icmpgt -> 42
/*     */     //   23: iload_2
/*     */     //   24: iload_1
/*     */     //   25: bipush #53
/*     */     //   27: isub
/*     */     //   28: if_icmplt -> 42
/*     */     //   31: iload_2
/*     */     //   32: sipush #1024
/*     */     //   35: if_icmpge -> 42
/*     */     //   38: iconst_1
/*     */     //   39: goto -> 43
/*     */     //   42: iconst_0
/*     */     //   43: ifeq -> 57
/*     */     //   46: aload_0
/*     */     //   47: invokespecial bitLengthOverflow : ()Z
/*     */     //   50: ifne -> 57
/*     */     //   53: iconst_1
/*     */     //   54: goto -> 58
/*     */     //   57: iconst_0
/*     */     //   58: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #154	-> 0
/*     */     //   #155	-> 5
/*     */     //   #157	-> 11
/*     */     //   #158	-> 16
/*     */     //   #159	-> 23
/*     */     //   #160	-> 31
/*     */     //   #159	-> 38
/*     */     //   #162	-> 46
/*     */     //   #153	-> 58
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	59	0	this	Lscala/math/BigInt;
/*     */     //   5	54	1	bitLen	I
/*     */     //   16	43	2	lowest	I
/*     */   }
/*     */   
/*     */   private boolean bitLengthOverflow() {
/* 170 */     BigInteger shifted = bigInteger().shiftRight(2147483647);
/* 171 */     return (shifted.signum() != 0 && !shifted.equals(BigInt$.MODULE$.scala$math$BigInt$$minusOne()));
/*     */   }
/*     */   
/*     */   public boolean isWhole() {
/* 174 */     return true;
/*     */   }
/*     */   
/*     */   public BigInteger underlying() {
/* 175 */     return bigInteger();
/*     */   }
/*     */   
/*     */   public boolean equals(BigInt that) {
/* 179 */     return (compare(that) == 0);
/*     */   }
/*     */   
/*     */   public int compare(BigInt that) {
/* 183 */     return bigInteger().compareTo(that.bigInteger());
/*     */   }
/*     */   
/*     */   public boolean $less$eq(BigInt that) {
/* 187 */     return (compare(that) <= 0);
/*     */   }
/*     */   
/*     */   public boolean $greater$eq(BigInt that) {
/* 191 */     return (compare(that) >= 0);
/*     */   }
/*     */   
/*     */   public boolean $less(BigInt that) {
/* 195 */     return (compare(that) < 0);
/*     */   }
/*     */   
/*     */   public boolean $greater(BigInt that) {
/* 199 */     return (compare(that) > 0);
/*     */   }
/*     */   
/*     */   public BigInt $plus(BigInt that) {
/* 203 */     return new BigInt(bigInteger().add(that.bigInteger()));
/*     */   }
/*     */   
/*     */   public BigInt $minus(BigInt that) {
/* 207 */     return new BigInt(bigInteger().subtract(that.bigInteger()));
/*     */   }
/*     */   
/*     */   public BigInt $times(BigInt that) {
/* 211 */     return new BigInt(bigInteger().multiply(that.bigInteger()));
/*     */   }
/*     */   
/*     */   public BigInt $div(BigInt that) {
/* 215 */     return new BigInt(bigInteger().divide(that.bigInteger()));
/*     */   }
/*     */   
/*     */   public BigInt $percent(BigInt that) {
/* 219 */     return new BigInt(bigInteger().remainder(that.bigInteger()));
/*     */   }
/*     */   
/*     */   public Tuple2<BigInt, BigInt> $div$percent(BigInt that) {
/* 224 */     BigInteger[] dr = bigInteger().divideAndRemainder(that.bigInteger());
/* 225 */     return new Tuple2(new BigInt(dr[0]), new BigInt(dr[1]));
/*     */   }
/*     */   
/*     */   public BigInt $less$less(int n) {
/* 230 */     return new BigInt(bigInteger().shiftLeft(n));
/*     */   }
/*     */   
/*     */   public BigInt $greater$greater(int n) {
/* 234 */     return new BigInt(bigInteger().shiftRight(n));
/*     */   }
/*     */   
/*     */   public BigInt $amp(BigInt that) {
/* 238 */     return new BigInt(bigInteger().and(that.bigInteger()));
/*     */   }
/*     */   
/*     */   public BigInt $bar(BigInt that) {
/* 242 */     return new BigInt(bigInteger().or(that.bigInteger()));
/*     */   }
/*     */   
/*     */   public BigInt $up(BigInt that) {
/* 246 */     return new BigInt(bigInteger().xor(that.bigInteger()));
/*     */   }
/*     */   
/*     */   public BigInt $amp$tilde(BigInt that) {
/* 250 */     return new BigInt(bigInteger().andNot(that.bigInteger()));
/*     */   }
/*     */   
/*     */   public BigInt gcd(BigInt that) {
/* 254 */     return new BigInt(bigInteger().gcd(that.bigInteger()));
/*     */   }
/*     */   
/*     */   public BigInt mod(BigInt that) {
/* 259 */     return new BigInt(bigInteger().mod(that.bigInteger()));
/*     */   }
/*     */   
/*     */   public BigInt min(BigInt that) {
/* 263 */     return new BigInt(bigInteger().min(that.bigInteger()));
/*     */   }
/*     */   
/*     */   public BigInt max(BigInt that) {
/* 267 */     return new BigInt(bigInteger().max(that.bigInteger()));
/*     */   }
/*     */   
/*     */   public BigInt pow(int exp) {
/* 271 */     return new BigInt(bigInteger().pow(exp));
/*     */   }
/*     */   
/*     */   public BigInt modPow(BigInt exp, BigInt m) {
/* 277 */     return new BigInt(bigInteger().modPow(exp.bigInteger(), m.bigInteger()));
/*     */   }
/*     */   
/*     */   public BigInt modInverse(BigInt m) {
/* 281 */     return new BigInt(bigInteger().modInverse(m.bigInteger()));
/*     */   }
/*     */   
/*     */   public BigInt unary_$minus() {
/* 285 */     return new BigInt(bigInteger().negate());
/*     */   }
/*     */   
/*     */   public BigInt abs() {
/* 289 */     return new BigInt(bigInteger().abs());
/*     */   }
/*     */   
/*     */   public int signum() {
/* 296 */     return bigInteger().signum();
/*     */   }
/*     */   
/*     */   public BigInt $tilde() {
/* 299 */     return unary_$tilde();
/*     */   }
/*     */   
/*     */   public BigInt unary_$tilde() {
/* 303 */     return new BigInt(bigInteger().not());
/*     */   }
/*     */   
/*     */   public boolean testBit(int n) {
/* 307 */     return bigInteger().testBit(n);
/*     */   }
/*     */   
/*     */   public BigInt setBit(int n) {
/* 311 */     return new BigInt(bigInteger().setBit(n));
/*     */   }
/*     */   
/*     */   public BigInt clearBit(int n) {
/* 315 */     return new BigInt(bigInteger().clearBit(n));
/*     */   }
/*     */   
/*     */   public BigInt flipBit(int n) {
/* 319 */     return new BigInt(bigInteger().flipBit(n));
/*     */   }
/*     */   
/*     */   public int lowestSetBit() {
/* 324 */     return bigInteger().getLowestSetBit();
/*     */   }
/*     */   
/*     */   public int bitLength() {
/* 329 */     return bigInteger().bitLength();
/*     */   }
/*     */   
/*     */   public int bitCount() {
/* 334 */     return bigInteger().bitCount();
/*     */   }
/*     */   
/*     */   public boolean isProbablePrime(int certainty) {
/* 343 */     return bigInteger().isProbablePrime(certainty);
/*     */   }
/*     */   
/*     */   public byte byteValue() {
/* 350 */     return (byte)intValue();
/*     */   }
/*     */   
/*     */   public short shortValue() {
/* 357 */     return (short)intValue();
/*     */   }
/*     */   
/*     */   public char charValue() {
/* 364 */     return (char)intValue();
/*     */   }
/*     */   
/*     */   public int intValue() {
/* 372 */     return bigInteger().intValue();
/*     */   }
/*     */   
/*     */   public long longValue() {
/* 380 */     return bigInteger().longValue();
/*     */   }
/*     */   
/*     */   public float floatValue() {
/* 387 */     return bigInteger().floatValue();
/*     */   }
/*     */   
/*     */   public double doubleValue() {
/* 394 */     return bigInteger().doubleValue();
/*     */   }
/*     */   
/*     */   public NumericRange.Exclusive<BigInt> until(BigInt end, BigInt step) {
/* 403 */     return Range$BigInt$.MODULE$.apply(this, end, step);
/*     */   }
/*     */   
/*     */   public BigInt until$default$2() {
/* 403 */     return BigInt$.MODULE$.apply(1);
/*     */   }
/*     */   
/*     */   public NumericRange.Inclusive<BigInt> to(BigInt end, BigInt step) {
/* 407 */     return Range$BigInt$.MODULE$.inclusive(this, end, step);
/*     */   }
/*     */   
/*     */   public BigInt to$default$2() {
/* 407 */     return BigInt$.MODULE$.apply(1);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 411 */     return bigInteger().toString();
/*     */   }
/*     */   
/*     */   public String toString(int radix) {
/* 415 */     return bigInteger().toString(radix);
/*     */   }
/*     */   
/*     */   public byte[] toByteArray() {
/* 423 */     return bigInteger().toByteArray();
/*     */   }
/*     */   
/*     */   public static BigInt javaBigInteger2bigInt(BigInteger paramBigInteger) {
/*     */     return BigInt$.MODULE$.javaBigInteger2bigInt(paramBigInteger);
/*     */   }
/*     */   
/*     */   public static BigInt long2bigInt(long paramLong) {
/*     */     return BigInt$.MODULE$.long2bigInt(paramLong);
/*     */   }
/*     */   
/*     */   public static BigInt int2bigInt(int paramInt) {
/*     */     return BigInt$.MODULE$.int2bigInt(paramInt);
/*     */   }
/*     */   
/*     */   public static BigInt probablePrime(int paramInt, Random paramRandom) {
/*     */     return BigInt$.MODULE$.probablePrime(paramInt, paramRandom);
/*     */   }
/*     */   
/*     */   public static BigInt apply(BigInteger paramBigInteger) {
/*     */     return BigInt$.MODULE$.apply(paramBigInteger);
/*     */   }
/*     */   
/*     */   public static BigInt apply(String paramString, int paramInt) {
/*     */     return BigInt$.MODULE$.apply(paramString, paramInt);
/*     */   }
/*     */   
/*     */   public static BigInt apply(String paramString) {
/*     */     return BigInt$.MODULE$.apply(paramString);
/*     */   }
/*     */   
/*     */   public static BigInt apply(int paramInt, Random paramRandom) {
/*     */     return BigInt$.MODULE$.apply(paramInt, paramRandom);
/*     */   }
/*     */   
/*     */   public static BigInt apply(int paramInt1, int paramInt2, Random paramRandom) {
/*     */     return BigInt$.MODULE$.apply(paramInt1, paramInt2, paramRandom);
/*     */   }
/*     */   
/*     */   public static BigInt apply(int paramInt, byte[] paramArrayOfbyte) {
/*     */     return BigInt$.MODULE$.apply(paramInt, paramArrayOfbyte);
/*     */   }
/*     */   
/*     */   public static BigInt apply(byte[] paramArrayOfbyte) {
/*     */     return BigInt$.MODULE$.apply(paramArrayOfbyte);
/*     */   }
/*     */   
/*     */   public static BigInt apply(long paramLong) {
/*     */     return BigInt$.MODULE$.apply(paramLong);
/*     */   }
/*     */   
/*     */   public static BigInt apply(int paramInt) {
/*     */     return BigInt$.MODULE$.apply(paramInt);
/*     */   }
/*     */   
/*     */   public static BigInt MaxLong() {
/*     */     return BigInt$.MODULE$.MaxLong();
/*     */   }
/*     */   
/*     */   public static BigInt MinLong() {
/*     */     return BigInt$.MODULE$.MinLong();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\BigInt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */