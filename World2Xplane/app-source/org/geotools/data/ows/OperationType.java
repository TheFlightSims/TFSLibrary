/*    */ package org.geotools.data.ows;
/*    */ 
/*    */ import java.net.URL;
/*    */ import java.util.List;
/*    */ 
/*    */ public class OperationType {
/*    */   protected List<String> formats;
/*    */   
/*    */   protected URL get;
/*    */   
/*    */   protected URL post;
/*    */   
/*    */   public List<String> getFormats() {
/* 42 */     return this.formats;
/*    */   }
/*    */   
/*    */   public void setFormats(List<String> formats) {
/* 46 */     this.formats = formats;
/*    */   }
/*    */   
/*    */   public URL getGet() {
/* 53 */     return this.get;
/*    */   }
/*    */   
/*    */   public void setGet(URL get) {
/* 56 */     this.get = get;
/*    */   }
/*    */   
/*    */   public URL getPost() {
/* 63 */     return this.post;
/*    */   }
/*    */   
/*    */   public void setPost(URL post) {
/* 67 */     this.post = post;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\ows\OperationType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */