package by.team34.controller;

import by.team34.dto.TemplateDto;
import by.team34.dto.VacancyDto;
import by.team34.entity.Vacancy;
import by.team34.service.IGenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vacancy")
public class VacancyController {

    @Resource(name="vacancyService")
    private IGenericService<Vacancy, Long> service;


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.PUT)
    @ResponseBody
    public Vacancy saveVacancy(@Valid @RequestBody Vacancy vacancy,
                               BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        if (vacancy.getId() != null) {
            service.update(vacancy);
        } else {
            service.insert(vacancy);
        }
        return vacancy;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public List<VacancyDto> vacancyList() {
        return TemplateDto.parseVacancyDto(service.findAll());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteVacancy(@RequestParam(value = "id") Long id) {
        service.delete(id);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    @ResponseBody
    public List<VacancyDto> VacancySort(@RequestParam(value = "type") String type) {
        return TemplateDto.parseVacancyDto(service.sort(type));
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public VacancyDto getVacancy(@RequestParam(value = "id") Long id) {
        return new VacancyDto(service.findBy(id));
    }
}
