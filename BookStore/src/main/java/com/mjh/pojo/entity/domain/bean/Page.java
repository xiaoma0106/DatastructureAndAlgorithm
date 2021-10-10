package com.mjh.pojo.entity.domain.bean;

import java.util.List;

/**
 * @author mjh
 * @date 2021-10-10 11:19
 */
public class Page<T> {
    public final static Integer PAGE_SIZE=4;
    //总共记录数
    private Integer pageTotalCount;
    //每页显示记录数
    private Integer pageSize=PAGE_SIZE;
    // 总页数
    private Integer pageTotal;
    //当前页码数
    private Integer pageNo;
    //当前叶数据
    private List<T> items;
    //分页条的请求地址
    private String url;

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo <1){
            pageNo=1;
        }else if (pageNo > pageTotal){
            pageNo=pageTotal;
        }
        this.pageNo = pageNo;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageTotalCount=" + pageTotalCount +
                ", pageSize=" + pageSize +
                ", pageTotal=" + pageTotal +
                ", pageNo=" + pageNo +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
