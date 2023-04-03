/*     */ package org.geotools.ows;
/*     */ 
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class ServiceException extends SAXException {
/*     */   public static final String INVALID_FORMAT = "InvalidFormat";
/*     */   
/*     */   public static final String INVALID_SRS = "InvalidSRS";
/*     */   
/*     */   public static final String INVALID_CRS = "InvalidCRS";
/*     */   
/*     */   public static final String LAYER_NOT_DEFINED = "LayerNotDefined";
/*     */   
/*     */   public static final String STYLE_NOT_DEFINED = "StyleNotDefined";
/*     */   
/*     */   public static final String LAYER_NOT_QUERYABLE = "LayerNotQueryable";
/*     */   
/*     */   public static final String CURRENT_UPDATE_SEQUENCE = "CurrentUpdateSequence";
/*     */   
/*     */   public static final String INVALID_UPDATE_SEQUENCE = "InvalidUpdateSequence";
/*     */   
/*     */   public static final String MISSING_DIMENSION_VALUE = "MissingDimensionValue";
/*     */   
/*     */   public static final String INVALID_DIMENSION_VALUE = "InvalidDimensionValue";
/*     */   
/*     */   public static final String OPERATION_NOT_SUPPORTED = "OperationNotSupported";
/*     */   
/*  80 */   private static final long serialVersionUID = "org.geotools.data.ows.ServiceException".hashCode();
/*     */   
/*  81 */   private String code = "";
/*     */   
/*  82 */   private String locator = null;
/*     */   
/*     */   private ServiceException next;
/*     */   
/*     */   private ServiceException() {
/*  86 */     super("");
/*     */   }
/*     */   
/*     */   public ServiceException(String msg) {
/*  95 */     super(msg);
/*     */   }
/*     */   
/*     */   public ServiceException(String msg, String code) {
/*  99 */     super(msg);
/* 100 */     this.code = code;
/*     */   }
/*     */   
/*     */   public ServiceException(String msg, String code, String locator) {
/* 112 */     super((msg == null) ? code : msg);
/* 113 */     this.code = code;
/* 114 */     this.locator = locator;
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 121 */     return this.code;
/*     */   }
/*     */   
/*     */   public String getLocator() {
/* 128 */     return this.locator;
/*     */   }
/*     */   
/*     */   public ServiceException getNext() {
/* 132 */     return this.next;
/*     */   }
/*     */   
/*     */   public void setNext(ServiceException next) {
/* 135 */     this.next = next;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\ows\ServiceException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */