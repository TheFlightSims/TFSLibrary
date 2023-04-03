/*     */ package org.codehaus.stax2.ri.evt;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import javax.xml.stream.Location;
/*     */ import javax.xml.stream.XMLStreamException;
/*     */ import javax.xml.stream.events.Characters;
/*     */ import org.codehaus.stax2.XMLStreamWriter2;
/*     */ 
/*     */ public class CharactersEventImpl extends BaseEventImpl implements Characters {
/*     */   final String mContent;
/*     */   
/*     */   final boolean mIsCData;
/*     */   
/*     */   final boolean mIgnorableWS;
/*     */   
/*     */   boolean mWhitespaceChecked = false;
/*     */   
/*     */   boolean mIsWhitespace = false;
/*     */   
/*     */   public CharactersEventImpl(Location loc, String content, boolean cdata) {
/*  30 */     super(loc);
/*  31 */     this.mContent = content;
/*  32 */     this.mIsCData = cdata;
/*  33 */     this.mIgnorableWS = false;
/*     */   }
/*     */   
/*     */   private CharactersEventImpl(Location loc, String content, boolean cdata, boolean allWS, boolean ignorableWS) {
/*  42 */     super(loc);
/*  43 */     this.mContent = content;
/*  44 */     this.mIsCData = cdata;
/*  45 */     this.mIsWhitespace = allWS;
/*  46 */     if (allWS) {
/*  47 */       this.mWhitespaceChecked = true;
/*  48 */       this.mIgnorableWS = ignorableWS;
/*     */     } else {
/*  50 */       this.mWhitespaceChecked = false;
/*  51 */       this.mIgnorableWS = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static final CharactersEventImpl createIgnorableWS(Location loc, String content) {
/*  56 */     return new CharactersEventImpl(loc, content, false, true, true);
/*     */   }
/*     */   
/*     */   public static final CharactersEventImpl createNonIgnorableWS(Location loc, String content) {
/*  60 */     return new CharactersEventImpl(loc, content, false, true, false);
/*     */   }
/*     */   
/*     */   public Characters asCharacters() {
/*  70 */     return this;
/*     */   }
/*     */   
/*     */   public int getEventType() {
/*  74 */     return this.mIsCData ? 12 : 4;
/*     */   }
/*     */   
/*     */   public boolean isCharacters() {
/*  77 */     return true;
/*     */   }
/*     */   
/*     */   public void writeAsEncodedUnicode(Writer w) throws XMLStreamException {
/*     */     try {
/*  83 */       if (this.mIsCData) {
/*  84 */         w.write("<![CDATA[");
/*  85 */         w.write(this.mContent);
/*  86 */         w.write("]]>");
/*     */       } else {
/*  88 */         writeEscapedXMLText(w, this.mContent);
/*     */       } 
/*  90 */     } catch (IOException ie) {
/*  91 */       throwFromIOE(ie);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void writeUsing(XMLStreamWriter2 w) throws XMLStreamException {
/*  97 */     if (this.mIsCData) {
/*  98 */       w.writeCData(this.mContent);
/*     */     } else {
/* 100 */       w.writeCharacters(this.mContent);
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getData() {
/* 111 */     return this.mContent;
/*     */   }
/*     */   
/*     */   public boolean isCData() {
/* 115 */     return this.mIsCData;
/*     */   }
/*     */   
/*     */   public boolean isIgnorableWhiteSpace() {
/* 119 */     return this.mIgnorableWS;
/*     */   }
/*     */   
/*     */   public boolean isWhiteSpace() {
/* 124 */     if (!this.mWhitespaceChecked) {
/* 125 */       this.mWhitespaceChecked = true;
/* 126 */       String str = this.mContent;
/* 127 */       int i = 0;
/* 128 */       int len = str.length();
/* 129 */       for (; i < len && 
/* 130 */         str.charAt(i) <= ' '; i++);
/* 134 */       this.mIsWhitespace = (i == len);
/*     */     } 
/* 136 */     return this.mIsWhitespace;
/*     */   }
/*     */   
/*     */   public void setWhitespaceStatus(boolean status) {
/* 147 */     this.mWhitespaceChecked = true;
/* 148 */     this.mIsWhitespace = status;
/*     */   }
/*     */   
/*     */   public boolean equals(Object o) {
/* 159 */     if (o == this)
/* 159 */       return true; 
/* 160 */     if (o == null)
/* 160 */       return false; 
/* 161 */     if (!(o instanceof Characters))
/* 161 */       return false; 
/* 163 */     Characters other = (Characters)o;
/* 165 */     if (this.mContent.equals(other.getData()))
/* 168 */       return (isCData() == other.isCData()); 
/* 170 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 175 */     return this.mContent.hashCode();
/*     */   }
/*     */   
/*     */   protected static void writeEscapedXMLText(Writer w, String text) throws IOException {
/* 187 */     int len = text.length();
/* 189 */     int i = 0;
/* 190 */     while (i < len) {
/* 191 */       int start = i;
/* 192 */       char c = Character.MIN_VALUE;
/* 194 */       while (i < len) {
/* 195 */         c = text.charAt(i);
/* 196 */         if (c == '<' || c == '&')
/*     */           break; 
/* 199 */         if (c == '>' && i >= 2 && text.charAt(i - 1) == ']' && text.charAt(i - 2) == ']')
/*     */           break; 
/* 203 */         i++;
/*     */       } 
/* 205 */       int outLen = i - start;
/* 206 */       if (outLen > 0)
/* 207 */         w.write(text, start, outLen); 
/* 209 */       if (i < len)
/* 210 */         if (c == '<') {
/* 211 */           w.write("&lt;");
/* 212 */         } else if (c == '&') {
/* 213 */           w.write("&amp;");
/* 214 */         } else if (c == '>') {
/* 215 */           w.write("&gt;");
/*     */         }  
/* 218 */       i++;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\evt\CharactersEventImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */