/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.Reader;
/*     */ import scala.Function1;
/*     */ import scala.Function3;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractSeq;
/*     */ import scala.collection.GenIterable;
/*     */ import scala.collection.GenMap;
/*     */ import scala.collection.GenSeq;
/*     */ import scala.collection.GenTraversable;
/*     */ import scala.collection.IndexedSeq;
/*     */ import scala.collection.IndexedSeqLike;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.IterableView;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Traversable;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.collection.TraversableView;
/*     */ import scala.collection.generic.GenericCompanion;
/*     */ import scala.collection.mutable.Buffer;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.io.Source;
/*     */ import scala.reflect.ClassTag;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction3;
/*     */ import scala.runtime.BooleanRef;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.RichInt$;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005mw!B\001\003\021\003I\021\001\003)bO\026$7+Z9\013\005\r!\021!C5n[V$\030M\0317f\025\t)a!\001\006d_2dWm\031;j_:T\021aB\001\006g\016\fG.Y\002\001!\tQ1\"D\001\003\r\025a!\001#\001\016\005!\001\026mZ3e'\026\f8CA\006\017!\ty\001#D\001\007\023\t\tbA\001\004B]f\024VM\032\005\006'-!\t\001F\001\007y%t\027\016\036 \025\003%AqAF\006C\002\023\025q#A\bV]\022,G/\032:nS:,G-\0228e+\005Ar\"A\r\036\t}|\000\000@\005\0077-\001\013Q\002\r\002!UsG-\032;fe6Lg.\0323F]\022\004\003\"B\017\f\t\003q\022\001\0044s_6LE/\032:bi>\024XcA\020\002>Q\031\001%!\022\025\007\005\ny\004\005\003\013E\005mb\001\002\007\003\001\r*\"\001J\026\024\007\t*C\007E\002'O%j\021\001B\005\003Q\021\0211\"\0212tiJ\f7\r^*fcB\021!f\013\007\001\t\025a#E1\001.\005\005!\026C\001\0302!\tyq&\003\0021\r\t9aj\034;iS:<\007CA\b3\023\t\031dAA\002B]f\0042AJ\033*\023\t1DA\001\006J]\022,\0070\0323TKFD\001\002\017\022\003\002\003\006I!O\001\005[>\024X\r\005\004\020uqzthP\005\003w\031\021\021BR;oGRLwN\\\032\021\007=i\024&\003\002?\r\t)\021I\035:bsB\021q\002Q\005\003\003\032\0211!\0238u\021!\031%E!A!\002\023!\025A\0024jeN$\030\007E\002\013\013&J!A\022\002\003\tA\013w-\032\005\t\021\n\022\t\021)A\005\005)1\017^1si\"A!J\tB\001B\003%q(A\002f]\022D\001\002\024\022\003\004\003\006Y!T\001\013KZLG-\0328dK\022\032\004c\001(RS5\tqJ\003\002Q\r\0059!/\0324mK\016$\030B\001*P\005!\031E.Y:t)\006<\007\"B\n#\t#!F#B+Y3j[FC\001,X!\rQ!%\013\005\006\031N\003\035!\024\005\006qM\003\r!\017\005\006\007N\003\r\001\022\005\006\021N\003\ra\020\005\006\025N\003\ra\020\005\006'\t\"\t!\030\013\003=\006$\"AV0\t\017\001d\026\021!a\002\033\006QQM^5eK:\034W\r\n\033\t\013ab\006\031A\035\t\017\r\024\003\031!C\005I\00691-\036:sK:$X#\001#\t\017\031\024\003\031!C\005O\006Y1-\036:sK:$x\fJ3r)\tA7\016\005\002\020S&\021!N\002\002\005+:LG\017C\004mK\006\005\t\031\001#\002\007a$\023\007\003\004oE\001\006K\001R\001\tGV\024(/\0328uA!)\001O\tC\005I\0061A.\031;fgRDQA\035\022\005\nM\fq!\0313e\033>\024X\rF\001E\021\025)(\005\"\003w\003\021\001\030mZ3\025\005\021;\b\"\002=u\001\004y\024\001C1cg&tG-\032=\t\013i\024C\021A>\002\r1,gn\032;i+\005y\004\"B?#\t\003q\030!B1qa2LHCA\025\000\021\031\t\t\001 a\001\005)\021N\0343fq\"9\021Q\001\022\005B\005\035\021aC5t\t\0264\027N\\3e\003R$B!!\003\002\020A\031q\"a\003\n\007\0055aAA\004C_>dW-\0318\t\017\005\005\0211\001a\001!9\0211\003\022\005B\005U\021!B:mS\016,G#\002,\002\030\005m\001bBA\r\003#\001\raP\001\007?N$\030M\035;\t\017\005u\021\021\003a\001\005!q,\0328e\021\035\t\031B\tC\001\003C!2AVA\022\021\031A\025q\004a\001!9\021q\005\022\005B\005%\022\001\003;p'R\024\030N\\4\025\005\005-\002\003BA\027\003oi!!a\f\013\t\005E\0221G\001\005Y\006twM\003\002\0026\005!!.\031<b\023\021\tI$a\f\003\rM#(/\0338h!\rQ\023Q\b\003\006Yq\021\r!\f\005\n\003\003b\022\021!a\002\003\007\n!\"\032<jI\026t7-\032\0232!\021q\025+a\017\t\017\005\035C\0041\001\002J\00511o\\;sG\026\004RAJA&\003wI1!!\024\005\005!IE/\032:bi>\024\bbBA)\027\021\005\0211K\001\rMJ|W.\023;fe\006\024G.Z\013\005\003+\ni\006\006\003\002X\005\025D\003BA-\003?\002BA\003\022\002\\A\031!&!\030\005\r1\nyE1\001.\021)\t\t'a\024\002\002\003\017\0211M\001\013KZLG-\0328dK\022\022\004\003\002(R\0037B\001\"a\022\002P\001\007\021q\r\t\006\025\005%\0241L\005\004\003W\022!\001C%uKJ\f'\r\\3\t\017\005=4\002\"\001\002r\005YaM]8n'R\024\030N\\4t)\021\t\031(a\037\021\t)\021\023Q\017\t\004\037\005]\024bAA=\r\t!1\t[1s\021!\t9%!\034A\002\005u\004#\002\024\002L\005}\004\003BAA\003\017s1aDAB\023\r\t)IB\001\007!J,G-\0324\n\t\005e\022\021\022\006\004\003\0133\001bBA8\027\021\005\021Q\022\013\005\003g\ny\t\003\005\002H\005-\005\031AAI!\025Q\021\021NA@\021\035\t)j\003C\001\003/\013\021B\032:p[2Kg.Z:\025\t\005M\024\021\024\005\t\003\017\n\031\n1\001\002~!9\021QS\006\005\002\005uE\003BA:\003?C\001\"a\022\002\034\002\007\021\021\023\005\b\003G[A\021AAS\003)1'o\\7SK\006$WM\035\013\005\003g\n9\013\003\005\002H\005\005\006\031AAU!\021\tY+!-\016\005\0055&\002BAX\003g\t!![8\n\t\005M\026Q\026\002\007%\026\fG-\032:\t\017\005]6\002\"\001\002:\006AaM]8n\r&dW\r\006\003\002t\005m\006\002CA$\003k\003\r!!0\021\t\005-\026qX\005\005\003\003\fiK\001\003GS2,\007bBA\\\027\021\005\021Q\031\013\005\003g\n9\r\003\005\002H\005\r\007\031AA@\021\035\tYm\003C\001\003\033\f!B\032:p[N{WO]2f)\021\t\031(a4\t\021\005\035\023\021\032a\001\003#\004B!a5\002X6\021\021Q\033\006\004\003_3\021\002BAm\003+\024aaU8ve\016,\007")
/*     */ public class PagedSeq<T> extends AbstractSeq<T> implements IndexedSeq<T> {
/*     */   private final Function3<Object, Object, Object, Object> more;
/*     */   
/*     */   private final Page<T> first1;
/*     */   
/*     */   private final int start;
/*     */   
/*     */   private final int end;
/*     */   
/*     */   private final ClassTag<T> evidence$3;
/*     */   
/*     */   private Page<T> current;
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
/*  33 */         ScalaRunTime$.MODULE$.array_update(data, start + i, this.source$1.next());
/*  34 */         i++;
/*     */       } 
/*  36 */       return (i == 0) ? -1 : i;
/*     */     }
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
/*     */   public GenericCompanion<IndexedSeq> companion() {
/* 128 */     return IndexedSeq.class.companion(this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<T> seq() {
/* 128 */     return IndexedSeq.class.seq(this);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 128 */     return IndexedSeqLike.class.hashCode((IndexedSeqLike)this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<T> thisCollection() {
/* 128 */     return IndexedSeqLike.class.thisCollection((IndexedSeqLike)this);
/*     */   }
/*     */   
/*     */   public IndexedSeq<T> toCollection(Object repr) {
/* 128 */     return IndexedSeqLike.class.toCollection((IndexedSeqLike)this, repr);
/*     */   }
/*     */   
/*     */   public Iterator<T> iterator() {
/* 128 */     return IndexedSeqLike.class.iterator((IndexedSeqLike)this);
/*     */   }
/*     */   
/*     */   public <A1> Buffer<A1> toBuffer() {
/* 128 */     return IndexedSeqLike.class.toBuffer((IndexedSeqLike)this);
/*     */   }
/*     */   
/*     */   public PagedSeq(Function3<Object, Object, Object, Object> more, Page<T> first1, int start, int end, ClassTag<T> evidence$3) {
/* 128 */     IndexedSeqLike.class.$init$((IndexedSeqLike)this);
/* 128 */     IndexedSeq.class.$init$(this);
/* 138 */     this.current = first1;
/*     */   }
/*     */   
/*     */   public PagedSeq(Function3<Object, Object, Object, Object> more, ClassTag<T> evidence$4) {
/*     */     this(more, new Page<T>(0, evidence$4), 0, 2147483647, evidence$4);
/*     */   }
/*     */   
/*     */   private Page<T> current() {
/* 138 */     return this.current;
/*     */   }
/*     */   
/*     */   private void current_$eq(Page<T> x$1) {
/* 138 */     this.current = x$1;
/*     */   }
/*     */   
/*     */   private Page<T> latest() {
/* 140 */     return this.first1.latest();
/*     */   }
/*     */   
/*     */   private Page<T> addMore() {
/* 142 */     return latest().addMore(this.more);
/*     */   }
/*     */   
/*     */   private Page<T> page(int absindex) {
/* 145 */     if (absindex < current().start())
/* 146 */       current_$eq(this.first1); 
/* 147 */     while (absindex >= current().end() && current().next() != null)
/* 148 */       current_$eq(current().next()); 
/* 149 */     while (absindex >= current().end() && !current().isLast())
/* 150 */       current_$eq(addMore()); 
/* 152 */     return current();
/*     */   }
/*     */   
/*     */   public int length() {
/*     */     while (true) {
/* 159 */       if (latest().isLast()) {
/* 160 */         int i = latest().end();
/* 160 */         Predef$ predef$ = Predef$.MODULE$;
/* 160 */         return RichInt$.MODULE$.min$extension(i, this.end) - this.start;
/*     */       } 
/*     */       addMore();
/*     */     } 
/*     */   }
/*     */   
/*     */   public T apply(int index) {
/* 166 */     if (isDefinedAt(index))
/* 166 */       return page(index + this.start).apply(index + this.start); 
/* 167 */     throw new IndexOutOfBoundsException(BoxesRunTime.boxToInteger(index).toString());
/*     */   }
/*     */   
/*     */   public boolean isDefinedAt(int index) {
/* 175 */     if (index >= 0 && index < this.end - this.start) {
/* 176 */       Page<T> p = page(index + this.start);
/* 176 */       if ((index + this.start < p.end()));
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public PagedSeq<T> slice(int _start, int _end) {
/* 185 */     page(this.start);
/* 186 */     int s = this.start + _start;
/* 187 */     int e = (_end == Integer.MAX_VALUE) ? _end : (this.start + _end);
/* 188 */     Page<T> f = this.first1;
/* 189 */     for (; f.end() <= s && !f.isLast(); f = f.next());
/* 190 */     return new PagedSeq(this.more, f, s, e, this.evidence$3);
/*     */   }
/*     */   
/*     */   public PagedSeq<T> slice(int start) {
/* 196 */     return slice(start, 2147483647);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 200 */     StringBuilder buf = new StringBuilder();
/* 201 */     iterator().foreach((Function1)new PagedSeq$$anonfun$toString$1(this, (PagedSeq<T>)buf));
/* 202 */     return buf.toString();
/*     */   }
/*     */   
/*     */   public static PagedSeq<Object> fromSource(Source paramSource) {
/*     */     return PagedSeq$.MODULE$.fromSource(paramSource);
/*     */   }
/*     */   
/*     */   public static PagedSeq<Object> fromFile(String paramString) {
/*     */     return PagedSeq$.MODULE$.fromFile(paramString);
/*     */   }
/*     */   
/*     */   public static PagedSeq<Object> fromFile(File paramFile) {
/*     */     return PagedSeq$.MODULE$.fromFile(paramFile);
/*     */   }
/*     */   
/*     */   public static PagedSeq<Object> fromReader(Reader paramReader) {
/*     */     return PagedSeq$.MODULE$.fromReader(paramReader);
/*     */   }
/*     */   
/*     */   public static PagedSeq<Object> fromLines(Iterable<String> paramIterable) {
/*     */     return PagedSeq$.MODULE$.fromLines(paramIterable);
/*     */   }
/*     */   
/*     */   public static PagedSeq<Object> fromLines(Iterator<String> paramIterator) {
/*     */     return PagedSeq$.MODULE$.fromLines(paramIterator);
/*     */   }
/*     */   
/*     */   public static PagedSeq<Object> fromStrings(Iterable<String> paramIterable) {
/*     */     return PagedSeq$.MODULE$.fromStrings(paramIterable);
/*     */   }
/*     */   
/*     */   public static PagedSeq<Object> fromStrings(Iterator<String> paramIterator) {
/*     */     return PagedSeq$.MODULE$.fromStrings(paramIterator);
/*     */   }
/*     */   
/*     */   public static <T> PagedSeq<T> fromIterable(Iterable<T> paramIterable, ClassTag<T> paramClassTag) {
/*     */     return PagedSeq$.MODULE$.fromIterable(paramIterable, paramClassTag);
/*     */   }
/*     */   
/*     */   public static <T> PagedSeq<T> fromIterator(Iterator<T> paramIterator, ClassTag<T> paramClassTag) {
/*     */     return PagedSeq$.MODULE$.fromIterator(paramIterator, paramClassTag);
/*     */   }
/*     */   
/*     */   public static int UndeterminedEnd() {
/*     */     return PagedSeq$.MODULE$.UndeterminedEnd();
/*     */   }
/*     */   
/*     */   public class PagedSeq$$anonfun$toString$1 extends AbstractFunction1<T, StringBuilder> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final StringBuilder buf$1;
/*     */     
/*     */     public final StringBuilder apply(Object ch) {
/*     */       return this.buf$1.append(ch);
/*     */     }
/*     */     
/*     */     public PagedSeq$$anonfun$toString$1(PagedSeq $outer, StringBuilder buf$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\PagedSeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */