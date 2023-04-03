/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.Set;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.generic.Shrinkable;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.collection.immutable.Map$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005-e\001B\001\003\001%\0211#S7nkR\f'\r\\3NCB\fE-\0319u_JT!a\001\003\002\0175,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\031!\"\005\017\024\t\001Ya$\t\t\005\0315y1$D\001\003\023\tq!AA\006BEN$(/Y2u\033\006\004\bC\001\t\022\031\001!QA\005\001C\002M\021\021!Q\t\003)a\001\"!\006\f\016\003\031I!a\006\004\003\0179{G\017[5oOB\021Q#G\005\0035\031\0211!\0218z!\t\001B\004B\003\036\001\t\0071CA\001C!\021aqdD\016\n\005\001\022!aA'baB\021QCI\005\003G\031\021AbU3sS\006d\027N_1cY\026D\001\"\n\001\003\002\004%\tBJ\001\005S6\f\007/F\001(!\021A3fD\016\016\003%R!A\013\003\002\023%lW.\036;bE2,\027B\001\021*\021!i\003A!a\001\n#q\023\001C5nCB|F%Z9\025\005=\022\004CA\0131\023\t\tdA\001\003V]&$\bbB\032-\003\003\005\raJ\001\004q\022\n\004\002C\033\001\005\003\005\013\025B\024\002\013%l\027\r\035\021\t\013]\002A\021\001\035\002\rqJg.\033;?)\tI$\b\005\003\r\001=Y\002\"B\0237\001\0049\003\"\002\037\001\t\003j\024\001B:ju\026,\022A\020\t\003+}J!\001\021\004\003\007%sG\017C\003C\001\021\0051)A\002hKR$\"\001R$\021\007U)5$\003\002G\r\t1q\n\035;j_:DQ\001S!A\002=\t1a[3z\021\025Q\005\001\"\021L\003\035I7/R7qif,\022\001\024\t\003+5K!A\024\004\003\017\t{w\016\\3b]\")\001\013\001C!#\006)\021\r\0359msR\0211D\025\005\006\021>\003\ra\004\005\006)\002!\t%V\001\tG>tG/Y5ogR\021AJ\026\005\006\021N\003\ra\004\005\0061\002!\t%W\001\fSN$UMZ5oK\022\fE\017\006\002M5\")\001j\026a\001\037!)A\f\001C!;\00611.Z=TKR,\022A\030\t\004?\002|Q\"\001\003\n\005\005$!aA*fi\")1\r\001C!I\006a1.Z=t\023R,'/\031;peV\tQ\rE\002`M>I!a\032\003\003\021%#XM]1u_JDQ!\033\001\005B)\fAa[3zgV\t1\016E\002`Y>I!!\034\003\003\021%#XM]1cY\026DC\001[8voB\021\001o]\007\002c*\021!OB\001\013C:tw\016^1uS>t\027B\001;r\005%i\027n\032:bi&|g.I\001w\003M\0027.Z=tA\002\022X\r^;s]N\004\023\n^3sC\ndWmW!^AI\fG\017[3sAQD\027M\034\021Ji\026\024\030\r^8s7\006kf&I\001y\003\025\021d\006\017\0301\021\025Q\b\001\"\021|\00391\030\r\\;fg&#XM]1u_J,\022\001 \t\004?\032\\\002\"\002@\001\t\003z\030A\002<bYV,7/\006\002\002\002A\031q\f\\\016)\013u|\027QA<\"\005\005\035\021!\0161wC2,Xm\0351!e\026$XO\0358tA%#XM]1cY\026\\&)\030\021sCRDWM\035\021uQ\006t\007%\023;fe\006$xN].C;:Bq!a\003\001\t\003\ti!\001\005ji\026\024\030\r^8s+\t\ty\001\005\003`M\006E\001#B\013\002\024=Y\022bAA\013\r\t1A+\0369mKJBq!!\007\001\t\003\nY\"\001\004u_2K7\017^\013\003\003;\001b!a\b\0020\005Ea\002BA\021\003WqA!a\t\002*5\021\021Q\005\006\004\003OA\021A\002\037s_>$h(C\001\b\023\r\tiCB\001\ba\006\0347.Y4f\023\021\t\t$a\r\003\t1K7\017\036\006\004\003[1\001bBA\034\001\021\005\023\021H\001\007kB$\027\r^3\025\013=\nY$!\020\t\r!\013)\0041\001\020\021\035\ty$!\016A\002m\tQA^1mk\026Dq!a\021\001\t\003\t)%A\005%[&tWo\035\023fcR!\021qIA%\033\005\001\001B\002%\002B\001\007q\002C\004\002N\001!\t!a\024\002\021\021\002H.^:%KF$B!a\022\002R!A\0211KA&\001\004\t\t\"\001\002lm\"9\021q\013\001\005B\005e\023!B2mK\006\024H#A\030\t\017\005u\003\001\"\021\002`\005IAO]1og\032|'/\034\013\005\003\017\n\t\007\003\005\002d\005m\003\031AA3\003\0051\007CB\013\002h=Y2$C\002\002j\031\021\021BR;oGRLwN\034\032\t\017\0055\004\001\"\021\002p\0051!/\032;bS:$B!a\022\002r!A\0211OA6\001\004\t)(A\001q!\031)\022qM\b\034\031\"9\021\021\020\001\005B\005m\024\001\003;p'R\024\030N\\4\025\005\005u\004\003BA@\003\013s1!FAA\023\r\t\031IB\001\007!J,G-\0324\n\t\005\035\025\021\022\002\007'R\024\030N\\4\013\007\005\re\001")
/*    */ public class ImmutableMapAdaptor<A, B> extends AbstractMap<A, B> implements Map<A, B>, Serializable {
/*    */   private Map<A, B> imap;
/*    */   
/*    */   public Map<A, B> imap() {
/* 27 */     return this.imap;
/*    */   }
/*    */   
/*    */   public void imap_$eq(Map<A, B> x$1) {
/* 27 */     this.imap = x$1;
/*    */   }
/*    */   
/*    */   public ImmutableMapAdaptor(Map<A, B> imap) {}
/*    */   
/*    */   public int size() {
/* 33 */     return imap().size();
/*    */   }
/*    */   
/*    */   public Option<B> get(Object key) {
/* 35 */     return imap().get(key);
/*    */   }
/*    */   
/*    */   public boolean isEmpty() {
/* 37 */     return imap().isEmpty();
/*    */   }
/*    */   
/*    */   public B apply(Object key) {
/* 39 */     return (B)imap().apply(key);
/*    */   }
/*    */   
/*    */   public boolean contains(Object key) {
/* 41 */     return imap().contains(key);
/*    */   }
/*    */   
/*    */   public boolean isDefinedAt(Object key) {
/* 43 */     return imap().isDefinedAt(key);
/*    */   }
/*    */   
/*    */   public Set<A> keySet() {
/* 45 */     return (Set<A>)imap().keySet();
/*    */   }
/*    */   
/*    */   public Iterator<A> keysIterator() {
/* 47 */     return imap().keysIterator();
/*    */   }
/*    */   
/*    */   public Iterable<A> keys() {
/* 50 */     return imap().keys();
/*    */   }
/*    */   
/*    */   public Iterator<B> valuesIterator() {
/* 52 */     return imap().valuesIterator();
/*    */   }
/*    */   
/*    */   public Iterable<B> values() {
/* 55 */     return imap().values();
/*    */   }
/*    */   
/*    */   public Iterator<Tuple2<A, B>> iterator() {
/* 57 */     return imap().iterator();
/*    */   }
/*    */   
/*    */   public List<Tuple2<A, B>> toList() {
/* 59 */     return imap().toList();
/*    */   }
/*    */   
/*    */   public void update(Object key, Object value) {
/* 61 */     imap_$eq(imap().updated(key, value));
/*    */   }
/*    */   
/*    */   public ImmutableMapAdaptor<A, B> $minus$eq(Object key) {
/* 63 */     imap_$eq((Map<A, B>)imap().$minus(key));
/* 63 */     return this;
/*    */   }
/*    */   
/*    */   public ImmutableMapAdaptor<A, B> $plus$eq(Tuple2 kv) {
/* 65 */     imap_$eq(imap().$plus(kv));
/* 65 */     return this;
/*    */   }
/*    */   
/*    */   public void clear() {
/* 67 */     imap_$eq(imap().empty());
/*    */   }
/*    */   
/*    */   public ImmutableMapAdaptor<A, B> transform(Function2 f) {
/* 69 */     imap_$eq((Map<A, B>)imap().transform(f, Map$.MODULE$.canBuildFrom()));
/* 69 */     return this;
/*    */   }
/*    */   
/*    */   public ImmutableMapAdaptor<A, B> retain(Function2 p) {
/* 72 */     imap_$eq((Map<A, B>)imap().filter((Function1)new ImmutableMapAdaptor$$anonfun$retain$1(this, (ImmutableMapAdaptor<A, B>)p)));
/* 73 */     return this;
/*    */   }
/*    */   
/*    */   public class ImmutableMapAdaptor$$anonfun$retain$1 extends AbstractFunction1<Tuple2<A, B>, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Function2 p$1;
/*    */     
/*    */     public final boolean apply(Tuple2 xy) {
/*    */       return BoxesRunTime.unboxToBoolean(this.p$1.apply(xy._1(), xy._2()));
/*    */     }
/*    */     
/*    */     public ImmutableMapAdaptor$$anonfun$retain$1(ImmutableMapAdaptor $outer, Function2 p$1) {}
/*    */   }
/*    */   
/*    */   public String toString() {
/* 76 */     return imap().toString();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ImmutableMapAdaptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */