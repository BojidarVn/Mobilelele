package bg.softuni.lection2.demo.web;

import bg.softuni.lection2.demo.service.OffersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OffersController {

    private final OffersService offersService;

    public OffersController(OffersService offersService) {
        this.offersService = offersService;
    }

    @GetMapping("/all")
    public String getAllOffers(Model model) {
        model.addAttribute("models", offersService.getAllOffers());
        return "offers";
    }



}
