package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.service.EventService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="EventListServlet", urlPatterns = "")
public class EventListServlet extends HttpServlet {
    final EventService es;
    final SpringTemplateEngine springTemplateEngine;

    public EventListServlet(EventService es, SpringTemplateEngine springTemplateEngine) {
        this.es = es;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        List<Event> events = new ArrayList<>();
        String text = req.getParameter("text");

        String firstRating = req.getParameter("rating");
        Double rating;
        if(firstRating !=null && !firstRating.isEmpty())
            rating = Double.parseDouble(firstRating);
        else
            rating = null;

        if((text!=null && !text.isEmpty()) || rating==null){
           // events = es.searchEvents(text, rating);
        }
        else{
            events = es.listAll();
        }

        context.setVariable("events", events);
        this.springTemplateEngine.process("listEvents.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.sendRedirect("/eventBooking");
    }
}
