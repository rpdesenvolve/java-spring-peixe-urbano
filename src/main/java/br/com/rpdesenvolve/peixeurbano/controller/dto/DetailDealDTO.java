package br.com.rpdesenvolve.peixeurbano.controller.dto;

import br.com.rpdesenvolve.peixeurbano.modelo.Deal;
import br.com.rpdesenvolve.peixeurbano.modelo.Type;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DetailDealDTO {

    private Long id;
    private String title;
    private String text;
    private String url;
    private Long totalSold;
    private Type type;
    private Date publishDate;
    private Date endDate;
    private List<BuyOptionDTO> buyOptionDTOList;

    public DetailDealDTO(Deal deal) {
        this.id = deal.getId();
        this.title = deal.getTitle();
        this.text = deal.getText();
        this.url = deal.getUrl();
        this.totalSold = deal.getTotalSold();
        this.type = deal.getType();
        this.publishDate = deal.getPublishDate();
        this.endDate = deal.getEndDate();
        this.buyOptionDTOList = new ArrayList<>();
        this.buyOptionDTOList.addAll(deal.getBuyOptionList().stream().map(BuyOptionDTO::new).collect(Collectors.toList()));
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }

    public String getText() { return text; }

    public String getUrl() { return url; }

    public Long getTotalSold() { return totalSold; }

    public Type getType() { return type; }

    public Date getPublishDate() { return publishDate; }

    public Date getEndDate() { return endDate; }

    public List<BuyOptionDTO> getBuyOptionDTOList() {
        return buyOptionDTOList;
    }
}
