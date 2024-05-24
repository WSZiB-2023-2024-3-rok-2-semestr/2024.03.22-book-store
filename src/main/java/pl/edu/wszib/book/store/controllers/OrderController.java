package pl.edu.wszib.book.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.wszib.book.store.exceptions.EmptyCartException;
import pl.edu.wszib.book.store.exceptions.IncorrectCartPositionsException;
import pl.edu.wszib.book.store.exceptions.UserNotLoggedException;
import pl.edu.wszib.book.store.services.IOrderService;

@Controller
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order/add")
    public String order() {
        try {
            this.orderService.confirmOrder();
        } catch (EmptyCartException | IncorrectCartPositionsException e) {
            return "redirect:/cart";
        }
        return "redirect:/";
    }

    @GetMapping("/order")
    public String orders(Model model) {
        try {
            model.addAttribute("orders", this.orderService.getOrdersForCurrentUser());
        } catch (UserNotLoggedException e) {
            return "redirect:/";
        }
        return "orders";
    }
}
