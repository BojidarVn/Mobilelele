package bg.softuni.lection2.demo.service.impl;

import bg.softuni.lection2.demo.model.OfferEntity;
import bg.softuni.lection2.demo.model.service.OfferServiceModel;
import bg.softuni.lection2.demo.model.view.OfferSummaryViewModel;
import bg.softuni.lection2.demo.repository.ModelRepository;
import bg.softuni.lection2.demo.repository.OfferRepository;
import bg.softuni.lection2.demo.repository.UserRepository;
import bg.softuni.lection2.demo.security.CurrentUser;
import bg.softuni.lection2.demo.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final CurrentUser currentUser;
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;
    private ModelMapper modelMapper;

    public OfferServiceImpl(CurrentUser currentUser, OfferRepository offerRepository,
                            UserRepository userRepository, ModelRepository modelRepository,
                            ModelMapper modelMapper) {

        this.currentUser = currentUser;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<OfferSummaryViewModel> getAllOffers() {
        // TODO IMPLEMENT mapping
        throw new UnsupportedOperationException("Comming soon :-)");
    }

    @Override
    public long save(OfferServiceModel offerServiceModel) {
        OfferEntity offerEntity= asNewEntity(offerServiceModel);
       OfferEntity newEntity= offerRepository.save(offerEntity);
        return newEntity.getId();
    }

    @Override
    public void delete(long id) {
        offerRepository.deleteById(id);
    }

    private OfferEntity asNewEntity(OfferServiceModel offerServiceModel) {
        OfferEntity offerEntity=new OfferEntity();
        modelMapper.map(offerServiceModel,offerEntity);
        offerEntity.setId(null);

        offerEntity.setModel(modelRepository.findById(offerServiceModel.getModelId()).orElseThrow());
        offerEntity.setUser(userRepository.findByUsername(currentUser.getName()).orElseThrow());

        return offerEntity;

    }
}
