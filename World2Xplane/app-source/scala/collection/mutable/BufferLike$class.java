/*     */ package scala.collection.mutable;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Predef$;
/*     */ import scala.collection.GenTraversableOnce;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Range$;
/*     */ import scala.collection.script.Message;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.RichInt$;
/*     */ 
/*     */ public abstract class BufferLike$class {
/*     */   public static void $init$(Buffer $this) {}
/*     */   
/*     */   public static void remove(Buffer $this, int n, int count) {
/* 116 */     Predef$ predef$ = Predef$.MODULE$;
/* 116 */     Range$ range$ = Range$.MODULE$;
/* 116 */     BufferLike$$anonfun$remove$1 bufferLike$$anonfun$remove$1 = new BufferLike$$anonfun$remove$1($this, n);
/*     */     Range range;
/* 116 */     if ((range = new Range(0, count, 1)).validateRangeBoundaries((Function1)bufferLike$$anonfun$remove$1)) {
/*     */       int terminal1;
/*     */       int step1;
/*     */       int i1;
/* 116 */       for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 116 */         $this.remove(n);
/* 116 */         i1 += step1;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Buffer $minus$eq(Buffer $this, Object x) {
/* 126 */     int i = $this.indexOf(x);
/* 127 */     (i != -1) ? $this.remove(i) : BoxedUnit.UNIT;
/* 128 */     return $this;
/*     */   }
/*     */   
/*     */   public static Buffer $plus$plus$eq$colon(Buffer $this, TraversableOnce xs) {
/* 136 */     $this.insertAll(0, xs.toTraversable());
/* 136 */     return $this;
/*     */   }
/*     */   
/*     */   public static void append(Buffer $this, Seq elems) {
/* 142 */     $this.appendAll((TraversableOnce)elems);
/*     */   }
/*     */   
/*     */   public static void appendAll(Buffer $this, TraversableOnce xs) {
/* 147 */     $this.$plus$plus$eq(xs);
/*     */   }
/*     */   
/*     */   public static void prepend(Buffer $this, Seq elems) {
/* 152 */     $this.prependAll((TraversableOnce)elems);
/*     */   }
/*     */   
/*     */   public static void prependAll(Buffer $this, TraversableOnce xs) {
/* 157 */     $this.$plus$plus$eq$colon(xs);
/*     */   }
/*     */   
/*     */   public static void insert(Buffer $this, int n, Seq elems) {
/* 166 */     $this.insertAll(n, (Traversable)elems);
/*     */   }
/*     */   
/*     */   public static void trimStart(Buffer $this, int n) {
/* 173 */     $this.remove(0, n);
/*     */   }
/*     */   
/*     */   public static void trimEnd(Buffer $this, int n) {
/* 180 */     int i = $this.length() - n;
/* 180 */     Predef$ predef$ = Predef$.MODULE$;
/* 180 */     $this.remove(RichInt$.MODULE$.max$extension(i, 0), n);
/*     */   }
/*     */   
/*     */   public static void $less$less(Buffer $this, Message cmd) {
/*     */     // Byte code:
/*     */     //   0: iconst_0
/*     */     //   1: istore #5
/*     */     //   3: aconst_null
/*     */     //   4: astore #7
/*     */     //   6: iconst_0
/*     */     //   7: istore #10
/*     */     //   9: aconst_null
/*     */     //   10: astore #12
/*     */     //   12: iconst_0
/*     */     //   13: istore #22
/*     */     //   15: aconst_null
/*     */     //   16: astore #24
/*     */     //   18: aload_1
/*     */     //   19: instanceof scala/collection/script/Include
/*     */     //   22: ifeq -> 89
/*     */     //   25: iconst_1
/*     */     //   26: istore #5
/*     */     //   28: aload_1
/*     */     //   29: checkcast scala/collection/script/Include
/*     */     //   32: astore #7
/*     */     //   34: getstatic scala/collection/script/Start$.MODULE$ : Lscala/collection/script/Start$;
/*     */     //   37: aload #7
/*     */     //   39: invokevirtual location : ()Lscala/collection/script/Location;
/*     */     //   42: astore_2
/*     */     //   43: dup
/*     */     //   44: ifnonnull -> 55
/*     */     //   47: pop
/*     */     //   48: aload_2
/*     */     //   49: ifnull -> 62
/*     */     //   52: goto -> 89
/*     */     //   55: aload_2
/*     */     //   56: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   59: ifeq -> 89
/*     */     //   62: aload_0
/*     */     //   63: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   66: iconst_1
/*     */     //   67: anewarray java/lang/Object
/*     */     //   70: dup
/*     */     //   71: iconst_0
/*     */     //   72: aload #7
/*     */     //   74: invokevirtual elem : ()Ljava/lang/Object;
/*     */     //   77: aastore
/*     */     //   78: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   81: invokeinterface prepend : (Lscala/collection/Seq;)V
/*     */     //   86: goto -> 955
/*     */     //   89: iload #5
/*     */     //   91: ifeq -> 149
/*     */     //   94: getstatic scala/collection/script/End$.MODULE$ : Lscala/collection/script/End$;
/*     */     //   97: aload #7
/*     */     //   99: invokevirtual location : ()Lscala/collection/script/Location;
/*     */     //   102: astore_3
/*     */     //   103: dup
/*     */     //   104: ifnonnull -> 115
/*     */     //   107: pop
/*     */     //   108: aload_3
/*     */     //   109: ifnull -> 122
/*     */     //   112: goto -> 149
/*     */     //   115: aload_3
/*     */     //   116: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   119: ifeq -> 149
/*     */     //   122: aload_0
/*     */     //   123: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   126: iconst_1
/*     */     //   127: anewarray java/lang/Object
/*     */     //   130: dup
/*     */     //   131: iconst_0
/*     */     //   132: aload #7
/*     */     //   134: invokevirtual elem : ()Ljava/lang/Object;
/*     */     //   137: aastore
/*     */     //   138: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   141: invokeinterface append : (Lscala/collection/Seq;)V
/*     */     //   146: goto -> 955
/*     */     //   149: iload #5
/*     */     //   151: ifeq -> 207
/*     */     //   154: aload #7
/*     */     //   156: invokevirtual location : ()Lscala/collection/script/Location;
/*     */     //   159: instanceof scala/collection/script/Index
/*     */     //   162: ifeq -> 207
/*     */     //   165: aload #7
/*     */     //   167: invokevirtual location : ()Lscala/collection/script/Location;
/*     */     //   170: checkcast scala/collection/script/Index
/*     */     //   173: astore #4
/*     */     //   175: aload_0
/*     */     //   176: aload #4
/*     */     //   178: invokevirtual n : ()I
/*     */     //   181: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   184: iconst_1
/*     */     //   185: anewarray java/lang/Object
/*     */     //   188: dup
/*     */     //   189: iconst_0
/*     */     //   190: aload #7
/*     */     //   192: invokevirtual elem : ()Ljava/lang/Object;
/*     */     //   195: aastore
/*     */     //   196: invokevirtual genericWrapArray : (Ljava/lang/Object;)Lscala/collection/mutable/WrappedArray;
/*     */     //   199: invokeinterface insert : (ILscala/collection/Seq;)V
/*     */     //   204: goto -> 955
/*     */     //   207: iload #5
/*     */     //   209: ifeq -> 258
/*     */     //   212: getstatic scala/collection/script/NoLo$.MODULE$ : Lscala/collection/script/NoLo$;
/*     */     //   215: aload #7
/*     */     //   217: invokevirtual location : ()Lscala/collection/script/Location;
/*     */     //   220: astore #6
/*     */     //   222: dup
/*     */     //   223: ifnonnull -> 235
/*     */     //   226: pop
/*     */     //   227: aload #6
/*     */     //   229: ifnull -> 243
/*     */     //   232: goto -> 258
/*     */     //   235: aload #6
/*     */     //   237: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   240: ifeq -> 258
/*     */     //   243: aload_0
/*     */     //   244: aload #7
/*     */     //   246: invokevirtual elem : ()Ljava/lang/Object;
/*     */     //   249: invokeinterface $plus$eq : (Ljava/lang/Object;)Lscala/collection/mutable/Buffer;
/*     */     //   254: pop
/*     */     //   255: goto -> 955
/*     */     //   258: aload_1
/*     */     //   259: instanceof scala/collection/script/Update
/*     */     //   262: ifeq -> 320
/*     */     //   265: iconst_1
/*     */     //   266: istore #10
/*     */     //   268: aload_1
/*     */     //   269: checkcast scala/collection/script/Update
/*     */     //   272: astore #12
/*     */     //   274: getstatic scala/collection/script/Start$.MODULE$ : Lscala/collection/script/Start$;
/*     */     //   277: aload #12
/*     */     //   279: invokevirtual location : ()Lscala/collection/script/Location;
/*     */     //   282: astore #8
/*     */     //   284: dup
/*     */     //   285: ifnonnull -> 297
/*     */     //   288: pop
/*     */     //   289: aload #8
/*     */     //   291: ifnull -> 305
/*     */     //   294: goto -> 320
/*     */     //   297: aload #8
/*     */     //   299: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   302: ifeq -> 320
/*     */     //   305: aload_0
/*     */     //   306: iconst_0
/*     */     //   307: aload #12
/*     */     //   309: invokevirtual elem : ()Ljava/lang/Object;
/*     */     //   312: invokeinterface update : (ILjava/lang/Object;)V
/*     */     //   317: goto -> 955
/*     */     //   320: iload #10
/*     */     //   322: ifeq -> 378
/*     */     //   325: getstatic scala/collection/script/End$.MODULE$ : Lscala/collection/script/End$;
/*     */     //   328: aload #12
/*     */     //   330: invokevirtual location : ()Lscala/collection/script/Location;
/*     */     //   333: astore #9
/*     */     //   335: dup
/*     */     //   336: ifnonnull -> 348
/*     */     //   339: pop
/*     */     //   340: aload #9
/*     */     //   342: ifnull -> 356
/*     */     //   345: goto -> 378
/*     */     //   348: aload #9
/*     */     //   350: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   353: ifeq -> 378
/*     */     //   356: aload_0
/*     */     //   357: aload_0
/*     */     //   358: invokeinterface length : ()I
/*     */     //   363: iconst_1
/*     */     //   364: isub
/*     */     //   365: aload #12
/*     */     //   367: invokevirtual elem : ()Ljava/lang/Object;
/*     */     //   370: invokeinterface update : (ILjava/lang/Object;)V
/*     */     //   375: goto -> 955
/*     */     //   378: iload #10
/*     */     //   380: ifeq -> 423
/*     */     //   383: aload #12
/*     */     //   385: invokevirtual location : ()Lscala/collection/script/Location;
/*     */     //   388: instanceof scala/collection/script/Index
/*     */     //   391: ifeq -> 423
/*     */     //   394: aload #12
/*     */     //   396: invokevirtual location : ()Lscala/collection/script/Location;
/*     */     //   399: checkcast scala/collection/script/Index
/*     */     //   402: astore #11
/*     */     //   404: aload_0
/*     */     //   405: aload #11
/*     */     //   407: invokevirtual n : ()I
/*     */     //   410: aload #12
/*     */     //   412: invokevirtual elem : ()Ljava/lang/Object;
/*     */     //   415: invokeinterface update : (ILjava/lang/Object;)V
/*     */     //   420: goto -> 955
/*     */     //   423: aload_1
/*     */     //   424: instanceof scala/collection/script/Remove
/*     */     //   427: ifeq -> 571
/*     */     //   430: iconst_1
/*     */     //   431: istore #22
/*     */     //   433: aload_1
/*     */     //   434: checkcast scala/collection/script/Remove
/*     */     //   437: astore #24
/*     */     //   439: getstatic scala/collection/script/Start$.MODULE$ : Lscala/collection/script/Start$;
/*     */     //   442: aload #24
/*     */     //   444: invokevirtual location : ()Lscala/collection/script/Location;
/*     */     //   447: astore #13
/*     */     //   449: dup
/*     */     //   450: ifnonnull -> 462
/*     */     //   453: pop
/*     */     //   454: aload #13
/*     */     //   456: ifnull -> 470
/*     */     //   459: goto -> 571
/*     */     //   462: aload #13
/*     */     //   464: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   467: ifeq -> 571
/*     */     //   470: aload_0
/*     */     //   471: iconst_0
/*     */     //   472: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */     //   475: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   480: aload #24
/*     */     //   482: invokevirtual elem : ()Ljava/lang/Object;
/*     */     //   485: astore #15
/*     */     //   487: dup
/*     */     //   488: astore #14
/*     */     //   490: aload #15
/*     */     //   492: if_acmpne -> 499
/*     */     //   495: iconst_1
/*     */     //   496: goto -> 557
/*     */     //   499: aload #14
/*     */     //   501: ifnonnull -> 508
/*     */     //   504: iconst_0
/*     */     //   505: goto -> 557
/*     */     //   508: aload #14
/*     */     //   510: instanceof java/lang/Number
/*     */     //   513: ifeq -> 529
/*     */     //   516: aload #14
/*     */     //   518: checkcast java/lang/Number
/*     */     //   521: aload #15
/*     */     //   523: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   526: goto -> 557
/*     */     //   529: aload #14
/*     */     //   531: instanceof java/lang/Character
/*     */     //   534: ifeq -> 550
/*     */     //   537: aload #14
/*     */     //   539: checkcast java/lang/Character
/*     */     //   542: aload #15
/*     */     //   544: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   547: goto -> 557
/*     */     //   550: aload #14
/*     */     //   552: aload #15
/*     */     //   554: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   557: ifeq -> 955
/*     */     //   560: aload_0
/*     */     //   561: iconst_0
/*     */     //   562: invokeinterface remove : (I)Ljava/lang/Object;
/*     */     //   567: pop
/*     */     //   568: goto -> 955
/*     */     //   571: iload #22
/*     */     //   573: ifeq -> 722
/*     */     //   576: getstatic scala/collection/script/End$.MODULE$ : Lscala/collection/script/End$;
/*     */     //   579: aload #24
/*     */     //   581: invokevirtual location : ()Lscala/collection/script/Location;
/*     */     //   584: astore #16
/*     */     //   586: dup
/*     */     //   587: ifnonnull -> 599
/*     */     //   590: pop
/*     */     //   591: aload #16
/*     */     //   593: ifnull -> 607
/*     */     //   596: goto -> 722
/*     */     //   599: aload #16
/*     */     //   601: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   604: ifeq -> 722
/*     */     //   607: aload_0
/*     */     //   608: aload_0
/*     */     //   609: invokeinterface length : ()I
/*     */     //   614: iconst_1
/*     */     //   615: isub
/*     */     //   616: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */     //   619: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   624: aload #24
/*     */     //   626: invokevirtual elem : ()Ljava/lang/Object;
/*     */     //   629: astore #18
/*     */     //   631: dup
/*     */     //   632: astore #17
/*     */     //   634: aload #18
/*     */     //   636: if_acmpne -> 643
/*     */     //   639: iconst_1
/*     */     //   640: goto -> 701
/*     */     //   643: aload #17
/*     */     //   645: ifnonnull -> 652
/*     */     //   648: iconst_0
/*     */     //   649: goto -> 701
/*     */     //   652: aload #17
/*     */     //   654: instanceof java/lang/Number
/*     */     //   657: ifeq -> 673
/*     */     //   660: aload #17
/*     */     //   662: checkcast java/lang/Number
/*     */     //   665: aload #18
/*     */     //   667: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   670: goto -> 701
/*     */     //   673: aload #17
/*     */     //   675: instanceof java/lang/Character
/*     */     //   678: ifeq -> 694
/*     */     //   681: aload #17
/*     */     //   683: checkcast java/lang/Character
/*     */     //   686: aload #18
/*     */     //   688: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   691: goto -> 701
/*     */     //   694: aload #17
/*     */     //   696: aload #18
/*     */     //   698: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   701: ifeq -> 955
/*     */     //   704: aload_0
/*     */     //   705: aload_0
/*     */     //   706: invokeinterface length : ()I
/*     */     //   711: iconst_1
/*     */     //   712: isub
/*     */     //   713: invokeinterface remove : (I)Ljava/lang/Object;
/*     */     //   718: pop
/*     */     //   719: goto -> 955
/*     */     //   722: iload #22
/*     */     //   724: ifeq -> 857
/*     */     //   727: aload #24
/*     */     //   729: invokevirtual location : ()Lscala/collection/script/Location;
/*     */     //   732: instanceof scala/collection/script/Index
/*     */     //   735: ifeq -> 857
/*     */     //   738: aload #24
/*     */     //   740: invokevirtual location : ()Lscala/collection/script/Location;
/*     */     //   743: checkcast scala/collection/script/Index
/*     */     //   746: astore #21
/*     */     //   748: aload_0
/*     */     //   749: aload #21
/*     */     //   751: invokevirtual n : ()I
/*     */     //   754: invokestatic boxToInteger : (I)Ljava/lang/Integer;
/*     */     //   757: invokeinterface apply : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */     //   762: aload #24
/*     */     //   764: invokevirtual elem : ()Ljava/lang/Object;
/*     */     //   767: astore #20
/*     */     //   769: dup
/*     */     //   770: astore #19
/*     */     //   772: aload #20
/*     */     //   774: if_acmpne -> 781
/*     */     //   777: iconst_1
/*     */     //   778: goto -> 839
/*     */     //   781: aload #19
/*     */     //   783: ifnonnull -> 790
/*     */     //   786: iconst_0
/*     */     //   787: goto -> 839
/*     */     //   790: aload #19
/*     */     //   792: instanceof java/lang/Number
/*     */     //   795: ifeq -> 811
/*     */     //   798: aload #19
/*     */     //   800: checkcast java/lang/Number
/*     */     //   803: aload #20
/*     */     //   805: invokestatic equalsNumObject : (Ljava/lang/Number;Ljava/lang/Object;)Z
/*     */     //   808: goto -> 839
/*     */     //   811: aload #19
/*     */     //   813: instanceof java/lang/Character
/*     */     //   816: ifeq -> 832
/*     */     //   819: aload #19
/*     */     //   821: checkcast java/lang/Character
/*     */     //   824: aload #20
/*     */     //   826: invokestatic equalsCharObject : (Ljava/lang/Character;Ljava/lang/Object;)Z
/*     */     //   829: goto -> 839
/*     */     //   832: aload #19
/*     */     //   834: aload #20
/*     */     //   836: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   839: ifeq -> 955
/*     */     //   842: aload_0
/*     */     //   843: aload #21
/*     */     //   845: invokevirtual n : ()I
/*     */     //   848: invokeinterface remove : (I)Ljava/lang/Object;
/*     */     //   853: pop
/*     */     //   854: goto -> 955
/*     */     //   857: iload #22
/*     */     //   859: ifeq -> 908
/*     */     //   862: getstatic scala/collection/script/NoLo$.MODULE$ : Lscala/collection/script/NoLo$;
/*     */     //   865: aload #24
/*     */     //   867: invokevirtual location : ()Lscala/collection/script/Location;
/*     */     //   870: astore #23
/*     */     //   872: dup
/*     */     //   873: ifnonnull -> 885
/*     */     //   876: pop
/*     */     //   877: aload #23
/*     */     //   879: ifnull -> 893
/*     */     //   882: goto -> 908
/*     */     //   885: aload #23
/*     */     //   887: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   890: ifeq -> 908
/*     */     //   893: aload_0
/*     */     //   894: aload #24
/*     */     //   896: invokevirtual elem : ()Ljava/lang/Object;
/*     */     //   899: invokeinterface $minus$eq : (Ljava/lang/Object;)Lscala/collection/mutable/Buffer;
/*     */     //   904: pop
/*     */     //   905: goto -> 955
/*     */     //   908: aload_1
/*     */     //   909: instanceof scala/collection/script/Reset
/*     */     //   912: ifeq -> 924
/*     */     //   915: aload_0
/*     */     //   916: invokeinterface clear : ()V
/*     */     //   921: goto -> 955
/*     */     //   924: aload_1
/*     */     //   925: instanceof scala/collection/script/Script
/*     */     //   928: ifeq -> 956
/*     */     //   931: aload_1
/*     */     //   932: checkcast scala/collection/script/Script
/*     */     //   935: astore #25
/*     */     //   937: aload #25
/*     */     //   939: invokevirtual iterator : ()Lscala/collection/Iterator;
/*     */     //   942: new scala/collection/mutable/BufferLike$$anonfun$$less$less$1
/*     */     //   945: dup
/*     */     //   946: aload_0
/*     */     //   947: invokespecial <init> : (Lscala/collection/mutable/Buffer;)V
/*     */     //   950: invokeinterface foreach : (Lscala/Function1;)V
/*     */     //   955: return
/*     */     //   956: new java/lang/UnsupportedOperationException
/*     */     //   959: dup
/*     */     //   960: new scala/collection/mutable/StringBuilder
/*     */     //   963: dup
/*     */     //   964: invokespecial <init> : ()V
/*     */     //   967: ldc 'message '
/*     */     //   969: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   972: aload_1
/*     */     //   973: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   976: ldc ' not understood'
/*     */     //   978: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   981: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   984: invokespecial <init> : (Ljava/lang/String;)V
/*     */     //   987: athrow
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #187	-> 0
/*     */     //   #192	-> 6
/*     */     //   #196	-> 12
/*     */     //   #187	-> 18
/*     */     //   #186	-> 18
/*     */     //   #186	-> 37
/*     */     //   #187	-> 39
/*     */     //   #186	-> 72
/*     */     //   #187	-> 74
/*     */     //   #186	-> 89
/*     */     //   #188	-> 94
/*     */     //   #186	-> 97
/*     */     //   #188	-> 99
/*     */     //   #186	-> 132
/*     */     //   #188	-> 134
/*     */     //   #186	-> 149
/*     */     //   #189	-> 156
/*     */     //   #186	-> 165
/*     */     //   #189	-> 167
/*     */     //   #186	-> 176
/*     */     //   #189	-> 178
/*     */     //   #186	-> 190
/*     */     //   #189	-> 192
/*     */     //   #186	-> 207
/*     */     //   #190	-> 212
/*     */     //   #186	-> 215
/*     */     //   #190	-> 217
/*     */     //   #186	-> 244
/*     */     //   #190	-> 246
/*     */     //   #192	-> 258
/*     */     //   #186	-> 277
/*     */     //   #192	-> 279
/*     */     //   #186	-> 307
/*     */     //   #192	-> 309
/*     */     //   #186	-> 320
/*     */     //   #193	-> 325
/*     */     //   #186	-> 328
/*     */     //   #193	-> 330
/*     */     //   #186	-> 365
/*     */     //   #193	-> 367
/*     */     //   #186	-> 378
/*     */     //   #194	-> 385
/*     */     //   #186	-> 394
/*     */     //   #194	-> 396
/*     */     //   #186	-> 405
/*     */     //   #194	-> 407
/*     */     //   #186	-> 410
/*     */     //   #194	-> 412
/*     */     //   #196	-> 423
/*     */     //   #186	-> 442
/*     */     //   #196	-> 444
/*     */     //   #186	-> 480
/*     */     //   #196	-> 482
/*     */     //   #186	-> 571
/*     */     //   #197	-> 576
/*     */     //   #186	-> 579
/*     */     //   #197	-> 581
/*     */     //   #186	-> 624
/*     */     //   #197	-> 626
/*     */     //   #186	-> 722
/*     */     //   #198	-> 729
/*     */     //   #186	-> 738
/*     */     //   #198	-> 740
/*     */     //   #186	-> 749
/*     */     //   #198	-> 751
/*     */     //   #186	-> 762
/*     */     //   #198	-> 764
/*     */     //   #186	-> 843
/*     */     //   #198	-> 845
/*     */     //   #186	-> 857
/*     */     //   #199	-> 862
/*     */     //   #186	-> 865
/*     */     //   #199	-> 867
/*     */     //   #186	-> 894
/*     */     //   #199	-> 896
/*     */     //   #201	-> 908
/*     */     //   #202	-> 924
/*     */     //   #186	-> 955
/*     */     //   #203	-> 956
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	988	0	$this	Lscala/collection/mutable/Buffer;
/*     */     //   0	988	1	cmd	Lscala/collection/script/Message;
/*     */   }
/*     */   
/*     */   public static String stringPrefix(Buffer $this) {
/* 210 */     return "Buffer";
/*     */   }
/*     */   
/*     */   public static Seq readOnly(Buffer $this) {
/* 215 */     return $this.toSeq();
/*     */   }
/*     */   
/*     */   public static Buffer $plus$plus(Buffer $this, GenTraversableOnce xs) {
/* 224 */     return (Buffer)$this.clone().$plus$plus$eq(xs.seq());
/*     */   }
/*     */   
/*     */   public static Buffer $minus(Buffer<Object> $this, Object elem) {
/* 232 */     return $this.clone().$minus$eq(elem);
/*     */   }
/*     */   
/*     */   public static Buffer $minus(Buffer<Object> $this, Object elem1, Object elem2, Seq elems) {
/* 244 */     return (Buffer)$this.clone().$minus$eq(elem1).$minus$eq(elem2).$minus$minus$eq((TraversableOnce)elems);
/*     */   }
/*     */   
/*     */   public static Buffer $minus$minus(Buffer $this, GenTraversableOnce xs) {
/* 254 */     return (Buffer)$this.clone().$minus$minus$eq(xs.seq());
/*     */   }
/*     */   
/*     */   public static Buffer clone(Buffer $this) {
/* 261 */     Builder bf = $this.newBuilder();
/* 262 */     bf.$plus$plus$eq((TraversableOnce)$this);
/* 263 */     return (Buffer)bf.result();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\BufferLike$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */