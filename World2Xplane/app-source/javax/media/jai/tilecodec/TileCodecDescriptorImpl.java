/*     */ package javax.media.jai.tilecodec;
/*     */ 
/*     */ import javax.media.jai.PropertyGenerator;
/*     */ 
/*     */ public abstract class TileCodecDescriptorImpl implements TileCodecDescriptor {
/*     */   private String formatName;
/*     */   
/*     */   private boolean includesSMInfo;
/*     */   
/*     */   private boolean includesLocationInfo;
/*     */   
/*     */   public TileCodecDescriptorImpl(String formatName, boolean includesSampleModelInfo, boolean includesLocationInfo) {
/*  54 */     if (formatName == null)
/*  55 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl0")); 
/*  59 */     this.formatName = formatName;
/*  60 */     this.includesSMInfo = includesSampleModelInfo;
/*  61 */     this.includesLocationInfo = includesLocationInfo;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  68 */     return this.formatName;
/*     */   }
/*     */   
/*     */   public String[] getSupportedModes() {
/*  82 */     return new String[] { "tileDecoder", "tileEncoder" };
/*     */   }
/*     */   
/*     */   public boolean isModeSupported(String registryModeName) {
/*  97 */     if (registryModeName == null)
/*  98 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl1")); 
/* 102 */     if (registryModeName.equalsIgnoreCase("tileDecoder") == true || registryModeName.equalsIgnoreCase("tileEncoder") == true)
/* 104 */       return true; 
/* 107 */     return false;
/*     */   }
/*     */   
/*     */   public boolean arePropertiesSupported() {
/* 120 */     return false;
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators(String modeName) {
/* 137 */     if (modeName == null)
/* 138 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl1")); 
/* 142 */     throw new UnsupportedOperationException(JaiI18N.getString("TileCodecDescriptorImpl2"));
/*     */   }
/*     */   
/*     */   public boolean includesSampleModelInfo() {
/* 151 */     return this.includesSMInfo;
/*     */   }
/*     */   
/*     */   public boolean includesLocationInfo() {
/* 159 */     return this.includesLocationInfo;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\tilecodec\TileCodecDescriptorImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */