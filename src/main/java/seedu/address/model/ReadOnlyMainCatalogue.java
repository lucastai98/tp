package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.project.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * Unmodifiable view of an main catalogue
 */
public interface ReadOnlyMainCatalogue {

    /**
     * Returns an unmodifiable view of the projects list.
     * This list will not contain any duplicate projects.
     */
    ObservableList<Project> getProjectList();

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Gets the current status for valid scope.
     */
    Status getStatus();

    /**
     * Resets the status.
     */
    void setStatus(Status status);

    /**
     * Enters a designated project.
     */
    void enter(Project project);

    /**
     * Enters a designated person.
     */
    void enter(Person person);

    /**
     * Quits the current project view.
     */
    void quit();

    /**
     * Enters a designated task of a project.
     */
    void enterTask(Task task);

    /**
     * Enters a designated teammate of a project.
     * @param teammate
     */
    void enterTeammate(Participation teammate);

    /**
     * Enters a designated meeting of a project.
     */
    void enterMeeting(Meeting meeting);
}
