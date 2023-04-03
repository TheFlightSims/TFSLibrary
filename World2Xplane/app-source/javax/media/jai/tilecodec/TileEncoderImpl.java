/*     */ package javax.media.jai.tilecodec;
/*     */ 
/*     */ import com.sun.media.jai.tilecodec.TileCodecUtils;
/*     */ import java.io.OutputStream;
/*     */ import javax.media.jai.ParameterListDescriptor;
/*     */ 
/*     */ public abstract class TileEncoderImpl implements TileEncoder {
/*     */   protected String formatName;
/*     */   
/*     */   protected OutputStream outputStream;
/*     */   
/*     */   protected TileCodecParameterList paramList;
/*     */   
/*     */   public TileEncoderImpl(String formatName, OutputStream output, TileCodecParameterList param) {
/*  84 */     if (formatName == null)
/*  85 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl0")); 
/*  89 */     if (output == null)
/*  90 */       throw new IllegalArgumentException(JaiI18N.getString("TileEncoderImpl0")); 
/*  94 */     TileCodecDescriptor tcd = TileCodecUtils.getTileCodecDescriptor("tileEncoder", formatName);
/*  98 */     if (param == null)
/*  99 */       param = tcd.getDefaultParameters("tileEncoder"); 
/* 101 */     if (param != null) {
/* 105 */       if (!param.getFormatName().equalsIgnoreCase(formatName))
/* 106 */         throw new IllegalArgumentException(JaiI18N.getString("TileEncoderImpl1")); 
/* 112 */       if (!param.isValidForMode("tileEncoder"))
/* 113 */         throw new IllegalArgumentException(JaiI18N.getString("TileEncoderImpl2")); 
/* 118 */       if (!param.getParameterListDescriptor().equals(tcd.getParameterListDescriptor("tileEncoder")))
/* 120 */         throw new IllegalArgumentException(JaiI18N.getString("TileCodec0")); 
/*     */     } else {
/* 126 */       ParameterListDescriptor pld = tcd.getParameterListDescriptor("tileEncoder");
/* 132 */       if (pld != null && pld.getNumParameters() != 0)
/* 133 */         throw new IllegalArgumentException(JaiI18N.getString("TileDecoderImpl6")); 
/*     */     } 
/* 138 */     this.formatName = formatName;
/* 139 */     this.outputStream = output;
/* 140 */     this.paramList = param;
/*     */   }
/*     */   
/*     */   public String getFormatName() {
/* 147 */     return this.formatName;
/*     */   }
/*     */   
/*     */   public TileCodecParameterList getEncodeParameterList() {
/* 155 */     return this.paramList;
/*     */   }
/*     */   
/*     */   public OutputStream getOutputStream() {
/* 163 */     return this.outputStream;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\tilecodec\TileEncoderImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */