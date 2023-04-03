/*     */ package scala.collection.parallel;
/*     */ 
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.mutable.ArrayBuffer;
/*     */ import scala.collection.mutable.ArrayBuffer$;
/*     */ 
/*     */ public abstract class Tasks$class {
/*     */   public static void $init$(Tasks $this) {
/* 101 */     $this.scala$collection$parallel$Tasks$_setter_$debugMessages_$eq((ArrayBuffer)ArrayBuffer$.MODULE$.apply((Seq)Nil$.MODULE$));
/*     */   }
/*     */   
/*     */   public static ArrayBuffer debuglog(Tasks $this, String s) {
/* 103 */     synchronized ($this) {
/* 104 */       ArrayBuffer arrayBuffer = $this.debugMessages().$plus$eq(s);
/*     */       /* monitor exit ClassFileLocalVariableReferenceExpression{type=ObjectType{scala/collection/parallel/Tasks}, name=$this} */
/*     */       return arrayBuffer;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\parallel\Tasks$class.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */