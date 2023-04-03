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
/*     */ public class UnsharpMaskDescriptor extends OperationDescriptorImpl {
/*  99 */   private static final String[][] resources = new String[][] { { "GlobalName", "UnsharpMask" }, { "LocalName", "UnsharpMask" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("UnsharpMaskDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/UnsharpMaskDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("UnsharpMaskDescriptor1") }, { "arg1Desc", JaiI18N.getString("UnsharpMaskDescriptor2") } };
/*     */   
/* 111 */   private static final String[] paramNames = new String[] { "kernel", "gain" };
/*     */   
/* 116 */   private static final Class[] paramClasses = new Class[] { KernelJAI.class, Float.class };
/*     */   
/* 122 */   private static final Object[] paramDefaults = new Object[] { new KernelJAI(3, 3, 1, 1, new float[] { 0.11111111F, 0.11111111F, 0.11111111F, 0.11111111F, 0.11111111F, 0.11111111F, 0.11111111F, 0.11111111F, 0.11111111F }), new Float(1.0F) };
/*     */   
/*     */   public UnsharpMaskDescriptor() {
/* 129 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 139 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 140 */     pg[0] = (PropertyGenerator)new AreaOpPropertyGenerator();
/* 141 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, KernelJAI kernel, Float gain, RenderingHints hints) {
/* 170 */     ParameterBlockJAI pb = new ParameterBlockJAI("UnsharpMask", "rendered");
/* 174 */     pb.setSource("source0", source0);
/* 176 */     pb.setParameter("kernel", kernel);
/* 177 */     pb.setParameter("gain", gain);
/* 179 */     return JAI.create("UnsharpMask", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\UnsharpMaskDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */