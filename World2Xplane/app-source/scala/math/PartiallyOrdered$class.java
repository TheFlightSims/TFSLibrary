package scala.math;

import scala.Function1;

public abstract class PartiallyOrdered$class {
  public static void $init$(PartiallyOrdered $this) {}
  
  public static boolean $less(PartiallyOrdered $this, Object that, Function1 evidence$2) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: invokeinterface tryCompareTo : (Ljava/lang/Object;Lscala/Function1;)Lscala/Option;
    //   8: astore_3
    //   9: aload_3
    //   10: instanceof scala/Some
    //   13: ifeq -> 40
    //   16: aload_3
    //   17: checkcast scala/Some
    //   20: astore #4
    //   22: aload #4
    //   24: invokevirtual x : ()Ljava/lang/Object;
    //   27: invokestatic unboxToInt : (Ljava/lang/Object;)I
    //   30: iconst_0
    //   31: if_icmpge -> 40
    //   34: iconst_1
    //   35: istore #5
    //   37: goto -> 43
    //   40: iconst_0
    //   41: istore #5
    //   43: iload #5
    //   45: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #30	-> 0
    //   #31	-> 9
    //   #30	-> 22
    //   #31	-> 24
    //   #32	-> 40
    //   #30	-> 43
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   0	46	0	$this	Lscala/math/PartiallyOrdered;
    //   0	46	1	that	Ljava/lang/Object;
    //   0	46	2	evidence$2	Lscala/Function1;
  }
  
  public static boolean $greater(PartiallyOrdered $this, Object that, Function1 evidence$3) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: invokeinterface tryCompareTo : (Ljava/lang/Object;Lscala/Function1;)Lscala/Option;
    //   8: astore_3
    //   9: aload_3
    //   10: instanceof scala/Some
    //   13: ifeq -> 40
    //   16: aload_3
    //   17: checkcast scala/Some
    //   20: astore #4
    //   22: aload #4
    //   24: invokevirtual x : ()Ljava/lang/Object;
    //   27: invokestatic unboxToInt : (Ljava/lang/Object;)I
    //   30: iconst_0
    //   31: if_icmple -> 40
    //   34: iconst_1
    //   35: istore #5
    //   37: goto -> 43
    //   40: iconst_0
    //   41: istore #5
    //   43: iload #5
    //   45: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #35	-> 0
    //   #36	-> 9
    //   #35	-> 22
    //   #36	-> 24
    //   #37	-> 40
    //   #35	-> 43
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   0	46	0	$this	Lscala/math/PartiallyOrdered;
    //   0	46	1	that	Ljava/lang/Object;
    //   0	46	2	evidence$3	Lscala/Function1;
  }
  
  public static boolean $less$eq(PartiallyOrdered $this, Object that, Function1 evidence$4) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: invokeinterface tryCompareTo : (Ljava/lang/Object;Lscala/Function1;)Lscala/Option;
    //   8: astore_3
    //   9: aload_3
    //   10: instanceof scala/Some
    //   13: ifeq -> 40
    //   16: aload_3
    //   17: checkcast scala/Some
    //   20: astore #4
    //   22: aload #4
    //   24: invokevirtual x : ()Ljava/lang/Object;
    //   27: invokestatic unboxToInt : (Ljava/lang/Object;)I
    //   30: iconst_0
    //   31: if_icmpgt -> 40
    //   34: iconst_1
    //   35: istore #5
    //   37: goto -> 43
    //   40: iconst_0
    //   41: istore #5
    //   43: iload #5
    //   45: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #40	-> 0
    //   #41	-> 9
    //   #40	-> 22
    //   #41	-> 24
    //   #42	-> 40
    //   #40	-> 43
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   0	46	0	$this	Lscala/math/PartiallyOrdered;
    //   0	46	1	that	Ljava/lang/Object;
    //   0	46	2	evidence$4	Lscala/Function1;
  }
  
  public static boolean $greater$eq(PartiallyOrdered $this, Object that, Function1 evidence$5) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: invokeinterface tryCompareTo : (Ljava/lang/Object;Lscala/Function1;)Lscala/Option;
    //   8: astore_3
    //   9: aload_3
    //   10: instanceof scala/Some
    //   13: ifeq -> 40
    //   16: aload_3
    //   17: checkcast scala/Some
    //   20: astore #4
    //   22: aload #4
    //   24: invokevirtual x : ()Ljava/lang/Object;
    //   27: invokestatic unboxToInt : (Ljava/lang/Object;)I
    //   30: iconst_0
    //   31: if_icmplt -> 40
    //   34: iconst_1
    //   35: istore #5
    //   37: goto -> 43
    //   40: iconst_0
    //   41: istore #5
    //   43: iload #5
    //   45: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #45	-> 0
    //   #46	-> 9
    //   #45	-> 22
    //   #46	-> 24
    //   #47	-> 40
    //   #45	-> 43
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   0	46	0	$this	Lscala/math/PartiallyOrdered;
    //   0	46	1	that	Ljava/lang/Object;
    //   0	46	2	evidence$5	Lscala/Function1;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\PartiallyOrdered$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */