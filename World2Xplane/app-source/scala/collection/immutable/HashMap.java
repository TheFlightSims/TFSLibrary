/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.CustomParallelizable;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.Parallel;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.Subtractable;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.immutable.ParHashMap;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ObjectRef;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\025\025f\001B\001\003\001%\021q\001S1tQ6\013\007O\003\002\004\t\005I\021.\\7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\004\025Ea2C\002\001\f=\005*\003\006\005\003\r\033=YR\"\001\002\n\0059\021!aC!cgR\024\030m\031;NCB\004\"\001E\t\r\001\021)!\003\001b\001'\t\t\021)\005\002\0251A\021QCF\007\002\r%\021qC\002\002\b\035>$\b.\0338h!\t)\022$\003\002\033\r\t\031\021I\\=\021\005AaBAB\017\001\t\013\0071CA\001C!\021aqdD\016\n\005\001\022!aA'baB)ABI\b\034I%\0211E\001\002\b\033\006\004H*[6f!\021a\001aD\016\021\005U1\023BA\024\007\0051\031VM]5bY&T\030M\0317f!\021I#\006L\030\016\003\021I!a\013\003\003)\r+8\017^8n!\006\024\030\r\0347fY&T\030M\0317f!\021)RfD\016\n\00592!A\002+va2,'\007\005\0031i=YR\"A\031\013\005\r\021$BA\032\005\003!\001\030M]1mY\026d\027BA\0332\005)\001\026M\035%bg\"l\025\r\035\005\006o\001!\t\001O\001\007y%t\027\016\036 \025\003\021BQA\017\001\005Bm\nAa]5{KV\tA\b\005\002\026{%\021aH\002\002\004\023:$\b\"\002!\001\t\003\n\025!B3naRLX#\001\023\t\013\r\003A\021\001#\002\021%$XM]1u_J,\022!\022\t\004S\031c\023BA$\005\005!IE/\032:bi>\024\b\"B%\001\t\003R\025a\0024pe\026\f7\r[\013\003\027V#\"\001T(\021\005Ui\025B\001(\007\005\021)f.\033;\t\013AC\005\031A)\002\003\031\004B!\006*-)&\0211K\002\002\n\rVt7\r^5p]F\002\"\001E+\005\013YC%\031A\n\003\003UCQ\001\027\001\005\002e\0131aZ3u)\tQV\fE\002\0267nI!\001\030\004\003\r=\003H/[8o\021\025qv\0131\001\020\003\rYW-\037\005\006A\002!\t%Y\001\bkB$\027\r^3e+\t\021W\rF\002dQ&\004B\001\004\001\020IB\021\001#\032\003\006M~\023\ra\032\002\003\005F\n\"a\007\r\t\013y{\006\031A\b\t\013)|\006\031\0013\002\013Y\fG.^3\t\0131\004A\021I7\002\013\021\002H.^:\026\0059\fHCA8s!\021a\001a\0049\021\005A\tH!\0024l\005\0049\007\"B:l\001\004!\030AA6w!\021)Rf\0049\t\0131\004A\021\t<\026\005]TH#\002=|}\006\005\001\003\002\007\001\037e\004\"\001\005>\005\013\031,(\031A4\t\013q,\b\031A?\002\013\025dW-\\\031\021\tUis\"\037\005\006V\004\r!`\001\006K2,WN\r\005\b\003\007)\b\031AA\003\003\025)G.Z7t!\021)\022qA?\n\007\005%aA\001\006=e\026\004X-\031;fIzBq!!\004\001\t\003\ty!\001\004%[&tWo\035\013\004I\005E\001B\0020\002\f\001\007q\002C\004\002\026\001!\t\"a\006\002\031\025dW-\034%bg\"\034u\016Z3\025\007q\nI\002\003\004_\003'\001\ra\004\005\b\003;\001AQCA\020\003\035IW\016\035:pm\026$2\001PA\021\021\035\t\031#a\007A\002q\nQ\001[2pI\026D\001\"a\n\001\t\003!\021\021F\001\fG>l\007/\036;f\021\006\034\b\016F\002=\003WAaAXA\023\001\004y\001\002CA\030\001\021\005A!!\r\002\t\035,G\017\r\013\b5\006M\022QGA\035\021\031q\026Q\006a\001\037!9\021qGA\027\001\004a\024\001\0025bg\"Dq!a\017\002.\001\007A(A\003mKZ,G\016\003\005\002@\001!\t\001BA!\003!)\b\017Z1uK\022\004T\003BA\"\003\023\"b\"!\022\002L\0055\023qJA)\003'\n9\006E\003\r\001=\t9\005E\002\021\003\023\"aAZA\037\005\0049\007B\0020\002>\001\007q\002C\004\0028\005u\002\031\001\037\t\017\005m\022Q\ba\001y!9!.!\020A\002\005\035\003bB:\002>\001\007\021Q\013\t\006+5z\021q\t\005\t\0033\ni\0041\001\002\\\0051Q.\032:hKJ\004r!!\030\002\b>\t9ED\002\r\003?:q!!\031\003\021\003\t\031'A\004ICNDW*\0319\021\0071\t)G\002\004\002\005!\005\021qM\n\b\003K\nI'a\036&!\031\tY'!\035\002v5\021\021Q\016\006\004\003_\"\021aB4f]\026\024\030nY\005\005\003g\niGA\nJ[6,H/\0312mK6\013\007OR1di>\024\030\020\005\002\r\001A!\021\021PA@\035\021\tY'a\037\n\t\005u\024QN\001\016\005&$x\n]3sCRLwN\\:\n\007y\n\tI\003\003\002~\0055\004bB\034\002f\021\005\021Q\021\013\003\003G2\021\"!#\002f\005\005A!a#\003\r5+'oZ3s+\031\ti)a(\002$N!\021qQAH!\r)\022\021S\005\004\003'3!AB!osJ+g\rC\0048\003\017#\t!a&\025\005\005e\005\003CAN\003\017\013i*!)\016\005\005\025\004c\001\t\002 \0221!#a\"C\002M\0012\001EAR\t\031i\022q\021b\001'!A\021qUAD\r\003\tI+A\003baBd\027\020\006\004\002,\0065\026\021\027\t\007+5\ni*!)\t\021\005=\026Q\025a\001\003W\0131a\033<2\021!\t\031,!*A\002\005-\026aA6we!A\021qWAD\r\003\tI,\001\004j]Z,'\017^\013\003\0033+q!!0\002f\021\tyLA\007NKJ<WMR;oGRLwN\\\013\007\003\003\fY-!5\021\023U\t\031-a2\002H\006\035\027bAAc\r\tIa)\0368di&|gN\r\t\007+5\nI-a4\021\007A\tY\rB\004\002N\006m&\031A\n\003\005\005\013\004c\001\t\002R\0221a-a/C\002MA\001\"!6\002f\021%\021q[\001\013Y&4G/T3sO\026\024XCBAm\003?\f\031\017\006\003\002\\\006\025\b\003CAN\003\017\013i.!9\021\007A\ty\016B\004\002N\006M'\031A\n\021\007A\t\031\017\002\004g\003'\024\ra\005\005\t\003O\f\031\0161\001\002j\0061Q.\032:hK\032\004\002\"a'\002<\006u\027\021\035\005\n\003[\f)\007)A\005\003_\fQ\002Z3gCVdG/T3sO\026\024\bCBAN\003\017C\002\004C\005\002t\006\025\004\025\"\003\002v\006YA.\0334u\033\026\024x-\032:1+\031\t90!@\003\002Q!\021\021 B\002!!\tY*a\"\002|\006}\bc\001\t\002~\0229\021QZAy\005\004\031\002c\001\t\003\002\0211a-!=C\002MA\001\"a:\002r\002\007!Q\001\t\t\0037\013Y,a?\002\000\"A!\021BA3\t\007\021Y!\001\007dC:\024U/\0337e\rJ|W.\006\004\003\016\t\r\"qE\013\003\005\037\001\"\"a\033\003\022\tU!q\004B\025\023\021\021\031\"!\034\003\031\r\013gNQ;jY\0224%o\\7\021\t\005m%qC\005\005\0053\021YB\001\003D_2d\027\002\002B\017\003[\022QbR3o\033\006\004h)Y2u_JL\bCB\013.\005C\021)\003E\002\021\005G!aA\005B\004\005\004\031\002c\001\t\003(\0211QDa\002C\002M\001b\001\004\001\003\"\t\025\002b\002!\002f\021\005!QF\013\007\005_\021)D!\017\026\005\tE\002C\002\007\001\005g\0219\004E\002\021\005k!aA\005B\026\005\004\031\002c\001\t\003:\0211QDa\013C\002M9\001B!\020\002f!%!qH\001\r\0136\004H/\037%bg\"l\025\r\035\t\005\0037\023\tE\002\005\003D\005\025\004\022\002B#\0051)U\016\035;z\021\006\034\b.T1q'\021\021\tEa\022\021\t1\001\001\004\006\005\bo\t\005C\021\001B&)\t\021y\004\003\006\003P\t\005\023\021!C\005\005#\n1B]3bIJ+7o\0347wKR\021!1\013\t\005\005+\022y&\004\002\003X)!!\021\fB.\003\021a\027M\\4\013\005\tu\023\001\0026bm\006LAA!\031\003X\t1qJ\0316fGRD\001B!\032\002f\021%!qM\001\020[\006\\W\rS1tQR\023\030.Z'baV1!\021NB$\007\027\"bBa\033\004N\rE3qKB.\007;\032y\006\005\005\002\034\n54QIB%\r\035\021y'!\032\001\005c\0221\002S1tQR\023\030.Z'baV1!1\017B=\005\032BA!\034\003vA1A\002\001B<\005w\0022\001\005B=\t\031\021\"Q\016b\001')\"!Q\020BA!\r\001\"q\020\003\b;\t5DQ1\001\024W\t\021\031\t\005\003\003\006\n=UB\001BD\025\021\021IIa#\002\023Ut7\r[3dW\026$'b\001BG\r\005Q\021M\0348pi\006$\030n\0348\n\t\tE%q\021\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007\002\004BK\005[\022)\031!C\001\t\t]\025A\0022ji6\f\007/\006\002\003\032B!\0211\024BN\023\rq\024q\020\005\f\005?\023iG!A!\002\023\021I*A\004cSRl\027\r\035\021\t\031\005\r!Q\016BC\002\023\005AAa)\026\005\t\025\006#B\013\003(\nU\024b\001BU\r\t)\021I\035:bs\"Y!Q\026B7\005\003\005\013\021\002BS\003\031)G.Z7tA!a!\021\027B7\005\013\007I\021\001\003\003\030\006)1/\033>fa!Y!Q\027B7\005\003\005\013\021\002BM\003\031\031\030N_31A!9qG!\034\005\002\teF\003\003B^\005{\023yL!1\021\021\005m%Q\016B<\005{B\001B!&\0038\002\007!\021\024\005\t\003\007\0219\f1\001\003&\"A!\021\027B\\\001\004\021I\nC\004;\005[\"\tEa&\t\021\005=\"Q\016C!\005\017$\002B!3\003L\n5'q\032\t\005+m\023i\bC\004_\005\013\004\rAa\036\t\021\005]\"Q\031a\001\0053C\001\"a\017\003F\002\007!\021\024\005\n\003\021i\007\"\021\005\005',BA!6\003\\Rq!q\033Bp\005C\024\031O!:\003h\n-\bC\002\007\001\005o\022I\016E\002\021\0057$qA\032Bi\005\004\021i.E\002\003~aAqA\030Bi\001\004\0219\b\003\005\0028\tE\007\031\001BM\021!\tYD!5A\002\te\005b\0026\003R\002\007!\021\034\005\bg\nE\007\031\001Bu!\031)RFa\036\003Z\"A\021\021\fBi\001\004\021i\017\005\005\002\034\006\035%q\017Bm\021!\021\tP!\034\005B\tM\030\001\003:f[>4X\r\032\031\025\021\tU(q\037B}\005w\004b\001\004\001\003x\tu\004b\0020\003p\002\007!q\017\005\t\003o\021y\0171\001\003\032\"A\0211\bBx\001\004\021I\nC\004D\005[\"\tEa@\026\005\r\005\001\003B\025G\007\007\001b!F\027\003x\tu\004bB%\003n\021\0053qA\013\005\007\023\031\t\002F\002M\007\027Aq\001UB\003\001\004\031i\001\005\004\026%\016\r1q\002\t\004!\rEAA\002,\004\006\t\0071\003\003\005\004\026\t5D\021BB\f\003\025\001xn](g)\025a4\021DB\017\021!\031Yba\005A\002\te\025!\0018\t\021\r}11\003a\001\0053\013!AY7\t\021\r\r\"Q\016C!\007K\tQa\0359mSR,\"aa\n\021\0131\031IC!>\n\007\r-\"AA\002TKFD\001ba\f\003n\021E3\021G\001\007[\026\024x-\032\031\026\t\rM2\021\b\013\t\007k\031Yda\020\004BA1A\002\001B<\007o\0012\001EB\035\t\03517Q\006b\001\005;D\001b!\020\004.\001\0071QG\001\005i\"\fG\017\003\005\002<\r5\002\031\001BM\021!\tIf!\fA\002\r\r\003\003CAN\003\017\0239ha\016\021\007A\0319\005\002\004\023\005G\022\ra\005\t\004!\r-CAB\017\003d\t\0071\003\003\005\004P\t\r\004\031\001BM\003\025A\027m\03551\021!\031\031Fa\031A\002\rU\023!B3mK6\004\004C\002\007\001\007\013\032I\005\003\005\004Z\t\r\004\031\001BM\003\025A\027m\03552\021\035a(1\ra\001\007+B\001\"a\017\003d\001\007!\021\024\005\bu\t\r\004\031\001BM\r\035\031\031'!\032\001\007K\022\001\002S1tQ6\013\007/M\013\007\007O\032ig!\035\024\t\r\0054\021\016\t\007\031\001\031Yga\034\021\007A\031i\007\002\004\023\007C\022\ra\005\t\004!\rEDaB\017\004b\021\025\ra\005\005\f=\016\005$Q1A\005\002\021\031)(\006\002\004l!Y1\021PB1\005\003\005\013\021BB6\003\021YW-\037\021\t\031\005]2\021\rBC\002\023\005AAa&\t\027\r}4\021\rB\001B\003%!\021T\001\006Q\006\034\b\016\t\005\fU\016\005$Q1A\005\002\021\031\031)\006\002\004\006*\"1q\016BA\021-\031Ii!\031\003\002\003\006Ia!\"\002\rY\fG.^3!\021-\0318\021\rBA\002\023\005Aa!$\026\005\r=\005CB\013.\007W\032)\t\003\007\004\024\016\005$\0211A\005\002\021\031)*\001\004lm~#S-\035\013\004\031\016]\005BCBM\007#\013\t\0211\001\004\020\006\031\001\020J\031\t\027\ru5\021\rB\001B\003&1qR\001\004WZ\004\003bB\034\004b\021\0051\021\025\013\013\007G\033)ka*\004*\016-\006\003CAN\007C\032Yga\034\t\017y\033y\n1\001\004l!A\021qGBP\001\004\021I\nC\004k\007?\003\ra!\"\t\017M\034y\n1\001\004\020\"1!h!\031\005BmB\021b!-\004b\021\005Aa!\036\002\r\035,GoS3z\021%\031)l!\031\005\002\021\0219*A\004hKRD\025m\0355\t\023\re6\021\rC\001\t\rm\026AD2p[B,H/\032%bg\"4uN\035\013\004y\ru\006\002CB`\007o\003\raa\033\002\003-D\001\"a\f\004b\021\00531\031\013\t\007\013\0349m!3\004LB!QcWB8\021\035q6\021\031a\001\007WB\001\"a\016\004B\002\007!\021\024\005\t\003w\031\t\r1\001\003\032\"I\021qHB1\t\003\"1qZ\013\005\007#\0349\016\006\b\004T\016m7Q\\Bp\007C\034\031oa:\021\r1\00111NBk!\r\0012q\033\003\bM\0165'\031ABm#\r\031y\007\007\005\b=\0165\007\031AB6\021!\t9d!4A\002\te\005\002CA\036\007\033\004\rA!'\t\017)\034i\r1\001\004V\"91o!4A\002\r\025\bCB\013.\007W\032)\016\003\005\002Z\r5\007\031ABu!!\tY*a\"\004l\rU\007\002\003By\007C\"\te!<\025\021\r%4q^By\007gDqAXBv\001\004\031Y\007\003\005\0028\r-\b\031\001BM\021!\tYda;A\002\te\005bB\"\004b\021\0053q_\013\003\007s\004B!\013$\004|B1Q#LB6\007_Bq!SB1\t\003\032y0\006\003\005\002\021%Ac\001'\005\004!9\001k!@A\002\021\025\001CB\013S\007w$9\001E\002\021\t\023!aAVB\005\004\031\002B\003C\007\007C\"\t!!\032\005\020\005QQM\\:ve\026\004\026-\033:\026\005\rm\b\002CB\030\007C\"\t\006b\005\026\t\021UA1\004\013\t\t/!i\002b\b\005\"A1A\002AB6\t3\0012\001\005C\016\t\0351G\021\003b\001\0073D\001b!\020\005\022\001\007Aq\003\005\t\003w!\t\0021\001\003\032\"A\021\021\fC\t\001\004!\031\003\005\005\002\034\006\03551\016C\r\r!!9#!\032\001\t\021%\"!\005%bg\"l\025\r]\"pY2L7/[8ocU1A1\006C\031\to\031B\001\"\n\005.A1A\002\001C\030\tg\0012\001\005C\031\t\031\021BQ\005b\001')\"AQ\007BA!\r\001Bq\007\003\b;\021\025BQ1\001\024\0211\t9\004\"\n\003\006\004%\t\001\002BL\021-\031y\b\"\n\003\002\003\006IA!'\t\027\021}BQ\005BC\002\023\005A\021I\001\004WZ\034XC\001C\"!\035aAQ\tC\030\tgI1\001b\022\003\005\035a\025n\035;NCBD1\002b\023\005&\t\005\t\025!\003\005D\005!1N^:!\021\0359DQ\005C\001\t\037\"b\001\"\025\005T\021U\003\003CAN\tK!y\003\"\016\t\021\005]BQ\na\001\0053C\001\002b\020\005N\001\007A1\t\005\007u\021\025B\021I\036\t\021\005=BQ\005C!\t7\"\002\002\"\030\005`\021\005D1\r\t\005+m#)\004C\004_\t3\002\r\001b\f\t\021\005]B\021\fa\001\0053C\001\"a\017\005Z\001\007!\021\024\005\n\003!)\003\"\021\005\tO*B\001\"\033\005pQqA1\016C:\tk\"9\b\"\037\005|\021}\004C\002\007\001\t_!i\007E\002\021\t_\"qA\032C3\005\004!\t(E\002\0056aAqA\030C3\001\004!y\003\003\005\0028\021\025\004\031\001BM\021!\tY\004\"\032A\002\te\005b\0026\005f\001\007AQ\016\005\bg\022\025\004\031\001C?!\031)R\006b\f\005n!A\021\021\fC3\001\004!\t\t\005\005\002\034\006\035Eq\006C7\021!\021\t\020\"\n\005B\021\025E\003\003CD\t\023#Y\t\"$\021\r1\001Aq\006C\033\021\035qF1\021a\001\t_A\001\"a\016\005\004\002\007!\021\024\005\t\003w!\031\t1\001\003\032\"91\t\"\n\005B\021EUC\001CJ!\021Ic\t\"&\021\rUiCq\006C\033\021\035IEQ\005C!\t3+B\001b'\005$R\031A\n\"(\t\017A#9\n1\001\005 B1QC\025CK\tC\0032\001\005CR\t\0311Fq\023b\001'!A11\005C\023\t\003\"9+\006\002\005*B)Ab!\013\005\b\"A1q\006C\023\t#\"i+\006\003\0050\022UF\003\003CY\to#I\fb/\021\r1\001Aq\006CZ!\r\001BQ\027\003\bM\022-&\031\001C9\021!\031i\004b+A\002\021E\006\002CA\036\tW\003\rA!'\t\021\005eC1\026a\001\t{\003\002\"a'\002\b\022=B1\027\004\b\t\003\f)\007\002Cb\005I\031VM]5bY&T\030\r^5p]B\023x\016_=\026\r\021\025G\021\033Ck'\025!y,a$&\021-!I\rb0\003\002\004%I\001b3\002\t=\024\030nZ\013\003\t\033\004b\001\004\001\005P\022M\007c\001\t\005R\0221!\003b0C\002M\0012\001\005Ck\t\031iBq\030b\001'!YA\021\034C`\005\003\007I\021\002Cn\003!y'/[4`I\025\fHc\001'\005^\"Q1\021\024Cl\003\003\005\r\001\"4\t\027\021\005Hq\030B\001B\003&AQZ\001\006_JLw\r\t\025\005\t?$)\017E\002\026\tOL1\001\";\007\005%!(/\0318tS\026tG\017C\0048\t#\t\001\"<\025\t\021=H\021\037\t\t\0037#y\fb4\005T\"AA\021\032Cv\001\004!i\r\003\005\005v\022}F\021\002C|\003-9(/\033;f\037\nTWm\031;\025\0071#I\020\003\005\005|\022M\b\031\001C\003\ryW\017\036\t\005\t,)!\004\002\006\002)!Q1\001B.\003\tIw.\003\003\006\b\025\005!AE(cU\026\034GoT;uaV$8\013\036:fC6D\001\"b\003\005@\022%QQB\001\013e\026\fGm\0242kK\016$Hc\001'\006\020!AQ\021CC\005\001\004)\031\"\001\002j]B!Aq`C\013\023\021)9\"\"\001\003#=\023'.Z2u\023:\004X\017^*ue\026\fW\016\003\005\003P\021}F\021BC\016)\t\ty\t\013\004\005@\026}QQ\005\t\004+\025\005\022bAC\022\r\t\0012+\032:jC24VM]:j_:,\026\n\022\020\002\005!Q!qJA3\003\003%IA!\025\t\017\tE\b\001\"\005\006,Q9A%\"\f\0060\025E\002B\0020\006*\001\007q\002C\004\0028\025%\002\031\001\037\t\017\005mR\021\006a\001y!9QQ\007\001\005\022\025m\021\001D<sSR,'+\0329mC\016,\007bBB\022\001\021\005Q\021H\013\003\013w\001B\001DB\025I!9Qq\b\001\005\002\025\005\023!B7fe\036,W\003BC\"\013\023\"b!\"\022\006L\0255\003#\002\007\001\037\025\035\003c\001\t\006J\0211a-\"\020C\002\035D\001b!\020\006>\001\007QQ\t\005\013\003O,i\004%AA\002\025=\003cBA/\003w{Qq\t\025\t\013{)\031&\"\027\006^A\031Q#\"\026\n\007\025]cA\001\006eKB\024XmY1uK\022\f#!b\027\002AU\033X\r\t;iK\002\002W.\032:hK\022\004\007%\\3uQ>$\007%\0338ti\026\fGML\021\003\013?\naA\r\0302a9\002\004bBC2\001\021\005QQM\001\007[\026\024x-\0323\026\t\025\035Tq\016\013\005\013S*)\b\006\003\006l\025E\004#\002\007\001\037\0255\004c\001\t\006p\0211a-\"\031C\002\035D\001\"a:\006b\001\007Q1\017\t\b\003;\nYlDC7\021!\031i$\"\031A\002\025-\004bBB\030\001\021EQ\021P\013\005\013w*\t\t\006\005\006~\025\rUQQCD!\025a\001aDC@!\r\001R\021\021\003\007M\026]$\031A4\t\021\ruRq\017a\001\013{Bq!a\017\006x\001\007A\b\003\005\002Z\025]\004\031ACE!\035\ti&a\"\020\013Bq!\"$\001\t\003*y)A\002qCJ,\022a\f\005\n\013'\003\021\023!C\001\013+\013q\"\\3sO\026$C-\0324bk2$HEM\013\005\013/+\t+\006\002\006\032*\"Q1\024BA!\r)RQT\005\004\013?3!\001\002(vY2$aAZCI\005\0049\007&\002\001\006 \025\025\002")
/*     */ public class HashMap<A, B> extends AbstractMap<A, B> implements Map<A, B>, MapLike<A, B, HashMap<A, B>>, Serializable, CustomParallelizable<Tuple2<A, B>, ParHashMap<A, B>> {
/*     */   public static final long serialVersionUID = 2L;
/*     */   
/*     */   public Combiner<Tuple2<A, B>, ParHashMap<A, B>> parCombiner() {
/*  36 */     return CustomParallelizable.class.parCombiner(this);
/*     */   }
/*     */   
/*     */   public HashMap() {
/*  36 */     CustomParallelizable.class.$init$(this);
/*     */   }
/*     */   
/*     */   public int size() {
/*  42 */     return 0;
/*     */   }
/*     */   
/*     */   public HashMap<A, B> empty() {
/*  44 */     return HashMap$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public Iterator<Tuple2<A, B>> iterator() {
/*  46 */     return scala.collection.Iterator$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {}
/*     */   
/*     */   public Option<B> get(Object key) {
/*  51 */     return get0((A)key, computeHash((A)key), 0);
/*     */   }
/*     */   
/*     */   public <B1> HashMap<A, B1> updated(Object key, Object value) {
/*  54 */     return updated0((A)key, computeHash((A)key), 0, (B1)value, (Tuple2<A, B1>)null, (Merger<A, B1>)null);
/*     */   }
/*     */   
/*     */   public <B1> HashMap<A, B1> $plus(Tuple2<A, B1> kv) {
/*  57 */     return updated0((A)kv._1(), computeHash((A)kv._1()), 0, (B1)kv._2(), kv, (Merger<A, B1>)null);
/*     */   }
/*     */   
/*     */   public <B1> HashMap<A, B1> $plus(Tuple2<?, ?> elem1, Tuple2 elem2, Seq elems) {
/*  60 */     return (HashMap<A, B1>)$plus(elem1).$plus(elem2).$plus$plus((GenTraversableOnce)elems, HashMap$.MODULE$.canBuildFrom());
/*     */   }
/*     */   
/*     */   public HashMap<A, B> $minus(Object key) {
/*  64 */     return removed0((A)key, computeHash((A)key), 0);
/*     */   }
/*     */   
/*     */   public int elemHashCode(Object key) {
/*  66 */     return scala.runtime.ScalaRunTime$.MODULE$.hash(key);
/*     */   }
/*     */   
/*     */   public final int improve(int hcode) {
/*  69 */     int h = hcode + (hcode << 9 ^ 0xFFFFFFFF);
/*  70 */     h ^= h >>> 14;
/*  71 */     h += h << 4;
/*  72 */     return h ^ h >>> 10;
/*     */   }
/*     */   
/*     */   public int computeHash(Object key) {
/*  75 */     return improve(elemHashCode((A)key));
/*     */   }
/*     */   
/*     */   public Option<B> get0(Object key, int hash, int level) {
/*  79 */     return (Option<B>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public <B1> HashMap<A, B1> updated0(Object key, int hash, int level, Object value, Tuple2<A, B1> kv, Merger merger) {
/*  82 */     return new HashMap1<A, B1>((A)key, hash, (B1)value, kv);
/*     */   }
/*     */   
/*     */   public HashMap<A, B> removed0(Object key, int hash, int level) {
/*  84 */     return this;
/*     */   }
/*     */   
/*     */   public Object writeReplace() {
/*  86 */     return new SerializationProxy<Object, Object>(this);
/*     */   }
/*     */   
/*     */   public Seq<HashMap<A, B>> split() {
/*  88 */     (new HashMap[1])[0] = this;
/*  88 */     return (Seq<HashMap<A, B>>)Seq$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new HashMap[1]));
/*     */   }
/*     */   
/*     */   public <B1> HashMap<A, B1> merge(HashMap<A, B1> that, Function2<Tuple2<A, B1>, Tuple2<A, B1>, Tuple2<A, B1>> mergef) {
/*  91 */     return merge0(that, 0, HashMap$.MODULE$.scala$collection$immutable$HashMap$$liftMerger(mergef));
/*     */   }
/*     */   
/*     */   public <B1> scala.runtime.Null$ merge$default$2() {
/*  91 */     return null;
/*     */   }
/*     */   
/*     */   public <B1> HashMap<A, B1> merged(HashMap<A, B1> that, Function2<Tuple2<A, B1>, Tuple2<A, B1>, Tuple2<A, B1>> mergef) {
/* 106 */     return merge0(that, 0, HashMap$.MODULE$.scala$collection$immutable$HashMap$$liftMerger(mergef));
/*     */   }
/*     */   
/*     */   public <B1> HashMap<A, B1> merge0(HashMap<A, B1> that, int level, Merger merger) {
/* 108 */     return that;
/*     */   }
/*     */   
/*     */   public ParHashMap<A, B> par() {
/* 110 */     return scala.collection.parallel.immutable.ParHashMap$.MODULE$.fromTrie(this);
/*     */   }
/*     */   
/*     */   public static String bitString$default$2() {
/*     */     return HashMap$.MODULE$.bitString$default$2();
/*     */   }
/*     */   
/*     */   public static int highestOneBit(int paramInt) {
/*     */     return HashMap$.MODULE$.highestOneBit(paramInt);
/*     */   }
/*     */   
/*     */   public static String bitString(int paramInt, String paramString) {
/*     */     return HashMap$.MODULE$.bitString(paramInt, paramString);
/*     */   }
/*     */   
/*     */   public static IndexedSeq<Object> bits(int paramInt) {
/*     */     return HashMap$.MODULE$.bits(paramInt);
/*     */   }
/*     */   
/*     */   public static int complement(int paramInt) {
/*     */     return HashMap$.MODULE$.complement(paramInt);
/*     */   }
/*     */   
/*     */   public static boolean shorter(int paramInt1, int paramInt2) {
/*     */     return HashMap$.MODULE$.shorter(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */   public static boolean unsignedCompare(int paramInt1, int paramInt2) {
/*     */     return HashMap$.MODULE$.unsignedCompare(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */   public static boolean hasMatch(int paramInt1, int paramInt2, int paramInt3) {
/*     */     return HashMap$.MODULE$.hasMatch(paramInt1, paramInt2, paramInt3);
/*     */   }
/*     */   
/*     */   public static int mask(int paramInt1, int paramInt2) {
/*     */     return HashMap$.MODULE$.mask(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */   public static boolean zero(int paramInt1, int paramInt2) {
/*     */     return HashMap$.MODULE$.zero(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */   public static <A, B> CanBuildFrom<HashMap<?, ?>, Tuple2<A, B>, HashMap<A, B>> canBuildFrom() {
/*     */     return HashMap$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static abstract class Merger<A, B> {
/*     */     public abstract Tuple2<A, B> apply(Tuple2<A, B> param1Tuple21, Tuple2<A, B> param1Tuple22);
/*     */     
/*     */     public abstract Merger<A, B> invert();
/*     */   }
/*     */   
/*     */   public static class $anonfun$1 extends AbstractFunction2<Tuple2<Object, Object>, Tuple2<Object, Object>, Tuple2<Object, Object>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<Object, Object> apply(Tuple2<Object, Object> a, Tuple2 b) {
/* 133 */       return a;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class HashMap$$anon$2 extends Merger<A1, B1> {
/*     */     public Tuple2<A1, B1> apply(Tuple2 kv1, Tuple2 kv2) {
/* 137 */       return (Tuple2<A1, B1>)this.mergef$1.apply(kv1, kv2);
/*     */     }
/*     */     
/* 138 */     private final HashMap.Merger<A1, B1> invert = new HashMap$$anon$2$$anon$3(this);
/*     */     
/*     */     public final Function2 mergef$1;
/*     */     
/*     */     public HashMap.Merger<A1, B1> invert() {
/* 138 */       return this.invert;
/*     */     }
/*     */     
/*     */     public HashMap$$anon$2(Function2 mergef$1) {}
/*     */     
/*     */     public class HashMap$$anon$2$$anon$3 extends HashMap.Merger<A1, B1> {
/*     */       public HashMap$$anon$2$$anon$3(HashMap$$anon$2 $outer) {}
/*     */       
/*     */       public Tuple2<A1, B1> apply(Tuple2 kv1, Tuple2 kv2) {
/* 139 */         return (Tuple2<A1, B1>)this.$outer.mergef$1.apply(kv2, kv1);
/*     */       }
/*     */       
/*     */       public HashMap.Merger<A1, B1> invert() {
/* 140 */         return this.$outer;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static class EmptyHashMap$ extends HashMap<Object, scala.runtime.Nothing$> {
/*     */     public static final EmptyHashMap$ MODULE$;
/*     */     
/*     */     private Object readResolve() {
/* 148 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public EmptyHashMap$() {
/* 148 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class HashMap1<A, B> extends HashMap<A, B> {
/*     */     private final A key;
/*     */     
/*     */     private final int hash;
/*     */     
/*     */     private final B value;
/*     */     
/*     */     private Tuple2<A, B> kv;
/*     */     
/*     */     public A key() {
/* 175 */       return this.key;
/*     */     }
/*     */     
/*     */     public int hash() {
/* 175 */       return this.hash;
/*     */     }
/*     */     
/*     */     public B value() {
/* 175 */       return this.value;
/*     */     }
/*     */     
/*     */     public Tuple2<A, B> kv() {
/* 175 */       return this.kv;
/*     */     }
/*     */     
/*     */     public void kv_$eq(Tuple2<A, B> x$1) {
/* 175 */       this.kv = x$1;
/*     */     }
/*     */     
/*     */     public HashMap1(Object key, int hash, Object value, Tuple2<A, B> kv) {}
/*     */     
/*     */     public int size() {
/* 176 */       return 1;
/*     */     }
/*     */     
/*     */     public A getKey() {
/* 178 */       return key();
/*     */     }
/*     */     
/*     */     public int getHash() {
/* 179 */       return hash();
/*     */     }
/*     */     
/*     */     public int computeHashFor(Object k) {
/* 180 */       return computeHash((A)k);
/*     */     }
/*     */     
/*     */     public Option<B> get0(Object key, int hash, int level) {
/* 183 */       if (hash == hash()) {
/* 183 */         A a = key();
/* 183 */         if ((key == a) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a) : key.equals(a)))));
/*     */       } 
/* 183 */       return (Option<B>)scala.None$.MODULE$;
/*     */     }
/*     */     
/*     */     public <B1> HashMap<A, B1> updated0(Object key, int hash, int level, Object value, Tuple2<A, B> kv, HashMap.Merger<A, B> merger) {
/* 201 */       if (hash == hash()) {
/* 201 */         A a = key();
/* 201 */         if ((key == a) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a) : key.equals(a))))) {
/* 206 */           Tuple2<A, B> nkv = merger.apply(kv(), kv);
/* 207 */           return (merger == null) ? ((value() == value) ? this : new HashMap1((A)key, hash, (B)value, kv)) : new HashMap1((A)nkv._1(), hash, (B)nkv._2(), nkv);
/*     */         } 
/*     */       } 
/* 212 */       HashMap1<A, B1> that = new HashMap1((A)key, hash, (B)value, kv);
/* 213 */       return (hash != hash()) ? HashMap$.MODULE$.<A, B1>scala$collection$immutable$HashMap$$makeHashTrieMap(hash(), this, hash, that, level, 2) : 
/*     */         
/* 216 */         new HashMap.HashMapCollision1<A, B1>(hash, ListMap$.MODULE$.empty().updated(key(), value()).updated(key, value));
/*     */     }
/*     */     
/*     */     public HashMap<A, B> removed0(Object key, int hash, int level) {
/* 221 */       if (hash == hash()) {
/* 221 */         A a = key();
/* 221 */         if ((key == a) ? true : ((key == null) ? false : ((key instanceof Number) ? BoxesRunTime.equalsNumObject((Number)key, a) : ((key instanceof Character) ? BoxesRunTime.equalsCharObject((Character)key, a) : key.equals(a)))));
/*     */       } 
/* 221 */       return this;
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<A, B>> iterator() {
/* 223 */       (new Tuple2[1])[0] = ensurePair();
/* 223 */       return scala.collection.Iterator$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1]));
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 224 */       f.apply(ensurePair());
/*     */     }
/*     */     
/*     */     public Tuple2<A, B> ensurePair() {
/* 226 */       kv_$eq(new Tuple2(key(), value()));
/* 226 */       return (kv() != null) ? kv() : kv();
/*     */     }
/*     */     
/*     */     public <B1> HashMap<A, B1> merge0(HashMap that, int level, HashMap.Merger merger) {
/* 228 */       return that.updated0(key(), hash(), level, value(), kv(), merger.invert());
/*     */     }
/*     */   }
/*     */   
/*     */   public static class HashMapCollision1<A, B> extends HashMap<A, B> {
/*     */     private final int hash;
/*     */     
/*     */     private final ListMap<A, B> kvs;
/*     */     
/*     */     public int hash() {
/* 232 */       return this.hash;
/*     */     }
/*     */     
/*     */     public ListMap<A, B> kvs() {
/* 232 */       return this.kvs;
/*     */     }
/*     */     
/*     */     public HashMapCollision1(int hash, ListMap<A, B> kvs) {}
/*     */     
/*     */     public int size() {
/* 236 */       return kvs().size();
/*     */     }
/*     */     
/*     */     public Option<B> get0(Object key, int hash, int level) {
/* 239 */       return (hash == hash()) ? kvs().get((A)key) : (Option<B>)scala.None$.MODULE$;
/*     */     }
/*     */     
/*     */     public <B1> HashMap<A, B1> updated0(Object key, int hash, int level, Object value, Tuple2<Object, Object> kv, HashMap.Merger<A, B> merger) {
/* 246 */       HashMap.HashMap1<Object, Object> that = new HashMap.HashMap1<Object, Object>(key, hash, value, kv);
/* 247 */       return (hash == hash()) ? ((merger != null && kvs().contains(key)) ? new HashMapCollision1(hash, kvs().$plus(merger.apply(new Tuple2(key, kvs().apply(key)), (Tuple2)kv))) : new HashMapCollision1(hash, kvs().updated((A)key, (B)value))) : HashMap$.MODULE$.<A, B1>scala$collection$immutable$HashMap$$makeHashTrieMap(hash(), this, hash, (HashMap)that, level, size() + 1);
/*     */     }
/*     */     
/*     */     public HashMap<A, B> removed0(Object key, int hash, int level) {
/* 252 */       ListMap<A, B> kvs1 = kvs().$minus((A)key);
/* 256 */       Tuple2<A, B> kv = (Tuple2)kvs1.head();
/* 257 */       return (hash == hash()) ? (kvs1.isEmpty() ? HashMap$.MODULE$.<A, B>empty() : (kvs1.tail().isEmpty() ? new HashMap.HashMap1<A, B>((A)kv._1(), hash, (B)kv._2(), kv) : 
/*     */         
/* 259 */         new HashMapCollision1(hash, kvs1))) : 
/* 260 */         this;
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<A, B>> iterator() {
/* 262 */       return kvs().iterator();
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 263 */       kvs().foreach(f);
/*     */     }
/*     */     
/*     */     public Seq<HashMap<A, B>> split() {
/* 265 */       Tuple2 tuple2 = kvs().splitAt(kvs().size() / 2);
/* 265 */       if (tuple2 != null) {
/* 265 */         Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 265 */         ListMap x = (ListMap)tuple21._1(), y = (ListMap)tuple21._2();
/* 267 */         (new HashMapCollision1[2])[0] = newhm$1(x);
/* 267 */         (new HashMapCollision1[2])[1] = newhm$1(y);
/* 267 */         return List$.MODULE$.apply((Seq<HashMap<A, B>>)scala.Predef$.MODULE$.wrapRefArray((Object[])new HashMapCollision1[2]));
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     private final HashMapCollision1 newhm$1(ListMap<A, B> lm) {
/*     */       return new HashMapCollision1(hash(), lm);
/*     */     }
/*     */     
/*     */     public <B1> HashMap<A, B1> merge0(HashMap that, int level, HashMap.Merger merger) {
/* 271 */       ObjectRef m = new ObjectRef(that);
/* 272 */       kvs().foreach((Function1)new HashMap$HashMapCollision1$$anonfun$merge0$1(this, level, merger, (HashMapCollision1<A, B>)m));
/* 273 */       return (HashMap<A, B1>)m.elem;
/*     */     }
/*     */     
/*     */     public class HashMap$HashMapCollision1$$anonfun$merge0$1 extends AbstractFunction1<Tuple2<A, B>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final int level$1;
/*     */       
/*     */       private final HashMap.Merger merger$1;
/*     */       
/*     */       private final ObjectRef m$1;
/*     */       
/*     */       public final void apply(Tuple2 p) {
/*     */         this.m$1.elem = ((HashMap)this.m$1.elem).updated0(p._1(), this.$outer.hash(), this.level$1, p._2(), p, this.merger$1);
/*     */       }
/*     */       
/*     */       public HashMap$HashMapCollision1$$anonfun$merge0$1(HashMap.HashMapCollision1 $outer, int level$1, HashMap.Merger merger$1, ObjectRef m$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public static class HashTrieMap<A, B> extends HashMap<A, B> {
/*     */     private final int bitmap;
/*     */     
/*     */     private final HashMap<A, B>[] elems;
/*     */     
/*     */     private final int size0;
/*     */     
/*     */     public int bitmap() {
/* 278 */       return this.bitmap;
/*     */     }
/*     */     
/*     */     public HashTrieMap(int bitmap, HashMap[] elems, int size0) {}
/*     */     
/*     */     public HashMap<A, B>[] elems() {
/* 279 */       return this.elems;
/*     */     }
/*     */     
/*     */     public int size0() {
/* 280 */       return this.size0;
/*     */     }
/*     */     
/*     */     public int size() {
/* 304 */       return size0();
/*     */     }
/*     */     
/*     */     public Option<B> get0(Object key, int hash, int level) {
/* 307 */       int index = hash >>> level & 0x1F;
/* 308 */       int mask = 1 << index;
/* 312 */       int offset = Integer.bitCount(bitmap() & mask - 1);
/* 314 */       return (bitmap() == -1) ? elems()[index & 0x1F].get0((A)key, hash, level + 5) : (((bitmap() & mask) != 0) ? elems()[offset].get0((A)key, hash, level + 5) : 
/*     */         
/* 316 */         (Option<B>)scala.None$.MODULE$);
/*     */     }
/*     */     
/*     */     public <B1> HashMap<A, B1> updated0(Object key, int hash, int level, Object value, Tuple2<A, Object> kv, HashMap.Merger<A, Object> merger) {
/* 320 */       int index = hash >>> level & 0x1F;
/* 321 */       int mask = 1 << index;
/* 322 */       int offset = Integer.bitCount(bitmap() & mask - 1);
/* 324 */       HashMap<A, B> sub = elems()[offset];
/* 326 */       HashMap<A, Object> subNew = sub.updated0((A)key, hash, level + 5, value, kv, merger);
/* 328 */       HashMap[] elemsNew = new HashMap[(elems()).length];
/* 329 */       scala.Array$.MODULE$.copy(elems(), 0, elemsNew, 0, (elems()).length);
/* 330 */       elemsNew[offset] = subNew;
/* 334 */       HashMap[] arrayOfHashMap1 = new HashMap[(elems()).length + 1];
/* 335 */       scala.Array$.MODULE$.copy(elems(), 0, arrayOfHashMap1, 0, offset);
/* 336 */       arrayOfHashMap1[offset] = new HashMap.HashMap1<Object, Object>(key, hash, value, (Tuple2)kv);
/* 337 */       scala.Array$.MODULE$.copy(elems(), offset, arrayOfHashMap1, offset + 1, (elems()).length - offset);
/* 338 */       return ((bitmap() & mask) != 0) ? ((subNew == sub) ? this : new HashTrieMap(bitmap(), (HashMap<A, B>[])elemsNew, size() + subNew.size() - sub.size())) : new HashTrieMap(bitmap() | mask, (HashMap<A, B>[])arrayOfHashMap1, size() + 1);
/*     */     }
/*     */     
/*     */     public HashMap<A, B> removed0(Object key, int hash, int level) {
/* 343 */       int index = hash >>> level & 0x1F;
/* 344 */       int mask = 1 << index;
/* 345 */       int offset = Integer.bitCount(bitmap() & mask - 1);
/* 347 */       HashMap<A, B> sub = elems()[offset];
/* 349 */       HashMap<A, B> subNew = sub.removed0((A)key, hash, level + 5);
/* 352 */       int bitmapNew = bitmap() ^ mask;
/* 354 */       HashMap[] elemsNew = new HashMap[(elems()).length - 1];
/* 355 */       scala.Array$.MODULE$.copy(elems(), 0, elemsNew, 0, offset);
/* 356 */       scala.Array$.MODULE$.copy(elems(), offset + 1, elemsNew, offset, (elems()).length - offset - 1);
/* 357 */       int sizeNew = size() - sub.size();
/* 367 */       HashMap[] arrayOfHashMap1 = new HashMap[(elems()).length];
/* 368 */       scala.Array$.MODULE$.copy(elems(), 0, arrayOfHashMap1, 0, (elems()).length);
/* 369 */       arrayOfHashMap1[offset] = subNew;
/* 370 */       int i = size() + subNew.size() - sub.size();
/* 371 */       return ((bitmap() & mask) != 0) ? ((subNew == sub) ? this : (subNew.isEmpty() ? ((bitmapNew != 0) ? ((elemsNew.length == 1 && !(elemsNew[0] instanceof HashTrieMap)) ? elemsNew[0] : new HashTrieMap(bitmapNew, (HashMap<A, B>[])elemsNew, sizeNew)) : HashMap$.MODULE$.<A, B>empty()) : (((elems()).length == 1 && !(subNew instanceof HashTrieMap)) ? subNew : new HashTrieMap(bitmap(), (HashMap<A, B>[])arrayOfHashMap1, i)))) : 
/*     */         
/* 374 */         this;
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<A, B>> iterator() {
/* 378 */       return (Iterator<Tuple2<A, B>>)new HashMap$HashTrieMap$$anon$1(this);
/*     */     }
/*     */     
/*     */     public class HashMap$HashTrieMap$$anon$1 extends TrieIterator<Tuple2<A, B>> {
/*     */       public HashMap$HashTrieMap$$anon$1(HashMap.HashTrieMap $outer) {
/* 378 */         super((Iterable<Tuple2<A, B>>[])$outer.elems());
/*     */       }
/*     */       
/*     */       public final Tuple2<A, B> getElem(Object cc) {
/* 379 */         return ((HashMap.HashMap1<A, B>)cc).ensurePair();
/*     */       }
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1<Tuple2<A, B>, ?> f) {
/* 401 */       int i = 0;
/* 402 */       while (i < (elems()).length) {
/* 403 */         elems()[i].foreach(f);
/* 404 */         i++;
/*     */       } 
/*     */     }
/*     */     
/*     */     private int posOf(int n, int bm) {
/* 409 */       int left = n;
/* 410 */       int i = -1;
/* 411 */       int b = bm;
/* 412 */       while (left >= 0) {
/* 413 */         i++;
/* 414 */         if ((b & 0x1) != 0)
/* 414 */           left--; 
/* 415 */         b >>>= 1;
/*     */       } 
/* 417 */       return i;
/*     */     }
/*     */     
/*     */     public Seq<HashMap<A, B>> split() {
/* 420 */       (new HashTrieMap[1])[0] = this;
/* 421 */       int nodesize = Integer.bitCount(bitmap());
/* 422 */       if (nodesize > 1) {
/* 423 */         int splitpoint = nodesize / 2;
/* 424 */         int bitsplitpoint = posOf(nodesize / 2, bitmap());
/* 425 */         int bm1 = bitmap() & -1 << bitsplitpoint;
/* 426 */         int bm2 = bitmap() & -1 >>> 32 - bitsplitpoint;
/* 428 */         Tuple2 tuple2 = scala.Predef$.MODULE$.refArrayOps((Object[])elems()).splitAt(splitpoint);
/* 428 */         if (tuple2 != null) {
/* 428 */           Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 428 */           HashMap[] e1 = (HashMap[])tuple21._1(), e2 = (HashMap[])tuple21._2();
/* 429 */           HashTrieMap hm1 = new HashTrieMap(bm1, (HashMap<A, B>[])e1, BoxesRunTime.unboxToInt(scala.Predef$.MODULE$.refArrayOps((Object[])e1).foldLeft(BoxesRunTime.boxToInteger(0), (Function2)new HashMap$HashTrieMap$$anonfun$2(this))));
/* 430 */           HashTrieMap hm2 = new HashTrieMap(bm2, (HashMap<A, B>[])e2, BoxesRunTime.unboxToInt(scala.Predef$.MODULE$.refArrayOps((Object[])e2).foldLeft(BoxesRunTime.boxToInteger(0), (Function2)new HashMap$HashTrieMap$$anonfun$3(this))));
/* 432 */           (new HashTrieMap[2])[0] = hm1;
/* 432 */           (new HashTrieMap[2])[1] = hm2;
/*     */         } else {
/*     */           throw new MatchError(tuple2);
/*     */         } 
/*     */       } else {
/*     */       
/*     */       } 
/* 433 */       return (size() == 1) ? (Seq<HashMap<A, B>>)Seq$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new HashTrieMap[1])) : elems()[0].split();
/*     */     }
/*     */     
/*     */     public class HashMap$HashTrieMap$$anonfun$2 extends AbstractFunction2<Object, HashMap<A, B>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply(int x$3, HashMap x$4) {
/*     */         return x$3 + x$4.size();
/*     */       }
/*     */       
/*     */       public HashMap$HashTrieMap$$anonfun$2(HashMap.HashTrieMap $outer) {}
/*     */     }
/*     */     
/*     */     public class HashMap$HashTrieMap$$anonfun$3 extends AbstractFunction2<Object, HashMap<A, B>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply(int x$5, HashMap x$6) {
/*     */         return x$5 + x$6.size();
/*     */       }
/*     */       
/*     */       public HashMap$HashTrieMap$$anonfun$3(HashMap.HashTrieMap $outer) {}
/*     */     }
/*     */     
/*     */     public <B1> HashMap<A, B1> merge0(HashMap that, int level, HashMap.Merger<?, ?> merger) {
/* 436 */       if (that instanceof HashMap.HashMap1) {
/* 436 */         HashMap.HashMap1<?, ?> hashMap1 = (HashMap.HashMap1)that;
/* 438 */         HashMap<?, ?> hashMap = updated0(hashMap1.key(), hashMap1.hash(), level, hashMap1.value(), hashMap1.kv(), merger);
/* 439 */       } else if (that instanceof HashTrieMap) {
/* 439 */         HashTrieMap hashTrieMap1 = (HashTrieMap)that;
/* 440 */         HashMap[] thiselems = (HashMap[])elems();
/* 442 */         HashMap[] thatelems = hashTrieMap1.elems();
/* 443 */         int thisbm = bitmap();
/* 444 */         int thatbm = hashTrieMap1.bitmap();
/* 447 */         int subcount = Integer.bitCount(thisbm | thatbm);
/* 450 */         HashMap[] merged = new HashMap[subcount];
/* 453 */         int i = 0;
/* 454 */         int thisi = 0;
/* 455 */         int thati = 0;
/* 456 */         int totalelems = 0;
/* 457 */         while (i < subcount) {
/* 458 */           int thislsb = thisbm ^ thisbm & thisbm - 1;
/* 459 */           int thatlsb = thatbm ^ thatbm & thatbm - 1;
/* 462 */           if (thislsb == thatlsb) {
/* 463 */             HashMap<?, ?> m = thiselems[thisi].merge0(thatelems[thati], level + 5, merger);
/* 464 */             totalelems += m.size();
/* 465 */             merged[i] = m;
/* 466 */             thisbm &= thislsb ^ 0xFFFFFFFF;
/* 467 */             thatbm &= thatlsb ^ 0xFFFFFFFF;
/* 468 */             thati++;
/* 469 */             thisi++;
/* 474 */           } else if (HashMap$.MODULE$.unsignedCompare(thislsb - 1, thatlsb - 1)) {
/* 478 */             HashMap m = thiselems[thisi];
/* 479 */             totalelems += m.size();
/* 480 */             merged[i] = m;
/* 481 */             thisbm &= thislsb ^ 0xFFFFFFFF;
/* 482 */             thisi++;
/*     */           } else {
/* 485 */             HashMap m = thatelems[thati];
/* 486 */             totalelems += m.size();
/* 487 */             merged[i] = m;
/* 488 */             thatbm &= thatlsb ^ 0xFFFFFFFF;
/* 489 */             thati++;
/*     */           } 
/* 492 */           i++;
/*     */         } 
/* 495 */         HashTrieMap hashTrieMap2 = new HashTrieMap(bitmap() | hashTrieMap1.bitmap(), (HashMap<A, B>[])merged, totalelems);
/* 496 */       } else if (that instanceof HashMap.HashMapCollision1) {
/* 496 */         HashMap hashMap = that.merge0(this, level, merger.invert());
/*     */       } else {
/* 497 */         if (that instanceof HashMap)
/* 497 */           return this; 
/* 498 */         throw scala.sys.package$.MODULE$.error("section supposed to be unreachable.");
/*     */       } 
/*     */       return (HashMap<A, B1>)SYNTHETIC_LOCAL_VARIABLE_21;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SerializationProxy<A, B> implements Serializable {
/*     */     public static final long serialVersionUID = 2L;
/*     */     
/*     */     private transient HashMap<A, B> scala$collection$immutable$HashMap$SerializationProxy$$orig;
/*     */     
/*     */     public HashMap<A, B> scala$collection$immutable$HashMap$SerializationProxy$$orig() {
/* 503 */       return this.scala$collection$immutable$HashMap$SerializationProxy$$orig;
/*     */     }
/*     */     
/*     */     public void scala$collection$immutable$HashMap$SerializationProxy$$orig_$eq(HashMap<A, B> x$1) {
/* 503 */       this.scala$collection$immutable$HashMap$SerializationProxy$$orig = x$1;
/*     */     }
/*     */     
/*     */     public SerializationProxy(HashMap<A, B> orig) {}
/*     */     
/*     */     private void writeObject(ObjectOutputStream out) {
/* 505 */       int s = scala$collection$immutable$HashMap$SerializationProxy$$orig().size();
/* 506 */       out.writeInt(s);
/* 507 */       scala$collection$immutable$HashMap$SerializationProxy$$orig().withFilter((Function1)new HashMap$SerializationProxy$$anonfun$writeObject$1(this)).foreach((Function1)new HashMap$SerializationProxy$$anonfun$writeObject$2(this, (SerializationProxy<A, B>)out));
/*     */     }
/*     */     
/*     */     public class HashMap$SerializationProxy$$anonfun$writeObject$2 extends AbstractFunction1<Tuple2<A, B>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final ObjectOutputStream out$1;
/*     */       
/*     */       public final void apply(Tuple2 x$7) {
/* 507 */         if (x$7 != null) {
/* 508 */           this.out$1.writeObject(x$7._1());
/* 509 */           this.out$1.writeObject(x$7._2());
/*     */           return;
/*     */         } 
/*     */         throw new MatchError(x$7);
/*     */       }
/*     */       
/*     */       public HashMap$SerializationProxy$$anonfun$writeObject$2(HashMap.SerializationProxy $outer, ObjectOutputStream out$1) {}
/*     */     }
/*     */     
/*     */     public class HashMap$SerializationProxy$$anonfun$writeObject$1 extends AbstractFunction1<Tuple2<A, B>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Tuple2 check$ifrefutable$1) {
/*     */         boolean bool;
/*     */         if (check$ifrefutable$1 != null) {
/*     */           bool = true;
/*     */         } else {
/*     */           bool = false;
/*     */         } 
/*     */         return bool;
/*     */       }
/*     */       
/*     */       public HashMap$SerializationProxy$$anonfun$writeObject$1(HashMap.SerializationProxy $outer) {}
/*     */     }
/*     */     
/*     */     private void readObject(ObjectInputStream in) {
/* 514 */       HashMap$ hashMap$ = HashMap$.MODULE$;
/* 514 */       scala$collection$immutable$HashMap$SerializationProxy$$orig_$eq(HashMap.EmptyHashMap$.MODULE$);
/* 515 */       int s = in.readInt();
/* 516 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 516 */       Range$ range$ = Range$.MODULE$;
/* 516 */       HashMap$SerializationProxy$$anonfun$readObject$1 hashMap$SerializationProxy$$anonfun$readObject$1 = new HashMap$SerializationProxy$$anonfun$readObject$1(this, (SerializationProxy<A, B>)in);
/*     */       Range range;
/* 516 */       if ((range = new Range(0, s, 1)).validateRangeBoundaries((Function1<Object, Object>)hashMap$SerializationProxy$$anonfun$readObject$1)) {
/*     */         int terminal1;
/*     */         int step1;
/*     */         int i1;
/* 516 */         for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 516 */           Object key1 = in.readObject(), value1 = in.readObject();
/* 516 */           scala$collection$immutable$HashMap$SerializationProxy$$orig_$eq(scala$collection$immutable$HashMap$SerializationProxy$$orig().updated((A)key1, (B)value1));
/* 516 */           i1 += step1;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public class HashMap$SerializationProxy$$anonfun$readObject$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final ObjectInputStream in$1;
/*     */       
/*     */       public final void apply(int i) {
/* 516 */         apply$mcVI$sp(i);
/*     */       }
/*     */       
/*     */       public HashMap$SerializationProxy$$anonfun$readObject$1(HashMap.SerializationProxy $outer, ObjectInputStream in$1) {}
/*     */       
/*     */       public void apply$mcVI$sp(int i) {
/* 517 */         Object key = this.in$1.readObject();
/* 518 */         Object value = this.in$1.readObject();
/* 519 */         this.$outer.scala$collection$immutable$HashMap$SerializationProxy$$orig_$eq(this.$outer.scala$collection$immutable$HashMap$SerializationProxy$$orig().updated(key, value));
/*     */       }
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 523 */       return scala$collection$immutable$HashMap$SerializationProxy$$orig();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\HashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */