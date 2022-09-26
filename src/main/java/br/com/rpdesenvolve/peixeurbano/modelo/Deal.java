package br.com.rpdesenvolve.peixeurbano.modelo;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    private String url;
    private Long totalSold;
    @Enumerated(EnumType.STRING)
    private Type type;
    private LocalDateTime createDate = LocalDateTime.now();
    private Date publishDate;
    private Date endDate;
    @OneToMany
    @JoinColumn(name = "buyOptionList_id")
    private List<BuyOption> buyOptionList;

    public Deal() {}

    public Deal(String title, String text, Type type, Date publishDate, Date endDate, List buyOptionList) {
        this.title = title;
        this.text = text;
        this.type = type;
        this.publishDate = publishDate;
        this.endDate = endDate;
        this.buyOptionList = buyOptionList;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public Long getTotalSold() { return totalSold; }

    public void setTotalSold(Long totalSold) { this.totalSold = totalSold; }

    public Type getType() { return type; }

    public void setType(Type type) { this.type = type; }

    public LocalDateTime getCreateDate() { return createDate; }

    public void setCreateDate(LocalDateTime createDate) { this.createDate = createDate; }

    public Date getPublishDate() { return publishDate; }

    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }

    public Date getEndDate() { return endDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public List<BuyOption> getBuyOptionList() {
        return buyOptionList;
    }

    public void setBuyOptionList(List<BuyOption> buyOptionList) {
        this.buyOptionList = buyOptionList;
    }
}
