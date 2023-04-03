/*     */ package org.codehaus.stax2.ri.evt;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import java.util.List;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.DTD;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ import org.codehaus.stax2.evt.DTD2;
/*     */ 
/*     */ public class DTDEventImpl extends BaseEventImpl implements DTD2 {
/*     */   final String mRootName;
/*     */   
/*     */   final String mSystemId;
/*     */   
/*     */   final String mPublicId;
/*     */   
/*     */   final String mInternalSubset;
/*     */   
/*     */   final Object mDTD;
/*     */   
/*  47 */   String mFullText = null;
/*     */   
/*     */   public DTDEventImpl(Location loc, String rootName, String sysId, String pubId, String intSubset, Object dtd) {
/*  59 */     super(loc);
/*  60 */     this.mRootName = rootName;
/*  61 */     this.mSystemId = sysId;
/*  62 */     this.mPublicId = pubId;
/*  63 */     this.mInternalSubset = intSubset;
/*  64 */     this.mFullText = null;
/*  65 */     this.mDTD = dtd;
/*     */   }
/*     */   
/*     */   public DTDEventImpl(Location loc, String rootName, String intSubset) {
/*  73 */     this(loc, rootName, (String)null, (String)null, intSubset, (Object)null);
/*     */   }
/*     */   
/*     */   public DTDEventImpl(Location loc, String fullText) {
/*  78 */     this(loc, (String)null, (String)null, (String)null, (String)null, (Object)null);
/*  79 */     this.mFullText = fullText;
/*     */   }
/*     */   
/*     */   public String getDocumentTypeDeclaration() {
/*     */     try {
/*  91 */       return doGetDocumentTypeDeclaration();
/*  92 */     } catch (XMLStreamException sex) {
/*  93 */       throw new RuntimeException("Internal error: " + sex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public List getEntities() {
/* 100 */     return null;
/*     */   }
/*     */   
/*     */   public List getNotations() {
/* 106 */     return null;
/*     */   }
/*     */   
/*     */   public Object getProcessedDTD() {
/* 111 */     return this.mDTD;
/*     */   }
/*     */   
/*     */   public int getEventType() {
/* 121 */     return 11;
/*     */   }
/*     */   
/*     */   public void writeAsEncodedUnicode(Writer w) throws XMLStreamException {
/*     */     try {
/* 129 */       if (this.mFullText != null) {
/* 130 */         w.write(this.mFullText);
/*     */         return;
/*     */       } 
/* 134 */       w.write("<!DOCTYPE");
/* 135 */       if (this.mRootName != null) {
/* 137 */         w.write(32);
/* 138 */         w.write(this.mRootName);
/*     */       } 
/* 140 */       if (this.mSystemId != null) {
/* 141 */         if (this.mPublicId != null) {
/* 142 */           w.write(" PUBLIC \"");
/* 143 */           w.write(this.mPublicId);
/* 144 */           w.write(34);
/*     */         } else {
/* 146 */           w.write(" SYSTEM");
/*     */         } 
/* 148 */         w.write(" \"");
/* 149 */         w.write(this.mSystemId);
/* 150 */         w.write(34);
/*     */       } 
/* 152 */       if (this.mInternalSubset != null) {
/* 153 */         w.write(" [");
/* 154 */         w.write(this.mInternalSubset);
/* 155 */         w.write(93);
/*     */       } 
/* 157 */       w.write(">");
/* 158 */     } catch (IOException ie) {
/* 159 */       throwFromIOE(ie);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeUsing(XMLStreamWriter2 w) throws XMLStreamException {
/* 165 */     if (this.mRootName != null) {
/* 166 */       w.writeDTD(this.mRootName, this.mSystemId, this.mPublicId, this.mInternalSubset);
/*     */       return;
/*     */     } 
/* 171 */     w.writeDTD(doGetDocumentTypeDeclaration());
/*     */   }
/*     */   
/*     */   public String getRootName() {
/* 181 */     return this.mRootName;
/*     */   }
/*     */   
/*     */   public String getSystemId() {
/* 185 */     return this.mSystemId;
/*     */   }
/*     */   
/*     */   public String getPublicId() {
/* 189 */     return this.mPublicId;
/*     */   }
/*     */   
/*     */   public String getInternalSubset() {
/* 193 */     return this.mInternalSubset;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 204 */     if (o == this)
/* 204 */       return true; 
/* 205 */     if (o == null)
/* 205 */       return false; 
/* 206 */     if (!(o instanceof DTD))
/* 206 */       return false; 
/* 208 */     DTD other = (DTD)o;
/* 216 */     return stringsWithNullsEqual(getDocumentTypeDeclaration(), other.getDocumentTypeDeclaration());
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 223 */     int hash = 0;
/* 224 */     if (this.mRootName != null)
/* 224 */       hash ^= this.mRootName.hashCode(); 
/* 225 */     if (this.mSystemId != null)
/* 225 */       hash ^= this.mSystemId.hashCode(); 
/* 226 */     if (this.mPublicId != null)
/* 226 */       hash ^= this.mPublicId.hashCode(); 
/* 227 */     if (this.mInternalSubset != null)
/* 227 */       hash ^= this.mInternalSubset.hashCode(); 
/* 228 */     if (this.mDTD != null)
/* 228 */       hash ^= this.mDTD.hashCode(); 
/* 229 */     if (hash == 0 && this.mFullText != null)
/* 230 */       hash ^= this.mFullText.hashCode(); 
/* 232 */     return hash;
/*     */   }
/*     */   
/*     */   protected String doGetDocumentTypeDeclaration() throws XMLStreamException {
/* 244 */     if (this.mFullText == null) {
/* 245 */       int len = 60;
/* 246 */       if (this.mInternalSubset != null)
/* 247 */         len += this.mInternalSubset.length() + 4; 
/* 249 */       StringWriter sw = new StringWriter(len);
/* 250 */       writeAsEncodedUnicode(sw);
/* 251 */       this.mFullText = sw.toString();
/*     */     } 
/* 253 */     return this.mFullText;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\evt\DTDEventImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */