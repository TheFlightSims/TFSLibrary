/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Array$;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.PartialFunction;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple3;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.FilterMonadic;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.generic.GenericTraversableTemplate;
/*     */ import scala.collection.generic.SliceInterval;
/*     */ import scala.collection.immutable.IndexedSeq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Map;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Range$;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.ArrayOps;
/*     */ import scala.collection.mutable.ArraySeq;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.HashMap;
/*     */ import scala.collection.mutable.HashMap$;
/*     */ import scala.collection.mutable.HashSet;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.ParSeq;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Numeric$IntIsIntegral$;
/*     */ import scala.math.Ordering;
/*     */ import scala.math.Ordering$Int$;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.IntRef;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.RichInt$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021eeaB\001\003!\003\r\ta\002\002\b'\026\fH*[6f\025\t\031A!\001\006d_2dWm\031;j_:T\021!B\001\006g\016\fG.Y\002\001+\rA1CG\n\006\001%iAd\b\t\003\025-i\021\001B\005\003\031\021\0211!\0218z!\021qq\"E\r\016\003\tI!\001\005\002\003\031%#XM]1cY\026d\025n[3\021\005I\031B\002\001\003\007)\001!)\031A\013\003\003\005\013\"AF\005\021\005)9\022B\001\r\005\005\035qu\016\0365j]\036\004\"A\005\016\005\rm\001AQ1\001\026\005\021\021V\r\035:\021\t9i\022#G\005\003=\t\021!bR3o'\026\fH*[6f!\021q\001%\005\022\n\005\005\022!A\004)be\006dG.\0327ju\006\024G.\032\t\004G\031\nR\"\001\023\013\005\025\022\021\001\0039be\006dG.\0327\n\005\035\"#A\002)beN+\027\017C\003*\001\021\005!&\001\004%S:LG\017\n\013\002WA\021!\002L\005\003[\021\021A!\0268ji\"1q\006\001Q\005RA\na\002\0365jg\016{G\016\\3di&|g.F\0012!\rq!'E\005\003g\t\0211aU3r\021\031)\004\001)C)m\005aAo\\\"pY2,7\r^5p]R\021\021g\016\005\006qQ\002\r!G\001\005e\026\004(\017C\003;\001\031\0051(\001\004mK:<G\017[\013\002yA\021!\"P\005\003}\021\0211!\0238u\021\025\001\005A\"\001B\003\025\t\007\017\0357z)\t\t\"\tC\003D\001\007A(A\002jIbDa!\022\001!\n#2\025a\0039be\016{WNY5oKJ,\022a\022\t\005G!\013\"%\003\002JI\tA1i\\7cS:,'\017C\003L\001\021\005A*A\007mK:<G\017[\"p[B\f'/\032\013\003y5CQA\024&A\002q\n1\001\\3o\021\025\001\006\001\"\021R\003\035I7/R7qif,\022A\025\t\003\025MK!\001\026\003\003\017\t{w\016\\3b]\")a\013\001C!w\005!1/\033>f\021\025A\006\001\"\001Z\0035\031XmZ7f]RdUM\\4uQR\031AHW0\t\013m;\006\031\001/\002\003A\004BAC/\022%&\021a\f\002\002\n\rVt7\r^5p]FBQ\001Y,A\002q\nAA\032:p[\")!\r\001C\001G\006Q\021N\0343fq^CWM]3\025\007q\"W\rC\003\\C\002\007A\fC\003aC\002\007A\bC\003h\001\021\005\001.\001\bmCN$\030J\0343fq^CWM]3\025\007qJ'\016C\003\\M\002\007A\fC\003lM\002\007A(A\002f]\022DQ!\034\001\005\0029\fA\002]3s[V$\030\r^5p]N,\022a\034\t\004\035AL\022BA9\003\005!IE/\032:bi>\024\b\"B:\001\t\003!\030\001D2p[\nLg.\031;j_:\034HCA8v\021\0251(\0171\001=\003\005qg\001\002=\001\te\024q\002U3s[V$\030\r^5p]NLEO]\n\003oj\0042AD>\032\023\ta(A\001\tBEN$(/Y2u\023R,'/\031;pe\")ap\036C\001\0061A(\0338jiz\"\"!!\001\021\007\005\rq/D\001\001\021%\t9a^A!\002\023\tI!A\002yIE\002rACA\006\003\037\tY\"C\002\002\016\021\021a\001V;qY\026\024\004#BA\t\003/\tRBAA\n\025\r\t)BA\001\b[V$\030M\0317f\023\021\tI\"a\005\003\r\t+hMZ3s!\021Q\021Q\004\037\n\007\005}AAA\003BeJ\f\027\020\003\005\002$]\004\013\021BA\b\003\021)G.\\:\t\021\005\035r\017)A\005\0037\tA!\0333yg\"A\0211F<A\002\023%\021+\001\005`Q\006\034h*\032=u\021%\tyc\036a\001\n\023\t\t$\001\007`Q\006\034h*\032=u?\022*\027\017F\002,\003gA\021\"a\002\002.\005\005\t\031\001*\t\017\005]r\017)Q\005%\006Iq\f[1t\035\026DH\017\t\005\007\003w9H\021A)\002\017!\f7OT3yi\"9\021qH<\005\002\005\005\023\001\0028fqR$\022!\007\005\b\003\013:H\021BA$\003\021\031x/\0319\025\013-\nI%!\024\t\017\005-\0231\ta\001y\005\t\021\016C\004\002P\005\r\003\031\001\037\002\003)D\001\"a\025xA\023%\021QK\001\005S:LG\017\006\002\002\n\0311\021\021\f\001\005\0037\022qbQ8nE&t\027\r^5p]NLEO]\n\004\003/R\b\"\003<\002X\t\005\t\025!\003=\021\035q\030q\013C\001\003C\"B!a\031\002fA!\0211AA,\021\0311\030q\fa\001y!Q\021\021NA,\003\003\006I!a\033\002\007a$C\007E\005\013\003[\n\t(a\007\002\034%\031\021q\016\003\003\rQ+\b\017\\34!\021q\0211O\t\n\007\005U$A\001\006J]\022,\0070\0323TKFD!\"a\t\002X\t\007I\021BA=+\t\t\t\bC\005\002~\005]\003\025!\003\002r\005)Q\r\\7tA!Q\021\021QA,\005\004%I!a!\002\t\rtGo]\013\003\0037A\021\"a\"\002X\001\006I!a\007\002\013\rtGo\035\021\t\025\005-\025q\013b\001\n\023\t\031)\001\003ok6\034\b\"CAH\003/\002\013\021BA\016\003\025qW/\\:!\021)\t\031*a\026C\002\023%\0211Q\001\005_\03247\017C\005\002\030\006]\003\025!\003\002\034\005)qN\0324tA!I\0211FA,\001\004%I!\025\005\013\003_\t9\0061A\005\n\005uEcA\026\002 \"I\021qAAN\003\003\005\rA\025\005\t\003o\t9\006)Q\005%\"9\0211HA,\t\003\t\006\002CA \003/\"\t!!\021\t\021\005M\023q\013C\005\003S#\"!a\033\t\017\0055\006\001\"\001\0020\0069!/\032<feN,W#A\r\t\017\005M\006\001\"\001\0026\006Q!/\032<feN,W*\0319\026\r\005]\0261[A_)\021\tI,a6\025\t\005m\026\021\031\t\004%\005uFaBA`\003c\023\r!\006\002\005)\"\fG\017\003\005\002D\006E\0069AAc\003\t\021g\rE\005\002H\0065\027$!5\002<6\021\021\021\032\006\004\003\027\024\021aB4f]\026\024\030nY\005\005\003\037\fIM\001\007DC:\024U/\0337e\rJ|W\016E\002\023\003'$q!!6\0022\n\007QCA\001C\021!\tI.!-A\002\005m\027!\0014\021\013)i\026#!5\t\017\005}\007\001\"\001\002b\006y!/\032<feN,\027\n^3sCR|'/\006\002\002dB\031a\002]\t\t\017\005\035\b\001\"\001\002j\006Q1\017^1siN<\026\016\0365\026\t\005-\030\021 \013\006%\0065\0301 \005\t\003_\f)\0171\001\002r\006!A\017[1u!\025q\0211_A|\023\r\t)P\001\002\007\017\026t7+Z9\021\007I\tI\020B\004\002V\006\025(\031A\013\t\017\005u\030Q\035a\001y\0051qN\0324tKRDqA!\001\001\t\003\021\031!\001\005f]\022\034x+\033;i+\021\021)A!\004\025\007I\0239\001\003\005\002p\006}\b\031\001B\005!\025q\0211\037B\006!\r\021\"Q\002\003\b\003+\fyP1\001\026\021\035\021\t\002\001C\001\005'\tA\"\0338eKb|em\0257jG\026,BA!\006\003\036Q\031AHa\006\t\021\005=(q\002a\001\0053\001RADAz\0057\0012A\005B\017\t!\t)Na\004C\002\t}\021CA\t\n\021\035\021\t\002\001C\001\005G)BA!\n\003.Q)AHa\n\0030!A\021q\036B\021\001\004\021I\003E\003\017\003g\024Y\003E\002\023\005[!\001\"!6\003\"\t\007!q\004\005\007A\n\005\002\031\001\037\t\017\tM\002\001\"\001\0036\005\001B.Y:u\023:$W\r_(g'2L7-Z\013\005\005o\021y\004F\002=\005sA\001\"a<\0032\001\007!1\b\t\006\035\005M(Q\b\t\004%\t}B\001CAk\005c\021\rAa\b\t\017\tM\002\001\"\001\003DU!!Q\tB')\025a$q\tB(\021!\tyO!\021A\002\t%\003#\002\b\002t\n-\003c\001\n\003N\021A\021Q\033B!\005\004\021y\002\003\004l\005\003\002\r\001\020\005\b\005'\002A\021\001B+\0035\031wN\034;bS:\0348\013\\5dKV!!q\013B0)\r\021&\021\f\005\t\003_\024\t\0061\001\003\\A)a\"a=\003^A\031!Ca\030\005\017\005U'\021\013b\001+!9!1\r\001\005\002\t\025\024\001C2p]R\f\027N\\:\025\007I\0239\007C\004\003j\t\005\004\031A\005\002\t\025dW-\034\005\b\005[\002A\021\tB8\003\025)h.[8o+\031\021\tHa \003xQ!!1\017BA)\021\021)H!\037\021\007I\0219\bB\004\002@\n-$\031A\013\t\021\005\r'1\016a\002\005w\002\022\"a2\002Nf\021iH!\036\021\007I\021y\b\002\005\002V\n-$\031\001B\020\021!\tyOa\033A\002\t\r\005#\002\b\002t\nu\004b\002BD\001\021\005!\021R\001\005I&4g-\006\003\003\f\nMEcA\r\003\016\"A\021q\036BC\001\004\021y\tE\003\017\003g\024\t\nE\002\023\005'#\001\"!6\003\006\n\007!q\004\005\b\005/\003A\021\001BM\003%Ig\016^3sg\026\034G/\006\003\003\034\n\rFcA\r\003\036\"A\021q\036BK\001\004\021y\nE\003\017\003g\024\t\013E\002\023\005G#\001\"!6\003\026\n\007!q\004\005\b\005O\003A\021\002BU\003%y7mY\"pk:$8/\006\003\003,\nUF\003\002BW\005o\003r!!\005\0030\nMF(\003\003\0032\006M!aA'baB\031!C!.\005\017\005U'Q\025b\001+!A!\021\030BS\001\004\021Y,\001\002tcB!aB\rBZ\021\035\021y\f\001C\001\003_\013\001\002Z5ti&t7\r\036\005\b\005\007\004A\021\001Bc\003\025\001\030\r^2i+\031\0219M!6\003NRA!\021\032Bl\0053\024i\016\006\003\003L\n=\007c\001\n\003N\0229\021q\030Ba\005\004)\002\002CAb\005\003\004\035A!5\021\023\005\035\027QZ\r\003T\n-\007c\001\n\003V\022A\021Q\033Ba\005\004\021y\002\003\004a\005\003\004\r\001\020\005\t\005\007\024\t\r1\001\003\\B)a\"a=\003T\"9!q\034Ba\001\004a\024\001\003:fa2\f7-\0323\t\017\t\r\b\001\"\001\003f\0069Q\017\0353bi\026$WC\002Bt\005k\024i\017\006\004\003j\n](1 \013\005\005W\024y\017E\002\023\005[$q!a0\003b\n\007Q\003\003\005\002D\n\005\b9\001By!%\t9-!4\032\005g\024Y\017E\002\023\005k$\001\"!6\003b\n\007!q\004\005\b\005s\024\t\0171\001=\003\025Ig\016Z3y\021!\021IG!9A\002\tM\bb\002B\000\001\021\0051\021A\001\fIAdWo\035\023d_2|g.\006\004\004\004\rE1\021\002\013\005\007\013\031\031\002\006\003\004\b\r-\001c\001\n\004\n\0219\021q\030B\005\004)\002\002CAb\005{\004\035a!\004\021\023\005\035\027QZ\r\004\020\r\035\001c\001\n\004\022\021A\021Q\033B\005\004\021y\002\003\005\003j\tu\b\031AB\b\021\035\0319\002\001C\001\0073\t1\002J2pY>tG\005\0357vgV111DB\025\007C!Ba!\b\004,Q!1qDB\022!\r\0212\021\005\003\b\003\033)B1\001\026\021!\t\031m!\006A\004\r\025\002#CAd\003\033L2qEB\020!\r\0212\021\006\003\t\003+\034)B1\001\003 !A!\021NB\013\001\004\0319\003C\004\0040\001!\ta!\r\002\013A\fG\rV8\026\r\rM2\021IB\035)\031\031)da\021\004FQ!1qGB\036!\r\0212\021\b\003\b\003\033iC1\001\026\021!\t\031m!\fA\004\ru\002#CAd\003\033L2qHB\034!\r\0212\021\t\003\t\003+\034iC1\001\003 !1aj!\fA\002qB\001B!\033\004.\001\0071q\b\005\b\007\023\002A\021AB&\003-\031wN\035:fgB|g\016Z:\026\t\r531\f\013\005\007\037\032i\006F\002S\007#BqaWB$\001\004\031\031\006E\004\013\007+\n2\021\f*\n\007\r]CAA\005Gk:\034G/[8oeA\031!ca\027\005\017\005U7q\tb\001+!A\021q^B$\001\004\031y\006E\003\017\003g\034I\006C\004\004d\001!\ta!\032\002\021M|'\017^,ji\"$2!GB4\021!\031Ig!\031A\002\r-\024A\0017u!\031Q1QK\t\022%\"91q\016\001\005\002\rE\024AB:peR\024\0250\006\003\004t\r%E\003BB;\007\027#2!GB<\021!\031Ih!\034A\004\rm\024aA8sIB11QPBB\007\017k!aa \013\007\r\005E!\001\003nCRD\027\002BBC\007\022\001b\024:eKJLgn\032\t\004%\r%EaBAk\007[\022\r!\006\005\t\0033\034i\0071\001\004\016B)!\"X\t\004\b\"91\021\023\001\005\002\rM\025AB:peR,G-\006\003\004\026\016uEcA\r\004\030\"A1\021PBH\001\b\031I\n\005\004\004~\r\r51\024\t\004%\ruE\001CAk\007\037\023\rAa\b\t\r\r\005\006\001\"\0211\003\025!xnU3r\021\035\031)\013\001C\001\007O\013q!\0338eS\016,7/\006\002\004*B!11VBY\033\t\031iKC\002\0040\n\t\021\"[7nkR\f'\r\\3\n\t\rM6Q\026\002\006%\006tw-\032\005\b\007o\003A\021IB]\003\0211\030.Z<\026\005\rm&CBB_\007\003\0349MB\004\004@\016U\006aa/\003\031q\022XMZ5oK6,g\016\036 \021\007)\031\031-C\002\004F\022\021a!\0218z%\0264\007#\002\b\004JFI\022bABf\005\t91+Z9WS\026<\bbBB\\\001\021\0053q\032\013\007\007\017\034\tna5\t\r\001\034i\r1\001=\021\035\031)n!4A\002q\nQ!\0368uS2Dqa!7\001\t\003\032Y.\001\005u_N#(/\0338h)\t\031i\016\005\003\004`\016\025hb\001\006\004b&\03111\035\003\002\rA\023X\rZ3g\023\021\0319o!;\003\rM#(/\0338h\025\r\031\031\017\002\t\005\035\001\t\022dB\004\004p\nA\ta!=\002\017M+\027\017T5lKB\031aba=\007\r\005\021\001\022AB{'\021\031\031p!1\t\017y\034\031\020\"\001\004zR\0211\021\037\005\t\007{\034\031\020\"\003\004\000\006y1.\0349PaRLW.\033>f/>\024H-\006\003\005\002\021\035AC\003C\002\t\023!y\001b\005\005\030A)a\"a\035\005\006A\031!\003b\002\005\017\005U71 b\001+!AA1BB~\001\004!i!A\001X!\021q!\007\"\002\t\017\021E11 a\001y\005\021a\016\r\005\b\t+\031Y\0201\001=\003\tq\027\007C\004\005\032\rm\b\031\001*\002\017\031|'o^1sI\"AAQDBz\t\023!y\"\001\007l[BTU/\0349UC\ndW-\006\003\005\"\021-BCBA\016\tG!i\003\003\005\005&\021m\001\031\001C\024\003\0219v\016\035;\021\0139\t\031\b\"\013\021\007I!Y\003B\004\002V\022m!\031A\013\t\017\021=B1\004a\001y\005!q\017\\3o\021!!\031da=\005\n\021U\022!C6naN+\027M]2i+\021!9\004\"\021\025\037q\"I\004b\021\005H\021-CQ\nC(\t#B\001\002b\017\0052\001\007AQH\001\002'B!aB\rC !\r\021B\021\t\003\b\003+$\tD1\001\026\021\035!)\005\"\rA\002q\n!!\034\031\t\017\021%C\021\007a\001y\005\021Q.\r\005\t\t\027!\t\0041\001\005>!9A\021\003C\031\001\004a\004b\002C\013\tc\001\r\001\020\005\b\t3!\t\0041\001S\021!!)fa=\005\002\021]\023aB5oI\026DxJZ\013\005\t3\"\031\007F\b=\t7\")\007\"\033\005n\021EDQ\017C=\021!!i\006b\025A\002\021}\023AB:pkJ\034W\r\005\003\017e\021\005\004c\001\n\005d\0219\021Q\033C*\005\004)\002b\002C4\t'\002\r\001P\001\rg>,(oY3PM\032\034X\r\036\005\b\tW\"\031\0061\001=\003-\031x.\036:dK\016{WO\034;\t\021\021=D1\013a\001\t?\na\001^1sO\026$\bb\002C:\t'\002\r\001P\001\ri\006\024x-\032;PM\032\034X\r\036\005\b\to\"\031\0061\001=\003-!\030M]4fi\016{WO\034;\t\017\021mD1\013a\001y\005IaM]8n\023:$W\r\037\005\t\t\032\031\020\"\001\005\002\006YA.Y:u\023:$W\r_(g+\021!\031\tb#\025\037q\")\t\"$\005\020\022EE1\023CK\t/C\001\002\"\030\005~\001\007Aq\021\t\005\035I\"I\tE\002\023\t\027#q!!6\005~\t\007Q\003C\004\005h\021u\004\031\001\037\t\017\021-DQ\020a\001y!AAq\016C?\001\004!9\tC\004\005t\021u\004\031\001\037\t\017\021]DQ\020a\001y!9A1\020C?\001\004a\004")
/*     */ public interface SeqLike<A, Repr> extends IterableLike<A, Repr>, GenSeqLike<A, Repr>, Parallelizable<A, ParSeq<A>> {
/*     */   Seq<A> thisCollection();
/*     */   
/*     */   Seq<A> toCollection(Repr paramRepr);
/*     */   
/*     */   int length();
/*     */   
/*     */   A apply(int paramInt);
/*     */   
/*     */   Combiner<A, ParSeq<A>> parCombiner();
/*     */   
/*     */   int lengthCompare(int paramInt);
/*     */   
/*     */   boolean isEmpty();
/*     */   
/*     */   int size();
/*     */   
/*     */   int segmentLength(Function1<A, Object> paramFunction1, int paramInt);
/*     */   
/*     */   int indexWhere(Function1<A, Object> paramFunction1, int paramInt);
/*     */   
/*     */   int lastIndexWhere(Function1<A, Object> paramFunction1, int paramInt);
/*     */   
/*     */   Iterator<Repr> permutations();
/*     */   
/*     */   Iterator<Repr> combinations(int paramInt);
/*     */   
/*     */   Repr reverse();
/*     */   
/*     */   <B, That> That reverseMap(Function1<A, B> paramFunction1, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   Iterator<A> reverseIterator();
/*     */   
/*     */   <B> boolean startsWith(GenSeq<B> paramGenSeq, int paramInt);
/*     */   
/*     */   <B> boolean endsWith(GenSeq<B> paramGenSeq);
/*     */   
/*     */   <B> int indexOfSlice(GenSeq<B> paramGenSeq);
/*     */   
/*     */   <B> int indexOfSlice(GenSeq<B> paramGenSeq, int paramInt);
/*     */   
/*     */   <B> int lastIndexOfSlice(GenSeq<B> paramGenSeq);
/*     */   
/*     */   <B> int lastIndexOfSlice(GenSeq<B> paramGenSeq, int paramInt);
/*     */   
/*     */   <B> boolean containsSlice(GenSeq<B> paramGenSeq);
/*     */   
/*     */   boolean contains(Object paramObject);
/*     */   
/*     */   <B, That> That union(GenSeq<B> paramGenSeq, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B> Repr diff(GenSeq<B> paramGenSeq);
/*     */   
/*     */   <B> Repr intersect(GenSeq<B> paramGenSeq);
/*     */   
/*     */   Repr distinct();
/*     */   
/*     */   <B, That> That patch(int paramInt1, GenSeq<B> paramGenSeq, int paramInt2, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That updated(int paramInt, B paramB, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That $plus$colon(B paramB, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That $colon$plus(B paramB, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That padTo(int paramInt, B paramB, CanBuildFrom<Repr, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B> boolean corresponds(GenSeq<B> paramGenSeq, Function2<A, B, Object> paramFunction2);
/*     */   
/*     */   Repr sortWith(Function2<A, A, Object> paramFunction2);
/*     */   
/*     */   <B> Repr sortBy(Function1<A, B> paramFunction1, Ordering<B> paramOrdering);
/*     */   
/*     */   <B> Repr sorted(Ordering<B> paramOrdering);
/*     */   
/*     */   Seq<A> toSeq();
/*     */   
/*     */   Range indices();
/*     */   
/*     */   Object view();
/*     */   
/*     */   SeqView<A, Repr> view(int paramInt1, int paramInt2);
/*     */   
/*     */   String toString();
/*     */   
/*     */   public class PermutationsItr extends AbstractIterator<Repr> {
/*     */     private final Tuple2<Buffer<A>, int[]> x$1;
/*     */     
/*     */     private final Buffer<A> elms;
/*     */     
/*     */     private final int[] idxs;
/*     */     
/*     */     private boolean _hasNext;
/*     */     
/*     */     public PermutationsItr(SeqLike $outer) {
/* 153 */       Tuple2<Buffer<A>, int[]> tuple2 = init();
/* 153 */       if (tuple2 != null) {
/* 153 */         this.x$1 = new Tuple2(tuple2._1(), tuple2._2());
/* 153 */         this.elms = (Buffer<A>)this.x$1._1();
/* 153 */         this.idxs = (int[])this.x$1._2();
/* 154 */         this._hasNext = true;
/*     */         return;
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     private boolean _hasNext() {
/* 154 */       return this._hasNext;
/*     */     }
/*     */     
/*     */     private void _hasNext_$eq(boolean x$1) {
/* 154 */       this._hasNext = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 156 */       return _hasNext();
/*     */     }
/*     */     
/*     */     public Repr next() {
/* 158 */       hasNext() ? BoxedUnit.UNIT : 
/* 159 */         Iterator$.MODULE$.empty().next();
/* 161 */       ArrayBuffer forcedElms = (new ArrayBuffer(this.elms.size())).$plus$plus$eq((TraversableOnce)this.elms);
/* 162 */       Object result = ((Builder)scala$collection$SeqLike$PermutationsItr$$$outer().newBuilder().$plus$plus$eq((TraversableOnce)forcedElms)).result();
/* 163 */       int i = this.idxs.length - 2;
/* 164 */       while (i >= 0 && this.idxs[i] >= this.idxs[i + 1])
/* 165 */         i--; 
/* 167 */       if (i < 0) {
/* 168 */         _hasNext_$eq(false);
/*     */       } else {
/* 170 */         int j = this.idxs.length - 1;
/* 171 */         for (; this.idxs[j] <= this.idxs[i]; j--);
/* 172 */         swap(i, j);
/* 174 */         int len = (this.idxs.length - i) / 2;
/* 175 */         int k = 1;
/* 176 */         while (k <= len) {
/* 177 */           swap(i + k, this.idxs.length - k);
/* 178 */           k++;
/*     */         } 
/*     */       } 
/* 181 */       return (Repr)result;
/*     */     }
/*     */     
/*     */     private void swap(int i, int j) {
/* 184 */       int tmpI = this.idxs[i];
/* 185 */       this.idxs[i] = this.idxs[j];
/* 186 */       this.idxs[j] = tmpI;
/* 187 */       Object tmpE = this.elms.apply(i);
/* 188 */       this.elms.update(i, this.elms.apply(j));
/* 189 */       this.elms.update(j, tmpE);
/*     */     }
/*     */     
/*     */     private Tuple2<Buffer<A>, int[]> init() {
/* 193 */       HashMap m = (HashMap)HashMap$.MODULE$.apply((Seq)Nil$.MODULE$);
/* 194 */       Tuple2 tuple2 = ((GenericTraversableTemplate)((SeqLike)scala$collection$SeqLike$PermutationsItr$$$outer().thisCollection().map((Function1)new $anonfun$2(this, (PermutationsItr)m), Seq$.MODULE$.canBuildFrom())).sortBy((Function1)new $anonfun$3(this), (Ordering)Ordering$Int$.MODULE$)).unzip((Function1)Predef$.MODULE$.conforms());
/* 194 */       if (tuple2 != null) {
/* 194 */         Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 194 */         Seq es = (Seq)tuple21._1(), is = (Seq)tuple21._2();
/* 196 */         return new Tuple2(es.toBuffer(), is.toArray(ClassTag$.MODULE$.Int()));
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public class $anonfun$2 extends AbstractFunction1<A, Tuple2<A, Object>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final HashMap m$1;
/*     */       
/*     */       public final Tuple2<A, Object> apply(Object e) {
/*     */         return new Tuple2(e, this.m$1.getOrElseUpdate(e, (Function0)new SeqLike$PermutationsItr$$anonfun$2$$anonfun$apply$1(this)));
/*     */       }
/*     */       
/*     */       public $anonfun$2(SeqLike.PermutationsItr $outer, HashMap m$1) {}
/*     */       
/*     */       public class SeqLike$PermutationsItr$$anonfun$2$$anonfun$apply$1 extends AbstractFunction0.mcI.sp implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final int apply() {
/*     */           return this.$outer.m$1.size();
/*     */         }
/*     */         
/*     */         public int apply$mcI$sp() {
/*     */           return this.$outer.m$1.size();
/*     */         }
/*     */         
/*     */         public SeqLike$PermutationsItr$$anonfun$2$$anonfun$apply$1(SeqLike.PermutationsItr.$anonfun$2 $outer) {}
/*     */       }
/*     */     }
/*     */     
/*     */     public class $anonfun$3 extends AbstractFunction1<Tuple2<A, Object>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply(Tuple2 x$2) {
/*     */         return x$2._2$mcI$sp();
/*     */       }
/*     */       
/*     */       public $anonfun$3(SeqLike.PermutationsItr $outer) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class CombinationsItr extends AbstractIterator<Repr> {
/*     */     private final int n;
/*     */     
/*     */     private final Tuple3<IndexedSeq<A>, int[], int[]> x$4;
/*     */     
/*     */     private final IndexedSeq<A> scala$collection$SeqLike$CombinationsItr$$elms;
/*     */     
/*     */     private final int[] scala$collection$SeqLike$CombinationsItr$$cnts;
/*     */     
/*     */     private final int[] scala$collection$SeqLike$CombinationsItr$$nums;
/*     */     
/*     */     private final int[] scala$collection$SeqLike$CombinationsItr$$offs;
/*     */     
/*     */     private boolean _hasNext;
/*     */     
/*     */     public CombinationsItr(SeqLike $outer, int n) {
/* 204 */       Tuple3<IndexedSeq<A>, int[], int[]> tuple3 = init();
/* 204 */       if (tuple3 != null) {
/* 204 */         this.x$4 = new Tuple3(tuple3._1(), tuple3._2(), tuple3._3());
/* 204 */         this.scala$collection$SeqLike$CombinationsItr$$elms = (IndexedSeq<A>)this.x$4._1();
/* 204 */         this.scala$collection$SeqLike$CombinationsItr$$cnts = (int[])this.x$4._2();
/* 204 */         this.scala$collection$SeqLike$CombinationsItr$$nums = (int[])this.x$4._3();
/* 205 */         this.scala$collection$SeqLike$CombinationsItr$$offs = (int[])Predef$.MODULE$.intArrayOps(scala$collection$SeqLike$CombinationsItr$$cnts()).scanLeft(BoxesRunTime.boxToInteger(0), (Function2)new $anonfun$1(this), Array$.MODULE$.canBuildFrom(ClassTag$.MODULE$.Int()));
/* 206 */         this._hasNext = true;
/*     */         return;
/*     */       } 
/*     */       throw new MatchError(tuple3);
/*     */     }
/*     */     
/*     */     public IndexedSeq<A> scala$collection$SeqLike$CombinationsItr$$elms() {
/*     */       return this.scala$collection$SeqLike$CombinationsItr$$elms;
/*     */     }
/*     */     
/*     */     public int[] scala$collection$SeqLike$CombinationsItr$$cnts() {
/*     */       return this.scala$collection$SeqLike$CombinationsItr$$cnts;
/*     */     }
/*     */     
/*     */     public int[] scala$collection$SeqLike$CombinationsItr$$nums() {
/*     */       return this.scala$collection$SeqLike$CombinationsItr$$nums;
/*     */     }
/*     */     
/*     */     public int[] scala$collection$SeqLike$CombinationsItr$$offs() {
/*     */       return this.scala$collection$SeqLike$CombinationsItr$$offs;
/*     */     }
/*     */     
/*     */     public class $anonfun$1 extends AbstractFunction2.mcIII.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply(int x$5, int x$6) {
/*     */         return x$5 + x$6;
/*     */       }
/*     */       
/*     */       public int apply$mcIII$sp(int x$5, int x$6) {
/*     */         return x$5 + x$6;
/*     */       }
/*     */       
/*     */       public $anonfun$1(SeqLike.CombinationsItr $outer) {}
/*     */     }
/*     */     
/*     */     private boolean _hasNext() {
/* 206 */       return this._hasNext;
/*     */     }
/*     */     
/*     */     private void _hasNext_$eq(boolean x$1) {
/* 206 */       this._hasNext = x$1;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 208 */       return _hasNext();
/*     */     }
/*     */     
/*     */     public Repr next() {
/* 210 */       hasNext() ? BoxedUnit.UNIT : 
/* 211 */         Iterator$.MODULE$.empty().next();
/* 214 */       Builder buf = scala$collection$SeqLike$CombinationsItr$$$outer().newBuilder();
/* 215 */       Predef$ predef$1 = Predef$.MODULE$;
/* 215 */       int i = (scala$collection$SeqLike$CombinationsItr$$nums()).length;
/* 215 */       Range$ range$ = Range$.MODULE$;
/* 215 */       SeqLike$CombinationsItr$$anonfun$next$1 seqLike$CombinationsItr$$anonfun$next$1 = new SeqLike$CombinationsItr$$anonfun$next$1(this, (CombinationsItr)buf);
/*     */       Range range;
/* 215 */       if ((range = new Range(0, i, 1)).validateRangeBoundaries((Function1)seqLike$CombinationsItr$$anonfun$next$1)) {
/*     */         int terminal1;
/*     */         int step1;
/*     */         int i1;
/* 215 */         for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 215 */           Predef$ predef$ = Predef$.MODULE$;
/* 215 */           int j = scala$collection$SeqLike$CombinationsItr$$nums()[i1];
/* 215 */           Range$ range$1 = Range$.MODULE$;
/* 215 */           SeqLike$CombinationsItr$$anonfun$next$1$$anonfun$apply$mcVI$sp$1 seqLike$CombinationsItr$$anonfun$next$1$$anonfun$apply$mcVI$sp$1 = new SeqLike$CombinationsItr$$anonfun$next$1$$anonfun$apply$mcVI$sp$1(seqLike$CombinationsItr$$anonfun$next$1, i1);
/*     */           Range range1;
/* 215 */           if ((range1 = new Range(0, j, 1)).validateRangeBoundaries((Function1)seqLike$CombinationsItr$$anonfun$next$1$$anonfun$apply$mcVI$sp$1)) {
/*     */             int terminal11;
/*     */             int step11;
/*     */             int i11;
/* 215 */             for (i11 = range1.start(), terminal11 = range1.terminalElement(), step11 = range1.step(); i11 != terminal11; ) {
/* 215 */               int k = i11;
/* 215 */               buf.$plus$eq(seqLike$CombinationsItr$$anonfun$next$1.$outer.scala$collection$SeqLike$CombinationsItr$$elms().apply(seqLike$CombinationsItr$$anonfun$next$1.$outer.scala$collection$SeqLike$CombinationsItr$$offs()[i1] + k));
/* 215 */               i11 += step11;
/*     */             } 
/*     */           } 
/* 215 */           i1 += step1;
/*     */         } 
/*     */       } 
/* 217 */       Object res = buf.result();
/* 220 */       int idx = (scala$collection$SeqLike$CombinationsItr$$nums()).length - 1;
/* 221 */       while (idx >= 0 && scala$collection$SeqLike$CombinationsItr$$nums()[idx] == scala$collection$SeqLike$CombinationsItr$$cnts()[idx])
/* 222 */         idx--; 
/* 224 */       int[] arrayOfInt = scala$collection$SeqLike$CombinationsItr$$nums();
/* 224 */       Predef$ predef$2 = Predef$.MODULE$;
/* 224 */       if ((idx = IndexedSeqOptimized$class.lastIndexWhere((IndexedSeqOptimized)new ArrayOps.ofInt(arrayOfInt), (Function1)new SeqLike$CombinationsItr$$anonfun$next$2(this), idx - 1)) < 
/*     */         
/* 226 */         0) {
/* 227 */         _hasNext_$eq(false);
/*     */       } else {
/* 229 */         int[] arrayOfInt1 = scala$collection$SeqLike$CombinationsItr$$nums();
/* 229 */         Predef$ predef$3 = Predef$.MODULE$;
/* 229 */         int[] arrayOfInt2 = (int[])IndexedSeqOptimized$class.slice((IndexedSeqOptimized)new ArrayOps.ofInt(arrayOfInt1), idx + 1, (scala$collection$SeqLike$CombinationsItr$$nums()).length);
/* 229 */         Predef$ predef$4 = Predef$.MODULE$;
/* 229 */         IntRef sum = new IntRef(BoxesRunTime.unboxToInt(TraversableOnce$class.sum((TraversableOnce)new ArrayOps.ofInt(arrayOfInt2), (Numeric)Numeric$IntIsIntegral$.MODULE$)) + 1);
/* 230 */         scala$collection$SeqLike$CombinationsItr$$nums()[idx] = scala$collection$SeqLike$CombinationsItr$$nums()[idx] - 1;
/* 231 */         int j = idx + 1;
/* 231 */         Predef$ predef$5 = Predef$.MODULE$;
/* 231 */         int k = (scala$collection$SeqLike$CombinationsItr$$nums()).length;
/* 231 */         Range$ range$1 = Range$.MODULE$;
/* 231 */         SeqLike$CombinationsItr$$anonfun$next$3 seqLike$CombinationsItr$$anonfun$next$3 = new SeqLike$CombinationsItr$$anonfun$next$3(this, (CombinationsItr)sum);
/*     */         Range range1;
/* 231 */         if ((range1 = new Range(j, k, 1)).validateRangeBoundaries((Function1)seqLike$CombinationsItr$$anonfun$next$3)) {
/*     */           int terminal2;
/*     */           int step2;
/*     */           int i2;
/* 231 */           for (i2 = range1.start(), terminal2 = range1.terminalElement(), step2 = range1.step(); i2 != terminal2; ) {
/* 231 */             int m = sum.elem;
/* 231 */             Predef$ predef$ = Predef$.MODULE$;
/* 231 */             scala$collection$SeqLike$CombinationsItr$$nums()[i2] = RichInt$.MODULE$.min$extension(m, scala$collection$SeqLike$CombinationsItr$$cnts()[i2]);
/* 231 */             sum.elem -= scala$collection$SeqLike$CombinationsItr$$nums()[i2];
/* 231 */             i2 += step2;
/*     */           } 
/*     */         } 
/*     */       } 
/* 237 */       return (Repr)res;
/*     */     }
/*     */     
/*     */     public class SeqLike$CombinationsItr$$anonfun$next$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final Builder buf$1;
/*     */       
/*     */       public final void apply(int k) {
/*     */         apply$mcVI$sp(k);
/*     */       }
/*     */       
/*     */       public void apply$mcVI$sp(int k) {
/*     */         Predef$ predef$ = Predef$.MODULE$;
/*     */         SeqLike$CombinationsItr$$anonfun$next$1$$anonfun$apply$mcVI$sp$1 seqLike$CombinationsItr$$anonfun$next$1$$anonfun$apply$mcVI$sp$1 = new SeqLike$CombinationsItr$$anonfun$next$1$$anonfun$apply$mcVI$sp$1(this, k);
/*     */         Range range;
/*     */         if ((range = RichInt$.MODULE$.until$extension0(0, this.$outer.scala$collection$SeqLike$CombinationsItr$$nums()[k])).validateRangeBoundaries((Function1)seqLike$CombinationsItr$$anonfun$next$1$$anonfun$apply$mcVI$sp$1)) {
/*     */           int terminal1;
/*     */           int step1;
/*     */           int i1;
/*     */           for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/*     */             int i = i1;
/*     */             seqLike$CombinationsItr$$anonfun$next$1$$anonfun$apply$mcVI$sp$1.apply(i);
/*     */             i1 += step1;
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/*     */       public SeqLike$CombinationsItr$$anonfun$next$1(SeqLike.CombinationsItr $outer, Builder buf$1) {}
/*     */       
/*     */       public class SeqLike$CombinationsItr$$anonfun$next$1$$anonfun$apply$mcVI$sp$1 extends AbstractFunction1<Object, Builder<A, Repr>> implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final int k$1;
/*     */         
/*     */         public SeqLike$CombinationsItr$$anonfun$next$1$$anonfun$apply$mcVI$sp$1(SeqLike$CombinationsItr$$anonfun$next$1 $outer, int k$1) {}
/*     */         
/*     */         public final Builder<A, Repr> apply(int j) {
/*     */           return this.$outer.buf$1.$plus$eq(this.$outer.$outer.scala$collection$SeqLike$CombinationsItr$$elms().apply(this.$outer.$outer.scala$collection$SeqLike$CombinationsItr$$offs()[this.k$1] + j));
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */     public class SeqLike$CombinationsItr$$anonfun$next$1$$anonfun$apply$mcVI$sp$1 extends AbstractFunction1<Object, Builder<A, Repr>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int k$1;
/*     */       
/*     */       public SeqLike$CombinationsItr$$anonfun$next$1$$anonfun$apply$mcVI$sp$1(SeqLike$CombinationsItr$$anonfun$next$1 $outer, int k$1) {}
/*     */       
/*     */       public final Builder<A, Repr> apply(int j) {
/*     */         return this.$outer.buf$1.$plus$eq(this.$outer.$outer.scala$collection$SeqLike$CombinationsItr$$elms().apply(this.$outer.$outer.scala$collection$SeqLike$CombinationsItr$$offs()[this.k$1] + j));
/*     */       }
/*     */     }
/*     */     
/*     */     public class SeqLike$CombinationsItr$$anonfun$next$2 extends AbstractFunction1.mcZI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final boolean apply(int x$7) {
/*     */         return apply$mcZI$sp(x$7);
/*     */       }
/*     */       
/*     */       public boolean apply$mcZI$sp(int x$7) {
/*     */         return (x$7 > 0);
/*     */       }
/*     */       
/*     */       public SeqLike$CombinationsItr$$anonfun$next$2(SeqLike.CombinationsItr $outer) {}
/*     */     }
/*     */     
/*     */     public class SeqLike$CombinationsItr$$anonfun$next$3 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final IntRef sum$1;
/*     */       
/*     */       public final void apply(int k) {
/*     */         apply$mcVI$sp(k);
/*     */       }
/*     */       
/*     */       public SeqLike$CombinationsItr$$anonfun$next$3(SeqLike.CombinationsItr $outer, IntRef sum$1) {}
/*     */       
/*     */       public void apply$mcVI$sp(int k) {
/*     */         int i = this.sum$1.elem;
/*     */         Predef$ predef$ = Predef$.MODULE$;
/*     */         this.$outer.scala$collection$SeqLike$CombinationsItr$$nums()[k] = RichInt$.MODULE$.min$extension(i, this.$outer.scala$collection$SeqLike$CombinationsItr$$cnts()[k]);
/*     */         this.sum$1.elem -= this.$outer.scala$collection$SeqLike$CombinationsItr$$nums()[k];
/*     */       }
/*     */     }
/*     */     
/*     */     private Tuple3<IndexedSeq<A>, int[], int[]> init() {
/* 246 */       Nil$ nil$ = Nil$.MODULE$;
/* 246 */       HashMap$ hashMap$ = HashMap$.MODULE$;
/* 246 */       HashMap m = (HashMap)((Builder)((Builder)new HashMap()).$plus$plus$eq((TraversableOnce)nil$)).result();
/* 249 */       Tuple2 tuple2 = ((GenericTraversableTemplate)((SeqLike)scala$collection$SeqLike$CombinationsItr$$$outer().thisCollection().map((Function1)new $anonfun$4(this, (CombinationsItr)m), (CanBuildFrom)Seq$.MODULE$.ReusableCBF())).sortBy((Function1)new $anonfun$5(this), (Ordering)Ordering$Int$.MODULE$)).unzip((Function1)Predef$.MODULE$.conforms());
/* 249 */       if (tuple2 != null) {
/* 249 */         Tuple2 tuple21 = new Tuple2(tuple2._1(), tuple2._2());
/* 249 */         Seq es = (Seq)tuple21._1(), is = (Seq)tuple21._2();
/* 250 */         int[] cs = new int[m.size()];
/* 251 */         is.foreach((Function1)new SeqLike$CombinationsItr$$anonfun$init$1(this, (CombinationsItr)cs));
/* 252 */         int[] ns = new int[cs.length];
/* 254 */         IntRef r = new IntRef(this.n);
/* 255 */         Predef$ predef$ = Predef$.MODULE$;
/* 255 */         int i = ns.length;
/* 255 */         Range$ range$ = Range$.MODULE$;
/* 255 */         SeqLike$CombinationsItr$$anonfun$init$2 seqLike$CombinationsItr$$anonfun$init$2 = new SeqLike$CombinationsItr$$anonfun$init$2(this, cs, ns, (CombinationsItr)r);
/*     */         Range range;
/* 255 */         if ((range = new Range(0, i, 1)).validateRangeBoundaries((Function1)seqLike$CombinationsItr$$anonfun$init$2)) {
/*     */           int terminal1;
/*     */           int step1;
/*     */           int i1;
/* 255 */           for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 255 */             int j = seqLike$CombinationsItr$$anonfun$init$2.r$1.elem;
/* 255 */             Predef$ predef$1 = Predef$.MODULE$;
/* 255 */             seqLike$CombinationsItr$$anonfun$init$2.ns$1[i1] = RichInt$.MODULE$.min$extension(j, seqLike$CombinationsItr$$anonfun$init$2.cs$1[i1]);
/* 255 */             seqLike$CombinationsItr$$anonfun$init$2.r$1.elem -= seqLike$CombinationsItr$$anonfun$init$2.ns$1[i1];
/* 255 */             i1 += step1;
/*     */           } 
/*     */         } 
/* 259 */         return new Tuple3(es.toIndexedSeq(), cs, ns);
/*     */       } 
/*     */       throw new MatchError(tuple2);
/*     */     }
/*     */     
/*     */     public class $anonfun$4 extends AbstractFunction1<A, Tuple2<A, Object>> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final HashMap m$2;
/*     */       
/*     */       public final Tuple2<A, Object> apply(Object e) {
/*     */         return new Tuple2(e, this.m$2.getOrElseUpdate(e, (Function0)new SeqLike$CombinationsItr$$anonfun$4$$anonfun$apply$2(this)));
/*     */       }
/*     */       
/*     */       public $anonfun$4(SeqLike.CombinationsItr $outer, HashMap m$2) {}
/*     */       
/*     */       public class SeqLike$CombinationsItr$$anonfun$4$$anonfun$apply$2 extends AbstractFunction0.mcI.sp implements Serializable {
/*     */         public static final long serialVersionUID = 0L;
/*     */         
/*     */         public final int apply() {
/*     */           return this.$outer.m$2.size();
/*     */         }
/*     */         
/*     */         public int apply$mcI$sp() {
/*     */           return this.$outer.m$2.size();
/*     */         }
/*     */         
/*     */         public SeqLike$CombinationsItr$$anonfun$4$$anonfun$apply$2(SeqLike.CombinationsItr.$anonfun$4 $outer) {}
/*     */       }
/*     */     }
/*     */     
/*     */     public class $anonfun$5 extends AbstractFunction1<Tuple2<A, Object>, Object> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int apply(Tuple2 x$8) {
/*     */         return x$8._2$mcI$sp();
/*     */       }
/*     */       
/*     */       public $anonfun$5(SeqLike.CombinationsItr $outer) {}
/*     */     }
/*     */     
/*     */     public class SeqLike$CombinationsItr$$anonfun$init$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final int[] cs$1;
/*     */       
/*     */       public final void apply(int i) {
/*     */         apply$mcVI$sp(i);
/*     */       }
/*     */       
/*     */       public void apply$mcVI$sp(int i) {
/*     */         this.cs$1[i] = this.cs$1[i] + 1;
/*     */       }
/*     */       
/*     */       public SeqLike$CombinationsItr$$anonfun$init$1(SeqLike.CombinationsItr $outer, int[] cs$1) {}
/*     */     }
/*     */     
/*     */     public class SeqLike$CombinationsItr$$anonfun$init$2 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       public final int[] cs$1;
/*     */       
/*     */       public final int[] ns$1;
/*     */       
/*     */       public final IntRef r$1;
/*     */       
/*     */       public final void apply(int k) {
/*     */         apply$mcVI$sp(k);
/*     */       }
/*     */       
/*     */       public SeqLike$CombinationsItr$$anonfun$init$2(SeqLike.CombinationsItr $outer, int[] cs$1, int[] ns$1, IntRef r$1) {}
/*     */       
/*     */       public void apply$mcVI$sp(int k) {
/*     */         int i = this.r$1.elem;
/*     */         Predef$ predef$ = Predef$.MODULE$;
/*     */         this.ns$1[k] = RichInt$.MODULE$.min$extension(i, this.cs$1[k]);
/*     */         this.r$1.elem -= this.ns$1[k];
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$reverse$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectRef xs$1;
/*     */     
/*     */     public SeqLike$$anonfun$reverse$1(SeqLike $outer, ObjectRef xs$1) {}
/*     */     
/*     */     public final void apply(Object x) {
/* 266 */       this.xs$1.elem = ((List)this.xs$1.elem).$colon$colon(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$reverseMap$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectRef xs$2;
/*     */     
/*     */     public SeqLike$$anonfun$reverseMap$1(SeqLike $outer, ObjectRef xs$2) {}
/*     */     
/*     */     public final void apply(Object x) {
/* 277 */       this.xs$2.elem = ((List)this.xs$2.elem).$colon$colon(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$contains$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object elem$1;
/*     */     
/*     */     public final boolean apply(Object x$12) {
/* 393 */       Object object = this.elem$1;
/* 393 */       return ((x$12 == object) ? true : ((x$12 == null) ? false : ((x$12 instanceof Number) ? BoxesRunTime.equalsNumObject((Number)x$12, object) : ((x$12 instanceof Character) ? BoxesRunTime.equalsCharObject((Character)x$12, object) : x$12.equals(object)))));
/*     */     }
/*     */     
/*     */     public SeqLike$$anonfun$contains$1(SeqLike $outer, Object elem$1) {}
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$diff$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Map occ$1;
/*     */     
/*     */     private final Builder b$3;
/*     */     
/*     */     public SeqLike$$anonfun$diff$1(SeqLike $outer, Map occ$1, Builder b$3) {}
/*     */     
/*     */     public final Object apply(Object x) {
/* 444 */       this.occ$1.update(x, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.occ$1.apply(x)) - 1));
/* 444 */       return (BoxesRunTime.unboxToInt(this.occ$1.apply(x)) == 0) ? this.b$3.$plus$eq(x) : BoxedUnit.UNIT;
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$intersect$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Map occ$2;
/*     */     
/*     */     private final Builder b$4;
/*     */     
/*     */     public SeqLike$$anonfun$intersect$1(SeqLike $outer, Map occ$2, Builder b$4) {}
/*     */     
/*     */     public final void apply(Object x) {
/* 472 */       if (BoxesRunTime.unboxToInt(this.occ$2.apply(x)) > 0) {
/* 473 */         this.b$4.$plus$eq(x);
/* 474 */         this.occ$2.update(x, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.occ$2.apply(x)) - 1));
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqLike$$anon$1 extends HashMap<Object, Object> {
/*     */     public int default(Object k) {
/* 480 */       return 0;
/*     */     }
/*     */     
/*     */     public SeqLike$$anon$1(SeqLike $outer) {}
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$occCounts$1 extends AbstractFunction1<Object, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final HashMap occ$3;
/*     */     
/*     */     public final void apply(Object y) {
/* 481 */       this.occ$3.update(y, BoxesRunTime.boxToInteger(BoxesRunTime.unboxToInt(this.occ$3.apply(y)) + 1));
/*     */     }
/*     */     
/*     */     public SeqLike$$anonfun$occCounts$1(SeqLike $outer, HashMap occ$3) {}
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$distinct$1 extends AbstractFunction1<A, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$5;
/*     */     
/*     */     private final HashSet seen$1;
/*     */     
/*     */     public SeqLike$$anonfun$distinct$1(SeqLike $outer, Builder b$5, HashSet seen$1) {}
/*     */     
/*     */     public final Object apply(Object x) {
/* 495 */       this.b$5.$plus$eq(x);
/* 496 */       return this.seen$1.apply(x) ? BoxedUnit.UNIT : this.seen$1.$plus$eq(x);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$sorted$1 extends AbstractFunction1<A, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ArraySeq arr$1;
/*     */     
/*     */     private final IntRef i$1;
/*     */     
/*     */     public SeqLike$$anonfun$sorted$1(SeqLike $outer, ArraySeq arr$1, IntRef i$1) {}
/*     */     
/*     */     public final void apply(Object x) {
/* 612 */       this.arr$1.update(this.i$1.elem, x);
/* 613 */       this.i$1.elem++;
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqLike$$anonfun$sorted$2 extends AbstractFunction1<A, Builder<A, Repr>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Builder b$6;
/*     */     
/*     */     public final Builder<A, Repr> apply(Object x) {
/* 618 */       return this.b$6.$plus$eq(x);
/*     */     }
/*     */     
/*     */     public SeqLike$$anonfun$sorted$2(SeqLike $outer, Builder b$6) {}
/*     */   }
/*     */   
/*     */   public class SeqLike$$anon$2 implements SeqView<A, Repr> {
/*     */     private Repr underlying;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public <B> SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<B> newForced(Function0 xs) {
/* 635 */       return SeqViewLike$class.newForced(this, xs);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<B> newAppended(GenTraversable that) {
/* 635 */       return SeqViewLike$class.newAppended(this, that);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<B> newMapped(Function1 f) {
/* 635 */       return SeqViewLike$class.newMapped(this, f);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<B> newFlatMapped(Function1 f) {
/* 635 */       return SeqViewLike$class.newFlatMapped(this, f);
/*     */     }
/*     */     
/*     */     public SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A> newFiltered(Function1 p) {
/* 635 */       return SeqViewLike$class.newFiltered(this, p);
/*     */     }
/*     */     
/*     */     public SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A> newSliced(SliceInterval _endpoints) {
/* 635 */       return SeqViewLike$class.newSliced(this, _endpoints);
/*     */     }
/*     */     
/*     */     public SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A> newDroppedWhile(Function1 p) {
/* 635 */       return SeqViewLike$class.newDroppedWhile(this, p);
/*     */     }
/*     */     
/*     */     public SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A> newTakenWhile(Function1 p) {
/* 635 */       return SeqViewLike$class.newTakenWhile(this, p);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<Tuple2<A, B>> newZipped(GenIterable that) {
/* 635 */       return SeqViewLike$class.newZipped(this, that);
/*     */     }
/*     */     
/*     */     public <A1, B> SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<Tuple2<A1, B>> newZippedAll(GenIterable that, Object _thisElem, Object _thatElem) {
/* 635 */       return SeqViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
/*     */     }
/*     */     
/*     */     public SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A> newReversed() {
/* 635 */       return SeqViewLike$class.newReversed(this);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<B> newPatched(int _from, GenSeq _patch, int _replaced) {
/* 635 */       return SeqViewLike$class.newPatched(this, _from, _patch, _replaced);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<B> newPrepended(Object elem) {
/* 635 */       return SeqViewLike$class.newPrepended(this, elem);
/*     */     }
/*     */     
/*     */     public SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A> newTaken(int n) {
/* 635 */       return SeqViewLike$class.newTaken(this, n);
/*     */     }
/*     */     
/*     */     public SeqViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A> newDropped(int n) {
/* 635 */       return SeqViewLike$class.newDropped(this, n);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> reverse() {
/* 635 */       return SeqViewLike$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <B, That> That patch(int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/* 635 */       return (That)SeqViewLike$class.patch(this, from, patch, replaced, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That padTo(int len, Object elem, CanBuildFrom bf) {
/* 635 */       return (That)SeqViewLike$class.padTo(this, len, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That reverseMap(Function1 f, CanBuildFrom bf) {
/* 635 */       return (That)SeqViewLike$class.reverseMap(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That updated(int index, Object elem, CanBuildFrom bf) {
/* 635 */       return (That)SeqViewLike$class.updated(this, index, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/* 635 */       return (That)SeqViewLike$class.$plus$colon(this, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $colon$plus(Object elem, CanBuildFrom bf) {
/* 635 */       return (That)SeqViewLike$class.$colon$plus(this, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That union(GenSeq that, CanBuildFrom bf) {
/* 635 */       return (That)SeqViewLike$class.union(this, that, bf);
/*     */     }
/*     */     
/*     */     public <B> SeqView<A, Repr> diff(GenSeq that) {
/* 635 */       return SeqViewLike$class.diff(this, that);
/*     */     }
/*     */     
/*     */     public <B> SeqView<A, Repr> intersect(GenSeq that) {
/* 635 */       return SeqViewLike$class.intersect(this, that);
/*     */     }
/*     */     
/*     */     public <B> SeqView<A, Repr> sorted(Ordering ord) {
/* 635 */       return SeqViewLike$class.sorted(this, ord);
/*     */     }
/*     */     
/*     */     public String stringPrefix() {
/* 635 */       return SeqViewLike$class.stringPrefix(this);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> drop(int n) {
/* 635 */       return (SeqView<A, Repr>)IterableViewLike$class.drop(this, n);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> take(int n) {
/* 635 */       return (SeqView<A, Repr>)IterableViewLike$class.take(this, n);
/*     */     }
/*     */     
/*     */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 635 */       return (That)IterableViewLike$class.zip(this, that, bf);
/*     */     }
/*     */     
/*     */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/* 635 */       return (That)IterableViewLike$class.zipWithIndex(this, bf);
/*     */     }
/*     */     
/*     */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 635 */       return (That)IterableViewLike$class.zipAll(this, that, thisElem, thatElem, bf);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<A, Repr>> grouped(int size) {
/* 635 */       return IterableViewLike$class.grouped(this, size);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<A, Repr>> sliding(int size, int step) {
/* 635 */       return IterableViewLike$class.sliding(this, size, step);
/*     */     }
/*     */     
/*     */     public Builder<A, SeqView<A, Repr>> newBuilder() {
/* 635 */       return TraversableViewLike$class.newBuilder(this);
/*     */     }
/*     */     
/*     */     public String viewIdentifier() {
/* 635 */       return TraversableViewLike$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public String viewIdString() {
/* 635 */       return TraversableViewLike$class.viewIdString(this);
/*     */     }
/*     */     
/*     */     public <B, That> That force(CanBuildFrom bf) {
/* 635 */       return (That)TraversableViewLike$class.force(this, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus(GenTraversableOnce xs, CanBuildFrom bf) {
/* 635 */       return (That)TraversableViewLike$class.$plus$plus(this, xs, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 635 */       return (That)TraversableViewLike$class.map(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 635 */       return (That)TraversableViewLike$class.collect(this, pf, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 635 */       return (That)TraversableViewLike$class.flatMap(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B> TraversableViewLike<A, Repr, SeqView<A, Repr>>.Transformed<B> flatten(Function1 asTraversable) {
/* 635 */       return TraversableViewLike$class.flatten(this, asTraversable);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> filter(Function1 p) {
/* 635 */       return (SeqView<A, Repr>)TraversableViewLike$class.filter(this, p);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> withFilter(Function1 p) {
/* 635 */       return (SeqView<A, Repr>)TraversableViewLike$class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<SeqView<A, Repr>, SeqView<A, Repr>> partition(Function1 p) {
/* 635 */       return TraversableViewLike$class.partition(this, p);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> init() {
/* 635 */       return (SeqView<A, Repr>)TraversableViewLike$class.init(this);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> slice(int from, int until) {
/* 635 */       return (SeqView<A, Repr>)TraversableViewLike$class.slice(this, from, until);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> dropWhile(Function1 p) {
/* 635 */       return (SeqView<A, Repr>)TraversableViewLike$class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> takeWhile(Function1 p) {
/* 635 */       return (SeqView<A, Repr>)TraversableViewLike$class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<SeqView<A, Repr>, SeqView<A, Repr>> span(Function1 p) {
/* 635 */       return TraversableViewLike$class.span(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<SeqView<A, Repr>, SeqView<A, Repr>> splitAt(int n) {
/* 635 */       return TraversableViewLike$class.splitAt(this, n);
/*     */     }
/*     */     
/*     */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 635 */       return (That)TraversableViewLike$class.scanLeft(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 635 */       return (That)TraversableViewLike$class.scanRight(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <K> Map<K, SeqView<A, Repr>> groupBy(Function1 f) {
/* 635 */       return TraversableViewLike$class.groupBy(this, f);
/*     */     }
/*     */     
/*     */     public <A1, A2> Tuple2<TraversableViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A1>, TraversableViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A2>> unzip(Function1 asPair) {
/* 635 */       return TraversableViewLike$class.unzip(this, asPair);
/*     */     }
/*     */     
/*     */     public <A1, A2, A3> Tuple3<TraversableViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A1>, TraversableViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A2>, TraversableViewLike<A, Repr, SeqView<A, Repr>>.Transformed<A3>> unzip3(Function1 asTriple) {
/* 635 */       return TraversableViewLike$class.unzip3(this, asTriple);
/*     */     }
/*     */     
/*     */     public String toString() {
/* 635 */       return TraversableViewLike$class.toString(this);
/*     */     }
/*     */     
/*     */     public String viewToString() {
/* 635 */       return GenTraversableViewLike$class.viewToString(this);
/*     */     }
/*     */     
/*     */     public Seq<A> thisSeq() {
/* 635 */       return ViewMkString$class.thisSeq(this);
/*     */     }
/*     */     
/*     */     public String mkString() {
/* 635 */       return ViewMkString$class.mkString(this);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/* 635 */       return ViewMkString$class.mkString(this, sep);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/* 635 */       return ViewMkString$class.mkString(this, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 635 */       return ViewMkString$class.addString(this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public GenericCompanion<Seq> companion() {
/* 635 */       return Seq$class.companion(this);
/*     */     }
/*     */     
/*     */     public Seq<A> seq() {
/* 635 */       return Seq$class.seq(this);
/*     */     }
/*     */     
/*     */     public Seq<A> thisCollection() {
/* 635 */       return SeqLike$class.thisCollection(this);
/*     */     }
/*     */     
/*     */     public Seq<A> toCollection(Object repr) {
/* 635 */       return SeqLike$class.toCollection(this, repr);
/*     */     }
/*     */     
/*     */     public Combiner<A, ParSeq<A>> parCombiner() {
/* 635 */       return SeqLike$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public int lengthCompare(int len) {
/* 635 */       return SeqLike$class.lengthCompare(this, len);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 635 */       return SeqLike$class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public int size() {
/* 635 */       return SeqLike$class.size(this);
/*     */     }
/*     */     
/*     */     public int segmentLength(Function1 p, int from) {
/* 635 */       return SeqLike$class.segmentLength(this, p, from);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p, int from) {
/* 635 */       return SeqLike$class.indexWhere(this, p, from);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 p, int end) {
/* 635 */       return SeqLike$class.lastIndexWhere(this, p, end);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<A, Repr>> permutations() {
/* 635 */       return SeqLike$class.permutations(this);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<A, Repr>> combinations(int n) {
/* 635 */       return SeqLike$class.combinations(this, n);
/*     */     }
/*     */     
/*     */     public Iterator<A> reverseIterator() {
/* 635 */       return SeqLike$class.reverseIterator(this);
/*     */     }
/*     */     
/*     */     public <B> boolean startsWith(GenSeq that, int offset) {
/* 635 */       return SeqLike$class.startsWith(this, that, offset);
/*     */     }
/*     */     
/*     */     public <B> boolean endsWith(GenSeq that) {
/* 635 */       return SeqLike$class.endsWith(this, that);
/*     */     }
/*     */     
/*     */     public <B> int indexOfSlice(GenSeq that) {
/* 635 */       return SeqLike$class.indexOfSlice(this, that);
/*     */     }
/*     */     
/*     */     public <B> int indexOfSlice(GenSeq that, int from) {
/* 635 */       return SeqLike$class.indexOfSlice(this, that, from);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOfSlice(GenSeq that) {
/* 635 */       return SeqLike$class.lastIndexOfSlice(this, that);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOfSlice(GenSeq that, int end) {
/* 635 */       return SeqLike$class.lastIndexOfSlice(this, that, end);
/*     */     }
/*     */     
/*     */     public <B> boolean containsSlice(GenSeq that) {
/* 635 */       return SeqLike$class.containsSlice(this, that);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/* 635 */       return SeqLike$class.contains(this, elem);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> distinct() {
/* 635 */       return (SeqView<A, Repr>)SeqLike$class.distinct(this);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenSeq that, Function2 p) {
/* 635 */       return SeqLike$class.corresponds(this, that, p);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> sortWith(Function2 lt) {
/* 635 */       return (SeqView<A, Repr>)SeqLike$class.sortWith(this, lt);
/*     */     }
/*     */     
/*     */     public <B> SeqView<A, Repr> sortBy(Function1 f, Ordering ord) {
/* 635 */       return (SeqView<A, Repr>)SeqLike$class.sortBy(this, f, ord);
/*     */     }
/*     */     
/*     */     public Seq<A> toSeq() {
/* 635 */       return SeqLike$class.toSeq(this);
/*     */     }
/*     */     
/*     */     public Range indices() {
/* 635 */       return SeqLike$class.indices(this);
/*     */     }
/*     */     
/*     */     public Object view() {
/* 635 */       return SeqLike$class.view(this);
/*     */     }
/*     */     
/*     */     public SeqView<A, SeqView<A, Repr>> view(int from, int until) {
/* 635 */       return SeqLike$class.view(this, from, until);
/*     */     }
/*     */     
/*     */     public boolean isDefinedAt(int idx) {
/* 635 */       return GenSeqLike$class.isDefinedAt(this, idx);
/*     */     }
/*     */     
/*     */     public int prefixLength(Function1 p) {
/* 635 */       return GenSeqLike$class.prefixLength(this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/* 635 */       return GenSeqLike$class.indexWhere(this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/* 635 */       return GenSeqLike$class.indexOf(this, elem);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem, int from) {
/* 635 */       return GenSeqLike$class.indexOf(this, elem, from);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOf(Object elem) {
/* 635 */       return GenSeqLike$class.lastIndexOf(this, elem);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOf(Object elem, int end) {
/* 635 */       return GenSeqLike$class.lastIndexOf(this, elem, end);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 p) {
/* 635 */       return GenSeqLike$class.lastIndexWhere(this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean startsWith(GenSeq that) {
/* 635 */       return GenSeqLike$class.startsWith(this, that);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 635 */       return GenSeqLike$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/* 635 */       return GenSeqLike$class.equals(this, that);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/* 635 */       IterableLike$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/* 635 */       return IterableLike$class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/* 635 */       return IterableLike$class.exists(this, p);
/*     */     }
/*     */     
/*     */     public Option<A> find(Function1 p) {
/* 635 */       return IterableLike$class.find(this, p);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/* 635 */       return (B)IterableLike$class.foldRight(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/* 635 */       return (B)IterableLike$class.reduceRight(this, op);
/*     */     }
/*     */     
/*     */     public Iterable<A> toIterable() {
/* 635 */       return IterableLike$class.toIterable(this);
/*     */     }
/*     */     
/*     */     public Iterator<A> toIterator() {
/* 635 */       return IterableLike$class.toIterator(this);
/*     */     }
/*     */     
/*     */     public A head() {
/* 635 */       return (A)IterableLike$class.head(this);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<A, Repr>> sliding(int size) {
/* 635 */       return IterableLike$class.sliding(this, size);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> takeRight(int n) {
/* 635 */       return (SeqView<A, Repr>)IterableLike$class.takeRight(this, n);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> dropRight(int n) {
/* 635 */       return (SeqView<A, Repr>)IterableLike$class.dropRight(this, n);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start, int len) {
/* 635 */       IterableLike$class.copyToArray(this, xs, start, len);
/*     */     }
/*     */     
/*     */     public <B> boolean sameElements(GenIterable that) {
/* 635 */       return IterableLike$class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public Stream<A> toStream() {
/* 635 */       return IterableLike$class.toStream(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object that) {
/* 635 */       return IterableLike$class.canEqual(this, that);
/*     */     }
/*     */     
/*     */     public <B> Builder<B, Seq<B>> genericBuilder() {
/* 635 */       return GenericTraversableTemplate.class.genericBuilder(this);
/*     */     }
/*     */     
/*     */     public <B> Seq<Seq<B>> transpose(Function1 asTraversable) {
/* 635 */       return (Seq<Seq<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> repr() {
/* 635 */       return (SeqView<A, Repr>)TraversableLike$class.repr(this);
/*     */     }
/*     */     
/*     */     public final boolean isTraversableAgain() {
/* 635 */       return TraversableLike$class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/* 635 */       return TraversableLike$class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/* 635 */       return (That)TraversableLike$class.$plus$plus$colon(this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/* 635 */       return (That)TraversableLike$class.$plus$plus$colon(this, that, bf);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> filterNot(Function1 p) {
/* 635 */       return (SeqView<A, Repr>)TraversableLike$class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 635 */       return (That)TraversableLike$class.scan(this, z, op, cbf);
/*     */     }
/*     */     
/*     */     public Option<A> headOption() {
/* 635 */       return TraversableLike$class.headOption(this);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> tail() {
/* 635 */       return (SeqView<A, Repr>)TraversableLike$class.tail(this);
/*     */     }
/*     */     
/*     */     public A last() {
/* 635 */       return (A)TraversableLike$class.last(this);
/*     */     }
/*     */     
/*     */     public Option<A> lastOption() {
/* 635 */       return TraversableLike$class.lastOption(this);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> sliceWithKnownDelta(int from, int until, int delta) {
/* 635 */       return (SeqView<A, Repr>)TraversableLike$class.sliceWithKnownDelta(this, from, until, delta);
/*     */     }
/*     */     
/*     */     public SeqView<A, Repr> sliceWithKnownBound(int from, int until) {
/* 635 */       return (SeqView<A, Repr>)TraversableLike$class.sliceWithKnownBound(this, from, until);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<A, Repr>> tails() {
/* 635 */       return TraversableLike$class.tails(this);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<A, Repr>> inits() {
/* 635 */       return TraversableLike$class.inits(this);
/*     */     }
/*     */     
/*     */     public Traversable<A> toTraversable() {
/* 635 */       return TraversableLike$class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/* 635 */       return (Col)TraversableLike$class.to(this, cbf);
/*     */     }
/*     */     
/*     */     public ParSeq<A> par() {
/* 635 */       return (ParSeq<A>)Parallelizable$class.par(this);
/*     */     }
/*     */     
/*     */     public List<A> reversed() {
/* 635 */       return TraversableOnce$class.reversed(this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/* 635 */       return TraversableOnce$class.nonEmpty(this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/* 635 */       return TraversableOnce$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 635 */       return TraversableOnce$class.collectFirst(this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/* 635 */       return (B)TraversableOnce$class.$div$colon(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 635 */       return (B)TraversableOnce$class.$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/* 635 */       return (B)TraversableOnce$class.foldLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/* 635 */       return (B)TraversableOnce$class.reduceLeft(this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 635 */       return TraversableOnce$class.reduceLeftOption(this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 635 */       return TraversableOnce$class.reduceRightOption(this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 reduce(Function2 op) {
/* 635 */       return (A1)TraversableOnce$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 635 */       return TraversableOnce$class.reduceOption(this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 fold(Object z, Function2 op) {
/* 635 */       return (A1)TraversableOnce$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 635 */       return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> B sum(Numeric num) {
/* 635 */       return (B)TraversableOnce$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <B> B product(Numeric num) {
/* 635 */       return (B)TraversableOnce$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <B> A min(Ordering cmp) {
/* 635 */       return (A)TraversableOnce$class.min(this, cmp);
/*     */     }
/*     */     
/*     */     public <B> A max(Ordering cmp) {
/* 635 */       return (A)TraversableOnce$class.max(this, cmp);
/*     */     }
/*     */     
/*     */     public <B> A maxBy(Function1 f, Ordering cmp) {
/* 635 */       return (A)TraversableOnce$class.maxBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> A minBy(Function1 f, Ordering cmp) {
/* 635 */       return (A)TraversableOnce$class.minBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/* 635 */       TraversableOnce$class.copyToBuffer(this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/* 635 */       TraversableOnce$class.copyToArray(this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/* 635 */       TraversableOnce$class.copyToArray(this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/* 635 */       return TraversableOnce$class.toArray(this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<A> toList() {
/* 635 */       return TraversableOnce$class.toList(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<A> toIndexedSeq() {
/* 635 */       return TraversableOnce$class.toIndexedSeq(this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/* 635 */       return TraversableOnce$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/* 635 */       return TraversableOnce$class.toSet(this);
/*     */     }
/*     */     
/*     */     public Vector<A> toVector() {
/* 635 */       return TraversableOnce$class.toVector(this);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 635 */       return TraversableOnce$class.toMap(this, ev);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/* 635 */       return TraversableOnce$class.addString(this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/* 635 */       return TraversableOnce$class.addString(this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 635 */       return (A1)GenTraversableOnce$class.$div$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 635 */       return PartialFunction.class.orElse(this, that);
/*     */     }
/*     */     
/*     */     public <C> PartialFunction<Object, C> andThen(Function1 k) {
/* 635 */       return PartialFunction.class.andThen(this, k);
/*     */     }
/*     */     
/*     */     public Function1<Object, Option<A>> lift() {
/* 635 */       return PartialFunction.class.lift(this);
/*     */     }
/*     */     
/*     */     public <A1, B1> B1 applyOrElse(Object x, Function1 default) {
/* 635 */       return (B1)PartialFunction.class.applyOrElse(this, x, default);
/*     */     }
/*     */     
/*     */     public <U> Function1<Object, Object> runWith(Function1 action) {
/* 635 */       return PartialFunction.class.runWith(this, action);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZD$sp(double v1) {
/* 635 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/* 635 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/* 635 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/* 635 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/* 635 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/* 635 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/* 635 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/* 635 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/* 635 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/* 635 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/* 635 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/* 635 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/* 635 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/* 635 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/* 635 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/* 635 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/* 635 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/* 635 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/* 635 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/* 635 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/* 635 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/* 635 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/* 635 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/* 635 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, A> compose(Function1 g) {
/* 635 */       return Function1.class.compose((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 635 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 635 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public SeqLike$$anon$2(SeqLike $outer) {
/* 635 */       Function1.class.$init$((Function1)this);
/* 635 */       PartialFunction.class.$init$(this);
/* 635 */       GenTraversableOnce$class.$init$(this);
/* 635 */       TraversableOnce$class.$init$(this);
/* 635 */       Parallelizable$class.$init$(this);
/* 635 */       TraversableLike$class.$init$(this);
/* 635 */       GenericTraversableTemplate.class.$init$(this);
/* 635 */       GenTraversable$class.$init$(this);
/* 635 */       Traversable$class.$init$(this);
/* 635 */       GenIterable$class.$init$(this);
/* 635 */       IterableLike$class.$init$(this);
/* 635 */       Iterable$class.$init$(this);
/* 635 */       GenSeqLike$class.$init$(this);
/* 635 */       GenSeq$class.$init$(this);
/* 635 */       SeqLike$class.$init$(this);
/* 635 */       Seq$class.$init$(this);
/* 635 */       ViewMkString$class.$init$(this);
/* 635 */       GenTraversableViewLike$class.$init$(this);
/* 635 */       TraversableViewLike$class.$init$(this);
/* 635 */       GenIterableViewLike$class.$init$(this);
/* 635 */       IterableViewLike$class.$init$(this);
/* 635 */       GenSeqViewLike$class.$init$(this);
/* 635 */       SeqViewLike$class.$init$(this);
/*     */     }
/*     */     
/*     */     private Object underlying$lzycompute() {
/* 636 */       synchronized (this) {
/* 636 */         if (!this.bitmap$0) {
/* 636 */           this.underlying = (Repr)this.$outer.repr();
/* 636 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/SeqLike$$anon$2}} */
/* 636 */         return this.underlying;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Repr underlying() {
/* 636 */       return this.bitmap$0 ? this.underlying : (Repr)underlying$lzycompute();
/*     */     }
/*     */     
/*     */     public Iterator<A> iterator() {
/* 637 */       return this.$outer.iterator();
/*     */     }
/*     */     
/*     */     public int length() {
/* 638 */       return this.$outer.length();
/*     */     }
/*     */     
/*     */     public A apply(int idx) {
/* 639 */       return (A)this.$outer.apply(idx);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SeqLike$$anon$3 extends AbstractSeq<B> implements IndexedSeq<B> {
/*     */     private final int length;
/*     */     
/*     */     private final int n0$1;
/*     */     
/*     */     private final IndexedSeq x2$1;
/*     */     
/*     */     public GenericCompanion<IndexedSeq> companion() {
/* 667 */       return IndexedSeq$class.companion(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> seq() {
/* 667 */       return IndexedSeq$class.seq(this);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 667 */       return IndexedSeqLike$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> thisCollection() {
/* 667 */       return IndexedSeqLike$class.thisCollection(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> toCollection(Object repr) {
/* 667 */       return IndexedSeqLike$class.toCollection(this, repr);
/*     */     }
/*     */     
/*     */     public Iterator<B> iterator() {
/* 667 */       return IndexedSeqLike$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <A1> Buffer<A1> toBuffer() {
/* 667 */       return IndexedSeqLike$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public SeqLike$$anon$3(int n0$1, int n1$1, IndexedSeq x2$1) {
/* 667 */       IndexedSeqLike$class.$init$(this);
/* 667 */       IndexedSeq$class.$init$(this);
/* 668 */       this.length = n1$1 - n0$1;
/*     */     }
/*     */     
/*     */     public int length() {
/* 668 */       return this.length;
/*     */     }
/*     */     
/*     */     public B apply(int x) {
/* 669 */       return this.x2$1.apply(this.n0$1 + x);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SeqLike$$anon$4 extends AbstractSeq<B> implements IndexedSeq<B> {
/*     */     private final int n0$1;
/*     */     
/*     */     private final int n1$1;
/*     */     
/*     */     private final IndexedSeq x2$1;
/*     */     
/*     */     public GenericCompanion<IndexedSeq> companion() {
/* 671 */       return IndexedSeq$class.companion(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> seq() {
/* 671 */       return IndexedSeq$class.seq(this);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 671 */       return IndexedSeqLike$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> thisCollection() {
/* 671 */       return IndexedSeqLike$class.thisCollection(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> toCollection(Object repr) {
/* 671 */       return IndexedSeqLike$class.toCollection(this, repr);
/*     */     }
/*     */     
/*     */     public Iterator<B> iterator() {
/* 671 */       return IndexedSeqLike$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <A1> Buffer<A1> toBuffer() {
/* 671 */       return IndexedSeqLike$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public SeqLike$$anon$4(int n0$1, int n1$1, IndexedSeq x2$1) {
/* 671 */       IndexedSeqLike$class.$init$(this);
/* 671 */       IndexedSeq$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int length() {
/* 672 */       return this.n1$1 - this.n0$1;
/*     */     }
/*     */     
/*     */     public B apply(int x) {
/* 673 */       return this.x2$1.apply(this.n1$1 - 1 - x);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class SeqLike$$anon$5 extends AbstractSeq<B> implements IndexedSeq<B> {
/*     */     private final Object[] Warr;
/*     */     
/*     */     private final int delta;
/*     */     
/*     */     private final int done;
/*     */     
/*     */     private final Iterator<B> wit;
/*     */     
/*     */     private int i;
/*     */     
/*     */     private final int length;
/*     */     
/*     */     public GenericCompanion<IndexedSeq> companion() {
/* 678 */       return IndexedSeq$class.companion(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> seq() {
/* 678 */       return IndexedSeq$class.seq(this);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 678 */       return IndexedSeqLike$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> thisCollection() {
/* 678 */       return IndexedSeqLike$class.thisCollection(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> toCollection(Object repr) {
/* 678 */       return IndexedSeqLike$class.toCollection(this, repr);
/*     */     }
/*     */     
/*     */     public Iterator<B> iterator() {
/* 678 */       return IndexedSeqLike$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <A1> Buffer<A1> toBuffer() {
/* 678 */       return IndexedSeqLike$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public SeqLike$$anon$5(Seq<B> W$1, int n0$1, int n1$1, boolean forward$1) {
/* 678 */       IndexedSeqLike$class.$init$(this);
/* 678 */       IndexedSeq$class.$init$(this);
/* 679 */       this.Warr = new Object[n1$1 - n0$1];
/* 680 */       this.delta = forward$1 ? 1 : -1;
/* 681 */       this.done = forward$1 ? (n1$1 - n0$1) : -1;
/* 682 */       this.wit = W$1.iterator().drop(n0$1);
/* 683 */       this.i = forward$1 ? 0 : (n1$1 - n0$1 - 1);
/* 684 */       while (i() != this.done) {
/* 685 */         this.Warr[i()] = wit().next();
/* 686 */         i_$eq(i() + this.delta);
/*     */       } 
/* 689 */       this.length = n1$1 - n0$1;
/*     */     }
/*     */     
/*     */     public Iterator<B> wit() {
/*     */       return this.wit;
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
/*     */     public int length() {
/* 689 */       return this.length;
/*     */     }
/*     */     
/*     */     public B apply(int x) {
/* 690 */       return (B)this.Warr[x];
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SeqLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */