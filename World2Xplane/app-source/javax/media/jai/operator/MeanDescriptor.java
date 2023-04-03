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
/*     */ public class MeanDescriptor extends OperationDescriptorImpl {
/*  84 */   private static final String[][] resources = new String[][] { { "GlobalName", "Mean" }, { "LocalName", "Mean" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("MeanDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/MeanDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("MeanDescriptor1") }, { "arg1Desc", JaiI18N.getString("MeanDescriptor2") }, { "arg2Desc", JaiI18N.getString("MeanDescriptor3") } };
/*     */   
/*  97 */   private static final String[] paramNames = new String[] { "roi", "xPeriod", "yPeriod" };
/*     */   
/* 102 */   private static final Class[] paramClasses = new Class[] { ROI.class, Integer.class, Integer.class };
/*     */   
/* 109 */   private static final Object[] paramDefaults = new Object[] { null, new Integer(1), new Integer(1) };
/*     */   
/*     */   public MeanDescriptor() {
/* 115 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public Number getParamMinValue(int index) {
/* 123 */     if (index == 0)
/* 124 */       return null; 
/* 125 */     if (index == 1 || index == 2)
/* 126 */       return new Integer(1); 
/* 128 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, ROI roi, Integer xPeriod, Integer yPeriod, RenderingHints hints) {
/* 161 */     ParameterBlockJAI pb = new ParameterBlockJAI("Mean", "rendered");
/* 165 */     pb.setSource("source0", source0);
/* 167 */     pb.setParameter("roi", roi);
/* 168 */     pb.setParameter("xPeriod", xPeriod);
/* 169 */     pb.setParameter("yPeriod", yPeriod);
/* 171 */     return JAI.create("Mean", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\MeanDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */