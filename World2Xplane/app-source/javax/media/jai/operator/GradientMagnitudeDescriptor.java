/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import com.sun.media.jai.util.AreaOpPropertyGenerator;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.KernelJAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.PropertyGenerator;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class GradientMagnitudeDescriptor extends OperationDescriptorImpl {
/*  93 */   private static final String[][] resources = new String[][] { { "GlobalName", "GradientMagnitude" }, { "LocalName", "GradientMagnitude" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("GradientMagnitudeDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jaiapi/javax.media.jai.operator.GradientMagnitudeDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", "A gradient mask." }, { "arg1Desc", "A gradient mask orthogonal to the first one." } };
/*     */   
/* 105 */   private static final String[] paramNames = new String[] { "mask1", "mask2" };
/*     */   
/* 111 */   private static final Class[] paramClasses = new Class[] { KernelJAI.class, KernelJAI.class };
/*     */   
/* 117 */   private static final Object[] paramDefaults = new Object[] { KernelJAI.GRADIENT_MASK_SOBEL_HORIZONTAL, KernelJAI.GRADIENT_MASK_SOBEL_VERTICAL };
/*     */   
/*     */   public GradientMagnitudeDescriptor() {
/* 124 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer msg) {
/* 136 */     if (!super.validateParameters(args, msg))
/* 137 */       return false; 
/* 140 */     KernelJAI h_kernel = (KernelJAI)args.getObjectParameter(0);
/* 141 */     KernelJAI v_kernel = (KernelJAI)args.getObjectParameter(1);
/* 144 */     if (h_kernel.getWidth() != v_kernel.getWidth() || h_kernel.getHeight() != v_kernel.getHeight()) {
/* 146 */       msg.append(getName() + " " + JaiI18N.getString("GradientMagnitudeDescriptor1"));
/* 148 */       return false;
/*     */     } 
/* 151 */     return true;
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 161 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 162 */     pg[0] = (PropertyGenerator)new AreaOpPropertyGenerator();
/* 163 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, KernelJAI mask1, KernelJAI mask2, RenderingHints hints) {
/* 192 */     ParameterBlockJAI pb = new ParameterBlockJAI("GradientMagnitude", "rendered");
/* 196 */     pb.setSource("source0", source0);
/* 198 */     pb.setParameter("mask1", mask1);
/* 199 */     pb.setParameter("mask2", mask2);
/* 201 */     return JAI.create("GradientMagnitude", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\GradientMagnitudeDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */