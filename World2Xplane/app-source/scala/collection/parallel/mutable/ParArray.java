/*     */ package scala.collection.parallel.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Function3;
/*     */ import scala.Function4;
/*     */ import scala.Function5;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.BufferedIterator;
/*     */ import scala.collection.CustomParallelizable;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenSeqLike;
/*     */ import scala.collection.GenSet;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Parallel;
/*     */ import scala.collection.Parallelizable;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.CanCombineFrom;
/*     */ import scala.collection.generic.DelegatedSignalling;
/*     */ import scala.collection.generic.GenTraversableFactory;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericParTemplate;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.generic.Signalling;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.ArraySeq;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.Iterable;
/*     */ import scala.collection.mutable.Seq;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.AugmentedIterableIterator;
/*     */ import scala.collection.parallel.AugmentedSeqIterator;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.IterableSplitter;
/*     */ import scala.collection.parallel.ParIterable;
/*     */ import scala.collection.parallel.ParIterableLike;
/*     */ import scala.collection.parallel.ParIterableView;
/*     */ import scala.collection.parallel.ParSeq;
/*     */ import scala.collection.parallel.ParSeqLike;
/*     */ import scala.collection.parallel.PreciseSplitter;
/*     */ import scala.collection.parallel.RemainsIterator;
/*     */ import scala.collection.parallel.SeqSplitter;
/*     */ import scala.collection.parallel.Splitter;
/*     */ import scala.collection.parallel.Task;
/*     */ import scala.collection.parallel.TaskSupport;
/*     */ import scala.collection.parallel.immutable.ParMap;
/*     */ import scala.collection.parallel.immutable.ParSet;
/*     */ import scala.math.Integral;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\035eh\001B\001\003\001-\021\001\002U1s\003J\024\030-\037\006\003\007\021\tq!\\;uC\ndWM\003\002\006\r\005A\001/\031:bY2,GN\003\002\b\021\005Q1m\0347mK\016$\030n\0348\013\003%\tQa]2bY\006\034\001!\006\002\r/M1\001!D\t!OE\002\"AD\b\016\003!I!\001\005\005\003\r\005s\027PU3g!\r\0212#F\007\002\005%\021AC\001\002\007!\006\0248+Z9\021\005Y9B\002\001\003\0061\001\021\r!\007\002\002)F\021!$\b\t\003\035mI!\001\b\005\003\0179{G\017[5oOB\021aBH\005\003?!\0211!\0218z!\021\tC%\006\024\016\003\tR!a\t\004\002\017\035,g.\032:jG&\021QE\t\002\023\017\026tWM]5d!\006\024H+Z7qY\006$X\r\005\002\023\001A)\001&K\013,Y5\tA!\003\002+\t\tQ\001+\031:TKFd\025n[3\021\007I\001Q\003E\002._Ui\021A\f\006\003\007\031I!\001\r\030\003\021\005\023(/Y=TKF\004\"A\004\032\n\005MB!\001D*fe&\fG.\033>bE2,\007\002C\033\001\005\013\007I\021\001\034\002\021\005\024(/Y=tKF,\022\001\f\005\tq\001\021\t\021)A\005Y\005I\021M\035:bsN,\027\017\t\005\007u\001!\tAA\036\002\rqJg.\033;?)\tYC\bC\0036s\001\007A\006C\004?\001\001\007I\021B \002\013\005\024(/Y=\026\003\001\0032AD!\036\023\t\021\005BA\003BeJ\f\027\020C\004E\001\001\007I\021B#\002\023\005\024(/Y=`I\025\fHC\001$J!\tqq)\003\002I\021\t!QK\\5u\021\035Q5)!AA\002\001\0131\001\037\0232\021\031a\005\001)Q\005\001\0061\021M\035:bs\002B#a\023(\021\0059y\025B\001)\t\005%!(/\0318tS\026tG\017C\003S\001\021\0053+A\005d_6\004\030M\\5p]V\tAKE\002V/j3AA\026\001\001)\naAH]3gS:,W.\0328u}A\031\021\005\027\024\n\005e\023#\001E$f]\026\024\030nY\"p[B\fg.[8o!\r\t3LJ\005\0039\n\0221cR3oKJL7\rU1s\007>l\007/\0318j_:DQA\017\001\005\002y#\"aK0\t\013\001l\006\031A1\002\005MT\bC\001\bc\023\t\031\007BA\002J]RDQ!\032\001\005\002\031\fQ!\0319qYf$\"!F4\t\013!$\007\031A1\002\003%DQA\033\001\005\002-\fa!\0369eCR,Gc\001$m[\")\001.\033a\001C\")a.\033a\001+\005!Q\r\\3n\021\025\001\b\001\"\001r\003\031aWM\\4uQV\t\021\rC\003t\001\021\005c'A\002tKFDa!\036\001\005\022\0211\030\001C:qY&$H/\032:\026\003]\004\"\001_=\016\003\0011AA\037\001\001w\n\001\002+\031:BeJ\f\0270\023;fe\006$xN]\n\004s6a\bc\001\025~+%\021a\020\002\002\f'\026\f8\013\0357jiR,'\017\003\005is\n\005\r\021\"\001r\021)\t\031!\037BA\002\023\005\021QA\001\006S~#S-\035\013\004\r\006\035\001\002\003&\002\002\005\005\t\031A1\t\023\005-\021P!A!B\023\t\027AA5!\021%\ty!\037BC\002\023\005\021/A\003v]RLG\016C\005\002\024e\024\t\021)A\005C\0061QO\034;jY\002B\021\"a\006z\005\013\007I\021A \002\007\005\024(\017C\005\002\034e\024\t\021)A\005\001\006!\021M\035:!\021\031Q\024\020\"\001\002 Q9q/!\t\002$\005\025\002\002\0035\002\036A\005\t\031A1\t\023\005=\021Q\004I\001\002\004\t\007\"CA\f\003;\001\n\0211\001A\021\035\tI#\037C\001\003W\tq\001[1t\035\026DH/\006\002\002.A\031a\"a\f\n\007\005E\002BA\004C_>dW-\0318\t\017\005U\022\020\"\001\0028\005!a.\032=u)\005)\002BBA\036s\022\005\021/A\005sK6\f\027N\\5oO\"1\021qH=\005\002Y\f1\001Z;q\021\035\t\031%\037C\001\003\013\na\001]:qY&$H\003BA$\003+\002R!!\023\002P]t1ADA&\023\r\ti\005C\001\ba\006\0347.Y4f\023\021\t\t&a\025\003\007M+\027OC\002\002N!A\001\"a\026\002B\001\007\021\021L\001\020g&TXm]%oG>l\007\017\\3uKB!a\"a\027b\023\r\ti\006\003\002\013yI,\007/Z1uK\022t\004bBA1s\022\005\0231M\001\006gBd\027\016^\013\003\003\017Bq!a\032z\t\003\nI'\001\005u_N#(/\0338h)\t\tY\007\005\003\002n\005]TBAA8\025\021\t\t(a\035\002\t1\fgn\032\006\003\003k\nAA[1wC&!\021\021PA8\005\031\031FO]5oO\"9\021QP=\005B\005}\024a\0024pe\026\f7\r[\013\005\003\003\013y\tF\002G\003\007C\001\"!\"\002|\001\007\021qQ\001\002MB1a\"!#\026\003\033K1!a#\t\005%1UO\\2uS>t\027\007E\002\027\003\037#q!!%\002|\t\007\021DA\001V\021\035\t)*\037C\005\003/\013QBZ8sK\006\034\007nX9vS\016\\W\003BAM\003C#\022BRAN\003G\0139+a+\t\021\005\025\0251\023a\001\003;\003bADAE+\005}\005c\001\f\002\"\0229\021\021SAJ\005\004I\002bBAS\003'\003\r\001Q\001\002C\"9\021\021VAJ\001\004\t\027\001\0028uS2Dq!!,\002\024\002\007\021-\001\003ge>l\007bBAYs\022\005\0231W\001\006G>,h\016\036\013\004C\006U\006\002CA\\\003_\003\r!!/\002\003A\004bADAE+\0055\002bBA_s\022%\021qX\001\fG>,h\016^0rk&\0347\016F\005b\003\003\f\031-!2\002H\"A\021qWA^\001\004\tI\fC\004\002&\006m\006\031\001!\t\017\005%\0261\030a\001C\"9\021QVA^\001\004\t\007bBAfs\022\005\023QZ\001\tM>dG\rT3giV!\021qZAk)\021\t\t.a9\025\t\005M\027\021\034\t\004-\005UGaBAl\003\023\024\r!\007\002\002'\"A\0211\\Ae\001\004\ti.\001\002paBAa\"a8\002TV\t\031.C\002\002b\"\021\021BR;oGRLwN\034\032\t\021\005\025\030\021\032a\001\003'\f\021A\037\005\b\003SLH\021BAv\00391w\016\0343MK\032$x,];jG.,B!!<\002rRQ\021q^Az\003k\f90a?\021\007Y\t\t\020B\004\002X\006\035(\031A\r\t\017\005\025\026q\035a\001\001\"9\021\021VAt\001\004\t\007\002CAn\003O\004\r!!?\021\0219\ty.a<\026\003_D\001\"!:\002h\002\007\021q\036\005\b\003LH\021\tB\001\003\0211w\016\0343\026\t\t\r!\021\002\013\005\005\013\021\t\002\006\003\003\b\t5\001c\001\f\003\n\021A\021\021SA\005\004\021Y!\005\002\026;!A\0211\\A\001\004\021y\001E\005\017\003?\0249Aa\002\003\b!A\021Q]A\001\004\0219\001C\004\003\026e$\tEa\006\002\023\005<wM]3hCR,W\003\002B\r\005?!BAa\007\003.Q1!Q\004B\021\005O\0012A\006B\020\t\035\t9Na\005C\002eA\001Ba\t\003\024\001\007!QE\001\006g\026\fx\016\035\t\t\035\005}'QD\013\003\036!A!\021\006B\n\001\004\021Y#\001\004d_6\024w\016\035\t\n\035\005}'Q\004B\017\005;A\001\"!:\003\024\001\007!Q\004\005\b\005cIH\021\tB\032\003\r\031X/\\\013\005\005k\021I\004\006\003\0038\tm\002c\001\f\003:\021A\021\021\023B\030\005\004\021Y\001\003\005\003>\t=\0029\001B \003\rqW/\034\t\007\003\023\022\tEa\016\n\t\t\r\0231\013\002\b\035VlWM]5d\021\035\0219%\037C\005\005\023\n\021b];n?F,\030nY6\026\t\t-#q\n\013\r\005\033\022\tF!\026\003X\te#1\f\t\004-\t=C\001CAI\005\013\022\rAa\003\t\021\tu\"Q\ta\001\005'\002b!!\023\003B\t5\003bBAS\005\013\002\r\001\021\005\b\003S\023)\0051\001b\021\035\tiK!\022A\002\005D\001B!\030\003F\001\007!QJ\001\005u\026\024x\016C\004\003be$\tEa\031\002\017A\024x\016Z;diV!!Q\rB5)\021\0219Ga\033\021\007Y\021I\007\002\005\002\022\n}#\031\001B\006\021!\021iDa\030A\004\t5\004CBA%\005\003\0229\007C\004\003re$IAa\035\002\033A\024x\016Z;di~\013X/[2l+\021\021)H!\037\025\031\t]$1\020B@\005\003\023\031I!\"\021\007Y\021I\b\002\005\002\022\n=$\031\001B\006\021!\021iDa\034A\002\tu\004CBA%\005\003\0229\bC\004\002&\n=\004\031\001!\t\017\005%&q\016a\001C\"9\021Q\026B8\001\004\t\007\002\003BD\005_\002\rAa\036\002\007=tW\rC\004\003\ff$\tE!$\002\r\031|'/\0317m)\021\tiCa$\t\021\005]&\021\022a\001\003sCqAa%z\t\023\021)*\001\007g_J\fG\016\\0rk&\0347\016\006\006\002.\t]%\021\024BN\005?C\001\"a.\003\022\002\007\021\021\030\005\b\003K\023\t\n1\001A\021\035\021iJ!%A\002\005\f\021B\\3yiVtG/\0337\t\017\t\005&\021\023a\001C\006)1\017^1si\"9!QU=\005B\t\035\026AB3ySN$8\017\006\003\002.\t%\006\002CA\\\005G\003\r!!/\t\017\t5\026\020\"\003\0030\006aQ\r_5tiN|\026/^5dWRQ\021Q\006BY\005g\023)La.\t\021\005]&1\026a\001\003sCq!!*\003,\002\007\001\tC\004\003\036\n-\006\031A1\t\017\t\005&1\026a\001C\"9!1X=\005B\tu\026\001\0024j]\022$BAa0\003FB!aB!1\026\023\r\021\031\r\003\002\007\037B$\030n\0348\t\021\005]&\021\030a\001\003sCqA!3z\t\023\021Y-\001\006gS:$w,];jG.$\"Ba0\003N\n='\021\033Bj\021!\t9La2A\002\005e\006bBAS\005\017\004\r\001\021\005\b\005;\0239\r1\001b\021\035\021\tKa2A\002\005DqAa6z\t\003\022I.\001\003ee>\004HcA<\003\\\"9!Q\034Bk\001\004\t\027!\0018\t\017\t\005\030\020\"\021\003d\006Y1m\0349z)>\f%O]1z+\021\021)O!<\025\017\031\0239Oa<\003r\"9aHa8A\002\t%\b\003\002\bB\005W\0042A\006Bw\t!\t\tJa8C\002\t-\001bBAW\005?\004\r!\031\005\b\005g\024y\0161\001b\003\raWM\034\005\b\005oLH\021\tB}\0031\001(/\0324jq2+gn\032;i)\r\t'1 \005\t\005{\024)\0201\001\002:\006!\001O]3e\021\035\031\t!\037C\005\007\007\t!\003\035:fM&DH*\0328hi\"|\026/^5dWRI\021m!\002\004\b\r%11\002\005\t\005{\024y\0201\001\002:\"9\021Q\025B\000\001\004\001\005bBAU\005\004\r!\031\005\b\007\033\021y\0201\001b\003!\031H/\031:ua>\034\bbBB\ts\022\00531C\001\013S:$W\r_,iKJ,GcA1\004\026!A!Q`B\b\001\004\tI\fC\004\004\032e$Iaa\007\002!%tG-\032=XQ\026\024XmX9vS\016\\G#C1\004\036\r}1\021EB\022\021!\021ipa\006A\002\005e\006bBAS\007/\001\r\001\021\005\b\003S\0339\0021\001b\021\035\tika\006A\002\005Dqaa\nz\t\003\032I#\001\bmCN$\030J\0343fq^CWM]3\025\007\005\034Y\003\003\005\003~\016\025\002\031AA]\021\035\031y#\037C\005\007c\tA\003\\1ti&sG-\032=XQ\026\024XmX9vS\016\\G#C1\0044\rU2qGB\035\021!\021ip!\fA\002\005e\006bBAS\007[\001\r\001\021\005\b\003[\033i\0031\001b\021\035\tIk!\fA\002\005Dqa!\020z\t\003\032y$\001\007tC6,W\t\\3nK:$8\017\006\003\002.\r\005\003\002CB\"\007w\001\ra!\022\002\tQD\027\r\036\031\005\007\017\032y\005\005\004\002J\r%3QJ\005\005\007\027\n\031F\001\005Ji\026\024\030\r^8s!\r12q\n\003\f\007#\032\t%!A\001\002\013\005\021DA\002`IEBqa!\026z\t\003\0329&\001\007nCB\0244m\\7cS:,'/\006\004\004Z\r\r4q\r\013\007\0077\032Yga\034\021\017!\032if!\031\004f%\0311q\f\003\003\021\r{WNY5oKJ\0042AFB2\t\035\t9na\025C\002e\0012AFB4\t\035\031Iga\025C\002e\021A\001\0265bi\"A\021QQB*\001\004\031i\007\005\004\017\003\023+2\021\r\005\t\007c\032\031\0061\001\004\\\005\0211M\031\005\b\007kJH\021BB<\003Ii\027\r\035\032d_6\024\027N\\3s?F,\030nY6\026\r\re4\021QBH)-151PBB\007\013\033\tja%\t\021\005\02551\017a\001\007{\002bADAE+\r}\004c\001\f\004\002\0229\021q[B:\005\004I\002bBAS\007g\002\r\001\021\005\t\007c\032\031\b1\001\004\bB9Qf!#\004\000\r5\025bABF]\t9!)^5mI\026\024\bc\001\f\004\020\02291\021NB:\005\004I\002bBAU\007g\002\r!\031\005\b\003[\033\031\b1\001b\021\035\0319*\037C!\0073\013\001cY8mY\026\034GOM2p[\nLg.\032:\026\r\rm5\021UBS)\031\031ija*\0042B9\001f!\030\004 \016\r\006c\001\f\004\"\0229\021q[BK\005\004I\002c\001\f\004&\02291\021NBK\005\004I\002\002CBU\007+\003\raa+\002\005A4\007C\002\b\004.V\031y*C\002\0040\"\021q\002U1si&\fGNR;oGRLwN\034\005\t\007c\032)\n1\001\004\036\"91QW=\005\n\r]\026AF2pY2,7\r\036\032d_6\024\027N\\3s?F,\030nY6\026\r\re6\021YBf)-151XBb\007\013\034ima4\t\021\r%61\027a\001\007{\003bADBW+\r}\006c\001\f\004B\0229\021q[BZ\005\004I\002bBAS\007g\003\r\001\021\005\t\007c\032\031\f1\001\004HB9Qf!#\004@\016%\007c\001\f\004L\02291\021NBZ\005\004I\002bBAU\007g\003\r!\031\005\b\003[\033\031\f1\001b\021\035\031\031.\037C!\007+\f\001C\0327bi6\f\007OM2p[\nLg.\032:\026\r\r]7Q\\Bq)\031\031Ina9\004pB9\001f!\030\004\\\016}\007c\001\f\004^\0229\021q[Bi\005\004I\002c\001\f\004b\02291\021NBi\005\004I\002\002CAC\007#\004\ra!:\021\r9\tI)FBt!\031\031Ioa;\004\\6\ta!C\002\004n\032\021!cR3o)J\fg/\032:tC\ndWm\0248dK\"A1\021OBi\001\004\031I\016C\004\004tf$\te!>\002\037\031LG\016^3se\r|WNY5oKJ,baa>\004~\022\005ACBB}\t\013!9\001E\004)\007;\032Ypa@\021\007Y\031i\020\002\005\002\022\016E(\031\001B\006!\r1B\021\001\003\b\t\007\031\tP1\001\032\005\021!\006.[:\t\021\tu8\021\037a\001\003sC\001b!\035\004r\002\0071\021 \005\b\t\027IH\021\002C\007\003U1\027\016\034;feJ\032w.\0342j]\026\024x,];jG.,b\001b\004\005\032\021uAc\003$\005\022\021MAq\004C\021\tGA\001B!@\005\n\001\007\021\021\030\005\t\007c\"I\0011\001\005\026A9Qf!#\005\030\021m\001c\001\f\005\032\021A\021\021\023C\005\005\004\021Y\001E\002\027\t;!q\001b\001\005\n\t\007\021\004C\004\002&\022%\001\031\001!\t\017\005%F\021\002a\001C\"9\021Q\026C\005\001\004\t\007b\002C\024s\022\005C\021F\001\023M&dG/\032:O_R\0244m\\7cS:,'/\006\004\005,\021EBQ\007\013\007\t[!9\004\"\017\021\017!\032i\006b\f\0054A\031a\003\"\r\005\021\005EEQ\005b\001\005\027\0012A\006C\033\t\035!\031\001\"\nC\002eA\001B!@\005&\001\007\021\021\030\005\t\007c\")\0031\001\005.!9AQH=\005\n\021}\022\001\0074jYR,'OT8ue\r|WNY5oKJ|\026/^5dWV1A\021\tC&\t\037\"2B\022C\"\t\013\"\t\006b\025\005V!A!Q C\036\001\004\tI\f\003\005\004r\021m\002\031\001C$!\035i3\021\022C%\t\033\0022A\006C&\t!\t\t\nb\017C\002\t-\001c\001\f\005P\0219A1\001C\036\005\004I\002bBAS\tw\001\r\001\021\005\b\003S#Y\0041\001b\021\035\ti\013b\017A\002\005Dq\001\"\027z\t\003\"Y&\001\007d_BL(GY;jY\022,'/\006\005\005^\021-Dq\016C1)\021!y\006b\035\021\007Y!\t\007\002\005\005d\021]#\031\001C3\005\r\021E\016Z\t\0045\021\035\004cB\027\004\n\022%DQ\016\t\004-\021-D\001CAI\t/\022\rAa\003\021\007Y!y\007B\004\005r\021]#\031A\r\003\t\r{G\016\034\005\t\007c\"9\0061\001\005`!9AqO=\005\n\021e\024AE2paf\024$-^5mI\026\024x,];jG.,b\001b\037\005\006\022%E#\003$\005~\021-EQ\022CH\021!!y\b\"\036A\002\021\005\025!\0012\021\0175\032I\tb!\005\bB\031a\003\"\"\005\021\005EEQ\017b\001\005\027\0012A\006CE\t\035!\t\b\"\036C\002eAq!!*\005v\001\007\001\tC\004\002*\022U\004\031A1\t\017\0055FQ\017a\001C\"9A1S=\005B\021U\025a\0059beRLG/[8oe\r|WNY5oKJ\034XC\002CL\tG#9\013\006\005\005\032\022%F1\026CX!\035qA1\024CP\t?K1\001\"(\t\005\031!V\017\0357feA9\001f!\030\005\"\022\025\006c\001\f\005$\022A\021\021\023CI\005\004\021Y\001E\002\027\tO#q\001b\001\005\022\n\007\021\004\003\005\003~\022E\005\031AA]\021!!i\013\"%A\002\021}\025!\0022ueV,\007\002\003CY\t#\003\r\001b(\002\r\t4\027\r\\:f\021\035!),\037C\005\to\013\021\004]1si&$\030n\03483G>l'-\0338feN|\026/^5dWV1A\021\030Cb\t\017$RB\022C^\t{#I\rb3\005N\022=\007\002CA\\\tg\003\r!!/\t\021\0215F1\027a\001\t\003r!LBE\t\003$)\rE\002\027\t\007$\001\"!%\0054\n\007!1\002\t\004-\021\035Ga\002C\002\tg\023\r!\007\005\t\tc#\031\f1\001\005@\"9\021Q\025CZ\001\004\001\005bBAU\tg\003\r!\031\005\b\003[#\031\f1\001b\021\035!\031.\037C!\t+\fQ\002^1lKJ\032w.\0342j]\026\024XC\002Cl\t;$\t\017\006\004\005Z\022\rHQ\035\t\bQ\ruC1\034Cp!\r1BQ\034\003\t\003##\tN1\001\003\fA\031a\003\"9\005\017\021\rA\021\033b\0013!9!Q\034Ci\001\004\t\007\002CB9\t#\004\r\001\"7\t\017\021%\030\020\"\021\005l\006iAM]8qe\r|WNY5oKJ,b\001\"<\005t\022]HC\002Cx\ts$Y\020E\004)\007;\"\t\020\">\021\007Y!\031\020\002\005\002\022\022\035(\031\001B\006!\r1Bq\037\003\b\t\007!9O1\001\032\021\035\021i\016b:A\002\005D\001b!\035\005h\002\007Aq\036\005\b\tLH\021IC\001\003A\021XM^3sg\026\0244m\\7cS:,'/\006\004\006\004\025%QQ\002\013\005\013\013)y\001E\004)\007;*9!b\003\021\007Y)I\001\002\005\002\022\022u(\031\001B\006!\r1RQ\002\003\b\t\007!iP1\001\032\021!\031\t\b\"@A\002\025\025\001bBC\ns\022%QQC\001\027e\0264XM]:fe\r|WNY5oKJ|\026/^5dWRYa)b\006\006\034\025uQ\021EC\023\021\035)I\"\"\005A\002\001\013A\001^1sO\"9\021QUC\t\001\004\001\005bBC\020\013#\001\r!Y\001\ti\006\024xM\032:p[\"9Q1EC\t\001\004\t\027aB:sG\032\024x.\034\005\b\013O)\t\0021\001b\003!\031(oY;oi&d\007bBC\026s\022\005SQF\001\fg\016\fg\016V8BeJ\f\0270\006\004\0060\025UR1\t\013\n\r\026ERqGC\036\013\023B\001\"!:\006*\001\007Q1\007\t\004-\025UB\001CAI\013S\021\rAa\003\t\021\005mW\021\006a\001\013s\001\022BDAp\013g)\031$b\r\t\021\025uR\021\006a\001\013\tq\001Z3ti\006\024(\017\005\003\017\003\026\005\003c\001\f\006D\021AQQIC\025\005\004)9EA\001B#\r)\031$\b\005\b\003[+I\0031\001b\021\035)i%\037C\t\013\037\n\021c]2b]R{\027I\035:bs~\013X/[2l+\021)\t&b\030\025\037\031+\031&b\026\006Z\025\005T1MC3\013SBq!\"\026\006L\001\007\001)\001\004te\016\f'O\035\005\b\013{)Y\0051\001A\021!\tY.b\023A\002\025m\003#\003\b\002`\026uSQLC/!\r1Rq\f\003\b\003#+YE1\001\032\021!\t)/b\023A\002\025u\003bBC\022\013\027\002\r!\031\005\b\013O*Y\0051\001b\003\035\031(o\0318uS2Dq!b\033\006L\001\007\021-\001\005eKN$hM]8n\0211)y'_A\001\002\023%Q\021OCA\003Y\031X\017]3sII,g/\032:tKJ\032w.\0342j]\026\024XCBC:\013s*i\b\006\003\006v\025}\004c\002\025\004^\025]T1\020\t\004-\025eD\001CAI\013[\022\rAa\003\021\007Y)i\bB\004\005\004\0255$\031A\r\t\021\rETQ\016a\001\013kJA\001b@\006\004&\031QQ\021\003\003)\005+x-\\3oi\026$7+Z9Ji\026\024\030\r^8s\017%)I\tAA\001\022\003)Y)\001\tQCJ\f%O]1z\023R,'/\031;peB\031\0010\"$\007\021i\004\021\021!E\001\013\037\0332!\"$\016\021\035QTQ\022C\001\013'#\"!b#\t\025\025]UQRI\001\n\003)I*A\016%Y\026\0348/\0338ji\022:'/Z1uKJ$C-\0324bk2$H%M\013\003\0137S3!YCOW\t)y\n\005\003\006\"\026-VBACR\025\021))+b*\002\023Ut7\r[3dW\026$'bACU\021\005Q\021M\0348pi\006$\030n\0348\n\t\0255V1\025\002\022k:\034\007.Z2lK\0224\026M]5b]\016,\007BCCY\013\033\013\n\021\"\001\006\032\006YB\005\\3tg&t\027\016\036\023he\026\fG/\032:%I\0264\027-\0367uIIB!\"\".\006\016F\005I\021AC\\\003m!C.Z:tS:LG\017J4sK\006$XM\035\023eK\032\fW\017\034;%gU\021Q\021\030\026\004\001\026u\005bBC_\001\021%QqX\001\007CN$\026m]6\026\r\025\005W1ZCi)\021)\031-\"6\021\017!*)-\"3\006P&\031Qq\031\003\003\tQ\0137o\033\t\004-\025-GaBCg\013w\023\r!\007\002\002%B\031a#\"5\005\017\025MW1\030b\0013\t\021A\013\035\005\b\013/,Y\f1\001\036\003\005!\bbBCn\001\021%QQ\\\001\fEVLG\016Z:BeJ\f\0270\006\004\006`\026%XQ\036\013\005\003[)\t\017\003\005\006d\026e\007\031ACs\003\005\031\007cB\027\004\n\026\035X1\036\t\004-\025%HaBAl\0133\024\r!\007\t\004-\0255HaBB5\0133\024\r!\007\005\b\013c\004A\021ICz\003\ri\027\r]\013\007\013k4I!b?\025\t\025]h1\002\013\005\013s,i\020E\002\027\013w$qa!\033\006p\n\007\021\004\003\005\006\000\026=\b9\001D\001\003\t\021g\r\005\005\"\r\007YcqAC}\023\r1)A\t\002\r\007\006t')^5mI\032\023x.\034\t\004-\031%AaBAl\013_\024\r!\007\005\t\003\013+y\0171\001\007\016A1a\"!#\026\r\017AqA\"\005\001\t\0032\031\"\001\003tG\006tWC\002D\013\rO1i\002\006\003\007\030\0315B\003\002D\r\rS!BAb\007\007 A\031aC\"\b\005\017\r%dq\002b\0013!Aa\021\005D\b\001\b1\031#A\002dE\032\004\002\"\tD\002W\031\025b1\004\t\004-\031\035B\001CAI\r\037\021\rAa\003\t\021\005mgq\002a\001\rW\001\022BDAp\rK1)C\"\n\t\021\005\025hq\002a\001\rK1aA\"\r\001\001\031M\"aC*dC:$v.\021:sCf,BA\"\016\007>M)aqF\007\0078A1\001&\"2G\rs\001R\001\037D\030\rw\0012A\006D\037\t!\t\tJb\fC\002\t-\001b\003D!\r_\021\t\021)A\005\r\007\nA\001\036:fKB)\001P\"\022\007<%!aq\tD%\005!\0316-\0318Ue\026,\027b\001D&\t\ty\001+\031:Ji\026\024\030M\0317f\031&\\W\rC\006\002f\032=\"\021!Q\001\n\031m\002bCAn\r_\021\t\021)A\005\r#\002\022BDAp\rw1YDb\017\t\025\031Ucq\006B\001B\003%\001)A\005uCJ<W\r^1se\"9!Hb\f\005\002\031eCC\003D\035\r72iFb\030\007b!Aa\021\tD,\001\0041\031\005\003\005\002f\032]\003\031\001D\036\021!\tYNb\026A\002\031E\003b\002D+\r/\002\r\001\021\005\013\rK2y\0031A\005\002\031\035\024A\002:fgVdG/F\001G\021)1YGb\fA\002\023\005aQN\001\013e\026\034X\017\034;`I\025\fHc\001$\007p!A!J\"\033\002\002\003\007a\t\003\005\007t\031=\002\025)\003G\003\035\021Xm];mi\002B\001Bb\036\0070\021\005a\021P\001\005Y\026\fg\rF\002G\rwB\001B\" \007v\001\007aqP\001\005aJ,g\017\005\003\017\005\0034\005\002\003DB\r_!IA\"\"\002\017%$XM]1uKR\031aIb\"\t\021\031\005c\021\021a\001\r\007B\001Bb#\0070\021%aQR\001\tg\016\fg\016T3bMRYaIb$\007\022\032MeQ\023DL\021\035))F\"#A\002\001CqA\"\026\007\n\002\007\001\tC\004\002.\032%\005\031A1\t\017\tMh\021\022a\001C\"Aa\021\024DE\001\0041Y$\001\005ti\006\024HO^1m\021!\t\tGb\f\005\002\031uUC\001DP!\0311\t+a\024\00789!a1UA&\035\0211)Kb+\016\005\031\035&b\001DU\025\0051AH]8pizJ\021!\003\005\t\r_3y\003\"\001\002,\005\0212\017[8vY\022\034\006\017\\5u\rV\024H\017[3s\r\0311\031\f\001\001\0076\n\031Q*\0319\026\t\031]fqX\n\006\rcka\021\030\t\007Q\025\025gIb/\021\013a4\tL\"0\021\007Y1y\fB\004\002X\032E&\031A\r\t\027\005\025e\021\027B\001B\003%a1\031\t\007\035\005%UC\"0\t\025\031Uc\021\027B\001B\003%\001\t\003\006\007J\032E&\021!Q\001\n\005\faa\0344gg\026$\bB\003Dg\rc\023\t\021)A\005C\0069\001n\\<nC:L\bb\002\036\0072\022\005a\021\033\013\013\rw3\031N\"6\007X\032e\007\002CAC\r\037\004\rAb1\t\017\031Ucq\032a\001\001\"9a\021\032Dh\001\004\t\007b\002Dg\r\037\004\r!\031\005\013\rK2\t\f1A\005\002\031\035\004B\003D6\rc\003\r\021\"\001\007`R\031aI\"9\t\021)3i.!AA\002\031C\001Bb\035\0072\002\006KA\022\005\t\ro2\t\f\"\001\007hR\031aI\";\t\021\031udQ\035a\001\rB\001\"!\031\0072\022\005aQ^\013\003\r_\004bA\"=\007x\032mVB\001Dz\025\r1)PB\001\nS6lW\017^1cY\026LAA\"?\007t\n!A*[:u\021!1yK\"-\005\002\005-\002b\002D\000\001\021%q\021A\001\foJLG/Z(cU\026\034G\017F\002G\017\007A\001b\"\002\007~\002\007qqA\001\004_V$\b\003BD\005\017\037i!ab\003\013\t\0355\0211O\001\003S>LAa\"\005\b\f\t\021rJ\0316fGR|U\017\0369viN#(/Z1n\021\0359)\002\001C\005\017/\t!B]3bI>\023'.Z2u)\r1u\021\004\005\t\01779\031\0021\001\b\036\005\021\021N\034\t\005\017\0239y\"\003\003\b\"\035-!!E(cU\026\034G/\0238qkR\034FO]3b[\"*\001a\"\n\b,A\031abb\n\n\007\035%\002B\001\tTKJL\027\r\034,feNLwN\\+J\tz\t\021aB\004\b0\tA\ta\"\r\002\021A\013'/\021:sCf\0042AED\032\r\031\t!\001#\001\b6M)q1GD\034cA!\021e\"\017'\023\r9YD\t\002\013!\006\024h)Y2u_JL\bb\002\036\b4\021\005qq\b\013\003\017cA\001bb\021\b4\021\rqQI\001\rG\006t')^5mI\032\023x.\\\013\005\017\017:9&\006\002\bJAI\021eb\023\bP\035Us\021L\005\004\017\033\022#AD\"b]\016{WNY5oK\032\023x.\034\t\005\017#:\031&\004\002\b4%\031A\021\017-\021\007Y99\006\002\004\031\017\003\022\r!\007\t\005%\0019)\006\003\005\b^\035MB\021AD0\003)qWm\036\"vS2$WM]\013\005\017C:9'\006\002\bdA9\001f!\030\bf\035%\004c\001\f\bh\0211\001db\027C\002e\001BA\005\001\bf!AqQND\032\t\0039y'A\006oK^\034u.\0342j]\026\024X\003BD9\017o*\"ab\035\021\017!\032if\"\036\bzA\031acb\036\005\ra9YG1\001\032!\021\021\002a\"\036\t\021\035ut1\007C\001\017\nq\001[1oI>4g-\006\003\b\002\036\035E\003BDB\017\023\003BA\005\001\b\006B\031acb\"\005\ra9YH1\001\032\021!\t9bb\037A\002\035-\005\003\002\bB\017\013C\001b\" \b4\021\005qqR\013\005\017#;9\n\006\004\b\024\036euQ\024\t\005%\0019)\nE\002\027\017/#a\001GDG\005\004I\002\002CA\f\017\033\003\rab'\021\t9\tuQ\023\005\007A\0365\005\031A1\t\021\035\005v1\007C\005\017G\013Qb\036:ba>\023(+\0322vS2$W\003BDS\017W#bab*\b.\036=\006\003\002\n\001\017S\0032AFDV\t\031Arq\024b\0013!9\021qCDP\001\004i\001B\0021\b \002\007\021\r\003\005\b4\036MB\021AD[\0039\031'/Z1uK\032\023x.\\\"paf,Bab.\b@R!q\021XDj)\0219Ylb1\021\tI\001qQ\030\t\004-\035}Fa\002\r\b2\n\007q\021Y\t\00355A!b\"2\b2\006\005\t9ADd\003))g/\0333f]\016,G%\r\t\007\017\023<ym\"0\016\005\035-'bADg\021\0059!/\0324mK\016$\030\002BDi\017\027\024\001b\0217bgN$\026m\032\005\t\003/9\t\f1\001\bVB!a\"QD_\021!9Inb\r\005\002\035m\027\001\0054s_6$&/\031<feN\f'\r\\3t+\0219inb9\025\t\035}wQ\035\t\005%\0019\t\017E\002\027\017G$a\001GDl\005\004I\002\002CDt\017/\004\ra\";\002\007a\0348\017E\003\017\0037:Y\017\005\004\004j\016-x\021\035\005\013\017_<\031$!A\005\n\035E\030a\003:fC\022\024Vm]8mm\026$\"ab=\021\t\0055tQ_\005\005\017o\fyG\001\004PE*,7\r\036")
/*     */ public class ParArray<T> implements ParSeq<T>, GenericParTemplate<T, ParArray>, ParSeqLike<T, ParArray<T>, ArraySeq<T>>, Serializable {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final ArraySeq<T> arrayseq;
/*     */   
/*     */   private transient Object[] scala$collection$parallel$mutable$ParArray$$array;
/*     */   
/*     */   private volatile ParArrayIterator$ ParArrayIterator$module;
/*     */   
/*     */   private volatile transient TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */   
/*     */   private volatile scala.collection.parallel.ParIterableLike$ScanNode$ ScanNode$module;
/*     */   
/*     */   private volatile scala.collection.parallel.ParIterableLike$ScanLeaf$ ScanLeaf$module;
/*     */   
/*     */   public ParSeq<T> toSeq() {
/*  58 */     return ParSeq$class.toSeq(this);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  58 */     return ParSeq.class.toString(this);
/*     */   }
/*     */   
/*     */   public String stringPrefix() {
/*  58 */     return ParSeq.class.stringPrefix(this);
/*     */   }
/*     */   
/*     */   public Object scala$collection$parallel$ParSeqLike$$super$zip(GenIterable that, CanBuildFrom bf) {
/*  58 */     return ParIterableLike.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public PreciseSplitter<T> iterator() {
/*  58 */     return ParSeqLike.class.iterator(this);
/*     */   }
/*     */   
/*     */   public int size() {
/*  58 */     return ParSeqLike.class.size(this);
/*     */   }
/*     */   
/*     */   public int segmentLength(Function1 p, int from) {
/*  58 */     return ParSeqLike.class.segmentLength(this, p, from);
/*     */   }
/*     */   
/*     */   public int indexWhere(Function1 p, int from) {
/*  58 */     return ParSeqLike.class.indexWhere(this, p, from);
/*     */   }
/*     */   
/*     */   public int lastIndexWhere(Function1 p, int end) {
/*  58 */     return ParSeqLike.class.lastIndexWhere(this, p, end);
/*     */   }
/*     */   
/*     */   public ParArray<T> reverse() {
/*  58 */     return (ParArray<T>)ParSeqLike.class.reverse(this);
/*     */   }
/*     */   
/*     */   public <S, That> That reverseMap(Function1 f, CanBuildFrom bf) {
/*  58 */     return (That)ParSeqLike.class.reverseMap(this, f, bf);
/*     */   }
/*     */   
/*     */   public <S> boolean startsWith(GenSeq that, int offset) {
/*  58 */     return ParSeqLike.class.startsWith(this, that, offset);
/*     */   }
/*     */   
/*     */   public <U> boolean sameElements(GenIterable that) {
/*  58 */     return ParSeqLike.class.sameElements(this, that);
/*     */   }
/*     */   
/*     */   public <S> boolean endsWith(GenSeq that) {
/*  58 */     return ParSeqLike.class.endsWith(this, that);
/*     */   }
/*     */   
/*     */   public <U, That> That patch(int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/*  58 */     return (That)ParSeqLike.class.patch(this, from, patch, replaced, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That updated(int index, Object elem, CanBuildFrom bf) {
/*  58 */     return (That)ParSeqLike.class.updated(this, index, elem, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/*  58 */     return (That)ParSeqLike.class.$plus$colon(this, elem, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That $colon$plus(Object elem, CanBuildFrom bf) {
/*  58 */     return (That)ParSeqLike.class.$colon$plus(this, elem, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That padTo(int len, Object elem, CanBuildFrom bf) {
/*  58 */     return (That)ParSeqLike.class.padTo(this, len, elem, bf);
/*     */   }
/*     */   
/*     */   public <U, S, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  58 */     return (That)ParSeqLike.class.zip(this, that, bf);
/*     */   }
/*     */   
/*     */   public <S> boolean corresponds(GenSeq that, Function2 p) {
/*  58 */     return ParSeqLike.class.corresponds(this, that, p);
/*     */   }
/*     */   
/*     */   public <U> ParArray<T> diff(GenSeq that) {
/*  58 */     return (ParArray<T>)ParSeqLike.class.diff(this, that);
/*     */   }
/*     */   
/*     */   public <U> ParArray<T> intersect(GenSeq that) {
/*  58 */     return (ParArray<T>)ParSeqLike.class.intersect(this, that);
/*     */   }
/*     */   
/*     */   public ParArray<T> distinct() {
/*  58 */     return (ParArray<T>)ParSeqLike.class.distinct(this);
/*     */   }
/*     */   
/*     */   public Object view() {
/*  58 */     return ParSeqLike.class.view(this);
/*     */   }
/*     */   
/*     */   public SeqSplitter<T> down(IterableSplitter p) {
/*  58 */     return ParSeqLike.class.down(this, p);
/*     */   }
/*     */   
/*     */   public ParIterable<T> toIterable() {
/*  58 */     return ParIterable$class.toIterable(this);
/*     */   }
/*     */   
/*     */   public TaskSupport scala$collection$parallel$ParIterableLike$$_tasksupport() {
/*  58 */     return this.scala$collection$parallel$ParIterableLike$$_tasksupport;
/*     */   }
/*     */   
/*     */   @TraitSetter
/*     */   public void scala$collection$parallel$ParIterableLike$$_tasksupport_$eq(TaskSupport x$1) {
/*  58 */     this.scala$collection$parallel$ParIterableLike$$_tasksupport = x$1;
/*     */   }
/*     */   
/*     */   private scala.collection.parallel.ParIterableLike$ScanNode$ ScanNode$lzycompute() {
/*  58 */     synchronized (this) {
/*  58 */       if (this.ScanNode$module == null)
/*  58 */         this.ScanNode$module = new scala.collection.parallel.ParIterableLike$ScanNode$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/mutable/ParArray}} */
/*  58 */       return this.ScanNode$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public scala.collection.parallel.ParIterableLike$ScanNode$ ScanNode() {
/*  58 */     return (this.ScanNode$module == null) ? ScanNode$lzycompute() : this.ScanNode$module;
/*     */   }
/*     */   
/*     */   private scala.collection.parallel.ParIterableLike$ScanLeaf$ ScanLeaf$lzycompute() {
/*  58 */     synchronized (this) {
/*  58 */       if (this.ScanLeaf$module == null)
/*  58 */         this.ScanLeaf$module = new scala.collection.parallel.ParIterableLike$ScanLeaf$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/mutable/ParArray}} */
/*  58 */       return this.ScanLeaf$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public scala.collection.parallel.ParIterableLike$ScanLeaf$ ScanLeaf() {
/*  58 */     return (this.ScanLeaf$module == null) ? ScanLeaf$lzycompute() : this.ScanLeaf$module;
/*     */   }
/*     */   
/*     */   public void initTaskSupport() {
/*  58 */     ParIterableLike.class.initTaskSupport(this);
/*     */   }
/*     */   
/*     */   public TaskSupport tasksupport() {
/*  58 */     return ParIterableLike.class.tasksupport(this);
/*     */   }
/*     */   
/*     */   public void tasksupport_$eq(TaskSupport ts) {
/*  58 */     ParIterableLike.class.tasksupport_$eq(this, ts);
/*     */   }
/*     */   
/*     */   public ParArray<T> repr() {
/*  58 */     return (ParArray<T>)ParIterableLike.class.repr(this);
/*     */   }
/*     */   
/*     */   public final boolean isTraversableAgain() {
/*  58 */     return ParIterableLike.class.isTraversableAgain(this);
/*     */   }
/*     */   
/*     */   public boolean hasDefiniteSize() {
/*  58 */     return ParIterableLike.class.hasDefiniteSize(this);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  58 */     return ParIterableLike.class.isEmpty(this);
/*     */   }
/*     */   
/*     */   public boolean nonEmpty() {
/*  58 */     return ParIterableLike.class.nonEmpty(this);
/*     */   }
/*     */   
/*     */   public T head() {
/*  58 */     return (T)ParIterableLike.class.head(this);
/*     */   }
/*     */   
/*     */   public Option<T> headOption() {
/*  58 */     return ParIterableLike.class.headOption(this);
/*     */   }
/*     */   
/*     */   public ParArray<T> tail() {
/*  58 */     return (ParArray<T>)ParIterableLike.class.tail(this);
/*     */   }
/*     */   
/*     */   public T last() {
/*  58 */     return (T)ParIterableLike.class.last(this);
/*     */   }
/*     */   
/*     */   public Option<T> lastOption() {
/*  58 */     return ParIterableLike.class.lastOption(this);
/*     */   }
/*     */   
/*     */   public ParArray<T> init() {
/*  58 */     return (ParArray<T>)ParIterableLike.class.init(this);
/*     */   }
/*     */   
/*     */   public ParArray<T> par() {
/*  58 */     return (ParArray<T>)ParIterableLike.class.par(this);
/*     */   }
/*     */   
/*     */   public boolean isStrictSplitterCollection() {
/*  58 */     return ParIterableLike.class.isStrictSplitterCollection(this);
/*     */   }
/*     */   
/*     */   public <S, That> Combiner<S, That> reuse(Option oldc, Combiner newc) {
/*  58 */     return ParIterableLike.class.reuse(this, oldc, newc);
/*     */   }
/*     */   
/*     */   public <R, Tp> Object task2ops(ParIterableLike.StrictSplitterCheckTask tsk) {
/*  58 */     return ParIterableLike.class.task2ops(this, tsk);
/*     */   }
/*     */   
/*     */   public <R> Object wrap(Function0 body) {
/*  58 */     return ParIterableLike.class.wrap(this, body);
/*     */   }
/*     */   
/*     */   public <PI extends DelegatedSignalling> Object delegatedSignalling2ops(DelegatedSignalling it) {
/*  58 */     return ParIterableLike.class.delegatedSignalling2ops(this, it);
/*     */   }
/*     */   
/*     */   public <Elem, To> Object builder2ops(Builder cb) {
/*  58 */     return ParIterableLike.class.builder2ops(this, cb);
/*     */   }
/*     */   
/*     */   public <S, That> Object bf2seq(CanBuildFrom bf) {
/*  58 */     return ParIterableLike.class.bf2seq(this, bf);
/*     */   }
/*     */   
/*     */   public <S, That extends Parallel> ParArray<T> sequentially(Function1 b) {
/*  58 */     return (ParArray<T>)ParIterableLike.class.sequentially(this, b);
/*     */   }
/*     */   
/*     */   public String mkString(String start, String sep, String end) {
/*  58 */     return ParIterableLike.class.mkString(this, start, sep, end);
/*     */   }
/*     */   
/*     */   public String mkString(String sep) {
/*  58 */     return ParIterableLike.class.mkString(this, sep);
/*     */   }
/*     */   
/*     */   public String mkString() {
/*  58 */     return ParIterableLike.class.mkString(this);
/*     */   }
/*     */   
/*     */   public boolean canEqual(Object other) {
/*  58 */     return ParIterableLike.class.canEqual(this, other);
/*     */   }
/*     */   
/*     */   public <U> U reduce(Function2 op) {
/*  58 */     return (U)ParIterableLike.class.reduce(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceOption(Function2 op) {
/*  58 */     return ParIterableLike.class.reduceOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> U fold(Object z, Function2 op) {
/*  58 */     return (U)ParIterableLike.class.fold(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S aggregate(Object z, Function2 seqop, Function2 combop) {
/*  58 */     return (S)ParIterableLike.class.aggregate(this, z, seqop, combop);
/*     */   }
/*     */   
/*     */   public <S> S foldLeft(Object z, Function2 op) {
/*  58 */     return (S)ParIterableLike.class.foldLeft(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S foldRight(Object z, Function2 op) {
/*  58 */     return (S)ParIterableLike.class.foldRight(this, z, op);
/*     */   }
/*     */   
/*     */   public <U> U reduceLeft(Function2 op) {
/*  58 */     return (U)ParIterableLike.class.reduceLeft(this, op);
/*     */   }
/*     */   
/*     */   public <U> U reduceRight(Function2 op) {
/*  58 */     return (U)ParIterableLike.class.reduceRight(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceLeftOption(Function2 op) {
/*  58 */     return ParIterableLike.class.reduceLeftOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> Option<U> reduceRightOption(Function2 op) {
/*  58 */     return ParIterableLike.class.reduceRightOption(this, op);
/*     */   }
/*     */   
/*     */   public <U> void foreach(Function1 f) {
/*  58 */     ParIterableLike.class.foreach(this, f);
/*     */   }
/*     */   
/*     */   public int count(Function1 p) {
/*  58 */     return ParIterableLike.class.count(this, p);
/*     */   }
/*     */   
/*     */   public <U> U sum(Numeric num) {
/*  58 */     return (U)ParIterableLike.class.sum(this, num);
/*     */   }
/*     */   
/*     */   public <U> U product(Numeric num) {
/*  58 */     return (U)ParIterableLike.class.product(this, num);
/*     */   }
/*     */   
/*     */   public <U> T min(Ordering ord) {
/*  58 */     return (T)ParIterableLike.class.min(this, ord);
/*     */   }
/*     */   
/*     */   public <U> T max(Ordering ord) {
/*  58 */     return (T)ParIterableLike.class.max(this, ord);
/*     */   }
/*     */   
/*     */   public <S> T maxBy(Function1 f, Ordering cmp) {
/*  58 */     return (T)ParIterableLike.class.maxBy(this, f, cmp);
/*     */   }
/*     */   
/*     */   public <S> T minBy(Function1 f, Ordering cmp) {
/*  58 */     return (T)ParIterableLike.class.minBy(this, f, cmp);
/*     */   }
/*     */   
/*     */   public <S, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  58 */     return (That)ParIterableLike.class.collect(this, pf, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  58 */     return (That)ParIterableLike.class.flatMap(this, f, bf);
/*     */   }
/*     */   
/*     */   public boolean forall(Function1 pred) {
/*  58 */     return ParIterableLike.class.forall(this, pred);
/*     */   }
/*     */   
/*     */   public boolean exists(Function1 pred) {
/*  58 */     return ParIterableLike.class.exists(this, pred);
/*     */   }
/*     */   
/*     */   public Option<T> find(Function1 pred) {
/*  58 */     return ParIterableLike.class.find(this, pred);
/*     */   }
/*     */   
/*     */   public Object combinerFactory() {
/*  58 */     return ParIterableLike.class.combinerFactory(this);
/*     */   }
/*     */   
/*     */   public <S, That> Object combinerFactory(Function0 cbf) {
/*  58 */     return ParIterableLike.class.combinerFactory(this, cbf);
/*     */   }
/*     */   
/*     */   public ParArray<T> filter(Function1 pred) {
/*  58 */     return (ParArray<T>)ParIterableLike.class.filter(this, pred);
/*     */   }
/*     */   
/*     */   public ParArray<T> filterNot(Function1 pred) {
/*  58 */     return (ParArray<T>)ParIterableLike.class.filterNot(this, pred);
/*     */   }
/*     */   
/*     */   public <U, That> That $plus$plus(GenTraversableOnce that, CanBuildFrom bf) {
/*  58 */     return (That)ParIterableLike.class.$plus$plus(this, that, bf);
/*     */   }
/*     */   
/*     */   public Tuple2<ParArray<T>, ParArray<T>> partition(Function1 pred) {
/*  58 */     return ParIterableLike.class.partition(this, pred);
/*     */   }
/*     */   
/*     */   public <K> ParMap<K, ParArray<T>> groupBy(Function1 f) {
/*  58 */     return ParIterableLike.class.groupBy(this, f);
/*     */   }
/*     */   
/*     */   public ParArray<T> take(int n) {
/*  58 */     return (ParArray<T>)ParIterableLike.class.take(this, n);
/*     */   }
/*     */   
/*     */   public ParArray<T> drop(int n) {
/*  58 */     return (ParArray<T>)ParIterableLike.class.drop(this, n);
/*     */   }
/*     */   
/*     */   public ParArray<T> slice(int unc_from, int unc_until) {
/*  58 */     return (ParArray<T>)ParIterableLike.class.slice(this, unc_from, unc_until);
/*     */   }
/*     */   
/*     */   public Tuple2<ParArray<T>, ParArray<T>> splitAt(int n) {
/*  58 */     return ParIterableLike.class.splitAt(this, n);
/*     */   }
/*     */   
/*     */   public <S, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  58 */     return (That)ParIterableLike.class.scanLeft(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public <S, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  58 */     return (That)ParIterableLike.class.scanRight(this, z, op, bf);
/*     */   }
/*     */   
/*     */   public ParArray<T> takeWhile(Function1 pred) {
/*  58 */     return (ParArray<T>)ParIterableLike.class.takeWhile(this, pred);
/*     */   }
/*     */   
/*     */   public Tuple2<ParArray<T>, ParArray<T>> span(Function1 pred) {
/*  58 */     return ParIterableLike.class.span(this, pred);
/*     */   }
/*     */   
/*     */   public ParArray<T> dropWhile(Function1 pred) {
/*  58 */     return (ParArray<T>)ParIterableLike.class.dropWhile(this, pred);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs) {
/*  58 */     ParIterableLike.class.copyToArray(this, xs);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs, int start) {
/*  58 */     ParIterableLike.class.copyToArray(this, xs, start);
/*     */   }
/*     */   
/*     */   public <U> void copyToArray(Object xs, int start, int len) {
/*  58 */     ParIterableLike.class.copyToArray(this, xs, start, len);
/*     */   }
/*     */   
/*     */   public <U, That> That zipWithIndex(CanBuildFrom bf) {
/*  58 */     return (That)ParIterableLike.class.zipWithIndex(this, bf);
/*     */   }
/*     */   
/*     */   public <S, U, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  58 */     return (That)ParIterableLike.class.zipAll(this, that, thisElem, thatElem, bf);
/*     */   }
/*     */   
/*     */   public <U, That> That toParCollection(Function0 cbf) {
/*  58 */     return (That)ParIterableLike.class.toParCollection(this, cbf);
/*     */   }
/*     */   
/*     */   public <K, V, That> That toParMap(Function0 cbf, scala.Predef$.less.colon.less ev) {
/*  58 */     return (That)ParIterableLike.class.toParMap(this, cbf, ev);
/*     */   }
/*     */   
/*     */   public <U> Object toArray(ClassTag evidence$1) {
/*  58 */     return ParIterableLike.class.toArray(this, evidence$1);
/*     */   }
/*     */   
/*     */   public List<T> toList() {
/*  58 */     return ParIterableLike.class.toList(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<T> toIndexedSeq() {
/*  58 */     return ParIterableLike.class.toIndexedSeq(this);
/*     */   }
/*     */   
/*     */   public Stream<T> toStream() {
/*  58 */     return ParIterableLike.class.toStream(this);
/*     */   }
/*     */   
/*     */   public Iterator<T> toIterator() {
/*  58 */     return ParIterableLike.class.toIterator(this);
/*     */   }
/*     */   
/*     */   public <U> Buffer<U> toBuffer() {
/*  58 */     return ParIterableLike.class.toBuffer(this);
/*     */   }
/*     */   
/*     */   public GenTraversable<T> toTraversable() {
/*  58 */     return ParIterableLike.class.toTraversable(this);
/*     */   }
/*     */   
/*     */   public <U> ParSet<U> toSet() {
/*  58 */     return ParIterableLike.class.toSet(this);
/*     */   }
/*     */   
/*     */   public <K, V> ParMap<K, V> toMap(scala.Predef$.less.colon.less ev) {
/*  58 */     return ParIterableLike.class.toMap(this, ev);
/*     */   }
/*     */   
/*     */   public Vector<T> toVector() {
/*  58 */     return ParIterableLike.class.toVector(this);
/*     */   }
/*     */   
/*     */   public <Col> Col to(CanBuildFrom cbf) {
/*  58 */     return (Col)ParIterableLike.class.to(this, cbf);
/*     */   }
/*     */   
/*     */   public int scanBlockSize() {
/*  58 */     return ParIterableLike.class.scanBlockSize(this);
/*     */   }
/*     */   
/*     */   public <S> S $div$colon(Object z, Function2 op) {
/*  58 */     return (S)ParIterableLike.class.$div$colon(this, z, op);
/*     */   }
/*     */   
/*     */   public <S> S $colon$bslash(Object z, Function2 op) {
/*  58 */     return (S)ParIterableLike.class.$colon$bslash(this, z, op);
/*     */   }
/*     */   
/*     */   public String debugInformation() {
/*  58 */     return ParIterableLike.class.debugInformation(this);
/*     */   }
/*     */   
/*     */   public Seq<String> brokenInvariants() {
/*  58 */     return ParIterableLike.class.brokenInvariants(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<String> debugBuffer() {
/*  58 */     return ParIterableLike.class.debugBuffer(this);
/*     */   }
/*     */   
/*     */   public void debugclear() {
/*  58 */     ParIterableLike.class.debugclear(this);
/*     */   }
/*     */   
/*     */   public ArrayBuffer<String> debuglog(String s) {
/*  58 */     return ParIterableLike.class.debuglog(this, s);
/*     */   }
/*     */   
/*     */   public void printDebugBuffer() {
/*  58 */     ParIterableLike.class.printDebugBuffer(this);
/*     */   }
/*     */   
/*     */   public Combiner<T, ParArray<T>> parCombiner() {
/*  58 */     return CustomParallelizable.class.parCombiner((CustomParallelizable)this);
/*     */   }
/*     */   
/*     */   public Builder<T, ParArray<T>> newBuilder() {
/*  58 */     return GenericParTemplate.class.newBuilder(this);
/*     */   }
/*     */   
/*     */   public Combiner<T, ParArray<T>> newCombiner() {
/*  58 */     return GenericParTemplate.class.newCombiner(this);
/*     */   }
/*     */   
/*     */   public <B> Combiner<B, ParArray<B>> genericBuilder() {
/*  58 */     return GenericParTemplate.class.genericBuilder(this);
/*     */   }
/*     */   
/*     */   public <B> Combiner<B, ParArray<B>> genericCombiner() {
/*  58 */     return GenericParTemplate.class.genericCombiner(this);
/*     */   }
/*     */   
/*     */   public <A1, A2> Tuple2<ParArray<A1>, ParArray<A2>> unzip(Function1 asPair) {
/*  58 */     return GenericTraversableTemplate.class.unzip((GenericTraversableTemplate)this, asPair);
/*     */   }
/*     */   
/*     */   public <A1, A2, A3> Tuple3<ParArray<A1>, ParArray<A2>, ParArray<A3>> unzip3(Function1 asTriple) {
/*  58 */     return GenericTraversableTemplate.class.unzip3((GenericTraversableTemplate)this, asTriple);
/*     */   }
/*     */   
/*     */   public <B> ParArray<B> flatten(Function1 asTraversable) {
/*  58 */     return (ParArray<B>)GenericTraversableTemplate.class.flatten((GenericTraversableTemplate)this, asTraversable);
/*     */   }
/*     */   
/*     */   public <B> ParArray<ParArray<B>> transpose(Function1 asTraversable) {
/*  58 */     return (ParArray<ParArray<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*     */   }
/*     */   
/*     */   public boolean isDefinedAt(int idx) {
/*  58 */     return GenSeqLike.class.isDefinedAt((GenSeqLike)this, idx);
/*     */   }
/*     */   
/*     */   public int prefixLength(Function1 p) {
/*  58 */     return GenSeqLike.class.prefixLength((GenSeqLike)this, p);
/*     */   }
/*     */   
/*     */   public int indexWhere(Function1 p) {
/*  58 */     return GenSeqLike.class.indexWhere((GenSeqLike)this, p);
/*     */   }
/*     */   
/*     */   public <B> int indexOf(Object elem) {
/*  58 */     return GenSeqLike.class.indexOf((GenSeqLike)this, elem);
/*     */   }
/*     */   
/*     */   public <B> int indexOf(Object elem, int from) {
/*  58 */     return GenSeqLike.class.indexOf((GenSeqLike)this, elem, from);
/*     */   }
/*     */   
/*     */   public <B> int lastIndexOf(Object elem) {
/*  58 */     return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem);
/*     */   }
/*     */   
/*     */   public <B> int lastIndexOf(Object elem, int end) {
/*  58 */     return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem, end);
/*     */   }
/*     */   
/*     */   public int lastIndexWhere(Function1 p) {
/*  58 */     return GenSeqLike.class.lastIndexWhere((GenSeqLike)this, p);
/*     */   }
/*     */   
/*     */   public <B> boolean startsWith(GenSeq that) {
/*  58 */     return GenSeqLike.class.startsWith((GenSeqLike)this, that);
/*     */   }
/*     */   
/*     */   public <B, That> That union(GenSeq that, CanBuildFrom bf) {
/*  58 */     return (That)GenSeqLike.class.union((GenSeqLike)this, that, bf);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/*  58 */     return GenSeqLike.class.hashCode((GenSeqLike)this);
/*     */   }
/*     */   
/*     */   public boolean equals(Object that) {
/*  58 */     return GenSeqLike.class.equals((GenSeqLike)this, that);
/*     */   }
/*     */   
/*     */   public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  58 */     return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */   }
/*     */   
/*     */   public ArraySeq<T> arrayseq() {
/*  58 */     return this.arrayseq;
/*     */   }
/*     */   
/*     */   public ParArray(ArraySeq<T> arrayseq) {
/*  58 */     GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*  58 */     Parallelizable.class.$init$((Parallelizable)this);
/*  58 */     GenSeqLike.class.$init$((GenSeqLike)this);
/*  58 */     GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/*  58 */     GenTraversable.class.$init$((GenTraversable)this);
/*  58 */     GenIterable.class.$init$(this);
/*  58 */     GenSeq.class.$init$(this);
/*  58 */     GenericParTemplate.class.$init$(this);
/*  58 */     CustomParallelizable.class.$init$((CustomParallelizable)this);
/*  58 */     ParIterableLike.class.$init$(this);
/*  58 */     ParIterable.class.$init$(this);
/*  58 */     ParIterable$class.$init$(this);
/*  58 */     ParSeqLike.class.$init$(this);
/*  58 */     ParSeq.class.$init$(this);
/*  58 */     ParSeq$class.$init$(this);
/*  66 */     this.scala$collection$parallel$mutable$ParArray$$array = arrayseq.array();
/*     */   }
/*     */   
/*     */   public Object[] scala$collection$parallel$mutable$ParArray$$array() {
/*  66 */     return this.scala$collection$parallel$mutable$ParArray$$array;
/*     */   }
/*     */   
/*     */   private void scala$collection$parallel$mutable$ParArray$$array_$eq(Object[] x$1) {
/*  66 */     this.scala$collection$parallel$mutable$ParArray$$array = x$1;
/*     */   }
/*     */   
/*     */   public GenericCompanion<ParArray> companion() {
/*  68 */     return (GenericCompanion<ParArray>)ParArray$.MODULE$;
/*     */   }
/*     */   
/*     */   public ParArray(int sz) {
/*  70 */     this(
/*     */         
/*  72 */         new ArraySeq(sz));
/*     */   }
/*     */   
/*     */   public T apply(int i) {
/*  75 */     return (T)scala$collection$parallel$mutable$ParArray$$array()[i];
/*     */   }
/*     */   
/*     */   public void update(int i, Object elem) {
/*  77 */     scala$collection$parallel$mutable$ParArray$$array()[i] = elem;
/*     */   }
/*     */   
/*     */   public int length() {
/*  79 */     return arrayseq().length();
/*     */   }
/*     */   
/*     */   public ArraySeq<T> seq() {
/*  81 */     return arrayseq();
/*     */   }
/*     */   
/*     */   public ParArrayIterator splitter() {
/*  84 */     ParArrayIterator pit = new ParArrayIterator(this, ParArrayIterator().$lessinit$greater$default$1(), ParArrayIterator().$lessinit$greater$default$2(), ParArrayIterator().$lessinit$greater$default$3());
/*  85 */     return pit;
/*     */   }
/*     */   
/*     */   private ParArrayIterator$ ParArrayIterator$lzycompute() {
/*  88 */     synchronized (this) {
/*  88 */       if (this.ParArrayIterator$module == null)
/*  88 */         this.ParArrayIterator$module = new ParArrayIterator$(this); 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/collection/parallel/mutable/ParArray}} */
/*  88 */       return this.ParArrayIterator$module;
/*     */     } 
/*     */   }
/*     */   
/*     */   public ParArrayIterator$ ParArrayIterator() {
/*  88 */     return (this.ParArrayIterator$module == null) ? ParArrayIterator$lzycompute() : this.ParArrayIterator$module;
/*     */   }
/*     */   
/*     */   public class ParArrayIterator$ {
/*     */     public int $lessinit$greater$default$1() {
/*  88 */       return 0;
/*     */     }
/*     */     
/*     */     public int $lessinit$greater$default$2() {
/*  88 */       return this.$outer.length();
/*     */     }
/*     */     
/*     */     public Object[] $lessinit$greater$default$3() {
/*  88 */       return this.$outer.scala$collection$parallel$mutable$ParArray$$array();
/*     */     }
/*     */     
/*     */     public ParArrayIterator$(ParArray $outer) {}
/*     */     
/*     */     public class ParArray$ParArrayIterator$$anonfun$1 extends AbstractFunction2.mcIII.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply(int x$1, int x$2) {
/* 104 */         return x$1 + x$2;
/*     */       }
/*     */       
/*     */       public int apply$mcIII$sp(int x$1, int x$2) {
/* 104 */         return x$1 + x$2;
/*     */       }
/*     */       
/*     */       public ParArray$ParArrayIterator$$anonfun$1(ParArray.ParArrayIterator $outer) {}
/*     */     }
/*     */     
/*     */     public class ParArray$ParArrayIterator$$anonfun$psplit$1 extends AbstractFunction1<Object, ParArray<T>.ParArrayIterator> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final IntRef traversed$1;
/*     */       
/*     */       public final ParArray<T>.ParArrayIterator apply(int sz) {
/* 108 */         int start = this.traversed$1.elem;
/* 109 */         int i = this.traversed$1.elem + sz;
/* 109 */         scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 109 */         int end = scala.runtime.RichInt$.MODULE$.min$extension(i, this.$outer.until());
/* 110 */         this.traversed$1.elem = end;
/* 111 */         return (this.traversed$1.elem < this.$outer.until()) ? new ParArray.ParArrayIterator(this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer(), start, end, this.$outer.arr()) : 
/*     */           
/* 113 */           new ParArray.ParArrayIterator(this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer(), this.traversed$1.elem, this.traversed$1.elem, this.$outer.arr());
/*     */       }
/*     */       
/*     */       public ParArray$ParArrayIterator$$anonfun$psplit$1(ParArray.ParArrayIterator $outer, IntRef traversed$1) {}
/*     */     }
/*     */     
/*     */     public class ParArray$ParArrayIterator$$anonfun$copy2builder$2 extends AbstractFunction1<ResizableParArrayCombiner<T>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public ParArray$ParArrayIterator$$anonfun$copy2builder$2(ParArray.ParArrayIterator $outer) {}
/*     */       
/*     */       public final void apply(ResizableParArrayCombiner pac) {
/* 461 */         Object[] targetarr = pac.lastbuff().internalArray();
/* 462 */         scala.Array$.MODULE$.copy(this.$outer.arr(), this.$outer.i(), targetarr, ((SeqLike)pac.lastbuff()).size(), this.$outer.until() - this.$outer.i());
/* 463 */         pac.lastbuff().setInternalSize(this.$outer.remaining());
/*     */       }
/*     */     }
/*     */     
/*     */     public class ParArray$ParArrayIterator$$anonfun$copy2builder$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Builder cb$2;
/*     */       
/*     */       public void apply$mcV$sp() {
/* 465 */         this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().builder2ops(this.cb$2).ifIs(
/* 466 */             (Function1)new ParArray$ParArrayIterator$$anonfun$copy2builder$1$$anonfun$apply$mcV$sp$3(this))
/*     */           
/* 472 */           .otherwise((Function0)new ParArray$ParArrayIterator$$anonfun$copy2builder$1$$anonfun$apply$mcV$sp$1(this), scala.reflect.ClassTag$.MODULE$.apply(UnrolledParArrayCombiner.class));
/*     */       }
/*     */       
/*     */       public class ParArray$ParArrayIterator$$anonfun$copy2builder$1$$anonfun$apply$mcV$sp$3 extends AbstractFunction1<UnrolledParArrayCombiner<T>, BoxedUnit> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public ParArray$ParArrayIterator$$anonfun$copy2builder$1$$anonfun$apply$mcV$sp$3(ParArray$ParArrayIterator$$anonfun$copy2builder$1 $outer) {}
/*     */         
/*     */         public final void apply(UnrolledParArrayCombiner pac) {
/*     */           Object[] targetarr = (Object[])pac.buff().lastPtr().array();
/*     */           scala.Array$.MODULE$.copy(this.$outer.$outer.arr(), this.$outer.$outer.i(), targetarr, 0, this.$outer.$outer.until() - this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i());
/*     */           pac.buff().size_$eq(pac.buff().size() + this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().until() - this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i());
/*     */           pac.buff().lastPtr().size_$eq(this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().until() - this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i());
/*     */         }
/*     */       }
/*     */       
/*     */       public final void apply() {
/* 472 */         apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public ParArray$ParArrayIterator$$anonfun$copy2builder$1(ParArray.ParArrayIterator $outer, Builder cb$2) {}
/*     */       
/*     */       public class ParArray$ParArrayIterator$$anonfun$copy2builder$1$$anonfun$apply$mcV$sp$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final void apply() {
/* 472 */           apply$mcV$sp();
/*     */         }
/*     */         
/*     */         public ParArray$ParArrayIterator$$anonfun$copy2builder$1$$anonfun$apply$mcV$sp$1(ParArray$ParArrayIterator$$anonfun$copy2builder$1 $outer) {}
/*     */         
/*     */         public void apply$mcV$sp() {
/* 473 */           this.$outer.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$copy2builder_quick(this.$outer.cb$2, this.$outer.$outer.arr(), this.$outer.$outer.until(), this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i());
/* 474 */           this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i_$eq(this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().until());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public class ParArray$ParArrayIterator$$anonfun$reverse2combiner$2 extends AbstractFunction1<ResizableParArrayCombiner<T>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public ParArray$ParArrayIterator$$anonfun$reverse2combiner$2(ParArray.ParArrayIterator $outer) {}
/*     */       
/*     */       public final void apply(ResizableParArrayCombiner pac) {
/* 528 */         int sz = this.$outer.remaining();
/* 529 */         pac.sizeHint(sz);
/* 530 */         Object[] targetarr = pac.lastbuff().internalArray();
/* 531 */         this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$reverse2combiner_quick(targetarr, this.$outer.arr(), 0, this.$outer.i(), this.$outer.until());
/* 532 */         pac.lastbuff().setInternalSize(sz);
/*     */       }
/*     */     }
/*     */     
/*     */     public class ParArray$ParArrayIterator$$anonfun$reverse2combiner$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Combiner cb$3;
/*     */       
/*     */       public void apply$mcV$sp() {
/* 534 */         this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().builder2ops((Builder)this.cb$3).ifIs(
/* 535 */             (Function1)new ParArray$ParArrayIterator$$anonfun$reverse2combiner$1$$anonfun$apply$mcV$sp$4(this))
/*     */           
/* 543 */           .otherwise((Function0)new ParArray$ParArrayIterator$$anonfun$reverse2combiner$1$$anonfun$apply$mcV$sp$2(this), scala.reflect.ClassTag$.MODULE$.apply(UnrolledParArrayCombiner.class));
/*     */       }
/*     */       
/*     */       public class ParArray$ParArrayIterator$$anonfun$reverse2combiner$1$$anonfun$apply$mcV$sp$4 extends AbstractFunction1<UnrolledParArrayCombiner<T>, BoxedUnit> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public ParArray$ParArrayIterator$$anonfun$reverse2combiner$1$$anonfun$apply$mcV$sp$4(ParArray$ParArrayIterator$$anonfun$reverse2combiner$1 $outer) {}
/*     */         
/*     */         public final void apply(UnrolledParArrayCombiner pac) {
/*     */           int sz = this.$outer.$outer.remaining();
/*     */           pac.sizeHint(sz);
/*     */           Object[] targetarr = (Object[])pac.buff().lastPtr().array();
/*     */           this.$outer.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$reverse2combiner_quick(targetarr, this.$outer.$outer.arr(), 0, this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i(), this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().until());
/*     */           pac.buff().size_$eq(pac.buff().size() + sz);
/*     */           pac.buff().lastPtr().size_$eq(sz);
/*     */         }
/*     */       }
/*     */       
/*     */       public final void apply() {
/* 543 */         apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public ParArray$ParArrayIterator$$anonfun$reverse2combiner$1(ParArray.ParArrayIterator $outer, Combiner cb$3) {}
/*     */       
/*     */       public class ParArray$ParArrayIterator$$anonfun$reverse2combiner$1$$anonfun$apply$mcV$sp$2 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final void apply() {
/* 543 */           this.$outer.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$super$reverse2combiner(this.$outer.cb$3);
/*     */         }
/*     */         
/*     */         public void apply$mcV$sp() {
/* 543 */           this.$outer.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$super$reverse2combiner(this.$outer.cb$3);
/*     */         }
/*     */         
/*     */         public ParArray$ParArrayIterator$$anonfun$reverse2combiner$1$$anonfun$apply$mcV$sp$2(ParArray$ParArrayIterator$$anonfun$reverse2combiner$1 $outer) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class ParArrayIterator implements SeqSplitter<T> {
/*     */     private int i;
/*     */     
/*     */     private final int until;
/*     */     
/*     */     private final Object[] arr;
/*     */     
/*     */     private Signalling signalDelegate;
/*     */     
/*     */     public Seq<SeqSplitter<T>> splitWithSignalling() {
/*     */       return SeqSplitter.class.splitWithSignalling(this);
/*     */     }
/*     */     
/*     */     public Seq<SeqSplitter<T>> psplitWithSignalling(Seq sizes) {
/*     */       return SeqSplitter.class.psplitWithSignalling(this, sizes);
/*     */     }
/*     */     
/*     */     public SeqSplitter<T>.Taken newTaken(int until) {
/*     */       return SeqSplitter.class.newTaken(this, until);
/*     */     }
/*     */     
/*     */     public SeqSplitter<T> take(int n) {
/*     */       return SeqSplitter.class.take(this, n);
/*     */     }
/*     */     
/*     */     public SeqSplitter<T> slice(int from1, int until1) {
/*     */       return SeqSplitter.class.slice(this, from1, until1);
/*     */     }
/*     */     
/*     */     public <S> SeqSplitter<T>.Mapped<S> map(Function1 f) {
/*     */       return SeqSplitter.class.map(this, f);
/*     */     }
/*     */     
/*     */     public <U, PI extends SeqSplitter<U>> SeqSplitter<T>.Appended<U, PI> appendParSeq(SeqSplitter that) {
/*     */       return SeqSplitter.class.appendParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S> SeqSplitter<T>.Zipped<S> zipParSeq(SeqSplitter that) {
/*     */       return SeqSplitter.class.zipParSeq(this, that);
/*     */     }
/*     */     
/*     */     public <S, U, R> SeqSplitter<T>.ZippedAll<U, R> zipAllParSeq(SeqSplitter that, Object thisElem, Object thatElem) {
/*     */       return SeqSplitter.class.zipAllParSeq(this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public SeqSplitter<T> reverse() {
/*     */       return SeqSplitter.class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> SeqSplitter<T>.Patched<U> patchParSeq(int from, SeqSplitter patchElems, int replaced) {
/*     */       return SeqSplitter.class.patchParSeq(this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public <S> boolean corresponds(Function2 corr, Iterator that) {
/*     */       return AugmentedSeqIterator.class.corresponds((AugmentedSeqIterator)this, corr, that);
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> reverseMap2combiner(Function1 f, Combiner cb) {
/*     */       return AugmentedSeqIterator.class.reverseMap2combiner((AugmentedSeqIterator)this, f, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> updated2combiner(int index, Object elem, Combiner cb) {
/*     */       return AugmentedSeqIterator.class.updated2combiner((AugmentedSeqIterator)this, index, elem, cb);
/*     */     }
/*     */     
/*     */     public Signalling signalDelegate() {
/*     */       return this.signalDelegate;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void signalDelegate_$eq(Signalling x$1) {
/*     */       this.signalDelegate = x$1;
/*     */     }
/*     */     
/*     */     public <S> boolean shouldSplitFurther(ParIterable coll, int parallelismLevel) {
/*     */       return IterableSplitter.class.shouldSplitFurther((IterableSplitter)this, coll, parallelismLevel);
/*     */     }
/*     */     
/*     */     public String buildString(Function1 closure) {
/*     */       return IterableSplitter.class.buildString((IterableSplitter)this, closure);
/*     */     }
/*     */     
/*     */     public String debugInformation() {
/*     */       return IterableSplitter.class.debugInformation((IterableSplitter)this);
/*     */     }
/*     */     
/*     */     public <U extends IterableSplitter<T>.Taken> U newSliceInternal(IterableSplitter.Taken it, int from1) {
/*     */       return (U)IterableSplitter.class.newSliceInternal((IterableSplitter)this, it, from1);
/*     */     }
/*     */     
/*     */     public <U, PI extends IterableSplitter<U>> IterableSplitter<T>.Appended<U, PI> appendParIterable(IterableSplitter that) {
/*     */       return IterableSplitter.class.appendParIterable((IterableSplitter)this, that);
/*     */     }
/*     */     
/*     */     public boolean isAborted() {
/*     */       return DelegatedSignalling.class.isAborted((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public void abort() {
/*     */       DelegatedSignalling.class.abort((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public int indexFlag() {
/*     */       return DelegatedSignalling.class.indexFlag((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public void setIndexFlag(int f) {
/*     */       DelegatedSignalling.class.setIndexFlag((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfGreater(int f) {
/*     */       DelegatedSignalling.class.setIndexFlagIfGreater((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public void setIndexFlagIfLesser(int f) {
/*     */       DelegatedSignalling.class.setIndexFlagIfLesser((DelegatedSignalling)this, f);
/*     */     }
/*     */     
/*     */     public int tag() {
/*     */       return DelegatedSignalling.class.tag((DelegatedSignalling)this);
/*     */     }
/*     */     
/*     */     public <U> U reduce(Function2 op) {
/*     */       return (U)AugmentedIterableIterator.class.reduce((AugmentedIterableIterator)this, op);
/*     */     }
/*     */     
/*     */     public <U> T min(Ordering ord) {
/*     */       return (T)AugmentedIterableIterator.class.min((AugmentedIterableIterator)this, ord);
/*     */     }
/*     */     
/*     */     public <U> T max(Ordering ord) {
/*     */       return (T)AugmentedIterableIterator.class.max((AugmentedIterableIterator)this, ord);
/*     */     }
/*     */     
/*     */     public <U> U reduceLeft(int howmany, Function2 op) {
/*     */       return (U)AugmentedIterableIterator.class.reduceLeft((AugmentedIterableIterator)this, howmany, op);
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> slice2combiner(int from, int until, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.slice2combiner((AugmentedIterableIterator)this, from, until, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> splitAt2combiners(int at, Combiner before, Combiner after) {
/*     */       return AugmentedIterableIterator.class.splitAt2combiners((AugmentedIterableIterator)this, at, before, after);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Object> takeWhile2combiner(Function1 p, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.takeWhile2combiner((AugmentedIterableIterator)this, p, cb);
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> span2combiners(Function1 p, Combiner before, Combiner after) {
/*     */       return AugmentedIterableIterator.class.span2combiners((AugmentedIterableIterator)this, p, before, after);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(Object startValue, Function2 op, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, That> Combiner<U, That> scanToCombiner(int howmany, Object startValue, Function2 op, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.scanToCombiner((AugmentedIterableIterator)this, howmany, startValue, op, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zip2combiner(RemainsIterator otherpit, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.zip2combiner((AugmentedIterableIterator)this, otherpit, cb);
/*     */     }
/*     */     
/*     */     public <U, S, That> Combiner<Tuple2<U, S>, That> zipAll2combiner(RemainsIterator that, Object thiselem, Object thatelem, Combiner cb) {
/*     */       return AugmentedIterableIterator.class.zipAll2combiner((AugmentedIterableIterator)this, that, thiselem, thatelem, cb);
/*     */     }
/*     */     
/*     */     public boolean isRemainingCheap() {
/*     */       return RemainsIterator.class.isRemainingCheap((RemainsIterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<T> seq() {
/*     */       return Iterator.class.seq((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*     */       return Iterator.class.isEmpty((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean isTraversableAgain() {
/*     */       return Iterator.class.isTraversableAgain((Iterator)this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/*     */       return Iterator.class.hasDefiniteSize((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> $plus$plus(Function0 that) {
/*     */       return Iterator.class.$plus$plus((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> flatMap(Function1 f) {
/*     */       return Iterator.class.flatMap((Iterator)this, f);
/*     */     }
/*     */     
/*     */     public Iterator<T> filter(Function1 p) {
/*     */       return Iterator.class.filter((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenTraversableOnce that, Function2 p) {
/*     */       return Iterator.class.corresponds((Iterator)this, that, p);
/*     */     }
/*     */     
/*     */     public Iterator<T> withFilter(Function1 p) {
/*     */       return Iterator.class.withFilter((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Iterator<T> filterNot(Function1 p) {
/*     */       return Iterator.class.filterNot((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> collect(PartialFunction pf) {
/*     */       return Iterator.class.collect((Iterator)this, pf);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanLeft(Object z, Function2 op) {
/*     */       return Iterator.class.scanLeft((Iterator)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> scanRight(Object z, Function2 op) {
/*     */       return Iterator.class.scanRight((Iterator)this, z, op);
/*     */     }
/*     */     
/*     */     public Iterator<T> takeWhile(Function1 p) {
/*     */       return Iterator.class.takeWhile((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<T>, Iterator<T>> partition(Function1 p) {
/*     */       return Iterator.class.partition((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<T>, Iterator<T>> span(Function1 p) {
/*     */       return Iterator.class.span((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public Iterator<T> dropWhile(Function1 p) {
/*     */       return Iterator.class.dropWhile((Iterator)this, p);
/*     */     }
/*     */     
/*     */     public <B> Iterator<Tuple2<T, B>> zip(Iterator that) {
/*     */       return Iterator.class.zip((Iterator)this, that);
/*     */     }
/*     */     
/*     */     public <A1> Iterator<A1> padTo(int len, Object elem) {
/*     */       return Iterator.class.padTo((Iterator)this, len, elem);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<T, Object>> zipWithIndex() {
/*     */       return Iterator.class.zipWithIndex((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B, A1, B1> Iterator<Tuple2<A1, B1>> zipAll(Iterator that, Object thisElem, Object thatElem) {
/*     */       return Iterator.class.zipAll((Iterator)this, that, thisElem, thatElem);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/*     */       return Iterator.class.contains((Iterator)this, elem);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/*     */       return Iterator.class.indexOf((Iterator)this, elem);
/*     */     }
/*     */     
/*     */     public BufferedIterator<T> buffered() {
/*     */       return Iterator.class.buffered((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<T>.GroupedIterator<B> grouped(int size) {
/*     */       return Iterator.class.grouped((Iterator)this, size);
/*     */     }
/*     */     
/*     */     public <B> Iterator<T>.GroupedIterator<B> sliding(int size, int step) {
/*     */       return Iterator.class.sliding((Iterator)this, size, step);
/*     */     }
/*     */     
/*     */     public int length() {
/*     */       return Iterator.class.length((Iterator)this);
/*     */     }
/*     */     
/*     */     public Tuple2<Iterator<T>, Iterator<T>> duplicate() {
/*     */       return Iterator.class.duplicate((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> Iterator<B> patch(int from, Iterator patchElems, int replaced) {
/*     */       return Iterator.class.patch((Iterator)this, from, patchElems, replaced);
/*     */     }
/*     */     
/*     */     public Traversable<T> toTraversable() {
/*     */       return Iterator.class.toTraversable((Iterator)this);
/*     */     }
/*     */     
/*     */     public Iterator<T> toIterator() {
/*     */       return Iterator.class.toIterator((Iterator)this);
/*     */     }
/*     */     
/*     */     public Stream<T> toStream() {
/*     */       return Iterator.class.toStream((Iterator)this);
/*     */     }
/*     */     
/*     */     public <B> int sliding$default$2() {
/*     */       return Iterator.class.sliding$default$2((Iterator)this);
/*     */     }
/*     */     
/*     */     public List<T> reversed() {
/*     */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public int size() {
/*     */       return TraversableOnce.class.size((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/*     */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/*     */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/*     */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/*     */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/*     */       return (B)TraversableOnce.class.foldRight((TraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/*     */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/*     */       return (B)TraversableOnce.class.reduceRight((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/*     */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/*     */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/*     */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*     */     }
/*     */     
/*     */     public <B> T maxBy(Function1 f, Ordering cmp) {
/*     */       return (T)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> T minBy(Function1 f, Ordering cmp) {
/*     */       return (T)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/*     */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/*     */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/*     */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/*     */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<T> toList() {
/*     */       return TraversableOnce.class.toList((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Iterable<T> toIterable() {
/*     */       return TraversableOnce.class.toIterable((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Seq<T> toSeq() {
/*     */       return TraversableOnce.class.toSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<T> toIndexedSeq() {
/*     */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/*     */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/*     */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public Vector<T> toVector() {
/*     */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/*     */       return (Col)TraversableOnce.class.to((TraversableOnce)this, cbf);
/*     */     }
/*     */     
/*     */     public <T, U> scala.collection.immutable.Map<T, U> toMap(scala.Predef$.less.colon.less ev) {
/*     */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/*     */       return TraversableOnce.class.mkString((TraversableOnce)this, start, sep, end);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/*     */       return TraversableOnce.class.mkString((TraversableOnce)this, sep);
/*     */     }
/*     */     
/*     */     public String mkString() {
/*     */       return TraversableOnce.class.mkString((TraversableOnce)this);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*     */       return TraversableOnce.class.addString((TraversableOnce)this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/*     */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/*     */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*     */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*     */     }
/*     */     
/*     */     public int i() {
/*     */       return this.i;
/*     */     }
/*     */     
/*     */     public void i_$eq(int x$1) {
/*     */       this.i = x$1;
/*     */     }
/*     */     
/*     */     public int until() {
/*     */       return this.until;
/*     */     }
/*     */     
/*     */     public Object[] arr() {
/*     */       return this.arr;
/*     */     }
/*     */     
/*     */     public ParArrayIterator(ParArray $outer, int i, int until, Object[] arr) {
/*     */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/*     */       TraversableOnce.class.$init$((TraversableOnce)this);
/*     */       Iterator.class.$init$((Iterator)this);
/*     */       RemainsIterator.class.$init$((RemainsIterator)this);
/*     */       AugmentedIterableIterator.class.$init$((AugmentedIterableIterator)this);
/*     */       DelegatedSignalling.class.$init$((DelegatedSignalling)this);
/*     */       IterableSplitter.class.$init$((IterableSplitter)this);
/*     */       AugmentedSeqIterator.class.$init$((AugmentedSeqIterator)this);
/*     */       SeqSplitter.class.$init$(this);
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/*     */       return (i() < until());
/*     */     }
/*     */     
/*     */     public T next() {
/*     */       Object elem = arr()[i()];
/*     */       i_$eq(i() + 1);
/*     */       return (T)elem;
/*     */     }
/*     */     
/*     */     public int remaining() {
/*     */       return until() - i();
/*     */     }
/*     */     
/*     */     public ParArrayIterator dup() {
/*     */       return new ParArrayIterator(scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer(), i(), until(), arr());
/*     */     }
/*     */     
/*     */     public Seq<ParArrayIterator> psplit(Seq sizesIncomplete) {
/*     */       IntRef traversed = new IntRef(i());
/*     */       int total = BoxesRunTime.unboxToInt(sizesIncomplete.reduceLeft((Function2)new ParArray$ParArrayIterator$$anonfun$1(this)));
/*     */       int left = remaining();
/*     */       Seq sizes = (total >= left) ? sizesIncomplete : (Seq)sizesIncomplete.$colon$plus(BoxesRunTime.boxToInteger(left - total), scala.collection.Seq$.MODULE$.canBuildFrom());
/*     */       return (Seq<ParArrayIterator>)sizes.map((Function1)new ParArray$ParArrayIterator$$anonfun$psplit$1(this, (ParArrayIterator)traversed), scala.collection.Seq$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public class ParArray$ParArrayIterator$$anonfun$1 extends AbstractFunction2.mcIII.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply(int x$1, int x$2) {
/*     */         return x$1 + x$2;
/*     */       }
/*     */       
/*     */       public int apply$mcIII$sp(int x$1, int x$2) {
/*     */         return x$1 + x$2;
/*     */       }
/*     */       
/*     */       public ParArray$ParArrayIterator$$anonfun$1(ParArray.ParArrayIterator $outer) {}
/*     */     }
/*     */     
/*     */     public class ParArray$ParArrayIterator$$anonfun$psplit$1 extends AbstractFunction1<Object, ParArrayIterator> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final IntRef traversed$1;
/*     */       
/*     */       public final ParArray<T>.ParArrayIterator apply(int sz) {
/*     */         int start = this.traversed$1.elem;
/*     */         int i = this.traversed$1.elem + sz;
/*     */         scala.Predef$ predef$ = scala.Predef$.MODULE$;
/*     */         int end = scala.runtime.RichInt$.MODULE$.min$extension(i, this.$outer.until());
/*     */         this.traversed$1.elem = end;
/*     */         return (this.traversed$1.elem < this.$outer.until()) ? new ParArray.ParArrayIterator(this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer(), start, end, this.$outer.arr()) : new ParArray.ParArrayIterator(this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer(), this.traversed$1.elem, this.traversed$1.elem, this.$outer.arr());
/*     */       }
/*     */       
/*     */       public ParArray$ParArrayIterator$$anonfun$psplit$1(ParArray.ParArrayIterator $outer, IntRef traversed$1) {}
/*     */     }
/*     */     
/*     */     public Seq<ParArrayIterator> split() {
/*     */       int left = remaining();
/*     */       int splitpoint = left / 2;
/*     */       (new ParArrayIterator[2])[0] = new ParArrayIterator(scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer(), i(), i() + splitpoint, arr());
/*     */       (new ParArrayIterator[2])[1] = new ParArrayIterator(scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer(), i() + splitpoint, until(), arr());
/*     */       Seq<ParArrayIterator> sq = (Seq)scala.collection.Seq$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new ParArrayIterator[2]));
/*     */       i_$eq(until());
/*     */       (new ParArrayIterator[1])[0] = this;
/*     */       return (left >= 2) ? sq : (Seq<ParArrayIterator>)scala.collection.Seq$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new ParArrayIterator[1]));
/*     */     }
/*     */     
/*     */     public String toString() {
/*     */       return (new StringBuilder()).append("ParArrayIterator(").append(BoxesRunTime.boxToInteger(i())).append(", ").append(BoxesRunTime.boxToInteger(until())).append(")").toString();
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1<T, ?> f) {
/*     */       foreach_quick(f, arr(), until(), i());
/*     */       i_$eq(until());
/*     */     }
/*     */     
/*     */     private <U> void foreach_quick(Function1 f, Object[] a, int ntil, int from) {
/*     */       int j = from;
/*     */       while (j < ntil) {
/*     */         f.apply(a[j]);
/*     */         j++;
/*     */       } 
/*     */     }
/*     */     
/*     */     public int count(Function1<T, Object> p) {
/*     */       int c = count_quick(p, arr(), until(), i());
/*     */       i_$eq(until());
/*     */       return c;
/*     */     }
/*     */     
/*     */     private int count_quick(Function1 p, Object[] a, int ntil, int from) {
/*     */       int cnt = 0;
/*     */       int j = from;
/*     */       while (j < ntil) {
/*     */         if (BoxesRunTime.unboxToBoolean(p.apply(a[j])))
/*     */           cnt++; 
/*     */         j++;
/*     */       } 
/*     */       return cnt;
/*     */     }
/*     */     
/*     */     public <S> S foldLeft(Object z, Function2<Object, T, Object> op) {
/*     */       Object r = foldLeft_quick(arr(), until(), op, z);
/*     */       i_$eq(until());
/*     */       return (S)r;
/*     */     }
/*     */     
/*     */     private <S> S foldLeft_quick(Object[] a, int ntil, Function2 op, Object z) {
/*     */       int j = i();
/*     */       Object sum = z;
/*     */       while (j < ntil) {
/*     */         sum = op.apply(sum, a[j]);
/*     */         j++;
/*     */       } 
/*     */       return (S)sum;
/*     */     }
/*     */     
/*     */     public <U> U fold(Object z, Function2<U, T, U> op) {
/*     */       return foldLeft((U)z, op);
/*     */     }
/*     */     
/*     */     public <S> S aggregate(Object z, Function2<S, T, S> seqop, Function2 combop) {
/*     */       return foldLeft((S)z, seqop);
/*     */     }
/*     */     
/*     */     public <U> U sum(Numeric num) {
/*     */       Object s = sum_quick(num, arr(), until(), i(), num.zero());
/*     */       i_$eq(until());
/*     */       return (U)s;
/*     */     }
/*     */     
/*     */     private <U> U sum_quick(Numeric num, Object[] a, int ntil, int from, Object zero) {
/*     */       int j = from;
/*     */       Object sum = zero;
/*     */       while (j < ntil) {
/*     */         sum = num.plus(sum, a[j]);
/*     */         j++;
/*     */       } 
/*     */       return (U)sum;
/*     */     }
/*     */     
/*     */     public <U> U product(Numeric num) {
/*     */       Object p = product_quick(num, arr(), until(), i(), num.one());
/*     */       i_$eq(until());
/*     */       return (U)p;
/*     */     }
/*     */     
/*     */     private <U> U product_quick(Numeric num, Object[] a, int ntil, int from, Object one) {
/*     */       int j = from;
/*     */       Object prod = one;
/*     */       while (j < ntil) {
/*     */         prod = num.times(prod, a[j]);
/*     */         j++;
/*     */       } 
/*     */       return (U)prod;
/*     */     }
/*     */     
/*     */     public boolean forall(Function1<T, Object> p) {
/*     */       if (isAborted())
/*     */         return false; 
/*     */       boolean all = true;
/*     */       while (i() < until()) {
/*     */         int nextuntil = (i() + scala.collection.parallel.package$.MODULE$.CHECK_RATE() > until()) ? until() : (i() + scala.collection.parallel.package$.MODULE$.CHECK_RATE());
/*     */         if (all = forall_quick(p, scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().scala$collection$parallel$mutable$ParArray$$array(), nextuntil, i())) {
/*     */           i_$eq(nextuntil);
/*     */         } else {
/*     */           i_$eq(until());
/*     */           abort();
/*     */         } 
/*     */         if (isAborted())
/*     */           return false; 
/*     */       } 
/*     */       return all;
/*     */     }
/*     */     
/*     */     private boolean forall_quick(Function1 p, Object[] a, int nextuntil, int start) {
/*     */       int j = start;
/*     */       while (j < nextuntil) {
/*     */         if (BoxesRunTime.unboxToBoolean(p.apply(a[j]))) {
/*     */           j++;
/*     */           continue;
/*     */         } 
/*     */         return false;
/*     */       } 
/*     */       return true;
/*     */     }
/*     */     
/*     */     public boolean exists(Function1<T, Object> p) {
/*     */       if (isAborted())
/*     */         return true; 
/*     */       boolean some = false;
/*     */       while (i() < until()) {
/*     */         int nextuntil = (i() + scala.collection.parallel.package$.MODULE$.CHECK_RATE() > until()) ? until() : (i() + scala.collection.parallel.package$.MODULE$.CHECK_RATE());
/*     */         if (some = exists_quick(p, scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().scala$collection$parallel$mutable$ParArray$$array(), nextuntil, i())) {
/*     */           i_$eq(until());
/*     */           abort();
/*     */         } else {
/*     */           i_$eq(nextuntil);
/*     */         } 
/*     */         if (isAborted())
/*     */           return true; 
/*     */       } 
/*     */       return some;
/*     */     }
/*     */     
/*     */     private boolean exists_quick(Function1 p, Object[] a, int nextuntil, int start) {
/*     */       int j = start;
/*     */       while (j < nextuntil) {
/*     */         if (BoxesRunTime.unboxToBoolean(p.apply(a[j])))
/*     */           return true; 
/*     */         j++;
/*     */       } 
/*     */       return false;
/*     */     }
/*     */     
/*     */     public Option<T> find(Function1 p) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: invokevirtual isAborted : ()Z
/*     */       //   4: ifeq -> 11
/*     */       //   7: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   10: areturn
/*     */       //   11: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   14: astore_2
/*     */       //   15: aload_0
/*     */       //   16: invokevirtual i : ()I
/*     */       //   19: aload_0
/*     */       //   20: invokevirtual until : ()I
/*     */       //   23: if_icmpge -> 137
/*     */       //   26: aload_0
/*     */       //   27: invokevirtual i : ()I
/*     */       //   30: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*     */       //   33: invokevirtual CHECK_RATE : ()I
/*     */       //   36: iadd
/*     */       //   37: aload_0
/*     */       //   38: invokevirtual until : ()I
/*     */       //   41: if_icmpge -> 58
/*     */       //   44: aload_0
/*     */       //   45: invokevirtual i : ()I
/*     */       //   48: getstatic scala/collection/parallel/package$.MODULE$ : Lscala/collection/parallel/package$;
/*     */       //   51: invokevirtual CHECK_RATE : ()I
/*     */       //   54: iadd
/*     */       //   55: goto -> 62
/*     */       //   58: aload_0
/*     */       //   59: invokevirtual until : ()I
/*     */       //   62: istore_3
/*     */       //   63: aload_0
/*     */       //   64: aload_1
/*     */       //   65: aload_0
/*     */       //   66: invokevirtual scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer : ()Lscala/collection/parallel/mutable/ParArray;
/*     */       //   69: invokevirtual scala$collection$parallel$mutable$ParArray$$array : ()[Ljava/lang/Object;
/*     */       //   72: iload_3
/*     */       //   73: aload_0
/*     */       //   74: invokevirtual i : ()I
/*     */       //   77: invokespecial find_quick : (Lscala/Function1;[Ljava/lang/Object;II)Lscala/Option;
/*     */       //   80: dup
/*     */       //   81: astore_2
/*     */       //   82: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   85: astore #4
/*     */       //   87: dup
/*     */       //   88: ifnonnull -> 100
/*     */       //   91: pop
/*     */       //   92: aload #4
/*     */       //   94: ifnull -> 108
/*     */       //   97: goto -> 116
/*     */       //   100: aload #4
/*     */       //   102: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   105: ifeq -> 116
/*     */       //   108: aload_0
/*     */       //   109: iload_3
/*     */       //   110: invokevirtual i_$eq : (I)V
/*     */       //   113: goto -> 128
/*     */       //   116: aload_0
/*     */       //   117: aload_0
/*     */       //   118: invokevirtual until : ()I
/*     */       //   121: invokevirtual i_$eq : (I)V
/*     */       //   124: aload_0
/*     */       //   125: invokevirtual abort : ()V
/*     */       //   128: aload_0
/*     */       //   129: invokevirtual isAborted : ()Z
/*     */       //   132: ifeq -> 15
/*     */       //   135: aload_2
/*     */       //   136: areturn
/*     */       //   137: aload_2
/*     */       //   138: areturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #276	-> 0
/*     */       //   #278	-> 11
/*     */       //   #279	-> 15
/*     */       //   #280	-> 26
/*     */       //   #282	-> 63
/*     */       //   #284	-> 82
/*     */       //   #287	-> 108
/*     */       //   #285	-> 116
/*     */       //   #286	-> 124
/*     */       //   #289	-> 128
/*     */       //   #291	-> 137
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	139	0	this	Lscala/collection/parallel/mutable/ParArray$ParArrayIterator;
/*     */       //   0	139	1	p	Lscala/Function1;
/*     */       //   15	123	2	r	Lscala/Option;
/*     */       //   63	76	3	nextuntil	I
/*     */     }
/*     */     
/*     */     private Option<T> find_quick(Function1 p, Object[] a, int nextuntil, int start) {
/*     */       int j = start;
/*     */       while (j < nextuntil) {
/*     */         Object elem = a[j];
/*     */         if (BoxesRunTime.unboxToBoolean(p.apply(elem)))
/*     */           return (Option<T>)new Some(elem); 
/*     */         j++;
/*     */       } 
/*     */       return (Option<T>)scala.None$.MODULE$;
/*     */     }
/*     */     
/*     */     public ParArrayIterator drop(int n) {
/*     */       i_$eq(i() + n);
/*     */       return this;
/*     */     }
/*     */     
/*     */     public <U> void copyToArray(Object array, int from, int len) {
/*     */       int i = scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().length() - i();
/*     */       scala.Predef$ predef$1 = scala.Predef$.MODULE$;
/*     */       int j = scala.runtime.RichInt$.MODULE$.min$extension(i, len);
/*     */       scala.Predef$ predef$2 = scala.Predef$.MODULE$;
/*     */       int totallen = scala.runtime.RichInt$.MODULE$.min$extension(j, scala.runtime.ScalaRunTime$.MODULE$.array_length(array) - from);
/*     */       scala.Array$.MODULE$.copy(arr(), i(), array, from, totallen);
/*     */       i_$eq(i() + totallen);
/*     */     }
/*     */     
/*     */     public int prefixLength(Function1<T, Object> pred) {
/*     */       int r = prefixLength_quick(pred, arr(), until(), i());
/*     */       i_$eq(i() + r + 1);
/*     */       return r;
/*     */     }
/*     */     
/*     */     private int prefixLength_quick(Function1 pred, Object[] a, int ntil, int startpos) {
/*     */       int j = startpos;
/*     */       int endpos = ntil;
/*     */       while (j < endpos) {
/*     */         if (BoxesRunTime.unboxToBoolean(pred.apply(a[j]))) {
/*     */           j++;
/*     */           continue;
/*     */         } 
/*     */         endpos = j;
/*     */       } 
/*     */       return endpos - startpos;
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1<T, Object> pred) {
/*     */       int r = indexWhere_quick(pred, arr(), until(), i());
/*     */       int ret = (r != -1) ? (r - i()) : r;
/*     */       i_$eq(until());
/*     */       return ret;
/*     */     }
/*     */     
/*     */     private int indexWhere_quick(Function1 pred, Object[] a, int ntil, int from) {
/*     */       int j = from;
/*     */       int pos = -1;
/*     */       while (j < ntil) {
/*     */         if (BoxesRunTime.unboxToBoolean(pred.apply(a[j]))) {
/*     */           pos = j;
/*     */           j = ntil;
/*     */           continue;
/*     */         } 
/*     */         j++;
/*     */       } 
/*     */       return pos;
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1<T, Object> pred) {
/*     */       int r = lastIndexWhere_quick(pred, arr(), i(), until());
/*     */       int ret = (r != -1) ? (r - i()) : r;
/*     */       i_$eq(until());
/*     */       return ret;
/*     */     }
/*     */     
/*     */     private int lastIndexWhere_quick(Function1 pred, Object[] a, int from, int ntil) {
/*     */       int pos = -1;
/*     */       int j = ntil - 1;
/*     */       while (j >= from) {
/*     */         if (BoxesRunTime.unboxToBoolean(pred.apply(a[j]))) {
/*     */           pos = j;
/*     */           j = -1;
/*     */           continue;
/*     */         } 
/*     */         j--;
/*     */       } 
/*     */       return pos;
/*     */     }
/*     */     
/*     */     public boolean sameElements(Iterator that) {
/*     */       boolean same = true;
/*     */       while (i() < until() && that.hasNext()) {
/*     */         Object object2 = that.next();
/*     */         Object object1;
/*     */         if (!(((object1 = arr()[i()]) == object2) ? 1 : ((object1 == null) ? 0 : ((object1 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object1, object2) : ((object1 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object1, object2) : object1.equals(object2)))))) {
/*     */           i_$eq(until());
/*     */           same = false;
/*     */         } 
/*     */         i_$eq(i() + 1);
/*     */       } 
/*     */       return same;
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> map2combiner(Function1<T, ?> f, Combiner<S, That> cb) {
/*     */       cb.sizeHint(remaining());
/*     */       map2combiner_quick(f, arr(), (Builder<?, ?>)cb, until(), i());
/*     */       i_$eq(until());
/*     */       return cb;
/*     */     }
/*     */     
/*     */     private <S, That> void map2combiner_quick(Function1 f, Object[] a, Builder cb, int ntil, int from) {
/*     */       int j = from;
/*     */       while (j < ntil) {
/*     */         cb.$plus$eq(f.apply(a[j]));
/*     */         j++;
/*     */       } 
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> collect2combiner(PartialFunction<T, ?> pf, Combiner<S, That> cb) {
/*     */       collect2combiner_quick(pf, arr(), (Builder<?, ?>)cb, until(), i());
/*     */       i_$eq(until());
/*     */       return cb;
/*     */     }
/*     */     
/*     */     private <S, That> void collect2combiner_quick(PartialFunction pf, Object[] a, Builder cb, int ntil, int from) {
/*     */       int j = from;
/*     */       while (j < ntil) {
/*     */         Object curr = a[j];
/*     */         pf.isDefinedAt(curr) ? cb.$plus$eq(pf.apply(curr)) : BoxedUnit.UNIT;
/*     */         j++;
/*     */       } 
/*     */     }
/*     */     
/*     */     public <S, That> Combiner<S, That> flatmap2combiner(Function1 f, Combiner<S, That> cb) {
/*     */       while (i() < until()) {
/*     */         GenTraversableOnce traversable = (GenTraversableOnce)f.apply(arr()[i()]);
/*     */         (traversable instanceof Iterable) ? cb.$plus$plus$eq((TraversableOnce)((Iterable)traversable).iterator()) : cb.$plus$plus$eq(traversable.seq());
/*     */         i_$eq(i() + 1);
/*     */       } 
/*     */       return cb;
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filter2combiner(Function1<T, Object> pred, Combiner<U, This> cb) {
/*     */       filter2combiner_quick(pred, (Builder<?, ?>)cb, arr(), until(), i());
/*     */       i_$eq(until());
/*     */       return cb;
/*     */     }
/*     */     
/*     */     private <U, This> void filter2combiner_quick(Function1 pred, Builder cb, Object[] a, int ntil, int from) {
/*     */       int j = i();
/*     */       while (j < ntil) {
/*     */         Object curr = a[j];
/*     */         BoxesRunTime.unboxToBoolean(pred.apply(curr)) ? cb.$plus$eq(curr) : BoxedUnit.UNIT;
/*     */         j++;
/*     */       } 
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> filterNot2combiner(Function1<T, Object> pred, Combiner<U, This> cb) {
/*     */       filterNot2combiner_quick(pred, (Builder<?, ?>)cb, arr(), until(), i());
/*     */       i_$eq(until());
/*     */       return cb;
/*     */     }
/*     */     
/*     */     private <U, This> void filterNot2combiner_quick(Function1 pred, Builder cb, Object[] a, int ntil, int from) {
/*     */       int j = i();
/*     */       while (j < ntil) {
/*     */         Object curr = a[j];
/*     */         BoxesRunTime.unboxToBoolean(pred.apply(curr)) ? BoxedUnit.UNIT : cb.$plus$eq(curr);
/*     */         j++;
/*     */       } 
/*     */     }
/*     */     
/*     */     public <U, Coll, Bld extends Builder<U, Coll>> Bld copy2builder(Builder cb) {
/*     */       cb.sizeHint(remaining());
/*     */       scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().builder2ops(cb).ifIs((Function1)new ParArray$ParArrayIterator$$anonfun$copy2builder$2(this)).otherwise((Function0)new ParArray$ParArrayIterator$$anonfun$copy2builder$1(this, (ParArrayIterator)cb), scala.reflect.ClassTag$.MODULE$.apply(ResizableParArrayCombiner.class));
/*     */       return (Bld)cb;
/*     */     }
/*     */     
/*     */     public class ParArray$ParArrayIterator$$anonfun$copy2builder$2 extends AbstractFunction1<ResizableParArrayCombiner<T>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public ParArray$ParArrayIterator$$anonfun$copy2builder$2(ParArray.ParArrayIterator $outer) {}
/*     */       
/*     */       public final void apply(ResizableParArrayCombiner pac) {
/*     */         Object[] targetarr = pac.lastbuff().internalArray();
/*     */         scala.Array$.MODULE$.copy(this.$outer.arr(), this.$outer.i(), targetarr, ((SeqLike)pac.lastbuff()).size(), this.$outer.until() - this.$outer.i());
/*     */         pac.lastbuff().setInternalSize(this.$outer.remaining());
/*     */       }
/*     */     }
/*     */     
/*     */     public class ParArray$ParArrayIterator$$anonfun$copy2builder$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Builder cb$2;
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().builder2ops(this.cb$2).ifIs((Function1)new ParArray$ParArrayIterator$$anonfun$copy2builder$1$$anonfun$apply$mcV$sp$3(this)).otherwise((Function0)new ParArray$ParArrayIterator$$anonfun$copy2builder$1$$anonfun$apply$mcV$sp$1(this), scala.reflect.ClassTag$.MODULE$.apply(UnrolledParArrayCombiner.class));
/*     */       }
/*     */       
/*     */       public class ParArray$ParArrayIterator$$anonfun$copy2builder$1$$anonfun$apply$mcV$sp$3 extends AbstractFunction1<UnrolledParArrayCombiner<T>, BoxedUnit> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public ParArray$ParArrayIterator$$anonfun$copy2builder$1$$anonfun$apply$mcV$sp$3(ParArray$ParArrayIterator$$anonfun$copy2builder$1 $outer) {}
/*     */         
/*     */         public final void apply(UnrolledParArrayCombiner pac) {
/*     */           Object[] targetarr = (Object[])pac.buff().lastPtr().array();
/*     */           scala.Array$.MODULE$.copy(this.$outer.$outer.arr(), this.$outer.$outer.i(), targetarr, 0, this.$outer.$outer.until() - this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i());
/*     */           pac.buff().size_$eq(pac.buff().size() + this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().until() - this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i());
/*     */           pac.buff().lastPtr().size_$eq(this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().until() - this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i());
/*     */         }
/*     */       }
/*     */       
/*     */       public final void apply() {
/*     */         apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public ParArray$ParArrayIterator$$anonfun$copy2builder$1(ParArray.ParArrayIterator $outer, Builder cb$2) {}
/*     */       
/*     */       public class ParArray$ParArrayIterator$$anonfun$copy2builder$1$$anonfun$apply$mcV$sp$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final void apply() {
/*     */           apply$mcV$sp();
/*     */         }
/*     */         
/*     */         public ParArray$ParArrayIterator$$anonfun$copy2builder$1$$anonfun$apply$mcV$sp$1(ParArray$ParArrayIterator$$anonfun$copy2builder$1 $outer) {}
/*     */         
/*     */         public void apply$mcV$sp() {
/*     */           this.$outer.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$copy2builder_quick(this.$outer.cb$2, this.$outer.$outer.arr(), this.$outer.$outer.until(), this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i());
/*     */           this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i_$eq(this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().until());
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public <U, Coll> void scala$collection$parallel$mutable$ParArray$ParArrayIterator$$copy2builder_quick(Builder b, Object[] a, int ntil, int from) {
/*     */       int j = from;
/*     */       while (j < ntil) {
/*     */         b.$plus$eq(a[j]);
/*     */         j++;
/*     */       } 
/*     */     }
/*     */     
/*     */     public <U, This> Tuple2<Combiner<U, This>, Combiner<U, This>> partition2combiners(Function1<T, Object> pred, Combiner btrue, Combiner bfalse) {
/*     */       partition2combiners_quick(pred, (Builder<?, ?>)btrue, (Builder<?, ?>)bfalse, arr(), until(), i());
/*     */       i_$eq(until());
/*     */       return new Tuple2(btrue, bfalse);
/*     */     }
/*     */     
/*     */     private <U, This> void partition2combiners_quick(Function1 p, Builder btrue, Builder bfalse, Object[] a, int ntil, int from) {
/*     */       int j = from;
/*     */       while (j < ntil) {
/*     */         Object curr = a[j];
/*     */         BoxesRunTime.unboxToBoolean(p.apply(curr)) ? btrue.$plus$eq(curr) : bfalse.$plus$eq(curr);
/*     */         j++;
/*     */       } 
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> take2combiner(int n, Combiner<U, This> cb) {
/*     */       cb.sizeHint(n);
/*     */       int ntil = i() + n;
/*     */       Object[] a = arr();
/*     */       while (i() < ntil) {
/*     */         cb.$plus$eq(a[i()]);
/*     */         i_$eq(i() + 1);
/*     */       } 
/*     */       return cb;
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> drop2combiner(int n, Combiner<U, This> cb) {
/*     */       drop(n);
/*     */       cb.sizeHint(remaining());
/*     */       while (i() < until()) {
/*     */         cb.$plus$eq(arr()[i()]);
/*     */         i_$eq(i() + 1);
/*     */       } 
/*     */       return cb;
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> reverse2combiner(Combiner<U, This> cb) {
/* 543 */       scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().builder2ops((Builder)cb).ifIs((Function1)new ParArray$ParArrayIterator$$anonfun$reverse2combiner$2(this)).otherwise((Function0)new ParArray$ParArrayIterator$$anonfun$reverse2combiner$1(this, (ParArrayIterator)cb), scala.reflect.ClassTag$.MODULE$.apply(ResizableParArrayCombiner.class));
/* 545 */       return cb;
/*     */     }
/*     */     
/*     */     public class ParArray$ParArrayIterator$$anonfun$reverse2combiner$2 extends AbstractFunction1<ResizableParArrayCombiner<T>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public ParArray$ParArrayIterator$$anonfun$reverse2combiner$2(ParArray.ParArrayIterator $outer) {}
/*     */       
/*     */       public final void apply(ResizableParArrayCombiner pac) {
/*     */         int sz = this.$outer.remaining();
/*     */         pac.sizeHint(sz);
/*     */         Object[] targetarr = pac.lastbuff().internalArray();
/*     */         this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$reverse2combiner_quick(targetarr, this.$outer.arr(), 0, this.$outer.i(), this.$outer.until());
/*     */         pac.lastbuff().setInternalSize(sz);
/*     */       }
/*     */     }
/*     */     
/*     */     public class ParArray$ParArrayIterator$$anonfun$reverse2combiner$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Combiner cb$3;
/*     */       
/*     */       public void apply$mcV$sp() {
/*     */         this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().builder2ops((Builder)this.cb$3).ifIs((Function1)new ParArray$ParArrayIterator$$anonfun$reverse2combiner$1$$anonfun$apply$mcV$sp$4(this)).otherwise((Function0)new ParArray$ParArrayIterator$$anonfun$reverse2combiner$1$$anonfun$apply$mcV$sp$2(this), scala.reflect.ClassTag$.MODULE$.apply(UnrolledParArrayCombiner.class));
/*     */       }
/*     */       
/*     */       public class ParArray$ParArrayIterator$$anonfun$reverse2combiner$1$$anonfun$apply$mcV$sp$4 extends AbstractFunction1<UnrolledParArrayCombiner<T>, BoxedUnit> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public ParArray$ParArrayIterator$$anonfun$reverse2combiner$1$$anonfun$apply$mcV$sp$4(ParArray$ParArrayIterator$$anonfun$reverse2combiner$1 $outer) {}
/*     */         
/*     */         public final void apply(UnrolledParArrayCombiner pac) {
/*     */           int sz = this.$outer.$outer.remaining();
/*     */           pac.sizeHint(sz);
/*     */           Object[] targetarr = (Object[])pac.buff().lastPtr().array();
/*     */           this.$outer.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$reverse2combiner_quick(targetarr, this.$outer.$outer.arr(), 0, this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().i(), this.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$anonfun$$$outer().until());
/*     */           pac.buff().size_$eq(pac.buff().size() + sz);
/*     */           pac.buff().lastPtr().size_$eq(sz);
/*     */         }
/*     */       }
/*     */       
/*     */       public final void apply() {
/*     */         apply$mcV$sp();
/*     */       }
/*     */       
/*     */       public ParArray$ParArrayIterator$$anonfun$reverse2combiner$1(ParArray.ParArrayIterator $outer, Combiner cb$3) {}
/*     */       
/*     */       public class ParArray$ParArrayIterator$$anonfun$reverse2combiner$1$$anonfun$apply$mcV$sp$2 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final void apply() {
/*     */           this.$outer.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$super$reverse2combiner(this.$outer.cb$3);
/*     */         }
/*     */         
/*     */         public void apply$mcV$sp() {
/*     */           this.$outer.$outer.scala$collection$parallel$mutable$ParArray$ParArrayIterator$$super$reverse2combiner(this.$outer.cb$3);
/*     */         }
/*     */         
/*     */         public ParArray$ParArrayIterator$$anonfun$reverse2combiner$1$$anonfun$apply$mcV$sp$2(ParArray$ParArrayIterator$$anonfun$reverse2combiner$1 $outer) {}
/*     */       }
/*     */     }
/*     */     
/*     */     public <U, This> Combiner<U, This> scala$collection$parallel$mutable$ParArray$ParArrayIterator$$super$reverse2combiner(Combiner cb) {
/*     */       return AugmentedSeqIterator.class.reverse2combiner((AugmentedSeqIterator)this, cb);
/*     */     }
/*     */     
/*     */     public void scala$collection$parallel$mutable$ParArray$ParArrayIterator$$reverse2combiner_quick(Object[] targ, Object[] a, int targfrom, int srcfrom, int srcuntil) {
/* 549 */       int j = srcfrom;
/* 550 */       int k = targfrom + srcuntil - srcfrom - 1;
/* 551 */       while (j < srcuntil) {
/* 552 */         targ[k] = a[j];
/* 553 */         j++;
/* 554 */         k--;
/*     */       } 
/*     */     }
/*     */     
/*     */     public <U, A> void scanToArray(Object z, Function2<Object, Object, Object> op, Object destarr, int from) {
/* 559 */       scanToArray_quick(scala$collection$parallel$mutable$ParArray$ParArrayIterator$$$outer().scala$collection$parallel$mutable$ParArray$$array(), (Object[])destarr, op, z, i(), until(), from);
/* 560 */       i_$eq(until());
/*     */     }
/*     */     
/*     */     public <U> void scanToArray_quick(Object[] srcarr, Object[] destarr, Function2 op, Object z, int srcfrom, int srcntil, int destfrom) {
/* 564 */       Object last = z;
/* 565 */       int j = srcfrom;
/* 566 */       int k = destfrom;
/* 567 */       while (j < srcntil) {
/* 568 */         last = op.apply(last, srcarr[j]);
/* 569 */         destarr[k] = last;
/* 570 */         j++;
/* 571 */         k++;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private <R, Tp> Task<R, Tp> asTask(Object t) {
/* 579 */     return (Task<R, Tp>)t;
/*     */   }
/*     */   
/*     */   private <S, That> boolean buildsArray(Builder c) {
/* 581 */     return c instanceof ResizableParArrayCombiner;
/*     */   }
/*     */   
/*     */   public <S, That> That map(Function1 f, CanBuildFrom bf) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_2
/*     */     //   2: aload_0
/*     */     //   3: invokevirtual repr : ()Lscala/collection/parallel/ParIterable;
/*     */     //   6: invokeinterface apply : (Ljava/lang/Object;)Lscala/collection/mutable/Builder;
/*     */     //   11: invokespecial buildsArray : (Lscala/collection/mutable/Builder;)Z
/*     */     //   14: ifeq -> 72
/*     */     //   17: new scala/collection/mutable/ArraySeq
/*     */     //   20: dup
/*     */     //   21: aload_0
/*     */     //   22: invokevirtual length : ()I
/*     */     //   25: invokespecial <init> : (I)V
/*     */     //   28: astore_3
/*     */     //   29: aload_3
/*     */     //   30: invokevirtual array : ()[Ljava/lang/Object;
/*     */     //   33: astore #4
/*     */     //   35: aload_0
/*     */     //   36: invokevirtual tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*     */     //   39: new scala/collection/parallel/mutable/ParArray$Map
/*     */     //   42: dup
/*     */     //   43: aload_0
/*     */     //   44: aload_1
/*     */     //   45: aload #4
/*     */     //   47: iconst_0
/*     */     //   48: aload_0
/*     */     //   49: invokevirtual length : ()I
/*     */     //   52: invokespecial <init> : (Lscala/collection/parallel/mutable/ParArray;Lscala/Function1;[Ljava/lang/Object;II)V
/*     */     //   55: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*     */     //   60: pop
/*     */     //   61: new scala/collection/parallel/mutable/ParArray
/*     */     //   64: dup
/*     */     //   65: aload_3
/*     */     //   66: invokespecial <init> : (Lscala/collection/mutable/ArraySeq;)V
/*     */     //   69: goto -> 78
/*     */     //   72: aload_0
/*     */     //   73: aload_1
/*     */     //   74: aload_2
/*     */     //   75: invokestatic map : (Lscala/collection/parallel/ParIterableLike;Lscala/Function1;Lscala/collection/generic/CanBuildFrom;)Ljava/lang/Object;
/*     */     //   78: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #583	-> 0
/*     */     //   #585	-> 17
/*     */     //   #586	-> 29
/*     */     //   #589	-> 35
/*     */     //   #592	-> 61
/*     */     //   #593	-> 72
/*     */     //   #583	-> 78
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	79	0	this	Lscala/collection/parallel/mutable/ParArray;
/*     */     //   0	79	1	f	Lscala/Function1;
/*     */     //   0	79	2	bf	Lscala/collection/generic/CanBuildFrom;
/*     */     //   29	40	3	targarrseq	Lscala/collection/mutable/ArraySeq;
/*     */     //   35	34	4	targetarr	[Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public <U, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 596 */     if (tasksupport().parallelismLevel() > 1 && buildsArray(cbf.apply(repr()))) {
/* 598 */       ArraySeq targarrseq = new ArraySeq(length() + 1);
/* 599 */       Object[] targetarr = targarrseq.array();
/* 600 */       targetarr[0] = z;
/* 603 */       (length() > 0) ? tasksupport().executeAndWaitResult((Task)task2ops((ParIterableLike<?, ParArray<?>, ArraySeq<?>>.StrictSplitterCheckTask<?, ?>)new ParIterableLike.CreateScanTree(this, 0, size(), z, op, (IterableSplitter)splitter())).mapResult(
/* 604 */             (Function1)new ParArray$$anonfun$scan$1(this, z, op, (ParArray<T>)targetarr))) : BoxedUnit.UNIT;
/*     */     } else {
/*     */     
/*     */     } 
/* 609 */     return (That)ParIterableLike.class.scan(this, z, op, cbf);
/*     */   }
/*     */   
/*     */   public class ParArray$$anonfun$scan$1 extends AbstractFunction1<ParIterableLike<T, ParArray<T>, ArraySeq<T>>.ScanTree<U>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object z$1;
/*     */     
/*     */     private final Function2 op$1;
/*     */     
/*     */     private final Object[] targetarr$1;
/*     */     
/*     */     public final void apply(ParIterableLike.ScanTree tree) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: getfield $outer : Lscala/collection/parallel/mutable/ParArray;
/*     */       //   4: invokevirtual tasksupport : ()Lscala/collection/parallel/TaskSupport;
/*     */       //   7: new scala/collection/parallel/mutable/ParArray$ScanToArray
/*     */       //   10: dup
/*     */       //   11: aload_0
/*     */       //   12: getfield $outer : Lscala/collection/parallel/mutable/ParArray;
/*     */       //   15: aload_1
/*     */       //   16: aload_0
/*     */       //   17: getfield z$1 : Ljava/lang/Object;
/*     */       //   20: aload_0
/*     */       //   21: getfield op$1 : Lscala/Function2;
/*     */       //   24: aload_0
/*     */       //   25: getfield targetarr$1 : [Ljava/lang/Object;
/*     */       //   28: invokespecial <init> : (Lscala/collection/parallel/mutable/ParArray;Lscala/collection/parallel/ParIterableLike$ScanTree;Ljava/lang/Object;Lscala/Function2;[Ljava/lang/Object;)V
/*     */       //   31: invokeinterface executeAndWaitResult : (Lscala/collection/parallel/Task;)Ljava/lang/Object;
/*     */       //   36: pop
/*     */       //   37: return
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #604	-> 0
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	38	0	this	Lscala/collection/parallel/mutable/ParArray$$anonfun$scan$1;
/*     */       //   0	38	1	tree	Lscala/collection/parallel/ParIterableLike$ScanTree;
/*     */     }
/*     */     
/*     */     public ParArray$$anonfun$scan$1(ParArray $outer, Object z$1, Function2 op$1, Object[] targetarr$1) {}
/*     */   }
/*     */   
/*     */   public class ScanToArray<U> implements Task<BoxedUnit, ScanToArray<U>> {
/*     */     private final ParIterableLike<T, ParArray<T>, ArraySeq<T>>.ScanTree<U> tree;
/*     */     
/*     */     private final U z;
/*     */     
/*     */     private final Function2<U, U, U> op;
/*     */     
/*     */     private final Object[] targetarr;
/*     */     
/*     */     private BoxedUnit result;
/*     */     
/*     */     private volatile Throwable throwable;
/*     */     
/*     */     public Throwable throwable() {
/* 613 */       return this.throwable;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void throwable_$eq(Throwable x$1) {
/* 613 */       this.throwable = x$1;
/*     */     }
/*     */     
/*     */     public ScanToArray<U> repr() {
/* 613 */       return (ScanToArray<U>)Task.class.repr(this);
/*     */     }
/*     */     
/*     */     public void merge(Object that) {
/* 613 */       Task.class.merge(this, that);
/*     */     }
/*     */     
/*     */     public void forwardThrowable() {
/* 613 */       Task.class.forwardThrowable(this);
/*     */     }
/*     */     
/*     */     public void tryLeaf(Option lastres) {
/* 613 */       Task.class.tryLeaf(this, lastres);
/*     */     }
/*     */     
/*     */     public void tryMerge(Object t) {
/* 613 */       Task.class.tryMerge(this, t);
/*     */     }
/*     */     
/*     */     public void mergeThrowables(Task that) {
/* 613 */       Task.class.mergeThrowables(this, that);
/*     */     }
/*     */     
/*     */     public void signalAbort() {
/* 613 */       Task.class.signalAbort(this);
/*     */     }
/*     */     
/*     */     public ScanToArray(ParArray $outer, ParIterableLike<T, ParArray<T>, ArraySeq<T>>.ScanTree<U> tree, Object z, Function2<U, U, U> op, Object[] targetarr) {
/* 613 */       Task.class.$init$(this);
/* 615 */       this.result = BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public void result() {}
/*     */     
/*     */     public void result_$eq(BoxedUnit x$1) {
/* 615 */       this.result = x$1;
/*     */     }
/*     */     
/*     */     public void leaf(Option prev) {
/* 616 */       iterate(this.tree);
/*     */     }
/*     */     
/*     */     private void iterate(ParIterableLike.ScanTree tree) {
/*     */       // Byte code:
/*     */       //   0: iconst_0
/*     */       //   1: istore #4
/*     */       //   3: aconst_null
/*     */       //   4: astore #6
/*     */       //   6: aload_1
/*     */       //   7: instanceof scala/collection/parallel/ParIterableLike$ScanNode
/*     */       //   10: ifeq -> 34
/*     */       //   13: aload_1
/*     */       //   14: checkcast scala/collection/parallel/ParIterableLike$ScanNode
/*     */       //   17: astore_2
/*     */       //   18: aload_0
/*     */       //   19: aload_2
/*     */       //   20: invokevirtual left : ()Lscala/collection/parallel/ParIterableLike$ScanTree;
/*     */       //   23: invokespecial iterate : (Lscala/collection/parallel/ParIterableLike$ScanTree;)V
/*     */       //   26: aload_2
/*     */       //   27: invokevirtual right : ()Lscala/collection/parallel/ParIterableLike$ScanTree;
/*     */       //   30: astore_1
/*     */       //   31: goto -> 0
/*     */       //   34: aload_1
/*     */       //   35: instanceof scala/collection/parallel/ParIterableLike$ScanLeaf
/*     */       //   38: ifeq -> 107
/*     */       //   41: iconst_1
/*     */       //   42: istore #4
/*     */       //   44: aload_1
/*     */       //   45: checkcast scala/collection/parallel/ParIterableLike$ScanLeaf
/*     */       //   48: dup
/*     */       //   49: astore #6
/*     */       //   51: invokevirtual prev : ()Lscala/Option;
/*     */       //   54: instanceof scala/Some
/*     */       //   57: ifeq -> 107
/*     */       //   60: aload #6
/*     */       //   62: invokevirtual prev : ()Lscala/Option;
/*     */       //   65: checkcast scala/Some
/*     */       //   68: astore_3
/*     */       //   69: aload_0
/*     */       //   70: aload_0
/*     */       //   71: invokevirtual scala$collection$parallel$mutable$ParArray$ScanToArray$$$outer : ()Lscala/collection/parallel/mutable/ParArray;
/*     */       //   74: invokevirtual scala$collection$parallel$mutable$ParArray$$array : ()[Ljava/lang/Object;
/*     */       //   77: aload_0
/*     */       //   78: getfield targetarr : [Ljava/lang/Object;
/*     */       //   81: aload #6
/*     */       //   83: invokevirtual from : ()I
/*     */       //   86: aload #6
/*     */       //   88: invokevirtual len : ()I
/*     */       //   91: aload_3
/*     */       //   92: invokevirtual x : ()Ljava/lang/Object;
/*     */       //   95: checkcast scala/collection/parallel/ParIterableLike$ScanLeaf
/*     */       //   98: invokevirtual acc : ()Ljava/lang/Object;
/*     */       //   101: invokespecial scanLeaf : ([Ljava/lang/Object;[Ljava/lang/Object;IILjava/lang/Object;)V
/*     */       //   104: goto -> 172
/*     */       //   107: iload #4
/*     */       //   109: ifeq -> 173
/*     */       //   112: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */       //   115: aload #6
/*     */       //   117: invokevirtual prev : ()Lscala/Option;
/*     */       //   120: astore #5
/*     */       //   122: dup
/*     */       //   123: ifnonnull -> 135
/*     */       //   126: pop
/*     */       //   127: aload #5
/*     */       //   129: ifnull -> 143
/*     */       //   132: goto -> 173
/*     */       //   135: aload #5
/*     */       //   137: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   140: ifeq -> 173
/*     */       //   143: aload_0
/*     */       //   144: aload_0
/*     */       //   145: invokevirtual scala$collection$parallel$mutable$ParArray$ScanToArray$$$outer : ()Lscala/collection/parallel/mutable/ParArray;
/*     */       //   148: invokevirtual scala$collection$parallel$mutable$ParArray$$array : ()[Ljava/lang/Object;
/*     */       //   151: aload_0
/*     */       //   152: getfield targetarr : [Ljava/lang/Object;
/*     */       //   155: aload #6
/*     */       //   157: invokevirtual from : ()I
/*     */       //   160: aload #6
/*     */       //   162: invokevirtual len : ()I
/*     */       //   165: aload_0
/*     */       //   166: getfield z : Ljava/lang/Object;
/*     */       //   169: invokespecial scanLeaf : ([Ljava/lang/Object;[Ljava/lang/Object;IILjava/lang/Object;)V
/*     */       //   172: return
/*     */       //   173: new scala/MatchError
/*     */       //   176: dup
/*     */       //   177: aload_1
/*     */       //   178: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   181: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #621	-> 0
/*     */       //   #618	-> 6
/*     */       //   #617	-> 6
/*     */       //   #619	-> 18
/*     */       //   #617	-> 19
/*     */       //   #619	-> 20
/*     */       //   #617	-> 26
/*     */       //   #620	-> 27
/*     */       //   #621	-> 34
/*     */       //   #617	-> 60
/*     */       //   #621	-> 62
/*     */       //   #622	-> 69
/*     */       //   #617	-> 81
/*     */       //   #622	-> 83
/*     */       //   #617	-> 86
/*     */       //   #622	-> 88
/*     */       //   #617	-> 91
/*     */       //   #622	-> 92
/*     */       //   #617	-> 107
/*     */       //   #623	-> 112
/*     */       //   #617	-> 115
/*     */       //   #623	-> 117
/*     */       //   #624	-> 143
/*     */       //   #617	-> 155
/*     */       //   #624	-> 157
/*     */       //   #617	-> 160
/*     */       //   #624	-> 162
/*     */       //   #617	-> 172
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	182	0	this	Lscala/collection/parallel/mutable/ParArray$ScanToArray;
/*     */       //   0	182	1	tree	Lscala/collection/parallel/ParIterableLike$ScanTree;
/*     */     }
/*     */     
/*     */     private void scanLeaf(Object[] srcarr, Object[] targetarr, int from, int len, Object startval) {
/* 627 */       int i = from;
/* 628 */       int until = from + len;
/* 629 */       Object curr = startval;
/* 630 */       Function2<U, U, U> operation = this.op;
/* 631 */       while (i < until) {
/* 632 */         curr = operation.apply(curr, srcarr[i]);
/* 633 */         i++;
/* 634 */         targetarr[i] = curr;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Seq<Task<BoxedUnit, ScanToArray<U>>> split() {
/* 637 */       ParIterableLike<T, ParArray<T>, ArraySeq<T>>.ScanTree<U> scanTree = this.tree;
/* 638 */       if (scanTree instanceof ParIterableLike.ScanNode) {
/* 638 */         ParIterableLike.ScanNode scanNode = (ParIterableLike.ScanNode)scanTree;
/* 638 */         (new ScanToArray[2])[0] = 
/* 639 */           new ScanToArray(scala$collection$parallel$mutable$ParArray$ScanToArray$$$outer(), scanNode.left(), this.z, this.op, this.targetarr);
/*     */       } 
/* 642 */       throw scala.sys.package$.MODULE$.error("Can only split scan tree internal nodes.");
/*     */     }
/*     */     
/*     */     public boolean shouldSplitFurther() {
/*     */       boolean bool;
/* 644 */       ParIterableLike<T, ParArray<T>, ArraySeq<T>>.ScanTree<U> scanTree = this.tree;
/* 645 */       if (scanTree instanceof ParIterableLike.ScanNode) {
/* 645 */         bool = true;
/*     */       } else {
/* 646 */         bool = false;
/*     */       } 
/*     */       return bool;
/*     */     }
/*     */   }
/*     */   
/*     */   public class Map<S> implements Task<BoxedUnit, Map<S>> {
/*     */     private final Function1<T, S> f;
/*     */     
/*     */     private final Object[] targetarr;
/*     */     
/*     */     private final int offset;
/*     */     
/*     */     private final int howmany;
/*     */     
/*     */     private BoxedUnit result;
/*     */     
/*     */     private volatile Throwable throwable;
/*     */     
/*     */     public Throwable throwable() {
/* 650 */       return this.throwable;
/*     */     }
/*     */     
/*     */     @TraitSetter
/*     */     public void throwable_$eq(Throwable x$1) {
/* 650 */       this.throwable = x$1;
/*     */     }
/*     */     
/*     */     public Map<S> repr() {
/* 650 */       return (Map<S>)Task.class.repr(this);
/*     */     }
/*     */     
/*     */     public void merge(Object that) {
/* 650 */       Task.class.merge(this, that);
/*     */     }
/*     */     
/*     */     public void forwardThrowable() {
/* 650 */       Task.class.forwardThrowable(this);
/*     */     }
/*     */     
/*     */     public void tryLeaf(Option lastres) {
/* 650 */       Task.class.tryLeaf(this, lastres);
/*     */     }
/*     */     
/*     */     public void tryMerge(Object t) {
/* 650 */       Task.class.tryMerge(this, t);
/*     */     }
/*     */     
/*     */     public void mergeThrowables(Task that) {
/* 650 */       Task.class.mergeThrowables(this, that);
/*     */     }
/*     */     
/*     */     public void signalAbort() {
/* 650 */       Task.class.signalAbort(this);
/*     */     }
/*     */     
/*     */     public Map(ParArray $outer, Function1<T, S> f, Object[] targetarr, int offset, int howmany) {
/* 650 */       Task.class.$init$(this);
/* 651 */       this.result = BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public void result() {}
/*     */     
/*     */     public void result_$eq(BoxedUnit x$1) {
/* 651 */       this.result = x$1;
/*     */     }
/*     */     
/*     */     public void leaf(Option prev) {
/* 653 */       Object[] tarr = this.targetarr;
/* 654 */       Object[] sarr = scala$collection$parallel$mutable$ParArray$Map$$$outer().scala$collection$parallel$mutable$ParArray$$array();
/* 655 */       int i = this.offset;
/* 656 */       int until = this.offset + this.howmany;
/* 657 */       while (i < until) {
/* 658 */         tarr[i] = this.f.apply(sarr[i]);
/* 659 */         i++;
/*     */       } 
/*     */     }
/*     */     
/*     */     public List<Map<S>> split() {
/* 663 */       int fp = this.howmany / 2;
/* 664 */       (new Map[2])[0] = new Map(scala$collection$parallel$mutable$ParArray$Map$$$outer(), this.f, this.targetarr, this.offset, fp);
/* 664 */       (new Map[2])[1] = new Map(scala$collection$parallel$mutable$ParArray$Map$$$outer(), this.f, this.targetarr, this.offset + fp, this.howmany - fp);
/* 664 */       return scala.collection.immutable.List$.MODULE$.apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new Map[2]));
/*     */     }
/*     */     
/*     */     public boolean shouldSplitFurther() {
/* 666 */       return (this.howmany > scala.collection.parallel.package$.MODULE$.thresholdFromSize(scala$collection$parallel$mutable$ParArray$Map$$$outer().length(), scala$collection$parallel$mutable$ParArray$Map$$$outer().tasksupport().parallelismLevel()));
/*     */     }
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) {
/* 672 */     out.defaultWriteObject();
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) {
/* 676 */     in.defaultReadObject();
/* 679 */     scala$collection$parallel$mutable$ParArray$$array_$eq(arrayseq().array());
/*     */   }
/*     */   
/*     */   public static <T> ParArray<T> fromTraversables(Seq<GenTraversableOnce<T>> paramSeq) {
/*     */     return ParArray$.MODULE$.fromTraversables(paramSeq);
/*     */   }
/*     */   
/*     */   public static <T> ParArray<T> createFromCopy(T[] paramArrayOfT, ClassTag<T> paramClassTag) {
/*     */     return ParArray$.MODULE$.createFromCopy(paramArrayOfT, paramClassTag);
/*     */   }
/*     */   
/*     */   public static <T> ParArray<T> handoff(Object paramObject, int paramInt) {
/*     */     return ParArray$.MODULE$.handoff(paramObject, paramInt);
/*     */   }
/*     */   
/*     */   public static <T> ParArray<T> handoff(Object paramObject) {
/*     */     return ParArray$.MODULE$.handoff(paramObject);
/*     */   }
/*     */   
/*     */   public static <T> CanCombineFrom<ParArray<?>, T, ParArray<T>> canBuildFrom() {
/*     */     return ParArray$.MODULE$.canBuildFrom();
/*     */   }
/*     */   
/*     */   public static <A> ParArray<A> iterate(A paramA, int paramInt, Function1<A, A> paramFunction1) {
/*     */     return (ParArray<A>)ParArray$.MODULE$.iterate(paramA, paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <T> ParArray<T> range(T paramT1, T paramT2, T paramT3, Integral<T> paramIntegral) {
/*     */     return (ParArray<T>)ParArray$.MODULE$.range(paramT1, paramT2, paramT3, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <T> ParArray<T> range(T paramT1, T paramT2, Integral<T> paramIntegral) {
/*     */     return (ParArray<T>)ParArray$.MODULE$.range(paramT1, paramT2, paramIntegral);
/*     */   }
/*     */   
/*     */   public static <A> ParArray<ParArray<ParArray<ParArray<ParArray<A>>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function5<Object, Object, Object, Object, Object, A> paramFunction5) {
/*     */     return (ParArray<ParArray<ParArray<ParArray<ParArray<A>>>>>)ParArray$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction5);
/*     */   }
/*     */   
/*     */   public static <A> ParArray<ParArray<ParArray<ParArray<A>>>> tabulate(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function4<Object, Object, Object, Object, A> paramFunction4) {
/*     */     return (ParArray<ParArray<ParArray<ParArray<A>>>>)ParArray$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction4);
/*     */   }
/*     */   
/*     */   public static <A> ParArray<ParArray<ParArray<A>>> tabulate(int paramInt1, int paramInt2, int paramInt3, Function3<Object, Object, Object, A> paramFunction3) {
/*     */     return (ParArray<ParArray<ParArray<A>>>)ParArray$.MODULE$.tabulate(paramInt1, paramInt2, paramInt3, paramFunction3);
/*     */   }
/*     */   
/*     */   public static <A> ParArray<ParArray<A>> tabulate(int paramInt1, int paramInt2, Function2<Object, Object, A> paramFunction2) {
/*     */     return (ParArray<ParArray<A>>)ParArray$.MODULE$.tabulate(paramInt1, paramInt2, paramFunction2);
/*     */   }
/*     */   
/*     */   public static <A> ParArray<A> tabulate(int paramInt, Function1<Object, A> paramFunction1) {
/*     */     return (ParArray<A>)ParArray$.MODULE$.tabulate(paramInt, paramFunction1);
/*     */   }
/*     */   
/*     */   public static <A> ParArray<ParArray<ParArray<ParArray<ParArray<A>>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Function0<A> paramFunction0) {
/*     */     return (ParArray<ParArray<ParArray<ParArray<ParArray<A>>>>>)ParArray$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ParArray<ParArray<ParArray<ParArray<A>>>> fill(int paramInt1, int paramInt2, int paramInt3, int paramInt4, Function0<A> paramFunction0) {
/*     */     return (ParArray<ParArray<ParArray<ParArray<A>>>>)ParArray$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramInt4, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ParArray<ParArray<ParArray<A>>> fill(int paramInt1, int paramInt2, int paramInt3, Function0<A> paramFunction0) {
/*     */     return (ParArray<ParArray<ParArray<A>>>)ParArray$.MODULE$.fill(paramInt1, paramInt2, paramInt3, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ParArray<ParArray<A>> fill(int paramInt1, int paramInt2, Function0<A> paramFunction0) {
/*     */     return (ParArray<ParArray<A>>)ParArray$.MODULE$.fill(paramInt1, paramInt2, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ParArray<A> fill(int paramInt, Function0<A> paramFunction0) {
/*     */     return (ParArray<A>)ParArray$.MODULE$.fill(paramInt, paramFunction0);
/*     */   }
/*     */   
/*     */   public static <A> ParArray<A> concat(Seq<Traversable<A>> paramSeq) {
/*     */     return (ParArray<A>)ParArray$.MODULE$.concat(paramSeq);
/*     */   }
/*     */   
/*     */   public static GenTraversableFactory<ParArray>.GenericCanBuildFrom<scala.runtime.Nothing$> ReusableCBF() {
/*     */     return ParArray$.MODULE$.ReusableCBF();
/*     */   }
/*     */   
/*     */   public static <A> ParArray<A> empty() {
/*     */     return (ParArray<A>)ParArray$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public static class ParArray$$anonfun$fromTraversables$1 extends AbstractFunction1<GenTraversableOnce<T>, ResizableParArrayCombiner<T>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ResizableParArrayCombiner cb$1;
/*     */     
/*     */     public ParArray$$anonfun$fromTraversables$1(ResizableParArrayCombiner cb$1) {}
/*     */     
/*     */     public final ResizableParArrayCombiner<T> apply(GenTraversableOnce xs) {
/* 716 */       return (ResizableParArrayCombiner<T>)this.cb$1.$plus$plus$eq(xs.seq());
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\ParArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */