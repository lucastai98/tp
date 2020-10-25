package seedu.address.logic;

import java.nio.file.Path;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyMainCatalogue;
import seedu.address.model.Status;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.project.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the MainCatalogue.
     *
     * @see seedu.address.model.Model#getProjectCatalogue()
     */
    ReadOnlyMainCatalogue getMainCatalogue();

    /** Returns an unmodifiable view of the filtered list of projects */
    ObservableList<Project> getFilteredProjectList();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Gets the project to be displayed on dashboard.
     * @return the project to be displayed on dashboard.
     */
    Optional<Project> getProjectToBeDisplayedOnDashBoard();

    /**
     * Gets the task to be displayed on dashboard.
     */
    Optional<Task> getTaskToBeDisplayedOnDashboard();

    /**
     * Gets the teammate to be displayed on dashboard.
     */
    Optional<Participation> getTeammateToBeDisplayedOnDashboard();

    /**
     * Gets the meeting to be displayed on dashboard.
     */
    Optional<Meeting> getMeetingToBeDisplayedOnDashboard();

    /**
     * Gets the person to be displayed on dashboard.
     */
    Optional<Person> getPersonToBeDisplayedOnDashboard();

    /**
     * Returns the user prefs' main catalogue file path.
     */
    Path getMainCatalogueFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Gets the current status for valid scope.
     */
    Status getStatus();

    /**
     * Enters the designated project.
     */
    void enter(Project project);

    /**
     * Quits the current project view.
     */
    void quit();

    /**
     * Checks whether the app is viewing list of projects.
     */
    boolean isProjectsView();
}

///** Returns an unmodifiable view of the filtered list of tasks */
//List<Task> getFilteredTaskList();

///** Returns an unmodifiable view of the filtered list of teammates */
//List<Person> getFilteredTeammateList();
