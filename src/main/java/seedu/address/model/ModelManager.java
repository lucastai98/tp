package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.project.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the main catalogue data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final MainCatalogue mainCatalogue;
    private final UserPrefs userPrefs;
    private final FilteredList<Project> filteredProjects;
    private final FilteredList<Person> filteredPersons;
    //private final List<Task> filteredTasks;
    //private final List<Person> filteredTeammates;
    private Optional<Project> projectToBeDisplayedOnDashboard;
    private Optional<Task> taskToBeDisplayedOnDashboard;
    private Optional<Participation> teammateToBeDisplayedOnDashboard;
    private Optional<Meeting> meetingToBeDisplayedOnDashboard;
    private Optional<Person> personToBeDisplayedOnDashboard;

    /**
     * Initializes a ModelManager with the given mainCatalogue and userPrefs.
     */
    public ModelManager(ReadOnlyMainCatalogue mainCatalogue, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(mainCatalogue, userPrefs);

        logger.fine("Initializing with main catalogue: " + mainCatalogue + " and user prefs " + userPrefs);

        this.mainCatalogue = new MainCatalogue(mainCatalogue);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredProjects = new FilteredList<>(this.mainCatalogue.getProjectList());
        filteredPersons = new FilteredList<>(this.mainCatalogue.getPersonList());
        //filteredTasks = new ArrayList<>();
        //filteredTeammates = new ArrayList<>();;
        this.projectToBeDisplayedOnDashboard = Optional.empty();
        this.taskToBeDisplayedOnDashboard = Optional.empty();
        this.teammateToBeDisplayedOnDashboard = Optional.empty();
        this.meetingToBeDisplayedOnDashboard = Optional.empty();
        this.personToBeDisplayedOnDashboard = Optional.empty();
    }

    public ModelManager() {
        this(new MainCatalogue(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getProjectCatalogueFilePath() {
        return userPrefs.getMainCatalogueFilePath();
    }

    @Override
    public void setProjectCatalogueFilePath(Path mainCatalogueFilePath) {
        requireNonNull(mainCatalogueFilePath);
        userPrefs.setMainCatalogueFilePath(mainCatalogueFilePath);
    }

    //=========== MainCatalogue ================================================================================

    @Override
    public void setProjectCatalogue(ReadOnlyMainCatalogue mainCatalogue) {
        this.mainCatalogue.resetData(mainCatalogue);
    }

    @Override
    public ReadOnlyMainCatalogue getProjectCatalogue() {
        return mainCatalogue;
    }

    @Override
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return mainCatalogue.hasProject(project);
    }

    @Override
    public void deleteProject(Project target) {
        mainCatalogue.removeProject(target);
    }

    @Override
    public void addProject(Project project) {
        mainCatalogue.addProject(project);
        updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
    }

    @Override
    public void setProject(Project target, Project editedProject) {
        requireAllNonNull(target, editedProject);

        mainCatalogue.setProject(target, editedProject);
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return mainCatalogue.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        mainCatalogue.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        mainCatalogue.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        mainCatalogue.setPerson(target, editedPerson);
    }

    //=========== Scoping modifiers ===========================================================================

    @Override
    public Status getStatus() {
        return mainCatalogue.getStatus();
    }

    @Override
    public void setAsProjectList() {
        mainCatalogue.setStatus(Status.PROJECT_LIST);
    }

    @Override
    public void setAsPersonList() {
        mainCatalogue.setStatus(Status.PERSON_LIST);
    }

    @Override
    public void enter(Project project) {
        mainCatalogue.enter(project);
        updateProjectToBeDisplayedOnDashboard(project);
        personToBeDisplayedOnDashboard = Optional.empty();
    }

    @Override
    public void enter(Person person) {
        mainCatalogue.enter(person);
        updatePersonToBeDisplayedOnDashboard(person);
        projectToBeDisplayedOnDashboard = Optional.empty();
    }

    @Override
    public void quit() {
        switch (mainCatalogue.getStatus()) {
        case PROJECT:
            projectToBeDisplayedOnDashboard = Optional.empty();
            teammateToBeDisplayedOnDashboard = Optional.empty();
            taskToBeDisplayedOnDashboard = Optional.empty();
            break;
        case TASK:
            taskToBeDisplayedOnDashboard = Optional.empty();
            break;
        case MEETING:
            meetingToBeDisplayedOnDashboard = Optional.empty();
            break;
        case TEAMMATE:
            teammateToBeDisplayedOnDashboard = Optional.empty();
            break;
        case PERSON:
            personToBeDisplayedOnDashboard = Optional.empty();
            break;
        default:
            break;
        }
        mainCatalogue.quit();
    }

    @Override
    public void enterTask(Task task) {
        mainCatalogue.enterTask(task);
        this.teammateToBeDisplayedOnDashboard = Optional.empty();
        this.meetingToBeDisplayedOnDashboard = Optional.empty();
        this.projectToBeDisplayedOnDashboard.get().updateTaskOnView(task);
        updateTaskToBeDisplayedOnDashboard(this.projectToBeDisplayedOnDashboard.get().getTaskOnView().get());
    }

    @Override
    public void enterTeammate(Participation teammate) {
        mainCatalogue.enterTeammate(teammate);
        this.meetingToBeDisplayedOnDashboard = Optional.empty();
        this.taskToBeDisplayedOnDashboard = Optional.empty();
        this.projectToBeDisplayedOnDashboard.get().updateTeammateOnView(teammate);
        updateTeammateToBeDisplayedOnDashboard(this.projectToBeDisplayedOnDashboard.get().getTeammateOnView().get());
    }

    @Override
    public void enterMeeting(Meeting meeting) {
        mainCatalogue.enterMeeting(meeting);
        this.taskToBeDisplayedOnDashboard = Optional.empty();
        this.teammateToBeDisplayedOnDashboard = Optional.empty();
        this.projectToBeDisplayedOnDashboard.get().updateMeetingOnView(meeting);
        updateMeetingToBeDisplayedOnDashboard(this.projectToBeDisplayedOnDashboard.get().getMeetingOnView().get());
    }

    //=========== Filtered Project List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Project} backed by the internal list of
     * {@code versionedMainCatalogue}
     */
    @Override
    public ObservableList<Project> getFilteredProjectList() {
        return filteredProjects;
    }

    @Override
    public void updateFilteredProjectList(Predicate<Project> predicate) {
        requireNonNull(predicate);
        filteredProjects.setPredicate(predicate);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedMainCatalogue}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return mainCatalogue.equals(other.mainCatalogue)
                && userPrefs.equals(other.userPrefs)
                && filteredProjects.equals(other.filteredProjects);
                //&& filteredTasks.equals(other.filteredTasks)
                //&& filteredTeammates.equals(other.filteredTeammates);
    }

    //=========== Project To Be Displayed On DashBoard Accessors ======================================================

    /**
     * Updates the project to be displayed on project dashboard.
     * @param project project to be displayed on dashboard
     */
    @Override
    public void updateProjectToBeDisplayedOnDashboard(Project project) {
        requireNonNull(project);
        this.projectToBeDisplayedOnDashboard = Optional.of(project);
        //updateFilteredTaskList(project.getFilteredTaskList());
        //updateFilteredTeammateList(project.getTeammates());
    }

    @Override
    public Optional<Project> getProjectToBeDisplayedOnDashboard() {
        return projectToBeDisplayedOnDashboard;
    }

    //=========== Task To Be Displayed On DashBoard Accessors ======================================================
    @Override
    public void updateTaskToBeDisplayedOnDashboard(Task task) {
        requireNonNull(task);
        this.taskToBeDisplayedOnDashboard = Optional.of(task);
    }

    @Override
    public Optional<Task> getTaskToBeDisplayedOnDashboard() {
        return taskToBeDisplayedOnDashboard;
    }

    //=========== Teammate To Be Displayed On DashBoard Accessors ======================================================
    @Override
    public void updateTeammateToBeDisplayedOnDashboard(Participation teammate) {
        requireNonNull(teammate);
        this.teammateToBeDisplayedOnDashboard = Optional.of(teammate);
    }

    @Override
    public Optional<Participation> getTeammateToBeDisplayedOnDashboard() {
        return teammateToBeDisplayedOnDashboard;
    }

    //=========== Meeting To Be Displayed On DashBoard Accessors ======================================================
    @Override
    public void updateMeetingToBeDisplayedOnDashboard(Meeting meeting) {
        requireNonNull(meeting);
        this.meetingToBeDisplayedOnDashboard = Optional.of(meeting);
    }

    @Override
    public Optional<Meeting> getMeetingToBeDisplayedOnDashboard() {
        return meetingToBeDisplayedOnDashboard;
    }

    //=========== Person To Be Displayed On DashBoard Accessors ======================================================
    @Override
    public void updatePersonToBeDisplayedOnDashboard(Person person) {
        requireNonNull(person);
        this.personToBeDisplayedOnDashboard = Optional.of(person);
    }

    @Override
    public Optional<Person> getPersonToBeDisplayedOnDashboard() {
        return personToBeDisplayedOnDashboard;
    }
}

//    //=========== Filtered Task List Accessors =============================================================
//
//    /**
//     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
//     * {@code versionedMainCatalogue}
//     */
//    @Override
//    public List<Task> getFilteredTaskList() {
//        return filteredTasks;
//    }
//
//    @Override
//    public void updateFilteredTaskList(List<Task> taskList) {
//        this.filteredTasks.addAll(taskList);
//    }
//
//    //=========== Filtered Teammates List Accessors =============================================================
//
//    /**
//     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
//     * {@code versionedMainCatalogue}
//     */
//    @Override
//    public List<Person> getFilteredTeammateList() {
//        return filteredTeammates;
//    }
//
//    @Override
//    public void updateFilteredTeammateList(List<Person> teammatesList) {
//        filteredTeammates.addAll(teammatesList);
//    }
