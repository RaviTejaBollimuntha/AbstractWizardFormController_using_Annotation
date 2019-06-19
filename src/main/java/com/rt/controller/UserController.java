package com.rt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.rt.model.User;

@Controller
@RequestMapping("/wizard")
@SessionAttributes({"command","pno"})
public class UserController {

    /**
     * The default handler (page=0)
     */
    @RequestMapping
    public String getInitialPage(final ModelMap modelMap) {
        // put your initial command
        modelMap.addAttribute("command", new User());
        modelMap.addAttribute("pno",0);
        // populate the model Map as needed
        return "RegiForm";
    }
    /**
     * Maybe you want to be provided with the _page parameter (in order to map the same method for all), as you have in
     * AbstractWizardFormController.
     */
    @RequestMapping(method = RequestMethod.POST)
    public String processPage(@RequestParam("_page") final String currentPage,@SessionAttribute("pno") int pno,
                              final @ModelAttribute("command") User command,ModelMap modelMap,
                              final HttpServletResponse response) {
    	int renderpageno=pno;
    	System.out.println(currentPage);
        if(currentPage.equals("Next")) {
        	renderpageno++;
        	modelMap.addAttribute("pno",renderpageno);
        	
        }else if (currentPage.equals("Previous")) {
        	renderpageno--;
        	modelMap.addAttribute("pno",renderpageno);
		}
        if(renderpageno==0)
        	return "RegiForm";
        return  "RegiForm"+renderpageno;
    }

    /**
     * The successful finish step ('_finish' request param must be present)
     */
    @RequestMapping(params = "_finish")
    public String processFinish(final @ModelAttribute("command") User command,
                                final Errors errors,
                                final ModelMap modelMap,
                                final SessionStatus status) {
        // some stuff
        status.setComplete();
        return "ResultForm";
    }

    @RequestMapping(params = "_cancel")
    public String processCancel(final HttpServletRequest request,
                                final HttpServletResponse response,
                                final SessionStatus status) {
        status.setComplete();
        return "canceledView";
    }

}