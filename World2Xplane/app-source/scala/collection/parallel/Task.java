/*    */ package scala.collection.parallel;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.TraitSetter;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005eaaB\001\003!\003\r\t!\003\002\005)\006\0348N\003\002\004\t\005A\001/\031:bY2,GN\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006\034\001!F\002\0133\031\032\"\001A\006\021\0051iQ\"\001\004\n\00591!AB!osJ+g\rC\003\021\001\021\005\021#\001\004%S:LG\017\n\013\002%A\021AbE\005\003)\031\021A!\0268ji\026!a\003\001\001\030\005\031\021Vm];miB\021\001$\007\007\001\t\025Q\002A1\001\034\005\005\021\026C\001\017 !\taQ$\003\002\037\r\t9aj\034;iS:<\007C\001\007!\023\t\tcAA\002B]fDQa\t\001\005\002\021\nAA]3qeV\tQ\005\005\002\031M\0211q\005\001CC\002m\021!\001\0269\t\013%\002a\021\001\026\002\t1,\027M\032\013\003%-BQ\001\f\025A\0025\naA]3tk2$\bc\001\007//%\021qF\002\002\007\037B$\030n\0348\t\0171\002\001\031!D\001cU\tq\003C\0044\001\001\007i\021\001\033\002\025I,7/\0367u?\022*\027\017\006\002\023k!9aGMA\001\002\0049\022a\001=%c!)\001\b\001D\001s\005\0212\017[8vY\022\034\006\017\\5u\rV\024H\017[3s+\005Q\004C\001\007<\023\tadAA\004C_>dW-\0318\t\ry\002a\021\001\002@\003\025\031\b\017\\5u+\005\001\005cA!J\031:\021!i\022\b\003\007\032k\021\001\022\006\003\013\"\ta\001\020:p_Rt\024\"A\004\n\005!3\021a\0029bG.\fw-Z\005\003\025.\0231aU3r\025\tAe\001\005\003N\001])S\"\001\002\t\r=\003A\021\001\002Q\003\025iWM]4f)\t\021\022\013C\003S\035\002\0071+\001\003uQ\006$(FA\023UW\005)\006C\001,\\\033\0059&B\001-Z\003%)hn\0315fG.,GM\003\002[\r\005Q\021M\0348pi\006$\030n\0348\n\005q;&!E;oG\",7m[3e-\006\024\030.\0318dK\"9a\f\001a\001\n\003y\026!\003;ie><\030M\0317f+\005\001\007CA!b\023\t\0217JA\005UQJ|w/\0312mK\"9A\r\001a\001\n\003)\027!\004;ie><\030M\0317f?\022*\027\017\006\002\023M\"9agYA\001\002\004\001\007B\0025\001A\003&\001-\001\006uQJ|w/\0312mK\002B#a\0326\021\0051Y\027B\0017\007\005!1x\016\\1uS2,\007\"\0028\001\t\003\t\022\001\0054pe^\f'\017\032+ie><\030M\0317f\021\031\001\b\001\"\001\003c\0069AO]=MK\0064GC\001\ns\021\025\031x\0161\001.\003\035a\027m\035;sKNDa!\036\001\005\002\t1\030\001\003;ss6+'oZ3\025\005I9\b\"\002=u\001\004\031\026!\001;\t\013i\004A\021B>\002\025\rDWmY6NKJ<W\r\006\002\023y\")!+\037a\001{*\022A\n\026\005\b\002!\tAAA\001\003=iWM]4f)\"\024xn^1cY\026\034Hc\001\n\002\004!1!K a\001\003\013\001d!a\002\002\f\005E\001CB'\001\003\023\ty\001E\002\031\003\027!1\"!\004\002\004\005\005\t\021!B\0017\t\031q\fJ\031\021\007a\t\t\002B\006\002\024\005\r\021\021!A\001\006\003Y\"aA0%e!9\021q\003\001\005\002\t\t\022aC:jO:\fG.\0212peR\004")
/*    */ public interface Task<R, Tp> {
/*    */   Tp repr();
/*    */   
/*    */   void leaf(Option<R> paramOption);
/*    */   
/*    */   R result();
/*    */   
/*    */   void result_$eq(R paramR);
/*    */   
/*    */   boolean shouldSplitFurther();
/*    */   
/*    */   Seq<Task<R, Tp>> split();
/*    */   
/*    */   void merge(Tp paramTp);
/*    */   
/*    */   Throwable throwable();
/*    */   
/*    */   @TraitSetter
/*    */   void throwable_$eq(Throwable paramThrowable);
/*    */   
/*    */   void forwardThrowable();
/*    */   
/*    */   void tryLeaf(Option<R> paramOption);
/*    */   
/*    */   void tryMerge(Tp paramTp);
/*    */   
/*    */   void mergeThrowables(Task<?, ?> paramTask);
/*    */   
/*    */   void signalAbort();
/*    */   
/*    */   public class Task$$anonfun$tryLeaf$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     private final Option lastres$1;
/*    */     
/*    */     public final void apply() {
/* 53 */       apply$mcV$sp();
/*    */     }
/*    */     
/*    */     public Task$$anonfun$tryLeaf$1(Task $outer, Option lastres$1) {}
/*    */     
/*    */     public void apply$mcV$sp() {
/* 54 */       this.$outer.leaf(this.lastres$1);
/* 55 */       this.$outer.result_$eq(this.$outer.result());
/*    */     }
/*    */   }
/*    */   
/*    */   public class Task$$anonfun$tryLeaf$2 extends AbstractFunction0.mcV.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply() {
/* 57 */       this.$outer.signalAbort();
/*    */     }
/*    */     
/*    */     public void apply$mcV$sp() {
/* 57 */       this.$outer.signalAbort();
/*    */     }
/*    */     
/*    */     public Task$$anonfun$tryLeaf$2(Task $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\Task.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */