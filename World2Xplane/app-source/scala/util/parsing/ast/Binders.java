/*     */ package scala.util.parsing.ast;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Product;
/*     */ import scala.Proxy;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractIterable;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.mutable.LinkedHashMap;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.NonLocalReturnControl;
/*     */ import scala.util.parsing.input.Position;
/*     */ import scala.util.parsing.input.Positional;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021EeaB\001\003!\003\r\ta\003\002\b\005&tG-\032:t\025\t\031A!A\002bgRT!!\002\004\002\017A\f'o]5oO*\021q\001C\001\005kRLGNC\001\n\003\025\0318-\0317b\007\001\031B\001\001\007\021)A\021QBD\007\002\021%\021q\002\003\002\007\003:L(+\0324\021\005E\021R\"\001\002\n\005M\021!AD!cgR\024\030m\031;Ts:$\030\r\037\t\003#UI!A\006\002\003\0215\013\007\017]1cY\026DQ\001\007\001\005\002e\ta\001J5oSR$C#\001\016\021\0055Y\022B\001\017\t\005\021)f.\033;\007\ty\001\001a\b\002\006'\016|\007/Z\013\003A%\0322!H\0214!\r\021SeJ\007\002G)\021A\005C\001\013G>dG.Z2uS>t\027B\001\024$\005A\t%m\035;sC\016$\030\n^3sC\ndW\r\005\002)S1\001A!\002\026\036\005\004Y#A\0032j]\022,'\017V=qKF\021Af\f\t\003\0335J!A\f\005\003\0179{G\017[5oOB\021\001'M\007\002\001%\021!G\005\002\f\035\006lW-\0227f[\026tG\017E\0025y\035r!!\016\036\017\005YJT\"A\034\013\005aR\021A\002\037s_>$h(C\001\n\023\tY\004\"A\004qC\016\\\027mZ3\n\005ur$\001C%uKJ\f'\r\\3\013\005mB\001\"\002!\036\t\003\t\025A\002\037j]&$h\bF\001C!\r\001Td\n\005\b\tv\021\r\021\"\003F\0031\031XOY:uSR,H/[8o+\0051\005\003B$KO1k\021\001\023\006\003\023\016\nq!\\;uC\ndW-\003\002L\021\n\031Q*\0319\021\005Aj\025B\001(\023\005\035)E.Z7f]RDa\001U\017!\002\0231\025!D:vEN$\030\016^;uS>t\007\005C\004S;\t\007I\021A*\002\005%$W#\001+\021\0055)\026B\001,\t\005\rIe\016\036\005\0071v\001\013\021\002+\002\007%$\007\005C\003[;\021\0051,\001\005ji\026\024\030\r^8s+\005a\006c\001\022^O%\021al\t\002\t\023R,'/\031;pe\")\001-\bC\001C\006)\021\r\0359msR\021qE\031\005\006G~\003\r\001V\001\002S\")Q-\bC\001M\006)!-\0338egR\021qM\033\t\003\033!L!!\033\005\003\017\t{w\016\\3b]\")1\016\032a\001O\005\t!\rC\003n;\021\005a.\001\005j]\022,\007PR8s)\ty'\017E\002\016aRK!!\035\005\003\r=\003H/[8o\021\025YG\0161\001(\021\025!X\004\"\001v\003%\tG\r\032\"j]\022,'\017\006\002\033m\")1n\035a\001O!)\0010\bC\001s\006a1-\0318BI\022\024\025N\0343feR\021qM\037\005\006W^\004\ra\n\005\006yv!\t!`\001\013gV\0247\017^5ukR,Gc\001\016\")1n\037a\001O!1\021\021A>A\0021\013QA^1mk\026Dq!!\002\036\t\003\t9!A\007hKR,E.Z7f]R4uN\035\013\004\031\006%\001BB6\002\004\001\007q\005C\004\002\016u!\t%a\004\002\021Q|7\013\036:j]\036$\"!!\005\021\t\005M\021\021\004\b\004\033\005U\021bAA\f\021\0051\001K]3eK\032LA!a\007\002\036\t11\013\036:j]\036T1!a\006\t\021\035\t\t#\bC\001\003G\tqBY5oI\026\0248\017V8TiJLgnZ\013\003\003K\001R\001NA\024\003#I1!!\013?\005\021a\025n\035;\t\017\0055R\004\"\001\0020\005y\021\r\0347po\032{'o^1sIJ+g-F\001C\021\035\t\031$\bC\001\003_\taA\\3ti\026$\007BBA\034;\021\005\021$A\004p]\026sG/\032:\t\r\005mR\004\"\001\032\003\031yg\016T3gi\032I\021q\b\001\021\002G\005\021\021\t\002\021\005&tG-\0338h'\026t7/\033;jm\026\0342!!\020\r\r\031\t)\005\001!\002H\ta!i\\;oI\026cW-\\3oiV!\021\021JA4'1\t\031\005D\030\002L\005E\0231KA-!\ri\021QJ\005\004\003\037B!!\002)s_bL\bc\001\031\002>A\031Q\"!\026\n\007\005]\003BA\004Qe>$Wo\031;\021\0075\tY&C\002\002^!\021AbU3sS\006d\027N_1cY\026D1\"!\031\002D\tU\r\021\"\001\002d\005\021Q\r\\\013\003\003K\0022\001KA4\t\035\tI'a\021C\002-\022ABY8v]\022,E.Z7f]RD1\"!\034\002D\tE\t\025!\003\002f\005\031Q\r\034\021\t\027\005E\0241\tBK\002\023\005\0211O\001\006g\016|\007/Z\013\003\003k\002B\001M\017\002f!Y\021\021PA\"\005#\005\013\021BA;\003\031\0318m\0349fA!9\001)a\021\005\002\005uDCBA@\003\003\013\031\tE\0031\003\007\n)\007\003\005\002b\005m\004\031AA3\021!\t\t(a\037A\002\005U\004\002CAD\003\007\"\t!!#\002\tM,GNZ\013\002\031\"A\021QRA\"\t\003\ty)\001\003oC6,WCAA\t\021!\ti!a\021\005B\005=\001\002CAK\003\007\"\t!a&\002\031\005d\007\017[1`I\025\fH%Z9\026\t\005e\0251\025\013\004O\006m\005\002CAO\003'\003\r!a(\002\013=$\b.\032:\021\013A\n\031%!)\021\007!\n\031\013B\004\002&\006M%\031A\026\003\003QD!\"!+\002D\005\005I\021AAV\003\021\031w\016]=\026\t\0055\0261\027\013\007\003_\013),a.\021\013A\n\031%!-\021\007!\n\031\fB\004\002j\005\035&\031A\026\t\025\005\005\024q\025I\001\002\004\t\t\f\003\006\002r\005\035\006\023!a\001\003s\003B\001M\017\0022\"Q\021QXA\"#\003%\t!a0\002\035\r|\007/\037\023eK\032\fW\017\034;%cU!\021\021YAl+\t\t\031M\013\003\002f\005\0257FAAd!\021\tI-a5\016\005\005-'\002BAg\003\037\f\021\"\0368dQ\026\0347.\0323\013\007\005E\007\"\001\006b]:|G/\031;j_:LA!!6\002L\n\tRO\\2iK\016\\W\r\032,be&\fgnY3\005\017\005%\0241\030b\001W!Q\0211\\A\"#\003%\t!!8\002\035\r|\007/\037\023eK\032\fW\017\034;%eU!\021q\\Ar+\t\t\tO\013\003\002v\005\025GaBA5\0033\024\ra\013\005\013\003O\f\031%!A\005B\005%\030!\0049s_\022,8\r\036)sK\032L\0070\006\002\002lB!\021Q^A|\033\t\tyO\003\003\002r\006M\030\001\0027b]\036T!!!>\002\t)\fg/Y\005\005\0037\ty\017C\005\002|\006\r\023\021!C\001'\006a\001O]8ek\016$\030I]5us\"Q\021q`A\"\003\003%\tA!\001\002\035A\024x\016Z;di\026cW-\\3oiR!!1\001B\005!\ri!QA\005\004\005\017A!aA!os\"I!1BA\003\003\005\r\001V\001\004q\022\n\004B\003B\b\003\007\n\t\021\"\021\003\022\005y\001O]8ek\016$\030\n^3sCR|'/\006\002\003\024A!!%\030B\002\021)\0219\"a\021\002\002\023\005!\021D\001\tG\006tW)];bYR\031qMa\007\t\025\t-!QCA\001\002\004\021\031aB\005\003 \001\t\t\021#\001\003\"\005a!i\\;oI\026cW-\\3oiB\031\001Ga\t\007\023\005\025\003!!A\t\002\t\0252#\002B\022\031\005e\003b\002!\003$\021\005!\021\006\013\003\005CA!\"!\004\003$\005\005IQ\tB\027)\t\tY\017C\005a\005G\t\t\021\"!\0032U!!1\007B\035)\031\021)Da\017\003>A)\001'a\021\0038A\031\001F!\017\005\017\005%$q\006b\001W!A\021\021\rB\030\001\004\0219\004\003\005\002r\t=\002\031\001B !\021\001TDa\016\t\025\t\r#1EA\001\n\003\023)%A\004v]\006\004\b\017\\=\026\t\t\035#1\013\013\005\005\023\0229\006\005\003\016a\n-\003cB\007\003N\tE#QK\005\004\005\037B!A\002+va2,'\007E\002)\005'\"q!!\033\003B\t\0071\006\005\0031;\tE\003B\003B-\005\003\n\t\0211\001\003\\\005\031\001\020\n\031\021\013A\n\031E!\025\t\025\t}#1EA\001\n\023\021\t'A\006sK\006$'+Z:pYZ,GC\001B2!\021\tiO!\032\n\t\t\035\024q\036\002\007\037\nTWm\031;\007\r\t-\004\001\001B7\0059)fNY8v]\022,E.Z7f]R,BAa\034\003xM!!\021\016\0070\021-\t\tG!\033\003\006\004%IAa\035\026\005\tU\004c\001\025\003x\0219!\021\020B5\005\004Y#!\001(\t\027\0055$\021\016B\001B\003%!Q\017\005\b\001\n%D\021\001B@)\021\021\tIa!\021\013A\022IG!\036\t\021\005\005$Q\020a\001\005kB\001\"!$\003j\021\005\021\021\036\004\007\005\023\003\001Aa#\003\027UsG-\032:CS:$WM]\013\007\005\033\0239J!)\024\r\t\035E\002TA)\021-\t\tHa\"\003\006\004%\tA!%\026\005\tM\005\003\002\031\036\005+\0032\001\013BL\t\031Q#q\021b\001W!Y\021\021\020BD\005\003\005\013\021\002BJ\0211\021iJa\"\003\006\003\005\013\021\002BP\003!\0328-\0317bIU$\030\016\034\023qCJ\034\030N\\4%CN$HEQ5oI\026\0248\017\n\023fY\026lWM\034;!!\rA#\021\025\003\t\005G\0239I1\001\003&\nAQ\r\\3nK:$H+E\002-\005\007A1B!+\003\b\n\r\t\025a\003\003,\006QQM^5eK:\034W\rJ\033\021\0175\021iKa(\0032&\031!q\026\005\003\023\031+hn\031;j_:\f\004#\002\031\0034\n}\025B\001\f\026\021\035\001%q\021C\001\005o#bA!/\003@\n\005G\003\002B^\005{\003r\001\rBD\005+\023y\n\003\005\003*\nU\0069\001BV\021!\t\tH!.A\002\tM\005\002\003Bb\005k\003\rAa(\002\017\025dW-\\3oi\"A\021Q\002BD\t\003\ny\001\003\005\003J\n\035E\021\001Bf\003U\031Gn\0348f\0132,W.\0328u/&$\bnU;cgR$BAa(\003N\"A!q\032Bd\001\004\021\t.A\003tk\n\034H\017\005\004\002\024\tMwfL\005\004\027\006u\001\002\003Bl\005\017#\tA!7\0027\rdwN\\3FY\026lWM\034;O_\n{WO\0343FY\026lWM\034;t+\t\021y\n\003\005\003^\n\035E\021\001Bm\003\035)\007\020\036:bGRD\001B!8\003\b\022\005!\021\035\013\005\005?\023\031\017\003\005\003P\n}\007\031\001Bi\021!\0219Oa\"\005\002\005=\025aD3mK6,g\016\036+p'R\024\030N\\4\t\033\t-(q\021B\003\006\004%\t\001\001Bm\003\035\0328-\0317bIU$\030\016\034\023qCJ\034\030N\\4%CN$HEQ5oI\026\0248\017\n\023fY\026lWM\034;\t\017\t=\b\001b\001\003r\006)RK\0343fe\nKg\016Z3s\023Nl\025\r\0359bE2,WC\002Bz\005{\034\031\001\006\003\003v\016]AC\002B|\007\017\031y\001E\0031\005g\023I\020E\0041\005\017\023Yp!\001\021\007!\022i\020B\004\003\000\n5(\031A\026\003\005\t$\bc\001\025\004\004\021A1Q\001Bw\005\004\021)K\001\002ti\"Q1\021\002Bw\003\003\005\035aa\003\002\025\0254\030\016Z3oG\026$\003\bE\004\016\005[\023Yp!\004\021\013A\022\031La?\t\025\rE!Q^A\001\002\b\031\031\"\001\006fm&$WM\\2fIe\002r!\004BW\007\003\031)\002E\0031\005g\033\t\001\003\005\004\032\t5\b\031\001B}\003\t)(\rC\004\004\036\001!\031aa\b\002\037M\033w\016]3Jg6\013\007\017]1cY\026,Ba!\t\004,Q!11EB\033)\021\031)c!\f\021\013A\022\031la\n\021\tAj2\021\006\t\004Q\r-Ba\002B\000\0077\021\ra\013\005\013\007_\031Y\"!AA\004\rE\022aC3wS\022,gnY3%cA\002r!\004BW\007S\031\031\004E\0031\005g\033I\003\003\005\002r\rm\001\031AB\024\021\035\031I\004\001C\002\007w\tQCT1nK\026cW-\\3oi&\033X*\0319qC\ndW\r\006\003\004>\r}\002\003\002\031\0034>Bq!a\"\0048\001\007q\006C\004\004D\0011\ta!\022\0023U\033XM\035(b[\026,E.Z7f]RL5/T1qa\006\024G.Z\013\005\007\017\032i\005\006\003\004J\r=\003#\002\031\0034\016-\003c\001\025\004N\0219\021QUB!\005\004Y\003\002CAD\007\003\002\raa\023\b\017\rM\003\001#\001\004V\005YQK\0343fe\nKg\016Z3s!\r\0014q\013\004\b\005\023\003\001\022AB-'\r\0319\006\004\005\b\001\016]C\021AB/)\t\031)\006C\004a\007/\"\ta!\031\026\r\r\r41NB8)\031\031)g!\037\004~Q!1qMB9!\035\001$qQB5\007[\0022\001KB6\t\031Q3q\fb\001WA\031\001fa\034\005\021\t\r6q\fb\001\005KC!ba\035\004`\005\005\t9AB;\003-)g/\0333f]\016,G%M\031\021\0175\021ik!\034\004xA)\001Ga-\004n!A\021\021OB0\001\004\031Y\b\005\0031;\r%\004\002\003Bb\007?\002\ra!\034\t\021\r\0055q\013C\001\007\007\013A!\0368jiV11QQBG\007##Baa\"\004\034R!1\021RBJ!\035\001$qQBF\007\037\0032\001KBG\t\035\021ypa C\002-\0022\001KBI\t!\021\031ka C\002\t\025\006BCBK\007\n\t\021q\001\004\030\006YQM^5eK:\034W\rJ\0313!\035i!QVBH\0073\003R\001\rBZ\007\037C\001b!(\004\000\001\0071qR\001\002q\"91\021\025\001\005\002\r\r\026\001C:fcV,gnY3\026\r\r\0256QVBZ)\021\0319k!0\025\t\r%6Q\027\t\ba\t\03551VBX!\rA3Q\026\003\b\005\034yJ1\001,!\025!\024qEBY!\rA31\027\003\t\007\013\031yJ1\001\003&\"Q1qWBP\003\003\005\035a!/\002\027\0254\030\016Z3oG\026$\023g\r\t\b\033\t56\021WB^!\025\001$1WBY\021!\031yla(A\002\r\005\027\001B8sS\036\004R\001NA\024\007\007\004r\001\rBD\007W\033\t\fC\004\004H\002!\ta!3\002\025Ut7/Z9vK:\034W-\006\004\004L\016U7\021\034\013\005\007\033\034\031\017\006\003\004P\016m\007#\002\033\002(\rE\007c\002\031\003\b\016M7q\033\t\004Q\rUGa\002B\000\007\013\024\ra\013\t\004Q\reG\001CB\003\007\013\024\rA!*\t\025\ru7QYA\001\002\b\031y.A\006fm&$WM\\2fIE\"\004cB\007\003.\016]7\021\035\t\006a\tM6q\033\005\t\007\033)\r1\001\004fB9\001Ga\"\004T\016\035\b#\002\033\002(\r]gaBBv\001\005\0051Q\036\002\n\005&tG-\032:F]Z\0342a!;\r\021\035\0015\021\036C\001\007c$\"aa=\021\007A\032I\017C\004a\007S4\taa>\026\t\reH\021\001\013\005\007w$)\001\005\003\016a\016u\b\003\002\031\036\007\0042\001\013C\001\t\035!\031a!>C\002-\022\021!\021\005\t\t\017\031)\0201\001\004\000\006\ta\017\003\005\005\f\r%H\021\001C\007\003\031)\007\020^3oIV!Aq\002C\013)\031\031\031\020\"\005\005\032!AAq\001C\005\001\004!\031\002E\002)\t+!q\001b\006\005\n\t\0071FA\001b\021!\031i\n\"\003A\002\021m\001\003\002\031\036\t'9q\001b\b\001\021\003!\t#\001\bF[B$\030PQ5oI\026\024XI\034<\021\007A\"\031CB\004\005&\001A\t\001b\n\003\035\025k\007\017^=CS:$WM]#omN!A1EBz\021\035\001E1\005C\001\tW!\"\001\"\t\t\017\001$\031\003\"\001\0050U!A\021\007C\035)\021!\031\004b\017\021\t5\001HQ\007\t\005au!9\004E\002)\ts!q\001b\001\005.\t\0071\006\003\005\005\b\0215\002\031\001C\034\r%!y\004\001I\001$\003!\tEA\006SKR,(O\\!oI\022{W\003\002C\"\t\033\0322\001\"\020\r\021!!9\005\"\020\007\002\021%\023!B1oI\022{G\003\002C&\t#\0022\001\013C'\t!!y\005\"\020C\002\t\025&!\001+\t\023\021MCQ\tCA\002\021U\023!\0022m_\016\\\007\003B\007\005XiI1\001\"\027\t\005!a$-\0378b[\026t\004b\002C/\001\021\005AqL\001\be\026$XO\0358`+\021!\t\007b\032\025\t\021\rD\021\016\t\006a\021uBQ\r\t\004Q\021\035D\001\003C(\t7\022\rA!*\t\021\021-D1\fa\001\tK\naA]3tk2$xa\002C8\001!%A\021O\001\b?\nKg\016Z3s!\r\001D1\017\004\b\tk\002\001\022\002C<\005\035y&)\0338eKJ\0342\001b\035\r\021\035\001E1\017C\001\tw\"\"\001\"\035\t\023\021}D1\017a\001\n\023\031\026!C2veJ,g\016^%e\021)!\031\tb\035A\002\023%AQQ\001\016GV\024(/\0328u\023\022|F%Z9\025\007i!9\tC\005\003\f\021\005\025\021!a\001)\"AA1\022C:A\003&A+\001\006dkJ\024XM\034;JI\002B\001\002b$\005t\021\005\001aU\001\006O\026t\027\n\032")
/*     */ public interface Binders extends AbstractSyntax, Mappable {
/*     */   BoundElement$ BoundElement();
/*     */   
/*     */   <bt extends AbstractSyntax.NameElement, st> Mappable.Mappable<UnderBinder<bt, st>> UnderBinderIsMappable(UnderBinder<bt, st> paramUnderBinder, Function1<bt, Mappable.Mappable<bt>> paramFunction1, Function1<st, Mappable.Mappable<st>> paramFunction11);
/*     */   
/*     */   <bt extends AbstractSyntax.NameElement> Mappable.Mappable<Scope<bt>> ScopeIsMappable(Scope<bt> paramScope, Function1<bt, Mappable.Mappable<bt>> paramFunction1);
/*     */   
/*     */   Mappable.Mappable<AbstractSyntax.NameElement> NameElementIsMappable(AbstractSyntax.NameElement paramNameElement);
/*     */   
/*     */   <t extends AbstractSyntax.NameElement> Mappable.Mappable<t> UserNameElementIsMappable(t paramt);
/*     */   
/*     */   UnderBinder$ UnderBinder();
/*     */   
/*     */   <bt extends AbstractSyntax.NameElement, st> UnderBinder<bt, List<st>> sequence(List<UnderBinder<bt, st>> paramList, Function1<st, Mappable.Mappable<st>> paramFunction1);
/*     */   
/*     */   <bt extends AbstractSyntax.NameElement, st> List<UnderBinder<bt, st>> unsequence(UnderBinder<bt, List<st>> paramUnderBinder, Function1<st, Mappable.Mappable<st>> paramFunction1);
/*     */   
/*     */   EmptyBinderEnv$ EmptyBinderEnv();
/*     */   
/*     */   <T> ReturnAndDo<T> return_(T paramT);
/*     */   
/*     */   _Binder$ scala$util$parsing$ast$Binders$$_Binder();
/*     */   
/*     */   public class Scope<binderType extends AbstractSyntax.NameElement> extends AbstractIterable<binderType> implements Iterable<binderType> {
/*     */     private final Map<binderType, AbstractSyntax.Element> substitution;
/*     */     
/*     */     private final int id;
/*     */     
/*     */     public Scope(Binders $outer) {
/*  90 */       this.substitution = 
/*  91 */         (Map<binderType, AbstractSyntax.Element>)new LinkedHashMap();
/*  94 */       this.id = $outer.scala$util$parsing$ast$Binders$$_Binder().genId();
/*     */     }
/*     */     
/*     */     private Map<binderType, AbstractSyntax.Element> substitution() {
/*     */       return this.substitution;
/*     */     }
/*     */     
/*     */     public int id() {
/*  94 */       return this.id;
/*     */     }
/*     */     
/*     */     public Iterator<binderType> iterator() {
/* 100 */       return substitution().keysIterator();
/*     */     }
/*     */     
/*     */     public binderType apply(int i) {
/* 103 */       return (binderType)iterator().toList().apply(i);
/*     */     }
/*     */     
/*     */     public boolean binds(AbstractSyntax.NameElement b) {
/* 106 */       return substitution().contains(b);
/*     */     }
/*     */     
/*     */     public Option<Object> indexFor(AbstractSyntax.NameElement b) {
/* 108 */       Object object = new Object();
/*     */       try {
/* 109 */         Iterator iter = iterator().zipWithIndex();
/* 110 */         iter.withFilter((Function1)new Binders$Scope$$anonfun$indexFor$1(this)).foreach((Function1)new Binders$Scope$$anonfun$indexFor$2(this, b, (Scope<binderType>)object));
/*     */       } catch (NonLocalReturnControl nonLocalReturnControl2) {
/*     */         NonLocalReturnControl nonLocalReturnControl1;
/*     */         if ((nonLocalReturnControl1 = null).key() == object)
/*     */           return (Option<Object>)nonLocalReturnControl1.value(); 
/*     */       } 
/* 117 */       return (Option<Object>)scala.None$.MODULE$;
/*     */     }
/*     */     
/*     */     public class Binders$Scope$$anonfun$indexFor$1 extends AbstractFunction1<Tuple2<binderType, Object>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(Tuple2 check$ifrefutable$1) {
/*     */         boolean bool;
/*     */         if (check$ifrefutable$1 != null) {
/*     */           bool = true;
/*     */         } else {
/*     */           bool = false;
/*     */         } 
/*     */         return bool;
/*     */       }
/*     */       
/*     */       public Binders$Scope$$anonfun$indexFor$1(Binders.Scope $outer) {}
/*     */     }
/*     */     
/*     */     public class Binders$Scope$$anonfun$indexFor$2 extends AbstractFunction1<Tuple2<binderType, Object>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final AbstractSyntax.NameElement b$1;
/*     */       
/*     */       private final Object nonLocalReturnKey1$1;
/*     */       
/*     */       public final void apply(Tuple2 x$1) {
/*     */         if (x$1 != null) {
/*     */           String str = this.b$1.name();
/*     */           if (((AbstractSyntax.NameElement)x$1._1()).name() == null) {
/*     */             ((AbstractSyntax.NameElement)x$1._1()).name();
/*     */             if (str != null);
/*     */           } else if (((AbstractSyntax.NameElement)x$1._1()).name().equals(str)) {
/*     */             throw new NonLocalReturnControl(this.nonLocalReturnKey1$1, new Some(BoxesRunTime.boxToInteger(x$1._2$mcI$sp() + 1)));
/*     */           } 
/*     */         } else {
/*     */           throw new MatchError(x$1);
/*     */         } 
/*     */         throw new NonLocalReturnControl(this.nonLocalReturnKey1$1, new Some(BoxesRunTime.boxToInteger(x$1._2$mcI$sp() + 1)));
/*     */       }
/*     */       
/*     */       public Binders$Scope$$anonfun$indexFor$2(Binders.Scope $outer, AbstractSyntax.NameElement b$1, Object nonLocalReturnKey1$1) {}
/*     */     }
/*     */     
/*     */     public void addBinder(AbstractSyntax.NameElement b) {
/* 126 */       substitution().$plus$eq(scala.Predef$Pair$.MODULE$.apply(b, b));
/*     */     }
/*     */     
/*     */     public boolean canAddBinder(AbstractSyntax.NameElement b) {
/* 136 */       return !binds((binderType)b);
/*     */     }
/*     */     
/*     */     public void substitute(AbstractSyntax.NameElement b, AbstractSyntax.Element value) {
/* 148 */       substitution().update(b, value);
/*     */     }
/*     */     
/*     */     public AbstractSyntax.Element getElementFor(AbstractSyntax.NameElement b) {
/* 154 */       return (AbstractSyntax.Element)substitution().apply(b);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 156 */       return (new StringBuilder()).append(iterator().toList().mkString("[", ", ", "]")).append("!").append(BoxesRunTime.boxToInteger(id())).toString();
/*     */     }
/*     */     
/*     */     public List<String> bindersToString() {
/* 159 */       return iterator().map((Function1)new Binders$Scope$$anonfun$bindersToString$1(this)).toList();
/*     */     }
/*     */     
/*     */     public class Binders$Scope$$anonfun$bindersToString$1 extends AbstractFunction1<binderType, String> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final String apply(AbstractSyntax.NameElement b) {
/* 159 */         return (new StringBuilder()).append(scala.runtime.StringAdd$.MODULE$.$plus$extension(scala.Predef$.MODULE$.any2stringadd(b), "!")).append(BoxesRunTime.boxToInteger(this.$outer.id())).toString();
/*     */       }
/*     */       
/*     */       public Binders$Scope$$anonfun$bindersToString$1(Binders.Scope $outer) {}
/*     */     }
/*     */     
/*     */     public Scope<binderType> allowForwardRef() {
/* 162 */       return this;
/*     */     }
/*     */     
/*     */     public Scope<binderType> nested() {
/* 167 */       return this;
/*     */     }
/*     */     
/*     */     public void onEnter() {}
/*     */     
/*     */     public void onLeft() {}
/*     */   }
/*     */   
/*     */   public class BoundElement$ implements Serializable {
/*     */     public final String toString() {
/* 188 */       return "BoundElement";
/*     */     }
/*     */     
/*     */     public <boundElement extends AbstractSyntax.NameElement> Binders.BoundElement<boundElement> apply(AbstractSyntax.NameElement el, Binders.Scope<boundElement> scope) {
/* 188 */       return new Binders.BoundElement<boundElement>(this.$outer, (boundElement)el, scope);
/*     */     }
/*     */     
/*     */     public <boundElement extends AbstractSyntax.NameElement> Option<Tuple2<boundElement, Binders.Scope<boundElement>>> unapply(Binders.BoundElement x$0) {
/* 188 */       return (x$0 == null) ? (Option<Tuple2<boundElement, Binders.Scope<boundElement>>>)scala.None$.MODULE$ : (Option<Tuple2<boundElement, Binders.Scope<boundElement>>>)new Some(new Tuple2(x$0.el(), x$0.scope()));
/*     */     }
/*     */     
/*     */     private Object readResolve() {
/* 188 */       return this.$outer.BoundElement();
/*     */     }
/*     */     
/*     */     public BoundElement$(Binders $outer) {}
/*     */   }
/*     */   
/*     */   public class BoundElement<boundElement extends AbstractSyntax.NameElement> implements AbstractSyntax.NameElement, Proxy, BindingSensitive, Product, Serializable {
/*     */     private final boundElement el;
/*     */     
/*     */     private final Binders.Scope<boundElement> scope;
/*     */     
/*     */     private Position pos;
/*     */     
/*     */     public int hashCode() {
/* 188 */       return Proxy.class.hashCode(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/* 188 */       return Proxy.class.equals(this, that);
/*     */     }
/*     */     
/*     */     public Position pos() {
/* 188 */       return this.pos;
/*     */     }
/*     */     
/*     */     public void pos_$eq(Position x$1) {
/* 188 */       this.pos = x$1;
/*     */     }
/*     */     
/*     */     public Positional setPos(Position newpos) {
/* 188 */       return Positional.class.setPos(this, newpos);
/*     */     }
/*     */     
/*     */     public boundElement el() {
/* 188 */       return this.el;
/*     */     }
/*     */     
/*     */     public Binders.Scope<boundElement> scope() {
/* 188 */       return this.scope;
/*     */     }
/*     */     
/*     */     public <boundElement extends AbstractSyntax.NameElement> BoundElement<boundElement> copy(AbstractSyntax.NameElement el, Binders.Scope<boundElement> scope) {
/* 188 */       return new BoundElement(scala$util$parsing$ast$Binders$BoundElement$$$outer(), (boundElement)el, scope);
/*     */     }
/*     */     
/*     */     public <boundElement extends AbstractSyntax.NameElement> boundElement copy$default$1() {
/* 188 */       return el();
/*     */     }
/*     */     
/*     */     public <boundElement extends AbstractSyntax.NameElement> Binders.Scope<boundElement> copy$default$2() {
/* 188 */       return scope();
/*     */     }
/*     */     
/*     */     public String productPrefix() {
/* 188 */       return "BoundElement";
/*     */     }
/*     */     
/*     */     public int productArity() {
/* 188 */       return 2;
/*     */     }
/*     */     
/*     */     public Object productElement(int x$1) {
/* 188 */       switch (x$1) {
/*     */         default:
/* 188 */           throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(x$1).toString());
/*     */         case 1:
/*     */         
/*     */         case 0:
/*     */           break;
/*     */       } 
/* 188 */       return el();
/*     */     }
/*     */     
/*     */     public Iterator<Object> productIterator() {
/* 188 */       return scala.runtime.ScalaRunTime$.MODULE$.typedProductIterator(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object x$1) {
/* 188 */       return x$1 instanceof BoundElement;
/*     */     }
/*     */     
/*     */     public BoundElement(Binders $outer, AbstractSyntax.NameElement el, Binders.Scope<boundElement> scope) {
/* 188 */       Positional.class.$init$(this);
/* 188 */       AbstractSyntax.NameElement$class.$init$(this);
/* 188 */       Proxy.class.$init$(this);
/* 188 */       Product.class.$init$(this);
/*     */     }
/*     */     
/*     */     public AbstractSyntax.Element self() {
/* 193 */       return scope().getElementFor(el());
/*     */     }
/*     */     
/*     */     public String name() {
/* 195 */       return ((AbstractSyntax.NameElement)self()).name();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 198 */       return (new StringBuilder()).append(Proxy.class.toString(this)).append("@").append(BoxesRunTime.boxToInteger(scope().id())).toString();
/*     */     }
/*     */     
/*     */     public <t extends AbstractSyntax.NameElement> boolean alpha_$eq$eq(BoundElement other) {
/* 200 */       Option option = other.scope().indexFor(other.el());
/* 200 */       if (scope().indexFor(el()) == null) {
/* 200 */         scope().indexFor(el());
/* 200 */         if (option != null);
/* 200 */       } else if (scope().indexFor(el()).equals(option)) {
/*     */       
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class UnboundElement<N extends AbstractSyntax.NameElement> implements AbstractSyntax.NameElement {
/*     */     private final N el;
/*     */     
/*     */     private Position pos;
/*     */     
/*     */     public boolean equals(Object that) {
/* 204 */       return AbstractSyntax.NameElement$class.equals(this, that);
/*     */     }
/*     */     
/*     */     public Position pos() {
/* 204 */       return this.pos;
/*     */     }
/*     */     
/*     */     public void pos_$eq(Position x$1) {
/* 204 */       this.pos = x$1;
/*     */     }
/*     */     
/*     */     public Positional setPos(Position newpos) {
/* 204 */       return Positional.class.setPos(this, newpos);
/*     */     }
/*     */     
/*     */     private N el() {
/* 204 */       return this.el;
/*     */     }
/*     */     
/*     */     public UnboundElement(Binders $outer, AbstractSyntax.NameElement el) {
/* 204 */       Positional.class.$init$(this);
/* 204 */       AbstractSyntax.NameElement$class.$init$(this);
/*     */     }
/*     */     
/*     */     public String name() {
/* 205 */       return (new StringBuilder()).append(el().name()).append("@??").toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public class UnderBinder<binderType extends AbstractSyntax.NameElement, elementT> implements AbstractSyntax.Element, BindingSensitive {
/*     */     private final Binders.Scope<binderType> scope;
/*     */     
/*     */     private final elementT scala$util$parsing$ast$Binders$$element;
/*     */     
/*     */     private final Function1<elementT, Mappable.Mappable<elementT>> evidence$5;
/*     */     
/*     */     private Position pos;
/*     */     
/*     */     public Position pos() {
/* 213 */       return this.pos;
/*     */     }
/*     */     
/*     */     public void pos_$eq(Position x$1) {
/* 213 */       this.pos = x$1;
/*     */     }
/*     */     
/*     */     public Positional setPos(Position newpos) {
/* 213 */       return Positional.class.setPos(this, newpos);
/*     */     }
/*     */     
/*     */     public Binders.Scope<binderType> scope() {
/* 213 */       return this.scope;
/*     */     }
/*     */     
/*     */     public elementT scala$util$parsing$ast$Binders$$element() {
/* 213 */       return this.scala$util$parsing$ast$Binders$$element;
/*     */     }
/*     */     
/*     */     public UnderBinder(Binders $outer, Binders.Scope<binderType> scope, Object element, Function1<elementT, Mappable.Mappable<elementT>> evidence$5) {
/* 213 */       Positional.class.$init$(this);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 214 */       return (new StringBuilder()).append("(").append(scope().toString()).append(") in { ").append(scala$util$parsing$ast$Binders$$element().toString()).append(" }").toString();
/*     */     }
/*     */     
/*     */     public elementT cloneElementWithSubst(Map subst) {
/* 242 */       return ((Mappable.Mappable<elementT>)this.evidence$5.apply(scala$util$parsing$ast$Binders$$element())).gmap(new Binders$UnderBinder$$anon$5(this, (UnderBinder<binderType, elementT>)subst));
/*     */     }
/*     */     
/*     */     public class Binders$UnderBinder$$anon$5 implements Mappable.Mapper {
/*     */       private final Map subst$1;
/*     */       
/*     */       public Binders$UnderBinder$$anon$5(Binders.UnderBinder $outer, Map subst$1) {}
/*     */       
/*     */       public <t> t apply(Object x, Function1 evidence$6) {
/*     */         Object object;
/* 242 */         if (x instanceof AbstractSyntax.NameElement && ((AbstractSyntax.NameElement)x).scala$util$parsing$ast$AbstractSyntax$NameElement$$$outer() == this.$outer.scala$util$parsing$ast$Binders$UnderBinder$$$outer() && this.subst$1.contains(x)) {
/* 242 */           object = this.subst$1.get(x);
/*     */         } else {
/* 245 */           object = x;
/*     */         } 
/*     */         return (t)object;
/*     */       }
/*     */     }
/*     */     
/*     */     public elementT cloneElementNoBoundElements() {
/* 249 */       return ((Mappable.Mappable<elementT>)this.evidence$5.apply(scala$util$parsing$ast$Binders$$element())).gmap(new Binders$UnderBinder$$anon$6(this));
/*     */     }
/*     */     
/*     */     public class Binders$UnderBinder$$anon$6 implements Mappable.Mapper {
/*     */       public Binders$UnderBinder$$anon$6(Binders.UnderBinder $outer) {}
/*     */       
/*     */       public <t> t apply(Object x, Function1 evidence$7) {
/*     */         Object object;
/* 249 */         if (x instanceof Binders.BoundElement && ((Binders.BoundElement)x).scala$util$parsing$ast$Binders$BoundElement$$$outer() == this.$outer.scala$util$parsing$ast$Binders$UnderBinder$$$outer()) {
/* 249 */           Binders.BoundElement<AbstractSyntax.NameElement> boundElement = (Binders.BoundElement)x;
/* 250 */           object = new Binders.UnboundElement<AbstractSyntax.NameElement>(this.$outer.scala$util$parsing$ast$Binders$UnderBinder$$$outer(), boundElement.el());
/*     */         } else {
/* 251 */           object = x;
/*     */         } 
/*     */         return (t)object;
/*     */       }
/*     */     }
/*     */     
/*     */     public elementT extract() {
/* 254 */       return cloneElementNoBoundElements();
/*     */     }
/*     */     
/*     */     public elementT extract(Map<AbstractSyntax.NameElement, AbstractSyntax.NameElement> subst) {
/* 255 */       return cloneElementWithSubst(subst);
/*     */     }
/*     */     
/*     */     public String elementToString() {
/* 258 */       return scala$util$parsing$ast$Binders$$element().toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public class UnderBinder$ {
/*     */     public class Binders$UnderBinder$$anon$5 implements Mappable.Mapper {
/*     */       private final Map subst$1;
/*     */       
/*     */       public Binders$UnderBinder$$anon$5(Binders.UnderBinder $outer, Map subst$1) {}
/*     */       
/*     */       public <t> t apply(Object x, Function1 evidence$6) {
/*     */         Object object;
/*     */         if (x instanceof AbstractSyntax.NameElement && ((AbstractSyntax.NameElement)x).scala$util$parsing$ast$AbstractSyntax$NameElement$$$outer() == this.$outer.scala$util$parsing$ast$Binders$UnderBinder$$$outer() && this.subst$1.contains(x)) {
/*     */           object = this.subst$1.get(x);
/*     */         } else {
/*     */           object = x;
/*     */         } 
/*     */         return (t)object;
/*     */       }
/*     */     }
/*     */     
/*     */     public class Binders$UnderBinder$$anon$6 implements Mappable.Mapper {
/*     */       public Binders$UnderBinder$$anon$6(Binders.UnderBinder $outer) {}
/*     */       
/*     */       public <t> t apply(Object x, Function1 evidence$7) {
/*     */         Object object;
/*     */         if (x instanceof Binders.BoundElement && ((Binders.BoundElement)x).scala$util$parsing$ast$Binders$BoundElement$$$outer() == this.$outer.scala$util$parsing$ast$Binders$UnderBinder$$$outer()) {
/*     */           Binders.BoundElement<AbstractSyntax.NameElement> boundElement = (Binders.BoundElement)x;
/*     */           object = new Binders.UnboundElement<AbstractSyntax.NameElement>(this.$outer.scala$util$parsing$ast$Binders$UnderBinder$$$outer(), boundElement.el());
/*     */         } else {
/*     */           object = x;
/*     */         } 
/*     */         return (t)object;
/*     */       }
/*     */     }
/*     */     
/*     */     public UnderBinder$(Binders $outer) {}
/*     */     
/*     */     public <binderType extends AbstractSyntax.NameElement, elementT> Binders.UnderBinder<binderType, elementT> apply(Binders.Scope<binderType> scope, Object element, Function1<elementT, Mappable.Mappable<elementT>> evidence$11) {
/* 285 */       return new Binders.UnderBinder<binderType, elementT>(this.$outer, scope, (elementT)element, evidence$11);
/*     */     }
/*     */     
/*     */     public <bt extends AbstractSyntax.NameElement, elementT> Binders.UnderBinder<bt, elementT> unit(Object x, Function1<elementT, Mappable.Mappable<elementT>> evidence$12) {
/* 286 */       Binders.Scope<AbstractSyntax.NameElement> scope = new Binders.Scope<AbstractSyntax.NameElement>(this.$outer);
/* 286 */       UnderBinder$ underBinder$ = this.$outer.UnderBinder();
/* 286 */       return new Binders.UnderBinder<bt, elementT>(underBinder$.$outer, (Binders.Scope)scope, (elementT)x, evidence$12);
/*     */     }
/*     */   }
/*     */   
/*     */   public class Binders$$anon$7 implements Mappable.Mappable<UnderBinder<bt, st>> {
/*     */     private final Binders.UnderBinder ub$1;
/*     */     
/*     */     public final Function1 evidence$8$1;
/*     */     
/*     */     private final Function1 evidence$9$1;
/*     */     
/*     */     public Binders.UnderBinder<bt, st> everywhere(Mappable.Mapper f, Function1 c) {
/*     */       return (Binders.UnderBinder<bt, st>)Mappable.Mappable$class.everywhere(this, f, c);
/*     */     }
/*     */     
/*     */     public Binders$$anon$7(Binders $outer, Binders.UnderBinder ub$1, Function1 evidence$8$1, Function1 evidence$9$1) {
/*     */       Mappable.Mappable$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Binders.UnderBinder<bt, st> gmap(Mappable.Mapper f) {
/*     */       Function1<st, Mappable.Mappable<st>> function1 = this.evidence$9$1;
/*     */       st st = (st)f.apply(this.ub$1.scala$util$parsing$ast$Binders$$element(), this.evidence$9$1);
/*     */       Binders.Scope<bt> scope = f.<Binders.Scope>apply(this.ub$1.scope(), (Function1<Binders.Scope, Mappable.Mappable<Binders.Scope>>)new Binders$$anon$7$$anonfun$gmap$4(this));
/*     */       Binders.UnderBinder$ underBinder$ = this.$outer.UnderBinder();
/*     */       return new Binders.UnderBinder<bt, st>(underBinder$.$outer, scope, st, function1);
/*     */     }
/*     */     
/*     */     public class Binders$$anon$7$$anonfun$gmap$4 extends AbstractFunction1<Binders.Scope<bt>, Mappable.Mappable<Binders.Scope<bt>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Mappable.Mappable<Binders.Scope<bt>> apply(Binders.Scope<AbstractSyntax.NameElement> scope) {
/*     */         return (Mappable.Mappable)this.$outer.scala$util$parsing$ast$Binders$$anon$$$outer().ScopeIsMappable(scope, this.$outer.evidence$8$1);
/*     */       }
/*     */       
/*     */       public Binders$$anon$7$$anonfun$gmap$4(Binders$$anon$7 $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Binders$$anon$8 implements Mappable.Mappable<Scope<bt>> {
/*     */     private final Binders.Scope scope$1;
/*     */     
/*     */     public final Function1 evidence$10$1;
/*     */     
/*     */     public Binders.Scope<bt> everywhere(Mappable.Mapper f, Function1 c) {
/*     */       return (Binders.Scope<bt>)Mappable.Mappable$class.everywhere(this, f, c);
/*     */     }
/*     */     
/*     */     public Binders$$anon$8(Binders $outer, Binders.Scope scope$1, Function1 evidence$10$1) {
/*     */       Mappable.Mappable$class.$init$(this);
/*     */     }
/*     */     
/*     */     public Binders.Scope<bt> gmap(Mappable.Mapper f) {
/*     */       Binders.Scope<AbstractSyntax.NameElement> newScope = new Binders.Scope<AbstractSyntax.NameElement>(this.$outer);
/*     */       this.scope$1.foreach((Function1)new Binders$$anon$8$$anonfun$gmap$5(this, f, newScope));
/*     */       return (Binders.Scope)newScope;
/*     */     }
/*     */     
/*     */     public class Binders$$anon$8$$anonfun$gmap$5 extends AbstractFunction1<bt, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Mappable.Mapper f$3;
/*     */       
/*     */       private final Binders.Scope newScope$1;
/*     */       
/*     */       public final void apply(AbstractSyntax.NameElement b) {
/*     */         this.newScope$1.addBinder(this.f$3.<AbstractSyntax.NameElement>apply(b, this.$outer.evidence$10$1));
/*     */       }
/*     */       
/*     */       public Binders$$anon$8$$anonfun$gmap$5(Binders$$anon$8 $outer, Mappable.Mapper f$3, Binders.Scope newScope$1) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class Binders$$anon$9 implements Mappable.Mappable<AbstractSyntax.NameElement> {
/*     */     private final AbstractSyntax.NameElement self$1;
/*     */     
/*     */     public AbstractSyntax.NameElement everywhere(Mappable.Mapper f, Function1 c) {
/*     */       return (AbstractSyntax.NameElement)Mappable.Mappable$class.everywhere(this, f, c);
/*     */     }
/*     */     
/*     */     public Binders$$anon$9(Binders $outer, AbstractSyntax.NameElement self$1) {
/*     */       Mappable.Mappable$class.$init$(this);
/*     */     }
/*     */     
/*     */     public AbstractSyntax.NameElement gmap(Mappable.Mapper f) {
/*     */       AbstractSyntax.NameElement nameElement = this.self$1;
/*     */       if (nameElement instanceof Binders.BoundElement)
/*     */         Binders.BoundElement boundElement = (Binders.BoundElement)nameElement; 
/*     */       return this.$outer.<AbstractSyntax.NameElement>UserNameElementIsMappable(this.self$1).gmap(f);
/*     */     }
/*     */     
/*     */     public class Binders$$anon$9$$anonfun$gmap$6 extends AbstractFunction1<AbstractSyntax.NameElement, Mappable.Mappable<AbstractSyntax.NameElement>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Mappable.Mappable<AbstractSyntax.NameElement> apply(AbstractSyntax.NameElement self) {
/*     */         return this.$outer.scala$util$parsing$ast$Binders$$anon$$$outer().NameElementIsMappable(self);
/*     */       }
/*     */       
/*     */       public Binders$$anon$9$$anonfun$gmap$6(Binders$$anon$9 $outer) {}
/*     */     }
/*     */     
/*     */     public class Binders$$anon$9$$anonfun$gmap$7 extends AbstractFunction1<Binders.Scope<AbstractSyntax.NameElement>, Mappable.Mappable<Binders.Scope<AbstractSyntax.NameElement>>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Mappable.Mappable<Binders.Scope<AbstractSyntax.NameElement>> apply(Binders.Scope<AbstractSyntax.NameElement> scope) {
/*     */         return this.$outer.scala$util$parsing$ast$Binders$$anon$$$outer().ScopeIsMappable(scope, (Function1<AbstractSyntax.NameElement, Mappable.Mappable<AbstractSyntax.NameElement>>)new Binders$$anon$9$$anonfun$gmap$7$$anonfun$apply$1(this));
/*     */       }
/*     */       
/*     */       public Binders$$anon$9$$anonfun$gmap$7(Binders$$anon$9 $outer) {}
/*     */       
/*     */       public class Binders$$anon$9$$anonfun$gmap$7$$anonfun$apply$1 extends AbstractFunction1<AbstractSyntax.NameElement, Mappable.Mappable<AbstractSyntax.NameElement>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final Mappable.Mappable<AbstractSyntax.NameElement> apply(AbstractSyntax.NameElement self) {
/*     */           return this.$outer.$outer.scala$util$parsing$ast$Binders$$anon$$$outer().NameElementIsMappable(self);
/*     */         }
/*     */         
/*     */         public Binders$$anon$9$$anonfun$gmap$7$$anonfun$apply$1(Binders$$anon$9$$anonfun$gmap$7 $outer) {}
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class Binders$$anonfun$sequence$1 extends AbstractFunction1<List<st>, Mappable.Mappable<List<st>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 evidence$13$1;
/*     */     
/*     */     public final Mappable.Mappable<List<st>> apply(List<st> xs) {
/* 298 */       return this.$outer.ListIsMappable(xs, this.evidence$13$1);
/*     */     }
/*     */     
/*     */     public Binders$$anonfun$sequence$1(Binders $outer, Function1 evidence$13$1) {}
/*     */   }
/*     */   
/*     */   public class Binders$$anonfun$sequence$2 extends AbstractFunction1<UnderBinder<bt, st>, st> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final st apply(Binders.UnderBinder x$2) {
/* 299 */       return (st)x$2.scala$util$parsing$ast$Binders$$element();
/*     */     }
/*     */     
/*     */     public Binders$$anonfun$sequence$2(Binders $outer) {}
/*     */   }
/*     */   
/*     */   public class Binders$$anonfun$sequence$3 extends AbstractFunction1<List<st>, Mappable.Mappable<List<st>>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 evidence$13$1;
/*     */     
/*     */     public final Mappable.Mappable<List<st>> apply(List<st> xs) {
/* 299 */       return this.$outer.ListIsMappable(xs, this.evidence$13$1);
/*     */     }
/*     */     
/*     */     public Binders$$anonfun$sequence$3(Binders $outer, Function1 evidence$13$1) {}
/*     */   }
/*     */   
/*     */   public class Binders$$anonfun$unsequence$1 extends AbstractFunction1<st, UnderBinder<bt, st>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Binders.UnderBinder orig$1;
/*     */     
/*     */     private final Function1 evidence$14$1;
/*     */     
/*     */     public final Binders.UnderBinder<bt, st> apply(Object sc) {
/* 303 */       return this.$outer.UnderBinder().apply(this.orig$1.scope(), (st)sc, this.evidence$14$1);
/*     */     }
/*     */     
/*     */     public Binders$$anonfun$unsequence$1(Binders $outer, Binders.UnderBinder orig$1, Function1 evidence$14$1) {}
/*     */   }
/*     */   
/*     */   public abstract class BinderEnv {
/*     */     public BinderEnv(Binders $outer) {}
/*     */     
/*     */     public <a extends AbstractSyntax.NameElement> BinderEnv extend(AbstractSyntax.NameElement v, Binders.Scope x) {
/* 315 */       return new Binders$BinderEnv$$anon$10(this, v, x);
/*     */     }
/*     */     
/*     */     public abstract <A extends AbstractSyntax.NameElement> Option<Binders.Scope<A>> apply(A param1A);
/*     */     
/*     */     public class Binders$BinderEnv$$anon$10 extends BinderEnv {
/*     */       private final AbstractSyntax.NameElement v$1;
/*     */       
/*     */       private final Binders.Scope x$3;
/*     */       
/*     */       public Binders$BinderEnv$$anon$10(Binders.BinderEnv $outer, AbstractSyntax.NameElement v$1, Binders.Scope x$3) {
/* 315 */         super($outer.scala$util$parsing$ast$Binders$BinderEnv$$$outer());
/*     */       }
/*     */       
/*     */       public <b extends AbstractSyntax.NameElement> Option<Binders.Scope<b>> apply(AbstractSyntax.NameElement w) {
/* 317 */         AbstractSyntax.NameElement nameElement = this.v$1;
/* 317 */         if (w == null) {
/* 317 */           if (nameElement != null);
/* 317 */         } else if (w.equals(nameElement)) {
/*     */         
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class EmptyBinderEnv$ extends BinderEnv {
/*     */     public EmptyBinderEnv$(Binders $outer) {
/* 322 */       super($outer);
/*     */     }
/*     */     
/*     */     public <A extends AbstractSyntax.NameElement> Option<Binders.Scope<A>> apply(AbstractSyntax.NameElement v) {
/* 323 */       return (Option<Binders.Scope<A>>)scala.None$.MODULE$;
/*     */     }
/*     */   }
/*     */   
/*     */   public class Binders$$anon$11 implements ReturnAndDo<T> {
/*     */     private final T r;
/*     */     
/*     */     public Binders$$anon$11(Binders $outer, Object result$1) {
/* 339 */       this.r = (T)result$1;
/*     */     }
/*     */     
/*     */     private T r() {
/* 339 */       return this.r;
/*     */     }
/*     */     
/*     */     public T andDo(Function0 block) {
/* 340 */       block.apply$mcV$sp();
/* 340 */       return r();
/*     */     }
/*     */   }
/*     */   
/*     */   public class _Binder$ {
/* 344 */     private int scala$util$parsing$ast$Binders$_Binder$$currentId = 0;
/*     */     
/*     */     public int scala$util$parsing$ast$Binders$_Binder$$currentId() {
/* 344 */       return this.scala$util$parsing$ast$Binders$_Binder$$currentId;
/*     */     }
/*     */     
/*     */     public void scala$util$parsing$ast$Binders$_Binder$$currentId_$eq(int x$1) {
/* 344 */       this.scala$util$parsing$ast$Binders$_Binder$$currentId = x$1;
/*     */     }
/*     */     
/*     */     public int genId() {
/* 345 */       return BoxesRunTime.unboxToInt(this.$outer.<Integer>return_(BoxesRunTime.boxToInteger(scala$util$parsing$ast$Binders$_Binder$$currentId())).andDo((Function0<BoxedUnit>)new Binders$_Binder$$anonfun$genId$1(this)));
/*     */     }
/*     */     
/*     */     public _Binder$(Binders $outer) {}
/*     */     
/*     */     public static class Binders$_Binder$$anonfun$genId$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final void apply() {
/* 345 */         this.$outer.scala$util$parsing$ast$Binders$_Binder$$currentId_$eq(this.$outer.scala$util$parsing$ast$Binders$_Binder$$currentId() + 1);
/*     */       }
/*     */       
/*     */       public void apply$mcV$sp() {
/* 345 */         this.$outer.scala$util$parsing$ast$Binders$_Binder$$currentId_$eq(this.$outer.scala$util$parsing$ast$Binders$_Binder$$currentId() + 1);
/*     */       }
/*     */       
/*     */       public Binders$_Binder$$anonfun$genId$1(Binders._Binder$ $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public interface ReturnAndDo<T> {
/*     */     T andDo(Function0<BoxedUnit> param1Function0);
/*     */   }
/*     */   
/*     */   public interface BindingSensitive {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\ast\Binders.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */