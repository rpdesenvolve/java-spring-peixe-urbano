package br.com.rpdesenvolve.peixeurbano.modelo;

import javax.persistence.*;
import java.util.Date;

@Entity
public class BuyOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double normalPrice;
    private Double salePrice;
    private Double percentageDiscount;
    private Long quantityCupom;
    private Date startDate;
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "deal_id")
    private Deal deal;

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public BuyOption() {}

    public BuyOption(String title, Double normalPrice, Double salePrice, Double percentageDiscount, Long quantityCupom, Date startDate, Date endDate) {
        this.title = title;
        this.normalPrice = normalPrice;
        this.salePrice = salePrice;
        this.percentageDiscount = percentageDiscount;
        this.quantityCupom = quantityCupom;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Double getNormalPrice() { return normalPrice; }

    public void setNormalPrice(Double normalPrice) { this.normalPrice = normalPrice; }

    public Double getSalePrice() { return salePrice; }

    public void setSalePrice(Double salePrice) { this.salePrice = salePrice; }

    public Double getPercentageDiscount() { return percentageDiscount; }

    public void setPercentageDiscount(Double percentageDiscount) { this.percentageDiscount = percentageDiscount; }

    public Long getQuantityCupom() { return quantityCupom; }

    public void setQuantityCupom(Long quantityCupom) { this.quantityCupom = quantityCupom; }

    public Date getStartDate() { return startDate; }

    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }

    public void setEndDate(Date endDate) { this.endDate = endDate; }
}
