/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class AWTImageDescriptor extends OperationDescriptorImpl {
/*  61 */   private static final String[][] resources = new String[][] { { "GlobalName", "AWTImage" }, { "LocalName", "AWTImage" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("AWTImageDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/AWTImageDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("AWTImageDescriptor1") } };
/*     */   
/*  72 */   private static final Class[] paramClasses = new Class[] { Image.class };
/*     */   
/*  77 */   private static final String[] paramNames = new String[] { "awtImage" };
/*     */   
/*  82 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT };
/*     */   
/*     */   public AWTImageDescriptor() {
/*  88 */     super(resources, 0, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public static RenderedOp create(Image awtImage, RenderingHints hints) {
/* 111 */     ParameterBlockJAI pb = new ParameterBlockJAI("AWTImage", "rendered");
/* 115 */     pb.setParameter("awtImage", awtImage);
/* 117 */     return JAI.create("AWTImage", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\AWTImageDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */