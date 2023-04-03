/*     */ package scala.reflect;
/*     */ 
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.StringContext;
/*     */ import scala.collection.Seq;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ public abstract class ClassTag$class {
/*     */   public static void $init$(ClassTag $this) {}
/*     */   
/*     */   public static ClassTag wrap(ClassTag $this) {
/*  48 */     return ClassTag$.MODULE$.apply($this.arrayClass($this.runtimeClass()));
/*     */   }
/*     */   
/*     */   public static Object newArray(ClassTag $this, int len) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokeinterface runtimeClass : ()Ljava/lang/Class;
/*     */     //   6: astore_2
/*     */     //   7: getstatic java/lang/Byte.TYPE : Ljava/lang/Class;
/*     */     //   10: dup
/*     */     //   11: ifnonnull -> 22
/*     */     //   14: pop
/*     */     //   15: aload_2
/*     */     //   16: ifnull -> 29
/*     */     //   19: goto -> 36
/*     */     //   22: aload_2
/*     */     //   23: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   26: ifeq -> 36
/*     */     //   29: iload_1
/*     */     //   30: newarray byte
/*     */     //   32: astore_3
/*     */     //   33: goto -> 280
/*     */     //   36: getstatic java/lang/Short.TYPE : Ljava/lang/Class;
/*     */     //   39: dup
/*     */     //   40: ifnonnull -> 51
/*     */     //   43: pop
/*     */     //   44: aload_2
/*     */     //   45: ifnull -> 58
/*     */     //   48: goto -> 65
/*     */     //   51: aload_2
/*     */     //   52: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   55: ifeq -> 65
/*     */     //   58: iload_1
/*     */     //   59: newarray short
/*     */     //   61: astore_3
/*     */     //   62: goto -> 280
/*     */     //   65: getstatic java/lang/Character.TYPE : Ljava/lang/Class;
/*     */     //   68: dup
/*     */     //   69: ifnonnull -> 80
/*     */     //   72: pop
/*     */     //   73: aload_2
/*     */     //   74: ifnull -> 87
/*     */     //   77: goto -> 94
/*     */     //   80: aload_2
/*     */     //   81: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   84: ifeq -> 94
/*     */     //   87: iload_1
/*     */     //   88: newarray char
/*     */     //   90: astore_3
/*     */     //   91: goto -> 280
/*     */     //   94: getstatic java/lang/Integer.TYPE : Ljava/lang/Class;
/*     */     //   97: dup
/*     */     //   98: ifnonnull -> 109
/*     */     //   101: pop
/*     */     //   102: aload_2
/*     */     //   103: ifnull -> 116
/*     */     //   106: goto -> 123
/*     */     //   109: aload_2
/*     */     //   110: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   113: ifeq -> 123
/*     */     //   116: iload_1
/*     */     //   117: newarray int
/*     */     //   119: astore_3
/*     */     //   120: goto -> 280
/*     */     //   123: getstatic java/lang/Long.TYPE : Ljava/lang/Class;
/*     */     //   126: dup
/*     */     //   127: ifnonnull -> 138
/*     */     //   130: pop
/*     */     //   131: aload_2
/*     */     //   132: ifnull -> 145
/*     */     //   135: goto -> 152
/*     */     //   138: aload_2
/*     */     //   139: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   142: ifeq -> 152
/*     */     //   145: iload_1
/*     */     //   146: newarray long
/*     */     //   148: astore_3
/*     */     //   149: goto -> 280
/*     */     //   152: getstatic java/lang/Float.TYPE : Ljava/lang/Class;
/*     */     //   155: dup
/*     */     //   156: ifnonnull -> 167
/*     */     //   159: pop
/*     */     //   160: aload_2
/*     */     //   161: ifnull -> 174
/*     */     //   164: goto -> 181
/*     */     //   167: aload_2
/*     */     //   168: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   171: ifeq -> 181
/*     */     //   174: iload_1
/*     */     //   175: newarray float
/*     */     //   177: astore_3
/*     */     //   178: goto -> 280
/*     */     //   181: getstatic java/lang/Double.TYPE : Ljava/lang/Class;
/*     */     //   184: dup
/*     */     //   185: ifnonnull -> 196
/*     */     //   188: pop
/*     */     //   189: aload_2
/*     */     //   190: ifnull -> 203
/*     */     //   193: goto -> 210
/*     */     //   196: aload_2
/*     */     //   197: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   200: ifeq -> 210
/*     */     //   203: iload_1
/*     */     //   204: newarray double
/*     */     //   206: astore_3
/*     */     //   207: goto -> 280
/*     */     //   210: getstatic java/lang/Boolean.TYPE : Ljava/lang/Class;
/*     */     //   213: dup
/*     */     //   214: ifnonnull -> 225
/*     */     //   217: pop
/*     */     //   218: aload_2
/*     */     //   219: ifnull -> 232
/*     */     //   222: goto -> 239
/*     */     //   225: aload_2
/*     */     //   226: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   229: ifeq -> 239
/*     */     //   232: iload_1
/*     */     //   233: newarray boolean
/*     */     //   235: astore_3
/*     */     //   236: goto -> 280
/*     */     //   239: getstatic java/lang/Void.TYPE : Ljava/lang/Class;
/*     */     //   242: dup
/*     */     //   243: ifnonnull -> 254
/*     */     //   246: pop
/*     */     //   247: aload_2
/*     */     //   248: ifnull -> 261
/*     */     //   251: goto -> 269
/*     */     //   254: aload_2
/*     */     //   255: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   258: ifeq -> 269
/*     */     //   261: iload_1
/*     */     //   262: anewarray scala/runtime/BoxedUnit
/*     */     //   265: astore_3
/*     */     //   266: goto -> 280
/*     */     //   269: aload_0
/*     */     //   270: invokeinterface runtimeClass : ()Ljava/lang/Class;
/*     */     //   275: iload_1
/*     */     //   276: invokestatic newInstance : (Ljava/lang/Class;I)Ljava/lang/Object;
/*     */     //   279: astore_3
/*     */     //   280: aload_3
/*     */     //   281: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #52	-> 0
/*     */     //   #53	-> 7
/*     */     //   #54	-> 36
/*     */     //   #55	-> 65
/*     */     //   #56	-> 94
/*     */     //   #57	-> 123
/*     */     //   #58	-> 152
/*     */     //   #59	-> 181
/*     */     //   #60	-> 210
/*     */     //   #61	-> 239
/*     */     //   #62	-> 269
/*     */     //   #52	-> 280
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	282	0	$this	Lscala/reflect/ClassTag;
/*     */     //   0	282	1	len	I
/*     */   }
/*     */   
/*     */   public static Option unapply(ClassTag $this, Object x) {
/*  73 */     return unapply_impl($this, x, ClassTag$.MODULE$.Any());
/*     */   }
/*     */   
/*     */   public static Option unapply(ClassTag $this, byte x) {
/*  74 */     return unapply_impl($this, BoxesRunTime.boxToByte(x), ClassTag$.MODULE$.Byte());
/*     */   }
/*     */   
/*     */   public static Option unapply(ClassTag $this, short x) {
/*  75 */     return unapply_impl($this, BoxesRunTime.boxToShort(x), ClassTag$.MODULE$.Short());
/*     */   }
/*     */   
/*     */   public static Option unapply(ClassTag $this, char x) {
/*  76 */     return unapply_impl($this, BoxesRunTime.boxToCharacter(x), ClassTag$.MODULE$.Char());
/*     */   }
/*     */   
/*     */   public static Option unapply(ClassTag $this, int x) {
/*  77 */     return unapply_impl($this, BoxesRunTime.boxToInteger(x), ClassTag$.MODULE$.Int());
/*     */   }
/*     */   
/*     */   public static Option unapply(ClassTag $this, long x) {
/*  78 */     return unapply_impl($this, BoxesRunTime.boxToLong(x), ClassTag$.MODULE$.Long());
/*     */   }
/*     */   
/*     */   public static Option unapply(ClassTag $this, float x) {
/*  79 */     return unapply_impl($this, BoxesRunTime.boxToFloat(x), ClassTag$.MODULE$.Float());
/*     */   }
/*     */   
/*     */   public static Option unapply(ClassTag $this, double x) {
/*  80 */     return unapply_impl($this, BoxesRunTime.boxToDouble(x), ClassTag$.MODULE$.Double());
/*     */   }
/*     */   
/*     */   public static Option unapply(ClassTag $this, boolean x) {
/*  81 */     return unapply_impl($this, BoxesRunTime.boxToBoolean(x), ClassTag$.MODULE$.Boolean());
/*     */   }
/*     */   
/*     */   public static Option unapply(ClassTag $this, BoxedUnit x) {
/*  82 */     return unapply_impl($this, x, ClassTag$.MODULE$.Unit());
/*     */   }
/*     */   
/*     */   private static Option unapply_impl(ClassTag $this, Object x, ClassTag<?> evidence$1) {
/*  87 */     Class<?> staticClass = package$.MODULE$.classTag(evidence$1).runtimeClass();
/*  88 */     Class<?> dynamicClass = x.getClass();
/*  89 */     Class<?> effectiveClass = staticClass.isPrimitive() ? staticClass : dynamicClass;
/*  90 */     boolean conforms = $this.runtimeClass().isAssignableFrom(effectiveClass);
/*  91 */     return (x == null) ? (Option)None$.MODULE$ : (conforms ? (Option)new Some(x) : (Option)None$.MODULE$);
/*     */   }
/*     */   
/*     */   public static boolean canEqual(ClassTag $this, Object x) {
/*  95 */     return x instanceof ClassTag;
/*     */   }
/*     */   
/*     */   public static boolean equals(ClassTag $this, Object x) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: instanceof scala/reflect/ClassTag
/*     */     //   4: ifeq -> 46
/*     */     //   7: aload_0
/*     */     //   8: invokeinterface runtimeClass : ()Ljava/lang/Class;
/*     */     //   13: aload_1
/*     */     //   14: checkcast scala/reflect/ClassTag
/*     */     //   17: invokeinterface runtimeClass : ()Ljava/lang/Class;
/*     */     //   22: astore_2
/*     */     //   23: dup
/*     */     //   24: ifnonnull -> 35
/*     */     //   27: pop
/*     */     //   28: aload_2
/*     */     //   29: ifnull -> 42
/*     */     //   32: goto -> 46
/*     */     //   35: aload_2
/*     */     //   36: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   39: ifeq -> 46
/*     */     //   42: iconst_1
/*     */     //   43: goto -> 47
/*     */     //   46: iconst_0
/*     */     //   47: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #96	-> 0
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	48	0	$this	Lscala/reflect/ClassTag;
/*     */     //   0	48	1	x	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   public static int hashCode(ClassTag $this) {
/*  97 */     return ScalaRunTime$.MODULE$.hash($this.runtimeClass());
/*     */   }
/*     */   
/*     */   private static final String prettyprint$1(ClassTag $this, Class clazz) {
/* 100 */     (new String[2])[0] = "Array[";
/* 100 */     (new String[2])[1] = "]";
/* 100 */     return clazz.isArray() ? (new StringContext((Seq)Predef$.MODULE$.wrapRefArray((Object[])new String[2]))).s((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { prettyprint$1($this, ScalaRunTime$.MODULE$.arrayElementClass(clazz)) })) : clazz.getName();
/*     */   }
/*     */   
/*     */   public static String toString(ClassTag $this) {
/* 102 */     return prettyprint$1($this, $this.runtimeClass());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\ClassTag$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */