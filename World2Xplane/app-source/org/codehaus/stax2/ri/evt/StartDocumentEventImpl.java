/*     */ package org.codehaus.stax2.ri.evt;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.XMLStreamReader;
/*     */ import javax.xml.stream.events.StartDocument;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ 
/*     */ public class StartDocumentEventImpl extends BaseEventImpl implements StartDocument {
/*     */   private final boolean mStandaloneSet;
/*     */   
/*     */   private final boolean mIsStandalone;
/*     */   
/*     */   private final String mVersion;
/*     */   
/*     */   private final boolean mEncodingSet;
/*     */   
/*     */   private final String mEncodingScheme;
/*     */   
/*     */   private final String mSystemId;
/*     */   
/*     */   public StartDocumentEventImpl(Location loc, XMLStreamReader r) {
/*  24 */     super(loc);
/*  25 */     this.mStandaloneSet = r.standaloneSet();
/*  26 */     this.mIsStandalone = r.isStandalone();
/*  33 */     String version = r.getVersion();
/*  34 */     if (version == null || version.length() == 0)
/*  35 */       version = "1.0"; 
/*  37 */     this.mVersion = version;
/*  39 */     this.mEncodingScheme = r.getCharacterEncodingScheme();
/*  40 */     this.mEncodingSet = (this.mEncodingScheme != null && this.mEncodingScheme.length() > 0);
/*  41 */     this.mSystemId = (loc != null) ? loc.getSystemId() : "";
/*     */   }
/*     */   
/*     */   public StartDocumentEventImpl(Location loc) {
/*  50 */     this(loc, (String)null);
/*     */   }
/*     */   
/*     */   public StartDocumentEventImpl(Location loc, String encoding) {
/*  55 */     this(loc, encoding, (String)null);
/*     */   }
/*     */   
/*     */   public StartDocumentEventImpl(Location loc, String encoding, String version) {
/*  60 */     this(loc, encoding, version, false, false);
/*     */   }
/*     */   
/*     */   public StartDocumentEventImpl(Location loc, String encoding, String version, boolean standaloneSet, boolean isStandalone) {
/*  66 */     super(loc);
/*  67 */     this.mEncodingScheme = encoding;
/*  68 */     this.mEncodingSet = (encoding != null && encoding.length() > 0);
/*  69 */     this.mVersion = version;
/*  70 */     this.mStandaloneSet = standaloneSet;
/*  71 */     this.mIsStandalone = isStandalone;
/*  72 */     this.mSystemId = "";
/*     */   }
/*     */   
/*     */   public boolean encodingSet() {
/*  76 */     return this.mEncodingSet;
/*     */   }
/*     */   
/*     */   public String getCharacterEncodingScheme() {
/*  80 */     return this.mEncodingScheme;
/*     */   }
/*     */   
/*     */   public String getSystemId() {
/*  84 */     return this.mSystemId;
/*     */   }
/*     */   
/*     */   public String getVersion() {
/*  88 */     return this.mVersion;
/*     */   }
/*     */   
/*     */   public boolean isStandalone() {
/*  92 */     return this.mIsStandalone;
/*     */   }
/*     */   
/*     */   public boolean standaloneSet() {
/*  96 */     return this.mStandaloneSet;
/*     */   }
/*     */   
/*     */   public int getEventType() {
/* 106 */     return 7;
/*     */   }
/*     */   
/*     */   public boolean isStartDocument() {
/* 110 */     return true;
/*     */   }
/*     */   
/*     */   public void writeAsEncodedUnicode(Writer w) throws XMLStreamException {
/*     */     try {
/* 118 */       w.write("<?xml version=\"");
/* 119 */       if (this.mVersion == null || this.mVersion.length() == 0) {
/* 120 */         w.write("1.0");
/*     */       } else {
/* 122 */         w.write(this.mVersion);
/*     */       } 
/* 124 */       w.write(34);
/* 125 */       if (this.mEncodingSet) {
/* 126 */         w.write(" encoding=\"");
/* 127 */         w.write(this.mEncodingScheme);
/* 128 */         w.write(34);
/*     */       } 
/* 130 */       if (this.mStandaloneSet)
/* 131 */         if (this.mIsStandalone) {
/* 132 */           w.write(" standalone=\"yes\"");
/*     */         } else {
/* 134 */           w.write(" standalone=\"no\"");
/*     */         }  
/* 137 */       w.write(" ?>");
/* 138 */     } catch (IOException ie) {
/* 139 */       throwFromIOE(ie);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeUsing(XMLStreamWriter2 w) throws XMLStreamException {
/* 145 */     w.writeStartDocument();
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 156 */     if (o == this)
/* 156 */       return true; 
/* 157 */     if (o == null)
/* 157 */       return false; 
/* 158 */     if (!(o instanceof StartDocument))
/* 158 */       return false; 
/* 160 */     StartDocument other = (StartDocument)o;
/* 162 */     return (encodingSet() == other.encodingSet() && isStandalone() == other.isStandalone() && standaloneSet() == other.standaloneSet() && stringsWithNullsEqual(getCharacterEncodingScheme(), other.getCharacterEncodingScheme()) && stringsWithNullsEqual(getSystemId(), other.getSystemId()) && stringsWithNullsEqual(getVersion(), other.getVersion()));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 174 */     int hash = 0;
/* 176 */     if (encodingSet())
/* 176 */       hash++; 
/* 177 */     if (isStandalone())
/* 177 */       hash--; 
/* 178 */     if (standaloneSet())
/* 178 */       hash ^= 0x1; 
/* 179 */     if (this.mVersion != null)
/* 179 */       hash ^= this.mVersion.hashCode(); 
/* 180 */     if (this.mEncodingScheme != null)
/* 180 */       hash ^= this.mEncodingScheme.hashCode(); 
/* 181 */     if (this.mSystemId != null)
/* 181 */       hash ^= this.mSystemId.hashCode(); 
/* 182 */     return hash;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\evt\StartDocumentEventImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */