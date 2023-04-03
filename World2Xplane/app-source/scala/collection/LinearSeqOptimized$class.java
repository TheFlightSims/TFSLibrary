/*     */ package scala.collection;
/*     */ 
/*     */ import java.util.NoSuchElementException;
/*     */ import scala.Function1;
/*     */ import scala.Function2;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.mutable.Builder;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.RichInt$;
/*     */ 
/*     */ public abstract class LinearSeqOptimized$class {
/*     */   public static void $init$(LinearSeqOptimized $this) {}
/*     */   
/*     */   public static int length(LinearSeqOptimized $this) {
/*  37 */     LinearSeqOptimized these = $this;
/*  38 */     int len = 0;
/*     */     while (true) {
/*  39 */       if (these.isEmpty())
/*  43 */         return len; 
/*     */       len++;
/*     */       these = (LinearSeqOptimized)these.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object apply(LinearSeqOptimized $this, int n) {
/*  51 */     LinearSeqOptimized rest = (LinearSeqOptimized)$this.drop(n);
/*  52 */     if (n < 0 || rest.isEmpty())
/*  52 */       throw new IndexOutOfBoundsException(String.valueOf(BoxesRunTime.boxToInteger(n))); 
/*  53 */     return rest.head();
/*     */   }
/*     */   
/*     */   public static void foreach(LinearSeqOptimized $this, Function1 f) {
/*  58 */     LinearSeqOptimized these = $this;
/*     */     while (true) {
/*  59 */       if (these.isEmpty())
/*     */         return; 
/*  60 */       f.apply(these.head());
/*  61 */       these = (LinearSeqOptimized)these.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean forall(LinearSeqOptimized $this, Function1 p) {
/*  68 */     LinearSeqOptimized these = $this;
/*     */     while (true) {
/*  69 */       if (these.isEmpty())
/*  73 */         return true; 
/*     */       if (BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
/*     */         these = (LinearSeqOptimized)these.tail();
/*     */         continue;
/*     */       } 
/*     */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean exists(LinearSeqOptimized $this, Function1 p) {
/*  78 */     LinearSeqOptimized these = $this;
/*     */     while (true) {
/*  79 */       if (these.isEmpty())
/*  83 */         return false; 
/*     */       if (BoxesRunTime.unboxToBoolean(p.apply(these.head())))
/*     */         return true; 
/*     */       these = (LinearSeqOptimized)these.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean contains(LinearSeqOptimized $this, Object elem) {
/*  88 */     LinearSeqOptimized these = $this;
/*     */     while (true) {
/*  89 */       if (these.isEmpty())
/*  93 */         return false; 
/*     */       Object object;
/*     */       if (((object = these.head()) == elem) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, elem) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, elem) : object.equals(elem)))))
/*     */         return true; 
/*     */       these = (LinearSeqOptimized)these.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Option find(LinearSeqOptimized $this, Function1 p) {
/*  98 */     LinearSeqOptimized these = $this;
/*     */     while (true) {
/*  99 */       if (these.isEmpty())
/* 103 */         return (Option)None$.MODULE$; 
/*     */       if (BoxesRunTime.unboxToBoolean(p.apply(these.head())))
/*     */         return (Option)new Some(these.head()); 
/*     */       these = (LinearSeqOptimized)these.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object foldLeft(LinearSeqOptimized $this, Object z, Function2 f) {
/* 108 */     Object acc = z;
/* 109 */     LinearSeqOptimized these = $this;
/*     */     while (true) {
/* 110 */       if (these.isEmpty())
/* 114 */         return acc; 
/*     */       acc = f.apply(acc, these.head());
/*     */       these = (LinearSeqOptimized)these.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Object foldRight(LinearSeqOptimized $this, Object z, Function2 f) {
/* 119 */     return $this.isEmpty() ? z : 
/* 120 */       f.apply($this.head(), ((LinearSeqOptimized)$this.tail()).foldRight(z, f));
/*     */   }
/*     */   
/*     */   public static Object reduceLeft(LinearSeqOptimized $this, Function2 f) {
/* 124 */     if ($this.isEmpty())
/* 124 */       throw new UnsupportedOperationException("empty.reduceLeft"); 
/* 124 */     return (
/* 125 */       (LinearSeqOptimized)$this.tail()).foldLeft($this.head(), f);
/*     */   }
/*     */   
/*     */   public static Object reduceRight(LinearSeqOptimized $this, Function2 op) {
/* 129 */     if ($this.isEmpty())
/* 129 */       throw new UnsupportedOperationException("Nil.reduceRight"); 
/* 129 */     return 
/* 130 */       ((SeqLike)$this.tail()).isEmpty() ? $this.head() : 
/* 131 */       op.apply($this.head(), ((LinearSeqOptimized)$this.tail()).reduceRight(op));
/*     */   }
/*     */   
/*     */   public static Object last(LinearSeqOptimized $this) {
/* 135 */     if ($this.isEmpty())
/* 135 */       throw new NoSuchElementException(); 
/* 136 */     LinearSeqOptimized these = $this;
/* 137 */     LinearSeqOptimized nx = (LinearSeqOptimized)$this.tail();
/*     */     while (true) {
/* 138 */       if (nx.isEmpty())
/* 142 */         return these.head(); 
/*     */       these = nx;
/*     */       nx = (LinearSeqOptimized)nx.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static LinearSeqOptimized take(LinearSeqOptimized $this, int n) {
/* 147 */     Builder b = $this.newBuilder();
/* 148 */     int i = 0;
/* 149 */     LinearSeqOptimized these = (LinearSeqOptimized)$this.repr();
/* 150 */     while (!these.isEmpty() && i < n) {
/* 151 */       i++;
/* 152 */       b.$plus$eq(these.head());
/* 153 */       these = (LinearSeqOptimized)these.tail();
/*     */     } 
/* 155 */     return (LinearSeqOptimized)b.result();
/*     */   }
/*     */   
/*     */   public static LinearSeqOptimized drop(LinearSeqOptimized $this, int n) {
/* 160 */     LinearSeqOptimized these = (LinearSeqOptimized)$this.repr();
/* 161 */     int count = n;
/* 162 */     while (!these.isEmpty() && count > 0) {
/* 163 */       these = (LinearSeqOptimized)these.tail();
/* 164 */       count--;
/*     */     } 
/* 177 */     return these;
/*     */   }
/*     */   
/*     */   public static LinearSeqOptimized dropRight(LinearSeqOptimized $this, int n) {
/* 182 */     Builder b = $this.newBuilder();
/* 183 */     LinearSeqOptimized these = $this;
/* 184 */     LinearSeqOptimized lead = (LinearSeqOptimized)$this.drop(n);
/*     */     while (true) {
/* 185 */       if (lead.isEmpty())
/* 190 */         return (LinearSeqOptimized)b.result(); 
/*     */       b.$plus$eq(these.head());
/*     */       these = (LinearSeqOptimized)these.tail();
/*     */       lead = (LinearSeqOptimized)lead.tail();
/*     */     } 
/*     */   }
/*     */   
/*     */   public static LinearSeqOptimized slice(LinearSeqOptimized $this, int from, int until) {
/* 195 */     LinearSeqOptimized these = (LinearSeqOptimized)$this.repr();
/* 196 */     Predef$ predef$ = Predef$.MODULE$;
/* 196 */     int count = RichInt$.MODULE$.max$extension(from, 0);
/* 197 */     if (until <= count)
/* 198 */       return (LinearSeqOptimized)$this.newBuilder().result(); 
/* 200 */     Builder b = $this.newBuilder();
/* 201 */     int sliceElems = until - count;
/* 202 */     while (these.nonEmpty() && count > 0) {
/* 203 */       these = (LinearSeqOptimized)these.tail();
/* 204 */       count--;
/*     */     } 
/* 206 */     while (these.nonEmpty() && sliceElems > 0) {
/* 207 */       sliceElems--;
/* 208 */       b.$plus$eq(these.head());
/* 209 */       these = (LinearSeqOptimized)these.tail();
/*     */     } 
/* 211 */     return (LinearSeqOptimized)b.result();
/*     */   }
/*     */   
/*     */   public static LinearSeqOptimized takeWhile(LinearSeqOptimized $this, Function1 p) {
/* 216 */     Builder b = $this.newBuilder();
/* 217 */     LinearSeqOptimized these = $this;
/* 218 */     while (!these.isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
/* 219 */       b.$plus$eq(these.head());
/* 220 */       these = (LinearSeqOptimized)these.tail();
/*     */     } 
/* 222 */     return (LinearSeqOptimized)b.result();
/*     */   }
/*     */   
/*     */   public static Tuple2 span(LinearSeqOptimized $this, Function1 p) {
/* 227 */     LinearSeqOptimized these = (LinearSeqOptimized)$this.repr();
/* 228 */     Builder b = $this.newBuilder();
/* 229 */     while (!these.isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
/* 230 */       b.$plus$eq(these.head());
/* 231 */       these = (LinearSeqOptimized)these.tail();
/*     */     } 
/* 233 */     return new Tuple2(b.result(), these);
/*     */   }
/*     */   
/*     */   public static boolean sameElements(LinearSeqOptimized $this, GenIterable that) {
/*     */     boolean bool;
/* 237 */     if (that instanceof LinearSeq) {
/* 237 */       LinearSeq<Object> linearSeq1 = (LinearSeq)that;
/* 239 */       LinearSeqOptimized these = $this;
/* 240 */       LinearSeq<Object> those = linearSeq1;
/* 241 */       while (!these.isEmpty() && !those.isEmpty()) {
/* 241 */         Object object1 = those.head();
/*     */         Object object;
/* 241 */         if (((object = these.head()) == object1) ? true : ((object == null) ? false : ((object instanceof Number) ? BoxesRunTime.equalsNumObject((Number)object, object1) : ((object instanceof Character) ? BoxesRunTime.equalsCharObject((Character)object, object1) : object.equals(object1))))) {
/* 242 */           these = (LinearSeqOptimized)these.tail();
/* 243 */           those = (LinearSeq)those.tail();
/*     */         } 
/*     */       } 
/* 245 */       bool = (these.isEmpty() && those.isEmpty());
/*     */     } else {
/* 247 */       bool = $this.scala$collection$LinearSeqOptimized$$super$sameElements(that);
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   private static final int loop$1(LinearSeqOptimized $this, int i, LinearSeqOptimized xs, int len$1) {
/*     */     // Byte code:
/*     */     //   0: iload_1
/*     */     //   1: iload_3
/*     */     //   2: if_icmpne -> 22
/*     */     //   5: aload_2
/*     */     //   6: invokeinterface isEmpty : ()Z
/*     */     //   11: ifeq -> 18
/*     */     //   14: iconst_0
/*     */     //   15: goto -> 32
/*     */     //   18: iconst_1
/*     */     //   19: goto -> 32
/*     */     //   22: aload_2
/*     */     //   23: invokeinterface isEmpty : ()Z
/*     */     //   28: ifeq -> 33
/*     */     //   31: iconst_m1
/*     */     //   32: ireturn
/*     */     //   33: aload_0
/*     */     //   34: iload_1
/*     */     //   35: iconst_1
/*     */     //   36: iadd
/*     */     //   37: aload_2
/*     */     //   38: invokeinterface tail : ()Ljava/lang/Object;
/*     */     //   43: checkcast scala/collection/LinearSeqOptimized
/*     */     //   46: astore_2
/*     */     //   47: istore_1
/*     */     //   48: astore_0
/*     */     //   49: goto -> 0
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #253	-> 0
/*     */     //   #254	-> 5
/*     */     //   #255	-> 22
/*     */     //   #256	-> 31
/*     */     //   #252	-> 32
/*     */     //   #258	-> 33
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	52	0	$this	Lscala/collection/LinearSeqOptimized;
/*     */     //   0	52	1	i	I
/*     */     //   0	52	2	xs	Lscala/collection/LinearSeqOptimized;
/*     */     //   0	52	3	len$1	I
/*     */   }
/*     */   
/*     */   public static int lengthCompare(LinearSeqOptimized $this, int len) {
/* 260 */     return (len < 0) ? 1 : 
/* 261 */       loop$1($this, 0, $this, len);
/*     */   }
/*     */   
/*     */   public static boolean isDefinedAt(LinearSeqOptimized $this, int x) {
/* 265 */     return (x >= 0 && $this.lengthCompare(x) > 0);
/*     */   }
/*     */   
/*     */   public static int segmentLength(LinearSeqOptimized $this, Function1 p, int from) {
/* 269 */     int i = 0;
/* 270 */     LinearSeqOptimized these = (LinearSeqOptimized)$this.drop(from);
/* 271 */     while (!these.isEmpty() && BoxesRunTime.unboxToBoolean(p.apply(these.head()))) {
/* 272 */       i++;
/* 273 */       these = (LinearSeqOptimized)these.tail();
/*     */     } 
/* 275 */     return i;
/*     */   }
/*     */   
/*     */   public static int indexWhere(LinearSeqOptimized $this, Function1 p, int from) {
/* 280 */     int i = from;
/* 281 */     LinearSeqOptimized these = (LinearSeqOptimized)$this.drop(from);
/* 282 */     while (these.nonEmpty()) {
/* 283 */       if (BoxesRunTime.unboxToBoolean(p.apply(these.head())))
/* 284 */         return i; 
/* 286 */       i++;
/* 287 */       these = (LinearSeqOptimized)these.tail();
/*     */     } 
/* 289 */     return -1;
/*     */   }
/*     */   
/*     */   public static int lastIndexWhere(LinearSeqOptimized $this, Function1 p, int end) {
/* 294 */     int i = 0;
/* 295 */     LinearSeqOptimized these = $this;
/* 296 */     int last = -1;
/* 297 */     while (!these.isEmpty() && i <= end) {
/* 298 */       if (BoxesRunTime.unboxToBoolean(p.apply(these.head())))
/* 298 */         last = i; 
/* 299 */       these = (LinearSeqOptimized)these.tail();
/* 300 */       i++;
/*     */     } 
/* 302 */     return last;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\LinearSeqOptimized$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */