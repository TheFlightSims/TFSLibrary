/*     */ package javax.media.jai.tilecodec;
/*     */ 
/*     */ import com.sun.media.jai.tilecodec.TileCodecUtils;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.io.InputStream;
/*     */ import javax.media.jai.ParameterListDescriptor;
/*     */ 
/*     */ public abstract class TileDecoderImpl implements TileDecoder {
/*     */   protected String formatName;
/*     */   
/*     */   protected InputStream inputStream;
/*     */   
/*     */   protected TileCodecParameterList paramList;
/*     */   
/*     */   public TileDecoderImpl(String formatName, InputStream input, TileCodecParameterList param) {
/*  88 */     if (formatName == null)
/*  89 */       throw new IllegalArgumentException(JaiI18N.getString("TileCodecDescriptorImpl0")); 
/*  93 */     if (input == null)
/*  94 */       throw new IllegalArgumentException(JaiI18N.getString("TileDecoderImpl0")); 
/*  98 */     TileCodecDescriptor tcd = TileCodecUtils.getTileCodecDescriptor("tileDecoder", formatName);
/* 102 */     if (param == null)
/* 103 */       param = tcd.getDefaultParameters("tileDecoder"); 
/* 105 */     if (param != null) {
/* 109 */       if (!param.getFormatName().equalsIgnoreCase(formatName))
/* 110 */         throw new IllegalArgumentException(JaiI18N.getString("TileDecoderImpl1")); 
/* 116 */       if (!param.isValidForMode("tileDecoder"))
/* 117 */         throw new IllegalArgumentException(JaiI18N.getString("TileDecoderImpl2")); 
/* 122 */       if (!param.getParameterListDescriptor().equals(tcd.getParameterListDescriptor("tileDecoder")))
/* 124 */         throw new IllegalArgumentException(JaiI18N.getString("TileCodec0")); 
/* 126 */       SampleModel sm = null;
/* 129 */       if (!tcd.includesSampleModelInfo()) {
/*     */         try {
/* 131 */           sm = (SampleModel)param.getObjectParameter("sampleModel");
/* 132 */         } catch (IllegalArgumentException iae) {
/* 135 */           throw new IllegalArgumentException(JaiI18N.getString("TileDecoderImpl3"));
/*     */         } 
/* 139 */         if (sm == null || sm == ParameterListDescriptor.NO_PARAMETER_DEFAULT)
/* 142 */           if (tcd.getParameterListDescriptor("tileDecoder").getParamDefaultValue("sampleModel") == null)
/* 146 */             throw new IllegalArgumentException(JaiI18N.getString("TileDecoderImpl4"));  
/*     */       } 
/*     */     } else {
/* 156 */       ParameterListDescriptor pld = tcd.getParameterListDescriptor("tileDecoder");
/* 160 */       if (!tcd.includesSampleModelInfo())
/* 162 */         throw new IllegalArgumentException(JaiI18N.getString("TileDecoderImpl5")); 
/* 169 */       if (pld != null && pld.getNumParameters() != 0)
/* 170 */         throw new IllegalArgumentException(JaiI18N.getString("TileDecoderImpl6")); 
/*     */     } 
/* 175 */     this.formatName = formatName;
/* 176 */     this.inputStream = input;
/* 177 */     this.paramList = param;
/*     */   }
/*     */   
/*     */   public String getFormatName() {
/* 184 */     return this.formatName;
/*     */   }
/*     */   
/*     */   public TileCodecParameterList getDecodeParameterList() {
/* 192 */     return this.paramList;
/*     */   }
/*     */   
/*     */   public InputStream getInputStream() {
/* 200 */     return this.inputStream;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\tilecodec\TileDecoderImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */