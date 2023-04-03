/*    */ package org.geotools.referencing.factory;
/*    */ 
/*    */ import org.geotools.factory.Hints;
/*    */ import org.geotools.metadata.iso.citation.Citations;
/*    */ import org.opengis.metadata.citation.Citation;
/*    */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*    */ 
/*    */ public class HTTP_URI_AuthorityFactory extends Abstract_URI_AuthorityFactory {
/*    */   public static final String HINTS_AUTHORITY = "http-uri";
/*    */   
/*    */   public HTTP_URI_AuthorityFactory() {
/* 51 */     super("http-uri");
/*    */   }
/*    */   
/*    */   public HTTP_URI_AuthorityFactory(Hints userHints) {
/* 60 */     super(userHints, "http-uri");
/*    */   }
/*    */   
/*    */   public HTTP_URI_AuthorityFactory(AllAuthoritiesFactory factory) {
/* 69 */     super(factory);
/*    */   }
/*    */   
/*    */   public Citation getAuthority() {
/* 77 */     return Citations.HTTP_URI_OGC;
/*    */   }
/*    */   
/*    */   protected URI_Parser buildParser(String code) throws NoSuchAuthorityCodeException {
/* 85 */     return HTTP_URI_Parser.buildParser(code);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\HTTP_URI_AuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */