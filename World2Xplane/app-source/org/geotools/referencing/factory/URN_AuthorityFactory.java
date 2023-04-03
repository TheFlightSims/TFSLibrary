/*    */ package org.geotools.referencing.factory;
/*    */ 
/*    */ import org.geotools.factory.Hints;
/*    */ import org.geotools.metadata.iso.citation.Citations;
/*    */ import org.opengis.metadata.citation.Citation;
/*    */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*    */ 
/*    */ public class URN_AuthorityFactory extends Abstract_URI_AuthorityFactory {
/*    */   public static final String HINTS_AUTHORITY = "urn";
/*    */   
/*    */   public URN_AuthorityFactory() {
/* 53 */     super("urn");
/*    */   }
/*    */   
/*    */   public URN_AuthorityFactory(Hints userHints) {
/* 62 */     super(userHints, "urn");
/*    */   }
/*    */   
/*    */   public URN_AuthorityFactory(AllAuthoritiesFactory factory) {
/* 71 */     super(factory);
/*    */   }
/*    */   
/*    */   public Citation getAuthority() {
/* 79 */     return Citations.URN_OGC;
/*    */   }
/*    */   
/*    */   protected URI_Parser buildParser(String code) throws NoSuchAuthorityCodeException {
/* 87 */     return URN_Parser.buildParser(code);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\URN_AuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */