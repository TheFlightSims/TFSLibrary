/*    */ package scala.util.control;
/*    */ 
/*    */ import scala.Function0;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.Nothing$;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001]3A!\001\002\001\023\t1!I]3bWNT!a\001\003\002\017\r|g\016\036:pY*\021QAB\001\005kRLGNC\001\b\003\025\0318-\0317b\007\001\031\"\001\001\006\021\005-aQ\"\001\004\n\00551!AB!osJ+g\rC\003\020\001\021\005\001#\001\004=S:LGO\020\013\002#A\021!\003A\007\002\005!9A\003\001b\001\n\023)\022A\0042sK\006\\W\t_2faRLwN\\\013\002-A\021!cF\005\0031\t\021AB\021:fC.\034uN\034;s_2DaA\007\001!\002\0231\022a\0042sK\006\\W\t_2faRLwN\034\021\t\013q\001A\021A\017\002\023\t\024X-Y6bE2,GC\001\020\"!\tYq$\003\002!\r\t!QK\\5u\021\031\0213\004\"a\001G\005\021q\016\035\t\004\027\021r\022BA\023\007\005!a$-\0378b[\026tdaB\024\001!\003\r\n\003\013\002\t)JL(\t\\8dWV\021\021fL\n\003M)AQa\013\024\007\0021\n!bY1uG\"\024%/Z1l)\ti\003\b\005\002/_1\001A!\002\031'\005\004\t$!\001+\022\005I*\004CA\0064\023\t!dAA\004O_RD\027N\\4\021\005-1\024BA\034\007\005\r\te.\037\005\007s)\"\t\031\001\036\002\017=t'I]3bWB\0311\002J\027*\005\031bd\001B\037'\001y\022Q\002\0207pG\006d\007e\0315jY\022t4C\001\037@!\r\001e%L\007\002\001!)!\t\001C\001\007\006aAO]=Ce\026\f7.\0312mKV\021AI\023\013\003\013.\0232A\022\006I\r\0219\025\tA#\003\031q\022XMZ5oK6,g\016\036 \021\007\0013\023\n\005\002/\025\022)\001'\021b\001c!1!%\021CA\0021\0032a\003\023J\021\025q\005\001\"\001P\003\025\021'/Z1l)\005\021t!B)\003\021\003\021\026A\002\"sK\006\\7\017\005\002\023'\032)\021A\001E\001)N\0211+\005\005\006\037M#\tA\026\013\002%\002")
/*    */ public class Breaks {
/* 28 */   private final BreakControl scala$util$control$Breaks$$breakException = new BreakControl();
/*    */   
/*    */   public BreakControl scala$util$control$Breaks$$breakException() {
/* 28 */     return this.scala$util$control$Breaks$$breakException;
/*    */   }
/*    */   
/*    */   public void breakable(Function0 op) {
/*    */     try {
/* 37 */       op.apply$mcV$sp();
/*    */     } catch (BreakControl breakControl2) {
/*    */       BreakControl breakControl1;
/* 40 */       if ((breakControl1 = null) != scala$util$control$Breaks$$breakException())
/* 40 */         throw breakControl1; 
/*    */     } 
/*    */   }
/*    */   
/*    */   public <T> Object tryBreakable(Function0 op) {
/* 60 */     return new Breaks$$anon$1(this, op);
/*    */   }
/*    */   
/*    */   public class Breaks$$anon$1 implements TryBlock<T> {
/*    */     public final Function0 op$1;
/*    */     
/*    */     public Breaks$$anon$1(Breaks $outer, Function0 op$1) {}
/*    */     
/*    */     public T catchBreak(Function0 onBreak) {
/*    */       try {
/*    */       
/* 61 */       } catch (BreakControl breakControl2) {
/*    */         BreakControl breakControl1;
/* 61 */         if ((breakControl1 = null) != 
/*    */           
/* 65 */           this.$outer.scala$util$control$Breaks$$breakException())
/* 65 */           throw breakControl1; 
/*    */       } 
/* 66 */       return (T)onBreak.apply();
/*    */     }
/*    */   }
/*    */   
/*    */   public Nothing$ break() {
/* 76 */     throw scala$util$control$Breaks$$breakException();
/*    */   }
/*    */   
/*    */   public interface TryBlock<T> {
/*    */     T catchBreak(Function0<T> param1Function0);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\control\Breaks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */