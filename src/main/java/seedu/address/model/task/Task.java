package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.GitUserName;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Participation;

/**
 * Represents a Task of a project.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String DESCRIPTION_VALIDATION_REGEX = "[^\\s].*";

    public static final String NAME_MESSAGE_CONSTRAINTS =
            "Task names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String DESCRIPTION_MESSAGE_CONSTRAINTS =
            "Task descriptions should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String PUBLISH_DATE_MESSAGE_CONSTRAINTS =
            "Publish date should only be in the format of dd-MM-yyyy";
    public static final String DEADLINE_MESSAGE_CONSTRAINTS =
            "Publish date should only be in the format of yyyy-MM-dd";
    public static final String PROGRESS_MESSAGE_CONSTRAINTS =
            "Progress values should only contain integers between 0 and 100 inclusive, and it should not be blank";
    public static final String IS_DONE_MESSAGE_CONSTRAINTS =
            "Is done values should only contain booleans, and it should not be blank";

    public final String taskName;
    private final String description;
    private LocalDate publishDate;
    private final Deadline deadline;
    private final Double progress;
    private final Boolean isDone;
    private final Set<String> assignees;

    /**
     * name, progress, and isDone should be present and not null. description and deadline can be null.
     */
    public Task(String taskName, String description, Deadline deadline, double progress, boolean isDone) {
        requireAllNonNull(taskName, progress, isDone);
        this.taskName = taskName;
        this.description = description;
        publishDate = LocalDate.now();
        this.deadline = deadline;
        this.progress = progress;
        this.isDone = isDone;
        this.assignees = new HashSet<>();
    }

    /**
     * Returns true if a given string is a valid attribute.
     */
    public static boolean isValidAttribute(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public Double getProgress() {
        return progress;
    }

    public Set<String> getAssignees() {
        return assignees;
    }

    public Boolean isDone() {
        return isDone;
    }

    public boolean hasAssignee(Participation assignee) {
        return assignees.contains(assignee.getPerson().toString());
    }

    public boolean hasAnyAssignee() {
        return !assignees.isEmpty();
    }

    /**
     * Checks if the task has an assignee whose name matches the given name.
     *
     * @param assigneeGitUserName the assignee's name to look for
     * @return true if this task has an assignee whose name matches the given name,
     * and false otherwise
     */
    public boolean hasAssigneeWhoseGitNameIs(GitUserName assigneeGitUserName) {
        return assignees.stream()
                .anyMatch(assignee -> assignee.equals(assigneeGitUserName.toString()));
    }

    /**
     * Checks if the task is due on the given deadline.
     * @param deadline  the given deadline to check
     * @return  true if the task is due on the given deadline, and false otherwise
     */
    public boolean isDueOn(Deadline deadline) {
        assert (deadline != null);
        if (this.deadline == null) {
            return false;
        } else {
            return this.deadline.equals(deadline);
        }
    }
    public boolean addAssignee(String assignee) {
        return assignees.add(assignee);
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidTaskName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid description.
     */
    public static boolean isValidTaskDescription(String test) {
        if (test.isBlank()) {
            return true;
        } else {
            return test.matches(DESCRIPTION_VALIDATION_REGEX);
        }
    }

    /**
     * Returns true if a given string is a valid publish date.
     */
    public static boolean isValidPublishDate(String test) {
        return Task.isValidDate(test);
    }

    /**
     * Returns true if a given string is a valid progress value.
     */
    public static boolean isValidProgress(String test) {
        if (test == null) {
            return false;
        }
        if (test.isEmpty()) {
            return false;
        }
        try {
            Double t = Double.valueOf(test);
            return 0 <= t && t <= 100;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns true if a given string is a valid isDone value.
     */
    public static boolean isValidIsDone(String test) {
        if (test.equals("false")) {
            return true;
        } else {
            return Boolean.parseBoolean(test);
        }
    }

    /**
     * Returns true if all fields are equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Double.compare(task.getProgress(), getProgress()) == 0
                && getTaskName().equals(task.getTaskName())
                && (getDescription() == task.getDescription() || getDescription().equals(task.getDescription()))
                //            && getPublishDate().equals(task.getPublishDate())
                && Objects.equals(getDeadline(), task.getDeadline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskName(), getDescription(), getPublishDate(), getDeadline(), getProgress(), isDone());
    }

    @Override
    public String toString() {
        return "[" + taskName + "]";
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * Return true if a given string is a valid date.
     */
    public static boolean isValidDate(String date) {
        String[] strings = date.split("-");
        int year = Integer.parseInt(strings[0]);
        int month = Integer.parseInt(strings[1]);
        int day = Integer.parseInt(strings[2]);
        boolean isValidDay = day <= 31 && day >= 1;
        boolean isValidMonth = month <= 12 && month >= 1;
        //year is always valid because it matches the regex as 4 digits of integers (1000 - 9999)
        if (day == 29 && month == 2) {
            if (year % 400 == 0) {
                return true;
            } else if (year % 100 == 0) {
                return false;
            } else {
                return year % 4 == 0;
            }
        } else if ((day == 30 || day == 31) && month == 2) {
            return false;
        } else if (day == 31 && (month == 4 || month == 6
                || month == 9 || month == 11)) {
            return false;
        } else {
            return isValidDay && isValidMonth;
        }
    }

    // TODO: may add isValidTask method.
}
