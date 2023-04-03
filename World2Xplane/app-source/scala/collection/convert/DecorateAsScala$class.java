/*     */ package scala.collection.convert;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Dictionary;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import scala.Function0;
/*     */ 
/*     */ public abstract class DecorateAsScala$class {
/*     */   public static void $init$(DecorateAsScala $this) {}
/*     */   
/*     */   public static Decorators.AsScala asScalaIteratorConverter(DecorateAsScala $this, Iterator i) {
/*  35 */     return new Decorators.AsScala(Decorators$.MODULE$, (Function0<?>)new DecorateAsScala$$anonfun$asScalaIteratorConverter$1($this, i));
/*     */   }
/*     */   
/*     */   public static Decorators.AsScala enumerationAsScalaIteratorConverter(DecorateAsScala $this, Enumeration i) {
/*  54 */     return new Decorators.AsScala(Decorators$.MODULE$, (Function0<?>)new DecorateAsScala$$anonfun$enumerationAsScalaIteratorConverter$1($this, i));
/*     */   }
/*     */   
/*     */   public static Decorators.AsScala iterableAsScalaIterableConverter(DecorateAsScala $this, Iterable i) {
/*  73 */     return new Decorators.AsScala(Decorators$.MODULE$, (Function0<?>)new DecorateAsScala$$anonfun$iterableAsScalaIterableConverter$1($this, i));
/*     */   }
/*     */   
/*     */   public static Decorators.AsScala collectionAsScalaIterableConverter(DecorateAsScala $this, Collection i) {
/*  88 */     return new Decorators.AsScala(Decorators$.MODULE$, (Function0<?>)new DecorateAsScala$$anonfun$collectionAsScalaIterableConverter$1($this, i));
/*     */   }
/*     */   
/*     */   public static Decorators.AsScala asScalaBufferConverter(DecorateAsScala $this, List l) {
/* 107 */     return new Decorators.AsScala(Decorators$.MODULE$, (Function0<?>)new DecorateAsScala$$anonfun$asScalaBufferConverter$1($this, l));
/*     */   }
/*     */   
/*     */   public static Decorators.AsScala asScalaSetConverter(DecorateAsScala $this, Set s) {
/* 126 */     return new Decorators.AsScala(Decorators$.MODULE$, (Function0<?>)new DecorateAsScala$$anonfun$asScalaSetConverter$1($this, s));
/*     */   }
/*     */   
/*     */   public static Decorators.AsScala mapAsScalaMapConverter(DecorateAsScala $this, Map m) {
/* 143 */     return new Decorators.AsScala(Decorators$.MODULE$, (Function0<?>)new DecorateAsScala$$anonfun$mapAsScalaMapConverter$1($this, m));
/*     */   }
/*     */   
/*     */   public static Decorators.AsScala asScalaConcurrentMapConverter(DecorateAsScala $this, ConcurrentMap m) {
/* 162 */     return new Decorators.AsScala(Decorators$.MODULE$, (Function0<?>)new DecorateAsScala$$anonfun$asScalaConcurrentMapConverter$1($this, m));
/*     */   }
/*     */   
/*     */   public static Decorators.AsScala mapAsScalaConcurrentMapConverter(DecorateAsScala $this, ConcurrentMap m) {
/* 180 */     return new Decorators.AsScala(Decorators$.MODULE$, (Function0<?>)new DecorateAsScala$$anonfun$mapAsScalaConcurrentMapConverter$1($this, m));
/*     */   }
/*     */   
/*     */   public static Decorators.AsScala dictionaryAsScalaMapConverter(DecorateAsScala $this, Dictionary p) {
/* 194 */     return new Decorators.AsScala(Decorators$.MODULE$, (Function0<?>)new DecorateAsScala$$anonfun$dictionaryAsScalaMapConverter$1($this, p));
/*     */   }
/*     */   
/*     */   public static Decorators.AsScala propertiesAsScalaMapConverter(DecorateAsScala $this, Properties p) {
/* 208 */     return new Decorators.AsScala(Decorators$.MODULE$, (Function0<?>)new DecorateAsScala$$anonfun$propertiesAsScalaMapConverter$1($this, p));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\convert\DecorateAsScala$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */