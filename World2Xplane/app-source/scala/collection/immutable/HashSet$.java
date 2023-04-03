/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.generic.CanBuildFrom;
/*     */ import scala.collection.generic.ImmutableSetFactory;
/*     */ 
/*     */ public final class HashSet$ extends ImmutableSetFactory<HashSet> implements Serializable {
/*     */   public static final HashSet$ MODULE$;
/*     */   
/*     */   private Object readResolve() {
/*  98 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private HashSet$() {
/*  98 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public <A> CanBuildFrom<HashSet<?>, A, HashSet<A>> canBuildFrom() {
/* 101 */     return setCanBuildFrom();
/*     */   }
/*     */   
/*     */   public <A> HashSet<A> empty() {
/* 102 */     return HashSet.EmptyHashSet$.MODULE$;
/*     */   }
/*     */   
/*     */   public <A> HashSet.HashTrieSet<A> scala$collection$immutable$HashSet$$makeHashTrieSet(int hash0, HashSet<?> elem0, int hash1, HashSet<?> elem1, int level) {
/* 108 */     int index0 = hash0 >>> level & 0x1F;
/* 109 */     int index1 = hash1 >>> level & 0x1F;
/* 111 */     int bitmap = 1 << index0 | 1 << index1;
/* 112 */     HashSet[] elems = new HashSet[2];
/* 113 */     if (index0 < index1) {
/* 114 */       elems[0] = elem0;
/* 115 */       elems[1] = elem1;
/*     */     } else {
/* 117 */       elems[0] = elem1;
/* 118 */       elems[1] = elem0;
/*     */     } 
/* 122 */     HashSet[] arrayOfHashSet1 = new HashSet[1];
/* 123 */     int i = 1 << index0;
/* 124 */     HashSet.HashTrieSet<?> child = scala$collection$immutable$HashSet$$makeHashTrieSet(hash0, elem0, hash1, elem1, level + 5);
/* 125 */     arrayOfHashSet1[0] = child;
/* 126 */     return (index0 != index1) ? new HashSet.HashTrieSet<A>(bitmap, (HashSet<A>[])elems, elem0.size() + elem1.size()) : new HashSet.HashTrieSet<A>(i, (HashSet<A>[])arrayOfHashSet1, child.size());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\HashSet$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */