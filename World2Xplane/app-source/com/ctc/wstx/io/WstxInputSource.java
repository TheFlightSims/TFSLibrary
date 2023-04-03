/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public abstract class WstxInputSource {
/*     */   protected final WstxInputSource mParent;
/*     */   
/*     */   protected final String mFromEntity;
/*     */   
/*  51 */   protected int mScopeId = 0;
/*     */   
/*     */   protected WstxInputSource(WstxInputSource parent, String fromEntity) {
/*  61 */     this.mParent = parent;
/*  62 */     this.mFromEntity = fromEntity;
/*     */   }
/*     */   
/*     */   public abstract void overrideSource(URL paramURL);
/*     */   
/*     */   public final WstxInputSource getParent() {
/*  81 */     return this.mParent;
/*     */   }
/*     */   
/*     */   public boolean isOrIsExpandedFrom(String entityId) {
/*  94 */     if (entityId != null) {
/*  95 */       WstxInputSource curr = this;
/*  96 */       while (curr != null) {
/*  97 */         if (entityId == curr.mFromEntity)
/*  98 */           return true; 
/* 100 */         curr = curr.mParent;
/*     */       } 
/*     */     } 
/* 103 */     return false;
/*     */   }
/*     */   
/*     */   public abstract boolean fromInternalEntity();
/*     */   
/*     */   public abstract URL getSource();
/*     */   
/*     */   public abstract String getPublicId();
/*     */   
/*     */   public abstract String getSystemId();
/*     */   
/*     */   protected abstract WstxInputLocation getLocation();
/*     */   
/*     */   public abstract WstxInputLocation getLocation(long paramLong, int paramInt1, int paramInt2);
/*     */   
/*     */   public String getEntityId() {
/* 134 */     return this.mFromEntity;
/*     */   }
/*     */   
/*     */   public int getScopeId() {
/* 136 */     return this.mScopeId;
/*     */   }
/*     */   
/*     */   public final void initInputLocation(WstxInputData reader, int currScopeId) {
/* 154 */     this.mScopeId = currScopeId;
/* 155 */     doInitInputLocation(reader);
/*     */   }
/*     */   
/*     */   protected abstract void doInitInputLocation(WstxInputData paramWstxInputData);
/*     */   
/*     */   public abstract int readInto(WstxInputData paramWstxInputData) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract boolean readMore(WstxInputData paramWstxInputData, int paramInt) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract void saveContext(WstxInputData paramWstxInputData);
/*     */   
/*     */   public abstract void restoreContext(WstxInputData paramWstxInputData);
/*     */   
/*     */   public abstract void close() throws IOException;
/*     */   
/*     */   public abstract void closeCompletely() throws IOException;
/*     */   
/*     */   public String toString() {
/* 220 */     StringBuffer sb = new StringBuffer(80);
/* 221 */     sb.append("<WstxInputSource [class ");
/* 222 */     sb.append(getClass().toString());
/* 223 */     sb.append("]; systemId: ");
/* 224 */     sb.append(getSystemId());
/* 225 */     sb.append(", source: ");
/* 226 */     sb.append(getSource());
/* 227 */     sb.append('>');
/* 228 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\WstxInputSource.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */