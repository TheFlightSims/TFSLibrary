/*     */ package com.ctc.wstx.io;
/*     */ 
/*     */ import com.ctc.wstx.util.StringUtil;
/*     */ import java.io.Serializable;
/*     */ import javax.xml.stream.Location;
/*     */ import org.codehaus.stax2.XMLStreamLocation2;
/*     */ 
/*     */ public class WstxInputLocation implements Serializable, XMLStreamLocation2 {
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*  33 */   private static final WstxInputLocation sEmptyLocation = new WstxInputLocation(null, "", "", -1, -1, -1);
/*     */   
/*     */   protected final WstxInputLocation mContext;
/*     */   
/*     */   protected final String mPublicId;
/*     */   
/*     */   protected final String mSystemId;
/*     */   
/*     */   protected final int mCharOffset;
/*     */   
/*     */   protected final int mCol;
/*     */   
/*     */   protected final int mRow;
/*     */   
/*  47 */   protected transient String mDesc = null;
/*     */   
/*     */   public WstxInputLocation(WstxInputLocation ctxt, String pubId, String sysId, int charOffset, int row, int col) {
/*  56 */     this.mContext = ctxt;
/*  57 */     this.mPublicId = pubId;
/*  58 */     this.mSystemId = sysId;
/*  62 */     this.mCharOffset = (charOffset < 0) ? Integer.MAX_VALUE : charOffset;
/*  63 */     this.mCol = col;
/*  64 */     this.mRow = row;
/*     */   }
/*     */   
/*     */   public static WstxInputLocation getEmptyLocation() {
/*  68 */     return sEmptyLocation;
/*     */   }
/*     */   
/*     */   public int getCharacterOffset() {
/*  71 */     return this.mCharOffset;
/*     */   }
/*     */   
/*     */   public int getColumnNumber() {
/*  72 */     return this.mCol;
/*     */   }
/*     */   
/*     */   public int getLineNumber() {
/*  73 */     return this.mRow;
/*     */   }
/*     */   
/*     */   public String getPublicId() {
/*  75 */     return this.mPublicId;
/*     */   }
/*     */   
/*     */   public String getSystemId() {
/*  76 */     return this.mSystemId;
/*     */   }
/*     */   
/*     */   public XMLStreamLocation2 getContext() {
/*  84 */     return this.mContext;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  94 */     if (this.mDesc == null) {
/*     */       StringBuffer sb;
/*  96 */       if (this.mContext != null) {
/*  97 */         sb = new StringBuffer(200);
/*     */       } else {
/*  99 */         sb = new StringBuffer(80);
/*     */       } 
/* 101 */       appendDesc(sb);
/* 102 */       this.mDesc = sb.toString();
/*     */     } 
/* 104 */     return this.mDesc;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 108 */     return this.mCharOffset ^ this.mRow ^ this.mCol + (this.mCol << 3);
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 112 */     if (!(o instanceof Location))
/* 113 */       return false; 
/* 115 */     Location other = (Location)o;
/* 117 */     if (other.getCharacterOffset() != getCharacterOffset())
/* 118 */       return false; 
/* 120 */     String otherPub = other.getPublicId();
/* 121 */     if (otherPub == null)
/* 122 */       otherPub = ""; 
/* 124 */     if (!otherPub.equals(this.mPublicId))
/* 125 */       return false; 
/* 127 */     String otherSys = other.getSystemId();
/* 128 */     if (otherSys == null)
/* 129 */       otherSys = ""; 
/* 131 */     return otherSys.equals(this.mSystemId);
/*     */   }
/*     */   
/*     */   private void appendDesc(StringBuffer sb) {
/*     */     String srcId;
/* 144 */     if (this.mSystemId != null) {
/* 145 */       sb.append("[row,col,system-id]: ");
/* 146 */       srcId = this.mSystemId;
/* 147 */     } else if (this.mPublicId != null) {
/* 148 */       sb.append("[row,col,public-id]: ");
/* 149 */       srcId = this.mPublicId;
/*     */     } else {
/* 151 */       sb.append("[row,col {unknown-source}]: ");
/* 152 */       srcId = null;
/*     */     } 
/* 154 */     sb.append('[');
/* 155 */     sb.append(this.mRow);
/* 156 */     sb.append(',');
/* 157 */     sb.append(this.mCol);
/* 163 */     if (srcId != null) {
/* 164 */       sb.append(',');
/* 165 */       sb.append('"');
/* 166 */       sb.append(srcId);
/* 167 */       sb.append('"');
/*     */     } 
/* 169 */     sb.append(']');
/* 170 */     if (this.mContext != null) {
/* 171 */       StringUtil.appendLF(sb);
/* 172 */       sb.append(" from ");
/* 173 */       this.mContext.appendDesc(sb);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\io\WstxInputLocation.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */