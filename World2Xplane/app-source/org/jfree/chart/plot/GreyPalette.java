/*    */ package org.jfree.chart.plot;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class GreyPalette extends ColorPalette implements Serializable {
/*    */   private static final long serialVersionUID = -2120941170159987395L;
/*    */   
/*    */   public GreyPalette() {
/* 64 */     initialize();
/*    */   }
/*    */   
/*    */   public void initialize() {
/* 72 */     setPaletteName("Grey");
/* 74 */     this.r = new int[256];
/* 75 */     this.g = new int[256];
/* 76 */     this.b = new int[256];
/* 78 */     this.r[0] = 255;
/* 79 */     this.g[0] = 255;
/* 80 */     this.b[0] = 255;
/* 81 */     this.r[1] = 0;
/* 82 */     this.g[1] = 0;
/* 83 */     this.b[1] = 0;
/* 85 */     for (int i = 2; i < 256; i++) {
/* 86 */       this.r[i] = i;
/* 87 */       this.g[i] = i;
/* 88 */       this.b[i] = i;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\GreyPalette.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */