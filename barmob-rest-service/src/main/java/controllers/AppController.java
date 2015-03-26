package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by gustavokm90 on 3/25/15.
 */
@Controller
@RequestMapping(value = "/app")
public class AppController {
    @RequestMapping(value = "/client", method = RequestMethod.GET)

    public ModelAndView welcome() {

        ModelAndView model = new ModelAndView("client");

        return model;

    }
}
