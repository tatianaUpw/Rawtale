package tests.core;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Visibility {
    enum VisibilityOptions { REQUIRED, OPTIONAL, ABSENT, DYNAMIC }
    VisibilityOptions   VisibilityOnPage();


}
