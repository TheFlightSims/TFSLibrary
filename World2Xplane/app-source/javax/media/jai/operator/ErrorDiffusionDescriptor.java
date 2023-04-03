/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.KernelJAI;
/*     */ import javax.media.jai.LookupTableJAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class ErrorDiffusionDescriptor extends OperationDescriptorImpl {
/*  70 */   private static final String[][] resources = new String[][] { { "GlobalName", "ErrorDiffusion" }, { "LocalName", "ErrorDiffusion" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("ErrorDiffusionDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/ErrorDiffusionDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("ErrorDiffusionDescriptor1") }, { "arg1Desc", JaiI18N.getString("ErrorDiffusionDescriptor2") } };
/*     */   
/*  82 */   private static final String[] paramNames = new String[] { "colorMap", "errorKernel" };
/*     */   
/*  87 */   private static final Class[] paramClasses = new Class[] { LookupTableJAI.class, KernelJAI.class };
/*     */   
/*  93 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT, KernelJAI.ERROR_FILTER_FLOYD_STEINBERG };
/*     */   
/*     */   public ErrorDiffusionDescriptor() {
/* 101 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, LookupTableJAI colorMap, KernelJAI errorKernel, RenderingHints hints) {
/* 130 */     ParameterBlockJAI pb = new ParameterBlockJAI("ErrorDiffusion", "rendered");
/* 134 */     pb.setSource("source0", source0);
/* 136 */     pb.setParameter("colorMap", colorMap);
/* 137 */     pb.setParameter("errorKernel", errorKernel);
/* 139 */     return JAI.create("ErrorDiffusion", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\ErrorDiffusionDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */