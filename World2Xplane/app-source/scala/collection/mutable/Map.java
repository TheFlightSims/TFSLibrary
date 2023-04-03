/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Function2;
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenIterable;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.GenSeq;
/*    */ import scala.collection.GenSet;
/*    */ import scala.collection.GenTraversable;
/*    */ import scala.collection.GenTraversableOnce;
/*    */ import scala.collection.Iterable;
/*    */ import scala.collection.Map;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.TraversableView;
/*    */ import scala.collection.generic.GenericCompanion;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.generic.Shrinkable;
/*    */ import scala.collection.generic.Subtractable;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.mutable.ParMap;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005=daB\001\003!\003\r\t!\003\002\004\033\006\004(BA\002\005\003\035iW\017^1cY\026T!!\002\004\002\025\r|G\016\\3di&|gNC\001\b\003\025\0318-\0317b\007\001)2A\003\r#'\025\0011b\004\023(!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\t\004!E\031R\"\001\002\n\005I\021!\001C%uKJ\f'\r\\3\021\t1!b#I\005\003+\031\021a\001V;qY\026\024\004CA\f\031\031\001!Q!\007\001C\002i\021\021!Q\t\0037y\001\"\001\004\017\n\005u1!a\002(pi\"Lgn\032\t\003\031}I!\001\t\004\003\007\005s\027\020\005\002\030E\021)1\005\001b\0015\t\t!\t\005\003&MY\tS\"\001\003\n\005\005!\001#\002\t)-\005R\023BA\025\003\005\035i\025\r\035'jW\026\004B\001\005\001\027C!)A\006\001C\001[\0051A%\0338ji\022\"\022A\f\t\003\031=J!\001\r\004\003\tUs\027\016\036\005\006e\001!\teM\001\006K6\004H/_\013\002U!)Q\007\001C!g\005\0311/Z9\t\013]\002A\021\001\035\002\027]LG\017\033#fM\006,H\016\036\013\003UeBQA\017\034A\002m\n\021\001\032\t\005\031q2\022%\003\002>\r\tIa)\0368di&|g.\r\005\006\001!\t\001Q\001\021o&$\b\016R3gCVdGOV1mk\026$\"AK!\t\013ir\004\031A\021\b\013\r\023\001\022\001#\002\0075\013\007\017\005\002\021\013\032)\021A\001E\001\rN\021Qi\022\t\004\021.kU\"A%\013\005)#\021aB4f]\026\024\030nY\005\003\031&\023\021#T;uC\ndW-T1q\r\006\034Go\034:z!\t\001\002\001C\003P\013\022\005\001+\001\004=S:LGO\020\013\002\t\")!+\022C\002'\006a1-\0318Ck&dGM\022:p[V\031A\013\0312\026\003U\003R\001\023,Y=\016L!aV%\003\031\r\013gNQ;jY\0224%o\\7\021\005eSV\"A#\n\005mc&\001B\"pY2L!!X%\003\033\035+g.T1q\r\006\034Go\034:z!\021aAcX1\021\005]\001G!B\rR\005\004Q\002CA\fc\t\025\031\023K1\001\033!\021\001\002aX1\t\013I*E\021A3\026\007\031L7.F\001h!\021\001\002\001\0336\021\005]IG!B\re\005\004Q\002CA\fl\t\025\031CM1\001\033\r\021iW\t\0018\003\027]KG\017\033#fM\006,H\016^\013\004_v|8\003\0027q\003\003\001B!\035>}}:\021!/\037\b\003gbt!\001^<\016\003UT!A\036\005\002\rq\022xn\034;?\023\0059\021BA\003\007\023\t\031E!\003\002nw*\0211\t\002\t\003/u$Q!\0077C\002i\001\"aF@\005\013\rb'\031\001\016\021\tA\001AP \005\013\003\013a'\021!Q\001\n\005\005\021AC;oI\026\024H._5oO\"I!\b\034B\001B\003%\021\021\002\t\005\031qbh\020\003\004PY\022\005\021Q\002\013\007\003\037\t\t\"a\005\021\tecGP \005\t\003\013\tY\0011\001\002\002!9!(a\003A\002\005%\001bBA\fY\022\005\023\021D\001\tIAdWo\035\023fcR!\0211DA\017\033\005a\007\002CA\020\003+\001\r!!\t\002\005-4\b\003\002\007\025yzDq!!\nm\t\003\t9#A\005%[&tWo\035\023fcR!\0211DA\025\021\035\tY#a\tA\002q\f1a[3z\021\031\021D\016\"\021\0020U\021\021q\002\005\b\003gaG\021IA\033\003\035)\b\017Z1uK\022,B!a\016\002>Q1\021\021HA\"\003\013\002R!\0277}\003w\0012aFA\037\t!\ty$!\rC\002\005\005#A\001\"2#\tqh\004C\004\002,\005E\002\031\001?\t\021\005\035\023\021\007a\001\003w\tQA^1mk\026Dq!a\023m\t\003\ni%A\003%a2,8/\006\003\002P\005UC\003BA)\003/\002R!\0277}\003'\0022aFA+\t!\ty$!\023C\002\005\005\003\002CA\020\003\023\002\r!!\027\021\0131!B0a\025\t\017\005uC\016\"\021\002`\0051A%\\5okN$B!a\004\002b!9\0211FA.\001\004a\bBB\034m\t\003\n)\007\006\003\002\002\005\035\004b\002\036\002d\001\007\021\021\002\005\0071$\t%a\033\025\t\005\005\021Q\016\005\007u\005%\004\031\001@")
/*    */ public interface Map<A, B> extends Iterable<Tuple2<A, B>>, Map<A, B>, MapLike<A, B, Map<A, B>> {
/*    */   Map<A, B> empty();
/*    */   
/*    */   Map<A, B> seq();
/*    */   
/*    */   Map<A, B> withDefault(Function1<A, B> paramFunction1);
/*    */   
/*    */   Map<A, B> withDefaultValue(B paramB);
/*    */   
/*    */   public class Map$$anonfun$withDefaultValue$1 extends AbstractFunction1<A, B> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Object d$2;
/*    */     
/*    */     public final B apply(Object x) {
/* 48 */       return (B)this.d$2;
/*    */     }
/*    */     
/*    */     public Map$$anonfun$withDefaultValue$1(Map $outer, Object d$2) {}
/*    */   }
/*    */   
/*    */   public static class WithDefault<A, B> extends Map.WithDefault<A, B> implements Map<A, B> {
/*    */     private final Map<A, B> underlying;
/*    */     
/*    */     private final Function1<A, B> d;
/*    */     
/*    */     public Map<A, B> seq() {
/* 74 */       return Map$class.seq(this);
/*    */     }
/*    */     
/*    */     public Builder<Tuple2<A, B>, Map<A, B>> newBuilder() {
/* 74 */       return MapLike$class.newBuilder(this);
/*    */     }
/*    */     
/*    */     public Combiner<Tuple2<A, B>, ParMap<A, B>> parCombiner() {
/* 74 */       return MapLike$class.parCombiner(this);
/*    */     }
/*    */     
/*    */     public Option<B> put(Object key, Object value) {
/* 74 */       return MapLike$class.put(this, key, value);
/*    */     }
/*    */     
/*    */     public void update(Object key, Object value) {
/* 74 */       MapLike$class.update(this, key, value);
/*    */     }
/*    */     
/*    */     public <B1> Map<A, B1> $plus(Tuple2 elem1, Tuple2 elem2, Seq elems) {
/* 74 */       return MapLike$class.$plus(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public <B1> Map<A, B1> $plus$plus(GenTraversableOnce xs) {
/* 74 */       return MapLike$class.$plus$plus(this, xs);
/*    */     }
/*    */     
/*    */     public Option<B> remove(Object key) {
/* 74 */       return MapLike$class.remove(this, key);
/*    */     }
/*    */     
/*    */     public void clear() {
/* 74 */       MapLike$class.clear(this);
/*    */     }
/*    */     
/*    */     public B getOrElseUpdate(Object key, Function0 op) {
/* 74 */       return (B)MapLike$class.getOrElseUpdate(this, key, op);
/*    */     }
/*    */     
/*    */     public MapLike<A, B, Map<A, B>> transform(Function2 f) {
/* 74 */       return MapLike$class.transform(this, f);
/*    */     }
/*    */     
/*    */     public MapLike<A, B, Map<A, B>> retain(Function2 p) {
/* 74 */       return MapLike$class.retain(this, p);
/*    */     }
/*    */     
/*    */     public Map<A, B> clone() {
/* 74 */       return MapLike$class.clone(this);
/*    */     }
/*    */     
/*    */     public Map<A, B> result() {
/* 74 */       return MapLike$class.result(this);
/*    */     }
/*    */     
/*    */     public Map<A, B> $minus(Object elem1, Object elem2, Seq elems) {
/* 74 */       return MapLike$class.$minus(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Map<A, B> $minus$minus(GenTraversableOnce xs) {
/* 74 */       return MapLike$class.$minus$minus(this, xs);
/*    */     }
/*    */     
/*    */     public Object scala$collection$mutable$Cloneable$$super$clone() {
/* 74 */       return super.clone();
/*    */     }
/*    */     
/*    */     public Shrinkable<A> $minus$eq(Object elem1, Object elem2, Seq elems) {
/* 74 */       return Shrinkable.class.$minus$eq(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Shrinkable<A> $minus$minus$eq(TraversableOnce xs) {
/* 74 */       return Shrinkable.class.$minus$minus$eq(this, xs);
/*    */     }
/*    */     
/*    */     public void sizeHint(int size) {
/* 74 */       Builder$class.sizeHint(this, size);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll) {
/* 74 */       Builder$class.sizeHint(this, coll);
/*    */     }
/*    */     
/*    */     public void sizeHint(TraversableLike coll, int delta) {
/* 74 */       Builder$class.sizeHint(this, coll, delta);
/*    */     }
/*    */     
/*    */     public void sizeHintBounded(int size, TraversableLike boundingColl) {
/* 74 */       Builder$class.sizeHintBounded(this, size, boundingColl);
/*    */     }
/*    */     
/*    */     public <NewTo> Builder<Tuple2<A, B>, NewTo> mapResult(Function1 f) {
/* 74 */       return Builder$class.mapResult(this, f);
/*    */     }
/*    */     
/*    */     public Growable<Tuple2<A, B>> $plus$eq(Object elem1, Object elem2, Seq elems) {
/* 74 */       return Growable.class.$plus$eq(this, elem1, elem2, elems);
/*    */     }
/*    */     
/*    */     public Growable<Tuple2<A, B>> $plus$plus$eq(TraversableOnce xs) {
/* 74 */       return Growable.class.$plus$plus$eq(this, xs);
/*    */     }
/*    */     
/*    */     public GenericCompanion<Iterable> companion() {
/* 74 */       return Iterable$class.companion(this);
/*    */     }
/*    */     
/*    */     public WithDefault(Map<A, B> underlying, Function1<A, B> d) {
/* 74 */       super(underlying, d);
/* 74 */       Traversable$class.$init$(this);
/* 74 */       Iterable$class.$init$(this);
/* 74 */       Growable.class.$init$(this);
/* 74 */       Builder$class.$init$(this);
/* 74 */       Shrinkable.class.$init$(this);
/* 74 */       Cloneable$class.$init$(this);
/* 74 */       MapLike$class.$init$(this);
/* 74 */       Map$class.$init$(this);
/*    */     }
/*    */     
/*    */     public WithDefault<A, B> $plus$eq(Tuple2<A, B> kv) {
/* 75 */       this.underlying.$plus$eq(kv);
/* 75 */       return this;
/*    */     }
/*    */     
/*    */     public WithDefault<A, B> $minus$eq(Object key) {
/* 76 */       this.underlying.$minus$eq((A)key);
/* 76 */       return this;
/*    */     }
/*    */     
/*    */     public WithDefault<A, B> empty() {
/* 77 */       return new WithDefault(this.underlying.empty(), this.d);
/*    */     }
/*    */     
/*    */     public <B1> WithDefault<A, B1> updated(Object key, Object value) {
/* 78 */       return new WithDefault(this.underlying.updated((A)key, value), this.d);
/*    */     }
/*    */     
/*    */     public <B1> WithDefault<A, B1> $plus(Tuple2 kv) {
/* 79 */       return updated((A)kv._1(), (B1)kv._2());
/*    */     }
/*    */     
/*    */     public WithDefault<A, B> $minus(Object key) {
/* 80 */       return new WithDefault(this.underlying.$minus((A)key), this.d);
/*    */     }
/*    */     
/*    */     public Map<A, B> withDefault(Function1<A, B> d) {
/* 85 */       return new WithDefault(this.underlying, d);
/*    */     }
/*    */     
/*    */     public Map<A, B> withDefaultValue(Object d) {
/* 86 */       return new WithDefault(this.underlying, (Function1<A, B>)new Map$WithDefault$$anonfun$withDefaultValue$2(this, (WithDefault<A, B>)d));
/*    */     }
/*    */     
/*    */     public class Map$WithDefault$$anonfun$withDefaultValue$2 extends AbstractFunction1<A, B> implements Serializable {
/*    */       public static final long serialVersionUID = 0L;
/*    */       
/*    */       private final Object d$1;
/*    */       
/*    */       public final B apply(Object x) {
/* 86 */         return (B)this.d$1;
/*    */       }
/*    */       
/*    */       public Map$WithDefault$$anonfun$withDefaultValue$2(Map.WithDefault $outer, Object d$1) {}
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\Map.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */