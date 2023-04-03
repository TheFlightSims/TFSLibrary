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
/*     */ public class DilateDescriptor extends OperationDescriptorImpl {
/* 181 */   private static final String[][] resources = new String[][] { { "GlobalName", "Dilate" }, { "LocalName", "Dilate" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("DilateDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jaiapi/<br>javax.media.jai.operator.DilateDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("DilateDescriptor1") } };
/*     */   
/* 192 */   private static final String[] paramNames = new String[] { "kernel" };
/*     */   
/* 197 */   private static final Class[] paramClasses = new Class[] { KernelJAI.class };
/*     */   
/* 202 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT };
/*     */   
/*     */   public DilateDescriptor() {
/* 208 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 218 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 219 */     pg[0] = (PropertyGenerator)new AreaOpPropertyGenerator();
/* 220 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, KernelJAI kernel, RenderingHints hints) {
/* 246 */     ParameterBlockJAI pb = new ParameterBlockJAI("Dilate", "rendered");
/* 250 */     pb.setSource("source0", source0);
/* 252 */     pb.setParameter("kernel", kernel);
/* 254 */     return JAI.create("Dilate", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\DilateDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */