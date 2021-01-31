package bg.softuni.lection2.demo.service.impl;

import bg.softuni.lection2.demo.model.BrandEntity;
import bg.softuni.lection2.demo.model.ModelEntity;
import bg.softuni.lection2.demo.service.BrandService;
import bg.softuni.lection2.demo.model.view.BrandViewModel;
import bg.softuni.lection2.demo.model.view.ModelViewModel;
import bg.softuni.lection2.demo.repository.ModelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BrandViewModel> getAllBrands() {

        List<BrandViewModel> result = new ArrayList<>();
        List<ModelEntity> allModels = modelRepository.findAll();



        allModels.forEach(me -> {
            // example fiesta -> ford
            BrandEntity brandEntity = me.getBrand();
            Optional<BrandViewModel> brandViewModelOpt = findByName(result, brandEntity.getName());
            if (!brandViewModelOpt.isPresent()) {
                // ako q nqma vkarana q suzdavame tyka
                BrandViewModel newBrandViewModel = new BrandViewModel();
                modelMapper.map(brandEntity, newBrandViewModel);
                result.add(newBrandViewModel);
                brandViewModelOpt=Optional.of(newBrandViewModel);
            }
            ModelViewModel newModelViewModel= new ModelViewModel();
            modelMapper.map(me,newModelViewModel);
            brandViewModelOpt.get().addModel(newModelViewModel);
        });
        return result;
    }

    private static Optional<BrandViewModel>
    findByName(List<BrandViewModel> allModels, String name) {

        return allModels.stream()
                .filter(m -> m.getName().equals(name)).findAny();
    }
}
