/*     */ package akka.serialization;
/*     */ 
/*     */ import akka.util.ClassLoaderObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import scala.Serializable;
/*     */ import scala.runtime.AbstractFunction0;
/*     */ import scala.runtime.BoxedUnit;
/*     */ 
/*     */ public final class JavaSerializer$ {
/*     */   public static final JavaSerializer$ MODULE$;
/*     */   
/*     */   private final JavaSerializer.CurrentSystem currentSystem;
/*     */   
/*     */   private JavaSerializer$() {
/*  86 */     MODULE$ = this;
/* 103 */     this.currentSystem = new JavaSerializer.CurrentSystem();
/*     */   }
/*     */   
/*     */   public JavaSerializer.CurrentSystem currentSystem() {
/* 103 */     return this.currentSystem;
/*     */   }
/*     */   
/*     */   public class JavaSerializer$$anonfun$toBinary$1 extends AbstractFunction0.mcV.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Object o$1;
/*     */     
/*     */     private final ObjectOutputStream out$1;
/*     */     
/*     */     public final void apply() {
/* 129 */       apply$mcV$sp();
/*     */     }
/*     */     
/*     */     public void apply$mcV$sp() {
/* 129 */       this.out$1.writeObject(this.o$1);
/*     */     }
/*     */     
/*     */     public JavaSerializer$$anonfun$toBinary$1(JavaSerializer $outer, Object o$1, ObjectOutputStream out$1) {}
/*     */   }
/*     */   
/*     */   public class JavaSerializer$$anonfun$1 extends AbstractFunction0<Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final ClassLoaderObjectInputStream in$1;
/*     */     
/*     */     public final Object apply() {
/* 136 */       return this.in$1.readObject();
/*     */     }
/*     */     
/*     */     public JavaSerializer$$anonfun$1(JavaSerializer $outer, ClassLoaderObjectInputStream in$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\serialization\JavaSerializer$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */