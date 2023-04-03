/*    */ package javax.media.jai;
/*    */ 
/*    */ import javax.media.jai.util.Range;
/*    */ 
/*    */ public interface ParameterListDescriptor {
/* 45 */   public static final Object NO_PARAMETER_DEFAULT = (null.class$javax$media$jai$ParameterNoDefault == null) ? (null.class$javax$media$jai$ParameterNoDefault = null.class$("javax.media.jai.ParameterNoDefault")) : null.class$javax$media$jai$ParameterNoDefault;
/*    */   
/*    */   int getNumParameters();
/*    */   
/*    */   Class[] getParamClasses();
/*    */   
/*    */   String[] getParamNames();
/*    */   
/*    */   Object[] getParamDefaults();
/*    */   
/*    */   Object getParamDefaultValue(String paramString);
/*    */   
/*    */   Range getParamValueRange(String paramString);
/*    */   
/*    */   String[] getEnumeratedParameterNames();
/*    */   
/*    */   EnumeratedParameter[] getEnumeratedParameterValues(String paramString);
/*    */   
/*    */   boolean isParameterValueValid(String paramString, Object paramObject);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\ParameterListDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */