/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.MatchError;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.Traversable;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.script.End$;
/*    */ import scala.collection.script.Include;
/*    */ import scala.collection.script.Index;
/*    */ import scala.collection.script.Location;
/*    */ import scala.collection.script.Message;
/*    */ import scala.collection.script.Remove;
/*    */ import scala.collection.script.Reset;
/*    */ import scala.collection.script.Script;
/*    */ import scala.collection.script.Start$;
/*    */ import scala.collection.script.Update;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.IntRef;
/*    */ import scala.runtime.Nothing$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005-aaB\001\003!\003\r\t!\003\002\021\037\n\034XM\035<bE2,')\0364gKJT!a\001\003\002\0175,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\004\001U\021!\"F\n\005\001-ya\004\005\002\r\0335\ta!\003\002\017\r\t1\021I\\=SK\032\0042\001E\t\024\033\005\021\021B\001\n\003\005\031\021UO\0324feB\021A#\006\007\001\t\0251\002A1\001\030\005\005\t\025C\001\r\034!\ta\021$\003\002\033\r\t9aj\034;iS:<\007C\001\007\035\023\tibAA\002B]f\0042\001E\020\"\023\t\001#AA\005Qk\nd\027n\0355feJ\031!\005\n\026\007\t\r\002\001!\t\002\ryI,g-\0338f[\026tGO\020\t\004K!\032R\"\001\024\013\005\035\"\021AB:de&\004H/\003\002*M\t9Q*Z:tC\036,\007C\001\t,\023\ta#A\001\005V]\022|\027M\0317f\021\025q\003\001\"\0010\003\031!\023N\\5uIQ\t\001\007\005\002\rc%\021!G\002\002\005+:LG\017B\0035\001\t\005QGA\002Qk\n\f\"\001\007\034\021\007A\0011\003\003\0049\001A%\t!O\001\tIAdWo\035\023fcR\021!hO\007\002\001!)Ah\016a\001'\0059Q\r\\3nK:$\bB\002 \001!\023\005q(A\007%a2,8\017\n9mkN$S-\035\013\003u\001CQ!Q\037A\002\t\013!\001_:\021\007\r#5#D\001\005\023\t)EAA\bUe\0064XM]:bE2,wJ\\2f\021\0319\005\001%C\001\021\006qA\005\0357vg\022*\027\017J2pY>tGC\001\036J\021\025ad\t1\001\024\021\031Y\005\001%C\001\031\0061Q\017\0353bi\026$2\001M'S\021\025q%\n1\001P\003\005q\007C\001\007Q\023\t\tfAA\002J]RDQa\025&A\002M\t!B\\3xK2,W.\0328u\021\031)\006\001%C\001-\0061!/Z7pm\026$\"aE,\t\0139#\006\031A(\t\re\003\001\023\"\0010\003\025\031G.Z1s\021\031Y\006\001%C\0019\006I\021N\\:feR\fE\016\034\013\004aus\006\"\002([\001\004y\005\"B0[\001\004\001\027!B3mK6\034\bcA\"b'%\021!\r\002\002\f)J\fg/\032:tC\ndW\rC\005e\001\005\005\t\021\"\003fQ\006q1/\0369fe\022\"\003\017\\;tI\025\fHC\001\036g\021\02597\r1\001\024\003\021)G.Z7\n\005aJ\027B\0016\003\005)\021UO\0324fe2K7.\032\005\nY\002\t\t\021!C\005[>\fAc];qKJ$C\005\0357vg\022*\027\017J2pY>tGC\001\036o\021\02597\0161\001\024\023\t9\025\016C\005r\001\005\005\t\021\"\003sm\006a1/\0369fe\022*\b\017Z1uKR\031\001g\035;\t\0139\003\b\031A(\t\013U\004\b\031A\n\002\0179,w/\0327f[&\0211*\033\005\nq\002\t\t\021!C\005sn\fAb];qKJ$#/Z7pm\026$\"a\005>\t\0139;\b\031A(\n\005UK\007\"C?\001\003\003\005I\021B\030\003-\031X\017]3sI\rdW-\031:\n\005eK\007\002DA\001\001\005\005\t\021\"\003\002\004\005%\021aD:va\026\024H%\0338tKJ$\030\t\0347\025\013A\n)!a\002\t\0139{\b\031A(\t\013}{\b\031\0011\n\005mK\007")
/*    */ public interface ObservableBuffer<A> extends Buffer<A>, Publisher<Message<A>> {
/*    */   ObservableBuffer<A> scala$collection$mutable$ObservableBuffer$$super$$plus$eq(A paramA);
/*    */   
/*    */   ObservableBuffer<A> scala$collection$mutable$ObservableBuffer$$super$$plus$eq$colon(A paramA);
/*    */   
/*    */   void scala$collection$mutable$ObservableBuffer$$super$update(int paramInt, A paramA);
/*    */   
/*    */   A scala$collection$mutable$ObservableBuffer$$super$remove(int paramInt);
/*    */   
/*    */   void scala$collection$mutable$ObservableBuffer$$super$clear();
/*    */   
/*    */   void scala$collection$mutable$ObservableBuffer$$super$insertAll(int paramInt, Traversable<A> paramTraversable);
/*    */   
/*    */   ObservableBuffer<A> $plus$eq(A paramA);
/*    */   
/*    */   ObservableBuffer<A> $plus$plus$eq(TraversableOnce<A> paramTraversableOnce);
/*    */   
/*    */   ObservableBuffer<A> $plus$eq$colon(A paramA);
/*    */   
/*    */   void update(int paramInt, A paramA);
/*    */   
/*    */   A remove(int paramInt);
/*    */   
/*    */   void clear();
/*    */   
/*    */   void insertAll(int paramInt, Traversable<A> paramTraversable);
/*    */   
/*    */   public class ObservableBuffer$$anon$2 extends Include<A> implements Undoable {
/*    */     public ObservableBuffer$$anon$2(ObservableBuffer $outer, Object element$1) {
/* 31 */       super((Location)End$.MODULE$, element$1);
/*    */     }
/*    */     
/*    */     public void undo() {
/* 32 */       this.$outer.trimEnd(1);
/*    */     }
/*    */   }
/*    */   
/*    */   public class ObservableBuffer$$anonfun$$plus$plus$eq$1 extends AbstractFunction1<A, ObservableBuffer<A>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final ObservableBuffer<A> apply(Object x) {
/* 38 */       return this.$outer.$plus$eq((A)x);
/*    */     }
/*    */     
/*    */     public ObservableBuffer$$anonfun$$plus$plus$eq$1(ObservableBuffer $outer) {}
/*    */   }
/*    */   
/*    */   public class ObservableBuffer$$anon$3 extends Include<A> implements Undoable {
/*    */     public ObservableBuffer$$anon$3(ObservableBuffer $outer, Object element$2) {
/* 44 */       super((Location)Start$.MODULE$, element$2);
/*    */     }
/*    */     
/*    */     public void undo() {
/* 45 */       this.$outer.trimStart(1);
/*    */     }
/*    */   }
/*    */   
/*    */   public class ObservableBuffer$$anon$4 extends Update<A> implements Undoable {
/*    */     private final Object oldelement$1;
/*    */     
/*    */     private final int n$1;
/*    */     
/*    */     public ObservableBuffer$$anon$4(ObservableBuffer $outer, Object oldelement$1, int n$1, Object newelement$1) {
/* 53 */       super((Location)new Index(n$1), newelement$1);
/*    */     }
/*    */     
/*    */     public void undo() {
/* 54 */       this.$outer.update(this.n$1, this.oldelement$1);
/*    */     }
/*    */   }
/*    */   
/*    */   public class ObservableBuffer$$anon$5 extends Remove<A> implements Undoable {
/*    */     private final Object oldelement$2;
/*    */     
/*    */     private final int n$2;
/*    */     
/*    */     public ObservableBuffer$$anon$5(ObservableBuffer $outer, Object oldelement$2, int n$2) {
/* 61 */       super((Location)new Index(n$2), oldelement$2);
/*    */     }
/*    */     
/*    */     public void undo() {
/* 62 */       this.$outer.insert(this.n$2, Predef$.MODULE$.genericWrapArray(new Object[] { this.oldelement$2 }));
/*    */     }
/*    */   }
/*    */   
/*    */   public class ObservableBuffer$$anon$6 extends Reset<Nothing$> implements Undoable {
/*    */     public ObservableBuffer$$anon$6(ObservableBuffer $outer) {}
/*    */     
/*    */     public void undo() {
/* 70 */       throw new UnsupportedOperationException("cannot undo");
/*    */     }
/*    */   }
/*    */   
/*    */   public class ObservableBuffer$$anon$1 extends Script<A> implements Undoable {
/*    */     public ObservableBuffer$$anon$1(ObservableBuffer $outer) {}
/*    */     
/*    */     public void undo() {
/* 78 */       throw new UnsupportedOperationException("cannot undo");
/*    */     }
/*    */   }
/*    */   
/*    */   public class ObservableBuffer$$anonfun$1 extends AbstractFunction2<Script<A>, A, Script<A>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final IntRef curr$1;
/*    */     
/*    */     public final Script<A> apply(Script x0$1, Object x1$1) {
/* 79 */       Tuple2 tuple2 = new Tuple2(x0$1, x1$1);
/* 79 */       if (tuple2 != null) {
/* 81 */         this.curr$1.elem++;
/* 82 */         return (Script<A>)((ArrayBuffer<Include>)tuple2._1()).$plus$eq(new Include((Location)new Index(this.curr$1.elem), tuple2._2()));
/*    */       } 
/*    */       throw new MatchError(tuple2);
/*    */     }
/*    */     
/*    */     public ObservableBuffer$$anonfun$1(ObservableBuffer $outer, IntRef curr$1) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ObservableBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */