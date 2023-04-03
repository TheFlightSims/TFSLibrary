/*     */ package akka.dispatch;
/*     */ 
/*     */ import com.typesafe.config.Config;
/*     */ import com.typesafe.config.ConfigList;
/*     */ import com.typesafe.config.ConfigMergeable;
/*     */ import com.typesafe.config.ConfigObject;
/*     */ import com.typesafe.config.ConfigOrigin;
/*     */ import com.typesafe.config.ConfigResolveOptions;
/*     */ import com.typesafe.config.ConfigValue;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.Tuple4;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction3;
/*     */ import scala.runtime.AbstractFunction4;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Statics;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021-uAB\001\003\021\003!a!A\007DC\016D\027N\\4D_:4\027n\032\006\003\007\021\t\001\002Z5ta\006$8\r\033\006\002\013\005!\021m[6b!\t9\001\"D\001\003\r\031I!\001#\001\005\025\ti1)Y2iS:<7i\0348gS\036\034\"\001C\006\021\0051yQ\"A\007\013\0039\tQa]2bY\006L!\001E\007\003\r\005s\027PU3g\021\025\021\002\002\"\001\025\003\031a\024N\\5u}\r\001A#\001\004\t\017YA!\031!C\001/\005YQ-\0349us\016{gNZ5h+\005A\002CA\r!\033\005Q\"BA\016\035\003\031\031wN\0344jO*\021QDH\001\tif\004Xm]1gK*\tq$A\002d_6L!!\t\016\003\r\r{gNZ5h\021\031\031\003\002)A\0051\005aQ-\0349us\016{gNZ5hA\0319Q\005\003I\001$C1#!\003)bi\",e\016\036:z'\t!3\002C\004)I\t\007i\021A\025\002\013Y\fG.\0333\026\003)\002\"\001D\026\n\0051j!a\002\"p_2,\027M\034\005\b]\021\022\rQ\"\001*\003\031)\0070[:ug\"91\004\nb\001\016\0039\022\006\002\0232\003\0272AA\r\005Ag\ty1\013\036:j]\036\004\026\r\0365F]R\024\030pE\0032\027Q2\024\b\005\0026I5\t\001\002\005\002\ro%\021\001(\004\002\b!J|G-^2u!\ta!(\003\002<\033\ta1+\032:jC2L'0\0312mK\"A\001&\rBK\002\023\005\021\006\003\005?c\tE\t\025!\003+\003\0311\030\r\\5eA!Aa&\rBK\002\023\005\021\006\003\005Bc\tE\t\025!\003+\003\035)\0070[:ug\002B\001bG\031\003\026\004%\ta\006\005\t\tF\022\t\022)A\0051\00591m\0348gS\036\004\003\002\003$2\005+\007I\021A$\002\013Y\fG.^3\026\003!\003\"!\023'\017\0051Q\025BA&\016\003\031\001&/\0323fM&\021QJ\024\002\007'R\024\030N\\4\013\005-k\001\002\003)2\005#\005\013\021\002%\002\rY\fG.^3!\021\025\021\022\007\"\001S)\025\031F+\026,X!\t)\024\007C\003)#\002\007!\006C\003/#\002\007!\006C\003\034#\002\007\001\004C\003G#\002\007\001\nC\004Zc\005\005I\021\001.\002\t\r|\007/\037\013\006'ncVL\030\005\bQa\003\n\0211\001+\021\035q\003\f%AA\002)Bqa\007-\021\002\003\007\001\004C\004G1B\005\t\031\001%\t\017\001\f\024\023!C\001C\006q1m\0349zI\021,g-Y;mi\022\nT#\0012+\005)\0327&\0013\021\005\025TW\"\0014\013\005\035D\027!C;oG\",7m[3e\025\tIW\"\001\006b]:|G/\031;j_:L!a\0334\003#Ut7\r[3dW\026$g+\031:jC:\034W\rC\004ncE\005I\021A1\002\035\r|\007/\037\023eK\032\fW\017\034;%e!9q.MI\001\n\003\001\030AD2paf$C-\0324bk2$HeM\013\002c*\022\001d\031\005\bgF\n\n\021\"\001u\0039\031w\016]=%I\0264\027-\0367uIQ*\022!\036\026\003\021\016Dqa^\031\002\002\023\005\0030A\007qe>$Wo\031;Qe\0264\027\016_\013\002sB\021!p`\007\002w*\021A0`\001\005Y\006twMC\001\003\021Q\027M^1\n\0055[\b\"CA\002c\005\005I\021AA\003\0031\001(o\0343vGR\f%/\033;z+\t\t9\001E\002\r\003\023I1!a\003\016\005\rIe\016\036\005\n\003\037\t\024\021!C\001\003#\ta\002\035:pIV\034G/\0227f[\026tG\017\006\003\002\024\005e\001c\001\007\002\026%\031\021qC\007\003\007\005s\027\020\003\006\002\034\0055\021\021!a\001\003\017\t1\001\037\0232\021%\ty\"MA\001\n\003\n\t#A\bqe>$Wo\031;Ji\026\024\030\r^8s+\t\t\031\003\005\004\002&\005-\0221C\007\003\003OQ1!!\013\016\003)\031w\016\0347fGRLwN\\\005\005\003[\t9C\001\005Ji\026\024\030\r^8s\021%\t\t$MA\001\n\003\t\031$\001\005dC:,\025/^1m)\rQ\023Q\007\005\013\0037\ty#!AA\002\005M\001\"CA\035c\005\005I\021IA\036\003!A\027m\0355D_\022,GCAA\004\021%\ty$MA\001\n\003\n\t%\001\005u_N#(/\0338h)\005I\b\"CA#c\005\005I\021IA$\003\031)\027/^1mgR\031!&!\023\t\025\005m\0211IA\001\002\004\t\031B\002\004\002N!\001\025q\n\002\017-\006dW/\032)bi\",e\016\036:z'\031\tYe\003\0337s!I\001&a\023\003\026\004%\t!\013\005\n}\005-#\021#Q\001\n)B\021BLA&\005+\007I\021A\025\t\023\005\013YE!E!\002\023Q\003\"C\016\002L\tU\r\021\"\001\030\021%!\0251\nB\tB\003%\001\004C\004\023\003\027\"\t!a\030\025\021\005\005\0241MA3\003O\0022!NA&\021\031A\023Q\fa\001U!1a&!\030A\002)B\001bGA/!\003\005\r\001\007\005\n3\006-\023\021!C\001\003W\"\002\"!\031\002n\005=\024\021\017\005\tQ\005%\004\023!a\001U!Aa&!\033\021\002\003\007!\006\003\005\034\003S\002\n\0211\001\031\021!\001\0271JI\001\n\003\t\007\002C7\002LE\005I\021A1\t\021=\fY%%A\005\002AD\001b^A&\003\003%\t\005\037\005\013\003\007\tY%!A\005\002\005\025\001BCA\b\003\027\n\t\021\"\001\002\000Q!\0211CAA\021)\tY\"! \002\002\003\007\021q\001\005\013\003?\tY%!A\005B\005\005\002BCA\031\003\027\n\t\021\"\001\002\bR\031!&!#\t\025\005m\021QQA\001\002\004\t\031\002\003\006\002:\005-\023\021!C!\003wA!\"a\020\002L\005\005I\021IA!\021)\t)%a\023\002\002\023\005\023\021\023\013\004U\005M\005BCA\016\003\037\013\t\0211\001\002\024\035I\021q\023\005\002\002#\005\021\021T\001\017-\006dW/\032)bi\",e\016\036:z!\r)\0241\024\004\n\003\033B\021\021!E\001\003;\033R!a'\002 f\002\022\"!)\002(*R\003$!\031\016\005\005\r&bAAS\033\0059!/\0368uS6,\027\002BAU\003G\023\021#\0212tiJ\f7\r\036$v]\016$\030n\03484\021\035\021\0221\024C\001\003[#\"!!'\t\025\005}\0221TA\001\n\013\n\t\005\003\006\0024\006m\025\021!CA\003k\013Q!\0319qYf$\002\"!\031\0028\006e\0261\030\005\007Q\005E\006\031\001\026\t\r9\n\t\f1\001+\021!Y\022\021\027I\001\002\004A\002BCA`\0037\013\t\021\"!\002B\0069QO\\1qa2LH\003BAb\003\037\004R\001DAc\003\023L1!a2\016\005\031y\005\017^5p]B1A\"a3+UaI1!!4\016\005\031!V\017\0357fg!Q\021\021[A_\003\003\005\r!!\031\002\007a$\003\007C\005\002V\006m\025\023!C\001a\006y\021\r\0359ms\022\"WMZ1vYR$3\007C\005\002Z\006m\025\023!C\001a\006YB\005\\3tg&t\027\016\036\023he\026\fG/\032:%I\0264\027-\0367uIMB!\"!8\002\034\006\005I\021BAp\003-\021X-\0313SKN|GN^3\025\005\005\005\bc\001>\002d&\031\021Q]>\003\r=\023'.Z2u\017%\tI\017CA\001\022\003\tY/A\bTiJLgn\032)bi\",e\016\036:z!\r)\024Q\036\004\te!\t\t\021#\001\002pN)\021Q^AysAI\021\021UAzU)B\002jU\005\005\003k\f\031KA\tBEN$(/Y2u\rVt7\r^5p]RBqAEAw\t\003\tI\020\006\002\002l\"Q\021qHAw\003\003%)%!\021\t\025\005M\026Q^A\001\n\003\013y\020F\005T\005\003\021\031A!\002\003\b!1\001&!@A\002)BaALA\001\004Q\003BB\016\002~\002\007\001\004\003\004G\003{\004\r\001\023\005\013\003\013i/!A\005\002\n-A\003\002B\007\005+\001R\001DAc\005\037\001r\001\004B\tU)B\002*C\002\003\0245\021a\001V;qY\026$\004\"CAi\005\023\t\t\0211\001T\021)\ti.!<\002\002\023%\021q\034\005\n\0057A!\031!C\001\005;\t\001#\0338wC2LG\rU1uQ\026sGO]=\026\005\005\005\004\002\003B\021\021\001\006I!!\031\002#%tg/\0317jIB\013G\017[#oiJL\b\005C\005\003&!\021\r\021\"\001\003\036\005!bn\0348Fq&\034H/\0338h!\006$\b.\0228uefD\001B!\013\tA\003%\021\021M\001\026]>tW\t_5ti&tw\rU1uQ\026sGO]=!\021%\021i\003\003b\001\n\003\021i\"\001\bf[B$\030\020U1uQ\026sGO]=\t\021\tE\002\002)A\005\003C\nq\"Z7qif\004\026\r\0365F]R\024\030\020\t\004\007\023\t\001AA!\016\024\013\tM\022\021\035\r\t\025\te\"1\007B\001B\003%\001$A\004`G>tg-[4\t\017I\021\031\004\"\001\003>Q!!q\bB!!\r9!1\007\005\b\005s\021Y\0041\001\031\021)\tYBa\r\002B\003%!Q\t\t\007\031\t\035\003Da\023\n\007\t%SB\001\004UkBdWM\r\t\b\005\033\0229\006\023B.\033\t\021yE\003\003\003R\tM\023AC2p]\016,(O]3oi*\031!QK?\002\tU$\030\016\\\005\005\0053\022yEA\tD_:\034WO\035:f]RD\025m\0355NCB\0042A!\030%\035\t9\001\001\003\005\034\005g\021\r\021\"\003\030\021\035!%1\007Q\001\naA!B!\032\0034\t\007I\021\002B4\003!)g\016\036:z\033\006\004XC\001B&\021%\021YGa\r!\002\023\021Y%A\005f]R\024\0300T1qA!A!q\016B\032\t\023\021\t(\001\007hKR\004\026\r\0365F]R\024\030\020\006\003\003\\\tM\004b\002B;\005[\002\r\001S\001\005a\006$\b\016\003\005\003z\tMB\021\001B>\003)\031\007.Z2l-\006d\027\016\032\013\007\005{\022\031Ia\"\021\0071\021y(C\002\003\0026\021A!\0268ji\"9!Q\021B<\001\004A\022!\003:fM\026\024XM\\2f\021!\021IIa\036A\002\t-\025a\004:fgR\024\030n\031;U_B\013G\017[:\021\t1\021i\tS\005\004\005\037k!A\003\037sKB,\027\r^3e}!A!1\023B\032\t\003\021)*\001\003s_>$HC\001BL!\rI\"\021T\005\004\0057S\"\001D\"p]\032Lwm\0242kK\016$\b\002\003BP\005g!\tA!)\002\r=\024\030nZ5o)\t\021\031\013E\002\032\005KK1Aa*\033\0051\031uN\0344jO>\023\030nZ5o\021!\021YKa\r\005\002\t5\026\001D<ji\"4\025\r\0347cC\016\\G\003\002B \005_C\001B!-\003*\002\007!1W\001\006_RDWM\035\t\0043\tU\026b\001B\\5\ty1i\0348gS\036lUM]4fC\ndW\r\003\005\003<\nMB\021\001B_\003\035\021Xm]8mm\026$\"Aa\020\t\021\tm&1\007C\001\005\003$BAa\020\003D\"A!Q\031B`\001\004\0219-A\004paRLwN\\:\021\007e\021I-C\002\003Lj\021AcQ8oM&<'+Z:pYZ,w\n\035;j_:\034\b\002\003Bh\005g!\tA!5\002\017!\f7\017U1uQR\031!Fa5\t\017\tU$Q\032a\001\021\"A!q\033B\032\t\003\021I.A\004jg\026k\007\017^=\025\003)B\001B!8\0034\021\005!q\\\001\tK:$(/_*fiR\021!\021\035\t\007\005G\024)O!;\016\005\tM\023\002\002Bt\005'\0221aU3u!\035\021YO!=z\005kl!A!<\013\t\t=(1K\001\004\033\006\004\030\002\002Bz\005[\024Q!\0228uef\0042!\007B|\023\r\021IP\007\002\f\007>tg-[4WC2,X\r\003\005\003~\nMB\021\001B\000\003)9W\r\036\"p_2,\027M\034\013\004U\r\005\001b\002B;\005w\004\r\001\023\005\t\007\013\021\031\004\"\001\004\b\005Iq-\032;Ok6\024WM\035\013\005\007\023\031y\001E\002{\007\027I1a!\004|\005\031qU/\0342fe\"9!QOB\002\001\004A\005\002CB\n\005g!\ta!\006\002\r\035,G/\0238u)\021\t9aa\006\t\017\tU4\021\003a\001\021\"A11\004B\032\t\003\031i\"A\004hKRduN\\4\025\t\r}1Q\005\t\004\031\r\005\022bAB\022\033\t!Aj\0348h\021\035\021)h!\007A\002!C\001b!\013\0034\021\00511F\001\nO\026$Hi\\;cY\026$Ba!\f\0044A\031Aba\f\n\007\rERB\001\004E_V\024G.\032\005\b\005k\0329\0031\001I\021!\0319Da\r\005\002\re\022!C4fiN#(/\0338h)\rI81\b\005\b\005k\032)\0041\001I\021!\031yDa\r\005\002\r\005\023!C4fi>\023'.Z2u)\021\0219ja\021\t\017\tU4Q\ba\001\021\"A1q\tB\032\t\003\031I%A\005hKR\034uN\0344jOR\031\001da\023\t\017\tU4Q\ta\001\021\"A1q\nB\032\t\003\031\t&A\005hKR\fe.\037*fMR!\021\021]B*\021\035\021)h!\024A\002!C\001ba\026\0034\021\0051\021L\001\tO\026$h+\0317vKR!!Q_B.\021\035\021)h!\026A\002!C\001ba\030\0034\021\0051\021M\001\tO\026$()\037;fgR!11MB4!\rQ8QM\005\004\007GY\bb\002B;\007;\002\r\001\023\005\t\007W\022\031\004\"\001\004n\005yq-\032;NS2d\027n]3d_:$7\017\006\003\004d\r=\004b\002B;\007S\002\r\001\023\005\t\007g\022\031\004\"\001\004v\005qq-\032;OC:|7/Z2p]\022\034H\003BB2\007oBqA!\036\004r\001\007\001\n\003\005\004|\tMB\021AB?\003\0359W\r\036'jgR$Baa \004\006B\031\021d!!\n\007\r\r%D\001\006D_:4\027n\032'jgRDqA!\036\004z\001\007\001\n\003\005\004\n\nMB\021ABF\00399W\r\036\"p_2,\027M\034'jgR$Ba!$\004\030B1!1]BH\007'KAa!%\003T\t!A*[:u!\rQ8QS\005\003YmDqA!\036\004\b\002\007\001\n\003\005\004\034\nMB\021ABO\00359W\r\036(v[\n,'\017T5tiR!1qTBQ!\031\021\031oa$\004\n!9!QOBM\001\004A\005\002CBS\005g!\taa*\002\025\035,G/\0238u\031&\034H\017\006\003\004*\016E\006C\002Br\007\037\033Y\013E\002{\007[K1aa,|\005\035Ie\016^3hKJDqA!\036\004$\002\007\001\n\003\005\0046\nMB\021AB\\\003-9W\r\036'p]\036d\025n\035;\025\t\re61\030\t\007\005G\034yia\031\t\017\tU41\027a\001\021\"A1q\030B\032\t\003\031\t-A\007hKR$u.\0362mK2K7\017\036\013\005\007\007\034I\r\005\004\003d\016=5Q\031\t\004u\016\035\027bAB\031w\"9!QOB_\001\004A\005\002CBg\005g!\taa4\002\033\035,Go\025;sS:<G*[:u)\021\031\tna5\021\013\t\r8qR=\t\017\tU41\032a\001\021\"A1q\033B\032\t\003\031I.A\007hKR|%M[3di2K7\017\036\013\005\0077\034y\017\r\003\004^\016\r\bC\002Br\007\037\033y\016\005\003\004b\016\rH\002\001\003\r\007K\034).!A\001\002\013\0051q\035\002\003A\nBa!;\003\030B\031Aba;\n\007\r5XBA\004O_RD\027N\\4\t\017\tU4Q\033a\001\021\"A11\037B\032\t\003\031)0A\007hKR\034uN\0344jO2K7\017\036\013\005\007o$\t\001\r\003\004z\016u\bC\002Br\007\037\033Y\020\005\003\004b\016uH\001DBs\007c\f\t\021!A\003\002\r}\030cABu1!9!QOBy\001\004A\005\002\003C\003\005g!\t\001b\002\002\033\035,G/\0218z%\0264G*[:u)\021!I\001b\0051\t\021-Aq\002\t\007\005G\034y\t\"\004\021\t\r\005Hq\002\003\r\007K$\031!!A\001\002\013\005A\021C\t\005\007S\f\031\002C\004\003v\021\r\001\031\001%\t\021\021]!1\007C\001\t3\tAbZ3u\005f$Xm\035'jgR$Ba!/\005\034!9!Q\017C\013\001\004A\005\002\003C\020\005g!\t\001\"\t\002'\035,G/T5mY&\034XmY8oINd\025n\035;\025\t\reF1\005\005\b\005k\"i\0021\001I\021!!9Ca\r\005\002\021%\022AE4fi:\013gn\\:fG>tGm\035'jgR$Ba!/\005,!9!Q\017C\023\001\004A\005\002\003C\030\005g!\t\001\"\r\002\031]LG\017[(oYf\004\026\r\0365\025\t\t}B1\007\005\b\005k\"i\0031\001I\021!!9Da\r\005\002\021e\022aC<ji\"|W\017\036)bi\"$BAa\020\005<!9!Q\017C\033\001\004A\005\002\003C \005g!\t\001\"\021\002\r\005$\b+\031;i)\021\021y\004b\021\t\017\tUDQ\ba\001\021\"AAq\tB\032\t\003!I%A\003bi.+\027\020\006\003\003@\021-\003b\002C'\t\013\002\r\001S\001\004W\026L\b\002\003C)\005g!\t\001b\025\002\023]LG\017\033,bYV,GC\002B \t+\"9\006C\004\003v\021=\003\031\001%\t\017\031#y\0051\001\003v\"AA1\fB\032\t\003!i&A\006hKR$UO]1uS>tGCBB\020\t?\"\t\007C\004\003v\021e\003\031\001%\t\021\021\rD\021\fa\001\tK\nA!\0368jiB!!Q\nC4\023\021!IGa\024\003\021QKW.Z+oSRD\001\002\"\034\0034\021\005AqN\001\020O\026$H)\036:bi&|g\016T5tiR11\021\030C9\tgBqA!\036\005l\001\007\001\n\003\005\005d\021-\004\031\001C3\021!!9Ha\r\005\002\te\027AC5t%\026\034x\016\034<fI\"AA1\020B\032\t\003!i(A\006sKN|GN^3XSRDG#\002\r\005\000\021\r\005b\002CA\ts\002\r\001G\001\007g>,(oY3\t\021\t\025G\021\020a\001\005\017D\001\002b\037\0034\021\005Aq\021\013\0041\021%\005b\002CA\t\013\003\r\001\007")
/*     */ public class CachingConfig implements Config {
/*     */   private final Tuple2<Config, ConcurrentHashMap<String, PathEntry>> x$1;
/*     */   
/*     */   private final Config akka$dispatch$CachingConfig$$config;
/*     */   
/*     */   private final ConcurrentHashMap<String, PathEntry> entryMap;
/*     */   
/*     */   public static class ValuePathEntry implements PathEntry, Product, Serializable {
/*     */     private final boolean valid;
/*     */     
/*     */     private final boolean exists;
/*     */     
/*     */     private final Config config;
/*     */     
/*     */     public boolean valid() {
/*  23 */       return this.valid;
/*     */     }
/*     */     
/*     */     public boolean exists() {
/*  23 */       return this.exists;
/*     */     }
/*     */     
/*     */     public Config config() {
/*  23 */       return this.config;
/*     */     }
/*     */     
/*     */     public ValuePathEntry copy(boolean valid, boolean exists, Config config) {
/*  23 */       return new ValuePathEntry(valid, exists, config);
/*     */     }
/*     */     
/*     */     public boolean copy$default$1() {
/*  23 */       return valid();
/*     */     }
/*     */     
/*     */     public boolean copy$default$2() {
/*  23 */       return exists();
/*     */     }
/*     */     
/*     */     public Config copy$default$3() {
/*  23 */       return config();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  23 */       return "ValuePathEntry";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  23 */       return 3;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  23 */       int i = x$1;
/*  23 */       switch (i) {
/*     */         default:
/*  23 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 2:
/*     */         
/*     */         case 1:
/*     */         
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  23 */       return BoxesRunTime.boxToBoolean(valid());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  23 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  23 */       return x$1 instanceof ValuePathEntry;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  23 */       int i = -889275714;
/*  23 */       i = Statics.mix(i, valid() ? 1231 : 1237);
/*  23 */       i = Statics.mix(i, exists() ? 1231 : 1237);
/*  23 */       i = Statics.mix(i, Statics.anyHash(config()));
/*  23 */       return Statics.finalizeHash(i, 3);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  23 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object x$1) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: aload_1
/*     */       //   2: if_acmpeq -> 104
/*     */       //   5: aload_1
/*     */       //   6: astore_2
/*     */       //   7: aload_2
/*     */       //   8: instanceof akka/dispatch/CachingConfig$ValuePathEntry
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 108
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/dispatch/CachingConfig$ValuePathEntry
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual valid : ()Z
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual valid : ()Z
/*     */       //   40: if_icmpne -> 100
/*     */       //   43: aload_0
/*     */       //   44: invokevirtual exists : ()Z
/*     */       //   47: aload #4
/*     */       //   49: invokevirtual exists : ()Z
/*     */       //   52: if_icmpne -> 100
/*     */       //   55: aload_0
/*     */       //   56: invokevirtual config : ()Lcom/typesafe/config/Config;
/*     */       //   59: aload #4
/*     */       //   61: invokevirtual config : ()Lcom/typesafe/config/Config;
/*     */       //   64: astore #5
/*     */       //   66: dup
/*     */       //   67: ifnonnull -> 79
/*     */       //   70: pop
/*     */       //   71: aload #5
/*     */       //   73: ifnull -> 87
/*     */       //   76: goto -> 100
/*     */       //   79: aload #5
/*     */       //   81: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   84: ifeq -> 100
/*     */       //   87: aload #4
/*     */       //   89: aload_0
/*     */       //   90: invokevirtual canEqual : (Ljava/lang/Object;)Z
/*     */       //   93: ifeq -> 100
/*     */       //   96: iconst_1
/*     */       //   97: goto -> 101
/*     */       //   100: iconst_0
/*     */       //   101: ifeq -> 108
/*     */       //   104: iconst_1
/*     */       //   105: goto -> 109
/*     */       //   108: iconst_0
/*     */       //   109: ireturn
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #23	-> 0
/*     */       //   #63	-> 14
/*     */       //   #23	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	110	0	this	Lakka/dispatch/CachingConfig$ValuePathEntry;
/*     */       //   0	110	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public ValuePathEntry(boolean valid, boolean exists, Config config) {
/*  23 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class ValuePathEntry$ extends AbstractFunction3<Object, Object, Config, ValuePathEntry> implements Serializable {
/*     */     public static final ValuePathEntry$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*  23 */       return "ValuePathEntry";
/*     */     }
/*     */     
/*     */     public CachingConfig.ValuePathEntry apply(boolean valid, boolean exists, Config config) {
/*  23 */       return new CachingConfig.ValuePathEntry(valid, exists, config);
/*     */     }
/*     */     
/*     */     public Option<Tuple3<Object, Object, Config>> unapply(CachingConfig.ValuePathEntry x$0) {
/*  23 */       return (x$0 == null) ? (Option<Tuple3<Object, Object, Config>>)scala.None$.MODULE$ : (Option<Tuple3<Object, Object, Config>>)new Some(new Tuple3(BoxesRunTime.boxToBoolean(x$0.valid()), BoxesRunTime.boxToBoolean(x$0.exists()), x$0.config()));
/*     */     }
/*     */     
/*     */     public Config apply$default$3() {
/*  23 */       return CachingConfig$.MODULE$.emptyConfig();
/*     */     }
/*     */     
/*     */     public Config $lessinit$greater$default$3() {
/*  23 */       return CachingConfig$.MODULE$.emptyConfig();
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  23 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public ValuePathEntry$() {
/*  23 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class StringPathEntry implements PathEntry, Product, Serializable {
/*     */     private final boolean valid;
/*     */     
/*     */     private final boolean exists;
/*     */     
/*     */     private final Config config;
/*     */     
/*     */     private final String value;
/*     */     
/*     */     public boolean valid() {
/*  24 */       return this.valid;
/*     */     }
/*     */     
/*     */     public boolean exists() {
/*  24 */       return this.exists;
/*     */     }
/*     */     
/*     */     public Config config() {
/*  24 */       return this.config;
/*     */     }
/*     */     
/*     */     public String value() {
/*  24 */       return this.value;
/*     */     }
/*     */     
/*     */     public StringPathEntry copy(boolean valid, boolean exists, Config config, String value) {
/*  24 */       return new StringPathEntry(valid, exists, config, value);
/*     */     }
/*     */     
/*     */     public boolean copy$default$1() {
/*  24 */       return valid();
/*     */     }
/*     */     
/*     */     public boolean copy$default$2() {
/*  24 */       return exists();
/*     */     }
/*     */     
/*     */     public Config copy$default$3() {
/*  24 */       return config();
/*     */     }
/*     */     
/*     */     public String copy$default$4() {
/*  24 */       return value();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/*  24 */       return "StringPathEntry";
/*     */     }
/*     */     
/*     */     public int productArity() {
/*  24 */       return 4;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/*  24 */       int i = x$1;
/*  24 */       switch (i) {
/*     */         default:
/*  24 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 3:
/*     */         
/*     */         case 2:
/*     */         
/*     */         case 1:
/*     */         
/*     */         case 0:
/*     */           break;
/*     */       } 
/*  24 */       return BoxesRunTime.boxToBoolean(valid());
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/*  24 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/*  24 */       return x$1 instanceof StringPathEntry;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  24 */       int i = -889275714;
/*  24 */       i = Statics.mix(i, valid() ? 1231 : 1237);
/*  24 */       i = Statics.mix(i, exists() ? 1231 : 1237);
/*  24 */       i = Statics.mix(i, Statics.anyHash(config()));
/*  24 */       i = Statics.mix(i, Statics.anyHash(value()));
/*  24 */       return Statics.finalizeHash(i, 4);
/*     */     }
/*     */     
/*     */     public String toString() {
/*  24 */       return scala.runtime.ScalaRunTime$.MODULE$._toString(this);
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
/*     */       //   8: instanceof akka/dispatch/CachingConfig$StringPathEntry
/*     */       //   11: ifeq -> 19
/*     */       //   14: iconst_1
/*     */       //   15: istore_3
/*     */       //   16: goto -> 21
/*     */       //   19: iconst_0
/*     */       //   20: istore_3
/*     */       //   21: iload_3
/*     */       //   22: ifeq -> 140
/*     */       //   25: aload_1
/*     */       //   26: checkcast akka/dispatch/CachingConfig$StringPathEntry
/*     */       //   29: astore #4
/*     */       //   31: aload_0
/*     */       //   32: invokevirtual valid : ()Z
/*     */       //   35: aload #4
/*     */       //   37: invokevirtual valid : ()Z
/*     */       //   40: if_icmpne -> 132
/*     */       //   43: aload_0
/*     */       //   44: invokevirtual exists : ()Z
/*     */       //   47: aload #4
/*     */       //   49: invokevirtual exists : ()Z
/*     */       //   52: if_icmpne -> 132
/*     */       //   55: aload_0
/*     */       //   56: invokevirtual config : ()Lcom/typesafe/config/Config;
/*     */       //   59: aload #4
/*     */       //   61: invokevirtual config : ()Lcom/typesafe/config/Config;
/*     */       //   64: astore #5
/*     */       //   66: dup
/*     */       //   67: ifnonnull -> 79
/*     */       //   70: pop
/*     */       //   71: aload #5
/*     */       //   73: ifnull -> 87
/*     */       //   76: goto -> 132
/*     */       //   79: aload #5
/*     */       //   81: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   84: ifeq -> 132
/*     */       //   87: aload_0
/*     */       //   88: invokevirtual value : ()Ljava/lang/String;
/*     */       //   91: aload #4
/*     */       //   93: invokevirtual value : ()Ljava/lang/String;
/*     */       //   96: astore #6
/*     */       //   98: dup
/*     */       //   99: ifnonnull -> 111
/*     */       //   102: pop
/*     */       //   103: aload #6
/*     */       //   105: ifnull -> 119
/*     */       //   108: goto -> 132
/*     */       //   111: aload #6
/*     */       //   113: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   116: ifeq -> 132
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
/*     */       //   #24	-> 0
/*     */       //   #63	-> 14
/*     */       //   #24	-> 21
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	142	0	this	Lakka/dispatch/CachingConfig$StringPathEntry;
/*     */       //   0	142	1	x$1	Ljava/lang/Object;
/*     */     }
/*     */     
/*     */     public StringPathEntry(boolean valid, boolean exists, Config config, String value) {
/*  24 */       Product.class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class StringPathEntry$ extends AbstractFunction4<Object, Object, Config, String, StringPathEntry> implements Serializable {
/*     */     public static final StringPathEntry$ MODULE$;
/*     */     
/*     */     public final String toString() {
/*  24 */       return "StringPathEntry";
/*     */     }
/*     */     
/*     */     public CachingConfig.StringPathEntry apply(boolean valid, boolean exists, Config config, String value) {
/*  24 */       return new CachingConfig.StringPathEntry(valid, exists, config, value);
/*     */     }
/*     */     
/*     */     public Option<Tuple4<Object, Object, Config, String>> unapply(CachingConfig.StringPathEntry x$0) {
/*  24 */       return (x$0 == null) ? (Option<Tuple4<Object, Object, Config, String>>)scala.None$.MODULE$ : (Option<Tuple4<Object, Object, Config, String>>)new Some(new Tuple4(BoxesRunTime.boxToBoolean(x$0.valid()), BoxesRunTime.boxToBoolean(x$0.exists()), x$0.config(), x$0.value()));
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/*  24 */       return MODULE$;
/*     */     }
/*     */     
/*     */     public StringPathEntry$() {
/*  24 */       MODULE$ = this;
/*     */     }
/*     */   }
/*     */   
/*     */   public CachingConfig(Config _config) {
/*     */     Tuple2 tuple22;
/*  44 */     Config config = _config;
/*  45 */     if (config instanceof CachingConfig) {
/*  45 */       CachingConfig cachingConfig = (CachingConfig)config;
/*  45 */       tuple22 = new Tuple2(cachingConfig.akka$dispatch$CachingConfig$$config(), cachingConfig.entryMap());
/*     */     } else {
/*  46 */       tuple22 = new Tuple2(_config, new ConcurrentHashMap<Object, Object>());
/*     */     } 
/*     */     Tuple2 tuple21 = tuple22;
/*     */     if (tuple21 != null) {
/*     */       Config config1 = (Config)tuple21._1();
/*     */       ConcurrentHashMap entryMap = (ConcurrentHashMap)tuple21._2();
/*     */       if (config1 != null) {
/*     */         Config config2 = config1;
/*     */         if (entryMap != null) {
/*     */           ConcurrentHashMap concurrentHashMap = entryMap;
/*     */           Tuple2<Config, ConcurrentHashMap<String, PathEntry>> tuple2 = new Tuple2(config2, concurrentHashMap);
/*     */           this.akka$dispatch$CachingConfig$$config = (Config)this.x$1._1();
/*     */           this.entryMap = (ConcurrentHashMap<String, PathEntry>)this.x$1._2();
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     throw new MatchError(tuple21);
/*     */   }
/*     */   
/*     */   public Config akka$dispatch$CachingConfig$$config() {
/*     */     return this.akka$dispatch$CachingConfig$$config;
/*     */   }
/*     */   
/*     */   private ConcurrentHashMap<String, PathEntry> entryMap() {
/*     */     return this.entryMap;
/*     */   }
/*     */   
/*     */   private PathEntry getPathEntry(String path) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial entryMap : ()Ljava/util/concurrent/ConcurrentHashMap;
/*     */     //   4: aload_1
/*     */     //   5: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   8: checkcast akka/dispatch/CachingConfig$PathEntry
/*     */     //   11: astore_2
/*     */     //   12: aload_2
/*     */     //   13: ifnonnull -> 297
/*     */     //   16: getstatic scala/util/Try$.MODULE$ : Lscala/util/Try$;
/*     */     //   19: new akka/dispatch/CachingConfig$$anonfun$1
/*     */     //   22: dup
/*     */     //   23: aload_0
/*     */     //   24: aload_1
/*     */     //   25: invokespecial <init> : (Lakka/dispatch/CachingConfig;Ljava/lang/String;)V
/*     */     //   28: invokevirtual apply : (Lscala/Function0;)Lscala/util/Try;
/*     */     //   31: astore #5
/*     */     //   33: aload #5
/*     */     //   35: instanceof scala/util/Failure
/*     */     //   38: ifeq -> 52
/*     */     //   41: getstatic akka/dispatch/CachingConfig$.MODULE$ : Lakka/dispatch/CachingConfig$;
/*     */     //   44: invokevirtual invalidPathEntry : ()Lakka/dispatch/CachingConfig$ValuePathEntry;
/*     */     //   47: astore #6
/*     */     //   49: goto -> 243
/*     */     //   52: aload #5
/*     */     //   54: instanceof scala/util/Success
/*     */     //   57: ifeq -> 94
/*     */     //   60: aload #5
/*     */     //   62: checkcast scala/util/Success
/*     */     //   65: astore #7
/*     */     //   67: aload #7
/*     */     //   69: invokevirtual value : ()Ljava/lang/Object;
/*     */     //   72: invokestatic unboxToBoolean : (Ljava/lang/Object;)Z
/*     */     //   75: istore #8
/*     */     //   77: iconst_0
/*     */     //   78: iload #8
/*     */     //   80: if_icmpne -> 94
/*     */     //   83: getstatic akka/dispatch/CachingConfig$.MODULE$ : Lakka/dispatch/CachingConfig$;
/*     */     //   86: invokevirtual nonExistingPathEntry : ()Lakka/dispatch/CachingConfig$ValuePathEntry;
/*     */     //   89: astore #6
/*     */     //   91: goto -> 243
/*     */     //   94: getstatic scala/util/Try$.MODULE$ : Lscala/util/Try$;
/*     */     //   97: new akka/dispatch/CachingConfig$$anonfun$2
/*     */     //   100: dup
/*     */     //   101: aload_0
/*     */     //   102: aload_1
/*     */     //   103: invokespecial <init> : (Lakka/dispatch/CachingConfig;Ljava/lang/String;)V
/*     */     //   106: invokevirtual apply : (Lscala/Function0;)Lscala/util/Try;
/*     */     //   109: astore #9
/*     */     //   111: aload #9
/*     */     //   113: instanceof scala/util/Failure
/*     */     //   116: ifeq -> 130
/*     */     //   119: getstatic akka/dispatch/CachingConfig$.MODULE$ : Lakka/dispatch/CachingConfig$;
/*     */     //   122: invokevirtual emptyPathEntry : ()Lakka/dispatch/CachingConfig$ValuePathEntry;
/*     */     //   125: astore #10
/*     */     //   127: goto -> 239
/*     */     //   130: aload #9
/*     */     //   132: instanceof scala/util/Success
/*     */     //   135: ifeq -> 287
/*     */     //   138: aload #9
/*     */     //   140: checkcast scala/util/Success
/*     */     //   143: astore #11
/*     */     //   145: aload #11
/*     */     //   147: invokevirtual value : ()Ljava/lang/Object;
/*     */     //   150: checkcast com/typesafe/config/ConfigValue
/*     */     //   153: astore #12
/*     */     //   155: aload #12
/*     */     //   157: invokeinterface valueType : ()Lcom/typesafe/config/ConfigValueType;
/*     */     //   162: getstatic com/typesafe/config/ConfigValueType.STRING : Lcom/typesafe/config/ConfigValueType;
/*     */     //   165: astore #13
/*     */     //   167: dup
/*     */     //   168: ifnonnull -> 180
/*     */     //   171: pop
/*     */     //   172: aload #13
/*     */     //   174: ifnull -> 188
/*     */     //   177: goto -> 219
/*     */     //   180: aload #13
/*     */     //   182: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   185: ifeq -> 219
/*     */     //   188: new akka/dispatch/CachingConfig$StringPathEntry
/*     */     //   191: dup
/*     */     //   192: iconst_1
/*     */     //   193: iconst_1
/*     */     //   194: aload #12
/*     */     //   196: ldc 'cached'
/*     */     //   198: invokeinterface atKey : (Ljava/lang/String;)Lcom/typesafe/config/Config;
/*     */     //   203: aload #12
/*     */     //   205: invokeinterface unwrapped : ()Ljava/lang/Object;
/*     */     //   210: checkcast java/lang/String
/*     */     //   213: invokespecial <init> : (ZZLcom/typesafe/config/Config;Ljava/lang/String;)V
/*     */     //   216: goto -> 237
/*     */     //   219: new akka/dispatch/CachingConfig$ValuePathEntry
/*     */     //   222: dup
/*     */     //   223: iconst_1
/*     */     //   224: iconst_1
/*     */     //   225: aload #12
/*     */     //   227: ldc 'cached'
/*     */     //   229: invokeinterface atKey : (Ljava/lang/String;)Lcom/typesafe/config/Config;
/*     */     //   234: invokespecial <init> : (ZZLcom/typesafe/config/Config;)V
/*     */     //   237: astore #10
/*     */     //   239: aload #10
/*     */     //   241: astore #6
/*     */     //   243: aload #6
/*     */     //   245: astore #4
/*     */     //   247: aload_0
/*     */     //   248: invokespecial entryMap : ()Ljava/util/concurrent/ConcurrentHashMap;
/*     */     //   251: aload_1
/*     */     //   252: aload #4
/*     */     //   254: invokevirtual putIfAbsent : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   257: checkcast akka/dispatch/CachingConfig$PathEntry
/*     */     //   260: astore #14
/*     */     //   262: aload #14
/*     */     //   264: ifnonnull -> 277
/*     */     //   267: aload #4
/*     */     //   269: checkcast akka/dispatch/CachingConfig$PathEntry
/*     */     //   272: astore #15
/*     */     //   274: goto -> 281
/*     */     //   277: aload #14
/*     */     //   279: astore #15
/*     */     //   281: aload #15
/*     */     //   283: astore_3
/*     */     //   284: goto -> 299
/*     */     //   287: new scala/MatchError
/*     */     //   290: dup
/*     */     //   291: aload #9
/*     */     //   293: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   296: athrow
/*     */     //   297: aload_2
/*     */     //   298: astore_3
/*     */     //   299: aload_3
/*     */     //   300: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #49	-> 0
/*     */     //   #50	-> 12
/*     */     //   #51	-> 16
/*     */     //   #52	-> 33
/*     */     //   #53	-> 52
/*     */     //   #55	-> 94
/*     */     //   #56	-> 111
/*     */     //   #57	-> 119
/*     */     //   #58	-> 130
/*     */     //   #59	-> 155
/*     */     //   #60	-> 188
/*     */     //   #62	-> 219
/*     */     //   #59	-> 237
/*     */     //   #55	-> 239
/*     */     //   #51	-> 243
/*     */     //   #66	-> 247
/*     */     //   #67	-> 262
/*     */     //   #68	-> 277
/*     */     //   #66	-> 281
/*     */     //   #50	-> 283
/*     */     //   #55	-> 287
/*     */     //   #71	-> 297
/*     */     //   #49	-> 299
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	301	0	this	Lakka/dispatch/CachingConfig;
/*     */     //   0	301	1	path	Ljava/lang/String;
/*     */     //   155	146	12	v	Lcom/typesafe/config/ConfigValue;
/*     */     //   247	36	4	ne	Lscala/Product;
/*     */   }
/*     */   
/*     */   public class $anonfun$1 extends AbstractFunction0.mcZ.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String path$1;
/*     */     
/*     */     public final boolean apply() {
/*  51 */       return apply$mcZ$sp();
/*     */     }
/*     */     
/*     */     public boolean apply$mcZ$sp() {
/*  51 */       return this.$outer.akka$dispatch$CachingConfig$$config().hasPath(this.path$1);
/*     */     }
/*     */     
/*     */     public $anonfun$1(CachingConfig $outer, String path$1) {}
/*     */   }
/*     */   
/*     */   public class $anonfun$2 extends AbstractFunction0<ConfigValue> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final String path$1;
/*     */     
/*     */     public final ConfigValue apply() {
/*  55 */       return this.$outer.akka$dispatch$CachingConfig$$config().getValue(this.path$1);
/*     */     }
/*     */     
/*     */     public $anonfun$2(CachingConfig $outer, String path$1) {}
/*     */   }
/*     */   
/*     */   public void checkValid(Config reference, Seq restrictToPaths) {
/*  75 */     akka$dispatch$CachingConfig$$config().checkValid(reference, (String[])restrictToPaths.toArray(scala.reflect.ClassTag$.MODULE$.apply(String.class)));
/*     */   }
/*     */   
/*     */   public ConfigObject root() {
/*  78 */     return akka$dispatch$CachingConfig$$config().root();
/*     */   }
/*     */   
/*     */   public ConfigOrigin origin() {
/*  80 */     return akka$dispatch$CachingConfig$$config().origin();
/*     */   }
/*     */   
/*     */   public CachingConfig withFallback(ConfigMergeable other) {
/*  82 */     return new CachingConfig(akka$dispatch$CachingConfig$$config().withFallback(other));
/*     */   }
/*     */   
/*     */   public CachingConfig resolve() {
/*  84 */     return resolve(ConfigResolveOptions.defaults());
/*     */   }
/*     */   
/*     */   public CachingConfig resolve(ConfigResolveOptions options) {
/*  87 */     Config resolved = akka$dispatch$CachingConfig$$config().resolve(options);
/*  88 */     return (resolved == akka$dispatch$CachingConfig$$config()) ? this : 
/*  89 */       new CachingConfig(resolved);
/*     */   }
/*     */   
/*     */   public boolean hasPath(String path) {
/*  93 */     PathEntry entry = getPathEntry(path);
/*  94 */     return entry.valid() ? 
/*  95 */       entry.exists() : 
/*     */       
/*  97 */       akka$dispatch$CachingConfig$$config().hasPath(path);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 100 */     return akka$dispatch$CachingConfig$$config().isEmpty();
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<String, ConfigValue>> entrySet() {
/* 102 */     return akka$dispatch$CachingConfig$$config().entrySet();
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String path) {
/* 104 */     return akka$dispatch$CachingConfig$$config().getBoolean(path);
/*     */   }
/*     */   
/*     */   public Number getNumber(String path) {
/* 106 */     return akka$dispatch$CachingConfig$$config().getNumber(path);
/*     */   }
/*     */   
/*     */   public int getInt(String path) {
/* 108 */     return akka$dispatch$CachingConfig$$config().getInt(path);
/*     */   }
/*     */   
/*     */   public long getLong(String path) {
/* 110 */     return akka$dispatch$CachingConfig$$config().getLong(path);
/*     */   }
/*     */   
/*     */   public double getDouble(String path) {
/* 112 */     return akka$dispatch$CachingConfig$$config().getDouble(path);
/*     */   }
/*     */   
/*     */   public String getString(String path) {
/*     */     String str;
/* 115 */     PathEntry pathEntry = getPathEntry(path);
/* 116 */     if (pathEntry instanceof StringPathEntry) {
/* 116 */       StringPathEntry stringPathEntry = (StringPathEntry)pathEntry;
/* 116 */       String string = stringPathEntry.value();
/* 117 */       str = string;
/*     */     } else {
/* 118 */       str = pathEntry.config().getString("cached");
/*     */     } 
/*     */     return str;
/*     */   }
/*     */   
/*     */   public ConfigObject getObject(String path) {
/* 122 */     return akka$dispatch$CachingConfig$$config().getObject(path);
/*     */   }
/*     */   
/*     */   public Config getConfig(String path) {
/* 124 */     return akka$dispatch$CachingConfig$$config().getConfig(path);
/*     */   }
/*     */   
/*     */   public Object getAnyRef(String path) {
/* 126 */     return akka$dispatch$CachingConfig$$config().getAnyRef(path);
/*     */   }
/*     */   
/*     */   public ConfigValue getValue(String path) {
/* 128 */     return akka$dispatch$CachingConfig$$config().getValue(path);
/*     */   }
/*     */   
/*     */   public Long getBytes(String path) {
/* 130 */     return akka$dispatch$CachingConfig$$config().getBytes(path);
/*     */   }
/*     */   
/*     */   public Long getMilliseconds(String path) {
/* 132 */     return akka$dispatch$CachingConfig$$config().getMilliseconds(path);
/*     */   }
/*     */   
/*     */   public Long getNanoseconds(String path) {
/* 134 */     return akka$dispatch$CachingConfig$$config().getNanoseconds(path);
/*     */   }
/*     */   
/*     */   public ConfigList getList(String path) {
/* 136 */     return akka$dispatch$CachingConfig$$config().getList(path);
/*     */   }
/*     */   
/*     */   public List<Boolean> getBooleanList(String path) {
/* 138 */     return akka$dispatch$CachingConfig$$config().getBooleanList(path);
/*     */   }
/*     */   
/*     */   public List<Number> getNumberList(String path) {
/* 140 */     return akka$dispatch$CachingConfig$$config().getNumberList(path);
/*     */   }
/*     */   
/*     */   public List<Integer> getIntList(String path) {
/* 142 */     return akka$dispatch$CachingConfig$$config().getIntList(path);
/*     */   }
/*     */   
/*     */   public List<Long> getLongList(String path) {
/* 144 */     return akka$dispatch$CachingConfig$$config().getLongList(path);
/*     */   }
/*     */   
/*     */   public List<Double> getDoubleList(String path) {
/* 146 */     return akka$dispatch$CachingConfig$$config().getDoubleList(path);
/*     */   }
/*     */   
/*     */   public List<String> getStringList(String path) {
/* 148 */     return akka$dispatch$CachingConfig$$config().getStringList(path);
/*     */   }
/*     */   
/*     */   public List<? extends ConfigObject> getObjectList(String path) {
/* 150 */     return akka$dispatch$CachingConfig$$config().getObjectList(path);
/*     */   }
/*     */   
/*     */   public List<? extends Config> getConfigList(String path) {
/* 152 */     return akka$dispatch$CachingConfig$$config().getConfigList(path);
/*     */   }
/*     */   
/*     */   public List<?> getAnyRefList(String path) {
/* 154 */     return akka$dispatch$CachingConfig$$config().getAnyRefList(path);
/*     */   }
/*     */   
/*     */   public List<Long> getBytesList(String path) {
/* 156 */     return akka$dispatch$CachingConfig$$config().getBytesList(path);
/*     */   }
/*     */   
/*     */   public List<Long> getMillisecondsList(String path) {
/* 158 */     return akka$dispatch$CachingConfig$$config().getMillisecondsList(path);
/*     */   }
/*     */   
/*     */   public List<Long> getNanosecondsList(String path) {
/* 160 */     return akka$dispatch$CachingConfig$$config().getNanosecondsList(path);
/*     */   }
/*     */   
/*     */   public CachingConfig withOnlyPath(String path) {
/* 162 */     return new CachingConfig(akka$dispatch$CachingConfig$$config().withOnlyPath(path));
/*     */   }
/*     */   
/*     */   public CachingConfig withoutPath(String path) {
/* 164 */     return new CachingConfig(akka$dispatch$CachingConfig$$config().withoutPath(path));
/*     */   }
/*     */   
/*     */   public CachingConfig atPath(String path) {
/* 166 */     return new CachingConfig(akka$dispatch$CachingConfig$$config().atPath(path));
/*     */   }
/*     */   
/*     */   public CachingConfig atKey(String key) {
/* 168 */     return new CachingConfig(akka$dispatch$CachingConfig$$config().atKey(key));
/*     */   }
/*     */   
/*     */   public CachingConfig withValue(String path, ConfigValue value) {
/* 170 */     return new CachingConfig(akka$dispatch$CachingConfig$$config().withValue(path, value));
/*     */   }
/*     */   
/*     */   public long getDuration(String path, TimeUnit unit) {
/* 172 */     return akka$dispatch$CachingConfig$$config().getDuration(path, unit);
/*     */   }
/*     */   
/*     */   public List<Long> getDurationList(String path, TimeUnit unit) {
/* 174 */     return akka$dispatch$CachingConfig$$config().getDurationList(path, unit);
/*     */   }
/*     */   
/*     */   public boolean isResolved() {
/* 176 */     return akka$dispatch$CachingConfig$$config().isResolved();
/*     */   }
/*     */   
/*     */   public Config resolveWith(Config source, ConfigResolveOptions options) {
/* 178 */     return akka$dispatch$CachingConfig$$config().resolveWith(source, options);
/*     */   }
/*     */   
/*     */   public Config resolveWith(Config source) {
/* 180 */     return akka$dispatch$CachingConfig$$config().resolveWith(source);
/*     */   }
/*     */   
/*     */   public static ValuePathEntry emptyPathEntry() {
/*     */     return CachingConfig$.MODULE$.emptyPathEntry();
/*     */   }
/*     */   
/*     */   public static ValuePathEntry nonExistingPathEntry() {
/*     */     return CachingConfig$.MODULE$.nonExistingPathEntry();
/*     */   }
/*     */   
/*     */   public static ValuePathEntry invalidPathEntry() {
/*     */     return CachingConfig$.MODULE$.invalidPathEntry();
/*     */   }
/*     */   
/*     */   public static Config emptyConfig() {
/*     */     return CachingConfig$.MODULE$.emptyConfig();
/*     */   }
/*     */   
/*     */   public static interface PathEntry {
/*     */     boolean valid();
/*     */     
/*     */     boolean exists();
/*     */     
/*     */     Config config();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\CachingConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */