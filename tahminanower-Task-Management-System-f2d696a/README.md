# Task-Management-System
Group Project of the course COMP2021 Object Oriented Programming

# Introduction

Welcome to the user manual of the Task Management System (TMS), a Java-based application designed to simplify and streamline your task organization. The TMS is a command-line tool that empowers users to define, query, and modify both simple primitive and composite tasks efficiently. The primary goal of the TMS is to facilitate task management by allowing users to create, edit, and delete tasks. A task can either be a primitive task, representing the smallest units of work, or a composite task, which is a collection of other tasks.

# Commands Description

# DEFINE (Task and Criteria Creation):

● Create Primitive Task: Primitive task is uniquely identified by a name, has a description, and a duration (indicating the minimum time required for completion). Primitive tasks in the TMS do not have any prerequisites, making them standalone units.

● Create Composite Task: Composite task is containing a name, a description, and a list of prerequisites. Composite tasks have prerequisite subtasks, which must be existing tasks (both primitive and composite).

● Create Basic Task Selection Criteria: This function allows users to create basic task selection criteria within the Task Management System, facilitating customized filtering and analysis of tasks. By utilizing the DefineBasicCriterion command, users can create specific criteria based on task properties, enhancing their ability to focus on relevant tasks in the system.

● Create Composite Task Selection Criteria: This feature empowers users to construct composite task selection criteria within the Task Management System, enabling more sophisticated and nuanced filtering of tasks. With the DefineNegatedCriterion and DefineBinaryCriterion commands, users can create composite criteria by combining existing criteria, allowing for advanced logical operations.

# EDIT (Task Modification and Deletion) :

● Delete Tasks: This function enables users to remove tasks from the Task Management System, providing a means to manage and streamline their task list. The DeleteTask command allows users to delete tasks based on their name. The function will block the deletion a task if that particular task exists as a subtask in another composite.

● Change Task Properties: This function users to modify the properties of existing tasks within the Task Management System. The ChangeTask command facilitates dynamic adjustments to task attributes while ensuring the integrity of task prerequisites. If a task is a subtask for a composite task, this function will not allow the change of that task’s property. This feature enhances the flexibility of task management by allowing users to adapt and refine task properties as project requirements evolve.

# Query (Task Information Retrieval):

● Print Task Information: This feature allows users to retrieve detailed information about a specific task within the Task Management System. By using the PrintTask command followed by the task's name, users can access a comprehensive overview of the task, including its name, description, duration, and prerequisites.

● Print All Tasks: With the PrintAllTasks command, users can obtain a holistic overview of all existing tasks within the system. This feature is designed for users who need a comprehensive list of tasks at a glance. The output is structured to be easy to read, presenting essential information about each task, such as name, description, duration, and prerequisites. 

● Report Task Duration: The ReportDuration command enables users to determine the minimum time required to finish a specific task, considering any prerequisites associated with that task. For composite tasks, the reported duration is the sum of the minimum durations of all sub-tasks, considering prerequisite relationships. This feature is essential for project planning and scheduling, providing accurate insights into the time required to complete a task and its dependencies.

● Report Earliest Finish Time: The ReportEarliestFinishTime command allows users to identify the earliest possible completion time for a specific task. It considers the sum of the earliest finish times of all prerequisites and the duration of the task itself. This functionality is crucial for project managers and users who need to understand the critical path and earliest possible completion time of tasks in a project. It aids in optimising project timelines and resource allocation.

● Print All Defined Criteria: This feature provides users with a comprehensive overview of all defined criteria within the Task Management System. The PrintAllCriteria command is designed to present these criteria in a simplified and standardized form, ensuring clarity and ease of interpretation

● Search for Tasks Based on an Existing Criterion: This powerful feature allows users to perform targeted searches within the Task Management System based on a predefined criterion. The Search command enables users to list all tasks that satisfy the specified criterion, facilitating focused and efficient task retrieval according to custom criteria.

# Saving and Loading:

● Store Defined Tasks and Criteria into a File: This feature provides users with the capability to persistently store the defined tasks and criteria within the Task Management System into an external file. The Store command allows for data preservation and portability, enabling users to save their project configurations for future use or sharing.

● Load Tasks and Criteria from a File: This feature offers users the ability to import previously defined tasks and criteria into the Task Management System from an external file. The Load command facilitates the seamless integration of pre-existing project configurations, enabling users to pick up where they left off or collaborate by sharing project states.

# System Close:

● Terminate Execution of the System: This feature provides users with a straightforward way to gracefully exit and terminate the execution of the Task Management System. The Quit command allows users to conclude their session, ensuring a clean and controlled closure of the system.

# Help:

● The Help command in this Java application serves as a command-line interactive help service, providing assistance and information about different aspects of the system. Upon invocation, the function presents a menu with three options:

● Option 1 (About this system): If the user selects this option, the function will provide a description of the system.

● Option 2 (How to write commands): If the user selects this option, the function enters a loop where it prompts the user to inquire about the syntax of specific commands related to the system. It then responds with the appropriate syntax for the requested command. This feature helps users understand how to structure commands for interacting with the system.

● Option 3 (Contact the developers): If the user selects this option, the function provides information on how to contact the developers of the system, including details.


## Look into the user Manual for more details on each and every funtion and how to run the system!
