package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.global.StartPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new StartPersonCommand object
 */
public class StartPersonCommandParser implements Parser<StartPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the StartPersonCommand
     * and returns a StartPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StartPersonCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new StartPersonCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, StartPersonCommand.MESSAGE_USAGE), pe);
        }
    }
}
