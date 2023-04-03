/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Function1;
/*    */ 
/*    */ public abstract class MultiMap$class {
/*    */   public static void $init$(MultiMap $this) {}
/*    */   
/*    */   public static Set makeSet(MultiMap $this) {
/* 65 */     return new HashSet();
/*    */   }
/*    */   
/*    */   public static MultiMap addBinding(MultiMap<Object, Object> $this, Object key, Object value) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: invokeinterface get : (Ljava/lang/Object;)Lscala/Option;
/*    */     //   7: astore #5
/*    */     //   9: getstatic scala/None$.MODULE$ : Lscala/None$;
/*    */     //   12: dup
/*    */     //   13: ifnonnull -> 25
/*    */     //   16: pop
/*    */     //   17: aload #5
/*    */     //   19: ifnull -> 33
/*    */     //   22: goto -> 59
/*    */     //   25: aload #5
/*    */     //   27: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   30: ifeq -> 59
/*    */     //   33: aload_0
/*    */     //   34: invokeinterface makeSet : ()Lscala/collection/mutable/Set;
/*    */     //   39: astore_3
/*    */     //   40: aload_3
/*    */     //   41: aload_2
/*    */     //   42: invokeinterface $plus$eq : (Ljava/lang/Object;)Lscala/collection/mutable/SetLike;
/*    */     //   47: pop
/*    */     //   48: aload_0
/*    */     //   49: aload_1
/*    */     //   50: aload_3
/*    */     //   51: invokeinterface update : (Ljava/lang/Object;Ljava/lang/Object;)V
/*    */     //   56: goto -> 89
/*    */     //   59: aload #5
/*    */     //   61: instanceof scala/Some
/*    */     //   64: ifeq -> 91
/*    */     //   67: aload #5
/*    */     //   69: checkcast scala/Some
/*    */     //   72: astore #4
/*    */     //   74: aload #4
/*    */     //   76: invokevirtual x : ()Ljava/lang/Object;
/*    */     //   79: checkcast scala/collection/mutable/SetLike
/*    */     //   82: aload_2
/*    */     //   83: invokeinterface $plus$eq : (Ljava/lang/Object;)Lscala/collection/mutable/SetLike;
/*    */     //   88: pop
/*    */     //   89: aload_0
/*    */     //   90: areturn
/*    */     //   91: new scala/MatchError
/*    */     //   94: dup
/*    */     //   95: aload #5
/*    */     //   97: invokespecial <init> : (Ljava/lang/Object;)V
/*    */     //   100: athrow
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #77	-> 0
/*    */     //   #78	-> 9
/*    */     //   #79	-> 33
/*    */     //   #80	-> 40
/*    */     //   #81	-> 48
/*    */     //   #78	-> 56
/*    */     //   #82	-> 59
/*    */     //   #77	-> 74
/*    */     //   #83	-> 76
/*    */     //   #85	-> 89
/*    */     //   #77	-> 89
/*    */     //   #77	-> 91
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	101	0	$this	Lscala/collection/mutable/MultiMap;
/*    */     //   0	101	1	key	Ljava/lang/Object;
/*    */     //   0	101	2	value	Ljava/lang/Object;
/*    */     //   40	16	3	set	Lscala/collection/mutable/Set;
/*    */   }
/*    */   
/*    */   public static MultiMap removeBinding(MultiMap $this, Object key, Object value) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: invokeinterface get : (Ljava/lang/Object;)Lscala/Option;
/*    */     //   7: astore #4
/*    */     //   9: getstatic scala/None$.MODULE$ : Lscala/None$;
/*    */     //   12: dup
/*    */     //   13: ifnonnull -> 25
/*    */     //   16: pop
/*    */     //   17: aload #4
/*    */     //   19: ifnull -> 84
/*    */     //   22: goto -> 33
/*    */     //   25: aload #4
/*    */     //   27: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   30: ifne -> 84
/*    */     //   33: aload #4
/*    */     //   35: instanceof scala/Some
/*    */     //   38: ifeq -> 86
/*    */     //   41: aload #4
/*    */     //   43: checkcast scala/Some
/*    */     //   46: astore_3
/*    */     //   47: aload_3
/*    */     //   48: invokevirtual x : ()Ljava/lang/Object;
/*    */     //   51: checkcast scala/collection/mutable/SetLike
/*    */     //   54: aload_2
/*    */     //   55: invokeinterface $minus$eq : (Ljava/lang/Object;)Lscala/collection/mutable/SetLike;
/*    */     //   60: pop
/*    */     //   61: aload_3
/*    */     //   62: invokevirtual x : ()Ljava/lang/Object;
/*    */     //   65: checkcast scala/collection/SetLike
/*    */     //   68: invokeinterface isEmpty : ()Z
/*    */     //   73: ifeq -> 84
/*    */     //   76: aload_0
/*    */     //   77: aload_1
/*    */     //   78: invokeinterface $minus$eq : (Ljava/lang/Object;)Lscala/collection/mutable/MapLike;
/*    */     //   83: pop
/*    */     //   84: aload_0
/*    */     //   85: areturn
/*    */     //   86: new scala/MatchError
/*    */     //   89: dup
/*    */     //   90: aload #4
/*    */     //   92: invokespecial <init> : (Ljava/lang/Object;)V
/*    */     //   95: athrow
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #99	-> 0
/*    */     //   #100	-> 9
/*    */     //   #101	-> 33
/*    */     //   #99	-> 47
/*    */     //   #102	-> 48
/*    */     //   #99	-> 61
/*    */     //   #103	-> 62
/*    */     //   #105	-> 84
/*    */     //   #99	-> 84
/*    */     //   #99	-> 86
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	96	0	$this	Lscala/collection/mutable/MultiMap;
/*    */     //   0	96	1	key	Ljava/lang/Object;
/*    */     //   0	96	2	value	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public static boolean entryExists(MultiMap $this, Object key, Function1 p) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: aload_1
/*    */     //   2: invokeinterface get : (Ljava/lang/Object;)Lscala/Option;
/*    */     //   7: astore #5
/*    */     //   9: getstatic scala/None$.MODULE$ : Lscala/None$;
/*    */     //   12: dup
/*    */     //   13: ifnonnull -> 25
/*    */     //   16: pop
/*    */     //   17: aload #5
/*    */     //   19: ifnull -> 33
/*    */     //   22: goto -> 39
/*    */     //   25: aload #5
/*    */     //   27: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   30: ifeq -> 39
/*    */     //   33: iconst_0
/*    */     //   34: istore #4
/*    */     //   36: goto -> 68
/*    */     //   39: aload #5
/*    */     //   41: instanceof scala/Some
/*    */     //   44: ifeq -> 71
/*    */     //   47: aload #5
/*    */     //   49: checkcast scala/Some
/*    */     //   52: astore_3
/*    */     //   53: aload_3
/*    */     //   54: invokevirtual x : ()Ljava/lang/Object;
/*    */     //   57: checkcast scala/collection/IterableLike
/*    */     //   60: aload_2
/*    */     //   61: invokeinterface exists : (Lscala/Function1;)Z
/*    */     //   66: istore #4
/*    */     //   68: iload #4
/*    */     //   70: ireturn
/*    */     //   71: new scala/MatchError
/*    */     //   74: dup
/*    */     //   75: aload #5
/*    */     //   77: invokespecial <init> : (Ljava/lang/Object;)V
/*    */     //   80: athrow
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #114	-> 0
/*    */     //   #115	-> 9
/*    */     //   #116	-> 39
/*    */     //   #114	-> 53
/*    */     //   #116	-> 54
/*    */     //   #114	-> 68
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	81	0	$this	Lscala/collection/mutable/MultiMap;
/*    */     //   0	81	1	key	Ljava/lang/Object;
/*    */     //   0	81	2	p	Lscala/Function1;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\MultiMap$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */