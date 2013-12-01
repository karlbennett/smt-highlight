package shiver.me.timbers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static shiver.me.timbers.Asserts.*;
import static shiver.me.timbers.NullHighlight.*;

/**
 * A collection of individual and possibly unrelated highlightMap.
 *
 * @author Karl Bennett
 */
public class IndividualHighlights implements Highlights {

    private final List<Highlight> highlightList;
    private final Map<String, Highlight> highlightMap;

    public IndividualHighlights(Iterable<Highlight> highlights) {

        assertIsNotNull(IndividualHighlights.class.getSimpleName() + " highlights argument cannot be null.", highlights);

        this.highlightList = new ArrayList<Highlight>();
        this.highlightMap = new HashMap<String, Highlight>();

        for (Highlight highlight : highlights) {

            this.highlightList.add(highlight);
            this.highlightMap.put(highlight.getName(), highlight);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Highlight get(int index) {

        if (highlightList.size() <= index) {

            return NULL_HIGHLIGHT;
        }

        return highlightList.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Highlight get(String name) {

        final Highlight highlight = highlightMap.get(name);

        return null == highlight ? NULL_HIGHLIGHT : highlight;
    }

    @Override
    public Iterator<Highlight> iterator() {

        return highlightList.iterator();
    }
}
