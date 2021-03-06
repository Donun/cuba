/*
 * Copyright (c) 2008-2017 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.cuba.web.toolkit.ui;

import com.haulmont.cuba.web.toolkit.ui.client.cubascrollboxlayout.CubaScrollBoxLayoutServerRpc;
import com.haulmont.cuba.web.toolkit.ui.client.cubascrollboxlayout.CubaScrollBoxLayoutState;
import com.vaadin.ui.CssLayout;

public class CubaScrollBoxLayout extends CssLayout {

    protected CubaScrollBoxLayoutServerRpc serverRpc;

    public CubaScrollBoxLayout() {
        serverRpc = (scrollTop, scrollLeft) -> {
            getState(false).scrollTop = scrollTop;
            getState(false).scrollLeft = scrollLeft;
        };
        registerRpc(serverRpc);
    }

    @Override
    protected CubaScrollBoxLayoutState getState() {
        return (CubaScrollBoxLayoutState) super.getState();
    }

    @Override
    protected CubaScrollBoxLayoutState getState(boolean markAsDirty) {
        return (CubaScrollBoxLayoutState) super.getState(markAsDirty);
    }

    public void setScrollTop(int scrollTop) {
        if (getState(false).scrollTop != scrollTop) {
            getState().scrollTop = scrollTop;
        }
    }

    public void setScrollLeft(int scrollLeft) {
        if (getState(false).scrollLeft != scrollLeft) {
            getState().scrollLeft = scrollLeft;
        }
    }
}