package common;

import java.awt.*;

/**
 * INTERFACE = a "certification badge". Anything that can be shown in one grid
 * cell implements this. GridView only knows about Displayable, never about the
 * concrete classes: that is ABSTRACTION + POLYMORPHISM (marks!).
 *
 * EXAM: keep this file exactly as it is. It works for every past paper.
 */
public interface Displayable {

    /** Text for the cell. Use "<html>line 1<br>line 2</html>" for several lines. */
    String getCellText();

}
