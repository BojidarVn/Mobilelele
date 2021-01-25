package bg.softuni.lection2.demo;

import bg.softuni.lection2.demo.model.BaseEntity;
import bg.softuni.lection2.demo.model.BrandEntity;
import bg.softuni.lection2.demo.model.ModelEntity;
import bg.softuni.lection2.demo.model.enums.ModelCategoryEnum;
import bg.softuni.lection2.demo.repository.BrandRepository;
import bg.softuni.lection2.demo.repository.ModelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    public DBInit(ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        BrandEntity fordBrand = new BrandEntity();
        fordBrand.setName("Ford");
        serCurrentTimestamp(fordBrand);

        BrandEntity hondaBrand = new BrandEntity();
        hondaBrand.setName("Honda");
        serCurrentTimestamp(hondaBrand);
        brandRepository.saveAll(List.of(fordBrand, hondaBrand));

        ModelEntity fiesta = initFiesta(fordBrand);

        ModelEntity escort = initEscort(fordBrand);

     initNC750S(hondaBrand);


    }

    private ModelEntity initNC750S(BrandEntity hondaBrand) {
        ModelEntity nc750s = new ModelEntity();

        nc750s
                .setName("NC750S")
                .setCategory(ModelCategoryEnum.MOTORCYCLE)
                .setImageUrl("https://prod.pictures.autoscout24.net/listing-images/f369d2b5-0916-a219-e053-0100007f05f3_04423b22-f875-4606-8017-a1fa42b7e096.jpg/1280x960.jpg")
                .setStartYear(2014)
                .setBrand(hondaBrand);

        serCurrentTimestamp(nc750s);
        return modelRepository.save(nc750s);
    }

    private ModelEntity initEscort(BrandEntity fordBrand) {
        ModelEntity escort = new ModelEntity();

        escort
                .setName("Escort")
                .setCategory(ModelCategoryEnum.CAR)
                .setImageUrl("https://67degreescdn.co.uk/105/3/114306/15825450215e53b87d931aa_img-8340.jpg?width=480")
                .setStartYear(1968)
                .setEndYear(2002)
                .setBrand(fordBrand);
        serCurrentTimestamp(escort);
        return modelRepository.save(escort);
    }

    private ModelEntity initFiesta(BrandEntity fordBrand) {
        ModelEntity fiesta = new ModelEntity();

        fiesta
                .setName("Fiesta")
                .setCategory(ModelCategoryEnum.CAR)
                .setImageUrl("https://media.autoexpress.co.uk/image/private/s--zkHyvWs---/f_auto,t_content-image-full-desktop@2/v1579598728/autoexpress/2020/01/new-fiesta-trend.jpg")
                .setStartYear(1976)
                .setBrand(fordBrand);
        serCurrentTimestamp(fiesta);
        return modelRepository.save(fiesta);
    }

    public static void serCurrentTimestamp(BaseEntity baseEntity) {
        baseEntity
                .setCreated(Instant.now())
                .setUpdated(Instant.now());
    }
}
































