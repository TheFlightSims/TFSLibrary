/*    */ package scala.collection.parallel.mutable;
/*    */ 
/*    */ import scala.Serializable;
/*    */ import scala.collection.generic.Growable;
/*    */ import scala.collection.generic.Sizing;
/*    */ import scala.collection.mutable.ArrayBuffer;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction2;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001A4q!\001\002\021\002\007\0051B\001\007MCjL8i\\7cS:,'O\003\002\004\t\0059Q.\036;bE2,'BA\003\007\003!\001\030M]1mY\026d'BA\004\t\003)\031w\016\0347fGRLwN\034\006\002\023\005)1oY1mC\016\001Q\003\002\007\030CI\0322\001A\007\022!\tqq\"D\001\t\023\t\001\002B\001\004B]f\024VM\032\t\005%M)\002%D\001\005\023\t!BA\001\005D_6\024\027N\\3s!\t1r\003\004\001\005\013a\001!\031A\r\003\t\025cW-\\\t\0035u\001\"AD\016\n\005qA!a\002(pi\"Lgn\032\t\003\035yI!a\b\005\003\007\005s\027\020\005\002\027C\0211!\005\001CC\002e\021!\001V8\t\013\021\002A\021A\023\002\r\021Jg.\033;%)\0051\003C\001\b(\023\tA\003B\001\003V]&$\bb\002\026\001\005\0045\taK\001\006G\"\f\027N\\\013\002YA\031QfL\031\016\0039R!a\001\004\n\005Ar#aC!se\006L()\0364gKJ\004\"A\006\032\005\013M\002!\031\001\033\003\t\t+hMZ\t\0035U\0222A\016\035?\r\0219\004\001A\033\003\031q\022XMZ5oK6,g\016\036 \021\007ebT#D\001;\025\tYd!A\004hK:,'/[2\n\005uR$\001C$s_^\f'\r\\3\021\005ez\024B\001!;\005\031\031\026N_5oO\"9!\t\001b\001\n\003\031\025\001\0037bgR\024WO\0324\026\003EBa!\022\001!\002\023\t\024!\0037bgR\024WO\0324!\021\0259\005\001\"\001I\003!!\003\017\\;tI\025\fHCA%K\033\005\001\001\"B&G\001\004)\022\001B3mK6DQ!\024\001\005\0029\013aA]3tk2$H#\001\021\t\013A\003A\021A\023\002\013\rdW-\031:\t\013I\003A\021A*\002\017\r|WNY5oKV\031AkV.\025\005Us\006\003\002\n\024-j\003\"AF,\005\013a\013&\031A-\003\0039\013\"AG\013\021\005YYF!\002/R\005\004i&!\002(foR{\027C\001\021\036\021\025y\026\0131\001V\003\025yG\017[3s\021\025\t\007\001\"\001c\003\021\031\030N_3\026\003\r\004\"A\0043\n\005\025D!aA%oi\")q\r\001D\001Q\006y\021\r\0347pG\006$X-\0218e\007>\004\0300F\001!\021\025Q\007A\"\001l\003=qWm\036'buf\034u.\0342j]\026\024HC\0017o!\025i\007!\006\0212\033\005\021\001\"B8j\001\004a\023!\0032vM\032\034\007.Y5o\001")
/*    */ public interface LazyCombiner<Elem, To, Buff extends Growable<Elem> & Sizing> extends Combiner<Elem, To> {
/*    */   void scala$collection$parallel$mutable$LazyCombiner$_setter_$lastbuff_$eq(Growable paramGrowable);
/*    */   
/*    */   ArrayBuffer<Buff> chain();
/*    */   
/*    */   Buff lastbuff();
/*    */   
/*    */   LazyCombiner<Elem, To, Buff> $plus$eq(Elem paramElem);
/*    */   
/*    */   To result();
/*    */   
/*    */   void clear();
/*    */   
/*    */   <N extends Elem, NewTo> Combiner<N, NewTo> combine(Combiner<N, NewTo> paramCombiner);
/*    */   
/*    */   int size();
/*    */   
/*    */   To allocateAndCopy();
/*    */   
/*    */   LazyCombiner<Elem, To, Buff> newLazyCombiner(ArrayBuffer<Buff> paramArrayBuffer);
/*    */   
/*    */   public class LazyCombiner$$anonfun$size$1 extends AbstractFunction2<Object, Buff, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final int apply(int x$1, Growable x$2) {
/* 37 */       return x$1 + ((Sizing)x$2).size();
/*    */     }
/*    */     
/*    */     public LazyCombiner$$anonfun$size$1(LazyCombiner $outer) {}
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\mutable\LazyCombiner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */