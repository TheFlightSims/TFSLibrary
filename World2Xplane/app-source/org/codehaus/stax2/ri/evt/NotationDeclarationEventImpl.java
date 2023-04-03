/*     */ package org.codehaus.stax2.ri.evt;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ import org.codehaus.stax2.evt.NotationDeclaration2;
/*     */ 
/*     */ public class NotationDeclarationEventImpl extends BaseEventImpl implements NotationDeclaration2 {
/*     */   final String mName;
/*     */   
/*     */   final String mPublicId;
/*     */   
/*     */   final String mSystemId;
/*     */   
/*     */   public NotationDeclarationEventImpl(Location loc, String name, String pubId, String sysId) {
/*  27 */     super(loc);
/*  28 */     this.mName = name;
/*  29 */     this.mPublicId = pubId;
/*  30 */     this.mSystemId = sysId;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  34 */     return this.mName;
/*     */   }
/*     */   
/*     */   public String getPublicId() {
/*  38 */     return this.mPublicId;
/*     */   }
/*     */   
/*     */   public String getSystemId() {
/*  42 */     return this.mSystemId;
/*     */   }
/*     */   
/*     */   public String getBaseURI() {
/*  50 */     return "";
/*     */   }
/*     */   
/*     */   public int getEventType() {
/*  60 */     return 14;
/*     */   }
/*     */   
/*     */   public void writeAsEncodedUnicode(Writer w) throws XMLStreamException {
/*     */     try {
/*  67 */       w.write("<!NOTATION ");
/*  68 */       w.write(this.mName);
/*  69 */       if (this.mPublicId != null) {
/*  70 */         w.write("PUBLIC \"");
/*  71 */         w.write(this.mPublicId);
/*  72 */         w.write(34);
/*     */       } else {
/*  74 */         w.write("SYSTEM");
/*     */       } 
/*  76 */       if (this.mSystemId != null) {
/*  77 */         w.write(" \"");
/*  78 */         w.write(this.mSystemId);
/*  79 */         w.write(34);
/*     */       } 
/*  81 */       w.write(62);
/*  82 */     } catch (IOException ie) {
/*  83 */       throwFromIOE(ie);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeUsing(XMLStreamWriter2 w) throws XMLStreamException {
/*  99 */     throw new XMLStreamException("Can not write notation declarations using an XMLStreamWriter");
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 110 */     if (o == this)
/* 110 */       return true; 
/* 111 */     if (o == null)
/* 111 */       return false; 
/* 113 */     if (!(o instanceof NotationDeclaration2))
/* 113 */       return false; 
/* 115 */     NotationDeclaration2 other = (NotationDeclaration2)o;
/* 117 */     return (stringsWithNullsEqual(getName(), other.getName()) && stringsWithNullsEqual(getPublicId(), other.getPublicId()) && stringsWithNullsEqual(getSystemId(), other.getSystemId()) && stringsWithNullsEqual(getBaseURI(), other.getBaseURI()));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 126 */     int hash = 0;
/* 127 */     if (this.mName != null)
/* 127 */       hash ^= this.mName.hashCode(); 
/* 128 */     if (this.mPublicId != null)
/* 128 */       hash ^= this.mPublicId.hashCode(); 
/* 129 */     if (this.mSystemId != null)
/* 129 */       hash ^= this.mSystemId.hashCode(); 
/* 130 */     return hash;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\evt\NotationDeclarationEventImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */