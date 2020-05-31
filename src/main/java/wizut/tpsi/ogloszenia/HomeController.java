package wizut.tpsi.ogloszenia;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wizut.tpsi.ogloszenia.jpa.BodyStyle;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;
import wizut.tpsi.ogloszenia.jpa.FuelType;
import wizut.tpsi.ogloszenia.jpa.Offer;
import wizut.tpsi.ogloszenia.services.OffersService;

@Controller
public class HomeController {
    
    @Autowired
    private OffersService offersService;
    
    @GetMapping("/")
   public String home(Model model) {
    List<CarManufacturer> carManufacturers = offersService.getCarManufacturers();
    List<CarModel> carModels = offersService.getCarModels();

    List<Offer> offers = offersService.getOffers();

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
}
