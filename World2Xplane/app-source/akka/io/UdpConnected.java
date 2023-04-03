/*     */ package akka.io;
/*     */ 
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ActorSystem;
/*     */ import akka.actor.ExtendedActorSystem;
/*     */ import akka.actor.Extension;
/*     */ import akka.util.ByteString;
/*     */ import java.net.InetSocketAddress;
/*     */ import scala.Function0;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple4;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.Traversable;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction4;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021=v!B\001\003\021\0039\021\001D+ea\016{gN\\3di\026$'BA\002\005\003\tIwNC\001\006\003\021\t7n[1\004\001A\021\001\"C\007\002\005\031)!B\001E\001\027\taQ\013\0329D_:tWm\031;fIN!\021\002\004\n\034!\ti\001#D\001\017\025\005y\021!B:dC2\f\027BA\t\017\005\031\te.\037*fMB\0311C\006\r\016\003QQ!!\006\003\002\013\005\034Go\034:\n\005]!\"aC#yi\026t7/[8o\023\022\004\"\001C\r\n\005i\021!aD+ea\016{gN\\3di\026$W\t\037;\021\005Ma\022BA\017\025\005M)\005\020^3og&|g.\0233Qe>4\030\016Z3s\021\025y\022\002\"\001!\003\031a\024N\\5u}Q\tq\001C\003#\023\021\0053%\001\004m_>\\W\017\035\013\002I9\021\001\002\001\005\006M%!\teJ\001\020GJ,\027\r^3FqR,gn]5p]R\021\001\004\013\005\006S\025\002\rAK\001\007gf\034H/Z7\021\005MY\023B\001\027\025\005M)\005\020^3oI\026$\027i\031;peNK8\017^3n\021\025q\023\002\"\0210\003\r9W\r\036\013\0031ABQ!K\027A\002E\002\"a\005\032\n\005M\"\"aC!di>\0248+_:uK64q!N\005\021\002G\005bGA\004NKN\034\030mZ3\024\005Qb\021f\001\0339%\0329\021(\003I\001\004\003Q$aB\"p[6\fg\016Z\n\005q1Y$\t\005\002=9\021\001\"P\005\003}\t\t\001cU3mK\016$\030n\0348IC:$G.\032:\n\005\001\013%!\005%bg\032\013\027\016\\;sK6+7o]1hK*\021aH\001\t\003\007Rj\021!\003\005\006\013b\"\tAR\001\007I%t\027\016\036\023\025\003\035\003\"!\004%\n\005%s!\001B+oSRDQa\023\035\005\0021\013aBZ1jYV\024X-T3tg\006<W-F\001N!\t\031eJ\002\003P\023\001\003&!D\"p[6\fg\016\032$bS2,GmE\003O\031E+\006\f\005\002D%\03291+\003I\001$\003!&!B#wK:$8c\001*\r\005B\021QBV\005\003/:\021q\001\025:pIV\034G\017\005\002\0163&\021!L\004\002\r'\026\024\030.\0317ju\006\024G.\032\005\t9:\023)\032!C\001;\006\0311-\0343\026\003y\003\"a\021\035\t\021\001t%\021#Q\001\ny\013AaY7eA!)qD\024C\001ER\021Qj\031\005\0069\006\004\rA\030\005\bK:\013\t\021\"\001g\003\021\031w\016]=\025\0055;\007b\002/e!\003\005\rA\030\005\bS:\013\n\021\"\001k\0039\031w\016]=%I\0264\027-\0367uIE*\022a\033\026\003=2\\\023!\034\t\003]Nl\021a\034\006\003aF\f\021\"\0368dQ\026\0347.\0323\013\005It\021AC1o]>$\030\r^5p]&\021Ao\034\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007b\002<O\003\003%\te^\001\016aJ|G-^2u!J,g-\033=\026\003a\004\"!\037@\016\003iT!a\037?\002\t1\fgn\032\006\002{\006!!.\031<b\023\ty(P\001\004TiJLgn\032\005\n\003\007q\025\021!C\001\003\013\tA\002\035:pIV\034G/\021:jif,\"!a\002\021\0075\tI!C\002\002\f9\0211!\0238u\021%\tyATA\001\n\003\t\t\"\001\bqe>$Wo\031;FY\026lWM\034;\025\t\005M\021\021\004\t\004\033\005U\021bAA\f\035\t\031\021I\\=\t\025\005m\021QBA\001\002\004\t9!A\002yIEB\021\"a\bO\003\003%\t%!\t\002\037A\024x\016Z;di&#XM]1u_J,\"!a\t\021\r\005\025\0221FA\n\033\t\t9CC\002\002*9\t!bY8mY\026\034G/[8o\023\021\ti#a\n\003\021%#XM]1u_JD\021\"!\rO\003\003%\t!a\r\002\021\r\fg.R9vC2$B!!\016\002<A\031Q\"a\016\n\007\005ebBA\004C_>dW-\0318\t\025\005m\021qFA\001\002\004\t\031\002C\005\002@9\013\t\021\"\021\002B\005A\001.Y:i\007>$W\r\006\002\002\b!I\021Q\t(\002\002\023\005\023qI\001\ti>\034FO]5oOR\t\001\020C\005\002L9\013\t\021\"\021\002N\0051Q-];bYN$B!!\016\002P!Q\0211DA%\003\003\005\r!a\005\007\r\005M\023\002QA+\005\025qu.Q2l'\031\t\t\006D)V1\"Y\021\021LA)\005+\007I\021AA.\003\025!xn[3o+\t\t\031\002C\006\002`\005E#\021#Q\001\n\005M\021A\002;pW\026t\007\005C\004 \003#\"\t!a\031\025\t\005\025\024q\r\t\004\007\006E\003\002CA-\003C\002\r!a\005\t\023\025\f\t&!A\005\002\005-D\003BA3\003[B!\"!\027\002jA\005\t\031AA\n\021%I\027\021KI\001\n\003\t\t(\006\002\002t)\032\0211\0037\t\021Y\f\t&!A\005B]D!\"a\001\002R\005\005I\021AA\003\021)\ty!!\025\002\002\023\005\0211\020\013\005\003'\ti\b\003\006\002\034\005e\024\021!a\001\003\017A!\"a\b\002R\005\005I\021IA\021\021)\t\t$!\025\002\002\023\005\0211\021\013\005\003k\t)\t\003\006\002\034\005\005\025\021!a\001\003'A!\"a\020\002R\005\005I\021IA!\021)\t)%!\025\002\002\023\005\023q\t\005\013\003\027\n\t&!A\005B\0055E\003BA\033\003\037C!\"a\007\002\f\006\005\t\031AA\n\017\035\t\031*\003E\001\003+\013QAT8BG.\0042aQAL\r\035\t\031&\003E\001\0033\033B!a&\002f!9q$a&\005\002\005uECAAK\021)\t\t+a&\002\002\023\005\0251U\001\006CB\004H.\037\013\005\003K\n)\013\003\005\002Z\005}\005\031AA\n\021)\tI+a&\002\002\023\005\0251V\001\bk:\f\007\017\0357z)\021\ti+a-\021\0135\ty+a\005\n\007\005EfB\001\004PaRLwN\034\005\013\003k\0139+!AA\002\005\025\024a\001=%a!Q\021\021XAL\003\003%I!a/\002\027I,\027\r\032*fg>dg/\032\013\003\003{\0032!_A`\023\r\t\tM\037\002\007\037\nTWm\031;\007\r\005\025\027\002QAd\005\021\031VM\0343\024\r\005\rGBX+Y\021-\tY-a1\003\026\004%\t!!4\002\017A\f\027\020\\8bIV\021\021q\032\t\005\003#\f9.\004\002\002T*\031\021Q\033\003\002\tU$\030\016\\\005\005\0033\f\031N\001\006CsR,7\013\036:j]\036D1\"!8\002D\nE\t\025!\003\002P\006A\001/Y=m_\006$\007\005C\006\002b\006\r'Q3A\005\002\005m\023aA1dW\"Y\021Q]Ab\005#\005\013\021BA\n\003\021\t7m\033\021\t\017}\t\031\r\"\001\002jR1\0211^Aw\003_\0042aQAb\021!\tY-a:A\002\005=\007\002CAq\003O\004\r!a\005\t\021\005M\0301\031C\001\003k\f\001b^1oiN\f5m[\013\003\003kA\021\"ZAb\003\003%\t!!?\025\r\005-\0301`A\021)\tY-a>\021\002\003\007\021q\032\005\013\003C\f9\020%AA\002\005M\001\"C5\002DF\005I\021\001B\001+\t\021\031AK\002\002P2D!Ba\002\002DF\005I\021AA9\0039\031w\016]=%I\0264\027-\0367uIIB\001B^Ab\003\003%\te\036\005\013\003\007\t\031-!A\005\002\005\025\001BCA\b\003\007\f\t\021\"\001\003\020Q!\0211\003B\t\021)\tYB!\004\002\002\003\007\021q\001\005\013\003?\t\031-!A\005B\005\005\002BCA\031\003\007\f\t\021\"\001\003\030Q!\021Q\007B\r\021)\tYB!\006\002\002\003\007\0211\003\005\013\003\t\031-!A\005B\005\005\003BCA#\003\007\f\t\021\"\021\002H!Q\0211JAb\003\003%\tE!\t\025\t\005U\"1\005\005\013\0037\021y\"!AA\002\005Mqa\002B\024\023!\005!\021F\001\005'\026tG\rE\002D\005W1q!!2\n\021\003\021ic\005\003\003,1A\006bB\020\003,\021\005!\021\007\013\003\005SA\001\"!)\003,\021\005!Q\007\013\005\003W\0249\004\003\005\003:\tM\002\031AAh\003\021!\027\r^1\t\025\005\005&1FA\001\n\003\023i\004\006\004\002l\n}\"\021\t\005\t\003\027\024Y\0041\001\002P\"A\021\021\035B\036\001\004\t\031\002\003\006\002*\n-\022\021!CA\005\013\"BAa\022\003PA)Q\"a,\003JA9QBa\023\002P\006M\021b\001B'\035\t1A+\0369mKJB!\"!.\003D\005\005\t\031AAv\021)\tILa\013\002\002\023%\0211\030\004\007\005+J\001Ia\026\003\017\r{gN\\3diN1!1\013\007_+bC1Ba\027\003T\tU\r\021\"\001\003^\0059\001.\0318eY\026\024XC\001B0!\r\031\"\021M\005\004\005G\"\"\001C!di>\024(+\0324\t\027\t\035$1\013B\tB\003%!qL\001\tQ\006tG\r\\3sA!Y!1\016B*\005+\007I\021\001B7\0035\021X-\\8uK\006#GM]3tgV\021!q\016\t\005\005c\0229(\004\002\003t)\031!Q\017?\002\0079,G/\003\003\003z\tM$!E%oKR\034vnY6fi\006#GM]3tg\"Y!Q\020B*\005#\005\013\021\002B8\0039\021X-\\8uK\006#GM]3tg\002B1B!!\003T\tU\r\021\"\001\003\004\006aAn\\2bY\006#GM]3tgV\021!Q\021\t\006\033\005=&q\016\005\f\005\023\023\031F!E!\002\023\021))A\007m_\016\fG.\0213ee\026\0348\017\t\005\f\005\033\023\031F!f\001\n\003\021y)A\004paRLwN\\:\026\005\tE\005C\002BJ\0053\023i*\004\002\003\026*!!qSA\024\003%IW.\\;uC\ndW-\003\003\003\034\nU%a\003+sCZ,'o]1cY\026\004BAa(\0034:!!\021\025BX\035\021\021\031K!,\017\t\t\025&1V\007\003\005OS1A!+\007\003\031a$o\\8u}%\tQ!\003\002\004\t%\031!\021\027\002\002\t%sW\r^\005\005\005k\0239L\001\007T_\016\\W\r^(qi&|gNC\002\0032\nA1Ba/\003T\tE\t\025!\003\003\022\006Aq\016\035;j_:\034\b\005C\004 \005'\"\tAa0\025\025\t\005'1\031Bc\005\017\024I\rE\002D\005'B\001Ba\027\003>\002\007!q\f\005\t\005W\022i\f1\001\003p!Q!\021\021B_!\003\005\rA!\"\t\025\t5%Q\030I\001\002\004\021\t\nC\005f\005'\n\t\021\"\001\003NRQ!\021\031Bh\005#\024\031N!6\t\025\tm#1\032I\001\002\004\021y\006\003\006\003l\t-\007\023!a\001\005_B!B!!\003LB\005\t\031\001BC\021)\021iIa3\021\002\003\007!\021\023\005\nS\nM\023\023!C\001\0053,\"Aa7+\007\t}C\016\003\006\003\b\tM\023\023!C\001\005?,\"A!9+\007\t=D\016\003\006\003f\nM\023\023!C\001\005O\fabY8qs\022\"WMZ1vYR$3'\006\002\003j*\032!Q\0217\t\025\t5(1KI\001\n\003\021y/\001\bd_BLH\005Z3gCVdG\017\n\033\026\005\tE(f\001BIY\"AaOa\025\002\002\023\005s\017\003\006\002\004\tM\023\021!C\001\003\013A!\"a\004\003T\005\005I\021\001B})\021\t\031Ba?\t\025\005m!q_A\001\002\004\t9\001\003\006\002 \tM\023\021!C!\003CA!\"!\r\003T\005\005I\021AB\001)\021\t)da\001\t\025\005m!q`A\001\002\004\t\031\002\003\006\002@\tM\023\021!C!\003\003B!\"!\022\003T\005\005I\021IA$\021)\tYEa\025\002\002\023\00531\002\013\005\003k\031i\001\003\006\002\034\r%\021\021!a\001\003'9\021b!\005\n\003\003E\taa\005\002\017\r{gN\\3diB\0311i!\006\007\023\tU\023\"!A\t\002\r]1#BB\013\0073A\006CDB\016\007C\021yFa\034\003\006\nE%\021Y\007\003\007;Q1aa\b\017\003\035\021XO\034;j[\026LAaa\t\004\036\t\t\022IY:ue\006\034GOR;oGRLwN\034\033\t\017}\031)\002\"\001\004(Q\02111\003\005\013\003\013\032)\"!A\005F\005\035\003BCAQ\007+\t\t\021\"!\004.QQ!\021YB\030\007c\031\031d!\016\t\021\tm31\006a\001\005?B\001Ba\033\004,\001\007!q\016\005\013\005\003\033Y\003%AA\002\t\025\005B\003BG\007W\001\n\0211\001\003\022\"Q\021\021VB\013\003\003%\ti!\017\025\t\rm21\t\t\006\033\005=6Q\b\t\f\033\r}\"q\fB8\005\013\023\t*C\002\004B9\021a\001V;qY\026$\004BCA[\007o\t\t\0211\001\003B\"Q1qIB\013#\003%\tAa:\0027\021bWm]:j]&$He\032:fCR,'\017\n3fM\006,H\016\036\0234\021)\031Ye!\006\022\002\023\005!q^\001\034I1,7o]5oSR$sM]3bi\026\024H\005Z3gCVdG\017\n\033\t\025\r=3QCI\001\n\003\0219/A\bbaBd\027\020\n3fM\006,H\016\036\0234\021)\031\031f!\006\022\002\023\005!q^\001\020CB\004H.\037\023eK\032\fW\017\034;%i!Q\021\021XB\013\003\003%I!a/\b\017\re\023\002#!\004\\\005QA)[:d_:tWm\031;\021\007\r\033iFB\004\004`%A\ti!\031\003\025\021K7oY8o]\026\034Go\005\004\004^1qV\013\027\005\b?\ruC\021AB3)\t\031Y\006\003\005w\007;\n\t\021\"\021x\021)\t\031a!\030\002\002\023\005\021Q\001\005\013\003\037\031i&!A\005\002\r5D\003BA\n\007_B!\"a\007\004l\005\005\t\031AA\004\021)\tyb!\030\002\002\023\005\023\021\005\005\013\003c\031i&!A\005\002\rUD\003BA\033\007oB!\"a\007\004t\005\005\t\031AA\n\021)\tyd!\030\002\002\023\005\023\021\t\005\013\003\013\032i&!A\005B\005\035\003BCA]\007;\n\t\021\"\003\002<\03691\021Q\005\t\002\016\r\025AD*vgB,g\016\032*fC\022Lgn\032\t\004\007\016\025eaBBD\023!\0055\021\022\002\017'V\034\b/\0328e%\026\fG-\0338h'\031\031)\t\0040V1\"9qd!\"\005\002\r5ECABB\021!18QQA\001\n\003:\bBCA\002\007\013\013\t\021\"\001\002\006!Q\021qBBC\003\003%\ta!&\025\t\005M1q\023\005\013\0037\031\031*!AA\002\005\035\001BCA\020\007\013\013\t\021\"\021\002\"!Q\021\021GBC\003\003%\ta!(\025\t\005U2q\024\005\013\0037\031Y*!AA\002\005M\001BCA \007\013\013\t\021\"\021\002B!Q\021QIBC\003\003%\t%a\022\t\025\005e6QQA\001\n\023\tYlB\004\004*&A\tia+\002\033I+7/^7f%\026\fG-\0338h!\r\0315Q\026\004\b\007_K\001\022QBY\0055\021Vm];nKJ+\027\rZ5oON11Q\026\007_+bCqaHBW\t\003\031)\f\006\002\004,\"Aao!,\002\002\023\005s\017\003\006\002\004\r5\026\021!C\001\003\013A!\"a\004\004.\006\005I\021AB_)\021\t\031ba0\t\025\005m11XA\001\002\004\t9\001\003\006\002 \r5\026\021!C!\003CA!\"!\r\004.\006\005I\021ABc)\021\t)da2\t\025\005m11YA\001\002\004\t\031\002\003\006\002@\r5\026\021!C!\003\003B!\"!\022\004.\006\005I\021IA$\021)\tIl!,\002\002\023%\0211\030\004\007\007#L\001ia5\003\021I+7-Z5wK\022\034baa4\r#VC\006b\003B\035\007\037\024)\032!C\001\003\033D1b!7\004P\nE\t\025!\003\002P\006)A-\031;bA!9qda4\005\002\ruG\003BBp\007C\0042aQBh\021!\021Ida7A\002\005=\007\"C3\004P\006\005I\021ABs)\021\031yna:\t\025\te21\035I\001\002\004\ty\rC\005j\007\037\f\n\021\"\001\003\002!Aaoa4\002\002\023\005s\017\003\006\002\004\r=\027\021!C\001\003\013A!\"a\004\004P\006\005I\021ABy)\021\t\031ba=\t\025\005m1q^A\001\002\004\t9\001\003\006\002 \r=\027\021!C!\003CA!\"!\r\004P\006\005I\021AB})\021\t)da?\t\025\005m1q_A\001\002\004\t\031\002\003\006\002@\r=\027\021!C!\003\003B!\"!\022\004P\006\005I\021IA$\021)\tYea4\002\002\023\005C1\001\013\005\003k!)\001\003\006\002\034\021\005\021\021!a\001\003'9\021\002\"\003\n\003\003E\t\001b\003\002\021I+7-Z5wK\022\0042a\021C\007\r%\031\t.CA\001\022\003!yaE\003\005\016\021E\001\f\005\005\004\034\021M\021qZBp\023\021!)b!\b\003#\005\0237\017\036:bGR4UO\\2uS>t\027\007C\004 \t\033!\t\001\"\007\025\005\021-\001BCA#\t\033\t\t\021\"\022\002H!Q\021\021\025C\007\003\003%\t\tb\b\025\t\r}G\021\005\005\t\005s!i\0021\001\002P\"Q\021\021\026C\007\003\003%\t\t\"\n\025\t\021\035B\021\006\t\006\033\005=\026q\032\005\013\003k#\031#!AA\002\r}\007BCA]\t\033\t\t\021\"\003\002<\036IAqF\005\002\002#\005A\021G\001\016\007>lW.\0318e\r\006LG.\0323\021\007\r#\031D\002\005P\023\005\005\t\022\001C\033'\025!\031\004b\016Y!\031\031Y\002b\005_\033\"9q\004b\r\005\002\021mBC\001C\031\021)\t)\005b\r\002\002\023\025\023q\t\005\013\003C#\031$!A\005\002\022\005CcA'\005D!1A\fb\020A\002yC!\"!+\0054\005\005I\021\021C$)\021!I\005b\023\021\t5\tyK\030\005\n\003k#)%!AA\0025C!\"!/\0054\005\005I\021BA^\r%!\t&\003I\001$C!\031FA\005D_:tWm\031;fIN!Aq\n\007RS\021!y\005b\026\007\017\021E\023\002#!\005ZM9Aq\013\007\005\\UC\006cA\"\005P!9q\004b\026\005\002\021}CC\001C1!\r\031Eq\013\005\tm\022]\023\021!C!o\"Q\0211\001C,\003\003%\t!!\002\t\025\005=AqKA\001\n\003!I\007\006\003\002\024\021-\004BCA\016\tO\n\t\0211\001\002\b!Q\021q\004C,\003\003%\t%!\t\t\025\005EBqKA\001\n\003!\t\b\006\003\0026\021M\004BCA\016\t_\n\t\0211\001\002\024!Q\021q\bC,\003\003%\t%!\021\t\025\005\025CqKA\001\n\003\n9\005\003\006\002:\022]\023\021!C\005\003w;q\001\" \n\021\003#\t'A\005D_:tWm\031;fI\032IA\021Q\005\021\002G\005B1\021\002\r\t&\0348m\0348oK\016$X\rZ\n\005\tb\021+\013\003\005\000\021\035ea\002CA\023!\005E\021R\n\b\t\017cA1R+Y!\r\031Eq\020\005\b?\021\035E\021\001CH)\t!\t\nE\002D\t\017C\001B\036CD\003\003%\te\036\005\013\003\007!9)!A\005\002\005\025\001BCA\b\t\017\013\t\021\"\001\005\032R!\0211\003CN\021)\tY\002b&\002\002\003\007\021q\001\005\013\003?!9)!A\005B\005\005\002BCA\031\t\017\013\t\021\"\001\005\"R!\021Q\007CR\021)\tY\002b(\002\002\003\007\0211\003\005\013\003!9)!A\005B\005\005\003BCA#\t\017\013\t\021\"\021\002H!Q\021\021\030CD\003\003%I!a/\b\017\0215\026\002#!\005\022\006aA)[:d_:tWm\031;fI\002")
/*     */ public final class UdpConnected {
/*     */   public static boolean equals(Object paramObject) {
/*     */     return UdpConnected$.MODULE$.equals(paramObject);
/*     */   }
/*     */   
/*     */   public static int hashCode() {
/*     */     return UdpConnected$.MODULE$.hashCode();
/*     */   }
/*     */   
/*     */   public static Extension apply(ActorSystem paramActorSystem) {
/*     */     return UdpConnected$.MODULE$.apply(paramActorSystem);
/*     */   }
/*     */   
/*     */   public static UdpConnectedExt get(ActorSystem paramActorSystem) {
/*     */     return UdpConnected$.MODULE$.get(paramActorSystem);
/*     */   }
/*     */   
/*     */   public static UdpConnectedExt createExtension(ExtendedActorSystem paramExtendedActorSystem) {
/*     */     return UdpConnected$.MODULE$.createExtension(paramExtendedActorSystem);
/*     */   }
/*     */   
/*     */   public static UdpConnected$ lookup() {
/*     */     return UdpConnected$.MODULE$.lookup();
/*     */   }
/*     */   
/*     */   public static abstract class Command$class {
/*     */     public static void $init$(UdpConnected.Command $this) {}
/*     */     
/*     */     public static UdpConnected.CommandFailed failureMessage(UdpConnected.Command $this) {
/*  46 */       return new UdpConnected.CommandFailed($this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class NoAck implements Event, Product, Serializable {
/*     */     private final Object token;
/*     */     
/*     */     public Object token() {
/*  55 */       return this.token;
/*     */     }
/*     */     
/*     */     public NoAck copy(Object token) {
/*  55 */       return new NoAck(token);
/*     */     }
/*     */     
/*     */     public Object copy$default$1() {
/*  55 */       return token();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  55 */       return "NoAck";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  55 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  55 */       int i = x$1;
/*  55 */       switch (i) {
/*     */         default:
/*  55 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  55 */       return token();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  55 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  55 */       return x$1 instanceof NoAck;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  55 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  55 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*  55 */       if (this != x$1) {
/*     */         boolean bool;
/*  55 */         Object object = x$1;
/*  55 */         if (object instanceof NoAck) {
/*  63 */           bool = true;
/*     */         } else {
/*  63 */           bool = false;
/*     */         } 
/*     */         if (bool) {
/*     */           NoAck noAck = (NoAck)x$1;
/*     */           if ((BoxesRunTime.equals(token(), noAck.token()) && noAck.canEqual(this)));
/*     */         } 
/*     */         return false;
/*     */       } 
/*     */     }
/*     */     
/*     */     public NoAck(Object token) {
/*     */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class NoAck$ extends NoAck {
/*     */     public static final NoAck$ MODULE$;
/*     */     
/*     */     public UdpConnected.NoAck apply(Object token) {
/*     */       return new UdpConnected.NoAck(token);
/*     */     }
/*     */     
/*     */     public Option<Object> unapply(UdpConnected.NoAck x$0) {
/*     */       return (x$0 == null) ? (Option<Object>)scala.None$.MODULE$ : (Option<Object>)new Some(x$0.token());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return MODULE$;
/*     */     }
/*     */     
/*     */     public NoAck$() {
/*     */       super(null);
/*     */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Send implements Command, Product, Serializable {
/*     */     private final ByteString payload;
/*     */     
/*     */     private final Object ack;
/*     */     
/*     */     public UdpConnected.CommandFailed failureMessage() {
/*  71 */       return UdpConnected.Command$class.failureMessage(this);
/*     */     }
/*     */     
/*     */     public ByteString payload() {
/*  71 */       return this.payload;
/*     */     }
/*     */     
/*     */     public Object ack() {
/*  71 */       return this.ack;
/*     */     }
/*     */     
/*     */     public Send copy(ByteString payload, Object ack) {
/*  71 */       return new Send(payload, ack);
/*     */     }
/*     */     
/*     */     public ByteString copy$default$1() {
/*  71 */       return payload();
/*     */     }
/*     */     
/*     */     public Object copy$default$2() {
/*  71 */       return ack();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  71 */       return "Send";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  71 */       return 2;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  71 */       int i = x$1;
/*  71 */       switch (i) {
/*     */         default:
/*  71 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 1:
/*     */         
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  71 */       return payload();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  71 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  71 */       return x$1 instanceof Send;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  71 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  71 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 95
/*     */       //   5: aload_1
/*     */       //   6: astore_2
/*     */       //   7: aload_2
/*     */       //   8: instanceof akka/io/UdpConnected$Send
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 99
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/io/UdpConnected$Send
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual payload : ()Lakka/util/ByteString;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual payload : ()Lakka/util/ByteString;
/*     */       //   40: astore #5
/*     */       //   42: dup
/*     */       //   43: ifnonnull -> 55
/*     */       //   46: pop
/*     */       //   47: aload #5
/*     */       //   49: ifnull -> 63
/*     */       //   52: goto -> 91
/*     */       //   55: aload #5
/*     */       //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   60: ifeq -> 91
/*     */       //   63: aload_0
/*     */       //   64: invokevirtual ack : ()Ljava/lang/Object;
/*     */       //   67: aload #4
/*     */       //   69: invokevirtual ack : ()Ljava/lang/Object;
/*     */       //   72: invokestatic equals : (Ljava/lang/Object;Ljava/lang/Object;)Z
/*     */       //   75: ifeq -> 91
/*     */       //   78: aload #4
/*     */       //   80: aload_0
/*     */       //   81: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   84: ifeq -> 91
/*     */       //   87: iconst_1
/*     */       //   88: goto -> 92
/*     */       //   91: iconst_0
/*     */       //   92: ifeq -> 99
/*     */       //   95: iconst_1
/*     */       //   96: goto -> 100
/*     */       //   99: iconst_0
/*     */       //   100: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #71	-> 0
/*     */       //   #63	-> 14
/*     */       //   #71	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	101	0	this	Lakka/io/UdpConnected$Send;
/*     */       //   0	101	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public Send(ByteString payload, Object ack) {
/*  71 */       UdpConnected.Command$class.$init$(this);
/*  71 */       Product.class.$init$(this);
/*  72 */       scala.Predef$.MODULE$.require(!(ack == null), 
/*  73 */           (Function0)new UdpConnected$Send$$anonfun$1(this));
/*     */     }
/*     */     
/*     */     public class UdpConnected$Send$$anonfun$1 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply() {
/*  73 */         return "ack must be non-null. Use NoAck if you don't want acks.";
/*     */       }
/*     */       
/*     */       public UdpConnected$Send$$anonfun$1(UdpConnected.Send $outer) {}
/*     */     }
/*     */     
/*     */     public boolean wantsAck() {
/*  75 */       return !(ack() instanceof UdpConnected.NoAck);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Send$ implements Serializable {
/*     */     public static final Send$ MODULE$;
/*     */     
/*     */     public UdpConnected.Send apply(ByteString payload, Object ack) {
/*     */       return new UdpConnected.Send(payload, ack);
/*     */     }
/*     */     
/*     */     public Option<Tuple2<ByteString, Object>> unapply(UdpConnected.Send x$0) {
/*     */       return (x$0 == null) ? (Option<Tuple2<ByteString, Object>>)scala.None$.MODULE$ : (Option<Tuple2<ByteString, Object>>)new Some(new Tuple2(x$0.payload(), x$0.ack()));
/*     */     }
/*     */     
/*     */     public class UdpConnected$Send$$anonfun$1 extends AbstractFunction0<String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply() {
/*     */         return "ack must be non-null. Use NoAck if you don't want acks.";
/*     */       }
/*     */       
/*     */       public UdpConnected$Send$$anonfun$1(UdpConnected.Send $outer) {}
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  77 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Send$() {
/*  77 */       MODULE$ = this;
/*     */     }
/*     */     
/*     */     public UdpConnected.Send apply(ByteString data) {
/*  78 */       return new UdpConnected.Send(data, UdpConnected.NoAck$.MODULE$);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Connect implements Command, Product, Serializable {
/*     */     private final ActorRef handler;
/*     */     
/*     */     private final InetSocketAddress remoteAddress;
/*     */     
/*     */     private final Option<InetSocketAddress> localAddress;
/*     */     
/*     */     private final Traversable<Inet.SocketOption> options;
/*     */     
/*     */     public UdpConnected.CommandFailed failureMessage() {
/*  87 */       return UdpConnected.Command$class.failureMessage(this);
/*     */     }
/*     */     
/*     */     public ActorRef handler() {
/*  87 */       return this.handler;
/*     */     }
/*     */     
/*     */     public Connect copy(ActorRef handler, InetSocketAddress remoteAddress, Option<InetSocketAddress> localAddress, Traversable<Inet.SocketOption> options) {
/*  87 */       return new Connect(handler, 
/*  88 */           remoteAddress, 
/*  89 */           localAddress, 
/*  90 */           options);
/*     */     }
/*     */     
/*     */     public ActorRef copy$default$1() {
/*     */       return handler();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*     */       return "Connect";
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
/*     */       return handler();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*     */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*     */       return x$1 instanceof Connect;
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
/*     */       //   2: if_acmpeq -> 176
/*     */       //   5: aload_1
/*     */       //   6: astore_2
/*     */       //   7: aload_2
/*     */       //   8: instanceof akka/io/UdpConnected$Connect
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 180
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/io/UdpConnected$Connect
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual handler : ()Lakka/actor/ActorRef;
/*     */       //   40: astore #5
/*     */       //   42: dup
/*     */       //   43: ifnonnull -> 55
/*     */       //   46: pop
/*     */       //   47: aload #5
/*     */       //   49: ifnull -> 63
/*     */       //   52: goto -> 172
/*     */       //   55: aload #5
/*     */       //   57: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   60: ifeq -> 172
/*     */       //   63: aload_0
/*     */       //   64: invokevirtual remoteAddress : ()Ljava/net/InetSocketAddress;
/*     */       //   67: aload #4
/*     */       //   69: invokevirtual remoteAddress : ()Ljava/net/InetSocketAddress;
/*     */       //   72: astore #6
/*     */       //   74: dup
/*     */       //   75: ifnonnull -> 87
/*     */       //   78: pop
/*     */       //   79: aload #6
/*     */       //   81: ifnull -> 95
/*     */       //   84: goto -> 172
/*     */       //   87: aload #6
/*     */       //   89: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   92: ifeq -> 172
/*     */       //   95: aload_0
/*     */       //   96: invokevirtual localAddress : ()Lscala/Option;
/*     */       //   99: aload #4
/*     */       //   101: invokevirtual localAddress : ()Lscala/Option;
/*     */       //   104: astore #7
/*     */       //   106: dup
/*     */       //   107: ifnonnull -> 119
/*     */       //   110: pop
/*     */       //   111: aload #7
/*     */       //   113: ifnull -> 127
/*     */       //   116: goto -> 172
/*     */       //   119: aload #7
/*     */       //   121: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   124: ifeq -> 172
/*     */       //   127: aload_0
/*     */       //   128: invokevirtual options : ()Lscala/collection/immutable/Traversable;
/*     */       //   131: aload #4
/*     */       //   133: invokevirtual options : ()Lscala/collection/immutable/Traversable;
/*     */       //   136: astore #8
/*     */       //   138: dup
/*     */       //   139: ifnonnull -> 151
/*     */       //   142: pop
/*     */       //   143: aload #8
/*     */       //   145: ifnull -> 159
/*     */       //   148: goto -> 172
/*     */       //   151: aload #8
/*     */       //   153: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   156: ifeq -> 172
/*     */       //   159: aload #4
/*     */       //   161: aload_0
/*     */       //   162: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   165: ifeq -> 172
/*     */       //   168: iconst_1
/*     */       //   169: goto -> 173
/*     */       //   172: iconst_0
/*     */       //   173: ifeq -> 180
/*     */       //   176: iconst_1
/*     */       //   177: goto -> 181
/*     */       //   180: iconst_0
/*     */       //   181: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #87	-> 0
/*     */       //   #63	-> 14
/*     */       //   #87	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	182	0	this	Lakka/io/UdpConnected$Connect;
/*     */       //   0	182	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public Connect(ActorRef handler, InetSocketAddress remoteAddress, Option<InetSocketAddress> localAddress, Traversable<Inet.SocketOption> options) {
/*     */       UdpConnected.Command$class.$init$(this);
/*     */       Product.class.$init$(this);
/*     */     }
/*     */     
/*     */     public InetSocketAddress remoteAddress() {
/*     */       return this.remoteAddress;
/*     */     }
/*     */     
/*     */     public InetSocketAddress copy$default$2() {
/*     */       return remoteAddress();
/*     */     }
/*     */     
/*     */     public Option<InetSocketAddress> localAddress() {
/*     */       return this.localAddress;
/*     */     }
/*     */     
/*     */     public Option<InetSocketAddress> copy$default$3() {
/*     */       return localAddress();
/*     */     }
/*     */     
/*     */     public Traversable<Inet.SocketOption> options() {
/*  90 */       return this.options;
/*     */     }
/*     */     
/*     */     public Traversable<Inet.SocketOption> copy$default$4() {
/*  90 */       return options();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Connect$ extends AbstractFunction4<ActorRef, InetSocketAddress, Option<InetSocketAddress>, Traversable<Inet.SocketOption>, Connect> implements Serializable {
/*     */     public static final Connect$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*     */       return "Connect";
/*     */     }
/*     */     
/*     */     public UdpConnected.Connect apply(ActorRef handler, InetSocketAddress remoteAddress, Option<InetSocketAddress> localAddress, Traversable<Inet.SocketOption> options) {
/*     */       return new UdpConnected.Connect(handler, remoteAddress, localAddress, options);
/*     */     }
/*     */     
/*     */     public Option<Tuple4<ActorRef, InetSocketAddress, Option<InetSocketAddress>, Traversable<Inet.SocketOption>>> unapply(UdpConnected.Connect x$0) {
/*     */       return (x$0 == null) ? (Option<Tuple4<ActorRef, InetSocketAddress, Option<InetSocketAddress>, Traversable<Inet.SocketOption>>>)scala.None$.MODULE$ : (Option<Tuple4<ActorRef, InetSocketAddress, Option<InetSocketAddress>, Traversable<Inet.SocketOption>>>)new Some(new Tuple4(x$0.handler(), x$0.remoteAddress(), x$0.localAddress(), x$0.options()));
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*     */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Connect$() {
/*     */       MODULE$ = this;
/*     */     }
/*     */     
/*     */     public Option<InetSocketAddress> $lessinit$greater$default$3() {
/*     */       return (Option<InetSocketAddress>)scala.None$.MODULE$;
/*     */     }
/*     */     
/*     */     public Option<InetSocketAddress> apply$default$3() {
/*     */       return (Option<InetSocketAddress>)scala.None$.MODULE$;
/*     */     }
/*     */     
/*     */     public Traversable<Inet.SocketOption> $lessinit$greater$default$4() {
/*  90 */       return (Traversable<Inet.SocketOption>)scala.collection.immutable.Nil$.MODULE$;
/*     */     }
/*     */     
/*     */     public Traversable<Inet.SocketOption> apply$default$4() {
/*  90 */       return (Traversable<Inet.SocketOption>)scala.collection.immutable.Nil$.MODULE$;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Disconnect$ implements Command, Product, Serializable {
/*     */     public static final Disconnect$ MODULE$;
/*     */     
/*     */     public UdpConnected.CommandFailed failureMessage() {
/*  97 */       return UdpConnected.Command$class.failureMessage(this);
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  97 */       return "Disconnect";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  97 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  97 */       int i = x$1;
/*  97 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  97 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  97 */       return x$1 instanceof Disconnect$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  97 */       return -1771096900;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  97 */       return "Disconnect";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  97 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Disconnect$() {
/*  97 */       MODULE$ = this;
/*  97 */       UdpConnected.Command$class.$init$(this);
/*  97 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SuspendReading$ implements Command, Product, Serializable {
/*     */     public static final SuspendReading$ MODULE$;
/*     */     
/*     */     public UdpConnected.CommandFailed failureMessage() {
/* 105 */       return UdpConnected.Command$class.failureMessage(this);
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/* 105 */       return "SuspendReading";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 105 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 105 */       int i = x$1;
/* 105 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 105 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 105 */       return x$1 instanceof SuspendReading$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 105 */       return 597366480;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 105 */       return "SuspendReading";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 105 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public SuspendReading$() {
/* 105 */       MODULE$ = this;
/* 105 */       UdpConnected.Command$class.$init$(this);
/* 105 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ResumeReading$ implements Command, Product, Serializable {
/*     */     public static final ResumeReading$ MODULE$;
/*     */     
/*     */     public UdpConnected.CommandFailed failureMessage() {
/* 111 */       return UdpConnected.Command$class.failureMessage(this);
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/* 111 */       return "ResumeReading";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 111 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 111 */       int i = x$1;
/* 111 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 111 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 111 */       return x$1 instanceof ResumeReading$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 111 */       return -1208766081;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 111 */       return "ResumeReading";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 111 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public ResumeReading$() {
/* 111 */       MODULE$ = this;
/* 111 */       UdpConnected.Command$class.$init$(this);
/* 111 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Received implements Event, Product, Serializable {
/*     */     private final ByteString data;
/*     */     
/*     */     public ByteString data() {
/* 122 */       return this.data;
/*     */     }
/*     */     
/*     */     public Received copy(ByteString data) {
/* 122 */       return new Received(data);
/*     */     }
/*     */     
/*     */     public ByteString copy$default$1() {
/* 122 */       return data();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/* 122 */       return "Received";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 122 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 122 */       int i = x$1;
/* 122 */       switch (i) {
/*     */         default:
/* 122 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/* 122 */       return data();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 122 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 122 */       return x$1 instanceof Received;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 122 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 122 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
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
/*     */       //   8: instanceof akka/io/UdpConnected$Received
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 84
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/io/UdpConnected$Received
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual data : ()Lakka/util/ByteString;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual data : ()Lakka/util/ByteString;
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
/*     */       //   #122	-> 0
/*     */       //   #63	-> 14
/*     */       //   #122	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	86	0	this	Lakka/io/UdpConnected$Received;
/*     */       //   0	86	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public Received(ByteString data) {
/* 122 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Received$ extends AbstractFunction1<ByteString, Received> implements Serializable {
/*     */     public static final Received$ MODULE$;
/*     */     
/*     */     public final String toString() {
/* 122 */       return "Received";
/*     */     }
/*     */     
/*     */     public UdpConnected.Received apply(ByteString data) {
/* 122 */       return new UdpConnected.Received(data);
/*     */     }
/*     */     
/*     */     public Option<ByteString> unapply(UdpConnected.Received x$0) {
/* 122 */       return (x$0 == null) ? (Option<ByteString>)scala.None$.MODULE$ : (Option<ByteString>)new Some(x$0.data());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 122 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Received$() {
/* 122 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class CommandFailed implements Event, Product, Serializable {
/*     */     private final UdpConnected.Command cmd;
/*     */     
/*     */     public UdpConnected.Command cmd() {
/* 128 */       return this.cmd;
/*     */     }
/*     */     
/*     */     public CommandFailed copy(UdpConnected.Command cmd) {
/* 128 */       return new CommandFailed(cmd);
/*     */     }
/*     */     
/*     */     public UdpConnected.Command copy$default$1() {
/* 128 */       return cmd();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/* 128 */       return "CommandFailed";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 128 */       return 1;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 128 */       int i = x$1;
/* 128 */       switch (i) {
/*     */         default:
/* 128 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 0:
/*     */           break;
/*     */       } 
/* 128 */       return cmd();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 128 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 128 */       return x$1 instanceof CommandFailed;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 128 */       return scala.runtime.ScalaRunTime$.MODULE$._hashCode(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 128 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
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
/*     */       //   8: instanceof akka/io/UdpConnected$CommandFailed
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 84
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/io/UdpConnected$CommandFailed
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual cmd : ()Lakka/io/UdpConnected$Command;
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual cmd : ()Lakka/io/UdpConnected$Command;
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
/*     */       //   #128	-> 0
/*     */       //   #63	-> 14
/*     */       //   #128	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	86	0	this	Lakka/io/UdpConnected$CommandFailed;
/*     */       //   0	86	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public CommandFailed(UdpConnected.Command cmd) {
/* 128 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class CommandFailed$ extends AbstractFunction1<Command, CommandFailed> implements Serializable {
/*     */     public static final CommandFailed$ MODULE$;
/*     */     
/*     */     public final String toString() {
/* 128 */       return "CommandFailed";
/*     */     }
/*     */     
/*     */     public UdpConnected.CommandFailed apply(UdpConnected.Command cmd) {
/* 128 */       return new UdpConnected.CommandFailed(cmd);
/*     */     }
/*     */     
/*     */     public Option<UdpConnected.Command> unapply(UdpConnected.CommandFailed x$0) {
/* 128 */       return (x$0 == null) ? (Option<UdpConnected.Command>)scala.None$.MODULE$ : (Option<UdpConnected.Command>)new Some(x$0.cmd());
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 128 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public CommandFailed$() {
/* 128 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Connected$ implements Connected, Product, Serializable {
/*     */     public static final Connected$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/* 136 */       return "Connected";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 136 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 136 */       int i = x$1;
/* 136 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 136 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 136 */       return x$1 instanceof Connected$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 136 */       return 1424757481;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 136 */       return "Connected";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 136 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Connected$() {
/* 136 */       MODULE$ = this;
/* 136 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Disconnected$ implements Disconnected, Product, Serializable {
/*     */     public static final Disconnected$ MODULE$;
/*     */     
/*     */     public String productPrefix() {
/* 143 */       return "Disconnected";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 143 */       return 0;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 143 */       int i = x$1;
/* 143 */       throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 143 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 143 */       return x$1 instanceof Disconnected$;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 143 */       return -1217068453;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 143 */       return "Disconnected";
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 143 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public Disconnected$() {
/* 143 */       MODULE$ = this;
/* 143 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface Event extends Message {}
/*     */   
/*     */   public static interface Message {}
/*     */   
/*     */   public static interface Command extends SelectionHandler.HasFailureMessage, Message {
/*     */     UdpConnected.CommandFailed failureMessage();
/*     */   }
/*     */   
/*     */   public static interface Connected extends Event {}
/*     */   
/*     */   public static interface Disconnected extends Event {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\io\UdpConnected.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */