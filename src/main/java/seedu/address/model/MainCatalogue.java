package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.project.Participation;
import seedu.address.model.project.Project;
import seedu.address.model.project.UniqueProjectList;
import seedu.address.model.task.Task;

/**
 * Wraps all data at the main-catalogue level
 * Duplicates are not allowed (by .isSameProject comparison)
 */
public class MainCatalogue implements ReadOnlyMainCatalogue {

    private final UniqueProjectList projects;
    private final UniquePersonList persons;
    private Status status;
    private Optional<Project> project;
    private Optional<Person> person;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        projects = new UniqueProjectList();
        persons = new UniquePersonList();
        status = Status.PROJECT_LIST;
        project = Optional.empty();
        person = Optional.empty();
    }

    public MainCatalogue() {}

    /**
     * Creates an MainCatalogue using the Projects in the {@code toBeCopied}
     */
    public MainCatalogue(ReadOnlyMainCatalogue toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the project list with {@code projects}.
     * {@code projects} must not contain duplicate projects.
     */
    public void setProjects(List<Project> projects) {
        this.projects.setProjects(projects);
    }

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code MainCatalogue} with {@code newData}.
     */
    public void resetData(ReadOnlyMainCatalogue newData) {
        requireNonNull(newData);

        setProjects(newData.getProjectList());
        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the main catalogue.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the main catalogue.
     * The person must not already exist in the main catalogue.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the main catalogue.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the main
     * catalogue.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code MainCatalogue}.
     * {@code key} must exist in the main catalogue.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //// project-level operations

    /**
     * Returns true if a project with the same identity as {@code project} exists in the main catalogue.
     */
    public boolean hasProject(Project project) {
        requireNonNull(project);
        return projects.contains(project);
    }

    /**
     * Adds a project to the main catalogue.
     * The project must not already exist in the main catalogue.
     */
    public void addProject(Project p) {
        projects.add(p);
    }

    /**
     * Replaces the given project {@code target} in the list with {@code editedProject}.
     * {@code target} must exist in the main catalogue.
     * The project identity of {@code editedProject} must not be the same as another existing project in the main
     * catalogue.
     */
    public void setProject(Project target, Project editedProject) {
        requireNonNull(editedProject);

        projects.setProject(target, editedProject);
    }

    /**
     * Removes {@code key} from this {@code MainCatalogue}.
     * {@code key} must exist in the main catalogue.
     */
    public void removeProject(Project key) {
        projects.remove(key);
    }

    //// project-specific-level operations TODO: may add more

    //// scoping operations
    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void enter(Project project) {
        status = Status.PROJECT;
        this.project = Optional.of(projects.getProject(project));
        this.person = Optional.empty();
    }

    @Override
    public void enter(Person person) {
        status = Status.PERSON;
        this.person = Optional.of(persons.getPerson(person));
        this.project = Optional.empty();
    }

    @Override
    public void quit() {
        if (status == Status.PROJECT) {
            status = Status.PROJECT_LIST;
            this.project = Optional.empty();
        } else if (status == Status.PERSON) {
            status = Status.PERSON_LIST;
            this.person = Optional.empty();
        } else if (status == Status.TASK) {
            status = Status.PROJECT;
            project.get().updateTaskOnView(null);
        } else if (status == Status.TEAMMATE) {
            status = Status.PROJECT;
            project.get().updateTeammateOnView(null);
        } else if (status == Status.MEETING) {
            status = Status.PROJECT;
            project.get().updateMeetingOnView(null);
        }
    }

    @Override
    public void enterTask(Task task) {
        status = Status.TASK;
        project.get().updateTaskOnView(task);
        project.get().updateMeetingFilter(null);
        project.get().updateTeammateOnView(null);
    }

    @Override
    public void enterTeammate(Participation teammate) {
        status = Status.TEAMMATE;
        project.get().updateTaskOnView(null);
        project.get().updateMeetingFilter(null);
        project.get().updateTeammateOnView(teammate);
    }

    @Override
    public void enterMeeting(Meeting meeting) {
        status = Status.MEETING;
        project.get().updateTaskOnView(null);
        project.get().updateTeammateOnView(null);
        project.get().updateMeetingOnView(meeting);
    }

    //// util methods

    @Override
    public String toString() {
        return projects.asUnmodifiableObservableList().size() + " projects";
        // TODO: refine later
    }

    @Override
    public ObservableList<Project> getProjectList() {
        return projects.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MainCatalogue // instanceof handles nulls
                && projects.equals(((MainCatalogue) other).projects)
                && persons.equals(((MainCatalogue) other).persons));
    }

    @Override
    public int hashCode() {
        return projects.hashCode();
    }
}
