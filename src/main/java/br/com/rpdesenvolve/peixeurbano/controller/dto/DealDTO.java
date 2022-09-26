package br.com.rpdesenvolve.peixeurbano.controller.dto;

import br.com.rpdesenvolve.peixeurbano.modelo.Deal;
import br.com.rpdesenvolve.peixeurbano.modelo.Type;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DealDTO {

    private Long id;
    private String title;
    private String text;
    private String url;
    private Type type;
    private Date publishDate;
    private Date endDate;

    public DealDTO(Deal deal) {
        this.id = deal.getId();
        this.title = deal.getTitle();
        this.text = deal.getText();
        this.url = deal.getUrl();
        this.type = deal.getType();
        this.publishDate = deal.getPublishDate();
        this.endDate = deal.getEndDate();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public Type getType() {
        return type;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public static List<DealDTO> convert(List<Deal> deals) {
        return deals.stream().map(DealDTO::new).collect(Collectors.toList());
    }
}
