/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.None$;
/*     */ import scala.Option;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.collection.IterableLike;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.SeqLike;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.ObjectRef;
/*     */ import scala.runtime.StringAdd$;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001A4q!\001\002\021\002\007\005\021BA\rBI\006\004H/\033<f/>\0248n\025;fC2Lgn\032+bg.\034(BA\002\005\003!\001\030M]1mY\026d'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\0011c\001\001\013\035A\0211\002D\007\002\r%\021QB\002\002\007\003:L(+\0324\021\005=\001R\"\001\002\n\005E\021!!\002+bg.\034\b\"B\n\001\t\003!\022A\002\023j]&$H\005F\001\026!\tYa#\003\002\030\r\t!QK\\5u\r\035I\002\001%A\002\002i\0211b\026:baB,G\rV1tWV\0311$I\026\024\007aQA\004\005\003\036=}QS\"\001\001\n\005e\001\002C\001\021\"\031\001!QA\t\rC\002\r\022\021AU\t\003I\035\002\"aC\023\n\005\0312!a\002(pi\"Lgn\032\t\003\027!J!!\013\004\003\007\005s\027\020\005\002!W\021)A\006\007b\001G\t\021A\013\035\005\006'a!\t\001\006\005\b_a\001\r\021\"\0011\003\021qW\r\037;\026\003E\002B!\b\r U!91\007\007a\001\n\003!\024\001\0038fqR|F%Z9\025\005U)\004b\002\0343\003\003\005\r!M\001\004q\022\n\004B\002\035\031A\003&\021'A\003oKb$\b\005\013\0028uA\0211bO\005\003y\031\021\001B^8mCRLG.\032\005\b}a\001\r\021\"\001@\0035\031\bn\\;mI^\013\027\016\036$peV\t\001\t\005\002\f\003&\021!I\002\002\b\005>|G.Z1o\021\035!\005\0041A\005\002\025\013\021c\0355pk2$w+Y5u\r>\024x\fJ3r)\t)b\tC\0047\007\006\005\t\031\001!\t\r!C\002\025)\003A\0039\031\bn\\;mI^\013\027\016\036$pe\002B#a\022\036\t\013-Cb\021\001'\002\013M\004H.\033;\026\0035\0032A\024,2\035\tyEK\004\002Q'6\t\021K\003\002S\021\0051AH]8pizJ\021aB\005\003+\032\tq\001]1dW\006<W-\003\002X1\n\0311+Z9\013\005U3\001\"\002.\031\t\003!\022aB2p[B,H/\032\005\0069b!\t\001F\001\tS:$XM\0358bY\")a\f\007C\001?\006i1\017]1x]N+(\r^1tWN$\022!\r\005\006Cb!\t\001F\001\013aJLg\016^\"iC&t\007\"B2\001\r#!\027A\0048fo^\023\030\r\0359fIR\0137o[\013\004K\"TGC\0014l!\021i\002dZ5\021\005\001BG!\002\022c\005\004\031\003C\001\021k\t\025a#M1\001$\021\025a'\r1\001n\003\005\021\007\003B\boO&L!a\034\002\003\tQ\0137o\033")
/*     */ public interface AdaptiveWorkStealingTasks extends Tasks {
/*     */   <R, Tp> WrappedTask<R, Tp> newWrappedTask(Task<R, Tp> paramTask);
/*     */   
/*     */   public abstract class WrappedTask$class {
/*     */     public static void $init$(AdaptiveWorkStealingTasks.WrappedTask $this) {
/* 156 */       $this.next_$eq((AdaptiveWorkStealingTasks.WrappedTask)null);
/* 157 */       $this.shouldWaitFor_$eq(true);
/*     */     }
/*     */     
/*     */     public static void compute(AdaptiveWorkStealingTasks.WrappedTask $this) {
/* 161 */       if ($this.body().shouldSplitFurther()) {
/* 162 */         $this.internal();
/* 163 */         $this.release();
/*     */       } else {
/* 165 */         $this.body().tryLeaf((Option)None$.MODULE$);
/* 166 */         $this.release();
/*     */       } 
/*     */     }
/*     */     
/*     */     public static void internal(AdaptiveWorkStealingTasks.WrappedTask $this) {
/* 170 */       AdaptiveWorkStealingTasks.WrappedTask<?, ?> last = $this.spawnSubtasks();
/* 172 */       last.body().tryLeaf((Option)None$.MODULE$);
/* 173 */       last.release();
/* 174 */       $this.body().result_$eq(last.body().result());
/* 175 */       $this.body().throwable_$eq(last.body().throwable());
/*     */       while (true) {
/* 177 */         if (last.next() == null)
/*     */           return; 
/* 179 */         if ((last = last.next())
/*     */           
/* 181 */           .tryCancel()) {
/* 183 */           last.body().tryLeaf((Option)new Some($this.body().result()));
/* 184 */           last.release();
/*     */         } else {
/* 187 */           last.sync();
/*     */         } 
/* 190 */         $this.body().tryMerge(last.body().repr());
/*     */       } 
/*     */     }
/*     */     
/*     */     public static AdaptiveWorkStealingTasks.WrappedTask spawnSubtasks(AdaptiveWorkStealingTasks.WrappedTask $this) {
/* 195 */       ObjectRef last = new ObjectRef(null);
/* 196 */       AdaptiveWorkStealingTasks.WrappedTask head = $this;
/*     */       while (true) {
/* 197 */         Seq subtasks = head.split();
/* 199 */         head = (AdaptiveWorkStealingTasks.WrappedTask)subtasks.head();
/* 200 */         ((IterableLike)((SeqLike)subtasks.tail()).reverse()).foreach((Function1)new AdaptiveWorkStealingTasks$WrappedTask$$anonfun$spawnSubtasks$1($this, (AdaptiveWorkStealingTasks.WrappedTask<R, Tp>)last));
/* 205 */         if (!head.body().shouldSplitFurther()) {
/* 206 */           head.next_$eq((AdaptiveWorkStealingTasks.WrappedTask)last.elem);
/* 207 */           return head;
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public static void printChain(AdaptiveWorkStealingTasks.WrappedTask $this) {
/* 211 */       AdaptiveWorkStealingTasks.WrappedTask curr = $this;
/* 212 */       String chain = "chain: ";
/*     */       while (true) {
/* 213 */         if (curr == null) {
/* 217 */           Predef$.MODULE$.println(chain);
/*     */           return;
/*     */         } 
/*     */         chain = (new StringBuilder()).append(chain).append(StringAdd$.MODULE$.$plus$extension(Predef$.MODULE$.any2stringadd(curr), " ---> ")).toString();
/*     */         curr = curr.next();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public interface WrappedTask<R, Tp> extends Tasks.WrappedTask<R, Tp> {
/*     */     WrappedTask<R, Tp> next();
/*     */     
/*     */     @TraitSetter
/*     */     void next_$eq(WrappedTask<R, Tp> param1WrappedTask);
/*     */     
/*     */     boolean shouldWaitFor();
/*     */     
/*     */     @TraitSetter
/*     */     void shouldWaitFor_$eq(boolean param1Boolean);
/*     */     
/*     */     Seq<WrappedTask<R, Tp>> split();
/*     */     
/*     */     void compute();
/*     */     
/*     */     void internal();
/*     */     
/*     */     WrappedTask<R, Tp> spawnSubtasks();
/*     */     
/*     */     void printChain();
/*     */     
/*     */     public class AdaptiveWorkStealingTasks$WrappedTask$$anonfun$spawnSubtasks$1 extends AbstractFunction1<WrappedTask<R, Tp>, BoxedUnit> implements Serializable {
/*     */       public static final long serialVersionUID = 0L;
/*     */       
/*     */       private final ObjectRef last$1;
/*     */       
/*     */       public AdaptiveWorkStealingTasks$WrappedTask$$anonfun$spawnSubtasks$1(AdaptiveWorkStealingTasks.WrappedTask $outer, ObjectRef last$1) {}
/*     */       
/*     */       public final void apply(AdaptiveWorkStealingTasks.WrappedTask t) {
/*     */         t.next_$eq((AdaptiveWorkStealingTasks.WrappedTask)this.last$1.elem);
/*     */         this.last$1.elem = t;
/*     */         t.start();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\AdaptiveWorkStealingTasks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */