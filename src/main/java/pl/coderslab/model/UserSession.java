package pl.coderslab.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {



//    List<CartItem> cartItems =new ArrayList<>();
//
//    public void addToCart(CartItem cartItem) {
//        cartItems.add((cartItem));
//    }
//
//    public List<CartItem> getCartItems() {
//        return cartItems;
//    }


}
