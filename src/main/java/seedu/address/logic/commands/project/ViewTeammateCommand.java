package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Participation;
import seedu.address.model.project.Project;

/**
 * Requests to view the details of an existing teammate in the project.
 */
public class ViewTeammateCommand extends Command {

    public static final String COMMAND_WORD = "viewteammate";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the details of the teammate identified "
            + "by the index number used in the displayed teammate list. ";

    public static final String MESSAGE_VIEW_TEAMMATE_SUCCESS = "Started TEAMMATE: %1$s";

    private final Index index;

    /**
     * @param index of the task in the filtered teammate list to edit
     *
     */
    public ViewTeammateCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        List<Participation> lastShownList = project.getTeammates();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAMMATE_DISPLAYED_INDEX);
        }

        Participation teammate = lastShownList.get(index.getZeroBased());
        model.enterTeammate(teammate);

        return new CommandResult(String.format(MESSAGE_VIEW_TEAMMATE_SUCCESS, teammate));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewTeammateCommand)) {
            return false;
        }

        // state check
        ViewTeammateCommand e = (ViewTeammateCommand) other;
        return index.equals(e.index);
    }
}
