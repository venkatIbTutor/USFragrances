package com.us.USFragrances.services;

import com.us.USFragrances.models.Cart;
import com.us.USFragrances.models.CartItem;
import com.us.USFragrances.models.Product;
import com.us.USFragrances.repositories.CartRepository;
import com.us.USFragrances.repositories.CartItemRepository;
import com.us.USFragrances.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart addToCart(Long cartId, Long productId, int quantity) {
        Cart cart = getCartById(cartId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            CartItem newItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(quantity)
                    .build();
            cart.addItem(newItem);
        }

        return cartRepository.save(cart);
    }

    public void removeFromCart(Long cartId, Long cartItemId) {
        Cart cart = getCartById(cartId);
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("CartItem not found"));

        cart.removeItem(item);
        cartItemRepository.delete(item);
        cartRepository.save(cart);
    }

    public void clearCart(Long cartId) {
        Cart cart = getCartById(cartId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }
}
