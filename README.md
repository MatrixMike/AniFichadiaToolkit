AniFichadiaToolkit
==================

Java Toolkit making life simpler for developers. 

The toolkit is a subset of classes, utilities, helpers, etc. that I've developed, used and refined over the years. I'm constantly looking to improve, add to and refine the toolkit, so please provide suggestions, comments, enhancements, etc.

More features will be made available shortly. The toolkit is compatible with Java 6 and Java 7, and works with Android (I'll be releasing an Android Toolkit shortly). For older Java versions, the source code should be supported, however this is untested.

This repository contains:
- Fully Documented Source Code,
- JavaDoc, and
- a JAR file (including one with JavaDoc) for your convenience and reference

The code is "mostly" compliant with Google Java Style (http://google-styleguide.googlecode.com/svn/trunk/javaguide.html), however there are some things that I find a little cringeworthy and in my opinion reduce readability (using Egyptian braces on classes and methods), so they've been excluded. However, you can use the Java Code Formatter in your IDE of choice (I use Eclipse with a custom formatter profile) to format it as required.

Components (and packages)
------------------------
### Simpler File management and operations (com.anifichadia.toolkit.file)
  -   IO (reading and writing objects to files, as well as flattening to byte arrays) (com.anifichadia.toolkit.file.io)
  -   Sorting (com.anifichadia.toolkit.file.comparators)
  -   Filtering (com.anifichadia.toolkit.file.filters)
  -   File operations (com.anifichadia.toolkit.file)

### Simpler coordinates and geometry (com.anifichadia.toolkit.geometry)
Coordinate2D is a class that allows double precision coordinates. Developers can use this class to calculate the Euclidean distance between coordinates, rotation around the origin and arbitrary points, and calculate angles between coordinates.

Alternatively, you could use Java's Point2D class, however, this is basic and does not contain the features of Coordinate2D. To facilitate compatibility with alternative coordinate/point implementations, refer to the MathUtilities class, or use Coordinate2D for the required operations.

### Simpler Command Line Argument Parsing (com.anifichadia.toolkit.program)
  - The provided classes reduce the need to implement custom command line argument parsers. Instead a framework has been provided to parse command line arguments and build help information for programs.

### Simpler RNG (com.anifichadia.toolkit.random)
  - A wrapper for Java's Random class has been provided, encapsulating multiple common random number generation methods, including generating numbers within a specified ranges. RNG methods have been provided for integers, floats, doubles, booleans, and gaussian (normal) distributions, including the standard normal distribution.

### Statistics (com.anifichadia.toolkit.math.statistics)
  - The statistics package has been designed to provide common statistics data (mean, variance, standard deviation, median) for a provided data set. Currently, the calculations are designed for the population of the dataset, not a sample, however, this feature will be implemented soon.

### Utilities (com.anifichadia.toolkit.utilities)


Example Usages
-----------------------
Example code has been written to conform with Java 6.

###Simple Object IO

```java
SerializableFileManager<SomeObject> fileManager = new SerializableFileManager<SomeObject>();
SomeObject obj = fileManager.readFromFile("/some/path/here");
if (obj == null) {
	// Object read failed, some error
} else {
	// Object read success
}
// ... some other code ...

boolean writeSuccess = fileManager.writeToFile("/some/other/path/here", obj, true);
System.out.println("Write success: " + writeSuccess);
```

###Simple Argument Parsing
```java
// Setup arguments
Argument inFileArg = new Argument (ArgType.SINGLE_VALUE, "-f", "File", true, "The input file path");
Argument outFileArg = new Argument (ArgType.SINGLE_VALUE, "-o", "Output File", true, "The output file path");
Argument someMultiValueArg = new Argument (ArgType.MULTI_VALUE, "-m",
		"Some Multi Value Argument", true, "Some Multi Value Argument");
Argument helpArg = new Argument (ArgType.FLAG, "-h", "Help/Usage", false,
		"Help/Usage instructions");
Argument[] arguments = {inFileArg, outFileArg, someMultiValueArg, helpArg};

// Extract and process params
Hashtable<Argument, String> argVals = null;
try {
	System.out
			.println ("============================= Arguments ===============================");
	argVals = ArgumentParser.parseArguments (arguments, args);
} catch (Exception e) {
	System.out.println (e.getMessage ());
	System.exit ( -1);
} finally {
	System.out
			.println ("=========================== End Arguments =============================");
}

// Extract arguments into variables
String inFilePath = argVals.get (inFileArg);
String outFilePath = argVals.get (outFileArg);
String[] someMultiValue = argVals.get (someMultiValueArg).split (",");
boolean helpFlag = Boolean.parseBoolean (argVals.get (helpArg));

// Print/do something with arguments
if (helpFlag) {
	System.out.println ("");
	System.out.println (ArgumentParser.generateHelp ("", arguments, ""));
	System.out.println ("");
}

System.out.println ("inFilePath: " + inFilePath);
System.out.println ("outFilePath: " + outFilePath);
System.out.println ("someMultiValue: " + Arrays.toString (someMultiValue));
System.out.println ("helpFlag: " + helpFlag);
```
Used arguments
```
-f "/some/input/file" -o "/some/output/file" -m 1 -m 2 -m 3 -h
```

With output:
```
============================= Arguments ===============================
File                            :	 /some/input/file
Output File                     :	 /some/output/file
Some Multi Value Argument       :	 1
Some Multi Value Argument       :	 1,2
Some Multi Value Argument       :	 1,2,3
Help/Usage                      :	 true
=========================== End Arguments =============================

Arguments:
	-f - File (Type: Single value):
			The input file path
	-o - Output File (Type: Single value):
			The output file path
	-m - Some Multi Value Argument (Type: Multi value):
			Some Multi Value Argument
	-h - Help/Usage (Type: Flag):
			Help/Usage instructions

inFilePath: /some/input/file
outFilePath: /some/output/file
someMultiValue: [1, 2, 3]
helpFlag: true
```

###Statistics
```java
double[] data = new double[] {5, 3, 1, 4, 2};
Statistics stat = new Statistics (data);

System.out.println ("Original Array: " + Arrays.toString (data));
System.out.println ("Size: " + stat.getSize ());
System.out.println ("Mean: " + stat.getMean ());
System.out.println ("Variance: " + stat.getVariance ());
System.out.println ("Standard Deviation: " + stat.getStdDev ());
System.out.println ("Median: " + stat.getMedian ());
```

With output
```
Original Array: [5.0, 3.0, 1.0, 4.0, 2.0]
Size: 5
Mean: 3.0
Variance: 2.0
Standard Deviation: 1.4142135623730951
Median: 3.0
```

Acknowledgements
----------------
- Stephen Kelvin Friedrich for the natural order sorting algorithm. Code is used under a BSD license
	http://www.eekboom.com/java/compareNatural/src/com/eekboom/utils/Strings.java

Upcomming Goodies/TODO
----------------------
- Add statistics for a sample
- Include testing packages
