/*     */ package scala.xml.dtd;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Some;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.AbstractFunction2;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ public final class DEFAULT$ extends AbstractFunction2<Object, String, DEFAULT> implements Serializable {
/*     */   public static final DEFAULT$ MODULE$;
/*     */   
/*     */   public final String toString() {
/* 150 */     return "DEFAULT";
/*     */   }
/*     */   
/*     */   public DEFAULT apply(boolean fixed, String attValue) {
/* 150 */     return new DEFAULT(fixed, attValue);
/*     */   }
/*     */   
/*     */   public Option<Tuple2<Object, String>> unapply(DEFAULT x$0) {
/* 150 */     return (x$0 == null) ? (Option<Tuple2<Object, String>>)scala.None$.MODULE$ : (Option<Tuple2<Object, String>>)new Some(new Tuple2(BoxesRunTime.boxToBoolean(x$0.fixed()), x$0.attValue()));
/*     */   }
/*     */   
/*     */   private Object readResolve() {
/* 150 */     return MODULE$;
/*     */   }
/*     */   
/*     */   private DEFAULT$() {
/* 150 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   public class DEFAULT$$anonfun$toString$2 extends AbstractFunction1<StringBuilder, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final void apply(StringBuilder sb) {
/* 151 */       this.$outer.buildString(sb);
/*     */     }
/*     */     
/*     */     public DEFAULT$$anonfun$toString$2(DEFAULT $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\DEFAULT$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */