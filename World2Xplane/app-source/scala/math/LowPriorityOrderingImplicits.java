/*     */ package scala.math;
/*     */ 
/*     */ import java.util.Comparator;
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Some;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\0053q!\001\002\021\002\007\005qA\001\017M_^\004&/[8sSRLxJ\0353fe&tw-S7qY&\034\027\016^:\013\005\r!\021\001B7bi\"T\021!B\001\006g\016\fG.Y\002\001'\t\001\001\002\005\002\n\0255\tA!\003\002\f\t\t1\021I\\=SK\032DQ!\004\001\005\0029\ta\001J5oSR$C#A\b\021\005%\001\022BA\t\005\005\021)f.\033;\t\013M\001A1\001\013\002\017=\024H-\032:fIV\021Q\003\b\013\003-\025\0022a\006\r\033\033\005\021\021BA\r\003\005!y%\017Z3sS:<\007CA\016\035\031\001!Q!\b\nC\002y\021\021!Q\t\003?\t\002\"!\003\021\n\005\005\"!a\002(pi\"Lgn\032\t\003\023\rJ!\001\n\003\003\007\005s\027\020C\004'%\005\005\t9A\024\002\025\0254\030\016Z3oG\026$\023\007\005\003\nQiQ\023BA\025\005\005%1UO\\2uS>t\027\007E\002,aii\021\001\f\006\003[9\nA\001\\1oO*\tq&\001\003kCZ\f\027BA\031-\005)\031u.\0349be\006\024G.\032\005\006g\001!\031\001N\001\025G>l\007/\031:bi>\024Hk\\(sI\026\024\030N\\4\026\005UBDC\001\034:!\r9\002d\016\t\0037a\"Q!\b\032C\002yAQA\017\032A\004m\n1aY7q!\rathN\007\002{)\021aHL\001\005kRLG.\003\002A{\tQ1i\\7qCJ\fGo\034:")
/*     */ public interface LowPriorityOrderingImplicits {
/*     */   <A> Ordering<A> ordered(Function1<A, Comparable<A>> paramFunction1);
/*     */   
/*     */   <A> Ordering<A> comparatorToOrdering(Comparator<A> paramComparator);
/*     */   
/*     */   public class LowPriorityOrderingImplicits$$anon$6 implements Ordering<A> {
/*     */     private final Function1 evidence$1$1;
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 149 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 149 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 149 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 149 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 149 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 149 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public A max(Object x, Object y) {
/* 149 */       return (A)Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public A min(Object x, Object y) {
/* 149 */       return (A)Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<A> reverse() {
/* 149 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 149 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<A>.Ops mkOrderingOps(Object lhs) {
/* 149 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public LowPriorityOrderingImplicits$$anon$6(LowPriorityOrderingImplicits $outer, Function1 evidence$1$1) {
/* 149 */       PartialOrdering$class.$init$(this);
/* 149 */       Ordering$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Object x, Object y) {
/* 150 */       return ((Comparable<Object>)this.evidence$1$1.apply(x)).compareTo(y);
/*     */     }
/*     */   }
/*     */   
/*     */   public class LowPriorityOrderingImplicits$$anon$7 implements Ordering<A> {
/*     */     private final Comparator cmp$2;
/*     */     
/*     */     public Some<Object> tryCompare(Object x, Object y) {
/* 152 */       return Ordering$class.tryCompare(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lteq(Object x, Object y) {
/* 152 */       return Ordering$class.lteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gteq(Object x, Object y) {
/* 152 */       return Ordering$class.gteq(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean lt(Object x, Object y) {
/* 152 */       return Ordering$class.lt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean gt(Object x, Object y) {
/* 152 */       return Ordering$class.gt(this, x, y);
/*     */     }
/*     */     
/*     */     public boolean equiv(Object x, Object y) {
/* 152 */       return Ordering$class.equiv(this, x, y);
/*     */     }
/*     */     
/*     */     public A max(Object x, Object y) {
/* 152 */       return (A)Ordering$class.max(this, x, y);
/*     */     }
/*     */     
/*     */     public A min(Object x, Object y) {
/* 152 */       return (A)Ordering$class.min(this, x, y);
/*     */     }
/*     */     
/*     */     public Ordering<A> reverse() {
/* 152 */       return Ordering$class.reverse(this);
/*     */     }
/*     */     
/*     */     public <U> Ordering<U> on(Function1 f) {
/* 152 */       return Ordering$class.on(this, f);
/*     */     }
/*     */     
/*     */     public Ordering<A>.Ops mkOrderingOps(Object lhs) {
/* 152 */       return Ordering$class.mkOrderingOps(this, lhs);
/*     */     }
/*     */     
/*     */     public LowPriorityOrderingImplicits$$anon$7(LowPriorityOrderingImplicits $outer, Comparator cmp$2) {
/* 152 */       PartialOrdering$class.$init$(this);
/* 152 */       Ordering$class.$init$(this);
/*     */     }
/*     */     
/*     */     public int compare(Object x, Object y) {
/* 153 */       return this.cmp$2.compare(x, y);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\math\LowPriorityOrderingImplicits.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */