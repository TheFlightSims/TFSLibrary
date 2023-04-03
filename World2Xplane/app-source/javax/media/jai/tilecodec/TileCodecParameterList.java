/*     */ package javax.media.jai.tilecodec;
/*     */ 
/*     */ import javax.media.jai.ParameterListDescriptor;
/*     */ import javax.media.jai.ParameterListImpl;
/*     */ 
/*     */ public class TileCodecParameterList extends ParameterListImpl {
/*     */   private String formatName;
/*     */   
/*     */   private String[] validModes;
/*     */   
/*     */   public TileCodecParameterList(String formatName, String[] validModes, ParameterListDescriptor descriptor) {
/*  72 */     super(descriptor);
/*  76 */     if (formatName == null)
/*  77 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl0")); 
/*  81 */     if (validModes == null)
/*  82 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecParameterList0")); 
/*  86 */     if (descriptor == null)
/*  87 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecParameterList1")); 
/*  91 */     this.formatName = formatName;
/*  92 */     this.validModes = validModes;
/*     */   }
/*     */   
/*     */   public String getFormatName() {
/*  99 */     return this.formatName;
/*     */   }
/*     */   
/*     */   public boolean isValidForMode(String registryModeName) {
/* 110 */     for (int i = 0; i < this.validModes.length; i++) {
/* 111 */       if (this.validModes[i].equalsIgnoreCase(registryModeName))
/* 112 */         return true; 
/*     */     } 
/* 116 */     return false;
/*     */   }
/*     */   
/*     */   public String[] getValidModes() {
/* 124 */     return (String[])this.validModes.clone();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\tilecodec\TileCodecParameterList.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */