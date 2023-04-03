/*     */ package akka.io;
/*     */ 
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorLogging;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.NoSerializationVerificationNeeded;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import akka.dispatch.RequiresMessageQueue;
/*     */ import akka.dispatch.UnboundedMessageQueueSemantics;
/*     */ import akka.event.LoggingAdapter;
/*     */ import akka.util.ByteString;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.net.SocketException;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import java.nio.channels.SocketChannel;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple4;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Traversable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractFunction4;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Statics;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\025ugAB\001\003\003\003\021aAA\007UGB\034uN\0348fGRLwN\034\006\003\007\021\t!![8\013\003\025\tA!Y6lCN)\001aB\007\024-A\021\001bC\007\002\023)\t!\"A\003tG\006d\027-\003\002\r\023\t1\021I\\=SK\032\004\"AD\t\016\003=Q!\001\005\003\002\013\005\034Go\034:\n\005Iy!!B!di>\024\bC\001\b\025\023\t)rB\001\007BGR|'\017T8hO&tw\rE\002\0305qi\021\001\007\006\0033\021\t\001\002Z5ta\006$8\r[\005\0037a\021ACU3rk&\024Xm]'fgN\fw-Z)vKV,\007CA\f\036\023\tq\002D\001\020V]\n|WO\0343fI6+7o]1hKF+X-^3TK6\fg\016^5dg\"A\001\005\001BC\002\023\005!%A\002uGB\034\001!F\001$!\t!S%D\001\003\023\t1#A\001\004UGB,\005\020\036\005\tQ\001\021\t\021)A\005G\005!Ao\0319!\021!Q\003A!b\001\n\003Y\023aB2iC:tW\r\\\013\002YA\021Q\006N\007\002])\021q\006M\001\tG\"\fgN\\3mg*\021\021GM\001\004]&|'\"A\032\002\t)\fg/Y\005\003k9\022QbU8dW\026$8\t[1o]\026d\007\002C\034\001\005\003\005\013\021\002\027\002\021\rD\027M\0348fY\002B\001\"\017\001\003\006\004%\tAO\001\taVdG.T8eKV\t1\b\005\002\ty%\021Q(\003\002\b\005>|G.Z1o\021!y\004A!A!\002\023Y\024!\0039vY2lu\016Z3!\021\025\t\005\001\"\001C\003\031a\024N\\5u}Q!1\tR#G!\t!\003\001C\003!\001\002\0071\005C\003+\001\002\007A\006C\003:\001\002\0071\b\003\004I\001\001\006K!S\001\ra\026tG-\0338h/JLG/\032\t\004\025\nUgB\001\023L\017\031a%\001#\001\003\033\006iAk\0319D_:tWm\031;j_:\004\"\001\n(\007\r\005\021\001\022\001\002P'\tqu\001C\003B\035\022\005\021\013F\001N\r\035\031f\n%A\022\"Q\023!BU3bIJ+7/\0367u'\t\021v!\013\003S-z#g!B,O\021\003A&aB!mYJ+\027\rZ\n\004-\036I\006C\001.S\033\005q\005\"B!W\t\003aF#A/\021\005i3f!B0O\021\003\001'aC#oI>37\013\036:fC6\0342AX\004Z\021\025\te\f\"\001c)\005\031\007C\001._\r\025)g\n#\001g\005=iuN]3ECR\fw+Y5uS:<7c\0013\b3\")\021\t\032C\001QR\t\021\016\005\002[I\036)1N\024E\001G\006YQI\0343PMN#(/Z1n\017\025ig\n#\001^\003\035\tE\016\034*fC\022<Qa\034(\t\002%\fq\"T8sK\022\013G/Y,bSRLgn\032\004\005c:\003%O\001\tDY>\034X-\0238g_Jl\027\r^5p]N!\001oB:w!\tAA/\003\002v\023\t9\001K]8ek\016$\bC\001\005x\023\tA\030B\001\007TKJL\027\r\\5{C\ndW\r\003\005{a\nU\r\021\"\001|\003=qw\016^5gS\016\fG/[8ogR{W#\001?\021\013u\f\t!a\002\017\005!q\030BA@\n\003\031\001&/\0323fM&!\0211AA\003\005\r\031V\r\036\006\003&\0012ADA\005\023\r\tYa\004\002\t\003\016$xN\035*fM\"I\021q\0029\003\022\003\006I\001`\001\021]>$\030NZ5dCRLwN\\:U_\002B!\"a\005q\005+\007I\021AA\013\003-\031Gn\\:fI\0263XM\034;\026\005\005]\001\003BA\r\003[qA!a\007\002*9!\021QDA\024\035\021\ty\"!\n\016\005\005\005\"bAA\022C\0051AH]8pizJ\021!B\005\003\007\021I1!a\013\003\003\r!6\r]\005\005\003_\t\tDA\003Fm\026tGOC\002\002,\tA!\"!\016q\005#\005\013\021BA\f\0031\031Gn\\:fI\0263XM\034;!\021\031\t\005\017\"\001\002:Q1\0211HA\037\003\001\"A\0279\t\ri\f9\0041\001}\021!\t\031\"a\016A\002\005]\001\"CA\"a\006\005I\021AA#\003\021\031w\016]=\025\r\005m\022qIA%\021!Q\030\021\tI\001\002\004a\bBCA\n\003\003\002\n\0211\001\002\030!I\021Q\n9\022\002\023\005\021qJ\001\017G>\004\030\020\n3fM\006,H\016\036\0232+\t\t\tFK\002}\003'Z#!!\026\021\t\005]\023\021M\007\003\0033RA!a\027\002^\005IQO\\2iK\016\\W\r\032\006\004\003?J\021AC1o]>$\030\r^5p]&!\0211MA-\005E)hn\0315fG.,GMV1sS\006t7-\032\005\n\003O\002\030\023!C\001\003S\nabY8qs\022\"WMZ1vYR$#'\006\002\002l)\"\021qCA*\021%\ty\007]A\001\n\003\n\t(A\007qe>$Wo\031;Qe\0264\027\016_\013\003\003g\002B!!\036\002|5\021\021q\017\006\004\003s\022\024\001\0027b]\036LA!! \002x\t11\013\036:j]\036D\021\"!!q\003\003%\t!a!\002\031A\024x\016Z;di\006\023\030\016^=\026\005\005\025\005c\001\005\002\b&\031\021\021R\005\003\007%sG\017C\005\002\016B\f\t\021\"\001\002\020\006q\001O]8ek\016$X\t\\3nK:$H\003BAI\003/\0032\001CAJ\023\r\t)*\003\002\004\003:L\bBCAM\003\027\013\t\0211\001\002\006\006\031\001\020J\031\t\023\005u\005/!A\005B\005}\025a\0049s_\022,8\r^%uKJ\fGo\034:\026\005\005\005\006CBAR\003S\013\t*\004\002\002&*\031\021qU\005\002\025\r|G\016\\3di&|g.\003\003\002,\006\025&\001C%uKJ\fGo\034:\t\023\005=\006/!A\005\002\005E\026\001C2b]\026\013X/\0317\025\007m\n\031\f\003\006\002\032\0065\026\021!a\001\003#C\021\"a.q\003\003%\t%!/\002\021!\f7\017[\"pI\026$\"!!\"\t\023\005u\006/!A\005B\005}\026\001\003;p'R\024\030N\\4\025\005\005M\004\"CAba\006\005I\021IAc\003\031)\027/^1mgR\0311(a2\t\025\005e\025\021YA\001\002\004\t\tjB\005\002L:\013\t\021#\001\002N\006\0012\t\\8tK&sgm\034:nCRLwN\034\t\0045\006=g\001C9O\003\003E\t!!5\024\013\005=\0271\033<\021\023\005U\0271\034?\002\030\005mRBAAl\025\r\tI.C\001\beVtG/[7f\023\021\ti.a6\003#\005\0237\017\036:bGR4UO\\2uS>t'\007C\004B\003\037$\t!!9\025\005\0055\007BCA_\003\037\f\t\021\"\022\002@\"Q\021q]Ah\003\003%\t)!;\002\013\005\004\b\017\\=\025\r\005m\0221^Aw\021\031Q\030Q\035a\001y\"A\0211CAs\001\004\t9\002\003\006\002r\006=\027\021!CA\003g\fq!\0368baBd\027\020\006\003\002v\n\005\001#\002\005\002x\006m\030bAA}\023\t1q\n\035;j_:\004b\001CAy\006]\021bAA\000\023\t1A+\0369mKJB!Ba\001\002p\006\005\t\031AA\036\003\rAH\005\r\005\013\005\017\ty-!A\005\n\t%\021a\003:fC\022\024Vm]8mm\026$\"Aa\003\021\t\005U$QB\005\005\005\037\t9H\001\004PE*,7\r\036\004\007\005'q\005I!\006\003\035\r{gN\\3di&|g.\0238g_N)!\021C\004tm\"Y!\021\004B\t\005+\007I\021\001B\016\0031\021XmZ5tiJ\fG/[8o+\t\021i\002E\002%\005?I1A!\t\003\005M\031\005.\0318oK2\024VmZ5tiJ\fG/[8o\021-\021)C!\005\003\022\003\006IA!\b\002\033I,w-[:ue\006$\030n\0348!\021-\021IC!\005\003\026\004%\tAa\013\002\017!\fg\016\0327feV\021\021q\001\005\f\005_\021\tB!E!\002\023\t9!\001\005iC:$G.\032:!\021)\021\031D!\005\003\026\004%\tAO\001\025W\026,\007o\0249f]>s\007+Z3s\0072|7/\0323\t\025\t]\"\021\003B\tB\003%1(A\013lK\026\004x\n]3o\037:\004V-\032:DY>\034X\r\032\021\t\025\tm\"\021\003BK\002\023\005!(\001\tvg\026\024Vm];nK^\023\030\016^5oO\"Q!q\bB\t\005#\005\013\021B\036\002#U\034XMU3tk6,wK]5uS:<\007\005C\004B\005#!\tAa\021\025\025\t\025#q\tB%\005\027\022i\005E\002[\005#A\001B!\007\003B\001\007!Q\004\005\t\005S\021\t\0051\001\002\b!9!1\007B!\001\004Y\004b\002B\036\005\003\002\ra\017\005\013\003\007\022\t\"!A\005\002\tECC\003B#\005'\022)Fa\026\003Z!Q!\021\004B(!\003\005\rA!\b\t\025\t%\"q\nI\001\002\004\t9\001C\005\0034\t=\003\023!a\001w!I!1\bB(!\003\005\ra\017\005\013\003\033\022\t\"%A\005\002\tuSC\001B0U\021\021i\"a\025\t\025\005\035$\021CI\001\n\003\021\031'\006\002\003f)\"\021qAA*\021)\021IG!\005\022\002\023\005!1N\001\017G>\004\030\020\n3fM\006,H\016\036\0234+\t\021iGK\002<\003'B!B!\035\003\022E\005I\021\001B6\0039\031w\016]=%I\0264\027-\0367uIQB!\"a\034\003\022\005\005I\021IA9\021)\t\tI!\005\002\002\023\005\0211\021\005\013\003\033\023\t\"!A\005\002\teD\003BAI\005wB!\"!'\003x\005\005\t\031AAC\021)\tiJ!\005\002\002\023\005\023q\024\005\013\003_\023\t\"!A\005\002\t\005EcA\036\003\004\"Q\021\021\024B@\003\003\005\r!!%\t\025\005]&\021CA\001\n\003\nI\f\003\006\002>\nE\021\021!C!\003C!\"a1\003\022\005\005I\021\tBF)\rY$Q\022\005\013\0033\023I)!AA\002\005Eu!\003BI\035\006\005\t\022\001BJ\0039\031uN\0348fGRLwN\\%oM>\0042A\027BK\r%\021\031BTA\001\022\003\0219jE\003\003\026\nee\017\005\007\002V\nm%QDA\004wm\022)%\003\003\003\036\006]'!E!cgR\024\030m\031;Gk:\034G/[8oi!9\021I!&\005\002\t\005FC\001BJ\021)\tiL!&\002\002\023\025\023q\030\005\013\003O\024)*!A\005\002\n\035FC\003B#\005S\023YK!,\0030\"A!\021\004BS\001\004\021i\002\003\005\003*\t\025\006\031AA\004\021\035\021\031D!*A\002mBqAa\017\003&\002\0071\b\003\006\002r\nU\025\021!CA\005g#BA!.\003>B)\001\"a>\0038BI\001B!/\003\036\005\0351hO\005\004\005wK!A\002+va2,G\007\003\006\003\004\tE\026\021!a\001\005\013B!Ba\002\003\026\006\005I\021\002B\005\r\031\021\031M\024!\003F\n\021R\013\0353bi\026\004VM\0343j]\036<&/\033;f'\035\021\tm\002BdgZ\0042A\004Be\023\r\021Ym\004\002\"\035>\034VM]5bY&T\030\r^5p]Z+'/\0334jG\006$\030n\0348OK\026$W\r\032\005\f\005\037\024\tM!f\001\n\003\021\t.\001\bsK6\f\027N\\5oO^\023\030\016^3\026\005\tM\007c\001.\003V\0329!q\033(\002\"\te'\001\004)f]\022LgnZ,sSR,7c\001Bk\017!9\021I!6\005\002\tuGC\001Bj\021!\021\tO!6\007\002\t-\022!C2p[6\fg\016Z3s\021!\021)O!6\007\002\t\035\030a\0023p/JLG/\032\013\005\005'\024I\017\003\005\003l\n\r\b\031\001B#\003\021IgNZ8\t\021\t=(Q\033D\001\005c\fqA]3mK\006\034X\r\006\002\003tB\031\001B!>\n\007\t]\030B\001\003V]&$\030\006\003Bk\005w\034\tba\031\007\017\tuh\n#\001\003\000\n\tR)\0349usB+g\016Z5oO^\023\030\016^3\024\t\tm(1\033\005\b\003\nmH\021AB\002)\t\031)\001E\002[\005wD\001B!9\003|\022\005!1\006\005\t\005K\024Y\020\"\001\004\fQ!!1[B\007\021!\021Yo!\003A\002\t\025\003\002\003Bx\005w$\tA!=\007\r\rM\001\001AB\013\005I\001VM\0343j]\036\024UO\0324fe^\023\030\016^3\024\007\rE\021\nC\006\003b\016E!Q1A\005\002\t-\002bCB\016\007#\021\t\021)A\005\003\017\t!bY8n[\006tG-\032:!\021-\031yb!\005\003\002\003\006Ia!\t\002\033I,W.Y5oS:<G)\031;b!\021\031\031c!\013\016\005\r\025\"bAB\024\t\005!Q\017^5m\023\021\031Yc!\n\003\025\tKH/Z*ue&tw\rC\006\0040\rE!\021!Q\001\n\005E\025aA1dW\"Y11GB\t\005\003\005\013\021BB\033\003\031\021WO\0324feB!1qGB\035\033\005\001\024bAB\036a\tQ!)\037;f\005V4g-\032:\t\027\r}2\021\003B\001B\003%1\021I\001\005i\006LG\016\005\003\002\032\r\r\023\002BB#\003c\021Ab\026:ji\026\034u.\\7b]\022Dq!QB\t\t\003\031I\005\006\007\004L\r=3\021KB*\007+\0329\006\005\003\004N\rEQ\"\001\001\t\021\t\0058q\ta\001\003\017A\001ba\b\004H\001\0071\021\005\005\t\007_\0319\0051\001\002\022\"A11GB$\001\004\031)\004\003\005\004@\r\035\003\031AB!\021!\021)o!\005\005\002\rmCcA%\004^!A!1^B-\001\004\031y\006E\002K\005#A\001Ba<\004\022\021\005!\021\037\004\007\007K\002\001aa\032\003!A+g\016Z5oO^\023\030\016^3GS2,7#BB2\023\016%\004\003BA;\007WJAa!\034\002x\tA!+\0368oC\ndW\rC\006\003b\016\r$Q1A\005\002\t-\002bCB\016\007G\022\t\021)A\005\003\017A1b!\036\004d\t\005\t\025!\003\004x\005Ya-\0337f\007\"\fgN\\3m!\ri3\021P\005\004\007wr#a\003$jY\026\034\005.\0318oK2D1ba \004d\t\005\t\025!\003\004\002\0061qN\0324tKR\0042\001CBB\023\r\031))\003\002\005\031>tw\rC\006\004\n\016\r$\021!Q\001\n\r\005\025!\003:f[\006Lg.\0338h\021-\031yca\031\003\002\003\006I!a\006\t\027\r}21\rB\001B\003%1\021\t\005\b\003\016\rD\021ABI)9\031\031j!&\004\030\016e51TBO\007?\003Ba!\024\004d!A!\021]BH\001\004\t9\001\003\005\004v\r=\005\031AB<\021!\031yha$A\002\r\005\005\002CBE\007\037\003\ra!!\t\021\r=2q\022a\001\003/A\001ba\020\004\020\002\0071\021\t\005\t\005K\034\031\007\"\001\004$R\031\021j!*\t\021\t-8\021\025a\001\007?B\001Ba<\004d\021\005!\021\037\005\t\007W\033\031\007\"\001\003r\006\031!/\0368\t\027\r=&\021\031B\tB\003%!1[\001\020e\026l\027-\0338j]\036<&/\033;fA!9\021I!1\005\002\rMF\003BB[\007o\0032A\027Ba\021!\021ym!-A\002\tM\007BCA\"\005\003\f\t\021\"\001\004<R!1QWB_\021)\021ym!/\021\002\003\007!1\033\005\013\003\033\022\t-%A\005\002\r\005WCABbU\021\021\031.a\025\t\025\005=$\021YA\001\n\003\n\t\b\003\006\002\002\n\005\027\021!C\001\003\007C!\"!$\003B\006\005I\021ABf)\021\t\tj!4\t\025\005e5\021ZA\001\002\004\t)\t\003\006\002\036\n\005\027\021!C!\003?C!\"a,\003B\006\005I\021ABj)\rY4Q\033\005\013\0033\033\t.!AA\002\005E\005BCA\\\005\003\f\t\021\"\021\002:\"Q\021Q\030Ba\003\003%\t%a0\t\025\005\r'\021YA\001\n\003\032i\016F\002<\007?D!\"!'\004\\\006\005\t\031AAI\017%\031\031OTA\001\022\003\031)/\001\nVa\022\fG/\032)f]\022LgnZ,sSR,\007c\001.\004h\032I!1\031(\002\002#\0051\021^\n\006\007O\034YO\036\t\t\003+\034iOa5\0046&!1q^Al\005E\t%m\035;sC\016$h)\0368di&|g.\r\005\b\003\016\035H\021ABz)\t\031)\017\003\006\002>\016\035\030\021!C#\003C!\"a:\004h\006\005I\021QB})\021\031)la?\t\021\t=7q\037a\001\005'D!\"!=\004h\006\005I\021QB\000)\021!\t\001b\001\021\013!\t9Pa5\t\025\t\r1Q`A\001\002\004\031)\f\003\006\003\b\r\035\030\021!C\005\005\0231a\001\"\003O\001\022-!aD,sSR,g)\0337f\r\006LG.\0323\024\013\021\035qa\035<\t\027\021=Aq\001BK\002\023\005A\021C\001\002KV\021A1\003\t\005\t+!I\"\004\002\005\030)\0211AM\005\005\t7!9BA\006J\037\026C8-\0329uS>t\007b\003C\020\t\017\021\t\022)A\005\t'\t!!\032\021\t\017\005#9\001\"\001\005$Q!AQ\005C\024!\rQFq\001\005\t\t\037!\t\0031\001\005\024!Q\0211\tC\004\003\003%\t\001b\013\025\t\021\025BQ\006\005\013\t\037!I\003%AA\002\021M\001BCA'\t\017\t\n\021\"\001\0052U\021A1\007\026\005\t'\t\031\006\003\006\002p\021\035\021\021!C!\003cB!\"!!\005\b\005\005I\021AAB\021)\ti\tb\002\002\002\023\005A1\b\013\005\003##i\004\003\006\002\032\022e\022\021!a\001\003\013C!\"!(\005\b\005\005I\021IAP\021)\ty\013b\002\002\002\023\005A1\t\013\004w\021\025\003BCAM\t\003\n\t\0211\001\002\022\"Q\021q\027C\004\003\003%\t%!/\t\025\005uFqAA\001\n\003\ny\f\003\006\002D\022\035\021\021!C!\t\033\"2a\017C(\021)\tI\nb\023\002\002\003\007\021\021S\004\n\t'r\025\021!E\001\t+\nqb\026:ji\0264\025\016\\3GC&dW\r\032\t\0045\022]c!\003C\005\035\006\005\t\022\001C-'\025!9\006b\027w!!\t)n!<\005\024\021\025\002bB!\005X\021\005Aq\f\013\003\t+B!\"!0\005X\005\005IQIA`\021)\t9\017b\026\002\002\023\005EQ\r\013\005\tK!9\007\003\005\005\020\021\r\004\031\001C\n\021)\t\t\020b\026\002\002\023\005E1\016\013\005\t[\"y\007E\003\t\003o$\031\002\003\006\003\004\021%\024\021!a\001\tKA!Ba\002\005X\005\005I\021\002B\005\017\035!)H\024E\001\007\013\t\021#R7qif\004VM\0343j]\036<&/\033;f\021\035!I\b\001Q!\nm\n!\002]3fe\016cwn]3e\021\035!i\b\001Q!\nm\n\001c\036:ji&twmU;ta\026tG-\0323\t\017\021\005\005\001)Q\005w\005\001\"/Z1eS:<7+^:qK:$W\r\032\005\t\t\013\003\001\025)\003\005\b\006\021\022N\034;fe\026\034H/\0323J]J+7/^7f!\025A\021q_A\004\021-!Y\t\001a\001\002\004%\t\001\"$\002\033\rdwn]3e\033\026\0348/Y4f+\t!y\t\005\002Ka\"YA1\023\001A\002\003\007I\021\001CK\003E\031Gn\\:fI6+7o]1hK~#S-\035\013\005\005g$9\n\003\006\002\032\022E\025\021!a\001\t\037C\001\002b'\001A\003&AqR\001\017G2|7/\0323NKN\034\030mZ3!\021\031!y\n\001C\001u\005aqO]5uKB+g\016Z5oO\"9A1\025\001\005\002\021\025\026AF<bSRLgn\032$peJ+w-[:ue\006$\030n\0348\025\r\021\035FQ\026CX!\021\031i\005\"+\n\007\021-\026CA\004SK\016,\027N^3\t\021\teA\021\025a\001\005;A\001B!9\005\"\002\007\021q\001\005\b\tg\003A\021\001C[\003%\031wN\0348fGR,G\r\006\003\005(\022]\006\002\003Bv\tc\003\raa\030\t\017\021m\006\001\"\001\005>\006Y\001/Z3s'\026tG/R(G)\021!9\013b0\t\021\t-H\021\030a\001\007?Bq\001b1\001\t\003!)-A\fdY>\034\030N\\4XSRD\007+\0328eS:<wK]5uKRAAq\025Cd\t\023$i\r\003\005\003l\022\005\007\031AB0\021!!Y\r\"1A\002\021\035\025AD2m_N,7i\\7nC:$WM\035\005\t\003'!\t\r1\001\005PB!\021\021\004Ci\023\021!\031.!\r\003!\r{gN\\3di&|gn\0217pg\026$\007b\002Cl\001\021\005A\021\\\001\bG2|7/\0338h)\031!9\013b7\005^\"A!1\036Ck\001\004\031y\006\003\005\005L\022U\007\031\001CD\021\035!\t\017\001C\001\tG\f1\003[1oI2,wK]5uK6+7o]1hKN$B\001b*\005f\"A!1\036Cp\001\004\031y\006C\004\005j\002!\t\001b;\002\037\r|W\016\0357fi\026\034uN\0348fGR$\002Ba=\005n\022=H\021\037\005\t\0053!9\0171\001\003\036!A!\021\035Ct\001\004\t9\001\003\005\005t\022\035\b\031\001C{\003\035y\007\017^5p]N\004b\001b>\005~\026\005QB\001C}\025\021!Y0!*\002\023%lW.\036;bE2,\027\002\002C\000\ts\0241\002\026:bm\026\0248/\0312mKB!Q1AC\005\035\021\tY\"\"\002\n\007\025\035!!\001\003J]\026$\030\002BC\006\013\033\021AbU8dW\026$x\n\035;j_:T1!b\002\003\021\035)\t\002\001C\001\013'\tab];ta\026tGMU3bI&tw\r\006\003\003t\026U\001\002\003Bv\013\037\001\raa\030\t\017\025e\001\001\"\001\006\034\005i!/Z:v[\026\024V-\0313j]\036$BAa=\006\036!A!1^C\f\001\004\031y\006C\004\006\"\001!\t!b\t\002\r\021|'+Z1e)\031\021\0310\"\n\006(!A!1^C\020\001\004\031y\006\003\005\005L\026}\001\031\001CD\021\035\021)\017\001C\001\013W!BAa=\006.!A!1^C\025\001\004\031y\006C\004\0062\001!\t!b\r\002\027\rdwn]3SK\006\034xN\\\013\003\013k\021b!b\016\005PN4hABC\035\001\001))D\001\007=e\0264\027N\\3nK:$h\bC\004\006>\001!\t!b\020\002\027!\fg\016\0327f\0072|7/\032\013\t\005g,\t%b\021\006F!A!1^C\036\001\004\031y\006\003\005\005L\026m\002\031\001CD\021!\t\031\"b\017A\002\021=\007bBC%\001\021\005Q1J\001\022I>\034En\\:f\007>tg.Z2uS>tG\003\003Bz\013\033*y%\"\025\t\021\t%Rq\ta\001\003\017A\001\002b3\006H\001\007Aq\021\005\t\003')9\0051\001\005P\"9QQ\013\001\005\002\025]\023a\0035b]\022dW-\022:s_J$bAa=\006Z\025m\003\002\003B\025\013'\002\r!a\002\t\021\025uS1\013a\001\t'\t\021\"\032=dKB$\030n\0348\t\017\025\005\004\001\"\001\006d\005\0212/\0314f'\",H\017Z8x]>+H\017];u)\005Y\004\002CC4\001\001&I!\"\033\002\025\025DHO]1di6\033x\r\006\003\006l\025=\004cA?\006n%!\021QPA\003\021!)\t(\"\032A\002\025M\024!\001;\021\t\025UTq\020\b\005\013o*YH\004\003\002 \025e\024\"\001\006\n\007\025u\024\"A\004qC\016\\\027mZ3\n\t\025\005U1\021\002\n)\"\024xn^1cY\026T1!\" \nQ\021))'b\"\021\t\025%U1R\007\003\003;JA!\"$\002^\t9A/Y5me\026\034\007bBCI\001\021\005!\021_\001\006C\n|'\017\036\005\b\013+\003A\021ACL\003!\031Ho\0349XSRDG\003\002Bz\0133C\001\"b'\006\024\002\007AqR\001\nG2|7/Z%oM>Dq!b(\001\t\003\022\t0\001\005q_N$8\013^8q\021\035)\031\013\001C!\013K\0131\002]8tiJ+7\017^1siR!!1_CT\021!)I+\")A\002\025M\024A\002:fCN|g\016C\004\006.\002!\t!b,\002\031A+g\016Z5oO^\023\030\016^3\025\013%+\t,b-\t\021\t\005X1\026a\001\003\017A\001\"\".\006,\002\0071\021I\001\006oJLG/\032\005\b\013s\003A\021AC^\003I\001VM\0343j]\036\024UO\0324fe^\023\030\016^3\025\025\r-SQXC`\013\007,)\r\003\005\003b\026]\006\031AA\004\021!)\t-b.A\002\r\005\022\001\0023bi\006D\001ba\f\0068\002\007\021q\003\005\t\007)9\f1\001\004B!9Q\021\032\001\005\002\025-\027\001\005)f]\022LgnZ,sSR,g)\0337f)9\031\031*\"4\006P\026MWQ[Cm\0137D\001B!9\006H\002\007\021q\001\005\t\013#,9\r1\001\006l\005Aa-\0337f!\006$\b\016\003\005\004\000\025\035\007\031ABA\021!)9.b2A\002\r\005\025!B2pk:$\b\002CB\030\013\017\004\r!a\006\t\021\r}Rq\031a\001\007\003\002")
/*     */ public abstract class TcpConnection implements Actor, ActorLogging, RequiresMessageQueue<UnboundedMessageQueueSemantics> {
/*     */   private final TcpExt tcp;
/*     */   
/*     */   private final SocketChannel channel;
/*     */   
/*     */   private final boolean pullMode;
/*     */   
/*     */   public PendingWrite akka$io$TcpConnection$$pendingWrite;
/*     */   
/*     */   private boolean peerClosed;
/*     */   
/*     */   public boolean akka$io$TcpConnection$$writingSuspended;
/*     */   
/*     */   private boolean readingSuspended;
/*     */   
/*     */   public Option<ActorRef> akka$io$TcpConnection$$interestedInResume;
/*     */   
/*     */   private CloseInformation closedMessage;
/*     */   
/*     */   private LoggingAdapter akka$actor$ActorLogging$$_log;
/*     */   
/*     */   private final ActorContext context;
/*     */   
/*     */   private final ActorRef self;
/*     */   
/*     */   public LoggingAdapter akka$actor$ActorLogging$$_log() {
/*  28 */     return this.akka$actor$ActorLogging$$_log;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void akka$actor$ActorLogging$$_log_$eq(LoggingAdapter x$1) {
/*  28 */     this.akka$actor$ActorLogging$$_log = x$1;
/*     */   }
/*     */   
/*     */   public LoggingAdapter log() {
/*  28 */     return ActorLogging.class.log(this);
/*     */   }
/*     */   
/*     */   public ActorContext context() {
/*  28 */     return this.context;
/*     */   }
/*     */   
/*     */   public final ActorRef self() {
/*  28 */     return this.self;
/*     */   }
/*     */   
/*     */   public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/*  28 */     this.context = x$1;
/*     */   }
/*     */   
/*     */   public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/*  28 */     this.self = x$1;
/*     */   }
/*     */   
/*     */   public final ActorRef sender() {
/*  28 */     return Actor.class.sender(this);
/*     */   }
/*     */   
/*     */   public void aroundReceive(PartialFunction receive, Object msg) {
/*  28 */     Actor.class.aroundReceive(this, receive, msg);
/*     */   }
/*     */   
/*     */   public void aroundPreStart() {
/*  28 */     Actor.class.aroundPreStart(this);
/*     */   }
/*     */   
/*     */   public void aroundPostStop() {
/*  28 */     Actor.class.aroundPostStop(this);
/*     */   }
/*     */   
/*     */   public void aroundPreRestart(Throwable reason, Option message) {
/*  28 */     Actor.class.aroundPreRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void aroundPostRestart(Throwable reason) {
/*  28 */     Actor.class.aroundPostRestart(this, reason);
/*     */   }
/*     */   
/*     */   public SupervisorStrategy supervisorStrategy() {
/*  28 */     return Actor.class.supervisorStrategy(this);
/*     */   }
/*     */   
/*     */   public void preStart() throws Exception {
/*  28 */     Actor.class.preStart(this);
/*     */   }
/*     */   
/*     */   public void preRestart(Throwable reason, Option message) throws Exception {
/*  28 */     Actor.class.preRestart(this, reason, message);
/*     */   }
/*     */   
/*     */   public void unhandled(Object message) {
/*  28 */     Actor.class.unhandled(this, message);
/*     */   }
/*     */   
/*     */   public TcpExt tcp() {
/*  28 */     return this.tcp;
/*     */   }
/*     */   
/*     */   public SocketChannel channel() {
/*  28 */     return this.channel;
/*     */   }
/*     */   
/*     */   public boolean pullMode() {
/*  28 */     return this.pullMode;
/*     */   }
/*     */   
/*     */   public TcpConnection(TcpExt tcp, SocketChannel channel, boolean pullMode) {
/*  28 */     Actor.class.$init$(this);
/*  28 */     ActorLogging.class.$init$(this);
/*  35 */     this.akka$io$TcpConnection$$pendingWrite = EmptyPendingWrite$.MODULE$;
/*  36 */     this.peerClosed = false;
/*  37 */     this.akka$io$TcpConnection$$writingSuspended = false;
/*  38 */     this.readingSuspended = pullMode;
/*  39 */     this.akka$io$TcpConnection$$interestedInResume = (Option<ActorRef>)scala.None$.MODULE$;
/*     */   }
/*     */   
/*     */   public CloseInformation closedMessage() {
/*  40 */     return this.closedMessage;
/*     */   }
/*     */   
/*     */   public void closedMessage_$eq(CloseInformation x$1) {
/*  40 */     this.closedMessage = x$1;
/*     */   }
/*     */   
/*     */   public boolean writePending() {
/*  42 */     return (this.akka$io$TcpConnection$$pendingWrite != EmptyPendingWrite$.MODULE$);
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> waitingForRegistration(ChannelRegistration registration, ActorRef commander) {
/*  47 */     return (PartialFunction<Object, BoxedUnit>)new TcpConnection$$anonfun$waitingForRegistration$1(this, registration, commander);
/*     */   }
/*     */   
/*     */   public class TcpConnection$$anonfun$waitingForRegistration$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ChannelRegistration registration$1;
/*     */     
/*     */     private final ActorRef commander$1;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_3
/*     */       //   2: aload_3
/*     */       //   3: instanceof akka/io/Tcp$Register
/*     */       //   6: ifeq -> 223
/*     */       //   9: aload_3
/*     */       //   10: checkcast akka/io/Tcp$Register
/*     */       //   13: astore #4
/*     */       //   15: aload #4
/*     */       //   17: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */       //   20: astore #5
/*     */       //   22: aload #4
/*     */       //   24: invokevirtual keepOpenOnPeerClosed : ()Z
/*     */       //   27: istore #6
/*     */       //   29: aload #4
/*     */       //   31: invokevirtual useResumeWriting : ()Z
/*     */       //   34: istore #7
/*     */       //   36: aload #5
/*     */       //   38: aload_0
/*     */       //   39: getfield commander$1 : Lakka/actor/ActorRef;
/*     */       //   42: astore #9
/*     */       //   44: dup
/*     */       //   45: ifnonnull -> 57
/*     */       //   48: pop
/*     */       //   49: aload #9
/*     */       //   51: ifnull -> 65
/*     */       //   54: goto -> 71
/*     */       //   57: aload #9
/*     */       //   59: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   62: ifeq -> 71
/*     */       //   65: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   68: goto -> 102
/*     */       //   71: aload_0
/*     */       //   72: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   75: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */       //   78: aload_0
/*     */       //   79: getfield commander$1 : Lakka/actor/ActorRef;
/*     */       //   82: invokeinterface unwatch : (Lakka/actor/ActorRef;)Lakka/actor/ActorRef;
/*     */       //   87: pop
/*     */       //   88: aload_0
/*     */       //   89: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   92: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */       //   95: aload #5
/*     */       //   97: invokeinterface watch : (Lakka/actor/ActorRef;)Lakka/actor/ActorRef;
/*     */       //   102: pop
/*     */       //   103: aload_0
/*     */       //   104: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   107: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */       //   110: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */       //   113: invokevirtual TraceLogging : ()Z
/*     */       //   116: ifeq -> 135
/*     */       //   119: aload_0
/*     */       //   120: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   123: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   126: ldc '[{}] registered as connection handler'
/*     */       //   128: aload #5
/*     */       //   130: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */       //   135: new akka/io/TcpConnection$ConnectionInfo
/*     */       //   138: dup
/*     */       //   139: aload_0
/*     */       //   140: getfield registration$1 : Lakka/io/ChannelRegistration;
/*     */       //   143: aload #5
/*     */       //   145: iload #6
/*     */       //   147: iload #7
/*     */       //   149: invokespecial <init> : (Lakka/io/ChannelRegistration;Lakka/actor/ActorRef;ZZ)V
/*     */       //   152: astore #10
/*     */       //   154: aload_0
/*     */       //   155: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   158: invokevirtual pullMode : ()Z
/*     */       //   161: ifne -> 176
/*     */       //   164: aload_0
/*     */       //   165: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   168: aload #10
/*     */       //   170: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   173: invokevirtual doRead : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;)V
/*     */       //   176: aload_0
/*     */       //   177: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   180: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */       //   183: getstatic scala/concurrent/duration/Duration$.MODULE$ : Lscala/concurrent/duration/Duration$;
/*     */       //   186: invokevirtual Undefined : ()Lscala/concurrent/duration/Duration$Infinite;
/*     */       //   189: invokeinterface setReceiveTimeout : (Lscala/concurrent/duration/Duration;)V
/*     */       //   194: aload_0
/*     */       //   195: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   198: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */       //   201: aload_0
/*     */       //   202: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   205: aload #10
/*     */       //   207: invokevirtual connected : (Lakka/io/TcpConnection$ConnectionInfo;)Lscala/PartialFunction;
/*     */       //   210: invokeinterface become : (Lscala/PartialFunction;)V
/*     */       //   215: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   218: astore #8
/*     */       //   220: goto -> 383
/*     */       //   223: aload_3
/*     */       //   224: instanceof akka/io/Tcp$CloseCommand
/*     */       //   227: ifeq -> 293
/*     */       //   230: aload_3
/*     */       //   231: checkcast akka/io/Tcp$CloseCommand
/*     */       //   234: astore #11
/*     */       //   236: new akka/io/TcpConnection$ConnectionInfo
/*     */       //   239: dup
/*     */       //   240: aload_0
/*     */       //   241: getfield registration$1 : Lakka/io/ChannelRegistration;
/*     */       //   244: aload_0
/*     */       //   245: getfield commander$1 : Lakka/actor/ActorRef;
/*     */       //   248: iconst_0
/*     */       //   249: iconst_0
/*     */       //   250: invokespecial <init> : (Lakka/io/ChannelRegistration;Lakka/actor/ActorRef;ZZ)V
/*     */       //   253: astore #12
/*     */       //   255: aload_0
/*     */       //   256: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   259: aload #12
/*     */       //   261: new scala/Some
/*     */       //   264: dup
/*     */       //   265: aload_0
/*     */       //   266: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   269: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   272: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   275: aload #11
/*     */       //   277: invokeinterface event : ()Lakka/io/Tcp$ConnectionClosed;
/*     */       //   282: invokevirtual handleClose : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */       //   285: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   288: astore #8
/*     */       //   290: goto -> 383
/*     */       //   293: getstatic akka/actor/ReceiveTimeout$.MODULE$ : Lakka/actor/ReceiveTimeout$;
/*     */       //   296: aload_3
/*     */       //   297: astore #13
/*     */       //   299: dup
/*     */       //   300: ifnonnull -> 312
/*     */       //   303: pop
/*     */       //   304: aload #13
/*     */       //   306: ifnull -> 320
/*     */       //   309: goto -> 374
/*     */       //   312: aload #13
/*     */       //   314: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   317: ifeq -> 374
/*     */       //   320: aload_0
/*     */       //   321: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   324: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   327: ldc 'Configured registration timeout of [{}] expired, stopping'
/*     */       //   329: aload_0
/*     */       //   330: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   333: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */       //   336: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */       //   339: invokevirtual RegisterTimeout : ()Lscala/concurrent/duration/Duration;
/*     */       //   342: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */       //   347: aload_0
/*     */       //   348: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   351: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */       //   354: aload_0
/*     */       //   355: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   358: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   361: invokeinterface stop : (Lakka/actor/ActorRef;)V
/*     */       //   366: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   369: astore #8
/*     */       //   371: goto -> 383
/*     */       //   374: aload_2
/*     */       //   375: aload_1
/*     */       //   376: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   381: astore #8
/*     */       //   383: aload #8
/*     */       //   385: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #47	-> 0
/*     */       //   #48	-> 2
/*     */       //   #51	-> 36
/*     */       //   #52	-> 71
/*     */       //   #53	-> 88
/*     */       //   #51	-> 102
/*     */       //   #55	-> 103
/*     */       //   #57	-> 135
/*     */       //   #58	-> 154
/*     */       //   #59	-> 176
/*     */       //   #60	-> 194
/*     */       //   #48	-> 218
/*     */       //   #62	-> 223
/*     */       //   #63	-> 236
/*     */       //   #64	-> 255
/*     */       //   #62	-> 288
/*     */       //   #66	-> 293
/*     */       //   #69	-> 320
/*     */       //   #70	-> 347
/*     */       //   #66	-> 369
/*     */       //   #47	-> 374
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	386	0	this	Lakka/io/TcpConnection$$anonfun$waitingForRegistration$1;
/*     */       //   0	386	1	x1	Ljava/lang/Object;
/*     */       //   0	386	2	default	Lscala/Function1;
/*     */       //   22	364	5	handler	Lakka/actor/ActorRef;
/*     */       //   29	357	6	keepOpenOnPeerClosed	Z
/*     */       //   36	350	7	useResumeWriting	Z
/*     */       //   154	64	10	info	Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   255	33	12	info	Lakka/io/TcpConnection$ConnectionInfo;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x1) {
/*     */       boolean bool;
/*  47 */       Object object = x1;
/*  48 */       if (object instanceof Tcp.Register) {
/*  48 */         bool = true;
/*  62 */       } else if (object instanceof Tcp.CloseCommand) {
/*  62 */         bool = true;
/*     */       } else {
/*  66 */         Object object1 = object;
/*  66 */         if (akka.actor.ReceiveTimeout$.MODULE$ == null) {
/*  66 */           if (object1 != null)
/*     */             boolean bool1 = false; 
/*     */         } else {
/*  66 */           if (akka.actor.ReceiveTimeout$.MODULE$.equals(object1))
/*  66 */             boolean bool2 = true; 
/*     */           boolean bool1 = false;
/*     */         } 
/*  66 */         bool = true;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public TcpConnection$$anonfun$waitingForRegistration$1(TcpConnection $outer, ChannelRegistration registration$1, ActorRef commander$1) {}
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> connected(ConnectionInfo info) {
/*  75 */     return handleWriteMessages(info).orElse((PartialFunction)new TcpConnection$$anonfun$connected$1(this, info));
/*     */   }
/*     */   
/*     */   public class TcpConnection$$anonfun$connected$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final TcpConnection.ConnectionInfo info$2;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x2, Function1 default) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_3
/*     */       //   2: getstatic akka/io/Tcp$SuspendReading$.MODULE$ : Lakka/io/Tcp$SuspendReading$;
/*     */       //   5: aload_3
/*     */       //   6: astore #4
/*     */       //   8: dup
/*     */       //   9: ifnonnull -> 21
/*     */       //   12: pop
/*     */       //   13: aload #4
/*     */       //   15: ifnull -> 29
/*     */       //   18: goto -> 48
/*     */       //   21: aload #4
/*     */       //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   26: ifeq -> 48
/*     */       //   29: aload_0
/*     */       //   30: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   33: aload_0
/*     */       //   34: getfield info$2 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   37: invokevirtual suspendReading : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   40: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   43: astore #5
/*     */       //   45: goto -> 205
/*     */       //   48: getstatic akka/io/Tcp$ResumeReading$.MODULE$ : Lakka/io/Tcp$ResumeReading$;
/*     */       //   51: aload_3
/*     */       //   52: astore #6
/*     */       //   54: dup
/*     */       //   55: ifnonnull -> 67
/*     */       //   58: pop
/*     */       //   59: aload #6
/*     */       //   61: ifnull -> 75
/*     */       //   64: goto -> 94
/*     */       //   67: aload #6
/*     */       //   69: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   72: ifeq -> 94
/*     */       //   75: aload_0
/*     */       //   76: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   79: aload_0
/*     */       //   80: getfield info$2 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   83: invokevirtual resumeReading : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   86: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   89: astore #5
/*     */       //   91: goto -> 205
/*     */       //   94: getstatic akka/io/SelectionHandler$ChannelReadable$.MODULE$ : Lakka/io/SelectionHandler$ChannelReadable$;
/*     */       //   97: aload_3
/*     */       //   98: astore #7
/*     */       //   100: dup
/*     */       //   101: ifnonnull -> 113
/*     */       //   104: pop
/*     */       //   105: aload #7
/*     */       //   107: ifnull -> 121
/*     */       //   110: goto -> 143
/*     */       //   113: aload #7
/*     */       //   115: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   118: ifeq -> 143
/*     */       //   121: aload_0
/*     */       //   122: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   125: aload_0
/*     */       //   126: getfield info$2 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   129: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   132: invokevirtual doRead : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;)V
/*     */       //   135: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   138: astore #5
/*     */       //   140: goto -> 205
/*     */       //   143: aload_3
/*     */       //   144: instanceof akka/io/Tcp$CloseCommand
/*     */       //   147: ifeq -> 196
/*     */       //   150: aload_3
/*     */       //   151: checkcast akka/io/Tcp$CloseCommand
/*     */       //   154: astore #8
/*     */       //   156: aload_0
/*     */       //   157: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   160: aload_0
/*     */       //   161: getfield info$2 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   164: new scala/Some
/*     */       //   167: dup
/*     */       //   168: aload_0
/*     */       //   169: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   172: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   175: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   178: aload #8
/*     */       //   180: invokeinterface event : ()Lakka/io/Tcp$ConnectionClosed;
/*     */       //   185: invokevirtual handleClose : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */       //   188: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   191: astore #5
/*     */       //   193: goto -> 205
/*     */       //   196: aload_2
/*     */       //   197: aload_1
/*     */       //   198: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   203: astore #5
/*     */       //   205: aload #5
/*     */       //   207: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #75	-> 0
/*     */       //   #76	-> 2
/*     */       //   #77	-> 48
/*     */       //   #78	-> 94
/*     */       //   #79	-> 143
/*     */       //   #75	-> 196
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	208	0	this	Lakka/io/TcpConnection$$anonfun$connected$1;
/*     */       //   0	208	1	x2	Ljava/lang/Object;
/*     */       //   0	208	2	default	Lscala/Function1;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x2) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_2
/*     */       //   2: getstatic akka/io/Tcp$SuspendReading$.MODULE$ : Lakka/io/Tcp$SuspendReading$;
/*     */       //   5: aload_2
/*     */       //   6: astore_3
/*     */       //   7: dup
/*     */       //   8: ifnonnull -> 19
/*     */       //   11: pop
/*     */       //   12: aload_3
/*     */       //   13: ifnull -> 26
/*     */       //   16: goto -> 32
/*     */       //   19: aload_3
/*     */       //   20: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   23: ifeq -> 32
/*     */       //   26: iconst_1
/*     */       //   27: istore #4
/*     */       //   29: goto -> 114
/*     */       //   32: getstatic akka/io/Tcp$ResumeReading$.MODULE$ : Lakka/io/Tcp$ResumeReading$;
/*     */       //   35: aload_2
/*     */       //   36: astore #5
/*     */       //   38: dup
/*     */       //   39: ifnonnull -> 51
/*     */       //   42: pop
/*     */       //   43: aload #5
/*     */       //   45: ifnull -> 59
/*     */       //   48: goto -> 65
/*     */       //   51: aload #5
/*     */       //   53: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   56: ifeq -> 65
/*     */       //   59: iconst_1
/*     */       //   60: istore #4
/*     */       //   62: goto -> 114
/*     */       //   65: getstatic akka/io/SelectionHandler$ChannelReadable$.MODULE$ : Lakka/io/SelectionHandler$ChannelReadable$;
/*     */       //   68: aload_2
/*     */       //   69: astore #6
/*     */       //   71: dup
/*     */       //   72: ifnonnull -> 84
/*     */       //   75: pop
/*     */       //   76: aload #6
/*     */       //   78: ifnull -> 92
/*     */       //   81: goto -> 98
/*     */       //   84: aload #6
/*     */       //   86: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   89: ifeq -> 98
/*     */       //   92: iconst_1
/*     */       //   93: istore #4
/*     */       //   95: goto -> 114
/*     */       //   98: aload_2
/*     */       //   99: instanceof akka/io/Tcp$CloseCommand
/*     */       //   102: ifeq -> 111
/*     */       //   105: iconst_1
/*     */       //   106: istore #4
/*     */       //   108: goto -> 114
/*     */       //   111: iconst_0
/*     */       //   112: istore #4
/*     */       //   114: iload #4
/*     */       //   116: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #75	-> 0
/*     */       //   #76	-> 2
/*     */       //   #77	-> 32
/*     */       //   #78	-> 65
/*     */       //   #79	-> 98
/*     */       //   #75	-> 111
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	117	0	this	Lakka/io/TcpConnection$$anonfun$connected$1;
/*     */       //   0	117	1	x2	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public TcpConnection$$anonfun$connected$1(TcpConnection $outer, TcpConnection.ConnectionInfo info$2) {}
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> peerSentEOF(ConnectionInfo info) {
/*  84 */     return handleWriteMessages(info).orElse((PartialFunction)new TcpConnection$$anonfun$peerSentEOF$1(this, info));
/*     */   }
/*     */   
/*     */   public class TcpConnection$$anonfun$peerSentEOF$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final TcpConnection.ConnectionInfo info$4;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x3, Function1 default) {
/*  84 */       Object object2, object1 = x3;
/*  85 */       if (object1 instanceof Tcp.CloseCommand) {
/*  85 */         Tcp.CloseCommand closeCommand = (Tcp.CloseCommand)object1;
/*  85 */         this.$outer.handleClose(this.info$4, (Option<ActorRef>)new Some(this.$outer.sender()), closeCommand.event());
/*  85 */         object2 = BoxedUnit.UNIT;
/*     */       } else {
/*     */         object2 = default.apply(x3);
/*     */       } 
/*     */       return (B1)object2;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x3) {
/*     */       boolean bool;
/*     */       Object object = x3;
/*  85 */       if (object instanceof Tcp.CloseCommand) {
/*  85 */         bool = true;
/*     */       } else {
/*     */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */     
/*     */     public TcpConnection$$anonfun$peerSentEOF$1(TcpConnection $outer, TcpConnection.ConnectionInfo info$4) {}
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> closingWithPendingWrite(ConnectionInfo info, Option closeCommander, Tcp.ConnectionClosed closedEvent) {
/*  90 */     return (PartialFunction<Object, BoxedUnit>)new TcpConnection$$anonfun$closingWithPendingWrite$1(this, info, closeCommander, closedEvent);
/*     */   }
/*     */   
/*     */   public class TcpConnection$$anonfun$closingWithPendingWrite$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final TcpConnection.ConnectionInfo info$5;
/*     */     
/*     */     private final Option closeCommander$1;
/*     */     
/*     */     private final Tcp.ConnectionClosed closedEvent$1;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x4, Function1 default) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_3
/*     */       //   2: getstatic akka/io/Tcp$SuspendReading$.MODULE$ : Lakka/io/Tcp$SuspendReading$;
/*     */       //   5: aload_3
/*     */       //   6: astore #4
/*     */       //   8: dup
/*     */       //   9: ifnonnull -> 21
/*     */       //   12: pop
/*     */       //   13: aload #4
/*     */       //   15: ifnull -> 29
/*     */       //   18: goto -> 48
/*     */       //   21: aload #4
/*     */       //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   26: ifeq -> 48
/*     */       //   29: aload_0
/*     */       //   30: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   33: aload_0
/*     */       //   34: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   37: invokevirtual suspendReading : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   40: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   43: astore #5
/*     */       //   45: goto -> 426
/*     */       //   48: getstatic akka/io/Tcp$ResumeReading$.MODULE$ : Lakka/io/Tcp$ResumeReading$;
/*     */       //   51: aload_3
/*     */       //   52: astore #6
/*     */       //   54: dup
/*     */       //   55: ifnonnull -> 67
/*     */       //   58: pop
/*     */       //   59: aload #6
/*     */       //   61: ifnull -> 75
/*     */       //   64: goto -> 94
/*     */       //   67: aload #6
/*     */       //   69: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   72: ifeq -> 94
/*     */       //   75: aload_0
/*     */       //   76: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   79: aload_0
/*     */       //   80: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   83: invokevirtual resumeReading : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   86: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   89: astore #5
/*     */       //   91: goto -> 426
/*     */       //   94: getstatic akka/io/SelectionHandler$ChannelReadable$.MODULE$ : Lakka/io/SelectionHandler$ChannelReadable$;
/*     */       //   97: aload_3
/*     */       //   98: astore #7
/*     */       //   100: dup
/*     */       //   101: ifnonnull -> 113
/*     */       //   104: pop
/*     */       //   105: aload #7
/*     */       //   107: ifnull -> 121
/*     */       //   110: goto -> 144
/*     */       //   113: aload #7
/*     */       //   115: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   118: ifeq -> 144
/*     */       //   121: aload_0
/*     */       //   122: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   125: aload_0
/*     */       //   126: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   129: aload_0
/*     */       //   130: getfield closeCommander$1 : Lscala/Option;
/*     */       //   133: invokevirtual doRead : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;)V
/*     */       //   136: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   139: astore #5
/*     */       //   141: goto -> 426
/*     */       //   144: getstatic akka/io/SelectionHandler$ChannelWritable$.MODULE$ : Lakka/io/SelectionHandler$ChannelWritable$;
/*     */       //   147: aload_3
/*     */       //   148: astore #8
/*     */       //   150: dup
/*     */       //   151: ifnonnull -> 163
/*     */       //   154: pop
/*     */       //   155: aload #8
/*     */       //   157: ifnull -> 171
/*     */       //   160: goto -> 225
/*     */       //   163: aload #8
/*     */       //   165: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   168: ifeq -> 225
/*     */       //   171: aload_0
/*     */       //   172: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   175: aload_0
/*     */       //   176: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   179: invokevirtual doWrite : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   182: aload_0
/*     */       //   183: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   186: invokevirtual writePending : ()Z
/*     */       //   189: ifeq -> 198
/*     */       //   192: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   195: goto -> 220
/*     */       //   198: aload_0
/*     */       //   199: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   202: aload_0
/*     */       //   203: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   206: aload_0
/*     */       //   207: getfield closeCommander$1 : Lscala/Option;
/*     */       //   210: aload_0
/*     */       //   211: getfield closedEvent$1 : Lakka/io/Tcp$ConnectionClosed;
/*     */       //   214: invokevirtual handleClose : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */       //   217: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   220: astore #5
/*     */       //   222: goto -> 426
/*     */       //   225: aload_3
/*     */       //   226: instanceof akka/io/TcpConnection$UpdatePendingWrite
/*     */       //   229: ifeq -> 310
/*     */       //   232: aload_3
/*     */       //   233: checkcast akka/io/TcpConnection$UpdatePendingWrite
/*     */       //   236: astore #9
/*     */       //   238: aload #9
/*     */       //   240: invokevirtual remainingWrite : ()Lakka/io/TcpConnection$PendingWrite;
/*     */       //   243: astore #10
/*     */       //   245: aload_0
/*     */       //   246: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   249: aload #10
/*     */       //   251: putfield akka$io$TcpConnection$$pendingWrite : Lakka/io/TcpConnection$PendingWrite;
/*     */       //   254: aload_0
/*     */       //   255: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   258: invokevirtual writePending : ()Z
/*     */       //   261: ifeq -> 283
/*     */       //   264: aload_0
/*     */       //   265: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   268: invokevirtual registration : ()Lakka/io/ChannelRegistration;
/*     */       //   271: iconst_4
/*     */       //   272: invokeinterface enableInterest : (I)V
/*     */       //   277: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   280: goto -> 305
/*     */       //   283: aload_0
/*     */       //   284: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   287: aload_0
/*     */       //   288: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   291: aload_0
/*     */       //   292: getfield closeCommander$1 : Lscala/Option;
/*     */       //   295: aload_0
/*     */       //   296: getfield closedEvent$1 : Lakka/io/Tcp$ConnectionClosed;
/*     */       //   299: invokevirtual handleClose : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */       //   302: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   305: astore #5
/*     */       //   307: goto -> 426
/*     */       //   310: aload_3
/*     */       //   311: instanceof akka/io/TcpConnection$WriteFileFailed
/*     */       //   314: ifeq -> 354
/*     */       //   317: aload_3
/*     */       //   318: checkcast akka/io/TcpConnection$WriteFileFailed
/*     */       //   321: astore #11
/*     */       //   323: aload #11
/*     */       //   325: invokevirtual e : ()Ljava/io/IOException;
/*     */       //   328: astore #12
/*     */       //   330: aload_0
/*     */       //   331: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   334: aload_0
/*     */       //   335: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   338: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */       //   341: aload #12
/*     */       //   343: invokevirtual handleError : (Lakka/actor/ActorRef;Ljava/io/IOException;)V
/*     */       //   346: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   349: astore #5
/*     */       //   351: goto -> 426
/*     */       //   354: getstatic akka/io/Tcp$Abort$.MODULE$ : Lakka/io/Tcp$Abort$;
/*     */       //   357: aload_3
/*     */       //   358: astore #13
/*     */       //   360: dup
/*     */       //   361: ifnonnull -> 373
/*     */       //   364: pop
/*     */       //   365: aload #13
/*     */       //   367: ifnull -> 381
/*     */       //   370: goto -> 417
/*     */       //   373: aload #13
/*     */       //   375: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   378: ifeq -> 417
/*     */       //   381: aload_0
/*     */       //   382: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   385: aload_0
/*     */       //   386: getfield info$5 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   389: new scala/Some
/*     */       //   392: dup
/*     */       //   393: aload_0
/*     */       //   394: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   397: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   400: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   403: getstatic akka/io/Tcp$Aborted$.MODULE$ : Lakka/io/Tcp$Aborted$;
/*     */       //   406: invokevirtual handleClose : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */       //   409: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   412: astore #5
/*     */       //   414: goto -> 426
/*     */       //   417: aload_2
/*     */       //   418: aload_1
/*     */       //   419: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   424: astore #5
/*     */       //   426: aload #5
/*     */       //   428: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #90	-> 0
/*     */       //   #91	-> 2
/*     */       //   #92	-> 48
/*     */       //   #93	-> 94
/*     */       //   #95	-> 144
/*     */       //   #96	-> 171
/*     */       //   #97	-> 182
/*     */       //   #98	-> 198
/*     */       //   #95	-> 220
/*     */       //   #100	-> 225
/*     */       //   #101	-> 245
/*     */       //   #102	-> 254
/*     */       //   #103	-> 283
/*     */       //   #100	-> 305
/*     */       //   #105	-> 310
/*     */       //   #107	-> 354
/*     */       //   #90	-> 417
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	429	0	this	Lakka/io/TcpConnection$$anonfun$closingWithPendingWrite$1;
/*     */       //   0	429	1	x4	Ljava/lang/Object;
/*     */       //   0	429	2	default	Lscala/Function1;
/*     */       //   245	184	10	remaining	Lakka/io/TcpConnection$PendingWrite;
/*     */       //   330	99	12	e	Ljava/io/IOException;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x4) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_2
/*     */       //   2: getstatic akka/io/Tcp$SuspendReading$.MODULE$ : Lakka/io/Tcp$SuspendReading$;
/*     */       //   5: aload_2
/*     */       //   6: astore_3
/*     */       //   7: dup
/*     */       //   8: ifnonnull -> 19
/*     */       //   11: pop
/*     */       //   12: aload_3
/*     */       //   13: ifnull -> 26
/*     */       //   16: goto -> 32
/*     */       //   19: aload_3
/*     */       //   20: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   23: ifeq -> 32
/*     */       //   26: iconst_1
/*     */       //   27: istore #4
/*     */       //   29: goto -> 193
/*     */       //   32: getstatic akka/io/Tcp$ResumeReading$.MODULE$ : Lakka/io/Tcp$ResumeReading$;
/*     */       //   35: aload_2
/*     */       //   36: astore #5
/*     */       //   38: dup
/*     */       //   39: ifnonnull -> 51
/*     */       //   42: pop
/*     */       //   43: aload #5
/*     */       //   45: ifnull -> 59
/*     */       //   48: goto -> 65
/*     */       //   51: aload #5
/*     */       //   53: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   56: ifeq -> 65
/*     */       //   59: iconst_1
/*     */       //   60: istore #4
/*     */       //   62: goto -> 193
/*     */       //   65: getstatic akka/io/SelectionHandler$ChannelReadable$.MODULE$ : Lakka/io/SelectionHandler$ChannelReadable$;
/*     */       //   68: aload_2
/*     */       //   69: astore #6
/*     */       //   71: dup
/*     */       //   72: ifnonnull -> 84
/*     */       //   75: pop
/*     */       //   76: aload #6
/*     */       //   78: ifnull -> 92
/*     */       //   81: goto -> 98
/*     */       //   84: aload #6
/*     */       //   86: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   89: ifeq -> 98
/*     */       //   92: iconst_1
/*     */       //   93: istore #4
/*     */       //   95: goto -> 193
/*     */       //   98: getstatic akka/io/SelectionHandler$ChannelWritable$.MODULE$ : Lakka/io/SelectionHandler$ChannelWritable$;
/*     */       //   101: aload_2
/*     */       //   102: astore #7
/*     */       //   104: dup
/*     */       //   105: ifnonnull -> 117
/*     */       //   108: pop
/*     */       //   109: aload #7
/*     */       //   111: ifnull -> 125
/*     */       //   114: goto -> 131
/*     */       //   117: aload #7
/*     */       //   119: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   122: ifeq -> 131
/*     */       //   125: iconst_1
/*     */       //   126: istore #4
/*     */       //   128: goto -> 193
/*     */       //   131: aload_2
/*     */       //   132: instanceof akka/io/TcpConnection$UpdatePendingWrite
/*     */       //   135: ifeq -> 144
/*     */       //   138: iconst_1
/*     */       //   139: istore #4
/*     */       //   141: goto -> 193
/*     */       //   144: aload_2
/*     */       //   145: instanceof akka/io/TcpConnection$WriteFileFailed
/*     */       //   148: ifeq -> 157
/*     */       //   151: iconst_1
/*     */       //   152: istore #4
/*     */       //   154: goto -> 193
/*     */       //   157: getstatic akka/io/Tcp$Abort$.MODULE$ : Lakka/io/Tcp$Abort$;
/*     */       //   160: aload_2
/*     */       //   161: astore #8
/*     */       //   163: dup
/*     */       //   164: ifnonnull -> 176
/*     */       //   167: pop
/*     */       //   168: aload #8
/*     */       //   170: ifnull -> 184
/*     */       //   173: goto -> 190
/*     */       //   176: aload #8
/*     */       //   178: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   181: ifeq -> 190
/*     */       //   184: iconst_1
/*     */       //   185: istore #4
/*     */       //   187: goto -> 193
/*     */       //   190: iconst_0
/*     */       //   191: istore #4
/*     */       //   193: iload #4
/*     */       //   195: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #90	-> 0
/*     */       //   #91	-> 2
/*     */       //   #92	-> 32
/*     */       //   #93	-> 65
/*     */       //   #95	-> 98
/*     */       //   #100	-> 131
/*     */       //   #105	-> 144
/*     */       //   #107	-> 157
/*     */       //   #90	-> 190
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	196	0	this	Lakka/io/TcpConnection$$anonfun$closingWithPendingWrite$1;
/*     */       //   0	196	1	x4	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public TcpConnection$$anonfun$closingWithPendingWrite$1(TcpConnection $outer, TcpConnection.ConnectionInfo info$5, Option closeCommander$1, Tcp.ConnectionClosed closedEvent$1) {}
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> closing(ConnectionInfo info, Option closeCommander) {
/* 111 */     return (PartialFunction<Object, BoxedUnit>)new TcpConnection$$anonfun$closing$1(this, info, closeCommander);
/*     */   }
/*     */   
/*     */   public class TcpConnection$$anonfun$closing$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final TcpConnection.ConnectionInfo info$6;
/*     */     
/*     */     private final Option closeCommander$2;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x5, Function1 default) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_3
/*     */       //   2: getstatic akka/io/Tcp$SuspendReading$.MODULE$ : Lakka/io/Tcp$SuspendReading$;
/*     */       //   5: aload_3
/*     */       //   6: astore #4
/*     */       //   8: dup
/*     */       //   9: ifnonnull -> 21
/*     */       //   12: pop
/*     */       //   13: aload #4
/*     */       //   15: ifnull -> 29
/*     */       //   18: goto -> 48
/*     */       //   21: aload #4
/*     */       //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   26: ifeq -> 48
/*     */       //   29: aload_0
/*     */       //   30: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   33: aload_0
/*     */       //   34: getfield info$6 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   37: invokevirtual suspendReading : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   40: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   43: astore #5
/*     */       //   45: goto -> 216
/*     */       //   48: getstatic akka/io/Tcp$ResumeReading$.MODULE$ : Lakka/io/Tcp$ResumeReading$;
/*     */       //   51: aload_3
/*     */       //   52: astore #6
/*     */       //   54: dup
/*     */       //   55: ifnonnull -> 67
/*     */       //   58: pop
/*     */       //   59: aload #6
/*     */       //   61: ifnull -> 75
/*     */       //   64: goto -> 94
/*     */       //   67: aload #6
/*     */       //   69: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   72: ifeq -> 94
/*     */       //   75: aload_0
/*     */       //   76: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   79: aload_0
/*     */       //   80: getfield info$6 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   83: invokevirtual resumeReading : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   86: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   89: astore #5
/*     */       //   91: goto -> 216
/*     */       //   94: getstatic akka/io/SelectionHandler$ChannelReadable$.MODULE$ : Lakka/io/SelectionHandler$ChannelReadable$;
/*     */       //   97: aload_3
/*     */       //   98: astore #7
/*     */       //   100: dup
/*     */       //   101: ifnonnull -> 113
/*     */       //   104: pop
/*     */       //   105: aload #7
/*     */       //   107: ifnull -> 121
/*     */       //   110: goto -> 144
/*     */       //   113: aload #7
/*     */       //   115: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   118: ifeq -> 144
/*     */       //   121: aload_0
/*     */       //   122: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   125: aload_0
/*     */       //   126: getfield info$6 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   129: aload_0
/*     */       //   130: getfield closeCommander$2 : Lscala/Option;
/*     */       //   133: invokevirtual doRead : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;)V
/*     */       //   136: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   139: astore #5
/*     */       //   141: goto -> 216
/*     */       //   144: getstatic akka/io/Tcp$Abort$.MODULE$ : Lakka/io/Tcp$Abort$;
/*     */       //   147: aload_3
/*     */       //   148: astore #8
/*     */       //   150: dup
/*     */       //   151: ifnonnull -> 163
/*     */       //   154: pop
/*     */       //   155: aload #8
/*     */       //   157: ifnull -> 171
/*     */       //   160: goto -> 207
/*     */       //   163: aload #8
/*     */       //   165: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   168: ifeq -> 207
/*     */       //   171: aload_0
/*     */       //   172: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   175: aload_0
/*     */       //   176: getfield info$6 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   179: new scala/Some
/*     */       //   182: dup
/*     */       //   183: aload_0
/*     */       //   184: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   187: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   190: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   193: getstatic akka/io/Tcp$Aborted$.MODULE$ : Lakka/io/Tcp$Aborted$;
/*     */       //   196: invokevirtual handleClose : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */       //   199: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   202: astore #5
/*     */       //   204: goto -> 216
/*     */       //   207: aload_2
/*     */       //   208: aload_1
/*     */       //   209: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   214: astore #5
/*     */       //   216: aload #5
/*     */       //   218: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #111	-> 0
/*     */       //   #112	-> 2
/*     */       //   #113	-> 48
/*     */       //   #114	-> 94
/*     */       //   #115	-> 144
/*     */       //   #111	-> 207
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	219	0	this	Lakka/io/TcpConnection$$anonfun$closing$1;
/*     */       //   0	219	1	x5	Ljava/lang/Object;
/*     */       //   0	219	2	default	Lscala/Function1;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x5) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_2
/*     */       //   2: getstatic akka/io/Tcp$SuspendReading$.MODULE$ : Lakka/io/Tcp$SuspendReading$;
/*     */       //   5: aload_2
/*     */       //   6: astore_3
/*     */       //   7: dup
/*     */       //   8: ifnonnull -> 19
/*     */       //   11: pop
/*     */       //   12: aload_3
/*     */       //   13: ifnull -> 26
/*     */       //   16: goto -> 32
/*     */       //   19: aload_3
/*     */       //   20: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   23: ifeq -> 32
/*     */       //   26: iconst_1
/*     */       //   27: istore #4
/*     */       //   29: goto -> 134
/*     */       //   32: getstatic akka/io/Tcp$ResumeReading$.MODULE$ : Lakka/io/Tcp$ResumeReading$;
/*     */       //   35: aload_2
/*     */       //   36: astore #5
/*     */       //   38: dup
/*     */       //   39: ifnonnull -> 51
/*     */       //   42: pop
/*     */       //   43: aload #5
/*     */       //   45: ifnull -> 59
/*     */       //   48: goto -> 65
/*     */       //   51: aload #5
/*     */       //   53: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   56: ifeq -> 65
/*     */       //   59: iconst_1
/*     */       //   60: istore #4
/*     */       //   62: goto -> 134
/*     */       //   65: getstatic akka/io/SelectionHandler$ChannelReadable$.MODULE$ : Lakka/io/SelectionHandler$ChannelReadable$;
/*     */       //   68: aload_2
/*     */       //   69: astore #6
/*     */       //   71: dup
/*     */       //   72: ifnonnull -> 84
/*     */       //   75: pop
/*     */       //   76: aload #6
/*     */       //   78: ifnull -> 92
/*     */       //   81: goto -> 98
/*     */       //   84: aload #6
/*     */       //   86: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   89: ifeq -> 98
/*     */       //   92: iconst_1
/*     */       //   93: istore #4
/*     */       //   95: goto -> 134
/*     */       //   98: getstatic akka/io/Tcp$Abort$.MODULE$ : Lakka/io/Tcp$Abort$;
/*     */       //   101: aload_2
/*     */       //   102: astore #7
/*     */       //   104: dup
/*     */       //   105: ifnonnull -> 117
/*     */       //   108: pop
/*     */       //   109: aload #7
/*     */       //   111: ifnull -> 125
/*     */       //   114: goto -> 131
/*     */       //   117: aload #7
/*     */       //   119: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   122: ifeq -> 131
/*     */       //   125: iconst_1
/*     */       //   126: istore #4
/*     */       //   128: goto -> 134
/*     */       //   131: iconst_0
/*     */       //   132: istore #4
/*     */       //   134: iload #4
/*     */       //   136: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #111	-> 0
/*     */       //   #112	-> 2
/*     */       //   #113	-> 32
/*     */       //   #114	-> 65
/*     */       //   #115	-> 98
/*     */       //   #111	-> 131
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	137	0	this	Lakka/io/TcpConnection$$anonfun$closing$1;
/*     */       //   0	137	1	x5	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public TcpConnection$$anonfun$closing$1(TcpConnection $outer, TcpConnection.ConnectionInfo info$6, Option closeCommander$2) {}
/*     */   }
/*     */   
/*     */   public PartialFunction<Object, BoxedUnit> handleWriteMessages(ConnectionInfo info) {
/* 118 */     return (PartialFunction<Object, BoxedUnit>)new TcpConnection$$anonfun$handleWriteMessages$1(this, info);
/*     */   }
/*     */   
/*     */   public class TcpConnection$$anonfun$handleWriteMessages$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final TcpConnection.ConnectionInfo info$3;
/*     */     
/*     */     public final <A1, B1> B1 applyOrElse(Object x6, Function1 default) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_3
/*     */       //   2: getstatic akka/io/SelectionHandler$ChannelWritable$.MODULE$ : Lakka/io/SelectionHandler$ChannelWritable$;
/*     */       //   5: aload_3
/*     */       //   6: astore #4
/*     */       //   8: dup
/*     */       //   9: ifnonnull -> 21
/*     */       //   12: pop
/*     */       //   13: aload #4
/*     */       //   15: ifnull -> 29
/*     */       //   18: goto -> 137
/*     */       //   21: aload #4
/*     */       //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   26: ifeq -> 137
/*     */       //   29: aload_0
/*     */       //   30: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   33: invokevirtual writePending : ()Z
/*     */       //   36: ifeq -> 129
/*     */       //   39: aload_0
/*     */       //   40: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   43: aload_0
/*     */       //   44: getfield info$3 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   47: invokevirtual doWrite : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   50: aload_0
/*     */       //   51: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   54: invokevirtual writePending : ()Z
/*     */       //   57: ifne -> 123
/*     */       //   60: aload_0
/*     */       //   61: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   64: getfield akka$io$TcpConnection$$interestedInResume : Lscala/Option;
/*     */       //   67: invokevirtual nonEmpty : ()Z
/*     */       //   70: ifeq -> 123
/*     */       //   73: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   76: aload_0
/*     */       //   77: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   80: getfield akka$io$TcpConnection$$interestedInResume : Lscala/Option;
/*     */       //   83: invokevirtual get : ()Ljava/lang/Object;
/*     */       //   86: checkcast akka/actor/ActorRef
/*     */       //   89: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   92: getstatic akka/io/Tcp$WritingResumed$.MODULE$ : Lakka/io/Tcp$WritingResumed$;
/*     */       //   95: aload_0
/*     */       //   96: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   99: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   102: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   107: aload_0
/*     */       //   108: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   111: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   114: putfield akka$io$TcpConnection$$interestedInResume : Lscala/Option;
/*     */       //   117: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   120: goto -> 132
/*     */       //   123: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   126: goto -> 132
/*     */       //   129: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   132: astore #5
/*     */       //   134: goto -> 665
/*     */       //   137: aload_3
/*     */       //   138: instanceof akka/io/Tcp$WriteCommand
/*     */       //   141: ifeq -> 384
/*     */       //   144: aload_3
/*     */       //   145: checkcast akka/io/Tcp$WriteCommand
/*     */       //   148: astore #6
/*     */       //   150: aload_0
/*     */       //   151: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   154: getfield akka$io$TcpConnection$$writingSuspended : Z
/*     */       //   157: ifeq -> 226
/*     */       //   160: aload_0
/*     */       //   161: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   164: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */       //   167: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */       //   170: invokevirtual TraceLogging : ()Z
/*     */       //   173: ifeq -> 190
/*     */       //   176: aload_0
/*     */       //   177: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   180: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   183: ldc 'Dropping write because writing is suspended'
/*     */       //   185: invokeinterface debug : (Ljava/lang/String;)V
/*     */       //   190: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   193: aload_0
/*     */       //   194: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   197: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   200: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   203: aload #6
/*     */       //   205: invokevirtual failureMessage : ()Lakka/io/Tcp$CommandFailed;
/*     */       //   208: aload_0
/*     */       //   209: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   212: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   215: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   220: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   223: goto -> 379
/*     */       //   226: aload_0
/*     */       //   227: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   230: invokevirtual writePending : ()Z
/*     */       //   233: ifeq -> 326
/*     */       //   236: aload_0
/*     */       //   237: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   240: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */       //   243: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */       //   246: invokevirtual TraceLogging : ()Z
/*     */       //   249: ifeq -> 266
/*     */       //   252: aload_0
/*     */       //   253: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   256: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   259: ldc 'Dropping write because queue is full'
/*     */       //   261: invokeinterface debug : (Ljava/lang/String;)V
/*     */       //   266: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   269: aload_0
/*     */       //   270: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   273: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   276: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   279: aload #6
/*     */       //   281: invokevirtual failureMessage : ()Lakka/io/Tcp$CommandFailed;
/*     */       //   284: aload_0
/*     */       //   285: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   288: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   291: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   296: aload_0
/*     */       //   297: getfield info$3 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   300: invokevirtual useResumeWriting : ()Z
/*     */       //   303: ifeq -> 320
/*     */       //   306: aload_0
/*     */       //   307: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   310: iconst_1
/*     */       //   311: putfield akka$io$TcpConnection$$writingSuspended : Z
/*     */       //   314: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   317: goto -> 379
/*     */       //   320: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   323: goto -> 379
/*     */       //   326: aload_0
/*     */       //   327: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   330: aload_0
/*     */       //   331: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   334: aload_0
/*     */       //   335: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   338: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   341: aload #6
/*     */       //   343: invokevirtual PendingWrite : (Lakka/actor/ActorRef;Lakka/io/Tcp$WriteCommand;)Lakka/io/TcpConnection$PendingWrite;
/*     */       //   346: putfield akka$io$TcpConnection$$pendingWrite : Lakka/io/TcpConnection$PendingWrite;
/*     */       //   349: aload_0
/*     */       //   350: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   353: invokevirtual writePending : ()Z
/*     */       //   356: ifeq -> 376
/*     */       //   359: aload_0
/*     */       //   360: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   363: aload_0
/*     */       //   364: getfield info$3 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   367: invokevirtual doWrite : (Lakka/io/TcpConnection$ConnectionInfo;)V
/*     */       //   370: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   373: goto -> 379
/*     */       //   376: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   379: astore #5
/*     */       //   381: goto -> 665
/*     */       //   384: getstatic akka/io/Tcp$ResumeWriting$.MODULE$ : Lakka/io/Tcp$ResumeWriting$;
/*     */       //   387: aload_3
/*     */       //   388: astore #7
/*     */       //   390: dup
/*     */       //   391: ifnonnull -> 403
/*     */       //   394: pop
/*     */       //   395: aload #7
/*     */       //   397: ifnull -> 411
/*     */       //   400: goto -> 546
/*     */       //   403: aload #7
/*     */       //   405: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   408: ifeq -> 546
/*     */       //   411: aload_0
/*     */       //   412: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   415: iconst_0
/*     */       //   416: putfield akka$io$TcpConnection$$writingSuspended : Z
/*     */       //   419: aload_0
/*     */       //   420: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   423: invokevirtual writePending : ()Z
/*     */       //   426: ifeq -> 510
/*     */       //   429: aload_0
/*     */       //   430: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   433: getfield akka$io$TcpConnection$$interestedInResume : Lscala/Option;
/*     */       //   436: invokevirtual isEmpty : ()Z
/*     */       //   439: ifeq -> 469
/*     */       //   442: aload_0
/*     */       //   443: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   446: new scala/Some
/*     */       //   449: dup
/*     */       //   450: aload_0
/*     */       //   451: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   454: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   457: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   460: putfield akka$io$TcpConnection$$interestedInResume : Lscala/Option;
/*     */       //   463: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   466: goto -> 541
/*     */       //   469: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   472: aload_0
/*     */       //   473: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   476: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   479: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   482: new akka/io/Tcp$CommandFailed
/*     */       //   485: dup
/*     */       //   486: getstatic akka/io/Tcp$ResumeWriting$.MODULE$ : Lakka/io/Tcp$ResumeWriting$;
/*     */       //   489: invokespecial <init> : (Lakka/io/Tcp$Command;)V
/*     */       //   492: aload_0
/*     */       //   493: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   496: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   499: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   504: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   507: goto -> 541
/*     */       //   510: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   513: aload_0
/*     */       //   514: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   517: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */       //   520: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   523: getstatic akka/io/Tcp$WritingResumed$.MODULE$ : Lakka/io/Tcp$WritingResumed$;
/*     */       //   526: aload_0
/*     */       //   527: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   530: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   533: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   538: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   541: astore #5
/*     */       //   543: goto -> 665
/*     */       //   546: aload_3
/*     */       //   547: instanceof akka/io/TcpConnection$UpdatePendingWrite
/*     */       //   550: ifeq -> 612
/*     */       //   553: aload_3
/*     */       //   554: checkcast akka/io/TcpConnection$UpdatePendingWrite
/*     */       //   557: astore #8
/*     */       //   559: aload #8
/*     */       //   561: invokevirtual remainingWrite : ()Lakka/io/TcpConnection$PendingWrite;
/*     */       //   564: astore #9
/*     */       //   566: aload_0
/*     */       //   567: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   570: aload #9
/*     */       //   572: putfield akka$io$TcpConnection$$pendingWrite : Lakka/io/TcpConnection$PendingWrite;
/*     */       //   575: aload_0
/*     */       //   576: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   579: invokevirtual writePending : ()Z
/*     */       //   582: ifeq -> 604
/*     */       //   585: aload_0
/*     */       //   586: getfield info$3 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   589: invokevirtual registration : ()Lakka/io/ChannelRegistration;
/*     */       //   592: iconst_4
/*     */       //   593: invokeinterface enableInterest : (I)V
/*     */       //   598: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   601: goto -> 607
/*     */       //   604: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   607: astore #5
/*     */       //   609: goto -> 665
/*     */       //   612: aload_3
/*     */       //   613: instanceof akka/io/TcpConnection$WriteFileFailed
/*     */       //   616: ifeq -> 656
/*     */       //   619: aload_3
/*     */       //   620: checkcast akka/io/TcpConnection$WriteFileFailed
/*     */       //   623: astore #10
/*     */       //   625: aload #10
/*     */       //   627: invokevirtual e : ()Ljava/io/IOException;
/*     */       //   630: astore #11
/*     */       //   632: aload_0
/*     */       //   633: getfield $outer : Lakka/io/TcpConnection;
/*     */       //   636: aload_0
/*     */       //   637: getfield info$3 : Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   640: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */       //   643: aload #11
/*     */       //   645: invokevirtual handleError : (Lakka/actor/ActorRef;Ljava/io/IOException;)V
/*     */       //   648: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   651: astore #5
/*     */       //   653: goto -> 665
/*     */       //   656: aload_2
/*     */       //   657: aload_1
/*     */       //   658: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   663: astore #5
/*     */       //   665: aload #5
/*     */       //   667: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #118	-> 0
/*     */       //   #119	-> 2
/*     */       //   #120	-> 29
/*     */       //   #121	-> 39
/*     */       //   #122	-> 50
/*     */       //   #123	-> 73
/*     */       //   #124	-> 107
/*     */       //   #122	-> 123
/*     */       //   #120	-> 129
/*     */       //   #128	-> 137
/*     */       //   #129	-> 150
/*     */       //   #130	-> 160
/*     */       //   #131	-> 190
/*     */       //   #133	-> 226
/*     */       //   #134	-> 236
/*     */       //   #135	-> 266
/*     */       //   #136	-> 296
/*     */       //   #139	-> 326
/*     */       //   #140	-> 349
/*     */       //   #129	-> 379
/*     */       //   #143	-> 384
/*     */       //   #154	-> 411
/*     */       //   #155	-> 419
/*     */       //   #156	-> 429
/*     */       //   #157	-> 469
/*     */       //   #158	-> 510
/*     */       //   #143	-> 541
/*     */       //   #160	-> 546
/*     */       //   #161	-> 566
/*     */       //   #162	-> 575
/*     */       //   #160	-> 607
/*     */       //   #164	-> 612
/*     */       //   #118	-> 656
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	668	0	this	Lakka/io/TcpConnection$$anonfun$handleWriteMessages$1;
/*     */       //   0	668	1	x6	Ljava/lang/Object;
/*     */       //   0	668	2	default	Lscala/Function1;
/*     */       //   566	102	9	remaining	Lakka/io/TcpConnection$PendingWrite;
/*     */       //   632	36	11	e	Ljava/io/IOException;
/*     */     }
/*     */     
/*     */     public final boolean isDefinedAt(Object x6) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_2
/*     */       //   2: getstatic akka/io/SelectionHandler$ChannelWritable$.MODULE$ : Lakka/io/SelectionHandler$ChannelWritable$;
/*     */       //   5: aload_2
/*     */       //   6: astore_3
/*     */       //   7: dup
/*     */       //   8: ifnonnull -> 19
/*     */       //   11: pop
/*     */       //   12: aload_3
/*     */       //   13: ifnull -> 26
/*     */       //   16: goto -> 32
/*     */       //   19: aload_3
/*     */       //   20: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   23: ifeq -> 32
/*     */       //   26: iconst_1
/*     */       //   27: istore #4
/*     */       //   29: goto -> 107
/*     */       //   32: aload_2
/*     */       //   33: instanceof akka/io/Tcp$WriteCommand
/*     */       //   36: ifeq -> 45
/*     */       //   39: iconst_1
/*     */       //   40: istore #4
/*     */       //   42: goto -> 107
/*     */       //   45: getstatic akka/io/Tcp$ResumeWriting$.MODULE$ : Lakka/io/Tcp$ResumeWriting$;
/*     */       //   48: aload_2
/*     */       //   49: astore #5
/*     */       //   51: dup
/*     */       //   52: ifnonnull -> 64
/*     */       //   55: pop
/*     */       //   56: aload #5
/*     */       //   58: ifnull -> 72
/*     */       //   61: goto -> 78
/*     */       //   64: aload #5
/*     */       //   66: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   69: ifeq -> 78
/*     */       //   72: iconst_1
/*     */       //   73: istore #4
/*     */       //   75: goto -> 107
/*     */       //   78: aload_2
/*     */       //   79: instanceof akka/io/TcpConnection$UpdatePendingWrite
/*     */       //   82: ifeq -> 91
/*     */       //   85: iconst_1
/*     */       //   86: istore #4
/*     */       //   88: goto -> 107
/*     */       //   91: aload_2
/*     */       //   92: instanceof akka/io/TcpConnection$WriteFileFailed
/*     */       //   95: ifeq -> 104
/*     */       //   98: iconst_1
/*     */       //   99: istore #4
/*     */       //   101: goto -> 107
/*     */       //   104: iconst_0
/*     */       //   105: istore #4
/*     */       //   107: iload #4
/*     */       //   109: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #118	-> 0
/*     */       //   #119	-> 2
/*     */       //   #120	-> 26
/*     */       //   #128	-> 32
/*     */       //   #129	-> 39
/*     */       //   #143	-> 45
/*     */       //   #160	-> 78
/*     */       //   #164	-> 91
/*     */       //   #118	-> 104
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	110	0	this	Lakka/io/TcpConnection$$anonfun$handleWriteMessages$1;
/*     */       //   0	110	1	x6	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public TcpConnection$$anonfun$handleWriteMessages$1(TcpConnection $outer, TcpConnection.ConnectionInfo info$3) {}
/*     */   }
/*     */   
/*     */   public void completeConnect(ChannelRegistration registration, ActorRef commander, Traversable options) {
/* 173 */     channel().socket().setTcpNoDelay(true);
/* 174 */     options.foreach((Function1)new TcpConnection$$anonfun$completeConnect$1(this));
/* 176 */     akka.actor.package$.MODULE$.actorRef2Scala(commander).$bang(new Tcp.Connected(
/* 177 */           (InetSocketAddress)channel().socket().getRemoteSocketAddress(), 
/* 178 */           (InetSocketAddress)channel().socket().getLocalSocketAddress()), self());
/* 180 */     context().setReceiveTimeout(tcp().Settings().RegisterTimeout());
/* 181 */     context().become(waitingForRegistration(registration, commander));
/*     */   }
/*     */   
/*     */   public class TcpConnection$$anonfun$completeConnect$1 extends AbstractFunction1<Inet.SocketOption, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(Inet.SocketOption x$1) {
/*     */       x$1.afterConnect(this.$outer.channel().socket());
/*     */     }
/*     */     
/*     */     public TcpConnection$$anonfun$completeConnect$1(TcpConnection $outer) {}
/*     */   }
/*     */   
/*     */   public void suspendReading(ConnectionInfo info) {
/* 185 */     this.readingSuspended = true;
/* 186 */     info.registration().disableInterest(1);
/*     */   }
/*     */   
/*     */   public void resumeReading(ConnectionInfo info) {
/* 189 */     this.readingSuspended = false;
/* 190 */     info.registration().enableInterest(1);
/*     */   }
/*     */   
/*     */   public void doRead(ConnectionInfo info, Option closeCommander) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield readingSuspended : Z
/*     */     //   4: ifne -> 385
/*     */     //   7: aload_0
/*     */     //   8: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */     //   11: invokevirtual bufferPool : ()Lakka/io/BufferPool;
/*     */     //   14: invokeinterface acquire : ()Ljava/nio/ByteBuffer;
/*     */     //   19: astore_3
/*     */     //   20: iconst_0
/*     */     //   21: istore #6
/*     */     //   23: aconst_null
/*     */     //   24: pop
/*     */     //   25: aconst_null
/*     */     //   26: astore #7
/*     */     //   28: aload_0
/*     */     //   29: aload_3
/*     */     //   30: aload_0
/*     */     //   31: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */     //   34: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */     //   37: invokevirtual ReceivedMessageSizeLimit : ()I
/*     */     //   40: aload_1
/*     */     //   41: invokespecial innerRead$1 : (Ljava/nio/ByteBuffer;ILakka/io/TcpConnection$ConnectionInfo;)Lakka/io/TcpConnection$ReadResult;
/*     */     //   44: astore #8
/*     */     //   46: getstatic akka/io/TcpConnection$AllRead$.MODULE$ : Lakka/io/TcpConnection$AllRead$;
/*     */     //   49: aload #8
/*     */     //   51: astore #9
/*     */     //   53: dup
/*     */     //   54: ifnonnull -> 66
/*     */     //   57: pop
/*     */     //   58: aload #9
/*     */     //   60: ifnull -> 74
/*     */     //   63: goto -> 105
/*     */     //   66: aload #9
/*     */     //   68: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   71: ifeq -> 105
/*     */     //   74: aload_0
/*     */     //   75: invokevirtual pullMode : ()Z
/*     */     //   78: ifeq -> 87
/*     */     //   81: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   84: goto -> 100
/*     */     //   87: aload_1
/*     */     //   88: invokevirtual registration : ()Lakka/io/ChannelRegistration;
/*     */     //   91: iconst_1
/*     */     //   92: invokeinterface enableInterest : (I)V
/*     */     //   97: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   100: astore #10
/*     */     //   102: goto -> 313
/*     */     //   105: getstatic akka/io/TcpConnection$MoreDataWaiting$.MODULE$ : Lakka/io/TcpConnection$MoreDataWaiting$;
/*     */     //   108: aload #8
/*     */     //   110: astore #11
/*     */     //   112: dup
/*     */     //   113: ifnonnull -> 125
/*     */     //   116: pop
/*     */     //   117: aload #11
/*     */     //   119: ifnull -> 133
/*     */     //   122: goto -> 176
/*     */     //   125: aload #11
/*     */     //   127: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   130: ifeq -> 176
/*     */     //   133: aload_0
/*     */     //   134: invokevirtual pullMode : ()Z
/*     */     //   137: ifeq -> 146
/*     */     //   140: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   143: goto -> 171
/*     */     //   146: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */     //   149: aload_0
/*     */     //   150: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */     //   153: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */     //   156: getstatic akka/io/SelectionHandler$ChannelReadable$.MODULE$ : Lakka/io/SelectionHandler$ChannelReadable$;
/*     */     //   159: aload_0
/*     */     //   160: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */     //   163: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   168: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   171: astore #10
/*     */     //   173: goto -> 313
/*     */     //   176: getstatic akka/io/TcpConnection$EndOfStream$.MODULE$ : Lakka/io/TcpConnection$EndOfStream$;
/*     */     //   179: aload #8
/*     */     //   181: astore #12
/*     */     //   183: dup
/*     */     //   184: ifnonnull -> 196
/*     */     //   187: pop
/*     */     //   188: aload #12
/*     */     //   190: ifnull -> 204
/*     */     //   193: goto -> 269
/*     */     //   196: aload #12
/*     */     //   198: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   201: ifeq -> 269
/*     */     //   204: iconst_1
/*     */     //   205: istore #6
/*     */     //   207: aload #8
/*     */     //   209: astore #7
/*     */     //   211: aload_0
/*     */     //   212: invokevirtual channel : ()Ljava/nio/channels/SocketChannel;
/*     */     //   215: invokevirtual socket : ()Ljava/net/Socket;
/*     */     //   218: invokevirtual isOutputShutdown : ()Z
/*     */     //   221: ifeq -> 269
/*     */     //   224: aload_0
/*     */     //   225: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */     //   228: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */     //   231: invokevirtual TraceLogging : ()Z
/*     */     //   234: ifeq -> 249
/*     */     //   237: aload_0
/*     */     //   238: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */     //   241: ldc_w 'Read returned end-of-stream, our side already closed'
/*     */     //   244: invokeinterface debug : (Ljava/lang/String;)V
/*     */     //   249: aload_0
/*     */     //   250: aload_1
/*     */     //   251: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */     //   254: aload_2
/*     */     //   255: getstatic akka/io/Tcp$ConfirmedClosed$.MODULE$ : Lakka/io/Tcp$ConfirmedClosed$;
/*     */     //   258: invokevirtual doCloseConnection : (Lakka/actor/ActorRef;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */     //   261: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   264: astore #10
/*     */     //   266: goto -> 313
/*     */     //   269: iload #6
/*     */     //   271: ifeq -> 329
/*     */     //   274: aload_0
/*     */     //   275: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */     //   278: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */     //   281: invokevirtual TraceLogging : ()Z
/*     */     //   284: ifeq -> 299
/*     */     //   287: aload_0
/*     */     //   288: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */     //   291: ldc_w 'Read returned end-of-stream, our side not yet closed'
/*     */     //   294: invokeinterface debug : (Ljava/lang/String;)V
/*     */     //   299: aload_0
/*     */     //   300: aload_1
/*     */     //   301: aload_2
/*     */     //   302: getstatic akka/io/Tcp$PeerClosed$.MODULE$ : Lakka/io/Tcp$PeerClosed$;
/*     */     //   305: invokevirtual handleClose : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */     //   308: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   311: astore #10
/*     */     //   313: aload_0
/*     */     //   314: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */     //   317: invokevirtual bufferPool : ()Lakka/io/BufferPool;
/*     */     //   320: aload_3
/*     */     //   321: invokeinterface release : (Ljava/nio/ByteBuffer;)V
/*     */     //   326: goto -> 385
/*     */     //   329: new scala/MatchError
/*     */     //   332: dup
/*     */     //   333: aload #8
/*     */     //   335: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   338: athrow
/*     */     //   339: astore #4
/*     */     //   341: aload_0
/*     */     //   342: aload_1
/*     */     //   343: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */     //   346: aload #4
/*     */     //   348: invokevirtual handleError : (Lakka/actor/ActorRef;Ljava/io/IOException;)V
/*     */     //   351: goto -> 372
/*     */     //   354: astore #5
/*     */     //   356: aload_0
/*     */     //   357: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */     //   360: invokevirtual bufferPool : ()Lakka/io/BufferPool;
/*     */     //   363: aload_3
/*     */     //   364: invokeinterface release : (Ljava/nio/ByteBuffer;)V
/*     */     //   369: aload #5
/*     */     //   371: athrow
/*     */     //   372: aload_0
/*     */     //   373: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */     //   376: invokevirtual bufferPool : ()Lakka/io/BufferPool;
/*     */     //   379: aload_3
/*     */     //   380: invokeinterface release : (Ljava/nio/ByteBuffer;)V
/*     */     //   385: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #194	-> 0
/*     */     //   #216	-> 7
/*     */     //   #222	-> 20
/*     */     //   #217	-> 28
/*     */     //   #218	-> 46
/*     */     //   #219	-> 74
/*     */     //   #220	-> 105
/*     */     //   #221	-> 133
/*     */     //   #222	-> 176
/*     */     //   #223	-> 224
/*     */     //   #224	-> 249
/*     */     //   #222	-> 264
/*     */     //   #217	-> 269
/*     */     //   #226	-> 274
/*     */     //   #227	-> 299
/*     */     //   #225	-> 311
/*     */     //   #230	-> 313
/*     */     //   #217	-> 329
/*     */     //   #229	-> 339
/*     */     //   #230	-> 354
/*     */     //   #194	-> 385
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	386	0	this	Lakka/io/TcpConnection;
/*     */     //   0	386	1	info	Lakka/io/TcpConnection$ConnectionInfo;
/*     */     //   0	386	2	closeCommander	Lscala/Option;
/*     */     //   20	366	3	buffer	Ljava/nio/ByteBuffer;
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   20	313	339	java/io/IOException
/*     */     //   20	313	354	finally
/*     */     //   329	339	339	java/io/IOException
/*     */     //   329	354	354	finally
/*     */   }
/*     */   
/*     */   private final ReadResult innerRead$1(ByteBuffer buffer, int remainingLimit, ConnectionInfo info$1) {
/*     */     // Byte code:
/*     */     //   0: iload_2
/*     */     //   1: iconst_0
/*     */     //   2: if_icmple -> 203
/*     */     //   5: aload_1
/*     */     //   6: invokevirtual clear : ()Ljava/nio/Buffer;
/*     */     //   9: pop
/*     */     //   10: getstatic scala/math/package$.MODULE$ : Lscala/math/package$;
/*     */     //   13: aload_0
/*     */     //   14: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */     //   17: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */     //   20: invokevirtual DirectBufferSize : ()I
/*     */     //   23: iload_2
/*     */     //   24: invokevirtual min : (II)I
/*     */     //   27: istore #5
/*     */     //   29: aload_1
/*     */     //   30: iload #5
/*     */     //   32: invokevirtual limit : (I)Ljava/nio/Buffer;
/*     */     //   35: pop
/*     */     //   36: aload_0
/*     */     //   37: invokevirtual channel : ()Ljava/nio/channels/SocketChannel;
/*     */     //   40: aload_1
/*     */     //   41: invokevirtual read : (Ljava/nio/ByteBuffer;)I
/*     */     //   44: istore #6
/*     */     //   46: aload_1
/*     */     //   47: invokevirtual flip : ()Ljava/nio/Buffer;
/*     */     //   50: pop
/*     */     //   51: aload_0
/*     */     //   52: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */     //   55: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */     //   58: invokevirtual TraceLogging : ()Z
/*     */     //   61: ifeq -> 81
/*     */     //   64: aload_0
/*     */     //   65: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */     //   68: ldc_w 'Read [{}] bytes.'
/*     */     //   71: iload #6
/*     */     //   73: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */     //   76: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */     //   81: iload #6
/*     */     //   83: iconst_0
/*     */     //   84: if_icmple -> 120
/*     */     //   87: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */     //   90: aload_3
/*     */     //   91: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */     //   94: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */     //   97: new akka/io/Tcp$Received
/*     */     //   100: dup
/*     */     //   101: getstatic akka/util/ByteString$.MODULE$ : Lakka/util/ByteString$;
/*     */     //   104: aload_1
/*     */     //   105: invokevirtual apply : (Ljava/nio/ByteBuffer;)Lakka/util/ByteString;
/*     */     //   108: invokespecial <init> : (Lakka/util/ByteString;)V
/*     */     //   111: aload_0
/*     */     //   112: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */     //   115: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   120: iload #6
/*     */     //   122: istore #7
/*     */     //   124: iload #5
/*     */     //   126: iload #7
/*     */     //   128: if_icmpne -> 141
/*     */     //   131: aload_1
/*     */     //   132: iload_2
/*     */     //   133: iload #5
/*     */     //   135: isub
/*     */     //   136: istore_2
/*     */     //   137: astore_1
/*     */     //   138: goto -> 0
/*     */     //   141: iload #7
/*     */     //   143: iconst_0
/*     */     //   144: if_icmplt -> 155
/*     */     //   147: getstatic akka/io/TcpConnection$AllRead$.MODULE$ : Lakka/io/TcpConnection$AllRead$;
/*     */     //   150: astore #8
/*     */     //   152: goto -> 166
/*     */     //   155: iconst_m1
/*     */     //   156: iload #7
/*     */     //   158: if_icmpne -> 171
/*     */     //   161: getstatic akka/io/TcpConnection$EndOfStream$.MODULE$ : Lakka/io/TcpConnection$EndOfStream$;
/*     */     //   164: astore #8
/*     */     //   166: aload #8
/*     */     //   168: goto -> 206
/*     */     //   171: new java/lang/IllegalStateException
/*     */     //   174: dup
/*     */     //   175: new scala/collection/mutable/StringBuilder
/*     */     //   178: dup
/*     */     //   179: invokespecial <init> : ()V
/*     */     //   182: ldc_w 'Unexpected value returned from read: '
/*     */     //   185: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   188: iload #6
/*     */     //   190: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */     //   193: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   196: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   199: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   202: athrow
/*     */     //   203: getstatic akka/io/TcpConnection$MoreDataWaiting$.MODULE$ : Lakka/io/TcpConnection$MoreDataWaiting$;
/*     */     //   206: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #196	-> 0
/*     */     //   #198	-> 5
/*     */     //   #199	-> 10
/*     */     //   #200	-> 29
/*     */     //   #201	-> 36
/*     */     //   #202	-> 46
/*     */     //   #204	-> 51
/*     */     //   #205	-> 81
/*     */     //   #207	-> 120
/*     */     //   #208	-> 124
/*     */     //   #209	-> 141
/*     */     //   #210	-> 155
/*     */     //   #207	-> 166
/*     */     //   #212	-> 171
/*     */     //   #214	-> 203
/*     */     //   #195	-> 206
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	207	0	this	Lakka/io/TcpConnection;
/*     */     //   0	207	1	buffer	Ljava/nio/ByteBuffer;
/*     */     //   0	207	2	remainingLimit	I
/*     */     //   0	207	3	info$1	Lakka/io/TcpConnection$ConnectionInfo;
/*     */     //   29	139	5	maxBufferSpace	I
/*     */     //   46	122	6	readBytes	I
/*     */   }
/*     */   
/*     */   public void doWrite(ConnectionInfo info) {
/* 233 */     this.akka$io$TcpConnection$$pendingWrite = this.akka$io$TcpConnection$$pendingWrite.doWrite(info);
/*     */   }
/*     */   
/*     */   public Tcp.ConnectionClosed closeReason() {
/* 236 */     return channel().socket().isOutputShutdown() ? Tcp.ConfirmedClosed$.MODULE$ : 
/* 237 */       Tcp.PeerClosed$.MODULE$;
/*     */   }
/*     */   
/*     */   public void handleClose(ConnectionInfo info, Option closeCommander, Tcp.ConnectionClosed closedEvent) {
/*     */     // Byte code:
/*     */     //   0: aload_3
/*     */     //   1: astore #4
/*     */     //   3: getstatic akka/io/Tcp$Aborted$.MODULE$ : Lakka/io/Tcp$Aborted$;
/*     */     //   6: aload #4
/*     */     //   8: astore #5
/*     */     //   10: dup
/*     */     //   11: ifnonnull -> 23
/*     */     //   14: pop
/*     */     //   15: aload #5
/*     */     //   17: ifnull -> 31
/*     */     //   20: goto -> 74
/*     */     //   23: aload #5
/*     */     //   25: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   28: ifeq -> 74
/*     */     //   31: aload_0
/*     */     //   32: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */     //   35: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */     //   38: invokevirtual TraceLogging : ()Z
/*     */     //   41: ifeq -> 56
/*     */     //   44: aload_0
/*     */     //   45: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */     //   48: ldc_w 'Got Abort command. RESETing connection.'
/*     */     //   51: invokeinterface debug : (Ljava/lang/String;)V
/*     */     //   56: aload_0
/*     */     //   57: aload_1
/*     */     //   58: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */     //   61: aload_2
/*     */     //   62: aload_3
/*     */     //   63: invokevirtual doCloseConnection : (Lakka/actor/ActorRef;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */     //   66: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   69: astore #6
/*     */     //   71: goto -> 360
/*     */     //   74: getstatic akka/io/Tcp$PeerClosed$.MODULE$ : Lakka/io/Tcp$PeerClosed$;
/*     */     //   77: aload #4
/*     */     //   79: astore #7
/*     */     //   81: dup
/*     */     //   82: ifnonnull -> 94
/*     */     //   85: pop
/*     */     //   86: aload #7
/*     */     //   88: ifnull -> 102
/*     */     //   91: goto -> 158
/*     */     //   94: aload #7
/*     */     //   96: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   99: ifeq -> 158
/*     */     //   102: aload_1
/*     */     //   103: invokevirtual keepOpenOnPeerClosed : ()Z
/*     */     //   106: ifeq -> 158
/*     */     //   109: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */     //   112: aload_1
/*     */     //   113: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */     //   116: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */     //   119: getstatic akka/io/Tcp$PeerClosed$.MODULE$ : Lakka/io/Tcp$PeerClosed$;
/*     */     //   122: aload_0
/*     */     //   123: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */     //   126: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   131: aload_0
/*     */     //   132: iconst_1
/*     */     //   133: putfield peerClosed : Z
/*     */     //   136: aload_0
/*     */     //   137: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */     //   140: aload_0
/*     */     //   141: aload_1
/*     */     //   142: invokevirtual peerSentEOF : (Lakka/io/TcpConnection$ConnectionInfo;)Lscala/PartialFunction;
/*     */     //   145: invokeinterface become : (Lscala/PartialFunction;)V
/*     */     //   150: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   153: astore #6
/*     */     //   155: goto -> 360
/*     */     //   158: aload_0
/*     */     //   159: invokevirtual writePending : ()Z
/*     */     //   162: ifeq -> 214
/*     */     //   165: aload_0
/*     */     //   166: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */     //   169: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */     //   172: invokevirtual TraceLogging : ()Z
/*     */     //   175: ifeq -> 190
/*     */     //   178: aload_0
/*     */     //   179: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */     //   182: ldc_w 'Got Close command but write is still pending.'
/*     */     //   185: invokeinterface debug : (Ljava/lang/String;)V
/*     */     //   190: aload_0
/*     */     //   191: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */     //   194: aload_0
/*     */     //   195: aload_1
/*     */     //   196: aload_2
/*     */     //   197: aload_3
/*     */     //   198: invokevirtual closingWithPendingWrite : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)Lscala/PartialFunction;
/*     */     //   201: invokeinterface become : (Lscala/PartialFunction;)V
/*     */     //   206: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   209: astore #6
/*     */     //   211: goto -> 360
/*     */     //   214: getstatic akka/io/Tcp$ConfirmedClosed$.MODULE$ : Lakka/io/Tcp$ConfirmedClosed$;
/*     */     //   217: aload #4
/*     */     //   219: astore #8
/*     */     //   221: dup
/*     */     //   222: ifnonnull -> 234
/*     */     //   225: pop
/*     */     //   226: aload #8
/*     */     //   228: ifnull -> 242
/*     */     //   231: goto -> 320
/*     */     //   234: aload #8
/*     */     //   236: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   239: ifeq -> 320
/*     */     //   242: aload_0
/*     */     //   243: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */     //   246: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */     //   249: invokevirtual TraceLogging : ()Z
/*     */     //   252: ifeq -> 267
/*     */     //   255: aload_0
/*     */     //   256: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */     //   259: ldc_w 'Got ConfirmedClose command, sending FIN.'
/*     */     //   262: invokeinterface debug : (Ljava/lang/String;)V
/*     */     //   267: aload_0
/*     */     //   268: getfield peerClosed : Z
/*     */     //   271: ifne -> 302
/*     */     //   274: aload_0
/*     */     //   275: invokevirtual safeShutdownOutput : ()Z
/*     */     //   278: ifeq -> 302
/*     */     //   281: aload_0
/*     */     //   282: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */     //   285: aload_0
/*     */     //   286: aload_1
/*     */     //   287: aload_2
/*     */     //   288: invokevirtual closing : (Lakka/io/TcpConnection$ConnectionInfo;Lscala/Option;)Lscala/PartialFunction;
/*     */     //   291: invokeinterface become : (Lscala/PartialFunction;)V
/*     */     //   296: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   299: goto -> 315
/*     */     //   302: aload_0
/*     */     //   303: aload_1
/*     */     //   304: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */     //   307: aload_2
/*     */     //   308: aload_3
/*     */     //   309: invokevirtual doCloseConnection : (Lakka/actor/ActorRef;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */     //   312: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   315: astore #6
/*     */     //   317: goto -> 360
/*     */     //   320: aload_0
/*     */     //   321: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */     //   324: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */     //   327: invokevirtual TraceLogging : ()Z
/*     */     //   330: ifeq -> 345
/*     */     //   333: aload_0
/*     */     //   334: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */     //   337: ldc_w 'Got Close command, closing connection.'
/*     */     //   340: invokeinterface debug : (Ljava/lang/String;)V
/*     */     //   345: aload_0
/*     */     //   346: aload_1
/*     */     //   347: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */     //   350: aload_2
/*     */     //   351: aload_3
/*     */     //   352: invokevirtual doCloseConnection : (Lakka/actor/ActorRef;Lscala/Option;Lakka/io/Tcp$ConnectionClosed;)V
/*     */     //   355: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   358: astore #6
/*     */     //   360: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #240	-> 0
/*     */     //   #241	-> 3
/*     */     //   #242	-> 31
/*     */     //   #243	-> 56
/*     */     //   #241	-> 69
/*     */     //   #244	-> 74
/*     */     //   #246	-> 109
/*     */     //   #248	-> 131
/*     */     //   #249	-> 136
/*     */     //   #244	-> 153
/*     */     //   #250	-> 158
/*     */     //   #251	-> 165
/*     */     //   #252	-> 190
/*     */     //   #250	-> 209
/*     */     //   #253	-> 214
/*     */     //   #254	-> 242
/*     */     //   #260	-> 267
/*     */     //   #262	-> 281
/*     */     //   #261	-> 302
/*     */     //   #253	-> 315
/*     */     //   #264	-> 320
/*     */     //   #265	-> 345
/*     */     //   #263	-> 358
/*     */     //   #240	-> 360
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	361	0	this	Lakka/io/TcpConnection;
/*     */     //   0	361	1	info	Lakka/io/TcpConnection$ConnectionInfo;
/*     */     //   0	361	2	closeCommander	Lscala/Option;
/*     */     //   0	361	3	closedEvent	Lakka/io/Tcp$ConnectionClosed;
/*     */   }
/*     */   
/*     */   public void doCloseConnection(ActorRef handler, Option closeCommander, Tcp.ConnectionClosed closedEvent) {
/*     */     // Byte code:
/*     */     //   0: aload_3
/*     */     //   1: getstatic akka/io/Tcp$Aborted$.MODULE$ : Lakka/io/Tcp$Aborted$;
/*     */     //   4: astore #4
/*     */     //   6: dup
/*     */     //   7: ifnonnull -> 19
/*     */     //   10: pop
/*     */     //   11: aload #4
/*     */     //   13: ifnull -> 27
/*     */     //   16: goto -> 34
/*     */     //   19: aload #4
/*     */     //   21: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   24: ifeq -> 34
/*     */     //   27: aload_0
/*     */     //   28: invokevirtual abort : ()V
/*     */     //   31: goto -> 41
/*     */     //   34: aload_0
/*     */     //   35: invokevirtual channel : ()Ljava/nio/channels/SocketChannel;
/*     */     //   38: invokevirtual close : ()V
/*     */     //   41: aload_0
/*     */     //   42: new akka/io/TcpConnection$CloseInformation
/*     */     //   45: dup
/*     */     //   46: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   49: invokevirtual Set : ()Lscala/collection/immutable/Set$;
/*     */     //   52: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   55: iconst_1
/*     */     //   56: anewarray akka/actor/ActorRef
/*     */     //   59: dup
/*     */     //   60: iconst_0
/*     */     //   61: aload_1
/*     */     //   62: aastore
/*     */     //   63: checkcast [Ljava/lang/Object;
/*     */     //   66: invokevirtual wrapRefArray : ([Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   69: invokevirtual apply : (Lscala/collection/Seq;)Lscala/collection/GenTraversable;
/*     */     //   72: checkcast scala/collection/SetLike
/*     */     //   75: getstatic scala/Option$.MODULE$ : Lscala/Option$;
/*     */     //   78: aload_2
/*     */     //   79: invokevirtual option2Iterable : (Lscala/Option;)Lscala/collection/Iterable;
/*     */     //   82: invokeinterface $plus$plus : (Lscala/collection/GenTraversableOnce;)Lscala/collection/Set;
/*     */     //   87: checkcast scala/collection/immutable/Set
/*     */     //   90: aload_3
/*     */     //   91: invokespecial <init> : (Lscala/collection/immutable/Set;Lakka/io/Tcp$Event;)V
/*     */     //   94: invokevirtual stopWith : (Lakka/io/TcpConnection$CloseInformation;)V
/*     */     //   97: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #269	-> 0
/*     */     //   #270	-> 34
/*     */     //   #271	-> 41
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	98	0	this	Lakka/io/TcpConnection;
/*     */     //   0	98	1	handler	Lakka/actor/ActorRef;
/*     */     //   0	98	2	closeCommander	Lscala/Option;
/*     */     //   0	98	3	closedEvent	Lakka/io/Tcp$ConnectionClosed;
/*     */   }
/*     */   
/*     */   public void handleError(ActorRef handler, IOException exception) {
/* 275 */     log().debug("Closing connection due to IO error {}", exception);
/* 276 */     (new ActorRef[1])[0] = handler;
/* 276 */     stopWith(new CloseInformation((Set<ActorRef>)scala.Predef$.MODULE$.Set().apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new ActorRef[1])), new Tcp.ErrorClosed(extractMsg(exception))));
/*     */   }
/*     */   
/*     */   public boolean safeShutdownOutput() {
/*     */     try {
/* 280 */       channel().socket().shutdownOutput();
/*     */     } catch (SocketException socketException) {}
/*     */     return false;
/*     */   }
/*     */   
/*     */   private String extractMsg(Throwable t) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: ifnonnull -> 10
/*     */     //   4: ldc_w 'unknown'
/*     */     //   7: goto -> 79
/*     */     //   10: aload_1
/*     */     //   11: invokevirtual getMessage : ()Ljava/lang/String;
/*     */     //   14: astore_3
/*     */     //   15: aload_3
/*     */     //   16: ifnonnull -> 25
/*     */     //   19: iconst_1
/*     */     //   20: istore #4
/*     */     //   22: goto -> 61
/*     */     //   25: ldc_w ''
/*     */     //   28: aload_3
/*     */     //   29: astore #5
/*     */     //   31: dup
/*     */     //   32: ifnonnull -> 44
/*     */     //   35: pop
/*     */     //   36: aload #5
/*     */     //   38: ifnull -> 52
/*     */     //   41: goto -> 58
/*     */     //   44: aload #5
/*     */     //   46: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   49: ifeq -> 58
/*     */     //   52: iconst_1
/*     */     //   53: istore #4
/*     */     //   55: goto -> 61
/*     */     //   58: iconst_0
/*     */     //   59: istore #4
/*     */     //   61: iload #4
/*     */     //   63: ifeq -> 74
/*     */     //   66: aload_1
/*     */     //   67: invokevirtual getCause : ()Ljava/lang/Throwable;
/*     */     //   70: astore_1
/*     */     //   71: goto -> 0
/*     */     //   74: aload_3
/*     */     //   75: astore #6
/*     */     //   77: aload #6
/*     */     //   79: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #287	-> 0
/*     */     //   #289	-> 10
/*     */     //   #290	-> 15
/*     */     //   #291	-> 74
/*     */     //   #289	-> 77
/*     */     //   #286	-> 79
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	80	0	this	Lakka/io/TcpConnection;
/*     */     //   0	80	1	t	Ljava/lang/Throwable;
/*     */   }
/*     */   
/*     */   public void abort() {
/*     */     try {
/* 296 */       channel().socket().setSoLinger(true, 0);
/*     */     } finally {
/*     */       BoxedUnit boxedUnit;
/* 296 */       Exception exception1 = null, exception2 = exception1;
/* 298 */       Option option = scala.util.control.NonFatal$.MODULE$.unapply(exception2);
/* 298 */       if (option.isEmpty())
/*     */         throw exception1; 
/* 298 */       Throwable e = (Throwable)option.get();
/* 301 */       log().debug("setSoLinger(true, 0) failed with [{}]", e);
/*     */     } 
/* 303 */     channel().close();
/*     */   }
/*     */   
/*     */   public void stopWith(CloseInformation closeInfo) {
/* 307 */     closedMessage_$eq(closeInfo);
/* 308 */     context().stop(self());
/*     */   }
/*     */   
/*     */   public void postStop() {
/* 312 */     if (channel().isOpen())
/* 313 */       abort(); 
/* 315 */     if (writePending())
/* 315 */       this.akka$io$TcpConnection$$pendingWrite.release(); 
/* 317 */     if (closedMessage() != null) {
/* 318 */       Set interestedInClose = 
/* 319 */         writePending() ? (Set)closedMessage().notificationsTo().$plus(this.akka$io$TcpConnection$$pendingWrite.commander()) : 
/* 320 */         closedMessage().notificationsTo();
/* 322 */       interestedInClose.foreach((Function1)new TcpConnection$$anonfun$postStop$1(this));
/*     */     } 
/*     */   }
/*     */   
/*     */   public class TcpConnection$$anonfun$postStop$1 extends AbstractFunction1<ActorRef, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(ActorRef x$2) {
/* 322 */       akka.actor.package$.MODULE$.actorRef2Scala(x$2).$bang(this.$outer.closedMessage().closedEvent(), this.$outer.self());
/*     */     }
/*     */     
/*     */     public TcpConnection$$anonfun$postStop$1(TcpConnection $outer) {}
/*     */   }
/*     */   
/*     */   public void postRestart(Throwable reason) {
/* 327 */     throw new IllegalStateException("Restarting not supported for connection actors.");
/*     */   }
/*     */   
/*     */   private final Tcp.WriteCommand create$default$2$1() {
/* 330 */     return Tcp.Write$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   private final PendingWrite create$1(Tcp.WriteCommand head, Tcp.WriteCommand tail, ActorRef commander$2) {
/*     */     // Byte code:
/*     */     //   0: iconst_0
/*     */     //   1: istore #5
/*     */     //   3: aconst_null
/*     */     //   4: pop
/*     */     //   5: aconst_null
/*     */     //   6: astore #6
/*     */     //   8: aload_1
/*     */     //   9: astore #7
/*     */     //   11: getstatic akka/io/Tcp$Write$.MODULE$ : Lakka/io/Tcp$Write$;
/*     */     //   14: invokevirtual empty : ()Lakka/io/Tcp$Write;
/*     */     //   17: aload #7
/*     */     //   19: astore #8
/*     */     //   21: dup
/*     */     //   22: ifnonnull -> 34
/*     */     //   25: pop
/*     */     //   26: aload #8
/*     */     //   28: ifnull -> 42
/*     */     //   31: goto -> 70
/*     */     //   34: aload #8
/*     */     //   36: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   39: ifeq -> 70
/*     */     //   42: aload_2
/*     */     //   43: getstatic akka/io/Tcp$Write$.MODULE$ : Lakka/io/Tcp$Write$;
/*     */     //   46: invokevirtual empty : ()Lakka/io/Tcp$Write;
/*     */     //   49: if_acmpne -> 60
/*     */     //   52: getstatic akka/io/TcpConnection$EmptyPendingWrite$.MODULE$ : Lakka/io/TcpConnection$EmptyPendingWrite$;
/*     */     //   55: astore #9
/*     */     //   57: goto -> 184
/*     */     //   60: aload_2
/*     */     //   61: aload_0
/*     */     //   62: invokespecial create$default$2$1 : ()Lakka/io/Tcp$WriteCommand;
/*     */     //   65: astore_2
/*     */     //   66: astore_1
/*     */     //   67: goto -> 0
/*     */     //   70: aload #7
/*     */     //   72: instanceof akka/io/Tcp$Write
/*     */     //   75: ifeq -> 125
/*     */     //   78: iconst_1
/*     */     //   79: istore #5
/*     */     //   81: aload #7
/*     */     //   83: checkcast akka/io/Tcp$Write
/*     */     //   86: astore #6
/*     */     //   88: aload #6
/*     */     //   90: invokevirtual data : ()Lakka/util/ByteString;
/*     */     //   93: astore #10
/*     */     //   95: aload #6
/*     */     //   97: invokevirtual ack : ()Lakka/io/Tcp$Event;
/*     */     //   100: astore #11
/*     */     //   102: aload #10
/*     */     //   104: invokevirtual nonEmpty : ()Z
/*     */     //   107: ifeq -> 125
/*     */     //   110: aload_0
/*     */     //   111: aload_3
/*     */     //   112: aload #10
/*     */     //   114: aload #11
/*     */     //   116: aload_2
/*     */     //   117: invokevirtual PendingBufferWrite : (Lakka/actor/ActorRef;Lakka/util/ByteString;Lakka/io/Tcp$Event;Lakka/io/Tcp$WriteCommand;)Lakka/io/TcpConnection$PendingBufferWrite;
/*     */     //   120: astore #9
/*     */     //   122: goto -> 184
/*     */     //   125: aload #7
/*     */     //   127: instanceof akka/io/Tcp$WriteFile
/*     */     //   130: ifeq -> 187
/*     */     //   133: aload #7
/*     */     //   135: checkcast akka/io/Tcp$WriteFile
/*     */     //   138: astore #12
/*     */     //   140: aload #12
/*     */     //   142: invokevirtual filePath : ()Ljava/lang/String;
/*     */     //   145: astore #13
/*     */     //   147: aload #12
/*     */     //   149: invokevirtual position : ()J
/*     */     //   152: lstore #14
/*     */     //   154: aload #12
/*     */     //   156: invokevirtual count : ()J
/*     */     //   159: lstore #16
/*     */     //   161: aload #12
/*     */     //   163: invokevirtual ack : ()Lakka/io/Tcp$Event;
/*     */     //   166: astore #18
/*     */     //   168: aload_0
/*     */     //   169: aload_3
/*     */     //   170: aload #13
/*     */     //   172: lload #14
/*     */     //   174: lload #16
/*     */     //   176: aload #18
/*     */     //   178: aload_2
/*     */     //   179: invokevirtual PendingWriteFile : (Lakka/actor/ActorRef;Ljava/lang/String;JJLakka/io/Tcp$Event;Lakka/io/Tcp$WriteCommand;)Lakka/io/TcpConnection$PendingWriteFile;
/*     */     //   182: astore #9
/*     */     //   184: aload #9
/*     */     //   186: areturn
/*     */     //   187: aload #7
/*     */     //   189: instanceof akka/io/Tcp$CompoundWrite
/*     */     //   192: ifeq -> 225
/*     */     //   195: aload #7
/*     */     //   197: checkcast akka/io/Tcp$CompoundWrite
/*     */     //   200: astore #19
/*     */     //   202: aload #19
/*     */     //   204: invokevirtual head : ()Lakka/io/Tcp$SimpleWriteCommand;
/*     */     //   207: astore #20
/*     */     //   209: aload #19
/*     */     //   211: invokevirtual tailCommand : ()Lakka/io/Tcp$WriteCommand;
/*     */     //   214: astore #21
/*     */     //   216: aload #20
/*     */     //   218: aload #21
/*     */     //   220: astore_2
/*     */     //   221: astore_1
/*     */     //   222: goto -> 0
/*     */     //   225: iload #5
/*     */     //   227: ifeq -> 273
/*     */     //   230: aload #6
/*     */     //   232: invokevirtual ack : ()Lakka/io/Tcp$Event;
/*     */     //   235: astore #22
/*     */     //   237: aload #6
/*     */     //   239: invokevirtual wantsAck : ()Z
/*     */     //   242: ifeq -> 263
/*     */     //   245: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */     //   248: aload_3
/*     */     //   249: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */     //   252: aload #22
/*     */     //   254: aload_0
/*     */     //   255: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */     //   258: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */     //   263: aload_2
/*     */     //   264: aload_0
/*     */     //   265: invokespecial create$default$2$1 : ()Lakka/io/Tcp$WriteCommand;
/*     */     //   268: astore_2
/*     */     //   269: astore_1
/*     */     //   270: goto -> 0
/*     */     //   273: new scala/MatchError
/*     */     //   276: dup
/*     */     //   277: aload #7
/*     */     //   279: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   282: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #333	-> 0
/*     */     //   #331	-> 8
/*     */     //   #332	-> 11
/*     */     //   #333	-> 70
/*     */     //   #334	-> 125
/*     */     //   #331	-> 184
/*     */     //   #335	-> 187
/*     */     //   #331	-> 225
/*     */     //   #336	-> 230
/*     */     //   #337	-> 237
/*     */     //   #338	-> 263
/*     */     //   #331	-> 273
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	283	0	this	Lakka/io/TcpConnection;
/*     */     //   0	283	1	head	Lakka/io/Tcp$WriteCommand;
/*     */     //   0	283	2	tail	Lakka/io/Tcp$WriteCommand;
/*     */     //   0	283	3	commander$2	Lakka/actor/ActorRef;
/*     */     //   95	188	10	data	Lakka/util/ByteString;
/*     */     //   102	181	11	ack	Lakka/io/Tcp$Event;
/*     */     //   147	136	13	path	Ljava/lang/String;
/*     */     //   154	129	14	offset	J
/*     */     //   161	122	16	count	J
/*     */     //   168	115	18	ack	Lakka/io/Tcp$Event;
/*     */     //   209	74	20	h	Lakka/io/Tcp$SimpleWriteCommand;
/*     */     //   216	67	21	t	Lakka/io/Tcp$WriteCommand;
/*     */     //   237	46	22	ack	Lakka/io/Tcp$Event;
/*     */   }
/*     */   
/*     */   public PendingWrite PendingWrite(ActorRef commander, Tcp.WriteCommand write) {
/* 340 */     return create$1(write, create$default$2$1(), commander);
/*     */   }
/*     */   
/*     */   public PendingBufferWrite PendingBufferWrite(ActorRef commander, ByteString data, Tcp.Event ack, Tcp.WriteCommand tail) {
/* 344 */     ByteBuffer buffer = tcp().bufferPool().acquire();
/*     */     try {
/* 348 */       return new PendingBufferWrite(this, commander, data.drop(copied), ack, buffer, tail);
/*     */     } finally {
/*     */       Exception exception1 = null, exception2 = exception1;
/* 350 */       Option option = scala.util.control.NonFatal$.MODULE$.unapply(exception2);
/* 350 */       if (option.isEmpty())
/*     */         throw exception1; 
/* 350 */       Throwable e = (Throwable)option.get();
/* 351 */       tcp().bufferPool().release(buffer);
/*     */     } 
/*     */   }
/*     */   
/*     */   public class PendingBufferWrite extends PendingWrite {
/*     */     private final ActorRef commander;
/*     */     
/*     */     private final ByteString remainingData;
/*     */     
/*     */     private final Object ack;
/*     */     
/*     */     private final ByteBuffer buffer;
/*     */     
/*     */     private final Tcp.WriteCommand tail;
/*     */     
/*     */     public ActorRef commander() {
/* 357 */       return this.commander;
/*     */     }
/*     */     
/*     */     public PendingBufferWrite(TcpConnection $outer, ActorRef commander, ByteString remainingData, Object ack, ByteBuffer buffer, Tcp.WriteCommand tail) {}
/*     */     
/*     */     private final TcpConnection.PendingWrite writeToChannel$1(ByteString data) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: invokevirtual akka$io$TcpConnection$PendingBufferWrite$$$outer : ()Lakka/io/TcpConnection;
/*     */       //   4: invokevirtual channel : ()Ljava/nio/channels/SocketChannel;
/*     */       //   7: aload_0
/*     */       //   8: getfield buffer : Ljava/nio/ByteBuffer;
/*     */       //   11: invokevirtual write : (Ljava/nio/ByteBuffer;)I
/*     */       //   14: istore_3
/*     */       //   15: aload_0
/*     */       //   16: invokevirtual akka$io$TcpConnection$PendingBufferWrite$$$outer : ()Lakka/io/TcpConnection;
/*     */       //   19: invokevirtual tcp : ()Lakka/io/TcpExt;
/*     */       //   22: invokevirtual Settings : ()Lakka/io/TcpExt$Settings;
/*     */       //   25: invokevirtual TraceLogging : ()Z
/*     */       //   28: ifeq -> 49
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual akka$io$TcpConnection$PendingBufferWrite$$$outer : ()Lakka/io/TcpConnection;
/*     */       //   35: invokevirtual log : ()Lakka/event/LoggingAdapter;
/*     */       //   38: ldc 'Wrote [{}] bytes to channel'
/*     */       //   40: iload_3
/*     */       //   41: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */       //   44: invokeinterface debug : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */       //   49: aload_0
/*     */       //   50: getfield buffer : Ljava/nio/ByteBuffer;
/*     */       //   53: invokevirtual hasRemaining : ()Z
/*     */       //   56: ifeq -> 102
/*     */       //   59: aload_1
/*     */       //   60: aload_0
/*     */       //   61: getfield remainingData : Lakka/util/ByteString;
/*     */       //   64: if_acmpne -> 71
/*     */       //   67: aload_0
/*     */       //   68: goto -> 200
/*     */       //   71: new akka/io/TcpConnection$PendingBufferWrite
/*     */       //   74: dup
/*     */       //   75: aload_0
/*     */       //   76: invokevirtual akka$io$TcpConnection$PendingBufferWrite$$$outer : ()Lakka/io/TcpConnection;
/*     */       //   79: aload_0
/*     */       //   80: invokevirtual commander : ()Lakka/actor/ActorRef;
/*     */       //   83: aload_1
/*     */       //   84: aload_0
/*     */       //   85: getfield ack : Ljava/lang/Object;
/*     */       //   88: aload_0
/*     */       //   89: getfield buffer : Ljava/nio/ByteBuffer;
/*     */       //   92: aload_0
/*     */       //   93: getfield tail : Lakka/io/Tcp$WriteCommand;
/*     */       //   96: invokespecial <init> : (Lakka/io/TcpConnection;Lakka/actor/ActorRef;Lakka/util/ByteString;Ljava/lang/Object;Ljava/nio/ByteBuffer;Lakka/io/Tcp$WriteCommand;)V
/*     */       //   99: goto -> 200
/*     */       //   102: aload_1
/*     */       //   103: invokevirtual nonEmpty : ()Z
/*     */       //   106: ifeq -> 145
/*     */       //   109: aload_0
/*     */       //   110: getfield buffer : Ljava/nio/ByteBuffer;
/*     */       //   113: invokevirtual clear : ()Ljava/nio/Buffer;
/*     */       //   116: pop
/*     */       //   117: aload_1
/*     */       //   118: aload_0
/*     */       //   119: getfield buffer : Ljava/nio/ByteBuffer;
/*     */       //   122: invokevirtual copyToBuffer : (Ljava/nio/ByteBuffer;)I
/*     */       //   125: istore #4
/*     */       //   127: aload_0
/*     */       //   128: getfield buffer : Ljava/nio/ByteBuffer;
/*     */       //   131: invokevirtual flip : ()Ljava/nio/Buffer;
/*     */       //   134: pop
/*     */       //   135: aload_1
/*     */       //   136: iload #4
/*     */       //   138: invokevirtual drop : (I)Lakka/util/ByteString;
/*     */       //   141: astore_1
/*     */       //   142: goto -> 0
/*     */       //   145: aload_0
/*     */       //   146: getfield ack : Ljava/lang/Object;
/*     */       //   149: instanceof akka/io/Tcp$NoAck
/*     */       //   152: ifne -> 181
/*     */       //   155: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */       //   158: aload_0
/*     */       //   159: invokevirtual commander : ()Lakka/actor/ActorRef;
/*     */       //   162: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */       //   165: aload_0
/*     */       //   166: getfield ack : Ljava/lang/Object;
/*     */       //   169: aload_0
/*     */       //   170: invokevirtual akka$io$TcpConnection$PendingBufferWrite$$$outer : ()Lakka/io/TcpConnection;
/*     */       //   173: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */       //   176: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */       //   181: aload_0
/*     */       //   182: invokevirtual release : ()V
/*     */       //   185: aload_0
/*     */       //   186: invokevirtual akka$io$TcpConnection$PendingBufferWrite$$$outer : ()Lakka/io/TcpConnection;
/*     */       //   189: aload_0
/*     */       //   190: invokevirtual commander : ()Lakka/actor/ActorRef;
/*     */       //   193: aload_0
/*     */       //   194: getfield tail : Lakka/io/Tcp$WriteCommand;
/*     */       //   197: invokevirtual PendingWrite : (Lakka/actor/ActorRef;Lakka/io/Tcp$WriteCommand;)Lakka/io/TcpConnection$PendingWrite;
/*     */       //   200: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #365	-> 0
/*     */       //   #366	-> 15
/*     */       //   #367	-> 49
/*     */       //   #369	-> 59
/*     */       //   #370	-> 71
/*     */       //   #372	-> 102
/*     */       //   #373	-> 109
/*     */       //   #374	-> 117
/*     */       //   #375	-> 127
/*     */       //   #376	-> 135
/*     */       //   #379	-> 145
/*     */       //   #380	-> 181
/*     */       //   #381	-> 185
/*     */       //   #364	-> 200
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	201	0	this	Lakka/io/TcpConnection$PendingBufferWrite;
/*     */       //   0	201	1	data	Lakka/util/ByteString;
/*     */       //   15	186	3	writtenBytes	I
/*     */       //   127	74	4	copied	I
/*     */     }
/*     */     
/*     */     public TcpConnection.PendingWrite doWrite(TcpConnection.ConnectionInfo info) {
/*     */       try {
/* 385 */         TcpConnection.PendingWrite next = writeToChannel$1(this.remainingData);
/* 386 */         if (next != TcpConnection.EmptyPendingWrite$.MODULE$)
/* 386 */           info.registration().enableInterest(4); 
/*     */       } catch (IOException iOException) {
/*     */         akka$io$TcpConnection$PendingBufferWrite$$$outer().handleError(info.handler(), iOException);
/*     */       } 
/*     */       return this;
/*     */     }
/*     */     
/*     */     public void release() {
/* 391 */       akka$io$TcpConnection$PendingBufferWrite$$$outer().tcp().bufferPool().release(this.buffer);
/*     */     }
/*     */   }
/*     */   
/*     */   public PendingWriteFile PendingWriteFile(ActorRef commander, String filePath, long offset, long count, Tcp.Event ack, Tcp.WriteCommand tail) {
/* 396 */     return new PendingWriteFile(this, commander, (new FileInputStream(filePath)).getChannel(), offset, count, ack, tail);
/*     */   }
/*     */   
/*     */   public class PendingWriteFile extends PendingWrite implements Runnable {
/*     */     private final ActorRef commander;
/*     */     
/*     */     private final FileChannel fileChannel;
/*     */     
/*     */     private final long offset;
/*     */     
/*     */     private final long remaining;
/*     */     
/*     */     private final Tcp.Event ack;
/*     */     
/*     */     private final Tcp.WriteCommand tail;
/*     */     
/*     */     public ActorRef commander() {
/* 399 */       return this.commander;
/*     */     }
/*     */     
/*     */     public PendingWriteFile(TcpConnection $outer, ActorRef commander, FileChannel fileChannel, long offset, long remaining, Tcp.Event ack, Tcp.WriteCommand tail) {}
/*     */     
/*     */     public TcpConnection.PendingWrite doWrite(TcpConnection.ConnectionInfo info) {
/* 407 */       akka$io$TcpConnection$PendingWriteFile$$$outer().tcp().fileIoDispatcher().execute(this);
/* 408 */       return this;
/*     */     }
/*     */     
/*     */     public void release() {
/* 411 */       this.fileChannel.close();
/*     */     }
/*     */     
/*     */     public void run() {
/*     */       try {
/* 415 */         long toWrite = scala.math.package$.MODULE$.min(this.remaining, akka$io$TcpConnection$PendingWriteFile$$$outer().tcp().Settings().TransferToLimit());
/* 416 */         long written = this.fileChannel.transferTo(this.offset, toWrite, akka$io$TcpConnection$PendingWriteFile$$$outer().channel());
/* 418 */         if (written < this.remaining) {
/* 419 */           PendingWriteFile updated = new PendingWriteFile(akka$io$TcpConnection$PendingWriteFile$$$outer(), commander(), this.fileChannel, this.offset + written, this.remaining - written, this.ack, this.tail);
/* 420 */           akka.actor.package$.MODULE$.actorRef2Scala(akka$io$TcpConnection$PendingWriteFile$$$outer().self()).$bang(new TcpConnection.UpdatePendingWrite(updated), akka$io$TcpConnection$PendingWriteFile$$$outer().self());
/*     */         } else {
/* 423 */           if (!(this.ack instanceof Tcp.NoAck))
/* 423 */             akka.actor.package$.MODULE$.actorRef2Scala(commander()).$bang(this.ack, akka$io$TcpConnection$PendingWriteFile$$$outer().self()); 
/* 424 */           release();
/* 425 */           akka.actor.package$.MODULE$.actorRef2Scala(akka$io$TcpConnection$PendingWriteFile$$$outer().self()).$bang(new TcpConnection.UpdatePendingWrite(akka$io$TcpConnection$PendingWriteFile$$$outer().PendingWrite(commander(), this.tail)), akka$io$TcpConnection$PendingWriteFile$$$outer().self());
/*     */         } 
/*     */       } catch (IOException iOException) {
/*     */         akka.actor.package$.MODULE$.actorRef2Scala(akka$io$TcpConnection$PendingWriteFile$$$outer().self()).$bang(new TcpConnection.WriteFileFailed(iOException), akka$io$TcpConnection$PendingWriteFile$$$outer().self());
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static class EndOfStream$ implements ReadResult {
/*     */     public static final EndOfStream$ MODULE$;
/*     */     
/*     */     public EndOfStream$() {
/* 438 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class AllRead$ implements ReadResult {
/*     */     public static final AllRead$ MODULE$;
/*     */     
/*     */     public AllRead$() {
/* 439 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class MoreDataWaiting$ implements ReadResult {
/*     */     public static final MoreDataWaiting$ MODULE$;
/*     */     
/*     */     public MoreDataWaiting$() {
/* 440 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class CloseInformation implements Product, Serializable {
/*     */     private final Set<ActorRef> notificationsTo;
/*     */     
/*     */     private final Tcp.Event closedEvent;
/*     */     
/*     */     public Set<ActorRef> notificationsTo() {
/* 446 */       return this.notificationsTo;
/*     */     }
/*     */     
/*     */     public Tcp.Event closedEvent() {
/* 446 */       return this.closedEvent;
/*     */     }
/*     */     
/*     */     public CloseInformation copy(Set<ActorRef> notificationsTo, Tcp.Event closedEvent) {
/* 446 */       return new CloseInformation(notificationsTo, closedEvent);
/*     */     }
/*     */     
/*     */     public Set<ActorRef> copy$default$1() {
/* 446 */       return notificationsTo();
/*     */     }
/*     */     
/*     */     public Tcp.Event copy$default$2() {
/* 446 */       return closedEvent();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/* 446 */       return "CloseInformation";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 446 */       return 2;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 446 */       int i = x$1;
/* 446 */       switch (i) {
/*     */         default:
/* 446 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 1:
/*     */         
/*     */         case 0:
/*     */           break;
/*     */       } 
/* 446 */       return notificationsTo();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 446 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 446 */       return x$1 instanceof CloseInformation;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 446 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 446 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 112
/*     */       //   5: aload_1
/*     */       //   6: astore_2
/*     */       //   7: aload_2
/*     */       //   8: instanceof akka/io/TcpConnection$CloseInformation
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 116
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/io/TcpConnection$CloseInformation
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual notificationsTo : ()Lscala/collection/immutable/Set;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual notificationsTo : ()Lscala/collection/immutable/Set;
/*     */       //   40: astore #5
/*     */       //   42: dup
/*     */       //   43: ifnonnull -> 55
/*     */       //   46: pop
/*     */       //   47: aload #5
/*     */       //   49: ifnull -> 63
/*     */       //   52: goto -> 108
/*     */       //   55: aload #5
/*     */       //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   60: ifeq -> 108
/*     */       //   63: aload_0
/*     */       //   64: invokevirtual closedEvent : ()Lakka/io/Tcp$Event;
/*     */       //   67: aload #4
/*     */       //   69: invokevirtual closedEvent : ()Lakka/io/Tcp$Event;
/*     */       //   72: astore #6
/*     */       //   74: dup
/*     */       //   75: ifnonnull -> 87
/*     */       //   78: pop
/*     */       //   79: aload #6
/*     */       //   81: ifnull -> 95
/*     */       //   84: goto -> 108
/*     */       //   87: aload #6
/*     */       //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   92: ifeq -> 108
/*     */       //   95: aload #4
/*     */       //   97: aload_0
/*     */       //   98: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   101: ifeq -> 108
/*     */       //   104: iconst_1
/*     */       //   105: goto -> 109
/*     */       //   108: iconst_0
/*     */       //   109: ifeq -> 116
/*     */       //   112: iconst_1
/*     */       //   113: goto -> 117
/*     */       //   116: iconst_0
/*     */       //   117: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #446	-> 0
/*     */       //   #63	-> 14
/*     */       //   #446	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	118	0	this	Lakka/io/TcpConnection$CloseInformation;
/*     */       //   0	118	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public CloseInformation(Set<ActorRef> notificationsTo, Tcp.Event closedEvent) {
/* 446 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class CloseInformation$ extends AbstractFunction2<Set<ActorRef>, Tcp.Event, CloseInformation> implements Serializable {
/*     */     public static final CloseInformation$ MODULE$;
/*     */     
/*     */     public final String toString() {
/* 446 */       return "CloseInformation";
/*     */     }
/*     */     
/*     */     public TcpConnection.CloseInformation apply(Set<ActorRef> notificationsTo, Tcp.Event closedEvent) {
/* 446 */       return new TcpConnection.CloseInformation(notificationsTo, closedEvent);
/*     */     }
/*     */     
/*     */     public Option<Tuple2<Set<ActorRef>, Tcp.Event>> unapply(TcpConnection.CloseInformation x$0) {
/* 446 */       return (x$0 == null) ? (Option<Tuple2<Set<ActorRef>, Tcp.Event>>)scala.None$.MODULE$ : (Option<Tuple2<Set<ActorRef>, Tcp.Event>>)new Some(new Tuple2(x$0.notificationsTo(), x$0.closedEvent()));
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 446 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public CloseInformation$() {
/* 446 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ConnectionInfo implements Product, Serializable {
/*     */     private final ChannelRegistration registration;
/*     */     
/*     */     private final ActorRef handler;
/*     */     
/*     */     private final boolean keepOpenOnPeerClosed;
/*     */     
/*     */     private final boolean useResumeWriting;
/*     */     
/*     */     public ChannelRegistration registration() {
/* 451 */       return this.registration;
/*     */     }
/*     */     
/*     */     public ConnectionInfo copy(ChannelRegistration registration, ActorRef handler, boolean keepOpenOnPeerClosed, boolean useResumeWriting) {
/* 451 */       return new ConnectionInfo(registration, 
/* 452 */           handler, 
/* 453 */           keepOpenOnPeerClosed, 
/* 454 */           useResumeWriting);
/*     */     }
/*     */     
/*     */     public ChannelRegistration copy$default$1() {
/*     */       return registration();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*     */       return "ConnectionInfo";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*     */       return 4;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*     */       int i = x$1;
/*     */       switch (i) {
/*     */         default:
/*     */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 3:
/*     */         
/*     */         case 2:
/*     */         
/*     */         case 1:
/*     */         
/*     */         case 0:
/*     */           break;
/*     */       } 
/*     */       return registration();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*     */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*     */       return x$1 instanceof ConnectionInfo;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*     */       int i = -889275714;
/*     */       i = Statics.mix(i, Statics.anyHash(registration()));
/*     */       i = Statics.mix(i, Statics.anyHash(handler()));
/*     */       i = Statics.mix(i, keepOpenOnPeerClosed() ? 1231 : 1237);
/*     */       i = Statics.mix(i, useResumeWriting() ? 1231 : 1237);
/*     */       return Statics.finalizeHash(i, 4);
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 136
/*     */       //   5: aload_1
/*     */       //   6: astore_2
/*     */       //   7: aload_2
/*     */       //   8: instanceof akka/io/TcpConnection$ConnectionInfo
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 140
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/io/TcpConnection$ConnectionInfo
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual registration : ()Lakka/io/ChannelRegistration;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual registration : ()Lakka/io/ChannelRegistration;
/*     */       //   40: astore #5
/*     */       //   42: dup
/*     */       //   43: ifnonnull -> 55
/*     */       //   46: pop
/*     */       //   47: aload #5
/*     */       //   49: ifnull -> 63
/*     */       //   52: goto -> 132
/*     */       //   55: aload #5
/*     */       //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   60: ifeq -> 132
/*     */       //   63: aload_0
/*     */       //   64: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */       //   67: aload #4
/*     */       //   69: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */       //   72: astore #6
/*     */       //   74: dup
/*     */       //   75: ifnonnull -> 87
/*     */       //   78: pop
/*     */       //   79: aload #6
/*     */       //   81: ifnull -> 95
/*     */       //   84: goto -> 132
/*     */       //   87: aload #6
/*     */       //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   92: ifeq -> 132
/*     */       //   95: aload_0
/*     */       //   96: invokevirtual keepOpenOnPeerClosed : ()Z
/*     */       //   99: aload #4
/*     */       //   101: invokevirtual keepOpenOnPeerClosed : ()Z
/*     */       //   104: if_icmpne -> 132
/*     */       //   107: aload_0
/*     */       //   108: invokevirtual useResumeWriting : ()Z
/*     */       //   111: aload #4
/*     */       //   113: invokevirtual useResumeWriting : ()Z
/*     */       //   116: if_icmpne -> 132
/*     */       //   119: aload #4
/*     */       //   121: aload_0
/*     */       //   122: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   125: ifeq -> 132
/*     */       //   128: iconst_1
/*     */       //   129: goto -> 133
/*     */       //   132: iconst_0
/*     */       //   133: ifeq -> 140
/*     */       //   136: iconst_1
/*     */       //   137: goto -> 141
/*     */       //   140: iconst_0
/*     */       //   141: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #451	-> 0
/*     */       //   #63	-> 14
/*     */       //   #451	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	142	0	this	Lakka/io/TcpConnection$ConnectionInfo;
/*     */       //   0	142	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public ConnectionInfo(ChannelRegistration registration, ActorRef handler, boolean keepOpenOnPeerClosed, boolean useResumeWriting) {
/*     */       Product.class.$init$(this);
/*     */     }
/*     */     
/*     */     public ActorRef handler() {
/*     */       return this.handler;
/*     */     }
/*     */     
/*     */     public ActorRef copy$default$2() {
/*     */       return handler();
/*     */     }
/*     */     
/*     */     public boolean keepOpenOnPeerClosed() {
/*     */       return this.keepOpenOnPeerClosed;
/*     */     }
/*     */     
/*     */     public boolean copy$default$3() {
/*     */       return keepOpenOnPeerClosed();
/*     */     }
/*     */     
/*     */     public boolean useResumeWriting() {
/* 454 */       return this.useResumeWriting;
/*     */     }
/*     */     
/*     */     public boolean copy$default$4() {
/* 454 */       return useResumeWriting();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ConnectionInfo$ extends AbstractFunction4<ChannelRegistration, ActorRef, Object, Object, ConnectionInfo> implements Serializable {
/*     */     public static final ConnectionInfo$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*     */       return "ConnectionInfo";
/*     */     }
/*     */     
/*     */     public TcpConnection.ConnectionInfo apply(ChannelRegistration registration, ActorRef handler, boolean keepOpenOnPeerClosed, boolean useResumeWriting) {
/*     */       return new TcpConnection.ConnectionInfo(registration, handler, keepOpenOnPeerClosed, useResumeWriting);
/*     */     }
/*     */     
/*     */     public Option<Tuple4<ChannelRegistration, ActorRef, Object, Object>> unapply(TcpConnection.ConnectionInfo x$0) {
/*     */       return (x$0 == null) ? (Option<Tuple4<ChannelRegistration, ActorRef, Object, Object>>)scala.None$.MODULE$ : (Option<Tuple4<ChannelRegistration, ActorRef, Object, Object>>)new Some(new Tuple4(x$0.registration(), x$0.handler(), BoxesRunTime.boxToBoolean(x$0.keepOpenOnPeerClosed()), BoxesRunTime.boxToBoolean(x$0.useResumeWriting())));
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return MODULE$;
/*     */     }
/*     */     
/*     */     public ConnectionInfo$() {
/*     */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class UpdatePendingWrite implements NoSerializationVerificationNeeded, Product, Serializable {
/*     */     private final TcpConnection.PendingWrite remainingWrite;
/*     */     
/*     */     public TcpConnection.PendingWrite remainingWrite() {
/* 458 */       return this.remainingWrite;
/*     */     }
/*     */     
/*     */     public UpdatePendingWrite copy(TcpConnection.PendingWrite remainingWrite) {
/* 458 */       return new UpdatePendingWrite(remainingWrite);
/*     */     }
/*     */     
/*     */     public TcpConnection.PendingWrite copy$default$1() {
/* 458 */       return remainingWrite();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/* 458 */       return "UpdatePendingWrite";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 458 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 458 */       int i = x$1;
/* 458 */       switch (i) {
/*     */         default:
/* 458 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/* 458 */       return remainingWrite();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 458 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 458 */       return x$1 instanceof UpdatePendingWrite;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 458 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 458 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 80
/*     */       //   5: aload_1
/*     */       //   6: astore_2
/*     */       //   7: aload_2
/*     */       //   8: instanceof akka/io/TcpConnection$UpdatePendingWrite
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 84
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/io/TcpConnection$UpdatePendingWrite
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual remainingWrite : ()Lakka/io/TcpConnection$PendingWrite;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual remainingWrite : ()Lakka/io/TcpConnection$PendingWrite;
/*     */       //   40: astore #5
/*     */       //   42: dup
/*     */       //   43: ifnonnull -> 55
/*     */       //   46: pop
/*     */       //   47: aload #5
/*     */       //   49: ifnull -> 63
/*     */       //   52: goto -> 76
/*     */       //   55: aload #5
/*     */       //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   60: ifeq -> 76
/*     */       //   63: aload #4
/*     */       //   65: aload_0
/*     */       //   66: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   69: ifeq -> 76
/*     */       //   72: iconst_1
/*     */       //   73: goto -> 77
/*     */       //   76: iconst_0
/*     */       //   77: ifeq -> 84
/*     */       //   80: iconst_1
/*     */       //   81: goto -> 85
/*     */       //   84: iconst_0
/*     */       //   85: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #458	-> 0
/*     */       //   #63	-> 14
/*     */       //   #458	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	86	0	this	Lakka/io/TcpConnection$UpdatePendingWrite;
/*     */       //   0	86	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public UpdatePendingWrite(TcpConnection.PendingWrite remainingWrite) {
/* 458 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class UpdatePendingWrite$ extends AbstractFunction1<PendingWrite, UpdatePendingWrite> implements Serializable {
/*     */     public static final UpdatePendingWrite$ MODULE$;
/*     */     
/*     */     public final String toString() {
/* 458 */       return "UpdatePendingWrite";
/*     */     }
/*     */     
/*     */     public TcpConnection.UpdatePendingWrite apply(TcpConnection.PendingWrite remainingWrite) {
/* 458 */       return new TcpConnection.UpdatePendingWrite(remainingWrite);
/*     */     }
/*     */     
/*     */     public Option<TcpConnection.PendingWrite> unapply(TcpConnection.UpdatePendingWrite x$0) {
/* 458 */       return (x$0 == null) ? (Option<TcpConnection.PendingWrite>)scala.None$.MODULE$ : (Option<TcpConnection.PendingWrite>)new Some(x$0.remainingWrite());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 458 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public UpdatePendingWrite$() {
/* 458 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class WriteFileFailed implements Product, Serializable {
/*     */     private final IOException e;
/*     */     
/*     */     public IOException e() {
/* 459 */       return this.e;
/*     */     }
/*     */     
/*     */     public WriteFileFailed copy(IOException e) {
/* 459 */       return new WriteFileFailed(e);
/*     */     }
/*     */     
/*     */     public IOException copy$default$1() {
/* 459 */       return e();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/* 459 */       return "WriteFileFailed";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 459 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 459 */       int i = x$1;
/* 459 */       switch (i) {
/*     */         default:
/* 459 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/* 459 */       return e();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 459 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 459 */       return x$1 instanceof WriteFileFailed;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 459 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 459 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 80
/*     */       //   5: aload_1
/*     */       //   6: astore_2
/*     */       //   7: aload_2
/*     */       //   8: instanceof akka/io/TcpConnection$WriteFileFailed
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 84
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/io/TcpConnection$WriteFileFailed
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual e : ()Ljava/io/IOException;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual e : ()Ljava/io/IOException;
/*     */       //   40: astore #5
/*     */       //   42: dup
/*     */       //   43: ifnonnull -> 55
/*     */       //   46: pop
/*     */       //   47: aload #5
/*     */       //   49: ifnull -> 63
/*     */       //   52: goto -> 76
/*     */       //   55: aload #5
/*     */       //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   60: ifeq -> 76
/*     */       //   63: aload #4
/*     */       //   65: aload_0
/*     */       //   66: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   69: ifeq -> 76
/*     */       //   72: iconst_1
/*     */       //   73: goto -> 77
/*     */       //   76: iconst_0
/*     */       //   77: ifeq -> 84
/*     */       //   80: iconst_1
/*     */       //   81: goto -> 85
/*     */       //   84: iconst_0
/*     */       //   85: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #459	-> 0
/*     */       //   #63	-> 14
/*     */       //   #459	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	86	0	this	Lakka/io/TcpConnection$WriteFileFailed;
/*     */       //   0	86	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public WriteFileFailed(IOException e) {
/* 459 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class WriteFileFailed$ extends AbstractFunction1<IOException, WriteFileFailed> implements Serializable {
/*     */     public static final WriteFileFailed$ MODULE$;
/*     */     
/*     */     public final String toString() {
/* 459 */       return "WriteFileFailed";
/*     */     }
/*     */     
/*     */     public TcpConnection.WriteFileFailed apply(IOException e) {
/* 459 */       return new TcpConnection.WriteFileFailed(e);
/*     */     }
/*     */     
/*     */     public Option<IOException> unapply(TcpConnection.WriteFileFailed x$0) {
/* 459 */       return (x$0 == null) ? (Option<IOException>)scala.None$.MODULE$ : (Option<IOException>)new Some(x$0.e());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 459 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public WriteFileFailed$() {
/* 459 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class PendingWrite {
/*     */     public abstract ActorRef commander();
/*     */     
/*     */     public abstract PendingWrite doWrite(TcpConnection.ConnectionInfo param1ConnectionInfo);
/*     */     
/*     */     public abstract void release();
/*     */   }
/*     */   
/*     */   public static class EmptyPendingWrite$ extends PendingWrite {
/*     */     public static final EmptyPendingWrite$ MODULE$;
/*     */     
/*     */     public EmptyPendingWrite$() {
/* 467 */       MODULE$ = this;
/*     */     }
/*     */     
/*     */     public ActorRef commander() {
/* 468 */       throw new IllegalStateException();
/*     */     }
/*     */     
/*     */     public TcpConnection.PendingWrite doWrite(TcpConnection.ConnectionInfo info) {
/* 469 */       throw new IllegalStateException();
/*     */     }
/*     */     
/*     */     public void release() {
/* 470 */       throw new IllegalStateException();
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface ReadResult {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\TcpConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */