/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Array$;
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.TraversableLike;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.Growable;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\025ma!B\001\003\003\003I!\001D!se\006L()^5mI\026\024(BA\002\005\003\035iW\017^1cY\026T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)\"AC\013\024\t\001Yq\"\t\t\003\0315i\021AB\005\003\035\031\021a!\0218z%\0264\007\003\002\t\022'yi\021AA\005\003%\t\021qAQ;jY\022,'\017\005\002\025+1\001A!\002\f\001\005\0049\"!\001+\022\005aY\002C\001\007\032\023\tQbAA\004O_RD\027N\\4\021\0051a\022BA\017\007\005\r\te.\037\t\004\031}\031\022B\001\021\007\005\025\t%O]1z!\ta!%\003\002$\r\ta1+\032:jC2L'0\0312mK\")Q\005\001C\001M\0051A(\0338jiz\"\022a\n\t\004!\001\031r!B\025\003\021\003Q\023\001D!se\006L()^5mI\026\024\bC\001\t,\r\025\t!\001#\001-'\rY3\"\t\005\006K-\"\tA\f\013\002U!)\001g\013C\001c\005!Q.Y6f+\t\021d\007F\0014)\t!t\007E\002\021\001U\002\"\001\006\034\005\013Yy#\031A\f\t\017az\023\021!a\002s\005QQM^5eK:\034W\rJ\031\021\007ijT'D\001<\025\tad!A\004sK\032dWm\031;\n\005yZ$\001C\"mCN\034H+Y4\007\t\001[\003!\021\002\006_\032\024VMZ\013\003\005\026\033\"aP\"\021\007A\001A\t\005\002\025\013\022)ac\020b\001\rF\021\001d\003\005\t\021~\022\031\021)A\006\023\006QQM^5eK:\034W\r\n\032\021\007ijD\tC\003&\021\0051\nF\001M)\tiu\nE\002O\021k\021a\013\005\006\021*\003\035!\023\005\n#~\002\r\0211A\005\nI\013Q!\0327f[N,\022a\025\t\004\031}!\005\"C+@\001\004\005\r\021\"\003W\003%)G.Z7t?\022*\027\017\006\002X5B\021A\002W\005\0033\032\021A!\0268ji\"91\fVA\001\002\004\031\026a\001=%c!1Ql\020Q!\nM\013a!\0327f[N\004\003bB0@\001\004%I\001Y\001\tG\006\004\030mY5usV\t\021\r\005\002\rE&\0211M\002\002\004\023:$\bbB3@\001\004%IAZ\001\rG\006\004\030mY5us~#S-\035\013\003/\036Dqa\0273\002\002\003\007\021\r\003\004j\001\006K!Y\001\nG\006\004\030mY5us\002Bqa[ A\002\023%\001-\001\003tSj,\007bB7@\001\004%IA\\\001\tg&TXm\030\023fcR\021qk\034\005\b72\f\t\0211\001b\021\031\tx\b)Q\005C\006)1/\033>fA!)1o\020C\005i\0069Qn[!se\006LHCA*v\021\025Y'\0171\001b\021\0259x\b\"\003y\003\031\021Xm]5{KR\021q+\037\005\006WZ\004\r!\031\005\006w~\"\t\005`\001\tg&TX\rS5oiR\021q+ \005\006Wj\004\r!\031\005\007~\"I!!\001\002\025\025t7/\036:f'&TX\rF\002X\003\007AQa\033@A\002\005Dq!a\002@\t\003\tI!\001\005%a2,8\017J3r)\021\tY!!\004\016\003}Bq!a\004\002\006\001\007A)\001\003fY\026l\007bBA\n\021\005\023QC\001\016IAdWo\035\023qYV\034H%Z9\025\t\005-\021q\003\005\t\0033\t\t\0021\001\002\034\005\021\001p\035\t\006\003;\ty\002R\007\002\t%\031\021\021\005\003\003\037Q\023\030M^3sg\006\024G.Z(oG\026Dq!!\n@\t\003\t9#A\003dY\026\f'\017F\001X\021\035\tYc\020C\001\003[\taA]3tk2$H#A*\t\017\005Er\b\"\021\0024\0051Q-];bYN$B!!\016\002<A\031A\"a\016\n\007\005ebAA\004C_>dW-\0318\t\017\005u\022q\006a\0017\005)q\016\0365fe\"9\021\021I \005B\005\r\023\001\003;p'R\024\030N\\4\025\005\005\025\003\003BA$\003#j!!!\023\013\t\005-\023QJ\001\005Y\006twM\003\002\002P\005!!.\031<b\023\021\t\031&!\023\003\rM#(/\0338h\r\031\t9f\013\001\002Z\t1qN\032\"zi\026\034B!!\026\002\\A!\001\003AA/!\ra\021qL\005\004\003C2!\001\002\"zi\026Dq!JA+\t\003\t)\007\006\002\002hA\031a*!\026\t\027E\013)\0061AA\002\023%\0211N\013\003\003[\002B\001D\020\002^!YQ+!\026A\002\003\007I\021BA9)\r9\0261\017\005\n7\006=\024\021!a\001\003[B\001\"XA+A\003&\021Q\016\005\t?\006U\003\031!C\005A\"IQ-!\026A\002\023%\0211\020\013\004/\006u\004\002C.\002z\005\005\t\031A1\t\017%\f)\006)Q\005C\"A1.!\026A\002\023%\001\rC\005n\003+\002\r\021\"\003\002\006R\031q+a\"\t\021m\013\031)!AA\002\005Dq!]A+A\003&\021\rC\004t\003+\"I!!$\025\t\0055\024q\022\005\007W\006-\005\031A1\t\017]\f)\006\"\003\002\024R\031q+!&\t\r-\f\t\n1\001b\021\035Y\030Q\013C!\0033#2aVAN\021\031Y\027q\023a\001C\"9q0!\026\005\n\005}EcA,\002\"\"11.!(A\002\005D\001\"a\002\002V\021\005\021Q\025\013\005\003O\013I+\004\002\002V!A\021qBAR\001\004\ti\006\003\005\002\024\005UC\021IAW)\021\t9+a,\t\021\005e\0211\026a\001\003c\003b!!\b\002 \005u\003\002CA\023\003+\"\t!a\n\t\021\005-\022Q\013C\001\003o#\"!!\034\t\021\005E\022Q\013C!\003w#B!!\016\002>\"9\021QHA]\001\004Y\002\002CA!\003+\"\t%a\021\007\r\005\r7\006AAc\005\035ygm\0255peR\034B!!1\002HB!\001\003AAe!\ra\0211Z\005\004\003\0334!!B*i_J$\bbB\023\002B\022\005\021\021\033\013\003\003'\0042ATAa\021-\t\026\021\031a\001\002\004%I!a6\026\005\005e\007\003\002\007 \003\023D1\"VAa\001\004\005\r\021\"\003\002^R\031q+a8\t\023m\013Y.!AA\002\005e\007\002C/\002B\002\006K!!7\t\021}\013\t\r1A\005\n\001D\021\"ZAa\001\004%I!a:\025\007]\013I\017\003\005\\\003K\f\t\0211\001b\021\035I\027\021\031Q!\n\005D\001b[Aa\001\004%I\001\031\005\n[\006\005\007\031!C\005\003c$2aVAz\021!Y\026q^A\001\002\004\t\007bB9\002B\002\006K!\031\005\bg\006\005G\021BA})\021\tI.a?\t\r-\f9\0201\001b\021\0359\030\021\031C\005\003$2a\026B\001\021\031Y\027Q a\001C\"910!1\005B\t\025AcA,\003\b!11Na\001A\002\005Dqa`Aa\t\023\021Y\001F\002X\005\033Aaa\033B\005\001\004\t\007\002CA\004\003\003$\tA!\005\025\t\tM!QC\007\003\003\003D\001\"a\004\003\020\001\007\021\021\032\005\t\003'\t\t\r\"\021\003\032Q!!1\003B\016\021!\tIBa\006A\002\tu\001CBA\017\003?\tI\r\003\005\002&\005\005G\021AA\024\021!\tY#!1\005\002\t\rBCAAm\021!\t\t$!1\005B\t\035B\003BA\033\005SAq!!\020\003&\001\0071\004\003\005\002B\005\005G\021IA\"\r\031\021yc\013\001\0032\t1qNZ\"iCJ\034BA!\f\0034A!\001\003\001B\033!\ra!qG\005\004\005s1!\001B\"iCJDq!\nB\027\t\003\021i\004\006\002\003@A\031aJ!\f\t\027E\023i\0031AA\002\023%!1I\013\003\005\013\002B\001D\020\0036!YQK!\fA\002\003\007I\021\002B%)\r9&1\n\005\n7\n\035\023\021!a\001\005\013B\001\"\030B\027A\003&!Q\t\005\t?\n5\002\031!C\005A\"IQM!\fA\002\023%!1\013\013\004/\nU\003\002C.\003R\005\005\t\031A1\t\017%\024i\003)Q\005C\"A1N!\fA\002\023%\001\rC\005n\005[\001\r\021\"\003\003^Q\031qKa\030\t\021m\023Y&!AA\002\005Dq!\035B\027A\003&\021\rC\004t\005[!IA!\032\025\t\t\025#q\r\005\007W\n\r\004\031A1\t\017]\024i\003\"\003\003lQ\031qK!\034\t\r-\024I\0071\001b\021\035Y(Q\006C!\005c\"2a\026B:\021\031Y'q\016a\001C\"9qP!\f\005\n\t]DcA,\003z!11N!\036A\002\005D\001\"a\002\003.\021\005!Q\020\013\005\005\022\t)\004\002\003.!A\021q\002B>\001\004\021)\004\003\005\002\024\t5B\021\tBC)\021\021yHa\"\t\021\005e!1\021a\001\005\023\003b!!\b\002 \tU\002\002CA\023\005[!\t!a\n\t\021\005-\"Q\006C\001\005\037#\"A!\022\t\021\005E\"Q\006C!\005'#B!!\016\003\026\"9\021Q\bBI\001\004Y\002\002CA!\005[!\t%a\021\007\r\tm5\006\001BO\005\025yg-\0238u'\021\021IJa(\021\007A\001\021\rC\004&\0053#\tAa)\025\005\t\025\006c\001(\003\032\"Y\021K!'A\002\003\007I\021\002BU+\t\021Y\013E\002\r?\005D1\"\026BM\001\004\005\r\021\"\003\0030R\031qK!-\t\023m\023i+!AA\002\t-\006\002C/\003\032\002\006KAa+\t\021}\023I\n1A\005\n\001D\021\"\032BM\001\004%IA!/\025\007]\023Y\f\003\005\\\005o\013\t\0211\001b\021\035I'\021\024Q!\n\005D\001b\033BM\001\004%I\001\031\005\n[\ne\005\031!C\005\005\007$2a\026Bc\021!Y&\021YA\001\002\004\t\007bB9\003\032\002\006K!\031\005\bg\neE\021\002Bf)\021\021YK!4\t\r-\024I\r1\001b\021\0359(\021\024C\005\005#$2a\026Bj\021\031Y'q\032a\001C\"91P!'\005B\t]GcA,\003Z\"11N!6A\002\005Dqa BM\t\023\021i\016F\002X\005?Daa\033Bn\001\004\t\007\002CA\004\0053#\tAa9\025\t\t\025(q]\007\003\0053Cq!a\004\003b\002\007\021\r\003\005\002\024\teE\021\tBv)\021\021)O!<\t\021\005e!\021\036a\001\005_\004R!!\b\002 \005D\001\"!\n\003\032\022\005\021q\005\005\t\003W\021I\n\"\001\003vR\021!1\026\005\t\003c\021I\n\"\021\003zR!\021Q\007B~\021\035\tiDa>A\002mA\001\"!\021\003\032\022\005\0231\t\004\007\007\003Y\003aa\001\003\r=4Gj\0348h'\021\021yp!\002\021\tA\0011q\001\t\004\031\r%\021bAB\006\r\t!Aj\0348h\021\035)#q C\001\007\037!\"a!\005\021\0079\023y\020C\006R\005\004\r\0211A\005\n\rUQCAB\f!\021aqda\002\t\027U\023y\0201AA\002\023%11\004\013\004/\016u\001\"C.\004\032\005\005\t\031AB\f\021!i&q Q!\n\r]\001\002C0\003\000\002\007I\021\0021\t\023\025\024y\0201A\005\n\r\025BcA,\004(!A1la\t\002\002\003\007\021\rC\004j\005\004\013\025B1\t\021-\024y\0201A\005\n\001D\021\"\034B\000\001\004%Iaa\f\025\007]\033\t\004\003\005\\\007[\t\t\0211\001b\021\035\t(q Q!\n\005Dqa\035B\000\t\023\0319\004\006\003\004\030\re\002BB6\0046\001\007\021\rC\004x\005$Ia!\020\025\007]\033y\004\003\004l\007w\001\r!\031\005\bw\n}H\021IB\")\r96Q\t\005\007W\016\005\003\031A1\t\017}\024y\020\"\003\004JQ\031qka\023\t\r-\0349\0051\001b\021!\t9Aa@\005\002\r=C\003BB)\007'j!Aa@\t\021\005=1Q\na\001\007\017A\001\"a\005\003\000\022\0053q\013\013\005\007#\032I\006\003\005\002\032\rU\003\031AB.!\031\ti\"a\b\004\b!A\021Q\005B\000\t\003\t9\003\003\005\002,\t}H\021AB1)\t\0319\002\003\005\0022\t}H\021IB3)\021\t)da\032\t\017\005u21\ra\0017!A\021\021\tB\000\t\003\n\031E\002\004\004n-\0021q\016\002\b_\0324En\\1u'\021\031Yg!\035\021\tA\00111\017\t\004\031\rU\024bAB<\r\t)a\t\\8bi\"9Qea\033\005\002\rmDCAB?!\rq51\016\005\f#\016-\004\031!a\001\n\023\031\t)\006\002\004\004B!AbHB:\021-)61\016a\001\002\004%Iaa\"\025\007]\033I\tC\005\\\007\013\013\t\0211\001\004\004\"AQla\033!B\023\031\031\t\003\005`\007W\002\r\021\"\003a\021%)71\016a\001\n\023\031\t\nF\002X\007'C\001bWBH\003\003\005\r!\031\005\bS\016-\004\025)\003b\021!Y71\016a\001\n\023\001\007\"C7\004l\001\007I\021BBN)\r96Q\024\005\t7\016e\025\021!a\001C\"9\021oa\033!B\023\t\007bB:\004l\021%11\025\013\005\007\007\033)\013\003\004l\007C\003\r!\031\005\bo\016-D\021BBU)\r961\026\005\007W\016\035\006\031A1\t\017m\034Y\007\"\021\0040R\031qk!-\t\r-\034i\0131\001b\021\035y81\016C\005\007k#2aVB\\\021\031Y71\027a\001C\"A\021qAB6\t\003\031Y\f\006\003\004>\016}VBAB6\021!\tya!/A\002\rM\004\002CA\n\007W\"\tea1\025\t\ru6Q\031\005\t\0033\031\t\r1\001\004HB1\021QDA\020\007gB\001\"!\n\004l\021\005\021q\005\005\t\003W\031Y\007\"\001\004NR\02111\021\005\t\003c\031Y\007\"\021\004RR!\021QGBj\021\035\tida4A\002mA\001\"!\021\004l\021\005\0231\t\004\007\0073\\\003aa7\003\021=4Gi\\;cY\026\034Baa6\004^B!\001\003ABp!\ra1\021]\005\004\007G4!A\002#pk\ndW\rC\004&\007/$\taa:\025\005\r%\bc\001(\004X\"Y\021ka6A\002\003\007I\021BBw+\t\031y\017\005\003\r?\r}\007bC+\004X\002\007\t\031!C\005\007g$2aVB{\021%Y6\021_A\001\002\004\031y\017\003\005^\007/\004\013\025BBx\021!y6q\033a\001\n\023\001\007\"C3\004X\002\007I\021BB)\r96q \005\t7\016m\030\021!a\001C\"9\021na6!B\023\t\007\002C6\004X\002\007I\021\0021\t\0235\0349\0161A\005\n\021\035AcA,\005\n!A1\f\"\002\002\002\003\007\021\rC\004r\007/\004\013\025B1\t\017M\0349\016\"\003\005\020Q!1q\036C\t\021\031YGQ\002a\001C\"9qoa6\005\n\021UAcA,\005\030!11\016b\005A\002\005Dqa_Bl\t\003\"Y\002F\002X\t;Aaa\033C\r\001\004\t\007bB@\004X\022%A\021\005\013\004/\022\r\002BB6\005 \001\007\021\r\003\005\002\b\r]G\021\001C\024)\021!I\003b\013\016\005\r]\007\002CA\b\tK\001\raa8\t\021\005M1q\033C!\t_!B\001\"\013\0052!A\021\021\004C\027\001\004!\031\004\005\004\002\036\005}1q\034\005\t\003K\0319\016\"\001\002(!A\0211FBl\t\003!I\004\006\002\004p\"A\021\021GBl\t\003\"i\004\006\003\0026\021}\002bBA\037\tw\001\ra\007\005\t\003\003\0329\016\"\021\002D\0311AQI\026\001\t\017\022\021b\0344C_>dW-\0318\024\t\021\rC\021\n\t\005!\001\t)\004C\004&\t\007\"\t\001\"\024\025\005\021=\003c\001(\005D!Y\021\013b\021A\002\003\007I\021\002C*+\t!)\006\005\003\r?\005U\002bC+\005D\001\007\t\031!C\005\t3\"2a\026C.\021%YFqKA\001\002\004!)\006\003\005^\t\007\002\013\025\002C+\021!yF1\ta\001\n\023\001\007\"C3\005D\001\007I\021\002C2)\r9FQ\r\005\t7\022\005\024\021!a\001C\"9\021\016b\021!B\023\t\007\002C6\005D\001\007I\021\0021\t\0235$\031\0051A\005\n\0215DcA,\005p!A1\fb\033\002\002\003\007\021\rC\004r\t\007\002\013\025B1\t\017M$\031\005\"\003\005vQ!AQ\013C<\021\031YG1\017a\001C\"9q\017b\021\005\n\021mDcA,\005~!11\016\"\037A\002\005Dqa\037C\"\t\003\"\t\tF\002X\t\007Caa\033C@\001\004\t\007bB@\005D\021%Aq\021\013\004/\022%\005BB6\005\006\002\007\021\r\003\005\002\b\021\rC\021\001CG)\021!y\t\"%\016\005\021\r\003\002CA\b\t\027\003\r!!\016\t\021\005MA1\tC!\t+#B\001b$\005\030\"A\021\021\004CJ\001\004!I\n\005\004\002\036\005}\021Q\007\005\t\003K!\031\005\"\001\002(!A\0211\006C\"\t\003!y\n\006\002\005V!A\021\021\007C\"\t\003\"\031\013\006\003\0026\021\025\006bBA\037\tC\003\ra\007\005\t\003\003\"\031\005\"\021\002D\0311A1V\026\001\t[\023aa\0344V]&$8\003\002CU\t_\0032\001\005\001X\021\035)C\021\026C\001\tg#\"\001\".\021\0079#I\013C\006R\tS\003\r\0211A\005\n\021eVC\001C^!\raqd\026\005\f+\022%\006\031!a\001\n\023!y\fF\002X\t\003D\021b\027C_\003\003\005\r\001b/\t\021u#I\013)Q\005\twC\001b\030CU\001\004%I\001\031\005\nK\022%\006\031!C\005\t\023$2a\026Cf\021!YFqYA\001\002\004\t\007bB5\005*\002\006K!\031\005\tW\022%\006\031!C\005A\"IQ\016\"+A\002\023%A1\033\013\004/\022U\007\002C.\005R\006\005\t\031A1\t\017E$I\013)Q\005C\"91\017\"+\005\n\021mG\003\002C^\t;Daa\033Cm\001\004\t\007bB<\005*\022%A\021\035\013\004/\022\r\bBB6\005`\002\007\021\rC\004|\tS#\t\005b:\025\007]#I\017\003\004l\tK\004\r!\031\005\b\022%F\021\002Cw)\r9Fq\036\005\007W\022-\b\031A1\t\021\005\035A\021\026C\001\tg$B\001\">\005x6\021A\021\026\005\b\003\037!\t\0201\001X\021!\t\031\002\"+\005B\021mH\003\002C{\t{D\001\"!\007\005z\002\007Aq \t\006\003;\tyb\026\005\t\003K!I\013\"\001\002(!A\0211\006CU\t\003))\001\006\002\005<\"A\021\021\007CU\t\003*I\001\006\003\0026\025-\001bBA\037\013\017\001\ra\007\005\t\003\003\"I\013\"\021\002D!IQ\021C\026\002\002\023%Q1C\001\fe\026\fGMU3t_24X\r\006\002\006\026A!\021qIC\f\023\021)I\"!\023\003\r=\023'.Z2u\001")
/*     */ public abstract class ArrayBuilder<T> implements Builder<T, Object>, Serializable {
/*     */   public static <T> ArrayBuilder<T> make(ClassTag<T> paramClassTag) {
/*     */     return ArrayBuilder$.MODULE$.make(paramClassTag);
/*     */   }
/*     */   
/*     */   public void sizeHint(int size) {
/*  24 */     Builder$class.sizeHint(this, size);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll) {
/*  24 */     Builder$class.sizeHint(this, coll);
/*     */   }
/*     */   
/*     */   public void sizeHint(TraversableLike coll, int delta) {
/*  24 */     Builder$class.sizeHint(this, coll, delta);
/*     */   }
/*     */   
/*     */   public void sizeHintBounded(int size, TraversableLike boundingColl) {
/*  24 */     Builder$class.sizeHintBounded(this, size, boundingColl);
/*     */   }
/*     */   
/*     */   public <NewTo> Builder<T, NewTo> mapResult(Function1 f) {
/*  24 */     return Builder$class.mapResult(this, f);
/*     */   }
/*     */   
/*     */   public Growable<T> $plus$eq(Object elem1, Object elem2, Seq elems) {
/*  24 */     return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*     */   }
/*     */   
/*     */   public Growable<T> $plus$plus$eq(TraversableOnce xs) {
/*  24 */     return Growable.class.$plus$plus$eq(this, xs);
/*     */   }
/*     */   
/*     */   public ArrayBuilder() {
/*  24 */     Growable.class.$init$(this);
/*  24 */     Builder$class.$init$(this);
/*     */   }
/*     */   
/*     */   public static class ofRef<T> extends ArrayBuilder<T> {
/*     */     private final ClassTag<T> evidence$2;
/*     */     
/*     */     private T[] elems;
/*     */     
/*     */     private T[] elems() {
/*  59 */       return this.elems;
/*     */     }
/*     */     
/*     */     private void elems_$eq(Object[] x$1) {
/*  59 */       this.elems = (T[])x$1;
/*     */     }
/*     */     
/*  60 */     private int capacity = 0;
/*     */     
/*     */     private int capacity() {
/*  60 */       return this.capacity;
/*     */     }
/*     */     
/*     */     private void capacity_$eq(int x$1) {
/*  60 */       this.capacity = x$1;
/*     */     }
/*     */     
/*  61 */     private int size = 0;
/*     */     
/*     */     private int size() {
/*  61 */       return this.size;
/*     */     }
/*     */     
/*     */     private void size_$eq(int x$1) {
/*  61 */       this.size = x$1;
/*     */     }
/*     */     
/*     */     private T[] mkArray(int size) {
/*  64 */       Object[] newelems = (Object[])this.evidence$2.newArray(size);
/*  65 */       if (size() > 0)
/*  65 */         Array$.MODULE$.copy(elems(), 0, newelems, 0, size()); 
/*  66 */       return (T[])newelems;
/*     */     }
/*     */     
/*     */     private void resize(int size) {
/*  70 */       elems_$eq(mkArray(size));
/*  71 */       capacity_$eq(size);
/*     */     }
/*     */     
/*     */     public void sizeHint(int size) {
/*  75 */       if (capacity() < size)
/*  75 */         resize(size); 
/*     */     }
/*     */     
/*     */     private void ensureSize(int size) {
/*  79 */       if (capacity() < size || capacity() == 0) {
/*  80 */         int newsize = (capacity() == 0) ? 16 : (capacity() * 2);
/*  81 */         for (; newsize < size; newsize *= 2);
/*  82 */         resize(newsize);
/*     */       } 
/*     */     }
/*     */     
/*     */     public ofRef<T> $plus$eq(Object elem) {
/*  87 */       ensureSize(size() + 1);
/*  88 */       elems()[size()] = (T)elem;
/*  89 */       size_$eq(size() + 1);
/*  90 */       return this;
/*     */     }
/*     */     
/*     */     public ofRef<T> $plus$plus$eq(TraversableOnce xs) {
/*     */       ofRef<T> ofRef1;
/*  93 */       if (xs instanceof WrappedArray.ofRef) {
/*  93 */         WrappedArray.ofRef ofRef2 = (WrappedArray.ofRef)xs;
/*  95 */         ensureSize(size() + ofRef2.length());
/*  96 */         Array$.MODULE$.copy(ofRef2.array(), 0, elems(), size(), ofRef2.length());
/*  97 */         size_$eq(size() + ofRef2.length());
/*  98 */         ofRef1 = this;
/*     */       } else {
/* 100 */         ofRef1 = (ofRef)Growable.class.$plus$plus$eq(this, xs);
/*     */       } 
/*     */       return ofRef1;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 104 */       size_$eq(0);
/*     */     }
/*     */     
/*     */     public T[] result() {
/* 108 */       return (capacity() != 0 && capacity() == size()) ? elems() : 
/* 109 */         mkArray(size());
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/*     */       boolean bool;
/* 112 */       if (other instanceof ofRef) {
/* 112 */         ofRef<T> ofRef1 = (ofRef)other;
/* 112 */         bool = (size() == ofRef1.size() && elems() == ofRef1.elems()) ? true : false;
/*     */       } else {
/* 114 */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 117 */       return "ArrayBuilder.ofRef";
/*     */     }
/*     */     
/*     */     public ofRef(ClassTag<T> evidence$2) {}
/*     */   }
/*     */   
/*     */   public static class ofByte extends ArrayBuilder<Object> {
/*     */     private byte[] elems;
/*     */     
/*     */     private byte[] elems() {
/* 123 */       return this.elems;
/*     */     }
/*     */     
/*     */     private void elems_$eq(byte[] x$1) {
/* 123 */       this.elems = x$1;
/*     */     }
/*     */     
/* 124 */     private int capacity = 0;
/*     */     
/*     */     private int capacity() {
/* 124 */       return this.capacity;
/*     */     }
/*     */     
/*     */     private void capacity_$eq(int x$1) {
/* 124 */       this.capacity = x$1;
/*     */     }
/*     */     
/* 125 */     private int size = 0;
/*     */     
/*     */     private int size() {
/* 125 */       return this.size;
/*     */     }
/*     */     
/*     */     private void size_$eq(int x$1) {
/* 125 */       this.size = x$1;
/*     */     }
/*     */     
/*     */     private byte[] mkArray(int size) {
/* 128 */       byte[] newelems = new byte[size];
/* 129 */       if (size() > 0)
/* 129 */         Array$.MODULE$.copy(elems(), 0, newelems, 0, size()); 
/* 130 */       return newelems;
/*     */     }
/*     */     
/*     */     private void resize(int size) {
/* 134 */       elems_$eq(mkArray(size));
/* 135 */       capacity_$eq(size);
/*     */     }
/*     */     
/*     */     public void sizeHint(int size) {
/* 139 */       if (capacity() < size)
/* 139 */         resize(size); 
/*     */     }
/*     */     
/*     */     private void ensureSize(int size) {
/* 143 */       if (capacity() < size || capacity() == 0) {
/* 144 */         int newsize = (capacity() == 0) ? 16 : (capacity() * 2);
/* 145 */         for (; newsize < size; newsize *= 2);
/* 146 */         resize(newsize);
/*     */       } 
/*     */     }
/*     */     
/*     */     public ofByte $plus$eq(byte elem) {
/* 151 */       ensureSize(size() + 1);
/* 152 */       elems()[size()] = elem;
/* 153 */       size_$eq(size() + 1);
/* 154 */       return this;
/*     */     }
/*     */     
/*     */     public ofByte $plus$plus$eq(TraversableOnce xs) {
/*     */       ofByte ofByte1;
/* 157 */       if (xs instanceof WrappedArray.ofByte) {
/* 157 */         WrappedArray.ofByte ofByte2 = (WrappedArray.ofByte)xs;
/* 159 */         ensureSize(size() + ofByte2.length());
/* 160 */         Array$.MODULE$.copy(ofByte2.array(), 0, elems(), size(), ofByte2.length());
/* 161 */         size_$eq(size() + ofByte2.length());
/* 162 */         ofByte1 = this;
/*     */       } else {
/* 164 */         ofByte1 = (ofByte)Growable.class.$plus$plus$eq(this, xs);
/*     */       } 
/*     */       return ofByte1;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 168 */       size_$eq(0);
/*     */     }
/*     */     
/*     */     public byte[] result() {
/* 172 */       return (capacity() != 0 && capacity() == size()) ? elems() : 
/* 173 */         mkArray(size());
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/*     */       boolean bool;
/* 176 */       if (other instanceof ofByte) {
/* 176 */         ofByte ofByte1 = (ofByte)other;
/* 176 */         bool = (size() == ofByte1.size() && elems() == ofByte1.elems()) ? true : false;
/*     */       } else {
/* 178 */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 181 */       return "ArrayBuilder.ofByte";
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofShort extends ArrayBuilder<Object> {
/*     */     private short[] elems;
/*     */     
/*     */     private short[] elems() {
/* 187 */       return this.elems;
/*     */     }
/*     */     
/*     */     private void elems_$eq(short[] x$1) {
/* 187 */       this.elems = x$1;
/*     */     }
/*     */     
/* 188 */     private int capacity = 0;
/*     */     
/*     */     private int capacity() {
/* 188 */       return this.capacity;
/*     */     }
/*     */     
/*     */     private void capacity_$eq(int x$1) {
/* 188 */       this.capacity = x$1;
/*     */     }
/*     */     
/* 189 */     private int size = 0;
/*     */     
/*     */     private int size() {
/* 189 */       return this.size;
/*     */     }
/*     */     
/*     */     private void size_$eq(int x$1) {
/* 189 */       this.size = x$1;
/*     */     }
/*     */     
/*     */     private short[] mkArray(int size) {
/* 192 */       short[] newelems = new short[size];
/* 193 */       if (size() > 0)
/* 193 */         Array$.MODULE$.copy(elems(), 0, newelems, 0, size()); 
/* 194 */       return newelems;
/*     */     }
/*     */     
/*     */     private void resize(int size) {
/* 198 */       elems_$eq(mkArray(size));
/* 199 */       capacity_$eq(size);
/*     */     }
/*     */     
/*     */     public void sizeHint(int size) {
/* 203 */       if (capacity() < size)
/* 203 */         resize(size); 
/*     */     }
/*     */     
/*     */     private void ensureSize(int size) {
/* 207 */       if (capacity() < size || capacity() == 0) {
/* 208 */         int newsize = (capacity() == 0) ? 16 : (capacity() * 2);
/* 209 */         for (; newsize < size; newsize *= 2);
/* 210 */         resize(newsize);
/*     */       } 
/*     */     }
/*     */     
/*     */     public ofShort $plus$eq(short elem) {
/* 215 */       ensureSize(size() + 1);
/* 216 */       elems()[size()] = elem;
/* 217 */       size_$eq(size() + 1);
/* 218 */       return this;
/*     */     }
/*     */     
/*     */     public ofShort $plus$plus$eq(TraversableOnce xs) {
/*     */       ofShort ofShort1;
/* 221 */       if (xs instanceof WrappedArray.ofShort) {
/* 221 */         WrappedArray.ofShort ofShort2 = (WrappedArray.ofShort)xs;
/* 223 */         ensureSize(size() + ofShort2.length());
/* 224 */         Array$.MODULE$.copy(ofShort2.array(), 0, elems(), size(), ofShort2.length());
/* 225 */         size_$eq(size() + ofShort2.length());
/* 226 */         ofShort1 = this;
/*     */       } else {
/* 228 */         ofShort1 = (ofShort)Growable.class.$plus$plus$eq(this, xs);
/*     */       } 
/*     */       return ofShort1;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 232 */       size_$eq(0);
/*     */     }
/*     */     
/*     */     public short[] result() {
/* 236 */       return (capacity() != 0 && capacity() == size()) ? elems() : 
/* 237 */         mkArray(size());
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/*     */       boolean bool;
/* 240 */       if (other instanceof ofShort) {
/* 240 */         ofShort ofShort1 = (ofShort)other;
/* 240 */         bool = (size() == ofShort1.size() && elems() == ofShort1.elems()) ? true : false;
/*     */       } else {
/* 242 */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 245 */       return "ArrayBuilder.ofShort";
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofChar extends ArrayBuilder<Object> {
/*     */     private char[] elems;
/*     */     
/*     */     private char[] elems() {
/* 251 */       return this.elems;
/*     */     }
/*     */     
/*     */     private void elems_$eq(char[] x$1) {
/* 251 */       this.elems = x$1;
/*     */     }
/*     */     
/* 252 */     private int capacity = 0;
/*     */     
/*     */     private int capacity() {
/* 252 */       return this.capacity;
/*     */     }
/*     */     
/*     */     private void capacity_$eq(int x$1) {
/* 252 */       this.capacity = x$1;
/*     */     }
/*     */     
/* 253 */     private int size = 0;
/*     */     
/*     */     private int size() {
/* 253 */       return this.size;
/*     */     }
/*     */     
/*     */     private void size_$eq(int x$1) {
/* 253 */       this.size = x$1;
/*     */     }
/*     */     
/*     */     private char[] mkArray(int size) {
/* 256 */       char[] newelems = new char[size];
/* 257 */       if (size() > 0)
/* 257 */         Array$.MODULE$.copy(elems(), 0, newelems, 0, size()); 
/* 258 */       return newelems;
/*     */     }
/*     */     
/*     */     private void resize(int size) {
/* 262 */       elems_$eq(mkArray(size));
/* 263 */       capacity_$eq(size);
/*     */     }
/*     */     
/*     */     public void sizeHint(int size) {
/* 267 */       if (capacity() < size)
/* 267 */         resize(size); 
/*     */     }
/*     */     
/*     */     private void ensureSize(int size) {
/* 271 */       if (capacity() < size || capacity() == 0) {
/* 272 */         int newsize = (capacity() == 0) ? 16 : (capacity() * 2);
/* 273 */         for (; newsize < size; newsize *= 2);
/* 274 */         resize(newsize);
/*     */       } 
/*     */     }
/*     */     
/*     */     public ofChar $plus$eq(char elem) {
/* 279 */       ensureSize(size() + 1);
/* 280 */       elems()[size()] = elem;
/* 281 */       size_$eq(size() + 1);
/* 282 */       return this;
/*     */     }
/*     */     
/*     */     public ofChar $plus$plus$eq(TraversableOnce xs) {
/*     */       ofChar ofChar1;
/* 285 */       if (xs instanceof WrappedArray.ofChar) {
/* 285 */         WrappedArray.ofChar ofChar2 = (WrappedArray.ofChar)xs;
/* 287 */         ensureSize(size() + ofChar2.length());
/* 288 */         Array$.MODULE$.copy(ofChar2.array(), 0, elems(), size(), ofChar2.length());
/* 289 */         size_$eq(size() + ofChar2.length());
/* 290 */         ofChar1 = this;
/*     */       } else {
/* 292 */         ofChar1 = (ofChar)Growable.class.$plus$plus$eq(this, xs);
/*     */       } 
/*     */       return ofChar1;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 296 */       size_$eq(0);
/*     */     }
/*     */     
/*     */     public char[] result() {
/* 300 */       return (capacity() != 0 && capacity() == size()) ? elems() : 
/* 301 */         mkArray(size());
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/*     */       boolean bool;
/* 304 */       if (other instanceof ofChar) {
/* 304 */         ofChar ofChar1 = (ofChar)other;
/* 304 */         bool = (size() == ofChar1.size() && elems() == ofChar1.elems()) ? true : false;
/*     */       } else {
/* 306 */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 309 */       return "ArrayBuilder.ofChar";
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofInt extends ArrayBuilder<Object> {
/*     */     private int[] elems;
/*     */     
/*     */     private int[] elems() {
/* 315 */       return this.elems;
/*     */     }
/*     */     
/*     */     private void elems_$eq(int[] x$1) {
/* 315 */       this.elems = x$1;
/*     */     }
/*     */     
/* 316 */     private int capacity = 0;
/*     */     
/*     */     private int capacity() {
/* 316 */       return this.capacity;
/*     */     }
/*     */     
/*     */     private void capacity_$eq(int x$1) {
/* 316 */       this.capacity = x$1;
/*     */     }
/*     */     
/* 317 */     private int size = 0;
/*     */     
/*     */     private int size() {
/* 317 */       return this.size;
/*     */     }
/*     */     
/*     */     private void size_$eq(int x$1) {
/* 317 */       this.size = x$1;
/*     */     }
/*     */     
/*     */     private int[] mkArray(int size) {
/* 320 */       int[] newelems = new int[size];
/* 321 */       if (size() > 0)
/* 321 */         Array$.MODULE$.copy(elems(), 0, newelems, 0, size()); 
/* 322 */       return newelems;
/*     */     }
/*     */     
/*     */     private void resize(int size) {
/* 326 */       elems_$eq(mkArray(size));
/* 327 */       capacity_$eq(size);
/*     */     }
/*     */     
/*     */     public void sizeHint(int size) {
/* 331 */       if (capacity() < size)
/* 331 */         resize(size); 
/*     */     }
/*     */     
/*     */     private void ensureSize(int size) {
/* 335 */       if (capacity() < size || capacity() == 0) {
/* 336 */         int newsize = (capacity() == 0) ? 16 : (capacity() * 2);
/* 337 */         for (; newsize < size; newsize *= 2);
/* 338 */         resize(newsize);
/*     */       } 
/*     */     }
/*     */     
/*     */     public ofInt $plus$eq(int elem) {
/* 343 */       ensureSize(size() + 1);
/* 344 */       elems()[size()] = elem;
/* 345 */       size_$eq(size() + 1);
/* 346 */       return this;
/*     */     }
/*     */     
/*     */     public ofInt $plus$plus$eq(TraversableOnce xs) {
/*     */       ofInt ofInt1;
/* 349 */       if (xs instanceof WrappedArray.ofInt) {
/* 349 */         WrappedArray.ofInt ofInt2 = (WrappedArray.ofInt)xs;
/* 351 */         ensureSize(size() + ofInt2.length());
/* 352 */         Array$.MODULE$.copy(ofInt2.array(), 0, elems(), size(), ofInt2.length());
/* 353 */         size_$eq(size() + ofInt2.length());
/* 354 */         ofInt1 = this;
/*     */       } else {
/* 356 */         ofInt1 = (ofInt)Growable.class.$plus$plus$eq(this, xs);
/*     */       } 
/*     */       return ofInt1;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 360 */       size_$eq(0);
/*     */     }
/*     */     
/*     */     public int[] result() {
/* 364 */       return (capacity() != 0 && capacity() == size()) ? elems() : 
/* 365 */         mkArray(size());
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/*     */       boolean bool;
/* 368 */       if (other instanceof ofInt) {
/* 368 */         ofInt ofInt1 = (ofInt)other;
/* 368 */         bool = (size() == ofInt1.size() && elems() == ofInt1.elems()) ? true : false;
/*     */       } else {
/* 370 */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 373 */       return "ArrayBuilder.ofInt";
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofLong extends ArrayBuilder<Object> {
/*     */     private long[] elems;
/*     */     
/*     */     private long[] elems() {
/* 379 */       return this.elems;
/*     */     }
/*     */     
/*     */     private void elems_$eq(long[] x$1) {
/* 379 */       this.elems = x$1;
/*     */     }
/*     */     
/* 380 */     private int capacity = 0;
/*     */     
/*     */     private int capacity() {
/* 380 */       return this.capacity;
/*     */     }
/*     */     
/*     */     private void capacity_$eq(int x$1) {
/* 380 */       this.capacity = x$1;
/*     */     }
/*     */     
/* 381 */     private int size = 0;
/*     */     
/*     */     private int size() {
/* 381 */       return this.size;
/*     */     }
/*     */     
/*     */     private void size_$eq(int x$1) {
/* 381 */       this.size = x$1;
/*     */     }
/*     */     
/*     */     private long[] mkArray(int size) {
/* 384 */       long[] newelems = new long[size];
/* 385 */       if (size() > 0)
/* 385 */         Array$.MODULE$.copy(elems(), 0, newelems, 0, size()); 
/* 386 */       return newelems;
/*     */     }
/*     */     
/*     */     private void resize(int size) {
/* 390 */       elems_$eq(mkArray(size));
/* 391 */       capacity_$eq(size);
/*     */     }
/*     */     
/*     */     public void sizeHint(int size) {
/* 395 */       if (capacity() < size)
/* 395 */         resize(size); 
/*     */     }
/*     */     
/*     */     private void ensureSize(int size) {
/* 399 */       if (capacity() < size || capacity() == 0) {
/* 400 */         int newsize = (capacity() == 0) ? 16 : (capacity() * 2);
/* 401 */         for (; newsize < size; newsize *= 2);
/* 402 */         resize(newsize);
/*     */       } 
/*     */     }
/*     */     
/*     */     public ofLong $plus$eq(long elem) {
/* 407 */       ensureSize(size() + 1);
/* 408 */       elems()[size()] = elem;
/* 409 */       size_$eq(size() + 1);
/* 410 */       return this;
/*     */     }
/*     */     
/*     */     public ofLong $plus$plus$eq(TraversableOnce xs) {
/*     */       ofLong ofLong1;
/* 413 */       if (xs instanceof WrappedArray.ofLong) {
/* 413 */         WrappedArray.ofLong ofLong2 = (WrappedArray.ofLong)xs;
/* 415 */         ensureSize(size() + ofLong2.length());
/* 416 */         Array$.MODULE$.copy(ofLong2.array(), 0, elems(), size(), ofLong2.length());
/* 417 */         size_$eq(size() + ofLong2.length());
/* 418 */         ofLong1 = this;
/*     */       } else {
/* 420 */         ofLong1 = (ofLong)Growable.class.$plus$plus$eq(this, xs);
/*     */       } 
/*     */       return ofLong1;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 424 */       size_$eq(0);
/*     */     }
/*     */     
/*     */     public long[] result() {
/* 428 */       return (capacity() != 0 && capacity() == size()) ? elems() : 
/* 429 */         mkArray(size());
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/*     */       boolean bool;
/* 432 */       if (other instanceof ofLong) {
/* 432 */         ofLong ofLong1 = (ofLong)other;
/* 432 */         bool = (size() == ofLong1.size() && elems() == ofLong1.elems()) ? true : false;
/*     */       } else {
/* 434 */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 437 */       return "ArrayBuilder.ofLong";
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofFloat extends ArrayBuilder<Object> {
/*     */     private float[] elems;
/*     */     
/*     */     private float[] elems() {
/* 443 */       return this.elems;
/*     */     }
/*     */     
/*     */     private void elems_$eq(float[] x$1) {
/* 443 */       this.elems = x$1;
/*     */     }
/*     */     
/* 444 */     private int capacity = 0;
/*     */     
/*     */     private int capacity() {
/* 444 */       return this.capacity;
/*     */     }
/*     */     
/*     */     private void capacity_$eq(int x$1) {
/* 444 */       this.capacity = x$1;
/*     */     }
/*     */     
/* 445 */     private int size = 0;
/*     */     
/*     */     private int size() {
/* 445 */       return this.size;
/*     */     }
/*     */     
/*     */     private void size_$eq(int x$1) {
/* 445 */       this.size = x$1;
/*     */     }
/*     */     
/*     */     private float[] mkArray(int size) {
/* 448 */       float[] newelems = new float[size];
/* 449 */       if (size() > 0)
/* 449 */         Array$.MODULE$.copy(elems(), 0, newelems, 0, size()); 
/* 450 */       return newelems;
/*     */     }
/*     */     
/*     */     private void resize(int size) {
/* 454 */       elems_$eq(mkArray(size));
/* 455 */       capacity_$eq(size);
/*     */     }
/*     */     
/*     */     public void sizeHint(int size) {
/* 459 */       if (capacity() < size)
/* 459 */         resize(size); 
/*     */     }
/*     */     
/*     */     private void ensureSize(int size) {
/* 463 */       if (capacity() < size || capacity() == 0) {
/* 464 */         int newsize = (capacity() == 0) ? 16 : (capacity() * 2);
/* 465 */         for (; newsize < size; newsize *= 2);
/* 466 */         resize(newsize);
/*     */       } 
/*     */     }
/*     */     
/*     */     public ofFloat $plus$eq(float elem) {
/* 471 */       ensureSize(size() + 1);
/* 472 */       elems()[size()] = elem;
/* 473 */       size_$eq(size() + 1);
/* 474 */       return this;
/*     */     }
/*     */     
/*     */     public ofFloat $plus$plus$eq(TraversableOnce xs) {
/*     */       ofFloat ofFloat1;
/* 477 */       if (xs instanceof WrappedArray.ofFloat) {
/* 477 */         WrappedArray.ofFloat ofFloat2 = (WrappedArray.ofFloat)xs;
/* 479 */         ensureSize(size() + ofFloat2.length());
/* 480 */         Array$.MODULE$.copy(ofFloat2.array(), 0, elems(), size(), ofFloat2.length());
/* 481 */         size_$eq(size() + ofFloat2.length());
/* 482 */         ofFloat1 = this;
/*     */       } else {
/* 484 */         ofFloat1 = (ofFloat)Growable.class.$plus$plus$eq(this, xs);
/*     */       } 
/*     */       return ofFloat1;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 488 */       size_$eq(0);
/*     */     }
/*     */     
/*     */     public float[] result() {
/* 492 */       return (capacity() != 0 && capacity() == size()) ? elems() : 
/* 493 */         mkArray(size());
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/*     */       boolean bool;
/* 496 */       if (other instanceof ofFloat) {
/* 496 */         ofFloat ofFloat1 = (ofFloat)other;
/* 496 */         bool = (size() == ofFloat1.size() && elems() == ofFloat1.elems()) ? true : false;
/*     */       } else {
/* 498 */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 501 */       return "ArrayBuilder.ofFloat";
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofDouble extends ArrayBuilder<Object> {
/*     */     private double[] elems;
/*     */     
/*     */     private double[] elems() {
/* 507 */       return this.elems;
/*     */     }
/*     */     
/*     */     private void elems_$eq(double[] x$1) {
/* 507 */       this.elems = x$1;
/*     */     }
/*     */     
/* 508 */     private int capacity = 0;
/*     */     
/*     */     private int capacity() {
/* 508 */       return this.capacity;
/*     */     }
/*     */     
/*     */     private void capacity_$eq(int x$1) {
/* 508 */       this.capacity = x$1;
/*     */     }
/*     */     
/* 509 */     private int size = 0;
/*     */     
/*     */     private int size() {
/* 509 */       return this.size;
/*     */     }
/*     */     
/*     */     private void size_$eq(int x$1) {
/* 509 */       this.size = x$1;
/*     */     }
/*     */     
/*     */     private double[] mkArray(int size) {
/* 512 */       double[] newelems = new double[size];
/* 513 */       if (size() > 0)
/* 513 */         Array$.MODULE$.copy(elems(), 0, newelems, 0, size()); 
/* 514 */       return newelems;
/*     */     }
/*     */     
/*     */     private void resize(int size) {
/* 518 */       elems_$eq(mkArray(size));
/* 519 */       capacity_$eq(size);
/*     */     }
/*     */     
/*     */     public void sizeHint(int size) {
/* 523 */       if (capacity() < size)
/* 523 */         resize(size); 
/*     */     }
/*     */     
/*     */     private void ensureSize(int size) {
/* 527 */       if (capacity() < size || capacity() == 0) {
/* 528 */         int newsize = (capacity() == 0) ? 16 : (capacity() * 2);
/* 529 */         for (; newsize < size; newsize *= 2);
/* 530 */         resize(newsize);
/*     */       } 
/*     */     }
/*     */     
/*     */     public ofDouble $plus$eq(double elem) {
/* 535 */       ensureSize(size() + 1);
/* 536 */       elems()[size()] = elem;
/* 537 */       size_$eq(size() + 1);
/* 538 */       return this;
/*     */     }
/*     */     
/*     */     public ofDouble $plus$plus$eq(TraversableOnce xs) {
/*     */       ofDouble ofDouble1;
/* 541 */       if (xs instanceof WrappedArray.ofDouble) {
/* 541 */         WrappedArray.ofDouble ofDouble2 = (WrappedArray.ofDouble)xs;
/* 543 */         ensureSize(size() + ofDouble2.length());
/* 544 */         Array$.MODULE$.copy(ofDouble2.array(), 0, elems(), size(), ofDouble2.length());
/* 545 */         size_$eq(size() + ofDouble2.length());
/* 546 */         ofDouble1 = this;
/*     */       } else {
/* 548 */         ofDouble1 = (ofDouble)Growable.class.$plus$plus$eq(this, xs);
/*     */       } 
/*     */       return ofDouble1;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 552 */       size_$eq(0);
/*     */     }
/*     */     
/*     */     public double[] result() {
/* 556 */       return (capacity() != 0 && capacity() == size()) ? elems() : 
/* 557 */         mkArray(size());
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/*     */       boolean bool;
/* 560 */       if (other instanceof ofDouble) {
/* 560 */         ofDouble ofDouble1 = (ofDouble)other;
/* 560 */         bool = (size() == ofDouble1.size() && elems() == ofDouble1.elems()) ? true : false;
/*     */       } else {
/* 562 */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 565 */       return "ArrayBuilder.ofDouble";
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofBoolean extends ArrayBuilder<Object> {
/*     */     private boolean[] elems;
/*     */     
/*     */     private boolean[] elems() {
/* 571 */       return this.elems;
/*     */     }
/*     */     
/*     */     private void elems_$eq(boolean[] x$1) {
/* 571 */       this.elems = x$1;
/*     */     }
/*     */     
/* 572 */     private int capacity = 0;
/*     */     
/*     */     private int capacity() {
/* 572 */       return this.capacity;
/*     */     }
/*     */     
/*     */     private void capacity_$eq(int x$1) {
/* 572 */       this.capacity = x$1;
/*     */     }
/*     */     
/* 573 */     private int size = 0;
/*     */     
/*     */     private int size() {
/* 573 */       return this.size;
/*     */     }
/*     */     
/*     */     private void size_$eq(int x$1) {
/* 573 */       this.size = x$1;
/*     */     }
/*     */     
/*     */     private boolean[] mkArray(int size) {
/* 576 */       boolean[] newelems = new boolean[size];
/* 577 */       if (size() > 0)
/* 577 */         Array$.MODULE$.copy(elems(), 0, newelems, 0, size()); 
/* 578 */       return newelems;
/*     */     }
/*     */     
/*     */     private void resize(int size) {
/* 582 */       elems_$eq(mkArray(size));
/* 583 */       capacity_$eq(size);
/*     */     }
/*     */     
/*     */     public void sizeHint(int size) {
/* 587 */       if (capacity() < size)
/* 587 */         resize(size); 
/*     */     }
/*     */     
/*     */     private void ensureSize(int size) {
/* 591 */       if (capacity() < size || capacity() == 0) {
/* 592 */         int newsize = (capacity() == 0) ? 16 : (capacity() * 2);
/* 593 */         for (; newsize < size; newsize *= 2);
/* 594 */         resize(newsize);
/*     */       } 
/*     */     }
/*     */     
/*     */     public ofBoolean $plus$eq(boolean elem) {
/* 599 */       ensureSize(size() + 1);
/* 600 */       elems()[size()] = elem;
/* 601 */       size_$eq(size() + 1);
/* 602 */       return this;
/*     */     }
/*     */     
/*     */     public ofBoolean $plus$plus$eq(TraversableOnce xs) {
/*     */       ofBoolean ofBoolean1;
/* 605 */       if (xs instanceof WrappedArray.ofBoolean) {
/* 605 */         WrappedArray.ofBoolean ofBoolean2 = (WrappedArray.ofBoolean)xs;
/* 607 */         ensureSize(size() + ofBoolean2.length());
/* 608 */         Array$.MODULE$.copy(ofBoolean2.array(), 0, elems(), size(), ofBoolean2.length());
/* 609 */         size_$eq(size() + ofBoolean2.length());
/* 610 */         ofBoolean1 = this;
/*     */       } else {
/* 612 */         ofBoolean1 = (ofBoolean)Growable.class.$plus$plus$eq(this, xs);
/*     */       } 
/*     */       return ofBoolean1;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 616 */       size_$eq(0);
/*     */     }
/*     */     
/*     */     public boolean[] result() {
/* 620 */       return (capacity() != 0 && capacity() == size()) ? elems() : 
/* 621 */         mkArray(size());
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/*     */       boolean bool;
/* 624 */       if (other instanceof ofBoolean) {
/* 624 */         ofBoolean ofBoolean1 = (ofBoolean)other;
/* 624 */         bool = (size() == ofBoolean1.size() && elems() == ofBoolean1.elems()) ? true : false;
/*     */       } else {
/* 626 */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 629 */       return "ArrayBuilder.ofBoolean";
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ofUnit extends ArrayBuilder<BoxedUnit> {
/*     */     private BoxedUnit[] elems;
/*     */     
/*     */     private BoxedUnit[] elems() {
/* 635 */       return this.elems;
/*     */     }
/*     */     
/*     */     private void elems_$eq(BoxedUnit[] x$1) {
/* 635 */       this.elems = x$1;
/*     */     }
/*     */     
/* 636 */     private int capacity = 0;
/*     */     
/*     */     private int capacity() {
/* 636 */       return this.capacity;
/*     */     }
/*     */     
/*     */     private void capacity_$eq(int x$1) {
/* 636 */       this.capacity = x$1;
/*     */     }
/*     */     
/* 637 */     private int size = 0;
/*     */     
/*     */     private int size() {
/* 637 */       return this.size;
/*     */     }
/*     */     
/*     */     private void size_$eq(int x$1) {
/* 637 */       this.size = x$1;
/*     */     }
/*     */     
/*     */     private BoxedUnit[] mkArray(int size) {
/* 640 */       BoxedUnit[] newelems = new BoxedUnit[size];
/* 641 */       if (size() > 0)
/* 641 */         Array$.MODULE$.copy(elems(), 0, newelems, 0, size()); 
/* 642 */       return newelems;
/*     */     }
/*     */     
/*     */     private void resize(int size) {
/* 646 */       elems_$eq(mkArray(size));
/* 647 */       capacity_$eq(size);
/*     */     }
/*     */     
/*     */     public void sizeHint(int size) {
/* 651 */       if (capacity() < size)
/* 651 */         resize(size); 
/*     */     }
/*     */     
/*     */     private void ensureSize(int size) {
/* 655 */       if (capacity() < size || capacity() == 0) {
/* 656 */         int newsize = (capacity() == 0) ? 16 : (capacity() * 2);
/* 657 */         for (; newsize < size; newsize *= 2);
/* 658 */         resize(newsize);
/*     */       } 
/*     */     }
/*     */     
/*     */     public ofUnit $plus$eq(BoxedUnit elem) {
/* 663 */       ensureSize(size() + 1);
/* 664 */       elems()[size()] = elem;
/* 665 */       size_$eq(size() + 1);
/* 666 */       return this;
/*     */     }
/*     */     
/*     */     public ofUnit $plus$plus$eq(TraversableOnce xs) {
/*     */       ofUnit ofUnit1;
/* 669 */       if (xs instanceof WrappedArray.ofUnit) {
/* 669 */         WrappedArray.ofUnit ofUnit2 = (WrappedArray.ofUnit)xs;
/* 671 */         ensureSize(size() + ofUnit2.length());
/* 672 */         Array$.MODULE$.copy(ofUnit2.array(), 0, elems(), size(), ofUnit2.length());
/* 673 */         size_$eq(size() + ofUnit2.length());
/* 674 */         ofUnit1 = this;
/*     */       } else {
/* 676 */         ofUnit1 = (ofUnit)Growable.class.$plus$plus$eq(this, xs);
/*     */       } 
/*     */       return ofUnit1;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 680 */       size_$eq(0);
/*     */     }
/*     */     
/*     */     public BoxedUnit[] result() {
/* 684 */       return (capacity() != 0 && capacity() == size()) ? elems() : 
/* 685 */         mkArray(size());
/*     */     }
/*     */     
/*     */     public boolean equals(Object other) {
/*     */       boolean bool;
/* 688 */       if (other instanceof ofUnit) {
/* 688 */         ofUnit ofUnit1 = (ofUnit)other;
/* 688 */         bool = (size() == ofUnit1.size() && elems() == ofUnit1.elems()) ? true : false;
/*     */       } else {
/* 690 */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 693 */       return "ArrayBuilder.ofUnit";
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ArrayBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */