package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.project.Deadline;
import seedu.address.model.project.Participation;

/**
 * Represents a Task of a project.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {
    public final String taskName;
    private final String description;
    private LocalDate publishDate;
    private final Deadline deadline;
    private final double progress;
    private final boolean isDone;
    private Set<Participation> assignees;

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

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    public double getProgress() {
        return progress;
    }

    public Set<Participation> getAssignees() {
        return assignees;
    }

    public boolean isDone() {
        return isDone;
    }

    public boolean hasAssignee(Participation assignee) {
        return assignees.contains(assignee);
    }

    /**
     * Checks if the task has an assignee whose name matches the given name.
     *
     * @param assigneeName the assignee's name to look for
     * @return true if this task has an assignee whose name matches the given name,
     * and false otherwise
     */
    public boolean hasAssigneeWhoseNameIs(String assigneeName) {
        return assignees.stream()
                .anyMatch(assignee -> assignee.getPerson().getPersonName().equals(assigneeName));
    }

    public boolean addAssignee(Participation assignee) {
        return assignees.add(assignee);
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
                && getPublishDate().equals(task.getPublishDate())
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

    // TODO: may add isValidTask method.
}
