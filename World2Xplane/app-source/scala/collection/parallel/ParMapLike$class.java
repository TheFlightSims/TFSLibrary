/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Some;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ 
/*     */ public abstract class ParMapLike$class {
/*     */   public static void $init$(ParMapLike $this) {}
/*     */   
/*     */   public static Object default(ParMapLike $this, Object key) {
/*  47 */     throw new NoSuchElementException((new StringBuilder()).append("key not found: ").append(key).toString());
/*     */   }
/*     */   
/*     */   public static Object apply(ParMapLike $this, Object key) {
/*  51 */     Option option = $this.get(key);
/*  52 */     if (option instanceof Some) {
/*  52 */       Some some = (Some)option;
/*  52 */       Object object = some.x();
/*     */     } 
/*  53 */     if (None$.MODULE$ == null) {
/*  53 */       if (option != null)
/*     */         throw new MatchError(option); 
/*  53 */     } else if (!None$.MODULE$.equals(option)) {
/*     */       throw new MatchError(option);
/*     */     } 
/*  53 */     return $this.default(key);
/*     */   }
/*     */   
/*     */   public static Object getOrElse(ParMapLike $this, Object key, Function0 default) {
/*  56 */     Option option = $this.get(key);
/*  57 */     if (option instanceof Some) {
/*  57 */       Some some = (Some)option;
/*  57 */       Object object = some.x();
/*     */     } 
/*  58 */     if (None$.MODULE$ == null) {
/*  58 */       if (option != null)
/*     */         throw new MatchError(option); 
/*  58 */     } else if (!None$.MODULE$.equals(option)) {
/*     */       throw new MatchError(option);
/*     */     } 
/*  58 */     return default.apply();
/*     */   }
/*     */   
/*     */   public static boolean contains(ParMapLike $this, Object key) {
/*  61 */     return $this.get(key).isDefined();
/*     */   }
/*     */   
/*     */   public static boolean isDefinedAt(ParMapLike $this, Object key) {
/*  63 */     return $this.contains(key);
/*     */   }
/*     */   
/*     */   public static IterableSplitter scala$collection$parallel$ParMapLike$$keysIterator(ParMapLike $this, IterableSplitter s) {
/*  66 */     return new ParMapLike$$anon$3($this, (ParMapLike)s);
/*     */   }
/*     */   
/*     */   public static IterableSplitter keysIterator(ParMapLike $this) {
/*  80 */     return scala$collection$parallel$ParMapLike$$keysIterator($this, $this.splitter());
/*     */   }
/*     */   
/*     */   public static IterableSplitter scala$collection$parallel$ParMapLike$$valuesIterator(ParMapLike $this, IterableSplitter s) {
/*  83 */     return new ParMapLike$$anon$4($this, (ParMapLike)s);
/*     */   }
/*     */   
/*     */   public static IterableSplitter valuesIterator(ParMapLike $this) {
/*  97 */     return scala$collection$parallel$ParMapLike$$valuesIterator($this, $this.splitter());
/*     */   }
/*     */   
/*     */   public static ParSet keySet(ParMapLike<K, V, Repr, Sequential> $this) {
/* 118 */     return new ParMapLike.DefaultKeySet($this);
/*     */   }
/*     */   
/*     */   public static ParIterable keys(ParMapLike $this) {
/* 120 */     return $this.keySet();
/*     */   }
/*     */   
/*     */   public static ParIterable values(ParMapLike<K, V, Repr, Sequential> $this) {
/* 122 */     return new ParMapLike.DefaultValuesIterable($this);
/*     */   }
/*     */   
/*     */   public static ParMap filterKeys(ParMapLike $this, Function1 p) {
/* 124 */     return new ParMapLike$$anon$1($this, (ParMapLike<K, V, Repr, Sequential>)p);
/*     */   }
/*     */   
/*     */   public static ParMap mapValues(ParMapLike $this, Function1 f) {
/* 136 */     return new ParMapLike$$anon$2($this, (ParMapLike<K, V, Repr, Sequential>)f);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\ParMapLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */