/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import com.sun.media.jai.util.AreaOpPropertyGenerator;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.PropertyGenerator;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class BoxFilterDescriptor extends OperationDescriptorImpl {
/*  97 */   private static final String[][] resources = new String[][] { { "GlobalName", "BoxFilter" }, { "LocalName", "BoxFilter" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("BoxFilterDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/BoxFilterDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("BoxFilterDescriptor1") }, { "arg1Desc", JaiI18N.getString("BoxFilterDescriptor2") }, { "arg2Desc", JaiI18N.getString("BoxFilterDescriptor3") }, { "arg3Desc", JaiI18N.getString("BoxFilterDescriptor4") } };
/*     */   
/* 111 */   private static final Class[] paramClasses = new Class[] { Integer.class, Integer.class, Integer.class, Integer.class };
/*     */   
/* 117 */   private static final String[] paramNames = new String[] { "width", "height", "xKey", "yKey" };
/*     */   
/* 122 */   private static final Object[] paramDefaults = new Object[] { new Integer(3), null, null, null };
/*     */   
/*     */   public BoxFilterDescriptor() {
/* 128 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public Number getParamMinValue(int index) {
/* 136 */     if (index == 0 || index == 1)
/* 137 */       return new Integer(1); 
/* 138 */     if (index == 2 || index == 3)
/* 139 */       return new Integer(-2147483648); 
/* 141 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer msg) {
/* 148 */     int argNumParams = args.getNumParameters();
/* 150 */     if (argNumParams > 0 && args.getObjectParameter(0) instanceof Integer) {
/* 153 */       if (argNumParams < 2) {
/* 154 */         Object obj = args.getObjectParameter(0);
/* 155 */         if (obj instanceof Integer)
/* 157 */           args.add(obj); 
/*     */       } 
/* 161 */       if (argNumParams < 3) {
/* 162 */         Object obj = args.getObjectParameter(0);
/* 163 */         if (obj instanceof Integer)
/* 165 */           args.add(((Integer)obj).intValue() / 2); 
/*     */       } 
/* 169 */       if (argNumParams < 4) {
/* 170 */         Object obj = args.getObjectParameter(1);
/* 171 */         if (obj instanceof Integer)
/* 173 */           args.add(((Integer)obj).intValue() / 2); 
/*     */       } 
/*     */     } 
/* 178 */     return super.validateParameters(args, msg);
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 188 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 189 */     pg[0] = (PropertyGenerator)new AreaOpPropertyGenerator();
/* 190 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, Integer width, Integer height, Integer xKey, Integer yKey, RenderingHints hints) {
/* 225 */     ParameterBlockJAI pb = new ParameterBlockJAI("BoxFilter", "rendered");
/* 229 */     pb.setSource("source0", source0);
/* 231 */     pb.setParameter("width", width);
/* 232 */     pb.setParameter("height", height);
/* 233 */     pb.setParameter("xKey", xKey);
/* 234 */     pb.setParameter("yKey", yKey);
/* 236 */     return JAI.create("BoxFilter", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\BoxFilterDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */