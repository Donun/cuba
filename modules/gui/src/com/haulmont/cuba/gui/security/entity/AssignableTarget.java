/*
 * Copyright (c) 2008-2013 Haulmont. All rights reserved.
 * Use is subject to license terms, see http://www.cuba-platform.com/license for details.
 */

package com.haulmont.cuba.gui.security.entity;

/**
 * <p>$Id$</p>
 *
 * @author artamonov
 */
public interface AssignableTarget {
    boolean isAssigned();

    String getPermissionValue();
}