/*     */ package javax.media.jai.tilecodec;
/*     */ 
/*     */ import java.awt.image.SampleModel;
/*     */ import javax.media.jai.ParameterListDescriptor;
/*     */ import javax.media.jai.ParameterListDescriptorImpl;
/*     */ 
/*     */ public class GZIPTileCodecDescriptor extends TileCodecDescriptorImpl {
/*  48 */   private static ParameterListDescriptorImpl pld = new ParameterListDescriptorImpl();
/*     */   
/*     */   public GZIPTileCodecDescriptor() {
/*  56 */     super("gzip", true, true);
/*     */   }
/*     */   
/*     */   public TileCodecParameterList getCompatibleParameters(String modeName, TileCodecParameterList otherParamList) {
/*  84 */     if (modeName == null)
/*  85 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl1")); 
/*  88 */     String[] validNames = getSupportedModes();
/*  89 */     boolean valid = false;
/*  91 */     for (int i = 0; i < validNames.length; i++) {
/*  92 */       if (modeName.equalsIgnoreCase(validNames[i])) {
/*  93 */         valid = true;
/*     */         break;
/*     */       } 
/*     */     } 
/*  98 */     if (!valid)
/*  99 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodec1")); 
/* 103 */     return null;
/*     */   }
/*     */   
/*     */   public TileCodecParameterList getDefaultParameters(String modeName) {
/* 121 */     if (modeName == null)
/* 122 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl1")); 
/* 125 */     String[] validNames = getSupportedModes();
/* 126 */     boolean valid = false;
/* 128 */     for (int i = 0; i < validNames.length; i++) {
/* 129 */       if (modeName.equalsIgnoreCase(validNames[i])) {
/* 130 */         valid = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 135 */     if (!valid)
/* 136 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodec1")); 
/* 140 */     return null;
/*     */   }
/*     */   
/*     */   public TileCodecParameterList getDefaultParameters(String modeName, SampleModel sm) {
/* 169 */     if (modeName == null)
/* 170 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl1")); 
/* 173 */     String[] validNames = getSupportedModes();
/* 174 */     boolean valid = false;
/* 176 */     for (int i = 0; i < validNames.length; i++) {
/* 177 */       if (modeName.equalsIgnoreCase(validNames[i])) {
/* 178 */         valid = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 183 */     if (!valid)
/* 184 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodec1")); 
/* 188 */     return null;
/*     */   }
/*     */   
/*     */   public ParameterListDescriptor getParameterListDescriptor(String modeName) {
/* 211 */     if (modeName == null)
/* 212 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl1")); 
/* 215 */     String[] validNames = getSupportedModes();
/* 216 */     boolean valid = false;
/* 218 */     for (int i = 0; i < validNames.length; i++) {
/* 219 */       if (modeName.equalsIgnoreCase(validNames[i])) {
/* 220 */         valid = true;
/*     */         break;
/*     */       } 
/*     */     } 
/* 225 */     if (!valid)
/* 226 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodec1")); 
/* 230 */     return (ParameterListDescriptor)pld;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\tilecodec\GZIPTileCodecDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */