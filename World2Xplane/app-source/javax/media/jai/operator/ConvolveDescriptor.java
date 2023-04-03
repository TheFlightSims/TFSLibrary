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
/*     */ public class ConvolveDescriptor extends OperationDescriptorImpl {
/* 109 */   private static final String[][] resources = new String[][] { { "GlobalName", "Convolve" }, { "LocalName", "Convolve" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("ConvolveDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/ConvolveDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("ConvolveDescriptor1") } };
/*     */   
/* 120 */   private static final String[] paramNames = new String[] { "kernel" };
/*     */   
/* 125 */   private static final Class[] paramClasses = new Class[] { KernelJAI.class };
/*     */   
/* 130 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT };
/*     */   
/*     */   public ConvolveDescriptor() {
/* 136 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 146 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 147 */     pg[0] = (PropertyGenerator)new AreaOpPropertyGenerator();
/* 148 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, KernelJAI kernel, RenderingHints hints) {
/* 174 */     ParameterBlockJAI pb = new ParameterBlockJAI("Convolve", "rendered");
/* 178 */     pb.setSource("source0", source0);
/* 180 */     pb.setParameter("kernel", kernel);
/* 182 */     return JAI.create("Convolve", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\ConvolveDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */