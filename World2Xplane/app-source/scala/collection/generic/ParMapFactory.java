/*    */ package scala.collection.generic;
/*    */ 
/*    */ import scala.Tuple2;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.parallel.Combiner;
/*    */ import scala.collection.parallel.ParMap;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005]a!B\001\003\003\003I!!\004)be6\013\007OR1di>\024\030P\003\002\004\t\0059q-\0328fe&\034'BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\001QC\001\006\022'\r\0011\002\016\t\004\0315yQ\"\001\002\n\0059\021!!D$f]6\013\007OR1di>\024\030\020\005\002\021#1\001A!\002\n\001\005\004\031\"AA\"D+\r!2EK\t\003+e\001\"AF\f\016\003\031I!\001\007\004\003\0179{G\017[5oOJ\031!\004\b\027\007\tm\001\001!\007\002\ryI,g-\0338f[\026tGO\020\t\005;\001\022\023&D\001\037\025\tyB!\001\005qCJ\fG\016\\3m\023\t\tcD\001\004QCJl\025\r\035\t\003!\r\"Q\001J\tC\002\025\022\021\001W\t\003+\031\002\"AF\024\n\005!2!aA!osB\021\001C\013\003\006WE\021\r!\n\002\0023B\022QF\r\t\007;9\022\023\006M\031\n\005=r\"A\003)be6\013\007\017T5lKB!\001#\005\022*!\t\001\"\007B\0054#\005\005\t\021!B\001K\t\031q\fJ\031\021\0071)t\"\003\0027\005\t1r)\0328fe&\034\007+\031:NCB\034u.\0349b]&|g\016C\0039\001\021\005\021(\001\004=S:LGO\020\013\002uA\031A\002A\b\006\tq\002\001!\020\002\b\033\006\0048i\0347ma\rq\004i\021\t\005!Ey$\t\005\002\021\001\022I\021iOA\001\002\003\025\t!\n\002\004?\022\022\004C\001\tD\t%!5(!A\001\002\013\005QEA\002`IMBQA\022\001\005B\035\013!B\\3x\005VLG\016Z3s+\rA5KV\013\002\023B!!*T(Y\033\005Y%B\001'\005\003\035iW\017^1cY\026L!AT&\003\017\t+\030\016\0343feB!a\003\025*V\023\t\tfA\001\004UkBdWM\r\t\003!M#Q\001V#C\002\025\022\021a\023\t\003!Y#QaV#C\002\025\022\021A\026\t\005!E\021V\013C\003[\001\031\0051,A\006oK^\034u.\0342j]\026\024Xc\001/cIV\tQ\f\005\003\036=\002,\027BA0\037\005!\031u.\0342j]\026\024\b\003\002\fQC\016\004\"\001\0052\005\013QK&\031A\023\021\005A!G!B,Z\005\004)\003\003\002\t\022C\0164Aa\032\001\001Q\n\t2)\0318D_6\024\027N\\3Ge>lW*\0319\026\007%THpE\002gU6\004\"AF6\n\00514!AB!osJ+g\rE\003\r]BDX0\003\002p\005\tq1)\0318D_6\024\027N\\3Ge>l\007gA9tmB!\001#\005:v!\t\0012\017B\005uM\006\005\t\021!B\001K\t\031q\f\n\033\021\005A1H!C<g\003\003\005\tQ!\001&\005\ryF%\016\t\005-AK8\020\005\002\021u\022)AK\032b\001KA\021\001\003 \003\006/\032\024\r!\n\t\005!EI8\020C\0039M\022\005q\020\006\002\002\002A)\0211\0014zw6\t\001\001C\004\002\b\031$\t!!\003\002\013\005\004\b\017\\=\025\t\005-\021Q\002\t\005;yCX\020\003\005\002\020\005\025\001\031AA\t\003\0211'o\\7\021\007\005\r1\bC\004\002\b\031$\t!!\006\025\005\005-\001")
/*    */ public abstract class ParMapFactory<CC extends ParMap<Object, Object>> extends GenMapFactory<CC> implements GenericParMapCompanion<CC> {
/*    */   public <K, V> Builder<Tuple2<K, V>, CC> newBuilder() {
/* 36 */     return (Builder)newCombiner();
/*    */   }
/*    */   
/*    */   public abstract <K, V> Combiner<Tuple2<K, V>, CC> newCombiner();
/*    */   
/*    */   public class CanCombineFromMap<K, V> implements CanCombineFrom<CC, Tuple2<K, V>, CC> {
/*    */     public CanCombineFromMap(ParMapFactory $outer) {}
/*    */     
/*    */     public Combiner<Tuple2<K, V>, CC> apply(ParMap from) {
/* 45 */       return from.genericMapCombiner();
/*    */     }
/*    */     
/*    */     public Combiner<Tuple2<K, V>, CC> apply() {
/* 46 */       return scala$collection$generic$ParMapFactory$CanCombineFromMap$$$outer().newCombiner();
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\generic\ParMapFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */