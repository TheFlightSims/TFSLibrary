package scala.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ScalaLongSignature {
  String[] bytes();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\reflect\ScalaLongSignature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */