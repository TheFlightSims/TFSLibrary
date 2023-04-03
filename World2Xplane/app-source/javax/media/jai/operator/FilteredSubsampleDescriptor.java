/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class FilteredSubsampleDescriptor extends OperationDescriptorImpl {
/* 376 */   private static final String[][] resources = new String[][] { { "GlobalName", "FilteredSubsample" }, { "LocalName", "FilteredSubsample" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("FilteredSubsampleDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/FilteredSubsampleDescriptor.html" }, { "Version", "1.0" }, { "arg0Desc", "The X subsample factor." }, { "arg1Desc", "The Y subsample factor." }, { "arg2Desc", "Symmetric filter coefficients." }, { "arg3Desc", "Interpolation object." } };
/*     */   
/* 390 */   private static final Class[] paramClasses = new Class[] { Integer.class, Integer.class, (array$F == null) ? (array$F = class$("[F")) : array$F, Interpolation.class };
/*     */   
/* 396 */   private static final String[] paramNames = new String[] { "scaleX", "scaleY", "qsFilterArray", "interpolation" };
/*     */   
/* 401 */   private static final Object[] paramDefaults = new Object[] { new Integer(2), new Integer(2), null, Interpolation.getInstance(0) };
/*     */   
/* 408 */   private static final String[] supportedModes = new String[] { "rendered" };
/*     */   
/*     */   static Class array$F;
/*     */   
/*     */   public FilteredSubsampleDescriptor() {
/* 414 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(String modeName, ParameterBlock args, StringBuffer msg) {
/* 435 */     if (!super.validateParameters(modeName, args, msg))
/* 436 */       return false; 
/* 438 */     int scaleX = args.getIntParameter(0);
/* 439 */     int scaleY = args.getIntParameter(1);
/* 440 */     if (scaleX < 1 || scaleY < 1) {
/* 441 */       msg.append(getName() + " " + JaiI18N.getString("FilteredSubsampleDescriptor1"));
/* 443 */       return false;
/*     */     } 
/* 446 */     float[] filter = (float[])args.getObjectParameter(2);
/* 450 */     if (filter == null) {
/* 451 */       int m = (scaleX > scaleY) ? scaleX : scaleY;
/* 452 */       if ((m & 0x1) == 0)
/* 453 */         m++; 
/* 455 */       double sigma = (m - 1) / 6.0D;
/* 459 */       if (m == 1)
/* 460 */         sigma = 1.0D; 
/* 462 */       filter = new float[m / 2 + 1];
/* 463 */       float sum = 0.0F;
/*     */       int i;
/* 465 */       for (i = 0; i < filter.length; i++) {
/* 466 */         filter[i] = (float)gaussian(i, sigma);
/* 467 */         if (i == 0) {
/* 468 */           sum += filter[i];
/*     */         } else {
/* 470 */           sum += filter[i] * 2.0F;
/*     */         } 
/*     */       } 
/* 473 */       for (i = 0; i < filter.length; i++)
/* 474 */         filter[i] = filter[i] / sum; 
/* 477 */       args.set(filter, 2);
/*     */     } 
/* 480 */     Interpolation interp = (Interpolation)args.getObjectParameter(3);
/* 483 */     if (!(interp instanceof javax.media.jai.InterpolationNearest) && !(interp instanceof javax.media.jai.InterpolationBilinear) && !(interp instanceof javax.media.jai.InterpolationBicubic) && !(interp instanceof javax.media.jai.InterpolationBicubic2)) {
/* 487 */       msg.append(getName() + " " + JaiI18N.getString("FilteredSubsampleDescriptor2"));
/* 489 */       return false;
/*     */     } 
/* 491 */     return true;
/*     */   }
/*     */   
/*     */   private double gaussian(double x, double sigma) {
/* 500 */     return Math.exp(-x * x / 2.0D * sigma * sigma) / sigma / Math.sqrt(6.283185307179586D);
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, Integer scaleX, Integer scaleY, float[] qsFilterArray, Interpolation interpolation, RenderingHints hints) {
/* 536 */     ParameterBlockJAI pb = new ParameterBlockJAI("FilteredSubsample", "rendered");
/* 540 */     pb.setSource("source0", source0);
/* 542 */     pb.setParameter("scaleX", scaleX);
/* 543 */     pb.setParameter("scaleY", scaleY);
/* 544 */     pb.setParameter("qsFilterArray", qsFilterArray);
/* 545 */     pb.setParameter("interpolation", interpolation);
/* 547 */     return JAI.create("FilteredSubsample", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\FilteredSubsampleDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */