/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.MatchError;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.Tuple4;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.LinearSeqOptimized;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.math.Ordering;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class RedBlackTree$ {
/*     */   public static final RedBlackTree$ MODULE$;
/*     */   
/*     */   private RedBlackTree$() {
/*  28 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public boolean isEmpty(RedBlackTree.Tree tree) {
/*  30 */     return (tree == null);
/*     */   }
/*     */   
/*     */   public <A> boolean contains(RedBlackTree.Tree<Object, ?> tree, Object x, Ordering ordering) {
/*  32 */     return (lookup(tree, x, ordering) != null);
/*     */   }
/*     */   
/*     */   public <A, B> Option<B> get(RedBlackTree.Tree<Object, ?> tree, Object x, Ordering ordering) {
/*     */     Some some;
/*  33 */     RedBlackTree.Tree<Object, ?> tree1 = lookup(tree, x, ordering);
/*  34 */     if (tree1 == null) {
/*  34 */       scala.None$ none$ = scala.None$.MODULE$;
/*     */     } else {
/*  35 */       some = new Some(tree1.value());
/*     */     } 
/*     */     return (Option<B>)some;
/*     */   }
/*     */   
/*     */   public <A, B> RedBlackTree.Tree<A, B> lookup(RedBlackTree.Tree<A, B> tree, Object x, Ordering ordering) {
/*     */     while (true) {
/*  40 */       int cmp = ordering.compare(x, tree.key());
/*  41 */       if (cmp < 0) {
/*  41 */         tree = tree.left();
/*     */         continue;
/*     */       } 
/*  42 */       if (cmp > 0) {
/*  42 */         tree = tree.right();
/*     */         continue;
/*     */       } 
/*  43 */       return (tree == null) ? null : tree;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int count(RedBlackTree.Tree tree) {
/*  46 */     return (tree == null) ? 0 : tree.count();
/*     */   }
/*     */   
/*     */   public <A, B, B1> RedBlackTree.Tree<A, B1> update(RedBlackTree.Tree<A, ?> tree, Object k, Object v, boolean overwrite, Ordering<A> ordering) {
/*  47 */     return blacken(upd(tree, (A)k, (B1)v, overwrite, ordering));
/*     */   }
/*     */   
/*     */   public <A, B> RedBlackTree.Tree<A, B> delete(RedBlackTree.Tree<A, B> tree, Object k, Ordering<A> ordering) {
/*  48 */     return blacken(del(tree, (A)k, ordering));
/*     */   }
/*     */   
/*     */   public <A, B> RedBlackTree.Tree<A, B> rangeImpl(RedBlackTree.Tree tree, Option from, Option until, Ordering evidence$1) {
/*     */     // Byte code:
/*     */     //   0: new scala/Tuple2
/*     */     //   3: dup
/*     */     //   4: aload_2
/*     */     //   5: aload_3
/*     */     //   6: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   9: astore #5
/*     */     //   11: aload #5
/*     */     //   13: ifnull -> 80
/*     */     //   16: aload #5
/*     */     //   18: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   21: instanceof scala/Some
/*     */     //   24: ifeq -> 80
/*     */     //   27: aload #5
/*     */     //   29: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   32: checkcast scala/Some
/*     */     //   35: astore #6
/*     */     //   37: aload #5
/*     */     //   39: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   42: instanceof scala/Some
/*     */     //   45: ifeq -> 80
/*     */     //   48: aload #5
/*     */     //   50: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   53: checkcast scala/Some
/*     */     //   56: astore #7
/*     */     //   58: aload_0
/*     */     //   59: aload_1
/*     */     //   60: aload #6
/*     */     //   62: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   65: aload #7
/*     */     //   67: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   70: aload #4
/*     */     //   72: invokevirtual range : (Lscala/collection/immutable/RedBlackTree$Tree;Ljava/lang/Object;Ljava/lang/Object;Lscala/math/Ordering;)Lscala/collection/immutable/RedBlackTree$Tree;
/*     */     //   75: astore #8
/*     */     //   77: goto -> 298
/*     */     //   80: aload #5
/*     */     //   82: ifnull -> 154
/*     */     //   85: aload #5
/*     */     //   87: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   90: instanceof scala/Some
/*     */     //   93: ifeq -> 154
/*     */     //   96: aload #5
/*     */     //   98: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   101: checkcast scala/Some
/*     */     //   104: astore #9
/*     */     //   106: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   109: aload #5
/*     */     //   111: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   114: astore #10
/*     */     //   116: dup
/*     */     //   117: ifnonnull -> 129
/*     */     //   120: pop
/*     */     //   121: aload #10
/*     */     //   123: ifnull -> 137
/*     */     //   126: goto -> 154
/*     */     //   129: aload #10
/*     */     //   131: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   134: ifeq -> 154
/*     */     //   137: aload_0
/*     */     //   138: aload_1
/*     */     //   139: aload #9
/*     */     //   141: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   144: aload #4
/*     */     //   146: invokevirtual from : (Lscala/collection/immutable/RedBlackTree$Tree;Ljava/lang/Object;Lscala/math/Ordering;)Lscala/collection/immutable/RedBlackTree$Tree;
/*     */     //   149: astore #8
/*     */     //   151: goto -> 298
/*     */     //   154: aload #5
/*     */     //   156: ifnull -> 228
/*     */     //   159: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   162: aload #5
/*     */     //   164: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   167: astore #11
/*     */     //   169: dup
/*     */     //   170: ifnonnull -> 182
/*     */     //   173: pop
/*     */     //   174: aload #11
/*     */     //   176: ifnull -> 190
/*     */     //   179: goto -> 228
/*     */     //   182: aload #11
/*     */     //   184: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   187: ifeq -> 228
/*     */     //   190: aload #5
/*     */     //   192: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   195: instanceof scala/Some
/*     */     //   198: ifeq -> 228
/*     */     //   201: aload #5
/*     */     //   203: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   206: checkcast scala/Some
/*     */     //   209: astore #12
/*     */     //   211: aload_0
/*     */     //   212: aload_1
/*     */     //   213: aload #12
/*     */     //   215: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   218: aload #4
/*     */     //   220: invokevirtual until : (Lscala/collection/immutable/RedBlackTree$Tree;Ljava/lang/Object;Lscala/math/Ordering;)Lscala/collection/immutable/RedBlackTree$Tree;
/*     */     //   223: astore #8
/*     */     //   225: goto -> 298
/*     */     //   228: aload #5
/*     */     //   230: ifnull -> 301
/*     */     //   233: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   236: aload #5
/*     */     //   238: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   241: astore #13
/*     */     //   243: dup
/*     */     //   244: ifnonnull -> 256
/*     */     //   247: pop
/*     */     //   248: aload #13
/*     */     //   250: ifnull -> 264
/*     */     //   253: goto -> 301
/*     */     //   256: aload #13
/*     */     //   258: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   261: ifeq -> 301
/*     */     //   264: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   267: aload #5
/*     */     //   269: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   272: astore #14
/*     */     //   274: dup
/*     */     //   275: ifnonnull -> 287
/*     */     //   278: pop
/*     */     //   279: aload #14
/*     */     //   281: ifnull -> 295
/*     */     //   284: goto -> 301
/*     */     //   287: aload #14
/*     */     //   289: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   292: ifeq -> 301
/*     */     //   295: aload_1
/*     */     //   296: astore #8
/*     */     //   298: aload #8
/*     */     //   300: areturn
/*     */     //   301: new scala/MatchError
/*     */     //   304: dup
/*     */     //   305: aload #5
/*     */     //   307: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   310: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #49	-> 0
/*     */     //   #50	-> 18
/*     */     //   #49	-> 27
/*     */     //   #50	-> 29
/*     */     //   #49	-> 37
/*     */     //   #50	-> 39
/*     */     //   #49	-> 48
/*     */     //   #50	-> 50
/*     */     //   #49	-> 60
/*     */     //   #50	-> 62
/*     */     //   #49	-> 65
/*     */     //   #50	-> 67
/*     */     //   #49	-> 80
/*     */     //   #51	-> 87
/*     */     //   #49	-> 96
/*     */     //   #51	-> 98
/*     */     //   #49	-> 109
/*     */     //   #51	-> 111
/*     */     //   #49	-> 139
/*     */     //   #51	-> 141
/*     */     //   #49	-> 154
/*     */     //   #52	-> 159
/*     */     //   #49	-> 162
/*     */     //   #52	-> 164
/*     */     //   #49	-> 190
/*     */     //   #52	-> 192
/*     */     //   #49	-> 201
/*     */     //   #52	-> 203
/*     */     //   #49	-> 213
/*     */     //   #52	-> 215
/*     */     //   #49	-> 228
/*     */     //   #53	-> 233
/*     */     //   #49	-> 236
/*     */     //   #53	-> 238
/*     */     //   #49	-> 267
/*     */     //   #53	-> 269
/*     */     //   #49	-> 298
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	311	0	this	Lscala/collection/immutable/RedBlackTree$;
/*     */     //   0	311	1	tree	Lscala/collection/immutable/RedBlackTree$Tree;
/*     */     //   0	311	2	from	Lscala/Option;
/*     */     //   0	311	3	until	Lscala/Option;
/*     */     //   0	311	4	evidence$1	Lscala/math/Ordering;
/*     */   }
/*     */   
/*     */   public <A, B> RedBlackTree.Tree<A, B> range(RedBlackTree.Tree<A, B> tree, Object from, Object until, Ordering<A> evidence$2) {
/*  55 */     return blacken(doRange(tree, (A)from, (A)until, evidence$2));
/*     */   }
/*     */   
/*     */   public <A, B> RedBlackTree.Tree<A, B> from(RedBlackTree.Tree<A, B> tree, Object from, Ordering<A> evidence$3) {
/*  56 */     return blacken(doFrom(tree, (A)from, evidence$3));
/*     */   }
/*     */   
/*     */   public <A, B> RedBlackTree.Tree<A, B> to(RedBlackTree.Tree<A, B> tree, Object to, Ordering<A> evidence$4) {
/*  57 */     return blacken(doTo(tree, (A)to, evidence$4));
/*     */   }
/*     */   
/*     */   public <A, B> RedBlackTree.Tree<A, B> until(RedBlackTree.Tree<A, B> tree, Object key, Ordering<A> evidence$5) {
/*  58 */     return blacken(doUntil(tree, (A)key, evidence$5));
/*     */   }
/*     */   
/*     */   public <A, B> RedBlackTree.Tree<A, B> drop(RedBlackTree.Tree<A, B> tree, int n, Ordering evidence$6) {
/*  60 */     return blacken(doDrop(tree, n));
/*     */   }
/*     */   
/*     */   public <A, B> RedBlackTree.Tree<A, B> take(RedBlackTree.Tree<A, B> tree, int n, Ordering evidence$7) {
/*  61 */     return blacken(doTake(tree, n));
/*     */   }
/*     */   
/*     */   public <A, B> RedBlackTree.Tree<A, B> slice(RedBlackTree.Tree<A, B> tree, int from, int until, Ordering evidence$8) {
/*  62 */     return blacken(doSlice(tree, from, until));
/*     */   }
/*     */   
/*     */   public <A, B> RedBlackTree.Tree<A, B> smallest(RedBlackTree.Tree<A, B> tree) {
/*  65 */     if (tree == null)
/*  65 */       throw new NoSuchElementException("empty map"); 
/*  66 */     RedBlackTree.Tree<A, B> result = tree;
/*  67 */     for (; result.left() != null; result = result.left());
/*  68 */     return result;
/*     */   }
/*     */   
/*     */   public <A, B> RedBlackTree.Tree<A, B> greatest(RedBlackTree.Tree<A, B> tree) {
/*  71 */     if (tree == null)
/*  71 */       throw new NoSuchElementException("empty map"); 
/*  72 */     RedBlackTree.Tree<A, B> result = tree;
/*  73 */     for (; result.right() != null; result = result.right());
/*  74 */     return result;
/*     */   }
/*     */   
/*     */   public <A, B, U> void foreach(RedBlackTree.Tree<?, ?> tree, Function1<Tuple2<?, ?>, ?> f) {
/*  77 */     while (tree != null) {
/*  78 */       if (tree.left() != null)
/*  78 */         foreach(tree.left(), f); 
/*  79 */       f.apply(new Tuple2(tree.key(), tree.value()));
/*  80 */       if (tree.right() != null)
/*  80 */         tree = tree.right(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A, U> void foreachKey(RedBlackTree.Tree<?, ?> tree, Function1<?, ?> f) {
/*  82 */     while (tree != null) {
/*  83 */       if (tree.left() != null)
/*  83 */         foreachKey(tree.left(), f); 
/*  84 */       f.apply(tree.key());
/*  85 */       if (tree.right() != null)
/*  85 */         tree = tree.right(); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public <A, B> Iterator<Tuple2<A, B>> iterator(RedBlackTree.Tree<A, B> tree) {
/*  88 */     return new RedBlackTree.EntriesIterator<A, B>(tree);
/*     */   }
/*     */   
/*     */   public <A, _> Iterator<A> keysIterator(RedBlackTree.Tree<A, ?> tree) {
/*  89 */     return new RedBlackTree.KeysIterator<A, Object>(tree);
/*     */   }
/*     */   
/*     */   public <_, B> Iterator<B> valuesIterator(RedBlackTree.Tree<?, B> tree) {
/*  90 */     return new RedBlackTree.ValuesIterator<Object, B>(tree);
/*     */   }
/*     */   
/*     */   public <A, B> RedBlackTree.Tree<A, B> nth(RedBlackTree.Tree<?, ?> tree, int n) {
/*     */     while (true) {
/*  94 */       int count = count(tree.left());
/*  95 */       if (n < count) {
/*  95 */         tree = tree.left();
/*     */         continue;
/*     */       } 
/*  96 */       if (n > count) {
/*  96 */         n = n - count - 1;
/*  96 */         tree = tree.right();
/*     */         continue;
/*     */       } 
/*  97 */       return (RedBlackTree.Tree)tree;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isBlack(RedBlackTree.Tree<?, ?> tree) {
/* 100 */     return (tree == null || scala$collection$immutable$RedBlackTree$$isBlackTree(tree));
/*     */   }
/*     */   
/*     */   private boolean isRedTree(RedBlackTree.Tree tree) {
/* 102 */     return tree instanceof RedBlackTree.RedTree;
/*     */   }
/*     */   
/*     */   public boolean scala$collection$immutable$RedBlackTree$$isBlackTree(RedBlackTree.Tree tree) {
/* 103 */     return tree instanceof RedBlackTree.BlackTree;
/*     */   }
/*     */   
/*     */   private <A, B> RedBlackTree.Tree<A, B> blacken(RedBlackTree.Tree<A, B> t) {
/* 105 */     return (t == null) ? null : t.black();
/*     */   }
/*     */   
/*     */   private <A, B> RedBlackTree.Tree<A, B> mkTree(boolean isBlack, Object k, Object v, RedBlackTree.Tree<A, B> l, RedBlackTree.Tree<A, B> r) {
/* 108 */     RedBlackTree.BlackTree$ blackTree$ = RedBlackTree.BlackTree$.MODULE$;
/* 108 */     RedBlackTree.RedTree$ redTree$ = RedBlackTree.RedTree$.MODULE$;
/* 108 */     return isBlack ? new RedBlackTree.BlackTree<A, B>((A)k, (B)v, l, r) : new RedBlackTree.RedTree<A, B>((A)k, (B)v, l, r);
/*     */   }
/*     */   
/*     */   public <A, B, B1> RedBlackTree.Tree<A, B1> scala$collection$immutable$RedBlackTree$$balanceLeft(boolean isBlack, Object z, Object zv, RedBlackTree.Tree<?, ?> l, RedBlackTree.Tree<Object, Object> d) {
/* 112 */     RedBlackTree.Tree<Object, Object> tree2 = l.left().right(), tree1 = l.left().left();
/* 112 */     Object object2 = l.left().value(), object1 = l.left().key();
/* 112 */     RedBlackTree.BlackTree$ blackTree$1 = RedBlackTree.BlackTree$.MODULE$;
/* 112 */     RedBlackTree.Tree<?, ?> tree3 = l.right();
/* 112 */     RedBlackTree.BlackTree$ blackTree$2 = RedBlackTree.BlackTree$.MODULE$;
/* 112 */     RedBlackTree.BlackTree<Object, Object> blackTree2 = new RedBlackTree.BlackTree<Object, Object>(z, zv, (RedBlackTree.Tree)tree3, d), blackTree1 = new RedBlackTree.BlackTree<Object, Object>(object1, object2, tree1, tree2);
/* 112 */     Object object4 = l.value(), object3 = l.key();
/* 112 */     RedBlackTree.RedTree$ redTree$1 = RedBlackTree.RedTree$.MODULE$;
/* 114 */     RedBlackTree.Tree<Object, Object> tree5 = l.right().left();
/* 114 */     RedBlackTree.Tree<?, ?> tree4 = l.left();
/* 114 */     Object object6 = l.value(), object5 = l.key();
/* 114 */     RedBlackTree.BlackTree$ blackTree$3 = RedBlackTree.BlackTree$.MODULE$;
/* 114 */     RedBlackTree.Tree<Object, Object> tree6 = l.right().right();
/* 114 */     RedBlackTree.BlackTree$ blackTree$4 = RedBlackTree.BlackTree$.MODULE$;
/* 114 */     RedBlackTree.BlackTree<Object, Object> blackTree4 = new RedBlackTree.BlackTree<Object, Object>(z, zv, tree6, d), blackTree3 = new RedBlackTree.BlackTree<Object, Object>(object5, object6, (RedBlackTree.Tree)tree4, tree5);
/* 114 */     Object object8 = l.right().value(), object7 = l.right().key();
/* 114 */     RedBlackTree.RedTree$ redTree$2 = RedBlackTree.RedTree$.MODULE$;
/* 114 */     return (isRedTree(l) && isRedTree(l.left())) ? new RedBlackTree.RedTree<A, B1>((A)object3, (B1)object4, (RedBlackTree.Tree)blackTree1, (RedBlackTree.Tree)blackTree2) : ((isRedTree(l) && isRedTree(l.right())) ? new RedBlackTree.RedTree<A, B1>((A)object7, (B1)object8, (RedBlackTree.Tree)blackTree3, (RedBlackTree.Tree)blackTree4) : 
/*     */       
/* 116 */       mkTree(isBlack, (A)z, (B1)zv, (RedBlackTree.Tree)l, (RedBlackTree.Tree)d));
/*     */   }
/*     */   
/*     */   public <A, B, B1> RedBlackTree.Tree<A, B1> scala$collection$immutable$RedBlackTree$$balanceRight(boolean isBlack, Object x, Object xv, RedBlackTree.Tree<Object, Object> a, RedBlackTree.Tree<?, ?> r) {
/* 120 */     RedBlackTree.Tree<Object, Object> tree1 = r.left().left();
/* 120 */     RedBlackTree.BlackTree$ blackTree$1 = RedBlackTree.BlackTree$.MODULE$;
/* 120 */     RedBlackTree.Tree<?, ?> tree3 = r.right();
/* 120 */     RedBlackTree.Tree<Object, Object> tree2 = r.left().right();
/* 120 */     Object object2 = r.value(), object1 = r.key();
/* 120 */     RedBlackTree.BlackTree$ blackTree$2 = RedBlackTree.BlackTree$.MODULE$;
/* 120 */     RedBlackTree.BlackTree<Object, Object> blackTree2 = new RedBlackTree.BlackTree<Object, Object>(object1, object2, tree2, (RedBlackTree.Tree)tree3), blackTree1 = new RedBlackTree.BlackTree<Object, Object>(x, xv, a, tree1);
/* 120 */     Object object4 = r.left().value(), object3 = r.left().key();
/* 120 */     RedBlackTree.RedTree$ redTree$1 = RedBlackTree.RedTree$.MODULE$;
/* 122 */     RedBlackTree.Tree<?, ?> tree4 = r.left();
/* 122 */     RedBlackTree.BlackTree$ blackTree$3 = RedBlackTree.BlackTree$.MODULE$;
/* 122 */     RedBlackTree.Tree<Object, Object> tree6 = r.right().right(), tree5 = r.right().left();
/* 122 */     Object object6 = r.right().value(), object5 = r.right().key();
/* 122 */     RedBlackTree.BlackTree$ blackTree$4 = RedBlackTree.BlackTree$.MODULE$;
/* 122 */     RedBlackTree.BlackTree<Object, Object> blackTree4 = new RedBlackTree.BlackTree<Object, Object>(object5, object6, tree5, tree6), blackTree3 = new RedBlackTree.BlackTree<Object, Object>(x, xv, a, (RedBlackTree.Tree)tree4);
/* 122 */     Object object8 = r.value(), object7 = r.key();
/* 122 */     RedBlackTree.RedTree$ redTree$2 = RedBlackTree.RedTree$.MODULE$;
/* 122 */     return (isRedTree(r) && isRedTree(r.left())) ? new RedBlackTree.RedTree<A, B1>((A)object3, (B1)object4, (RedBlackTree.Tree)blackTree1, (RedBlackTree.Tree)blackTree2) : ((isRedTree(r) && isRedTree(r.right())) ? new RedBlackTree.RedTree<A, B1>((A)object7, (B1)object8, (RedBlackTree.Tree)blackTree3, (RedBlackTree.Tree)blackTree4) : 
/*     */       
/* 124 */       mkTree(isBlack, (A)x, (B1)xv, (RedBlackTree.Tree)a, (RedBlackTree.Tree)r));
/*     */   }
/*     */   
/*     */   private <A, B, B1> RedBlackTree.Tree<A, B1> upd(RedBlackTree.Tree<?, ?> tree, Object k, Object v, boolean overwrite, Ordering<A> ordering) {
/* 127 */     RedBlackTree.RedTree$ redTree$ = RedBlackTree.RedTree$.MODULE$;
/* 129 */     int cmp = ordering.compare(k, tree.key());
/* 132 */     if (!overwrite) {
/* 132 */       Object object = tree.key();
/* 132 */       if ((k == object) ? true : ((k == null) ? false : ((k instanceof Number) ? BoxesRunTime.equalsNumObject((Number)k, object) : ((k instanceof Character) ? BoxesRunTime.equalsCharObject((Character)k, object) : k.equals(object)))));
/*     */     } 
/* 132 */     return (tree == null) ? new RedBlackTree.RedTree<A, B1>((A)k, (B1)v, null, null) : ((cmp < 0) ? scala$collection$immutable$RedBlackTree$$balanceLeft(scala$collection$immutable$RedBlackTree$$isBlackTree(tree), (A)tree.key(), tree.value(), upd((RedBlackTree.Tree)tree.left(), (A)k, (B1)v, overwrite, ordering), (RedBlackTree.Tree)tree.right()) : ((cmp > 0) ? scala$collection$immutable$RedBlackTree$$balanceRight(scala$collection$immutable$RedBlackTree$$isBlackTree(tree), (A)tree.key(), tree.value(), (RedBlackTree.Tree)tree.left(), upd((RedBlackTree.Tree)tree.right(), (A)k, (B1)v, overwrite, ordering)) : mkTree(scala$collection$immutable$RedBlackTree$$isBlackTree(tree), (A)k, (B1)v, (RedBlackTree.Tree)tree.left(), (RedBlackTree.Tree)tree.right())));
/*     */   }
/*     */   
/*     */   private <A, B, B1> RedBlackTree.Tree<A, B1> updNth(RedBlackTree.Tree<?, ?> tree, int idx, Object k, Object v, boolean overwrite) {
/* 136 */     RedBlackTree.RedTree$ redTree$ = RedBlackTree.RedTree$.MODULE$;
/* 138 */     int rank = count(tree.left()) + 1;
/* 139 */     return (tree == null) ? new RedBlackTree.RedTree<A, B1>((A)k, (B1)v, null, null) : ((idx < rank) ? scala$collection$immutable$RedBlackTree$$balanceLeft(scala$collection$immutable$RedBlackTree$$isBlackTree(tree), (A)tree.key(), tree.value(), updNth((RedBlackTree.Tree)tree.left(), idx, (A)k, (B1)v, overwrite), (RedBlackTree.Tree)tree.right()) : (
/* 140 */       (idx > rank) ? scala$collection$immutable$RedBlackTree$$balanceRight(scala$collection$immutable$RedBlackTree$$isBlackTree(tree), (A)tree.key(), tree.value(), (RedBlackTree.Tree)tree.left(), updNth((RedBlackTree.Tree)tree.right(), idx - rank, (A)k, (B1)v, overwrite)) : (
/* 141 */       overwrite ? mkTree(scala$collection$immutable$RedBlackTree$$isBlackTree(tree), (A)k, (B1)v, (RedBlackTree.Tree)tree.left(), (RedBlackTree.Tree)tree.right()) : 
/* 142 */       (RedBlackTree.Tree)tree)));
/*     */   }
/*     */   
/*     */   private <A, B> RedBlackTree.Tree<A, B> del(RedBlackTree.Tree tree, Object k, Ordering ordering) {
/* 219 */     int cmp = ordering.compare(k, tree.key());
/* 220 */     return (tree == null) ? null : ((cmp < 0) ? delLeft$1(tree, k, ordering) : (
/* 221 */       (cmp > 0) ? delRight$1(tree, k, ordering) : 
/* 222 */       append$1(tree.left(), tree.right())));
/*     */   }
/*     */   
/*     */   private final RedBlackTree.Tree balance$1(Object x, Object xv, RedBlackTree.Tree<?, ?> tl, RedBlackTree.Tree<?, ?> tr) {
/*     */     RedBlackTree.Tree<?, ?> tree2 = tr.black(), tree1 = tl.black();
/*     */     RedBlackTree.RedTree$ redTree$1 = RedBlackTree.RedTree$.MODULE$;
/*     */     RedBlackTree.Tree<?, ?> tree3 = tl.right();
/*     */     RedBlackTree.BlackTree$ blackTree$1 = RedBlackTree.BlackTree$.MODULE$;
/*     */     RedBlackTree.BlackTree<Object, Object> blackTree1 = new RedBlackTree.BlackTree<Object, Object>(x, xv, (RedBlackTree.Tree)tree3, (RedBlackTree.Tree)tr);
/*     */     RedBlackTree.Tree<Object, Object> tree4 = tl.left().black();
/*     */     Object object2 = tl.value(), object1 = tl.key();
/*     */     RedBlackTree.RedTree$ redTree$2 = RedBlackTree.RedTree$.MODULE$;
/*     */     RedBlackTree.Tree<Object, Object> tree6 = tl.right().left();
/*     */     RedBlackTree.Tree<?, ?> tree5 = tl.left();
/*     */     Object object4 = tl.value(), object3 = tl.key();
/*     */     RedBlackTree.BlackTree$ blackTree$2 = RedBlackTree.BlackTree$.MODULE$;
/*     */     RedBlackTree.Tree<Object, Object> tree7 = tl.right().right();
/*     */     RedBlackTree.BlackTree$ blackTree$3 = RedBlackTree.BlackTree$.MODULE$;
/*     */     RedBlackTree.BlackTree<Object, Object> blackTree3 = new RedBlackTree.BlackTree<Object, Object>(x, xv, tree7, (RedBlackTree.Tree)tr), blackTree2 = new RedBlackTree.BlackTree<Object, Object>(object3, object4, (RedBlackTree.Tree)tree5, tree6);
/*     */     Object object6 = tl.right().value(), object5 = tl.right().key();
/*     */     RedBlackTree.RedTree$ redTree$3 = RedBlackTree.RedTree$.MODULE$;
/*     */     RedBlackTree.BlackTree$ blackTree$4 = RedBlackTree.BlackTree$.MODULE$;
/*     */     RedBlackTree.Tree<?, ?> tree8 = tr.left();
/*     */     RedBlackTree.BlackTree$ blackTree$5 = RedBlackTree.BlackTree$.MODULE$;
/*     */     RedBlackTree.Tree<Object, Object> tree9 = tr.right().black();
/*     */     RedBlackTree.BlackTree<Object, Object> blackTree4 = new RedBlackTree.BlackTree<Object, Object>(x, xv, (RedBlackTree.Tree)tl, (RedBlackTree.Tree)tree8);
/*     */     Object object8 = tr.value(), object7 = tr.key();
/*     */     RedBlackTree.RedTree$ redTree$4 = RedBlackTree.RedTree$.MODULE$;
/*     */     RedBlackTree.Tree<Object, Object> tree10 = tr.left().left();
/*     */     RedBlackTree.BlackTree$ blackTree$6 = RedBlackTree.BlackTree$.MODULE$;
/*     */     RedBlackTree.Tree<?, ?> tree12 = tr.right();
/*     */     RedBlackTree.Tree<Object, Object> tree11 = tr.left().right();
/*     */     Object object10 = tr.value(), object9 = tr.key();
/*     */     RedBlackTree.BlackTree$ blackTree$7 = RedBlackTree.BlackTree$.MODULE$;
/*     */     RedBlackTree.BlackTree<Object, Object> blackTree6 = new RedBlackTree.BlackTree<Object, Object>(object9, object10, tree11, (RedBlackTree.Tree)tree12), blackTree5 = new RedBlackTree.BlackTree<Object, Object>(x, xv, (RedBlackTree.Tree)tl, tree10);
/*     */     Object object12 = tr.left().value(), object11 = tr.left().key();
/*     */     RedBlackTree.RedTree$ redTree$5 = RedBlackTree.RedTree$.MODULE$;
/*     */     RedBlackTree.BlackTree$ blackTree$8 = RedBlackTree.BlackTree$.MODULE$;
/*     */     RedBlackTree.BlackTree$ blackTree$9 = RedBlackTree.BlackTree$.MODULE$;
/*     */     return isRedTree(tl) ? (isRedTree(tr) ? new RedBlackTree.RedTree<Object, Object>(x, xv, (RedBlackTree.Tree)tree1, (RedBlackTree.Tree)tree2) : (isRedTree(tl.left()) ? new RedBlackTree.RedTree<Object, Object>(object1, object2, tree4, blackTree1) : (isRedTree(tl.right()) ? new RedBlackTree.RedTree<Object, Object>(object5, object6, blackTree2, blackTree3) : new RedBlackTree.BlackTree<Object, Object>(x, xv, (RedBlackTree.Tree)tl, (RedBlackTree.Tree)tr)))) : (isRedTree(tr) ? (isRedTree(tr.right()) ? new RedBlackTree.RedTree<Object, Object>(object7, object8, blackTree4, tree9) : (isRedTree(tr.left()) ? new RedBlackTree.RedTree<Object, Object>(object11, object12, blackTree5, blackTree6) : new RedBlackTree.BlackTree<Object, Object>(x, xv, (RedBlackTree.Tree)tl, (RedBlackTree.Tree)tr))) : new RedBlackTree.BlackTree<Object, Object>(x, xv, (RedBlackTree.Tree)tl, (RedBlackTree.Tree)tr));
/*     */   }
/*     */   
/*     */   private final RedBlackTree.Tree subl$1(RedBlackTree.Tree t) {
/*     */     if (t instanceof RedBlackTree.BlackTree)
/*     */       return t.red(); 
/*     */     throw scala.sys.package$.MODULE$.error((new StringBuilder()).append("Defect: invariance violation; expected black, got ").append(t).toString());
/*     */   }
/*     */   
/*     */   private final RedBlackTree.Tree balLeft$1(Object x, Object xv, RedBlackTree.Tree<?, ?> tl, RedBlackTree.Tree<?, ?> tr) {
/*     */     RedBlackTree.Tree<?, ?> tree = tl.black();
/*     */     RedBlackTree.RedTree$ redTree$ = RedBlackTree.RedTree$.MODULE$;
/*     */     if (scala$collection$immutable$RedBlackTree$$isBlackTree(tr)) {
/*     */     
/*     */     } else {
/*     */       if (isRedTree(tr) && scala$collection$immutable$RedBlackTree$$isBlackTree(tr.left())) {
/*     */         RedBlackTree.Tree<Object, Object> tree1 = tr.left().left();
/*     */         RedBlackTree.BlackTree$ blackTree$ = RedBlackTree.BlackTree$.MODULE$;
/*     */         RedBlackTree.Tree<Object, Object> tree2 = balance$1(tr.key(), tr.value(), tr.left().right(), subl$1(tr.right()));
/*     */         RedBlackTree.BlackTree<Object, Object> blackTree = new RedBlackTree.BlackTree<Object, Object>(x, xv, (RedBlackTree.Tree)tl, tree1);
/*     */         Object object2 = tr.left().value(), object1 = tr.left().key();
/*     */         RedBlackTree.RedTree$ redTree$1 = RedBlackTree.RedTree$.MODULE$;
/*     */         return new RedBlackTree.RedTree<Object, Object>(object1, object2, blackTree, tree2);
/*     */       } 
/*     */       throw scala.sys.package$.MODULE$.error("Defect: invariance violation");
/*     */     } 
/*     */     return isRedTree(tl) ? new RedBlackTree.RedTree<Object, Object>(x, xv, (RedBlackTree.Tree)tree, (RedBlackTree.Tree)tr) : (RedBlackTree.Tree)"JD-Core does not support Kotlin";
/*     */   }
/*     */   
/*     */   private final RedBlackTree.Tree balRight$1(Object x, Object xv, RedBlackTree.Tree<?, ?> tl, RedBlackTree.Tree<?, ?> tr) {
/*     */     RedBlackTree.Tree<?, ?> tree = tr.black();
/*     */     RedBlackTree.RedTree$ redTree$ = RedBlackTree.RedTree$.MODULE$;
/*     */     if (scala$collection$immutable$RedBlackTree$$isBlackTree(tl)) {
/*     */     
/*     */     } else {
/*     */       if (isRedTree(tl) && scala$collection$immutable$RedBlackTree$$isBlackTree(tl.right())) {
/*     */         RedBlackTree.Tree<Object, Object> tree1 = tl.right().right();
/*     */         RedBlackTree.BlackTree$ blackTree$ = RedBlackTree.BlackTree$.MODULE$;
/*     */         RedBlackTree.BlackTree<Object, Object> blackTree = new RedBlackTree.BlackTree<Object, Object>(x, xv, tree1, (RedBlackTree.Tree)tr);
/*     */         RedBlackTree.Tree<Object, Object> tree2 = balance$1(tl.key(), tl.value(), subl$1(tl.left()), tl.right().left());
/*     */         Object object2 = tl.right().value(), object1 = tl.right().key();
/*     */         RedBlackTree.RedTree$ redTree$1 = RedBlackTree.RedTree$.MODULE$;
/*     */         return new RedBlackTree.RedTree<Object, Object>(object1, object2, tree2, blackTree);
/*     */       } 
/*     */       throw scala.sys.package$.MODULE$.error("Defect: invariance violation");
/*     */     } 
/*     */     return isRedTree(tr) ? new RedBlackTree.RedTree<Object, Object>(x, xv, (RedBlackTree.Tree)tl, (RedBlackTree.Tree)tree) : (RedBlackTree.Tree)"JD-Core does not support Kotlin";
/*     */   }
/*     */   
/*     */   private final RedBlackTree.Tree delLeft$1(RedBlackTree.Tree<?, ?> tree$1, Object k$1, Ordering ordering$1) {
/*     */     RedBlackTree.Tree<?, ?> tree1 = tree$1.right();
/*     */     RedBlackTree.Tree<Object, ?> tree = del((RedBlackTree.Tree)tree$1.left(), k$1, ordering$1);
/*     */     Object object2 = tree$1.value(), object1 = tree$1.key();
/*     */     RedBlackTree.RedTree$ redTree$ = RedBlackTree.RedTree$.MODULE$;
/*     */     return scala$collection$immutable$RedBlackTree$$isBlackTree(tree$1.left()) ? balLeft$1(tree$1.key(), tree$1.value(), del((RedBlackTree.Tree)tree$1.left(), k$1, ordering$1), tree$1.right()) : new RedBlackTree.RedTree<Object, Object>(object1, object2, (RedBlackTree.Tree)tree, (RedBlackTree.Tree)tree1);
/*     */   }
/*     */   
/*     */   private final RedBlackTree.Tree delRight$1(RedBlackTree.Tree<?, ?> tree$1, Object k$1, Ordering ordering$1) {
/*     */     RedBlackTree.Tree<Object, ?> tree1 = del((RedBlackTree.Tree)tree$1.right(), k$1, ordering$1);
/*     */     RedBlackTree.Tree<?, ?> tree = tree$1.left();
/*     */     Object object2 = tree$1.value(), object1 = tree$1.key();
/*     */     RedBlackTree.RedTree$ redTree$ = RedBlackTree.RedTree$.MODULE$;
/*     */     return scala$collection$immutable$RedBlackTree$$isBlackTree(tree$1.right()) ? balRight$1(tree$1.key(), tree$1.value(), tree$1.left(), del((RedBlackTree.Tree)tree$1.right(), k$1, ordering$1)) : new RedBlackTree.RedTree<Object, Object>(object1, object2, (RedBlackTree.Tree)tree, (RedBlackTree.Tree)tree1);
/*     */   }
/*     */   
/*     */   private final RedBlackTree.Tree append$1(RedBlackTree.Tree<?, ?> tl, RedBlackTree.Tree<?, ?> tr) {
/*     */     RedBlackTree.Tree<?, ?> bc = append$1(tl.right(), tr.left());
/*     */     RedBlackTree.Tree<?, ?> tree2 = bc.left(), tree1 = tl.left();
/*     */     Object object2 = tl.value(), object1 = tl.key();
/*     */     RedBlackTree.RedTree$ redTree$1 = RedBlackTree.RedTree$.MODULE$;
/*     */     RedBlackTree.Tree<?, ?> tree4 = tr.right(), tree3 = bc.right();
/*     */     Object object4 = tr.value(), object3 = tr.key();
/*     */     RedBlackTree.RedTree$ redTree$2 = RedBlackTree.RedTree$.MODULE$;
/*     */     RedBlackTree.RedTree<Object, Object> redTree2 = new RedBlackTree.RedTree<Object, Object>(object3, object4, (RedBlackTree.Tree)tree3, (RedBlackTree.Tree)tree4), redTree1 = new RedBlackTree.RedTree<Object, Object>(object1, object2, (RedBlackTree.Tree)tree1, (RedBlackTree.Tree)tree2);
/*     */     Object object6 = bc.value(), object5 = bc.key();
/*     */     RedBlackTree.RedTree$ redTree$3 = RedBlackTree.RedTree$.MODULE$;
/*     */     RedBlackTree.Tree<?, ?> tree5 = tr.right();
/*     */     Object object8 = tr.value(), object7 = tr.key();
/*     */     RedBlackTree.RedTree$ redTree$4 = RedBlackTree.RedTree$.MODULE$;
/*     */     RedBlackTree.RedTree<Object, Object> redTree3 = new RedBlackTree.RedTree<Object, Object>(object7, object8, (RedBlackTree.Tree)bc, (RedBlackTree.Tree)tree5);
/*     */     RedBlackTree.Tree<?, ?> tree6 = tl.left();
/*     */     Object object10 = tl.value(), object9 = tl.key();
/*     */     RedBlackTree.RedTree$ redTree$5 = RedBlackTree.RedTree$.MODULE$;
/*     */     RedBlackTree.Tree<?, ?> tree11 = append$1(tl.right(), tr.left());
/*     */     RedBlackTree.Tree<?, ?> tree8 = tree11.left(), tree7 = tl.left();
/*     */     Object object12 = tl.value(), object11 = tl.key();
/*     */     RedBlackTree.BlackTree$ blackTree$1 = RedBlackTree.BlackTree$.MODULE$;
/*     */     RedBlackTree.Tree<?, ?> tree10 = tr.right(), tree9 = tree11.right();
/*     */     Object object14 = tr.value(), object13 = tr.key();
/*     */     RedBlackTree.BlackTree$ blackTree$2 = RedBlackTree.BlackTree$.MODULE$;
/*     */     RedBlackTree.BlackTree<Object, Object> blackTree2 = new RedBlackTree.BlackTree<Object, Object>(object13, object14, (RedBlackTree.Tree)tree9, (RedBlackTree.Tree)tree10), blackTree1 = new RedBlackTree.BlackTree<Object, Object>(object11, object12, (RedBlackTree.Tree)tree7, (RedBlackTree.Tree)tree8);
/*     */     Object object16 = tree11.value(), object15 = tree11.key();
/*     */     RedBlackTree.RedTree$ redTree$6 = RedBlackTree.RedTree$.MODULE$;
/*     */     RedBlackTree.Tree<?, ?> tree12 = tr.right();
/*     */     Object object18 = tr.value(), object17 = tr.key();
/*     */     RedBlackTree.BlackTree$ blackTree$3 = RedBlackTree.BlackTree$.MODULE$;
/*     */     if (isRedTree(tr)) {
/*     */       RedBlackTree.Tree<?, ?> tree13 = tr.right();
/*     */       RedBlackTree.Tree tree = append$1(tl, tr.left());
/*     */       Object object20 = tr.value(), object19 = tr.key();
/*     */       RedBlackTree.RedTree$ redTree$ = RedBlackTree.RedTree$.MODULE$;
/*     */     } else {
/*     */       if (isRedTree(tl)) {
/*     */         RedBlackTree.Tree<Object, Object> tree13 = append$1(tl.right(), tr);
/*     */         RedBlackTree.Tree<?, ?> tree = tl.left();
/*     */         Object object20 = tl.value(), object19 = tl.key();
/*     */         RedBlackTree.RedTree$ redTree$ = RedBlackTree.RedTree$.MODULE$;
/*     */         return new RedBlackTree.RedTree<Object, Object>(object19, object20, (RedBlackTree.Tree)tree, tree13);
/*     */       } 
/*     */       throw scala.sys.package$.MODULE$.error((new StringBuilder()).append("unmatched tree on append: ").append(tl).append(", ").append(tr).toString());
/*     */     } 
/*     */     return (tl == null) ? tr : ((tr == null) ? tl : ((isRedTree(tl) && isRedTree(tr)) ? (isRedTree(bc) ? new RedBlackTree.RedTree<Object, Object>(object5, object6, redTree1, redTree2) : new RedBlackTree.RedTree<Object, Object>(object9, object10, (RedBlackTree.Tree)tree6, redTree3)) : ((scala$collection$immutable$RedBlackTree$$isBlackTree(tl) && scala$collection$immutable$RedBlackTree$$isBlackTree(tr)) ? (isRedTree(tree11) ? new RedBlackTree.RedTree<Object, Object>(object15, object16, blackTree1, blackTree2) : balLeft$1(tl.key(), tl.value(), tl.left(), new RedBlackTree.BlackTree<Object, Object>(object17, object18, (RedBlackTree.Tree)tree11, (RedBlackTree.Tree)tree12))) : (RedBlackTree.Tree)"JD-Core does not support Kotlin")));
/*     */   }
/*     */   
/*     */   private <A, B> RedBlackTree.Tree<A, B> doFrom(RedBlackTree.Tree<A, B> tree, Object from, Ordering<A> ordering) {
/* 226 */     if (tree == null)
/* 226 */       return null; 
/* 227 */     if (ordering.lt(tree.key(), from))
/* 227 */       return doFrom(tree.right(), (A)from, ordering); 
/* 228 */     RedBlackTree.Tree<Object, ?> newLeft = doFrom((RedBlackTree.Tree)tree.left(), from, ordering);
/* 229 */     return (newLeft == tree.left()) ? tree : (
/* 230 */       (newLeft == null) ? upd(tree.right(), tree.key(), tree.value(), false, ordering) : 
/* 231 */       rebalance(tree, (RedBlackTree.Tree)newLeft, tree.right()));
/*     */   }
/*     */   
/*     */   private <A, B> RedBlackTree.Tree<A, B> doTo(RedBlackTree.Tree<A, B> tree, Object to, Ordering<A> ordering) {
/* 234 */     if (tree == null)
/* 234 */       return null; 
/* 235 */     if (ordering.lt(to, tree.key()))
/* 235 */       return doTo(tree.left(), (A)to, ordering); 
/* 236 */     RedBlackTree.Tree<Object, ?> newRight = doTo((RedBlackTree.Tree)tree.right(), to, ordering);
/* 237 */     return (newRight == tree.right()) ? tree : (
/* 238 */       (newRight == null) ? upd(tree.left(), tree.key(), tree.value(), false, ordering) : 
/* 239 */       rebalance(tree, tree.left(), (RedBlackTree.Tree)newRight));
/*     */   }
/*     */   
/*     */   private <A, B> RedBlackTree.Tree<A, B> doUntil(RedBlackTree.Tree<A, B> tree, Object until, Ordering<A> ordering) {
/* 242 */     if (tree == null)
/* 242 */       return null; 
/* 243 */     if (ordering.lteq(until, tree.key()))
/* 243 */       return doUntil(tree.left(), (A)until, ordering); 
/* 244 */     RedBlackTree.Tree<Object, ?> newRight = doUntil((RedBlackTree.Tree)tree.right(), until, ordering);
/* 245 */     return (newRight == tree.right()) ? tree : (
/* 246 */       (newRight == null) ? upd(tree.left(), tree.key(), tree.value(), false, ordering) : 
/* 247 */       rebalance(tree, tree.left(), (RedBlackTree.Tree)newRight));
/*     */   }
/*     */   
/*     */   private <A, B> RedBlackTree.Tree<A, B> doRange(RedBlackTree.Tree<A, B> tree, Object from, Object until, Ordering<A> ordering) {
/* 250 */     if (tree == null)
/* 250 */       return null; 
/* 251 */     if (ordering.lt(tree.key(), from))
/* 251 */       return doRange(tree.right(), (A)from, (A)until, ordering); 
/* 252 */     if (ordering.lteq(until, tree.key()))
/* 252 */       return doRange(tree.left(), (A)from, (A)until, ordering); 
/* 253 */     RedBlackTree.Tree<Object, ?> newLeft = doFrom((RedBlackTree.Tree)tree.left(), from, ordering);
/* 254 */     RedBlackTree.Tree<Object, ?> newRight = doUntil((RedBlackTree.Tree)tree.right(), until, ordering);
/* 255 */     return (newLeft == tree.left() && newRight == tree.right()) ? tree : (
/* 256 */       (newLeft == null) ? upd((RedBlackTree.Tree)newRight, tree.key(), tree.value(), false, ordering) : (
/* 257 */       (newRight == null) ? upd((RedBlackTree.Tree)newLeft, tree.key(), tree.value(), false, ordering) : 
/* 258 */       rebalance(tree, (RedBlackTree.Tree)newLeft, (RedBlackTree.Tree)newRight)));
/*     */   }
/*     */   
/*     */   private <A, B> RedBlackTree.Tree<A, B> doDrop(RedBlackTree.Tree<A, B> tree, int n) {
/* 262 */     if (n <= 0)
/* 262 */       return tree; 
/* 263 */     if (n >= count(tree))
/* 263 */       return null; 
/* 264 */     int count = count(tree.left());
/* 265 */     if (n > count)
/* 265 */       return doDrop(tree.right(), n - count - 1); 
/* 266 */     RedBlackTree.Tree<?, ?> newLeft = doDrop(tree.left(), n);
/* 267 */     return (newLeft == tree.left()) ? tree : (
/* 268 */       (newLeft == null) ? updNth(tree.right(), n - count - 1, tree.key(), tree.value(), false) : 
/* 269 */       rebalance(tree, (RedBlackTree.Tree)newLeft, tree.right()));
/*     */   }
/*     */   
/*     */   private <A, B> RedBlackTree.Tree<A, B> doTake(RedBlackTree.Tree<A, B> tree, int n) {
/* 272 */     if (n <= 0)
/* 272 */       return null; 
/* 273 */     if (n >= count(tree))
/* 273 */       return tree; 
/* 274 */     int count = count(tree.left());
/* 275 */     if (n <= count)
/* 275 */       return doTake(tree.left(), n); 
/* 276 */     RedBlackTree.Tree<?, ?> newRight = doTake(tree.right(), n - count - 1);
/* 277 */     return (newRight == tree.right()) ? tree : (
/* 278 */       (newRight == null) ? updNth(tree.left(), n, tree.key(), tree.value(), false) : 
/* 279 */       rebalance(tree, tree.left(), (RedBlackTree.Tree)newRight));
/*     */   }
/*     */   
/*     */   private <A, B> RedBlackTree.Tree<A, B> doSlice(RedBlackTree.Tree<?, ?> tree, int from, int until) {
/* 282 */     if (tree == null)
/* 282 */       return null; 
/* 283 */     int count = count(tree.left());
/* 284 */     if (from > count)
/* 284 */       return doSlice((RedBlackTree.Tree)tree.right(), from - count - 1, until - count - 1); 
/* 285 */     if (until <= count)
/* 285 */       return doSlice((RedBlackTree.Tree)tree.left(), from, until); 
/* 286 */     RedBlackTree.Tree<?, ?> newLeft = doDrop(tree.left(), from);
/* 287 */     RedBlackTree.Tree<?, ?> newRight = doTake(tree.right(), until - count - 1);
/* 288 */     return (newLeft == tree.left() && newRight == tree.right()) ? (RedBlackTree.Tree)tree : (
/* 289 */       (newLeft == null) ? updNth((RedBlackTree.Tree)newRight, from - count - 1, (A)tree.key(), (B)tree.value(), false) : (
/* 290 */       (newRight == null) ? updNth((RedBlackTree.Tree)newLeft, until, (A)tree.key(), (B)tree.value(), false) : 
/* 291 */       rebalance((RedBlackTree.Tree)tree, (RedBlackTree.Tree)newLeft, (RedBlackTree.Tree)newRight)));
/*     */   }
/*     */   
/*     */   private final List unzip$1(List<RedBlackTree.Tree<A, B>> zipper, boolean leftMost) {
/*     */     while (true) {
/* 306 */       RedBlackTree.Tree<A, B> next = leftMost ? ((RedBlackTree.Tree<A, B>)zipper.head()).left() : ((RedBlackTree.Tree<A, B>)zipper.head()).right();
/* 307 */       if (next == null)
/* 307 */         return zipper; 
/* 309 */       zipper = zipper.$colon$colon(next);
/*     */     } 
/*     */   }
/*     */   
/*     */   private final Tuple4 unzipBoth$1(RedBlackTree.Tree<?, ?> left, RedBlackTree.Tree<?, ?> right, List leftZipper, List rightZipper, int smallerDepth) {
/*     */     while (true) {
/* 320 */       if (scala$collection$immutable$RedBlackTree$$isBlackTree(left) && scala$collection$immutable$RedBlackTree$$isBlackTree(right)) {
/* 321 */         smallerDepth++;
/* 321 */         rightZipper = rightZipper.$colon$colon(right);
/* 321 */         leftZipper = leftZipper.$colon$colon(left);
/* 321 */         right = right.left();
/* 321 */         left = left.right();
/*     */         continue;
/*     */       } 
/* 322 */       if (isRedTree(left) && isRedTree(right)) {
/* 323 */         rightZipper = rightZipper.$colon$colon(right);
/* 323 */         leftZipper = leftZipper.$colon$colon(left);
/* 323 */         right = right.left();
/* 323 */         left = left.right();
/*     */         continue;
/*     */       } 
/* 324 */       if (isRedTree(right)) {
/* 325 */         rightZipper = rightZipper.$colon$colon(right);
/* 325 */         leftZipper = leftZipper;
/* 325 */         right = right.left();
/* 325 */         left = left;
/*     */         continue;
/*     */       } 
/* 326 */       if (isRedTree(left)) {
/* 327 */         leftZipper = leftZipper.$colon$colon(left);
/* 327 */         right = right;
/* 327 */         left = left.right();
/*     */         continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 330 */     if (left == null && scala$collection$immutable$RedBlackTree$$isBlackTree(right)) {
/*     */     
/*     */     } else {
/* 333 */       if (scala$collection$immutable$RedBlackTree$$isBlackTree(left) && right == null)
/* 334 */         return new Tuple4(unzip$1(leftZipper.$colon$colon(left), false), BoxesRunTime.boxToBoolean(false), BoxesRunTime.boxToBoolean(false), BoxesRunTime.boxToInteger(smallerDepth)); 
/* 337 */       throw scala.sys.package$.MODULE$.error((new StringBuilder()).append("unmatched trees in unzip: ").append(left).append(", ").append(right).toString());
/*     */     } 
/*     */     return (left == null && right == null) ? new Tuple4(Nil$.MODULE$, BoxesRunTime.boxToBoolean(true), BoxesRunTime.boxToBoolean(false), BoxesRunTime.boxToInteger(smallerDepth)) : (Tuple4)"JD-Core does not support Kotlin";
/*     */   }
/*     */   
/*     */   private <A, B> Tuple4<List<RedBlackTree.Tree<A, B>>, Object, Object, Object> compareDepth(RedBlackTree.Tree left, RedBlackTree.Tree right) {
/* 340 */     return unzipBoth$1(left, right, Nil$.MODULE$, Nil$.MODULE$, 0);
/*     */   }
/*     */   
/*     */   private final List findDepth$1(List<RedBlackTree.Tree<?, ?>> zipper, int depth) {
/*     */     while (true) {
/* 346 */       boolean bool = false;
/* 346 */       $colon$colon<RedBlackTree.Tree<?, ?>> $colon$colon = null;
/*     */       if (zipper instanceof $colon$colon) {
/*     */         bool = true;
/*     */         $colon$colon = ($colon$colon)zipper;
/* 346 */         if (scala$collection$immutable$RedBlackTree$$isBlackTree($colon$colon.hd$1())) {
/* 347 */           if (depth == 1)
/*     */             return zipper; 
/* 347 */           depth--;
/* 347 */           zipper = $colon$colon.tl$1();
/*     */           continue;
/*     */         } 
/*     */       } 
/*     */       if (bool) {
/* 348 */         zipper = $colon$colon.tl$1();
/*     */         continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 349 */     if (Nil$.MODULE$ == null) {
/* 349 */       if (zipper != null)
/*     */         throw new MatchError(zipper); 
/* 349 */     } else if (!Nil$.MODULE$.equals(zipper)) {
/*     */       throw new MatchError(zipper);
/*     */     } 
/* 349 */     throw scala.sys.package$.MODULE$.error("Defect: unexpected empty zipper while computing range");
/*     */   }
/*     */   
/*     */   private <A, B> RedBlackTree.Tree<A, B> rebalance(RedBlackTree.Tree tree, RedBlackTree.Tree<?, ?> newLeft, RedBlackTree.Tree<?, ?> newRight) {
/* 354 */     RedBlackTree.Tree<?, ?> blkNewLeft = blacken(newLeft);
/* 355 */     RedBlackTree.Tree<?, ?> blkNewRight = blacken(newRight);
/* 356 */     Tuple4<List<RedBlackTree.Tree<?, ?>>, Object, Object, Object> tuple4 = compareDepth(blkNewLeft, blkNewRight);
/* 356 */     if (tuple4 != null) {
/* 356 */       Tuple4 tuple41 = new Tuple4(tuple4._1(), tuple4._2(), tuple4._3(), tuple4._4());
/* 356 */       List zipper = (List)tuple41._1();
/* 356 */       boolean levelled = BoxesRunTime.unboxToBoolean(tuple41._2()), leftMost = BoxesRunTime.unboxToBoolean(tuple41._3());
/* 356 */       int smallerDepth = BoxesRunTime.unboxToInt(tuple41._4());
/* 359 */       Object object2 = tree.value(), object1 = tree.key();
/* 359 */       RedBlackTree.BlackTree$ blackTree$ = RedBlackTree.BlackTree$.MODULE$;
/* 361 */       List<RedBlackTree.Tree> zipFrom = findDepth$1(zipper, smallerDepth);
/* 363 */       RedBlackTree.Tree<Object, Object> tree1 = zipFrom.head();
/* 363 */       Object object4 = tree.value(), object3 = tree.key();
/* 363 */       RedBlackTree.RedTree$ redTree$1 = RedBlackTree.RedTree$.MODULE$;
/* 365 */       RedBlackTree.Tree<Object, Object> tree2 = zipFrom.head();
/* 365 */       Object object6 = tree.value(), object5 = tree.key();
/* 365 */       RedBlackTree.RedTree$ redTree$2 = RedBlackTree.RedTree$.MODULE$;
/* 365 */       RedBlackTree.RedTree union = leftMost ? new RedBlackTree.RedTree<Object, Object>(object3, object4, (RedBlackTree.Tree)blkNewLeft, tree1) : new RedBlackTree.RedTree<Object, Object>(object5, object6, tree2, (RedBlackTree.Tree)blkNewRight);
/* 367 */       RedBlackTree.Tree<A, B> zippedTree = (RedBlackTree.Tree)((LinearSeqOptimized)zipFrom.tail()).foldLeft(union, (Function2)new RedBlackTree$$anonfun$1(leftMost));
/* 373 */       return levelled ? new RedBlackTree.BlackTree<A, B>((A)object1, (B)object2, (RedBlackTree.Tree)blkNewLeft, (RedBlackTree.Tree)blkNewRight) : zippedTree;
/*     */     } 
/*     */     throw new MatchError(tuple4);
/*     */   }
/*     */   
/*     */   public static class RedBlackTree$$anonfun$1 extends AbstractFunction2<RedBlackTree.Tree<A, B>, RedBlackTree.Tree<A, B>, RedBlackTree.Tree<A, B>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final boolean leftMost$1;
/*     */     
/*     */     public RedBlackTree$$anonfun$1(boolean leftMost$1) {}
/*     */     
/*     */     public final RedBlackTree.Tree<A, B> apply(RedBlackTree.Tree<A, B> tree, RedBlackTree.Tree<?, ?> node) {
/*     */       return this.leftMost$1 ? RedBlackTree$.MODULE$.scala$collection$immutable$RedBlackTree$$balanceLeft(RedBlackTree$.MODULE$.scala$collection$immutable$RedBlackTree$$isBlackTree(node), (A)node.key(), node.value(), tree, (RedBlackTree.Tree)node.right()) : RedBlackTree$.MODULE$.scala$collection$immutable$RedBlackTree$$balanceRight(RedBlackTree$.MODULE$.scala$collection$immutable$RedBlackTree$$isBlackTree(node), (A)node.key(), node.value(), (RedBlackTree.Tree)node.left(), tree);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\RedBlackTree$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */