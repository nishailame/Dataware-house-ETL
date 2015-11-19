#Smart text editor/processor 

Incorporates “intelligent” behaviors of modern-day text interfaces including Autocomplete, Markov text generation, 
Flagging misspelled words and Spelling auto-correct.
If you expand this project you will see several folders including src, data and some library folders. 
src is where all of source (Java) code. A package is just a collection of files that are organized into their own space so they won’t interfere with other files of the same name in other packages. 
The "application" package contains the graphical user interface (GUI) front end for your text editor. 
To run the program follow below path:

MOOCTextEditor-> src -> application -> MainApplication

This app has below features in respective packages:
Part 1: Making Flesch Score Calculation More Efficient – document package
Part 2: Markov Text Generation – TextGen package
Part 3:  Spell Checking and Autocomplete – Spelling package
Part 4:  Spelling Suggestions – Spelling package

I have included Junit testing files in packages, so you may see error. Please follow below steps to get rid of those errors:

•	Add JUnit to your project. To do this, go to Project->Properties. Select "Java Build Path". Select the "Libraries" tab and "Add Library". Select JUnit, then JUnit 4.

•	Set up the workspace JRE. Right click on the project folder in the Package Explorer and select Build Path->Configure Build Path. 
  Go on the Libraries tab and click on "Add Library". Select the "JRE System Library" and click next. Select "Workspace default JRE" and click Finish. Then click OK.
