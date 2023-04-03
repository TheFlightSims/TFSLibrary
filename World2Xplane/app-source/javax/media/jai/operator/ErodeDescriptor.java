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
/*     */ public class ErodeDescriptor extends OperationDescriptorImpl {
/* 175 */   private static final String[][] resources = new String[][] { { "GlobalName", "Erode" }, { "LocalName", "Erode" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("ErodeDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jaiapi/<br>javax.media.jai.operator.ErodeDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("ErodeDescriptor1") } };
/*     */   
/* 186 */   private static final String[] paramNames = new String[] { "kernel" };
/*     */   
/* 191 */   private static final Class[] paramClasses = new Class[] { KernelJAI.class };
/*     */   
/* 196 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT };
/*     */   
/*     */   public ErodeDescriptor() {
/* 202 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 212 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 213 */     pg[0] = (PropertyGenerator)new AreaOpPropertyGenerator();
/* 214 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, KernelJAI kernel, RenderingHints hints) {
/* 240 */     ParameterBlockJAI pb = new ParameterBlockJAI("Erode", "rendered");
/* 244 */     pb.setSource("source0", source0);
/* 246 */     pb.setParameter("kernel", kernel);
/* 248 */     return JAI.create("Erode", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\ErodeDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */