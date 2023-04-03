/*     */ package akka.actor.dsl;
/*     */ 
/*     */ import akka.actor.Actor;
/*     */ import akka.actor.ActorContext;
/*     */ import akka.actor.ActorDSL;
/*     */ import akka.actor.ActorLogging;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.Cancellable;
/*     */ import akka.actor.Props;
/*     */ import akka.actor.ScalaActorRef;
/*     */ import akka.actor.SupervisorStrategy;
/*     */ import akka.event.LoggingAdapter;
/*     */ import akka.util.Timeout;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.TreeSet;
/*     */ import scala.collection.mutable.Queue;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.concurrent.Awaitable;
/*     */ import scala.concurrent.ExecutionContext;
/*     */ import scala.concurrent.duration.Deadline;
/*     */ import scala.concurrent.duration.Duration;
/*     */ import scala.concurrent.duration.FiniteDuration;
/*     */ import scala.math.Ordering;
/*     */ import scala.math.PartialOrdering;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.AbstractFunction3;
/*     */ import scala.runtime.AbstractPartialFunction;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021MvAB\001\003\021\0031\001\"A\003J]\n|\007P\003\002\004\t\005\031Am\0357\013\005\0251\021!B1di>\024(\"A\004\002\t\005\\7.\031\t\003\023)i\021A\001\004\007\027\tA\tA\002\007\003\013%s'm\034=\024\005)i\001C\001\b\022\033\005y!\"\001\t\002\013M\034\027\r\\1\n\005Iy!AB!osJ+g\rC\003\025\025\021\005a#\001\004=S:LGOP\002\001)\005Aaa\002\r\013!\003\rJ#\007\002\006#V,'/_\n\003/5AQaG\f\007\002q\t\001\002Z3bI2Lg.Z\013\002;A\021adI\007\002?)\021\001%I\001\tIV\024\030\r^5p]*\021!eD\001\013G>t7-\036:sK:$\030B\001\023 \005!!U-\0313mS:,\007\"\002\024\030\r\0039\023AC<ji\"\034E.[3oiR\021\001F\013\t\003S]i\021A\003\005\006W\025\002\r\001L\001\002GB\021QFL\007\002\t%\021q\006\002\002\t\003\016$xN\035*fM\")\021g\006D\001e\00511\r\\5f]R,\022\001L\025\005/Q\nIC\002\0036\025\0213$aA$fiN)A'\004\0258uA\021a\002O\005\003s=\021q\001\025:pIV\034G\017\005\002\017w%\021Ah\004\002\r'\026\024\030.\0317ju\006\024G.\032\005\t7Q\022)\032!C\0019!Aq\b\016B\tB\003%Q$A\005eK\006$G.\0338fA!A\021\007\016BK\002\023\005!\007\003\005Ci\tE\t\025!\003-\003\035\031G.[3oi\002BQ\001\006\033\005\002\021#2!\022$H!\tIC\007C\003\034\007\002\007Q\004C\0042\007B\005\t\031\001\027\t\013\031\"D\021A%\025\005\025S\005\"B\026I\001\004a\003b\002'5\003\003%\t!T\001\005G>\004\030\020F\002F\035>CqaG&\021\002\003\007Q\004C\0042\027B\005\t\031\001\027\t\017E#\024\023!C\001%\006q1m\0349zI\021,g-Y;mi\022\nT#A*+\005u!6&A+\021\005Y[V\"A,\013\005aK\026!C;oG\",7m[3e\025\tQv\"\001\006b]:|G/\031;j_:L!\001X,\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\004_iE\005I\021A0\002\035\r|\007/\037\023eK\032\fW\017\034;%eU\t\001M\013\002-)\"9!\rNA\001\n\003\032\027!\0049s_\022,8\r\036)sK\032L\0070F\001e!\t)'.D\001g\025\t9\007.\001\003mC:<'\"A5\002\t)\fg/Y\005\003W\032\024aa\025;sS:<\007bB75\003\003%\tA\\\001\raJ|G-^2u\003JLG/_\013\002_B\021a\002]\005\003c>\0211!\0238u\021\035\031H'!A\005\002Q\fa\002\035:pIV\034G/\0227f[\026tG\017\006\002vqB\021aB^\005\003o>\0211!\0218z\021\035I(/!AA\002=\f1\001\037\0232\021\035YH'!A\005Bq\fq\002\035:pIV\034G/\023;fe\006$xN]\013\002{B!a0a\001v\033\005y(bAA\001\037\005Q1m\0347mK\016$\030n\0348\n\007\005\025qP\001\005Ji\026\024\030\r^8s\021%\tI\001NA\001\n\003\tY!\001\005dC:,\025/^1m)\021\ti!a\005\021\0079\ty!C\002\002\022=\021qAQ8pY\026\fg\016\003\005z\003\017\t\t\0211\001v\021%\t9\002NA\001\n\003\nI\"\001\005iCND7i\0343f)\005y\007\"CA\017i\005\005I\021IA\020\003!!xn\025;sS:<G#\0013\t\023\005\rB'!A\005B\005\025\022AB3rk\006d7\017\006\003\002\016\005\035\002\002C=\002\"\005\005\t\031A;\007\r\005-\"\002RA\027\005\031\031V\r\\3diN1\021\021F\007)oiB\021bGA\025\005+\007I\021\001\017\t\023}\nIC!E!\002\023i\002bCA\033\003S\021)\032!C\001\003o\t\021\002\035:fI&\034\027\r^3\026\005\005e\002#\002\b\002<U,\030bAA\037\037\ty\001+\031:uS\006dg)\0368di&|g\016C\006\002B\005%\"\021#Q\001\n\005e\022A\0039sK\022L7-\031;fA!I\021'!\013\003\026\004%\tA\r\005\n\005\006%\"\021#Q\001\n1Bq\001FA\025\t\003\tI\005\006\005\002L\0055\023qJA)!\rI\023\021\006\005\0077\005\035\003\031A\017\t\021\005U\022q\ta\001\003sA\001\"MA$!\003\005\r\001\f\005\bM\005%B\021AA+)\021\tY%a\026\t\r-\n\031\0061\001-\021%a\025\021FA\001\n\003\tY\006\006\005\002L\005u\023qLA1\021!Y\022\021\fI\001\002\004i\002BCA\033\0033\002\n\0211\001\002:!A\021'!\027\021\002\003\007A\006\003\005R\003S\t\n\021\"\001S\021%q\026\021FI\001\n\003\t9'\006\002\002j)\032\021\021\b+\t\023\0055\024\021FI\001\n\003y\026AD2paf$C-\0324bk2$He\r\005\tE\006%\022\021!C!G\"AQ.!\013\002\002\023\005a\016C\005t\003S\t\t\021\"\001\002vQ\031Q/a\036\t\021e\f\031(!AA\002=D\001b_A\025\003\003%\t\005 \005\013\003\023\tI#!A\005\002\005uD\003BA\007\003B\001\"_A>\003\003\005\r!\036\005\013\003/\tI#!A\005B\005e\001BCA\017\003S\t\t\021\"\021\002 !Q\0211EA\025\003\003%\t%a\"\025\t\0055\021\021\022\005\ts\006\025\025\021!a\001k\036I\021Q\022\006\002\002#%\021qR\001\004\017\026$\bcA\025\002\022\032AQGCA\001\022\023\t\031jE\003\002\022\006U%\bE\004\002\030\006uU\004L#\016\005\005e%bAAN\037\0059!/\0368uS6,\027\002BAP\0033\023\021#\0212tiJ\f7\r\036$v]\016$\030n\03483\021\035!\022\021\023C\001\003G#\"!a$\t\025\005u\021\021SA\001\n\013\ny\002\003\006\002*\006E\025\021!CA\003W\013Q!\0319qYf$R!RAW\003_CaaGAT\001\004i\002\002C\031\002(B\005\t\031\001\027\t\025\005M\026\021SA\001\n\003\013),A\004v]\006\004\b\017\\=\025\t\005]\0261\031\t\006\035\005e\026QX\005\004\003w{!AB(qi&|g\016E\003\017\003kB&C\002\002B>\021a\001V;qY\026\024\004\"CAc\003c\013\t\0211\001F\003\rAH\005\r\005\n\003\023\f\t*%A\005\002}\0131\004\n7fgNLg.\033;%OJ,\027\r^3sI\021,g-Y;mi\022\022\004\"CAg\003#\013\n\021\"\001`\003=\t\007\017\0357zI\021,g-Y;mi\022\022\004BCAi\003#\013\t\021\"\003\002T\006Y!/Z1e%\026\034x\016\034<f)\t\t)\016E\002f\003/L1!!7g\005\031y%M[3di\036I\021Q\034\006\002\002#%\021q\\\001\007'\026dWm\031;\021\007%\n\tOB\005\002,)\t\t\021#\003\002dN)\021\021]AsuAQ\021qSAt;\005eB&a\023\n\t\005%\030\021\024\002\022\003\n\034HO]1di\032+hn\031;j_:\034\004b\002\013\002b\022\005\021Q\036\013\003\003?D!\"!\b\002b\006\005IQIA\020\021)\tI+!9\002\002\023\005\0251\037\013\t\003\027\n)0a>\002z\"11$!=A\002uA\001\"!\016\002r\002\007\021\021\b\005\tc\005E\b\023!a\001Y!Q\0211WAq\003\003%\t)!@\025\t\005}(q\001\t\006\035\005e&\021\001\t\b\035\t\rQ$!\017-\023\r\021)a\004\002\007)V\004H.Z\032\t\025\005\025\0271`A\001\002\004\tY\005C\005\003\f\005\005\030\023!C\001?\006YB\005\\3tg&t\027\016\036\023he\026\fG/\032:%I\0264\027-\0367uIMB\021Ba\004\002bF\005I\021A0\002\037\005\004\b\017\\=%I\0264\027-\0367uIMB!\"!5\002b\006\005I\021BAj\r\031\021)B\003#\003\030\tQ1\013^1si^\013Go\0315\024\013\tMQb\016\036\t\025\tm!1\003BK\002\023\005!'\001\004uCJ<W\r\036\005\013\005?\021\031B!E!\002\023a\023a\002;be\036,G\017\t\005\b)\tMA\021\001B\022)\021\021)Ca\n\021\007%\022\031\002C\004\003\034\t\005\002\031\001\027\t\0231\023\031\"!A\005\002\t-B\003\002B\023\005[A\021Ba\007\003*A\005\t\031\001\027\t\021E\023\031\"%A\005\002}C\001B\031B\n\003\003%\te\031\005\t[\nM\021\021!C\001]\"I1Oa\005\002\002\023\005!q\007\013\004k\ne\002\002C=\0036\005\005\t\031A8\t\021m\024\031\"!A\005BqD!\"!\003\003\024\005\005I\021\001B )\021\tiA!\021\t\021e\024i$!AA\002UD!\"a\006\003\024\005\005I\021IA\r\021)\tiBa\005\002\002\023\005\023q\004\005\013\003G\021\031\"!A\005B\t%C\003BA\007\005\027B\001\"\037B$\003\003\005\r!^\004\n\005\037R\021\021!E\005\005#\n!b\025;beR<\026\r^2i!\rI#1\013\004\n\005+Q\021\021!E\005\005+\032RAa\025\003Xi\002r!a&\003Z1\022)#\003\003\003\\\005e%!E!cgR\024\030m\031;Gk:\034G/[8oc!9ACa\025\005\002\t}CC\001B)\021)\tiBa\025\002\002\023\025\023q\004\005\013\003S\023\031&!A\005\002\n\025D\003\002B\023\005OBqAa\007\003d\001\007A\006\003\006\0024\nM\023\021!CA\005W\"BA!\034\003pA!a\"!/-\021)\t)M!\033\002\002\003\007!Q\005\005\013\003#\024\031&!A\005\n\005Mwa\002B;\025!%%qO\001\005\027&\0347\016E\002*\005s2qAa\037\013\021\023\023iH\001\003LS\016\\7#\002B=\033]R\004b\002\013\003z\021\005!\021\021\013\003\005oB\001B\031B=\003\003%\te\031\005\t[\ne\024\021!C\001]\"I1O!\037\002\002\023\005!\021\022\013\004k\n-\005\002C=\003\b\006\005\t\031A8\t\021m\024I(!A\005BqD!\"!\003\003z\005\005I\021\001BI)\021\tiAa%\t\021e\024y)!AA\002UD!\"a\006\003z\005\005I\021IA\r\021)\tiB!\037\002\002\023\005\023q\004\005\013\003#\024I(!A\005\n\005MgAC\006\003!\003\r\tA!(\0050N\031!1T\007\t\021\t\005&1\024C\001\005G\013a\001J5oSR$CC\001BS!\rq!qU\005\004\005S{!\001B+oSR4AB!,\003\034B\005\031\021\003BX\005W\024a\"\0238c_b,\005\020^3og&|gnE\002\003,6A\001B!)\003,\022\005!1\025\005\n\005k\023YK1A\005\0029\f\021\003R*M\023:\024w\016_)vKV,7+\033>f\021!\021ILa+!\002\023y\027A\005#T\031&s'm\034=Rk\026,XmU5{K\002B!B!0\003,\n\007I\021\001B`\003\035IgNY8y\035J,\"A!1\021\t\t\r'qZ\007\003\005\013TAAa2\003J\0061\021\r^8nS\016T1A\tBf\025\r\021i\r[\001\005kRLG.\003\003\003R\n\025'!D!u_6L7-\0238uK\036,'\017C\005\003V\n-\006\025!\003\003B\006A\021N\0342pq:\023\b\005\003\006\003Z\n-&\031!C\001\0057\f!\"\0338c_b\004&o\0349t+\t\021i\016E\002.\005?L1A!9\005\005\025\001&o\0349t\021%\021)Oa+!\002\023\021i.A\006j]\n|\007\020\025:paN\004\003b\002Bu\005W#\tAM\001\f]\026<(+Z2fSZ,'\017\005\003\003n\n=XB\001BN\023\021\021\tPa=\003\023\025CH/\0328tS>t'b\001B{\t\005A\021i\031;pe\022\033F\n\003\006\003z\nm%\031!C\006\005w\fQ\002Z3bI2Lg.Z(sI\026\024XC\001B!\031\021ypa\004\004\0269!1\021AB\006\035\021\031\031a!\003\016\005\r\025!bAB\004+\0051AH]8pizJ\021\001E\005\004\007\033y\021a\0029bG.\fw-Z\005\005\007#\031\031B\001\005Pe\022,'/\0338h\025\r\031ia\004\t\004\007/9bBA\005\001\021%\031YBa'!\002\023\021i0\001\beK\006$G.\0338f\037J$WM\035\021\007\017\r}!1\024\003\004\"\tQ\021J\0342pq\006\033Go\034:\024\017\ruQba\t\004*A\031Qf!\n\n\007\r\035BAA\003BGR|'\017E\002.\007WI1a!\f\005\0051\t5\r^8s\031><w-\0338h\021)\031\td!\b\003\002\003\006Ia\\\001\005g&TX\rC\004\025\007;!\ta!\016\025\t\r]2\021\b\t\005\005[\034i\002C\004\0042\rM\002\031A8\t\025\ru2Q\004a\001\n\003\031y$A\004dY&,g\016^:\026\005\r\005\003CBB\"\007\023\032)\"\004\002\004F)\0311qI@\002\0175,H/\0312mK&!11JB#\005\025\tV/Z;f\021)\031ye!\bA\002\023\0051\021K\001\fG2LWM\034;t?\022*\027\017\006\003\003&\016M\003\"C=\004N\005\005\t\031AB!\021%\0319f!\b!B\023\031\t%\001\005dY&,g\016^:!\021)\031Yf!\bC\002\023\0051QL\001\t[\026\0348/Y4fgV\0211q\f\t\006\007\007\032I%\036\005\n\007G\032i\002)A\005\007?\n\021\"\\3tg\006<Wm\035\021\t\025\r\0354Q\004a\001\n\003\031I'\001\tdY&,g\016^:CsRKW.Z8viV\02111\016\t\007\007[\032\031h!\006\016\005\r=$bAB9\006I\021.\\7vi\006\024G.Z\005\005\007k\032yGA\004Ue\026,7+\032;\t\025\re4Q\004a\001\n\003\031Y(\001\013dY&,g\016^:CsRKW.Z8vi~#S-\035\013\005\005K\033i\bC\005z\007o\n\t\0211\001\004l!I1\021QB\017A\003&11N\001\022G2LWM\034;t\005f$\026.\\3pkR\004\003BCBC\007;\001\r\021\"\001\004\b\006q\001O]5oi\026$w+\031:oS:<WCAA\007\021)\031Yi!\bA\002\023\0051QR\001\023aJLg\016^3e/\006\024h.\0338h?\022*\027\017\006\003\003&\016=\005\"C=\004\n\006\005\t\031AA\007\021%\031\031j!\b!B\023\ti!A\bqe&tG/\0323XCJt\027N\\4!\021!\0319j!\b\005\002\re\025\001D3ocV,W/Z)vKJLH\003\002BS\0077C\001b!(\004\026\002\0071QC\001\002c\"A1\021UB\017\t\003\031\031+\001\bf]F,X-^3NKN\034\030mZ3\025\t\t\0256Q\025\005\b\007O\033y\n1\001v\003\ri7o\032\005\r\007W\033i\0021AA\002\023\0051QV\001\013GV\024(/\0328u\033N<W#A;\t\031\rE6Q\004a\001\002\004%\taa-\002\035\r,(O]3oi6\033xm\030\023fcR!!QUB[\021!I8qVA\001\002\004)\b\002CB]\007;\001\013\025B;\002\027\r,(O]3oi6\033x\r\t\005\013\007{\033iB1A\005\002\r}\026aD2mS\026tG\017\025:fI&\034\027\r^3\026\005\r\005\007c\002\b\004D\016U\021QB\005\004\007\013|!!\003$v]\016$\030n\03482\021%\031Im!\b!\002\023\031\t-\001\tdY&,g\016\036)sK\022L7-\031;fA!a1QZB\017\001\004\005\r\021\"\001\004P\006i1-\036:sK:$8+\0327fGR,\"a!5\021\t\r]\021\021\006\005\r\007+\034i\0021AA\002\023\0051q[\001\022GV\024(/\0328u'\026dWm\031;`I\025\fH\003\002BS\0073D\021\"_Bj\003\003\005\ra!5\t\023\ru7Q\004Q!\n\rE\027AD2veJ,g\016^*fY\026\034G\017\t\005\013\007C\034iB1A\005\002\r\r\030\001E7fgN\fw-\032)sK\022L7-\031;f+\t\031)\017\005\004\017\007\007,\030Q\002\005\n\007S\034i\002)A\005\007K\f\021#\\3tg\006<W\r\025:fI&\034\027\r^3!\021)\031io!\bA\002\023\0051q^\001\020GV\024(/\0328u\t\026\fG\r\\5oKV\0211\021\037\t\006\035\005e61\037\t\007\035\005}Vd!>\021\0075\03290C\002\004z\022\0211bQ1oG\026dG.\0312mK\"Q1Q`B\017\001\004%\taa@\002'\r,(O]3oi\022+\027\r\0327j]\026|F%Z9\025\t\t\025F\021\001\005\ns\016m\030\021!a\001\007cD\021\002\"\002\004\036\001\006Ka!=\002!\r,(O]3oi\022+\027\r\0327j]\026\004\003\002\003C\005\007;!\t\001b\003\002\017I,7-Z5wKV\021AQ\002\t\007\035\005mRO!*\t\025\021E!1\024b\001\n\023!\031\"A\005fqR\024\030\rV5nKV\021AQ\003\t\004=\021]\021b\001C\r?\tqa)\0338ji\026$UO]1uS>t\007\"\003C\017\0057\003\013\021\002C\013\003))\007\020\036:b)&lW\r\t\005\t\tC\021Y\n\"\001\005$\005)\021N\0342pqR\021AQ\005\013\005\tO!)\013\005\003\003n\022%bAB\006\003\034\002!Yc\005\003\005*\0215\002cA\027\0050%\0211\002\002\005\f\tg!IC!A!\002\023!)$\001\004tsN$X-\034\t\004[\021]\022b\001C\035\t\tY\021i\031;peNK8\017^3n\021\035!B\021\006C\001\t{!B\001b\n\005@!AA1\007C\036\001\004!)\004C\005\005D\021%\"\031!C\001e\005A!/Z2fSZ,'\017\003\005\005H\021%\002\025!\003-\003%\021XmY3jm\026\024\b\005\003\005\005L\021%B\021\001C'\003\0319W\r\036*fMR\tA\006\003\005\005R\021%B\021\001C*\003\021\031XM\0343\025\r\t\025FQ\013C,\021\035\021Y\002b\024A\0021Bqaa*\005P\001\007Q\002\003\006\005\\\021%\"\031!C\005\t'\ta\002Z3gCVdG\017V5nK>,H\017C\005\005`\021%\002\025!\003\005\026\005yA-\0324bk2$H+[7f_V$\b\005\003\005\005\n\021%B\021\001C2)\r)HQ\r\005\013\tO\"\t\007%AA\002\021U\021a\002;j[\026|W\017\036\005\t\tW\"I\003\"\001\005n\00511/\0327fGR,B\001b\034\005xQ!A\021\017CD)\021!\031\bb!\021\t\021UDq\017\007\001\t!!I\b\"\033C\002\021m$!\001+\022\007\021uT\017E\002\017\tJ1\001\"!\020\005\035qu\016\0365j]\036D\001\"!\016\005j\001\007AQ\021\t\007\035\005mR\017b\035\t\025\021\035D\021\016I\001\002\004!)\002\003\005\005\f\022%B\021\001CG\003\0259\030\r^2i)\021\021)\013b$\t\017\tmA\021\022a\001Y!AA1\023C\025\t\003\022\031+\001\005gS:\fG.\033>f\021)!9\n\"\013\022\002\023\005A\021T\001\022e\026\034W-\033<fI\021,g-Y;mi\022\nTC\001CNU\r!)\002\026\005\013\t?#I#%A\005\002\021\005\026\001E:fY\026\034G\017\n3fM\006,H\016\036\0232+\021!I\nb)\005\021\021eDQ\024b\001\twB\001\002b\r\005 \001\017AQ\007\005\t\tS\023Y\nb\001\005,\006y1/\0328eKJ4%o\\7J]\n|\007\020F\002-\t[C\001\002\"\t\005(\002\017Aq\005\b\004[\021E\026b\001B{\t\001")
/*     */ public interface Inbox {
/*     */   void akka$actor$dsl$Inbox$_setter_$akka$actor$dsl$Inbox$$deadlineOrder_$eq(Ordering paramOrdering);
/*     */   
/*     */   void akka$actor$dsl$Inbox$_setter_$akka$actor$dsl$Inbox$$extraTime_$eq(FiniteDuration paramFiniteDuration);
/*     */   
/*     */   Ordering<Query> akka$actor$dsl$Inbox$$deadlineOrder();
/*     */   
/*     */   FiniteDuration akka$actor$dsl$Inbox$$extraTime();
/*     */   
/*     */   Inbox inbox(ActorSystem paramActorSystem);
/*     */   
/*     */   ActorRef senderFromInbox(Inbox paramInbox);
/*     */   
/*     */   public static class Get implements Query, Product, Serializable {
/*     */     private final Deadline deadline;
/*     */     
/*     */     private final ActorRef client;
/*     */     
/*     */     public Deadline deadline() {
/*  34 */       return this.deadline;
/*     */     }
/*     */     
/*     */     public ActorRef client() {
/*  34 */       return this.client;
/*     */     }
/*     */     
/*     */     public Get copy(Deadline deadline, ActorRef client) {
/*  34 */       return new Get(deadline, client);
/*     */     }
/*     */     
/*     */     public Deadline copy$default$1() {
/*  34 */       return deadline();
/*     */     }
/*     */     
/*     */     public ActorRef copy$default$2() {
/*  34 */       return client();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  34 */       return "Get";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  34 */       return 2;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  34 */       int i = x$1;
/*  34 */       switch (i) {
/*     */         default:
/*  34 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 1:
/*     */         
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  34 */       return deadline();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  34 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  34 */       return x$1 instanceof Get;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  34 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  34 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
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
/*     */       //   8: instanceof akka/actor/dsl/Inbox$Get
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 116
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/actor/dsl/Inbox$Get
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual deadline : ()Lscala/concurrent/duration/Deadline;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual deadline : ()Lscala/concurrent/duration/Deadline;
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
/*     */       //   64: invokevirtual client : ()Lakka/actor/ActorRef;
/*     */       //   67: aload #4
/*     */       //   69: invokevirtual client : ()Lakka/actor/ActorRef;
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
/*     */       //   #34	-> 0
/*     */       //   #63	-> 14
/*     */       //   #34	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	118	0	this	Lakka/actor/dsl/Inbox$Get;
/*     */       //   0	118	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public Get(Deadline deadline, ActorRef client) {
/*  34 */       Product.class.$init$(this);
/*     */     }
/*     */     
/*     */     public Get withClient(ActorRef c) {
/*  35 */       ActorRef x$2 = c;
/*  35 */       Deadline x$3 = copy$default$1();
/*  35 */       return copy(x$3, x$2);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Get$ extends AbstractFunction2<Deadline, ActorRef, Get> implements Serializable {
/*     */     public static final Get$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*     */       return "Get";
/*     */     }
/*     */     
/*     */     public Inbox.Get apply(Deadline deadline, ActorRef client) {
/*     */       return new Inbox.Get(deadline, client);
/*     */     }
/*     */     
/*     */     public Option<Tuple2<Deadline, ActorRef>> unapply(Inbox.Get x$0) {
/*     */       return (x$0 == null) ? (Option<Tuple2<Deadline, ActorRef>>)scala.None$.MODULE$ : (Option<Tuple2<Deadline, ActorRef>>)new Some(new Tuple2(x$0.deadline(), x$0.client()));
/*     */     }
/*     */     
/*     */     public ActorRef $lessinit$greater$default$2() {
/*     */       null;
/*     */       return null;
/*     */     }
/*     */     
/*     */     public ActorRef apply$default$2() {
/*     */       null;
/*     */       return null;
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Get$() {
/*     */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Select implements Query, Product, Serializable {
/*     */     private final Deadline deadline;
/*     */     
/*     */     private final PartialFunction<Object, Object> predicate;
/*     */     
/*     */     private final ActorRef client;
/*     */     
/*     */     public Deadline deadline() {
/*  37 */       return this.deadline;
/*     */     }
/*     */     
/*     */     public PartialFunction<Object, Object> predicate() {
/*  37 */       return this.predicate;
/*     */     }
/*     */     
/*     */     public ActorRef client() {
/*  37 */       return this.client;
/*     */     }
/*     */     
/*     */     public Select copy(Deadline deadline, PartialFunction<Object, Object> predicate, ActorRef client) {
/*  37 */       return new Select(deadline, predicate, client);
/*     */     }
/*     */     
/*     */     public Deadline copy$default$1() {
/*  37 */       return deadline();
/*     */     }
/*     */     
/*     */     public PartialFunction<Object, Object> copy$default$2() {
/*  37 */       return predicate();
/*     */     }
/*     */     
/*     */     public ActorRef copy$default$3() {
/*  37 */       return client();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  37 */       return "Select";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  37 */       return 3;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  37 */       int i = x$1;
/*  37 */       switch (i) {
/*     */         default:
/*  37 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 2:
/*     */         
/*     */         case 1:
/*     */         
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  37 */       return deadline();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  37 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  37 */       return x$1 instanceof Select;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  37 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  37 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 144
/*     */       //   5: aload_1
/*     */       //   6: astore_2
/*     */       //   7: aload_2
/*     */       //   8: instanceof akka/actor/dsl/Inbox$Select
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 148
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/actor/dsl/Inbox$Select
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual deadline : ()Lscala/concurrent/duration/Deadline;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual deadline : ()Lscala/concurrent/duration/Deadline;
/*     */       //   40: astore #5
/*     */       //   42: dup
/*     */       //   43: ifnonnull -> 55
/*     */       //   46: pop
/*     */       //   47: aload #5
/*     */       //   49: ifnull -> 63
/*     */       //   52: goto -> 140
/*     */       //   55: aload #5
/*     */       //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   60: ifeq -> 140
/*     */       //   63: aload_0
/*     */       //   64: invokevirtual predicate : ()Lscala/PartialFunction;
/*     */       //   67: aload #4
/*     */       //   69: invokevirtual predicate : ()Lscala/PartialFunction;
/*     */       //   72: astore #6
/*     */       //   74: dup
/*     */       //   75: ifnonnull -> 87
/*     */       //   78: pop
/*     */       //   79: aload #6
/*     */       //   81: ifnull -> 95
/*     */       //   84: goto -> 140
/*     */       //   87: aload #6
/*     */       //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   92: ifeq -> 140
/*     */       //   95: aload_0
/*     */       //   96: invokevirtual client : ()Lakka/actor/ActorRef;
/*     */       //   99: aload #4
/*     */       //   101: invokevirtual client : ()Lakka/actor/ActorRef;
/*     */       //   104: astore #7
/*     */       //   106: dup
/*     */       //   107: ifnonnull -> 119
/*     */       //   110: pop
/*     */       //   111: aload #7
/*     */       //   113: ifnull -> 127
/*     */       //   116: goto -> 140
/*     */       //   119: aload #7
/*     */       //   121: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   124: ifeq -> 140
/*     */       //   127: aload #4
/*     */       //   129: aload_0
/*     */       //   130: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   133: ifeq -> 140
/*     */       //   136: iconst_1
/*     */       //   137: goto -> 141
/*     */       //   140: iconst_0
/*     */       //   141: ifeq -> 148
/*     */       //   144: iconst_1
/*     */       //   145: goto -> 149
/*     */       //   148: iconst_0
/*     */       //   149: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #37	-> 0
/*     */       //   #63	-> 14
/*     */       //   #37	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	150	0	this	Lakka/actor/dsl/Inbox$Select;
/*     */       //   0	150	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public Select(Deadline deadline, PartialFunction<Object, Object> predicate, ActorRef client) {
/*  37 */       Product.class.$init$(this);
/*     */     }
/*     */     
/*     */     public Select withClient(ActorRef c) {
/*  38 */       ActorRef x$4 = c;
/*  38 */       Deadline x$5 = copy$default$1();
/*  38 */       PartialFunction<Object, Object> x$6 = copy$default$2();
/*  38 */       return copy(x$5, x$6, x$4);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Select$ extends AbstractFunction3<Deadline, PartialFunction<Object, Object>, ActorRef, Select> implements Serializable {
/*     */     public static final Select$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*     */       return "Select";
/*     */     }
/*     */     
/*     */     public Inbox.Select apply(Deadline deadline, PartialFunction<Object, Object> predicate, ActorRef client) {
/*     */       return new Inbox.Select(deadline, predicate, client);
/*     */     }
/*     */     
/*     */     public Option<Tuple3<Deadline, PartialFunction<Object, Object>, ActorRef>> unapply(Inbox.Select x$0) {
/*     */       return (x$0 == null) ? (Option<Tuple3<Deadline, PartialFunction<Object, Object>, ActorRef>>)scala.None$.MODULE$ : (Option<Tuple3<Deadline, PartialFunction<Object, Object>, ActorRef>>)new Some(new Tuple3(x$0.deadline(), x$0.predicate(), x$0.client()));
/*     */     }
/*     */     
/*     */     public ActorRef $lessinit$greater$default$3() {
/*     */       null;
/*     */       return null;
/*     */     }
/*     */     
/*     */     public ActorRef apply$default$3() {
/*     */       null;
/*     */       return null;
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Select$() {
/*     */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class StartWatch implements Product, Serializable {
/*     */     private final ActorRef target;
/*     */     
/*     */     public ActorRef target() {
/*  40 */       return this.target;
/*     */     }
/*     */     
/*     */     public StartWatch copy(ActorRef target) {
/*  40 */       return new StartWatch(target);
/*     */     }
/*     */     
/*     */     public ActorRef copy$default$1() {
/*  40 */       return target();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  40 */       return "StartWatch";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  40 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  40 */       int i = x$1;
/*  40 */       switch (i) {
/*     */         default:
/*  40 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  40 */       return target();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  40 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  40 */       return x$1 instanceof StartWatch;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  40 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  40 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
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
/*     */       //   8: instanceof akka/actor/dsl/Inbox$StartWatch
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 84
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/actor/dsl/Inbox$StartWatch
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual target : ()Lakka/actor/ActorRef;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual target : ()Lakka/actor/ActorRef;
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
/*     */       //   #40	-> 0
/*     */       //   #63	-> 14
/*     */       //   #40	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	86	0	this	Lakka/actor/dsl/Inbox$StartWatch;
/*     */       //   0	86	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public StartWatch(ActorRef target) {
/*  40 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class StartWatch$ extends AbstractFunction1<ActorRef, StartWatch> implements Serializable {
/*     */     public static final StartWatch$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*  40 */       return "StartWatch";
/*     */     }
/*     */     
/*     */     public Inbox.StartWatch apply(ActorRef target) {
/*  40 */       return new Inbox.StartWatch(target);
/*     */     }
/*     */     
/*     */     public Option<ActorRef> unapply(Inbox.StartWatch x$0) {
/*  40 */       return (x$0 == null) ? (Option<ActorRef>)scala.None$.MODULE$ : (Option<ActorRef>)new Some(x$0.target());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  40 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public StartWatch$() {
/*  40 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Kick$ implements Product, Serializable {
/*     */     public static final Kick$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/*  41 */       return "Kick";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  41 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  41 */       int i = x$1;
/*  41 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  41 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  41 */       return x$1 instanceof Kick$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  41 */       return 2338406;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  41 */       return "Kick";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  41 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Kick$() {
/*  43 */       MODULE$ = this;
/*  43 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class InboxExtension$class {
/*     */     public static void $init$(ActorDSL.Extension $this) {
/*  50 */       $this.akka$actor$dsl$Inbox$InboxExtension$_setter_$DSLInboxQueueSize_$eq($this.config().getInt("inbox-size"));
/*  52 */       $this.akka$actor$dsl$Inbox$InboxExtension$_setter_$inboxNr_$eq(new AtomicInteger());
/*  53 */       $this.akka$actor$dsl$Inbox$InboxExtension$_setter_$inboxProps_$eq(akka.actor.Props$.MODULE$.apply(Inbox.InboxActor.class, (Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { akka.actor.ActorDSL$.MODULE$, BoxesRunTime.boxToInteger($this.DSLInboxQueueSize()) })));
/*     */     }
/*     */     
/*     */     public static ActorRef newReceiver(ActorDSL.Extension $this) {
/*  55 */       return $this.mkChild($this.inboxProps(), (new StringBuilder()).append("inbox-").append(BoxesRunTime.boxToInteger($this.inboxNr().incrementAndGet())).toString());
/*     */     }
/*     */   }
/*     */   
/*     */   public class $anon$1 implements Ordering<Query> {
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/*  58 */       return Ordering.class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/*  58 */       return Ordering.class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/*  58 */       return Ordering.class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/*  58 */       return Ordering.class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/*  58 */       return Ordering.class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/*  58 */       return Ordering.class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Object max(Object x, Object y) {
/*  58 */       return Ordering.class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Object min(Object x, Object y) {
/*  58 */       return Ordering.class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Inbox.Query> reverse() {
/*  58 */       return Ordering.class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/*  58 */       return Ordering.class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Inbox.Query>.Ops mkOrderingOps(Object lhs) {
/*  58 */       return Ordering.class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public $anon$1(akka.actor.ActorDSL$ $outer) {
/*  58 */       PartialOrdering.class.$init$((PartialOrdering)this);
/*  58 */       Ordering.class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Inbox.Query left, Inbox.Query right) {
/*  59 */       return left.deadline().time().compare((Duration)right.deadline().time());
/*     */     }
/*     */   }
/*     */   
/*     */   public class InboxActor implements Actor, ActorLogging {
/*     */     private final int size;
/*     */     
/*     */     private Queue<Inbox.Query> clients;
/*     */     
/*     */     private final Queue<Object> messages;
/*     */     
/*     */     private TreeSet<Inbox.Query> clientsByTimeout;
/*     */     
/*     */     private boolean printedWarning;
/*     */     
/*     */     private Object currentMsg;
/*     */     
/*     */     private final Function1<Inbox.Query, Object> clientPredicate;
/*     */     
/*     */     private Inbox.Select currentSelect;
/*     */     
/*     */     private final Function1<Object, Object> messagePredicate;
/*     */     
/*     */     private Option<Tuple2<Deadline, Cancellable>> currentDeadline;
/*     */     
/*     */     private LoggingAdapter akka$actor$ActorLogging$$_log;
/*     */     
/*     */     private final ActorContext context;
/*     */     
/*     */     private final ActorRef self;
/*     */     
/*     */     public LoggingAdapter akka$actor$ActorLogging$$_log() {
/*  62 */       return this.akka$actor$ActorLogging$$_log;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void akka$actor$ActorLogging$$_log_$eq(LoggingAdapter x$1) {
/*  62 */       this.akka$actor$ActorLogging$$_log = x$1;
/*     */     }
/*     */     
/*     */     public LoggingAdapter log() {
/*  62 */       return ActorLogging.class.log(this);
/*     */     }
/*     */     
/*     */     public ActorContext context() {
/*  62 */       return this.context;
/*     */     }
/*     */     
/*     */     public final ActorRef self() {
/*  62 */       return this.self;
/*     */     }
/*     */     
/*     */     public void akka$actor$Actor$_setter_$context_$eq(ActorContext x$1) {
/*  62 */       this.context = x$1;
/*     */     }
/*     */     
/*     */     public final void akka$actor$Actor$_setter_$self_$eq(ActorRef x$1) {
/*  62 */       this.self = x$1;
/*     */     }
/*     */     
/*     */     public final ActorRef sender() {
/*  62 */       return Actor.class.sender(this);
/*     */     }
/*     */     
/*     */     public void aroundReceive(PartialFunction receive, Object msg) {
/*  62 */       Actor.class.aroundReceive(this, receive, msg);
/*     */     }
/*     */     
/*     */     public void aroundPreStart() {
/*  62 */       Actor.class.aroundPreStart(this);
/*     */     }
/*     */     
/*     */     public void aroundPostStop() {
/*  62 */       Actor.class.aroundPostStop(this);
/*     */     }
/*     */     
/*     */     public void aroundPreRestart(Throwable reason, Option message) {
/*  62 */       Actor.class.aroundPreRestart(this, reason, message);
/*     */     }
/*     */     
/*     */     public void aroundPostRestart(Throwable reason) {
/*  62 */       Actor.class.aroundPostRestart(this, reason);
/*     */     }
/*     */     
/*     */     public SupervisorStrategy supervisorStrategy() {
/*  62 */       return Actor.class.supervisorStrategy(this);
/*     */     }
/*     */     
/*     */     public void preStart() throws Exception {
/*  62 */       Actor.class.preStart(this);
/*     */     }
/*     */     
/*     */     public void postStop() throws Exception {
/*  62 */       Actor.class.postStop(this);
/*     */     }
/*     */     
/*     */     public void preRestart(Throwable reason, Option message) throws Exception {
/*  62 */       Actor.class.preRestart(this, reason, message);
/*     */     }
/*     */     
/*     */     public void postRestart(Throwable reason) throws Exception {
/*  62 */       Actor.class.postRestart(this, reason);
/*     */     }
/*     */     
/*     */     public void unhandled(Object message) {
/*  62 */       Actor.class.unhandled(this, message);
/*     */     }
/*     */     
/*     */     public InboxActor(akka.actor.ActorDSL$ $outer, int size) {
/*  62 */       Actor.class.$init$(this);
/*  62 */       ActorLogging.class.$init$(this);
/*  63 */       this.clients = (Queue<Inbox.Query>)scala.collection.mutable.Queue$.MODULE$.empty();
/*  64 */       this.messages = (Queue<Object>)scala.collection.mutable.Queue$.MODULE$.empty();
/*  65 */       this.clientsByTimeout = scala.collection.immutable.TreeSet$.MODULE$.empty($outer.akka$actor$dsl$Inbox$$deadlineOrder());
/*  66 */       this.printedWarning = false;
/*  85 */       this.clientPredicate = (Function1<Inbox.Query, Object>)new $anonfun$1(this);
/*  92 */       this.messagePredicate = (Function1<Object, Object>)new $anonfun$2(this);
/*  94 */       this.currentDeadline = (Option<Tuple2<Deadline, Cancellable>>)scala.None$.MODULE$;
/*     */     }
/*     */     
/*     */     public Queue<Inbox.Query> clients() {
/*     */       return this.clients;
/*     */     }
/*     */     
/*     */     public void clients_$eq(Queue<Inbox.Query> x$1) {
/*     */       this.clients = x$1;
/*     */     }
/*     */     
/*     */     public Queue<Object> messages() {
/*     */       return this.messages;
/*     */     }
/*     */     
/*     */     public TreeSet<Inbox.Query> clientsByTimeout() {
/*     */       return this.clientsByTimeout;
/*     */     }
/*     */     
/*     */     public void clientsByTimeout_$eq(TreeSet<Inbox.Query> x$1) {
/*     */       this.clientsByTimeout = x$1;
/*     */     }
/*     */     
/*     */     public boolean printedWarning() {
/*     */       return this.printedWarning;
/*     */     }
/*     */     
/*     */     public void printedWarning_$eq(boolean x$1) {
/*     */       this.printedWarning = x$1;
/*     */     }
/*     */     
/*     */     public void enqueueQuery(Inbox.Query q) {
/*     */       Inbox.Query query = q.withClient(sender());
/*     */       (new Inbox.Query[1])[0] = query;
/*     */       clients().enqueue((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Inbox.Query[1]));
/*     */       clientsByTimeout_$eq(clientsByTimeout().$plus(query));
/*     */     }
/*     */     
/*     */     public void enqueueMessage(Object msg) {
/*     */       if (messages().size() < this.size) {
/*     */         messages().enqueue((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { msg }));
/*     */       } else if (!printedWarning()) {
/*     */         log().warning((new StringBuilder()).append("dropping message: either your program is buggy or you might want to increase akka.actor.dsl.inbox-size, current value is ").append(BoxesRunTime.boxToInteger(this.size)).toString());
/*     */         printedWarning_$eq(true);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Object currentMsg() {
/*     */       return this.currentMsg;
/*     */     }
/*     */     
/*     */     public void currentMsg_$eq(Object x$1) {
/*     */       this.currentMsg = x$1;
/*     */     }
/*     */     
/*     */     public Function1<Inbox.Query, Object> clientPredicate() {
/*     */       return this.clientPredicate;
/*     */     }
/*     */     
/*     */     public class $anonfun$1 extends AbstractFunction1<Inbox.Query, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Inbox.Query x0$1) {
/*     */         boolean bool;
/*     */         Inbox.Query query = x0$1;
/*     */         if (query instanceof Inbox.Get) {
/*     */           bool = true;
/*     */         } else if (query instanceof Inbox.Select) {
/*     */           Inbox.Select select = (Inbox.Select)query;
/*     */           PartialFunction<Object, Object> p = select.predicate();
/*     */           bool = p.isDefinedAt(this.$outer.currentMsg());
/*     */         } else {
/*     */           bool = false;
/*     */         } 
/*     */         return bool;
/*     */       }
/*     */       
/*     */       public $anonfun$1(Inbox.InboxActor $outer) {}
/*     */     }
/*     */     
/*     */     public Inbox.Select currentSelect() {
/*     */       return this.currentSelect;
/*     */     }
/*     */     
/*     */     public void currentSelect_$eq(Inbox.Select x$1) {
/*     */       this.currentSelect = x$1;
/*     */     }
/*     */     
/*     */     public Function1<Object, Object> messagePredicate() {
/*     */       return this.messagePredicate;
/*     */     }
/*     */     
/*     */     public class $anonfun$2 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Object msg) {
/*     */         return this.$outer.currentSelect().predicate().isDefinedAt(msg);
/*     */       }
/*     */       
/*     */       public $anonfun$2(Inbox.InboxActor $outer) {}
/*     */     }
/*     */     
/*     */     public Option<Tuple2<Deadline, Cancellable>> currentDeadline() {
/*  94 */       return this.currentDeadline;
/*     */     }
/*     */     
/*     */     public void currentDeadline_$eq(Option<Tuple2<Deadline, Cancellable>> x$1) {
/*  94 */       this.currentDeadline = x$1;
/*     */     }
/*     */     
/*     */     public PartialFunction<Object, BoxedUnit> receive() {
/* 132 */       return (new Inbox$InboxActor$$anonfun$receive$1(this)).andThen((Function1)new Inbox$InboxActor$$anonfun$receive$2(this));
/*     */     }
/*     */     
/*     */     public class Inbox$InboxActor$$anonfun$receive$1 extends AbstractPartialFunction.mcVL.sp<Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final <A1, B1> B1 applyOrElse(Object x1, Function1 default) {
/*     */         // Byte code:
/*     */         //   0: aload_1
/*     */         //   1: astore_3
/*     */         //   2: aload_3
/*     */         //   3: instanceof akka/actor/dsl/Inbox$Get
/*     */         //   6: ifeq -> 86
/*     */         //   9: aload_3
/*     */         //   10: checkcast akka/actor/dsl/Inbox$Get
/*     */         //   13: astore #4
/*     */         //   15: aload_0
/*     */         //   16: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   19: invokevirtual messages : ()Lscala/collection/mutable/Queue;
/*     */         //   22: invokevirtual isEmpty : ()Z
/*     */         //   25: ifeq -> 43
/*     */         //   28: aload_0
/*     */         //   29: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   32: aload #4
/*     */         //   34: invokevirtual enqueueQuery : (Lakka/actor/dsl/Inbox$Query;)V
/*     */         //   37: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   40: goto -> 81
/*     */         //   43: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */         //   46: aload_0
/*     */         //   47: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   50: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */         //   53: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */         //   56: aload_0
/*     */         //   57: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   60: invokevirtual messages : ()Lscala/collection/mutable/Queue;
/*     */         //   63: invokevirtual dequeue : ()Ljava/lang/Object;
/*     */         //   66: aload_0
/*     */         //   67: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   70: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */         //   73: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */         //   78: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   81: astore #5
/*     */         //   83: goto -> 724
/*     */         //   86: aload_3
/*     */         //   87: instanceof akka/actor/dsl/Inbox$Select
/*     */         //   90: ifeq -> 282
/*     */         //   93: aload_3
/*     */         //   94: checkcast akka/actor/dsl/Inbox$Select
/*     */         //   97: astore #6
/*     */         //   99: aload_0
/*     */         //   100: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   103: invokevirtual messages : ()Lscala/collection/mutable/Queue;
/*     */         //   106: invokevirtual isEmpty : ()Z
/*     */         //   109: ifeq -> 127
/*     */         //   112: aload_0
/*     */         //   113: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   116: aload #6
/*     */         //   118: invokevirtual enqueueQuery : (Lakka/actor/dsl/Inbox$Query;)V
/*     */         //   121: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   124: goto -> 267
/*     */         //   127: aload_0
/*     */         //   128: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   131: aload #6
/*     */         //   133: invokevirtual currentSelect_$eq : (Lakka/actor/dsl/Inbox$Select;)V
/*     */         //   136: aload_0
/*     */         //   137: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   140: invokevirtual messages : ()Lscala/collection/mutable/Queue;
/*     */         //   143: aload_0
/*     */         //   144: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   147: invokevirtual messagePredicate : ()Lscala/Function1;
/*     */         //   150: invokevirtual dequeueFirst : (Lscala/Function1;)Lscala/Option;
/*     */         //   153: astore #7
/*     */         //   155: aload #7
/*     */         //   157: instanceof scala/Some
/*     */         //   160: ifeq -> 212
/*     */         //   163: aload #7
/*     */         //   165: checkcast scala/Some
/*     */         //   168: astore #8
/*     */         //   170: aload #8
/*     */         //   172: invokevirtual x : ()Ljava/lang/Object;
/*     */         //   175: astore #9
/*     */         //   177: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */         //   180: aload_0
/*     */         //   181: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   184: invokevirtual sender : ()Lakka/actor/ActorRef;
/*     */         //   187: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */         //   190: aload #9
/*     */         //   192: aload_0
/*     */         //   193: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   196: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */         //   199: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */         //   204: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   207: astore #10
/*     */         //   209: goto -> 254
/*     */         //   212: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */         //   215: aload #7
/*     */         //   217: astore #11
/*     */         //   219: dup
/*     */         //   220: ifnonnull -> 232
/*     */         //   223: pop
/*     */         //   224: aload #11
/*     */         //   226: ifnull -> 240
/*     */         //   229: goto -> 272
/*     */         //   232: aload #11
/*     */         //   234: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */         //   237: ifeq -> 272
/*     */         //   240: aload_0
/*     */         //   241: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   244: aload #6
/*     */         //   246: invokevirtual enqueueQuery : (Lakka/actor/dsl/Inbox$Query;)V
/*     */         //   249: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   252: astore #10
/*     */         //   254: aload_0
/*     */         //   255: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   258: aconst_null
/*     */         //   259: pop
/*     */         //   260: aconst_null
/*     */         //   261: invokevirtual currentSelect_$eq : (Lakka/actor/dsl/Inbox$Select;)V
/*     */         //   264: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   267: astore #5
/*     */         //   269: goto -> 724
/*     */         //   272: new scala/MatchError
/*     */         //   275: dup
/*     */         //   276: aload #7
/*     */         //   278: invokespecial <init> : (Ljava/lang/Object;)V
/*     */         //   281: athrow
/*     */         //   282: aload_3
/*     */         //   283: instanceof akka/actor/dsl/Inbox$StartWatch
/*     */         //   286: ifeq -> 325
/*     */         //   289: aload_3
/*     */         //   290: checkcast akka/actor/dsl/Inbox$StartWatch
/*     */         //   293: astore #12
/*     */         //   295: aload #12
/*     */         //   297: invokevirtual target : ()Lakka/actor/ActorRef;
/*     */         //   300: astore #13
/*     */         //   302: aload_0
/*     */         //   303: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   306: invokevirtual context : ()Lakka/actor/ActorContext;
/*     */         //   309: aload #13
/*     */         //   311: invokeinterface watch : (Lakka/actor/ActorRef;)Lakka/actor/ActorRef;
/*     */         //   316: pop
/*     */         //   317: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   320: astore #5
/*     */         //   322: goto -> 724
/*     */         //   325: getstatic akka/actor/dsl/Inbox$Kick$.MODULE$ : Lakka/actor/dsl/Inbox$Kick$;
/*     */         //   328: aload_3
/*     */         //   329: astore #14
/*     */         //   331: dup
/*     */         //   332: ifnonnull -> 344
/*     */         //   335: pop
/*     */         //   336: aload #14
/*     */         //   338: ifnull -> 352
/*     */         //   341: goto -> 536
/*     */         //   344: aload #14
/*     */         //   346: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */         //   349: ifeq -> 536
/*     */         //   352: getstatic scala/concurrent/duration/Deadline$.MODULE$ : Lscala/concurrent/duration/Deadline$;
/*     */         //   355: invokevirtual now : ()Lscala/concurrent/duration/Deadline;
/*     */         //   358: astore #15
/*     */         //   360: new akka/actor/dsl/Inbox$InboxActor$$anonfun$receive$1$$anonfun$3
/*     */         //   363: dup
/*     */         //   364: aload_0
/*     */         //   365: aload #15
/*     */         //   367: invokespecial <init> : (Lakka/actor/dsl/Inbox$InboxActor$$anonfun$receive$1;Lscala/concurrent/duration/Deadline;)V
/*     */         //   370: astore #16
/*     */         //   372: aload_0
/*     */         //   373: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   376: invokevirtual clientsByTimeout : ()Lscala/collection/immutable/TreeSet;
/*     */         //   379: invokevirtual iterator : ()Lscala/collection/Iterator;
/*     */         //   382: aload #16
/*     */         //   384: invokeinterface takeWhile : (Lscala/Function1;)Lscala/collection/Iterator;
/*     */         //   389: astore #17
/*     */         //   391: aload #17
/*     */         //   393: invokeinterface hasNext : ()Z
/*     */         //   398: ifeq -> 457
/*     */         //   401: aload #17
/*     */         //   403: invokeinterface next : ()Ljava/lang/Object;
/*     */         //   408: checkcast akka/actor/dsl/Inbox$Query
/*     */         //   411: astore #18
/*     */         //   413: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */         //   416: aload #18
/*     */         //   418: invokeinterface client : ()Lakka/actor/ActorRef;
/*     */         //   423: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */         //   426: new akka/actor/Status$Failure
/*     */         //   429: dup
/*     */         //   430: new java/util/concurrent/TimeoutException
/*     */         //   433: dup
/*     */         //   434: ldc 'deadline passed'
/*     */         //   436: invokespecial <init> : (Ljava/lang/String;)V
/*     */         //   439: invokespecial <init> : (Ljava/lang/Throwable;)V
/*     */         //   442: aload_0
/*     */         //   443: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   446: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */         //   449: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */         //   454: goto -> 391
/*     */         //   457: aload_0
/*     */         //   458: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   461: getstatic scala/collection/mutable/Queue$.MODULE$ : Lscala/collection/mutable/Queue$;
/*     */         //   464: invokevirtual empty : ()Lscala/collection/GenTraversable;
/*     */         //   467: checkcast scala/collection/generic/Growable
/*     */         //   470: aload_0
/*     */         //   471: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   474: invokevirtual clients : ()Lscala/collection/mutable/Queue;
/*     */         //   477: aload #16
/*     */         //   479: invokevirtual filterNot : (Lscala/Function1;)Ljava/lang/Object;
/*     */         //   482: checkcast scala/collection/TraversableOnce
/*     */         //   485: invokeinterface $plus$plus$eq : (Lscala/collection/TraversableOnce;)Lscala/collection/generic/Growable;
/*     */         //   490: checkcast scala/collection/mutable/Queue
/*     */         //   493: invokevirtual clients_$eq : (Lscala/collection/mutable/Queue;)V
/*     */         //   496: aload_0
/*     */         //   497: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   500: aload_0
/*     */         //   501: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   504: invokevirtual clientsByTimeout : ()Lscala/collection/immutable/TreeSet;
/*     */         //   507: new akka/actor/dsl/Inbox$Get
/*     */         //   510: dup
/*     */         //   511: aload #15
/*     */         //   513: getstatic akka/actor/dsl/Inbox$Get$.MODULE$ : Lakka/actor/dsl/Inbox$Get$;
/*     */         //   516: invokevirtual apply$default$2 : ()Lakka/actor/ActorRef;
/*     */         //   519: invokespecial <init> : (Lscala/concurrent/duration/Deadline;Lakka/actor/ActorRef;)V
/*     */         //   522: invokevirtual from : (Ljava/lang/Object;)Lscala/collection/immutable/TreeSet;
/*     */         //   525: invokevirtual clientsByTimeout_$eq : (Lscala/collection/immutable/TreeSet;)V
/*     */         //   528: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   531: astore #5
/*     */         //   533: goto -> 724
/*     */         //   536: aload_0
/*     */         //   537: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   540: invokevirtual clients : ()Lscala/collection/mutable/Queue;
/*     */         //   543: invokevirtual isEmpty : ()Z
/*     */         //   546: ifeq -> 563
/*     */         //   549: aload_0
/*     */         //   550: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   553: aload_3
/*     */         //   554: invokevirtual enqueueMessage : (Ljava/lang/Object;)V
/*     */         //   557: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   560: goto -> 722
/*     */         //   563: aload_0
/*     */         //   564: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   567: aload_3
/*     */         //   568: invokevirtual currentMsg_$eq : (Ljava/lang/Object;)V
/*     */         //   571: aload_0
/*     */         //   572: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   575: invokevirtual clients : ()Lscala/collection/mutable/Queue;
/*     */         //   578: aload_0
/*     */         //   579: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   582: invokevirtual clientPredicate : ()Lscala/Function1;
/*     */         //   585: invokevirtual dequeueFirst : (Lscala/Function1;)Lscala/Option;
/*     */         //   588: astore #19
/*     */         //   590: aload #19
/*     */         //   592: instanceof scala/Some
/*     */         //   595: ifeq -> 668
/*     */         //   598: aload #19
/*     */         //   600: checkcast scala/Some
/*     */         //   603: astore #20
/*     */         //   605: aload #20
/*     */         //   607: invokevirtual x : ()Ljava/lang/Object;
/*     */         //   610: checkcast akka/actor/dsl/Inbox$Query
/*     */         //   613: astore #21
/*     */         //   615: aload_0
/*     */         //   616: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   619: aload_0
/*     */         //   620: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   623: invokevirtual clientsByTimeout : ()Lscala/collection/immutable/TreeSet;
/*     */         //   626: aload #21
/*     */         //   628: invokevirtual $minus : (Ljava/lang/Object;)Lscala/collection/immutable/TreeSet;
/*     */         //   631: invokevirtual clientsByTimeout_$eq : (Lscala/collection/immutable/TreeSet;)V
/*     */         //   634: getstatic akka/actor/package$.MODULE$ : Lakka/actor/package$;
/*     */         //   637: aload #21
/*     */         //   639: invokeinterface client : ()Lakka/actor/ActorRef;
/*     */         //   644: invokevirtual actorRef2Scala : (Lakka/actor/ActorRef;)Lakka/actor/ScalaActorRef;
/*     */         //   647: aload_3
/*     */         //   648: aload_0
/*     */         //   649: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   652: invokevirtual self : ()Lakka/actor/ActorRef;
/*     */         //   655: invokeinterface $bang : (Ljava/lang/Object;Lakka/actor/ActorRef;)V
/*     */         //   660: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   663: astore #22
/*     */         //   665: goto -> 709
/*     */         //   668: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */         //   671: aload #19
/*     */         //   673: astore #23
/*     */         //   675: dup
/*     */         //   676: ifnonnull -> 688
/*     */         //   679: pop
/*     */         //   680: aload #23
/*     */         //   682: ifnull -> 696
/*     */         //   685: goto -> 727
/*     */         //   688: aload #23
/*     */         //   690: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */         //   693: ifeq -> 727
/*     */         //   696: aload_0
/*     */         //   697: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   700: aload_3
/*     */         //   701: invokevirtual enqueueMessage : (Ljava/lang/Object;)V
/*     */         //   704: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   707: astore #22
/*     */         //   709: aload_0
/*     */         //   710: getfield $outer : Lakka/actor/dsl/Inbox$InboxActor;
/*     */         //   713: aconst_null
/*     */         //   714: pop
/*     */         //   715: aconst_null
/*     */         //   716: invokevirtual currentMsg_$eq : (Ljava/lang/Object;)V
/*     */         //   719: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */         //   722: astore #5
/*     */         //   724: aload #5
/*     */         //   726: areturn
/*     */         //   727: new scala/MatchError
/*     */         //   730: dup
/*     */         //   731: aload #19
/*     */         //   733: invokespecial <init> : (Ljava/lang/Object;)V
/*     */         //   736: athrow
/*     */         // Line number table:
/*     */         //   Java source line number -> byte code offset
/*     */         //   #96	-> 0
/*     */         //   #97	-> 2
/*     */         //   #98	-> 15
/*     */         //   #99	-> 43
/*     */         //   #98	-> 81
/*     */         //   #100	-> 86
/*     */         //   #101	-> 99
/*     */         //   #103	-> 127
/*     */         //   #104	-> 136
/*     */         //   #105	-> 155
/*     */         //   #106	-> 212
/*     */         //   #108	-> 254
/*     */         //   #101	-> 267
/*     */         //   #104	-> 272
/*     */         //   #110	-> 282
/*     */         //   #111	-> 325
/*     */         //   #112	-> 352
/*     */         //   #113	-> 360
/*     */         //   #114	-> 372
/*     */         //   #115	-> 391
/*     */         //   #116	-> 401
/*     */         //   #117	-> 413
/*     */         //   #120	-> 457
/*     */         //   #121	-> 496
/*     */         //   #111	-> 531
/*     */         //   #123	-> 536
/*     */         //   #125	-> 563
/*     */         //   #126	-> 571
/*     */         //   #127	-> 590
/*     */         //   #128	-> 668
/*     */         //   #130	-> 709
/*     */         //   #123	-> 722
/*     */         //   #96	-> 724
/*     */         //   #126	-> 727
/*     */         // Local variable table:
/*     */         //   start	length	slot	name	descriptor
/*     */         //   0	737	0	this	Lakka/actor/dsl/Inbox$InboxActor$$anonfun$receive$1;
/*     */         //   0	737	1	x1	Ljava/lang/Object;
/*     */         //   0	737	2	default	Lscala/Function1;
/*     */         //   177	560	9	msg	Ljava/lang/Object;
/*     */         //   302	435	13	target	Lakka/actor/ActorRef;
/*     */         //   360	171	15	now	Lscala/concurrent/duration/Deadline;
/*     */         //   372	159	16	pred	Lscala/Function1;
/*     */         //   391	140	17	overdue	Lscala/collection/Iterator;
/*     */         //   413	41	18	toKick	Lakka/actor/dsl/Inbox$Query;
/*     */         //   615	122	21	q	Lakka/actor/dsl/Inbox$Query;
/*     */       }
/*     */       
/*     */       public final boolean isDefinedAt(Object x1) {
/*     */         boolean bool;
/*     */         Object object = x1;
/*     */         if (object instanceof Inbox.Get) {
/*     */           bool = true;
/*     */         } else if (object instanceof Inbox.Select) {
/*     */           bool = true;
/*     */         } else if (object instanceof Inbox.StartWatch) {
/*     */           bool = true;
/*     */         } else {
/*     */           Object object1 = object;
/*     */           if (Inbox.Kick$.MODULE$ == null) {
/*     */             if (object1 != null)
/*     */               boolean bool1 = true; 
/*     */           } else {
/*     */             if (Inbox.Kick$.MODULE$.equals(object1))
/*     */               boolean bool2 = true; 
/*     */             boolean bool1 = true;
/*     */           } 
/*     */           bool = true;
/*     */         } 
/*     */         return bool;
/*     */       }
/*     */       
/*     */       public Inbox$InboxActor$$anonfun$receive$1(Inbox.InboxActor $outer) {}
/*     */       
/*     */       public class Inbox$InboxActor$$anonfun$receive$1$$anonfun$3 extends AbstractFunction1<Inbox.Query, Object> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         private final Deadline now$1;
/*     */         
/*     */         public final boolean apply(Inbox.Query q) {
/*     */           return q.deadline().time().$less(this.now$1.time());
/*     */         }
/*     */         
/*     */         public Inbox$InboxActor$$anonfun$receive$1$$anonfun$3(Inbox$InboxActor$$anonfun$receive$1 $outer, Deadline now$1) {}
/*     */       }
/*     */     }
/*     */     
/*     */     public class Inbox$InboxActor$$anonfun$receive$2 extends AbstractFunction1<BoxedUnit, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public Inbox$InboxActor$$anonfun$receive$2(Inbox.InboxActor $outer) {}
/*     */       
/*     */       public final void apply(BoxedUnit x$1) {
/* 133 */         if (this.$outer.clients().isEmpty()) {
/* 134 */           if (this.$outer.currentDeadline().isDefined()) {
/* 135 */             ((Cancellable)((Tuple2)this.$outer.currentDeadline().get())._2()).cancel();
/* 136 */             this.$outer.currentDeadline_$eq((Option<Tuple2<Deadline, Cancellable>>)scala.None$.MODULE$);
/*     */           } 
/*     */         } else {
/* 139 */           Deadline next = ((Inbox.Query)this.$outer.clientsByTimeout().head()).deadline();
/* 141 */           if (this.$outer.currentDeadline().isEmpty()) {
/* 142 */             this.$outer.currentDeadline_$eq((Option<Tuple2<Deadline, Cancellable>>)new Some(new Tuple2(next, this.$outer.context().system().scheduler().scheduleOnce(next.timeLeft(), this.$outer.self(), Inbox.Kick$.MODULE$, (ExecutionContext)this.$outer.context().dispatcher(), this.$outer.self()))));
/*     */           } else {
/* 145 */             ((Cancellable)((Tuple2)this.$outer.currentDeadline().get())._2()).cancel();
/* 146 */             this.$outer.currentDeadline_$eq((Option<Tuple2<Deadline, Cancellable>>)new Some(new Tuple2(next, this.$outer.context().system().scheduler().scheduleOnce(next.timeLeft(), this.$outer.self(), Inbox.Kick$.MODULE$, (ExecutionContext)this.$outer.context().dispatcher(), this.$outer.self()))));
/*     */           } 
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class Inbox extends akka.actor.Inbox {
/*     */     private final ActorSystem system;
/*     */     
/*     */     private final ActorRef receiver;
/*     */     
/*     */     private final FiniteDuration defaultTimeout;
/*     */     
/*     */     public Inbox(akka.actor.ActorDSL$ $outer, ActorSystem system) {
/* 169 */       this.receiver = ((Inbox.InboxExtension)akka.actor.ActorDSL$Extension$.MODULE$.apply(system)).newReceiver();
/* 175 */       this.defaultTimeout = ((ActorDSL.Extension)akka.actor.ActorDSL$Extension$.MODULE$.apply(system)).DSLDefaultTimeout();
/*     */     }
/*     */     
/*     */     public ActorRef receiver() {
/*     */       return this.receiver;
/*     */     }
/*     */     
/*     */     public ActorRef getRef() {
/*     */       return receiver();
/*     */     }
/*     */     
/*     */     public void send(ActorRef target, Object msg) {
/*     */       target.tell(msg, receiver());
/*     */     }
/*     */     
/*     */     private FiniteDuration defaultTimeout() {
/* 175 */       return this.defaultTimeout;
/*     */     }
/*     */     
/*     */     public FiniteDuration receive$default$1() {
/* 187 */       return defaultTimeout();
/*     */     }
/*     */     
/*     */     public Object receive(FiniteDuration timeout) {
/* 188 */       Timeout t = new Timeout(timeout.$plus(akka$actor$dsl$Inbox$Inbox$$$outer().akka$actor$dsl$Inbox$$extraTime()));
/* 189 */       return scala.concurrent.Await$.MODULE$.result((Awaitable)akka.pattern.AskableActorRef$.MODULE$.$qmark$extension(akka.pattern.package$.MODULE$.ask(receiver()), new Inbox.Get(scala.concurrent.duration.Deadline$.MODULE$.now().$plus(timeout), Inbox.Get$.MODULE$.apply$default$2()), t), (Duration)scala.concurrent.duration.Duration$.MODULE$.Inf());
/*     */     }
/*     */     
/*     */     public <T> FiniteDuration select$default$1() {
/* 204 */       return defaultTimeout();
/*     */     }
/*     */     
/*     */     public <T> T select(FiniteDuration timeout, PartialFunction<Object, Object> predicate) {
/* 205 */       Timeout t = new Timeout(timeout.$plus(akka$actor$dsl$Inbox$Inbox$$$outer().akka$actor$dsl$Inbox$$extraTime()));
/* 206 */       return (T)predicate.apply(scala.concurrent.Await$.MODULE$.result((Awaitable)akka.pattern.AskableActorRef$.MODULE$.$qmark$extension(akka.pattern.package$.MODULE$.ask(receiver()), new Inbox.Select(scala.concurrent.duration.Deadline$.MODULE$.now().$plus(timeout), predicate, Inbox.Select$.MODULE$.apply$default$3()), t), (Duration)scala.concurrent.duration.Duration$.MODULE$.Inf()));
/*     */     }
/*     */     
/*     */     public void watch(ActorRef target) {
/* 213 */       ScalaActorRef qual$1 = akka.actor.package$.MODULE$.actorRef2Scala(receiver());
/* 213 */       Inbox.StartWatch x$7 = new Inbox.StartWatch(target);
/* 213 */       ActorRef x$8 = qual$1.$bang$default$2(x$7);
/* 213 */       qual$1.$bang(x$7, x$8);
/*     */     }
/*     */     
/*     */     public void finalize() {
/* 220 */       this.system.stop(receiver());
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface Query {
/*     */     Deadline deadline();
/*     */     
/*     */     Query withClient(ActorRef param1ActorRef);
/*     */     
/*     */     ActorRef client();
/*     */   }
/*     */   
/*     */   public interface InboxExtension {
/*     */     void akka$actor$dsl$Inbox$InboxExtension$_setter_$DSLInboxQueueSize_$eq(int param1Int);
/*     */     
/*     */     void akka$actor$dsl$Inbox$InboxExtension$_setter_$inboxNr_$eq(AtomicInteger param1AtomicInteger);
/*     */     
/*     */     void akka$actor$dsl$Inbox$InboxExtension$_setter_$inboxProps_$eq(Props param1Props);
/*     */     
/*     */     int DSLInboxQueueSize();
/*     */     
/*     */     AtomicInteger inboxNr();
/*     */     
/*     */     Props inboxProps();
/*     */     
/*     */     ActorRef newReceiver();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dsl\Inbox.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */