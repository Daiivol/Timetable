# Timetable
#App Description:

A Simple Timetable App for Bits Pilani Students for helping with erp registeration and creating timetable and checking course clashes.

#App Features:

User can add a new subject with its name, room no. , professor name, and all the periods the subject has classes on then choose to add the subject to the timetable and change the timetable easily for his requirement.

The feature to View Subjects shows all the subjects added by the user.

The user can choose to edit, delete from the added subjects and quickly add or remove it from timetable with a single tap.

The user can view the clashes of subjects with same periods which have been added to the timetable.

Lastly the user can view their timetable.

#App technicalities:

This app was made in android studio using XML, JAVA, and SQL languages.

The MainActivity.class is loaded when app is started from app drawer in phone.

The MainActivity.class loads the activity_main.xml content when app starts and creates a sql database called subjects.db and adds an example subject data inside the database when the app is installed and opened for first time.

The following activites can be opened from MainActivity in order - 

Add_subjects.class

View_subjects.class

View_clashes.class

View_timetable.class



Further detail of each activity class - 

Add_subjects.class  - loads the activity_add_subjects.xml and and content_add_subjects.xml, then saves the xml content using AddAdapter.class into new row of subject.db using DatabaseHelper.class 

View_subjects.class - loads the activity_view_subjects.xml, content_view_subject.xml and view_subject_cardview.xml using SubjectAdapter.class which uses DatabaseHelper.class to get the subjects from databse and show them.

It can also delete a subject directly from the class using DatabaseHelper.class from the cardview itself

It can start the Edit_subjects.class activity which edits the subject in cardview using AddAdapter and DatabaseHelper.class from the cardview itself

It can also add or remove the subject from timetable from cardview itself

It can also start the Add_subject.class activity again using the fab button

View_clashes.class - loads the activity_view_clashes.xml, content_view_clashes.xml and view_clashes_cardview.xml using ClashAdapter.class which uses DatabaseHelper.class to get the clashes of subjects selected to be in the timetable from database and shows the clashes of all courses in timetable.

View_timetable.class - loads view_timetable.xml and directly loads the subjects from databse which are to be in the tietable using DatabaseHelper.class while shifting to landscape orientation for phone.

Note: This activity doesnt start when there are clashes in subjects which are to be shown in the timetable and asks the user to resolve them first.

Subclasses used for data -

Subject.class - used to create an object of subject data

Period.class - used to create an object of period to be saved in periodlist of subject.class

clash.class - used to create an object of clash data

timetable.class - used to create an object for subject data which are to be in timetable



#Database technicalities:

The database was create using SQLiteOpenHelper.

Name of the database create is Subjects.db and the databse verion is 1.

The table "Subjects" created inside has following columns and datatype -

Id - integer primary key

Name - text

Room - text

Prof - text

Day - text

Period - text

InTable - integer

it was created with the following create statement - 

CREATE TABLE Subjects ( Id integer primary key autoincrement , Name text , Room text , Prof text , Day text , Period text , InTable integer );

Note: Since a single subject can have more than one period , the subject name is saved in different row again for different period values.

There are no NULL attributes or data in the database.
