/*    */ package scala.reflect;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ public final class NameTransformer$ {
/*    */   public static final NameTransformer$ MODULE$;
/*    */   
/*    */   private final String MODULE_SUFFIX_STRING;
/*    */   
/*    */   private final String NAME_JOIN_STRING;
/*    */   
/*    */   private final String MODULE_INSTANCE_NAME;
/*    */   
/*    */   private final int nops;
/*    */   
/*    */   private final int ncodes;
/*    */   
/*    */   private final String[] op2code;
/*    */   
/*    */   private final NameTransformer.OpCodes[] code2op;
/*    */   
/*    */   private NameTransformer$() {
/* 15 */     MODULE$ = this;
/* 18 */     this.MODULE_SUFFIX_STRING = (String)scala.sys.package$.MODULE$.props().getOrElse("SCALA_MODULE_SUFFIX_STRING", (Function0)new NameTransformer.$anonfun$1());
/* 19 */     this.NAME_JOIN_STRING = (String)scala.sys.package$.MODULE$.props().getOrElse("SCALA_NAME_JOIN_STRING", (Function0)new NameTransformer.$anonfun$2());
/* 20 */     this.MODULE_INSTANCE_NAME = "MODULE$";
/* 22 */     this.nops = 128;
/* 23 */     this.ncodes = 676;
/* 27 */     this.op2code = new String[nops()];
/* 28 */     this.code2op = new NameTransformer.OpCodes[ncodes()];
/* 36 */     enterOp('~', "$tilde");
/* 37 */     enterOp('=', "$eq");
/* 38 */     enterOp('<', "$less");
/* 39 */     enterOp('>', "$greater");
/* 40 */     enterOp('!', "$bang");
/* 41 */     enterOp('#', "$hash");
/* 42 */     enterOp('%', "$percent");
/* 43 */     enterOp('^', "$up");
/* 44 */     enterOp('&', "$amp");
/* 45 */     enterOp('|', "$bar");
/* 46 */     enterOp('*', "$times");
/* 47 */     enterOp('/', "$div");
/* 48 */     enterOp('+', "$plus");
/* 49 */     enterOp('-', "$minus");
/* 50 */     enterOp(':', "$colon");
/* 51 */     enterOp('\\', "$bslash");
/* 52 */     enterOp('?', "$qmark");
/* 53 */     enterOp('@', "$at");
/*    */   }
/*    */   
/*    */   public String MODULE_SUFFIX_STRING() {
/*    */     return this.MODULE_SUFFIX_STRING;
/*    */   }
/*    */   
/*    */   public static class $anonfun$1 extends AbstractFunction0<String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply() {
/*    */       return "$";
/*    */     }
/*    */   }
/*    */   
/*    */   public String NAME_JOIN_STRING() {
/*    */     return this.NAME_JOIN_STRING;
/*    */   }
/*    */   
/*    */   public static class $anonfun$2 extends AbstractFunction0<String> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final String apply() {
/*    */       return "$";
/*    */     }
/*    */   }
/*    */   
/*    */   public String MODULE_INSTANCE_NAME() {
/*    */     return this.MODULE_INSTANCE_NAME;
/*    */   }
/*    */   
/*    */   private int nops() {
/*    */     return this.nops;
/*    */   }
/*    */   
/*    */   private int ncodes() {
/*    */     return this.ncodes;
/*    */   }
/*    */   
/*    */   private String[] op2code() {
/*    */     return this.op2code;
/*    */   }
/*    */   
/*    */   private NameTransformer.OpCodes[] code2op() {
/*    */     return this.code2op;
/*    */   }
/*    */   
/*    */   private void enterOp(char op, String code) {
/*    */     op2code()[op] = code;
/*    */     int c = (code.charAt(1) - 97) * 26 + code.charAt(2) - 97;
/*    */     code2op()[c] = new NameTransformer.OpCodes(op, code, code2op()[c]);
/*    */   }
/*    */   
/*    */   public String encode(String name) {
/* 61 */     StringBuilder buf = null;
/* 62 */     int len = name.length();
/* 63 */     int i = 0;
/* 64 */     while (i < len) {
/* 65 */       char c = name.charAt(i);
/* 67 */       (buf == null) ? (
/* 68 */         buf = new StringBuilder())
/* 69 */         .append(name.substring(0, i)) : BoxedUnit.UNIT;
/* 75 */       (buf == null) ? (
/* 76 */         buf = new StringBuilder())
/* 77 */         .append(name.substring(0, i)) : BoxedUnit.UNIT;
/* 79 */       scala.Predef$ predef$ = scala.Predef$.MODULE$;
/* 81 */       (c < nops() && op2code()[c] != null) ? buf.append(op2code()[c]) : (Character.isJavaIdentifierPart(c) ? ((buf != null) ? 
/* 82 */         buf.append(c) : BoxedUnit.UNIT) : buf.append((new StringOps("$u%04X")).format((Seq)scala.Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(c) }))));
/* 84 */       i++;
/*    */     } 
/* 86 */     return (buf == null) ? name : buf.toString();
/*    */   }
/*    */   
/*    */   public String decode(String name0) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: ldc '<init>'
/*    */     //   3: invokevirtual endsWith : (Ljava/lang/String;)Z
/*    */     //   6: ifeq -> 45
/*    */     //   9: new scala/collection/mutable/StringBuilder
/*    */     //   12: dup
/*    */     //   13: invokespecial <init> : ()V
/*    */     //   16: aload_1
/*    */     //   17: iconst_0
/*    */     //   18: aload_1
/*    */     //   19: invokevirtual length : ()I
/*    */     //   22: ldc '<init>'
/*    */     //   24: invokevirtual length : ()I
/*    */     //   27: isub
/*    */     //   28: invokevirtual substring : (II)Ljava/lang/String;
/*    */     //   31: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*    */     //   34: ldc 'this'
/*    */     //   36: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*    */     //   39: invokevirtual toString : ()Ljava/lang/String;
/*    */     //   42: goto -> 46
/*    */     //   45: aload_1
/*    */     //   46: astore_2
/*    */     //   47: aconst_null
/*    */     //   48: astore_3
/*    */     //   49: aload_2
/*    */     //   50: invokevirtual length : ()I
/*    */     //   53: istore #4
/*    */     //   55: iconst_0
/*    */     //   56: istore #5
/*    */     //   58: goto -> 95
/*    */     //   61: pop
/*    */     //   62: aload #6
/*    */     //   64: ifnonnull -> 95
/*    */     //   67: iload #7
/*    */     //   69: ifne -> 95
/*    */     //   72: aload_3
/*    */     //   73: ifnull -> 85
/*    */     //   76: aload_3
/*    */     //   77: iload #8
/*    */     //   79: invokevirtual append : (C)Lscala/collection/mutable/StringBuilder;
/*    */     //   82: goto -> 88
/*    */     //   85: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */     //   88: pop
/*    */     //   89: iload #5
/*    */     //   91: iconst_1
/*    */     //   92: iadd
/*    */     //   93: istore #5
/*    */     //   95: iload #5
/*    */     //   97: iload #4
/*    */     //   99: if_icmpge -> 405
/*    */     //   102: aconst_null
/*    */     //   103: astore #6
/*    */     //   105: iconst_0
/*    */     //   106: istore #7
/*    */     //   108: aload_2
/*    */     //   109: iload #5
/*    */     //   111: invokevirtual charAt : (I)C
/*    */     //   114: istore #8
/*    */     //   116: iload #8
/*    */     //   118: bipush #36
/*    */     //   120: if_icmpne -> 62
/*    */     //   123: iload #5
/*    */     //   125: iconst_2
/*    */     //   126: iadd
/*    */     //   127: iload #4
/*    */     //   129: if_icmpge -> 62
/*    */     //   132: aload_2
/*    */     //   133: iload #5
/*    */     //   135: iconst_1
/*    */     //   136: iadd
/*    */     //   137: invokevirtual charAt : (I)C
/*    */     //   140: istore #9
/*    */     //   142: bipush #97
/*    */     //   144: iload #9
/*    */     //   146: if_icmpgt -> 62
/*    */     //   149: iload #9
/*    */     //   151: bipush #122
/*    */     //   153: if_icmpgt -> 62
/*    */     //   156: aload_2
/*    */     //   157: iload #5
/*    */     //   159: iconst_2
/*    */     //   160: iadd
/*    */     //   161: invokevirtual charAt : (I)C
/*    */     //   164: istore #10
/*    */     //   166: bipush #97
/*    */     //   168: iload #10
/*    */     //   170: if_icmpgt -> 291
/*    */     //   173: iload #10
/*    */     //   175: bipush #122
/*    */     //   177: if_icmpgt -> 291
/*    */     //   180: aload_0
/*    */     //   181: invokespecial code2op : ()[Lscala/reflect/NameTransformer$OpCodes;
/*    */     //   184: iload #9
/*    */     //   186: bipush #97
/*    */     //   188: isub
/*    */     //   189: bipush #26
/*    */     //   191: imul
/*    */     //   192: iload #10
/*    */     //   194: iadd
/*    */     //   195: bipush #97
/*    */     //   197: isub
/*    */     //   198: aaload
/*    */     //   199: astore #6
/*    */     //   201: aload #6
/*    */     //   203: ifnull -> 230
/*    */     //   206: aload_2
/*    */     //   207: aload #6
/*    */     //   209: invokevirtual code : ()Ljava/lang/String;
/*    */     //   212: iload #5
/*    */     //   214: invokevirtual startsWith : (Ljava/lang/String;I)Z
/*    */     //   217: ifne -> 230
/*    */     //   220: aload #6
/*    */     //   222: invokevirtual next : ()Lscala/reflect/NameTransformer$OpCodes;
/*    */     //   225: astore #6
/*    */     //   227: goto -> 201
/*    */     //   230: aload #6
/*    */     //   232: ifnull -> 62
/*    */     //   235: aload_3
/*    */     //   236: ifnonnull -> 261
/*    */     //   239: new scala/collection/mutable/StringBuilder
/*    */     //   242: dup
/*    */     //   243: invokespecial <init> : ()V
/*    */     //   246: dup
/*    */     //   247: astore_3
/*    */     //   248: aload_2
/*    */     //   249: iconst_0
/*    */     //   250: iload #5
/*    */     //   252: invokevirtual substring : (II)Ljava/lang/String;
/*    */     //   255: invokevirtual append : (Ljava/lang/String;)Lscala/collection/mutable/StringBuilder;
/*    */     //   258: goto -> 264
/*    */     //   261: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */     //   264: pop
/*    */     //   265: aload_3
/*    */     //   266: aload #6
/*    */     //   268: invokevirtual op : ()C
/*    */     //   271: invokevirtual append : (C)Lscala/collection/mutable/StringBuilder;
/*    */     //   274: pop
/*    */     //   275: iload #5
/*    */     //   277: aload #6
/*    */     //   279: invokevirtual code : ()Ljava/lang/String;
/*    */     //   282: invokevirtual length : ()I
/*    */     //   285: iadd
/*    */     //   286: istore #5
/*    */     //   288: goto -> 62
/*    */     //   291: iload #4
/*    */     //   293: iload #5
/*    */     //   295: isub
/*    */     //   296: bipush #6
/*    */     //   298: if_icmplt -> 62
/*    */     //   301: iload #9
/*    */     //   303: bipush #117
/*    */     //   305: if_icmpne -> 62
/*    */     //   308: iload #10
/*    */     //   310: invokestatic isDigit : (C)Z
/*    */     //   313: ifne -> 330
/*    */     //   316: bipush #65
/*    */     //   318: iload #10
/*    */     //   320: if_icmpgt -> 62
/*    */     //   323: iload #10
/*    */     //   325: bipush #70
/*    */     //   327: if_icmpgt -> 62
/*    */     //   330: aload_2
/*    */     //   331: iload #5
/*    */     //   333: iconst_2
/*    */     //   334: iadd
/*    */     //   335: iload #5
/*    */     //   337: bipush #6
/*    */     //   339: iadd
/*    */     //   340: invokevirtual substring : (II)Ljava/lang/String;
/*    */     //   343: astore #11
/*    */     //   345: aload #11
/*    */     //   347: bipush #16
/*    */     //   349: invokestatic parseInt : (Ljava/lang/String;I)I
/*    */     //   352: i2c
/*    */     //   353: istore #12
/*    */     //   355: aload_3
/*    */     //   356: ifnonnull -> 381
/*    */     //   359: new scala/collection/mutable/StringBuilder
/*    */     //   362: dup
/*    */     //   363: invokespecial <init> : ()V
/*    */     //   366: dup
/*    */     //   367: astore_3
/*    */     //   368: aload_2
/*    */     //   369: iconst_0
/*    */     //   370: iload #5
/*    */     //   372: invokevirtual substring : (II)Ljava/lang/String;
/*    */     //   375: invokevirtual append : (Ljava/lang/String;)Lscala/collection/mutable/StringBuilder;
/*    */     //   378: goto -> 384
/*    */     //   381: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*    */     //   384: pop
/*    */     //   385: aload_3
/*    */     //   386: iload #12
/*    */     //   388: invokevirtual append : (C)Lscala/collection/mutable/StringBuilder;
/*    */     //   391: pop
/*    */     //   392: iload #5
/*    */     //   394: bipush #6
/*    */     //   396: iadd
/*    */     //   397: istore #5
/*    */     //   399: iconst_1
/*    */     //   400: istore #7
/*    */     //   402: goto -> 62
/*    */     //   405: aload_3
/*    */     //   406: ifnonnull -> 413
/*    */     //   409: aload_2
/*    */     //   410: goto -> 417
/*    */     //   413: aload_3
/*    */     //   414: invokevirtual toString : ()Ljava/lang/String;
/*    */     //   417: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #96	-> 0
/*    */     //   #97	-> 45
/*    */     //   #96	-> 46
/*    */     //   #98	-> 48
/*    */     //   #99	-> 49
/*    */     //   #100	-> 55
/*    */     //   #101	-> 58
/*    */     //   #128	-> 61
/*    */     //   #149	-> 62
/*    */     //   #150	-> 72
/*    */     //   #151	-> 76
/*    */     //   #150	-> 85
/*    */     //   #152	-> 89
/*    */     //   #101	-> 95
/*    */     //   #102	-> 103
/*    */     //   #103	-> 105
/*    */     //   #104	-> 108
/*    */     //   #105	-> 116
/*    */     //   #106	-> 132
/*    */     //   #107	-> 142
/*    */     //   #108	-> 156
/*    */     //   #109	-> 166
/*    */     //   #110	-> 180
/*    */     //   #111	-> 201
/*    */     //   #112	-> 230
/*    */     //   #113	-> 235
/*    */     //   #114	-> 239
/*    */     //   #115	-> 248
/*    */     //   #113	-> 261
/*    */     //   #117	-> 265
/*    */     //   #118	-> 275
/*    */     //   #122	-> 291
/*    */     //   #123	-> 301
/*    */     //   #124	-> 308
/*    */     //   #125	-> 316
/*    */     //   #127	-> 330
/*    */     //   #129	-> 345
/*    */     //   #130	-> 355
/*    */     //   #131	-> 359
/*    */     //   #132	-> 368
/*    */     //   #130	-> 381
/*    */     //   #134	-> 385
/*    */     //   #136	-> 392
/*    */     //   #137	-> 399
/*    */     //   #156	-> 405
/*    */     //   #94	-> 417
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	418	0	this	Lscala/reflect/NameTransformer$;
/*    */     //   0	418	1	name0	Ljava/lang/String;
/*    */     //   47	371	2	name	Ljava/lang/String;
/*    */     //   49	369	3	buf	Lscala/collection/mutable/StringBuilder;
/*    */     //   55	363	4	len	I
/*    */     //   58	360	5	i	I
/*    */     //   105	313	6	ops	Lscala/reflect/NameTransformer$OpCodes;
/*    */     //   108	310	7	unicode	Z
/*    */     //   116	302	8	c	C
/*    */     //   142	276	9	ch1	C
/*    */     //   166	252	10	ch2	C
/*    */     //   345	73	11	hex	Ljava/lang/String;
/*    */     //   355	47	12	str	C
/*    */     // Exception table:
/*    */     //   from	to	target	type
/*    */     //   345	405	61	java/lang/NumberFormatException
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\NameTransformer$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */