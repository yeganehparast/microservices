package com.bestseller.assignment.starbux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * This controller redirects the requests to the UI interface
 */
@Controller
@ApiIgnore
public class HomeController
{

    /*
    TODO About the way of exception handling
    The following code is happend frequently in the controllers method
        } catch (ProductNotFoundException pnfe) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, pnfe.getMessage());
        } catch (DataIntegrityViolationException dive) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Data integrity violation");
        } catch (TransactionSystemException tse) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Data constraint violation");
        }

        It makes code a bit dirty. Futhermore, there are many duplicate codes (Don't Repeat yourself)
        My idea is that using @ControllerAdvice of the spring in such cases would be better selection.
     */
    @RequestMapping("/")
    public String home()
    {
        return "redirect:swagger-ui.html";
    }

}
