/*    */ package org.geotools.data.shapefile.shp.xml;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Envelope;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import org.geotools.data.shapefile.files.FileReader;
/*    */ import org.geotools.data.shapefile.files.ShpFileType;
/*    */ import org.geotools.data.shapefile.files.ShpFiles;
/*    */ import org.jdom.Document;
/*    */ import org.jdom.Element;
/*    */ import org.jdom.JDOMException;
/*    */ import org.jdom.input.SAXBuilder;
/*    */ 
/*    */ public class ShpXmlFileReader implements FileReader {
/*    */   Document dom;
/*    */   
/*    */   public ShpXmlFileReader(ShpFiles shapefileFiles) throws JDOMException, IOException {
/* 52 */     SAXBuilder builder = new SAXBuilder(false);
/* 54 */     InputStream inputStream = shapefileFiles.getInputStream(ShpFileType.SHP_XML, this);
/*    */     try {
/* 56 */       this.dom = builder.build(inputStream);
/*    */     } finally {
/* 58 */       inputStream.close();
/*    */     } 
/*    */   }
/*    */   
/*    */   public Metadata parse() {
/* 63 */     return parseMetadata(this.dom.getRootElement());
/*    */   }
/*    */   
/*    */   protected Metadata parseMetadata(Element root) {
/* 67 */     Metadata meta = new Metadata();
/* 68 */     meta.setIdinfo(parseIdInfo(root.getChild("idinfo")));
/* 70 */     return meta;
/*    */   }
/*    */   
/*    */   protected IdInfo parseIdInfo(Element element) {
/* 74 */     IdInfo idInfo = new IdInfo();
/* 76 */     Element bounding = element.getChild("spdom").getChild("bounding");
/* 77 */     idInfo.setBounding(parseBounding(bounding));
/* 79 */     Element lbounding = element.getChild("spdom").getChild("lbounding");
/* 80 */     idInfo.setLbounding(parseBounding(lbounding));
/* 82 */     return idInfo;
/*    */   }
/*    */   
/*    */   protected Envelope parseBounding(Element bounding) {
/* 86 */     if (bounding == null)
/* 87 */       return new Envelope(); 
/* 89 */     double minX = Double.parseDouble(bounding.getChildText("westbc"));
/* 90 */     double maxX = Double.parseDouble(bounding.getChildText("eastbc"));
/* 91 */     double minY = Double.parseDouble(bounding.getChildText("southbc"));
/* 92 */     double maxY = Double.parseDouble(bounding.getChildText("northbc"));
/* 94 */     return new Envelope(minX, maxX, minY, maxY);
/*    */   }
/*    */   
/*    */   public String id() {
/* 98 */     return "Shp Xml Reader";
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\shapefile\shp\xml\ShpXmlFileReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */