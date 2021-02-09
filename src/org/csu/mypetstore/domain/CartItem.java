package org.csu.mypetstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class CartItem implements Serializable {
  private static final long serialVersionUID = 6620528781626504362L;

  private Item item;
  private int stockQuantity;
  private int cartQuantity;
  private boolean inStock;
  private BigDecimal total;

  public int getCartQuantity() {
    return cartQuantity;
  }

  public void setCartQuantity( int cartQuantity ) {
    this.cartQuantity = cartQuantity;
  }

  public boolean isInStock() {
    return inStock;
  }

  public void setInStock(boolean inStock) {
    this.inStock = inStock;
  }

  public BigDecimal getTotal() {
    return total;
  }

  public Item getItem() {
    return item;
  }

  public void setItem(Item item) {
    this.item = item;
    calculateTotal();
  }

  public int getStockQuantity() {
    return stockQuantity;
  }

  public void setStockQuantity( int stockQuantity ) {
    this.stockQuantity = stockQuantity;
    calculateTotal();
  }

  public void incrementCartQuantity() {
    cartQuantity++;
    calculateTotal();
  }

  private void calculateTotal() {
    if (item != null && item.getListPrice() != null) {
      total = item.getListPrice().multiply(new BigDecimal( cartQuantity ));
    } else {
      total = null;
    }
  }

}
