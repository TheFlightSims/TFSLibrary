/*    */ package org.codehaus.stax2.ri.evt;
/*    */ 
/*    */ import javax.xml.stream.Location;
/*    */ import javax.xml.stream.events.Namespace;
/*    */ 
/*    */ public class NamespaceEventImpl extends AttributeEventImpl implements Namespace {
/*    */   final String mPrefix;
/*    */   
/*    */   final String mURI;
/*    */   
/*    */   protected NamespaceEventImpl(Location loc, String nsURI) {
/* 26 */     super(loc, "xmlns", "http://www.w3.org/2000/xmlns/", null, nsURI, true);
/* 29 */     this.mPrefix = "";
/* 30 */     this.mURI = nsURI;
/*    */   }
/*    */   
/*    */   protected NamespaceEventImpl(Location loc, String nsPrefix, String nsURI) {
/* 39 */     super(loc, nsPrefix, "http://www.w3.org/2000/xmlns/", "xmlns", nsURI, true);
/* 42 */     this.mPrefix = nsPrefix;
/* 43 */     this.mURI = nsURI;
/*    */   }
/*    */   
/*    */   public static NamespaceEventImpl constructDefaultNamespace(Location loc, String nsURI) {
/* 48 */     return new NamespaceEventImpl(loc, nsURI);
/*    */   }
/*    */   
/*    */   public static NamespaceEventImpl constructNamespace(Location loc, String nsPrefix, String nsURI) {
/* 53 */     if (nsPrefix == null || nsPrefix.length() == 0)
/* 54 */       return new NamespaceEventImpl(loc, nsURI); 
/* 56 */     return new NamespaceEventImpl(loc, nsPrefix, nsURI);
/*    */   }
/*    */   
/*    */   public String getNamespaceURI() {
/* 60 */     return this.mURI;
/*    */   }
/*    */   
/*    */   public String getPrefix() {
/* 64 */     return this.mPrefix;
/*    */   }
/*    */   
/*    */   public boolean isDefaultNamespaceDeclaration() {
/* 68 */     return (this.mPrefix.length() == 0);
/*    */   }
/*    */   
/*    */   public int getEventType() {
/* 78 */     return 13;
/*    */   }
/*    */   
/*    */   public boolean isNamespace() {
/* 82 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\codehaus\stax2\ri\evt\NamespaceEventImpl.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */