package bg.softuni.lection2.demo;

import bg.softuni.lection2.demo.model.*;
import bg.softuni.lection2.demo.model.enums.EngineEnum;
import bg.softuni.lection2.demo.model.enums.ModelCategoryEnum;
import bg.softuni.lection2.demo.model.enums.TransmissionEnum;
import bg.softuni.lection2.demo.model.enums.UserRoleEnum;
import bg.softuni.lection2.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final OfferRepository offerRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserRoleRepository userRoleRepository;

    public DBInit(ModelRepository modelRepository, BrandRepository brandRepository, OfferRepository offerRepository,
                  UserRepository userRepository , PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
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

        ModelEntity fiestaModel = initFiesta(fordBrand);

        ModelEntity escort = initEscort(fordBrand);

        initNC750S(hondaBrand);

        createFiestaOffer(fiestaModel);

        initUsers();



    }


    private void initUsers() {

        UserRoleEntity adminRole=new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
        UserRoleEntity userRole=new UserRoleEntity().setRole(UserRoleEnum.USER);

        userRoleRepository.saveAll(List.of(adminRole,userRole));

        UserEntity admin = new UserEntity();
        admin
                .setFirstName("Кирил")
                .setLastName("Димитров")
                .setUsername("admin")
                .setPassword(passwordEncoder.encode("topsecred"))
        .setUserRoles(List.of(adminRole,userRole));
serCurrentTimestamp(admin);

        UserEntity pesho = new UserEntity();
        pesho
                .setFirstName("Петър")
                .setLastName("Иванов")
                .setUsername("pesho")
                .setPassword(passwordEncoder.encode("topsecred"))
                .setUserRoles(List.of(userRole));
        serCurrentTimestamp(pesho);

        userRepository.saveAll(List.of(admin,pesho));
    }



    private void createFiestaOffer(ModelEntity modelEntity) {
        OfferEntity fiestaOffer=new OfferEntity();

        fiestaOffer
                .setEngine(EngineEnum.DIESEL)
                .setImageUrl("https://www.ford.com/content/dam/vdm_ford/live/en_us/ford/nameplate/ecosport/2020/collections/_360/lightningblue/ecosport_20_lightning_blue_34.jpg")
                .setMileage(40000)
                .setPrice(BigDecimal.valueOf(10000))
                .setYear(2019)
                .setDescription("Karana e ot nemska baba. Zimata v garaj")
                .setTransmission(TransmissionEnum.MANUAL)
                .setModel(modelEntity);

        serCurrentTimestamp(fiestaOffer);

        offerRepository.save(fiestaOffer);
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
































