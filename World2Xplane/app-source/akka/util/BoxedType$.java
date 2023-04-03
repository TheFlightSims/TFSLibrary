/*    */ package akka.util;
/*    */ 
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.Map;
/*    */ import scala.runtime.BoxedUnit;
/*    */ 
/*    */ public final class BoxedType$ {
/*    */   public static final BoxedType$ MODULE$;
/*    */   
/*    */   private final Map<Class<?>, Class<?>> toBoxed;
/*    */   
/*    */   private BoxedType$() {
/*  6 */     MODULE$ = this;
/*  9 */     (new scala.Tuple2[9])[0] = scala.Predef$ArrowAssoc$.MODULE$
/* 10 */       .$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(boolean.class), Boolean.class);
/* 11 */     (new scala.Tuple2[9])[1] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(byte.class), Byte.class);
/* 12 */     (new scala.Tuple2[9])[2] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(char.class), Character.class);
/* 13 */     (new scala.Tuple2[9])[3] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(short.class), Short.class);
/* 14 */     (new scala.Tuple2[9])[4] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(int.class), Integer.class);
/* 15 */     (new scala.Tuple2[9])[5] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(long.class), Long.class);
/* 16 */     (new scala.Tuple2[9])[6] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(float.class), Float.class);
/* 17 */     (new scala.Tuple2[9])[7] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(double.class), Double.class);
/* 18 */     (new scala.Tuple2[9])[8] = scala.Predef$ArrowAssoc$.MODULE$.$minus$greater$extension(scala.Predef$.MODULE$.any2ArrowAssoc(BoxedUnit.TYPE), BoxedUnit.class);
/*    */     this.toBoxed = (Map<Class<?>, Class<?>>)scala.Predef$.MODULE$.Map().apply((Seq)scala.Predef$.MODULE$.wrapRefArray((Object[])new scala.Tuple2[9]));
/*    */   }
/*    */   
/*    */   private Map<Class<?>, Class<?>> toBoxed() {
/*    */     return this.toBoxed;
/*    */   }
/*    */   
/*    */   public final Class<?> apply(Class<?> c) {
/* 20 */     return c.isPrimitive() ? (Class)toBoxed().apply(c) : c;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\BoxedType$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */