/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\0052A!\001\002\001\017\tQaj\0343f\005V4g-\032:\013\005\r!\021a\001=nY*\tQ!A\003tG\006d\027m\001\001\024\005\001A\001cA\005\017!5\t!B\003\002\f\031\0059Q.\036;bE2,'BA\007\005\003)\031w\016\0347fGRLwN\\\005\003\037)\0211\"\021:sCf\024UO\0324feB\021\021CE\007\002\005%\0211C\001\002\005\035>$W\rC\003\026\001\021\005a#\001\004=S:LGO\020\013\002/A\021\021\003\001\005\0063\001!\tAG\001\nI\005l\007\017\n9mkN$\"aF\016\t\013qA\002\031A\017\002\003=\004\"AH\020\016\003\021I!\001\t\003\003\007\005s\027\020")
/*    */ public class NodeBuffer extends ArrayBuffer<Node> {
/*    */   public NodeBuffer $amp$plus(Object o) {
/*    */     // Byte code:
/*    */     //   0: aload_1
/*    */     //   1: ifnonnull -> 10
/*    */     //   4: iconst_1
/*    */     //   5: istore #4
/*    */     //   7: goto -> 73
/*    */     //   10: aload_1
/*    */     //   11: instanceof scala/runtime/BoxedUnit
/*    */     //   14: ifeq -> 23
/*    */     //   17: iconst_1
/*    */     //   18: istore #4
/*    */     //   20: goto -> 73
/*    */     //   23: getstatic scala/xml/Text$.MODULE$ : Lscala/xml/Text$;
/*    */     //   26: aload_1
/*    */     //   27: invokevirtual unapply : (Ljava/lang/Object;)Lscala/Option;
/*    */     //   30: astore_2
/*    */     //   31: aload_2
/*    */     //   32: invokevirtual isEmpty : ()Z
/*    */     //   35: ifne -> 70
/*    */     //   38: ldc ''
/*    */     //   40: aload_2
/*    */     //   41: invokevirtual get : ()Ljava/lang/Object;
/*    */     //   44: astore_3
/*    */     //   45: dup
/*    */     //   46: ifnonnull -> 57
/*    */     //   49: pop
/*    */     //   50: aload_3
/*    */     //   51: ifnull -> 64
/*    */     //   54: goto -> 70
/*    */     //   57: aload_3
/*    */     //   58: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   61: ifeq -> 70
/*    */     //   64: iconst_1
/*    */     //   65: istore #4
/*    */     //   67: goto -> 73
/*    */     //   70: iconst_0
/*    */     //   71: istore #4
/*    */     //   73: iload #4
/*    */     //   75: ifne -> 204
/*    */     //   78: aload_1
/*    */     //   79: instanceof scala/collection/Iterator
/*    */     //   82: ifeq -> 109
/*    */     //   85: aload_1
/*    */     //   86: checkcast scala/collection/Iterator
/*    */     //   89: astore #5
/*    */     //   91: aload #5
/*    */     //   93: new scala/xml/NodeBuffer$$anonfun$$amp$plus$1
/*    */     //   96: dup
/*    */     //   97: aload_0
/*    */     //   98: invokespecial <init> : (Lscala/xml/NodeBuffer;)V
/*    */     //   101: invokeinterface foreach : (Lscala/Function1;)V
/*    */     //   106: goto -> 204
/*    */     //   109: aload_1
/*    */     //   110: instanceof scala/xml/Node
/*    */     //   113: ifeq -> 132
/*    */     //   116: aload_1
/*    */     //   117: checkcast scala/xml/Node
/*    */     //   120: astore #6
/*    */     //   122: aload_0
/*    */     //   123: aload #6
/*    */     //   125: invokespecial $plus$eq : (Ljava/lang/Object;)Lscala/collection/mutable/ArrayBuffer;
/*    */     //   128: pop
/*    */     //   129: goto -> 204
/*    */     //   132: aload_1
/*    */     //   133: instanceof scala/collection/Iterable
/*    */     //   136: ifeq -> 160
/*    */     //   139: aload_1
/*    */     //   140: checkcast scala/collection/Iterable
/*    */     //   143: astore #7
/*    */     //   145: aload_0
/*    */     //   146: aload #7
/*    */     //   148: invokeinterface iterator : ()Lscala/collection/Iterator;
/*    */     //   153: invokevirtual $amp$plus : (Ljava/lang/Object;)Lscala/xml/NodeBuffer;
/*    */     //   156: pop
/*    */     //   157: goto -> 204
/*    */     //   160: getstatic scala/runtime/ScalaRunTime$.MODULE$ : Lscala/runtime/ScalaRunTime$;
/*    */     //   163: aload_1
/*    */     //   164: iconst_1
/*    */     //   165: invokevirtual isArray : (Ljava/lang/Object;I)Z
/*    */     //   168: ifeq -> 191
/*    */     //   171: aload_0
/*    */     //   172: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*    */     //   175: aload_1
/*    */     //   176: invokevirtual genericArrayOps : (Ljava/lang/Object;)Lscala/collection/mutable/ArrayOps;
/*    */     //   179: invokeinterface iterator : ()Lscala/collection/Iterator;
/*    */     //   184: invokevirtual $amp$plus : (Ljava/lang/Object;)Lscala/xml/NodeBuffer;
/*    */     //   187: pop
/*    */     //   188: goto -> 204
/*    */     //   191: aload_0
/*    */     //   192: new scala/xml/Atom
/*    */     //   195: dup
/*    */     //   196: aload_1
/*    */     //   197: invokespecial <init> : (Ljava/lang/Object;)V
/*    */     //   200: invokespecial $plus$eq : (Ljava/lang/Object;)Lscala/collection/mutable/ArrayBuffer;
/*    */     //   203: pop
/*    */     //   204: aload_0
/*    */     //   205: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #37	-> 0
/*    */     //   #36	-> 0
/*    */     //   #36	-> 40
/*    */     //   #37	-> 41
/*    */     //   #38	-> 78
/*    */     //   #39	-> 109
/*    */     //   #40	-> 132
/*    */     //   #41	-> 160
/*    */     //   #42	-> 191
/*    */     //   #44	-> 204
/*    */     //   #36	-> 204
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	206	0	this	Lscala/xml/NodeBuffer;
/*    */     //   0	206	1	o	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   public class NodeBuffer$$anonfun$$amp$plus$1 extends AbstractFunction1<Object, NodeBuffer> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final NodeBuffer apply(Object o) {
/* 38 */       return this.$outer.$amp$plus(o);
/*    */     }
/*    */     
/*    */     public NodeBuffer$$anonfun$$amp$plus$1(NodeBuffer $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\NodeBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */