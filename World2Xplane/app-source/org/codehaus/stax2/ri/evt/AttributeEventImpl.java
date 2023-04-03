/*     */ package org.codehaus.stax2.ri.evt;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.Attribute;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ 
/*     */ public class AttributeEventImpl extends BaseEventImpl implements Attribute {
/*     */   final QName mName;
/*     */   
/*     */   final String mValue;
/*     */   
/*     */   final boolean mWasSpecified;
/*     */   
/*     */   public AttributeEventImpl(Location loc, String localName, String uri, String prefix, String value, boolean wasSpecified) {
/*  23 */     super(loc);
/*  24 */     this.mValue = value;
/*  25 */     if (prefix == null) {
/*  26 */       if (uri == null) {
/*  27 */         this.mName = new QName(localName);
/*     */       } else {
/*  29 */         this.mName = new QName(uri, localName);
/*     */       } 
/*     */     } else {
/*  32 */       if (uri == null)
/*  33 */         uri = ""; 
/*  35 */       this.mName = new QName(uri, localName, prefix);
/*     */     } 
/*  37 */     this.mWasSpecified = wasSpecified;
/*     */   }
/*     */   
/*     */   public AttributeEventImpl(Location loc, QName name, String value, boolean wasSpecified) {
/*  42 */     super(loc);
/*  43 */     this.mName = name;
/*  44 */     this.mValue = value;
/*  45 */     this.mWasSpecified = wasSpecified;
/*     */   }
/*     */   
/*     */   public int getEventType() {
/*  55 */     return 10;
/*     */   }
/*     */   
/*     */   public boolean isAttribute() {
/*  58 */     return true;
/*     */   }
/*     */   
/*     */   public void writeAsEncodedUnicode(Writer w) throws XMLStreamException {
/*  66 */     String prefix = this.mName.getPrefix();
/*     */     try {
/*  68 */       if (prefix != null && prefix.length() > 0) {
/*  69 */         w.write(prefix);
/*  70 */         w.write(58);
/*     */       } 
/*  72 */       w.write(this.mName.getLocalPart());
/*  73 */       w.write(61);
/*  74 */       w.write(34);
/*  75 */       writeEscapedAttrValue(w, this.mValue);
/*  76 */       w.write(34);
/*  77 */     } catch (IOException ie) {
/*  78 */       throwFromIOE(ie);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeUsing(XMLStreamWriter2 w) throws XMLStreamException {
/*  84 */     QName n = this.mName;
/*  85 */     w.writeAttribute(n.getPrefix(), n.getLocalPart(), n.getNamespaceURI(), this.mValue);
/*     */   }
/*     */   
/*     */   public String getDTDType() {
/*  99 */     return "CDATA";
/*     */   }
/*     */   
/*     */   public QName getName() {
/* 104 */     return this.mName;
/*     */   }
/*     */   
/*     */   public String getValue() {
/* 109 */     return this.mValue;
/*     */   }
/*     */   
/*     */   public boolean isSpecified() {
/* 114 */     return this.mWasSpecified;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 125 */     if (o == this)
/* 125 */       return true; 
/* 126 */     if (o == null)
/* 126 */       return false; 
/* 127 */     if (!(o instanceof Attribute))
/* 127 */       return false; 
/* 129 */     Attribute other = (Attribute)o;
/* 130 */     if (this.mName.equals(other.getName()) && this.mValue.equals(other.getValue()))
/* 137 */       if (isSpecified() == other.isSpecified())
/* 138 */         return stringsWithNullsEqual(getDTDType(), other.getDTDType());  
/* 141 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 151 */     return this.mName.hashCode() ^ this.mValue.hashCode();
/*     */   }
/*     */   
/*     */   protected static void writeEscapedAttrValue(Writer w, String value) throws IOException {
/* 163 */     int i = 0;
/* 164 */     int len = value.length();
/*     */     do {
/* 166 */       int start = i;
/* 167 */       char c = Character.MIN_VALUE;
/* 169 */       for (; i < len; i++) {
/* 170 */         c = value.charAt(i);
/* 171 */         if (c == '<' || c == '&' || c == '"')
/*     */           break; 
/*     */       } 
/* 175 */       int outLen = i - start;
/* 176 */       if (outLen > 0)
/* 177 */         w.write(value, start, outLen); 
/* 179 */       if (i >= len)
/*     */         continue; 
/* 180 */       if (c == '<') {
/* 181 */         w.write("&lt;");
/* 182 */       } else if (c == '&') {
/* 183 */         w.write("&amp;");
/* 184 */       } else if (c == '"') {
/* 185 */         w.write("&quot;");
/*     */       } 
/* 189 */     } while (++i < len);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\evt\AttributeEventImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */