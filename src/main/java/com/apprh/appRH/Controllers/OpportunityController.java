package com.apprh.appRH.Controllers;

import com.apprh.appRH.Repositories.CandidateRepository;
import com.apprh.appRH.Repositories.OpportunityRepository;
import com.apprh.appRH.models.AppRhOpportunity;
import com.apprh.appRH.models.Candidate;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.UUID;


@RestController
public class OpportunityController {

    private OpportunityRepository opr;
    private CandidateRepository cr;

    @RequestMapping(value = "/cadop", method = RequestMethod.GET)
    public String form() {
        return "opportunity/formOpportunity";
    }

    @RequestMapping(value = "cadop", method = RequestMethod.POST)
    public String form(@Valid AppRhOpportunity opportunity, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Check the fields..");
            return "redirect:/cadop";
        }
        opr.save(opportunity);
        attributes.addFlashAttribute("message", "Opportunity successfully registred!");
        return "redirect:/cadop";
    }

    @RequestMapping("/opportunity")
    public ModelAndView listOpportunity() {
        ModelAndView mav = new ModelAndView("opportunity/list");
        Iterable<AppRhOpportunity> opportunities = opr.findAll();
        mav.addObject("opportunities", opportunities);
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView opportunityDetails(@PathVariable("id")UUID id) {
        AppRhOpportunity opportunity = opr.findById(id);
        ModelAndView mav = new ModelAndView("opportunity/details");
        mav.addObject("opportunity", opportunity);
        Iterable<Candidate> candidates = cr.findByOpportunity(opportunity);
        mav.addObject("candidates", candidates);
        return mav;
    }

    @RequestMapping("/deleteop")
    public String deletOpportunity(UUID id) {
        AppRhOpportunity opportunity = opr.findById(id);
        opr.delete(opportunity);
        return "redirect:/opportunities";
    }

    public String opportunityDetails(@PathVariable("id") UUID id, @Valid Candidate candidate, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Check the fields...");
            return "redirect:/{id}";
        }
        if (cr.findByRg(candidate.getRg()) != null) {
            attributes.addFlashAttribute("message error", "duplicate RG!");
            return "redirect:/{id}";
        }
        AppRhOpportunity opportunity = opr.findById(id);
        candidate.setOpportunity(opportunity);
        cr.save(candidate);
        attributes.addFlashAttribute("message", "Candidate successfully added!");
        return "redirect:/{id}";
    }

    @RequestMapping("/deletecand")
    public String deleteCandidate(String rg) {
        Candidate candidate = cr.findByRg(rg);
        AppRhOpportunity opportunity = candidate.getOpportunity();
        String id = "" + opportunity.getId();
        cr.delete(candidate);

        return "redirect:/" + id;
    }

    //edição da vaga
    @RequestMapping(value = "edit-opportunity", method = RequestMethod.GET)
    public ModelAndView editOpportunity(UUID id) {
        AppRhOpportunity opportunity = opr.findById(id);
        ModelAndView mv = new ModelAndView("opportunity/updateop");
        mv.addObject("opportunity", opportunity);
        return mv;
    }

    //atualização da vaga
    @RequestMapping(value = "/edit-opportunity", method = RequestMethod.POST)
    public String updateOpportunity(@Valid AppRhOpportunity opportunity, BindingResult result, RedirectAttributes attributes) {
        opr.save(opportunity);
        attributes.addFlashAttribute("success", "Opportunity successfully update!");
        UUID uuid = opportunity.getId();
        String id = "" + uuid;
        return "redirect:/" + uuid;
    }
}
