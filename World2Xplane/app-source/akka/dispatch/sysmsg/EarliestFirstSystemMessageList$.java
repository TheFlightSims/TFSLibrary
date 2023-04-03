/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ public final class EarliestFirstSystemMessageList$ {
/*     */   public static final EarliestFirstSystemMessageList$ MODULE$;
/*     */   
/*     */   public final int hashCode$extension(SystemMessage $this) {
/* 108 */     return $this.hashCode();
/*     */   }
/*     */   
/*     */   public final boolean equals$extension(SystemMessage $this, Object x$1) {
/*     */     // Byte code:
/*     */     //   0: aload_2
/*     */     //   1: astore_3
/*     */     //   2: aload_3
/*     */     //   3: instanceof akka/dispatch/sysmsg/EarliestFirstSystemMessageList
/*     */     //   6: ifeq -> 15
/*     */     //   9: iconst_1
/*     */     //   10: istore #4
/*     */     //   12: goto -> 18
/*     */     //   15: iconst_0
/*     */     //   16: istore #4
/*     */     //   18: iload #4
/*     */     //   20: ifeq -> 80
/*     */     //   23: aload_2
/*     */     //   24: ifnonnull -> 33
/*     */     //   27: aconst_null
/*     */     //   28: pop
/*     */     //   29: aconst_null
/*     */     //   30: goto -> 40
/*     */     //   33: aload_2
/*     */     //   34: checkcast akka/dispatch/sysmsg/EarliestFirstSystemMessageList
/*     */     //   37: invokevirtual head : ()Lakka/dispatch/sysmsg/SystemMessage;
/*     */     //   40: astore #5
/*     */     //   42: aload_1
/*     */     //   43: aload #5
/*     */     //   45: astore #6
/*     */     //   47: dup
/*     */     //   48: ifnonnull -> 60
/*     */     //   51: pop
/*     */     //   52: aload #6
/*     */     //   54: ifnull -> 68
/*     */     //   57: goto -> 72
/*     */     //   60: aload #6
/*     */     //   62: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   65: ifeq -> 72
/*     */     //   68: iconst_1
/*     */     //   69: goto -> 73
/*     */     //   72: iconst_0
/*     */     //   73: ifeq -> 80
/*     */     //   76: iconst_1
/*     */     //   77: goto -> 81
/*     */     //   80: iconst_0
/*     */     //   81: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #108	-> 0
/*     */     //   #63	-> 9
/*     */     //   #108	-> 18
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	82	0	this	Lakka/dispatch/sysmsg/EarliestFirstSystemMessageList$;
/*     */     //   0	82	1	$this	Lakka/dispatch/sysmsg/SystemMessage;
/*     */     //   0	82	2	x$1	Ljava/lang/Object;
/*     */   }
/*     */   
/*     */   private EarliestFirstSystemMessageList$() {
/* 108 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final boolean isEmpty$extension(SystemMessage $this) {
/* 114 */     return ($this == null);
/*     */   }
/*     */   
/*     */   public final boolean nonEmpty$extension(SystemMessage $this) {
/* 119 */     return ($this != null);
/*     */   }
/*     */   
/*     */   public final int size$extension(SystemMessage $this) {
/* 124 */     return SystemMessageList$.MODULE$.sizeInner($this, 0);
/*     */   }
/*     */   
/*     */   public final SystemMessage tail$extension(SystemMessage $this) {
/* 133 */     return $this.next();
/*     */   }
/*     */   
/*     */   public final SystemMessage reverse$extension(SystemMessage $this) {
/* 141 */     null;
/* 141 */     return SystemMessageList$.MODULE$.reverseInner($this, null);
/*     */   }
/*     */   
/*     */   public final SystemMessage $colon$colon$extension(SystemMessage $this, SystemMessage msg) {
/* 147 */     scala.Predef$.MODULE$.assert((msg != null));
/* 148 */     msg.next_$eq($this);
/* 149 */     return msg;
/*     */   }
/*     */   
/*     */   public final SystemMessage reverse_$colon$colon$colon$extension(SystemMessage $this, SystemMessage other) {
/* 160 */     SystemMessage remaining = other;
/* 161 */     SystemMessage result = $this;
/* 162 */     while (LatestFirstSystemMessageList$.MODULE$.nonEmpty$extension(remaining)) {
/* 163 */       SystemMessage msg = remaining;
/* 164 */       remaining = LatestFirstSystemMessageList$.MODULE$.tail$extension(remaining);
/* 165 */       result = $colon$colon$extension(result, msg);
/*     */     } 
/* 167 */     return result;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\EarliestFirstSystemMessageList$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */