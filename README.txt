INSTRUCTIONS:

Import the  Project
	From Eclipse select "File" -> "New" and select Java project
		Uncheck "Use default location" to specify the root directory of the assesment test.
		You should use JDK 1.7 or greater for this test (1.6 may work but is untested)
	Once the project is open right click on the project in the "Package Explorer" pane in Eclipse
		Select "Configure" -> "Convert to Maven Project"
		Refresh the project once the dependencies are downloaded
		
In the Package Explorer window in Eclipse, open the "src" folder.
	Right click on "tst.skubana", select "Run As", and select "JUnit Test"
	
In the JUnit window you should see Runs at 6/6, Errors at 3, and Failures at 2
	If you don't see these numbers or were unable to get to this step, there may be a problem in your project setup. 
	Please contact the test administrator before proceeding.

Some notes:
	-Review db.sql and data.sql in the data directory. The first file defines the database structure, the second populates the database with test data.
		-You'll need to review the first file when writing SQL queries to accomplish the PerformanceDataDao solution.
	-There is Javadoc defined for all of the Interfaces. 
		-You can review this by hovering over the methods from your Eclipse IDE or by opening the Interface files directly.
	-The architecture of this setup is based on Spring, but you should not need to write any Spring code to accomplish the task. 
		-Focus on the areas marked TODO. These can be listed in the Eclipse view called "Tasks"
	-You should only need to modify the code in the "com.skubana.solution" package. Do not modify the code in any other area.	

