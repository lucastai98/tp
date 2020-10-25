---
layout: page
title: User Guide
---

# User Guide for Taskmania

Taskmania (based off AB3) is a **desktop app for a project leader to manage team members and tasks** to be finished in a
 software project, optimized for use via a Command Line Interface (CLI) while still having the benefits of a 
 Graphical User Interface (GUI). If you can type fast, Taskmania can allow you to manage your team faster than 
 a traditional point and click interface.

## Contents
- **Quickstart**
- **Features in global scope**
  - Get help `help` 
  - Exit application `exit`
  - Start working on an existing project `start `
  - Add a new project to the catalogue `add `
  - Delete a project from the catalogue `delete `
  - List all projects `list `
  - Locate projects with matching keywords `find ` 
  - Edit details of a project `edit `

- **Features in project scope**
  - Task related features
    - Add a task to a project `addtask `
    - Assign a task to a teammate `assign `
    - Edit a task `edittask `
    - Filter tasks by various aspects `filtert `
    - View details of a task `viewtask `
    - List all tasks `allt `
    - Return to project view from task view `leaveTaskView `
  - Teammate related features
    - Create a new teammate in a project `newteammate `
    - Edit a teammate's details `editteammate `
    - View a teammate's details `viewteammate `
    - Return to project view from teammate view `leaveTeammateView `
- **Summary**

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure that you have Java `11` or above installed in your Computer.

2. Download the latest `Taskmania.jar` from [here](https://github.com/AY2021S1-CS2103T-W10-3/tp).

3. Copy the file to the folder you want to use as the _home folder_ for your TaskMania.

4. Double-click the file to start the app. The window that appears will be similar to the below should appear in a few seconds. Note how
 the app contains some sample information.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
 open the help window.<br>
   Some commands you can try:

   * **`start 1 `** : Opens the first project

   * **`exit`** : Exits the app.

6. Refer to the Features below for details of each command.

--------------------------------------------------------------------------------------------------------------------

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* One and only one Item in parenthesis should be supplied by the user
  e.g. `(ta/ASSIGNEE NAME) (td/DEADLINE) (tn/TASK NAME)` can be used as "ta/Alice", "td/31-12-2020 10:00:00" or as "tn/group meeting", but not as "ta/Alice td/31-12-2020 10:00:00" or "".

* Items in square brackets are optional.<br>
  e.g `n/NAME [tg/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[tg/TAG]…​` can be used as ` ` (i.e. 0 times), `tg/friend`, `tg/friend tg/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.
  
**:information_source: Notes about scoping:**<br>

The hierarchy of command scoping is as follows:
* `CATALOGUE` (i.e. global)
  * `PROJECT`
    * `TASK`
    * `Meeting`
  * `TEAMMATE`

<br>A lower-level scope always belongs to any parent scopes. For example, if the app is currently in `PROJECT`
scope, it is also in the `CATALOGUE` scope. However, it is not necessarily in `TASK` scope because `TASK` is
a child level of `PROJECT` and it is definitely not in `PERSON` scope because `PERSON` is parallel to `PROJECT`.

</div>

--------------------------------------------------------------------------------------------------------------------

# **Features** in global scope

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Exit application : `exit`

Closes the application for the user.

Format: `exit

### Start work on an existing project `start `
Initialises the project specified.

Format: `start (INDEX)`
- Initialises the project at the specified INDEX.
- The index refers to the index number shown in the displayed project list.
- The index must be a positive integer 1, 2, 3, …​

Examples: `start 2` Initialises the second project in the project list.

### Add a new project to the catalogue `add `
Adds a project to the project list.

Format: `add (n/PROJECT NAME) (dl/DEADLINE) (ru/REPO URL) (d/PROJECT DESCRIPTION) [tg/TAGS...] `
  - The fields can be entered in any order, as long as the prefixes are matched correctly
  - Project Name can be any alphanumeric value (containing only alphabets and / or numbers).
  - Repo URL must be a valid link
  - Description can be anything, as long as it is not blank.
  - Any number of tags can be added, separated by space " ".

Example: `add n/Blair project dl/29-02-2020 00:00:00 ru/http://github.com/a/b.git d/Coding in Greenwich tg/challenging hell` 

Adds a new project with the 
- projectName Blair project 
- deadline of 29 February 2020 midnight 
- URL for the team repository 
- Coding in Greenwich as the description 
- 2 tags "challenging" and "hell".

### Delete a project from the catalogue `delete `

Deletes a project and all associated information from the project catalogue

Format: `delete (INDEX) `
- Initialises the project at the specified INDEX.
- The index refers to the index number shown in the displayed project list.
- The index must be a positive integer 1, 2, 3, …​

Examples: `delete 2` deletes the second project from the catalogue.

### List all projects in the catalogue `list `

List all projects currently in the project catalogue

Format: `list `
- Lists all projects if there are projects in the catalogue

Example: `list ` lists all projects in the catalogue to the user.

#### Locate projects by keyword `find `

Finds projects whose names contain the given keywords.

Format: `find [KEYWORD...]`
- The search is case-insensitive. e.g run will match Run.
- Only the name of the projects are searched.
- Can be multiple words.

Example: `find scare` would return the **Scare House** and **Easily scare Night** projects.

Outcome: The projects with matching names will be shown to the user.

### Edits details of a project `edit `

Updates the details of a project.

Format: `edit [n/PROJECT NAME] [dl/DEADLINE] [ru/REPO URL] [d/PROJECT DESCRIPTION] [tg/TAGS...] `
  - Any combination of the fields above can be entered.
  - The information entered will replace all the data in each respective field.
  - Project Name can be any alphanumeric value (containing only alphabets and / or numbers).
  - Repo URL must be a valid link
  - Description can be anything, as long as it is not blank.
  - Any number of tags can be added, separated by space " ".

Example: `edit n/Resident Evil project /d new horror` changes the name of the project to **Evil project**, and the description to **new horror**`

# **Features** in project scope

## **Task** related features

### Add task to a project `addtask `

Creates a new task and adds it to the current project.

Format: `addtask (n/TASK NAME) (tp/TASK PROGRESS (done/TASK STATUS) (td/TASK DEADLINE) `
  - All fields above are required
  - Task Name can be any alphanumeric value (containing only alphabets and / or numbers).
  - Task status is simply *true* to signify the task is completed or *false* otherwise.
  - Task progress is a percentage value indicating how much of the task is done.
  - Task deadline is indicated by a date and time with format *DD-MM-YYYY hh:mm:ss* 

Example: `addtask n/Do User Guide tp/30 done/done td/29-02-2020 00:00:00` creates a task named Do User Guide, 30% completed, is completed, and has a deadline of 29th Feb 2020, midnight.

### Assign a task to a teammate `assign `

Assigns a task to a teammate within a project

Format: `assign TASKINDEX TEAMMATE_GIT_USERNAME  ` (teammate git username is the unique name of each teammate)

Example: `assign 3 Lucas98` assigns task number 3 in the list to user *Lucas98*.

### Edit task to a project `addtask `

Creates a new task and adds it to the current project.

Format: `edittask (INDEX) [n/TASK_NAME] [tp/TASK_PROGRESS] [done/TASK_STATUS] [td/TASK_DEADLINE] `
  - INDEX field is necessary to include.
  - Any combination and any number of the subsequent fields above can be entered.
  - The information entered will replace all the data in each respective field.
  - Task Name can be any alphanumeric value (containing only alphabets and / or numbers).
  - Task status is simply *true* to signify the task is completed or *false* otherwise.
  - Task progress is a percentage value indicating how much of the task is done.
  - Task deadline is indicated by a date and time with format *DD-MM-YYYY hh:mm:ss* 

Example: `edittask 3 tn/Finish project status/true` changes the name of task 3 in the list to Finish project, and the done status to true, indicating the task is completed.

### Filter tasks `filtert `

Filters tasks in the task list by various predicates:
  - by assignee's name
  - by deadline
  - by done status
  - by progress
  - by task's name

Format: `filtert [ta/TASK_ASSIGNEE_NAME] [td/DEADLINE] [done/ DONE_STATUS] [tp/TASK PROGRESS] [tn/TASK_NAME]` 
  - User may choose one predicate to filter tasks by
  - Assignee name is the name of the Teammate who is assigned to the task
  - Deadline of the task follows the format *DD-MM-YYYY hh:mm:ss*
  - Task status is simply *true* to signify the task is completed or *false* otherwise.
  - Task progress is a percentage value indicating how much of the task is done.
  - Task Name can be any alphanumeric value (containing only alphabets and / or numbers).
-  

Example: `filtert done/true` filters all the tasks that are done, and displays the done tasks to the user.

### View details of a task `viewtask `

View all the details of a task, beyond the little information given in the project view.

Format: `viewtask INDEX `
  - View all the information of the task specified by the INDEX. 
  - Index has to be a valid number that is in the range of tasks displayed on screen.

Example: `viewtask 4` displays all information from task number 4 in the list.

### List all tasks `allt `

List all tasks in the task list of a project

Enters the Task scope.

Format: `allt `

Example: `allt` displays all tasks in the task list.

### Return to project view from task view `leaveTaskView`

Change the view on the screen to project view, when previously on task view.

Format: `leaveTaskView`

Example: `leaveTaskView` leaves the view of tasks, and reenters the project view.

## **Teammate** related features

### Create a new teammate in a project `newteammate`

Creates a new teammate in a project with all the relevant fields contained in it.

Format: `newteammate (mn/TEAMMATE_NAME) (mg/GIT_USER_NAME) (mp/PHONE_NUMBER) (me/EMAIL) (ma/ADDRESS)`
  - All fields are necessary to fill in
  - Teammate name has to be 1 or more words consisting only of letters.
  - The Git User name has to be a unique Github registered User Name
  - The phone number has to be a minimum of 3 and maximum of 16 numbers.
  - The email has to have a proper prefix and proper domain name consisting of at least 2 letters.
  - Address can be any amount of letters, symbols and numbers, the only constraint is that it cannot be blank.


Example: `newteammate mn/Lucas mg/LucasTai98 mp/93824823 me/lucas@gmail.com ma/18 Evelyn Road` creates a new teamamte in the respective project with:
  - name Lucas
  - Git name of Lucas98
  - phone number of 93824823
  - email of lucas@gmail.com
  - address of 18 Evelyn road
  
### Edit a teammate’s details `editteammate`

Update the information of a teammate.

Format: `editteammate (GIT_USER_NAME) [mn/TEAMMATE_NAME] [mp/PHONE_NUMBER] [me/EMAIL] [ma/ADDRESS]`
  - Any combination or number of fields can be filled in.
  - Teammate name has to be 1 or more words consisting only of letters.
  - The Git User name cannot be changes, but is required to identify the teammate to edit.
  - The phone number has to be a minimum of 3 and maximum of 16 numbers.
  - The email has to have a proper prefix and proper domain name consisting of at least 2 letters.
  - Address can be any amount of letters, symbols and numbers, the only constraint is that it cannot be blank.


Example: `editteammate Lucas98 tn/GeNiaaz ta/5 Hacker Way` changes the name of the teammate to GeNiaaz and the address of said teammate to 5 Hacker Way.

### View a teammate’s details `viewteammate`

View all of a specific teammate's details

Format: `viewteammate GIT_USER_NAME`

Example: `viewteammate Lucas98` displays all the information about the teammate with the Git User Name Lucas 98 to the user.

### Return to project view from teammate view `leaveTeammateView`

Change the view on the screen to project view, when previously on teammate view.

Format: `leaveTeammateView`

Example: `leaveTeammateView` leaves the view of teammates, and reenters the project view.

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that 
contains the data of your previous Taskmania home folder.

## Summary

Action | Format, Examples | Scope
--------|------------------|-------
**Get Help** | `help` | global scope
**Exit application** | `exit` | global scope
**Start** | `start INDEX`<br> e.g., `start 3` | global scope
**Add** | `add (n/PROJECT NAME) (dl/DEADLINE) (ru/REPO URL) (d/PROJECT DESCRIPTION) [tg/TAGS...] `   eg, `add n/Blair project dl/29-02-2020 00:00:00 ru/http://github.com/a/b.git d/Coding in Greenwich tg/challenging hell` | global scope
**Delete project** | `delete INDEX` <br> eg. `delete 2` | global scope
**Show all projects** | `list` | global scope 
**Find KEYWORD** | `find KEYWORD` <br> eg. `find read` | global scope
**edit** | `edit [n/PROJECT NAME] [dl/DEADLINE] [ru/REPO URL] [d/PROJECT DESCRIPTION] [tg/TAGS...] ` eg, `edit n/Resident Evil project /d new horror`| global scope
**Add Task** | `addtask (n/TASK NAME) (tp/TASK PROGRESS (done/TASK STATUS) (td/TASK DEADLINE) ` eg, `addtask n/Do User Guide tp/30 done/done td/29-02-2020 00:00:00` | project scope
**Assign A Task To A Teammate** | `assign INDEX NAME` <br> e.g. `assign 1 Niaaz` | project scope
**Edit task details** | `edittask (INDEX) [n/TASK_NAME[ [tp/TASK_PROGRESS] [done/TASK_STATUS] [td/TASK_DEADLINE] ` eg, `edittask 3 tn/Finish project status/true` | project scope
**Filter Tasks by Assignee/Deadline/Task Name** | ``filtert (ta/ASSIGNEE NAME) **OR** (td/DEADLINE) **OR** (tn/TASK NAME) **OR** (tp/PROGRESS) **OR** (done/ISDONE)``<br>e.g. `filtert ta/Alice` | project scope
**View Details of A Task** | `viewtask INDEX` <br> eg. `viewtask 1` | project scope
**Show all the tasks** | `allt` | project scope 
**Leave Task view to go to Project view** | `leavetaskview` | project scope
**Create new teammate** | `newteammate (mn/TEAMMATE_NAME) (mg/GIT_USER_NAME) (mp/PHONE_NUMBER) (me/EMAIL) (ma/ADDRESS)` eg, `newteammate mn/Lucas mg/LucasTai98 mp/93824823 me/lucas@gmail.com ma/18 Evelyn Road`| project scope
**Edit teammate details** | `editteammate (GIT_USER_NAME) [mn/TEAMMATE_NAME] [mp/PHONE_NUMBER] [me/EMAIL] [ma/ADDRESS]` eg, `editteammate Lucas98 tn/GeNiaaz ta/5 Hacker Way`
**View a teammate’s details** | `viewteammate GIT_USER_NAME` | project scope
**Leave Teammate view to go to Project view** | `leaveteammateview` | project scope
