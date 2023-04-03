/*     */ package scala.xml.parsing;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.Predef$;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.BufferedIterator;
/*     */ import scala.collection.IndexedSeqLike;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Range;
/*     */ import scala.collection.immutable.Range$;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.CharRef;
/*     */ import scala.runtime.Nothing$;
/*     */ import scala.sys.package$;
/*     */ import scala.xml.Utility$;
/*     */ 
/*     */ public abstract class MarkupParserCommon$class {
/*     */   public static void $init$(MarkupParserCommon $this) {}
/*     */   
/*     */   public static Nothing$ unreachable(MarkupParserCommon $this) {
/*  24 */     return package$.MODULE$.error("Cannot be reached.");
/*     */   }
/*     */   
/*     */   public static Tuple2 xTag(MarkupParserCommon $this, Object pscope) {
/*  41 */     String name = $this.xName();
/*  42 */     $this.xSpaceOpt();
/*  44 */     return new Tuple2(name, $this.mkAttributes(name, pscope));
/*     */   }
/*     */   
/*     */   public static Object xProcInstr(MarkupParserCommon $this) {
/*  52 */     String n = $this.xName();
/*  53 */     $this.xSpaceOpt();
/*  54 */     return $this.xTakeUntil((Function2<Object, String, ?>)new MarkupParserCommon$$anonfun$xProcInstr$1($this, n), (Function0)new MarkupParserCommon$$anonfun$xProcInstr$2($this), "?>");
/*     */   }
/*     */   
/*     */   public static String xAttributeValue(MarkupParserCommon $this, char endCh) {
/*  61 */     StringBuilder buf = new StringBuilder();
/*  62 */     while ($this.ch() != endCh) {
/*  64 */       if ($this.ch() == '<')
/*  64 */         return $this.<String>errorAndResult("'<' not allowed in attrib value", ""); 
/*  65 */       if ($this.ch() == '\032')
/*  65 */         throw $this.truncatedError(""); 
/*  66 */       buf.append($this.ch_returning_nextch());
/*     */     } 
/*  68 */     $this.ch_returning_nextch();
/*  70 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public static String xAttributeValue(MarkupParserCommon $this) {
/*  74 */     String str = $this.xAttributeValue($this.ch_returning_nextch());
/*  76 */     return normalizeAttributeValue($this, str);
/*     */   }
/*     */   
/*     */   private static String takeUntilChar(MarkupParserCommon $this, Iterator it, char end) {
/*  80 */     StringBuilder buf = new StringBuilder();
/*  81 */     while (it.hasNext()) {
/*  81 */       char c = BoxesRunTime.unboxToChar(it.next());
/*  82 */       if (end == c)
/*  82 */         return buf.toString(); 
/*  83 */       buf.append(c);
/*     */     } 
/*  85 */     Predef$ predef$ = Predef$.MODULE$;
/*  85 */     throw package$.MODULE$.error((new StringOps("Expected '%s'")).format(Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToCharacter(end) })));
/*     */   }
/*     */   
/*     */   public static void xEndTag(MarkupParserCommon $this, String startName) {
/*  91 */     $this.xToken('/');
/*  92 */     if ($this.xName() == null) {
/*  92 */       $this.xName();
/*  92 */       if (startName != null)
/*  93 */         throw $this.errorNoEnd(startName); 
/*     */     } else if (!$this.xName().equals(startName)) {
/*  93 */       throw $this.errorNoEnd(startName);
/*     */     } 
/*  95 */     $this.xSpaceOpt();
/*  96 */     $this.xToken('>');
/*     */   }
/*     */   
/*     */   public static String xName(MarkupParserCommon $this) {
/* 108 */     if ($this.ch() == '\032')
/* 109 */       throw $this.truncatedError(""); 
/* 110 */     if ($this.isNameStart($this.ch())) {
/* 113 */       StringBuilder buf = new StringBuilder();
/*     */       do {
/* 115 */         buf.append($this.ch_returning_nextch());
/* 116 */       } while ($this.isNameChar($this.ch()));
/* 119 */       $this.reportSyntaxError("name cannot end in ':'");
/* 120 */       String str = buf.toString();
/* 120 */       Predef$ predef$1 = Predef$.MODULE$;
/* 120 */       return (BoxesRunTime.unboxToChar(buf.last()) == ':') ? (String)(new StringOps(str)).dropRight(1) : 
/*     */         
/* 122 */         buf.toString();
/*     */     } 
/*     */     Predef$ predef$ = Predef$.MODULE$;
/*     */     return $this.<String>errorAndResult((new StringOps("name expected, but char '%s' cannot start a name")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToCharacter($this.ch()) }, )), "");
/*     */   }
/*     */   
/*     */   private static String attr_unescape(MarkupParserCommon $this, String s) {
/*     */     // Byte code:
/*     */     //   0: ldc 'lt'
/*     */     //   2: dup
/*     */     //   3: ifnonnull -> 14
/*     */     //   6: pop
/*     */     //   7: aload_1
/*     */     //   8: ifnull -> 21
/*     */     //   11: goto -> 27
/*     */     //   14: aload_1
/*     */     //   15: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   18: ifeq -> 27
/*     */     //   21: ldc '<'
/*     */     //   23: astore_2
/*     */     //   24: goto -> 187
/*     */     //   27: ldc 'gt'
/*     */     //   29: dup
/*     */     //   30: ifnonnull -> 41
/*     */     //   33: pop
/*     */     //   34: aload_1
/*     */     //   35: ifnull -> 48
/*     */     //   38: goto -> 54
/*     */     //   41: aload_1
/*     */     //   42: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   45: ifeq -> 54
/*     */     //   48: ldc '>'
/*     */     //   50: astore_2
/*     */     //   51: goto -> 187
/*     */     //   54: ldc 'amp'
/*     */     //   56: dup
/*     */     //   57: ifnonnull -> 68
/*     */     //   60: pop
/*     */     //   61: aload_1
/*     */     //   62: ifnull -> 75
/*     */     //   65: goto -> 81
/*     */     //   68: aload_1
/*     */     //   69: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   72: ifeq -> 81
/*     */     //   75: ldc '&'
/*     */     //   77: astore_2
/*     */     //   78: goto -> 187
/*     */     //   81: ldc 'apos'
/*     */     //   83: dup
/*     */     //   84: ifnonnull -> 95
/*     */     //   87: pop
/*     */     //   88: aload_1
/*     */     //   89: ifnull -> 102
/*     */     //   92: goto -> 108
/*     */     //   95: aload_1
/*     */     //   96: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   99: ifeq -> 108
/*     */     //   102: ldc '''
/*     */     //   104: astore_2
/*     */     //   105: goto -> 187
/*     */     //   108: ldc 'quot'
/*     */     //   110: dup
/*     */     //   111: ifnonnull -> 122
/*     */     //   114: pop
/*     */     //   115: aload_1
/*     */     //   116: ifnull -> 129
/*     */     //   119: goto -> 135
/*     */     //   122: aload_1
/*     */     //   123: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   126: ifeq -> 135
/*     */     //   129: ldc '"'
/*     */     //   131: astore_2
/*     */     //   132: goto -> 187
/*     */     //   135: ldc 'quote'
/*     */     //   137: dup
/*     */     //   138: ifnonnull -> 149
/*     */     //   141: pop
/*     */     //   142: aload_1
/*     */     //   143: ifnull -> 156
/*     */     //   146: goto -> 162
/*     */     //   149: aload_1
/*     */     //   150: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   153: ifeq -> 162
/*     */     //   156: ldc '"'
/*     */     //   158: astore_2
/*     */     //   159: goto -> 187
/*     */     //   162: new scala/collection/mutable/StringBuilder
/*     */     //   165: dup
/*     */     //   166: invokespecial <init> : ()V
/*     */     //   169: ldc '&'
/*     */     //   171: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   174: aload_1
/*     */     //   175: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   178: ldc ';'
/*     */     //   180: invokevirtual append : (Ljava/lang/Object;)Lscala/collection/mutable/StringBuilder;
/*     */     //   183: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   186: astore_2
/*     */     //   187: aload_2
/*     */     //   188: areturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #126	-> 0
/*     */     //   #125	-> 0
/*     */     //   #127	-> 27
/*     */     //   #128	-> 54
/*     */     //   #129	-> 81
/*     */     //   #130	-> 108
/*     */     //   #131	-> 135
/*     */     //   #132	-> 162
/*     */     //   #125	-> 187
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	189	0	$this	Lscala/xml/parsing/MarkupParserCommon;
/*     */     //   0	189	1	s	Ljava/lang/String;
/*     */   }
/*     */   
/*     */   private static String normalizeAttributeValue(MarkupParserCommon $this, String attval) {
/* 139 */     StringBuilder buf = new StringBuilder();
/* 140 */     Predef$ predef$ = Predef$.MODULE$;
/* 140 */     BufferedIterator it = (new StringOps(attval)).iterator().buffered();
/* 142 */     while (it.hasNext()) {
/* 142 */       char c = BoxesRunTime.unboxToChar(it.next());
/* 142 */       switch (c) {
/*     */         default:
/*     */         
/*     */         case '&':
/* 144 */           if (BoxesRunTime.unboxToChar(it.head()) == '#')
/* 144 */             it.next(); 
/*     */         case '\t':
/*     */         case '\n':
/*     */         case '\r':
/*     */         case ' ':
/*     */           break;
/*     */       } 
/*     */       buf.append(" ");
/*     */     } 
/* 149 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public static String xCharRef(MarkupParserCommon $this, Function0 ch, Function0 nextch) {
/* 158 */     return Utility$.MODULE$.parseCharRef(ch, nextch, (Function1)new MarkupParserCommon$$anonfun$xCharRef$5($this), (Function1)new MarkupParserCommon$$anonfun$xCharRef$6($this));
/*     */   }
/*     */   
/*     */   public static String xCharRef(MarkupParserCommon $this, Iterator it) {
/* 161 */     CharRef c = new CharRef(BoxesRunTime.unboxToChar(it.next()));
/* 162 */     return Utility$.MODULE$.parseCharRef((Function0)new MarkupParserCommon$$anonfun$xCharRef$1($this, c), (Function0)new MarkupParserCommon$$anonfun$xCharRef$2($this, c, it), (Function1)new MarkupParserCommon$$anonfun$xCharRef$7($this), (Function1)new MarkupParserCommon$$anonfun$xCharRef$8($this));
/*     */   }
/*     */   
/*     */   public static String xCharRef(MarkupParserCommon $this) {
/* 165 */     return $this.xCharRef((Function0<Object>)new MarkupParserCommon$$anonfun$xCharRef$3($this), (Function0<BoxedUnit>)new MarkupParserCommon$$anonfun$xCharRef$4($this));
/*     */   }
/*     */   
/*     */   public static Object errorAndResult(MarkupParserCommon $this, String msg, Object x) {
/* 193 */     $this.reportSyntaxError(msg);
/* 194 */     return x;
/*     */   }
/*     */   
/*     */   public static void xToken(MarkupParserCommon $this, char that) {
/* 198 */     if ($this.ch() == that) {
/* 198 */       $this.nextch();
/*     */     } else {
/* 199 */       Predef$ predef$ = Predef$.MODULE$;
/* 199 */       $this.xHandleError(that, (new StringOps("'%s' expected instead of '%s'")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToCharacter(that), BoxesRunTime.boxToCharacter($this.ch()) })));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void xToken(MarkupParserCommon $this, Seq that) {
/* 201 */     that.foreach((Function1)new MarkupParserCommon$$anonfun$xToken$1($this));
/*     */   }
/*     */   
/*     */   public static void xEQ(MarkupParserCommon $this) {
/* 204 */     $this.xSpaceOpt();
/* 204 */     $this.xToken('=');
/* 204 */     $this.xSpaceOpt();
/*     */   }
/*     */   
/*     */   public static void xSpaceOpt(MarkupParserCommon $this) {
/* 207 */     for (; $this.isSpace($this.ch()) && !$this.eof(); $this.nextch());
/*     */   }
/*     */   
/*     */   public static void xSpace(MarkupParserCommon $this) {
/* 211 */     if ($this.isSpace($this.ch())) {
/* 211 */       $this.nextch();
/* 211 */       $this.xSpaceOpt();
/*     */     } else {
/* 212 */       $this.xHandleError($this.ch(), "whitespace expected");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object returning(MarkupParserCommon $this, Object x, Function1 f) {
/* 215 */     f.apply(x);
/* 215 */     return x;
/*     */   }
/*     */   
/*     */   public static Object saving(MarkupParserCommon $this, Object getter, Function1 setter, Function0 body) {
/* 219 */     Object saved = getter;
/*     */     try {
/* 220 */       return body.apply();
/*     */     } finally {
/* 221 */       setter.apply(saved);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object xTakeUntil(MarkupParserCommon $this, Function2 handler, Function0 positioner, String until) {
/* 233 */     StringBuilder sb = new StringBuilder();
/* 234 */     Predef$ predef$1 = Predef$.MODULE$;
/* 234 */     char head = BoxesRunTime.unboxToChar((new StringOps(until)).head());
/* 235 */     Predef$ predef$2 = Predef$.MODULE$;
/* 235 */     String rest = (String)(new StringOps(until)).tail();
/*     */     while (true) {
/* 237 */       if ($this.ch() == head && peek($this, rest))
/* 239 */         return handler.apply(positioner.apply(), sb.toString()); 
/* 240 */       if ($this.ch() == '\032')
/* 241 */         throw $this.truncatedError(""); 
/* 243 */       sb.append($this.ch());
/* 244 */       $this.nextch();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean peek(MarkupParserCommon $this, String lookingFor) {
/* 255 */     Predef$ predef$ = Predef$.MODULE$;
/* 255 */     if ($this.lookahead().take(lookingFor.length()).sameElements(IndexedSeqLike.class.iterator((IndexedSeqLike)new StringOps(lookingFor)))) {
/* 257 */       Predef$ predef$1 = Predef$.MODULE$;
/* 257 */       int i = lookingFor.length();
/* 257 */       Range$ range$ = Range$.MODULE$;
/* 257 */       MarkupParserCommon$$anonfun$peek$1 markupParserCommon$$anonfun$peek$1 = new MarkupParserCommon$$anonfun$peek$1($this);
/*     */       Range.Inclusive inclusive;
/* 257 */       if ((inclusive = new Range.Inclusive(0, i, 1)).validateRangeBoundaries((Function1)markupParserCommon$$anonfun$peek$1)) {
/*     */         int terminal1;
/*     */         int step1;
/*     */         int i1;
/* 257 */         for (i1 = inclusive.start(), terminal1 = inclusive.terminalElement(), step1 = inclusive.step(); i1 != terminal1; ) {
/* 257 */           $this.nextch();
/* 257 */           i1 += step1;
/*     */         } 
/*     */       } 
/*     */       if (true);
/*     */     } 
/*     */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\parsing\MarkupParserCommon$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */