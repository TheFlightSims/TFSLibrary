/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ 
/*     */ public abstract class BaseInputSource extends WstxInputSource {
/*     */   final String mPublicId;
/*     */   
/*     */   final String mSystemId;
/*     */   
/*     */   protected URL mSource;
/*     */   
/*     */   protected char[] mBuffer;
/*     */   
/*     */   protected int mInputLast;
/*     */   
/*  51 */   long mSavedInputProcessed = 0L;
/*     */   
/*  53 */   int mSavedInputRow = 1;
/*     */   
/*  54 */   int mSavedInputRowStart = 0;
/*     */   
/*  56 */   int mSavedInputPtr = 0;
/*     */   
/*  64 */   transient WstxInputLocation mParentLocation = null;
/*     */   
/*     */   protected BaseInputSource(WstxInputSource parent, String fromEntity, String publicId, String systemId, URL src) {
/*  74 */     super(parent, fromEntity);
/*  75 */     this.mSystemId = systemId;
/*  76 */     this.mPublicId = publicId;
/*  77 */     this.mSource = src;
/*     */   }
/*     */   
/*     */   public void overrideSource(URL src) {
/*  82 */     this.mSource = src;
/*     */   }
/*     */   
/*     */   public abstract boolean fromInternalEntity();
/*     */   
/*     */   public URL getSource() {
/*  88 */     return this.mSource;
/*     */   }
/*     */   
/*     */   public String getPublicId() {
/*  92 */     return this.mPublicId;
/*     */   }
/*     */   
/*     */   public String getSystemId() {
/*  96 */     return this.mSystemId;
/*     */   }
/*     */   
/*     */   protected abstract void doInitInputLocation(WstxInputData paramWstxInputData);
/*     */   
/*     */   public abstract int readInto(WstxInputData paramWstxInputData) throws IOException, XMLStreamException;
/*     */   
/*     */   public abstract boolean readMore(WstxInputData paramWstxInputData, int paramInt) throws IOException, XMLStreamException;
/*     */   
/*     */   public void saveContext(WstxInputData reader) {
/* 110 */     this.mSavedInputPtr = reader.mInputPtr;
/* 113 */     this.mSavedInputProcessed = reader.mCurrInputProcessed;
/* 114 */     this.mSavedInputRow = reader.mCurrInputRow;
/* 115 */     this.mSavedInputRowStart = reader.mCurrInputRowStart;
/*     */   }
/*     */   
/*     */   public void restoreContext(WstxInputData reader) {
/* 120 */     reader.mInputBuffer = this.mBuffer;
/* 121 */     reader.mInputEnd = this.mInputLast;
/* 122 */     reader.mInputPtr = this.mSavedInputPtr;
/* 125 */     reader.mCurrInputProcessed = this.mSavedInputProcessed;
/* 126 */     reader.mCurrInputRow = this.mSavedInputRow;
/* 127 */     reader.mCurrInputRowStart = this.mSavedInputRowStart;
/*     */   }
/*     */   
/*     */   public abstract void close() throws IOException;
/*     */   
/*     */   protected final WstxInputLocation getLocation() {
/* 145 */     return getLocation(this.mSavedInputProcessed + this.mSavedInputPtr - 1L, this.mSavedInputRow, this.mSavedInputPtr - this.mSavedInputRowStart + 1);
/*     */   }
/*     */   
/*     */   public final WstxInputLocation getLocation(long total, int row, int col) {
/*     */     WstxInputLocation pl;
/* 154 */     if (this.mParent == null) {
/* 155 */       pl = null;
/*     */     } else {
/* 160 */       pl = this.mParentLocation;
/* 161 */       if (pl == null)
/* 162 */         this.mParentLocation = pl = this.mParent.getLocation(); 
/* 164 */       pl = this.mParent.getLocation();
/*     */     } 
/* 170 */     return new WstxInputLocation(pl, getPublicId(), getSystemId(), (int)total, row, col);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\BaseInputSource.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */