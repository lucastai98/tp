package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.project.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Project> PREDICATE_SHOW_ALL_PROJECTS = unused -> true;
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    //Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;
    //Predicate<Person> PREDICATE_SHOW_ALL_TEAMMATES = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' main catalogue file path.
     */
    Path getProjectCatalogueFilePath();

    /**
     * Sets the user prefs' main catalogue file path.
     */
    void setProjectCatalogueFilePath(Path mainCatalogueFilePath);

    /**
     * Replaces main catalogue data with the data in {@code mainCatalogue}.
     */
    void setProjectCatalogue(ReadOnlyMainCatalogue mainCatalogue);

    /** Returns the MainCatalogue */
    ReadOnlyMainCatalogue getProjectCatalogue();

    /**
     * Returns true if a project with the same identity as {@code project} exists in the main catalogue.
     */
    boolean hasProject(Project project);

    /**
     * Deletes the given project.
     * The project must exist in the main catalogue.
     */
    void deleteProject(Project target);

    /**
     * Adds the given project.
     * {@code project} must not already exist in the main catalogue.
     */
    void addProject(Project project);

    /**
     * Replaces the given project {@code target} with {@code editedProject}.
     * {@code target} must exist in the main catalogue.
     * The project identity of {@code editedProject} must not be the same as another existing project in the main
     * catalogue.
     */
    void setProject(Project target, Project editedProject);

    /** Returns an unmodifiable view of the filtered project list */
    ObservableList<Project> getFilteredProjectList();

    /**
     * Updates the filter of the filtered project list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredProjectList(Predicate<Project> predicate);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the main catalogue.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the main catalogue.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the main catalogue.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the main catalogue.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the main
     * catalogue.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Gets the current status for valid scope.
     */
    Status getStatus();

    /**
     * Sets current status to project_list branch.
     */
    void setAsProjectList();

    /**
     * Sets current status to person_list branch.
     */
    void setAsPersonList();

    /**
     * Enters the designated project.
     */
    void enter(Project project);

    /**
     * Enters the designated person.
     */
    void enter(Person person);

    /**
     * Quits the current project view.
     */
    void quit();

    /**
     * Enters the designated task of the current project.
     */
    void enterTask(Task task);

    /**
     * Enters the designated teammate of the current project.
     * @param teammate
     */
    void enterTeammate(Participation teammate);

    /**
     * Enters the designated meeting of the current project.
     */
    void enterMeeting(Meeting meeting);

    /**
     * Updates the project to be displayed on dashboard.
     * @param project project to be displayed on dashboard
     */
    void updateProjectToBeDisplayedOnDashboard(Project project);

    /**
     * Gets the project to be displayed on dashboard.
     * @return project to be displayed on dashboard wrapped in Optional box.
     */
    Optional<Project> getProjectToBeDisplayedOnDashboard();

    /**
     * Updates the task to be displayed on dashboard.
     */
    void updateTaskToBeDisplayedOnDashboard(Task task);

    /**
     * Gets the task to be displayed on dashboard.
     */
    Optional<Task> getTaskToBeDisplayedOnDashboard();

    /**
     * Updates the teammate to be displayed on dashboard.
     */
    void updateTeammateToBeDisplayedOnDashboard(Participation teammate);

    /**
     * Gets the teammate to be displayed on dashboard.
     */
    Optional<Participation> getTeammateToBeDisplayedOnDashboard();

    /**
     * Updates the meeting to be displayed on dashboard.
     */
    void updateMeetingToBeDisplayedOnDashboard(Meeting meeting);

    /**
     * Gets the meeting to be displayed on dashboard.
     */
    Optional<Meeting> getMeetingToBeDisplayedOnDashboard();

    /**
     * Updates the person to be displayed on dashboard.
     */
    void updatePersonToBeDisplayedOnDashboard(Person person);

    /**
     * Gets the person to be displayed on dashboard.
     */
    Optional<Person> getPersonToBeDisplayedOnDashboard();

}

///** Returns an unmodifiable view of the filtered task list */
//List<Task> getFilteredTaskList();

///**
// * Updates the filter of the filtered task list to filter by the given {@code predicate}.
// * @throws NullPointerException if {@code predicate} is null.
// */
//void updateFilteredTaskList(List<Task> taskList);

///** Returns an unmodifiable view of the filtered teammate list */
//List<Person> getFilteredTeammateList() ;

///**
// * Updates the filter of the filtered teammate list to filter by the given {@code predicate}.
// * @throws NullPointerException if {@code predicate} is null.
// */
//void updateFilteredTeammateList(List<Person> teammatesList);
