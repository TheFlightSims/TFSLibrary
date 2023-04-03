/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class RainbowPalette extends ColorPalette implements Serializable {
/*     */   private static final long serialVersionUID = -1906707320728242478L;
/*     */   
/*  63 */   private int[] red = new int[] { 
/*  63 */       255, 0, 120, 115, 111, 106, 102, 97, 93, 88, 
/*  63 */       84, 79, 75, 70, 66, 61, 57, 52, 48, 43, 
/*  63 */       39, 34, 30, 25, 21, 16, 12, 7, 3, 0, 
/*  63 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/*  63 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/*  63 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/*  63 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/*  63 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/*  63 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/*  63 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/*  63 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/*  63 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/*  63 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/*  63 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/*  63 */       0, 0, 1, 5, 10, 14, 19, 23, 28, 32, 
/*  63 */       37, 41, 46, 50, 55, 59, 64, 68, 73, 77, 
/*  63 */       82, 86, 91, 95, 100, 104, 109, 113, 118, 123, 
/*  63 */       127, 132, 136, 141, 145, 150, 154, 159, 163, 168, 
/*  63 */       172, 177, 181, 186, 190, 195, 199, 204, 208, 213, 
/*  63 */       217, 222, 226, 231, 235, 240, 244, 249, 253, 255, 
/*  63 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/*  63 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/*  63 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/*  63 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/*  63 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/*  63 */       255, 255, 255, 255, 255, 255 };
/*     */   
/*  97 */   private int[] green = new int[] { 
/*  97 */       255, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/*  97 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/*  97 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 
/*  97 */       6, 11, 15, 20, 24, 29, 33, 38, 42, 47, 
/*  97 */       51, 56, 60, 65, 69, 74, 78, 83, 87, 92, 
/*  97 */       96, 101, 105, 110, 114, 119, 123, 128, 132, 137, 
/*  97 */       141, 146, 150, 155, 159, 164, 168, 173, 177, 182, 
/*  97 */       186, 191, 195, 200, 204, 209, 213, 218, 222, 227, 
/*  97 */       231, 236, 241, 245, 250, 254, 255, 255, 255, 255, 
/*  97 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/*  97 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/*  97 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/*  97 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/*  97 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/*  97 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/*  97 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/*  97 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/*  97 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/*  97 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/*  97 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 252, 
/*  97 */       248, 243, 239, 234, 230, 225, 221, 216, 212, 207, 
/*  97 */       203, 198, 194, 189, 185, 180, 176, 171, 167, 162, 
/*  97 */       158, 153, 149, 144, 140, 135, 131, 126, 122, 117, 
/*  97 */       113, 108, 104, 99, 95, 90, 86, 81, 77, 72, 
/*  97 */       68, 63, 59, 54, 50, 45, 41, 36, 32, 27, 
/*  97 */       23, 18, 14, 9, 5, 0 };
/*     */   
/* 131 */   private int[] blue = new int[] { 
/* 131 */       255, 0, 255, 255, 255, 255, 255, 255, 255, 255, 
/* 131 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/* 131 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/* 131 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/* 131 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/* 131 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/* 131 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/* 131 */       255, 255, 255, 255, 255, 255, 255, 255, 255, 255, 
/* 131 */       255, 255, 255, 255, 255, 255, 251, 247, 242, 238, 
/* 131 */       233, 229, 224, 220, 215, 211, 206, 202, 197, 193, 
/* 131 */       188, 184, 179, 175, 170, 166, 161, 157, 152, 148, 
/* 131 */       143, 139, 134, 130, 125, 121, 116, 112, 107, 103, 
/* 131 */       98, 94, 89, 85, 80, 76, 71, 67, 62, 58, 
/* 131 */       53, 49, 44, 40, 35, 31, 26, 22, 17, 13, 
/* 131 */       8, 4, 0, 0, 0, 0, 0, 0, 0, 0, 
/* 131 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/* 131 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/* 131 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/* 131 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/* 131 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/* 131 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/* 131 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/* 131 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/* 131 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/* 131 */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/* 131 */       0, 0, 0, 0, 0, 0 };
/*     */   
/*     */   public RainbowPalette() {
/* 169 */     initialize();
/*     */   }
/*     */   
/*     */   public void initialize() {
/* 177 */     setPaletteName("Rainbow");
/* 179 */     this.r = new int[this.red.length];
/* 180 */     this.g = new int[this.green.length];
/* 181 */     this.b = new int[this.blue.length];
/* 182 */     System.arraycopy(this.red, 0, this.r, 0, this.red.length);
/* 183 */     System.arraycopy(this.green, 0, this.g, 0, this.green.length);
/* 184 */     System.arraycopy(this.blue, 0, this.b, 0, this.blue.length);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jfree\chart\plot\RainbowPalette.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */