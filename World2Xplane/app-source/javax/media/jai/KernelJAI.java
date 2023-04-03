/*     */ package javax.media.jai;
/*     */ 
/*     */ import java.awt.image.Kernel;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class KernelJAI implements Serializable {
/*  47 */   public static final KernelJAI ERROR_FILTER_FLOYD_STEINBERG = new KernelJAI(3, 2, 1, 0, new float[] { 0.0F, 0.0F, 0.4375F, 0.1875F, 0.3125F, 0.0625F });
/*     */   
/*  60 */   public static final KernelJAI ERROR_FILTER_JARVIS = new KernelJAI(5, 3, 2, 0, new float[] { 
/*  60 */         0.0F, 0.0F, 0.0F, 0.14583333F, 0.104166664F, 0.0625F, 0.104166664F, 0.14583333F, 0.104166664F, 0.0625F, 
/*  60 */         0.020833334F, 0.0625F, 0.104166664F, 0.0625F, 0.020833334F });
/*     */   
/*  74 */   public static final KernelJAI ERROR_FILTER_STUCKI = new KernelJAI(5, 3, 2, 0, new float[] { 
/*  74 */         0.0F, 0.0F, 0.0F, 0.16666667F, 0.11904762F, 0.04761905F, 0.0952381F, 0.1904762F, 0.0952381F, 0.04761905F, 
/*  74 */         0.023809524F, 0.04761905F, 0.0952381F, 0.04761905F, 0.023809524F });
/*     */   
/*  83 */   public static final KernelJAI[] DITHER_MASK_441 = new KernelJAI[] { new KernelJAI(4, 4, 1, 1, new float[] { 
/*  83 */           0.9375F, 0.4375F, 0.8125F, 0.3125F, 0.1875F, 0.6875F, 0.0625F, 0.5625F, 0.75F, 0.25F, 
/*  83 */           0.875F, 0.375F, 0.0F, 0.5F, 0.125F, 0.625F }) };
/*     */   
/*  95 */   public static final KernelJAI[] DITHER_MASK_443 = new KernelJAI[] { new KernelJAI(4, 4, 1, 1, new float[] { 
/*  95 */           0.0F, 0.5F, 0.125F, 0.625F, 0.75F, 0.25F, 0.875F, 0.375F, 0.1875F, 0.6875F, 
/*  95 */           0.0625F, 0.5625F, 0.9375F, 0.4375F, 0.8125F, 0.3125F }), new KernelJAI(4, 4, 1, 1, new float[] { 
/*  95 */           0.625F, 0.125F, 0.5F, 0.0F, 0.375F, 0.875F, 0.25F, 0.75F, 0.5625F, 0.0625F, 
/*  95 */           0.6875F, 0.1875F, 0.3125F, 0.8125F, 0.4375F, 0.9375F }), new KernelJAI(4, 4, 1, 1, new float[] { 
/*  95 */           0.9375F, 0.4375F, 0.8125F, 0.3125F, 0.1875F, 0.6875F, 0.0625F, 0.5625F, 0.75F, 0.25F, 
/*  95 */           0.875F, 0.375F, 0.0F, 0.5F, 0.125F, 0.625F }) };
/*     */   
/* 116 */   public static final KernelJAI GRADIENT_MASK_SOBEL_VERTICAL = new KernelJAI(3, 3, 1, 1, new float[] { -1.0F, -2.0F, -1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F });
/*     */   
/* 125 */   public static final KernelJAI GRADIENT_MASK_SOBEL_HORIZONTAL = new KernelJAI(3, 3, 1, 1, new float[] { -1.0F, 0.0F, 1.0F, -2.0F, 0.0F, 2.0F, -1.0F, 0.0F, 1.0F });
/*     */   
/*     */   protected int width;
/*     */   
/*     */   protected int height;
/*     */   
/*     */   protected int xOrigin;
/*     */   
/*     */   protected int yOrigin;
/*     */   
/* 144 */   protected float[] data = null;
/*     */   
/* 147 */   protected float[] dataH = null;
/*     */   
/* 150 */   protected float[] dataV = null;
/*     */   
/*     */   protected boolean isSeparable = false;
/*     */   
/*     */   protected boolean isHorizontallySymmetric = false;
/*     */   
/*     */   protected boolean isVerticallySymmetric = false;
/*     */   
/* 162 */   protected KernelJAI rotatedKernel = null;
/*     */   
/*     */   private synchronized void checkSeparable() {
/* 168 */     float floatZeroTol = 1.0E-5F;
/* 170 */     if (this.isSeparable)
/*     */       return; 
/* 171 */     if (this.width <= 1 || this.height <= 1)
/*     */       return; 
/* 183 */     float maxData = 0.0F;
/* 184 */     int imax = 0, jmax = 0;
/* 186 */     for (int k = 0; k < this.data.length; k++) {
/* 187 */       float tmp = Math.abs(this.data[k]);
/* 188 */       if (tmp > maxData) {
/* 189 */         imax = k;
/* 190 */         maxData = tmp;
/*     */       } 
/*     */     } 
/* 197 */     if (maxData < floatZeroTol / this.data.length) {
/* 198 */       this.isSeparable = false;
/*     */       return;
/*     */     } 
/* 202 */     float[] tmpRow = new float[this.width];
/* 203 */     float fac = 1.0F / this.data[imax];
/* 206 */     jmax = imax % this.width;
/* 207 */     imax /= this.width;
/* 210 */     for (int j = 0; j < this.width; j++)
/* 211 */       tmpRow[j] = this.data[imax * this.width + j] * fac; 
/*     */     int i, i0;
/* 217 */     for (i = 0, i0 = 0; i < this.height; i++, i0 += this.width) {
/* 218 */       for (int n = 0; n < this.width; n++) {
/* 219 */         float tmp = Math.abs(this.data[i0 + jmax] * tmpRow[n] - this.data[i0 + n]);
/* 220 */         if (tmp > floatZeroTol) {
/* 221 */           this.isSeparable = false;
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/* 228 */     this.dataH = tmpRow;
/* 229 */     this.dataV = new float[this.height];
/* 230 */     for (i = 0; i < this.height; i++)
/* 231 */       this.dataV[i] = this.data[jmax + i * this.width]; 
/* 233 */     this.isSeparable = true;
/* 242 */     float sumH = 0.0F, sumV = 0.0F;
/*     */     int m;
/* 243 */     for (m = 0; m < this.width; ) {
/* 243 */       sumH += this.dataH[m];
/* 243 */       m++;
/*     */     } 
/* 244 */     for (m = 0; m < this.height; ) {
/* 244 */       sumV += this.dataV[m];
/* 244 */       m++;
/*     */     } 
/* 246 */     if (Math.abs(sumH) >= Math.abs(sumV) && Math.abs(sumH) > floatZeroTol) {
/* 247 */       fac = 1.0F / sumH;
/* 248 */       for (m = 0; m < this.width; ) {
/* 248 */         this.dataH[m] = this.dataH[m] * fac;
/* 248 */         m++;
/*     */       } 
/* 249 */       for (m = 0; m < this.height; ) {
/* 249 */         this.dataV[m] = this.dataV[m] * sumH;
/* 249 */         m++;
/*     */       } 
/* 250 */     } else if (Math.abs(sumH) < Math.abs(sumV) && Math.abs(sumV) > floatZeroTol) {
/* 252 */       fac = 1.0F / sumV;
/* 253 */       for (m = 0; m < this.width; ) {
/* 253 */         this.dataH[m] = this.dataH[m] * sumV;
/* 253 */         m++;
/*     */       } 
/* 254 */       for (m = 0; m < this.height; ) {
/* 254 */         this.dataV[m] = this.dataV[m] * fac;
/* 254 */         m++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void classifyKernel() {
/* 261 */     if (!this.isSeparable)
/* 262 */       checkSeparable(); 
/* 264 */     this.isHorizontallySymmetric = false;
/* 265 */     this.isVerticallySymmetric = false;
/*     */   }
/*     */   
/*     */   public KernelJAI(int width, int height, int xOrigin, int yOrigin, float[] data) {
/* 291 */     if (data == null)
/* 292 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 295 */     this.width = width;
/* 296 */     this.height = height;
/* 297 */     this.xOrigin = xOrigin;
/* 298 */     this.yOrigin = yOrigin;
/* 299 */     this.data = (float[])data.clone();
/* 300 */     if (width <= 0)
/* 301 */       throw new IllegalArgumentException(JaiI18N.getString("KernelJAI0")); 
/* 303 */     if (height <= 0)
/* 304 */       throw new IllegalArgumentException(JaiI18N.getString("KernelJAI1")); 
/* 306 */     if (width * height != data.length)
/* 307 */       throw new IllegalArgumentException(JaiI18N.getString("KernelJAI2")); 
/* 309 */     classifyKernel();
/*     */   }
/*     */   
/*     */   public KernelJAI(int width, int height, int xOrigin, int yOrigin, float[] dataH, float[] dataV) {
/* 342 */     if (dataH == null || dataV == null)
/* 343 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 346 */     if (width <= 0)
/* 347 */       throw new IllegalArgumentException(JaiI18N.getString("KernelJAI0")); 
/* 350 */     if (height <= 0)
/* 351 */       throw new IllegalArgumentException(JaiI18N.getString("KernelJAI1")); 
/* 354 */     if (width != dataH.length)
/* 355 */       throw new IllegalArgumentException(JaiI18N.getString("KernelJAI3")); 
/* 358 */     if (height != dataV.length)
/* 359 */       throw new IllegalArgumentException(JaiI18N.getString("KernelJAI4")); 
/* 362 */     this.width = width;
/* 363 */     this.height = height;
/* 364 */     this.xOrigin = xOrigin;
/* 365 */     this.yOrigin = yOrigin;
/* 366 */     this.dataH = (float[])dataH.clone();
/* 367 */     this.dataV = (float[])dataV.clone();
/* 368 */     this.data = new float[dataH.length * dataV.length];
/* 370 */     int rowOffset = 0;
/* 371 */     for (int i = 0; i < dataV.length; i++) {
/* 372 */       float vValue = dataV[i];
/* 373 */       for (int j = 0; j < dataH.length; j++)
/* 374 */         this.data[rowOffset + j] = vValue * dataH[j]; 
/* 376 */       rowOffset += dataH.length;
/*     */     } 
/* 378 */     this.isSeparable = true;
/* 379 */     classifyKernel();
/*     */   }
/*     */   
/*     */   public KernelJAI(int width, int height, float[] data) {
/* 398 */     this(width, height, width / 2, height / 2, data);
/*     */   }
/*     */   
/*     */   public KernelJAI(Kernel k) {
/* 409 */     this(k.getWidth(), k.getHeight(), k.getXOrigin(), k.getYOrigin(), k.getKernelData(null));
/*     */   }
/*     */   
/*     */   public int getWidth() {
/* 415 */     return this.width;
/*     */   }
/*     */   
/*     */   public int getHeight() {
/* 420 */     return this.height;
/*     */   }
/*     */   
/*     */   public int getXOrigin() {
/* 425 */     return this.xOrigin;
/*     */   }
/*     */   
/*     */   public int getYOrigin() {
/* 430 */     return this.yOrigin;
/*     */   }
/*     */   
/*     */   public float[] getKernelData() {
/* 435 */     return (float[])this.data.clone();
/*     */   }
/*     */   
/*     */   public float[] getHorizontalKernelData() {
/* 444 */     if (this.dataH == null)
/* 445 */       return null; 
/* 447 */     return (float[])this.dataH.clone();
/*     */   }
/*     */   
/*     */   public float[] getVerticalKernelData() {
/* 456 */     if (this.dataV == null)
/* 457 */       return null; 
/* 459 */     return (float[])this.dataV.clone();
/*     */   }
/*     */   
/*     */   public float getElement(int xIndex, int yIndex) {
/* 469 */     if (!this.isSeparable)
/* 470 */       return this.data[yIndex * this.width + xIndex]; 
/* 472 */     return this.dataH[xIndex] * this.dataV[yIndex];
/*     */   }
/*     */   
/*     */   public boolean isSeparable() {
/* 480 */     return this.isSeparable;
/*     */   }
/*     */   
/*     */   public boolean isHorizontallySymmetric() {
/* 485 */     return this.isHorizontallySymmetric;
/*     */   }
/*     */   
/*     */   public boolean isVerticallySymmetric() {
/* 490 */     return this.isVerticallySymmetric;
/*     */   }
/*     */   
/*     */   public int getLeftPadding() {
/* 497 */     return this.xOrigin;
/*     */   }
/*     */   
/*     */   public int getRightPadding() {
/* 504 */     return this.width - this.xOrigin - 1;
/*     */   }
/*     */   
/*     */   public int getTopPadding() {
/* 511 */     return this.yOrigin;
/*     */   }
/*     */   
/*     */   public int getBottomPadding() {
/* 518 */     return this.height - this.yOrigin - 1;
/*     */   }
/*     */   
/*     */   public KernelJAI getRotatedKernel() {
/* 528 */     if (this.rotatedKernel == null)
/* 529 */       if (this.isSeparable) {
/* 530 */         float[] rotDataH = new float[this.width];
/* 531 */         float[] rotDataV = new float[this.height];
/*     */         int i;
/* 532 */         for (i = 0; i < this.width; i++)
/* 533 */           rotDataH[i] = this.dataH[this.width - 1 - i]; 
/* 535 */         for (i = 0; i < this.height; i++)
/* 536 */           rotDataV[i] = this.dataV[this.height - 1 - i]; 
/* 538 */         this.rotatedKernel = new KernelJAI(this.width, this.height, this.width - 1 - this.xOrigin, this.height - 1 - this.yOrigin, rotDataH, rotDataV);
/*     */       } else {
/* 546 */         int length = this.data.length;
/* 547 */         float[] newData = new float[this.data.length];
/* 548 */         for (int i = 0; i < length; i++)
/* 549 */           newData[i] = this.data[length - 1 - i]; 
/* 551 */         this.rotatedKernel = new KernelJAI(this.width, this.height, this.width - 1 - this.xOrigin, this.height - 1 - this.yOrigin, newData);
/*     */       }  
/* 559 */     return this.rotatedKernel;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\KernelJAI.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */