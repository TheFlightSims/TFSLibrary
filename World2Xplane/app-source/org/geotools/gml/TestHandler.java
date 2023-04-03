/*    */ package org.geotools.gml;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import org.xml.sax.helpers.XMLFilterImpl;
/*    */ 
/*    */ public class TestHandler extends XMLFilterImpl implements GMLHandlerJTS {
/*    */   public void geometry(Geometry geometry) {
/* 36 */     System.out.println("here is the geometry: " + geometry.toString());
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\gml\TestHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */