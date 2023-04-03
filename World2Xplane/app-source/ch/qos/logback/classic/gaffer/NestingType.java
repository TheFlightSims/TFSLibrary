package ch.qos.logback.classic.gaffer;

import groovy.lang.GroovyObject;
import groovy.lang.MetaClass;
import java.lang.ref.SoftReference;
import org.codehaus.groovy.reflection.ClassInfo;
import org.codehaus.groovy.runtime.BytecodeInterface8;
import org.codehaus.groovy.runtime.ScriptBytecodeAdapter;
import org.codehaus.groovy.runtime.callsite.CallSite;
import org.codehaus.groovy.runtime.callsite.CallSiteArray;
import org.codehaus.groovy.runtime.typehandling.DefaultTypeTransformation;

public enum NestingType implements GroovyObject {
  NA, SINGLE, AS_COLLECTION;
  
  public static final NestingType MIN_VALUE;
  
  public static final NestingType MAX_VALUE;
  
  NestingType() {
    MetaClass metaClass = $getStaticMetaClass();
    this.metaClass = metaClass;
  }
  
  static {
    __$swapInit();
    long l1 = 0L;
    __timeStamp__239_neverHappen1368189247426 = l1;
    long l2 = 1368189247426L;
    __timeStamp = l2;
    Object object1 = $getCallSiteArray()[13].callStatic(NestingType.class, "NA", Integer.valueOf(0));
    NA = (NestingType)ScriptBytecodeAdapter.castToType(object1, NestingType.class);
    Object object2 = $getCallSiteArray()[14].callStatic(NestingType.class, "SINGLE", Integer.valueOf(1));
    SINGLE = (NestingType)ScriptBytecodeAdapter.castToType(object2, NestingType.class);
    Object object3 = $getCallSiteArray()[15].callStatic(NestingType.class, "AS_COLLECTION", Integer.valueOf(2));
    AS_COLLECTION = (NestingType)ScriptBytecodeAdapter.castToType(object3, NestingType.class);
    NestingType nestingType1 = NA;
    MIN_VALUE = nestingType1;
    NestingType nestingType2 = AS_COLLECTION;
    MAX_VALUE = nestingType2;
    NestingType[] arrayOfNestingType = { NA, SINGLE, AS_COLLECTION };
    $VALUES = arrayOfNestingType;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\ch\qos\logback\classic\gaffer\NestingType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */