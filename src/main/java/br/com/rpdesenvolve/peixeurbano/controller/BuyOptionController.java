package br.com.rpdesenvolve.peixeurbano.controller;

import br.com.rpdesenvolve.peixeurbano.controller.dto.BuyOptionDTO;
import br.com.rpdesenvolve.peixeurbano.controller.dto.DetailBuyOptionDTO;
import br.com.rpdesenvolve.peixeurbano.controller.form.BuyOptionForm;
import br.com.rpdesenvolve.peixeurbano.controller.form.UpdateBuyOptionForm;
import br.com.rpdesenvolve.peixeurbano.controller.form.UpdateSellUnitForm;
import br.com.rpdesenvolve.peixeurbano.modelo.BuyOption;
import br.com.rpdesenvolve.peixeurbano.modelo.Deal;
import br.com.rpdesenvolve.peixeurbano.repository.BuyOptionRepository;
import br.com.rpdesenvolve.peixeurbano.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/buyoption")
public class BuyOptionController {

    @Autowired
    private BuyOptionRepository buyOptionRepository;
    @Autowired
    private DealRepository dealRepository;

    @GetMapping
    @Cacheable(value = "buyOptionList")
    public Page<BuyOptionDTO> list(@RequestParam(required = false) String title,
                                   @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pagination) {
        if (title == null) {
            Page<BuyOption> buyOptions = buyOptionRepository.findAll(pagination);
            return BuyOptionDTO.convert(buyOptions);
        } else {
            Page<BuyOption> buyOptions = buyOptionRepository.findByTitle(title, pagination);
            return BuyOptionDTO.convert(buyOptions);
        }
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "buyOptionList", allEntries = true)
    public ResponseEntity<BuyOptionDTO> register(@RequestBody BuyOptionForm buyOptionForm, UriComponentsBuilder uriComponentsBuilder) {
        BuyOption buyOption = buyOptionForm.convert();

        Double percentageDiscount = (buyOption.getNormalPrice() * buyOption.getPercentageDiscount()) / 100;
        Double salePrice = buyOption.getNormalPrice() - percentageDiscount;
        buyOption.setSalePrice(salePrice);

        buyOptionRepository.save(buyOption);

        URI uri = uriComponentsBuilder.path("/buyoption/{id}").buildAndExpand(buyOption.getId()).toUri();
        return ResponseEntity.created(uri).body(new BuyOptionDTO(buyOption));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailBuyOptionDTO> detail(@PathVariable Long id) {
        Optional<BuyOption> buyOption = buyOptionRepository.findById(id);

        return buyOption.map(option -> ResponseEntity.ok(new DetailBuyOptionDTO(option))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "buyOptionList", allEntries = true)
    public ResponseEntity<DetailBuyOptionDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateBuyOptionForm updateBuyOptionForm) {
        Optional<BuyOption> optional = buyOptionRepository.findById(id);
        if (optional.isPresent()) {

            Double percentageDiscount = (updateBuyOptionForm.getNormalPrice() * updateBuyOptionForm.getPercentageDiscount()) / 100;
            Double salePrice = updateBuyOptionForm.getSalePrice() - percentageDiscount;
            updateBuyOptionForm.setSalePrice(salePrice);

            BuyOption buyOption = updateBuyOptionForm.update(id, buyOptionRepository);
            return ResponseEntity.ok(new DetailBuyOptionDTO(buyOption));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "buyOptionList", allEntries = true)
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<BuyOption> buyOption = buyOptionRepository.findById(id);
        if (buyOption.isPresent()) {
            buyOptionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/sellunit/{id}")
    @Transactional
    @CacheEvict(value = "buyOptionList", allEntries = true)
    public ResponseEntity<DetailBuyOptionDTO> sellUnit(@PathVariable Long id, @RequestBody @Valid UpdateSellUnitForm updateSellUnitForm) {
        Optional<BuyOption> optional = buyOptionRepository.findById(id);
        if (optional.isPresent()) {
            if (updateSellUnitForm.getQuantityCupom() > 0) {
                BuyOption buyOption = updateSellUnitForm.update(id, buyOptionRepository);
                return ResponseEntity.ok(new DetailBuyOptionDTO(buyOption));
            } else {
                return ResponseEntity.badRequest().build();
            }
        }

        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> checkout(@PathVariable Long id) {
        Optional<BuyOption> optionalBuyOption = buyOptionRepository.findById(id);
        if (optionalBuyOption.isPresent()) {
            Optional<Deal> optionalDeal = dealRepository.findById(id);
            if (optionalDeal.isPresent()) {
                //relationship
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }
}
