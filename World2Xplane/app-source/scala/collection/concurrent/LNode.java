/*     */ package scala.collection.concurrent;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.ListMap;
/*     */ import scala.collection.immutable.ListMap$;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001)4Q!\001\002\003\t!\021Q\001\024(pI\026T!a\001\003\002\025\r|gnY;se\026tGO\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006,2!\003\t\035'\t\001!\002\005\003\f\0319YR\"\001\002\n\0055\021!\001C'bS:tu\016Z3\021\005=\001B\002\001\003\006#\001\021\ra\005\002\002\027\016\001\021C\001\013\031!\t)b#D\001\007\023\t9bAA\004O_RD\027N\\4\021\005UI\022B\001\016\007\005\r\te.\037\t\003\037q!Q!\b\001C\002M\021\021A\026\005\t?\001\021)\031!C\003A\0059A.[:u[\006\004X#A\021\021\t\t*cbG\007\002G)\021A\005B\001\nS6lW\017^1cY\026L!AJ\022\003\0171K7\017^'ba\"A\001\006\001B\001B\0035\021%\001\005mSN$X.\0319!\021\025Q\003\001\"\001,\003\031a\024N\\5u}Q\021A&\f\t\005\027\001q1\004C\003 S\001\007\021\005C\003+\001\021\005q\006F\002-aIBQ!\r\030A\0029\t\021a\033\005\006g9\002\raG\001\002m\")!\006\001C\001kQ)AF\016\035;y!)q\007\016a\001\035\005\0211.\r\005\006sQ\002\raG\001\003mFBQa\017\033A\0029\t!a\033\032\t\013u\"\004\031A\016\002\005Y\024\004\"B \001\t\003\001\025\001C5og\026\024H/\0323\025\0071\n%\tC\0032}\001\007a\002C\0034}\001\0071\004C\003E\001\021\005Q)A\004sK6|g/\0323\025\007)1u\tC\0032\007\002\007a\002C\003I\007\002\007\021*\001\002diB!1B\023\b\034\023\tY%AA\004Ue&,W*\0319\t\0135\003A\021\001(\002\007\035,G\017\006\002P%B\031Q\003U\016\n\005E3!AB(qi&|g\016C\0032\031\002\007a\002C\003U\001\021\005Q+\001\006dC\016DW\rZ*ju\026$\"AV-\021\005U9\026B\001-\007\005\rIe\016\036\005\006\021N\003\rA\027\t\003+mK!\001\030\004\003\r\005s\027PU3g\021\025q\006\001\"\001`\003\031\031HO]5oOR\021\001\r\033\t\003C\032l\021A\031\006\003G\022\fA\001\\1oO*\tQ-\001\003kCZ\f\027BA4c\005\031\031FO]5oO\")\021.\030a\001-\006\031A.\032<")
/*     */ public final class LNode<K, V> extends MainNode<K, V> {
/*     */   private final ListMap<K, V> listmap;
/*     */   
/*     */   public final ListMap<K, V> listmap() {
/* 431 */     return this.listmap;
/*     */   }
/*     */   
/*     */   public LNode(ListMap<K, V> listmap) {}
/*     */   
/*     */   public LNode(Object k, Object v) {
/* 433 */     this((ListMap<K, V>)ListMap$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[1])));
/*     */   }
/*     */   
/*     */   public LNode(Object k1, Object v1, Object k2, Object v2) {
/* 434 */     this((ListMap<K, V>)ListMap$.MODULE$.apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Tuple2[2])));
/*     */   }
/*     */   
/*     */   public LNode<K, V> inserted(Object k, Object v) {
/* 435 */     return new LNode(listmap().$plus(new Tuple2(k, v)));
/*     */   }
/*     */   
/*     */   public MainNode<K, V> removed(Object k, TrieMap ct) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokevirtual listmap : ()Lscala/collection/immutable/ListMap;
/*     */     //   4: aload_1
/*     */     //   5: invokevirtual $minus : (Ljava/lang/Object;)Lscala/collection/immutable/ListMap;
/*     */     //   8: astore_3
/*     */     //   9: aload_3
/*     */     //   10: invokevirtual size : ()I
/*     */     //   13: iconst_1
/*     */     //   14: if_icmple -> 28
/*     */     //   17: new scala/collection/concurrent/LNode
/*     */     //   20: dup
/*     */     //   21: aload_3
/*     */     //   22: invokespecial <init> : (Lscala/collection/immutable/ListMap;)V
/*     */     //   25: goto -> 97
/*     */     //   28: aload_3
/*     */     //   29: invokevirtual iterator : ()Lscala/collection/Iterator;
/*     */     //   32: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   37: checkcast scala/Tuple2
/*     */     //   40: astore #7
/*     */     //   42: aload #7
/*     */     //   44: ifnull -> 98
/*     */     //   47: new scala/Tuple2
/*     */     //   50: dup
/*     */     //   51: aload #7
/*     */     //   53: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   56: aload #7
/*     */     //   58: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   61: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
/*     */     //   64: astore #4
/*     */     //   66: aload #4
/*     */     //   68: invokevirtual _1 : ()Ljava/lang/Object;
/*     */     //   71: astore #6
/*     */     //   73: aload #4
/*     */     //   75: invokevirtual _2 : ()Ljava/lang/Object;
/*     */     //   78: astore #5
/*     */     //   80: new scala/collection/concurrent/TNode
/*     */     //   83: dup
/*     */     //   84: aload #6
/*     */     //   86: aload #5
/*     */     //   88: aload_2
/*     */     //   89: aload #6
/*     */     //   91: invokevirtual computeHash : (Ljava/lang/Object;)I
/*     */     //   94: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;I)V
/*     */     //   97: areturn
/*     */     //   98: new scala/MatchError
/*     */     //   101: dup
/*     */     //   102: aload #7
/*     */     //   104: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   107: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #437	-> 0
/*     */     //   #438	-> 9
/*     */     //   #440	-> 28
/*     */     //   #441	-> 80
/*     */     //   #436	-> 97
/*     */     //   #440	-> 98
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	108	0	this	Lscala/collection/concurrent/LNode;
/*     */     //   0	108	1	k	Ljava/lang/Object;
/*     */     //   0	108	2	ct	Lscala/collection/concurrent/TrieMap;
/*     */     //   9	99	3	updmap	Lscala/collection/immutable/ListMap;
/*     */     //   73	24	6	k	Ljava/lang/Object;
/*     */     //   80	17	5	v	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public Option<V> get(Object k) {
/* 444 */     return listmap().get(k);
/*     */   }
/*     */   
/*     */   public int cachedSize(Object ct) {
/* 445 */     return listmap().size();
/*     */   }
/*     */   
/*     */   public String string(int lev) {
/* 446 */     Predef$ predef$1 = Predef$.MODULE$, predef$2 = Predef$.MODULE$;
/* 446 */     return (new StringBuilder()).append((new StringOps(" ")).$times(lev)).append((new StringOps("LNode(%s)")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { listmap().mkString(", ") }))).toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\concurrent\LNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */