/*    */ package scala.reflect;
/*    */ 
/*    */ import scala.Predef$;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.runtime.ScalaRunTime$;
/*    */ 
/*    */ public abstract class Manifest$class {
/*    */   public static void $init$(Manifest $this) {}
/*    */   
/*    */   public static List typeArguments(Manifest $this) {
/* 45 */     return (List)Nil$.MODULE$;
/*    */   }
/*    */   
/*    */   public static Manifest arrayManifest(Manifest<?> $this) {
/* 48 */     return package$.MODULE$.Manifest().classType($this.arrayClass($this.erasure()), $this, (Seq<Manifest<?>>)Predef$.MODULE$.wrapRefArray((Object[])new Manifest[0]));
/*    */   }
/*    */   
/*    */   public static boolean canEqual(Manifest $this, Object that) {
/*    */     boolean bool;
/* 50 */     if (that instanceof Manifest) {
/* 50 */       bool = true;
/*    */     } else {
/* 52 */       bool = false;
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */   
/*    */   public static boolean equals(Manifest $this, Object that) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: instanceof scala/reflect/Manifest
/*    */     //   4: ifeq -> 84
/*    */     //   7: aload_1
/*    */     //   8: checkcast scala/reflect/Manifest
/*    */     //   11: astore_3
/*    */     //   12: aload_3
/*    */     //   13: aload_0
/*    */     //   14: invokeinterface canEqual : (Ljava/lang/Object;)Z
/*    */     //   19: ifeq -> 78
/*    */     //   22: aload_0
/*    */     //   23: invokeinterface erasure : ()Ljava/lang/Class;
/*    */     //   28: aload_3
/*    */     //   29: invokeinterface erasure : ()Ljava/lang/Class;
/*    */     //   34: astore_2
/*    */     //   35: dup
/*    */     //   36: ifnonnull -> 47
/*    */     //   39: pop
/*    */     //   40: aload_2
/*    */     //   41: ifnull -> 54
/*    */     //   44: goto -> 78
/*    */     //   47: aload_2
/*    */     //   48: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   51: ifeq -> 78
/*    */     //   54: aload_0
/*    */     //   55: aload_3
/*    */     //   56: invokeinterface $less$colon$less : (Lscala/reflect/ClassTag;)Z
/*    */     //   61: ifeq -> 78
/*    */     //   64: aload_3
/*    */     //   65: aload_0
/*    */     //   66: invokeinterface $less$colon$less : (Lscala/reflect/ClassTag;)Z
/*    */     //   71: ifeq -> 78
/*    */     //   74: iconst_1
/*    */     //   75: goto -> 79
/*    */     //   78: iconst_0
/*    */     //   79: istore #4
/*    */     //   81: goto -> 87
/*    */     //   84: iconst_0
/*    */     //   85: istore #4
/*    */     //   87: iload #4
/*    */     //   89: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #58	-> 0
/*    */     //   #57	-> 0
/*    */     //   #59	-> 84
/*    */     //   #57	-> 87
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	90	0	$this	Lscala/reflect/Manifest;
/*    */     //   0	90	1	that	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public static int hashCode(Manifest $this) {
/* 61 */     return ScalaRunTime$.MODULE$.hash($this.erasure());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\Manifest$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */