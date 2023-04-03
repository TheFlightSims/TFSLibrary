/*     */ package scala.math;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Some;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\031mq!B\001\003\021\0039\021a\002(v[\026\024\030n\031\006\003\007\021\tA!\\1uQ*\tQ!A\003tG\006d\027m\001\001\021\005!IQ\"\001\002\007\013)\021\001\022A\006\003\0179+X.\032:jGN\031\021\002\004\t\021\0055qQ\"\001\003\n\005=!!AB!osJ+g\r\005\002\016#%\021!\003\002\002\r'\026\024\030.\0317ju\006\024G.\032\005\006)%!\t!F\001\007y%t\027\016\036 \025\003\0351qaF\005\021\002\007\005\001D\001\bFqR\024\030-S7qY&\034\027\016^:\024\005Ya\001\"\002\016\027\t\003Y\022A\002\023j]&$H\005F\001\035!\tiQ$\003\002\037\t\t!QK\\5u\021\025\001c\003b\001\"\003=IgNZ5y\035VlWM]5d\037B\034Xc\001\022\002XQ\0311%!\030\025\007\021\nI\006E\002&\003\013\001B\001\003\024\002V\0319!B\001I\001\004\0039SC\001\0257'\r1\023&\r\t\003U=j\021a\013\006\003Y5\nA\001\\1oO*\ta&\001\003kCZ\f\027B\001\031,\005\031y%M[3diB\031\001B\r\033\n\005M\022!\001C(sI\026\024\030N\\4\021\005U2D\002\001\003\006o\031\022\r\001\017\002\002)F\021\021\b\020\t\003\033iJ!a\017\003\003\0179{G\017[5oOB\021Q\"P\005\003}\021\0211!\0218z\021\025Qb\005\"\001\034\021\025\teE\"\001C\003\021\001H.^:\025\007Q\032U\tC\003E\001\002\007A'A\001y\021\0251\005\t1\0015\003\005I\b\"\002%'\r\003I\025!B7j]V\034Hc\001\033K\027\")Ai\022a\001i!)ai\022a\001i!)QJ\nD\001\035\006)A/[7fgR\031Ag\024)\t\013\021c\005\031\001\033\t\013\031c\005\031\001\033\t\013I3c\021A*\002\r9,w-\031;f)\t!D\013C\003E#\002\007A\007C\003WM\031\005q+A\004ge>l\027J\034;\025\005QB\006\"\002#V\001\004I\006CA\007[\023\tYFAA\002J]RDQ!\030\024\007\002y\013Q\001^8J]R$\"!W0\t\013\021c\006\031\001\033\t\013\0054c\021\0012\002\rQ|Gj\0348h)\t\031g\r\005\002\016I&\021Q\r\002\002\005\031>tw\rC\003EA\002\007A\007C\003iM\031\005\021.A\004u_\032cw.\031;\025\005)l\007CA\007l\023\taGAA\003GY>\fG\017C\003EO\002\007A\007C\003pM\031\005\001/\001\005u_\022{WO\0317f)\t\tH\017\005\002\016e&\0211\017\002\002\007\t>,(\r\\3\t\013\021s\007\031\001\033\t\013Y4C\021A<\002\ti,'o\\\013\002i!)\021P\nC\001o\006\031qN\\3\t\013m4C\021\001?\002\007\005\0247\017\006\0025{\")AI\037a\001i!1qP\nC\001\003\003\taa]5h]VlGcA-\002\004!)AI a\001i\0311\021q\001\024\001\003\023\0211a\0249t'\r\t)\001\004\005\013\003\033\t)A!A!\002\023!\024a\0017ig\"9A#!\002\005\002\005EA\003BA\n\003/\001B!!\006\002\0065\ta\005C\004\002\016\005=\001\031\001\033\t\021\005m\021Q\001C\001\003;\tQ\001\n9mkN$2\001NA\020\021\035\t\t#!\007A\002Q\n1A\0355t\021!\t)#!\002\005\002\005\035\022A\002\023nS:,8\017F\0025\003SAq!!\t\002$\001\007A\007\003\005\002.\005\025A\021AA\030\003\031!C/[7fgR\031A'!\r\t\017\005\005\0221\006a\001i!A\021QGA\003\t\003\t9$\001\007v]\006\024\030p\030\023nS:,8\017F\0015\021\035Y\030Q\001C\001\003oAqa`A\003\t\003\ti\004F\001Z\021\035i\026Q\001C\001\003{Aq!YA\003\t\003\t\031\005F\001d\021\035A\027Q\001C\001\003\017\"\022A\033\005\b_\006\025A\021AA&)\005\t\bbBA(M\021\r\021\021K\001\r[.tU/\\3sS\016|\005o\035\013\005\003'\t\031\006C\004\002\016\0055\003\031\001\033\021\007U\n9\006B\0038?\t\007\001\b\003\004\002\\}\001\035!J\001\004]Vl\007B\002# \001\004\t)fB\004\002b%A\t!a\031\002\023%k\007\017\\5dSR\034\b\003BA3\003Oj\021!\003\004\b\003SJ\001\022AA6\005%IU\016\0357jG&$8oE\003\002h1\ti\007E\002\002fYAq\001FA4\t\003\t\t\b\006\002\002d\031I\021QO\005\021\002\007\005\021q\017\002\021\005&<\027J\034;Jg&sG/Z4sC2\034R!a\035*\003s\002R\001CA>\003J1!! \003\005!Ie\016^3he\006d\007c\001\005\002\002&\031\0211\021\002\003\r\tKw-\0238u\021\031Q\0221\017C\0017!9\021)a\035\005\002\005%ECBA@\003\027\013i\tC\004E\003\017\003\r!a \t\017\031\0139\t1\001\002\000!9\001*a\035\005\002\005EECBA@\003'\013)\nC\004E\003\037\003\r!a \t\017\031\013y\t1\001\002\000!9Q*a\035\005\002\005eECBA@\0037\013i\nC\004E\003/\003\r!a \t\017\031\0139\n1\001\002\000!A\021\021UA:\t\003\t\031+\001\003rk>$HCBA@\003K\0139\013C\004E\003?\003\r!a \t\017\031\013y\n1\001\002\000!A\0211VA:\t\003\ti+A\002sK6$b!a \0020\006E\006b\002#\002*\002\007\021q\020\005\b\r\006%\006\031AA@\021\035\021\0261\017C\001\003k#B!a \0028\"9A)a-A\002\005}\004b\002,\002t\021\005\0211\030\013\005\003\ni\f\003\004E\003s\003\r!\027\005\b;\006MD\021AAa)\rI\0261\031\005\b\t\006}\006\031AA@\021\035\t\0271\017C\001\003\017$2aYAe\021\035!\025Q\031a\001\003Bq\001[A:\t\003\ti\rF\002k\003\037Dq\001RAf\001\004\ty\bC\004p\003g\"\t!a5\025\007E\f)\016C\004E\003#\004\r!a \b\017\005e\027\002c\001\002\\\006\001\")[4J]RL5/\0238uK\036\024\030\r\034\t\005\003K\niNB\004\002v%A\t!a8\024\017\005u\027&!9\002dB!\021QMA:!\021\t)/a;\017\007!\t9/C\002\002j\n\t\001b\024:eKJLgnZ\005\005\003[\fyO\001\bCS\036Le\016^(sI\026\024\030N\\4\013\007\005%(\001C\004\025\003;$\t!a=\025\005\005m\007BCA|\003;\f\t\021\"\003\002z\006Y!/Z1e%\026\034x\016\034<f)\005Ic!CA\023A\005\031\021AA\000\0055Ie\016^%t\023:$Xm\032:bYN)\0211`\025\003\002A!\001\"a\037Z\021\031Q\0221 C\0017!9\021)a?\005\002\t\035A#B-\003\n\t-\001B\002#\003\006\001\007\021\f\003\004G\005\013\001\r!\027\005\b\021\006mH\021\001B\b)\025I&\021\003B\n\021\031!%Q\002a\0013\"1aI!\004A\002eCq!TA~\t\003\0219\002F\003Z\0053\021Y\002\003\004E\005+\001\r!\027\005\007\r\nU\001\031A-\t\021\005\005\0261 C\001\005?!R!\027B\021\005GAa\001\022B\017\001\004I\006B\002$\003\036\001\007\021\f\003\005\002,\006mH\021\001B\024)\025I&\021\006B\026\021\031!%Q\005a\0013\"1aI!\nA\002eCqAUA~\t\003\021y\003F\002Z\005cAa\001\022B\027\001\004I\006b\002,\002|\022\005!Q\007\013\0043\n]\002B\002#\0034\001\007\021\fC\004^\003w$\tAa\017\025\007e\023i\004\003\004E\005s\001\r!\027\005\bC\006mH\021\001B!)\r\031'1\t\005\007\t\n}\002\031A-\t\017!\fY\020\"\001\003HQ\031!N!\023\t\r\021\023)\0051\001Z\021\035y\0271 C\001\005\033\"2!\035B(\021\031!%1\na\0013\0369!1K\005\t\004\tU\023!D%oi&\033\030J\034;fOJ\fG\016\005\003\002f\t]caBA\023!\005!\021L\n\b\005/J#1\fB/!\021\t)'a?\021\t\005\025(qL\005\005\005C\nyOA\006J]R|%\017Z3sS:<\007b\002\013\003X\021\005!Q\r\013\003\005+B!\"a>\003X\005\005I\021BA}\r%\021Y'\003I\001\004\003\021iGA\bTQ>\024H/S:J]R,wM]1m'\025\021I'\013B8!\025A\0211\020B9!\ri!1O\005\004\005k\"!!B*i_J$\bB\002\016\003j\021\0051\004C\004B\005S\"\tAa\037\025\r\tE$Q\020B@\021\035!%\021\020a\001\005cBqA\022B=\001\004\021\t\bC\004I\005S\"\tAa!\025\r\tE$Q\021BD\021\035!%\021\021a\001\005cBqA\022BA\001\004\021\t\bC\004N\005S\"\tAa#\025\r\tE$Q\022BH\021\035!%\021\022a\001\005cBqA\022BE\001\004\021\t\b\003\005\002\"\n%D\021\001BJ)\031\021\tH!&\003\030\"9AI!%A\002\tE\004b\002$\003\022\002\007!\021\017\005\t\003W\023I\007\"\001\003\034R1!\021\017BO\005?Cq\001\022BM\001\004\021\t\bC\004G\0053\003\rA!\035\t\017I\023I\007\"\001\003$R!!\021\017BS\021\035!%\021\025a\001\005cBqA\026B5\t\003\021I\013\006\003\003r\t-\006B\002#\003(\002\007\021\fC\004^\005S\"\tAa,\025\007e\023\t\fC\004E\005[\003\rA!\035\t\017\005\024I\007\"\001\0036R\0311Ma.\t\017\021\023\031\f1\001\003r!9\001N!\033\005\002\tmFc\0016\003>\"9AI!/A\002\tE\004bB8\003j\021\005!\021\031\013\004c\n\r\007b\002#\003@\002\007!\021O\004\b\005\017L\0012\001Be\003=\031\006n\034:u\023NLe\016^3he\006d\007\003BA3\005\0274qAa\033\n\021\003\021imE\004\003L&\022yM!5\021\t\005\025$\021\016\t\005\003K\024\031.\003\003\003V\006=(!D*i_J$xJ\0353fe&tw\rC\004\025\005\027$\tA!7\025\005\t%\007BCA|\005\027\f\t\021\"\003\002z\032I!q\\\005\021\002\007\005!\021\035\002\017\005f$X-S:J]R,wM]1m'\025\021i.\013Br!\025A\0211\020Bs!\ri!q]\005\004\005S$!\001\002\"zi\026DaA\007Bo\t\003Y\002bB!\003^\022\005!q\036\013\007\005K\024\tPa=\t\017\021\023i\0171\001\003f\"9aI!<A\002\t\025\bb\002%\003^\022\005!q\037\013\007\005K\024IPa?\t\017\021\023)\0201\001\003f\"9aI!>A\002\t\025\bbB'\003^\022\005!q \013\007\005K\034\taa\001\t\017\021\023i\0201\001\003f\"9aI!@A\002\t\025\b\002CAQ\005;$\taa\002\025\r\t\0258\021BB\006\021\035!5Q\001a\001\005KDqARB\003\001\004\021)\017\003\005\002,\nuG\021AB\b)\031\021)o!\005\004\024!9Ai!\004A\002\t\025\bb\002$\004\016\001\007!Q\035\005\b%\nuG\021AB\f)\021\021)o!\007\t\017\021\033)\0021\001\003f\"9aK!8\005\002\ruA\003\002Bs\007?Aa\001RB\016\001\004I\006bB/\003^\022\00511\005\013\0043\016\025\002b\002#\004\"\001\007!Q\035\005\bC\nuG\021AB\025)\r\03171\006\005\b\t\016\035\002\031\001Bs\021\035A'Q\034C\001\007_!2A[B\031\021\035!5Q\006a\001\005KDqa\034Bo\t\003\031)\004F\002r\007oAq\001RB\032\001\004\021)oB\004\004<%A\031a!\020\002\035\tKH/Z%t\023:$Xm\032:bYB!\021QMB \r\035\021y.\003E\001\007\003\032raa\020*\007\007\032)\005\005\003\002f\tu\007\003BAs\007\017JAa!\023\002p\na!)\037;f\037J$WM]5oO\"9Aca\020\005\002\r5CCAB\037\021)\t9pa\020\002\002\023%\021\021 \004\n\007'J\001\023aA\001\007+\022ab\0215be&\033\030J\034;fOJ\fGnE\003\004R%\0329\006E\003\t\003w\032I\006E\002\016\0077J1a!\030\005\005\021\031\005.\031:\t\ri\031\t\006\"\001\034\021\035\t5\021\013C\001\007G\"ba!\027\004f\r\035\004b\002#\004b\001\0071\021\f\005\b\r\016\005\004\031AB-\021\035A5\021\013C\001\007W\"ba!\027\004n\r=\004b\002#\004j\001\0071\021\f\005\b\r\016%\004\031AB-\021\035i5\021\013C\001\007g\"ba!\027\004v\r]\004b\002#\004r\001\0071\021\f\005\b\r\016E\004\031AB-\021!\t\tk!\025\005\002\rmDCBB-\007{\032y\bC\004E\007s\002\ra!\027\t\017\031\033I\b1\001\004Z!A\0211VB)\t\003\031\031\t\006\004\004Z\r\0255q\021\005\b\t\016\005\005\031AB-\021\03515\021\021a\001\0073BqAUB)\t\003\031Y\t\006\003\004Z\r5\005b\002#\004\n\002\0071\021\f\005\b-\016EC\021ABI)\021\031Ifa%\t\r\021\033y\t1\001Z\021\035i6\021\013C\001\007/#2!WBM\021\035!5Q\023a\001\0073Bq!YB)\t\003\031i\nF\002d\007?Cq\001RBN\001\004\031I\006C\004i\007#\"\taa)\025\007)\034)\013C\004E\007C\003\ra!\027\t\017=\034\t\006\"\001\004*R\031\021oa+\t\017\021\0339\0131\001\004Z\03591qV\005\t\004\rE\026AD\"iCJL5/\0238uK\036\024\030\r\034\t\005\003K\032\031LB\004\004T%A\ta!.\024\017\rM\026fa.\004:B!\021QMB)!\021\t)oa/\n\t\ru\026q\036\002\r\007\"\f'o\024:eKJLgn\032\005\b)\rMF\021ABa)\t\031\t\f\003\006\002x\016M\026\021!C\005\003s4\021ba2\n!\003\r\ta!3\003\0351{gnZ%t\023:$Xm\032:bYN)1QY\025\004LB!\001\"a\037d\021\031Q2Q\031C\0017!9\021i!2\005\002\rEG#B2\004T\016U\007B\002#\004P\002\0071\r\003\004G\007\037\004\ra\031\005\b\021\016\025G\021ABm)\025\03171\\Bo\021\031!5q\033a\001G\"1aia6A\002\rDq!TBc\t\003\031\t\017F\003d\007G\034)\017\003\004E\007?\004\ra\031\005\007\r\016}\007\031A2\t\021\005\0056Q\031C\001\007S$RaYBv\007[Da\001RBt\001\004\031\007B\002$\004h\002\0071\r\003\005\002,\016\025G\021ABy)\025\03171_B{\021\031!5q\036a\001G\"1aia<A\002\rDqAUBc\t\003\031I\020F\002d\007wDa\001RB|\001\004\031\007b\002,\004F\022\0051q \013\004G\022\005\001B\002#\004~\002\007\021\fC\004^\007\013$\t\001\"\002\025\007e#9\001\003\004E\t\007\001\ra\031\005\bC\016\025G\021\001C\006)\r\031GQ\002\005\007\t\022%\001\031A2\t\017!\034)\r\"\001\005\022Q\031!\016b\005\t\r\021#y\0011\001d\021\035y7Q\031C\001\t/!2!\035C\r\021\031!EQ\003a\001G\0369AQD\005\t\004\021}\021A\004'p]\036L5/\0238uK\036\024\030\r\034\t\005\003K\"\tCB\004\004H&A\t\001b\t\024\017\021\005\022\006\"\n\005(A!\021QMBc!\021\t)\017\"\013\n\t\021-\022q\036\002\r\031>twm\024:eKJLgn\032\005\b)\021\005B\021\001C\030)\t!y\002\003\006\002x\022\005\022\021!C\005\003s4\021\002\"\016\n!\003\r\t\001b\016\003#\031cw.\031;Jg\016{gN\0327jGR,GmE\003\0054%\"I\004E\002\tM)DaA\007C\032\t\003Y\002bB!\0054\021\005Aq\b\013\006U\022\005C1\t\005\007\t\022u\002\031\0016\t\r\031#i\0041\001k\021\035AE1\007C\001\t\017\"RA\033C%\t\027Ba\001\022C#\001\004Q\007B\002$\005F\001\007!\016C\004N\tg!\t\001b\024\025\013)$\t\006b\025\t\r\021#i\0051\001k\021\0311EQ\na\001U\"9!\013b\r\005\002\021]Cc\0016\005Z!1A\t\"\026A\002)DqA\026C\032\t\003!i\006F\002k\t?Ba\001\022C.\001\004I\006bB/\0054\021\005A1\r\013\0043\022\025\004B\002#\005b\001\007!\016C\004b\tg!\t\001\"\033\025\007\r$Y\007\003\004E\tO\002\rA\033\005\bQ\022MB\021\001C8)\rQG\021\017\005\007\t\0225\004\031\0016\t\017=$\031\004\"\001\005vQ\031\021\017b\036\t\r\021#\031\b1\001k\r%!Y(\003I\001\004\003!iHA\tGY>\fG/S:Ge\006\034G/[8oC2\034r\001\"\037*\t\"\t\t\005\003\002f\021M\002\003\002\005\005\004*L1\001\"\"\003\005)1%/Y2uS>t\027\r\034\005\0075\021eD\021A\016\t\021\021-E\021\020C\001\t\033\0131\001Z5w)\025QGq\022CI\021\031!E\021\022a\001U\"1a\t\"#A\002)4\021\002\"&\n!\003\r\t\001b&\003#\031cw.\031;Bg&3\027J\034;fOJ\fGnE\004\005\024&\"y\b\"'\021\t!\tYH\033\005\0075\021ME\021A\016\t\021\005\005F1\023C\001\t?#RA\033CQ\tGCa\001\022CO\001\004Q\007B\002$\005\036\002\007!\016\003\005\002,\022ME\021\001CT)\025QG\021\026CV\021\031!EQ\025a\001U\"1a\t\"*A\002)<q\001b,\n\021\007!\t,A\tGY>\fG/S:Ge\006\034G/[8oC2\004B!!\032\0054\0329A1P\005\t\002\021U6c\002CZS\021]F\021\030\t\005\003K\"I\b\005\003\002f\022m\026\002\002C_\003_\024QB\0227pCR|%\017Z3sS:<\007b\002\013\0054\022\005A\021\031\013\003\tcC!\"a>\0054\006\005I\021BA}\017\035!9-\003E\001\t\023\f\021C\0227pCR\f5/\0234J]R,wM]1m!\021\t)\007b3\007\017\021U\025\002#\001\005NN9A1Z\025\005P\022e\006\003BA3\t'Cq\001\006Cf\t\003!\031\016\006\002\005J\"Q\021q\037Cf\003\003%I!!?\007\023\021e\027\002%A\002\002\021m'A\005#pk\ndW-S:D_:4G.[2uK\022\034R\001b6*\t;\0042\001\003\024r\021\031QBq\033C\0017!9\021\tb6\005\002\021\rH#B9\005f\022\035\bB\002#\005b\002\007\021\017\003\004G\tC\004\r!\035\005\b\021\022]G\021\001Cv)\025\tHQ\036Cx\021\031!E\021\036a\001c\"1a\t\";A\002EDq!\024Cl\t\003!\031\020F\003r\tk$9\020\003\004E\tc\004\r!\035\005\007\r\022E\b\031A9\t\017I#9\016\"\001\005|R\031\021\017\"@\t\r\021#I\0201\001r\021\0351Fq\033C\001\013\003!2!]C\002\021\031!Eq a\0013\"9Q\fb6\005\002\025\035AcA-\006\n!1A)\"\002A\002EDq!\031Cl\t\003)i\001F\002d\013\037Aa\001RC\006\001\004\t\bb\0025\005X\022\005Q1\003\013\004U\026U\001B\002#\006\022\001\007\021\017C\004p\t/$\t!\"\007\025\007E,Y\002\003\004E\013/\001\r!\035\004\n\013?I\001\023aA\001\013C\021!\003R8vE2,\027j\035$sC\016$\030n\0348bYN9QQD\025\006$\025\025\002\003BA3\t/\004B\001\003CBc\"1!$\"\b\005\002mA\001\002b#\006\036\021\005Q1\006\013\006c\0265Rq\006\005\007\t\026%\002\031A9\t\r\031+I\0031\001r\r%)\031$\003I\001\004\003))D\001\nE_V\024G.Z!t\023\032Le\016^3he\006d7cBC\031S\025\rRq\007\t\005\021\005m\024\017\003\004\033\013c!\ta\007\005\t\003C+\t\004\"\001\006>Q)\021/b\020\006B!1A)b\017A\002EDaARC\036\001\004\t\b\002CAV\013c!\t!\"\022\025\013E,9%\"\023\t\r\021+\031\0051\001r\021\0311U1\ta\001c\032IQQJ\005\021\002\007\005Qq\n\002\027\005&<G)Z2j[\006d\027j]\"p]\032d\027n\031;fIN)Q1J\025\006RA!\001BJC*!\rAQQK\005\004\013/\022!A\003\"jO\022+7-[7bY\"1!$b\023\005\002mAq!QC&\t\003)i\006\006\004\006T\025}S\021\r\005\b\t\026m\003\031AC*\021\0351U1\fa\001\013'Bq\001SC&\t\003))\007\006\004\006T\025\035T\021\016\005\b\t\026\r\004\031AC*\021\0351U1\ra\001\013'Bq!TC&\t\003)i\007\006\004\006T\025=T\021\017\005\b\t\026-\004\031AC*\021\0351U1\016a\001\013'BqAUC&\t\003))\b\006\003\006T\025]\004b\002#\006t\001\007Q1\013\005\b-\026-C\021AC>)\021)\031&\" \t\r\021+I\b1\001Z\021\035iV1\nC\001\013\003#2!WCB\021\035!Uq\020a\001\013'Bq!YC&\t\003)9\tF\002d\013\023Cq\001RCC\001\004)\031\006C\004i\013\027\"\t!\"$\025\007),y\tC\004E\013\027\003\r!b\025\t\017=,Y\005\"\001\006\024R\031\021/\"&\t\017\021+\t\n1\001\006T\031IQ\021T\005\021\002\007\005Q1\024\002\027\005&<G)Z2j[\006d\027j\035$sC\016$\030n\0348bYN9QqS\025\006\036\026}\005\003BA3\013\027\002R\001\003CB\013'BaAGCL\t\003Y\002\002\003CF\013/#\t!\"*\025\r\025MSqUCU\021\035!U1\025a\001\013'BqARCR\001\004)\031FB\005\006.&\001\n1!\001\0060\n1\")[4EK\016LW.\0317Bg&3\027J\034;fOJ\fGnE\004\006,&*i*\"-\021\013!\tY(b\025\t\ri)Y\013\"\001\034\021!\t\t+b+\005\002\025]FCBC*\013s+Y\fC\004E\013k\003\r!b\025\t\017\031+)\f1\001\006T!A\0211VCV\t\003)y\f\006\004\006T\025\005W1\031\005\b\t\026u\006\031AC*\021\0351UQ\030a\001\013':q!b2\n\021\007)I-\001\fCS\036$UmY5nC2L5O\022:bGRLwN\\1m!\021\t)'b3\007\017\025e\025\002#\001\006NN9Q1Z\025\006P\026E\007\003BA3\013/\003B!!:\006T&!QQ[Ax\005I\021\025n\032#fG&l\027\r\\(sI\026\024\030N\\4\t\017Q)Y\r\"\001\006ZR\021Q\021\032\005\013\003o,Y-!A\005\n\005exaBCp\023!\005Q\021]\001\027\005&<G)Z2j[\006d\027i]%g\023:$Xm\032:bYB!\021QMCr\r\035)i+\003E\001\013K\034r!b9*\013O,\t\016\005\003\002f\025-\006b\002\013\006d\022\005Q1\036\013\003\013CD!\"a>\006d\006\005I\021BA}\017\035)\t0\003E\002\013g\f!\003R8vE2,\027j\035$sC\016$\030n\0348bYB!\021QMC{\r\035)y\"\003E\001\013o\034r!\">*\013s,Y\020\005\003\002f\025u\001\003BAs\013{LA!b@\002p\nqAi\\;cY\026|%\017Z3sS:<\007b\002\013\006v\022\005a1\001\013\003\013gD!\"a>\006v\006\005I\021BA}\017\0351I!\003E\001\r\027\t!\003R8vE2,\027i]%g\023:$Xm\032:bYB!\021Q\rD\007\r\035)\031$\003E\001\r\037\031rA\"\004*\r#)Y\020\005\003\002f\025E\002b\002\013\007\016\021\005aQ\003\013\003\r\027A!\"a>\007\016\005\005I\021BA}\021%\t90CA\001\n\023\tI\020")
/*     */ public interface Numeric<T> extends Ordering<T> {
/*     */   T plus(T paramT1, T paramT2);
/*     */   
/*     */   T minus(T paramT1, T paramT2);
/*     */   
/*     */   T times(T paramT1, T paramT2);
/*     */   
/*     */   T negate(T paramT);
/*     */   
/*     */   T fromInt(int paramInt);
/*     */   
/*     */   int toInt(T paramT);
/*     */   
/*     */   long toLong(T paramT);
/*     */   
/*     */   float toFloat(T paramT);
/*     */   
/*     */   double toDouble(T paramT);
/*     */   
/*     */   T zero();
/*     */   
/*     */   T one();
/*     */   
/*     */   T abs(T paramT);
/*     */   
/*     */   int signum(T paramT);
/*     */   
/*     */   Ops mkNumericOps(T paramT);
/*     */   
/*     */   public static abstract class ExtraImplicits$class {
/*     */     public static void $init$(Numeric.ExtraImplicits $this) {}
/*     */     
/*     */     public static Numeric.Ops infixNumericOps(Numeric.ExtraImplicits $this, Object x, Numeric<T> num) {
/*  25 */       return new Numeric.Ops(num, (T)x);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Implicits$ implements ExtraImplicits {
/*     */     public static final Implicits$ MODULE$;
/*     */     
/*     */     public <T> Numeric<T>.Ops infixNumericOps(Object x, Numeric num) {
/*  27 */       return Numeric.ExtraImplicits$class.infixNumericOps(this, x, num);
/*     */     }
/*     */     
/*     */     public Implicits$() {
/*  27 */       MODULE$ = this;
/*  27 */       Numeric.ExtraImplicits$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class BigIntIsIntegral$class {
/*     */     public static void $init$(Numeric.BigIntIsIntegral $this) {}
/*     */     
/*     */     public static BigInt plus(Numeric.BigIntIsIntegral $this, BigInt x, BigInt y) {
/*  30 */       return x.$plus(y);
/*     */     }
/*     */     
/*     */     public static BigInt minus(Numeric.BigIntIsIntegral $this, BigInt x, BigInt y) {
/*  31 */       return x.$minus(y);
/*     */     }
/*     */     
/*     */     public static BigInt times(Numeric.BigIntIsIntegral $this, BigInt x, BigInt y) {
/*  32 */       return x.$times(y);
/*     */     }
/*     */     
/*     */     public static BigInt quot(Numeric.BigIntIsIntegral $this, BigInt x, BigInt y) {
/*  33 */       return x.$div(y);
/*     */     }
/*     */     
/*     */     public static BigInt rem(Numeric.BigIntIsIntegral $this, BigInt x, BigInt y) {
/*  34 */       return x.$percent(y);
/*     */     }
/*     */     
/*     */     public static BigInt negate(Numeric.BigIntIsIntegral $this, BigInt x) {
/*  35 */       return x.unary_$minus();
/*     */     }
/*     */     
/*     */     public static BigInt fromInt(Numeric.BigIntIsIntegral $this, int x) {
/*  36 */       return BigInt$.MODULE$.apply(x);
/*     */     }
/*     */     
/*     */     public static int toInt(Numeric.BigIntIsIntegral $this, BigInt x) {
/*  37 */       return x.intValue();
/*     */     }
/*     */     
/*     */     public static long toLong(Numeric.BigIntIsIntegral $this, BigInt x) {
/*  38 */       return x.longValue();
/*     */     }
/*     */     
/*     */     public static float toFloat(Numeric.BigIntIsIntegral $this, BigInt x) {
/*  39 */       return x.floatValue();
/*     */     }
/*     */     
/*     */     public static double toDouble(Numeric.BigIntIsIntegral $this, BigInt x) {
/*  40 */       return x.doubleValue();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BigIntIsIntegral$ implements BigIntIsIntegral, Ordering.BigIntOrdering {
/*     */     public static final BigIntIsIntegral$ MODULE$;
/*     */     
/*     */     public int compare(BigInt x, BigInt y) {
/*  42 */       return Ordering.BigIntOrdering$class.compare(this, x, y);
/*     */     }
/*     */     
/*     */     public BigInt plus(BigInt x, BigInt y) {
/*  42 */       return Numeric.BigIntIsIntegral$class.plus(this, x, y);
/*     */     }
/*     */     
/*     */     public BigInt minus(BigInt x, BigInt y) {
/*  42 */       return Numeric.BigIntIsIntegral$class.minus(this, x, y);
/*     */     }
/*     */     
/*     */     public BigInt times(BigInt x, BigInt y) {
/*  42 */       return Numeric.BigIntIsIntegral$class.times(this, x, y);
/*     */     }
/*     */     
/*     */     public BigInt quot(BigInt x, BigInt y) {
/*  42 */       return Numeric.BigIntIsIntegral$class.quot(this, x, y);
/*     */     }
/*     */     
/*     */     public BigInt rem(BigInt x, BigInt y) {
/*  42 */       return Numeric.BigIntIsIntegral$class.rem(this, x, y);
/*     */     }
/*     */     
/*     */     public BigInt negate(BigInt x) {
/*  42 */       return Numeric.BigIntIsIntegral$class.negate(this, x);
/*     */     }
/*     */     
/*     */     public BigInt fromInt(int x) {
/*  42 */       return Numeric.BigIntIsIntegral$class.fromInt(this, x);
/*     */     }
/*     */     
/*     */     public int toInt(BigInt x) {
/*  42 */       return Numeric.BigIntIsIntegral$class.toInt(this, x);
/*     */     }
/*     */     
/*     */     public long toLong(BigInt x) {
/*  42 */       return Numeric.BigIntIsIntegral$class.toLong(this, x);
/*     */     }
/*     */     
/*     */     public float toFloat(BigInt x) {
/*  42 */       return Numeric.BigIntIsIntegral$class.toFloat(this, x);
/*     */     }
/*     */     
/*     */     public double toDouble(BigInt x) {
/*  42 */       return Numeric.BigIntIsIntegral$class.toDouble(this, x);
/*     */     }
/*     */     
/*     */     public Integral<BigInt>.IntegralOps mkNumericOps(Object lhs) {
/*  42 */       return Integral$class.mkNumericOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Object zero() {
/*  42 */       return Numeric$class.zero(this);
/*     */     }
/*     */     
/*     */     public Object one() {
/*  42 */       return Numeric$class.one(this);
/*     */     }
/*     */     
/*     */     public Object abs(Object x) {
/*  42 */       return Numeric$class.abs(this, x);
/*     */     }
/*     */     
/*     */     public int signum(Object x) {
/*  42 */       return Numeric$class.signum(this, x);
/*     */     }
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/*  42 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/*  42 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/*  42 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/*  42 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/*  42 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/*  42 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Object max(Object x, Object y) {
/*  42 */       return Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Object min(Object x, Object y) {
/*  42 */       return Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<BigInt> reverse() {
/*  42 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/*  42 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<BigInt>.Ops mkOrderingOps(Object lhs) {
/*  42 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  42 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public BigIntIsIntegral$() {
/*  42 */       MODULE$ = this;
/*  42 */       PartialOrdering$class.$init$(this);
/*  42 */       Ordering$class.$init$(this);
/*  42 */       Numeric$class.$init$(this);
/*  42 */       Integral$class.$init$(this);
/*  42 */       Numeric.BigIntIsIntegral$class.$init$(this);
/*  42 */       Ordering.BigIntOrdering$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class IntIsIntegral$class {
/*     */     public static void $init$(Numeric.IntIsIntegral $this) {}
/*     */     
/*     */     public static int plus(Numeric.IntIsIntegral $this, int x, int y) {
/*  45 */       return x + y;
/*     */     }
/*     */     
/*     */     public static int minus(Numeric.IntIsIntegral $this, int x, int y) {
/*  46 */       return x - y;
/*     */     }
/*     */     
/*     */     public static int times(Numeric.IntIsIntegral $this, int x, int y) {
/*  47 */       return x * y;
/*     */     }
/*     */     
/*     */     public static int quot(Numeric.IntIsIntegral $this, int x, int y) {
/*  48 */       return x / y;
/*     */     }
/*     */     
/*     */     public static int rem(Numeric.IntIsIntegral $this, int x, int y) {
/*  49 */       return x % y;
/*     */     }
/*     */     
/*     */     public static int negate(Numeric.IntIsIntegral $this, int x) {
/*  50 */       return -x;
/*     */     }
/*     */     
/*     */     public static int fromInt(Numeric.IntIsIntegral $this, int x) {
/*  51 */       return x;
/*     */     }
/*     */     
/*     */     public static int toInt(Numeric.IntIsIntegral $this, int x) {
/*  52 */       return x;
/*     */     }
/*     */     
/*     */     public static long toLong(Numeric.IntIsIntegral $this, int x) {
/*  53 */       return x;
/*     */     }
/*     */     
/*     */     public static float toFloat(Numeric.IntIsIntegral $this, int x) {
/*  54 */       return x;
/*     */     }
/*     */     
/*     */     public static double toDouble(Numeric.IntIsIntegral $this, int x) {
/*  55 */       return x;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class IntIsIntegral$ implements IntIsIntegral, Ordering.IntOrdering {
/*     */     public static final IntIsIntegral$ MODULE$;
/*     */     
/*     */     public int compare(int x, int y) {
/*  57 */       return Ordering.IntOrdering$class.compare(this, x, y);
/*     */     }
/*     */     
/*     */     public int plus(int x, int y) {
/*  57 */       return Numeric.IntIsIntegral$class.plus(this, x, y);
/*     */     }
/*     */     
/*     */     public int minus(int x, int y) {
/*  57 */       return Numeric.IntIsIntegral$class.minus(this, x, y);
/*     */     }
/*     */     
/*     */     public int times(int x, int y) {
/*  57 */       return Numeric.IntIsIntegral$class.times(this, x, y);
/*     */     }
/*     */     
/*     */     public int quot(int x, int y) {
/*  57 */       return Numeric.IntIsIntegral$class.quot(this, x, y);
/*     */     }
/*     */     
/*     */     public int rem(int x, int y) {
/*  57 */       return Numeric.IntIsIntegral$class.rem(this, x, y);
/*     */     }
/*     */     
/*     */     public int negate(int x) {
/*  57 */       return Numeric.IntIsIntegral$class.negate(this, x);
/*     */     }
/*     */     
/*     */     public int fromInt(int x) {
/*  57 */       return Numeric.IntIsIntegral$class.fromInt(this, x);
/*     */     }
/*     */     
/*     */     public int toInt(int x) {
/*  57 */       return Numeric.IntIsIntegral$class.toInt(this, x);
/*     */     }
/*     */     
/*     */     public long toLong(int x) {
/*  57 */       return Numeric.IntIsIntegral$class.toLong(this, x);
/*     */     }
/*     */     
/*     */     public float toFloat(int x) {
/*  57 */       return Numeric.IntIsIntegral$class.toFloat(this, x);
/*     */     }
/*     */     
/*     */     public double toDouble(int x) {
/*  57 */       return Numeric.IntIsIntegral$class.toDouble(this, x);
/*     */     }
/*     */     
/*     */     public Integral<Object>.IntegralOps mkNumericOps(Object lhs) {
/*  57 */       return Integral$class.mkNumericOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Object zero() {
/*  57 */       return Numeric$class.zero(this);
/*     */     }
/*     */     
/*     */     public Object one() {
/*  57 */       return Numeric$class.one(this);
/*     */     }
/*     */     
/*     */     public Object abs(Object x) {
/*  57 */       return Numeric$class.abs(this, x);
/*     */     }
/*     */     
/*     */     public int signum(Object x) {
/*  57 */       return Numeric$class.signum(this, x);
/*     */     }
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/*  57 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/*  57 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/*  57 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/*  57 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/*  57 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/*  57 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Object max(Object x, Object y) {
/*  57 */       return Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Object min(Object x, Object y) {
/*  57 */       return Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Object> reverse() {
/*  57 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/*  57 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Object>.Ops mkOrderingOps(Object lhs) {
/*  57 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  57 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public IntIsIntegral$() {
/*  57 */       MODULE$ = this;
/*  57 */       PartialOrdering$class.$init$(this);
/*  57 */       Ordering$class.$init$(this);
/*  57 */       Numeric$class.$init$(this);
/*  57 */       Integral$class.$init$(this);
/*  57 */       Numeric.IntIsIntegral$class.$init$(this);
/*  57 */       Ordering.IntOrdering$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class ShortIsIntegral$class {
/*     */     public static void $init$(Numeric.ShortIsIntegral $this) {}
/*     */     
/*     */     public static short plus(Numeric.ShortIsIntegral $this, short x, short y) {
/*  60 */       return (short)(x + y);
/*     */     }
/*     */     
/*     */     public static short minus(Numeric.ShortIsIntegral $this, short x, short y) {
/*  61 */       return (short)(x - y);
/*     */     }
/*     */     
/*     */     public static short times(Numeric.ShortIsIntegral $this, short x, short y) {
/*  62 */       return (short)(x * y);
/*     */     }
/*     */     
/*     */     public static short quot(Numeric.ShortIsIntegral $this, short x, short y) {
/*  63 */       return (short)(x / y);
/*     */     }
/*     */     
/*     */     public static short rem(Numeric.ShortIsIntegral $this, short x, short y) {
/*  64 */       return (short)(x % y);
/*     */     }
/*     */     
/*     */     public static short negate(Numeric.ShortIsIntegral $this, short x) {
/*  65 */       return (short)-x;
/*     */     }
/*     */     
/*     */     public static short fromInt(Numeric.ShortIsIntegral $this, int x) {
/*  66 */       return (short)x;
/*     */     }
/*     */     
/*     */     public static int toInt(Numeric.ShortIsIntegral $this, short x) {
/*  67 */       return x;
/*     */     }
/*     */     
/*     */     public static long toLong(Numeric.ShortIsIntegral $this, short x) {
/*  68 */       return x;
/*     */     }
/*     */     
/*     */     public static float toFloat(Numeric.ShortIsIntegral $this, short x) {
/*  69 */       return x;
/*     */     }
/*     */     
/*     */     public static double toDouble(Numeric.ShortIsIntegral $this, short x) {
/*  70 */       return x;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ShortIsIntegral$ implements ShortIsIntegral, Ordering.ShortOrdering {
/*     */     public static final ShortIsIntegral$ MODULE$;
/*     */     
/*     */     public int compare(short x, short y) {
/*  72 */       return Ordering.ShortOrdering$class.compare(this, x, y);
/*     */     }
/*     */     
/*     */     public short plus(short x, short y) {
/*  72 */       return Numeric.ShortIsIntegral$class.plus(this, x, y);
/*     */     }
/*     */     
/*     */     public short minus(short x, short y) {
/*  72 */       return Numeric.ShortIsIntegral$class.minus(this, x, y);
/*     */     }
/*     */     
/*     */     public short times(short x, short y) {
/*  72 */       return Numeric.ShortIsIntegral$class.times(this, x, y);
/*     */     }
/*     */     
/*     */     public short quot(short x, short y) {
/*  72 */       return Numeric.ShortIsIntegral$class.quot(this, x, y);
/*     */     }
/*     */     
/*     */     public short rem(short x, short y) {
/*  72 */       return Numeric.ShortIsIntegral$class.rem(this, x, y);
/*     */     }
/*     */     
/*     */     public short negate(short x) {
/*  72 */       return Numeric.ShortIsIntegral$class.negate(this, x);
/*     */     }
/*     */     
/*     */     public short fromInt(int x) {
/*  72 */       return Numeric.ShortIsIntegral$class.fromInt(this, x);
/*     */     }
/*     */     
/*     */     public int toInt(short x) {
/*  72 */       return Numeric.ShortIsIntegral$class.toInt(this, x);
/*     */     }
/*     */     
/*     */     public long toLong(short x) {
/*  72 */       return Numeric.ShortIsIntegral$class.toLong(this, x);
/*     */     }
/*     */     
/*     */     public float toFloat(short x) {
/*  72 */       return Numeric.ShortIsIntegral$class.toFloat(this, x);
/*     */     }
/*     */     
/*     */     public double toDouble(short x) {
/*  72 */       return Numeric.ShortIsIntegral$class.toDouble(this, x);
/*     */     }
/*     */     
/*     */     public Integral<Object>.IntegralOps mkNumericOps(Object lhs) {
/*  72 */       return Integral$class.mkNumericOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Object zero() {
/*  72 */       return Numeric$class.zero(this);
/*     */     }
/*     */     
/*     */     public Object one() {
/*  72 */       return Numeric$class.one(this);
/*     */     }
/*     */     
/*     */     public Object abs(Object x) {
/*  72 */       return Numeric$class.abs(this, x);
/*     */     }
/*     */     
/*     */     public int signum(Object x) {
/*  72 */       return Numeric$class.signum(this, x);
/*     */     }
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/*  72 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/*  72 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/*  72 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/*  72 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/*  72 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/*  72 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Object max(Object x, Object y) {
/*  72 */       return Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Object min(Object x, Object y) {
/*  72 */       return Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Object> reverse() {
/*  72 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/*  72 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Object>.Ops mkOrderingOps(Object lhs) {
/*  72 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  72 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public ShortIsIntegral$() {
/*  72 */       MODULE$ = this;
/*  72 */       PartialOrdering$class.$init$(this);
/*  72 */       Ordering$class.$init$(this);
/*  72 */       Numeric$class.$init$(this);
/*  72 */       Integral$class.$init$(this);
/*  72 */       Numeric.ShortIsIntegral$class.$init$(this);
/*  72 */       Ordering.ShortOrdering$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class ByteIsIntegral$class {
/*     */     public static void $init$(Numeric.ByteIsIntegral $this) {}
/*     */     
/*     */     public static byte plus(Numeric.ByteIsIntegral $this, byte x, byte y) {
/*  75 */       return (byte)(x + y);
/*     */     }
/*     */     
/*     */     public static byte minus(Numeric.ByteIsIntegral $this, byte x, byte y) {
/*  76 */       return (byte)(x - y);
/*     */     }
/*     */     
/*     */     public static byte times(Numeric.ByteIsIntegral $this, byte x, byte y) {
/*  77 */       return (byte)(x * y);
/*     */     }
/*     */     
/*     */     public static byte quot(Numeric.ByteIsIntegral $this, byte x, byte y) {
/*  78 */       return (byte)(x / y);
/*     */     }
/*     */     
/*     */     public static byte rem(Numeric.ByteIsIntegral $this, byte x, byte y) {
/*  79 */       return (byte)(x % y);
/*     */     }
/*     */     
/*     */     public static byte negate(Numeric.ByteIsIntegral $this, byte x) {
/*  80 */       return (byte)-x;
/*     */     }
/*     */     
/*     */     public static byte fromInt(Numeric.ByteIsIntegral $this, int x) {
/*  81 */       return (byte)x;
/*     */     }
/*     */     
/*     */     public static int toInt(Numeric.ByteIsIntegral $this, byte x) {
/*  82 */       return x;
/*     */     }
/*     */     
/*     */     public static long toLong(Numeric.ByteIsIntegral $this, byte x) {
/*  83 */       return x;
/*     */     }
/*     */     
/*     */     public static float toFloat(Numeric.ByteIsIntegral $this, byte x) {
/*  84 */       return x;
/*     */     }
/*     */     
/*     */     public static double toDouble(Numeric.ByteIsIntegral $this, byte x) {
/*  85 */       return x;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ByteIsIntegral$ implements ByteIsIntegral, Ordering.ByteOrdering {
/*     */     public static final ByteIsIntegral$ MODULE$;
/*     */     
/*     */     public int compare(byte x, byte y) {
/*  87 */       return Ordering.ByteOrdering$class.compare(this, x, y);
/*     */     }
/*     */     
/*     */     public byte plus(byte x, byte y) {
/*  87 */       return Numeric.ByteIsIntegral$class.plus(this, x, y);
/*     */     }
/*     */     
/*     */     public byte minus(byte x, byte y) {
/*  87 */       return Numeric.ByteIsIntegral$class.minus(this, x, y);
/*     */     }
/*     */     
/*     */     public byte times(byte x, byte y) {
/*  87 */       return Numeric.ByteIsIntegral$class.times(this, x, y);
/*     */     }
/*     */     
/*     */     public byte quot(byte x, byte y) {
/*  87 */       return Numeric.ByteIsIntegral$class.quot(this, x, y);
/*     */     }
/*     */     
/*     */     public byte rem(byte x, byte y) {
/*  87 */       return Numeric.ByteIsIntegral$class.rem(this, x, y);
/*     */     }
/*     */     
/*     */     public byte negate(byte x) {
/*  87 */       return Numeric.ByteIsIntegral$class.negate(this, x);
/*     */     }
/*     */     
/*     */     public byte fromInt(int x) {
/*  87 */       return Numeric.ByteIsIntegral$class.fromInt(this, x);
/*     */     }
/*     */     
/*     */     public int toInt(byte x) {
/*  87 */       return Numeric.ByteIsIntegral$class.toInt(this, x);
/*     */     }
/*     */     
/*     */     public long toLong(byte x) {
/*  87 */       return Numeric.ByteIsIntegral$class.toLong(this, x);
/*     */     }
/*     */     
/*     */     public float toFloat(byte x) {
/*  87 */       return Numeric.ByteIsIntegral$class.toFloat(this, x);
/*     */     }
/*     */     
/*     */     public double toDouble(byte x) {
/*  87 */       return Numeric.ByteIsIntegral$class.toDouble(this, x);
/*     */     }
/*     */     
/*     */     public Integral<Object>.IntegralOps mkNumericOps(Object lhs) {
/*  87 */       return Integral$class.mkNumericOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Object zero() {
/*  87 */       return Numeric$class.zero(this);
/*     */     }
/*     */     
/*     */     public Object one() {
/*  87 */       return Numeric$class.one(this);
/*     */     }
/*     */     
/*     */     public Object abs(Object x) {
/*  87 */       return Numeric$class.abs(this, x);
/*     */     }
/*     */     
/*     */     public int signum(Object x) {
/*  87 */       return Numeric$class.signum(this, x);
/*     */     }
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/*  87 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/*  87 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/*  87 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/*  87 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/*  87 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/*  87 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Object max(Object x, Object y) {
/*  87 */       return Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Object min(Object x, Object y) {
/*  87 */       return Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Object> reverse() {
/*  87 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/*  87 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Object>.Ops mkOrderingOps(Object lhs) {
/*  87 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  87 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public ByteIsIntegral$() {
/*  87 */       MODULE$ = this;
/*  87 */       PartialOrdering$class.$init$(this);
/*  87 */       Ordering$class.$init$(this);
/*  87 */       Numeric$class.$init$(this);
/*  87 */       Integral$class.$init$(this);
/*  87 */       Numeric.ByteIsIntegral$class.$init$(this);
/*  87 */       Ordering.ByteOrdering$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class CharIsIntegral$class {
/*     */     public static void $init$(Numeric.CharIsIntegral $this) {}
/*     */     
/*     */     public static char plus(Numeric.CharIsIntegral $this, char x, char y) {
/*  90 */       return (char)(x + y);
/*     */     }
/*     */     
/*     */     public static char minus(Numeric.CharIsIntegral $this, char x, char y) {
/*  91 */       return (char)(x - y);
/*     */     }
/*     */     
/*     */     public static char times(Numeric.CharIsIntegral $this, char x, char y) {
/*  92 */       return (char)(x * y);
/*     */     }
/*     */     
/*     */     public static char quot(Numeric.CharIsIntegral $this, char x, char y) {
/*  93 */       return (char)(x / y);
/*     */     }
/*     */     
/*     */     public static char rem(Numeric.CharIsIntegral $this, char x, char y) {
/*  94 */       return (char)(x % y);
/*     */     }
/*     */     
/*     */     public static char negate(Numeric.CharIsIntegral $this, char x) {
/*  95 */       return (char)-x;
/*     */     }
/*     */     
/*     */     public static char fromInt(Numeric.CharIsIntegral $this, int x) {
/*  96 */       return (char)x;
/*     */     }
/*     */     
/*     */     public static int toInt(Numeric.CharIsIntegral $this, char x) {
/*  97 */       return x;
/*     */     }
/*     */     
/*     */     public static long toLong(Numeric.CharIsIntegral $this, char x) {
/*  98 */       return x;
/*     */     }
/*     */     
/*     */     public static float toFloat(Numeric.CharIsIntegral $this, char x) {
/*  99 */       return x;
/*     */     }
/*     */     
/*     */     public static double toDouble(Numeric.CharIsIntegral $this, char x) {
/* 100 */       return x;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class CharIsIntegral$ implements CharIsIntegral, Ordering.CharOrdering {
/*     */     public static final CharIsIntegral$ MODULE$;
/*     */     
/*     */     public int compare(char x, char y) {
/* 102 */       return Ordering.CharOrdering$class.compare(this, x, y);
/*     */     }
/*     */     
/*     */     public char plus(char x, char y) {
/* 102 */       return Numeric.CharIsIntegral$class.plus(this, x, y);
/*     */     }
/*     */     
/*     */     public char minus(char x, char y) {
/* 102 */       return Numeric.CharIsIntegral$class.minus(this, x, y);
/*     */     }
/*     */     
/*     */     public char times(char x, char y) {
/* 102 */       return Numeric.CharIsIntegral$class.times(this, x, y);
/*     */     }
/*     */     
/*     */     public char quot(char x, char y) {
/* 102 */       return Numeric.CharIsIntegral$class.quot(this, x, y);
/*     */     }
/*     */     
/*     */     public char rem(char x, char y) {
/* 102 */       return Numeric.CharIsIntegral$class.rem(this, x, y);
/*     */     }
/*     */     
/*     */     public char negate(char x) {
/* 102 */       return Numeric.CharIsIntegral$class.negate(this, x);
/*     */     }
/*     */     
/*     */     public char fromInt(int x) {
/* 102 */       return Numeric.CharIsIntegral$class.fromInt(this, x);
/*     */     }
/*     */     
/*     */     public int toInt(char x) {
/* 102 */       return Numeric.CharIsIntegral$class.toInt(this, x);
/*     */     }
/*     */     
/*     */     public long toLong(char x) {
/* 102 */       return Numeric.CharIsIntegral$class.toLong(this, x);
/*     */     }
/*     */     
/*     */     public float toFloat(char x) {
/* 102 */       return Numeric.CharIsIntegral$class.toFloat(this, x);
/*     */     }
/*     */     
/*     */     public double toDouble(char x) {
/* 102 */       return Numeric.CharIsIntegral$class.toDouble(this, x);
/*     */     }
/*     */     
/*     */     public Integral<Object>.IntegralOps mkNumericOps(Object lhs) {
/* 102 */       return Integral$class.mkNumericOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Object zero() {
/* 102 */       return Numeric$class.zero(this);
/*     */     }
/*     */     
/*     */     public Object one() {
/* 102 */       return Numeric$class.one(this);
/*     */     }
/*     */     
/*     */     public Object abs(Object x) {
/* 102 */       return Numeric$class.abs(this, x);
/*     */     }
/*     */     
/*     */     public int signum(Object x) {
/* 102 */       return Numeric$class.signum(this, x);
/*     */     }
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 102 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 102 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 102 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 102 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 102 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 102 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Object max(Object x, Object y) {
/* 102 */       return Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Object min(Object x, Object y) {
/* 102 */       return Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Object> reverse() {
/* 102 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 102 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Object>.Ops mkOrderingOps(Object lhs) {
/* 102 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 102 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public CharIsIntegral$() {
/* 102 */       MODULE$ = this;
/* 102 */       PartialOrdering$class.$init$(this);
/* 102 */       Ordering$class.$init$(this);
/* 102 */       Numeric$class.$init$(this);
/* 102 */       Integral$class.$init$(this);
/* 102 */       Numeric.CharIsIntegral$class.$init$(this);
/* 102 */       Ordering.CharOrdering$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class LongIsIntegral$class {
/*     */     public static void $init$(Numeric.LongIsIntegral $this) {}
/*     */     
/*     */     public static long plus(Numeric.LongIsIntegral $this, long x, long y) {
/* 105 */       return x + y;
/*     */     }
/*     */     
/*     */     public static long minus(Numeric.LongIsIntegral $this, long x, long y) {
/* 106 */       return x - y;
/*     */     }
/*     */     
/*     */     public static long times(Numeric.LongIsIntegral $this, long x, long y) {
/* 107 */       return x * y;
/*     */     }
/*     */     
/*     */     public static long quot(Numeric.LongIsIntegral $this, long x, long y) {
/* 108 */       return x / y;
/*     */     }
/*     */     
/*     */     public static long rem(Numeric.LongIsIntegral $this, long x, long y) {
/* 109 */       return x % y;
/*     */     }
/*     */     
/*     */     public static long negate(Numeric.LongIsIntegral $this, long x) {
/* 110 */       return -x;
/*     */     }
/*     */     
/*     */     public static long fromInt(Numeric.LongIsIntegral $this, int x) {
/* 111 */       return x;
/*     */     }
/*     */     
/*     */     public static int toInt(Numeric.LongIsIntegral $this, long x) {
/* 112 */       return (int)x;
/*     */     }
/*     */     
/*     */     public static long toLong(Numeric.LongIsIntegral $this, long x) {
/* 113 */       return x;
/*     */     }
/*     */     
/*     */     public static float toFloat(Numeric.LongIsIntegral $this, long x) {
/* 114 */       return (float)x;
/*     */     }
/*     */     
/*     */     public static double toDouble(Numeric.LongIsIntegral $this, long x) {
/* 115 */       return x;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class LongIsIntegral$ implements LongIsIntegral, Ordering.LongOrdering {
/*     */     public static final LongIsIntegral$ MODULE$;
/*     */     
/*     */     public int compare(long x, long y) {
/* 117 */       return Ordering.LongOrdering$class.compare(this, x, y);
/*     */     }
/*     */     
/*     */     public long plus(long x, long y) {
/* 117 */       return Numeric.LongIsIntegral$class.plus(this, x, y);
/*     */     }
/*     */     
/*     */     public long minus(long x, long y) {
/* 117 */       return Numeric.LongIsIntegral$class.minus(this, x, y);
/*     */     }
/*     */     
/*     */     public long times(long x, long y) {
/* 117 */       return Numeric.LongIsIntegral$class.times(this, x, y);
/*     */     }
/*     */     
/*     */     public long quot(long x, long y) {
/* 117 */       return Numeric.LongIsIntegral$class.quot(this, x, y);
/*     */     }
/*     */     
/*     */     public long rem(long x, long y) {
/* 117 */       return Numeric.LongIsIntegral$class.rem(this, x, y);
/*     */     }
/*     */     
/*     */     public long negate(long x) {
/* 117 */       return Numeric.LongIsIntegral$class.negate(this, x);
/*     */     }
/*     */     
/*     */     public long fromInt(int x) {
/* 117 */       return Numeric.LongIsIntegral$class.fromInt(this, x);
/*     */     }
/*     */     
/*     */     public int toInt(long x) {
/* 117 */       return Numeric.LongIsIntegral$class.toInt(this, x);
/*     */     }
/*     */     
/*     */     public long toLong(long x) {
/* 117 */       return Numeric.LongIsIntegral$class.toLong(this, x);
/*     */     }
/*     */     
/*     */     public float toFloat(long x) {
/* 117 */       return Numeric.LongIsIntegral$class.toFloat(this, x);
/*     */     }
/*     */     
/*     */     public double toDouble(long x) {
/* 117 */       return Numeric.LongIsIntegral$class.toDouble(this, x);
/*     */     }
/*     */     
/*     */     public Integral<Object>.IntegralOps mkNumericOps(Object lhs) {
/* 117 */       return Integral$class.mkNumericOps(this, lhs);
/*     */     }
/*     */     
/*     */     public Object zero() {
/* 117 */       return Numeric$class.zero(this);
/*     */     }
/*     */     
/*     */     public Object one() {
/* 117 */       return Numeric$class.one(this);
/*     */     }
/*     */     
/*     */     public Object abs(Object x) {
/* 117 */       return Numeric$class.abs(this, x);
/*     */     }
/*     */     
/*     */     public int signum(Object x) {
/* 117 */       return Numeric$class.signum(this, x);
/*     */     }
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 117 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 117 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 117 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 117 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 117 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 117 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Object max(Object x, Object y) {
/* 117 */       return Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Object min(Object x, Object y) {
/* 117 */       return Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Object> reverse() {
/* 117 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 117 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Object>.Ops mkOrderingOps(Object lhs) {
/* 117 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 117 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public LongIsIntegral$() {
/* 117 */       MODULE$ = this;
/* 117 */       PartialOrdering$class.$init$(this);
/* 117 */       Ordering$class.$init$(this);
/* 117 */       Numeric$class.$init$(this);
/* 117 */       Integral$class.$init$(this);
/* 117 */       Numeric.LongIsIntegral$class.$init$(this);
/* 117 */       Ordering.LongOrdering$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class FloatIsConflicted$class {
/*     */     public static void $init$(Numeric.FloatIsConflicted $this) {}
/*     */     
/*     */     public static float plus(Numeric.FloatIsConflicted $this, float x, float y) {
/* 120 */       return x + y;
/*     */     }
/*     */     
/*     */     public static float minus(Numeric.FloatIsConflicted $this, float x, float y) {
/* 121 */       return x - y;
/*     */     }
/*     */     
/*     */     public static float times(Numeric.FloatIsConflicted $this, float x, float y) {
/* 122 */       return x * y;
/*     */     }
/*     */     
/*     */     public static float negate(Numeric.FloatIsConflicted $this, float x) {
/* 123 */       return -x;
/*     */     }
/*     */     
/*     */     public static float fromInt(Numeric.FloatIsConflicted $this, int x) {
/* 124 */       return x;
/*     */     }
/*     */     
/*     */     public static int toInt(Numeric.FloatIsConflicted $this, float x) {
/* 125 */       return (int)x;
/*     */     }
/*     */     
/*     */     public static long toLong(Numeric.FloatIsConflicted $this, float x) {
/* 126 */       return (long)x;
/*     */     }
/*     */     
/*     */     public static float toFloat(Numeric.FloatIsConflicted $this, float x) {
/* 127 */       return x;
/*     */     }
/*     */     
/*     */     public static double toDouble(Numeric.FloatIsConflicted $this, float x) {
/* 128 */       return x;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class FloatIsFractional$class {
/*     */     public static void $init$(Numeric.FloatIsFractional $this) {}
/*     */     
/*     */     public static float div(Numeric.FloatIsFractional $this, float x, float y) {
/* 131 */       return x / y;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class FloatAsIfIntegral$class {
/*     */     public static void $init$(Numeric.FloatAsIfIntegral $this) {}
/*     */     
/*     */     public static float quot(Numeric.FloatAsIfIntegral $this, float x, float y) {
/* 134 */       return BigDecimal$.MODULE$.apply(x).$div(BigDecimal$.MODULE$.apply(y)).floatValue();
/*     */     }
/*     */     
/*     */     public static float rem(Numeric.FloatAsIfIntegral $this, float x, float y) {
/* 135 */       return BigDecimal$.MODULE$.apply(x).remainder(BigDecimal$.MODULE$.apply(y)).floatValue();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class FloatIsFractional$ implements FloatIsFractional, Ordering.FloatOrdering {
/*     */     public static final FloatIsFractional$ MODULE$;
/*     */     
/*     */     public int compare(float x, float y) {
/* 137 */       return Ordering.FloatOrdering$class.compare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(float x, float y) {
/* 137 */       return Ordering.FloatOrdering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(float x, float y) {
/* 137 */       return Ordering.FloatOrdering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(float x, float y) {
/* 137 */       return Ordering.FloatOrdering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(float x, float y) {
/* 137 */       return Ordering.FloatOrdering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(float x, float y) {
/* 137 */       return Ordering.FloatOrdering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public float max(float x, float y) {
/* 137 */       return Ordering.FloatOrdering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public float min(float x, float y) {
/* 137 */       return Ordering.FloatOrdering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Object> reverse() {
/* 137 */       return Ordering.FloatOrdering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public float div(float x, float y) {
/* 137 */       return Numeric.FloatIsFractional$class.div(this, x, y);
/*     */     }
/*     */     
/*     */     public Fractional<Object>.FractionalOps mkNumericOps(Object lhs) {
/* 137 */       return Fractional$class.mkNumericOps(this, lhs);
/*     */     }
/*     */     
/*     */     public float plus(float x, float y) {
/* 137 */       return Numeric.FloatIsConflicted$class.plus(this, x, y);
/*     */     }
/*     */     
/*     */     public float minus(float x, float y) {
/* 137 */       return Numeric.FloatIsConflicted$class.minus(this, x, y);
/*     */     }
/*     */     
/*     */     public float times(float x, float y) {
/* 137 */       return Numeric.FloatIsConflicted$class.times(this, x, y);
/*     */     }
/*     */     
/*     */     public float negate(float x) {
/* 137 */       return Numeric.FloatIsConflicted$class.negate(this, x);
/*     */     }
/*     */     
/*     */     public float fromInt(int x) {
/* 137 */       return Numeric.FloatIsConflicted$class.fromInt(this, x);
/*     */     }
/*     */     
/*     */     public int toInt(float x) {
/* 137 */       return Numeric.FloatIsConflicted$class.toInt(this, x);
/*     */     }
/*     */     
/*     */     public long toLong(float x) {
/* 137 */       return Numeric.FloatIsConflicted$class.toLong(this, x);
/*     */     }
/*     */     
/*     */     public float toFloat(float x) {
/* 137 */       return Numeric.FloatIsConflicted$class.toFloat(this, x);
/*     */     }
/*     */     
/*     */     public double toDouble(float x) {
/* 137 */       return Numeric.FloatIsConflicted$class.toDouble(this, x);
/*     */     }
/*     */     
/*     */     public Object zero() {
/* 137 */       return Numeric$class.zero(this);
/*     */     }
/*     */     
/*     */     public Object one() {
/* 137 */       return Numeric$class.one(this);
/*     */     }
/*     */     
/*     */     public Object abs(Object x) {
/* 137 */       return Numeric$class.abs(this, x);
/*     */     }
/*     */     
/*     */     public int signum(Object x) {
/* 137 */       return Numeric$class.signum(this, x);
/*     */     }
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 137 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 137 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Object>.Ops mkOrderingOps(Object lhs) {
/* 137 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 137 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public FloatIsFractional$() {
/* 137 */       MODULE$ = this;
/* 137 */       PartialOrdering$class.$init$(this);
/* 137 */       Ordering$class.$init$(this);
/* 137 */       Numeric$class.$init$(this);
/* 137 */       Numeric.FloatIsConflicted$class.$init$(this);
/* 137 */       Fractional$class.$init$(this);
/* 137 */       Numeric.FloatIsFractional$class.$init$(this);
/* 137 */       Ordering.FloatOrdering$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class FloatAsIfIntegral$ implements FloatAsIfIntegral, Ordering.FloatOrdering {
/*     */     public static final FloatAsIfIntegral$ MODULE$;
/*     */     
/*     */     public int compare(float x, float y) {
/* 138 */       return Ordering.FloatOrdering$class.compare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(float x, float y) {
/* 138 */       return Ordering.FloatOrdering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(float x, float y) {
/* 138 */       return Ordering.FloatOrdering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(float x, float y) {
/* 138 */       return Ordering.FloatOrdering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(float x, float y) {
/* 138 */       return Ordering.FloatOrdering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(float x, float y) {
/* 138 */       return Ordering.FloatOrdering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public float max(float x, float y) {
/* 138 */       return Ordering.FloatOrdering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public float min(float x, float y) {
/* 138 */       return Ordering.FloatOrdering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Object> reverse() {
/* 138 */       return Ordering.FloatOrdering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public float quot(float x, float y) {
/* 138 */       return Numeric.FloatAsIfIntegral$class.quot(this, x, y);
/*     */     }
/*     */     
/*     */     public float rem(float x, float y) {
/* 138 */       return Numeric.FloatAsIfIntegral$class.rem(this, x, y);
/*     */     }
/*     */     
/*     */     public Integral<Object>.IntegralOps mkNumericOps(Object lhs) {
/* 138 */       return Integral$class.mkNumericOps(this, lhs);
/*     */     }
/*     */     
/*     */     public float plus(float x, float y) {
/* 138 */       return Numeric.FloatIsConflicted$class.plus(this, x, y);
/*     */     }
/*     */     
/*     */     public float minus(float x, float y) {
/* 138 */       return Numeric.FloatIsConflicted$class.minus(this, x, y);
/*     */     }
/*     */     
/*     */     public float times(float x, float y) {
/* 138 */       return Numeric.FloatIsConflicted$class.times(this, x, y);
/*     */     }
/*     */     
/*     */     public float negate(float x) {
/* 138 */       return Numeric.FloatIsConflicted$class.negate(this, x);
/*     */     }
/*     */     
/*     */     public float fromInt(int x) {
/* 138 */       return Numeric.FloatIsConflicted$class.fromInt(this, x);
/*     */     }
/*     */     
/*     */     public int toInt(float x) {
/* 138 */       return Numeric.FloatIsConflicted$class.toInt(this, x);
/*     */     }
/*     */     
/*     */     public long toLong(float x) {
/* 138 */       return Numeric.FloatIsConflicted$class.toLong(this, x);
/*     */     }
/*     */     
/*     */     public float toFloat(float x) {
/* 138 */       return Numeric.FloatIsConflicted$class.toFloat(this, x);
/*     */     }
/*     */     
/*     */     public double toDouble(float x) {
/* 138 */       return Numeric.FloatIsConflicted$class.toDouble(this, x);
/*     */     }
/*     */     
/*     */     public Object zero() {
/* 138 */       return Numeric$class.zero(this);
/*     */     }
/*     */     
/*     */     public Object one() {
/* 138 */       return Numeric$class.one(this);
/*     */     }
/*     */     
/*     */     public Object abs(Object x) {
/* 138 */       return Numeric$class.abs(this, x);
/*     */     }
/*     */     
/*     */     public int signum(Object x) {
/* 138 */       return Numeric$class.signum(this, x);
/*     */     }
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 138 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 138 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Object>.Ops mkOrderingOps(Object lhs) {
/* 138 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 138 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public FloatAsIfIntegral$() {
/* 138 */       MODULE$ = this;
/* 138 */       PartialOrdering$class.$init$(this);
/* 138 */       Ordering$class.$init$(this);
/* 138 */       Numeric$class.$init$(this);
/* 138 */       Numeric.FloatIsConflicted$class.$init$(this);
/* 138 */       Integral$class.$init$(this);
/* 138 */       Numeric.FloatAsIfIntegral$class.$init$(this);
/* 138 */       Ordering.FloatOrdering$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class DoubleIsConflicted$class {
/*     */     public static void $init$(Numeric.DoubleIsConflicted $this) {}
/*     */     
/*     */     public static double plus(Numeric.DoubleIsConflicted $this, double x, double y) {
/* 142 */       return x + y;
/*     */     }
/*     */     
/*     */     public static double minus(Numeric.DoubleIsConflicted $this, double x, double y) {
/* 143 */       return x - y;
/*     */     }
/*     */     
/*     */     public static double times(Numeric.DoubleIsConflicted $this, double x, double y) {
/* 144 */       return x * y;
/*     */     }
/*     */     
/*     */     public static double negate(Numeric.DoubleIsConflicted $this, double x) {
/* 145 */       return -x;
/*     */     }
/*     */     
/*     */     public static double fromInt(Numeric.DoubleIsConflicted $this, int x) {
/* 146 */       return x;
/*     */     }
/*     */     
/*     */     public static int toInt(Numeric.DoubleIsConflicted $this, double x) {
/* 147 */       return (int)x;
/*     */     }
/*     */     
/*     */     public static long toLong(Numeric.DoubleIsConflicted $this, double x) {
/* 148 */       return (long)x;
/*     */     }
/*     */     
/*     */     public static float toFloat(Numeric.DoubleIsConflicted $this, double x) {
/* 149 */       return (float)x;
/*     */     }
/*     */     
/*     */     public static double toDouble(Numeric.DoubleIsConflicted $this, double x) {
/* 150 */       return x;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class DoubleIsFractional$class {
/*     */     public static void $init$(Numeric.DoubleIsFractional $this) {}
/*     */     
/*     */     public static double div(Numeric.DoubleIsFractional $this, double x, double y) {
/* 153 */       return x / y;
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class DoubleAsIfIntegral$class {
/*     */     public static void $init$(Numeric.DoubleAsIfIntegral $this) {}
/*     */     
/*     */     public static double quot(Numeric.DoubleAsIfIntegral $this, double x, double y) {
/* 156 */       return BigDecimal$.MODULE$.apply(x).$div(BigDecimal$.MODULE$.apply(y)).doubleValue();
/*     */     }
/*     */     
/*     */     public static double rem(Numeric.DoubleAsIfIntegral $this, double x, double y) {
/* 157 */       return BigDecimal$.MODULE$.apply(x).remainder(BigDecimal$.MODULE$.apply(y)).doubleValue();
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class BigDecimalIsConflicted$class {
/*     */     public static void $init$(Numeric.BigDecimalIsConflicted $this) {}
/*     */     
/*     */     public static BigDecimal plus(Numeric.BigDecimalIsConflicted $this, BigDecimal x, BigDecimal y) {
/* 161 */       return x.$plus(y);
/*     */     }
/*     */     
/*     */     public static BigDecimal minus(Numeric.BigDecimalIsConflicted $this, BigDecimal x, BigDecimal y) {
/* 162 */       return x.$minus(y);
/*     */     }
/*     */     
/*     */     public static BigDecimal times(Numeric.BigDecimalIsConflicted $this, BigDecimal x, BigDecimal y) {
/* 163 */       return x.$times(y);
/*     */     }
/*     */     
/*     */     public static BigDecimal negate(Numeric.BigDecimalIsConflicted $this, BigDecimal x) {
/* 164 */       return x.unary_$minus();
/*     */     }
/*     */     
/*     */     public static BigDecimal fromInt(Numeric.BigDecimalIsConflicted $this, int x) {
/* 165 */       return BigDecimal$.MODULE$.apply(x);
/*     */     }
/*     */     
/*     */     public static int toInt(Numeric.BigDecimalIsConflicted $this, BigDecimal x) {
/* 166 */       return x.intValue();
/*     */     }
/*     */     
/*     */     public static long toLong(Numeric.BigDecimalIsConflicted $this, BigDecimal x) {
/* 167 */       return x.longValue();
/*     */     }
/*     */     
/*     */     public static float toFloat(Numeric.BigDecimalIsConflicted $this, BigDecimal x) {
/* 168 */       return x.floatValue();
/*     */     }
/*     */     
/*     */     public static double toDouble(Numeric.BigDecimalIsConflicted $this, BigDecimal x) {
/* 169 */       return x.doubleValue();
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class BigDecimalIsFractional$class {
/*     */     public static void $init$(Numeric.BigDecimalIsFractional $this) {}
/*     */     
/*     */     public static BigDecimal div(Numeric.BigDecimalIsFractional $this, BigDecimal x, BigDecimal y) {
/* 173 */       return x.$div(y);
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class BigDecimalAsIfIntegral$class {
/*     */     public static void $init$(Numeric.BigDecimalAsIfIntegral $this) {}
/*     */     
/*     */     public static BigDecimal quot(Numeric.BigDecimalAsIfIntegral $this, BigDecimal x, BigDecimal y) {
/* 176 */       return x.$div(y);
/*     */     }
/*     */     
/*     */     public static BigDecimal rem(Numeric.BigDecimalAsIfIntegral $this, BigDecimal x, BigDecimal y) {
/* 177 */       return x.remainder(y);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BigDecimalIsFractional$ implements BigDecimalIsFractional, Ordering.BigDecimalOrdering {
/*     */     public static final BigDecimalIsFractional$ MODULE$;
/*     */     
/*     */     public int compare(BigDecimal x, BigDecimal y) {
/* 182 */       return Ordering.BigDecimalOrdering$class.compare(this, x, y);
/*     */     }
/*     */     
/*     */     public BigDecimal div(BigDecimal x, BigDecimal y) {
/* 182 */       return Numeric.BigDecimalIsFractional$class.div(this, x, y);
/*     */     }
/*     */     
/*     */     public Fractional<BigDecimal>.FractionalOps mkNumericOps(Object lhs) {
/* 182 */       return Fractional$class.mkNumericOps(this, lhs);
/*     */     }
/*     */     
/*     */     public BigDecimal plus(BigDecimal x, BigDecimal y) {
/* 182 */       return Numeric.BigDecimalIsConflicted$class.plus(this, x, y);
/*     */     }
/*     */     
/*     */     public BigDecimal minus(BigDecimal x, BigDecimal y) {
/* 182 */       return Numeric.BigDecimalIsConflicted$class.minus(this, x, y);
/*     */     }
/*     */     
/*     */     public BigDecimal times(BigDecimal x, BigDecimal y) {
/* 182 */       return Numeric.BigDecimalIsConflicted$class.times(this, x, y);
/*     */     }
/*     */     
/*     */     public BigDecimal negate(BigDecimal x) {
/* 182 */       return Numeric.BigDecimalIsConflicted$class.negate(this, x);
/*     */     }
/*     */     
/*     */     public BigDecimal fromInt(int x) {
/* 182 */       return Numeric.BigDecimalIsConflicted$class.fromInt(this, x);
/*     */     }
/*     */     
/*     */     public int toInt(BigDecimal x) {
/* 182 */       return Numeric.BigDecimalIsConflicted$class.toInt(this, x);
/*     */     }
/*     */     
/*     */     public long toLong(BigDecimal x) {
/* 182 */       return Numeric.BigDecimalIsConflicted$class.toLong(this, x);
/*     */     }
/*     */     
/*     */     public float toFloat(BigDecimal x) {
/* 182 */       return Numeric.BigDecimalIsConflicted$class.toFloat(this, x);
/*     */     }
/*     */     
/*     */     public double toDouble(BigDecimal x) {
/* 182 */       return Numeric.BigDecimalIsConflicted$class.toDouble(this, x);
/*     */     }
/*     */     
/*     */     public Object zero() {
/* 182 */       return Numeric$class.zero(this);
/*     */     }
/*     */     
/*     */     public Object one() {
/* 182 */       return Numeric$class.one(this);
/*     */     }
/*     */     
/*     */     public Object abs(Object x) {
/* 182 */       return Numeric$class.abs(this, x);
/*     */     }
/*     */     
/*     */     public int signum(Object x) {
/* 182 */       return Numeric$class.signum(this, x);
/*     */     }
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 182 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 182 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 182 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 182 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 182 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 182 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Object max(Object x, Object y) {
/* 182 */       return Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Object min(Object x, Object y) {
/* 182 */       return Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<BigDecimal> reverse() {
/* 182 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 182 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<BigDecimal>.Ops mkOrderingOps(Object lhs) {
/* 182 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 182 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public BigDecimalIsFractional$() {
/* 182 */       MODULE$ = this;
/* 182 */       PartialOrdering$class.$init$(this);
/* 182 */       Ordering$class.$init$(this);
/* 182 */       Numeric$class.$init$(this);
/* 182 */       Numeric.BigDecimalIsConflicted$class.$init$(this);
/* 182 */       Fractional$class.$init$(this);
/* 182 */       Numeric.BigDecimalIsFractional$class.$init$(this);
/* 182 */       Ordering.BigDecimalOrdering$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class BigDecimalAsIfIntegral$ implements BigDecimalAsIfIntegral, Ordering.BigDecimalOrdering {
/*     */     public static final BigDecimalAsIfIntegral$ MODULE$;
/*     */     
/*     */     public int compare(BigDecimal x, BigDecimal y) {
/* 183 */       return Ordering.BigDecimalOrdering$class.compare(this, x, y);
/*     */     }
/*     */     
/*     */     public BigDecimal quot(BigDecimal x, BigDecimal y) {
/* 183 */       return Numeric.BigDecimalAsIfIntegral$class.quot(this, x, y);
/*     */     }
/*     */     
/*     */     public BigDecimal rem(BigDecimal x, BigDecimal y) {
/* 183 */       return Numeric.BigDecimalAsIfIntegral$class.rem(this, x, y);
/*     */     }
/*     */     
/*     */     public Integral<BigDecimal>.IntegralOps mkNumericOps(Object lhs) {
/* 183 */       return Integral$class.mkNumericOps(this, lhs);
/*     */     }
/*     */     
/*     */     public BigDecimal plus(BigDecimal x, BigDecimal y) {
/* 183 */       return Numeric.BigDecimalIsConflicted$class.plus(this, x, y);
/*     */     }
/*     */     
/*     */     public BigDecimal minus(BigDecimal x, BigDecimal y) {
/* 183 */       return Numeric.BigDecimalIsConflicted$class.minus(this, x, y);
/*     */     }
/*     */     
/*     */     public BigDecimal times(BigDecimal x, BigDecimal y) {
/* 183 */       return Numeric.BigDecimalIsConflicted$class.times(this, x, y);
/*     */     }
/*     */     
/*     */     public BigDecimal negate(BigDecimal x) {
/* 183 */       return Numeric.BigDecimalIsConflicted$class.negate(this, x);
/*     */     }
/*     */     
/*     */     public BigDecimal fromInt(int x) {
/* 183 */       return Numeric.BigDecimalIsConflicted$class.fromInt(this, x);
/*     */     }
/*     */     
/*     */     public int toInt(BigDecimal x) {
/* 183 */       return Numeric.BigDecimalIsConflicted$class.toInt(this, x);
/*     */     }
/*     */     
/*     */     public long toLong(BigDecimal x) {
/* 183 */       return Numeric.BigDecimalIsConflicted$class.toLong(this, x);
/*     */     }
/*     */     
/*     */     public float toFloat(BigDecimal x) {
/* 183 */       return Numeric.BigDecimalIsConflicted$class.toFloat(this, x);
/*     */     }
/*     */     
/*     */     public double toDouble(BigDecimal x) {
/* 183 */       return Numeric.BigDecimalIsConflicted$class.toDouble(this, x);
/*     */     }
/*     */     
/*     */     public Object zero() {
/* 183 */       return Numeric$class.zero(this);
/*     */     }
/*     */     
/*     */     public Object one() {
/* 183 */       return Numeric$class.one(this);
/*     */     }
/*     */     
/*     */     public Object abs(Object x) {
/* 183 */       return Numeric$class.abs(this, x);
/*     */     }
/*     */     
/*     */     public int signum(Object x) {
/* 183 */       return Numeric$class.signum(this, x);
/*     */     }
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 183 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 183 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 183 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 183 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 183 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 183 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public Object max(Object x, Object y) {
/* 183 */       return Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public Object min(Object x, Object y) {
/* 183 */       return Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<BigDecimal> reverse() {
/* 183 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 183 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<BigDecimal>.Ops mkOrderingOps(Object lhs) {
/* 183 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 183 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public BigDecimalAsIfIntegral$() {
/* 183 */       MODULE$ = this;
/* 183 */       PartialOrdering$class.$init$(this);
/* 183 */       Ordering$class.$init$(this);
/* 183 */       Numeric$class.$init$(this);
/* 183 */       Numeric.BigDecimalIsConflicted$class.$init$(this);
/* 183 */       Integral$class.$init$(this);
/* 183 */       Numeric.BigDecimalAsIfIntegral$class.$init$(this);
/* 183 */       Ordering.BigDecimalOrdering$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class DoubleIsFractional$ implements DoubleIsFractional, Ordering.DoubleOrdering {
/*     */     public static final DoubleIsFractional$ MODULE$;
/*     */     
/*     */     public int compare(double x, double y) {
/* 185 */       return Ordering.DoubleOrdering$class.compare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(double x, double y) {
/* 185 */       return Ordering.DoubleOrdering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(double x, double y) {
/* 185 */       return Ordering.DoubleOrdering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(double x, double y) {
/* 185 */       return Ordering.DoubleOrdering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(double x, double y) {
/* 185 */       return Ordering.DoubleOrdering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(double x, double y) {
/* 185 */       return Ordering.DoubleOrdering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public double max(double x, double y) {
/* 185 */       return Ordering.DoubleOrdering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public double min(double x, double y) {
/* 185 */       return Ordering.DoubleOrdering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Object> reverse() {
/* 185 */       return Ordering.DoubleOrdering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public double div(double x, double y) {
/* 185 */       return Numeric.DoubleIsFractional$class.div(this, x, y);
/*     */     }
/*     */     
/*     */     public Fractional<Object>.FractionalOps mkNumericOps(Object lhs) {
/* 185 */       return Fractional$class.mkNumericOps(this, lhs);
/*     */     }
/*     */     
/*     */     public double plus(double x, double y) {
/* 185 */       return Numeric.DoubleIsConflicted$class.plus(this, x, y);
/*     */     }
/*     */     
/*     */     public double minus(double x, double y) {
/* 185 */       return Numeric.DoubleIsConflicted$class.minus(this, x, y);
/*     */     }
/*     */     
/*     */     public double times(double x, double y) {
/* 185 */       return Numeric.DoubleIsConflicted$class.times(this, x, y);
/*     */     }
/*     */     
/*     */     public double negate(double x) {
/* 185 */       return Numeric.DoubleIsConflicted$class.negate(this, x);
/*     */     }
/*     */     
/*     */     public double fromInt(int x) {
/* 185 */       return Numeric.DoubleIsConflicted$class.fromInt(this, x);
/*     */     }
/*     */     
/*     */     public int toInt(double x) {
/* 185 */       return Numeric.DoubleIsConflicted$class.toInt(this, x);
/*     */     }
/*     */     
/*     */     public long toLong(double x) {
/* 185 */       return Numeric.DoubleIsConflicted$class.toLong(this, x);
/*     */     }
/*     */     
/*     */     public float toFloat(double x) {
/* 185 */       return Numeric.DoubleIsConflicted$class.toFloat(this, x);
/*     */     }
/*     */     
/*     */     public double toDouble(double x) {
/* 185 */       return Numeric.DoubleIsConflicted$class.toDouble(this, x);
/*     */     }
/*     */     
/*     */     public Object zero() {
/* 185 */       return Numeric$class.zero(this);
/*     */     }
/*     */     
/*     */     public Object one() {
/* 185 */       return Numeric$class.one(this);
/*     */     }
/*     */     
/*     */     public Object abs(Object x) {
/* 185 */       return Numeric$class.abs(this, x);
/*     */     }
/*     */     
/*     */     public int signum(Object x) {
/* 185 */       return Numeric$class.signum(this, x);
/*     */     }
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 185 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 185 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Object>.Ops mkOrderingOps(Object lhs) {
/* 185 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 185 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public DoubleIsFractional$() {
/* 185 */       MODULE$ = this;
/* 185 */       PartialOrdering$class.$init$(this);
/* 185 */       Ordering$class.$init$(this);
/* 185 */       Numeric$class.$init$(this);
/* 185 */       Numeric.DoubleIsConflicted$class.$init$(this);
/* 185 */       Fractional$class.$init$(this);
/* 185 */       Numeric.DoubleIsFractional$class.$init$(this);
/* 185 */       Ordering.DoubleOrdering$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class DoubleAsIfIntegral$ implements DoubleAsIfIntegral, Ordering.DoubleOrdering {
/*     */     public static final DoubleAsIfIntegral$ MODULE$;
/*     */     
/*     */     public int compare(double x, double y) {
/* 186 */       return Ordering.DoubleOrdering$class.compare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(double x, double y) {
/* 186 */       return Ordering.DoubleOrdering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(double x, double y) {
/* 186 */       return Ordering.DoubleOrdering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(double x, double y) {
/* 186 */       return Ordering.DoubleOrdering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(double x, double y) {
/* 186 */       return Ordering.DoubleOrdering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(double x, double y) {
/* 186 */       return Ordering.DoubleOrdering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public double max(double x, double y) {
/* 186 */       return Ordering.DoubleOrdering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public double min(double x, double y) {
/* 186 */       return Ordering.DoubleOrdering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<Object> reverse() {
/* 186 */       return Ordering.DoubleOrdering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public double quot(double x, double y) {
/* 186 */       return Numeric.DoubleAsIfIntegral$class.quot(this, x, y);
/*     */     }
/*     */     
/*     */     public double rem(double x, double y) {
/* 186 */       return Numeric.DoubleAsIfIntegral$class.rem(this, x, y);
/*     */     }
/*     */     
/*     */     public Integral<Object>.IntegralOps mkNumericOps(Object lhs) {
/* 186 */       return Integral$class.mkNumericOps(this, lhs);
/*     */     }
/*     */     
/*     */     public double plus(double x, double y) {
/* 186 */       return Numeric.DoubleIsConflicted$class.plus(this, x, y);
/*     */     }
/*     */     
/*     */     public double minus(double x, double y) {
/* 186 */       return Numeric.DoubleIsConflicted$class.minus(this, x, y);
/*     */     }
/*     */     
/*     */     public double times(double x, double y) {
/* 186 */       return Numeric.DoubleIsConflicted$class.times(this, x, y);
/*     */     }
/*     */     
/*     */     public double negate(double x) {
/* 186 */       return Numeric.DoubleIsConflicted$class.negate(this, x);
/*     */     }
/*     */     
/*     */     public double fromInt(int x) {
/* 186 */       return Numeric.DoubleIsConflicted$class.fromInt(this, x);
/*     */     }
/*     */     
/*     */     public int toInt(double x) {
/* 186 */       return Numeric.DoubleIsConflicted$class.toInt(this, x);
/*     */     }
/*     */     
/*     */     public long toLong(double x) {
/* 186 */       return Numeric.DoubleIsConflicted$class.toLong(this, x);
/*     */     }
/*     */     
/*     */     public float toFloat(double x) {
/* 186 */       return Numeric.DoubleIsConflicted$class.toFloat(this, x);
/*     */     }
/*     */     
/*     */     public double toDouble(double x) {
/* 186 */       return Numeric.DoubleIsConflicted$class.toDouble(this, x);
/*     */     }
/*     */     
/*     */     public Object zero() {
/* 186 */       return Numeric$class.zero(this);
/*     */     }
/*     */     
/*     */     public Object one() {
/* 186 */       return Numeric$class.one(this);
/*     */     }
/*     */     
/*     */     public Object abs(Object x) {
/* 186 */       return Numeric$class.abs(this, x);
/*     */     }
/*     */     
/*     */     public int signum(Object x) {
/* 186 */       return Numeric$class.signum(this, x);
/*     */     }
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 186 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 186 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<Object>.Ops mkOrderingOps(Object lhs) {
/* 186 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 186 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public DoubleAsIfIntegral$() {
/* 186 */       MODULE$ = this;
/* 186 */       PartialOrdering$class.$init$(this);
/* 186 */       Ordering$class.$init$(this);
/* 186 */       Numeric$class.$init$(this);
/* 186 */       Numeric.DoubleIsConflicted$class.$init$(this);
/* 186 */       Integral$class.$init$(this);
/* 186 */       Numeric.DoubleAsIfIntegral$class.$init$(this);
/* 186 */       Ordering.DoubleOrdering$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class Ops {
/*     */     private final T lhs;
/*     */     
/*     */     public Ops(Numeric $outer, Object lhs) {}
/*     */     
/*     */     public T $plus(Object rhs) {
/* 210 */       return scala$math$Numeric$Ops$$$outer().plus(this.lhs, (T)rhs);
/*     */     }
/*     */     
/*     */     public T $minus(Object rhs) {
/* 211 */       return scala$math$Numeric$Ops$$$outer().minus(this.lhs, (T)rhs);
/*     */     }
/*     */     
/*     */     public T $times(Object rhs) {
/* 212 */       return scala$math$Numeric$Ops$$$outer().times(this.lhs, (T)rhs);
/*     */     }
/*     */     
/*     */     public T unary_$minus() {
/* 213 */       return scala$math$Numeric$Ops$$$outer().negate(this.lhs);
/*     */     }
/*     */     
/*     */     public T abs() {
/* 214 */       return scala$math$Numeric$Ops$$$outer().abs(this.lhs);
/*     */     }
/*     */     
/*     */     public int signum() {
/* 215 */       return scala$math$Numeric$Ops$$$outer().signum(this.lhs);
/*     */     }
/*     */     
/*     */     public int toInt() {
/* 216 */       return scala$math$Numeric$Ops$$$outer().toInt(this.lhs);
/*     */     }
/*     */     
/*     */     public long toLong() {
/* 217 */       return scala$math$Numeric$Ops$$$outer().toLong(this.lhs);
/*     */     }
/*     */     
/*     */     public float toFloat() {
/* 218 */       return scala$math$Numeric$Ops$$$outer().toFloat(this.lhs);
/*     */     }
/*     */     
/*     */     public double toDouble() {
/* 219 */       return scala$math$Numeric$Ops$$$outer().toDouble(this.lhs);
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface IntIsIntegral extends Integral<Object> {
/*     */     int plus(int param1Int1, int param1Int2);
/*     */     
/*     */     int minus(int param1Int1, int param1Int2);
/*     */     
/*     */     int times(int param1Int1, int param1Int2);
/*     */     
/*     */     int quot(int param1Int1, int param1Int2);
/*     */     
/*     */     int rem(int param1Int1, int param1Int2);
/*     */     
/*     */     int negate(int param1Int);
/*     */     
/*     */     int fromInt(int param1Int);
/*     */     
/*     */     int toInt(int param1Int);
/*     */     
/*     */     long toLong(int param1Int);
/*     */     
/*     */     float toFloat(int param1Int);
/*     */     
/*     */     double toDouble(int param1Int);
/*     */   }
/*     */   
/*     */   public static interface ExtraImplicits {
/*     */     <T> Numeric<T>.Ops infixNumericOps(T param1T, Numeric<T> param1Numeric);
/*     */   }
/*     */   
/*     */   public static interface ByteIsIntegral extends Integral<Object> {
/*     */     byte plus(byte param1Byte1, byte param1Byte2);
/*     */     
/*     */     byte minus(byte param1Byte1, byte param1Byte2);
/*     */     
/*     */     byte times(byte param1Byte1, byte param1Byte2);
/*     */     
/*     */     byte quot(byte param1Byte1, byte param1Byte2);
/*     */     
/*     */     byte rem(byte param1Byte1, byte param1Byte2);
/*     */     
/*     */     byte negate(byte param1Byte);
/*     */     
/*     */     byte fromInt(int param1Int);
/*     */     
/*     */     int toInt(byte param1Byte);
/*     */     
/*     */     long toLong(byte param1Byte);
/*     */     
/*     */     float toFloat(byte param1Byte);
/*     */     
/*     */     double toDouble(byte param1Byte);
/*     */   }
/*     */   
/*     */   public static interface CharIsIntegral extends Integral<Object> {
/*     */     char plus(char param1Char1, char param1Char2);
/*     */     
/*     */     char minus(char param1Char1, char param1Char2);
/*     */     
/*     */     char times(char param1Char1, char param1Char2);
/*     */     
/*     */     char quot(char param1Char1, char param1Char2);
/*     */     
/*     */     char rem(char param1Char1, char param1Char2);
/*     */     
/*     */     char negate(char param1Char);
/*     */     
/*     */     char fromInt(int param1Int);
/*     */     
/*     */     int toInt(char param1Char);
/*     */     
/*     */     long toLong(char param1Char);
/*     */     
/*     */     float toFloat(char param1Char);
/*     */     
/*     */     double toDouble(char param1Char);
/*     */   }
/*     */   
/*     */   public static interface LongIsIntegral extends Integral<Object> {
/*     */     long plus(long param1Long1, long param1Long2);
/*     */     
/*     */     long minus(long param1Long1, long param1Long2);
/*     */     
/*     */     long times(long param1Long1, long param1Long2);
/*     */     
/*     */     long quot(long param1Long1, long param1Long2);
/*     */     
/*     */     long rem(long param1Long1, long param1Long2);
/*     */     
/*     */     long negate(long param1Long);
/*     */     
/*     */     long fromInt(int param1Int);
/*     */     
/*     */     int toInt(long param1Long);
/*     */     
/*     */     long toLong(long param1Long);
/*     */     
/*     */     float toFloat(long param1Long);
/*     */     
/*     */     double toDouble(long param1Long);
/*     */   }
/*     */   
/*     */   public static interface ShortIsIntegral extends Integral<Object> {
/*     */     short plus(short param1Short1, short param1Short2);
/*     */     
/*     */     short minus(short param1Short1, short param1Short2);
/*     */     
/*     */     short times(short param1Short1, short param1Short2);
/*     */     
/*     */     short quot(short param1Short1, short param1Short2);
/*     */     
/*     */     short rem(short param1Short1, short param1Short2);
/*     */     
/*     */     short negate(short param1Short);
/*     */     
/*     */     short fromInt(int param1Int);
/*     */     
/*     */     int toInt(short param1Short);
/*     */     
/*     */     long toLong(short param1Short);
/*     */     
/*     */     float toFloat(short param1Short);
/*     */     
/*     */     double toDouble(short param1Short);
/*     */   }
/*     */   
/*     */   public static interface BigIntIsIntegral extends Integral<BigInt> {
/*     */     BigInt plus(BigInt param1BigInt1, BigInt param1BigInt2);
/*     */     
/*     */     BigInt minus(BigInt param1BigInt1, BigInt param1BigInt2);
/*     */     
/*     */     BigInt times(BigInt param1BigInt1, BigInt param1BigInt2);
/*     */     
/*     */     BigInt quot(BigInt param1BigInt1, BigInt param1BigInt2);
/*     */     
/*     */     BigInt rem(BigInt param1BigInt1, BigInt param1BigInt2);
/*     */     
/*     */     BigInt negate(BigInt param1BigInt);
/*     */     
/*     */     BigInt fromInt(int param1Int);
/*     */     
/*     */     int toInt(BigInt param1BigInt);
/*     */     
/*     */     long toLong(BigInt param1BigInt);
/*     */     
/*     */     float toFloat(BigInt param1BigInt);
/*     */     
/*     */     double toDouble(BigInt param1BigInt);
/*     */   }
/*     */   
/*     */   public static interface FloatIsConflicted extends Numeric<Object> {
/*     */     float plus(float param1Float1, float param1Float2);
/*     */     
/*     */     float minus(float param1Float1, float param1Float2);
/*     */     
/*     */     float times(float param1Float1, float param1Float2);
/*     */     
/*     */     float negate(float param1Float);
/*     */     
/*     */     float fromInt(int param1Int);
/*     */     
/*     */     int toInt(float param1Float);
/*     */     
/*     */     long toLong(float param1Float);
/*     */     
/*     */     float toFloat(float param1Float);
/*     */     
/*     */     double toDouble(float param1Float);
/*     */   }
/*     */   
/*     */   public static interface FloatIsFractional extends FloatIsConflicted, Fractional<Object> {
/*     */     float div(float param1Float1, float param1Float2);
/*     */   }
/*     */   
/*     */   public static interface FloatAsIfIntegral extends FloatIsConflicted, Integral<Object> {
/*     */     float quot(float param1Float1, float param1Float2);
/*     */     
/*     */     float rem(float param1Float1, float param1Float2);
/*     */   }
/*     */   
/*     */   public static interface DoubleIsConflicted extends Numeric<Object> {
/*     */     double plus(double param1Double1, double param1Double2);
/*     */     
/*     */     double minus(double param1Double1, double param1Double2);
/*     */     
/*     */     double times(double param1Double1, double param1Double2);
/*     */     
/*     */     double negate(double param1Double);
/*     */     
/*     */     double fromInt(int param1Int);
/*     */     
/*     */     int toInt(double param1Double);
/*     */     
/*     */     long toLong(double param1Double);
/*     */     
/*     */     float toFloat(double param1Double);
/*     */     
/*     */     double toDouble(double param1Double);
/*     */   }
/*     */   
/*     */   public static interface DoubleIsFractional extends DoubleIsConflicted, Fractional<Object> {
/*     */     double div(double param1Double1, double param1Double2);
/*     */   }
/*     */   
/*     */   public static interface DoubleAsIfIntegral extends DoubleIsConflicted, Integral<Object> {
/*     */     double quot(double param1Double1, double param1Double2);
/*     */     
/*     */     double rem(double param1Double1, double param1Double2);
/*     */   }
/*     */   
/*     */   public static interface BigDecimalIsConflicted extends Numeric<BigDecimal> {
/*     */     BigDecimal plus(BigDecimal param1BigDecimal1, BigDecimal param1BigDecimal2);
/*     */     
/*     */     BigDecimal minus(BigDecimal param1BigDecimal1, BigDecimal param1BigDecimal2);
/*     */     
/*     */     BigDecimal times(BigDecimal param1BigDecimal1, BigDecimal param1BigDecimal2);
/*     */     
/*     */     BigDecimal negate(BigDecimal param1BigDecimal);
/*     */     
/*     */     BigDecimal fromInt(int param1Int);
/*     */     
/*     */     int toInt(BigDecimal param1BigDecimal);
/*     */     
/*     */     long toLong(BigDecimal param1BigDecimal);
/*     */     
/*     */     float toFloat(BigDecimal param1BigDecimal);
/*     */     
/*     */     double toDouble(BigDecimal param1BigDecimal);
/*     */   }
/*     */   
/*     */   public static interface BigDecimalIsFractional extends BigDecimalIsConflicted, Fractional<BigDecimal> {
/*     */     BigDecimal div(BigDecimal param1BigDecimal1, BigDecimal param1BigDecimal2);
/*     */   }
/*     */   
/*     */   public static interface BigDecimalAsIfIntegral extends BigDecimalIsConflicted, Integral<BigDecimal> {
/*     */     BigDecimal quot(BigDecimal param1BigDecimal1, BigDecimal param1BigDecimal2);
/*     */     
/*     */     BigDecimal rem(BigDecimal param1BigDecimal1, BigDecimal param1BigDecimal2);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\Numeric.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */