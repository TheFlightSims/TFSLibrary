/*     */ package scala;
/*     */ 
/*     */ public final class language$ {
/*     */   public static final language$ MODULE$;
/*     */   
/*     */   private languageFeature.dynamics dynamics;
/*     */   
/*     */   private languageFeature.postfixOps postfixOps;
/*     */   
/*     */   private languageFeature.reflectiveCalls reflectiveCalls;
/*     */   
/*     */   private languageFeature.implicitConversions implicitConversions;
/*     */   
/*     */   private languageFeature.higherKinds higherKinds;
/*     */   
/*     */   private languageFeature.existentials existentials;
/*     */   
/*     */   private volatile byte bitmap$0;
/*     */   
/*     */   private language$() {
/*  26 */     MODULE$ = this;
/*     */   }
/*     */   
/*     */   private languageFeature.dynamics dynamics$lzycompute() {
/*  45 */     synchronized (this) {
/*  45 */       if ((byte)(this.bitmap$0 & 0x1) == 0) {
/*  45 */         this.dynamics = languageFeature.dynamics$.MODULE$;
/*  45 */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x1);
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/language$}} */
/*  45 */       return this.dynamics;
/*     */     } 
/*     */   }
/*     */   
/*     */   public languageFeature.dynamics dynamics() {
/*  45 */     return ((byte)(this.bitmap$0 & 0x1) == 0) ? dynamics$lzycompute() : this.dynamics;
/*     */   }
/*     */   
/*     */   private languageFeature.postfixOps postfixOps$lzycompute() {
/*  56 */     synchronized (this) {
/*  56 */       if ((byte)(this.bitmap$0 & 0x2) == 0) {
/*  56 */         this.postfixOps = languageFeature.postfixOps$.MODULE$;
/*  56 */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x2);
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/language$}} */
/*  56 */       return this.postfixOps;
/*     */     } 
/*     */   }
/*     */   
/*     */   public languageFeature.postfixOps postfixOps() {
/*  56 */     return ((byte)(this.bitmap$0 & 0x2) == 0) ? postfixOps$lzycompute() : this.postfixOps;
/*     */   }
/*     */   
/*     */   private languageFeature.reflectiveCalls reflectiveCalls$lzycompute() {
/*  74 */     synchronized (this) {
/*  74 */       if ((byte)(this.bitmap$0 & 0x4) == 0) {
/*  74 */         this.reflectiveCalls = languageFeature.reflectiveCalls$.MODULE$;
/*  74 */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x4);
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/language$}} */
/*  74 */       return this.reflectiveCalls;
/*     */     } 
/*     */   }
/*     */   
/*     */   public languageFeature.reflectiveCalls reflectiveCalls() {
/*  74 */     return ((byte)(this.bitmap$0 & 0x4) == 0) ? reflectiveCalls$lzycompute() : this.reflectiveCalls;
/*     */   }
/*     */   
/*     */   private languageFeature.implicitConversions implicitConversions$lzycompute() {
/* 101 */     synchronized (this) {
/* 101 */       if ((byte)(this.bitmap$0 & 0x8) == 0) {
/* 101 */         this.implicitConversions = languageFeature.implicitConversions$.MODULE$;
/* 101 */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x8);
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/language$}} */
/* 101 */       return this.implicitConversions;
/*     */     } 
/*     */   }
/*     */   
/*     */   public languageFeature.implicitConversions implicitConversions() {
/* 101 */     return ((byte)(this.bitmap$0 & 0x8) == 0) ? implicitConversions$lzycompute() : this.implicitConversions;
/*     */   }
/*     */   
/*     */   private languageFeature.higherKinds higherKinds$lzycompute() {
/* 124 */     synchronized (this) {
/* 124 */       if ((byte)(this.bitmap$0 & 0x10) == 0) {
/* 124 */         this.higherKinds = languageFeature.higherKinds$.MODULE$;
/* 124 */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x10);
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/language$}} */
/* 124 */       return this.higherKinds;
/*     */     } 
/*     */   }
/*     */   
/*     */   public languageFeature.higherKinds higherKinds() {
/* 124 */     return ((byte)(this.bitmap$0 & 0x10) == 0) ? higherKinds$lzycompute() : this.higherKinds;
/*     */   }
/*     */   
/*     */   private languageFeature.existentials existentials$lzycompute() {
/* 142 */     synchronized (this) {
/* 142 */       if ((byte)(this.bitmap$0 & 0x20) == 0) {
/* 142 */         this.existentials = languageFeature.existentials$.MODULE$;
/* 142 */         this.bitmap$0 = (byte)(this.bitmap$0 | 0x20);
/*     */       } 
/*     */       /* monitor exit ThisExpression{ObjectType{scala/language$}} */
/* 142 */       return this.existentials;
/*     */     } 
/*     */   }
/*     */   
/*     */   public languageFeature.existentials existentials() {
/* 142 */     return ((byte)(this.bitmap$0 & 0x20) == 0) ? existentials$lzycompute() : this.existentials;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\language$.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */