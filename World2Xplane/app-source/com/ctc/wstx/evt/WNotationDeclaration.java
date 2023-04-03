/*    */ package com.ctc.wstx.evt;
/*    */ 
/*    */ import java.net.URL;
/*    */ import javax.xml.stream.Location;
/*    */ import org.codehaus.stax2.ri.evt.NotationDeclarationEventImpl;
/*    */ 
/*    */ public class WNotationDeclaration extends NotationDeclarationEventImpl {
/*    */   final URL _baseURL;
/*    */   
/*    */   public WNotationDeclaration(Location loc, String name, String pubId, String sysId, URL baseURL) {
/* 30 */     super(loc, name, pubId, sysId);
/* 31 */     this._baseURL = baseURL;
/*    */   }
/*    */   
/*    */   public String getBaseURI() {
/* 37 */     if (this._baseURL == null)
/* 38 */       return super.getBaseURI(); 
/* 40 */     return this._baseURL.toExternalForm();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\com\ctc\wstx\evt\WNotationDeclaration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */