package wizut.tpsi.ogloszenia;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;
import wizut.tpsi.ogloszenia.services.OffersService;

@Controller
public class HomeController {
    
    @Autowired
    private OffersService offersService;
    
    @GetMapping("/")
    public String getPosts(Model model) throws SQLException, SQLException {
    model.addAttribute("carManufacturer", offersService.getCarManufacturer(2));
    model.addAttribute("carModel", offersService.getCarModel(3));
    return "index";
    }
}
