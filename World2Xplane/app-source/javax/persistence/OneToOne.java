package javax.persistence;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OneToOne {
  Class targetEntity() default void.class;
  
  CascadeType[] cascade() default {};
  
  FetchType fetch() default FetchType.EAGER;
  
  boolean optional() default true;
  
  String mappedBy() default "";
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\persistence\OneToOne.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */