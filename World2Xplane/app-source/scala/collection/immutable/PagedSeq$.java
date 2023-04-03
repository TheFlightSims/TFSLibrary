/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.Reader;
/*     */ import scala.Function1;
/*     */ import scala.Function3;
/*     */ import scala.Serializable;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.io.Source;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction3;
/*     */ import scala.runtime.BooleanRef;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ObjectRef;
/*     */ 
/*     */ public final class PagedSeq$ {
/*     */   public static final PagedSeq$ MODULE$;
/*     */   
/*     */   private final int UndeterminedEnd;
/*     */   
/*     */   private PagedSeq$() {
/*  25 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public final int UndeterminedEnd() {
/*  26 */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   public <T> PagedSeq<T> fromIterator(Iterator source, ClassTag<T> evidence$1) {
/*  30 */     return new PagedSeq<T>((Function3<Object, Object, Object, Object>)new PagedSeq$$anonfun$fromIterator$1(source), evidence$1);
/*     */   }
/*     */   
/*     */   public static class PagedSeq$$anonfun$fromIterator$1 extends AbstractFunction3<Object, Object, Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Iterator source$1;
/*     */     
/*     */     public PagedSeq$$anonfun$fromIterator$1(Iterator source$1) {}
/*     */     
/*     */     public final int apply(Object data, int start, int len) {
/*  31 */       int i = 0;
/*  32 */       while (i < len && this.source$1.hasNext()) {
/*  33 */         scala.runtime.ScalaRunTime$.MODULE$.array_update(data, start + i, this.source$1.next());
/*  34 */         i++;
/*     */       } 
/*  36 */       return (i == 0) ? -1 : i;
/*     */     }
/*     */   }
/*     */   
/*     */   public <T> PagedSeq<T> fromIterable(Iterable source, ClassTag<T> evidence$2) {
/*  41 */     return fromIterator(source.iterator(), evidence$2);
/*     */   }
/*     */   
/*     */   public PagedSeq<Object> fromStrings(Iterator source) {
/*  45 */     ObjectRef current = new ObjectRef("");
/*  57 */     return new PagedSeq((Function3<Object, Object, Object, Object>)new PagedSeq$$anonfun$fromStrings$1(source, current), scala.reflect.ClassTag$.MODULE$.Char());
/*     */   }
/*     */   
/*     */   public final int scala$collection$immutable$PagedSeq$$more$1(char[] data, int start, int len, Iterator source$2, ObjectRef current$1) {
/*     */     // Byte code:
/*     */     //   0: aload #5
/*     */     //   2: getfield elem : Ljava/lang/Object;
/*     */     //   5: checkcast java/lang/String
/*     */     //   8: invokevirtual length : ()I
/*     */     //   11: iconst_0
/*     */     //   12: if_icmpeq -> 128
/*     */     //   15: getstatic scala/runtime/RichInt$.MODULE$ : Lscala/runtime/RichInt$;
/*     */     //   18: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   21: aload #5
/*     */     //   23: getfield elem : Ljava/lang/Object;
/*     */     //   26: checkcast java/lang/String
/*     */     //   29: invokevirtual length : ()I
/*     */     //   32: istore #7
/*     */     //   34: astore #6
/*     */     //   36: iload #7
/*     */     //   38: iload_3
/*     */     //   39: invokevirtual min$extension : (II)I
/*     */     //   42: istore #10
/*     */     //   44: aload #5
/*     */     //   46: getfield elem : Ljava/lang/Object;
/*     */     //   49: checkcast java/lang/String
/*     */     //   52: iconst_0
/*     */     //   53: iload #10
/*     */     //   55: aload_1
/*     */     //   56: iload_2
/*     */     //   57: invokevirtual getChars : (II[CI)V
/*     */     //   60: aload #5
/*     */     //   62: aload #5
/*     */     //   64: getfield elem : Ljava/lang/Object;
/*     */     //   67: checkcast java/lang/String
/*     */     //   70: iload #10
/*     */     //   72: invokevirtual substring : (I)Ljava/lang/String;
/*     */     //   75: putfield elem : Ljava/lang/Object;
/*     */     //   78: iload #10
/*     */     //   80: iload_3
/*     */     //   81: if_icmpne -> 89
/*     */     //   84: iload #10
/*     */     //   86: goto -> 157
/*     */     //   89: getstatic scala/runtime/RichInt$.MODULE$ : Lscala/runtime/RichInt$;
/*     */     //   92: getstatic scala/Predef$.MODULE$ : Lscala/Predef$;
/*     */     //   95: aload_0
/*     */     //   96: aload_1
/*     */     //   97: iload_2
/*     */     //   98: iload #10
/*     */     //   100: iadd
/*     */     //   101: iload_3
/*     */     //   102: iload #10
/*     */     //   104: isub
/*     */     //   105: aload #4
/*     */     //   107: aload #5
/*     */     //   109: invokevirtual scala$collection$immutable$PagedSeq$$more$1 : ([CIILscala/collection/Iterator;Lscala/runtime/ObjectRef;)I
/*     */     //   112: istore #9
/*     */     //   114: astore #8
/*     */     //   116: iload #9
/*     */     //   118: iconst_0
/*     */     //   119: invokevirtual max$extension : (II)I
/*     */     //   122: iload #10
/*     */     //   124: iadd
/*     */     //   125: goto -> 157
/*     */     //   128: aload #4
/*     */     //   130: invokeinterface hasNext : ()Z
/*     */     //   135: ifeq -> 156
/*     */     //   138: aload #5
/*     */     //   140: aload #4
/*     */     //   142: invokeinterface next : ()Ljava/lang/Object;
/*     */     //   147: checkcast java/lang/String
/*     */     //   150: putfield elem : Ljava/lang/Object;
/*     */     //   153: goto -> 0
/*     */     //   156: iconst_m1
/*     */     //   157: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #47	-> 0
/*     */     //   #48	-> 18
/*     */     //   #49	-> 44
/*     */     //   #50	-> 60
/*     */     //   #51	-> 78
/*     */     //   #52	-> 92
/*     */     //   #53	-> 128
/*     */     //   #54	-> 138
/*     */     //   #55	-> 153
/*     */     //   #56	-> 156
/*     */     //   #46	-> 157
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	158	0	this	Lscala/collection/immutable/PagedSeq$;
/*     */     //   0	158	1	data	[C
/*     */     //   0	158	2	start	I
/*     */     //   0	158	3	len	I
/*     */     //   0	158	4	source$2	Lscala/collection/Iterator;
/*     */     //   0	158	5	current$1	Lscala/runtime/ObjectRef;
/*     */     //   44	114	10	cnt	I
/*     */   }
/*     */   
/*     */   public static class PagedSeq$$anonfun$fromStrings$1 extends AbstractFunction3<char[], Object, Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Iterator source$2;
/*     */     
/*     */     private final ObjectRef current$1;
/*     */     
/*     */     public final int apply(char[] x$1, int x$2, int x$3) {
/*  57 */       return PagedSeq$.MODULE$.scala$collection$immutable$PagedSeq$$more$1(x$1, x$2, x$3, this.source$2, this.current$1);
/*     */     }
/*     */     
/*     */     public PagedSeq$$anonfun$fromStrings$1(Iterator source$2, ObjectRef current$1) {}
/*     */   }
/*     */   
/*     */   public PagedSeq<Object> fromStrings(Iterable source) {
/*  62 */     return fromStrings(source.iterator());
/*     */   }
/*     */   
/*     */   public PagedSeq<Object> fromLines(Iterator source) {
/*  69 */     BooleanRef isFirst = new BooleanRef(true);
/*  70 */     return fromStrings(source.map((Function1)new PagedSeq$$anonfun$fromLines$1(isFirst)));
/*     */   }
/*     */   
/*     */   public static class PagedSeq$$anonfun$fromLines$1 extends AbstractFunction1<String, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final BooleanRef isFirst$1;
/*     */     
/*     */     public PagedSeq$$anonfun$fromLines$1(BooleanRef isFirst$1) {}
/*     */     
/*     */     public final String apply(String line) {
/*  72 */       this.isFirst$1.elem = false;
/*  73 */       return this.isFirst$1.elem ? line : (
/*  74 */         new StringBuilder()).append("\n").append(line).toString();
/*     */     }
/*     */   }
/*     */   
/*     */   public PagedSeq<Object> fromLines(Iterable source) {
/*  83 */     return fromLines(source.iterator());
/*     */   }
/*     */   
/*     */   public PagedSeq<Object> fromReader(Reader source) {
/*  88 */     return new PagedSeq((Function3<Object, Object, Object, Object>)new PagedSeq$$anonfun$fromReader$1(source), scala.reflect.ClassTag$.MODULE$.Char());
/*     */   }
/*     */   
/*     */   public static class PagedSeq$$anonfun$fromReader$1 extends AbstractFunction3<char[], Object, Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Reader source$3;
/*     */     
/*     */     public final int apply(char[] x$4, int x$5, int x$6) {
/*  88 */       return this.source$3.read(x$4, x$5, x$6);
/*     */     }
/*     */     
/*     */     public PagedSeq$$anonfun$fromReader$1(Reader source$3) {}
/*     */   }
/*     */   
/*     */   public PagedSeq<Object> fromFile(File source) {
/*  93 */     return fromReader(new FileReader(source));
/*     */   }
/*     */   
/*     */   public PagedSeq<Object> fromFile(String source) {
/*  98 */     return fromFile(new File(source));
/*     */   }
/*     */   
/*     */   public PagedSeq<Object> fromSource(Source source) {
/* 103 */     return fromLines(source.getLines());
/*     */   }
/*     */   
/*     */   public class PagedSeq$$anonfun$toString$1 extends AbstractFunction1<T, StringBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final StringBuilder buf$1;
/*     */     
/*     */     public final StringBuilder apply(Object ch) {
/* 201 */       return this.buf$1.append(ch);
/*     */     }
/*     */     
/*     */     public PagedSeq$$anonfun$toString$1(PagedSeq $outer, StringBuilder buf$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\PagedSeq$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */