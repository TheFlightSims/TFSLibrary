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
/*     */ public class OrConstDescriptor extends OperationDescriptorImpl {
/*  90 */   private static final String[][] resources = new String[][] { { "GlobalName", "OrConst" }, { "LocalName", "OrConst" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("OrConstDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/OrConstDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("OrConstDescriptor1") } };
/*     */   
/* 107 */   private static final Class[] paramClasses = new Class[] { (array$I == null) ? (array$I = class$("[I")) : array$I };
/*     */   
/*     */   static Class class$(String x0) {
/*     */     try {
/* 108 */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/* 108 */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */   
/* 112 */   private static final String[] paramNames = new String[] { "constants" };
/*     */   
/* 117 */   private static final Object[] paramDefaults = new Object[] { { 0 } };
/*     */   
/* 121 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   static Class array$I;
/*     */   
/*     */   public OrConstDescriptor() {
/* 128 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   public boolean validateArguments(String modeName, ParameterBlock args, StringBuffer message) {
/* 142 */     if (!super.validateArguments(modeName, args, message))
/* 143 */       return false; 
/* 146 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 147 */       return true; 
/* 149 */     RenderedImage src = args.getRenderedSource(0);
/* 151 */     int dtype = src.getSampleModel().getDataType();
/* 153 */     if (dtype != 0 && dtype != 1 && dtype != 2 && dtype != 3) {
/* 157 */       message.append(getName() + " " + JaiI18N.getString("OrConstDescriptor2"));
/* 159 */       return false;
/*     */     } 
/* 162 */     int length = ((int[])args.getObjectParameter(0)).length;
/* 163 */     if (length < 1) {
/* 164 */       message.append(getName() + " " + JaiI18N.getString("OrConstDescriptor3"));
/* 166 */       return false;
/*     */     } 
/* 169 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, int[] constants, RenderingHints hints) {
/* 195 */     ParameterBlockJAI pb = new ParameterBlockJAI("OrConst", "rendered");
/* 199 */     pb.setSource("source0", source0);
/* 201 */     pb.setParameter("constants", constants);
/* 203 */     return JAI.create("OrConst", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, int[] constants, RenderingHints hints) {
/* 228 */     ParameterBlockJAI pb = new ParameterBlockJAI("OrConst", "renderable");
/* 232 */     pb.setSource("source0", source0);
/* 234 */     pb.setParameter("constants", constants);
/* 236 */     return JAI.createRenderable("OrConst", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\OrConstDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */