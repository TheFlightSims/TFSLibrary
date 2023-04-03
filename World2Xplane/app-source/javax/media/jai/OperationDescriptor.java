/*    */ package javax.media.jai;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.util.Locale;
/*    */ import java.util.ResourceBundle;
/*    */ 
/*    */ public interface OperationDescriptor extends RegistryElementDescriptor {
/* 77 */   public static final Object NO_PARAMETER_DEFAULT = ParameterListDescriptor.NO_PARAMETER_DEFAULT;
/*    */   
/*    */   String[][] getResources(Locale paramLocale);
/*    */   
/*    */   ResourceBundle getResourceBundle(Locale paramLocale);
/*    */   
/*    */   int getNumSources();
/*    */   
/*    */   Class[] getSourceClasses(String paramString);
/*    */   
/*    */   String[] getSourceNames();
/*    */   
/*    */   Class getDestClass(String paramString);
/*    */   
/*    */   boolean validateArguments(String paramString, ParameterBlock paramParameterBlock, StringBuffer paramStringBuffer);
/*    */   
/*    */   boolean isImmediate();
/*    */   
/*    */   Object getInvalidRegion(String paramString, ParameterBlock paramParameterBlock1, RenderingHints paramRenderingHints1, ParameterBlock paramParameterBlock2, RenderingHints paramRenderingHints2, OperationNode paramOperationNode);
/*    */   
/*    */   PropertyGenerator[] getPropertyGenerators();
/*    */   
/*    */   boolean isRenderedSupported();
/*    */   
/*    */   Class[] getSourceClasses();
/*    */   
/*    */   Class getDestClass();
/*    */   
/*    */   boolean validateArguments(ParameterBlock paramParameterBlock, StringBuffer paramStringBuffer);
/*    */   
/*    */   boolean isRenderableSupported();
/*    */   
/*    */   Class[] getRenderableSourceClasses();
/*    */   
/*    */   Class getRenderableDestClass();
/*    */   
/*    */   boolean validateRenderableArguments(ParameterBlock paramParameterBlock, StringBuffer paramStringBuffer);
/*    */   
/*    */   int getNumParameters();
/*    */   
/*    */   Class[] getParamClasses();
/*    */   
/*    */   String[] getParamNames();
/*    */   
/*    */   Object[] getParamDefaults();
/*    */   
/*    */   Object getParamDefaultValue(int paramInt);
/*    */   
/*    */   Number getParamMinValue(int paramInt);
/*    */   
/*    */   Number getParamMaxValue(int paramInt);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\OperationDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */