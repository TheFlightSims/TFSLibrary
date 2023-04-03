/*     */ package javax.media.jai;
/*     */ 
/*     */ public class ColorCube extends LookupTableJAI {
/*  36 */   public static final ColorCube BYTE_496 = createColorCube(0, 38, new int[] { 4, 9, 6 });
/*     */   
/*  44 */   public static final ColorCube BYTE_855 = createColorCube(0, 54, new int[] { 8, 5, 5 });
/*     */   
/*     */   private int[] dimension;
/*     */   
/*     */   private int[] dimsLessOne;
/*     */   
/*     */   private int[] multipliers;
/*     */   
/*     */   private int adjustedOffset;
/*     */   
/*     */   private int dataType;
/*     */   
/*     */   private int numBands;
/*     */   
/*     */   public static ColorCube createColorCube(int dataType, int offset, int[] dimension) {
/*     */     ColorCube colorCube;
/* 106 */     switch (dataType) {
/*     */       case 0:
/* 108 */         colorCube = createColorCubeByte(offset, dimension);
/* 129 */         return colorCube;
/*     */       case 2:
/*     */         colorCube = createColorCubeShort(offset, dimension);
/* 129 */         return colorCube;
/*     */       case 1:
/*     */         colorCube = createColorCubeUShort(offset, dimension);
/* 129 */         return colorCube;
/*     */       case 3:
/*     */         colorCube = createColorCubeInt(offset, dimension);
/* 129 */         return colorCube;
/*     */       case 4:
/*     */         colorCube = createColorCubeFloat(offset, dimension);
/* 129 */         return colorCube;
/*     */       case 5:
/*     */         colorCube = createColorCubeDouble(offset, dimension);
/* 129 */         return colorCube;
/*     */     } 
/*     */     throw new RuntimeException(JaiI18N.getString("ColorCube0"));
/*     */   }
/*     */   
/*     */   public static ColorCube createColorCube(int dataType, int[] dimension) {
/* 144 */     if (dimension == null)
/* 145 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 148 */     return createColorCube(dataType, 0, dimension);
/*     */   }
/*     */   
/*     */   private static ColorCube createColorCubeByte(int offset, int[] dimension) {
/* 165 */     ColorCube colorCube = new ColorCube(createDataArrayByte(offset, dimension), offset);
/* 167 */     colorCube.initFields(offset, dimension);
/* 168 */     return colorCube;
/*     */   }
/*     */   
/*     */   private static ColorCube createColorCubeShort(int offset, int[] dimension) {
/* 185 */     ColorCube colorCube = new ColorCube(createDataArrayShort(offset, dimension), offset, false);
/* 188 */     colorCube.initFields(offset, dimension);
/* 189 */     return colorCube;
/*     */   }
/*     */   
/*     */   private static ColorCube createColorCubeUShort(int offset, int[] dimension) {
/* 207 */     ColorCube colorCube = new ColorCube(createDataArrayUShort(offset, dimension), offset, true);
/* 210 */     colorCube.initFields(offset, dimension);
/* 211 */     return colorCube;
/*     */   }
/*     */   
/*     */   private static ColorCube createColorCubeInt(int offset, int[] dimension) {
/* 228 */     ColorCube colorCube = new ColorCube(createDataArrayInt(offset, dimension), offset);
/* 230 */     colorCube.initFields(offset, dimension);
/* 231 */     return colorCube;
/*     */   }
/*     */   
/*     */   private static ColorCube createColorCubeFloat(int offset, int[] dimension) {
/* 248 */     ColorCube colorCube = new ColorCube(createDataArrayFloat(offset, dimension), offset);
/* 250 */     colorCube.initFields(offset, dimension);
/* 251 */     return colorCube;
/*     */   }
/*     */   
/*     */   private static ColorCube createColorCubeDouble(int offset, int[] dimension) {
/* 268 */     ColorCube colorCube = new ColorCube(createDataArrayDouble(offset, dimension), offset);
/* 271 */     colorCube.initFields(offset, dimension);
/* 272 */     return colorCube;
/*     */   }
/*     */   
/*     */   private static Object createDataArray(int dataType, int offset, int[] dimension) {
/*     */     double dataMin, dataMax;
/*     */     Object dataArray;
/* 300 */     int nbands = dimension.length;
/* 301 */     if (nbands == 0)
/* 302 */       throw new RuntimeException(JaiI18N.getString("ColorCube1")); 
/* 306 */     for (int band = 0; band < nbands; band++) {
/* 307 */       if (dimension[band] == 0)
/* 308 */         throw new RuntimeException(JaiI18N.getString("ColorCube2")); 
/*     */     } 
/* 313 */     int[] dimensionAbs = new int[nbands];
/* 314 */     for (int i = 0; i < nbands; i++)
/* 315 */       dimensionAbs[i] = Math.abs(dimension[i]); 
/* 319 */     double floatSize = dimensionAbs[0];
/* 320 */     for (int j = 1; j < nbands; j++)
/* 321 */       floatSize *= dimensionAbs[j]; 
/* 323 */     if (floatSize > 2.147483647E9D)
/* 327 */       throw new RuntimeException(JaiI18N.getString("ColorCube3")); 
/* 329 */     int size = (int)floatSize;
/* 335 */     switch (dataType) {
/*     */       case 0:
/* 337 */         dataMin = 0.0D;
/* 338 */         dataMax = 255.0D;
/* 339 */         dataArray = new byte[nbands][size];
/*     */         break;
/*     */       case 2:
/* 342 */         dataMin = -32768.0D;
/* 343 */         dataMax = 32767.0D;
/* 344 */         dataArray = new short[nbands][size];
/*     */         break;
/*     */       case 1:
/* 347 */         dataMin = 0.0D;
/* 348 */         dataMax = 65535.0D;
/* 349 */         dataArray = new short[nbands][size];
/*     */         break;
/*     */       case 3:
/* 352 */         dataMin = -2.147483648E9D;
/* 353 */         dataMax = 2.147483647E9D;
/* 354 */         dataArray = new int[nbands][size];
/*     */         break;
/*     */       case 4:
/* 357 */         dataMin = -3.4028234663852886E38D;
/* 358 */         dataMax = 3.4028234663852886E38D;
/* 359 */         dataArray = new float[nbands][size];
/*     */         break;
/*     */       case 5:
/* 362 */         dataMin = -1.7976931348623157E308D;
/* 363 */         dataMax = Double.MAX_VALUE;
/* 364 */         dataArray = new double[nbands][size];
/*     */         break;
/*     */       default:
/* 367 */         throw new RuntimeException(JaiI18N.getString("ColorCube7"));
/*     */     } 
/* 371 */     if ((size + offset) > dataMax)
/* 372 */       throw new RuntimeException(JaiI18N.getString("ColorCube4")); 
/* 376 */     int[] multipliers = new int[nbands];
/* 377 */     multipliers[0] = 1;
/*     */     int k;
/* 378 */     for (k = 1; k < nbands; k++)
/* 379 */       multipliers[k] = multipliers[k - 1] * dimensionAbs[k - 1]; 
/* 383 */     for (k = 0; k < nbands; k++) {
/*     */       double delta, start;
/*     */       int index;
/*     */       byte[][] byteData;
/*     */       short[][] shortData;
/*     */       int[][] intData;
/*     */       float[][] floatData;
/*     */       double[][] doubleData;
/* 385 */       int idimension = dimensionAbs[k];
/* 387 */       if (idimension == 1) {
/* 389 */         delta = 0.0D;
/* 390 */       } else if (dataType == 4 || dataType == 5) {
/* 392 */         delta = 1.0D / (idimension - 1);
/*     */       } else {
/* 394 */         delta = (dataMax - dataMin) / (idimension - 1);
/*     */       } 
/* 399 */       if (dimension[k] < 0) {
/* 400 */         delta = -delta;
/* 401 */         start = dataMax;
/*     */       } else {
/* 403 */         start = dataMin;
/*     */       } 
/* 405 */       int repeatCount = multipliers[k];
/* 409 */       switch (dataType) {
/*     */         case 0:
/* 411 */           byteData = (byte[][])dataArray;
/* 412 */           index = 0;
/* 413 */           while (index < size) {
/* 414 */             double val = start;
/* 415 */             for (int m = 0; m < idimension; m++) {
/* 416 */               for (int n = 0; n < repeatCount; n++) {
/* 417 */                 byteData[k][index] = (byte)((int)(val + 0.5D) & 0xFF);
/* 419 */                 index++;
/*     */               } 
/* 421 */               val += delta;
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case 1:
/*     */         case 2:
/* 428 */           shortData = (short[][])dataArray;
/* 429 */           index = 0;
/* 430 */           while (index < size) {
/* 431 */             double val = start;
/* 432 */             for (int m = 0; m < idimension; m++) {
/* 433 */               for (int n = 0; n < repeatCount; n++) {
/* 434 */                 shortData[k][index] = (short)(int)(val + 0.5D);
/* 435 */                 index++;
/*     */               } 
/* 437 */               val += delta;
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case 3:
/* 443 */           intData = (int[][])dataArray;
/* 444 */           index = 0;
/* 445 */           while (index < size) {
/* 446 */             double val = start;
/* 447 */             for (int m = 0; m < idimension; m++) {
/* 448 */               for (int n = 0; n < repeatCount; n++) {
/* 449 */                 intData[k][index] = (int)(val + 0.5D);
/* 450 */                 index++;
/*     */               } 
/* 452 */               val += delta;
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case 4:
/* 458 */           floatData = (float[][])dataArray;
/* 459 */           index = 0;
/* 460 */           while (index < size) {
/* 461 */             double val = start;
/* 462 */             for (int m = 0; m < idimension; m++) {
/* 463 */               for (int n = 0; n < repeatCount; n++) {
/* 464 */                 floatData[k][index] = (float)val;
/* 465 */                 index++;
/*     */               } 
/* 467 */               val += delta;
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         case 5:
/* 473 */           doubleData = (double[][])dataArray;
/* 474 */           index = 0;
/* 475 */           while (index < size) {
/* 476 */             double val = start;
/* 477 */             for (int m = 0; m < idimension; m++) {
/* 478 */               for (int n = 0; n < repeatCount; n++) {
/* 479 */                 doubleData[k][index] = val;
/* 480 */                 index++;
/*     */               } 
/* 482 */               val += delta;
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         default:
/* 487 */           throw new RuntimeException(JaiI18N.getString("ColorCube5"));
/*     */       } 
/*     */     } 
/* 491 */     return dataArray;
/*     */   }
/*     */   
/*     */   private static byte[][] createDataArrayByte(int offset, int[] dimension) {
/* 508 */     return (byte[][])createDataArray(0, offset, dimension);
/*     */   }
/*     */   
/*     */   private static short[][] createDataArrayShort(int offset, int[] dimension) {
/* 526 */     return (short[][])createDataArray(2, offset, dimension);
/*     */   }
/*     */   
/*     */   private static short[][] createDataArrayUShort(int offset, int[] dimension) {
/* 544 */     return (short[][])createDataArray(1, offset, dimension);
/*     */   }
/*     */   
/*     */   private static int[][] createDataArrayInt(int offset, int[] dimension) {
/* 562 */     return (int[][])createDataArray(3, offset, dimension);
/*     */   }
/*     */   
/*     */   private static float[][] createDataArrayFloat(int offset, int[] dimension) {
/* 580 */     return (float[][])createDataArray(4, offset, dimension);
/*     */   }
/*     */   
/*     */   private static double[][] createDataArrayDouble(int offset, int[] dimension) {
/* 598 */     return (double[][])createDataArray(5, offset, dimension);
/*     */   }
/*     */   
/*     */   protected ColorCube(byte[][] data, int offset) {
/* 612 */     super(data, offset);
/*     */   }
/*     */   
/*     */   protected ColorCube(short[][] data, int offset, boolean isUShort) {
/* 627 */     super(data, offset, isUShort);
/*     */   }
/*     */   
/*     */   protected ColorCube(int[][] data, int offset) {
/* 640 */     super(data, offset);
/*     */   }
/*     */   
/*     */   protected ColorCube(float[][] data, int offset) {
/* 653 */     super(data, offset);
/*     */   }
/*     */   
/*     */   protected ColorCube(double[][] data, int offset) {
/* 666 */     super(data, offset);
/*     */   }
/*     */   
/*     */   private void initFields(int offset, int[] dimension) {
/* 677 */     this.dimension = dimension;
/* 680 */     this.multipliers = new int[dimension.length];
/* 681 */     this.dimsLessOne = new int[dimension.length];
/* 684 */     this.multipliers[0] = 1;
/*     */     int i;
/* 685 */     for (i = 1; i < this.multipliers.length; i++)
/* 686 */       this.multipliers[i] = this.multipliers[i - 1] * Math.abs(dimension[i - 1]); 
/* 691 */     for (i = 0; i < this.multipliers.length; i++) {
/* 692 */       if (dimension[i] < 0)
/* 693 */         this.multipliers[i] = -this.multipliers[i]; 
/* 695 */       this.dimsLessOne[i] = Math.abs(dimension[i]) - 1;
/*     */     } 
/* 699 */     this.adjustedOffset = offset;
/* 700 */     for (i = 0; i < dimension.length; i++) {
/* 701 */       if (dimension[i] > 1 && this.multipliers[i] < 0)
/* 702 */         this.adjustedOffset += Math.abs(this.multipliers[i]) * this.dimsLessOne[i]; 
/*     */     } 
/* 708 */     this.dataType = getDataType();
/* 709 */     this.numBands = getNumBands();
/*     */   }
/*     */   
/*     */   public int[] getDimension() {
/* 718 */     return this.dimension;
/*     */   }
/*     */   
/*     */   public int[] getDimsLessOne() {
/* 726 */     return this.dimsLessOne;
/*     */   }
/*     */   
/*     */   public int[] getMultipliers() {
/* 734 */     return this.multipliers;
/*     */   }
/*     */   
/*     */   public int getAdjustedOffset() {
/* 743 */     return this.adjustedOffset;
/*     */   }
/*     */   
/*     */   public int findNearestEntry(float[] pixel) {
/* 759 */     int band, index = -1;
/* 761 */     index = this.adjustedOffset;
/* 763 */     switch (this.dataType) {
/*     */       case 0:
/* 765 */         for (band = 0; band < this.numBands; band++) {
/* 766 */           int tmp = (int)(pixel[band] * this.dimsLessOne[band]);
/* 768 */           if ((tmp & 0xFF) > 127)
/* 769 */             tmp += 256; 
/* 772 */           index += (tmp >> 8) * this.multipliers[band];
/*     */         } 
/* 838 */         return index;
/*     */       case 2:
/*     */         for (band = 0; band < this.numBands; band++) {
/*     */           int tmp = (int)(pixel[band] - -32768.0F) * this.dimsLessOne[band];
/*     */           if ((tmp & 0xFFFF) > 32767)
/*     */             tmp += 65536; 
/*     */           index += (tmp >> 16) * this.multipliers[band];
/*     */         } 
/* 838 */         return index;
/*     */       case 1:
/*     */         for (band = 0; band < this.numBands; band++) {
/*     */           int tmp = (int)(pixel[band] * this.dimsLessOne[band]);
/*     */           if ((tmp & 0xFFFF) > 32767)
/*     */             tmp += 65536; 
/*     */           index += (tmp >> 16) * this.multipliers[band];
/*     */         } 
/* 838 */         return index;
/*     */       case 3:
/*     */         for (band = 0; band < this.numBands; band++) {
/*     */           long tmp = (long)((pixel[band] - -2.1474836E9F) * this.dimsLessOne[band]);
/*     */           if (tmp > 2147483647L)
/*     */             tmp += 0L; 
/*     */           index += (int)(tmp >> 32L) * this.multipliers[band];
/*     */         } 
/* 838 */         return index;
/*     */       case 4:
/*     */         for (band = 0; band < this.numBands; band++) {
/*     */           float ftmp = pixel[band] * this.dimsLessOne[band];
/*     */           int itmp = (int)ftmp;
/*     */           if (ftmp - itmp >= 0.5F)
/*     */             itmp++; 
/*     */           index += itmp * this.multipliers[band];
/*     */         } 
/* 838 */         return index;
/*     */       case 5:
/*     */         for (band = 0; band < this.numBands; band++) {
/*     */           double ftmp = (pixel[band] * this.dimsLessOne[band]);
/*     */           int itmp = (int)ftmp;
/*     */           if (ftmp - itmp >= 0.5D)
/*     */             itmp++; 
/*     */           index += itmp * this.multipliers[band];
/*     */         } 
/* 838 */         return index;
/*     */     } 
/*     */     throw new RuntimeException(JaiI18N.getString("ColorCube6"));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ColorCube.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */