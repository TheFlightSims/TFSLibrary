/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.Histogram;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class MatchCDFDescriptor extends OperationDescriptorImpl {
/*  72 */   private static final String[][] resources = new String[][] { { "GlobalName", "MatchCDF" }, { "LocalName", "MatchCDF" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("MatchCDFDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/MatchCDFDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", "The desired Cumulative Distribution Function." } };
/*     */   
/*  83 */   private static final Class[] paramClasses = new Class[] { (array$$F == null) ? (array$$F = class$("[[F")) : array$$F };
/*     */   
/*     */   static Class class$(String x0) {
/*     */     try {
/*  84 */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/*  84 */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*  88 */   private static final String[] paramNames = new String[] { "CDF" };
/*     */   
/*  93 */   private static final Object[] paramDefaults = new Object[] { null };
/*     */   
/*  97 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   static Class array$$F;
/*     */   
/*     */   public MatchCDFDescriptor() {
/* 104 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   public boolean validateArguments(String modeName, ParameterBlock args, StringBuffer msg) {
/* 119 */     if (!super.validateArguments(modeName, args, msg))
/* 120 */       return false; 
/* 123 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 124 */       return true; 
/* 127 */     RenderedImage src = args.getRenderedSource(0);
/* 129 */     float[][] CDF = (float[][])args.getObjectParameter(0);
/* 133 */     Object prop = src.getProperty("histogram");
/* 134 */     if (prop == null || prop.equals(Image.UndefinedProperty)) {
/* 136 */       msg.append(getName() + " " + JaiI18N.getString("MatchCDFDescriptor1"));
/* 138 */       return false;
/*     */     } 
/* 139 */     if (!(prop instanceof Histogram)) {
/* 141 */       msg.append(getName() + " " + JaiI18N.getString("MatchCDFDescriptor2"));
/* 143 */       return false;
/*     */     } 
/* 145 */     Histogram hist = (Histogram)prop;
/* 146 */     int numBands = hist.getNumBands();
/* 148 */     if (CDF == null) {
/* 149 */       int[] numBins = hist.getNumBins();
/* 150 */       CDF = new float[numBands][];
/* 152 */       for (int i = 0; i < numBands; i++) {
/* 153 */         CDF[i] = new float[numBins[i]];
/* 154 */         for (int j = 0; j < numBins[i]; j++)
/* 155 */           CDF[i][j] = ((j + 1) / numBins[i]); 
/*     */       } 
/*     */     } 
/* 159 */     if (CDF.length != numBands) {
/* 161 */       msg.append(getName() + " " + JaiI18N.getString("MatchCDFDescriptor3"));
/* 163 */       return false;
/*     */     } 
/*     */     int b;
/* 166 */     for (b = 0; b < numBands; b++) {
/* 167 */       if ((CDF[b]).length != hist.getNumBins(b)) {
/* 169 */         msg.append(getName() + " " + JaiI18N.getString("MatchCDFDescriptor4"));
/* 171 */         return false;
/*     */       } 
/*     */     } 
/* 175 */     for (b = 0; b < numBands; b++) {
/* 176 */       float[] CDFband = CDF[b];
/* 177 */       int length = CDFband.length;
/* 179 */       if (CDFband[length - 1] != 1.0D) {
/* 181 */         msg.append(getName() + " " + JaiI18N.getString("MatchCDFDescriptor7"));
/* 183 */         return false;
/*     */       } 
/* 186 */       for (int i = 0; i < length; i++) {
/* 187 */         if (CDFband[i] < 0.0F) {
/* 189 */           msg.append(getName() + " " + JaiI18N.getString("MatchCDFDescriptor5"));
/* 191 */           return false;
/*     */         } 
/* 192 */         if (i != 0 && 
/* 193 */           CDFband[i] < CDFband[i - 1]) {
/* 195 */           msg.append(getName() + " " + JaiI18N.getString("MatchCDFDescriptor6"));
/* 197 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 203 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, float[][] CDF, RenderingHints hints) {
/* 230 */     ParameterBlockJAI pb = new ParameterBlockJAI("MatchCDF", "rendered");
/* 234 */     pb.setSource("source0", source0);
/* 236 */     pb.setParameter("CDF", CDF);
/* 238 */     return JAI.create("MatchCDF", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, float[][] CDF, RenderingHints hints) {
/* 263 */     ParameterBlockJAI pb = new ParameterBlockJAI("MatchCDF", "renderable");
/* 267 */     pb.setSource("source0", source0);
/* 269 */     pb.setParameter("CDF", CDF);
/* 271 */     return JAI.createRenderable("MatchCDF", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\MatchCDFDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */