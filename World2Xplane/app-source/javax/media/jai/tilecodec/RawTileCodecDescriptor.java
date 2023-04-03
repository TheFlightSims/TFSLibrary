/*     */ package javax.media.jai.tilecodec;
/*     */ 
/*     */ import java.awt.image.SampleModel;
/*     */ import javax.media.jai.ParameterListDescriptor;
/*     */ import javax.media.jai.ParameterListDescriptorImpl;
/*     */ 
/*     */ public class RawTileCodecDescriptor extends TileCodecDescriptorImpl {
/*  53 */   private static ParameterListDescriptorImpl pld = new ParameterListDescriptorImpl();
/*     */   
/*     */   public RawTileCodecDescriptor() {
/*  60 */     super("raw", true, true);
/*     */   }
/*     */   
/*     */   public TileCodecParameterList getCompatibleParameters(String modeName, TileCodecParameterList otherParamList) {
/*  87 */     if (modeName == null)
/*  88 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl1")); 
/*  91 */     String[] validNames = getSupportedModes();
/*  92 */     boolean valid = false;
/*  94 */     for (int i = 0; i < validNames.length; i++) {
/*  95 */       if (modeName.equalsIgnoreCase(validNames[i])) {
/*  96 */         valid = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 101 */     if (!valid)
/* 102 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodec1")); 
/* 106 */     return null;
/*     */   }
/*     */   
/*     */   public TileCodecParameterList getDefaultParameters(String modeName) {
/* 124 */     if (modeName == null)
/* 125 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl1")); 
/* 128 */     String[] validNames = getSupportedModes();
/* 129 */     boolean valid = false;
/* 131 */     for (int i = 0; i < validNames.length; i++) {
/* 132 */       if (modeName.equalsIgnoreCase(validNames[i])) {
/* 133 */         valid = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 138 */     if (!valid)
/* 139 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodec1")); 
/* 143 */     return null;
/*     */   }
/*     */   
/*     */   public TileCodecParameterList getDefaultParameters(String modeName, SampleModel sm) {
/* 172 */     if (modeName == null)
/* 173 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl1")); 
/* 176 */     String[] validNames = getSupportedModes();
/* 177 */     boolean valid = false;
/* 179 */     for (int i = 0; i < validNames.length; i++) {
/* 180 */       if (modeName.equalsIgnoreCase(validNames[i])) {
/* 181 */         valid = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 186 */     if (!valid)
/* 187 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodec1")); 
/* 191 */     return null;
/*     */   }
/*     */   
/*     */   public ParameterListDescriptor getParameterListDescriptor(String modeName) {
/* 214 */     if (modeName == null)
/* 215 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl1")); 
/* 218 */     String[] validNames = getSupportedModes();
/* 219 */     boolean valid = false;
/* 221 */     for (int i = 0; i < validNames.length; i++) {
/* 222 */       if (modeName.equalsIgnoreCase(validNames[i])) {
/* 223 */         valid = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 228 */     if (!valid)
/* 229 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodec1")); 
/* 233 */     return (ParameterListDescriptor)pld;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\tilecodec\RawTileCodecDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */