/*     */ package scala.util.parsing.combinator;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.package$;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.VolatileByteRef;
/*     */ import scala.util.Either;
/*     */ import scala.util.Right;
/*     */ import scala.util.parsing.input.Position;
/*     */ import scala.util.parsing.input.Reader;
/*     */ 
/*     */ public abstract class PackratParsers$class {
/*     */   public static void $init$(PackratParsers $this) {}
/*     */   
/*     */   public static PackratParsers.PackratParser phrase(PackratParsers $this, Parsers.Parser<?> p) {
/* 106 */     Parsers.Parser<?> q = $this.scala$util$parsing$combinator$PackratParsers$$super$phrase(p);
/* 107 */     return new PackratParsers$$anon$1($this, q);
/*     */   }
/*     */   
/*     */   public static Position scala$util$parsing$combinator$PackratParsers$$getPosFromResult(PackratParsers $this, Parsers.ParseResult r) {
/* 115 */     return r.next().pos();
/*     */   }
/*     */   
/*     */   public static PackratParsers.PackratParser parser2packrat(PackratParsers $this, Function0 p) {
/* 146 */     ObjectRef q$lzy = new ObjectRef(null);
/* 188 */     VolatileByteRef bitmap$0 = new VolatileByteRef((byte)0);
/*     */     return $this.memo($this.scala$util$parsing$combinator$PackratParsers$$super$Parser((Function1<Reader<Object>, Parsers.ParseResult<?>>)new PackratParsers$$anonfun$parser2packrat$1($this, q$lzy, p, bitmap$0)));
/*     */   }
/*     */   
/*     */   private static final Parsers.Parser q$lzycompute$1(PackratParsers $this, ObjectRef q$lzy$1, Function0 p$3, VolatileByteRef bitmap$0$1) {
/*     */     synchronized ($this) {
/* 188 */       if ((byte)(bitmap$0$1.elem & 0x1) == 0) {
/*     */         q$lzy$1.elem = p$3.apply();
/*     */         bitmap$0$1.elem = (byte)(bitmap$0$1.elem | 0x1);
/*     */       } 
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/util/parsing/combinator/PackratParsers}, name=$this} */
/*     */       return (Parsers.Parser)q$lzy$1.elem;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final Parsers.Parser q$2(PackratParsers $this, ObjectRef q$lzy$1, Function0 p$3, VolatileByteRef bitmap$0$1) {
/* 188 */     return ((byte)(bitmap$0$1.elem & 0x1) == 0) ? q$lzycompute$1($this, q$lzy$1, p$3, bitmap$0$1) : (Parsers.Parser)q$lzy$1.elem;
/*     */   }
/*     */   
/*     */   public static Option scala$util$parsing$combinator$PackratParsers$$recall(PackratParsers $this, Parsers.Parser p, PackratParsers.PackratReader in) {
/*     */     // Byte code:
/*     */     //   0: aload_2
/*     */     //   1: aload_1
/*     */     //   2: invokevirtual scala$util$parsing$combinator$PackratParsers$$getFromCache : (Lscala/util/parsing/combinator/Parsers$Parser;)Lscala/Option;
/*     */     //   5: astore #8
/*     */     //   7: aload_2
/*     */     //   8: invokevirtual scala$util$parsing$combinator$PackratParsers$$recursionHeads : ()Lscala/collection/mutable/HashMap;
/*     */     //   11: aload_2
/*     */     //   12: invokevirtual pos : ()Lscala/util/parsing/input/Position;
/*     */     //   15: invokevirtual get : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   18: astore #9
/*     */     //   20: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   23: dup
/*     */     //   24: ifnonnull -> 36
/*     */     //   27: pop
/*     */     //   28: aload #9
/*     */     //   30: ifnull -> 252
/*     */     //   33: goto -> 44
/*     */     //   36: aload #9
/*     */     //   38: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   41: ifne -> 252
/*     */     //   44: aload #9
/*     */     //   46: instanceof scala/Some
/*     */     //   49: ifeq -> 255
/*     */     //   52: aload #9
/*     */     //   54: checkcast scala/Some
/*     */     //   57: astore #5
/*     */     //   59: aload #5
/*     */     //   61: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   64: ifnull -> 255
/*     */     //   67: aload #8
/*     */     //   69: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   72: astore_3
/*     */     //   73: dup
/*     */     //   74: ifnonnull -> 85
/*     */     //   77: pop
/*     */     //   78: aload_3
/*     */     //   79: ifnull -> 92
/*     */     //   82: goto -> 164
/*     */     //   85: aload_3
/*     */     //   86: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   89: ifeq -> 164
/*     */     //   92: aload #5
/*     */     //   94: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   97: checkcast scala/util/parsing/combinator/PackratParsers$Head
/*     */     //   100: invokevirtual headParser : ()Lscala/util/parsing/combinator/Parsers$Parser;
/*     */     //   103: astore #4
/*     */     //   105: aload #5
/*     */     //   107: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   110: checkcast scala/util/parsing/combinator/PackratParsers$Head
/*     */     //   113: invokevirtual involvedSet : ()Lscala/collection/immutable/List;
/*     */     //   116: aload #4
/*     */     //   118: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*     */     //   121: aload_1
/*     */     //   122: invokevirtual contains : (Ljava/lang/Object;)Z
/*     */     //   125: ifne -> 164
/*     */     //   128: new scala/Some
/*     */     //   131: dup
/*     */     //   132: new scala/util/parsing/combinator/PackratParsers$MemoEntry
/*     */     //   135: dup
/*     */     //   136: aload_0
/*     */     //   137: getstatic scala/package$.MODULE$ : Lscala/package$;
/*     */     //   140: invokevirtual Right : ()Lscala/util/Right$;
/*     */     //   143: new scala/util/parsing/combinator/Parsers$Failure
/*     */     //   146: dup
/*     */     //   147: aload_0
/*     */     //   148: ldc 'dummy '
/*     */     //   150: aload_2
/*     */     //   151: invokespecial <init> : (Lscala/util/parsing/combinator/Parsers;Ljava/lang/String;Lscala/util/parsing/input/Reader;)V
/*     */     //   154: invokevirtual apply : (Ljava/lang/Object;)Lscala/util/Right;
/*     */     //   157: invokespecial <init> : (Lscala/util/parsing/combinator/PackratParsers;Lscala/util/Either;)V
/*     */     //   160: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   163: areturn
/*     */     //   164: aload #5
/*     */     //   166: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   169: checkcast scala/util/parsing/combinator/PackratParsers$Head
/*     */     //   172: invokevirtual evalSet : ()Lscala/collection/immutable/List;
/*     */     //   175: aload_1
/*     */     //   176: invokevirtual contains : (Ljava/lang/Object;)Z
/*     */     //   179: ifeq -> 252
/*     */     //   182: aload #5
/*     */     //   184: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   187: checkcast scala/util/parsing/combinator/PackratParsers$Head
/*     */     //   190: aload #5
/*     */     //   192: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   195: checkcast scala/util/parsing/combinator/PackratParsers$Head
/*     */     //   198: invokevirtual evalSet : ()Lscala/collection/immutable/List;
/*     */     //   201: new scala/util/parsing/combinator/PackratParsers$$anonfun$scala$util$parsing$combinator$PackratParsers$$recall$1
/*     */     //   204: dup
/*     */     //   205: aload_0
/*     */     //   206: aload_1
/*     */     //   207: invokespecial <init> : (Lscala/util/parsing/combinator/PackratParsers;Lscala/util/parsing/combinator/Parsers$Parser;)V
/*     */     //   210: invokevirtual filterNot : (Lscala/Function1;)Ljava/lang/Object;
/*     */     //   213: checkcast scala/collection/immutable/List
/*     */     //   216: invokevirtual evalSet_$eq : (Lscala/collection/immutable/List;)V
/*     */     //   219: aload_1
/*     */     //   220: aload_2
/*     */     //   221: invokevirtual apply : (Lscala/util/parsing/input/Reader;)Lscala/util/parsing/combinator/Parsers$ParseResult;
/*     */     //   224: astore #7
/*     */     //   226: aload #8
/*     */     //   228: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   231: checkcast scala/util/parsing/combinator/PackratParsers$MemoEntry
/*     */     //   234: astore #6
/*     */     //   236: aload #6
/*     */     //   238: getstatic scala/package$.MODULE$ : Lscala/package$;
/*     */     //   241: invokevirtual Right : ()Lscala/util/Right$;
/*     */     //   244: aload #7
/*     */     //   246: invokevirtual apply : (Ljava/lang/Object;)Lscala/util/Right;
/*     */     //   249: invokevirtual r_$eq : (Lscala/util/Either;)V
/*     */     //   252: aload #8
/*     */     //   254: areturn
/*     */     //   255: new scala/MatchError
/*     */     //   258: dup
/*     */     //   259: aload #9
/*     */     //   261: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   264: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #157	-> 0
/*     */     //   #158	-> 7
/*     */     //   #161	-> 20
/*     */     //   #160	-> 20
/*     */     //   #162	-> 44
/*     */     //   #164	-> 67
/*     */     //   #162	-> 92
/*     */     //   #160	-> 94
/*     */     //   #164	-> 100
/*     */     //   #162	-> 105
/*     */     //   #160	-> 107
/*     */     //   #164	-> 113
/*     */     //   #166	-> 128
/*     */     //   #162	-> 164
/*     */     //   #160	-> 166
/*     */     //   #168	-> 172
/*     */     //   #162	-> 182
/*     */     //   #171	-> 184
/*     */     //   #162	-> 190
/*     */     //   #171	-> 192
/*     */     //   #172	-> 219
/*     */     //   #174	-> 226
/*     */     //   #176	-> 236
/*     */     //   #160	-> 252
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	265	0	$this	Lscala/util/parsing/combinator/PackratParsers;
/*     */     //   0	265	1	p	Lscala/util/parsing/combinator/Parsers$Parser;
/*     */     //   0	265	2	in	Lscala/util/parsing/combinator/PackratParsers$PackratReader;
/*     */     //   7	247	8	cached	Lscala/Option;
/*     */     //   20	234	9	head	Lscala/Option;
/*     */     //   226	26	7	tempRes	Lscala/util/parsing/combinator/Parsers$ParseResult;
/*     */     //   236	16	6	tempEntry	Lscala/util/parsing/combinator/PackratParsers$MemoEntry;
/*     */   }
/*     */   
/*     */   public static void scala$util$parsing$combinator$PackratParsers$$setupLR(PackratParsers $this, Parsers.Parser p, PackratParsers.PackratReader in, PackratParsers.LR recDetect) {
/*     */     // Byte code:
/*     */     //   0: aload_3
/*     */     //   1: invokevirtual head : ()Lscala/Option;
/*     */     //   4: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   7: astore #4
/*     */     //   9: dup
/*     */     //   10: ifnonnull -> 22
/*     */     //   13: pop
/*     */     //   14: aload #4
/*     */     //   16: ifnull -> 30
/*     */     //   19: goto -> 56
/*     */     //   22: aload #4
/*     */     //   24: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   27: ifeq -> 56
/*     */     //   30: aload_3
/*     */     //   31: new scala/Some
/*     */     //   34: dup
/*     */     //   35: new scala/util/parsing/combinator/PackratParsers$Head
/*     */     //   38: dup
/*     */     //   39: aload_0
/*     */     //   40: aload_1
/*     */     //   41: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */     //   44: getstatic scala/collection/immutable/Nil$.MODULE$ : Lscala/collection/immutable/Nil$;
/*     */     //   47: invokespecial <init> : (Lscala/util/parsing/combinator/PackratParsers;Lscala/util/parsing/combinator/Parsers$Parser;Lscala/collection/immutable/List;Lscala/collection/immutable/List;)V
/*     */     //   50: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   53: invokevirtual head_$eq : (Lscala/Option;)V
/*     */     //   56: aload_2
/*     */     //   57: invokevirtual scala$util$parsing$combinator$PackratParsers$$lrStack : ()Lscala/collection/immutable/List;
/*     */     //   60: new scala/util/parsing/combinator/PackratParsers$$anonfun$scala$util$parsing$combinator$PackratParsers$$setupLR$1
/*     */     //   63: dup
/*     */     //   64: aload_0
/*     */     //   65: aload_1
/*     */     //   66: invokespecial <init> : (Lscala/util/parsing/combinator/PackratParsers;Lscala/util/parsing/combinator/Parsers$Parser;)V
/*     */     //   69: invokevirtual takeWhile : (Lscala/Function1;)Lscala/collection/immutable/List;
/*     */     //   72: astore #11
/*     */     //   74: aload #11
/*     */     //   76: invokevirtual isEmpty : ()Z
/*     */     //   79: ifeq -> 83
/*     */     //   82: return
/*     */     //   83: aload #11
/*     */     //   85: invokevirtual head : ()Ljava/lang/Object;
/*     */     //   88: checkcast scala/util/parsing/combinator/PackratParsers$LR
/*     */     //   91: dup
/*     */     //   92: astore #8
/*     */     //   94: aload_3
/*     */     //   95: invokevirtual head : ()Lscala/Option;
/*     */     //   98: invokevirtual head_$eq : (Lscala/Option;)V
/*     */     //   101: aload_3
/*     */     //   102: invokevirtual head : ()Lscala/Option;
/*     */     //   105: dup
/*     */     //   106: astore #6
/*     */     //   108: invokevirtual isEmpty : ()Z
/*     */     //   111: ifeq -> 120
/*     */     //   114: getstatic scala/None$.MODULE$ : Lscala/None$;
/*     */     //   117: goto -> 162
/*     */     //   120: new scala/Some
/*     */     //   123: dup
/*     */     //   124: aload #6
/*     */     //   126: invokevirtual get : ()Ljava/lang/Object;
/*     */     //   129: checkcast scala/util/parsing/combinator/PackratParsers$Head
/*     */     //   132: astore #9
/*     */     //   134: aload #9
/*     */     //   136: aload #8
/*     */     //   138: invokevirtual rule : ()Lscala/util/parsing/combinator/Parsers$Parser;
/*     */     //   141: astore #10
/*     */     //   143: aload #9
/*     */     //   145: invokevirtual involvedSet : ()Lscala/collection/immutable/List;
/*     */     //   148: aload #10
/*     */     //   150: invokevirtual $colon$colon : (Ljava/lang/Object;)Lscala/collection/immutable/List;
/*     */     //   153: invokevirtual involvedSet_$eq : (Lscala/collection/immutable/List;)V
/*     */     //   156: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */     //   159: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   162: pop
/*     */     //   163: aload #11
/*     */     //   165: invokevirtual tail : ()Ljava/lang/Object;
/*     */     //   168: checkcast scala/collection/immutable/List
/*     */     //   171: astore #11
/*     */     //   173: goto -> 74
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #189	-> 0
/*     */     //   #191	-> 56
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	176	0	$this	Lscala/util/parsing/combinator/PackratParsers;
/*     */     //   0	176	1	p	Lscala/util/parsing/combinator/Parsers$Parser;
/*     */     //   0	176	2	in	Lscala/util/parsing/combinator/PackratParsers$PackratReader;
/*     */     //   0	176	3	recDetect	Lscala/util/parsing/combinator/PackratParsers$LR;
/*     */     //   74	102	11	these1	Lscala/collection/immutable/List;
/*     */     //   143	10	10	x$41	Lscala/util/parsing/combinator/Parsers$Parser;
/*     */   }
/*     */   
/*     */   public static Parsers.ParseResult scala$util$parsing$combinator$PackratParsers$$lrAnswer(PackratParsers $this, Parsers.Parser p, PackratParsers.PackratReader in, PackratParsers.LR growable) {
/*     */     // Byte code:
/*     */     //   0: aload_3
/*     */     //   1: ifnull -> 174
/*     */     //   4: aload_3
/*     */     //   5: invokevirtual head : ()Lscala/Option;
/*     */     //   8: instanceof scala/Some
/*     */     //   11: ifeq -> 174
/*     */     //   14: aload_3
/*     */     //   15: invokevirtual head : ()Lscala/Option;
/*     */     //   18: checkcast scala/Some
/*     */     //   21: astore #6
/*     */     //   23: aload #6
/*     */     //   25: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   28: checkcast scala/util/parsing/combinator/PackratParsers$Head
/*     */     //   31: invokevirtual getHead : ()Lscala/util/parsing/combinator/Parsers$Parser;
/*     */     //   34: dup
/*     */     //   35: ifnonnull -> 46
/*     */     //   38: pop
/*     */     //   39: aload_1
/*     */     //   40: ifnull -> 53
/*     */     //   43: goto -> 169
/*     */     //   46: aload_1
/*     */     //   47: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   50: ifeq -> 169
/*     */     //   53: aload_2
/*     */     //   54: aload_1
/*     */     //   55: new scala/util/parsing/combinator/PackratParsers$MemoEntry
/*     */     //   58: dup
/*     */     //   59: aload_0
/*     */     //   60: getstatic scala/package$.MODULE$ : Lscala/package$;
/*     */     //   63: invokevirtual Right : ()Lscala/util/Right$;
/*     */     //   66: aload_3
/*     */     //   67: invokevirtual seed : ()Lscala/util/parsing/combinator/Parsers$ParseResult;
/*     */     //   70: invokevirtual apply : (Ljava/lang/Object;)Lscala/util/Right;
/*     */     //   73: invokespecial <init> : (Lscala/util/parsing/combinator/PackratParsers;Lscala/util/Either;)V
/*     */     //   76: invokevirtual scala$util$parsing$combinator$PackratParsers$$updateCacheAndGet : (Lscala/util/parsing/combinator/Parsers$Parser;Lscala/util/parsing/combinator/PackratParsers$MemoEntry;)Lscala/util/parsing/combinator/PackratParsers$MemoEntry;
/*     */     //   79: pop
/*     */     //   80: aload_3
/*     */     //   81: invokevirtual seed : ()Lscala/util/parsing/combinator/Parsers$ParseResult;
/*     */     //   84: astore #8
/*     */     //   86: aload #8
/*     */     //   88: instanceof scala/util/parsing/combinator/Parsers$Failure
/*     */     //   91: ifeq -> 108
/*     */     //   94: aload #8
/*     */     //   96: checkcast scala/util/parsing/combinator/Parsers$Failure
/*     */     //   99: astore #4
/*     */     //   101: aload #4
/*     */     //   103: astore #7
/*     */     //   105: goto -> 154
/*     */     //   108: aload #8
/*     */     //   110: instanceof scala/util/parsing/combinator/Parsers$Error
/*     */     //   113: ifeq -> 130
/*     */     //   116: aload #8
/*     */     //   118: checkcast scala/util/parsing/combinator/Parsers$Error
/*     */     //   121: astore #5
/*     */     //   123: aload #5
/*     */     //   125: astore #7
/*     */     //   127: goto -> 154
/*     */     //   130: aload #8
/*     */     //   132: instanceof scala/util/parsing/combinator/Parsers$Success
/*     */     //   135: ifeq -> 159
/*     */     //   138: aload_0
/*     */     //   139: aload_1
/*     */     //   140: aload_2
/*     */     //   141: aload #6
/*     */     //   143: invokevirtual x : ()Ljava/lang/Object;
/*     */     //   146: checkcast scala/util/parsing/combinator/PackratParsers$Head
/*     */     //   149: invokestatic grow : (Lscala/util/parsing/combinator/PackratParsers;Lscala/util/parsing/combinator/Parsers$Parser;Lscala/util/parsing/combinator/PackratParsers$PackratReader;Lscala/util/parsing/combinator/PackratParsers$Head;)Lscala/util/parsing/combinator/Parsers$ParseResult;
/*     */     //   152: astore #7
/*     */     //   154: aload #7
/*     */     //   156: goto -> 173
/*     */     //   159: new scala/MatchError
/*     */     //   162: dup
/*     */     //   163: aload #8
/*     */     //   165: invokespecial <init> : (Ljava/lang/Object;)V
/*     */     //   168: athrow
/*     */     //   169: aload_3
/*     */     //   170: invokevirtual seed : ()Lscala/util/parsing/combinator/Parsers$ParseResult;
/*     */     //   173: areturn
/*     */     //   174: new java/lang/Exception
/*     */     //   177: dup
/*     */     //   178: ldc_w 'lrAnswer with no head !!'
/*     */     //   181: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   184: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #210	-> 0
/*     */     //   #212	-> 5
/*     */     //   #210	-> 14
/*     */     //   #212	-> 15
/*     */     //   #210	-> 23
/*     */     //   #213	-> 25
/*     */     //   #215	-> 53
/*     */     //   #210	-> 66
/*     */     //   #215	-> 67
/*     */     //   #210	-> 80
/*     */     //   #216	-> 81
/*     */     //   #217	-> 86
/*     */     //   #218	-> 108
/*     */     //   #219	-> 130
/*     */     //   #210	-> 141
/*     */     //   #219	-> 143
/*     */     //   #216	-> 154
/*     */     //   #210	-> 169
/*     */     //   #213	-> 170
/*     */     //   #210	-> 173
/*     */     //   #222	-> 174
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	185	0	$this	Lscala/util/parsing/combinator/PackratParsers;
/*     */     //   0	185	1	p	Lscala/util/parsing/combinator/Parsers$Parser;
/*     */     //   0	185	2	in	Lscala/util/parsing/combinator/PackratParsers$PackratReader;
/*     */     //   0	185	3	growable	Lscala/util/parsing/combinator/PackratParsers$LR;
/*     */   }
/*     */   
/*     */   public static PackratParsers.PackratParser memo(PackratParsers $this, Parsers.Parser p) {
/* 234 */     return new PackratParsers$$anon$2($this, p);
/*     */   }
/*     */   
/*     */   private static Parsers.ParseResult grow(PackratParsers $this, Parsers.Parser<T> p, PackratParsers.PackratReader<T> rest, PackratParsers.Head head) {
/*     */     while (true) {
/* 286 */       rest.scala$util$parsing$combinator$PackratParsers$$recursionHeads().put(rest.pos(), head);
/* 287 */       PackratParsers.MemoEntry memoEntry = (PackratParsers.MemoEntry)rest.<T>scala$util$parsing$combinator$PackratParsers$$getFromCache(p).get();
/* 287 */       if (memoEntry != null && memoEntry
/* 288 */         .r() instanceof Right) {
/* 288 */         Right right = (Right)memoEntry.r();
/* 288 */         Parsers.ParseResult parseResult = (Parsers.ParseResult)right.b();
/*     */         head.evalSet_$eq(head.involvedSet());
/* 294 */         Parsers.ParseResult<T> tempRes = p.apply(rest);
/* 295 */         if (tempRes instanceof Parsers.Success) {
/* 295 */           Parsers.Success success = (Parsers.Success)tempRes;
/* 296 */           if (scala$util$parsing$combinator$PackratParsers$$getPosFromResult($this, parseResult).$less(scala$util$parsing$combinator$PackratParsers$$getPosFromResult($this, tempRes))) {
/* 297 */             rest.scala$util$parsing$combinator$PackratParsers$$updateCacheAndGet(p, new PackratParsers.MemoEntry<T>($this, (Either<PackratParsers.LR, Parsers.ParseResult<?>>)package$.MODULE$.Right().apply(success)));
/* 298 */             $this = $this;
/*     */             continue;
/*     */           } 
/* 301 */           rest.scala$util$parsing$combinator$PackratParsers$$recursionHeads().$minus$eq(rest.pos());
/* 302 */           PackratParsers.MemoEntry memoEntry1 = (PackratParsers.MemoEntry)rest.<T>scala$util$parsing$combinator$PackratParsers$$getFromCache(p).get();
/* 302 */           if (memoEntry1 != null && memoEntry1
/* 303 */             .r() instanceof Right) {
/* 303 */             Right right1 = (Right)memoEntry1.r();
/* 303 */             if (right1.b() instanceof Parsers.ParseResult) {
/* 303 */               Parsers.ParseResult parseResult1 = (Parsers.ParseResult)right1.b();
/*     */               Parsers.ParseResult parseResult2 = parseResult1;
/*     */             } 
/*     */           } 
/* 304 */           throw new Exception("impossible match");
/*     */         } 
/* 308 */         rest.scala$util$parsing$combinator$PackratParsers$$recursionHeads().$minus$eq(rest.pos());
/* 309 */         return parseResult;
/*     */       } 
/*     */       break;
/*     */     } 
/*     */     throw new Exception("impossible match");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\parsing\combinator\PackratParsers$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */