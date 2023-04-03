/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class XorConstDescriptor extends OperationDescriptorImpl {
/*  91 */   private static final String[][] resources = new String[][] { { "GlobalName", "XorConst" }, { "LocalName", "XorConst" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("XorConstDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/XorConstDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("XorConstDescriptor1") } };
/*     */   
/* 108 */   private static final Class[] paramClasses = new Class[] { (array$I == null) ? (array$I = class$("[I")) : array$I };
/*     */   
/*     */   static Class class$(String x0) {
/*     */     try {
/* 109 */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/* 109 */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */   
/* 113 */   private static final String[] paramNames = new String[] { "constants" };
/*     */   
/* 118 */   private static final Object[] paramDefaults = new Object[] { { 0 } };
/*     */   
/* 122 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   static Class array$I;
/*     */   
/*     */   public XorConstDescriptor() {
/* 129 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   public boolean validateArguments(String modeName, ParameterBlock args, StringBuffer message) {
/* 143 */     if (!super.validateArguments(modeName, args, message))
/* 144 */       return false; 
/* 147 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 148 */       return true; 
/* 150 */     RenderedImage src = args.getRenderedSource(0);
/* 152 */     int dtype = src.getSampleModel().getDataType();
/* 154 */     if (dtype != 0 && dtype != 1 && dtype != 2 && dtype != 3) {
/* 158 */       message.append(getName() + " " + JaiI18N.getString("XorConstDescriptor2"));
/* 160 */       return false;
/*     */     } 
/* 163 */     int length = ((int[])args.getObjectParameter(0)).length;
/* 164 */     if (length < 1) {
/* 165 */       message.append(getName() + " " + JaiI18N.getString("XorConstDescriptor3"));
/* 167 */       return false;
/*     */     } 
/* 170 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, int[] constants, RenderingHints hints) {
/* 196 */     ParameterBlockJAI pb = new ParameterBlockJAI("XorConst", "rendered");
/* 200 */     pb.setSource("source0", source0);
/* 202 */     pb.setParameter("constants", constants);
/* 204 */     return JAI.create("XorConst", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, int[] constants, RenderingHints hints) {
/* 229 */     ParameterBlockJAI pb = new ParameterBlockJAI("XorConst", "renderable");
/* 233 */     pb.setSource("source0", source0);
/* 235 */     pb.setParameter("constants", constants);
/* 237 */     return JAI.createRenderable("XorConst", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\XorConstDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */