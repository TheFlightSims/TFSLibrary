/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Function0;
/*     */ import scala.Serializable;
/*     */ import scala.collection.AbstractIterator;
/*     */ import scala.collection.Iterator;
/*     */ import scala.collection.Iterator$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.Nothing$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0314A!\001\002\003\023\tq1\013\036:fC6LE/\032:bi>\024(BA\002\005\003%IW.\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!\006\002\013#M\031\001aC\016\021\0071iq\"D\001\005\023\tqAA\001\tBEN$(/Y2u\023R,'/\031;peB\021\001#\005\007\001\t\031\021\002\001\"b\001'\t\t\021)\005\002\0251A\021QCF\007\002\r%\021qC\002\002\b\035>$\b.\0338h!\t)\022$\003\002\033\r\t\031\021I\\=\021\0071ar\"\003\002\036\t\tA\021\n^3sCR|'\017C\003 \001\021%\001%\001\004=S:LGO\020\013\002CA\031!\005A\b\016\003\tAQa\b\001\005\002\021\"\"!I\023\t\013\031\032\003\031A\024\002\tM,GN\032\t\004E!z\021BA\025\003\005\031\031FO]3b[\032!1\006\001\001-\005!a\025M_=DK2d7C\001\026.!\t)b&\003\0020\r\t1\021I\\=SK\032D\001\"\r\026\003\002\023\006IAM\001\003gR\0042!F\032(\023\t!dA\001\005=Eft\027-\\3?\021\025y\"\006\"\0017)\t9\024\b\005\0029U5\t\001\001\003\0042k\021\005\rA\r\005\tw)B)\031!C\001y\005\ta/F\001(\021!q$\006#A!B\0239\023A\001<!\021%\001\005\0011AA\002\023%\021)A\003uQ\026\034X-F\0018\021%\031\005\0011AA\002\023%A)A\005uQ\026\034Xm\030\023fcR\021Q\t\023\t\003+\031K!a\022\004\003\tUs\027\016\036\005\b\023\n\013\t\0211\0018\003\rAH%\r\005\007\027\002\001\013\025B\034\002\rQDWm]3!\021\025i\005\001\"\001O\003\035A\027m\035(fqR,\022a\024\t\003+AK!!\025\004\003\017\t{w\016\\3b]\")1\013\001C\001)\006!a.\032=u)\005y\001\"\002,\001\t\003b\024\001\003;p'R\024X-Y7\t\013a\003A\021I-\002\rQ|G*[:u+\005Q\006cA.d\0379\021A,\031\b\003;\002l\021A\030\006\003?\"\ta\001\020:p_Rt\024\"A\004\n\005\t4\021a\0029bG.\fw-Z\005\003I\026\024A\001T5ti*\021!M\002")
/*     */ public final class StreamIterator<A> extends AbstractIterator<A> implements Iterator<A> {
/*     */   private LazyCell these;
/*     */   
/*     */   private StreamIterator() {}
/*     */   
/*     */   public StreamIterator(Stream self) {
/* 963 */     this();
/* 964 */     these_$eq(new LazyCell(this, (Function0<Stream<A>>)new StreamIterator$$anonfun$$init$$1(this, (StreamIterator<A>)self)));
/*     */   }
/*     */   
/*     */   public class StreamIterator$$anonfun$$init$$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Stream self$1;
/*     */     
/*     */     public final Stream<A> apply() {
/* 964 */       return this.self$1;
/*     */     }
/*     */     
/*     */     public StreamIterator$$anonfun$$init$$1(StreamIterator $outer, Stream self$1) {}
/*     */   }
/*     */   
/*     */   public class LazyCell {
/*     */     private final Function0<Stream<A>> st;
/*     */     
/*     */     private Stream<A> v;
/*     */     
/*     */     private volatile boolean bitmap$0;
/*     */     
/*     */     public LazyCell(StreamIterator $outer, Function0<Stream<A>> st) {}
/*     */     
/*     */     public Stream<A> v() {
/* 969 */       return this.bitmap$0 ? this.v : v$lzycompute();
/*     */     }
/*     */     
/*     */     private Stream v$lzycompute() {
/* 969 */       synchronized (this) {
/* 969 */         if (!this.bitmap$0) {
/* 969 */           this.v = (Stream<A>)this.st.apply();
/* 969 */           this.bitmap$0 = true;
/*     */         } 
/*     */         /* monitor exit ThisExpression{InnerObjectType{ObjectType{scala/collection/immutable/StreamIterator}.Lscala/collection/immutable/StreamIterator$LazyCell;}} */
/* 969 */         this.st = null;
/* 969 */         return this.v;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private LazyCell these() {
/* 972 */     return this.these;
/*     */   }
/*     */   
/*     */   private void these_$eq(LazyCell x$1) {
/* 972 */     this.these = x$1;
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 974 */     return these().v().nonEmpty();
/*     */   }
/*     */   
/*     */   public A next() {
/* 978 */     Stream<A> cur = these().v();
/* 979 */     Object result = cur.head();
/* 980 */     these_$eq(new LazyCell(this, (Function0<Stream<A>>)new StreamIterator$$anonfun$next$1(this, (StreamIterator<A>)cur)));
/* 981 */     return isEmpty() ? (A)Iterator$.MODULE$.empty().next() : (A)result;
/*     */   }
/*     */   
/*     */   public class StreamIterator$$anonfun$next$1 extends AbstractFunction0<Stream<A>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Stream cur$1;
/*     */     
/*     */     public final Stream<A> apply() {
/*     */       return (Stream<A>)this.cur$1.tail();
/*     */     }
/*     */     
/*     */     public StreamIterator$$anonfun$next$1(StreamIterator $outer, Stream cur$1) {}
/*     */   }
/*     */   
/*     */   public Stream<A> toStream() {
/* 984 */     Stream<A> result = these().v();
/* 985 */     these_$eq(new LazyCell(this, (Function0<Stream<A>>)new StreamIterator$$anonfun$toStream$1(this)));
/* 986 */     return result;
/*     */   }
/*     */   
/*     */   public class StreamIterator$$anonfun$toStream$1 extends AbstractFunction0<Stream<Nothing$>> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final Stream<Nothing$> apply() {
/*     */       return Stream$.MODULE$.empty();
/*     */     }
/*     */     
/*     */     public StreamIterator$$anonfun$toStream$1(StreamIterator $outer) {}
/*     */   }
/*     */   
/*     */   public List<A> toList() {
/* 988 */     return toStream().toList();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\StreamIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */