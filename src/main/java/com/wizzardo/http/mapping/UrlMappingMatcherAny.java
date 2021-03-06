package com.wizzardo.http.mapping;

import com.wizzardo.http.Named;

/**
* Created by wizzardo on 27.03.15.
*/
class UrlMappingMatcherAny<T extends Named> extends UrlMappingMatcher<T> {
    protected UrlMappingMatcherAny(UrlMapping<T> parent) {
        super(parent);
    }

    @Override
    protected boolean matches(String urlPart) {
        return true;
    }

    @Override
    protected boolean checkNextPart() {
        return false;
    }

    @Override
    protected void setValue(T t) {
        super.setValue(t);
        if (parent != null)
            parent.setValue(t);
    }
}
