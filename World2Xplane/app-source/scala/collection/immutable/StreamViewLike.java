/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.PartialFunction;
/*    */ import scala.Predef$;
/*    */ import scala.Tuple2;
/*    */ import scala.Tuple3;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenIterableViewLike;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenSeqLike;
/*    */ import scala.collection.GenSeqViewLike;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.GenTraversableViewLike;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.IterableLike;
/*    */ import scala.collection.IterableView;
/*    */ import scala.collection.IterableViewLike;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Parallelizable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.SeqLike;
/*    */ import scala.collection.SeqView;
/*    */ import scala.collection.SeqViewLike;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.TraversableViewLike;
/*    */ import scala.collection.ViewMkString;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.generic.FilterMonadic;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.GenericTraversableTemplate;
/*    */ import scala.collection.generic.SliceInterval;
/*    */ import scala.collection.mutable.Buffer;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.ParSeq;
/*    */ import scala.math.Numeric;
/*    */ import scala.math.Ordering;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ import scala.runtime.Nothing$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\t\035gaB\001\003!\003\r\t!\003\002\017'R\024X-Y7WS\026<H*[6f\025\t\031A!A\005j[6,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U!!\"F\020&'\021\0011bD\021\021\0051iQ\"\001\004\n\00591!AB!osJ+g\r\005\003\021#MqR\"\001\003\n\005I!!aB*fcZKWm\036\t\003)Ua\001\001\002\004\027\001\021\025\ra\006\002\002\003F\021\001d\007\t\003\031eI!A\007\004\003\0179{G\017[5oOB\021A\002H\005\003;\031\0211!\0218z!\t!r\004\002\004!\001\021\025\ra\006\002\005\007>dG\016E\003\021EMqB%\003\002$\t\tY1+Z9WS\026<H*[6f!\t!R\005\002\004'\001\021\025\ra\n\002\005)\"L7/\005\002\031QI\031\021fK\030\007\t)\002\001\001\013\002\ryI,g-\0338f[\026tGO\020\t\005Y5\032b$D\001\003\023\tq#A\001\006TiJ,\027-\034,jK^\004R\001\f\001\024=\021BQ!\r\001\005\002I\na\001J5oSR$C#A\032\021\0051!\024BA\033\007\005\021)f.\033;\t\013]\002A\021\t\035\002\013\031|'oY3\026\007e25\b\006\002;{A\021Ac\017\003\006yY\022\ra\006\002\005)\"\fG\017C\003?m\001\017q(\001\002cMB)\001i\021\020Fu5\t\021I\003\002C\t\0059q-\0328fe&\034\027B\001#B\0051\031\025M\034\"vS2$gI]8n!\t!b\tB\003Hm\t\007\001JA\001C#\t\0312DB\004K\001A\005\031\021A&\003\027Q\023\030M\\:g_JlW\rZ\013\003\031>\033B!S\006N!B!A&\f(\037!\t!r\n\002\004H\023\022\025\ra\006\t\004#JsU\"\001\001\n\005)\023\003\"B\031J\t\003\021\004\"B+J\t\0032\026\001\003;p'R\024\030N\\4\025\003]\003\"\001W/\016\003eS!AW.\002\t1\fgn\032\006\0029\006!!.\031<b\023\tq\026L\001\004TiJLgn\032\004\007A\002\t\t\001B1\003'\005\0237\017\036:bGR$&/\0318tM>\024X.\0323\026\005\t47cA0dOB\031\021\013Z3\n\005\001\024\003C\001\013g\t\0319u\f\"b\001/A\031\021+S3\t\013%|F\021\0016\002\rqJg.\033;?)\005Y\007cA)`K\0329Q\016\001I\001$\003q'!C#naRLh+[3x'\021a7b\0349\021\007EK\005\004\005\002Rc&\021QN\t\004\bg\002\001\n1%\001u\005\0311uN]2fIV\021Q/_\n\005e.1(\020E\002RobL!a\035\022\021\005QIH!B$s\005\0049\002cA)Jq\0329A\020\001I\001$\003i(AB*mS\016,GmE\003|\027y\f\t\001\005\002R&\021AP\t\t\004#&\033b!CA\003\001A\005\031\023AA\004\005\031i\025\r\0359fIV!\021\021BA\t'\035\t\031aCA\006\003'\001R!UA\007\003\037I1!!\002#!\r!\022\021\003\003\007\017\006\r!\031A\f\021\tEK\025q\002\004\n\003/\001\001\023aI\001\0033\021!B\0227bi6\013\007\017]3e+\021\tY\"a\t\024\017\005U1\"!\b\002&A)\021+a\b\002\"%\031\021q\003\022\021\007Q\t\031\003\002\004H\003+\021\ra\006\t\005#&\013\tCB\005\002*\001\001\n1%\001\002,\tA\021\t\0359f]\022,G-\006\003\002.\005U2cBA\024\027\005=\022q\007\t\006#\006E\0221G\005\004\003S\021\003c\001\013\0026\0211q)a\nC\002!\003B!U%\0024\031I\0211\b\001\021\002G\005\021Q\b\002\t\r&dG/\032:fIN9\021\021H\006\002@\005\005\001cA)\002B%\031\0211\b\022\007\023\005\025\003\001%A\022\002\005\035#A\003+bW\026tw\013[5mKN9\0211I\006\002J\005\005\001cA)\002L%\031\021Q\t\022\007\023\005=\003\001%A\022\002\005E#\001\004#s_B\004X\rZ,iS2,7cBA'\027\005M\023\021\001\t\004#\006U\023bAA(E\031I\021\021\f\001\021\002G\005\0211\f\002\0075&\004\b/\0323\026\t\005u\023QM\n\b\003/Z\021qLA4!\025\t\026\021MA2\023\r\tIF\t\t\004)\005\025DAB$\002X\t\007q\003\005\003R\023\006%\004C\002\007\002lM\t\031'C\002\002n\031\021a\001V;qY\026\024d!CA9\001A\005\031\023AA:\005%Q\026\016\0359fI\006cG.\006\004\002v\005u\0241Q\n\b\003_Z\021qOAC!\035\t\026\021PA>\003\003K1!!\035#!\r!\022Q\020\003\b\003\nyG1\001I\005\t\t\025\007E\002\025\003\007#aaRA8\005\0049\002\003B)J\003\017\003r\001DA6\003w\n\tIB\005\002\f\002\001\n1%\001\002\016\nA!+\032<feN,GmE\004\002\n.\ty)!\001\021\007E\013\t*C\002\002\f\n2\021\"!&\001!\003\r\n!a&\003\017A\013Go\0315fIV!\021\021TAQ'\035\t\031jCAN\003G\003R!UAO\003?K1!!&#!\r!\022\021\025\003\007\017\006M%\031\001%\021\tEK\025q\024\004\n\003O\003\001\023aI\001\003S\023\021\002\025:fa\026tG-\0323\026\t\005-\0261W\n\b\003K[\021QVA[!\025\t\026qVAY\023\r\t9K\t\t\004)\005MFAB$\002&\n\007\001\n\005\003R\023\006E\006bBA]\001\021E\0231X\001\n]\026<hi\034:dK\022,B!!0\002DR!\021qXAc!\021\t\026*!1\021\007Q\t\031\r\002\004H\003o\023\ra\006\005\n\003\017\f9\f\"a\001\003\023\f!\001_:\021\0131\tY-a4\n\007\0055gA\001\005=Eft\027-\\3?!\025\001\022\021[Aa\023\r\t\031\016\002\002\007\017\026t7+Z9\t\017\005]\007\001\"\025\002Z\006Ya.Z<BaB,g\016Z3e+\021\tY.!9\025\t\005u\0271\035\t\005#&\013y\016E\002\025\003C$aaRAk\005\004A\005\002CAs\003+\004\r!a:\002\tQD\027\r\036\t\006!\005%\030q\\\005\004\003W$!AD$f]R\023\030M^3sg\006\024G.\032\005\b\003_\004A\021KAy\003%qWm^'baB,G-\006\003\002t\006eH\003BA{\003w\004B!U%\002xB\031A#!?\005\r\035\013iO1\001\030\021!\ti0!<A\002\005}\030!\0014\021\r1\021\taEA|\023\r\021\031A\002\002\n\rVt7\r^5p]FBqAa\002\001\t#\022I!A\007oK^4E.\031;NCB\004X\rZ\013\005\005\027\021\t\002\006\003\003\016\tM\001\003B)J\005\037\0012\001\006B\t\t\0319%Q\001b\001/!A\021Q B\003\001\004\021)\002\005\004\r\005\003\031\"q\003\t\006!\te!qB\005\004\0057!!AE$f]R\023\030M^3sg\006\024G.Z(oG\026DqAa\b\001\t#\022\t#A\006oK^4\025\016\034;fe\026$G\003BA\001\005GA\001B!\n\003\036\001\007!qE\001\002aB1AB!\001\024\005S\0012\001\004B\026\023\r\021iC\002\002\b\005>|G.Z1o\021\035\021\t\004\001C)\005g\t\021B\\3x'2L7-\0323\025\t\005\005!Q\007\005\t\005o\021y\0031\001\003:\005Qq,\0328ea>Lg\016^:\021\007\001\023Y$C\002\003>\005\023Qb\0257jG\026Le\016^3sm\006d\007b\002B!\001\021E#1I\001\020]\026<HI]8qa\026$w\013[5mKR!\021\021\001B#\021!\021)Ca\020A\002\t\035\002b\002B%\001\021E#1J\001\016]\026<H+Y6f]^C\027\016\\3\025\t\005\005!Q\n\005\t\005K\0219\0051\001\003(!9!\021\013\001\005R\tM\023!\0038fojK\007\017]3e+\021\021)F!\030\025\t\t]#q\f\t\005#&\023I\006\005\004\r\003W\032\"1\f\t\004)\tuCAB$\003P\t\007q\003\003\005\002f\n=\003\031\001B1!\025\001\"1\rB.\023\r\021)\007\002\002\f\017\026t\027\n^3sC\ndW\rC\004\003j\001!\tFa\033\002\0319,wOW5qa\026$\027\t\0347\026\r\t5$Q\017B=)!\021yGa\037\003\000\t\r\005\003B)J\005c\002r\001DA6\005g\0229\bE\002\025\005k\"q!a \003h\t\007\001\nE\002\025\005s\"aa\022B4\005\0049\002\002CAs\005O\002\rA! \021\013A\021\031Ga\036\t\021\t\005%q\ra\001\005g\n\021b\030;iSN,E.Z7\t\021\t\025%q\ra\001\005o\n\021b\030;iCR,E.Z7\t\017\t%\005\001\"\025\003\f\006Ya.Z<SKZ,'o]3e+\t\t\t\001C\004\003\020\002!\tF!%\002\0259,w\017U1uG\",G-\006\003\003\024\neE\003\003BK\0057\023)Ka+\021\tEK%q\023\t\004)\teEAB$\003\016\n\007\001\n\003\005\003\036\n5\005\031\001BP\003\025yfM]8n!\ra!\021U\005\004\005G3!aA%oi\"A!q\025BG\001\004\021I+\001\004`a\006$8\r\033\t\006!\005E'q\023\005\t\005[\023i\t1\001\003 \006IqL]3qY\006\034W\r\032\005\b\005c\003A\021\013BZ\0031qWm\036)sKB,g\016Z3e+\021\021)La/\025\t\t]&Q\030\t\005#&\023I\fE\002\025\005w#aa\022BX\005\004A\005\002\003B`\005_\003\rA!/\002\t\025dW-\034\005\b\005\007\004A\021\tBc\0031\031HO]5oOB\023XMZ5y+\0059\006")
/*    */ public interface StreamViewLike<A, Coll, This extends StreamView<A, Coll> & StreamViewLike<A, Coll, This>> extends SeqView<A, Coll>, SeqViewLike<A, Coll, This> {
/*    */   <B, That> That force(CanBuildFrom<Coll, B, That> paramCanBuildFrom);
/*    */   
/*    */   <B> Transformed<B> newForced(Function0<GenSeq<B>> paramFunction0);
/*    */   
/*    */   <B> Transformed<B> newAppended(GenTraversable<B> paramGenTraversable);
/*    */   
/*    */   <B> Transformed<B> newMapped(Function1<A, B> paramFunction1);
/*    */   
/*    */   <B> Transformed<B> newFlatMapped(Function1<A, GenTraversableOnce<B>> paramFunction1);
/*    */   
/*    */   Transformed<A> newFiltered(Function1<A, Object> paramFunction1);
/*    */   
/*    */   Transformed<A> newSliced(SliceInterval paramSliceInterval);
/*    */   
/*    */   Transformed<A> newDroppedWhile(Function1<A, Object> paramFunction1);
/*    */   
/*    */   Transformed<A> newTakenWhile(Function1<A, Object> paramFunction1);
/*    */   
/*    */   <B> Transformed<Tuple2<A, B>> newZipped(GenIterable<B> paramGenIterable);
/*    */   
/*    */   <A1, B> Transformed<Tuple2<A1, B>> newZippedAll(GenIterable<B> paramGenIterable, A1 paramA1, B paramB);
/*    */   
/*    */   Transformed<A> newReversed();
/*    */   
/*    */   <B> Transformed<B> newPatched(int paramInt1, GenSeq<B> paramGenSeq, int paramInt2);
/*    */   
/*    */   <B> Transformed<B> newPrepended(B paramB);
/*    */   
/*    */   String stringPrefix();
/*    */   
/*    */   public abstract class Transformed$class {
/*    */     public static void $init$(StreamViewLike.Transformed $this) {}
/*    */     
/*    */     public static String toString(StreamViewLike.Transformed $this) {
/* 18 */       return $this.viewToString();
/*    */     }
/*    */   }
/*    */   
/*    */   public abstract class AbstractTransformed<B> extends SeqViewLike<A, Coll, This>.AbstractTransformed<B> implements Transformed<B> {
/*    */     public String toString() {
/* 22 */       return StreamViewLike.Transformed$class.toString(this);
/*    */     }
/*    */     
/*    */     public <B, That> That force(CanBuildFrom bf) {
/* 22 */       return (That)StreamViewLike$class.force(this, bf);
/*    */     }
/*    */     
/*    */     public <B> StreamViewLike<B, Coll, StreamView<B, Coll>>.Transformed<B> newForced(Function0 xs) {
/* 22 */       return StreamViewLike$class.newForced(this, xs);
/*    */     }
/*    */     
/*    */     public <B> StreamViewLike<B, Coll, StreamView<B, Coll>>.Transformed<B> newAppended(GenTraversable that) {
/* 22 */       return StreamViewLike$class.newAppended(this, that);
/*    */     }
/*    */     
/*    */     public <B> StreamViewLike<B, Coll, StreamView<B, Coll>>.Transformed<B> newMapped(Function1 f) {
/* 22 */       return StreamViewLike$class.newMapped(this, f);
/*    */     }
/*    */     
/*    */     public <B> StreamViewLike<B, Coll, StreamView<B, Coll>>.Transformed<B> newFlatMapped(Function1 f) {
/* 22 */       return StreamViewLike$class.newFlatMapped(this, f);
/*    */     }
/*    */     
/*    */     public StreamViewLike<B, Coll, StreamView<B, Coll>>.Transformed<B> newFiltered(Function1 p) {
/* 22 */       return StreamViewLike$class.newFiltered(this, p);
/*    */     }
/*    */     
/*    */     public StreamViewLike<B, Coll, StreamView<B, Coll>>.Transformed<B> newSliced(SliceInterval _endpoints) {
/* 22 */       return StreamViewLike$class.newSliced(this, _endpoints);
/*    */     }
/*    */     
/*    */     public StreamViewLike<B, Coll, StreamView<B, Coll>>.Transformed<B> newDroppedWhile(Function1 p) {
/* 22 */       return StreamViewLike$class.newDroppedWhile(this, p);
/*    */     }
/*    */     
/*    */     public StreamViewLike<B, Coll, StreamView<B, Coll>>.Transformed<B> newTakenWhile(Function1 p) {
/* 22 */       return StreamViewLike$class.newTakenWhile(this, p);
/*    */     }
/*    */     
/*    */     public <B> StreamViewLike<B, Coll, StreamView<B, Coll>>.Transformed<Tuple2<B, B>> newZipped(GenIterable that) {
/* 22 */       return StreamViewLike$class.newZipped(this, that);
/*    */     }
/*    */     
/*    */     public <A1, B> StreamViewLike<B, Coll, StreamView<B, Coll>>.Transformed<Tuple2<A1, B>> newZippedAll(GenIterable that, Object _thisElem, Object _thatElem) {
/* 22 */       return StreamViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
/*    */     }
/*    */     
/*    */     public StreamViewLike<B, Coll, StreamView<B, Coll>>.Transformed<B> newReversed() {
/* 22 */       return StreamViewLike$class.newReversed(this);
/*    */     }
/*    */     
/*    */     public <B> StreamViewLike<B, Coll, StreamView<B, Coll>>.Transformed<B> newPatched(int _from, GenSeq _patch, int _replaced) {
/* 22 */       return StreamViewLike$class.newPatched(this, _from, _patch, _replaced);
/*    */     }
/*    */     
/*    */     public <B> StreamViewLike<B, Coll, StreamView<B, Coll>>.Transformed<B> newPrepended(Object elem) {
/* 22 */       return StreamViewLike$class.newPrepended(this, elem);
/*    */     }
/*    */     
/*    */     public String stringPrefix() {
/* 22 */       return StreamViewLike$class.stringPrefix(this);
/*    */     }
/*    */     
/*    */     public AbstractTransformed(StreamViewLike $outer) {
/* 22 */       super($outer);
/* 22 */       StreamViewLike$class.$init$(this);
/* 22 */       StreamViewLike.Transformed$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public class StreamViewLike$$anon$1 extends AbstractTransformed<B> implements Forced<B> {
/*    */     private final GenSeq<B> forced;
/*    */     
/*    */     public int length() {
/* 53 */       return GenSeqViewLike.Forced.class.length((GenSeqViewLike.Forced)this);
/*    */     }
/*    */     
/*    */     public B apply(int idx) {
/* 53 */       return (B)GenSeqViewLike.Forced.class.apply((GenSeqViewLike.Forced)this, idx);
/*    */     }
/*    */     
/*    */     public Iterator<B> iterator() {
/* 53 */       return GenIterableViewLike.Forced.class.iterator((GenIterableViewLike.Forced)this);
/*    */     }
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/* 53 */       GenTraversableViewLike.Forced.class.foreach((GenTraversableViewLike.Forced)this, f);
/*    */     }
/*    */     
/*    */     public final String viewIdentifier() {
/* 53 */       return GenTraversableViewLike.Forced.class.viewIdentifier((GenTraversableViewLike.Forced)this);
/*    */     }
/*    */     
/*    */     public GenSeq<B> forced() {
/* 53 */       return this.forced;
/*    */     }
/*    */     
/*    */     public StreamViewLike$$anon$1(StreamViewLike<A, Coll, This> $outer, Function0 xs$1) {
/* 53 */       super($outer);
/* 53 */       GenTraversableViewLike.Forced.class.$init$((GenTraversableViewLike.Forced)this);
/* 53 */       GenIterableViewLike.Forced.class.$init$((GenIterableViewLike.Forced)this);
/* 53 */       GenSeqViewLike.Forced.class.$init$((GenSeqViewLike.Forced)this);
/*    */     }
/*    */   }
/*    */   
/*    */   public class StreamViewLike$$anon$2 extends AbstractTransformed<B> implements Appended<B> {
/*    */     private final GenTraversable<B> rest;
/*    */     
/*    */     private final GenSeq<Object> restSeq;
/*    */     
/*    */     private volatile boolean bitmap$0;
/*    */     
/*    */     private GenSeq restSeq$lzycompute() {
/* 54 */       synchronized (this) {
/* 54 */         if (!this.bitmap$0) {
/* 54 */           this.restSeq = GenSeqViewLike.Appended.class.restSeq((GenSeqViewLike.Appended)this);
/* 54 */           this.bitmap$0 = true;
/*    */         } 
/*    */         /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/StreamViewLike$$anon$2}} */
/* 54 */         return this.restSeq;
/*    */       } 
/*    */     }
/*    */     
/*    */     public GenSeq<B> restSeq() {
/* 54 */       return this.bitmap$0 ? (GenSeq)this.restSeq : restSeq$lzycompute();
/*    */     }
/*    */     
/*    */     public int length() {
/* 54 */       return GenSeqViewLike.Appended.class.length((GenSeqViewLike.Appended)this);
/*    */     }
/*    */     
/*    */     public B apply(int idx) {
/* 54 */       return (B)GenSeqViewLike.Appended.class.apply((GenSeqViewLike.Appended)this, idx);
/*    */     }
/*    */     
/*    */     public Iterator<B> iterator() {
/* 54 */       return GenIterableViewLike.Appended.class.iterator((GenIterableViewLike.Appended)this);
/*    */     }
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/* 54 */       GenTraversableViewLike.Appended.class.foreach((GenTraversableViewLike.Appended)this, f);
/*    */     }
/*    */     
/*    */     public final String viewIdentifier() {
/* 54 */       return GenTraversableViewLike.Appended.class.viewIdentifier((GenTraversableViewLike.Appended)this);
/*    */     }
/*    */     
/*    */     public GenTraversable<B> rest() {
/* 54 */       return this.rest;
/*    */     }
/*    */     
/*    */     public StreamViewLike$$anon$2(StreamViewLike<A, Coll, This> $outer, GenTraversable<B> that$1) {
/* 54 */       super($outer);
/* 54 */       GenTraversableViewLike.Appended.class.$init$((GenTraversableViewLike.Appended)this);
/* 54 */       GenIterableViewLike.Appended.class.$init$((GenIterableViewLike.Appended)this);
/* 54 */       GenSeqViewLike.Appended.class.$init$((GenSeqViewLike.Appended)this);
/*    */     }
/*    */   }
/*    */   
/*    */   public class StreamViewLike$$anon$3 extends AbstractTransformed<B> implements Mapped<B> {
/*    */     private final Function1<A, B> mapping;
/*    */     
/*    */     public int length() {
/* 55 */       return GenSeqViewLike.Mapped.class.length((GenSeqViewLike.Mapped)this);
/*    */     }
/*    */     
/*    */     public B apply(int idx) {
/* 55 */       return (B)GenSeqViewLike.Mapped.class.apply((GenSeqViewLike.Mapped)this, idx);
/*    */     }
/*    */     
/*    */     public Iterator<B> iterator() {
/* 55 */       return GenIterableViewLike.Mapped.class.iterator((GenIterableViewLike.Mapped)this);
/*    */     }
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/* 55 */       GenTraversableViewLike.Mapped.class.foreach((GenTraversableViewLike.Mapped)this, f);
/*    */     }
/*    */     
/*    */     public final String viewIdentifier() {
/* 55 */       return GenTraversableViewLike.Mapped.class.viewIdentifier((GenTraversableViewLike.Mapped)this);
/*    */     }
/*    */     
/*    */     public Function1<A, B> mapping() {
/* 55 */       return this.mapping;
/*    */     }
/*    */     
/*    */     public StreamViewLike$$anon$3(StreamViewLike<A, Coll, This> $outer, Function1<A, B> f$1) {
/* 55 */       super($outer);
/* 55 */       GenTraversableViewLike.Mapped.class.$init$((GenTraversableViewLike.Mapped)this);
/* 55 */       GenIterableViewLike.Mapped.class.$init$((GenIterableViewLike.Mapped)this);
/* 55 */       GenSeqViewLike.Mapped.class.$init$((GenSeqViewLike.Mapped)this);
/*    */     }
/*    */   }
/*    */   
/*    */   public class StreamViewLike$$anon$4 extends AbstractTransformed<B> implements FlatMapped<B> {
/*    */     private final Function1<A, GenTraversableOnce<B>> mapping;
/*    */     
/*    */     private final int[] index;
/*    */     
/*    */     private volatile boolean bitmap$0;
/*    */     
/*    */     private int[] index$lzycompute() {
/* 56 */       synchronized (this) {
/* 56 */         if (!this.bitmap$0) {
/* 56 */           this.index = GenSeqViewLike.FlatMapped.class.index((GenSeqViewLike.FlatMapped)this);
/* 56 */           this.bitmap$0 = true;
/*    */         } 
/*    */         /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/StreamViewLike$$anon$4}} */
/* 56 */         return this.index;
/*    */       } 
/*    */     }
/*    */     
/*    */     public int[] index() {
/* 56 */       return this.bitmap$0 ? this.index : index$lzycompute();
/*    */     }
/*    */     
/*    */     public int findRow(int idx, int lo, int hi) {
/* 56 */       return GenSeqViewLike.FlatMapped.class.findRow((GenSeqViewLike.FlatMapped)this, idx, lo, hi);
/*    */     }
/*    */     
/*    */     public int length() {
/* 56 */       return GenSeqViewLike.FlatMapped.class.length((GenSeqViewLike.FlatMapped)this);
/*    */     }
/*    */     
/*    */     public B apply(int idx) {
/* 56 */       return (B)GenSeqViewLike.FlatMapped.class.apply((GenSeqViewLike.FlatMapped)this, idx);
/*    */     }
/*    */     
/*    */     public Iterator<B> iterator() {
/* 56 */       return GenIterableViewLike.FlatMapped.class.iterator((GenIterableViewLike.FlatMapped)this);
/*    */     }
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/* 56 */       GenTraversableViewLike.FlatMapped.class.foreach((GenTraversableViewLike.FlatMapped)this, f);
/*    */     }
/*    */     
/*    */     public final String viewIdentifier() {
/* 56 */       return GenTraversableViewLike.FlatMapped.class.viewIdentifier((GenTraversableViewLike.FlatMapped)this);
/*    */     }
/*    */     
/*    */     public Function1<A, GenTraversableOnce<B>> mapping() {
/* 56 */       return this.mapping;
/*    */     }
/*    */     
/*    */     public StreamViewLike$$anon$4(StreamViewLike<A, Coll, This> $outer, Function1<A, GenTraversableOnce<B>> f$2) {
/* 56 */       super($outer);
/* 56 */       GenTraversableViewLike.FlatMapped.class.$init$((GenTraversableViewLike.FlatMapped)this);
/* 56 */       GenIterableViewLike.FlatMapped.class.$init$((GenIterableViewLike.FlatMapped)this);
/* 56 */       GenSeqViewLike.FlatMapped.class.$init$((GenSeqViewLike.FlatMapped)this);
/*    */     }
/*    */   }
/*    */   
/*    */   public class StreamViewLike$$anon$5 extends AbstractTransformed<A> implements Filtered {
/*    */     private final Function1<A, Object> pred;
/*    */     
/*    */     private final int[] index;
/*    */     
/*    */     private volatile boolean bitmap$0;
/*    */     
/*    */     private int[] index$lzycompute() {
/* 57 */       synchronized (this) {
/* 57 */         if (!this.bitmap$0) {
/* 57 */           this.index = GenSeqViewLike.Filtered.class.index((GenSeqViewLike.Filtered)this);
/* 57 */           this.bitmap$0 = true;
/*    */         } 
/*    */         /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/StreamViewLike$$anon$5}} */
/* 57 */         return this.index;
/*    */       } 
/*    */     }
/*    */     
/*    */     public int[] index() {
/* 57 */       return this.bitmap$0 ? this.index : index$lzycompute();
/*    */     }
/*    */     
/*    */     public int length() {
/* 57 */       return GenSeqViewLike.Filtered.class.length((GenSeqViewLike.Filtered)this);
/*    */     }
/*    */     
/*    */     public Object apply(int idx) {
/* 57 */       return GenSeqViewLike.Filtered.class.apply((GenSeqViewLike.Filtered)this, idx);
/*    */     }
/*    */     
/*    */     public Iterator<Object> iterator() {
/* 57 */       return GenIterableViewLike.Filtered.class.iterator((GenIterableViewLike.Filtered)this);
/*    */     }
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/* 57 */       GenTraversableViewLike.Filtered.class.foreach((GenTraversableViewLike.Filtered)this, f);
/*    */     }
/*    */     
/*    */     public final String viewIdentifier() {
/* 57 */       return GenTraversableViewLike.Filtered.class.viewIdentifier((GenTraversableViewLike.Filtered)this);
/*    */     }
/*    */     
/*    */     public Function1<A, Object> pred() {
/* 57 */       return this.pred;
/*    */     }
/*    */     
/*    */     public StreamViewLike$$anon$5(StreamViewLike<A, Coll, This> $outer, Function1<A, Object> p$1) {
/* 57 */       super($outer);
/* 57 */       GenTraversableViewLike.Filtered.class.$init$((GenTraversableViewLike.Filtered)this);
/* 57 */       GenIterableViewLike.Filtered.class.$init$((GenIterableViewLike.Filtered)this);
/* 57 */       GenSeqViewLike.Filtered.class.$init$((GenSeqViewLike.Filtered)this);
/*    */     }
/*    */   }
/*    */   
/*    */   public class StreamViewLike$$anon$6 extends AbstractTransformed<A> implements Sliced {
/*    */     private final SliceInterval endpoints;
/*    */     
/*    */     public int length() {
/* 58 */       return GenSeqViewLike.Sliced.class.length((GenSeqViewLike.Sliced)this);
/*    */     }
/*    */     
/*    */     public Object apply(int idx) {
/* 58 */       return GenSeqViewLike.Sliced.class.apply((GenSeqViewLike.Sliced)this, idx);
/*    */     }
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/* 58 */       GenSeqViewLike.Sliced.class.foreach((GenSeqViewLike.Sliced)this, f);
/*    */     }
/*    */     
/*    */     public Iterator<Object> iterator() {
/* 58 */       return GenSeqViewLike.Sliced.class.iterator((GenSeqViewLike.Sliced)this);
/*    */     }
/*    */     
/*    */     public int from() {
/* 58 */       return GenTraversableViewLike.Sliced.class.from((GenTraversableViewLike.Sliced)this);
/*    */     }
/*    */     
/*    */     public int until() {
/* 58 */       return GenTraversableViewLike.Sliced.class.until((GenTraversableViewLike.Sliced)this);
/*    */     }
/*    */     
/*    */     public final String viewIdentifier() {
/* 58 */       return GenTraversableViewLike.Sliced.class.viewIdentifier((GenTraversableViewLike.Sliced)this);
/*    */     }
/*    */     
/*    */     public SliceInterval endpoints() {
/* 58 */       return this.endpoints;
/*    */     }
/*    */     
/*    */     public StreamViewLike$$anon$6(StreamViewLike<A, Coll, This> $outer, SliceInterval _endpoints$1) {
/* 58 */       super($outer);
/* 58 */       GenTraversableViewLike.Sliced.class.$init$((GenTraversableViewLike.Sliced)this);
/* 58 */       GenIterableViewLike.Sliced.class.$init$((GenIterableViewLike.Sliced)this);
/* 58 */       GenSeqViewLike.Sliced.class.$init$((GenSeqViewLike.Sliced)this);
/*    */     }
/*    */   }
/*    */   
/*    */   public class StreamViewLike$$anon$7 extends AbstractTransformed<A> implements DroppedWhile {
/*    */     private final Function1<A, Object> pred;
/*    */     
/*    */     private final int start;
/*    */     
/*    */     private volatile boolean bitmap$0;
/*    */     
/*    */     private int start$lzycompute() {
/* 59 */       synchronized (this) {
/* 59 */         if (!this.bitmap$0) {
/* 59 */           this.start = GenSeqViewLike.DroppedWhile.class.start((GenSeqViewLike.DroppedWhile)this);
/* 59 */           this.bitmap$0 = true;
/*    */         } 
/*    */         /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/StreamViewLike$$anon$7}} */
/* 59 */         return this.start;
/*    */       } 
/*    */     }
/*    */     
/*    */     public int start() {
/* 59 */       return this.bitmap$0 ? this.start : start$lzycompute();
/*    */     }
/*    */     
/*    */     public int length() {
/* 59 */       return GenSeqViewLike.DroppedWhile.class.length((GenSeqViewLike.DroppedWhile)this);
/*    */     }
/*    */     
/*    */     public Object apply(int idx) {
/* 59 */       return GenSeqViewLike.DroppedWhile.class.apply((GenSeqViewLike.DroppedWhile)this, idx);
/*    */     }
/*    */     
/*    */     public Iterator<Object> iterator() {
/* 59 */       return GenIterableViewLike.DroppedWhile.class.iterator((GenIterableViewLike.DroppedWhile)this);
/*    */     }
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/* 59 */       GenTraversableViewLike.DroppedWhile.class.foreach((GenTraversableViewLike.DroppedWhile)this, f);
/*    */     }
/*    */     
/*    */     public final String viewIdentifier() {
/* 59 */       return GenTraversableViewLike.DroppedWhile.class.viewIdentifier((GenTraversableViewLike.DroppedWhile)this);
/*    */     }
/*    */     
/*    */     public Function1<A, Object> pred() {
/* 59 */       return this.pred;
/*    */     }
/*    */     
/*    */     public StreamViewLike$$anon$7(StreamViewLike<A, Coll, This> $outer, Function1<A, Object> p$2) {
/* 59 */       super($outer);
/* 59 */       GenTraversableViewLike.DroppedWhile.class.$init$((GenTraversableViewLike.DroppedWhile)this);
/* 59 */       GenIterableViewLike.DroppedWhile.class.$init$((GenIterableViewLike.DroppedWhile)this);
/* 59 */       GenSeqViewLike.DroppedWhile.class.$init$((GenSeqViewLike.DroppedWhile)this);
/*    */     }
/*    */   }
/*    */   
/*    */   public class StreamViewLike$$anon$8 extends AbstractTransformed<A> implements TakenWhile {
/*    */     private final Function1<A, Object> pred;
/*    */     
/*    */     private final int len;
/*    */     
/*    */     private volatile boolean bitmap$0;
/*    */     
/*    */     private int len$lzycompute() {
/* 60 */       synchronized (this) {
/* 60 */         if (!this.bitmap$0) {
/* 60 */           this.len = GenSeqViewLike.TakenWhile.class.len((GenSeqViewLike.TakenWhile)this);
/* 60 */           this.bitmap$0 = true;
/*    */         } 
/*    */         /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/StreamViewLike$$anon$8}} */
/* 60 */         return this.len;
/*    */       } 
/*    */     }
/*    */     
/*    */     public int len() {
/* 60 */       return this.bitmap$0 ? this.len : len$lzycompute();
/*    */     }
/*    */     
/*    */     public int length() {
/* 60 */       return GenSeqViewLike.TakenWhile.class.length((GenSeqViewLike.TakenWhile)this);
/*    */     }
/*    */     
/*    */     public Object apply(int idx) {
/* 60 */       return GenSeqViewLike.TakenWhile.class.apply((GenSeqViewLike.TakenWhile)this, idx);
/*    */     }
/*    */     
/*    */     public Iterator<Object> iterator() {
/* 60 */       return GenIterableViewLike.TakenWhile.class.iterator((GenIterableViewLike.TakenWhile)this);
/*    */     }
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/* 60 */       GenTraversableViewLike.TakenWhile.class.foreach((GenTraversableViewLike.TakenWhile)this, f);
/*    */     }
/*    */     
/*    */     public final String viewIdentifier() {
/* 60 */       return GenTraversableViewLike.TakenWhile.class.viewIdentifier((GenTraversableViewLike.TakenWhile)this);
/*    */     }
/*    */     
/*    */     public Function1<A, Object> pred() {
/* 60 */       return this.pred;
/*    */     }
/*    */     
/*    */     public StreamViewLike$$anon$8(StreamViewLike<A, Coll, This> $outer, Function1<A, Object> p$3) {
/* 60 */       super($outer);
/* 60 */       GenTraversableViewLike.TakenWhile.class.$init$((GenTraversableViewLike.TakenWhile)this);
/* 60 */       GenIterableViewLike.TakenWhile.class.$init$((GenIterableViewLike.TakenWhile)this);
/* 60 */       GenSeqViewLike.TakenWhile.class.$init$((GenSeqViewLike.TakenWhile)this);
/*    */     }
/*    */   }
/*    */   
/*    */   public class StreamViewLike$$anon$9 extends AbstractTransformed<Tuple2<A, B>> implements Zipped<B> {
/*    */     private final GenIterable<B> other;
/*    */     
/*    */     private final Seq<Object> thatSeq;
/*    */     
/*    */     private volatile boolean bitmap$0;
/*    */     
/*    */     private Seq thatSeq$lzycompute() {
/* 61 */       synchronized (this) {
/* 61 */         if (!this.bitmap$0) {
/* 61 */           this.thatSeq = GenSeqViewLike.Zipped.class.thatSeq((GenSeqViewLike.Zipped)this);
/* 61 */           this.bitmap$0 = true;
/*    */         } 
/*    */         /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/StreamViewLike$$anon$9}} */
/* 61 */         return this.thatSeq;
/*    */       } 
/*    */     }
/*    */     
/*    */     public Seq<B> thatSeq() {
/* 61 */       return this.bitmap$0 ? (Seq)this.thatSeq : thatSeq$lzycompute();
/*    */     }
/*    */     
/*    */     public int length() {
/* 61 */       return GenSeqViewLike.Zipped.class.length((GenSeqViewLike.Zipped)this);
/*    */     }
/*    */     
/*    */     public Tuple2<A, B> apply(int idx) {
/* 61 */       return GenSeqViewLike.Zipped.class.apply((GenSeqViewLike.Zipped)this, idx);
/*    */     }
/*    */     
/*    */     public Iterator<Tuple2<A, B>> iterator() {
/* 61 */       return GenIterableViewLike.Zipped.class.iterator((GenIterableViewLike.Zipped)this);
/*    */     }
/*    */     
/*    */     public final String viewIdentifier() {
/* 61 */       return GenIterableViewLike.Zipped.class.viewIdentifier((GenIterableViewLike.Zipped)this);
/*    */     }
/*    */     
/*    */     public GenIterable<B> other() {
/* 61 */       return this.other;
/*    */     }
/*    */     
/*    */     public StreamViewLike$$anon$9(StreamViewLike<A, Coll, This> $outer, GenIterable<B> that$2) {
/* 61 */       super($outer);
/* 61 */       GenIterableViewLike.Zipped.class.$init$((GenIterableViewLike.Zipped)this);
/* 61 */       GenSeqViewLike.Zipped.class.$init$((GenSeqViewLike.Zipped)this);
/*    */     }
/*    */   }
/*    */   
/*    */   public class StreamViewLike$$anon$10 extends AbstractTransformed<Tuple2<A1, B>> implements ZippedAll<A1, B> {
/*    */     private final GenIterable<B> other;
/*    */     
/*    */     private final A1 thisElem;
/*    */     
/*    */     private final B thatElem;
/*    */     
/*    */     private final Seq<Object> thatSeq;
/*    */     
/*    */     private volatile boolean bitmap$0;
/*    */     
/*    */     private Seq thatSeq$lzycompute() {
/* 63 */       synchronized (this) {
/* 63 */         if (!this.bitmap$0) {
/* 63 */           this.thatSeq = GenSeqViewLike.ZippedAll.class.thatSeq((GenSeqViewLike.ZippedAll)this);
/* 63 */           this.bitmap$0 = true;
/*    */         } 
/*    */         /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/StreamViewLike$$anon$10}} */
/* 63 */         return this.thatSeq;
/*    */       } 
/*    */     }
/*    */     
/*    */     public Seq<B> thatSeq() {
/* 63 */       return this.bitmap$0 ? (Seq)this.thatSeq : thatSeq$lzycompute();
/*    */     }
/*    */     
/*    */     public int length() {
/* 63 */       return GenSeqViewLike.ZippedAll.class.length((GenSeqViewLike.ZippedAll)this);
/*    */     }
/*    */     
/*    */     public Tuple2<A1, B> apply(int idx) {
/* 63 */       return GenSeqViewLike.ZippedAll.class.apply((GenSeqViewLike.ZippedAll)this, idx);
/*    */     }
/*    */     
/*    */     public final String viewIdentifier() {
/* 63 */       return GenIterableViewLike.ZippedAll.class.viewIdentifier((GenIterableViewLike.ZippedAll)this);
/*    */     }
/*    */     
/*    */     public Iterator<Tuple2<A1, B>> iterator() {
/* 63 */       return GenIterableViewLike.ZippedAll.class.iterator((GenIterableViewLike.ZippedAll)this);
/*    */     }
/*    */     
/*    */     public GenIterable<B> other() {
/* 63 */       return this.other;
/*    */     }
/*    */     
/*    */     public A1 thisElem() {
/* 63 */       return this.thisElem;
/*    */     }
/*    */     
/*    */     public B thatElem() {
/* 63 */       return this.thatElem;
/*    */     }
/*    */     
/*    */     public StreamViewLike$$anon$10(StreamViewLike<A, Coll, This> $outer, GenIterable<B> that$3, Object _thisElem$1, Object _thatElem$1) {
/* 63 */       super($outer);
/* 63 */       GenIterableViewLike.ZippedAll.class.$init$((GenIterableViewLike.ZippedAll)this);
/* 63 */       GenSeqViewLike.ZippedAll.class.$init$((GenSeqViewLike.ZippedAll)this);
/*    */     }
/*    */   }
/*    */   
/*    */   public class StreamViewLike$$anon$13 implements Reversed {
/*    */     private final Object underlying;
/*    */     
/*    */     private volatile boolean bitmap$0;
/*    */     
/*    */     public String toString() {
/* 65 */       return StreamViewLike.Transformed$class.toString(this);
/*    */     }
/*    */     
/*    */     public <B, That> That force(CanBuildFrom bf) {
/* 65 */       return (That)StreamViewLike$class.force(this, bf);
/*    */     }
/*    */     
/*    */     public <B> StreamViewLike<A, Coll, StreamView<A, Coll>>.Transformed<B> newForced(Function0 xs) {
/* 65 */       return StreamViewLike$class.newForced(this, xs);
/*    */     }
/*    */     
/*    */     public <B> StreamViewLike<A, Coll, StreamView<A, Coll>>.Transformed<B> newAppended(GenTraversable that) {
/* 65 */       return StreamViewLike$class.newAppended(this, that);
/*    */     }
/*    */     
/*    */     public <B> StreamViewLike<A, Coll, StreamView<A, Coll>>.Transformed<B> newMapped(Function1 f) {
/* 65 */       return StreamViewLike$class.newMapped(this, f);
/*    */     }
/*    */     
/*    */     public <B> StreamViewLike<A, Coll, StreamView<A, Coll>>.Transformed<B> newFlatMapped(Function1 f) {
/* 65 */       return StreamViewLike$class.newFlatMapped(this, f);
/*    */     }
/*    */     
/*    */     public StreamViewLike<A, Coll, StreamView<A, Coll>>.Transformed<A> newFiltered(Function1 p) {
/* 65 */       return StreamViewLike$class.newFiltered(this, p);
/*    */     }
/*    */     
/*    */     public StreamViewLike<A, Coll, StreamView<A, Coll>>.Transformed<A> newSliced(SliceInterval _endpoints) {
/* 65 */       return StreamViewLike$class.newSliced(this, _endpoints);
/*    */     }
/*    */     
/*    */     public StreamViewLike<A, Coll, StreamView<A, Coll>>.Transformed<A> newDroppedWhile(Function1 p) {
/* 65 */       return StreamViewLike$class.newDroppedWhile(this, p);
/*    */     }
/*    */     
/*    */     public StreamViewLike<A, Coll, StreamView<A, Coll>>.Transformed<A> newTakenWhile(Function1 p) {
/* 65 */       return StreamViewLike$class.newTakenWhile(this, p);
/*    */     }
/*    */     
/*    */     public <B> StreamViewLike<A, Coll, StreamView<A, Coll>>.Transformed<Tuple2<A, B>> newZipped(GenIterable that) {
/* 65 */       return StreamViewLike$class.newZipped(this, that);
/*    */     }
/*    */     
/*    */     public <A1, B> StreamViewLike<A, Coll, StreamView<A, Coll>>.Transformed<Tuple2<A1, B>> newZippedAll(GenIterable that, Object _thisElem, Object _thatElem) {
/* 65 */       return StreamViewLike$class.newZippedAll(this, that, _thisElem, _thatElem);
/*    */     }
/*    */     
/*    */     public StreamViewLike<A, Coll, StreamView<A, Coll>>.Transformed<A> newReversed() {
/* 65 */       return StreamViewLike$class.newReversed(this);
/*    */     }
/*    */     
/*    */     public <B> StreamViewLike<A, Coll, StreamView<A, Coll>>.Transformed<B> newPatched(int _from, GenSeq _patch, int _replaced) {
/* 65 */       return StreamViewLike$class.newPatched(this, _from, _patch, _replaced);
/*    */     }
/*    */     
/*    */     public <B> StreamViewLike<A, Coll, StreamView<A, Coll>>.Transformed<B> newPrepended(Object elem) {
/* 65 */       return StreamViewLike$class.newPrepended(this, elem);
/*    */     }
/*    */     
/*    */     public String stringPrefix() {
/* 65 */       return StreamViewLike$class.stringPrefix(this);
/*    */     }
/*    */     
/*    */     public Iterator<Object> iterator() {
/* 65 */       return GenSeqViewLike.Reversed.class.iterator((GenSeqViewLike.Reversed)this);
/*    */     }
/*    */     
/*    */     public int length() {
/* 65 */       return GenSeqViewLike.Reversed.class.length((GenSeqViewLike.Reversed)this);
/*    */     }
/*    */     
/*    */     public Object apply(int idx) {
/* 65 */       return GenSeqViewLike.Reversed.class.apply((GenSeqViewLike.Reversed)this, idx);
/*    */     }
/*    */     
/*    */     public final String viewIdentifier() {
/* 65 */       return GenSeqViewLike.Reversed.class.viewIdentifier((GenSeqViewLike.Reversed)this);
/*    */     }
/*    */     
/*    */     public <U> void foreach(Function1 f) {
/* 65 */       IterableViewLike.Transformed.class.foreach((IterableViewLike.Transformed)this, f);
/*    */     }
/*    */     
/*    */     public boolean isEmpty() {
/* 65 */       return GenIterableViewLike.Transformed.class.isEmpty((GenIterableViewLike.Transformed)this);
/*    */     }
/*    */     
/*    */     public Option<A> headOption() {
/* 65 */       return TraversableViewLike.Transformed.class.headOption((TraversableViewLike.Transformed)this);
/*    */     }
/*    */     
/*    */     public Option<A> lastOption() {
/* 65 */       return TraversableViewLike.Transformed.class.lastOption((TraversableViewLike.Transformed)this);
/*    */     }
/*    */     
/*    */     private Object underlying$lzycompute() {
/* 65 */       synchronized (this) {
/* 65 */         if (!this.bitmap$0) {
/* 65 */           this.underlying = GenTraversableViewLike.Transformed.class.underlying((GenTraversableViewLike.Transformed)this);
/* 65 */           this.bitmap$0 = true;
/*    */         } 
/*    */         /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/StreamViewLike$$anon$13}} */
/* 65 */         return this.underlying;
/*    */       } 
/*    */     }
/*    */     
/*    */     public Coll underlying() {
/* 65 */       return this.bitmap$0 ? (Coll)this.underlying : (Coll)underlying$lzycompute();
/*    */     }
/*    */     
/*    */     public final String viewIdString() {
/* 65 */       return GenTraversableViewLike.Transformed.class.viewIdString((GenTraversableViewLike.Transformed)this);
/*    */     }
/*    */     
/*    */     public SeqViewLike<A, Coll, StreamView<A, Coll>>.Transformed<A> newTaken(int n) {
/* 65 */       return SeqViewLike.class.newTaken(this, n);
/*    */     }
/*    */     
/*    */     public SeqViewLike<A, Coll, StreamView<A, Coll>>.Transformed<A> newDropped(int n) {
/* 65 */       return SeqViewLike.class.newDropped(this, n);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> reverse() {
/* 65 */       return (StreamView<A, Coll>)SeqViewLike.class.reverse(this);
/*    */     }
/*    */     
/*    */     public <B, That> That patch(int from, GenSeq patch, int replaced, CanBuildFrom bf) {
/* 65 */       return (That)SeqViewLike.class.patch(this, from, patch, replaced, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That padTo(int len, Object elem, CanBuildFrom bf) {
/* 65 */       return (That)SeqViewLike.class.padTo(this, len, elem, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That reverseMap(Function1 f, CanBuildFrom bf) {
/* 65 */       return (That)SeqViewLike.class.reverseMap(this, f, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That updated(int index, Object elem, CanBuildFrom bf) {
/* 65 */       return (That)SeqViewLike.class.updated(this, index, elem, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$colon(Object elem, CanBuildFrom bf) {
/* 65 */       return (That)SeqViewLike.class.$plus$colon(this, elem, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That $colon$plus(Object elem, CanBuildFrom bf) {
/* 65 */       return (That)SeqViewLike.class.$colon$plus(this, elem, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That union(GenSeq that, CanBuildFrom bf) {
/* 65 */       return (That)SeqViewLike.class.union(this, that, bf);
/*    */     }
/*    */     
/*    */     public <B> StreamView<A, Coll> diff(GenSeq that) {
/* 65 */       return (StreamView<A, Coll>)SeqViewLike.class.diff(this, that);
/*    */     }
/*    */     
/*    */     public <B> StreamView<A, Coll> intersect(GenSeq that) {
/* 65 */       return (StreamView<A, Coll>)SeqViewLike.class.intersect(this, that);
/*    */     }
/*    */     
/*    */     public <B> StreamView<A, Coll> sorted(Ordering ord) {
/* 65 */       return (StreamView<A, Coll>)SeqViewLike.class.sorted(this, ord);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> drop(int n) {
/* 65 */       return (StreamView<A, Coll>)IterableViewLike.class.drop((IterableViewLike)this, n);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> take(int n) {
/* 65 */       return (StreamView<A, Coll>)IterableViewLike.class.take((IterableViewLike)this, n);
/*    */     }
/*    */     
/*    */     public <A1, B, That> That zip(GenIterable that, CanBuildFrom bf) {
/* 65 */       return (That)IterableViewLike.class.zip((IterableViewLike)this, that, bf);
/*    */     }
/*    */     
/*    */     public <A1, That> That zipWithIndex(CanBuildFrom bf) {
/* 65 */       return (That)IterableViewLike.class.zipWithIndex((IterableViewLike)this, bf);
/*    */     }
/*    */     
/*    */     public <B, A1, That> That zipAll(GenIterable that, Object thisElem, Object thatElem, CanBuildFrom bf) {
/* 65 */       return (That)IterableViewLike.class.zipAll((IterableViewLike)this, that, thisElem, thatElem, bf);
/*    */     }
/*    */     
/*    */     public Iterator<StreamView<A, Coll>> grouped(int size) {
/* 65 */       return IterableViewLike.class.grouped((IterableViewLike)this, size);
/*    */     }
/*    */     
/*    */     public Iterator<StreamView<A, Coll>> sliding(int size, int step) {
/* 65 */       return IterableViewLike.class.sliding((IterableViewLike)this, size, step);
/*    */     }
/*    */     
/*    */     public Builder<A, StreamView<A, Coll>> newBuilder() {
/* 65 */       return TraversableViewLike.class.newBuilder((TraversableViewLike)this);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus(GenTraversableOnce xs, CanBuildFrom bf) {
/* 65 */       return (That)TraversableViewLike.class.$plus$plus((TraversableViewLike)this, xs, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That map(Function1 f, CanBuildFrom bf) {
/* 65 */       return (That)TraversableViewLike.class.map((TraversableViewLike)this, f, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That collect(PartialFunction pf, CanBuildFrom bf) {
/* 65 */       return (That)TraversableViewLike.class.collect((TraversableViewLike)this, pf, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That flatMap(Function1 f, CanBuildFrom bf) {
/* 65 */       return (That)TraversableViewLike.class.flatMap((TraversableViewLike)this, f, bf);
/*    */     }
/*    */     
/*    */     public <B> TraversableViewLike<A, Coll, StreamView<A, Coll>>.Transformed<B> flatten(Function1 asTraversable) {
/* 65 */       return TraversableViewLike.class.flatten((TraversableViewLike)this, asTraversable);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> filter(Function1 p) {
/* 65 */       return (StreamView<A, Coll>)TraversableViewLike.class.filter((TraversableViewLike)this, p);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> withFilter(Function1 p) {
/* 65 */       return (StreamView<A, Coll>)TraversableViewLike.class.withFilter((TraversableViewLike)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<StreamView<A, Coll>, StreamView<A, Coll>> partition(Function1 p) {
/* 65 */       return TraversableViewLike.class.partition((TraversableViewLike)this, p);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> init() {
/* 65 */       return (StreamView<A, Coll>)TraversableViewLike.class.init((TraversableViewLike)this);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> slice(int from, int until) {
/* 65 */       return (StreamView<A, Coll>)TraversableViewLike.class.slice((TraversableViewLike)this, from, until);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> dropWhile(Function1 p) {
/* 65 */       return (StreamView<A, Coll>)TraversableViewLike.class.dropWhile((TraversableViewLike)this, p);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> takeWhile(Function1 p) {
/* 65 */       return (StreamView<A, Coll>)TraversableViewLike.class.takeWhile((TraversableViewLike)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<StreamView<A, Coll>, StreamView<A, Coll>> span(Function1 p) {
/* 65 */       return TraversableViewLike.class.span((TraversableViewLike)this, p);
/*    */     }
/*    */     
/*    */     public Tuple2<StreamView<A, Coll>, StreamView<A, Coll>> splitAt(int n) {
/* 65 */       return TraversableViewLike.class.splitAt((TraversableViewLike)this, n);
/*    */     }
/*    */     
/*    */     public <B, That> That scanLeft(Object z, Function2 op, CanBuildFrom bf) {
/* 65 */       return (That)TraversableViewLike.class.scanLeft((TraversableViewLike)this, z, op, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That scanRight(Object z, Function2 op, CanBuildFrom bf) {
/* 65 */       return (That)TraversableViewLike.class.scanRight((TraversableViewLike)this, z, op, bf);
/*    */     }
/*    */     
/*    */     public <K> Map<K, StreamView<A, Coll>> groupBy(Function1 f) {
/* 65 */       return TraversableViewLike.class.groupBy((TraversableViewLike)this, f);
/*    */     }
/*    */     
/*    */     public <A1, A2> Tuple2<TraversableViewLike<A, Coll, StreamView<A, Coll>>.Transformed<A1>, TraversableViewLike<A, Coll, StreamView<A, Coll>>.Transformed<A2>> unzip(Function1 asPair) {
/* 65 */       return TraversableViewLike.class.unzip((TraversableViewLike)this, asPair);
/*    */     }
/*    */     
/*    */     public <A1, A2, A3> Tuple3<TraversableViewLike<A, Coll, StreamView<A, Coll>>.Transformed<A1>, TraversableViewLike<A, Coll, StreamView<A, Coll>>.Transformed<A2>, TraversableViewLike<A, Coll, StreamView<A, Coll>>.Transformed<A3>> unzip3(Function1 asTriple) {
/* 65 */       return TraversableViewLike.class.unzip3((TraversableViewLike)this, asTriple);
/*    */     }
/*    */     
/*    */     public String viewToString() {
/* 65 */       return GenTraversableViewLike.class.viewToString((GenTraversableViewLike)this);
/*    */     }
/*    */     
/*    */     public Seq<A> thisSeq() {
/* 65 */       return ViewMkString.class.thisSeq((ViewMkString)this);
/*    */     }
/*    */     
/*    */     public String mkString() {
/* 65 */       return ViewMkString.class.mkString((ViewMkString)this);
/*    */     }
/*    */     
/*    */     public String mkString(String sep) {
/* 65 */       return ViewMkString.class.mkString((ViewMkString)this, sep);
/*    */     }
/*    */     
/*    */     public String mkString(String start, String sep, String end) {
/* 65 */       return ViewMkString.class.mkString((ViewMkString)this, start, sep, end);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String start, String sep, String end) {
/* 65 */       return ViewMkString.class.addString((ViewMkString)this, b, start, sep, end);
/*    */     }
/*    */     
/*    */     public GenericCompanion<Seq> companion() {
/* 65 */       return Seq.class.companion((Seq)this);
/*    */     }
/*    */     
/*    */     public Seq<A> seq() {
/* 65 */       return Seq.class.seq((Seq)this);
/*    */     }
/*    */     
/*    */     public Seq<A> thisCollection() {
/* 65 */       return SeqLike.class.thisCollection((SeqLike)this);
/*    */     }
/*    */     
/*    */     public Seq<A> toCollection(Object repr) {
/* 65 */       return SeqLike.class.toCollection((SeqLike)this, repr);
/*    */     }
/*    */     
/*    */     public Combiner<A, ParSeq<A>> parCombiner() {
/* 65 */       return SeqLike.class.parCombiner((SeqLike)this);
/*    */     }
/*    */     
/*    */     public int lengthCompare(int len) {
/* 65 */       return SeqLike.class.lengthCompare((SeqLike)this, len);
/*    */     }
/*    */     
/*    */     public int size() {
/* 65 */       return SeqLike.class.size((SeqLike)this);
/*    */     }
/*    */     
/*    */     public int segmentLength(Function1 p, int from) {
/* 65 */       return SeqLike.class.segmentLength((SeqLike)this, p, from);
/*    */     }
/*    */     
/*    */     public int indexWhere(Function1 p, int from) {
/* 65 */       return SeqLike.class.indexWhere((SeqLike)this, p, from);
/*    */     }
/*    */     
/*    */     public int lastIndexWhere(Function1 p, int end) {
/* 65 */       return SeqLike.class.lastIndexWhere((SeqLike)this, p, end);
/*    */     }
/*    */     
/*    */     public Iterator<StreamView<A, Coll>> permutations() {
/* 65 */       return SeqLike.class.permutations((SeqLike)this);
/*    */     }
/*    */     
/*    */     public Iterator<StreamView<A, Coll>> combinations(int n) {
/* 65 */       return SeqLike.class.combinations((SeqLike)this, n);
/*    */     }
/*    */     
/*    */     public Iterator<A> reverseIterator() {
/* 65 */       return SeqLike.class.reverseIterator((SeqLike)this);
/*    */     }
/*    */     
/*    */     public <B> boolean startsWith(GenSeq that, int offset) {
/* 65 */       return SeqLike.class.startsWith((SeqLike)this, that, offset);
/*    */     }
/*    */     
/*    */     public <B> boolean endsWith(GenSeq that) {
/* 65 */       return SeqLike.class.endsWith((SeqLike)this, that);
/*    */     }
/*    */     
/*    */     public <B> int indexOfSlice(GenSeq that) {
/* 65 */       return SeqLike.class.indexOfSlice((SeqLike)this, that);
/*    */     }
/*    */     
/*    */     public <B> int indexOfSlice(GenSeq that, int from) {
/* 65 */       return SeqLike.class.indexOfSlice((SeqLike)this, that, from);
/*    */     }
/*    */     
/*    */     public <B> int lastIndexOfSlice(GenSeq that) {
/* 65 */       return SeqLike.class.lastIndexOfSlice((SeqLike)this, that);
/*    */     }
/*    */     
/*    */     public <B> int lastIndexOfSlice(GenSeq that, int end) {
/* 65 */       return SeqLike.class.lastIndexOfSlice((SeqLike)this, that, end);
/*    */     }
/*    */     
/*    */     public <B> boolean containsSlice(GenSeq that) {
/* 65 */       return SeqLike.class.containsSlice((SeqLike)this, that);
/*    */     }
/*    */     
/*    */     public boolean contains(Object elem) {
/* 65 */       return SeqLike.class.contains((SeqLike)this, elem);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> distinct() {
/* 65 */       return (StreamView<A, Coll>)SeqLike.class.distinct((SeqLike)this);
/*    */     }
/*    */     
/*    */     public <B> boolean corresponds(GenSeq that, Function2 p) {
/* 65 */       return SeqLike.class.corresponds((SeqLike)this, that, p);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> sortWith(Function2 lt) {
/* 65 */       return (StreamView<A, Coll>)SeqLike.class.sortWith((SeqLike)this, lt);
/*    */     }
/*    */     
/*    */     public <B> StreamView<A, Coll> sortBy(Function1 f, Ordering ord) {
/* 65 */       return (StreamView<A, Coll>)SeqLike.class.sortBy((SeqLike)this, f, ord);
/*    */     }
/*    */     
/*    */     public Seq<A> toSeq() {
/* 65 */       return SeqLike.class.toSeq((SeqLike)this);
/*    */     }
/*    */     
/*    */     public Range indices() {
/* 65 */       return SeqLike.class.indices((SeqLike)this);
/*    */     }
/*    */     
/*    */     public Object view() {
/* 65 */       return SeqLike.class.view((SeqLike)this);
/*    */     }
/*    */     
/*    */     public SeqView<A, StreamView<A, Coll>> view(int from, int until) {
/* 65 */       return SeqLike.class.view((SeqLike)this, from, until);
/*    */     }
/*    */     
/*    */     public boolean isDefinedAt(int idx) {
/* 65 */       return GenSeqLike.class.isDefinedAt((GenSeqLike)this, idx);
/*    */     }
/*    */     
/*    */     public int prefixLength(Function1 p) {
/* 65 */       return GenSeqLike.class.prefixLength((GenSeqLike)this, p);
/*    */     }
/*    */     
/*    */     public int indexWhere(Function1 p) {
/* 65 */       return GenSeqLike.class.indexWhere((GenSeqLike)this, p);
/*    */     }
/*    */     
/*    */     public <B> int indexOf(Object elem) {
/* 65 */       return GenSeqLike.class.indexOf((GenSeqLike)this, elem);
/*    */     }
/*    */     
/*    */     public <B> int indexOf(Object elem, int from) {
/* 65 */       return GenSeqLike.class.indexOf((GenSeqLike)this, elem, from);
/*    */     }
/*    */     
/*    */     public <B> int lastIndexOf(Object elem) {
/* 65 */       return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem);
/*    */     }
/*    */     
/*    */     public <B> int lastIndexOf(Object elem, int end) {
/* 65 */       return GenSeqLike.class.lastIndexOf((GenSeqLike)this, elem, end);
/*    */     }
/*    */     
/*    */     public int lastIndexWhere(Function1 p) {
/* 65 */       return GenSeqLike.class.lastIndexWhere((GenSeqLike)this, p);
/*    */     }
/*    */     
/*    */     public <B> boolean startsWith(GenSeq that) {
/* 65 */       return GenSeqLike.class.startsWith((GenSeqLike)this, that);
/*    */     }
/*    */     
/*    */     public int hashCode() {
/* 65 */       return GenSeqLike.class.hashCode((GenSeqLike)this);
/*    */     }
/*    */     
/*    */     public boolean equals(Object that) {
/* 65 */       return GenSeqLike.class.equals((GenSeqLike)this, that);
/*    */     }
/*    */     
/*    */     public boolean forall(Function1 p) {
/* 65 */       return IterableLike.class.forall((IterableLike)this, p);
/*    */     }
/*    */     
/*    */     public boolean exists(Function1 p) {
/* 65 */       return IterableLike.class.exists((IterableLike)this, p);
/*    */     }
/*    */     
/*    */     public Option<A> find(Function1 p) {
/* 65 */       return IterableLike.class.find((IterableLike)this, p);
/*    */     }
/*    */     
/*    */     public <B> B foldRight(Object z, Function2 op) {
/* 65 */       return (B)IterableLike.class.foldRight((IterableLike)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceRight(Function2 op) {
/* 65 */       return (B)IterableLike.class.reduceRight((IterableLike)this, op);
/*    */     }
/*    */     
/*    */     public Iterable<A> toIterable() {
/* 65 */       return IterableLike.class.toIterable((IterableLike)this);
/*    */     }
/*    */     
/*    */     public Iterator<A> toIterator() {
/* 65 */       return IterableLike.class.toIterator((IterableLike)this);
/*    */     }
/*    */     
/*    */     public A head() {
/* 65 */       return (A)IterableLike.class.head((IterableLike)this);
/*    */     }
/*    */     
/*    */     public Iterator<StreamView<A, Coll>> sliding(int size) {
/* 65 */       return IterableLike.class.sliding((IterableLike)this, size);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> takeRight(int n) {
/* 65 */       return (StreamView<A, Coll>)IterableLike.class.takeRight((IterableLike)this, n);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> dropRight(int n) {
/* 65 */       return (StreamView<A, Coll>)IterableLike.class.dropRight((IterableLike)this, n);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start, int len) {
/* 65 */       IterableLike.class.copyToArray((IterableLike)this, xs, start, len);
/*    */     }
/*    */     
/*    */     public <B> boolean sameElements(GenIterable that) {
/* 65 */       return IterableLike.class.sameElements((IterableLike)this, that);
/*    */     }
/*    */     
/*    */     public Stream<A> toStream() {
/* 65 */       return IterableLike.class.toStream((IterableLike)this);
/*    */     }
/*    */     
/*    */     public boolean canEqual(Object that) {
/* 65 */       return IterableLike.class.canEqual((IterableLike)this, that);
/*    */     }
/*    */     
/*    */     public <B> Builder<B, Seq<B>> genericBuilder() {
/* 65 */       return GenericTraversableTemplate.class.genericBuilder((GenericTraversableTemplate)this);
/*    */     }
/*    */     
/*    */     public <B> Seq<Seq<B>> transpose(Function1 asTraversable) {
/* 65 */       return (Seq<Seq<B>>)GenericTraversableTemplate.class.transpose((GenericTraversableTemplate)this, asTraversable);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> repr() {
/* 65 */       return (StreamView<A, Coll>)TraversableLike.class.repr((TraversableLike)this);
/*    */     }
/*    */     
/*    */     public final boolean isTraversableAgain() {
/* 65 */       return TraversableLike.class.isTraversableAgain((TraversableLike)this);
/*    */     }
/*    */     
/*    */     public boolean hasDefiniteSize() {
/* 65 */       return TraversableLike.class.hasDefiniteSize((TraversableLike)this);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus$colon(TraversableOnce that, CanBuildFrom bf) {
/* 65 */       return (That)TraversableLike.class.$plus$plus$colon((TraversableLike)this, that, bf);
/*    */     }
/*    */     
/*    */     public <B, That> That $plus$plus$colon(Traversable that, CanBuildFrom bf) {
/* 65 */       return (That)TraversableLike.class.$plus$plus$colon((TraversableLike)this, that, bf);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> filterNot(Function1 p) {
/* 65 */       return (StreamView<A, Coll>)TraversableLike.class.filterNot((TraversableLike)this, p);
/*    */     }
/*    */     
/*    */     public <B, That> That scan(Object z, Function2 op, CanBuildFrom cbf) {
/* 65 */       return (That)TraversableLike.class.scan((TraversableLike)this, z, op, cbf);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> tail() {
/* 65 */       return (StreamView<A, Coll>)TraversableLike.class.tail((TraversableLike)this);
/*    */     }
/*    */     
/*    */     public A last() {
/* 65 */       return (A)TraversableLike.class.last((TraversableLike)this);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> sliceWithKnownDelta(int from, int until, int delta) {
/* 65 */       return (StreamView<A, Coll>)TraversableLike.class.sliceWithKnownDelta((TraversableLike)this, from, until, delta);
/*    */     }
/*    */     
/*    */     public StreamView<A, Coll> sliceWithKnownBound(int from, int until) {
/* 65 */       return (StreamView<A, Coll>)TraversableLike.class.sliceWithKnownBound((TraversableLike)this, from, until);
/*    */     }
/*    */     
/*    */     public Iterator<StreamView<A, Coll>> tails() {
/* 65 */       return TraversableLike.class.tails((TraversableLike)this);
/*    */     }
/*    */     
/*    */     public Iterator<StreamView<A, Coll>> inits() {
/* 65 */       return TraversableLike.class.inits((TraversableLike)this);
/*    */     }
/*    */     
/*    */     public Traversable<A> toTraversable() {
/* 65 */       return TraversableLike.class.toTraversable((TraversableLike)this);
/*    */     }
/*    */     
/*    */     public <Col> Col to(CanBuildFrom cbf) {
/* 65 */       return (Col)TraversableLike.class.to((TraversableLike)this, cbf);
/*    */     }
/*    */     
/*    */     public ParSeq<A> par() {
/* 65 */       return (ParSeq<A>)Parallelizable.class.par((Parallelizable)this);
/*    */     }
/*    */     
/*    */     public List<A> reversed() {
/* 65 */       return TraversableOnce.class.reversed((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public boolean nonEmpty() {
/* 65 */       return TraversableOnce.class.nonEmpty((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public int count(Function1 p) {
/* 65 */       return TraversableOnce.class.count((TraversableOnce)this, p);
/*    */     }
/*    */     
/*    */     public <B> Option<B> collectFirst(PartialFunction pf) {
/* 65 */       return TraversableOnce.class.collectFirst((TraversableOnce)this, pf);
/*    */     }
/*    */     
/*    */     public <B> B $div$colon(Object z, Function2 op) {
/* 65 */       return (B)TraversableOnce.class.$div$colon((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B $colon$bslash(Object z, Function2 op) {
/* 65 */       return (B)TraversableOnce.class.$colon$bslash((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B foldLeft(Object z, Function2 op) {
/* 65 */       return (B)TraversableOnce.class.foldLeft((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B reduceLeft(Function2 op) {
/* 65 */       return (B)TraversableOnce.class.reduceLeft((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceLeftOption(Function2 op) {
/* 65 */       return TraversableOnce.class.reduceLeftOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <B> Option<B> reduceRightOption(Function2 op) {
/* 65 */       return TraversableOnce.class.reduceRightOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> A1 reduce(Function2 op) {
/* 65 */       return (A1)TraversableOnce.class.reduce((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> Option<A1> reduceOption(Function2 op) {
/* 65 */       return TraversableOnce.class.reduceOption((TraversableOnce)this, op);
/*    */     }
/*    */     
/*    */     public <A1> A1 fold(Object z, Function2 op) {
/* 65 */       return (A1)TraversableOnce.class.fold((TraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <B> B aggregate(Object z, Function2 seqop, Function2 combop) {
/* 65 */       return (B)TraversableOnce.class.aggregate((TraversableOnce)this, z, seqop, combop);
/*    */     }
/*    */     
/*    */     public <B> B sum(Numeric num) {
/* 65 */       return (B)TraversableOnce.class.sum((TraversableOnce)this, num);
/*    */     }
/*    */     
/*    */     public <B> B product(Numeric num) {
/* 65 */       return (B)TraversableOnce.class.product((TraversableOnce)this, num);
/*    */     }
/*    */     
/*    */     public <B> A min(Ordering cmp) {
/* 65 */       return (A)TraversableOnce.class.min((TraversableOnce)this, cmp);
/*    */     }
/*    */     
/*    */     public <B> A max(Ordering cmp) {
/* 65 */       return (A)TraversableOnce.class.max((TraversableOnce)this, cmp);
/*    */     }
/*    */     
/*    */     public <B> A maxBy(Function1 f, Ordering cmp) {
/* 65 */       return (A)TraversableOnce.class.maxBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public <B> A minBy(Function1 f, Ordering cmp) {
/* 65 */       return (A)TraversableOnce.class.minBy((TraversableOnce)this, f, cmp);
/*    */     }
/*    */     
/*    */     public <B> void copyToBuffer(Buffer dest) {
/* 65 */       TraversableOnce.class.copyToBuffer((TraversableOnce)this, dest);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs, int start) {
/* 65 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs, start);
/*    */     }
/*    */     
/*    */     public <B> void copyToArray(Object xs) {
/* 65 */       TraversableOnce.class.copyToArray((TraversableOnce)this, xs);
/*    */     }
/*    */     
/*    */     public <B> Object toArray(ClassTag evidence$1) {
/* 65 */       return TraversableOnce.class.toArray((TraversableOnce)this, evidence$1);
/*    */     }
/*    */     
/*    */     public List<A> toList() {
/* 65 */       return TraversableOnce.class.toList((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public IndexedSeq<A> toIndexedSeq() {
/* 65 */       return TraversableOnce.class.toIndexedSeq((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <B> Buffer<B> toBuffer() {
/* 65 */       return TraversableOnce.class.toBuffer((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <B> Set<B> toSet() {
/* 65 */       return TraversableOnce.class.toSet((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public Vector<A> toVector() {
/* 65 */       return TraversableOnce.class.toVector((TraversableOnce)this);
/*    */     }
/*    */     
/*    */     public <T, U> Map<T, U> toMap(Predef$.less.colon.less ev) {
/* 65 */       return TraversableOnce.class.toMap((TraversableOnce)this, ev);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b, String sep) {
/* 65 */       return TraversableOnce.class.addString((TraversableOnce)this, b, sep);
/*    */     }
/*    */     
/*    */     public StringBuilder addString(StringBuilder b) {
/* 65 */       return TraversableOnce.class.addString((TraversableOnce)this, b);
/*    */     }
/*    */     
/*    */     public <A1> A1 $div$colon$bslash(Object z, Function2 op) {
/* 65 */       return (A1)GenTraversableOnce.class.$div$colon$bslash((GenTraversableOnce)this, z, op);
/*    */     }
/*    */     
/*    */     public <A1, B1> PartialFunction<A1, B1> orElse(PartialFunction that) {
/* 65 */       return PartialFunction.class.orElse((PartialFunction)this, that);
/*    */     }
/*    */     
/*    */     public <C> PartialFunction<Object, C> andThen(Function1 k) {
/* 65 */       return PartialFunction.class.andThen((PartialFunction)this, k);
/*    */     }
/*    */     
/*    */     public Function1<Object, Option<A>> lift() {
/* 65 */       return PartialFunction.class.lift((PartialFunction)this);
/*    */     }
/*    */     
/*    */     public <A1, B1> B1 applyOrElse(Object x, Function1 default) {
/* 65 */       return (B1)PartialFunction.class.applyOrElse((PartialFunction)this, x, default);
/*    */     }
/*    */     
/*    */     public <U> Function1<Object, Object> runWith(Function1 action) {
/* 65 */       return PartialFunction.class.runWith((PartialFunction)this, action);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZD$sp(double v1) {
/* 65 */       return Function1.class.apply$mcZD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDD$sp(double v1) {
/* 65 */       return Function1.class.apply$mcDD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFD$sp(double v1) {
/* 65 */       return Function1.class.apply$mcFD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcID$sp(double v1) {
/* 65 */       return Function1.class.apply$mcID$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJD$sp(double v1) {
/* 65 */       return Function1.class.apply$mcJD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVD$sp(double v1) {
/* 65 */       Function1.class.apply$mcVD$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZF$sp(float v1) {
/* 65 */       return Function1.class.apply$mcZF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDF$sp(float v1) {
/* 65 */       return Function1.class.apply$mcDF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFF$sp(float v1) {
/* 65 */       return Function1.class.apply$mcFF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcIF$sp(float v1) {
/* 65 */       return Function1.class.apply$mcIF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJF$sp(float v1) {
/* 65 */       return Function1.class.apply$mcJF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVF$sp(float v1) {
/* 65 */       Function1.class.apply$mcVF$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZI$sp(int v1) {
/* 65 */       return Function1.class.apply$mcZI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDI$sp(int v1) {
/* 65 */       return Function1.class.apply$mcDI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFI$sp(int v1) {
/* 65 */       return Function1.class.apply$mcFI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcII$sp(int v1) {
/* 65 */       return Function1.class.apply$mcII$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJI$sp(int v1) {
/* 65 */       return Function1.class.apply$mcJI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVI$sp(int v1) {
/* 65 */       Function1.class.apply$mcVI$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public boolean apply$mcZJ$sp(long v1) {
/* 65 */       return Function1.class.apply$mcZJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public double apply$mcDJ$sp(long v1) {
/* 65 */       return Function1.class.apply$mcDJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public float apply$mcFJ$sp(long v1) {
/* 65 */       return Function1.class.apply$mcFJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public int apply$mcIJ$sp(long v1) {
/* 65 */       return Function1.class.apply$mcIJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public long apply$mcJJ$sp(long v1) {
/* 65 */       return Function1.class.apply$mcJJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public void apply$mcVJ$sp(long v1) {
/* 65 */       Function1.class.apply$mcVJ$sp((Function1)this, v1);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, A> compose(Function1 g) {
/* 65 */       return Function1.class.compose((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZD$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcZD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDD$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcDD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFD$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcFD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcID$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcID$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJD$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcJD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVD$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcVD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZF$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcZF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDF$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcDF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFF$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcFF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcIF$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcIF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJF$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcJF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVF$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcVF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZI$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcZI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDI$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcDI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFI$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcFI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcII$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcII$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJI$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcJI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVI$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcVI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcZJ$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcZJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcDJ$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcDJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcFJ$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcFJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcIJ$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcIJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, Object> compose$mcJJ$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcJJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<A, BoxedUnit> compose$mcVJ$sp(Function1 g) {
/* 65 */       return Function1.class.compose$mcVJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZD$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcZD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDD$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcDD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFD$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcFD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcID$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcID$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJD$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcJD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVD$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcVD$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZF$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcZF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDF$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcDF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFF$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcFF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcIF$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcIF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJF$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcJF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVF$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcVF$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZI$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcZI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDI$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcDI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFI$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcFI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcII$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcII$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJI$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcJI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVI$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcVI$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcZJ$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcZJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcDJ$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcDJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcFJ$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcFJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcIJ$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcIJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcJJ$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcJJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public <A> Function1<Object, A> andThen$mcVJ$sp(Function1 g) {
/* 65 */       return Function1.class.andThen$mcVJ$sp((Function1)this, g);
/*    */     }
/*    */     
/*    */     public StreamViewLike$$anon$13(StreamViewLike $outer) {
/* 65 */       Function1.class.$init$((Function1)this);
/* 65 */       PartialFunction.class.$init$((PartialFunction)this);
/* 65 */       GenTraversableOnce.class.$init$((GenTraversableOnce)this);
/* 65 */       TraversableOnce.class.$init$((TraversableOnce)this);
/* 65 */       Parallelizable.class.$init$((Parallelizable)this);
/* 65 */       TraversableLike.class.$init$((TraversableLike)this);
/* 65 */       GenericTraversableTemplate.class.$init$((GenericTraversableTemplate)this);
/* 65 */       GenTraversable.class.$init$((GenTraversable)this);
/* 65 */       Traversable.class.$init$((Traversable)this);
/* 65 */       GenIterable.class.$init$((GenIterable)this);
/* 65 */       IterableLike.class.$init$((IterableLike)this);
/* 65 */       Iterable.class.$init$((Iterable)this);
/* 65 */       GenSeqLike.class.$init$((GenSeqLike)this);
/* 65 */       GenSeq.class.$init$((GenSeq)this);
/* 65 */       SeqLike.class.$init$((SeqLike)this);
/* 65 */       Seq.class.$init$((Seq)this);
/* 65 */       ViewMkString.class.$init$((ViewMkString)this);
/* 65 */       GenTraversableViewLike.class.$init$((GenTraversableViewLike)this);
/* 65 */       TraversableViewLike.class.$init$((TraversableViewLike)this);
/* 65 */       GenIterableViewLike.class.$init$((GenIterableViewLike)this);
/* 65 */       IterableViewLike.class.$init$((IterableViewLike)this);
/* 65 */       GenSeqViewLike.class.$init$((GenSeqViewLike)this);
/* 65 */       SeqViewLike.class.$init$(this);
/* 65 */       GenTraversableViewLike.Transformed.class.$init$((GenTraversableViewLike.Transformed)this);
/* 65 */       TraversableViewLike.Transformed.class.$init$((TraversableViewLike.Transformed)this);
/* 65 */       GenIterableViewLike.Transformed.class.$init$((GenIterableViewLike.Transformed)this);
/* 65 */       IterableViewLike.Transformed.class.$init$((IterableViewLike.Transformed)this);
/* 65 */       GenSeqViewLike.Transformed.class.$init$((GenSeqViewLike.Transformed)this);
/* 65 */       SeqViewLike.Transformed.class.$init$(this);
/* 65 */       GenSeqViewLike.Reversed.class.$init$((GenSeqViewLike.Reversed)this);
/* 65 */       StreamViewLike$class.$init$(this);
/* 65 */       StreamViewLike.Transformed$class.$init$(this);
/*    */     }
/*    */   }
/*    */   
/*    */   public class StreamViewLike$$anon$11 extends AbstractTransformed<B> implements Patched<B> {
/*    */     private final int from;
/*    */     
/*    */     private final GenSeq<B> patch;
/*    */     
/*    */     private final int replaced;
/*    */     
/*    */     private final int scala$collection$GenSeqViewLike$Patched$$plen;
/*    */     
/*    */     private volatile boolean bitmap$0;
/*    */     
/*    */     private int scala$collection$GenSeqViewLike$Patched$$plen$lzycompute() {
/* 67 */       synchronized (this) {
/* 67 */         if (!this.bitmap$0) {
/* 67 */           this.scala$collection$GenSeqViewLike$Patched$$plen = GenSeqViewLike.Patched.class.scala$collection$GenSeqViewLike$Patched$$plen((GenSeqViewLike.Patched)this);
/* 67 */           this.bitmap$0 = true;
/*    */         } 
/*    */         /* monitor exit ThisExpression{ObjectType{scala/collection/immutable/StreamViewLike$$anon$11}} */
/* 67 */         return this.scala$collection$GenSeqViewLike$Patched$$plen;
/*    */       } 
/*    */     }
/*    */     
/*    */     public int scala$collection$GenSeqViewLike$Patched$$plen() {
/* 67 */       return this.bitmap$0 ? this.scala$collection$GenSeqViewLike$Patched$$plen : scala$collection$GenSeqViewLike$Patched$$plen$lzycompute();
/*    */     }
/*    */     
/*    */     public Iterator<B> iterator() {
/* 67 */       return GenSeqViewLike.Patched.class.iterator((GenSeqViewLike.Patched)this);
/*    */     }
/*    */     
/*    */     public int length() {
/* 67 */       return GenSeqViewLike.Patched.class.length((GenSeqViewLike.Patched)this);
/*    */     }
/*    */     
/*    */     public B apply(int idx) {
/* 67 */       return (B)GenSeqViewLike.Patched.class.apply((GenSeqViewLike.Patched)this, idx);
/*    */     }
/*    */     
/*    */     public final String viewIdentifier() {
/* 67 */       return GenSeqViewLike.Patched.class.viewIdentifier((GenSeqViewLike.Patched)this);
/*    */     }
/*    */     
/*    */     public int from() {
/* 67 */       return this.from;
/*    */     }
/*    */     
/*    */     public GenSeq<B> patch() {
/* 67 */       return this.patch;
/*    */     }
/*    */     
/*    */     public int replaced() {
/* 67 */       return this.replaced;
/*    */     }
/*    */     
/*    */     public StreamViewLike$$anon$11(StreamViewLike<A, Coll, This> $outer, int _from$1, GenSeq<B> _patch$1, int _replaced$1) {
/* 67 */       super($outer);
/* 67 */       GenSeqViewLike.Patched.class.$init$((GenSeqViewLike.Patched)this);
/*    */     }
/*    */   }
/*    */   
/*    */   public class StreamViewLike$$anon$12 extends AbstractTransformed<B> implements Prepended<B> {
/*    */     private final B fst;
/*    */     
/*    */     public Iterator<B> iterator() {
/* 69 */       return GenSeqViewLike.Prepended.class.iterator((GenSeqViewLike.Prepended)this);
/*    */     }
/*    */     
/*    */     public int length() {
/* 69 */       return GenSeqViewLike.Prepended.class.length((GenSeqViewLike.Prepended)this);
/*    */     }
/*    */     
/*    */     public B apply(int idx) {
/* 69 */       return (B)GenSeqViewLike.Prepended.class.apply((GenSeqViewLike.Prepended)this, idx);
/*    */     }
/*    */     
/*    */     public final String viewIdentifier() {
/* 69 */       return GenSeqViewLike.Prepended.class.viewIdentifier((GenSeqViewLike.Prepended)this);
/*    */     }
/*    */     
/*    */     public B fst() {
/* 69 */       return this.fst;
/*    */     }
/*    */     
/*    */     public StreamViewLike$$anon$12(StreamViewLike<A, Coll, This> $outer, Object elem$1) {
/* 69 */       super($outer);
/* 69 */       GenSeqViewLike.Prepended.class.$init$((GenSeqViewLike.Prepended)this);
/*    */     }
/*    */   }
/*    */   
/*    */   public interface Forced<B> extends SeqViewLike<A, Coll, This>.Forced<B>, Transformed<B> {}
/*    */   
/*    */   public interface Sliced extends SeqViewLike<A, Coll, This>.Sliced, Transformed<A> {}
/*    */   
/*    */   public interface Mapped<B> extends SeqViewLike<A, Coll, This>.Mapped<B>, Transformed<B> {}
/*    */   
/*    */   public interface Zipped<B> extends SeqViewLike<A, Coll, This>.Zipped<B>, Transformed<Tuple2<A, B>> {}
/*    */   
/*    */   public interface Patched<B> extends SeqViewLike<A, Coll, This>.Patched<B>, Transformed<B> {}
/*    */   
/*    */   public interface Appended<B> extends SeqViewLike<A, Coll, This>.Appended<B>, Transformed<B> {}
/*    */   
/*    */   public interface Filtered extends SeqViewLike<A, Coll, This>.Filtered, Transformed<A> {}
/*    */   
/*    */   public interface Reversed extends SeqViewLike<A, Coll, This>.Reversed, Transformed<A> {}
/*    */   
/*    */   public interface EmptyView extends Transformed<Nothing$>, SeqViewLike<A, Coll, This>.EmptyView {}
/*    */   
/*    */   public interface ZippedAll<A1, B> extends SeqViewLike<A, Coll, This>.ZippedAll<A1, B>, Transformed<Tuple2<A1, B>> {}
/*    */   
/*    */   public interface Prepended<B> extends SeqViewLike<A, Coll, This>.Prepended<B>, Transformed<B> {}
/*    */   
/*    */   public interface FlatMapped<B> extends SeqViewLike<A, Coll, This>.FlatMapped<B>, Transformed<B> {}
/*    */   
/*    */   public interface TakenWhile extends SeqViewLike<A, Coll, This>.TakenWhile, Transformed<A> {}
/*    */   
/*    */   public interface Transformed<B> extends StreamView<B, Coll>, SeqViewLike<A, Coll, This>.Transformed<B> {
/*    */     String toString();
/*    */   }
/*    */   
/*    */   public interface DroppedWhile extends SeqViewLike<A, Coll, This>.DroppedWhile, Transformed<A> {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\StreamViewLike.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */