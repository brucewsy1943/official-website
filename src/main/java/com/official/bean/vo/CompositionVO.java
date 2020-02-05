
package com.official.bean.vo;

import com.official.bean.Composition;

import java.util.List;

public class CompositionVO {
    private List<Composition> compositions;

    private Integer count;

    public List<Composition> getCompositions() {
        return compositions;
    }

    public void setCompositions(List<Composition> compositions) {
        this.compositions = compositions;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
