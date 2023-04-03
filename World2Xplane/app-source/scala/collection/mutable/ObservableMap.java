/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.Some;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.script.Include;
/*    */ import scala.collection.script.Message;
/*    */ import scala.collection.script.Remove;
/*    */ import scala.collection.script.Reset;
/*    */ import scala.collection.script.Update;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.Nothing$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001e3q!\001\002\021\002\007\005\021BA\007PEN,'O^1cY\026l\025\r\035\006\003\007\021\tq!\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!F\002\013+}\031B\001A\006\020CA\021A\"D\007\002\r%\021aB\002\002\007\003:L(+\0324\021\tA\t2CH\007\002\005%\021!C\001\002\004\033\006\004\bC\001\013\026\031\001!QA\006\001C\002]\021\021!Q\t\0031m\001\"\001D\r\n\005i1!a\002(pi\"Lgn\032\t\003\031qI!!\b\004\003\007\005s\027\020\005\002\025?\021)\001\005\001b\001/\t\t!\tE\002\021E\021J!a\t\002\003\023A+(\r\\5tQ\026\024(cA\023(a\031!a\005\001\001%\0051a$/\0324j]\026lWM\034;?!\rA3&L\007\002S)\021!\006B\001\007g\016\024\030\016\035;\n\0051J#aB'fgN\fw-\032\t\005\0319\032b$\003\0020\r\t1A+\0369mKJ\002\"\001E\031\n\005I\022!\001C+oI>\f'\r\\3\t\013Q\002A\021A\033\002\r\021Jg.\033;%)\0051\004C\001\0078\023\tAdA\001\003V]&$H!\002\036\001\005\003Y$a\001)vEF\021\001\004\020\t\005!\001\031b\004\003\004?\001A%\taP\001\tIAdWo\035\023fcR\021\001)Q\007\002\001!)!)\020a\001[\005\0211N\036\005\007\t\002\001J\021A#\002\023\021j\027N\\;tI\025\fHC\001!G\021\02595\t1\001\024\003\rYW-\037\005\007\023\002\001J\021A\033\002\013\rdW-\031:\t\023-\003\021\021!A\005\n1s\025AD:va\026\024H\005\n9mkN$S-\035\013\003\0016CQA\021&A\0025J!AP(\n\005A\023!aB'ba2K7.\032\005\n%\002\t\t\021!C\005'V\013qb];qKJ$C%\\5okN$S-\035\013\003\001RCQaR)A\002MI!\001R(\t\023]\003\021\021!A\005\nUB\026aC:va\026\024He\0317fCJL!!S(")
/*    */ public interface ObservableMap<A, B> extends Map<A, B>, Publisher<Message<Tuple2<A, B>>> {
/*    */   ObservableMap<A, B> scala$collection$mutable$ObservableMap$$super$$plus$eq(Tuple2<A, B> paramTuple2);
/*    */   
/*    */   ObservableMap<A, B> scala$collection$mutable$ObservableMap$$super$$minus$eq(A paramA);
/*    */   
/*    */   void scala$collection$mutable$ObservableMap$$super$clear();
/*    */   
/*    */   ObservableMap<A, B> $plus$eq(Tuple2<A, B> paramTuple2);
/*    */   
/*    */   ObservableMap<A, B> $minus$eq(A paramA);
/*    */   
/*    */   void clear();
/*    */   
/*    */   public class ObservableMap$$anon$1 extends Include<Tuple2<A, B>> implements Undoable {
/*    */     private final Object key$1;
/*    */     
/*    */     public ObservableMap$$anon$1(ObservableMap $outer, Object key$1, Object value$1) {
/* 38 */       super(new Tuple2(key$1, value$1));
/*    */     }
/*    */     
/*    */     public void undo() {
/* 39 */       this.$outer.$minus$eq(this.key$1);
/*    */     }
/*    */   }
/*    */   
/*    */   public class ObservableMap$$anon$2 extends Update<Tuple2<A, B>> implements Undoable {
/*    */     private final Object key$1;
/*    */     
/*    */     private final Some x2$1;
/*    */     
/*    */     public ObservableMap$$anon$2(ObservableMap $outer, Object key$1, Object value$1, Some x2$1) {
/* 43 */       super(new Tuple2(key$1, value$1));
/*    */     }
/*    */     
/*    */     public void undo() {
/* 44 */       this.$outer.$plus$eq(new Tuple2(this.key$1, this.x2$1.x()));
/*    */     }
/*    */   }
/*    */   
/*    */   public class ObservableMap$$anon$3 extends Remove<Tuple2<A, B>> implements Undoable {
/*    */     private final Some x2$2;
/*    */     
/*    */     private final Object key$2;
/*    */     
/*    */     public ObservableMap$$anon$3(ObservableMap $outer, Some x2$2, Object key$2) {
/* 55 */       super(new Tuple2(key$2, x2$2.x()));
/*    */     }
/*    */     
/*    */     public void undo() {
/* 56 */       this.$outer.update(this.key$2, this.x2$2.x());
/*    */     }
/*    */   }
/*    */   
/*    */   public class ObservableMap$$anon$4 extends Reset<Nothing$> implements Undoable {
/*    */     public ObservableMap$$anon$4(ObservableMap $outer) {}
/*    */     
/*    */     public void undo() {
/* 65 */       throw new UnsupportedOperationException("cannot undo");
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ObservableMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */