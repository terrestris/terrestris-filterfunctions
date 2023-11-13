package de.terrestris.filterfunctions;

import org.geotools.api.filter.capability.FunctionName;
import org.geotools.filter.FunctionExpressionImpl;
import org.geotools.filter.capability.FunctionNameImpl;

import static org.geotools.filter.capability.FunctionNameImpl.parameter;

/**
 * Filter function that can be used to harness String.format. The three arguments taken are the format string, the fully
 * qualified class name of the parameter and the actual parameter.
 */
public class StringFormatFunction extends FunctionExpressionImpl {

  private static final FunctionName NAME =
    new FunctionNameImpl(
      "stringFormat",
      String.class,
      parameter("format", String.class),
      parameter("class", String.class),
      parameter("double", Object.class));

  public StringFormatFunction() {
    super(NAME);
  }

  @Override
  public String evaluate(Object feature) {
    String format;
    String className;
    Object target;

    try {
      format = getExpression(0).evaluate(feature, String.class);
    } catch (Exception e) {
      throw new IllegalArgumentException(
        "Filter Function problem for function stringFormat argument #0 - expected type String");
    }

    try {
      className = getExpression(1).evaluate(feature, String.class);
    } catch (Exception e) {
      throw new IllegalArgumentException(
        "Filter Function problem for function stringFormat argument #1 - expected type String");
    }

    try {
      target = getExpression(2).evaluate(feature, Class.forName(className));
    } catch (Exception e) {
      throw new IllegalArgumentException(
        "Filter Function problem for function stringFormat argument #2 - expected type " + className);
    }

    return String.format(format, target);
  }

}
