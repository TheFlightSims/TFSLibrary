/*     */ package com.sun.media.jai.opimage;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.CRIFImpl;
/*     */ import javax.media.jai.Histogram;
/*     */ import javax.media.jai.ImageLayout;
/*     */ 
/*     */ public class MatchCDFCRIF extends CRIFImpl {
/*     */   private static void createHistogramMap(float[] CDFin, float[] CDFout, double lowValue, double binWidth, int numBins, float[] abscissa, float[] ordinate) {
/*  43 */     double x = lowValue;
/*  44 */     int j = 0;
/*  45 */     int jMax = numBins - 1;
/*  48 */     for (int i = 0; i < numBins; i++) {
/*  51 */       float w = CDFin[i];
/*  52 */       for (; CDFout[j] < w && j < jMax; j++);
/*  55 */       abscissa[i] = (float)x;
/*  56 */       ordinate[i] = (float)(j * binWidth);
/*  59 */       x += binWidth;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static float[][][] createSpecificationMap(Histogram histIn, float[][] CDFOut) {
/*  75 */     int numBands = histIn.getNumBands();
/*  76 */     float[][][] bp = new float[numBands][][];
/*  79 */     float[] CDFin = null;
/*  80 */     for (int band = 0; band < numBands; band++) {
/*  82 */       int numBins = histIn.getNumBins(band);
/*  83 */       bp[band] = new float[2][];
/*  84 */       bp[band][0] = new float[numBins];
/*  85 */       bp[band][1] = new float[numBins];
/*  88 */       int[] binsIn = histIn.getBins(band);
/*  89 */       long binTotalIn = binsIn[0];
/*     */       int i;
/*  90 */       for (i = 1; i < numBins; i++)
/*  91 */         binTotalIn += binsIn[i]; 
/*  95 */       if (CDFin == null || CDFin.length < numBins)
/*  96 */         CDFin = new float[numBins]; 
/* 101 */       CDFin[0] = binsIn[0] / (float)binTotalIn;
/* 102 */       for (i = 1; i < numBins; i++)
/* 103 */         CDFin[i] = CDFin[i - 1] + binsIn[i] / (float)binTotalIn; 
/* 107 */       double binWidth = (histIn.getHighValue(band) - histIn.getLowValue(band)) / numBins;
/* 109 */       createHistogramMap(CDFin, (CDFOut.length > 1) ? CDFOut[band] : CDFOut[0], histIn.getLowValue(band), binWidth, numBins, bp[band][0], bp[band][1]);
/*     */     } 
/* 115 */     return bp;
/*     */   }
/*     */   
/*     */   public MatchCDFCRIF() {
/* 120 */     super("matchcdf");
/*     */   }
/*     */   
/*     */   public RenderedImage create(ParameterBlock args, RenderingHints renderHints) {
/* 133 */     ImageLayout layout = RIFUtil.getImageLayoutHint(renderHints);
/* 137 */     RenderedImage src = args.getRenderedSource(0);
/* 138 */     Histogram hist = (Histogram)src.getProperty("histogram");
/* 139 */     float[][] CDF = (float[][])args.getObjectParameter(0);
/* 140 */     float[][][] bp = createSpecificationMap(hist, CDF);
/* 142 */     return (RenderedImage)new PiecewiseOpImage(src, renderHints, layout, bp);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\sun\media\jai\opimage\MatchCDFCRIF.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */