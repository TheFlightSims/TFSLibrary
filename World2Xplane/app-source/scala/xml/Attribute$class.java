/*    */ package scala.xml;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.collection.Iterator;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.package$;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public abstract class Attribute$class {
/*    */   public static void $init$(Attribute $this) {}
/*    */   
/*    */   public static MetaData remove(Attribute $this, String key) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: invokeinterface isPrefixed : ()Z
/*    */     //   6: ifne -> 43
/*    */     //   9: aload_0
/*    */     //   10: invokeinterface key : ()Ljava/lang/String;
/*    */     //   15: dup
/*    */     //   16: ifnonnull -> 27
/*    */     //   19: pop
/*    */     //   20: aload_1
/*    */     //   21: ifnull -> 34
/*    */     //   24: goto -> 43
/*    */     //   27: aload_1
/*    */     //   28: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   31: ifeq -> 43
/*    */     //   34: aload_0
/*    */     //   35: invokeinterface next : ()Lscala/xml/MetaData;
/*    */     //   40: goto -> 62
/*    */     //   43: aload_0
/*    */     //   44: aload_0
/*    */     //   45: invokeinterface next : ()Lscala/xml/MetaData;
/*    */     //   50: aload_1
/*    */     //   51: invokevirtual remove : (Ljava/lang/String;)Lscala/xml/MetaData;
/*    */     //   54: invokeinterface copy : (Lscala/xml/MetaData;)Lscala/xml/Attribute;
/*    */     //   59: checkcast scala/xml/MetaData
/*    */     //   62: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #60	-> 0
/*    */     //   #61	-> 43
/*    */     //   #60	-> 62
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	63	0	$this	Lscala/xml/Attribute;
/*    */     //   0	63	1	key	Ljava/lang/String;
/*    */   }
/*    */   
/*    */   public static MetaData remove(Attribute $this, String namespace, NamespaceBinding scope, String key) {
/*    */     // Byte code:
/*    */     //   0: aload_0
/*    */     //   1: invokeinterface key : ()Ljava/lang/String;
/*    */     //   6: dup
/*    */     //   7: ifnonnull -> 18
/*    */     //   10: pop
/*    */     //   11: aload_3
/*    */     //   12: ifnull -> 25
/*    */     //   15: goto -> 63
/*    */     //   18: aload_3
/*    */     //   19: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   22: ifeq -> 63
/*    */     //   25: aload_2
/*    */     //   26: aload_0
/*    */     //   27: invokeinterface pre : ()Ljava/lang/String;
/*    */     //   32: invokevirtual getURI : (Ljava/lang/String;)Ljava/lang/String;
/*    */     //   35: dup
/*    */     //   36: ifnonnull -> 47
/*    */     //   39: pop
/*    */     //   40: aload_1
/*    */     //   41: ifnull -> 54
/*    */     //   44: goto -> 63
/*    */     //   47: aload_1
/*    */     //   48: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   51: ifeq -> 63
/*    */     //   54: aload_0
/*    */     //   55: invokeinterface next : ()Lscala/xml/MetaData;
/*    */     //   60: goto -> 84
/*    */     //   63: aload_0
/*    */     //   64: aload_0
/*    */     //   65: invokeinterface next : ()Lscala/xml/MetaData;
/*    */     //   70: aload_1
/*    */     //   71: aload_2
/*    */     //   72: aload_3
/*    */     //   73: invokevirtual remove : (Ljava/lang/String;Lscala/xml/NamespaceBinding;Ljava/lang/String;)Lscala/xml/MetaData;
/*    */     //   76: invokeinterface copy : (Lscala/xml/MetaData;)Lscala/xml/Attribute;
/*    */     //   81: checkcast scala/xml/MetaData
/*    */     //   84: areturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #64	-> 0
/*    */     //   #65	-> 63
/*    */     //   #64	-> 84
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	85	0	$this	Lscala/xml/Attribute;
/*    */     //   0	85	1	namespace	Ljava/lang/String;
/*    */     //   0	85	2	scope	Lscala/xml/NamespaceBinding;
/*    */     //   0	85	3	key	Ljava/lang/String;
/*    */   }
/*    */   
/*    */   public static boolean isPrefixed(Attribute $this) {
/* 67 */     return !($this.pre() == null);
/*    */   }
/*    */   
/*    */   public static boolean wellformed(Attribute $this, NamespaceBinding scope) {
/* 72 */     String arg = $this.isPrefixed() ? scope.getURI($this.pre()) : null;
/* 73 */     return ($this.next().apply(arg, scope, $this.key()) == null && $this.next().wellformed(scope));
/*    */   }
/*    */   
/*    */   public static Iterator iterator(Attribute $this) {
/* 78 */     return ($this.value() == null) ? $this.next().iterator() : 
/* 79 */       package$.MODULE$.Iterator().single($this).$plus$plus((Function0)new Attribute$$anonfun$iterator$1($this));
/*    */   }
/*    */   
/*    */   public static int size(Attribute $this) {
/* 83 */     return ($this.value() == null) ? $this.next().size() : (
/* 84 */       1 + $this.next().size());
/*    */   }
/*    */   
/*    */   public static void toString1(Attribute $this, StringBuilder sb) {
/* 90 */     if ($this.value() == null)
/*    */       return; 
/* 92 */     $this.isPrefixed() ? 
/* 93 */       sb.append($this.pre()).append(':') : BoxedUnit.UNIT;
/* 95 */     sb.append($this.key()).append('=');
/* 96 */     StringBuilder sb2 = new StringBuilder();
/* 97 */     Utility$.MODULE$.sequenceToXML($this.value(), TopScope$.MODULE$, sb2, true, Utility$.MODULE$.sequenceToXML$default$5(), Utility$.MODULE$.sequenceToXML$default$6(), Utility$.MODULE$.sequenceToXML$default$7());
/* 98 */     Utility$.MODULE$.appendQuoted(sb2.toString(), sb);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Attribute$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */