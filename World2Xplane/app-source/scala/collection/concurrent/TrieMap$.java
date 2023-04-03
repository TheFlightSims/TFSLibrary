/*     */ package scala.collection.concurrent;
/*     */ 
/*     */ import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenMapFactory;
/*     */ import scala.collection.generic.MutableMapFactory;
/*     */ 
/*     */ public final class TrieMap$ extends MutableMapFactory<TrieMap> implements Serializable {
/*     */   public static final TrieMap$ MODULE$;
/*     */   
/*     */   private final AtomicReferenceFieldUpdater<INodeBase<?, ?>, MainNode<?, ?>> inodeupdater;
/*     */   
/*     */   private Object readResolve() {
/* 908 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private TrieMap$() {
/* 908 */     MODULE$ = this;
/* 909 */     this.inodeupdater = AtomicReferenceFieldUpdater.newUpdater((Class)INodeBase.class, (Class)MainNode.class, "mainnode");
/*     */   }
/*     */   
/*     */   public AtomicReferenceFieldUpdater<INodeBase<?, ?>, MainNode<?, ?>> inodeupdater() {
/* 909 */     return this.inodeupdater;
/*     */   }
/*     */   
/*     */   public <K, V> CanBuildFrom<TrieMap<?, ?>, Tuple2<K, V>, TrieMap<K, V>> canBuildFrom() {
/* 911 */     return (CanBuildFrom<TrieMap<?, ?>, Tuple2<K, V>, TrieMap<K, V>>)new GenMapFactory.MapCanBuildFrom((GenMapFactory)this);
/*     */   }
/*     */   
/*     */   public <K, V> TrieMap<K, V> empty() {
/* 913 */     return new TrieMap<K, V>();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\TrieMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */