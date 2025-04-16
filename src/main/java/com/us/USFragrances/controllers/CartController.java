package com.us.USFragrances.controllers;

import com.us.USFragrances.models.Cart;
import com.us.USFragrances.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    /**
     * Get cart by ID
     */
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartById(@PathVariable Long cartId) {
        Cart cart = cartService.getCartById(cartId);
        return ResponseEntity.ok(cart);
    }

    /**
     * Add product to cart
     */
    @PostMapping("/cart/add")
    @ResponseBody
    public ResponseEntity<?> addToCart(
            @RequestParam Long cartId,
            @RequestParam Long productId,
            @RequestParam int quantity) {

        try {
            Cart updatedCart = cartService.addToCart(cartId, productId, quantity);
            return ResponseEntity.ok(updatedCart);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add to cart");
        }
    }

    /**
     * Remove product from cart
     */
    @DeleteMapping("/{cartId}/remove/{cartItemId}")
    public ResponseEntity<String> removeFromCart(
            @PathVariable Long cartId,
            @PathVariable Long cartItemId) {

        cartService.removeFromCart(cartId, cartItemId);
        return ResponseEntity.ok("Item removed from cart successfully");
    }

    /**
     * Clear cart
     */
    @DeleteMapping("/{cartId}/clear")
    public ResponseEntity<String> clearCart(@PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.ok("Cart cleared successfully");
    }
}
