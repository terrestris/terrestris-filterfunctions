## terrestris GeoTools filter functions

Prerequisites: Java 11 and maven 3.5

* build with `mvn install`
* copy into Geoserver's `WEB-INF/lib` to enable

After that, you currently have the following extra functions available:

### stringFormat

A function that utilizes Java's String.format function for better control of string conversions. It takes three parameters:

* format - the format string as per https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Formatter.html#syntax
* class - the (fully qualified) class name of the parameter value, eg. `java.lang.Double` 
* target - the target value

Example:

`CQL_FILTER = stringFormat('%f', 'java.lang.Double', some_attribute) ilike '%123%'`
