/*    */ package scala.util.automata;
/*    */ 
/*    */ import scala.Array$;
/*    */ import scala.Function0;
/*    */ import scala.Function1;
/*    */ import scala.Predef$;
/*    */ import scala.Serializable;
/*    */ import scala.Tuple2;
/*    */ import scala.collection.GenMap;
/*    */ import scala.collection.IndexedSeqOptimized;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.TraversableLike;
/*    */ import scala.collection.TraversableOnce;
/*    */ import scala.collection.generic.CanBuildFrom;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.collection.immutable.Map$EmptyMap$;
/*    */ import scala.collection.immutable.Range;
/*    */ import scala.collection.immutable.Range$;
/*    */ import scala.collection.immutable.StringLike;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.ArrayOps;
/*    */ import scala.collection.mutable.Builder;
/*    */ import scala.collection.mutable.Map;
/*    */ import scala.collection.mutable.MapBuilder;
/*    */ import scala.collection.mutable.StringBuilder;
/*    */ import scala.collection.mutable.WrappedArray;
/*    */ import scala.reflect.ClassTag;
/*    */ import scala.reflect.ClassTag$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction0;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.runtime.BoxesRunTime;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001m3Q!\001\002\002\002%\021A\002R3u/>\024H-Q;u_6T!a\001\003\002\021\005,Ho\\7bi\006T!!\002\004\002\tU$\030\016\034\006\002\017\005)1oY1mC\016\001QC\001\006\027'\t\0011\002\005\002\r\0335\ta!\003\002\017\r\t1\021I\\=SK\032DQ\001\005\001\005\002E\ta\001P5oSRtD#\001\n\021\007M\001A#D\001\003!\t)b\003\004\001\005\013]\001!\031\001\r\003\003Q\013\"!G\006\021\0051Q\022BA\016\007\005\035qu\016\0365j]\036Dq!\b\001C\002\033\005a$A\004ogR\fG/Z:\026\003}\001\"\001\004\021\n\005\0052!aA%oi\"91\005\001b\001\016\003!\023A\0024j]\006d7/F\001&!\raaeH\005\003O\031\021Q!\021:sCfDq!\013\001C\002\033\005!&A\003eK2$\030-F\001,!\raa\005\f\t\005[I\"r$D\001/\025\ty\003'A\004nkR\f'\r\\3\013\005E2\021AC2pY2,7\r^5p]&\0211G\f\002\004\033\006\004\bbB\033\001\005\0045\t\001J\001\bI\0264\027-\0367u\021\0259\004\001\"\0019\003\035I7OR5oC2$\"!\017\037\021\0051Q\024BA\036\007\005\035\021un\0347fC:DQ!\020\034A\002}\t\021!\035\005\006\001!\t\001Q\001\007SN\034\026N\\6\025\005e\n\005\"B\037?\001\004y\002\"B\"\001\t\003!\025\001\0028fqR$2aH#G\021\025i$\t1\001 \021\0259%\t1\001\025\003\025a\027MY3m\021\025I\005\001\"\021K\003!!xn\025;sS:<G#A&\021\0051\013V\"A'\013\0059{\025\001\0027b]\036T\021\001U\001\005U\0064\030-\003\002S\033\n11\013\036:j]\036DC\001\001+X3B\021A\"V\005\003-\032\021!\002Z3qe\026\034\027\r^3eC\005A\026A\007+iSN\0043\r\\1tg\002:\030\016\0347!E\026\004#/Z7pm\026$\027%\001.\002\rIr\023\007\r\0301\001")
/*    */ public abstract class DetWordAutom<T> {
/*    */   public abstract int nstates();
/*    */   
/*    */   public abstract int[] finals();
/*    */   
/*    */   public abstract Map<T, Object>[] delta();
/*    */   
/*    */   public abstract int[] default();
/*    */   
/*    */   public boolean isFinal(int q) {
/* 30 */     return (finals()[q] != 0);
/*    */   }
/*    */   
/*    */   public boolean isSink(int q) {
/* 31 */     return (delta()[q].isEmpty() && default()[q] == q);
/*    */   }
/*    */   
/*    */   public int next(int q, Object label) {
/* 32 */     return BoxesRunTime.unboxToInt(delta()[q].getOrElse(label, (Function0)new DetWordAutom$$anonfun$next$1(this, q)));
/*    */   }
/*    */   
/*    */   public class DetWordAutom$$anonfun$next$1 extends AbstractFunction0.mcI.sp implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final int q$1;
/*    */     
/*    */     public final int apply() {
/* 32 */       return this.$outer.default()[this.q$1];
/*    */     }
/*    */     
/*    */     public int apply$mcI$sp() {
/* 32 */       return this.$outer.default()[this.q$1];
/*    */     }
/*    */     
/*    */     public DetWordAutom$$anonfun$next$1(DetWordAutom $outer, int q$1) {}
/*    */   }
/*    */   
/*    */   public String toString() {
/* 35 */     StringBuilder sb = new StringBuilder("[DetWordAutom  nstates=");
/* 36 */     sb.append(nstates());
/* 37 */     sb.append(" finals=");
/* 38 */     int[] arrayOfInt = finals();
/* 38 */     Predef$ predef$1 = Predef$.MODULE$;
/* 38 */     ClassTag classTag1 = ClassTag$.MODULE$.apply(Tuple2.class);
/* 38 */     Array$ array$1 = Array$.MODULE$;
/* 38 */     Object[] arrayOfObject = (Object[])IndexedSeqOptimized.class.zipWithIndex((IndexedSeqOptimized)new ArrayOps.ofInt(arrayOfInt), (CanBuildFrom)new Object(classTag1));
/* 38 */     Predef$ predef$2 = Predef$.MODULE$;
/* 38 */     ClassTag classTag2 = ClassTag$.MODULE$.apply(Tuple2.class);
/* 38 */     Array$ array$2 = Array$.MODULE$;
/* 38 */     WrappedArray wrappedArray = Predef$.MODULE$.wrapRefArray((Object[])TraversableLike.class.map((TraversableLike)new ArrayOps.ofRef(arrayOfObject), (Function1)new DetWordAutom$$anonfun$1(this), (CanBuildFrom)new Object(classTag2)));
/* 38 */     Map map = (Map)((Builder)(new MapBuilder((GenMap)Map$EmptyMap$.MODULE$)).$plus$plus$eq((TraversableOnce)wrappedArray)).result();
/* 39 */     sb.append(map.toString());
/* 40 */     sb.append(" delta=\n");
/* 42 */     Predef$ predef$3 = Predef$.MODULE$;
/* 42 */     int i = nstates();
/* 42 */     Range$ range$ = Range$.MODULE$;
/* 42 */     DetWordAutom$$anonfun$toString$1 detWordAutom$$anonfun$toString$1 = new DetWordAutom$$anonfun$toString$1(this, (DetWordAutom<T>)sb);
/*    */     Range range;
/* 42 */     if ((range = new Range(0, i, 1)).validateRangeBoundaries((Function1)detWordAutom$$anonfun$toString$1)) {
/*    */       int terminal1;
/*    */       int step1;
/*    */       int i1;
/* 42 */       for (i1 = range.start(), terminal1 = range.terminalElement(), step1 = range.step(); i1 != terminal1; ) {
/* 42 */         int j = i1;
/* 42 */         Predef$ predef$4 = Predef$.MODULE$;
/* 42 */         sb.append(StringLike.class.format((StringLike)new StringOps("%d->%s\n"), (Seq)Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(j), delta()[j] })));
/* 42 */         Predef$ predef$5 = Predef$.MODULE$;
/* 42 */         (j < (default()).length) ? sb.append(StringLike.class.format((StringLike)new StringOps("_>%s\n"), (Seq)Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(default()[j]) }))) : BoxedUnit.UNIT;
/* 42 */         i1 += step1;
/*    */       } 
/*    */     } 
/* 47 */     return sb.toString();
/*    */   }
/*    */   
/*    */   public class DetWordAutom$$anonfun$1 extends AbstractFunction1<Tuple2<Object, Object>, Tuple2<Object, Object>> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final Tuple2<Object, Object> apply(Tuple2 x$1) {
/*    */       return x$1.swap$mcII$sp();
/*    */     }
/*    */     
/*    */     public DetWordAutom$$anonfun$1(DetWordAutom $outer) {}
/*    */   }
/*    */   
/*    */   public class DetWordAutom$$anonfun$toString$1 extends AbstractFunction1<Object, Object> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final StringBuilder sb$1;
/*    */     
/*    */     public DetWordAutom$$anonfun$toString$1(DetWordAutom $outer, StringBuilder sb$1) {}
/*    */     
/*    */     public final Object apply(int i) {
/*    */       Predef$ predef$1 = Predef$.MODULE$;
/*    */       this.sb$1.append((new StringOps("%d->%s\n")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(i), this.$outer.delta()[i] })));
/*    */       Predef$ predef$2 = Predef$.MODULE$;
/*    */       return (i < (this.$outer.default()).length) ? this.sb$1.append((new StringOps("_>%s\n")).format((Seq)Predef$.MODULE$.genericWrapArray(new Object[] { BoxesRunTime.boxToInteger(this.$outer.default()[i]) }))) : BoxedUnit.UNIT;
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scal\\util\automata\DetWordAutom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */