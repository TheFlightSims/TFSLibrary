/*      */ package javax.media.jai;
/*      */ 
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.io.Serializable;
/*      */ import java.util.LinkedList;
/*      */ import java.util.ListIterator;
/*      */ 
/*      */ public class Histogram implements Serializable {
/*      */   private int[] numBins;
/*      */   
/*      */   private double[] lowValue;
/*      */   
/*      */   private double[] highValue;
/*      */   
/*      */   private int numBands;
/*      */   
/*      */   private double[] binWidth;
/*      */   
/*   86 */   private int[][] bins = (int[][])null;
/*      */   
/*   89 */   private int[] totals = null;
/*      */   
/*   92 */   private double[] mean = null;
/*      */   
/*      */   private static final int[] fill(int[] array, int newLength) {
/*  102 */     int[] newArray = null;
/*  104 */     if (array == null || array.length == 0)
/*  105 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  107 */     if (newLength > 0) {
/*  108 */       newArray = new int[newLength];
/*  109 */       int oldLength = array.length;
/*  110 */       for (int i = 0; i < newLength; i++) {
/*  111 */         if (i < oldLength) {
/*  112 */           newArray[i] = array[i];
/*      */         } else {
/*  114 */           newArray[i] = array[0];
/*      */         } 
/*      */       } 
/*      */     } 
/*  118 */     return newArray;
/*      */   }
/*      */   
/*      */   private static final double[] fill(double[] array, int newLength) {
/*  129 */     double[] newArray = null;
/*  131 */     if (array == null || array.length == 0)
/*  132 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  134 */     if (newLength > 0) {
/*  135 */       newArray = new double[newLength];
/*  136 */       int oldLength = array.length;
/*  137 */       for (int i = 0; i < newLength; i++) {
/*  138 */         if (i < oldLength) {
/*  139 */           newArray[i] = array[i];
/*      */         } else {
/*  141 */           newArray[i] = array[0];
/*      */         } 
/*      */       } 
/*      */     } 
/*  145 */     return newArray;
/*      */   }
/*      */   
/*      */   public Histogram(int[] numBins, double[] lowValue, double[] highValue) {
/*  184 */     if (numBins == null || lowValue == null || highValue == null)
/*  185 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  188 */     this.numBands = numBins.length;
/*  190 */     if (lowValue.length != this.numBands || highValue.length != this.numBands)
/*  191 */       throw new IllegalArgumentException(JaiI18N.getString("Histogram0")); 
/*  195 */     if (this.numBands == 0)
/*  196 */       throw new IllegalArgumentException(JaiI18N.getString("Histogram1")); 
/*      */     int i;
/*  200 */     for (i = 0; i < this.numBands; i++) {
/*  201 */       if (numBins[i] <= 0)
/*  202 */         throw new IllegalArgumentException(JaiI18N.getString("Histogram2")); 
/*  206 */       if (lowValue[i] >= highValue[i])
/*  207 */         throw new IllegalArgumentException(JaiI18N.getString("Histogram3")); 
/*      */     } 
/*  212 */     this.numBins = (int[])numBins.clone();
/*  213 */     this.lowValue = (double[])lowValue.clone();
/*  214 */     this.highValue = (double[])highValue.clone();
/*  216 */     this.binWidth = new double[this.numBands];
/*  219 */     for (i = 0; i < this.numBands; i++)
/*  220 */       this.binWidth[i] = (highValue[i] - lowValue[i]) / numBins[i]; 
/*      */   }
/*      */   
/*      */   public Histogram(int[] numBins, double[] lowValue, double[] highValue, int numBands) {
/*  260 */     this(fill(numBins, numBands), fill(lowValue, numBands), fill(highValue, numBands));
/*      */   }
/*      */   
/*      */   public Histogram(int numBins, double lowValue, double highValue, int numBands) {
/*  289 */     if (numBands <= 0)
/*  290 */       throw new IllegalArgumentException(JaiI18N.getString("Histogram1")); 
/*  294 */     if (numBins <= 0)
/*  295 */       throw new IllegalArgumentException(JaiI18N.getString("Histogram2")); 
/*  299 */     if (lowValue >= highValue)
/*  300 */       throw new IllegalArgumentException(JaiI18N.getString("Histogram3")); 
/*  304 */     this.numBands = numBands;
/*  305 */     this.numBins = new int[numBands];
/*  306 */     this.lowValue = new double[numBands];
/*  307 */     this.highValue = new double[numBands];
/*  308 */     this.binWidth = new double[numBands];
/*  310 */     double bw = (highValue - lowValue) / numBins;
/*  312 */     for (int i = 0; i < numBands; i++) {
/*  313 */       this.numBins[i] = numBins;
/*  314 */       this.lowValue[i] = lowValue;
/*  315 */       this.highValue[i] = highValue;
/*  316 */       this.binWidth[i] = bw;
/*      */     } 
/*      */   }
/*      */   
/*      */   public int[] getNumBins() {
/*  322 */     return (int[])this.numBins.clone();
/*      */   }
/*      */   
/*      */   public int getNumBins(int band) {
/*  335 */     return this.numBins[band];
/*      */   }
/*      */   
/*      */   public double[] getLowValue() {
/*  340 */     return (double[])this.lowValue.clone();
/*      */   }
/*      */   
/*      */   public double getLowValue(int band) {
/*  353 */     return this.lowValue[band];
/*      */   }
/*      */   
/*      */   public double[] getHighValue() {
/*  358 */     return (double[])this.highValue.clone();
/*      */   }
/*      */   
/*      */   public double getHighValue(int band) {
/*  371 */     return this.highValue[band];
/*      */   }
/*      */   
/*      */   public int getNumBands() {
/*  380 */     return this.numBands;
/*      */   }
/*      */   
/*      */   public synchronized int[][] getBins() {
/*  389 */     if (this.bins == null) {
/*  390 */       this.bins = new int[this.numBands][];
/*  392 */       for (int i = 0; i < this.numBands; i++)
/*  393 */         this.bins[i] = new int[this.numBins[i]]; 
/*      */     } 
/*  397 */     return this.bins;
/*      */   }
/*      */   
/*      */   public int[] getBins(int band) {
/*  410 */     getBins();
/*  411 */     return this.bins[band];
/*      */   }
/*      */   
/*      */   public int getBinSize(int band, int bin) {
/*  425 */     getBins();
/*  426 */     return this.bins[band][bin];
/*      */   }
/*      */   
/*      */   public double getBinLowValue(int band, int bin) {
/*  441 */     return this.lowValue[band] + bin * this.binWidth[band];
/*      */   }
/*      */   
/*      */   public void clearHistogram() {
/*  449 */     if (this.bins != null)
/*  450 */       synchronized (this.bins) {
/*  451 */         for (int i = 0; i < this.numBands; i++) {
/*  452 */           int[] b = this.bins[i];
/*  453 */           int length = b.length;
/*  455 */           for (int j = 0; j < length; j++)
/*  456 */             b[j] = 0; 
/*      */         } 
/*      */       }  
/*      */   }
/*      */   
/*      */   public int[] getTotals() {
/*  475 */     if (this.totals == null) {
/*  476 */       getBins();
/*  478 */       synchronized (this) {
/*  479 */         this.totals = new int[this.numBands];
/*  481 */         for (int i = 0; i < this.numBands; i++) {
/*  482 */           int[] b = this.bins[i];
/*  483 */           int length = b.length;
/*  484 */           int t = 0;
/*  486 */           for (int j = 0; j < length; j++)
/*  487 */             t += b[j]; 
/*  490 */           this.totals[i] = t;
/*      */         } 
/*      */       } 
/*      */     } 
/*  495 */     return this.totals;
/*      */   }
/*      */   
/*      */   public int getSubTotal(int band, int minBin, int maxBin) {
/*  517 */     if (minBin < 0 || maxBin >= this.numBins[band])
/*  518 */       throw new ArrayIndexOutOfBoundsException(JaiI18N.getString("Histogram5")); 
/*  522 */     if (minBin > maxBin)
/*  523 */       throw new IllegalArgumentException(JaiI18N.getString("Histogram10")); 
/*  527 */     int[] b = getBins(band);
/*  528 */     int total = 0;
/*  530 */     for (int i = minBin; i <= maxBin; i++)
/*  531 */       total += b[i]; 
/*  534 */     return total;
/*      */   }
/*      */   
/*      */   public double[] getMean() {
/*  543 */     if (this.mean == null) {
/*  544 */       getTotals();
/*  546 */       synchronized (this) {
/*  547 */         this.mean = new double[this.numBands];
/*  549 */         for (int i = 0; i < this.numBands; i++) {
/*  550 */           int[] counts = getBins(i);
/*  551 */           int nBins = this.numBins[i];
/*  552 */           double level = getLowValue(i);
/*  553 */           double bw = this.binWidth[i];
/*  555 */           double mu = 0.0D;
/*  556 */           double total = this.totals[i];
/*  558 */           for (int b = 0; b < nBins; b++) {
/*  559 */             mu += counts[b] / total * level;
/*  560 */             level += bw;
/*      */           } 
/*  563 */           this.mean[i] = mu;
/*      */         } 
/*      */       } 
/*      */     } 
/*  568 */     return this.mean;
/*      */   }
/*      */   
/*      */   public void countPixels(Raster raster, ROI roi, int xStart, int yStart, int xPeriod, int yPeriod) {
/*      */     LinkedList rectList;
/*  610 */     if (raster == null)
/*  611 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/*  614 */     SampleModel sampleModel = raster.getSampleModel();
/*  616 */     if (sampleModel.getNumBands() != this.numBands)
/*  617 */       throw new IllegalArgumentException(JaiI18N.getString("Histogram4")); 
/*  621 */     Rectangle bounds = raster.getBounds();
/*  624 */     if (roi == null) {
/*  625 */       rectList = new LinkedList();
/*  626 */       rectList.addLast(bounds);
/*      */     } else {
/*  628 */       rectList = roi.getAsRectangleList(bounds.x, bounds.y, bounds.width, bounds.height);
/*  630 */       if (rectList == null)
/*      */         return; 
/*      */     } 
/*  635 */     PixelAccessor accessor = new PixelAccessor(sampleModel, null);
/*  637 */     ListIterator iterator = rectList.listIterator(0);
/*  639 */     while (iterator.hasNext()) {
/*  640 */       Rectangle r = iterator.next();
/*  641 */       int tx = r.x;
/*  642 */       int ty = r.y;
/*  645 */       r.x = startPosition(tx, xStart, xPeriod);
/*  646 */       r.y = startPosition(ty, yStart, yPeriod);
/*  647 */       r.width = tx + r.width - r.x;
/*  648 */       r.height = ty + r.height - r.y;
/*  650 */       if (r.width <= 0 || r.height <= 0)
/*      */         continue; 
/*  654 */       switch (accessor.sampleType) {
/*      */         case -1:
/*      */         case 0:
/*  657 */           countPixelsByte(accessor, raster, r, xPeriod, yPeriod);
/*      */           continue;
/*      */         case 1:
/*  660 */           countPixelsUShort(accessor, raster, r, xPeriod, yPeriod);
/*      */           continue;
/*      */         case 2:
/*  663 */           countPixelsShort(accessor, raster, r, xPeriod, yPeriod);
/*      */           continue;
/*      */         case 3:
/*  666 */           countPixelsInt(accessor, raster, r, xPeriod, yPeriod);
/*      */           continue;
/*      */         case 4:
/*  669 */           countPixelsFloat(accessor, raster, r, xPeriod, yPeriod);
/*      */           continue;
/*      */         case 5:
/*  672 */           countPixelsDouble(accessor, raster, r, xPeriod, yPeriod);
/*      */           continue;
/*      */       } 
/*  675 */       throw new RuntimeException(JaiI18N.getString("Histogram11"));
/*      */     } 
/*      */   }
/*      */   
/*      */   private void countPixelsByte(PixelAccessor accessor, Raster raster, Rectangle rect, int xPeriod, int yPeriod) {
/*  684 */     UnpackedImageData uid = accessor.getPixels(raster, rect, 0, false);
/*  687 */     byte[][] byteData = uid.getByteData();
/*  688 */     int pixelStride = uid.pixelStride * xPeriod;
/*  689 */     int lineStride = uid.lineStride * yPeriod;
/*  690 */     int[] offsets = uid.bandOffsets;
/*  692 */     for (int b = 0; b < this.numBands; b++) {
/*  693 */       byte[] data = byteData[b];
/*  694 */       int lineOffset = offsets[b];
/*  696 */       int[] bin = new int[this.numBins[b]];
/*  697 */       double low = this.lowValue[b];
/*  698 */       double high = this.highValue[b];
/*  699 */       double bwidth = this.binWidth[b];
/*      */       int h;
/*  701 */       for (h = 0; h < rect.height; h += yPeriod) {
/*  702 */         int pixelOffset = lineOffset;
/*  703 */         lineOffset += lineStride;
/*      */         int w;
/*  705 */         for (w = 0; w < rect.width; w += xPeriod) {
/*  706 */           int d = data[pixelOffset] & 0xFF;
/*  707 */           pixelOffset += pixelStride;
/*  709 */           if (d >= low && d < high) {
/*  710 */             int i = (int)((d - low) / bwidth);
/*  711 */             bin[i] = bin[i] + 1;
/*      */           } 
/*      */         } 
/*      */       } 
/*  716 */       mergeBins(b, bin);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void countPixelsUShort(PixelAccessor accessor, Raster raster, Rectangle rect, int xPeriod, int yPeriod) {
/*  724 */     UnpackedImageData uid = accessor.getPixels(raster, rect, 1, false);
/*  727 */     short[][] shortData = uid.getShortData();
/*  728 */     int pixelStride = uid.pixelStride * xPeriod;
/*  729 */     int lineStride = uid.lineStride * yPeriod;
/*  730 */     int[] offsets = uid.bandOffsets;
/*  732 */     for (int b = 0; b < this.numBands; b++) {
/*  733 */       short[] data = shortData[b];
/*  734 */       int lineOffset = offsets[b];
/*  736 */       int[] bin = new int[this.numBins[b]];
/*  737 */       double low = this.lowValue[b];
/*  738 */       double high = this.highValue[b];
/*  739 */       double bwidth = this.binWidth[b];
/*      */       int h;
/*  741 */       for (h = 0; h < rect.height; h += yPeriod) {
/*  742 */         int pixelOffset = lineOffset;
/*  743 */         lineOffset += lineStride;
/*      */         int w;
/*  745 */         for (w = 0; w < rect.width; w += xPeriod) {
/*  746 */           int d = data[pixelOffset] & 0xFFFF;
/*  747 */           pixelOffset += pixelStride;
/*  749 */           if (d >= low && d < high) {
/*  750 */             int i = (int)((d - low) / bwidth);
/*  751 */             bin[i] = bin[i] + 1;
/*      */           } 
/*      */         } 
/*      */       } 
/*  756 */       mergeBins(b, bin);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void countPixelsShort(PixelAccessor accessor, Raster raster, Rectangle rect, int xPeriod, int yPeriod) {
/*  764 */     UnpackedImageData uid = accessor.getPixels(raster, rect, 2, false);
/*  767 */     short[][] shortData = uid.getShortData();
/*  768 */     int pixelStride = uid.pixelStride * xPeriod;
/*  769 */     int lineStride = uid.lineStride * yPeriod;
/*  770 */     int[] offsets = uid.bandOffsets;
/*  772 */     for (int b = 0; b < this.numBands; b++) {
/*  773 */       short[] data = shortData[b];
/*  774 */       int lineOffset = offsets[b];
/*  776 */       int[] bin = new int[this.numBins[b]];
/*  777 */       double low = this.lowValue[b];
/*  778 */       double high = this.highValue[b];
/*  779 */       double bwidth = this.binWidth[b];
/*      */       int h;
/*  781 */       for (h = 0; h < rect.height; h += yPeriod) {
/*  782 */         int pixelOffset = lineOffset;
/*  783 */         lineOffset += lineStride;
/*      */         int w;
/*  785 */         for (w = 0; w < rect.width; w += xPeriod) {
/*  786 */           int d = data[pixelOffset];
/*  787 */           pixelOffset += pixelStride;
/*  789 */           if (d >= low && d < high) {
/*  790 */             int i = (int)((d - low) / bwidth);
/*  791 */             bin[i] = bin[i] + 1;
/*      */           } 
/*      */         } 
/*      */       } 
/*  796 */       mergeBins(b, bin);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void countPixelsInt(PixelAccessor accessor, Raster raster, Rectangle rect, int xPeriod, int yPeriod) {
/*  804 */     UnpackedImageData uid = accessor.getPixels(raster, rect, 3, false);
/*  807 */     int[][] intData = uid.getIntData();
/*  808 */     int pixelStride = uid.pixelStride * xPeriod;
/*  809 */     int lineStride = uid.lineStride * yPeriod;
/*  810 */     int[] offsets = uid.bandOffsets;
/*  812 */     for (int b = 0; b < this.numBands; b++) {
/*  813 */       int[] data = intData[b];
/*  814 */       int lineOffset = offsets[b];
/*  816 */       int[] bin = new int[this.numBins[b]];
/*  817 */       double low = this.lowValue[b];
/*  818 */       double high = this.highValue[b];
/*  819 */       double bwidth = this.binWidth[b];
/*      */       int h;
/*  821 */       for (h = 0; h < rect.height; h += yPeriod) {
/*  822 */         int pixelOffset = lineOffset;
/*  823 */         lineOffset += lineStride;
/*      */         int w;
/*  825 */         for (w = 0; w < rect.width; w += xPeriod) {
/*  826 */           int d = data[pixelOffset];
/*  827 */           pixelOffset += pixelStride;
/*  829 */           if (d >= low && d < high) {
/*  830 */             int i = (int)((d - low) / bwidth);
/*  831 */             bin[i] = bin[i] + 1;
/*      */           } 
/*      */         } 
/*      */       } 
/*  836 */       mergeBins(b, bin);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void countPixelsFloat(PixelAccessor accessor, Raster raster, Rectangle rect, int xPeriod, int yPeriod) {
/*  844 */     UnpackedImageData uid = accessor.getPixels(raster, rect, 4, false);
/*  847 */     float[][] floatData = uid.getFloatData();
/*  848 */     int pixelStride = uid.pixelStride * xPeriod;
/*  849 */     int lineStride = uid.lineStride * yPeriod;
/*  850 */     int[] offsets = uid.bandOffsets;
/*  852 */     for (int b = 0; b < this.numBands; b++) {
/*  853 */       float[] data = floatData[b];
/*  854 */       int lineOffset = offsets[b];
/*  856 */       int[] bin = new int[this.numBins[b]];
/*  857 */       double low = this.lowValue[b];
/*  858 */       double high = this.highValue[b];
/*  859 */       double bwidth = this.binWidth[b];
/*      */       int h;
/*  861 */       for (h = 0; h < rect.height; h += yPeriod) {
/*  862 */         int pixelOffset = lineOffset;
/*  863 */         lineOffset += lineStride;
/*      */         int w;
/*  865 */         for (w = 0; w < rect.width; w += xPeriod) {
/*  866 */           float d = data[pixelOffset];
/*  867 */           pixelOffset += pixelStride;
/*  869 */           if (d >= low && d < high) {
/*  870 */             int i = (int)((d - low) / bwidth);
/*  871 */             bin[i] = bin[i] + 1;
/*      */           } 
/*      */         } 
/*      */       } 
/*  876 */       mergeBins(b, bin);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void countPixelsDouble(PixelAccessor accessor, Raster raster, Rectangle rect, int xPeriod, int yPeriod) {
/*  884 */     UnpackedImageData uid = accessor.getPixels(raster, rect, 5, false);
/*  887 */     double[][] doubleData = uid.getDoubleData();
/*  888 */     int pixelStride = uid.pixelStride * xPeriod;
/*  889 */     int lineStride = uid.lineStride * yPeriod;
/*  890 */     int[] offsets = uid.bandOffsets;
/*  892 */     for (int b = 0; b < this.numBands; b++) {
/*  893 */       double[] data = doubleData[b];
/*  894 */       int lineOffset = offsets[b];
/*  896 */       int[] bin = new int[this.numBins[b]];
/*  897 */       double low = this.lowValue[b];
/*  898 */       double high = this.highValue[b];
/*  899 */       double bwidth = this.binWidth[b];
/*      */       int h;
/*  901 */       for (h = 0; h < rect.height; h += yPeriod) {
/*  902 */         int pixelOffset = lineOffset;
/*  903 */         lineOffset += lineStride;
/*      */         int w;
/*  905 */         for (w = 0; w < rect.width; w += xPeriod) {
/*  906 */           double d = data[pixelOffset];
/*  907 */           pixelOffset += pixelStride;
/*  909 */           if (d >= low && d < high) {
/*  910 */             int i = (int)((d - low) / bwidth);
/*  911 */             bin[i] = bin[i] + 1;
/*      */           } 
/*      */         } 
/*      */       } 
/*  916 */       mergeBins(b, bin);
/*      */     } 
/*      */   }
/*      */   
/*      */   private int startPosition(int pos, int start, int Period) {
/*  922 */     int t = (pos - start) % Period;
/*  923 */     return (t == 0) ? pos : (pos + Period - t);
/*      */   }
/*      */   
/*      */   private void mergeBins(int band, int[] bin) {
/*  928 */     getBins();
/*  930 */     synchronized (this.bins) {
/*  931 */       int[] b = this.bins[band];
/*  932 */       int length = b.length;
/*  934 */       for (int i = 0; i < length; i++)
/*  935 */         b[i] = b[i] + bin[i]; 
/*      */     } 
/*      */   }
/*      */   
/*      */   public double[] getMoment(int moment, boolean isAbsolute, boolean isCentral) {
/*  964 */     if (moment < 1)
/*  965 */       throw new IllegalArgumentException(JaiI18N.getString("Histogram6")); 
/*  970 */     if ((moment == 1 || isCentral) && this.mean == null)
/*  971 */       getMean(); 
/*  975 */     if (moment == 1 && !isAbsolute && !isCentral)
/*  976 */       return this.mean; 
/*  979 */     double[] moments = new double[this.numBands];
/*  982 */     if (moment == 1 && isCentral) {
/*  983 */       for (int band = 0; band < this.numBands; band++)
/*  984 */         moments[band] = 0.0D; 
/*      */     } else {
/*  988 */       getTotals();
/*  990 */       for (int band = 0; band < this.numBands; band++) {
/*  992 */         int[] counts = getBins(band);
/*  993 */         int nBins = this.numBins[band];
/*  994 */         double level = getLowValue(band);
/*  995 */         double bw = this.binWidth[band];
/*  996 */         double total = this.totals[band];
/*  999 */         double mmt = 0.0D;
/* 1001 */         if (isCentral) {
/* 1003 */           double mu = this.mean[band];
/* 1005 */           if (isAbsolute && moment % 2 == 0) {
/* 1007 */             for (int b = 0; b < nBins; b++) {
/* 1008 */               mmt += Math.pow(level - mu, moment) * counts[b] / total;
/* 1010 */               level += bw;
/*      */             } 
/*      */           } else {
/* 1014 */             for (int b = 0; b < nBins; b++) {
/* 1015 */               mmt += Math.abs(Math.pow(level - mu, moment)) * counts[b] / total;
/* 1017 */               level += bw;
/*      */             } 
/*      */           } 
/* 1020 */         } else if (isAbsolute && moment % 2 != 0) {
/* 1022 */           for (int b = 0; b < nBins; b++) {
/* 1023 */             mmt += Math.abs(Math.pow(level, moment)) * counts[b] / total;
/* 1025 */             level += bw;
/*      */           } 
/*      */         } else {
/* 1029 */           for (int b = 0; b < nBins; b++) {
/* 1030 */             mmt += Math.pow(level, moment) * counts[b] / total;
/* 1031 */             level += bw;
/*      */           } 
/*      */         } 
/* 1036 */         moments[band] = mmt;
/*      */       } 
/*      */     } 
/* 1040 */     return moments;
/*      */   }
/*      */   
/*      */   public double[] getStandardDeviation() {
/* 1054 */     getMean();
/* 1056 */     double[] variance = getMoment(2, false, false);
/* 1058 */     double[] stdev = new double[this.numBands];
/* 1060 */     for (int i = 0; i < variance.length; i++)
/* 1061 */       stdev[i] = Math.sqrt(variance[i] - this.mean[i] * this.mean[i]); 
/* 1064 */     return stdev;
/*      */   }
/*      */   
/*      */   public double[] getEntropy() {
/* 1080 */     getTotals();
/* 1082 */     double log2 = Math.log(2.0D);
/* 1084 */     double[] entropy = new double[this.numBands];
/* 1086 */     for (int band = 0; band < this.numBands; band++) {
/* 1087 */       int[] counts = getBins(band);
/* 1088 */       int nBins = this.numBins[band];
/* 1089 */       double total = this.totals[band];
/* 1091 */       double H = 0.0D;
/* 1093 */       for (int b = 0; b < nBins; b++) {
/* 1094 */         double p = counts[b] / total;
/* 1095 */         if (p != 0.0D)
/* 1096 */           H -= p * Math.log(p) / log2; 
/*      */       } 
/* 1100 */       entropy[band] = H;
/*      */     } 
/* 1103 */     return entropy;
/*      */   }
/*      */   
/*      */   public Histogram getSmoothed(boolean isWeighted, int k) {
/* 1129 */     if (k < 0)
/* 1130 */       throw new IllegalArgumentException(JaiI18N.getString("Histogram7")); 
/* 1131 */     if (k == 0)
/* 1132 */       return this; 
/* 1136 */     Histogram smoothedHistogram = new Histogram(getNumBins(), getLowValue(), getHighValue());
/* 1140 */     int[][] smoothedBins = smoothedHistogram.getBins();
/* 1143 */     getTotals();
/* 1146 */     double[] weights = null;
/* 1147 */     if (isWeighted) {
/* 1148 */       int numWeights = 2 * k + 1;
/* 1149 */       double denom = (numWeights * numWeights);
/* 1150 */       weights = new double[numWeights];
/*      */       int i;
/* 1151 */       for (i = 0; i <= k; i++)
/* 1152 */         weights[i] = (i + 1) / denom; 
/* 1154 */       for (i = k + 1; i < numWeights; i++)
/* 1155 */         weights[i] = weights[numWeights - 1 - i]; 
/*      */     } 
/* 1159 */     for (int band = 0; band < this.numBands; band++) {
/* 1161 */       int[] counts = getBins(band);
/* 1162 */       int[] smoothedCounts = smoothedBins[band];
/* 1163 */       int nBins = smoothedHistogram.getNumBins(band);
/* 1166 */       int sum = 0;
/* 1168 */       if (isWeighted) {
/* 1169 */         for (int i = 0; i < nBins; i++) {
/* 1171 */           int min = Math.max(i - k, 0);
/* 1172 */           int max = Math.min(i + k, nBins);
/* 1175 */           int offset = (k > i) ? (k - i) : 0;
/* 1178 */           double acc = 0.0D;
/* 1179 */           double weightTotal = 0.0D;
/* 1180 */           for (int j = min; j < max; j++) {
/* 1181 */             double w = weights[offset++];
/* 1182 */             acc += counts[j] * w;
/* 1183 */             weightTotal += w;
/*      */           } 
/* 1187 */           smoothedCounts[i] = (int)(acc / weightTotal + 0.5D);
/* 1190 */           sum += smoothedCounts[i];
/*      */         } 
/*      */       } else {
/* 1193 */         for (int i = 0; i < nBins; i++) {
/* 1195 */           int min = Math.max(i - k, 0);
/* 1196 */           int max = Math.min(i + k, nBins);
/* 1199 */           int acc = 0;
/* 1200 */           for (int j = min; j < max; j++)
/* 1201 */             acc += counts[j]; 
/* 1205 */           smoothedCounts[i] = (int)(acc / (max - min + 1) + 0.5D);
/* 1209 */           sum += smoothedCounts[i];
/*      */         } 
/*      */       } 
/* 1215 */       double factor = this.totals[band] / sum;
/* 1216 */       for (int b = 0; b < nBins; b++)
/* 1217 */         smoothedCounts[b] = (int)(smoothedCounts[b] * factor + 0.5D); 
/*      */     } 
/* 1221 */     return smoothedHistogram;
/*      */   }
/*      */   
/*      */   public Histogram getGaussianSmoothed(double standardDeviation) {
/* 1240 */     if (standardDeviation < 0.0D)
/* 1241 */       throw new IllegalArgumentException(JaiI18N.getString("Histogram8")); 
/* 1242 */     if (standardDeviation == 0.0D)
/* 1243 */       return this; 
/* 1247 */     Histogram smoothedHistogram = new Histogram(getNumBins(), getLowValue(), getHighValue());
/* 1251 */     int[][] smoothedBins = smoothedHistogram.getBins();
/* 1254 */     getTotals();
/* 1257 */     int numWeights = (int)(5.16D * standardDeviation + 0.5D);
/* 1258 */     if (numWeights % 2 == 0)
/* 1259 */       numWeights++; 
/* 1263 */     double[] weights = new double[numWeights];
/* 1264 */     int m = numWeights / 2;
/* 1265 */     double var = standardDeviation * standardDeviation;
/* 1266 */     double gain = 1.0D / Math.sqrt(6.283185307179586D * var);
/* 1267 */     double exp = -1.0D / 2.0D * var;
/* 1268 */     for (int i = m; i < numWeights; i++) {
/* 1269 */       double del = (i - m);
/* 1270 */       weights[numWeights - 1 - i] = gain * Math.exp(exp * del * del);
/* 1270 */       weights[i] = gain * Math.exp(exp * del * del);
/*      */     } 
/* 1273 */     for (int band = 0; band < this.numBands; band++) {
/* 1275 */       int[] counts = getBins(band);
/* 1276 */       int[] smoothedCounts = smoothedBins[band];
/* 1277 */       int nBins = smoothedHistogram.getNumBins(band);
/* 1280 */       int sum = 0;
/* 1282 */       for (int b = 0; b < nBins; b++) {
/* 1284 */         int min = Math.max(b - m, 0);
/* 1285 */         int max = Math.min(b + m, nBins);
/* 1288 */         int offset = (m > b) ? (m - b) : 0;
/* 1291 */         double acc = 0.0D;
/* 1292 */         double weightTotal = 0.0D;
/* 1293 */         for (int k = min; k < max; k++) {
/* 1294 */           double w = weights[offset++];
/* 1295 */           acc += counts[k] * w;
/* 1296 */           weightTotal += w;
/*      */         } 
/* 1300 */         smoothedCounts[b] = (int)(acc / weightTotal + 0.5D);
/* 1303 */         sum += smoothedCounts[b];
/*      */       } 
/* 1308 */       double factor = this.totals[band] / sum;
/* 1309 */       for (int j = 0; j < nBins; j++)
/* 1310 */         smoothedCounts[j] = (int)(smoothedCounts[j] * factor + 0.5D); 
/*      */     } 
/* 1314 */     return smoothedHistogram;
/*      */   }
/*      */   
/*      */   public double[] getPTileThreshold(double p) {
/* 1331 */     if (p <= 0.0D || p >= 1.0D)
/* 1332 */       throw new IllegalArgumentException(JaiI18N.getString("Histogram9")); 
/* 1335 */     double[] thresholds = new double[this.numBands];
/* 1336 */     getTotals();
/* 1338 */     for (int band = 0; band < this.numBands; band++) {
/* 1340 */       int nBins = this.numBins[band];
/* 1341 */       int[] counts = getBins(band);
/* 1344 */       int totalCount = this.totals[band];
/* 1348 */       int numBinWidths = 0;
/* 1349 */       int count = counts[0];
/* 1350 */       int idx = 0;
/* 1351 */       while (count / totalCount < p) {
/* 1352 */         numBinWidths++;
/* 1353 */         count += counts[++idx];
/*      */       } 
/* 1357 */       thresholds[band] = getLowValue(band) + numBinWidths * this.binWidth[band];
/*      */     } 
/* 1360 */     return thresholds;
/*      */   }
/*      */   
/*      */   public double[] getModeThreshold(double power) {
/* 1378 */     double[] thresholds = new double[this.numBands];
/* 1379 */     getTotals();
/* 1381 */     for (int band = 0; band < this.numBands; band++) {
/* 1383 */       int nBins = this.numBins[band];
/* 1384 */       int[] counts = getBins(band);
/* 1387 */       int mode1 = 0;
/* 1388 */       int mode1Count = counts[0];
/* 1389 */       for (int b = 1; b < nBins; b++) {
/* 1390 */         if (counts[b] > mode1Count) {
/* 1391 */           mode1 = b;
/* 1392 */           mode1Count = counts[b];
/*      */         } 
/*      */       } 
/* 1397 */       int mode2 = -1;
/* 1398 */       double mode2count = 0.0D;
/* 1399 */       for (int i = 0; i < nBins; i++) {
/* 1400 */         double d = counts[i] * Math.pow(Math.abs(i - mode1), power);
/* 1401 */         if (d > mode2count) {
/* 1402 */           mode2 = i;
/* 1403 */           mode2count = d;
/*      */         } 
/*      */       } 
/* 1408 */       int min = mode1;
/* 1409 */       int minCount = counts[mode1];
/* 1410 */       for (int j = mode1 + 1; j <= mode2; j++) {
/* 1411 */         if (counts[j] < minCount) {
/* 1412 */           min = j;
/* 1413 */           minCount = counts[j];
/*      */         } 
/*      */       } 
/* 1417 */       thresholds[band] = (int)((mode1 + mode2) / 2.0D + 0.5D);
/*      */     } 
/* 1420 */     return thresholds;
/*      */   }
/*      */   
/*      */   public double[] getIterativeThreshold() {
/* 1437 */     double[] thresholds = new double[this.numBands];
/* 1438 */     getTotals();
/* 1440 */     for (int band = 0; band < this.numBands; band++) {
/* 1442 */       int nBins = this.numBins[band];
/* 1443 */       int[] counts = getBins(band);
/* 1444 */       double bw = this.binWidth[band];
/* 1447 */       double threshold = 0.5D * (getLowValue(band) + getHighValue(band));
/* 1448 */       double mid1 = 0.5D * (getLowValue(band) + threshold);
/* 1449 */       double mid2 = 0.5D * (threshold + getHighValue(band));
/* 1452 */       if (this.totals[band] != 0) {
/* 1455 */         int countDown = 1000;
/*      */         do {
/* 1458 */           thresholds[band] = threshold;
/* 1461 */           double total = this.totals[band];
/* 1464 */           double level = getLowValue(band);
/* 1467 */           double mean1 = 0.0D;
/* 1468 */           double mean2 = 0.0D;
/* 1471 */           int count1 = 0;
/* 1474 */           for (int b = 0; b < nBins; b++) {
/* 1476 */             if (level <= threshold) {
/* 1477 */               int c = counts[b];
/* 1478 */               mean1 += c * level;
/* 1479 */               count1 += c;
/*      */             } else {
/* 1481 */               mean2 += counts[b] * level;
/*      */             } 
/* 1485 */             level += bw;
/*      */           } 
/* 1489 */           if (count1 != 0) {
/* 1490 */             mean1 /= count1;
/*      */           } else {
/* 1493 */             mean1 = mid1;
/*      */           } 
/* 1495 */           if (total != count1) {
/* 1496 */             mean2 /= total - count1;
/*      */           } else {
/* 1499 */             mean2 = mid2;
/*      */           } 
/* 1503 */           threshold = 0.5D * (mean1 + mean2);
/* 1505 */         } while (Math.abs(threshold - thresholds[band]) > 1.0E-6D && --countDown > 0);
/*      */       } else {
/* 1508 */         thresholds[band] = threshold;
/*      */       } 
/*      */     } 
/* 1512 */     return thresholds;
/*      */   }
/*      */   
/*      */   public double[] getMaxVarianceThreshold() {
/* 1524 */     double[] thresholds = new double[this.numBands];
/* 1525 */     getTotals();
/* 1526 */     getMean();
/* 1527 */     double[] variance = getMoment(2, false, false);
/* 1529 */     for (int band = 0; band < this.numBands; band++) {
/* 1531 */       int nBins = this.numBins[band];
/* 1532 */       int[] counts = getBins(band);
/* 1533 */       double total = this.totals[band];
/* 1534 */       double mBand = this.mean[band];
/* 1535 */       double bw = this.binWidth[band];
/* 1537 */       double prob0 = 0.0D;
/* 1538 */       double mean0 = 0.0D;
/* 1539 */       double lv = getLowValue(band);
/* 1540 */       double level = lv;
/* 1541 */       double maxRatio = -1.7976931348623157E308D;
/* 1542 */       int maxIndex = 0;
/* 1543 */       int runLength = 0;
/* 1545 */       for (int t = 0; t < nBins; t++, level += bw) {
/* 1546 */         double p = counts[t] / total;
/* 1547 */         prob0 += p;
/* 1548 */         if (prob0 != 0.0D) {
/* 1552 */           double m0 = (mean0 += p * level) / prob0;
/* 1554 */           double prob1 = 1.0D - prob0;
/* 1556 */           if (prob1 != 0.0D) {
/* 1560 */             double m1 = (mBand - mean0) / prob1;
/* 1562 */             double var0 = 0.0D;
/* 1563 */             double g = lv;
/* 1564 */             for (int b = 0; b <= t; b++, g += bw) {
/* 1565 */               double del = g - m0;
/* 1566 */               var0 += del * del * counts[b];
/*      */             } 
/* 1568 */             var0 /= total;
/* 1570 */             double var1 = 0.0D;
/* 1571 */             for (int i = t + 1; i < nBins; i++, g += bw) {
/* 1572 */               double del = g - m1;
/* 1573 */               var1 += del * del * counts[i];
/*      */             } 
/* 1575 */             var1 /= total;
/* 1577 */             if (var0 == 0.0D && var1 == 0.0D && m1 != 0.0D) {
/* 1578 */               maxIndex = (int)(((m0 + m1) / 2.0D - getLowValue(band)) / bw + 0.5D);
/* 1580 */               runLength = 0;
/*      */               break;
/*      */             } 
/* 1584 */             if (var0 / prob0 >= 0.5D && var1 / prob1 >= 0.5D) {
/* 1588 */               double mdel = m0 - m1;
/* 1589 */               double ratio = prob0 * prob1 * mdel * mdel / (var0 + var1);
/* 1591 */               if (ratio > maxRatio) {
/* 1592 */                 maxRatio = ratio;
/* 1593 */                 maxIndex = t;
/* 1594 */                 runLength = 0;
/* 1595 */               } else if (ratio == maxRatio) {
/* 1596 */                 runLength++;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 1600 */       thresholds[band] = getLowValue(band) + (maxIndex + runLength / 2.0D + 0.5D) * bw;
/*      */     } 
/* 1604 */     return thresholds;
/*      */   }
/*      */   
/*      */   public double[] getMaxEntropyThreshold() {
/* 1622 */     double[] thresholds = new double[this.numBands];
/* 1623 */     getTotals();
/* 1625 */     double[] entropy = getEntropy();
/* 1627 */     double log2 = Math.log(2.0D);
/* 1629 */     for (int band = 0; band < this.numBands; band++) {
/* 1631 */       int nBins = this.numBins[band];
/* 1632 */       int[] counts = getBins(band);
/* 1633 */       double total = this.totals[band];
/* 1634 */       double H = entropy[band];
/* 1636 */       double P1 = 0.0D;
/* 1637 */       double H1 = 0.0D;
/* 1639 */       double maxCriterion = -1.7976931348623157E308D;
/* 1640 */       int maxIndex = 0;
/* 1641 */       int runLength = 0;
/* 1643 */       for (int t = 0; t < nBins; t++) {
/* 1644 */         double p = counts[t] / total;
/* 1646 */         if (p != 0.0D) {
/* 1650 */           P1 += p;
/* 1652 */           H1 -= p * Math.log(p) / log2;
/* 1654 */           double max1 = 0.0D;
/* 1655 */           for (int b = 0; b <= t; b++) {
/* 1656 */             if (counts[b] > max1)
/* 1657 */               max1 = counts[b]; 
/*      */           } 
/* 1661 */           if (max1 != 0.0D) {
/* 1665 */             double max2 = 0.0D;
/* 1666 */             for (int i = t + 1; i < nBins; i++) {
/* 1667 */               if (counts[i] > max2)
/* 1668 */                 max2 = counts[i]; 
/*      */             } 
/* 1672 */             if (max2 != 0.0D) {
/* 1676 */               double ratio = H1 / H;
/* 1678 */               double criterion = ratio * Math.log(P1) / Math.log(max1 / total) + (1.0D - ratio) * Math.log(1.0D - P1) / Math.log(max2 / total);
/* 1682 */               if (criterion > maxCriterion) {
/* 1683 */                 maxCriterion = criterion;
/* 1684 */                 maxIndex = t;
/* 1685 */                 runLength = 0;
/* 1686 */               } else if (criterion == maxCriterion) {
/* 1687 */                 runLength++;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 1691 */       thresholds[band] = getLowValue(band) + (maxIndex + runLength / 2.0D + 0.5D) * this.binWidth[band];
/*      */     } 
/* 1695 */     return thresholds;
/*      */   }
/*      */   
/*      */   public double[] getMinErrorThreshold() {
/* 1712 */     double[] thresholds = new double[this.numBands];
/* 1713 */     getTotals();
/* 1714 */     getMean();
/* 1716 */     for (int band = 0; band < this.numBands; band++) {
/* 1718 */       int nBins = this.numBins[band];
/* 1719 */       int[] counts = getBins(band);
/* 1720 */       double total = this.totals[band];
/* 1721 */       double lv = getLowValue(band);
/* 1722 */       double bw = this.binWidth[band];
/* 1724 */       int total1 = 0;
/* 1725 */       int total2 = this.totals[band];
/* 1726 */       double sum1 = 0.0D;
/* 1727 */       double sum2 = this.mean[band] * total;
/* 1729 */       double level = lv;
/* 1731 */       double minCriterion = Double.MAX_VALUE;
/* 1732 */       int minIndex = 0;
/* 1733 */       int runLength = 0;
/* 1735 */       double J0 = Double.MAX_VALUE;
/* 1736 */       double J1 = Double.MAX_VALUE;
/* 1737 */       double J2 = Double.MAX_VALUE;
/* 1738 */       int Jcount = 0;
/* 1740 */       for (int t = 0; t < nBins; t++, level += bw) {
/* 1741 */         int c = counts[t];
/* 1743 */         total1 += c;
/* 1744 */         total2 -= c;
/* 1746 */         double incr = level * c;
/* 1747 */         sum1 += incr;
/* 1748 */         sum2 -= incr;
/* 1750 */         if (total1 != 0 && sum1 != 0.0D) {
/* 1752 */           if (total2 == 0 || sum2 == 0.0D)
/*      */             break; 
/* 1756 */           double m1 = sum1 / total1;
/* 1757 */           double m2 = sum2 / total2;
/* 1759 */           double s1 = 0.0D;
/* 1760 */           double g = lv;
/* 1761 */           for (int b = 0; b <= t; b++, g += bw) {
/* 1762 */             double v = g - m1;
/* 1763 */             s1 += counts[b] * v * v;
/*      */           } 
/* 1765 */           s1 /= total1;
/* 1767 */           if (s1 >= 0.5D) {
/* 1771 */             double s2 = 0.0D;
/* 1773 */             for (int i = t + 1; i < nBins; i++, g += bw) {
/* 1774 */               double v = g - m2;
/* 1775 */               s2 += counts[i] * v * v;
/*      */             } 
/* 1777 */             s2 /= total2;
/* 1779 */             if (s2 >= 0.5D) {
/* 1783 */               double P1 = total1 / total;
/* 1784 */               double P2 = total2 / total;
/* 1785 */               double J = 1.0D + P1 * Math.log(s1) + P2 * Math.log(s2) - 2.0D * (P1 * Math.log(P1) + P2 * Math.log(P2));
/* 1790 */               Jcount++;
/* 1792 */               J0 = J1;
/* 1793 */               J1 = J2;
/* 1794 */               J2 = J;
/* 1796 */               if (Jcount >= 3 && 
/* 1797 */                 J1 <= J0 && J1 <= J2)
/* 1798 */                 if (J1 < minCriterion) {
/* 1799 */                   minCriterion = J1;
/* 1800 */                   minIndex = t - 1;
/* 1801 */                   runLength = 0;
/* 1802 */                 } else if (J1 == minCriterion) {
/* 1803 */                   runLength++;
/*      */                 }  
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/* 1809 */       thresholds[band] = (minIndex == 0) ? this.mean[band] : (getLowValue(band) + (minIndex + runLength / 2.0D + 0.5D) * bw);
/*      */     } 
/* 1814 */     return thresholds;
/*      */   }
/*      */   
/*      */   public double[] getMinFuzzinessThreshold() {
/* 1823 */     double[] thresholds = new double[this.numBands];
/* 1824 */     getTotals();
/* 1825 */     getMean();
/* 1827 */     for (int band = 0; band < this.numBands; band++) {
/* 1829 */       int nBins = this.numBins[band];
/* 1830 */       int[] counts = getBins(band);
/* 1831 */       double total = this.totals[band];
/* 1833 */       double bw = this.binWidth[band];
/* 1835 */       int total1 = 0;
/* 1836 */       int total2 = this.totals[band];
/* 1837 */       double sum1 = 0.0D;
/* 1838 */       double sum2 = this.mean[band] * total;
/* 1840 */       double lv = getLowValue(band);
/* 1841 */       double level = lv;
/* 1842 */       double C = getHighValue(band) - lv;
/* 1844 */       double minCriterion = Double.MAX_VALUE;
/* 1845 */       int minIndex = 0;
/* 1846 */       int runLength = 0;
/* 1848 */       for (int t = 0; t < nBins; t++, level += bw) {
/* 1849 */         int c = counts[t];
/* 1851 */         total1 += c;
/* 1852 */         total2 -= c;
/* 1854 */         double incr = level * c;
/* 1855 */         sum1 += incr;
/* 1856 */         sum2 -= incr;
/* 1858 */         if (total1 != 0 && total2 != 0) {
/* 1862 */           double m1 = sum1 / total1;
/* 1863 */           double m2 = sum2 / total2;
/* 1865 */           double g = lv;
/* 1866 */           double E = 0.0D;
/* 1867 */           for (int b = 0; b < nBins; b++, g += bw) {
/* 1868 */             double u = (b <= t) ? (1.0D / (1.0D + Math.abs(g - m1) / C)) : (1.0D / (1.0D + Math.abs(g - m2) / C));
/* 1871 */             double v = 1.0D - u;
/* 1872 */             E += (-u * Math.log(u) - v * Math.log(v)) * counts[b] / total;
/*      */           } 
/* 1875 */           if (E < minCriterion) {
/* 1876 */             minCriterion = E;
/* 1877 */             minIndex = t;
/* 1878 */             runLength = 0;
/* 1879 */           } else if (E == minCriterion) {
/* 1880 */             runLength++;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1884 */       thresholds[band] = lv + (minIndex + runLength / 2.0D + 0.5D) * bw;
/*      */     } 
/* 1887 */     return thresholds;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\Histogram.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */