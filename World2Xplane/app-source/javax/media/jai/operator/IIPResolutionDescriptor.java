/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.net.URL;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class IIPResolutionDescriptor extends OperationDescriptorImpl {
/* 142 */   public static final Integer MAX_RESOLUTION = new Integer(2147483647);
/*     */   
/* 148 */   private static final String[][] resources = new String[][] { { "GlobalName", "IIPResolution" }, { "LocalName", "IIPResolution" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("IIPResolutionDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/IIPResolutionDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("IIPResolutionDescriptor1") }, { "arg1Desc", JaiI18N.getString("IIPResolutionDescriptor2") }, { "arg2Desc", JaiI18N.getString("IIPResolutionDescriptor3") } };
/*     */   
/* 161 */   private static final Class[] paramClasses = new Class[] { String.class, Integer.class, Integer.class };
/*     */   
/* 168 */   private static final String[] paramNames = new String[] { "URL", "resolution", "subImage" };
/*     */   
/* 175 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT, MAX_RESOLUTION, new Integer(0) };
/*     */   
/*     */   public IIPResolutionDescriptor() {
/* 183 */     super(resources, 0, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public Number getParamMinValue(int index) {
/* 199 */     if (index == 0)
/* 200 */       return null; 
/* 201 */     if (index == 1 || index == 2)
/* 202 */       return new Integer(0); 
/* 204 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer msg) {
/* 217 */     if (!super.validateParameters(args, msg))
/* 218 */       return false; 
/*     */     try {
/* 222 */       new URL((String)args.getObjectParameter(0));
/* 223 */     } catch (Exception e) {
/* 225 */       msg.append(getName() + " " + JaiI18N.getString("IIPDescriptor15"));
/* 227 */       return false;
/*     */     } 
/* 230 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(String URL, Integer resolution, Integer subImage, RenderingHints hints) {
/* 259 */     ParameterBlockJAI pb = new ParameterBlockJAI("IIPResolution", "rendered");
/* 263 */     pb.setParameter("URL", URL);
/* 264 */     pb.setParameter("resolution", resolution);
/* 265 */     pb.setParameter("subImage", subImage);
/* 267 */     return JAI.create("IIPResolution", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\IIPResolutionDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */