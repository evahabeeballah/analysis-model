package edu.hm.hafner.analysis.registry;

import edu.hm.hafner.analysis.Issue;
import edu.hm.hafner.analysis.IssueParser;
import edu.hm.hafner.analysis.parser.PyLintParser;
import edu.hm.hafner.analysis.parser.pylint.PyLintDescriptions;
import edu.hm.hafner.analysis.util.Deferred;

/**
 * A descriptor for the PyLint.
 *
 * @author Lorenz Munsch
 */
class PyLintDescriptor extends ParserDescriptor {
    private static final String ID = "pylint";
    private static final String NAME = "Pylint";

    private final Deferred<PyLintDescriptions> messages = new Deferred<>(PyLintDescriptions::new);

    PyLintDescriptor() {
        super(ID, NAME);
    }

    @Override
    public IssueParser createParser(final Option... options) {
        return new PyLintParser();
    }

    @Override
    public String getHelp() {
        return "<p>Create a ./pylintrc that contains:"
                + "<p><code>msg-template={path}:{module}:{line}: [{msg_id}({symbol}), {obj}] {msg}</code></p>"
                + "</p>"
                + "<p>Start pylint using the command:"
                + "<p><code>pylint --rcfile=./pylintrc CODE > pylint.log</code></p>"
                + "</p>";
    }

    @Override
    public String getDescription(final Issue issue) {
        return messages.get().getDescription(issue.getType());
    }
}
