/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class HistogramDescriptor extends OperationDescriptorImpl {
/* 114 */   private static final String[][] resources = new String[][] { 
/* 114 */       { "GlobalName", "Histogram" }, { "LocalName", "Histogram" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("HistogramDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/HistogramDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion2") }, { "arg0Desc", JaiI18N.getString("HistogramDescriptor1") }, { "arg1Desc", JaiI18N.getString("HistogramDescriptor2") }, { "arg2Desc", JaiI18N.getString("HistogramDescriptor3") }, { "arg3Desc", JaiI18N.getString("HistogramDescriptor4") }, 
/* 114 */       { "arg4Desc", JaiI18N.getString("HistogramDescriptor5") }, { "arg5Desc", JaiI18N.getString("HistogramDescriptor6") } };
/*     */   
/* 130 */   private static final String[] paramNames = new String[] { "roi", "xPeriod", "yPeriod", "numBins", "lowValue", "highValue" };
/*     */   
/* 140 */   private static final Class[] paramClasses = new Class[] { ROI.class, Integer.class, Integer.class, (array$I == null) ? (array$I = class$("[I")) : array$I, (array$D == null) ? (array$D = class$("[D")) : array$D, (array$D == null) ? (array$D = class$("[D")) : array$D };
/*     */   
/* 150 */   private static final Object[] paramDefaults = new Object[] { null, new Integer(1), new Integer(1), { 256 }, { 0.0D }, { 256.0D } };
/*     */   
/*     */   static Class array$I;
/*     */   
/*     */   static Class array$D;
/*     */   
/*     */   public HistogramDescriptor() {
/* 161 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public Number getParamMinValue(int index) {
/* 169 */     switch (index) {
/*     */       case 1:
/*     */       case 2:
/* 172 */         return new Integer(1);
/*     */       case 0:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/* 177 */         return null;
/*     */     } 
/* 179 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer msg) {
/* 199 */     if (args == null || msg == null)
/* 200 */       throw new IllegalArgumentException(JaiI18N.getString("Generic0")); 
/* 203 */     if (!super.validateParameters(args, msg))
/* 204 */       return false; 
/* 207 */     int[] numBins = (int[])args.getObjectParameter(3);
/* 208 */     double[] lowValue = (double[])args.getObjectParameter(4);
/* 209 */     double[] highValue = (double[])args.getObjectParameter(5);
/* 211 */     int l1 = numBins.length;
/* 212 */     int l2 = lowValue.length;
/* 213 */     int l3 = highValue.length;
/* 215 */     int length = Math.max(l1, Math.max(l2, l3));
/* 217 */     for (int i = 0; i < length; i++) {
/* 218 */       if (i < l1 && numBins[i] <= 0) {
/* 219 */         msg.append(getName() + " " + JaiI18N.getString("HistogramDescriptor7"));
/* 221 */         return false;
/*     */       } 
/* 224 */       double l = (i < l2) ? lowValue[i] : lowValue[0];
/* 225 */       double h = (i < l3) ? highValue[i] : highValue[0];
/* 227 */       if (l >= h) {
/* 228 */         msg.append(getName() + " " + JaiI18N.getString("HistogramDescriptor8"));
/* 230 */         return false;
/*     */       } 
/*     */     } 
/* 234 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, ROI roi, Integer xPeriod, Integer yPeriod, int[] numBins, double[] lowValue, double[] highValue, RenderingHints hints) {
/* 275 */     ParameterBlockJAI pb = new ParameterBlockJAI("Histogram", "rendered");
/* 279 */     pb.setSource("source0", source0);
/* 281 */     pb.setParameter("roi", roi);
/* 282 */     pb.setParameter("xPeriod", xPeriod);
/* 283 */     pb.setParameter("yPeriod", yPeriod);
/* 284 */     pb.setParameter("numBins", numBins);
/* 285 */     pb.setParameter("lowValue", lowValue);
/* 286 */     pb.setParameter("highValue", highValue);
/* 288 */     return JAI.create("Histogram", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\HistogramDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */