/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Function0;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple4;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.LinearSeqOptimized;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021Mg!B\001\003\003\003I!\001\003*fI\nc\027mY6\013\005\r!\021!C5n[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001+\tQ\021dE\002\001\027=\001\"\001D\007\016\003\031I!A\004\004\003\r\005s\027PU3g!\ta\001#\003\002\022\r\ta1+\032:jC2L'0\0312mK\")1\003\001C\001)\0051A(\0338jiz\"\022!\006\t\004-\0019R\"\001\002\021\005aIB\002\001\003\0065\001\021\ra\007\002\002\003F\021Ad\b\t\003\031uI!A\b\004\003\0179{G\017[5oOB\021A\002I\005\003C\031\0211!\0218z\021\025\031\003A\"\001%\003%I7oU7bY2,'\017F\002&Q)\002\"\001\004\024\n\005\0352!a\002\"p_2,\027M\034\005\006S\t\002\raF\001\002q\")1F\ta\001/\005\t\021\020C\003.\001\021%a&A\004cY\006\0347.\0328\026\007=\022y\003F\0021\005c\001B!\r\032\003.5\t\001AB\0034\001\005\005AG\001\003Ue\026,WCA\033;'\r\0214b\004\005\006'I\"\ta\016\013\002qA\031\021GM\035\021\005aQDAB\0363\t\013\0071DA\001C\021\025i$G\"\001?\003\035I7/R7qif,\022!\n\005\006\001J2\tAP\001\bSN\024E.Y2l\021\025\021%G\"\001D\003\031awn\\6vaR\021\001\b\022\005\006S\005\003\ra\006\005\006\rJ\"\taR\001\007kB$\027\r^3\026\005![EcA%O!B\031\021G\r&\021\005aYE!\002'F\005\004i%A\001\"2#\tIt\004C\003P\013\002\007q#A\001l\021\025\tV\t1\001K\003\0051\b\"B*3\t\003!\026A\0023fY\026$X\r\006\0029+\")qJ\025a\001/!)qK\rC\0011\006)!/\0318hKR\031\001(\0270\t\013i3\006\031A.\002\t\031\024x.\034\t\004\031q;\022BA/\007\005\031y\005\017^5p]\")qL\026a\0017\006)QO\034;jY\")\021M\rD\001E\0069am\034:fC\016DWCA2n)\t!w\r\005\002\rK&\021aM\002\002\005+:LG\017C\003iA\002\007\021.A\001g!\025a!nF\035m\023\tYgAA\005Gk:\034G/[8oeA\021\001$\034\003\006]\002\024\ra\007\002\002+\")\001O\rD\001c\006AAo\\*ue\026\fW.F\001s!\r12/^\005\003i\n\021aa\025;sK\006l\007\003\002\007w/eJ!a\036\004\003\rQ+\b\017\\33\021\025I(G\"\001{\003!IG/\032:bi>\024X#A>\021\007qlX/D\001\005\023\tqHA\001\005Ji\026\024\030\r^8s\021\035\t\tA\rD\001\003\007\t1!\0369e+\021\t)!a\003\025\r\005\035\021QBA\b!\021\t$'!\003\021\007a\tY\001B\003M\n\007Q\nC\003P\002\007q\003\003\004R\002\007\021\021\002\005\b\003'\021d\021AA\013\003\r!W\r\034\013\004q\005]\001BB(\002\022\001\007q\003C\004\002\034I2\t!!\b\002\021Ml\027\r\0347fgR,\"!a\b\021\tE\n\t#\017\004\b\003G\001\021\021AA\023\005!quN\\#naRLX\003BA\024\003[\031R!!\t\002*=\001B!\r\032\002,A\031\001$!\f\005\017m\n\t\003\"b\0017!91#!\t\005\002\005EBCAA\032!\025\t\024\021EA\026\021\031i\024\021\005C\001}!A\021\021HA\021\r\003\tY$A\002lKf,\022a\006\005\t\003\t\tC\"\001\002B\005)a/\0317vKV\021\0211\006\005\t\003\013\n\tC\"\001\002H\005!A.\0324u+\t\tI\003\003\005\002L\005\005b\021AA$\003\025\021\030n\0325u\021\035\021\025\021\005C\001\003\037\"B!!\013\002R!1q*!\024A\002]A\021\"!\026\002\"\001&I!a\026\002\027\t\fG.\0318dK2+g\r^\013\005\0033\n)\007\006\007\002\\\005=\024\021OA;\003s\nyH\005\004\002^\005\005\024\021\016\004\007\003?\002\001!a\027\003\031q\022XMZ5oK6,g\016\036 \021\013E\n\t#a\031\021\007a\t)\007B\004M\003'\022\r!a\032\022\007\005-r\004E\002\r\003WJ1!!\034\007\005\035\001&o\0343vGRDa\001QA*\001\004)\003bBA:\003'\002\raF\001\002u\"A\021qOA*\001\004\tY#\001\002{m\"A\0211PA*\001\004\ti(A\001m!\021\t$'a\031\t\021\005\005\0251\013a\001\003{\n\021\001\032\005\n\003\013\013\t\003)C\005\003\017\013ABY1mC:\034WMU5hQR,B!!#\002\024Ra\0211RAK\003/\013I*!(\002$J1\021QRAH\003S2a!a\030\001\001\005-\005#B\031\002\"\005E\005c\001\r\002\024\0229A*a!C\002\005\035\004B\002!\002\004\002\007Q\005\003\004*\003\007\003\ra\006\005\t\0037\013\031\t1\001\002,\005\021\001P\036\005\t\003?\013\031\t1\001\002\"\006\t\021\r\005\0032e\005E\005\002CAS\003\007\003\r!!)\002\003ID\001\"!\001\002\"\021\005\021\021V\013\005\003W\013\t\f\006\004\002.\006M\026Q\027\t\005cI\ny\013E\002\031\003c#q\001TAT\005\004\t9\007\003\004P\003O\003\ra\006\005\b#\006\035\006\031AAX\021!\t\031\"!\t\005\002\005eF\003BA\025\003wCaaTA\\\001\0049\002\002CA\016\003C!\t!a0\026\005\005M\002b\0029\002\"\021\005\0211Y\013\003\003\013\004BAF:\002HB)AB^\f\002,!9\0210!\t\005\002\005-WCAAg!\021aX0a2\t\017\005\f\t\003\"\001\002RV!\0211[An)\r!\027Q\033\005\bQ\006=\007\031AAl!\035a!nFA\026\0033\0042\001GAn\t\031q\027q\032b\0017!A\021q\\A\021\t\003\n\t/A\002s]\036$b!!\013\002d\006\025\bB\002.\002^\002\0071\f\003\004`\003;\004\ra\027\005\n\003S\f\t\003)C\005\003W\fAbY8na\006\024X\rR3qi\"$b!!<\002\000\n\005\001#\003\007\002p\006MX%JA}\023\r\t\tP\002\002\007)V\004H.\032\033\021\013Y\t)0a\r\n\007\005](A\001\003MSN$\bc\001\007\002|&\031\021Q \004\003\007%sG\017\003\005\002F\005\035\b\031AA\025\021!\tY%a:A\002\005%\002\"\003B\003\003C\001K\021\002B\004\003%\021XMY1mC:\034W\r\006\004\002*\t%!Q\002\005\t\005\027\021\031\0011\001\002*\0059a.Z<MK\032$\b\002\003B\b\005\007\001\r!!\013\002\0219,wOU5hQRD\001Ba\005\002\"\021\005\0211H\001\006M&\0248\017\036\005\t\005/\t\t\003\"\001\002<\005!A.Y:u\021!\021Y\"!\t\005\002\tu\021!B2pk:$XCAA}\021\035\tyN\rD\001\005C!R\001\017B\022\005KAaA\027B\020\001\004Y\006BB0\003 \001\0071\fC\004\003\024I2\t!a\017\t\017\t]!G\"\001\002<!9!1\004\032\007\002\tu\001c\001\r\0030\021)1\b\fb\0017!1!1\007\027A\002A\n\021\001\036\005\b\005o\001A\021\002B\035\003\031i7\016\026:fKV!!1\bB#)1\021iDa\022\003J\t-#Q\nB)%\031\021yD!\021\002j\0311\021q\f\001\001\005{\001R!MA\021\005\007\0022\001\007B#\t\031Y$Q\007b\0017!1\001I!\016A\002\025Baa\024B\033\001\0049\002bB)\0036\001\007!1\t\005\t\003w\022)\0041\001\003PA!\021G\rB\"\021!\t)K!\016A\002\t=sa\002B+\001!\005%qK\001\006\0136\004H/\037\t\004c\teca\002B.\001!\005%Q\f\002\006\0136\004H/_\n\b\0053\022y&!\033\020!\r\t$\007\b\005\b'\teC\021\001B2)\t\0219\006\003\004>\0053\"\tA\020\005\007\001\neC\021\001 \t\017\t\023I\006\"\001\003lQ!!q\fB7\021\031y%\021\016a\001/!A\021\021\001B-\t\003\021\t(\006\003\003t\teDC\002B;\005w\022i\b\005\0032e\t]\004c\001\r\003z\02111Ha\034C\002mAaa\024B8\001\0049\002bB)\003p\001\007!q\017\005\t\003'\021I\006\"\001\003\002R!!q\fBB\021\031y%q\020a\001/!A\0211\004B-\t\003\0219)\006\002\003\nB!\021'!\t\035\021\035I(\021\fC\001\005\033+\"Aa$\021\tql(\021\023\t\005\031Y<B\004C\004q\0053\"\tA!&\026\005\t]\005\003\002\ft\005#Cq!\031B-\t\003\021Y*\006\003\003\036\n\025Fc\0013\003 \"9\001N!'A\002\t\005\006C\002\007k/q\021\031\013E\002\031\005K#aA\034BM\005\004Y\002\002CAp\0053\"\tA!+\025\r\t-&Q\026BX\035\r\t$1\013\005\0075\n\035\006\031A.\t\r}\0239\0131\001\\\021!\021\031B!\027\005\002\tMV#\001\017\t\021\t]!\021\fC\001\005gC\001Ba\007\003Z\021\005!Q\004\005\013\005w\023I&!A\005B\tu\026!\0049s_\022,8\r\036)sK\032L\0070\006\002\003@B!!\021\031Bf\033\t\021\031M\003\003\003F\n\035\027\001\0027b]\036T!A!3\002\t)\fg/Y\005\005\005\033\024\031M\001\004TiJLgn\032\005\013\005#\024I&!A\005\002\tu\021\001\0049s_\022,8\r^!sSRL\bB\003Bk\0053\n\t\021\"\001\003X\006q\001O]8ek\016$X\t\\3nK:$HcA\020\003Z\"Q!1\034Bj\003\003\005\r!!?\002\007a$\023\007\003\006\003`\ne\023\021!C!\005C\fq\002\035:pIV\034G/\023;fe\006$xN]\013\003\005G\0042\001`? \021)\0219O!\027\002\002\023\005!\021^\001\tG\006tW)];bYR\031QEa;\t\023\tm'Q]A\001\002\004y\002B\003Bx\0053\n\t\021\"\021\003r\006A\001.Y:i\007>$W\r\006\002\002z\"Q!Q\037B-\003\003%\tEa>\002\021Q|7\013\036:j]\036$\"Aa0\t\025\tm(\021LA\001\n\023\021i0A\006sK\006$'+Z:pYZ,GC\001B\000!\021\021\tm!\001\n\t\r\r!1\031\002\007\037\nTWm\031;\007\r\r\035\001\001QB\005\005\035\021V\r\032+sK\026,Baa\003\004\022M91QAB\007\003Sz\001#B\031\002\"\r=\001c\001\r\004\022\02191h!\002\005\006\004Y\002bCA\035\007\013\021)\032!C!\003wA!ba\006\004\006\tE\t\025!\003\030\003\021YW-\037\021\t\027\005}2Q\001BK\002\023\00531D\013\003\007\037A1ba\b\004\006\tE\t\025!\003\004\020\0051a/\0317vK\002B1\"!\022\004\006\tU\r\021\"\021\004$U\0211Q\005\t\005cI\032y\001C\006\004*\r\025!\021#Q\001\n\r\025\022!\0027fMR\004\003bCA&\007\013\021)\032!C!\007GA1ba\f\004\006\tE\t\025!\003\004&\0051!/[4ii\002BqaEB\003\t\003\031\031\004\006\006\0046\r]2\021HB\036\007{\001R!MB\003\007\037Aq!!\017\0042\001\007q\003\003\005\002@\rE\002\031AB\b\021!\t)e!\rA\002\r\025\002\002CA&\007c\001\ra!\n\t\r\001\033)\001\"\001?\021)\031\031e!\002\002\002\023\0051QI\001\005G>\004\0300\006\003\004H\r5CCCB%\007\037\032\tfa\025\004XA)\021g!\002\004LA\031\001d!\024\005\rm\032\tE1\001\034\021%\tId!\021\021\002\003\007q\003\003\006\002@\r\005\003\023!a\001\007\027B!\"!\022\004BA\005\t\031AB+!\021\t$ga\023\t\025\005-3\021\tI\001\002\004\031)\006\003\006\004\\\r\025\021\023!C\001\007;\nabY8qs\022\"WMZ1vYR$\023'\006\003\004`\rUTCAB1U\r921M\026\003\007K\002Baa\032\004r5\0211\021\016\006\005\007W\032i'A\005v]\016DWmY6fI*\0311q\016\004\002\025\005tgn\034;bi&|g.\003\003\004t\r%$!E;oG\",7m[3e-\006\024\030.\0318dK\02211h!\027C\002mA!b!\037\004\006E\005I\021AB>\0039\031w\016]=%I\0264\027-\0367uII*Ba! \004\002V\0211q\020\026\005\007\037\031\031\007\002\004<\007o\022\ra\007\005\013\007\013\033)!%A\005\002\r\035\025AD2paf$C-\0324bk2$HeM\013\005\007\023\033i)\006\002\004\f*\"1QEB2\t\031Y41\021b\0017!Q1\021SB\003#\003%\taa%\002\035\r|\007/\037\023eK\032\fW\017\034;%iU!1\021RBK\t\031Y4q\022b\0017!Q!1XB\003\003\003%\tE!0\t\025\tE7QAA\001\n\003\021i\002\003\006\003V\016\025\021\021!C\001\007;#2aHBP\021)\021Yna'\002\002\003\007\021\021 \005\013\005?\034)!!A\005B\t\005\bB\003Bt\007\013\t\t\021\"\001\004&R\031Qea*\t\023\tm71UA\001\002\004y\002B\003Bx\007\013\t\t\021\"\021\003r\"Q!Q_B\003\003\003%\tEa>\t\025\r=6QAA\001\n\003\032\t,\001\004fcV\fGn\035\013\004K\rM\006\"\003Bn\007[\013\t\0211\001 \017%\0319\fAA\001\022\003\031I,A\004SK\022$&/Z3\021\007E\032YLB\005\004\b\001\t\t\021#\001\004>N!11X\006\020\021\035\03121\030C\001\007\003$\"a!/\t\025\tU81XA\001\n\013\0229\020\003\006\004H\016m\026\021!CA\007\023\fQ!\0319qYf,Baa3\004RRQ1QZBj\007+\0349na7\021\013E\032)aa4\021\007a\031\t\016\002\004<\007\013\024\ra\007\005\b\003s\031)\r1\001\030\021!\tyd!2A\002\r=\007\002CA#\007\013\004\ra!7\021\tE\0224q\032\005\t\003\027\032)\r1\001\004Z\"Q1q\\B^\003\003%\ti!9\002\017Ut\027\r\0359msV!11]Bv)\021\031)oa<\021\t1a6q\035\t\013\031\005=xc!;\004n\0165\bc\001\r\004l\02211h!8C\002m\001B!\r\032\004j\"Q1\021_Bo\003\003\005\raa=\002\007a$\003\007E\0032\007\013\031I\017\003\006\003|\016m\026\021!C\005\005{4aa!?\001\001\016m(!\003\"mC\016\\GK]3f+\021\031i\020b\001\024\017\r]8q`A5\037A)\021'!\t\005\002A\031\001\004b\001\005\017m\0329\020\"b\0017!Y\021\021HB|\005+\007I\021IA\036\021)\0319ba>\003\022\003\006Ia\006\005\f\003\0319P!f\001\n\003\"Y!\006\002\005\002!Y1qDB|\005#\005\013\021\002C\001\021-\t)ea>\003\026\004%\t\005\"\005\026\005\021M\001\003B\0313\t\003A1b!\013\004x\nE\t\025!\003\005\024!Y\0211JB|\005+\007I\021\tC\t\021-\031yca>\003\022\003\006I\001b\005\t\017M\0319\020\"\001\005\036QQAq\004C\021\tG!)\003b\n\021\013E\0329\020\"\001\t\017\005eB1\004a\001/!A\021q\bC\016\001\004!\t\001\003\005\002F\021m\001\031\001C\n\021!\tY\005b\007A\002\021M\001B\002!\004x\022\005a\b\003\006\004D\r]\030\021!C\001\t[)B\001b\f\0056QQA\021\007C\034\ts!Y\004b\020\021\013E\0329\020b\r\021\007a!)\004\002\004<\tW\021\ra\007\005\n\003s!Y\003%AA\002]A!\"a\020\005,A\005\t\031\001C\032\021)\t)\005b\013\021\002\003\007AQ\b\t\005cI\"\031\004\003\006\002L\021-\002\023!a\001\t{A!ba\027\004xF\005I\021\001C\"+\021\031y\006\"\022\005\rm\"\tE1\001\034\021)\031Iha>\022\002\023\005A\021J\013\005\t\027\"y%\006\002\005N)\"A\021AB2\t\031YDq\tb\0017!Q1QQB|#\003%\t\001b\025\026\t\021UC\021L\013\003\t/RC\001b\005\004d\02111\b\"\025C\002mA!b!%\004xF\005I\021\001C/+\021!)\006b\030\005\rm\"YF1\001\034\021)\021Yla>\002\002\023\005#Q\030\005\013\005#\03490!A\005\002\tu\001B\003Bk\007o\f\t\021\"\001\005hQ\031q\004\"\033\t\025\tmGQMA\001\002\004\tI\020\003\006\003`\016]\030\021!C!\005CD!Ba:\004x\006\005I\021\001C8)\r)C\021\017\005\n\0057$i'!AA\002}A!Ba<\004x\006\005I\021\tBy\021)\021)pa>\002\002\023\005#q\037\005\013\007_\03390!A\005B\021eDcA\023\005|!I!1\034C<\003\003\005\raH\004\n\t\002\021\021!E\001\t\003\013\021B\0217bG.$&/Z3\021\007E\"\031IB\005\004z\002\t\t\021#\001\005\006N!A1Q\006\020\021\035\031B1\021C\001\t\023#\"\001\"!\t\025\tUH1QA\001\n\013\0229\020\003\006\004H\022\r\025\021!CA\t\037+B\001\"%\005\030RQA1\023CM\t7#i\n\")\021\013E\0329\020\"&\021\007a!9\n\002\004<\t\033\023\ra\007\005\b\003s!i\t1\001\030\021!\ty\004\"$A\002\021U\005\002CA#\t\033\003\r\001b(\021\tE\022DQ\023\005\t\003\027\"i\t1\001\005 \"Q1q\034CB\003\003%\t\t\"*\026\t\021\035Fq\026\013\005\tS#\031\f\005\003\r9\022-\006C\003\007\002p^!i\013\"-\0052B\031\001\004b,\005\rm\"\031K1\001\034!\021\t$\007\",\t\025\rEH1UA\001\002\004!)\fE\0032\007o$i\013\003\006\003|\022\r\025\021!C\005\005{DS\001\001C^\t\003\0042\001\004C_\023\r!yL\002\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\002\002_PIj) \030$\037\025\b\001\021\025G1\032Ch!\raAqY\005\004\t\0234!A\0033faJ,7-\031;fI\006\022AQZ\001#kN,\007\005\031+sK\026l\025\r\0351!_J\004\003\r\026:fKN+G\017\031\021j]N$X-\0313\"\005\021E\027A\002\032/cAr\003\007")
/*     */ public abstract class RedBlack<A> implements Serializable {
/*     */   public static final long serialVersionUID = 8691885935445612921L;
/*     */   
/*     */   private volatile Empty$ Empty$module;
/*     */   
/*     */   private volatile RedTree$ RedTree$module;
/*     */   
/*     */   private volatile BlackTree$ BlackTree$module;
/*     */   
/*     */   public <B> Tree<B> scala$collection$immutable$RedBlack$$blacken(Tree<B> t) {
/*     */     Tree<B> tree;
/*  27 */     if (t instanceof RedTree) {
/*  27 */       RedTree redTree = (RedTree)t;
/*  28 */       tree = new BlackTree(this, (A)redTree.key(), redTree.value(), redTree.left(), redTree.right());
/*     */     } else {
/*  29 */       tree = t;
/*     */     } 
/*     */     return tree;
/*     */   }
/*     */   
/*     */   public <B> NonEmpty<B> scala$collection$immutable$RedBlack$$mkTree(boolean isBlack, Object k, Object v, Tree<B> l, Tree<B> r) {
/*  32 */     return isBlack ? new BlackTree<B>(this, (A)k, (B)v, l, r) : new RedTree<B>(this, (A)k, (B)v, l, r);
/*     */   }
/*     */   
/*     */   public abstract class Tree<B> implements Serializable {
/*     */     public Tree(RedBlack $outer) {}
/*     */     
/*     */     public <B1> Tree<B1> update(Object k, Object v) {
/*  38 */       return scala$collection$immutable$RedBlack$Tree$$$outer().scala$collection$immutable$RedBlack$$blacken(upd((A)k, v));
/*     */     }
/*     */     
/*     */     public Tree<B> delete(Object k) {
/*  39 */       return scala$collection$immutable$RedBlack$Tree$$$outer().scala$collection$immutable$RedBlack$$blacken(del((A)k));
/*     */     }
/*     */     
/*     */     public Tree<B> range(Option<A> from, Option<A> until) {
/*  40 */       return scala$collection$immutable$RedBlack$Tree$$$outer().scala$collection$immutable$RedBlack$$blacken(rng(from, until));
/*     */     }
/*     */     
/*     */     public abstract boolean isEmpty();
/*     */     
/*     */     public abstract boolean isBlack();
/*     */     
/*     */     public abstract Tree<B> lookup(A param1A);
/*     */     
/*     */     public abstract <U> void foreach(Function2<A, B, U> param1Function2);
/*     */     
/*     */     public abstract Stream<Tuple2<A, B>> toStream();
/*     */     
/*     */     public abstract Iterator<Tuple2<A, B>> iterator();
/*     */     
/*     */     public abstract <B1> Tree<B1> upd(A param1A, B1 param1B1);
/*     */     
/*     */     public abstract Tree<B> del(A param1A);
/*     */     
/*     */     public abstract RedBlack<A>.NonEmpty<B> smallest();
/*     */     
/*     */     public abstract Tree<B> rng(Option<A> param1Option1, Option<A> param1Option2);
/*     */     
/*     */     public abstract A first();
/*     */     
/*     */     public abstract A last();
/*     */     
/*     */     public abstract int count();
/*     */   }
/*     */   
/*     */   public abstract class NonEmpty<B> extends Tree<B> implements Serializable {
/*     */     public NonEmpty(RedBlack<A> $outer) {
/*  52 */       super($outer);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*  53 */       return false;
/*     */     }
/*     */     
/*     */     public RedBlack<A>.Tree<B> lookup(Object k) {
/*  59 */       return scala$collection$immutable$RedBlack$NonEmpty$$$outer().isSmaller(k, key()) ? left().lookup((A)k) : (
/*  60 */         scala$collection$immutable$RedBlack$NonEmpty$$$outer().isSmaller(key(), (A)k) ? right().lookup((A)k) : 
/*  61 */         this);
/*     */     }
/*     */     
/*     */     public <B1> NonEmpty<B1> scala$collection$immutable$RedBlack$NonEmpty$$balanceLeft(boolean isBlack, Object z, Object zv, RedBlack.Tree l, RedBlack.Tree d) {
/*     */       NonEmpty<B1> nonEmpty;
/*  63 */       boolean bool = false;
/*  63 */       RedBlack.RedTree redTree = null;
/*     */       bool = true;
/*     */       if (l instanceof RedBlack.RedTree && (redTree = (RedBlack.RedTree)l).left() instanceof RedBlack.RedTree) {
/*  63 */         RedBlack.RedTree redTree1 = (RedBlack.RedTree)redTree.left();
/*  64 */         nonEmpty = new RedBlack.RedTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree.key(), redTree.value(), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree1.key(), redTree1.value(), redTree1.left(), redTree1.right()), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)z, zv, redTree.right(), d));
/*  65 */       } else if (bool && redTree.right() instanceof RedBlack.RedTree) {
/*  65 */         RedBlack.RedTree redTree1 = (RedBlack.RedTree)redTree.right();
/*  66 */         nonEmpty = new RedBlack.RedTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree1.key(), redTree1.value(), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree.key(), redTree.value(), redTree.left(), redTree1.left()), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)z, zv, redTree1.right(), d));
/*     */       } else {
/*  68 */         nonEmpty = scala$collection$immutable$RedBlack$NonEmpty$$$outer().scala$collection$immutable$RedBlack$$mkTree(isBlack, z, zv, l, d);
/*     */       } 
/*     */       return nonEmpty;
/*     */     }
/*     */     
/*     */     public <B1> NonEmpty<B1> scala$collection$immutable$RedBlack$NonEmpty$$balanceRight(boolean isBlack, Object x, Object xv, RedBlack.Tree a, RedBlack.Tree r) {
/*     */       NonEmpty<B1> nonEmpty;
/*  71 */       boolean bool = false;
/*  71 */       RedBlack.RedTree redTree = null;
/*     */       bool = true;
/*     */       if (r instanceof RedBlack.RedTree && (redTree = (RedBlack.RedTree)r).left() instanceof RedBlack.RedTree) {
/*  71 */         RedBlack.RedTree redTree1 = (RedBlack.RedTree)redTree.left();
/*  72 */         nonEmpty = new RedBlack.RedTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree1.key(), redTree1.value(), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)x, xv, a, redTree1.left()), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree.key(), redTree.value(), redTree1.right(), redTree.right()));
/*  73 */       } else if (bool && redTree.right() instanceof RedBlack.RedTree) {
/*  73 */         RedBlack.RedTree redTree1 = (RedBlack.RedTree)redTree.right();
/*  74 */         nonEmpty = new RedBlack.RedTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree.key(), redTree.value(), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)x, xv, a, redTree.left()), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree1.key(), redTree1.value(), redTree1.left(), redTree1.right()));
/*     */       } else {
/*  76 */         nonEmpty = scala$collection$immutable$RedBlack$NonEmpty$$$outer().scala$collection$immutable$RedBlack$$mkTree(isBlack, x, xv, a, r);
/*     */       } 
/*     */       return nonEmpty;
/*     */     }
/*     */     
/*     */     public <B1> RedBlack<A>.Tree<B1> upd(Object k, Object v) {
/*  79 */       return scala$collection$immutable$RedBlack$NonEmpty$$$outer().isSmaller(k, key()) ? scala$collection$immutable$RedBlack$NonEmpty$$balanceLeft(isBlack(), key(), value(), left().upd((A)k, (B1)v), right()) : (
/*  80 */         scala$collection$immutable$RedBlack$NonEmpty$$$outer().isSmaller(key(), (A)k) ? scala$collection$immutable$RedBlack$NonEmpty$$balanceRight(isBlack(), key(), value(), left(), right().upd((A)k, (B1)v)) : 
/*  81 */         scala$collection$immutable$RedBlack$NonEmpty$$$outer().<B1>scala$collection$immutable$RedBlack$$mkTree(isBlack(), k, (B1)v, left(), right()));
/*     */     }
/*     */     
/*     */     private final NonEmpty balance$1(Object x, Object xv, RedBlack.Tree tl, RedBlack.Tree tr) {
/*  86 */       Tuple2 tuple2 = new Tuple2(tl, tr);
/*  86 */       if (tuple2 != null && tuple2
/*  87 */         ._1() instanceof RedBlack.RedTree) {
/*  87 */         RedBlack.RedTree redTree = (RedBlack.RedTree)tuple2._1();
/*  87 */         if (tuple2._2() instanceof RedBlack.RedTree) {
/*  87 */           RedBlack.RedTree redTree1 = (RedBlack.RedTree)tuple2._2();
/*  88 */           return new RedBlack.RedTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)x, xv, new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree.key(), redTree.value(), redTree.left(), redTree.right()), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree1.key(), redTree1.value(), redTree1.left(), redTree1.right()));
/*     */         } 
/*     */       } 
/*  89 */       if (tuple2 != null && tuple2._1() instanceof RedBlack.RedTree) {
/*  89 */         RedBlack.RedTree redTree = (RedBlack.RedTree)tuple2._1();
/*  89 */         if (redTree.left() instanceof RedBlack.RedTree) {
/*  89 */           RedBlack.RedTree redTree1 = (RedBlack.RedTree)redTree.left();
/*  90 */           return new RedBlack.RedTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree.key(), redTree.value(), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree1.key(), redTree1.value(), redTree1.left(), redTree1.right()), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)x, xv, redTree.right(), (RedBlack.Tree)tuple2._2()));
/*     */         } 
/*     */       } 
/*  91 */       if (tuple2 != null && tuple2._1() instanceof RedBlack.RedTree) {
/*  91 */         RedBlack.RedTree redTree = (RedBlack.RedTree)tuple2._1();
/*  91 */         if (redTree.right() instanceof RedBlack.RedTree) {
/*  91 */           RedBlack.RedTree redTree1 = (RedBlack.RedTree)redTree.right();
/*  92 */           return new RedBlack.RedTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree1.key(), redTree1.value(), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree.key(), redTree.value(), redTree.left(), redTree1.left()), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)x, xv, redTree1.right(), (RedBlack.Tree)tuple2._2()));
/*     */         } 
/*     */       } 
/*  93 */       if (tuple2 != null && tuple2._2() instanceof RedBlack.RedTree) {
/*  93 */         RedBlack.RedTree redTree = (RedBlack.RedTree)tuple2._2();
/*  93 */         if (redTree.right() instanceof RedBlack.RedTree) {
/*  93 */           RedBlack.RedTree redTree1 = (RedBlack.RedTree)redTree.right();
/*  94 */           return new RedBlack.RedTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree.key(), redTree.value(), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)x, xv, (RedBlack.Tree)tuple2._1(), redTree.left()), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree1.key(), redTree1.value(), redTree1.left(), redTree1.right()));
/*     */         } 
/*     */       } 
/*  95 */       if (tuple2 != null && tuple2._2() instanceof RedBlack.RedTree) {
/*  95 */         RedBlack.RedTree redTree = (RedBlack.RedTree)tuple2._2();
/*  95 */         if (redTree.left() instanceof RedBlack.RedTree) {
/*  95 */           RedBlack.RedTree redTree1 = (RedBlack.RedTree)redTree.left();
/*  96 */           return new RedBlack.RedTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree1.key(), redTree1.value(), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)x, xv, (RedBlack.Tree)tuple2._1(), redTree1.left()), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree.key(), redTree.value(), redTree1.right(), redTree.right()));
/*     */         } 
/*     */       } 
/*     */       if (tuple2 != null)
/*  98 */         return new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)x, xv, (RedBlack.Tree)tuple2._1(), (RedBlack.Tree)tuple2._2()); 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     private final RedBlack.RedTree subl$1(RedBlack.Tree t) {
/* 100 */       if (t instanceof RedBlack.BlackTree) {
/* 100 */         RedBlack.BlackTree blackTree = (RedBlack.BlackTree)t;
/* 100 */         return new RedBlack.RedTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)blackTree
/* 101 */             .key(), blackTree.value(), blackTree.left(), blackTree.right());
/*     */       } 
/* 102 */       throw scala.sys.package$.MODULE$.error((new StringBuilder()).append("Defect: invariance violation; expected black, got ").append(t).toString());
/*     */     }
/*     */     
/*     */     private final NonEmpty balLeft$1(Object x, Object xv, RedBlack.Tree tl, RedBlack.Tree tr) {
/* 104 */       Tuple2 tuple2 = new Tuple2(tl, tr);
/* 104 */       if (tuple2 != null && tuple2
/* 105 */         ._1() instanceof RedBlack.RedTree) {
/* 105 */         RedBlack.RedTree redTree1 = (RedBlack.RedTree)tuple2._1();
/* 106 */         RedBlack.RedTree redTree2 = new RedBlack.RedTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)x, xv, new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree1.key(), redTree1.value(), redTree1.left(), redTree1.right()), (RedBlack.Tree)tuple2._2());
/* 107 */       } else if (tuple2 != null && tuple2._2() instanceof RedBlack.BlackTree) {
/* 107 */         RedBlack.BlackTree blackTree = (RedBlack.BlackTree)tuple2._2();
/* 108 */         NonEmpty nonEmpty = balance$1(x, xv, (RedBlack.Tree)tuple2._1(), new RedBlack.RedTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)blackTree.key(), blackTree.value(), blackTree.left(), blackTree.right()));
/*     */       } else {
/* 109 */         if (tuple2 != null && tuple2._2() instanceof RedBlack.RedTree) {
/* 109 */           RedBlack.RedTree redTree = (RedBlack.RedTree)tuple2._2();
/* 109 */           if (redTree.left() instanceof RedBlack.BlackTree) {
/* 109 */             RedBlack.BlackTree blackTree = (RedBlack.BlackTree)redTree.left();
/* 110 */             return new RedBlack.RedTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)blackTree.key(), blackTree.value(), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)x, xv, (RedBlack.Tree)tuple2._1(), blackTree.left()), balance$1(redTree.key(), redTree.value(), blackTree.right(), subl$1(redTree.right())));
/*     */           } 
/*     */         } 
/* 111 */         throw scala.sys.package$.MODULE$.error((new StringBuilder()).append("Defect: invariance violation at ").append(right()).toString());
/*     */       } 
/*     */       return (NonEmpty)SYNTHETIC_LOCAL_VARIABLE_7;
/*     */     }
/*     */     
/*     */     private final NonEmpty balRight$1(Object x, Object xv, RedBlack.Tree tl, RedBlack.Tree tr) {
/* 113 */       Tuple2 tuple2 = new Tuple2(tl, tr);
/* 113 */       if (tuple2 != null && tuple2
/* 114 */         ._2() instanceof RedBlack.RedTree) {
/* 114 */         RedBlack.RedTree redTree1 = (RedBlack.RedTree)tuple2._2();
/* 115 */         RedBlack.RedTree redTree2 = new RedBlack.RedTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)x, xv, (RedBlack.Tree)tuple2._1(), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)redTree1.key(), redTree1.value(), redTree1.left(), redTree1.right()));
/* 116 */       } else if (tuple2 != null && tuple2._1() instanceof RedBlack.BlackTree) {
/* 116 */         RedBlack.BlackTree blackTree = (RedBlack.BlackTree)tuple2._1();
/* 117 */         NonEmpty nonEmpty = balance$1(x, xv, new RedBlack.RedTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)blackTree.key(), blackTree.value(), blackTree.left(), blackTree.right()), (RedBlack.Tree)tuple2._2());
/*     */       } else {
/* 118 */         if (tuple2 != null && tuple2._1() instanceof RedBlack.RedTree) {
/* 118 */           RedBlack.RedTree redTree = (RedBlack.RedTree)tuple2._1();
/* 118 */           if (redTree.right() instanceof RedBlack.BlackTree) {
/* 118 */             RedBlack.BlackTree blackTree = (RedBlack.BlackTree)redTree.right();
/* 119 */             return new RedBlack.RedTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)blackTree.key(), blackTree.value(), balance$1(redTree.key(), redTree.value(), subl$1(redTree.left()), blackTree.left()), new RedBlack.BlackTree(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), (A)x, xv, blackTree.right(), (RedBlack.Tree)tuple2._2()));
/*     */           } 
/*     */         } 
/* 120 */         throw scala.sys.package$.MODULE$.error((new StringBuilder()).append("Defect: invariance violation at ").append(left()).toString());
/*     */       } 
/*     */       return (NonEmpty)SYNTHETIC_LOCAL_VARIABLE_7;
/*     */     }
/*     */     
/*     */     private final NonEmpty delLeft$1(Object k$1) {
/*     */       NonEmpty nonEmpty;
/* 122 */       RedBlack<A>.Tree<B> tree = left();
/* 123 */       if (tree instanceof RedBlack.BlackTree) {
/* 123 */         nonEmpty = balLeft$1(key(), value(), left().del((A)k$1), right());
/*     */       } else {
/* 124 */         nonEmpty = new RedBlack.RedTree<B>(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), key(), value(), left().del((A)k$1), right());
/*     */       } 
/*     */       return nonEmpty;
/*     */     }
/*     */     
/*     */     private final NonEmpty delRight$1(Object k$1) {
/*     */       NonEmpty nonEmpty;
/* 126 */       RedBlack<A>.Tree<B> tree = right();
/* 127 */       if (tree instanceof RedBlack.BlackTree) {
/* 127 */         nonEmpty = balRight$1(key(), value(), left(), right().del((A)k$1));
/*     */       } else {
/* 128 */         nonEmpty = new RedBlack.RedTree<B>(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), key(), value(), left(), right().del((A)k$1));
/*     */       } 
/*     */       return nonEmpty;
/*     */     }
/*     */     
/*     */     private final RedBlack.Tree append$1(RedBlack.Tree tl, RedBlack.Tree tr) {
/*     */       // Byte code:
/*     */       //   0: new scala/Tuple2
/*     */       //   3: dup
/*     */       //   4: aload_1
/*     */       //   5: aload_2
/*     */       //   6: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */       //   9: astore_3
/*     */       //   10: aload_3
/*     */       //   11: ifnull -> 60
/*     */       //   14: aload_0
/*     */       //   15: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   18: invokevirtual Empty : ()Lscala/collection/immutable/RedBlack$Empty$;
/*     */       //   21: aload_3
/*     */       //   22: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   25: astore #4
/*     */       //   27: dup
/*     */       //   28: ifnonnull -> 40
/*     */       //   31: pop
/*     */       //   32: aload #4
/*     */       //   34: ifnull -> 48
/*     */       //   37: goto -> 60
/*     */       //   40: aload #4
/*     */       //   42: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   45: ifeq -> 60
/*     */       //   48: aload_3
/*     */       //   49: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   52: checkcast scala/collection/immutable/RedBlack$Tree
/*     */       //   55: astore #5
/*     */       //   57: goto -> 688
/*     */       //   60: aload_3
/*     */       //   61: ifnull -> 110
/*     */       //   64: aload_0
/*     */       //   65: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   68: invokevirtual Empty : ()Lscala/collection/immutable/RedBlack$Empty$;
/*     */       //   71: aload_3
/*     */       //   72: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   75: astore #6
/*     */       //   77: dup
/*     */       //   78: ifnonnull -> 90
/*     */       //   81: pop
/*     */       //   82: aload #6
/*     */       //   84: ifnull -> 98
/*     */       //   87: goto -> 110
/*     */       //   90: aload #6
/*     */       //   92: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   95: ifeq -> 110
/*     */       //   98: aload_3
/*     */       //   99: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   102: checkcast scala/collection/immutable/RedBlack$Tree
/*     */       //   105: astore #5
/*     */       //   107: goto -> 688
/*     */       //   110: aload_3
/*     */       //   111: ifnull -> 334
/*     */       //   114: aload_3
/*     */       //   115: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   118: instanceof scala/collection/immutable/RedBlack$RedTree
/*     */       //   121: ifeq -> 334
/*     */       //   124: aload_3
/*     */       //   125: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   128: checkcast scala/collection/immutable/RedBlack$RedTree
/*     */       //   131: astore #7
/*     */       //   133: aload_3
/*     */       //   134: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   137: instanceof scala/collection/immutable/RedBlack$RedTree
/*     */       //   140: ifeq -> 334
/*     */       //   143: aload_3
/*     */       //   144: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   147: checkcast scala/collection/immutable/RedBlack$RedTree
/*     */       //   150: astore #8
/*     */       //   152: aload_0
/*     */       //   153: aload #7
/*     */       //   155: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   158: aload #8
/*     */       //   160: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   163: invokespecial append$1 : (Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   166: astore #9
/*     */       //   168: aload #9
/*     */       //   170: instanceof scala/collection/immutable/RedBlack$RedTree
/*     */       //   173: ifeq -> 271
/*     */       //   176: aload #9
/*     */       //   178: checkcast scala/collection/immutable/RedBlack$RedTree
/*     */       //   181: astore #10
/*     */       //   183: new scala/collection/immutable/RedBlack$RedTree
/*     */       //   186: dup
/*     */       //   187: aload_0
/*     */       //   188: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   191: aload #10
/*     */       //   193: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   196: aload #10
/*     */       //   198: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   201: new scala/collection/immutable/RedBlack$RedTree
/*     */       //   204: dup
/*     */       //   205: aload_0
/*     */       //   206: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   209: aload #7
/*     */       //   211: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   214: aload #7
/*     */       //   216: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   219: aload #7
/*     */       //   221: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   224: aload #10
/*     */       //   226: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   229: invokespecial <init> : (Lscala/collection/immutable/RedBlack;Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)V
/*     */       //   232: new scala/collection/immutable/RedBlack$RedTree
/*     */       //   235: dup
/*     */       //   236: aload_0
/*     */       //   237: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   240: aload #8
/*     */       //   242: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   245: aload #8
/*     */       //   247: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   250: aload #10
/*     */       //   252: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   255: aload #8
/*     */       //   257: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   260: invokespecial <init> : (Lscala/collection/immutable/RedBlack;Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)V
/*     */       //   263: invokespecial <init> : (Lscala/collection/immutable/RedBlack;Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)V
/*     */       //   266: astore #11
/*     */       //   268: goto -> 327
/*     */       //   271: new scala/collection/immutable/RedBlack$RedTree
/*     */       //   274: dup
/*     */       //   275: aload_0
/*     */       //   276: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   279: aload #7
/*     */       //   281: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   284: aload #7
/*     */       //   286: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   289: aload #7
/*     */       //   291: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   294: new scala/collection/immutable/RedBlack$RedTree
/*     */       //   297: dup
/*     */       //   298: aload_0
/*     */       //   299: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   302: aload #8
/*     */       //   304: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   307: aload #8
/*     */       //   309: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   312: aload #9
/*     */       //   314: aload #8
/*     */       //   316: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   319: invokespecial <init> : (Lscala/collection/immutable/RedBlack;Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)V
/*     */       //   322: invokespecial <init> : (Lscala/collection/immutable/RedBlack;Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)V
/*     */       //   325: astore #11
/*     */       //   327: aload #11
/*     */       //   329: astore #5
/*     */       //   331: goto -> 688
/*     */       //   334: aload_3
/*     */       //   335: ifnull -> 551
/*     */       //   338: aload_3
/*     */       //   339: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   342: instanceof scala/collection/immutable/RedBlack$BlackTree
/*     */       //   345: ifeq -> 551
/*     */       //   348: aload_3
/*     */       //   349: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   352: checkcast scala/collection/immutable/RedBlack$BlackTree
/*     */       //   355: astore #12
/*     */       //   357: aload_3
/*     */       //   358: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   361: instanceof scala/collection/immutable/RedBlack$BlackTree
/*     */       //   364: ifeq -> 551
/*     */       //   367: aload_3
/*     */       //   368: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   371: checkcast scala/collection/immutable/RedBlack$BlackTree
/*     */       //   374: astore #13
/*     */       //   376: aload_0
/*     */       //   377: aload #12
/*     */       //   379: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   382: aload #13
/*     */       //   384: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   387: invokespecial append$1 : (Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   390: astore #14
/*     */       //   392: aload #14
/*     */       //   394: instanceof scala/collection/immutable/RedBlack$RedTree
/*     */       //   397: ifeq -> 495
/*     */       //   400: aload #14
/*     */       //   402: checkcast scala/collection/immutable/RedBlack$RedTree
/*     */       //   405: astore #15
/*     */       //   407: new scala/collection/immutable/RedBlack$RedTree
/*     */       //   410: dup
/*     */       //   411: aload_0
/*     */       //   412: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   415: aload #15
/*     */       //   417: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   420: aload #15
/*     */       //   422: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   425: new scala/collection/immutable/RedBlack$BlackTree
/*     */       //   428: dup
/*     */       //   429: aload_0
/*     */       //   430: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   433: aload #12
/*     */       //   435: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   438: aload #12
/*     */       //   440: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   443: aload #12
/*     */       //   445: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   448: aload #15
/*     */       //   450: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   453: invokespecial <init> : (Lscala/collection/immutable/RedBlack;Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)V
/*     */       //   456: new scala/collection/immutable/RedBlack$BlackTree
/*     */       //   459: dup
/*     */       //   460: aload_0
/*     */       //   461: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   464: aload #13
/*     */       //   466: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   469: aload #13
/*     */       //   471: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   474: aload #15
/*     */       //   476: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   479: aload #13
/*     */       //   481: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   484: invokespecial <init> : (Lscala/collection/immutable/RedBlack;Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)V
/*     */       //   487: invokespecial <init> : (Lscala/collection/immutable/RedBlack;Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)V
/*     */       //   490: astore #16
/*     */       //   492: goto -> 544
/*     */       //   495: aload_0
/*     */       //   496: aload #12
/*     */       //   498: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   501: aload #12
/*     */       //   503: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   506: aload #12
/*     */       //   508: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   511: new scala/collection/immutable/RedBlack$BlackTree
/*     */       //   514: dup
/*     */       //   515: aload_0
/*     */       //   516: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   519: aload #13
/*     */       //   521: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   524: aload #13
/*     */       //   526: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   529: aload #14
/*     */       //   531: aload #13
/*     */       //   533: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   536: invokespecial <init> : (Lscala/collection/immutable/RedBlack;Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)V
/*     */       //   539: invokespecial balLeft$1 : (Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)Lscala/collection/immutable/RedBlack$NonEmpty;
/*     */       //   542: astore #16
/*     */       //   544: aload #16
/*     */       //   546: astore #5
/*     */       //   548: goto -> 688
/*     */       //   551: aload_3
/*     */       //   552: ifnull -> 621
/*     */       //   555: aload_3
/*     */       //   556: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   559: instanceof scala/collection/immutable/RedBlack$RedTree
/*     */       //   562: ifeq -> 621
/*     */       //   565: aload_3
/*     */       //   566: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   569: checkcast scala/collection/immutable/RedBlack$RedTree
/*     */       //   572: astore #17
/*     */       //   574: new scala/collection/immutable/RedBlack$RedTree
/*     */       //   577: dup
/*     */       //   578: aload_0
/*     */       //   579: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   582: aload #17
/*     */       //   584: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   587: aload #17
/*     */       //   589: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   592: aload_0
/*     */       //   593: aload_3
/*     */       //   594: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   597: checkcast scala/collection/immutable/RedBlack$Tree
/*     */       //   600: aload #17
/*     */       //   602: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   605: invokespecial append$1 : (Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   608: aload #17
/*     */       //   610: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   613: invokespecial <init> : (Lscala/collection/immutable/RedBlack;Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)V
/*     */       //   616: astore #5
/*     */       //   618: goto -> 688
/*     */       //   621: aload_3
/*     */       //   622: ifnull -> 691
/*     */       //   625: aload_3
/*     */       //   626: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   629: instanceof scala/collection/immutable/RedBlack$RedTree
/*     */       //   632: ifeq -> 691
/*     */       //   635: aload_3
/*     */       //   636: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   639: checkcast scala/collection/immutable/RedBlack$RedTree
/*     */       //   642: astore #18
/*     */       //   644: new scala/collection/immutable/RedBlack$RedTree
/*     */       //   647: dup
/*     */       //   648: aload_0
/*     */       //   649: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   652: aload #18
/*     */       //   654: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   657: aload #18
/*     */       //   659: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   662: aload #18
/*     */       //   664: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   667: aload_0
/*     */       //   668: aload #18
/*     */       //   670: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   673: aload_3
/*     */       //   674: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   677: checkcast scala/collection/immutable/RedBlack$Tree
/*     */       //   680: invokespecial append$1 : (Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   683: invokespecial <init> : (Lscala/collection/immutable/RedBlack;Ljava/lang/Object;Ljava/lang/Object;Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)V
/*     */       //   686: astore #5
/*     */       //   688: aload #5
/*     */       //   690: areturn
/*     */       //   691: new scala/MatchError
/*     */       //   694: dup
/*     */       //   695: aload_3
/*     */       //   696: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   699: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #130	-> 0
/*     */       //   #131	-> 14
/*     */       //   #130	-> 21
/*     */       //   #131	-> 22
/*     */       //   #130	-> 48
/*     */       //   #131	-> 49
/*     */       //   #130	-> 60
/*     */       //   #132	-> 64
/*     */       //   #130	-> 71
/*     */       //   #132	-> 72
/*     */       //   #130	-> 98
/*     */       //   #132	-> 99
/*     */       //   #130	-> 110
/*     */       //   #133	-> 115
/*     */       //   #130	-> 124
/*     */       //   #133	-> 125
/*     */       //   #130	-> 133
/*     */       //   #133	-> 134
/*     */       //   #130	-> 143
/*     */       //   #133	-> 144
/*     */       //   #134	-> 152
/*     */       //   #130	-> 153
/*     */       //   #134	-> 155
/*     */       //   #130	-> 158
/*     */       //   #134	-> 160
/*     */       //   #135	-> 168
/*     */       //   #134	-> 191
/*     */       //   #135	-> 193
/*     */       //   #134	-> 196
/*     */       //   #135	-> 198
/*     */       //   #130	-> 209
/*     */       //   #135	-> 211
/*     */       //   #130	-> 214
/*     */       //   #135	-> 216
/*     */       //   #130	-> 219
/*     */       //   #135	-> 221
/*     */       //   #134	-> 224
/*     */       //   #135	-> 226
/*     */       //   #130	-> 240
/*     */       //   #135	-> 242
/*     */       //   #130	-> 245
/*     */       //   #135	-> 247
/*     */       //   #134	-> 250
/*     */       //   #135	-> 252
/*     */       //   #130	-> 255
/*     */       //   #135	-> 257
/*     */       //   #136	-> 271
/*     */       //   #130	-> 279
/*     */       //   #136	-> 281
/*     */       //   #130	-> 284
/*     */       //   #136	-> 286
/*     */       //   #130	-> 289
/*     */       //   #136	-> 291
/*     */       //   #130	-> 302
/*     */       //   #136	-> 304
/*     */       //   #130	-> 307
/*     */       //   #136	-> 309
/*     */       //   #130	-> 314
/*     */       //   #136	-> 316
/*     */       //   #134	-> 327
/*     */       //   #130	-> 334
/*     */       //   #138	-> 339
/*     */       //   #130	-> 348
/*     */       //   #138	-> 349
/*     */       //   #130	-> 357
/*     */       //   #138	-> 358
/*     */       //   #130	-> 367
/*     */       //   #138	-> 368
/*     */       //   #139	-> 376
/*     */       //   #130	-> 377
/*     */       //   #139	-> 379
/*     */       //   #130	-> 382
/*     */       //   #139	-> 384
/*     */       //   #140	-> 392
/*     */       //   #139	-> 415
/*     */       //   #140	-> 417
/*     */       //   #139	-> 420
/*     */       //   #140	-> 422
/*     */       //   #130	-> 433
/*     */       //   #140	-> 435
/*     */       //   #130	-> 438
/*     */       //   #140	-> 440
/*     */       //   #130	-> 443
/*     */       //   #140	-> 445
/*     */       //   #139	-> 448
/*     */       //   #140	-> 450
/*     */       //   #130	-> 464
/*     */       //   #140	-> 466
/*     */       //   #130	-> 469
/*     */       //   #140	-> 471
/*     */       //   #139	-> 474
/*     */       //   #140	-> 476
/*     */       //   #130	-> 479
/*     */       //   #140	-> 481
/*     */       //   #141	-> 495
/*     */       //   #130	-> 496
/*     */       //   #141	-> 498
/*     */       //   #130	-> 501
/*     */       //   #141	-> 503
/*     */       //   #130	-> 506
/*     */       //   #141	-> 508
/*     */       //   #130	-> 519
/*     */       //   #141	-> 521
/*     */       //   #130	-> 524
/*     */       //   #141	-> 526
/*     */       //   #130	-> 531
/*     */       //   #141	-> 533
/*     */       //   #139	-> 544
/*     */       //   #130	-> 551
/*     */       //   #143	-> 556
/*     */       //   #130	-> 565
/*     */       //   #143	-> 566
/*     */       //   #130	-> 582
/*     */       //   #143	-> 584
/*     */       //   #130	-> 587
/*     */       //   #143	-> 589
/*     */       //   #130	-> 593
/*     */       //   #143	-> 594
/*     */       //   #130	-> 600
/*     */       //   #143	-> 602
/*     */       //   #130	-> 608
/*     */       //   #143	-> 610
/*     */       //   #130	-> 621
/*     */       //   #144	-> 626
/*     */       //   #130	-> 635
/*     */       //   #144	-> 636
/*     */       //   #130	-> 652
/*     */       //   #144	-> 654
/*     */       //   #130	-> 657
/*     */       //   #144	-> 659
/*     */       //   #130	-> 662
/*     */       //   #144	-> 664
/*     */       //   #130	-> 668
/*     */       //   #144	-> 670
/*     */       //   #130	-> 673
/*     */       //   #144	-> 674
/*     */       //   #130	-> 688
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	700	0	this	Lscala/collection/immutable/RedBlack$NonEmpty;
/*     */       //   0	700	1	tl	Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   0	700	2	tr	Lscala/collection/immutable/RedBlack$Tree;
/*     */     }
/*     */     
/*     */     public RedBlack<A>.Tree<B> del(Object k) {
/*     */       RedBlack<A>.Tree<B> tree;
/* 147 */       if (scala$collection$immutable$RedBlack$NonEmpty$$$outer().isSmaller(k, key())) {
/* 147 */         tree = delLeft$1(k);
/* 149 */       } else if (scala$collection$immutable$RedBlack$NonEmpty$$$outer().isSmaller(key(), (A)k)) {
/* 149 */         tree = delRight$1(k);
/*     */       } else {
/* 150 */         tree = append$1(left(), right());
/*     */       } 
/*     */       return tree;
/*     */     }
/*     */     
/*     */     public NonEmpty<B> smallest() {
/* 154 */       return left().isEmpty() ? this : left().smallest();
/*     */     }
/*     */     
/*     */     public Stream<Tuple2<A, B>> toStream() {
/* 157 */       (new Tuple2[1])[0] = new Tuple2(key(), value());
/* 157 */       return (Stream<Tuple2<A, B>>)((Stream)left().toStream().$plus$plus((GenTraversableOnce)Stream$.MODULE$.apply((Seq<?>)scala.Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1])), Stream$.MODULE$.canBuildFrom())).$plus$plus((GenTraversableOnce)right().toStream(), Stream$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<A, B>> iterator() {
/* 160 */       return left().iterator().$plus$plus((Function0)new RedBlack$NonEmpty$$anonfun$iterator$1(this)).$plus$plus((Function0)new RedBlack$NonEmpty$$anonfun$iterator$2(this));
/*     */     }
/*     */     
/*     */     public class RedBlack$NonEmpty$$anonfun$iterator$2 extends AbstractFunction0<Iterator<Tuple2<A, B>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Iterator<Tuple2<A, B>> apply() {
/* 160 */         return this.$outer.right().iterator();
/*     */       }
/*     */       
/*     */       public RedBlack$NonEmpty$$anonfun$iterator$2(RedBlack.NonEmpty $outer) {}
/*     */     }
/*     */     
/*     */     public class RedBlack$NonEmpty$$anonfun$iterator$1 extends AbstractFunction0<Iterator<Tuple2<A, B>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Iterator<Tuple2<A, B>> apply() {
/* 160 */         return scala.collection.Iterator$.MODULE$.single(scala.Predef$Pair$.MODULE$.apply(this.$outer.key(), this.$outer.value()));
/*     */       }
/*     */       
/*     */       public RedBlack$NonEmpty$$anonfun$iterator$1(RedBlack.NonEmpty $outer) {}
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function2<A, B, ?> f) {
/* 163 */       left().foreach(f);
/* 164 */       f.apply(key(), value());
/* 165 */       right().foreach(f);
/*     */     }
/*     */     
/*     */     public RedBlack<A>.Tree<B> rng(Option from, Option until) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   4: astore_3
/*     */       //   5: dup
/*     */       //   6: ifnonnull -> 17
/*     */       //   9: pop
/*     */       //   10: aload_3
/*     */       //   11: ifnull -> 24
/*     */       //   14: goto -> 53
/*     */       //   17: aload_3
/*     */       //   18: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   21: ifeq -> 53
/*     */       //   24: aload_2
/*     */       //   25: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   28: astore #4
/*     */       //   30: dup
/*     */       //   31: ifnonnull -> 43
/*     */       //   34: pop
/*     */       //   35: aload #4
/*     */       //   37: ifnull -> 51
/*     */       //   40: goto -> 53
/*     */       //   43: aload #4
/*     */       //   45: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   48: ifeq -> 53
/*     */       //   51: aload_0
/*     */       //   52: areturn
/*     */       //   53: aload_1
/*     */       //   54: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   57: astore #5
/*     */       //   59: dup
/*     */       //   60: ifnonnull -> 72
/*     */       //   63: pop
/*     */       //   64: aload #5
/*     */       //   66: ifnull -> 108
/*     */       //   69: goto -> 80
/*     */       //   72: aload #5
/*     */       //   74: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   77: ifne -> 108
/*     */       //   80: aload_0
/*     */       //   81: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   84: aload_0
/*     */       //   85: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   88: aload_1
/*     */       //   89: invokevirtual get : ()Ljava/lang/Object;
/*     */       //   92: invokevirtual isSmaller : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */       //   95: ifeq -> 108
/*     */       //   98: aload_0
/*     */       //   99: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   102: aload_1
/*     */       //   103: aload_2
/*     */       //   104: invokevirtual rng : (Lscala/Option;Lscala/Option;)Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   107: areturn
/*     */       //   108: aload_2
/*     */       //   109: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   112: astore #6
/*     */       //   114: dup
/*     */       //   115: ifnonnull -> 127
/*     */       //   118: pop
/*     */       //   119: aload #6
/*     */       //   121: ifnull -> 171
/*     */       //   124: goto -> 135
/*     */       //   127: aload #6
/*     */       //   129: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   132: ifne -> 171
/*     */       //   135: aload_0
/*     */       //   136: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   139: aload_2
/*     */       //   140: invokevirtual get : ()Ljava/lang/Object;
/*     */       //   143: aload_0
/*     */       //   144: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   147: invokevirtual isSmaller : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */       //   150: ifne -> 284
/*     */       //   153: aload_0
/*     */       //   154: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   157: aload_0
/*     */       //   158: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   161: aload_2
/*     */       //   162: invokevirtual get : ()Ljava/lang/Object;
/*     */       //   165: invokevirtual isSmaller : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */       //   168: ifeq -> 284
/*     */       //   171: aload_0
/*     */       //   172: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   175: aload_1
/*     */       //   176: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   179: invokevirtual rng : (Lscala/Option;Lscala/Option;)Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   182: astore #7
/*     */       //   184: aload_0
/*     */       //   185: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   188: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   191: aload_2
/*     */       //   192: invokevirtual rng : (Lscala/Option;Lscala/Option;)Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   195: astore #8
/*     */       //   197: aload #7
/*     */       //   199: aload_0
/*     */       //   200: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   203: if_acmpne -> 219
/*     */       //   206: aload #8
/*     */       //   208: aload_0
/*     */       //   209: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   212: if_acmpne -> 219
/*     */       //   215: aload_0
/*     */       //   216: goto -> 283
/*     */       //   219: aload #7
/*     */       //   221: aload_0
/*     */       //   222: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   225: invokevirtual Empty : ()Lscala/collection/immutable/RedBlack$Empty$;
/*     */       //   228: if_acmpne -> 247
/*     */       //   231: aload #8
/*     */       //   233: aload_0
/*     */       //   234: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   237: aload_0
/*     */       //   238: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   241: invokevirtual upd : (Ljava/lang/Object;Ljava/lang/Object;)Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   244: goto -> 283
/*     */       //   247: aload #8
/*     */       //   249: aload_0
/*     */       //   250: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   253: invokevirtual Empty : ()Lscala/collection/immutable/RedBlack$Empty$;
/*     */       //   256: if_acmpne -> 275
/*     */       //   259: aload #7
/*     */       //   261: aload_0
/*     */       //   262: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   265: aload_0
/*     */       //   266: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   269: invokevirtual upd : (Ljava/lang/Object;Ljava/lang/Object;)Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   272: goto -> 283
/*     */       //   275: aload_0
/*     */       //   276: aload #7
/*     */       //   278: aload #8
/*     */       //   280: invokespecial rebalance : (Lscala/collection/immutable/RedBlack$Tree;Lscala/collection/immutable/RedBlack$Tree;)Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   283: areturn
/*     */       //   284: aload_0
/*     */       //   285: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   288: aload_1
/*     */       //   289: aload_2
/*     */       //   290: invokevirtual rng : (Lscala/Option;Lscala/Option;)Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   293: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #169	-> 0
/*     */       //   #170	-> 53
/*     */       //   #171	-> 108
/*     */       //   #173	-> 171
/*     */       //   #174	-> 184
/*     */       //   #175	-> 197
/*     */       //   #176	-> 219
/*     */       //   #177	-> 247
/*     */       //   #178	-> 275
/*     */       //   #168	-> 283
/*     */       //   #172	-> 284
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	294	0	this	Lscala/collection/immutable/RedBlack$NonEmpty;
/*     */       //   0	294	1	from	Lscala/Option;
/*     */       //   0	294	2	until	Lscala/Option;
/*     */       //   184	110	7	newLeft	Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   197	97	8	newRight	Lscala/collection/immutable/RedBlack$Tree;
/*     */     }
/*     */     
/*     */     private final List unzip$1(List<NonEmpty<B>> zipper, boolean leftMost) {
/*     */       RedBlack<A>.Tree<B> next;
/*     */       while (true) {
/* 193 */         next = leftMost ? ((NonEmpty<B>)zipper.head()).left() : ((NonEmpty<B>)zipper.head()).right();
/* 194 */         if (next instanceof NonEmpty) {
/* 194 */           NonEmpty<B> nonEmpty = (NonEmpty)next;
/* 194 */           zipper = zipper.$colon$colon(nonEmpty);
/*     */           continue;
/*     */         } 
/*     */         break;
/*     */       } 
/* 196 */       if (scala$collection$immutable$RedBlack$NonEmpty$$$outer().Empty() == null) {
/* 196 */         scala$collection$immutable$RedBlack$NonEmpty$$$outer().Empty();
/* 196 */         if (next != null)
/*     */           throw new MatchError(next); 
/* 196 */       } else if (!scala$collection$immutable$RedBlack$NonEmpty$$$outer().Empty().equals(next)) {
/*     */         throw new MatchError(next);
/*     */       } 
/*     */       return zipper;
/*     */     }
/*     */     
/*     */     private final Tuple4 unzipBoth$1(RedBlack.Tree left, RedBlack.Tree right, List leftZipper, List rightZipper, int smallerDepth) {
/*     */       // Byte code:
/*     */       //   0: new scala/Tuple2
/*     */       //   3: dup
/*     */       //   4: aload_1
/*     */       //   5: aload_2
/*     */       //   6: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */       //   9: astore #19
/*     */       //   11: aload #19
/*     */       //   13: ifnull -> 95
/*     */       //   16: aload #19
/*     */       //   18: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   21: instanceof scala/collection/immutable/RedBlack$BlackTree
/*     */       //   24: ifeq -> 95
/*     */       //   27: aload #19
/*     */       //   29: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   32: checkcast scala/collection/immutable/RedBlack$BlackTree
/*     */       //   35: astore #6
/*     */       //   37: aload #19
/*     */       //   39: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   42: instanceof scala/collection/immutable/RedBlack$BlackTree
/*     */       //   45: ifeq -> 95
/*     */       //   48: aload #19
/*     */       //   50: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   53: checkcast scala/collection/immutable/RedBlack$BlackTree
/*     */       //   56: astore #7
/*     */       //   58: aload #6
/*     */       //   60: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   63: aload #7
/*     */       //   65: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   68: aload_3
/*     */       //   69: aload #6
/*     */       //   71: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*     */       //   74: aload #4
/*     */       //   76: aload #7
/*     */       //   78: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*     */       //   81: iload #5
/*     */       //   83: iconst_1
/*     */       //   84: iadd
/*     */       //   85: istore #5
/*     */       //   87: astore #4
/*     */       //   89: astore_3
/*     */       //   90: astore_2
/*     */       //   91: astore_1
/*     */       //   92: goto -> 0
/*     */       //   95: aload #19
/*     */       //   97: ifnull -> 173
/*     */       //   100: aload #19
/*     */       //   102: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   105: instanceof scala/collection/immutable/RedBlack$RedTree
/*     */       //   108: ifeq -> 173
/*     */       //   111: aload #19
/*     */       //   113: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   116: checkcast scala/collection/immutable/RedBlack$RedTree
/*     */       //   119: astore #8
/*     */       //   121: aload #19
/*     */       //   123: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   126: instanceof scala/collection/immutable/RedBlack$RedTree
/*     */       //   129: ifeq -> 173
/*     */       //   132: aload #19
/*     */       //   134: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   137: checkcast scala/collection/immutable/RedBlack$RedTree
/*     */       //   140: astore #9
/*     */       //   142: aload #8
/*     */       //   144: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   147: aload #9
/*     */       //   149: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   152: aload_3
/*     */       //   153: aload #8
/*     */       //   155: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*     */       //   158: aload #4
/*     */       //   160: aload #9
/*     */       //   162: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*     */       //   165: astore #4
/*     */       //   167: astore_3
/*     */       //   168: astore_2
/*     */       //   169: astore_1
/*     */       //   170: goto -> 0
/*     */       //   173: aload #19
/*     */       //   175: ifnull -> 221
/*     */       //   178: aload #19
/*     */       //   180: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   183: instanceof scala/collection/immutable/RedBlack$RedTree
/*     */       //   186: ifeq -> 221
/*     */       //   189: aload #19
/*     */       //   191: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   194: checkcast scala/collection/immutable/RedBlack$RedTree
/*     */       //   197: astore #10
/*     */       //   199: aload_1
/*     */       //   200: aload #10
/*     */       //   202: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   205: aload_3
/*     */       //   206: aload #4
/*     */       //   208: aload #10
/*     */       //   210: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*     */       //   213: astore #4
/*     */       //   215: astore_3
/*     */       //   216: astore_2
/*     */       //   217: astore_1
/*     */       //   218: goto -> 0
/*     */       //   221: aload #19
/*     */       //   223: ifnull -> 265
/*     */       //   226: aload #19
/*     */       //   228: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   231: instanceof scala/collection/immutable/RedBlack$RedTree
/*     */       //   234: ifeq -> 265
/*     */       //   237: aload #19
/*     */       //   239: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   242: checkcast scala/collection/immutable/RedBlack$RedTree
/*     */       //   245: astore #11
/*     */       //   247: aload #11
/*     */       //   249: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   252: aload_2
/*     */       //   253: aload_3
/*     */       //   254: aload #11
/*     */       //   256: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*     */       //   259: astore_3
/*     */       //   260: astore_2
/*     */       //   261: astore_1
/*     */       //   262: goto -> 0
/*     */       //   265: aload #19
/*     */       //   267: ifnull -> 368
/*     */       //   270: aload_0
/*     */       //   271: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   274: invokevirtual Empty : ()Lscala/collection/immutable/RedBlack$Empty$;
/*     */       //   277: aload #19
/*     */       //   279: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   282: astore #12
/*     */       //   284: dup
/*     */       //   285: ifnonnull -> 297
/*     */       //   288: pop
/*     */       //   289: aload #12
/*     */       //   291: ifnull -> 305
/*     */       //   294: goto -> 368
/*     */       //   297: aload #12
/*     */       //   299: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   302: ifeq -> 368
/*     */       //   305: aload_0
/*     */       //   306: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   309: invokevirtual Empty : ()Lscala/collection/immutable/RedBlack$Empty$;
/*     */       //   312: aload #19
/*     */       //   314: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   317: astore #13
/*     */       //   319: dup
/*     */       //   320: ifnonnull -> 332
/*     */       //   323: pop
/*     */       //   324: aload #13
/*     */       //   326: ifnull -> 340
/*     */       //   329: goto -> 368
/*     */       //   332: aload #13
/*     */       //   334: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   337: ifeq -> 368
/*     */       //   340: new scala/Tuple4
/*     */       //   343: dup
/*     */       //   344: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */       //   347: iconst_1
/*     */       //   348: invokestatic boxToBoolean : (Z)Ljava/lang/Boolean;
/*     */       //   351: iconst_0
/*     */       //   352: invokestatic boxToBoolean : (Z)Ljava/lang/Boolean;
/*     */       //   355: iload #5
/*     */       //   357: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */       //   360: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
/*     */       //   363: astore #18
/*     */       //   365: goto -> 560
/*     */       //   368: aload #19
/*     */       //   370: ifnull -> 466
/*     */       //   373: aload_0
/*     */       //   374: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   377: invokevirtual Empty : ()Lscala/collection/immutable/RedBlack$Empty$;
/*     */       //   380: aload #19
/*     */       //   382: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   385: astore #14
/*     */       //   387: dup
/*     */       //   388: ifnonnull -> 400
/*     */       //   391: pop
/*     */       //   392: aload #14
/*     */       //   394: ifnull -> 408
/*     */       //   397: goto -> 466
/*     */       //   400: aload #14
/*     */       //   402: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   405: ifeq -> 466
/*     */       //   408: aload #19
/*     */       //   410: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   413: instanceof scala/collection/immutable/RedBlack$BlackTree
/*     */       //   416: ifeq -> 466
/*     */       //   419: aload #19
/*     */       //   421: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   424: checkcast scala/collection/immutable/RedBlack$BlackTree
/*     */       //   427: astore #15
/*     */       //   429: new scala/Tuple4
/*     */       //   432: dup
/*     */       //   433: aload_0
/*     */       //   434: aload #4
/*     */       //   436: aload #15
/*     */       //   438: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*     */       //   441: iconst_1
/*     */       //   442: invokespecial unzip$1 : (Lscala/collection/immutable/List;Z)Lscala/collection/immutable/List;
/*     */       //   445: iconst_0
/*     */       //   446: invokestatic boxToBoolean : (Z)Ljava/lang/Boolean;
/*     */       //   449: iconst_1
/*     */       //   450: invokestatic boxToBoolean : (Z)Ljava/lang/Boolean;
/*     */       //   453: iload #5
/*     */       //   455: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */       //   458: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
/*     */       //   461: astore #18
/*     */       //   463: goto -> 560
/*     */       //   466: aload #19
/*     */       //   468: ifnull -> 563
/*     */       //   471: aload #19
/*     */       //   473: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   476: instanceof scala/collection/immutable/RedBlack$BlackTree
/*     */       //   479: ifeq -> 563
/*     */       //   482: aload #19
/*     */       //   484: invokevirtual _1 : ()Ljava/lang/Object;
/*     */       //   487: checkcast scala/collection/immutable/RedBlack$BlackTree
/*     */       //   490: astore #17
/*     */       //   492: aload_0
/*     */       //   493: invokevirtual scala$collection$immutable$RedBlack$NonEmpty$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   496: invokevirtual Empty : ()Lscala/collection/immutable/RedBlack$Empty$;
/*     */       //   499: aload #19
/*     */       //   501: invokevirtual _2 : ()Ljava/lang/Object;
/*     */       //   504: astore #16
/*     */       //   506: dup
/*     */       //   507: ifnonnull -> 519
/*     */       //   510: pop
/*     */       //   511: aload #16
/*     */       //   513: ifnull -> 527
/*     */       //   516: goto -> 563
/*     */       //   519: aload #16
/*     */       //   521: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   524: ifeq -> 563
/*     */       //   527: new scala/Tuple4
/*     */       //   530: dup
/*     */       //   531: aload_0
/*     */       //   532: aload_3
/*     */       //   533: aload #17
/*     */       //   535: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*     */       //   538: iconst_0
/*     */       //   539: invokespecial unzip$1 : (Lscala/collection/immutable/List;Z)Lscala/collection/immutable/List;
/*     */       //   542: iconst_0
/*     */       //   543: invokestatic boxToBoolean : (Z)Ljava/lang/Boolean;
/*     */       //   546: iconst_0
/*     */       //   547: invokestatic boxToBoolean : (Z)Ljava/lang/Boolean;
/*     */       //   550: iload #5
/*     */       //   552: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */       //   555: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
/*     */       //   558: astore #18
/*     */       //   560: aload #18
/*     */       //   562: areturn
/*     */       //   563: new scala/MatchError
/*     */       //   566: dup
/*     */       //   567: aload #19
/*     */       //   569: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   572: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #206	-> 0
/*     */       //   #207	-> 18
/*     */       //   #206	-> 27
/*     */       //   #207	-> 29
/*     */       //   #206	-> 37
/*     */       //   #207	-> 39
/*     */       //   #206	-> 48
/*     */       //   #207	-> 50
/*     */       //   #208	-> 58
/*     */       //   #206	-> 95
/*     */       //   #209	-> 102
/*     */       //   #206	-> 111
/*     */       //   #209	-> 113
/*     */       //   #206	-> 121
/*     */       //   #209	-> 123
/*     */       //   #206	-> 132
/*     */       //   #209	-> 134
/*     */       //   #210	-> 142
/*     */       //   #206	-> 173
/*     */       //   #211	-> 180
/*     */       //   #206	-> 189
/*     */       //   #211	-> 191
/*     */       //   #212	-> 199
/*     */       //   #206	-> 221
/*     */       //   #213	-> 228
/*     */       //   #206	-> 237
/*     */       //   #213	-> 239
/*     */       //   #214	-> 247
/*     */       //   #206	-> 265
/*     */       //   #215	-> 270
/*     */       //   #206	-> 277
/*     */       //   #215	-> 279
/*     */       //   #206	-> 312
/*     */       //   #215	-> 314
/*     */       //   #216	-> 340
/*     */       //   #206	-> 368
/*     */       //   #217	-> 373
/*     */       //   #206	-> 380
/*     */       //   #217	-> 382
/*     */       //   #206	-> 408
/*     */       //   #217	-> 410
/*     */       //   #206	-> 419
/*     */       //   #217	-> 421
/*     */       //   #219	-> 429
/*     */       //   #218	-> 429
/*     */       //   #217	-> 461
/*     */       //   #206	-> 466
/*     */       //   #220	-> 473
/*     */       //   #206	-> 482
/*     */       //   #220	-> 484
/*     */       //   #206	-> 499
/*     */       //   #220	-> 501
/*     */       //   #222	-> 527
/*     */       //   #221	-> 527
/*     */       //   #220	-> 558
/*     */       //   #206	-> 560
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	573	0	this	Lscala/collection/immutable/RedBlack$NonEmpty;
/*     */       //   0	573	1	left	Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   0	573	2	right	Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   0	573	3	leftZipper	Lscala/collection/immutable/List;
/*     */       //   0	573	4	rightZipper	Lscala/collection/immutable/List;
/*     */       //   0	573	5	smallerDepth	I
/*     */     }
/*     */     
/*     */     private Tuple4<List<NonEmpty<B>>, Object, Object, Object> compareDepth(RedBlack.Tree left, RedBlack.Tree right) {
/* 224 */       return unzipBoth$1(left, right, Nil$.MODULE$, Nil$.MODULE$, 0);
/*     */     }
/*     */     
/*     */     private final List findDepth$1(List zipper, int depth) {
/*     */       while (true) {
/* 230 */         boolean bool = false;
/* 230 */         $colon$colon $colon$colon = null;
/*     */         bool = true;
/*     */         if (zipper instanceof $colon$colon && ($colon$colon = ($colon$colon)zipper).hd$1() instanceof RedBlack.BlackTree) {
/* 231 */           if (depth == 1)
/*     */             return zipper; 
/* 231 */           depth--;
/* 231 */           zipper = $colon$colon.tl$1();
/*     */           continue;
/*     */         } 
/*     */         if (bool) {
/* 232 */           zipper = $colon$colon.tl$1();
/*     */           continue;
/*     */         } 
/*     */         break;
/*     */       } 
/* 233 */       if (Nil$.MODULE$ == null) {
/* 233 */         if (zipper != null)
/*     */           throw new MatchError(zipper); 
/* 233 */       } else if (!Nil$.MODULE$.equals(zipper)) {
/*     */         throw new MatchError(zipper);
/*     */       } 
/* 233 */       throw scala.sys.package$.MODULE$.error("Defect: unexpected empty zipper while computing range");
/*     */     }
/*     */     
/*     */     private RedBlack<A>.Tree<B> rebalance(RedBlack.Tree newLeft, RedBlack.Tree newRight) {
/* 238 */       RedBlack<A>.Tree<B> blkNewLeft = scala$collection$immutable$RedBlack$NonEmpty$$$outer().scala$collection$immutable$RedBlack$$blacken(newLeft);
/* 239 */       RedBlack<A>.Tree<B> blkNewRight = scala$collection$immutable$RedBlack$NonEmpty$$$outer().scala$collection$immutable$RedBlack$$blacken(newRight);
/* 240 */       Tuple4<List<NonEmpty<B>>, Object, Object, Object> tuple4 = compareDepth(blkNewLeft, blkNewRight);
/* 240 */       if (tuple4 != null) {
/* 240 */         Tuple4 tuple41 = new Tuple4(tuple4._1(), tuple4._2(), tuple4._3(), tuple4._4());
/* 240 */         List zipper = (List)tuple41._1();
/* 240 */         boolean levelled = BoxesRunTime.unboxToBoolean(tuple41._2()), leftMost = BoxesRunTime.unboxToBoolean(tuple41._3());
/* 240 */         int smallerDepth = BoxesRunTime.unboxToInt(tuple41._4());
/* 245 */         List<RedBlack<A>.Tree<B>> zipFrom = findDepth$1(zipper, smallerDepth);
/* 246 */         RedBlack.RedTree union = leftMost ? 
/* 247 */           new RedBlack.RedTree<B>(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), key(), value(), blkNewLeft, zipFrom.head()) : 
/*     */           
/* 249 */           new RedBlack.RedTree<B>(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), key(), value(), zipFrom.head(), blkNewRight);
/* 251 */         RedBlack<A>.Tree<B> zippedTree = (RedBlack.Tree)((LinearSeqOptimized)zipFrom.tail()).foldLeft(union, (Function2)new RedBlack$NonEmpty$$anonfun$1(this, leftMost));
/* 257 */         return levelled ? new RedBlack.BlackTree<B>(scala$collection$immutable$RedBlack$NonEmpty$$$outer(), key(), value(), blkNewLeft, blkNewRight) : zippedTree;
/*     */       } 
/*     */       throw new MatchError(tuple4);
/*     */     }
/*     */     
/*     */     public class RedBlack$NonEmpty$$anonfun$1 extends AbstractFunction2<RedBlack<A>.Tree<B>, NonEmpty<B>, NonEmpty<B>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final boolean leftMost$1;
/*     */       
/*     */       public RedBlack$NonEmpty$$anonfun$1(RedBlack.NonEmpty $outer, boolean leftMost$1) {}
/*     */       
/*     */       public final RedBlack<A>.NonEmpty<B> apply(RedBlack.Tree tree, RedBlack.NonEmpty node) {
/*     */         return this.leftMost$1 ? this.$outer.scala$collection$immutable$RedBlack$NonEmpty$$balanceLeft(node.isBlack(), node.key(), node.value(), tree, node.right()) : this.$outer.scala$collection$immutable$RedBlack$NonEmpty$$balanceRight(node.isBlack(), node.key(), node.value(), node.left(), tree);
/*     */       }
/*     */     }
/*     */     
/*     */     public A first() {
/* 260 */       return left().isEmpty() ? key() : left().first();
/*     */     }
/*     */     
/*     */     public A last() {
/* 261 */       return right().isEmpty() ? key() : right().last();
/*     */     }
/*     */     
/*     */     public int count() {
/* 262 */       return 1 + left().count() + right().count();
/*     */     }
/*     */     
/*     */     public abstract A key();
/*     */     
/*     */     public abstract B value();
/*     */     
/*     */     public abstract RedBlack<A>.Tree<B> left();
/*     */     
/*     */     public abstract RedBlack<A>.Tree<B> right();
/*     */   }
/*     */   
/*     */   private Empty$ Empty$lzycompute() {
/* 264 */     synchronized (this) {
/* 264 */       if (this.Empty$module == null)
/* 264 */         this.Empty$module = new Empty$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/RedBlack}} */
/* 264 */       return this.Empty$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public Empty$ Empty() {
/* 264 */     return (this.Empty$module == null) ? Empty$lzycompute() : this.Empty$module;
/*     */   }
/*     */   
/*     */   public class Empty$ extends Tree<scala.runtime.Nothing$> implements Product, Serializable {
/*     */     public String productPrefix() {
/* 264 */       return "Empty";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 264 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 264 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 264 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 264 */       return x$1 instanceof Empty$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 264 */       return 67081517;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 264 */       return "Empty";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 264 */       return scala$collection$immutable$RedBlack$Empty$$$outer().Empty();
/*     */     }
/*     */     
/*     */     public Empty$(RedBlack<A> $outer) {
/* 264 */       super($outer);
/* 264 */       Product.class.$init$(this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 265 */       return true;
/*     */     }
/*     */     
/*     */     public boolean isBlack() {
/* 266 */       return true;
/*     */     }
/*     */     
/*     */     public RedBlack<A>.Tree<scala.runtime.Nothing$> lookup(Object k) {
/* 267 */       return this;
/*     */     }
/*     */     
/*     */     public <B> RedBlack<A>.Tree<B> upd(Object k, Object v) {
/* 268 */       return new RedBlack.RedTree<B>(scala$collection$immutable$RedBlack$Empty$$$outer(), (A)k, (B)v, scala$collection$immutable$RedBlack$Empty$$$outer().Empty(), scala$collection$immutable$RedBlack$Empty$$$outer().Empty());
/*     */     }
/*     */     
/*     */     public RedBlack<A>.Tree<scala.runtime.Nothing$> del(Object k) {
/* 269 */       return this;
/*     */     }
/*     */     
/*     */     public RedBlack<A>.NonEmpty<scala.runtime.Nothing$> smallest() {
/* 270 */       throw new NoSuchElementException("empty map");
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<A, scala.runtime.Nothing$>> iterator() {
/* 271 */       return scala.collection.Iterator$.MODULE$.empty();
/*     */     }
/*     */     
/*     */     public Stream<Tuple2<A, scala.runtime.Nothing$>> toStream() {
/* 272 */       return Stream$.MODULE$.empty();
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function2 f) {}
/*     */     
/*     */     public Empty$ rng(Option from, Option until) {
/* 276 */       return this;
/*     */     }
/*     */     
/*     */     public scala.runtime.Nothing$ first() {
/* 277 */       throw new NoSuchElementException("empty map");
/*     */     }
/*     */     
/*     */     public scala.runtime.Nothing$ last() {
/* 278 */       throw new NoSuchElementException("empty map");
/*     */     }
/*     */     
/*     */     public int count() {
/* 279 */       return 0;
/*     */     }
/*     */   }
/*     */   
/*     */   private RedTree$ RedTree$lzycompute() {
/* 281 */     synchronized (this) {
/* 281 */       if (this.RedTree$module == null)
/* 281 */         this.RedTree$module = new RedTree$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/RedBlack}} */
/* 281 */       return this.RedTree$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public RedTree$ RedTree() {
/* 281 */     return (this.RedTree$module == null) ? RedTree$lzycompute() : this.RedTree$module;
/*     */   }
/*     */   
/*     */   public class RedTree$ implements Serializable {
/*     */     public final String toString() {
/* 281 */       return "RedTree";
/*     */     }
/*     */     
/*     */     public <B> RedBlack<A>.RedTree<B> apply(Object key, Object value, RedBlack<A>.Tree<B> left, RedBlack<A>.Tree<B> right) {
/* 281 */       return new RedBlack.RedTree<B>(this.$outer, (A)key, (B)value, left, right);
/*     */     }
/*     */     
/*     */     public <B> Option<Tuple4<A, B, RedBlack<A>.Tree<B>, RedBlack<A>.Tree<B>>> unapply(RedBlack.RedTree x$0) {
/* 281 */       return (x$0 == null) ? (Option<Tuple4<A, B, RedBlack<A>.Tree<B>, RedBlack<A>.Tree<B>>>)scala.None$.MODULE$ : (Option<Tuple4<A, B, RedBlack<A>.Tree<B>, RedBlack<A>.Tree<B>>>)new Some(new Tuple4(x$0.key(), x$0.value(), x$0.left(), x$0.right()));
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 281 */       return this.$outer.RedTree();
/*     */     }
/*     */     
/*     */     public RedTree$(RedBlack $outer) {}
/*     */   }
/*     */   
/*     */   public class RedTree<B> extends NonEmpty<B> implements Product, Serializable {
/*     */     private final A key;
/*     */     
/*     */     private final B value;
/*     */     
/*     */     private final RedBlack<A>.Tree<B> left;
/*     */     
/*     */     private final RedBlack<A>.Tree<B> right;
/*     */     
/*     */     public A key() {
/* 281 */       return this.key;
/*     */     }
/*     */     
/*     */     public <B> RedTree<B> copy(Object key, Object value, RedBlack<A>.Tree<B> left, RedBlack<A>.Tree<B> right) {
/* 281 */       return new RedTree(scala$collection$immutable$RedBlack$RedTree$$$outer(), (A)key, 
/* 282 */           (B)value, 
/* 283 */           left, 
/* 284 */           right);
/*     */     }
/*     */     
/*     */     public <B> A copy$default$1() {
/*     */       return key();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*     */       return "RedTree";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*     */       return 4;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*     */       switch (x$1) {
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
/*     */       return key();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*     */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*     */       return x$1 instanceof RedTree;
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
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 285
/*     */       //   5: aload_1
/*     */       //   6: instanceof scala/collection/immutable/RedBlack$RedTree
/*     */       //   9: ifeq -> 31
/*     */       //   12: aload_1
/*     */       //   13: checkcast scala/collection/immutable/RedBlack$RedTree
/*     */       //   16: invokevirtual scala$collection$immutable$RedBlack$RedTree$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   19: aload_0
/*     */       //   20: invokevirtual scala$collection$immutable$RedBlack$RedTree$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   23: if_acmpne -> 31
/*     */       //   26: iconst_1
/*     */       //   27: istore_2
/*     */       //   28: goto -> 33
/*     */       //   31: iconst_0
/*     */       //   32: istore_2
/*     */       //   33: iload_2
/*     */       //   34: ifeq -> 289
/*     */       //   37: aload_1
/*     */       //   38: checkcast scala/collection/immutable/RedBlack$RedTree
/*     */       //   41: astore #9
/*     */       //   43: aload_0
/*     */       //   44: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   47: aload #9
/*     */       //   49: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   52: astore #4
/*     */       //   54: dup
/*     */       //   55: astore_3
/*     */       //   56: aload #4
/*     */       //   58: if_acmpne -> 65
/*     */       //   61: iconst_1
/*     */       //   62: goto -> 117
/*     */       //   65: aload_3
/*     */       //   66: ifnonnull -> 73
/*     */       //   69: iconst_0
/*     */       //   70: goto -> 117
/*     */       //   73: aload_3
/*     */       //   74: instanceof java/lang/Number
/*     */       //   77: ifeq -> 92
/*     */       //   80: aload_3
/*     */       //   81: checkcast java/lang/Number
/*     */       //   84: aload #4
/*     */       //   86: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */       //   89: goto -> 117
/*     */       //   92: aload_3
/*     */       //   93: instanceof java/lang/Character
/*     */       //   96: ifeq -> 111
/*     */       //   99: aload_3
/*     */       //   100: checkcast java/lang/Character
/*     */       //   103: aload #4
/*     */       //   105: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */       //   108: goto -> 117
/*     */       //   111: aload_3
/*     */       //   112: aload #4
/*     */       //   114: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   117: ifeq -> 281
/*     */       //   120: aload_0
/*     */       //   121: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   124: aload #9
/*     */       //   126: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   129: astore #6
/*     */       //   131: dup
/*     */       //   132: astore #5
/*     */       //   134: aload #6
/*     */       //   136: if_acmpne -> 143
/*     */       //   139: iconst_1
/*     */       //   140: goto -> 201
/*     */       //   143: aload #5
/*     */       //   145: ifnonnull -> 152
/*     */       //   148: iconst_0
/*     */       //   149: goto -> 201
/*     */       //   152: aload #5
/*     */       //   154: instanceof java/lang/Number
/*     */       //   157: ifeq -> 173
/*     */       //   160: aload #5
/*     */       //   162: checkcast java/lang/Number
/*     */       //   165: aload #6
/*     */       //   167: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */       //   170: goto -> 201
/*     */       //   173: aload #5
/*     */       //   175: instanceof java/lang/Character
/*     */       //   178: ifeq -> 194
/*     */       //   181: aload #5
/*     */       //   183: checkcast java/lang/Character
/*     */       //   186: aload #6
/*     */       //   188: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */       //   191: goto -> 201
/*     */       //   194: aload #5
/*     */       //   196: aload #6
/*     */       //   198: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   201: ifeq -> 281
/*     */       //   204: aload_0
/*     */       //   205: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   208: aload #9
/*     */       //   210: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   213: astore #7
/*     */       //   215: dup
/*     */       //   216: ifnonnull -> 228
/*     */       //   219: pop
/*     */       //   220: aload #7
/*     */       //   222: ifnull -> 236
/*     */       //   225: goto -> 281
/*     */       //   228: aload #7
/*     */       //   230: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   233: ifeq -> 281
/*     */       //   236: aload_0
/*     */       //   237: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   240: aload #9
/*     */       //   242: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   245: astore #8
/*     */       //   247: dup
/*     */       //   248: ifnonnull -> 260
/*     */       //   251: pop
/*     */       //   252: aload #8
/*     */       //   254: ifnull -> 268
/*     */       //   257: goto -> 281
/*     */       //   260: aload #8
/*     */       //   262: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   265: ifeq -> 281
/*     */       //   268: aload #9
/*     */       //   270: aload_0
/*     */       //   271: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   274: ifeq -> 281
/*     */       //   277: iconst_1
/*     */       //   278: goto -> 282
/*     */       //   281: iconst_0
/*     */       //   282: ifeq -> 289
/*     */       //   285: iconst_1
/*     */       //   286: goto -> 290
/*     */       //   289: iconst_0
/*     */       //   290: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #281	-> 0
/*     */       //   #236	-> 26
/*     */       //   #281	-> 33
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	291	0	this	Lscala/collection/immutable/RedBlack$RedTree;
/*     */       //   0	291	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public RedTree(RedBlack<A> $outer, Object key, Object value, RedBlack<A>.Tree<B> left, RedBlack<A>.Tree<B> right) {
/*     */       super($outer);
/*     */       Product.class.$init$(this);
/*     */     }
/*     */     
/*     */     public B value() {
/*     */       return this.value;
/*     */     }
/*     */     
/*     */     public <B> B copy$default$2() {
/*     */       return value();
/*     */     }
/*     */     
/*     */     public RedBlack<A>.Tree<B> left() {
/*     */       return this.left;
/*     */     }
/*     */     
/*     */     public <B> RedBlack<A>.Tree<B> copy$default$3() {
/*     */       return left();
/*     */     }
/*     */     
/*     */     public RedBlack<A>.Tree<B> right() {
/* 284 */       return this.right;
/*     */     }
/*     */     
/*     */     public <B> RedBlack<A>.Tree<B> copy$default$4() {
/* 284 */       return right();
/*     */     }
/*     */     
/*     */     public boolean isBlack() {
/* 285 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   private BlackTree$ BlackTree$lzycompute() {
/* 287 */     synchronized (this) {
/* 287 */       if (this.BlackTree$module == null)
/* 287 */         this.BlackTree$module = new BlackTree$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/RedBlack}} */
/* 287 */       return this.BlackTree$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public BlackTree$ BlackTree() {
/* 287 */     return (this.BlackTree$module == null) ? BlackTree$lzycompute() : this.BlackTree$module;
/*     */   }
/*     */   
/*     */   public abstract boolean isSmaller(A paramA1, A paramA2);
/*     */   
/*     */   public class BlackTree$ implements Serializable {
/*     */     public final String toString() {
/* 287 */       return "BlackTree";
/*     */     }
/*     */     
/*     */     public <B> RedBlack<A>.BlackTree<B> apply(Object key, Object value, RedBlack<A>.Tree<B> left, RedBlack<A>.Tree<B> right) {
/* 287 */       return new RedBlack.BlackTree<B>(this.$outer, (A)key, (B)value, left, right);
/*     */     }
/*     */     
/*     */     public <B> Option<Tuple4<A, B, RedBlack<A>.Tree<B>, RedBlack<A>.Tree<B>>> unapply(RedBlack.BlackTree x$0) {
/* 287 */       return (x$0 == null) ? (Option<Tuple4<A, B, RedBlack<A>.Tree<B>, RedBlack<A>.Tree<B>>>)scala.None$.MODULE$ : (Option<Tuple4<A, B, RedBlack<A>.Tree<B>, RedBlack<A>.Tree<B>>>)new Some(new Tuple4(x$0.key(), x$0.value(), x$0.left(), x$0.right()));
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 287 */       return this.$outer.BlackTree();
/*     */     }
/*     */     
/*     */     public BlackTree$(RedBlack $outer) {}
/*     */   }
/*     */   
/*     */   public class BlackTree<B> extends NonEmpty<B> implements Product, Serializable {
/*     */     private final A key;
/*     */     
/*     */     private final B value;
/*     */     
/*     */     private final RedBlack<A>.Tree<B> left;
/*     */     
/*     */     private final RedBlack<A>.Tree<B> right;
/*     */     
/*     */     public A key() {
/* 287 */       return this.key;
/*     */     }
/*     */     
/*     */     public <B> BlackTree<B> copy(Object key, Object value, RedBlack<A>.Tree<B> left, RedBlack<A>.Tree<B> right) {
/* 287 */       return new BlackTree(scala$collection$immutable$RedBlack$BlackTree$$$outer(), (A)key, 
/* 288 */           (B)value, 
/* 289 */           left, 
/* 290 */           right);
/*     */     }
/*     */     
/*     */     public <B> A copy$default$1() {
/*     */       return key();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*     */       return "BlackTree";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*     */       return 4;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*     */       switch (x$1) {
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
/*     */       return key();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*     */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*     */       return x$1 instanceof BlackTree;
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
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 285
/*     */       //   5: aload_1
/*     */       //   6: instanceof scala/collection/immutable/RedBlack$BlackTree
/*     */       //   9: ifeq -> 31
/*     */       //   12: aload_1
/*     */       //   13: checkcast scala/collection/immutable/RedBlack$BlackTree
/*     */       //   16: invokevirtual scala$collection$immutable$RedBlack$BlackTree$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   19: aload_0
/*     */       //   20: invokevirtual scala$collection$immutable$RedBlack$BlackTree$$$outer : ()Lscala/collection/immutable/RedBlack;
/*     */       //   23: if_acmpne -> 31
/*     */       //   26: iconst_1
/*     */       //   27: istore_2
/*     */       //   28: goto -> 33
/*     */       //   31: iconst_0
/*     */       //   32: istore_2
/*     */       //   33: iload_2
/*     */       //   34: ifeq -> 289
/*     */       //   37: aload_1
/*     */       //   38: checkcast scala/collection/immutable/RedBlack$BlackTree
/*     */       //   41: astore #9
/*     */       //   43: aload_0
/*     */       //   44: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   47: aload #9
/*     */       //   49: invokevirtual key : ()Ljava/lang/Object;
/*     */       //   52: astore #4
/*     */       //   54: dup
/*     */       //   55: astore_3
/*     */       //   56: aload #4
/*     */       //   58: if_acmpne -> 65
/*     */       //   61: iconst_1
/*     */       //   62: goto -> 117
/*     */       //   65: aload_3
/*     */       //   66: ifnonnull -> 73
/*     */       //   69: iconst_0
/*     */       //   70: goto -> 117
/*     */       //   73: aload_3
/*     */       //   74: instanceof java/lang/Number
/*     */       //   77: ifeq -> 92
/*     */       //   80: aload_3
/*     */       //   81: checkcast java/lang/Number
/*     */       //   84: aload #4
/*     */       //   86: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */       //   89: goto -> 117
/*     */       //   92: aload_3
/*     */       //   93: instanceof java/lang/Character
/*     */       //   96: ifeq -> 111
/*     */       //   99: aload_3
/*     */       //   100: checkcast java/lang/Character
/*     */       //   103: aload #4
/*     */       //   105: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */       //   108: goto -> 117
/*     */       //   111: aload_3
/*     */       //   112: aload #4
/*     */       //   114: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   117: ifeq -> 281
/*     */       //   120: aload_0
/*     */       //   121: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   124: aload #9
/*     */       //   126: invokevirtual value : ()Ljava/lang/Object;
/*     */       //   129: astore #6
/*     */       //   131: dup
/*     */       //   132: astore #5
/*     */       //   134: aload #6
/*     */       //   136: if_acmpne -> 143
/*     */       //   139: iconst_1
/*     */       //   140: goto -> 201
/*     */       //   143: aload #5
/*     */       //   145: ifnonnull -> 152
/*     */       //   148: iconst_0
/*     */       //   149: goto -> 201
/*     */       //   152: aload #5
/*     */       //   154: instanceof java/lang/Number
/*     */       //   157: ifeq -> 173
/*     */       //   160: aload #5
/*     */       //   162: checkcast java/lang/Number
/*     */       //   165: aload #6
/*     */       //   167: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */       //   170: goto -> 201
/*     */       //   173: aload #5
/*     */       //   175: instanceof java/lang/Character
/*     */       //   178: ifeq -> 194
/*     */       //   181: aload #5
/*     */       //   183: checkcast java/lang/Character
/*     */       //   186: aload #6
/*     */       //   188: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */       //   191: goto -> 201
/*     */       //   194: aload #5
/*     */       //   196: aload #6
/*     */       //   198: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   201: ifeq -> 281
/*     */       //   204: aload_0
/*     */       //   205: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   208: aload #9
/*     */       //   210: invokevirtual left : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   213: astore #7
/*     */       //   215: dup
/*     */       //   216: ifnonnull -> 228
/*     */       //   219: pop
/*     */       //   220: aload #7
/*     */       //   222: ifnull -> 236
/*     */       //   225: goto -> 281
/*     */       //   228: aload #7
/*     */       //   230: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   233: ifeq -> 281
/*     */       //   236: aload_0
/*     */       //   237: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   240: aload #9
/*     */       //   242: invokevirtual right : ()Lscala/collection/immutable/RedBlack$Tree;
/*     */       //   245: astore #8
/*     */       //   247: dup
/*     */       //   248: ifnonnull -> 260
/*     */       //   251: pop
/*     */       //   252: aload #8
/*     */       //   254: ifnull -> 268
/*     */       //   257: goto -> 281
/*     */       //   260: aload #8
/*     */       //   262: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   265: ifeq -> 281
/*     */       //   268: aload #9
/*     */       //   270: aload_0
/*     */       //   271: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   274: ifeq -> 281
/*     */       //   277: iconst_1
/*     */       //   278: goto -> 282
/*     */       //   281: iconst_0
/*     */       //   282: ifeq -> 289
/*     */       //   285: iconst_1
/*     */       //   286: goto -> 290
/*     */       //   289: iconst_0
/*     */       //   290: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #287	-> 0
/*     */       //   #236	-> 26
/*     */       //   #287	-> 33
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	291	0	this	Lscala/collection/immutable/RedBlack$BlackTree;
/*     */       //   0	291	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public BlackTree(RedBlack<A> $outer, Object key, Object value, RedBlack<A>.Tree<B> left, RedBlack<A>.Tree<B> right) {
/*     */       super($outer);
/*     */       Product.class.$init$(this);
/*     */     }
/*     */     
/*     */     public B value() {
/*     */       return this.value;
/*     */     }
/*     */     
/*     */     public <B> B copy$default$2() {
/*     */       return value();
/*     */     }
/*     */     
/*     */     public RedBlack<A>.Tree<B> left() {
/*     */       return this.left;
/*     */     }
/*     */     
/*     */     public <B> RedBlack<A>.Tree<B> copy$default$3() {
/*     */       return left();
/*     */     }
/*     */     
/*     */     public RedBlack<A>.Tree<B> right() {
/* 290 */       return this.right;
/*     */     }
/*     */     
/*     */     public <B> RedBlack<A>.Tree<B> copy$default$4() {
/* 290 */       return right();
/*     */     }
/*     */     
/*     */     public boolean isBlack() {
/* 291 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\RedBlack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */