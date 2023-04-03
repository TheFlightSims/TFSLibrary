/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ import javax.media.jai.util.Range;
/*     */ 
/*     */ public class ConstantDescriptor extends OperationDescriptorImpl {
/*  79 */   private static final String[][] resources = new String[][] { { "GlobalName", "Constant" }, { "LocalName", "Constant" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("ConstantDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/ConstantDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("ConstantDescriptor1") }, { "arg1Desc", JaiI18N.getString("ConstantDescriptor2") }, { "arg2Desc", JaiI18N.getString("ConstantDescriptor3") } };
/*     */   
/*  92 */   private static final Class[] paramClasses = new Class[] { Float.class, Float.class, (array$Ljava$lang$Number == null) ? (array$Ljava$lang$Number = class$("[Ljava.lang.Number;")) : array$Ljava$lang$Number };
/*     */   
/*  99 */   private static final String[] paramNames = new String[] { "width", "height", "bandValues" };
/*     */   
/* 104 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT, NO_PARAMETER_DEFAULT, NO_PARAMETER_DEFAULT };
/*     */   
/* 108 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/* 113 */   private static final Object[] validParamValues = new Object[] { new Range(Float.class, new Float(0.0F), false, null, false), new Range(Float.class, new Float(0.0F), false, null, false), null };
/*     */   
/*     */   static Class array$Ljava$lang$Number;
/*     */   
/*     */   public ConstantDescriptor() {
/* 121 */     super(resources, supportedModes, 0, paramNames, paramClasses, paramDefaults, validParamValues);
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(String modeName, ParameterBlock args, StringBuffer message) {
/* 136 */     if (!super.validateParameters(modeName, args, message))
/* 137 */       return false; 
/* 140 */     int length = ((Number[])args.getObjectParameter(2)).length;
/* 141 */     if (length < 1) {
/* 142 */       message.append(getName() + " " + JaiI18N.getString("ConstantDescriptor4"));
/* 144 */       return false;
/*     */     } 
/* 147 */     if (modeName.equalsIgnoreCase("rendered")) {
/* 148 */       int width = Math.round(args.getFloatParameter(0));
/* 149 */       int height = Math.round(args.getFloatParameter(1));
/* 151 */       if (width < 1 || height < 1) {
/* 152 */         message.append(getName() + " " + JaiI18N.getString("ConstantDescriptor5"));
/* 154 */         return false;
/*     */       } 
/* 156 */     } else if (modeName.equalsIgnoreCase("renderable")) {
/* 157 */       float width = args.getFloatParameter(0);
/* 158 */       float height = args.getFloatParameter(1);
/* 160 */       if (width <= 0.0F || height <= 0.0F) {
/* 161 */         message.append(getName() + " " + JaiI18N.getString("ConstantDescriptor6"));
/* 163 */         return false;
/*     */       } 
/*     */     } 
/* 167 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(Float width, Float height, Number[] bandValues, RenderingHints hints) {
/* 196 */     ParameterBlockJAI pb = new ParameterBlockJAI("Constant", "rendered");
/* 200 */     pb.setParameter("width", width);
/* 201 */     pb.setParameter("height", height);
/* 202 */     pb.setParameter("bandValues", bandValues);
/* 204 */     return JAI.create("Constant", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(Float width, Float height, Number[] bandValues, RenderingHints hints) {
/* 232 */     ParameterBlockJAI pb = new ParameterBlockJAI("Constant", "renderable");
/* 236 */     pb.setParameter("width", width);
/* 237 */     pb.setParameter("height", height);
/* 238 */     pb.setParameter("bandValues", bandValues);
/* 240 */     return JAI.createRenderable("Constant", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\ConstantDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */