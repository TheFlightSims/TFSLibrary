/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.reflect.ClassTag;
/*    */ 
/*    */ public final class ArrayBuilder$ implements Serializable {
/*    */   public static final ArrayBuilder$ MODULE$;
/*    */   
/*    */   private ArrayBuilder$() {
/* 30 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   private Object readResolve() {
/* 30 */     return MODULE$;
/*    */   }
/*    */   
/*    */   public <T> ArrayBuilder<T> make(ClassTag evidence$1) {
/*    */     // Byte code:
/*    */     //   0: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*    */     //   3: astore_2
/*    */     //   4: aload_1
/*    */     //   5: checkcast scala/reflect/ClassTag
/*    */     //   8: astore #4
/*    */     //   10: aload #4
/*    */     //   12: invokeinterface runtimeClass : ()Ljava/lang/Class;
/*    */     //   17: astore_3
/*    */     //   18: getstatic java/lang/Byte.TYPE : Ljava/lang/Class;
/*    */     //   21: dup
/*    */     //   22: ifnonnull -> 33
/*    */     //   25: pop
/*    */     //   26: aload_3
/*    */     //   27: ifnull -> 40
/*    */     //   30: goto -> 52
/*    */     //   33: aload_3
/*    */     //   34: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   37: ifeq -> 52
/*    */     //   40: new scala/collection/mutable/ArrayBuilder$ofByte
/*    */     //   43: dup
/*    */     //   44: invokespecial <init> : ()V
/*    */     //   47: astore #5
/*    */     //   49: goto -> 335
/*    */     //   52: getstatic java/lang/Short.TYPE : Ljava/lang/Class;
/*    */     //   55: dup
/*    */     //   56: ifnonnull -> 67
/*    */     //   59: pop
/*    */     //   60: aload_3
/*    */     //   61: ifnull -> 74
/*    */     //   64: goto -> 86
/*    */     //   67: aload_3
/*    */     //   68: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   71: ifeq -> 86
/*    */     //   74: new scala/collection/mutable/ArrayBuilder$ofShort
/*    */     //   77: dup
/*    */     //   78: invokespecial <init> : ()V
/*    */     //   81: astore #5
/*    */     //   83: goto -> 335
/*    */     //   86: getstatic java/lang/Character.TYPE : Ljava/lang/Class;
/*    */     //   89: dup
/*    */     //   90: ifnonnull -> 101
/*    */     //   93: pop
/*    */     //   94: aload_3
/*    */     //   95: ifnull -> 108
/*    */     //   98: goto -> 120
/*    */     //   101: aload_3
/*    */     //   102: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   105: ifeq -> 120
/*    */     //   108: new scala/collection/mutable/ArrayBuilder$ofChar
/*    */     //   111: dup
/*    */     //   112: invokespecial <init> : ()V
/*    */     //   115: astore #5
/*    */     //   117: goto -> 335
/*    */     //   120: getstatic java/lang/Integer.TYPE : Ljava/lang/Class;
/*    */     //   123: dup
/*    */     //   124: ifnonnull -> 135
/*    */     //   127: pop
/*    */     //   128: aload_3
/*    */     //   129: ifnull -> 142
/*    */     //   132: goto -> 154
/*    */     //   135: aload_3
/*    */     //   136: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   139: ifeq -> 154
/*    */     //   142: new scala/collection/mutable/ArrayBuilder$ofInt
/*    */     //   145: dup
/*    */     //   146: invokespecial <init> : ()V
/*    */     //   149: astore #5
/*    */     //   151: goto -> 335
/*    */     //   154: getstatic java/lang/Long.TYPE : Ljava/lang/Class;
/*    */     //   157: dup
/*    */     //   158: ifnonnull -> 169
/*    */     //   161: pop
/*    */     //   162: aload_3
/*    */     //   163: ifnull -> 176
/*    */     //   166: goto -> 188
/*    */     //   169: aload_3
/*    */     //   170: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   173: ifeq -> 188
/*    */     //   176: new scala/collection/mutable/ArrayBuilder$ofLong
/*    */     //   179: dup
/*    */     //   180: invokespecial <init> : ()V
/*    */     //   183: astore #5
/*    */     //   185: goto -> 335
/*    */     //   188: getstatic java/lang/Float.TYPE : Ljava/lang/Class;
/*    */     //   191: dup
/*    */     //   192: ifnonnull -> 203
/*    */     //   195: pop
/*    */     //   196: aload_3
/*    */     //   197: ifnull -> 210
/*    */     //   200: goto -> 222
/*    */     //   203: aload_3
/*    */     //   204: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   207: ifeq -> 222
/*    */     //   210: new scala/collection/mutable/ArrayBuilder$ofFloat
/*    */     //   213: dup
/*    */     //   214: invokespecial <init> : ()V
/*    */     //   217: astore #5
/*    */     //   219: goto -> 335
/*    */     //   222: getstatic java/lang/Double.TYPE : Ljava/lang/Class;
/*    */     //   225: dup
/*    */     //   226: ifnonnull -> 237
/*    */     //   229: pop
/*    */     //   230: aload_3
/*    */     //   231: ifnull -> 244
/*    */     //   234: goto -> 256
/*    */     //   237: aload_3
/*    */     //   238: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   241: ifeq -> 256
/*    */     //   244: new scala/collection/mutable/ArrayBuilder$ofDouble
/*    */     //   247: dup
/*    */     //   248: invokespecial <init> : ()V
/*    */     //   251: astore #5
/*    */     //   253: goto -> 335
/*    */     //   256: getstatic java/lang/Boolean.TYPE : Ljava/lang/Class;
/*    */     //   259: dup
/*    */     //   260: ifnonnull -> 271
/*    */     //   263: pop
/*    */     //   264: aload_3
/*    */     //   265: ifnull -> 278
/*    */     //   268: goto -> 290
/*    */     //   271: aload_3
/*    */     //   272: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   275: ifeq -> 290
/*    */     //   278: new scala/collection/mutable/ArrayBuilder$ofBoolean
/*    */     //   281: dup
/*    */     //   282: invokespecial <init> : ()V
/*    */     //   285: astore #5
/*    */     //   287: goto -> 335
/*    */     //   290: getstatic java/lang/Void.TYPE : Ljava/lang/Class;
/*    */     //   293: dup
/*    */     //   294: ifnonnull -> 305
/*    */     //   297: pop
/*    */     //   298: aload_3
/*    */     //   299: ifnull -> 312
/*    */     //   302: goto -> 324
/*    */     //   305: aload_3
/*    */     //   306: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   309: ifeq -> 324
/*    */     //   312: new scala/collection/mutable/ArrayBuilder$ofUnit
/*    */     //   315: dup
/*    */     //   316: invokespecial <init> : ()V
/*    */     //   319: astore #5
/*    */     //   321: goto -> 335
/*    */     //   324: new scala/collection/mutable/ArrayBuilder$ofRef
/*    */     //   327: dup
/*    */     //   328: aload #4
/*    */     //   330: invokespecial <init> : (Lscala/reflect/ClassTag;)V
/*    */     //   333: astore #5
/*    */     //   335: aload #5
/*    */     //   337: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #38	-> 0
/*    */     //   #39	-> 10
/*    */     //   #40	-> 18
/*    */     //   #41	-> 52
/*    */     //   #42	-> 86
/*    */     //   #43	-> 120
/*    */     //   #44	-> 154
/*    */     //   #45	-> 188
/*    */     //   #46	-> 222
/*    */     //   #47	-> 256
/*    */     //   #48	-> 290
/*    */     //   #49	-> 324
/*    */     //   #39	-> 335
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	338	0	this	Lscala/collection/mutable/ArrayBuilder$;
/*    */     //   0	338	1	evidence$1	Lscala/reflect/ClassTag;
/*    */     //   10	327	4	tag	Lscala/reflect/ClassTag;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ArrayBuilder$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */