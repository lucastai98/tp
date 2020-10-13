package seedu.address.testutil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectDescription;
import seedu.address.model.project.ProjectName;
import seedu.address.model.project.RepoUrl;
import seedu.address.model.tag.ProjectTag;
import seedu.address.model.task.Task;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Project objects.
 */
public class ProjectBuilder {

    public static final String DEFAULT_PROJECT_NAME = "changeName";
    public static final String DEFAULT_DEADLINE = "29-02-2020 00:00:00";
    public static final String DEFAULT_REPOURL = "https://github.com/a/b.git";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private ProjectName projectName;
    private Deadline deadline;
    private RepoUrl repoUrl;
    private ProjectDescription projectDescription;
    private Set<ProjectTag> projectTags;
    private Set<Task> tasks;
    private Set<Meeting> meetings;

    /**
     * Creates a {@code ProjectBuilder} with the default details.
     */
    public ProjectBuilder() {
        projectName = new ProjectName(DEFAULT_PROJECT_NAME);
        deadline = new Deadline(DEFAULT_DEADLINE);
        repoUrl = new RepoUrl(DEFAULT_REPOURL);
        projectDescription = new ProjectDescription(DEFAULT_ADDRESS);
        projectTags = new HashSet<>();
        tasks = new HashSet<>();
        meetings = new HashSet<>();
    }

    /**
     * Initializes the ProjectBuilder with the data of {@code projectToCopy}.
     */
    public ProjectBuilder(Project projectToCopy) {
        projectName = projectToCopy.getProjectName();
        deadline = projectToCopy.getDeadline();
        repoUrl = projectToCopy.getRepoUrl();
        projectDescription = projectToCopy.getProjectDescription();
        projectTags = new HashSet<>(projectToCopy.getProjectTags());
        tasks = new HashSet<>(projectToCopy.getTasks());
        meetings = new HashSet<>(projectToCopy.getMeetings());
    }

    /**
     * Sets the {@code ProjectName} of the {@code Project} that we are building.
     */
    public ProjectBuilder withProjectName(String projectName) {
        this.projectName = new ProjectName(projectName);
        return this;
    }

    /**
     * Parses the {@code projectTags} into a {@code Set<ProjectTag>} and set it to the {@code Project}
     * that we are building.
     */
    public ProjectBuilder withTags(String ... projectTags) {
        this.projectTags = SampleDataUtil.getTagSet(projectTags);
        return this;
    }

    /**
     * Parses the {@code tasks} into a {@code Set<Task>} and set it to the {@code Project} that we are building.
     */
    public ProjectBuilder withTasks(String ... tasks) {
        this.tasks = SampleDataUtil.getTaskSet(tasks);
        return this;
    }

    /**
     * Parses the {@code tasks} into a {@code Set<Task>} and set it to the {@code Project} that we are building.
     */
    public ProjectBuilder withMeetings(String ... meetings) {
        this.meetings = SampleDataUtil.getMeetingSet(meetings);
        return this;
    }

    /**
     * Sets the {@code ProjectDescription} of the {@code Project} that we are building.
     */
    public ProjectBuilder withProjectDescription(String projectDescription) {
        this.projectDescription = new ProjectDescription(projectDescription);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Project} that we are building.
     */
    public ProjectBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code RepoUrl} of the {@code Project} that we are building.
     */
    public ProjectBuilder withRepoUrl(String repoUrl) {
        this.repoUrl = new RepoUrl(repoUrl);
        return this;
    }

    /**
     * Creates a project
     *
     * @return project sample
     */
    public Project build() {
        return new Project(projectName, deadline, repoUrl, projectDescription,
                projectTags, new HashMap<>(), tasks, meetings);
    }

}
