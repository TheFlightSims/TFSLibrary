/*    */ package scala.xml;
/*    */ 
/*    */ public final class Equality$ {
/*    */   public static final Equality$ MODULE$;
/*    */   
/*    */   private Equality$() {
/* 41 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public Object asRef(Object x) {
/* 42 */     return x;
/*    */   }
/*    */   
/*    */   public boolean compareBlithely(Object x1, String x2) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: instanceof scala/xml/Atom
/*    */     //   4: ifeq -> 45
/*    */     //   7: aload_1
/*    */     //   8: checkcast scala/xml/Atom
/*    */     //   11: astore_3
/*    */     //   12: aload_3
/*    */     //   13: invokevirtual data : ()Ljava/lang/Object;
/*    */     //   16: dup
/*    */     //   17: ifnonnull -> 28
/*    */     //   20: pop
/*    */     //   21: aload_2
/*    */     //   22: ifnull -> 35
/*    */     //   25: goto -> 39
/*    */     //   28: aload_2
/*    */     //   29: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   32: ifeq -> 39
/*    */     //   35: iconst_1
/*    */     //   36: goto -> 40
/*    */     //   39: iconst_0
/*    */     //   40: istore #5
/*    */     //   42: goto -> 95
/*    */     //   45: aload_1
/*    */     //   46: instanceof scala/xml/NodeSeq
/*    */     //   49: ifeq -> 92
/*    */     //   52: aload_1
/*    */     //   53: checkcast scala/xml/NodeSeq
/*    */     //   56: astore #4
/*    */     //   58: aload #4
/*    */     //   60: invokevirtual text : ()Ljava/lang/String;
/*    */     //   63: dup
/*    */     //   64: ifnonnull -> 75
/*    */     //   67: pop
/*    */     //   68: aload_2
/*    */     //   69: ifnull -> 82
/*    */     //   72: goto -> 86
/*    */     //   75: aload_2
/*    */     //   76: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   79: ifeq -> 86
/*    */     //   82: iconst_1
/*    */     //   83: goto -> 87
/*    */     //   86: iconst_0
/*    */     //   87: istore #5
/*    */     //   89: goto -> 95
/*    */     //   92: iconst_0
/*    */     //   93: istore #5
/*    */     //   95: iload #5
/*    */     //   97: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #47	-> 0
/*    */     //   #46	-> 0
/*    */     //   #48	-> 45
/*    */     //   #49	-> 92
/*    */     //   #46	-> 95
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	98	0	this	Lscala/xml/Equality$;
/*    */     //   0	98	1	x1	Ljava/lang/Object;
/*    */     //   0	98	2	x2	Ljava/lang/String;
/*    */   }
/*    */   
/*    */   public boolean compareBlithely(Object x1, Node x2) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: instanceof scala/xml/NodeSeq
/*    */     //   4: ifeq -> 59
/*    */     //   7: aload_1
/*    */     //   8: checkcast scala/xml/NodeSeq
/*    */     //   11: astore_3
/*    */     //   12: aload_3
/*    */     //   13: invokevirtual length : ()I
/*    */     //   16: iconst_1
/*    */     //   17: if_icmpne -> 59
/*    */     //   20: aload_2
/*    */     //   21: aload_3
/*    */     //   22: iconst_0
/*    */     //   23: invokevirtual apply : (I)Lscala/xml/Node;
/*    */     //   26: astore #4
/*    */     //   28: dup
/*    */     //   29: ifnonnull -> 41
/*    */     //   32: pop
/*    */     //   33: aload #4
/*    */     //   35: ifnull -> 49
/*    */     //   38: goto -> 53
/*    */     //   41: aload #4
/*    */     //   43: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   46: ifeq -> 53
/*    */     //   49: iconst_1
/*    */     //   50: goto -> 54
/*    */     //   53: iconst_0
/*    */     //   54: istore #5
/*    */     //   56: goto -> 62
/*    */     //   59: iconst_0
/*    */     //   60: istore #5
/*    */     //   62: iload #5
/*    */     //   64: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #52	-> 0
/*    */     //   #51	-> 0
/*    */     //   #53	-> 59
/*    */     //   #51	-> 62
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	65	0	this	Lscala/xml/Equality$;
/*    */     //   0	65	1	x1	Ljava/lang/Object;
/*    */     //   0	65	2	x2	Lscala/xml/Node;
/*    */   }
/*    */   
/*    */   public boolean compareBlithely(Object x1, Object x2) {
/*    */     boolean bool;
/* 56 */     if (x1 == null || x2 == null)
/* 57 */       return (x1 == x2); 
/* 59 */     if (x2 instanceof String) {
/* 59 */       String str = (String)x2;
/* 59 */       bool = compareBlithely(x1, str);
/* 61 */     } else if (x2 instanceof Node) {
/* 61 */       Node node = (Node)x2;
/* 61 */       bool = compareBlithely(x1, node);
/*    */     } else {
/* 62 */       bool = false;
/*    */     } 
/*    */     return bool;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Equality$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */