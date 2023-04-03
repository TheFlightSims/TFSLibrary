/*      */ package scala.collection.immutable;
/*      */ 
/*      */ import java.util.NoSuchElementException;
/*      */ import scala.Function0;
/*      */ import scala.Function1;
/*      */ import scala.Function2;
/*      */ import scala.Function3;
/*      */ import scala.Function4;
/*      */ import scala.Function5;
/*      */ import scala.Option;
/*      */ import scala.PartialFunction;
/*      */ import scala.Serializable;
/*      */ import scala.Some;
/*      */ import scala.Tuple2;
/*      */ import scala.Tuple3;
/*      */ import scala.collection.AbstractSeq;
/*      */ import scala.collection.GenIterable;
/*      */ import scala.collection.GenIterableViewLike;
/*      */ import scala.collection.GenMap;
/*      */ import scala.collection.GenSeq;
/*      */ import scala.collection.GenSeqLike;
/*      */ import scala.collection.GenSeqViewLike;
/*      */ import scala.collection.GenSet;
/*      */ import scala.collection.GenTraversable;
/*      */ import scala.collection.GenTraversableOnce;
/*      */ import scala.collection.GenTraversableViewLike;
/*      */ import scala.collection.Iterable;
/*      */ import scala.collection.IterableLike;
/*      */ import scala.collection.IterableView;
/*      */ import scala.collection.IterableViewLike;
/*      */ import scala.collection.Iterator;
/*      */ import scala.collection.LinearSeq;
/*      */ import scala.collection.LinearSeqLike;
/*      */ import scala.collection.LinearSeqOptimized;
/*      */ import scala.collection.Parallelizable;
/*      */ import scala.collection.Seq;
/*      */ import scala.collection.SeqLike;
/*      */ import scala.collection.SeqView;
/*      */ import scala.collection.SeqViewLike;
/*      */ import scala.collection.Traversable;
/*      */ import scala.collection.TraversableLike;
/*      */ import scala.collection.TraversableOnce;
/*      */ import scala.collection.TraversableView;
/*      */ import scala.collection.TraversableViewLike;
/*      */ import scala.collection.ViewMkString;
/*      */ import scala.collection.generic.CanBuildFrom;
/*      */ import scala.collection.generic.FilterMonadic;
/*      */ import scala.collection.generic.GenTraversableFactory;
/*      */ import scala.collection.generic.GenericCompanion;
/*      */ import scala.collection.generic.GenericTraversableTemplate;
/*      */ import scala.collection.generic.SliceInterval;
/*      */ import scala.collection.mutable.Buffer;
/*      */ import scala.collection.mutable.Builder;
/*      */ import scala.collection.mutable.LazyBuilder;
/*      */ import scala.collection.mutable.StringBuilder;
/*      */ import scala.collection.mutable.WrappedArray;
/*      */ import scala.collection.parallel.Combiner;
/*      */ import scala.collection.parallel.ParSeq;
/*      */ import scala.collection.parallel.immutable.ParSeq;
/*      */ import scala.math.Integral;
/*      */ import scala.math.Numeric;
/*      */ import scala.math.Ordering;
/*      */ import scala.reflect.ClassTag;
/*      */ import scala.reflect.ScalaSignature;
/*      */ import scala.runtime.AbstractFunction0;
/*      */ import scala.runtime.AbstractFunction1;
/*      */ import scala.runtime.BoxedUnit;
/*      */ import scala.runtime.BoxesRunTime;
/*      */ import scala.runtime.ObjectRef;
/*      */ 
/*      */ @ScalaSignature(bytes = "\006\001\031]d!B\001\003\003\003I!AB*ue\026\fWN\003\002\004\t\005I\021.\\7vi\006\024G.\032\006\003\013\031\t!bY8mY\026\034G/[8o\025\0059\021!B:dC2\f7\001A\013\003\025E\031R\001A\006\034?\031\0022\001D\007\020\033\005!\021B\001\b\005\005-\t%m\035;sC\016$8+Z9\021\005A\tB\002\001\003\007%\001!)\031A\n\003\003\005\013\"\001\006\r\021\005U1R\"\001\004\n\005]1!a\002(pi\"Lgn\032\t\003+eI!A\007\004\003\007\005s\027\020E\002\035;=i\021AA\005\003=\t\021\021\002T5oK\006\0248+Z9\021\t\001\032s\"J\007\002C)\021!\005B\001\bO\026tWM]5d\023\t!\023E\001\016HK:,'/[2Ue\0064XM]:bE2,G+Z7qY\006$X\r\005\002\035\001A!AbJ\b*\023\tACA\001\nMS:,\027M]*fc>\003H/[7ju\026$\007c\001\017\001\037!)1\006\001C\001Y\0051A(\0338jiz\"\022!\013\005\006]\001!\teL\001\nG>l\007/\0318j_:,\022\001\r\t\004AE*\023B\001\032\"\005A9UM\\3sS\016\034u.\0349b]&|g\016C\0035\001\031\005Q'A\004jg\026k\007\017^=\026\003Y\002\"!F\034\n\005a2!a\002\"p_2,\027M\034\005\006u\0011\taO\001\005Q\026\fG-F\001\020\021\025i\004A\"\001?\003\021!\030-\0337\026\003%BQ\001\021\001\007\022U\n1\002^1jY\022+g-\0338fI\")!\t\001C\001\007\0061\021\r\0359f]\022,\"\001R$\025\005\025S\005c\001\017\001\rB\021\001c\022\003\006\021\006\023\r!\023\002\002\005F\021q\002\007\005\007\027\006#\t\031\001'\002\tI,7\017\036\t\004+5{\025B\001(\007\005!a$-\0378b[\026t\004c\001\007Q\r&\021\021\013\002\002\020)J\fg/\032:tC\ndWm\0248dK\")1\013\001C\001}\005)am\034:dK\")Q\013\001C\001-\006)\001O]5oiR\tq\013\005\002\0261&\021\021L\002\002\005+:LG\017C\003V\001\021\0051\f\006\002X9\")QL\027a\001=\006\0311/\0329\021\005}\023gBA\013a\023\t\tg!\001\004Qe\026$WMZ\005\003G\022\024aa\025;sS:<'BA1\007\021\0251\007\001\"\021h\003\031aWM\\4uQV\t\001\016\005\002\026S&\021!N\002\002\004\023:$\b\"\0027\001\t\023i\027AB1t)\"\fG/\006\002oaR\021qN\035\t\003!A$Q!]6C\002M\021A\001\0265bi\")1o\033a\001i\006\t\001\020\005\002\026k&\021aO\002\002\007\003:L(+\0324)\005-D\bCA\013z\023\tQhA\001\004j]2Lg.\032\005\006y\002!I!`\001\tCN\034FO]3b[V\031a0a\001\025\007}\f)\001\005\003\035\001\005\005\001c\001\t\002\004\021)\001j\037b\001'!)1o\037a\001i\"\0221\020\037\005\b\003\027\001A\021BA\007\003=I7o\025;sK\006l')^5mI\026\024XCBA\b\003;\t\t\003F\0027\003#A\001\"a\005\002\n\001\007\021QC\001\003E\032\004\002\002IA\fS\005m\021qD\005\004\0033\t#\001D\"b]\n+\030\016\0343Ge>l\007c\001\t\002\036\0211\001*!\003C\002M\0012\001EA\021\t\031\t\030\021\002b\001'!\032\021\021\002=\t\r\005\035\002\001\"\021?\003!!xn\025;sK\006l\007BBA\026\001\021\005S'A\biCN$UMZ5oSR,7+\033>f\021\035\ty\003\001C!\003c\t!\002\n9mkN$\003\017\\;t+\031\t\031$!\021\002:Q!\021QGA\")\021\t9$a\017\021\007A\tI\004\002\004r\003[\021\ra\005\005\t\003'\ti\003q\001\002>AA\001%a\006*\003\t9\004E\002\021\003\003\"a\001SA\027\005\004I\005\002CA#\003[\001\r!a\022\002\tQD\027\r\036\t\006\031\005%\023qH\005\004\003\027\"!AE$f]R\023\030M^3sg\006\024G.Z(oG\026Dq!a\024\001\t\003\n\t&A\006%a2,8\017J2pY>tWCBA*\003C\nI\006\006\003\002V\005\rD\003BA,\0037\0022\001EA-\t\031\t\030Q\nb\001'!A\0211CA'\001\b\ti\006\005\005!\003/I\023qLA,!\r\001\022\021\r\003\007\021\0065#\031A%\t\021\005\025\024Q\na\001\003?\nA!\0327f[\"9\021\021\016\001\005F\005-\024\001C:dC:dUM\032;\026\r\0055\024QPA;)\021\ty'!#\025\t\005E\024q\020\013\005\003g\n9\bE\002\021\003k\"a!]A4\005\004\031\002\002CA\n\003O\002\035!!\037\021\021\001\n9\"KA>\003g\0022\001EA?\t\031A\025q\rb\001'!A\021\021QA4\001\004\t\031)\001\002paBAQ#!\"\002|=\tY(C\002\002\b\032\021\021BR;oGRLwN\034\032\t\021\005-\025q\ra\001\003w\n\021A\037\005\b\003\037\003AQIAI\003\ri\027\r]\013\007\003'\013\t+!'\025\t\005U\0251\025\013\005\003/\013Y\nE\002\021\0033#a!]AG\005\004\031\002\002CA\n\003\033\003\035!!(\021\021\001\n9\"KAP\003/\0032\001EAQ\t\031A\025Q\022b\001'!A\021QUAG\001\004\t9+A\001g!\031)\022\021V\b\002 &\031\0211\026\004\003\023\031+hn\031;j_:\f\004bBAX\001\021\025\023\021W\001\bG>dG.Z2u+\031\t\031,!1\002:R!\021QWAb)\021\t9,a/\021\007A\tI\f\002\004r\003[\023\ra\005\005\t\003'\ti\013q\001\002>BA\001%a\006*\003\0139\fE\002\021\003\003$a\001SAW\005\004\031\002\002CAc\003[\003\r!a2\002\005A4\007CB\013\002J>\ty,C\002\002L\032\021q\002U1si&\fGNR;oGRLwN\034\005\b\003\037\004AQIAi\003\0351G.\031;NCB,b!a5\002b\006eG\003BAk\003G$B!a6\002\\B\031\001#!7\005\rE\fiM1\001\024\021!\t\031\"!4A\004\005u\007\003\003\021\002\030%\ny.a6\021\007A\t\t\017\002\004I\003\033\024\ra\005\005\t\003K\013i\r1\001\002fB1Q#!+\020\003O\004R\001DA%\003?Dq!a;\001\t\003\ni/\001\004gS2$XM\035\013\004S\005=\b\002CAy\003S\004\r!a=\002\003A\004R!FAU\037YBq!a>\001\t\013\nI0\001\006xSRDg)\0337uKJ$B!a?\003bA!\021Q`A\000\033\005\001aA\002B\001\001\t\021\031A\001\tTiJ,\027-\\,ji\"4\025\016\034;feN!\021q B\003!\021\tiPa\002\n\t\t%!1\002\002\013/&$\bNR5mi\026\024\030b\001B\007\t\tyAK]1wKJ\034\030M\0317f\031&\\W\rC\006\002r\006}(\021!Q\001\n\005M\bbB\026\002\000\022\005!1\003\013\005\003w\024)\002\003\005\002r\nE\001\031AAz\021!\ty)a@\005B\teQC\002B\016\005S\021\t\003\006\003\003\036\t-B\003\002B\020\005G\0012\001\005B\021\t\031\t(q\003b\001'!A\0211\003B\f\001\b\021)\003\005\005!\003/I#q\005B\020!\r\001\"\021\006\003\007\021\n]!\031A\n\t\021\005\025&q\003a\001\005[\001b!FAU\037\t\035\002\002CAh\003$\tE!\r\026\r\tM\"\021\tB\035)\021\021)Da\021\025\t\t]\"1\b\t\004!\teBAB9\0030\t\0071\003\003\005\002\024\t=\0029\001B\037!!\001\023qC\025\003@\t]\002c\001\t\003B\0211\001Ja\fC\002MA\001\"!*\0030\001\007!Q\t\t\007+\005%vBa\022\021\0131\tIEa\020\t\021\t-\023q C!\005\033\nqAZ8sK\006\034\007.\006\003\003P\t]CcA,\003R!A\021Q\025B%\001\004\021\031\006\005\004\026\003S{!Q\013\t\004!\t]CA\002%\003J\t\0071\003\003\005\002x\006}H\021\tB.)\021\tYP!\030\t\021\t}#\021\fa\001\003g\f\021!\035\005\t\003c\f)\0201\001\002t\"9!Q\r\001\005B\t\035\024\001C5uKJ\fGo\034:\026\005\t%\004\003\002\007\003l=I1A!\034\005\005!IE/\032:bi>\024\bb\002B&\001\021\025#\021O\013\005\005g\022Y\bF\002X\005kB\001\"!*\003p\001\007!q\017\t\007+\005%vB!\037\021\007A\021Y\b\002\004I\005_\022\ra\005\025\005\005_\022y\b\005\003\003\002\n\035UB\001BB\025\r\021)IB\001\013C:tw\016^1uS>t\027\002\002BE\005\007\023q\001^1jYJ,7\rC\004\003\016\002!)Ea$\002\021\031|G\016\032'fMR,BA!%\003\030R!!1\023BO)\021\021)J!'\021\007A\0219\n\002\004I\005\027\023\ra\005\005\t\003\003\023Y\t1\001\003\034BAQ#!\"\003\026>\021)\n\003\005\002\f\n-\005\031\001BKQ\021\021YIa \t\017\t\r\006\001\"\022\003&\006Q!/\0323vG\026dUM\032;\026\t\t\035&1\026\013\005\005S\023i\013E\002\021\005W#a\001\023BQ\005\004I\005\002CAS\005C\003\rAa,\021\021U\t)I!+\020\005SCqAa-\001\t\003\022),A\005qCJ$\030\016^5p]R!!q\027B_!\025)\"\021X\025*\023\r\021YL\002\002\007)V\004H.\032\032\t\021\005E(\021\027a\001\003gDqA!1\001\t\013\022\031-A\002{SB,\002B!2\003V\nm'1\032\013\005\005\017\024i\016\006\003\003J\n5\007c\001\t\003L\0221\021Oa0C\002MA\001\"a\005\003@\002\017!q\032\t\tA\005]\021F!5\003JB9QC!/\003T\ne\007c\001\t\003V\0229!q\033B`\005\004I%AA!2!\r\001\"1\034\003\007\021\n}&\031A\n\t\021\005\025#q\030a\001\005?\004R\001\004Bq\0053L1Aa9\005\005-9UM\\%uKJ\f'\r\\3\t\017\t\035\b\001\"\021\003j\006a!0\0339XSRD\027J\0343fqV1!1\036B}\005_$BA!<\003rB\031\001Ca<\005\rE\024)O1\001\024\021!\t\031B!:A\004\tM\b\003\003\021\002\030%\022)P!<\021\rU\021ILa>i!\r\001\"\021 \003\b\005/\024)O1\001J\021\035\021i\020\001C!\005\f\021\"\0313e'R\024\030N\\4\025\025\r\0051QBB\t\007+\0319\002\005\003\004\004\r%QBAB\003\025\r\0319\001B\001\b[V$\030M\0317f\023\021\031Ya!\002\003\033M#(/\0338h\005VLG\016Z3s\021!\031yAa?A\002\r\005\021!\0012\t\017\rM!1 a\001=\006)1\017^1si\"1QLa?A\002yCqa!\007\003|\002\007a,A\002f]\022Dqa!\b\001\t\003\032y\"\001\005nWN#(/\0338h)\rq6\021\005\005\007;\016m\001\031\0010\t\017\ru\001\001\"\021\004&U\ta\fC\004\004\036\001!\te!\013\025\017y\033Yc!\f\0040!911CB\024\001\004q\006BB/\004(\001\007a\fC\004\004\032\r\035\002\031\0010\t\017\rM\002\001\"\021\0046\005AAo\\*ue&tw\rF\001_\021\035\031I\004\001C!\007w\tqa\0359mSR\fE\017\006\003\0038\016u\002bBB \007o\001\r\001[\001\002]\"911\t\001\005B\r\025\023\001\002;bW\026$2!KB$\021\035\031yd!\021A\002!Dqaa\023\001\t\013\032i%\001\003ee>\004HcA\025\004P!91qHB%\001\004A\007\006BB%\005Bqa!\026\001\t\003\0329&A\003tY&\034W\rF\003*\0073\032i\006C\004\004\\\rM\003\031\0015\002\t\031\024x.\034\005\b\007?\032\031\0061\001i\003\025)h\016^5m\021\031\031\031\007\001C!}\005!\021N\\5u\021\035\0319\007\001C!\007S\n\021\002^1lKJKw\r\033;\025\007%\032Y\007C\004\004@\r\025\004\031\0015\t\017\r=\004\001\"\021\004r\005IA/Y6f/\"LG.\032\013\004S\rM\004\002CAy\007[\002\r!a=\t\017\r]\004\001\"\021\004z\005IAM]8q/\"LG.\032\013\004S\rm\004\002CAy\007k\002\r!a=\t\r\r}\004\001\"\021?\003!!\027n\035;j]\016$\bbBBB\001\021\0053QQ\001\006a\006$Gk\\\013\007\007\017\033)j!$\025\r\r%5qSBN)\021\031Yia$\021\007A\031i\t\002\004r\007\003\023\ra\005\005\t\003'\031\t\tq\001\004\022BA\001%a\006*\007'\033Y\tE\002\021\007+#a\001SBA\005\004I\005bBBM\007\003\003\r\001[\001\004Y\026t\007\002CA3\007\003\003\raa%\t\r\r}\005\001\"\021?\003\035\021XM^3sg\026Dqaa)\001\t\003\032)+A\004gY\006$H/\0328\026\t\r\0356Q\026\013\005\007S\033y\013\005\003\035\001\r-\006c\001\t\004.\0221\001j!)C\002MA\001b!-\004\"\002\01711W\001\016CN$&/\031<feN\f'\r\\3\021\rU\tIkDB[!\025a\021\021JBV\021\035\031I\f\001C!\007w\013AA^5foV\0211Q\030\n\006\007#81\031\004\b\007\003\0349\fAB_\0051a$/\0324j]\026lWM\034;?!\025a2QY\b*\023\r\0319M\001\002\013'R\024X-Y7WS\026<\bbBBf\001\021\0053QZ\001\rgR\024\030N\\4Qe\0264\027\016_\013\003\007\037\004Ba!5\004\\6\02111\033\006\005\007+\0349.\001\003mC:<'BABm\003\021Q\027M^1\n\007\r\034\031nB\004\004`\nA\ta!9\002\rM#(/Z1n!\ra21\035\004\007\003\tA\ta!:\024\t\r\r8q\035\t\005A\r%X%C\002\004l\006\022!bU3r\r\006\034Go\034:z\021\035Y31\035C\001\007_$\"a!9\007\017\rM81\035\001\004v\n\0212\013\036:fC6\034\025M\034\"vS2$gI]8n+\021\0319\020b\002\024\t\rE8\021 \t\007\007w\034i\020\"\002\016\005\r\r\030\002BB\000\t\003\0211cR3oKJL7mQ1o\005VLG\016\032$s_6L1\001b\001\"\005U9UM\034+sCZ,'o]1cY\0264\025m\031;pef\0042\001\005C\004\t\031\0212\021\037b\001'!91f!=\005\002\021-AC\001C\007!\031\031Yp!=\005\006!AA\021CBr\t\007!\031\"\001\007dC:\024U/\0337e\rJ|W.\006\003\005\026\021\005RC\001C\f!%\001\023q\003C\r\t?!\031\003\005\003\004|\022m\021b\001C\017c\t!1i\0347m!\r\001B\021\005\003\007%\021=!\031A\n\021\tq\001Aq\004\005\t\tO\031\031\017\"\001\005*\005Qa.Z<Ck&dG-\032:\026\t\021-BQG\013\003\t[\001\002ba\001\0050\021MBqG\005\005\tc\031)AA\004Ck&dG-\032:\021\007A!)\004\002\004\023\tK\021\ra\005\t\0059\001!\031DB\004\005<\r\r\b\001\"\020\003\033M#(/Z1n\005VLG\016Z3s+\021!y\004\"\023\024\t\021eB\021\t\t\t\007\007!\031\005b\022\005L%!AQIB\003\005-a\025M_=Ck&dG-\032:\021\007A!I\005\002\004\023\ts\021\ra\005\t\0059\001!9\005C\004,\ts!\t\001b\024\025\005\021E\003CBB~\ts!9\005\003\005\005V\021eB\021\001C,\003\031\021Xm];miR\021A1J\004\t\t7\032\031\017#\001\005^\005)Q)\0349usB!11 C0\r!!\tga9\t\002\021\r$!B#naRL8C\002C0\tK\"9\007E\002\035\001Q\0012!\006C5\023\r!YG\002\002\r'\026\024\030.\0317ju\006\024G.\032\005\bW\021}C\021\001C8)\t!i\006\003\0045\t?\"\t%\016\005\bu\021}C\021\tC;+\005!\002bB\037\005`\021\005CQ\017\005\007\001\022}C\021A\033\t\025\021uDqLA\001\n\023!y(A\006sK\006$'+Z:pYZ,GC\001CA!\021\031\t\016b!\n\t\021\02551\033\002\007\037\nTWm\031;\t\021\021%51\035C!\t\027\013Q!Z7qif,B\001\"$\005\024V\021Aq\022\t\0059\001!\t\nE\002\021\t'#aA\005CD\005\004\031\002\002\003CL\007G$\t\005\"'\002\013\005\004\b\017\\=\026\t\021mE\021\025\013\005\t;#\031\013\005\003\035\001\021}\005c\001\t\005\"\0221!\003\"&C\002MA\001\002\"*\005\026\002\007AqU\001\003qN\004R!\006CU\t?K1\001b+\007\005)a$/\0329fCR,GM\020\004\b\t_\033\031\017\001CY\005-\031uN\\:Xe\006\004\b/\032:\026\t\021MFqX\n\004\t[#\bb\003C\\\t[\023\t\021*A\005\ts\013!\001\0367\021\tUiE1\030\t\0059\001!i\fE\002\021\t#aA\005CW\005\004\031\002bB\026\005.\022\005A1\031\013\005\t\013$9\r\005\004\004|\0225FQ\030\005\n\to#\t\r\"a\001\tsC\001\002b3\005.\022\005AQZ\001\022I!\f7\017\033\023d_2|g\016J2pY>tG\003\002C^\t\037D\001\002\"5\005J\002\007AQX\001\003Q\022D\001\002\"6\005.\022\005Aq[\001\030I!\f7\017\033\023d_2|g\016J2pY>tGeY8m_:$B\001b/\005Z\"AA1\034Cj\001\004!Y,\001\004qe\0264\027\016\037\005\t\t?\034\031\017b\001\005b\006Y1m\0348t/J\f\007\017]3s+\021!\031\017\";\025\t\021\025H1\036\t\007\007w$i\013b:\021\007A!I\017\002\004\023\t;\024\ra\005\005\n\t[$i\016\"a\001\t_\faa\035;sK\006l\007\003B\013N\tc\004B\001\b\001\005h\036AA1ZBr\021\003!)\020\005\003\004|\022]h\001\003C}\007GD\t\001b?\003#\021B\027m\0355%G>dwN\034\023d_2|gnE\002\005xRDqa\013C|\t\003!y\020\006\002\005v\"AQ1\001C|\t\003))!A\004v]\006\004\b\017\\=\026\t\025\035Q1\003\013\005\013\023)9\002E\003\026\013\027)y!C\002\006\016\031\021aa\0249uS>t\007cB\013\003:\026EQQ\003\t\004!\025MAA\002\n\006\002\t\0071\003\005\003\035\001\025E\001\002\003CS\013\003\001\r!\"\006\b\021\025m11\035E\001\013;\tAaY8ogB!11`C\020\r!)\tca9\t\002\025\r\"\001B2p]N\0342!b\bu\021\035YSq\004C\001\013O!\"!\"\b\t\021\021]Uq\004C\001\013W)B!\"\f\006tQ1QqFC;\013o\002baa?\0062\025EdaBC\032\007G\024QQ\007\002\005\007>t7/\006\003\0068\025u2CBC\031\013s!9\007\005\003\035\001\025m\002c\001\t\006>\0219!#\"\r\005\006\004\031\002b\003Ci\013c\021\t\021)A\005\013wA1\002b.\0062\t\005I\025!\003\006DA!Q#TC\035\021\035YS\021\007C\001\013\017\"b!\"\023\006L\0255\003CBB~\013c)Y\004\003\005\005R\026\025\003\031AC\036\021%!9,\"\022\005\002\004)\031\005\003\0045\013c!\t%\016\005\bu\025EB\021IC*+\t)Y\004\003\007\006X\025E\002\031!A!B\023)I$A\003uYZ\013G\016\013\003\006V\025m\003cA\013\006^%\031Qq\f\004\003\021Y|G.\031;jY\026Da\001QC\031\t\003)\004bB\037\0062\021\005SQM\013\003\013sAc!\"\r\006j\025=\004cA\013\006l%\031QQ\016\004\003!M+'/[1m-\026\0248/[8o+&#e\004C|%\032Wa]<O#\021\007A)\031\b\002\004\023\013S\021\ra\005\005\t\t#,I\0031\001\006r!IAqWC\025\t\003\007Q\021\020\t\005+5+Y\b\005\003\035\001\025E\004\002CC\002\013?!\t!b \026\t\025\005U\021\022\013\005\013\007+i\tE\003\026\013\027))\tE\004\026\005s+9)b#\021\007A)I\t\002\004\023\013{\022\ra\005\t\0059\001)9\t\003\005\005&\026u\004\031ACF\021!)\tja9\005\002\025M\025aB5uKJ\fG/Z\013\005\013++i\n\006\003\006\030\026\rF\003BCM\013?\003B\001\b\001\006\034B\031\001#\"(\005\rI)yI1\001\024\021!\t)+b$A\002\025\005\006cB\013\002*\026mU1\024\005\t\007')y\t1\001\006\034\"AQ\021SBr\t\003*9+\006\003\006*\026EFCBCV\013o+I\f\006\003\006.\026M\006\003\002\017\001\013_\0032\001ECY\t\031\021RQ\025b\001'!A\021QUCS\001\004))\fE\004\026\003S+y+b,\t\021\rMQQ\025a\001\013_Cqa!'\006&\002\007\001\016\003\005\004\\\r\rH\021AC_)\031)y,\"1\006DB\031A\004\0015\t\017\rMQ1\030a\001Q\"9QQYC^\001\004A\027\001B:uKBD\001ba\027\004d\022\005Q\021\032\013\005\013+Y\rC\004\004\024\025\035\007\031\0015\t\021\025=71\035C\001\013#\f1bY8oi&tW/\0317msV!Q1[Cm)\021)).b7\021\tq\001Qq\033\t\004!\025eGA\002\n\006N\n\0071\003C\005\002f\0255G\0211\001\006^B!Q#TCl\021!)\toa9\005B\025\r\030\001\0024jY2,B!\":\006nR!Qq]Cz)\021)I/b<\021\tq\001Q1\036\t\004!\0255HA\002\n\006`\n\0071\003C\005\002f\025}G\0211\001\006rB!Q#TCv\021\035\031y$b8A\002!D\001\"b>\004d\022\005S\021`\001\ti\006\024W\017\\1uKV!Q1 D\002)\021)iP\"\003\025\t\025}hQ\001\t\0059\0011\t\001E\002\021\r\007!aAEC{\005\004\031\002\002CAS\013k\004\rAb\002\021\rU\tI\013\033D\001\021\035\031y$\">A\002!D\001B\"\004\004d\022\005cqB\001\006e\006tw-Z\013\005\r#1I\002\006\005\007\024\031eb1\bD\037)\0211)B\"\b\021\tq\001aq\003\t\004!\031eAa\002D\016\r\027\021\ra\005\002\002)\"Qaq\004D\006\003\003\005\035A\"\t\002\025\0254\030\016Z3oG\026$\023\007\005\004\007$\031Mbq\003\b\005\rK1yC\004\003\007(\0315RB\001D\025\025\r1Y\003C\001\007yI|w\016\036 \n\003\035I1A\"\r\007\003\035\001\030mY6bO\026LAA\"\016\0078\tA\021J\034;fOJ\fGNC\002\0072\031A\001ba\005\007\f\001\007aq\003\005\t\00731Y\0011\001\007\030!AQQ\031D\006\001\00419\002C\005\007B\r\rH\021\001\002\007D\005aa-\0337uKJ,G\rV1jYV!aQ\tD&)\03119E\"\024\007RA111`C\031\r\023\0022\001\005D&\t\031\021bq\bb\001'!AAQ\036D \001\0041y\005\005\003\035\001\031%\003\002CAy\r\001\rAb\025\021\rU\tIK\"\0237\021%19fa9\005\002\t1I&A\007d_2dWm\031;fIR\013\027\016\\\013\t\r72IG\"\031\007vQAaQ\fD2\rW2y\007\005\004\004|\026Ebq\f\t\004!\031\005DA\002%\007V\t\0071\003\003\005\005n\032U\003\031\001D3!\021a\002Ab\032\021\007A1I\007\002\004\023\r+\022\ra\005\005\t\003\0134)\0061\001\007nA9Q#!3\007h\031}\003\002CA\n\r+\002\rA\"\035\021\023\001\n9B\"\032\007`\031M\004c\001\t\007v\0211\021O\"\026C\002M\001")
/*      */ public abstract class Stream<A> extends AbstractSeq<A> implements LinearSeq<A>, GenericTraversableTemplate<A, Stream>, LinearSeqOptimized<A, Stream<A>> {
/*      */   public boolean scala$collection$LinearSeqOptimized$$super$sameElements(GenIterable that) {
/*  185 */     return IterableLike.class.sameElements(this, that);
/*      */   }
/*      */   
/*      */   public A apply(int n) {
/*  185 */     return (A)LinearSeqOptimized.class.apply(this, n);
/*      */   }
/*      */   
/*      */   public boolean forall(Function1 p) {
/*  185 */     return LinearSeqOptimized.class.forall(this, p);
/*      */   }
/*      */   
/*      */   public boolean exists(Function1 p) {
/*  185 */     return LinearSeqOptimized.class.exists(this, p);
/*      */   }
/*      */   
/*      */   public boolean contains(Object elem) {
/*  185 */     return LinearSeqOptimized.class.contains(this, elem);
/*      */   }
/*      */   
/*      */   public Option<A> find(Function1 p) {
/*  185 */     return LinearSeqOptimized.class.find(this, p);
/*      */   }
/*      */   
/*      */   public <B> B foldRight(Object z, Function2 f) {
/*  185 */     return (B)LinearSeqOptimized.class.foldRight(this, z, f);
/*      */   }
/*      */   
/*      */   public <B> B reduceRight(Function2 op) {
/*  185 */     return (B)LinearSeqOptimized.class.reduceRight(this, op);
/*      */   }
/*      */   
/*      */   public A last() {
/*  185 */     return (A)LinearSeqOptimized.class.last(this);
/*      */   }
/*      */   
/*      */   public Stream<A> dropRight(int n) {
/*  185 */     return (Stream<A>)LinearSeqOptimized.class.dropRight(this, n);
/*      */   }
/*      */   
/*      */   public Tuple2<Stream<A>, Stream<A>> span(Function1 p) {
/*  185 */     return LinearSeqOptimized.class.span(this, p);
/*      */   }
/*      */   
/*      */   public <B> boolean sameElements(GenIterable that) {
/*  185 */     return LinearSeqOptimized.class.sameElements(this, that);
/*      */   }
/*      */   
/*      */   public int lengthCompare(int len) {
/*  185 */     return LinearSeqOptimized.class.lengthCompare(this, len);
/*      */   }
/*      */   
/*      */   public boolean isDefinedAt(int x) {
/*  185 */     return LinearSeqOptimized.class.isDefinedAt(this, x);
/*      */   }
/*      */   
/*      */   public int segmentLength(Function1 p, int from) {
/*  185 */     return LinearSeqOptimized.class.segmentLength(this, p, from);
/*      */   }
/*      */   
/*      */   public int indexWhere(Function1 p, int from) {
/*  185 */     return LinearSeqOptimized.class.indexWhere(this, p, from);
/*      */   }
/*      */   
/*      */   public int lastIndexWhere(Function1 p, int end) {
/*  185 */     return LinearSeqOptimized.class.lastIndexWhere(this, p, end);
/*      */   }
/*      */   
/*      */   public LinearSeq<A> seq() {
/*  185 */     return LinearSeq$class.seq(this);
/*      */   }
/*      */   
/*      */   public LinearSeq<A> thisCollection() {
/*  185 */     return LinearSeqLike.class.thisCollection(this);
/*      */   }
/*      */   
/*      */   public LinearSeq<A> toCollection(LinearSeqLike repr) {
/*  185 */     return LinearSeqLike.class.toCollection(this, repr);
/*      */   }
/*      */   
/*      */   public int hashCode() {
/*  185 */     return LinearSeqLike.class.hashCode(this);
/*      */   }
/*      */   
/*      */   public final <B> boolean corresponds(GenSeq that, Function2 p) {
/*  185 */     return LinearSeqLike.class.corresponds(this, that, p);
/*      */   }
/*      */   
/*      */   public Seq<A> toSeq() {
/*  185 */     return Seq$class.toSeq(this);
/*      */   }
/*      */   
/*      */   public Combiner<A, ParSeq<A>> parCombiner() {
/*  185 */     return Seq$class.parCombiner(this);
/*      */   }
/*      */   
/*      */   public Stream() {
/*  185 */     Traversable$class.$init$(this);
/*  185 */     Iterable$class.$init$(this);
/*  185 */     Seq$class.$init$(this);
/*  185 */     LinearSeqLike.class.$init$(this);
/*  185 */     LinearSeq.class.$init$(this);
/*  185 */     LinearSeq$class.$init$(this);
/*  185 */     LinearSeqOptimized.class.$init$(this);
/*      */   }
/*      */   
/*      */   public GenericCompanion<Stream> companion() {
/*  190 */     return (GenericCompanion<Stream>)Stream$.MODULE$;
/*      */   }
/*      */   
/*      */   public <B> Stream<B> append(Function0 rest) {
/*  237 */     Stream$$anonfun$append$1 stream$$anonfun$append$1 = new Stream$$anonfun$append$1(this, (Stream<A>)rest);
/*  237 */     A a = head();
/*  237 */     cons$ cons$ = cons$.MODULE$;
/*  237 */     return isEmpty() ? ((GenTraversableOnce)rest.apply()).toStream() : new Cons<B>((B)a, (Function0<Stream<B>>)stream$$anonfun$append$1);
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$append$1 extends AbstractFunction0<Stream<B>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function0 rest$1;
/*      */     
/*      */     public final Stream<B> apply() {
/*  237 */       return ((Stream)this.$outer.tail()).append(this.rest$1);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$append$1(Stream $outer, Function0 rest$1) {}
/*      */   }
/*      */   
/*      */   public Stream<A> force() {
/*  248 */     Stream these = this;
/*      */     while (true) {
/*  249 */       if (these.isEmpty())
/*  250 */         return this; 
/*      */       these = (Stream)these.tail();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void print() {
/*  254 */     print(", ");
/*      */   }
/*      */   
/*      */   private final void loop$1(Stream these, String start, String sep$1) {
/*      */     while (true) {
/*  261 */       scala.Console$.MODULE$.print(start);
/*  262 */       if (these.isEmpty()) {
/*  262 */         scala.Console$.MODULE$.print("empty");
/*      */         return;
/*      */       } 
/*  264 */       scala.Console$.MODULE$.print(these.head());
/*  265 */       start = sep$1;
/*  265 */       these = (Stream)these.tail();
/*      */     } 
/*      */   }
/*      */   
/*      */   public void print(String sep) {
/*  268 */     loop$1(this, "", sep);
/*      */   }
/*      */   
/*      */   public int length() {
/*  280 */     int len = 0;
/*  281 */     Stream left = this;
/*      */     while (true) {
/*  282 */       if (left.isEmpty())
/*  286 */         return len; 
/*      */       len++;
/*      */       left = (Stream)left.tail();
/*      */     } 
/*      */   }
/*      */   
/*      */   public <That> That scala$collection$immutable$Stream$$asThat(Object x) {
/*  292 */     return (That)x;
/*      */   }
/*      */   
/*      */   public <B> Stream<B> scala$collection$immutable$Stream$$asStream(Object x) {
/*  293 */     return (Stream<B>)x;
/*      */   }
/*      */   
/*      */   public <B, That> boolean scala$collection$immutable$Stream$$isStreamBuilder(CanBuildFrom bf) {
/*  295 */     return bf.apply(repr()) instanceof StreamBuilder;
/*      */   }
/*      */   
/*      */   public Stream<A> toStream() {
/*  299 */     return this;
/*      */   }
/*      */   
/*      */   private final boolean loop$2(Stream s) {
/*      */     while (true) {
/*  302 */       if (s.tailDefined()) {
/*  302 */         s = (Stream)s.tail();
/*      */         continue;
/*      */       } 
/*  302 */       return s.isEmpty();
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean hasDefiniteSize() {
/*  303 */     return loop$2(this);
/*      */   }
/*      */   
/*      */   public <B, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/*  330 */     Stream$$anonfun$$plus$plus$1 stream$$anonfun$$plus$plus$1 = new Stream$$anonfun$$plus$plus$1(this, (Stream<A>)that);
/*  330 */     A a = head();
/*  330 */     cons$ cons$ = cons$.MODULE$;
/*  330 */     return (bf.apply(repr()) instanceof StreamBuilder) ? (isEmpty() ? (That)that.toStream() : (That)new Cons<A>(a, (Function0<Stream<A>>)stream$$anonfun$$plus$plus$1)) : 
/*      */       
/*  332 */       (That)TraversableLike.class.$plus$plus(this, that, bf);
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$$plus$plus$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final GenTraversableOnce that$1;
/*      */     
/*      */     public final Stream<A> apply() {
/*      */       return (Stream<A>)((Stream)this.$outer.tail()).$plus$plus(this.that$1, Stream$.MODULE$.canBuildFrom());
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$$plus$plus$1(Stream $outer, GenTraversableOnce that$1) {}
/*      */   }
/*      */   
/*      */   public <B, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/*  335 */     Stream$$anonfun$$plus$colon$1 stream$$anonfun$$plus$colon$1 = new Stream$$anonfun$$plus$colon$1(this);
/*  335 */     cons$ cons$ = cons$.MODULE$;
/*  335 */     return (bf.apply(repr()) instanceof StreamBuilder) ? (That)new Cons(elem, (Function0<Stream<Object>>)stream$$anonfun$$plus$colon$1) : 
/*  336 */       (That)SeqLike.class.$plus$colon(this, elem, bf);
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$$plus$colon$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     public final Stream<A> apply() {
/*      */       return this.$outer;
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$$plus$colon$1(Stream $outer) {}
/*      */   }
/*      */   
/*      */   public final <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  354 */     WrappedArray wrappedArray = scala.Predef$.MODULE$.genericWrapArray(new Object[] { z });
/*  354 */     Stream$ stream$ = Stream$.MODULE$;
/*  355 */     Stream$$anonfun$scanLeft$1 stream$$anonfun$scanLeft$1 = new Stream$$anonfun$scanLeft$1(this, z, (Stream<A>)op);
/*  355 */     cons$ cons$ = cons$.MODULE$;
/*  355 */     return (bf.apply(repr()) instanceof StreamBuilder) ? (isEmpty() ? (That)wrappedArray.toStream() : (That)new Cons(z, (Function0<Stream<Object>>)stream$$anonfun$scanLeft$1)) : 
/*      */       
/*  357 */       (That)TraversableLike.class.scanLeft(this, z, op, bf);
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$scanLeft$1 extends AbstractFunction0<Stream<B>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Object z$1;
/*      */     
/*      */     private final Function2 op$1;
/*      */     
/*      */     public final Stream<B> apply() {
/*      */       return (Stream<B>)((Stream)this.$outer.tail()).scanLeft(this.op$1.apply(this.z$1, this.$outer.head()), this.op$1, Stream$.MODULE$.canBuildFrom());
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$scanLeft$1(Stream $outer, Object z$1, Function2 op$1) {}
/*      */   }
/*      */   
/*      */   public final <B, That> That map(Function1 f, CanBuildFrom bf) {
/*  376 */     Stream$$anonfun$map$1 stream$$anonfun$map$1 = new Stream$$anonfun$map$1(this, (Stream<A>)f);
/*  376 */     Object object = f.apply(head());
/*  376 */     cons$ cons$ = cons$.MODULE$;
/*  376 */     return (bf.apply(repr()) instanceof StreamBuilder) ? (isEmpty() ? (That)Empty$.MODULE$ : (That)new Cons(object, (Function0<Stream<Object>>)stream$$anonfun$map$1)) : 
/*      */       
/*  378 */       (That)TraversableLike.class.map(this, f, bf);
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$map$1 extends AbstractFunction0<Stream<B>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function1 f$1;
/*      */     
/*      */     public final Stream<B> apply() {
/*      */       CanBuildFrom<Stream<?>, ?, Stream<?>> canBuildFrom = Stream$.MODULE$.canBuildFrom();
/*      */       Function1 function1 = this.f$1;
/*      */       Stream stream = (Stream)this.$outer.tail();
/*      */       Stream$$anonfun$map$1 stream$$anonfun$map$1 = new Stream$$anonfun$map$1(stream, (Stream<A>)function1);
/*      */       Object object = function1.apply(stream.head());
/*      */       Stream.cons$ cons$ = Stream.cons$.MODULE$;
/*      */       return (canBuildFrom.apply(stream.repr()) instanceof Stream.StreamBuilder) ? (stream.isEmpty() ? Stream.Empty$.MODULE$ : new Stream.Cons<B>((B)object, (Function0<Stream<B>>)stream$$anonfun$map$1)) : (Stream<B>)TraversableLike.class.map(stream, function1, canBuildFrom);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$map$1(Stream $outer, Function1 f$1) {}
/*      */   }
/*      */   
/*      */   public final <B, That> That collect(PartialFunction<?, ?> pf, CanBuildFrom<Stream<?>, ?, ?> bf) {
/*  387 */     Stream<?> rest = this;
/*  388 */     for (; rest.nonEmpty() && !pf.isDefinedAt(rest.head()); rest = (Stream)rest.tail());
/*  392 */     return (bf.apply(repr()) instanceof StreamBuilder) ? (rest.isEmpty() ? (That)Empty$.MODULE$ : 
/*  393 */       (That)Stream$.MODULE$.collectedTail(rest, pf, bf)) : (That)TraversableLike.class.collect(this, pf, bf);
/*      */   }
/*      */   
/*      */   public final <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  441 */     ObjectRef nonEmptyPrefix = new ObjectRef(this);
/*  442 */     Stream prefix = ((GenTraversableOnce)f.apply(((Stream)nonEmptyPrefix.elem).head())).toStream();
/*  443 */     while (!((Stream)nonEmptyPrefix.elem).isEmpty() && prefix.isEmpty()) {
/*  444 */       nonEmptyPrefix.elem = ((Stream)nonEmptyPrefix.elem).tail();
/*  445 */       if (!((Stream)nonEmptyPrefix.elem).isEmpty())
/*  446 */         prefix = ((GenTraversableOnce)f.apply(((Stream)nonEmptyPrefix.elem).head())).toStream(); 
/*      */     } 
/*  449 */     return (bf.apply(repr()) instanceof StreamBuilder) ? (isEmpty() ? (That)Empty$.MODULE$ : (((Stream)nonEmptyPrefix.elem).isEmpty() ? (That)Stream$.MODULE$.empty() : 
/*  450 */       (That)prefix.append((Function0)new Stream$$anonfun$flatMap$1(this, f, (Stream<A>)nonEmptyPrefix)))) : 
/*      */       
/*  453 */       (That)TraversableLike.class.flatMap(this, f, bf);
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$flatMap$1 extends AbstractFunction0<Stream<B>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function1 f$2;
/*      */     
/*      */     private final ObjectRef nonEmptyPrefix$1;
/*      */     
/*      */     public final Stream<B> apply() {
/*      */       Stream prefix1;
/*      */       ObjectRef nonEmptyPrefix1;
/*      */       Stream stream1;
/*      */       Function1 function1;
/*      */       CanBuildFrom<Stream<?>, ?, Stream<?>> canBuildFrom;
/*      */       for (canBuildFrom = Stream$.MODULE$.canBuildFrom(), function1 = this.f$2, stream1 = (Stream)((Stream)this.nonEmptyPrefix$1.elem).tail(), nonEmptyPrefix1 = new ObjectRef(stream1), prefix1 = ((GenTraversableOnce)function1.apply(((Stream)nonEmptyPrefix1.elem).head())).toStream(); !((Stream)nonEmptyPrefix1.elem).isEmpty() && prefix1.isEmpty(); ) {
/*      */         nonEmptyPrefix1.elem = ((Stream)nonEmptyPrefix1.elem).tail();
/*      */         if (!((Stream)nonEmptyPrefix1.elem).isEmpty())
/*      */           prefix1 = ((GenTraversableOnce)function1.apply(((Stream)nonEmptyPrefix1.elem).head())).toStream(); 
/*      */       } 
/*      */       return (canBuildFrom.apply(stream1.repr()) instanceof Stream.StreamBuilder) ? (stream1.isEmpty() ? Stream.Empty$.MODULE$ : (((Stream)nonEmptyPrefix1.elem).isEmpty() ? Stream$.MODULE$.<B>empty() : prefix1.append((Function0)new Stream$$anonfun$flatMap$1(stream1, function1, (Stream<A>)nonEmptyPrefix1)))) : (Stream<B>)TraversableLike.class.flatMap(stream1, function1, canBuildFrom);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$flatMap$1(Stream $outer, Function1 f$2, ObjectRef nonEmptyPrefix$1) {}
/*      */   }
/*      */   
/*      */   public Stream<A> filter(Function1<A, Object> p) {
/*  471 */     Stream<A> rest = this;
/*      */     while (true) {
/*  472 */       if (rest.isEmpty() || BoxesRunTime.unboxToBoolean(p.apply(rest.head())))
/*  474 */         return rest.nonEmpty() ? Stream$.MODULE$.<A>filteredTail(rest, p) : 
/*  475 */           Empty$.MODULE$; 
/*      */       rest = (Stream<A>)rest.tail();
/*      */     } 
/*      */   }
/*      */   
/*      */   public final StreamWithFilter withFilter(Function1<A, Object> p) {
/*  478 */     return new StreamWithFilter(this, p);
/*      */   }
/*      */   
/*      */   public class StreamWithFilter extends TraversableLike<A, Stream<A>>.WithFilter {
/*      */     public final Function1<A, Object> scala$collection$immutable$Stream$StreamWithFilter$$p;
/*      */     
/*      */     public StreamWithFilter(Stream $outer, Function1<A, Object> p) {
/*  482 */       super($outer, p);
/*      */     }
/*      */     
/*      */     public final Stream scala$collection$immutable$Stream$StreamWithFilter$$tailMap$1(Stream coll, Function1 f$3) {
/*  486 */       ObjectRef tail = new ObjectRef(coll);
/*      */       while (true) {
/*  488 */         if (((Stream)tail.elem).isEmpty())
/*  490 */           return Stream.Empty$.MODULE$; 
/*  491 */         head = ((Stream)tail.elem).head();
/*  492 */         tail.elem = ((Stream)tail.elem).tail();
/*  493 */         if (BoxesRunTime.unboxToBoolean(this.scala$collection$immutable$Stream$StreamWithFilter$$p.apply(head))) {
/*  494 */           Stream$StreamWithFilter$$anonfun$scala$collection$immutable$Stream$StreamWithFilter$$tailMap$1$1 stream$StreamWithFilter$$anonfun$scala$collection$immutable$Stream$StreamWithFilter$$tailMap$1$1 = new Stream$StreamWithFilter$$anonfun$scala$collection$immutable$Stream$StreamWithFilter$$tailMap$1$1(this, f$3, (StreamWithFilter)tail);
/*  494 */           Object object = f$3.apply(head);
/*  494 */           Stream.cons$ cons$ = Stream.cons$.MODULE$;
/*  494 */           return new Stream.Cons(object, (Function0<Stream<Object>>)stream$StreamWithFilter$$anonfun$scala$collection$immutable$Stream$StreamWithFilter$$tailMap$1$1);
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*      */     public class Stream$StreamWithFilter$$anonfun$scala$collection$immutable$Stream$StreamWithFilter$$tailMap$1$1 extends AbstractFunction0<Stream<B>> implements Serializable {
/*      */       public static final long serialVersionUID = 0L;
/*      */       
/*      */       private final Function1 f$3;
/*      */       
/*      */       private final ObjectRef tail$1;
/*      */       
/*      */       public final Stream<B> apply() {
/*  494 */         return this.$outer.scala$collection$immutable$Stream$StreamWithFilter$$tailMap$1((Stream)this.tail$1.elem, this.f$3);
/*      */       }
/*      */       
/*      */       public Stream$StreamWithFilter$$anonfun$scala$collection$immutable$Stream$StreamWithFilter$$tailMap$1$1(Stream.StreamWithFilter $outer, Function1 f$3, ObjectRef tail$1) {}
/*      */     }
/*      */     
/*      */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/*  499 */       Stream stream = scala$collection$immutable$Stream$StreamWithFilter$$$outer();
/*  499 */       scala$collection$immutable$Stream$StreamWithFilter$$$outer();
/*  499 */       return (bf.apply(stream.repr()) instanceof Stream.StreamBuilder) ? (That)scala$collection$immutable$Stream$StreamWithFilter$$tailMap$1(scala$collection$immutable$Stream$StreamWithFilter$$$outer(), f) : 
/*  500 */         (That)super.map(f, bf);
/*      */     }
/*      */     
/*      */     public final Stream scala$collection$immutable$Stream$StreamWithFilter$$tailFlatMap$1(Stream coll, Function1 f$4) {
/*  505 */       ObjectRef tail = new ObjectRef(coll);
/*      */       while (true) {
/*  507 */         if (((Stream)tail.elem).isEmpty())
/*  509 */           return Stream.Empty$.MODULE$; 
/*  510 */         head = ((Stream)tail.elem).head();
/*  511 */         tail.elem = ((Stream)tail.elem).tail();
/*  512 */         if (BoxesRunTime.unboxToBoolean(this.scala$collection$immutable$Stream$StreamWithFilter$$p.apply(head)))
/*  513 */           return ((GenTraversableOnce)f$4.apply(head)).toStream().append((Function0)new Stream$StreamWithFilter$$anonfun$scala$collection$immutable$Stream$StreamWithFilter$$tailFlatMap$1$1(this, f$4, (StreamWithFilter)tail)); 
/*      */       } 
/*      */     }
/*      */     
/*      */     public class Stream$StreamWithFilter$$anonfun$scala$collection$immutable$Stream$StreamWithFilter$$tailFlatMap$1$1 extends AbstractFunction0<Stream<B>> implements Serializable {
/*      */       public static final long serialVersionUID = 0L;
/*      */       
/*      */       private final Function1 f$4;
/*      */       
/*      */       private final ObjectRef tail$2;
/*      */       
/*      */       public final Stream<B> apply() {
/*  513 */         return this.$outer.scala$collection$immutable$Stream$StreamWithFilter$$tailFlatMap$1((Stream)this.tail$2.elem, this.f$4);
/*      */       }
/*      */       
/*      */       public Stream$StreamWithFilter$$anonfun$scala$collection$immutable$Stream$StreamWithFilter$$tailFlatMap$1$1(Stream.StreamWithFilter $outer, Function1 f$4, ObjectRef tail$2) {}
/*      */     }
/*      */     
/*      */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  518 */       Stream stream = scala$collection$immutable$Stream$StreamWithFilter$$$outer();
/*  518 */       scala$collection$immutable$Stream$StreamWithFilter$$$outer();
/*  518 */       return (bf.apply(stream.repr()) instanceof Stream.StreamBuilder) ? (That)scala$collection$immutable$Stream$StreamWithFilter$$tailFlatMap$1(scala$collection$immutable$Stream$StreamWithFilter$$$outer(), f) : 
/*  519 */         (That)super.flatMap(f, bf);
/*      */     }
/*      */     
/*      */     public <B> void foreach(Function1 f) {
/*  523 */       Stream$StreamWithFilter$$anonfun$foreach$1 stream$StreamWithFilter$$anonfun$foreach$1 = new Stream$StreamWithFilter$$anonfun$foreach$1(this, (StreamWithFilter)f);
/*  523 */       Stream<Object> stream = (Stream)this.$outer;
/*      */       while (true) {
/*  523 */         if (stream.isEmpty())
/*      */           return; 
/*  523 */         Object object = stream.head();
/*  523 */         BoxesRunTime.unboxToBoolean(stream$StreamWithFilter$$anonfun$foreach$1.$outer.scala$collection$immutable$Stream$StreamWithFilter$$p.apply(object)) ? stream$StreamWithFilter$$anonfun$foreach$1.f$5.apply(object) : BoxedUnit.UNIT;
/*  523 */         stream = (Stream<Object>)stream.tail();
/*      */       } 
/*      */     }
/*      */     
/*      */     public class Stream$StreamWithFilter$$anonfun$foreach$1 extends AbstractFunction1<A, Object> implements Serializable {
/*      */       public static final long serialVersionUID = 0L;
/*      */       
/*      */       public final Function1 f$5;
/*      */       
/*      */       public Stream$StreamWithFilter$$anonfun$foreach$1(Stream.StreamWithFilter $outer, Function1 f$5) {}
/*      */       
/*      */       public final Object apply(Object x) {
/*  524 */         return BoxesRunTime.unboxToBoolean(this.$outer.scala$collection$immutable$Stream$StreamWithFilter$$p.apply(x)) ? this.f$5.apply(x) : BoxedUnit.UNIT;
/*      */       }
/*      */     }
/*      */     
/*      */     public StreamWithFilter withFilter(Function1 q) {
/*  527 */       return new StreamWithFilter(scala$collection$immutable$Stream$StreamWithFilter$$$outer(), (Function1<A, Object>)new Stream$StreamWithFilter$$anonfun$withFilter$1(this, (StreamWithFilter)q));
/*      */     }
/*      */     
/*      */     public class Stream$StreamWithFilter$$anonfun$withFilter$1 extends AbstractFunction1<A, Object> implements Serializable {
/*      */       public static final long serialVersionUID = 0L;
/*      */       
/*      */       private final Function1 q$1;
/*      */       
/*      */       public final boolean apply(Object x) {
/*  527 */         return (BoxesRunTime.unboxToBoolean(this.$outer.scala$collection$immutable$Stream$StreamWithFilter$$p.apply(x)) && BoxesRunTime.unboxToBoolean(this.q$1.apply(x)));
/*      */       }
/*      */       
/*      */       public Stream$StreamWithFilter$$anonfun$withFilter$1(Stream.StreamWithFilter $outer, Function1 q$1) {}
/*      */     }
/*      */   }
/*      */   
/*      */   public Iterator<A> iterator() {
/*  531 */     return new StreamIterator<A>(this);
/*      */   }
/*      */   
/*      */   public final <B> void foreach(Function1 f) {
/*      */     while (true) {
/*  546 */       if (isEmpty())
/*      */         return; 
/*  547 */       f.apply(head());
/*  548 */       this = (Stream)tail();
/*      */     } 
/*      */   }
/*      */   
/*      */   public final <B> B foldLeft(Object z, Function2 op) {
/*      */     while (true) {
/*  562 */       if (isEmpty())
/*  562 */         return (B)z; 
/*  563 */       z = op.apply(z, head());
/*  563 */       this = (Stream)tail();
/*      */     } 
/*      */   }
/*      */   
/*      */   public final <B> B reduceLeft(Function2 f) {
/*  574 */     if (isEmpty())
/*  574 */       throw new UnsupportedOperationException("empty.reduceLeft"); 
/*  576 */     Object reducedRes = head();
/*  577 */     Stream left = (Stream)tail();
/*      */     while (true) {
/*  578 */       if (left.isEmpty())
/*  582 */         return (B)reducedRes; 
/*      */       reducedRes = f.apply(reducedRes, left.head());
/*      */       left = (Stream)left.tail();
/*      */     } 
/*      */   }
/*      */   
/*      */   public Tuple2<Stream<A>, Stream<A>> partition(Function1 p) {
/*  603 */     return new Tuple2(filter((Function1<A, Object>)new Stream$$anonfun$partition$1(this, (Stream<A>)p)), filterNot((Function1)new Stream$$anonfun$partition$2(this, (Stream<A>)p)));
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$partition$1 extends AbstractFunction1<A, Object> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function1 p$2;
/*      */     
/*      */     public final boolean apply(Object x$1) {
/*  603 */       return BoxesRunTime.unboxToBoolean(this.p$2.apply(x$1));
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$partition$1(Stream $outer, Function1 p$2) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$partition$2 extends AbstractFunction1<A, Object> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function1 p$2;
/*      */     
/*      */     public final boolean apply(Object x$2) {
/*  603 */       return BoxesRunTime.unboxToBoolean(this.p$2.apply(x$2));
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$partition$2(Stream $outer, Function1 p$2) {}
/*      */   }
/*      */   
/*      */   public final <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  641 */     Stream$$anonfun$zip$1 stream$$anonfun$zip$1 = new Stream$$anonfun$zip$1(this, (Stream<A>)that);
/*  641 */     Tuple2 tuple2 = new Tuple2(head(), that.head());
/*  641 */     cons$ cons$ = cons$.MODULE$;
/*  641 */     return (bf.apply(repr()) instanceof StreamBuilder) ? ((isEmpty() || that.isEmpty()) ? (That)Empty$.MODULE$ : (That)new Cons<Tuple2>(tuple2, (Function0<Stream<Tuple2>>)stream$$anonfun$zip$1)) : 
/*      */       
/*  643 */       (That)IterableLike.class.zip(this, that, bf);
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$zip$1 extends AbstractFunction0<Stream<Tuple2<A1, B>>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final GenIterable that$2;
/*      */     
/*      */     public final Stream<Tuple2<A1, B>> apply() {
/*      */       return (Stream<Tuple2<A1, B>>)((Stream)this.$outer.tail()).zip((GenIterable)this.that$2.tail(), Stream$.MODULE$.canBuildFrom());
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$zip$1(Stream $outer, GenIterable that$2) {}
/*      */   }
/*      */   
/*      */   public <A1, That> That zipWithIndex(CanBuildFrom<Stream<?>, Tuple2<?, Object>, That> bf) {
/*  668 */     return zip((GenIterable)Stream$.MODULE$.from(0), bf);
/*      */   }
/*      */   
/*      */   private final void loop$3(String pre, Stream these, StringBuilder b$1, String sep$2, String end$1) {
/*      */     while (true) {
/*  687 */       if (these.isEmpty()) {
/*  687 */         b$1.append(end$1);
/*      */         break;
/*      */       } 
/*  689 */       b$1.append(pre).append(these.head());
/*  690 */       if (these.tailDefined()) {
/*  690 */         these = (Stream)these.tail();
/*  690 */         pre = sep$2;
/*      */         continue;
/*      */       } 
/*  691 */       b$1.append(sep$2).append("?").append(end$1);
/*      */       break;
/*      */     } 
/*      */   }
/*      */   
/*      */   public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  694 */     b.append(start);
/*  695 */     loop$3("", this, b, sep, end);
/*  696 */     return b;
/*      */   }
/*      */   
/*      */   public String mkString(String sep) {
/*  699 */     return mkString("", sep, "");
/*      */   }
/*      */   
/*      */   public String mkString() {
/*  700 */     return mkString("");
/*      */   }
/*      */   
/*      */   public String mkString(String start, String sep, String end) {
/*  702 */     force();
/*  703 */     return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*      */   }
/*      */   
/*      */   public String toString() {
/*  705 */     return TraversableOnce.class.mkString((TraversableOnce)this, (new StringBuilder()).append(stringPrefix()).append("(").toString(), ", ", ")");
/*      */   }
/*      */   
/*      */   public Tuple2<Stream<A>, Stream<A>> splitAt(int n) {
/*  707 */     return new Tuple2(take(n), drop(n));
/*      */   }
/*      */   
/*      */   public Stream<A> take(int n) {
/*  729 */     Stream$ stream$ = Stream$.MODULE$;
/*  730 */     Stream$$anonfun$take$1 stream$$anonfun$take$1 = new Stream$$anonfun$take$1(this);
/*  730 */     A a1 = head();
/*  730 */     cons$ cons$1 = cons$.MODULE$;
/*  731 */     Stream$$anonfun$take$2 stream$$anonfun$take$2 = new Stream$$anonfun$take$2(this, n);
/*  731 */     A a2 = head();
/*  731 */     cons$ cons$2 = cons$.MODULE$;
/*  731 */     return (n <= 0 || isEmpty()) ? Empty$.MODULE$ : ((n == 1) ? new Cons<A>(a1, (Function0<Stream<A>>)stream$$anonfun$take$1) : new Cons<A>(a2, (Function0<Stream<A>>)stream$$anonfun$take$2));
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$take$1 extends AbstractFunction0<Stream<scala.runtime.Nothing$>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     public final Stream<scala.runtime.Nothing$> apply() {
/*      */       return Stream$.MODULE$.empty();
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$take$1(Stream $outer) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$take$2 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final int n$1;
/*      */     
/*      */     public final Stream<A> apply() {
/*  731 */       return ((Stream<A>)this.$outer.tail()).take(this.n$1 - 1);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$take$2(Stream $outer, int n$1) {}
/*      */   }
/*      */   
/*      */   public final Stream<A> drop(int n) {
/*      */     while (true) {
/*  734 */       if (n <= 0 || isEmpty())
/*  734 */         return this; 
/*  735 */       n--;
/*  735 */       this = (Stream)tail();
/*      */     } 
/*      */   }
/*      */   
/*      */   public Stream<A> slice(int from, int until) {
/*  751 */     scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*  751 */     int lo = scala.runtime.RichInt$.MODULE$.max$extension(from, 0);
/*  752 */     return (until <= lo || isEmpty()) ? Stream$.MODULE$.<A>empty() : 
/*  753 */       drop(lo).take(until - lo);
/*      */   }
/*      */   
/*      */   public Stream<A> init() {
/*  766 */     Stream$$anonfun$init$1 stream$$anonfun$init$1 = new Stream$$anonfun$init$1(this);
/*  766 */     A a = head();
/*  766 */     cons$ cons$ = cons$.MODULE$;
/*  766 */     return isEmpty() ? (Stream<A>)TraversableLike.class.init(this) : (((SeqLike)tail()).isEmpty() ? Empty$.MODULE$ : new Cons<A>(a, (Function0<Stream<A>>)stream$$anonfun$init$1));
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$init$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     public final Stream<A> apply() {
/*  766 */       return ((Stream<A>)this.$outer.tail()).init();
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$init$1(Stream $outer) {}
/*      */   }
/*      */   
/*      */   public Stream<A> takeRight(int n) {
/*  778 */     Stream<A> these = this;
/*  779 */     Stream<A> lead = drop(n);
/*      */     while (true) {
/*  780 */       if (lead.isEmpty())
/*  784 */         return these; 
/*      */       these = (Stream<A>)these.tail();
/*      */       lead = (Stream<A>)lead.tail();
/*      */     } 
/*      */   }
/*      */   
/*      */   public Stream<A> takeWhile(Function1 p) {
/*  803 */     Stream$$anonfun$takeWhile$1 stream$$anonfun$takeWhile$1 = new Stream$$anonfun$takeWhile$1(this, (Stream<A>)p);
/*  803 */     A a = head();
/*  803 */     cons$ cons$ = cons$.MODULE$;
/*  803 */     return (!isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(head()))) ? new Cons<A>(a, (Function0<Stream<A>>)stream$$anonfun$takeWhile$1) : 
/*  804 */       Empty$.MODULE$;
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$takeWhile$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function1 p$3;
/*      */     
/*      */     public final Stream<A> apply() {
/*      */       return ((Stream<A>)this.$outer.tail()).takeWhile(this.p$3);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$takeWhile$1(Stream $outer, Function1 p$3) {}
/*      */   }
/*      */   
/*      */   public Stream<A> dropWhile(Function1 p) {
/*  824 */     Stream<A> these = this;
/*  825 */     for (; !these.isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(these.head())); these = (Stream)these.tail());
/*  826 */     return these;
/*      */   }
/*      */   
/*      */   public final Stream scala$collection$immutable$Stream$$loop$4(Set seen, Stream<A> rest) {
/*      */     while (true) {
/*  849 */       if (seen.apply(rest.head())) {
/*  849 */         rest = (Stream)rest.tail();
/*  849 */         seen = seen;
/*      */         continue;
/*      */       } 
/*  850 */       Stream$$anonfun$scala$collection$immutable$Stream$$loop$4$1 stream$$anonfun$scala$collection$immutable$Stream$$loop$4$1 = new Stream$$anonfun$scala$collection$immutable$Stream$$loop$4$1(this, seen, rest);
/*  850 */       A a = rest.head();
/*  850 */       cons$ cons$ = cons$.MODULE$;
/*  850 */       return rest.isEmpty() ? rest : new Cons<A>(a, (Function0<Stream<A>>)stream$$anonfun$scala$collection$immutable$Stream$$loop$4$1);
/*      */     } 
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$scala$collection$immutable$Stream$$loop$4$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Set seen$1;
/*      */     
/*      */     private final Stream rest$2;
/*      */     
/*      */     public final Stream<A> apply() {
/*  850 */       return this.$outer.scala$collection$immutable$Stream$$loop$4((Set)this.seen$1.$plus(this.rest$2.head()), (Stream)this.rest$2.tail());
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$scala$collection$immutable$Stream$$loop$4$1(Stream $outer, Set seen$1, Stream rest$2) {}
/*      */   }
/*      */   
/*      */   public Stream<A> distinct() {
/*  852 */     return scala$collection$immutable$Stream$$loop$4((Set)Set$.MODULE$.apply(Nil$.MODULE$), this);
/*      */   }
/*      */   
/*      */   public final Stream scala$collection$immutable$Stream$$loop$5(int len, Stream<A> these, Object elem$1) {
/*  883 */     Stream$$anonfun$scala$collection$immutable$Stream$$loop$5$1 stream$$anonfun$scala$collection$immutable$Stream$$loop$5$1 = new Stream$$anonfun$scala$collection$immutable$Stream$$loop$5$1(this, (Stream<A>)elem$1);
/*  883 */     Stream$ stream$ = Stream$.MODULE$;
/*  883 */     Stream$$anonfun$fill$1 stream$$anonfun$fill$1 = new Stream$$anonfun$fill$1(len, (Function0)stream$$anonfun$scala$collection$immutable$Stream$$loop$5$1);
/*  883 */     Object object = elem$1;
/*  883 */     cons$ cons$1 = cons$.MODULE$;
/*  884 */     Stream$$anonfun$scala$collection$immutable$Stream$$loop$5$2 stream$$anonfun$scala$collection$immutable$Stream$$loop$5$2 = new Stream$$anonfun$scala$collection$immutable$Stream$$loop$5$2(this, elem$1, len, these);
/*  884 */     A a = these.head();
/*  884 */     cons$ cons$2 = cons$.MODULE$;
/*  884 */     return these.isEmpty() ? ((len <= 0) ? Empty$.MODULE$ : new Cons(object, (Function0<Stream<Object>>)stream$$anonfun$fill$1)) : new Cons<A>(a, (Function0<Stream<A>>)stream$$anonfun$scala$collection$immutable$Stream$$loop$5$2);
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$scala$collection$immutable$Stream$$loop$5$1 extends AbstractFunction0<B> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     public final Object elem$1;
/*      */     
/*      */     public final B apply() {
/*      */       return (B)this.elem$1;
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$scala$collection$immutable$Stream$$loop$5$1(Stream $outer, Object elem$1) {}
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$scala$collection$immutable$Stream$$loop$5$2 extends AbstractFunction0<Stream<B>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Object elem$1;
/*      */     
/*      */     private final int len$1;
/*      */     
/*      */     private final Stream these$1;
/*      */     
/*      */     public final Stream<B> apply() {
/*  884 */       return this.$outer.scala$collection$immutable$Stream$$loop$5(this.len$1 - 1, (Stream)this.these$1.tail(), this.elem$1);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$scala$collection$immutable$Stream$$loop$5$2(Stream $outer, Object elem$1, int len$1, Stream these$1) {}
/*      */   }
/*      */   
/*      */   public <B, That> That padTo(int len, Object elem, CanBuildFrom bf) {
/*  886 */     return (bf.apply(repr()) instanceof StreamBuilder) ? (That)scala$collection$immutable$Stream$$loop$5(len, this, elem) : 
/*  887 */       (That)SeqLike.class.padTo(this, len, elem, bf);
/*      */   }
/*      */   
/*      */   public Stream<A> reverse() {
/*  911 */     ObjectRef result = new ObjectRef(Empty$.MODULE$);
/*  912 */     Stream these = this;
/*      */     while (true) {
/*  913 */       if (these.isEmpty())
/*  919 */         return (Stream<A>)result.elem; 
/*      */       Stream$$anonfun$1 stream$$anonfun$1 = new Stream$$anonfun$1(this, (Stream<A>)result);
/*      */       Stream$ stream$ = Stream$.MODULE$;
/*      */       Stream<?> r = (new ConsWrapper((Function0<Stream<?>>)stream$$anonfun$1)).$hash$colon$colon(these.head());
/*      */       r.tail();
/*      */       result.elem = r;
/*      */       these = (Stream)these.tail();
/*      */     } 
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final ObjectRef result$1;
/*      */     
/*      */     public final Stream<A> apply() {
/*      */       return (Stream<A>)this.result$1.elem;
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$1(Stream $outer, ObjectRef result$1) {}
/*      */   }
/*      */   
/*      */   public final Stream scala$collection$immutable$Stream$$flatten1$1(Traversable t, Function1 asTraversable$1) {
/*  937 */     Stream$$anonfun$scala$collection$immutable$Stream$$flatten1$1$1 stream$$anonfun$scala$collection$immutable$Stream$$flatten1$1$1 = new Stream$$anonfun$scala$collection$immutable$Stream$$flatten1$1$1(this, asTraversable$1, (Stream<A>)t);
/*  937 */     Object object = t.head();
/*  937 */     cons$ cons$ = cons$.MODULE$;
/*  939 */     return t.isEmpty() ? ((Stream)tail()).flatten(asTraversable$1) : new Cons(object, (Function0<Stream<Object>>)stream$$anonfun$scala$collection$immutable$Stream$$flatten1$1$1);
/*      */   }
/*      */   
/*      */   public class Stream$$anonfun$scala$collection$immutable$Stream$$flatten1$1$1 extends AbstractFunction0<Stream<B>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function1 asTraversable$1;
/*      */     
/*      */     private final Traversable t$1;
/*      */     
/*      */     public final Stream<B> apply() {
/*      */       return this.$outer.scala$collection$immutable$Stream$$flatten1$1((Traversable)this.t$1.tail(), this.asTraversable$1);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$scala$collection$immutable$Stream$$flatten1$1$1(Stream $outer, Function1 asTraversable$1, Traversable t$1) {}
/*      */   }
/*      */   
/*      */   public <B> Stream<B> flatten(Function1 asTraversable) {
/*  941 */     return isEmpty() ? Stream$.MODULE$.<B>empty() : 
/*  942 */       scala$collection$immutable$Stream$$flatten1$1(((GenTraversableOnce)asTraversable.apply(head())).seq().toTraversable(), asTraversable);
/*      */   }
/*      */   
/*      */   public Object view() {
/*  945 */     return new Stream$$anon$1(this);
/*      */   }
/*      */   
/*      */   public class Stream$$anon$1 implements StreamView<A, Stream<A>> {
/*      */     private Stream<A> underlying;
/*      */     
/*      */     private volatile boolean bitmap$0;
/*      */     
/*      */     public <B, That> That force(CanBuildFrom bf) {
/*  945 */       return (That)StreamViewLike$class.force(this, bf);
/*      */     }
/*      */     
/*      */     public <B> StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<B> newForced(Function0 xs) {
/*  945 */       return StreamViewLike$class.newForced(this, xs);
/*      */     }
/*      */     
/*      */     public <B> StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<B> newAppended(GenTraversable that) {
/*  945 */       return StreamViewLike$class.newAppended(this, that);
/*      */     }
/*      */     
/*      */     public <B> StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<B> newMapped(Function1 f) {
/*  945 */       return StreamViewLike$class.newMapped(this, f);
/*      */     }
/*      */     
/*      */     public <B> StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<B> newFlatMapped(Function1 f) {
/*  945 */       return StreamViewLike$class.newFlatMapped(this, f);
/*      */     }
/*      */     
/*      */     public StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A> newFiltered(Function1 p) {
/*  945 */       return StreamViewLike$class.newFiltered(this, p);
/*      */     }
/*      */     
/*      */     public StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A> newSliced(SliceInterval _endpoints) {
/*  945 */       return StreamViewLike$class.newSliced(this, _endpoints);
/*      */     }
/*      */     
/*      */     public StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A> newDroppedWhile(Function1 p) {
/*  945 */       return StreamViewLike$class.newDroppedWhile(this, p);
/*      */     }
/*      */     
/*      */     public StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A> newTakenWhile(Function1 p) {
/*  945 */       return StreamViewLike$class.newTakenWhile(this, p);
/*      */     }
/*      */     
/*      */     public <B> StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<Tuple2<A, B>> newZipped(GenIterable that) {
/*  945 */       return StreamViewLike$class.newZipped(this, that);
/*      */     }
/*      */     
/*      */     public <A1, B> StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<Tuple2<A1, B>> newZippedAll(GenIterable that, Object _thisElem, Object _thatElem) {
/*  945 */       return StreamViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
/*      */     }
/*      */     
/*      */     public StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A> newReversed() {
/*  945 */       return StreamViewLike$class.newReversed(this);
/*      */     }
/*      */     
/*      */     public <B> StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<B> newPatched(int _from, GenSeq _patch, int _replaced) {
/*  945 */       return StreamViewLike$class.newPatched(this, _from, _patch, _replaced);
/*      */     }
/*      */     
/*      */     public <B> StreamViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<B> newPrepended(Object elem) {
/*  945 */       return StreamViewLike$class.newPrepended(this, elem);
/*      */     }
/*      */     
/*      */     public String stringPrefix() {
/*  945 */       return StreamViewLike$class.stringPrefix(this);
/*      */     }
/*      */     
/*      */     public SeqViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A> newTaken(int n) {
/*  945 */       return SeqViewLike.class.newTaken(this, n);
/*      */     }
/*      */     
/*      */     public SeqViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A> newDropped(int n) {
/*  945 */       return SeqViewLike.class.newDropped(this, n);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> reverse() {
/*  945 */       return (StreamView<A, Stream<A>>)SeqViewLike.class.reverse(this);
/*      */     }
/*      */     
/*      */     public <B, That> That patch(int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/*  945 */       return (That)SeqViewLike.class.patch(this, from, patch, replaced, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That padTo(int len, Object elem, CanBuildFrom bf) {
/*  945 */       return (That)SeqViewLike.class.padTo(this, len, elem, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That reverseMap(Function1 f, CanBuildFrom bf) {
/*  945 */       return (That)SeqViewLike.class.reverseMap(this, f, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That updated(int index, Object elem, CanBuildFrom bf) {
/*  945 */       return (That)SeqViewLike.class.updated(this, index, elem, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/*  945 */       return (That)SeqViewLike.class.$plus$colon(this, elem, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That $colon$plus(Object elem, CanBuildFrom bf) {
/*  945 */       return (That)SeqViewLike.class.$colon$plus(this, elem, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That union(GenSeq that, CanBuildFrom bf) {
/*  945 */       return (That)SeqViewLike.class.union(this, that, bf);
/*      */     }
/*      */     
/*      */     public <B> StreamView<A, Stream<A>> diff(GenSeq that) {
/*  945 */       return (StreamView<A, Stream<A>>)SeqViewLike.class.diff(this, that);
/*      */     }
/*      */     
/*      */     public <B> StreamView<A, Stream<A>> intersect(GenSeq that) {
/*  945 */       return (StreamView<A, Stream<A>>)SeqViewLike.class.intersect(this, that);
/*      */     }
/*      */     
/*      */     public <B> StreamView<A, Stream<A>> sorted(Ordering ord) {
/*  945 */       return (StreamView<A, Stream<A>>)SeqViewLike.class.sorted(this, ord);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> drop(int n) {
/*  945 */       return (StreamView<A, Stream<A>>)IterableViewLike.class.drop((IterableViewLike)this, n);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> take(int n) {
/*  945 */       return (StreamView<A, Stream<A>>)IterableViewLike.class.take((IterableViewLike)this, n);
/*      */     }
/*      */     
/*      */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  945 */       return (That)IterableViewLike.class.zip((IterableViewLike)this, that, bf);
/*      */     }
/*      */     
/*      */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/*  945 */       return (That)IterableViewLike.class.zipWithIndex((IterableViewLike)this, bf);
/*      */     }
/*      */     
/*      */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  945 */       return (That)IterableViewLike.class.zipAll((IterableViewLike)this, that, thisElem, thatElem, bf);
/*      */     }
/*      */     
/*      */     public Iterator<StreamView<A, Stream<A>>> grouped(int size) {
/*  945 */       return IterableViewLike.class.grouped((IterableViewLike)this, size);
/*      */     }
/*      */     
/*      */     public Iterator<StreamView<A, Stream<A>>> sliding(int size, int step) {
/*  945 */       return IterableViewLike.class.sliding((IterableViewLike)this, size, step);
/*      */     }
/*      */     
/*      */     public Builder<A, StreamView<A, Stream<A>>> newBuilder() {
/*  945 */       return TraversableViewLike.class.newBuilder((TraversableViewLike)this);
/*      */     }
/*      */     
/*      */     public String viewIdentifier() {
/*  945 */       return TraversableViewLike.class.viewIdentifier((TraversableViewLike)this);
/*      */     }
/*      */     
/*      */     public String viewIdString() {
/*  945 */       return TraversableViewLike.class.viewIdString((TraversableViewLike)this);
/*      */     }
/*      */     
/*      */     public <B, That> That $plus$plus(GenTraversableOnce xs, CanBuildFrom bf) {
/*  945 */       return (That)TraversableViewLike.class.$plus$plus((TraversableViewLike)this, xs, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/*  945 */       return (That)TraversableViewLike.class.map((TraversableViewLike)this, f, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  945 */       return (That)TraversableViewLike.class.collect((TraversableViewLike)this, pf, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  945 */       return (That)TraversableViewLike.class.flatMap((TraversableViewLike)this, f, bf);
/*      */     }
/*      */     
/*      */     public <B> TraversableViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<B> flatten(Function1 asTraversable) {
/*  945 */       return TraversableViewLike.class.flatten((TraversableViewLike)this, asTraversable);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> filter(Function1 p) {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableViewLike.class.filter((TraversableViewLike)this, p);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> withFilter(Function1 p) {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableViewLike.class.withFilter((TraversableViewLike)this, p);
/*      */     }
/*      */     
/*      */     public Tuple2<StreamView<A, Stream<A>>, StreamView<A, Stream<A>>> partition(Function1 p) {
/*  945 */       return TraversableViewLike.class.partition((TraversableViewLike)this, p);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> init() {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableViewLike.class.init((TraversableViewLike)this);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> slice(int from, int until) {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableViewLike.class.slice((TraversableViewLike)this, from, until);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> dropWhile(Function1 p) {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableViewLike.class.dropWhile((TraversableViewLike)this, p);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> takeWhile(Function1 p) {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableViewLike.class.takeWhile((TraversableViewLike)this, p);
/*      */     }
/*      */     
/*      */     public Tuple2<StreamView<A, Stream<A>>, StreamView<A, Stream<A>>> span(Function1 p) {
/*  945 */       return TraversableViewLike.class.span((TraversableViewLike)this, p);
/*      */     }
/*      */     
/*      */     public Tuple2<StreamView<A, Stream<A>>, StreamView<A, Stream<A>>> splitAt(int n) {
/*  945 */       return TraversableViewLike.class.splitAt((TraversableViewLike)this, n);
/*      */     }
/*      */     
/*      */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  945 */       return (That)TraversableViewLike.class.scanLeft((TraversableViewLike)this, z, op, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  945 */       return (That)TraversableViewLike.class.scanRight((TraversableViewLike)this, z, op, bf);
/*      */     }
/*      */     
/*      */     public <K> Map<K, StreamView<A, Stream<A>>> groupBy(Function1 f) {
/*  945 */       return TraversableViewLike.class.groupBy((TraversableViewLike)this, f);
/*      */     }
/*      */     
/*      */     public <A1, A2> Tuple2<TraversableViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A1>, TraversableViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A2>> unzip(Function1 asPair) {
/*  945 */       return TraversableViewLike.class.unzip((TraversableViewLike)this, asPair);
/*      */     }
/*      */     
/*      */     public <A1, A2, A3> Tuple3<TraversableViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A1>, TraversableViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A2>, TraversableViewLike<A, Stream<A>, StreamView<A, Stream<A>>>.Transformed<A3>> unzip3(Function1 asTriple) {
/*  945 */       return TraversableViewLike.class.unzip3((TraversableViewLike)this, asTriple);
/*      */     }
/*      */     
/*      */     public String toString() {
/*  945 */       return TraversableViewLike.class.toString((TraversableViewLike)this);
/*      */     }
/*      */     
/*      */     public String viewToString() {
/*  945 */       return GenTraversableViewLike.class.viewToString((GenTraversableViewLike)this);
/*      */     }
/*      */     
/*      */     public Seq<A> thisSeq() {
/*  945 */       return ViewMkString.class.thisSeq((ViewMkString)this);
/*      */     }
/*      */     
/*      */     public String mkString() {
/*  945 */       return ViewMkString.class.mkString((ViewMkString)this);
/*      */     }
/*      */     
/*      */     public String mkString(String sep) {
/*  945 */       return ViewMkString.class.mkString((ViewMkString)this, sep);
/*      */     }
/*      */     
/*      */     public String mkString(String start, String sep, String end) {
/*  945 */       return ViewMkString.class.mkString((ViewMkString)this, start, sep, end);
/*      */     }
/*      */     
/*      */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  945 */       return ViewMkString.class.addString((ViewMkString)this, b, start, sep, end);
/*      */     }
/*      */     
/*      */     public GenericCompanion<Seq> companion() {
/*  945 */       return Seq.class.companion((Seq)this);
/*      */     }
/*      */     
/*      */     public Seq<A> seq() {
/*  945 */       return Seq.class.seq((Seq)this);
/*      */     }
/*      */     
/*      */     public Seq<A> thisCollection() {
/*  945 */       return SeqLike.class.thisCollection((SeqLike)this);
/*      */     }
/*      */     
/*      */     public Seq<A> toCollection(Object repr) {
/*  945 */       return SeqLike.class.toCollection((SeqLike)this, repr);
/*      */     }
/*      */     
/*      */     public Combiner<A, ParSeq<A>> parCombiner() {
/*  945 */       return SeqLike.class.parCombiner((SeqLike)this);
/*      */     }
/*      */     
/*      */     public int lengthCompare(int len) {
/*  945 */       return SeqLike.class.lengthCompare((SeqLike)this, len);
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/*  945 */       return SeqLike.class.isEmpty((SeqLike)this);
/*      */     }
/*      */     
/*      */     public int size() {
/*  945 */       return SeqLike.class.size((SeqLike)this);
/*      */     }
/*      */     
/*      */     public int segmentLength(Function1 p, int from) {
/*  945 */       return SeqLike.class.segmentLength((SeqLike)this, p, from);
/*      */     }
/*      */     
/*      */     public int indexWhere(Function1 p, int from) {
/*  945 */       return SeqLike.class.indexWhere((SeqLike)this, p, from);
/*      */     }
/*      */     
/*      */     public int lastIndexWhere(Function1 p, int end) {
/*  945 */       return SeqLike.class.lastIndexWhere((SeqLike)this, p, end);
/*      */     }
/*      */     
/*      */     public Iterator<StreamView<A, Stream<A>>> permutations() {
/*  945 */       return SeqLike.class.permutations((SeqLike)this);
/*      */     }
/*      */     
/*      */     public Iterator<StreamView<A, Stream<A>>> combinations(int n) {
/*  945 */       return SeqLike.class.combinations((SeqLike)this, n);
/*      */     }
/*      */     
/*      */     public Iterator<A> reverseIterator() {
/*  945 */       return SeqLike.class.reverseIterator((SeqLike)this);
/*      */     }
/*      */     
/*      */     public <B> boolean startsWith(GenSeq that, int offset) {
/*  945 */       return SeqLike.class.startsWith((SeqLike)this, that, offset);
/*      */     }
/*      */     
/*      */     public <B> boolean endsWith(GenSeq that) {
/*  945 */       return SeqLike.class.endsWith((SeqLike)this, that);
/*      */     }
/*      */     
/*      */     public <B> int indexOfSlice(GenSeq that) {
/*  945 */       return SeqLike.class.indexOfSlice((SeqLike)this, that);
/*      */     }
/*      */     
/*      */     public <B> int indexOfSlice(GenSeq that, int from) {
/*  945 */       return SeqLike.class.indexOfSlice((SeqLike)this, that, from);
/*      */     }
/*      */     
/*      */     public <B> int lastIndexOfSlice(GenSeq that) {
/*  945 */       return SeqLike.class.lastIndexOfSlice((SeqLike)this, that);
/*      */     }
/*      */     
/*      */     public <B> int lastIndexOfSlice(GenSeq that, int end) {
/*  945 */       return SeqLike.class.lastIndexOfSlice((SeqLike)this, that, end);
/*      */     }
/*      */     
/*      */     public <B> boolean containsSlice(GenSeq that) {
/*  945 */       return SeqLike.class.containsSlice((SeqLike)this, that);
/*      */     }
/*      */     
/*      */     public boolean contains(Object elem) {
/*  945 */       return SeqLike.class.contains((SeqLike)this, elem);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> distinct() {
/*  945 */       return (StreamView<A, Stream<A>>)SeqLike.class.distinct((SeqLike)this);
/*      */     }
/*      */     
/*      */     public <B> boolean corresponds(GenSeq that, Function2 p) {
/*  945 */       return SeqLike.class.corresponds((SeqLike)this, that, p);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> sortWith(Function2 lt) {
/*  945 */       return (StreamView<A, Stream<A>>)SeqLike.class.sortWith((SeqLike)this, lt);
/*      */     }
/*      */     
/*      */     public <B> StreamView<A, Stream<A>> sortBy(Function1 f, Ordering ord) {
/*  945 */       return (StreamView<A, Stream<A>>)SeqLike.class.sortBy((SeqLike)this, f, ord);
/*      */     }
/*      */     
/*      */     public Seq<A> toSeq() {
/*  945 */       return SeqLike.class.toSeq((SeqLike)this);
/*      */     }
/*      */     
/*      */     public Range indices() {
/*  945 */       return SeqLike.class.indices((SeqLike)this);
/*      */     }
/*      */     
/*      */     public Object view() {
/*  945 */       return SeqLike.class.view((SeqLike)this);
/*      */     }
/*      */     
/*      */     public SeqView<A, StreamView<A, Stream<A>>> view(int from, int until) {
/*  945 */       return SeqLike.class.view((SeqLike)this, from, until);
/*      */     }
/*      */     
/*      */     public boolean isDefinedAt(int idx) {
/*  945 */       return GenSeqLike.class.isDefinedAt((GenSeqLike)this, idx);
/*      */     }
/*      */     
/*      */     public int prefixLength(Function1 p) {
/*  945 */       return GenSeqLike.class.prefixLength((GenSeqLike)this, p);
/*      */     }
/*      */     
/*      */     public int indexWhere(Function1 p) {
/*  945 */       return GenSeqLike.class.indexWhere((GenSeqLike)this, p);
/*      */     }
/*      */     
/*      */     public <B> int indexOf(Object elem) {
/*  945 */       return GenSeqLike.class.indexOf((GenSeqLike)this, elem);
/*      */     }
/*      */     
/*      */     public <B> int indexOf(Object elem, int from) {
/*  945 */       return GenSeqLike.class.indexOf((GenSeqLike)this, elem, from);
/*      */     }
/*      */     
/*      */     public <B> int lastIndexOf(Object elem) {
/*  945 */       return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem);
/*      */     }
/*      */     
/*      */     public <B> int lastIndexOf(Object elem, int end) {
/*  945 */       return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem, end);
/*      */     }
/*      */     
/*      */     public int lastIndexWhere(Function1 p) {
/*  945 */       return GenSeqLike.class.lastIndexWhere((GenSeqLike)this, p);
/*      */     }
/*      */     
/*      */     public <B> boolean startsWith(GenSeq that) {
/*  945 */       return GenSeqLike.class.startsWith((GenSeqLike)this, that);
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  945 */       return GenSeqLike.class.hashCode((GenSeqLike)this);
/*      */     }
/*      */     
/*      */     public boolean equals(Object that) {
/*  945 */       return GenSeqLike.class.equals((GenSeqLike)this, that);
/*      */     }
/*      */     
/*      */     public <U> void foreach(Function1 f) {
/*  945 */       IterableLike.class.foreach((IterableLike)this, f);
/*      */     }
/*      */     
/*      */     public boolean forall(Function1 p) {
/*  945 */       return IterableLike.class.forall((IterableLike)this, p);
/*      */     }
/*      */     
/*      */     public boolean exists(Function1 p) {
/*  945 */       return IterableLike.class.exists((IterableLike)this, p);
/*      */     }
/*      */     
/*      */     public Option<A> find(Function1 p) {
/*  945 */       return IterableLike.class.find((IterableLike)this, p);
/*      */     }
/*      */     
/*      */     public <B> B foldRight(Object z, Function2 op) {
/*  945 */       return (B)IterableLike.class.foldRight((IterableLike)this, z, op);
/*      */     }
/*      */     
/*      */     public <B> B reduceRight(Function2 op) {
/*  945 */       return (B)IterableLike.class.reduceRight((IterableLike)this, op);
/*      */     }
/*      */     
/*      */     public Iterable<A> toIterable() {
/*  945 */       return IterableLike.class.toIterable((IterableLike)this);
/*      */     }
/*      */     
/*      */     public Iterator<A> toIterator() {
/*  945 */       return IterableLike.class.toIterator((IterableLike)this);
/*      */     }
/*      */     
/*      */     public A head() {
/*  945 */       return (A)IterableLike.class.head((IterableLike)this);
/*      */     }
/*      */     
/*      */     public Iterator<StreamView<A, Stream<A>>> sliding(int size) {
/*  945 */       return IterableLike.class.sliding((IterableLike)this, size);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> takeRight(int n) {
/*  945 */       return (StreamView<A, Stream<A>>)IterableLike.class.takeRight((IterableLike)this, n);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> dropRight(int n) {
/*  945 */       return (StreamView<A, Stream<A>>)IterableLike.class.dropRight((IterableLike)this, n);
/*      */     }
/*      */     
/*      */     public <B> void copyToArray(Object xs, int start, int len) {
/*  945 */       IterableLike.class.copyToArray((IterableLike)this, xs, start, len);
/*      */     }
/*      */     
/*      */     public <B> boolean sameElements(GenIterable that) {
/*  945 */       return IterableLike.class.sameElements((IterableLike)this, that);
/*      */     }
/*      */     
/*      */     public Stream<A> toStream() {
/*  945 */       return IterableLike.class.toStream((IterableLike)this);
/*      */     }
/*      */     
/*      */     public boolean canEqual(Object that) {
/*  945 */       return IterableLike.class.canEqual((IterableLike)this, that);
/*      */     }
/*      */     
/*      */     public <B> Builder<B, Seq<B>> genericBuilder() {
/*  945 */       return GenericTraversableTemplate.class.genericBuilder((GenericTraversableTemplate)this);
/*      */     }
/*      */     
/*      */     public <B> Seq<Seq<B>> transpose(Function1 asTraversable) {
/*  945 */       return (Seq<Seq<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> repr() {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableLike.class.repr((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public final boolean isTraversableAgain() {
/*  945 */       return TraversableLike.class.isTraversableAgain((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public boolean hasDefiniteSize() {
/*  945 */       return TraversableLike.class.hasDefiniteSize((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/*  945 */       return (That)TraversableLike.class.$plus$plus$colon((TraversableLike)this, that, bf);
/*      */     }
/*      */     
/*      */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/*  945 */       return (That)TraversableLike.class.$plus$plus$colon((TraversableLike)this, that, bf);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> filterNot(Function1 p) {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableLike.class.filterNot((TraversableLike)this, p);
/*      */     }
/*      */     
/*      */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/*  945 */       return (That)TraversableLike.class.scan((TraversableLike)this, z, op, cbf);
/*      */     }
/*      */     
/*      */     public Option<A> headOption() {
/*  945 */       return TraversableLike.class.headOption((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> tail() {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableLike.class.tail((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public A last() {
/*  945 */       return (A)TraversableLike.class.last((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public Option<A> lastOption() {
/*  945 */       return TraversableLike.class.lastOption((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> sliceWithKnownDelta(int from, int until, int delta) {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableLike.class.sliceWithKnownDelta((TraversableLike)this, from, until, delta);
/*      */     }
/*      */     
/*      */     public StreamView<A, Stream<A>> sliceWithKnownBound(int from, int until) {
/*  945 */       return (StreamView<A, Stream<A>>)TraversableLike.class.sliceWithKnownBound((TraversableLike)this, from, until);
/*      */     }
/*      */     
/*      */     public Iterator<StreamView<A, Stream<A>>> tails() {
/*  945 */       return TraversableLike.class.tails((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public Iterator<StreamView<A, Stream<A>>> inits() {
/*  945 */       return TraversableLike.class.inits((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public Traversable<A> toTraversable() {
/*  945 */       return TraversableLike.class.toTraversable((TraversableLike)this);
/*      */     }
/*      */     
/*      */     public <Col> Col to(CanBuildFrom cbf) {
/*  945 */       return (Col)TraversableLike.class.to((TraversableLike)this, cbf);
/*      */     }
/*      */     
/*      */     public ParSeq<A> par() {
/*  945 */       return (ParSeq<A>)Parallelizable.class.par((Parallelizable)this);
/*      */     }
/*      */     
/*      */     public List<A> reversed() {
/*  945 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*      */     }
/*      */     
/*      */     public boolean nonEmpty() {
/*  945 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*      */     }
/*      */     
/*      */     public int count(Function1 p) {
/*  945 */       return TraversableOnce.class.count((TraversableOnce)this, p);
/*      */     }
/*      */     
/*      */     public <B> Option<B> collectFirst(PartialFunction pf) {
/*  945 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*      */     }
/*      */     
/*      */     public <B> B $div$colon(Object z, Function2 op) {
/*  945 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*      */     }
/*      */     
/*      */     public <B> B $colon$bslash(Object z, Function2 op) {
/*  945 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*      */     }
/*      */     
/*      */     public <B> B foldLeft(Object z, Function2 op) {
/*  945 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*      */     }
/*      */     
/*      */     public <B> B reduceLeft(Function2 op) {
/*  945 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*      */     }
/*      */     
/*      */     public <B> Option<B> reduceLeftOption(Function2 op) {
/*  945 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*      */     }
/*      */     
/*      */     public <B> Option<B> reduceRightOption(Function2 op) {
/*  945 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*      */     }
/*      */     
/*      */     public <A1> A1 reduce(Function2 op) {
/*  945 */       return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*      */     }
/*      */     
/*      */     public <A1> Option<A1> reduceOption(Function2 op) {
/*  945 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*      */     }
/*      */     
/*      */     public <A1> A1 fold(Object z, Function2 op) {
/*  945 */       return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*      */     }
/*      */     
/*      */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  945 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*      */     }
/*      */     
/*      */     public <B> B sum(Numeric num) {
/*  945 */       return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*      */     }
/*      */     
/*      */     public <B> B product(Numeric num) {
/*  945 */       return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*      */     }
/*      */     
/*      */     public <B> A min(Ordering cmp) {
/*  945 */       return (A)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*      */     }
/*      */     
/*      */     public <B> A max(Ordering cmp) {
/*  945 */       return (A)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*      */     }
/*      */     
/*      */     public <B> A maxBy(Function1 f, Ordering cmp) {
/*  945 */       return (A)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*      */     }
/*      */     
/*      */     public <B> A minBy(Function1 f, Ordering cmp) {
/*  945 */       return (A)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*      */     }
/*      */     
/*      */     public <B> void copyToBuffer(Buffer dest) {
/*  945 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*      */     }
/*      */     
/*      */     public <B> void copyToArray(Object xs, int start) {
/*  945 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*      */     }
/*      */     
/*      */     public <B> void copyToArray(Object xs) {
/*  945 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*      */     }
/*      */     
/*      */     public <B> Object toArray(ClassTag evidence$1) {
/*  945 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*      */     }
/*      */     
/*      */     public List<A> toList() {
/*  945 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*      */     }
/*      */     
/*      */     public IndexedSeq<A> toIndexedSeq() {
/*  945 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*      */     }
/*      */     
/*      */     public <B> Buffer<B> toBuffer() {
/*  945 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*      */     }
/*      */     
/*      */     public <B> Set<B> toSet() {
/*  945 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*      */     }
/*      */     
/*      */     public Vector<A> toVector() {
/*  945 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*      */     }
/*      */     
/*      */     public <T, U> Map<T, U> toMap(scala.Predef$.less.colon.less ev) {
/*  945 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*      */     }
/*      */     
/*      */     public StringBuilder addString(StringBuilder b, String sep) {
/*  945 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*      */     }
/*      */     
/*      */     public StringBuilder addString(StringBuilder b) {
/*  945 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*      */     }
/*      */     
/*      */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  945 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*      */     }
/*      */     
/*      */     public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/*  945 */       return PartialFunction.class.orElse((PartialFunction)this, that);
/*      */     }
/*      */     
/*      */     public <C> PartialFunction<Object, C> andThen(Function1 k) {
/*  945 */       return PartialFunction.class.andThen((PartialFunction)this, k);
/*      */     }
/*      */     
/*      */     public Function1<Object, Option<A>> lift() {
/*  945 */       return PartialFunction.class.lift((PartialFunction)this);
/*      */     }
/*      */     
/*      */     public <A1, B1> B1 applyOrElse(Object x, Function1 default) {
/*  945 */       return (B1)PartialFunction.class.applyOrElse((PartialFunction)this, x, default);
/*      */     }
/*      */     
/*      */     public <U> Function1<Object, Object> runWith(Function1 action) {
/*  945 */       return PartialFunction.class.runWith((PartialFunction)this, action);
/*      */     }
/*      */     
/*      */     public boolean apply$mcZD$sp(double v1) {
/*  945 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public double apply$mcDD$sp(double v1) {
/*  945 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public float apply$mcFD$sp(double v1) {
/*  945 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public int apply$mcID$sp(double v1) {
/*  945 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public long apply$mcJD$sp(double v1) {
/*  945 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public void apply$mcVD$sp(double v1) {
/*  945 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public boolean apply$mcZF$sp(float v1) {
/*  945 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public double apply$mcDF$sp(float v1) {
/*  945 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public float apply$mcFF$sp(float v1) {
/*  945 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public int apply$mcIF$sp(float v1) {
/*  945 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public long apply$mcJF$sp(float v1) {
/*  945 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public void apply$mcVF$sp(float v1) {
/*  945 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public boolean apply$mcZI$sp(int v1) {
/*  945 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public double apply$mcDI$sp(int v1) {
/*  945 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public float apply$mcFI$sp(int v1) {
/*  945 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public int apply$mcII$sp(int v1) {
/*  945 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public long apply$mcJI$sp(int v1) {
/*  945 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public void apply$mcVI$sp(int v1) {
/*  945 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public boolean apply$mcZJ$sp(long v1) {
/*  945 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public double apply$mcDJ$sp(long v1) {
/*  945 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public float apply$mcFJ$sp(long v1) {
/*  945 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public int apply$mcIJ$sp(long v1) {
/*  945 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public long apply$mcJJ$sp(long v1) {
/*  945 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public void apply$mcVJ$sp(long v1) {
/*  945 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, A> compose(Function1 g) {
/*  945 */       return Function1.class.compose((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*  945 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*  945 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*      */     }
/*      */     
/*      */     public Stream$$anon$1(Stream $outer) {
/*  945 */       Function1.class.$init$((Function1)this);
/*  945 */       PartialFunction.class.$init$((PartialFunction)this);
/*  945 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  945 */       TraversableOnce.class.$init$((TraversableOnce)this);
/*  945 */       Parallelizable.class.$init$((Parallelizable)this);
/*  945 */       TraversableLike.class.$init$((TraversableLike)this);
/*  945 */       GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/*  945 */       GenTraversable.class.$init$((GenTraversable)this);
/*  945 */       Traversable.class.$init$((Traversable)this);
/*  945 */       GenIterable.class.$init$((GenIterable)this);
/*  945 */       IterableLike.class.$init$((IterableLike)this);
/*  945 */       Iterable.class.$init$((Iterable)this);
/*  945 */       GenSeqLike.class.$init$((GenSeqLike)this);
/*  945 */       GenSeq.class.$init$((GenSeq)this);
/*  945 */       SeqLike.class.$init$((SeqLike)this);
/*  945 */       Seq.class.$init$((Seq)this);
/*  945 */       ViewMkString.class.$init$((ViewMkString)this);
/*  945 */       GenTraversableViewLike.class.$init$((GenTraversableViewLike)this);
/*  945 */       TraversableViewLike.class.$init$((TraversableViewLike)this);
/*  945 */       GenIterableViewLike.class.$init$((GenIterableViewLike)this);
/*  945 */       IterableViewLike.class.$init$((IterableViewLike)this);
/*  945 */       GenSeqViewLike.class.$init$((GenSeqViewLike)this);
/*  945 */       SeqViewLike.class.$init$(this);
/*  945 */       StreamViewLike$class.$init$(this);
/*      */     }
/*      */     
/*      */     private Stream underlying$lzycompute() {
/*  946 */       synchronized (this) {
/*  946 */         if (!this.bitmap$0) {
/*  946 */           this.underlying = (Stream<A>)this.$outer.repr();
/*  946 */           this.bitmap$0 = true;
/*      */         } 
/*      */         /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/Stream$$anon$1}} */
/*  946 */         return this.underlying;
/*      */       } 
/*      */     }
/*      */     
/*      */     public Stream<A> underlying() {
/*  946 */       return this.bitmap$0 ? this.underlying : underlying$lzycompute();
/*      */     }
/*      */     
/*      */     public Iterator<A> iterator() {
/*  947 */       return this.$outer.iterator();
/*      */     }
/*      */     
/*      */     public int length() {
/*  948 */       return this.$outer.length();
/*      */     }
/*      */     
/*      */     public A apply(int idx) {
/*  949 */       return this.$outer.apply(idx);
/*      */     }
/*      */   }
/*      */   
/*      */   public String stringPrefix() {
/*  954 */     return "Stream";
/*      */   }
/*      */   
/*      */   public static <T> Stream<T> range(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*      */     return Stream$.MODULE$.range(paramT1, paramT2, paramT3, paramIntegral);
/*      */   }
/*      */   
/*      */   public static <A> Stream<A> tabulate(int paramInt, Function1<Object, A> paramFunction1) {
/*      */     return Stream$.MODULE$.tabulate(paramInt, paramFunction1);
/*      */   }
/*      */   
/*      */   public static <A> Stream<A> fill(int paramInt, Function0<A> paramFunction0) {
/*      */     return Stream$.MODULE$.fill(paramInt, paramFunction0);
/*      */   }
/*      */   
/*      */   public static <A> Stream<A> continually(Function0<A> paramFunction0) {
/*      */     return Stream$.MODULE$.continually(paramFunction0);
/*      */   }
/*      */   
/*      */   public static Stream<Object> from(int paramInt) {
/*      */     return Stream$.MODULE$.from(paramInt);
/*      */   }
/*      */   
/*      */   public static Stream<Object> from(int paramInt1, int paramInt2) {
/*      */     return Stream$.MODULE$.from(paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */   public static <A> Stream<A> iterate(A paramA, int paramInt, Function1<A, A> paramFunction1) {
/*      */     return Stream$.MODULE$.iterate(paramA, paramInt, paramFunction1);
/*      */   }
/*      */   
/*      */   public static <A> Stream<A> iterate(A paramA, Function1<A, A> paramFunction1) {
/*      */     return Stream$.MODULE$.iterate(paramA, paramFunction1);
/*      */   }
/*      */   
/*      */   public static <A> ConsWrapper<A> consWrapper(Function0<Stream<A>> paramFunction0) {
/*      */     return Stream$.MODULE$.consWrapper(paramFunction0);
/*      */   }
/*      */   
/*      */   public static <A> Stream<A> empty() {
/*      */     return Stream$.MODULE$.empty();
/*      */   }
/*      */   
/*      */   public static <A> CanBuildFrom<Stream<?>, A, Stream<A>> canBuildFrom() {
/*      */     return Stream$.MODULE$.canBuildFrom();
/*      */   }
/*      */   
/*      */   public static <A> Some<Stream<A>> unapplySeq(Stream<A> paramStream) {
/*      */     return Stream$.MODULE$.unapplySeq(paramStream);
/*      */   }
/*      */   
/*      */   public static <A> Stream<A> iterate(A paramA, int paramInt, Function1<A, A> paramFunction1) {
/*      */     return (Stream<A>)Stream$.MODULE$.iterate(paramA, paramInt, paramFunction1);
/*      */   }
/*      */   
/*      */   public static <T> Stream<T> range(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*      */     return (Stream<T>)Stream$.MODULE$.range(paramT1, paramT2, paramT3, paramIntegral);
/*      */   }
/*      */   
/*      */   public static <T> Stream<T> range(T paramT1, T paramT2, Integral<T> paramIntegral) {
/*      */     return (Stream<T>)Stream$.MODULE$.range(paramT1, paramT2, paramIntegral);
/*      */   }
/*      */   
/*      */   public static <A> Stream<Stream<Stream<Stream<Stream<A>>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function5<Object, Object, Object, Object, Object, A> paramFunction5) {
/*      */     return (Stream<Stream<Stream<Stream<Stream<A>>>>>)Stream$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction5);
/*      */   }
/*      */   
/*      */   public static <A> Stream<Stream<Stream<Stream<A>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function4<Object, Object, Object, Object, A> paramFunction4) {
/*      */     return (Stream<Stream<Stream<Stream<A>>>>)Stream$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction4);
/*      */   }
/*      */   
/*      */   public static <A> Stream<Stream<Stream<A>>> tabulate(int paramInt1, int paramInt2, int paramInt3, Function3<Object, Object, Object, A> paramFunction3) {
/*      */     return (Stream<Stream<Stream<A>>>)Stream$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramFunction3);
/*      */   }
/*      */   
/*      */   public static <A> Stream<Stream<A>> tabulate(int paramInt1, int paramInt2, Function2<Object, Object, A> paramFunction2) {
/*      */     return (Stream<Stream<A>>)Stream$.MODULE$.tabulate(paramInt1, paramInt2, paramFunction2);
/*      */   }
/*      */   
/*      */   public static <A> Stream<A> tabulate(int paramInt, Function1<Object, A> paramFunction1) {
/*      */     return (Stream<A>)Stream$.MODULE$.tabulate(paramInt, paramFunction1);
/*      */   }
/*      */   
/*      */   public static <A> Stream<Stream<Stream<Stream<Stream<A>>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function0<A> paramFunction0) {
/*      */     return (Stream<Stream<Stream<Stream<Stream<A>>>>>)Stream$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction0);
/*      */   }
/*      */   
/*      */   public static <A> Stream<Stream<Stream<Stream<A>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function0<A> paramFunction0) {
/*      */     return (Stream<Stream<Stream<Stream<A>>>>)Stream$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction0);
/*      */   }
/*      */   
/*      */   public static <A> Stream<Stream<Stream<A>>> fill(int paramInt1, int paramInt2, int paramInt3, Function0<A> paramFunction0) {
/*      */     return (Stream<Stream<Stream<A>>>)Stream$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramFunction0);
/*      */   }
/*      */   
/*      */   public static <A> Stream<Stream<A>> fill(int paramInt1, int paramInt2, Function0<A> paramFunction0) {
/*      */     return (Stream<Stream<A>>)Stream$.MODULE$.fill(paramInt1, paramInt2, paramFunction0);
/*      */   }
/*      */   
/*      */   public static <A> Stream<A> fill(int paramInt, Function0<A> paramFunction0) {
/*      */     return (Stream<A>)Stream$.MODULE$.fill(paramInt, paramFunction0);
/*      */   }
/*      */   
/*      */   public static <A> Stream<A> concat(Seq<Traversable<A>> paramSeq) {
/*      */     return (Stream<A>)Stream$.MODULE$.concat(paramSeq);
/*      */   }
/*      */   
/*      */   public static GenTraversableFactory<Stream>.GenericCanBuildFrom<scala.runtime.Nothing$> ReusableCBF() {
/*      */     return Stream$.MODULE$.ReusableCBF();
/*      */   }
/*      */   
/*      */   public static <A> Stream<A> empty() {
/*      */     return (Stream<A>)Stream$.MODULE$.empty();
/*      */   }
/*      */   
/*      */   public abstract boolean isEmpty();
/*      */   
/*      */   public abstract A head();
/*      */   
/*      */   public abstract boolean tailDefined();
/*      */   
/*      */   public static class StreamCanBuildFrom<A> extends GenTraversableFactory<Stream>.GenericCanBuildFrom<A> {
/*      */     public StreamCanBuildFrom() {
/* 1008 */       super((GenTraversableFactory)Stream$.MODULE$);
/*      */     }
/*      */   }
/*      */   
/*      */   public static class StreamBuilder<A> extends LazyBuilder<A, Stream<A>> {
/*      */     public Stream<A> result() {
/*      */       Stream prefix1;
/*      */       ObjectRef nonEmptyPrefix1;
/*      */       Stream stream1;
/*      */       Stream$StreamBuilder$$anonfun$result$1 stream$StreamBuilder$$anonfun$result$1;
/*      */       Stream.StreamCanBuildFrom streamCanBuildFrom;
/* 1023 */       for (Stream$ stream$1 = Stream$.MODULE$; !((Stream)nonEmptyPrefix1.elem).isEmpty() && prefix1.isEmpty(); ) {
/* 1023 */         nonEmptyPrefix1.elem = ((Stream)nonEmptyPrefix1.elem).tail();
/* 1023 */         if (!((Stream)nonEmptyPrefix1.elem).isEmpty())
/* 1023 */           prefix1 = ((GenTraversableOnce)((TraversableOnce)((Stream<TraversableOnce>)nonEmptyPrefix1.elem).head()).toStream()).toStream(); 
/*      */       } 
/* 1023 */       Stream$ stream$2 = Stream$.MODULE$;
/* 1023 */       return (streamCanBuildFrom.apply(stream1.repr()) instanceof StreamBuilder) ? (stream1.isEmpty() ? Stream.Empty$.MODULE$ : (((Stream)nonEmptyPrefix1.elem).isEmpty() ? Stream.Empty$.MODULE$ : prefix1.append((Function0)new Stream$$anonfun$flatMap$1(stream1, (Function1)stream$StreamBuilder$$anonfun$result$1, (Stream<A>)nonEmptyPrefix1)))) : (Stream<A>)TraversableLike.class.flatMap(stream1, (Function1)stream$StreamBuilder$$anonfun$result$1, (CanBuildFrom)streamCanBuildFrom);
/*      */     }
/*      */     
/*      */     public class Stream$StreamBuilder$$anonfun$result$1 extends AbstractFunction1<TraversableOnce<A>, Stream<A>> implements Serializable {
/*      */       public static final long serialVersionUID = 0L;
/*      */       
/*      */       public final Stream<A> apply(TraversableOnce x$3) {
/* 1023 */         return x$3.toStream();
/*      */       }
/*      */       
/*      */       public Stream$StreamBuilder$$anonfun$result$1(Stream.StreamBuilder $outer) {}
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Empty$ extends Stream<scala.runtime.Nothing$> implements Serializable {
/*      */     public static final Empty$ MODULE$;
/*      */     
/*      */     private Object readResolve() {
/* 1026 */       return MODULE$;
/*      */     }
/*      */     
/*      */     public Empty$() {
/* 1026 */       MODULE$ = this;
/*      */     }
/*      */     
/*      */     public boolean isEmpty() {
/* 1027 */       return true;
/*      */     }
/*      */     
/*      */     public scala.runtime.Nothing$ head() {
/* 1028 */       throw new NoSuchElementException("head of empty stream");
/*      */     }
/*      */     
/*      */     public scala.runtime.Nothing$ tail() {
/* 1029 */       throw new UnsupportedOperationException("tail of empty stream");
/*      */     }
/*      */     
/*      */     public boolean tailDefined() {
/* 1030 */       return false;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class ConsWrapper<A> {
/*      */     private final Function0<Stream<A>> tl;
/*      */     
/*      */     public ConsWrapper(Function0<Stream<A>> tl) {}
/*      */     
/*      */     public Stream<A> $hash$colon$colon(Object hd) {
/* 1043 */       Function0<Stream<A>> function0 = this.tl;
/* 1043 */       Stream.cons$ cons$ = Stream.cons$.MODULE$;
/* 1043 */       return new Stream.Cons<A>((A)hd, function0);
/*      */     }
/*      */     
/*      */     public Stream<A> $hash$colon$colon$colon(Stream prefix) {
/* 1044 */       return prefix.append(this.tl);
/*      */     }
/*      */   }
/*      */   
/*      */   public static class $hash$colon$colon$ {
/*      */     public static final $hash$colon$colon$ MODULE$;
/*      */     
/*      */     public $hash$colon$colon$() {
/* 1055 */       MODULE$ = this;
/*      */     }
/*      */     
/*      */     public <A> Option<Tuple2<A, Stream<A>>> unapply(Stream xs) {
/* 1057 */       return xs.isEmpty() ? (Option<Tuple2<A, Stream<A>>>)scala.None$.MODULE$ : 
/* 1058 */         (Option<Tuple2<A, Stream<A>>>)new Some(new Tuple2(xs.head(), xs.tail()));
/*      */     }
/*      */   }
/*      */   
/*      */   public static class cons$ {
/*      */     public static final cons$ MODULE$;
/*      */     
/*      */     public cons$() {
/* 1063 */       MODULE$ = this;
/*      */     }
/*      */     
/*      */     public <A> Stream.Cons<A> apply(Object hd, Function0<Stream<A>> tl) {
/* 1069 */       return new Stream.Cons<A>((A)hd, tl);
/*      */     }
/*      */     
/*      */     public <A> Option<Tuple2<A, Stream<A>>> unapply(Stream<A> xs) {
/* 1072 */       return Stream.$hash$colon$colon$.MODULE$.unapply(xs);
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Cons<A> extends Stream<A> implements Serializable {
/*      */     public static final long serialVersionUID = -602202424901551803L;
/*      */     
/*      */     private final A hd;
/*      */     
/*      */     private final Function0<Stream<A>> tl;
/*      */     
/*      */     private volatile Stream<A> tlVal;
/*      */     
/*      */     public Cons(Object hd, Function0<Stream<A>> tl) {}
/*      */     
/*      */     public boolean isEmpty() {
/* 1078 */       return false;
/*      */     }
/*      */     
/*      */     public A head() {
/* 1079 */       return this.hd;
/*      */     }
/*      */     
/*      */     public boolean tailDefined() {
/* 1081 */       return (this.tlVal != null);
/*      */     }
/*      */     
/*      */     public Stream<A> tail() {
/* 1083 */       if (!tailDefined())
/* 1084 */         synchronized (this) {
/* 1085 */           if (!tailDefined())
/* 1085 */             this.tlVal = (Stream<A>)this.tl.apply(); 
/*      */           /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/collection/immutable/Stream}.Lscala/collection/immutable/Stream$Cons;}} */
/* 1088 */           return this.tlVal;
/*      */         }  
/* 1088 */       return this.tlVal;
/*      */     }
/*      */   }
/*      */   
/*      */   public static class Stream$$anonfun$iterate$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Object start$2;
/*      */     
/*      */     private final Function1 f$6;
/*      */     
/*      */     public final Stream<A> apply() {
/* 1098 */       return Stream$.MODULE$.iterate((A)this.f$6.apply(this.start$2), this.f$6);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$iterate$1(Object start$2, Function1 f$6) {}
/*      */   }
/*      */   
/*      */   public static class Stream$$anonfun$from$1 extends AbstractFunction0<Stream<Object>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final int start$1;
/*      */     
/*      */     private final int step$1;
/*      */     
/*      */     public final Stream<Object> apply() {
/* 1112 */       return Stream$.MODULE$.from(this.start$1 + this.step$1, this.step$1);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$from$1(int start$1, int step$1) {}
/*      */   }
/*      */   
/*      */   public static class Stream$$anonfun$continually$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Function0 elem$3;
/*      */     
/*      */     public final Stream<A> apply() {
/* 1129 */       return Stream$.MODULE$.continually(this.elem$3);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$continually$1(Function0 elem$3) {}
/*      */   }
/*      */   
/*      */   public static class Stream$$anonfun$fill$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final int n$2;
/*      */     
/*      */     private final Function0 elem$2;
/*      */     
/*      */     public final Stream<A> apply() {
/* 1132 */       return Stream$.MODULE$.fill(this.n$2 - 1, this.elem$2);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$fill$1(int n$2, Function0 elem$2) {}
/*      */   }
/*      */   
/*      */   public static class Stream$$anonfun$scala$collection$immutable$Stream$$loop$6$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final int n$3;
/*      */     
/*      */     private final Function1 f$7;
/*      */     
/*      */     private final int i$1;
/*      */     
/*      */     public final Stream<A> apply() {
/* 1136 */       return Stream$.MODULE$.scala$collection$immutable$Stream$$loop$6(this.i$1 + 1, this.n$3, this.f$7);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$scala$collection$immutable$Stream$$loop$6$1(int n$3, Function1 f$7, int i$1) {}
/*      */   }
/*      */   
/*      */   public static class Stream$$anonfun$range$1 extends AbstractFunction0<Stream<T>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Object start$3;
/*      */     
/*      */     private final Object end$2;
/*      */     
/*      */     private final Object step$2;
/*      */     
/*      */     private final Integral evidence$1$1;
/*      */     
/*      */     private final Integral num$1;
/*      */     
/*      */     public final Stream<T> apply() {
/* 1145 */       return Stream$.MODULE$.range((T)this.num$1.mkNumericOps(this.start$3).$plus(this.step$2), (T)this.end$2, (T)this.step$2, this.evidence$1$1);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$range$1(Object start$3, Object end$2, Object step$2, Integral evidence$1$1, Integral num$1) {}
/*      */   }
/*      */   
/*      */   public static class Stream$$anonfun$filteredTail$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Stream stream$2;
/*      */     
/*      */     private final Function1 p$1;
/*      */     
/*      */     public final Stream<A> apply() {
/* 1149 */       return ((Stream<A>)this.stream$2.tail()).filter(this.p$1);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$filteredTail$1(Stream stream$2, Function1 p$1) {}
/*      */   }
/*      */   
/*      */   public static class Stream$$anonfun$collectedTail$1 extends AbstractFunction0<Stream<B>> implements Serializable {
/*      */     public static final long serialVersionUID = 0L;
/*      */     
/*      */     private final Stream stream$1;
/*      */     
/*      */     private final PartialFunction pf$1;
/*      */     
/*      */     private final CanBuildFrom bf$1;
/*      */     
/*      */     public final Stream<B> apply() {
/* 1153 */       return (Stream<B>)((Stream)this.stream$1.tail()).collect(this.pf$1, this.bf$1);
/*      */     }
/*      */     
/*      */     public Stream$$anonfun$collectedTail$1(Stream stream$1, PartialFunction pf$1, CanBuildFrom bf$1) {}
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\Stream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */