/*     */ package scala.collection.convert;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Set;
/*     */ import scala.collection.concurrent.Map;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.ConcurrentMap;
/*     */ import scala.collection.mutable.Map;
/*     */ import scala.collection.mutable.Seq;
/*     */ import scala.collection.mutable.Set;
/*     */ 
/*     */ public abstract class DecorateAsJava$class {
/*     */   public static void $init$(DecorateAsJava $this) {}
/*     */   
/*     */   public static Decorators.AsJava asJavaIteratorConverter(DecorateAsJava $this, Iterator i) {
/*  74 */     return new Decorators.AsJava(Decorators$.MODULE$, (Function0<?>)new DecorateAsJava$$anonfun$asJavaIteratorConverter$1($this, i));
/*     */   }
/*     */   
/*     */   public static Decorators.AsJavaEnumeration asJavaEnumerationConverter(DecorateAsJava $this, Iterator<?> i) {
/*  92 */     return new Decorators.AsJavaEnumeration(Decorators$.MODULE$, i);
/*     */   }
/*     */   
/*     */   public static Decorators.AsJava asJavaIterableConverter(DecorateAsJava $this, Iterable i) {
/* 111 */     return new Decorators.AsJava(Decorators$.MODULE$, (Function0<?>)new DecorateAsJava$$anonfun$asJavaIterableConverter$1($this, i));
/*     */   }
/*     */   
/*     */   public static Decorators.AsJavaCollection asJavaCollectionConverter(DecorateAsJava $this, Iterable<?> i) {
/* 126 */     return new Decorators.AsJavaCollection(Decorators$.MODULE$, i);
/*     */   }
/*     */   
/*     */   public static Decorators.AsJava bufferAsJavaListConverter(DecorateAsJava $this, Buffer b) {
/* 145 */     return new Decorators.AsJava(Decorators$.MODULE$, (Function0<?>)new DecorateAsJava$$anonfun$bufferAsJavaListConverter$1($this, b));
/*     */   }
/*     */   
/*     */   public static Decorators.AsJava mutableSeqAsJavaListConverter(DecorateAsJava $this, Seq b) {
/* 164 */     return new Decorators.AsJava(Decorators$.MODULE$, (Function0<?>)new DecorateAsJava$$anonfun$mutableSeqAsJavaListConverter$1($this, b));
/*     */   }
/*     */   
/*     */   public static Decorators.AsJava seqAsJavaListConverter(DecorateAsJava $this, Seq b) {
/* 183 */     return new Decorators.AsJava(Decorators$.MODULE$, (Function0<?>)new DecorateAsJava$$anonfun$seqAsJavaListConverter$1($this, b));
/*     */   }
/*     */   
/*     */   public static Decorators.AsJava mutableSetAsJavaSetConverter(DecorateAsJava $this, Set s) {
/* 202 */     return new Decorators.AsJava(Decorators$.MODULE$, (Function0<?>)new DecorateAsJava$$anonfun$mutableSetAsJavaSetConverter$1($this, s));
/*     */   }
/*     */   
/*     */   public static Decorators.AsJava setAsJavaSetConverter(DecorateAsJava $this, Set s) {
/* 221 */     return new Decorators.AsJava(Decorators$.MODULE$, (Function0<?>)new DecorateAsJava$$anonfun$setAsJavaSetConverter$1($this, s));
/*     */   }
/*     */   
/*     */   public static Decorators.AsJava mutableMapAsJavaMapConverter(DecorateAsJava $this, Map m) {
/* 240 */     return new Decorators.AsJava(Decorators$.MODULE$, (Function0<?>)new DecorateAsJava$$anonfun$mutableMapAsJavaMapConverter$1($this, m));
/*     */   }
/*     */   
/*     */   public static Decorators.AsJavaDictionary asJavaDictionaryConverter(DecorateAsJava $this, Map<?, ?> m) {
/* 259 */     return new Decorators.AsJavaDictionary<Object, Object>(Decorators$.MODULE$, m);
/*     */   }
/*     */   
/*     */   public static Decorators.AsJava mapAsJavaMapConverter(DecorateAsJava $this, Map m) {
/* 278 */     return new Decorators.AsJava(Decorators$.MODULE$, (Function0<?>)new DecorateAsJava$$anonfun$mapAsJavaMapConverter$1($this, m));
/*     */   }
/*     */   
/*     */   public static Decorators.AsJava asJavaConcurrentMapConverter(DecorateAsJava $this, ConcurrentMap m) {
/* 298 */     return new Decorators.AsJava(Decorators$.MODULE$, (Function0<?>)new DecorateAsJava$$anonfun$asJavaConcurrentMapConverter$1($this, m));
/*     */   }
/*     */   
/*     */   public static Decorators.AsJava mapAsJavaConcurrentMapConverter(DecorateAsJava $this, Map m) {
/* 317 */     return new Decorators.AsJava(Decorators$.MODULE$, (Function0<?>)new DecorateAsJava$$anonfun$mapAsJavaConcurrentMapConverter$1($this, m));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\convert\DecorateAsJava$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */