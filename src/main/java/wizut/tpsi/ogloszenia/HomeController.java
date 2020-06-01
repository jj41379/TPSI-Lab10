package wizut.tpsi.ogloszenia;

import java.sql.SQLException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import wizut.tpsi.ogloszenia.jpa.BodyStyle;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;
import wizut.tpsi.ogloszenia.jpa.FuelType;
import wizut.tpsi.ogloszenia.jpa.Offer;
import wizut.tpsi.ogloszenia.services.OffersService;
import wizut.tpsi.ogloszenia.web.OfferFilter;

@Controller
public class HomeController {
    
    @Autowired
    private OffersService offersService;
    
    @GetMapping("/")
    public String home(Model model, OfferFilter offerFilter) {
    List<CarManufacturer> carManufacturers = offersService.getCarManufacturers();
    List<CarModel> carModels = offersService.getCarModels();

    List<Offer> offers;

    if(offerFilter.getManufacturerId()!=null) {
        offers = offersService.getOffersByManufacturer(offerFilter.getManufacturerId());
        } else {
        offers = offersService.getOffers();
        }

    model.addAttribute("carManufacturers", carManufacturers);
    model.addAttribute("carModels", carModels);
    model.addAttribute("offers", offers);

    return "offersList";
}
    
    @GetMapping("/home")
    public String getPosts(Model model) throws SQLException, SQLException {
    model.addAttribute("carManufacturer", offersService.getCarManufacturer(2));
    model.addAttribute("carModel", offersService.getCarModel(3));
    return "home";
    }
    
    @GetMapping("/offer/{id}")
    public String offerDetails(Model model, @PathVariable("id") Integer id) {
    Offer offer = offersService.getOffer(id);
    model.addAttribute("offer", offer);
    return "offerDetails";
    }
    
    @GetMapping("/newoffer")
    public String newOfferForm(Model model, Offer offer) {
        List<CarModel> carModels = offersService.getCarModels();
        List<BodyStyle> bodyStyles = offersService.getBodyStyles();
        List<FuelType> fuelTypes = offersService.getFuelTypes();

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);
    return "offerForm";
    }
    
    @PostMapping("/newoffer")
        public String saveNewOffer(Model model, @Valid Offer offer, BindingResult binding) {
            if(binding.hasErrors()) {
                List<CarModel> carModels = offersService.getCarModels();
                List<BodyStyle> bodyStyles = offersService.getBodyStyles();
                List<FuelType> fuelTypes = offersService.getFuelTypes();

                model.addAttribute("carModels", carModels);
                model.addAttribute("bodyStyles", bodyStyles);
                model.addAttribute("fuelTypes", fuelTypes);

                return "offerForm";
            }
            offer = offersService.createOffer(offer);

            return "redirect:/offer/" + offer.getId();
        }
}
