/*    */ package org.geotools.data.ows;
/*    */ 
/*    */ import java.net.URL;
/*    */ 
/*    */ public abstract class AbstractGetCapabilitiesRequest extends AbstractRequest implements GetCapabilitiesRequest {
/*    */   public static final String SERVICE = "SERVICE";
/*    */   
/*    */   public AbstractGetCapabilitiesRequest(URL serverURL) {
/* 41 */     super(serverURL, null);
/*    */   }
/*    */   
/*    */   protected void initRequest() {
/* 51 */     setProperty("REQUEST", "GetCapabilities");
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\AbstractGetCapabilitiesRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */