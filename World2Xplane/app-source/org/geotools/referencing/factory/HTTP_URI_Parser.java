/*    */ package org.geotools.referencing.factory;
/*    */ 
/*    */ import org.geotools.resources.i18n.Errors;
/*    */ import org.geotools.util.Version;
/*    */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*    */ 
/*    */ final class HTTP_URI_Parser extends URI_Parser {
/*    */   private static final String AUTHORITY = "http://www.opengis.net/def";
/*    */   
/*    */   private static final String SEPARATOR = "/";
/*    */   
/*    */   private static final String BASE_URI = "http://www.opengis.net/def/";
/*    */   
/*    */   private static final String UNVERSIONED = "0";
/*    */   
/*    */   protected HTTP_URI_Parser(String uri, URI_Type type, String authority, Version version, String code) {
/* 67 */     super(uri, type, authority, version, code);
/*    */   }
/*    */   
/*    */   public static HTTP_URI_Parser buildParser(String uri) throws NoSuchAuthorityCodeException {
/* 80 */     String uriText = uri.trim();
/* 81 */     int length = "http://www.opengis.net/def/".length();
/* 82 */     if (uriText.regionMatches(true, 0, "http://www.opengis.net/def/", 0, length)) {
/* 83 */       String[] segments = uriText.substring(length).split("/");
/* 84 */       if (segments.length == 4 && !segments[0].isEmpty()) {
/* 85 */         URI_Type uriType = URI_Type.get(segments[0]);
/* 86 */         if (uriType != null && !segments[1].isEmpty() && !segments[2].isEmpty() && !segments[3].isEmpty()) {
/* 88 */           String uriAuthority = segments[1];
/* 89 */           Version uriVersion = segments[2].equals("0") ? null : new Version(segments[2]);
/* 91 */           String uriCode = segments[3];
/* 92 */           return new HTTP_URI_Parser(uriText, uriType, uriAuthority, uriVersion, uriCode);
/*    */         } 
/*    */       } 
/*    */     } 
/* 96 */     throw new NoSuchAuthorityCodeException(Errors.format(57, uriText), "http://www.opengis.net/def", uriText);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\HTTP_URI_Parser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */