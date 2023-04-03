/*    */ package org.geotools.data.ows;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.geotools.ows.ServiceException;
/*    */ 
/*    */ public abstract class GetCapabilitiesResponse extends Response {
/*    */   protected Capabilities capabilities;
/*    */   
/*    */   public GetCapabilitiesResponse(HTTPResponse httpResponse) throws ServiceException, IOException {
/* 39 */     super(httpResponse);
/*    */   }
/*    */   
/*    */   public Capabilities getCapabilities() {
/* 46 */     return this.capabilities;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\GetCapabilitiesResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */