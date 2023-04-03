/*      */ package scala.collection;
/*      */ 
/*      */ import java.util.NoSuchElementException;
/*      */ import scala.Function0;
/*      */ import scala.Function1;
/*      */ import scala.Function2;
/*      */ import scala.None$;
/*      */ import scala.Option;
/*      */ import scala.PartialFunction;
/*      */ import scala.Predef$;
/*      */ import scala.Serializable;
/*      */ import scala.Some;
/*      */ import scala.Tuple2;
/*      */ import scala.collection.immutable.List;
/*      */ import scala.collection.immutable.List$;
/*      */ import scala.collection.immutable.Nil$;
/*      */ import scala.collection.immutable.Stream;
/*      */ import scala.collection.immutable.StringLike;
/*      */ import scala.collection.immutable.StringOps;
/*      */ import scala.collection.mutable.ArrayBuffer;
/*      */ import scala.collection.mutable.ArrayBuffer$;
/*      */ import scala.collection.mutable.Queue;
/*      */ import scala.collection.mutable.StringBuilder;
/*      */ import scala.reflect.ScalaSignature;
/*      */ import scala.runtime.AbstractFunction0;
/*      */ import scala.runtime.AbstractFunction1;
/*      */ import scala.runtime.BooleanRef;
/*      */ import scala.runtime.BoxesRunTime;
/*      */ import scala.runtime.IntRef;
/*      */ import scala.runtime.Nothing$;
/*      */ import scala.runtime.ObjectRef;
/*      */ import scala.runtime.RichInt$;
/*      */ import scala.runtime.VolatileByteRef;
/*      */ 
/*      */ @ScalaSignature(bytes = "\006\001\021\035v!B\001\003\021\0039\021\001C%uKJ\fGo\034:\013\005\r!\021AC2pY2,7\r^5p]*\tQ!A\003tG\006d\027m\001\001\021\005!IQ\"\001\002\007\013)\021\001\022A\006\003\021%#XM]1u_J\034\"!\003\007\021\0055qQ\"\001\003\n\005=!!AB!osJ+g\rC\003\022\023\021\005!#\001\004=S:LGO\020\013\002\017!)A#\003C\002+\005!\022\n^3sCR|'oQ1o\005VLG\016\032$s_6,\"A\006\021\026\003]\001B\001G\016\037S9\021\001\"G\005\0035\t\tq\002\026:bm\026\0248/\0312mK>s7-Z\005\0039u\021ACQ;gM\026\024X\rZ\"b]\n+\030\016\0343Ge>l'B\001\016\003!\ty\002\005\004\001\005\013\005\032\"\031\001\022\003\003\005\013\"a\t\024\021\0055!\023BA\023\005\005\035qu\016\0365j]\036\004\"!D\024\n\005!\"!aA!osB\021\001B\013\004\b\025\t\001\n1!\001,+\ta\023gE\002+\0315\0022\001\003\0301\023\ty#AA\bUe\0064XM]:bE2,wJ\\2f!\ty\022\007\002\004\"U\021\025\rA\t\005\006g)\"\t\001N\001\007I%t\027\016\036\023\025\003U\002\"!\004\034\n\005]\"!\001B+oSRDQ!\017\026\005\002i\n1a]3r+\005Y\004c\001\005+a!)QH\013D\001}\0059\001.Y:OKb$X#A \021\0055\001\025BA!\005\005\035\021un\0347fC:DQa\021\026\007\002\021\013AA\\3yiR\t\001\007C\003GU\021\005a(A\004jg\026k\007\017^=\t\013!SC\021\001 \002%%\034HK]1wKJ\034\030M\0317f\003\036\f\027N\034\005\006\025*\"\tAP\001\020Q\006\034H)\0324j]&$XmU5{K\")AJ\013C\001\033\006!A/Y6f)\tYd\nC\003P\027\002\007\001+A\001o!\ti\021+\003\002S\t\t\031\021J\034;\t\013QSC\021A+\002\t\021\024x\016\035\013\003wYCQaT*A\002ACQ\001\027\026\005\002e\013Qa\0357jG\026$2a\017.]\021\025Yv\0131\001Q\003\0211'o\\7\t\013u;\006\031\001)\002\013UtG/\0337\t\013}SC\021\0011\002\0075\f\007/\006\002bIR\021!M\032\t\004\021)\032\007CA\020e\t\025)gL1\001#\005\005\021\005\"B4_\001\004A\027!\0014\021\t5I\007gY\005\003U\022\021\021BR;oGRLwN\\\031\t\0131TC\021A7\002\025\021\002H.^:%a2,8/\006\002ocR\021qn\035\t\004\021)\002\bCA\020r\t\025)7N1\001s#\t\001d\005\003\004uW\022\005\r!^\001\005i\"\fG\017E\002\016mbL!a\036\003\003\021q\022\027P\\1nKz\0022\001C=q\023\tQ(A\001\nHK:$&/\031<feN\f'\r\\3P]\016,\007\"\002?+\t\003i\030a\0024mCRl\025\r]\013\004}\006\rAcA@\002\006A!\001BKA\001!\ry\0221\001\003\006Kn\024\rA\t\005\007On\004\r!a\002\021\0135I\007'!\003\021\t!I\030\021\001\005\b\003\033QC\021AA\b\003\0311\027\016\034;feR\0311(!\005\t\021\005M\0211\002a\001\003+\t\021\001\035\t\005\033%\004t\bC\004\002\032)\"\t!a\007\002\027\r|'O]3ta>tGm]\013\005\003;\tY\003\006\003\002 \0055BcA \002\"!A\0211CA\f\001\004\t\031\003E\004\016\003K\001\024\021F \n\007\005\035BAA\005Gk:\034G/[8oeA\031q$a\013\005\r\025\f9B1\001#\021\035!\030q\003a\001\003_\001B\001C=\002*!9\0211\007\026\005\002\005U\022AC<ji\"4\025\016\034;feR\0311(a\016\t\021\005M\021\021\007a\001\003+Aq!a\017+\t\003\ti$A\005gS2$XM\035(piR\0311(a\020\t\021\005M\021\021\ba\001\003+Aq!a\021+\t\003\t)%A\004d_2dWm\031;\026\t\005\035\023Q\n\013\005\003\023\ny\005\005\003\tU\005-\003cA\020\002N\0211Q-!\021C\002\tB\001\"!\025\002B\001\007\0211K\001\003a\032\004b!DA+a\005-\023bAA,\t\ty\001+\031:uS\006dg)\0368di&|g\016\013\005\002B\005m\023qMA6!\021\ti&a\031\016\005\005}#bAA1\t\005Q\021M\0348pi\006$\030n\0348\n\t\005\025\024q\f\002\n[&<'/\031;j_:\f#!!\033\002\031\002\034w\016\0347fGR\004\007\005[1tA\rD\027M\\4fI:\002C\013[3!aJ,g/[8vg\002\022W\r[1wS>\024\beY1oA\t,\007E]3qe>$WoY3eA]LG\017\033\021ai>\034V-\0351/C\t\ti'A\0033]ar\003\007C\004\002r)\"\t!a\035\002\021M\034\027M\034'fMR,B!!\036\002~Q!\021qOAC)\021\tI(a \021\t!Q\0231\020\t\004?\005uDAB3\002p\t\007!\005\003\005\002\002\006=\004\031AAB\003\ty\007\017\005\005\016\003K\tY\bMA>\021!\t9)a\034A\002\005m\024!\001>\t\017\005-%\006\"\001\002\016\006I1oY1o%&<\007\016^\013\005\003\037\0139\n\006\003\002\022\006uE\003BAJ\0033\003B\001\003\026\002\026B\031q$a&\005\r\025\fII1\001#\021!\t\t)!#A\002\005m\005\003C\007\002&A\n)*!&\t\021\005\035\025\021\022a\001\003+Cq!!)+\t\003\t\031+A\005uC.,w\013[5mKR\0311(!*\t\021\005M\021q\024a\001\003+Aq!!++\t\003\tY+A\005qCJ$\030\016^5p]R!\021QVAZ!\025i\021qV\036<\023\r\t\t\f\002\002\007)V\004H.\032\032\t\021\005M\021q\025a\001\003+Aq!a.+\t\003\tI,\001\003ta\006tG\003BAW\003wC\001\"a\005\0026\002\007\021Q\003\005\b\003SC\021AAa\003%!'o\0349XQ&dW\rF\002<\003\007D\001\"a\005\002>\002\007\021Q\003\005\b\003\017TC\021AAe\003\rQ\030\016]\013\005\003\027\f\031\016\006\003\002N\006U\007\003\002\005+\003\037\004b!DAXa\005E\007cA\020\002T\0221Q-!2C\002\tBq\001^Ac\001\004\t9\016\005\003\tU\005E\007bBAnU\021\005\021Q\\\001\006a\006$Gk\\\013\005\003?\f)\017\006\004\002b\006%\030Q\036\t\005\021)\n\031\017E\002 \003K$q!a:\002Z\n\007!O\001\002Bc!9\0211^Am\001\004\001\026a\0017f]\"A\021q^Am\001\004\t\031/\001\003fY\026l\007bBAzU\021\005\021Q_\001\ru&\004x+\033;i\023:$W\r_\013\003\003o\004B\001\003\026\002zB)Q\"a,1!\"9\021Q \026\005\002\005}\030A\002>ja\006cG.\006\005\003\002\tU!\021\002B\007)!\021\031Aa\006\003\034\t}\001\003\002\005+\005\013\001r!DAX\005\017\021Y\001E\002 \005\023!q!a:\002|\n\007!\017E\002 \005\033!\001Ba\004\002|\n\007!\021\003\002\003\005F\n2Aa\005'!\ry\"Q\003\003\007K\006m(\031\001\022\t\017Q\fY\0201\001\003\032A!\001B\013B\n\021!\021i\"a?A\002\t\035\021\001\003;iSN,E.Z7\t\021\t\005\0221 a\001\005\027\t\001\002\0365bi\026cW-\034\005\b\005KQC\021\001B\024\003\0351wN]3bG\",BA!\013\0032Q\031QGa\013\t\017\035\024\031\0031\001\003.A)Q\"\033\031\0030A\031qD!\r\005\017\tM\"1\005b\001E\t\tQ\013C\004\0038)\"\tA!\017\002\r\031|'/\0317m)\ry$1\b\005\t\003'\021)\0041\001\002\026!9!q\b\026\005\002\t\005\023AB3ySN$8\017F\002@\005\007B\001\"a\005\003>\001\007\021Q\003\005\b\005\017RC\021\001B%\003!\031wN\034;bS:\034HcA \003L!9\021q\036B#\001\0041\003b\002B(U\021\005!\021K\001\005M&tG\r\006\003\003T\te\003\003B\007\003VAJ1Aa\026\005\005\031y\005\017^5p]\"A\0211\003B'\001\004\t)\002C\004\003^)\"\tAa\030\002\025%tG-\032=XQ\026\024X\rF\002Q\005CB\001\"a\005\003\\\001\007\021Q\003\005\b\005KRC\021\001B4\003\035Ig\016Z3y\037\032,BA!\033\003pQ\031\001Ka\033\t\021\005=(1\ra\001\005[\0022a\bB8\t\031)'1\rb\001e\"9!1\017\026\005\002\tU\024\001\0032vM\032,'/\0323\026\005\t]\004\003\002\005\003zAJ1Aa\037\003\005A\021UO\0324fe\026$\027\n^3sCR|'O\002\004\003\000)\002!\021\021\002\020\017J|W\017]3e\023R,'/\031;peV!!1\021BJ'\031\021iH!\"\003\026B)\001Ba\"\003\f&\031!\021\022\002\003!\005\0237\017\036:bGRLE/\032:bi>\024\b#\002\005\003\016\nE\025b\001BH\005\t\0311+Z9\021\007}\021\031\n\002\004f\005{\022\rA\035\t\005\021)\022Y\t\003\006\003\032\nu$\021!Q\001\nm\nAa]3mM\"Q!Q\024B?\005\003\005\013\021\002)\002\tML'0\032\005\013\005C\023iH!A!\002\023\001\026\001B:uKBDq!\005B?\t\003\021)\013\006\005\003(\n-&Q\026BX!\031\021IK! \003\0226\t!\006C\004\003\032\n\r\006\031A\036\t\017\tu%1\025a\001!\"9!\021\025BR\001\004\001\006\"\003BZ\005{\002\013\025\002B[\003\031\021WO\0324feB1!q\027B_\005#k!A!/\013\007\tm&!A\004nkR\f'\r\\3\n\t\t}&\021\030\002\f\003J\024\030-\037\"vM\032,'\017\003\005\003D\nu\004\025)\003@\003\0311\027\016\0347fI\"A!q\031B?A\003&q(\001\005`a\006\024H/[1m\021%\021YM! !B\023\021i-A\002qC\022\004R!\004B+\005\037\004R!\004Bi\005#K1Aa5\005\005%1UO\\2uS>t\007\007\003\005\003X\nuD\021\001Bm\003-9\030\016\0365QC\022$\027N\\4\025\t\tm'Q\\\007\003\005{B\021Ba8\003V\022\005\rA!9\002\003a\004B!\004<\003\022\"A!Q\035B?\t\003\0219/A\006xSRD\007+\031:uS\006dG\003\002Bn\005SDqAa8\003d\002\007q\b\003\005\003n\nuD\021\002Bx\003E!\030m[3EKN$(/^2uSZ,G.\037\013\005\005c\024\031\020\005\003\t\005\033\003\004b\002BO\005W\004\r\001\025\005\t\005o\024i\b\"\003\003z\0069\001/\0313eS:<G\003\002B~\007\017\001bA!@\004\004\tEUB\001B\000\025\r\031\tAA\001\nS6lW\017^1cY\026LAa!\002\003\000\n!A*[:u\021\035\021yN!>A\002AC\001ba\003\003~\021%1QB\001\004O\006\004X#\001)\t\021\rE!Q\020C\005\007'\t!aZ8\025\007}\032)\002C\004\004\030\r=\001\031\001)\002\013\r|WO\034;\t\021\rm!Q\020C\005\007;\tAAZ5mYR\tq\b\003\004>\005{\"\tA\020\005\b\007\nuD\021AB\022)\t\031)\003\005\004\004(\r]\"\021\023\b\005\007S\031\031D\004\003\004,\rERBAB\027\025\r\031yCB\001\007yI|w\016\036 \n\003\025I1a!\016\005\003\035\001\030mY6bO\026LAa!\002\004:)\0311Q\007\003\t\017\ru\"\006\"\001\004@\0059qM]8va\026$W\003BB!\007\017\"Baa\021\004JA1!\021\026B?\007\013\0022aHB$\t\031)71\bb\001e\"9!QTB\036\001\004\001\006bBB'U\021\0051qJ\001\bg2LG-\0338h+\021\031\tfa\026\025\r\rM3\021LB.!\031\021IK! \004VA\031qda\026\005\r\025\034YE1\001s\021\035\021ija\023A\002AC\021B!)\004LA\005\t\031\001)\t\017\r}#\006\"\001\004\016\0051A.\0328hi\"Dqaa\031+\t\003\031)'A\005ekBd\027nY1uKV\021\021Q\026\005\b\007SRC\021AB6\003\025\001\030\r^2i+\021\031iga\035\025\021\r=4QOB<\007w\002B\001\003\026\004rA\031qda\035\005\r\025\0349G1\001s\021\031Y6q\ra\001!\"A1\021PB4\001\004\031y'\001\006qCR\034\007.\0227f[NDqa! \004h\001\007\001+\001\005sKBd\027mY3e\021\035\031\tI\013C\001\007\007\0131bY8qsR{\027I\035:bsV!1QQBJ)\035)4qQBK\0073C\001b!#\004\000\001\00711R\001\003qN\004R!DBG\007#K1aa$\005\005\025\t%O]1z!\ry21\023\003\007K\016}$\031\001:\t\017\r]5q\020a\001!\006)1\017^1si\"9\0211^B@\001\004\001\006bBBOU\021\0051qT\001\rg\006lW-\0227f[\026tGo\035\013\004\r\005\006b\002;\004\034\002\00711\025\031\005\007K\033I\013\005\003\tU\r\035\006cA\020\004*\022Y11VBQ\003\003\005\tQ!\001#\005\ryF%\r\005\b\007_SC\021ABY\0035!x\016\026:bm\026\0248/\0312mKV\02111\027\t\005\021\rU\006'C\002\0048\n\0211\002\026:bm\026\0248/\0312mK\"111\030\026\005\002i\n!\002^8Ji\026\024\030\r^8s\021\035\031yL\013C\001\007\003\f\001\002^8TiJ,\027-\\\013\003\007\007\004RA!@\004FBJAaa2\003\000\n11\013\036:fC6Dqaa3+\t\003\032i-\001\005u_N#(/\0338h)\t\031y\r\005\003\004R\016mWBABj\025\021\031)na6\002\t1\fgn\032\006\003\0073\fAA[1wC&!1Q\\Bj\005\031\031FO]5oO\"I1\021\035\026\022\002\023\00511]\001\022g2LG-\0338hI\021,g-Y;mi\022\022T\003BBs\007o,\"aa:+\007A\033Io\013\002\004lB!1Q^Bz\033\t\031yO\003\003\004r\006}\023!C;oG\",7m[3e\023\021\031)pa<\003#Ut7\r[3dW\026$g+\031:jC:\034W\r\002\004f\007?\024\rA\035\005\n\007wL!\031!C\001\007{\fQ!Z7qif,\"aa@\021\007!Q3\005\003\005\005\004%\001\013\021BB\000\003\031)W\016\035;zA!9AqA\005\005\002\021%\021AB:j]\036dW-\006\003\005\f\021EA\003\002C\007\t'\001B\001\003\026\005\020A\031q\004\"\005\005\r\005\")A1\001#\021!\ty\017\"\002A\002\021=\001b\002C\f\023\021\005A\021D\001\006CB\004H._\013\005\t7!\t\003\006\003\005\036\021\r\002\003\002\005+\t?\0012a\bC\021\t\031\tCQ\003b\001E!AAQ\005C\013\001\004!9#A\003fY\026l7\017E\003\016\tS!y\"C\002\005,\021\021!\002\020:fa\026\fG/\0323?\021\035\031Y\"\003C\001\t_)B\001\"\r\005:Q!A1\007C )\021!)\004b\017\021\t!QCq\007\t\004?\021eBAB\021\005.\t\007!\005C\005\002p\0225B\0211\001\005>A!QB\036C\034\021\035\tY\017\"\fA\002ACq\001b\021\n\t\003!)%\001\005uC\n,H.\031;f+\021!9\005b\024\025\t\021%CQ\013\013\005\t\027\"\t\006\005\003\tU\0215\003cA\020\005P\0211\021\005\"\021C\002\tBqa\032C!\001\004!\031\006E\003\016SB#i\005C\004\005X\021\005\003\031\001)\002\007\025tG\rC\004\005\\%!\t\001\"\030\002\013I\fgnZ3\025\r\021}C\021\rC2!\rA!\006\025\005\b\007/#I\0061\001Q\021\035!9\006\"\027A\002ACq\001b\027\n\t\003!9\007\006\005\005`\021%D1\016C7\021\035\0319\n\"\032A\002ACq\001b\026\005f\001\007\001\013C\004\003\"\022\025\004\031\001)\t\017\021E\024\002\"\001\005t\0059\021\016^3sCR,W\003\002C;\t{\"B\001b\036\005\006R!A\021\020CA!\021A!\006b\037\021\007}!i\bB\004\005\000\021=$\031\001\022\003\003QCqa\032C8\001\004!\031\t\005\004\016S\022mD1\020\005\t\007/#y\0071\001\005|!11,\003C\001\t\023#B\001b\030\005\f\"91q\023CD\001\004\001\006BB.\n\t\003!y\t\006\004\005`\021EE1\023\005\b\007/#i\t1\001Q\021\035\021\t\013\"$A\002ACq\001b&\n\t\003!I*A\006d_:$\030N\\;bY2LX\003\002CN\tC#B\001\"(\005$B!\001B\013CP!\ryB\021\025\003\007C\021U%\031\001\022\t\023\005=HQ\023CA\002\021\025\006\003B\007w\t?\003")
/*      */ public interface Iterator<A> extends TraversableOnce<A> {
/*      */   Iterator<A> seq();
/*      */   
/*      */   boolean hasNext();
/*      */   
/*      */   A next();
/*      */   
/*      */   boolean isEmpty();
/*      */   
/*      */   boolean isTraversableAgain();
/*      */   
/*      */   boolean hasDefiniteSize();
/*      */   
/*      */   Iterator<A> take(int paramInt);
/*      */   
/*      */   Iterator<A> drop(int paramInt);
/*      */   
/*      */   Iterator<A> slice(int paramInt1, int paramInt2);
/*      */   
/*      */   <B> Iterator<B> map(Function1<A, B> paramFunction1);
/*      */   
/*      */   <B> Iterator<B> $plus$plus(Function0<GenTraversableOnce<B>> paramFunction0);
/*      */   
/*      */   <B> Iterator<B> flatMap(Function1<A, GenTraversableOnce<B>> paramFunction1);
/*      */   
/*      */   Iterator<A> filter(Function1<A, Object> paramFunction1);
/*      */   
/*      */   <B> boolean corresponds(GenTraversableOnce<B> paramGenTraversableOnce, Function2<A, B, Object> paramFunction2);
/*      */   
/*      */   Iterator<A> withFilter(Function1<A, Object> paramFunction1);
/*      */   
/*      */   Iterator<A> filterNot(Function1<A, Object> paramFunction1);
/*      */   
/*      */   <B> Iterator<B> collect(PartialFunction<A, B> paramPartialFunction);
/*      */   
/*      */   <B> Iterator<B> scanLeft(B paramB, Function2<B, A, B> paramFunction2);
/*      */   
/*      */   <B> Iterator<B> scanRight(B paramB, Function2<A, B, B> paramFunction2);
/*      */   
/*      */   Iterator<A> takeWhile(Function1<A, Object> paramFunction1);
/*      */   
/*      */   Tuple2<Iterator<A>, Iterator<A>> partition(Function1<A, Object> paramFunction1);
/*      */   
/*      */   Tuple2<Iterator<A>, Iterator<A>> span(Function1<A, Object> paramFunction1);
/*      */   
/*      */   Iterator<A> dropWhile(Function1<A, Object> paramFunction1);
/*      */   
/*      */   <B> Iterator<Tuple2<A, B>> zip(Iterator<B> paramIterator);
/*      */   
/*      */   <A1> Iterator<A1> padTo(int paramInt, A1 paramA1);
/*      */   
/*      */   Iterator<Tuple2<A, Object>> zipWithIndex();
/*      */   
/*      */   <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator<B> paramIterator, A1 paramA1, B1 paramB1);
/*      */   
/*      */   <U> void foreach(Function1<A, U> paramFunction1);
/*      */   
/*      */   boolean forall(Function1<A, Object> paramFunction1);
/*      */   
/*      */   boolean exists(Function1<A, Object> paramFunction1);
/*      */   
/*      */   boolean contains(Object paramObject);
/*      */   
/*      */   Option<A> find(Function1<A, Object> paramFunction1);
/*      */   
/*      */   int indexWhere(Function1<A, Object> paramFunction1);
/*      */   
/*      */   <B> int indexOf(B paramB);
/*      */   
/*      */   BufferedIterator<A> buffered();
/*      */   
/*      */   <B> GroupedIterator<B> grouped(int paramInt);
/*      */   
/*      */   <B> GroupedIterator<B> sliding(int paramInt1, int paramInt2);
/*      */   
/*      */   <B> int sliding$default$2();
/*      */   
/*      */   int length();
/*      */   
/*      */   Tuple2<Iterator<A>, Iterator<A>> duplicate();
/*      */   
/*      */   <B> Iterator<B> patch(int paramInt1, Iterator<B> paramIterator, int paramInt2);
/*      */   
/*      */   <B> void copyToArray(Object paramObject, int paramInt1, int paramInt2);
/*      */   
/*      */   boolean sameElements(Iterator<?> paramIterator);
/*      */   
/*      */   Traversable<A> toTraversable();
/*      */   
/*      */   Iterator<A> toIterator();
/*      */   
/*      */   Stream<A> toStream();
/*      */   
/*      */   String toString();
/*      */   
/*      */   public static class Iterator$$anon$25 extends TraversableOnce.BufferedCanBuildFrom<A, Iterator> {
/*      */     public <B> Iterator<B> bufferToColl(ArrayBuffer coll) {
/*   32 */       return coll.iterator();
/*      */     }
/*      */     
/*      */     public <B> Iterator<B> traversableToColl(GenTraversable<B> t) {
/*   33 */       return t.toIterator();
/*      */     }
/*      */   }
/*      */   
/*      */   public static class $anon$2 extends AbstractIterator<Nothing$> {
/*      */     public boolean hasNext() {
/*   38 */       return false;
/*      */     }
/*      */     
/*      */     public Nothing$ next() {
/*   39 */       throw new NoSuchElementException("next on empty iterator");
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Iterator$$anon$3 extends AbstractIterator<A> {
/*      */     private boolean hasnext = true;
/*      */     
/*      */     private final Object elem$1;
/*      */     
/*      */     private boolean hasnext() {
/*   50 */       return this.hasnext;
/*      */     }
/*      */     
/*      */     private void hasnext_$eq(boolean x$1) {
/*   50 */       this.hasnext = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*   51 */       return hasnext();
/*      */     }
/*      */     
/*      */     public A next() {
/*   53 */       hasnext_$eq(false);
/*   53 */       return hasnext() ? (A)this.elem$1 : 
/*   54 */         (A)Iterator$.MODULE$.empty().next();
/*      */     }
/*      */     
/*      */     public Iterator$$anon$3(Object elem$1) {}
/*      */   }
/*      */   
/*      */   public static class Iterator$$anon$4 extends AbstractIterator<A> {
/*   72 */     private int i = 0;
/*      */     
/*      */     private final int len$1;
/*      */     
/*      */     private final Function0 elem$2;
/*      */     
/*      */     private int i() {
/*   72 */       return this.i;
/*      */     }
/*      */     
/*      */     private void i_$eq(int x$1) {
/*   72 */       this.i = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*   73 */       return (i() < this.len$1);
/*      */     }
/*      */     
/*      */     public A next() {
/*   75 */       i_$eq(i() + 1);
/*   75 */       return hasNext() ? (A)this.elem$2.apply() : 
/*   76 */         (A)Iterator$.MODULE$.empty().next();
/*      */     }
/*      */     
/*      */     public Iterator$$anon$4(int len$1, Function0 elem$2) {}
/*      */   }
/*      */   
/*      */   public static class Iterator$$anon$5 extends AbstractIterator<A> {
/*   86 */     private int i = 0;
/*      */     
/*      */     private final int end$1;
/*      */     
/*      */     private final Function1 f$1;
/*      */     
/*      */     private int i() {
/*   86 */       return this.i;
/*      */     }
/*      */     
/*      */     private void i_$eq(int x$1) {
/*   86 */       this.i = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*   87 */       return (i() < this.end$1);
/*      */     }
/*      */     
/*      */     public A next() {
/*   89 */       Object result = this.f$1.apply(BoxesRunTime.boxToInteger(i()));
/*   89 */       i_$eq(i() + 1);
/*   89 */       return hasNext() ? (A)result : 
/*   90 */         (A)Iterator$.MODULE$.empty().next();
/*      */     }
/*      */     
/*      */     public Iterator$$anon$5(int end$1, Function1 f$1) {}
/*      */   }
/*      */   
/*      */   public static class Iterator$$anon$6 extends AbstractIterator<Object> {
/*      */     private int i;
/*      */     
/*      */     private final int end$2;
/*      */     
/*      */     private final int step$1;
/*      */     
/*      */     public Iterator$$anon$6(int start$1, int end$2, int step$1) {
/*  109 */       if (step$1 == 0)
/*  109 */         throw new IllegalArgumentException("zero step"); 
/*  110 */       this.i = start$1;
/*      */     }
/*      */     
/*      */     private int i() {
/*  110 */       return this.i;
/*      */     }
/*      */     
/*      */     private void i_$eq(int x$1) {
/*  110 */       this.i = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  111 */       return ((this.step$1 <= 0 || i() < this.end$2) && (this.step$1 >= 0 || i() > this.end$2));
/*      */     }
/*      */     
/*      */     public int next() {
/*  113 */       int result = i();
/*  113 */       i_$eq(i() + this.step$1);
/*  113 */       return hasNext() ? result : 
/*  114 */         BoxesRunTime.unboxToInt(Iterator$.MODULE$.empty().next());
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Iterator$$anon$7 extends AbstractIterator<T> {
/*      */     private boolean first;
/*      */     
/*      */     private T acc;
/*      */     
/*      */     private final Function1 f$2;
/*      */     
/*      */     public Iterator$$anon$7(Object start$2, Function1 f$2) {
/*  124 */       this.first = true;
/*  125 */       this.acc = (T)start$2;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  126 */       return true;
/*      */     }
/*      */     
/*      */     public T next() {
/*  128 */       if (this.first) {
/*  128 */         this.first = false;
/*      */       } else {
/*  129 */         this.acc = (T)this.f$2.apply(this.acc);
/*      */       } 
/*  131 */       return this.acc;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Iterator$$anon$8 extends AbstractIterator<Object> {
/*      */     private int i;
/*      */     
/*      */     private final int step$2;
/*      */     
/*      */     public Iterator$$anon$8(int start$3, int step$2) {
/*  149 */       this.i = start$3;
/*      */     }
/*      */     
/*      */     private int i() {
/*  149 */       return this.i;
/*      */     }
/*      */     
/*      */     private void i_$eq(int x$1) {
/*  149 */       this.i = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  150 */       return true;
/*      */     }
/*      */     
/*      */     public int next() {
/*  151 */       int result = i();
/*  151 */       i_$eq(i() + this.step$2);
/*  151 */       return result;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Iterator$$anon$9 extends AbstractIterator<A> {
/*      */     private final Function0 elem$3;
/*      */     
/*      */     public Iterator$$anon$9(Function0 elem$3) {}
/*      */     
/*      */     public boolean hasNext() {
/*  161 */       return true;
/*      */     }
/*      */     
/*      */     public A next() {
/*  162 */       return (A)this.elem$3.apply();
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$10 extends AbstractIterator<A> {
/*      */     private int remaining;
/*      */     
/*      */     public Iterator$$anon$10(Iterator $outer, int lo$1, int until$1) {
/*  307 */       this.remaining = until$1 - lo$1;
/*      */     }
/*      */     
/*      */     private int remaining() {
/*  307 */       return this.remaining;
/*      */     }
/*      */     
/*      */     private void remaining_$eq(int x$1) {
/*  307 */       this.remaining = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  308 */       return (remaining() > 0 && this.$outer.hasNext());
/*      */     }
/*      */     
/*      */     public A next() {
/*  311 */       remaining_$eq(remaining() - 1);
/*  312 */       return (remaining() > 0) ? this.$outer.next() : 
/*      */         
/*  314 */         (A)Iterator$.MODULE$.empty().next();
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$11 extends AbstractIterator<B> {
/*      */     private final Function1 f$3;
/*      */     
/*      */     public Iterator$$anon$11(Iterator $outer, Function1 f$3) {}
/*      */     
/*      */     public boolean hasNext() {
/*  327 */       return this.$outer.hasNext();
/*      */     }
/*      */     
/*      */     public B next() {
/*  328 */       return (B)this.f$3.apply(this.$outer.next());
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$12 extends AbstractIterator<B> {
/*      */     private Iterator<B> cur;
/*      */     
/*      */     private boolean selfExhausted;
/*      */     
/*      */     private Iterator<B> it;
/*      */     
/*      */     private final Function0 that$1;
/*      */     
/*      */     private volatile boolean bitmap$0;
/*      */     
/*      */     public Iterator$$anon$12(Iterator<B> $outer, Function0 that$1) {
/*  343 */       this.cur = $outer;
/*  344 */       this.selfExhausted = false;
/*      */     }
/*      */     
/*      */     private Iterator<B> cur() {
/*      */       return this.cur;
/*      */     }
/*      */     
/*      */     private void cur_$eq(Iterator<B> x$1) {
/*      */       this.cur = x$1;
/*      */     }
/*      */     
/*      */     private boolean selfExhausted() {
/*  344 */       return this.selfExhausted;
/*      */     }
/*      */     
/*      */     private void selfExhausted_$eq(boolean x$1) {
/*  344 */       this.selfExhausted = x$1;
/*      */     }
/*      */     
/*      */     private Iterator it$lzycompute() {
/*  348 */       synchronized (this) {
/*  348 */         if (!this.bitmap$0) {
/*  348 */           this.it = ((GenTraversableOnce<B>)this.that$1.apply()).toIterator();
/*  348 */           this.bitmap$0 = true;
/*      */         } 
/*      */         /* monitor exit ThisExpression{ObjectType{scala/collection/Iterator$$anon$12}} */
/*  348 */         this.that$1 = null;
/*  348 */         return this.it;
/*      */       } 
/*      */     }
/*      */     
/*      */     private Iterator<B> it() {
/*  348 */       return this.bitmap$0 ? this.it : it$lzycompute();
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  350 */       if (!cur().hasNext()) {
/*  350 */         if (!selfExhausted() && 
/*  351 */           it().hasNext()) {
/*  352 */           cur_$eq(it());
/*  353 */           selfExhausted_$eq(true);
/*      */           if (true);
/*      */         } 
/*      */         return false;
/*      */       } 
/*      */     }
/*      */     
/*      */     public B next() {
/*  357 */       hasNext();
/*  357 */       return cur().next();
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$13 extends AbstractIterator<B> {
/*  369 */     private Iterator<B> cur = (Iterator)Iterator$.MODULE$.empty();
/*      */     
/*      */     private final Function1 f$4;
/*      */     
/*      */     private Iterator<B> cur() {
/*  369 */       return this.cur;
/*      */     }
/*      */     
/*      */     private void cur_$eq(Iterator<B> x$1) {
/*  369 */       this.cur = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*      */       while (true) {
/*  371 */         if (this.$outer.hasNext()) {
/*  371 */           cur_$eq(((GenTraversableOnce<B>)this.f$4.apply(this.$outer.next())).toIterator());
/*      */           continue;
/*      */         } 
/*  371 */         return cur().hasNext();
/*      */       } 
/*      */     }
/*      */     
/*      */     public B next() {
/*  372 */       return (hasNext() ? (Object<B>)cur() : (Object)Iterator$.MODULE$.empty()).next();
/*      */     }
/*      */     
/*      */     public Iterator$$anon$13(Iterator $outer, Function1 f$4) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$14 extends AbstractIterator<A> {
/*      */     private A hd;
/*      */     
/*      */     private A hd() {
/*  383 */       return this.hd;
/*      */     }
/*      */     
/*      */     private void hd_$eq(Object x$1) {
/*  383 */       this.hd = (A)x$1;
/*      */     }
/*      */     
/*      */     private boolean hdDefined = false;
/*      */     
/*      */     private final Function1 p$1;
/*      */     
/*      */     private boolean hdDefined() {
/*  384 */       return this.hdDefined;
/*      */     }
/*      */     
/*      */     private void hdDefined_$eq(boolean x$1) {
/*  384 */       this.hdDefined = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  386 */       if (!hdDefined()) {
/*  388 */         while (this.$outer.hasNext()) {
/*  389 */           hd_$eq(this.$outer.next());
/*  390 */           if (BoxesRunTime.unboxToBoolean(this.p$1.apply(hd()))) {
/*  391 */             hdDefined_$eq(true);
/*      */             if (true)
/*      */               continue; 
/*      */           } 
/*      */         } 
/*      */       } else {
/*      */       
/*      */       } 
/*      */       return false;
/*      */     }
/*      */     
/*      */     public A next() {
/*  395 */       hdDefined_$eq(false);
/*  395 */       return hasNext() ? hd() : (A)Iterator$.MODULE$.empty().next();
/*      */     }
/*      */     
/*      */     public Iterator$$anon$14(Iterator $outer, Function1 p$1) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anonfun$filterNot$1 extends AbstractFunction1<A, Object> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function1 p$2;
/*      */     
/*      */     public final boolean apply(Object x$1) {
/*  436 */       return !BoxesRunTime.unboxToBoolean(this.p$2.apply(x$1));
/*      */     }
/*      */     
/*      */     public Iterator$$anonfun$filterNot$1(Iterator $outer, Function1 p$2) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$15 extends AbstractIterator<B> {
/*      */     private final BufferedIterator self$1;
/*      */     
/*      */     private final PartialFunction pf$1;
/*      */     
/*      */     public Iterator$$anon$15(Iterator $outer, BufferedIterator self$1, PartialFunction pf$1) {}
/*      */     
/*      */     private void skip() {
/*  451 */       for (; this.self$1.hasNext() && !this.pf$1.isDefinedAt(this.self$1.head()); this.self$1.next());
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  452 */       skip();
/*  452 */       return this.self$1.hasNext();
/*      */     }
/*      */     
/*      */     public B next() {
/*  453 */       skip();
/*  453 */       return (B)this.pf$1.apply(this.self$1.next());
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$16 extends AbstractIterator<B> {
/*      */     private boolean hasNext;
/*      */     
/*      */     private B elem;
/*      */     
/*      */     private final Function2 op$1;
/*      */     
/*      */     public Iterator$$anon$16(Iterator $outer, Object z$1, Function2 op$1) {
/*  470 */       this.hasNext = true;
/*  471 */       this.elem = (B)z$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*      */       return this.hasNext;
/*      */     }
/*      */     
/*      */     private void hasNext_$eq(boolean x$1) {
/*      */       this.hasNext = x$1;
/*      */     }
/*      */     
/*      */     private B elem() {
/*  471 */       return this.elem;
/*      */     }
/*      */     
/*      */     private void elem_$eq(Object x$1) {
/*  471 */       this.elem = (B)x$1;
/*      */     }
/*      */     
/*      */     public B next() {
/*  473 */       Object res = elem();
/*  474 */       if (this.$outer.hasNext()) {
/*  474 */         elem_$eq((B)this.op$1.apply(elem(), this.$outer.next()));
/*      */       } else {
/*  475 */         hasNext_$eq(false);
/*      */       } 
/*  476 */       return hasNext() ? (B)res : 
/*  477 */         (B)Iterator$.MODULE$.empty().next();
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$17 extends AbstractIterator<A> {
/*      */     private A hd;
/*      */     
/*      */     private boolean hdDefined;
/*      */     
/*      */     private Iterator<A> tail;
/*      */     
/*      */     private final Function1 p$3;
/*      */     
/*      */     public Iterator$$anon$17(Iterator<A> $outer, Function1 p$3) {
/*  507 */       this.hdDefined = false;
/*  508 */       this.tail = $outer;
/*      */     }
/*      */     
/*      */     private A hd() {
/*      */       return this.hd;
/*      */     }
/*      */     
/*      */     private void hd_$eq(Object x$1) {
/*      */       this.hd = (A)x$1;
/*      */     }
/*      */     
/*      */     private boolean hdDefined() {
/*      */       return this.hdDefined;
/*      */     }
/*      */     
/*      */     private void hdDefined_$eq(boolean x$1) {
/*      */       this.hdDefined = x$1;
/*      */     }
/*      */     
/*      */     private Iterator<A> tail() {
/*  508 */       return this.tail;
/*      */     }
/*      */     
/*      */     private void tail_$eq(Iterator<A> x$1) {
/*  508 */       this.tail = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  510 */       if (!hdDefined()) {
/*  510 */         if (tail().hasNext()) {
/*  511 */           hd_$eq(tail().next());
/*  512 */           if (BoxesRunTime.unboxToBoolean(this.p$3.apply(hd()))) {
/*  512 */             hdDefined_$eq(true);
/*      */           } else {
/*  513 */             tail_$eq((Iterator)Iterator$.MODULE$.empty());
/*      */           } 
/*  514 */           if (hdDefined());
/*      */         } 
/*      */         return false;
/*      */       } 
/*      */     }
/*      */     
/*      */     public A next() {
/*  516 */       hdDefined_$eq(false);
/*  516 */       return hasNext() ? hd() : (A)Iterator$.MODULE$.empty().next();
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$PartitionIterator$1 extends AbstractIterator<A> {
/*      */     private final Function1<A, Object> p;
/*      */     
/*      */     private Iterator$PartitionIterator$1 other;
/*      */     
/*      */     public Iterator$PartitionIterator$1 other() {
/*  531 */       return this.other;
/*      */     }
/*      */     
/*      */     public void other_$eq(Iterator$PartitionIterator$1 x$1) {
/*  531 */       this.other = x$1;
/*      */     }
/*      */     
/*  532 */     private final Queue<A> lookahead = new Queue();
/*      */     
/*      */     private final BufferedIterator self$2;
/*      */     
/*      */     public Queue<A> lookahead() {
/*  532 */       return this.lookahead;
/*      */     }
/*      */     
/*      */     public void skip() {
/*  534 */       while (this.self$2.hasNext() && !BoxesRunTime.unboxToBoolean(this.p.apply(this.self$2.head())))
/*  535 */         other().lookahead().$plus$eq(this.self$2.next()); 
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  537 */       if (lookahead().isEmpty()) {
/*  537 */         skip();
/*  537 */         if (!this.self$2.hasNext());
/*      */       } 
/*  537 */       return true;
/*      */     }
/*      */     
/*      */     public A next() {
/*  539 */       skip();
/*  539 */       return lookahead().isEmpty() ? this.self$2.next() : (A)lookahead().dequeue();
/*      */     }
/*      */     
/*      */     public Iterator$PartitionIterator$1(Iterator $outer, Function1<A, Object> p, BufferedIterator self$2) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anonfun$1 extends AbstractFunction1<A, Object> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function1 p$4;
/*      */     
/*      */     public final boolean apply(Object x$2) {
/*  542 */       return !BoxesRunTime.unboxToBoolean(this.p$4.apply(x$2));
/*      */     }
/*      */     
/*      */     public Iterator$$anonfun$1(Iterator $outer, Function1 p$4) {}
/*      */   }
/*      */   
/*      */   public class Iterator$Leading$1 extends AbstractIterator<A> {
/*      */     private boolean isDone = false;
/*      */     
/*      */     private boolean isDone() {
/*  565 */       return this.isDone;
/*      */     }
/*      */     
/*      */     private void isDone_$eq(boolean x$1) {
/*  565 */       this.isDone = x$1;
/*      */     }
/*      */     
/*  566 */     private final Queue<A> lookahead = new Queue();
/*      */     
/*      */     private final BufferedIterator self$3;
/*      */     
/*      */     private final Function1 p$5;
/*      */     
/*      */     public Queue<A> lookahead() {
/*  566 */       return this.lookahead;
/*      */     }
/*      */     
/*      */     public boolean advance() {
/*  568 */       if (this.self$3.hasNext() && BoxesRunTime.unboxToBoolean(this.p$5.apply(this.self$3.head()))) {
/*  569 */         lookahead().$plus$eq(this.self$3.next());
/*      */         if (true);
/*      */       } 
/*      */       return false;
/*      */     }
/*      */     
/*      */     public void finish() {
/*      */       do {
/*      */       
/*  574 */       } while (advance());
/*  575 */       isDone_$eq(true);
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  577 */       return (lookahead().nonEmpty() || advance());
/*      */     }
/*      */     
/*      */     public A next() {
/*  579 */       if (lookahead().isEmpty())
/*  580 */         advance(); 
/*  582 */       return (A)lookahead().dequeue();
/*      */     }
/*      */     
/*      */     public Iterator$Leading$1(Iterator $outer, BufferedIterator self$3, Function1 p$5) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$18 extends AbstractIterator<A> {
/*      */     private BufferedIterator<A> it;
/*      */     
/*      */     private final BufferedIterator self$3;
/*      */     
/*      */     private final Iterator$Leading$1 leading$1;
/*      */     
/*      */     private volatile boolean bitmap$0;
/*      */     
/*      */     public Iterator$$anon$18(Iterator $outer, BufferedIterator self$3, Iterator$Leading$1 leading$1) {}
/*      */     
/*      */     private BufferedIterator it$lzycompute() {
/*  587 */       synchronized (this) {
/*  587 */         if (!this.bitmap$0) {
/*  588 */           this.leading$1.finish();
/*  589 */           this.it = this.self$3;
/*      */           this.bitmap$0 = true;
/*      */         } 
/*      */         /* monitor exit ThisExpression{ObjectType{scala/collection/Iterator$$anon$18}} */
/*      */         this.self$3 = null;
/*      */         this.leading$1 = null;
/*      */         return this.it;
/*      */       } 
/*      */     }
/*      */     
/*      */     private BufferedIterator<A> it() {
/*      */       return this.bitmap$0 ? this.it : it$lzycompute();
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  591 */       return it().hasNext();
/*      */     }
/*      */     
/*      */     public A next() {
/*  592 */       return it().next();
/*      */     }
/*      */     
/*      */     public String toString() {
/*  593 */       return "unknown-if-empty iterator";
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$19 extends AbstractIterator<A> {
/*      */     private boolean dropped = false;
/*      */     
/*      */     private final BufferedIterator self$4;
/*      */     
/*      */     private final Function1 p$6;
/*      */     
/*      */     private boolean dropped() {
/*  609 */       return this.dropped;
/*      */     }
/*      */     
/*      */     private void dropped_$eq(boolean x$1) {
/*  609 */       this.dropped = x$1;
/*      */     }
/*      */     
/*      */     private void skip() {
/*  611 */       if (!dropped()) {
/*  612 */         for (; this.self$4.hasNext() && BoxesRunTime.unboxToBoolean(this.p$6.apply(this.self$4.head())); this.self$4.next());
/*  613 */         dropped_$eq(true);
/*      */       } 
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  615 */       skip();
/*  615 */       return this.self$4.hasNext();
/*      */     }
/*      */     
/*      */     public A next() {
/*  616 */       skip();
/*  616 */       return this.self$4.next();
/*      */     }
/*      */     
/*      */     public Iterator$$anon$19(Iterator $outer, BufferedIterator self$4, Function1 p$6) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$20 extends AbstractIterator<Tuple2<A, B>> {
/*      */     private final Iterator that$2;
/*      */     
/*      */     public Iterator$$anon$20(Iterator $outer, Iterator that$2) {}
/*      */     
/*      */     public boolean hasNext() {
/*  634 */       return (this.$outer.hasNext() && this.that$2.hasNext());
/*      */     }
/*      */     
/*      */     public Tuple2<A, B> next() {
/*  635 */       return new Tuple2(this.$outer.next(), this.that$2.next());
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$21 extends AbstractIterator<A1> {
/*  651 */     private int count = 0;
/*      */     
/*      */     private final int len$3;
/*      */     
/*      */     private final Object elem$4;
/*      */     
/*      */     private int count() {
/*  651 */       return this.count;
/*      */     }
/*      */     
/*      */     private void count_$eq(int x$1) {
/*  651 */       this.count = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  652 */       return (this.$outer.hasNext() || count() < this.len$3);
/*      */     }
/*      */     
/*      */     public A1 next() {
/*  654 */       count_$eq(count() + 1);
/*  655 */       return this.$outer.hasNext() ? this.$outer.next() : (
/*  656 */         (count() <= this.len$3) ? (A1)this.elem$4 : 
/*  657 */         (A1)Iterator$.MODULE$.empty().next());
/*      */     }
/*      */     
/*      */     public Iterator$$anon$21(Iterator $outer, int len$3, Object elem$4) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$22 extends AbstractIterator<Tuple2<A, Object>> {
/*  669 */     private int idx = 0;
/*      */     
/*      */     private int idx() {
/*  669 */       return this.idx;
/*      */     }
/*      */     
/*      */     private void idx_$eq(int x$1) {
/*  669 */       this.idx = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  670 */       return this.$outer.hasNext();
/*      */     }
/*      */     
/*      */     public Tuple2<A, Object> next() {
/*  672 */       Tuple2<A, Object> ret = new Tuple2(this.$outer.next(), BoxesRunTime.boxToInteger(idx()));
/*  673 */       idx_$eq(idx() + 1);
/*  674 */       return ret;
/*      */     }
/*      */     
/*      */     public Iterator$$anon$22(Iterator $outer) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$23 extends AbstractIterator<Tuple2<A1, B1>> {
/*      */     private final Iterator that$3;
/*      */     
/*      */     private final Object thisElem$1;
/*      */     
/*      */     private final Object thatElem$1;
/*      */     
/*      */     public Iterator$$anon$23(Iterator $outer, Iterator that$3, Object thisElem$1, Object thatElem$1) {}
/*      */     
/*      */     public boolean hasNext() {
/*  702 */       return (this.$outer.hasNext() || this.that$3.hasNext());
/*      */     }
/*      */     
/*      */     public Tuple2<A1, B1> next() {
/*  704 */       return this.$outer.hasNext() ? (
/*  705 */         this.that$3.hasNext() ? new Tuple2(this.$outer.next(), this.that$3.next()) : 
/*  706 */         new Tuple2(this.$outer.next(), this.thatElem$1)) : (
/*      */         
/*  708 */         this.that$3.hasNext() ? new Tuple2(this.thisElem$1, this.that$3.next()) : 
/*  709 */         (Tuple2<A1, B1>)Iterator$.MODULE$.empty().next());
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anonfun$contains$1 extends AbstractFunction1<A, Object> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Object elem$5;
/*      */     
/*      */     public final boolean apply(Object x$3) {
/*  765 */       Object object = this.elem$5;
/*  765 */       return ((x$3 == object) ? true : ((x$3 == null) ? false : ((x$3 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)x$3, object) : ((x$3 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)x$3, object) : x$3.equals(object)))));
/*      */     }
/*      */     
/*      */     public Iterator$$anonfun$contains$1(Iterator $outer, Object elem$5) {}
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$1 extends AbstractIterator<A> implements BufferedIterator<A> {
/*      */     private A hd;
/*      */     
/*      */     private boolean hdDefined;
/*      */     
/*      */     public BufferedIterator<A> buffered() {
/*  834 */       return BufferedIterator$class.buffered(this);
/*      */     }
/*      */     
/*      */     public Iterator$$anon$1(Iterator $outer) {
/*  834 */       BufferedIterator$class.$init$(this);
/*  836 */       this.hdDefined = false;
/*      */     }
/*      */     
/*      */     private A hd() {
/*      */       return this.hd;
/*      */     }
/*      */     
/*      */     private void hd_$eq(Object x$1) {
/*      */       this.hd = (A)x$1;
/*      */     }
/*      */     
/*      */     private boolean hdDefined() {
/*  836 */       return this.hdDefined;
/*      */     }
/*      */     
/*      */     private void hdDefined_$eq(boolean x$1) {
/*  836 */       this.hdDefined = x$1;
/*      */     }
/*      */     
/*      */     public A head() {
/*  839 */       if (!hdDefined()) {
/*  840 */         hd_$eq(next());
/*  841 */         hdDefined_$eq(true);
/*      */       } 
/*  843 */       return hd();
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  847 */       return (hdDefined() || this.$outer.hasNext());
/*      */     }
/*      */     
/*      */     public A next() {
/*  851 */       hdDefined_$eq(false);
/*  852 */       return hdDefined() ? hd() : 
/*  853 */         this.$outer.next();
/*      */     }
/*      */   }
/*      */   
/*      */   public class GroupedIterator<B> extends AbstractIterator<Seq<B>> implements Iterator<Seq<B>> {
/*      */     private final Iterator<A> self;
/*      */     
/*      */     public final int scala$collection$Iterator$GroupedIterator$$size;
/*      */     
/*      */     public final int scala$collection$Iterator$GroupedIterator$$step;
/*      */     
/*      */     private ArrayBuffer<B> buffer;
/*      */     
/*      */     private boolean filled;
/*      */     
/*      */     private boolean _partial;
/*      */     
/*      */     public Option<Function0<B>> scala$collection$Iterator$GroupedIterator$$pad;
/*      */     
/*      */     public GroupedIterator(Iterator $outer, Iterator<A> self, int size, int step) {
/*  866 */       boolean bool = (size >= 1 && step >= 1) ? true : false;
/*  866 */       Predef$ predef$1 = Predef$.MODULE$;
/*  866 */       if (bool) {
/*  868 */         this.buffer = (ArrayBuffer<B>)ArrayBuffer$.MODULE$.apply((Seq)Nil$.MODULE$);
/*  869 */         this.filled = false;
/*  870 */         this._partial = true;
/*  871 */         this.scala$collection$Iterator$GroupedIterator$$pad = (Option<Function0<B>>)None$.MODULE$;
/*      */         return;
/*      */       } 
/*      */       Predef$ predef$2 = Predef$.MODULE$;
/*      */       throw new IllegalArgumentException((new StringBuilder()).append("requirement failed: ").append(StringLike.class.format(new StringOps("size=%d and step=%d, but both must be positive"), Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(this.scala$collection$Iterator$GroupedIterator$$size), BoxesRunTime.boxToInteger(this.scala$collection$Iterator$GroupedIterator$$step) }))).toString());
/*      */     }
/*      */     
/*      */     public GroupedIterator<B> withPadding(Function0 x) {
/*  884 */       this.scala$collection$Iterator$GroupedIterator$$pad = (Option<Function0<B>>)new Some(x);
/*  885 */       return this;
/*      */     }
/*      */     
/*      */     public GroupedIterator<B> withPartial(boolean x) {
/*  899 */       this._partial = x;
/*  900 */       if (this._partial == true)
/*  901 */         this.scala$collection$Iterator$GroupedIterator$$pad = (Option<Function0<B>>)None$.MODULE$; 
/*  903 */       return this;
/*      */     }
/*      */     
/*      */     private Seq<A> takeDestructively(int size) {
/*  911 */       ArrayBuffer buf = new ArrayBuffer();
/*  912 */       int i = 0;
/*  913 */       while (this.self.hasNext() && i < size) {
/*  914 */         buf.$plus$eq(this.self.next());
/*  915 */         i++;
/*      */       } 
/*  917 */       return (Seq<A>)buf;
/*      */     }
/*      */     
/*      */     private List<B> padding(int x) {
/*  920 */       return (List<B>)List$.MODULE$.fill(x, (Function0)new Iterator$GroupedIterator$$anonfun$padding$1(this));
/*      */     }
/*      */     
/*      */     public class Iterator$GroupedIterator$$anonfun$padding$1 extends AbstractFunction0<B> implements Serializable {
/*      */       public static final long serialVersionUID = 0L;
/*      */       
/*      */       public final B apply() {
/*  920 */         return (B)((Function0)this.$outer.scala$collection$Iterator$GroupedIterator$$pad.get()).apply();
/*      */       }
/*      */       
/*      */       public Iterator$GroupedIterator$$anonfun$padding$1(Iterator.GroupedIterator $outer) {}
/*      */     }
/*      */     
/*      */     private int gap() {
/*  921 */       int i = this.scala$collection$Iterator$GroupedIterator$$step - this.scala$collection$Iterator$GroupedIterator$$size;
/*  921 */       Predef$ predef$ = Predef$.MODULE$;
/*  921 */       return RichInt$.MODULE$.max$extension(i, 0);
/*      */     }
/*      */     
/*      */     private final boolean isFirst$1(int prevSize$1) {
/*  925 */       return (prevSize$1 == 0);
/*      */     }
/*      */     
/*      */     private boolean go(int count) {
/*  937 */       IntRef len$lzy = new IntRef(0);
/*  938 */       BooleanRef incomplete$lzy = new BooleanRef(false);
/*      */       VolatileByteRef bitmap$0 = new VolatileByteRef((byte)0);
/*      */       int prevSize = this.buffer.size();
/*      */       Seq<A> res = takeDestructively(count);
/*      */       int shortBy = count - res.length();
/*      */       Seq xs = (this.scala$collection$Iterator$GroupedIterator$$pad.isDefined() && !this.self.hasNext()) ? ((shortBy > 0) ? (Seq)res.$plus$plus((GenTraversableOnce)padding(shortBy), Seq$.MODULE$.canBuildFrom()) : res) : res;
/*  958 */       int i = len$2(xs, len$lzy, bitmap$0);
/*  958 */       Predef$ predef$1 = Predef$.MODULE$;
/*  961 */       int j = this.scala$collection$Iterator$GroupedIterator$$step;
/*  961 */       Predef$ predef$2 = Predef$.MODULE$;
/*  961 */       return xs.isEmpty() ? false : (this._partial ? deliver$1(RichInt$.MODULE$.min$extension(i, this.scala$collection$Iterator$GroupedIterator$$size), prevSize, xs, len$lzy, bitmap$0) : (incomplete$1(count, xs, len$lzy, incomplete$lzy, bitmap$0) ? false : (isFirst$1(prevSize) ? deliver$1(len$2(xs, len$lzy, bitmap$0), prevSize, xs, len$lzy, bitmap$0) : deliver$1(RichInt$.MODULE$.min$extension(j, this.scala$collection$Iterator$GroupedIterator$$size), prevSize, xs, len$lzy, bitmap$0))));
/*      */     }
/*      */     
/*      */     private final int len$lzycompute$1(Seq xs$1, IntRef len$lzy$1, VolatileByteRef bitmap$0$1) {
/*      */       synchronized (this) {
/*      */         if ((byte)(bitmap$0$1.elem & 0x1) == 0) {
/*      */           len$lzy$1.elem = xs$1.length();
/*      */           bitmap$0$1.elem = (byte)(bitmap$0$1.elem | 0x1);
/*      */         } 
/*      */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/collection/Iterator}.Lscala/collection/Iterator$GroupedIterator;}} */
/*      */         return len$lzy$1.elem;
/*      */       } 
/*      */     }
/*      */     
/*      */     private final int len$2(Seq xs$1, IntRef len$lzy$1, VolatileByteRef bitmap$0$1) {
/*      */       return ((byte)(bitmap$0$1.elem & 0x1) == 0) ? len$lzycompute$1(xs$1, len$lzy$1, bitmap$0$1) : len$lzy$1.elem;
/*      */     }
/*      */     
/*      */     private final boolean incomplete$lzycompute$1(int count$1, Seq xs$1, IntRef len$lzy$1, BooleanRef incomplete$lzy$1, VolatileByteRef bitmap$0$1) {
/*      */       synchronized (this) {
/*      */         if ((byte)(bitmap$0$1.elem & 0x2) == 0) {
/*      */           incomplete$lzy$1.elem = (len$2(xs$1, len$lzy$1, bitmap$0$1) < count$1);
/*      */           bitmap$0$1.elem = (byte)(bitmap$0$1.elem | 0x2);
/*      */         } 
/*      */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/collection/Iterator}.Lscala/collection/Iterator$GroupedIterator;}} */
/*      */         return incomplete$lzy$1.elem;
/*      */       } 
/*      */     }
/*      */     
/*      */     private final boolean incomplete$1(int count$1, Seq xs$1, IntRef len$lzy$1, BooleanRef incomplete$lzy$1, VolatileByteRef bitmap$0$1) {
/*      */       return ((byte)(bitmap$0$1.elem & 0x2) == 0) ? incomplete$lzycompute$1(count$1, xs$1, len$lzy$1, incomplete$lzy$1, bitmap$0$1) : incomplete$lzy$1.elem;
/*      */     }
/*      */     
/*      */     private final boolean deliver$1(int howMany, int prevSize$1, Seq xs$1, IntRef len$lzy$1, VolatileByteRef bitmap$0$1) {
/*      */       if (howMany > 0 && (isFirst$1(prevSize$1) || len$2(xs$1, len$lzy$1, bitmap$0$1) > gap())) {
/*      */         if (!isFirst$1(prevSize$1)) {
/*      */           int i = this.scala$collection$Iterator$GroupedIterator$$step;
/*      */           Predef$ predef$1 = Predef$.MODULE$;
/*      */           this.buffer.trimStart(RichInt$.MODULE$.min$extension(i, prevSize$1));
/*      */         } 
/*      */         Predef$ predef$ = Predef$.MODULE$;
/*      */         int available = isFirst$1(prevSize$1) ? len$2(xs$1, len$lzy$1, bitmap$0$1) : RichInt$.MODULE$.min$extension(howMany, len$2(xs$1, len$lzy$1, bitmap$0$1) - gap());
/*      */         this.buffer.$plus$plus$eq(xs$1.takeRight(available));
/*      */         this.filled = true;
/*      */         if (true);
/*      */       } 
/*      */       return false;
/*      */     }
/*      */     
/*      */     private boolean fill() {
/*  966 */       return this.self.hasNext() ? (
/*      */         
/*  968 */         this.buffer.isEmpty() ? go(this.scala$collection$Iterator$GroupedIterator$$size) : 
/*  969 */         go(this.scala$collection$Iterator$GroupedIterator$$step)) : false;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  972 */       return (this.filled || fill());
/*      */     }
/*      */     
/*      */     public List<B> next() {
/*  974 */       if (!this.filled)
/*  975 */         fill(); 
/*  977 */       if (this.filled) {
/*  979 */         this.filled = false;
/*  980 */         return this.buffer.toList();
/*      */       } 
/*      */       throw new NoSuchElementException("next on empty iterator");
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$Partner$1 extends AbstractIterator<A> {
/*      */     private final Queue gap$1;
/*      */     
/*      */     private final ObjectRef ahead$1;
/*      */     
/*      */     public Iterator$Partner$1(Iterator $outer, Queue gap$1, ObjectRef ahead$1) {}
/*      */     
/*      */     public boolean hasNext() {
/* 1048 */       synchronized (scala$collection$Iterator$Partner$$$outer()) {
/* 1048 */         return 
/* 1049 */           ((this != (Iterator)this.ahead$1.elem && !this.gap$1.isEmpty()) || scala$collection$Iterator$Partner$$$outer().hasNext());
/*      */       } 
/*      */     }
/*      */     
/*      */     public A next() {
/* 1051 */       synchronized (scala$collection$Iterator$Partner$$$outer()) {
/* 1052 */         if (this.gap$1.isEmpty())
/* 1052 */           this.ahead$1.elem = this; 
/* 1054 */         Object e = scala$collection$Iterator$Partner$$$outer().next();
/* 1055 */         this.gap$1.enqueue((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { e }));
/* 1056 */         return (A)((this == (Iterator)this.ahead$1.elem) ? e : 
/* 1057 */           this.gap$1.dequeue());
/*      */       } 
/*      */     }
/*      */     
/*      */     private boolean compareGap(Queue queue) {
/* 1061 */       return (this.gap$1 == queue);
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 1062 */       return this.gap$1.hashCode();
/*      */     }
/*      */     
/*      */     public boolean equals(Object other) {
/*      */       boolean bool;
/* 1063 */       if (other instanceof Iterator$Partner$1) {
/* 1063 */         Iterator$Partner$1 iterator$Partner$1 = (Iterator$Partner$1)other;
/* 1063 */         bool = (iterator$Partner$1.compareGap(this.gap$1) && this.gap$1.isEmpty());
/*      */       } else {
/* 1065 */         bool = super.equals(other);
/*      */       } 
/*      */       return bool;
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anon$24 extends AbstractIterator<B> {
/*      */     private Iterator<A> origElems;
/*      */     
/*      */     private int i;
/*      */     
/*      */     private final int from$1;
/*      */     
/*      */     private final Iterator patchElems$1;
/*      */     
/*      */     private final int replaced$1;
/*      */     
/*      */     public Iterator$$anon$24(Iterator<A> $outer, int from$1, Iterator patchElems$1, int replaced$1) {
/* 1079 */       this.origElems = $outer;
/* 1080 */       this.i = 0;
/*      */     }
/*      */     
/*      */     private Iterator<A> origElems() {
/*      */       return this.origElems;
/*      */     }
/*      */     
/*      */     private void origElems_$eq(Iterator<A> x$1) {
/*      */       this.origElems = x$1;
/*      */     }
/*      */     
/*      */     private int i() {
/* 1080 */       return this.i;
/*      */     }
/*      */     
/*      */     private void i_$eq(int x$1) {
/* 1080 */       this.i = x$1;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/* 1082 */       return (i() < this.from$1) ? origElems().hasNext() : (
/* 1083 */         (this.patchElems$1.hasNext() || origElems().hasNext()));
/*      */     }
/*      */     
/*      */     public B next() {
/* 1086 */       if (i() == this.from$1)
/* 1086 */         origElems_$eq(origElems().drop(this.replaced$1)); 
/* 1087 */       Object result = 
/* 1088 */         (i() >= this.from$1 && this.patchElems$1.hasNext()) ? 
/* 1089 */         this.patchElems$1.next() : origElems().next();
/* 1090 */       i_$eq(i() + 1);
/* 1091 */       return (B)result;
/*      */     }
/*      */   }
/*      */   
/*      */   public class Iterator$$anonfun$toStream$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     public final Stream<A> apply() {
/* 1143 */       return this.$outer.toStream();
/*      */     }
/*      */     
/*      */     public Iterator$$anonfun$toStream$1(Iterator $outer) {}
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\Iterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */