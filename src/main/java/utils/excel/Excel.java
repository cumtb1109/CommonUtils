package utils.excel;


import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Excel {
    int sort() default 0;

    String name();

    String destExcelName();

}
