/*
 * Copyright (c) 2008-2013 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/license for details.
 */

package com.haulmont.cuba.web.toolkit.ui.client.verticalactionslayout;

import com.haulmont.cuba.web.toolkit.ui.client.orderedactionslayout.CubaOrderedActionsLayoutWidget;

/**
 * @author devyatkin
 * @version $Id$
 */
public class CubaVerticalActionsLayoutWidget extends CubaOrderedActionsLayoutWidget {

    public static final String CLASSNAME = "v-verticallayout";

    public CubaVerticalActionsLayoutWidget(){
        super(CLASSNAME, true);
    }
}
