/*    */ package org.geotools.filter.expression;
/*    */ 
/*    */ import org.geotools.factory.Hints;
/*    */ import org.xml.sax.helpers.NamespaceSupport;
/*    */ 
/*    */ public interface PropertyAccessorFactory {
/* 39 */   public static final Hints.Key NAMESPACE_CONTEXT = new Hints.Key(NamespaceSupport.class);
/*    */   
/*    */   PropertyAccessor createPropertyAccessor(Class<?> paramClass1, String paramString, Class<?> paramClass2, Hints paramHints);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\filter\expression\PropertyAccessorFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */