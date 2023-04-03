/*    */ package scala.collection.mutable;
/*    */ 
/*    */ import scala.collection.script.Include;
/*    */ import scala.collection.script.Message;
/*    */ import scala.collection.script.Remove;
/*    */ import scala.collection.script.Reset;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.Nothing$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001I3q!\001\002\021\002\007\005\021BA\007PEN,'O^1cY\026\034V\r\036\006\003\007\021\tq!\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!\006\002\013+M!\001aC\b\037!\taQ\"D\001\007\023\tqaA\001\004B]f\024VM\032\t\004!E\031R\"\001\002\n\005I\021!aA*fiB\021A#\006\007\001\t\0251\002A1\001\030\005\005\t\025C\001\r\034!\ta\021$\003\002\033\r\t9aj\034;iS:<\007C\001\007\035\023\tibAA\002B]f\0042\001E\020\"\023\t\001#AA\005Qk\nd\027n\0355feJ\031!\005\n\026\007\t\r\002\001!\t\002\ryI,g-\0338f[\026tGO\020\t\004K!\032R\"\001\024\013\005\035\"\021AB:de&\004H/\003\002*M\t9Q*Z:tC\036,\007C\001\t,\023\ta#A\001\005V]\022|\027M\0317f\021\025q\003\001\"\0010\003\031!\023N\\5uIQ\t\001\007\005\002\rc%\021!G\002\002\005+:LG\017B\0035\001\t\005QGA\002Qk\n\f\"\001\007\034\021\007A\0011\003\003\0049\001A%\t!O\001\tIAdWo\035\023fcR\021!hO\007\002\001!)Ah\016a\001'\005!Q\r\\3n\021\031q\004\001%C\001\005IA%\\5okN$S-\035\013\003u\001CQ\001P\037A\002MAaA\021\001\021\n\003y\023!B2mK\006\024\b\"\003#\001\003\003\005I\021B#H\0039\031X\017]3sI\021\002H.^:%KF$\"A\017$\t\013q\032\005\031A\n\n\005aB\025BA%\003\005\035\031V\r\036'jW\026D\021b\023\001\002\002\003%I\001\024(\002\037M,\b/\032:%I5Lg.^:%KF$\"AO'\t\013qR\005\031A\n\n\005yB\005\"\003)\001\003\003\005I\021B\030R\003-\031X\017]3sI\rdW-\031:\n\005\tC\005")
/*    */ public interface ObservableSet<A> extends Set<A>, Publisher<Message<A>> {
/*    */   ObservableSet<A> scala$collection$mutable$ObservableSet$$super$$plus$eq(A paramA);
/*    */   
/*    */   ObservableSet<A> scala$collection$mutable$ObservableSet$$super$$minus$eq(A paramA);
/*    */   
/*    */   void scala$collection$mutable$ObservableSet$$super$clear();
/*    */   
/*    */   ObservableSet<A> $plus$eq(A paramA);
/*    */   
/*    */   ObservableSet<A> $minus$eq(A paramA);
/*    */   
/*    */   void clear();
/*    */   
/*    */   public class ObservableSet$$anon$1 extends Include<A> implements Undoable {
/*    */     public void undo() {
/* 33 */       this.$outer.$minus$eq(elem());
/*    */     }
/*    */     
/*    */     public ObservableSet$$anon$1(ObservableSet $outer, Object elem$1) {
/* 33 */       super(elem$1);
/*    */     }
/*    */   }
/*    */   
/*    */   public class ObservableSet$$anon$2 extends Remove<A> implements Undoable {
/*    */     public void undo() {
/* 41 */       this.$outer.$plus$eq(elem());
/*    */     }
/*    */     
/*    */     public ObservableSet$$anon$2(ObservableSet $outer, Object elem$2) {
/* 41 */       super(elem$2);
/*    */     }
/*    */   }
/*    */   
/*    */   public class ObservableSet$$anon$3 extends Reset<Nothing$> implements Undoable {
/*    */     public ObservableSet$$anon$3(ObservableSet $outer) {}
/*    */     
/*    */     public void undo() {
/* 49 */       throw new UnsupportedOperationException("cannot undo");
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\mutable\ObservableSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */