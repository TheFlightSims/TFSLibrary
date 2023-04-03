package scala.collection;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import scala.collection.concurrent.Map;
import scala.collection.convert.Wrappers$DictionaryWrapper$;
import scala.collection.convert.Wrappers$IterableWrapper$;
import scala.collection.convert.Wrappers$IteratorWrapper$;
import scala.collection.convert.Wrappers$JCollectionWrapper$;
import scala.collection.convert.Wrappers$JConcurrentMapWrapper$;
import scala.collection.convert.Wrappers$JDictionaryWrapper$;
import scala.collection.convert.Wrappers$JEnumerationWrapper$;
import scala.collection.convert.Wrappers$JIterableWrapper$;
import scala.collection.convert.Wrappers$JIteratorWrapper$;
import scala.collection.convert.Wrappers$JListWrapper$;
import scala.collection.convert.Wrappers$JMapWrapper$;
import scala.collection.convert.Wrappers$JPropertiesWrapper$;
import scala.collection.convert.Wrappers$JSetWrapper$;
import scala.collection.convert.Wrappers$MutableBufferWrapper$;
import scala.collection.convert.Wrappers$MutableMapWrapper$;
import scala.collection.convert.Wrappers$MutableSeqWrapper$;
import scala.collection.convert.Wrappers$MutableSetWrapper$;
import scala.collection.convert.Wrappers$SeqWrapper$;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.ConcurrentMap;
import scala.collection.mutable.Map;
import scala.collection.mutable.Seq;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\021ms!B\001\003\021\0039\021a\004&bm\006\034uN\034<feNLwN\\:\013\005\r!\021AC2pY2,7\r^5p]*\tQ!A\003tG\006d\027m\001\001\021\005!IQ\"\001\002\007\013)\021\001\022A\006\003\037)\013g/Y\"p]Z,'o]5p]N\034B!\003\007\021-A\021QBD\007\002\t%\021q\002\002\002\007\003:L(+\0324\021\005E!R\"\001\n\013\005M\021\021aB2p]Z,'\017^\005\003+I\0211b\026:ba\006\0338kY1mCB\021\021cF\005\0031I\021!b\026:ba\006\033(*\031<b\021\025Q\022\002\"\001\034\003\031a\024N\\5u}Q\tq!\002\003\036\023\001q\"\001F\"p]\016,(O]3oi6\013\007o\026:baB,'/F\002 QI\002B\001I\022'c9\021\021#I\005\003EI\t\001b\026:baB,'o]\005\003;\021J!!\n\n\003\021]\023\030\r\0359feN\004\"a\n\025\r\001\021)\021\006\bb\001U\t\t\021)\005\002,]A\021Q\002L\005\003[\021\021qAT8uQ&tw\r\005\002\016_%\021\001\007\002\002\004\003:L\bCA\0243\t\025\031DD1\001+\005\005\021\005\006\002\0176qi\002\"!\004\034\n\005]\"!A\0033faJ,7-\031;fI\006\n\021(A\031Vg\026\004\023\rI7f[\n,'\017I8gAM\034\027\r\\1/G>dG.Z2uS>tgfY8om\026\024HOL,sCB\004XM]:\"\003m\naA\r\0302a9\002T\001B\037\n\001y\022\021\003R5di&|g.\031:z/J\f\007\017]3s+\ry$\t\022\t\005A\001\0135)\003\002>IA\021qE\021\003\006Sq\022\rA\013\t\003O\021#Qa\r\037C\002)BC\001P\0339u\025!q)\003\001I\005=IE/\032:bE2,wK]1qa\026\024XCA%M!\r\001#jS\005\003\017\022\002\"a\n'\005\013%2%\031\001\026)\t\031+\004HO\003\005\037&\001\001KA\bJi\026\024\030\r^8s/J\f\007\017]3s+\t\tF\013E\002!%NK!a\024\023\021\005\035\"F!B\025O\005\004Q\003\006\002(6qi*AaV\005\0011\n\021\"jQ8mY\026\034G/[8o/J\f\007\017]3s+\tIF\fE\002!5nK!a\026\023\021\005\035bF!B\025W\005\004Q\003\006\002,6qi*AaX\005\001A\n)\"jQ8oGV\024(/\0328u\033\006\004xK]1qa\026\024XcA1eMB!\001EY2f\023\tyF\005\005\002(I\022)\021F\030b\001UA\021qE\032\003\006gy\023\rA\013\025\005=VB$(\002\003j\023\001Q'A\005&ES\016$\030n\0348bef<&/\0319qKJ,2a\0338q!\021\001C.\\8\n\005%$\003CA\024o\t\025I\003N1\001+!\t9\003\017B\0034Q\n\007!\006\013\003ikaRT\001B:\n\001Q\0241CS#ok6,'/\031;j_:<&/\0319qKJ,\"!\036=\021\007\0012x/\003\002tIA\021q\005\037\003\006SI\024\rA\013\025\005eVB$(\002\003|\023\001a(\001\005&Ji\026\024\030M\0317f/J\f\007\017]3s+\ri\030\021\001\t\004Ay|\030BA>%!\r9\023\021\001\003\006Si\024\rA\013\025\005uVB$(\002\004\002\b%\001\021\021\002\002\021\025&#XM]1u_J<&/\0319qKJ,B!a\003\002\022A)\001%!\004\002\020%\031\021q\001\023\021\007\035\n\t\002\002\004*\003\013\021\rA\013\025\006\003\013)\004HO\003\007\003/I\001!!\007\003\031)c\025n\035;Xe\006\004\b/\032:\026\t\005m\021\021\005\t\006A\005u\021qD\005\004\003/!\003cA\024\002\"\0211\021&!\006C\002)BS!!\0066qi*a!a\n\n\001\005%\"a\003&NCB<&/\0319qKJ,b!a\013\0022\005U\002c\002\021\002.\005=\0221G\005\004\003O!\003cA\024\0022\0211\021&!\nC\002)\0022aJA\033\t\031\031\024Q\005b\001U!*\021QE\0339u\0251\0211H\005\001\003{\021!C\023)s_B,'\017^5fg^\023\030\r\0359feB\031\001%a\020\n\007\005mB\005K\003\002:UB$(\002\004\002F%\001\021q\t\002\f\025N+Go\026:baB,'/\006\003\002J\005=\003#\002\021\002L\0055\023bAA#IA\031q%a\024\005\r%\n\031E1\001+Q\025\t\031%\016\035;\013\031\t)&\003\001\002X\tQQ*\0319Xe\006\004\b/\032:\026\r\005e\023qLA2!\035\001\0231LA/\003CJ1!!\026%!\r9\023q\f\003\007S\005M#\031\001\026\021\007\035\n\031\007\002\0044\003'\022\rA\013\025\006\003'*\004HO\003\007\003SJ\001!a\033\003)5+H/\0312mK\n+hMZ3s/J\f\007\017]3s+\021\ti'a\035\021\013\001\ny'!\035\n\007\005%D\005E\002(\003g\"a!KA4\005\004Q\003&BA4kaRTABA=\023\001\tYHA\tNkR\f'\r\\3NCB<&/\0319qKJ,b!! \002\004\006\035\005c\002\021\002\000\005\005\025QQ\005\004\003s\"\003cA\024\002\004\0221\021&a\036C\002)\0022aJAD\t\031\031\024q\017b\001U!*\021qO\0339u\0251\021QR\005\001\003\037\023\021#T;uC\ndWmU3r/J\f\007\017]3s+\021\t\t*a&\021\013\001\n\031*!&\n\007\0055E\005E\002(\003/#a!KAF\005\004Q\003&BAFkaRTABAO\023\001\tyJA\tNkR\f'\r\\3TKR<&/\0319qKJ,B!!)\002(B)\001%a)\002&&\031\021Q\024\023\021\007\035\n9\013\002\004*\0037\023\rA\013\025\006\0037+\004HO\003\007\003[K\001!a,\003\025M+\027o\026:baB,'/\006\003\0022\006]\006#\002\021\0024\006U\026bAAWIA\031q%a.\005\r%\nYK1\001+Q\025\tY+\016\035;\013\031\ti,\003\001\002@\nQ1+\032;Xe\006\004\b/\032:\026\t\005\005\027q\031\t\006A\005\r\027QY\005\004\003{#\003cA\024\002H\0221\021&a/C\002)BS!a/6qi*a!!4\n\001\005='!\005+p\023R,'/\031;pe^\023\030\r\0359feV!\021\021[Al!\025\001\0231[Ak\023\r\ti\r\n\t\004O\005]GAB\025\002L\n\007!\006K\003\002LVB$\bC\005\002^&\021\r\021\"\001\002`\006\tB)[2uS>t\027M]=Xe\006\004\b/\032:\026\005\005\005hb\001\021\002d&\031\021Q\034\023)\013\005mW\007\017\036\t\021\005%\030\002)A\005\003C\f!\003R5di&|g.\031:z/J\f\007\017]3sA!I\021Q^\005C\002\023\005\021q^\001\020\023R,'/\0312mK^\023\030\r\0359feV\021\021\021\037\b\004A\005M\030bAAwI!*\0211^\0339u!A\021\021`\005!\002\023\t\t0\001\tJi\026\024\030M\0317f/J\f\007\017]3sA!I\021Q`\005C\002\023\005\021q`\001\020\023R,'/\031;pe^\023\030\r\0359feV\021!\021\001\b\004A\t\r\021bAAI!*\0211`\0339u!A!\021B\005!\002\023\021\t!\001\tJi\026\024\030\r^8s/J\f\007\017]3sA!I!QB\005C\002\023\005!qB\001\023\025\016{G\016\\3di&|gn\026:baB,'/\006\002\003\0229\031\001Ea\005\n\007\t5A\005K\003\003\fUB$\b\003\005\003\032%\001\013\021\002B\t\003MQ5i\0347mK\016$\030n\0348Xe\006\004\b/\032:!\021%\021i\"\003b\001\n\003\021y\"A\013K\007>t7-\036:sK:$X*\0319Xe\006\004\b/\032:\026\005\t\005bb\001\021\003$%\031!Q\004\023)\013\tmQ\007\017\036\t\021\t%\022\002)A\005\005C\taCS\"p]\016,(O]3oi6\013\007o\026:baB,'\017\t\005\n\005[I!\031!C\001\005_\t!C\023#jGRLwN\\1ss^\023\030\r\0359feV\021!\021\007\b\004A\tM\022b\001B\027I!*!1F\0339u!A!\021H\005!\002\023\021\t$A\nK\t&\034G/[8oCJLxK]1qa\026\024\b\005C\005\003>%\021\r\021\"\001\003@\005\031\"*\0228v[\026\024\030\r^5p]^\023\030\r\0359feV\021!\021\t\b\004A\t\r\023b\001B\037I!*!1H\0339u!A!\021J\005!\002\023\021\t%\001\013K\013:,X.\032:bi&|gn\026:baB,'\017\t\005\n\005\033J!\031!C\001\005\037\n\001CS%uKJ\f'\r\\3Xe\006\004\b/\032:\026\005\tEcb\001\021\003T%\031!Q\n\023)\013\t-S\007\017\036\t\021\te\023\002)A\005\005#\n\021CS%uKJ\f'\r\\3Xe\006\004\b/\032:!\021%\021i&\003b\001\n\003\021y&\001\tK\023R,'/\031;pe^\023\030\r\0359feV\021!\021\r\b\004A\t\r\024b\001B/I!*!1L\0339u!A!\021N\005!\002\023\021\t'A\tK\023R,'/\031;pe^\023\030\r\0359fe\002B\021B!\034\n\005\004%\tAa\034\002\031)c\025n\035;Xe\006\004\b/\032:\026\005\tEdb\001\021\003t%\031!Q\016\023)\013\t-T\007\017\036\t\021\te\024\002)A\005\005c\nQB\023'jgR<&/\0319qKJ\004\003\"\003B?\023\t\007I\021\001B@\003-QU*\0319Xe\006\004\b/\032:\026\005\t\005eb\001\021\003\004&\031!Q\020\023)\013\tmT\007\017\036\t\021\t%\025\002)A\005\005\003\013ABS'ba^\023\030\r\0359fe\002B\021B!$\n\005\004%\tAa$\002%)\003&o\0349feRLWm],sCB\004XM]\013\003\005#s1\001\tBJ\023\r\021i\t\n\025\006\005\027+\004H\017\005\t\0053K\001\025!\003\003\022\006\031\"\n\025:pa\026\024H/[3t/J\f\007\017]3sA!I!QT\005C\002\023\005!qT\001\f\025N+Go\026:baB,'/\006\002\003\":\031\001Ea)\n\007\tuE\005K\003\003\034VB$\b\003\005\003*&\001\013\021\002BQ\0031Q5+\032;Xe\006\004\b/\032:!\021%\021i+\003b\001\n\003\021y+\001\013NkR\f'\r\\3Ck\0324WM],sCB\004XM]\013\003\005cs1\001\tBZ\023\r\021i\013\n\025\006\005W+\004H\017\005\t\005sK\001\025!\003\0032\006)R*\036;bE2,')\0364gKJ<&/\0319qKJ\004\003\"\003B_\023\t\007I\021\001B`\003EiU\017^1cY\026l\025\r],sCB\004XM]\013\003\005\003t1\001\tBb\023\r\021i\f\n\025\006\005w+\004H\017\005\t\005\023L\001\025!\003\003B\006\021R*\036;bE2,W*\0319Xe\006\004\b/\032:!\021%\021i-\003b\001\n\003\021y-A\tNkR\f'\r\\3TKF<&/\0319qKJ,\"A!5\017\007\001\022\031.C\002\003N\022BSAa36qiB\001B!7\nA\003%!\021[\001\023\033V$\030M\0317f'\026\fxK]1qa\026\024\b\005C\005\003^&\021\r\021\"\001\003`\006\tR*\036;bE2,7+\032;Xe\006\004\b/\032:\026\005\t\005hb\001\021\003d&\031!Q\034\023)\013\tmW\007\017\036\t\021\t%\030\002)A\005\005C\f!#T;uC\ndWmU3u/J\f\007\017]3sA!I!Q^\005C\002\023\005!q^\001\013'\026\fxK]1qa\026\024XC\001By\035\r\001#1_\005\004\005[$\003&\002BvkaR\004\002\003B}\023\001\006IA!=\002\027M+\027o\026:baB,'\017\t\005\b\005{LA\021\001B\000\003)\t7OS1wC2K7\017^\013\005\007\003\031)\002\006\003\004\004\r]\001CBB\003\007\037\031\031\"\004\002\004\b)!1\021BB\006\003\021)H/\0337\013\005\r5\021\001\0026bm\006LAa!\005\004\b\t!A*[:u!\r93Q\003\003\007S\tm(\031\001\026\t\021\re!1 a\001\0077\t\021A\031\t\007\007;\031\031ca\005\016\005\r}!bAB\021\005\0059Q.\036;bE2,\027\002BB\023\007?\021aAQ;gM\026\024\bf\002B~k\r%2QF\021\003\007W\tA$^:fA\t,hMZ3s\003NT\025M^1MSN$\b%\0338ti\026\fG-\t\002\0040\005)!GL\035/a!9!Q`\005\005\002\rMR\003BB\033\007w!Baa\016\004>A11QAB\b\007s\0012aJB\036\t\031I3\021\007b\001U!A1\021DB\031\001\004\031y\004\005\004\004\036\r\0053\021H\005\005\007\007\032yBA\002TKFDsa!\r6\007\017\032i#\t\002\004J\005\001So]3![V$\030M\0317f'\026\f\030i\035&bm\006d\025n\035;!S:\034H/Z1e\021\035\021i0\003C\001\007\033*Baa\024\004VQ!1\021KB,!\031\031)aa\004\004TA\031qe!\026\005\r%\032YE1\001+\021!\031Iba\023A\002\re\003#\002\005\004\\\rM\023bAB\"\005!:11J\033\004`\r5\022EAB1\003e)8/\032\021tKF\f5OS1wC2K7\017\036\021j]N$X-\0313\t\017\r\025\024\002\"\001\004h\005I\021m\035&bm\006\034V\r^\013\005\007S\032\031\b\006\003\004l\rU\004CBB\003\007[\032\t(\003\003\004p\r\035!aA*fiB\031qea\035\005\r%\032\031G1\001+\021!\0319ha\031A\002\re\024!A:\021\r\ru11PB9\023\021\031yga\b)\017\r\rTga \004.\005\0221\021Q\001 kN,\007%\\;uC\ndWmU3u\003NT\025M^1TKR\004\023N\\:uK\006$\007bBB3\023\021\0051QQ\013\005\007\017\033i\t\006\003\004\n\016=\005CBB\003\007[\032Y\tE\002(\007\033#a!KBB\005\004Q\003\002CB<\007\007\003\ra!%\021\013!\031\031ja#\n\007\r=$\001K\004\004\004V\0329j!\f\"\005\re\025\001G;tK\002\032X\r^!t\025\0064\030mU3uA%t7\017^3bI\"91QT\005\005\002\r}\025!C1t\025\0064\030-T1q+\031\031\tka+\0040R!11UBY!!\031)a!*\004*\0165\026\002BBT\007\017\0211!T1q!\r931\026\003\007S\rm%\031\001\026\021\007\035\032y\013\002\0044\0077\023\rA\013\005\t\007g\033Y\n1\001\0046\006\tQ\016\005\005\004\036\r]6\021VBW\023\021\0319ka\b)\017\rmUga/\004.\005\0221QX\001 kN,\007%\\;uC\ndW-T1q\003NT\025M^1NCB\004\023N\\:uK\006$\007bBBO\023\021\0051\021Y\013\007\007\007\034Im!4\025\t\r\0257q\032\t\t\007\013\031)ka2\004LB\031qe!3\005\r%\032yL1\001+!\r93Q\032\003\007g\r}&\031\001\026\t\021\rM6q\030a\001\007#\004r\001CBj\007\017\034Y-C\002\004(\nAsaa06\007/\034i#\t\002\004Z\006ARo]3![\006\004\030i\035&bm\006l\025\r\035\021j]N$X-\0313\t\017\ru\027\002\"\001\004`\006y\021m]*dC2\f\027\n^3sC\ndW-\006\003\004b\016-H\003BBr\007[\004R\001CBs\007SL1aa:\003\005!IE/\032:bE2,\007cA\024\004l\0221\021fa7C\002)B\001ba<\004\\\002\0071\021_\001\002SB111_B}\007Sl!a!>\013\t\r]81B\001\005Y\006tw-\003\003\004h\016U\bfBBnk\ru8QF\021\003\007\f1%^:fA%$XM]1cY\026\f5oU2bY\006LE/\032:bE2,\007%\0338ti\026\fG\rC\004\004^&!\t\001b\001\026\t\021\025A1\002\013\005\t\017!i\001E\003\t\007K$I\001E\002(\t\027!a!\013C\001\005\004Q\003\002CBx\t\003\001\r\001b\004\021\r\r\025A\021\003C\005\023\021!\031ba\002\003\025\r{G\016\\3di&|g\016K\004\005\002U\"9b!\f\"\005\021e\021!J;tK\002\032w\016\0347fGRLwN\\!t'\016\fG.Y%uKJ\f'\r\\3!S:\034H/Z1e\021\035!i\"\003C\001\t?\t!\"Y:TG\006d\027-T1q+\031!\t\003b\n\005,Q!A1\005C\027!!\031iba.\005&\021%\002cA\024\005(\0211\021\006b\007C\002)\0022a\nC\026\t\031\031D1\004b\001U!A11\027C\016\001\004!y\003\005\005\004\006\r\025FQ\005C\025Q\035!Y\"\016C\032\007[\t#\001\"\016\0023U\034X\rI7ba\006\0338kY1mC6\013\007\017I5ogR,\027\r\032\005\b\t;IA\021\001C\035)\021!Y\004b\023\021\021\ru1q\027C\037\t{\001B\001b\020\005F9\031Q\002\"\021\n\007\021\rC!\001\004Qe\026$WMZ\005\005\t\017\"IE\001\004TiJLgn\032\006\004\t\007\"\001\002\003C'\to\001\r\001b\024\002\003A\004Ba!\002\005R%!A1KB\004\005)\001&o\0349feRLWm\035\025\b\to)DqKB\027C\t!I&\001\021vg\026\004\003O]8qKJ$\030.Z:BgN\033\027\r\\1NCB\004\023N\\:uK\006$\007")
public final class JavaConversions {
  public static <A, B> ConcurrentMap<A, B> mapAsScalaDeprecatedConcurrentMap(ConcurrentMap<A, B> paramConcurrentMap) {
    return JavaConversions$.MODULE$.mapAsScalaDeprecatedConcurrentMap(paramConcurrentMap);
  }
  
  public static Map<String, String> propertiesAsScalaMap(Properties paramProperties) {
    return JavaConversions$.MODULE$.propertiesAsScalaMap(paramProperties);
  }
  
  public static <A, B> Map<A, B> dictionaryAsScalaMap(Dictionary<A, B> paramDictionary) {
    return JavaConversions$.MODULE$.dictionaryAsScalaMap(paramDictionary);
  }
  
  public static <A, B> Map<A, B> mapAsScalaConcurrentMap(ConcurrentMap<A, B> paramConcurrentMap) {
    return JavaConversions$.MODULE$.mapAsScalaConcurrentMap(paramConcurrentMap);
  }
  
  public static <A, B> ConcurrentMap<A, B> asScalaConcurrentMap(ConcurrentMap<A, B> paramConcurrentMap) {
    return JavaConversions$.MODULE$.asScalaConcurrentMap(paramConcurrentMap);
  }
  
  public static <A, B> Map<A, B> mapAsScalaMap(Map<A, B> paramMap) {
    return JavaConversions$.MODULE$.mapAsScalaMap(paramMap);
  }
  
  public static <A> Set<A> asScalaSet(Set<A> paramSet) {
    return JavaConversions$.MODULE$.asScalaSet(paramSet);
  }
  
  public static <A> Buffer<A> asScalaBuffer(List<A> paramList) {
    return JavaConversions$.MODULE$.asScalaBuffer(paramList);
  }
  
  public static <A> Iterable<A> collectionAsScalaIterable(Collection<A> paramCollection) {
    return JavaConversions$.MODULE$.collectionAsScalaIterable(paramCollection);
  }
  
  public static <A> Iterable<A> iterableAsScalaIterable(java.lang.Iterable<A> paramIterable) {
    return JavaConversions$.MODULE$.iterableAsScalaIterable(paramIterable);
  }
  
  public static <A> Iterator<A> enumerationAsScalaIterator(Enumeration<A> paramEnumeration) {
    return JavaConversions$.MODULE$.enumerationAsScalaIterator(paramEnumeration);
  }
  
  public static <A> Iterator<A> asScalaIterator(Iterator<A> paramIterator) {
    return JavaConversions$.MODULE$.asScalaIterator(paramIterator);
  }
  
  public static <A, B> ConcurrentMap<A, B> mapAsJavaConcurrentMap(Map<A, B> paramMap) {
    return JavaConversions$.MODULE$.mapAsJavaConcurrentMap(paramMap);
  }
  
  public static <A, B> ConcurrentMap<A, B> asJavaConcurrentMap(ConcurrentMap<A, B> paramConcurrentMap) {
    return JavaConversions$.MODULE$.asJavaConcurrentMap(paramConcurrentMap);
  }
  
  public static <A, B> Map<A, B> mapAsJavaMap(Map<A, B> paramMap) {
    return JavaConversions$.MODULE$.mapAsJavaMap(paramMap);
  }
  
  public static <A, B> Dictionary<A, B> asJavaDictionary(Map<A, B> paramMap) {
    return JavaConversions$.MODULE$.asJavaDictionary(paramMap);
  }
  
  public static <A, B> Map<A, B> mutableMapAsJavaMap(Map<A, B> paramMap) {
    return JavaConversions$.MODULE$.mutableMapAsJavaMap(paramMap);
  }
  
  public static <A> Set<A> setAsJavaSet(Set<A> paramSet) {
    return JavaConversions$.MODULE$.setAsJavaSet(paramSet);
  }
  
  public static <A> Set<A> mutableSetAsJavaSet(Set<A> paramSet) {
    return JavaConversions$.MODULE$.mutableSetAsJavaSet(paramSet);
  }
  
  public static <A> List<A> seqAsJavaList(Seq<A> paramSeq) {
    return JavaConversions$.MODULE$.seqAsJavaList(paramSeq);
  }
  
  public static <A> List<A> mutableSeqAsJavaList(Seq<A> paramSeq) {
    return JavaConversions$.MODULE$.mutableSeqAsJavaList(paramSeq);
  }
  
  public static <A> List<A> bufferAsJavaList(Buffer<A> paramBuffer) {
    return JavaConversions$.MODULE$.bufferAsJavaList(paramBuffer);
  }
  
  public static <A> Collection<A> asJavaCollection(Iterable<A> paramIterable) {
    return JavaConversions$.MODULE$.asJavaCollection(paramIterable);
  }
  
  public static <A> java.lang.Iterable<A> asJavaIterable(Iterable<A> paramIterable) {
    return JavaConversions$.MODULE$.asJavaIterable(paramIterable);
  }
  
  public static <A> Enumeration<A> asJavaEnumeration(Iterator<A> paramIterator) {
    return JavaConversions$.MODULE$.asJavaEnumeration(paramIterator);
  }
  
  public static <A> Iterator<A> asJavaIterator(Iterator<A> paramIterator) {
    return JavaConversions$.MODULE$.asJavaIterator(paramIterator);
  }
  
  public static Map<String, String> asScalaMap(Properties paramProperties) {
    return JavaConversions$.MODULE$.asScalaMap(paramProperties);
  }
  
  public static <A, B> Map<A, B> asScalaMap(Map<A, B> paramMap) {
    return JavaConversions$.MODULE$.asScalaMap(paramMap);
  }
  
  public static <A> Iterable<A> asScalaIterable(Collection<A> paramCollection) {
    return JavaConversions$.MODULE$.asScalaIterable(paramCollection);
  }
  
  public static <A> Iterable<A> asScalaIterable(java.lang.Iterable<A> paramIterable) {
    return JavaConversions$.MODULE$.asScalaIterable(paramIterable);
  }
  
  public static <A, B> Map<A, B> asJavaMap(Map<A, B> paramMap) {
    return JavaConversions$.MODULE$.asJavaMap(paramMap);
  }
  
  public static <A, B> Map<A, B> asJavaMap(Map<A, B> paramMap) {
    return JavaConversions$.MODULE$.asJavaMap(paramMap);
  }
  
  public static <A> Set<A> asJavaSet(Set<A> paramSet) {
    return JavaConversions$.MODULE$.asJavaSet(paramSet);
  }
  
  public static <A> Set<A> asJavaSet(Set<A> paramSet) {
    return JavaConversions$.MODULE$.asJavaSet(paramSet);
  }
  
  public static <A> List<A> asJavaList(Seq<A> paramSeq) {
    return JavaConversions$.MODULE$.asJavaList(paramSeq);
  }
  
  public static <A> List<A> asJavaList(Seq<A> paramSeq) {
    return JavaConversions$.MODULE$.asJavaList(paramSeq);
  }
  
  public static <A> List<A> asJavaList(Buffer<A> paramBuffer) {
    return JavaConversions$.MODULE$.asJavaList(paramBuffer);
  }
  
  public static Wrappers$SeqWrapper$ SeqWrapper() {
    return JavaConversions$.MODULE$.SeqWrapper();
  }
  
  public static Wrappers$MutableSetWrapper$ MutableSetWrapper() {
    return JavaConversions$.MODULE$.MutableSetWrapper();
  }
  
  public static Wrappers$MutableSeqWrapper$ MutableSeqWrapper() {
    return JavaConversions$.MODULE$.MutableSeqWrapper();
  }
  
  public static Wrappers$MutableMapWrapper$ MutableMapWrapper() {
    return JavaConversions$.MODULE$.MutableMapWrapper();
  }
  
  public static Wrappers$MutableBufferWrapper$ MutableBufferWrapper() {
    return JavaConversions$.MODULE$.MutableBufferWrapper();
  }
  
  public static Wrappers$JSetWrapper$ JSetWrapper() {
    return JavaConversions$.MODULE$.JSetWrapper();
  }
  
  public static Wrappers$JPropertiesWrapper$ JPropertiesWrapper() {
    return JavaConversions$.MODULE$.JPropertiesWrapper();
  }
  
  public static Wrappers$JMapWrapper$ JMapWrapper() {
    return JavaConversions$.MODULE$.JMapWrapper();
  }
  
  public static Wrappers$JListWrapper$ JListWrapper() {
    return JavaConversions$.MODULE$.JListWrapper();
  }
  
  public static Wrappers$JIteratorWrapper$ JIteratorWrapper() {
    return JavaConversions$.MODULE$.JIteratorWrapper();
  }
  
  public static Wrappers$JIterableWrapper$ JIterableWrapper() {
    return JavaConversions$.MODULE$.JIterableWrapper();
  }
  
  public static Wrappers$JEnumerationWrapper$ JEnumerationWrapper() {
    return JavaConversions$.MODULE$.JEnumerationWrapper();
  }
  
  public static Wrappers$JDictionaryWrapper$ JDictionaryWrapper() {
    return JavaConversions$.MODULE$.JDictionaryWrapper();
  }
  
  public static Wrappers$JConcurrentMapWrapper$ JConcurrentMapWrapper() {
    return JavaConversions$.MODULE$.JConcurrentMapWrapper();
  }
  
  public static Wrappers$JCollectionWrapper$ JCollectionWrapper() {
    return JavaConversions$.MODULE$.JCollectionWrapper();
  }
  
  public static Wrappers$IteratorWrapper$ IteratorWrapper() {
    return JavaConversions$.MODULE$.IteratorWrapper();
  }
  
  public static Wrappers$IterableWrapper$ IterableWrapper() {
    return JavaConversions$.MODULE$.IterableWrapper();
  }
  
  public static Wrappers$DictionaryWrapper$ DictionaryWrapper() {
    return JavaConversions$.MODULE$.DictionaryWrapper();
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\JavaConversions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */