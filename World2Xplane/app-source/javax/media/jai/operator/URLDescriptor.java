/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import com.sun.media.jai.codec.ImageDecodeParam;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.net.URL;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class URLDescriptor extends OperationDescriptorImpl {
/*  74 */   private static final String[][] resources = new String[][] { { "GlobalName", "URL" }, { "LocalName", "URL" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("URLDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/URLDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("URLDescriptor1") }, { "arg1Desc", JaiI18N.getString("URLDescriptor2") } };
/*     */   
/*  86 */   private static final String[] paramNames = new String[] { "URL", "param" };
/*     */   
/*  91 */   private static final Class[] paramClasses = new Class[] { URL.class, ImageDecodeParam.class };
/*     */   
/*  97 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT, null };
/*     */   
/*     */   public URLDescriptor() {
/* 103 */     super(resources, 0, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public static RenderedOp create(URL uRL, ImageDecodeParam param, RenderingHints hints) {
/* 129 */     ParameterBlockJAI pb = new ParameterBlockJAI("URL", "rendered");
/* 133 */     pb.setParameter("URL", uRL);
/* 134 */     pb.setParameter("param", param);
/* 136 */     return JAI.create("URL", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\URLDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */