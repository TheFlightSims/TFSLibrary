/*    */ package scala.collection.immutable;
/*    */ 
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ 
/*    */ public final class StringOps$ {
/*    */   public static final StringOps$ MODULE$;
/*    */   
/*    */   public final int hashCode$extension(String $this) {
/* 31 */     return $this.hashCode();
/*    */   }
/*    */   
/*    */   public final boolean equals$extension(String $this, Object x$1) {
/*    */     // Byte code:
/*    */     //   0: aload_2
/*    */     //   1: instanceof scala/collection/immutable/StringOps
/*    */     //   4: ifeq -> 12
/*    */     //   7: iconst_1
/*    */     //   8: istore_3
/*    */     //   9: goto -> 14
/*    */     //   12: iconst_0
/*    */     //   13: istore_3
/*    */     //   14: iload_3
/*    */     //   15: ifeq -> 69
/*    */     //   18: aload_2
/*    */     //   19: ifnonnull -> 26
/*    */     //   22: aconst_null
/*    */     //   23: goto -> 33
/*    */     //   26: aload_2
/*    */     //   27: checkcast scala/collection/immutable/StringOps
/*    */     //   30: invokevirtual repr : ()Ljava/lang/String;
/*    */     //   33: astore #4
/*    */     //   35: aload_1
/*    */     //   36: dup
/*    */     //   37: ifnonnull -> 49
/*    */     //   40: pop
/*    */     //   41: aload #4
/*    */     //   43: ifnull -> 57
/*    */     //   46: goto -> 61
/*    */     //   49: aload #4
/*    */     //   51: invokevirtual equals : (Ljava/lang/Object;)Z
/*    */     //   54: ifeq -> 61
/*    */     //   57: iconst_1
/*    */     //   58: goto -> 62
/*    */     //   61: iconst_0
/*    */     //   62: ifeq -> 69
/*    */     //   65: iconst_1
/*    */     //   66: goto -> 70
/*    */     //   69: iconst_0
/*    */     //   70: ireturn
/*    */     // Line number table:
/*    */     //   Java source line number -> byte code offset
/*    */     //   #31	-> 0
/*    */     //   #236	-> 7
/*    */     //   #31	-> 14
/*    */     // Local variable table:
/*    */     //   start	length	slot	name	descriptor
/*    */     //   0	71	0	this	Lscala/collection/immutable/StringOps$;
/*    */     //   0	71	1	$this	Ljava/lang/String;
/*    */     //   0	71	2	x$1	Ljava/lang/Object;
/*    */   }
/*    */   
/*    */   private StringOps$() {
/* 31 */     MODULE$ = this;
/*    */   }
/*    */   
/*    */   public final WrappedString thisCollection$extension(String $this) {
/* 33 */     return new WrappedString($this);
/*    */   }
/*    */   
/*    */   public final WrappedString toCollection$extension(String $this, String repr) {
/* 34 */     return new WrappedString(repr);
/*    */   }
/*    */   
/*    */   public final StringBuilder newBuilder$extension(String $this) {
/* 37 */     return scala.collection.mutable.StringBuilder$.MODULE$.newBuilder();
/*    */   }
/*    */   
/*    */   public final char apply$extension(String $this, int index) {
/* 39 */     return $this.charAt(index);
/*    */   }
/*    */   
/*    */   public final String slice$extension(String $this, int from, int until) {
/* 41 */     int start = (from < 0) ? 0 : from;
/* 42 */     if (until <= start || start >= $this.length())
/* 43 */       return ""; 
/* 45 */     int end = (until > length$extension($this)) ? length$extension($this) : until;
/* 46 */     return $this.substring(start, end);
/*    */   }
/*    */   
/*    */   public final int length$extension(String $this) {
/* 49 */     return $this.length();
/*    */   }
/*    */   
/*    */   public final WrappedString seq$extension(String $this) {
/* 51 */     return new WrappedString($this);
/*    */   }
/*    */   
/*    */   public final String toString$extension(String $this) {
/*    */     return $this;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\StringOps$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */