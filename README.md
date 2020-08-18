# cellphone
Cellphone project for WCF
https://github.com/WCF-Insurance/java-developer-cell-phone-usage

# Stories
| Story Num |Story |
|---|---| 
|1 |Generate cell phone report for a given year.|
|2 |Report will include totals header section to include the following:| 
| | * Report Run Date| 
| | * Number of phones|
| | * Total Minutes| 
| | * Total Data|
| | * Average Minutes| 
| | * Average Data|
|3 |Report will include details section to include the following:|
| | * Employee Id| 
| | * Employee Name|
| | * Model| 
| | * Purchase Date|
| | * Minutes Used By Month|
| | * Minutes Used By Month|
|4 |Print report to a local printer|


# Design
* Report year will be passed in on command line. 
* Data comes from CSV files.
* Use configuration files for location of CSV files.
* Use Streams for parsing and processing as data size is unknown.
* All Data will be aggreated at time of parsing based on year of report   
* Report will be generated as a PDF.  Provides more flexibility for output.
* Location of PDF will be configurable through app configuration
* Shell out from application and open PDF in browser.

# Implementation/Architecture
* Implement as a executable jar file
* Use Spring boot version 2.3.3
* Use PDF generation library ??? version 

# ToDo

    
# Q&A
* Clarify requirement under Details Section "For each company cell phone...".
  Requirement does not match data in details section.
*  

# Running the app

# Project Status
