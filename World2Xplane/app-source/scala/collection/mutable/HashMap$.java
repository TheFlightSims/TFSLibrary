/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Function1;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Map;
/*     */ import scala.collection.MapLike;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.GenMapFactory;
/*     */ import scala.collection.generic.MutableMapFactory;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class HashMap$ extends MutableMapFactory<HashMap> implements Serializable {
/*     */   public static final HashMap$ MODULE$;
/*     */   
/*     */   public class HashMap$$anonfun$iterator$1 extends AbstractFunction1<DefaultEntry<A, B>, Tuple2<A, B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Tuple2<A, B> apply(DefaultEntry e) {
/*  96 */       return new Tuple2(e.key(), e.value());
/*     */     }
/*     */     
/*     */     public HashMap$$anonfun$iterator$1(HashMap $outer) {}
/*     */   }
/*     */   
/*     */   public class HashMap$$anonfun$foreach$1 extends AbstractFunction1<DefaultEntry<A, B>, C> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Function1 f$1;
/*     */     
/*     */     public final C apply(DefaultEntry e) {
/*  98 */       return (C)this.f$1.apply(new Tuple2(e.key(), e.value()));
/*     */     }
/*     */     
/*     */     public HashMap$$anonfun$foreach$1(HashMap $outer, Function1 f$1) {}
/*     */   }
/*     */   
/*     */   public class HashMap$$anon$1 extends MapLike<A, B, HashMap<A, B>>.DefaultKeySet {
/*     */     public HashMap$$anon$1(HashMap $outer) {
/* 101 */       super($outer);
/*     */     }
/*     */     
/*     */     public <C> void foreach(Function1 f) {
/* 102 */       this.$outer.foreachEntry((Function1)new HashMap$$anon$1$$anonfun$foreach$2(this, ($anon$1)f));
/*     */     }
/*     */     
/*     */     public class HashMap$$anon$1$$anonfun$foreach$2 extends AbstractFunction1<DefaultEntry<A, B>, C> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 f$2;
/*     */       
/*     */       public final C apply(DefaultEntry e) {
/* 102 */         return (C)this.f$2.apply(e.key());
/*     */       }
/*     */       
/*     */       public HashMap$$anon$1$$anonfun$foreach$2(HashMap$$anon$1 $outer, Function1 f$2) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class HashMap$$anon$2 extends MapLike<A, B, HashMap<A, B>>.DefaultValuesIterable {
/*     */     public HashMap$$anon$2(HashMap $outer) {
/* 106 */       super($outer);
/*     */     }
/*     */     
/*     */     public <C> void foreach(Function1 f) {
/* 107 */       this.$outer.foreachEntry((Function1)new HashMap$$anon$2$$anonfun$foreach$3(this, ($anon$2)f));
/*     */     }
/*     */     
/*     */     public class HashMap$$anon$2$$anonfun$foreach$3 extends AbstractFunction1<DefaultEntry<A, B>, C> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final Function1 f$3;
/*     */       
/*     */       public final C apply(DefaultEntry e) {
/* 107 */         return (C)this.f$3.apply(e.value());
/*     */       }
/*     */       
/*     */       public HashMap$$anon$2$$anonfun$foreach$3(HashMap$$anon$2 $outer, Function1 f$3) {}
/*     */     }
/*     */   }
/*     */   
/*     */   public class HashMap$$anon$3 extends AbstractIterator<A> {
/*     */     private final Iterator<DefaultEntry<A, B>> iter;
/*     */     
/*     */     public HashMap$$anon$3(HashMap<A, B> $outer) {
/* 112 */       this.iter = $outer.entriesIterator();
/*     */     }
/*     */     
/*     */     private Iterator<DefaultEntry<A, B>> iter() {
/* 112 */       return this.iter;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 113 */       return iter().hasNext();
/*     */     }
/*     */     
/*     */     public A next() {
/* 114 */       return (A)((DefaultEntry)iter().next()).key();
/*     */     }
/*     */   }
/*     */   
/*     */   public class HashMap$$anon$4 extends AbstractIterator<B> {
/*     */     private final Iterator<DefaultEntry<A, B>> iter;
/*     */     
/*     */     public HashMap$$anon$4(HashMap<A, B> $outer) {
/* 119 */       this.iter = $outer.entriesIterator();
/*     */     }
/*     */     
/*     */     private Iterator<DefaultEntry<A, B>> iter() {
/* 119 */       return this.iter;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 120 */       return iter().hasNext();
/*     */     }
/*     */     
/*     */     public B next() {
/* 121 */       return (B)((DefaultEntry)iter().next()).value();
/*     */     }
/*     */   }
/*     */   
/*     */   public class HashMap$$anonfun$writeObject$1 extends AbstractFunction1<DefaultEntry<A, B>, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectOutputStream out$1;
/*     */     
/*     */     public HashMap$$anonfun$writeObject$1(HashMap $outer, ObjectOutputStream out$1) {}
/*     */     
/*     */     public final void apply(DefaultEntry entry) {
/* 136 */       this.out$1.writeObject(entry.key());
/* 137 */       this.out$1.writeObject(entry.value());
/*     */     }
/*     */   }
/*     */   
/*     */   public class HashMap$$anonfun$readObject$1 extends AbstractFunction0<DefaultEntry<A, B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ObjectInputStream in$1;
/*     */     
/*     */     public final DefaultEntry<A, B> apply() {
/* 142 */       return this.$outer.createNewEntry((A)this.in$1.readObject(), this.in$1.readObject());
/*     */     }
/*     */     
/*     */     public HashMap$$anonfun$readObject$1(HashMap $outer, ObjectInputStream in$1) {}
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 151 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private HashMap$() {
/* 151 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A, B> CanBuildFrom<HashMap<?, ?>, Tuple2<A, B>, HashMap<A, B>> canBuildFrom() {
/* 152 */     return (CanBuildFrom<HashMap<?, ?>, Tuple2<A, B>, HashMap<A, B>>)new GenMapFactory.MapCanBuildFrom((GenMapFactory)this);
/*     */   }
/*     */   
/*     */   public <A, B> HashMap<A, B> empty() {
/* 153 */     return new HashMap<A, B>();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\HashMap$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */