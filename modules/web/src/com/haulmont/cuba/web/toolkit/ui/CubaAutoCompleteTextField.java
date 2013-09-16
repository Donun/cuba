/*
 * Copyright (c) 2008-2013 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/license for details.
 */

package com.haulmont.cuba.web.toolkit.ui;

import com.haulmont.cuba.gui.autocomplete.AutoCompleteSupport;
import com.haulmont.cuba.gui.autocomplete.Suggester;
import com.haulmont.cuba.gui.autocomplete.Suggestion;
import com.vaadin.event.ShortcutAction.ModifierKey;

import java.util.List;

/**
 * @author chevelev
 * @version $Id$
 */
public class CubaAutoCompleteTextField extends CubaResizableTextArea implements AutoCompleteSupport {

    private static final long serialVersionUID = -6051244662590740225L;

    private List<Suggestion> suggestions = null;
    private String suggestWord = "";
    private Suggester suggester = null;
    private int key = ' ';
    private int modifier = ModifierKey.CTRL;
    private int cursorPosition;
    private String text;
    private int textLength;

    public CubaAutoCompleteTextField() {
    }

    public CubaAutoCompleteTextField(String caption, int rows, int cols) {
        setColumns(cols);
        setRows(rows);
        setCaption(caption);
    }

/*  vaadin7
    @Override
    public void paintContent(PaintTarget target) throws PaintException {
        super.paintContent(target);

        target.addVariable(this, "suggestWord", suggestWord == null ? ""
                : suggestWord);
        target.addVariable(this, "cursor", -1);
        target.addVariable(this, "key", getKey());
        target.addVariable(this, "modifier", getModifier());

        if (suggestions != null) {
            String[] titles = new String[suggestions.size()];
            String[] suggs = new String[suggestions.size()];
            String[] suffices = new String[suggestions.size()];
            String[] starts = new String[suggestions.size()];
            String[] ends = new String[suggestions.size()];
            int i = 0;
            for (Suggestion s : suggestions) {
                titles[i] = JsonPaintTarget.escapeJSON(s.getDisplayText());
                suggs[i] = JsonPaintTarget.escapeJSON(s.getValueText());
                suffices[i] = JsonPaintTarget.escapeJSON(s.getValueSuffix());
                starts[i] = "" + s.getStartPosition();
                ends[i++] = "" + s.getEndPosition();
            }
            suggestions = null;

            target.addVariable(this, "titles", titles);
            target.addVariable(this, "suggestions", suggs);
            target.addVariable(this, "suffices", suffices);
            target.addVariable(this, "starts", starts);
            target.addVariable(this, "ends", ends);
        }
    }*/


    /*@Override vaadin7
    @SuppressWarnings("unchecked")
    public void changeVariables(Object source, Map variables) {
        super.changeVariables(source, variables);

        suggestions = null;
        if (variables.containsKey("cursor") && suggester != null) {
            cursorPosition = (Integer) variables.get("cursor");
            text = getText();
            textLength = text != null ? text.length() : 0;
            suggestions = suggester.getSuggestions(this, text, cursorPosition);
        }
        if (suggestions != null) {
            requestRepaint();
        }
    }*/

    public Suggester getSuggester() {
        return suggester;
    }

    public void setSuggester(Suggester suggester) {
        this.suggester = suggester;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public int getModifier() {
        return modifier;
    }

    @Override
    public int getCursorPosition() {
        return cursorPosition;
    }

    public int getTextLength() {
        return textLength;
    }
}