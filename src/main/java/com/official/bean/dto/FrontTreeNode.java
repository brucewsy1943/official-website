package com.official.bean.dto;

import com.official.tree.TreeEntitys;
import com.official.tree.TreeUtils;

import java.util.Arrays;
import java.util.List;

/**前台的树用
 * Created by Administrator on 2020/3/2.
 */
public class FrontTreeNode extends TreeEntitys<FrontTreeNode> {
    private Integer id;
    private Integer parentId;
    private String text;
    private String href;
    private List<FrontTreeNode> nodes;
    private String[] tags;
    private Integer linkType;
    private String refno;//栏目标识
    private String bannerPic;//导航图

    public FrontTreeNode() {
    }

    public FrontTreeNode(Integer id, Integer parentId, String text, String href, List<FrontTreeNode> nodes, String[] tags, Integer linkType, String refno, String bannerPic) {
        this.id = id;
        this.parentId = parentId;
        this.text = text;
        this.href = href;
        this.nodes = nodes;
        this.tags = tags;
        this.linkType = linkType;
        this.refno = refno;
        this.bannerPic = bannerPic;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public List<FrontTreeNode> getNodes() {
        return nodes;
    }

    @Override
    public void setNodes(List<FrontTreeNode> nodes) {
        this.nodes = nodes;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public Integer getLinkType() {
        return linkType;
    }

    public void setLinkType(Integer linkType) {
        this.linkType = linkType;
    }

    public String getRefno() {
        return refno;
    }

    public void setRefno(String refno) {
        this.refno = refno;
    }

    public String getBannerPic() {
        return bannerPic;
    }

    public void setBannerPic(String bannerPic) {
        this.bannerPic = bannerPic;
    }

    @Override
    public String toString() {
        return "FrontTreeNode{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", text='" + text + '\'' +
                ", href='" + href + '\'' +
                ", nodes=" + nodes +
                ", tags=" + Arrays.toString(tags) +
                ", linkType=" + linkType +
                ", refno='" + refno + '\'' +
                ", bannerPic='" + bannerPic + '\'' +
                '}';
    }
}
