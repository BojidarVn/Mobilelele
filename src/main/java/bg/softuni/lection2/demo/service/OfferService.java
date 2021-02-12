package bg.softuni.lection2.demo.service;

import bg.softuni.lection2.demo.model.service.OfferServiceModel;
import bg.softuni.lection2.demo.model.view.OfferSummaryViewModel;

import java.util.List;

public interface OfferService {
    List<OfferSummaryViewModel> getAllOffers();

long save(OfferServiceModel offerServiceModel);

void delete(long id);
}
