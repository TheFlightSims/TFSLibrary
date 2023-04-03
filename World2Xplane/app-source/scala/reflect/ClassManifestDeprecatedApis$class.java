/*     */ package scala.reflect;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import scala.Function2;
/*     */ import scala.Predef$;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.collection.mutable.ArrayBuilder;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.collection.mutable.WrappedArray;
/*     */ 
/*     */ public abstract class ClassManifestDeprecatedApis$class {
/*     */   public static void $init$(ClassTag $this) {}
/*     */   
/*     */   public static Class erasure(ClassTag $this) {
/*  19 */     return $this.runtimeClass();
/*     */   }
/*     */   
/*     */   private static final boolean loop$1(ClassTag $this, Set left, Set seen, Class sup$1) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: invokeinterface nonEmpty : ()Z
/*     */     //   6: ifeq -> 134
/*     */     //   9: aload_1
/*     */     //   10: invokeinterface head : ()Ljava/lang/Object;
/*     */     //   15: checkcast java/lang/Class
/*     */     //   18: astore #6
/*     */     //   20: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   23: aload #6
/*     */     //   25: invokevirtual getInterfaces : ()[Ljava/lang/Class;
/*     */     //   28: checkcast [Ljava/lang/Object;
/*     */     //   31: invokevirtual refArrayOps : ([Ljava/lang/Object;)Lscala/collection/mutable/ArrayOps;
/*     */     //   34: invokeinterface toSet : ()Lscala/collection/immutable/Set;
/*     */     //   39: getstatic scala/Option$.MODULE$ : Lscala/Option$;
/*     */     //   42: getstatic scala/Option$.MODULE$ : Lscala/Option$;
/*     */     //   45: aload #6
/*     */     //   47: invokevirtual getSuperclass : ()Ljava/lang/Class;
/*     */     //   50: invokevirtual apply : (Ljava/lang/Object;)Lscala/Option;
/*     */     //   53: invokevirtual option2Iterable : (Lscala/Option;)Lscala/collection/Iterable;
/*     */     //   56: invokeinterface $plus$plus : (Lscala/collection/GenTraversableOnce;)Lscala/collection/Set;
/*     */     //   61: checkcast scala/collection/immutable/Set
/*     */     //   64: astore #4
/*     */     //   66: aload #4
/*     */     //   68: aload_3
/*     */     //   69: invokeinterface apply : (Ljava/lang/Object;)Z
/*     */     //   74: ifeq -> 85
/*     */     //   77: iconst_1
/*     */     //   78: ifeq -> 134
/*     */     //   81: iconst_1
/*     */     //   82: goto -> 135
/*     */     //   85: aload_1
/*     */     //   86: aload #4
/*     */     //   88: invokeinterface $plus$plus : (Lscala/collection/GenTraversableOnce;)Lscala/collection/Set;
/*     */     //   93: aload_2
/*     */     //   94: invokeinterface filterNot : (Lscala/Function1;)Ljava/lang/Object;
/*     */     //   99: checkcast scala/collection/immutable/Set
/*     */     //   102: astore #5
/*     */     //   104: aload_0
/*     */     //   105: aload #5
/*     */     //   107: aload #6
/*     */     //   109: invokeinterface $minus : (Ljava/lang/Object;)Lscala/collection/Set;
/*     */     //   114: checkcast scala/collection/immutable/Set
/*     */     //   117: aload_2
/*     */     //   118: aload #6
/*     */     //   120: invokeinterface $plus : (Ljava/lang/Object;)Lscala/collection/Set;
/*     */     //   125: checkcast scala/collection/immutable/Set
/*     */     //   128: astore_2
/*     */     //   129: astore_1
/*     */     //   130: astore_0
/*     */     //   131: goto -> 0
/*     */     //   134: iconst_0
/*     */     //   135: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #23	-> 0
/*     */     //   #24	-> 9
/*     */     //   #25	-> 20
/*     */     //   #26	-> 66
/*     */     //   #23	-> 81
/*     */     //   #27	-> 85
/*     */     //   #28	-> 104
/*     */     //   #23	-> 134
/*     */     //   #22	-> 135
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	136	0	$this	Lscala/reflect/ClassTag;
/*     */     //   0	136	1	left	Lscala/collection/immutable/Set;
/*     */     //   0	136	2	seen	Lscala/collection/immutable/Set;
/*     */     //   0	136	3	sup$1	Ljava/lang/Class;
/*     */     //   20	116	6	next	Ljava/lang/Class;
/*     */     //   66	70	4	supers	Lscala/collection/immutable/Set;
/*     */     //   104	32	5	xs	Lscala/collection/immutable/Set;
/*     */   }
/*     */   
/*     */   private static boolean subtype(ClassTag $this, Class sub, Class sup) {
/*  32 */     (new Class[1])[0] = sub;
/*  32 */     return loop$1($this, (Set)Predef$.MODULE$.Set().apply((Seq)Predef$.MODULE$.wrapRefArray((Object[])new Class[1])), (Set)Predef$.MODULE$.Set().apply((Seq)Nil$.MODULE$), sup);
/*     */   }
/*     */   
/*     */   private static boolean subargs(ClassTag<T> $this, List args1, List args2) {
/*  35 */     return args1.corresponds((GenSeq)args2, (Function2)new ClassManifestDeprecatedApis$$anonfun$subargs$1($this));
/*     */   }
/*     */   
/*     */   private static final boolean cannotMatch$1(ClassTag $this, ClassTag that$1) {
/*  50 */     return (that$1 instanceof AnyValManifest || that$1 == package$.MODULE$.Manifest().AnyVal() || that$1 == package$.MODULE$.Manifest().Nothing() || that$1 == package$.MODULE$.Manifest().Null());
/*     */   }
/*     */   
/*     */   public static boolean $less$colon$less(ClassTag $this, ClassTag that) {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: aload_1
/*     */     //   2: invokestatic cannotMatch$1 : (Lscala/reflect/ClassTag;Lscala/reflect/ClassTag;)Z
/*     */     //   5: ifne -> 102
/*     */     //   8: aload_0
/*     */     //   9: invokeinterface erasure : ()Ljava/lang/Class;
/*     */     //   14: aload_1
/*     */     //   15: invokeinterface erasure : ()Ljava/lang/Class;
/*     */     //   20: astore_2
/*     */     //   21: dup
/*     */     //   22: ifnonnull -> 33
/*     */     //   25: pop
/*     */     //   26: aload_2
/*     */     //   27: ifnull -> 40
/*     */     //   30: goto -> 59
/*     */     //   33: aload_2
/*     */     //   34: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   37: ifeq -> 59
/*     */     //   40: aload_0
/*     */     //   41: aload_0
/*     */     //   42: invokeinterface typeArguments : ()Lscala/collection/immutable/List;
/*     */     //   47: aload_1
/*     */     //   48: invokeinterface typeArguments : ()Lscala/collection/immutable/List;
/*     */     //   53: invokestatic subargs : (Lscala/reflect/ClassTag;Lscala/collection/immutable/List;Lscala/collection/immutable/List;)Z
/*     */     //   56: goto -> 95
/*     */     //   59: aload_1
/*     */     //   60: invokeinterface typeArguments : ()Lscala/collection/immutable/List;
/*     */     //   65: invokevirtual isEmpty : ()Z
/*     */     //   68: ifeq -> 94
/*     */     //   71: aload_0
/*     */     //   72: aload_0
/*     */     //   73: invokeinterface erasure : ()Ljava/lang/Class;
/*     */     //   78: aload_1
/*     */     //   79: invokeinterface erasure : ()Ljava/lang/Class;
/*     */     //   84: invokestatic subtype : (Lscala/reflect/ClassTag;Ljava/lang/Class;Ljava/lang/Class;)Z
/*     */     //   87: ifeq -> 94
/*     */     //   90: iconst_1
/*     */     //   91: goto -> 95
/*     */     //   94: iconst_0
/*     */     //   95: ifeq -> 102
/*     */     //   98: iconst_1
/*     */     //   99: goto -> 103
/*     */     //   102: iconst_0
/*     */     //   103: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #64	-> 0
/*     */     //   #66	-> 8
/*     */     //   #67	-> 40
/*     */     //   #71	-> 59
/*     */     //   #64	-> 98
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	104	0	$this	Lscala/reflect/ClassTag;
/*     */     //   0	104	1	that	Lscala/reflect/ClassTag;
/*     */   }
/*     */   
/*     */   public static boolean $greater$colon$greater(ClassTag<?> $this, ClassTag that) {
/*  81 */     return that.$less$colon$less($this);
/*     */   }
/*     */   
/*     */   public static boolean canEqual(ClassTag $this, Object other) {
/*     */     boolean bool;
/*  83 */     if (other instanceof ClassTag) {
/*  83 */       bool = true;
/*     */     } else {
/*  85 */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   public static Class arrayClass(ClassTag $this, Class<?> tp) {
/*  89 */     return Array.newInstance(tp, 0).getClass();
/*     */   }
/*     */   
/*     */   public static ClassTag arrayManifest(ClassTag<?> $this) {
/*  93 */     return package$.MODULE$.ClassManifest().classType($this.arrayClass($this.erasure()), $this, (Seq<OptManifest<?>>)Predef$.MODULE$.wrapRefArray((Object[])new OptManifest[0]));
/*     */   }
/*     */   
/*     */   public static Object newArray(ClassTag $this, int len) {
/*  96 */     return Array.newInstance($this.erasure(), len);
/*     */   }
/*     */   
/*     */   public static Object[] newArray2(ClassTag $this, int len) {
/* 100 */     return (Object[])Array.newInstance($this.arrayClass($this.erasure()), len);
/*     */   }
/*     */   
/*     */   public static Object[][] newArray3(ClassTag $this, int len) {
/* 105 */     return (Object[][])Array.newInstance($this.arrayClass($this.arrayClass($this.erasure())), len);
/*     */   }
/*     */   
/*     */   public static Object[][][] newArray4(ClassTag $this, int len) {
/* 110 */     return (Object[][][])Array.newInstance($this.arrayClass($this.arrayClass($this.arrayClass($this.erasure()))), len);
/*     */   }
/*     */   
/*     */   public static Object[][][][] newArray5(ClassTag $this, int len) {
/* 115 */     return (Object[][][][])Array.newInstance($this.arrayClass($this.arrayClass($this.arrayClass($this.arrayClass($this.erasure())))), len);
/*     */   }
/*     */   
/*     */   public static WrappedArray newWrappedArray(ClassTag $this, int len) {
/* 121 */     return (WrappedArray)new WrappedArray.ofRef((Object[])$this.newArray(len));
/*     */   }
/*     */   
/*     */   public static ArrayBuilder newArrayBuilder(ClassTag $this) {
/* 126 */     return (ArrayBuilder)new ArrayBuilder.ofRef($this);
/*     */   }
/*     */   
/*     */   public static List typeArguments(ClassTag $this) {
/* 129 */     return (List)Nil$.MODULE$;
/*     */   }
/*     */   
/*     */   public static String argString(ClassTag $this) {
/* 132 */     return $this.typeArguments().nonEmpty() ? $this.typeArguments().mkString("[", ", ", "]") : (
/* 133 */       $this.erasure().isArray() ? (new StringBuilder()).append("[").append(package$.MODULE$.ClassManifest().fromClass($this.erasure().getComponentType())).append("]").toString() : 
/* 134 */       "");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\ClassManifestDeprecatedApis$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */