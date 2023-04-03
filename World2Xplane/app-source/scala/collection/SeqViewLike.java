/*     */ package scala.collection;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
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
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.immutable.Stream;
/*     */ import scala.collection.immutable.Vector;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.parallel.Combiner;
/*     */ import scala.collection.parallel.ParSeq;
/*     */ import scala.math.Numeric;
/*     */ import scala.math.Ordering;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\021uaaB\001\003!\003\r\ta\002\002\f'\026\fh+[3x\031&\\WM\003\002\004\t\005Q1m\0347mK\016$\030n\0348\013\003\025\tQa]2bY\006\034\001!\006\003\t')\0023c\002\001\n\033qi\003g\r\t\003\025-i\021\001B\005\003\031\021\021a!\0218z%\0264\007c\001\b\020#5\t!!\003\002\021\005\t\0311+Z9\021\005I\031B\002\001\003\007)\001!)\031A\013\003\003\005\013\"AF\r\021\005)9\022B\001\r\005\005\035qu\016\0365j]\036\004\"A\003\016\n\005m!!aA!osB!a\"H\t \023\tq\"AA\004TKFd\025n[3\021\005I\001CAB\021\001\t\013\007!E\001\003UQ&\034\030C\001\f$%\r!c\005\f\004\005K\001\0011E\001\007=e\0264\027N\\3nK:$h\b\005\003\017OEI\023B\001\025\003\005\035\031V-\035,jK^\004\"A\005\026\005\r-\002AQ1\001\026\005\021\031u\016\0347\021\0139\001\021#K\020\021\t9q\023#K\005\003_\t\021A\"\023;fe\006\024G.\032,jK^\004RAD\031\022S}I!A\r\002\003!%#XM]1cY\0264\026.Z<MS.,\007#\002\b5#%z\022BA\033\003\00599UM\\*fcZKWm\036'jW\026DQa\016\001\005\002a\na\001J5oSR$C#A\035\021\005)Q\024BA\036\005\005\021)f.\033;\007\017u\002\001\023aA\001}\tYAK]1og\032|'/\\3e+\ty$iE\003=\023\001#u\t\005\003\017O\005K\003C\001\nC\t\031\031E\b\"b\001+\t\t!\tE\002F\r\006k\021\001A\005\003{E\0022!\022%B\023\tiD\007C\0038y\021\005\001\bC\003Ly\031\005A*\001\004mK:<G\017[\013\002\033B\021!BT\005\003\037\022\0211!\0238u\021\025\tFH\"\001S\003\025\t\007\017\0357z)\t\t5\013C\003U!\002\007Q*A\002jIbDQA\026\037\005B]\013\001\002^8TiJLgn\032\013\0021B\021\021LX\007\0025*\0211\fX\001\005Y\006twMC\001^\003\021Q\027M^1\n\005}S&AB*ue&twM\002\004b\001\005\005!A\031\002\024\003\n\034HO]1diR\023\030M\\:g_JlW\rZ\013\003G\032\034R\001Y\005eO\"\0042AD\bf!\t\021b\r\002\004DA\022\025\r!\006\t\004\013\032+\007cA#=K\")!\016\031C\001W\0061A(\0338jiz\"\022\001\034\t\004\013\002,ga\0028\001!\003\r\na\034\002\n\0136\004H/\037,jK^\034R!\\\005qcN\0042!\022\037\027!\t)%/\003\002ocA\021Q\t^\005\003]R2qA\036\001\021\002G\005qO\001\004G_J\034W\rZ\013\003qr\034R!^\005z{~\0042!\022>|\023\t1\030\007\005\002\023y\022)1)\036b\001+A\031QI`>\n\005Y$\004cA#=w\032I\0211\001\001\021\002G\005\021Q\001\002\007'2L7-\0323\024\023\005\005\021\"a\002\002\f\005=\001cA#\002\n%\031\0211A\031\021\007\025\013i!C\002\002\004Q\0022!\022\037\022\r%\t\031\002\001I\001$\003\t)B\001\004NCB\004X\rZ\013\005\003/\tybE\005\002\022%\tI\"!\t\002&A)Q)a\007\002\036%\031\0211C\031\021\007I\ty\002\002\004D\003#\021\r!\006\t\006\013\006\r\022QD\005\004\003'!\004\003B#=\003;1\021\"!\013\001!\003\r\n!a\013\003\025\031c\027\r^'baB,G-\006\003\002.\005U2#CA\024\023\005=\022qGA\036!\025)\025\021GA\032\023\r\tI#\r\t\004%\005UBAB\"\002(\t\007Q\003E\003F\003s\t\031$C\002\002*Q\002B!\022\037\0024\031I\021q\b\001\021\002G\005\021\021\t\002\t\003B\004XM\0343fIV!\0211IA&'%\ti$CA#\003\037\n\031\006E\003F\003\017\nI%C\002\002@E\0022AEA&\t\035\031\025Q\bb\001\003\033\n\"!E\r\021\013\025\013\t&!\023\n\007\005}B\007\005\003Fy\005%c!CA,\001A\005\031\023AA-\005!1\025\016\034;fe\026$7#CA+\023\005m\023qLA\b!\r)\025QL\005\004\003/\n\004cA#\002b%\031\021q\013\033\007\023\005\025\004\001%A\022\002\005\035$A\003+bW\026tw\013[5mKNI\0211M\005\002j\0055\024q\002\t\004\013\006-\024bAA3cA\031Q)a\034\n\007\005\025DGB\005\002t\001\001\n1%\001\002v\taAI]8qa\026$w\013[5mKNI\021\021O\005\002x\005m\024q\002\t\004\013\006e\024bAA:cA\031Q)! \n\007\005MDGB\005\002\002\002\001\n1%\001\002\004\n1!,\0339qK\022,B!!\"\002\016NI\021qP\005\002\b\006=\0251\023\t\006\013\006%\0251R\005\004\003\003\013\004c\001\n\002\016\02211)a C\002U\001R!RAI\003\027K1!!!5!\021)E(!&\021\r)\t9*EAF\023\r\tI\n\002\002\007)V\004H.\032\032\007\023\005u\005\001%A\022\002\005}%!\003.jaB,G-\0217m+\031\t\t+!+\0020NI\0211T\005\002$\006E\026Q\027\t\b\013\006\025\026qUAW\023\r\ti*\r\t\004%\005%F\001CAV\0037\023\r!!\024\003\005\005\013\004c\001\n\0020\02211)a'C\002U\001r!RAZ\003O\013i+C\002\002\036R\002B!\022\037\0028B9!\"a&\002(\0065f!CA^\001A\005\031\023AA_\005!\021VM^3sg\026$7cBA]\023\005=\021q\030\t\004\013\006\005\027bAA^i\031I\021Q\031\001\021\002G\005\021q\031\002\b!\006$8\r[3e+\021\tI-a4\024\017\005\r\027\"a3\002RB!Q\tPAg!\r\021\022q\032\003\b\007\006\r'\031AA'!\025)\0251[Ag\023\r\t)\r\016\004\n\003/\004\001\023aI\001\0033\024\021\002\025:fa\026tG-\0323\026\t\005m\027\021]\n\b\003+L\021Q\\Ar!\021)E(a8\021\007I\t\t\017B\004D\003+\024\r!!\024\021\013\025\013)/a8\n\007\005]G\007C\004\002j\002!\t&a;\002\0239,wOR8sG\026$W\003BAw\003g$B!a<\002vB!Q\tPAy!\r\021\0221\037\003\007\007\006\035(\031A\013\t\023\005]\030q\035CA\002\005e\030A\001=t!\025Q\0211`A\000\023\r\ti\020\002\002\ty\tLh.Y7f}A)aB!\001\002r&\031!1\001\002\003\r\035+gnU3r\021\035\0219\001\001C)\005\023\t1B\\3x\003B\004XM\0343fIV!!1\002B\t)\021\021iAa\005\021\t\025c$q\002\t\004%\tEAaB\"\003\006\t\007\021Q\n\005\t\005+\021)\0011\001\003\030\005!A\017[1u!\025q!\021\004B\b\023\r\021YB\001\002\017\017\026tGK]1wKJ\034\030M\0317f\021\035\021y\002\001C)\005C\t\021B\\3x\033\006\004\b/\0323\026\t\t\r\"\021\006\013\005\005K\021Y\003\005\003Fy\t\035\002c\001\n\003*\02111I!\bC\002UA\001B!\f\003\036\001\007!qF\001\002MB1!B!\r\022\005OI1Aa\r\005\005%1UO\\2uS>t\027\007C\004\0038\001!\tF!\017\002\0339,wO\0227bi6\013\007\017]3e+\021\021YD!\021\025\t\tu\"1\t\t\005\013r\022y\004E\002\023\005\003\"aa\021B\033\005\004)\002\002\003B\027\005k\001\rA!\022\021\r)\021\t$\005B$!\025q!\021\nB \023\r\021YE\001\002\023\017\026tGK]1wKJ\034\030M\0317f\037:\034W\rC\004\003P\001!\tF!\025\002\0279,wOR5mi\026\024X\r\032\013\005\003\037\021\031\006\003\005\003V\t5\003\031\001B,\003\005\001\bC\002\006\0032E\021I\006E\002\013\0057J1A!\030\005\005\035\021un\0347fC:DqA!\031\001\t#\022\031'A\005oK^\034F.[2fIR!\021q\002B3\021!\0219Ga\030A\002\t%\024AC0f]\022\004x.\0338ugB!!1\016B9\033\t\021iGC\002\003p\t\tqaZ3oKJL7-\003\003\003t\t5$!D*mS\016,\027J\034;feZ\fG\016C\004\003x\001!\tF!\037\002\0379,w\017\022:paB,Gm\0265jY\026$B!a\004\003|!A!Q\013B;\001\004\0219\006C\004\003\000\001!\tF!!\002\0339,w\017V1lK:<\006.\0337f)\021\tyAa!\t\021\tU#Q\020a\001\005/BqAa\"\001\t#\022I)A\005oK^T\026\016\0359fIV!!1\022BJ)\021\021iI!&\021\t\025c$q\022\t\007\025\005]\025C!%\021\007I\021\031\n\002\004D\005\013\023\r!\006\005\t\005+\021)\t1\001\003\030B)aB!'\003\022&\031!1\024\002\003\027\035+g.\023;fe\006\024G.\032\005\b\005?\003A\021\013BQ\0031qWm\036.jaB,G-\0217m+\031\021\031Ka+\0030RA!Q\025BY\005k\023I\f\005\003Fy\t\035\006c\002\006\002\030\n%&Q\026\t\004%\t-F\001CAV\005;\023\r!!\024\021\007I\021y\013\002\004D\005;\023\r!\006\005\t\005+\021i\n1\001\0034B)aB!'\003.\"A!q\027BO\001\004\021I+A\005`i\"L7/\0227f[\"A!1\030BO\001\004\021i+A\005`i\"\fG/\0227f[\"9!q\030\001\005\022\t\005\027a\0038foJ+g/\032:tK\022,\"!a\004\t\017\t\025\007\001\"\005\003H\006Qa.Z<QCR\034\007.\0323\026\t\t%'q\032\013\t\005\027\024\tN!6\003\\B!Q\t\020Bg!\r\021\"q\032\003\b\007\n\r'\031AA'\021\035\021\031Na1A\0025\013Qa\0304s_6D\001Ba6\003D\002\007!\021\\\001\007?B\fGo\0315\021\0139\021\tA!4\t\017\tu'1\031a\001\033\006IqL]3qY\006\034W\r\032\005\b\005C\004A\021\003Br\0031qWm\036)sKB,g\016Z3e+\021\021)Oa;\025\t\t\035(Q\036\t\005\013r\022I\017E\002\023\005W$qa\021Bp\005\004\ti\005\003\005\003p\n}\007\031\001Bu\003\021)G.Z7\t\017\tM\b\001\"\025\003v\006Aa.Z<UC.,g\016\006\003\002\020\t]\bb\002B}\005c\004\r!T\001\002]\"9!Q \001\005R\t}\030A\0038fo\022\023x\016\0359fIR!\021qBB\001\021\035\021IPa?A\0025Cqa!\002\001\t\003\0329!A\004sKZ,'o]3\026\003}Aqaa\003\001\t\003\032i!A\003qCR\034\007.\006\004\004\020\r\0252Q\003\013\t\007#\0319ca\013\0040Q!11CB\r!\r\0212Q\003\003\b\007/\031IA1\001\026\005\021!\006.\031;\t\021\rm1\021\002a\002\007;\t!A\0314\021\023\t-4qD\020\004$\rM\021\002BB\021\005[\022AbQ1o\005VLG\016\032$s_6\0042AEB\023\t\035\0315\021\002b\001\003\033Bqa!\013\004\n\001\007Q*\001\003ge>l\007\002CB\006\007\023\001\ra!\f\021\0139\021\taa\t\t\017\rE2\021\002a\001\033\006A!/\0329mC\016,G\rC\004\0046\001!\tea\016\002\013A\fG\rV8\026\r\re2qIB )\031\031Yd!\023\004NQ!1QHB!!\r\0212q\b\003\b\007/\031\031D1\001\026\021!\031Yba\rA\004\r\r\003#\003B6\007?y2QIB\037!\r\0212q\t\003\b\007\016M\"\031AA'\021\035\031Yea\rA\0025\0131\001\\3o\021!\021yoa\rA\002\r\025\003bBB)\001\021\00531K\001\013e\0264XM]:f\033\006\004XCBB+\007G\032Y\006\006\003\004X\r\025D\003BB-\007;\0022AEB.\t\035\0319ba\024C\002UA\001ba\007\004P\001\0171q\f\t\n\005W\032ybHB1\0073\0022AEB2\t\031\0315q\nb\001+!A!QFB(\001\004\0319\007\005\004\013\005c\t2\021\r\005\b\007W\002A\021IB7\003\035)\b\017Z1uK\022,baa\034\004~\rUDCBB9\007\032\031\t\006\003\004t\r]\004c\001\n\004v\02191qCB5\005\004)\002\002CB\016\007S\002\035a!\037\021\023\t-4qD\020\004|\rM\004c\001\n\004~\02191i!\033C\002\0055\003bBBA\007S\002\r!T\001\006S:$W\r\037\005\t\005_\034I\0071\001\004|!91q\021\001\005B\r%\025a\003\023qYV\034HeY8m_:,baa#\004\032\016EE\003BBG\0077#Baa$\004\024B\031!c!%\005\017\r]1Q\021b\001+!A11DBC\001\b\031)\nE\005\003l\r}qda&\004\020B\031!c!'\005\017\r\033)I1\001\002N!A!q^BC\001\004\0319\nC\004\004 \002!\te!)\002\027\021\032w\016\\8oIAdWo]\013\007\007G\033\tl!+\025\t\r\02561\027\013\005\007O\033Y\013E\002\023\007S#qaa\006\004\036\n\007Q\003\003\005\004\034\ru\0059ABW!%\021Yga\b \007_\0339\013E\002\023\007c#qaQBO\005\004\ti\005\003\005\003p\016u\005\031ABX\021\035\0319\f\001C!\007s\013Q!\0368j_:,baa/\004J\016\005G\003BB_\007\027$Baa0\004DB\031!c!1\005\017\r]1Q\027b\001+!A11DB[\001\b\031)\rE\005\003l\r}qda2\004@B\031!c!3\005\017\r\033)L1\001\002N!A!QCB[\001\004\031i\rE\003\017\005\003\0319\rC\004\004R\002!\tea5\002\t\021LgMZ\013\005\007+\034i\016F\002 \007/D\001B!\006\004P\002\0071\021\034\t\006\035\t\00511\034\t\004%\ruGaB\"\004P\n\007\021Q\n\005\b\007C\004A\021IBr\003%Ig\016^3sg\026\034G/\006\003\004f\0165HcA\020\004h\"A!QCBp\001\004\031I\017E\003\017\005\003\031Y\017E\002\023\007[$qaQBp\005\004\ti\005C\004\004r\002!\tea=\002\rM|'\017^3e+\021\031)\020\"\006\025\007}\0319\020\003\005\004z\016=\b9AB~\003\ry'\017\032\t\007\007{$i\001b\005\017\t\r}H\021\002\b\005\t\003!9!\004\002\005\004)\031AQ\001\004\002\rq\022xn\034;?\023\005)\021b\001C\006\t\0059\001/Y2lC\036,\027\002\002C\b\t#\021\001b\024:eKJLgn\032\006\004\t\027!\001c\001\n\005\026\02191ia<C\002\0055\003b\002C\r\001\021\005C1D\001\rgR\024\030N\\4Qe\0264\027\016_\013\0021\002")
/*     */ public interface SeqViewLike<A, Coll, This extends SeqView<A, Coll> & SeqViewLike<A, Coll, This>> extends Seq<A>, SeqLike<A, This>, IterableView<A, Coll>, IterableViewLike<A, Coll, This>, GenSeqViewLike<A, Coll, This> {
/*     */   <B> Transformed<B> newForced(Function0<GenSeq<B>> paramFunction0);
/*     */   
/*     */   <B> Transformed<B> newAppended(GenTraversable<B> paramGenTraversable);
/*     */   
/*     */   <B> Transformed<B> newMapped(Function1<A, B> paramFunction1);
/*     */   
/*     */   <B> Transformed<B> newFlatMapped(Function1<A, GenTraversableOnce<B>> paramFunction1);
/*     */   
/*     */   Transformed<A> newFiltered(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Transformed<A> newSliced(SliceInterval paramSliceInterval);
/*     */   
/*     */   Transformed<A> newDroppedWhile(Function1<A, Object> paramFunction1);
/*     */   
/*     */   Transformed<A> newTakenWhile(Function1<A, Object> paramFunction1);
/*     */   
/*     */   <B> Transformed<Tuple2<A, B>> newZipped(GenIterable<B> paramGenIterable);
/*     */   
/*     */   <A1, B> Transformed<Tuple2<A1, B>> newZippedAll(GenIterable<B> paramGenIterable, A1 paramA1, B paramB);
/*     */   
/*     */   Transformed<A> newReversed();
/*     */   
/*     */   <B> Transformed<B> newPatched(int paramInt1, GenSeq<B> paramGenSeq, int paramInt2);
/*     */   
/*     */   <B> Transformed<B> newPrepended(B paramB);
/*     */   
/*     */   Transformed<A> newTaken(int paramInt);
/*     */   
/*     */   Transformed<A> newDropped(int paramInt);
/*     */   
/*     */   This reverse();
/*     */   
/*     */   <B, That> That patch(int paramInt1, GenSeq<B> paramGenSeq, int paramInt2, CanBuildFrom<This, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That padTo(int paramInt, B paramB, CanBuildFrom<This, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That reverseMap(Function1<A, B> paramFunction1, CanBuildFrom<This, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That updated(int paramInt, B paramB, CanBuildFrom<This, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That $plus$colon(B paramB, CanBuildFrom<This, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That $colon$plus(B paramB, CanBuildFrom<This, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B, That> That union(GenSeq<B> paramGenSeq, CanBuildFrom<This, B, That> paramCanBuildFrom);
/*     */   
/*     */   <B> This diff(GenSeq<B> paramGenSeq);
/*     */   
/*     */   <B> This intersect(GenSeq<B> paramGenSeq);
/*     */   
/*     */   <B> This sorted(Ordering<B> paramOrdering);
/*     */   
/*     */   String stringPrefix();
/*     */   
/*     */   public abstract class Transformed$class {
/*     */     public static void $init$(SeqViewLike.Transformed $this) {}
/*     */     
/*     */     public static String toString(SeqViewLike.Transformed $this) {
/*  39 */       return $this.viewToString();
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class AbstractTransformed<B> implements Seq<B>, IterableViewLike<A, Coll, This>.Transformed<B>, Transformed<B> {
/*     */     private final Object underlying;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public String toString() {
/*  43 */       return SeqViewLike.Transformed$class.toString(this);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<B, Coll, SeqView<B, Coll>>.Transformed<B> newForced(Function0 xs) {
/*  43 */       return SeqViewLike$class.newForced(this, xs);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<B, Coll, SeqView<B, Coll>>.Transformed<B> newAppended(GenTraversable that) {
/*  43 */       return SeqViewLike$class.newAppended(this, that);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<B, Coll, SeqView<B, Coll>>.Transformed<B> newMapped(Function1 f) {
/*  43 */       return SeqViewLike$class.newMapped(this, f);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<B, Coll, SeqView<B, Coll>>.Transformed<B> newFlatMapped(Function1 f) {
/*  43 */       return SeqViewLike$class.newFlatMapped(this, f);
/*     */     }
/*     */     
/*     */     public SeqViewLike<B, Coll, SeqView<B, Coll>>.Transformed<B> newFiltered(Function1 p) {
/*  43 */       return SeqViewLike$class.newFiltered(this, p);
/*     */     }
/*     */     
/*     */     public SeqViewLike<B, Coll, SeqView<B, Coll>>.Transformed<B> newSliced(SliceInterval _endpoints) {
/*  43 */       return SeqViewLike$class.newSliced(this, _endpoints);
/*     */     }
/*     */     
/*     */     public SeqViewLike<B, Coll, SeqView<B, Coll>>.Transformed<B> newDroppedWhile(Function1 p) {
/*  43 */       return SeqViewLike$class.newDroppedWhile(this, p);
/*     */     }
/*     */     
/*     */     public SeqViewLike<B, Coll, SeqView<B, Coll>>.Transformed<B> newTakenWhile(Function1 p) {
/*  43 */       return SeqViewLike$class.newTakenWhile(this, p);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<B, Coll, SeqView<B, Coll>>.Transformed<Tuple2<B, B>> newZipped(GenIterable that) {
/*  43 */       return SeqViewLike$class.newZipped(this, that);
/*     */     }
/*     */     
/*     */     public <A1, B> SeqViewLike<B, Coll, SeqView<B, Coll>>.Transformed<Tuple2<A1, B>> newZippedAll(GenIterable that, Object _thisElem, Object _thatElem) {
/*  43 */       return SeqViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
/*     */     }
/*     */     
/*     */     public SeqViewLike<B, Coll, SeqView<B, Coll>>.Transformed<B> newReversed() {
/*  43 */       return SeqViewLike$class.newReversed(this);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<B, Coll, SeqView<B, Coll>>.Transformed<B> newPatched(int _from, GenSeq _patch, int _replaced) {
/*  43 */       return SeqViewLike$class.newPatched(this, _from, _patch, _replaced);
/*     */     }
/*     */     
/*     */     public <B> SeqViewLike<B, Coll, SeqView<B, Coll>>.Transformed<B> newPrepended(Object elem) {
/*  43 */       return SeqViewLike$class.newPrepended(this, elem);
/*     */     }
/*     */     
/*     */     public SeqViewLike<B, Coll, SeqView<B, Coll>>.Transformed<B> newTaken(int n) {
/*  43 */       return SeqViewLike$class.newTaken(this, n);
/*     */     }
/*     */     
/*     */     public SeqViewLike<B, Coll, SeqView<B, Coll>>.Transformed<B> newDropped(int n) {
/*  43 */       return SeqViewLike$class.newDropped(this, n);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> reverse() {
/*  43 */       return SeqViewLike$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <B, That> That patch(int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/*  43 */       return (That)SeqViewLike$class.patch(this, from, patch, replaced, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That padTo(int len, Object elem, CanBuildFrom bf) {
/*  43 */       return (That)SeqViewLike$class.padTo(this, len, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That reverseMap(Function1 f, CanBuildFrom bf) {
/*  43 */       return (That)SeqViewLike$class.reverseMap(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That updated(int index, Object elem, CanBuildFrom bf) {
/*  43 */       return (That)SeqViewLike$class.updated(this, index, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/*  43 */       return (That)SeqViewLike$class.$plus$colon(this, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $colon$plus(Object elem, CanBuildFrom bf) {
/*  43 */       return (That)SeqViewLike$class.$colon$plus(this, elem, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That union(GenSeq that, CanBuildFrom bf) {
/*  43 */       return (That)SeqViewLike$class.union(this, that, bf);
/*     */     }
/*     */     
/*     */     public <B> SeqView<B, Coll> diff(GenSeq that) {
/*  43 */       return SeqViewLike$class.diff(this, that);
/*     */     }
/*     */     
/*     */     public <B> SeqView<B, Coll> intersect(GenSeq that) {
/*  43 */       return SeqViewLike$class.intersect(this, that);
/*     */     }
/*     */     
/*     */     public <B> SeqView<B, Coll> sorted(Ordering ord) {
/*  43 */       return SeqViewLike$class.sorted(this, ord);
/*     */     }
/*     */     
/*     */     public String stringPrefix() {
/*  43 */       return SeqViewLike$class.stringPrefix(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  43 */       IterableViewLike.Transformed$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*  43 */       return GenIterableViewLike.Transformed$class.isEmpty(this);
/*     */     }
/*     */     
/*     */     public Option<B> headOption() {
/*  43 */       return TraversableViewLike.Transformed$class.headOption(this);
/*     */     }
/*     */     
/*     */     public Option<B> lastOption() {
/*  43 */       return TraversableViewLike.Transformed$class.lastOption(this);
/*     */     }
/*     */     
/*     */     private Object underlying$lzycompute() {
/*  43 */       synchronized (this) {
/*  43 */         if (!this.bitmap$0) {
/*  43 */           this.underlying = GenTraversableViewLike.Transformed$class.underlying(this);
/*  43 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/collection/SeqViewLike}.Lscala/collection/SeqViewLike$AbstractTransformed;}} */
/*  43 */         return this.underlying;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Coll underlying() {
/*  43 */       return this.bitmap$0 ? (Coll)this.underlying : (Coll)underlying$lzycompute();
/*     */     }
/*     */     
/*     */     public final String viewIdString() {
/*  43 */       return GenTraversableViewLike.Transformed$class.viewIdString(this);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> drop(int n) {
/*  43 */       return (SeqView<B, Coll>)IterableViewLike$class.drop(this, n);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> take(int n) {
/*  43 */       return (SeqView<B, Coll>)IterableViewLike$class.take(this, n);
/*     */     }
/*     */     
/*     */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/*  43 */       return (That)IterableViewLike$class.zip(this, that, bf);
/*     */     }
/*     */     
/*     */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/*  43 */       return (That)IterableViewLike$class.zipWithIndex(this, bf);
/*     */     }
/*     */     
/*     */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/*  43 */       return (That)IterableViewLike$class.zipAll(this, that, thisElem, thatElem, bf);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<B, Coll>> grouped(int size) {
/*  43 */       return IterableViewLike$class.grouped(this, size);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<B, Coll>> sliding(int size, int step) {
/*  43 */       return IterableViewLike$class.sliding(this, size, step);
/*     */     }
/*     */     
/*     */     public Builder<B, SeqView<B, Coll>> newBuilder() {
/*  43 */       return TraversableViewLike$class.newBuilder(this);
/*     */     }
/*     */     
/*     */     public String viewIdentifier() {
/*  43 */       return TraversableViewLike$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public <B, That> That force(CanBuildFrom bf) {
/*  43 */       return (That)TraversableViewLike$class.force(this, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus(GenTraversableOnce xs, CanBuildFrom bf) {
/*  43 */       return (That)TraversableViewLike$class.$plus$plus(this, xs, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/*  43 */       return (That)TraversableViewLike$class.map(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/*  43 */       return (That)TraversableViewLike$class.collect(this, pf, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/*  43 */       return (That)TraversableViewLike$class.flatMap(this, f, bf);
/*     */     }
/*     */     
/*     */     public <B> TraversableViewLike<B, Coll, SeqView<B, Coll>>.Transformed<B> flatten(Function1 asTraversable) {
/*  43 */       return TraversableViewLike$class.flatten(this, asTraversable);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> filter(Function1 p) {
/*  43 */       return (SeqView<B, Coll>)TraversableViewLike$class.filter(this, p);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> withFilter(Function1 p) {
/*  43 */       return (SeqView<B, Coll>)TraversableViewLike$class.withFilter(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<SeqView<B, Coll>, SeqView<B, Coll>> partition(Function1 p) {
/*  43 */       return TraversableViewLike$class.partition(this, p);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> init() {
/*  43 */       return (SeqView<B, Coll>)TraversableViewLike$class.init(this);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> slice(int from, int until) {
/*  43 */       return (SeqView<B, Coll>)TraversableViewLike$class.slice(this, from, until);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> dropWhile(Function1 p) {
/*  43 */       return (SeqView<B, Coll>)TraversableViewLike$class.dropWhile(this, p);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> takeWhile(Function1 p) {
/*  43 */       return (SeqView<B, Coll>)TraversableViewLike$class.takeWhile(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<SeqView<B, Coll>, SeqView<B, Coll>> span(Function1 p) {
/*  43 */       return TraversableViewLike$class.span(this, p);
/*     */     }
/*     */     
/*     */     public Tuple2<SeqView<B, Coll>, SeqView<B, Coll>> splitAt(int n) {
/*  43 */       return TraversableViewLike$class.splitAt(this, n);
/*     */     }
/*     */     
/*     */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/*  43 */       return (That)TraversableViewLike$class.scanLeft(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/*  43 */       return (That)TraversableViewLike$class.scanRight(this, z, op, bf);
/*     */     }
/*     */     
/*     */     public <K> Map<K, SeqView<B, Coll>> groupBy(Function1 f) {
/*  43 */       return TraversableViewLike$class.groupBy(this, f);
/*     */     }
/*     */     
/*     */     public <A1, A2> Tuple2<TraversableViewLike<B, Coll, SeqView<B, Coll>>.Transformed<A1>, TraversableViewLike<B, Coll, SeqView<B, Coll>>.Transformed<A2>> unzip(Function1 asPair) {
/*  43 */       return TraversableViewLike$class.unzip(this, asPair);
/*     */     }
/*     */     
/*     */     public <A1, A2, A3> Tuple3<TraversableViewLike<B, Coll, SeqView<B, Coll>>.Transformed<A1>, TraversableViewLike<B, Coll, SeqView<B, Coll>>.Transformed<A2>, TraversableViewLike<B, Coll, SeqView<B, Coll>>.Transformed<A3>> unzip3(Function1 asTriple) {
/*  43 */       return TraversableViewLike$class.unzip3(this, asTriple);
/*     */     }
/*     */     
/*     */     public String viewToString() {
/*  43 */       return GenTraversableViewLike$class.viewToString(this);
/*     */     }
/*     */     
/*     */     public Seq<B> thisSeq() {
/*  43 */       return ViewMkString$class.thisSeq(this);
/*     */     }
/*     */     
/*     */     public String mkString() {
/*  43 */       return ViewMkString$class.mkString(this);
/*     */     }
/*     */     
/*     */     public String mkString(String sep) {
/*  43 */       return ViewMkString$class.mkString(this, sep);
/*     */     }
/*     */     
/*     */     public String mkString(String start, String sep, String end) {
/*  43 */       return ViewMkString$class.mkString(this, start, sep, end);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/*  43 */       return ViewMkString$class.addString(this, b, start, sep, end);
/*     */     }
/*     */     
/*     */     public GenericCompanion<Seq> companion() {
/*  43 */       return Seq$class.companion(this);
/*     */     }
/*     */     
/*     */     public Seq<B> seq() {
/*  43 */       return Seq$class.seq(this);
/*     */     }
/*     */     
/*     */     public Seq<B> thisCollection() {
/*  43 */       return SeqLike$class.thisCollection(this);
/*     */     }
/*     */     
/*     */     public Seq<B> toCollection(Object repr) {
/*  43 */       return SeqLike$class.toCollection(this, repr);
/*     */     }
/*     */     
/*     */     public Combiner<B, ParSeq<B>> parCombiner() {
/*  43 */       return SeqLike$class.parCombiner(this);
/*     */     }
/*     */     
/*     */     public int lengthCompare(int len) {
/*  43 */       return SeqLike$class.lengthCompare(this, len);
/*     */     }
/*     */     
/*     */     public int size() {
/*  43 */       return SeqLike$class.size(this);
/*     */     }
/*     */     
/*     */     public int segmentLength(Function1 p, int from) {
/*  43 */       return SeqLike$class.segmentLength(this, p, from);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p, int from) {
/*  43 */       return SeqLike$class.indexWhere(this, p, from);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 p, int end) {
/*  43 */       return SeqLike$class.lastIndexWhere(this, p, end);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<B, Coll>> permutations() {
/*  43 */       return SeqLike$class.permutations(this);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<B, Coll>> combinations(int n) {
/*  43 */       return SeqLike$class.combinations(this, n);
/*     */     }
/*     */     
/*     */     public Iterator<B> reverseIterator() {
/*  43 */       return SeqLike$class.reverseIterator(this);
/*     */     }
/*     */     
/*     */     public <B> boolean startsWith(GenSeq that, int offset) {
/*  43 */       return SeqLike$class.startsWith(this, that, offset);
/*     */     }
/*     */     
/*     */     public <B> boolean endsWith(GenSeq that) {
/*  43 */       return SeqLike$class.endsWith(this, that);
/*     */     }
/*     */     
/*     */     public <B> int indexOfSlice(GenSeq that) {
/*  43 */       return SeqLike$class.indexOfSlice(this, that);
/*     */     }
/*     */     
/*     */     public <B> int indexOfSlice(GenSeq that, int from) {
/*  43 */       return SeqLike$class.indexOfSlice(this, that, from);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOfSlice(GenSeq that) {
/*  43 */       return SeqLike$class.lastIndexOfSlice(this, that);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOfSlice(GenSeq that, int end) {
/*  43 */       return SeqLike$class.lastIndexOfSlice(this, that, end);
/*     */     }
/*     */     
/*     */     public <B> boolean containsSlice(GenSeq that) {
/*  43 */       return SeqLike$class.containsSlice(this, that);
/*     */     }
/*     */     
/*     */     public boolean contains(Object elem) {
/*  43 */       return SeqLike$class.contains(this, elem);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> distinct() {
/*  43 */       return (SeqView<B, Coll>)SeqLike$class.distinct(this);
/*     */     }
/*     */     
/*     */     public <B> boolean corresponds(GenSeq that, Function2 p) {
/*  43 */       return SeqLike$class.corresponds(this, that, p);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> sortWith(Function2 lt) {
/*  43 */       return (SeqView<B, Coll>)SeqLike$class.sortWith(this, lt);
/*     */     }
/*     */     
/*     */     public <B> SeqView<B, Coll> sortBy(Function1 f, Ordering ord) {
/*  43 */       return (SeqView<B, Coll>)SeqLike$class.sortBy(this, f, ord);
/*     */     }
/*     */     
/*     */     public Seq<B> toSeq() {
/*  43 */       return SeqLike$class.toSeq(this);
/*     */     }
/*     */     
/*     */     public Range indices() {
/*  43 */       return SeqLike$class.indices(this);
/*     */     }
/*     */     
/*     */     public Object view() {
/*  43 */       return SeqLike$class.view(this);
/*     */     }
/*     */     
/*     */     public SeqView<B, SeqView<B, Coll>> view(int from, int until) {
/*  43 */       return SeqLike$class.view(this, from, until);
/*     */     }
/*     */     
/*     */     public boolean isDefinedAt(int idx) {
/*  43 */       return GenSeqLike$class.isDefinedAt(this, idx);
/*     */     }
/*     */     
/*     */     public int prefixLength(Function1 p) {
/*  43 */       return GenSeqLike$class.prefixLength(this, p);
/*     */     }
/*     */     
/*     */     public int indexWhere(Function1 p) {
/*  43 */       return GenSeqLike$class.indexWhere(this, p);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem) {
/*  43 */       return GenSeqLike$class.indexOf(this, elem);
/*     */     }
/*     */     
/*     */     public <B> int indexOf(Object elem, int from) {
/*  43 */       return GenSeqLike$class.indexOf(this, elem, from);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOf(Object elem) {
/*  43 */       return GenSeqLike$class.lastIndexOf(this, elem);
/*     */     }
/*     */     
/*     */     public <B> int lastIndexOf(Object elem, int end) {
/*  43 */       return GenSeqLike$class.lastIndexOf(this, elem, end);
/*     */     }
/*     */     
/*     */     public int lastIndexWhere(Function1 p) {
/*  43 */       return GenSeqLike$class.lastIndexWhere(this, p);
/*     */     }
/*     */     
/*     */     public <B> boolean startsWith(GenSeq that) {
/*  43 */       return GenSeqLike$class.startsWith(this, that);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/*  43 */       return GenSeqLike$class.hashCode(this);
/*     */     }
/*     */     
/*     */     public boolean equals(Object that) {
/*  43 */       return GenSeqLike$class.equals(this, that);
/*     */     }
/*     */     
/*     */     public boolean forall(Function1 p) {
/*  43 */       return IterableLike$class.forall(this, p);
/*     */     }
/*     */     
/*     */     public boolean exists(Function1 p) {
/*  43 */       return IterableLike$class.exists(this, p);
/*     */     }
/*     */     
/*     */     public Option<B> find(Function1 p) {
/*  43 */       return IterableLike$class.find(this, p);
/*     */     }
/*     */     
/*     */     public <B> B foldRight(Object z, Function2 op) {
/*  43 */       return (B)IterableLike$class.foldRight(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceRight(Function2 op) {
/*  43 */       return (B)IterableLike$class.reduceRight(this, op);
/*     */     }
/*     */     
/*     */     public Iterable<B> toIterable() {
/*  43 */       return IterableLike$class.toIterable(this);
/*     */     }
/*     */     
/*     */     public Iterator<B> toIterator() {
/*  43 */       return IterableLike$class.toIterator(this);
/*     */     }
/*     */     
/*     */     public B head() {
/*  43 */       return (B)IterableLike$class.head(this);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<B, Coll>> sliding(int size) {
/*  43 */       return IterableLike$class.sliding(this, size);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> takeRight(int n) {
/*  43 */       return (SeqView<B, Coll>)IterableLike$class.takeRight(this, n);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> dropRight(int n) {
/*  43 */       return (SeqView<B, Coll>)IterableLike$class.dropRight(this, n);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start, int len) {
/*  43 */       IterableLike$class.copyToArray(this, xs, start, len);
/*     */     }
/*     */     
/*     */     public <B> boolean sameElements(GenIterable that) {
/*  43 */       return IterableLike$class.sameElements(this, that);
/*     */     }
/*     */     
/*     */     public Stream<B> toStream() {
/*  43 */       return IterableLike$class.toStream(this);
/*     */     }
/*     */     
/*     */     public boolean canEqual(Object that) {
/*  43 */       return IterableLike$class.canEqual(this, that);
/*     */     }
/*     */     
/*     */     public <B> Builder<B, Seq<B>> genericBuilder() {
/*  43 */       return GenericTraversableTemplate.class.genericBuilder(this);
/*     */     }
/*     */     
/*     */     public <B> Seq<Seq<B>> transpose(Function1 asTraversable) {
/*  43 */       return (Seq<Seq<B>>)GenericTraversableTemplate.class.transpose(this, asTraversable);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> repr() {
/*  43 */       return (SeqView<B, Coll>)TraversableLike$class.repr(this);
/*     */     }
/*     */     
/*     */     public final boolean isTraversableAgain() {
/*  43 */       return TraversableLike$class.isTraversableAgain(this);
/*     */     }
/*     */     
/*     */     public boolean hasDefiniteSize() {
/*  43 */       return TraversableLike$class.hasDefiniteSize(this);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/*  43 */       return (That)TraversableLike$class.$plus$plus$colon(this, that, bf);
/*     */     }
/*     */     
/*     */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/*  43 */       return (That)TraversableLike$class.$plus$plus$colon(this, that, bf);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> filterNot(Function1 p) {
/*  43 */       return (SeqView<B, Coll>)TraversableLike$class.filterNot(this, p);
/*     */     }
/*     */     
/*     */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/*  43 */       return (That)TraversableLike$class.scan(this, z, op, cbf);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> tail() {
/*  43 */       return (SeqView<B, Coll>)TraversableLike$class.tail(this);
/*     */     }
/*     */     
/*     */     public B last() {
/*  43 */       return (B)TraversableLike$class.last(this);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> sliceWithKnownDelta(int from, int until, int delta) {
/*  43 */       return (SeqView<B, Coll>)TraversableLike$class.sliceWithKnownDelta(this, from, until, delta);
/*     */     }
/*     */     
/*     */     public SeqView<B, Coll> sliceWithKnownBound(int from, int until) {
/*  43 */       return (SeqView<B, Coll>)TraversableLike$class.sliceWithKnownBound(this, from, until);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<B, Coll>> tails() {
/*  43 */       return TraversableLike$class.tails(this);
/*     */     }
/*     */     
/*     */     public Iterator<SeqView<B, Coll>> inits() {
/*  43 */       return TraversableLike$class.inits(this);
/*     */     }
/*     */     
/*     */     public Traversable<B> toTraversable() {
/*  43 */       return TraversableLike$class.toTraversable(this);
/*     */     }
/*     */     
/*     */     public <Col> Col to(CanBuildFrom cbf) {
/*  43 */       return (Col)TraversableLike$class.to(this, cbf);
/*     */     }
/*     */     
/*     */     public ParSeq<B> par() {
/*  43 */       return (ParSeq<B>)Parallelizable$class.par(this);
/*     */     }
/*     */     
/*     */     public List<B> reversed() {
/*  43 */       return TraversableOnce$class.reversed(this);
/*     */     }
/*     */     
/*     */     public boolean nonEmpty() {
/*  43 */       return TraversableOnce$class.nonEmpty(this);
/*     */     }
/*     */     
/*     */     public int count(Function1 p) {
/*  43 */       return TraversableOnce$class.count(this, p);
/*     */     }
/*     */     
/*     */     public <B> Option<B> collectFirst(PartialFunction pf) {
/*  43 */       return TraversableOnce$class.collectFirst(this, pf);
/*     */     }
/*     */     
/*     */     public <B> B $div$colon(Object z, Function2 op) {
/*  43 */       return (B)TraversableOnce$class.$div$colon(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B $colon$bslash(Object z, Function2 op) {
/*  43 */       return (B)TraversableOnce$class.$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B foldLeft(Object z, Function2 op) {
/*  43 */       return (B)TraversableOnce$class.foldLeft(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B reduceLeft(Function2 op) {
/*  43 */       return (B)TraversableOnce$class.reduceLeft(this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceLeftOption(Function2 op) {
/*  43 */       return TraversableOnce$class.reduceLeftOption(this, op);
/*     */     }
/*     */     
/*     */     public <B> Option<B> reduceRightOption(Function2 op) {
/*  43 */       return TraversableOnce$class.reduceRightOption(this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 reduce(Function2 op) {
/*  43 */       return (A1)TraversableOnce$class.reduce(this, op);
/*     */     }
/*     */     
/*     */     public <A1> Option<A1> reduceOption(Function2 op) {
/*  43 */       return TraversableOnce$class.reduceOption(this, op);
/*     */     }
/*     */     
/*     */     public <A1> A1 fold(Object z, Function2 op) {
/*  43 */       return (A1)TraversableOnce$class.fold(this, z, op);
/*     */     }
/*     */     
/*     */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/*  43 */       return (B)TraversableOnce$class.aggregate(this, z, seqop, combop);
/*     */     }
/*     */     
/*     */     public <B> B sum(Numeric num) {
/*  43 */       return (B)TraversableOnce$class.sum(this, num);
/*     */     }
/*     */     
/*     */     public <B> B product(Numeric num) {
/*  43 */       return (B)TraversableOnce$class.product(this, num);
/*     */     }
/*     */     
/*     */     public <B> B min(Ordering cmp) {
/*  43 */       return (B)TraversableOnce$class.min(this, cmp);
/*     */     }
/*     */     
/*     */     public <B> B max(Ordering cmp) {
/*  43 */       return (B)TraversableOnce$class.max(this, cmp);
/*     */     }
/*     */     
/*     */     public <B> B maxBy(Function1 f, Ordering cmp) {
/*  43 */       return (B)TraversableOnce$class.maxBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> B minBy(Function1 f, Ordering cmp) {
/*  43 */       return (B)TraversableOnce$class.minBy(this, f, cmp);
/*     */     }
/*     */     
/*     */     public <B> void copyToBuffer(Buffer dest) {
/*  43 */       TraversableOnce$class.copyToBuffer(this, dest);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs, int start) {
/*  43 */       TraversableOnce$class.copyToArray(this, xs, start);
/*     */     }
/*     */     
/*     */     public <B> void copyToArray(Object xs) {
/*  43 */       TraversableOnce$class.copyToArray(this, xs);
/*     */     }
/*     */     
/*     */     public <B> Object toArray(ClassTag evidence$1) {
/*  43 */       return TraversableOnce$class.toArray(this, evidence$1);
/*     */     }
/*     */     
/*     */     public List<B> toList() {
/*  43 */       return TraversableOnce$class.toList(this);
/*     */     }
/*     */     
/*     */     public IndexedSeq<B> toIndexedSeq() {
/*  43 */       return TraversableOnce$class.toIndexedSeq(this);
/*     */     }
/*     */     
/*     */     public <B> Buffer<B> toBuffer() {
/*  43 */       return TraversableOnce$class.toBuffer(this);
/*     */     }
/*     */     
/*     */     public <B> Set<B> toSet() {
/*  43 */       return TraversableOnce$class.toSet(this);
/*     */     }
/*     */     
/*     */     public Vector<B> toVector() {
/*  43 */       return TraversableOnce$class.toVector(this);
/*     */     }
/*     */     
/*     */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/*  43 */       return TraversableOnce$class.toMap(this, ev);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b, String sep) {
/*  43 */       return TraversableOnce$class.addString(this, b, sep);
/*     */     }
/*     */     
/*     */     public StringBuilder addString(StringBuilder b) {
/*  43 */       return TraversableOnce$class.addString(this, b);
/*     */     }
/*     */     
/*     */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/*  43 */       return (A1)GenTraversableOnce$class.$div$colon$bslash(this, z, op);
/*     */     }
/*     */     
/*     */     public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/*  43 */       return PartialFunction.class.orElse(this, that);
/*     */     }
/*     */     
/*     */     public <C> PartialFunction<Object, C> andThen(Function1 k) {
/*  43 */       return PartialFunction.class.andThen(this, k);
/*     */     }
/*     */     
/*     */     public Function1<Object, Option<B>> lift() {
/*  43 */       return PartialFunction.class.lift(this);
/*     */     }
/*     */     
/*     */     public <A1, B1> B1 applyOrElse(Object x, Function1 default) {
/*  43 */       return (B1)PartialFunction.class.applyOrElse(this, x, default);
/*     */     }
/*     */     
/*     */     public <U> Function1<Object, Object> runWith(Function1 action) {
/*  43 */       return PartialFunction.class.runWith(this, action);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZD$sp(double v1) {
/*  43 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDD$sp(double v1) {
/*  43 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFD$sp(double v1) {
/*  43 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcID$sp(double v1) {
/*  43 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJD$sp(double v1) {
/*  43 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVD$sp(double v1) {
/*  43 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZF$sp(float v1) {
/*  43 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDF$sp(float v1) {
/*  43 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFF$sp(float v1) {
/*  43 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIF$sp(float v1) {
/*  43 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJF$sp(float v1) {
/*  43 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVF$sp(float v1) {
/*  43 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZI$sp(int v1) {
/*  43 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDI$sp(int v1) {
/*  43 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFI$sp(int v1) {
/*  43 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcII$sp(int v1) {
/*  43 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJI$sp(int v1) {
/*  43 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int v1) {
/*  43 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public boolean apply$mcZJ$sp(long v1) {
/*  43 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public double apply$mcDJ$sp(long v1) {
/*  43 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public float apply$mcFJ$sp(long v1) {
/*  43 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public int apply$mcIJ$sp(long v1) {
/*  43 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public long apply$mcJJ$sp(long v1) {
/*  43 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public void apply$mcVJ$sp(long v1) {
/*  43 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, B> compose(Function1 g) {
/*  43 */       return Function1.class.compose((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/*  43 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/*  43 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*     */     }
/*     */     
/*     */     public AbstractTransformed(SeqViewLike $outer) {
/*  43 */       Function1.class.$init$((Function1)this);
/*  43 */       PartialFunction.class.$init$(this);
/*  43 */       GenTraversableOnce$class.$init$(this);
/*  43 */       TraversableOnce$class.$init$(this);
/*  43 */       Parallelizable$class.$init$(this);
/*  43 */       TraversableLike$class.$init$(this);
/*  43 */       GenericTraversableTemplate.class.$init$(this);
/*  43 */       GenTraversable$class.$init$(this);
/*  43 */       Traversable$class.$init$(this);
/*  43 */       GenIterable$class.$init$(this);
/*  43 */       IterableLike$class.$init$(this);
/*  43 */       Iterable$class.$init$(this);
/*  43 */       GenSeqLike$class.$init$(this);
/*  43 */       GenSeq$class.$init$(this);
/*  43 */       SeqLike$class.$init$(this);
/*  43 */       Seq$class.$init$(this);
/*  43 */       ViewMkString$class.$init$(this);
/*  43 */       GenTraversableViewLike$class.$init$(this);
/*  43 */       TraversableViewLike$class.$init$(this);
/*  43 */       GenIterableViewLike$class.$init$(this);
/*  43 */       IterableViewLike$class.$init$(this);
/*  43 */       GenTraversableViewLike.Transformed$class.$init$(this);
/*  43 */       TraversableViewLike.Transformed$class.$init$(this);
/*  43 */       GenIterableViewLike.Transformed$class.$init$(this);
/*  43 */       IterableViewLike.Transformed$class.$init$(this);
/*  43 */       GenSeqViewLike$class.$init$(this);
/*  43 */       SeqViewLike$class.$init$(this);
/*  43 */       GenSeqViewLike.Transformed$class.$init$(this);
/*  43 */       SeqViewLike.Transformed$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anon$1 extends AbstractTransformed<B> implements Forced<B> {
/*     */     private final GenSeq<B> forced;
/*     */     
/*     */     public int length() {
/*  76 */       return GenSeqViewLike.Forced$class.length(this);
/*     */     }
/*     */     
/*     */     public B apply(int idx) {
/*  76 */       return (B)GenSeqViewLike.Forced$class.apply(this, idx);
/*     */     }
/*     */     
/*     */     public Iterator<B> iterator() {
/*  76 */       return GenIterableViewLike.Forced$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  76 */       GenTraversableViewLike.Forced$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  76 */       return GenTraversableViewLike.Forced$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public GenSeq<B> forced() {
/*  76 */       return this.forced;
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anon$1(SeqViewLike<A, Coll, This> $outer, Function0 xs$1) {
/*  76 */       super($outer);
/*  76 */       GenTraversableViewLike.Forced$class.$init$(this);
/*  76 */       GenIterableViewLike.Forced$class.$init$(this);
/*  76 */       GenSeqViewLike.Forced$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anon$2 extends AbstractTransformed<B> implements Appended<B> {
/*     */     private final GenTraversable<B> rest;
/*     */     
/*     */     private final GenSeq<Object> restSeq;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     private GenSeq restSeq$lzycompute() {
/*  77 */       synchronized (this) {
/*  77 */         if (!this.bitmap$0) {
/*  77 */           this.restSeq = GenSeqViewLike.Appended$class.restSeq(this);
/*  77 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/SeqViewLike$$anon$2}} */
/*  77 */         return this.restSeq;
/*     */       } 
/*     */     }
/*     */     
/*     */     public GenSeq<B> restSeq() {
/*  77 */       return this.bitmap$0 ? (GenSeq)this.restSeq : restSeq$lzycompute();
/*     */     }
/*     */     
/*     */     public int length() {
/*  77 */       return GenSeqViewLike.Appended$class.length(this);
/*     */     }
/*     */     
/*     */     public B apply(int idx) {
/*  77 */       return (B)GenSeqViewLike.Appended$class.apply(this, idx);
/*     */     }
/*     */     
/*     */     public Iterator<B> iterator() {
/*  77 */       return GenIterableViewLike.Appended$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  77 */       GenTraversableViewLike.Appended$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  77 */       return GenTraversableViewLike.Appended$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public GenTraversable<B> rest() {
/*  77 */       return this.rest;
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anon$2(SeqViewLike<A, Coll, This> $outer, GenTraversable<B> that$1) {
/*  77 */       super($outer);
/*  77 */       GenTraversableViewLike.Appended$class.$init$(this);
/*  77 */       GenIterableViewLike.Appended$class.$init$(this);
/*  77 */       GenSeqViewLike.Appended$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anon$3 extends AbstractTransformed<B> implements Mapped<B> {
/*     */     private final Function1<A, B> mapping;
/*     */     
/*     */     public int length() {
/*  78 */       return GenSeqViewLike.Mapped$class.length(this);
/*     */     }
/*     */     
/*     */     public B apply(int idx) {
/*  78 */       return (B)GenSeqViewLike.Mapped$class.apply(this, idx);
/*     */     }
/*     */     
/*     */     public Iterator<B> iterator() {
/*  78 */       return GenIterableViewLike.Mapped$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  78 */       GenTraversableViewLike.Mapped$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  78 */       return GenTraversableViewLike.Mapped$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Function1<A, B> mapping() {
/*  78 */       return this.mapping;
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anon$3(SeqViewLike<A, Coll, This> $outer, Function1<A, B> f$1) {
/*  78 */       super($outer);
/*  78 */       GenTraversableViewLike.Mapped$class.$init$(this);
/*  78 */       GenIterableViewLike.Mapped$class.$init$(this);
/*  78 */       GenSeqViewLike.Mapped$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anon$4 extends AbstractTransformed<B> implements FlatMapped<B> {
/*     */     private final Function1<A, GenTraversableOnce<B>> mapping;
/*     */     
/*     */     private final int[] index;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     private int[] index$lzycompute() {
/*  79 */       synchronized (this) {
/*  79 */         if (!this.bitmap$0) {
/*  79 */           this.index = GenSeqViewLike.FlatMapped$class.index(this);
/*  79 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/SeqViewLike$$anon$4}} */
/*  79 */         return this.index;
/*     */       } 
/*     */     }
/*     */     
/*     */     public int[] index() {
/*  79 */       return this.bitmap$0 ? this.index : index$lzycompute();
/*     */     }
/*     */     
/*     */     public int findRow(int idx, int lo, int hi) {
/*  79 */       return GenSeqViewLike.FlatMapped$class.findRow(this, idx, lo, hi);
/*     */     }
/*     */     
/*     */     public int length() {
/*  79 */       return GenSeqViewLike.FlatMapped$class.length(this);
/*     */     }
/*     */     
/*     */     public B apply(int idx) {
/*  79 */       return (B)GenSeqViewLike.FlatMapped$class.apply(this, idx);
/*     */     }
/*     */     
/*     */     public Iterator<B> iterator() {
/*  79 */       return GenIterableViewLike.FlatMapped$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  79 */       GenTraversableViewLike.FlatMapped$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  79 */       return GenTraversableViewLike.FlatMapped$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Function1<A, GenTraversableOnce<B>> mapping() {
/*  79 */       return this.mapping;
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anon$4(SeqViewLike<A, Coll, This> $outer, Function1<A, GenTraversableOnce<B>> f$2) {
/*  79 */       super($outer);
/*  79 */       GenTraversableViewLike.FlatMapped$class.$init$(this);
/*  79 */       GenIterableViewLike.FlatMapped$class.$init$(this);
/*  79 */       GenSeqViewLike.FlatMapped$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anon$5 extends AbstractTransformed<A> implements Filtered {
/*     */     private final Function1<A, Object> pred;
/*     */     
/*     */     private final int[] index;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     private int[] index$lzycompute() {
/*  80 */       synchronized (this) {
/*  80 */         if (!this.bitmap$0) {
/*  80 */           this.index = GenSeqViewLike.Filtered$class.index(this);
/*  80 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/SeqViewLike$$anon$5}} */
/*  80 */         return this.index;
/*     */       } 
/*     */     }
/*     */     
/*     */     public int[] index() {
/*  80 */       return this.bitmap$0 ? this.index : index$lzycompute();
/*     */     }
/*     */     
/*     */     public int length() {
/*  80 */       return GenSeqViewLike.Filtered$class.length(this);
/*     */     }
/*     */     
/*     */     public Object apply(int idx) {
/*  80 */       return GenSeqViewLike.Filtered$class.apply(this, idx);
/*     */     }
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  80 */       return GenIterableViewLike.Filtered$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  80 */       GenTraversableViewLike.Filtered$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  80 */       return GenTraversableViewLike.Filtered$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Function1<A, Object> pred() {
/*  80 */       return this.pred;
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anon$5(SeqViewLike<A, Coll, This> $outer, Function1<A, Object> p$1) {
/*  80 */       super($outer);
/*  80 */       GenTraversableViewLike.Filtered$class.$init$(this);
/*  80 */       GenIterableViewLike.Filtered$class.$init$(this);
/*  80 */       GenSeqViewLike.Filtered$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anon$6 extends AbstractTransformed<A> implements Sliced {
/*     */     private final SliceInterval endpoints;
/*     */     
/*     */     public int length() {
/*  81 */       return GenSeqViewLike.Sliced$class.length(this);
/*     */     }
/*     */     
/*     */     public Object apply(int idx) {
/*  81 */       return GenSeqViewLike.Sliced$class.apply(this, idx);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  81 */       GenSeqViewLike.Sliced$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  81 */       return GenSeqViewLike.Sliced$class.iterator(this);
/*     */     }
/*     */     
/*     */     public int from() {
/*  81 */       return GenTraversableViewLike.Sliced$class.from(this);
/*     */     }
/*     */     
/*     */     public int until() {
/*  81 */       return GenTraversableViewLike.Sliced$class.until(this);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  81 */       return GenTraversableViewLike.Sliced$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public SliceInterval endpoints() {
/*  81 */       return this.endpoints;
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anon$6(SeqViewLike<A, Coll, This> $outer, SliceInterval _endpoints$1) {
/*  81 */       super($outer);
/*  81 */       GenTraversableViewLike.Sliced$class.$init$(this);
/*  81 */       GenIterableViewLike.Sliced$class.$init$(this);
/*  81 */       GenSeqViewLike.Sliced$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anon$7 extends AbstractTransformed<A> implements DroppedWhile {
/*     */     private final Function1<A, Object> pred;
/*     */     
/*     */     private final int start;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     private int start$lzycompute() {
/*  82 */       synchronized (this) {
/*  82 */         if (!this.bitmap$0) {
/*  82 */           this.start = GenSeqViewLike.DroppedWhile$class.start(this);
/*  82 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/SeqViewLike$$anon$7}} */
/*  82 */         return this.start;
/*     */       } 
/*     */     }
/*     */     
/*     */     public int start() {
/*  82 */       return this.bitmap$0 ? this.start : start$lzycompute();
/*     */     }
/*     */     
/*     */     public int length() {
/*  82 */       return GenSeqViewLike.DroppedWhile$class.length(this);
/*     */     }
/*     */     
/*     */     public Object apply(int idx) {
/*  82 */       return GenSeqViewLike.DroppedWhile$class.apply(this, idx);
/*     */     }
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  82 */       return GenIterableViewLike.DroppedWhile$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  82 */       GenTraversableViewLike.DroppedWhile$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  82 */       return GenTraversableViewLike.DroppedWhile$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Function1<A, Object> pred() {
/*  82 */       return this.pred;
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anon$7(SeqViewLike<A, Coll, This> $outer, Function1<A, Object> p$2) {
/*  82 */       super($outer);
/*  82 */       GenTraversableViewLike.DroppedWhile$class.$init$(this);
/*  82 */       GenIterableViewLike.DroppedWhile$class.$init$(this);
/*  82 */       GenSeqViewLike.DroppedWhile$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anon$8 extends AbstractTransformed<A> implements TakenWhile {
/*     */     private final Function1<A, Object> pred;
/*     */     
/*     */     private final int len;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     private int len$lzycompute() {
/*  83 */       synchronized (this) {
/*  83 */         if (!this.bitmap$0) {
/*  83 */           this.len = GenSeqViewLike.TakenWhile$class.len(this);
/*  83 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/SeqViewLike$$anon$8}} */
/*  83 */         return this.len;
/*     */       } 
/*     */     }
/*     */     
/*     */     public int len() {
/*  83 */       return this.bitmap$0 ? this.len : len$lzycompute();
/*     */     }
/*     */     
/*     */     public int length() {
/*  83 */       return GenSeqViewLike.TakenWhile$class.length(this);
/*     */     }
/*     */     
/*     */     public Object apply(int idx) {
/*  83 */       return GenSeqViewLike.TakenWhile$class.apply(this, idx);
/*     */     }
/*     */     
/*     */     public Iterator<Object> iterator() {
/*  83 */       return GenIterableViewLike.TakenWhile$class.iterator(this);
/*     */     }
/*     */     
/*     */     public <U> void foreach(Function1 f) {
/*  83 */       GenTraversableViewLike.TakenWhile$class.foreach(this, f);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  83 */       return GenTraversableViewLike.TakenWhile$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Function1<A, Object> pred() {
/*  83 */       return this.pred;
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anon$8(SeqViewLike<A, Coll, This> $outer, Function1<A, Object> p$3) {
/*  83 */       super($outer);
/*  83 */       GenTraversableViewLike.TakenWhile$class.$init$(this);
/*  83 */       GenIterableViewLike.TakenWhile$class.$init$(this);
/*  83 */       GenSeqViewLike.TakenWhile$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anon$9 extends AbstractTransformed<Tuple2<A, B>> implements Zipped<B> {
/*     */     private final GenIterable<B> other;
/*     */     
/*     */     private final Seq<Object> thatSeq;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     private Seq thatSeq$lzycompute() {
/*  84 */       synchronized (this) {
/*  84 */         if (!this.bitmap$0) {
/*  84 */           this.thatSeq = GenSeqViewLike.Zipped$class.thatSeq(this);
/*  84 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/SeqViewLike$$anon$9}} */
/*  84 */         return this.thatSeq;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Seq<B> thatSeq() {
/*  84 */       return this.bitmap$0 ? (Seq)this.thatSeq : thatSeq$lzycompute();
/*     */     }
/*     */     
/*     */     public int length() {
/*  84 */       return GenSeqViewLike.Zipped$class.length(this);
/*     */     }
/*     */     
/*     */     public Tuple2<A, B> apply(int idx) {
/*  84 */       return GenSeqViewLike.Zipped$class.apply(this, idx);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<A, B>> iterator() {
/*  84 */       return GenIterableViewLike.Zipped$class.iterator(this);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  84 */       return GenIterableViewLike.Zipped$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public GenIterable<B> other() {
/*  84 */       return this.other;
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anon$9(SeqViewLike<A, Coll, This> $outer, GenIterable<B> that$2) {
/*  84 */       super($outer);
/*  84 */       GenIterableViewLike.Zipped$class.$init$(this);
/*  84 */       GenSeqViewLike.Zipped$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anon$10 extends AbstractTransformed<Tuple2<A1, B>> implements ZippedAll<A1, B> {
/*     */     private final GenIterable<B> other;
/*     */     
/*     */     private final A1 thisElem;
/*     */     
/*     */     private final B thatElem;
/*     */     
/*     */     private final Seq<Object> thatSeq;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     private Seq thatSeq$lzycompute() {
/*  85 */       synchronized (this) {
/*  85 */         if (!this.bitmap$0) {
/*  85 */           this.thatSeq = GenSeqViewLike.ZippedAll$class.thatSeq(this);
/*  85 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/SeqViewLike$$anon$10}} */
/*  85 */         return this.thatSeq;
/*     */       } 
/*     */     }
/*     */     
/*     */     public Seq<B> thatSeq() {
/*  85 */       return this.bitmap$0 ? (Seq)this.thatSeq : thatSeq$lzycompute();
/*     */     }
/*     */     
/*     */     public int length() {
/*  85 */       return GenSeqViewLike.ZippedAll$class.length(this);
/*     */     }
/*     */     
/*     */     public Tuple2<A1, B> apply(int idx) {
/*  85 */       return GenSeqViewLike.ZippedAll$class.apply(this, idx);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  85 */       return GenIterableViewLike.ZippedAll$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public Iterator<Tuple2<A1, B>> iterator() {
/*  85 */       return GenIterableViewLike.ZippedAll$class.iterator(this);
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anon$10(SeqViewLike<A, Coll, This> $outer, GenIterable<B> that$3, Object _thisElem$1, Object _thatElem$1) {
/*  85 */       super($outer);
/*  85 */       GenIterableViewLike.ZippedAll$class.$init$(this);
/*  85 */       GenSeqViewLike.ZippedAll$class.$init$(this);
/*     */     }
/*     */     
/*     */     public GenIterable<B> other() {
/*  86 */       return this.other;
/*     */     }
/*     */     
/*     */     public A1 thisElem() {
/*  87 */       return this.thisElem;
/*     */     }
/*     */     
/*     */     public B thatElem() {
/*  88 */       return this.thatElem;
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anon$11 extends AbstractTransformed<A> implements Reversed {
/*     */     public Iterator<Object> iterator() {
/*  90 */       return GenSeqViewLike.Reversed$class.iterator(this);
/*     */     }
/*     */     
/*     */     public int length() {
/*  90 */       return GenSeqViewLike.Reversed$class.length(this);
/*     */     }
/*     */     
/*     */     public Object apply(int idx) {
/*  90 */       return GenSeqViewLike.Reversed$class.apply(this, idx);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  90 */       return GenSeqViewLike.Reversed$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anon$11(SeqViewLike<A, Coll, This> $outer) {
/*  90 */       super($outer);
/*  90 */       GenSeqViewLike.Reversed$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anon$12 extends AbstractTransformed<B> implements Patched<B> {
/*     */     private final int from;
/*     */     
/*     */     private final GenSeq<B> patch;
/*     */     
/*     */     private final int replaced;
/*     */     
/*     */     private final int scala$collection$GenSeqViewLike$Patched$$plen;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     private int scala$collection$GenSeqViewLike$Patched$$plen$lzycompute() {
/*  91 */       synchronized (this) {
/*  91 */         if (!this.bitmap$0) {
/*  91 */           this.scala$collection$GenSeqViewLike$Patched$$plen = GenSeqViewLike.Patched$class.scala$collection$GenSeqViewLike$Patched$$plen(this);
/*  91 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{ObjectType{scala/collection/SeqViewLike$$anon$12}} */
/*  91 */         return this.scala$collection$GenSeqViewLike$Patched$$plen;
/*     */       } 
/*     */     }
/*     */     
/*     */     public int scala$collection$GenSeqViewLike$Patched$$plen() {
/*  91 */       return this.bitmap$0 ? this.scala$collection$GenSeqViewLike$Patched$$plen : scala$collection$GenSeqViewLike$Patched$$plen$lzycompute();
/*     */     }
/*     */     
/*     */     public Iterator<B> iterator() {
/*  91 */       return GenSeqViewLike.Patched$class.iterator(this);
/*     */     }
/*     */     
/*     */     public int length() {
/*  91 */       return GenSeqViewLike.Patched$class.length(this);
/*     */     }
/*     */     
/*     */     public B apply(int idx) {
/*  91 */       return (B)GenSeqViewLike.Patched$class.apply(this, idx);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  91 */       return GenSeqViewLike.Patched$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anon$12(SeqViewLike<A, Coll, This> $outer, int _from$1, GenSeq<B> _patch$1, int _replaced$1) {
/*  91 */       super($outer);
/*  91 */       GenSeqViewLike.Patched$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int from() {
/*  92 */       return this.from;
/*     */     }
/*     */     
/*     */     public GenSeq<B> patch() {
/*  93 */       return this.patch;
/*     */     }
/*     */     
/*     */     public int replaced() {
/*  94 */       return this.replaced;
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anon$13 extends AbstractTransformed<B> implements Prepended<B> {
/*     */     private final B fst;
/*     */     
/*     */     public Iterator<B> iterator() {
/*  96 */       return GenSeqViewLike.Prepended$class.iterator(this);
/*     */     }
/*     */     
/*     */     public int length() {
/*  96 */       return GenSeqViewLike.Prepended$class.length(this);
/*     */     }
/*     */     
/*     */     public B apply(int idx) {
/*  96 */       return (B)GenSeqViewLike.Prepended$class.apply(this, idx);
/*     */     }
/*     */     
/*     */     public final String viewIdentifier() {
/*  96 */       return GenSeqViewLike.Prepended$class.viewIdentifier(this);
/*     */     }
/*     */     
/*     */     public B fst() {
/*  96 */       return this.fst;
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anon$13(SeqViewLike<A, Coll, This> $outer, Object elem$1) {
/*  96 */       super($outer);
/*  96 */       GenSeqViewLike.Prepended$class.$init$(this);
/*     */     }
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anonfun$padTo$1 extends AbstractFunction0<B> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object elem$2;
/*     */     
/*     */     public final B apply() {
/* 112 */       return (B)this.elem$2;
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anonfun$padTo$1(SeqViewLike $outer, Object elem$2) {}
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anonfun$union$1 extends AbstractFunction0<Seq<B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final GenSeq that$4;
/*     */     
/*     */     public final Seq<B> apply() {
/* 129 */       return (Seq<B>)this.$outer.thisSeq().union(this.that$4, Seq$.MODULE$.canBuildFrom());
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anonfun$union$1(SeqViewLike $outer, GenSeq that$4) {}
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anonfun$diff$1 extends AbstractFunction0<Seq<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final GenSeq that$5;
/*     */     
/*     */     public final Seq<A> apply() {
/* 132 */       return (Seq<A>)this.$outer.thisSeq().diff(this.that$5);
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anonfun$diff$1(SeqViewLike $outer, GenSeq that$5) {}
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anonfun$intersect$1 extends AbstractFunction0<Seq<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final GenSeq that$6;
/*     */     
/*     */     public final Seq<A> apply() {
/* 135 */       return (Seq<A>)this.$outer.thisSeq().intersect(this.that$6);
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anonfun$intersect$1(SeqViewLike $outer, GenSeq that$6) {}
/*     */   }
/*     */   
/*     */   public class SeqViewLike$$anonfun$sorted$1 extends AbstractFunction0<Seq<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Ordering ord$1;
/*     */     
/*     */     public final Seq<A> apply() {
/* 138 */       return this.$outer.thisSeq().sorted(this.ord$1);
/*     */     }
/*     */     
/*     */     public SeqViewLike$$anonfun$sorted$1(SeqViewLike $outer, Ordering ord$1) {}
/*     */   }
/*     */   
/*     */   public interface Forced<B> extends IterableViewLike<A, Coll, This>.Forced<B>, GenSeqViewLike<A, Coll, This>.Forced<B>, Transformed<B> {}
/*     */   
/*     */   public interface Sliced extends IterableViewLike<A, Coll, This>.Sliced, GenSeqViewLike<A, Coll, This>.Sliced, Transformed<A> {}
/*     */   
/*     */   public interface Mapped<B> extends IterableViewLike<A, Coll, This>.Mapped<B>, GenSeqViewLike<A, Coll, This>.Mapped<B>, Transformed<B> {}
/*     */   
/*     */   public interface Zipped<B> extends IterableViewLike<A, Coll, This>.Zipped<B>, GenSeqViewLike<A, Coll, This>.Zipped<B>, Transformed<Tuple2<A, B>> {}
/*     */   
/*     */   public interface Patched<B> extends Transformed<B>, GenSeqViewLike<A, Coll, This>.Patched<B> {}
/*     */   
/*     */   public interface Appended<B> extends IterableViewLike<A, Coll, This>.Appended<B>, GenSeqViewLike<A, Coll, This>.Appended<B>, Transformed<B> {}
/*     */   
/*     */   public interface Filtered extends IterableViewLike<A, Coll, This>.Filtered, GenSeqViewLike<A, Coll, This>.Filtered, Transformed<A> {}
/*     */   
/*     */   public interface Reversed extends Transformed<A>, GenSeqViewLike<A, Coll, This>.Reversed {}
/*     */   
/*     */   public interface EmptyView extends Transformed<Nothing$>, IterableViewLike<A, Coll, This>.EmptyView, GenSeqViewLike<A, Coll, This>.EmptyView {}
/*     */   
/*     */   public interface ZippedAll<A1, B> extends IterableViewLike<A, Coll, This>.ZippedAll<A1, B>, GenSeqViewLike<A, Coll, This>.ZippedAll<A1, B>, Transformed<Tuple2<A1, B>> {}
/*     */   
/*     */   public interface Prepended<B> extends Transformed<B>, GenSeqViewLike<A, Coll, This>.Prepended<B> {}
/*     */   
/*     */   public interface FlatMapped<B> extends IterableViewLike<A, Coll, This>.FlatMapped<B>, GenSeqViewLike<A, Coll, This>.FlatMapped<B>, Transformed<B> {}
/*     */   
/*     */   public interface TakenWhile extends IterableViewLike<A, Coll, This>.TakenWhile, GenSeqViewLike<A, Coll, This>.TakenWhile, Transformed<A> {}
/*     */   
/*     */   public interface Transformed<B> extends SeqView<B, Coll>, IterableViewLike<A, Coll, This>.Transformed<B>, GenSeqViewLike<A, Coll, This>.Transformed<B> {
/*     */     int length();
/*     */     
/*     */     B apply(int param1Int);
/*     */     
/*     */     String toString();
/*     */   }
/*     */   
/*     */   public interface DroppedWhile extends IterableViewLike<A, Coll, This>.DroppedWhile, GenSeqViewLike<A, Coll, This>.DroppedWhile, Transformed<A> {}
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\SeqViewLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */